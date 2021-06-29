/**
##		Date					: 26-08-2019
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Vasu
*/


package ehr.questionnaire.presentation;

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
import ehr.questionnaire.vo.EHRSection_QuestionnaireVO;
import ehr.vo.EHRVO;
import ehr.vo.EHRVOUtility;
import emr.vo.PatientClinicalDocDetailVO;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import opd.OpdConfig;




import registration.controller.fb.CRNoFB;

public class EHRSection_QuestionnaireUTL extends ControllerUTIL
{

			public static void getTemplateTileEssentials(EHRSection_QuestionnaireFB _fb, HttpServletRequest _request)
			{
				HttpSession session = _request.getSession();
				Status objStatus = new Status();
				String strHtmlCode="";

				try
				{
					Map<String, Object> mpTempParaValues = new HashMap<String, Object>();
					String deskMenuId = "277";//For Questionnaire
					String deskType = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
					String deskId = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ID);
					
					PatientClinicalDocDetailVO clinicalDocVO= new PatientClinicalDocDetailVO();	
					UserVO userVO = getUserVO(_request);
					PatientDetailVO selectedPatientVO =null;
					selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
					clinicalDocVO = (PatientClinicalDocDetailVO) session.getAttribute(EHRConfig.CLINICAL_DOCUMENT_TYPE_DETAILS);
					mpTempParaValues = EHRSection_QuestionnaireDATA.getTemplateClinicalData(userVO,selectedPatientVO,clinicalDocVO,deskMenuId,deskType,deskId);
					 
					List<GenericTemplateUtility.TempParameter> voTempPara  = (List)mpTempParaValues.get("TEMPLATE_CONTENT");

					List<EHRSection_QuestionnaireVO> voQuestionnaire = new ArrayList<EHRSection_QuestionnaireVO>();
					
					EHRSection_QuestionnaireVO ehrSecQuestionnaireVo=null;
					if(voTempPara!=null)
					
					for(GenericTemplateUtility.TempParameter voPara : voTempPara)
					{
						 String templateContent=voPara.getTemplateContent();
						 
						 ehrSecQuestionnaireVo= new EHRSection_QuestionnaireVO();
						 
						 ehrSecQuestionnaireVo.setTemplateContent(templateContent);
						 ehrSecQuestionnaireVo.setTemplateName(voPara.getTemplateName());
						 ehrSecQuestionnaireVo.setRecordDate(voPara.getRecordDate());
						 
						 voQuestionnaire.add(ehrSecQuestionnaireVo);
						
					}
					EHRVOUtility.setPatQuestionnaire(_request, selectedPatientVO.getPatCrNo(), voQuestionnaire);
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
				
					    		if(vo.getClinicalSecCompKey().equals("QUESTIONNAIRE"))
					    			
					    		{
					    		
					    		ftlvalue= vo.getClinicalSecTemplateContent();
					    		
					    	
					    		}
					    	}
							
							
						} 
						
						if(ftlvalue!=null && !ftlvalue.trim().equals(""))
						{
							StringTemplateLoader stringLoader = new StringTemplateLoader();
							String firstTemplate = "";
							
							stringLoader.putTemplate(firstTemplate, ftlvalue);
							Configuration cfg1 = new Configuration();
							cfg1.setTemplateLoader(stringLoader);
							
							template = cfg1.getTemplate(firstTemplate);
						
						StringWriter out = new StringWriter();
						template.process(data, out);
						out.flush();
						strHtmlCode += out.toString();
						
						
						
						_fb.setFtlValueForQuestionnaire(strHtmlCode);
						}
						else
						{
							_fb.setFtlValueForQuestionnaire("");
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

			public static void getEssentialDataToPopulate(EHRSection_QuestionnaireFB _fb, HttpServletRequest _request,HttpServletResponse response)
			{
				HttpSession session = _request.getSession();
				Status objStatus = new Status();
				String strHtmlCode="";

				try
				{
					Map<String, Object> mpTempParaValues = new HashMap<String, Object>();
					String deskMenuId = "277";//For Questionnaire
					String deskType = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
					String deskId = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ID);
					
					PatientClinicalDocDetailVO clinicalDocVO= new PatientClinicalDocDetailVO();	
					UserVO userVO = getUserVO(_request);
					PatientDetailVO selectedPatientVO =null;
					selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
					clinicalDocVO = (PatientClinicalDocDetailVO) session.getAttribute(EHRConfig.CLINICAL_DOCUMENT_TYPE_DETAILS);
					mpTempParaValues = EHRSection_QuestionnaireDATA.getTemplateClinicalData(userVO,selectedPatientVO,clinicalDocVO,deskMenuId,deskType,deskId);
					 
					List<GenericTemplateUtility.TempParameter> voTempPara  = (List)mpTempParaValues.get("TEMPLATE_CONTENT");

                    List<EHRSection_QuestionnaireVO> voQuestionnaire = new ArrayList<EHRSection_QuestionnaireVO>();
					
					EHRSection_QuestionnaireVO ehrSecQuestionnaireVo=null;
					
					for(GenericTemplateUtility.TempParameter voPara : voTempPara)
					{
						 String templateContent=voPara.getTemplateContent();
						 
						 ehrSecQuestionnaireVo= new EHRSection_QuestionnaireVO();
						 
						 ehrSecQuestionnaireVo.setTemplateContent(templateContent);
						 ehrSecQuestionnaireVo.setTemplateName(voPara.getTemplateName());
						 ehrSecQuestionnaireVo.setRecordDate(voPara.getRecordDate());
						 
						 voQuestionnaire.add(ehrSecQuestionnaireVo);
						
					}
					EHRVOUtility.setPatQuestionnaire(_request, selectedPatientVO.getPatCrNo(), voQuestionnaire);
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
				
					    		if(vo.getClinicalSecCompKey().equals("QUESTIONNAIRE"))
					    			
					    		{
					    		
					    		ftlvalue= vo.getClinicalSecTemplateContent();
					    		
					    	
					    		}
					    	}
							
							
						} 
						
						
						
						if(ftlvalue!=null && !ftlvalue.trim().equals(""))
						{
							StringTemplateLoader stringLoader = new StringTemplateLoader();
							String firstTemplate = "";
							
							stringLoader.putTemplate(firstTemplate, ftlvalue);
							Configuration cfg1 = new Configuration();
							cfg1.setTemplateLoader(stringLoader);
							
							template = cfg1.getTemplate(firstTemplate);
						
						StringWriter out = new StringWriter();
						template.process(data, out);
						out.flush();
						strHtmlCode += out.toString();
						
						
						
						_fb.setFtlValueForQuestionnaire(strHtmlCode);
						}
						else
						{
							_fb.setFtlValueForQuestionnaire("");
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


