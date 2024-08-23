package net.Eric.accounting.service.Impl;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import net.Eric.accounting.dto.AccountDto;
import net.Eric.accounting.entity.Account;
import net.Eric.accounting.repository.AccountRepository;
import net.Eric.accounting.service.AccountsService;

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
		
		System.out.println("account expensed:"+account.getExpensed());
		System.out.println("account Amount:"+account.getAmount());
		System.out.println("account Category:"+account.getCategory());
		
		
		Account savedAccount = accountRepository.save(account); 
		return modelMapper.map(savedAccount, AccountDto.class);
	}

}
