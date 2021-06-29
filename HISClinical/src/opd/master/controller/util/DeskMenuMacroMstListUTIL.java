package opd.master.controller.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.DeskMenuMacroMstListDATA;
import opd.master.controller.fb.DeskMenuMacroMstListFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.DeskMenuMacroMstVO;

public class DeskMenuMacroMstListUTIL extends ControllerUTIL
{
	
	
	public static void getDeskType(DeskMenuMacroMstListFB _fb,HttpServletRequest _request)
	{
		
		List deskTypeList=DeskMenuMacroMstListDATA.getDeskType(getUserVO(_request));
		WebUTIL.setAttributeInSession(_request,OpdConfig.ESSENTIAL_BO_OPTION_DESK_TYPE_LIST,deskTypeList);
	}
	
	
	public static void getDeskMenuBasedOnDeskType(DeskMenuMacroMstListFB _fb,HttpServletRequest _request)
	{
		
		Status objStatus =new Status();
		
		try
		{
			List lstDeskMenu=DeskMenuMacroMstListDATA.getDeskMenuBasedOnDeskType(_fb.getDeskType(),getUserVO(_request));
			WebUTIL.setAttributeInSession(_request,OpdConfig.ESSENTIALBO_DESK_MENU_BASED_ON_DESK_TYPE,lstDeskMenu);
			objStatus.add(Status.TRANSINPROCESS);
			WebUTIL.setStatus(_request,objStatus);
		}
		catch(HisRecordNotFoundException e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR,"",e.getMessage());			//"No Desk Menu Found"
		}
		catch(HisDataAccessException e)
		{			
			objStatus.add(Status.ERROR_DA,"Data Access Exception","");			
		}
		catch(HisApplicationExecutionException e)
		{			
			objStatus.add(Status.ERROR_AE,"Exception","");
		}		
		catch(Exception e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in UTIL","");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	public static void getMacroHead(DeskMenuMacroMstListFB _fb,HttpServletRequest _request)
	{
		Status objStatus =new Status();
		DeskMenuMacroMstVO[] arrDeskMenuMacroMstVO=null;
		try
		{
			String deskMenuID=_fb.getDeskMenuID();
			String deskType=_fb.getDeskType();
			 arrDeskMenuMacroMstVO=DeskMenuMacroMstListDATA.getMacroHead(deskType,deskMenuID,getUserVO(_request));
		//	WebUTIL.setAttributeInSession(_request, OpdConfig.ARR_DESK_MENU_MACRO_MASTER_VO, arrDeskMenuMacroMstVO);
			objStatus.add(Status.TRANSINPROCESS);
			objStatus.add(Status.LIST);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS);
			objStatus.add(Status.LIST);
			objStatus.add(Status.ERROR,"",e.getMessage());
			
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Record Not Found");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Transaction Failed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		finally
		{
			WebUTIL.setAttributeInSession(_request, OpdConfig.ARR_DESK_MENU_MACRO_MASTER_VO, arrDeskMenuMacroMstVO);
			WebUTIL.setStatus(_request,objStatus);
		}

	}
	
	public static void deleteMacroHead(DeskMenuMacroMstListFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();
		DeskMenuMacroMstVO deskMenuMacroMstVO= new DeskMenuMacroMstVO();
		deskMenuMacroMstVO.setMacroID(_fb.getChk());
		
		try
		{
			DeskMenuMacroMstListDATA.deleteMacroHead(deskMenuMacroMstVO,getUserVO(_request));
			objStatus.add(Status.DONE,"Macro Head deleted Successfully","");
			getMacroHead(_fb, _request);
		}
		catch(HisDataAccessException e)
		{			
			objStatus.add(Status.ERROR_DA,"Data Access Exception","");			
		}
		catch(HisApplicationExecutionException e)
		{			
			objStatus.add(Status.ERROR_AE,"Exception","");
		}		
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in UTIL","");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
}
