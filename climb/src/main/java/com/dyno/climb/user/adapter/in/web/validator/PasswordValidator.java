package com.dyno.climb.user.adapter.in.web.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

  private static final String PASSWORD_PATTERN =
      "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,20}$";

  private final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

  @Override
  public void initialize(ValidPassword constraintAnnotation) {}

  @Override
  public boolean isValid(String password, ConstraintValidatorContext context) {
    return password != null && pattern.matcher(password).matches();
  }
}
