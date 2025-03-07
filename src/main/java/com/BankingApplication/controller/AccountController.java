package com.BankingApplication.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.service.AccountSrervice;

@RestController
@RequestMapping("api/accounts")
public class AccountController {
	
private AccountSrervice accountSrevice;

	public AccountController(AccountSrervice accountSrevice) {
		super();
		this.accountSrevice = accountSrevice;
	}

	//Add Account REST API
	@PostMapping()
	public ResponseEntity <AccountDto> addAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountSrevice.createAccount(accountDto),HttpStatus.CREATED);
	}
	
	//Get Account REST API 
	@GetMapping("/{Id}")
	public ResponseEntity<AccountDto> getAccouuntById(@PathVariable Long Id){
		AccountDto accountDto=accountSrevice.getacoountById(Id);
		return ResponseEntity.ok(accountDto);
	}
	
	
	//Deposit REST API 
	
	@PutMapping("/{id}/deposit")
	
	
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String , Double>request){
		Double amount=request.get("amount");
		AccountDto accountDto =accountSrevice.dipost(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	//Withdraw REST API 
	
	@PutMapping("/{id}/withdraw")
	
	
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String , Double>request){
		Double amount=request.get("amount");
		AccountDto accountDto =accountSrevice.withdraw(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	//GetAll REST API 
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		List<AccountDto> accounts= accountSrevice.getAllAccounts();
		return ResponseEntity.ok(accounts);
		
	}
	
	
	//Delete Account REST API 
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id){
		accountSrevice.deleteAccount(id);
		return ResponseEntity.ok("Account Deleted Successfully!!");
	}
	
	
	
}
