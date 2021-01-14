package com.todolist.todolist_ka.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todolist.todolist_ka.model.ToDoList;
import com.todolist.todolist_ka.model.User;
/**
 * 
 * JPA repository for TODoList 
 * 
 * @author Kumuditha Athukorala
 *
 */
@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Long>{

	
	ArrayList<ToDoList> findByUser(User u);
	
	void deleteById(long l);
}
