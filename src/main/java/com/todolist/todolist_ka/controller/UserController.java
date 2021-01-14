
package com.todolist.todolist_ka.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.todolist.todolist_ka.model.ToDoList;
import com.todolist.todolist_ka.model.User;
import com.todolist.todolist_ka.service.ToDoListService;
import com.todolist.todolist_ka.service.UserService;
import com.todolist.todolist_ka.validator.UserValidator;
/**
 * 
 * User Controller - Main controller class of the todolistka application.
 * 
 * @author Kumuditha Athukorala
 *
 */
@Controller
public class UserController {
	
    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator; 
    
    @Autowired
    private ToDoListService toDoListService;
    
    private User loggedUser;
    
    /*
     * Get mapping end-point controller method for registration. 
     * Create the model of User Registration Form 
     * 
     * */
    @GetMapping("/registration")
    public String registration(Model model) {
    	
        model.addAttribute("userForm", new User());

        return "registration";
    }

    /*
     * Post mapping end-point controller method for registration. 
     * Validate the User Registration data and save the registered user. 
     * 
     * */
    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        
    	userValidator.validate(userForm, bindingResult); // Validates the user registration form inputs and binds the errors.

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        return "redirect:/welcome";
    }

    /*
     * Get mapping end-point controller method for user login validations. 
     * Validate the User Login render the login page. 
     * 
     * */
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
    	
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    /*
     * Get mapping end-point controller method for welcome page. 
     * Captures the logged user and user specific to do list items.   
     * Sets up the model with related components.
     * 
     * */
	@GetMapping({ "/", "/welcome" })
	public String welcome(Model model) {	
		
		loggedUser = userService.findLoggedUser(); // Access the logged user.

		ArrayList<ToDoList> toDoLists = toDoListService.findByUser(loggedUser); // Find logged user specific  to do lists.
				
		model.addAttribute("lists", toDoLists);
		
		model.addAttribute("toDoListForm", new ToDoList());
		
		model.addAttribute("toDoListUpdateForm", new ToDoList());
		
        return "welcome";
    }
    
	/*
     * Post mapping end-point controller method for add to do list item. 
     * Captures the logged user and user.   
     * Sets up to do list item specific fields.
     * 
     * */
	@PostMapping("/addToDoItem")
	public String addToDoItem(@ModelAttribute("toDoListForm") ToDoList toDoListForm) {

		loggedUser = userService.findLoggedUser(); // Access the logged user.

		toDoListForm.setUser(loggedUser);

		toDoListForm.setLastUpdate(getDateTime());

		toDoListService.save(toDoListForm);   // Save a new to do list

		return "redirect:/welcome";
	}
	
	/*
     * Get mapping end-point controller method for delete a to do list item. 
     * Captures the primary key of the specific to list item.
     * 
     * */
	@GetMapping("/deleteItem")
	public String deleteItem(HttpServletRequest servletRequest) {
		
		toDoListService.deleteById(Long.parseLong(servletRequest.getParameter("itemId"))); // Delete the to do list item record.
		
		return "redirect:/welcome";
	}
	
	/*
     * Post mapping end-point controller method for update a to do list item. 
     * Captures the primary key of the specific to list item.
     * 
     * */
	@PostMapping("/updateToDoItem")
	public String updateItem(@ModelAttribute("toDoListUpdateForm") ToDoList toDoListUpdateForm) {		
		
		loggedUser = userService.findLoggedUser();  // Access the logged user.
		
		toDoListUpdateForm.setUser(loggedUser);	
		
		toDoListUpdateForm.setLastUpdate(getDateTime()); // Set the current date and time to the to do list record.
		
		toDoListService.update(toDoListUpdateForm);    // Update the to do list item. 
		
		return "redirect:/welcome";
	}

	/*
     * Method to get the date and time 
     * Returns the date and time as a String value.
     * 
     * */
	public String getDateTime() {
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		
		LocalDateTime currentDateTime = LocalDateTime.now(); 
		
		return dateTimeFormatter.format(currentDateTime);
	}
}
