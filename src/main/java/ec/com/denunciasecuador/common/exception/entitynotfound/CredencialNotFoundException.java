package ec.com.denunciasecuador.common.exception.entitynotfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CredencialNotFoundException extends RuntimeException {
	public CredencialNotFoundException(String message) {
		super(message);
	}
}
