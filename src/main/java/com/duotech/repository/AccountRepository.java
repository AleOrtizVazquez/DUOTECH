package com.duotech.repository;

import com.duotech.entity.Account;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account ,Integer> {

    Account findTop1Byaccount(String account);

    Optional<Account> findById(Integer id);

}
