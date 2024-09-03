package net.Eric.accounting.service.Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import net.Eric.accounting.dto.AccountDto;
import net.Eric.accounting.entity.Account;
import net.Eric.accounting.repository.AccountRepository;
import net.Eric.accounting.service.AccountsService;
import net.Eric.accounting.exception.ResourceNotFoundException;

@Service
@Component
public class AccountsServiceImpl implements AccountsService{

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		//System.out.println("accountDto expensed:"+accountDto.getExpensed());
		//System.out.println("accountDto Amount:"+accountDto.getAmount());
		//System.out.println("accountDto Category:"+accountDto.getCategory());
		
	    // 获取 UTC+8 时区的当前日期时间
	    ZonedDateTime utcPlus8 = ZonedDateTime.now(ZoneId.of("Asia/Taipei"));
	    LocalDateTime currentDateTime = utcPlus8.toLocalDateTime();
		
		Account account = modelMapper.map(accountDto, Account.class);
	    // 设置 createDate
	    account.setCreateDate(currentDateTime);
		
		//System.out.println("account expensed:"+account.getExpensed());
		//System.out.println("account Amount:"+account.getAmount());
		//System.out.println("account Category:"+account.getCategory());
		
		
		Account savedAccount = accountRepository.save(account); 
		return modelMapper.map(savedAccount, AccountDto.class);
	}

	@Override
	public AccountDto getAccount(Long id) {
		
		
		Account account = accountRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Account not found with id:"+id));
		
		//System.out.println("accountDto expensed:"+account.getExpensed());
		//System.out.println("accountDto Amount:"+account.getAmount());
		//System.out.println("accountDto Category:"+account.getCategory());
		
		return modelMapper.map(account, AccountDto.class);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account)->modelMapper.map(account,AccountDto.class)).collect(Collectors.toList());
	}

	@Override
	public AccountDto updateAccount(AccountDto accountDto, Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Account not found with id:"+id));
		
	    // 获取 UTC+8 时区的当前日期时间
	    ZonedDateTime utcPlus8 = ZonedDateTime.now(ZoneId.of("Asia/Taipei"));
	    LocalDateTime currentDateTime = utcPlus8.toLocalDateTime();
	    
	    account.setExpensed(accountDto.getExpensed());
	    account.setAmount(accountDto.getAmount());
	    account.setCategory(accountDto.getCategory());
	    account.setCreateDate(currentDateTime);
	    account.setName(accountDto.getName());
	    Account savedAccount = accountRepository.save(account); 
		
		return modelMapper.map(savedAccount, AccountDto.class);
	}

	@Override
	public void deleteAccount(Long id) {
		accountRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Account not found with id:"+id));
		accountRepository.deleteById(id);
		
	}

	@Override
	public List<AccountDto> getAllAccountsByCategory(String category) {
		List<Account> accounts = accountRepository.findByCategory(category);
		return accounts.stream().map((account)->modelMapper.map(account,AccountDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<String> getAllCategories() {
	       List<Account> accounts = accountRepository.findAll();
	        return accounts.stream()
	                .map(Account::getCategory)
	                .distinct()
	                .collect(Collectors.toList());
	}

	@Override
	public List<AccountDto> getAccountsByWeek(int year, int week) {
		   // 計算該週的起始日期和結束日期
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        LocalDate startDate = LocalDate.now()
            .withYear(year)
            .with(weekFields.weekOfYear(), week)
            .with(weekFields.dayOfWeek(), 1); // 週一
        LocalDate endDate = startDate.plusDays(6); // 週日

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        // 查詢符合條件的帳戶資料
        List<Account> accounts = accountRepository.findAllByCreateDateBetween(startDateTime, endDateTime);

        // 將 Entity 轉換為 DTO
        return accounts.stream().map(account -> new AccountDto(
                account.getId(),
                account.getName(),
                account.getExpensed(),
                account.getCategory(),
                account.getAmount(),
                account.getCreateDate()
        )).collect(Collectors.toList());
	}

}
