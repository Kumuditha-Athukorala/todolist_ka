package com.todolist.todolist_ka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todolist.todolist_ka.model.User;

/**
 * 
 * JPA repository for User 
 * 
 * @author Kumuditha Athukorala
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String username);

}
