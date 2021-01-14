package com.todolist.todolist_ka.service;

import java.util.ArrayList;

import com.todolist.todolist_ka.model.ToDoList;
import com.todolist.todolist_ka.model.User;

/**
 * 
 * Service interface for TODoList 
 * 
 * @author Kumuditha Athukorala
 *
 */
public interface ToDoListService {

	void save(ToDoList toDoList);
	
	ArrayList<ToDoList> findByUser(User u);
	
	void deleteById(long l);
	
	void update(ToDoList toDoList);
}
