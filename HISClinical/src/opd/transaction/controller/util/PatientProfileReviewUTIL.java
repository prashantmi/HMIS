package opd.transaction.controller.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.PatientProfileReviewDATA;
import opd.transaction.controller.fb.PatientProfileReviewFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileReviewDtlVO;
import hisglobal.vo.UserVO;

public class PatientProfileReviewUTIL extends ControllerUTIL
{
	
		public static void getAllAdmittedPatientList(PatientProfileReviewFB fb, HttpServletRequest request) {
		
		Status objStatus = new Status();
		try
		{
			setSysdate(request);
			//UserVO userVO= getUserVO(request);
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			//objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
		
		public static void getPreviousProfileDetails(PatientProfileReviewFB fb, HttpServletRequest request) {
			
			Status objStatus = new Status();
			try
			{
				UserVO userVO= getUserVO(request);
				
				PatientProfileDetailVO[] patientProfileDetailVO=PatientProfileReviewDATA.getPreviousProfileDetails(fb.getPatCrNo(),userVO);
				
				if(patientProfileDetailVO!=null)
				{
					for(int i=0;i<patientProfileDetailVO.length;i++)
					{
						/*if(patientProfileDetailVO[i].getProfileType().equals(OpdConfig.PROFILE_TYPE_REFER))
						{
							patientProfileDetailVO[i].setProfileTypeDesc(OpdConfig.PROFILE_TYPE_REFER_DESC);
						}
						else if(patientProfileDetailVO[i].getProfileType().equals(OpdConfig.PROFILE_TYPE_DISCHARGE))
						{
							patientProfileDetailVO[i].setProfileTypeDesc(OpdConfig.PROFILE_TYPE_DISCHARGE_DESC);
						}
						else if(patientProfileDetailVO[i].getProfileType().equals(OpdConfig.PROFILE_TYPE_CASESHEET))
						{
							patientProfileDetailVO[i].setProfileTypeDesc(OpdConfig.PROFILE_TYPE_CASESHEET_DESC);
						}
						else if(patientProfileDetailVO[i].getProfileType().equals(OpdConfig.PROFILE_TYPE_GENERAL))
						{
							patientProfileDetailVO[i].setProfileTypeDesc(OpdConfig.PROFILE_TYPE_GENERAL_DESC);
						}*/
						
						if(patientProfileDetailVO[i].getAdmissionNo()==null)
						{
							patientProfileDetailVO[i].setAdmissionNo("-");
						}
					}
				}
				
				WebUTIL.setAttributeInSession(request,OpdConfig.PATIENT_PROFILE_REVIEW_DETAIL_PREVIOUS_PROFILE_VO_ARRAY, patientProfileDetailVO);
				objStatus.add(Status.TRANSINPROCESS);
			}
			catch (HisRecordNotFoundException e)
			{
				//objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
				objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
				e.printStackTrace();
			}
			catch (HisDataAccessException e)
			{
				objStatus.add(Status.ERROR_DA, e.getMessage(), "");
				e.printStackTrace();
			}
			catch (HisApplicationExecutionException e)
			{
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
				e.printStackTrace();
			}
			catch (HisException e)
			{
				objStatus.add(Status.ERROR, e.getMessage(), "");
				e.printStackTrace();
			}
			catch (Exception e)
			{
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
				e.printStackTrace();
			}
			finally
			{
				WebUTIL.setStatus(request, objStatus);
			}
		}

		public static void getReviewDetails(PatientProfileReviewFB fb, HttpServletRequest request) {
			
			Status objStatus = new Status();
			try
			{
				//UserVO userVO= getUserVO(request);
				objStatus.add(Status.TRANSINPROCESS);
				objStatus.add(Status.RECORDFOUND);
			}
			catch (HisRecordNotFoundException e)
			{
				//objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
				objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
				e.printStackTrace();
			}
			catch (HisDataAccessException e)
			{
				objStatus.add(Status.ERROR_DA, e.getMessage(), "");
				e.printStackTrace();
			}
			catch (HisApplicationExecutionException e)
			{
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
				e.printStackTrace();
			}
			catch (HisException e)
			{
				objStatus.add(Status.ERROR, e.getMessage(), "");
				e.printStackTrace();
			}
			catch (Exception e)
			{
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
				e.printStackTrace();
			}
			finally
			{
				WebUTIL.setStatus(request, objStatus);
			}
		}
		
		public static void savePatientProfileReviewDetail(PatientProfileReviewFB fb,HttpServletRequest request)
		{
			Status objStatus = new Status();
			//HttpSession session=request.getSession();
			ProfileReviewDtlVO profileReviewDtlVO=new ProfileReviewDtlVO();
			//PatientProfileDetailVO[] patientProfileDetailVO=null;
			try
			{
									
				HelperMethods.populate(profileReviewDtlVO, fb);
				
				PatientProfileReviewDATA.savePatientProfileReviewDetail(profileReviewDtlVO,getUserVO(request));
				
				objStatus.add(Status.DONE,"","Patient Profile Review Detail Saved Successfully");
				
				objStatus.add(Status.TRANSINPROCESS);
				objStatus.add(Status.RECORDFOUND);
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
		
		public static void fetchProfileReviewDetails(PatientProfileReviewFB fb, HttpServletRequest request) {
			
			Status objStatus = new Status();
			HttpSession session=request.getSession();
			try
			{
				UserVO userVO= getUserVO(request);
				
						
				ProfileReviewDtlVO[] profileReviewDtlVO=PatientProfileReviewDATA.fetchProfileReviewDetails(fb.getPatCrNo(),fb.getProfileId(),userVO);
				WebUTIL.setAttributeInSession(request,OpdConfig.PATIENT_PROFILE_REVIEW_DETAIL_PREVIOUS_REVIEW_DTL_VO_ARRAY, profileReviewDtlVO);
				
				if(profileReviewDtlVO.length==0)
				{
					objStatus.add(Status.DONE,"","No Previous Review Found");
				}
				
				PatientProfileDetailVO[] patientProfileDetailVO=(PatientProfileDetailVO[])session.getAttribute(OpdConfig.PATIENT_PROFILE_REVIEW_DETAIL_PREVIOUS_PROFILE_VO_ARRAY);
				
				if(patientProfileDetailVO!=null)
				{
					for(int i=0;i<patientProfileDetailVO.length;i++)
					{
						if(fb.getProfileId().equals(patientProfileDetailVO[i].getProfileId()))
						{
							fb.setProfileHeader(patientProfileDetailVO[i].getProfileHeader());
							fb.setProfileTypeDesc(patientProfileDetailVO[i].getProfileTypeDesc());
							fb.setCreationDate(patientProfileDetailVO[i].getEntryDate());
						}
					}
				}
			}
			catch (HisRecordNotFoundException e)
			{
				//objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
				objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
				e.printStackTrace();
			}
			catch (HisDataAccessException e)
			{
				objStatus.add(Status.ERROR_DA, e.getMessage(), "");
				e.printStackTrace();
			}
			catch (HisApplicationExecutionException e)
			{
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
				e.printStackTrace();
			}
			catch (HisException e)
			{
				objStatus.add(Status.ERROR, e.getMessage(), "");
				e.printStackTrace();
			}
			catch (Exception e)
			{
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
				e.printStackTrace();
			}
			finally
			{
				WebUTIL.setStatus(request, objStatus);
			}
		}
		
}
