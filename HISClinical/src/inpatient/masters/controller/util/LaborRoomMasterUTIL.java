package inpatient.masters.controller.util;

import inpatient.masters.controller.data.LaborRoomMasterDATA;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import inpatient.InpatientConfig;
import inpatient.masters.controller.fb.LaborRoomMasterFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.LaborRoomMasterVO;
import hisglobal.vo.UserVO;

public class LaborRoomMasterUTIL extends ControllerUTIL
{
	public static boolean getEssentails(LaborRoomMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();

		List<Entry> laborRoomMasterList=null;
		try
		{	
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			Map<String,Object> essentialMap = LaborRoomMasterDATA.getLaborRoomMasterEssentails(userVO);
			laborRoomMasterList=(List<Entry>) essentialMap.get(InpatientConfig.INPATIENT_ESSENTIAL_LIST_ALL_CLINICAL_DEPARTMENTS);
			WebUTIL.setAttributeInSession(_request, InpatientConfig.INPATIENT_ESSENTIAL_LIST_ALL_CLINICAL_DEPARTMENTS, laborRoomMasterList);
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
	
	
	public static boolean saveDetail(LaborRoomMasterFB _fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		LaborRoomMasterVO laborRoomMasterVO=new LaborRoomMasterVO();
		boolean flag=false;
		try
		{
			HelperMethods.populate(laborRoomMasterVO, _fb);

			flag=LaborRoomMasterDATA.saveDetail(laborRoomMasterVO,userVO);
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
		return flag;
	}
	
	public static void getModifyDetail(LaborRoomMasterFB _fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		List<Entry> laborRoomMasterList=null;
		
		LaborRoomMasterVO laborRoomMasterVO=new LaborRoomMasterVO();
		try
		{
			Map<String,Object> essentialMap = LaborRoomMasterDATA.getLaborRoomMasterEssentails(userVO);
			laborRoomMasterList=(List<Entry>)essentialMap.get(InpatientConfig.INPATIENT_ESSENTIAL_LIST_ALL_CLINICAL_DEPARTMENTS);
			_fb.setDepartmentCode(laborRoomMasterList.get(0).getValue());

			WebUTIL.setAttributeInSession(request, InpatientConfig.INPATIENT_ESSENTIAL_LIST_ALL_CLINICAL_DEPARTMENTS, laborRoomMasterList);

			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");

			String sLaborRoomID = concatid[0];
			String SLNO = concatid[1];
			laborRoomMasterVO.setLaborRoomId(sLaborRoomID);
			laborRoomMasterVO.setSlNo(SLNO);
			laborRoomMasterVO=LaborRoomMasterDATA.getModifyDetail(laborRoomMasterVO,userVO);
			HelperMethods.populate(_fb, laborRoomMasterVO);
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

	public static boolean saveModifyDetail(LaborRoomMasterFB _fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		boolean flag=false;
		LaborRoomMasterVO laborRoomMasterVO = new LaborRoomMasterVO();
		try
		{	
			
			
			HelperMethods.populate(laborRoomMasterVO, _fb);
			
	
			// Fetching Selected Record Primary Key
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");


			String sLaborRoomID = concatid[0];
			String sSLNO = concatid[1];
			
			laborRoomMasterVO.setLaborRoomId(sLaborRoomID);
			laborRoomMasterVO.setSlNo(sSLNO);
			_fb.setLaborRoomId(sLaborRoomID);

			LaborRoomMasterDATA.saveModifyDetail(laborRoomMasterVO,userVO);
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
