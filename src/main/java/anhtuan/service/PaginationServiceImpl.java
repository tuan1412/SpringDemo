package anhtuan.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anhtuan.dao.UserDao;
import anhtuan.model.User;
import anhtuan.util.Const;

@Service
@Transactional
public class PaginationServiceImpl implements PaginationService {
	@Autowired
	private UserDao userDao;

	@Override
	public long getPageNums() {
		return (long) Math.ceil(userDao.getCount() * 1.0 / Const.PAGE_SIZE);
	}

	@Override
	public List<User> getContentPage(long page) {
		int first = (int) ((page - 1) * Const.PAGE_SIZE);
		int max = Const.PAGE_SIZE;
		return userDao.findAll(first, max);
	}

}
