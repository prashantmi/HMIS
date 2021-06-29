package opd.transaction.controller.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.transaction.controller.data.OpdDeskFooterDATA;
import opd.transaction.controller.fb.OpdDeskFooterFB;
import registration.RegistrationConfig;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;

public class OpdDeskFooterUTIL extends ControllerUTIL
{
	// *
	public static void setInitials(OpdDeskFooterFB opdDeskFooterFB, HttpServletRequest request)
	{

		Status status = new Status();

		HttpSession session = request.getSession();
		try
		{
			String unitCode = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE);
			String roomCode = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ROOM_CODE);
			String patCount = OpdDeskFooterDATA.getPatientEssentials(getUserVO(request), unitCode, roomCode);

			if (patCount != null && !patCount.equals("0"))
			{
				opdDeskFooterFB.setEpisodeIsOpen(RegistrationConfig.EPISODE_ISOPEN_TRUE);
				status.add(Status.INPROCESS);
				// throw new HisRecordNotFoundException();
			}
		}
		catch (HisRecordNotFoundException e)
		{
			status.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, status);
		}
	}
}
