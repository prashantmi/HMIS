package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.Map;

import new_investigation.transactions.bo.InvestigationEssentialBOi;
import new_investigation.transactions.controller.fb.InvValueAuditFB;
import new_investigation.transactions.delegate.InvResultEntryDelegate;
import new_investigation.transactions.delegate.InvValueAuditDelegate;
import new_investigation.vo.InvValueAuditVO;
import new_investigation.vo.template.ResultEntryVO;

public class InvValueAuditDATA {
	

	
	public static Map LabComboForAudit(InvValueAuditFB invInvValueAuditFB, UserVO _UserVO)
	{
		InvValueAuditDelegate masterDelegate = new InvValueAuditDelegate();
		return masterDelegate.LabComboForAudit(invInvValueAuditFB, _UserVO);
	}
	
	public static Map AllTestComboForAudit(InvValueAuditVO vo, UserVO _UserVO)
	{
		InvValueAuditDelegate masterDelegate = new InvValueAuditDelegate();
		return masterDelegate.AllTestComboForAudit(vo, _UserVO);
	}
	
	public static Map TestComboForAudit(InvValueAuditVO vo, UserVO _UserVO)
	{
		InvValueAuditDelegate masterDelegate = new InvValueAuditDelegate();
		return masterDelegate.TestComboForAudit(vo, _UserVO);
	}
	
	public static Map getlistauditprocess(InvValueAuditVO vo, UserVO _UserVO)
	{
		InvValueAuditDelegate masterDelegate = new InvValueAuditDelegate();
		return masterDelegate.getlistauditprocess(vo, _UserVO);
	}
	
	public static Map showPatDetails(InvValueAuditVO vo, UserVO _UserVO,String dNo,String testCode,String labCode)
	{
		InvValueAuditDelegate masterDelegate = new InvValueAuditDelegate();
		return masterDelegate.showPatDetails(vo, _UserVO,dNo,testCode,labCode);
	}

	
}
