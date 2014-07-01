package be.vdab.constraints;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailadresValidator implements ConstraintValidator<Emailadres, String> {
	private final static String EMAIL_REGEX = "^[A-Za-z0-9]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	private final static Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

	@Override
	public void initialize(Emailadres emailadres) {
	}

	@Override
	public boolean isValid(String emailadres, ConstraintValidatorContext context) {
		if(emailadres == null) {
			return true;
		}
		
		Matcher matcher = EMAIL_PATTERN.matcher(emailadres);
		return matcher.find();
	}
	
}
