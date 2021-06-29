package opd.master.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DeskMenuMacroMstVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.DeskMenuMacroMstAddDATA;
import opd.master.controller.fb.DeskMenuMacroMstAddFB;

public class DeskMenuMacroMstAddUTIL extends ControllerUTIL
{
	public static void setDesk(DeskMenuMacroMstAddFB _fb,HttpServletRequest _request)
	{
		List lst =(List)_request.getSession().getAttribute(OpdConfig.ESSENTIAL_BO_OPTION_DESK_TYPE_LIST);
		String deskType=_fb.getDeskType();
		String deskTypeName="";
		if(lst!=null){
			for(int i=0;i<lst.size();i++)
			{
			    Entry ent=(Entry)lst.get(i);
			    if( ent.getValue().equalsIgnoreCase(deskType))
			    {
			    	deskTypeName=ent.getLabel();
			    }		
			}
		}
		_fb.setDeskName(deskTypeName);
		
		List list =(List)_request.getSession().getAttribute(OpdConfig.ESSENTIALBO_DESK_MENU_BASED_ON_DESK_TYPE);
		String deskMenu=_fb.getDeskMenuID();
		String deskMenuName="";
		if(lst!=null){
		for(int i=0;i<list.size();i++)
			{
			    Entry ent=(Entry)list.get(i);
			    if( ent.getValue().equalsIgnoreCase(deskMenu))
			    {
			    	deskMenuName=ent.getLabel();
			    }		
			}
		}
		_fb.setDeskMenu(deskMenuName);
	}
	
	public static void setDeptUnitName(DeskMenuMacroMstAddFB _fb,HttpServletRequest _request)
	{
		List listUnit=(ArrayList)_request.getSession().getAttribute(OpdConfig.EssentialBO_LIST_ALL_UNITS);
		ArrayList allUnits=new ArrayList();
		String[] selunits=_fb.getSelectedUnits();
		for(int i=0;i<listUnit.size();i++)
		{
			Entry entobj=(Entry)listUnit.get(i);
			for(int j=0;j<selunits.length;j++ )
			{
				if(selunits[j].equals(entobj.getValue()))
				{
					Entry newobj=new Entry();
					newobj.setValue(entobj.getValue());
					newobj.setLabel(entobj.getLabel());
					allUnits.add(newobj);
				}
			}
		}
		_fb.setSelectedUnitsName(allUnits);
	}
	
	public static void setMacroHeadEssential(DeskMenuMacroMstAddFB _fb,HttpServletRequest _request)
	{
		List allUnit=DeskMenuMacroMstAddDATA.getAllUnit(getUserVO(_request));
		WebUTIL.setAttributeInSession(_request,OpdConfig.EssentialBO_LIST_ALL_UNITS,allUnit );
	}
	
	public static boolean saveMacroHead (DeskMenuMacroMstAddFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		try
		{
			UserVO userVO = getUserVO(_request);
			
			DeskMenuMacroMstVO voDeskMenuMacro;
			int countUnit=_fb.getSelectedUnits().length;
			for(int i=0;i<countUnit;i++)
			{
				voDeskMenuMacro=new DeskMenuMacroMstVO();
				voDeskMenuMacro.setDeptUnitCode(_fb.getSelectedUnits()[i]);
				voDeskMenuMacro.setDeskType(_fb.getDeskType());
				voDeskMenuMacro.setDeskMenuID(_fb.getDeskMenuID());
				voDeskMenuMacro.setMacroHead(_fb.getMacroHead());
				voDeskMenuMacro.setMacroDesc(_fb.getMacroDesc());
				voDeskMenuMacro.setIsValid(Config.IS_VALID_ACTIVE);
				DeskMenuMacroMstAddDATA.saveMacroHead(voDeskMenuMacro,userVO);
			}
			
		}
		catch(HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
		return true;
	}
	
	public static void getMacroHeadForModify(DeskMenuMacroMstAddFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		String macroID=new String(_fb.getChk());
		String deskType=new String(_fb.getDeskType());
		String deskMenuID= new String(_fb.getDeskMenuID());
		UserVO userVO = getUserVO(_request);
		try
		{
			
			DeskMenuMacroMstVO deskMacroVO=DeskMenuMacroMstAddDATA.getMacroHeadForModify(macroID,userVO);
			HelperMethods.populate(_fb,deskMacroVO);
			_fb.setDeskType(deskType);
			_fb.setDeskMenuID(deskMenuID);
			
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	public static void modifySaveMacroHead (DeskMenuMacroMstAddFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		UserVO userVO = getUserVO(_request);
		try
		{
			DeskMenuMacroMstVO _deskMenuMacroMstVO=new DeskMenuMacroMstVO();
			_fb.setMacroID(_fb.getChk());
			HelperMethods.populate(_deskMenuMacroMstVO, _fb);
			DeskMenuMacroMstAddDATA.modifySaveMacroHead(_deskMenuMacroMstVO,userVO);
			objStatus.add(Status.DONE,"Macro Head Modified Successfully","");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
}
