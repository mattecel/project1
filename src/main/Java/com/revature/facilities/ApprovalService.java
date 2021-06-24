package com.revature.facilities;

import com.revature.beans.Approval;

public interface ApprovalService {
	Approval updateApproval(Approval app);
	void addApproval (Approval app, Integer statusId);
	
	Approval getApprovalByStatus(Integer statusId);
	Approval getApproval(Integer approvalId);
}
