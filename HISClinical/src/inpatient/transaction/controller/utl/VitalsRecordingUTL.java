package inpatient.transaction.controller.utl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.utility.generictemplate.InformationControlBean;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientMonitoringMstVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;

import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.MonitorVitalsDATA;
import inpatient.transaction.controller.data.VitalsRecordingDATA;
import inpatient.transaction.controller.fb.VitalsRecordingFB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import registration.controller.fb.CRNoFB;

public class VitalsRecordingUTL extends ControllerUTIL
{
	public static boolean getEssentails(VitalsRecordingFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		Map mpEssentials = null;
		try
		{
			UserVO userVO = getUserVO(_request);
			session = _request.getSession();
			setSysdate(_request);

			String deskType=(String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
			
			// Getting Patient Detail
			CRNoFB crnoFB = new CRNoFB();
			crnoFB.setPatCrNo(_fb.getPatCrNo());
			InpatientDetailUTL.getInpatientDetailByCrNo(crnoFB, _request);
			PatientDetailVO patDtlVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);

			// Setting Template Essentials
				// Patient Information in Session for Template
				InformationControlBean infoBean = new InformationControlBean();
				infoBean.putPatientDetailValues(patDtlVO);
			WebUTIL.setAttributeInSession(_request, GenericTemplateConfig.GENERIC_TEMPLATE_INFORMATION_BEAN, infoBean);
			WebUTIL.setAttributeInSession(_request, GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_GENDER_TYPE, patDtlVO.getPatGenderType());
				// Age of Patient in Integer
				Date sysdate = (Date) session.getAttribute(Config.SYSDATEOBJECT);
				Date dob = WebUTIL.getDateFromString(patDtlVO.getPatDOB(), "dd-MMM-yyyy");
				long diff = sysdate.getTime() - dob.getTime();
				int age = (int)(diff / ((long)1000 * 60 * 60 * 24 * 365));
				if(age<=0)	age=1;
			WebUTIL.setAttributeInSession(_request, GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_AGE, Integer.toString(age));

			
			
			// Getting Template Parameter List
			UserDeskMenuTemplateMasterVO userDeskTempVO=new UserDeskMenuTemplateMasterVO();
			HelperMethods.populate(userDeskTempVO, _fb);	
			
			List<Entry> lstTemplates = (List<Entry>) session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_ALL_TEMPLATE_LIST+_fb.getDeskMenuId());
			List<Entry> templateParaList = MonitorVitalsDATA.getEssentails(lstTemplates, userVO);

			PatientMonitoringMstVO patMonitringMstVO = new PatientMonitoringMstVO();
			HelperMethods.populate(patMonitringMstVO, _fb);
			PatientClinicalDetailVO patClinicalDtlVO=new PatientClinicalDetailVO();						
			HelperMethods.populate(patClinicalDtlVO, _fb);
			mpEssentials = VitalsRecordingDATA.getVitalsRecordingEssentials(patMonitringMstVO, patClinicalDtlVO, lstTemplates, userVO);
			List<PatientClinicalDetailVO> lstPatCliVOs = (List<PatientClinicalDetailVO>) mpEssentials.get(InpatientConfig.ESSENTIALS_MAP_MONITORING_VITALS_LAST_RECORD_VALUES);
			Map<String, PatientClinicalDetailVO> mpValues = new HashMap<String, PatientClinicalDetailVO>();

			for (PatientClinicalDetailVO vo : lstPatCliVOs)
				mpValues.put(vo.getParaId(),vo);
			mpEssentials.put(InpatientConfig.ESSENTIALS_MAP_MONITORING_VITALS_LAST_RECORD_VALUES_MAP, mpValues);
			
			// Setting Data for Vital Charts
			List<Entry> lstReportDates = (List<Entry>) mpEssentials.get(InpatientConfig.ESSENTIALS_MAP_MONITORING_VITALS_RECORD_DATE_LIST);
				// Map of RecordDate/Map of Temp Id/Map of Parameter Id/Para Value
			Map<String, Map<String, Map<String, String>>> mpChartClinicalData = (Map<String, Map<String, Map<String, String>>>) 
				mpEssentials.get(InpatientConfig.ESSENTIALS_MAP_MONITORING_VITALS_VALUES_MAP);
			

				// Setting Record Dates			
			LinkedHashMap<String, String> mapRecordDates = new LinkedHashMap<String, String>();
			for (Entry e : lstReportDates)
				mapRecordDates.put(e.getValue(), e.getLabel());
			
				// Getting Vital Chart Data in form Map of RecordDate/Map of Parameter Id/Para Value
			Map<String, Map<String, String>> mpVitalChartData = new HashMap<String, Map<String,String>>();
			String dataFoundFlag = OpdConfig.NO; 
			List<String> lstRemoveDates = new ArrayList<String>();
			for(String recordDate : mapRecordDates.keySet())
			{
				Map<String, Map<String, String >> mpTemp = mpChartClinicalData.get(recordDate);				
				if(mpTemp==null)	{	lstRemoveDates.add(recordDate);	continue;	}
				// Removing Not Useful Templates Data 
				List<String> lstRemoveTemplates = new ArrayList<String>();
				for(String key : mpTemp.keySet())
				{
					boolean flag = false;
					for(Entry entTemp : lstTemplates)
						if(entTemp.getValue().split("#")[0].equals(key))
						{
							flag=true;
							break;
						}
					if(!flag)	lstRemoveTemplates.add(key);
					else if(mpTemp.get(key).size()==0)	lstRemoveTemplates.add(key);
				}
				for(String tempId : lstRemoveTemplates)
					mpTemp.remove(tempId);
				
				// Conveting Templates in Map Para/Value
				Map<String, String> mpParaValuesInside = new HashMap<String, String>();
				for(String tempId : mpTemp.keySet())
					for(String paraId : mpTemp.get(tempId).keySet())
						mpParaValuesInside.put(paraId, mpTemp.get(tempId).get(paraId));				

				// Putting Data in Vital Chart Map
				if(mpParaValuesInside.keySet().size()!=0)
				{
					mpVitalChartData.put(recordDate, mpParaValuesInside);
					dataFoundFlag = OpdConfig.YES;
				}
				else
					lstRemoveDates.add(recordDate);
			}
			for(String str : lstRemoveDates)	mapRecordDates.remove(str);
			_fb.setVitalChartFlag(dataFoundFlag);
			
			// Setting Clinical Data
			mpEssentials.put(GenericTemplateConfig.GENERIC_CHART_VIEW_MODE, GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_PARA_WISE_LIST);
			mpEssentials.put(GenericTemplateConfig.GENERIC_CHART_PARAMETER_LIST, templateParaList);
			mpEssentials.put(GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP, mapRecordDates);
			mpEssentials.put(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpVitalChartData);
			
			WebUTIL.setMapInSession(mpEssentials,_request);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}

/*	public static void addRow(VitalsRecordingFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		PatientMonitoringMstVO[] patMonitoringVital = null;
		PatientMonitoringMstVO[] patientMonitoringVital = null;
		PatientMonitoringMstVO[] patientMonitor = null;
		List lstTemp = null;
		String paraName = "";

		try
		{
			lstTemp = (ArrayList) session.getAttribute(InpatientConfig.TEMPLATE_PARAMETER_LIST);
			for (int i = 0; i < lstTemp.size(); i++)
			{
				Entry ent = (Entry) lstTemp.get(i);
				if (ent.getValue().equalsIgnoreCase(_fb.getParaId()))
				{
					paraName = ent.getLabel();
					break;
				}
			}

			List newList = new ArrayList(lstTemp);
			newList = (List) WebUTIL.removeEntriesfromOptions(newList, _fb.getParaId());
			WebUTIL.setAttributeInSession(_request, InpatientConfig.TEMPLATE_PARAMETER_LIST, newList);

			patMonitoringVital = (PatientMonitoringMstVO[]) session.getAttribute(InpatientConfig.ARR_PAT_VITAL_MONITOR);

			if (patMonitoringVital == null)
			{
				patientMonitoringVital = new PatientMonitoringMstVO[1];

				patientMonitoringVital[0] = new PatientMonitoringMstVO();
				patientMonitoringVital[0].setParaId(_fb.getParaId());
				patientMonitoringVital[0].setParaName(paraName);
				patientMonitoringVital[0].setRemarks(_fb.getVitalRemarks());
				patientMonitoringVital[0].setDuration(_fb.getVitalDuration());

				WebUTIL.setAttributeInSession(_request, InpatientConfig.ARR_PAT_VITAL_MONITOR, patientMonitoringVital);
			}
			else
			{
				patientMonitor = new PatientMonitoringMstVO[patMonitoringVital.length + 1];
				int i = 0;
				for (; i < patMonitoringVital.length; i++)
				{
					patientMonitor[i] = patMonitoringVital[i];
				}
				patientMonitor[i] = new PatientMonitoringMstVO();

				patientMonitor[i].setParaId(_fb.getParaId());
				patientMonitor[i].setParaName(paraName);
				patientMonitor[i].setRemarks(_fb.getVitalRemarks());
				patientMonitor[i].setDuration(_fb.getVitalDuration());

				WebUTIL.setAttributeInSession(_request, InpatientConfig.ARR_PAT_VITAL_MONITOR, patientMonitor);
			}
			objStatus.add(Status.TRANSINPROCESS);

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

	public static void deleteRow(VitalsRecordingFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		PatientMonitoringMstVO[] patMonitoringVitalVO = null;
		PatientMonitoringMstVO[] patientMonitoringVitalVO = null;

		try
		{
			List lstTemp = (List) session.getAttribute(InpatientConfig.TEMPLATE_PARAMETER_LIST);
			List newList = new ArrayList(lstTemp);
			newList = (List) WebUTIL.addEntryToOptions(newList, _fb.getHiddenParaId(), _fb.getHiddenParaName());
			WebUTIL.setAttributeInSession(_request, InpatientConfig.TEMPLATE_PARAMETER_LIST, newList);

			patMonitoringVitalVO = (PatientMonitoringMstVO[]) session.getAttribute(InpatientConfig.ARR_PAT_VITAL_MONITOR);
			patientMonitoringVitalVO = new PatientMonitoringMstVO[patMonitoringVitalVO.length - 1];

			int j = 0;
			for (int i = 0; i < patMonitoringVitalVO.length; i++)
			{
				if (!_fb.getHiddenParaId().equals(patMonitoringVitalVO[i].getParaId()))
				{
					patientMonitoringVitalVO[j] = patMonitoringVitalVO[i];
					j++;
				}
			}
			WebUTIL.setAttributeInSession(_request, InpatientConfig.ARR_PAT_VITAL_MONITOR, patientMonitoringVitalVO);
			objStatus.add(Status.TRANSINPROCESS);
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

	public static void saveDetail(VitalsRecordingFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		PatientMonitoringMstVO[] patMonitoringVitalVO = null;
		PatientMonitoringMstVO[] patientMonitoringVitalVO = null;
		int count;
		int j = 0;

		try
		{
			DailyPatientVO[] al = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			DailyPatientVO voDP = new DailyPatientVO();
			for (int i = 0; i < al.length; i++)
			{
				voDP = (DailyPatientVO) al[i];
				if (voDP.getPatCrNo().equals(_fb.getPatCrNo())) break;
			}

			// /////Start Addition of New Vitals////////////

			patMonitoringVitalVO = (PatientMonitoringMstVO[]) session.getAttribute(InpatientConfig.ARR_PAT_VITAL_MONITOR);

			if (patMonitoringVitalVO == null) if (_fb.getParaId().equals("-1")) count = 0;
			else count = 1;
			else if (_fb.getParaId().equals("-1")) count = patMonitoringVitalVO.length;
			else count = patMonitoringVitalVO.length + 1;

			if (count > 0)
			{
				patientMonitoringVitalVO = new PatientMonitoringMstVO[count];
				if (!_fb.getParaId().equals("-1"))
				{
					patientMonitoringVitalVO[j] = new PatientMonitoringMstVO();
					HelperMethods.populate(patientMonitoringVitalVO[0], voDP);
					patientMonitoringVitalVO[j].setParaId(_fb.getParaId());
					patientMonitoringVitalVO[j].setDuration(_fb.getVitalDuration());
					patientMonitoringVitalVO[j].setRemarks(_fb.getVitalRemarks());
					patientMonitoringVitalVO[j].setInstructionMode(InpatientConfig.ENTER_BY_DOCTOR);
					patientMonitoringVitalVO[j].setVarifyFlag(InpatientConfig.VITALS_VARIFFIED_FLAG_BY_DOCTOR);
					j++;
				}

				if (patMonitoringVitalVO != null)
				{

					int len = patMonitoringVitalVO.length;
					for (int i = 0; i < len; i++)
					{
						patientMonitoringVitalVO[j] = new PatientMonitoringMstVO();

						HelperMethods.populate(patientMonitoringVitalVO[j], voDP);
						patientMonitoringVitalVO[j].setParaId(patMonitoringVitalVO[i].getParaId());
						patientMonitoringVitalVO[j].setDuration(patMonitoringVitalVO[i].getDuration());
						patientMonitoringVitalVO[j].setRemarks(patMonitoringVitalVO[i].getRemarks());
						patientMonitoringVitalVO[j].setInstructionMode(InpatientConfig.ENTER_BY_DOCTOR);
						patientMonitoringVitalVO[j].setVarifyFlag(InpatientConfig.VITALS_VARIFFIED_FLAG_BY_DOCTOR);
						j++;
					}
				}
			}

			// /////End Addition of New Vitals////////////

			// /////Start Modification of Vitals////////////

			String[] modParaId = _fb.getModChk();
			String[] modDuration = _fb.getModDuration();
			String[] modRemarks = _fb.getModRemarks();
			String[] modSerialNo = _fb.getSerialNo();

			PatientMonitoringMstVO[] modPatMonitringMstVO = null;
			if (modDuration != null)
			{
				modPatMonitringMstVO = new PatientMonitoringMstVO[modDuration.length];
			}
			if (modDuration != null)
			{
				for (int i = 0; i < modDuration.length; i++)
				{
					modPatMonitringMstVO[i] = new PatientMonitoringMstVO();

					HelperMethods.populate(modPatMonitringMstVO[i], voDP);

					modPatMonitringMstVO[i].setDuration(modDuration[i]);
					modPatMonitringMstVO[i].setParaId(modParaId[i]);
					modPatMonitringMstVO[i].setRemarks(modRemarks[i]);
					modPatMonitringMstVO[i].setSerialNo(modSerialNo[i]);
				}
			}

			// /////End Modification of Vitals////////////

			MonitorVitalsDATA.saveVitalsDetail(patientMonitoringVitalVO, modPatMonitringMstVO, getUserVO(_request));
			objStatus.add(Status.DONE, "", "Record added Successfully");

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

	public static void revokeVitals(VitalsRecordingFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		PatientMonitoringMstVO patMonitoringVitalVO = new PatientMonitoringMstVO();

		try
		{

			DailyPatientVO[] arrayDailyPatVO = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			DailyPatientVO selectedPatientVO = null;
			for (int i = 0; i < arrayDailyPatVO.length; i++)
				if (_fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo())) selectedPatientVO = arrayDailyPatVO[i];

			String revokeParaId = _fb.getRevokeParaId();
			HelperMethods.populate(patMonitoringVitalVO, selectedPatientVO);
			MonitorVitalsDATA.revokeVitals(revokeParaId, patMonitoringVitalVO, getUserVO(_request));

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
	}*/
}
