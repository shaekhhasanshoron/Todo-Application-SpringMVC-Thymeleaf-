package com.shoron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shoron.service.TodoService;

@Controller
public class HomeController {

	@Autowired
	TodoService todoService;
	
	@RequestMapping(value="/todo", method=RequestMethod.GET)
	public String listOfTodos(Model model){
		model.addAttribute("todoList", todoService.retrieveTodos("shoron"));
		return "todo";
	}	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String indexPage(){
		return "index";
	}

}
