/* 
## Copyright Information		: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Amit Garg 
 ## Module Name					: MRD
 ## Process/Database Object Name: Medical and Fitness certificate issue process
 ## Purpose						: Medical and Fitness certificate issue process
 ## Date of Creation			: 
 ## Modification Log			:				
 ##		Modify Date				: 04-Dec-2014 
 ##		Reason	(CR/PRS)		:  
 ##		Modify By				: Amit Garg

*/


package mrd.transaction.controller.utl;

import inpatient.InpatientConfig;
import inpatient.transaction.controller.utl.InpatientDetailUTL;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.OpdDiagnosisDATA;
import opd.transaction.controller.fb.ConsentRequestFB;
import registration.RegistrationConfig;
import medicalboard.MedicalBoardConfig;
import mrd.MrdConfig;
import mrd.transaction.controller.data.MedicalCertificateDATA;
import mrd.transaction.controller.fb.MedicalCertificateFB;
import hisglobal.backutil.HelperMethods;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.vo.ConsentRequestVO;
import hisglobal.vo.EpisodeRestAdviceVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.PatFitnessDtlVO;
import hisglobal.vo.PatMedicalDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class MedicalCertificateUTL extends ControllerUTIL
{
	/**  Getting The Patient Detail & All Episode of the Patient
	 * @param fb
	 * @param request
	 */
	public static void getAllEpisodeOfThePatient(MedicalCertificateFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		EpisodeVO[] patEpisodeVO=null;
		
		try
		{
			if(fb.getBackDatedFlagMC().equals(Config.GENERATE_MEDICAL_CERTIFICATE_BACK_DATED_YES))
				patEpisodeVO=MedicalCertificateDATA.getAllEpisodeOfThePatient(fb.getPatCrNo(),getUserVO(request));
			else
				patEpisodeVO=MedicalCertificateDATA.getAllEpisodeOfThePatientTodayVisited(fb.getPatCrNo(),getUserVO(request));			
			
			WebUTIL.setAttributeInSession(request,MrdConfig.MC_PATIENT_ALL_EPISODE_VO_ARR ,patEpisodeVO );
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
	
	/** Getting the List of Medical Certificate Generated for the Patient & Rest Advice on a particular Episode
	 * @param fb
	 * @param request
	 */
	public static void getRestAdvice(MedicalCertificateFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		EpisodeRestAdviceVO epiRestAdviceVO=new EpisodeRestAdviceVO();
		EpisodeRestAdviceVO[] arrEpiRestAdvice=null;
		
		try
		{
			// Getting the Rest Advice on a particular Episode
			if(fb.getBackDatedFlagMC().equals(Config.GENERATE_MEDICAL_CERTIFICATE_BACK_DATED_YES))
				arrEpiRestAdvice=MedicalCertificateDATA.getEpisodeRestAdvice(fb.getPatCrNo(),fb.getSelectedEpiCode(),getUserVO(request));
			else
				arrEpiRestAdvice=MedicalCertificateDATA.getEpisodeRestAdviceTodayVisited(fb.getPatCrNo(),fb.getSelectedEpiCode(),getUserVO(request));
			
			WebUTIL.setAttributeInSession(request, MrdConfig.MC_PATIENT_EPI_REST_ADVICE_VO_BY_EPISODE_ARR, arrEpiRestAdvice);

			EpisodeVO[] patEpisodeVO=(EpisodeVO[])session.getAttribute(MrdConfig.MC_PATIENT_ALL_EPISODE_VO_ARR);
			for(int i=0;i<patEpisodeVO.length;i++)
			{
				if(fb.getSelectedEpiCode().equals(patEpisodeVO[i].getEpisodeCode()))
				{
					WebUTIL.setAttributeInSession(request, MrdConfig.MC_DIAGNOSIS_CODE_TYPE, patEpisodeVO[i].getDiagnosisCodeType()) ;
					fb.setUnitDiagnosisCodeType(patEpisodeVO[i].getDiagnosisCodeType());
					fb.setDeptUnitCode(patEpisodeVO[i].getDepartmentUnitCode());
					if(patEpisodeVO[i].getEpisodeCloseDate()!=null)
						fb.setEpisodeCloseDate(patEpisodeVO[i].getEpisodeCloseDate());
					else
						fb.setEpisodeCloseDate("");
				}	
			}
			
			epiRestAdviceVO.setPatCrNo(fb.getPatCrNo()) ;
			epiRestAdviceVO.setEpisodeCode(fb.getSelectedEpiCode());
			String diagCodeType=(String)session.getAttribute(MrdConfig.MC_DIAGNOSIS_CODE_TYPE);
			
			// Getting the List of diagnosis of the Patient
			List lstDiag=MedicalCertificateDATA.getPatDiagnosisList(diagCodeType,epiRestAdviceVO,getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.MC_PATIENT_DIAGNOSIS_LIST, lstDiag);
			
			// Getting the List of All Visits of the patient on a particular Episode 
			EpisodeVO[] patientEpisodeVO=MedicalCertificateDATA.getAllVisitOfEpisodePat(fb.getPatCrNo(),fb.getSelectedEpiCode(),getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.MC_PATIENT_ALL_VISIT_EPISODE_VO_ARR ,patientEpisodeVO );
			
			// Getting the List of All Consultant 
			List lstConsultant=MedicalCertificateDATA.getAllConsultantForMC(fb.getDeptUnitCode(),getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.MC_LIST_ALL_CONSULTANT ,lstConsultant);
			
			if(lstConsultant.size()>0)
				objStatus.add(Status.TRANSINPROCESS);
			else
				objStatus.add(Status.TRANSINPROCESS,"No Consultant Found","");
			
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
	
	public static void saveMedicalCertificateGeneration(MedicalCertificateFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		PatMedicalDtlVO patMedicalDtlVO=new PatMedicalDtlVO();
		UserVO userVO=getUserVO(request);
		
		try
		{
			
			patMedicalDtlVO.setPatCrNo(fb.getPatCrNo());
			patMedicalDtlVO.setEpisodeCode(fb.getSelectedEpiCode());
			if(fb.getFlagForMCSave().equals(MrdConfig.MC_SAVE_FLAG_REST))
			{
				EpisodeVO[] patEpisodeVO=(EpisodeVO[])session.getAttribute(MrdConfig.MC_PATIENT_ALL_EPISODE_VO_ARR);
				for(int i=0;i<patEpisodeVO.length;i++)
				{
					if(fb.getSelectedEpiCode().equals(patEpisodeVO[i].getEpisodeCode()))
					{
						if(patEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_IPD))
							patMedicalDtlVO.setPatAdmNo(patEpisodeVO[i].getAdmissionNo());
					}	
				}
				int i=Integer.parseInt(fb.getSelectedRest());
				EpisodeRestAdviceVO[] arrEpiRestAdvice=(EpisodeRestAdviceVO[])session.getAttribute(MrdConfig.MC_PATIENT_EPI_REST_ADVICE_VO_BY_EPISODE_ARR);
				
				patMedicalDtlVO.setEmpNo(arrEpiRestAdvice[i].getEmpNo());
				patMedicalDtlVO.setSufferingFrom(fb.getSufferingFrom());
				patMedicalDtlVO.setAdviceDays(arrEpiRestAdvice[i].getAdviceDays());
				patMedicalDtlVO.setFromDate(arrEpiRestAdvice[i].getStartDate());
				patMedicalDtlVO.setToDate(arrEpiRestAdvice[i].getEndDate());
				patMedicalDtlVO.setEpisodeVisitNo(arrEpiRestAdvice[i].getEpisodeVisitNo());
				patMedicalDtlVO.setMedicalCertificateDesc(fb.getMedicalCertificateId());
				
				MedicalCertificateDATA.saveOnBasisRestAdvice(patMedicalDtlVO,fb.getFlagForMCSave(),fb.getDeptUnitCode(),fb.getGenerationMode(),getUserVO(request));
				fb.setIsOverlapped(MrdConfig.IS_OVERLAPPED_NO);
				objStatus.add(Status.DONE,"","Record added Successfully");
			}
			if(fb.getFlagForMCSave().equals(MrdConfig.MC_SAVE_FLAG_NEW))
			{
				EpisodeVO[] patEpisodeVO=(EpisodeVO[])session.getAttribute(MrdConfig.MC_PATIENT_ALL_VISIT_EPISODE_VO_ARR);
				for(int i=0;i<patEpisodeVO.length;i++)
				{
					if(fb.getNewFromDate().equals(patEpisodeVO[i].getEpisodeDate()))
						patMedicalDtlVO.setEpisodeVisitNo(patEpisodeVO[i].getEpisodeVisitNo());
			
					if(fb.getSelectedEpiCode().equals(patEpisodeVO[i].getEpisodeCode()))
					{
						if(patEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_IPD))
							patMedicalDtlVO.setPatAdmNo(patEpisodeVO[i].getAdmissionNo());
					}
				}
				if(fb.getEmpMappingFlag().equals(Config.MEDICAL_CERTIFICATE_EMPLOYEE_MAPPING_OFFLINE))
					patMedicalDtlVO.setEmpNo(fb.getNewEmpNo().split("@")[0]);
				else
					patMedicalDtlVO.setEmpNo(userVO.getUserEmpID());
				
				patMedicalDtlVO.setSufferingFrom(fb.getNewSufferingFrom());
				patMedicalDtlVO.setAdviceDays(fb.getNewAdvDays());
				patMedicalDtlVO.setFromDate(fb.getNewFromDate());
				patMedicalDtlVO.setToDate(fb.getNewToDate());
				patMedicalDtlVO.setMedicalCertificateDesc(fb.getNewMedicalCertificateId());
				fb.setNewAdviceDays(fb.getNewAdvDays());
				
				MedicalCertificateDATA.saveOnBasisRestAdvice(patMedicalDtlVO,fb.getFlagForMCSave(),fb.getDeptUnitCode(),fb.getGenerationMode(),getUserVO(request));
				fb.setIsOverlapped(MrdConfig.IS_OVERLAPPED_NO);
				objStatus.add(Status.DONE,"","Record added Successfully");
			}
			
			if(fb.getFlagForMCSave().equals(MrdConfig.MC_SAVE_FLAG_MODIFY))
			{
				int i=Integer.parseInt(fb.getSelectedRadioModify());
				PatMedicalDtlVO[] patMedDtlVO=(PatMedicalDtlVO[])session.getAttribute(MrdConfig.MC_ISSUED_MEDICAL_CERTIFICATE_ON_BASIS_EPISODE);
				
				patMedicalDtlVO.setEpisodeVisitNo(patMedDtlVO[i].getEpisodeVisitNo());
				patMedicalDtlVO.setMedicalCertificateDesc(patMedDtlVO[i].getMedicalCertificateName());
				patMedicalDtlVO.setMedicalCertificateId(patMedDtlVO[i].getMedicalCertificateId());
				patMedicalDtlVO.setPatAdmNo(patMedDtlVO[i].getPatAdmNo());
				patMedicalDtlVO.setSufferingFrom(fb.getModSufferingFrom());
				patMedicalDtlVO.setAdviceDays(fb.getModAdviceDays());
				patMedicalDtlVO.setFromDate(patMedDtlVO[i].getFromDate());
				patMedicalDtlVO.setToDate(fb.getModToDate());
				patMedicalDtlVO.setRemarks(fb.getRemarks());
				patMedicalDtlVO.setSNo(patMedDtlVO[i].getSNo());
				patMedicalDtlVO.setEmpNo(patMedDtlVO[i].getEmpNo());
				
				MedicalCertificateDATA.saveExtendMedicalCertificate(patMedicalDtlVO,getUserVO(request));
				fb.setIsOverlapped(MrdConfig.IS_OVERLAPPED_NO);
				objStatus.add(Status.DONE,"","Record Modified Successfully");
			}
			
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			fb.setIsOverlapped(MrdConfig.IS_OVERLAPPED_YES);
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
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
	
	
	public static void getGeneratedCertificateForFitnessNModify(MedicalCertificateFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		//HttpSession session=request.getSession();
		
		try
		{
			// Getting the List of Medical Certificate Generated for the Patient on a particular Episode
			PatMedicalDtlVO[] issuePatMCVO=MedicalCertificateDATA.getIssuedMedicalCertificate(fb.getPatCrNo(),fb.getSelectedEpiCode(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.MC_ISSUED_MEDICAL_CERTIFICATE_ON_BASIS_EPISODE , issuePatMCVO);
			
			if(issuePatMCVO.length>0)
			{
				if(fb.getCertificateType().equals(MrdConfig.CERTIFICATE_TYPE_FITNESS_CERTIFICATE))
				{
					if(fb.getBackDatedFlagFC().equals(Config.GENERATE_FITNESS_CERTIFICATE_BACK_DATED_YES))
					{
						for(int i=0;i<issuePatMCVO.length;i++)
						{
							if(issuePatMCVO[i].getFitnessCertificateId()==null)
							{
								String toDate=issuePatMCVO[i].getToDate();
								SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
								Calendar c = Calendar.getInstance();
								c.setTime(sdf.parse(toDate));
								c.add(Calendar.DATE, 1);			// Getting the Fitness Date Which is Next Day of Medical Certificate End Date
								String fitnessDate = sdf.format(c.getTime());
								issuePatMCVO[i].setFitnessDate(fitnessDate);
							}
						}
					}	
				}	
				objStatus.add(Status.TRANSINPROCESS);
			}	
			else
				objStatus.add(Status.TRANSINPROCESS,"","No Generated Medical Certificate Found");
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
	
	
	public static void saveFitnessCertificateGeneration(MedicalCertificateFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		PatFitnessDtlVO patFitnessDtlVO=new PatFitnessDtlVO();
		PatMedicalDtlVO patMedDtlVO=new PatMedicalDtlVO();
		
		try
		{
			int i=Integer.parseInt(fb.getSelectedRadioFitness());
			PatMedicalDtlVO[] patMedicalDtlVO=(PatMedicalDtlVO[])session.getAttribute(MrdConfig.MC_ISSUED_MEDICAL_CERTIFICATE_ON_BASIS_EPISODE);
			
			patFitnessDtlVO.setPatCrNo(fb.getPatCrNo());
			patFitnessDtlVO.setEpisodeCode(fb.getSelectedEpiCode());
			patFitnessDtlVO.setEpisodeVisitNo(patMedicalDtlVO[i].getEpisodeVisitNo());
			patFitnessDtlVO.setSufferingFrom(patMedicalDtlVO[i].getSufferingFrom());
			patFitnessDtlVO.setPatAdmNo(patMedicalDtlVO[i].getPatAdmNo());
			patFitnessDtlVO.setMedicalCertificateDesc(patMedicalDtlVO[i].getMedicalCertificateName());
			patFitnessDtlVO.setMedicalCertificateId(patMedicalDtlVO[i].getMedicalCertificateId());
			patFitnessDtlVO.setFitnessCertificateDesc(fb.getFitnessCertificateId());
			
			if(fb.getBackDatedFlagFC().equals(Config.GENERATE_FITNESS_CERTIFICATE_BACK_DATED_YES))
			{
				patFitnessDtlVO.setFitnessDate(fb.getFitnessDate());
				
				MedicalCertificateDATA.saveFitnessDate(patFitnessDtlVO,fb.getDeptUnitCode(),fb.getGenerationMode(),getUserVO(request));
				fb.setIsOverlapped(MrdConfig.IS_OVERLAPPED_NO);
				objStatus.add(Status.DONE,"","Fitness Date Added Successfully");
			}
			else
			{
				if(fb.getExtendFlag().equals(MrdConfig.FITNESS_MC_EXTEND_NO))
				{
					patFitnessDtlVO.setFitnessDate(fb.getFitnessDt());
					
					MedicalCertificateDATA.saveFitnessDate(patFitnessDtlVO,fb.getDeptUnitCode(),fb.getGenerationMode(),getUserVO(request));
					fb.setIsOverlapped(MrdConfig.IS_OVERLAPPED_NO);
					objStatus.add(Status.DONE,"","Fitness Date Added Successfully");
				}
				if(fb.getExtendFlag().equals(MrdConfig.FITNESS_MC_EXTEND_YES))
				{
					patFitnessDtlVO.setFitnessDate(fb.getFitnessDt());
					
					String toDate=fb.getFitnessDt();
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
					Calendar c = Calendar.getInstance();
					c.setTime(sdf.parse(toDate));
					c.add(Calendar.DATE, -1);		//Getting The Previous Date of Fitness Date	
					String extendDate = sdf.format(c.getTime());  
				
					Calendar calendar1=Calendar.getInstance();
					Calendar calendar2=Calendar.getInstance();
					calendar1.setTime(sdf.parse(patMedicalDtlVO[i].getFromDate()));
					calendar2.setTime(sdf.parse(extendDate));
					
					long noOfDays=((calendar2.getTime().getTime() -	calendar1.getTime().getTime()) / (24 * 3600 * 1000))+1;
					String advDays=String.valueOf(noOfDays);
					
					patMedDtlVO.setPatCrNo(fb.getPatCrNo());
					patMedDtlVO.setEpisodeCode(fb.getSelectedEpiCode());
					patMedDtlVO.setEpisodeVisitNo(patMedicalDtlVO[i].getEpisodeVisitNo());
					patMedDtlVO.setMedicalCertificateDesc(patMedicalDtlVO[i].getMedicalCertificateName());
					patMedDtlVO.setMedicalCertificateId(patMedicalDtlVO[i].getMedicalCertificateId());
					patMedDtlVO.setPatAdmNo(patMedicalDtlVO[i].getPatAdmNo());
					patMedDtlVO.setSufferingFrom(patMedicalDtlVO[i].getSufferingFrom());
					patMedDtlVO.setAdviceDays(advDays);
					patMedDtlVO.setFromDate(patMedicalDtlVO[i].getFromDate());
					patMedDtlVO.setToDate(extendDate);
				//	patMedDtlVO.setRemarks(fb.getRemarks());
					patMedDtlVO.setSNo(patMedicalDtlVO[i].getSNo());
					patMedDtlVO.setEmpNo(patMedicalDtlVO[i].getEmpNo());
					
					MedicalCertificateDATA.saveFitnessDateNExtendMC(patMedDtlVO,patFitnessDtlVO,fb.getDeptUnitCode(),fb.getGenerationMode(),getUserVO(request));
					fb.setIsOverlapped(MrdConfig.IS_OVERLAPPED_NO);
					objStatus.add(Status.DONE,"","Fitness Date Added Successfully");
				}
			}
			
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			fb.setIsOverlapped(MrdConfig.IS_OVERLAPPED_YES);
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
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
	
	
	
	
	
	
	
	
	/** Getting the Details of a particular Rest Advice
	 * @param fb
	 * @param request
	 */
	/*public static void generateMCByRestAdvice(MedicalCertificateFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		try
		{
			String diagCodeType=(String)session.getAttribute(MrdConfig.MC_DIAGNOSIS_CODE_TYPE);
			int i=Integer.parseInt(fb.getSelectedRest());
			EpisodeRestAdviceVO[] arrEpiRestAdvice=(EpisodeRestAdviceVO[])session.getAttribute(MrdConfig.MC_PATIENT_EPI_REST_ADVICE_VO_BY_EPISODE_ARR);
			arrEpiRestAdvice[i].setPatCrNo(fb.getPatCrNo());
			arrEpiRestAdvice[i].setEpisodeCode(fb.getSelectedEpiCode());
			fb.setFromDate(arrEpiRestAdvice[i].getStartDate());
			fb.setToDate(arrEpiRestAdvice[i].getEndDate());
			fb.setAdviceDays(arrEpiRestAdvice[i].getAdviceDays());
			fb.setEmpNo(arrEpiRestAdvice[i].getEmpNo());
			fb.setConsultantName(arrEpiRestAdvice[i].getConsultantName());
			
			List lstDiag=MedicalCertificateDATA.getPatDiagnosisList(diagCodeType,arrEpiRestAdvice[i],getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.MC_PATIENT_DIAGNOSIS_LIST, lstDiag);
			if(lstDiag.size()==0)
				objStatus.add(Status.TRANSINPROCESS,"","No Diagnosis Found");
			else
				objStatus.add(Status.TRANSINPROCESS);
			
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
	*/
	
	/** Saving the Data Based on Rest Advice of the Patient
	 * @param fb
	 * @param request
	 */
	/*public static void saveOnBasisRestAdvice(MedicalCertificateFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		PatMedicalDtlVO patMedicalDtlVO=new PatMedicalDtlVO();
		try
		{
			EpisodeVO[] patEpisodeVO=(EpisodeVO[])session.getAttribute(MrdConfig.MC_PATIENT_ALL_EPISODE_VO_ARR);
			for(int i=0;i<patEpisodeVO.length;i++)
			{
				if(fb.getSelectedEpiCode().equals(patEpisodeVO[i].getEpisodeCode()))
				{
					if(patEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_IPD))
						patMedicalDtlVO.setPatAdmNo(patEpisodeVO[i].getAdmissionNo());
				}	
			}
			
			int i=Integer.parseInt(fb.getSelectedRest());
			EpisodeRestAdviceVO[] arrEpiRestAdvice=(EpisodeRestAdviceVO[])session.getAttribute(MrdConfig.MC_PATIENT_EPI_REST_ADVICE_VO_BY_EPISODE_ARR);
			patMedicalDtlVO.setPatCrNo(fb.getPatCrNo());
			patMedicalDtlVO.setEpisodeCode(fb.getSelectedEpiCode());
			patMedicalDtlVO.setEmpNo(arrEpiRestAdvice[i].getEmpNo());
			patMedicalDtlVO.setSufferingFrom(fb.getSufferingFrom());
			patMedicalDtlVO.setAdviceDays(arrEpiRestAdvice[i].getAdviceDays());
			patMedicalDtlVO.setFromDate(arrEpiRestAdvice[i].getStartDate());
			patMedicalDtlVO.setToDate(arrEpiRestAdvice[i].getEndDate());
			patMedicalDtlVO.setEpisodeVisitNo(arrEpiRestAdvice[i].getEpisodeVisitNo());
			patMedicalDtlVO.setMedicalCertificateDesc(fb.getMedicalCertificateId());
			
			MedicalCertificateDATA.saveOnBasisRestAdvice(patMedicalDtlVO,fb.getDeptUnitCode(),fb.getGenerationMode(),getUserVO(request));
			fb.setIsOverlapped(MrdConfig.IS_OVERLAPPED_NO);
			objStatus.add(Status.DONE,"","Record added Successfully");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			fb.setIsOverlapped(MrdConfig.IS_OVERLAPPED_YES);
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
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
	}*/
	
	/**  Getting Details for New Advice
	 * @param fb
	 * @param request
	 */
	/*public static void getForNewAdvice(MedicalCertificateFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		EpisodeRestAdviceVO epiRestAdviceVO=new EpisodeRestAdviceVO();
		
		try
		{
			epiRestAdviceVO.setPatCrNo(fb.getPatCrNo()) ;
			epiRestAdviceVO.setEpisodeCode(fb.getSelectedEpiCode());
			String diagCodeType=(String)session.getAttribute(MrdConfig.MC_DIAGNOSIS_CODE_TYPE);
			
			// Getting the List of diagnosis of the Patient
			List lstDiag=MedicalCertificateDATA.getPatDiagnosisList(diagCodeType,epiRestAdviceVO,getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.MC_PATIENT_DIAGNOSIS_LIST, lstDiag);
			
			// Getting the List of All Visits of the patient on a particular Episode 
			EpisodeVO[] patEpisodeVO=MedicalCertificateDATA.getAllVisitOfEpisodePat(fb.getPatCrNo(),fb.getSelectedEpiCode(),getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.MC_PATIENT_ALL_VISIT_EPISODE_VO_ARR ,patEpisodeVO );
			
			// Getting the List of All Consultant 
			List lstConsultant=MedicalCertificateDATA.getAllConsultantForMC(getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.MC_LIST_ALL_CONSULTANT ,lstConsultant);
			if(lstDiag.size()==0 )
				objStatus.add(Status.TRANSINPROCESS,"","No Diagnosis Found");
			else
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
	}*/
	
	/**  Saving for New Advice
	 * @param fb
	 * @param request
	 */
	/*public static void saveOnBasisNewAdvice(MedicalCertificateFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		PatMedicalDtlVO patMedicalDtlVO=new PatMedicalDtlVO();
		
		try
		{
			EpisodeVO[] patEpisodeVO=(EpisodeVO[])session.getAttribute(MrdConfig.MC_PATIENT_ALL_VISIT_EPISODE_VO_ARR);
			for(int i=0;i<patEpisodeVO.length;i++)
			{
				if(fb.getNewFromDate().equals(patEpisodeVO[i].getEpisodeDate()))
					patMedicalDtlVO.setEpisodeVisitNo(patEpisodeVO[i].getEpisodeVisitNo());
		
				if(fb.getSelectedEpiCode().equals(patEpisodeVO[i].getEpisodeCode()))
				{
					if(patEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_IPD))
						patMedicalDtlVO.setPatAdmNo(patEpisodeVO[i].getAdmissionNo());
				}
			}
			
			
			patMedicalDtlVO.setPatCrNo(fb.getPatCrNo());
			patMedicalDtlVO.setEpisodeCode(fb.getSelectedEpiCode());
			
			patMedicalDtlVO.setEmpNo(fb.getNewEmpNo());
			patMedicalDtlVO.setSufferingFrom(fb.getSufferingFrom());
			patMedicalDtlVO.setAdviceDays(fb.getNewAdvDays());
			patMedicalDtlVO.setFromDate(fb.getNewFromDate());
			patMedicalDtlVO.setToDate(fb.getNewToDate());
			patMedicalDtlVO.setMedicalCertificateDesc(fb.getNewMedicalCertificateId());
			fb.setNewAdviceDays(fb.getNewAdvDays());
			
			MedicalCertificateDATA.saveOnBasisRestAdvice(patMedicalDtlVO,fb.getDeptUnitCode(),fb.getGenerationMode(),getUserVO(request));
			fb.setIsOverlapped(MrdConfig.IS_OVERLAPPED_NO);
			objStatus.add(Status.DONE,"","Record Added Successfully");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			fb.setIsOverlapped(MrdConfig.IS_OVERLAPPED_YES);
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
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
	
	
	
	
	/** Giving the Fitnss date On the Basis of Medical Certificate
	 * @param fb
	 * @param request
	 */
	/*public static void giveFitnessDate(MedicalCertificateFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		String fitnessDate="";
		
		try
		{
			int i=Integer.parseInt(fb.getIndex());
			PatMedicalDtlVO[] patMedicalDtlVO=(PatMedicalDtlVO[])session.getAttribute(MrdConfig.MC_ISSUED_MEDICAL_CERTIFICATE_ON_BASIS_EPISODE);
			String toDate=(patMedicalDtlVO[i].getToDate());
			fb.setSufferingFrom(patMedicalDtlVO[i].getSufferingFrom());
			fb.setAdviceDays(patMedicalDtlVO[i].getAdviceDays());
			fb.setFromDate(patMedicalDtlVO[i].getFromDate());
			fb.setToDate(patMedicalDtlVO[i].getToDate());
			fb.setConsultantName(patMedicalDtlVO[i].getEmpName());
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(toDate));
			c.add(Calendar.DATE, 1);			// Getting the Fitness Date Which is Next Day of Medical Certificate End Date
			fitnessDate = sdf.format(c.getTime());  
			fb.setFitnessDate(fitnessDate);
			objStatus.add(Status.TRANSINPROCESS);
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
	}*/
	
	/** Saving the Fitness Date
	 * @param fb
	 * @param request
	 */
	/*public static void saveFitnessDate(MedicalCertificateFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		PatFitnessDtlVO patFitnessDtlVO=new PatFitnessDtlVO();
		
		
		try
		{
			int i=Integer.parseInt(fb.getIndex());
			PatMedicalDtlVO[] patMedicalDtlVO=(PatMedicalDtlVO[])session.getAttribute(MrdConfig.MC_ISSUED_MEDICAL_CERTIFICATE_ON_BASIS_EPISODE);
			
			patFitnessDtlVO.setPatCrNo(fb.getPatCrNo());
			patFitnessDtlVO.setEpisodeCode(fb.getSelectedEpiCode());
			patFitnessDtlVO.setEpisodeVisitNo(patMedicalDtlVO[i].getEpisodeVisitNo());
			patFitnessDtlVO.setSufferingFrom(patMedicalDtlVO[i].getSufferingFrom());
			patFitnessDtlVO.setFitnessDate(fb.getFitnessDate());
			patFitnessDtlVO.setPatAdmNo(patMedicalDtlVO[i].getPatAdmNo());
			patFitnessDtlVO.setMedicalCertificateDesc(patMedicalDtlVO[i].getMedicalCertificateName());
			patFitnessDtlVO.setMedicalCertificateId(patMedicalDtlVO[i].getMedicalCertificateId());
			patFitnessDtlVO.setFitnessCertificateDesc(fb.getFitnessCertificateId());
			
			
			MedicalCertificateDATA.saveFitnessDate(patFitnessDtlVO,fb.getDeptUnitCode(),fb.getGenerationMode(),getUserVO(request));
			objStatus.add(Status.DONE,"","Fitness Date Added Successfully");
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
	}*/
	
	/** Changing the Duration of Medical Certificate
	 * @param fb
	 * @param request
	 */
	/*public static void extendMedicalCertificate(MedicalCertificateFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		EpisodeRestAdviceVO epiRestAdviceVO=new EpisodeRestAdviceVO();
		
		try
		{
			epiRestAdviceVO.setPatCrNo(fb.getPatCrNo()) ;
			epiRestAdviceVO.setEpisodeCode(fb.getSelectedEpiCode());
			String diagCodeType=(String)session.getAttribute(MrdConfig.MC_DIAGNOSIS_CODE_TYPE);
			
			int i=Integer.parseInt(fb.getIndex());
			PatMedicalDtlVO[] patMedicalDtlVO=(PatMedicalDtlVO[])session.getAttribute(MrdConfig.MC_ISSUED_MEDICAL_CERTIFICATE_ON_BASIS_EPISODE);
			// Getting the List of diagnosis of the Patient
			List lstDiag=MedicalCertificateDATA.getPatDiagnosisList(diagCodeType,epiRestAdviceVO,getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.MC_PATIENT_DIAGNOSIS_LIST, lstDiag);
			
			fb.setSufferingFrom(patMedicalDtlVO[i].getSufferingFrom());
			fb.setModAdviceDays(patMedicalDtlVO[i].getAdviceDays());
			fb.setModFromDate(patMedicalDtlVO[i].getFromDate());
			fb.setModToDate(patMedicalDtlVO[i].getToDate());
			fb.setMedicalCertificateDesc(patMedicalDtlVO[i].getMedicalCertificateName());
			fb.setConsultantName(patMedicalDtlVO[i].getEmpName());
			
			if(lstDiag.size()==0)
				objStatus.add(Status.TRANSINPROCESS,"","No Diagnosis Found");
			else
				objStatus.add(Status.TRANSINPROCESS);
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
	}*/
	
	/** Saving the Duration of Medical Certificate
	 * @param fb
	 * @param request
	 */
	/*public static void saveExtendMedicalCertificate(MedicalCertificateFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		PatMedicalDtlVO patMedDtlVO=new PatMedicalDtlVO();
		
		try
		{
			int i=Integer.parseInt(fb.getIndex());
			PatMedicalDtlVO[] patMedicalDtlVO=(PatMedicalDtlVO[])session.getAttribute(MrdConfig.MC_ISSUED_MEDICAL_CERTIFICATE_ON_BASIS_EPISODE);
			
			patMedDtlVO.setPatCrNo(fb.getPatCrNo());
			patMedDtlVO.setEpisodeCode(fb.getSelectedEpiCode());
			patMedDtlVO.setEpisodeVisitNo(patMedicalDtlVO[i].getEpisodeVisitNo());
			patMedDtlVO.setMedicalCertificateDesc(patMedicalDtlVO[i].getMedicalCertificateName());
			patMedDtlVO.setMedicalCertificateId(patMedicalDtlVO[i].getMedicalCertificateId());
			patMedDtlVO.setPatAdmNo(patMedicalDtlVO[i].getPatAdmNo());
			patMedDtlVO.setSufferingFrom(fb.getSufferingFrom());
			patMedDtlVO.setAdviceDays(fb.getModAdviceDays());
			patMedDtlVO.setFromDate(patMedicalDtlVO[i].getFromDate());
			patMedDtlVO.setToDate(fb.getModToDate());
			patMedDtlVO.setRemarks(fb.getRemarks());
			patMedDtlVO.setSNo(patMedicalDtlVO[i].getSNo());
			patMedDtlVO.setEmpNo(patMedicalDtlVO[i].getEmpNo());
			
			MedicalCertificateDATA.saveExtendMedicalCertificate(patMedDtlVO,getUserVO(request));
			fb.setIsOverlapped(MrdConfig.IS_OVERLAPPED_NO);
			objStatus.add(Status.DONE,"","Record Modified Successfully");
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			fb.setIsOverlapped(MrdConfig.IS_OVERLAPPED_YES);
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
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
	}*/
	
	/** Searching the Diagnosis
	 * @param fb
	 * @param _rq
	 */
	public static void searchCode(MedicalCertificateFB fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		List icdCodes = new ArrayList();
		try
		{
			String searchCode = fb.getSearchCode();
			String searchDisease = fb.getSearchDisease();
			if (fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_ICD_CODE))
			{
				if (searchCode == null || searchCode.trim().equals("")) if (searchDisease == null || searchDisease.trim().equals("")) System.out
						.println("gfhfh");
				else icdCodes = OpdDiagnosisDATA.getDiseaseName(searchDisease, getUserVO(_rq));
				else icdCodes = OpdDiagnosisDATA.getIcdCodes(searchCode, getUserVO(_rq));

				if (icdCodes == null) throw new HisRecordNotFoundException("No Code Exists");
				WebUTIL.getSession(_rq).setAttribute(OpdConfig.EssentialBO_DIAGNOSIS_CODE_LIST_UNIT_WISE, icdCodes);
			}
			if (fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_HOSPITAL_CODE))
			{
				if (searchCode == null || searchCode.trim().equals("")) if (searchDisease == null || searchDisease.trim().equals("")) System.out
						.println("gfhfh");
				else icdCodes = OpdDiagnosisDATA.getHospitalDiagnosisName(searchDisease, getUserVO(_rq));
				else icdCodes = OpdDiagnosisDATA.getHospitalDiagnosisCodes(searchCode, getUserVO(_rq));

				if (icdCodes == null) throw new HisRecordNotFoundException("No Code Exists");
				WebUTIL.getSession(_rq).setAttribute(OpdConfig.EssentialBO_HOSPITAL_DIAGNOSIS_CODE_LIST, icdCodes);
			}
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.NEW, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
	
	/** Populating the Diagnosis
	 * @param _fb
	 * @param _rq
	 * @param _resp
	 */
	public static void populate(MedicalCertificateFB _fb, HttpServletRequest _rq, HttpServletResponse _resp)
	{
		List listDiagnosis = null;
		if (_fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_ICD_CODE)) listDiagnosis = (List) WebUTIL.getSession(_rq).getAttribute(OpdConfig.EssentialBO_DIAGNOSIS_CODE_LIST_UNIT_WISE);
		if (_fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_HOSPITAL_CODE)) listDiagnosis = (List) WebUTIL.getSession(_rq).getAttribute(OpdConfig.EssentialBO_HOSPITAL_DIAGNOSIS_CODE_LIST);

		Entry ent = (Entry) listDiagnosis.get(Integer.parseInt(_fb.getSelectedCode()));

		String label = ent.getLabel();
		String value = (ent.getValue()).trim();
		try
		{
			_fb.setSufferingFrom(label);
			PrintWriter writer = _resp.getWriter();
			writer.write(label + '^' + value);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			
		}
	}
	
	public static boolean checkEmployeeId(MedicalCertificateFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean exist=false;
		String maxDays="";
		try
		{
			if(fb.getEmpMappingFlag().equals(Config.MEDICAL_CERTIFICATE_EMPLOYEE_MAPPING_ONLINE))
			{
				UserVO userVO=ControllerUTIL.getUserVO(request);
				//userVO.setUserEmpID("PGIPER10000004");
				if(userVO.getUserEmpID()==null)
				{	
					exist=true;
					throw new Exception("First Map The User With The Employee ID");
				}
				else
				{
					maxDays=MedicalCertificateDATA.getEmpMaxDaysOnline(userVO);
					fb.setOnlineEmpMaxDays(maxDays);
					fb.setEmpConsultantName(userVO.getUserName());
				}	
			}	
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			objStatus.add(Status.DONE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return exist;
	}
	
	public static boolean getMedicalCertificatePatReqList(MedicalCertificateFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		
		try
		{
			setSysdate(request);
			
			Map map=MedicalCertificateDATA.getMedicalCertificatePatReqList(getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
		
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
		return flag;
	}
	/* Medical and fitness certificate issue patient all details */
	
	public static boolean getMedicalCertificateIssuePatDtl(MedicalCertificateFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		String billNoQty="";
		HttpSession session = request.getSession();
		
		try
		{
			PatMedicalDtlVO patMedicalDtlVO=new PatMedicalDtlVO(); 
			setSysdate(request);
			String patDtl[]=fb.getSelectRecord().split("@");
			fb.setCertificateId(patDtl[0]);
			fb.setRecordType(patDtl[1]);
			fb.setPatCrNo(patDtl[2]);
			fb.setDiagnosis(patDtl[3]);
			fb.setSurgery(patDtl[4]);
			fb.setAdviceDays(patDtl[5]);
			fb.setFitnessDate(patDtl[6]);
			fb.setAdvisedBy(patDtl[7]);
			fb.setDuration(patDtl[8]);
			if(patDtl[9]==null || patDtl[9].equals(""))
			{
				fb.setRemarks("");
			}
			else
			{
				fb.setRemarks(patDtl[9]);
			}
			
			fb.setFromDate(patDtl[10]);
			fb.setToDate(patDtl[11]);
			
			if(patDtl[12]==null || patDtl[12].equals(""))
			{
				fb.setPreviousMcNo("");
			}
			else
			{
				fb.setPreviousMcNo(patDtl[12]);
			}
			fb.setGdt_entry_date(patDtl[13]);
			fb.setEmpDesig(patDtl[14]);
			fb.setEmp_dept(patDtl[15]);
			
			//fb.setGdt_entry_date(fb.getGdt_entry_date().split(""));
			HelperMethods.populate(patMedicalDtlVO, fb);
			Map map=MedicalCertificateDATA.getMedicalCertificateIssuePatDtl(patMedicalDtlVO,getUserVO(request));
			billNoQty=MedicalCertificateDATA.getBillNoQtyDtl(patMedicalDtlVO,getUserVO(request));
			fb.setBillNo(billNoQty.replace("^", "#").split("#")[0]);
			fb.setQuantity(billNoQty.replace("^", "#").split("#")[1]);
			System.out.println("billNo:::::"+fb.getBillNo()+"qty:::"+fb.getQuantity());
			if(fb.getBillNo() != null)
			{
				patMedicalDtlVO.setBillNo(fb.getBillNo());
			}
			else
			{
				patMedicalDtlVO.setBillNo("");
			}
			WebUTIL.setAttributeInSession(request, MrdConfig.PAT_MEDICAL_VO_MEDICAL_CERTIFICATE, patMedicalDtlVO);
			WebUTIL.setMapInSession(map, request);
			//objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
		
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
		return flag;
	}
	/* Medical and fitness certificates save details after billing*/
	public static boolean saveMedicalCertificateIssueDtl(MedicalCertificateFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		HttpSession session = request.getSession();
		
		try
		{
			PatMedicalDtlVO patMedicalDtlVO=new PatMedicalDtlVO(); 
			HelperMethods.populate(patMedicalDtlVO, fb);
			setSysdate(request);
			MedicalCertificateDATA.saveMedicalCertificateIssueDtl(patMedicalDtlVO,getUserVO(request));
			//objStatus.add(Status.NEW);\
			objStatus.add(Status.DONE,"","Record Added Successfully");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
		
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
		return flag;
	}
	

	public static boolean setCertificateData(MedicalCertificateFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		try
		{
			setSysdate(_rq);
			HttpSession session = _rq.getSession();
			PatMedicalDtlVO voMReq = (PatMedicalDtlVO) session.getAttribute(MrdConfig.PAT_MEDICAL_VO_MEDICAL_CERTIFICATE); //-----
			if(voMReq.getPreviousMcNo().equals("null"))
			{
				voMReq.setPreviousMcNo("");
			}
			if(voMReq.getRemarks().equals("null"))
			{
				voMReq.setRemarks("");
			}
			UserVO userVO=getUserVO(_rq);
			
			//voMBReq.setEntryDate(_rq.getParameter("handoverDate"));
			//voMBReq.setFinalRemark(_rq.getParameter("remarks"));
			//voMBReq.setOrgName(_rq.getParameter("orgName"));
			//voMBReq.setOpinion(_rq.getParameter("opinion"));
			//voMReq.setCertificateId(_rq.getParameter("certificateNo"));			
			
			String strPatCrRq= _rq.getParameter("patCrNo");
			if(_fb.getPatCrNo().equals("")||_fb.getPatCrNo()==null)
			{	
				_fb.setPatCrNo(strPatCrRq);
			}
			InpatientDetailUTL.getInpatientDetailByCrNo(_fb, _rq);
			PatientDetailVO patientVO=(PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
		

			String Gender=patientVO.getPatGender();
			String Age= patientVO.getPatAge();
			
			String AgeGender = Gender.concat(Age);
			
			System.out.println(AgeGender+"Age Gender of Patient");
			

			GenericTemplateUtility.setVOInInfoBean(_rq, voMReq);
			GenericTemplateUtility.setVOInInfoBean(_rq, userVO.getStrHospitalMstVo());
			GenericTemplateUtility.setVOInInfoBean(_rq, patientVO);
			objStatus.add(Status.TRANSINPROCESS);
	 	}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return true;
	}	
	
}
