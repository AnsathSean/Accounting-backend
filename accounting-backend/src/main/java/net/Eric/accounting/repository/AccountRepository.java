package net.Eric.accounting.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.Eric.accounting.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	List<Account> findByCategory(String category);
	List<Account> findAllByCreateDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
