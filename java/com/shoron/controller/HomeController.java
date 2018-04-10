package com.shoron.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.shoron.service.TodoService;

@Controller
@SessionAttributes("name")
public class HomeController {

	@Autowired
	TodoService todoService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String indexPage() {
		return "index";
	}
	
	@RequestMapping(value = "/todo", method = RequestMethod.GET)
	public String listOfTodos(Model model) {
		model.addAttribute("todoList", todoService.retrieveTodos("shoron"));
		return "list-todo";
	}

	@RequestMapping(value = "/addtodo", method = RequestMethod.GET)
	public String showTodoListPage(Model model) {
		return "add-update-todo";
	}

	@RequestMapping(value = "/addtodo", method = RequestMethod.POST)
	public String addTodos(ModelMap model,@RequestParam String description) {
		todoService.addTodo("shoron", description, new Date(), false);
		model.clear(); // for not letting any extra parameter
		return "redirect:/todo";
	}

}
