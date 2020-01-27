package es.adalid97.fp2020.user.services.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.adalid97.fp2020.user.exceptions.UserException;
import es.adalid97.fp2020.user.model.User;
import es.adalid97.fp2020.user.repository.UserRepository;

public class UserServiceImplTest {

	private static final String NICK_NAME = "adalid97";
	private UserServiceImpl userService;
	private final UserRepository userRepository = mock(UserRepository.class);
	private final User modifiedUser = mock(User.class);

	@BeforeEach
	public void inicializarEnCadaTest() {
		this.userService = new UserServiceImpl(userRepository);
	}

	// Ejecucion de test que devuelve una excepcion
	@Test
	public void shouldThrowExceptionModifyUserNotExists() {
		// Training - Entrenamiento
		when(this.userRepository.userExists(NICK_NAME)).thenReturn(false);

		// Validción de resultados
		assertThrows(UserException.class, () -> this.userService.modifyUser(NICK_NAME, modifiedUser));

	}

	// Ejecucion de test que devuelve una excepcion
	@Test
	public void shouldTrueExceptionModifyWithExistingNickName() {
		// Training - Entrenamiento
		when(this.userRepository.userExists(NICK_NAME)).thenReturn(true);
		when(modifiedUser.getNickName()).thenReturn("nuevoNick");
		when(userRepository.userExists("nuevoNick")).thenReturn(true);

		// Validción de resultados
		assertThrows(UserException.class, () -> this.userService.modifyUser(NICK_NAME, modifiedUser));

	}

	// Ejecucion de test que NO devuelve nada
	@Test
	public void shouldModifyUser() {
		// Training - Entrenamiento
		when(this.userRepository.userExists(NICK_NAME)).thenReturn(true);
		when(modifiedUser.getNickName()).thenReturn(NICK_NAME);

		// TEST EXECUTION
		this.userRepository.modifyUser(NICK_NAME, modifiedUser);

		// Validción de resultados
		verify(this.userRepository, times(1)).modifyUser(NICK_NAME, modifiedUser);

	}

	// Ejecucion de test que devuelve una lista
	@Test
	public void ShouldSearchByCountry() {

		// Definicion de variables
		final List<User> usersReturnetByRepository = new ArrayList();

		// Training - Entrenamiento
		when(userRepository.searchUsersByCountry("es")).thenReturn(usersReturnetByRepository);

		// TEST EXECUTION
		final List<User> result = this.userService.searchUsersByCountry("es");

		// Validacion de usuarios
		assertSame(usersReturnetByRepository, result);
		// assertEquals(usersReturnetByRepository, result);

	}

}
