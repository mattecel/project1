package com.revature.facilities;

import com.revature.beans.Author;

public interface AuthorService {

	Author updateAuthor(Author auth);
	
	Author getAuthor(String user, String pass);
	Author getAuthor(Integer id);
}
