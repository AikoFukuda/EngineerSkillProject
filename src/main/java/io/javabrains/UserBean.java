package io.javabrains;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import io.javabrains.UserRepository;

public class UserBean {
	@Autowired
	UserRepository userRepository;
	
	public String getTableTagById(Long id) {
		Optional<Users> opt = userRepository.findById(id);
		Users users = opt.get();
		String result = "<tr><td>" + users.getName() + "</td><td>" + users.getEmail() + "</td></tr>";
		return result;
	}
}
