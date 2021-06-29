package mrd.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.BirthDeathUploadDtlVO;
import hisglobal.vo.PatientVO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.DeathRegistrationUploadDATA;
import mrd.transaction.controller.fb.DeathRegistrationUploadFB;

public class DeathRegistrationUploadUTL extends ControllerUTIL
{
	public static void getListOfDeath(DeathRegistrationUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		PatientVO[] deathDtlVO=null;
		
		try
		{
			deathDtlVO=DeathRegistrationUploadDATA.getListOfDeath(getUserVO(request)); 
			WebUTIL.setAttributeInSession(request, MrdConfig.ARR_DEATH_REGISTRATION_UPLOAD_LIST_VO, deathDtlVO);
			fb.setIsPrint(MrdConfig.NO);
			objStatus.add(Status.LIST);
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
	
	public static void getEssentialForBirthRegUpload(DeathRegistrationUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		
		try
		{
			List lstRelation=DeathRegistrationUploadDATA.getRelationList(getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.ESSENTIAL_ALL_RELATION_LIST, lstRelation);
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
	
	public static void getEssentialForBirthRegUploadNHandover(DeathRegistrationUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		BirthDeathUploadDtlVO birthUploadVO=new BirthDeathUploadDtlVO();
		String recordType="";
		String patCrNo="";
		
		try
		{
			patCrNo=fb.getPatCrNo();
			recordType=MrdConfig.RREGISTRATION_UPLOAD_MODE_DEATH;
			birthUploadVO=DeathRegistrationUploadDATA.getBirthDeathUploadDtl(recordType, fb.getPatCrNo(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.BIRTH_REGISTRATION_UPLOAD_DETAIL_VO, birthUploadVO);
			HelperMethods.populate(fb, birthUploadVO);
			fb.setPatCrNo(patCrNo);
			List lstRelation=DeathRegistrationUploadDATA.getRelationList(getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.ESSENTIAL_ALL_RELATION_LIST, lstRelation);
			
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

	public static void saveForRegUpload(DeathRegistrationUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		BirthDeathUploadDtlVO birthUploadVO=new BirthDeathUploadDtlVO();
		
		try
		{
			HelperMethods.populate(birthUploadVO, fb);
			birthUploadVO.setRecordType(MrdConfig.RREGISTRATION_UPLOAD_MODE_DEATH);
			birthUploadVO.setPatCrNo(fb.getPatCrNo());
			if(fb.getIsHandoverTo()==null)
				birthUploadVO.setEntryMode(MrdConfig.BIRTH_DEATH_ENTRY_MODE_UPLOAD);
			else
				birthUploadVO.setEntryMode(MrdConfig.BIRTH_DEATH_ENTRY_MODE_UPLOAD_N_HANDOVER);
			
			DeathRegistrationUploadDATA.saveForRegUpload(birthUploadVO,getUserVO(request));
			
			if(fb.getIsHandoverTo()!=null)
				fb.setIsPrint(MrdConfig.YES);
			
			objStatus.add(Status.DONE,"","Record Added Successfully");
			
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

	public static void saveHandoverForRegUpload(DeathRegistrationUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		BirthDeathUploadDtlVO birthHandoverVO=new BirthDeathUploadDtlVO();
		
		try
		{
			BirthDeathUploadDtlVO birthUploadVO=(BirthDeathUploadDtlVO)session.getAttribute(MrdConfig.BIRTH_REGISTRATION_UPLOAD_DETAIL_VO);
			HelperMethods.populate(birthHandoverVO, fb);
			birthHandoverVO.setRecordType(MrdConfig.RREGISTRATION_UPLOAD_MODE_DEATH);
			birthHandoverVO.setPatCrNo(fb.getPatCrNo());
			birthHandoverVO.setEntryMode(MrdConfig.BIRTH_DEATH_ENTRY_MODE_UPLOAD_N_HANDOVER);
			birthHandoverVO.setUploadDateTime(birthUploadVO.getUploadDateTime());
			
			DeathRegistrationUploadDATA.saveHandoverForRegUpload(birthHandoverVO,getUserVO(request));
			
			fb.setIsPrint(MrdConfig.YES);
			
			objStatus.add(Status.DONE,"","Record Added Successfully");
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
	
	public static void saveDuplicateHandoverForRegUpload(DeathRegistrationUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		BirthDeathUploadDtlVO birthHandoverVO=new BirthDeathUploadDtlVO();
		
		try
		{
			BirthDeathUploadDtlVO birthUploadVO=(BirthDeathUploadDtlVO)session.getAttribute(MrdConfig.BIRTH_REGISTRATION_UPLOAD_DETAIL_VO);
			HelperMethods.populate(birthHandoverVO, fb);
			birthHandoverVO.setRecordType(MrdConfig.RREGISTRATION_UPLOAD_MODE_DEATH);
			birthHandoverVO.setPatCrNo(fb.getPatCrNo());
			birthHandoverVO.setEntryMode(MrdConfig.BIRTH_DEATH_ENTRY_MODE_DUPLICATE_N_HANDOVER);
			birthHandoverVO.setUploadDateTime(birthUploadVO.getUploadDateTime());
			
			DeathRegistrationUploadDATA.saveHandoverForRegUpload(birthHandoverVO,getUserVO(request));
			
			fb.setIsPrint(MrdConfig.YES);
			
			objStatus.add(Status.DONE,"","Record Added Successfully");
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
	
	public static void getPatDeathUploadList(DeathRegistrationUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		List deathRegUploadList=null;	
		HttpSession session=WebUTIL.getSession(request);
		try
		{
			
			deathRegUploadList=DeathRegistrationUploadDATA.getPatDeathUploadList(fb.getStr_patCrNo(),fb.getStr_deathDate(), fb.getStr_firstName().toUpperCase(),fb.getStr_middleName().toUpperCase(),fb.getStr_lastName().toUpperCase(),getUserVO(request));
			session.setAttribute(MrdConfig.SERACH_DEATH_REGISTRATION_UPLOAD_LIST, deathRegUploadList);
			
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
}
