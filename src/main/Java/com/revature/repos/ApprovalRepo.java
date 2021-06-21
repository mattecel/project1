package com.revature.repos;

import com.revature.beans.Approval;

public interface ApprovalRepo {
	
	void updateApproval(Approval a);
	void addApproval(Approval a, Integer statusId);
	
	Approval getApprovalByStatus(Integer statusId);
	Approval getApproval(Integer approvalId);
}
