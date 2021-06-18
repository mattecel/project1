package com.revature.facilities;

import com.revature.beans.Status;

public interface StatusService {
	void updateStatus(Integer statusId);
	void addStatus(Integer storyId);
	
	Status getStatusByStory(Integer storyId);
	Status getStatus(Integer statusId);
}
