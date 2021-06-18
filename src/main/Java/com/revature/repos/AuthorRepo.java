package com.revature.repos;

import com.revature.beans.Author;

public interface AuthorRepo {
	void addAuthor(Author a);
	void updateAuthor(Integer id);
	
	Author getAuthor(String user, String pass);
	Author getAuthor(Integer id);
}
