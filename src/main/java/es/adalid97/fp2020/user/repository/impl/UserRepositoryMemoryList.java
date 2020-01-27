package es.adalid97.fp2020.user.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import es.adalid97.fp2020.user.exceptions.UserException;
import es.adalid97.fp2020.user.model.User;
import es.adalid97.fp2020.user.repository.UserRepository;

@Repository
public class UserRepositoryMemoryList implements UserRepository {

	final List<User> userList = new ArrayList();

	@Override
	public void addUser(User user) {

		userList.add(user);

	}

	@Override
	public void modifyUser(String nickName, User modifiedUser) {

		// Utiliza lombok para construir un objeto usuario
		// User.builder().nickName(nickName).build() con la anotación previa @Builders
		final int position = userList.indexOf(User.builder().nickName(nickName).build());

		if (position == -1) {
			throw new UserException("err.user.not.exists");
		}

		userList.set(position, modifiedUser);

	}

	@Override
	public void deleteUser(String nickName) {

		userList.remove(User.builder().nickName(nickName).build());

	}

	@Override
	public List<User> searchUsersByCountry(String country) {

		return userList.stream().filter(user -> user.getCountry().equals(country)).collect(Collectors.toList());

	}

	@Override
	public boolean userExists(String nickName) {

		return userList.contains(User.builder().nickName(nickName).build());

	}

	@Override
	public List<User> getAllUsers() {
		return userList.stream().collect(Collectors.toList());

	}

}
