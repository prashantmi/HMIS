/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.serviceprocedure.presentation;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.Inv_RequisitionRaisingEpisodeVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import investigationDesk.InvestigationConfig;
import ehr.EHRConfig;
import ehr.chronicdisease.presentation.EHRSection_ChronicDiseaseDATA;
import ehr.chronicdisease.presentation.EHRSection_ChronicDiseaseFB;
import ehr.chronicdisease.vo.EHRSection_ChronicDiseaseVO;
import ehr.investigation.vo.*;
import ehr.patientreferral.presentation.EHRSection_PatientReferralDATA;
import ehr.patientreferral.vo.EHRSection_PatientReferralVO;
import ehr.serviceprocedure.vo.EHRSection_ServiceProcedureVO;
import ehr.vo.EHRVO;
import ehr.vo.EHRVOUtility;
import ehr.vo.EHR_AccessPermissionVO;
import ehr.vo.EHR_PatientEncounterVO;
import emr.vo.PatientClinicalDocDetailVO;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class EHRSection_ServiceProcedureUTL extends ControllerUTIL
{

	public static void getEssentialData(EHRSection_ServiceProcedureFB _fb,HttpServletRequest _request, HttpServletResponse response)
	{
		
		HttpSession session = _request.getSession();
		Status objStatus = new Status();		
		String strHtmlCode="";

		try
		{
			List<EHRSection_ServiceProcedureVO> lstServiceProcedure= new ArrayList<EHRSection_ServiceProcedureVO>();
			
			UserVO userVO = getUserVO(_request);
			PatientDetailVO selectedPatientVO =null;
			
			PatientClinicalDocDetailVO clinicalDocVO= new PatientClinicalDocDetailVO();	
			clinicalDocVO = (PatientClinicalDocDetailVO) session.getAttribute(EHRConfig.CLINICAL_DOCUMENT_TYPE_DETAILS);
			
			selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			lstServiceProcedure = EHRSection_ServiceProcedureDATA.getServiceProcedures(userVO,selectedPatientVO,clinicalDocVO);
			
			 EHRVOUtility.setPatientServiceProcedureVO(_request, selectedPatientVO.getPatCrNo(), lstServiceProcedure);
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
		
			    		if(vo.getClinicalSecCompKey().equals("SERVICE_PROCEDURE"))
			    			
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
				
				StringWriter out = new StringWriter();
				template.process(data, out);
				out.flush();
				strHtmlCode += out.toString();
				
				System.out.println(strHtmlCode);
				
				_fb.setFtlValueForServiceProcedure(strHtmlCode);
			
				}
				else
				{
					_fb.setFtlValueForServiceProcedure("");
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

	public static void getEssentialDataToPopulate(EHRSection_ServiceProcedureFB _fb, HttpServletRequest _request,HttpServletResponse response) 
	{
		HttpSession session = _request.getSession();
		Status objStatus = new Status();		
		String strHtmlCode="";

		try
		{
			List<EHRSection_ServiceProcedureVO> lstServiceProcedure= new ArrayList<EHRSection_ServiceProcedureVO>();
			
			UserVO userVO = getUserVO(_request);
			PatientDetailVO selectedPatientVO =null;
			
			PatientClinicalDocDetailVO clinicalDocVO= new PatientClinicalDocDetailVO();	
			clinicalDocVO = (PatientClinicalDocDetailVO) session.getAttribute(EHRConfig.CLINICAL_DOCUMENT_TYPE_DETAILS);
			
			selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			lstServiceProcedure = EHRSection_ServiceProcedureDATA.getServiceProcedures(userVO,selectedPatientVO,clinicalDocVO);
			
			 EHRVOUtility.setPatientServiceProcedureVO(_request, selectedPatientVO.getPatCrNo(), lstServiceProcedure);
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
		
			    		if(vo.getClinicalSecCompKey().equals("SERVICE_PROCEDURE"))
			    			
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
				
				StringWriter out = new StringWriter();
				template.process(data, out);
				out.flush();
				strHtmlCode += out.toString();
				
				System.out.println(strHtmlCode);
				
				_fb.setFtlValueForServiceProcedure(strHtmlCode);
			
				}
				else
				{
					_fb.setFtlValueForServiceProcedure("");
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
		




