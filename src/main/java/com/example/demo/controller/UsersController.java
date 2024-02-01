package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Song;
import com.example.demo.entities.Users;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.services.SongService;
import com.example.demo.services.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.*;

@Controller
public class UsersController {
	@Autowired
	UsersService service;
	@Autowired
	SongService songservice;
	
	@PostMapping("/register")
	public String addUsers(@ModelAttribute Users user) {
		boolean userStatus=service.emailExists(user.getEmail());
		if(userStatus==false)
		{
		service.addUser(user);
		System.out.println("User added");
		}
		else
		{
			System.out.println("User is already added");
		}
		return "home";
	}
	@PostMapping("/validate")
	public String validate(@RequestParam("email")String email,
			@RequestParam("password")String password,HttpSession session,Model model)
	{
		if(service.validateUser(email,password)==true) {
			String role=service.getRole(email);
			
			session.setAttribute("email",email);
			if(role.equals("admin"))//user is not valid redirected to login page
				{
			return "adminHome";
		}
			else {
				Users user=service.getUser(email);
				boolean userStatus=user.isPremium();
				List<Song>songsList	=songservice.fetchAllSongs();
			      model.addAttribute("songs",songsList);
				
				model.addAttribute("isPremium",userStatus);
				return "customerHome";
			}
		}	
		else
		{
			return "login";
		}
	}
	/*@GetMapping("/pay")
	public String pay(@RequestParam String email)
	{
		boolean paymentStatus=false;//payment api
		if(paymentStatus==true) {
		Users user=service.getUser(email);
		user.setPremium(true);
		service.updateUser(user);
		}
		return "login";
	}*/
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "login";
	}
}
