package com.ingg.appaunthentication.repository;

import org.springframework.data.repository.CrudRepository;

import com.ingg.appaunthentication.domain.User;



public interface UserRepository extends CrudRepository< User, String >{

	User findByUsername(String userName);

}
