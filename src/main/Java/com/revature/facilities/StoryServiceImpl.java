package com.revature.facilities;

import java.util.List;

import com.revature.beans.Story;
import com.revature.repos.StoryDAO;
import com.revature.repos.StoryRepo;

public class StoryServiceImpl implements StoryService {
	private StoryRepo stoDao = new StoryDAO();
	
	@Override
	public List<Story> getStories(Integer authorId) {
		return stoDao.getStories(authorId);
	}

	@Override
	public Story getStory(Integer storyId) {
		return stoDao.getStoryById(storyId);
	}

	@Override
	public Story updateStory(Story story) {
		stoDao.updateStory(story);
		return stoDao.getStoryById(story.getStoryId());
	}

	@Override
	public Story addStory(Story story, Integer authorId) {
		return stoDao.addStory(story, authorId);
	}

}
