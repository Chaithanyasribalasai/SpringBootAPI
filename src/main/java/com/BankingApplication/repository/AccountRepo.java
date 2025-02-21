package com.BankingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BankingApplication.entity.Account;

public interface AccountRepo extends JpaRepository<Account , Long>{

}
