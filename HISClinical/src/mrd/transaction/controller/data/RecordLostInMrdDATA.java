package mrd.transaction.controller.data;

import java.util.List;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;
import mrd.vo.CaseSheetEnquiryVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.MrdRecordLostFoundDtlVO;
import hisglobal.vo.UserVO;

public class RecordLostInMrdDATA extends ControllerDATA
{
	public static List getListAllRecordType(UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getRecordType(userVO);
	}
	
	public static MrdRecordDtlVO[] searchLostRecord(CaseSheetEnquiryVO caseSheetEnqVO,UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.searchLostRecord(caseSheetEnqVO,userVO);
	}
	
	public static List getLostRecordReportedByList(UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getLostRecordReportedByList(userVO);
	}
	
	public static void saveMrdRecordLostDetail(MrdRecordLostFoundDtlVO mrdRecordLostVO,UserVO userVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		delegate.saveMrdRecordLostDetail(mrdRecordLostVO,userVO);
	}
}
