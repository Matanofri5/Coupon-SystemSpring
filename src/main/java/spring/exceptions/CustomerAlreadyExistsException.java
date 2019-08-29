package spring.exceptions;

public class CustomerAlreadyExistsException extends Exception {

	public CustomerAlreadyExistsException(String msg) {
		super(msg);
	}
}
