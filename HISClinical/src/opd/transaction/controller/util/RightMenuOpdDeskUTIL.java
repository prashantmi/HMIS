package opd.transaction.controller.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.RightMenuOpdDeskDATA;
import opd.transaction.controller.fb.RightMenuOpdDeskFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.DeskDetailVO;

public class RightMenuOpdDeskUTIL extends ControllerUTIL
{
	/**
	 * sets all Right Menu Details
	 * @param _request -HttpServletRequest
	 */
	// *
	public static void getOpdRightMenuDetail(RightMenuOpdDeskFB rightMenuOpdDeskFB, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		//boolean flag = WebUTIL.objectIsAvailableInSession(request, OpdConfig.OPD_RIGHT_DESK_MENU_DTL);
		try
		{
			// if(!flag){
			rightMenuOpdDeskFB.setDepartmentUnitCode((String) session.getAttribute(OpdConfig.OPD_DESK_UNIT_CODE));
			DeskDetailVO[] deskDetailVOs = RightMenuOpdDeskDATA.getOpdRightMenuDetail(getUserVO(request),
					OpdConfig.OPD_DESK_LOCATION_RIGHT_MENU, rightMenuOpdDeskFB.getDepartmentUnitCode(),
					OpdConfig.DESK_TYPE_OPD);
			session.setAttribute(OpdConfig.OPD_RIGHT_DESK_MENU_DTL, deskDetailVOs);
			// }
			// else{
			// System.out.println("");
			// session.getAttribute(OpdConfig.OPD_RIGHT_DESK_MENU_DTL);
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
