package com.todolist.todolist_ka.service;

import com.todolist.todolist_ka.model.User;

/**
 * 
 * Service interface for User 
 * 
 * @author Kumuditha Athukorala
 *
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);
    
    User findLoggedUser();
}
