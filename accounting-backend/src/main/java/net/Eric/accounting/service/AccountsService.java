package net.Eric.accounting.service;

import java.util.List;

import net.Eric.accounting.dto.AccountDto;

public interface AccountsService {

	AccountDto createAccount(AccountDto accountDto);
	AccountDto getAccount(Long id);
	List<AccountDto> getAllAccounts();
	AccountDto updateAccount(AccountDto accountDto,Long id);
	void deleteAccount(Long id);
	List<AccountDto> getAllAccountsByCategory(String category);
	List<String> getAllCategories();
	List<AccountDto> getAccountsByWeek(int year, int week);
}
