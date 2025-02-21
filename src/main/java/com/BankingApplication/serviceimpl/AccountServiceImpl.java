
package com.BankingApplication.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.BankingApplication.Mapper.AccountMapper;
import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.entity.Account;
import com.BankingApplication.repository.AccountRepo;
import com.BankingApplication.service.AccountSrervice;


@Service
public class AccountServiceImpl implements AccountSrervice{

	
	private AccountRepo accountrepo;
	
	public AccountServiceImpl(AccountRepo accountrepo) {
		super();
		this.accountrepo = accountrepo;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account=AccountMapper.mapToAccount(accountDto);   
		
		Account saveAccount= accountrepo.save(account);
		return AccountMapper.mapToaccountDto(saveAccount);
	}

	@Override
	public AccountDto getacoountById(Long id) {
		Account account=accountrepo.findById(id).orElseThrow(()->new RuntimeException("Account Does not Exists"));
		return AccountMapper.mapToaccountDto(account);
	}

	@Override
	public AccountDto dipost(Long id, double amount) {
		Account account=accountrepo.findById(id).orElseThrow(()->new RuntimeException("Account Does not Exists"));
		double total = account.getBalance()+amount;
		account.setBalance(total);
		Account savedAccount=accountrepo.save(account);
		return AccountMapper.mapToaccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account=accountrepo.findById(id).orElseThrow(()->new RuntimeException("Account Does not Exists"));
		
		if(account.getBalance()<=amount) {
			
			throw new RuntimeException("InSuffient Ammount");
			  
		}
		else {
		
		double total = account.getBalance()-amount;
		account.setBalance(total);
		Account savedAccount=accountrepo.save(account);
		
		return AccountMapper.mapToaccountDto(savedAccount);
	}}

	@Override
	public List<AccountDto> getAllAccounts() {
		List <Account> accounts=accountrepo.findAll();
		return accounts.stream().map((account) -> AccountMapper.mapToaccountDto(account)).collect(Collectors.toList());
		
	}

	@Override
	public void deleteAccount(Long id) {
		Account account=accountrepo.findById(id).orElseThrow(()->
		new RuntimeException("Account Does not Exists"));
		
		accountrepo.deleteById(id); 
		
		
		
	}

	
}
