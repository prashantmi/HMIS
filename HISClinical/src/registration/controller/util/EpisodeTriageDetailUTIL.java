package registration.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.EpisodeTriageDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.RegistrationConfig;
import registration.controller.data.EpisodeTriageDetailDATA;
import registration.controller.fb.EpisodeTriageDetailFB;

public class EpisodeTriageDetailUTIL extends ControllerUTIL
{
	// Direct Call
	//* Setting Triage Detail Essentials
	public static void setTriageDetailDirectEssentials(EpisodeTriageDetailFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		try
		{
			HttpSession session = WebUTIL.getSession(_rq);
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);
			
			// Patient Tile Info
			PatDetailUTIL.getPatientDtlByCrno(_fb, _rq);
			PatientVO patVO =(PatientVO) session.getAttribute(RegistrationConfig.PATIENT_VO);

			// Episode Detail
			EpisodeVO episodeVO = EpisodeTriageDetailDATA.getLastEpisodeInEmergency(patVO, userVO);
			WebUTIL.setAttributeInSession(_rq,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR,episodeVO);
			_fb.setEpisodeCode(episodeVO.getEpisodeCode());

			// Episode Visit No
			episodeVO.setPatCrNo(_fb.getPatCrNo());
			episodeVO = EpisodeTriageDetailDATA.retrieveByEpisodeNo(episodeVO, userVO);			
			_fb.setEpisodeVisitNo(episodeVO.getEpisodeVisitNo());
			// Need QueNo from Daily Patient

			// Essentials
			EpisodeTriageDetailVO episodeTriageVO = new EpisodeTriageDetailVO();
			WebUTIL.populate(episodeTriageVO, _fb);
			Map mpEssential = EpisodeTriageDetailDATA.getTriageDetailEssentials(episodeTriageVO, userVO);
			
			EpisodeTriageDetailVO vo = (EpisodeTriageDetailVO) mpEssential.get(RegistrationConfig.PATIENT_EPISODE_TRIAGE_DETAIL_VO);
				//EpisodeTriageDetailDATA.getPatTriageDtl(_fb.getPatCrNo(),_fb.getEpisodeCode(),_fb.getEpisodeVisitNo(),userVO);
			mpEssential.remove(RegistrationConfig.PATIENT_EPISODE_TRIAGE_DETAIL_VO);
			WebUTIL.setMapInSession(mpEssential, _rq);

			if(vo!=null)
			{
				_fb.setEntryMode("OUT");
				_fb.setEmergencyCode(vo.getEmergencyCode());
				_fb.setInDate(vo.getInDate());
				_fb.setInTime(vo.getInTime());
				if(_fb.getInTime()!=null && !_fb.getInTime().equals(""))
				{
					_fb.setInTimeHr(_fb.getInTime().split(":")[0]);
					_fb.setInTimeMin(_fb.getInTime().split(":")[1]);
				}
				_fb.setInPatStatus(vo.getInPatStatus());
				_fb.setInCondition(vo.getInCondition());
				
				if(vo.getOutDate()==null)
				{
					_fb.setOutDate("");
					_fb.setOutTimeHr("00");
					_fb.setOutTimeMin("00");
					_fb.setOutTime("00:00");
					_fb.setOutCondition("");
					_fb.setOutPatStatus("");					
				}
				else 
				{
					_fb.setEntryMode("TOMODIFY");
					_fb.setOutDate(vo.getOutDate());
					_fb.setOutTime(vo.getOutTime());
					if(_fb.getOutTime()!=null && !_fb.getOutTime().equals(""))
					{
						_fb.setOutTimeHr(_fb.getOutTime().split(":")[0]);
						_fb.setOutTimeMin(_fb.getOutTime().split(":")[1]);
					}
					_fb.setOutPatStatus(vo.getOutPatStatus());
					_fb.setOutCondition(vo.getOutCondition());					
				}
				_fb.setRemarks(vo.getRemarks());
				_fb.setEntryDate(vo.getEntryDate());
				_fb.setSeatId(vo.getSeatId());
			}
			else
			{
				Date today=(Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT);
				String sysDate=WebUTIL.getCustomisedSysDate(today, "dd-MMM-yyyy");
				_fb.setInDate(sysDate);
			}

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
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
			WebUTIL.setStatus(_rq, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}
	/**
	##		Modify Date				: 16-01-2015	
	##		Reason	(CR/PRS)		: To Run Desk according to New VO
	##		Modify By				: Akash Singh
	*/

	// Setting Triage Detail Essentials
	public static void setTriageDetailEssentials(EpisodeTriageDetailFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		try
		{
			HttpSession session = WebUTIL.getSession(_rq);
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);
			DailyPatientVO voDP = new DailyPatientVO();
			
			// Setting Essentials from Desk
			PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			voDP = ptaientDetailVO;
			if(ptaientDetailVO == null || !ptaientDetailVO.getPatCrNo().equals(_fb.getPatCrNo()))
			{
				DailyPatientVO[] al = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < al.length; i++)
				{
					voDP = (DailyPatientVO) al[i];
					if (voDP.getPatCrNo().equals(_fb.getPatCrNo())) break;
				}
			}
				_fb.setEpisodeCode(voDP.getEpisodeCode());
				_fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
				_fb.setSerialNo(voDP.getSerialNo());
						
			
			// Patient Tile Info
			PatDetailUTIL.getPatientDtlByCrno(_fb, _rq);

			// Essentials
			EpisodeTriageDetailVO episodeTriageVO = new EpisodeTriageDetailVO();
			WebUTIL.populate(episodeTriageVO, _fb);
			Map mpEssential = EpisodeTriageDetailDATA.getTriageDetailEssentials(episodeTriageVO, userVO);
			
			EpisodeTriageDetailVO vo = (EpisodeTriageDetailVO) mpEssential.get(RegistrationConfig.PATIENT_EPISODE_TRIAGE_DETAIL_VO);
				//EpisodeTriageDetailDATA.getPatTriageDtl(_fb.getPatCrNo(),_fb.getEpisodeCode(),_fb.getEpisodeVisitNo(),userVO);
			mpEssential.remove(RegistrationConfig.PATIENT_EPISODE_TRIAGE_DETAIL_VO);
			WebUTIL.setMapInSession(mpEssential, _rq);

			if(vo!=null)
			{
				_fb.setEntryMode("OUT");
				_fb.setEmergencyCode(vo.getEmergencyCode());
				_fb.setInDate(vo.getInDate());
				_fb.setInTime(vo.getInTime());
				if(_fb.getInTime()!=null && !_fb.getInTime().equals(""))
				{
					_fb.setInTimeHr(_fb.getInTime().split(":")[0]);
					_fb.setInTimeMin(_fb.getInTime().split(":")[1]);
				}
				_fb.setInPatStatus(vo.getInPatStatus());
				_fb.setInCondition(vo.getInCondition());
				
				if(vo.getOutDate()==null)
				{
					_fb.setOutDate("");
					_fb.setOutTimeHr("00");
					_fb.setOutTimeMin("00");
					_fb.setOutTime("00:00");
					_fb.setOutCondition("");
					_fb.setOutPatStatus("");					
				}
				else 
				{
					_fb.setEntryMode("TOMODIFY");
					_fb.setOutDate(vo.getOutDate());
					_fb.setOutTime(vo.getOutTime());
					if(_fb.getOutTime()!=null && !_fb.getOutTime().equals(""))
					{
						_fb.setOutTimeHr(_fb.getOutTime().split(":")[0]);
						_fb.setOutTimeMin(_fb.getOutTime().split(":")[1]);
					}
					_fb.setOutPatStatus(vo.getOutPatStatus());
					_fb.setOutCondition(vo.getOutCondition());					
				}
				_fb.setRemarks(vo.getRemarks());
				_fb.setEntryDate(vo.getEntryDate());
				_fb.setSeatId(vo.getSeatId());
			}
			else
			{
				Date today=(Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT);
				String sysDate=WebUTIL.getCustomisedSysDate(today, "dd-MMM-yyyy");
				_fb.setInDate(sysDate);
			}

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
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
			WebUTIL.setStatus(_rq, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	//* Saving Triage Details
	public static void saveTriageDetails(EpisodeTriageDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		try
		{
			EpisodeTriageDetailVO triVO = new EpisodeTriageDetailVO();
			
			HelperMethods.populate(triVO, _fb);
			
			if(_fb.getOutDate().equals(""))
			{
				triVO.setOutDate(null);
				triVO.setOutTime(null);
				triVO.setOutPatStatus(null);
				triVO.setOutCondition(null);
			}
			triVO.setSystemIPAddress(_request.getRemoteAddr());
			
			EpisodeTriageDetailDATA.saveTriageDetails(triVO, getUserVO(_request));
			
			objStatus.add(Status.DONE, "", "Patient Day Care Details Recorded Succesfully");
		}

		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println("Inside HisUpdateUnsuccesfullException");
			objStatus.add(Status.UNSUCESSFULL, "", "update unsuccesfull");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "INSERT FAILED");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Inside Exception");
			objStatus.add(Status.ERROR_AE, "Application Execution Exception", "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	//* Modify Triage Details
	public static void modifyTriageDetails(EpisodeTriageDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		try
		{
			EpisodeTriageDetailVO triVO = new EpisodeTriageDetailVO();
			
			HelperMethods.populate(triVO, _fb);
			triVO.setSystemIPAddress(_request.getRemoteAddr());
			
			EpisodeTriageDetailDATA.modifyTriageDetails(triVO, getUserVO(_request));
			
			objStatus.add(Status.DONE, "", "Patient Triage Details Modified Succesfully");
		}

		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println("Inside HisUpdateUnsuccesfullException");
			objStatus.add(Status.UNSUCESSFULL, "", "update unsuccesfull");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "INSERT FAILED");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Inside Exception");
			objStatus.add(Status.ERROR_AE, "Application Execution Exception", "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}
}
