package mrd.transaction.controller.utl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.BirthRegistrationUploadDATA;
import mrd.transaction.controller.fb.BirthRegistrationUploadFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.ANCNeonatalDetailVO;
import hisglobal.vo.BirthDeathUploadDtlVO;
import hisglobal.vo.PatientVO;

public class BirthRegistrationUploadUTL extends ControllerUTIL
{
	public static void getListOfBirth(BirthRegistrationUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		ANCNeonatalDetailVO[] newnatalDtlVO=null;
		
		try
		{
			newnatalDtlVO=BirthRegistrationUploadDATA.getListOfBirth(getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.ARR_BIRTH_REGISTRATION_UPLOAD_LIST_VO, newnatalDtlVO);
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
	
	public static void getMotherNChildDetail(BirthRegistrationUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		ANCNeonatalDetailVO[] newnatalDtlVO=null;
		String motherCrNo="";
		String childCrNo="";
		
		try
		{
			if(fb.getIsFromSearch().equals(MrdConfig.NO))
				newnatalDtlVO=(ANCNeonatalDetailVO[])session.getAttribute(MrdConfig.ARR_BIRTH_REGISTRATION_UPLOAD_LIST_VO);
			else
				newnatalDtlVO=(ANCNeonatalDetailVO[])session.getAttribute(MrdConfig.ARR_BIRTH_REGISTRATION_UPLOAD_DUPLICATE_VO);
			
			for(int i=0;i<newnatalDtlVO.length;i++)
			{
				if(fb.getSelectedChild().equals(newnatalDtlVO[i].getChildCrNo()))
				{
					motherCrNo=newnatalDtlVO[i].getPatCrNo();
					break;
				}
			}
			childCrNo=fb.getSelectedChild();
			Map map=BirthRegistrationUploadDATA.getMotherNChildDetail(motherCrNo,childCrNo,getUserVO(request));
		//	PatientVO motherPatVO=(PatientVO)map.get(MrdConfig.MOTHER_DETAIL_VO);
		//	PatientVO childPatVO=(PatientVO)map.get(MrdConfig.CHILD_DETAIL_VO);
			
			WebUTIL.setMapInSession(map, request);
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
	
	/*public static void getMotherNChildDetailForDuplicate(BirthRegistrationUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		String motherCrNo="";
		String childCrNo="";
		
		try
		{
			ANCNeonatalDetailVO[] newnatalDtlVO=(ANCNeonatalDetailVO[])session.getAttribute(MrdConfig.ARR_BIRTH_REGISTRATION_UPLOAD_DUPLICATE_VO);
			for(int i=0;i<newnatalDtlVO.length;i++)
			{
				if(fb.getSelectedChild().equals(newnatalDtlVO[i].getChildCrNo()))
				{
					motherCrNo=newnatalDtlVO[i].getPatCrNo();
					break;
				}
			}
			childCrNo=fb.getSelectedChild();
			Map map=BirthRegistrationUploadDATA.getMotherNChildDetail(motherCrNo,childCrNo,getUserVO(request));
		//	PatientVO motherPatVO=(PatientVO)map.get(MrdConfig.MOTHER_DETAIL_VO);
		//	PatientVO childPatVO=(PatientVO)map.get(MrdConfig.CHILD_DETAIL_VO);
			
			WebUTIL.setMapInSession(map, request);
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
	}	*/
	
	public static void getEssentialForBirthRegUpload(BirthRegistrationUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		
		try
		{
			List lstRelation=BirthRegistrationUploadDATA.getRelationList(getUserVO(request));
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
	
	public static void saveForRegUpload(BirthRegistrationUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		BirthDeathUploadDtlVO birthUploadVO=new BirthDeathUploadDtlVO();
		
		try
		{
			HelperMethods.populate(birthUploadVO, fb);
			birthUploadVO.setRecordType(MrdConfig.RREGISTRATION_UPLOAD_MODE_BIRTH);
			birthUploadVO.setPatCrNo(fb.getSelectedChild());
			if(fb.getIsHandoverTo()==null)
				birthUploadVO.setEntryMode(MrdConfig.BIRTH_DEATH_ENTRY_MODE_UPLOAD);
			else
				birthUploadVO.setEntryMode(MrdConfig.BIRTH_DEATH_ENTRY_MODE_UPLOAD_N_HANDOVER);
			
			BirthRegistrationUploadDATA.saveForRegUpload(birthUploadVO,getUserVO(request));
			
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
	
	
	public static void getEssentialForBirthRegUploadNHandover(BirthRegistrationUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		BirthDeathUploadDtlVO birthUploadVO=new BirthDeathUploadDtlVO();
		String recordType="";
		
		try
		{
			recordType=MrdConfig.RREGISTRATION_UPLOAD_MODE_BIRTH;
			birthUploadVO=BirthRegistrationUploadDATA.getBirthDeathUploadDtl(recordType, fb.getSelectedChild(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.BIRTH_REGISTRATION_UPLOAD_DETAIL_VO, birthUploadVO);
			HelperMethods.populate(fb, birthUploadVO);
			List lstRelation=BirthRegistrationUploadDATA.getRelationList(getUserVO(request));
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
	
	
	
	public static void saveHandoverForRegUpload(BirthRegistrationUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		BirthDeathUploadDtlVO birthHandoverVO=new BirthDeathUploadDtlVO();
		
		try
		{
			BirthDeathUploadDtlVO birthUploadVO=(BirthDeathUploadDtlVO)session.getAttribute(MrdConfig.BIRTH_REGISTRATION_UPLOAD_DETAIL_VO);
			HelperMethods.populate(birthHandoverVO, fb);
			birthHandoverVO.setRecordType(MrdConfig.RREGISTRATION_UPLOAD_MODE_BIRTH);
			birthHandoverVO.setPatCrNo(fb.getSelectedChild());
			birthHandoverVO.setEntryMode(MrdConfig.BIRTH_DEATH_ENTRY_MODE_UPLOAD_N_HANDOVER);
			birthHandoverVO.setUploadDateTime(birthUploadVO.getUploadDateTime());
			
			BirthRegistrationUploadDATA.saveHandoverForRegUpload(birthHandoverVO,getUserVO(request));
			
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
	
	public static void saveHandoverForDuplicateRegUpload(BirthRegistrationUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		BirthDeathUploadDtlVO birthHandoverVO=new BirthDeathUploadDtlVO();
		
		try
		{
			BirthDeathUploadDtlVO birthUploadVO=(BirthDeathUploadDtlVO)session.getAttribute(MrdConfig.BIRTH_REGISTRATION_UPLOAD_DETAIL_VO);
			HelperMethods.populate(birthHandoverVO, fb);
			birthHandoverVO.setRecordType(MrdConfig.RREGISTRATION_UPLOAD_MODE_BIRTH);
			birthHandoverVO.setPatCrNo(fb.getSelectedChild());
			birthHandoverVO.setEntryMode(MrdConfig.BIRTH_DEATH_ENTRY_MODE_DUPLICATE_N_HANDOVER);
			birthHandoverVO.setUploadDateTime(birthUploadVO.getUploadDateTime());
			
			BirthRegistrationUploadDATA.saveHandoverForRegUpload(birthHandoverVO,getUserVO(request));
			
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
	
	public static void printSlipForBirthRegUpload(BirthRegistrationUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		
		try
		{
			PatientVO birthSlipVO=BirthRegistrationUploadDATA.getBirthSlipDetail(fb.getSelectedChild(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.BIRTH_REGISTRATION_SLIP_DETAIL_VO, birthSlipVO);
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "Data Access Error", "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		catch (HisException e)
		{
			System.out.println("Inside Exception");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void searchForBirthRegUpload(BirthRegistrationUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		ANCNeonatalDetailVO[] searchDtlVO=null;
		PatientVO searchFindVO=new PatientVO();
		
		try
		{
			if(fb.getSearchType().equals(MrdConfig.SEARCH_TYPE_CHILD))
			{
				searchFindVO.setPatCrNo(fb.getSearchChildCrNo());
				searchFindVO.setPatDOB(fb.getSearchChildDob());
				
				searchDtlVO=BirthRegistrationUploadDATA.searchForBirthRegUpload(searchFindVO,getUserVO(request));
				WebUTIL.setAttributeInSession(request, MrdConfig.ARR_BIRTH_REGISTRATION_UPLOAD_DUPLICATE_VO, searchDtlVO);
			}
			else
			{
				searchFindVO.setPatCrNo(fb.getSearchMomCrNo());
				searchFindVO.setPatFirstName(fb.getSearchMomFName());
				searchFindVO.setPatMiddleName(fb.getSearchMomMName());
				searchFindVO.setPatLastName(fb.getSearchMomLName());
				
				searchDtlVO=BirthRegistrationUploadDATA.searchForBirthRegUploadByMother(searchFindVO,getUserVO(request));
				WebUTIL.setAttributeInSession(request, MrdConfig.ARR_BIRTH_REGISTRATION_UPLOAD_DUPLICATE_VO, searchDtlVO);
			}
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
			objStatus.add(Status.ERROR_DA, "Data Access Error", "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		catch (HisException e)
		{
			System.out.println("Inside Exception");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
}
