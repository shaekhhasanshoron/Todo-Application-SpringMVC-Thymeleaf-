package com.shoron.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.shoron.service.TodoService;
import com.shoron.todo.Todo;

@Controller
@SessionAttributes("name")
public class HomeController {

	@Autowired
	TodoService todoService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, false));
	}	
	
	
	
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
		model.addAttribute("todo",new Todo());
		return "add-update-todo";
	}		

	@RequestMapping(value = "/addtodo", method = RequestMethod.POST)
	public String addTodo(ModelMap model,@Valid Todo todo, BindingResult result) {
		
		if(result.hasErrors()){
			return "add-update-todo";
		}
		todoService.addTodo("shoron",todo.getDescription(), new Date(), false);
		model.clear(); // for not letting any extra parameter
		return "redirect:/todo";
	}
	
	@RequestMapping(value = "/updatetodo", method = RequestMethod.GET)
	public String getSingleTodo(ModelMap model,@RequestParam int id) {
		Todo todo=todoService.retrieveSingleTodo(id);
		model.clear();
		model.addAttribute("todo",todo);
		return "add-update-todo";
	}	
	
	@RequestMapping(value = "/updatetodo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model,@Valid Todo todo, BindingResult result) {
		
		if(result.hasErrors()){
			return "add-update-todo";
		}
		model.clear();
		todoService.updateTodo(todo);
		return "redirect:/todo";
	}
	
	
	@RequestMapping(value = "/deletetodo", method = RequestMethod.GET)
	public String deleteTodo(ModelMap model,@RequestParam int id) {
		todoService.deleteTodo(id);
		model.clear();
		return "redirect:/todo";
	}
}
