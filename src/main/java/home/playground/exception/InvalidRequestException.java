package home.playground.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException {

	private static final long serialVersionUID = 20190825L;
	
	public InvalidRequestException() {}

	public InvalidRequestException(String msg) {
		super(msg);
	}
}
