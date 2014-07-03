package be.vdab.constraints;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WachtwoordValidator implements ConstraintValidator<Wachtwoord, String> {
	private final static String WACHTWOORD_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$";
	private final static Pattern WACHTWOORD_PATTERN = Pattern.compile(WACHTWOORD_REGEX);
	
	@Override
	public void initialize(Wachtwoord wachtwoord) {
	}

	@Override
	public boolean isValid(String wachtwoord, ConstraintValidatorContext context) {
		if(wachtwoord == null) {
			return true;
		}
		
		
		
		Matcher matcher = WACHTWOORD_PATTERN.matcher(wachtwoord);
		System.out.println(wachtwoord.matches(WACHTWOORD_REGEX));
		return matcher.matches();
	}

}
