//created by swati sagar on date :20-02-109
package mrd.transaction.controller.data;


import java.util.List;
import java.util.Map;

import mrd.transaction.bo.MrdBO;
import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;
import mrd.vo.CaseSheetEnquiryVO;
import mrd.vo.CommonCaseSheetEnquiryVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MrdIcdDtlVO;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.RequestRecordDtlVO;
import hisglobal.vo.UserVO;

public class IcdIndexingDtlDATA extends ControllerDATA
{
	
	
	public static Map getEssentialForIcdIndexing(UserVO userVO,PatientDetailVO patdtlVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getEssentialForIcdIndexing(userVO,patdtlVO);
	}
	
	public static void saveIcdIndexDetail(List<MrdIcdDtlVO> lstmrdVo,UserVO userVO,PatientDetailVO patDtlVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		delegate.saveIcdIndexDetail(lstmrdVo,userVO,patDtlVO);
	}
	
	//Added by Vasu on 07.March.2019

	public static void deleteIcdIndexDetail(MrdIcdDtlVO lcdMrdVo, UserVO userVO) {
		MrdBO essentialBO = new MrdBO();
		essentialBO.deleteIcdIndexDetail(lcdMrdVo,userVO);
	}
	

	
}
