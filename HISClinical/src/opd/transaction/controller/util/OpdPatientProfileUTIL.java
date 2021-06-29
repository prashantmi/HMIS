package opd.transaction.controller.util;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.fb.OpdPatientProfileFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatProfileDtlVO;

public class OpdPatientProfileUTIL extends ControllerUTIL
{
	public static void getPreviousProfile(OpdPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		// String htmlResult="";
		PatProfileDtlVO[] patProfileDtlVOs = null;
		try
		{
			session = _rq.getSession();
			setSysdate(_rq);
			Date entrydateObject = (Date) session.getAttribute(Config.SYSDATEOBJECT);
			String entryDate = WebUTIL.getCustomisedSysDate(entrydateObject, "dd-MMM-yyyy");
			_fb.setEntryDate(entryDate);

			session.removeAttribute(OpdConfig.OPD_PATIENT_PROFILE_EPISODEDIAGNOSISVO_ARRAY);
			session.removeAttribute(OpdConfig.OPD_PATIENT_PROFILE_EPISODEALLERGIESVO_ARRAY);
			_fb.setPatProfileHeader("");
			_fb.setPatProfileRemark("");
			_fb.setEffectiveFrom("");
			_fb.setEffectiveTo("");

			//String departmentUnitCode = (String) session.getAttribute(OpdConfig.OPD_DESK_UNIT_CODE);
			_fb.setHtmlValue("<html>");

			// _fb.setPatCrNo("1080800000014"); //// for Test purpose
			// departmentUnitCode="10412"; //// for Test purpose
			//patProfileDtlVOs = OpdPatientProfileDATA.getPatientPreviousProfile(_fb.getPatCrNo(), departmentUnitCode, getUserVO(_rq));
			session.setAttribute(OpdConfig.OPD_PATIENT_PREVIOUS_PROFILE_ARRAY, patProfileDtlVOs);
			objStatus.add(Status.RECORDFOUND);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.NEW);
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

	public static void getPatientEpisodes(OpdPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		EpisodeVO[] episodeVOs = null;
		String htmlResult = "";

		try
		{

			session = _rq.getSession();
			String profileType = _fb.getProfileType();
			htmlResult = _fb.getHtmlValue();
			//String departmentUnitCode = (String) session.getAttribute(OpdConfig.OPD_DESK_UNIT_CODE);
			// htmlResult=htmlResult+"<head><center><b><h1>"+_fb.getPatProfileHeader()+"<h1></b></center></head><p>&nbsp;</p><body></br>"
			// +
			// "<center><b>Patient CrNo. :: "+_fb.getPatCrNo()+"</b></center>&nbsp";
			if (profileType.equals("0"))
			{
				//episodeVOs = OpdPatientProfileDATA.getPatientEpisodes(_fb.getPatCrNo(), departmentUnitCode, getUserVO(_rq));
				session.setAttribute(OpdConfig.OPD_PATIENT_PROFILE_EPISODEVO_ARRAY, episodeVOs);
			}
			else
			{
			}
			_fb.setHtmlValue(htmlResult);
			session.setAttribute(OpdConfig.OPD_FILE_HEADER, _fb.getPatProfileHeader());
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
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

	public static void showProfileMenu(OpdPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		//String[] menuArray = new String[4];
		java.util.List menuList = new ArrayList();
		String htmlResult = "";
		try
		{
			session = _rq.getSession();
			htmlResult = _fb.getHtmlValue();
			// if(htmlResult.indexOf(_fb.getSelectedEpisode())==-1){
			// htmlResult=htmlResult+"<left><b><h2> Episode :: "+_fb.getSelectedEpisode()+"</h2></left> &nbsp";
			// }
			// htmlResult=htmlResult+"<head><center><b><h1>"+_fb.getPatProfileHeader()+"<h1></b></center></head><p>&nbsp;</p><body></br>"
			// +
			// "<center><b>Patient CrNo. :: "+_fb.getPatCrNo()+"</b></center>&nbsp";
			//String profileType = _fb.getProfileType();
			//String departmentUnitCode = (String) session.getAttribute(OpdConfig.OPD_DESK_UNIT_CODE);
			// if(profileType.equals("0")){
			// Entry entry=new Entry();
			// entry.setLabel("Vitals");
			// entry.setValue("SHOWPROFILEDIAGNOSIS");
			// menuList.add(entry);
			Entry entry1 = new Entry();
			entry1.setLabel("Diagnosis");
			entry1.setValue("SHOWPROFILEDIAGNOSIS");
			menuList.add(entry1);
			Entry entry2 = new Entry();
			entry2.setLabel("Allergies");
			entry2.setValue("SHOWPROFILEALLERGIES");
			menuList.add(entry2);
			session.setAttribute("PROFILE_MENU", menuList);
			// }
			// else{

			// }
			objStatus.add(Status.LIST);
			_fb.setHtmlValue(htmlResult);
		}
		catch (HisRecordNotFoundException e)
		{
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

	public static boolean getPatientDiagnosisDetail(OpdPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		//HttpSession session = null;
		//java.util.List menuList = new ArrayList();
		// String htmlResult="";
		boolean flag = false;
		try
		{
			_fb.setSelectedRow(new String[]
			{ "" });
			//session = _rq.getSession();
			// htmlResult=_fb.getHtmlValue();
			//EpisodeDiagnosisVO[] episodeDiagnosisVOs = OpdPatientProfileDATA.getEpisodeDiagnosis(_fb.getPatCrNo(), _fb.getFromDate(),_fb.getToDate(), getUserVO(_rq));
			//session.setAttribute(OpdConfig.OPD_PATIENT_PROFILE_EPISODEDIAGNOSISVO_ARRAY, episodeDiagnosisVOs);
			objStatus.add(Status.TRANSINPROCESS);
			flag = true;
		}
		catch (HisRecordNotFoundException e)
		{
			// objStatus.add(Status.TRANSINPROCESS);
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
		return flag;
	}

	public static boolean getPatientEpisodeAllergiesDetail(OpdPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		//HttpSession session = null;
		//java.util.List menuList = new ArrayList();
		boolean flag = false;
		//String htmlResult = "";
		try
		{
			_fb.setSelectedRow(new String[]
			{ "" });
			//session = _rq.getSession();
			//htmlResult = _fb.getHtmlValue();
			//EpisodeAllergiesVO[] episodeallergiesVOs = OpdPatientProfileDATA.getPatProfileEpisodeAllergies(_fb.getPatCrNo(), _fb.getFromDate(), _fb.getToDate(), getUserVO(_rq));
			//session.setAttribute(OpdConfig.OPD_PATIENT_PROFILE_EPISODEALLERGIESVO_ARRAY, episodeallergiesVOs);
			objStatus.add(Status.TRANSINPROCESS);
			flag = true;
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
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
		return flag;
	}

	public static void genrateProfile(OpdPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		String resultHtml;
		PatProfileDtlVO patProfileDtlVO = null;
		PatProfileDtlVO[] prevDtlVOs = null;
		try
		{
			session = _rq.getSession();
			String departmentUnitCode = (String) session.getAttribute(OpdConfig.OPD_DESK_UNIT_CODE);

			resultHtml = _fb.getHtmlValue() + "</table></body></html>";
			prevDtlVOs = (PatProfileDtlVO[]) session.getAttribute(OpdConfig.OPD_PATIENT_PREVIOUS_PROFILE_ARRAY);
			patProfileDtlVO = new PatProfileDtlVO();
			HelperMethods.populate(patProfileDtlVO, _fb);
			patProfileDtlVO.setPrevEffectiveFrom(_fb.getEffectiveFrom());
			patProfileDtlVO.setPrevEffectiveTo(_fb.getEffectiveTo());
			if (prevDtlVOs != null)
			{
				patProfileDtlVO.setPatProfileId(prevDtlVOs[prevDtlVOs.length - 1].getPatProfileId());
				patProfileDtlVO.setSerialNo(prevDtlVOs[prevDtlVOs.length - 1].getSerialNo());
			}
			// departmentUnitCode="10412"; //// for testing purpose
			patProfileDtlVO.setFileString(resultHtml);
			String departmentCode = departmentUnitCode.substring(0, 3);
			patProfileDtlVO.setDepartmentUnitCode(departmentUnitCode);
			patProfileDtlVO.setDepartmentCode(departmentCode);
			patProfileDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
			//OpdPatientProfileDATA.savePatientProfile(patProfileDtlVO, getUserVO(_rq));
			objStatus.add(Status.DONE, "", "Profile Genrated Successfully");

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

	public static void modifyProfileEffectiveDate(OpdPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		PatProfileDtlVO patProfileDtlVO = new PatProfileDtlVO();
		try
		{
			int index = Integer.parseInt(_fb.getModifyChoice());
			patProfileDtlVO.setPrevEffectiveFrom(_fb.getEffectiveFrom());
			patProfileDtlVO.setPrevEffectiveTo(_fb.getEffectiveTo());
			patProfileDtlVO.setSerialNo(_fb.getSelectedProfileSerialNo());
			patProfileDtlVO.setPatProfileId(_fb.getSelectedProfileId());
			patProfileDtlVO.setPrevEffectiveTo(_fb.getSelectedEffectiveTo()[index]);
			//OpdPatientProfileDATA.modifyProfileEffectiveDate(patProfileDtlVO, getUserVO(_rq));
			objStatus.add(Status.DONE, "", "Profile Effective Date Modified Successfully");

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
