package opd.transaction.controller.util;

/**
 * @author  CDAC
 */

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
import hisglobal.utility.HisPrinterSupport;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.OpdClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.fb.TemplateParameterMasterFB;
import opd.master.controller.util.TemplateUtilityUTIL;
import opd.master.controller.util.TemplateUtilityUTIL.TempParameter;
import opd.transaction.controller.data.OpdTemplateTabDATA;
import opd.transaction.controller.fb.ConsentHtmlParsingFB;
import opd.transaction.controller.fb.OpdTemplateTabFB;

public class OpdTemplateTabUTIL extends ControllerUTIL
{
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	// * Setting Template Tab Essential
	public static void getSetDeskMenuTemplatesEssentials(OpdTemplateTabFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		UserVO userVO = getUserVO(_rq);
		HttpSession session = WebUTIL.getSession(_rq);

		List lstTemplateIds = new ArrayList();
		Map mpTempPara = new HashMap();
		Map mpActiveTemp = new HashMap();
		Map mpInActiveTemp = new HashMap();
		Map putmap = new HashMap();
		List activeTemps = new ArrayList();
		List inActiveTemps = new ArrayList();
		List beanArr = new ArrayList();
		PatientDetailVO[] dailyPatientVOs = null;

		try
		{
			// Set For Bean Essentials
			_fb.setUserSeatId(userVO.getSeatId());
			_fb.setDepartmentUnitCode((String) session.getAttribute(OpdConfig.OPD_DESK_UNIT_CODE));
			/*DailyPatientVO[] al=(DailyPatientVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			DailyPatientVO voDP = new DailyPatientVO();
			for (int i = 0; i < al.length; i++)
			{
				voDP = (DailyPatientVO) al[i];
				if (voDP.getPatCrNo().equals(_fb.getPatCrNo())) break;
			}*/
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
			_fb.setEpisodeCode(patientDetailVO.getEpisodeCode());
			_fb.setEpisodeVisitNo(patientDetailVO.getEpisodeVisitNo());
			/*DeskDetailVO[] voDesks = (DeskDetailVO[]) session.getAttribute(OpdConfig.OPD_LEFT_DESK_MENU_DTL);
			DeskDetailVO voDV = null;
			boolean flag = false;
			for (int i = 0; i < voDesks.length; i++)
			{
				voDV = (DeskDetailVO) voDesks[i];
				if (voDV.getDeskMenuId().equals(_fb.getDeskMenuId()))
				{
					flag = true;
					break;
				}
			}
			if (!flag)
			{
				voDesks = (DeskDetailVO[]) session.getAttribute(OpdConfig.OPD_RIGHT_DESK_MENU_DTL);
				voDV = null;
				for (int i = 0; i < voDesks.length; i++)
				{
					voDV = (DeskDetailVO) voDesks[i];
					if (voDV.getDeskMenuId().equals(_fb.getDeskMenuId()))
					{
						flag = true;
						break;
					}
				}
			}
			if (flag) _fb.setDeskMenuName(voDV.getUserDeskMenuName());*/
//  ----------- Desk Menu Name and Set in Form Bean
			_fb.setDeskMenuName("");
			// Set Template Essentials
			// if CRNO present in session
			if (_fb.getPatCrNo() != null && !_fb.getPatCrNo().equals(""))
			{
				// template menu already opened
				if (session.getAttribute(OpdConfig.OPD_DESK_TEMPLATES_ACTIVE_IDS_MAP) != null)
				{
					mpActiveTemp = (HashMap) session.getAttribute(OpdConfig.OPD_DESK_TEMPLATES_ACTIVE_IDS_MAP);
					mpInActiveTemp = (HashMap) session.getAttribute(OpdConfig.OPD_DESK_TEMPLATES_INACTIVE_IDS_MAP);;
					mpTempPara = (HashMap) session.getAttribute(OpdConfig.OPD_DESK_TEMPLATES_ACTIVE_MAP);
				}
				if (mpActiveTemp.get(_fb.getDeskMenuId()) != null)
				{
					activeTemps = (ArrayList) mpActiveTemp.get(_fb.getDeskMenuId());
					inActiveTemps = (ArrayList) mpInActiveTemp.get(_fb.getDeskMenuId());
					beanArr = (ArrayList) mpTempPara.get(_fb.getDeskMenuId());
					_fb.setActiveTemplateIds(activeTemps);
				}
				else
				{
					lstTemplateIds = OpdTemplateTabDATA.getOpdDeskMenuTemplates(_fb.getDepartmentUnitCode(), _fb
							.getDeskMenuId(), userVO);
					if (lstTemplateIds.size() > 0)
					{
						_fb.setHaveTemplate("1");
						for (int i = 0; i < lstTemplateIds.size(); i++)
						{
							Entry entObj = (Entry) lstTemplateIds.get(i);
							Entry entNew = new Entry();
							entNew.setValue(entObj.getValue().trim().split("#")[0].trim());
							entNew.setLabel(entObj.getLabel());
							if (entObj.getValue().trim().split("#")[1].equals("1")) activeTemps.add(entNew);
							else if (entObj.getValue().trim().split("#")[1].equals("0")) inActiveTemps.add(entNew);
						}

						_fb.setActiveTemplateIds(activeTemps);
						beanArr = new ArrayList();// setShowTemplates(_fb,_rq);
						for (int i = 0; i < activeTemps.size(); i++)
						{
							Entry entObj = (Entry) _fb.getActiveTemplateIds().get(i);
							TemplateParameterMasterFB tFB = new TemplateParameterMasterFB();
							tFB.setTemplateId(entObj.getValue());
							beanArr.add(tFB);
						}
						mpActiveTemp.put(_fb.getDeskMenuId(), activeTemps);
						mpInActiveTemp.put(_fb.getDeskMenuId(), inActiveTemps);
						mpTempPara.put(_fb.getDeskMenuId(), beanArr);
					}
				}
				OpdTemplateTabUTIL.setTemplateParaValues(_fb, beanArr, _fb.getEpisodeVisitNo(), _rq);
			}
			else
			{
				_fb.setHaveTemplate("0");
			}
			putmap.put(OpdConfig.OPD_DESKMENU_TEMPLATES_ACTIVE_IDS_LIST, activeTemps);
			putmap.put(OpdConfig.OPD_DESKMENU_TEMPLATES_INACTIVE_IDS_LIST, inActiveTemps);
			putmap.put(OpdConfig.OPD_DESKMENU_TEMPLATES_ACTIVE_LIST, beanArr);

			putmap.put(OpdConfig.OPD_DESK_TEMPLATES_ACTIVE_IDS_MAP, mpActiveTemp);
			putmap.put(OpdConfig.OPD_DESK_TEMPLATES_INACTIVE_IDS_MAP, mpInActiveTemp);
			putmap.put(OpdConfig.OPD_DESK_TEMPLATES_ACTIVE_MAP, mpTempPara);
			WebUTIL.setMapInSession(putmap, _rq);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
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

	// * Setting Template Parameter Values
	static void setTemplateParaValues(OpdTemplateTabFB _fb, List beanArr, String visitNo, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		try
		{
			UserVO userVO = getUserVO(_rq);
			List activeTemps = _fb.getActiveTemplateIds();
			int showTempLen = beanArr.size();

			for (int i = 0; i < showTempLen; i++)
			{
				TemplateParameterMasterFB tFB = (TemplateParameterMasterFB) beanArr.get(i);
				Entry entObj = (Entry) activeTemps.get(i);
				tFB.setTemplateId(entObj.getValue());
				if (tFB.getActualParameterValues() == null) TemplateUtilityUTIL.createActualParaValueMap(tFB, _rq);

				OpdClinicalDetailVO voClinical = new OpdClinicalDetailVO();

				voClinical.setPatCrNo(_fb.getPatCrNo());
				voClinical.setEpisodeCode(_fb.getEpisodeCode());
				voClinical.setEpisodeVisitNo(visitNo);
				voClinical.setDeskMenuId(_fb.getDeskMenuId());
				voClinical.setTemplateId(tFB.getTemplateId());
				List lst = OpdTemplateTabDATA.getClinicalTemplateData(voClinical, userVO);
				OpdTemplateTabUTIL.setParaValueInMap(tFB, lst);

			}
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
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

	// * Saving Template Parameter Values in Clinical Table
	public static void saveTemplateParameterValues(OpdTemplateTabFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List beanArr = new ArrayList();
		HttpSession session = WebUTIL.getSession(_request);
		try
		{
			UserVO userVO = getUserVO(_request);
			beanArr = (ArrayList) session.getAttribute(OpdConfig.OPD_DESKMENU_TEMPLATES_ACTIVE_LIST);
			
			// Deleting Existing Template Data
			OpdClinicalDetailVO vo = new OpdClinicalDetailVO();
			vo.setPatCrNo(_fb.getPatCrNo());
			vo.setEpisodeCode(_fb.getEpisodeCode());
			vo.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			vo.setDeskMenuId(_fb.getDeskMenuId());
			List lstTempIds = (ArrayList) session.getAttribute(OpdConfig.OPD_DESKMENU_TEMPLATES_ACTIVE_IDS_LIST);
			OpdTemplateTabDATA.removeOpdTempParaValues(vo, lstTempIds,userVO);
			
			// Clear the Values in Session
			for (int j = 0; j < beanArr.size(); j++)
			{
				TemplateParameterMasterFB fbTPM = (TemplateParameterMasterFB) beanArr.get(j);
				fbTPM.getActualParameterValues().clear();
			}
			
			// Getting Entered Data from Request
			List<TempParameter> lstParaValues = TemplateUtilityUTIL.getTempParameterValueList(_request);
			List<OpdClinicalDetailVO> lstClinicalVo = new ArrayList<OpdClinicalDetailVO>();
			for (TempParameter tempPara : lstParaValues)
			{
				OpdClinicalDetailVO voOCD = new OpdClinicalDetailVO();
				voOCD.setPatCrNo(_fb.getPatCrNo());
				voOCD.setEpisodeCode(_fb.getEpisodeCode());
				voOCD.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
				voOCD.setDeskMenuId(_fb.getDeskMenuId());

				voOCD.setTemplateId(tempPara.getTemplateId());
				voOCD.setParaId(tempPara.getParaId());
				voOCD.setParaValue(tempPara.getParaValue());
				
				// Setting Values in Session
				for (int j = 0; j < beanArr.size(); j++)
				{
					TemplateParameterMasterFB fbTPM = (TemplateParameterMasterFB) beanArr.get(j);
					if (fbTPM.getTemplateId().equals(voOCD.getTemplateId()))
					{
						Map mp = fbTPM.getActualParameterValues();
						mp.put(voOCD.getParaId(), voOCD.getParaValue());
						break;
					}
				}
				lstClinicalVo.add(voOCD);
			}
			OpdTemplateTabDATA.saveOpdTempParaValues(lstClinicalVo, userVO);
			
			/*Enumeration requestParameters = _request.getParameterNames();
			while (requestParameters.hasMoreElements())
			{
				String nextElement = (String) requestParameters.nextElement();
				System.out.println("class type  =" + _request.getParameterValues(nextElement).getClass().isArray());
				if (nextElement.toString().startsWith("PARAMETER@"))
				{
					String requestDataArray[] = _request.getParameterValues(nextElement);
					if (requestDataArray.length != 0) for (int i = 0; i < requestDataArray.length; i++)
						if (!requestDataArray[i].trim().equals(""))
						{
							OpdClinicalDetailVO voOCD = new OpdClinicalDetailVO();

							voOCD.setPatCrNo(_fb.getPatCrNo());
							voOCD.setEpisodeCode(_fb.getEpisodeCode());
							voOCD.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
							voOCD.setDeskMenuId(_fb.getDeskMenuId());

							String pId = (String) nextElement;
							pId = pId.replace("PARAMETER@", "");
							voOCD.setTemplateId(pId.split("&")[1]);
							voOCD.setParaId(pId.split("&")[0]);
							voOCD.setParaValue(requestDataArray[i]);

							// Setting Values in Session
							for (int j = 0; j < beanArr.size(); j++)
							{

								TemplateParameterMasterFB fbTPM = (TemplateParameterMasterFB) beanArr.get(j);
								if (fbTPM.getTemplateId().equals(voOCD.getTemplateId()))
								{
									Map mp = fbTPM.getActualParameterValues();
									mp.put(voOCD.getParaId(), voOCD.getParaValue());
									break;
								}
							}
							OpdTemplateTabDATA.saveOrReplaceOpdTempParaValues(voOCD, userVO);
						}
				}
			}*/
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRenewalRequiredException e)
		{
			e.printStackTrace();
			System.out.println("Inside HisEpisodeOpenInEmergencyException");
			objStatus.add(Status.ERROR_AE, "Renewal Required", "");
		}
		catch (HisEpisodeOpenInEmergencyException e)
		{
			e.printStackTrace();
			System.out.println("Inside HisEpisodeOpenInEmergencyException");
			objStatus.add(Status.ERROR, "Episode Opened in emergency", "");
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	}

	// * Setting Parameter Values In Map
	public static void setParaValueInMap(TemplateParameterMasterFB tFB, List lstValues)
	{
		try
		{
			for (int i = 0; i < lstValues.size(); i++)
			{
				Entry entObj = (Entry) lstValues.get(i);
				Map mp = tFB.getActualParameterValues();
				mp.put(entObj.getValue(), entObj.getLabel());
			}
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException" + e);
		}
	}

	// * Adding Non-Default Templates
	public static void addUndefaultTemplate(OpdTemplateTabFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		List activeTemps = new ArrayList();
		List inActiveTemps = new ArrayList();
		List beanArr = new ArrayList();
		try
		{
			activeTemps = (ArrayList) session.getAttribute(OpdConfig.OPD_DESKMENU_TEMPLATES_ACTIVE_IDS_LIST);
			inActiveTemps = (ArrayList) session.getAttribute(OpdConfig.OPD_DESKMENU_TEMPLATES_INACTIVE_IDS_LIST);
			beanArr = (ArrayList) session.getAttribute(OpdConfig.OPD_DESKMENU_TEMPLATES_ACTIVE_LIST);

			String selTemps[] = _fb.getSelUndefaultTemp().split("#");
			for (int i = 0; i < selTemps.length; i++)
			{
				Entry entObj = null;
				for (int j = 0; j < inActiveTemps.size(); j++)
					if (((Entry) inActiveTemps.get(j)).getValue().equals(selTemps[i]))
					{
						entObj = (Entry) inActiveTemps.get(j);
						inActiveTemps.remove(j);
						break;
					}
				if (entObj != null)
				{
					activeTemps.add(entObj);
					TemplateParameterMasterFB tFB = new TemplateParameterMasterFB();
					tFB.setTemplateId(entObj.getValue());
					TemplateUtilityUTIL.getTemplateParametersData(tFB, _rq);
					beanArr.add(tFB);
				}
			}
			_fb.setActiveTemplateIds(activeTemps);
			OpdTemplateTabUTIL.setTemplateParaValues(_fb, beanArr, _fb.getEpisodeVisitNo(), _rq);

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
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

	// * Printing Consent
	public static void printConsent(OpdTemplateTabFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_request);
		try
		{
			ConsentHtmlParsingFB newFB = new ConsentHtmlParsingFB();
			newFB.setTemplateId(_fb.getConsentHtmlToPrint());
			ConsentHtmlParsingUTIL.parseHtml(newFB, _request);
			String strHtmData = (String) session.getAttribute(OpdConfig.OPD_TEMPLATE_CONSENT_HTML_DATA);

			ConvertHTMLToPDF converter = new ConvertHTMLToPDF(strHtmData);
			converter.convertToText();
			HisPrinterSupport.createTempFile(converter.getTargetContent(), "TempConsentPrint");
			//HisPrinterSupport.printSlip(_request.getRemoteAddr(), "TempConsentPrint.dat");

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRenewalRequiredException e)
		{
			e.printStackTrace();
			System.out.println("Inside HisEpisodeOpenInEmergencyException");
			objStatus.add(Status.ERROR_AE, "Renewal Required", "");
		}
		catch (HisEpisodeOpenInEmergencyException e)
		{
			e.printStackTrace();
			System.out.println("Inside HisEpisodeOpenInEmergencyException");
			objStatus.add(Status.ERROR, "Episode Opened in emergency", "");
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
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	}

	// * Setting Template Report Essentials
	public static void setTempReportEssentials(OpdTemplateTabFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		List lstPrevVisits = new ArrayList();
		Map putmap = new HashMap();
		try
		{
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);

			lstPrevVisits = OpdTemplateTabDATA.getPrevVisitDates(_fb.getPatCrNo(), _fb.getEpisodeCode(), userVO);

			putmap.put(OpdConfig.OPD_LIST_PREV_VISIT_DATES, lstPrevVisits);
			_fb.setChoice(OpdConfig.CHOICE_PREVVISIT_TEMP_WISE);

			WebUTIL.setMapInSession(putmap, _rq);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
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

	// * Setting Parameter Report Essentials
	public static void setParaReportEssentials(OpdTemplateTabFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		UserVO userVO = getUserVO(_rq);

		Map putmap = new HashMap();

		List lstTemplateIds = new ArrayList();
		List lstAllTempParameters = new ArrayList();
		List lstParaNameList = new ArrayList();

		try
		{
			setSysdate(_rq);

			// Getting All Templates set in Session
			lstTemplateIds = OpdTemplateTabDATA.getOpdDeskMenuTemplates(_fb.getDepartmentUnitCode(), _fb.getDeskMenuId(),
					userVO);

			List tempIds = new ArrayList();
			List newtemplat = new ArrayList();
			for (int i = 0; i < lstTemplateIds.size(); i++)
			{
				Entry entObj = (Entry) lstTemplateIds.get(i);
				Entry entNew = new Entry();
				entNew.setValue(entObj.getValue().trim().split("#")[0].trim());
				entNew.setLabel(entObj.getLabel());
				newtemplat.add(entNew);
				tempIds.add(entNew.getValue());
			}
			lstTemplateIds = newtemplat;
			putmap.put(OpdConfig.OPD_DESK_TEMPLATES_BY_CRNO_VISIT_LIST, lstTemplateIds);

			// Getting All Parameters TemplateWise with Actual ParaIds e.g. Matrix
			lstAllTempParameters = OpdTemplateTabDATA.getOpdDeskTemplatesAllParas(tempIds, userVO);
			putmap.put(OpdConfig.OPD_DESK_ALL_TEMP_PARAS_BY_CRNO_VISIT_LIST, lstAllTempParameters);

			// Splitting Unique ParaId from Compound
			List tempParaIds = new ArrayList();
			List tempWiseParaIds = new ArrayList();
			for (int i = 0; i < lstAllTempParameters.size(); i++)
			{
				Entry entObj = (Entry) lstAllTempParameters.get(i);
				String str[] = entObj.getLabel().split("#");
				for (int j = 0; j < str.length; j++)
				{
					tempParaIds.add(str[j]);
					Entry entNew = new Entry();
					entNew.setValue(entObj.getValue());
					entNew.setLabel(str[j]);
					boolean flag = false;
					for (int k = 0; k < tempWiseParaIds.size(); k++)
					{
						if (((Entry) tempWiseParaIds.get(k)).getValue().equals(entObj.getValue())
								&& ((Entry) tempWiseParaIds.get(k)).getLabel().equals(str[j]))
						{
							flag = true;
							break;
						}
					}
					if (!flag) tempWiseParaIds.add(entNew);
				}
			}

			// Fetching Parameters Names
			lstParaNameList = OpdTemplateTabDATA.getOpdTemplParaNames(tempParaIds, userVO);
			putmap.put(OpdConfig.OPD_DESK_ALL_TEMP_PARAS_NAMES_BY_CRNO_VISIT_LIST, lstParaNameList);

			// Setting Map of Parameters by Name
			Map listTempParasForChoice = new HashMap();
			for (int i = 0; i < lstTemplateIds.size(); i++)
			{
				Entry entObj = (Entry) lstTemplateIds.get(i);
				List lstParas = new ArrayList();
				for (int j = 0; j < tempWiseParaIds.size(); j++)
				{
					Entry tempEnt = (Entry) tempWiseParaIds.get(j);
					if (tempEnt.getValue().equals(entObj.getValue()))
					{
						Entry para = new Entry();
						para.setValue(tempEnt.getLabel());
						String paraName = "";
						for (int k = 0; k < lstParaNameList.size(); k++)
						{
							if (((Entry) lstParaNameList.get(k)).getValue().equals(tempEnt.getLabel()))
							{
								paraName = ((Entry) lstParaNameList.get(k)).getLabel();
								break;
							}
						}
						para.setLabel(paraName);
						lstParas.add(para);
					}
				}
				listTempParasForChoice.put(entObj.getValue(), lstParas);
			}
			putmap.put(OpdConfig.OPD_DESK_TEMPWISE_PARAS_BY_CRNO_VISIT_MAP, listTempParasForChoice);

			_fb.setChoice(OpdConfig.CHOICE_PREVVISIT_PARA_WISE);
			_fb.setHaveTemplate(OpdConfig.CHOICE_PREVVISIT_SHOW_PARA_WISE);
			WebUTIL.setMapInSession(putmap, _rq);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
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

	// * Getting Template Wise Report
	public static void getTempWiseReport(OpdTemplateTabFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		UserVO userVO = getUserVO(_rq);

		Map putmap = new HashMap();

		List lstTemplateIds = new ArrayList();
		Map mpTempPara = new HashMap();
		List activeTemps = new ArrayList();
		List beanArr = new ArrayList();

		try
		{
			setSysdate(_rq);

			lstTemplateIds = OpdTemplateTabDATA.getOpdDeskMenuTemplates(_fb.getDepartmentUnitCode(), _fb.getDeskMenuId(),
					userVO);

			beanArr = new ArrayList();
			for (int i = 0; i < lstTemplateIds.size(); i++)
			{
				Entry entObj = (Entry) lstTemplateIds.get(i);
				Entry entNew = new Entry();
				entNew.setValue(entObj.getValue().trim().split("#")[0].trim());
				entNew.setLabel(entObj.getLabel());
				activeTemps.add(entNew);
				TemplateParameterMasterFB tFB = new TemplateParameterMasterFB();
				tFB.setTemplateId(entNew.getValue());
				tFB.setTemplateName(entNew.getLabel());
				beanArr.add(tFB);
			}
			_fb.setActiveTemplateIds(activeTemps);
			mpTempPara.put(_fb.getDeskMenuId(), beanArr);
			OpdTemplateTabUTIL.setTemplateParaValues(_fb, beanArr, _fb.getSelectedVisitNo(), _rq);

			putmap.put(OpdConfig.OPD_DESKMENU_TEMPLATES_ACTIVE_IDS_LIST, activeTemps);
			putmap.put(OpdConfig.OPD_DESKMENU_TEMPLATES_ACTIVE_LIST, beanArr);

			_fb.setChoice(OpdConfig.CHOICE_PREVVISIT_TEMP_WISE);
			WebUTIL.setMapInSession(putmap, _rq);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
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

	// * Getting Parameter Wise Report
	public static void getParaWiseReport(OpdTemplateTabFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);

		List lstPrevVisits = new ArrayList();
		List lstAllTempParameters = new ArrayList();
		List lstSelClinData = new ArrayList();
		Map putmap = new HashMap();

		Map mapMainShow = new HashMap();
		List lstMainList = new ArrayList();

		try
		{
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);

			lstPrevVisits = OpdTemplateTabDATA.getPrevVisitsInBetween(_fb.getPatCrNo(), _fb.getEpisodeCode(), _fb
					.getFromVisitDate(), _fb.getToVisitDate(), userVO);

			if (lstPrevVisits.size() > 0)
			{
				String selectedParas = _fb.getConsentHtmlToPrint();
				String paraArr[] = selectedParas.split("&");

				// Map ( key:Temp Id, Value : Set of Selected Parameters Ids)
				Set setTemps = new HashSet(); // ****** Set of All Selected Templates Ids
				Map mpTempSelParas = new HashMap(); // ****** Map
				for (int i = 0; i < paraArr.length; i++)
				{
					String tempId = paraArr[i].split("#")[1];
					setTemps.add(tempId);
					HashSet hsTempPara;
					if ((hsTempPara = (HashSet) mpTempSelParas.get(tempId)) == null) hsTempPara = new HashSet();
					hsTempPara.add(paraArr[i].split("#")[0]);
					mpTempSelParas.put(tempId, hsTempPara);
				}

				lstAllTempParameters = (ArrayList) session
						.getAttribute(OpdConfig.OPD_DESK_ALL_TEMP_PARAS_BY_CRNO_VISIT_LIST);

				// Map (Key:Temp Id,Value : Set of All Parameters IDs)
				Map mpTempAllParas = new HashMap(); // ****** Map
				for (int i = 0; i < lstAllTempParameters.size(); i++)
				{
					String tempId = ((Entry) lstAllTempParameters.get(i)).getValue();
					HashSet hsTempPara;
					if ((hsTempPara = (HashSet) mpTempAllParas.get(tempId)) == null) hsTempPara = new HashSet();
					hsTempPara.add(((Entry) lstAllTempParameters.get(i)).getLabel());
					mpTempAllParas.put(tempId, hsTempPara);
				}

				// Map (Key:Temp Id,Value : Set of Final Parameters IDs)
				Map mpFinalTempParas = new HashMap(); // Map
				for (Object o : setTemps)
				{
					String tempId = (String) o;

					Set setSel = (HashSet) mpTempSelParas.get(tempId);
					HashSet setFinalPara = new HashSet();

					Set allParaSet = (HashSet) mpTempAllParas.get(tempId);
					for (Iterator ite = allParaSet.iterator(); ite.hasNext();)
					{
						boolean flagMatch;
						String pivotPara = (String) ite.next();
						if (pivotPara.contains("#"))
						{
							flagMatch = true;
							String str[] = pivotPara.split("#");
							/*for (int j = 0; j < str.length; j++)
							{
								boolean eleMatch = true;
								for (Object selPara : setSel)
								{
									if (((String) selPara).equals(str[j]))
										eleMatch = eleMatch && true;										
									else
									{
										eleMatch = false;
										break;
									}
								}
								if (!eleMatch)
								{
									flagMatch = false;
									break;
								}
							}*/
							for (Object selPara : setSel)
							{
								boolean eleMatch = false;
								for (int j = 0; j < str.length; j++)
								{
									if (((String) selPara).equals(str[j]))
									{
										eleMatch = true;
										break;
									}
								}
								flagMatch=flagMatch && eleMatch;
							}
							
						}
						else
						{
							flagMatch = false;
							for (Object selPara : setSel)
							{
								if (((String) selPara).equals(pivotPara))
								{
									flagMatch = true;
									break;
								}
							}
						}
						if (flagMatch) setFinalPara.add(pivotPara);
					}

					mpFinalTempParas.put(tempId, setFinalPara);
				}

				// Constructing ParaQuery
				String paraQuery = "";
				for (Object o : setTemps)
				{
					String tempId = (String) o;
					paraQuery += " (HGNUM_TEMPLATE_ID=" + tempId + " AND HGSTR_PARA_ID IN (";
					Set setPara = (HashSet) mpFinalTempParas.get(tempId);
					String str = "";
					for (Object act : setPara)
						str += "'" + (String) act + "',";
					if (!str.equals("")) str = str.substring(0, str.length() - 1);
					paraQuery += str + ")) OR";
				}
				if (!paraQuery.equals("")) paraQuery = paraQuery.substring(0, paraQuery.length() - 2);

				// Constructing Visit List
				String qryVisits = "";
				for (int i = 0; i < lstPrevVisits.size(); i++)
					qryVisits += ((Entry) lstPrevVisits.get(i)).getValue() + ",";
				if (!qryVisits.equals("")) qryVisits = qryVisits.substring(0, qryVisits.length() - 1);

				OpdClinicalDetailVO voOpdCD = new OpdClinicalDetailVO();
				voOpdCD.setPatCrNo(_fb.getPatCrNo());
				voOpdCD.setEpisodeCode(_fb.getEpisodeCode());
				voOpdCD.setDeskMenuId(_fb.getDeskMenuId());

				// Fetching the Actual Parameter Values .....
				lstSelClinData = OpdTemplateTabDATA.getOpdClinDataBySelParas(voOpdCD, qryVisits, paraQuery, userVO);

				// Map (Key : Visit No , Value: Map of (tempId, ArrayList( paraId,ParaValue ) )
				Map mpFinalData = new HashMap();
				for (int i = 0; i < lstSelClinData.size(); i++)
				{
					Entry entObj = new Entry();

					String visitNo = ((Entry) lstSelClinData.get(i)).getValue().split("%")[0];
					String tempId = ((Entry) lstSelClinData.get(i)).getValue().split("%")[1].split("~")[0];

					entObj.setValue(((Entry) lstSelClinData.get(i)).getValue().split("%")[1].split("~")[1]);
					entObj.setLabel(((Entry) lstSelClinData.get(i)).getLabel());

					Map map;
					ArrayList al;
					if ((map = (HashMap) mpFinalData.get(visitNo)) == null)
					{
						map = new HashMap();
						al = new ArrayList();
						map.put(tempId, al);
						mpFinalData.put(visitNo, map);
					}
					else if ((al = (ArrayList) map.get(tempId)) == null)
					{
						al = new ArrayList();
						map.put(tempId, al);
					}
					al.add(entObj);
				}

				// Creating Show Table of Data of Previous Visit
				ArrayList lstParaNameList = (ArrayList) session
						.getAttribute(OpdConfig.OPD_DESK_ALL_TEMP_PARAS_NAMES_BY_CRNO_VISIT_LIST);

				// Creating Show List of Lists on the basis of Parameter-Wise or Visit
				int sizeLst = lstPrevVisits.size(); // Number of visits to See

				if (_fb.getHaveTemplate().equals(OpdConfig.CHOICE_PREVVISIT_SHOW_VISIT_WISE))
				{
					// Visit-Wise
					for (int i = 0; i < sizeLst; i++)
					{
						List lst = new ArrayList();
						lstMainList.add(lstPrevVisits.get(i));
						String visitNo = ((Entry) lstPrevVisits.get(i)).getValue();
						for (Object o : setTemps)
						{
							String tempId = (String) o;
							Set setPara = (HashSet) mpFinalTempParas.get(tempId);
							for (Object act : setPara)
							{
								String paraId = (String) act;
								String namePara = "";
								if (paraId.contains("#"))
								{
									String strArr[] = paraId.split("#");
									for (int j = 0; j < strArr.length; j++)
									{
										for (int k = 0; k < lstParaNameList.size(); k++)
											if (((Entry) lstParaNameList.get(k)).getValue().equals(strArr[j]))
											{
												namePara += ((Entry) lstParaNameList.get(k)).getLabel() + " ";
												break;
											}
									}
								}
								else
								{
									for (int j = 0; j < lstParaNameList.size(); j++)
										if (((Entry) lstParaNameList.get(j)).getValue().equals(paraId))
										{
											namePara = ((Entry) lstParaNameList.get(j)).getLabel();
											break;
										}
								}

								String valuePara = "";
								Map map = (HashMap) mpFinalData.get(visitNo);
								if (map != null)
								{
									ArrayList lstVisitTempValue = (ArrayList) (map).get(tempId);
									if (lstVisitTempValue != null) for (int k = 0; k < lstVisitTempValue.size(); k++)
										if (((Entry) lstVisitTempValue.get(k)).getValue().equals(paraId))
										{
											if (((Entry) lstVisitTempValue.get(k)).getLabel() != null) valuePara = ((Entry) lstVisitTempValue
													.get(k)).getLabel();
											break;
										}
								}
								Entry entObj = new Entry();
								entObj.setValue(namePara);
								entObj.setLabel(valuePara);
								lst.add(entObj);
							}
							mapMainShow.put(visitNo, lst);
						}
					}
				}
				else if (_fb.getHaveTemplate().equals(OpdConfig.CHOICE_PREVVISIT_SHOW_PARA_WISE))
				{
					// Parameter-Wise
					for (Object o : setTemps)
					{
						String tempId = (String) o;
						Set setPara = (HashSet) mpFinalTempParas.get(tempId);
						for (Object act : setPara)
						{
							String paraId = (String) act;
							String namePara = "";
							if (paraId.contains("#"))
							{
								String strArr[] = paraId.split("#");
								for (int j = 0; j < strArr.length; j++)
								{
									for (int k = 0; k < lstParaNameList.size(); k++)
										if (((Entry) lstParaNameList.get(k)).getValue().equals(strArr[j]))
										{
											namePara += ((Entry) lstParaNameList.get(k)).getLabel() + " ";
											break;
										}
								}
							}
							else
							{
								for (int j = 0; j < lstParaNameList.size(); j++)
									if (((Entry) lstParaNameList.get(j)).getValue().equals(paraId))
									{
										namePara = ((Entry) lstParaNameList.get(j)).getLabel();
										break;
									}
							}
							Entry ent = new Entry();
							ent.setValue(paraId);
							ent.setLabel(namePara);
							lstMainList.add(ent);

							List lst = new ArrayList();
							for (int i = 0; i < sizeLst; i++)
							{
								String visitNo = ((Entry) lstPrevVisits.get(i)).getValue();
								String valuePara = "";
								Map map = (HashMap) mpFinalData.get(visitNo);
								if (map != null)
								{
									ArrayList lstVisitTempValue = (ArrayList) (map).get(tempId);
									if (lstVisitTempValue != null) for (int k = 0; k < lstVisitTempValue.size(); k++)
										if (((Entry) lstVisitTempValue.get(k)).getValue().equals(paraId))
										{
											if (((Entry) lstVisitTempValue.get(k)).getLabel() != null) valuePara = ((Entry) lstVisitTempValue
													.get(k)).getLabel();
											break;
										}
								}
								Entry entObj = new Entry();
								entObj.setValue(((Entry) lstPrevVisits.get(i)).getLabel());
								entObj.setLabel(valuePara);
								lst.add(entObj);
							}
							mapMainShow.put(paraId, lst);
						}
					}
				}
			}
			putmap.put(OpdConfig.OPD_PREV_VISIT_PARA_WISE_REP_LIST, lstMainList);
			putmap.put(OpdConfig.OPD_PREV_VISIT_PARA_WISE_REP_MAP, mapMainShow);
			_fb.setChoice(OpdConfig.CHOICE_PREVVISIT_PARA_WISE);
			WebUTIL.setMapInSession(putmap, _rq);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			//objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			objStatus.add(Status.ERROR_DA,"Wrong Parameters Combination Selection", "");
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
