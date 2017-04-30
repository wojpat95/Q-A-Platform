package QAPlatform.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import QAPlatform.model.Question;

@Component
public class QuestionValidator implements Validator{
	
    @Override
    public boolean supports(Class<?> aClass) {
        return Question.class.equals(aClass);
    }
    
    @Override
    public void validate(Object o, Errors errors) {
        Question question = (Question) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "topic", "NotEmpty");        
        if (question.getTopic().length() < 6 || question.getTopic().length() > 200) {
            errors.rejectValue("topic", "Size.newquestion.topic");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "body", "NotEmpty");
        if (question.getBody().length() > 1000) {
            errors.rejectValue("body", "Size.newquestion.body");
        }
    }
}
