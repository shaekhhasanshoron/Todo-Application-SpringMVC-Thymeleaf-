package com.shoron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoron.service.LoginService;


@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	

	@RequestMapping(value="/login", method= RequestMethod.GET)
	public String showLoginPage(){
		return "login";
	}
	
	@RequestMapping(value="/login", method= RequestMethod.POST)
	public String handleLoginRequest(@RequestParam String name,
			@RequestParam String password,
			Model model){
		
		if(loginService.validateUser(name, password)){
			model.addAttribute("name",name);
			model.addAttribute("password",password);
			return "welcome";
		}
		else{
			model.addAttribute("errorMessage","Invalid Username or Password");
				return "login";		
		}
		
	
	}
	
}