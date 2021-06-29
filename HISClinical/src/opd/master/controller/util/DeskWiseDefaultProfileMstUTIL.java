package opd.master.controller.util;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.DeskWiseDefaultProfileMstDATA;
import opd.master.controller.fb.DeskWiseDefaultProfileMstFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.DeskWiseDefaultProfileMstVO;
import hisglobal.vo.UserVO;

public class DeskWiseDefaultProfileMstUTIL extends ControllerUTIL
{
	public static void getDeskType(DeskWiseDefaultProfileMstFB _fb, HttpServletRequest _request)
	 {
		Status objStatus = new Status();
		//Map mp = new HashMap();
		List deskTypeLst = new ArrayList();
		//DeskWiseDefaultProfileMstVO _DeskWiseDefaultProfileMstVO = new DeskWiseDefaultProfileMstVO();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			deskTypeLst= DeskWiseDefaultProfileMstDATA.getDeskType(userVO);
				
			
			WebUTIL.setAttributeInSession(_request, OpdConfig.DESK_TYPE_LIST, deskTypeLst);
						
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "e.printStackTrace()", "");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	}
	
	public static void getDeskName(DeskWiseDefaultProfileMstFB _fb, HttpServletRequest _request)
	 {
		Status objStatus = new Status();
		//Map mp = new HashMap();
		List deskNameLst = new ArrayList();
		//DeskWiseDefaultProfileMstVO _DeskWiseDefaultProfileMstVO = new DeskWiseDefaultProfileMstVO();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			String deskTypeId=_fb.getDeskTypeId();
			deskNameLst= DeskWiseDefaultProfileMstDATA.getDeskName(deskTypeId,userVO);
				
			
			WebUTIL.setAttributeInSession(_request, OpdConfig.DESK_NAME_LIST, deskNameLst);
						
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "e.printStackTrace()", "");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	}

	public static void getMenuName(DeskWiseDefaultProfileMstFB _fb, HttpServletRequest _request)
	 {
		Status objStatus = new Status();
		//Map mp = new HashMap();
		List defaultValueLst = new ArrayList();
		List NondefaultmenuNameLst = new ArrayList();
		List allMenuList=new ArrayList();
		List defaultMenuList=new ArrayList();
				
		//DeskWiseDefaultProfileMstVO _DeskWiseDefaultProfileMstVO = new DeskWiseDefaultProfileMstVO();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			String deskId=_fb.getDeskId();
			
			defaultValueLst= DeskWiseDefaultProfileMstDATA.getMenuName(deskId,userVO);
			defaultMenuList = DeskWiseDefaultProfileMstDATA.getDefaultMenuName(deskId,userVO);
			if(defaultValueLst.size() > defaultMenuList.size())
			{
				allMenuList=DeskWiseDefaultProfileMstDATA.getAllMenuName(deskId,userVO);
				NondefaultmenuNameLst= DeskWiseDefaultProfileMstDATA.getNonDefaultMenuName(deskId,userVO);
				WebUTIL.setAttributeInSession(_request, OpdConfig.DEFAULT_MENU_NAME_LIST, allMenuList);
			}
			else
			{
				NondefaultmenuNameLst= DeskWiseDefaultProfileMstDATA.getNonDefaultMenuName(deskId,userVO);
			
				WebUTIL.setAttributeInSession(_request, OpdConfig.DEFAULT_MENU_NAME_LIST, defaultMenuList);
				WebUTIL.setAttributeInSession(_request, OpdConfig.NON_DEFAULT_MENU_NAME_LIST, NondefaultmenuNameLst);
			}
		
			
			
						
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "e.printStackTrace()", "");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	}

	

	public static boolean saveDefaultProfileDetails(DeskWiseDefaultProfileMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			DeskWiseDefaultProfileMstVO _DeskWiseDefaultProfileMstVO = new DeskWiseDefaultProfileMstVO();
			
			_DeskWiseDefaultProfileMstVO.setDeskTypeId(_fb.getDeskTypeId());
			_DeskWiseDefaultProfileMstVO.setDeskId(_fb.getDeskId());
			
			if(_fb.getMenuListDefault()!=null)
			{
				_DeskWiseDefaultProfileMstVO.setMenuListDefault(_fb.getMenuListDefault());
			
			}
			if(_fb.getMenuListNonDefault()!=null)
			{
				_DeskWiseDefaultProfileMstVO.setMenuListNonDefault(_fb.getMenuListNonDefault());
			
			}
			hasFlag=DeskWiseDefaultProfileMstDATA.saveDefaultProfileDetails(_DeskWiseDefaultProfileMstVO, userVO);
			
			if(hasFlag)
			 {
				 objStatus.add(Status.INPROCESS, "Record Updated Successfully", "");
			 }else
			 {		System.out.println("in false");
				 objStatus.add(Status.NEW, "Record Already Exists", "");
			 }

		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
			hasFlag = false;
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			hasFlag = false;

		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			hasFlag = false;
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
			hasFlag = false;
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}
}
