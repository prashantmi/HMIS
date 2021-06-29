package mrd.transaction.controller.utl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import mrd.MrdConfig;
import mrd.transaction.controller.data.FitnessCertificateDATA;
import mrd.transaction.controller.fb.FitnessCertificateFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatFitnessDtlVO;
import hisglobal.vo.PatMedicalDtlVO;

public class FitnessCertificateUTL extends ControllerUTIL
{
	/** Getting The List of All The Episodes of the Patient
	 * @param fb
	 * @param request
	 */
	public static void getAllEpisodeOfThePatient(FitnessCertificateFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		
		try
		{
			EpisodeVO[] patEpisodeVO=FitnessCertificateDATA.getAllEpisodeOfThePatient(fb.getPatCrNo(),getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.MC_PATIENT_ALL_EPISODE_VO_ARR ,patEpisodeVO );
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
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
	
	/** Getting the List of Medical Certificate Generated for the Patient On a particular Episode
	 * @param fb
	 * @param request
	 */
	public static void getMCListOfThePat(FitnessCertificateFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession(); 
		
		try
		{
			EpisodeVO[] patEpisodeVO=(EpisodeVO[])session.getAttribute(MrdConfig.MC_PATIENT_ALL_EPISODE_VO_ARR);
			for(int i=0;i<patEpisodeVO.length;i++)
			{
				if(fb.getSelectedEpiCode().equals(patEpisodeVO[i].getEpisodeCode()))
				{
					fb.setDeptUnitCode(patEpisodeVO[i].getDepartmentUnitCode());
				}	
			}
			
			PatMedicalDtlVO[] patMedicalDtlVO=FitnessCertificateDATA.getMCListOfThePat(fb.getPatCrNo(),fb.getSelectedEpiCode(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.FC_PATIENT_MEDICAL_CERTIFICATE_LIST_BY_EPISODE, patMedicalDtlVO);
			if(patMedicalDtlVO.length>0)
				objStatus.add(Status.TRANSINPROCESS);
			else
				objStatus.add(Status.TRANSINPROCESS,"","No Medical Certificate Found");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
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
	
	/** Getting the Fitness Date of the Patient Based On a Particular Medical Certificate
	 * @param fb
	 * @param request
	 */
	public static void showFitnessDate(FitnessCertificateFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		String fitnessDate="";
		
		try
		{
			int i=Integer.parseInt(fb.getSelectedMC());
			PatMedicalDtlVO[] patMedicalDtlVO=(PatMedicalDtlVO[])session.getAttribute(MrdConfig.FC_PATIENT_MEDICAL_CERTIFICATE_LIST_BY_EPISODE);
			String toDate=(patMedicalDtlVO[i].getToDate());
			fb.setSufferingFrom(patMedicalDtlVO[i].getSufferingFrom());
			fb.setAdviceDays(patMedicalDtlVO[i].getAdviceDays());
			fb.setFromDate(patMedicalDtlVO[i].getFromDate());
			fb.setToDate(patMedicalDtlVO[i].getToDate());
			
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
	}	
	
	/** Saving the Fitness Date of The Patient
	 * @param fb
	 * @param request
	 */
	public static void saveFitnessDate(FitnessCertificateFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		PatFitnessDtlVO patFitnessDtlVO=new PatFitnessDtlVO();
		
		try
		{
			/*EpisodeVO[] patEpisodeVO=(EpisodeVO[])session.getAttribute(MrdConfig.MC_PATIENT_ALL_EPISODE_VO_ARR);
			for(int i=0;i<patEpisodeVO.length;i++)
			{
				if(fb.getSelectedEpiCode().equals(patEpisodeVO[i].getEpisodeCode()))
				{
					if(patEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_IPD))
						patFitnessDtlVO.setPatAdmNo(patEpisodeVO[i].getAdmissionNo());
				}
			}*/
			int i=Integer.parseInt(fb.getSelectedMC());
			PatMedicalDtlVO[] patMedicalDtlVO=(PatMedicalDtlVO[])session.getAttribute(MrdConfig.FC_PATIENT_MEDICAL_CERTIFICATE_LIST_BY_EPISODE);
			
			patFitnessDtlVO.setPatCrNo(fb.getPatCrNo());
			patFitnessDtlVO.setEpisodeCode(fb.getSelectedEpiCode());
			patFitnessDtlVO.setEpisodeVisitNo(patMedicalDtlVO[i].getEpisodeVisitNo());
			patFitnessDtlVO.setSufferingFrom(patMedicalDtlVO[i].getSufferingFrom());
			patFitnessDtlVO.setFitnessDate(fb.getFitnessDate());
			patFitnessDtlVO.setPatAdmNo(patMedicalDtlVO[i].getPatAdmNo());
			patFitnessDtlVO.setFitnessCertificateId(fb.getFitnessCertificateId());
			
			FitnessCertificateDATA.saveFitnessDate(patFitnessDtlVO,fb.getDeptUnitCode(),fb.getGenerationMode(),getUserVO(request));
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
}
