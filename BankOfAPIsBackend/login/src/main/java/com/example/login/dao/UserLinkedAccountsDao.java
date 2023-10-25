package com.example.login.dao;

import com.example.login.model.UserLinkedAccounts;
import org.springframework.data.repository.CrudRepository;

public interface UserLinkedAccountsDao extends CrudRepository<UserLinkedAccounts, Integer> {

}
