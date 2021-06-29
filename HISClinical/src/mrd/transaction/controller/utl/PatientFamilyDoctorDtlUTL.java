package mrd.transaction.controller.utl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mrd.MrdConfig;
import mrd.transaction.controller.data.PatientFamilyDoctorDtlDATA;
import mrd.transaction.controller.fb.PatientFamilyDoctorDtlFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.PatFamilyDocDtlVO;

public class PatientFamilyDoctorDtlUTL extends ControllerUTIL
{
	public static void getExistingFamilyDoctorRecord(PatientFamilyDoctorDtlFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		PatFamilyDocDtlVO[] arrExistingPatFamilyDocVO=null;
		
		try
		{
			arrExistingPatFamilyDocVO=PatientFamilyDoctorDtlDATA.getExistingFamilyDoctorRecord(fb.getPatCrNo(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.ARR_EXISTING_PAT_FAMILY_DOC, arrExistingPatFamilyDocVO);
			if(arrExistingPatFamilyDocVO.length>0)
				objStatus.add(Status.LIST);
			else
				objStatus.add(Status.LIST,"","No Family Doctor Detail Exist");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void getPhysicianType(PatientFamilyDoctorDtlFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		
		try
		{
			List lstPhysicianType=PatientFamilyDoctorDtlDATA.getPhysicianType(getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.FAMILY_DOCTOR_PHYSICIAN_TYPE_LIST , lstPhysicianType);
			objStatus.add(Status.TRANSINPROCESS);
			
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void savePatientFamilyDoctorDetail(PatientFamilyDoctorDtlFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		
		try
		{
			PatFamilyDocDtlVO patFamilyDocVO=new PatFamilyDocDtlVO();
			HelperMethods.populate(patFamilyDocVO, fb);
			PatientFamilyDoctorDtlDATA.savePatientFamilyDoctorDetail(patFamilyDocVO,getUserVO(request));
			objStatus.add(Status.LIST,"","Record Saved Successfully");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void getPatientFamilyDocDetail(PatientFamilyDoctorDtlFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		PatFamilyDocDtlVO patFamilyDocVO=null;
		String crNo=fb.getChk()[0].split("@")[0];
		//String hCode=fb.getChk()[0].split("@")[1];
		String slNo=fb.getChk()[0].split("@")[2];
		
		try
		{
			getPhysicianType(fb, request);
			patFamilyDocVO=PatientFamilyDoctorDtlDATA.getPatientFamilyDocDetail(fb.getChk()[0],getUserVO(request));
			HelperMethods.populate(fb,patFamilyDocVO);
			fb.setPatCrNo(crNo);
			fb.setSlNo(slNo);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void modifyPatientFamilyDocDetail(PatientFamilyDoctorDtlFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		
		try
		{	String slNo=fb.getSlNo();
			PatFamilyDocDtlVO patFamilyDocVO=new PatFamilyDocDtlVO();
			HelperMethods.populate(patFamilyDocVO, fb);
			patFamilyDocVO.setSlNo(slNo);
			PatientFamilyDoctorDtlDATA.modifyPatientFamilyDocDetail(patFamilyDocVO,getUserVO(request));
			objStatus.add(Status.LIST,"","Record Modified Successfully");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void revokePatientFamilyDocDetail(PatientFamilyDoctorDtlFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		
		try
		{
			for(int i=0;i<fb.getChk().length;i++)
			{
				String crNo=fb.getChk()[i].split("@")[0];
				String hCode=fb.getChk()[i].split("@")[1];
				String slNo=fb.getChk()[i].split("@")[2];
				
				PatientFamilyDoctorDtlDATA.revokePatientFamilyDocDetail(crNo,hCode,slNo,getUserVO(request));
			}
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}	
	
}
