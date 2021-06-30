package com.revature.repos;

import com.revature.beans.Author;

public interface AuthorRepo {
	void addAuthor(Author a);
	void updateAuthorPoints(Author a);
	
	Author getAuthor(String user, String pass);
	Author getAuthor(Integer id);
	Author getAuthorByStory(Integer id);
}
