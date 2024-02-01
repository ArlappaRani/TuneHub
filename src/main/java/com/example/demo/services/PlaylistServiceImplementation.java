package com.example.demo.services;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Playlist;
import com.example.demo.repositories.PlaylistRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
@Service
public class PlaylistServiceImplementation 
        
implements PlaylistService{
	
	@Autowired
	PlaylistRepository repo;
	
   @Override
   public void addPlaylist(Playlist playlist)
   {
	   repo.save(playlist);
   }

@Override
public List<Playlist> fetchAllplaylists() {
	// TODO Auto-generated method stub
	return repo.findAll();
}
}
