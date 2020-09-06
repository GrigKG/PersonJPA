package telran.ashkelon2020.person.dto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)

public class UnauthorizedExeption extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
