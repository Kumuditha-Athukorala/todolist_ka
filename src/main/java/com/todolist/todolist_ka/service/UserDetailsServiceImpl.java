package com.todolist.todolist_ka.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todolist.todolist_ka.model.User;
import com.todolist.todolist_ka.repository.UserRepository;

/**
 * 
 * Service implementation class for User Details 
 * 
 * @author Kumuditha Athukorala
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	
	@Autowired
    private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet < > ();

        grantedAuthorities.add(new SimpleGrantedAuthority("user"));		

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
   
	}
	
	

}
