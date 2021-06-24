package com.revature.repos;

import java.util.List;

import com.revature.beans.Story;

public interface StoryRepo {
	Story addStory(Story story, Integer id); 
	void updateStory(Story story);
	void updateStoryStatus(Integer statid, Integer storyid);
	
	List<Story> getStories(Integer id);
	Story getStoryById(Integer id);
}