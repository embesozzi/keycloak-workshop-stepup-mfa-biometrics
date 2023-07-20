package com.twogenidentity.iam.identity.repository;

import com.twogenidentity.iam.identity.models.Account;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
	@Override
	Iterable<Account> findAll();

}
