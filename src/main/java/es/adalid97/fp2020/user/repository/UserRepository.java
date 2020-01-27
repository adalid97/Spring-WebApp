package es.adalid97.fp2020.user.repository;

import java.util.List;

import es.adalid97.fp2020.user.model.User;

public interface UserRepository {
	public abstract void addUser(User user);

	public abstract void modifyUser(String nickName, User modifiedUser);

	public abstract void deleteUser(String nickName);

	public abstract List<User> searchUsersByCountry(String country);

	public abstract boolean userExists(String nickName);

	public abstract List<User> getAllUsers();

}
