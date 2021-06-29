package opd.transaction.controller.util;

import opd.transaction.controller.data.LeftMenuOpdDeskDATA;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import opd.OpdConfig;
import opd.transaction.controller.fb.LeftMenuOpdDeskFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.DeskDetailVO;

public class LeftMenuOpdDeskUTIL extends ControllerUTIL
{
	/**
	 * sets all Left Menu Details
	 * 
	 * @param _request -HttpServletRequest
	 */
	// *
	public static void getOpdLeftMenuDetail(LeftMenuOpdDeskFB leftMenuOpdDeskFB, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		//boolean flag = WebUTIL.objectIsAvailableInSession(request, OpdConfig.OPD_LEFT_DESK_MENU_DTL);
		try
		{
			// if(!flag){
			leftMenuOpdDeskFB.setDepartmentUnitCode((String) session.getAttribute(OpdConfig.OPD_DESK_UNIT_CODE));
			DeskDetailVO[] deskDetailVOs = LeftMenuOpdDeskDATA.getOpdLeftMenuDetail(getUserVO(request),
					OpdConfig.OPD_DESK_LOCATION_LEFT_MENU, leftMenuOpdDeskFB.getDepartmentUnitCode(),
					OpdConfig.DESK_TYPE_OPD);
			session.setAttribute(OpdConfig.OPD_LEFT_DESK_MENU_DTL, deskDetailVOs);
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
