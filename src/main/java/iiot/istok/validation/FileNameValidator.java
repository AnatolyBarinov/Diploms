package iiot.istok.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class FileNameValidator implements ConstraintValidator<ValidFileName, String> {
    private static final Pattern FORBIDDEN_CHARS_PATTERN = Pattern.compile("[/\\\\:*?\"<>|]+");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        if (value == null || value.isBlank()) {
            context.buildConstraintViolationWithTemplate("{filename.empty}")
                    .addConstraintViolation();
            return false;
        }

        if (FORBIDDEN_CHARS_PATTERN.matcher(value).find()) {
            context.buildConstraintViolationWithTemplate("{filename.forbidden-chars}")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
