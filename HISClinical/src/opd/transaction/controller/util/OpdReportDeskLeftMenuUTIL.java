package opd.transaction.controller.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import opd.OpdConfig;
import opd.transaction.controller.fb.OpdReportLeftMenuFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DeskDetailVO;

public class OpdReportDeskLeftMenuUTIL extends ControllerUTIL
{
	public static void getOpdReportDeskLeftMenuDetail(OpdReportLeftMenuFB opdReportLeftMenuFB, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		//boolean flag = WebUTIL.objectIsAvailableInSession(request, OpdConfig.OPD_REPORT_DESK_LEFT_MENU_DTL);

		List<DeskDetailVO> lstBottomMenus = new ArrayList<DeskDetailVO>();
		try
		{
			// if(!flag){
			// opdReportLeftMenuFB.setDepartmentUnitCode((String)session.getAttribute(OpdConfig.OPD_DESK_UNIT_CODE));
			opdReportLeftMenuFB.setDepartmentUnitCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE));
			lstBottomMenus = (ArrayList<DeskDetailVO>)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_BOTTOM_MENU_DTL);
			DeskDetailVO[] deskDetailVOs = new DeskDetailVO[0];
			//deskDetailVOs = OpdReportDeskLeftMenuDATA.getOpdReportDeskLeftMenuDetail(getUserVO(request),OpdConfig.DESK_LOCATION_BOTTOM, opdReportLeftMenuFB.getDepartmentUnitCode(), DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK);
			if(lstBottomMenus!=null && lstBottomMenus.size()>0)
				deskDetailVOs = lstBottomMenus.toArray(deskDetailVOs);
			else
				throw new HisRecordNotFoundException("No Report Found");
			session.setAttribute(OpdConfig.OPD_REPORT_DESK_LEFT_MENU_DTL, deskDetailVOs);
			// }
			// else{
			// System.out.println("");
			// session.getAttribute(OpdConfig.OPD_LEFT_DESK_MENU_DTL);
			// }
			objStatus.add(Status.INPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}
}
