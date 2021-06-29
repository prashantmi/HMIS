package mrd.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;
import mrd.vo.CaseSheetEnquiryVO;
import mrd.vo.CommonCaseSheetEnquiryVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.RequestRecordDtlVO;
import hisglobal.vo.UserVO;

public class OfflineMrdRecordReqDtlDATA extends ControllerDATA
{
	public static Map getEssentialForOfflineReq(UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getEssentialForOnlineReq(userVO);
	}
	
	
	
	public static String getIcdDtls(UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getIcdDtls(userVO);
	}
	
	public static List getListDischargeType(UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getListDischargeType(userVO);
	}
	
	public static CommonCaseSheetEnquiryVO[] searchRecord(CaseSheetEnquiryVO caseSheetEnqVO,UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.searchRecord(caseSheetEnqVO,userVO);
	}
	
	public static void saveForOfflineRequestDetail(MrdRecordRequestDtlVO mrdRecordRequestVO,List<RequestRecordDtlVO> lstRequestRecordVO,UserVO userVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		delegate.saveForOfflineRequestDetail(mrdRecordRequestVO,lstRequestRecordVO,userVO);
	}
	
	public static Map getMrdNPurposeBasedOnRecordType(String recordType, UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getMrdNPurposeBasedOnRecordType(recordType,userVO);
	}
	
	public static List getRequestedRecordId(String empId,UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getRequestedRecordId(empId,userVO);
	}
	
}
