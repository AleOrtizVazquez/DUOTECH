package com.duotech.repository;

import com.duotech.entity.Account;
import com.duotech.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<User, Integer> {

    User findTop1ByemailAndPasswordAndActive(String email, String password, boolean active);


    User findTop1ByemailAndAccountAndActive(String email, Account account, boolean active);


    User findTop1ByemailAndActive(String email,boolean active);



    User findTop1ByemailAndNameAndActive(String emaile ,String name,boolean active);

}
