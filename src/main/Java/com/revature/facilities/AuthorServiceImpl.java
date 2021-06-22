package com.revature.facilities;

import com.revature.beans.Author;
import com.revature.repos.AuthorDAO;
import com.revature.repos.AuthorRepo;
import com.revature.repos.StoryDAO;
import com.revature.repos.StoryRepo;

public class AuthorServiceImpl implements AuthorService {
	private AuthorRepo authDao = new AuthorDAO();

	@Override
	public Author updateAuthor(Author auth) {
		authDao.updateAuthorPoints(auth);
		return authDao.getAuthor(auth.getAuthorId());
	}

	@Override
	public Author getAuthor(String user, String pass) {
		return authDao.getAuthor(user, pass);
	}

	@Override
	public Author getAuthor(Integer id) {
		return authDao.getAuthor(id);
	}

}
