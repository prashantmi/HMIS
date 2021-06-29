/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.patientreferral.presentation;

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

import com.google.gson.JsonElement;
import com.ibm.icu.util.BytesTrie.Entry;

import ehr.EHRConfig;
import ehr.chronicdisease.presentation.EHRSection_ChronicDiseaseDATA;
import ehr.chronicdisease.presentation.EHRSection_ChronicDiseaseFB;
import ehr.chronicdisease.vo.EHRSection_ChronicDiseaseVO;
import ehr.patientreferral.business.EHRSection_PatientReferralBO;
import ehr.patientreferral.vo.EHRSection_PatientReferralVO;
import ehr.vo.EHRVO;
import ehr.vo.EHRVOUtility;
import emr.vo.PatientClinicalDocDetailVO;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import com.google.gson.Gson;

public class EHRSection_PatientReferralUTL extends ControllerUTIL
{
	public static void getEssentialData(EHRSection_PatientReferralFB _fb,HttpServletRequest _request, HttpServletResponse response) throws IOException 
	{
		HttpSession session = _request.getSession();
		Status objStatus = new Status();
		String strHtmlCode="";
		String JSONObject_ReferList = "";
       Map essentialMap = new HashMap(); 
		try
		{
			List<EHRSection_PatientReferralVO> lstPatientReferral= new ArrayList<EHRSection_PatientReferralVO>();
			
			UserVO userVO = getUserVO(_request);
			PatientDetailVO selectedPatientVO =null;
			
			String patCrNo = _fb.getPatCrNo();
			
			PatientClinicalDocDetailVO clinicalDocVO= new PatientClinicalDocDetailVO();	
			clinicalDocVO = (PatientClinicalDocDetailVO) session.getAttribute(EHRConfig.CLINICAL_DOCUMENT_TYPE_DETAILS);
			
			selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			
			essentialMap = EHRSection_PatientReferralDATA.getReferralEssentialsData(patCrNo,userVO);
			
			lstPatientReferral = EHRSection_PatientReferralDATA.getPatientReferralData(userVO,selectedPatientVO,clinicalDocVO);
			
			_request.getSession().setAttribute(EHRConfig.REFERRED_DEPARTMENT_LIST, lstPatientReferral);
			
			if(lstPatientReferral!=null && lstPatientReferral.size()>0)
			{
				 JSONObject_ReferList=new Gson().toJson(lstPatientReferral);
				JSONObject_ReferList = JSONObject_ReferList.toString();
							
			}
			
			_request.getSession().setAttribute("previouslyReferredDeptListJSON", JSONObject_ReferList);
			 //System.out.println(lstPatientReferral);
			 EHRVOUtility.setPatientReferralVO(_request, selectedPatientVO.getPatCrNo(), lstPatientReferral);
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
		
			    		if(vo.getClinicalSecCompKey().equals("PAT_REFERRAL"))
			    			
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
				
				_fb.setFtlValueForPatReferral(strHtmlCode);
			
				}
				else
				{
					_fb.setFtlValueForPatReferral("");
				}
				List<Entry> allDepartments = (List<Entry>)essentialMap.get("referral_department_list");
				
				_request.getSession().setAttribute("referDepartmentList", allDepartments);	
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
	
	public static void getEssentialDataToPopulate(EHRSection_PatientReferralFB _fb,HttpServletRequest _request, HttpServletResponse response) throws IOException 
	{
		HttpSession session = _request.getSession();
		Status objStatus = new Status();
		String strHtmlCode="";

		try
		{
			List<EHRSection_PatientReferralVO> lstPatientReferral= new ArrayList<EHRSection_PatientReferralVO>();
			
			UserVO userVO = getUserVO(_request);
			PatientDetailVO selectedPatientVO =null;
			
			PatientClinicalDocDetailVO clinicalDocVO= new PatientClinicalDocDetailVO();	
			clinicalDocVO = (PatientClinicalDocDetailVO) session.getAttribute(EHRConfig.CLINICAL_DOCUMENT_TYPE_DETAILS);
			
			selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			lstPatientReferral = EHRSection_PatientReferralDATA.getPatientReferralData(userVO,selectedPatientVO,clinicalDocVO);
			 System.out.println(lstPatientReferral);
			 EHRVOUtility.setPatientReferralVO(_request, selectedPatientVO.getPatCrNo(), lstPatientReferral);
			 EHRVO voEHR  = EHRVOUtility.getEHRVO(_request,_fb.getPatCrNo());
			 String ftlvalue="";
			 //List lstPrintTemplates = (List)session.getAttribute(EHRConfig.CLINICAL_SECTION_COMP_LIST);
			 List lstPrintTemplates = (List)session.getAttribute("compositionList");
			 
			 
			 Template template = null;
			Map<String, Object> data = new HashMap<String, Object>();  
			data.put("voEHR", voEHR);
			
				if(lstPrintTemplates!=null)
				{
				for(int i=0;i<lstPrintTemplates.size();i++)
				{
					PatientClinicalDocDetailVO vo  = (PatientClinicalDocDetailVO)lstPrintTemplates.get(i);
		
			    		if(vo.getClinicalSecCompKey().equals("PAT_REFERRAL"))
			    			
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
				
				_fb.setFtlValueForPatReferral(strHtmlCode);
			
				}
				else
				{
					_fb.setFtlValueForPatReferral("");
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

	//Added by Vasu on 16.Dec.2019
	public static void savePatientReferralDetails(EHRSection_PatientReferralFB _fb, HttpServletRequest _request,HttpServletResponse _response)
	{
		HttpSession session = _request.getSession();
		Status objStatus = new Status();
		UserVO userVO = getUserVO(_request);
        PatientDetailVO selectedPatientVO =null;
        List<EHRSection_PatientReferralVO> lstReferVo =new ArrayList<EHRSection_PatientReferralVO>();
        List<EHRSection_PatientReferralVO> lstPatientReferral= new ArrayList<EHRSection_PatientReferralVO>();
		try
		{
			selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			PatientClinicalDocDetailVO clinicalDocVO= new PatientClinicalDocDetailVO();	
			clinicalDocVO = (PatientClinicalDocDetailVO) session.getAttribute(EHRConfig.CLINICAL_DOCUMENT_TYPE_DETAILS);
             for(int i=0;i<_fb.getReferdeptCode().length;i++){
				
            	String deptCode[] = _fb.getReferdeptCode()[i].split("#",0);
            	EHRSection_PatientReferralVO referVO= new EHRSection_PatientReferralVO();
            	referVO.setReferdeptCode(deptCode[0]);
            	referVO.setReferDeptUnitCode(deptCode[1]);
            	referVO.setReferTypeCode(_fb.getReferTypeCode()[i]);
            	referVO.setReferReason(_fb.getReferReason()[i]);
				
				lstReferVo.add(referVO);
				
			}
             
             EHRSection_PatientReferralBO.savePatientReferralDetails(lstReferVo,selectedPatientVO,userVO);
             
             lstPatientReferral = EHRSection_PatientReferralDATA.getPatientReferralData(userVO,selectedPatientVO,clinicalDocVO);
 			
 			_request.getSession().setAttribute("PrevReferDepartmentList", lstPatientReferral);	
 			
            String JSONObject_ImageList=new Gson().toJson(lstPatientReferral);
			
            _response.setContentType("application/Json");
			
            _response.getWriter().write(JSONObject_ImageList.toString());

 			
 			 EHRVOUtility.setPatientReferralVO(_request, selectedPatientVO.getPatCrNo(), lstPatientReferral);
             
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
		
		}
	}

	//Added by Vasu on 18.Dec.2019
	public static void deletePreviousReferrals(EHRSection_PatientReferralFB fb,HttpServletRequest request, HttpServletResponse response,String slNo)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		EHRSection_PatientReferralVO referVO =new EHRSection_PatientReferralVO();
		try
		{
			UserVO userVO = getUserVO(request);	
		    PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
		    referVO.setSlNo(slNo);
		    EHRSection_PatientReferralBO.deletePreviousOperationDetails(referVO,userVO,ptaientDetailVO);
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
		




