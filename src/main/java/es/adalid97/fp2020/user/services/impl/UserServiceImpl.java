package es.adalid97.fp2020.user.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.adalid97.fp2020.user.exceptions.UserException;
import es.adalid97.fp2020.user.model.User;
import es.adalid97.fp2020.user.repository.UserRepository;
import es.adalid97.fp2020.user.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void addUser(User user) {
		// Si el usuario existe, se lanza una excepción
		if (this.userRepository.userExists(user.getNickName())) {
			throw new UserException("err.user.already.exists");
		}

		this.userRepository.addUser(user);

	}

	@Override
	public void modifyUser(String nickName, User modifiedUser) {
		// Si el NO usuario existe, se lanza una excepción
		if (!this.userRepository.userExists(nickName)) {
			throw new UserException("err.user.not.exists");
		}

		if (siElNickQueSeQuiereModificarEstaEnUso(nickName, modifiedUser)) {
			throw new UserException("err.nick.already.in.use");
		}

		this.userRepository.modifyUser(nickName, modifiedUser);

	}

	private boolean siElNickQueSeQuiereModificarEstaEnUso(String nickName, User modifiedUser) {
		// Si el nickName nuevo no es igual al antiguo y el usuario nuevo existe
		return !nickName.equals(modifiedUser.getNickName())
				&& this.userRepository.userExists(modifiedUser.getNickName());
	}

	@Override
	public void deleteUser(String nickName) {
		this.userRepository.deleteUser(nickName);
	}

	@Override
	public List<User> searchUsersByCountry(String country) {
		return this.userRepository.searchUsersByCountry(country);
	}

	@Override
	public List<User> getAllUsers() {
		return this.userRepository.getAllUsers();
	}

}
