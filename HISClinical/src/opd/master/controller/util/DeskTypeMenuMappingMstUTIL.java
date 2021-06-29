package opd.master.controller.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.DeskTypeMenuMappingMstDATA;
import opd.master.controller.fb.DeskTypeMenuMappingMasterFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.DeskTypeMenuMappingVO;
import hisglobal.vo.UserVO;

public class DeskTypeMenuMappingMstUTIL extends ControllerUTIL 
{
	public static boolean getEssentails(DeskTypeMenuMappingMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		List menuList=null;
		try
		{	
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			if(_fb.getControls()[0]!=null)
			{
				_fb.setDeskType(_fb.getControls()[0]);
			}
			
			DeskTypeMenuMappingVO deskTypeMenuMappingVO=new DeskTypeMenuMappingVO();
			deskTypeMenuMappingVO.setDeskType(_fb.getDeskType());
			
			Map essentialMap = DeskTypeMenuMappingMstDATA.getDeskTypeMenuMappingMstEssentails(deskTypeMenuMappingVO,userVO);
			menuList=(List)essentialMap.get(OpdConfig.ALL_MENU_LIST);
			String deskTypeDesc=(String)essentialMap.get(OpdConfig.DESC_TYPE_DESC);
			_fb.setDeskTypeDesc(deskTypeDesc);
			session.setAttribute(OpdConfig.ALL_MENU_LIST, menuList);
			
			//WebUTIL.setMapInSession(essentialMap, _request);
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
	
	public static boolean saveDetail(DeskTypeMenuMappingMasterFB _fb,HttpServletRequest request)
	{
		boolean saveFlag = true;
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		try
		{
			//HttpSession session=WebUTIL.getSession(request);
			String[] selMenu=_fb.getSelmenuIdLst();
			DeskTypeMenuMappingVO[] _deskTypeMenuMappingVO=new DeskTypeMenuMappingVO[selMenu.length];
			
			for(int i=0;i<_deskTypeMenuMappingVO.length;i++)
			{
				_deskTypeMenuMappingVO[i]=new DeskTypeMenuMappingVO();
				_deskTypeMenuMappingVO[i].setDeskMenuId(selMenu[i]);
				_deskTypeMenuMappingVO[i].setDeskType(_fb.getDeskType());
			}
						
			DeskTypeMenuMappingMstDATA.saveDetail(_deskTypeMenuMappingVO,userVO);
			objStatus.add(Status.DONE,"Record Saved Successfully","");		
		}
		catch(HisRecordNotFoundException e)
		{
			saveFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			saveFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			saveFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			saveFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			saveFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
		return saveFlag;
	}
	
	public static void getModifyDetail(DeskTypeMenuMappingMasterFB _fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		Map essentialMap=new HashMap();
		
		DeskTypeMenuMappingVO deskTypeMenuMappingVO=new DeskTypeMenuMappingVO();
		try
		{
			HttpSession session=WebUTIL.getSession(request);
						
			// Fetching Selected Record Primary Key
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");

			String menuId = concatid[1];
			String deskType=concatid[0];
			_fb.setDeskType(deskType);
			 
			deskTypeMenuMappingVO.setDeskMenuId(menuId);
			deskTypeMenuMappingVO.setDeskType(deskType);
			
			essentialMap=DeskTypeMenuMappingMstDATA.getModifyDetail(deskTypeMenuMappingVO,userVO);
			String deskTypeDesc=(String)essentialMap.get(OpdConfig.DESC_TYPE_DESC);
			_fb.setDeskTypeDesc(deskTypeDesc);
			
			List selMenuLst=(List)essentialMap.get(OpdConfig.SELECTED_MENU_LIST);
			session.setAttribute(OpdConfig.SELECTED_MENU_LIST, selMenuLst);
			
			List remaningMenuLst=(List)essentialMap.get(OpdConfig.REMANING_MENU_LIST);
			session.setAttribute(OpdConfig.REMANING_MENU_LIST, remaningMenuLst);
			
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
	
	public static boolean saveModifyDetail(DeskTypeMenuMappingMasterFB _fb,HttpServletRequest request)
	{
		boolean saveFlag = true;
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		try
		{
			//HttpSession session=WebUTIL.getSession(request);
			
			String[] selMenu=_fb.getSelmenuIdLst();
			DeskTypeMenuMappingVO[] _deskTypeMenuMappingVO=new DeskTypeMenuMappingVO[selMenu.length];
			
			for(int i=0;i<_deskTypeMenuMappingVO.length;i++)
			{
				_deskTypeMenuMappingVO[i]=new DeskTypeMenuMappingVO();
				_deskTypeMenuMappingVO[i].setDeskMenuId(selMenu[i]);
				_deskTypeMenuMappingVO[i].setDeskType(_fb.getDeskType());
			}
			
			DeskTypeMenuMappingMstDATA.saveModifyDetail(_deskTypeMenuMappingVO,userVO);
			objStatus.add(Status.DONE,"Record Modified Successfully","");		
		}
		catch(HisRecordNotFoundException e)
		{
			saveFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			saveFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			saveFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			saveFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			saveFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
		return saveFlag;
	}
}
