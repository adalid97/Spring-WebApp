package es.adalid97.fp2020.user.exceptions;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = -4655742215994508456L;

	public UserException(String errorMessage) {
		super(errorMessage);
	}

}
