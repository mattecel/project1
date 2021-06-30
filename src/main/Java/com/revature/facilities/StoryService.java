package com.revature.facilities;

import java.util.List;

import com.revature.beans.Story;

public interface StoryService {
	List<Story> getStories (Integer authorId);
	Story getStory(Integer storyId);
	void approveStory(Integer storyId, Integer authorPoints);
	
	Story updateStory(Story story);
	Story addStory(Story story, Integer authorId);
}
