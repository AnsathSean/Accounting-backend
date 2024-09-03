package net.Eric.accounting.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.Eric.accounting.dto.AccountDto;
import net.Eric.accounting.service.AccountsService;
import java.util.List;

@CrossOrigin("*")
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
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccount(@PathVariable("id") Long id){
		//System.out.println("我有跑到Get id這裡");
		AccountDto accountDto = accountsService.getAccount(id);
		return new ResponseEntity<>(accountDto, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		List<AccountDto> accountDtos = accountsService.getAllAccounts();
		return new ResponseEntity<>(accountDtos,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AccountDto> updateAccounts(@PathVariable("id") Long id,@RequestBody AccountDto accountDto){
		AccountDto updateAaccountDto = accountsService.updateAccount(accountDto, id);
		return new ResponseEntity<>(updateAaccountDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable("id")Long id){
		accountsService.deleteAccount(id);
		return new ResponseEntity<>("Delete Success",HttpStatus.OK);
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<AccountDto>> getAllAccountsByCategory(@PathVariable("category") String category){
		List<AccountDto> accountDtos = accountsService.getAllAccountsByCategory(category);
		return new ResponseEntity<>(accountDtos,HttpStatus.OK);
	}
	
    @GetMapping("/categories")
    public List<String> getAllCategories() {
        return accountsService.getAllCategories();
    }
    
    @GetMapping("/weekly/{year}/{week}")
    public List<AccountDto> getAccountsByWeek(@PathVariable("year") int year, @PathVariable("week") int week) {
        return accountsService.getAccountsByWeek(year, week);
    }
	
}
