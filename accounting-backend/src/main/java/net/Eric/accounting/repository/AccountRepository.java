package net.Eric.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.Eric.accounting.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
