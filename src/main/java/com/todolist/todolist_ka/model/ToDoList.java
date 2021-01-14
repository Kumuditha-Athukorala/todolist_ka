package com.todolist.todolist_ka.model;

import javax.persistence.*;

/**
 * 
 * Entity class for the ToDoList table.
 * 
 * @author Kumuditha Athukorala
 *
 */
@Entity
@Table(name = "todolist")
public class ToDoList {	
	
 	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long toDoListId;
    
    private String name;
    
    private String description;
    
    private String lastUpdate;
    
    private boolean status;  
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
	private User user;
    
	
	public long getToDoListId() {
		return toDoListId;
	}

	public void setToDoListId(long toDoListId) {
		this.toDoListId = toDoListId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	 
}
