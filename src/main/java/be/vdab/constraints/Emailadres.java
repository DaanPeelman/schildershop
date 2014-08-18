package be.vdab.constraints;

import java.lang.annotation.*;

import javax.validation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailadresValidator.class)
public @interface Emailadres {
	String message() default "{be.vdab.constraints.Emailadres}";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};
}
