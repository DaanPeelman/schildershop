package be.vdab.constraints;

import java.lang.annotation.*;

import javax.validation.*;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PrijsValidator.class)
public @interface Prijs {
	String message() default "{be.vdab.constraints.Prijs}";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default {};
}
