package mortuary.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;

import javax.servlet.http.HttpServletRequest;

import mortuary.transaction.controller.fb.PostmortemLoginFB;

public class PostmortemLoginUTL extends ControllerUTIL
{
	public static void goToPostmortemDesk(PostmortemLoginFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		try
		{
			DynamicDeskUTIL.setAttributeInSession(request, DynamicDeskConfig.DYNAMIC_DESK_TYPE,DynamicDeskConfig.DESK_TYPE_POSTMORTEM_DESK);
			DynamicDeskUTIL.setAttributeInSession(request, DynamicDeskConfig.DYNAMIC_DESK_ROWS_SPAN,"5%,0%,95%,0%,0%");
			objStatus.add(Status.INPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}	
}
