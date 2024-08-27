package net.Eric.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.Eric.accounting.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	List<Account> findByCategory(String category);
}
