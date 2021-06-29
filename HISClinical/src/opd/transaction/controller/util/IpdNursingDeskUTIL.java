/**
## Copyright Information		: 	C-DAC, Noida  
## Project Name					: 	HIS-NIMS
## Name of Developer		 	: 	Akash Singh
## Module Name					: 	OPD(Ipd Doctor Desk)
## Process/Database Object Name	:	IPD Nursing Desk
## Purpose						:	Util File for taking FB Value to userDeskVO
## Date of Creation				: 	16-December-2014
## Modification Log				:					
##		Modify Date				: 	
##		Reason	(CR/PRS)		: 
##		Modify By				: 
*/
package opd.transaction.controller.util;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.UserDeskMenuMasterVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.data.IpdNursingDeskDATA;
import opd.transaction.controller.fb.DoctorDeskFB;

public class IpdNursingDeskUTIL extends ControllerUTIL{

	public static StringBuffer getIpdNursingPatientCount(DoctorDeskFB objFB,HttpServletRequest request, HttpServletResponse objResponse_p) 
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		UserDeskMenuMasterVO userDeskVO = new UserDeskMenuMasterVO();	
		try
		{
			userDeskVO.setDeskType(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK);// objFB.getDeskType());
			System.out.println("desk Type is :- "+userDeskVO.getDeskType());
			sbAjaxRes = IpdNursingDeskDATA.getIpdNursingPatientCount(userDeskVO, ControllerUTIL.getUserVO(request));
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return sbAjaxRes;
	}

}
