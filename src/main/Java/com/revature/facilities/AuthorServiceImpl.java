package com.revature.facilities;

import com.revature.beans.Author;
import com.revature.repos.AuthorDAO;
import com.revature.repos.AuthorRepo;

public class AuthorServiceImpl implements AuthorService {
	private AuthorRepo authDao = new AuthorDAO();

	@Override
	public void addAuthor(Author a) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAuthor(Integer id) {
		// TODO Auto-generated method stub

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
