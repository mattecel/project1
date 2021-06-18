package com.revature.facilities;

import com.revature.beans.Author;

public interface AuthorService {
	void addAuthor(Author a);
	void updateAuthor(Integer id);
	
	Author getAuthor(String user, String pass);
	Author getAuthor(Integer id);
}
