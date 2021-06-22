package com.revature.facilities;

import com.revature.beans.Status;
import com.revature.repos.StatusDAO;
import com.revature.repos.StatusRepo;

public class StatusServiceImpl implements StatusService {
	private StatusRepo staDao = new StatusDAO();

	@Override
	public Status updateStatus(Status sta) {
		staDao.updateStatus(sta);
		return staDao.getStatus(sta.getStatusId());
	}

	@Override
	public Status getStatusByStory(Integer storyId) {	
		return staDao.getStatusByStory(storyId);
	}

	@Override
	public Status getStatus(Integer statusId) {
		return staDao.getStatus(statusId);
	}

}
