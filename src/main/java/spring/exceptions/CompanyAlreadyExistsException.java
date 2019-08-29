package spring.exceptions;

public class CompanyAlreadyExistsException extends Exception {
	
	public CompanyAlreadyExistsException (String msg) {
		super(msg);
	}
}
