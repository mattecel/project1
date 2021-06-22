package com.revature.facilities;

import com.revature.beans.Status;

public interface StatusService {
	Status updateStatus(Status sta);
	
	Status getStatusByStory(Integer storyId);
	Status getStatus(Integer statusId);
}
