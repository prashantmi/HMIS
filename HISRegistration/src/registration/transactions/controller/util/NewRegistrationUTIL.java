package registration.transactions.controller.util;

import hisglobal.config.HISConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisSQLManualException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
import hisglobal.utility.EmailSender.EmailHttpPostClient;
import hisglobal.utility.EmailSender.NICEmailClient;
import hisglobal.utility.EmailSender.config.EmailConfig;
import hisglobal.utility.SMSSender.SMSHttpPostClient;
import hisglobal.utility.SMSSender.SMSHttpsNICPostClient;
import hisglobal.utility.SMSSender.config.SMSConfig;
import hisglobal.utility.filetransfer.config.FileTransferConfig;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.WebRowSet;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.transport.http.HTTPSession;
import org.apache.struts2.util.TokenHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import registration.bo.RegEssentialBO;
import registration.config.RegistrationConfig;
import registration.config.RegistrationConfigurationBean;
import registration.config.Exceptions.HisInvalidTokenNumberException;
import registration.config.slip.NewRegistrationSlip;
import registration.config.slip.RegistrationSlip;
import registration.transactions.controller.actionsupport.NewPatientRegistrationSUP;
import registration.transactions.controller.data.NewRegistrationDATA;
import vo.registration.BeneficiaryPatientVO;
import vo.registration.CreditAvailDetailVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientIdVO;
import vo.registration.PatientVO;
import vo.registration.RegistrationConfigVO;
import vo.registration.RenewalConfigVO;

import java.util.*;

import registration.QRCodeTest;
//Lucene
/*import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;*/


public class NewRegistrationUTIL extends ControllerUTIL
{
/**
 * sets all new registration essentials
 * @param _request -HttpServletRequest
 */
	public static void setAllNewRegistrationEssentials(NewPatientRegistrationSUP objNewPatientRegistrationSUP,HttpServletRequest objRequest,
			HttpServletResponse objResponse, Map<Object, Object> mapSession,String callerName){		
		Status objStatus=new Status();
		Map mp=new HashMap();
		try
		{
			UserVO userVO=getUserVO(objRequest);
			HospitalMstVO hosVo= getHospitalVO(objRequest);
			
			userVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
			userVO.setTariffID(RegistrationConfig.NEW_REGISTRATION_TARIFF_ID);
			HttpSession session =objRequest.getSession();						
			
			//To Check the Cash Collection Back Date Day End Flag Check, Added by Singaravelan on 03-Jun-2015
			System.out.println("Back Date Day End Check Flag :::value :::"+userVO.getCheckBackDateDayEndFlag());			
			if(userVO.getCheckBackDateDayEndFlag().equals("1"))
				throw new HisRecordNotFoundException("Please do your day end first !");
			if(userVO.getCheckBackDateDayEndFlag().equals("2"))
				throw new HisRecordNotFoundException("Please deposit the money first at cash collection counter !");				
			
			mp=NewRegistrationDATA.getRegistrationFormInitialEssentials(userVO,objNewPatientRegistrationSUP.getIsUnitWiseRegistration());
			/*if(((String)session.getAttribute(RegistrationConfig.SESSION_FLAG_NEW_REGISTRATION_DUPLICATE))==null){
				mp=NewRegistrationDATA.getAllNewRegistrationEssentials(userVO,hosVo.getState());
				WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.SESSION_FLAG_NEW_REGISTRATION_DUPLICATE, RegistrationConfig.SESSION_FLAG_YES);
				WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.REGISTRATION_ESSENTIAL_MAP, mp);
			}
			else{
				mp=(Map)session.getAttribute(RegistrationConfig.REGISTRATION_ESSENTIAL_MAP);
			}*/
			 
			WebUTIL.setMapInSession(mp,objRequest,callerName);
			
			//NewRegistrationUTIL.setDeptOptions(_request, objNewPatRegSupp_p);
			//isRegistrationAllowed(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL,getUserVO(_request));
		}
		catch(HisRecordNotFoundException e){
			objNewPatientRegistrationSUP.addActionError(e.getMessage());
			//WebUTIL.setMapInSession(e.getEssentialMap(),objRequest,"NewRegistrationAction");
			
		}		
		catch(HisDataAccessException e){
		//	WebUTIL.setMapInSession(e.getEssentialMap(),_request,"NewRegistrationAction");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE, "","Application Execution Error");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			WebUTIL.setStatus(objRequest,objStatus);
		}	
	}
	
	public static void populateRegistrationFormEssentials_AJAX(NewPatientRegistrationSUP objNewPatientRegistrationSUP,HttpServletRequest objRequest,
			HttpServletResponse objResponse, Map<Object, Object> mapSession){		
		Status objStatus=new Status();
		String outputString="";
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		Map mp=new HashMap();
		Map mpToSetInSession=new HashMap();
		String strErrMsg="";
		Element msgElement=null;
		Element renewalConfigElement=null;
		String strAlreadyRegisteredFlag="0";
		try
		{
			UserVO userVO=getUserVO(objRequest);
			HospitalMstVO hospitalVO=getHospitalVO(objRequest);
			
			userVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
			userVO.setTariffID(RegistrationConfig.NEW_REGISTRATION_TARIFF_ID);
			
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			System.out.println("hospitalVO.getLocalLangCode :"+hospitalVO.getLocalLangCode());
			
			rootElement.setAttribute(RegistrationConfig.LOCAL_LANGUAGE, hospitalVO.getLocalLangCode());	//"marathi");
			
			//Creating message Element specially for Error Message
			msgElement=responseDocument.createElement("msgs");
			rootElement.appendChild(msgElement);
			msgElement.setAttribute("msg", strErrMsg);
			
			
			// Creating Node for Renewal COnfig. Now Putting only isreferred attribute, rest are putting in session
			renewalConfigElement=responseDocument.createElement("renewalConfig");
			rootElement.appendChild(renewalConfigElement);
						
			// Creating Node(defaults) for default values
			Element defaultElement=responseDocument.createElement("defaults");
			rootElement.appendChild(defaultElement);
			
			defaultElement.setAttribute("defaultpatAddCountryCode", RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			defaultElement.setAttribute("defaultpatAddStateCode", hospitalVO.getState());
			defaultElement.setAttribute("defaultpatAddDistrictCode", hospitalVO.getDistrictCode());
			defaultElement.setAttribute("defaultpatRefGnctdHospitalCode", userVO.getHospitalCode());
			defaultElement.setAttribute("defaultpatAddPIN", hospitalVO.getPinCode());
			defaultElement.setAttribute("defaultuserBoundDefaultGender", userVO.getUserBoundDefaultGender());
			
			// Calling DATA
			mp=NewRegistrationDATA.getRegistrationFormEssentials_AJAX(userVO,hospitalVO.getState(),objRequest);
			//RenewalConfigVO objRenewalConfigVO = (RenewalConfigVO)mp.get(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
			
			RegistrationConfigVO objRegistrationConfigVO = (RegistrationConfigVO)mp.get(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
			strAlreadyRegisteredFlag=objRegistrationConfigVO.getAlreadyRegisteredFlag();
			System.out.println("strAlreadyRegisteredFlag :"+strAlreadyRegisteredFlag);
			renewalConfigElement.setAttribute("alreadyRegistered",strAlreadyRegisteredFlag );
			renewalConfigElement.setAttribute("aadhaarSearchable", objRegistrationConfigVO.getIsAadhaarSearchable());
			renewalConfigElement.setAttribute("mobileSearch", objRegistrationConfigVO.getisMobileSearch());
			//Start:Sheeldarshi
			//Reason:Bug 10166 - System should be able to validate senior citizen category
			renewalConfigElement.setAttribute("seniorCitizenAgeLimit", objRegistrationConfigVO.getSeniorCitizenAgeLimit());
			renewalConfigElement.setAttribute("seniorCitizenCatCode", objRegistrationConfigVO.getSeniorCitizenCatCode());
			renewalConfigElement.setAttribute("barcodeSlipPrintFlag", objRegistrationConfigVO.getIsBarcodeSlipPrint());//By Mukund
			if(HISConfig.HIS_SNOMEDCT_SERVICES_ON.equalsIgnoreCase("ON"))
				renewalConfigElement.setAttribute("isSnomedServiceOn", RegistrationConfig.SNOMED_SERVICE_ON);
			else
				renewalConfigElement.setAttribute("isSnomedServiceOn", RegistrationConfig.SNOMED_SERVICE_OFF);
			//End
			Map<String, RenewalConfigVO> mapOfRenewalVoOnPatCatGroupKey = (Map<String, RenewalConfigVO>)mp.get(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO);
			
			// Setting in Session
			mpToSetInSession.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT, mp.get(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT));
			mpToSetInSession.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY, mp.get(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY));
			mpToSetInSession.put(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO, mapOfRenewalVoOnPatCatGroupKey);
			
			
			// Creating Json Object for XML Node
			if(objNewPatientRegistrationSUP.getIsUnitWiseRegistration()!=null && objNewPatientRegistrationSUP.getIsUnitWiseRegistration().equals("1"))
				createDepartmentListObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT),responseDocument,"departmentUnitCode");
			else
				createDepartmentListObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT),responseDocument,"departmentCode");
			
			createPatientCategoryListObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY),responseDocument,"patPrimaryCatCode");
			
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS),responseDocument,"patMaritalStatusCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_GENDER),responseDocument,"patGenderCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION),responseDocument,"patReligionCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_CASTE),responseDocument,"patCasteCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY),responseDocument,"patAddCountryCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_STATE),responseDocument,"patAddStateCode");
			/*  ## 		Modification Log							
	 		##		Modify Date				:10thMar'15 
	 		##		Reason	(CR/PRS)		:RMO Changes in Category Master, Registration Process
	 		##		Modify By				:Sheeldarshi 
			 */
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_RMO_EMP),responseDocument,"patRMOEmployee");
			//End
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY),responseDocument,"patIsUrban");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE),responseDocument,"patAgeUnit");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL),responseDocument,"patRefGnctdHospitalCode");
			
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL),responseDocument,"patRefGnctdHospitalDept");
			//createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL),responseDocument,"countryCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL),responseDocument,"patOccupation");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE),responseDocument,"patAddDistrictCode");
			
			createVerificationObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS),responseDocument,"patDocType");
			//Start:Sheeldarshi
			//Reason: Change for adding Organisation combo on All Registration Pages
			//Date: 14thJune'15
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_CLIENT_OPTION_LIST),responseDocument,"clientCode");
			//End
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL),responseDocument,"relationWithStaff");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE),responseDocument,"agsDistrictCode");
			//Start:Sheeldarshi
			//Reason: Change for adding leter type combo on All Registration Pages
			//Date: 14thJune'16
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_LETTER_TYPE_OPTION_LIST),responseDocument,"letterType");
			createPaymentModeObject((List<Entry>)mp.get(RegistrationConfig.PAYMENT_MODE_OPTION_LIST),responseDocument,"paymentModeCode");

			WebUTIL.setMapInSession(mpToSetInSession,objRequest,"NewRegistrationAction");
			
			
		}
		catch(Exception e){
			
			strErrMsg= e.getMessage();
			renewalConfigElement.setAttribute("alreadyRegistered",strAlreadyRegisteredFlag );
			//System.out.println("\nstrErrMsg "+strErrMsg);
			e.printStackTrace();
		}		
		finally
		{
			//Element msgElement = (Element) responseDocument.getFirstChild();
			try
			{
				
				msgElement.setAttribute("msg", strErrMsg);
				java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
				trf.newTransformer().transform( new DOMSource(responseDocument),new StreamResult(baos));
				outputString=baos.toString();
				//System.out.println("outputString "+outputString);
				writeResponse(objResponse, outputString);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}	
	}
	public static void writeResponse(HttpServletResponse resp, String output){
		try{
			resp.reset();
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/xml");
			resp.setHeader("Cache-Control", "no-cache");
			System.out.println(output);
		resp.getWriter().write(output);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static void getState_AJAX(HttpServletRequest objRequest, HttpServletResponse objResponse) {
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		String outputString="";
		try{
			String strCountryCode= (String)objRequest.getParameter("countryCode");
			UserVO userVO=getUserVO(objRequest);
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			List lstState=NewRegistrationDATA.getState_AJAX(userVO,strCountryCode);
			
			createOptionObject((List<Entry>)lstState,responseDocument,"patAddStateCode");
			
			
			//System.out.println("outputString "+outputString);
		}
		
		catch(Exception e){
			e.printStackTrace();
		}		
		finally
		{
			java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
			try {
				trf.newTransformer().transform( new DOMSource(responseDocument),new StreamResult(baos));
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			outputString=baos.toString();
			writeResponse(objResponse, outputString);
		}	
		
	}
	
	public static void getDistrict_AJAX(HttpServletRequest objRequest, HttpServletResponse objResponse) {
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		String outputString="";
		try{
			String strCountryCode= (String)objRequest.getParameter("countryCode");
			String strStateCode =	(String)objRequest.getParameter("stateCode");
			UserVO userVO=getUserVO(objRequest);
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			List lstDistrict=NewRegistrationDATA.getDistrict_AJAX(userVO,strStateCode,strCountryCode);
			
			createOptionObject((List<Entry>)lstDistrict,responseDocument,"patAddDistrictCode");
			
		}
		
		catch(Exception e){
			e.printStackTrace();
		}		
		finally
		{
			
			java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
			try {
				trf.newTransformer().transform( new DOMSource(responseDocument),new StreamResult(baos));
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			outputString=baos.toString();
			writeResponse(objResponse, outputString);
		}	
		
	}
	
	public static void getRefDept_AJAX(HttpServletRequest objRequest, HttpServletResponse objResponse) {
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		String outputString="";
		try{
			String strRefHospCode= (String)objRequest.getParameter("refHospCode");
			String strFlafRefHospOrInst = (String)objRequest.getParameter("flafRefHospOrInst");
			
			UserVO userVO=getUserVO(objRequest);
			
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			if(strFlafRefHospOrInst!=null && strFlafRefHospOrInst.equals("I"))
				strRefHospCode=userVO.getHospitalCode();
			
			List lstRefDept=NewRegistrationDATA.getRefDept_AJAX(userVO, strRefHospCode);
			
			createOptionObject((List<Entry>)lstRefDept,responseDocument,"patRefGnctdHospitalDept");
			
			//System.out.println("outputString "+outputString);
		}
		
		catch(Exception e){
			e.printStackTrace();
		}		
		finally
		{
			java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
			try {
				trf.newTransformer().transform( new DOMSource(responseDocument),new StreamResult(baos));
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			outputString=baos.toString();
			writeResponse(objResponse, outputString);
		}	
		
	}
	
	public static void getVerDocExceptCatDoc_AJAX(HttpServletRequest objRequest, HttpServletResponse objResponse) {
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		String outputString="";
		try{
			String strDocCode= (String)objRequest.getParameter("strDocCode");
			
			UserVO userVO=getUserVO(objRequest);
			
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
								
			List lstDocList=NewRegistrationDATA.getVerDocExceptCatDoc_AJAX(userVO, strDocCode);
			
			createVerificationObject((List<Entry>)lstDocList, responseDocument, "patDocType");
	
			System.out.println("outputString createVerificationObject :"+outputString);
		}
		
		catch(Exception e){
			e.printStackTrace();
		}		
		finally
		{
			java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
			try {
				trf.newTransformer().transform( new DOMSource(responseDocument),new StreamResult(baos));
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			outputString=baos.toString();
			System.out.println("outputString createVerificationObject 2 :"+outputString);
			writeResponse(objResponse, outputString);
		}	
		
	}
	
	public static void getPatDtlOnPatCatId(HttpServletRequest objRequest, HttpServletResponse objResponse) {
		
		System.out.println("NewRegistrationUTIL :: getPatDtlOnPatCatId");
		List<String> lstPatientJsonObjString = new ArrayList<String>();
		StringBuilder strResponse = new StringBuilder();
		String strMode="3";	//Changed to 3 from 0 to use the PIST_EMP_REGISTRATION_DTL Table
		try{
		
			String patPrimaryCatCode= (String)objRequest.getParameter("patPrimaryCatCode");
			String strSearchCatName =	(String)objRequest.getParameter("searchCatName");
			String strSearchCatId	=	(String)objRequest.getParameter("searchCatId");
			String strAlreadyRegistrdFlag = (String)objRequest.getParameter("alreadyRegisteredFlag");
			
			if(strAlreadyRegistrdFlag!=null && !strAlreadyRegistrdFlag.equals("") && !strAlreadyRegistrdFlag.equals("0")){
				strMode= strAlreadyRegistrdFlag;
				if(strAlreadyRegistrdFlag.equals("3"))
					strMode = "4";
			}
			
			UserVO userVO=getUserVO(objRequest);
			
			if(patPrimaryCatCode!=null && !patPrimaryCatCode.equals(""))
				lstPatientJsonObjString=NewRegistrationDATA.getPatDtlOnPatCatId(userVO,patPrimaryCatCode,strSearchCatName,strSearchCatId,strMode);
			
			
			strResponse.append("[");
			if(lstPatientJsonObjString!=null && lstPatientJsonObjString.size()>0){
				for(int i=0; i<lstPatientJsonObjString.size(); i++){
					if(i==0)
						strResponse.append(lstPatientJsonObjString.get(i));
					else
						strResponse.append(","+lstPatientJsonObjString.get(i));
				}
			}
			strResponse.append("]");
		}
		
		catch(Exception e){
			e.printStackTrace();
		}		
		finally
		{
			writeResponse(objResponse, strResponse.toString());
		}	
		
	}
public static void getPatDtlOnPatMobile(HttpServletRequest objRequest, HttpServletResponse objResponse) {
		
		System.out.println("NewRegistrationUTIL :: getPatDtlOnPatCatId");
		List<String> lstPatientJsonObjString = new ArrayList<String>();
		StringBuilder strResponse = new StringBuilder();
		String strMode="1";	//Changed to 3 from 0 to use the PIST_EMP_REGISTRATION_DTL Table
		try{
		
			String searchId= (String)objRequest.getParameter("searchId");
			String searchValue =	(String)objRequest.getParameter("searchValue");
			
			
			
			UserVO userVO=getUserVO(objRequest);
			
			//if(patPrimaryCatCode!=null && !patPrimaryCatCode.equals(""))
				lstPatientJsonObjString=NewRegistrationDATA.getPatDtlOnPatMobile(userVO,searchId,searchValue,strMode);
			
			
			strResponse.append("[");
			if(lstPatientJsonObjString!=null && lstPatientJsonObjString.size()>0){
				for(int i=0; i<lstPatientJsonObjString.size(); i++){
					if(i==0)
						strResponse.append(lstPatientJsonObjString.get(i));
					else
						strResponse.append(","+lstPatientJsonObjString.get(i));
				}
			}
			strResponse.append("]");
		}
		
		catch(Exception e){
			e.printStackTrace();
		}		
		finally
		{
			writeResponse(objResponse, strResponse.toString());
		}	
		
	}
	
	private static void createDepartmentListObject(List<Entry> list,Document responseDocument, String fieldName) 
	{
	 
		Element fieldElement=responseDocument.createElement(fieldName);
		for(Entry entry:list)
		{
			//101#10101#1001#1#1#30#M
			Element option= responseDocument.createElement("option");
			fieldElement.appendChild(option);
			String[] entryValueArray=entry.getValue().split("#");
			option.setAttribute("value", "{\"departmentCode\":\""+entryValueArray[0]+"\",\"departmentName\": \""+entry.getLabel()+"\",\"deptUnitCode\":\""+entryValueArray[1]+"\",\"roomCode\":\""+entryValueArray[2]+"\",\"roundRobinUnitFlag\":\""+entryValueArray[4]+"\",\"roundRobinRoomFlag\":\""+entryValueArray[3]+"\",\"ageBound\":\""+(entryValueArray[5])+"\",\"defaultGenderCode\":\""+entryValueArray[6]+"\"}");
		}
		responseDocument.getFirstChild().appendChild(fieldElement);
	}
	
	private static void createPatientCategoryListObject(List<Entry> list,Document responseDocument, String fieldName) 
	{
	 
		Element fieldElement=responseDocument.createElement(fieldName);
		for(Entry entry:list)
		{
			Element option= responseDocument.createElement("option");
			fieldElement.appendChild(option);
			String[] entryValueArray=entry.getValue().split("#");
			//option.setAttribute("value", "{\"patPrimaryCatCode\":\""+entryValueArray[0]+"\",\"patPrimaryCatName\": \""+entryValueArray[1]+"\",\"charges\":\""+entryValueArray[2]+"\",\"idReqFlag\":\""+entryValueArray[3]+"\",\"idSize\":\""+entryValueArray[4]+"\",\"idValidationType\":\""+entryValueArray[5]+"\",\"expiryFlag\":\""+entryValueArray[6]+"\",\"expiryDays\":\""+entryValueArray[7]+"\",\"expiryMonth\":\""+entryValueArray[8]+"\",\"patCatGroup\":\""+entryValueArray[9]+"\",\"patCatShortName\":\""+entryValueArray[10]+"\"}");
			option.setAttribute("value", "{\"patPrimaryCatCode\":\""+entryValueArray[0]+"\",\"patPrimaryCatName\": \""+entryValueArray[1]+"\",\"charges\":\""+entryValueArray[2]+"\",\"idReqFlag\":\""+entryValueArray[3]+"\",\"idSize\":\""+entryValueArray[4]+"\",\"idValidationType\":\""+entryValueArray[5]+"\",\"expiryFlag\":\""+entryValueArray[6]+"\",\"expiryDays\":\""+entryValueArray[7]+"\",\"expiryMonth\":\""+entryValueArray[8]+"\",\"patCatGroup\":\""+entryValueArray[9]+"\",\"patCatShortName\":\""+entryValueArray[10]+"\",\"patCatDocCode\":\""+entryValueArray[11]+"\",\"patCatDocIsAlternateId\":\""+entryValueArray[12]+"\",\"clientCode\":\""+entryValueArray[13]+"\",\"clientName\":\""+entryValueArray[14]+"\",\"patCatIsApprovalReq\":\""+entryValueArray[15]+"\",\"patCatBoundToDeptCode\":\""+entryValueArray[17]+"\"}");											
		}
		responseDocument.getFirstChild().appendChild(fieldElement);
	}
	
	private static void createVerificationObject(List<Entry> list,Document responseDocument, String fieldName) 
	{
	 
		Element fieldElement=responseDocument.createElement(fieldName);
		for(Entry entry:list)
		{
			Element option= responseDocument.createElement("option");
			fieldElement.appendChild(option);
			String[] entryValueArray=entry.getValue().split("#");
			option.setAttribute("value", "{\"patDocType\":\""+entryValueArray[0]+"\",\"patDocIdSize\": \""+entryValueArray[1]+"\",\"patDocValidationType\":\""+entryValueArray[2]+"\",\"patDocTypeName\":\""+entry.getLabel()+"\"}");
		}
		responseDocument.getFirstChild().appendChild(fieldElement);
	}
	
	private static void createOptionObject(List<Entry> list,Document responseDocument, String fieldName) 
	{
	 
		Element fieldElement=responseDocument.createElement(fieldName);
		for(Entry entry:list)
		{
			Element option= responseDocument.createElement("option");
			fieldElement.appendChild(option);
			
			option.setAttribute("value", entry.getValue());
			option.setAttribute("label", entry.getLabel());
		}
		responseDocument.getFirstChild().appendChild(fieldElement);
	}
	private static void createPaymentModeObject(List<Entry> list,Document responseDocument, String fieldName) 
	{
	 
		Element fieldElement=responseDocument.createElement(fieldName);
		for(Entry entry:list)
		{
			Element option= responseDocument.createElement("option");
			fieldElement.appendChild(option);
			String[] entryValueArray=entry.getValue().split("#");
			option.setAttribute("value", "{\"paymentModeCode\":\""+entryValueArray[0]+"\",\"recieptCategoryCode\": \""+entryValueArray[1]+"\",\"refundCategoryCode\":\""+entryValueArray[2]+"\",\"paymentCodeName\":\""+entry.getLabel()+"\"}");
		}
		responseDocument.getFirstChild().appendChild(fieldElement);
	}

	private static void createPatientDtlObject(String strPatientJasonObj,Document responseDocument, String fieldName) 
	{
		System.out.println("NewRegistrationUTIL :: createPatientDtlObject -->> Start");
		Element fieldElement=responseDocument.createElement(fieldName);
		
		fieldElement.setAttribute("value", strPatientJasonObj);
		responseDocument.getFirstChild().appendChild(fieldElement);
		//System.out.println("NewRegistrationUTIL :: createPatientDtlObject -->> End");
	}
	
	private static boolean createSaveMsgObject(Document responseDocument, String strIsSavedSuccussfulFlag,String strSavedMsg,
											String strIsFormFieldRestFlag, String strPrintDivContent, String strPrintFlag,String strIsDuplicatePatient) 
	{
		
	 
		Element elementSaveMsg =responseDocument.createElement("savedMsgDtl");
		elementSaveMsg.setAttribute("isSavedSuccussful", strIsSavedSuccussfulFlag);
		elementSaveMsg.setAttribute("savedMessage", strSavedMsg);
		elementSaveMsg.setAttribute("isFormFieldTobeReset", strIsFormFieldRestFlag);
		elementSaveMsg.setAttribute("isPrintFlag", strPrintFlag);
		elementSaveMsg.setAttribute("printDivContent", strPrintDivContent);
		elementSaveMsg.setAttribute("isDuplicatePatientPopup", strIsDuplicatePatient);

		
		responseDocument.getFirstChild().appendChild(elementSaveMsg);
		
		return true;
	}
	
	//By Mukund to set token as an attribute in SaveMsgDtl
		private static boolean createSaveMsgObject2(Document responseDocument, String strIsSavedSuccussfulFlag,String strSavedMsg,
					String strIsFormFieldRestFlag, String strPrintDivContent, String strPrintFlag,String strIsDuplicatePatient, String strTokenFrmStruts) 
			{
				
				Element elementSaveMsg =responseDocument.createElement("savedMsgDtl");
				elementSaveMsg.setAttribute("isSavedSuccussful", strIsSavedSuccussfulFlag);
				elementSaveMsg.setAttribute("savedMessage", strSavedMsg);
				elementSaveMsg.setAttribute("isFormFieldTobeReset", strIsFormFieldRestFlag);
				elementSaveMsg.setAttribute("isPrintFlag", strPrintFlag);
				elementSaveMsg.setAttribute("printDivContent", strPrintDivContent);
				elementSaveMsg.setAttribute("isDuplicatePatientPopup", strIsDuplicatePatient);
				elementSaveMsg.setAttribute("tkn", strTokenFrmStruts);
				
				responseDocument.getFirstChild().appendChild(elementSaveMsg);
				
				return true;
			}

	

	
	/**
	 * saves New Patient Registration Details
	 * generates a String of Queue, Departement, unit and room 
	 * @param _request -HttpServletRequest
	 * @param objNewPatRegSupp_p -NewRegistrationFBDuplicate form bean
	 * @return return true if any duplicate record found against the patient detail
	 * else save the patient details and register and return false
	 */
	public static void saveNewPatientRegistration(NewPatientRegistrationSUP objNewPatRegSupp_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p ){
		
		System.out.println("NewRegistrationUTIL :: saveNewPatientRegistration()");
		
		boolean exists=false;
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		String outputString="";
		
		UserVO userVO=getUserVO(objRequest_p);
		RenewalConfigVO objRenewalConfigVO=null;
		
		userVO.setTariffID(RegistrationConfig.NEW_REGISTRATION_TARIFF_ID);
		userVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
		Status objStatus =new Status();	
		
		String strIsSavedSuccussfulFlag="0", strSavedOrErrMsg="",strIsFormFieldResetFlag="0", strPrintDivContent="", strErrMsg="",strIsDuplicatePatient="";
		String strTokenToSet="";//By Mukund on 28.11.2016
		boolean flagSaveMsgObjCreated=false;
		String strBillPrintDiv="";
		
		EmailHttpPostClient emailPost=new EmailHttpPostClient();
		NICEmailClient  emailNICPost=new NICEmailClient();
	    EmailConfig objEmailConfig=new EmailConfig();
		
		try
		{
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			//gets the hospital information
			HospitalMstVO objHospitalMstVO = getHospitalVO(objRequest_p);
			
			PatientIdVO objPatientIdVO = new PatientIdVO();
			CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();
			BeneficiaryPatientVO objBenPatVO=new BeneficiaryPatientVO();
			objPatientIdVO.setPatIdNo(objNewPatRegSupp_p.getPatCardNo());
			objPatientIdVO.setPatDocType(objNewPatRegSupp_p.getPatDocType());
			String strUniqueIdDuplicyFlag = "0";
			//By Mukund on 25.11.2016//in the following lines new token were generated and then set inside the session using TokenHelper class
			String sessionTokenId = objRequest_p.getHeader("token");
			System.out.println("Session Token is: "+sessionTokenId);
			System.out.println("Struts 2 Token: "+ TokenHelper.getToken("token"));
			strTokenToSet = TokenHelper.generateGUID();
			TokenHelper.setSessionToken("token",strTokenToSet);
			System.out.println("New Token Id is: "+strTokenToSet);
			//End: Mukund			
			
			if(objNewPatRegSupp_p.getIsIdRequired()!=null && objNewPatRegSupp_p.getIsIdRequired().equals("2"))
				objNewPatRegSupp_p.setPatIdNo(objNewPatRegSupp_p.getHiddenPatIdNo());
			
			if(objNewPatRegSupp_p.getPatRefGnctdHospitalCode()!=null && !objNewPatRegSupp_p.getPatRefGnctdHospitalCode().equals(""))
				objNewPatRegSupp_p.setPatRefGnctdHospitalCode(objNewPatRegSupp_p.getPatRefGnctdHospitalCode().split("\\#")[0]);
			System.out.println("PatRefGnctdHospitalCode :"+objNewPatRegSupp_p.getPatRefGnctdHospitalCode());
			
			//To Check the Duplicacy on the National Id/Adhaar Id Basis
			/*if((objNewPatRegSupp_p.getPatNationalId()!=null && !objNewPatRegSupp_p.getPatNationalId().equals("")) || (objNewPatRegSupp_p.getPatCardNo()!=null && !objNewPatRegSupp_p.getPatCardNo().equals("")))
			{				
				objPatientIdVO.setPatIdNo(objNewPatRegSupp_p.getPatCardNo());
				objPatientIdVO.setPatDocType(objNewPatRegSupp_p.getPatDocType());
				strUniqueIdDuplicyFlag = NewRegistrationDATA.checkUniqueIdDuplicy(userVO, objPatientIdVO,objNewPatRegSupp_p.getPatNationalId());
					
				if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
					if(strUniqueIdDuplicyFlag.equals("1")){
						strErrMsg="Duplicate National Id/ Aadhar No";
						//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
						flagSaveMsgObjCreated = createSaveMsgObject2(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient, strTokenToSet);
						return;
					}else if(strUniqueIdDuplicyFlag.equals("2")){
						strErrMsg="Patient with this "+objNewPatRegSupp_p.getPatDocTypeName()+"("+objNewPatRegSupp_p.getPatCardNo()+ ") already registered.";
						//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
						flagSaveMsgObjCreated = createSaveMsgObject2(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient, strTokenToSet);
						return;
					}else if(strUniqueIdDuplicyFlag.equals("4")){//for hashed id
						strErrMsg="Duplicate National Id/ Aadhar No";
						flagSaveMsgObjCreated = createSaveMsgObject2(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient, strTokenToSet);
						return;
					}
				}
			
			}*/	
			
			//To Check the Duplicacy on the Secondary Alternate Id Basis
			/*if((objNewPatRegSupp_p.getPatIdNo()!=null && !objNewPatRegSupp_p.getPatIdNo().equals("") && objNewPatRegSupp_p.getPatCatDocIsAlternateId().equals("1")))
			{
				objPatientIdVO.setPatIdNo(objNewPatRegSupp_p.getPatIdNo());
				objPatientIdVO.setPatDocType(objNewPatRegSupp_p.getPatCatDocCode());
				strUniqueIdDuplicyFlag = NewRegistrationDATA.checkUniqueIdDuplicy(userVO, objPatientIdVO,objNewPatRegSupp_p.getPatNationalId());
					
				if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
					if(strUniqueIdDuplicyFlag.equals("1")){
						strErrMsg="Duplicate National Id/ Aadhar No";
						//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
						flagSaveMsgObjCreated = createSaveMsgObject2(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient, strTokenToSet);
						return;
					}else if(strUniqueIdDuplicyFlag.equals("2")){
						strErrMsg="Patient with this Id No ("+objNewPatRegSupp_p.getPatIdNo()+ ") already registered.";
						//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
						flagSaveMsgObjCreated = createSaveMsgObject2(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient, strTokenToSet);
						return;
					}
				}
			
			}*/
			//To Check the Duplicacy on the Reference Letter No for the CREDIT BASED BENEFICIARY WITH REFERENCE Categories
			if(objNewPatRegSupp_p.getPatPrimaryCatGrp()!=null && objNewPatRegSupp_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) )
			{
				HelperMethods.populate(objCreditAvailDtlVO, objNewPatRegSupp_p);
				objCreditAvailDtlVO.setTariffId(userVO.getTariffID());
				strUniqueIdDuplicyFlag = NewRegistrationDATA.checkBeneficiaryIdDuplicy(userVO, objCreditAvailDtlVO);

				if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
					if(strUniqueIdDuplicyFlag.equals("1")){
						strErrMsg="Patient with this Reference Letter No ("+objNewPatRegSupp_p.getCreditLetterRefNo()+ ") already registered.";
						//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
						flagSaveMsgObjCreated = createSaveMsgObject2(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient, strTokenToSet);
						return;
					}
				}
			}
			/*Start:Sheeldarshi
			Reason:prevent user from registering patients with same arogyashri number. 
			Date:03-July-15
			*/
			//To Check the Duplicacy on the arogyashri No for the CREDIT BASED BENEFICIARY WITHOUT REFERENCE Categories
			/*if(objNewPatRegSupp_p.getPatPrimaryCatGrp()!=null && objNewPatRegSupp_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE) )
			{
				HelperMethods.populate(objBenPatVO, objNewPatRegSupp_p);
				objCreditAvailDtlVO.setTariffId(userVO.getTariffID());
				strUniqueIdDuplicyFlag = NewRegistrationDATA.checkAarogyashriIdDuplicy(userVO, objBenPatVO);

				if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
					if(strUniqueIdDuplicyFlag.equals("1")){
						strErrMsg="Patient with this Aarogyashri No ("+objNewPatRegSupp_p.getAgsNo()+ ") already registered.";
						flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
						return;
					}
				}
			}*/
			//End
			//For validating the pat amount collected
			if(objNewPatRegSupp_p.getPatAmountCollected()==null || objNewPatRegSupp_p.getPatAmountCollected().equals(""))
			{
				strErrMsg= "Registration Fee cannot be empty.\nPlease Enter this.";
				//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
				flagSaveMsgObjCreated = createSaveMsgObject2(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient, strTokenToSet);
				//objNewPatRegSupp_p.setErrorMessage("Registration Fee cannot be empty.\nPlease relogin.");
				return ;
			}
			
			setSysdateAndDefaultCrNoFormat(objRequest_p);
			//isReferredDecider(objNewPatRegSupp_p,objRequest_p);
				
			if(objNewPatRegSupp_p.getPatRefGnctdHospitalDept().equals("0")){
				objNewPatRegSupp_p.setPatRefGnctdHospitalDept(objNewPatRegSupp_p.getPatRefHospitalDeptOther());
			}
			if(objNewPatRegSupp_p.getPatAddState()!=null && objNewPatRegSupp_p.getPatAddState().indexOf(',')!=-1){
				String[] strArrPatAddState= objNewPatRegSupp_p.getPatAddState().split("\\,"); 
				if(!strArrPatAddState[0].trim().equals(""))
					objNewPatRegSupp_p.setPatAddState(strArrPatAddState[0].trim());
				else
					objNewPatRegSupp_p.setPatAddState(strArrPatAddState[1].trim());
			}
			
			if(objNewPatRegSupp_p.getPatAddDistrict()!=null && objNewPatRegSupp_p.getPatAddDistrict().indexOf(',')!=-1){
				String[] strArrPatAddDistrict= objNewPatRegSupp_p.getPatAddDistrict().split("\\,"); 
				if(!strArrPatAddDistrict[0].trim().equals(""))
					objNewPatRegSupp_p.setPatAddDistrict(strArrPatAddDistrict[0].trim());
				else
					objNewPatRegSupp_p.setPatAddDistrict(strArrPatAddDistrict[1].trim());
			}
			
			setNotMandatoryDefaultCmbValue(objNewPatRegSupp_p);
			
			
			
			PatientVO patientVO = new PatientVO();
			//edited by sandip naik on 1 may 2017
			int location= getSpecialCharacterCount(objNewPatRegSupp_p.getPatAddCityLoc());
			//end by sandip
			
			HelperMethods.populate(patientVO, objNewPatRegSupp_p);
			
			//To set Credit Limit for Beneficiary Categories
			if(objNewPatRegSupp_p.getPatPrimaryCatGrp()!=null && objNewPatRegSupp_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) )
			{
				patientVO.setCreditLimit(objNewPatRegSupp_p.getCreditLimit());
			}
			if(objNewPatRegSupp_p.getPatPrimaryCatGrp()!=null && objNewPatRegSupp_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE) )
			{
				patientVO.setCreditLimit(objNewPatRegSupp_p.getAgsCreditLimit());
			}
			
			Map<String, RenewalConfigVO> mapOfRenewalVoOnPatCatGroupKey= (Map<String, RenewalConfigVO>)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO);
			objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get(objNewPatRegSupp_p.getPatPrimaryCatCode());
			if(objRenewalConfigVO==null)
				objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get("10");
			patientVO.setRenewalConfig(objRenewalConfigVO);
			patientVO.getRenewalConfig().setStrRenewalGroup(RegistrationConfig.RENEWAL_CONFIG_GROUP_OPD);
			
			patientVO.setPatIsDead("0");
			patientVO.setPatIsMerged("0");
			patientVO.setTariffId(userVO.getTariffID());
			patientVO.setPatIdNo(objNewPatRegSupp_p.getPatIdNo());
			
			HelperMethods.populate(patientVO.getPatAddress(), objNewPatRegSupp_p);
			patientVO.getPatAddress().setPatVerificationStatus("2");
			if(objNewPatRegSupp_p.getPatAddCountryCode()!=null && objNewPatRegSupp_p.getPatAddCountryCode().equals(RegistrationConfig.COUNTRY_CODE_INDIA))
				patientVO.setPatNationalityCode("1");
			else
				patientVO.setPatNationalityCode("0");
			
			if(objHospitalMstVO.getDistrictCode()!=null && objNewPatRegSupp_p.getPatAddDistrictCode()!=null && objHospitalMstVO.getDistrictCode().equals(objNewPatRegSupp_p.getPatAddDistrictCode()) )
				patientVO.getPatAddress().setPatIsLocal("1");
			else
				patientVO.getPatAddress().setPatIsLocal("0");
				
			if(objNewPatRegSupp_p.getPatRefGnctdHospitalDept().equals("0"))
				patientVO.setPatRefGnctdHospitalDept(objNewPatRegSupp_p.getPatRefHospitalDeptOther());
			if(objNewPatRegSupp_p.getIsReferred().equals(""))
				patientVO.setIsReferred("0");
			
			
			String[] deptToVisit = objNewPatRegSupp_p.getDepartmentsToVisitStamp();
		
			EpisodeVO[] arrEpisodeVO;		
				
			Collection colDept		=(Collection)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT);
			//Collection colPrimaryCat=(Collection)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY);
			//HospitalMstVO objHospitalMstVO= (HospitalMstVO)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_VO_RENEWEL_CONFIG);
			
			String deptName="";
			//for radiotherapy
			String referDeptname=objNewPatRegSupp_p.getPatRefGnctdHospitalDept();
			
			/**Added by Vasu dated on 23.06.2018 for Patient Image Upload*/
             //To Upload Image
			if(WebUTIL.getSession(objRequest_p).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT)!=null){
				//FormFile imageFile=(FormFile)WebUTIL.getSession(_req).getAttribute(RegistrationConfig.UPLOADED_FILE);
				//patientVO.setImageFile((byte[])WebUTIL.getSession(objRequest_p).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT));
				byte[] filContent=IOUtils.toByteArray((FileInputStream)WebUTIL.getSession(objRequest_p).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT));
				//patientVO.setImageFile((byte[])WebUTIL.getSession(objRequest_p).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT));
				patientVO.setImageFile(filContent);
			    patientVO.setImageFileName((String)WebUTIL.getSession(objRequest_p).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_NAME));
			    patientVO.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_TRUE);
			}
			/**End Vasu*/
			
			if(deptToVisit== null || deptToVisit.length==0)
			{
				arrEpisodeVO = new EpisodeVO[1];
				
				arrEpisodeVO[0]=new EpisodeVO();
				if(objNewPatRegSupp_p.getIsUnitWiseRegistration()!=null && objNewPatRegSupp_p.getIsUnitWiseRegistration().equals("1")){
				setDepartmentUnitDtl(colDept, patientVO, objNewPatRegSupp_p.getDepartmentUnitCode());
				}
				else
				{
					setDepartmentDtl(colDept, patientVO, objNewPatRegSupp_p.getDepartmentCode());
				}
				arrEpisodeVO[0].setDepartmentCode(objNewPatRegSupp_p.getDepartmentCode());
				arrEpisodeVO[0].setDepartmentUnitCode(patientVO.getDepartmentUnitCode());
				
				//deptName=getEntryLabel(colDept,objNewPatRegSupp_p.getDepartmentCode());
				//since department contains department name plus unit name
				//arrEpisodeVO[0].setDepartment(deptName);
				//arrEpisodeVO[0].setDepartment(deptName);
				arrEpisodeVO[0].setSnomdCIdVisitReason(objNewPatRegSupp_p.getSnomdCIdVisitReason());
				arrEpisodeVO[0].setSnomdPTVisitReason(objNewPatRegSupp_p.getSnomdPTVisitReason());
			
				
				arrEpisodeVO[0].setPaymentModeCode(objNewPatRegSupp_p.getPaymentModeCode().split("#")[0]);
				if(!objNewPatRegSupp_p.getPaymentModeCode().equals("1"))
					arrEpisodeVO[0].setPaymentModeCodeRefId(objNewPatRegSupp_p.getPaymentModeRefId());
			}
			else
			{
				arrEpisodeVO = new EpisodeVO[deptToVisit.length];		
				for(int i=0; i<arrEpisodeVO.length; i++)
				{
					arrEpisodeVO[i]=new EpisodeVO();
					arrEpisodeVO[i].setDepartmentCode(deptToVisit[i]);
					deptName=getEntryLabel(colDept,deptToVisit[i]);
					//since department contains department name plus unit name
					arrEpisodeVO[i].setDepartment(deptName);
					////for radiotherapy
					//arrEpisodeVO[0].setDepartment(referDeptname);
				}
			}
			patientVO.setSystemIPAddress(objRequest_p.getRemoteAddr());
			patientVO.setRegistrationType(RegistrationConfig.REGISTRATION_TYPE_GENERAL_OPD);	
			HttpSession sess=WebUTIL.getSession(objRequest_p);
			
			String sysdate=(String)sess.getAttribute(Config.SYSDATE);
			
			////for generating episode number
			patientVO.setSystemDate(sysdate);
			patientVO.setPatRegCatCode(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL);
			patientVO.setApprovedBy(objNewPatRegSupp_p.getPatRMOEmployee());
			/////setting vo in session for dupllicate registration or new department visit
			//WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, arrEpisodeVO);
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.REGISTRATIONDESK_PATIENT_VO, patientVO);
			//edited by sandip naik on 1 may 2017
			if(location==0)
			{
				
				strErrMsg="Unexpected action!Please try again!!Contact Administrator!!";
				//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
				flagSaveMsgObjCreated = createSaveMsgObject2(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient, strTokenToSet);
				return;
			}
			else
			{
				
			//end by sandip
			EpisodeVO episodeVO[]=NewRegistrationDATA.registerNewPatient(patientVO,arrEpisodeVO,userVO,objRequest_p);

			//calling barcode slip content creation method
			//WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.EPISODE_FOR_BARCODE, episodeVO);
			//WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATVO_FOR_BARCODE, patientVO);
			BarcodeGenerationUTIL.saveAndCreateBarcodeSlipPrintingDetails(objRequest_p, episodeVO, patientVO);
			/*  ## 		Modification Log							
	 		##		Modify Date				:26thFeb'15 
	 		##		Reason	(CR/PRS)		:SMS Integration
	 		##		Modify By				:Sheeldarshi */
			
			//SMS Code Commented by Singaravelan on 20-Mar-2015
			
			SMSConfig objSMSConfig=new SMSConfig();
			String   message  = "You are registered successfully in "+userVO.getStrHospitalMstVo().getHospitalName()+ " with Unique CRNO "+episodeVO[0].getPatCrNo();
			final String   smsmessage  = "You are registered successfully in "+userVO.getStrHospitalMstVo().getHospitalName()+ " with Unique CRNO "+episodeVO[0].getPatCrNo();
			final String patMobileNo=patientVO.getPatAddMobileNo();
			/**Added by Vasu on 25.June for SMS Configuration*/
			if(patientVO.getPatAddMobileNo()!=null && !patientVO.getPatAddMobileNo().equals("")){
			 //code from sending message through CDAC MUmbai SMS Gateway
			 //SMSHttpPostClient.sendSingleSMSThroughSMSGateway(objSMSConfig.sms_username, objSMSConfig.sms_password,objSMSConfig.sms_senderId,objSMSConfig.sms_url, patientVO.getPatAddMobileNo(),message);
				new Thread( new Runnable() {
			           public void run(){

			        	 SMSHttpPostClient.sendSMS (patMobileNo,smsmessage);

			          return; // to stop the thread
			                          }
			         }).start();
			}
			/**End Vasu*/
			//code from sending message through NIC SMS Gateway
			
			//SMSHttpsNICPostClient.sendTextSMSThroughNICSMSGateway(objSMSConfig.nic_sms_username, objSMSConfig.nic_sms_password,objSMSConfig.nic_sms_senderId,objSMSConfig.nic_sms_url, patientVO.getPatAddMobileNo(),message);

		
			//End:Sheeldarshi
			
			/*  ## 		Modification Log							
	 		##		Modify Date				:08-July 2016
	 		##		Reason	(CR/PRS)		:Mail Integration
	 		##		Modify By				:Garima Gotra */
			
			
			message=message + "\n\n\nDISCLAIMER: PLEASE DO NOT REPLY TO THIS MAIL. THIS IS AN AUTO GENERATED MAIL AND REPLIES TO THIS EMAIL ID ARE NOT ATTENDED TO. FOR ANY QUERIES OR CLARIFICATIONS PLEASE CALL US AT THE CONTACT NUMBERS PROVIDED AT OUR WEBSITE________ OR VISIT Hospital.";	
			
			if(patientVO.getPatAddEmailId()!=null && !patientVO.getPatAddEmailId().trim().equalsIgnoreCase(""))
			{
			//Code commented by Garima for Integration with NIC Mail service
			//Code for sending mail by CDAC SMTP Host
			 //Session _mailSession=emailPost.createMailSession(objEmailConfig.SMTP_USERNAME, objEmailConfig.SMTP_PASSWORD);
			//emailPost.sendSingleMailthroughGateway(_mailSession, objEmailConfig.SMTP_FROM_MAILI_ID, patientVO.getPatAddEmailId(), "Registration Confirmation In NIMS", message);
			
			//Code for sending mail by NIC SMS Gateway
			 //emailNICPost.sendEmail(patientVO.getPatAddEmailId(), "Registration Confirmation In NIMS", message);
			}
			
			if(objNewPatRegSupp_p.getIsActualDob().equals("1"))
				objNewPatRegSupp_p.setPatAge(patientVO.getPatAge());
			strIsSavedSuccussfulFlag="1";
			strSavedOrErrMsg="Patient Registered";
			if(patientVO.getPatCrNo()!=null && !patientVO.getPatCrNo().equals(""))
				strSavedOrErrMsg= strSavedOrErrMsg+" with CR No: "+patientVO.getPatCrNo();
			strIsFormFieldResetFlag="1";
			String tmpFileName=RegistrationConfig.CARD_NEW_REGISTRATION+userVO.getSeatId();
			
			//Calling QRCode Creation 
			//QRCodeTest objQRCode = new QRCodeTest();

			/*String str= "{\"crno\":\""+patientVO.getPatCrNo()+"\", \"uid\":\""+patientVO.getPatNationalId()+"\", \"uhid\":\""+patientVO.getPatSecUHID()+"\", \"mobileNo\":\""+patientVO.getPatAddMobileNo()+"\", "
					+ "\"patCategoryCode\":\""+patientVO.getPatPrimaryCatCode()+"\", \"name\":\""+patientVO.getPatFirstName()+" "+patientVO.getPatMiddleName()+" "+patientVO.getPatLastName()+"\", \"nameArray\":[\""+patientVO.getPatFirstName()+"\",\" "+patientVO.getPatMiddleName()+"\",\" "+patientVO.getPatLastName()+"\"], "
					+ "\"gender\":\""+patientVO.getPatGender().substring(0, 1)+"\", \"dob\":\""+patientVO.getPatDOB()+"\", \"ageInYears\":\""+patientVO.getPatAgeWithUnit()+"\", \"yob\":\""+patientVO.getPatDOB()+"\", \"fatherName\":\""+patientVO.getPatGuardianName()+"\", "
					+ "\"motherName\":\""+patientVO.getPatMotherName()+"\", \"spouseName\":\""+(patientVO.getPatHusbandName().equals("")?"N/A":patientVO.getPatHusbandName())+"\", \"house\":\""+patientVO.getPatAddHNo()+"\", \"loc\":\""+patientVO.getPatAddStreet()+" "+patientVO.getPatAddCityLoc()+" "+patientVO.getPatAddCity()+"\", "
					+ "\"vtc\":\""+patientVO.getPatAddCity()+"\", \"dist\":\""+patientVO.getPatAddDistrict()+"\", \"distCode\":\""+patientVO.getPatAddDistrictCode()+"\", "
					+ "\"state\":\""+patientVO.getPatAddState()+"\", \"stateCode\":\""+patientVO.getPatAddStateCode()+"\", \"pc\":\""+patientVO.getPatAddPIN()+"\"}";*/
			
			

//			String dataQR = "Name: "+patientVO.getPatFirstName()+" "+patientVO.getPatLastName()+", Age: "+patientVO.getPatAge()+", Gender: "+patientVO.getPatGender()+", CrNo: "+patientVO.getPatCrNo();
			
			/**to decrypt the aadhaar number*/
			if(patientVO.getPatNationalId()!=null && !patientVO.getPatNationalId().equals(""))
			{
				String strPatAadhaarNo =NewRegistrationDATA.getAadhaarDecrypted(patientVO.getPatNationalId(), patientVO.getPatCrNo());
				patientVO.setPatNationalId(strPatAadhaarNo.split("#@#")[0]);
				System.out.println(strPatAadhaarNo.split("#@#")[0]+"\n"+strPatAadhaarNo.split("#@#")[1]+"\n"+strPatAadhaarNo.split("#@#")[2]);
			}
			/***/
			
			//NewRegistrationDATA.CreateQREntry(patientVO, str, userVO.getHospitalCode());
			//objQRCode.createQRCode(str, objRequest_p);
			strPrintDivContent="";
			strPrintDivContent=NewRegistrationSlip.print(preapareSlip(episodeVO,patientVO,objNewPatRegSupp_p, objRequest_p), tmpFileName,objRequest_p,"NR");
			
			System.out.println("PrintSlip :"+strPrintDivContent);
			
			System.out.println("Amount Collected :"+objNewPatRegSupp_p.getPatAmountCollected());
			System.out.println("Actual Amount :"+objNewPatRegSupp_p.getPatActualAmount());
			//Bill Receipt Printing 
			if((objNewPatRegSupp_p.getPatAmountCollected()!=null)&&!(objNewPatRegSupp_p.getPatAmountCollected().equals("0"))&&!(objNewPatRegSupp_p.getPatAmountCollected().equalsIgnoreCase("0.00"))){
			
				strBillPrintDiv=NewRegistrationSlip.printBillReceipt(preapareSlip(episodeVO,patientVO,objNewPatRegSupp_p, objRequest_p), tmpFileName,objRequest_p,"NR");
				System.out.println("PrintBillSlip :"+strBillPrintDiv);
				WebUTIL.setAttributeInSession(objRequest_p,"billReceiptString", strBillPrintDiv);
				//strPrintDivContent+=strBillPrintDiv+"";
			}
			
			System.out.println("FullSlip :"+strPrintDivContent);
			//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strSavedOrErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"1",strIsDuplicatePatient);                             //opd card enable
			flagSaveMsgObjCreated = createSaveMsgObject2(responseDocument, strIsSavedSuccussfulFlag, strSavedOrErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient, strTokenToSet);           //opd card disable 

		}
		}
		catch(HisDuplicateRecordException e){
			strIsDuplicatePatient="1";
			strErrMsg = "Duplicate Record Exist";
		}
		catch(HisInvalidTokenNumberException e){
			strErrMsg = "INVALID TOKEN NUMBER";
		}
		catch(HisUpdateUnsuccesfullException e){
			strErrMsg = "Update Unsuccessful";
		}		
		catch(HisDataAccessException e){
			strErrMsg = "Registration Failed";
			objStatus.add(Status.ERROR_DA,"","Registration Failed");		
		}
		catch(HisApplicationExecutionException e){	
			strErrMsg = "Application Execution Error";
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisSQLManualException e){	
			strErrMsg = "Department-Unit Limit Exhausted";
			objStatus.add(Status.ERROR_AE,"","Registration Failed");
		}
		catch(HisException e){
			strErrMsg = "Error";
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e){
			e.printStackTrace();
			strErrMsg = "Error";
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			if(!flagSaveMsgObjCreated)
				//createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
				 createSaveMsgObject2(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient, strTokenToSet);
			
			java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
			try {
				trf.newTransformer().transform( new DOMSource(responseDocument),new StreamResult(baos));
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			outputString=baos.toString();
			writeResponse(objResponse_p, outputString);
		}	
	}
	
	
	
	
	
	private static String getEntryLabel(Collection _col,String _value)
	{
		//List ls= new ArrayList(col);
		Iterator it=_col.iterator();
		while (it.hasNext())
		{
			Entry objEntry=(Entry)it.next();
			if(objEntry.getValue().split("#")[0].equals(_value))
				return objEntry.getLabel();		
		}		
		//System.out.println("getEntryLabel:  "+)
		return _value;				
	}
	
	private static void setDepartmentDtl(Collection objCol_p, PatientVO objPatientVO_p,  String strDeptCode_p){
		
		Iterator itr=objCol_p.iterator();
		while (itr.hasNext())
		{
			Entry objEntry=(Entry)itr.next();
			String[] strDeptValArray = objEntry.getValue().split("#");
			if(strDeptValArray !=null && strDeptValArray[0].equals(strDeptCode_p)){
				objPatientVO_p.setDepartmentUnitCode(strDeptValArray[1]);
				objPatientVO_p.setRoomCode(strDeptValArray[2]);
				objPatientVO_p.setRoundRobinUnitFlag(strDeptValArray[4]);
				objPatientVO_p.setRoundRobinRoomFlag(strDeptValArray[3]);
				objPatientVO_p.setDepartment(objEntry.getLabel());
				break;
				//return objEntry.getLabel();		
			}
		}		
		//System.out.println("getEntryLabel:  "+)
	}
	
private static void setDepartmentUnitDtl(Collection objCol_p, PatientVO objPatientVO_p,  String strDeptUnitCode_p){
		
		Iterator itr=objCol_p.iterator();
		while (itr.hasNext())
		{
			Entry objEntry=(Entry)itr.next();
			String[] strDeptValArray = objEntry.getValue().split("#");
			if(strDeptValArray !=null && strDeptValArray[1].equals(strDeptUnitCode_p)){
				objPatientVO_p.setDepartmentUnitCode(strDeptValArray[1]);
				objPatientVO_p.setRoomCode(strDeptValArray[2]);
				objPatientVO_p.setRoundRobinUnitFlag(strDeptValArray[4]);
				objPatientVO_p.setRoundRobinRoomFlag(strDeptValArray[3]);
				objPatientVO_p.setDepartment(objEntry.getLabel());
				break;
				//return objEntry.getLabel();		
			}
		}		
		//System.out.println("getEntryLabel:  "+)
	}
	
	private static void setPrimaryCatDtl(Collection objCol_p, NewPatientRegistrationSUP objNewPatRegSupp_p,  String strPrimaryCatCode_p){
		
		/*
		patPrimaryCatCode	:- entryValueArray[0]
		patPrimaryCatName	:- entryValueArray[1]
		charges				:- entryValueArray[2]
		idReqFlag			:- entryValueArray[3]
		idSize				:- entryValueArray[4]
		idValidationType	:- entryValueArray[5]
		expiryFlag			:- entryValueArray[6]
		expiryDays			:- entryValueArray[7]
		expiryMonth			:- entryValueArray[8]
		patCatGroup			:- entryValueArray[9]*/
		
		Iterator itr=objCol_p.iterator();
		while (itr.hasNext())
		{
			Entry objEntry=(Entry)itr.next();
			String[] strPrimaryCatValArray = objEntry.getValue().split("#");
			if(strPrimaryCatValArray !=null && strPrimaryCatValArray[0].equals(strPrimaryCatCode_p)){
				objNewPatRegSupp_p.setPatPrimaryCat(strPrimaryCatValArray[1]);
				
				break;
				//return objEntry.getLabel();		
			}
		}		
		//System.out.println("getEntryLabel:  "+)
	}
	
	private static void setNotMandatoryDefaultCmbValue(NewPatientRegistrationSUP objNewPatRegSupp_p){
		
		objNewPatRegSupp_p.setPatMaritalStatusCode(objNewPatRegSupp_p.getPatMaritalStatusCode().equals("-1")?"":objNewPatRegSupp_p.getPatMaritalStatusCode());	
		objNewPatRegSupp_p.setPatCasteCode(objNewPatRegSupp_p.getPatCasteCode().equals("-1")?"":objNewPatRegSupp_p.getPatCasteCode());
		objNewPatRegSupp_p.setPatReligionCode(objNewPatRegSupp_p.getPatReligionCode().equals("-1")?"":objNewPatRegSupp_p.getPatReligionCode());
		objNewPatRegSupp_p.setPatOccupation(objNewPatRegSupp_p.getPatOccupation().equals("-1")?"":objNewPatRegSupp_p.getPatOccupation());
		objNewPatRegSupp_p.setPatIsUrban(objNewPatRegSupp_p.getPatIsUrban().equals("-1")?"":objNewPatRegSupp_p.getPatIsUrban());
		objNewPatRegSupp_p.setPatAddPhoneOwner(objNewPatRegSupp_p.getPatAddPhoneOwner().equals("-1")?"":objNewPatRegSupp_p.getPatAddPhoneOwner());
		objNewPatRegSupp_p.setPatRefGnctdHospitalCode(objNewPatRegSupp_p.getPatRefGnctdHospitalCode().equals("-1")?"":objNewPatRegSupp_p.getPatRefGnctdHospitalCode());
		objNewPatRegSupp_p.setPatRefGnctdHospitalDept(objNewPatRegSupp_p.getPatRefGnctdHospitalDept().equals("-1")?"":objNewPatRegSupp_p.getPatRefGnctdHospitalDept());
		objNewPatRegSupp_p.setPatAgeUnit(objNewPatRegSupp_p.getPatAgeUnit().equals("-1")?"":objNewPatRegSupp_p.getPatAgeUnit());
		objNewPatRegSupp_p.setPatDocType(objNewPatRegSupp_p.getPatDocType().equals("-1")?"":objNewPatRegSupp_p.getPatDocType());
		
		//System.out.println("getEntryLabel:  "+)
	}
	
	public static RegistrationSlip preapareSlip(EpisodeVO episodeVO[],PatientVO _patVO,NewPatientRegistrationSUP objNewPatRegSupp_p,HttpServletRequest _request) throws ParseException{		
		
		NewRegistrationDATA dataReg_op=new NewRegistrationDATA();
		UserVO _userVO=ControllerUTIL.getUserVO(objNewPatRegSupp_p.getObjRequest());
		RegistrationSlip regSlip=new RegistrationSlip();
		HelperMethods.setNullToEmpty(_patVO);
		//HelperMethods.setNullToEmpty(_patVO.getPatAddress());
		for (int i=0;i<episodeVO.length;i++)
		{
			HelperMethods.setNullToEmpty(episodeVO[i]);
			regSlip.setEpisodeVisitType(episodeVO[i].getEpisodeVisitType());
		}
		HelperMethods.populate(regSlip,_patVO);

		regSlip.setPatAge(_patVO.getPatAgeWithUnit());
		
		HospitalMstVO hospitalVO=getHospitalVO(_request);
		
		regSlip.setHospitalName(hospitalVO.getHospitalName());
		regSlip.setHospitalAddress1(hospitalVO.getAddress1());
		regSlip.setHospitalAddress2(hospitalVO.getAddress2());
		regSlip.setHospitalCity(hospitalVO.getCity());
		regSlip.setHospitalDistrict(hospitalVO.getDistrictName());
		regSlip.setHospitalState(hospitalVO.getStateName());
		regSlip.setHospitalpinCode(hospitalVO.getPinCode());
		regSlip.setHospitalPhone(hospitalVO.getPhone());
		regSlip.setHospitalFax(hospitalVO.getFax());
		regSlip.setHospitalEmail(hospitalVO.getEmail());
		
		/***/
		
		RegistrationConfigurationBean objRegConfigbean  = (RegistrationConfigurationBean)WebUTIL.getSession(_request).getAttribute(RegistrationConfig.Registration_Configuration_Bean);
		regSlip.setIsQRCodePrintFlag(objRegConfigbean.getIsQRCodePrint());
		
		/***/
		if(_patVO.getPatPrimaryCatGrp().equals("0"))
		{
			regSlip.setPatPrimaryCatGrp("Payment"); 
		   regSlip.setPatPrimaryCatGrpCode("0"); 
		}
		else if(_patVO.getPatPrimaryCatGrp().equals("1"))
		{
			regSlip.setPatPrimaryCatGrp("Staff"); 
		regSlip.setPatPrimaryCatGrpCode("1");
		}
		else if(_patVO.getPatPrimaryCatGrp().equals("2"))
		{
		regSlip.setPatPrimaryCatGrp("Free"); 
		regSlip.setPatPrimaryCatGrpCode("2");
		}
		else if (_patVO.getPatPrimaryCatGrp().equals("3"))
		{
		regSlip.setPatPrimaryCatGrp("Credit/Beneficery"); 
		regSlip.setPatPrimaryCatGrpCode("3");
		}
		else if (_patVO.getPatPrimaryCatGrp().equals("4"))
		{
		regSlip.setPatPrimaryCatGrp("Credit/Beneficery Without Reference"); 
		regSlip.setPatPrimaryCatGrpCode("4");
		}

			
		String primaryCatName=objNewPatRegSupp_p.getPatPrimaryCat();
		regSlip.setPatPrimaryCat(primaryCatName);				
		regSlip.setPatGender(objNewPatRegSupp_p.getPatGender());
		//System.out.println("-------------"+objNewPatRegSupp_p.getPatOccupation()+"--------------------");
		regSlip.setPatOccupation("");
		//System.out.println("-------------"+regSlip.getPatOccupation()+"--------------------");
		//System.out.println("-------------"+objNewPatRegSupp_p.getPatCasteCode()+"--------------------");
		regSlip.setPatCasteCode(objNewPatRegSupp_p.getPatCaste());
		//System.out.println("-------------"+regSlip.getPatOccupation()+"--------------------");
	
		String fileNoView[] =new String[episodeVO.length];
		String fileNo[] =new String[episodeVO.length];
		String episodeDate[] =new String[episodeVO.length];
		String roomName[] =new String[episodeVO.length];
		String depName[]=new String[episodeVO.length];
		String depUnit[]=new String[episodeVO.length];
		String queueNo[]=new String[episodeVO.length];
		String workingDays[]=new String[episodeVO.length];
		String timing[]=new String[episodeVO.length];
		// disclaimers
		String disclaimer1[]=new String[episodeVO.length];
		String disclaimer2[]=new String[episodeVO.length];
		String disclaimer3[]=new String[episodeVO.length];
		String isHeader[]=new String[episodeVO.length];
		String alignment[]=new String[episodeVO.length];
		String unitConsultant[]=new String[episodeVO.length];
		String filePrintFlag[]=new String[episodeVO.length];
		String cardPrintFlag[]=new String[episodeVO.length];
		String expDate[]=new String[episodeVO.length];
		
		String billNo[]=new String[episodeVO.length];
				
		String patMiddleName=regSlip.getPatMiddleName();
		
		//Commented by Mukund on 10.04.2017 For printing whole middle name
				/*if(!(patMiddleName==null || patMiddleName.equals("")))
				{
					patMiddleName=patMiddleName.substring(0,1).toUpperCase();
					regSlip.setPatMiddleName(patMiddleName);
				}*/
		
		////Address For card
		String cityLocation="";
		String district="";
		String state="";
	
		String country=objNewPatRegSupp_p.getPatAddCountry();
		
		cityLocation=objNewPatRegSupp_p.getPatAddCity();
		if(_patVO.getPatAddDistrictCode()==null || _patVO.getPatAddDistrictCode().equals(""))
			district=_patVO.getPatAddDistrict();
		else
			district=objNewPatRegSupp_p.getPatAddDistrict();
		
			state=objNewPatRegSupp_p.getPatAddState();
		
		//inorder to prevent null showing for the location textbox on the print slip
		cityLocation=cityLocation==null?" ":cityLocation;
		
		
		String address=_patVO.getPatAddHNo();
		address=address+" "+_patVO.getPatAddStreet();
		
		address=address+" "+_patVO.getPatAddCity();
		if(district.equals("-1"))
			district="";
			String address2=district;
	
			
		if(!address2.equals(""))
			address2=address2+","+state;
		else
			address2=state;
		
			address2=address2+","+country;
			if(_patVO.getPatAddPIN() != null && !_patVO.getPatAddPIN().equals(""))
			address2=address2+"-"+_patVO.getPatAddPIN();
			if(_patVO.getPatAddMobileNo() != null && !_patVO.getPatAddMobileNo().equals(""))
				address2=address2+" Mobile: "+_patVO.getPatAddMobileNo();
			//String patOccupation="";
			//patOccupation=_patVO.getPatOccupation();
		
		///////////////////////////////////////////////////////////////////////////////////////
		for(int i=0;i<episodeVO.length;i++){	
			if(episodeVO[i].getFileNo()==null)
			{
				episodeVO[i].setFileNo("");
				episodeVO[i].setFileNoView("");
			}
			
			
			fileNoView[i]=episodeVO[i].getFileNoView();
			fileNo[i]=episodeVO[i].getFileNo();
			roomName[i]=episodeVO[i].getRoom();
			
			depName[i]=episodeVO[i].getDepartment();	
			if(objNewPatRegSupp_p.getIsUnitWiseRegistration()!=null && objNewPatRegSupp_p.getIsUnitWiseRegistration().equals("1")){
				depUnit[i]=episodeVO[i].getDepartmentUnit();
			}else{
				depUnit[i]=episodeVO[i].getDepartmentUnit();
			}
			queueNo[i]=episodeVO[i].getQueNo();
			expDate[i]=episodeVO[i].getExpiryDate();
			unitConsultant[i]=episodeVO[i].getUnitConsultant();
			//added to set the card and file print format
			filePrintFlag[i]=episodeVO[i].getFilePrintSetting();
			cardPrintFlag[i]=episodeVO[i].getCardPrintSetting();
			billNo[i]=episodeVO[i].getBillNo();
			
			// disclaimers
			System.out.println("----------"+episodeVO[i].getDisclaimer()+"-------------");
			if(episodeVO[i].getDisclaimer()!="" && episodeVO[i].getDisclaimer()!=null){
			disclaimer1[i]=episodeVO[i].getDisclaimer().split("@")[0];
			disclaimer2[i]=episodeVO[i].getDisclaimer().split("@")[1];
			disclaimer3[i]=episodeVO[i].getDisclaimer().split("@")[2];
			isHeader[i]=episodeVO[i].getDisclaimer().split("@")[3];
			alignment[i]=episodeVO[i].getDisclaimer().split("@")[4];
			}
			else{
				disclaimer1[i]="";
				disclaimer2[i]="";
				disclaimer3[i]="";
				isHeader[i]="";
				alignment[i]="";
			}
			
			String[] temp=episodeVO[i].getUnitWorkingDays().split("#");
			if(temp.length==0)
				temp=new String[]{"",""};
			System.out.println("temp[0]"+temp[0]);
			//System.out.println("temp[1]"+temp[1]);
			workingDays[i]=temp[0];
			
			/*timing[i]=temp[1];
			workingDays[i]=episodeVO[i].getUnitWorkingDays();
			episodeDate[i]=episodeVO[i].getEntryDate();
			Calendar cal = Calendar.getInstance(expDate[i]); 
			String date=sdf.format(cal.getTime());*/
			
			episodeDate[i]=(String)_request.getSession().getAttribute(Config.SYSDATE);
			
		    SimpleDateFormat _sdf1 =new SimpleDateFormat("dd/MM/yyyy HH:mm");
		    SimpleDateFormat _sdf2 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
		    SimpleDateFormat _sdf3 =new SimpleDateFormat("dd-MMM-yyyy");
			
		    if(episodeDate[i]!=null&&episodeDate[i]!="")episodeDate[i]=_sdf2.format(_sdf1.parse(episodeDate[i]));
		    if(expDate[i]!=null&&expDate[i]!="")expDate[i]=_sdf2.format(_sdf3.parse(expDate[i]));
		}
		regSlip.setHostName(_request.getRemoteAddr());
		regSlip.setPatCrNo(episodeVO[0].getPatCrNo());
		regSlip.setRoom(roomName);
		regSlip.setDepartmentToBeVisited(depName);
		/*for(int k=0;k<regSlip.getDepartmentToBeVisited().length;k++)
		{
			System.out.println("department"+regSlip.getDepartmentToBeVisited()[k]);
		}*/
		regSlip.setFileNoView(fileNoView);
		regSlip.setFileNo(fileNo);
		regSlip.setExpDate(expDate);
		regSlip.setDepartmentUnit(depUnit);		
		regSlip.setUnitConsultant(unitConsultant);		
		regSlip.setQueNo(queueNo);
		regSlip.setEpisodeDate(episodeDate);	
		regSlip.setWorkingDays(workingDays);
		regSlip.setTiming(timing);
		regSlip.setAddress1(address);
		
		regSlip.setAddress2(address2);
		regSlip.setDisclaimer1(disclaimer1);
		regSlip.setDisclaimer2(disclaimer2);
		regSlip.setDisclaimer3(disclaimer3);
		regSlip.setIsHeader(isHeader);
		regSlip.setAlignment(alignment);
		regSlip.setUnitConsultant(unitConsultant);
		regSlip.setFilePrintFlag(filePrintFlag);
		regSlip.setCardPrintFlag(cardPrintFlag);
		//regSlip.setPatOccupation(patOccupation);
		regSlip.setSheetNo("1");
		//For Bill Receipt 
		@SuppressWarnings("static-access")
		String counterName=dataReg_op.getCounterName(_userVO,_patVO.getSystemIPAddress());
		regSlip.setBillNo(billNo);
		regSlip.setCounterName(counterName);	
		regSlip.setDependentName(objNewPatRegSupp_p.getPatMemRelationCode());
		regSlip.setPatMemRelationName(objNewPatRegSupp_p.getPatMemRelationName());
		regSlip.setHiddenPatIdNo(objNewPatRegSupp_p.getHiddenPatIdNo());
		regSlip.setPatMemDeptName(objNewPatRegSupp_p.getPatMemDeptName());
		regSlip.setLoginUserName(_userVO.getUsrName());//By Mukund
		return regSlip;		
	}
	public static boolean validateSavePatDtl(NewPatientRegistrationSUP objPatSup_p,HttpServletResponse objResponse_p) {
		List lstErrMsg=new ArrayList();
		boolean flagValidationError=false;
		System.out.println("NewRegistrationUTIL :: validateSavePatDtl()");
		System.out.println("objPatSup_p.getPatFirstName() :"+objPatSup_p.getPatFirstName());
		
		if(objPatSup_p.getIsUnitWiseRegistration()!=null && objPatSup_p.getIsUnitWiseRegistration().equals("1")){
			if(objPatSup_p.getDepartmentUnitCode().equals("-1") ||  objPatSup_p.getDepartmentUnitCode() == null ||objPatSup_p.getDepartmentUnitCode()=="" ){
				lstErrMsg.add("Visiting Unit is Required");
			}
			else if(!objPatSup_p.getDepartmentUnitCode().matches("^[0-9]{5}$")){
				lstErrMsg.add("Visiting Unit is Required. It Accepts Numeric Values Between 10001 To 99999 Only.");
			}
		}
		else{
			if(objPatSup_p.getDepartmentCode().equals("-1") ||  objPatSup_p.getDepartmentCode() == null ||objPatSup_p.getDepartmentCode()=="" ){
				lstErrMsg.add("Visiting Department is Required");
			}
			else if(!objPatSup_p.getDepartmentCode().matches("^([0-9]{3})$")){
				lstErrMsg.add("Visiting Department is Required. It Accepts Numeric Values Between 101 To 999 Only.");
			}			
		}	
		if(objPatSup_p.getPatPrimaryCatCode().equals("-1") || objPatSup_p.getPatPrimaryCatCode() == null ||  objPatSup_p.getPatPrimaryCatCode()=="" ){
			lstErrMsg.add("Patient Category is Required");
		}
		else if(!objPatSup_p.getPatPrimaryCatCode().matches("^([1-9][0-9])$"))
		{
			lstErrMsg.add("Patient Category Is Required. It Accepts Numeric Values Between 11 To 99 Only.");
		}
		if(objPatSup_p.getPatFirstName()=="" || objPatSup_p.getPatFirstName()==null){
			lstErrMsg.add("First Name is Required");
		}
		else if(!objPatSup_p.getPatFirstName().matches("^[a-zA-Z\\s]*$"))
		{
			lstErrMsg.add("First Name Is Required. It Accept Alphabets Only.");
		}
		if(objPatSup_p.getIsActualDob().equals("0"))
		{
			if(objPatSup_p.getPatAge()=="" || objPatSup_p.getPatAge()==null){
				lstErrMsg.add("Age is Required");
			}
			/*else if(!objPatSup_p.getPatAge().matches("^([1-9]|[1-9][0-9]|1[0-2][0-5])$"))
			{
				lstErrMsg.add("Age Is Required. It Accept Numeric Value Between 1 to 125 Only.");
			}*/
		}
		else if(objPatSup_p.getIsActualDob().equals("1"))
		{
			if(objPatSup_p.getPatDOB()=="" || objPatSup_p.getPatAge()==null)
			{
				lstErrMsg.add("DOB is Required");
			}
			else if(!(objPatSup_p.getPatDOB()=="" || objPatSup_p.getPatAge()==null))
			{
				monthRenewalModeValidation(objPatSup_p.getPatDOB(),lstErrMsg);
			}
		}
		if(objPatSup_p.getPatGenderCode().equals("-1") ||objPatSup_p.getPatGenderCode()==null ||  objPatSup_p.getPatGenderCode()=="" ){
			lstErrMsg.add("Gender is Required");
		}
		else if(!objPatSup_p.getPatGenderCode().matches("^(F|I|M|N|T|O)$"))
		{
			lstErrMsg.add("Gender Is Required. It Accept Valid Gender Only.");
		}
		if((objPatSup_p.getPatGuardianName()=="" || objPatSup_p.getPatGuardianName()==null) && (objPatSup_p.getPatHusbandName()=="" || objPatSup_p.getPatHusbandName()==null)){
			lstErrMsg.add("Father Name Or Spouse Name is Required");
		}
		if(!(objPatSup_p.getPatGuardianName()=="" || objPatSup_p.getPatGuardianName()==null))
		{
			if(!(objPatSup_p.getPatGuardianName().matches("^[a-zA-Z\\s]*$")))
			{
				lstErrMsg.add("Fathers Name Is Required. It Accept Alphabets with Spaces Only.");
			}
		}
		if(!(objPatSup_p.getPatHusbandName()=="" || objPatSup_p.getPatHusbandName()==null))
		{
			if(!(objPatSup_p.getPatHusbandName().matches("^[a-zA-Z\\s]*$")))
			{
				lstErrMsg.add("Spouse Name Is Required. It Accept Alphabets with Spaces Only.");
			}
		}
		if(objPatSup_p.getPatAmountCollected()=="" || objPatSup_p.getPatAmountCollected()==null){
			lstErrMsg.add("Registration Fee is Required");
		}
		if(objPatSup_p.getPatAddCountryCode().equals("-1")  || objPatSup_p.getPatAddCountryCode()==null || objPatSup_p.getPatAddCountryCode()==""){
			lstErrMsg.add("Country Name is Required");
		}
		else if(!objPatSup_p.getPatAddCountryCode().matches("^[A-Z][A-Z][A-Z]$"))
		{
			lstErrMsg.add("Country Name is Required. It Accepts Valid Country Name Only.");
		}
		if(objPatSup_p.getPatAddCountryCode().equals("IND") || objPatSup_p.getPatAddCountryCode().equals("-1") || objPatSup_p.getPatAddCountryCode()==null)
		{
			if(objPatSup_p.getPatAddStateCode().equals("-1") || objPatSup_p.getPatAddStateCode()==null || objPatSup_p.getPatAddStateCode()=="" ){
			lstErrMsg.add("State Name is Required");
			}
			else if(!objPatSup_p.getPatAddStateCode().matches("^[1-9][0-9]{0,1}$"))
			{
				lstErrMsg.add("State Name is Required. It Accepts Valid State Name Only.");
			}
			if(objPatSup_p.getPatAddDistrictCode().equals("-1")  || objPatSup_p.getPatAddDistrictCode()==null || objPatSup_p.getPatAddDistrictCode()==""){
				lstErrMsg.add("District Name is Required");
			}
			else if(!objPatSup_p.getPatAddDistrictCode().matches("^[0-9]{2,3}$") && !objPatSup_p.getPatAddDistrictCode().matches("^[1-9]{1}$"))//handling single digit district code
			{
				lstErrMsg.add("District Name is Required. It Accept Valid District's Name Only.");
			}
		}
		else
		{
			String[] state = objPatSup_p.getPatAddState().split(",");
			String[] district = objPatSup_p.getPatAddDistrict().split(",");
			if(state[1].equals(null) || state[1]=="" || state[1].equals(" "))
			{
				lstErrMsg.add("State Name is Required");
			}
			if(district[1].equals(null) || district[1]=="" || district[1].equals(" "))
			{
				lstErrMsg.add("District Name is Required");
			}
		}
		/*if(objPatSup_p.getPatAddCountryCode().equals("-1") || objPatSup_p.getPatAddCountryCode()==""){
			lstErrMsg.add("Country Name is Required");
		}
		else if(!objPatSup_p.getPatAddCountryCode().matches("^[A-Z][A-Z][A-Z]$"))
		{
			lstErrMsg.add("Country Name is Required. It Accepts Valid Country Name Only.");
		}
		if(objPatSup_p.getPatAddStateCode().equals("-1") || objPatSup_p.getPatAddStateCode()==""){
			lstErrMsg.add("State Name is Required");
		}
		else if(!objPatSup_p.getPatAddStateCode().matches("^[1-9][0-9]{0,1}$"))
		{
			lstErrMsg.add("State Name is Required. It Accepts Valid State Name Only.");
		}
		if(objPatSup_p.getPatAddDistrictCode().equals("-1") || objPatSup_p.getPatAddDistrictCode()=="" || objPatSup_p.getPatAddDistrictCode()==null){
			lstErrMsg.add("District Name is Required");
		}
		else if(!objPatSup_p.getPatAddDistrictCode().matches("^[1-9][0-9][1-9]$"))
		{
			lstErrMsg.add("District Name is Required. It Accept Valid District's Name Only.");
		}*/
		if(objPatSup_p.getIsReferred()!=""){
			if(objPatSup_p.getReferringInstType().equals("G")){
				if(objPatSup_p.getPatRefGnctdHospitalCode()=="" || (objPatSup_p.getPatRefGnctdHospitalCode().equals("-1")))
					lstErrMsg.add("Institute Name is Required");
			}
			else if(objPatSup_p.getReferringInstType().equals("O")){
				if(objPatSup_p.getPatRefHospitalName()=="" ){
					lstErrMsg.add("Other Name For Institute is Required");
				}
				else if(!objPatSup_p.getPatRefHospitalName().matches("^[a-zA-Z\\s]*$"))
				{
					lstErrMsg.add("Other Name For Institute Is Required. Is Accepts Alphabets With Spaces Only.");
				}
			}
			if(objPatSup_p.getPatRefDoctor()=="" ){
				lstErrMsg.add("Doctor Name is Required");
			}
			else if(!objPatSup_p.getPatRefDoctor().matches("^[a-zA-Z(). ]*"))
			{
				lstErrMsg.add("Docter Name Is Required. It Accept Alphabets and Space with (). Only.");
			}
		}
		if(lstErrMsg!=null && lstErrMsg.size()>0){
			createValidateSaveMsgObject(lstErrMsg,objResponse_p);
			flagValidationError=true;
		}
		return flagValidationError;
	}
	public static void createValidateSaveMsgObject(List lstErrMsg,HttpServletResponse objResponse_p) {
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		Element msgElement=null;
		String strIsSavedSuccussfulFlag="0", strSavedOrErrMsg="",strIsFormFieldResetFlag="0", strPrintDivContent="", strErrMsg="",strIsDuplicatePatient="";
		String outputString="";
		try{
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			/*msgElement=responseDocument.createElement("msgs");
			rootElement.appendChild(msgElement);
			msgElement.setAttribute("msg", strErrMsg);*/
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			strErrMsg="<ul>";
			for(int i=0; i<lstErrMsg.size(); i++)
				strErrMsg+="<li>"+lstErrMsg.get(i).toString()+"</li>";
			strErrMsg+="</ul>";
			createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
			
			java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
			try {
				trf.newTransformer().transform( new DOMSource(responseDocument),new StreamResult(baos));
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			outputString=baos.toString();
			writeResponse(objResponse_p, outputString);
		}
	}
	
	public static void monthRenewalModeValidation(String dobStr,List lst)
	{
		/*String[] part = dob.split("-");
		String dobmonth = part[1];
		int dobdate;
		if(dobmonth.equals("Jan") || dobmonth.equals("Mar") || dobmonth.equals("May") || dobmonth.equals("Jul") || dobmonth.equals("Aug") || dobmonth.equals("Oct") || dobmonth.equals("Dec"))
		{
			dobdate = Integer.parseInt(part[0]);
			if(dobdate > 31)
			{
				lst.add("DOB is Required. Not A Valid Format Of Date");
			}
		}
		else if(dobmonth.equals("Apr") || dobmonth.equals("Jun") || dobmonth.equals("Sep") || dobmonth.equals("Nov"))
		{
			dobdate = Integer.parseInt(part[0]);
			if(dobdate > 30)
			{
				lst.add("DOB is Required. Not A Valid Format Of Date");
			}
		}
		else if(dobmonth.equals("Feb"))
		{
			dobdate = Integer.parseInt(part[0]);
			if(dobdate > 28)
			{
				lst.add("DOB is Required. Not A Valid Format Of Date");
			}
		}
		else
		{
			lst.add("DOB is Required. Not A Valid Format Of Date");
		}*/
		String currentDateStr;
		Date currentDate,dateDOB;
		try{
				SimpleDateFormat _sdf =new SimpleDateFormat("dd-MMM-yyyy");
				Calendar cldr = Calendar.getInstance();
		        
				currentDateStr =_sdf.format(cldr.getTime());
				currentDate=_sdf.parse(currentDateStr);//currentDateStr = today's date in dd-MMM-yyyy format
				
				dateDOB = _sdf.parse(dobStr);//dtDOB in dd-MMM-yyyy format
				
				//compare two dates
				int judge = dateDOB.compareTo(currentDate);
				if(judge<=0)// dateDOB is earlier than currentDate
				{
					//Do nothing
				}
				else if(judge>0)//dateDOB is later than currentDate
				{
					lst.add("DOB is Not A Valid Date");
				}
		}catch(Exception e)
		{
			lst.add("Error!!, Contact To System Administrator");
			System.out.println("DOB is Not A Valid Date");
			e.printStackTrace();
		}
	}
	
	/* #Start					:Sheeldarshi 
	#Modify Date(CR/PRS)	:22ndMay'15 
	#Reason					:The Total amount collected by concerned operator should be displayed on registration & Billing Processes
	 */
	public static void getCashCollectionDetail(HttpServletRequest request,HttpServletResponse response, NewPatientRegistrationSUP objPatSup_p) {
		// TODO Auto-generated method stub
		//NewPatientRegistrationSUP voObj = null;
	
		RegEssentialBO  essentialBO=new RegEssentialBO();
		BillConfigUtil bcu = null;
		try {
	
			PatientVO patientVO = new PatientVO();	
			HttpSession session=request.getSession();
			UserVO userVO=getUserVO(request);
			String strHospitalCode = session.getAttribute("HOSPITAL_CODE").toString();
			bcu = new BillConfigUtil(strHospitalCode);
			String strSeatId = session.getAttribute("SEATID").toString();
			
			
					
			patientVO.setStrValue1(strHospitalCode);
			patientVO.setStrValue2(strSeatId);
		
			
			essentialBO.getCashCollectionDetail(patientVO,userVO);
			
			if (patientVO.getStrMsgType().equals("0"))
			{
				objPatSup_p.setStrResultWs(patientVO.getGblWs1());

						
				getCashCollectionDetailView(patientVO,request);
			      
				

			} 
			else 
			{

				throw new Exception(patientVO.getStrMsgString());

			}
			
			
		}
		catch (Exception e) 
		{

			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try 
			{
				HisException eObj = new HisException("Global Billing File", "hisglobal.BillingDATA.getCashCollectionDetail()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			}
			catch (IOException e1) 
			{
				//System.out.println("Inside IInd Else::::"+e1.getMessage());
			}

		} 
		finally {

				essentialBO = null;
				//patientVO = null;
				bcu = null;
		}
	}
	public static void getCashCollectionDetailView(PatientVO voObj, HttpServletRequest _request) {
		WebRowSet ws = voObj.getGblWs1();
		
		try {

			
			if (ws != null) {
				
				if(ws.size() != 0){
					
					ws.next();
							
				
					//Double netAmount=   ws.getDouble(3)+ws.getDouble(4);
				_request.setAttribute("userName",ws.getString(1));
				_request.setAttribute("totalBill", ws.getInt(2));
				_request.setAttribute("recievedAmount", HisUtil.getAmountWithDecimal(ws.getDouble(3),2));
				_request.setAttribute("refundAmount", HisUtil.getAmountWithDecimal(Math.abs(ws.getDouble(4)),2));
				_request.setAttribute("netAmount", HisUtil.getAmountWithDecimal(ws.getDouble(5),2));
			
				System.out.println("recievedAmount"+ws.getDouble(3));
				
			} else {

				throw new Exception("Cash Collection WebRowSet is Null");

			}
			}
			} catch (Exception e) {
			
		 
			
			new HisException("Cash Collection Detail",
					"billing.HLPBilling.getCashCollectionDetailView()-->", e
							.getMessage());
		}

	}
	//edited by sandip naik on 1 may 2017
	
	public static int getSpecialCharacterCount(String s) {
		System.out.println("NewRegUTIL :: getSpecialCharacterCount");
	     if (s == null || s.trim().isEmpty()) {
	         System.out.println("Incorrect format of string");
	         return 1;
	     }
	     Pattern p = Pattern.compile("^[a-zA-Z0-9]+(\\s+[a-zA-Z0-9]+)*$");//("[^A-Za-z0-9]\\s");
	     Matcher m = p.matcher(s);
	    // boolean b = m.matches();
	     boolean b = m.find();
	     if (b == true)
	     {
	    	 System.out.println("There is no special char.");
		     return 1;
	     }
	     else
	     {
	    	 System.out.println("There is a special character in my string ");
	    	 return 0;
	     }
	 }
	// end by sandip
	//edited by sandip naik on 2 may 2017
	
		public static int getAlphaCount(String s) {
			System.out.println("NewRegUTIL :: getAlphaCount");
		     if (s == null || s.trim().isEmpty()) {
		         System.out.println("Incorrect format of string");
		         return 1;
		     }
		     Pattern p = Pattern.compile("[^A-Za-z]");
		     Matcher m = p.matcher(s);
		    // boolean b = m.matches();
		     boolean b = m.find();
		     if (b == true)
		     {
		         System.out.println("There is no special char.");
		         return 1;        

		     }
		     else
		     {
		    	 System.out.println("There is a special character in my string ");
		    	 return 0;
		     }
		 }
		// end by sandip


	
}//end of class