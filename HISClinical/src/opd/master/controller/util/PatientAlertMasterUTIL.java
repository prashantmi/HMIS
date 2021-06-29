package opd.master.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisInsertNotAllowedException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.PatientAlertMasterVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.master.controller.data.PatientAlertMasterDATA;
import opd.master.controller.fb.PatientAlertMasterFB;

public class PatientAlertMasterUTIL extends ControllerUTIL{
	
	public static void getPatientAlertEssentials(PatientAlertMasterFB _fb,HttpServletRequest _rq)
	{
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		try{
				UserVO userVO= getUserVO( _rq);
				setSysdate(_rq);
				essentialMap = PatientAlertMasterDATA.getPatientAlertEssentials(userVO);
				WebUTIL.setMapInSession(essentialMap, _rq);
				objStatus.add(Status.NEW,"","");
			}
		catch(HisRecordNotFoundException e){	
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());		
			
		}
		catch(HisInsertNotAllowedException e){	
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());		
			
		}
		catch(HisDataAccessException e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Data Access Exception","");			
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"Exception","");
			}		
		catch(Exception e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in ProfileAccessPolicyUTIL","");
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
		}
	}
	public static boolean savePatientAlert(PatientAlertMasterFB _fb,HttpServletRequest _rq)
	{
		boolean flag=false;
		Status objStatus=new Status();
		PatientAlertMasterVO patientAlertMasterVO= new PatientAlertMasterVO();
		try
		{
				UserVO userVO= getUserVO( _rq);
				HelperMethods.populate(patientAlertMasterVO, _fb);
				PatientAlertMasterDATA.savePatientAlert(patientAlertMasterVO,userVO);
				flag=true;
				objStatus.add(Status.DONE,"","Record Saved Successfully");
			}
			catch(HisDuplicateRecordException e){	   		   	
				 System.out.println("Inside HisDuplicateRecordException");
		  		 e.printStackTrace(); 
		  		 flag=false;
		  		 objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
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
				WebUTIL.setStatus(_rq, objStatus);
				System.out.println("   -----> objStatus in finally  : " + objStatus);
				System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
			}
		return flag;
	}
	public static void fetchPatientAlertModify(PatientAlertMasterFB _fb,HttpServletRequest _rq)
	{
			Status objStatus = new Status();
			UserVO userVO= getUserVO( _rq);
			Map essentialMap=new HashMap();
			PatientAlertMasterVO patientAlertMasterVO=new PatientAlertMasterVO();
			try
			{
				essentialMap = PatientAlertMasterDATA.fetchPatientAlertEssentials(userVO);
				WebUTIL.setMapInSession(essentialMap, _rq);
				String chk=_fb.getChk().replace("^", "#");
				String primaryKey[]=chk.split("#");
				String patientAlert=primaryKey[0];
				String serialNo=primaryKey[2];
				patientAlertMasterVO.setPatAlertId(patientAlert);
				patientAlertMasterVO.setSerialNo(serialNo);
				PatientAlertMasterDATA.fetchPatientAlertModify(patientAlertMasterVO,userVO);
				HelperMethods.populate(_fb, patientAlertMasterVO);
				
				if(_fb.getHmode().equals("VIEW"))
				{
					
				}
				
				objStatus.add(Status.DONE);
			}
			catch (HisRecordNotFoundException e)
			{
				System.out.println("Inside HisRecordNotFoundException");
				e.printStackTrace();
				objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
				
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
				WebUTIL.setStatus(_rq, objStatus);
				System.out.println("   -----> objStatus in finally  : " + objStatus);
				System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
			}
		}
		
	public static boolean modifySave(PatientAlertMasterFB _fb,HttpServletRequest _rq)
	{
		boolean flag=false;
		Status objStatus = new Status();
		UserVO userVO= getUserVO( _rq);
		PatientAlertMasterVO patientAlertMasterVO=new PatientAlertMasterVO();
		try{
				HelperMethods.populate(patientAlertMasterVO,_fb);
				String chk=_fb.getChk().replace("^", "#");
				String primaryKey[]=chk.split("#");
				String patientAlert=primaryKey[0];
				String slNo=primaryKey[2];
				patientAlertMasterVO.setPatAlertId(patientAlert);
				patientAlertMasterVO.setSerialNo(slNo);
				PatientAlertMasterDATA.modifySave(patientAlertMasterVO,userVO);
				flag=true;
				objStatus.add(Status.DONE);
				
			}
		catch(HisDuplicateRecordException e){	   		   	
			 System.out.println("Inside HisDuplicateRecordException");
	  		 e.printStackTrace(); 
	  		 flag=false;
	  		 objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			flag=false;
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			
		}
		catch (HisApplicationExecutionException e)
		{
			flag=false;
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			
		}
		catch (HisException e)
		{
			flag=false;
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
			
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}		
		return flag;
	}
	
}
