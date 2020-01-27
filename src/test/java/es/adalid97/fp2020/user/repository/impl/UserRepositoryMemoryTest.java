package es.adalid97.fp2020.user.repository.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.adalid97.fp2020.user.exceptions.UserException;
import es.adalid97.fp2020.user.model.User;

public class UserRepositoryMemoryTest {

//	private final UserRepository userRepository = mock(UserRepository.class);
	private final User modifiedUser = mock(User.class);
	private UserRepositoryMemoryList userRepository;

	private static final String NICK_NAME = "adalid97";
	private static final String NICK_NAME2 = "adalid22";
	private final User user1 = User.builder().nickName(NICK_NAME).build();
	private final User user2 = User.builder().nickName(NICK_NAME2).build();
	private final User user3 = mock(User.class);

	@BeforeEach
	public void inicializarEnCadaTest() {
		this.userRepository = new UserRepositoryMemoryList();

	}

	@Test
	public void comprobarQueModificaUsusarioNoExiste() {

		assertThrows(UserException.class, () -> this.userRepository.modifyUser(NICK_NAME, user1));

	}

	@Test
	public void comprobarQueModificaUsusario() {

		this.userRepository.userList.add(user1);
		this.userRepository.userList.add(user2);

		this.userRepository.modifyUser(NICK_NAME, this.user3);

		assertSame(this.user3, this.userRepository.userList.get(0));

	}

}
