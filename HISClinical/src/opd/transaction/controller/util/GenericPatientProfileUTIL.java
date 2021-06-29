package opd.transaction.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HTMLToPDFUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.generictemplate.ChartCellData;
import hisglobal.utility.generictemplate.ChartColumnHead;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.utility.noSqlDB.mongodb.MongoXmlHandler;
import hisglobal.utility.servlets.ServletsUtilityConfig;
import hisglobal.vo.ChartMasterVO;
import hisglobal.vo.ChartParameterMappingVO;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.DisclaimerMstVO;
import hisglobal.vo.DoctorRoundDtlVO;
import hisglobal.vo.DrugDoseVO;
import hisglobal.vo.DrugFrequencyMstVO;
import hisglobal.vo.DrugRouteMstVO;
import hisglobal.vo.DrugSheduleDtlVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeExtInvDtlVO;
import hisglobal.vo.EpisodeSummaryDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.PatAllergyDtlVO;
import hisglobal.vo.PatDietAdviceDetailVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatExtTreatmentDetailVO;
import hisglobal.vo.PatIntakeOutDtlVO;
import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.ProfileAccessDetailVO;
import hisglobal.vo.ProfileAccessPolicyVO;
import hisglobal.vo.ProfileAlertsDtlVO;
import hisglobal.vo.ProfileAllergyDtlVO;
import hisglobal.vo.ProfileChartViewDtlVO;
import hisglobal.vo.ProfileClinicalDtlVO;
import hisglobal.vo.ProfileDiagnosisDtlVO;
import hisglobal.vo.ProfileDietAdviceDtlVO;
import hisglobal.vo.ProfileDrugAdviceDtlVO;
import hisglobal.vo.ProfileDrugScheduleDtlVO;
import hisglobal.vo.ProfileExtExamDtlVO;
import hisglobal.vo.ProfileExtTreatmentDtlVO;
import hisglobal.vo.ProfileFooterDtlVO;
import hisglobal.vo.ProfileGroupDtlVO;
import hisglobal.vo.ProfileImageDtlVO;
import hisglobal.vo.ProfileInvDtlVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.ProfileOTDetailVO;
import hisglobal.vo.ProfileOTDtlVO;
import hisglobal.vo.ProfileProgressNotesDtlVO;
import hisglobal.vo.ProfileRestAdviceDtlVO;
import hisglobal.vo.ProfileTypeMstVO;
import hisglobal.vo.RestAdviceDtlVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.TemplateParameterMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.utl.InpatientDetailUTL;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import mrd.MrdConfig;
import emr.dataentry.spp.business.bo.UniPagePrescriptionBO;
import emr.dataentry.spp.presentation.fb.UniPagePrescriptionFB;
import opd.OpdConfig;
import opd.bo.OpdPatientBO;
import opd.transaction.controller.data.ChartReportingDATA;
import opd.transaction.controller.data.GenericPatientProfileDATA;
import opd.transaction.controller.data.GenericTemplateTileDATA;
import opd.transaction.controller.data.PatientTreatmentDetailDATA;
import opd.transaction.controller.fb.ChartReportingFB;
import opd.transaction.controller.fb.GenericPatientProfileFB;
import registration.RegistrationConfig;
import registration.controller.fb.CRNoFB;

public class GenericPatientProfileUTIL extends ControllerUTIL
{
	// Setting Essentials for Patient Profile and Previous Profiles of this Episode
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void setEssentials(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		List<PatientProfileDetailVO> lstPrevProfiles = null;
		Map<String, Object> essentialMap = null;
		String profileBound = "";
		try
		{
			PatientDetailVO voPD = new PatientDetailVO();
			UserVO userVO = getUserVO(_rq);
			session = _rq.getSession();
			setSysdate(_rq);
			_fb.setEntryDate(WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
			ControllerUTIL.getHospitalVO(_rq);
			// Setting Essentials From Desk
			// Unit
			//_fb.setDepartmentUnitCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE));
			// Episode, Visit, Admission No.
			PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);			
			if(ptaientDetailVO == null)
			{
				PatientDetailVO[] al = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);				
				for (int i = 0; i < al.length; i++)
				{
					voPD = (PatientDetailVO) al[i];
					if (voPD.getPatCrNo().equals(_fb.getPatCrNo())) break;
				}
			}
			voPD = ptaientDetailVO;
			_fb.setEpisodeCode(voPD.getEpisodeCode());
			_fb.setEpisodeVisitNo(voPD.getEpisodeVisitNo());
			_fb.setAdmissionNo(voPD.getPatAdmNo());
			_fb.setPatCategoryCode(voPD.getPatPrimaryCatCode());
			_fb.setDepartmentUnitCode(voPD.getDepartmentUnitCode());
			_fb.setPatCrNo(voPD.getPatCrNo());
			// Desk Type, Desk Id
			_fb.setDeskType((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE));
			_fb.setDeskId((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ID));

			// Getting Episode Previous Patient Profiles
			PatientProfileDetailVO voPatProfile = new PatientProfileDetailVO();
			HelperMethods.populate(voPatProfile, _fb);

			essentialMap = GenericPatientProfileDATA.getPatientProfilesEssentials(voPatProfile, _fb.getDeskType(), userVO);
			lstPrevProfiles = (List) essentialMap.get(OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST);

			profileBound = (String) essentialMap.get(OpdConfig.OPD_DESK_PROFILE_BOUND);
			List<Entry> listProfileType = new ArrayList<Entry>();
			List<ProfileTypeMstVO> profileTypeVOList = (List) essentialMap.get(OpdConfig.PROFILE_TYPE_VO_LIST);

			/*
			 * if( (profileBound!=null) && (profileBound.equals(OpdConfig.PROFILE_BOUND_OPD)) ) { for(int i=1;i<OpdConfig.PROFILE_TYPE_OPD.length;i++) {
			 * if(!OpdConfig.PROFILE_TYPE_OPD[i].equals("")) { Entry entry=new Entry();
			 * entry.setLabel(OpdConfig.PROFILE_TYPE_OPD[i]); entry.setValue(String.valueOf(i)); listProfileType.add(entry); } } }
			 * 
			 * if( (profileBound!=null) && (profileBound.equals(OpdConfig.PROFILE_BOUND_IPD)) ) { for(int i=1;i<OpdConfig.PROFILE_TYPE_IPD.length;i++) {
			 * 
			 * Entry entry=new Entry(); entry.setLabel(OpdConfig.PROFILE_TYPE_IPD[i]); entry.setValue(String.valueOf(i));
			 * listProfileType.add(entry); } }
			 */
			ProfileTypeMstVO profileTypeVO = null;
			if ((profileBound != null) && profileTypeVOList != null && profileTypeVOList.size() > 0)
			{
				for (int i = 0; i < profileTypeVOList.size(); i++)
				{
					profileTypeVO = new ProfileTypeMstVO();
					profileTypeVO = profileTypeVOList.get(i);
					Entry entry = new Entry();
					entry.setLabel(profileTypeVO.getProfileName());
					entry.setValue(profileTypeVO.getProfileType() + "#" + profileTypeVO.getIsUnique() + "#" + ((profileTypeVO.getDefaultName()==null)?"":profileTypeVO.getDefaultName()) + "#" + profileTypeVO.getProfileGenerationMode());
					listProfileType.add(entry);
					//_fb.setProfileGenerationMode(profileTypeVO.getProfileGenerationMode());
				}
				if (profileTypeVOList.size() == 1)
				{
					_fb.setProfileType(profileTypeVOList.get(0).getProfileType() + "#" + profileTypeVOList.get(0).getIsUnique() + "#"
							+ profileTypeVOList.get(0).getDefaultName() + "#" + profileTypeVO.getProfileGenerationMode());
					_fb.setProfileHeader(profileTypeVOList.get(0).getDefaultName());
				}
			}

			Iterator itr = lstPrevProfiles.iterator();
			while (itr.hasNext())
			{
				PatientProfileDetailVO patientProfileDtlVO = (PatientProfileDetailVO) itr.next();
				for (int i = 0; i < profileTypeVOList.size(); i++)
				{
					if (profileTypeVOList.get(i).getProfileType().equals(patientProfileDtlVO.getProfileType()))
					{
						patientProfileDtlVO.setProfileTypeDesc(profileTypeVOList.get(i).getProfileName());
					}
				}

				/*
				 * if(patientProfileDtlVO.getProfileType().equals(OpdConfig.PROFILE_TYPE_REFER)) {
				 * patientProfileDtlVO.setProfileTypeDesc(OpdConfig.PROFILE_TYPE_REFER_DESC); } else
				 * if(patientProfileDtlVO.getProfileType().equals(OpdConfig.PROFILE_TYPE_DISCHARGE)) {
				 * patientProfileDtlVO.setProfileTypeDesc(OpdConfig.PROFILE_TYPE_DISCHARGE_DESC); } else
				 * if(patientProfileDtlVO.getProfileType().equals(OpdConfig.PROFILE_TYPE_CASESHEET)) {
				 * patientProfileDtlVO.setProfileTypeDesc(OpdConfig.PROFILE_TYPE_CASESHEET_DESC); } else
				 * if(patientProfileDtlVO.getProfileType().equals(OpdConfig.PROFILE_TYPE_GENERAL)) {
				 * patientProfileDtlVO.setProfileTypeDesc(OpdConfig.PROFILE_TYPE_GENERAL_DESC); }
				 */
			}

			WebUTIL.setAttributeInSession(_rq, OpdConfig.PROFILE_TYPE_LIST, listProfileType);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PROFILE_TYPE_VO_LIST, profileTypeVOList);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST, lstPrevProfiles);

			_fb.setProfileGenerationType(OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED);

			if (lstPrevProfiles.size() > 0) objStatus.add(Status.LIST);
			objStatus.add(Status.NEW);
			if (listProfileType.size() == 0)
			{

				throw new HisRecordNotFoundException("No Profile Type Found");
			}
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	// Modifying Patient Profile
	public static boolean modifyProfile(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		boolean flag = true;
		Status objStatus = new Status();
		HttpSession session = null;

		List<PatientProfileDetailVO> lstPrevProfiles = null;
		List<PatientProfileDetailVO> lstModifyingProfiles = new ArrayList<PatientProfileDetailVO>();
		PatientProfileDetailVO patProfileDtlVO = null;
		try
		{
			UserVO userVO = getUserVO(_rq);
			session = _rq.getSession();
			lstPrevProfiles = (List<PatientProfileDetailVO>) session.getAttribute(OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST);

			for (int i = 0; i < _fb.getSelectedIndex().length; i++)
			{
				int index = Integer.parseInt(_fb.getSelectedIndex()[i]);

				patProfileDtlVO = new PatientProfileDetailVO();
				HelperMethods.populate(patProfileDtlVO, lstPrevProfiles.get(index));

				patProfileDtlVO.setPatCrNo(_fb.getPatCrNo());
				patProfileDtlVO.setEpisodeCode(_fb.getEpisodeCode());
				patProfileDtlVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
				patProfileDtlVO.setDepartmentUnitCode(_fb.getDepartmentUnitCode());
				patProfileDtlVO.setAdmissionNo(_fb.getAdmissionNo());
				if (!patProfileDtlVO.getAccessType().equalsIgnoreCase(_fb.getSelectedAccessType())
						|| !patProfileDtlVO.getUserLevel().equalsIgnoreCase(_fb.getSelectedUserLevel()))
				{
					patProfileDtlVO.setAccessType(_fb.getSelectedAccessType());
					patProfileDtlVO.setUserLevel(_fb.getSelectedUserLevel());
					lstModifyingProfiles.add(patProfileDtlVO);
				}
			}
			if (lstModifyingProfiles.size() > 0) GenericPatientProfileDATA.modifyPatientProfile(lstModifyingProfiles, userVO);
			objStatus.add(Status.LIST);
			objStatus.add(Status.NEW, "", "Profile(s) Modified Successfully");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Removing Patient Profile
	public static boolean removeProfile(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		boolean flag = true;
		Status objStatus = new Status();
		HttpSession session = null;

		List<PatientProfileDetailVO> lstPrevProfiles = null;
		List<PatientProfileDetailVO> lstDeletingProfiles = new ArrayList<PatientProfileDetailVO>();
		PatientProfileDetailVO patProfileDtlVO = null;
		try
		{
			UserVO userVO = getUserVO(_rq);
			session = _rq.getSession();
			lstPrevProfiles = (List<PatientProfileDetailVO>) session.getAttribute(OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST);

			for (int i = 0; i < _fb.getSelectedIndex().length; i++)
			{
				int index = Integer.parseInt(_fb.getSelectedIndex()[i]);
				patProfileDtlVO = lstPrevProfiles.get(index);
				lstDeletingProfiles.add(patProfileDtlVO);
			}
			if (lstDeletingProfiles.size() > 0) GenericPatientProfileDATA.removePatientProfile(lstDeletingProfiles, userVO);
			if (lstPrevProfiles.size() > 1) objStatus.add(Status.LIST);
			objStatus.add(Status.NEW, "", "Profile Removed Successfully");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Setting Essentials for Patient Profile and Previous Profiles of this Episode
	public static void setAccessPrivlEssentials(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		Map<String, Object> map = null;
		List<PatientProfileDetailVO> lstPrevProfiles = null;
		List<ProfileAccessDetailVO> lstProfileAccesses = null;
		List<Entry> lstAdded = new ArrayList<Entry>();
		List<Entry> lstNotAdded = new ArrayList<Entry>();

		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);
			session = _rq.getSession();
			lstPrevProfiles = (List<PatientProfileDetailVO>) session.getAttribute(OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST);

			// Getting Essentials
			PatientProfileDetailVO patProVO = lstPrevProfiles.get(Integer.parseInt(_fb.getAccessPrivilSerialNo()));
			/*
			 * _fb.setProfileId(patProVO.getProfileId()); _fb.setAccessType(patProVO.getAccessType());
			 */

			map = GenericPatientProfileDATA.getPatientProfilesAccessEssentials(patProVO, userVO);

			lstProfileAccesses = (List<ProfileAccessDetailVO>) map.get(OpdConfig.PATIENT_PROFILE_ACCESS_PRIVILEDGES_LIST);

			// if(_fb.getAccessType().equalsIgnoreCase(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC))
			// {
			lstNotAdded = (List<Entry>) map.get(OpdConfig.OPD_PATIENT_PROFILE_ESSENTIAL_UNIT_LIST);
			for (ProfileAccessDetailVO vo : lstProfileAccesses)
			{
				Entry entObj = null;
				for (Entry e : lstNotAdded)
				{
					if (vo.getAccessType() != null)
					{
						if (vo.getAccessType().equals(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_TO_ALL))
						{
							_fb.setSelectedUserLevel(vo.getUserLevel());
						}
						else
						{
							_fb.setSelectedUnitUserLevel(vo.getUserLevel());
						}

					}

					if (vo.getDepartmentUnitCode() != null)
					{
						if (e.getValue().equalsIgnoreCase(vo.getDepartmentUnitCode()))
						{
							entObj = e;
							break;
						}
					}
				}
				if (entObj != null) lstAdded.add(entObj);
				lstNotAdded.remove(entObj);
				if (lstAdded.size() > 0)
				{
					_fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC);
				}

			}
			map.put(OpdConfig.OPD_PATIENT_PROFILE_ESSENTIAL_UNIT_LIST, lstNotAdded);
			map.put(OpdConfig.OPD_PATIENT_PROFILE_ADDED_UNIT_LIST, lstAdded);

			List<UserVO> listAddedUser = new ArrayList();
			List<Entry> lstNotAddedUser = new ArrayList<Entry>();
			Map<String, String> mpAllUsers = new HashMap<String, String>();
			for (int i = 0; i < lstProfileAccesses.size(); i++)
			{
				ProfileAccessDetailVO vo = new ProfileAccessDetailVO();
				UserVO _userVO = new UserVO();
				vo = (ProfileAccessDetailVO) lstProfileAccesses.get(i);

				if (vo.getUserId() != null)
				{
					_userVO.setUserId(vo.getUserId());
					_userVO.setUserSeatId(vo.getUserSeatId());
					_userVO.setUserEmpID(vo.getUserEmpID());
					_userVO.setUserType(vo.getUserType());
					_userVO.setUserName(vo.getUserName());
					listAddedUser.add(_userVO);
					Entry entObj = new Entry();
					entObj.setLabel(vo.getUserName());
					entObj.setValue(vo.getUserId());
					mpAllUsers.put(entObj.getValue(), entObj.getLabel());
				}
				// UserVO userVO=new UserVO();

			}
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST, listAddedUser);
			// }
			// else if(_fb.getAccessType().equalsIgnoreCase(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_RESTRICTED_USERS))
			// {

			/*
			 * Map<String, String> mpAllUsers = new HashMap<String, String>(); for(ProfileAccessDetailVO vo :
			 * lstProfileAccesses) { if(vo.getUserId()!=null) { Entry entObj = new Entry();
			 * entObj.setLabel(vo.getUserName()); entObj.setValue(vo.getUserId()); lstAddedUser.add(entObj);
			 * mpAllUsers.put(entObj.getValue(), entObj.getLabel()); } }
			 */

			if (listAddedUser.size() > 0)
			{
				if (lstAdded.size() > 0)
				{
					_fb.setPreviousUnitUser(_fb.getPreviousUnitUser() + "#" + OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND);
				}
				else
				{
					_fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND);
				}
			}

			if ((listAddedUser.size() > 0) || (lstAdded.size() > 0))
			{
				_fb.setSelectedAccessType(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_OTHER);
			}
			map.put(OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST, listAddedUser);
			map.put(OpdConfig.OPD_PATIENT_PROFILE_ESSENTIAL_USER_LIST, lstNotAddedUser);
			map.put(OpdConfig.OPD_PATIENT_PROFILE_SEARCHED_USER_LIST, lstNotAddedUser);
			map.put(OpdConfig.OPD_PATIENT_PROFILE_ALL_USERS_MAP, mpAllUsers);
			// }
			WebUTIL.setMapInSession(map, _rq);
			objStatus.add(Status.NEW);
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	// Setting Search Users
	public static void setSearchUsers(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		List<UserVO> lstSearchUsers = new ArrayList<UserVO>();
		Map<String, String> mpAllUsers = null;
		Map map = new HashMap();
		List<Entry> lstUnit = null;
		// List<Entry> lstUnitAll = null;
		List<Entry> lstUnitLeft = new ArrayList<Entry>();
		// List<Entry> lstNotAdded = new ArrayList<Entry>();
		List<Entry> lstAdded = new ArrayList<Entry>();
		List<Entry> lstAddUnit = new ArrayList<Entry>();
		// List<Entry> allUnitList = new ArrayList<Entry>();
		HttpSession session = null;
		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);
			session = WebUTIL.getSession(_rq);

			// Setting List
			mpAllUsers = (HashMap<String, String>) session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ALL_USERS_MAP);

			lstUnit = (List<Entry>) session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ESSENTIAL_ALL_UNIT_LIST);
			// lstUnitAll=(List<Entry>)session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ESSENTIAL_ALL_UNIT_LIST);
			// lstUnitLeft=(List<Entry>)session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ESSENTIAL_ALL_UNIT_LIST);
			/*
			 * if(_fb.getUsersList().length>0) { for(String _userId : _fb.getUsersList()) { Entry entObj = new Entry();
			 * entObj.setValue(_userId); entObj.setLabel(mpAllUsers.get(_userId)); lstNotAdded.add(entObj); } }
			 * if(_fb.getSelectedUsers().length>0) { for(String _userId : _fb.getSelectedUsers()) { Entry entObj = new
			 * Entry(); entObj.setValue(_userId); entObj.setLabel(mpAllUsers.get(_userId)); lstAdded.add(entObj); } }
			 */
			/*
			 * _fb.setUsersList(new String[0]); _fb.setSelectedUsers(new String[0]);
			 */

			// Setting Search Users
			String searchStr = _fb.getSearchString();
			if (searchStr != null && !searchStr.trim().equals(""))
			{
				lstSearchUsers = GenericPatientProfileDATA.getSearchUsersForProfileAccessPrivil(_fb.getSearchMode(), searchStr.toUpperCase(), userVO);
			}
			if (lstSearchUsers.size() > 0)
			{
				for (UserVO vo : lstSearchUsers)
					mpAllUsers.put(vo.getUserId(), vo.getUserSeatId());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_SEARCHED_USER_LIST, lstSearchUsers);
				objStatus.add(Status.NEW);
				objStatus.add(Status.TRANSINPROCESS);
			}
			else objStatus.add(Status.NEW, "", "No User Found");

			List lstAddedUnit = (List) session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ADDED_UNIT_LIST);

			if (lstAddedUnit.size() > 0 || _fb.getSelectedUnits() != null && _fb.getSelectedUnits().length > 0)
			{
				_fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC + "#" + OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND);
			}
			else
			{
				_fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND);
			}

			Map<String, String> mpAllUnits = new HashMap<String, String>();
			Iterator itr = lstUnit.iterator();
			while (itr.hasNext())
			{
				Entry entry = (Entry) itr.next();
				Entry entry1 = new Entry();
				entry1.setLabel(entry.getLabel());
				entry1.setValue(entry.getValue());
				lstAddUnit.add(entry1);
				mpAllUnits.put(entry1.getValue(), entry1.getLabel());

			}
			map.put(OpdConfig.OPD_PATIENT_PROFILE_ALL_UNITS_MAP, mpAllUnits);

			Iterator iterator = lstUnit.iterator();
			while (iterator.hasNext())
			{
				Entry entObj = null;
				Entry entry = (Entry) iterator.next();
				lstUnitLeft.add(entry);
				if (_fb.getSelectedUnits() != null)
				{
					for (int i = 0; i < _fb.getSelectedUnits().length; i++)
					{
						if (entry.getValue().equalsIgnoreCase(_fb.getSelectedUnits()[i]))
						{
							entObj = entry;
							break;
						}
					}
				}
				if (entObj != null)
				{
					iterator.remove();
				}

			}

			/*
			 * if(_fb.getUnitList().length>0) { for(String _userId : _fb.getUnitList()) { Entry entObj = new Entry();
			 * entObj.setValue(_userId); entObj.setLabel(mpAllUnits.get(_userId)); lstNotAdded.add(entObj); } }
			 */

			if (_fb.getSelectedUnits().length > 0)
			{
				for (String _userId : _fb.getSelectedUnits())
				{
					Entry entObj = new Entry();
					entObj.setValue(_userId);
					entObj.setLabel(mpAllUnits.get(_userId));
					lstAdded.add(entObj);
				}
			}

			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_ESSENTIAL_ALL_UNIT_LIST, lstUnitLeft);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_ESSENTIAL_UNIT_LIST, lstUnit);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_ADDED_UNIT_LIST, lstAdded);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_ALL_USERS_MAP, mpAllUsers);
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	// Setting Search Users
	public static void addSearchUsers(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		Map<String, String> mpAllUsers = null;
		List<Entry> lstNotAdded = new ArrayList<Entry>();
		// List<Entry> lstAdded = new ArrayList<Entry>();
		List listAdded = new ArrayList();
		List searchUserList = new ArrayList();
		HttpSession session = null;
		try
		{
			// setSysdate(_rq);
			session = WebUTIL.getSession(_rq);

			// Setting List
			mpAllUsers = (HashMap<String, String>) session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ALL_USERS_MAP);
			/*
			 * if(_fb.getUsersList().length>0) { for(String _userId : _fb.getUsersList()) { Entry entObj = new Entry();
			 * entObj.setValue(_userId); entObj.setLabel(mpAllUsers.get(_userId)); lstNotAdded.add(entObj); } }
			 * if(_fb.getSelectedUsers().length>0) { for(String _userId : _fb.getSelectedUsers()) { Entry entObj = new
			 * Entry(); entObj.setValue(_userId); entObj.setLabel(mpAllUsers.get(_userId)); lstAdded.add(entObj); } }
			 */
			_fb.setUsersList(new String[0]);
			_fb.setSelectedUsers(new String[0]);

			listAdded = (List) session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST);
			searchUserList = (List) session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_SEARCHED_USER_LIST);

			for (int i = 0; i < searchUserList.size(); i++)
			{
				UserVO userVO = new UserVO();
				userVO = (UserVO) searchUserList.get(i);
				for (int j = 0; j < _fb.getSelectedUserIndex().length; j++)
				{
					if (userVO.getUserId().equals(_fb.getSelectedUserIndex()[j])) listAdded.add(searchUserList.get(i));
				}
			}
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST, listAdded);

			// Setting Search Users
			for (String userId : _fb.getSelectedUserIndex())
			{
				Entry entObj = new Entry();
				entObj.setValue(userId);
				entObj.setLabel(mpAllUsers.get(userId));
				lstNotAdded.add(0, entObj);
			}

			List lstAddedUnit = (List) session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ADDED_UNIT_LIST);

			if (lstAddedUnit.size() > 0)
			{
				_fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC + "#" + OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND);
			}
			else
			{
				_fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND);
			}

			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_ESSENTIAL_USER_LIST, lstNotAdded);
			// WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST, lstAdded);
			objStatus.add(Status.NEW);
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	// Setting Essentials for Patient Profile and Previous Profiles of this Episode
	public static void saveAccessPrivlEssentials(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		// Map<String, Object> map = null;
		// List<PatientProfileDetailVO> lstPrevProfiles = null;
		List<PatientProfileDetailVO> lstPrevProfiles = null;
		List<ProfileAccessDetailVO> lstProfileAccesses = null;
		// Map<String,Object> essentialMap=null;
		// List<Entry> lstAdded = new ArrayList<Entry>();
		// List<Entry> lstNotAdded = new ArrayList<Entry>();
		lstProfileAccesses = new ArrayList<ProfileAccessDetailVO>();
		PatientProfileDetailVO _patientProfileDtlVO = new PatientProfileDetailVO();
		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);
			// lstPrevProfiles=(List)essentialMap.get(OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST);

			// _patientProfileDtlVO.setAccessTypeFlag(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_OTHER);
			// if(_fb.getSelectedAccessType().equals(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_TO_ALL))
			// {
			lstPrevProfiles = (List) session.getAttribute(OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST);
			String accessSerialNo = _fb.getAccessPrivilSerialNo();

			PatientProfileDetailVO patProfileDtlVO = lstPrevProfiles.get(Integer.parseInt(accessSerialNo));

			_patientProfileDtlVO.setProfileId(patProfileDtlVO.getProfileId());
			_patientProfileDtlVO.setAdmissionNo(patProfileDtlVO.getAdmissionNo());
			_patientProfileDtlVO.setPatCrNo(patProfileDtlVO.getPatCrNo());
			_patientProfileDtlVO.setEpisodeCode(patProfileDtlVO.getEpisodeCode());
			_patientProfileDtlVO.setDepartmentUnitCode(patProfileDtlVO.getDepartmentUnitCode());
			_patientProfileDtlVO.setProfileType(patProfileDtlVO.getProfileType());
			_patientProfileDtlVO.setProfileHeader(patProfileDtlVO.getProfileHeader());
			_patientProfileDtlVO.setSerialNo(patProfileDtlVO.getSerialNo());
			_patientProfileDtlVO.setProfileStatus(patProfileDtlVO.getProfileStatus());
			_patientProfileDtlVO.setAccessTypeFlag(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_TO_ALL);
			_patientProfileDtlVO.setAccessType(_fb.getSelectedAccessType());
			_patientProfileDtlVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			_patientProfileDtlVO.setUserLevel(_fb.getUserLevel());
			// }

			if (_fb.getSelectedAccessType().equalsIgnoreCase(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_OTHER))
			{
				for (int i = 0; i < _fb.getSelectedUserUnitSpecificType().length; i++)
				{
					if (_fb.getSelectedUserUnitSpecificType()[i].equalsIgnoreCase(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC))
					{
						_patientProfileDtlVO.setAccessTypeFlag(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_OTHER);
						_patientProfileDtlVO.setUserLevel(_fb.getSelectedUnitUserLevel());
						for (String unitCode : _fb.getSelectedUnits())
						{
							ProfileAccessDetailVO vo = new ProfileAccessDetailVO();
							vo.setProfileId(_fb.getProfileId());
							vo.setAccessType(_fb.getAccessType());
							vo.setDepartmentUnitCode(unitCode);
							vo.setAccessType(_fb.getSelectedUserUnitSpecificType()[i]);
							lstProfileAccesses.add(vo);
						}
					}
					if (_fb.getSelectedUserUnitSpecificType()[i].equalsIgnoreCase(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND))
					{
						List userVOList = (List) session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST);

						Iterator itr = userVOList.iterator();
						while (itr.hasNext())
						{
							UserVO userVO1 = (UserVO) itr.next();
							// ProfileAccessDetailVO vo =(ProfileAccessDetailVO)itr.next();
							ProfileAccessDetailVO vo = new ProfileAccessDetailVO();
							vo.setProfileId(_fb.getProfileId());
							// vo.setAccessType(_fb.getAccessType());
							vo.setUserId(userVO1.getUserId());
							vo.setAccessType(_fb.getSelectedUserUnitSpecificType()[i]);
							lstProfileAccesses.add(vo);
						}

						/*
						 * if(_fb.getSelectedUsers().length>0) { for(String userId : _fb.getSelectedUsers()) {
						 * ProfileAccessDetailVO vo = new ProfileAccessDetailVO(); vo.setProfileId(_fb.getProfileId());
						 * //vo.setAccessType(_fb.getAccessType()); vo.setUserId(userId);
						 * vo.setAccessType(_fb.getSelectedUserUnitSpecificType()[i]); lstProfileAccesses.add(vo); } }
						 */
					}

				}
			}
			else
			{
				_patientProfileDtlVO.setAccessTypeFlag(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_TO_ALL);
				_patientProfileDtlVO.setUserLevel(_fb.getSelectedUserLevel());
			}

			// Saving Here
			// if(lstProfileAccesses.size()>0)
			GenericPatientProfileDATA.saveProfileAccessPriviledges(lstProfileAccesses, _patientProfileDtlVO, userVO);

			objStatus.add(Status.DONE, "", "Access Privileges Save Successfully");
			objStatus.add(Status.NEW);

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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	/**
	 * set the profile options based on the desk menu usability and mapping in profile type tab mapping table
	 */
	public static boolean setProfileOptionsList(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		System.out.println("*******************************in setProfileOptionsList");
		boolean flag = true;
		Status objStatus = new Status();
		HttpSession session = null;
		List<DeskMenuMasterVO> lstProMenus = null;
		List<PatientProfileDetailVO> lstPrevProfiles = null;
		String isUnique = "";
		String profileHeader = "";
		try
		{
			UserVO userVO = getUserVO(_rq);
			session = _rq.getSession();
			// Getting Profile Based Desk Menus
			lstPrevProfiles = (List) session.getAttribute(OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST);

			String profileType = _fb.getProfileType().split("#")[0];
			System.out.println("Selected Profile Type :"+_fb.getProfileType());
			if (_fb.getProfileType().split("#").length > 1)
			{
				isUnique = _fb.getProfileType().split("#")[1];
				profileHeader = _fb.getProfileHeader();
			}
			if (_fb.getDischargeModifyFlag().equals(OpdConfig.PAT_PROFILE_DTL_DISCHARGE_MODIFY_FLAG_YES))
			{
				flag = true;
			}
			else
			{
				if (isUnique.equals("1"))
				{
					Iterator itr = lstPrevProfiles.iterator();
					while (itr.hasNext())
					{
						PatientProfileDetailVO patientProfileDetailVO = (PatientProfileDetailVO) itr.next();
						if (patientProfileDetailVO.getProfileType().equals(profileType))
						{
							flag = false;
							break;
						}
					}
				}
			}

			if (flag)
			{
				if(_fb.getHmode().equalsIgnoreCase("PROFILEOPTIONS"))
				{
				session.removeAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST);
				}
				
				if (session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST) == null)
				{
					// Initializing Profile with Header and Footer
					PatientDetailVO patVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
					ProfileProforma proforma = new ProfileProforma();
					proforma.setHeader(_fb.getProfileHeader(), _fb.getRemarks(), patVO, userVO);
					proforma.setProfileType(profileType);
					List profileTypeList = (List) session.getAttribute(OpdConfig.PROFILE_TYPE_LIST);
					Iterator iteratorProfileTypeList = profileTypeList.iterator();
					while (iteratorProfileTypeList.hasNext())
					{
						Entry entry = (Entry) iteratorProfileTypeList.next();
						if ((_fb.getProfileType() != null) && (_fb.getProfileType().equals(entry.getValue())))
						{
							proforma.setProfileTypeDesc(entry.getLabel());
							break;
						}
					}

					WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);

					lstProMenus = GenericPatientProfileDATA.getProfileBasedDeskMenus(profileType, _fb.getDeskId(), userVO);

					/*
					 * DeskMenuMasterVO footerVO = new DeskMenuMasterVO(); footerVO.setDeskMenuId("99999");
					 * footerVO.setDeskMenuName("Profile Footer"); footerVO.setDeskUrl("PROFILEFOOTER");
					 * lstProMenus.add(footerVO);
					 * 
					 * 
					 * if(_fb.getProfileType()!=null) { if(_fb.getProfileType().equals(OpdConfig.PROFILE_TYPE_DISCHARGE)) {
					 * DeskMenuMasterVO footerVO1 = new DeskMenuMasterVO(); footerVO1.setDeskMenuId("99998");
					 * footerVO1.setDeskMenuName("Advice On Discharge"); footerVO1.setDeskUrl("ADVICEONDISCHARGE");
					 * lstProMenus.add(footerVO1); } }
					 * */
					 

					Map<String, String> mapMenuURL = new HashMap<String, String>();
					for (DeskMenuMasterVO vo : lstProMenus)
						mapMenuURL.put(vo.getDeskMenuId(), vo.getDeskUrl());
					proforma.setOptionsURLMap(mapMenuURL);

					//WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST, lstProMenus);
					if (lstProMenus.size() == 0) throw new HisRecordNotFoundException("No Profile Related Options in this Desk... ");
				}
				else
				{
					lstProMenus = GenericPatientProfileDATA.getProfileBasedDeskMenus(profileType, _fb.getDeskId(), userVO);
				}

				GenericPatientProfileUTIL.setMenuValidity(_fb, _rq, lstProMenus, userVO);//!! Calling Data of all Tabs and Setting it's radio on/off
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST, lstProMenus);
				
				List<ProfileTypeMstVO> profileTypeVOList = (List) session.getAttribute(OpdConfig.PROFILE_TYPE_VO_LIST);
				for (int i = 0; i < profileTypeVOList.size(); i++)
				{
					if (profileTypeVOList.get(i).getProfileType().equals(profileType))
					{
						_fb.setIsDesclaimerRequired(profileTypeVOList.get(i).getIsDesclaimerRequired());
						break;
					}
				}
			}
			else
			{ //Added by Dheeraj on 08-Dec-2018
				if(profileType.equals("12"))
				{
					objStatus.add(Status.DONE, "", "Refer Profile" + " Already Exist: To Make New Profile Delete Existing");
				}
				else
				objStatus.add(Status.DONE, "", profileHeader + " Already Exist: To Make New Profile Delete Existing");
			}

			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Move Option Up
	public static boolean moveOptionUp(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		boolean flag = true;
		Status objStatus = new Status();
		HttpSession session = null;
		try
		{
			session = _rq.getSession();
			// setSysdate(_rq);

			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			proforma.moveMenuUp(_fb.getSelectedMenuId());

			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Move Option Down
	public static boolean moveOptionDown(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		boolean flag = true;
		Status objStatus = new Status();
		HttpSession session = null;
		try
		{
			session = _rq.getSession();
			// setSysdate(_rq);

			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			proforma.moveMenuDown(_fb.getSelectedMenuId());

			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Remove Option
	public static boolean removeOption(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		boolean flag = true;
		Status objStatus = new Status();
		HttpSession session = null;
		try
		{
			session = _rq.getSession();
			// setSysdate(_rq);

			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			if (_fb.getSelectedMenuId().equals("99998")) ;
			{
				session.removeAttribute(OpdConfig.PAT_PROFILE_DTL_DISCHARGE_DRUG_DETAIL_VO_ARRAY);
				session.removeAttribute(OpdConfig.PAT_PROFILE_DTL_DISCHARGE_DIET_DETAIL_VO);
				session.removeAttribute(OpdConfig.PAT_PROFILE_DTL_DISCHARGE_REST_ADVICE_DETAIL_VO);
				session.removeAttribute(OpdConfig.PAT_PROFILE_DTL_DISCHARGE_OTHER_ADVICE_DETAIL_VO);
				session.removeAttribute(OpdConfig.PREV_OTHER_INSTRUCTION_LIST);
				session.removeAttribute(OpdConfig.PREV_OTHER_ACTIVITY_LIST);
			}
			proforma.removeMenu(_fb.getSelectedMenuId());

			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}
	
// Setting Menu Validity Based On Menu Data Availablity
	private static void setMenuValidity(GenericPatientProfileFB _fb, HttpServletRequest _rq, List<DeskMenuMasterVO> lstProMenus, UserVO userVO)
	{
		System.out.println("GenericPatientProfileUTIL.setMenuValidity()");
		Status objStatus = new Status();
		//HttpSession session = null;
		try
		{
			for (DeskMenuMasterVO vo : lstProMenus)
			{
				boolean haveData = false;
				try
				{
					PatientDetailVO voDp = new PatientDetailVO();
					HelperMethods.populate(voDp, _fb);
					voDp.setPatAdmNo(_fb.getAdmissionNo());
					if(vo.getDeskUrl().equals("DESKDIAGNOSIS"))	// Diagnosis
					{
						EpisodeDiagnosisVO[] episodeDiagnosisVOs = GenericPatientProfileDATA.getEpisodeDiagnosis(_fb.getPatCrNo(), voDp, _fb.getDeskType(), userVO, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_EPISODEDIAGNOSISVO_ARRAY, episodeDiagnosisVOs);
						if(episodeDiagnosisVOs!=null && episodeDiagnosisVOs.length>0)
							haveData = true;
					}
					else if(vo.getDeskUrl().equals("DESKALLERGIES"))	// Allergies
					{
						PatAllergyDtlVO[] patAllergyDtlVOs = GenericPatientProfileDATA.getPatientAllergiesDetail(_fb.getPatCrNo(), userVO, voDp, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_EPISODEALLERGIESVO_ARRAY, patAllergyDtlVOs);
						if(patAllergyDtlVOs!=null && patAllergyDtlVOs.length>0)
							haveData = true;
					}
					else if(vo.getDeskUrl().equals("OTVIEWING"))	// OT Detail Viewing
					{
						ProfileOTDetailVO[] profileOTDetailVOs = GenericPatientProfileDATA.getPatientOperationDetail(_fb.getPatCrNo(), userVO,voDp,OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_OPERATION_DETAIL_VO_ARRAY, profileOTDetailVOs);
						if(profileOTDetailVOs!=null && profileOTDetailVOs.length>0)
							haveData = true;
					}
					else if(vo.getDeskUrl().equals("DESKTREATMENTDETAIL"))	// Patient Treatment Detail
					{
						PatDrugTreatmentDetailVO[] patDrugTreatmentDtlVO = GenericPatientProfileDATA.getPatientTreatmentDetail(_fb.getPatCrNo(), voDp, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED, _fb.getDeskType(), userVO);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_EPISODETREATMENTVO_ARRAY, patDrugTreatmentDtlVO);
						if(patDrugTreatmentDtlVO!=null && patDrugTreatmentDtlVO.length>0)
							haveData = true;
					}
					else if(vo.getDeskUrl().equals("GENERICPATIENTALERTS"))	// Patient Chronic Disease
					{
						PatientAlertsDetailVO[] patientAlertsDetailVOs = GenericPatientProfileDATA.getPatientAlertsDetail(_fb.getPatCrNo(), userVO, voDp, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_ALERTSVO_ARRAY, patientAlertsDetailVOs);
						if(patientAlertsDetailVOs!=null && patientAlertsDetailVOs.length>0)
							haveData = true;
					}
					else if(vo.getDeskUrl().equals("IMAGEEXAMINATION"))	// Diaggrammatic Representaion
					{
						OpdPatientImageDtlVO[] episodeExamImagesVOs = GenericPatientProfileDATA.getEpisodeExamImages(_fb.getPatCrNo(), userVO, voDp, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED);
						if (episodeExamImagesVOs.length <= 0) throw new HisRecordNotFoundException("No Exam Images Found");
						WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_EPISODEEXAMIMAGESVO_ARRAY, episodeExamImagesVOs);
						if(episodeExamImagesVOs!=null && episodeExamImagesVOs.length>0)
							haveData = true;
					}
					else if(vo.getDeskUrl().equals("RESULTVIEWING"))	// Investigation Result View
					{
						ProfileInvestigationVO[] profileInvestigationVOs = GenericPatientProfileDATA.getEpisodeInvestigation(_fb.getPatCrNo(), _fb.getDeskType(), userVO, voDp, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_EPISODEINVESTIGATIONVO_ARRAY, profileInvestigationVOs);
						if(profileInvestigationVOs!=null && profileInvestigationVOs.length>0)
							haveData = true;
					}
					else if(vo.getDeskUrl().equals("DOCTORROUND"))	// Progress Notes By Doctor
					{
						DoctorRoundDtlVO[] doctorRoundDtlVOs = GenericPatientProfileDATA.getPatientProgressNotes(_fb.getPatCrNo(), userVO, voDp, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED);
						if (doctorRoundDtlVOs.length == 0)  throw new HisRecordNotFoundException("No Progress Notes Found");
						WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_PROGRESS_NOTES_ARRAY, doctorRoundDtlVOs);
						if(doctorRoundDtlVOs!=null && doctorRoundDtlVOs.length>0)
							haveData = true;
					}
					else if(vo.getDeskUrl().equals("EXTERNALEXAMINATION"))	// External Examination
					{
						EpisodeExtInvDtlVO[] arrEpisodeExtInvs = GenericPatientProfileDATA.getEpisodeExtInvestigation(_fb.getPatCrNo(),voDp, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED,userVO);
						if (arrEpisodeExtInvs.length <= 0) throw new HisRecordNotFoundException("No External Investigation Found");
						WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_EPISODE_EXT_INVESTIGATION_VO_ARRAY, arrEpisodeExtInvs);
						if(arrEpisodeExtInvs!=null && arrEpisodeExtInvs.length>0)
							haveData = true;
					}
					else if(vo.getDeskUrl().equals("DISCHARGEPROFILEFOOTER"))	// Discharge Profile Footer
					{
						haveData = true;
					}
					else if(vo.getDeskUrl().equals("PROFILEFOOTER"))	// Profile Footer
					{
						haveData = true;
					}
					
					else if(vo.getDeskUrl().equals("CHARTDETAIL"))	// Discharge Profile Vital ChartView 
					{
						haveData = true;
					}
					
					else if(vo.getDeskUrl().equals("ADVICEONDISCHARGE"))	// Advice On Discharge
					{
						haveData = true;
					}
					else if(vo.getDeskUrl().equals("GENERICTEMPLATE"))	// Generic Template
					{
						haveData = true;
						UserDeskMenuTemplateMasterVO voUDMTMst = new UserDeskMenuTemplateMasterVO();
						HelperMethods.populate(voUDMTMst, _fb);
						voUDMTMst.setDeptUnitCode(_fb.getDepartmentUnitCode());
						voUDMTMst.setDeskMenuId(vo.getDeskMenuId());
						List<Entry> lstTemps = GenericTemplateTileDATA.getDeskMenuTemplateList(_fb.getDeskType(),_fb.getPatCrNo(), voUDMTMst, userVO);
						PatientClinicalDetailVO patCliniDtlVO = new PatientClinicalDetailVO();
						HelperMethods.populate(patCliniDtlVO, _fb);
						patCliniDtlVO.setDeskMenuId(vo.getDeskMenuId());
						List<Entry> lstReportDates = GenericTemplateTileDATA.getReportDateList(_fb.getDeskType(), patCliniDtlVO, userVO);
						Map<String, Map<String, Map<String, String>>> mpChartClinicalData = null;
						mpChartClinicalData = GenericTemplateTileDATA.getPatientChartClinicalDataTempWise(_fb.getDeskType(), patCliniDtlVO, lstReportDates,
								lstTemps, userVO);
						LinkedHashMap<String, String> mapRecordDates = new LinkedHashMap<String, String>();
						for (Entry e : lstReportDates)
							mapRecordDates.put(e.getLabel(), e.getLabel());
						List<String> lstRemove = new ArrayList<String>();
						for (String recordDate : mapRecordDates.keySet())
						{
							Map<String, Map<String, String>> mpTemp = mpChartClinicalData.get(recordDate);
							if (mpTemp == null)
							{
								lstRemove.add(recordDate);
								continue;
							}
							// Removing Not Useful Templates Data
							List<String> lstRemoveTemplates = new ArrayList<String>();
							for (String key : mpTemp.keySet())
							{
								boolean flg = false;
								for (Entry entTemp : lstTemps)
									if (entTemp.getValue().split("#")[0].equals(key))
									{
										flg = true;
										break;
									}
								if (!flg) lstRemoveTemplates.add(key);
								else if (mpTemp.get(key).size() == 0) lstRemoveTemplates.add(key);
							}
							for (String tempId : lstRemoveTemplates)
								mpTemp.remove(tempId);

							// Checking For Record Date Data
							if (mpTemp.keySet().size() == 0)
							{
								mpChartClinicalData.remove(recordDate);
								lstRemove.add(recordDate);
							}
						}
						List<Entry> lstRemoveEntry = new ArrayList<Entry>();
						for (String str : lstRemove)
						{
							mapRecordDates.remove(str);
							for (Entry entObj : lstReportDates)
								if (entObj.getValue().equals(str))
								{
									lstRemoveEntry.add(entObj);
									break;
								}
						}
						//for (Entry entObj : lstRemoveEntry)
						//	lstReportDates.remove(entObj);

						if (lstReportDates == null || lstReportDates.size() == 0)
						{
							objStatus.add(Status.NEW, "", "No Template Data Found");
							haveData = false;
						}
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					haveData = false;
				}

				if(haveData)
					vo.setIsValid("1");
				else
					vo.setIsValid("0");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
	}
	
	
	// Vital Chart View
	public static boolean getChartReportingEssentials(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		
		
	Status objStatus = new Status();
	HttpSession session = WebUTIL.getSession(_rq);
	Map essentialMap = new HashMap();
	PatientDetailVO[] dailyPatientVOs = null;
	boolean flag = true;

	try
	{
		UserVO userVO = getUserVO(_rq);
		setSysdate(_rq);
		_fb.setEntryDate(WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), null));
		_fb.setToDate(WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), null));

		// Getting Patient Detail
		CRNoFB crnoFB = new CRNoFB();
		crnoFB.setPatCrNo(_fb.getPatCrNo());
		InpatientDetailUTL.getInpatientDetailByCrNo(crnoFB, _rq);
		PatientDetailVO patDtlVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);

		// Setting Desk Essentials
		_fb.setDeskType((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE));
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
		//_fb.setDeptUnitCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE));
		/*PatientDetailVO[] al = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
		PatientDetailVO voDP = null;
		for (PatientDetailVO vo : al)
			if (vo.getPatCrNo().equals(_fb.getPatCrNo()))
			{
				voDP = vo;
				break;					
			}*/
		_fb.setEpisodeCode(voDP.getEpisodeCode());
		_fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
		_fb.setDepartmentUnitCode(voDP.getDepartmentUnitCode());
		if(voDP.getPatAdmNo()!=null)
			_fb.setAdmissionNo(voDP.getPatAdmNo());
		else
			_fb.setAdmissionNo("");
		_fb.setFromDate(voDP.getEpisodeDate());

		HelperMethods.populatetToNullOrEmpty(voDP, patDtlVO);

		essentialMap = ChartReportingDATA.getChartReportingEssentials(_fb.getDeskType(), _fb.getDepartmentUnitCode(),voDP, userVO);
		String strDate = (String) essentialMap.remove(OpdConfig.OPD_ESSENTIAL_DATE_EPISODE_OR_ADMISSION_START);
		_fb.setEpiAdmStartDate(strDate);
		_fb.setFromDate(strDate);
		_fb.setSortType(Config.SORT_TYPE_ASC);
		_fb.setOldSortType(Config.SORT_TYPE_ASC);
		
		Map<String, Map> mpDefulatCharts = (Map<String, Map>)essentialMap.get(OpdConfig.OPD_ESSENTIAL_MAP_OF_ALL_DEFAULT_CHARTS_DETAILS);
		if(mpDefulatCharts.keySet().size()>0)
			_fb.setHaveDefault(true);

		WebUTIL.setMapInSession(essentialMap, _rq);
		objStatus.add(Status.TRANSINPROCESS);
		objStatus.add(Status.TRANSINPROCESS);
	}
	catch (HisRecordNotFoundException e)
	{
		e.printStackTrace();
		objStatus.add(Status.NEW, e.getMessage(), "");
		flag = false;
	}
	catch (HisDataAccessException e)
	{
		e.printStackTrace();
		objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		flag = false;
	}
	catch (HisApplicationExecutionException e)
	{
		e.printStackTrace();
		objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		flag = false;
	}
	catch (HisException e)
	{
		e.printStackTrace();
		objStatus.add(Status.ERROR, e.getMessage(), "");
		flag = false;
	}
	catch (Exception e)
	{
		e.printStackTrace();
		objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		flag = false;
	}
	finally
	{
		WebUTIL.setStatus(_rq, objStatus);
	}
	return flag;
}
	
	public static boolean getChartViewData(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
			boolean flag = true;
			Status objStatus = new Status();
			HttpSession session = WebUTIL.getSession(_rq);
			Map mp = new HashMap();

			try
			{
				UserVO userVO = getUserVO(_rq);

				PatientDetailVO patDtlVO = new PatientDetailVO();
				HelperMethods.populate(patDtlVO, _fb);
				// Getting Patient Detail Age, Gender
				PatientDetailVO ptDtlVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
				HelperMethods.populatetToNullOrEmpty(patDtlVO, ptDtlVO);
				
				List<ChartMasterVO> lstCharts = (List<ChartMasterVO>)session.getAttribute(OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHARTS_DESK_N_UNIT_WISE);
				ChartMasterVO chartDtl = null;
				for(ChartMasterVO vo : lstCharts)
					if(vo.getChartId().equals(_fb.getChartId()))
					{
						chartDtl = vo;
						break;
					}				
				_fb.setChartHeader(chartDtl.getChartName());
				_fb.setIsGraph(chartDtl.getIsGraph());
				
				List<ChartParameterMappingVO> lstChartParas = null;			
				if(_fb.getChartPara()!=null && _fb.getChartPara().length>0)
				{
					lstChartParas = new ArrayList<ChartParameterMappingVO>();
					List<ChartParameterMappingVO> lstAllPara = (List<ChartParameterMappingVO>)session.getAttribute(OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHARTS_PARAMETERS);
					for(ChartParameterMappingVO vo : lstAllPara)
					{
						for(String para : _fb.getChartPara())
							if(para.equals(vo.getParaId()))
							{
								lstChartParas.add(vo);
								break;
							}
					}
				}
				//------
				_fb.setFilterCriteria(OpdConfig.CHOICE_DATE_WISE);
				mp = ChartReportingDATA.getChartReportingData(_fb.getDeskType(), patDtlVO, chartDtl, lstChartParas, _fb.getFilterCriteria(), _fb.getFromDate(), _fb.getToDate(), userVO);

				// Setting Selected Chart in the format as added all Default Chart for using common View Tile
				Map<String, Object> mpViewChart = new HashMap();
				LinkedHashMap<String, ChartColumnHead> mpColHead = (LinkedHashMap<String, ChartColumnHead>)mp.remove(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_HEADER_DETAIL);
				LinkedHashMap<String, Map<String,ChartCellData>> mpChartData = (LinkedHashMap<String, Map<String,ChartCellData>>)mp.remove(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL);
				mpViewChart.put(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_HEADER_DETAIL, mpColHead);
				mpViewChart.put(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL, mpChartData);
				Map<String, Map> mpCharts = new HashMap<String, Map>();
				mpCharts.put(_fb.getChartId(), mpViewChart);			
				mp.put(OpdConfig.OPD_ESSENTIAL_MAP_OF_ALL_DEFAULT_CHARTS_DETAILS, mpCharts);
				
				WebUTIL.setMapInSession(mp, _rq);
				objStatus.add(Status.TRANSINPROCESS);
			}
			catch (HisRecordNotFoundException e)
			{
				flag = false;
				e.printStackTrace();
				objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			}
			catch (HisDataAccessException e)
			{
				flag = false;
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			}
			catch (HisApplicationExecutionException e)
			{
				flag = false;
				e.printStackTrace();
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			}
			catch (HisException e)
			{
				flag = false;
				e.printStackTrace();
				objStatus.add(Status.ERROR, e.getMessage(), "");
			}
			catch (Exception e)
			{
				flag = false;
				e.printStackTrace();
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			}
			finally
			{
				WebUTIL.setStatus(_rq, objStatus);
			}
			return flag;
		}





	public static boolean setChartViewDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		ProfileChartViewDtlVO chartDtlVO = new ProfileChartViewDtlVO();
		boolean flag = true;
		try
		{
			session = _rq.getSession();
			//ProfileChartViewDtlVO episodeChartViewDtlVOs = (ProfileChartViewDtlVO) session.getAttribute(OpdConfig.PATIENT_PROFILE_CHART_VIEW_DTL_VO_ARRAY);
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			List<ProfileChartViewDtlVO> lstCharts = new ArrayList<ProfileChartViewDtlVO>();
			chartDtlVO.setChartId(_fb.getChartId());
			chartDtlVO.setChartHtml(_fb.getChartHtml());
			chartDtlVO.setFromDate(_fb.getFromDate());
			chartDtlVO.setToDate(_fb.getToDate());
			lstCharts.add(chartDtlVO);
			
			proforma.setChartViewDtlProforma(_fb.getSelectedMenuId(), lstCharts);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			objStatus.add(Status.NEW);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}
	
	//END VITAL CHART VIEW
	
	

	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	// Advice On Discharge: Getting Detail 
	public static boolean getPatientAdviceOnDischargeDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		boolean flag = true;
		// List diagnosisTypeList=new ArrayList();
		// String url="";
		Map mpEssentials = null;
		List<PatDrugTreatmentDetailVO> lstDrugTreatmentDtl = null;
		List<PatExtTreatmentDetailVO> lstExtTreatmentDtl = null;
		PatDietAdviceDetailVO _patDietDetailVO = null;
		RestAdviceDtlVO _restAdviceDtlVO = null;
		PatDrugTreatmentDetailVO[] drugVO = null;
		PatExtTreatmentDetailVO[] extVO = null;
		PatientDetailVO[] dailyPatientVOs = null;
		// List<PatDrugTreatmentDetailVO> _lstDrugTreatmentDtl = null;
		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);

			_fb.setSelectedRow(new String[]
			{ "" });

			drugVO = (PatDrugTreatmentDetailVO[]) session.getAttribute(OpdConfig.PAT_PROFILE_DTL_DISCHARGE_DRUG_DETAIL_VO_ARRAY);
			_patDietDetailVO = (PatDietAdviceDetailVO) session.getAttribute(OpdConfig.PAT_PROFILE_DTL_DISCHARGE_DIET_DETAIL_VO);
			_restAdviceDtlVO = (RestAdviceDtlVO) session.getAttribute(OpdConfig.PAT_PROFILE_DTL_DISCHARGE_REST_ADVICE_DETAIL_VO);
			extVO = (PatExtTreatmentDetailVO[]) session.getAttribute(OpdConfig.PAT_PROFILE_DTL_DISCHARGE_OTHER_ADVICE_DETAIL_VO);

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
			/*PatientDetailVO[] al = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO voDP = null;
			for (PatientDetailVO vo : al)
				if (vo.getPatCrNo().equals(_fb.getPatCrNo()))
				{
					voDP = vo;
					break;
				}*/
			// _fb.setEpisodeCode(voDP.getEpisodeCode());
			// _fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
			// _fb.setAdmissionNo(voDP.getPatAdmNo());
			// _fb.setDepartmentUnitCode(voDP.getDepartmentUnitCode());

		//	mpEssentials = PatientTreatmentDetailDATA.getPatTreatmentDetailEssential(_fb.getPatCrNo(), _fb.getEpisodeCode(), _fb
			//		.getDepartmentUnitCode(), patientDetailVO.getPatGenderCode(), userVO);
			mpEssentials = PatientTreatmentDetailDATA.getPatTreatmentDetailEssential(patientDetailVO.getPatCrNo(), patientDetailVO.getEpisodeCode(), patientDetailVO.getDepartmentUnitCode(), patientDetailVO.getPatGenderCode(),patientDetailVO, userVO);
			// Setting Initial Drug Detail List

			// for modify case
			// Drug Advice for modify case
			lstDrugTreatmentDtl = new ArrayList<PatDrugTreatmentDetailVO>();
			if (drugVO != null && drugVO.length > 0)
			{
				int rows = drugVO.length;
				// String[] isEmptyStomach=_fb.getEmptyStomachIndexArray().split("#");
				for (int i = 0; i < rows; i++)
				{
					/*
					 * _fb.setSelDrugId(drugVO[i].getDrugId()); _fb.setSelDrugName(drugVO[i].getDrugName());
					 */
					PatDrugTreatmentDetailVO vo = new PatDrugTreatmentDetailVO();
					vo.setDrugId(drugVO[i].getDrugId());
					vo.setDrugName(drugVO[i].getDrugName());
					vo.setDoseId(drugVO[i].getDoseId());
					vo.setDoseName(drugVO[i].getDoseName());
					vo.setDays(drugVO[i].getDays());
					vo.setRxContinueFlag(drugVO[i].getRxContinueFlag());
					vo.setIsEmptyStomach(drugVO[i].getIsEmptyStomach());
					vo.setFrequencyId(drugVO[i].getFrequencyId());
					vo.setRemarks(drugVO[i].getRemarks());
					vo.setStartDay(drugVO[0].getStartDay());
					if (drugVO[i].getDrugRouteId()!=null && drugVO[i].getDrugRouteId().equals("-1"))
					{
						vo.setDrugRouteId("");
					}
					else
					{
						vo.setDrugRouteId(drugVO[i].getDrugRouteId());
					}
					vo.setDrugRouteName(drugVO[i].getDrugRouteName());

					lstDrugTreatmentDtl.add(vo);
				}
				// _lstDrugTreatmentDtl.add(_fb.resetVO(new PatDrugTreatmentDetailVO()));
				_fb.setDrugDetailRows(Integer.toString(rows));
				mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST, lstDrugTreatmentDtl);
				// WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST, _lstDrugTreatmentDtl);

			}
			else
			{

				lstDrugTreatmentDtl = new ArrayList<PatDrugTreatmentDetailVO>();
				lstDrugTreatmentDtl.add(_fb.resetVO(new PatDrugTreatmentDetailVO()));
				mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST, lstDrugTreatmentDtl);
			}
			// Diet Advice for modify case

			if (_patDietDetailVO != null)
			{
				_fb.setDietDays(_patDietDetailVO.getDays());
				_fb.setDietTypeId(_patDietDetailVO.getDietTypeCode());
				_fb.setDietRemark(_patDietDetailVO.getRemarks());
			}

			// Rest Advice For modify case
			if (_restAdviceDtlVO != null)
			{
				_fb.setRestDays(_restAdviceDtlVO.getDays());
				_fb.setRestReason(_restAdviceDtlVO.getRestReason());
			}

			// Other Advice for modify case
			lstExtTreatmentDtl = new ArrayList<PatExtTreatmentDetailVO>();;
			if (extVO != null && extVO.length > 0)
			{
				// Setting Entered and New Row

				int rows = extVO.length;
				for (int i = 0; i < rows; i++)
				{
					PatExtTreatmentDetailVO vo = new PatExtTreatmentDetailVO();

					// vo.setDoseId(_fb.getSelExtDoseId()[i]);
					// vo.setDoseName(_fb.getSelExtDoseName()[i]);
					vo.setFrequencyId(extVO[i].getFrequencyId());
					vo.setDays(extVO[i].getDays());
					vo.setStartDay(extVO[i].getStartDay());
					vo.setRemarks(extVO[i].getRemarks());
					vo.setRxContinueFlag(extVO[i].getRxContinueFlag());
					vo.setExtTreatmentId(extVO[i].getExtTreatmentId());
					vo.setExtTreatmentName(extVO[i].getExtTreatmentName());
					lstExtTreatmentDtl.add(vo);
				}
				// lstExtTreatmentDtl.add(_fb.resetExtTreatmentVO(new PatExtTreatmentDetailVO()));
				_fb.setExtDrugDetailRows(Integer.toString(rows));
				mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_EXT_DETAIL_LIST, lstExtTreatmentDtl);

				// _fb.setActiveTab("tabLAAdvice");
			}
			else
			{
				// Setting Initial External treatment Detail List
				lstExtTreatmentDtl = new ArrayList<PatExtTreatmentDetailVO>();
				lstExtTreatmentDtl.add(_fb.resetExtTreatmentVO(new PatExtTreatmentDetailVO()));
				mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_EXT_DETAIL_LIST, lstExtTreatmentDtl);
				_fb.setExtDrugDetailRows(Integer.toString(lstExtTreatmentDtl.size()));
			}

			// getting last visit(drug detail)
			List<PatDrugTreatmentDetailVO> prevAll = (List<PatDrugTreatmentDetailVO>) mpEssentials
					.get(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST);
			String maxEntryDate = (String) mpEssentials.get(OpdConfig.MAX_ENTRY_DATE_BY_CRNO);

			List<PatDrugTreatmentDetailVO> prevLast = new ArrayList<PatDrugTreatmentDetailVO>();
			for (PatDrugTreatmentDetailVO vo : prevAll)
			{
				if (vo.getEntryDate().equals(maxEntryDate))
				{
					prevLast.add(vo);
				}
			}

			/*
			 * int maxVisit = 0; for(PatDrugTreatmentDetailVO vo : prevAll)
			 * if(Integer.parseInt(vo.getEpisodeVisitNo())>maxVisit) maxVisit=Integer.parseInt(vo.getEpisodeVisitNo());
			 * 
			 * for(PatDrugTreatmentDetailVO vo : prevAll) if(vo.getEpisodeVisitNo().equals(Integer.toString(maxVisit))) {
			 * prevLast.add(vo); }
			 */

			// comparing end date with system date(for revoke button)
			Iterator<PatDrugTreatmentDetailVO> itr = prevLast.iterator();
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
			while (itr.hasNext())
			{
				PatDrugTreatmentDetailVO patDrugTreatmentDetailVO = itr.next();
				if(patDrugTreatmentDetailVO!=null)
				{	
				
				if(patDrugTreatmentDetailVO.getEndDate()!=null)
				{Date endDate =null;
				endDate = df.parse(patDrugTreatmentDetailVO.getEndDate());
				Date sysDate = df.parse(WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
				if (endDate.before(sysDate))
				{
					patDrugTreatmentDetailVO.setDate("before");

				}
				else
				{
					patDrugTreatmentDetailVO.setDate("after");
				}
				}
				if (patDrugTreatmentDetailVO.getRemarks() == null)
				{
					patDrugTreatmentDetailVO.setRemarks("");
				}
				/*
				 * if(patDrugTreatmentDetailVO.getRxContinue().equals("2")) { itr.remove(); }
				 */

			}}

			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST, prevLast);

			// for removing revoked drug
			Iterator<PatDrugTreatmentDetailVO> drugItr = prevLast.iterator();
			while (drugItr.hasNext())
			{
				PatDrugTreatmentDetailVO patDrugTreatmentDetailVO = drugItr.next();
				if (patDrugTreatmentDetailVO.getRxContinue().equals(OpdConfig.REVOKE))
				{
					drugItr.remove();
				}
			}
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST, prevLast);

			// getting today visit(drug detail)
			List<PatDrugTreatmentDetailVO> todayDrugDetailList = new ArrayList<PatDrugTreatmentDetailVO>();
			Iterator<PatDrugTreatmentDetailVO> prevAllDrugDetailItr = prevAll.iterator();
			while (prevAllDrugDetailItr.hasNext())
			{
				PatDrugTreatmentDetailVO patDrugTreatmentDetailVO = prevAllDrugDetailItr.next();
				if (patDrugTreatmentDetailVO.getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo()))
				{

					todayDrugDetailList.add(patDrugTreatmentDetailVO);

				}
			}
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_TODAY_DRUG_DETAIL_LIST, todayDrugDetailList);

			// concatenating drugId with itemtypeId
			// List<DrugDoseVO> drugDoseVOList =
			// (List<DrugDoseVO>)mpEssentials.get(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES);
			List<Entry> drugList = (List<Entry>) mpEssentials.get(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS);
			Iterator<PatDrugTreatmentDetailVO> iter = lstDrugTreatmentDtl.iterator();

			while (iter.hasNext())
			{
				PatDrugTreatmentDetailVO patDrugTreatmentDetailVO = iter.next();
				Iterator<Entry> itera = drugList.iterator();
				while (itera.hasNext())
				{
					Entry obj = itera.next();
					String[] itemIdAndItemTypeId = obj.getValue().split("#");
					String itemId = itemIdAndItemTypeId[0];
					String ItemTypeId = itemIdAndItemTypeId[1];
					if (patDrugTreatmentDetailVO.getDrugId().equals(itemId))
					{
						if (patDrugTreatmentDetailVO.getRemarks() == null)
						{
							patDrugTreatmentDetailVO.setRemarks("");
						}
						patDrugTreatmentDetailVO.setDrugId(patDrugTreatmentDetailVO.getDrugId() + "#" + ItemTypeId);
					}
				}
			}

			// mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST, lstDrugTreatmentDtl);
			_fb.setDrugDetailRows(Integer.toString(lstDrugTreatmentDtl.size()));

			/** *************set Essential for rest advice detail ****************************************** */

			// setting the rest start date(system date)
			String sysDate = WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			_fb.setRestStartDate(String.valueOf(sysDate));

			// getting today visit(rest advice)
			List prevAllRestAdvice = (List<PatDrugTreatmentDetailVO>) mpEssentials.get(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_REST_DETAIL_LIST);
			List todayAllRestAdvice = new ArrayList();

			Iterator prevAllRestAdviceItr = prevAllRestAdvice.iterator();
			while (prevAllRestAdviceItr.hasNext())
			{
				RestAdviceDtlVO restAdviceDtlVO = (RestAdviceDtlVO) prevAllRestAdviceItr.next();
				if (restAdviceDtlVO.getEntryDate().equals(sysDate.toString()))
				{
					todayAllRestAdvice.add(restAdviceDtlVO);
					prevAllRestAdviceItr.remove();
				}
			}
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_TODAY_REST_DETAIL_LIST, todayAllRestAdvice);

			/** ******************************set Essential for external treatment***************************** */

			List deptUnitWiseInstList = (List) mpEssentials.get(OpdConfig.OTHER_INSTRUCTION_LIST_BY_DEPTUNITCODE_AND_GENDER);
			List deptUnitWiseActivityList = (List) mpEssentials.get(OpdConfig.ONE_TIME_ACTIVITY_LIST_BY_DEPTUNITCODE_AND_GENDER);

			WebUTIL.setAttributeInSession(_rq, OpdConfig.OTHER_INSTRUCTION_LIST_FOR_BOTH, deptUnitWiseInstList);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.ONE_TIME_ACTIVITY_LIST_FOR_BOTH, deptUnitWiseActivityList);

			String selectedChoice = (String) session.getAttribute(OpdConfig.SELECTED_CHOICE);

			if (selectedChoice == null)
			{
				WebUTIL.setAttributeInSession(_rq, OpdConfig.SELECTED_CHOICE, "0");
			}
			else if (selectedChoice.equals("1"))
			{
				List allInstList = (List) session.getAttribute(OpdConfig.ALL_OTHER_INSTRUCTION_LIST_BY_GENDER);
				List allActivityList = (List) session.getAttribute(OpdConfig.ALL_ONE_TIME_ACTIVITY_LIST_BY_GENDER);

				WebUTIL.setAttributeInSession(_rq, OpdConfig.OTHER_INSTRUCTION_LIST_FOR_BOTH, allInstList);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.ONE_TIME_ACTIVITY_LIST_FOR_BOTH, allActivityList);
			}
			else if (selectedChoice.equals("0"))
			{
				List _deptUnitWiseInstList = (List) session.getAttribute(OpdConfig.OTHER_INSTRUCTION_LIST_BY_DEPTUNITCODE_AND_GENDER);
				List _deptUnitWiseActivityList = (List) session.getAttribute(OpdConfig.ONE_TIME_ACTIVITY_LIST_BY_DEPTUNITCODE_AND_GENDER);

				WebUTIL.setAttributeInSession(_rq, OpdConfig.OTHER_INSTRUCTION_LIST_FOR_BOTH, _deptUnitWiseInstList);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.ONE_TIME_ACTIVITY_LIST_FOR_BOTH, _deptUnitWiseActivityList);

			}
			// getting last visit(External Treatment detail)
			List<PatExtTreatmentDetailVO> prevAllExt = (List<PatExtTreatmentDetailVO>) mpEssentials
					.get(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_EXT_TREATMENT_DETAIL_LIST);
			List<PatExtTreatmentDetailVO> prevLastExt = new ArrayList<PatExtTreatmentDetailVO>();
			/*
			 * int maxVisitExt = 0; for(PatExtTreatmentDetailVO vo : prevAllExt)
			 * if(Integer.parseInt(vo.getEpisodeVisitNo())>maxVisitExt) maxVisitExt=Integer.parseInt(vo.getEpisodeVisitNo());
			 * 
			 * for(PatExtTreatmentDetailVO vo : prevAllExt) if(vo.getEpisodeVisitNo().equals(Integer.toString(maxVisitExt)))
			 * prevLastExt.add(vo);
			 */
			String maxEntryDateForExtTreat = (String) mpEssentials.get(OpdConfig.MAX_ENTRY_DATE_BY_CRNO_FOR_EXTTREAT);
			for (PatExtTreatmentDetailVO vo : prevAllExt)
			{
				if (vo.getEntryDate().equals(maxEntryDateForExtTreat))
				{
					prevLastExt.add(vo);
				}
			}
			/*
			 * //for removing revoked drug Iterator extTreatItr = prevLastExt.iterator(); while(extTreatItr.hasNext()) {
			 * PatExtTreatmentDetailVO vo = (PatExtTreatmentDetailVO)extTreatItr.next(); if(vo.getRxContinue().equals("0")) {
			 * extTreatItr.remove(); } }
			 */

			// comparing end date with system date(for extRevoke button)
			Iterator<PatExtTreatmentDetailVO> prevLastExtItr = prevLastExt.iterator();
			while (prevLastExtItr.hasNext())
			{
				PatExtTreatmentDetailVO patExtTreatmentDetailVO = prevLastExtItr.next();
				Date endDateExt = df.parse(patExtTreatmentDetailVO.getEndDate());
				Date sysDateExt = df.parse(WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
				if (endDateExt.before(sysDateExt))
				{
					patExtTreatmentDetailVO.setDate("before");

				}
				if (patExtTreatmentDetailVO.getRemarks() == null || patExtTreatmentDetailVO.getRemarks().equals(null))
				{
					patExtTreatmentDetailVO.setRemarks("");
				}
				if (patExtTreatmentDetailVO.getRxContinue().equals(OpdConfig.REVOKE))
				{
					prevLastExtItr.remove();
				}
			}

			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_EXT_TREATMENT_DETAIL_LIST, prevLastExt);

			// getting today visit(drug detail)
			List<PatExtTreatmentDetailVO> todayExtDetailList = new ArrayList<PatExtTreatmentDetailVO>();
			Iterator<PatExtTreatmentDetailVO> prevAllExtDetailItr = prevAllExt.iterator();
			while (prevAllExtDetailItr.hasNext())
			{
				PatExtTreatmentDetailVO patExtTreatmentDetailVO = prevAllExtDetailItr.next();
				if (patExtTreatmentDetailVO.getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo()))
				{
					todayExtDetailList.add(patExtTreatmentDetailVO);
				}
			}
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_TODAY_EXT_DETAIL_LIST, todayExtDetailList);

			/** ************************ set essential for diet advice detail***************************** */

			// setting the Diet start date(system date)
			_fb.setDietStartDate(String.valueOf(sysDate));

			// getting today visit(Diet advice)
			List<PatDietAdviceDetailVO> prevAllDietAdvice = (List<PatDietAdviceDetailVO>) mpEssentials
					.get(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DIET_ADVICE_DETAIL_LIST);
			List<PatDietAdviceDetailVO> todayAllDietAdvice = new ArrayList();

			Iterator<PatDietAdviceDetailVO> prevAllDietAdviceItr = prevAllDietAdvice.iterator();
			while (prevAllDietAdviceItr.hasNext())
			{
				PatDietAdviceDetailVO patDietAdviceDetailVO = prevAllDietAdviceItr.next();
				if (patDietAdviceDetailVO.getEntryDate().equals(sysDate.toString()))
				{
					todayAllDietAdvice.add(patDietAdviceDetailVO);
					prevAllDietAdviceItr.remove();
				}
			}
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_TODAY_DIET_DETAIL_LIST, todayAllDietAdvice);

			WebUTIL.setMapInSession(mpEssentials, _rq);

			// EpisodeDiagnosisVO[] episodeDiagnosisVOs = GenericPatientProfileDATA.getEpisodeDiagnosis(_fb.getPatCrNo(),
			// _fb.getEpisodeCode(),_fb.getAdmissionNo(),_fb.getDeskType(), userVO);
			// WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_EPISODEDIAGNOSISVO_ARRAY,
			// episodeDiagnosisVOs);

			/*
			 * ////DIAGNOSIS PROFORMA ProfileProforma proforma =
			 * (ProfileProforma)session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			 * 
			 * List lstProMenus = (List)session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST);
			 * if(proforma!=null) { //Map<String, StringBuilder> map = proforma.getAddedOptionsMap(); List lstMenus =
			 * proforma.getOptionsOrderList();
			 * 
			 * for(int i=0;i<lstMenus.size();i++) { String key = (String)lstMenus.get(i); DeskMenuMasterVO vo =null;
			 * Iterator lstProMenusItr = lstProMenus.iterator(); while(lstProMenusItr.hasNext()) { DeskMenuMasterVO v =
			 * (DeskMenuMasterVO)lstProMenusItr.next(); if(v.getDeskMenuId().equals(key)) { vo=v; break; } } url =
			 * vo.getDeskUrl();
			 * 
			 * 
			 * if(url.equals("DESKDIAGNOSIS")) { Map<String,Map<String,Object>>
			 * mapProfileOptions=proforma.getMapProfileOptions(); Map<String,Object> mpDiag = mapProfileOptions.get(key);
			 * diagnosisTypeList=(List)mpDiag.get("lstEpisodeDiagnosisVO"); } }
			 * 
			 * String strDiagnosisCheckFlag=""; if(episodeDiagnosisVOs!=null) { for(int i=0;i<episodeDiagnosisVOs.length;i++) {
			 * boolean diagTypeFlag=false; Iterator diagIterator=diagnosisTypeList.iterator(); while(diagIterator.hasNext()) {
			 * EpisodeDiagnosisVO vo=(EpisodeDiagnosisVO)diagIterator.next();
			 * 
			 * if(vo.getDiagnosticCode().equals(episodeDiagnosisVOs[i].getDiagnosticCode())) { diagTypeFlag=true; } }
			 * 
			 * if(diagTypeFlag==true) { strDiagnosisCheckFlag+=OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_YES+"#"; }
			 * else { strDiagnosisCheckFlag+=OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_NO+"#"; } }
			 * 
			 * 
			 * if(strDiagnosisCheckFlag.length()>0)
			 * 
			 * strDiagnosisCheckFlag=strDiagnosisCheckFlag.substring(0,strDiagnosisCheckFlag.length()-1);
			 * 
			 * _fb.setDiagnosisCheckFlag(strDiagnosisCheckFlag); } }
			 */

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Advice On Discharge: Setting Patient Diagnosis Detail To Profile
	public static boolean setPatientAdviceOnDischargeDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		boolean flag = true;
		List lstDietType = null;
		List lstDrugRoutes = null;
		DrugFrequencyMstVO[] drugFrequencyMstVO;
		List<PatDrugTreatmentDetailVO> drugVOList = null;
		List<PatExtTreatmentDetailVO> extVOList = null;
		// List<PatDrugTreatmentDetailVO> lstDrugTreatmentDtl = null;
		try
		{
			session = _rq.getSession();

			lstDrugRoutes = (List) session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE);
			lstDietType = (List) session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_ALL_DIET_TYPE_LIST);
			drugFrequencyMstVO = (DrugFrequencyMstVO[]) session.getAttribute(OpdConfig.ESSENTIALS__DOSAGE_FREQUECY_ARRAY);

			// EpisodeDiagnosisVO[] episodeDiagnosisVOs =
			// (EpisodeDiagnosisVO[])session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_EPISODEDIAGNOSISVO_ARRAY);

			/** **************************saving drug detail********************************* */
			// Saving Drug Detail
			List drugDoseVOLst = (List) session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES);
			DrugFrequencyMstVO[] drugFreqMstVoArray = (DrugFrequencyMstVO[]) session.getAttribute(OpdConfig.ESSENTIALS__DOSAGE_FREQUECY_ARRAY);
			String doseQty = "";
			String isFrequencyBound = "";
			String freqValue = "";
			Integer totalPerDayDoseQty = 0;
			// lstDrugTreatmentDtl= new ArrayList<PatDrugTreatmentDetailVO>();
			PatDrugTreatmentDetailVO[] drugVO = null;
			String[] isEmptyStomach = _fb.getEmptyStomachIndexArray().split("#");
			// drugVO=new PatDrugTreatmentDetailVO[Integer.parseInt(_fb.getDrugDetailRows())];
			drugVOList = new ArrayList<PatDrugTreatmentDetailVO>();
			for (int i = 0; i < Integer.parseInt(_fb.getDrugDetailRows()); i++)
			{
				/*
				 * if(!_fb.getSelDrugName()[i].equals("")) { drugVO[i]=new PatDrugTreatmentDetailVO(); }
				 */

				Map drugSchedule = (Map) session.getAttribute(OpdConfig.DRUG_SHEDULE_MAP);
				if (drugSchedule == null)
				{
					drugSchedule = new HashMap();
				}
				String key = _fb.getSelDrugId()[i] + "#" + _fb.getSelFrequencyId()[i] + "#" + _fb.getSelDoseId()[i].split("\\^")[0];
				if (drugSchedule.containsKey(key))
				{

					List drugScheduleLst = (List) drugSchedule.get(key);

					doseQty = "0";
					isFrequencyBound = "0";
					freqValue = "1";
					totalPerDayDoseQty = 0;
					// getting frequency value from frequency master for calculating dose qty
					for (int j = 0; j < drugFreqMstVoArray.length; j++)
					{
						DrugFrequencyMstVO vo = drugFreqMstVoArray[j];
						if (_fb.getSelFrequencyId()[i].equals(vo.getFrequencyId()))
						{
							freqValue = vo.getFrequency();
						}
					}

					// String lstSize=drugScheduleLst.size();
					if (Integer.parseInt(freqValue) != (drugScheduleLst.size()))
					{
						drugSchedule.remove(_fb.getSelDrugId()[i]);
						drugScheduleLst.clear();
					}

					for (int k = 0; k < drugScheduleLst.size(); k++)
					{
						DrugSheduleDtlVO drugScheduleVO = (DrugSheduleDtlVO) drugScheduleLst.get(k);

						// checking isFrequency bound
						Iterator itrIsFreqBoundForSched = drugDoseVOLst.iterator();
						while (itrIsFreqBoundForSched.hasNext())
						{
							DrugDoseVO vo = (DrugDoseVO) itrIsFreqBoundForSched.next();
							if (drugScheduleVO.getDoseId().equals(vo.getDoseId()))
							{
								isFrequencyBound = vo.getIsFrequencyBound();
							}
						}

						// getting doseQty
						Iterator itrForDoseQtySched = drugDoseVOLst.iterator();
						while (itrForDoseQtySched.hasNext())
						{
							DrugDoseVO vo = (DrugDoseVO) itrForDoseQtySched.next();
							if (drugScheduleVO.getDoseId().equals(vo.getDoseId()))
							{
								doseQty = vo.getDoseQty();
							}
						}

						if (isFrequencyBound.equals(OpdConfig.IS_FREQUENCY_BOUND))
						{
							totalPerDayDoseQty = totalPerDayDoseQty + Integer.parseInt(doseQty);
						}
						else
						{
							/*
							 * if(totalPerDayDoseQty<Integer.parseInt(doseQty)) {
							 * totalPerDayDoseQty=Integer.parseInt(doseQty); } else { totalPerDayDoseQty=totalPerDayDoseQty; }
							 */
							if(i<_fb.getQuantity().length)	//  To handle ArrayIndexOutOfBoundException. Added by Adil on 26-Dec-2012
							{
								if(_fb.getQuantity()[i]==null)	// To handle NullPointerException. Added by Adil on 26-Dec-2012
									_fb.getQuantity()[i]="1";
								else if(_fb.getQuantity()[i].equals("")) // To handle NullPointerException. Added by Adil on 26-Dec-2012
									_fb.getQuantity()[i]="1";
								
								totalPerDayDoseQty = Integer.parseInt(_fb.getQuantity()[i]);
							}
							else								//If totalPerDayDoseQty did not set in just above block, then setting it in else block;
							{
								if(_fb.getQuantity().length==0)
									totalPerDayDoseQty =1;
							}

						}

					}
				}
				if (!drugSchedule.containsKey(key))
				{
					doseQty = "0";
					isFrequencyBound = "0";
					freqValue = "1";
					totalPerDayDoseQty = 0;

					// getting frequency value from frequency master for calculating dose qty
					DrugFrequencyMstVO vo = null;
					for (int j = 0; j < drugFreqMstVoArray.length; j++)
					{
						vo = drugFreqMstVoArray[j];
						if (_fb.getSelFrequencyId()[i].equals(vo.getFrequencyId()))
						{
							freqValue = vo.getFrequency();
							if (freqValue.equals("0"))
							{
								freqValue = "1";
							}
							break;
						}
					}

					List list = new ArrayList();
					DrugSheduleDtlVO drugScheduleVO = new DrugSheduleDtlVO();
					for (int j = 0; j < Integer.parseInt(freqValue); j++)
					{
						drugScheduleVO = new DrugSheduleDtlVO();
						if (vo != null)
						{
							if (vo.getMorDoseTime() != null && j == 0)
							{
								drugScheduleVO.setDoseTime(vo.getMorDoseTime());
								drugScheduleVO.setDoseShift(OpdConfig.MORNING_SHIFT_ID);
							}
							else if (vo.getAftDoseTime() != null && j == 1)
							{
								drugScheduleVO.setDoseTime(vo.getAftDoseTime());
								drugScheduleVO.setDoseShift(OpdConfig.NOON_SHIFT_ID);
							}
							else if (vo.getEveDoseTime() != null && j == 2)
							{
								drugScheduleVO.setDoseTime(vo.getEveDoseTime());
								drugScheduleVO.setDoseShift(OpdConfig.EVENING_SHIFT_ID);
							}
							else if (vo.getNgtDoseTime() != null)
							{
								drugScheduleVO.setDoseTime(vo.getNgtDoseTime());
								drugScheduleVO.setDoseShift(OpdConfig.NIGHT_SHIFT_ID);
							}

						}
						// drugScheduleVO.setDoseShift(profileDrugScheduleVO.getDoseShift());
						// drugScheduleVO.setDoseTime(profileDrugScheduleVO.getDoseTime());
						drugScheduleVO.setIsEmptyStomach(isEmptyStomach[i]);
						drugScheduleVO.setDoseId(_fb.getSelDoseId()[i].split("\\^")[0]);
						drugScheduleVO.setPopupDrugId(_fb.getSelDrugId()[i]);
						drugScheduleVO.setDoseName(_fb.getSelDoseName()[i]);
						list.add(drugScheduleVO);
					}

					drugSchedule.put(key, list);
					session.setAttribute(OpdConfig.DRUG_SHEDULE_MAP, drugSchedule);
				}

				// getting dose qty
				// String finalDoseqty=getDoseQty(doseQty, isFrequencyBound,
				// _fb.getSelDays()[i],totalPerDayDoseQty.toString());

				// drugVO.setDoseQty(finalDoseqty);

				// because for new record requiredQty=doseQty
				// drugVO.setRequiredQty(finalDoseqty);

				if (!_fb.getSelDrugName()[i].equals(""))
				{
					PatDrugTreatmentDetailVO patDrugTreatmentVO = new PatDrugTreatmentDetailVO();
					patDrugTreatmentVO.setIssueQty("0");

					patDrugTreatmentVO.setDrugId(_fb.getSelDrugId()[i]);
					patDrugTreatmentVO.setDrugName(_fb.getSelDrugName()[i]);
					patDrugTreatmentVO.setDoseId(_fb.getSelDoseId()[i].split("\\^")[0]);
					patDrugTreatmentVO.setDoseName(_fb.getSelDoseName()[i]);
					patDrugTreatmentVO.setFrequencyId(_fb.getSelFrequencyId()[i]);
					if (drugFrequencyMstVO != null)
					{
						for (int j = 0; j < drugFrequencyMstVO.length; j++)
						{
							if (_fb.getSelFrequencyId()[i].equals(drugFrequencyMstVO[j].getFrequencyId()))
							{
								patDrugTreatmentVO.setFrequencyName(drugFrequencyMstVO[j].getFrequencyName());
							}
						}
					}
					patDrugTreatmentVO.setDays(_fb.getSelDays()[i]);
				//	patDrugTreatmentVO.setDoseQty(_fb.getSelDoseQuantity()[i]);
					patDrugTreatmentVO.setStartDay(_fb.getSelStartDay()[i]);
					patDrugTreatmentVO.setRemarks(_fb.getSelInstructions()[i]);
					patDrugTreatmentVO.setRxContinueFlag(_fb.getRxContinueFlag()[i]);
					patDrugTreatmentVO.setEndDate(_fb.getEndDate()[i]);
					// setting next visit
					patDrugTreatmentVO.setEpisodeCode(_fb.getEpisodeCode());
					patDrugTreatmentVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
					patDrugTreatmentVO.setPatCrNo(_fb.getPatCrNo());
					patDrugTreatmentVO.setAdmissionNo(_fb.getAdmissionNo());
					// drugVO.setSysDate(sysadteString);
					patDrugTreatmentVO.setIsEmptyStomach(isEmptyStomach[i]);
					if (isEmptyStomach[i] != null)
					{
						if (isEmptyStomach[i].equals("0"))
						{
							patDrugTreatmentVO.setIsEmptyStomachName("No");
						}
						else if (isEmptyStomach[i].equals("1"))
						{
							patDrugTreatmentVO.setIsEmptyStomachName("Yes");
						}
					}
					patDrugTreatmentVO.setDrugRouteId(_fb.getSelDrugRouteId()[i]);

					Iterator itr = lstDrugRoutes.iterator();
					while (itr.hasNext())
					{
						DrugRouteMstVO _drugRouteMstVO = (DrugRouteMstVO) itr.next();

						if (_fb.getSelDrugRouteId()[i].equals(_drugRouteMstVO.getDrugRouteId()))
						{
							patDrugTreatmentVO.setDrugRouteName(_drugRouteMstVO.getDrugRouteName());
						}
					}
					patDrugTreatmentVO.setRevokeStatus("false");
					drugVOList.add(patDrugTreatmentVO);
				}

			}
			drugVO = new PatDrugTreatmentDetailVO[drugVOList.size()];
			for (int i = 0; i < drugVOList.size(); i++)
			{
				drugVO[i] = (PatDrugTreatmentDetailVO) drugVOList.get(i);
			}
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_PROFILE_DTL_DISCHARGE_DRUG_DETAIL_VO_ARRAY, drugVO);

			// ///Diet Advice

			PatDietAdviceDetailVO _patDietDetailVO = null;
			if (!(_fb.getDietDays().equals("")))
			{
				_patDietDetailVO = new PatDietAdviceDetailVO();
				_patDietDetailVO.setDays(_fb.getDietDays());
				_patDietDetailVO.setDietTypeCode(_fb.getDietTypeId());
				// _patDietDetailVO.setDietTypeDesc(_fb.getDietType());
				_patDietDetailVO.setRemarks(_fb.getDietRemark());

				Iterator itr = lstDietType.iterator();
				while (itr.hasNext())
				{
					Entry entry = (Entry) itr.next();

					if (_fb.getDietTypeId().equals(entry.getValue()))
					{
						_patDietDetailVO.setDietTypeDesc(entry.getLabel());
					}
				}
				// _patDietDetailVO.setStartDate(_fb.getDietStartDate());
				// _patDietDetailVO.setSerialNo(_fb.getDietSerialNo());

				_patDietDetailVO.setEpisodeCode(_fb.getEpisodeCode());
				_patDietDetailVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
				_patDietDetailVO.setPatCrNo(_fb.getPatCrNo());
				_patDietDetailVO.setAdmissionNo(_fb.getAdmissionNo());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_PROFILE_DTL_DISCHARGE_DIET_DETAIL_VO, _patDietDetailVO);
			}
			else
			{
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_PROFILE_DTL_DISCHARGE_DIET_DETAIL_VO, _patDietDetailVO);
			}

			// /Rest Advice
			RestAdviceDtlVO restAdviceDtlVO = null;
			if (!(_fb.getRestDays().equals("")))
			{
				restAdviceDtlVO = new RestAdviceDtlVO();
				restAdviceDtlVO.setDays(_fb.getRestDays());
				restAdviceDtlVO.setRestReason(_fb.getRestReason());
				// restAdviceDtlVO.setRemarks(_fb.getRestRemark());
				// restAdviceDtlVO.setStartDate(_fb.getRestStartDate());
				// restAdviceDtlVO.setSerialNo(_fb.getRestSerialNo());
				restAdviceDtlVO.setEpisodeCode(_fb.getEpisodeCode());
				restAdviceDtlVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
				restAdviceDtlVO.setPatCrNo(_fb.getPatCrNo());
				restAdviceDtlVO.setAdmissionNo(_fb.getAdmissionNo());
				restAdviceDtlVO.setCirtificateStatus("0");// status for medical certificate which is not generated
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_PROFILE_DTL_DISCHARGE_REST_ADVICE_DETAIL_VO, restAdviceDtlVO);
			}
			else
			{
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_PROFILE_DTL_DISCHARGE_REST_ADVICE_DETAIL_VO, restAdviceDtlVO);
			}

			// Other Advice
			// List<PatExtTreatmentDetailVO> lstExtTreatmentDtl= new ArrayList<PatExtTreatmentDetailVO>();
			PatExtTreatmentDetailVO[] extVO = null;
			// extVO=new PatExtTreatmentDetailVO[Integer.parseInt(_fb.getExtDrugDetailRows())];
			extVOList = new ArrayList<PatExtTreatmentDetailVO>();
			for (int i = 0; i < Integer.parseInt(_fb.getExtDrugDetailRows()); i++)
			{
				// extVO[i] = new PatExtTreatmentDetailVO();

				PatExtTreatmentDetailVO patExtTreatmentDetailVO = new PatExtTreatmentDetailVO();
				// extVO.setDoseId(_fb.getSelExtDoseId()[i]);
				// extVO.setDoseName(_fb.getSelExtDoseName()[i]);
				if (!_fb.getSelExtTreatmentName()[i].equals(""))
				{
					patExtTreatmentDetailVO.setExtTreatmentName(_fb.getSelExtTreatmentName()[i]);
					if (_fb.getSelExtTreatmentId()[i].equals("-1"))
					{
						patExtTreatmentDetailVO.setIsMasterBound(OpdConfig.IS_NOT_MASTER_BOUND);
					}
					else
					{
						patExtTreatmentDetailVO.setIsMasterBound(OpdConfig.IS_MASTER_BOUND);
					}
					patExtTreatmentDetailVO.setFrequencyId(_fb.getSelExtFrequencyId()[i]);
					patExtTreatmentDetailVO.setDays(_fb.getSelExtDays()[i]);
					patExtTreatmentDetailVO.setStartDay(_fb.getSelExtStartDay()[i]);
					patExtTreatmentDetailVO.setRemarks(_fb.getSelExtInstructions()[i]);
					patExtTreatmentDetailVO.setRxContinueFlag(_fb.getRxContinueFlag()[i]);
					patExtTreatmentDetailVO.setExtTreatmentId(_fb.getSelExtTreatmentId()[i]);
					patExtTreatmentDetailVO.setExtTreatmentName(_fb.getSelExtTreatmentName()[i]);
					patExtTreatmentDetailVO.setTreatmentType(OpdConfig.EXTERNAL_TREATMENT);
					// setting visit detail
					patExtTreatmentDetailVO.setEpisodeCode(_fb.getEpisodeCode());
					patExtTreatmentDetailVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
					patExtTreatmentDetailVO.setPatCrNo(_fb.getPatCrNo());
					patExtTreatmentDetailVO.setAdmissionNo(_fb.getAdmissionNo());
					patExtTreatmentDetailVO.setRxContinue(OpdConfig.NEW_RECORD);
					patExtTreatmentDetailVO.setRevokeStatus("");
					patExtTreatmentDetailVO.setTodayVisitFlag("");
					extVOList.add(patExtTreatmentDetailVO);
					// lstExtTreatmentDtl.add(extVO);
				}
			}
			extVO = new PatExtTreatmentDetailVO[extVOList.size()];
			for (int i = 0; i < extVOList.size(); i++)
			{
				extVO[i] = (PatExtTreatmentDetailVO) extVOList.get(i);
			}
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_PROFILE_DTL_DISCHARGE_OTHER_ADVICE_DETAIL_VO, extVO);

			/*
			 * //removing the blank row from lstExtTreatmentDtl bcz user can fill or not fill the last row Iterator<PatExtTreatmentDetailVO>
			 * removeBlankRowExtItr=lstExtTreatmentDtl.iterator(); while(removeBlankRowExtItr.hasNext()) {
			 * PatExtTreatmentDetailVO patExtTreatmentDetailVO=removeBlankRowExtItr.next();
			 * if(patExtTreatmentDetailVO.getDays().equals("")) { removeBlankRowExtItr.remove(); } }
			 */

			// Other Instructions and activities
			List selOtherInstList = new ArrayList();
			List selActivityList = new ArrayList();
			selOtherInstList = (List) session.getAttribute(OpdConfig.PREV_OTHER_INSTRUCTION_LIST);
			selActivityList = (List) session.getAttribute(OpdConfig.PREV_OTHER_ACTIVITY_LIST);

			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			proforma.setAdviceOnDischargeProforma(_fb.getSelectedMenuId(), drugVO, _patDietDetailVO, restAdviceDtlVO, extVO, selOtherInstList,
					selActivityList);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Advice On Discharge:Setting Discharge Profile Instruction
	public static void saveOtherInstruction(GenericPatientProfileFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_request);
		List otherInstructionList = new ArrayList();
		try
		{
			// UserVO userVO = getUserVO(_request);
			String[] otherInstructionId = _fb.getOtherInstructionExtId();
			// String[] otherInstruction=_fb.getOtherInstruction();
			// String[] activity=_fb.getActivityExt();
			String[] activityId = _fb.getActivityExtId();
			List selOtherInstList = new ArrayList();
			List selActivityList = new ArrayList();
			session.removeAttribute(OpdConfig.PREV_OTHER_INSTRUCTION_LIST);
			session.removeAttribute(OpdConfig.PREV_OTHER_ACTIVITY_LIST);

			List allInstList = (List) session.getAttribute(OpdConfig.ALL_OTHER_INSTRUCTION_LIST_BY_GENDER);
			List allActivity = (List) session.getAttribute(OpdConfig.ALL_ONE_TIME_ACTIVITY_LIST_BY_GENDER);

			if (otherInstructionId != null)
			{
				for (int i = 0; i < otherInstructionId.length; i++)
				{
					PatExtTreatmentDetailVO extVO = new PatExtTreatmentDetailVO();

					extVO.setExtTreatmentId(otherInstructionId[i]);

					for (int j = 0; j < allInstList.size(); j++)
					{
						Entry obj = (Entry) allInstList.get(j);
						if (otherInstructionId[i].equals(obj.getValue()))
						{
							extVO.setExtTreatmentName(obj.getLabel());
						}
					}
					// extVO.setExtTreatmentName(otherInstruction[i]);
					extVO.setTreatmentType(OpdConfig.OTHER_INSTRUCTION);

					otherInstructionList.add(extVO);
					selOtherInstList.add(extVO);
					WebUTIL.setAttributeInSession(_request, OpdConfig.PREV_OTHER_INSTRUCTION_LIST, selOtherInstList);

				}
			}

			if (activityId != null)
			{
				for (int i = 0; i < activityId.length; i++)
				{
					PatExtTreatmentDetailVO extVO = new PatExtTreatmentDetailVO();

					extVO.setExtTreatmentId(activityId[i]);

					for (int j = 0; j < allActivity.size(); j++)
					{
						Entry obj = (Entry) allActivity.get(j);
						if (activityId[i].equals(obj.getValue()))
						{
							extVO.setExtTreatmentName(obj.getLabel());
						}
					}

					// extVO.setExtTreatmentName(activity[i]);
					extVO.setTreatmentType(OpdConfig.ONE_TIME_ACTIVITY);

					selActivityList.add(extVO);
					otherInstructionList.add(extVO);
					WebUTIL.setAttributeInSession(_request, OpdConfig.PREV_OTHER_ACTIVITY_LIST, selActivityList);
				}
			}

			session.setAttribute(OpdConfig.OTHER_INSTRUCTION_LIST_FOR_SAVE, otherInstructionList);
			// session.setAttribute(OpdConfig.PREV_OTHER_INST_AND_ACTIVITY_LIST, otherInstructionList);

			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	// Advice On Discharge:Getting Discharge Profile All Instruction Activiy
	public static void getAllInstructionAndActivity(GenericPatientProfileFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_request);

		try
		{
			// UserVO userVO = getUserVO(_request);

			List allInstList = (List) session.getAttribute(OpdConfig.ALL_OTHER_INSTRUCTION_LIST_BY_GENDER);
			List allActivityList = (List) session.getAttribute(OpdConfig.ALL_ONE_TIME_ACTIVITY_LIST_BY_GENDER);

			WebUTIL.setAttributeInSession(_request, OpdConfig.OTHER_INSTRUCTION_LIST_FOR_BOTH, allInstList);
			WebUTIL.setAttributeInSession(_request, OpdConfig.ONE_TIME_ACTIVITY_LIST_FOR_BOTH, allActivityList);

			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	// Advice On Discharge:Getting Discharge Profile Dept Wise Instruction Activity
	public static void getDeptWiseInstructionAndActivity(GenericPatientProfileFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_request);

		try
		{
			// UserVO userVO = getUserVO(_request);

			List deptUnitWiseInstList = (List) session.getAttribute(OpdConfig.OTHER_INSTRUCTION_LIST_BY_DEPTUNITCODE_AND_GENDER);
			List deptUnitWiseActivityList = (List) session.getAttribute(OpdConfig.ONE_TIME_ACTIVITY_LIST_BY_DEPTUNITCODE_AND_GENDER);

			WebUTIL.setAttributeInSession(_request, OpdConfig.OTHER_INSTRUCTION_LIST_FOR_BOTH, deptUnitWiseInstList);
			WebUTIL.setAttributeInSession(_request, OpdConfig.ONE_TIME_ACTIVITY_LIST_FOR_BOTH, deptUnitWiseActivityList);

			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	// Advice On Discharge:to add drug detail row
	public static void addNewDrugRow(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();

		List<PatDrugTreatmentDetailVO> lstDrugTreatmentDtl = null;
		try
		{
			setSysdate(_rq);

			// for after submitting change drug page revoke checked check box remain checked
			String revokeItemId = "";
			String[] revokeArray = _fb.getRevoke();
			if (revokeArray != null)
			{
				for (int i = 0; i < revokeArray.length; i++)
				{
					String[] itemIdAndIndexArray = revokeArray[i].split("\\$");
					revokeItemId = itemIdAndIndexArray[0] + "#" + revokeItemId;
				}
				if (!revokeItemId.equals("")) revokeItemId = revokeItemId.substring(0, revokeItemId.length() - 1);
				_fb.setRevokeHidden(revokeItemId);
			}

			// for after submitting change drug page extension checked check box remain checked
			String extensionItemId = "";
			String[] extensionArray = _fb.getExtension();

			if (extensionArray != null)
			{
				for (int i = 0; i < extensionArray.length; i++)
				{
					String[] itemIdAndIndexArray = extensionArray[i].split("\\$");
					extensionItemId = itemIdAndIndexArray[0] + "#" + extensionItemId;
				}
				if (!extensionItemId.equals("")) extensionItemId = extensionItemId.substring(0, extensionItemId.length() - 1);
				_fb.setExtensionHidden(extensionItemId);
			}

			// for after submitting change drug page extensionDays values remain same
			String extensiondays = "";
			String[] extensionDaysArray = _fb.getExtensionDays();

			if (extensionDaysArray != null)
			{
				for (int i = 0; i < extensionDaysArray.length; i++)
				{
					// String[] itemIdAndIndexArray=extensionDaysArray[i].split("\\$");
					extensiondays = extensionDaysArray[i] + "#" + extensiondays;
				}
				if (!extensiondays.equals("")) extensiondays = extensiondays.substring(0, extensiondays.length() - 1);
				_fb.setExtensionDaysHidden(extensiondays);
			}

			// Setting Entered and New Row
			lstDrugTreatmentDtl = new ArrayList<PatDrugTreatmentDetailVO>();;
			int rows = Integer.parseInt(_fb.getDrugDetailRows());
			String[] isEmptyStomach = _fb.getEmptyStomachIndexArray().split("#");
			for (int i = 0; i < rows; i++)
			{
				PatDrugTreatmentDetailVO vo = new PatDrugTreatmentDetailVO();
				vo.setDrugId(_fb.getSelDrugId()[i] + "#" + _fb.getSelDrugItemTypeId()[i]);
				vo.setDrugName(_fb.getSelDrugName()[i]);
				vo.setDoseId(_fb.getSelDoseId()[i]);
				vo.setDoseName(_fb.getSelDoseName()[i]);
				vo.setFrequencyId(_fb.getSelFrequencyId()[i]);
				vo.setDays(_fb.getSelDays()[i]);
				vo.setStartDay(_fb.getSelStartDay()[i]);
				vo.setRemarks(_fb.getSelInstructions()[i]);
				vo.setRxContinueFlag(_fb.getRxContinueFlag()[i]);
				vo.setIsEmptyStomach(isEmptyStomach[i]);
				vo.setDrugRouteId(_fb.getSelDrugRouteId()[i]);
				vo.setDrugRouteName(_fb.getSelDrugRouteName()[i]);
				// vo.setQuantity(_fb.getQuantity()[i]);
				lstDrugTreatmentDtl.add(vo);
			}
			lstDrugTreatmentDtl.add(_fb.resetVO(new PatDrugTreatmentDetailVO()));
			_fb.setDrugDetailRows(Integer.toString(rows + 1));

			WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST, lstDrugTreatmentDtl);

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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	// Advice On Discharge:to add external treatment row
	public static void addNewExtTretmentRow(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();

		List<PatExtTreatmentDetailVO> lstExtTreatmentDtl = null;
		try
		{
			setSysdate(_rq);

			// Setting Entered and New Row
			lstExtTreatmentDtl = new ArrayList<PatExtTreatmentDetailVO>();;
			int rows = Integer.parseInt(_fb.getExtDrugDetailRows());
			for (int i = 0; i < rows; i++)
			{
				PatExtTreatmentDetailVO vo = new PatExtTreatmentDetailVO();

				// vo.setDoseId(_fb.getSelExtDoseId()[i]);
				// vo.setDoseName(_fb.getSelExtDoseName()[i]);
				vo.setFrequencyId(_fb.getSelExtFrequencyId()[i]);
				vo.setDays(_fb.getSelExtDays()[i]);
				vo.setStartDay(_fb.getSelExtStartDay()[i]);
				vo.setRemarks(_fb.getSelExtInstructions()[i]);
				vo.setRxContinueFlag(_fb.getRxContinueFlag()[i]);
				vo.setExtTreatmentId(_fb.getSelExtTreatmentId()[i]);
				vo.setExtTreatmentName(_fb.getSelExtTreatmentName()[i]);
				lstExtTreatmentDtl.add(vo);
			}
			lstExtTreatmentDtl.add(_fb.resetExtTreatmentVO(new PatExtTreatmentDetailVO()));
			_fb.setExtDrugDetailRows(Integer.toString(rows + 1));
			_fb.setActiveTab("tabLAAdvice");

			WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_TREATMENT_DTL_EXT_DETAIL_LIST, lstExtTreatmentDtl);

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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	// Advice On Discharge:Setting Patient Discharge Profile Footer Detail To Profile
	public static boolean setPatientDischargeProfileFooterDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		DisclaimerMstVO disclaimerMstVO = new DisclaimerMstVO();
		boolean flag = true;
		try
		{
			session = _rq.getSession();
			String profileType = _fb.getProfileType().split("#")[0];
			disclaimerMstVO = (DisclaimerMstVO) session.getAttribute(OpdConfig.PAT_PROFILE_DTL_DISCLAIMER_DTL_VO);
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			proforma.setProfileFooterDetailProforma(_fb.getSelectedMenuId(), _fb.getRemarks(), _fb.getReviewDate(), _fb.getDischargeType(), _fb.getDischargeTypeName(),
					_fb.getDischargeAdvisedBy(), _fb.getDischargeAdvisedByName(), _fb.getDischargeAdvisedDept(), _fb.getDischargeAdvisedDeptName(), profileType, disclaimerMstVO,_fb.getSnomdCIdRemarks(),_fb.getSnomdPTRemarks());
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Advice On Discharge:Getting Default Drug Schedule
	public static void getDefaultSchedule(GenericPatientProfileFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_request);
		try
		{
			// UserVO userVO = getUserVO(_request);
			List drugSheduleLst = new ArrayList();
			String[] popUpDoseId = _fb.getPopupDoseId().split("\\^");
			_fb.setPopupDoseId(popUpDoseId[0]);
			Map drugSchedule = (Map) session.getAttribute(OpdConfig.DRUG_SHEDULE_MAP);
			if (drugSchedule != null)
			{
				// drugSheduleLst=(List)drugSchedule.get(_fb.getPopupDrugId());
				drugSheduleLst = (List) drugSchedule.get(_fb.getPopupDrugId() + "#" + _fb.getPopupFreqId() + "#" + _fb.getPopupDoseId());
				if (drugSheduleLst != null)
				{
					for (int i = 0; i < drugSheduleLst.size(); i++)
					{
						DrugSheduleDtlVO drugSheduleDtlVO = (DrugSheduleDtlVO) drugSheduleLst.get(i);
						if ((drugSheduleDtlVO.getPopupDrugId()!=null && !(drugSheduleDtlVO.getPopupDrugId().equals(_fb.getPopupDrugId())))
								|| (drugSheduleDtlVO.getPopupFreqId()!=null && !(drugSheduleDtlVO.getPopupFreqId().equals(_fb.getPopupFreqId()))))
						{
							// drugSchedule.remove(_fb.getPopupRowIndex());
							drugSchedule.remove(_fb.getPopupDrugId() + "#" + _fb.getPopupFreqId() + "#" + _fb.getPopupDoseId());
						}
					}
				}
			}
			session.setAttribute(OpdConfig.DRUG_SHEDULE_MAP, drugSchedule);
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
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	// Advice On Discharge:Setting Patient Drug Schedule to Profile
	public static void saveDrugSchedule(GenericPatientProfileFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_request);
		try
		{
			// UserVO userVO = getUserVO(_request);
			Map drugSheduleMap = new HashMap();
			List drugSheduleLst = new ArrayList();

			drugSheduleMap = (Map) session.getAttribute(OpdConfig.DRUG_SHEDULE_MAP);
			if (drugSheduleMap == null)
			{
				drugSheduleMap = new HashMap();
			}

			// String[] isEmptyStomachArray= _fb.getPopupCheckIndex().split("#");//this is for getting IsEmptyStomach Check
			// Box value from popup
			// because only selected check box array return from form not whole
			int freqCount = Integer.parseInt(_fb.getPopupFreqCount());
			String doseName = "";
			for (int i = 0; i < freqCount; i++)
			{
				String scheduleSlNo = new Integer(i).toString();

				DrugSheduleDtlVO drugSheduleDtlVO = new DrugSheduleDtlVO();
				if (_fb.getPopupDoseIdArray() != null)
				{
					List lst = (List) session.getAttribute(OpdConfig.LIST_OF_DRUGDOSE_FOR_POPUP);
					Iterator itr = lst.iterator();
					while (itr.hasNext())
					{
						Entry obj = (Entry) itr.next();
						if (obj.getValue().equals(_fb.getPopupDoseIdArray()[i]))
						{
							doseName = obj.getLabel();
							break;
						}
					}

					drugSheduleDtlVO.setDoseName(doseName);
					drugSheduleDtlVO.setDoseId(_fb.getPopupDoseIdArray()[i]);
				}
				else
				{
					drugSheduleDtlVO.setDoseId("0");
					drugSheduleDtlVO.setDoseName(_fb.getPopupDoseNameArray()[i]);
				}

				drugSheduleDtlVO.setDoseShift(_fb.getDrugFreqShift()[i]);
				drugSheduleDtlVO.setDoseTime(_fb.getDrugRequirmentTimeHrs()[i] + ":" + _fb.getDrugRequirmentTimeMin()[i]);
				// drugSheduleDtlVO.setIsEmptyStomach(_fb.getPopupIsEmptyStomachArray()[i]);
				drugSheduleDtlVO.setIsEmptyStomach(_fb.getPopupIsEmptyStomach());
				drugSheduleDtlVO.setSheduleSerialNo(scheduleSlNo);
				drugSheduleDtlVO.setItemTypeId(_fb.getPopupItemTypeId());
				drugSheduleDtlVO.setPopupDrugId(_fb.getPopupDrugId());
				drugSheduleDtlVO.setPopupFreqId(_fb.getPopupFreqId());
				drugSheduleDtlVO.setFrequency(_fb.getPopupFreqCount());
				drugSheduleDtlVO.setPopupRowIndex(_fb.getPopupRowIndex());
				// drugSheduleDtlVO.setPopupRowIndex(_fb.getRemarks()[i]);
				drugSheduleLst.add(drugSheduleDtlVO);
			}
			// drugSheduleMap.put(_fb.getPopupDrugId(), drugSheduleLst);
			drugSheduleMap.put(_fb.getPopupDrugId() + "#" + _fb.getPopupFreqId() + "#" + _fb.getPopupDoseId(), drugSheduleLst);
			session.setAttribute(OpdConfig.DRUG_SHEDULE_MAP, drugSheduleMap);

			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	// Advice On Discharge:Setting Patient Drug Schedule Map to Profile
	public static void setDrugScheduleMap(List<ProfileDrugScheduleDtlVO> drugScheduleDtlVOList, ProfileDrugAdviceDtlVO[] _profileDrugAdviceDtlVO,
			HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Map drugSheduleMap = new HashMap();
		List drugSheduleLst = new ArrayList();
		DrugSheduleDtlVO drugSheduleDtlVO = null;

		for (int i = 0; i < _profileDrugAdviceDtlVO.length; i++)
		{
			// String scheduleSlNo=new Integer(i).toString();

			drugSheduleLst = new ArrayList();
			for (int j = 0; j < drugScheduleDtlVOList.size(); j++)
			{
				ProfileDrugScheduleDtlVO profileDrugScheduleVO = drugScheduleDtlVOList.get(j);
				drugSheduleDtlVO = new DrugSheduleDtlVO();
				if (profileDrugScheduleVO.getItemId().equals(_profileDrugAdviceDtlVO[i].getDrugId()))
				{
					drugSheduleDtlVO.setDoseShift(profileDrugScheduleVO.getDoseShift());
					drugSheduleDtlVO.setDoseTime(profileDrugScheduleVO.getDoseTime());
					// drugSheduleDtlVO.setIsEmptyStomach(_fb.getPopupIsEmptyStomachArray()[i]);
					drugSheduleDtlVO.setIsEmptyStomach(_profileDrugAdviceDtlVO[i].getIsEmptyStomach());
					// drugSheduleDtlVO.setSheduleSerialNo(scheduleSlNo);
					// drugSheduleDtlVO.setItemTypeId(_fb.getPopupItemTypeId());
					drugSheduleDtlVO.setDoseId(profileDrugScheduleVO.getDoseId());
					drugSheduleDtlVO.setPopupDrugId(profileDrugScheduleVO.getItemId());
					drugSheduleDtlVO.setPopupFreqId(_profileDrugAdviceDtlVO[i].getFrequencyId());
					// drugSheduleDtlVO.setFrequency(_fb.getPopupFreqCount());
					drugSheduleDtlVO.setFrequency(_profileDrugAdviceDtlVO[i].getFrequency());
					drugSheduleDtlVO.setPopupRowIndex(String.valueOf(i));
					// drugSheduleDtlVO.setPopupRowIndex(_fb.getRemarks()[i]);
					drugSheduleLst.add(drugSheduleDtlVO);
				}
			}
			drugSheduleMap.put(_profileDrugAdviceDtlVO[i].getDrugId() + "#" + _profileDrugAdviceDtlVO[i].getFrequencyId() + "#"
					+ _profileDrugAdviceDtlVO[i].getDoseId(), drugSheduleLst);
		}

		session.setAttribute(OpdConfig.DRUG_SHEDULE_MAP, drugSheduleMap);
	}

	// Getting Patient Diagnosis Detail
	public static boolean getPatientDiagnosisDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		boolean flag = true;
		List diagnosisTypeList = new ArrayList();
		String url = "";
		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);

			_fb.setSelectedRow(new String[]
			{ "" });
			System.out.println("Episode code :"+ _fb.getEpisodeCode()+" AddmissionNo :"+_fb.getAdmissionNo()+" DeskType ;"+_fb.getDeskType()+" Episode Visit No"+_fb.getEpisodeVisitNo()+" Profile Genration mode :"+_fb.getProfileGenerationMode());
			PatientDetailVO voDp = new PatientDetailVO();
			HelperMethods.populate(voDp, _fb);
			voDp.setPatAdmNo(_fb.getAdmissionNo());
			EpisodeDiagnosisVO[] episodeDiagnosisVOs = GenericPatientProfileDATA.getEpisodeDiagnosis(_fb.getPatCrNo(), voDp, _fb.getDeskType(), userVO, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED); //passing genration mode is 1
			/*EpisodeDiagnosisVO[] episodeDiagnosisVOs = GenericPatientProfileDATA.getEpisodeDiagnosis(_fb.getPatCrNo(), _fb.getEpisodeCode(), _fb
					.getAdmissionNo(), _fb.getDeskType(), userVO, _fb.getEpisodeVisitNo(), OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED); //passing genration mode is 1
*/			
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_EPISODEDIAGNOSISVO_ARRAY, episodeDiagnosisVOs);

			// //DIAGNOSIS PROFORMA
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);

			List lstProMenus = (List) session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST);
			if (proforma != null)
			{
				// Map<String, StringBuilder> map = proforma.getAddedOptionsMap();
				List lstMenus = proforma.getOptionsOrderList();

				for (int i = 0; i < lstMenus.size(); i++)
				{
					String key = (String) lstMenus.get(i);
					DeskMenuMasterVO vo = null;
					Iterator lstProMenusItr = lstProMenus.iterator();
					while (lstProMenusItr.hasNext())
					{
						DeskMenuMasterVO v = (DeskMenuMasterVO) lstProMenusItr.next();
						if (v.getDeskMenuId().equals(key))
						{
							vo = v;
							break;
						}
					}
					url = vo.getDeskUrl();

					if (url.equals("DESKDIAGNOSIS"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpDiag = mapProfileOptions.get(key);
						diagnosisTypeList = (List) mpDiag.get("lstEpisodeDiagnosisVO");
					}
				}

				String strDiagnosisCheckFlag = "";
				if (episodeDiagnosisVOs != null)
				{
					for (int i = 0; i < episodeDiagnosisVOs.length; i++)
					{
						boolean diagTypeFlag = false;
						Iterator diagIterator = diagnosisTypeList.iterator();
						while (diagIterator.hasNext())
						{
							EpisodeDiagnosisVO vo = (EpisodeDiagnosisVO) diagIterator.next();

							if (vo.getDiagnosticCode().equals(episodeDiagnosisVOs[i].getDiagnosticCode()))
							{
								diagTypeFlag = true;
							}
						}

						if (diagTypeFlag == true)
						{
							strDiagnosisCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_YES + "#";
						}
						else
						{
							strDiagnosisCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_NO + "#";
						}
					}

					if (strDiagnosisCheckFlag.length() > 0)

					strDiagnosisCheckFlag = strDiagnosisCheckFlag.substring(0, strDiagnosisCheckFlag.length() - 1);

					_fb.setDiagnosisCheckFlag(strDiagnosisCheckFlag);
				}

			}

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Setting Patient Diagnosis Detail To Profile
	public static boolean setPatientDiagnosisDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		boolean flag = true;
		try
		{
			session = _rq.getSession();
			EpisodeDiagnosisVO[] episodeDiagnosisVOs = (EpisodeDiagnosisVO[]) session
					.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_EPISODEDIAGNOSISVO_ARRAY);
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			proforma.setDiagnosisProforma(_fb.getSelectedMenuId(), episodeDiagnosisVOs, _fb.getSelectedRow());
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Getting Patient Allergies Detail
	public static boolean getPatientAllergiesDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		List allergyTypeList = new ArrayList();
		boolean flag = true;
		String url = "";
		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);

			_fb.setSelectedRow(new String[]
			{ "" });
			System.out.println("Episode code :"+ _fb.getEpisodeCode()+" AddmissionNo :"+_fb.getAdmissionNo()+" DeskType ;"+_fb.getDeskType()+" Episode Visit No"+_fb.getEpisodeVisitNo()+" Profile Genration mode :"+_fb.getProfileGenerationMode());
			PatientDetailVO voDp = new PatientDetailVO();
			HelperMethods.populate(voDp, _fb);
			PatAllergyDtlVO[] patAllergyDtlVOs = GenericPatientProfileDATA.getPatientAllergiesDetail(_fb.getPatCrNo(), userVO, voDp, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_EPISODEALLERGIESVO_ARRAY, patAllergyDtlVOs);

			// //Allergy PROFORMA to get the check field at the time of modify
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);

			List lstProMenus = (List) session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST);
			if (proforma != null)
			{
				// Map<String, StringBuilder> map = proforma.getAddedOptionsMap();
				List lstMenus = proforma.getOptionsOrderList();

				for (int i = 0; i < lstMenus.size(); i++)
				{
					String key = (String) lstMenus.get(i);
					DeskMenuMasterVO vo = null;
					Iterator lstProMenusItr = lstProMenus.iterator();
					while (lstProMenusItr.hasNext())
					{
						DeskMenuMasterVO v = (DeskMenuMasterVO) lstProMenusItr.next();
						if (v.getDeskMenuId().equals(key))
						{
							vo = v;
							break;
						}
					}
					url = vo.getDeskUrl();

					if (url.equals("DESKALLERGIES"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpAllerg = mapProfileOptions.get(key);
						allergyTypeList = (List) mpAllerg.get("lstEpisodeAllergiesVO");
					}
				}

				String strAllergyCheckFlag = "";
				if (patAllergyDtlVOs != null)
				{
					for (int i = 0; i < patAllergyDtlVOs.length; i++)
					{
						boolean allergyTypeFlag = false;
						Iterator allergyIterator = allergyTypeList.iterator();
						while (allergyIterator.hasNext())
						{
							PatAllergyDtlVO vo = (PatAllergyDtlVO) allergyIterator.next();

							if (vo.getAllergyTypeCode().equals(patAllergyDtlVOs[i].getAllergyTypeCode())
									&& (vo.getAllergyName().equals(patAllergyDtlVOs[i].getAllergyName())))
							{
								allergyTypeFlag = true;

							}
						}

						if (allergyTypeFlag == true)
						{
							strAllergyCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_YES + "#";
						}
						else
						{
							strAllergyCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_NO + "#";
						}
					}

					if (strAllergyCheckFlag.length() > 0) strAllergyCheckFlag = strAllergyCheckFlag.substring(0, strAllergyCheckFlag.length() - 1);

					_fb.setAllergyCheckFlag(strAllergyCheckFlag);
				}
			}
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Setting Patient Allergies Detail To Profile
	public static boolean setPatientAllergiesDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		System.out.println("Selected Row "+_fb.getSelectedRow().length);
		Status objStatus = new Status();
		HttpSession session = null;
		boolean flag = true;
		try
		{
			session = _rq.getSession();
			PatAllergyDtlVO[] patAllergyDtlVOs = (PatAllergyDtlVO[]) session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_EPISODEALLERGIESVO_ARRAY);
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			proforma.setAllergiesProforma(_fb.getSelectedMenuId(), patAllergyDtlVOs, _fb.getSelectedRow());
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Getting Patient Operation Detail
	public static boolean getPatientOperationDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		List oTTypeList = new ArrayList();
		boolean flag = true;
		String url = "";
		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);

			// _fb.setSelectedRow(new String[]{ "" });
			PatientDetailVO voDp = new PatientDetailVO();
			HelperMethods.populate(voDp, _fb);
			voDp.setPatAdmNo(_fb.getAdmissionNo());
			ProfileOTDetailVO[] profileOTDetailVOs = GenericPatientProfileDATA.getPatientOperationDetail(_fb.getPatCrNo(), userVO,voDp,OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_OPERATION_DETAIL_VO_ARRAY, profileOTDetailVOs);

			// //Operation PROFORMA
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);

			List lstProMenus = (List) session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST);
			if (proforma != null)
			{
				// Map<String, StringBuilder> map = proforma.getAddedOptionsMap();
				List lstMenus = proforma.getOptionsOrderList();

				for (int i = 0; i < lstMenus.size(); i++)
				{
					String key = (String) lstMenus.get(i);
					DeskMenuMasterVO vo = null;
					Iterator lstProMenusItr = lstProMenus.iterator();
					while (lstProMenusItr.hasNext())
					{
						DeskMenuMasterVO v = (DeskMenuMasterVO) lstProMenusItr.next();
						if (v.getDeskMenuId().equals(key))
						{
							vo = v;
							break;
						}
					}
					url = vo.getDeskUrl();

					if (url.equals("OTVIEWING"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpOT = mapProfileOptions.get(key);
						oTTypeList = (List) mpOT.get("lstOperationDetailVO");
					}
				}

				String strOTCheckFlag = "";
				if (profileOTDetailVOs != null)
				{
					for (int i = 0; i < profileOTDetailVOs.length; i++)
					{
						boolean oTTypeFlag = false;
						Iterator oTIterator = oTTypeList.iterator();
						while (oTIterator.hasNext())
						{
							ProfileOTDetailVO vo = (ProfileOTDetailVO) oTIterator.next();

							if (vo.getOtReqNo().equals(profileOTDetailVOs[i].getOtReqNo()))
							{
								oTTypeFlag = true;
							}
						}

						if (oTTypeFlag == true)
						{
							strOTCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_YES + "#";
						}
						else
						{
							strOTCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_NO + "#";
						}
					}

					if (strOTCheckFlag.length() > 0)

					strOTCheckFlag = strOTCheckFlag.substring(0, strOTCheckFlag.length() - 1);

					_fb.setOperationCheckFlag(strOTCheckFlag);
				}

			}
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Setting Patient Operation Detail To Profile
	public static boolean setPatientOperationDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		List profileOTList = new ArrayList();
		Map templateDetailsMap = null;
		List templateList = null;
		List paraIdValueList = null;
		List templateListAll = null;
		List paraIdValueListAll = null;
		List mapAllList = new ArrayList();
		boolean flag = true;
		try
		{
			session = _rq.getSession();
			UserVO userVO = getUserVO(_rq);
			// String[] deptCode=null;
			ProfileOTDetailVO[] profileOTDetailVOs = (ProfileOTDetailVO[]) session
					.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_OPERATION_DETAIL_VO_ARRAY);

			for (int i = 0; i < _fb.getSelectedRow().length; i++)
			{
				ProfileOTDetailVO pDetailVO = (ProfileOTDetailVO) profileOTDetailVOs[Integer.valueOf(_fb.getSelectedRow()[i])];
				profileOTList.add(pDetailVO);
			}
			templateDetailsMap = GenericPatientProfileDATA.getPatientProfileOperationTemplateDetails(profileOTList, _fb.getPatCrNo(), userVO);
			templateListAll = (List) templateDetailsMap.get(OpdConfig.OPERATION_TEMPLATE_LIST_ALL);
			paraIdValueListAll = (List) templateDetailsMap.get(OpdConfig.OPERATION_TEMPLATE_PARA_ID_VALUE_ALL);

			// (0) template Id
			// (1) parameter ID
			// (2) parameter value
			// (3) status code
			Iterator templateListAllItr = templateListAll.iterator();
			Iterator paraIdValueListAllItr = paraIdValueListAll.iterator();
			while ((templateListAllItr.hasNext()) && (paraIdValueListAllItr.hasNext()))
			{
				templateList = (List) templateListAllItr.next();
				paraIdValueList = (List) paraIdValueListAllItr.next();
				Iterator templateListIterator = templateList.iterator();
				Map mapAll = new HashMap();
				while (templateListIterator.hasNext())
				{
					String templateId = (String) templateListIterator.next();
					Map map = new HashMap();
					Iterator paraIdValueListIterator = paraIdValueList.iterator();
					while (paraIdValueListIterator.hasNext())
					{
						List list = (List) paraIdValueListIterator.next();
						if (templateId.equals((String) list.get(0)))
						{
							map.put(list.get(1), list.get(2));
						}
					}
					mapAll.put(templateId, map);

				}
				mapAllList.add(mapAll);
			}

			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPERATION_TEMPLATE_MAP_ALL_LIST, mapAllList);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPERATION_TEMPLATE_LIST_ALL, templateListAll);

			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			proforma.setOperationDetailProforma(_fb.getSelectedMenuId(), profileOTDetailVOs, _fb.getSelectedRow());
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Getting Patient Treatment Detail
	public static boolean getPatientTreatmentDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		List treatmentTypeList = new ArrayList();
		boolean flag = true;
		String url = "";
		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);

			_fb.setSelectedRow(new String[]
			{ "" });
			PatientDetailVO voDp = new PatientDetailVO();
			HelperMethods.populate(voDp, _fb);
			voDp.setPatAdmNo(_fb.getAdmissionNo());
			PatDrugTreatmentDetailVO[] patDrugTreatmentDtlVO = GenericPatientProfileDATA.getPatientTreatmentDetail(_fb.getPatCrNo(), voDp, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED, _fb.getDeskType(), userVO);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_EPISODETREATMENTVO_ARRAY, patDrugTreatmentDtlVO);

			// //Allergy PROFORMA to get the check field at the time of modify
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);

			List lstProMenus = (List) session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST);
			if (proforma != null)
			{
				// Map<String, StringBuilder> map = proforma.getAddedOptionsMap();
				List lstMenus = proforma.getOptionsOrderList();

				for (int i = 0; i < lstMenus.size(); i++)
				{
					String key = (String) lstMenus.get(i);
					DeskMenuMasterVO vo = null;
					Iterator lstProMenusItr = lstProMenus.iterator();
					while (lstProMenusItr.hasNext())
					{
						DeskMenuMasterVO v = (DeskMenuMasterVO) lstProMenusItr.next();
						if (v.getDeskMenuId().equals(key))
						{
							vo = v;
							break;
						}
					}
					url = vo.getDeskUrl();

					if (url.equals("DESKTREATMENTDETAIL"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpTreat = mapProfileOptions.get(key);
						treatmentTypeList = (List) mpTreat.get("lstEpisodeTreatmentVO");
					}
				}

				String strTreatmentCheckFlag = "";
				if (patDrugTreatmentDtlVO != null)
				{
					for (int i = 0; i < patDrugTreatmentDtlVO.length; i++)
					{
						boolean treatmentTypeFlag = false;
						Iterator treatmentIterator = treatmentTypeList.iterator();
						while (treatmentIterator.hasNext())
						{
							PatDrugTreatmentDetailVO vo = (PatDrugTreatmentDetailVO) treatmentIterator.next();

							if (vo.getDrugId().equals(patDrugTreatmentDtlVO[i].getDrugId()))
							{
								treatmentTypeFlag = true;

							}
						}

						if (treatmentTypeFlag == true)
						{
							strTreatmentCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_YES + "#";
						}
						else
						{
							strTreatmentCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_NO + "#";
						}
					}

					if (strTreatmentCheckFlag.length() > 0) strTreatmentCheckFlag = strTreatmentCheckFlag.substring(0,
							strTreatmentCheckFlag.length() - 1);

					_fb.setTreatmentCheckFlag(strTreatmentCheckFlag);
				}
			}
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Setting Patient Treatment Detail To Profile
	public static boolean setPatientTreatmentDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		System.out.println("Selected Row "+_fb.getSelectedRow().length);
		Status objStatus = new Status();
		HttpSession session = null;
		boolean flag = true;
		try
		{
			session = _rq.getSession();
			PatDrugTreatmentDetailVO[] patDrugTreatmentDtlVOs = (PatDrugTreatmentDetailVO[]) session
					.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_EPISODETREATMENTVO_ARRAY);
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			proforma.setTreatmentProforma(_fb.getSelectedMenuId(), patDrugTreatmentDtlVOs, _fb.getSelectedRow());
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Getting Patient Tempate Detail
	public static boolean getPatientComplaintsDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		boolean flag = true;
		List<Entry> lstTemps = null;
		List<Entry> lstReportDates = new ArrayList<Entry>();
		Map<String, TemplateMasterVO> mapTemplateDtl = null;
		List<TemplateParameterMasterVO> lstAllTempParas = null;
		LinkedHashMap<String, LinkedHashMap<String, String>> mapAllTempParas = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		LinkedHashMap<String, LinkedHashMap<String, TemplateParameterMasterVO>> mapAllValuedTempParas = new LinkedHashMap<String, LinkedHashMap<String, TemplateParameterMasterVO>>();
		String strComplaintsCheckFlag = "";
		List<Entry> lstVisitDates = new ArrayList<Entry>();
		try
		{
			UserVO userVO = getUserVO(_rq);

			_fb.setSelectedRow(new String[]{ "" });
			_fb.setUserSeatId(userVO.getUserSeatId());

			// Setting Template List
			UserDeskMenuTemplateMasterVO voUDMTMst = new UserDeskMenuTemplateMasterVO();
			HelperMethods.populate(voUDMTMst, _fb);
			voUDMTMst.setDeptUnitCode(_fb.getDepartmentUnitCode());
			voUDMTMst.setDeskMenuId(_fb.getSelectedMenuId());
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_USER_DESK_MENU_TEMPLATE_MASTER_VO, voUDMTMst);

			lstTemps = GenericTemplateTileDATA.getDeskMenuTemplateList(_fb.getDeskType(),_fb.getPatCrNo(), voUDMTMst, userVO);
			for (Entry e : lstTemps)	e.setValue(e.getValue().split("#")[0]);
			WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.TEMPLATE_TILE_ALL_TEMPLATE_LIST+_fb.getSelectedMenuId(), lstTemps);//---- Where to reuse

			if (lstTemps.size()==0)
			{
				objStatus.add(Status.NEW, "", "No Template Exist");
				flag = false;
			}
			else
			{
				// To get Template wise essentials
				mapTemplateDtl = GenericTemplateTileDATA.getAllTemplateDetails(lstTemps, userVO);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_DTL_MAP, mapTemplateDtl);//---- Where to reuse

				lstAllTempParas = GenericTemplateTileDATA.getAllTemplateParametersDetail(lstTemps, userVO);

				if (lstAllTempParas.size()==0)
				{
					objStatus.add(Status.NEW, "", "No Template Exist");
					flag = false;
				}
				else
				{
					// Setting Template Parameters
					for (TemplateParameterMasterVO vo : lstAllTempParas)
					{
						if (vo.getParaId() != null)
						{
							LinkedHashMap<String, String> mapTemp = mapAllTempParas.get(vo.getTemplateId());
							if (mapTemp == null) mapTemp = new LinkedHashMap<String, String>();
							mapTemp.put(vo.getParaId(), vo.getParaName());
							mapAllTempParas.put(vo.getTemplateId(), mapTemp);

							if (!vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_LABEL) 
									&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_COMMENT)
									&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_INFORMATION)
									&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_IMAGEVIEW))
							{

								LinkedHashMap<String, TemplateParameterMasterVO> map = mapAllValuedTempParas.get(vo.getTemplateId());
								if (map == null) map = new LinkedHashMap<String, TemplateParameterMasterVO>();
								map.put(vo.getParaId(), vo);
								mapAllValuedTempParas.put(vo.getTemplateId(), map);
							}
						}
					}
					WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_PARA_MAP, mapAllTempParas);//---- Where to reuse
					WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_VALUED_TEMP_PARA_MAP, mapAllValuedTempParas);//---- Where to reuse
					WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_LIST_ALL_TEMP_PARAS, lstAllTempParas);//---- Where to reuse

					
					
					// Setting Template Dates
					PatientClinicalDetailVO patCliniDtlVO = new PatientClinicalDetailVO();
					HelperMethods.populate(patCliniDtlVO, _fb);
					patCliniDtlVO.setDeskMenuId(_fb.getSelectedMenuId());

					lstReportDates = GenericTemplateTileDATA.getReportDateList(_fb.getDeskType(), patCliniDtlVO, userVO);
					WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_VISIT_DATES, lstReportDates);//---- Where to reuse

					if (lstReportDates == null) lstReportDates = new ArrayList<Entry>();
					// Setting Record Dates
					LinkedHashMap<String, String> mapRecordDates = new LinkedHashMap<String, String>();
					for (Entry e : lstReportDates)
						mapRecordDates.put(e.getLabel(), e.getLabel());

					// Map of RecordDate/Map of Temp Id/Map of Parameter Id/Para Value
					Map<String, Map<String, Map<String, String>>> mpChartClinicalData = null;
					mpChartClinicalData = GenericTemplateTileDATA.getPatientChartClinicalDataTempWise(_fb.getDeskType(), patCliniDtlVO, lstReportDates,
							lstTemps, userVO);

					List<String> lstRemove = new ArrayList<String>();
					for (String recordDate : mapRecordDates.keySet())
					{
						Map<String, Map<String, String>> mpTemp = mpChartClinicalData.get(recordDate);
						if (mpTemp == null)
						{
							lstRemove.add(recordDate);
							continue;
						}
						// Removing Not Useful Templates Data
						List<String> lstRemoveTemplates = new ArrayList<String>();
						for (String key : mpTemp.keySet())
						{
							boolean flg = false;
							for (Entry entTemp : lstTemps)
								if (entTemp.getValue().split("#")[0].equals(key))
								{
									flg = true;
									break;
								}
							if (!flg) lstRemoveTemplates.add(key);
							else if (mpTemp.get(key).size() == 0) lstRemoveTemplates.add(key);
						}
						for (String tempId : lstRemoveTemplates)
							mpTemp.remove(tempId);

						// Checking For Record Date Data
						if (mpTemp.keySet().size() == 0)
						{
							mpChartClinicalData.remove(recordDate);
							lstRemove.add(recordDate);
						}
					}
					List<Entry> lstRemoveEntry = new ArrayList<Entry>();
					for (String str : lstRemove)
					{
						mapRecordDates.remove(str);
						for (Entry entObj : lstReportDates)
							if (entObj.getValue().equals(str))
							{
								lstRemoveEntry.add(entObj);
								break;
							}
					}
				//	for (Entry entObj : lstRemoveEntry)
					//	lstReportDates.remove(entObj);

					// Setting Desk Menu Wise
					//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpChartClinicalData);
					Map mpDeskMenuChartClinicalData = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);
					if(mpDeskMenuChartClinicalData==null) mpDeskMenuChartClinicalData = new HashMap();
					mpDeskMenuChartClinicalData.put(_fb.getSelectedMenuId(), mpChartClinicalData);
					WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpDeskMenuChartClinicalData);

					WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_TEMPLATE_DESK_ID, _fb.getSelectedMenuId());

					if (lstReportDates == null || lstReportDates.size() == 0)
					{
						objStatus.add(Status.NEW, "", "No Template Data Found");
						flag = false;
					}
				
				}
			}

			Map mpDeskMenuReportModes = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP);
			//changed by manisha date: 24.8.2017
			//if(mpDeskMenuReportModes!=null && mpDeskMenuReportModes.get(_fb.getSelectedMenuId())!=null && session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES)!=null)
			if(mpDeskMenuReportModes!=null)
			{
			//_fb.setReportMode((String)mpDeskMenuReportModes.get(_fb.getSelectedMenuId()));
			mpDeskMenuReportModes.put(_fb.getSelectedMenuId(), _fb.getReportMode());
			WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP, mpDeskMenuReportModes);
			}
		
			else
			{
			_fb.setReportMode(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_DATE_WISE);
			mpDeskMenuReportModes =  new HashMap();
			mpDeskMenuReportModes.put(_fb.getSelectedMenuId(), _fb.getReportMode());
			WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP, mpDeskMenuReportModes);
			} //end

			// To get the selected check during modify
			if (_fb.getReportMode() != null && !_fb.getReportMode().equals(""))
			{
				if (_fb.getReportMode().equals(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_PARAMETER_WISE))
				{
					// Getting Desk Menu Wise
					//Map mpTempParas = (HashMap) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
					Map mpDeskMenuTempParas = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
					Map mpTempParas = (Map) mpDeskMenuTempParas.get(_fb.getSelectedMenuId());
					
					Map mapAllTempPara = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_PARA_MAP);

					if (mapAllTempPara != null)
					{
						Iterator mapAllTempParaItr = mapAllTempPara.keySet().iterator();

						while (mapAllTempParaItr.hasNext())
						{
							String tempId = (String) mapAllTempParaItr.next();
							Map mpAllParaId = (Map) mapAllTempPara.get(tempId);

							Iterator mapAllParaKeyItr = mpAllParaId.keySet().iterator();

							while (mapAllParaKeyItr.hasNext())
							{
								String paraId = (String) mapAllParaKeyItr.next();
								// String paraName = (String)mpAllParaId.get(paraId);

								if (mpTempParas != null)
								{
									Iterator mpTempParasItr = mpTempParas.keySet().iterator();

									boolean complaintTypeFlag = false;
									while (mpTempParasItr.hasNext())
									{
										String tempId1 = (String) mpTempParasItr.next();

										List paraIdList = (List) mpTempParas.get(tempId1);

										if (paraIdList.size() != 0)
										{
											int _paraIdList = paraIdList.size();
											for (int i = 0; i < _paraIdList; i++)
											{
												Entry entry = (Entry) paraIdList.get(i);

												if (paraId.equals(entry.getValue()))
												{
													complaintTypeFlag = true;
												}
											}
										}
									}
									if (complaintTypeFlag == true)
									{
										strComplaintsCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_YES + "#";
									}
									else
									{
										strComplaintsCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_NO + "#";
									}
								}
							}
						}
					}

					if (strComplaintsCheckFlag.length() > 0) strComplaintsCheckFlag = strComplaintsCheckFlag.substring(0, strComplaintsCheckFlag
							.length() - 1);
					_fb.setComplaintsCheckFlag(strComplaintsCheckFlag);
				}
				if (_fb.getReportMode().equals(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_DATE_WISE) && session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES)!=null)
				{
					// Getting Desk Menu Wise
					//lstVisitDates = (List<Entry>) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES);
					Map mapDeskMenuVisitDates = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES);
					lstVisitDates = (List<Entry>) mapDeskMenuVisitDates.get(_fb.getSelectedMenuId());
					
					lstReportDates = (List<Entry>) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_VISIT_DATES);

					int reportDate = lstReportDates.size();
					for (int i = 0; i < reportDate; i++)
					{
						Entry entry = (Entry) lstReportDates.get(i);

						if (lstVisitDates != null)
						{
							int visitDate = lstVisitDates.size();
							boolean complaintFlag = false;
							for (int j = 0; j < visitDate; j++)
							{
								Entry entry1 = (Entry) lstVisitDates.get(j);

								if (entry.equals(entry1))
								{
									complaintFlag = true;
								}
							}
							if (complaintFlag == true)
							{
								strComplaintsCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_YES + "#";
							}
							else
							{
								strComplaintsCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_NO + "#";
							}
						}
					}

					if (strComplaintsCheckFlag.length() > 0) strComplaintsCheckFlag = strComplaintsCheckFlag.substring(0, strComplaintsCheckFlag
							.length() - 1);

					_fb.setComplaintsDateWiseCheckFlag(strComplaintsCheckFlag);
				}
			}
			else
			{
				_fb.setReportMode(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_DATE_WISE);
				getDateWiseComplaintEssentials(_fb, _rq);
				
				//changed by manisha date: 24.8.2017
				if(mpDeskMenuReportModes==null)	
				{	
				mpDeskMenuReportModes =  new HashMap();
				mpDeskMenuReportModes.put(_fb.getSelectedMenuId(), _fb.getReportMode());
				}
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP, mpDeskMenuReportModes);
				
			}

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Setting Template Report Essentials
	public static void setTempReportEssentials(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		try
		{
			if (_fb.getReportMode().equals(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_DATE_WISE)) getDateWiseComplaintEssentials(_fb, _rq);
			else if (_fb.getReportMode().equals(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_PARAMETER_WISE)) getParameterWiseComplaintEssentials(
					_fb, _rq);
			
			Map mpDeskMenuReportModes = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP);
			if(mpDeskMenuReportModes==null)	mpDeskMenuReportModes =  new HashMap();
			mpDeskMenuReportModes.put(_fb.getSelectedMenuId(), _fb.getReportMode());
			WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP, mpDeskMenuReportModes);

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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	// Getting Dates List for Template Data
	public static void getDateWiseComplaintEssentials(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		List<Entry> lstReportDates = new ArrayList<Entry>();
		try
		{
			lstReportDates = (List<Entry>) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_VISIT_DATES);
			if (lstReportDates == null || lstReportDates.size() == 0)
			{
				objStatus.add(Status.TRANSINPROCESS, "No Record Date Found", "");
			}
			else
			{
				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_VIEW_MODE, 
						GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
				objStatus.add(Status.TRANSINPROCESS);
			}
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	// Getting Parameter List for Template Data
	public static void getParameterWiseComplaintEssentials(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		List<Entry> lstTemps = null;
		Map putmap = new HashMap();
		//Map<String, Map<String, String>> mpTempParaValues = null;
		try
		{
			//UserVO userVO = getUserVO(_rq);
			lstTemps = (List<Entry>) session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_ALL_TEMPLATE_LIST+_fb.getSelectedMenuId());

			// Patient Centric Data and Episode Visit Data
			//mpTempParaValues = new HashMap<String, Map<String, String>>();
			PatientClinicalDetailVO patCliniDtlVO = new PatientClinicalDetailVO();
			HelperMethods.populate(patCliniDtlVO, _fb);
			if (lstTemps.size() > 0)
			{
				String[] templateIds = new String[lstTemps.size()];
				for (int i = 0; i < lstTemps.size(); i++)
					templateIds[i] = lstTemps.get(i).getValue();
				patCliniDtlVO.setTemplateIds(templateIds);
				//mpTempParaValues = GenericTemplateTileDATA.getPatientFinalClinicalData(_fb.getDeskType(), patCliniDtlVO, userVO);
			}

			//putmap.put(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP, mpTempParaValues);
			WebUTIL.setMapInSession(putmap, _rq);
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	// In Use to view the visit date wise template in popup
	// Viewing Dates Wise Template
	public static void getViewDateWiseComplaint(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		// List<Entry> lstAllTempsIds = null;
		// Map<String, TemplateMasterVO> mapTemplateDtl = null;
		HttpSession session = _rq.getSession();
		List<Entry> lstTemps = null;
		// Map mpTemps = null;
		// List<Entry> lstReportDates = new ArrayList<Entry>();
		List<Entry> lstVisitDates = new ArrayList<Entry>();
		try
		{
			// UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);

			lstTemps = (List<Entry>) session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_ALL_TEMPLATE_LIST+_fb.getSelectedMenuId());

			
			// Setting Desk Menu Wise
			//WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS, lstTemps);
			Map mpDeskMenuTemps = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS); 
			if(mpDeskMenuTemps==null)	mpDeskMenuTemps = new HashMap();
			mpDeskMenuTemps.put(_fb.getSelectedMenuId(), lstTemps);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS, mpDeskMenuTemps);

			Entry entry = new Entry();
			entry.setLabel(_fb.getRecordDate());
			entry.setValue(_fb.getRecordDate());
			lstVisitDates.add(entry);
			// Setting Desk Menu Wise
			//WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES, lstVisitDates);
			Map mpDeskMenuVisitDates = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES); 
			if(mpDeskMenuVisitDates==null)	mpDeskMenuVisitDates = new HashMap();
			mpDeskMenuVisitDates.put(_fb.getSelectedMenuId(), lstVisitDates);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES, mpDeskMenuVisitDates);

			/*
			 * LinkedHashMap<String, String> mapRecordDates = new LinkedHashMap<String, String>();
			 * mapRecordDates.put(_fb.getRecordDate(),_fb.getRecordDate());
			 * WebUTIL.setAttributeInSession(_rq,GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP,mapRecordDates);
			 */

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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	// Setting Patient Diagnosis Detail To Profile
	/*
	 * public static boolean setPatientComplaintsDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq) { Status
	 * objStatus = new Status(); HttpSession session = null; boolean flag = true; try { session = _rq.getSession();
	 * EpisodeDiagnosisVO[] episodeDiagnosisVOs =
	 * (EpisodeDiagnosisVO[])session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_EPISODEDIAGNOSISVO_ARRAY); ProfileProforma
	 * proforma = (ProfileProforma)session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
	 * proforma.setDiagnosisProforma(_fb.getSelectedMenuId(), episodeDiagnosisVOs, _fb.getSelectedRow());
	 * WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma); objStatus.add(Status.NEW); }
	 * catch (HisRecordNotFoundException e) { e.printStackTrace(); objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
	 * flag=false; } catch (HisDataAccessException e) { e.printStackTrace(); objStatus.add(Status.ERROR_DA, e.getMessage(),
	 * ""); flag=false; } catch (HisApplicationExecutionException e) { e.printStackTrace(); objStatus.add(Status.ERROR_AE,
	 * e.getMessage(), ""); flag=false; } catch (HisException e) { e.printStackTrace(); objStatus.add(Status.ERROR,
	 * e.getMessage(), ""); flag=false; } catch (Exception e) { e.printStackTrace(); objStatus.add(Status.ERROR_AE,
	 * e.getMessage(), ""); flag=false; } finally { WebUTIL.setStatus(_rq, objStatus); } return flag; }
	 */

	// Setting Template View Report Data
	
	public static void setPatientComplaintsDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		List<Entry> lstReportDates = new ArrayList<Entry>();
		List<Entry> lstSelectedTemps = new ArrayList<Entry>();
		Map<String, TemplateMasterVO> mapTemplateDtl = null;
		List<Entry> lstTemps = null;
		LinkedHashMap<String, String> mapRecordDates = new LinkedHashMap<String, String>();
		Map<String, Map<String, Map<String, String>>> mpChartClinicalData = null;
		LinkedHashMap<String, LinkedHashMap<String, TemplateParameterMasterVO>> mapAllValuedTempParas = null;
		HttpSession session = _rq.getSession();
		List<Entry> lstVisitDates = new ArrayList<Entry>();
		try
		{
			//UserVO userVO = getUserVO(_rq);

			mapTemplateDtl = (Map<String, TemplateMasterVO>) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_DTL_MAP);
			mapAllValuedTempParas = (LinkedHashMap<String, LinkedHashMap<String, TemplateParameterMasterVO>>) session
					.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_VALUED_TEMP_PARA_MAP);

			// Getting Desk Menu Wise
			//mpChartClinicalData = (Map<String, Map<String, Map<String, String>>>) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);
			Map mpDeskMenuChartClinicalData = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);
			mpChartClinicalData = (Map<String, Map<String, Map<String, String>>>) mpDeskMenuChartClinicalData.get(_fb.getSelectedMenuId());

			//added by manisha date: 24.8.2017
			LinkedHashMap<String, LinkedHashMap<String, String>> mapAllRecordDates = null;
			mapAllRecordDates = (LinkedHashMap) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP);

			// GenericTemplateTileDATA.getPatientChartClinicalDataTempWise(_fb.getDeskType(),patCliniDtlVO, lstReportDates, lstSelectedTemps, userVO);
			
			// Getting Visit Dates
			lstReportDates = (List<Entry>) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_VISIT_DATES);
			// Setting Record Dates
			for (Entry e : lstReportDates)
				mapRecordDates.put(e.getLabel(), e.getLabel());

			// Setting Selected Template
			if(_fb.getSelectedDates()!=null)
			{
				for (int i = 0; i < _fb.getSelectedDates().length; i++)
				{
					Entry entry = new Entry();
					entry.setValue(_fb.getSelectedDates()[i]);
					entry.setLabel(mapRecordDates.get(_fb.getSelectedDates()[i]));
					lstVisitDates.add(entry);
				}
			}
			else
			{
				for (Entry ent : lstReportDates)
				{
					Entry entry = new Entry();
					entry.setValue(ent.getLabel());
					entry.setLabel(ent.getLabel());
					lstVisitDates.add(entry);
				}
			}
			// Setting Desk Menu Wise
			//WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES, lstVisitDates);
			Map mpDeskMenuVisitDates = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES); 
			if(mpDeskMenuVisitDates==null)	mpDeskMenuVisitDates = new HashMap();
			mpDeskMenuVisitDates.put(_fb.getSelectedMenuId(), lstVisitDates);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES, mpDeskMenuVisitDates);


			// Getiing Desk Menu Wise
			Map mpDeskMenuReportModes = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP);
			//if(mpDeskMenuReportModes!=null && mpDeskMenuReportModes.get(_fb.getSelectedMenuId())!=null)
			//	_fb.setReportMode((String)mpDeskMenuReportModes.get(_fb.getSelectedMenuId()));
			//else
			//	_fb.setReportMode(null);

			
			if (_fb.getReportMode().equals(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_DATE_WISE))
			{
				lstTemps = (List<Entry>) session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_ALL_TEMPLATE_LIST+_fb.getSelectedMenuId());

				// Setting Desk Menu Wise
				//WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS, lstTemps);
				Map mpDeskMenuTemps = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS); 
				if(mpDeskMenuTemps==null)	mpDeskMenuTemps = new HashMap();
				mpDeskMenuTemps.put(_fb.getSelectedMenuId(), lstTemps);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS, mpDeskMenuTemps);
				
				
				
				/*mpChartClinicalData = (Map<String, Map<String, Map<String, String>>>) session
						.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);*/

				/*
				 * Map<String, Map<String, Map<String, String>>> mpChartClinicalDataTemp = new HashMap<String, Map<String,Map<String,String>>>();
				 * for(int i=0;i<_fb.getSelectedDates().length;i++) { Map mpVisitData
				 * =(Map)mpChartClinicalData.get(_fb.getSelectedDates()[i]);
				 * 
				 * mpChartClinicalDataTemp.put(_fb.getSelectedDates()[i], mpVisitData); }
				 * 
				 * WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP,
				 * mpChartClinicalDataTemp);
				 */

				// Setting Selected Template
				/*
				 * for (int i = 0; i < _fb.getSelectedTemplates().length; i++) { TemplateMasterVO voTemp =
				 * mapTemplateDtl.get(_fb.getSelectedTemplates()[i]); Entry e = new Entry();
				 * e.setLabel(voTemp.getTemplateName()); e.setValue(voTemp.getTemplateId()); lstSelectedTemps.add(e); }
				 * WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS, lstSelectedTemps);
				 */
			}
			else if (_fb.getReportMode().equals(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_PARAMETER_WISE))
			{
				// Getting Selected Paras
				LinkedHashMap<String, List<String>> mapSelPara = new LinkedHashMap<String, List<String>>();
				for (int i = 0; i < _fb.getSelectedParas().length; i++)
				{
					String tempId = _fb.getSelectedParas()[i].split("#")[0];
					String paraId = _fb.getSelectedParas()[i].split("#")[1];
					List<String> lstPara = mapSelPara.get(tempId);
					if (lstPara == null) lstPara = new ArrayList<String>();
					mapSelPara.put(tempId, lstPara);
					lstPara.add(paraId);
				}
				LinkedHashMap<String, LinkedHashSet<String>> mapViewParas = new LinkedHashMap<String, LinkedHashSet<String>>();
				for (String tempId : mapSelPara.keySet())
				{
					LinkedHashMap<String, TemplateParameterMasterVO> mapParaDtl = mapAllValuedTempParas.get(tempId);
					List<String> selectedParas = mapSelPara.get(tempId);
					LinkedHashSet<String> viewParas = mapViewParas.get(tempId);
					if (viewParas == null) viewParas = new LinkedHashSet<String>();
					mapViewParas.put(tempId, viewParas);
					for (String selParaId : selectedParas)
					{
						for (String paraId : mapParaDtl.keySet())
						{
							TemplateParameterMasterVO tempParaVO = mapParaDtl.get(paraId);
							if (tempParaVO.getParaId() != null && !tempParaVO.getParaId().equals("") && tempParaVO.getParaId().equals(selParaId)) viewParas
									.add(paraId);
							else if (tempParaVO.getParentParaId() != null && !tempParaVO.getParentParaId().equals(""))
							{
								String[] parents = tempParaVO.getParentParaId().split(GenericTemplateUtility.SEP_IN_PARA_PARENT);
								boolean flag = false;
								for (int i = 0; i < parents.length; i++)
									if (parents[i].equals(selParaId))
									{
										flag = true;
										break;
									}
								if (flag) viewParas.add(paraId);
							}
						}
					}
				}
				// Setting Template Chart Selected Template
				LinkedHashMap<String, String> mapChartTemps = new LinkedHashMap<String, String>();
				for (String tempId : mapViewParas.keySet())
				{
					TemplateMasterVO voTemp = mapTemplateDtl.get(tempId);
					Entry e = new Entry();
					e.setLabel(voTemp.getTemplateName());
					e.setValue(voTemp.getTemplateId());
					lstSelectedTemps.add(e);
					mapChartTemps.put(voTemp.getTemplateId(), voTemp.getTemplateName());
				}
				// Setting Desk Menu Wise
				//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_LIST_MAP, mapChartTemps);
				Map mpDeskMenuChartTemps = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_LIST_MAP); 
				if(mpDeskMenuChartTemps==null)	mpDeskMenuChartTemps = new HashMap();
				mpDeskMenuChartTemps.put(_fb.getSelectedMenuId(), mapChartTemps);
				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_LIST_MAP, mpDeskMenuChartTemps);

				
				// Setting Template Chart Selected Template Parameters
				LinkedHashMap<String, List<Entry>> mapChartTempParas = new LinkedHashMap<String, List<Entry>>();
				for (String tempId : mapViewParas.keySet())
				{
					List<Entry> lstParas = mapChartTempParas.get(tempId);
					if (lstParas == null) lstParas = new ArrayList<Entry>();
					mapChartTempParas.put(tempId, lstParas);
					for (String paraId : mapViewParas.get(tempId))
					{
						Entry e = new Entry(mapAllValuedTempParas.get(tempId).get(paraId).getParaName(), paraId);
						lstParas.add(e);
					}
				}
				// Setting Desk Menu Wise
				//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP, mapChartTempParas);
				Map mpDeskMenuChartTempParas = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
				if(mpDeskMenuChartTempParas==null) mpDeskMenuChartTempParas = new HashMap();
				mpDeskMenuChartTempParas.put(_fb.getSelectedMenuId(), mapChartTempParas);
				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP, mpDeskMenuChartTempParas);

				// Map of RecordDate/Map of Temp Id/Map of Parameter Id/Para Value
				PatientClinicalDetailVO patCliniDtlVO = new PatientClinicalDetailVO();
				HelperMethods.populate(patCliniDtlVO, _fb);
				patCliniDtlVO.setDeskMenuId(_fb.getSelectedMenuId());

				// Remove Report Dates that don't have Data
				// boolean dataFoundFlag = false;
				List<String> lstRemove = new ArrayList<String>();
				for (String recordDate : mapRecordDates.keySet())
				{
					Map<String, Map<String, String>> mpTemp = mpChartClinicalData.get(recordDate);
					if (mpTemp == null)
					{
						lstRemove.add(recordDate);
						continue;
					}
					// Removing Not Useful Templates Data
					List<String> lstRemoveTemplates = new ArrayList<String>();
					for (String key : mpTemp.keySet())
					{
						boolean flag = false;
						for (Entry entTemp : lstSelectedTemps)
							if (entTemp.getValue().split("#")[0].equals(key))
							{
								flag = true;
								break;
							}
						if (!flag) lstRemoveTemplates.add(key);
						else if (mpTemp.get(key).size() == 0) lstRemoveTemplates.add(key);
					}
					for (String tempId : lstRemoveTemplates)
						mpTemp.remove(tempId);

					// Checking For Record Date Data
					if (mpTemp.keySet().size() == 0)
					{
						mpChartClinicalData.remove(recordDate);
						lstRemove.add(recordDate);
					}
					// else
					// dataFoundFlag = true;
				}
				List<Entry> lstRemoveEntry = new ArrayList<Entry>();
				for (String str : lstRemove)
				{
					mapRecordDates.remove(str);
					for (Entry entObj : lstReportDates)
						if (entObj.getValue().equals(str))
						{
							lstRemoveEntry.add(entObj);
							break;
						}
				}
				//for (Entry entObj : lstRemoveEntry)
					//lstReportDates.remove(entObj);

				// Removing Non-data Selected Parameters in Report Parameter Wise
				// dataFoundFlag = false;
				
				// Getting Desk Menu Wise
				//mapChartTempParas = (LinkedHashMap<String, List<Entry>>) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
				Map mpDeskMenuTempParas = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
				mapChartTempParas = (LinkedHashMap<String, List<Entry>>) mpDeskMenuTempParas.get(_fb.getSelectedMenuId());


				for (String tempId : mapChartTempParas.keySet())
				{
					List<Entry> removePara = new ArrayList<Entry>();
					for (Entry entObj : mapChartTempParas.get(tempId))
					{
						boolean flag = false;
						// Map of RecordDate/Map of Temp Id/Map of Parameter Id/Para Value
						for (String recordDate : mpChartClinicalData.keySet())
							for (String templateId : mpChartClinicalData.get(recordDate).keySet())
								if (templateId.equals(tempId)) for (String paraId : mpChartClinicalData.get(recordDate).get(templateId).keySet())
									if (paraId.equals(entObj.getValue()))
									{
										// dataFoundFlag = true;
										flag = true;
									}
						if (!flag) removePara.add(entObj);
					}
					for (Entry entObj : removePara)
						mapChartTempParas.get(tempId).remove(entObj);
				}
			}

			// Setting Desk Menu Wise
			//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpChartClinicalData);
			if(mpDeskMenuChartClinicalData==null) mpDeskMenuChartClinicalData = new HashMap();
			mpDeskMenuChartClinicalData.put(_fb.getSelectedMenuId(), mpChartClinicalData);
			WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpDeskMenuChartClinicalData);

			// Setting Clinical Data
			/*
			 * if (_fb.getReportMode().equals(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE)) {
			 * WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_VISIT_DATES, lstReportDates); } else if
			 * (_fb.getReportMode().equals(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_PARAMETER_WISE)) {
			 */
			//added by manisha date: 24.8.2017
			if(mapAllRecordDates==null) mapAllRecordDates = new LinkedHashMap<String, LinkedHashMap<String,String>>();
			mapAllRecordDates.put(_fb.getSelectedMenuId(), mapRecordDates);
			WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP, mapAllRecordDates);
			// }

			WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_VIEW_MODE,
					GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);

			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			proforma.setComplaintsProforma(_fb.getSelectedMenuId());
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);

			objStatus.add(Status.NEW);
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	// Getting Patient progress notes
	public static boolean getPatientProgressNotes(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		// HttpSession session=WebUTIL.getSession(_rq);
		// List allergyTypeList=new ArrayList();
		boolean flag = true;
		// String url="";
		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);

			_fb.setSelectedRow(new String[]
			{ "" });
			PatientDetailVO voDp = new PatientDetailVO();
			HelperMethods.populate(voDp, _fb);
			voDp.setPatAdmNo(_fb.getAdmissionNo());
			DoctorRoundDtlVO[] doctorRoundDtlVOs = GenericPatientProfileDATA.getPatientProgressNotes(_fb.getPatCrNo(), userVO, voDp, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_PROGRESS_NOTES_ARRAY, doctorRoundDtlVOs);

			if (doctorRoundDtlVOs.length == 0)
			{
				objStatus.add(Status.NEW, "", "No Progress Notes Found");
			}
			/*
			 * ////Allergy PROFORMA to get the check field at the time of modify ProfileProforma proforma =
			 * (ProfileProforma)session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			 * 
			 * List lstProMenus = (List)session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST);
			 * if(proforma!=null) { //Map<String, StringBuilder> map = proforma.getAddedOptionsMap(); List lstMenus =
			 * proforma.getOptionsOrderList();
			 * 
			 * for(int i=0;i<lstMenus.size();i++) { String key = (String)lstMenus.get(i); DeskMenuMasterVO vo =null;
			 * Iterator lstProMenusItr = lstProMenus.iterator(); while(lstProMenusItr.hasNext()) { DeskMenuMasterVO v =
			 * (DeskMenuMasterVO)lstProMenusItr.next(); if(v.getDeskMenuId().equals(key)) { vo=v; break; } } url =
			 * vo.getDeskUrl();
			 * 
			 * 
			 * if(url.equals("DESKALLERGIES")) { Map<String,Map<String,Object>>
			 * mapProfileOptions=proforma.getMapProfileOptions(); Map<String,Object> mpAllerg = mapProfileOptions.get(key);
			 * allergyTypeList=(List)mpAllerg.get("lstEpisodeAllergiesVO"); } }
			 * 
			 * String strAllergyCheckFlag=""; if(patAllergyDtlVOs!=null) { for(int i=0;i<patAllergyDtlVOs.length;i++)
			 * {boolean allergyTypeFlag=false; Iterator allergyIterator=allergyTypeList.iterator();
			 * while(allergyIterator.hasNext()) { PatAllergyDtlVO vo=(PatAllergyDtlVO)allergyIterator.next();
			 * 
			 * if(vo.getAllergyTypeCode().equals(patAllergyDtlVOs[i].getAllergyTypeCode())) { allergyTypeFlag=true; } }
			 * 
			 * if(allergyTypeFlag==true) { strAllergyCheckFlag+=OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_YES+"#"; }
			 * else { strAllergyCheckFlag+=OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_NO+"#"; } }
			 * 
			 * 
			 * if(strAllergyCheckFlag.length()>0)
			 * strAllergyCheckFlag=strAllergyCheckFlag.substring(0,strAllergyCheckFlag.length()-1);
			 * 
			 * _fb.setAllergyCheckFlag(strAllergyCheckFlag); } }
			 */
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Getting Patient opd progress notes
	public static boolean getPatientOPDProgressNotes(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		boolean flag = true;
		try
		{
			UserVO userVO = getUserVO(_rq);
			_fb.setSelectedRow(new String[]
			{ "" });
			EpisodeVO episodeVO = new EpisodeVO();
			episodeVO.setPatCrNo(_fb.getPatCrNo());
			episodeVO.setEpisodeCode(_fb.getEpisodeCode());
			episodeVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			EpisodeSummaryDetailVO[] episodeSummaryDtlVOs = GenericPatientProfileDATA.getPatientOPDProgressNotes(episodeVO, userVO);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_PROGRESS_NOTES_ARRAY, episodeSummaryDtlVOs);

			if (episodeSummaryDtlVOs == null)
			{
				objStatus.add(Status.NEW, "", "No Progress Notes Found");
			}
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Setting Patient progress notes Detail To Profile
	public static boolean setPatientProgressNotes(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		boolean flag = true;
		try
		{
			session = _rq.getSession();
			DoctorRoundDtlVO[] doctorRoundDtlVOs = (DoctorRoundDtlVO[]) session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_PROGRESS_NOTES_ARRAY);
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			proforma.setProgressNotesDetailProforma(_fb.getSelectedMenuId(), doctorRoundDtlVOs, _fb.getSelectedRow());
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Getting Patient Alerts Detail
	public static boolean getPatientAlertsDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		List alertsTypeList = new ArrayList();
		boolean flag = true;
		String url = "";
		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);

			_fb.setSelectedRow(new String[]
			{ "" });
			PatientDetailVO voDp = new PatientDetailVO();
			HelperMethods.populate(voDp, _fb);
			PatientAlertsDetailVO[] patientAlertsDetailVOs = GenericPatientProfileDATA.getPatientAlertsDetail(_fb.getPatCrNo(), userVO, voDp, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_ALERTSVO_ARRAY, patientAlertsDetailVOs);

			// //Patient Alerts PROFORMA to get the check field at the time of modify
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);

			List lstProMenus = (List) session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST);
			if (proforma != null)
			{
				// Map<String, StringBuilder> map = proforma.getAddedOptionsMap();
				List lstMenus = proforma.getOptionsOrderList();

				for (int i = 0; i < lstMenus.size(); i++)
				{
					String key = (String) lstMenus.get(i);
					DeskMenuMasterVO vo = null;
					Iterator lstProMenusItr = lstProMenus.iterator();
					while (lstProMenusItr.hasNext())
					{
						DeskMenuMasterVO v = (DeskMenuMasterVO) lstProMenusItr.next();
						if (v.getDeskMenuId().equals(key))
						{
							vo = v;
							break;
						}
					}
					url = vo.getDeskUrl();

					if (url.equals("GENERICPATIENTALERTS"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpAlerts = mapProfileOptions.get(key);
						alertsTypeList = (List) mpAlerts.get("lstPatientAlertsVO");
					}
				}

				String strAlertsCheckFlag = "";
				if (patientAlertsDetailVOs != null)
				{
					for (int i = 0; i < patientAlertsDetailVOs.length; i++)
					{
						boolean alertsTypeFlag = false;
						Iterator alertsIterator = alertsTypeList.iterator();
						while (alertsIterator.hasNext())
						{
							PatientAlertsDetailVO vo = (PatientAlertsDetailVO) alertsIterator.next();

							if (vo.getPatAlertId().equals(patientAlertsDetailVOs[i].getPatAlertId()))
							{
								alertsTypeFlag = true;

							}
						}

						if (alertsTypeFlag == true)
						{
							strAlertsCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_YES + "#";
						}
						else
						{
							strAlertsCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_NO + "#";
						}
					}

					if (strAlertsCheckFlag.length() > 0) strAlertsCheckFlag = strAlertsCheckFlag.substring(0, strAlertsCheckFlag.length() - 1);

					_fb.setGenericCheckFlag(strAlertsCheckFlag);
				}
			}
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Setting Patient Alerts Detail To Profile
	public static boolean setPatientAlertsDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		boolean flag = true;
		try
		{
			session = _rq.getSession();
			PatientAlertsDetailVO[] patAlertsDetailVOs = (PatientAlertsDetailVO[]) session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ALERTSVO_ARRAY);
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			proforma.setAlertsProforma(_fb.getSelectedMenuId(), patAlertsDetailVOs, _fb.getSelectedRow());
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Getting Patient Exam Image Detail
	public static boolean getPatientImageExamDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		String url = "";
		List imageTypeList = new ArrayList();
		boolean flag = true;
		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);

			_fb.setSelectedRow(new String[]
			{ "" });
			PatientDetailVO voDp = new PatientDetailVO();
			HelperMethods.populate(voDp, _fb);
			voDp.setPatAdmNo(_fb.getAdmissionNo());
			OpdPatientImageDtlVO[] episodeExamImagesVOs = GenericPatientProfileDATA.getEpisodeExamImages(_fb.getPatCrNo(), userVO, voDp, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED);
			if (episodeExamImagesVOs.length <= 0) throw new HisRecordNotFoundException("No Exam Images Found");
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_EPISODEEXAMIMAGESVO_ARRAY, episodeExamImagesVOs);

			// //Image PROFORMA to get the check field at the time of modify
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);

			List lstProMenus = (List) session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST);
			if (proforma != null)
			{
				// Map<String, StringBuilder> map = proforma.getAddedOptionsMap();
				List lstMenus = proforma.getOptionsOrderList();

				for (int i = 0; i < lstMenus.size(); i++)
				{
					String key = (String) lstMenus.get(i);
					DeskMenuMasterVO vo = null;
					Iterator lstProMenusItr = lstProMenus.iterator();
					while (lstProMenusItr.hasNext())
					{
						DeskMenuMasterVO v = (DeskMenuMasterVO) lstProMenusItr.next();
						if (v.getDeskMenuId().equals(key))
						{
							vo = v;
							break;
						}
					}
					url = vo.getDeskUrl();

					if (url.equals("IMAGEEXAMINATION"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpImage = mapProfileOptions.get(key);
						imageTypeList = (List) mpImage.get("lstOpdPatientImageDtlVO");
					}
				}

				String strImageCheckFlag = "";
				if (episodeExamImagesVOs != null)
				{
					for (int i = 0; i < episodeExamImagesVOs.length; i++)
					{
						boolean imageTypeFlag = false;
						Iterator imageIterator = imageTypeList.iterator();
						while (imageIterator.hasNext())
						{
							OpdPatientImageDtlVO vo = (OpdPatientImageDtlVO) imageIterator.next();

							if (vo.getImageFileName().equals(episodeExamImagesVOs[i].getImageFileName()))
							{
								imageTypeFlag = true;

							}
						}

						if (imageTypeFlag == true)
						{
							strImageCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_YES + "#";
						}
						else
						{
							strImageCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_NO + "#";
						}
					}

					if (strImageCheckFlag.length() > 0) strImageCheckFlag = strImageCheckFlag.substring(0, strImageCheckFlag.length() - 1);

					_fb.setImageCheckFlag(strImageCheckFlag);
				}
			}

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Setting Patient Exam Image Detail To Profile
	public static boolean setPatientImageExamDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		boolean flag = true;
		try
		{
			session = _rq.getSession();
			OpdPatientImageDtlVO[] episodeExamImagesVOs = (OpdPatientImageDtlVO[]) session
					.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_EPISODEEXAMIMAGESVO_ARRAY);
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			proforma.setExamImagesProforma(_fb.getSelectedMenuId(), episodeExamImagesVOs, _fb.getSelectedRow());
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Getting Patient Next Visit Detail
	public static boolean getPatientNextVisitDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		boolean flag = true;
		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);

			_fb.setSelectedRow(new String[]
			{ "" });
			EpisodeVO episodeVO = GenericPatientProfileDATA.getPatientNextVisitDetail(_fb.getPatCrNo(), _fb.getEpisodeCode(),
					_fb.getEpisodeVisitNo(), userVO);
			if (episodeVO.getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo())
					&& (episodeVO.getEpisodeNextVisitDate() != null || episodeVO.getComplainDetail() != null))
			{
			}
			else throw new HisRecordNotFoundException("No Next Visit Detail Exists");

			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_NEXTVISITDETAIL_EPISODEVO, episodeVO);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Setting Patient Next Visit Detail To Profile
	public static boolean setPatientNextVisitDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		boolean flag = true;
		try
		{
			session = _rq.getSession();
			EpisodeVO episodeVO = (EpisodeVO) session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_NEXTVISITDETAIL_EPISODEVO);
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			proforma.setNextVisitDetailProforma(_fb.getSelectedMenuId(), episodeVO, _fb.getSelectedRow());
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Getting Patient Profile Footer Detail
	public static boolean getPatientProfileFooterDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		boolean flag = true;
		HttpSession session = _rq.getSession();
		String url ="";
		String remarks = "";
		String reviewOn = "", dischargeType="", dischargeAdvisedBy="", dischargeAdvisedDept="";
		DisclaimerMstVO disclaimerVO = new DisclaimerMstVO();
		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);
			PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);			

			String profileType = _fb.getProfileType().split("#")[0];
			if (_fb.getIsDesclaimerRequired().equals(OpdConfig.DISCLAIMER_REQUIRED_YES))
			{
				DisclaimerMstVO disclaimerMstVO = GenericPatientProfileDATA.fetchDisclaimerDetails(_fb.getDepartmentUnitCode(), profileType, userVO);
				Map mp = GenericPatientProfileDATA.getDischargeProfileEssentials(_fb.getDepartmentUnitCode(), _fb.getPatCrNo(), userVO);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_PROFILE_DTL_DISCLAIMER_DTL_VO, disclaimerMstVO);

				if (disclaimerMstVO.getDisclaimerDesc1() != null)
				{
					_fb.setDisclaimerDesc1(disclaimerMstVO.getDisclaimerDesc1());
				}
				if (disclaimerMstVO.getDisclaimerDesc2() != null)
				{
					_fb.setDisclaimerDesc2(disclaimerMstVO.getDisclaimerDesc2());
				}
				if (disclaimerMstVO.getDisclaimerDesc3() != null)
				{
					_fb.setDisclaimerDesc3(disclaimerMstVO.getDisclaimerDesc3());
				}
				
				WebUTIL.setMapInSession(mp, _rq);

			
				
				// //Discharge Profile Footer PROFORMA
				ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);

				List lstProMenus = (List) session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST);
				if (proforma != null)
				{
					// Map<String, StringBuilder> map = proforma.getAddedOptionsMap();
					List lstMenus = proforma.getOptionsOrderList();

					for (int i = 0; i < lstMenus.size(); i++)
					{
						String key = (String) lstMenus.get(i);
						DeskMenuMasterVO vo = null;
						Iterator lstProMenusItr = lstProMenus.iterator();
						while (lstProMenusItr.hasNext())
						{
							DeskMenuMasterVO v = (DeskMenuMasterVO) lstProMenusItr.next();
							if (v.getDeskMenuId().equals(key))
							{
								vo = v;
								break;
							}
						}
						url = vo.getDeskUrl();

						if (url.equals("VITALRECORDINGS"))
						{
							Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
							Map<String, Object> mpFooter = mapProfileOptions.get(key);
							remarks = (String) mpFooter.get("Remarks");
							reviewOn = (String) mpFooter.get("ReviewOn");
							dischargeType = (String) mpFooter.get("DichargeType");
							dischargeAdvisedBy = (String) mpFooter.get("DichargeAdvisedBy");
							dischargeAdvisedDept = (String) mpFooter.get("DichargeAdvisedDept");
							// _profileType=(String)mpFooter.get("ProfileType");
							disclaimerVO = (DisclaimerMstVO) mpFooter.get("Disclaimer");

							_fb.setRemarks(remarks);
							_fb.setReviewDate(reviewOn);
							_fb.setDischargeType(dischargeType.split("#")[0]);
							_fb.setDischargeAdvisedBy(dischargeAdvisedBy.split("#")[0]);
							_fb.setDischargeAdvisedDept(dischargeAdvisedDept.split("#")[0]);
							_fb.setDischargeTypeName(dischargeType.split("#")[1]);
							_fb.setDischargeAdvisedByName(dischargeAdvisedBy.split("#")[1]);
							_fb.setDischargeAdvisedDeptName(dischargeAdvisedDept.split("#")[1]);
						}
					}
				}
			}
			
			Map essentialMap = GenericPatientProfileDATA.getPatientProfileFooterEssentials(ptaientDetailVO,userVO);
			
			List<EpisodeVO> episodeVO = (List<EpisodeVO>) essentialMap.get(OpdConfig.PATIENT_PROFILE_FOOTER_ESSENTIALS);
			
			EpisodeVO eVO = null;
			eVO = episodeVO.get(0); 
			
			WebUTIL.getSession(_rq).setAttribute(OpdConfig.PATIENT_PROFILE_FOOTER_CONSULTANT_NAME, eVO);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Setting Patient Profile Footer Detail To Profile
	public static boolean setPatientProfileFooterDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		DisclaimerMstVO disclaimerMstVO = new DisclaimerMstVO();
		boolean flag = true;
		try
		{
			session = _rq.getSession();
			String profileType = _fb.getProfileType().split("#")[0];
			disclaimerMstVO = (DisclaimerMstVO) session.getAttribute(OpdConfig.PAT_PROFILE_DTL_DISCLAIMER_DTL_VO);
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			proforma.setProfileFooterDetailProforma(_fb.getSelectedMenuId(), _fb.getRemarks(), _fb.getReviewDate(), _fb.getDischargeType(), _fb.getDischargeTypeName(),
					_fb.getDischargeAdvisedBy(), _fb.getDischargeAdvisedByName(), _fb.getDischargeAdvisedDept(), _fb.getDischargeAdvisedDeptName(), profileType, disclaimerMstVO,_fb.getSnomdCIdRemarks(),_fb.getSnomdPTRemarks());
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}
	
	
  

	// Getting Patient Investigation Detail
	public static boolean getPatientInvestigationDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		boolean flag = true;
		List investigationTypeList = new ArrayList();
		String url = "";
		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);

			_fb.setSelectedRow(new String[]
			{ "" });
			PatientDetailVO voDp = new PatientDetailVO();
			HelperMethods.populate(voDp, _fb);
			voDp.setPatAdmNo(_fb.getAdmissionNo());
			ProfileInvestigationVO[] profileInvestigationVOs = GenericPatientProfileDATA.getEpisodeInvestigation(_fb.getPatCrNo(), _fb.getDeskType(), userVO, voDp, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_EPISODEINVESTIGATIONVO_ARRAY, profileInvestigationVOs);

			// //Investigation PROFORMA
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);

			List lstProMenus = (List) session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST);
			if (proforma != null)
			{
				// Map<String, StringBuilder> map = proforma.getAddedOptionsMap();
				List lstMenus = proforma.getOptionsOrderList();

				for (int i = 0; i < lstMenus.size(); i++)
				{
					String key = (String) lstMenus.get(i);
					DeskMenuMasterVO vo = null;
					Iterator lstProMenusItr = lstProMenus.iterator();
					while (lstProMenusItr.hasNext())
					{
						DeskMenuMasterVO v = (DeskMenuMasterVO) lstProMenusItr.next();
						if (v.getDeskMenuId().equals(key))
						{
							vo = v;
							break;
						}
					}
					url = vo.getDeskUrl();

					if (url.equals("RESULTVIEWING"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpInv = mapProfileOptions.get(key);
						investigationTypeList = (List) mpInv.get("lstEpisodeInvestigationVO");
					}
				}

				String strInvestigationCheckFlag = "";
				if (profileInvestigationVOs != null)
				{
					for (int i = 0; i < profileInvestigationVOs.length; i++)
					{
						boolean invTypeFlag = false;
						Iterator invIterator = investigationTypeList.iterator();
						while (invIterator.hasNext())
						{
							ProfileInvestigationVO vo = (ProfileInvestigationVO) invIterator.next();

							if (vo.getReqDNo().equals(profileInvestigationVOs[i].getReqDNo()))
							{
								invTypeFlag = true;

							}
						}

						if (invTypeFlag == true)
						{
							strInvestigationCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_YES + "#";
						}
						else
						{
							strInvestigationCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_NO + "#";
						}
					}

					if (strInvestigationCheckFlag.length() > 0)

					strInvestigationCheckFlag = strInvestigationCheckFlag.substring(0, strInvestigationCheckFlag.length() - 1);

					_fb.setInvestigationCheckFlag(strInvestigationCheckFlag);
				}

			}

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Setting Patient Investigation Detail To Profile
	public static boolean setPatientInvestigationDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		boolean flag = true;
		String req[];
		int i;
		String requisition="";
		try
		{
			session = _rq.getSession();
			ProfileInvestigationVO[] profileInvestigationVOs = (ProfileInvestigationVO[]) session
					.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_EPISODEINVESTIGATIONVO_ARRAY);
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			req = _fb.getRequisitionDNo();
			for(i=0;i<req.length;i++)
			{
				requisition= req[i]+","+requisition;
				_fb.setReqDnoList(requisition);
				//requisition=req[i]+",";
			}
			//_fb.setReqDnoList(_fb.getRequisitionDNo()[0]+","+_fb.getRequisitionDNo()[1]+","+_fb.getRequisitionDNo()[2]+","+_fb.getRequisitionDNo()[3]+","+_fb.getRequisitionDNo()[4]);
			proforma.setInvestigationProforma(_fb.getSelectedMenuId(), profileInvestigationVOs, _fb.getSelectedRow());
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Getting Patient Progress Notes in General Profile
	public static boolean getPatientEpisodeProgressNotes(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session=WebUTIL.getSession(_rq);
		List progNotesList=new ArrayList();
		boolean flag = true;
		String url="";
		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);

			_fb.setSelectedRow(new String[]{""});
			PatientDetailVO voDp = new PatientDetailVO();
			HelperMethods.populate(voDp, _fb);
			voDp.setPatAdmNo(_fb.getAdmissionNo());
			DoctorRoundDtlVO[] doctorRoundDtlVOs = GenericPatientProfileDATA.getPatientProgressNotes(_fb.getPatCrNo(), userVO, voDp, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED);
			if (doctorRoundDtlVOs.length == 0)  throw new HisRecordNotFoundException("No Progress Notes Found");
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_PROGRESS_NOTES_ARRAY, doctorRoundDtlVOs);

			// //Progress Notes from PROFORMA to get the check field at the time of modify
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			List lstProMenus = (List) session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST);
			_fb.setProgNotesCheckFlag("");
			if (proforma != null)
			{
				// Map<String, StringBuilder> map = proforma.getAddedOptionsMap();
				List lstMenus = proforma.getOptionsOrderList();

				for (int i = 0; i < lstMenus.size(); i++)
				{
					String key = (String) lstMenus.get(i);
					DeskMenuMasterVO vo = null;
					Iterator lstProMenusItr = lstProMenus.iterator();
					while (lstProMenusItr.hasNext())
					{
						DeskMenuMasterVO v = (DeskMenuMasterVO) lstProMenusItr.next();
						if (v.getDeskMenuId().equals(key))
						{
							vo = v;
							break;
						}
					}
					url = vo.getDeskUrl();

					if (url.equals("DOCTORROUND"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpExtInvs = mapProfileOptions.get(key);
						progNotesList = (List) mpExtInvs.get("lstPatEpiProgressNotesDtlVO");
					}
				}

				String strProgNotesCheckFlag = "";
				if (doctorRoundDtlVOs != null)
				{
					for (int i = 0; i < doctorRoundDtlVOs.length; i++)
					{
						boolean selFlag = false;
						Iterator iterator = progNotesList.iterator();
						while (iterator.hasNext())
						{
							DoctorRoundDtlVO vo = (DoctorRoundDtlVO) iterator.next();

							if (vo.getRoundTime().equals(doctorRoundDtlVOs[i].getRoundTime()) && vo.getProgressNote().equals(doctorRoundDtlVOs[i].getProgressNote()))
							{
								selFlag = true;
								break;
							}
						}
						if (selFlag == true)
						{
							strProgNotesCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_YES + "#";
						}
						else
						{
							strProgNotesCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_NO + "#";
						}
					}

					if (strProgNotesCheckFlag.length() > 0) strProgNotesCheckFlag = strProgNotesCheckFlag.substring(0, strProgNotesCheckFlag.length() - 1);

					_fb.setProgNotesCheckFlag(strProgNotesCheckFlag);
				}
			}
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Setting Patient Progress notes Detail To General Profile
	public static boolean setPatientEpisodeProgressNotes(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		boolean flag = true;
		try
		{
			session = _rq.getSession();
			DoctorRoundDtlVO[] doctorRoundDtlVOs = (DoctorRoundDtlVO[]) session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_PROGRESS_NOTES_ARRAY);
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			proforma.setEpiProgressNotesDetailProforma(_fb.getSelectedMenuId(), doctorRoundDtlVOs, _fb.getSelectedRow());
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}
	
	// Getting Patient External Investigation Detail
	public static boolean getPatientExtInvestigationDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		String url = "";
		List extInvList = new ArrayList();
		boolean flag = true;
		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);

			_fb.setSelectedRow(new String[]{""});
			PatientDetailVO voDp = new PatientDetailVO();
			HelperMethods.populate(voDp, _fb);
			voDp.setPatAdmNo(_fb.getAdmissionNo());
			EpisodeExtInvDtlVO[] arrEpisodeExtInvs = GenericPatientProfileDATA.getEpisodeExtInvestigation(_fb.getPatCrNo(),voDp, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED,userVO);
			if (arrEpisodeExtInvs.length <= 0) throw new HisRecordNotFoundException("No External Investigation Found");
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_EPISODE_EXT_INVESTIGATION_VO_ARRAY, arrEpisodeExtInvs);

			// //Ext Inv from PROFORMA to get the check field at the time of modify
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			List lstProMenus = (List) session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST);
			_fb.setExtInvCheckFlag("");
			if (proforma != null)
			{
				// Map<String, StringBuilder> map = proforma.getAddedOptionsMap();
				List lstMenus = proforma.getOptionsOrderList();

				for (int i = 0; i < lstMenus.size(); i++)
				{
					String key = (String) lstMenus.get(i);
					DeskMenuMasterVO vo = null;
					Iterator lstProMenusItr = lstProMenus.iterator();
					while (lstProMenusItr.hasNext())
					{
						DeskMenuMasterVO v = (DeskMenuMasterVO) lstProMenusItr.next();
						if (v.getDeskMenuId().equals(key))
						{
							vo = v;
							break;
						}
					}
					url = vo.getDeskUrl();

					if (url.equals("EXTERNALEXAMINATION"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpExtInvs = mapProfileOptions.get(key);
						extInvList = (List) mpExtInvs.get("lstPatientExtInvDtlVO");
					}
				}

				String strExtInvCheckFlag = "";
				if (arrEpisodeExtInvs != null)
				{
					for (int i = 0; i < arrEpisodeExtInvs.length; i++)
					{
						boolean selFlag = false;
						Iterator iterator = extInvList.iterator();
						while (iterator.hasNext())
						{
							EpisodeExtInvDtlVO vo = (EpisodeExtInvDtlVO) iterator.next();

							if (vo.getRecordDate().equals(arrEpisodeExtInvs[i].getRecordDate()) && vo.getParaId().equals(arrEpisodeExtInvs[i].getParaId()))
							{
								selFlag = true;
								break;
							}
						}
						if (selFlag == true)
						{
							strExtInvCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_YES + "#";
						}
						else
						{
							strExtInvCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_NO + "#";
						}
					}

					if (strExtInvCheckFlag.length() > 0) strExtInvCheckFlag = strExtInvCheckFlag.substring(0, strExtInvCheckFlag.length() - 1);

					_fb.setExtInvCheckFlag(strExtInvCheckFlag);
				}
			}

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	
	// Setting Patient External Investigation Image Detail To Profile
	public static boolean setPatientExtInvestigationDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		boolean flag = true;
		try
		{
			session = _rq.getSession();
			EpisodeExtInvDtlVO[] arrEpisodeExtInvs = (EpisodeExtInvDtlVO[]) session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_EPISODE_EXT_INVESTIGATION_VO_ARRAY);
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			proforma.setExtInvestigationProforma(_fb.getSelectedMenuId(), arrEpisodeExtInvs, _fb.getSelectedRow());
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	public static boolean getPatientOutTakeDetail(GenericPatientProfileFB _fb,HttpServletRequest _rq) 
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		String url = "";
		List extInvList = new ArrayList();
		boolean flag = true;
		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);

			_fb.setSelectedRow(new String[]{""});
			PatientDetailVO voDp = new PatientDetailVO();
			HelperMethods.populate(voDp, _fb);
			voDp.setPatAdmNo(_fb.getAdmissionNo());
			PatIntakeOutDtlVO[] arrPatIntakeOutDtl = GenericPatientProfileDATA.getPatientOutTakeDetail(_fb.getPatCrNo(),voDp, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED,userVO);
			if (arrPatIntakeOutDtl.length <= 0) throw new HisRecordNotFoundException("No External Investigation Found");
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_EPISODE_EXT_INVESTIGATION_VO_ARRAY, arrPatIntakeOutDtl);

			// //Ext Inv from PROFORMA to get the check field at the time of modify
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			List lstProMenus = (List) session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST);
			_fb.setExtInvCheckFlag("");
			if (proforma != null)
			{
				// Map<String, StringBuilder> map = proforma.getAddedOptionsMap();
				List lstMenus = proforma.getOptionsOrderList();

				for (int i = 0; i < lstMenus.size(); i++)
				{
					String key = (String) lstMenus.get(i);
					DeskMenuMasterVO vo = null;
					Iterator lstProMenusItr = lstProMenus.iterator();
					while (lstProMenusItr.hasNext())
					{
						DeskMenuMasterVO v = (DeskMenuMasterVO) lstProMenusItr.next();
						if (v.getDeskMenuId().equals(key))
						{
							vo = v;
							break;
						}
					}
					url = vo.getDeskUrl();

					if (url.equals("EXTERNALEXAMINATION"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpExtInvs = mapProfileOptions.get(key);
						extInvList = (List) mpExtInvs.get("lstPatientExtInvDtlVO");
					}
				}

				String strExtInvCheckFlag = "";
				if (arrPatIntakeOutDtl != null)
				{
					for (int i = 0; i < arrPatIntakeOutDtl.length; i++)
					{
						boolean selFlag = false;
						Iterator iterator = extInvList.iterator();
						while (iterator.hasNext())
						{
							EpisodeExtInvDtlVO vo = (EpisodeExtInvDtlVO) iterator.next();
//change required Here..
							//if (vo.getRecordDate().equals(arrPatIntakeOutDtl[i].getRecordDate()) && vo.getParaId().equals(arrPatIntakeOutDtl[i].getParaId()))
							//{
							//	selFlag = true;
							//	break;
							//}
						}
						if (selFlag == true)
						{
							strExtInvCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_YES + "#";
						}
						else
						{
							strExtInvCheckFlag += OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_NO + "#";
						}
					}

					if (strExtInvCheckFlag.length() > 0) strExtInvCheckFlag = strExtInvCheckFlag.substring(0, strExtInvCheckFlag.length() - 1);

					_fb.setExtInvCheckFlag(strExtInvCheckFlag);
				}
			}

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	
	// Generating and Save Final Profile
	public static boolean genrateProfile(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		boolean flag = true;
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		List diagnosisTypeList = new ArrayList();
		List allergyTypeList = new ArrayList();
		List investigationTypeList = new ArrayList();
		List treatmentTypeList = new ArrayList();
		List alertsTypeList = new ArrayList();
		List otTypeList = new ArrayList();
		List imageTypeList = new ArrayList();
		List lstEpiProgNotes = new ArrayList();
		List lstExtInvs = new ArrayList();
		List footerTypeList = new ArrayList();
		List dischargefooterTypeList = new ArrayList();
		List lstDrugAdvice = new ArrayList();
		List lstDietAdvice = new ArrayList();
		List lstRestAdvice = new ArrayList();
		List lstOtherAdvice = new ArrayList();
		List lstOtherInstructions = new ArrayList();
		List lstOtherActivity = new ArrayList();
		List chartViewList = new ArrayList();
		
		// String resultHtml;
		String url = "";
		String remarks = "";
		String reviewOn = "", dischargeType="", dischargeAdvisedBy="", dischargeAdvisedDept="", snomdcid="", snomdpt="";
		String templateDeskMenuId = "";
		// String _profileType="";
		Map mpTempParaValues = null;
		Map inAllMap = new HashMap();
		Map mpTempParas = null;
		Map inAllPreviousMap = new HashMap();
		PatientProfileDetailVO patProfileDtlVO = null;
		DisclaimerMstVO disclaimerVO = new DisclaimerMstVO();
		ProfileFooterDtlVO _profileFooterDtlVO = new ProfileFooterDtlVO();
		Map lstVisitDates = null;
		Map mpDeskMenuReportMode = null;
		try
		{
			UserVO userVO = getUserVO(_rq);

			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			List lstProMenus = lstProMenus= (List) session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST);	
			if (proforma != null)
			{

				List lstMenus = proforma.getOptionsOrderList();
				for (int i = 0; i < lstMenus.size(); i++)
				{
					String key = (String) lstMenus.get(i);
					DeskMenuMasterVO vo = null;
					Iterator lstProMenusItr = lstProMenus.iterator();
					while (lstProMenusItr.hasNext())
					{
						DeskMenuMasterVO v = (DeskMenuMasterVO) lstProMenusItr.next();
						if (v.getDeskMenuId().equals(key))
						{
							vo = v;
							break;
						}
					}
					url = vo.getDeskUrl();

					// Diagnosis Detail
					if (url.equals("DESKDIAGNOSIS"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpDiag = mapProfileOptions.get(key);
						diagnosisTypeList = (List) mpDiag.get("lstEpisodeDiagnosisVO");
					}

					// Allergy Detail
					if (url.equals("DESKALLERGIES"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpAllerg = mapProfileOptions.get(key);
						allergyTypeList = (List) mpAllerg.get("lstEpisodeAllergiesVO");
					}

					// Investigation Detail
					if (url.equals("RESULTVIEWING"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpInv = mapProfileOptions.get(key);
						investigationTypeList = (List) mpInv.get("lstEpisodeInvestigationVO");
					}

					if (url.equals("DESKTREATMENTDETAIL"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpTreat = mapProfileOptions.get(key);
						treatmentTypeList = (List) mpTreat.get("lstEpisodeTreatmentVO");
					}

					if (url.equals("GENERICPATIENTALERTS"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpAlerts = mapProfileOptions.get(key);
						alertsTypeList = (List) mpAlerts.get("lstPatientAlertsVO");
					}
					if (url.equals("PROFILEFOOTER"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpFooter = mapProfileOptions.get(key);
						remarks = (String) mpFooter.get("Remarks");
						reviewOn = (String) mpFooter.get("ReviewOn");
						dischargeType = (String) mpFooter.get("DichargeType");
						dischargeAdvisedBy = (String) mpFooter.get("DichargeAdvisedBy");
						dischargeAdvisedDept = (String) mpFooter.get("DichargeAdvisedDept");
						// _profileType=(String)mpFooter.get("ProfileType");
						disclaimerVO = (DisclaimerMstVO) mpFooter.get("Disclaimer");
						snomdcid=(String) mpFooter.get("SnomdCIdRemarks");
						snomdpt=(String) mpFooter.get("SnomdPTRemarks");

						_profileFooterDtlVO.setProfileSummary(remarks);
						_profileFooterDtlVO.setReviewDate(reviewOn);
						_profileFooterDtlVO.setDischargeType(dischargeType.split("#")[0]);
						//_profileFooterDtlVO.setDischargeAdvisedBy(dischargeAdvisedBy.split("#")[0]);
						_profileFooterDtlVO.setDischargeAdvisedBy(dischargeAdvisedBy);
						_profileFooterDtlVO.setDischargeAdvisedDept(dischargeAdvisedDept.split("#")[0]);
						_profileFooterDtlVO.setSnomdCIdRemarks(snomdcid);
						_profileFooterDtlVO.setSnomdPTRemarks(snomdpt);
						
						
						/*
						 * _profileFooterDtlVO.setDisclaimerId(disclaimerVO.getDisclaimerId());
						 * _profileFooterDtlVO.setDisclaimerDesc1(disclaimerVO.getDisclaimerDesc1());
						 * _profileFooterDtlVO.setDisclaimerDesc2(disclaimerVO.getDisclaimerDesc2());
						 * _profileFooterDtlVO.setDisclaimerDesc3(disclaimerVO.getDisclaimerDesc3());
						 */
						footerTypeList.add(_profileFooterDtlVO);
					}
					if (url.equals("DISCHARGEPROFILEFOOTER"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpFooter = mapProfileOptions.get(key);
						remarks = (String) mpFooter.get("Remarks");
						reviewOn = (String) mpFooter.get("ReviewOn");
						dischargeType = (String) mpFooter.get("DichargeType");
						dischargeAdvisedBy = (String) mpFooter.get("DichargeAdvisedBy");
						dischargeAdvisedDept = (String) mpFooter.get("DichargeAdvisedDept");
						// _profileType=(String)mpFooter.get("ProfileType");
						disclaimerVO = (DisclaimerMstVO) mpFooter.get("Disclaimer");
						snomdcid=(String) mpFooter.get("SnomdCIdRemarks");
						snomdpt=(String) mpFooter.get("SnomdPTRemarks");


						_profileFooterDtlVO.setProfileSummary(remarks);
						_profileFooterDtlVO.setReviewDate(reviewOn);
						_profileFooterDtlVO.setDischargeType(dischargeType.split("#")[0]);
						//_profileFooterDtlVO.setDischargeAdvisedBy(dischargeAdvisedBy.split("#")[0]);
						_profileFooterDtlVO.setDischargeAdvisedDept(dischargeAdvisedDept.split("#")[0]);
						_profileFooterDtlVO.setSnomdCIdRemarks(snomdcid);
						_profileFooterDtlVO.setSnomdPTRemarks(snomdpt);
						

						if(disclaimerVO != null)
						{
						if(disclaimerVO.getDisclaimerId()!=null)
						_profileFooterDtlVO.setDisclaimerId(disclaimerVO.getDisclaimerId());
						if(disclaimerVO.getDisclaimerDesc1()!=null)
						_profileFooterDtlVO.setDisclaimerDesc1(disclaimerVO.getDisclaimerDesc1());
						if(disclaimerVO.getDisclaimerDesc2()!=null)
						_profileFooterDtlVO.setDisclaimerDesc2(disclaimerVO.getDisclaimerDesc2());
						if(disclaimerVO.getDisclaimerDesc3()!=null)
						_profileFooterDtlVO.setDisclaimerDesc3(disclaimerVO.getDisclaimerDesc3());
						}
						
						dischargefooterTypeList.add(_profileFooterDtlVO);
					}
					if (url.equals("ADVICEONDISCHARGE"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpDrugAdvice = mapProfileOptions.get(key);
						lstDrugAdvice = (List) mpDrugAdvice.get("lstPatientDrugAdviceVO");
						lstDietAdvice = (List) mpDrugAdvice.get("lstPatientDietAdviceVO");
						lstRestAdvice = (List) mpDrugAdvice.get("lstPatientRestAdviceVO");
						lstOtherAdvice = (List) mpDrugAdvice.get("lstPatientOtherAdviceVO");
						lstOtherInstructions = (List) mpDrugAdvice.get("lstPatientOtherInstructionsVO");
						lstOtherActivity = (List) mpDrugAdvice.get("lstPatientOtherActivityVO");
					}
						
					if (url.equals("GENERICTEMPLATE"))
					{
						// Getting Desk Menu Wise
						mpTempParaValues = (HashMap) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);
//						Map mpDeskMenuTempParaValues = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP); 
//						mpTempParaValues = (HashMap) mpDeskMenuTempParaValues.get(vo.getDeskMenuId());

						templateDeskMenuId = (String) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_TEMPLATE_DESK_ID);
						templateDeskMenuId = vo.getDeskMenuId();
						
						// Getting Desk Menu Wise
						mpTempParas = (HashMap) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
//						Map mpDeskMenuTempParas = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP); 
//						if(mpDeskMenuTempParas!=null)
//							mpTempParas = (HashMap) mpDeskMenuTempParas.get(vo.getDeskMenuId());
						
						// Getting Desk Menu Wise
						lstVisitDates = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES);
//						Map mpDeskMenuVisitDates = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES); 
//						lstVisitDates = (List<Entry>) mpDeskMenuVisitDates.get(vo.getDeskMenuId());

						// Getting Desk Menu Wise Report View Modes 
						mpDeskMenuReportMode = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP);
					}
					if (url.equals("OTVIEWING"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpOperation = mapProfileOptions.get(key);
						otTypeList = (List) mpOperation.get("lstOperationDetailVO");
					}

					if (url.equals("IMAGEEXAMINATION"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpImage = mapProfileOptions.get(key);
						imageTypeList = (List) mpImage.get("lstOpdPatientImageDtlVO");
					}

					if (url.equals("DOCTORROUND"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpProgressNotes = mapProfileOptions.get(key);
						lstEpiProgNotes = (List) mpProgressNotes.get("lstPatEpiProgressNotesDtlVO");
					}

					if (url.equals("EXTERNALEXAMINATION"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpExtInvestigation = mapProfileOptions.get(key);
						lstExtInvs = (List) mpExtInvestigation.get("lstPatientExtInvDtlVO");
					}
					
					
					//Vital Chart View
					
					if (url.equals("CHARTDETAIL"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpDiag = mapProfileOptions.get(key);
						chartViewList = (List) mpDiag.get("lstEpisodeChartViewDtlVO");
					}
					

				}

			}

			Map drugScheduleMap = (Map) session.getAttribute(OpdConfig.DRUG_SHEDULE_MAP);

			inAllMap.put("DESKDIAGNOSIS", diagnosisTypeList);
			inAllMap.put("DESKALLERGIES", allergyTypeList);
			inAllMap.put("RESULTVIEWING", investigationTypeList);
			inAllMap.put("DESKTREATMENTDETAIL", treatmentTypeList);
			inAllMap.put("GENERICPATIENTALERTS", alertsTypeList);
			inAllMap.put("PROFILEFOOTER", footerTypeList);
			inAllMap.put("DISCHARGEPROFILEFOOTER", dischargefooterTypeList);
			inAllMap.put("DRUGADVICE", lstDrugAdvice);
			inAllMap.put("DRUGSCHEDULE", drugScheduleMap);
			inAllMap.put("DIETADVICE", lstDietAdvice);
			inAllMap.put("RESTADVICE", lstRestAdvice);
			inAllMap.put("OTHERADVICE", lstOtherAdvice);
			inAllMap.put("OTHERINSTRUCTIONS", lstOtherInstructions);
			inAllMap.put("OTHERACTIVITY", lstOtherActivity);
			inAllMap.put("GENERICTEMPLATE", mpTempParaValues);
			inAllMap.put("GENERICTEMPLATE_DESKMENUID", templateDeskMenuId);
			inAllMap.put("PARACOMPLAINTS", mpTempParas);
			inAllMap.put("SELECTEDDATES", lstVisitDates);
			inAllMap.put("REPORTMODES", mpDeskMenuReportMode);
			inAllMap.put("OTVIEWING", otTypeList);
			inAllMap.put("IMAGEEXAMINATION", imageTypeList);
			inAllMap.put("DOCTORROUND", lstEpiProgNotes);
			inAllMap.put("EXTERNALEXAMINATION", lstExtInvs);
			inAllMap.put("CHARTDETAIL", chartViewList);

			// Previous Details For Desk Diagnosis
			ProfileDiagnosisDtlVO[] profileDiagnosisDtlVO = (ProfileDiagnosisDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DIAGNOSIS_DTL_VO_ARRAY);
			inAllPreviousMap.put("DESKDIAGNOSIS", profileDiagnosisDtlVO);

			// Previous Details For Desk Allergy
			ProfileAllergyDtlVO[] _profileAllergyDtlVO = (ProfileAllergyDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_ALLERGY_DTL_VO_ARRAY);
			inAllPreviousMap.put("DESKALLERGIES", _profileAllergyDtlVO);

			// Previous Details For Desk Investigation
			ProfileInvDtlVO[] _profileInvDtlVO = (ProfileInvDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_INVESTIGATION_DTL_VO_ARRAY);
			inAllPreviousMap.put("RESULTVIEWING", _profileInvDtlVO);

			// Previous Details For Desk Treatment
			ProfileDrugAdviceDtlVO[] _profileDrugAdviceDtlVO = (ProfileDrugAdviceDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_TREATMENT_DTL_VO_ARRAY);
			inAllPreviousMap.put("DESKTREATMENTDETAIL", _profileDrugAdviceDtlVO);

			// Previous Details For Patient Alerts
			ProfileAlertsDtlVO[] profileAlertsDtlVO = (ProfileAlertsDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_ALERTS_DTL_VO_ARRAY);
			inAllPreviousMap.put("GENERICPATIENTALERTS", profileAlertsDtlVO);

			// Previous Details For Profile Footer
			ProfileFooterDtlVO[] profileFooterDetail = (ProfileFooterDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_FOOTER_DTL_VO_ARRAY);
			inAllPreviousMap.put("PROFILEFOOTER", profileFooterDetail);

			// Previous Details For Discharge Profile Footer
			ProfileFooterDtlVO[] dischargeProfileFooterDetail = (ProfileFooterDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_FOOTER_DTL_VO_ARRAY);
			inAllPreviousMap.put("DISCHARGEPROFILEFOOTER", dischargeProfileFooterDetail);

			// Previous Details for Advice On Discharge
			ProfileDrugAdviceDtlVO[] profileDrugAdviceDtlVO = (ProfileDrugAdviceDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_DRUG_DTL_VO_ARRAY);
			inAllPreviousMap.put("DRUGADVICE", profileDrugAdviceDtlVO);
			ProfileDietAdviceDtlVO profileDietAdviceDtlVO = (ProfileDietAdviceDtlVO) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_DIET_ADVICE_DTL_VO);
			inAllPreviousMap.put("DIETADVICE", profileDietAdviceDtlVO);
			ProfileRestAdviceDtlVO profileRestAdviceDtlVO = (ProfileRestAdviceDtlVO) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_REST_ADVICE_DTL_VO);
			inAllPreviousMap.put("RESTADVICE", profileRestAdviceDtlVO);
			ProfileExtTreatmentDtlVO[] _profileExtTreatmentDtlVO = (ProfileExtTreatmentDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_EXT_TREATMENT_DTL_VO_ARRAY);
			inAllPreviousMap.put("OTHERADVICE", _profileExtTreatmentDtlVO);
			ProfileExtTreatmentDtlVO[] profileExtTreatmentDtlVO = (ProfileExtTreatmentDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_OTHER_INSTRUCTIONS_DTL_VO_ARRAY);
			inAllPreviousMap.put("OTHERINSTRUCTIONS", profileExtTreatmentDtlVO);
			ProfileExtTreatmentDtlVO[] profileExtTreatmentDtlVOs = (ProfileExtTreatmentDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_OTHER_ACTIVITY_DTL_VO_ARRAY);
			inAllPreviousMap.put("OTHERACTIVITY", profileExtTreatmentDtlVOs);
			List profileDrugScheduleDtlVOList = (List) session.getAttribute(OpdConfig.PATIENT_PROFILE_DRUG_SCHEDULE_DTL_VO_LIST);
			inAllPreviousMap.put("DRUGSCHEDULE", profileDrugScheduleDtlVOList);

			// Previous Details For Profile Clinical
			ProfileClinicalDtlVO[] profileClinicalDetailVO = (ProfileClinicalDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_COMPLAINTS_DTL_VO_ARRAY);
			inAllPreviousMap.put("GENERICTEMPLATE", profileClinicalDetailVO);

			// to get the desk menu id in case of complaints
			UserDeskMenuTemplateMasterVO userDeskMenuTemplateMasterVO = (UserDeskMenuTemplateMasterVO) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_USER_DESK_MENU_TEMPLATE_MASTER_VO);

			// Previous Details For Desk OT
			ProfileOTDtlVO[] profileOTDtlVOs = (ProfileOTDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_OT_DTL_VO_ARRAY);
			inAllPreviousMap.put("OTVIEWING", profileOTDtlVOs);

			// Previous Details for Profile Image
			ProfileImageDtlVO[] profileImageDtlVOs = (ProfileImageDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_IMAGE_DTL_VO_ARRAY);
			inAllPreviousMap.put("IMAGEEXAMINATION", profileImageDtlVOs);

			// Previous Details for Profile Progress Notes
			ProfileProgressNotesDtlVO[] profileEpiProgNtesDtlVOs = (ProfileProgressNotesDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROGRESS_NOTES_DTL_VO_ARRAY);
			inAllPreviousMap.put("DOCTORROUND", profileEpiProgNtesDtlVOs);

			// Previous Details for Profile External Investigation
			ProfileExtExamDtlVO[] profileExtInvDtlVOs = (ProfileExtExamDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_EXT_INV_DTL_VO_ARRAY);
			inAllPreviousMap.put("EXTERNALEXAMINATION", profileExtInvDtlVOs);

			
			
			// Previous Details For CHART VIEW DETAILS
			 List<ProfileChartViewDtlVO> profileChartViewDtlVO=(List<ProfileChartViewDtlVO>) session.getAttribute(OpdConfig.PATIENT_PROFILE_CHART_VIEW_DTL_VO_ARRAY);
			inAllPreviousMap.put("CHARTDETAIL", profileChartViewDtlVO);

			
			patProfileDtlVO = new PatientProfileDetailVO();
			HelperMethods.populate(patProfileDtlVO, _fb);
			patProfileDtlVO.setProfileType(proforma.getProfileType());
			patProfileDtlVO.setProfileStatus(OpdConfig.HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_INPROCESS);						
			// patProfileDtlVO.setProfileData(resultHtml);
			GenericPatientProfileDATA.savePatientProfile(patProfileDtlVO, inAllMap, inAllPreviousMap, userDeskMenuTemplateMasterVO, userVO);
			objStatus.add(Status.NEW, "", "Profile Saved Successfully");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Profile Design Utility Class
	public static class ProfileProforma
	{
		public Map<String, Object> mapProfileHeader;
		public Map<String, Map<String, Object>> mapProfileOptions;
		public Map<String, String> mapOptionURLs;

		public StringBuilder profileHtmlCode;
		public StringBuilder profileHeader;
		// public StringBuilder profileFooter;
		public String patientTitle;
		private Map<String, StringBuilder> mpOptionsCode;
		private List<String> lstMenuOrder;
		private String profileType;
		private String profileTypeDesc;
		private String genrationType;
		private String genrationTypeDesc;

		public ProfileProforma()
		{
			this.mapProfileHeader = new HashMap<String, Object>();
			this.mapProfileOptions = new HashMap<String, Map<String, Object>>();
			this.mapOptionURLs = new HashMap<String, String>();

			this.profileHtmlCode = new StringBuilder("");
			this.profileHeader = new StringBuilder("");
			// this.profileFooter=new StringBuilder("");
			this.patientTitle = "";
			this.mpOptionsCode = new HashMap<String, StringBuilder>();
			this.lstMenuOrder = new ArrayList<String>();
		}

		public void setOptionsURLMap(Map<String, String> mp)
		{
			this.mapOptionURLs = mp;
		}

		public String getFinalProfileHtmlCode()
		{
			this.profileHtmlCode.append("<html><head><title>Profile of Patient " + this.patientTitle + "</title></head><body>");
			this.profileHtmlCode.append(this.profileHeader);
			for (String key : this.lstMenuOrder)
				this.profileHtmlCode.append(this.mpOptionsCode.get(key));
			// this.profileHtmlCode.append(this.profileFooter);
			this.profileHtmlCode.append("</body></html>");
			return this.profileHtmlCode.toString();
		}

		public String getProfileType()
		{
			return profileType;
		}

		public void setProfileType(String profileType)
		{
			this.profileType = profileType;
		}

		public String getProfileTypeDesc()
		{
			return profileTypeDesc;
		}

		public void setProfileTypeDesc(String profileTypeDesc)
		{
			this.profileTypeDesc = profileTypeDesc;
		}

		public String getGenrationType()
		{
			return genrationType;
		}

		public void setGenrationType(String genrationType)
		{
			this.genrationType = genrationType;
		}

		public String getGenrationTypeDesc()
		{
			return genrationTypeDesc;
		}

		public void setGenrationTypeDesc(String genrationTypeDesc)
		{
			this.genrationTypeDesc = genrationTypeDesc;
		}

		public Map<String, StringBuilder> getAddedOptionsMap()
		{
			return this.mpOptionsCode;
		}

		public List<String> getOptionsOrderList()
		{
			return this.lstMenuOrder;
		}

		public void moveMenuUp(String _deskMenuId)
		{
			int index = this.lstMenuOrder.indexOf(_deskMenuId);
			this.lstMenuOrder.remove(_deskMenuId);
			this.lstMenuOrder.add(index - 1, _deskMenuId);
		}

		public void moveMenuDown(String _deskMenuId)
		{
			int index = this.lstMenuOrder.indexOf(_deskMenuId);
			this.lstMenuOrder.remove(_deskMenuId);
			this.lstMenuOrder.add(index + 1, _deskMenuId);
		}

		public void removeMenu(String _deskMenuId)
		{
			this.lstMenuOrder.remove(_deskMenuId);
			this.mpOptionsCode.remove(_deskMenuId);
			this.mapProfileOptions.remove(_deskMenuId);
		}

		public void setHeader(String _header, String _remark, PatientDetailVO _patDtlVO, UserVO _userVO)
		{
			_patDtlVO.setPatientName();
			this.patientTitle = _patDtlVO.getPatName() + "(" + _patDtlVO.getPatCrNo() + ")";

			this.mapProfileHeader.put("Header", _header);
			this.mapProfileHeader.put("PatientDetailVO", setNullToEmpty(_patDtlVO));

			// Header
			this.profileHeader.append("<center><b><h1>");
			this.profileHeader.append(_header);
			this.profileHeader.append("<h1></b></center>");
			this.profileHeader.append("<br><center><b>Patient CrNo. :: ");
			this.profileHeader.append(_patDtlVO.getPatCrNo());
			this.profileHeader.append("</b></center><br>");

			/*
			 * // Footer this.profileFooter.append("<br><br>"); this.profileFooter.append("<b>Profile Remarks :
			 * </b>&nbsp;"); this.profileFooter.append("<p>"+_remark+"</p>");
			 */
		}

		public void setDiagnosisProforma(String _deskMenuId, EpisodeDiagnosisVO[] _diagDetail, String[] _selRows)
		{
			if (this.mpOptionsCode.get(_deskMenuId) == null)
			{
				this.lstMenuOrder.add(_deskMenuId);
			}
			StringBuilder sb = new StringBuilder("");

			// Menu Header
			sb.append("<table>");
			sb.append("<tr align='left'>");
			sb.append("<td>");
			sb.append("<h3>");
			sb.append("DIAGNOSIS DETAIL");
			sb.append("</h3>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			// Data Table Head
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td width='20%' align='center'>");
			sb.append("<b>");
			sb.append("Visit Date");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("<td width='20%' align='center'>");
			sb.append("<b>");
			sb.append("Diagnostic Code");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("<td width='20%' align='center'>");
			sb.append("<b>");
			sb.append("Dignosis Name");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("<td width='20%' align='center'>");
			sb.append("<b>");
			sb.append("Diagnostic Type Name");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("<td width='20%' align='center'>");
			sb.append("<b>");
			sb.append("Remarks");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("</tr>");
			for (int i = 0; i < _selRows.length; i++)
			{
				EpisodeDiagnosisVO vo = _diagDetail[Integer.parseInt(_selRows[i])];
				sb.append("<tr>");
				sb.append("<td width='20%' align='center'>");
				sb.append(vo.getEpisodeDate());
				sb.append("</td>");
				sb.append("<td width='20%' align='center'>");
				sb.append(vo.getDiagnosticCode());
				sb.append("</td>");
				sb.append("<td width='20%' align='center'>");
				sb.append(vo.getDignosisName());
				sb.append("</td>");
				sb.append("<td width='20%' align='center'>");
				sb.append(vo.getDiagnosticTypeName());
				sb.append("</td>");
				sb.append("<td width='20%' align='center'>");
				sb.append(vo.getRemarks());
				sb.append("</td>");
				sb.append("</tr>");
			}
			sb.append("</table>");

			this.mpOptionsCode.put(_deskMenuId, sb);

			List<EpisodeDiagnosisVO> lstSelected = new ArrayList<EpisodeDiagnosisVO>();
			Map<String, Object> mpDiag = new HashMap<String, Object>();
			for (int i = 0; i < _selRows.length; i++)
				lstSelected.add((EpisodeDiagnosisVO) setNullToEmpty(_diagDetail[Integer.parseInt(_selRows[i])]));
			mpDiag.put("lstEpisodeDiagnosisVO", lstSelected);

			this.mapProfileOptions.put(_deskMenuId, mpDiag);
		}
		

		public void setChartViewDtlProforma(String _deskMenuId, List<ProfileChartViewDtlVO> _lstChartViewDetail)
		{
			if (this.mpOptionsCode.get(_deskMenuId) == null)
			{
				this.lstMenuOrder.add(_deskMenuId);
			}
			List<ProfileChartViewDtlVO> lstSelected = _lstChartViewDetail;
			Map<String, Object> mpChart = new HashMap<String, Object>();
			mpChart.put("lstEpisodeChartViewDtlVO", lstSelected);
			this.mapProfileOptions.put(_deskMenuId, mpChart);
		}
		
		
		public void setAllergiesProforma(String _deskMenuId, PatAllergyDtlVO[] _allergyDetail, String[] _selRows)
		{
			if (this.mpOptionsCode.get(_deskMenuId) == null)
			{
				this.lstMenuOrder.add(_deskMenuId);
			}
			StringBuilder sb = new StringBuilder("");

			// Menu Header
			sb.append("<table>");
			sb.append("<tr align='left'>");
			sb.append("<td>");
			sb.append("<h3>");
			sb.append("ALLERGIES DETAIL");
			sb.append("</h3>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			// Data Table Head
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td width='5%' align='center'>");
			sb.append("<b>");
			sb.append("Duration Date");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("<td width='10%' align='center'>");
			sb.append("<b>");
			sb.append("Allery Name");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("<td width='10%' align='center'>");
			sb.append("<b>");
			sb.append("Allergy Type");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("</tr>");
			for (int i = 0; i < _selRows.length; i++)
			{
				PatAllergyDtlVO vo = _allergyDetail[Integer.parseInt(_selRows[i])];
				sb.append("<tr>");
				sb.append("<td width='5%' align='center'>");
				sb.append(vo.getDuration());
				sb.append("</td>");
				sb.append("<td width='10%' align='center'>");
				sb.append(vo.getAllergyName());
				sb.append("</td>");
				sb.append("<td width='10%' align='center'>");
				sb.append(vo.getAllergyTypeName());
				sb.append("</td>");
				sb.append("</tr>");
			}
			sb.append("</table>");

			this.mpOptionsCode.put(_deskMenuId, sb);

			List<PatAllergyDtlVO> lstSelected = new ArrayList<PatAllergyDtlVO>();
			Map<String, Object> mpAllerg = new HashMap<String, Object>();
			for (int i = 0; i < _selRows.length; i++)
				lstSelected.add((PatAllergyDtlVO) setNullToEmpty(_allergyDetail[Integer.parseInt(_selRows[i])]));
			mpAllerg.put("lstEpisodeAllergiesVO", lstSelected);

			this.mapProfileOptions.put(_deskMenuId, mpAllerg);
		}

		public void setExamImagesProforma(String _deskMenuId, OpdPatientImageDtlVO[] _examImageDetail, String[] _selRows)
		{
			if (this.mpOptionsCode.get(_deskMenuId) == null)
			{
				this.lstMenuOrder.add(_deskMenuId);
			}
			StringBuilder sb = new StringBuilder("");

			// Menu Header
			sb.append("<table>");
			sb.append("<tr align='left'>");
			sb.append("<td>");
			sb.append("<h3>");
			sb.append("IMAGE EXAM DETAIL");
			sb.append("</h3>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			// Data Table Head
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td width='20%' align='center'>");
			sb.append("<b>");
			sb.append("Date");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("<td width='30%' align='center'>");
			sb.append("<b>");
			sb.append("Image Name");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("<td width='50%' align='center'>");
			sb.append("<b>");
			sb.append("Remarks");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("</tr>");
			for (int i = 0; i < _selRows.length; i++)
			{
				OpdPatientImageDtlVO vo = _examImageDetail[Integer.parseInt(_selRows[i])];
				sb.append("<tr>");
				sb.append("<td width='20%' align='center'>");
				sb.append(vo.getEntryDate());
				sb.append("</td>");
				sb.append("<td width='30%' align='center'>");
				sb.append(vo.getImageName());
				sb.append("</td>");
				sb.append("<td width='50%' align='center'>");
				sb.append(vo.getRemarks());
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td width='100%' align='center' colspan='3'>");

				String path = ServletsUtilityConfig.SERVLET_DISPLAY_FILE_URL + "?" + ServletsUtilityConfig.FILE_PATH_WINDOWS + "="
						+ Config.IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_WINDOWS + "&" + ServletsUtilityConfig.FILE_PATH_LINUX + "="
						+ Config.IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_LINUX + "&" + ServletsUtilityConfig.FILE_NAME + "="
						+ vo.getImageFileName();

				sb.append("<img alt='");
				sb.append(vo.getImageName());
				sb.append("' src='");
				sb.append(path);
				sb.append("' />");
				sb.append("</td>");
				sb.append("</tr>");
			}
			sb.append("</table>");

			this.mpOptionsCode.put(_deskMenuId, sb);

			List<OpdPatientImageDtlVO> lstSelected = new ArrayList<OpdPatientImageDtlVO>();
			Map<String, Object> mpImage = new HashMap<String, Object>();
			for (int i = 0; i < _selRows.length; i++)
				lstSelected.add((OpdPatientImageDtlVO) setNullToEmpty(_examImageDetail[Integer.parseInt(_selRows[i])]));
			mpImage.put("lstOpdPatientImageDtlVO", lstSelected);

			this.mapProfileOptions.put(_deskMenuId, mpImage);
		}

		public void setNextVisitDetailProforma(String _deskMenuId, EpisodeVO _epiVO, String[] _selRows)
		{
			if (this.mpOptionsCode.get(_deskMenuId) == null)
			{
				this.lstMenuOrder.add(_deskMenuId);
			}
			StringBuilder sb = new StringBuilder("");

			// Menu Header
			sb.append("<table>");
			sb.append("<tr align='left'>");
			sb.append("<td>");
			sb.append("<h3>");
			sb.append("NEXT VISIT DETAIL DETAIL");
			sb.append("</h3>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			// Data Table Head
			sb.append("<table width='100%'>");
			if (_epiVO.getEpisodeNextVisitDate() != null && !_epiVO.getEpisodeNextVisitDate().equals(""))
			{
				sb.append("<tr>");
				sb.append("<td width='20%' align='left'>");
				sb.append("<b>");
				sb.append("Next Visit Date ");
				sb.append("</b>");
				sb.append("</td>");
				sb.append("<td width='80%' align='left'>");
				sb.append("<b>");
				sb.append(_epiVO.getEpisodeNextVisitDate());
				sb.append("</b>");
				sb.append("</td>");
				sb.append("<tr>");
			}

			if (_epiVO.getComplainDetail() != null && !_epiVO.getComplainDetail().equals(""))
			{
				sb.append("</tr>");
				sb.append("<td width='30%' align='left'>");
				sb.append("<b>");
				sb.append("Visit Summary ");
				sb.append("</b>");
				sb.append("</td>");
				sb.append("<td width='80%' align='left'>");
				sb.append(_epiVO.getComplainDetail());
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}

			this.mpOptionsCode.put(_deskMenuId, sb);

			Map<String, Object> mpNextVisit = new HashMap<String, Object>();
			mpNextVisit.put("EpisodeVO", (EpisodeVO) setNullToEmpty(_epiVO));
			this.mapProfileOptions.put(_deskMenuId, mpNextVisit);
		}

		public void setInvestigationProforma(String _deskMenuId, ProfileInvestigationVO[] _invDetail, String[] _selRows)
		{
			if (this.mpOptionsCode.get(_deskMenuId) == null)
			{
				this.lstMenuOrder.add(_deskMenuId);
			}
			StringBuilder sb = new StringBuilder("");

			// Menu Header
			sb.append("<table>");
			sb.append("<tr align='left'>");
			sb.append("<td>");
			sb.append("<h3>");
			sb.append("INVESTIGATION DETAIL");
			sb.append("</h3>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			// Data Table Head
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td width='20%' align='center'>");
			sb.append("<b>");
			sb.append("Visit Date");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("<td width='20%' align='center'>");
			sb.append("<b>");
			sb.append("Test Name");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("<td width='20%' align='center'>");
			sb.append("<b>");
			sb.append("Result Status");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("</tr>");
			for (int i = 0; i < _selRows.length; i++)
			{
				ProfileInvestigationVO vo = _invDetail[Integer.parseInt(_selRows[i])];
				sb.append("<tr>");
				sb.append("<td width='20%' align='center'>");
				sb.append(vo.getVisitDate());
				sb.append("</td>");
				sb.append("<td width='20%' align='center'>");
				sb.append(vo.getTestName());
				sb.append("</td>");
				sb.append("<td width='20%' align='center'>");
				sb.append(vo.getResultStatus());
				sb.append("</td>");
				sb.append("</tr>");
			}
			sb.append("</table>");

			this.mpOptionsCode.put(_deskMenuId, sb);

			List<ProfileInvestigationVO> lstSelected = new ArrayList<ProfileInvestigationVO>();
			Map<String, Object> mpInv = new HashMap<String, Object>();
			for (int i = 0; i < _selRows.length; i++)
				lstSelected.add((ProfileInvestigationVO) setNullToEmpty(_invDetail[Integer.parseInt(_selRows[i])]));
			mpInv.put("lstEpisodeInvestigationVO", lstSelected);

			this.mapProfileOptions.put(_deskMenuId, mpInv);
		}

		public void setTreatmentProforma(String _deskMenuId, PatDrugTreatmentDetailVO[] _treatmentDetail, String[] _selRows)
		{
			if (this.mpOptionsCode.get(_deskMenuId) == null)
			{
				this.lstMenuOrder.add(_deskMenuId);
			}
			StringBuilder sb = new StringBuilder("");

			// Menu Header
			sb.append("<table>");
			sb.append("<tr align='left'>");
			sb.append("<td>");
			sb.append("<h3>");
			sb.append("TREATMENT DETAIL");
			sb.append("</h3>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			// Data Table Head
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td width='20%' align='center'>");
			sb.append("<b>");
			sb.append("Drug Name");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("<td width='20%' align='center'>");
			sb.append("<b>");
			sb.append("Dosage");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("<td width='20%' align='center'>");
			sb.append("<b>");
			sb.append("Frequency");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("<td width='20%' align='center'>");
			sb.append("<b>");
			sb.append("Start Date");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("<td width='20%' align='center'>");
			sb.append("<b>");
			sb.append("End Date");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("</tr>");
			for (int i = 0; i < _selRows.length; i++)
			{
				PatDrugTreatmentDetailVO vo = _treatmentDetail[Integer.parseInt(_selRows[i])];
				sb.append("<tr>");
				sb.append("<td width='20%' align='center'>");
				sb.append(vo.getDrugName());
				sb.append("</td>");
				sb.append("<td width='20%' align='center'>");
				sb.append(vo.getDoseName());
				sb.append("</td>");
				sb.append("<td width='20%' align='center'>");
				sb.append(vo.getFrequencyName());
				sb.append("</td>");
				sb.append("<td width='20%' align='center'>");
				sb.append(vo.getStartDate());
				sb.append("</td>");
				sb.append("<td width='20%' align='center'>");
				sb.append(vo.getEndDate());
				sb.append("</td>");
				sb.append("</tr>");
			}
			sb.append("</table>");

			this.mpOptionsCode.put(_deskMenuId, sb);

			List<PatDrugTreatmentDetailVO> lstSelected = new ArrayList<PatDrugTreatmentDetailVO>();
			Map<String, Object> mpTreat = new HashMap<String, Object>();
			for (int i = 0; i < _selRows.length; i++)
				lstSelected.add((PatDrugTreatmentDetailVO) setNullToEmpty(_treatmentDetail[Integer.parseInt(_selRows[i])]));
			mpTreat.put("lstEpisodeTreatmentVO", lstSelected);

			this.mapProfileOptions.put(_deskMenuId, mpTreat);
		}

		public void setAlertsProforma(String _deskMenuId, PatientAlertsDetailVO[] _alertsDetail, String[] _selRows)
		{
			if (this.mpOptionsCode.get(_deskMenuId) == null)
			{
				this.lstMenuOrder.add(_deskMenuId);
			}
			StringBuilder sb = new StringBuilder("");

			// Menu Header
			sb.append("<table>");
			sb.append("<tr align='left'>");
			sb.append("<td>");
			sb.append("<h3>");
			sb.append("PATIENT CHRONIC DISEASE");
			sb.append("</h3>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			// Data Table Head
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td width='5%' align='center'>");
			sb.append("<b>");
			sb.append("Chronic Disease");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("<td width='10%' align='center'>");
			sb.append("<b>");
			sb.append("Duration");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("<td width='10%' align='center'>");
			sb.append("<b>");
			sb.append("Remarks");
			sb.append("</b>");
			sb.append("</td>");
			sb.append("</tr>");
			for (int i = 0; i < _selRows.length; i++)
			{
				PatientAlertsDetailVO vo = _alertsDetail[Integer.parseInt(_selRows[i])];
				sb.append("<tr>");
				sb.append("<td width='5%' align='center'>");
				sb.append(vo.getAlertName());
				sb.append("</td>");
				sb.append("<td width='10%' align='center'>");
				sb.append(vo.getDurationDays());
				sb.append("</td>");
				sb.append("<td width='10%' align='center'>");
				sb.append(vo.getRemarks());
				sb.append("</td>");
				sb.append("</tr>");
			}
			sb.append("</table>");

			this.mpOptionsCode.put(_deskMenuId, sb);

			List<PatientAlertsDetailVO> lstSelected = new ArrayList<PatientAlertsDetailVO>();
			Map<String, Object> mpAlerts = new HashMap<String, Object>();
			for (int i = 0; i < _selRows.length; i++)
				lstSelected.add((PatientAlertsDetailVO) setNullToEmpty(_alertsDetail[Integer.parseInt(_selRows[i])]));
			mpAlerts.put("lstPatientAlertsVO", lstSelected);

			this.mapProfileOptions.put(_deskMenuId, mpAlerts);
		}

		public void setProfileFooterDetailProforma(String _deskMenuId, String _remarks, String _reviewDate, String _dischargeType, 
				String _dischargeTypeName, String _dischargeAdvisedBy, String _dischargeAdvisedByName, String _dischargeAdvisedDept, 
				String _dischargeAdvisedDeptName, String _profileType, DisclaimerMstVO disclaimerMstVO, String _snomdCId, String _snomd_PT)
		{
			/*
			 * if(this.mpOptionsCode.get(_deskMenuId)==null) { this.lstMenuOrder.add(_deskMenuId); }
			 */
			if (!this.lstMenuOrder.contains(_deskMenuId)) this.lstMenuOrder.add(_deskMenuId);
			// this.lstMenuOrder.add(_deskMenuId);
			Map<String, Object> mpFooter = new HashMap<String, Object>();
			mpFooter.put("Remarks", _remarks);
			mpFooter.put("ReviewOn", _reviewDate);
			mpFooter.put("DichargeType", _dischargeType + "#" + _dischargeTypeName);
			//mpFooter.put("DichargeAdvisedBy", _dischargeAdvisedBy + "#" + _dischargeAdvisedByName);
			mpFooter.put("DichargeAdvisedBy", _dischargeAdvisedBy);
			mpFooter.put("DichargeAdvisedDept", _dischargeAdvisedDept + "#" + _dischargeAdvisedDeptName);
			mpFooter.put("ProfileType", _profileType);
			mpFooter.put("Disclaimer", disclaimerMstVO);
			mpFooter.put("SnomdCIdRemarks", _snomdCId);
			mpFooter.put("SnomdPTRemarks", _snomd_PT);
		
			
			this.mapProfileOptions.put(_deskMenuId, mpFooter);
		}
		
		
		

		public void setComplaintsProforma(String _deskMenuId)
		{
			/*
			 * if(this.mpOptionsCode.get(_deskMenuId)==null) { this.lstMenuOrder.add(_deskMenuId); }
			 */
			if (!this.lstMenuOrder.contains(_deskMenuId)) this.lstMenuOrder.add(_deskMenuId);
			Map<String, Object> mpComplaints = new HashMap<String, Object>();
			
			this.mapProfileOptions.put(_deskMenuId, mpComplaints);
		}
		
		public void setProgressNotesDetailProforma(String _deskMenuId, DoctorRoundDtlVO[] _progressDetail, String[] _selRows)
		{

			if (!this.lstMenuOrder.contains(_deskMenuId)) this.lstMenuOrder.add(_deskMenuId);
			// this.lstMenuOrder.add(_deskMenuId);

			List<DoctorRoundDtlVO> lstSelected = new ArrayList<DoctorRoundDtlVO>();
			Map<String, Object> mpProgress = new HashMap<String, Object>();
			for (int i = 0; i < _selRows.length; i++)
				lstSelected.add((DoctorRoundDtlVO) setNullToEmpty(_progressDetail[Integer.parseInt(_selRows[i])]));
			mpProgress.put("lstProgressNotesVO", lstSelected);

			this.mapProfileOptions.put(_deskMenuId, mpProgress);

		}

		public void setOperationDetailProforma(String _deskMenuId, ProfileOTDetailVO[] _profileOTDetailVO, String[] _selRows)
		{

			if (!this.lstMenuOrder.contains(_deskMenuId)) this.lstMenuOrder.add(_deskMenuId);
			// this.lstMenuOrder.add(_deskMenuId);

			List<ProfileOTDetailVO> lstSelected = new ArrayList<ProfileOTDetailVO>();
			Map<String, Object> mpOperation = new HashMap<String, Object>();
			for (int i = 0; i < _selRows.length; i++)
				lstSelected.add((ProfileOTDetailVO) setNullToEmpty(_profileOTDetailVO[Integer.parseInt(_selRows[i])]));
			mpOperation.put("lstOperationDetailVO", lstSelected);

			this.mapProfileOptions.put(_deskMenuId, mpOperation);

		}

		public void setEpiProgressNotesDetailProforma(String _deskMenuId, DoctorRoundDtlVO[] _progressDetail, String[] _selRows)
		{

			if (!this.lstMenuOrder.contains(_deskMenuId)) this.lstMenuOrder.add(_deskMenuId);
			// this.lstMenuOrder.add(_deskMenuId);

			List<DoctorRoundDtlVO> lstSelected = new ArrayList<DoctorRoundDtlVO>();
			Map<String, Object> mpProgress = new HashMap<String, Object>();
			for (int i = 0; i < _selRows.length; i++)
				lstSelected.add((DoctorRoundDtlVO) setNullToEmpty(_progressDetail[Integer.parseInt(_selRows[i])]));
			mpProgress.put("lstPatEpiProgressNotesDtlVO", lstSelected);

			this.mapProfileOptions.put(_deskMenuId, mpProgress);

		}

		public void setExtInvestigationProforma(String _deskMenuId, EpisodeExtInvDtlVO[] _extInvDetail, String[] _selRows)
		{
			if (!this.lstMenuOrder.contains(_deskMenuId)) this.lstMenuOrder.add(_deskMenuId);
			//if (this.mpOptionsCode.get(_deskMenuId) == null) this.lstMenuOrder.add(_deskMenuId);
			//this.mpOptionsCode.put(_deskMenuId, sb);

			List<EpisodeExtInvDtlVO> lstSelected = new ArrayList<EpisodeExtInvDtlVO>();
			Map<String, Object> mpDiag = new HashMap<String, Object>();
			for (int i = 0; i < _selRows.length; i++)
				lstSelected.add((EpisodeExtInvDtlVO) setNullToEmpty(_extInvDetail[Integer.parseInt(_selRows[i])]));
			mpDiag.put("lstPatientExtInvDtlVO", lstSelected);

			this.mapProfileOptions.put(_deskMenuId, mpDiag);
		}

		public void setAdviceOnDischargeProforma(String _deskMenuId, PatDrugTreatmentDetailVO[] drugVO, PatDietAdviceDetailVO _patDietDetailVO,
				RestAdviceDtlVO restAdviceDtlVO, PatExtTreatmentDetailVO[] extVO, List selOtherInstList, List selActivityList)
		{
			/*
			 * if(this.mpOptionsCode.get(_deskMenuId)==null) { this.lstMenuOrder.add(_deskMenuId); }
			 */
			if (!this.lstMenuOrder.contains(_deskMenuId)) this.lstMenuOrder.add(_deskMenuId);
			// this.lstMenuOrder.add(_deskMenuId);

			// Drug Advice
			List<PatDrugTreatmentDetailVO> lstDrugAdvice = new ArrayList<PatDrugTreatmentDetailVO>();
			Map<String, Object> mpAdviceOnDischarge = new HashMap<String, Object>();
			for (int i = 0; i < drugVO.length; i++)
			{
				if (drugVO[i] != null)
				{
					if (!drugVO[i].getDrugName().equals("")) lstDrugAdvice.add((PatDrugTreatmentDetailVO) setNullToEmpty(drugVO[i]));
				}
			}
			mpAdviceOnDischarge.put("lstPatientDrugAdviceVO", lstDrugAdvice);
			this.mapProfileOptions.put(_deskMenuId, mpAdviceOnDischarge);
			// Diet Advice
			if (_patDietDetailVO != null)
			{
				List<PatDietAdviceDetailVO> lstDietAdvice = new ArrayList<PatDietAdviceDetailVO>();
				lstDietAdvice.add((PatDietAdviceDetailVO) setNullToEmpty(_patDietDetailVO));
				mpAdviceOnDischarge.put("lstPatientDietAdviceVO", lstDietAdvice);
				this.mapProfileOptions.put(_deskMenuId, mpAdviceOnDischarge);
			}
			else
			{
				mpAdviceOnDischarge.put("lstPatientDietAdviceVO", new ArrayList<PatDietAdviceDetailVO>());
				this.mapProfileOptions.put(_deskMenuId, mpAdviceOnDischarge);
			}
			// /// Rest Advice
			if (restAdviceDtlVO != null)
			{
				List<RestAdviceDtlVO> lstRestAdvice = new ArrayList<RestAdviceDtlVO>();
				lstRestAdvice.add((RestAdviceDtlVO) setNullToEmpty(restAdviceDtlVO));
				mpAdviceOnDischarge.put("lstPatientRestAdviceVO", lstRestAdvice);
				this.mapProfileOptions.put(_deskMenuId, mpAdviceOnDischarge);
			}
			else
			{
				mpAdviceOnDischarge.put("lstPatientRestAdviceVO", new ArrayList<RestAdviceDtlVO>());
				this.mapProfileOptions.put(_deskMenuId, mpAdviceOnDischarge);
			}

			// //Other Advice
			List<PatExtTreatmentDetailVO> lstOtherAdvice = new ArrayList<PatExtTreatmentDetailVO>();
			for (int i = 0; i < extVO.length; i++)
			{
				if (!extVO[i].getExtTreatmentName().equals("")) lstOtherAdvice.add((PatExtTreatmentDetailVO) setNullToEmpty(extVO[i]));
			}
			mpAdviceOnDischarge.put("lstPatientOtherAdviceVO", lstOtherAdvice);
			this.mapProfileOptions.put(_deskMenuId, mpAdviceOnDischarge);

			// Other Instructions and Activities
			if (selOtherInstList != null)
			{
				mpAdviceOnDischarge.put("lstPatientOtherInstructionsVO", selOtherInstList);
				this.mapProfileOptions.put(_deskMenuId, mpAdviceOnDischarge);
			}
			else
			{
				mpAdviceOnDischarge.put("lstPatientOtherInstructionsVO", new ArrayList());
				this.mapProfileOptions.put(_deskMenuId, mpAdviceOnDischarge);
			}

			if (selActivityList != null)
			{
				mpAdviceOnDischarge.put("lstPatientOtherActivityVO", selActivityList);
				this.mapProfileOptions.put(_deskMenuId, mpAdviceOnDischarge);
			}
			else
			{
				mpAdviceOnDischarge.put("lstPatientOtherActivityVO", new ArrayList());
				this.mapProfileOptions.put(_deskMenuId, mpAdviceOnDischarge);
			}
		}

		public Map<String, Map<String, Object>> getMapProfileOptions()
		{
			return mapProfileOptions;
		}

		public void setMapProfileOptions(Map<String, Map<String, Object>> mapProfileOptions)
		{
			this.mapProfileOptions = mapProfileOptions;
		}
	}

	public static Object setNullToEmpty(Object obj)
	{
		Class clsObject = obj.getClass();
		Method[] clsMethods = clsObject.getMethods();
		HashMap<String, Integer> mpSettersInClass = new HashMap<String, Integer>();

		for (int i = 0; i < clsMethods.length; i++)
			if (clsMethods[i].getName().indexOf("set") == 0) mpSettersInClass.put(clsMethods[i].getName().substring(3), i);

		try
		{
			for (int i = 0; i < clsMethods.length; i++)
			{
				if (clsMethods[i].getName().indexOf("get") == 0)
				{
					Object str = clsMethods[i].invoke(obj);
					if (str == null && clsMethods[i].getReturnType().equals(String.class))
					{
						if (mpSettersInClass.containsKey(clsMethods[i].getName().substring(3)))
						{
							int idx = mpSettersInClass.get(clsMethods[i].getName().substring(3));
							clsMethods[idx].invoke(obj, new Object[]
							{ "" });
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisException("GenericPatientProfileUTIL.setNullToEmpty::" + e);
		}
		return obj;
	}

	public static void fetchProfileDetails(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		// List<PatientProfileDetailVO> lstPrevProfiles = null;
		List<DeskMenuMasterVO> lstProMenus = null;
		// PatientProfileDetailVO patProfileDtlVO = null;
		Map essentialMap = null;
		List lstDietType = null;
		List lstDrugRoutes = null;
		List profileOTList = new ArrayList();
		Map templateDetailsMap = null;
		List templateList = null;
		List paraIdValueList = null;
		List templateListAll = null;
		List paraIdValueListAll = null;
		List mapAllList = new ArrayList();
		List<Entry> lstReportDates = new ArrayList<Entry>();
		List<Entry> lstSelectedTemps = new ArrayList<Entry>();
		List<Entry> lstVisitDates = new ArrayList<Entry>();
		List<Entry> lstTemps = null;
//		List<Entry> lstActiveTemps = null;
//		List<Entry> lstInactiveTemps = null;
		Map<String, TemplateMasterVO> mapTemplateDtl = null;
		List<TemplateParameterMasterVO> lstAllTempParas = null;
		LinkedHashMap<String, LinkedHashMap<String, String>> mapAllTempParas = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		LinkedHashMap<String, LinkedHashMap<String, TemplateParameterMasterVO>> mapAllValuedTempParas = new LinkedHashMap<String, LinkedHashMap<String, TemplateParameterMasterVO>>();
		// String profileBound="";
		String remarks = "";
		String reviewDate = "" , dischargeType="", dischargeAdvisedBy="", dischargeAdvisedDept="";
		String profileType = "";
		DisclaimerMstVO _disclaimerMstVOs = new DisclaimerMstVO();
		PatientDetailVO[] dailyPatientVOs = null;
		try
		{
			UserVO userVO = getUserVO(_rq);
			session = _rq.getSession();
			setSysdate(_rq);
			_fb.setEntryDate(WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));

			/*
			 * for(int i=0; i<_fb.getSelectedIndex().length; i++) { int index = Integer.parseInt(_fb.getSelectedIndex()[i]);
			 * patProfileDtlVO = lstPrevProfiles.get(index); _fb.setProfileId(patProfileDtlVO.getProfileId());
			 * _fb.setSerialNo(patProfileDtlVO.getSerialNo()); _fb.setProfileHeader(patProfileDtlVO.getProfileHeader());
			 * _fb.setRemarks(patProfileDtlVO.getRemarks()); }
			 */

			// String profileTypeCode=_fb.getProfileType();
			profileType = _fb.getProfileType();
			// String isUnique=_fb.getProfileType().split("#")[1];
			// String profileHeader=_fb.getProfileType().split("#")[2];
			
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
			/*PatientDetailVO[] al = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO voDP = null;
			for (PatientDetailVO vo : al)
				if (vo.getPatCrNo().equals(_fb.getPatCrNo()))
				{
					voDP = vo;
					break;
				}*/

			PatientProfileDetailVO _patientProfileDtlVO = new PatientProfileDetailVO();
			_patientProfileDtlVO.setProfileId(_fb.getProfileId());
			_patientProfileDtlVO.setPatCrNo(_fb.getPatCrNo());
			_patientProfileDtlVO.setEpisodeCode(_fb.getEpisodeCode());
			_patientProfileDtlVO.setDepartmentUnitCode(_fb.getDepartmentUnitCode());
			_patientProfileDtlVO.setProfileType(profileType);

			essentialMap = GenericPatientProfileDATA.fetchProfileDetails(_patientProfileDtlVO, patientDetailVO.getPatGenderCode(), userVO);
			WebUTIL.setMapInSession(essentialMap, _rq);
			lstProMenus = GenericPatientProfileDATA.getProfileBasedDeskMenus(profileType, _fb.getDeskId(), userVO);

			lstDrugRoutes = (List) session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE);
			lstDietType = (List) session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_ALL_DIET_TYPE_LIST);

			ProfileProforma proforma = new ProfileProforma();

			// //////// to set the header
			PatientDetailVO patVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);

			proforma.setHeader(_fb.getProfileHeader(), _fb.getRemarks(), patVO, userVO);
			proforma.setProfileType(profileType);
			List profileTypeList = (List) session.getAttribute(OpdConfig.PROFILE_TYPE_LIST);
			Iterator iteratorProfileTypeList = profileTypeList.iterator();
			while (iteratorProfileTypeList.hasNext())
			{
				Entry entry = (Entry) iteratorProfileTypeList.next();
				if ((_fb.getProfileType() != null) && (_fb.getProfileType().equals(entry.getValue())))
				{
					proforma.setProfileTypeDesc(entry.getLabel());
					break;
				}
			}
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);

			// Profile Diagnosis Detail
			ProfileDiagnosisDtlVO[] profileDiagnosisDtlVO = (ProfileDiagnosisDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DIAGNOSIS_DTL_VO_ARRAY);
			if (profileDiagnosisDtlVO != null && profileDiagnosisDtlVO.length > 0)
			{
				// to get fetch selected row
				String[] str = new String[profileDiagnosisDtlVO.length];
				for (int i = 0; i < profileDiagnosisDtlVO.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);

				// to get episode diagnosis vo
				EpisodeDiagnosisVO[] episodeDiagnosisVOs = new EpisodeDiagnosisVO[profileDiagnosisDtlVO.length];
				for (int i = 0; i < profileDiagnosisDtlVO.length; i++)
				{
					episodeDiagnosisVOs[i] = new EpisodeDiagnosisVO();
					HelperMethods.populate(episodeDiagnosisVOs[i], profileDiagnosisDtlVO[i]);
				}

				// to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("DESKDIAGNOSIS"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}

				proforma.setDiagnosisProforma(_fb.getSelectedMenuId(), episodeDiagnosisVOs, _fb.getFetchSelectedRow());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}

			// Profile Allergy Detail

			ProfileAllergyDtlVO[] profileAllergyDtlVO = (ProfileAllergyDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_ALLERGY_DTL_VO_ARRAY);
			if (profileAllergyDtlVO != null && profileAllergyDtlVO.length > 0)
			{
				String[] str = new String[profileAllergyDtlVO.length];
				for (int i = 0; i < profileAllergyDtlVO.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);

				PatAllergyDtlVO[] patAllergyDtlVOs = new PatAllergyDtlVO[profileAllergyDtlVO.length];
				for (int i = 0; i < profileAllergyDtlVO.length; i++)
				{
					patAllergyDtlVOs[i] = new PatAllergyDtlVO();
					HelperMethods.populate(patAllergyDtlVOs[i], profileAllergyDtlVO[i]);
				}

				// to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("DESKALLERGIES"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}

				proforma.setAllergiesProforma(_fb.getSelectedMenuId(), patAllergyDtlVOs, _fb.getFetchSelectedRow());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}

			// Profile Investigation Detail

			ProfileInvDtlVO[] profileInvDtlVO = (ProfileInvDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_INVESTIGATION_DTL_VO_ARRAY);
			if (profileInvDtlVO != null && profileInvDtlVO.length > 0)
			{
				String[] str = new String[profileInvDtlVO.length];
				for (int i = 0; i < profileInvDtlVO.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);

				ProfileInvestigationVO[] profileInvestigationVOs = new ProfileInvestigationVO[profileInvDtlVO.length];
				for (int i = 0; i < profileInvDtlVO.length; i++)
				{
					profileInvestigationVOs[i] = new ProfileInvestigationVO();
					HelperMethods.populate(profileInvestigationVOs[i], profileInvDtlVO[i]);
				}

				// to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("RESULTVIEWING"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_INVESTIGATION_PROFILE, profileInvestigationVOs);
				proforma.setInvestigationProforma(_fb.getSelectedMenuId(), profileInvestigationVOs, _fb.getFetchSelectedRow());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}

			// Profile Treatment Detail
			ProfileDrugAdviceDtlVO[] profileDrugAdviceDtlVO = (ProfileDrugAdviceDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_TREATMENT_DTL_VO_ARRAY);
			if (profileDrugAdviceDtlVO != null && profileDrugAdviceDtlVO.length > 0)
			{
				// to get fetch selected row
				String[] str = new String[profileDrugAdviceDtlVO.length];
				for (int i = 0; i < profileDrugAdviceDtlVO.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);

				// to get episode diagnosis vo
				PatDrugTreatmentDetailVO[] patDrugTreatmentDetailVOs = new PatDrugTreatmentDetailVO[profileDrugAdviceDtlVO.length];
				for (int i = 0; i < patDrugTreatmentDetailVOs.length; i++)
				{
					patDrugTreatmentDetailVOs[i] = new PatDrugTreatmentDetailVO();
					HelperMethods.populate(patDrugTreatmentDetailVOs[i], profileDrugAdviceDtlVO[i]);
				}

				// to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("DESKTREATMENTDETAIL"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}

				proforma.setTreatmentProforma(_fb.getSelectedMenuId(), patDrugTreatmentDetailVOs, _fb.getFetchSelectedRow());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}

			// Profile Alerts Detail

			ProfileAlertsDtlVO[] profileAlertsDtlVO = (ProfileAlertsDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_ALERTS_DTL_VO_ARRAY);
			if (profileAlertsDtlVO != null && profileAlertsDtlVO.length > 0)
			{
				String[] str = new String[profileAlertsDtlVO.length];
				for (int i = 0; i < profileAlertsDtlVO.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);

				PatientAlertsDetailVO[] patAlertsDetailVos = new PatientAlertsDetailVO[profileAlertsDtlVO.length];
				for (int i = 0; i < profileAlertsDtlVO.length; i++)
				{
					patAlertsDetailVos[i] = new PatientAlertsDetailVO();
					HelperMethods.populate(patAlertsDetailVos[i], profileAlertsDtlVO[i]);
				}

				// to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("GENERICPATIENTALERTS"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}

				proforma.setAlertsProforma(_fb.getSelectedMenuId(), patAlertsDetailVos, _fb.getFetchSelectedRow());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}

			// //Advice On Discharge
			ProfileDrugAdviceDtlVO[] _profileDrugAdviceDtlVO = (ProfileDrugAdviceDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_DRUG_DTL_VO_ARRAY);
			ProfileDietAdviceDtlVO profileDietAdviceDtlVO = (ProfileDietAdviceDtlVO) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_DIET_ADVICE_DTL_VO);
			ProfileRestAdviceDtlVO profileRestAdviceDtlVO = (ProfileRestAdviceDtlVO) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_REST_ADVICE_DTL_VO);
			ProfileExtTreatmentDtlVO[] _profileExtTreatmentDtlVO = (ProfileExtTreatmentDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_EXT_TREATMENT_DTL_VO_ARRAY);
			ProfileExtTreatmentDtlVO[] profileExtTreatmentDtlVO = (ProfileExtTreatmentDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_OTHER_INSTRUCTIONS_DTL_VO_ARRAY);
			ProfileExtTreatmentDtlVO[] profileExtTreatmentDtlVOs = (ProfileExtTreatmentDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_OTHER_ACTIVITY_DTL_VO_ARRAY);
			List<ProfileDrugScheduleDtlVO> profileDrugScheduleDtlVOList = (List) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DRUG_SCHEDULE_DTL_VO_LIST);

			if ((_profileDrugAdviceDtlVO != null && _profileDrugAdviceDtlVO.length > 0) || (profileDietAdviceDtlVO != null)
					|| (profileRestAdviceDtlVO != null) || (_profileExtTreatmentDtlVO != null && _profileExtTreatmentDtlVO.length > 0)
					|| (profileExtTreatmentDtlVO != null && profileExtTreatmentDtlVO.length > 0)
					|| (profileExtTreatmentDtlVOs != null && profileExtTreatmentDtlVOs.length > 0))
			{
				PatDietAdviceDetailVO _patDietDetailVO = null;
				RestAdviceDtlVO _restAdviceDtlVO = null;
				// to get Pat Drug Treatment Vo
				PatDrugTreatmentDetailVO[] _patDrugTreatmentDetailVOs = new PatDrugTreatmentDetailVO[_profileDrugAdviceDtlVO.length];
				setDrugScheduleMap(profileDrugScheduleDtlVOList, _profileDrugAdviceDtlVO, _rq);

				for (int i = 0; i < _patDrugTreatmentDetailVOs.length; i++)
				{
					_patDrugTreatmentDetailVOs[i] = new PatDrugTreatmentDetailVO();

					HelperMethods.populate(_patDrugTreatmentDetailVOs[i], _profileDrugAdviceDtlVO[i]);

					if (_patDrugTreatmentDetailVOs[i].getIsEmptyStomach().equals("0"))
					{
						_patDrugTreatmentDetailVOs[i].setIsEmptyStomachName("No");
					}
					else if (_patDrugTreatmentDetailVOs[i].getIsEmptyStomach().equals("1"))
					{
						_patDrugTreatmentDetailVOs[i].setIsEmptyStomachName("Yes");
					}
					if (lstDrugRoutes != null)
					{
						Iterator itr = lstDrugRoutes.iterator();
						while (itr.hasNext())
						{
							DrugRouteMstVO _drugRouteMstVO = (DrugRouteMstVO) itr.next();
							if (_patDrugTreatmentDetailVOs[i].getDrugRouteId()!=null && _patDrugTreatmentDetailVOs[i].getDrugRouteId().equals(_drugRouteMstVO.getDrugRouteId()))
							{
								_patDrugTreatmentDetailVOs[i].setDrugRouteName(_drugRouteMstVO.getDrugRouteName());
							}
						}
					}
				}
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_PROFILE_DTL_DISCHARGE_DRUG_DETAIL_VO_ARRAY, _patDrugTreatmentDetailVOs);

				// to get selected menu id
				// _fb.setSelectedMenuId("99998");
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("ADVICEONDISCHARGE"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}

				if (profileDietAdviceDtlVO != null)
				{
					_patDietDetailVO = new PatDietAdviceDetailVO();
					HelperMethods.populate(_patDietDetailVO, profileDietAdviceDtlVO);
					if (lstDietType != null)
					{
						Iterator iterator = lstDietType.iterator();
						while (iterator.hasNext())
						{
							Entry entry = (Entry) iterator.next();

							if (_patDietDetailVO.getDietTypeCode().equals(entry.getValue()))
							{
								_patDietDetailVO.setDietTypeDesc(entry.getLabel());
							}
						}
					}
					WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_PROFILE_DTL_DISCHARGE_DIET_DETAIL_VO, _patDietDetailVO);
				}
				if (profileRestAdviceDtlVO != null)
				{
					_restAdviceDtlVO = new RestAdviceDtlVO();
					HelperMethods.populate(_restAdviceDtlVO, profileRestAdviceDtlVO);

					WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_PROFILE_DTL_DISCHARGE_REST_ADVICE_DETAIL_VO, _restAdviceDtlVO);
				}
				PatExtTreatmentDetailVO[] _patExtTreatmentDetailVO = new PatExtTreatmentDetailVO[_profileExtTreatmentDtlVO.length];
				for (int i = 0; i < _patExtTreatmentDetailVO.length; i++)
				{
					_patExtTreatmentDetailVO[i] = new PatExtTreatmentDetailVO();

					HelperMethods.populate(_patExtTreatmentDetailVO[i], _profileExtTreatmentDtlVO[i]);

				}

				WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_PROFILE_DTL_DISCHARGE_OTHER_ADVICE_DETAIL_VO, _patExtTreatmentDetailVO);

				// Other Instructions/Activities
				List deptUnitWiseInstList = (List) essentialMap.get(OpdConfig.ALL_OTHER_INSTRUCTION_LIST_BY_GENDER);
				List deptUnitWiseActivityList = (List) essentialMap.get(OpdConfig.ALL_ONE_TIME_ACTIVITY_LIST_BY_GENDER);

				WebUTIL.setAttributeInSession(_rq, OpdConfig.OTHER_INSTRUCTION_LIST_FOR_BOTH, deptUnitWiseInstList);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.ONE_TIME_ACTIVITY_LIST_FOR_BOTH, deptUnitWiseActivityList);

				List<PatExtTreatmentDetailVO> selOtherInstList = new ArrayList();
				List<PatExtTreatmentDetailVO> selActivityList = new ArrayList();
				PatExtTreatmentDetailVO[] patExtTreatmentDetailVO = null;
				PatExtTreatmentDetailVO[] patExtTreatmentDetailVOs = null;
				if (profileExtTreatmentDtlVO != null)
				{
					patExtTreatmentDetailVO = new PatExtTreatmentDetailVO[profileExtTreatmentDtlVO.length];
					for (int i = 0; i < profileExtTreatmentDtlVO.length; i++)
					{
						patExtTreatmentDetailVO[i] = new PatExtTreatmentDetailVO();
						HelperMethods.populate(patExtTreatmentDetailVO[i], profileExtTreatmentDtlVO[i]);
					}

				}
				if (patExtTreatmentDetailVO != null)
				{
					for (int i = 0; i < patExtTreatmentDetailVO.length; i++)
					{
						selOtherInstList.add(patExtTreatmentDetailVO[i]);
					}
					WebUTIL.setAttributeInSession(_rq, OpdConfig.PREV_OTHER_INSTRUCTION_LIST, selOtherInstList);
					WebUTIL.setAttributeInSession(_rq, OpdConfig.SELECTED_CHOICE, "1");
				}
				// other activities
				if (profileExtTreatmentDtlVOs != null)
				{
					patExtTreatmentDetailVOs = new PatExtTreatmentDetailVO[profileExtTreatmentDtlVOs.length];
					for (int i = 0; i < profileExtTreatmentDtlVOs.length; i++)
					{
						patExtTreatmentDetailVOs[i] = new PatExtTreatmentDetailVO();
						HelperMethods.populate(patExtTreatmentDetailVOs[i], profileExtTreatmentDtlVOs[i]);
					}

				}
				if (patExtTreatmentDetailVOs != null)
				{
					for (int i = 0; i < patExtTreatmentDetailVOs.length; i++)
					{
						selActivityList.add(patExtTreatmentDetailVOs[i]);
					}
					WebUTIL.setAttributeInSession(_rq, OpdConfig.PREV_OTHER_ACTIVITY_LIST, selActivityList);
					WebUTIL.setAttributeInSession(_rq, OpdConfig.SELECTED_CHOICE, "1");
				}

				proforma.setAdviceOnDischargeProforma(_fb.getSelectedMenuId(), _patDrugTreatmentDetailVOs, _patDietDetailVO, _restAdviceDtlVO,
						_patExtTreatmentDetailVO, selOtherInstList, selActivityList);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);

			}

			// Final Profile Complaints Detail
			ProfileClinicalDtlVO[] profileClinicalDetailVO = (ProfileClinicalDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_COMPLAINTS_DTL_VO_ARRAY);
			if (profileClinicalDetailVO != null && profileClinicalDetailVO.length > 0)
			{
				for (int i = 0; i < profileClinicalDetailVO.length; i++)
				{
					String deskMenuId = profileClinicalDetailVO[i].getDeskMenuId();
					String recordView = profileClinicalDetailVO[i].getRecordView();
					if (deskMenuId==null || deskMenuId.equals("") || recordView==null || recordView.equals(""))	continue;

					UserDeskMenuTemplateMasterVO userDeskMenuTemplateMasterVO = new UserDeskMenuTemplateMasterVO();
					HelperMethods.populate(userDeskMenuTemplateMasterVO, _fb);
					userDeskMenuTemplateMasterVO.setDeptUnitCode(_fb.getDepartmentUnitCode());
					userDeskMenuTemplateMasterVO.setDeskMenuId(deskMenuId);

					lstTemps = GenericTemplateTileDATA.getDeskMenuTemplateList(_fb.getDeskType(), _fb.getPatCrNo(),userDeskMenuTemplateMasterVO, userVO);

					for (Entry e : lstTemps)	e.setValue(e.getValue().split("#")[0]);

					mapTemplateDtl = GenericTemplateTileDATA.getAllTemplateDetails(lstTemps, userVO);
					WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_DTL_MAP, mapTemplateDtl);

					lstAllTempParas = GenericTemplateTileDATA.getAllTemplateParametersDetail(lstTemps, userVO);
					for (TemplateParameterMasterVO vo : lstAllTempParas)
					{
						if (vo.getParaId() != null)
						{
							LinkedHashMap<String, String> mapTemp = mapAllTempParas.get(vo.getTemplateId());
							if (mapTemp == null) mapTemp = new LinkedHashMap<String, String>();
							mapTemp.put(vo.getParaId(), vo.getParaName());
							mapAllTempParas.put(vo.getTemplateId(), mapTemp);
	
							if (!vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_LABEL)
									&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_COMMENT)
									&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_INFORMATION)
									&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_IMAGEVIEW))
							{
	
								LinkedHashMap<String, TemplateParameterMasterVO> map = mapAllValuedTempParas.get(vo.getTemplateId());
								if (map == null) map = new LinkedHashMap<String, TemplateParameterMasterVO>();
								map.put(vo.getParaId(), vo);
								mapAllValuedTempParas.put(vo.getTemplateId(), map);
							}
						}
					}
					WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_PARA_MAP, mapAllTempParas);
					WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_VALUED_TEMP_PARA_MAP, mapAllValuedTempParas);
					WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_LIST_ALL_TEMP_PARAS, lstAllTempParas);

					if(recordView.equals(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_PARAMETER_WISE))
					{
						// Getting Selected Paras
						LinkedHashMap<String, List<String>> mapSelPara = new LinkedHashMap<String, List<String>>();
						for (int x=i; i< profileClinicalDetailVO.length; x++)
						{
							ProfileClinicalDtlVO voProCliDtl = profileClinicalDetailVO[x];
							if(voProCliDtl.getDeskMenuId().equals(deskMenuId))
							{
								String tempId = voProCliDtl.getTemplateId();
								List<String> lstPara = mapSelPara.get(tempId);
								if (lstPara == null) lstPara = new ArrayList<String>();
								mapSelPara.put(voProCliDtl.getTemplateId(), lstPara);
								lstPara.add(voProCliDtl.getParaId());
								i++;
							}
							else
								break;
						}
						LinkedHashMap<String, LinkedHashSet<String>> mapViewParas = new LinkedHashMap<String, LinkedHashSet<String>>();
						for (String tempId : mapSelPara.keySet())
						{
							LinkedHashMap<String, TemplateParameterMasterVO> mapParaDtl = mapAllValuedTempParas.get(tempId);
							List<String> selectedParas = mapSelPara.get(tempId);
							LinkedHashSet<String> viewParas = mapViewParas.get(tempId);
							if (viewParas == null) viewParas = new LinkedHashSet<String>();
							mapViewParas.put(tempId, viewParas);
							for (String selParaId : selectedParas)
							{
								for (String paraId : mapParaDtl.keySet())
								{
									TemplateParameterMasterVO tempParaVO = mapParaDtl.get(paraId);
									if (tempParaVO.getParaId() != null && !tempParaVO.getParaId().equals("") && tempParaVO.getParaId().equals(selParaId)) viewParas
											.add(paraId);
									else if (tempParaVO.getParentParaId() != null && !tempParaVO.getParentParaId().equals(""))
									{
										String[] parents = tempParaVO.getParentParaId().split(GenericTemplateUtility.SEP_IN_PARA_PARENT);
										boolean flag1 = false;
										for (int x = 0; x < parents.length; x++)
											if (parents[x].equals(selParaId))
											{
												flag1 = true;
												break;
											}
										if (flag1) viewParas.add(paraId);
									}
								}
							}
						}
						// Setting Template Chart Selected Template
						LinkedHashMap<String, String> mapChartTemps = new LinkedHashMap<String, String>();
						for (String tempId : mapViewParas.keySet())
						{
							TemplateMasterVO voTemp = mapTemplateDtl.get(tempId);
							Entry e = new Entry();
							e.setLabel(voTemp.getTemplateName());
							e.setValue(voTemp.getTemplateId());
							lstSelectedTemps.add(e);
							mapChartTemps.put(voTemp.getTemplateId(), voTemp.getTemplateName());
						}

						// Setting Desk Menu Wise
						//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_LIST_MAP, mapChartTemps);
						Map mpDeskMenuChartTemps = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_LIST_MAP); 
						if(mpDeskMenuChartTemps==null)	mpDeskMenuChartTemps = new HashMap();
						mpDeskMenuChartTemps.put(deskMenuId, mapChartTemps);
						WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_LIST_MAP, mpDeskMenuChartTemps);

						// Setting Template Chart Selected Template Parameters
						LinkedHashMap<String, List<Entry>> mapChartTempParas = new LinkedHashMap<String, List<Entry>>();
						for (String tempId : mapViewParas.keySet())
						{
							List<Entry> lstParas = mapChartTempParas.get(tempId);
							if (lstParas == null) lstParas = new ArrayList<Entry>();
							mapChartTempParas.put(tempId, lstParas);
							for (String paraId : mapViewParas.get(tempId))
							{
								Entry e = new Entry(mapAllValuedTempParas.get(tempId).get(paraId).getParaName(), paraId);
								lstParas.add(e);
							}
						}
						// Setting Desk Menu Wise
						//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP, mapChartTempParas);
						Map mpDeskMenuChartTempParas = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
						if(mpDeskMenuChartTempParas==null) mpDeskMenuChartTempParas = new HashMap();
						mpDeskMenuChartTempParas.put(deskMenuId, mapChartTempParas);
						WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP, mpDeskMenuChartTempParas);

						// Getting Visit Dates
						PatientClinicalDetailVO _patCliDtlVO = new PatientClinicalDetailVO();
						HelperMethods.populate(_patCliDtlVO, _fb);
						_patCliDtlVO.setDeskMenuId(deskMenuId);  //added by manisha date: 24.8.2017
						lstReportDates = GenericTemplateTileDATA.getReportDateList(_fb.getDeskType(), _patCliDtlVO, userVO);
						if (lstReportDates == null) lstReportDates = new ArrayList<Entry>();
						// Setting Record Dates
						LinkedHashMap<String, String> mapRecordDates = new LinkedHashMap<String, String>();
						for (Entry e : lstReportDates)
							mapRecordDates.put(e.getLabel(), e.getLabel());
		
						// Map of RecordDate/Map of Temp Id/Map of Parameter Id/Para Value
						Map<String, Map<String, Map<String, String>>> mpChartClinicalData = null;
						PatientClinicalDetailVO patCliniDtlVO = new PatientClinicalDetailVO();
						HelperMethods.populate(patCliniDtlVO, _fb);
						patCliniDtlVO.setDeskMenuId(deskMenuId);
						mpChartClinicalData = GenericTemplateTileDATA.getPatientChartClinicalDataTempWise(_fb.getDeskType(), patCliniDtlVO, lstReportDates,
								lstSelectedTemps, userVO);
		
						// Remove Report Dates that don't have Data
						boolean dataFoundFlag = false;
						List<String> lstRemove = new ArrayList<String>();
						for (String recordDate : mapRecordDates.keySet())
						{
							Map<String, Map<String, String>> mpTemp = mpChartClinicalData.get(recordDate);
							if (mpTemp == null)
							{
								lstRemove.add(recordDate);
								continue;
							}
							// Removing Not Useful Templates Data
							List<String> lstRemoveTemplates = new ArrayList<String>();
							for (String key : mpTemp.keySet())
							{
								boolean flag1 = false;
								for (Entry entTemp : lstSelectedTemps)
									if (entTemp.getValue().split("#")[0].equals(key))
									{
										flag1 = true;
										break;
									}
								if (!flag1) lstRemoveTemplates.add(key);
								else if (mpTemp.get(key).size() == 0) lstRemoveTemplates.add(key);
							}
							for (String tempId : lstRemoveTemplates)
								mpTemp.remove(tempId);
		
							// Checking For Record Date Data
							if (mpTemp.keySet().size() == 0)
							{
								mpChartClinicalData.remove(recordDate);
								lstRemove.add(recordDate);
							}
							else dataFoundFlag = true;
						}
						List<Entry> lstRemoveEntry = new ArrayList<Entry>();
						for (String str : lstRemove)
						{
							mapRecordDates.remove(str);
							for (Entry entObj : lstReportDates)
								if (entObj.getValue().equals(str))
								{
									lstRemoveEntry.add(entObj);
									break;
								}
						}
						//for (Entry entObj : lstRemoveEntry)
							//lstReportDates.remove(entObj);

						dataFoundFlag = false;

						// Getting Desk Menu Wise 
						//LinkedHashMap<String, List<Entry>> mapChartTempParas1 = (LinkedHashMap<String, List<Entry>>) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
						Map mpDeskMenuChartTempParas1 = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
						LinkedHashMap<String, List<Entry>> mapChartTempParas1 = (LinkedHashMap<String, List<Entry>>)mpDeskMenuChartTempParas1.get(deskMenuId);
		
						for (String tempId : mapChartTempParas1.keySet())
						{
							List<Entry> removePara = new ArrayList<Entry>();
							for (Entry entObj : mapChartTempParas1.get(tempId))
							{
								boolean flag1 = false;
								// Map of RecordDate/Map of Temp Id/Map of Parameter Id/Para Value
								for (String recordDate : mpChartClinicalData.keySet())
									for (String templateId : mpChartClinicalData.get(recordDate).keySet())
										if (templateId.equals(tempId)) for (String paraId : mpChartClinicalData.get(recordDate).get(templateId).keySet())
											if (paraId.equals(entObj.getValue()))
											{
												dataFoundFlag = true;
												flag1 = true;
											}
								if (!flag1) removePara.add(entObj);
							}
							for (Entry entObj : removePara)
								mapChartTempParas1.get(tempId).remove(entObj);
						}
						// Setting Desk Menu Wise
						//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP, mapRecordDates);
						Map mpDeskMenuRecordDates = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP);
						if(mpDeskMenuRecordDates==null) mpDeskMenuRecordDates = new HashMap();
						mpDeskMenuRecordDates.put(deskMenuId, mapRecordDates);
						WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP, mpDeskMenuRecordDates);

						// Setting Desk Menu Wise
						//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpChartClinicalData);
						Map mpDeskMenuChartClinicalData = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);
						if(mpDeskMenuChartClinicalData==null) mpDeskMenuChartClinicalData = new HashMap();
						mpDeskMenuChartClinicalData.put(deskMenuId, mpChartClinicalData);
						WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpDeskMenuChartClinicalData);
						WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_VIEW_MODE,GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_TEMPLATE_DESK_ID, deskMenuId);

						if (!dataFoundFlag)
						{
							throw new HisRecordNotFoundException("No Data Found");
						}
						proforma.setComplaintsProforma(deskMenuId);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);

						_fb.setReportMode(recordView);
						// Setting Desk Menu Wise Report View
						Map mpDeskMenuReportModes = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP); 
						if(mpDeskMenuReportModes==null)	mpDeskMenuReportModes = new HashMap();
						mpDeskMenuReportModes.put(deskMenuId, recordView);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP, mpDeskMenuReportModes);
					}
					else if(recordView.equals(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_DATE_WISE))
					{
						PatientClinicalDetailVO _patCliDtlVO = new PatientClinicalDetailVO();
						HelperMethods.populate(_patCliDtlVO, _fb);

						lstReportDates = GenericTemplateTileDATA.getReportDateList(_fb.getDeskType(), _patCliDtlVO, userVO);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_VISIT_DATES, lstReportDates);

						if (lstReportDates == null) lstReportDates = new ArrayList<Entry>();
						// Setting Record Dates
						LinkedHashMap<String, String> mapRecordDates = new LinkedHashMap<String, String>();
						for (Entry e : lstReportDates)
							mapRecordDates.put(e.getLabel(), e.getLabel());

						// Map of RecordDate/Map of Temp Id/Map of Parameter Id/Para Value
						Map<String, Map<String, Map<String, String>>> mpChartClinicalData = null;
						PatientClinicalDetailVO patCliniDtlVO = new PatientClinicalDetailVO();
						HelperMethods.populate(patCliniDtlVO, _fb);
						patCliniDtlVO.setDeskMenuId(deskMenuId);
						mpChartClinicalData = GenericTemplateTileDATA.getPatientChartClinicalDataTempWise(_fb.getDeskType(), patCliniDtlVO, 
								lstReportDates, lstTemps, userVO);

						// Remove Report Dates that don't have Data
						List<String> lstRemove = new ArrayList<String>();
						for (String recordDate : mapRecordDates.keySet())
						{
							Map<String, Map<String, String>> mpTemp = mpChartClinicalData.get(recordDate);
							if (mpTemp == null)
							{
								lstRemove.add(recordDate);
								continue;
							}
							// Removing Not Useful Templates Data
							List<String> lstRemoveTemplates = new ArrayList<String>();
							for (String key : mpTemp.keySet())
							{
								boolean flag1 = false;
								for (Entry entTemp : lstTemps)
									if (entTemp.getValue().split("#")[0].equals(key))
									{
										flag1 = true;
										break;
									}
								if (!flag1) lstRemoveTemplates.add(key);
								else if (mpTemp.get(key).size() == 0) lstRemoveTemplates.add(key);
							}
							for (String tempId : lstRemoveTemplates)
								mpTemp.remove(tempId);

							// Checking For Record Date Data
							if (mpTemp.keySet().size() == 0)
							{
								mpChartClinicalData.remove(recordDate);
								lstRemove.add(recordDate);
							}
							// else
							// dataFoundFlag = true;
						}
						List<Entry> lstRemoveEntry = new ArrayList<Entry>();
						for (String str : lstRemove)
						{
							mapRecordDates.remove(str);
							for (Entry entObj : lstReportDates)
								if (entObj.getValue().equals(str))
								{
									lstRemoveEntry.add(entObj);
									break;
								}
						}
					//	for (Entry entObj : lstRemoveEntry)
						//	lstReportDates.remove(entObj);

						// Setting Desk Menu Wise
						//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpChartClinicalData);
						Map mpDeskMenuChartClinicalData = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);
						if(mpDeskMenuChartClinicalData==null) mpDeskMenuChartClinicalData = new HashMap();
						mpDeskMenuChartClinicalData.put(deskMenuId, mpChartClinicalData);
						WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpDeskMenuChartClinicalData);

						WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_VIEW_MODE,GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
							
						Map<String, Map<String, Map<String, String>>> mpChartClinicalDataTemp = new HashMap<String, Map<String, Map<String, String>>>();
						
						String strVisitDate = null;
						lstVisitDates = new ArrayList<Entry>();
						for (int x = i; x < profileClinicalDetailVO.length; x++)
						{
							ProfileClinicalDtlVO voProCliDtl = profileClinicalDetailVO[x];
							if(voProCliDtl.getDeskMenuId().equals(deskMenuId))
							{
								if(strVisitDate==null || !strVisitDate.equals(voProCliDtl.getRecordDate()))
								{
									strVisitDate = voProCliDtl.getRecordDate();
									Entry entry = new Entry();
									entry.setLabel(voProCliDtl.getRecordDate());
									entry.setValue(voProCliDtl.getRecordDate());
									String strVal = null;
									for(Entry ent : lstReportDates)
										if(_fb.getDeskType().equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK) 
												&& ent.getLabel().equals(entry.getLabel().substring(0,11)))
										{
											strVal = ent.getValue();
											//entry.setLabel(ent.getLabel());
											//entry.setValue(ent.getValue());
											break;
										}
										else if(ent.getLabel().equals(entry.getLabel()))
										{
											strVal = ent.getValue();
											//entry.setValue(ent.getValue());
											break;
										}
									lstVisitDates.add(entry);
									Map mpVisitData = (Map) mpChartClinicalData.get(strVal);
									mpChartClinicalDataTemp.put(voProCliDtl.getRecordDate(), mpVisitData);
								}
								i++;
							}
							else
								break;
						}

						// Setting Desk Menu Wise
						//WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES, lstVisitDates);
						Map mpDeskMenuVisitDates = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES);
						if(mpDeskMenuVisitDates==null)	mpDeskMenuVisitDates = new HashMap();
						mpDeskMenuVisitDates.put(deskMenuId, lstVisitDates);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES, mpDeskMenuVisitDates);
						
						// Setting Desk Menu Wise
						//WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS, lstTemps);
						Map mpDeskMenuTemps = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS); 
						if(mpDeskMenuTemps==null)	mpDeskMenuTemps = new HashMap();
						mpDeskMenuTemps.put(deskMenuId, lstTemps);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS, mpDeskMenuTemps);

						// Setting Desk Menu Wise
						//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpChartClinicalData);
						if(mpDeskMenuChartClinicalData==null) mpDeskMenuChartClinicalData = new HashMap();
						mpDeskMenuChartClinicalData.put(deskMenuId, mpChartClinicalData);
						WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpDeskMenuChartClinicalData);

						WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_TEMPLATE_DESK_ID, deskMenuId);

						proforma.setComplaintsProforma(deskMenuId);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);

						_fb.setReportMode(recordView);
						// Setting Desk Menu Wise Report View
						Map mpDeskMenuReportModes = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP); 
						if(mpDeskMenuReportModes==null)	mpDeskMenuReportModes = new HashMap();
						mpDeskMenuReportModes.put(deskMenuId, recordView);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP, mpDeskMenuReportModes);
					}
				}
			}

			/*// Profile Complaints Detail Date Wise
			ProfileClinicalDtlVO[] _profileClinicalDetailVOs = (ProfileClinicalDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_COMPLAINTS_DTL_DATE_WISE_VO_ARRAY);
			String deskMenuIds = "";
			String recordViews = "";
			if (_profileClinicalDetailVOs != null && _profileClinicalDetailVOs.length > 0)
			{

				for (int i = 0; i < _profileClinicalDetailVOs.length; i++)
				{
					deskMenuIds = _profileClinicalDetailVOs[i].getDeskMenuId();
					recordViews = _profileClinicalDetailVOs[i].getRecordView();

					if (!deskMenuIds.equals("") && !recordViews.equals(""))
					{
						break;
					}
				}

				UserDeskMenuTemplateMasterVO userDeskMenuTemplateMasterVO = new UserDeskMenuTemplateMasterVO();
				HelperMethods.populate(userDeskMenuTemplateMasterVO, _fb);
				userDeskMenuTemplateMasterVO.setDeptUnitCode(_fb.getDepartmentUnitCode());
				userDeskMenuTemplateMasterVO.setDeskMenuId(deskMenuIds);

				lstTemps = GenericTemplateTileDATA.getDeskMenuTemplateList(_fb.getDeskType(), userDeskMenuTemplateMasterVO, userVO);

				lstActiveTemps = new ArrayList<Entry>();
				lstInactiveTemps = new ArrayList<Entry>();
				for (Entry e : lstTemps)
				{
					Entry ent = new Entry();
					ent.setLabel(e.getLabel());
					ent.setValue(e.getValue().split("#")[0]);
					String defalutFlag = e.getValue().split("#")[1];
					if (defalutFlag.equals(GenericTemplateConfig.YES)) lstActiveTemps.add(ent);
					else if (defalutFlag.equals(GenericTemplateConfig.NO)) lstInactiveTemps.add(ent);
					e.setValue(e.getValue().split("#")[0]);
				}

				mapTemplateDtl = GenericTemplateTileDATA.getAllTemplateDetails(lstTemps, userVO);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_DTL_MAP, mapTemplateDtl);

				lstAllTempParas = GenericTemplateTileDATA.getAllTemplateParametersDetail(lstTemps, userVO);
				for (TemplateParameterMasterVO vo : lstAllTempParas)
				{
					if (vo.getParaId() != null)
					{
						LinkedHashMap<String, String> mapTemp = mapAllTempParas.get(vo.getTemplateId());
						if (mapTemp == null) mapTemp = new LinkedHashMap<String, String>();
						mapTemp.put(vo.getParaId(), vo.getParaName());
						mapAllTempParas.put(vo.getTemplateId(), mapTemp);

						if (!vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_LABEL)
								&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_COMMENT)
								&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_INFORMATION)
								&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_IMAGEVIEW))
						{

							LinkedHashMap<String, TemplateParameterMasterVO> map = mapAllValuedTempParas.get(vo.getTemplateId());
							if (map == null) map = new LinkedHashMap<String, TemplateParameterMasterVO>();
							map.put(vo.getParaId(), vo);
							mapAllValuedTempParas.put(vo.getTemplateId(), map);
						}
					}
				}
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_PARA_MAP, mapAllTempParas);///----
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_VALUED_TEMP_PARA_MAP, mapAllValuedTempParas);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_LIST_ALL_TEMP_PARAS, lstAllTempParas);

				// set method

				PatientClinicalDetailVO _patCliDtlVO = new PatientClinicalDetailVO();
				HelperMethods.populate(_patCliDtlVO, _fb);

				lstReportDates = GenericTemplateTileDATA.getReportDateList(_fb.getDeskType(), _patCliDtlVO, userVO);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_VISIT_DATES, lstReportDates);
				// }

				// tempid:paraid,paraname

				if (lstReportDates == null) lstReportDates = new ArrayList<Entry>();
				// Setting Record Dates
				LinkedHashMap<String, String> mapRecordDates = new LinkedHashMap<String, String>();
				for (Entry e : lstReportDates)
					mapRecordDates.put(e.getValue(), e.getLabel());

				// Map of RecordDate/Map of Temp Id/Map of Parameter Id/Para Value
				Map<String, Map<String, Map<String, String>>> mpChartClinicalData = null;
				PatientClinicalDetailVO patCliniDtlVO = new PatientClinicalDetailVO();
				HelperMethods.populate(patCliniDtlVO, _fb);
				patCliniDtlVO.setDeskMenuId(deskMenuIds);
				mpChartClinicalData = GenericTemplateTileDATA.getPatientChartClinicalDataTempWise(_fb.getDeskType(), patCliniDtlVO, lstReportDates,
						lstTemps, userVO);//----

				// Remove Report Dates that don't have Data
				// boolean dataFoundFlag = false;
				List<String> lstRemove = new ArrayList<String>();
				for (String recordDate : mapRecordDates.keySet())
				{
					Map<String, Map<String, String>> mpTemp = mpChartClinicalData.get(recordDate);
					if (mpTemp == null)
					{
						lstRemove.add(recordDate);
						continue;
					}
					// Removing Not Useful Templates Data
					List<String> lstRemoveTemplates = new ArrayList<String>();
					for (String key : mpTemp.keySet())
					{
						boolean flag = false;
						for (Entry entTemp : lstTemps)
							if (entTemp.getValue().split("#")[0].equals(key))
							{
								flag = true;
								break;
							}
						if (!flag) lstRemoveTemplates.add(key);
						else if (mpTemp.get(key).size() == 0) lstRemoveTemplates.add(key);
					}
					for (String tempId : lstRemoveTemplates)
						mpTemp.remove(tempId);

					// Checking For Record Date Data
					if (mpTemp.keySet().size() == 0)
					{
						mpChartClinicalData.remove(recordDate);
						lstRemove.add(recordDate);
					}
					// else
					// dataFoundFlag = true;
				}
				List<Entry> lstRemoveEntry = new ArrayList<Entry>();
				for (String str : lstRemove)
				{
					mapRecordDates.remove(str);
					for (Entry entObj : lstReportDates)
						if (entObj.getValue().equals(str))
						{
							lstRemoveEntry.add(entObj);
							break;
						}
				}
				for (Entry entObj : lstRemoveEntry)
					lstReportDates.remove(entObj);

				// Adding Desk menu Wise
				
				// Setting Desk Menu Wise
				//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpChartClinicalData);
				Map mpDeskMenuChartClinicalData = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);
				if(mpDeskMenuChartClinicalData==null) mpDeskMenuChartClinicalData = new HashMap();
				mpDeskMenuChartClinicalData.put(_fb.getSelectedMenuId(), mpChartClinicalData);
				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpDeskMenuChartClinicalData);
				
				
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_TEMPLATE_DESK_ID, deskMenuIds);
				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_VIEW_MODE, GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);

				for (int i = 0; i < _profileClinicalDetailVOs.length; i++)
				{
					Entry entry = new Entry();
					entry.setLabel(_profileClinicalDetailVOs[i].getRecordDate());
					for(Entry ent : lstReportDates)
						if(_fb.getDeskType().equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK) 
								&& ent.getLabel().equals(entry.getLabel().substring(0,11)))
						{
							entry.setValue(ent.getValue());
							break;
						}
						else if(ent.getLabel().equals(entry.getLabel()))
						{
							entry.setValue(ent.getValue());
							break;
						}

					lstVisitDates.add(entry);
				}

				// Setting Desk Menu Wise
				//WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES, lstVisitDates);
				Map mpDeskMenuVisitDates = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES);
				if(mpDeskMenuVisitDates==null)	mpDeskMenuVisitDates = new HashMap();
				mpDeskMenuVisitDates.put(_fb.getSelectedMenuId(), lstVisitDates);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES, mpDeskMenuVisitDates);

				// Setting Desk Menu Wise
				//WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS, lstTemps);
				Map mpDeskMenuTemps = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS); 
				if(mpDeskMenuTemps==null)	mpDeskMenuTemps = new HashMap();
				mpDeskMenuTemps.put(_fb.getSelectedMenuId(), lstTemps);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS, mpDeskMenuTemps);
	
				proforma.setComplaintsProforma(deskMenuIds);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
				_fb.setReportMode(recordViews);

				// Setting Desk Menu Wise Report View
				Map mpDeskMenuReportModes = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP); 
				if(mpDeskMenuReportModes==null)	mpDeskMenuReportModes = new HashMap();
				mpDeskMenuReportModes.put(_fb.getSelectedMenuId(), recordViews);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP, mpDeskMenuReportModes);
			}

			// /////////Profile Complaints Detail Parameter Wise
			ProfileClinicalDtlVO[] profileClinicalDetailVO = (ProfileClinicalDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_COMPLAINTS_DTL_VO_ARRAY);
			String deskMenuId = "";
			String recordView = "";
			if (profileClinicalDetailVO != null && profileClinicalDetailVO.length > 0)
			{

				for (int i = 0; i < profileClinicalDetailVO.length; i++)
				{
					deskMenuId = profileClinicalDetailVO[i].getDeskMenuId();
					recordView = profileClinicalDetailVO[i].getRecordView();

					if (!deskMenuId.equals("") && !recordView.equals(""))
					{
						break;
					}
				}

				UserDeskMenuTemplateMasterVO userDeskMenuTemplateMasterVO = new UserDeskMenuTemplateMasterVO();
				HelperMethods.populate(userDeskMenuTemplateMasterVO, _fb);
				userDeskMenuTemplateMasterVO.setDeptUnitCode(_fb.getDepartmentUnitCode());
				userDeskMenuTemplateMasterVO.setDeskMenuId(deskMenuId);

				lstTemps = GenericTemplateTileDATA.getDeskMenuTemplateList(_fb.getDeskType(), userDeskMenuTemplateMasterVO, userVO);//---------

				lstActiveTemps = new ArrayList<Entry>();
				lstInactiveTemps = new ArrayList<Entry>();
				for (Entry e : lstTemps)
				{
					Entry ent = new Entry();
					ent.setLabel(e.getLabel());
					ent.setValue(e.getValue().split("#")[0]);
					String defalutFlag = e.getValue().split("#")[1];
					if (defalutFlag.equals(GenericTemplateConfig.YES)) lstActiveTemps.add(ent);
					else if (defalutFlag.equals(GenericTemplateConfig.NO)) lstInactiveTemps.add(ent);
					e.setValue(e.getValue().split("#")[0]);
				}

				// putmap.put(GenericTemplateConfig.TEMPLATE_TILE_ALL_TEMPLATE_LIST, lstTemps);
				// putmap.put(GenericTemplateConfig.TEMPLATE_TILE_DEFAULT_TEMPLATE_LIST, lstActiveTemps);
				// putmap.put(GenericTemplateConfig.TEMPLATE_TILE_UNDEFAULT_TEMPLATE_LIST, lstInactiveTemps);
				mapTemplateDtl = GenericTemplateTileDATA.getAllTemplateDetails(lstTemps, userVO);//----------
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_DTL_MAP, mapTemplateDtl);

				lstAllTempParas = GenericTemplateTileDATA.getAllTemplateParametersDetail(lstTemps, userVO);//-----
				for (TemplateParameterMasterVO vo : lstAllTempParas)
				{
					if (vo.getParaId() != null)
					{
						LinkedHashMap<String, String> mapTemp = mapAllTempParas.get(vo.getTemplateId());
						if (mapTemp == null) mapTemp = new LinkedHashMap<String, String>();
						mapTemp.put(vo.getParaId(), vo.getParaName());
						mapAllTempParas.put(vo.getTemplateId(), mapTemp);

						if (!vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_LABEL)
								&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_COMMENT)
								&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_INFORMATION)
								&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_IMAGEVIEW))
						{

							LinkedHashMap<String, TemplateParameterMasterVO> map = mapAllValuedTempParas.get(vo.getTemplateId());
							if (map == null) map = new LinkedHashMap<String, TemplateParameterMasterVO>();
							map.put(vo.getParaId(), vo);
							mapAllValuedTempParas.put(vo.getTemplateId(), map);
						}
					}
				}
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_PARA_MAP, mapAllTempParas);//------
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_VALUED_TEMP_PARA_MAP, mapAllValuedTempParas);

				// set method

				mapTemplateDtl = (Map<String, TemplateMasterVO>) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_DTL_MAP);
				mapAllValuedTempParas = (LinkedHashMap<String, LinkedHashMap<String, TemplateParameterMasterVO>>) session
						.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_VALUED_TEMP_PARA_MAP);

				// Getting Selected Paras
				LinkedHashMap<String, List<String>> mapSelPara = new LinkedHashMap<String, List<String>>();
				for (int i = 0; i < profileClinicalDetailVO.length; i++)
				{
					String tempId = profileClinicalDetailVO[i].getTemplateId();
					// String paraId = _fb.getSelectedParas()[i].split("#")[1];
					List<String> lstPara = mapSelPara.get(tempId);
					if (lstPara == null) lstPara = new ArrayList<String>();
					mapSelPara.put(profileClinicalDetailVO[i].getTemplateId(), lstPara);
					lstPara.add(profileClinicalDetailVO[i].getParaId());
				}

				LinkedHashMap<String, LinkedHashSet<String>> mapViewParas = new LinkedHashMap<String, LinkedHashSet<String>>();
				for (String tempId : mapSelPara.keySet())
				{
					LinkedHashMap<String, TemplateParameterMasterVO> mapParaDtl = mapAllValuedTempParas.get(tempId);
					List<String> selectedParas = mapSelPara.get(tempId);
					LinkedHashSet<String> viewParas = mapViewParas.get(tempId);
					if (viewParas == null) viewParas = new LinkedHashSet<String>();
					mapViewParas.put(tempId, viewParas);
					for (String selParaId : selectedParas)
					{
						for (String paraId : mapParaDtl.keySet())
						{
							TemplateParameterMasterVO tempParaVO = mapParaDtl.get(paraId);
							if (tempParaVO.getParaId() != null && !tempParaVO.getParaId().equals("") && tempParaVO.getParaId().equals(selParaId)) viewParas
									.add(paraId);
							else if (tempParaVO.getParentParaId() != null && !tempParaVO.getParentParaId().equals(""))
							{
								String[] parents = tempParaVO.getParentParaId().split(GenericTemplateUtility.SEP_IN_PARA_PARENT);
								boolean flag = false;
								for (int i = 0; i < parents.length; i++)
									if (parents[i].equals(selParaId))
									{
										flag = true;
										break;
									}
								if (flag) viewParas.add(paraId);
							}
						}
					}
				}
				// Setting Template Chart Selected Template
				LinkedHashMap<String, String> mapChartTemps = new LinkedHashMap<String, String>();
				for (String tempId : mapViewParas.keySet())
				{
					TemplateMasterVO voTemp = mapTemplateDtl.get(tempId);
					Entry e = new Entry();
					e.setLabel(voTemp.getTemplateName());
					e.setValue(voTemp.getTemplateId());
					lstSelectedTemps.add(e);
					mapChartTemps.put(voTemp.getTemplateId(), voTemp.getTemplateName());
				}
				// Setting Desk Menu Wise
				//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_LIST_MAP, mapChartTemps);
				Map mpDeskMenuChartTemps = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_LIST_MAP); 
				if(mpDeskMenuChartTemps==null)	mpDeskMenuChartTemps = new HashMap();
				mpDeskMenuChartTemps.put(_fb.getSelectedMenuId(), mapChartTemps);
				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_LIST_MAP, mpDeskMenuChartTemps);

				// Setting Template Chart Selected Template Parameters
				LinkedHashMap<String, List<Entry>> mapChartTempParas = new LinkedHashMap<String, List<Entry>>();
				for (String tempId : mapViewParas.keySet())
				{
					List<Entry> lstParas = mapChartTempParas.get(tempId);
					if (lstParas == null) lstParas = new ArrayList<Entry>();
					mapChartTempParas.put(tempId, lstParas);
					for (String paraId : mapViewParas.get(tempId))
					{
						Entry e = new Entry(mapAllValuedTempParas.get(tempId).get(paraId).getParaName(), paraId);
						lstParas.add(e);
					}
				}
				// Setting Desk Menu Wise
				//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP, mapChartTempParas);
				Map mpDeskMenuChartTempParas = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
				if(mpDeskMenuChartTempParas==null) mpDeskMenuChartTempParas = new HashMap();
				mpDeskMenuChartTempParas.put(_fb.getSelectedMenuId(), mapChartTempParas);
				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP, mpDeskMenuChartTempParas);

				// Getting Visit Dates
				PatientClinicalDetailVO _patCliDtlVO = new PatientClinicalDetailVO();
				HelperMethods.populate(_patCliDtlVO, _fb);
				lstReportDates = GenericTemplateTileDATA.getReportDateList(_fb.getDeskType(), _patCliDtlVO, userVO);//----
				if (lstReportDates == null) lstReportDates = new ArrayList<Entry>();
				// Setting Record Dates
				LinkedHashMap<String, String> mapRecordDates = new LinkedHashMap<String, String>();
				for (Entry e : lstReportDates)
					mapRecordDates.put(e.getValue(), e.getLabel());

				// Map of RecordDate/Map of Temp Id/Map of Parameter Id/Para Value
				Map<String, Map<String, Map<String, String>>> mpChartClinicalData = null;
				PatientClinicalDetailVO patCliniDtlVO = new PatientClinicalDetailVO();
				HelperMethods.populate(patCliniDtlVO, _fb);
				patCliniDtlVO.setDeskMenuId(deskMenuId);
				mpChartClinicalData = GenericTemplateTileDATA.getPatientChartClinicalDataTempWise(_fb.getDeskType(), patCliniDtlVO, lstReportDates,
						lstSelectedTemps, userVO);//----

				// Remove Report Dates that don't have Data
				boolean dataFoundFlag = false;
				List<String> lstRemove = new ArrayList<String>();
				for (String recordDate : mapRecordDates.keySet())
				{
					Map<String, Map<String, String>> mpTemp = mpChartClinicalData.get(recordDate);
					if (mpTemp == null)
					{
						lstRemove.add(recordDate);
						continue;
					}
					// Removing Not Useful Templates Data
					List<String> lstRemoveTemplates = new ArrayList<String>();
					for (String key : mpTemp.keySet())
					{
						boolean flag = false;
						for (Entry entTemp : lstSelectedTemps)
							if (entTemp.getValue().split("#")[0].equals(key))
							{
								flag = true;
								break;
							}
						if (!flag) lstRemoveTemplates.add(key);
						else if (mpTemp.get(key).size() == 0) lstRemoveTemplates.add(key);
					}
					for (String tempId : lstRemoveTemplates)
						mpTemp.remove(tempId);

					// Checking For Record Date Data
					if (mpTemp.keySet().size() == 0)
					{
						mpChartClinicalData.remove(recordDate);
						lstRemove.add(recordDate);
					}
					else dataFoundFlag = true;
				}
				List<Entry> lstRemoveEntry = new ArrayList<Entry>();
				for (String str : lstRemove)
				{
					mapRecordDates.remove(str);
					for (Entry entObj : lstReportDates)
						if (entObj.getValue().equals(str))
						{
							lstRemoveEntry.add(entObj);
							break;
						}
				}
				for (Entry entObj : lstRemoveEntry)
					lstReportDates.remove(entObj);

				// Removing Non-data Selected Parameters in Report Parameter Wise
				// if (_fb.getReportMode().equals(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_PARAMETER_WISE))
				// {
				dataFoundFlag = false;
				
				// Getting Desk Menu Wise 
				//LinkedHashMap<String, List<Entry>> mapChartTempParas1 = (LinkedHashMap<String, List<Entry>>) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
				Map mpDeskMenuChartTempParas1 = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
				LinkedHashMap<String, List<Entry>> mapChartTempParas1 = (LinkedHashMap<String, List<Entry>>)mpDeskMenuChartTempParas1.get(_fb.getSelectedMenuId());

				for (String tempId : mapChartTempParas1.keySet())
				{
					List<Entry> removePara = new ArrayList<Entry>();
					for (Entry entObj : mapChartTempParas1.get(tempId))
					{
						boolean flag = false;
						// Map of RecordDate/Map of Temp Id/Map of Parameter Id/Para Value
						for (String recordDate : mpChartClinicalData.keySet())
							for (String templateId : mpChartClinicalData.get(recordDate).keySet())
								if (templateId.equals(tempId)) for (String paraId : mpChartClinicalData.get(recordDate).get(templateId).keySet())
									if (paraId.equals(entObj.getValue()))
									{
										dataFoundFlag = true;
										flag = true;
									}
						if (!flag) removePara.add(entObj);
					}
					for (Entry entObj : removePara)
						mapChartTempParas1.get(tempId).remove(entObj);
				}
				// mpChartClinicalData
				// }

				
				// Setting Desk Menu Wise
				//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP, mapRecordDates);
				Map mpDeskMenuRecordDates = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP);
				if(mpDeskMenuRecordDates==null) mpDeskMenuRecordDates = new HashMap();
				mpDeskMenuRecordDates.put(_fb.getSelectedMenuId(), mapRecordDates);
				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP, mpDeskMenuRecordDates);

				// }
				// Setting Desk Menu Wise
				//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpChartClinicalData);
				Map mpDeskMenuChartClinicalData = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);
				if(mpDeskMenuChartClinicalData==null) mpDeskMenuChartClinicalData = new HashMap();
				mpDeskMenuChartClinicalData.put(_fb.getSelectedMenuId(), mpChartClinicalData);
				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpDeskMenuChartClinicalData);

				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_VIEW_MODE, GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_TEMPLATE_DESK_ID, deskMenuId);

				if (!dataFoundFlag)// &&
				// _fb.getReportMode().equals(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_PARAMETER_WISE))
				{
					throw new HisRecordNotFoundException("No Data Found");
				}

				proforma.setComplaintsProforma(deskMenuId);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);

				_fb.setReportMode(recordView);
				// Setting Desk Menu Wise Report View
				Map mpDeskMenuReportModes = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP); 
				if(mpDeskMenuReportModes==null)	mpDeskMenuReportModes = new HashMap();
				mpDeskMenuReportModes.put(_fb.getSelectedMenuId(), recordViews);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP, mpDeskMenuReportModes);

			}*/

			// Profile OT Detail

			ProfileOTDtlVO[] profileOTDtlVOs = (ProfileOTDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_OT_DTL_VO_ARRAY);
			if (profileOTDtlVOs != null && profileOTDtlVOs.length > 0)
			{
				String[] str = new String[profileOTDtlVOs.length];
				for (int i = 0; i < profileOTDtlVOs.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);

				ProfileOTDetailVO[] _profileOTDetailVOs = new ProfileOTDetailVO[profileOTDtlVOs.length];
				for (int i = 0; i < profileOTDtlVOs.length; i++)
				{
					_profileOTDetailVOs[i] = new ProfileOTDetailVO();
					HelperMethods.populate(_profileOTDetailVOs[i], profileOTDtlVOs[i]);
				}

				for (int i = 0; i < profileOTDtlVOs.length; i++)
				{
					ProfileOTDetailVO pDetailVO = (ProfileOTDetailVO) _profileOTDetailVOs[i];
					profileOTList.add(pDetailVO);
				}
				templateDetailsMap = GenericPatientProfileDATA.getPatientProfileOperationTemplateDetails(profileOTList, _fb.getPatCrNo(), userVO);
				templateListAll = (List) templateDetailsMap.get(OpdConfig.OPERATION_TEMPLATE_LIST_ALL);
				paraIdValueListAll = (List) templateDetailsMap.get(OpdConfig.OPERATION_TEMPLATE_PARA_ID_VALUE_ALL);

				// (0) template Id
				// (1) parameter ID
				// (2) parameter value
				// (3) status code
				Iterator templateListAllItr = templateListAll.iterator();
				Iterator paraIdValueListAllItr = paraIdValueListAll.iterator();
				while ((templateListAllItr.hasNext()) && (paraIdValueListAllItr.hasNext()))
				{
					templateList = (List) templateListAllItr.next();
					paraIdValueList = (List) paraIdValueListAllItr.next();
					Iterator templateListIterator = templateList.iterator();
					Map mapAll = new HashMap();
					while (templateListIterator.hasNext())
					{
						String templateId = (String) templateListIterator.next();
						Map map = new HashMap();
						Iterator paraIdValueListIterator = paraIdValueList.iterator();
						while (paraIdValueListIterator.hasNext())
						{
							List list = (List) paraIdValueListIterator.next();
							if (templateId.equals((String) list.get(0)))
							{
								map.put(list.get(1), list.get(2));
							}
						}
						mapAll.put(templateId, map);

					}
					mapAllList.add(mapAll);
				}

				WebUTIL.setAttributeInSession(_rq, OpdConfig.OPERATION_TEMPLATE_MAP_ALL_LIST, mapAllList);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.OPERATION_TEMPLATE_LIST_ALL, templateListAll);

				// to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("OTVIEWING"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}

				proforma.setOperationDetailProforma(_fb.getSelectedMenuId(), _profileOTDetailVOs, _fb.getFetchSelectedRow());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}

			// Profile Image Detail

			ProfileImageDtlVO[] profileImageDtlVO = (ProfileImageDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_IMAGE_DTL_VO_ARRAY);
			if (profileImageDtlVO != null && profileImageDtlVO.length > 0)
			{
				String[] str = new String[profileImageDtlVO.length];
				for (int i = 0; i < profileImageDtlVO.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);

				OpdPatientImageDtlVO[] patImageDtlVOs = new OpdPatientImageDtlVO[profileImageDtlVO.length];
				for (int i = 0; i < profileImageDtlVO.length; i++)
				{
					patImageDtlVOs[i] = new OpdPatientImageDtlVO();
					HelperMethods.populate(patImageDtlVOs[i], profileImageDtlVO[i]);
				}

				// to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("IMAGEEXAMINATION"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}

				proforma.setExamImagesProforma(_fb.getSelectedMenuId(), patImageDtlVOs, _fb.getFetchSelectedRow());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}

			// Profile Footer Detail
			ProfileFooterDtlVO[] profileFooterDetailVO = (ProfileFooterDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_FOOTER_DTL_VO_ARRAY);
			if (profileFooterDetailVO != null && profileFooterDetailVO.length > 0)
			{
				// to get fetch selected row
				String[] str = new String[profileFooterDetailVO.length];
				for (int i = 0; i < profileFooterDetailVO.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);

				// to get episode diagnosis vo
				// DisclaimerMstVO[] disclaimerMstVOs=new DisclaimerMstVO[profileFooterDetailVO.length];
				for (int i = 0; i < profileFooterDetailVO.length; i++)
				{
					// disclaimerMstVOs=new DisclaimerMstVO();
					HelperMethods.populate(_disclaimerMstVOs, profileFooterDetailVO[i]);
					remarks = profileFooterDetailVO[i].getProfileSummary();
					reviewDate = profileFooterDetailVO[i].getReviewDate();
					profileType = profileFooterDetailVO[i].getProfileType();
					dischargeType = profileFooterDetailVO[i].getDischargeType() + "#" + profileFooterDetailVO[i].getDischargeTypeName();
					dischargeAdvisedBy = profileFooterDetailVO[i].getDischargeAdvisedBy() + "#" + profileFooterDetailVO[i].getDischargeAdvisedByName();
					dischargeAdvisedDept = profileFooterDetailVO[i].getDischargeAdvisedDept() + "#" + profileFooterDetailVO[i].getDischargeAdvisedDeptName();
					_fb.setRemarks(remarks);
					_fb.setProfileType(profileType);
					_fb.setReviewDate(reviewDate);
					_fb.setDischargeType(profileFooterDetailVO[i].getDischargeType());
					_fb.setDischargeAdvisedBy(profileFooterDetailVO[i].getDischargeAdvisedBy());
					_fb.setDischargeAdvisedDept(profileFooterDetailVO[i].getDischargeAdvisedDept());
					_fb.setDischargeTypeName(profileFooterDetailVO[i].getDischargeTypeName());
					_fb.setDischargeAdvisedByName(profileFooterDetailVO[i].getDischargeAdvisedByName());
					_fb.setDischargeAdvisedDeptName(profileFooterDetailVO[i].getDischargeAdvisedDeptName());
					_fb.setSnomdCIdRemarks(profileFooterDetailVO[i].getSnomdCIdRemarks());
					_fb.setSnomdPTRemarks(profileFooterDetailVO[i].getSnomdPTRemarks());

				}

				// to get selected menu id
				// _fb.setSelectedMenuId("99999");

				// to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("PROFILEFOOTER"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
					// Discharge Profile Footer Detail
					if (_deskMenuMasterVO.getDeskUrl().equals("DISCHARGEPROFILEFOOTER"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}

				/*
				 * Iterator itr=lstProMenus.iterator(); while(itr.hasNext()) { DeskMenuMasterVO
				 * _deskMenuMasterVO=(DeskMenuMasterVO)itr.next();
				 * 
				 * //if(_deskMenuMasterVO.getDeskUrl().equals("PROFILEFOOTER")) //{
				 * 
				 * //} }
				 */

				proforma.setProfileFooterDetailProforma(_fb.getSelectedMenuId(), _fb.getRemarks(), _fb.getReviewDate(), _fb.getDischargeType(), _fb.getDischargeTypeName(),
						_fb.getDischargeAdvisedBy(), _fb.getDischargeAdvisedByName(), _fb.getDischargeAdvisedDept(), _fb.getDischargeAdvisedDeptName(), profileType, _disclaimerMstVOs,_fb.getSnomdCIdRemarks(),_fb.getSnomdPTRemarks());

				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}

			// Profile Progress Notes Detail

			ProfileProgressNotesDtlVO[] profileProgNotesDtlVO = (ProfileProgressNotesDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROGRESS_NOTES_DTL_VO_ARRAY);
			if (profileProgNotesDtlVO != null && profileProgNotesDtlVO.length > 0)
			{
				String[] str = new String[profileProgNotesDtlVO.length];
				for (int i = 0; i < profileProgNotesDtlVO.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);

				DoctorRoundDtlVO[] patProgNotesDtlVOs = new DoctorRoundDtlVO[profileProgNotesDtlVO.length];
				for (int i = 0; i < profileProgNotesDtlVO.length; i++)
				{
					patProgNotesDtlVOs[i] = new DoctorRoundDtlVO();
					HelperMethods.populate(patProgNotesDtlVOs[i], profileProgNotesDtlVO[i]);
				}

				// to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("DOCTORROUND"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}

				proforma.setEpiProgressNotesDetailProforma(_fb.getSelectedMenuId(), patProgNotesDtlVOs, _fb.getFetchSelectedRow());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}

			// Profile External Investigation Detail

			ProfileExtExamDtlVO[] profileExtExamDtlVO = (ProfileExtExamDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_EXT_INV_DTL_VO_ARRAY);
			if (profileExtExamDtlVO != null && profileExtExamDtlVO.length > 0)
			{
				String[] str = new String[profileExtExamDtlVO.length];
				for (int i = 0; i < profileExtExamDtlVO.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);

				EpisodeExtInvDtlVO[] patExtInvDtlVOs = new EpisodeExtInvDtlVO[profileExtExamDtlVO.length];
				for (int i = 0; i < profileExtExamDtlVO.length; i++)
				{
					patExtInvDtlVOs[i] = new EpisodeExtInvDtlVO();
					HelperMethods.populate(patExtInvDtlVOs[i], profileExtExamDtlVO[i]);
				}

				// to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("EXTERNALEXAMINATION"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}

				proforma.setExtInvestigationProforma(_fb.getSelectedMenuId(), patExtInvDtlVOs, _fb.getFetchSelectedRow());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}
			
			objStatus.add(Status.NEW);
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	public static boolean setProfileOptionsNewList(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		boolean flag = true;
		Status objStatus = new Status();
		HttpSession session = null;
		List<DeskMenuMasterVO> lstProMenus = null;
		try
		{
			UserVO userVO = getUserVO(_rq);
			session = _rq.getSession();
			// setSysdate(_rq);
			// String profileType=_fb.getProfileType().split("#")[0];
			String profileType = _fb.getProfileType();

			if (_fb.getProfileType() != null)
			{
				if (_fb.getProfileType().equals(OpdConfig.PROFILE_TYPE_DISCHARGE))
				{
					_fb.setDischargeModifyFlag(OpdConfig.PAT_PROFILE_DTL_DISCHARGE_MODIFY_FLAG_YES);
				}
			}
			// Getting Profile Based Desk Menus
			if (session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST) == null)
			{
				// Initialising Profile with Header and Footer
				// PatientDetailVO patVO = (PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);

				lstProMenus = GenericPatientProfileDATA.getProfileBasedDeskMenus(profileType, _fb.getDeskId(), userVO);

				/*
				 * if(_fb.getProfileType()!=null) { if(_fb.getProfileType().equals(OpdConfig.PROFILE_TYPE_DISCHARGE)) {
				 * DeskMenuMasterVO footerVO1 = new DeskMenuMasterVO(); footerVO1.setDeskMenuId("99998");
				 * footerVO1.setDeskMenuName("Advice On Discharge"); footerVO1.setDeskUrl("ADVICEONDISCHARGE");
				 * lstProMenus.add(footerVO1); } }
				 * 
				 * DeskMenuMasterVO footerVO = new DeskMenuMasterVO(); footerVO.setDeskMenuId("99999");
				 * footerVO.setDeskMenuName("Profile Footer"); footerVO.setDeskUrl("PROFILEFOOTER");
				 * lstProMenus.add(footerVO);
				 */

				Map<String, String> mapMenuURL = new HashMap<String, String>();
				for (DeskMenuMasterVO vo : lstProMenus)
					mapMenuURL.put(vo.getDeskMenuId(), vo.getDeskUrl());
				// proforma.setOptionsURLMap(mapMenuURL);

				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST, lstProMenus);
				if (lstProMenus.size() == 0) throw new HisRecordNotFoundException("No Profile Related Options in this Desk... ");
			}

			List<ProfileTypeMstVO> profileTypeVOList = (List) session.getAttribute(OpdConfig.PROFILE_TYPE_VO_LIST);
			for (int i = 0; i < profileTypeVOList.size(); i++)
			{
				if (profileTypeVOList.get(i).getProfileType().equals(profileType))
				{
					_fb.setIsDesclaimerRequired(profileTypeVOList.get(i).getIsDesclaimerRequired());
					break;
				}
			}

			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	public static boolean saveProfileHeader(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		boolean flag = true;
		Status objStatus = new Status();
		// HttpSession session = WebUTIL.getSession(_rq);
		// List diagnosisTypeList=new ArrayList();
		// String resultHtml;
		// String url="";
		// Map inAllMap=new HashMap();
		// Map inAllPreviousMap=new HashMap();
		PatientProfileDetailVO patProfileDtlVO = null;
		try
		{
			UserVO userVO = getUserVO(_rq);

			// resultHtml = _fb.getProfileHTML(); //proforma.getFinalProfileHtmlCode();
			patProfileDtlVO = new PatientProfileDetailVO();
			HelperMethods.populate(patProfileDtlVO, _fb);

			// patProfileDtlVO.setProfileData(resultHtml);
			GenericPatientProfileDATA.updateProfileHeaderDetail(patProfileDtlVO, userVO);
			// objStatus.add(Status.NEW, "", "Profile Genrated Successfully");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	/**
	 * generate the profile
	 */
	public static boolean profileGeneration(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		boolean flag = true;
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		// List diagnosisTypeList=new ArrayList();
		String resultHtml;
		// String url="";
		// Map inAllMap=new HashMap();
		// Map inAllPreviousMap=new HashMap();
		List<PatientProfileDetailVO> lstPrevProfiles = null;
		PatientProfileDetailVO patProfileDtlVO = null;
		try
		{
			UserVO userVO = getUserVO(_rq);
			// session = _rq.getSession();
			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			ProfileGroupDtlVO[] profileGroupDtlVO = (ProfileGroupDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_ACCESS_POLICY_VO_ARRAY);
			ProfileAccessPolicyVO profileAccessPolicy = (ProfileAccessPolicyVO) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_ACCESS_POLICY_DETAIL_VO);

			lstPrevProfiles = (List<PatientProfileDetailVO>) session.getAttribute(OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST);

			for (int i = 0; i < _fb.getSelectedIndex().length; i++)
			{
				int index = Integer.parseInt(_fb.getSelectedIndex()[i]);
				patProfileDtlVO = lstPrevProfiles.get(index);
			}
			
			String base64Data =_fb.getProfileHTML();//by Vasu on 05.March.18 for base64 decoding
			System.out.println(base64Data);
			resultHtml = new String(DatatypeConverter.parseBase64Binary(base64Data));
			//resultHtml = _fb.getProfileHTML(); // proforma.getFinalProfileHtmlCode();
			System.out.println("***********get profile type***********"+proforma.getProfileType());
			patProfileDtlVO.setProfileType(proforma.getProfileType());
			patProfileDtlVO.setProfileData(resultHtml);    
			//Added by Vasu on 14.Nov.2017 to convert HTML data To PDF
			ByteArrayOutputStream baos = HTMLToPDFUTIL.convertHtmlToPDFDirect(_rq, resultHtml);
			byte[] b = baos.toByteArray(); 
			patProfileDtlVO.setProfileDataPdf(b);
			System.out.println("lookout tag Vasu "+b.length);

			if (profileAccessPolicy!=null && profileAccessPolicy.getAccessType() != null)
			{
				if (!profileAccessPolicy.getAccessType().equals(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_TO_ALL))
				{
					if (profileGroupDtlVO == null || profileGroupDtlVO.length == 0)
					{
						objStatus.add(Status.DONE, "", "No Default Access Policy Found");
					}
					else
					{
						GenericPatientProfileDATA.profileGeneration(patProfileDtlVO, profileGroupDtlVO, profileAccessPolicy, userVO);
						objStatus.add(Status.DONE, "", "Profile Generated Successfully");
					}
				}
				else
				{
					GenericPatientProfileDATA.profileGeneration(patProfileDtlVO, profileGroupDtlVO, profileAccessPolicy, userVO);
					objStatus.add(Status.DONE, "", "Profile Generated Successfully");
				}

			}
			else
			{
				GenericPatientProfileDATA.profileGeneration(patProfileDtlVO, profileGroupDtlVO, profileAccessPolicy, userVO);
				objStatus.add(Status.DONE, "", "Profile Generated Successfully");
				objStatus.add(Status.FAILURE, "", "No Default Access Policy Found");
			}
			// objStatus.add(Status.NEW, "", "Profile Genrated Successfully");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	public static void fetchDetailsForGenerate(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		// boolean flag = true;
		Status objStatus = new Status();
		HttpSession session = null;
		Map essentialMap = null;
		List<PatientProfileDetailVO> lstPrevProfiles = null;
		// List<PatientProfileDetailVO> lstDeletingProfiles = new ArrayList<PatientProfileDetailVO>();
		PatientProfileDetailVO patProfileDtlVO = null;
		List<DeskMenuMasterVO> lstProMenus = null;
		DisclaimerMstVO _disclaimerMstVOs = new DisclaimerMstVO();
		List lstDietType = null;
		List lstDrugRoutes = null;
		List profileOTList = new ArrayList();
		Map templateDetailsMap = null;
		List templateList = null;
		List paraIdValueList = null;
		List templateListAll = null;
		List paraIdValueListAll = null;
		List mapAllList = new ArrayList();
		List<Entry> lstReportDates = new ArrayList<Entry>();
		List<Entry> lstSelectedTemps = new ArrayList<Entry>();
		List<Entry> lstTemps = null;
		List<Entry> lstVisitDates = null;
		Map<String, TemplateMasterVO> mapTemplateDtl = null;
		List<TemplateParameterMasterVO> lstAllTempParas = null;
		LinkedHashMap<String, LinkedHashMap<String, String>> mapAllTempParas = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		LinkedHashMap<String, LinkedHashMap<String, TemplateParameterMasterVO>> mapAllValuedTempParas = new LinkedHashMap<String, LinkedHashMap<String, TemplateParameterMasterVO>>();
		String remarks = "";
		String reviewDate = "" , dischargeType="", dischargeAdvisedBy="", dischargeAdvisedDept="";
		String profileType = "";
		try
		{
			UserVO userVO = getUserVO(_rq);
			session = _rq.getSession();
			lstPrevProfiles = (List<PatientProfileDetailVO>) session.getAttribute(OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST);

			for (int i = 0; i < _fb.getSelectedIndex().length; i++)
			{
				int index = Integer.parseInt(_fb.getSelectedIndex()[i]);
				patProfileDtlVO = lstPrevProfiles.get(index);
				_fb.setProfileId(patProfileDtlVO.getProfileId());
				_fb.setSerialNo(patProfileDtlVO.getSerialNo());
				_fb.setProfileHeader(patProfileDtlVO.getProfileHeader());
				_fb.setRemarks(patProfileDtlVO.getRemarks());
				_fb.setProfileType(patProfileDtlVO.getProfileType());
			}

			PatientProfileDetailVO _patientProfileDtlVO = new PatientProfileDetailVO();
			_patientProfileDtlVO.setProfileId(_fb.getProfileId());
			_patientProfileDtlVO.setProfileType(_fb.getProfileType());
			_patientProfileDtlVO.setPatCategoryCode(_fb.getPatCategoryCode());
			_patientProfileDtlVO.setPatCrNo(_fb.getPatCrNo());
			_patientProfileDtlVO.setEpisodeCode(_fb.getEpisodeCode());
			_patientProfileDtlVO.setDepartmentUnitCode(_fb.getDepartmentUnitCode());

			essentialMap = GenericPatientProfileDATA.fetchDetailsForGenerate(_patientProfileDtlVO,_fb.getDeskType(), userVO);
			WebUTIL.setMapInSession(essentialMap, _rq);

			lstDrugRoutes = (List) session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE);
			lstDietType = (List) session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_ALL_DIET_TYPE_LIST);

			// to set profile options
			if (session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST) == null)
			{
				// Initialising Profile with Header and Footer
				// PatientDetailVO patVO = (PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);

				lstProMenus = GenericPatientProfileDATA.getProfileBasedDeskMenus(_fb.getProfileType(), _fb.getDeskId(), userVO);

				/*
				 * if(_fb.getProfileType()!=null) { if(_fb.getProfileType().equals(OpdConfig.PROFILE_TYPE_DISCHARGE)) {
				 * DeskMenuMasterVO footerVO1 = new DeskMenuMasterVO(); footerVO1.setDeskMenuId("99998");
				 * footerVO1.setDeskMenuName("Advice On Discharge"); footerVO1.setDeskUrl("ADVICEONDISCHARGE");
				 * lstProMenus.add(footerVO1); } }
				 * 
				 * DeskMenuMasterVO footerVO = new DeskMenuMasterVO(); footerVO.setDeskMenuId("99999");
				 * footerVO.setDeskMenuName("Profile Footer"); footerVO.setDeskUrl("PROFILEFOOTER");
				 * lstProMenus.add(footerVO);
				 */

				Map<String, String> mapMenuURL = new HashMap<String, String>();
				for (DeskMenuMasterVO vo : lstProMenus)
					mapMenuURL.put(vo.getDeskMenuId(), vo.getDeskUrl());
				// proforma.setOptionsURLMap(mapMenuURL);

				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST, lstProMenus);
				if (lstProMenus.size() == 0) throw new HisRecordNotFoundException("No Profile Related Options in this Desk... ");
			}
			else
			{
				lstProMenus = (List) session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST);
			}
			// to set profile header

			ProfileProforma proforma = new ProfileProforma();
			PatientDetailVO patVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);

			proforma.setHeader(_fb.getProfileHeader(), _fb.getRemarks(), patVO, userVO);
			proforma.setProfileType(_fb.getProfileType());
			List profileTypeList = (List) session.getAttribute(OpdConfig.PROFILE_TYPE_LIST);
			Iterator iteratorProfileTypeList = profileTypeList.iterator();
			while (iteratorProfileTypeList.hasNext())
			{
				Entry entry = (Entry) iteratorProfileTypeList.next();
				if ((_fb.getProfileType() != null) && (_fb.getProfileType().equals(entry.getValue())))
				{
					proforma.setProfileTypeDesc(entry.getLabel());
					break;
				}
			}
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			// Profile Diagnosis Detail
			ProfileDiagnosisDtlVO[] profileDiagnosisDtlVO = (ProfileDiagnosisDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DIAGNOSIS_DTL_VO_ARRAY);
			if (profileDiagnosisDtlVO != null && profileDiagnosisDtlVO.length > 0)
			{
				// //////// to get fetch selected row
				String[] str = new String[profileDiagnosisDtlVO.length];
				for (int i = 0; i < profileDiagnosisDtlVO.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);

				// ////////to get episode diagnosis vo
				EpisodeDiagnosisVO[] episodeDiagnosisVOs = new EpisodeDiagnosisVO[profileDiagnosisDtlVO.length];
				for (int i = 0; i < profileDiagnosisDtlVO.length; i++)
				{
					episodeDiagnosisVOs[i] = new EpisodeDiagnosisVO();
					HelperMethods.populate(episodeDiagnosisVOs[i], profileDiagnosisDtlVO[i]);
				}

				/*
				 * PatientDetailVO patVO = (PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
				 * 
				 * proforma.setHeader(_fb.getProfileHeader(),_fb.getRemarks(), patVO, userVO);
				 * proforma.setProfileType(_fb.getProfileType()); List profileTypeList
				 * =(List)session.getAttribute(OpdConfig.PROFILE_TYPE_LIST); Iterator
				 * iteratorProfileTypeList=profileTypeList.iterator(); while(iteratorProfileTypeList.hasNext()) { Entry
				 * entry=(Entry)iteratorProfileTypeList.next(); if( (_fb.getProfileType()!=null) &&
				 * (_fb.getProfileType().equals(entry.getValue()))) { proforma.setProfileTypeDesc(entry.getLabel()); break; } }
				 * WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
				 */

				// /////////to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("DESKDIAGNOSIS"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}

				proforma.setDiagnosisProforma(_fb.getSelectedMenuId(), episodeDiagnosisVOs, _fb.getFetchSelectedRow());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}

			// Profile Allergy Detail

			ProfileAllergyDtlVO[] profileAllergyDtlVO = (ProfileAllergyDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_ALLERGY_DTL_VO_ARRAY);
			if (profileAllergyDtlVO != null && profileAllergyDtlVO.length > 0)
			{
				String[] str = new String[profileAllergyDtlVO.length];
				for (int i = 0; i < profileAllergyDtlVO.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);

				PatAllergyDtlVO[] patAllergyDtlVOs = new PatAllergyDtlVO[profileAllergyDtlVO.length];
				for (int i = 0; i < profileAllergyDtlVO.length; i++)
				{
					patAllergyDtlVOs[i] = new PatAllergyDtlVO();
					HelperMethods.populate(patAllergyDtlVOs[i], profileAllergyDtlVO[i]);
				}

				/*
				 * PatientDetailVO patVO = (PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
				 * proforma.setHeader(_fb.getProfileHeader(),_fb.getRemarks(), patVO, userVO);
				 * proforma.setProfileType(_fb.getProfileType()); List profileTypeList
				 * =(List)session.getAttribute(OpdConfig.PROFILE_TYPE_LIST); Iterator
				 * iteratorProfileTypeList=profileTypeList.iterator(); while(iteratorProfileTypeList.hasNext()) { Entry
				 * entry=(Entry)iteratorProfileTypeList.next(); if( (_fb.getProfileType()!=null) &&
				 * (_fb.getProfileType().equals(entry.getValue()))) { proforma.setProfileTypeDesc(entry.getLabel()); break; } }
				 * WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
				 */

				// to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("DESKALLERGIES"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}

				proforma.setAllergiesProforma(_fb.getSelectedMenuId(), patAllergyDtlVOs, _fb.getFetchSelectedRow());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}

			// Profile Investigation Detail

			ProfileInvDtlVO[] profileInvDtlVO = (ProfileInvDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_INVESTIGATION_DTL_VO_ARRAY);
			if (profileInvDtlVO != null && profileInvDtlVO.length > 0)
			{
				String[] str = new String[profileInvDtlVO.length];
				for (int i = 0; i < profileInvDtlVO.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);

				ProfileInvestigationVO[] profileInvestigationVOs = new ProfileInvestigationVO[profileInvDtlVO.length];
				for (int i = 0; i < profileInvDtlVO.length; i++)
				{
					profileInvestigationVOs[i] = new ProfileInvestigationVO();
					//profileInvestigationVOs[i].setVisitDate(profileInvDtlVO[i].getVisitDate());
					HelperMethods.populate(profileInvestigationVOs[i], profileInvDtlVO[i]);
				}

				// to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("RESULTVIEWING"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_INVESTIGATION_PROFILE, profileInvestigationVOs);
				proforma.setInvestigationProforma(_fb.getSelectedMenuId(), profileInvestigationVOs, _fb.getFetchSelectedRow());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}

			// Profile Treatment Detail
			ProfileDrugAdviceDtlVO[] profileDrugAdviceDtlVO = (ProfileDrugAdviceDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_TREATMENT_DTL_VO_ARRAY);
			if (profileDrugAdviceDtlVO != null && profileDrugAdviceDtlVO.length > 0)
			{
				// to get fetch selected row
				String[] str = new String[profileDrugAdviceDtlVO.length];
				for (int i = 0; i < profileDrugAdviceDtlVO.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);

				// to get episode diagnosis vo
				PatDrugTreatmentDetailVO[] patDrugTreatmentDetailVOs = new PatDrugTreatmentDetailVO[profileDrugAdviceDtlVO.length];
				for (int i = 0; i < patDrugTreatmentDetailVOs.length; i++)
				{
					patDrugTreatmentDetailVOs[i] = new PatDrugTreatmentDetailVO();
					HelperMethods.populate(patDrugTreatmentDetailVOs[i], profileDrugAdviceDtlVO[i]);
				}

				// to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("DESKTREATMENTDETAIL"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}

				proforma.setTreatmentProforma(_fb.getSelectedMenuId(), patDrugTreatmentDetailVOs, _fb.getFetchSelectedRow());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}

			// Profile Alerts Detail

			ProfileAlertsDtlVO[] profileAlertsDtlVO = (ProfileAlertsDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_ALERTS_DTL_VO_ARRAY);
			if (profileAlertsDtlVO != null && profileAlertsDtlVO.length > 0)
			{
				String[] str = new String[profileAlertsDtlVO.length];
				for (int i = 0; i < profileAlertsDtlVO.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);

				PatientAlertsDetailVO[] patAlertsDetailVos = new PatientAlertsDetailVO[profileAlertsDtlVO.length];
				for (int i = 0; i < profileAlertsDtlVO.length; i++)
				{
					patAlertsDetailVos[i] = new PatientAlertsDetailVO();
					HelperMethods.populate(patAlertsDetailVos[i], profileAlertsDtlVO[i]);
				}

				// to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("GENERICPATIENTALERTS"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}

				proforma.setAlertsProforma(_fb.getSelectedMenuId(), patAlertsDetailVos, _fb.getFetchSelectedRow());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}

			// //Advice On Discharge
			ProfileDrugAdviceDtlVO[] _profileDrugAdviceDtlVO = (ProfileDrugAdviceDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_DRUG_DTL_VO_ARRAY);
			ProfileDietAdviceDtlVO profileDietAdviceDtlVO = (ProfileDietAdviceDtlVO) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_DIET_ADVICE_DTL_VO);
			ProfileRestAdviceDtlVO profileRestAdviceDtlVO = (ProfileRestAdviceDtlVO) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_REST_ADVICE_DTL_VO);
			ProfileExtTreatmentDtlVO[] _profileExtTreatmentDtlVO = (ProfileExtTreatmentDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_EXT_TREATMENT_DTL_VO_ARRAY);
			if ((_profileDrugAdviceDtlVO != null && _profileDrugAdviceDtlVO.length > 0) || (profileDietAdviceDtlVO != null)
					|| (profileRestAdviceDtlVO != null) || (_profileExtTreatmentDtlVO != null && _profileExtTreatmentDtlVO.length > 0))
			{
				PatDietAdviceDetailVO _patDietDetailVO = null;
				RestAdviceDtlVO _restAdviceDtlVO = null;
				// to get Pat Drug Treatment Vo
				PatDrugTreatmentDetailVO[] _patDrugTreatmentDetailVOs = new PatDrugTreatmentDetailVO[_profileDrugAdviceDtlVO.length];
				for (int i = 0; i < _patDrugTreatmentDetailVOs.length; i++)
				{
					_patDrugTreatmentDetailVOs[i] = new PatDrugTreatmentDetailVO();

					HelperMethods.populate(_patDrugTreatmentDetailVOs[i], _profileDrugAdviceDtlVO[i]);

					if (_patDrugTreatmentDetailVOs[i].getIsEmptyStomach().equals("0"))
					{
						_patDrugTreatmentDetailVOs[i].setIsEmptyStomachName("No");
					}
					else if (_patDrugTreatmentDetailVOs[i].getIsEmptyStomach().equals("1"))
					{
						_patDrugTreatmentDetailVOs[i].setIsEmptyStomachName("Yes");
					}
					if (lstDrugRoutes != null)
					{
						Iterator itr = lstDrugRoutes.iterator();
						while (itr.hasNext())
						{
							DrugRouteMstVO _drugRouteMstVO = (DrugRouteMstVO) itr.next();
							if (_patDrugTreatmentDetailVOs[i].getDrugRouteId()!=null && _patDrugTreatmentDetailVOs[i].getDrugRouteId().equals(_drugRouteMstVO.getDrugRouteId()))
							{
								_patDrugTreatmentDetailVOs[i].setDrugRouteName(_drugRouteMstVO.getDrugRouteName());
							}
						}
					}
				}

				// to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("ADVICEONDISCHARGE"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
					// if(_deskMenuMasterVO.getDeskUrl().equals("PROFILEFOOTER"))
					// {
					// _fb.setSelectedMenuId("99998");
					// }
				}

				if (profileDietAdviceDtlVO != null)
				{
					_patDietDetailVO = new PatDietAdviceDetailVO();
					HelperMethods.populate(_patDietDetailVO, profileDietAdviceDtlVO);
					if (lstDietType != null)
					{
						Iterator iterator = lstDietType.iterator();
						while (iterator.hasNext())
						{
							Entry entry = (Entry) iterator.next();

							if (_patDietDetailVO.getDietTypeCode().equals(entry.getValue()))
							{
								_patDietDetailVO.setDietTypeDesc(entry.getLabel());
							}
						}
					}
				}
				if (profileRestAdviceDtlVO != null)
				{
					_restAdviceDtlVO = new RestAdviceDtlVO();
					HelperMethods.populate(_restAdviceDtlVO, profileRestAdviceDtlVO);
				}

				PatExtTreatmentDetailVO[] _patExtTreatmentDetailVO = new PatExtTreatmentDetailVO[_profileExtTreatmentDtlVO.length];
				for (int i = 0; i < _patExtTreatmentDetailVO.length; i++)
				{
					_patExtTreatmentDetailVO[i] = new PatExtTreatmentDetailVO();

					HelperMethods.populate(_patExtTreatmentDetailVO[i], _profileExtTreatmentDtlVO[i]);
				}

				List selOtherInstList = new ArrayList();
				List selActivityList = new ArrayList();
				proforma.setAdviceOnDischargeProforma(_fb.getSelectedMenuId(), _patDrugTreatmentDetailVOs, _patDietDetailVO, _restAdviceDtlVO,
						_patExtTreatmentDetailVO, selOtherInstList, selActivityList);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);

			}

			// Final Profile Complaints Detail 
			ProfileClinicalDtlVO[] profileClinicalDetailVO = (ProfileClinicalDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_COMPLAINTS_DTL_VO_ARRAY);
			if (profileClinicalDetailVO != null && profileClinicalDetailVO.length > 0)
			{
				for (int i = 0; i < profileClinicalDetailVO.length; i++)
				{
					String deskMenuId = profileClinicalDetailVO[i].getDeskMenuId();
					String recordView = profileClinicalDetailVO[i].getRecordView();
					if (deskMenuId==null || deskMenuId.equals("") || recordView==null || recordView.equals(""))	continue;

					UserDeskMenuTemplateMasterVO userDeskMenuTemplateMasterVO = new UserDeskMenuTemplateMasterVO();
					HelperMethods.populate(userDeskMenuTemplateMasterVO, _fb);
					userDeskMenuTemplateMasterVO.setDeptUnitCode(_fb.getDepartmentUnitCode());
					userDeskMenuTemplateMasterVO.setDeskMenuId(deskMenuId);

					lstTemps = GenericTemplateTileDATA.getDeskMenuTemplateList(_fb.getDeskType(),_fb.getPatCrNo(), userDeskMenuTemplateMasterVO, userVO);

					for (Entry e : lstTemps)	e.setValue(e.getValue().split("#")[0]);

					mapTemplateDtl = GenericTemplateTileDATA.getAllTemplateDetails(lstTemps, userVO);
					WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_DTL_MAP, mapTemplateDtl);

					lstAllTempParas = GenericTemplateTileDATA.getAllTemplateParametersDetail(lstTemps, userVO);
					for (TemplateParameterMasterVO vo : lstAllTempParas)
					{
						if (vo.getParaId() != null)
						{
							LinkedHashMap<String, String> mapTemp = mapAllTempParas.get(vo.getTemplateId());
							if (mapTemp == null) mapTemp = new LinkedHashMap<String, String>();
							mapTemp.put(vo.getParaId(), vo.getParaName());
							mapAllTempParas.put(vo.getTemplateId(), mapTemp);
	
							if (!vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_LABEL)
									&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_COMMENT)
									&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_INFORMATION)
									&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_IMAGEVIEW))
							{
	
								LinkedHashMap<String, TemplateParameterMasterVO> map = mapAllValuedTempParas.get(vo.getTemplateId());
								if (map == null) map = new LinkedHashMap<String, TemplateParameterMasterVO>();
								map.put(vo.getParaId(), vo);
								mapAllValuedTempParas.put(vo.getTemplateId(), map);
							}
						}
					}
					WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_PARA_MAP, mapAllTempParas);
					WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_VALUED_TEMP_PARA_MAP, mapAllValuedTempParas);
					WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_LIST_ALL_TEMP_PARAS, lstAllTempParas);

					if(recordView.equals(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_PARAMETER_WISE))
					{
						// Getting Selected Paras
						LinkedHashMap<String, List<String>> mapSelPara = new LinkedHashMap<String, List<String>>();
						for (int x=i; x< profileClinicalDetailVO.length; x++)
						{
							ProfileClinicalDtlVO voProCliDtl = profileClinicalDetailVO[x];
							if(voProCliDtl.getDeskMenuId().equals(deskMenuId))
							{
								String tempId = voProCliDtl.getTemplateId();
								List<String> lstPara = mapSelPara.get(tempId);
								if (lstPara == null) lstPara = new ArrayList<String>();
								mapSelPara.put(voProCliDtl.getTemplateId(), lstPara);
								lstPara.add(voProCliDtl.getParaId());
								i++;
							}
							else
								break;
						}
						LinkedHashMap<String, LinkedHashSet<String>> mapViewParas = new LinkedHashMap<String, LinkedHashSet<String>>();
						for (String tempId : mapSelPara.keySet())
						{
							LinkedHashMap<String, TemplateParameterMasterVO> mapParaDtl = mapAllValuedTempParas.get(tempId);
							List<String> selectedParas = mapSelPara.get(tempId);
							LinkedHashSet<String> viewParas = mapViewParas.get(tempId);
							if (viewParas == null) viewParas = new LinkedHashSet<String>();
							mapViewParas.put(tempId, viewParas);
							for (String selParaId : selectedParas)
							{
								for (String paraId : mapParaDtl.keySet())
								{
									TemplateParameterMasterVO tempParaVO = mapParaDtl.get(paraId);
									if (tempParaVO.getParaId() != null && !tempParaVO.getParaId().equals("") && tempParaVO.getParaId().equals(selParaId)) viewParas
											.add(paraId);
									else if (tempParaVO.getParentParaId() != null && !tempParaVO.getParentParaId().equals(""))
									{
										String[] parents = tempParaVO.getParentParaId().split(GenericTemplateUtility.SEP_IN_PARA_PARENT);
										boolean flag1 = false;
										for (int x = 0; x < parents.length; x++)
											if (parents[x].equals(selParaId))
											{
												flag1 = true;
												break;
											}
										if (flag1) viewParas.add(paraId);
									}
								}
							}
						}
						// Setting Template Chart Selected Template
						LinkedHashMap<String, String> mapChartTemps = new LinkedHashMap<String, String>();
						for (String tempId : mapViewParas.keySet())
						{
							TemplateMasterVO voTemp = mapTemplateDtl.get(tempId);
							Entry e = new Entry();
							e.setLabel(voTemp.getTemplateName());
							e.setValue(voTemp.getTemplateId());
							lstSelectedTemps.add(e);
							mapChartTemps.put(voTemp.getTemplateId(), voTemp.getTemplateName());
						}

						// Setting Desk Menu Wise
						//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_LIST_MAP, mapChartTemps);
						Map mpDeskMenuChartTemps = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_LIST_MAP); 
						if(mpDeskMenuChartTemps==null)	mpDeskMenuChartTemps = new HashMap();
						mpDeskMenuChartTemps.put(deskMenuId, mapChartTemps);
						WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_LIST_MAP, mpDeskMenuChartTemps);

						// Setting Template Chart Selected Template Parameters
						LinkedHashMap<String, List<Entry>> mapChartTempParas = new LinkedHashMap<String, List<Entry>>();
						for (String tempId : mapViewParas.keySet())
						{
							List<Entry> lstParas = mapChartTempParas.get(tempId);
							if (lstParas == null) lstParas = new ArrayList<Entry>();
							mapChartTempParas.put(tempId, lstParas);
							for (String paraId : mapViewParas.get(tempId))
							{
								Entry e = new Entry(mapAllValuedTempParas.get(tempId).get(paraId).getParaName(), paraId);
								lstParas.add(e);
							}
						}
						// Setting Desk Menu Wise
						//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP, mapChartTempParas);
						Map mpDeskMenuChartTempParas = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
						if(mpDeskMenuChartTempParas==null) mpDeskMenuChartTempParas = new HashMap();
						mpDeskMenuChartTempParas.put(deskMenuId, mapChartTempParas);
						WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP, mpDeskMenuChartTempParas);

						// Getting Visit Dates
						PatientClinicalDetailVO _patCliDtlVO = new PatientClinicalDetailVO();
						HelperMethods.populate(_patCliDtlVO, _fb);
						_patCliDtlVO.setDeskMenuId(deskMenuId); //added by manisha date: 24.8.2017
						lstReportDates = GenericTemplateTileDATA.getReportDateList(_fb.getDeskType(), _patCliDtlVO, userVO);
						if (lstReportDates == null) lstReportDates = new ArrayList<Entry>();
						// Setting Record Dates
						LinkedHashMap<String, String> mapRecordDates = new LinkedHashMap<String, String>();
						for (Entry e : lstReportDates)
							mapRecordDates.put(e.getLabel(), e.getLabel());
		
						// Map of RecordDate/Map of Temp Id/Map of Parameter Id/Para Value
						Map<String, Map<String, Map<String, String>>> mpChartClinicalData = null;
						PatientClinicalDetailVO patCliniDtlVO = new PatientClinicalDetailVO();
						HelperMethods.populate(patCliniDtlVO, _fb);
						patCliniDtlVO.setDeskMenuId(deskMenuId);
						mpChartClinicalData = GenericTemplateTileDATA.getPatientChartClinicalDataTempWise(_fb.getDeskType(), patCliniDtlVO, lstReportDates,
								lstSelectedTemps, userVO);
		
						// Remove Report Dates that don't have Data
						boolean dataFoundFlag = false;
						List<String> lstRemove = new ArrayList<String>();
						for (String recordDate : mapRecordDates.keySet())
						{
							Map<String, Map<String, String>> mpTemp = mpChartClinicalData.get(recordDate);
							if (mpTemp == null)
							{
								lstRemove.add(recordDate);
								continue;
							}
							// Removing Not Useful Templates Data
							List<String> lstRemoveTemplates = new ArrayList<String>();
							for (String key : mpTemp.keySet())
							{
								boolean flag1 = false;
								for (Entry entTemp : lstSelectedTemps)
									if (entTemp.getValue().split("#")[0].equals(key))
									{
										flag1 = true;
										break;
									}
								if (!flag1) lstRemoveTemplates.add(key);
								else if (mpTemp.get(key).size() == 0) lstRemoveTemplates.add(key);
							}
							for (String tempId : lstRemoveTemplates)
								mpTemp.remove(tempId);
		
							// Checking For Record Date Data
							if (mpTemp.keySet().size() == 0)
							{
								mpChartClinicalData.remove(recordDate);
								lstRemove.add(recordDate);
							}
							else dataFoundFlag = true;
						}
						List<Entry> lstRemoveEntry = new ArrayList<Entry>();
						for (String str : lstRemove)
						{
							mapRecordDates.remove(str);
							for (Entry entObj : lstReportDates)
								if (entObj.getValue().equals(str))
								{
									lstRemoveEntry.add(entObj);
									break;
								}
						}
						//for (Entry entObj : lstRemoveEntry)
							//lstReportDates.remove(entObj);

						dataFoundFlag = false;

						// Getting Desk Menu Wise 
						//LinkedHashMap<String, List<Entry>> mapChartTempParas1 = (LinkedHashMap<String, List<Entry>>) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
						Map mpDeskMenuChartTempParas1 = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
						LinkedHashMap<String, List<Entry>> mapChartTempParas1 = (LinkedHashMap<String, List<Entry>>)mpDeskMenuChartTempParas1.get(deskMenuId);
		
						for (String tempId : mapChartTempParas1.keySet())
						{
							List<Entry> removePara = new ArrayList<Entry>();
							for (Entry entObj : mapChartTempParas1.get(tempId))
							{
								boolean flag1 = false;
								// Map of RecordDate/Map of Temp Id/Map of Parameter Id/Para Value
								for (String recordDate : mpChartClinicalData.keySet())
									for (String templateId : mpChartClinicalData.get(recordDate).keySet())
										if (templateId.equals(tempId)) for (String paraId : mpChartClinicalData.get(recordDate).get(templateId).keySet())
											if (paraId.equals(entObj.getValue()))
											{
												dataFoundFlag = true;
												flag1 = true;
											}
								if (!flag1) removePara.add(entObj);
							}
							for (Entry entObj : removePara)
								mapChartTempParas1.get(tempId).remove(entObj);
						}
						// Setting Desk Menu Wise
						//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP, mapRecordDates);
						Map mpDeskMenuRecordDates = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP);
						if(mpDeskMenuRecordDates==null) mpDeskMenuRecordDates = new HashMap();
						mpDeskMenuRecordDates.put(deskMenuId, mapRecordDates);
						WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP, mpDeskMenuRecordDates);

						// Setting Desk Menu Wise
						//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpChartClinicalData);
						Map mpDeskMenuChartClinicalData = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);
						if(mpDeskMenuChartClinicalData==null) mpDeskMenuChartClinicalData = new HashMap();
						mpDeskMenuChartClinicalData.put(deskMenuId, mpChartClinicalData);
						WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpDeskMenuChartClinicalData);
						WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_VIEW_MODE,GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_TEMPLATE_DESK_ID, deskMenuId);

						if (!dataFoundFlag)
						{
							throw new HisRecordNotFoundException("No Data Found");
						}
						proforma.setComplaintsProforma(deskMenuId);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);

						_fb.setReportMode(recordView);
						// Setting Desk Menu Wise Report View
						Map mpDeskMenuReportModes = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP); 
						if(mpDeskMenuReportModes==null)	mpDeskMenuReportModes = new HashMap();
						mpDeskMenuReportModes.put(deskMenuId, recordView);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP, mpDeskMenuReportModes);
					}
					else if(recordView.equals(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_DATE_WISE))
					{
						PatientClinicalDetailVO _patCliDtlVO = new PatientClinicalDetailVO();
						HelperMethods.populate(_patCliDtlVO, _fb);
						_patCliDtlVO.setDeskMenuId(deskMenuId);   //added by manisha date: 24.8.2017
						lstReportDates = GenericTemplateTileDATA.getReportDateList(_fb.getDeskType(), _patCliDtlVO, userVO);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_VISIT_DATES, lstReportDates);

						if (lstReportDates == null) lstReportDates = new ArrayList<Entry>();
						// Setting Record Dates
						LinkedHashMap<String, String> mapRecordDates = new LinkedHashMap<String, String>();
						for (Entry e : lstReportDates)
							mapRecordDates.put(e.getLabel(), e.getLabel());

						// Map of RecordDate/Map of Temp Id/Map of Parameter Id/Para Value
						Map<String, Map<String, Map<String, String>>> mpChartClinicalData = null;
						PatientClinicalDetailVO patCliniDtlVO = new PatientClinicalDetailVO();
						HelperMethods.populate(patCliniDtlVO, _fb);
						patCliniDtlVO.setDeskMenuId(deskMenuId);
						mpChartClinicalData = GenericTemplateTileDATA.getPatientChartClinicalDataTempWise(_fb.getDeskType(), patCliniDtlVO, 
								lstReportDates, lstTemps, userVO);

						// Remove Report Dates that don't have Data
						List<String> lstRemove = new ArrayList<String>();
						for (String recordDate : mapRecordDates.keySet())
						{
							Map<String, Map<String, String>> mpTemp = mpChartClinicalData.get(recordDate);
							if (mpTemp == null)
							{
								lstRemove.add(recordDate);
								continue;
							}
							// Removing Not Useful Templates Data
							List<String> lstRemoveTemplates = new ArrayList<String>();
							for (String key : mpTemp.keySet())
							{
								boolean flag1 = false;
								for (Entry entTemp : lstTemps)
									if (entTemp.getValue().split("#")[0].equals(key))
									{
										flag1 = true;
										break;
									}
								if (!flag1) lstRemoveTemplates.add(key);
								else if (mpTemp.get(key).size() == 0) lstRemoveTemplates.add(key);
							}
							for (String tempId : lstRemoveTemplates)
								mpTemp.remove(tempId);

							// Checking For Record Date Data
							if (mpTemp.keySet().size() == 0)
							{
								mpChartClinicalData.remove(recordDate);
								lstRemove.add(recordDate);
							}
							// else
							// dataFoundFlag = true;
						}
						List<Entry> lstRemoveEntry = new ArrayList<Entry>();
						for (String str : lstRemove)
						{
							mapRecordDates.remove(str);
							for (Entry entObj : lstReportDates)
								if (entObj.getValue().equals(str))
								{
									lstRemoveEntry.add(entObj);
									break;
								}
						}
						//for (Entry entObj : lstRemoveEntry)
						//	lstReportDates.remove(entObj);

						// Setting Desk Menu Wise
						//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpChartClinicalData);
						Map mpDeskMenuChartClinicalData = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);
						if(mpDeskMenuChartClinicalData==null) mpDeskMenuChartClinicalData = new HashMap();
						mpDeskMenuChartClinicalData.put(deskMenuId, mpChartClinicalData);
						WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpDeskMenuChartClinicalData);

						WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_VIEW_MODE,GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
							
						Map<String, Map<String, Map<String, String>>> mpChartClinicalDataTemp = new HashMap<String, Map<String, Map<String, String>>>();
						
						String strVisitDate = null;
						lstVisitDates = new ArrayList<Entry>();
						Set<String> setUniqueDated = new HashSet<String>();
						for (int x = i; x < profileClinicalDetailVO.length; x++)
						{
							ProfileClinicalDtlVO voProCliDtl = profileClinicalDetailVO[x];
							if(voProCliDtl.getDeskMenuId().equals(deskMenuId))
							{
								if(strVisitDate==null || !strVisitDate.equals(voProCliDtl.getRecordDate()))
								{
									strVisitDate = voProCliDtl.getRecordDate();
									Entry entry = new Entry();
									if(_fb.getDeskType().equals(DynamicDeskConfig.DESK_TYPE_CASUALITY_DESK) || _fb.getDeskType().equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK))
									{
										entry.setLabel(voProCliDtl.getRecordDate().split(" ")[0]);
										entry.setValue(voProCliDtl.getRecordDate().split(" ")[0]);
									}
									else
									{
										entry.setLabel(voProCliDtl.getRecordDate());
										entry.setValue(voProCliDtl.getRecordDate());
									}
									String strVal = voProCliDtl.getRecordDate();
									for(Entry ent : lstReportDates)
										if(_fb.getDeskType().equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK) 
												&& ent.getLabel().equals(entry.getLabel().substring(0,11)))
										{
											strVal = ent.getLabel();
											//entry.setLabel(ent.getLabel());
											entry.setValue(ent.getLabel());		//Earlier this line was commented. Uncommented By Adil on 1-Aug-2012
											break;
										}
										else if(ent.getLabel().equals(entry.getLabel()))
										{
											strVal = ent.getLabel();
											entry.setValue(ent.getLabel());		//Earlier this line was commented. Uncommented By Adil on 1-Aug-2012
											break;
										}
									if(setUniqueDated.add(strVal))
										lstVisitDates.add(entry);
									Map mpVisitData = (Map) mpChartClinicalData.get(strVal);
									mpChartClinicalDataTemp.put(voProCliDtl.getRecordDate(), mpVisitData);
								}
								x++;
							}
							else
								break;
						}

						// Setting Desk Menu Wise
						//WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES, lstVisitDates);
						Map mpDeskMenuVisitDates = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES);
						if(mpDeskMenuVisitDates==null)	mpDeskMenuVisitDates = new HashMap();
						mpDeskMenuVisitDates.put(deskMenuId, lstVisitDates);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES, mpDeskMenuVisitDates);
						
						// Setting Desk Menu Wise
						//WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS, lstTemps);
						Map mpDeskMenuTemps = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS); 
						if(mpDeskMenuTemps==null)	mpDeskMenuTemps = new HashMap();
						mpDeskMenuTemps.put(deskMenuId, lstTemps);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS, mpDeskMenuTemps);

						// Setting Desk Menu Wise
						//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpChartClinicalData);
						if(mpDeskMenuChartClinicalData==null) mpDeskMenuChartClinicalData = new HashMap();
						mpDeskMenuChartClinicalData.put(deskMenuId, mpChartClinicalData);
						WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpDeskMenuChartClinicalData);

						WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_TEMPLATE_DESK_ID, deskMenuId);

						proforma.setComplaintsProforma(deskMenuId);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);

						_fb.setReportMode(recordView);
						// Setting Desk Menu Wise Report View
						Map mpDeskMenuReportModes = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP); 
						if(mpDeskMenuReportModes==null)	mpDeskMenuReportModes = new HashMap();
						mpDeskMenuReportModes.put(deskMenuId, recordView);
						WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP, mpDeskMenuReportModes);
					}
				}
			}

			
			
			/*// Profile Complaints Detail Date Wise
			ProfileClinicalDtlVO[] _profileClinicalDetailVOs = (ProfileClinicalDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_COMPLAINTS_DTL_DATE_WISE_VO_ARRAY);
			String deskMenuIds = "";
			String recordViews = "";
			if (_profileClinicalDetailVOs != null && _profileClinicalDetailVOs.length > 0)
			{

				for (int i = 0; i < _profileClinicalDetailVOs.length; i++)
				{
					deskMenuIds = _profileClinicalDetailVOs[i].getDeskMenuId();
					recordViews = _profileClinicalDetailVOs[i].getRecordView();

					if (!deskMenuIds.equals("") && !recordViews.equals(""))
					{
						break;
					}
				}

				UserDeskMenuTemplateMasterVO userDeskMenuTemplateMasterVO = new UserDeskMenuTemplateMasterVO();
				HelperMethods.populate(userDeskMenuTemplateMasterVO, _fb);
				userDeskMenuTemplateMasterVO.setDeptUnitCode(_fb.getDepartmentUnitCode());
				userDeskMenuTemplateMasterVO.setDeskMenuId(deskMenuIds);

				lstTemps = GenericTemplateTileDATA.getDeskMenuTemplateList(_fb.getDeskType(), userDeskMenuTemplateMasterVO, userVO);

				lstActiveTemps = new ArrayList<Entry>();
				lstInactiveTemps = new ArrayList<Entry>();
				for (Entry e : lstTemps)
				{
					Entry ent = new Entry();
					ent.setLabel(e.getLabel());
					ent.setValue(e.getValue().split("#")[0]);
					String defalutFlag = e.getValue().split("#")[1];
					if (defalutFlag.equals(GenericTemplateConfig.YES)) lstActiveTemps.add(ent);
					else if (defalutFlag.equals(GenericTemplateConfig.NO)) lstInactiveTemps.add(ent);
					e.setValue(e.getValue().split("#")[0]);
				}

				mapTemplateDtl = GenericTemplateTileDATA.getAllTemplateDetails(lstTemps, userVO);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_DTL_MAP, mapTemplateDtl);

				lstAllTempParas = GenericTemplateTileDATA.getAllTemplateParametersDetail(lstTemps, userVO);
				for (TemplateParameterMasterVO vo : lstAllTempParas)
				{
					if (vo.getParaId() != null)
					{
						LinkedHashMap<String, String> mapTemp = mapAllTempParas.get(vo.getTemplateId());
						if (mapTemp == null) mapTemp = new LinkedHashMap<String, String>();
						mapTemp.put(vo.getParaId(), vo.getParaName());
						mapAllTempParas.put(vo.getTemplateId(), mapTemp);

						if (!vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_LABEL)
								&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_COMMENT)
								&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_INFORMATION)
								&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_IMAGEVIEW))
						{

							LinkedHashMap<String, TemplateParameterMasterVO> map = mapAllValuedTempParas.get(vo.getTemplateId());
							if (map == null) map = new LinkedHashMap<String, TemplateParameterMasterVO>();
							map.put(vo.getParaId(), vo);
							mapAllValuedTempParas.put(vo.getTemplateId(), map);
						}
					}
				}
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_PARA_MAP, mapAllTempParas);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_VALUED_TEMP_PARA_MAP, mapAllValuedTempParas);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_LIST_ALL_TEMP_PARAS, lstAllTempParas);

				// set method

				PatientClinicalDetailVO _patCliDtlVO = new PatientClinicalDetailVO();
				HelperMethods.populate(_patCliDtlVO, _fb);

				lstReportDates = GenericTemplateTileDATA.getReportDateList(_fb.getDeskType(), _patCliDtlVO, userVO);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_VISIT_DATES, lstReportDates);
				// }

				// tempid:paraid,paraname

				if (lstReportDates == null) lstReportDates = new ArrayList<Entry>();
				// Setting Record Dates
				LinkedHashMap<String, String> mapRecordDates = new LinkedHashMap<String, String>();
				for (Entry e : lstReportDates)
					mapRecordDates.put(e.getValue(), e.getLabel());

				// Map of RecordDate/Map of Temp Id/Map of Parameter Id/Para Value
				Map<String, Map<String, Map<String, String>>> mpChartClinicalData = null;
				PatientClinicalDetailVO patCliniDtlVO = new PatientClinicalDetailVO();
				HelperMethods.populate(patCliniDtlVO, _fb);
				patCliniDtlVO.setDeskMenuId(deskMenuIds);
				mpChartClinicalData = GenericTemplateTileDATA.getPatientChartClinicalDataTempWise(_fb.getDeskType(), patCliniDtlVO, lstReportDates,
						lstTemps, userVO);

				// Remove Report Dates that don't have Data
				// boolean dataFoundFlag = false;
				List<String> lstRemove = new ArrayList<String>();
				for (String recordDate : mapRecordDates.keySet())
				{
					Map<String, Map<String, String>> mpTemp = mpChartClinicalData.get(recordDate);
					if (mpTemp == null)
					{
						lstRemove.add(recordDate);
						continue;
					}
					// Removing Not Useful Templates Data
					List<String> lstRemoveTemplates = new ArrayList<String>();
					for (String key : mpTemp.keySet())
					{
						boolean flag1 = false;
						for (Entry entTemp : lstTemps)
							if (entTemp.getValue().split("#")[0].equals(key))
							{
								flag1 = true;
								break;
							}
						if (!flag1) lstRemoveTemplates.add(key);
						else if (mpTemp.get(key).size() == 0) lstRemoveTemplates.add(key);
					}
					for (String tempId : lstRemoveTemplates)
						mpTemp.remove(tempId);

					// Checking For Record Date Data
					if (mpTemp.keySet().size() == 0)
					{
						mpChartClinicalData.remove(recordDate);
						lstRemove.add(recordDate);
					}
					// else
					// dataFoundFlag = true;
				}
				List<Entry> lstRemoveEntry = new ArrayList<Entry>();
				for (String str : lstRemove)
				{
					mapRecordDates.remove(str);
					for (Entry entObj : lstReportDates)
						if (entObj.getValue().equals(str))
						{
							lstRemoveEntry.add(entObj);
							break;
						}
				}
				for (Entry entObj : lstRemoveEntry)
					lstReportDates.remove(entObj);

				// Setting Desk Menu Wise
				//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpChartClinicalData);
				Map mpDeskMenuChartClinicalData = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);
				if(mpDeskMenuChartClinicalData==null) mpDeskMenuChartClinicalData = new HashMap();
				mpDeskMenuChartClinicalData.put(_fb.getSelectedMenuId(), mpChartClinicalData);
				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpDeskMenuChartClinicalData);

				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_VIEW_MODE,GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
				
				Map<String, Map<String, Map<String, String>>> mpChartClinicalDataTemp = new HashMap<String, Map<String, Map<String, String>>>();
				for (int i = 0; i < _profileClinicalDetailVOs.length; i++)
				{
					Entry entry = new Entry();
					entry.setLabel(_profileClinicalDetailVOs[i].getRecordDate());
					entry.setValue(_profileClinicalDetailVOs[i].getRecordDate());
					String strVal = null;
					for(Entry ent : lstReportDates)
						if(_fb.getDeskType().equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK) 
								&& ent.getLabel().equals(entry.getLabel().substring(0,11)))
						{
							strVal = ent.getValue();
							//entry.setLabel(ent.getLabel());
							//entry.setValue(ent.getValue());
							break;
						}
						else if(ent.getLabel().equals(entry.getLabel()))
						{
							strVal = ent.getValue();
							//entry.setValue(ent.getValue());
							break;
						}
					lstVisitDates.add(entry);

					Map mpVisitData = (Map) mpChartClinicalData.get(strVal);

					mpChartClinicalDataTemp.put(_profileClinicalDetailVOs[i].getRecordDate(), mpVisitData);
				}

				// Setting Desk Menu Wise
				//WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES, lstVisitDates);
				Map mpDeskMenuVisitDates = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES);
				if(mpDeskMenuVisitDates==null)	mpDeskMenuVisitDates = new HashMap();
				mpDeskMenuVisitDates.put(_fb.getSelectedMenuId(), lstVisitDates);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES, mpDeskMenuVisitDates);
				
				// Setting Desk Menu Wise
				//WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS, lstTemps);
				Map mpDeskMenuTemps = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS); 
				if(mpDeskMenuTemps==null)	mpDeskMenuTemps = new HashMap();
				mpDeskMenuTemps.put(_fb.getSelectedMenuId(), lstTemps);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS, mpDeskMenuTemps);

				// Setting Desk Menu Wise
				//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpChartClinicalData);
				if(mpDeskMenuChartClinicalData==null) mpDeskMenuChartClinicalData = new HashMap();
				mpDeskMenuChartClinicalData.put(_fb.getSelectedMenuId(), mpChartClinicalData);
				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpDeskMenuChartClinicalData);

				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_TEMPLATE_DESK_ID, deskMenuIds);

				proforma.setComplaintsProforma(deskMenuIds);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);

				_fb.setReportMode(recordViews);
				// Setting Desk Menu Wise Report View
				Map mpDeskMenuReportModes = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP); 
				if(mpDeskMenuReportModes==null)	mpDeskMenuReportModes = new HashMap();
				mpDeskMenuReportModes.put(_fb.getSelectedMenuId(), recordViews);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP, mpDeskMenuReportModes);
			}

			// Profile Complaints Detail
			ProfileClinicalDtlVO[] profileClinicalDetailVO = (ProfileClinicalDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_COMPLAINTS_DTL_VO_ARRAY);
			String deskMenuId = "";
			String recordView = "";
			if (profileClinicalDetailVO != null && profileClinicalDetailVO.length > 0)
			{

				for (int i = 0; i < profileClinicalDetailVO.length; i++)
				{
					deskMenuId = profileClinicalDetailVO[i].getDeskMenuId();
					recordView = profileClinicalDetailVO[i].getRecordView();

					if (!deskMenuIds.equals("") && !recordView.equals(""))
					{
						break;
					}
				}

				UserDeskMenuTemplateMasterVO userDeskMenuTemplateMasterVO = new UserDeskMenuTemplateMasterVO();
				HelperMethods.populate(userDeskMenuTemplateMasterVO, _fb);
				userDeskMenuTemplateMasterVO.setDeptUnitCode(_fb.getDepartmentUnitCode());
				userDeskMenuTemplateMasterVO.setDeskMenuId(deskMenuId);

				lstTemps = GenericTemplateTileDATA.getDeskMenuTemplateList(_fb.getDeskType(), userDeskMenuTemplateMasterVO, userVO);

				lstActiveTemps = new ArrayList<Entry>();
				lstInactiveTemps = new ArrayList<Entry>();
				for (Entry e : lstTemps)
				{
					Entry ent = new Entry();
					ent.setLabel(e.getLabel());
					ent.setValue(e.getValue().split("#")[0]);
					String defalutFlag = e.getValue().split("#")[1];
					if (defalutFlag.equals(GenericTemplateConfig.YES)) lstActiveTemps.add(ent);
					else if (defalutFlag.equals(GenericTemplateConfig.NO)) lstInactiveTemps.add(ent);
					e.setValue(e.getValue().split("#")[0]);
				}

				// putmap.put(GenericTemplateConfig.TEMPLATE_TILE_ALL_TEMPLATE_LIST, lstTemps);
				// putmap.put(GenericTemplateConfig.TEMPLATE_TILE_DEFAULT_TEMPLATE_LIST, lstActiveTemps);
				// putmap.put(GenericTemplateConfig.TEMPLATE_TILE_UNDEFAULT_TEMPLATE_LIST, lstInactiveTemps);
				mapTemplateDtl = GenericTemplateTileDATA.getAllTemplateDetails(lstTemps, userVO);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_DTL_MAP, mapTemplateDtl);

				lstAllTempParas = GenericTemplateTileDATA.getAllTemplateParametersDetail(lstTemps, userVO);
				for (TemplateParameterMasterVO vo : lstAllTempParas)
				{
					if (vo.getParaId() != null)
					{
						LinkedHashMap<String, String> mapTemp = mapAllTempParas.get(vo.getTemplateId());
						if (mapTemp == null) mapTemp = new LinkedHashMap<String, String>();
						mapTemp.put(vo.getParaId(), vo.getParaName());
						mapAllTempParas.put(vo.getTemplateId(), mapTemp);

						if (!vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_LABEL)
								&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_COMMENT)
								&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_INFORMATION)
								&& !vo.getControlType().equals(GenericTemplateUtility.CONTROL_TYPES_IMAGEVIEW))
						{

							LinkedHashMap<String, TemplateParameterMasterVO> map = mapAllValuedTempParas.get(vo.getTemplateId());
							if (map == null) map = new LinkedHashMap<String, TemplateParameterMasterVO>();
							map.put(vo.getParaId(), vo);
							mapAllValuedTempParas.put(vo.getTemplateId(), map);
						}
					}
				}
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_PARA_MAP, mapAllTempParas);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_VALUED_TEMP_PARA_MAP, mapAllValuedTempParas);

				// set method

				mapTemplateDtl = (Map<String, TemplateMasterVO>) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_DTL_MAP);
				mapAllValuedTempParas = (LinkedHashMap<String, LinkedHashMap<String, TemplateParameterMasterVO>>) session
						.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_VALUED_TEMP_PARA_MAP);

				// Getting Selected Paras
				LinkedHashMap<String, List<String>> mapSelPara = new LinkedHashMap<String, List<String>>();
				for (int i = 0; i < profileClinicalDetailVO.length; i++)
				{
					String tempId = profileClinicalDetailVO[i].getTemplateId();
					// String paraId = _fb.getSelectedParas()[i].split("#")[1];
					List<String> lstPara = mapSelPara.get(tempId);
					if (lstPara == null) lstPara = new ArrayList<String>();
					mapSelPara.put(profileClinicalDetailVO[i].getTemplateId(), lstPara);
					lstPara.add(profileClinicalDetailVO[i].getParaId());
				}
				
				LinkedHashMap<String, LinkedHashSet<String>> mapViewParas = new LinkedHashMap<String, LinkedHashSet<String>>();
				for (String tempId : mapSelPara.keySet())
				{
					LinkedHashMap<String, TemplateParameterMasterVO> mapParaDtl = mapAllValuedTempParas.get(tempId);
					List<String> selectedParas = mapSelPara.get(tempId);
					LinkedHashSet<String> viewParas = mapViewParas.get(tempId);
					if (viewParas == null) viewParas = new LinkedHashSet<String>();
					mapViewParas.put(tempId, viewParas);
					for (String selParaId : selectedParas)
					{
						for (String paraId : mapParaDtl.keySet())
						{
							TemplateParameterMasterVO tempParaVO = mapParaDtl.get(paraId);
							if (tempParaVO.getParaId() != null && !tempParaVO.getParaId().equals("") && tempParaVO.getParaId().equals(selParaId)) viewParas
									.add(paraId);
							else if (tempParaVO.getParentParaId() != null && !tempParaVO.getParentParaId().equals(""))
							{
								String[] parents = tempParaVO.getParentParaId().split(GenericTemplateUtility.SEP_IN_PARA_PARENT);
								boolean flag1 = false;
								for (int i = 0; i < parents.length; i++)
									if (parents[i].equals(selParaId))
									{
										flag1 = true;
										break;
									}
								if (flag1) viewParas.add(paraId);
							}
						}
					}
				}
				// Setting Template Chart Selected Template
				LinkedHashMap<String, String> mapChartTemps = new LinkedHashMap<String, String>();
				for (String tempId : mapViewParas.keySet())
				{
					TemplateMasterVO voTemp = mapTemplateDtl.get(tempId);
					Entry e = new Entry();
					e.setLabel(voTemp.getTemplateName());
					e.setValue(voTemp.getTemplateId());
					lstSelectedTemps.add(e);
					mapChartTemps.put(voTemp.getTemplateId(), voTemp.getTemplateName());
				}
				// Setting Desk Menu Wise
				//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_LIST_MAP, mapChartTemps);
				Map mpDeskMenuChartTemps = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_LIST_MAP); 
				if(mpDeskMenuChartTemps==null)	mpDeskMenuChartTemps = new HashMap();
				mpDeskMenuChartTemps.put(_fb.getSelectedMenuId(), mapChartTemps);
				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_LIST_MAP, mpDeskMenuChartTemps);

				// Setting Template Chart Selected Template Parameters
				LinkedHashMap<String, List<Entry>> mapChartTempParas = new LinkedHashMap<String, List<Entry>>();
				for (String tempId : mapViewParas.keySet())
				{
					List<Entry> lstParas = mapChartTempParas.get(tempId);
					if (lstParas == null) lstParas = new ArrayList<Entry>();
					mapChartTempParas.put(tempId, lstParas);
					for (String paraId : mapViewParas.get(tempId))
					{
						Entry e = new Entry(mapAllValuedTempParas.get(tempId).get(paraId).getParaName(), paraId);
						lstParas.add(e);
					}
				}
				// Setting Desk Menu Wise
				//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP, mapChartTempParas);
				Map mpDeskMenuChartTempParas = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
				if(mpDeskMenuChartTempParas==null) mpDeskMenuChartTempParas = new HashMap();
				mpDeskMenuChartTempParas.put(_fb.getSelectedMenuId(), mapChartTempParas);
				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP, mpDeskMenuChartTempParas);

				// Getting Visit Dates
				PatientClinicalDetailVO _patCliDtlVO = new PatientClinicalDetailVO();
				HelperMethods.populate(_patCliDtlVO, _fb);
				lstReportDates = GenericTemplateTileDATA.getReportDateList(_fb.getDeskType(), _patCliDtlVO, userVO);
				if (lstReportDates == null) lstReportDates = new ArrayList<Entry>();
				// Setting Record Dates
				LinkedHashMap<String, String> mapRecordDates = new LinkedHashMap<String, String>();
				for (Entry e : lstReportDates)
					mapRecordDates.put(e.getValue(), e.getLabel());

				// Map of RecordDate/Map of Temp Id/Map of Parameter Id/Para Value
				Map<String, Map<String, Map<String, String>>> mpChartClinicalData = null;
				PatientClinicalDetailVO patCliniDtlVO = new PatientClinicalDetailVO();
				HelperMethods.populate(patCliniDtlVO, _fb);
				patCliniDtlVO.setDeskMenuId(deskMenuId);
				mpChartClinicalData = GenericTemplateTileDATA.getPatientChartClinicalDataTempWise(_fb.getDeskType(), patCliniDtlVO, lstReportDates,
						lstSelectedTemps, userVO);

				// Remove Report Dates that don't have Data
				boolean dataFoundFlag = false;
				List<String> lstRemove = new ArrayList<String>();
				for (String recordDate : mapRecordDates.keySet())
				{
					Map<String, Map<String, String>> mpTemp = mpChartClinicalData.get(recordDate);
					if (mpTemp == null)
					{
						lstRemove.add(recordDate);
						continue;
					}
					// Removing Not Useful Templates Data
					List<String> lstRemoveTemplates = new ArrayList<String>();
					for (String key : mpTemp.keySet())
					{
						boolean flag1 = false;
						for (Entry entTemp : lstSelectedTemps)
							if (entTemp.getValue().split("#")[0].equals(key))
							{
								flag1 = true;
								break;
							}
						if (!flag1) lstRemoveTemplates.add(key);
						else if (mpTemp.get(key).size() == 0) lstRemoveTemplates.add(key);
					}
					for (String tempId : lstRemoveTemplates)
						mpTemp.remove(tempId);

					// Checking For Record Date Data
					if (mpTemp.keySet().size() == 0)
					{
						mpChartClinicalData.remove(recordDate);
						lstRemove.add(recordDate);
					}
					else dataFoundFlag = true;
				}
				List<Entry> lstRemoveEntry = new ArrayList<Entry>();
				for (String str : lstRemove)
				{
					mapRecordDates.remove(str);
					for (Entry entObj : lstReportDates)
						if (entObj.getValue().equals(str))
						{
							lstRemoveEntry.add(entObj);
							break;
						}
				}
				for (Entry entObj : lstRemoveEntry)
					lstReportDates.remove(entObj);

				// Removing Non-data Selected Parameters in Report Parameter Wise
				// if (_fb.getReportMode().equals(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_PARAMETER_WISE))
				// {
				dataFoundFlag = false;

				// Getting Desk Menu Wise 
				//LinkedHashMap<String, List<Entry>> mapChartTempParas1 = (LinkedHashMap<String, List<Entry>>) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
				Map mpDeskMenuChartTempParas1 = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
				LinkedHashMap<String, List<Entry>> mapChartTempParas1 = (LinkedHashMap<String, List<Entry>>)mpDeskMenuChartTempParas1.get(_fb.getSelectedMenuId());

				for (String tempId : mapChartTempParas1.keySet())
				{
					List<Entry> removePara = new ArrayList<Entry>();
					for (Entry entObj : mapChartTempParas1.get(tempId))
					{
						boolean flag1 = false;
						// Map of RecordDate/Map of Temp Id/Map of Parameter Id/Para Value
						for (String recordDate : mpChartClinicalData.keySet())
							for (String templateId : mpChartClinicalData.get(recordDate).keySet())
								if (templateId.equals(tempId)) for (String paraId : mpChartClinicalData.get(recordDate).get(templateId).keySet())
									if (paraId.equals(entObj.getValue()))
									{
										dataFoundFlag = true;
										flag1 = true;
									}
						if (!flag1) removePara.add(entObj);
					}
					for (Entry entObj : removePara)
						mapChartTempParas1.get(tempId).remove(entObj);
				}
				// mpChartClinicalData
				// }

				// Setting Clinical Data
				
				// Setting Desk Menu Wise
				//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP, mapRecordDates);
				Map mpDeskMenuRecordDates = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP);
				if(mpDeskMenuRecordDates==null) mpDeskMenuRecordDates = new HashMap();
				mpDeskMenuRecordDates.put(_fb.getSelectedMenuId(), mapRecordDates);
				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP, mpDeskMenuRecordDates);

				// }
				// Setting Desk Menu Wise
				//WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpChartClinicalData);
				Map mpDeskMenuChartClinicalData = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);
				if(mpDeskMenuChartClinicalData==null) mpDeskMenuChartClinicalData = new HashMap();
				mpDeskMenuChartClinicalData.put(_fb.getSelectedMenuId(), mpChartClinicalData);
				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpDeskMenuChartClinicalData);

				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_VIEW_MODE,GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_TEMPLATE_DESK_ID, deskMenuId);

				if (!dataFoundFlag)// &&
				// _fb.getReportMode().equals(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_PARAMETER_WISE))
				{
					throw new HisRecordNotFoundException("No Data Found");
				}

				proforma.setComplaintsProforma(deskMenuId);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);

				_fb.setReportMode(recordView);
				// Setting Desk Menu Wise Report View
				Map mpDeskMenuReportModes = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP); 
				if(mpDeskMenuReportModes==null)	mpDeskMenuReportModes = new HashMap();
				mpDeskMenuReportModes.put(_fb.getSelectedMenuId(), recordViews);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP, mpDeskMenuReportModes);
			}*/

			// Profile OT Detail

			ProfileOTDtlVO[] profileOTDtlVOs = (ProfileOTDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_OT_DTL_VO_ARRAY);
			if (profileOTDtlVOs != null && profileOTDtlVOs.length > 0)
			{
				String[] str = new String[profileOTDtlVOs.length];
				for (int i = 0; i < profileOTDtlVOs.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);

				ProfileOTDetailVO[] _profileOTDetailVOs = new ProfileOTDetailVO[profileOTDtlVOs.length];
				for (int i = 0; i < profileOTDtlVOs.length; i++)
				{
					_profileOTDetailVOs[i] = new ProfileOTDetailVO();
					HelperMethods.populate(_profileOTDetailVOs[i], profileOTDtlVOs[i]);
				}

				for (int i = 0; i < profileOTDtlVOs.length; i++)
				{
					ProfileOTDetailVO pDetailVO = (ProfileOTDetailVO) _profileOTDetailVOs[i];
					profileOTList.add(pDetailVO);
				}
				templateDetailsMap = GenericPatientProfileDATA.getPatientProfileOperationTemplateDetails(profileOTList, _fb.getPatCrNo(), userVO);
				templateListAll = (List) templateDetailsMap.get(OpdConfig.OPERATION_TEMPLATE_LIST_ALL);
				paraIdValueListAll = (List) templateDetailsMap.get(OpdConfig.OPERATION_TEMPLATE_PARA_ID_VALUE_ALL);

				// (0) template Id
				// (1) parameter ID
				// (2) parameter value
				// (3) status code
				Iterator templateListAllItr = templateListAll.iterator();
				Iterator paraIdValueListAllItr = paraIdValueListAll.iterator();
				while ((templateListAllItr.hasNext()) && (paraIdValueListAllItr.hasNext()))
				{
					templateList = (List) templateListAllItr.next();
					paraIdValueList = (List) paraIdValueListAllItr.next();
					Iterator templateListIterator = templateList.iterator();
					Map mapAll = new HashMap();
					while (templateListIterator.hasNext())
					{
						String templateId = (String) templateListIterator.next();
						Map map = new HashMap();
						Iterator paraIdValueListIterator = paraIdValueList.iterator();
						while (paraIdValueListIterator.hasNext())
						{
							List list = (List) paraIdValueListIterator.next();
							if (templateId.equals((String) list.get(0)))
							{
								map.put(list.get(1), list.get(2));
							}
						}
						mapAll.put(templateId, map);

					}
					mapAllList.add(mapAll);
				}

				WebUTIL.setAttributeInSession(_rq, OpdConfig.OPERATION_TEMPLATE_MAP_ALL_LIST, mapAllList);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.OPERATION_TEMPLATE_LIST_ALL, templateListAll);

				// to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("OTVIEWING"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}

				proforma.setOperationDetailProforma(_fb.getSelectedMenuId(), _profileOTDetailVOs, _fb.getFetchSelectedRow());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}

			// Profile Image Detail

			ProfileImageDtlVO[] profileImageDtlVO = (ProfileImageDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_IMAGE_DTL_VO_ARRAY);
			if (profileImageDtlVO != null && profileImageDtlVO.length > 0)
			{
				String[] str = new String[profileImageDtlVO.length];
				for (int i = 0; i < profileImageDtlVO.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);

				OpdPatientImageDtlVO[] patImageDtlVOs = new OpdPatientImageDtlVO[profileImageDtlVO.length];
				for (int i = 0; i < profileImageDtlVO.length; i++)
				{
					patImageDtlVOs[i] = new OpdPatientImageDtlVO();
					HelperMethods.populate(patImageDtlVOs[i], profileImageDtlVO[i]);
				}

				// to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("IMAGEEXAMINATION"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}

				proforma.setExamImagesProforma(_fb.getSelectedMenuId(), patImageDtlVOs, _fb.getFetchSelectedRow());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}

			

			// Profile Progress Notes Detail

			ProfileProgressNotesDtlVO[] profileProgNotesDtlVO = (ProfileProgressNotesDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROGRESS_NOTES_DTL_VO_ARRAY);
			if (profileProgNotesDtlVO != null && profileProgNotesDtlVO.length > 0)
			{
				String[] str = new String[profileProgNotesDtlVO.length];
				for (int i = 0; i < profileProgNotesDtlVO.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);

				DoctorRoundDtlVO[] patProgNotesDtlVOs = new DoctorRoundDtlVO[profileProgNotesDtlVO.length];
				for (int i = 0; i < profileProgNotesDtlVO.length; i++)
				{
					patProgNotesDtlVOs[i] = new DoctorRoundDtlVO();
					HelperMethods.populate(patProgNotesDtlVOs[i], profileProgNotesDtlVO[i]);
				}

				// to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("DOCTORROUND"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}

				proforma.setEpiProgressNotesDetailProforma(_fb.getSelectedMenuId(), patProgNotesDtlVOs, _fb.getFetchSelectedRow());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}

			// Profile External Investigation Detail

			ProfileExtExamDtlVO[] profileExtExamDtlVO = (ProfileExtExamDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_EXT_INV_DTL_VO_ARRAY);
			if (profileExtExamDtlVO != null && profileExtExamDtlVO.length > 0)
			{
				String[] str = new String[profileExtExamDtlVO.length];
				for (int i = 0; i < profileExtExamDtlVO.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);

				EpisodeExtInvDtlVO[] patExtInvDtlVOs = new EpisodeExtInvDtlVO[profileExtExamDtlVO.length];
				for (int i = 0; i < profileExtExamDtlVO.length; i++)
				{
					patExtInvDtlVOs[i] = new EpisodeExtInvDtlVO();
					HelperMethods.populate(patExtInvDtlVOs[i], profileExtExamDtlVO[i]);
				}

				// to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("EXTERNALEXAMINATION"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}

				proforma.setExtInvestigationProforma(_fb.getSelectedMenuId(), patExtInvDtlVOs, _fb.getFetchSelectedRow());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}

			/*
			 * /////Diet Advice
			 * 
			 * PatDietAdviceDetailVO _patDietDetailVO=null ; if(!(_fb.getDietDays().equals(""))) { _patDietDetailVO= new
			 * PatDietAdviceDetailVO(); _patDietDetailVO.setDays(_fb.getDietDays());
			 * _patDietDetailVO.setDietTypeCode(_fb.getDietTypeId()); //_patDietDetailVO.setDietTypeDesc(_fb.getDietType());
			 * _patDietDetailVO.setRemarks(_fb.getDietRemark());
			 * 
			 * Iterator itr=lstDietType.iterator(); while(itr.hasNext()) { Entry entry=(Entry)itr.next();
			 * 
			 * if(_fb.getDietTypeId().equals(entry.getValue())) { _patDietDetailVO.setDietTypeDesc(entry.getLabel()); } }
			 * //_patDietDetailVO.setStartDate(_fb.getDietStartDate());
			 * //_patDietDetailVO.setSerialNo(_fb.getDietSerialNo());
			 * 
			 * _patDietDetailVO.setEpisodeCode(_fb.getEpisodeCode());
			 * _patDietDetailVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo()); _patDietDetailVO.setPatCrNo(_fb.getPatCrNo());
			 * _patDietDetailVO.setAdmissionNo(_fb.getAdmissionNo()); WebUTIL.setAttributeInSession(_rq,
			 * OpdConfig.PAT_PROFILE_DTL_DISCHARGE_DIET_DETAIL_VO, _patDietDetailVO); } else {
			 * WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_PROFILE_DTL_DISCHARGE_DIET_DETAIL_VO, _patDietDetailVO); }
			 * 
			 * 
			 * ///Rest Advice RestAdviceDtlVO restAdviceDtlVO=null ; if(!(_fb.getRestDays().equals(""))) {
			 * restAdviceDtlVO=new RestAdviceDtlVO(); restAdviceDtlVO.setDays(_fb.getRestDays());
			 * restAdviceDtlVO.setRestReason(_fb.getRestReason()); //restAdviceDtlVO.setRemarks(_fb.getRestRemark());
			 * //restAdviceDtlVO.setStartDate(_fb.getRestStartDate()); //restAdviceDtlVO.setSerialNo(_fb.getRestSerialNo());
			 * restAdviceDtlVO.setEpisodeCode(_fb.getEpisodeCode());
			 * restAdviceDtlVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo()); restAdviceDtlVO.setPatCrNo(_fb.getPatCrNo());
			 * restAdviceDtlVO.setAdmissionNo(_fb.getAdmissionNo()); restAdviceDtlVO.setCirtificateStatus("0");// status for
			 * medical certificate which is not generated WebUTIL.setAttributeInSession(_rq,
			 * OpdConfig.PAT_PROFILE_DTL_DISCHARGE_REST_ADVICE_DETAIL_VO, restAdviceDtlVO); } else {
			 * WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_PROFILE_DTL_DISCHARGE_REST_ADVICE_DETAIL_VO,
			 * restAdviceDtlVO); }
			 */
			
			//vital chart view detail
			
			 List<ProfileChartViewDtlVO> lstCharts=(List<ProfileChartViewDtlVO>) session.getAttribute(OpdConfig.PATIENT_PROFILE_CHART_VIEW_DTL_VO_ARRAY);
			 if (lstCharts != null && lstCharts.size()>0)
			 {
				// /////////to get selected menu id
				Iterator itr = lstProMenus.iterator();
				while (itr.hasNext())
				{
					DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

					if (_deskMenuMasterVO.getDeskUrl().equals("CHARTDETAIL"))
					{
						_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
					}
				}

				proforma.setChartViewDtlProforma(_fb.getSelectedMenuId(), lstCharts);
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			 }
			 
			 
			// Profile Footer Detail
				ProfileFooterDtlVO[] profileFooterDetailVO = (ProfileFooterDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_FOOTER_DTL_VO_ARRAY);
				if (profileFooterDetailVO != null && profileFooterDetailVO.length > 0)
				{
					// to get fetch selected row
					String[] str = new String[profileFooterDetailVO.length];
					for (int i = 0; i < profileFooterDetailVO.length; i++)
					{
						str[i] = String.valueOf(i);
					}
					_fb.setFetchSelectedRow(str);

					// to get episode diagnosis vo
					// DisclaimerMstVO[] disclaimerMstVOs=new DisclaimerMstVO[profileFooterDetailVO.length];
					for (int i = 0; i < profileFooterDetailVO.length; i++)
					{
						// disclaimerMstVOs=new DisclaimerMstVO();
						HelperMethods.populate(_disclaimerMstVOs, profileFooterDetailVO[i]);
						remarks = profileFooterDetailVO[i].getProfileSummary();
						reviewDate = profileFooterDetailVO[i].getReviewDate();
						profileType = profileFooterDetailVO[i].getProfileType();
						dischargeType = profileFooterDetailVO[i].getDischargeType() + "#" + profileFooterDetailVO[i].getDischargeTypeName();
						dischargeAdvisedBy = profileFooterDetailVO[i].getDischargeAdvisedBy() + "#" + profileFooterDetailVO[i].getDischargeAdvisedByName();
						dischargeAdvisedDept = profileFooterDetailVO[i].getDischargeAdvisedDept() + "#" + profileFooterDetailVO[i].getDischargeAdvisedDeptName();
						_fb.setRemarks(remarks);
						_fb.setProfileType(profileType);
						_fb.setReviewDate(reviewDate);
						_fb.setDischargeType(profileFooterDetailVO[i].getDischargeType());
						_fb.setDischargeAdvisedBy(profileFooterDetailVO[i].getDischargeAdvisedBy());
						_fb.setDischargeAdvisedDept(profileFooterDetailVO[i].getDischargeAdvisedDept());
						_fb.setDischargeTypeName(profileFooterDetailVO[i].getDischargeTypeName());
						_fb.setDischargeAdvisedByName(profileFooterDetailVO[i].getDischargeAdvisedByName());
						_fb.setDischargeAdvisedDeptName(profileFooterDetailVO[i].getDischargeAdvisedDeptName());
					}

					// to get selected menu id
					Iterator itr = lstProMenus.iterator();
					while (itr.hasNext())
					{
						DeskMenuMasterVO _deskMenuMasterVO = (DeskMenuMasterVO) itr.next();

						// if(_deskMenuMasterVO.getDeskUrl().equals("PROFILEFOOTER"))
						// {
						// _fb.setSelectedMenuId("99999");
						// }
						if (_deskMenuMasterVO.getDeskUrl().equals("PROFILEFOOTER"))
						{
							_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
						}
						if (_deskMenuMasterVO.getDeskUrl().equals("DISCHARGEPROFILEFOOTER"))
						{
							_fb.setSelectedMenuId(_deskMenuMasterVO.getDeskMenuId());
						}

					}

					proforma.setProfileFooterDetailProforma(_fb.getSelectedMenuId(), _fb.getRemarks(), _fb.getReviewDate(), _fb.getDischargeType(), _fb.getDischargeTypeName(),
							_fb.getDischargeAdvisedBy(), _fb.getDischargeAdvisedByName(), _fb.getDischargeAdvisedDept(), _fb.getDischargeAdvisedDeptName(), 
							profileType, _disclaimerMstVOs,_fb.getSnomdCIdRemarks(),_fb.getSnomdPTRemarks());

					WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
				}

			objStatus.add(Status.NEW);
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	public static void removeUserPriv(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		// Map<String, String> mpAllUsers = null;
		// List<Entry> lstNotAdded = new ArrayList<Entry>();
		// List<Entry> lstAdded = new ArrayList<Entry>();
		// List listAdded=new ArrayList();
		// Map map = new HashMap();
		// List<Entry> lstUnit = null;
		// List<Entry> lstUnitAll = null;
		// List searchUserList=new ArrayList();
		// List<Entry> lstAddUnit = new ArrayList<Entry>();
		// List<Entry> allUnitList = new ArrayList<Entry>();
		// List<Entry> lstUnitLeft = new ArrayList<Entry>();
		HttpSession session = null;
		try
		{
			// setSysdate(_rq);
			// UserVO userVO = getUserVO(_rq);
			session = WebUTIL.getSession(_rq);

			List addedUserList = (List) session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST);

			// mpAllUsers = (HashMap<String, String>)session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ALL_USERS_MAP);

			// lstUnit=(List<Entry>)session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ESSENTIAL_ALL_UNIT_LIST);
			// lstUnitAll=(List<Entry>)session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ESSENTIAL_ALL_UNIT_LIST);
			List lstAddedUnit = (List) session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ADDED_UNIT_LIST);

			String index = _fb.getDeleteIndex();
			// UserVO _userVO=(UserVO)addedUserList.get(Integer.parseInt(index));
			addedUserList.remove(Integer.parseInt(index));

			if (lstAddedUnit.size() > 0)
			{
				_fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC + "#" + OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND);
			}
			else
			{
				_fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND);
			}

			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST, addedUserList);

			/*
			 * ProfileAccessDetailVO _profileAccessDetailVO=new ProfileAccessDetailVO();
			 * _profileAccessDetailVO.setUserId(_userVO.getUserId()); _profileAccessDetailVO.setProfileId(_fb.getProfileId()
			 */

			// GenericPatientProfileDATA.removeUserPriv(_profileAccessDetailVO, userVO);
			/*
			 * List lstAddedUnit=(List)session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ADDED_UNIT_LIST);
			 * 
			 * if(lstAddedUnit.size()>0 || _fb.getSelectedUnits()!=null && _fb.getSelectedUnits().length>0) {
			 * _fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC+"#"+OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND); }
			 * else { _fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND); }
			 * 
			 * 
			 * Map<String, String> mpAllUnits = new HashMap<String, String>(); Iterator itr=lstUnit.iterator();
			 * while(itr.hasNext()) { Entry entry=(Entry)itr.next(); Entry entry1=new Entry();
			 * entry1.setLabel(entry.getLabel()); entry1.setValue(entry.getValue()); lstAddUnit.add(entry1);
			 * mpAllUnits.put(entry1.getValue(),entry1.getLabel()); } map.put(OpdConfig.OPD_PATIENT_PROFILE_ALL_UNITS_MAP,
			 * mpAllUnits);
			 * 
			 * 
			 * Iterator iterator=lstUnit.iterator(); while(iterator.hasNext()) { Entry entObj=null; Entry
			 * entry=(Entry)iterator.next(); lstUnitLeft.add(entry); if(_fb.getSelectedUnits()!=null) { for(int i=0;i<_fb.getSelectedUnits().length;i++) {
			 * if(entry.getValue().equalsIgnoreCase(_fb.getSelectedUnits()[i])) { entObj=entry; break; } } } if(entObj!=null) {
			 * iterator.remove(); } }
			 * 
			 * if(_fb.getUnitList().length>0) { for(String _userId : _fb.getUnitList()) { Entry entObj = new Entry();
			 * entObj.setValue(_userId); entObj.setLabel(mpAllUnits.get(_userId)); lstNotAdded.add(entObj); } }
			 * 
			 * if(_fb.getSelectedUnits().length>0) { for(String _userId : _fb.getSelectedUnits()) { Entry entObj = new
			 * Entry(); entObj.setValue(_userId); entObj.setLabel(mpAllUnits.get(_userId)); lstAdded.add(entObj); } }
			 * 
			 * WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_ESSENTIAL_ALL_UNIT_LIST, lstUnitLeft);
			 * WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_ESSENTIAL_UNIT_LIST, lstUnit);
			 * WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_ADDED_UNIT_LIST, lstAdded);
			 * WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_ALL_USERS_MAP, mpAllUsers);
			 */

			/*
			 * Iterator itr=addedUserList.iterator(); while(itr.hasNext()) { UserVO userVO1=(UserVO)itr.next(); }
			 */

			objStatus.add(Status.NEW);
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	public static String getLabelOfEntry(List list, String value)
	{

		Iterator itr = list.iterator();
		String label = "";
		while (itr.hasNext())
		{
			Entry entry = (Entry) itr.next();
			if (value.equals(entry.getValue()))
			{
				label = entry.getLabel();
				break;
			}
		}
		return label;
	}
	/**
	## 		Modification Log		:added DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO		
	##		Modify Date				: 20-01-2015
	##		Reason	(CR/PRS)		: Take patient detail from selected patient Vo
	##		Modify By				: Akash Singh
	*/
	public static void fetchDetailsForAutomaticGenerate(GenericPatientProfileFB fb, HttpServletRequest request, String patCrNo, String profileType, String deskId, String profileGenerationMode, String episodeCode, String episodeVisitNo, String autoProfileCalledFrom)
	{		
		// Getting Profile Based Menus
		HttpSession session =request.getSession();
		System.out.println("lenght of Profile Based desk menu List ***********"+session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST)+" profileType :"+profileType);
		UserVO userVO = getUserVO(request);
		List<DeskMenuMasterVO> lstAutoProMenus = new ArrayList<DeskMenuMasterVO>();;
		List<DeskMenuMasterVO> lstProMenus = null;
		lstProMenus = GenericPatientProfileDATA.getProfileBasedDeskMenus(profileType, deskId, userVO);
		Map tempRecordDates = new HashMap();
		List<Entry> lstTemps  = null;
		PatientDetailVO voDP = null;
		if(autoProfileCalledFrom.equals("2"))
		{
			voDP = new PatientDetailVO();
			PatientVO patVO = (PatientVO) request.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
			HelperMethods.populate(voDP,patVO);
			
			EpisodeVO[] episodeVO=(EpisodeVO[])request.getSession().getAttribute(RegistrationConfig.ARRAY_OPEN_EPISODE_VISITED_TODAY);
			for(int i=0;i<episodeVO.length;i++){
				if(episodeVO[i].getEpisodeCode().equals(episodeCode))
				{
					voDP.setEpisodeCode(episodeVO[i].getEpisodeCode());
					voDP.setEpisodeVisitNo(episodeVO[i].getEpisodeVisitNo());
					break;
				}
			}
		}
		else
		{

			PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			voDP = ptaientDetailVO;
			if(ptaientDetailVO == null)
			{
				PatientDetailVO[] al = (PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (PatientDetailVO vo : al)
					if (vo.getPatCrNo().equals(patCrNo))
					{
						voDP = vo;
						break;					
					}
			}
		}
		ProfileProforma proforma = new ProfileProforma();
		proforma.setHeader("Patient Episode Summary", "", voDP, userVO);
		proforma.setProfileType(profileType);
		List profileTypeList = (List) session.getAttribute(OpdConfig.PROFILE_TYPE_LIST);
		Iterator iteratorProfileTypeList = profileTypeList.iterator();
		while (iteratorProfileTypeList.hasNext())
		{
			Entry entry = (Entry) iteratorProfileTypeList.next();
			if ((fb.getProfileType() != null) && (fb.getProfileType().equals(entry.getValue())))
			{
				proforma.setProfileTypeDesc(entry.getLabel());
				break;
			}
		}

		WebUTIL.setAttributeInSession(request, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);

		// Getting Data Menu Wise
		for(DeskMenuMasterVO deskMenuMasterVO: lstProMenus)
		{	
			System.out.println("deskURL"+deskMenuMasterVO.getDeskUrl());
			System.out.println("Episode Code :"+voDP.getEpisodeCode()+"  episodeVisitNo :"+voDP.getEpisodeVisitNo());
			// Fetch Menu Data Passing By Menu Id and URL Based
			if(deskMenuMasterVO.getDeskUrl().equals("DESKALLERGIES"))
			{
				System.out.println("deskURL"+deskMenuMasterVO.getDeskUrl());
				if(GenericAutometicPatientProfileUTIL.DESKALLERGIES(fb, request, patCrNo, deskMenuMasterVO.getDeskMenuId(), userVO, proforma,profileGenerationMode ,voDP))
				{
					System.out.println("SET TRUE for DeskAllergies");
				}
			}
			else if(deskMenuMasterVO.getDeskUrl().equals("DESKDIAGNOSIS"))
			{				
				System.out.println("deskURL"+deskMenuMasterVO.getDeskUrl());
				if(GenericAutometicPatientProfileUTIL.DESKDIAGNOSIS(fb, request, patCrNo, profileType, deskMenuMasterVO.getDeskMenuId(), userVO, proforma, voDP, profileGenerationMode ))
				{
					System.out.println("SET TRUE for DeskDiagnosis");
				}
			}
			else if(deskMenuMasterVO.getDeskUrl().equals("GENERICPATIENTALERTS"))
			{
				if(GenericAutometicPatientProfileUTIL.GENERICPATIENTALERTS(fb, patCrNo,deskMenuMasterVO.getDeskMenuId(), request, userVO, proforma, voDP, profileGenerationMode ))
				{
					System.out.println("SET TRUE for Genric Patient Alerts");
				}
			}
			else if(deskMenuMasterVO.getDeskUrl().equals("DESKTREATMENTDETAIL"))
			{
				if(GenericAutometicPatientProfileUTIL.DESKTREATMENTDETAIL(fb, patCrNo, request,  deskMenuMasterVO.getDeskMenuId(), userVO, proforma, voDP, profileGenerationMode ))
				{
					System.out.println("SET TRUE for Desk Treatment Detail");
				}
			}
			else if(deskMenuMasterVO.getDeskUrl().equals("EXTERNALEXAMINATION"))
			{
				if(GenericAutometicPatientProfileUTIL.EXTERNALEXAMINATION(fb, patCrNo, request,  deskMenuMasterVO.getDeskMenuId(), userVO, proforma, voDP, profileGenerationMode ))
				{
					System.out.println("SET TRUE for External Examination");
				}
			}
			else if(deskMenuMasterVO.getDeskUrl().equals("RESULTVIEWING"))
			{
				if(GenericAutometicPatientProfileUTIL.RESULTVIEWING(fb, patCrNo, request,  deskMenuMasterVO.getDeskMenuId(), userVO, proforma, voDP, profileGenerationMode))
				{
					System.out.println("SET TRUE for Result viewing");
				}
			}
			else if(deskMenuMasterVO.getDeskUrl().equals("OTVIEWING"))
			{
				if(GenericAutometicPatientProfileUTIL.OTVIEWING(fb, patCrNo, request,  deskMenuMasterVO.getDeskMenuId(), userVO, proforma, voDP, profileGenerationMode))
				{
					System.out.println("SET TRUE for OT viewing");
				}
			}
			else if(deskMenuMasterVO.getDeskUrl().equals("IMAGEEXAMINATION"))
			{
				if(GenericAutometicPatientProfileUTIL.IMAGEEXAMINATION(fb, patCrNo, request,  deskMenuMasterVO.getDeskMenuId(), userVO, proforma, voDP, profileGenerationMode))
				{
					System.out.println("SET TRUE for Image Exam");
				}
			}
			else if(deskMenuMasterVO.getDeskUrl().equals("DOCTORROUND"))
			{
				if(GenericAutometicPatientProfileUTIL.DOCTORROUND(fb, patCrNo, request,  deskMenuMasterVO.getDeskMenuId(), userVO, proforma, voDP, profileGenerationMode))
				{
					System.out.println("SET TRUE for Result viewing");
				}
			}
			
			//Added by Vasu on 16.Apr.2019 to Add profile footer in case of Automatic Generated profile
			else if(deskMenuMasterVO.getDeskUrl().equals("PROFILEFOOTER"))
			{
				if(GenericAutometicPatientProfileUTIL.patientProfileFooter(fb, request, patCrNo, profileType, deskMenuMasterVO.getDeskMenuId(), userVO, proforma, voDP, profileGenerationMode ))
				{
					System.out.println("SET TRUE for Profile Footer");
				}
			}
			//End Vasu
			else if(deskMenuMasterVO.getDeskUrl().equals("GENERICTEMPLATE"))
			{
				if(GenericAutometicPatientProfileUTIL.GENERICTEMPLATE(fb, request, patCrNo, profileType, deskMenuMasterVO.getDeskMenuId(), userVO, proforma, voDP, profileGenerationMode ))
				{
					System.out.println("SET TRUE for Generic Template");
					List<Entry> mpDeskMenuVisitDates = (List)session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_VISIT_DATES);
						tempRecordDates.put(deskMenuMasterVO.getDeskMenuId(),mpDeskMenuVisitDates);
						UserDeskMenuTemplateMasterVO userDeskMenuTemplateMasterVO = new UserDeskMenuTemplateMasterVO();
						HelperMethods.populate(userDeskMenuTemplateMasterVO, fb);
						userDeskMenuTemplateMasterVO.setDeptUnitCode(voDP.getDepartmentUnitCode());
						userDeskMenuTemplateMasterVO.setDeskMenuId(deskMenuMasterVO.getDeskMenuId());

						lstTemps = GenericTemplateTileDATA.getDeskMenuTemplateList(fb.getDeskType(),fb.getPatCrNo(), userDeskMenuTemplateMasterVO, userVO);
						Map mpDeskMenuTemps = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS); 
						if(mpDeskMenuTemps==null)	mpDeskMenuTemps = new HashMap();
						mpDeskMenuTemps.put(deskMenuMasterVO.getDeskMenuId(), lstTemps);
						WebUTIL.setAttributeInSession(request, OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS, mpDeskMenuTemps);
				}
			}
		}
		WebUTIL.setAttributeInSession(request, OpdConfig.OPD_PATIENT_PROFILE_AUTO_MENU_LIST, lstProMenus);
		WebUTIL.setAttributeInSession(request, OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES, tempRecordDates);
		
	}
	
	public static boolean autometicGenrateProfile(GenericPatientProfileFB _fb,HttpServletRequest _rq) 
	{	
		boolean flag = false;
		String profileId = null;
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		List diagnosisTypeList = new ArrayList();
		List allergyTypeList = new ArrayList();
		List investigationTypeList = new ArrayList();
		List treatmentTypeList = new ArrayList();
		List alertsTypeList = new ArrayList();
		List otTypeList = new ArrayList();
		List imageTypeList = new ArrayList();
		List lstEpiProgNotes = new ArrayList();
		List lstExtInvs = new ArrayList();
		List footerTypeList = new ArrayList();
		List dischargefooterTypeList = new ArrayList();
		List lstDrugAdvice = new ArrayList();
		List lstDietAdvice = new ArrayList();
		List lstRestAdvice = new ArrayList();
		List lstOtherAdvice = new ArrayList();
		List lstOtherInstructions = new ArrayList();
		List lstOtherActivity = new ArrayList();
		// String resultHtml;
		String url = "";
		String remarks = "";
		String reviewOn = "", dischargeType="", dischargeAdvisedBy="", dischargeAdvisedDept="", snomdcid="", snomdpt="";
		String templateDeskMenuId = "";
		// String _profileType="";
		Map mpTempParaValues = null;
		Map inAllMap = new HashMap();
		Map mpTempParas = new HashMap();
		Map inAllPreviousMap = new HashMap();
		PatientProfileDetailVO patProfileDtlVO = null;
		DisclaimerMstVO disclaimerVO = new DisclaimerMstVO();
		ProfileFooterDtlVO _profileFooterDtlVO = new ProfileFooterDtlVO();
		Map lstVisitDates = null;
		Map mpDeskMenuReportMode = new HashMap();
		try
		{
			UserVO userVO = getUserVO(_rq);

			ProfileProforma proforma = (ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
			List lstProMenus = lstProMenus= (List) session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_AUTO_MENU_LIST);	
			if (proforma != null)
			{

				List lstMenus = proforma.getOptionsOrderList();
				for (int i = 0; i < lstMenus.size(); i++)
				{
					String key = (String) lstMenus.get(i);
					DeskMenuMasterVO vo = null;
					Iterator lstProMenusItr = lstProMenus.iterator();
					while (lstProMenusItr.hasNext())
					{
						DeskMenuMasterVO v = (DeskMenuMasterVO) lstProMenusItr.next();
						if (v.getDeskMenuId().equals(key))
						{
							vo = v;
							break;
						}
					}
					url = vo.getDeskUrl();
					System.out.println("URL :"+url);

					// Diagnosis Detail
					if (url.equals("DESKDIAGNOSIS"))
					{
						System.out.println("in DESKDIAGNOSIS");
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpDiag = mapProfileOptions.get(key);
						diagnosisTypeList = (List) mpDiag.get("lstEpisodeDiagnosisVO");
					}

					// Allergy Detail
					else if (url.equals("DESKALLERGIES"))
					{
						System.out.println("in DESKALLERGIES");
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpAllerg = mapProfileOptions.get(key);
						allergyTypeList = (List) mpAllerg.get("lstEpisodeAllergiesVO");
					}

					// Investigation Detail
					else if (url.equals("RESULTVIEWING"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpInv = mapProfileOptions.get(key);
						investigationTypeList = (List) mpInv.get("lstEpisodeInvestigationVO");
					}

					else if (url.equals("DESKTREATMENTDETAIL"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpTreat = mapProfileOptions.get(key);
						treatmentTypeList = (List) mpTreat.get("lstEpisodeTreatmentVO");
					}

					else if (url.equals("GENERICPATIENTALERTS"))
					{
						System.out.println("in GENERICPATIENTALERTS");
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpAlerts = mapProfileOptions.get(key);
						alertsTypeList = (List) mpAlerts.get("lstPatientAlertsVO");
					}
					else if (url.equals("PROFILEFOOTER"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpFooter = mapProfileOptions.get(key);
						remarks = (String) mpFooter.get("Remarks");
						reviewOn = (String) mpFooter.get("ReviewOn");
						dischargeType = (String) mpFooter.get("DichargeType");
						dischargeAdvisedBy = (String) mpFooter.get("DichargeAdvisedBy");
						dischargeAdvisedDept = (String) mpFooter.get("DichargeAdvisedDept");
						// _profileType=(String)mpFooter.get("ProfileType");
						disclaimerVO = (DisclaimerMstVO) mpFooter.get("Disclaimer");

						_profileFooterDtlVO.setProfileSummary(remarks);
						_profileFooterDtlVO.setReviewDate(reviewOn);
						
						_profileFooterDtlVO.setDischargeAdvisedBy(dischargeAdvisedBy);
						
						/*_profileFooterDtlVO.setDischargeType(dischargeType.split("#")[0]);
						_profileFooterDtlVO.setDischargeAdvisedBy(dischargeAdvisedBy.split("#")[0]);
						_profileFooterDtlVO.setDischargeAdvisedDept(dischargeAdvisedDept.split("#")[0]);*/
						/*
						 * _profileFooterDtlVO.setDisclaimerId(disclaimerVO.getDisclaimerId());
						 * _profileFooterDtlVO.setDisclaimerDesc1(disclaimerVO.getDisclaimerDesc1());
						 * _profileFooterDtlVO.setDisclaimerDesc2(disclaimerVO.getDisclaimerDesc2());
						 * _profileFooterDtlVO.setDisclaimerDesc3(disclaimerVO.getDisclaimerDesc3());
						 */
						footerTypeList.add(_profileFooterDtlVO);
					}
					else if (url.equals("DISCHARGEPROFILEFOOTER"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpFooter = mapProfileOptions.get(key);
						remarks = (String) mpFooter.get("Remarks");
						reviewOn = (String) mpFooter.get("ReviewOn");
						dischargeType = (String) mpFooter.get("DichargeType");
						dischargeAdvisedBy = (String) mpFooter.get("DichargeAdvisedBy");
						dischargeAdvisedDept = (String) mpFooter.get("DichargeAdvisedDept");
						// _profileType=(String)mpFooter.get("ProfileType");
						disclaimerVO = (DisclaimerMstVO) mpFooter.get("Disclaimer");
						snomdcid=(String) mpFooter.get("SnomdCIdRemarks");
						snomdpt=(String) mpFooter.get("SnomdPTRemarks");
						_profileFooterDtlVO.setProfileSummary(remarks);
						_profileFooterDtlVO.setReviewDate(reviewOn);
						_profileFooterDtlVO.setDischargeType(dischargeType.split("#")[0]);
						_profileFooterDtlVO.setDischargeAdvisedBy(dischargeAdvisedBy.split("#")[0]);
						_profileFooterDtlVO.setDischargeAdvisedDept(dischargeAdvisedDept.split("#")[0]);

						_profileFooterDtlVO.setDisclaimerId(disclaimerVO.getDisclaimerId());
						_profileFooterDtlVO.setDisclaimerDesc1(disclaimerVO.getDisclaimerDesc1());
						_profileFooterDtlVO.setDisclaimerDesc2(disclaimerVO.getDisclaimerDesc2());
						_profileFooterDtlVO.setDisclaimerDesc3(disclaimerVO.getDisclaimerDesc3());
						_profileFooterDtlVO.setSnomdCIdRemarks(snomdcid);
						_profileFooterDtlVO.setSnomdPTRemarks(snomdpt);
					


						dischargefooterTypeList.add(_profileFooterDtlVO);
					}
					else if (url.equals("ADVICEONDISCHARGE"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpDrugAdvice = mapProfileOptions.get(key);
						lstDrugAdvice = (List) mpDrugAdvice.get("lstPatientDrugAdviceVO");
						lstDietAdvice = (List) mpDrugAdvice.get("lstPatientDietAdviceVO");
						lstRestAdvice = (List) mpDrugAdvice.get("lstPatientRestAdviceVO");
						lstOtherAdvice = (List) mpDrugAdvice.get("lstPatientOtherAdviceVO");
						lstOtherInstructions = (List) mpDrugAdvice.get("lstPatientOtherInstructionsVO");
						lstOtherActivity = (List) mpDrugAdvice.get("lstPatientOtherActivityVO");
					}

					else if (url.equals("GENERICTEMPLATE"))
					{
						// Getting Desk Menu Wise
						mpTempParaValues = (HashMap) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);
//						Map mpDeskMenuTempParaValues = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP); 
//						mpTempParaValues = (HashMap) mpDeskMenuTempParaValues.get(vo.getDeskMenuId());

						templateDeskMenuId = (String) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_TEMPLATE_DESK_ID);
						templateDeskMenuId = vo.getDeskMenuId();
						
						// Getting Desk Menu Wise
						mpTempParas = (HashMap) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
//						Map mpDeskMenuTempParas = (Map) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP); 
//						if(mpDeskMenuTempParas!=null)
//							mpTempParas = (HashMap) mpDeskMenuTempParas.get(vo.getDeskMenuId());
						
						// Getting Desk Menu Wise
						lstVisitDates = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES);
//						Map mpDeskMenuVisitDates = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES); 
//						lstVisitDates = (List<Entry>) mpDeskMenuVisitDates.get(vo.getDeskMenuId());
						// Getting Desk Menu Wise Report View Modes 
						mpDeskMenuReportMode = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP);						
					}
					else if (url.equals("OTVIEWING"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpOperation = mapProfileOptions.get(key);
						otTypeList = (List) mpOperation.get("lstOperationDetailVO");
					}

					else if (url.equals("IMAGEEXAMINATION"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpImage = mapProfileOptions.get(key);
						imageTypeList = (List) mpImage.get("lstOpdPatientImageDtlVO");
					}

					else if (url.equals("DOCTORROUND"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpProgressNotes = mapProfileOptions.get(key);
						lstEpiProgNotes = (List) mpProgressNotes.get("lstPatEpiProgressNotesDtlVO");
					}

					else if (url.equals("EXTERNALEXAMINATION"))
					{
						Map<String, Map<String, Object>> mapProfileOptions = proforma.getMapProfileOptions();
						Map<String, Object> mpExtInvestigation = mapProfileOptions.get(key);
						lstExtInvs = (List) mpExtInvestigation.get("lstPatientExtInvDtlVO");
					}
				}

			}

			Map drugScheduleMap = (Map) session.getAttribute(OpdConfig.DRUG_SHEDULE_MAP);

			inAllMap.put("DESKDIAGNOSIS", diagnosisTypeList);
			inAllMap.put("DESKALLERGIES", allergyTypeList);
			inAllMap.put("RESULTVIEWING", investigationTypeList);
			inAllMap.put("DESKTREATMENTDETAIL", treatmentTypeList);
			inAllMap.put("GENERICPATIENTALERTS", alertsTypeList);
			inAllMap.put("PROFILEFOOTER", footerTypeList);
			inAllMap.put("DISCHARGEPROFILEFOOTER", dischargefooterTypeList);
			inAllMap.put("DRUGADVICE", lstDrugAdvice);
			inAllMap.put("DRUGSCHEDULE", drugScheduleMap);
			inAllMap.put("DIETADVICE", lstDietAdvice);
			inAllMap.put("RESTADVICE", lstRestAdvice);
			inAllMap.put("OTHERADVICE", lstOtherAdvice);
			inAllMap.put("OTHERINSTRUCTIONS", lstOtherInstructions);
			inAllMap.put("OTHERACTIVITY", lstOtherActivity);
			inAllMap.put("GENERICTEMPLATE", mpTempParaValues);
			inAllMap.put("GENERICTEMPLATE_DESKMENUID", templateDeskMenuId);
			inAllMap.put("PARACOMPLAINTS", mpTempParas);
			inAllMap.put("SELECTEDDATES", lstVisitDates);
			inAllMap.put("REPORTMODES", mpDeskMenuReportMode);
			inAllMap.put("OTVIEWING", otTypeList);
			inAllMap.put("IMAGEEXAMINATION", imageTypeList);
			inAllMap.put("DOCTORROUND", lstEpiProgNotes);
			inAllMap.put("EXTERNALEXAMINATION", lstExtInvs);

			// Previous Details For Desk Diagnosis
			ProfileDiagnosisDtlVO[] profileDiagnosisDtlVO = (ProfileDiagnosisDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DIAGNOSIS_DTL_VO_ARRAY);
			inAllPreviousMap.put("DESKDIAGNOSIS", profileDiagnosisDtlVO);

			// Previous Details For Desk Allergy
			ProfileAllergyDtlVO[] _profileAllergyDtlVO = (ProfileAllergyDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_ALLERGY_DTL_VO_ARRAY);
			inAllPreviousMap.put("DESKALLERGIES", _profileAllergyDtlVO);

			// Previous Details For Desk Investigation
			ProfileInvDtlVO[] _profileInvDtlVO = (ProfileInvDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_INVESTIGATION_DTL_VO_ARRAY);
			inAllPreviousMap.put("RESULTVIEWING", _profileInvDtlVO);

			// Previous Details For Desk Treatment
			ProfileDrugAdviceDtlVO[] _profileDrugAdviceDtlVO = (ProfileDrugAdviceDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_TREATMENT_DTL_VO_ARRAY);
			inAllPreviousMap.put("DESKTREATMENTDETAIL", _profileDrugAdviceDtlVO);

			// Previous Details For Patient Alerts
			ProfileAlertsDtlVO[] profileAlertsDtlVO = (ProfileAlertsDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_ALERTS_DTL_VO_ARRAY);
			inAllPreviousMap.put("GENERICPATIENTALERTS", profileAlertsDtlVO);

			// Previous Details For Profile Footer
			ProfileFooterDtlVO[] profileFooterDetail = (ProfileFooterDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_FOOTER_DTL_VO_ARRAY);
			inAllPreviousMap.put("PROFILEFOOTER", profileFooterDetail);

			// Previous Details For Discharge Profile Footer
			ProfileFooterDtlVO[] dischargeProfileFooterDetail = (ProfileFooterDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_FOOTER_DTL_VO_ARRAY);
			inAllPreviousMap.put("DISCHARGEPROFILEFOOTER", dischargeProfileFooterDetail);

			// Previous Details for Advice On Discharge
			ProfileDrugAdviceDtlVO[] profileDrugAdviceDtlVO = (ProfileDrugAdviceDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_DRUG_DTL_VO_ARRAY);
			inAllPreviousMap.put("DRUGADVICE", profileDrugAdviceDtlVO);
			ProfileDietAdviceDtlVO profileDietAdviceDtlVO = (ProfileDietAdviceDtlVO) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_DIET_ADVICE_DTL_VO);
			inAllPreviousMap.put("DIETADVICE", profileDietAdviceDtlVO);
			ProfileRestAdviceDtlVO profileRestAdviceDtlVO = (ProfileRestAdviceDtlVO) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_REST_ADVICE_DTL_VO);
			inAllPreviousMap.put("RESTADVICE", profileRestAdviceDtlVO);
			ProfileExtTreatmentDtlVO[] _profileExtTreatmentDtlVO = (ProfileExtTreatmentDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_EXT_TREATMENT_DTL_VO_ARRAY);
			inAllPreviousMap.put("OTHERADVICE", _profileExtTreatmentDtlVO);
			ProfileExtTreatmentDtlVO[] profileExtTreatmentDtlVO = (ProfileExtTreatmentDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_OTHER_INSTRUCTIONS_DTL_VO_ARRAY);
			inAllPreviousMap.put("OTHERINSTRUCTIONS", profileExtTreatmentDtlVO);
			ProfileExtTreatmentDtlVO[] profileExtTreatmentDtlVOs = (ProfileExtTreatmentDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_DISCHARGE_OTHER_ACTIVITY_DTL_VO_ARRAY);
			inAllPreviousMap.put("OTHERACTIVITY", profileExtTreatmentDtlVOs);
			List profileDrugScheduleDtlVOList = (List) session.getAttribute(OpdConfig.PATIENT_PROFILE_DRUG_SCHEDULE_DTL_VO_LIST);
			inAllPreviousMap.put("DRUGSCHEDULE", profileDrugScheduleDtlVOList);

			// Previous Details For Profile Clinical
			ProfileClinicalDtlVO[] profileClinicalDetailVO = (ProfileClinicalDtlVO[]) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_COMPLAINTS_DTL_VO_ARRAY);
			inAllPreviousMap.put("GENERICTEMPLATE", profileClinicalDetailVO);

			// to get the desk menu id in case of complaints
			UserDeskMenuTemplateMasterVO userDeskMenuTemplateMasterVO = (UserDeskMenuTemplateMasterVO) session
					.getAttribute(OpdConfig.PATIENT_PROFILE_USER_DESK_MENU_TEMPLATE_MASTER_VO);

			// Previous Details For Desk OT
			ProfileOTDtlVO[] profileOTDtlVOs = (ProfileOTDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_OT_DTL_VO_ARRAY);
			inAllPreviousMap.put("OTVIEWING", profileOTDtlVOs);

			// Previous Details for Profile Image
			ProfileImageDtlVO[] profileImageDtlVOs = (ProfileImageDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_IMAGE_DTL_VO_ARRAY);
			inAllPreviousMap.put("IMAGEEXAMINATION", profileImageDtlVOs);

			// Previous Details for Profile Progress Notes
			ProfileProgressNotesDtlVO[] profileEpiProgNtesDtlVOs = (ProfileProgressNotesDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROGRESS_NOTES_DTL_VO_ARRAY);
			inAllPreviousMap.put("DOCTORROUND", profileEpiProgNtesDtlVOs);

			// Previous Details for Profile External Investigation
			ProfileExtExamDtlVO[] profileExtInvDtlVOs = (ProfileExtExamDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_EXT_INV_DTL_VO_ARRAY);
			inAllPreviousMap.put("EXTERNALEXAMINATION", profileExtInvDtlVOs);

			patProfileDtlVO = new PatientProfileDetailVO();
			
			HelperMethods.populate(patProfileDtlVO, _fb);
			patProfileDtlVO.setProfileType(proforma.getProfileType());
			patProfileDtlVO.setProfileStatus(OpdConfig.HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_GENERATED);						
			// patProfileDtlVO.setProfileData(resultHtml);
			profileId = GenericPatientProfileDATA.savePatientProfile(patProfileDtlVO, inAllMap, inAllPreviousMap, userDeskMenuTemplateMasterVO, userVO);
			System.out.println("Profile Id in Util  :"+profileId);
			
			//To create HTML File in PHDM\AHIMS\PatientProfileDIR 
			_fb.setProfileId(profileId);
			
			GenericAutometicPatientProfileUTIL.PRINTHTML(_fb, _rq, userVO);
			flag = true;
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}
	
	
	public static String getViewDoc(GenericPatientProfileFB fb,	HttpServletRequest request, HttpServletResponse response_p) 
	{
		InputStream inputStream = null;
		BufferedOutputStream bos = null;
		String s ="";
		byte[] getDoc;
		try
		{
			String filename=fb.getProfileId()+Config.PATIENT_PROFILE_FILE_STORAGE_EXT;
			getDoc= MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_GENERATED_PATIENT_PROFILE_UPLOAD).latestFetchFile(filename);
			s = new String(getDoc);
						
		}
		catch (Exception e)
		{
	    e.printStackTrace();
	    String msg="<B>This file does not exist:: Please Contact Administrator</B>";
		byte[] bytes=msg.getBytes();
		response_p.setHeader("Pragma", "no-cache");
			try
			{
				bos.write(bytes, 0, bytes.length);
			}catch(Exception ee)
			{
			}
			
		}
	/*finally
	{
		try
		{
			if(inputStream!=null) inputStream.close();
			response_p.getOutputStream().flush();
			if(bos!=null)	bos.close();
		}
	catch(Exception e)
		{
		}
	}*/
		return s;
	}
	
	//Added by VASU on 6-Nov-2017 for Getting Patient Investigation Test Detail
	public static boolean getPatientInvestigationTestDetail(GenericPatientProfileFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		boolean flag = true;
		List investigationTypeList = new ArrayList();
		String url = "";
		String _reqDno="";
		String age="";
		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);
			String[] patAge=_fb.getStrHiddenPatDtl().split("\\^");

			 _reqDno=_fb.getReqDnoList().substring(0, _fb.getReqDnoList().length() - 1);
			
			try{
			age=patAge[3].split(" ")[0];
			}catch(Exception e){
				System.out.println("Exception error to getting a age ");
			}
			
			_fb.setSelectedRow(new String[]
			{ "" });
			ProfileInvestigationVO[] profileInvestigationVOs = GenericPatientProfileDATA.getEpisodeTestInvestigation(_fb.getPatCrNo(), _fb.getEpisodeCode(), _fb.getAdmissionNo(), _fb.getDeskType(), userVO,age,_reqDno);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_INVESTIGATION_PROFILE, profileInvestigationVOs);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	
	//Added by Dheeraj on 05-Dec-2018 to get Patient Profile from Postgres
	
			public static void viewPatientProfile(GenericPatientProfileFB fb,	HttpServletRequest request, HttpServletResponse response_p) 
			{
				Status objStatus = new Status();
				InputStream inputStream = null;
				BufferedOutputStream bos = null;
				
				HttpSession session = WebUTIL.getSession(request);
				List<PatientProfileDetailVO> lstPrevProfiles = null;
				PatientProfileDetailVO patProfileDtlVO = null;
				
				lstPrevProfiles = (List<PatientProfileDetailVO>) session.getAttribute(OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST);
				patProfileDtlVO = lstPrevProfiles.get(0);
				for (int i = 0; i < fb.getSelectedIndex().length; i++)
				{
					int index = Integer.parseInt(fb.getSelectedIndex()[i]);
					patProfileDtlVO = lstPrevProfiles.get(index);
				}
				
				try
				{
				
						byte[] getDoc = OpdPatientBO.fetchPatientProfile(patProfileDtlVO); 
						
						response_p.setContentType("application/pdf");
				        
						OutputStream os = response_p.getOutputStream();
						bos = new BufferedOutputStream(os);
						inputStream = new ByteArrayInputStream(getDoc);
						int c;
						while ((c = inputStream.read()) != -1)
						{
							os.write(c);
						}    
						
				}
				catch (HisRecordNotFoundException e)
				{
					e.printStackTrace();
					objStatus.add(Status.LIST, e.getMessage(), "");
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
				/*catch (Exception e)
				{
					e.printStackTrace();
					objStatus.add(Status.ERROR_AE, e.getMessage(), "");
				}*/
				catch (Exception e)
				{
					e.printStackTrace();
					String msg="<B>This file does not exist:: Please Contact Administrator</B>";
					byte[] bytes=msg.getBytes();
					response_p.setHeader("Pragma", "no-cache");
					try
					{
						bos.write(bytes, 0, bytes.length);
					}catch(Exception ee)
					{
					}
					
				}
				finally
				{
					/*WebUTIL.setStatus(request, objStatus);*/
					try
					{
						if(inputStream!=null) inputStream.close();
						response_p.getOutputStream().flush();
						if(bos!=null)	bos.close();
					}
					catch(Exception e)
					{
					}
				}
			}
			
			
			
			public static String viewHtmlPatientProfile(GenericPatientProfileFB fb,	HttpServletRequest request, HttpServletResponse response_p) 
			{
				Status objStatus = new Status();
				InputStream inputStream = null;
				BufferedOutputStream bos = null;
				String htmlData = "";
				int profile= 0;
				
				HttpSession session = WebUTIL.getSession(request);
				List<PatientProfileDetailVO> lstPrevProfiles = null;
				PatientProfileDetailVO patProfileDtlVO = null;
				
				lstPrevProfiles = (List<PatientProfileDetailVO>) session.getAttribute(OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST);
				patProfileDtlVO = lstPrevProfiles.get(0);
				for (int i = 0; i < lstPrevProfiles.size(); i++)
				{
					patProfileDtlVO = lstPrevProfiles.get(i);
					if(fb.getProfileHeader().equals(patProfileDtlVO.getProfileHeader()))
					{
						if(fb.getProfileId().equals(patProfileDtlVO.getProfileId()))
						{
							patProfileDtlVO = lstPrevProfiles.get(i);
							profile=1;
						}
					}
					if(profile==1)
					{
						break;
					}
				}
				
				try
				{
				
						htmlData = OpdPatientBO.fetchHtmlPatientProfile(patProfileDtlVO); 
						
				}
				catch (HisRecordNotFoundException e)
				{
					e.printStackTrace();
					objStatus.add(Status.LIST, e.getMessage(), "");
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
				/*catch (Exception e)
				{
					e.printStackTrace();
					objStatus.add(Status.ERROR_AE, e.getMessage(), "");
				}*/
				catch (Exception e)
				{
					e.printStackTrace();
					String msg="<B>This file does not exist:: Please Contact Administrator</B>";
					byte[] bytes=msg.getBytes();
					response_p.setHeader("Pragma", "no-cache");
					try
					{
						bos.write(bytes, 0, bytes.length);
					}catch(Exception ee)
					{
					}
					
				}
				
				return htmlData;
			}

			//Added by Vasu on 30.May.2019 to get single page PDF view
			public static void getViewSinglePageDoc(HttpServletRequest request,HttpServletResponse response, GenericPatientProfileFB fb)
			{
				Status objStatus = new Status();
				InputStream inputStream = null;
				BufferedOutputStream bos = null;
				String htmlData = "";
				int profile= 0;
				
				HttpSession session = WebUTIL.getSession(request);
				List<PatientProfileDetailVO> lstPrevProfiles = null;
				PatientProfileDetailVO patProfileDtlVO = null;
				
				if(fb.getIsCallFromEMR()!=null && fb.getIsCallFromEMR().equals("1"))
				{
				lstPrevProfiles = (List<PatientProfileDetailVO>) session.getAttribute(MrdConfig.PATIENT_PROFILE_DTL_VO_ARRAY);
				}
				else
				{
				lstPrevProfiles = (List<PatientProfileDetailVO>) session.getAttribute(OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST);
				}
				
				patProfileDtlVO = lstPrevProfiles.get(0);
				for (int i = 0; i < lstPrevProfiles.size(); i++)
				{
					patProfileDtlVO = lstPrevProfiles.get(i);
					if(fb.getProfileHeader().equals(patProfileDtlVO.getProfileHeader()))
					{
						if(fb.getProfileId().equals(patProfileDtlVO.getProfileId()))
						{
							patProfileDtlVO = lstPrevProfiles.get(i);
							profile=1;
						}
					}
					if(profile==1)
					{
						break;
					}
				}
				
				try
				{
				
						//htmlData = OpdPatientBO.fetchHtmlPatientProfile(patProfileDtlVO); 
						byte[] getDoc = OpdPatientBO.fetchPDFFromPostgres(patProfileDtlVO); 
						
						response.setContentType("application/pdf");
						OutputStream os = response.getOutputStream();
						bos = new BufferedOutputStream(os);
						inputStream = new ByteArrayInputStream(getDoc);
						int c;
						while ((c = inputStream.read()) != -1)
						{
							os.write(c);
						}    
						
				}
				catch (HisRecordNotFoundException e)
				{
					e.printStackTrace();
					objStatus.add(Status.LIST, e.getMessage(), "");
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
					String msg="<B>This file does not exist:: Please Contact Administrator</B>";
					byte[] bytes=msg.getBytes();
					response.setHeader("Pragma", "no-cache");
					try
					{
						bos.write(bytes, 0, bytes.length);
					}catch(Exception ee)
					{
					}
					
				}
				finally
				{
					try
					{
						if(inputStream!=null) inputStream.close();
						response.getOutputStream().flush();
						if(bos!=null)	bos.close();
					}
					catch(Exception e)
					{
					}
				}
				
			}			
			
}
