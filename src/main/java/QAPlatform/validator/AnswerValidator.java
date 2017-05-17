package QAPlatform.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import QAPlatform.model.Answer;

@Component
public class AnswerValidator implements Validator{
	
    @Override
    public boolean supports(Class<?> aClass) {
        return Answer.class.equals(aClass);
    }
    
    @Override
    public void validate(Object o, Errors errors) {

        Answer answer= (Answer) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "body", "NotEmpty");
        if (answer.getBody().length() < 0) {
            errors.rejectValue("body", "Size.newanswer.body");
        }

        if (answer.getBody().length() > 1000) {
            errors.rejectValue("body", "Size.newanswer.body");
        }

    }
}
