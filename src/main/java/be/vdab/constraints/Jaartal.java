package be.vdab.constraints;

import java.lang.annotation.*;

import javax.validation.*;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = JaartalValidator.class)
public @interface Jaartal {
	String message() default "{be.vdab.constraints.Jaartal}";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default {};
}
