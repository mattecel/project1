package com.revature.facilities;

import com.revature.beans.Approval;

public interface ApprovalService {
	Approval updateApproval(Approval app);
	
	Approval getApprovalByStatus(Integer statusId);
	Approval getApproval(Integer approvalId);
}
