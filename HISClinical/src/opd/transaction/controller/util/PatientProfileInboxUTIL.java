package opd.transaction.controller.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.PatientProfileInboxDATA;
import opd.transaction.controller.fb.GenericPatientProfileFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.UserVO;

public class PatientProfileInboxUTIL extends ControllerUTIL
{
	// Setting Essentials for Patient Profile Inbox 
	public static void setEssentials(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		List<PatientProfileDetailVO> lstProfiles = null;
		try
		{
			UserVO userVO = getUserVO(_rq);
			session = _rq.getSession();
			setSysdate(_rq);

			// Setting Essentials From Desk
				// Unit
			_fb.setDepartmentUnitCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE));
			
			// Getting Episode Previous Patient Profiles
			PatientProfileDetailVO voPatProfile = new PatientProfileDetailVO();
			HelperMethods.populate(voPatProfile, _fb);
			lstProfiles = PatientProfileInboxDATA.getPatientProfilesForInbox(_fb.getPatCrNo(), _fb.getDepartmentUnitCode(), userVO);
			
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILES_FOR_INBOX_LIST, lstProfiles);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST, lstProfiles);//Added by Vasu on 17.Jan.2019
			if(lstProfiles!=null && lstProfiles.size()>0)
				objStatus.add(Status.NEW);
			else
				objStatus.add(Status.NEW,"","No Profile Found");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, "No Profile Found", "");
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
}
