package validators;

import play.data.validation.Constraints;
import play.libs.F;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EstimatedTimeValidator extends Constraints.Validator<String> implements ConstraintValidator<EstimatedTimeFormat, String> {
    @Override
    public void initialize(EstimatedTimeFormat constraintAnnotation) {
    }

    @Override
    public boolean isValid(String estimatedTime) {
         return estimatedTime.matches("((?:(\\d\\d?)h( )?)|(?:(\\d\\d?)min))+");
    }

    @Override
    public F.Tuple<String, Object[]> getErrorMessageKey() {
        return new F.Tuple<String, Object[]>("est_time", new Object[]{"Estimated time is not valid"});
    }
}
