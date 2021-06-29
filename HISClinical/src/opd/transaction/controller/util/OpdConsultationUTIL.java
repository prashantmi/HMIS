package opd.transaction.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.ConsultationDtlVO;
import hisglobal.vo.ConsultationProfileDtlVO;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.OpdConsultationDATA;
import opd.transaction.controller.fb.OpdConsultationFB;

public class OpdConsultationUTIL extends ControllerUTIL
{
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void getOpdConsultationEssentials(OpdConsultationFB _fb, HttpServletRequest _rq)
	{

		Status objStatus = new Status();
		Map essentialMap = new HashMap();
		HttpSession session = WebUTIL.getSession(_rq);
		// HttpSession session=WebUTIL.getSession(_rq);
		// String seatId;
		// String empNo="";
		// String compSeatId;
		// String userId;

		List consultantList = null;
		// List cosultantListWithSeatidAndNo=null;
		boolean flag = false;
		int i = 0;
		ConsultationDtlVO consultationDtlVO = new ConsultationDtlVO();
		//DailyPatientVO[] dailyPatientVO;
		PatientDetailVO[] dailyPatientVOs = null;

		try
		{

			PatientDetailVO patientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(patientDetailVO == null || !patientDetailVO.getPatCrNo().equals(_fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (i = 0; i < dailyPatientVOs.length; i++)
				{
					if (_fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						patientDetailVO = dailyPatientVOs[i];
						flag = true;
						break;
					}
				}
			}
			_fb.setPatCrNo(patientDetailVO.getPatCrNo());
			_fb.setEpisodeCode(patientDetailVO.getEpisodeCode());

			
			/*
			dailyPatientVO = (DailyPatientVO[]) _rq.getSession().getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			for (; i < dailyPatientVO.length; i++)
			{
				if (dailyPatientVO[i].getPatCrNo().equals(_fb.getPatCrNo()))
				{
					flag = true;
					break;
				}
			}*/
			
			if (flag) HelperMethods.populate(consultationDtlVO, dailyPatientVOs[i]);
			/* Added By Nilesh Gupta */
			consultationDtlVO.setPatCrNo(_fb.getPatCrNo());
			consultationDtlVO.setDepartmentUnitCode(patientDetailVO.getDepartmentUnitCode());

			essentialMap = OpdConsultationDATA.getOpdConsultationEssentials(consultationDtlVO, getUserVO(_rq));

			WebUTIL.setMapInSession(essentialMap, _rq);
			// cosultantListWithSeatidAndNo=(List)essentialMap.get(OpdConfig.OPD_CONSULTANT_NO_SEATID);
			consultantList = (List) essentialMap.get(OpdConfig.OPD_ECONSULTANT_DETAIL_LIST);
			/*
			 * ListIterator cosultantListWithSeatidAndNoItr=cosultantListWithSeatidAndNo.listIterator();
			 * while(cosultantListWithSeatidAndNoItr.hasNext()){ Entry entry =(Entry)cosultantListWithSeatidAndNoItr.next();
			 * //if(seatId.equals(entry.getLabel())){ if(userId.equals(entry.getLabel())){ empNo=entry.getValue(); break; } }
			 */
			/*
			 * ListIterator consultantListItr=consultantList.listIterator(); while(consultantListItr.hasNext()){ Entry
			 * entry=(Entry)consultantListItr.next(); if(userId.equals(entry.getValue())){ consultantListItr.remove(); break; } }
			 */
			essentialMap.put(OpdConfig.OPD_ECONSULTANT_DETAIL_LIST, consultantList);
			objStatus.add(Status.INPROCESS);

		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
			essentialMap = e.getEssentialMap();
			e.printStackTrace();
			WebUTIL.setMapInSession(essentialMap, _rq);
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

	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void sendOpdConsultationData(OpdConsultationFB _fb, HttpServletRequest _rq)
	{

		Status objStatus = new Status();

		HttpSession session = WebUTIL.getSession(_rq);
		ConsultationDtlVO consultationDtlVO = new ConsultationDtlVO();
		int i = 0;
		Entry entry1 = null;
		boolean flag = false;
		List<ConsultationProfileDtlVO> consultationProfileDtlVOList = new ArrayList<ConsultationProfileDtlVO>();
		ConsultationProfileDtlVO consultationProfileDtlVO = null;
		PatientDetailVO[] dailyPatientVOs = null;

		try
		{

			HelperMethods.populate(consultationDtlVO, _fb);
			// List fromDocDetail=(List)session.getAttribute(OpdConfig.OPD_CONSULTANT_NAME_NO);
			List toDocNoSeatid = (List) session.getAttribute(OpdConfig.OPD_CONSULTANT_NO_SEATID);
			

			PatientDetailVO patientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			flag = true;
			if(patientDetailVO == null || !patientDetailVO.getPatCrNo().equals(_fb.getPatCrNo()))
			{
				flag = false;
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (i = 0; i < dailyPatientVOs.length; i++)
				{
					if (_fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))					{
						flag = true;
						patientDetailVO = dailyPatientVOs[i];
						break;
					}
				}
			}
			/*dailyPatientVO = (DailyPatientVO[]) _rq.getSession().getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			for (; i < dailyPatientVO.length; i++)
			{
				if (dailyPatientVO[i].getPatCrNo().equals(_fb.getPatCrNo()))
				{
					flag = true;
					break;
				}
			}*/
			if (flag)
			{
				HelperMethods.populate(consultationDtlVO, patientDetailVO);
			}
			System.out.println(_fb.getToDoctorSeatId());
			// Entry entry=(Entry)fromDocDetail.get(0);
			
			consultationDtlVO.setFromDoctorCode(getUserVO(_rq).getUserEmpID());
			consultationDtlVO.setFromDoctorSeatId(getUserVO(_rq).getSeatId());
			consultationDtlVO.setMailType(OpdConfig.OPD_COSULTANT_NEW_MAIL);
			consultationDtlVO.setAckStatus(OpdConfig.OPD_CONSULTANT_ACK_STATUS_NEW);
			ListIterator listIteratorToDocNoSeatid = toDocNoSeatid.listIterator();

			while (listIteratorToDocNoSeatid.hasNext())
			{
				entry1 = (Entry) listIteratorToDocNoSeatid.next();
				if (consultationDtlVO.getToDoctorSeatId().equals(entry1.getValue()))
				{
					break;
				}

			}
			consultationDtlVO.setToDoctorCode(entry1.getLabel());
			String content = consultationDtlVO.getContent().trim();
			consultationDtlVO.setContent(content);

			String[] selectedProfile = _fb.getSelectedProfile().split("#");
			if (selectedProfile != null)
				for (i = 0; i < selectedProfile.length; i++)
					if(!selectedProfile[i].trim().equals("-1") && !selectedProfile[i].trim().equals(""))
					{
						consultationProfileDtlVO = new ConsultationProfileDtlVO();
						consultationProfileDtlVO.setProfileId(selectedProfile[i]);
						consultationProfileDtlVOList.add(consultationProfileDtlVO);
					}
			if((_fb.getToDoctorSeatId()!=null) && !(_fb.getToDoctorSeatId().equals("-1")))
			{	
				String part[]=_fb.getToDocId().split("#");
				consultationDtlVO.setUserType("1");
				consultationDtlVO.setToDoctName(part[1]);
				consultationDtlVO.setToDoctorSeatId(part[0]);
			}
			else
			{	
				consultationDtlVO.setUserType("2");
				String[] parts =_fb.getToDeptId().split("#");
				consultationDtlVO.setDepartmentCode(parts[0]);
				consultationDtlVO.setDepartmentName(parts[1]);
				consultationDtlVO.setToDoctorSeatId(null);

			}
			OpdConsultationDATA.sendCosultantData(consultationDtlVO, consultationProfileDtlVOList, getUserVO(_rq));
			objStatus.add(Status.DONE, "", "Mail Sent Succesfully");

		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.INPROCESS, e.getMessage(), "");

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

	public static void attachProfile(OpdConsultationFB _fb, HttpServletRequest _rq)
	{

		Status objStatus = new Status();

		// HttpSession session=WebUTIL.getSession(_rq);
		ConsultationDtlVO consultationDtlVO = new ConsultationDtlVO();
		// DailyPatientVO[] dailyPatientVO;
		// int i=0;
		// Entry entry1=null;
		// boolean flag=false;
		try
		{

			HelperMethods.populate(consultationDtlVO, _fb);
			String[] selectProfile = _fb.getSelectedRow();
			WebUTIL.setAttributeInSession(_rq, OpdConfig.SELECTED_PATIENT_PROFILE, selectProfile);
			// objStatus.add(Status.DONE,"","Mail Sent Succesfully");

		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.INPROCESS, e.getMessage(), "");

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

}
