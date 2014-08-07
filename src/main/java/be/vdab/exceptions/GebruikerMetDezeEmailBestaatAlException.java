package be.vdab.exceptions;

public class GebruikerMetDezeEmailBestaatAlException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public GebruikerMetDezeEmailBestaatAlException() {
		super();
	}

	public GebruikerMetDezeEmailBestaatAlException(String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GebruikerMetDezeEmailBestaatAlException(String message,
			Throwable cause) {
		super(message, cause);
	}

	public GebruikerMetDezeEmailBestaatAlException(String message) {
		super(message);
	}

	public GebruikerMetDezeEmailBestaatAlException(Throwable cause) {
		super(cause);
	}

	
}