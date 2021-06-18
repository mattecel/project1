package tests;


import org.junit.Test;

import com.revature.repos.AuthorDAO;
import com.revature.repos.AuthorRepo;

public class Testing {

	private AuthorRepo authDao = new AuthorDAO();
	
	@Test
	public void grabTest() {
//		List<Story> stories = new ArrayList<Story>();
//		Story sto = new Story(2, "The Beast", "Who truly is the beast?", "A short story depicting beastial blood fueds", "")
//		Author a = new Author(2, "sol1", "123", "Sol", "Farrell", 80, stories);
		
		System.out.println(authDao.getAuthor("lulu1", "123"));
	}
}
