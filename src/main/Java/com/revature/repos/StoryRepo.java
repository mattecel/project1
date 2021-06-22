package com.revature.repos;

import java.util.List;

import com.revature.beans.Story;

public interface StoryRepo {
	void addStory(Story story, Integer id); // Throw story object as well as author id; 
	void updateStory(Story story);
	void updateStoryStatus(Integer statid, Integer storyid);
	
	List<Story> getStories(Integer id);
	Story getStoryById(Integer id);
}