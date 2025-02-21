package com.BankingApplication.service;

import java.util.List;

import com.BankingApplication.dto.AccountDto;

public interface AccountSrervice {

	
	AccountDto createAccount(AccountDto accountDto);
	AccountDto getacoountById(Long id);
	AccountDto dipost(Long id,double amount);
	AccountDto withdraw(Long id,double amount);
	List<AccountDto> getAllAccounts ();
	void deleteAccount(Long id);
}
