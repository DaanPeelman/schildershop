package be.vdab.constraints;

import javax.validation.*;

public class WachtwoordValidator implements ConstraintValidator<Wachtwoord, String> {
	private final static String WACHTWOORD_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$";
	
	@Override
	public void initialize(Wachtwoord wachtwoord) {
	}

	@Override
	public boolean isValid(String wachtwoord, ConstraintValidatorContext context) {
		if(wachtwoord == null) {
			return true;
		}
		
		return wachtwoord.matches(WACHTWOORD_REGEX);
	}

}
