package inpatient.masters.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DeliveryPlaceMasterVO;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.LaborRoomAreaMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.utility.Entry;
import inpatient.masters.controller.data.LaborRoomAreaMstDATA;
import inpatient.masters.controller.fb.LaborRoomAreaMstFB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.master.controller.data.DeskMenuMasterDATA;
import opd.master.controller.fb.DeskMenuMasterFB;

import inpatient.InpatientConfig;

public class LaborRoomAreaMstUTL extends ControllerUTIL {
	
	public static boolean getEssentails(LaborRoomAreaMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List<Entry> laborRoomList=null;
		List<Entry> areaTypeList=null;
		try
		{	
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			Map essentialMap = LaborRoomAreaMstDATA.getLaborRoomAreaMstEssentails(userVO);
			laborRoomList=(List<Entry>)essentialMap.get(InpatientConfig.ESSENTIALBO_LIST_LABOR_ROOM);
			areaTypeList=(List<Entry>)essentialMap.get(InpatientConfig.ESSENTIALBO_LIST_AREA_TYPE);
			
			WebUTIL.setAttributeInSession(_request, InpatientConfig.ESSENTIALBO_LIST_LABOR_ROOM, laborRoomList);
			WebUTIL.setAttributeInSession(_request, InpatientConfig.ESSENTIALBO_LIST_AREA_TYPE, areaTypeList);
			
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
	

	//Saving the Data
	public static void saveLaborRoomAreaMst(LaborRoomAreaMstFB _fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		LaborRoomAreaMasterVO laborRoomAreaMstVO=new LaborRoomAreaMasterVO();
		try
		{
			HelperMethods.populate(laborRoomAreaMstVO, _fb);
			
			LaborRoomAreaMstDATA.saveDetail(laborRoomAreaMstVO,userVO);
			objStatus.add(Status.DONE,"DATA Inserted Successfully","");		
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
	
	public static void getModifyDetail(LaborRoomAreaMstFB _fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		//Map mp=new HashMap();
		List<Entry> laborRoomList=null;
		List<Entry>areaTypeList=null;
				
		LaborRoomAreaMasterVO laborRoomAreaMstVO=new LaborRoomAreaMasterVO();
		try
		{
			//HttpSession session=WebUTIL.getSession(request);
			// Fetching Selected Record Primary Key
			//Map essentialMap = LaborRoomAreaMstDATA.getLaborRoomAreaMstEssentails(userVO);
			//laborRoomList=(List<Entry>)essentialMap.get(InpatientConfig.ESSENTIALBO_LIST_LABOR_ROOM);
			//areaTypeList=(List<Entry>)essentialMap.get(InpatientConfig.ESSENTIALBO_LIST_AREA_TYPE);
			//_fb.setLaborRoomId(laborRoomList.get(0).getValue());
			//WebUTIL.setAttributeInSession(request, InpatientConfig.ESSENTIALBO_LIST_LABOR_ROOM, laborRoomList);
			//_fb.setLaborRoomAreaType(areaTypeList.get(1).getValue());
			//WebUTIL.setAttributeInSession(request, InpatientConfig.ESSENTIALBO_LIST_AREA_TYPE, areaTypeList);
			
			
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");

			String slaborRoomAreaId = concatid[0];
			//String slaborRoomAreaType = concatid[1];
			
			laborRoomAreaMstVO.setLaborRoomAreaId(slaborRoomAreaId);
			_fb.setLaborRoomAreaId(slaborRoomAreaId);
			//laborRoomAreaMstVO.setLaborRoomAreaType(slaborRoomAreaType);
			//_fb.setLaborRoomAreaType(slaborRoomAreaType);
			laborRoomAreaMstVO=LaborRoomAreaMstDATA.getModifyDetail(laborRoomAreaMstVO,userVO);
			
			HelperMethods.populate(_fb, laborRoomAreaMstVO);
			
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
	public static boolean saveModifyDetail(LaborRoomAreaMstFB _fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		boolean flag=false;
		LaborRoomAreaMasterVO laborRoomAreaMstVO=new LaborRoomAreaMasterVO();
		try
		{	
			HelperMethods.populate(laborRoomAreaMstVO, _fb);
			
			LaborRoomAreaMstDATA.saveModifyDetail(laborRoomAreaMstVO,userVO);
			objStatus.add(Status.DONE,"DATA Inserted Successfully","");	
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
