package anhtuan.service;

import java.util.List;

import anhtuan.model.User;

public interface PaginationService {
	long getPageNums();
	List<User> getContentPage(long page);
}
