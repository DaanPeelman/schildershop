package be.vdab.constraints;

import java.math.BigDecimal;

import javax.validation.*;

public class PrijsValidator implements ConstraintValidator<Prijs, BigDecimal> {
	private final static BigDecimal MIN_PRIJS = BigDecimal.ZERO;

	@Override
	public void initialize(Prijs prijs) {}

	@Override
	public boolean isValid(BigDecimal prijs, ConstraintValidatorContext context) {
		if (prijs == null) {
			return true;
		}
		return prijs.compareTo(MIN_PRIJS) >= 0;
	}
}
