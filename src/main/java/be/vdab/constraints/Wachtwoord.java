package be.vdab.constraints;

import java.lang.annotation.*;

import javax.validation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = WachtwoordValidator.class)
public @interface Wachtwoord {
	String message() default "{be.vdab.constraints.Wachtwoord}";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};
}
