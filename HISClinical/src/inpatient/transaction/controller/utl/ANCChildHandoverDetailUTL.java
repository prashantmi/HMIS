package inpatient.transaction.controller.utl;

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
import hisglobal.vo.ANCChildHandoverDetailVO;
import hisglobal.vo.ANCNeonatalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.ANCChildHandoverDetailDATA;
import inpatient.transaction.controller.fb.ANCChildHandoverDetailFB;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ANCChildHandoverDetailUTL extends ControllerUTIL
{
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	// Setting Esentials for ANC Child Handover Detail
	public static void setEssentials(ANCChildHandoverDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		PatientDetailVO[] dailyPatientVOs = null;
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			_fb.setEntryDate(WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
			_fb.setEntryTime(WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "HH:mm"));

			String sys = (String) session.getAttribute(Config.SYSDATE);
			String time = sys.split(" ")[1];
			_fb.setEntryTimeHr(time.split(":")[0]);
			_fb.setEntryTimeMin(time.split(":")[1]);

			// Setting Desk Essentials
			// User Seat, Ward
			_fb.setUserSeatId(userVO.getUserSeatId());
			//_fb.setDepartmentUnitCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE));
			_fb.setWardCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_WARD_CODE));
			// Episode, Visit, Admission No, Unit
			/*PatientDetailVO[] al = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO voDP = null;
			for (PatientDetailVO vo : al)
				if (vo.getPatCrNo().equals(_fb.getPatCrNo()))
				{
					voDP = vo;
					break;
				}*/
			PatientDetailVO voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(voDP == null || !voDP.getPatCrNo().equals(_fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (_fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						voDP = dailyPatientVOs[i];
						break;
					}
				}
			}
			_fb.setPatCrNo(voDP.getPatCrNo());
			_fb.setEpisodeCode(voDP.getEpisodeCode());
			_fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
			_fb.setDepartmentUnitCode(voDP.getDepartmentUnitCode());
			if (voDP.getPatAdmNo() != null) _fb.setAdmissionNo(voDP.getPatAdmNo());
			else _fb.setAdmissionNo("");

			// Getting Patient Detail
			/*CRNoFB crnoFB = new CRNoFB();
			crnoFB.setPatCrNo(_fb.getPatCrNo());
			// Getting Patient Demographic Detail always call first to avoid episode related information to be replaced 
			InpatientDetailUTL.getInpatientDetailByCrNo(crnoFB, _request);
			PatientDetailVO patDtlVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);*/

			// Getting ANC Neo Nat Detail Essentials
			ANCChildHandoverDetailVO ancChildHandoverVO = new ANCChildHandoverDetailVO();
			HelperMethods.populate(ancChildHandoverVO, _fb);
			essentialMap = ANCChildHandoverDetailDATA.getEssentials(ancChildHandoverVO ,userVO);
			
			ANCNeonatalDetailVO voNeoNatDtl = (ANCNeonatalDetailVO)essentialMap.get(InpatientConfig.ANCDETAIL_ESSENTIAL_NEONAT_ADM_DETAIL);			
			if(voNeoNatDtl==null)
				throw new HisRecordNotFoundException("Not Eligible for Neonatal Handover Detail");
			else
			{
				ANCChildHandoverDetailVO voHandoverDtl = (ANCChildHandoverDetailVO)essentialMap.get(InpatientConfig.ANCDETAIL_ESSENTIAL_NEONAT_HANDOVER_DETAIL);
				if(voHandoverDtl!=null) HelperMethods.populate(_fb, voHandoverDtl);
			}
				

			WebUTIL.setMapInSession(essentialMap, _request);			
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

	// Saving ANC Child Handover Detail
	public static boolean saveANCChildHandoverDetail(ANCChildHandoverDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();		
		boolean flag = true;
		
		HttpSession session = null;
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			session = _request.getSession();

			// ANC Child Handover Detail
			ANCChildHandoverDetailVO ancChildHandoverVO = new ANCChildHandoverDetailVO();
			HelperMethods.populate(ancChildHandoverVO, _fb);
			
			if(session.getAttribute(InpatientConfig.ANCDETAIL_ESSENTIAL_NEONAT_HANDOVER_DETAIL)==null)
				ANCChildHandoverDetailDATA.saveANCChildHandoverDetail(ancChildHandoverVO, userVO);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			flag=false;
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			flag=false;
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{

			e.printStackTrace();
			flag=false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			flag=false;
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flag=false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return flag;
	}	
}
