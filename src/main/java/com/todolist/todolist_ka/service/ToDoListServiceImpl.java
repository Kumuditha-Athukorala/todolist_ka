package com.todolist.todolist_ka.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.todolist_ka.model.ToDoList;
import com.todolist.todolist_ka.model.User;
import com.todolist.todolist_ka.repository.ToDoListRepository;

/**
 * 
 * Service implementation class for TODoList 
 * 
 * @author Kumuditha Athukorala
 *
 */
@Service
public class ToDoListServiceImpl implements ToDoListService{

	@Autowired
    private ToDoListRepository toDoListRepository;
	
	@Override
	public void save(ToDoList toDoList) {		
		toDoListRepository.save(toDoList);
	}

	@Override
	public ArrayList<ToDoList> findByUser(User u) {
		
		ArrayList<ToDoList> arrayList = new ArrayList<ToDoList>();
		arrayList = toDoListRepository.findByUser(u);
		return arrayList;
	}

	@Override
	public void deleteById(long l) {
		toDoListRepository.deleteById(l);		
	}

	@Override
	public void update(ToDoList toDoList) {
		
		try {
			
			ToDoList list = new ToDoList();
			list.setToDoListId(toDoList.getToDoListId());
			list.setName(toDoList.getName());
			list.setLastUpdate(toDoList.getLastUpdate());
			list.setStatus(toDoList.isStatus());
			list.setUser(toDoList.getUser());
			list.setDescription(toDoList.getDescription());
			
			toDoListRepository.save(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
