package anhtuan.dao;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import anhtuan.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Role findOne(String name) {
		String hql = "FROM Role r "
					+"WHERE r.name = :name";
		try {
			return (Role) currentSession().createQuery(hql)
						.setParameter("name", name)
						.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}	
	}
}
