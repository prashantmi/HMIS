package inpatient.transaction.controller.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import hisglobal.utility.generictemplate.GenericTemplateConfig;

import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.MonitoringModeMstVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientMonitoringMstVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;

import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.MonitorVitalsDATA;
import inpatient.transaction.controller.fb.MonitorVitalsFB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;

public class MonitorVitalsUTL extends ControllerUTIL
{

	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static boolean getEssentails(MonitorVitalsFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_request);
		PatientDetailVO[] dailyPatientVOs = null;

		try
		{	
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			InpatientDetailUTL.getInpatientDetailByCrNo(_fb, _request);
			PatientDetailVO patientVO=(PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			String patDeathStatus=(String)patientVO.getPatStatusCode();
			_fb.setHiddenPatDeadStatus(patDeathStatus);
			
			
			// Setting Desk Essentials
			// User Seat, Unit, Ward
			_fb.setUserSeatId(userVO.getUserSeatId());
			//_fb.setDeptUnitCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE));
			_fb.setWardCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_WARD_CODE));
			// Episode, Visit, Admission No
			/*PatientDetailVO[] al = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO voDP = new PatientDetailVO();
			for (int i = 0; i < al.length; i++)
			{
				voDP = (PatientDetailVO) al[i];
				if (voDP.getPatCrNo().equals(_fb.getPatCrNo())) break;
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
			_fb.setDeptUnitCode(voDP.getDepartmentUnitCode());
			if(voDP.getPatAdmNo()!=null)
				_fb.setAdmissionNo(voDP.getPatAdmNo());
			else
				_fb.setAdmissionNo("");
			// Desk Type, Desk Id, Desk Menu Name
			String deskType=(String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
			_fb.setDeskType(deskType);
			_fb.setDeskId((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ID));
			
			
			// Vital Monitoring Other Essentials 
			PatientMonitoringMstVO patMonitringMstVO=new PatientMonitoringMstVO();						
			HelperMethods.populate(patMonitringMstVO, _fb);
			PatientClinicalDetailVO patClinicalDtlVO=new PatientClinicalDetailVO();						
			HelperMethods.populate(patClinicalDtlVO, _fb);
			UserDeskMenuTemplateMasterVO userDeskTempVO=new UserDeskMenuTemplateMasterVO();
			HelperMethods.populate(userDeskTempVO, _fb);			
			Map<String, Object> mpEssentials = MonitorVitalsDATA.getEssentailsForMonitoringVital(deskType, userDeskTempVO, patClinicalDtlVO, patMonitringMstVO, userVO);

			// Getting Template Parameter List
			List<Entry> lstTemplates = (List<Entry>) mpEssentials.get(GenericTemplateConfig.TEMPLATE_TILE_ALL_TEMPLATE_LIST+_fb.getDeskMenuId());
			List<Entry> templateParaList = MonitorVitalsDATA.getEssentails(lstTemplates, userVO);
			WebUTIL.setAttributeInSession(_request, InpatientConfig.TEMPLATE_PARAMETER_LIST, templateParaList);			

				// Setting Parameter List for Vital Chart	
			List<Entry> lstParaList = new ArrayList<Entry>();
			for (Entry e : templateParaList)
				lstParaList.add(e);
			
			
				// Removing Monitoring Paramters
			PatientMonitoringMstVO[] patMonitringMstVOs = (PatientMonitoringMstVO[]) mpEssentials.get(InpatientConfig.SELECTED_PARAMETER_LIST);			
			for(PatientMonitoringMstVO vo :patMonitringMstVOs)
			{
				if(vo.getRemarks()==null)	vo.setRemarks("");
				Entry objEntry = null;
				for(Entry obj : templateParaList)
					if(obj.getValue().equals(vo.getParaId()))
					{
						objEntry = obj;
						break;
					}					 
				templateParaList.remove(objEntry);
			}
		
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
			mpEssentials.put(GenericTemplateConfig.GENERIC_CHART_VIEW_MODE, GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_PARA_WISE_LIST);//GENERIC_CHART_VIEW_MODE_PARA_WISE_CHART_TABLE);
			mpEssentials.put(GenericTemplateConfig.GENERIC_CHART_PARAMETER_LIST, lstParaList);
			mpEssentials.put(GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP, mapRecordDates);
			mpEssentials.put(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpVitalChartData);
			
			WebUTIL.setMapInSession(mpEssentials,_request);
			WebUTIL.setAttributeInSession(_request, InpatientConfig.SELECTED_PARAMETER_LIST, patMonitringMstVOs);
						
			if(templateParaList==null || templateParaList.size()==0)
				objStatus.add(Status.TRANSINPROCESS,"","No Parameter Available for Monitoring");
			else
				objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "No Mapped Vitals Found to monitor");
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
	
	public static void addRow(MonitorVitalsFB _fb, HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session=_request.getSession();
		PatientMonitoringMstVO[] patMonitoringVital=null;
		PatientMonitoringMstVO[] patientMonitoringVital=null;
		PatientMonitoringMstVO[] patientMonitor=null;
		List lstTemp=null;
		String modeName="";
		String paraName="";
		
		try
		{
			lstTemp=(ArrayList)session.getAttribute(InpatientConfig.TEMPLATE_PARAMETER_LIST);
			for(int i=0;i<lstTemp.size();i++)
			{
				Entry ent=(Entry)lstTemp.get(i);
			    if( ent.getValue().equalsIgnoreCase(_fb.getParaId()))
			    {
			    	paraName=ent.getLabel();
			    	break;
			    }
			}
			MonitoringModeMstVO[] monitorModeMstVO=(MonitoringModeMstVO[])session.getAttribute(InpatientConfig.ARR_MONITOR_MODE_VO);
			for(int i=0;i<monitorModeMstVO.length;i++)
			{
				if(_fb.getMonitorMode().equals(monitorModeMstVO[i].getModeId()))
				{
					modeName=monitorModeMstVO[i].getModeName();
					break;
				}
			}
			
			List newList = new ArrayList(lstTemp);
			newList=(List) WebUTIL.removeEntriesfromOptions(newList,_fb.getParaId()); 
			WebUTIL.setAttributeInSession(_request,InpatientConfig.TEMPLATE_PARAMETER_LIST,newList);
			
			patMonitoringVital=(PatientMonitoringMstVO[])session.getAttribute(InpatientConfig.ARR_PAT_VITAL_MONITOR);
			
			if(patMonitoringVital==null)
			{
				patientMonitoringVital=new PatientMonitoringMstVO[1];
				
				patientMonitoringVital[0]=new PatientMonitoringMstVO();
				patientMonitoringVital[0].setParaId(_fb.getParaId());
				patientMonitoringVital[0].setParaName(paraName);
				patientMonitoringVital[0].setRemarks(_fb.getVitalRemarks());
			//	patientMonitoringVital[0].setDuration(_fb.getVitalDuration());
				patientMonitoringVital[0].setMonitorMode(_fb.getMonitorMode());
				patientMonitoringVital[0].setMonitorModeName(modeName);
				
				
				WebUTIL.setAttributeInSession(_request,InpatientConfig.ARR_PAT_VITAL_MONITOR , patientMonitoringVital);
			}
			else
			{
				patientMonitor=new PatientMonitoringMstVO[patMonitoringVital.length+1];
				int i=0;
				for(;i<patMonitoringVital.length;i++)
				{
					patientMonitor[i]=patMonitoringVital[i];
				}
				patientMonitor[i]=new PatientMonitoringMstVO();
				
				patientMonitor[i].setParaId(_fb.getParaId());
				patientMonitor[i].setParaName(paraName);
				patientMonitor[i].setRemarks(_fb.getVitalRemarks());
				patientMonitor[i].setDuration(_fb.getVitalDuration());
				patientMonitor[i].setMonitorMode(_fb.getMonitorMode());
				patientMonitor[i].setMonitorModeName(modeName);
				
				WebUTIL.setAttributeInSession(_request,InpatientConfig.ARR_PAT_VITAL_MONITOR , patientMonitor);
			}
			objStatus.add(Status.TRANSINPROCESS);			
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	public static void deleteRow(MonitorVitalsFB _fb, HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session=_request.getSession();
		PatientMonitoringMstVO[] patMonitoringVitalVO=null;
		PatientMonitoringMstVO[] patientMonitoringVitalVO=null;
		
		try
		{
			List lstTemp=(List)session.getAttribute(InpatientConfig.TEMPLATE_PARAMETER_LIST);
			List newList = new ArrayList(lstTemp);
			newList=(List) WebUTIL.addEntryToOptions(newList,_fb.getHiddenParaId(),_fb.getHiddenParaName()); 
			WebUTIL.setAttributeInSession(_request,InpatientConfig.TEMPLATE_PARAMETER_LIST,newList);
			
			patMonitoringVitalVO=(PatientMonitoringMstVO[])session.getAttribute(InpatientConfig.ARR_PAT_VITAL_MONITOR);
			patientMonitoringVitalVO=new PatientMonitoringMstVO[patMonitoringVitalVO.length-1];
			
			int j=0;
			for(int i=0;i<patMonitoringVitalVO.length;i++)
			{
				if(!_fb.getHiddenParaId().equals(patMonitoringVitalVO[i].getParaId()))
				{
					patientMonitoringVitalVO[j]=patMonitoringVitalVO[i];
					j++;
				}
			}
			WebUTIL.setAttributeInSession(_request,InpatientConfig.ARR_PAT_VITAL_MONITOR , patientMonitoringVitalVO);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}	
	public static void saveDetail(MonitorVitalsFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();
		HttpSession session=_request.getSession();
		PatientMonitoringMstVO[] patMonitoringVitalVO=null;
		PatientMonitoringMstVO[] patientMonitoringVitalVO=null;
		PatientDetailVO[] dailyPatientVOs = null;

		int count;
		int j=0;
		
		try
		{
			/*PatientDetailVO[] al = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO voDP = new PatientDetailVO();
			for (int i = 0; i < al.length; i++)
			{
				voDP = (PatientDetailVO) al[i];
				if (voDP.getPatCrNo().equals(_fb.getPatCrNo())) break;
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
			
///////Start Addition of New Vitals////////////
			
			patMonitoringVitalVO=(PatientMonitoringMstVO[])session.getAttribute(InpatientConfig.ARR_PAT_VITAL_MONITOR);
			MonitoringModeMstVO[] monitorModeMstVO=(MonitoringModeMstVO[])session.getAttribute(InpatientConfig.ARR_MONITOR_MODE_VO);
			if(patMonitoringVitalVO==null)
			{
				if(_fb.getParaId()==null)
				{
					count=0;
				}
				else
				{
					if(_fb.getParaId().equals("-1"))
					{
						count=0;
					}
					else
					{
						count=1;
					}
				}
				
				
					
			}
			else
			{
				if(_fb.getParaId()==null)
				{
					count=patMonitoringVitalVO.length;
				}
				else
				{
					if(_fb.getParaId().equals("-1"))
					{
						count=patMonitoringVitalVO.length;
					}
					else
					{
						count=patMonitoringVitalVO.length+1;
					}
				}
				
					
			}
				
			
			if(count>0)
			{
				patientMonitoringVitalVO=new PatientMonitoringMstVO[count];
				if((_fb.getParaId()!=null) && !(_fb.getParaId().equals("-1")))
				{
					patientMonitoringVitalVO[j]=new PatientMonitoringMstVO();
					HelperMethods.populate(patientMonitoringVitalVO[0], voDP);
					patientMonitoringVitalVO[j].setAdmissionNo(voDP.getPatAdmNo());
					patientMonitoringVitalVO[j].setParaId(_fb.getParaId());
				//	patientMonitoringVitalVO[j].setDuration(_fb.getVitalDuration());
					patientMonitoringVitalVO[j].setRemarks(_fb.getVitalRemarks());
					patientMonitoringVitalVO[j].setInstructionMode(InpatientConfig.ENTER_BY_DOCTOR);
					patientMonitoringVitalVO[j].setVarifyFlag(InpatientConfig.VITALS_VARIFFIED_FLAG_BY_DOCTOR);
					patientMonitoringVitalVO[j].setMonitorMode(_fb.getMonitorMode());
					for(int i=0;i<monitorModeMstVO.length;i++)
					{
						if(_fb.getMonitorMode().equals(monitorModeMstVO[i].getModeId()))
						{
							patientMonitoringVitalVO[j].setDuration(monitorModeMstVO[i].getIntervalInMin());
							patientMonitoringVitalVO[j].setDayFrequency(monitorModeMstVO[i].getDayFrequency());
						}
					}
					j++;
				}
				
				if(patMonitoringVitalVO!=null)
				{
					
					int len=patMonitoringVitalVO.length;
					for(int i=0;i<len;i++)
					{
						patientMonitoringVitalVO[j]=new PatientMonitoringMstVO();
						
						HelperMethods.populate(patientMonitoringVitalVO[j], voDP);
						patientMonitoringVitalVO[j].setAdmissionNo(voDP.getPatAdmNo());
						patientMonitoringVitalVO[j].setParaId(patMonitoringVitalVO[i].getParaId());
					//	patientMonitoringVitalVO[j].setDuration(patMonitoringVitalVO[i].getDuration());
						patientMonitoringVitalVO[j].setRemarks(patMonitoringVitalVO[i].getRemarks());
						patientMonitoringVitalVO[j].setInstructionMode(InpatientConfig.ENTER_BY_DOCTOR);
						patientMonitoringVitalVO[j].setVarifyFlag(InpatientConfig.VITALS_VARIFFIED_FLAG_BY_DOCTOR);
						patientMonitoringVitalVO[j].setMonitorMode(patMonitoringVitalVO[i].getMonitorMode());
						for(int k=0;k<monitorModeMstVO.length;k++)
						{
							if(patMonitoringVitalVO[i].getMonitorMode().equals(monitorModeMstVO[k].getModeId()))
							{
								patientMonitoringVitalVO[j].setDuration(monitorModeMstVO[k].getIntervalInMin());
								patientMonitoringVitalVO[j].setDayFrequency(monitorModeMstVO[k].getDayFrequency());
							}
						}
						j++;
					}
				}
			}	
			
///////End Addition of New Vitals////////////
			
///////Start Modification of  Vitals////////////
			
			String[] modParaId=_fb.getModChk();
			String[] modMonitorMode=_fb.getModMonitorMode();
			String[] modRemarks=_fb.getModRemarks();
			String[] modSerialNo=_fb.getArrSerialNo();
			
			PatientMonitoringMstVO[] modPatMonitringMstVO=null;
			if(modMonitorMode!=null)
			{
				modPatMonitringMstVO=new PatientMonitoringMstVO[modMonitorMode.length];
			}
			if(modMonitorMode!=null)
			{
				for(int i=0;i<modMonitorMode.length;i++)
				{
					modPatMonitringMstVO[i]=new PatientMonitoringMstVO();
					
					HelperMethods.populate(modPatMonitringMstVO[i], voDP);
					modPatMonitringMstVO[i].setAdmissionNo(voDP.getPatAdmNo());
					//modPatMonitringMstVO[i].setDuration(modDuration[i]);
					modPatMonitringMstVO[i].setParaId(modParaId[i]);
					modPatMonitringMstVO[i].setRemarks(modRemarks[i]);
					modPatMonitringMstVO[i].setSerialNo(modSerialNo[i]);
					modPatMonitringMstVO[i].setMonitorMode(modMonitorMode[i]);
					for(int k=0;k<monitorModeMstVO.length;k++)
					{
						if(modMonitorMode[i].equals(monitorModeMstVO[k].getModeId()))
						{
							modPatMonitringMstVO[i].setDuration(monitorModeMstVO[k].getIntervalInMin());
							modPatMonitringMstVO[i].setDayFrequency(monitorModeMstVO[k].getDayFrequency());
						}
					}
					
				}
			}
			
///////End Modification of  Vitals////////////
			
			
			MonitorVitalsDATA.saveVitalsDetail(patientMonitoringVitalVO,modPatMonitringMstVO,getUserVO(_request));
			objStatus.add(Status.TRANSINPROCESS,"","Record added Successfully");
			
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	public static void revokeVitals(MonitorVitalsFB _fb,HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		PatientMonitoringMstVO patMonitoringVitalVO=new PatientMonitoringMstVO();
		PatientDetailVO[] dailyPatientVOs = null;

		
		try
		{
			
			/*DailyPatientVO[] arrayDailyPatVO = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			DailyPatientVO selectedPatientVO = null;
			for (int i = 0; i < arrayDailyPatVO.length; i++)
				if (_fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
					selectedPatientVO = arrayDailyPatVO[i];*/
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
			_fb.setPatCrNo(selectedPatientVO.getPatCrNo());
			
			String revokeParaId=_fb.getRevokeParaId();
			HelperMethods.populate(patMonitoringVitalVO, selectedPatientVO);
			MonitorVitalsDATA.revokeVitals(revokeParaId,patMonitoringVitalVO,getUserVO(_request));
			
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
