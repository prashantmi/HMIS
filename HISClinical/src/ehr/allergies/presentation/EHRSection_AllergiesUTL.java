/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.allergies.presentation;

import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ehr.EHRConfig;
import ehr.allergies.vo.EHRSection_AllergiesVO;
import ehr.chronicdisease.vo.EHRSection_ChronicDiseaseVO;
import ehr.vo.EHRVO;
import ehr.vo.EHRVOUtility;
import emr.vo.PatientClinicalDocDetailVO;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class EHRSection_AllergiesUTL extends ControllerUTIL
{
	public static void getEssentialData(EHRSection_AllergiesFB _fb,HttpServletRequest _request, HttpServletResponse response) throws IOException 
	{
		HttpSession session = _request.getSession();
		Status objStatus = new Status();
		String strHtmlCode="";

		try
		{
			List<EHRSection_AllergiesVO> lstAllergies= new ArrayList<EHRSection_AllergiesVO>();
			
			UserVO userVO = getUserVO(_request);
			PatientDetailVO selectedPatientVO =null;
			selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			lstAllergies = EHRSection_AllergiesDATA.getAllergiesData(userVO,selectedPatientVO);
			 System.out.println(lstAllergies);
			 EHRVOUtility.setAllergiesVO(_request, selectedPatientVO.getPatCrNo(), lstAllergies);
			 EHRVO voEHR  = EHRVOUtility.getEHRVO(_request,_fb.getPatCrNo());
			 String ftlvalue="";
			 //List lstPrintTemplates = voEHR.getListClinicalDocEssentials();
			 List lstPrintTemplates = (List)session.getAttribute(EHRConfig.CLINICAL_SECTION_COMP_LIST);
			 Template template = null;
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("voEHR", voEHR);
			
				if(lstPrintTemplates!=null)
				{
				for(int i=0;i<lstPrintTemplates.size();i++)
				{
					PatientClinicalDocDetailVO vo  = (PatientClinicalDocDetailVO)lstPrintTemplates.get(i);
		
			    		if(vo.getClinicalSecCompKey().equals("PAT_ALLERGY"))
			    			
			    		{
			    		
			    		ftlvalue= vo.getClinicalSecTemplateContent();
			    		System.out.println(ftlvalue);
			    	/*	_fb.setFtlvalue(ftlvalue);*/
			    		}
			    	}
					
					
				} 
				
				if(ftlvalue!=null && !ftlvalue.trim().equals(""))
				{
					StringTemplateLoader stringLoader = new StringTemplateLoader();
					String firstTemplate = "";
					System.out.println(ftlvalue);
					stringLoader.putTemplate(firstTemplate, ftlvalue);
					Configuration cfg1 = new Configuration();
					cfg1.setTemplateLoader(stringLoader);
					System.out.println(stringLoader);
					template = cfg1.getTemplate(firstTemplate);
				}
				StringWriter out = new StringWriter();
				template.process(data, out);
				out.flush();
				strHtmlCode += out.toString();
				
				System.out.println(strHtmlCode);
				
				_fb.setFtlValueForAllergies(strHtmlCode);
			
			
				
					
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

	public static void getEssentialDataToPopulate(EHRSection_AllergiesFB _fb,HttpServletRequest _request, HttpServletResponse _response)
	{
		HttpSession session = _request.getSession();
		Status objStatus = new Status();
		
		String strHtmlCode="";

		try
		{
			List<EHRSection_AllergiesVO> lstAllergies= new ArrayList<EHRSection_AllergiesVO>();
			
			UserVO userVO = getUserVO(_request);
			PatientDetailVO selectedPatientVO =null;
			selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			lstAllergies = EHRSection_AllergiesDATA.getAllergiesData(userVO,selectedPatientVO);
			 System.out.println(lstAllergies);
			 EHRVOUtility.setAllergiesVO(_request, selectedPatientVO.getPatCrNo(), lstAllergies);
			 EHRVO voEHR  = EHRVOUtility.getEHRVO(_request,_fb.getPatCrNo());
			 String ftlvalue="";
			 //List lstPrintTemplates = voEHR.getListClinicalDocEssentials();
			 List lstPrintTemplates = (List)session.getAttribute(EHRConfig.CLINICAL_SECTION_COMP_LIST);
			 Template template = null;
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("voEHR", voEHR);
			
				if(lstPrintTemplates!=null)
				{
				for(int i=0;i<lstPrintTemplates.size();i++)
				{
					PatientClinicalDocDetailVO vo  = (PatientClinicalDocDetailVO)lstPrintTemplates.get(i);
		
			    		if(vo.getClinicalSecCompKey().equals("PAT_ALLERGY"))
			    			
			    		{
			    		
			    		ftlvalue= vo.getClinicalSecTemplateContent();
			    		System.out.println(ftlvalue);
			    	
			    		}
			    	}
					
					
				} 
				
				if(ftlvalue!=null && !ftlvalue.trim().equals(""))
				{
					StringTemplateLoader stringLoader = new StringTemplateLoader();
					String firstTemplate = "";
					System.out.println(ftlvalue);
					stringLoader.putTemplate(firstTemplate, ftlvalue);
					Configuration cfg1 = new Configuration();
					cfg1.setTemplateLoader(stringLoader);
					System.out.println(stringLoader);
					template = cfg1.getTemplate(firstTemplate);
				}
				StringWriter out = new StringWriter();
				template.process(data, out);
				out.flush();
				strHtmlCode += out.toString();
				
				System.out.println(strHtmlCode);
				
				_fb.setFtlValueForAllergies(strHtmlCode);
			
				objStatus.add(Status.NEW);
				_response.getWriter().write(strHtmlCode);
				
					
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
		




