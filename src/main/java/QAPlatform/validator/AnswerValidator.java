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
    	System.out.println("OK");
        Answer answer= (Answer) o;
        System.out.println("OK");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "body", "NotEmpty");
        if (answer.getBody().length() < 0) {
            errors.rejectValue("body", "Size.newanswer.body");
        }
        System.out.println("OK");
        if (answer.getBody().length() > 1000) {
            errors.rejectValue("body", "Size.newanswer.body");
        }
        System.out.println("OK");
    }
}
