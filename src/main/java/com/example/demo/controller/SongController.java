package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entities.Song;
import com.example.demo.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
@Controller
public class SongController {
	
	@Autowired
	SongService service;
	@PostMapping("/addSong")//for transferring
	public String addSong(@ModelAttribute Song song)
	{
		boolean songStatus=service.songExists(song.getName());
		if(songStatus == false)
		{
		service.addSong(song);
		System.out.println("Song added successfully");
		}
		else {
			System.out.println("Song already exists");
		}
		return "adminHome";
	}
	
	@GetMapping("/viewSongs")//not transferring data just getting from db
	public String viewSongs(Model model)
	{
	List<Song>songsList	=service.fetchAllSongs();
      model.addAttribute("songs",songsList);
		return "displaySongs";
	}
	
	@GetMapping("/playSongs")//not transferring data just getting from db
	public String playSongs(Model model)
	{
	 boolean premiumUser=false;	
	  if(premiumUser==true) {
	List<Song>songsList	=service.fetchAllSongs();
		model.addAttribute("songs",songsList);
		return "displaySongs";

       }
	  else {
		  return "makePayment";
	  }
}
}