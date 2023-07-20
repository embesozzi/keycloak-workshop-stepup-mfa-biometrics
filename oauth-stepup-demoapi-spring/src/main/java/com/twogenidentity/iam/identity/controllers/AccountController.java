package com.twogenidentity.iam.identity.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.twogenidentity.iam.identity.models.Account;
import com.twogenidentity.iam.identity.repository.AccountRepository;

@RestController
@CrossOrigin
@RequestMapping(value={"/api/v1/accounts"})
public class AccountController
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AccountRepository accountRepository;
	
	@GetMapping()
	public Iterable<Account> index() {
		log.debug("Get all accounts");
		return this.accountRepository.findAll();
	}
}
