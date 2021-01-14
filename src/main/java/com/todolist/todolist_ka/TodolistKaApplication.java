package com.todolist.todolist_ka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * Main class of the application.
 * 
 * @author Kumuditha Athukorala
 *
 */
@SpringBootApplication
@ComponentScan("com.todolist.todolist_ka")
public class TodolistKaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistKaApplication.class, args);
	}

}
