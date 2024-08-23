package net.Eric.accounting.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.Eric.accounting.dto.AccountDto;
import net.Eric.accounting.service.AccountsService;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	
	@Autowired
	private AccountsService accountsService;
	
	//Build Add Account REST API
	@PostMapping
	public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
		AccountDto savedAccountDto = accountsService.createAccount(accountDto);
		return new ResponseEntity<>(savedAccountDto,HttpStatus.CREATED);
	}
	
	
	
}
