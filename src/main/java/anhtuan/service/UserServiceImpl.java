package anhtuan.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import anhtuan.dao.UserDao;
import anhtuan.model.Role;
import anhtuan.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private Md5PasswordEncoder passwordEncoder;
	
	
	@Override
	public void create(User user) {
		userDao.create(user);
	}
	
	@Override
	public void createMember(User user) {
		user.addRole(new Role("ROLE_MEMBER"));
		user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
		userDao.create(user);
	}

	@Override
	public User findOne(String username) {
		return userDao.findOne(username);
	}

	@Override
	public User findOne(Long id) {
		return userDao.findOne(id);
	}

	@Override
	public void update(User user, String role) {
		User existUser = userDao.findOne(user.getId());
		existUser.setAvatar(user.getAvatar());
		existUser.setName(user.getName());
		existUser.setBirthday(user.getBirthday());
		existUser.setGender(user.getGender());
		existUser.setConfirmPassword(user.getConfirmPassword());
		
		if (role.equals("admin") && !existUser.isAdmin()) {
			existUser.addRole(new Role("ROLE_ADMIN"));
		}
		if (role.equals("member") && existUser.isAdmin()) {
			existUser.removeRole(new Role("ROLE_ADMIN"));
		}
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public void createAdmin(User user) {
		user.addRole(new Role("ROLE_MEMBER"));
		user.addRole(new Role("ROLE_ADMIN"));
		user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
		userDao.create(user);
	}

	@Override
	public void remove(User user) {
		userDao.remove(user);
	}	
}
