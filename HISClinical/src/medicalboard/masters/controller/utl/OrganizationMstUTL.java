package medicalboard.masters.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRegistrationTimingExpiredException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.MbOrganizationMstVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import medicalboard.MedicalBoardConfig;
import medicalboard.masters.controller.data.OrganizationMstDATA;
import medicalboard.masters.controller.fb.OrganizationMstFB;



public class OrganizationMstUTL extends ControllerUTIL{

	public static void getEssential(HttpServletRequest _request){	
		
		Status objStatus=new Status();
		Map mp=new HashMap();
		List orgTypeList=new ArrayList();
		
		try{
//		UserVO userVO=getUserVO(_request);
		setSysdate(_request);
		
		for(int i=1;i<MedicalBoardConfig.MEDICAL_BOARD_ORGANIZATION_TYPES.length;i++)
		{
			Entry entry=new Entry();
			entry.setLabel(MedicalBoardConfig.MEDICAL_BOARD_ORGANIZATION_TYPES[i]);
			entry.setValue(String.valueOf(i));
			orgTypeList.add(entry);
		}
		
		WebUTIL.setAttributeInSession(_request,MedicalBoardConfig.MEDICALBOARD_ORGANIZATIONTYPES ,orgTypeList);
	
		   WebUTIL.setMapInSession(mp,_request);
		
		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			e.printStackTrace();
		}		
		catch(HisRegistrationTimingExpiredException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisDataAccessException e){
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "","Application Execution Error");
			e.printStackTrace();
		}
		catch(HisException e){
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		    System.out.println("objStatus in finally"+objStatus);		 
		    System.out.println("objStatus list"+objStatus.getStatusList());
		}	
	}

	
	
	public static boolean saveOrganizationDtl(OrganizationMstFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		MbOrganizationMstVO mbOrganizationMstVO=new MbOrganizationMstVO();
    	 boolean hasFlag=true;
		try
		{
			HelperMethods.populate(mbOrganizationMstVO, fb);
			
			OrganizationMstDATA.saveOrganizationDtl(mbOrganizationMstVO,getUserVO(request));
			
			objStatus.add(Status.DONE,"","Record Added Successfully");
		}
		catch (HisDuplicateRecordException e)
		{   hasFlag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{   hasFlag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{   hasFlag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{   hasFlag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e)
		{  hasFlag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return hasFlag;
	}
	
	
	public static boolean getOrganizationDtl(OrganizationMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		MbOrganizationMstVO mbOrganizationMstVO = new MbOrganizationMstVO();
//		Map mp = new HashMap();
//		String str = new String();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sOrganizationId = concatid[0];
			String sSlno = concatid[1];
			// putting the selected Record Primary Key into Vo

			fb.setOrgID(sOrganizationId);
			fb.setSlNo(sSlno);

			mbOrganizationMstVO.setSlNo(sSlno);
			mbOrganizationMstVO.setOrgID(sOrganizationId);
			
			mbOrganizationMstVO = OrganizationMstDATA.getOrganizationDtl(mbOrganizationMstVO, userVO);
		    
			HelperMethods.populate(fb,mbOrganizationMstVO);
			
			//objStatus.add(Status.NEW);
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
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return true;
	}
	
	public static boolean saveModOrganizationDtl(OrganizationMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=true;
		try
		{
			
			UserVO userVO = getUserVO(_request);
			MbOrganizationMstVO mbOrganizationMstVO = new MbOrganizationMstVO();
			HelperMethods.populate( mbOrganizationMstVO , fb);
		
			OrganizationMstDATA.saveModOrganizationDtl(mbOrganizationMstVO,userVO);
			objStatus.add(Status.DONE,"","record modified successfully");
		}
		catch (HisDuplicateRecordException e)
		{  flag=false;
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisRecordNotFoundException e)
		{  flag=false;
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{   flag=false;
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{   flag=false;
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{   flag=false;
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Abortion Method already exists");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return flag;
	}

	
	

	
	
	
}
