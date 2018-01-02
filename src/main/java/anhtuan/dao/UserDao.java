package anhtuan.dao;

import java.util.List;

import anhtuan.model.User;

public interface UserDao {
	User findOne(String username);
	User findOne(Long id);
	void create(User user);
	void update(User user);
	List<User> findAll();
	long getCount();
	List<User> findAll(int fisrt, int max);
	void remove(User user);
}
