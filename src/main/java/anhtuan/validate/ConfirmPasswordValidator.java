package anhtuan.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import anhtuan.model.User;

@Component
public class ConfirmPasswordValidator implements Validator  {


	@Override
	public boolean supports(Class<?> arg0) {
		return User.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;
		// Match password
		if (!user.getConfirmPassword().equals(user.getPassword())) {
			errors.rejectValue("confirmPassword", "Match.user.confirmPassword");
		}

	}

}
