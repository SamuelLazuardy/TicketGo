package com.patterndesign.ticketGo.services;

import java.util.List;
import java.util.Optional;

import com.patterndesign.ticketGo.Domain.User;

public interface UserService {
	
	boolean isUserExist(User user);
	
	User save(User user);
	
	Optional<User> findById(String id);
	
	List<User>listUser();
	
	void deleteUserById(String id);
	
	
}
