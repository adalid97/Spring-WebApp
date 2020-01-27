package es.adalid97.fp2020.user.services;

import java.util.List;

import es.adalid97.fp2020.user.model.User;

public interface UserService {
	// Todos los métodos de las interfaces son public y abstract
	public abstract void addUser(User user);

	public abstract void modifyUser(String nickName, User modifyUser);

	public abstract void deleteUser(String nickName);

	public abstract List<User> searchUsersByCountry(String country);

	public abstract List<User> getAllUsers();

}
