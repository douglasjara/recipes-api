package validators;

import play.data.validation.Constraints;
import play.libs.F;

import javax.validation.ConstraintValidator;

public class DifficultyValidator extends Constraints.Validator<String> implements ConstraintValidator<DifficultyOptions, String> {
    @Override
    public void initialize(DifficultyOptions constraintAnnotation) {
    }

    @Override
    public boolean isValid(String difficulty) {
         return difficulty=="easy" || difficulty=="medium" || difficulty=="hard";
    }

    @Override
    public F.Tuple<String, Object[]> getErrorMessageKey() {
        return new F.Tuple<String, Object[]>("Difficulty is not valid", new Object[]{""});
    }
}
