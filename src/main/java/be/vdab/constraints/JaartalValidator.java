package be.vdab.constraints;

import javax.validation.*;

public class JaartalValidator implements ConstraintValidator<Jaartal, Integer> {
	private final static int MIN_JAARTAL = 1;
	
	@Override
	public void initialize(Jaartal jaartal) {}

	@Override
	public boolean isValid(Integer jaartal, ConstraintValidatorContext context) {
		if (jaartal == null) {
			return true;
		}
		return jaartal >= MIN_JAARTAL;
	}
}
