package com.zhang.web;

import java.io.FileNotFoundException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
	
	
	@GetMapping("/book/error1")
	public String error1() throws FileNotFoundException {
		throw new FileNotFoundException("book.txt not found");
	}
	
	@GetMapping("/book/error2")
	public String error2() throws ClassNotFoundException {
		throw new ClassNotFoundException("Book class not found");
	}
}
