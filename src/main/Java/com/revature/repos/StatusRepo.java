package com.revature.repos;

import com.revature.beans.Status;

public interface StatusRepo {
	void updateStatus(Status st);
	Status addStatus(Status st, Integer storyId);
	
	Status getStatusByStory(Integer storyId);
	Status getStatus(Integer statusId);
}
