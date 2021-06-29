package ehr.followup.dataentry;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.PatDischargeReqDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.DischargeRequestDATA;
import inpatient.transaction.controller.fb.DischargeRequestFB;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import registration.RegistrationConfig;
import ehr.EHRConfig;
import ehr.followup.vo.EHRSection_FollowupVO;
import ehr.vo.EHRVOUtility;
import emr.vo.EHR_PatEncounterDetailsVO;


public class EHRSection_FollowupUTL extends ControllerUTIL{

	public static void getEssentials(EHRSection_FollowupFB _fb,
			HttpServletRequest _request, HttpServletResponse response) {
		
		//System.out.println("followupUTIL.setEssentials()");
		HttpSession session = _request.getSession();
		Status objStatus = new Status();
		EHRSection_FollowupVO followVO = new EHRSection_FollowupVO();
		Map mpEssential = new HashMap();
		try
		{
			
			//System.out.println("sysout");
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);	// Required for Current Date
			_fb.setNextVisitDateFlag(InpatientConfig.NEXT_VISIT_SELECTION_DATE);

			// Set Desk Essentials
				// Desk Type & Unit Code
			
			
			_fb.setDeskType((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE));
			//_fb.setDepartmentUnitCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE));
				// Patient Detail
			PatientDetailVO voDP = null;
			/*if(session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST)!=null)
			{
				System.out.println("TAKING VO from :-- DynamicDeskConfig.DYNAMIC_DESK_TYPE");
				PatientDetailVO[] al=(PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < al.length; i++)
				{
					voDP = (PatientDetailVO) al[i];
					if (voDP.getPatCrNo().equals(_fb.getPatCrNo())) break;
				}
				_fb.setEpisodeCode(voDP.getEpisodeCode());
				_fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
				_fb.setAdmissionNo(voDP.getPatAdmNo());
				_fb.setSerialNo(voDP.getSerialNo());
				_fb.setIsConfirmed(voDP.getIsConfirmed());
				_fb.setTriageDuration(voDP.getTriageDuration());
				_fb.setDepartmentUnitCode(voDP.getDepartmentUnitCode());
					// User Detail
				_fb.setLoginSeatId(userVO.getSeatId());
				_fb.setEmpNo(userVO.getUserEmpID());
				
				
			}*/
			//else 
				if(session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO)!=null)
			{
				//System.out.println("TAKING VO from :-- DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO");
				voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
				_fb.setEpisodeCode(voDP.getEpisodeCode());
				_fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
				_fb.setAdmissionNo(voDP.getPatAdmNo());
				_fb.setSerialNo(voDP.getSerialNo());
				_fb.setIsConfirmed(voDP.getIsConfirmed());
				_fb.setTriageDuration(voDP.getTriageDuration());
				_fb.setDepartmentUnitCode(voDP.getDepartmentUnitCode());
					// User Detail
				_fb.setLoginSeatId(userVO.getSeatId());
				_fb.setEmpNo(userVO.getUserEmpID());
				_fb.setIsUnitAppointmentBased(voDP.getIsUnitAppointmentBased());

			}
				else
				{
					 voDP = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
					    _fb.setEpisodeCode(voDP.getEpisodeCode());
						_fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
						_fb.setAdmissionNo(voDP.getPatAdmNo());
						_fb.setSerialNo(voDP.getSerialNo());
						_fb.setIsConfirmed(voDP.getIsConfirmed());
						_fb.setTriageDuration(voDP.getTriageDuration());
						_fb.setDepartmentUnitCode(voDP.getDepartmentUnitCode());
							// User Detail
						_fb.setLoginSeatId(userVO.getSeatId());
						_fb.setEmpNo(userVO.getUserEmpID());
						_fb.setIsUnitAppointmentBased(voDP.getIsUnitAppointmentBased());
				}
			//voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(_fb.getDeskType()!=null && _fb.getDeskType().equals(DynamicDeskConfig.DESK_TYPE_CASUALITY_DESK))
			{
				if(voDP.getIsBroughtDead()!=null && voDP.getIsBroughtDead().equals(RegistrationConfig.PATIENT_BROUGHT_DEAD_TRUE) || voDP.getIsDead().equals(RegistrationConfig.PATIENT_STATUS_CODE_DEAD))
					_fb.setIsPatDead(EHRConfig.YES);
				else
					_fb.setIsPatDead(EHRConfig.NO);
			}
			else
				_fb.setIsPatDead(EHRConfig.NO);
			
			// Get Visit Summary Essentials
			
			 //WebUTIL.populate(followVO, _fb);
			//HelperMethods.populate(followVO,_fb);
			
			followVO.setEpisodeCode(_fb.getEpisodeCode());
			followVO.setPatCrNo(_fb.getPatCrNo());
			followVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			
			mpEssential = EHRSection_FollowupDATA.getEssentials(followVO, userVO);
			if(mpEssential != null)
			{
				_fb.setIsSetFOLLOWUP("1");
			}
			
			List<EHRSection_FollowupVO> EHRSection_FollowupVO = (List<EHRSection_FollowupVO>) mpEssential.get(EHRConfig.OPD_ESSENTIAL_ALL_VISIT_OF_EPISODE_LIST);
			List<EHRSection_FollowupVO> EHRSection_FollowupVO1 = (List<EHRSection_FollowupVO>) mpEssential.get(EHRConfig.OPD_ESSENTIAL_ALL_VISIT_SUMMARY_OF_CURRENT_EPISODE_VISIT_LIST);
			List<EHRSection_FollowupVO> EHRSection_FollowupVO2 = (List<EHRSection_FollowupVO>) mpEssential.get(EHRConfig.OPD_ESSENTIAL_ALL_KEYWORDS_LIST);
			
			//EHRVOUtility.setFollowUpDetailVO(_request, _fb.getPatCrNo(), EHRSection_FollowupVO);//Added by Vasu on 20.Nov.2018
			EHRSection_FollowupVO eVO = null;
			eVO = EHRSection_FollowupVO.get(0); 
			
			EHRSection_FollowupVO eVOPrint = null;
			if (EHRSection_FollowupVO1 != null) {
	            eVOPrint = (EHRSection_FollowupVO)EHRSection_FollowupVO1.get(0);
	            if (eVOPrint.getRequestByName() != null && eVOPrint.getRequestByName().equalsIgnoreCase("--/EMP NAME NOT FOUND")) {
	               eVOPrint.setRequestByName(getUserVO(_request).getUsrName());
	               eVOPrint.setRequestById(getUserVO(_request).getUserEmpID());
	            } else if (eVOPrint.getRequestByName() != null && !eVOPrint.getRequestByName().equalsIgnoreCase("--/EMP NAME NOT FOUND")) {
	               eVO.setRequestByName(eVOPrint.getRequestByName());
	               eVO.setRequestById(eVOPrint.getRequestById());
	            } else if (eVOPrint.getRequestByName() == null || eVOPrint.getRequestByName().equals("")) {
	               eVO.setRequestByName(getUserVO(_request).getUsrName());
	               eVO.setRequestById(getUserVO(_request).getUserEmpID());
	               eVOPrint.setRequestByName(getUserVO(_request).getUsrName());
	               eVOPrint.setRequestById(getUserVO(_request).getUserEmpID());
	            }
	         }
			eVOPrint.setUsername(userVO.getUsrName());
			WebUTIL.getSession(_request).setAttribute(EHRConfig.FOLLOWUP_ESSENTAILS, eVOPrint);
			//Added by Vasu on 15.April.2019
			EHRSection_FollowupVO eVOPrint1 = null;
			eVOPrint1 = EHRSection_FollowupVO.get(0);
			if(eVOPrint1.getUnitConsultant()!=null && eVOPrint1.getUnitConsultant().equalsIgnoreCase("--/EMP NAME NOT FOUND"))
			{
				eVOPrint1.setUnitConsultant("-");
				
			}
			WebUTIL.getSession(_request).setAttribute(EHRConfig.FOLLOWUP_ESSENTAILS_NEW, eVOPrint1);
			//End Vasu
			EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) WebUTIL.getSession(_request).getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
			patencountervo.setListSaveAllFollowUp(eVOPrint);
			WebUTIL.getSession(_request).setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, patencountervo);
		
			
			WebUTIL.populate(_fb, eVO);
			
	
			for(int i=0;i<EHRSection_FollowupVO1.size();i++)
			{
				EHRSection_FollowupVO lstSummary=(EHRSection_FollowupVO)EHRSection_FollowupVO1.get(i);
				_fb.setVisitReason(lstSummary.getVisitReason());
				
			}	
			ArrayList<String> ar = new ArrayList<String>();
			String keyWords="";
			String keyWordId="";
			for(int i=0;i<EHRSection_FollowupVO2.size();i++)
			{
				EHRSection_FollowupVO keywordVO=(EHRSection_FollowupVO)EHRSection_FollowupVO2.get(i);
				keywordVO.getEpisodeKeyword();
				ar.add(keywordVO.getEpisodeKeyword());
				keyWords+=keywordVO.getEpisodeKeyword()+"##";
				keyWordId+=keywordVO.getEpisodeKeywordID()+"##";
				
			}	
		
			//System.out.println("ar::"+ar);
			_fb.setEpisodeKeywords(keyWords);
			_fb.setEpisodeKeywords(keyWords);
			
			String [] tempKeys = ar.toArray(new String[EHRSection_FollowupVO2.size()]);//(String[]) ar.toArray();
			//System.out.println("tempkeys:::"+tempKeys);
			_fb.setEpisodeKeyword(tempKeys);
			EHRSection_FollowupVO firstEpisodeVO = null, lastEpisodeVO = null;
			EHRSection_FollowupVO latestSummaryVO = null;
			if(EHRSection_FollowupVO.size()>0)
			{
				firstEpisodeVO = EHRSection_FollowupVO.get(EHRSection_FollowupVO.size()-1);
				EHRSection_FollowupVO.remove(0);
			}
				
			if(firstEpisodeVO!=null)
			{
				_fb.setEpisodeDate(firstEpisodeVO.getEpisodeDate());
				_fb.setEpisodeTypeCode(firstEpisodeVO.getEpisodeTypeCode());
			}
			
			if(EHRSection_FollowupVO1.size()>0)
			{
				if(EHRSection_FollowupVO1.get(0).getEpisodeVisitNo().equalsIgnoreCase(_fb.getEpisodeVisitNo()))
				{
					latestSummaryVO = EHRSection_FollowupVO1.get(0);
					EHRSection_FollowupVO1.remove(latestSummaryVO);
				}
				else if(EHRSection_FollowupVO.size()>0)
					lastEpisodeVO = EHRSection_FollowupVO.get(0);  
				else if(firstEpisodeVO!=null)
					lastEpisodeVO = firstEpisodeVO;
			}
			if(latestSummaryVO!=null)
			{
				
				_fb.setIsEpisodeAlreadyOpen(latestSummaryVO.getEpisodeIsOpen());
				if(latestSummaryVO.getEpisodeNextVisitDate()!=null && !latestSummaryVO.getEpisodeNextVisitDate().equals(""))
				{
				_fb.setEpisodeNextVisitDate(latestSummaryVO.getEpisodeNextVisitDate());	
				_fb.setPatNextAptNo(latestSummaryVO.getPatNextAptNo());
				_fb.setPatNextAptSlot(latestSummaryVO.getPatNextAptSlot());
				
				}
			if(latestSummaryVO.getEpisodeKeywords()!=null)
			{
				
				String keyWord=latestSummaryVO.getEpisodeKeywords();
				_request.setAttribute("keywordData", keyWord);
				_fb.setKeywords(keyWord);
			}
			else
			{
				_request.setAttribute("keywordData", "");
			}
				_fb.setEpisodeIsOpen(latestSummaryVO.getEpisodeIsOpen());
				//System.out.println("keywordData:::"+_request.getAttribute("keywordData"));
				//_fb.setEpisodeKeywords(keyWord);
				_fb.setVisitNotes(latestSummaryVO.getVisitNotes());
				_fb.setNextVisitDuration(latestSummaryVO.getNextVisitDuration());
				//_fb.setNextVisitCriteria(null);
				_fb.setNextVisitDurationCriteria(null);
				if(latestSummaryVO.getNextVisitCriteria()!=null)
				{
					if(!latestSummaryVO.getNextVisitCriteria().equalsIgnoreCase(EHRConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS) && 
							!latestSummaryVO.getNextVisitCriteria().equalsIgnoreCase(EHRConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE) )
					{
						_fb.setNextVisitCriteria(EHRConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS);
						_fb.setNextVisitDurationCriteria(latestSummaryVO.getNextVisitCriteria());
					}
					else
						_fb.setNextVisitCriteria(latestSummaryVO.getNextVisitCriteria());
				}
				if(latestSummaryVO.getSeatId()==null)
				{
					_fb.setSeatId("");
				}
				else
				{
					_fb.setSeatId(latestSummaryVO.getSeatId());					
				}
				_fb.setEpisodeSummary(latestSummaryVO.getEpisodeSummary());				
			}
			else if(lastEpisodeVO!=null)
			{
				_fb.setEpisodeIsOpen(lastEpisodeVO.getEpisodeIsOpen());
				_fb.setIsEpisodeAlreadyOpen(lastEpisodeVO.getEpisodeIsOpen());
				_fb.setEpisodeKeywords(lastEpisodeVO.getEpisodeName());
			}
			 
			// Setting Previous Episode Summary Detail
			if(EHRSection_FollowupVO.size()>0)
			{
				Iterator<EHRSection_FollowupVO> itrSumm = EHRSection_FollowupVO1.iterator();
				if(itrSumm.hasNext())
				{
					EHRSection_FollowupVO episodeSummVO = itrSumm.next();
					for(EHRSection_FollowupVO episodeVO : EHRSection_FollowupVO)
					{
						if(!episodeVO.getEpisodeVisitNo().equalsIgnoreCase(episodeSummVO.getEpisodeVisitNo()))
						{
							while(Integer.parseInt(episodeSummVO.getEpisodeVisitNo())>Integer.parseInt(episodeVO.getEpisodeVisitNo()) &&
									itrSumm.hasNext())
								episodeSummVO = itrSumm.next();
						}
						if(episodeVO.getEpisodeVisitNo().equalsIgnoreCase(episodeSummVO.getEpisodeVisitNo()))
						{
							//episodeVO.setVisitNotes(episodeSummVO.getVisitNotes());
							episodeVO.setNextVisitCriteria(episodeSummVO.getNextVisitCriteria());
							episodeVO.setNextVisitDuration(episodeSummVO.getNextVisitDuration());
						}
					}
				}
			}
			List<EHRSection_FollowupVO> toRemove = new ArrayList<EHRSection_FollowupVO>();
			for(EHRSection_FollowupVO episodeSummVO : EHRSection_FollowupVO1)			
				if(!episodeSummVO.getEpisodeVisitNo().equalsIgnoreCase(_fb.getEpisodeVisitNo()))
					toRemove.add(episodeSummVO);
			for(EHRSection_FollowupVO episodeSummVO : toRemove)
					EHRSection_FollowupVO1.remove(episodeSummVO);
			
			//_fb.setRequestByName(getUserVO(_request).getUsrName());
			//_fb.setRequestById(getUserVO(_request).getUserEmpID());
			
	
		//	mpEssential.put(EHRConfig.OPD_ESSENTIAL_ALL_VISIT_SUMMARY_OF_CURRENT_EPISODE_VISIT_LIST, EHRSection_FollowupVO1);
			
			WebUTIL.setMapInSession(mpEssential, _request);
			
			eVOPrint.setUnitConsultant(eVOPrint1.getUnitConsultant());
			EHRVOUtility.setOPDFollowUpVO(_request, _fb.getPatCrNo(), eVOPrint);//Added by Vasu on 20.Nov.2018
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

	public static String saveEhrFollowupdetails(HttpServletRequest _request,
			HttpServletResponse response, EHRSection_FollowupFB _fb) {

		Status objStatus = new Status();
		String saveFlag = "true";
		try
		{
			HttpSession session = WebUTIL.getSession(_request);
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			EHRSection_FollowupVO followVO = new EHRSection_FollowupVO();
			//EpisodeSummaryDetailVO episodeSummaryVO = new EpisodeSummaryDetailVO();
			followVO.setEpisodeIsOpen(_fb.getEpisodeIsOpen());
		//	followVO.setEpisodeIsOpen(_fb.getIsEpisodeAlreadyOpen());
			followVO.setIsConfirmed(_fb.getIsConfirmed());
			followVO.setNextVisitCriteria(_fb.getNextVisitCriteria());
			followVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			followVO.setVisitNotes(_fb.getVisitNotes());
			followVO.setEpisodeSummary(_fb.getEpisodeSummary());
			followVO.setPatNextAptNo(_fb.getPatNextAptNo());
			followVO.setPatNextAptQueueNo(_fb.getPatNextAptQueueNo());
			followVO.setPatCrNo(_fb.getPatCrNo());
			followVO.setEpisodeCloseDate(_fb.getEpisodeCloseDate());
			followVO.setSnomdCIdEpisodeSummary(_fb.getSnomdCIdEpisodeSummary());
			followVO.setSnomdPTEpisodeSummary(_fb.getSnomdPTEpisodeSummary());
			followVO.setSnomdCIdKeywords(_fb.getSnomdCIdKeywords());
			followVO.setSnomdPTKeywords(_fb.getSnomdPTKeywords());
			followVO.setSnomdCIdVisitNotes(_fb.getSnomdCIdVisitNotes());
			followVO.setSnomdPTVisitNotes(_fb.getSnomdPTVisitNotes());
			followVO.setEpisodeTypeCode(_fb.getEpisodeTypeCode());
			followVO.setAdmissionNo(_fb.getAdmissionNo());
			followVO.setEpisodeCloseType(_fb.getEpisodeCloseType());
			followVO.setEpisodeDate(_fb.getEpisodeDate());
			followVO.setEpisodeCode(_fb.getEpisodeCode());
			followVO.setRequestByName(_fb.getRequestByName());
			followVO.setRequestById(_fb.getRequestById());
			
		//	HelperMethods.populate(followVO, _fb);	
			//WebUTIL.populate(followVO, _fb);
			followVO.setEpisodeVisitSlNo(_fb.getSerialNo());
			
			followVO.setEpisodeKeywords(_fb.getKeywords());
			
			if(followVO.getEpisodeIsOpen().equalsIgnoreCase(RegistrationConfig.EPISODE_ISOPEN_TRUE))
			{
				if(followVO.getNextVisitCriteria().equalsIgnoreCase(EHRConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS))
				{
					followVO.setNextVisitDuration(null);
					followVO.setEpisodeNextVisitDate(null);
				}
				else if(followVO.getNextVisitCriteria().equalsIgnoreCase(EHRConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS))
				{
					followVO.setNextVisitDuration(_fb.getNextVisitDuration());
					followVO.setNextVisitCriteria(_fb.getNextVisitDurationCriteria());
					followVO.setEpisodeNextVisitDate(null);
				}
				else if(followVO.getNextVisitCriteria().equalsIgnoreCase(EHRConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE))
				{
					followVO.setNextVisitDuration(null);
					followVO.setEpisodeNextVisitDate(_fb.getEpisodeNextVisitDate());
					followVO.setPatNextAptNo(_fb.getPatNextAptNo()); 
					followVO.setPatNextAptSlot(_fb.getPatNextAptSlot()); 
					followVO.setPatNextAptQueueNo(_fb.getPatNextAptQueueNo()); 
					
				}
			}
			else
			{
				followVO.setNextVisitCriteria(null);
				followVO.setNextVisitDuration(null);
				followVO.setEpisodeNextVisitDate(null);
			}
			

			// For CMO Desk
		//	EpisodeActionDtlVO episodeActionDtlVO = new EpisodeActionDtlVO();
			Date sessionDateObject = (Date) session.getAttribute(Config.SYSDATEOBJECT);
			String actiondate = (String) WebUTIL.getCustomisedSysDate(sessionDateObject, "dd-MM-yyyy");
			String actiontime = (String) WebUTIL.getCustomisedSysDate(sessionDateObject, "HH:mm");

			//HelperMethods.populate(episodeActionDtlVO, followVO);
			followVO.setEpisodeActionDate(actiondate);
			followVO.setEpisodeActionTime(actiontime);
			
			// For Episode Close
			//EpisodeCloseVO episodeCloseVO = null;
			if((_fb.getIsEpisodeAlreadyOpen()==null || _fb.getIsEpisodeAlreadyOpen().equalsIgnoreCase(RegistrationConfig.EPISODE_ISOPEN_TRUE) || _fb.getIsEpisodeAlreadyOpen().equalsIgnoreCase(""))
					&& _fb.getEpisodeIsOpen().equalsIgnoreCase(RegistrationConfig.EPISODE_ISOPEN_FALSE))
			{
				//followVO = new EpisodeCloseVO();
				followVO.setIsCloseVo("1");
				//HelperMethods.populate(followVO, _fb);				
			}

			if((_fb.getEpisodeIsOpen() != null) && (_fb.getEpisodeIsOpen().equals("0")))
			{
				followVO.setEpisodeCloseDate(actiondate);
			}

			EHRSection_FollowupDATA.saveEhrFollowupdetails(followVO, userVO, _fb.getDeskType());
			EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) WebUTIL.getSession(_request).getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
			patencountervo.setListSaveAllFollowUp(followVO);
			WebUTIL.getSession(_request).setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, patencountervo);
			followVO.setEntryDate(_fb.getEntryDate());
			WebUTIL.getSession(_request).setAttribute(EHRConfig.FOLLOWUP_ESSENTAILS, followVO);
			

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			//System.out.println("Inside HisRecordNotFoundException");
			saveFlag="false";
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			//System.out.println("Inside HisDataAccessException");
			saveFlag="false";
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			//System.out.println("Inside HisApplicationExecutionException");
			saveFlag="false";
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			//System.out.println("Inside HisException");
			saveFlag="false";
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			//System.out.println("Inside HisException");
			saveFlag="false";
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return saveFlag;
		//return isSave;
		
	}
	
	public static void saveDetails(HttpServletRequest _rq,HttpServletResponse response, EHRSection_FollowupFB _fb) 
	{
		
		try
		{
			String flag=EHRSection_FollowupUTL.saveEhrFollowupdetails(_rq,response,_fb);
			//writeResponse(response, flag);
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
	
	//Added by Vasu on 18.Dec.2018 for FollowUp Discharge
	public static void getPatientStatus(EHRSection_FollowupFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		boolean flag=true;
		String profileStatus="";
		String compolsory="0";		//Flag For Compulsory or not 0-->NO	1-->YES
		PatientDetailVO[] dailyPatientVOs = null;
		
		try
		{
			setSysdate(request);
			
			PatientDetailVO voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(voDP == null || !voDP.getPatCrNo().equals(fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						voDP = dailyPatientVOs[i];
						break;
					}
				}
			}
			fb.setPatCrNo(voDP.getPatCrNo());
			/*fb.setRequestByName(getUserVO(request).getUsrName());
			fb.setDischargePreparedByName(getUserVO(request).getUsrName());
			fb.setDischargeApprovedByName(getUserVO(request).getUsrName());*/
			
			Map map=EHRSection_FollowupDATA.getDischargeStatusListNProfileStatus(voDP,getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			
			if(compolsory.equals(InpatientConfig.CHECK_DISCHARGE_SUMMARY_GENERATED_REQUIRED_NO))
			{
				flag=EHRSection_FollowupDATA.getPatientStatus(voDP.getPatAdmNo(),getUserVO(request));
					if(flag)
						fb.setPatStatus(InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED);
					else
					{
						fb.setPatStatus(InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE_APPROVAL);
						EHRSection_FollowupVO patDischargeReqVO=EHRSection_FollowupDATA.getDischargeRemarks(voDP.getPatAdmNo(),getUserVO(request));
						fb.setDischargeRemarksDisabled(patDischargeReqVO.getRemarks());
						fb.setDischargeStatus(patDischargeReqVO.getDischargeStatus());
						//System.out.println("val22"+fb.getDischargeStatus());
						//System.out.println("ifelse1");
						
					}
					objStatus.add(Status.TRANSINPROCESS);
			}	
			else
			{
				profileStatus=(String)map.get(InpatientConfig.ESSENTIAL_PROFILE_STATUS);
				fb.setProfileStatus(profileStatus);
				if(profileStatus.equals(OpdConfig.HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_GENERATED))
				{
					flag=EHRSection_FollowupDATA.getPatientStatus(voDP.getPatAdmNo(),getUserVO(request));
					if(flag)
						fb.setPatStatus(InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED);
					else
					{
						fb.setPatStatus(InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE_APPROVAL);
						EHRSection_FollowupVO patDischargeReqVO=EHRSection_FollowupDATA.getDischargeRemarks(voDP.getPatAdmNo(),getUserVO(request));
						fb.setDischargeRemarksDisabled(patDischargeReqVO.getRemarks());
						fb.setDischargeStatus(patDischargeReqVO.getDischargeStatus());
						//System.out.println("ifelse2");
					}
						objStatus.add(Status.TRANSINPROCESS);
				}
				else
					objStatus.add(Status.UNSUCESSFULL,"","Discharge Summary Not Created");
			}
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}	
	}
	
	//Added by Vasu on 03.Jan.2019 to save discharge follow up details
	
	public static String saveDischargeFollowUpDetails(HttpServletRequest _request,HttpServletResponse response, EHRSection_FollowupFB _fb) {

		Status objStatus = new Status();
		String saveFlag = "true";
		try
		{
			HttpSession session = WebUTIL.getSession(_request);
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			
			_fb.setAdmissionNo(ptaientDetailVO.getPatAdmNo());
			_fb.setEpisodeCode(ptaientDetailVO.getEpisodeCode());
			_fb.setEpisodeVisitNo(ptaientDetailVO.getEpisodeVisitNo());
			
			EHRSection_FollowupVO followVO = new EHRSection_FollowupVO();
			
			HelperMethods.populate(followVO, _fb);
			PatientDetailVO patientVO=(PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
            String patDeathStatus=(String)patientVO.getPatStatusCode();
			
			if(patDeathStatus.equalsIgnoreCase(RegistrationConfig.PATIENT_STATUS_CODE_DEAD))
				followVO.setIsDead("1");
			else
				followVO.setIsDead("0");
			
			followVO.setPatCrNo(patientVO.getPatCrNo());
			followVO.setPatAdmNo(patientVO.getPatAdmNo());
			followVO.setEpisodeCode(patientVO.getEpisodeCode());
			followVO.setEpisodeVisitNo(patientVO.getEpisodeVisitNo());
			if(_fb.getPatStatus().equals(InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED ))
			{
				followVO.setRemarks(_fb.getDischargeRemarks());
				followVO.setRequestType(InpatientConfig.DISCHARGE_REQUEST_TYPE_REQUEST);
			}
			else
			{
				followVO.setRemarks(_fb.getRevokeRemarks());
				followVO.setRequestType(InpatientConfig.DISCHARGE_REQUEST_TYPE_REQUEST);
			}
			
			EHRSection_FollowupDATA.saveDischargeFollowUpDetails(followVO, userVO, _fb.getDeskType());
			
			EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) WebUTIL.getSession(_request).getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
			patencountervo.setListSaveAllFollowUp(followVO);
			WebUTIL.getSession(_request).setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, patencountervo);

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			//System.out.println("Inside HisRecordNotFoundException");
			saveFlag="false";
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			//System.out.println("Inside HisDataAccessException");
			saveFlag="false";
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			//System.out.println("Inside HisApplicationExecutionException");
			saveFlag="false";
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			//System.out.println("Inside HisException");
			saveFlag="false";
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			//System.out.println("Inside HisException");
			saveFlag="false";
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return saveFlag;
		//return isSave;
		
	}
	
	//Added by Vasu on 04.Jan.2019
	public static void getDischargeFollowUpEssentials(EHRSection_FollowupFB _fb,HttpServletRequest _request,HttpServletResponse response) {

		Status objStatus = new Status();
		try
		{
			HttpSession session = WebUTIL.getSession(_request);
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			
			SimpleDateFormat _sdf1 =new SimpleDateFormat("dd/MM/yyyy HH:mm");
		    //SimpleDateFormat _sdf2 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
		    SimpleDateFormat _sdf3 =new SimpleDateFormat("dd-MMM-yyyy");
		   // SimpleDateFormat _sdf4 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
              //System.out.println("finall2");
		    String currDate =_sdf3.format(_sdf1.parse((String)_request.getSession().getAttribute(Config.SYSDATE)));
		    //System.out.println(currDate);
		    _fb.setCurrDate(currDate);
			PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			
			_fb.setAdmissionNo(ptaientDetailVO.getPatAdmNo());
			_fb.setEpisodeCode(ptaientDetailVO.getEpisodeCode());
			_fb.setEpisodeVisitNo(ptaientDetailVO.getEpisodeVisitNo());
			
		/*	_fb.setRequestByName(getUserVO(_request).getUsrName());
			_fb.setDischargePreparedByName(getUserVO(_request).getUsrName());
			_fb.setDischargeApprovedByName(getUserVO(_request).getUsrName());*/
			
			EHRSection_FollowupVO followVO = new EHRSection_FollowupVO();
			
			HelperMethods.populate(followVO, _fb);
			
			followVO = EHRSection_FollowupDATA.getDischargeFollowUpDetails(followVO, userVO);
			
			
			//added  by swati sagar on date:14-06-2019
			
          //Deafult values set for Single page			
			if (followVO.getDischargeStatusName()==null || followVO.getDischargeStatusName().equals("") ){
				followVO.setDischargeStatus("8");
			}
			
			if(followVO.getNextVisitDate()==null || followVO.getNextVisitDate()=="" ){
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
				String strDate= formatter.format(date);
				followVO.setNextVisitDate(strDate);
			}
			
			
			
			HelperMethods.populate(_fb, followVO);
			
			if(_fb.getRequestByName()==null || _fb.getRequestByName().equals("")  ){
				
				//_fb.setRequestByName(getUserVO(_request).getUsrName());
				_fb.setRequestByName(ptaientDetailVO.getConsultantName());
			}
			if(_fb.getDischargePreparedByName()==null || _fb.getDischargePreparedByName().equals("")  ){
				
				_fb.setDischargePreparedByName(getUserVO(_request).getUsrName());
			}
			if(_fb.getDischargeApprovedByName()==null || _fb.getDischargeApprovedByName().equals("")  ){
				//_fb.setDischargeApprovedByName(getUserVO(_request).getUsrName());
				_fb.setDischargeApprovedByName(ptaientDetailVO.getConsultantName());
				
			}
			
			/*if(followVO.getNextVisitDate()==null){
				System.out.println("ifnew");
				_fb.setSysDate("-");
			}*/
			//else{
			_fb.setSysDate(followVO.getNextVisitDate());
			//}
			//_fb.setDischargeStatus(followVO.getDischargeStatusName());//added by swati on date:14-06-2019
			//System.out.println("disnamenew"+_fb.getDischargeStatus());
			//System.out.println("date+++"+_fb.getSysDate());
			EHRVOUtility.setDischargeFollowUpVO(_request, _fb.getPatCrNo(), followVO);
			
			_fb.setNextVisitDateSinglePage(followVO.getNextVisitDate());
			
			
			_request.getSession().setAttribute("nextVisitDate", followVO.getNextVisitDate());
			_request.getSession().setAttribute("dischargeDate", followVO.getDischargeDate());
			_request.getSession().setAttribute("dischargePreparedDate", followVO.getDischargePreparedDate());
			
			//EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) WebUTIL.getSession(_request).getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
			
			//WebUTIL.getSession(_request).setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, patencountervo);

			//objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			//System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			//System.out.println("Inside HisDataAccessException");
		
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			//System.out.println("Inside HisApplicationExecutionException");
			
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			//System.out.println("Inside HisException");
			
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			//System.out.println("Inside HisException");
		
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		
		
	}
	
	
	
	
}//End of Class
