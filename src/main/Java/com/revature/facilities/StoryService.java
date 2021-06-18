package com.revature.facilities;

import java.util.List;

import com.revature.beans.Story;

public interface StoryService {
	List<Story> getStories (Integer authorId); 
	Story getStory (Integer storyId);
	
	void updateStory(Integer storyId);
	void addStory(Story story, Integer authorId);
}
