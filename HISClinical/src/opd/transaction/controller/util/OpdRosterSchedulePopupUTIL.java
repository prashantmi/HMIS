package opd.transaction.controller.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.transaction.controller.data.OpdRosterSchedulePopupDATA;
import opd.transaction.controller.fb.OpdRosterSchedulePopupFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

public class OpdRosterSchedulePopupUTIL extends ControllerUTIL
{
	// Getting and Setting Macros
	public static void getSchedule(OpdRosterSchedulePopupFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		try
		{
			UserVO userVO = getUserVO(_request);

			List<Entry> lstScheduleDates = OpdRosterSchedulePopupDATA.getOpdRosterSchedule(_fb.getDepartmentUnitCode(), _fb.getUserId(), _fb.getEntryDate(), userVO);
			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_ROSTER_SCHEDULE_POPUP_LIST_OF_SCHEDULE_DATES, lstScheduleDates);
			if(lstScheduleDates.size()>0)
				objStatus.add(Status.NEW, "", "");
			else
				objStatus.add(Status.NEW, "", "No Schedule Date Found");
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}	
	}
}
