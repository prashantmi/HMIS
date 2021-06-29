package opd.master.controller.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.DeskMenuMasterDATA;
import opd.master.controller.fb.DeskMenuMasterFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.UserVO;

public class DeskMenuMasterUTIL extends ControllerUTIL
{
	public static boolean getEssentails(DeskMenuMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List templateCategoryList=null;
		List deskTypeList=null;
		try
		{	
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			Map essentialMap = DeskMenuMasterDATA.getDeskMenuMasterEssentails(userVO);
			templateCategoryList=(List)essentialMap.get(OpdConfig.ESSENTIALBO_LIST_TEMPLATE_CATEGORY);
			deskTypeList=(List)essentialMap.get(OpdConfig.ESSENTIALBO_LIST_DESK_TYPE);
			
			WebUTIL.setAttributeInSession(_request, OpdConfig.ESSENTIALBO_LIST_TEMPLATE_CATEGORY, templateCategoryList);
			WebUTIL.setAttributeInSession(_request, OpdConfig.ESSENTIALBO_LIST_DESK_TYPE, deskTypeList);
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
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
		}
		return true;
	}
	
	public static void saveDetail(DeskMenuMasterFB _fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		DeskMenuMasterVO deskMenuMasterVO=new DeskMenuMasterVO();
		try
		{
			HelperMethods.populate(deskMenuMasterVO, _fb);
			if(_fb.getIsTemplateBased().equals("0"))
			{
				deskMenuMasterVO.setTemplateCategory("");
			}
			DeskMenuMasterDATA.saveDetail(deskMenuMasterVO,userVO);
			objStatus.add(Status.DONE,"Record Saved Successfully","");		
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void getModifyDetail(DeskMenuMasterFB _fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		//Map mp=new HashMap();
		//List selectedTemplateList=null;
		//List templateList=null;
		
		DeskMenuMasterVO deskMenuMasterVO=new DeskMenuMasterVO();
		try
		{
			//HttpSession session=WebUTIL.getSession(request);
			// Fetching Selected Record Primary Key
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");

			String sDeskMenuID = concatid[0];
			
			deskMenuMasterVO.setDeskMenuId(sDeskMenuID);
			_fb.setDeskMenuId(sDeskMenuID);
			deskMenuMasterVO=DeskMenuMasterDATA.getModifyDetail(deskMenuMasterVO,userVO);
			
			
			HelperMethods.populate(_fb, deskMenuMasterVO);
			
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
		
	}
	
	public static boolean saveModifyDetail(DeskMenuMasterFB _fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		boolean flag=false;
		DeskMenuMasterVO deskMenuMasterVO=new DeskMenuMasterVO();
		try
		{	
			
			
			HelperMethods.populate(deskMenuMasterVO, _fb);
			
			if(_fb.getIsTemplateBased().equals("0"))
			{
				deskMenuMasterVO.setTemplateCategory("");
			}
			
			// Fetching Selected Record Primary Key
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");

			String sDeskMenuID = concatid[0];
			
			deskMenuMasterVO.setDeskMenuId(sDeskMenuID);
			
			DeskMenuMasterDATA.saveModifyDetail(deskMenuMasterVO,userVO);
			objStatus.add(Status.DONE,"Record Saved Successfully","");	
			flag=true;
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
		return flag;
	}

}
