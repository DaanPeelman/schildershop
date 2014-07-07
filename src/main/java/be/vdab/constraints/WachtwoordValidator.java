package be.vdab.constraints;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
