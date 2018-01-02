package anhtuan.service;

import java.util.List;

import anhtuan.model.User;

public interface UserService {
	void create(User user);
	void createMember(User user);
	List<User> findAll();
	User findOne(Long id);
	User findOne(String username);
	void update(User user, String role);
	void createAdmin(User user);
	void remove(User user);

}