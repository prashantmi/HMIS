/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.complaints.presentation;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisEpisodeOpenInEmergencyException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRenewalRequiredException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.utility.generictemplate.InformationControlBean;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.TemplateParameterMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.utl.InpatientDetailUTL;
import inpatient.transaction.controller.utl.PatNursingDeskDataFlagsMenuWiseUTL;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ehr.EHRConfig;
import ehr.complaints.vo.EHRSection_ComplaintsVO;
import ehr.examination.presentation.EHRSection_ExaminationDATA;
import ehr.examination.vo.EHRSection_ExaminationVO;
import ehr.vo.EHRVO;
import ehr.vo.EHRVOUtility;
import emr.vo.PatientClinicalDocDetailVO;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import opd.OpdConfig;




import registration.controller.fb.CRNoFB;

public class EHRSection_ComplaintsUTL extends ControllerUTIL
{

	
	public static void setTemplateTileEssentials(EHRSection_ComplaintsFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);

		Map putmap = new HashMap();
		List<Entry> lstTemps = null;
		List<Entry> lstActiveTemps = null;
		List<Entry> lstInactiveTemps = null;

		Map<String, Map<String, String>> mpTempParaValues = null;

		try
		{
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);
			_fb.setRecordDate(WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), null));

			// Getting Patient Detail
			CRNoFB crnoFB = new CRNoFB();
			crnoFB.setPatCrNo(_fb.getPatCrNo());
			InpatientDetailUTL.getInpatientDetailByCrNo(crnoFB, _rq);
			PatientDetailVO voDP = null;
			PatientDetailVO patDtlVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);

			// Setting Desk Essentials
			// User Seat, Ward -- Not Unit due to IPD
					//_fb.setUserSeatId(userVO.getUserSeatId());
			_fb.setUserSeatId(userVO.getSeatId());	// User Id in Place of Seat Id
			//_fb.setDeptUnitCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE)); // commented on 2012.03.29
			_fb.setWardCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_WARD_CODE));
			// Episode, Visit, Admission No, Unit
			PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			
			/*if(ptaientDetailVO == null || !ptaientDetailVO.getPatCrNo().equals(_fb.getPatCrNo()))
			{
				PatientDetailVO[] al = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (PatientDetailVO vo : al)
					if (vo.getPatCrNo().equals(_fb.getPatCrNo()))
					{
						voDP = vo;
						break;					
					}
			}*/
			
			if(ptaientDetailVO!=null)
							voDP =ptaientDetailVO;
			else
				voDP=patDtlVO;
			
			_fb.setEpisodeCode(voDP.getEpisodeCode());
			_fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
			_fb.setDeptUnitCode(voDP.getDepartmentUnitCode());// Added here 2012.03.29
			if(voDP.getPatAdmNo()!=null)
				_fb.setAdmissionNo(voDP.getPatAdmNo());
			else
				_fb.setAdmissionNo("");
			// Desk Type, Desk Id, Desk Menu Name
			
			if(session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE)!=null)
			{
			_fb.setDeskType((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE));
			_fb.setDeskId((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ID));
			DeskDetailVO deskDtlVO = null;
			
			List<DeskDetailVO> lstDeskMenus = (List<DeskDetailVO>) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_LEFT_MENU_DTL);
			for (DeskDetailVO vo : lstDeskMenus)
				if (vo.getDeskMenuId().equals(_fb.getDeskMenuId()))
				{
					deskDtlVO = vo;
					break;
				}
			if (deskDtlVO == null)
			{
				lstDeskMenus = (List<DeskDetailVO>) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_RIGHT_MENU_DTL);
				for (DeskDetailVO vo : lstDeskMenus)
					if (vo.getDeskMenuId().equals(_fb.getDeskMenuId()))
					{
						deskDtlVO = vo;
						break;
					}
			}
			if (deskDtlVO == null)
			{
				lstDeskMenus = (List<DeskDetailVO>) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TOP_MENU_DTL);
				for (DeskDetailVO vo : lstDeskMenus)
					if (vo.getDeskMenuId().equals(_fb.getDeskMenuId()))
					{
						deskDtlVO = vo;
						break;
					}
			}
			if (deskDtlVO == null)
			{
				lstDeskMenus = (List<DeskDetailVO>) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_BOTTOM_MENU_DTL);
				for (DeskDetailVO vo : lstDeskMenus)
					if (vo.getDeskMenuId().equals(_fb.getDeskMenuId()))
					{
						deskDtlVO = vo;
						break;
					}
			}
			}
			//_fb.setDeskMenuName(deskDtlVO.getUserDeskMenuName());
			else
			_fb.setDeskType(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK);
			_fb.setDeskMenuId("1");
			_fb.setDeskMenuName("COMPLAINTS");
			
			// Setting Template Essentials
				// Patient Information in Session for Template
				InformationControlBean infoBean = new InformationControlBean();
				infoBean.putPatientDetailValues(patDtlVO);
			WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_TEMPLATE_INFORMATION_BEAN, infoBean);
			WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_GENDER_TYPE, patDtlVO.getPatGenderType());
				// Age of Patient in Integer
				Date sysdate = (Date) session.getAttribute(Config.SYSDATEOBJECT);
				Date dob = WebUTIL.getDateFromString(patDtlVO.getPatDOB(), "dd-MMM-yyyy");
				long diff = sysdate.getTime() - dob.getTime();
				int age = (int)(diff / ((long)1000 * 60 * 60 * 24 * 365));
				if(age<=0)	age=1;
			WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_AGE, Integer.toString(age));
			
			// Template List (Default, NonDefault Once)
			if ((session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_DEFAULT_TEMPLATE_LIST+_fb.getDeskMenuId()) != null))
			{
				lstTemps = (List<Entry>) session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_ALL_TEMPLATE_LIST+_fb.getDeskMenuId());
				lstActiveTemps = (List<Entry>) session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_DEFAULT_TEMPLATE_LIST+_fb.getDeskMenuId());
				lstInactiveTemps = (List<Entry>) session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_UNDEFAULT_TEMPLATE_LIST+_fb.getDeskMenuId());
			}
			else
			{
				UserDeskMenuTemplateMasterVO vo = new UserDeskMenuTemplateMasterVO();
				HelperMethods.populate(vo, _fb);
				lstTemps = EHRSection_ComplaintsDATA.getDeskMenuTemplateList(_fb.getDeskType(), vo, userVO);
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
			}
			putmap.put(GenericTemplateConfig.TEMPLATE_TILE_ALL_TEMPLATE_LIST+_fb.getDeskMenuId(), lstTemps);
			putmap.put(GenericTemplateConfig.TEMPLATE_TILE_DEFAULT_TEMPLATE_LIST+_fb.getDeskMenuId(), lstActiveTemps);
						
			putmap.put(GenericTemplateConfig.TEMPLATE_TILE_UNDEFAULT_TEMPLATE_LIST+_fb.getDeskMenuId(), lstInactiveTemps);

			// Patient Centric Data and Episode Visit Data
			mpTempParaValues = new HashMap<String, Map<String, String>>();
			EHRSection_ComplaintsVO patCliniDtlVO = new EHRSection_ComplaintsVO();
			HelperMethods.populate(patCliniDtlVO, _fb);

			if(lstTemps.size()>0)
			{
				String[] templateIds = new String[lstTemps.size()];
				for(int i=0;i<lstTemps.size();i++)
					templateIds[i] = lstTemps.get(i).getValue();
				patCliniDtlVO.setTemplateIds(templateIds);
				mpTempParaValues = EHRSection_ComplaintsDATA.getPatientFinalClinicalData(_fb.getDeskType(), patCliniDtlVO, userVO);
				if(!mpTempParaValues.isEmpty())
					_fb.setIsSetCOMPLAINTS("1");
			}
			// Set Data Entered Inactive Templates into Active
			List<Entry> temp = new ArrayList<Entry>();
			for (Entry e : lstInactiveTemps)
			{
				for (String key : mpTempParaValues.keySet())
				{
					if (key.equalsIgnoreCase(e.getValue())) temp.add(e);
				}
			}
			for (Entry e : temp)
			{
				lstActiveTemps.add(e);
				lstInactiveTemps.remove(e);
			}

			putmap.put(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP, mpTempParaValues);
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
	
	public static void setTempReportEssentials(EHRSection_ComplaintsFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		try
		{
			_fb.setReportMode(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE);
			setTemplateWiseReportEssentials(_fb,_rq);
			
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
	
	public static void setTemplateWiseReportEssentials(EHRSection_ComplaintsFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		List<Entry> lstAllTempsIds = null;
		Map<String, TemplateMasterVO> mapTemplateDtl = null;
		HttpSession session = _rq.getSession();
		try
		{
			_fb.setDeskMenuId("1");
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);
			lstAllTempsIds = (List<Entry>) session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_ALL_TEMPLATE_LIST+_fb.getDeskMenuId());
			mapTemplateDtl = EHRSection_ComplaintsDATA.getAllTemplateDetails(lstAllTempsIds, userVO);

			WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_DTL_MAP, mapTemplateDtl);
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
	
	
	
	
	public static void setViewReportEssentials(EHRSection_ComplaintsFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		// Report Dates for Charting in OPD Visits & in IPD Round Dates
		List<Entry> lstReportDates = new ArrayList<Entry>();
		List<Entry> lstSelectedTemps = new ArrayList<Entry>();
		Map<String, TemplateMasterVO> mapTemplateDtl = null;
		LinkedHashMap<String, LinkedHashMap<String, TemplateParameterMasterVO>> mapAllValuedTempParas = null;
		HttpSession session = _rq.getSession();
		try
		{
			_fb.setDeskMenuId("1");
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);
			mapTemplateDtl = (Map<String, TemplateMasterVO>) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_DTL_MAP);
			mapAllValuedTempParas = (LinkedHashMap<String, LinkedHashMap<String, TemplateParameterMasterVO>>) session
					.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_VALUED_TEMP_PARA_MAP);
			if (_fb.getReportMode().equals(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE))
			{
				/*// Setting Selected Template
				for (int i = 0; i < _fb.getSelectedTemplates().length; i++)
				{
					TemplateMasterVO voTemp = mapTemplateDtl.get(_fb.getSelectedTemplates()[i]);
					Entry e = new Entry();
					e.setLabel(voTemp.getTemplateName());
					e.setValue(voTemp.getTemplateId());
					lstSelectedTemps.add(e);
				}*/
				lstSelectedTemps = (List<Entry>) session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_ALL_TEMPLATE_LIST+_fb.getDeskMenuId());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS_COMPLAINTS, lstSelectedTemps);
				
			}
			
			else if (_fb.getReportMode().equals(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_PARAMETER_WISE))
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
				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_LIST_MAP, mapChartTemps);
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
				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP, mapChartTempParas);
			}

			// Getting Visit Dates
			EHRSection_ComplaintsVO _patCliDtlVO = new EHRSection_ComplaintsVO();
			HelperMethods.populate(_patCliDtlVO, _fb);
			//_patCliDtlVO.setDeskMenuId(_fb.getDeskMenuId()); 
			_patCliDtlVO.setDeskMenuId("1");   //added by manisha date: 24.8.2017
			lstReportDates = EHRSection_ComplaintsDATA.getReportDateList(_fb.getDeskType(), _patCliDtlVO, userVO);
			if(lstReportDates==null)	lstReportDates = new ArrayList<Entry>();
			// Setting Record Dates
			LinkedHashMap<String, String> mapRecordDates = new LinkedHashMap<String, String>();
			for (Entry e : lstReportDates)
				mapRecordDates.put(e.getValue(), e.getLabel());

			// Map of RecordDate/Map of Temp Id/Map of Parameter Id/Para Value
			EHRSection_ComplaintsVO patCliniDtlVO = new EHRSection_ComplaintsVO();
			HelperMethods.populate(patCliniDtlVO, _fb);
			
			Map<String, Map<String, Map<String, GenericTemplateUtility.TempParameter>>> mpChartClinicalDataWithParaDtl = null;
			mpChartClinicalDataWithParaDtl = EHRSection_ComplaintsDATA.getPatientChartClinicalDataTempWiseWithParaDtl(_fb.getDeskType(), patCliniDtlVO, lstReportDates,  lstSelectedTemps, userVO);

			Map<String, Map<String, Map<String, String>>> mpChartClinicalData = new HashMap<String, Map<String,Map<String,String>>>();
			for(String recordDate : mpChartClinicalDataWithParaDtl.keySet())
			{
				Map<String, Map<String, String >> mpTemp = mpChartClinicalData.get(recordDate);
				if(mpTemp==null)	mpTemp = new HashMap<String, Map<String,String>>();
				for(String tempId : mpChartClinicalDataWithParaDtl.get(recordDate).keySet())
				{
					Map<String, String> mpPara = mpTemp.get(tempId);
					if(mpPara==null)	mpPara = new HashMap<String,String>();
					for(String paraId : mpChartClinicalDataWithParaDtl.get(recordDate).get(tempId).keySet())
						mpPara.put(paraId, mpChartClinicalDataWithParaDtl.get(recordDate).get(tempId).get(paraId).getParaValue());
					mpTemp.put(tempId, mpPara);
				}
				mpChartClinicalData.put(recordDate, mpTemp);
			}
			
			// Remove Report Dates that don't have Data
			boolean dataFoundFlag = false; 
			List<String> lstRemove = new ArrayList<String>();
			for(String recordDate : mapRecordDates.keySet())
			{
				Map<String, Map<String, String >> mpTemp = mpChartClinicalData.get(recordDate);
				if(mpTemp==null)	{	lstRemove.add(recordDate);	continue;	}
				// Removing Not Useful Templates Data 
				List<String> lstRemoveTemplates = new ArrayList<String>();
				for(String key : mpTemp.keySet())
				{
					boolean flag = false;
					for(Entry entTemp : lstSelectedTemps)
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

				// Checking For Record Date Data
				if(mpTemp.keySet().size()==0)
				{						
					mpChartClinicalData.remove(recordDate);
					lstRemove.add(recordDate);
				}
				else
					dataFoundFlag = true;
			}
			List<Entry> lstRemoveEntry = new ArrayList<Entry>();
			for(String str : lstRemove)
			{
				mapRecordDates.remove(str);
				for(Entry entObj: lstReportDates)
					if( entObj.getValue().equals(str))	{	lstRemoveEntry.add(entObj);	break;	}					
			}
		//	for(Entry entObj: lstRemoveEntry)	lstReportDates.remove(entObj);
			
			// Removing Non-data Selected Parameters in Report Parameter Wise 
			if (_fb.getReportMode().equals(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_PARAMETER_WISE))
			{
				dataFoundFlag = false;
				LinkedHashMap<String, List<Entry>> mapChartTempParas = (LinkedHashMap<String, List<Entry>>)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
				
				for(String tempId : mapChartTempParas.keySet())
				{
					List<Entry> removePara = new ArrayList<Entry>();
					for(Entry entObj : mapChartTempParas.get(tempId))
					{
						boolean flag=false;						
						//Map of RecordDate/Map of Temp Id/Map of Parameter Id/Para Value
						for(String recordDate : mpChartClinicalData.keySet())
							for(String templateId : mpChartClinicalData.get(recordDate).keySet())
								if(templateId.equals(tempId))
									for(String paraId : mpChartClinicalData.get(recordDate).get(templateId).keySet())
										if(paraId.equals(entObj.getValue()))
										{
											dataFoundFlag = true;
											flag=true;
										}
						if(!flag) removePara.add(entObj);
					}
					for(Entry entObj : removePara)	mapChartTempParas.get(tempId).remove(entObj);
				}
			}

			// Setting Clinical Data
			if (_fb.getReportMode().equals(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE))
			{
				WebUTIL.setAttributeInSession(_rq, OpdConfig.GENERIC_TEMP_TILE_ALL_VISIT_DATES_COMPLAINTS, lstReportDates);
			}
			else if (_fb.getReportMode().equals(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_PARAMETER_WISE))
			{
				WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP, mapRecordDates);
			}
			WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_DETAIL_MAP_COMPLAINTS, mpChartClinicalDataWithParaDtl);
			WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP_COMPLAINTS, mpChartClinicalData);
			WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_CHART_VIEW_MODE, GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);			

			if(!dataFoundFlag)// && _fb.getReportMode().equals(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_PARAMETER_WISE))
			{
				throw new HisRecordNotFoundException("No Data Found");
			}

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
	
	// Saving Patient Clinical Data
			public static boolean savePatientClinicalData(EHRSection_ComplaintsFB _fb, HttpServletRequest _request)
			{
				boolean flag = true;
				Status objStatus = new Status();
				List<GenericTemplateUtility.TempParameter> lstTempData = null;
				HttpSession session = _request.getSession();
				try
				{
					UserVO userVO = getUserVO(_request);
					setSysdate(_request);
					_fb.setRecordDate(WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), null));

					// Getting Entered Parameter Values
					lstTempData = GenericTemplateUtility.getTempParameterValueList(_request);

					// Deleting Existing Template Data & Saving New One
					List<PatientClinicalDetailVO> lstPatCliniData = new ArrayList<PatientClinicalDetailVO>();
					for (GenericTemplateUtility.TempParameter obj : lstTempData)
					{
						PatientClinicalDetailVO vo = new PatientClinicalDetailVO();
						HelperMethods.populate(vo, _fb);
						vo.setDeskMenuId("1");
						vo.setTemplateId(obj.getTemplateId());
						vo.setParaId(obj.getParaId());
						
						
						String arr[]=obj.getParaValue().split("#");
						if(arr.length>1)
						{
							if(arr[0].matches("-?\\d+(\\.\\d+)?") && ! arr[2].isEmpty() && ! arr[1].isEmpty())   /// check is numeric && is not empty
							{
								vo.setParaValuePreffereTerm(arr[1]);
								vo.setParaValueConcpetId(arr[0]);
								vo.setParaValue(arr[2]);
							}
							else 
							{
								vo.setParaValuePreffereTerm("");
								vo.setParaValueConcpetId("");
								vo.setParaValue(arr[2]);
							}
								
						}
						else
						{
							vo.setParaValuePreffereTerm("");
							vo.setParaValueConcpetId("");
							vo.setParaValue(obj.getParaValue());
						}
						lstPatCliniData.add(vo);
					}
					
					EHRSection_ComplaintsDATA.savePatientClinicalDetail(_fb.getDeskType(), lstPatCliniData, userVO);
					
					if(lstPatCliniData!=null && lstPatCliniData.size()>0)
					{	
					// Nursing Desk Data Set
					PatNursingDeskDataFlagsMenuWiseUTL.setMenuDetailFlag(_request, _fb.getDeskMenuId());
					}
					// Setting Data in Session
					setRequestValueInMap(_request);

					objStatus.add(Status.TRANSINPROCESS,"Record Saved Successfully","");
				}
				catch (HisRenewalRequiredException e)
				{
					e.printStackTrace();
					objStatus.add(Status.ERROR_AE, "Renewal Required", "");
					flag = false;
				}
				catch (HisEpisodeOpenInEmergencyException e)
				{
					e.printStackTrace();
					objStatus.add(Status.ERROR, "Episode Opened in emergency", "");
					flag = false;
				}
				catch (HisRecordNotFoundException e)
				{
					e.printStackTrace();
					objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
					flag = false;
				}
				catch (HisDataAccessException e)
				{
					e.printStackTrace();
					objStatus.add(Status.ERROR_DA, "", "Data Access Error");
					flag = false;
				}
				catch (HisApplicationExecutionException e)
				{
					e.printStackTrace();
					objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
					flag = false;
				}
				catch (HisException e)
				{
					e.printStackTrace();
					objStatus.add(Status.ERROR, "", "Error");
					flag = false;
				}
				finally
				{
					WebUTIL.setStatus(_request, objStatus);
				}
				return flag;
			}
			
			private static void setRequestValueInMap(HttpServletRequest _request)
			{
				HttpSession session = WebUTIL.getSession(_request);
				List<GenericTemplateUtility.TempParameter> lstTempData = null;
				Map<String, Map<String, String>> mpTempParaValues = null;
				try
				{
					// Getting Entered Parameter Values
					lstTempData = GenericTemplateUtility.getTempParameterValueList(_request);
					if (session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP) != null) mpTempParaValues = (Map<String, Map<String, String>>) session
							.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP);
					mpTempParaValues = new HashMap<String, Map<String, String>>();
					for (GenericTemplateUtility.TempParameter para : lstTempData)
					{
						Map<String, String> map = mpTempParaValues.get(para.getTemplateId());
						if (map == null) map = new HashMap<String, String>();
						map.put(para.getParaId(), para.getParaValue());
						mpTempParaValues.put(para.getTemplateId(), map);
					}
					WebUTIL.setAttributeInSession(_request, GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP, mpTempParaValues);
				}
				finally
				{
				}
			}

			//Added by Vasu on 07.June.2019 to get Complaints Clinical Template Data
			public static void getTemplateTileEssentials(EHRSection_ComplaintsFB _fb, HttpServletRequest _request)
			{
				HttpSession session = _request.getSession();
				Status objStatus = new Status();
				String strHtmlCode="";

				try
				{
					Map<String, Object> mpTempParaValues = new HashMap<String, Object>();
					String deskMenuId = "1";//For Complaints
					String deskType = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
					String deskId = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ID);
					
					PatientClinicalDocDetailVO clinicalDocVO= new PatientClinicalDocDetailVO();	
					UserVO userVO = getUserVO(_request);
					PatientDetailVO selectedPatientVO =null;
					selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
					clinicalDocVO = (PatientClinicalDocDetailVO) session.getAttribute(EHRConfig.CLINICAL_DOCUMENT_TYPE_DETAILS);
					mpTempParaValues = EHRSection_ComplaintsDATA.getTemplateClinicalData(userVO,selectedPatientVO,clinicalDocVO,deskMenuId,deskType,deskId);
					 
					List<GenericTemplateUtility.TempParameter> voTempPara  = (List)mpTempParaValues.get("TEMPLATE_CONTENT");

					List<EHRSection_ComplaintsVO> voComplaints = new ArrayList<EHRSection_ComplaintsVO>();
					
					EHRSection_ComplaintsVO ehrSecComplaintsVo=null;
					if(voTempPara!=null)
					
					for(GenericTemplateUtility.TempParameter voPara : voTempPara)
					{
						 String templateContent=voPara.getTemplateContent();
						 
						 ehrSecComplaintsVo= new EHRSection_ComplaintsVO();
						 
						 ehrSecComplaintsVo.setTemplateContent(templateContent);
						 
						 ehrSecComplaintsVo.setTemplateName(voPara.getTemplateName());
						 ehrSecComplaintsVo.setRecordDate(voPara.getRecordDate());
						 
						 voComplaints.add(ehrSecComplaintsVo);
						
					}
					EHRVOUtility.setPatComplaints(_request, selectedPatientVO.getPatCrNo(), voComplaints);
					EHRVO voEHR  = EHRVOUtility.getEHRVO(_request,_fb.getPatCrNo());
					String ftlvalue="";
					 
					 List lstPrintTemplates = (List)session.getAttribute(EHRConfig.CLINICAL_SECTION_COMP_LIST);
					 Template template = null;
					Map<String, Object> data = new HashMap<String, Object>();
					data.put("voEHR", voEHR);
					
						if(lstPrintTemplates!=null)
						{
						for(int i=0;i<lstPrintTemplates.size();i++)
						{
							PatientClinicalDocDetailVO vo  = (PatientClinicalDocDetailVO)lstPrintTemplates.get(i);
				
					    		if(vo.getClinicalSecCompKey().equals("ENC_COMPLAINTS"))
					    			
					    		{
					    		
					    		ftlvalue= vo.getClinicalSecTemplateContent();
					    		//System.out.println(ftlvalue);
					    	
					    		}
					    	}
							
							
						} 
						
						if(ftlvalue!=null && !ftlvalue.trim().equals(""))
						{
							StringTemplateLoader stringLoader = new StringTemplateLoader();
							String firstTemplate = "";
							//System.out.println(ftlvalue);
							stringLoader.putTemplate(firstTemplate, ftlvalue);
							Configuration cfg1 = new Configuration();
							cfg1.setTemplateLoader(stringLoader);
							//System.out.println(stringLoader);
							template = cfg1.getTemplate(firstTemplate);
						
						StringWriter out = new StringWriter();
						template.process(data, out);
						out.flush();
						strHtmlCode += out.toString();
						
						//System.out.println(strHtmlCode);
						
						_fb.setFtlValueForComplaints(strHtmlCode);
						}
						else
						{
							_fb.setFtlValueForComplaints("");
						}
					
						
							
				}
				catch (HisRecordNotFoundException e)
				{
					e.printStackTrace();
					objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					WebUTIL.setStatus(_request, objStatus);
					/*response.getWriter().write(strHtmlCode);*/
				}
				
			}

			//Added by Vasu on 07.June.2019 to get Complaints Clinical Template Data
			public static void getEssentialDataToPopulate(EHRSection_ComplaintsFB _fb, HttpServletRequest _request,HttpServletResponse response)
			{
				HttpSession session = _request.getSession();
				Status objStatus = new Status();
				String strHtmlCode="";

				try
				{
					Map<String, Object> mpTempParaValues = new HashMap<String, Object>();
					String deskMenuId = "1";//For Complaints
					String deskType = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
					String deskId = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ID);
					
					PatientClinicalDocDetailVO clinicalDocVO= new PatientClinicalDocDetailVO();	
					UserVO userVO = getUserVO(_request);
					PatientDetailVO selectedPatientVO =null;
					selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
					clinicalDocVO = (PatientClinicalDocDetailVO) session.getAttribute(EHRConfig.CLINICAL_DOCUMENT_TYPE_DETAILS);
					mpTempParaValues = EHRSection_ComplaintsDATA.getTemplateClinicalData(userVO,selectedPatientVO,clinicalDocVO,deskMenuId,deskType,deskId);
					 
					List<GenericTemplateUtility.TempParameter> voTempPara  = (List)mpTempParaValues.get("TEMPLATE_CONTENT");

					List<EHRSection_ComplaintsVO> voComplaints = new ArrayList<EHRSection_ComplaintsVO>();
					
					EHRSection_ComplaintsVO ehrSecComplaintsVo=null;
					
					for(GenericTemplateUtility.TempParameter voPara : voTempPara)
					{
						 String templateContent=voPara.getTemplateContent();
						 
						 ehrSecComplaintsVo= new EHRSection_ComplaintsVO();
						 
						 ehrSecComplaintsVo.setTemplateContent(templateContent);
						 
						 ehrSecComplaintsVo.setTemplateName(voPara.getTemplateName());
						 ehrSecComplaintsVo.setRecordDate(voPara.getRecordDate());
						 
						 voComplaints.add(ehrSecComplaintsVo);
						
					}
					EHRVOUtility.setPatComplaints(_request, selectedPatientVO.getPatCrNo(), voComplaints);
					EHRVO voEHR  = EHRVOUtility.getEHRVO(_request,_fb.getPatCrNo());
					String ftlvalue="";
					 
					 List lstPrintTemplates = (List)session.getAttribute(EHRConfig.CLINICAL_SECTION_COMP_LIST);
					 Template template = null;
					Map<String, Object> data = new HashMap<String, Object>();
					data.put("voEHR", voEHR);
					
						if(lstPrintTemplates!=null)
						{
						for(int i=0;i<lstPrintTemplates.size();i++)
						{
							PatientClinicalDocDetailVO vo  = (PatientClinicalDocDetailVO)lstPrintTemplates.get(i);
				
					    		if(vo.getClinicalSecCompKey().equals("ENC_COMPLAINTS"))
					    			
					    		{
					    		
					    		ftlvalue= vo.getClinicalSecTemplateContent();
					    		//System.out.println(ftlvalue);
					    	
					    		}
					    	}
							
							
						} 
						
						if(ftlvalue!=null && !ftlvalue.trim().equals(""))
						{
							StringTemplateLoader stringLoader = new StringTemplateLoader();
							String firstTemplate = "";
							//System.out.println(ftlvalue);
							stringLoader.putTemplate(firstTemplate, ftlvalue);
							Configuration cfg1 = new Configuration();
							cfg1.setTemplateLoader(stringLoader);
							//System.out.println(stringLoader);
							template = cfg1.getTemplate(firstTemplate);
						
						StringWriter out = new StringWriter();
						template.process(data, out);
						out.flush();
						strHtmlCode += out.toString();
						
						//System.out.println(strHtmlCode);
						
						_fb.setFtlValueForComplaints(strHtmlCode);
						}
						else
						{
							_fb.setFtlValueForComplaints("");
						}
					
						objStatus.add(Status.NEW);
						response.getWriter().write(strHtmlCode);
							
				}
				catch (HisRecordNotFoundException e)
				{
					e.printStackTrace();
					objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					_request.setAttribute(Config.STATUS_OBJECT, objStatus);
					 WebUTIL.setStatus(_request, objStatus);
				}
				
				
			}
			

}


