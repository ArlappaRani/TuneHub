package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


//Nav controller class is created for mapping purpose
@Controller
public class NavController {
	@GetMapping("/login")//url
	public String login()//method name
	{
		return "login";//page name same
	}
    @GetMapping("/registration")
    public String registration()
    {
    	return "registration";
    }
    
    @GetMapping("/newSong")
    public String newSong()
    {
    	return "newSong";
    }

}
