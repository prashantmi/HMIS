package opd.transaction.controller.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jscape.inet.sftp.requests.Request;

import opd.OpdConfig;
import opd.transaction.controller.data.OpdNextVisitDetailDATA;
import opd.transaction.controller.fb.OpdNextVisitDetailFB;
import registration.RegistrationConfig;
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
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.EpisodeActionDtlVO;
import hisglobal.vo.EpisodeCloseVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeKeywordsMasterVO;
import hisglobal.vo.EpisodeSummaryDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class OpdNextVisitDetailUTIL extends ControllerUTIL
{
	public static void setEssentials(OpdNextVisitDetailFB _fb, HttpServletRequest _request)
	{
		System.out.println("OpdNextVisitDetailUTIL.setEssentials()");
		HttpSession session = _request.getSession();
		Status objStatus = new Status();
		
		Map mpEssential = new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);	// Required for Current Date

			// Set Desk Essentials
				// Desk Type & Unit Code
			_fb.setDeskType((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE));
			//_fb.setDepartmentUnitCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE));
				// Patient Detail
			PatientDetailVO voDP = null;
			if(session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST)!=null)
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
				
				
			}
			else if(session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO)!=null)
			{
				System.out.println("TAKING VO from :-- DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO");
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
			voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(_fb.getDeskType().equals(DynamicDeskConfig.DESK_TYPE_CASUALITY_DESK))
			{
				//commented by Akash Singh, dated on 04-06-2015, Due to issue coming in case of casulity patent when pat_status was 1...it was taking them as a dead patient
				//if(voDP.getIsBroughtDead()!=null && voDP.getIsBroughtDead().equals(RegistrationConfig.PATIENT_BROUGHT_DEAD_TRUE) || voDP.getPatStatusCode().equals(RegistrationConfig.PATIENT_STATUS_CODE_DEAD))
				if(voDP.getIsBroughtDead()!=null && voDP.getIsBroughtDead().equals(RegistrationConfig.PATIENT_BROUGHT_DEAD_TRUE) || voDP.getIsDead().equals(RegistrationConfig.PATIENT_STATUS_CODE_DEAD))
					_fb.setIsPatDead(OpdConfig.YES);
				else
					_fb.setIsPatDead(OpdConfig.NO);
			}
			else
				_fb.setIsPatDead(OpdConfig.NO);
			
			// Get Visit Summary Essentials
			EpisodeVO epiVO = new EpisodeVO();
			WebUTIL.populate(epiVO, _fb);
			
			mpEssential = OpdNextVisitDetailDATA.getEssentials(epiVO, userVO);
			
			List<EpisodeVO> lstPreviousEpisodeDtl = (List<EpisodeVO>) mpEssential.get(OpdConfig.OPD_ESSENTIAL_ALL_VISIT_OF_EPISODE_LIST);
			List<EpisodeSummaryDetailVO> lstAllSummaryDtl = (List<EpisodeSummaryDetailVO>) mpEssential.get(OpdConfig.OPD_ESSENTIAL_ALL_VISIT_SUMMARY_OF_CURRENT_EPISODE_VISIT_LIST);
			List<EpisodeKeywordsMasterVO> lstAllKeywordDtl = (List<EpisodeKeywordsMasterVO>) mpEssential.get(OpdConfig.OPD_ESSENTIAL_ALL_KEYWORDS_LIST);
			
			
			EpisodeVO eVO = null;
			eVO = lstPreviousEpisodeDtl.get(0);  
			WebUTIL.populate(_fb, eVO);
			
			//List<EpisodeDiagnosisVO> lstEpiVisitDiag = (List<EpisodeDiagnosisVO>) mpEssential.get(OpdConfig.OPD_EPISODE_VISIT_DIAGNOSIS_DETAIL);
			
			// Setting Is Diagnosis Detail present or not for Current Episode Visit
			/*if(lstEpiVisitDiag==null || lstEpiVisitDiag.size()<=0)
				_fb.setIsDiagnosisDtlExists(OpdConfig.NO);
			else
				_fb.setIsDiagnosisDtlExists(OpdConfig.YES);*/
			for(int i=0;i<lstAllSummaryDtl.size();i++)
			{
				EpisodeSummaryDetailVO lstSummary=(EpisodeSummaryDetailVO)lstAllSummaryDtl.get(i);
				_fb.setVisitReason(lstSummary.getVisitReason());
				
			}	
			ArrayList<String> ar = new ArrayList<String>();
			String keyWords="";
			String keyWordId="";
			for(int i=0;i<lstAllKeywordDtl.size();i++)
			{
				EpisodeKeywordsMasterVO keywordVO=(EpisodeKeywordsMasterVO)lstAllKeywordDtl.get(i);
				keywordVO.getEpisodeKeyword();
				ar.add(keywordVO.getEpisodeKeyword());
				keyWords+=keywordVO.getEpisodeKeyword()+"##";
				keyWordId+=keywordVO.getEpisodeKeywordID()+"##";
				
			}	
			//String[] stringArray = Arrays.copyOf(ar, ar.size(), String[].class);
			System.out.println("ar::"+ar);
			_fb.setEpisodeKeywords(keyWords);
			_fb.setEpisodeKeywords(keyWords);
			
			String [] tempKeys = ar.toArray(new String[lstAllKeywordDtl.size()]);//(String[]) ar.toArray();
			System.out.println("tempkeys:::"+tempKeys);
			_fb.setEpisodeKeyword(tempKeys);
			EpisodeVO firstEpisodeVO = null, lastEpisodeVO = null;
			EpisodeSummaryDetailVO latestSummaryVO = null;
			if(lstPreviousEpisodeDtl.size()>0)
			{
				firstEpisodeVO = lstPreviousEpisodeDtl.get(lstPreviousEpisodeDtl.size()-1);
				lstPreviousEpisodeDtl.remove(0);
			}
				
			if(firstEpisodeVO!=null)
			{
				_fb.setEpisodeDate(firstEpisodeVO.getEpisodeDate());
				_fb.setEpisodeTypeCode(firstEpisodeVO.getEpisodeTypeCode());
			}
			
			if(lstAllSummaryDtl.size()>0)
			{
				if(lstAllSummaryDtl.get(0).getEpisodeVisitNo().equalsIgnoreCase(_fb.getEpisodeVisitNo()))
				{
					latestSummaryVO = lstAllSummaryDtl.get(0);
					lstAllSummaryDtl.remove(latestSummaryVO);
				}
				else if(lstPreviousEpisodeDtl.size()>0)
					lastEpisodeVO = lstPreviousEpisodeDtl.get(0);  
				else if(firstEpisodeVO!=null)
					lastEpisodeVO = firstEpisodeVO;
			}
			if(latestSummaryVO!=null)
			{
				//_fb.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
		
				//_fb.setEpisodeIsOpen(latestSummaryVO.getEpisodeIsOpen());
				_fb.setIsEpisodeAlreadyOpen(latestSummaryVO.getEpisodeIsOpen());
				if(latestSummaryVO.getEpisodeNextVisitDate()!=null && !latestSummaryVO.getEpisodeNextVisitDate().equals(""))
				{
				_fb.setEpisodeNextVisitDate(latestSummaryVO.getEpisodeNextVisitDate());	
				_fb.setPatNextAptNo(latestSummaryVO.getPatNextAptNo());
				_fb.setPatNextAptSlot(latestSummaryVO.getPatNextAptSlot());
				
				}
			if(latestSummaryVO.getEpisodeKeywords()!=null)
			{
				//String keyWord=latestSummaryVO.getEpisodeKeywords().replaceAll("#",",");
				//keyWord=keyWord.concat(",");
				String keyWord=latestSummaryVO.getEpisodeKeywords();
				_request.setAttribute("keywordData", keyWord);
				_fb.setKeywords(keyWord);
			}
			else
			{
				_request.setAttribute("keywordData", "");
			}
				_fb.setEpisodeIsOpen(latestSummaryVO.getEpisodeIsOpen());
				System.out.println("keywordData:::"+_request.getAttribute("keywordData"));
				//_fb.setEpisodeKeywords(keyWord);
				_fb.setVisitNotes(latestSummaryVO.getVisitNotes());
				_fb.setNextVisitDuration(latestSummaryVO.getNextVisitDuration());
				//_fb.setNextVisitCriteria(null);
				_fb.setNextVisitDurationCriteria(null);
				if(latestSummaryVO.getNextVisitCriteria()!=null)
				{
					if(!latestSummaryVO.getNextVisitCriteria().equalsIgnoreCase(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS) && 
							!latestSummaryVO.getNextVisitCriteria().equalsIgnoreCase(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE) )
					{
						_fb.setNextVisitCriteria(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS);
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
			if(lstPreviousEpisodeDtl.size()>0)
			{
				Iterator<EpisodeSummaryDetailVO> itrSumm = lstAllSummaryDtl.iterator();
				if(itrSumm.hasNext())
				{
					EpisodeSummaryDetailVO episodeSummVO = itrSumm.next();
					for(EpisodeVO episodeVO : lstPreviousEpisodeDtl)
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
			List<EpisodeSummaryDetailVO> toRemove = new ArrayList<EpisodeSummaryDetailVO>();
			for(EpisodeSummaryDetailVO episodeSummVO : lstAllSummaryDtl)			
				if(!episodeSummVO.getEpisodeVisitNo().equalsIgnoreCase(_fb.getEpisodeVisitNo()))
					toRemove.add(episodeSummVO);
			for(EpisodeSummaryDetailVO episodeSummVO : toRemove)
					lstAllSummaryDtl.remove(episodeSummVO);
			
				
			
			
			/*
			 * For Casualty Desk 
			 if (_fb.getIsConfirmed().equals(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED))
			{
				EpisodeVO $_epiVO = new EpisodeVO();
				epiVO.setPatCrNo(_fb.getPatCrNo());
				epiVO.setEpisodeCode(_fb.getEpisodeCode());
				epiVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
				
				//epiVO = OpdNextVisitDetailDATA.retrieveEpisodeDetail(epiVO, userVO);
				//mpEssential = OpdNextVisitDetailDATA.getEssentials(epiVO, userVO);
				
				/*if (epiVO.getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo()))
				{
					_fb.setVisitNotes(epiVO.getComplainDetail());
					if (epiVO.getEpisodeNextVisitDate() == null) _fb.setNextVisitDate("");
					else _fb.setNextVisitDate(epiVO.getEpisodeNextVisitDate());
					_fb.setEndTreatment(epiVO.getEpisodeIsOpen());
					_fb.setIsConfirmed(epiVO.getIsConfirmed());
				}
			}*/

			
			WebUTIL.setMapInSession(mpEssential, _request);
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
 
	/**
	## 		Modification Log		: if((_fb.getEpisodeIsOpen() != null) && (_fb.getEpisodeIsOpen().equals("0")))		
	##		Modify Date				: 19-02-2015
	##		Reason	(CR/PRS)		: Data model changed in table , So Episode Closed Date passed
	##		Modify By				: Akash Singh
	*/
	public static boolean saveNextVisitDetail(OpdNextVisitDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean saveFlag = true;
		try
		{
			HttpSession session = WebUTIL.getSession(_request);
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			EpisodeSummaryDetailVO episodeSummaryVO = new EpisodeSummaryDetailVO();
			WebUTIL.populate(episodeSummaryVO, _fb);
			episodeSummaryVO.setEpisodeVisitSlNo(_fb.getSerialNo());
			episodeSummaryVO.setEpisodeKeywords(_fb.getKeywords()); 
			//episodeSummaryVO.setEpisodeKeywords(_fb.getEpisodeKeywords());  //commented as autocomplete not in use
			
			
			
			if(episodeSummaryVO.getEpisodeIsOpen().equalsIgnoreCase(RegistrationConfig.EPISODE_ISOPEN_TRUE))
			{
				if(episodeSummaryVO.getNextVisitCriteria().equalsIgnoreCase(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS))
				{
					episodeSummaryVO.setNextVisitDuration(null);
					episodeSummaryVO.setEpisodeNextVisitDate(null);
					episodeSummaryVO.setPatNextAptNo(null); 
					episodeSummaryVO.setPatNextAptSlot(null); 
					episodeSummaryVO.setPatNextAptQueueNo(null); 
				}
				else if(episodeSummaryVO.getNextVisitCriteria().equalsIgnoreCase(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS))
				{
					episodeSummaryVO.setNextVisitDuration(_fb.getNextVisitDuration());
					episodeSummaryVO.setNextVisitCriteria(_fb.getNextVisitDurationCriteria());
					episodeSummaryVO.setEpisodeNextVisitDate(null);
					episodeSummaryVO.setPatNextAptNo(null); 
					episodeSummaryVO.setPatNextAptSlot(null); 
					episodeSummaryVO.setPatNextAptQueueNo(null); 
				}
				else if(episodeSummaryVO.getNextVisitCriteria().equalsIgnoreCase(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE))
				{
					episodeSummaryVO.setNextVisitDuration(null);
					episodeSummaryVO.setEpisodeNextVisitDate(_fb.getEpisodeNextVisitDate());
					episodeSummaryVO.setPatNextAptNo(_fb.getPatNextAptNo()); 
					episodeSummaryVO.setPatNextAptSlot(_fb.getPatNextAptSlot()); 
					episodeSummaryVO.setPatNextAptQueueNo(_fb.getPatNextAptQueueNo()); 
					
				}
			}
			else
			{
				episodeSummaryVO.setNextVisitCriteria(null);
				episodeSummaryVO.setNextVisitDuration(null);
				episodeSummaryVO.setEpisodeNextVisitDate(null);
				episodeSummaryVO.setPatNextAptNo(null); 
				episodeSummaryVO.setPatNextAptSlot(null); 
				episodeSummaryVO.setPatNextAptQueueNo(null); 
			}
			

			// For CMO Desk
			EpisodeActionDtlVO episodeActionDtlVO = new EpisodeActionDtlVO();
			Date sessionDateObject = (Date) session.getAttribute(Config.SYSDATEOBJECT);
			String actiondate = (String) WebUTIL.getCustomisedSysDate(sessionDateObject, "dd-MM-yyyy");
			String actiontime = (String) WebUTIL.getCustomisedSysDate(sessionDateObject, "HH:mm");

			HelperMethods.populate(episodeActionDtlVO, episodeSummaryVO);
			episodeActionDtlVO.setEpisodeActionDate(actiondate);
			episodeActionDtlVO.setEpisodeActionTime(actiontime);
			
			// For Episode Close
			EpisodeCloseVO episodeCloseVO = null;
			if((_fb.getIsEpisodeAlreadyOpen()==null || _fb.getIsEpisodeAlreadyOpen().equalsIgnoreCase(RegistrationConfig.EPISODE_ISOPEN_TRUE) || _fb.getIsEpisodeAlreadyOpen().equalsIgnoreCase(""))
					&& _fb.getEpisodeIsOpen().equalsIgnoreCase(RegistrationConfig.EPISODE_ISOPEN_FALSE))
			{
				episodeCloseVO = new EpisodeCloseVO();
				HelperMethods.populate(episodeCloseVO, _fb);				
			}
			if((_fb.getEpisodeIsOpen() != null) && (_fb.getEpisodeIsOpen().equals("0")))
			{
				episodeActionDtlVO.setEpisodeCloseDate(actiondate);
			}

			OpdNextVisitDetailDATA.saveVisitSummaryDetail(episodeSummaryVO, userVO, episodeActionDtlVO, _fb.getDeskType(), episodeCloseVO);
			HelperMethods.populate(_fb,episodeSummaryVO);

			
			/*EpisodeVO episodeVO = new EpisodeVO();
			episodeVO.setPatCrNo(_fb.getPatCrNo());
			episodeVO.setEpisodeCode(_fb.getEpisodeCode());
			episodeVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			episodeVO.setComplainDetail(_fb.getVisitNotes());
			episodeVO.setEpisodeNextVisitDate(_fb.getNextVisitDate());
			episodeVO.setEpisodeIsOpen(_fb.getEndTreatment());
			episodeVO.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
			episodeVO.setQueNo(_fb.getSerialNo()); // Patient Serial No

			// /for cmo desk////
			Date sessionDateObject = (Date) session.getAttribute(Config.SYSDATEOBJECT);
			String actiondate = (String) WebUTIL.getCustomisedSysDate(sessionDateObject, "dd-MM-yyyy");
			String actiontime = (String) WebUTIL.getCustomisedSysDate(sessionDateObject, "HH:mm");

			HelperMethods.populate(episodeActionDtlVO, episodeVO);
			episodeActionDtlVO.setEpisodeActionDate(actiondate);
			episodeActionDtlVO.setEpisodeActionTime(actiontime);
			// ///fetching desk tye////
			String deskType = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
			if (deskType == null) deskType = "";

			OpdNextVisitDetailDATA.saveNextVisitDetail(episodeVO, userVO, episodeActionDtlVO, deskType);*/

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			saveFlag=false;
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			saveFlag=false;
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			saveFlag=false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			saveFlag=false;
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			System.out.println("Inside HisException");
			saveFlag=false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return saveFlag;
	}

	public static boolean modifyNextVisitDetail(OpdNextVisitDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean saveFlag = true;
		try
		{
			HttpSession session = WebUTIL.getSession(_request);
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			EpisodeSummaryDetailVO episodeSummaryVO = new EpisodeSummaryDetailVO();
			WebUTIL.populate(episodeSummaryVO, _fb);
			episodeSummaryVO.setEpisodeVisitSlNo(_fb.getSerialNo());
			episodeSummaryVO.setEpisodeKeywords(_fb.getKeywords());
			
			if(episodeSummaryVO.getEpisodeIsOpen().equalsIgnoreCase(RegistrationConfig.EPISODE_ISOPEN_TRUE))
			{
				if(episodeSummaryVO.getNextVisitCriteria().equalsIgnoreCase(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS))
				{
					episodeSummaryVO.setNextVisitDuration(null);
					episodeSummaryVO.setEpisodeNextVisitDate(null);
				}
				else if(episodeSummaryVO.getNextVisitCriteria().equalsIgnoreCase(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS))
				{
					episodeSummaryVO.setNextVisitDuration(_fb.getNextVisitDuration());
					episodeSummaryVO.setNextVisitCriteria(_fb.getNextVisitDurationCriteria());
					episodeSummaryVO.setEpisodeNextVisitDate(null);
				}
				else if(episodeSummaryVO.getNextVisitCriteria().equalsIgnoreCase(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE))
				{
					episodeSummaryVO.setNextVisitDuration(null);
					episodeSummaryVO.setEpisodeNextVisitDate(_fb.getEpisodeNextVisitDate());
					episodeSummaryVO.setPatNextAptNo(_fb.getPatNextAptNo()); 
					episodeSummaryVO.setPatNextAptSlot(_fb.getPatNextAptSlot()); 
					episodeSummaryVO.setPatNextAptQueueNo(_fb.getPatNextAptQueueNo()); 
					
				}
			}
			else
			{
				episodeSummaryVO.setNextVisitCriteria(null);
				episodeSummaryVO.setNextVisitDuration(null);
				episodeSummaryVO.setEpisodeNextVisitDate(null);
			}
			

			// For CMO Desk
			EpisodeActionDtlVO episodeActionDtlVO = new EpisodeActionDtlVO();
			Date sessionDateObject = (Date) session.getAttribute(Config.SYSDATEOBJECT);
			String actiondate = (String) WebUTIL.getCustomisedSysDate(sessionDateObject, "dd-MM-yyyy");
			String actiontime = (String) WebUTIL.getCustomisedSysDate(sessionDateObject, "HH:mm");

			HelperMethods.populate(episodeActionDtlVO, episodeSummaryVO);
			episodeActionDtlVO.setEpisodeActionDate(actiondate);
			episodeActionDtlVO.setEpisodeActionTime(actiontime);
			
			// For Episode Close
			EpisodeCloseVO episodeCloseVO = null;
			if((_fb.getIsEpisodeAlreadyOpen()==null || _fb.getIsEpisodeAlreadyOpen().equalsIgnoreCase(RegistrationConfig.EPISODE_ISOPEN_TRUE) || _fb.getIsEpisodeAlreadyOpen().equalsIgnoreCase(""))
					&& _fb.getEpisodeIsOpen().equalsIgnoreCase(RegistrationConfig.EPISODE_ISOPEN_FALSE))
			{
				episodeCloseVO = new EpisodeCloseVO();
				HelperMethods.populate(episodeCloseVO, _fb);				
			}

			if((_fb.getEpisodeIsOpen() != null) && (_fb.getEpisodeIsOpen().equals("0")))
			{
				episodeActionDtlVO.setEpisodeCloseDate(actiondate);
			}

			OpdNextVisitDetailDATA.modifyVisitSummaryDetail(episodeSummaryVO, userVO, episodeActionDtlVO, _fb.getDeskType(), episodeCloseVO);
			

			
			/*EpisodeVO episodeVO = new EpisodeVO();
			episodeVO.setPatCrNo(_fb.getPatCrNo());
			episodeVO.setEpisodeCode(_fb.getEpisodeCode());
			episodeVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			episodeVO.setComplainDetail(_fb.getVisitNotes());
			episodeVO.setEpisodeNextVisitDate(_fb.getNextVisitDate());
			episodeVO.setEpisodeIsOpen(_fb.getEndTreatment());
			episodeVO.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
			episodeVO.setQueNo(_fb.getSerialNo()); // Patient Serial No

			// /for cmo desk////
			Date sessionDateObject = (Date) session.getAttribute(Config.SYSDATEOBJECT);
			String actiondate = (String) WebUTIL.getCustomisedSysDate(sessionDateObject, "dd-MM-yyyy");
			String actiontime = (String) WebUTIL.getCustomisedSysDate(sessionDateObject, "HH:mm");

			HelperMethods.populate(episodeActionDtlVO, episodeVO);
			episodeActionDtlVO.setEpisodeActionDate(actiondate);
			episodeActionDtlVO.setEpisodeActionTime(actiontime);
			// ///fetching desk tye////
			String deskType = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
			if (deskType == null) deskType = "";

			OpdNextVisitDetailDATA.saveNextVisitDetail(episodeVO, userVO, episodeActionDtlVO, deskType);*/

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			saveFlag=false;
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			saveFlag=false;
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			saveFlag=false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			saveFlag=false;
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			System.out.println("Inside HisException");
			saveFlag=false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return saveFlag;
	}

	// On Click of Visit Date Hyper Link
	public static void showVisitSummary(OpdNextVisitDetailFB _fb, HttpServletRequest _request)
	{
		HttpSession session = _request.getSession();
		Status objStatus = new Status();

		LinkedHashMap<String, List<List<Object>>> mpVisitDtl = null;
		try
		{
			UserVO userVO = getUserVO(_request);

			// Get Desk Menus List
			List<DeskDetailVO> lstTopMenus , lstLeftMenus, lstRightMenus, lstBottomMenus;
			lstTopMenus = (List<DeskDetailVO>)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TOP_MENU_DTL);
			lstLeftMenus = (List<DeskDetailVO>)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_LEFT_MENU_DTL);
			lstRightMenus = (List<DeskDetailVO>)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_RIGHT_MENU_DTL);
			lstBottomMenus = (List<DeskDetailVO>)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_BOTTOM_MENU_DTL);
			
			List<String> lstMenuIds = new ArrayList<String>();
			for(DeskDetailVO vo : lstTopMenus)
				//if(vo.getUsabilityFlag().equals(OpdConfig.USABILITY_FLAG_TWO))
					lstMenuIds.add(vo.getDeskMenuId());
			for(DeskDetailVO vo : lstLeftMenus)
				//if(vo.getUsabilityFlag().equals(OpdConfig.USABILITY_FLAG_TWO))
					lstMenuIds.add(vo.getDeskMenuId());
			for(DeskDetailVO vo : lstRightMenus)
				//if(vo.getUsabilityFlag().equals(OpdConfig.USABILITY_FLAG_TWO))
					lstMenuIds.add(vo.getDeskMenuId());
			for(DeskDetailVO vo : lstBottomMenus)
				//if(vo.getUsabilityFlag().equals(OpdConfig.USABILITY_FLAG_TWO))
					lstMenuIds.add(vo.getDeskMenuId());
			
			EpisodeVO epiVO = new EpisodeVO();
			WebUTIL.populate(epiVO, _fb);
			mpVisitDtl = OpdNextVisitDetailDATA.getDynamicVisitSummaryDetail(epiVO, lstMenuIds, userVO);

			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_DYNAMIC_VISIT_DETAIL_MAP, mpVisitDtl);
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
	
	public static void getVisitSummaryDetails(OpdNextVisitDetailFB _fb, HttpServletRequest _request)
	{
		HttpSession session = _request.getSession();
		Status objStatus = new Status();
		
		Map mpEssential = new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);	// Required for Current Date

			// Set Desk Essentials
				// Desk Type & Unit Code
			
			EpisodeVO epiVO = new EpisodeVO();
			WebUTIL.populate(epiVO, _fb);
			mpEssential = OpdNextVisitDetailDATA.getVisitSummaryDetails(epiVO, userVO);
			
			List<EpisodeVO> lstAllSummaryDtl = (List<EpisodeVO>) mpEssential.get(OpdConfig.OPD_ESSENTIAL_ALL_EPISODE_VISIT_LIST);				
			for(int i=0;i<lstAllSummaryDtl.size();i++)
			{
				EpisodeVO lstSummary=(EpisodeVO)lstAllSummaryDtl.get(i);
				_fb.setVisitReason(lstSummary.getVisitReason());
				_fb.setEpisodeKeywords(lstSummary.getEpisodeKeywords());
				
			}	
			
			WebUTIL.setMapInSession(mpEssential, _request);
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
	
	
	
}
