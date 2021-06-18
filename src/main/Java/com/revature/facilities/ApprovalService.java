package com.revature.facilities;

import com.revature.beans.Approval;

public interface ApprovalService {
	void updateApproval(Integer approvalId);
	void addApproval(Integer approvalId);
	
	Approval getApprovalByStatus(Integer statusId);
	Approval getApproval(Integer approvalId);
}
