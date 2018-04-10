package com.shoron.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	public boolean validateUser(String user, String pass){
		return user.equalsIgnoreCase("user") 
				&& pass.equalsIgnoreCase("user");
	}
	
}
