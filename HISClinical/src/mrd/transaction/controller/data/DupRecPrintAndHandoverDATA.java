/* 
## Copyright Information		: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Anant Patel 
 ## Module Name					: MRD
 ## Process/Database Object Name: Duplicate Record Printing And Handover process
 ## Purpose						: Duplicate Record Printing And Handover process
 ## Date of Creation			: 19 Jan 2015
 ## Modification Log			:				
 ##		Modify Date				:  
 ##		Reason	(CR/PRS)		:  
 ##		Modify By				: 

*/

package mrd.transaction.controller.data;

import java.util.Map;

import mrd.transaction.bo.MrdEssentialBO;
import mrd.vo.DupRecPrintReqVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

public class DupRecPrintAndHandoverDATA extends ControllerDATA {

	public static Map getDupRecPrintAndHandoverDtl(UserVO userVO)
	{
		MrdEssentialBO serviceBO=new MrdEssentialBO();	
		return serviceBO.getDupRecPrintAndHandoverDtl(userVO);
	}
	
	public static Map getDuplicateRecordHandoverDtl(mrd.vo.DupRecPrintReqVO dupRecPrintReqVO, UserVO UserVO)
	{
		MrdEssentialBO serviceBO=new MrdEssentialBO();	
		return (serviceBO.getDuplicateRecordHandoverDtl(dupRecPrintReqVO,UserVO));
	}
	
	public static String getBillNoDtl(DupRecPrintReqVO dupRecPrintReqVO, UserVO UserVO)
	{
		MrdEssentialBO serviceBO=new MrdEssentialBO();	
		return (serviceBO.getBillNoDtl(dupRecPrintReqVO,UserVO));
	}
	
	
	public static void saveDuplicateRecordPrintHAndover(DupRecPrintReqVO dupRecPrintReqVO , UserVO UserVO)
	{
		MrdEssentialBO serviceBO=new MrdEssentialBO();	
		serviceBO.saveDuplicateRecordHandoverDtl(dupRecPrintReqVO,UserVO);
	}

}






