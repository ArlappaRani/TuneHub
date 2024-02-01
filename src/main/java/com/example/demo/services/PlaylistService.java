package com.example.demo.services;
import com.example.demo.entities.Playlist;
import java.util.List;
public interface PlaylistService {
	
	public void addPlaylist(Playlist playlist);
	
	public List<Playlist> fetchAllplaylists();

}
