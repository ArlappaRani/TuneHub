package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Users;
import com.example.demo.repositories.UsersRepository;


@Service
public class UsersServiceImplementation
implements UsersService{
	
	@Autowired
	UsersRepository repo;
	@Override
	public String addUser(Users user)
	{
		repo.save(user);
		return "user added successfully";
	}
	@Override
	public boolean emailExists(String email) {
		// TODO Auto-generated method stub
		if(repo.findByEmail(email)==null)//checking find by email for database stored email and registration form email already existed or not 
		{
			return false;
		}
		else
		{
		return true;
	}
		

}
	@Override
	public boolean validateUser(String email, String password) {
		// TODO Auto-generated method stub
		Users user=repo.findByEmail(email);
		String db_pass=user.getPassword();
		if(password.equals(db_pass))
		{
			return true;
		}
		else {
		return false;
	}
}
	@Override
	public String getRole(String email)
	{
		Users user=repo.findByEmail(email);
		return user.getRole();
	}
	@Override
	public Users getUser(String email) {
		// TODO Auto-generated method stub
		return repo.findByEmail(email);
	}
	
	@Override
	public void updateUser(Users user)
	{
		repo.save(user);
	}
}
