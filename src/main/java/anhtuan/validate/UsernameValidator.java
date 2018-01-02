package anhtuan.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import anhtuan.model.User;
import anhtuan.service.UserService;

@Component
public class UsernameValidator implements Validator {
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> arg0) {
		return User.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;
		
		// Unique email
		User dbUser = userService.findOne(user.getUsername());
		if (dbUser != null && dbUser.getId() != user.getId()) {
			errors.rejectValue("username", "Unique.user.username");
		}
	}

}
