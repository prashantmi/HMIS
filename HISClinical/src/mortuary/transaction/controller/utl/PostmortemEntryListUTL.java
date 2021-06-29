package mortuary.transaction.controller.utl;

import javax.servlet.http.HttpServletRequest;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.PostmortemEntryListDATA;
import mortuary.transaction.controller.fb.PostmortemEntryListFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;
import hisglobal.vo.DeceasedDetailVO;

public class PostmortemEntryListUTL extends ControllerUTIL
{
	public static void getDeceasedListForPostmortemEntry(PostmortemEntryListFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		DeceasedDetailVO[] decesedList=null;
		
		try
		{
			decesedList=PostmortemEntryListDATA.getDeceasedListForPostmortemEntry(getUserVO(request));
			DynamicDeskUTIL.setAttributeInSession(request, DynamicDeskConfig.POSTMORTEM_DESK_DECEASED_LIST, decesedList);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
}
