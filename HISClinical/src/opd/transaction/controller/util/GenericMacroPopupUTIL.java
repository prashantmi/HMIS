package opd.transaction.controller.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.transaction.controller.data.GenericMacroPopupDATA;
import opd.transaction.controller.fb.GenericMacroPopupFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

public class GenericMacroPopupUTIL extends ControllerUTIL
{
	// Getting and Setting Macros
	public static void getSetMacros(GenericMacroPopupFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		try
		{
			UserVO userVO = getUserVO(_request);

			List<Entry> lstMacros = GenericMacroPopupDATA.getMacrosByProcessId(_fb.getMacroProcessId(), userVO);
			WebUTIL.setAttributeInSession(_request, OpdConfig.GENERIC_MACRO_POPUP_LIST_BY_PROCESS_ID, lstMacros);
			if(lstMacros.size()>0)
				objStatus.add(Status.NEW, "", "");
			else
				objStatus.add(Status.NEW, "", "No Macros Found");
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

	// Getting and Setting Macros Unit Wise
	public static void getSetUnitMacros(GenericMacroPopupFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		try
		{
			UserVO userVO = getUserVO(_request);

			List<Entry> lstMacros = GenericMacroPopupDATA.getUnitMacrosByProcessId(_fb.getMacroProcessId(),_fb.getUnitCode(), userVO);
			WebUTIL.setAttributeInSession(_request, OpdConfig.GENERIC_MACRO_POPUP_LIST_BY_PROCESS_ID, lstMacros);
			if(lstMacros.size()>0)
				objStatus.add(Status.NEW, "", "");
			else
				objStatus.add(Status.NEW, "", "No Macros Found");
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
