package opd.transaction.controller.util;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.UserDeskMenuMasterVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.transaction.controller.data.OpdBayDeskDATA;
import opd.transaction.controller.fb.DoctorDeskFB;

public class OpdBayDeskUTIL extends ControllerUTIL {

	public static StringBuffer getOpdPatientCount(DoctorDeskFB objFB, HttpServletRequest request, HttpServletResponse response) 
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		System.out.println("BackgroundQuery.doGet()");
		OpdBayDeskDATA opdBayDeskDATA = new OpdBayDeskDATA();
		HttpSession session = request.getSession();
		UserDeskMenuMasterVO userDeskVO = new UserDeskMenuMasterVO();	
		try
		{
			/*IMP Note Take UNITCODE & ROOMCODE from session*****************&  change madi  &******************/
			String unitCode = (String) request.getParameter("UNITCODE");
			String roomCode = (String) request.getParameter("ROOMCODE");
			userDeskVO.setDeskType(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK);// objFB.getDeskType());
			sbAjaxRes = opdBayDeskDATA.getOpdPatientCount(userDeskVO, ControllerUTIL.getUserVO(request));
			//System.out.println("JSON :-"+patientCountObj);
			//sbAjaxRes.append(patientCountObj);
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
