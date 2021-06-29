package ehr.visitreason.dataentry;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import ehr.EHRConfig;
import ehr.visitreason.vo.EHRSection_VisitReasonVO;
import ehr.vo.EHRVOUtility;
import emr.vo.EHR_PatEncounterDetailsVO;


public class EHRSection_VisitReasonUTL extends ControllerUTIL{

	public static void getEssentials(EHRSection_VisitReasonFB _fb,
			HttpServletRequest _request, HttpServletResponse response) {
		
		HttpSession session = _request.getSession();
		Status objStatus = new Status();
		EHRSection_VisitReasonVO visitVO[] = null;
		EHRSection_VisitReasonVO visitVOPrint = new EHRSection_VisitReasonVO();
		Map mpEssential = new HashMap();
		//EhrUniPagePrescriptionFB fb = new EhrUniPagePrescriptionFB();
		try
		{
			UserVO userVO = getUserVO(_request);
			HospitalMstVO hospVO = getHospitalVO(_request);
			setSysdate(_request);	// Required for Current Date

			//_fb.setDeskType((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE));

			//PatientDetailVO voDP = null;
			PatientDetailVO voDP = new PatientDetailVO();
			HelperMethods.populate(voDP, _fb);
		
			// Getting Diagnosis Essentials
			if(voDP!=null)
			{
				visitVO = EHRSection_VisitReasonDATA.getEssentials(voDP, userVO);	
				
				_fb.setEhrVisitReason(null);
				for(int i=0; i<visitVO.length; i++)
				{
					_fb.setEhrVisitReason(visitVO[i].getEhrVisitReason());
					_fb.setSnomdPTVisitReason(visitVO[i].getSnomdPTVisitReason());
					_fb.setSnomdCIdVisitReason(visitVO[i].getSnomdCIdVisitReason());
					_fb.setPresentIllnessHistory(visitVO[i].getPresentIllnessHistory());
					visitVOPrint.setEhrVisitReason(visitVO[i].getEhrVisitReason());
					visitVOPrint.setEntryDate(visitVO[i].getEntryDate());
					
					if(visitVO[i].getPresentIllnessHistory()!=null && !visitVO[i].getPresentIllnessHistory().equals(""))
					{
						visitVOPrint.setPresentIllnessHistory(visitVO[i].getPresentIllnessHistory());
					}
				}
				
				if(_fb.getEhrVisitReason()!=null && !_fb.getEhrVisitReason().equals(""))
				{
					_fb.setIsSet_OPDNEXTVISITDETAIL("1");
					//System.out.println("neha: Visit Reason := " + _fb.getIsSet_OPDNEXTVISITDETAIL() );
				}
				visitVOPrint.setUsername(userVO.getUserName());
				WebUTIL.getSession(_request).setAttribute(EHRConfig.VISIT_REASON_ESSENTAILS, visitVOPrint);
				WebUTIL.getSession(_request).setAttribute(EHRConfig.HOSPITAL_DEATILS, hospVO);
				
				
				EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) WebUTIL.getSession(_request).getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
				patencountervo.setListSaveAllVisitReason(visitVOPrint);
				WebUTIL.getSession(_request).setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, patencountervo);
				
				//Added by Vasu on 11.Dec.2019 to print History of present illness on prescription
				
				
				EHRVOUtility.setCheifComplaintsDetailVO(_request, _fb.getPatCrNo(), visitVOPrint); //Added by Vasu on 19.Dec.2018
			
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

	public static String saveEhrVisitdetails(HttpServletRequest request,
			HttpServletResponse response, EHRSection_VisitReasonFB _fb) {

		String isSave = "true";
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		DailyPatientVO selectedPatientVO = new DailyPatientVO();
		EHRSection_VisitReasonVO visitVO = new EHRSection_VisitReasonVO();
		try
		{

			PatientDetailVO selectedPatientDtlVO =null;
			selectedPatientDtlVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			HelperMethods.populatetToNullOrEmpty(visitVO, _fb);
			
			visitVO.setAdmissionNo(selectedPatientDtlVO.getPatAdmNo());
			visitVO.setEhrVisitReason(_fb.getEhrVisitReason());
			visitVO.setPresentIllnessHistory(_fb.getPresentIllnessHistory());
				
			EHRSection_VisitReasonDATA.saveDetails(visitVO, getUserVO(request));
			if(visitVO.getEhrVisitReason()=="")
			{
				_fb.setIsSet_OPDNEXTVISITDETAIL("0");
			}
			EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) WebUTIL.getSession(request).getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
			patencountervo.setListSaveAllVisitReason(visitVO);
			WebUTIL.getSession(request).setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, patencountervo);
		
			
     		objStatus.add(Status.DONE, "Record Saved", "");
		}

		
		catch (HisDataAccessException e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR, e.getMessage(), "");
			System.out.print(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			isSave = "false";
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			System.out.print(e.getMessage());
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return isSave;
		
	}
	
	public static void saveDetails(HttpServletRequest _rq,HttpServletResponse response, EHRSection_VisitReasonFB _fb) 
	{
		String flag=EHRSection_VisitReasonUTL.saveEhrVisitdetails(_rq,response,_fb);
		
		try
		{
			writeResponse(response, flag);
		}
		catch (Exception e) {
			// //LOGGER_INV.log(Level.INFO,e.getMessage());}
			e.printStackTrace();
			
		}
		
		
		
	}
	
	public static void writeResponse(HttpServletResponse resp, String output)
			throws IOException {
		// //LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::writeResponse");

		try {
			resp.reset();
			resp.flushBuffer();
			resp.setContentType("application/json");
			resp.setHeader("Cache-Control", "no-cache");
			resp.getWriter().write(output);
		} catch (Exception e) {
						e.printStackTrace();

		}
	}
	

}
