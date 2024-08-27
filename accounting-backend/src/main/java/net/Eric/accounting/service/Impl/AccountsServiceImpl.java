package net.Eric.accounting.service.Impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
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

}
