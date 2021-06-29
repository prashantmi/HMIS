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
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.EpisodeRefDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileTypeMstVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.OpdReferPatientDATA;
import opd.transaction.controller.fb.OpdReferPatientFB;
import registration.RegistrationConfig;

public class OpdReferPatientUTIL extends ControllerUTIL
{

	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void getEssentialsByChoice(OpdReferPatientFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		Map essentialMap = new HashMap();
		HttpSession session = WebUTIL.getSession(_rq);
		List<PatientProfileDetailVO> lstPrevProfiles = null;
		boolean flag = false;
		PatientDetailVO[] dailyPatientVOs = null;
		int i = 0;
		String profileBound = "";
		try
		{
			/*dailyPatientVO = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			for (; i < dailyPatientVO.length; i++)
			{
				if (dailyPatientVO[i].getPatCrNo().equals(_fb.getPatCrNo()))
				{
					flag = true;
					break;
				}
			}
			PatientDetailVO[] al = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO voPD = new PatientDetailVO();
			for (int j = 0; j < al.length; j++)
			{
				voPD = (PatientDetailVO) al[j];
				if (voPD.getPatCrNo().equals(_fb.getPatCrNo())) break;
			}*/
			
			PatientDetailVO voPD = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			flag = true;
			if(voPD == null || !voPD.getPatCrNo().equals(_fb.getPatCrNo()))
			{
				flag = false;
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (i = 0; i < dailyPatientVOs.length; i++)
				{
					if (_fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))					{
						flag = true;
						voPD = dailyPatientVOs[i];
						break;
					}
				}
			}
			_fb.setAdmissionNo(voPD.getPatAdmNo());
			// Desk Type, Desk Id
			_fb.setDeskType((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE));
			_fb.setDeskId((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ID));
			if (flag)
			{
				_fb.setFromDepartment(voPD.getDepartment());
				_fb.setFromDepartmentCode(voPD.getDepartmentCode());
				_fb.setFromDepartmentUnit(voPD.getDepartmentUnit());
				_fb.setFromDepartmentUnitCode(voPD.getDepartmentUnitCode());
				_fb.setEpisodeCode(voPD.getEpisodeCode());
				_fb.setEpisodeVisitNo(voPD.getEpisodeVisitNo());

				if (voPD.getIsBroughtDead() != null
						&& voPD.getIsBroughtDead().equals(RegistrationConfig.PATIENT_BROUGHT_DEAD_TRUE))
				{
					_fb.setIsPatDead(RegistrationConfig.YES);
					throw new HisRecordNotFoundException("Patient is Dead. You Cannot Refer Dead Patient");
				}
				else _fb.setIsPatDead(RegistrationConfig.NO);
			}
			//System.out.println("desk type "+_fb.getDeskType());
			essentialMap = OpdReferPatientDATA.getopeReferPatientEssentials(_fb.getPatCrNo(), _fb.getFromDepartmentCode(), getUserVO(_rq), _fb.getDeskType(), _fb.getEpisodeCode());
			
			// Removing Current Department from Department Refer List
			List<Entry> lstAllDepartment = (List<Entry>) essentialMap.get(RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT);
			if (voPD.getDepartmentUnitType().equals(RegistrationConfig.UNIT_TYPE_GENERAL))
				for(Entry entry : lstAllDepartment)
					if (_fb.getFromDepartmentCode().equals(entry.getValue()))
					{
						lstAllDepartment.remove(entry);
						break;
					}
			if(lstAllDepartment.size()==0)	_fb.setSetReferDept(false);

			// Removing Current Special Unit from Special Unit Refer List
			List<Entry> lstUnit = (List<Entry>) essentialMap.get(OpdConfig.OPD_UNIT_WITH_SPECIALITY);
			if (voPD.getDepartmentUnitType().equals(RegistrationConfig.UNIT_TYPE_SPECIALITY))
				for(Entry entry : lstUnit)
					if (_fb.getFromDepartmentUnitCode().equals(entry.getValue()))
					{
						lstUnit.remove(entry);
						break;
					}
			if(lstUnit.size()==0)	_fb.setSetReferSpecialUnit(false);

			// Removing Current Department Unit from Same Department Unit Refer List
			List<Entry> lstDeptUnits = (List<Entry>) essentialMap.get(OpdConfig.OPD_ESSENTIALBO_LIST_DEPARTMENTS_GENERAL_UNITS);
			if (voPD.getDepartmentUnitType().equals(RegistrationConfig.UNIT_TYPE_GENERAL))
				for(Entry entry : lstDeptUnits)
					if (_fb.getFromDepartmentUnitCode().equals(entry.getValue()))
					{
						lstDeptUnits.remove(entry);
						break;
					}
			if(lstDeptUnits==null || lstDeptUnits.size()==0)	_fb.setSetReferSameDeptUnit(false);
			
			// Setting Internal Refer Option
			if(!_fb.isSetReferDept() && !_fb.isSetReferSpecialUnit() && !_fb.isSetReferSameDeptUnit())
				_fb.setSetInternalRefer(false);
			
			// Refer Other Hospitals
			List<Entry> lstOtherHospital = (List<Entry>) essentialMap.get(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL);
			if(lstOtherHospital==null || lstOtherHospital.size()==0)	_fb.setSetReferOtherHos(false);
						
			WebUTIL.setMapInSession(essentialMap, _rq);
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

	public static void saveReferPatientDetail(OpdReferPatientFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		Map essentialMap = new HashMap();
		HttpSession session = WebUTIL.getSession(_rq);
		PatientDetailVO[] dailyPatientVO = null;
		EpisodeRefDtlVO[] episodeRefVO = new EpisodeRefDtlVO[1];
		boolean flag = false;
		int i = 0;
		try
		{
			PatientDetailVO patientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(patientDetailVO == null || !patientDetailVO.getPatCrNo().equals(_fb.getPatCrNo()))
			{
				dailyPatientVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (; i < dailyPatientVO.length; i++)
				{
					if (dailyPatientVO[i].getPatCrNo().equals(_fb.getPatCrNo()))
					{
						patientDetailVO = dailyPatientVO[i];
						flag = true;
						break;
					}
				}
			}
			else
				flag = true;

			// For External/////////////////////////////////////////////
			if (_fb.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_REFER_EXTERNAL))
			{
				episodeRefVO[0] = new EpisodeRefDtlVO();
				if (flag) HelperMethods.populate(episodeRefVO[0], patientDetailVO);

				episodeRefVO[0].setIsRefferInOut(_fb.getIsRefferInOut());

				episodeRefVO[0].setExternalHospitalDepartment(_fb.getPatRefGnctdHospitalDept());
				episodeRefVO[0].setExternalHospitalDepartmentUnit(_fb.getPatRefGnctdHospitalDeptUnit());

				episodeRefVO[0].setExternalHospitalName(_fb.getPatRefHospitalName());
				if (_fb.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_TRUE))
					episodeRefVO[0].setExternalHospitalCode(_fb.getPatRefGnctdHospitalCode());
				else if (_fb.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_FALSE)) 
					episodeRefVO[0].setExternalHospitalCode("");

				episodeRefVO[0].setRemarks(_fb.getRemarks());
				episodeRefVO[0].setSystemIPAddress(_rq.getRemoteAddr());
				episodeRefVO[0].setFromDepartmentCode(patientDetailVO.getDepartmentCode());
				episodeRefVO[0].setFromDepartmentUnitCode(patientDetailVO.getDepartmentUnitCode());
			}

			// For Internal/////////////////////////////
			if (_fb.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL))
			{
				// Internal department
				if (_fb.getChoice().equalsIgnoreCase(OpdConfig.PATIENT_INTERNAL_REFER_TYPE_DEPARTMENT))
				{
					episodeRefVO = new EpisodeRefDtlVO[_fb.getDepartmentCode().length];
					for (int m = 0; m < _fb.getDepartmentCode().length; m++)
					{
						episodeRefVO[m] = new EpisodeRefDtlVO();
						if (flag) HelperMethods.populate(episodeRefVO[m], patientDetailVO);

						episodeRefVO[m].setToDepartmentCode(_fb.getDepartmentCode()[m]);
						episodeRefVO[m].setSystemIPAddress(_rq.getRemoteAddr());
						episodeRefVO[m].setIsRefferInOut(_fb.getIsRefferInOut());
						episodeRefVO[m].setFromDepartmentCode(patientDetailVO.getDepartmentCode());
						episodeRefVO[m].setFromDepartmentUnitCode(patientDetailVO.getDepartmentUnitCode());
						episodeRefVO[m].setRemarks(_fb.getDeptRemarks()[m]);
					}
				}
				// Internal Speacial Clinic Unit
				else if (_fb.getChoice().equalsIgnoreCase(OpdConfig.PATIENT_INTERNAL_REFER_TYPE_SPECIAL_UNIT))
				{
					episodeRefVO = new EpisodeRefDtlVO[_fb.getDepartmentUnitCode().length];
					for (int n = 0; n < _fb.getDepartmentUnitCode().length; n++)
					{
						episodeRefVO[n] = new EpisodeRefDtlVO();
						if (flag) HelperMethods.populate(episodeRefVO[n], patientDetailVO);

						episodeRefVO[n].setToDepartmentCode(_fb.getDepartmentUnitCode()[n].substring(0, 3));
						episodeRefVO[n].setToDepartmentUnitCode(_fb.getDepartmentUnitCode()[n]);
						episodeRefVO[n].setSystemIPAddress(_rq.getRemoteAddr());
						episodeRefVO[n].setIsRefferInOut(_fb.getIsRefferInOut());
						episodeRefVO[n].setFromDepartmentCode(patientDetailVO.getDepartmentCode());
						episodeRefVO[n].setFromDepartmentUnitCode(patientDetailVO.getDepartmentUnitCode());
						episodeRefVO[n].setRemarks(_fb.getUnitRemarks()[n]);

					}
				}
				// Internal Same Department Unit
				else if (_fb.getChoice().equalsIgnoreCase(OpdConfig.PATIENT_INTERNAL_REFER_TYPE_SAME_DEPT_UNIT))
				{
					episodeRefVO = new EpisodeRefDtlVO[_fb.getSameDeptUnitCode().length];
					for (int n = 0; n < _fb.getSameDeptUnitCode().length; n++)
					{
						episodeRefVO[n] = new EpisodeRefDtlVO();
						if (flag) HelperMethods.populate(episodeRefVO[n], patientDetailVO);

						episodeRefVO[n].setToDepartmentCode(_fb.getSameDeptUnitCode()[n].substring(0, 3));
						episodeRefVO[n].setToDepartmentUnitCode(_fb.getSameDeptUnitCode()[n]);
						episodeRefVO[n].setSystemIPAddress(_rq.getRemoteAddr());
						episodeRefVO[n].setIsRefferInOut(_fb.getIsRefferInOut());
						episodeRefVO[n].setFromDepartmentCode(patientDetailVO.getDepartmentCode());
						episodeRefVO[n].setFromDepartmentUnitCode(patientDetailVO.getDepartmentUnitCode());
						episodeRefVO[n].setRemarks(_fb.getSameDeptUnitRemarks()[n]);

					}
				}
			}

			// Fetching Desk Type
			String deskType = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);

			OpdReferPatientDATA.saveOpdReferPatient(episodeRefVO, getUserVO(_rq), deskType);
			objStatus.add(Status.DONE, "", "Patient Refered");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
			essentialMap = e.getEssentialMap();
			WebUTIL.setMapInSession(essentialMap, _rq);
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
