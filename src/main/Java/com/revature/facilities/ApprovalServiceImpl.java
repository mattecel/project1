package com.revature.facilities;

import com.revature.beans.Approval;
import com.revature.repos.ApprovalDAO;
import com.revature.repos.ApprovalRepo;

public class ApprovalServiceImpl implements ApprovalService {
	private ApprovalRepo appDao = new ApprovalDAO();
	

	@Override
	public Approval updateApproval(Approval app) {
		appDao.updateApproval(app);
		return appDao.getApproval(app.getApprovalId());
	}

	@Override
	public Approval getApprovalByStatus(Integer statusId) {
		return appDao.getApprovalByStatus(statusId);
	}

	@Override
	public Approval getApproval(Integer approvalId) {
		return appDao.getApproval(approvalId);
	}

	@Override
	public void addApproval(Approval app, Integer statusId) {
		appDao.addApproval(app, statusId);
	}

}
