package com.todolist.todolist_ka.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.todolist.todolist_ka.model.User;
import com.todolist.todolist_ka.repository.UserRepository;

/**
 * 
 * Service implementation class for User 
 * 
 * @author Kumuditha Athukorala
 *
 */
@Service
public class UserServiceImpl implements UserService{
	
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {
		
		try {
			
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        userRepository.save(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	        
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findLoggedUser() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String userName;
		User user = new User();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
			user = findByUsername(userName);
		}

		return user;
	}

}
