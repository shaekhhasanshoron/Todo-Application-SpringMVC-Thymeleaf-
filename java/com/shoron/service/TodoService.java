package com.shoron.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shoron.todo.Todo;

@Service
public class TodoService {

	private static List<Todo> todoList = new ArrayList<Todo>();
	private static int numberOfTodos = 3;

	static {
		todoList.add(new Todo(1, "Wake Up Morning", "Wake up early in the morning", new Date(), false));
		todoList.add(new Todo(2, "Do Exercise", "Do some home workuout and running", new Date(), false));
		todoList.add(new Todo(3, "Go To Work", "Go to office for work", new Date(), false));
	}
	
}
