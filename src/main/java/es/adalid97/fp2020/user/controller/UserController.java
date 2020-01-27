package es.adalid97.fp2020.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.adalid97.fp2020.user.model.User;
import es.adalid97.fp2020.user.services.UserService;

@RestController
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/users")
	public void addUser(@RequestBody User user) {
		this.userService.addUser(user);
	}

	@PutMapping("/users/{nickName}")
	public void modifyUser(@PathVariable String nickName, @RequestBody User modifyUser) {
		this.userService.modifyUser(nickName, modifyUser);
	}

	@DeleteMapping("/users/{nickName}")
	public void deleteUser(@PathVariable("nickName") String nickName) {
		this.userService.deleteUser(nickName);
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		return this.userService.getAllUsers();
	}

	@GetMapping("/users/{country}")
	public List<User> getUsersCountry(@PathVariable("country") String country) {
		return this.userService.searchUsersByCountry(country);
	}
}
