package com.ingg.appaunthentication.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ingg.appaunthentication.domain.User;
import com.ingg.appaunthentication.model.JwtUser;
import com.ingg.appaunthentication.service.CustomSequencesService;

@Component
public class UserConverter {
	@Autowired
	CustomSequencesService customSequencesService;
	
	public User convertJwtUserToUser(JwtUser jwtUser) {
		User userNew = new User();
		userNew.setId(String.valueOf(customSequencesService.getNextSequence("user_id")));
		userNew.setUsername(jwtUser.getUserName());
		userNew.setPassword(jwtUser.getPassword());
		userNew.setRole(jwtUser.getRole()==null?"admin":jwtUser.getRole());
		return userNew;

	}
	
}
