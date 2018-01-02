package anhtuan.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import anhtuan.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public User findOne(String username) {
		String hql = "FROM User u " + "WHERE u.username = :username";

		try {
			return (User) currentSession().createQuery(hql).setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void create(User user) {
		currentSession().persist(user);
	}

	@Override
	public User findOne(Long id) {
		String hql = "FROM User u " + "LEFT JOIN FETCH u.roles " + "WHERE u.id = :id";

		try {
			return (User) currentSession().createQuery(hql).setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void update(User user) {
		currentSession().update(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		String hql = "FROM User u " + "LEFT JOIN FETCH u.roles ";
		return currentSession().createQuery(hql).getResultList();
	}

	@Override
	public long getCount() {
		String hql = "SELECT COUNT (u.id) " + "FROM User u";
		return (long) currentSession().createQuery(hql).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll(int fisrt, int max) {
		String hql = "FROM User u " + "LEFT JOIN FETCH u.roles ";
		return (List<User>) currentSession()
							.createQuery(hql)
							.setFirstResult(fisrt)
							.setMaxResults(max)
							.getResultList();
	}

	@Override
	public void remove(User user) {
		currentSession().remove(user);
		
	}
}
