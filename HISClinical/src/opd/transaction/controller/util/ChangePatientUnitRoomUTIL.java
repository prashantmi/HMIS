package opd.transaction.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.RoomChangeVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.ChangePatientUnitRoomDATA;
import opd.transaction.controller.data.DoctorDeskDashboardDATA;
import opd.transaction.controller.fb.ChangePatientUnitRoomFB;

public class ChangePatientUnitRoomUTIL extends ControllerUTIL
{
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void getEssentials(ChangePatientUnitRoomFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();

		String oldRoomName = "";
		Map essentialMap = new HashMap();
		PatientDetailVO[] dailyPatientVOs = null;
		try
		{
			setSysdate(_rq);
			UserVO userVO = getUserVO(_rq);
			// //////Retrieving the patient vo of the selected patient////////
			PatientDetailVO patientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(patientDetailVO == null || !patientDetailVO.getPatCrNo().equals(_fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (_fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						patientDetailVO = dailyPatientVOs[i];
						break;
					}
				}
			}
			_fb.setPatCrNo(patientDetailVO.getPatCrNo());
			/*PatientDetailVO[] arrayDailyPatVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO selectedPatientVO = null;
			for (int i = 0; i < arrayDailyPatVO.length; i++)
			{
				if (_fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
				{
					selectedPatientVO = arrayDailyPatVO[i];
				}
			}*/

			WebUTIL.getSession(_rq).setAttribute(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO, patientDetailVO);
			
			if((patientDetailVO.getPatChangeType()!= null))
			{
				if(patientDetailVO.getPatChangeType().equals("1"))
				{
					objStatus.add(Status.UNSUCESSFULL, "Rediret Patient can't change Unit Room", "");
				}
			}
			else
			{
				String _unitCode = (String) WebUTIL.getSession(_rq).getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE);
				
				//Changed by Akash for New Desk on date 12-Feb-2015
				//String _roomCode = (String) WebUTIL.getSession(_rq).getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ROOM_CODE);
				
				//_unitCode = selectedPatientVO.getDepartmentUnitCode();---
				//_roomCode = selectedPatientVO.getRoomCode();---
	
				essentialMap = ChangePatientUnitRoomDATA.getEpisodeRoomChangeEssential(userVO, _unitCode, patientDetailVO.getRoomCode(),patientDetailVO);
	
				WebUTIL.setMapInSession(essentialMap, _rq);
				List changeRoomList = (List) essentialMap.get(OpdConfig.ESSENTIALBO_LIST_CHANGE_ROOM);

				//Commented by Akash Singh for Getting old room from SELECTED_DESK_PATIENT_VO
				oldRoomName = (String) essentialMap.get(OpdConfig.ESSENTIALBO_OLD_ROOM_NAME);
				//oldRoomName = (String) essentialMap.get(patientDetailVO.getRoom());
	
				WebUTIL.getSession(_rq).setAttribute(OpdConfig.ESSENTIALBO_LIST_CHANGE_ROOM, changeRoomList);
				_fb.setOldRoomName(oldRoomName);
	
				objStatus.add(Status.TRANSINPROCESS);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.getSession(_rq).setAttribute(OpdConfig.ESSENTIALBO_LIST_CHANGE_ROOM, new ArrayList());
			_fb.setOldRoomName("");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
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
	}

	public static void changePatientRoom(ChangePatientUnitRoomFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();

		RoomChangeVO _roomChangeVO = new RoomChangeVO();
		EpisodeVO _episodeVO = new EpisodeVO();
		PatientDetailVO _dailyPatientVO = new PatientDetailVO();

		PatientDetailVO[] dailyPatientVOs = null;
		HttpSession session = _request.getSession();
		try
		{
			setSysdate(_request);

			/*PatientDetailVO[] arrayDailyPatVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO selectedPatientVO = null;
			for (int i = 0; i < arrayDailyPatVO.length; i++)
			{
				if (_fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
				{
					selectedPatientVO = arrayDailyPatVO[i];
				}
			}*/
			PatientDetailVO selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(selectedPatientVO == null || !selectedPatientVO.getPatCrNo().equals(_fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (_fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						selectedPatientVO = dailyPatientVOs[i];
						break;
					}
				}
			}
			WebUTIL.getSession(_request).setAttribute(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO, selectedPatientVO);

			HelperMethods.populate(_roomChangeVO, _fb);

			String _unitCode = (String) WebUTIL.getSession(_request).getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE);
			//Changed by Akash for New Desk on date 12-Feb-2015
			//String _roomCode = (String) WebUTIL.getSession(_request).getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ROOM_CODE);

			_roomChangeVO.setPatCrNo(selectedPatientVO.getPatCrNo());
			_roomChangeVO.setEpisodeCode(selectedPatientVO.getEpisodeCode());
			_roomChangeVO.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());
			_roomChangeVO.setToRoomCode(_fb.getRoomCode());
			_roomChangeVO.setFromRoomCode(selectedPatientVO.getRoomCode());
			_roomChangeVO.setChangeReason(_fb.getRemarks());

			_episodeVO.setRoomCode(_fb.getRoomCode());
			_episodeVO.setPatCrNo(selectedPatientVO.getPatCrNo());
			_episodeVO.setEpisodeCode(selectedPatientVO.getEpisodeCode());
			_episodeVO.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());

			_dailyPatientVO.setRoomCode(_fb.getRoomCode());
			_dailyPatientVO.setPatCrNo(selectedPatientVO.getPatCrNo());
			_dailyPatientVO.setEpisodeCode(selectedPatientVO.getEpisodeCode());
			_dailyPatientVO.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());
			_dailyPatientVO.setSerialNo(selectedPatientVO.getSerialNo());

			ChangePatientUnitRoomDATA.changePatientRoom(_roomChangeVO, _episodeVO, _dailyPatientVO, getUserVO(_request), _unitCode);
			
			//FOR UPDATING DESK_SELECTED_PATIENT_DTL_VO
			String deskType = (String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
			DoctorDeskDashboardDATA doctorDeskDashboardDATA = new DoctorDeskDashboardDATA();
			Map<String, Object> mapEssential = doctorDeskDashboardDATA.getDeskPatDtl(selectedPatientVO, ControllerUTIL.getUserVO(_request),deskType);
			DynamicDeskUTIL.setMapInSession(mapEssential, _request);
			objStatus.add(Status.LIST, "Room Changed Successfully", "");

		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, "Data Access Exception", "");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "Exception", "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "Exception in AddDeptMasterUTIL", "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}
}
