/**
## Copyright Information		: 	C-DAC, Noida  
## Project Name					: 	HIS-NIMS
## Name of Developer		 	: 	Akash Singh
## Module Name					: 	OPD(Ipd Doctor Desk)
## Process/Database Object Name	:	IPD Nursing Desk
## Purpose						:	Data File inititate BO for business Logic 
## Date of Creation				: 	16-December-2014
## Modification Log				:					
##		Modify Date				: 	
##		Reason	(CR/PRS)		: 
##		Modify By				: 
*/
package opd.transaction.controller.data;

import opd.bo.OpdEssentialBO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserVO;

public class IpdNursingDeskDATA extends ControllerDATA{

	public static StringBuffer getIpdNursingPatientCount(UserDeskMenuMasterVO userDeskVO, UserVO userVO) 
	{
		OpdEssentialBO opdEssentialBO = new OpdEssentialBO();
		return opdEssentialBO.getSingleDeptPatientCount(userDeskVO,userVO);
	}

}
