package inpatient.transaction.controller.utl;

import inpatient.transaction.controller.data.ANCTeamDetailDATA;
import inpatient.transaction.controller.fb.AncTeamDetailFB;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;

public class ANCTeamDetailUTL extends ControllerUTIL
{
	public static void setEssentials(AncTeamDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		Map essentialMap = new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			
			essentialMap=ANCTeamDetailDATA.getANCTeamDtlEssential(userVO);
			
			WebUTIL.setMapInSession(essentialMap, _request);
			
			objStatus.add(Status.TRANSINPROCESS);
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
			WebUTIL.setStatus(_request, objStatus);
		}
	}
}
