/**
## Copyright Information		: 	C-DAC, Noida  
## Project Name					: 	HIS-NIMS
## Name of Developer		 	: 	Akash Singh
## Module Name					: 	MRD
## Process/Database Object Name	:	Medical Certificate Request
## Purpose						:	online request raise from OPD Doctor Desk or OPD Bay Desk or IPD Doctor Desk. Doctor provide request slip to patient with complete medical certificate information like rest dates, fitness dates etc.
## Date of Creation				: 	19-November-2014
## Modification Log				:					
##		Modify Date				: 	
##		Reason	(CR/PRS)		: 
##		Modify By				: 
*/
package mrd.transaction.controller.utl;

import hisglobal.utility.Entry;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.PatMedicalDtlVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.MedicalCertificateRequestDATA;
import mrd.transaction.controller.fb.MedicalCertificateRequestFB;

public class MedicalCertificateRequestUTL extends ControllerUTIL
{
	/**  Getting The Patient Detail & All Episode of the Patient
	 * @param fb
	 * @param request
	 */
	public static void setEssentials(MedicalCertificateRequestFB _fb, HttpServletRequest _request) 
	{
		HttpSession session = _request.getSession();
		Status objStatus = new Status();	
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);	// Required for Current Date
			PatMedicalDtlVO patMedicalDtlVO = new PatMedicalDtlVO();
			WebUTIL.populate(patMedicalDtlVO, _fb);
			System.out.println("episodeCode  :"+patMedicalDtlVO.getEpisodeCode());
			System.out.println("episodeVisitNo  :"+patMedicalDtlVO.getEpisodeVisitNo());
			System.out.println("getDepartmentUnitCode  :"+patMedicalDtlVO.getDepartmentUnitCode());
			System.out.println("patCrNo  :"+patMedicalDtlVO.getPatCrNo());
			
			Map<String, Object> mapEssential  = MedicalCertificateRequestDATA.getEssentials(patMedicalDtlVO, userVO);
			String Diagnosis = MedicalCertificateRequestDATA.getPatDiagnosisList(patMedicalDtlVO, userVO);
			_fb.setDiagnosis(Diagnosis);
			WebUTIL.setMapInSession(mapEssential, _request);
			List<Entry> lstAdvisedBy = (List<Entry>) mapEssential.get(MrdConfig.MEDICAL_CERTIFICATE_REQUEST_ADVISEDBY_DETAIL);
			for(Entry entry : lstAdvisedBy)
				if (userVO.getUserEmpID().equals(entry.getValue()))
				{
					_fb.setAdvisedBy(entry.getValue().toString());
					break;
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
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	public static boolean saveMedicalCertificateRequest(MedicalCertificateRequestFB objFB, HttpServletRequest request) 
	{
		boolean flgSave = true;
		Status objStatus=new Status();
		PatMedicalDtlVO patMedicalDtlVO = new PatMedicalDtlVO();
		try
		{	

			WebUTIL.populate(patMedicalDtlVO, objFB);
			System.out.println("episodeCode  :"+patMedicalDtlVO.getEpisodeCode());
			System.out.println("episodeVisitNo  :"+patMedicalDtlVO.getEpisodeVisitNo());
			System.out.println("getDepartmentUnitCode  :"+patMedicalDtlVO.getDepartmentUnitCode());
			System.out.println("patCrNo  :"+patMedicalDtlVO.getPatCrNo());
			UserVO userVO = getUserVO(request);
			MedicalCertificateRequestDATA.saveMedicalCertificateRequest(patMedicalDtlVO, userVO);
		}
		catch (HisDuplicateRecordException e)
		{
			flgSave =  true;
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			flgSave =  true;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Billing Tarrif Not Avilable");
		}
		catch (HisApplicationExecutionException e)
		{
			flgSave =  true;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			flgSave =  true;
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e)
		{
			flgSave =  true;
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flgSave;
	}

	public static void setFitnessEssentials(MedicalCertificateRequestFB objFB,HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		PatMedicalDtlVO patMedicalDtlVO = new PatMedicalDtlVO();
		WebUTIL.populate(patMedicalDtlVO, objFB);
		System.out.println("episodeCode  :"+patMedicalDtlVO.getEpisodeCode());
		System.out.println("episodeVisitNo  :"+patMedicalDtlVO.getEpisodeVisitNo());
		System.out.println("getDepartmentUnitCode  :"+patMedicalDtlVO.getDepartmentUnitCode());
		System.out.println("patCrNo  :"+patMedicalDtlVO.getPatCrNo());
		System.out.println("selectedFitnessRow"+objFB.getSelectedFitnessRow());
		List patCertificateDetail = (List)session.getAttribute(MrdConfig.PREVIOUS_MEDICAL_CERTIFIATE_DTL);
		if(patCertificateDetail!=null && patCertificateDetail.size()>0)
		{
			PatMedicalDtlVO vo = (PatMedicalDtlVO)patCertificateDetail.get(Integer.parseInt(objFB.getSelectedFitnessRow()));
			objFB.setDiagnosis(vo.getDiagnosis());
			objFB.setSurgery(vo.getSurgery());
			objFB.setAdviceDays(vo.getAdviceDays());
			objFB.setFromDate(vo.getFromDate());
			objFB.setToDate(vo.getToDate());
			objFB.setFitnessDate(vo.getFitnessDate());
			objFB.setRemarks(vo.getRemarks());
			objFB.setAdvisedBy(vo.getAdvisedBy());
			objFB.setMedicalFitnessFlag("1");
			objFB.setMedicalCertificateId(vo.getMedicalCertificateId());
		}
		
	}
}
