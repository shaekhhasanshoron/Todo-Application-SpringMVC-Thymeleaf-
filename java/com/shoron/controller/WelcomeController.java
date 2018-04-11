package com.shoron.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.jsse.openssl.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import com.shoron.service.LoginService;


@Controller
//@SessionAttributes("name")
public class WelcomeController {

//	@Autowired
//	LoginService loginService;
	

	@RequestMapping(value="/", method= RequestMethod.GET)
	public String showLoginPage(ModelMap model){
		model.put("name", "shoron");
		return "welcome";
	}	
	
//	@RequestMapping(value="/login", method= RequestMethod.POST)
//	public String handleLoginRequest(@RequestParam String name,
//			@RequestParam String password,
//			Model model){
//		
////		if(loginService.validateUser(name, password)){
////			model.addAttribute("name",name);
////			model.addAttribute("password",password);
////			return "welcome";
////		}
////		else{
////			model.addAttribute("errorMessage","Invalid Username or Password");
////				return "login";		
////		}
//		model.addAttribute("name",name);
//		return "welcome";
//		
//	
//	}
	
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	        request.getSession().invalidate();
	    }
	 
		return "redirect:/";
	}
	
}
