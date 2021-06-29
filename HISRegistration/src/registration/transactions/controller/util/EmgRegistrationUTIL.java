package registration.transactions.controller.util;

import hisglobal.config.HISConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRegistrationTimingExpiredException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
import hisglobal.utility.EmailSender.NICEmailClient;
import hisglobal.utility.SMSSender.SMSHttpPostClient;
import hisglobal.utility.SMSSender.SMSHttpsNICPostClient;
import hisglobal.utility.EmailSender.config.EmailConfig;
import hisglobal.utility.SMSSender.config.SMSConfig;
import hisglobal.utility.filetransfer.FTPFileTransfer;
import hisglobal.utility.filetransfer.config.FileTransferConfig;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.WebRowSet;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.util.TokenHelper;
import org.codehaus.jettison.json.JSONArray;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import registration.QRCodeTest;
import registration.bo.RegEssentialBO;
import registration.config.RegistrationConfig;
import registration.config.RegistrationConfigurationBean;
import registration.config.Exceptions.HisInvalidTokenNumberException;
import registration.config.slip.NewRegistrationSlip;
import registration.config.slip.RegistrationSlip;
import registration.transactions.controller.actionsupport.EmgPatientRegistrationSUP;
import registration.transactions.controller.actionsupport.NewPatientRegistrationSUP;
import registration.transactions.controller.data.EmgRegistrationDATA;
import registration.transactions.controller.data.NewRegistrationDATA;
import vo.registration.CreditAvailDetailVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientBroughtByDtlVO;
import vo.registration.PatientIdVO;
import vo.registration.PatientVO;
import vo.registration.RegistrationConfigVO;
import vo.registration.RenewalConfigVO;
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








import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import java.awt.Image;
import java.awt.image.BufferedImage;
public class EmgRegistrationUTIL extends ControllerUTIL
{
/**
 * sets all new registration essentials
 * @param _request -HttpServletRequest
 */
	public static void setAllEmgRegistrationEssentials(EmgPatientRegistrationSUP objEmgPatientRegistrationSUP,HttpServletRequest objRequest,
			HttpServletResponse objResponse, Map<Object, Object> mapSession){		
		Status objStatus=new Status();
		Map mp=new HashMap();
		try
		{
			UserVO userVO=getUserVO(objRequest);
			HospitalMstVO objHospitalMstVO=getHospitalVO(objRequest);
			 /*  ## Modification Log							
				 ##		Modify Date				:12thDec'14 
				 ##		Reason	(CR/PRS)		:Bug fix 7720
				 ##		Modify By				:Sheeldarshi 

			userVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);*/
			userVO.setModuleId(RegistrationConfig.MODULE_ID_EMERGENCY);
			//End:Sheeldarshi
			
			userVO.setTariffID(RegistrationConfig.EMERGENCY_REGISTRATION_TARIFF_ID);
			HttpSession session =objRequest.getSession();
			
			//To Check the Cash Collection Back Date Day End Flag Check, Added by Singaravelan on 03-Jun-2015
			System.out.println("Back Date Day End Check Flag :::value :::"+userVO.getCheckBackDateDayEndFlag());			
			if(userVO.getCheckBackDateDayEndFlag().equals("1"))
				throw new HisRecordNotFoundException("Please do your day end first !");
			if(userVO.getCheckBackDateDayEndFlag().equals("2"))
				throw new HisRecordNotFoundException("Please deposit the money first at cash collection counter !");	
			
			mp=EmgRegistrationDATA.getEmgRegistrationFormInitialEssentials(userVO);
			
			WebUTIL.setMapInSession(mp,objRequest,"EmgRegistrationAction");
		}
		catch(HisRecordNotFoundException e){
			objEmgPatientRegistrationSUP.addActionError(e.getMessage());
		}		
		catch(HisRegistrationTimingExpiredException e){
			e.printStackTrace();
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			e.printStackTrace();
		}
		catch(HisException e){
			e.printStackTrace();
		}		
		finally
		{
			WebUTIL.setStatus(objRequest,objStatus);
		}	
	}
	
	public static void populateRegistrationFormEssentials_AJAX(EmgPatientRegistrationSUP objEmgPatientRegistrationSUP,HttpServletRequest objRequest,
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
			HospitalMstVO objHospitalMstVO=getHospitalVO(objRequest);
			userVO.setModuleId(RegistrationConfig.MODULE_ID_EMERGENCY);
			userVO.setTariffID(RegistrationConfig.EMERGENCY_REGISTRATION_TARIFF_ID);
			
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			rootElement.setAttribute(RegistrationConfig.LOCAL_LANGUAGE, objHospitalMstVO.getLocalLangCode());
			
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
			defaultElement.setAttribute("defaultpatAddStateCode", objHospitalMstVO.getState());
			defaultElement.setAttribute("defaultpatAddDistrictCode", objHospitalMstVO.getDistrictCode());
			defaultElement.setAttribute("defaultpatRefGnctdHospitalCode", userVO.getHospitalCode());
			defaultElement.setAttribute("defaultpatAddPIN", objHospitalMstVO.getPinCode());
			defaultElement.setAttribute("defaultuserBoundDefaultGender", userVO.getUserBoundDefaultGender());

			
			// Calling DATA
			mp=EmgRegistrationDATA.getRegistrationFormEssentials_AJAX(userVO,objHospitalMstVO.getState(),objRequest);
			
			RegistrationConfigVO objRegistrationConfigVO = (RegistrationConfigVO)mp.get(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
			strAlreadyRegisteredFlag=objRegistrationConfigVO.getAlreadyRegisteredFlag();
			System.out.println("strAlreadyRegisteredFlag :"+strAlreadyRegisteredFlag);
			renewalConfigElement.setAttribute("alreadyRegistered",strAlreadyRegisteredFlag );
			renewalConfigElement.setAttribute("aadhaarSearchable", objRegistrationConfigVO.getIsAadhaarSearchable());
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
			createDepartmentListObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT),responseDocument,"departmentUnitCode");
			createPatientCategoryListObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY),responseDocument,"patPrimaryCatCode");
			
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS),responseDocument,"patMaritalStatusCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_GENDER),responseDocument,"patGenderCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION),responseDocument,"patReligionCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_CASTE),responseDocument,"patCasteCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY),responseDocument,"patAddCountryCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_STATE),responseDocument,"patAddStateCode");
			/**Start: Mukund Vinayak Modified for RMO Employee data on 30.May.2016*/
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_RMO_EMP),responseDocument,"patRMOEmployee");
			/**End: Mukund*/	
			
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY),responseDocument,"patIsUrban");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE),responseDocument,"patAgeUnit");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL),responseDocument,"patRefGnctdHospitalCode");
			
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL),responseDocument,"patOccupation");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE),responseDocument,"patAddDistrictCode");
			

		 	 List relationList=(List<ArrayList>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL);
			    
			 List relationList2=(ArrayList) WebUTIL.removeEntriesfromOptions(relationList, RegistrationConfig.RELATION_MASTER_VALUE_FOR_SELF);
			    
			createOptionObject(relationList2,responseDocument,"broughtByRelationCode");

			createVerificationObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS),responseDocument,"patDocType");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL),responseDocument,"patRefGnctdHospitalDept");
			
			//Start:Sheeldarshi
			//Reason: Change for adding Organisation combo on All Registration Pages
			//Date: 14thJune'15
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_CLIENT_OPTION_LIST),responseDocument,"clientCode");
			//End
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL),responseDocument,"relationWithStaff");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE),responseDocument,"agsDistrictCode");
			//added by Mukund on 22.07.2016
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_LETTER_TYPE_OPTION_LIST),responseDocument,"letterType");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.BROUGHT_BY_DEATH_DECLARED_BY),responseDocument,"broughtByDeclared");//
			createPaymentModeObject((List<Entry>)mp.get(RegistrationConfig.PAYMENT_MODE_OPTION_LIST),responseDocument,"paymentModeCode");

			WebUTIL.setMapInSession(mpToSetInSession,objRequest,"EmgRegistrationAction");
			
			
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
			//System.out.println(output);
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
			
			List lstState=EmgRegistrationDATA.getState_AJAX(userVO,strCountryCode);
			
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
			
			List lstDistrict=EmgRegistrationDATA.getDistrict_AJAX(userVO,strStateCode,strCountryCode);
			
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
			
			List lstRefDept=EmgRegistrationDATA.getRefDept_AJAX(userVO, strRefHospCode);
			
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
	
	public static void getPatDtlOnPatCatId(HttpServletRequest objRequest, HttpServletResponse objResponse) {
		
		System.out.println("EmgRegistrationUTIL :: getPatDtlOnPatCatId");
		List<String> lstPatientJsonObjString = new ArrayList<String>();
		StringBuilder strResponse = new StringBuilder();
		String strMode="3";
		try{
		
			String patPrimaryCatCode= (String)objRequest.getParameter("patPrimaryCatCode");
			String strSearchCatName =	(String)objRequest.getParameter("searchCatName");
			String strSearchCatId	=	(String)objRequest.getParameter("searchCatId");
			String strAlreadyRegistrdFlag = (String)objRequest.getParameter("alreadyRegisteredFlag");
			
			if(strAlreadyRegistrdFlag!=null && !strAlreadyRegistrdFlag.equals("") && !strAlreadyRegistrdFlag.equals("0"))
				strMode= strAlreadyRegistrdFlag;
			
			UserVO userVO=getUserVO(objRequest);
			
			//if(patPrimaryCatCode!=null && !patPrimaryCatCode.equals(""))
				lstPatientJsonObjString=EmgRegistrationDATA.getPatDtlOnPatCatId(userVO,patPrimaryCatCode,strSearchCatName,strSearchCatId,strMode);
			
			
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
			option.setAttribute("value", "{\"departmentCode\":\""+entryValueArray[0]+"\",\"departmentName\": \""+entry.getLabel()+"\",\"deptUnitCode\":\""+entryValueArray[1]+"\",\"roomCode\":\""+entryValueArray[2]+"\",\"roundRobinUnitFlag\":\""+entryValueArray[4]+"\",\"roundRobinRoomFlag\":\""+entryValueArray[3]+"\",\"ageBound\":\""+(entryValueArray[5])+"\",\"defaultGenderCode\":\""+entryValueArray[6]+"\",\"isReferCompulsory\":\""+entryValueArray[7]+"\"}");
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
		System.out.println("EmgRegistrationUTIL :: createPatientDtlObject -->> Start");
		Element fieldElement=responseDocument.createElement(fieldName);
		
		fieldElement.setAttribute("value", strPatientJasonObj);
		responseDocument.getFirstChild().appendChild(fieldElement);
		//System.out.println("EmgRegistrationUTIL :: createPatientDtlObject -->> End");
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

		//End:Mukund
	

	
	/**
	 * saves Emg Patient Registration Details
	 * generates a String of Queue, Departement, unit and room 
	 * @param _request -HttpServletRequest
	 * @param objEmgPatRegSupp_p -EmgRegistrationFBDuplicate form bean
	 * @return return true if any duplicate record found against the patient detail
	 * else save the patient details and register and return false
	 */
	public static void saveEmgPatientRegistration(EmgPatientRegistrationSUP objEmgPatRegSupp_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p )
	{
		
		System.out.println("EmgRegistrationUTIL :: saveEmgPatientRegistration()");
		
		boolean exists=false;
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		String outputString="";
		
		UserVO userVO=getUserVO(objRequest_p);
		RenewalConfigVO objRenewalConfigVO=null;
		
		userVO.setTariffID(RegistrationConfig.EMERGENCY_REGISTRATION_TARIFF_ID);
		userVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
		Status objStatus =new Status();	
		
		String strIsSavedSuccussfulFlag="0", strSavedOrErrMsg="",strIsFormFieldResetFlag="0", strPrintDivContent="", strErrMsg="",strIsDuplicatePatient="";
		String strTokenToSet="";
		boolean flagSaveMsgObjCreated=false;
		PatientBroughtByDtlVO patientBroughtByDtlVO=new PatientBroughtByDtlVO();
		String strBillPrintDiv="";
		
		NICEmailClient  emailNICPost=new NICEmailClient();
		
		
		try
		{
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			//gets the hospital information
			HospitalMstVO objHospitalMstVO = getHospitalVO(objRequest_p);
			
			PatientIdVO objPatientIdVO = new PatientIdVO();
			CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();

			objPatientIdVO.setPatIdNo(objEmgPatRegSupp_p.getPatCardNo());
			objPatientIdVO.setPatDocType(objEmgPatRegSupp_p.getPatDocType());
			String strUniqueIdDuplicyFlag = "0";
			//By Mukund on 30.11.2016//in the following lines new token were generated and then set inside the session using TokenHelper class
			String sessionTokenId = objRequest_p.getHeader("token");
			System.out.println("Session Token: "+sessionTokenId);
			System.out.println("Jsp Token: "+ TokenHelper.getToken("token"));
			strTokenToSet = TokenHelper.generateGUID();
			TokenHelper.setSessionToken("token",strTokenToSet);
			System.out.println("New Token: "+strTokenToSet);
			//End: Mukund
			if(objEmgPatRegSupp_p.getIsUnknown()!=null&&objEmgPatRegSupp_p.getIsUnknown().equals("1")){
				objEmgPatRegSupp_p.setIsMLC("1");
			}
			if(objEmgPatRegSupp_p.getIsBroughtBy()!=null){
			if(objEmgPatRegSupp_p.getIsBroughtBy().equals(RegistrationConfig.IS_BROUGHT_BY_TRUE)){
				 HelperMethods.populate(patientBroughtByDtlVO, objEmgPatRegSupp_p);
			}
			}
			else{
				patientBroughtByDtlVO=null;
			}
			
			if(objEmgPatRegSupp_p.getIsIdRequired()!=null && objEmgPatRegSupp_p.getIsIdRequired().equals("2"))
				objEmgPatRegSupp_p.setPatIdNo(objEmgPatRegSupp_p.getHiddenPatIdNo());
			
			if(objEmgPatRegSupp_p.getPatRefGnctdHospitalCode()!=null && !objEmgPatRegSupp_p.getPatRefGnctdHospitalCode().equals(""))
				objEmgPatRegSupp_p.setPatRefGnctdHospitalCode(objEmgPatRegSupp_p.getPatRefGnctdHospitalCode().split("\\#")[0]);
			System.out.println("PatRefGnctdHospitalCode :"+objEmgPatRegSupp_p.getPatRefGnctdHospitalCode());
			
			
			
			if((objEmgPatRegSupp_p.getPatNationalId()!=null && !objEmgPatRegSupp_p.getPatNationalId().equals("")) || (objEmgPatRegSupp_p.getPatCardNo()!=null && !objEmgPatRegSupp_p.getPatCardNo().equals("")))
				strUniqueIdDuplicyFlag = EmgRegistrationDATA.checkUniqueIdDuplicy(userVO, objPatientIdVO,objEmgPatRegSupp_p.getPatNationalId());
			
			/*if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
				if(strUniqueIdDuplicyFlag.equals("1")){
					strErrMsg="Duplicate National Id/ Aadhar No";
					//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
					flagSaveMsgObjCreated = createSaveMsgObject2(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient,strTokenToSet);
					return;
				}else if(strUniqueIdDuplicyFlag.equals("2")){
					strErrMsg="Patient with this "+objEmgPatRegSupp_p.getPatDocTypeName()+"("+objEmgPatRegSupp_p.getPatCardNo()+ ") already registered.";
					//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
					flagSaveMsgObjCreated = createSaveMsgObject2(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient,strTokenToSet);

					return;
				}
				else if(strUniqueIdDuplicyFlag.equals("4")){
					strErrMsg="Duplicate National Id/ Aadhar No";
					//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
					flagSaveMsgObjCreated = createSaveMsgObject2(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient,strTokenToSet);

					return;
				}
			}*/
			//Start:Sheeldarshi:16 Oct 14:Duplicacy Check
			//To Check the Duplicacy on the Secondary Alternate Id Basis
			/*if((objEmgPatRegSupp_p.getPatIdNo()!=null && !objEmgPatRegSupp_p.getPatIdNo().equals("") && objEmgPatRegSupp_p.getPatCatDocIsAlternateId().equals("1")))
			{
				objPatientIdVO.setPatIdNo(objEmgPatRegSupp_p.getPatIdNo());
				objPatientIdVO.setPatDocType(objEmgPatRegSupp_p.getPatCatDocCode());
				strUniqueIdDuplicyFlag = NewRegistrationDATA.checkUniqueIdDuplicy(userVO, objPatientIdVO,objEmgPatRegSupp_p.getPatNationalId());
					
				if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
					if(strUniqueIdDuplicyFlag.equals("1")){
						strErrMsg="Duplicate National Id/ Aadhar No";
						//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
						flagSaveMsgObjCreated = createSaveMsgObject2(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient,strTokenToSet);
						return;
					}else if(strUniqueIdDuplicyFlag.equals("2")){
						strErrMsg="Patient with this Id No ("+objEmgPatRegSupp_p.getPatIdNo()+ ") already registered.";
						//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
						flagSaveMsgObjCreated = createSaveMsgObject2(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient,strTokenToSet);
						return;
					}else if(strUniqueIdDuplicyFlag.equals("4")){
						strErrMsg="Patient with this Id No ("+objEmgPatRegSupp_p.getPatIdNo()+ ") already registered.";
						//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
						flagSaveMsgObjCreated = createSaveMsgObject2(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient,strTokenToSet);
						return;
					}
				}
			
			}*/
			
			//To Check the Duplicacy on the Reference Letter No for the CREDIT BASED BENEFICIARY WITH REFERENCE Categories
			if(objEmgPatRegSupp_p.getPatPrimaryCatGrp()!=null && objEmgPatRegSupp_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) )
			{
				HelperMethods.populate(objCreditAvailDtlVO, objEmgPatRegSupp_p);
				objCreditAvailDtlVO.setTariffId(userVO.getTariffID());
				strUniqueIdDuplicyFlag = NewRegistrationDATA.checkBeneficiaryIdDuplicy(userVO, objCreditAvailDtlVO);

				if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
					if(strUniqueIdDuplicyFlag.equals("1")){
						strErrMsg="Patient with this Reference Letter No ("+objEmgPatRegSupp_p.getCreditLetterRefNo()+ ") already registered.";
						//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
						flagSaveMsgObjCreated = createSaveMsgObject2(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient,strTokenToSet);
						return;
					}
				}
			}
			
			//End:Sheeldarshi:16 Oct 14:Duplicacy Check
			//For validating the pat amount collected
			/*if(objEmgPatRegSupp_p.getPatAmountCollected()==null || objEmgPatRegSupp_p.getPatAmountCollected().equals(""))
			{
				strErrMsg= "Registration Fee cannot be empty.\nPlease Enter this.";
				flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0");
				//objEmgPatRegSupp_p.setErrorMessage("Registration Fee cannot be empty.\nPlease relogin.");
				return ;
			}*/
			
			setSysdateAndDefaultCrNoFormat(objRequest_p);
			//isReferredDecider(objEmgPatRegSupp_p,objRequest_p);
				
			if(objEmgPatRegSupp_p.getPatRefGnctdHospitalDept().equals("0")){
				objEmgPatRegSupp_p.setPatRefGnctdHospitalDept(objEmgPatRegSupp_p.getPatRefHospitalDeptOther());
			}
			if(objEmgPatRegSupp_p.getPatAddState()!=null && objEmgPatRegSupp_p.getPatAddState().indexOf(',')!=-1){
				String[] strArrPatAddState= objEmgPatRegSupp_p.getPatAddState().split("\\,"); 
				if(!strArrPatAddState[0].trim().equals(""))
					objEmgPatRegSupp_p.setPatAddState(strArrPatAddState[0].trim());
				else
					objEmgPatRegSupp_p.setPatAddState(strArrPatAddState[1].trim());
			}
			
			if(objEmgPatRegSupp_p.getPatAddDistrict()!=null && objEmgPatRegSupp_p.getPatAddDistrict().indexOf(',')!=-1){
				String[] strArrPatAddDistrict= objEmgPatRegSupp_p.getPatAddDistrict().split("\\,"); 
				if(!strArrPatAddDistrict[0].trim().equals(""))
					objEmgPatRegSupp_p.setPatAddDistrict(strArrPatAddDistrict[0].trim());
				else
					objEmgPatRegSupp_p.setPatAddDistrict(strArrPatAddDistrict[1].trim());
			}
			
			setNotMandatoryDefaultCmbValue(objEmgPatRegSupp_p);
			PatientVO patientVO = new PatientVO();	
			//edited by sandip naik on 1 may 2017
            int location= getSpecialCharacterCount(objEmgPatRegSupp_p.getPatAddCityLoc());
            // end by sandip
			HelperMethods.populate(patientVO, objEmgPatRegSupp_p);
			
			//To set the CREDIT LIMIT By Mukund on 22.07.2016
			if(objEmgPatRegSupp_p.getPatPrimaryCatGrp()!=null && objEmgPatRegSupp_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) )
			{
				patientVO.setCreditLimit(objEmgPatRegSupp_p.getCreditLimit());
			}
			if(objEmgPatRegSupp_p.getPatPrimaryCatGrp()!=null && objEmgPatRegSupp_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE) )
			{
				patientVO.setCreditLimit(objEmgPatRegSupp_p.getAgsCreditLimit());
			}
			//End: Mukund
			
			Map<String, RenewalConfigVO> mapOfRenewalVoOnPatCatGroupKey= (Map<String, RenewalConfigVO>)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO);
			objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get(objEmgPatRegSupp_p.getPatPrimaryCatCode());
			if(objRenewalConfigVO==null)
				objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get("10");
			patientVO.setRenewalConfig(objRenewalConfigVO);
			patientVO.getRenewalConfig().setStrRenewalGroup(RegistrationConfig.RENEWAL_CONFIG_GROUP_CASUAL);
			
			patientVO.setPatIsDead("0");
			patientVO.setPatIsMerged("0");
			patientVO.setTariffId(userVO.getTariffID());
			patientVO.setPatIdNo(objEmgPatRegSupp_p.getPatIdNo());
			
			HelperMethods.populate(patientVO.getPatAddress(), objEmgPatRegSupp_p);
			patientVO.getPatAddress().setPatVerificationStatus("2");
			if(objEmgPatRegSupp_p.getPatAddCountryCode()!=null && objEmgPatRegSupp_p.getPatAddCountryCode().equals(RegistrationConfig.COUNTRY_CODE_INDIA))
				patientVO.setPatNationalityCode("1");
			else
				patientVO.setPatNationalityCode("0");
			
			if(objHospitalMstVO.getDistrictCode()!=null && objEmgPatRegSupp_p.getPatAddDistrictCode()!=null && objHospitalMstVO.getDistrictCode().equals(objEmgPatRegSupp_p.getPatAddDistrictCode()) )
				patientVO.getPatAddress().setPatIsLocal("1");
			else
				patientVO.getPatAddress().setPatIsLocal("0");
				
			if(objEmgPatRegSupp_p.getPatRefGnctdHospitalDept().equals("0"))
				patientVO.setPatRefGnctdHospitalDept(objEmgPatRegSupp_p.getPatRefHospitalDeptOther());
			if(objEmgPatRegSupp_p.getIsReferred().equals(""))
				patientVO.setIsReferred("0");
			
			
			String[] deptToVisit = objEmgPatRegSupp_p.getDepartmentsToVisitStamp();
		
			EpisodeVO[] arrEpisodeVO;		
				
			Collection colDept		=(Collection)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT);
			//Collection colPrimaryCat=(Collection)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY);
			//HospitalMstVO objHospitalMstVO= (HospitalMstVO)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_VO_RENEWEL_CONFIG);
			
			String deptName="";
			//for radiotherapy
			String referDeptname=objEmgPatRegSupp_p.getPatRefGnctdHospitalDept();
			
			String  isAmbulatory = objEmgPatRegSupp_p.getIsAmbulatoryCheckUncheck(); //Added by Vasu on 03.May.2018
			String isTrauma = objEmgPatRegSupp_p.getIsTrauma();
			
			if(deptToVisit== null || deptToVisit.length==0)
			{
				arrEpisodeVO = new EpisodeVO[1];
				
				arrEpisodeVO[0]=new EpisodeVO();
				setDepartmentDtl(colDept, patientVO, objEmgPatRegSupp_p.getDepartmentUnitCode());
				arrEpisodeVO[0].setDepartmentCode(objEmgPatRegSupp_p.getDepartmentCode());
				arrEpisodeVO[0].setDepartmentUnitCode(patientVO.getDepartmentUnitCode());
			    
				arrEpisodeVO[0].setIsAmbulatory(isAmbulatory); //Added by Vasu on 03.May.18
				arrEpisodeVO[0].setIsTrauma(isTrauma);
				//deptName=getEntryLabel(colDept,objEmgPatRegSupp_p.getDepartmentCode());
				//since department contains department name plus unit name
				//arrEpisodeVO[0].setDepartment(deptName);
				//arrEpisodeVO[0].setDepartment(deptName);
				arrEpisodeVO[0].setSnomdCIdVisitReason(objEmgPatRegSupp_p.getSnomdCIdVisitReason());
				arrEpisodeVO[0].setSnomdPTVisitReason(objEmgPatRegSupp_p.getSnomdPTVisitReason());
				
				arrEpisodeVO[0].setPaymentModeCode(objEmgPatRegSupp_p.getPaymentModeCode());
				if(!objEmgPatRegSupp_p.getPaymentModeCode().equals("1"))
					arrEpisodeVO[0].setPaymentModeCodeRefId(objEmgPatRegSupp_p.getPaymentModeRefId());
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
					arrEpisodeVO[i].setIsAmbulatory(isAmbulatory); //Added by Vasu on 03.May.18
					arrEpisodeVO[i].setIsTrauma(isTrauma);
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
			patientVO.setPatRegCatCode(RegistrationConfig.PATIENT_REG_CATEGORY_EMERGENCY);
			
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
			
			
			/////setting vo in session for dupllicate registration or new department visit
			//WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, arrEpisodeVO);
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.REGISTRATIONDESK_PATIENT_VO, patientVO);
			patientVO.setApprovedBy(objEmgPatRegSupp_p.getPatRMOEmployee());
			// edited by sandip naik on 1 may 2017
			
			//Added by Vasu on 03.May.18
			 patientVO.setIsAmbulatory(objEmgPatRegSupp_p.getIsAmbulatoryCheckUncheck());
			//End Vasu
			if(location==0)
			{
				
				strErrMsg="Unexpected action!Please try again!!Contact Administrator!!";
				//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
				flagSaveMsgObjCreated = createSaveMsgObject2(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient, strTokenToSet);
				return;
			}
			else
				// end by sandip
			{
			EpisodeVO episodeVO[]=EmgRegistrationDATA.registerEmgPatient(patientVO,arrEpisodeVO,userVO,objRequest_p,patientBroughtByDtlVO);
			if(objEmgPatRegSupp_p.getIsActualDob().equals("1"))
				objEmgPatRegSupp_p.setPatAge(patientVO.getPatAge());

			//By Mukund on 25.09.2017
			
			//calling barcode slip content creation method
			//WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.EPISODE_FOR_BARCODE, episodeVO);
			//WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATVO_FOR_BARCODE, patientVO);
			BarcodeGenerationUTIL.saveAndCreateBarcodeSlipPrintingDetails(objRequest_p, episodeVO, patientVO);
			/* Start : Surabhi
			 * date : 5-oct-16
			 * reason : SMS integration
			 * */
			
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
			//code from sending message through CDAC MUmbai SMS Gateway
			//SMSHttpPostClient.sendSingleSMSThroughSMSGateway(objSMSConfig.sms_username, objSMSConfig.sms_password,objSMSConfig.sms_senderId,objSMSConfig.sms_url, patientVO.getPatAddMobileNo(),message);
			
			//code from sending message through NIC SMS Gateway
/***********///SMSHttpsNICPostClient.sendTextSMSThroughNICSMSGateway(objSMSConfig.nic_sms_username, objSMSConfig.nic_sms_password,objSMSConfig.nic_sms_senderId,objSMSConfig.nic_sms_url, patientVO.getPatAddMobileNo(),message);
			
			//End
			
			/*  ## 		Modification Log							
	 		##		Modify Date				:08-July 2016
	 		##		Reason	(CR/PRS)		:Mail Integration
	 		##		Modify By				:Garima Gotra */
			
				
			message=message + "\n\n\nDISCLAIMER: PLEASE DO NOT REPLY TO THIS MAIL. THIS IS AN AUTO GENERATED MAIL AND REPLIES TO THIS EMAIL ID ARE NOT ATTENDED TO. FOR ANY QUERIES OR CLARIFICATIONS PLEASE CALL US AT THE CONTACT NUMBERS PROVIDED AT OUR WEBSITE nims.edu.in OR VISIT NIMS.";				
			if(patientVO.getPatAddEmailId()!=null && !patientVO.getPatAddEmailId().trim().equalsIgnoreCase(""))
			{
			
			//Code commented by Garima for Integration with NIC Mail service
			//Code for sending mail by CDAC SMTP Host
			 //Session _mailSession=emailPost.createMailSession(objEmailConfig.SMTP_USERNAME, objEmailConfig.SMTP_PASSWORD);
			//emailPost.sendSingleMailthroughGateway(_mailSession, objEmailConfig.SMTP_FROM_MAILI_ID, patientVO.getPatAddEmailId(), "Registration Confirmation In NIMS", message);
			
			//Code for sending mail by NIC SMS Gateway
			 //emailNICPost.sendEmail(patientVO.getPatAddEmailId(), "Registration Confirmation In NIMS", message);
			}
			
			strIsSavedSuccussfulFlag="1";
			strSavedOrErrMsg="Patient Registered";
			strIsFormFieldResetFlag="1";
			String tmpFileName=RegistrationConfig.CARD_NEW_REGISTRATION+userVO.getSeatId();
			
			//Calling QRCode Creation 
			/*QRCodeTest objQRCode = new QRCodeTest();
			String dataQR = "Name: "+patientVO.getPatFirstName()+" "+patientVO.getPatLastName()+", Age: "+patientVO.getPatAge()+", Gender: "+patientVO.getPatGender()+", CrNo: "+patientVO.getPatCrNo();
			
			String str = "{\"crno\":\""+patientVO.getPatCrNo()+"\", \"uid\":\""+patientVO.getPatNationalId()+"\", \"uhid\":\""+patientVO.getPatSecUHID()+"\", \"mobileNo\":\""+patientVO.getPatAddMobileNo()+"\", "
					+ "\"patCategoryCode\":\""+patientVO.getPatPrimaryCatCode()+"\", \"name\":\"Patient Full Name\", \"nameArray\":[\""+patientVO.getPatFirstName()+"\",\" "+patientVO.getPatMiddleName()+"\",\" "+patientVO.getPatLastName()+"\"], "
					+ "\"gender\":\""+patientVO.getPatGender().substring(0, 1)+"\", \"dob\"=\""+patientVO.getPatDOB()+"\", \"ageInYears\":\""+patientVO.getPatAgeWithUnit()+"\", \"yob\":\""+patientVO.getPatDOB()+"\", \"fatherName\":\""+patientVO.getPatGuardianName()+"\", "
					+ "\"motherName\":\""+patientVO.getPatMotherName()+"\", \"spouseName\":\""+(patientVO.getPatHusbandName().equals("")?"N/A":patientVO.getPatHusbandName())+"\", \"house\":\""+patientVO.getPatAddHNo()+"\", \"loc\":\""+patientVO.getPatAddStreet()+" "+patientVO.getPatAddCityLoc()+" "+patientVO.getPatAddCity()+"\", "
					+ "\"vtc\":\""+patientVO.getPatAddCity()+"\", \"dist\":\""+patientVO.getPatAddDistrict()+"\", \"distCode\":\""+patientVO.getPatAddDistrictCode()+"\", "
					+ "\"state\":\""+patientVO.getPatAddState()+"\", \"stateCode\":\""+patientVO.getPatAddStateCode()+"\", \"pc\":\""+patientVO.getPatAddPIN()+"\"}";
			

			objQRCode.createQRCode(str, objRequest_p);*/
			/**to decrypt the aadhaar number*/
			if(patientVO.getPatNationalId()!=null && !patientVO.getPatNationalId().equals(""))
			{
				String strPatAadhaarNo =NewRegistrationDATA.getAadhaarDecrypted(patientVO.getPatNationalId(), patientVO.getPatCrNo());
				patientVO.setPatNationalId(strPatAadhaarNo.split("#@#")[0]);
				System.out.println(strPatAadhaarNo.split("#@#")[0]+"\n"+strPatAadhaarNo.split("#@#")[1]+"\n"+strPatAadhaarNo.split("#@#")[2]);
			}
			/***/

			strPrintDivContent="";
			strPrintDivContent=NewRegistrationSlip.print(preapareSlip(episodeVO,patientVO,objEmgPatRegSupp_p, objRequest_p), tmpFileName,objRequest_p,"ER");
			
			System.out.println("PrintSlip :"+strPrintDivContent);
		
			//Bill Receipt Printing modified on 30.05.2016 ByMukund!
			if((objEmgPatRegSupp_p.getPatAmountCollected()!=null)&&!(objEmgPatRegSupp_p.getPatAmountCollected()!="0")&&!(objEmgPatRegSupp_p.getPatAmountCollected().equalsIgnoreCase("0.00"))){
				strBillPrintDiv=NewRegistrationSlip.printBillReceipt(preapareSlip(episodeVO,patientVO,objEmgPatRegSupp_p, objRequest_p), tmpFileName,objRequest_p,"EMG");
				System.out.println("PrintBillSlip :"+strBillPrintDiv);
				WebUTIL.setAttributeInSession(objRequest_p,"billReceiptString", strBillPrintDiv);
				//strPrintDivContent+="<br><br>"+strBillPrintDiv+"";
			}
			
			System.out.println("FullSlip :"+strPrintDivContent);			
			//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strSavedOrErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"1",strIsDuplicatePatient);
			flagSaveMsgObjCreated = createSaveMsgObject2(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient,strTokenToSet);

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
				createSaveMsgObject2(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient,strTokenToSet);
			
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
			if(strDeptValArray !=null && strDeptValArray[1].equals(strDeptCode_p)){
				objPatientVO_p.setDepartmentCode(strDeptValArray[0]);
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
	
	private static void setPrimaryCatDtl(Collection objCol_p, EmgPatientRegistrationSUP objEmgPatRegSupp_p,  String strPrimaryCatCode_p){
		
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
				objEmgPatRegSupp_p.setPatPrimaryCat(strPrimaryCatValArray[1]);
				
				break;
				//return objEntry.getLabel();		
			}
		}		
		//System.out.println("getEntryLabel:  "+)
	}
	
	private static void setNotMandatoryDefaultCmbValue(EmgPatientRegistrationSUP objEmgPatRegSupp_p){
		if(objEmgPatRegSupp_p.getIsUnknown()!=null&&objEmgPatRegSupp_p.getIsUnknown().equals("1")){

			objEmgPatRegSupp_p.setPatMaritalStatusCode("");	
			objEmgPatRegSupp_p.setPatCasteCode("");
			objEmgPatRegSupp_p.setPatReligionCode("");
			objEmgPatRegSupp_p.setPatOccupation("");
			objEmgPatRegSupp_p.setPatIsUrban("");
			objEmgPatRegSupp_p.setPatAddPhoneOwner("");
			objEmgPatRegSupp_p.setPatRefGnctdHospitalCode("");
			objEmgPatRegSupp_p.setPatRefGnctdHospitalDept("");
			/*  ## 		Modification Log							
		 		##		Modify Date				:15thDec'14 
		 		##		Reason	(CR/PRS)		:Bug fix 7728
		 		##		Modify By				:Sheeldarshi 
		 	objEmgPatRegSupp_p.setPatAgeUnit("");         */
			objEmgPatRegSupp_p.setPatDocType("");
				
		}
		else{
		objEmgPatRegSupp_p.setPatMaritalStatusCode(objEmgPatRegSupp_p.getPatMaritalStatusCode().equals("-1")?"":objEmgPatRegSupp_p.getPatMaritalStatusCode());	
		objEmgPatRegSupp_p.setPatCasteCode(objEmgPatRegSupp_p.getPatCasteCode().equals("-1")?"":objEmgPatRegSupp_p.getPatCasteCode());
		objEmgPatRegSupp_p.setPatReligionCode(objEmgPatRegSupp_p.getPatReligionCode().equals("-1")?"":objEmgPatRegSupp_p.getPatReligionCode());
		objEmgPatRegSupp_p.setPatOccupation(objEmgPatRegSupp_p.getPatOccupation().equals("-1")?"":objEmgPatRegSupp_p.getPatOccupation());
		objEmgPatRegSupp_p.setPatIsUrban(objEmgPatRegSupp_p.getPatIsUrban().equals("-1")?"":objEmgPatRegSupp_p.getPatIsUrban());
		objEmgPatRegSupp_p.setPatAddPhoneOwner(objEmgPatRegSupp_p.getPatAddPhoneOwner().equals("-1")?"":objEmgPatRegSupp_p.getPatAddPhoneOwner());
		objEmgPatRegSupp_p.setPatRefGnctdHospitalCode(objEmgPatRegSupp_p.getPatRefGnctdHospitalCode().equals("-1")?"":objEmgPatRegSupp_p.getPatRefGnctdHospitalCode());
		objEmgPatRegSupp_p.setPatRefGnctdHospitalDept(objEmgPatRegSupp_p.getPatRefGnctdHospitalDept().equals("-1")?"":objEmgPatRegSupp_p.getPatRefGnctdHospitalDept());
		objEmgPatRegSupp_p.setPatAgeUnit(objEmgPatRegSupp_p.getPatAgeUnit().equals("-1")?"":objEmgPatRegSupp_p.getPatAgeUnit());
		objEmgPatRegSupp_p.setPatDocType(objEmgPatRegSupp_p.getPatDocType().equals("-1")?"":objEmgPatRegSupp_p.getPatDocType());
		}
		//System.out.println("getEntryLabel:  "+)
	}
	
	public static RegistrationSlip preapareSlip(EpisodeVO episodeVO[],PatientVO _patVO,EmgPatientRegistrationSUP objEmgPatRegSupp_p,HttpServletRequest _request) throws ParseException{		
		
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
		UserVO _userVO = getUserVO(_request);		
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

			
		String primaryCatName=objEmgPatRegSupp_p.getPatPrimaryCat();
		regSlip.setPatPrimaryCat(primaryCatName);				
		regSlip.setPatGender(objEmgPatRegSupp_p.getPatGender());
		//System.out.println("-------------"+objEmgPatRegSupp_p.getPatOccupation()+"--------------------");
		regSlip.setPatOccupation("");
		//System.out.println("-------------"+regSlip.getPatOccupation()+"--------------------");
		//System.out.println("-------------"+objEmgPatRegSupp_p.getPatCasteCode()+"--------------------");
		regSlip.setPatCasteCode(objEmgPatRegSupp_p.getPatCaste());
		//System.out.println("-------------"+regSlip.getPatOccupation()+"--------------------");
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
				
		String patMiddleName=regSlip.getPatMiddleName();
		
		if(!(patMiddleName==null || patMiddleName.equals("")))
		{
			patMiddleName=patMiddleName.substring(0,1).toUpperCase();
			regSlip.setPatMiddleName(patMiddleName);
		}
		
		////Address For card
		String cityLocation="";
		String district="";
		String state="";
	
		String country=objEmgPatRegSupp_p.getPatAddCountry();
		
		cityLocation=objEmgPatRegSupp_p.getPatAddCity();
		if(_patVO.getPatAddDistrictCode()==null || _patVO.getPatAddDistrictCode().equals(""))
			district=_patVO.getPatAddDistrict();
		else
			district=objEmgPatRegSupp_p.getPatAddDistrict();
		
			state=objEmgPatRegSupp_p.getPatAddState();
		
		//inorder to prevent null showing for the location textbox on the print slip
		cityLocation=cityLocation==null?" ":cityLocation;
		
		
		String address=_patVO.getPatAddHNo();
		address=address+" "+_patVO.getPatAddStreet();
		
		address=address+" " +_patVO.getPatAddCity();
		if(district.equals("-1"))
			district="";
			String address2=district;
		if(!address2.equals(""))
			address2=address2+","+state;
		else
			address2=state;
		
			address2=address2+" "+country;
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
			depUnit[i]=episodeVO[i].getDepartmentUnit();
			queueNo[i]=episodeVO[i].getQueNo();
			expDate[i]=episodeVO[i].getExpiryDate();
			episodeDate[i]=(String)_request.getSession().getAttribute(Config.SYSDATE);
			
		    SimpleDateFormat _sdf1 =new SimpleDateFormat("dd/MM/yyyy HH:mm");
		    SimpleDateFormat _sdf2 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
		    SimpleDateFormat _sdf3 =new SimpleDateFormat("dd-MMM-yyyy");
			
		    if(episodeDate[i]!=null&&episodeDate[i]!="")episodeDate[i]=_sdf2.format(_sdf1.parse(episodeDate[i]));
		    if(expDate[i]!=null&&expDate[i]!="")expDate[i]=_sdf2.format(_sdf3.parse(expDate[i]));	
		    
		     		    
		    //expDate[i]=_sdf2.format(_sdf1.parse(expDate[i]));		
			
			unitConsultant[i]=episodeVO[i].getUnitConsultant();
			//added to set the card and file print format
			filePrintFlag[i]=episodeVO[i].getFilePrintSetting();
			cardPrintFlag[i]=episodeVO[i].getCardPrintSetting();
			
			// disclaimers
			//System.out.println("----------"+episodeVO[i].getDisclaimer()+"-------------");
	
			
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
			
			//workingDays[i]=episodeVO[i].getUnitWorkingDays();
			//episodeDate[i]=(String)_request.getSession().getAttribute(Config.SYSDATE);
			
			String[] temp=episodeVO[i].getUnitWorkingDays().split("#");
			if(temp.length==0)
				temp=new String[]{"",""};
			System.out.println("temp[0]"+temp[0]);
			//System.out.println("temp[1]"+temp[1]);
			workingDays[i]=temp[0];
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
		
		regSlip.setDependentName(objEmgPatRegSupp_p.getPatMemRelationCode());
		regSlip.setPatMemRelationName(objEmgPatRegSupp_p.getPatMemRelationName());
		regSlip.setHiddenPatIdNo(objEmgPatRegSupp_p.getHiddenPatIdNo());
		regSlip.setPatMemDeptName(objEmgPatRegSupp_p.getPatMemDeptName());
		regSlip.setLoginUserName(_userVO.getUsrName());//By Mukund				
		return regSlip;		
	}
	
	public static boolean validateSavePatDtl(EmgPatientRegistrationSUP objPatSup_p,HttpServletResponse objResponse_p) {
		List lstErrMsg=new ArrayList();
		boolean flagValidationError=false;
		System.out.println("objPatSup_p.getDepartmentCode :: "+ objPatSup_p.getDepartmentCode());
		if(objPatSup_p.getDepartmentUnitCode().equals("-1") || objPatSup_p.getDepartmentUnitCode().isEmpty() || objPatSup_p.getDepartmentUnitCode() == null){
			lstErrMsg.add("Visiting Unit Is Required");
		}
		else if(!objPatSup_p.getDepartmentUnitCode().matches("^([1-9][0-9][0-9][0-9][1-9])$"))
		{
			lstErrMsg.add("Visiting Unit Is Required. It Accepts Numeric Values Between 10001 To 99999 Only.");
		}
		if(objPatSup_p.getPatPrimaryCatCode().equals("-1") || objPatSup_p.getPatPrimaryCatCode().isEmpty() || objPatSup_p.getPatPrimaryCatCode() == null){
			lstErrMsg.add("Patient Category Is Required");
		}
		else if(!objPatSup_p.getPatPrimaryCatCode().matches("^([1-9][0-9])$"))
		{
			lstErrMsg.add("Patient Category Is Required. It Accepts Numeric Values Between 11 To 99 Only.");
		}
		if(objPatSup_p.getPatFirstName().isEmpty() || objPatSup_p.getPatFirstName()==null){
			lstErrMsg.add("First Name Is Required");
		}
		else if(!objPatSup_p.getPatFirstName().matches("^[a-zA-Z\\s]*$"))
		{
			lstErrMsg.add("First Name Is Required. It Accept Alphabets Only.");
		}
		if(objPatSup_p.getIsActualDob().equals("0"))
		{
			if(objPatSup_p.getPatAge().isEmpty() || objPatSup_p.getPatAge()==null){
				lstErrMsg.add("Age is Required");
			}
			else if(!objPatSup_p.getPatAge().matches("^([1-9]|[1-9][0-9]|1[0-2][0-5])$"))
			{
				lstErrMsg.add("Age Is Required. It Accept Numeric Value Between 1 to 125 Only.");
			}
		}
		else if(objPatSup_p.getIsActualDob().equals("1"))
		{
			if(objPatSup_p.getPatDOB().isEmpty() || objPatSup_p.getPatAge()==null)
			{
				lstErrMsg.add("DOB is Required");
			}
			else if(!(objPatSup_p.getPatDOB().isEmpty() || objPatSup_p.getPatAge()==null))
			{
				monthRenewalModeValidation(objPatSup_p.getPatDOB(),lstErrMsg);
			}
		}
		if(objPatSup_p.getPatGenderCode().equals("-1") || objPatSup_p.getPatGenderCode().isEmpty() || objPatSup_p.getPatGenderCode()==null){
			lstErrMsg.add("Gender is Required");
		}
		else if(!objPatSup_p.getPatGenderCode().matches("^(F|I|M|N|T)$"))
		{
			lstErrMsg.add("Gender Is Required. It Accept Valid Gender Only.");
		}
		if(objPatSup_p.getIsUnknown().equals("0")){
			if((objPatSup_p.getPatGuardianName().isEmpty() || objPatSup_p.getPatGuardianName()==null) && (objPatSup_p.getPatHusbandName().isEmpty() || objPatSup_p.getPatHusbandName()==null)){
				lstErrMsg.add("Father Name Or Spouse Name is Required");
			}
			if(!(objPatSup_p.getPatGuardianName().isEmpty() || objPatSup_p.getPatGuardianName()==null))
			{
				if(!(objPatSup_p.getPatGuardianName().matches("^[a-zA-Z\\s]*$")))
				{
					lstErrMsg.add("Fathers Name Is Required. It Accept Alphabets with Spaces Only.");
				}
			}
			if(!(objPatSup_p.getPatHusbandName().isEmpty() || objPatSup_p.getPatHusbandName()==null))
			{
				if(!(objPatSup_p.getPatHusbandName().matches("^[a-zA-Z\\s]*$")))
				{
					lstErrMsg.add("Spouse Name Is Required. It Accept Alphabets with Spaces Only.");
				}
			}
			if(objPatSup_p.getPatAmountCollected().isEmpty() || objPatSup_p.getPatAmountCollected()==null){
				lstErrMsg.add("Registration Fee is Required");
			}
		}
		if(objPatSup_p.getPatAddCountryCode().equals("IND") || objPatSup_p.getPatAddCountryCode().equals("-1"))
		{
			if(objPatSup_p.getPatAddStateCode().equals("-1") || objPatSup_p.getPatAddStateCode().isEmpty() || objPatSup_p.getPatAddStateCode()==null){
			lstErrMsg.add("State Name is Required");
			}
			else if(!objPatSup_p.getPatAddStateCode().matches("^[1-9][0-9]{0,1}$"))
			{
				lstErrMsg.add("State Name is Required. It Accepts Valid State Name Only.");
			}
			if(objPatSup_p.getPatAddDistrictCode().equals("-1") || objPatSup_p.getPatAddDistrictCode().isEmpty() || objPatSup_p.getPatAddDistrictCode()==null){
				lstErrMsg.add("District Name is Required");
			}
			else if(!objPatSup_p.getPatAddDistrictCode().matches("^[0-9]{2,3}$"))
			{
				lstErrMsg.add("District Name is Required. It Accept Valid District's Name Only.");
			}
		}
		else
		{
			if(objPatSup_p.getIsUnknown().equals("0")){
			System.out.println("objPatSup_p.getIsUnknown() :: "+objPatSup_p.getIsUnknown());
				String[] state = objPatSup_p.getPatAddState().split(",");
				String[] district = objPatSup_p.getPatAddDistrict().split(",");
				
				if(state[1].isEmpty() || state[1].equals(" ") || state[1].equals(null))
				{
					lstErrMsg.add("State Name is Required");
				}
				if(district[1].equals(null) || district[1].isEmpty() || district[1].equals(" "))
				{
					lstErrMsg.add("District Name is Required");
				}
			}
		}
		/*if(objPatSup_p.getPatAddCountryCode().equals("-1") || objPatSup_p.getPatAddCountryCode().isEmpty() || objPatSup_p.getPatAddCountryCode()==null){
			lstErrMsg.add("Country Name is Required");
		}
		else if(!objPatSup_p.getPatAddCountryCode().matches("^[A-Z][A-Z][A-Z]$"))
		{
			lstErrMsg.add("Country Name is Required. It Accepts Valid Country Name Only.");
		}
		if(objPatSup_p.getPatAddStateCode().equals("-1") || objPatSup_p.getPatAddStateCode().isEmpty() || objPatSup_p.getPatAddStateCode()==null){
			lstErrMsg.add("State Name is Required");
		}
		else if(!objPatSup_p.getPatAddStateCode().matches("^[1-9][0-9]{0,1}$"))
		{
			lstErrMsg.add("State Name is Required. It Accepts Valid State Name Only.");
		}
		if(objPatSup_p.getPatAddDistrictCode().equals("-1") || objPatSup_p.getPatAddDistrictCode().isEmpty() || objPatSup_p.getPatAddDistrictCode()==null){
			lstErrMsg.add("District Name is Required");
		}
		else if(!objPatSup_p.getPatAddDistrictCode().matches("^[1-9][0-9][1-9]$"))
		{
			lstErrMsg.add("District Name is Required. It Accept Valid District's Name Only.");
		}*/
		if(!(objPatSup_p.getIsReferred().isEmpty() || objPatSup_p.getIsReferred()==null)){
			if(objPatSup_p.getReferringInstType().equals("G")){
				if(objPatSup_p.getPatRefGnctdHospitalCode().isEmpty() || (objPatSup_p.getPatRefGnctdHospitalCode().equals("-1")) || objPatSup_p.getPatRefGnctdHospitalCode()==null){
					lstErrMsg.add("Institute Name is Required");
				}
			}
			else if(objPatSup_p.getReferringInstType().equals("O")){
				if(objPatSup_p.getPatRefHospitalName().isEmpty() ){
					lstErrMsg.add("Other Name For Institute is Required");
				}
				else if(!objPatSup_p.getPatRefHospitalName().matches("^[a-zA-Z\\s]*$"))
				{
					lstErrMsg.add("Other Name For Institute Is Required. Is Accepts Alphabets With Spaces Only.");
				}
			}
			if(objPatSup_p.getPatRefDoctor().isEmpty() || objPatSup_p.getPatRefDoctor()==null){
				lstErrMsg.add("Doctor Name is Required");
			}
			else if(!objPatSup_p.getPatRefDoctor().matches("^[a-zA-Z(). ]*"))
			{
				lstErrMsg.add("Docter Name Is Required. It Accept Alphabets and Space with (). Only.");
			}
		}
		if(objPatSup_p.getIsBroughtBy() != null){
			if(objPatSup_p.getIsRelative().isEmpty() || objPatSup_p.getIsRelative().equals("-1") || objPatSup_p.getIsRelative()==null){
				lstErrMsg.add("Brought By is Required");
				if(objPatSup_p.getBroughtByName().isEmpty() || objPatSup_p.getBroughtByName()==null){
					lstErrMsg.add("Brought By Name is Required");
				}
				else if(!objPatSup_p.getBroughtByName().matches("^[a-zA-Z]*"))
				{
					lstErrMsg.add("Brought By Name Is Required. It Accept Alphabets Only.");
				}
				if(objPatSup_p.getBroughtByLocation().isEmpty() || objPatSup_p.getBroughtByLocation()==null){
					lstErrMsg.add("Brought From Location  is Required");
				}
				else if(!objPatSup_p.getBroughtByLocation().matches("^[a-zA-Z0-9]*"))
				{
					lstErrMsg.add("Brought From Location Is Required. It Accept Alphabets Only.");
				}
				if(objPatSup_p.getBroughtByPhone().isEmpty() || objPatSup_p.getBroughtByPhone()==null){
					lstErrMsg.add("Mobile Number is Required");
				}
				else if(!objPatSup_p.getBroughtByPhone().matches("^[1-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$"))
				{
					lstErrMsg.add("Mobile Number Is Required. It Accept Numbers Only.");
				}
				if(objPatSup_p.getBroughtByAddress().isEmpty() || objPatSup_p.getBroughtByAddress()==null){
					lstErrMsg.add("Address is Required");
				}
				else if(!objPatSup_p.getBroughtByAddress().matches("^[a-zA-Z0-9]*"))
				{
					lstErrMsg.add("Brought From Address Is Required. It Accept Alphabets Only.");
				}
			}
			else if(objPatSup_p.getIsRelative().equals("2")){
				if(objPatSup_p.getBroughtByName().isEmpty() || objPatSup_p.getBroughtByName()==null){
					lstErrMsg.add("Officers Name is Required");
				}
				else if(!objPatSup_p.getBroughtByName().matches("^[a-zA-Z]*"))
				{
					lstErrMsg.add("Officers Name Is Required. It Accept Alphabets Only.");
				}
				if(objPatSup_p.getBroughtByLocation().isEmpty() || objPatSup_p.getBroughtByLocation()==null){
					lstErrMsg.add("Brought From Location  is Required");
				}
				else if(!objPatSup_p.getBroughtByLocation().matches("^[a-zA-Z0-9]*"))
				{
					lstErrMsg.add("Brought From Location Is Required. It Accept Alphabets Only.");
				}
				if(objPatSup_p.getConstableDesig().isEmpty() || objPatSup_p.getConstableDesig()==null){
					lstErrMsg.add("Designation is Required");
				}
				else if(!objPatSup_p.getConstableDesig().matches("^[a-zA-Z0-9]*"))
				{
					lstErrMsg.add("Designation Is Required. It Accept Alphabets And Numbers Only.");
				}
				if(objPatSup_p.getConstableBadgeNo().isEmpty() || objPatSup_p.getConstableBadgeNo()==null){
					lstErrMsg.add("Badge Number is Required");
				}
				else if(!objPatSup_p.getConstableBadgeNo().matches("^[a-zA-Z0-9]*"))
				{
					lstErrMsg.add("Badge Number Is Required. It Accept Alphabets And Numbers Only.");
				}
			}
			else if(objPatSup_p.getIsRelative().equals("3")){
				if(objPatSup_p.getBroughtByName().isEmpty() || objPatSup_p.getBroughtByName()==null){
					lstErrMsg.add("Brought By Name is Required");
				}
				else if(!objPatSup_p.getBroughtByName().matches("^[a-zA-Z]*"))
				{
					lstErrMsg.add("Brought By Name Is Required. It Accept Alphabets Only.");
				}
				if(objPatSup_p.getBroughtByLocation().isEmpty() || objPatSup_p.getBroughtByLocation()==null){
					lstErrMsg.add("Brought From Location  is Required");
				}
				else if(!objPatSup_p.getBroughtByLocation().matches("^[a-zA-Z0-9]*"))
				{
					lstErrMsg.add("Brought From Location Is Required. It Accept Alphabets Only.");
				}
				if(objPatSup_p.getBroughtByPhone().isEmpty() || objPatSup_p.getBroughtByPhone()==null){
					lstErrMsg.add("Mobile Number is Required");
				}
				else if(!objPatSup_p.getBroughtByPhone().matches("^[1-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$"))
				{
					lstErrMsg.add("Mobile Number Is Required. It Accept Numbers Only.");
				}
				if(objPatSup_p.getBroughtByAddress().isEmpty() || objPatSup_p.getBroughtByAddress()==null){
					lstErrMsg.add("Address is Required");
				}
				else if(!objPatSup_p.getBroughtByAddress().matches("^[a-zA-Z0-9]*"))
				{
					lstErrMsg.add("Brought From Address Is Required. It Accept Alphabets Only.");
				}
			}
			else if(objPatSup_p.getIsRelative().equals("1")){
				if(objPatSup_p.getBroughtByRelationCode().equals("-1") || objPatSup_p.getBroughtByRelationCode().isEmpty() || objPatSup_p.getBroughtByRelationCode()==null){
					lstErrMsg.add("Relationship is Required");
				}
				else if(!objPatSup_p.getBroughtByRelationCode().matches("^[1-9][0-9]{0,1}$"))
				{
					lstErrMsg.add("Relationship is Required. It Accepts Valid Relationship Name Only.");
				}
				if(objPatSup_p.getBroughtByName().isEmpty() || objPatSup_p.getBroughtByName()==null){
					lstErrMsg.add("Brought By Name is Required");
				}
				else if(!objPatSup_p.getBroughtByName().matches("^[a-zA-Z]*"))
				{
					lstErrMsg.add("Brought By Name Is Required. It Accept Alphabets Only.");
				}
				if(objPatSup_p.getBroughtByLocation().isEmpty() || objPatSup_p.getBroughtByLocation()==null){
					lstErrMsg.add("Brought From Location  is Required");
				}
				else if(!objPatSup_p.getBroughtByLocation().matches("^[a-zA-Z0-9]*"))
				{
					lstErrMsg.add("Brought From Location Is Required. It Accept Alphabets And Numbers Only.");
				}
				if(objPatSup_p.getBroughtByPhone().isEmpty() || objPatSup_p.getBroughtByPhone()==null){
					lstErrMsg.add("Mobile Number is Required");
				}
				else if(!objPatSup_p.getBroughtByPhone().matches("^[1-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$"))
				{
					lstErrMsg.add("Mobile Number Is Required. It Accept Numbers Only.");
				}
				if(objPatSup_p.getBroughtByAddress().isEmpty() || objPatSup_p.getBroughtByAddress()==null){
					lstErrMsg.add("Address is Required");
				}
				else if(!objPatSup_p.getBroughtByAddress().matches("^[a-zA-Z0-9]*"))
				{
					lstErrMsg.add("Brought From Address Is Required. It Accept Alphabets Only.");
				}
			}
		}
				
		if(lstErrMsg!=null && lstErrMsg.size()>0){
			createValidateSaveMsgObject(lstErrMsg,objResponse_p);
			flagValidationError=true;
		}
		return flagValidationError;
	}
	public static void createValidateSaveMsgObject(List lstErrMsg,HttpServletResponse objResponse_p) {
		String strIsDuplicatePatient="";
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		Element msgElement=null;
		String strIsSavedSuccussfulFlag="0", strSavedOrErrMsg="",strIsFormFieldResetFlag="0", strPrintDivContent="", strErrMsg="";
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
	
	public static void monthRenewalModeValidation(String dob,List lst)
	{
		String[] part = dob.split("-");
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
		}
	}
	
	/* #Start					:Sheeldarshi 
	   #Modify Date(CR/PRS)		:22ndMay'15 
	   #Reason					:The Total amount collected by concerned operator should be displayed on registration & Billing Processes
	 */
	public static void getCashCollectionDetail(HttpServletRequest request,HttpServletResponse response, EmgPatientRegistrationSUP objPatSup_p) {
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
	public static void showSaveStaffImage(EmgPatientRegistrationSUP p_EmgPatientRegistrationSUP, HttpServletRequest _request)
	{
		HttpSession session= _request.getSession();
		String serverContext = "http://10.226.2.186:8780";//HISConfig.HIS_SERVICES_SERVER_URL;////session.getServletConfig();
		String serverContext2 = "http://"+_request.getRemoteAddr()+":"+Integer.toString(_request.getServerPort());//"http://10.226.2.186:8780"
				
		//System.out.println("Mukund RemoteAddr: "+serverContext2);
		
		String strHospitalCode = session.getAttribute("HOSPITAL_CODE").toString();
		String strHospitalCode2 = "";//strHospitalCode+"1700";
		String webServiceInput = p_EmgPatientRegistrationSUP.getPatIdNo()+"/"+p_EmgPatientRegistrationSUP.getMemSlNo()+"/"+strHospitalCode;//"331010008122/2/33101";
		String webServiceUrl = "/HBIMS/services/restful/hisImageRetrievalUtil/";
		String strFileName = "image_"+p_EmgPatientRegistrationSUP.getPatIdNo()+"_"+p_EmgPatientRegistrationSUP.getMemSlNo()+"_"+strHospitalCode;
		String completeUrl = serverContext +webServiceUrl+webServiceInput; //331011700005757
		//String completeUrl = "http://10.226.22.190:8580/HISRegistration/services/restful/hisImageRetrievalBasedOnCrNo/331011800000019";
		String strErrMsg="", strProcessId = FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD;
		Status objStatus = new Status();
		
		try
		{
			//calling the web service to get the data depends on input
			ResteasyClient client = new ResteasyClientBuilder().build();
	        ResteasyWebTarget target = client.target(completeUrl);
	        Response res = target.request().get();
	      	        
	        String ws_status = res.readEntity(String.class);
	        System.out.println("Output from mongoDB ImageWebService Server...");
	        System.out.println(ws_status);
	        String status1 ="", status2 ="";
	        ws_status="["+ws_status+"]";
	        JSONArray jsonArray = new JSONArray(ws_status);
	         
	        System.out.println(jsonArray.length());
	        for(int i=0;i<jsonArray.length();i++){
	        	status1 = jsonArray.getJSONObject(i).getString("data");
	        }
	        JSONArray jsonArray1 = new JSONArray(status1);
	        for(int j=0;j<jsonArray1.length();j++)
	        	status2 = jsonArray1.getJSONObject(j).getString("HRGBYTE_IMAGE");
	       //Getting the image as Base64 string
	       // System.out.println(status2);
        if(status2!=null && !status2.equals("") && !status2.equals("0")){	        
	        byte[] imageByte = Base64.decodeBase64(status2.getBytes());  
	        InputStream input = new ByteArrayInputStream(imageByte);
	        BufferedImage bfi = ImageIO.read(input);

	        /****************************/
	        /*
			response.flushBuffer();
			response.setContentType("image/jpg");
			os = response.getOutputStream();
			os.flush();
			bos = new BufferedOutputStream(os);
			bos.flush();
			bos.write(imageByte, 0, imageByte.length);
			bos.close();
			/*************************************/
	        
	        /***/
	        
	        //FileInputStream objFileInput = new FileInputStream(input);
	        /***/
	        
	        //Storing the image at temporary location
	        String path = session.getServletContext().getRealPath("/../HIS.war/hisglobal/images/"+strFileName+".png");
	        File outputfile = new File(path);
	        outputfile.createNewFile();
	        ImageIO.write(bfi , "png", outputfile);
	        bfi.flush();
	       
	        //picking the file from temp location
	        File tempInputFile = new File(path);
	        FileInputStream fis = new FileInputStream(tempInputFile);
	        	try
	    		{
	        		if(tempInputFile!=null && tempInputFile.isFile())
	    			{
	    				long fileSize = tempInputFile.length();
	    				
	    				if (fileSize > 4194304) // 4MB   10240 10KB
	    				{
	    					//addActionError("Can't Upload. File is larger than 4 MB.");
	    					strErrMsg = "Can't Upload. File is larger than 4 MB.";
	    	    			objStatus.add(Status.ERROR_AE,"","Can't Upload. File is larger than 4 MB.");
	    				}
	    				else
	    				{
	    					FileInputStream fileInStream = new FileInputStream(tempInputFile);
	    					/*p_EmgPatientRegistrationSUP.getSession().put(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT, fileInStream);
	    					p_EmgPatientRegistrationSUP.getMapSession().put(FileTransferConfig.KEY_UPLOADED_FILE_NAME, tempInputFile.getName());*/

	    					 WebUTIL.setAttributeInSession(_request,FileTransferConfig.KEY_UPLOADED_FILE_NAME, tempInputFile.getName());
	    					 WebUTIL.setAttributeInSession(_request,FileTransferConfig.KEY_UPLOADED_FILE_CONTENT, fileInStream);
	    					
	    					// Uploading File to FTP Server if required
	    					if(strProcessId!=null && FileTransferConfig.MAP_PROCESS_DETIAL.containsKey(strProcessId))
	    					{
	    						if(strHospitalCode2!=null && !strHospitalCode2.trim().equals("") && strFileName!=null && !strFileName.trim().equals("") )
	    						{
	    							System.out.println("Inside FTP");
	    							FileInputStream fileInFTPStream = new FileInputStream(tempInputFile);
	    							FTPFileTransfer.uploadFile(strProcessId, strFileName, fileInFTPStream, strHospitalCode2);
	    							System.out.println("FTP Saved");
	    						}
	    					}
	    					/*varStatus = FILE_UPLOAD_STATUS_DONE;
	    					addActionError("File Uploaded Successfully");*/
	    					strErrMsg = "File Uploaded Successfully";
	    	    			objStatus.add(Status.ERROR_AE,"","File Uploaded Successfully");
	    				}
	    			}
	    			else
	    			{
	    				//addActionError("File is not uploaded. Please try again.");
	    				strErrMsg = "File is not uploaded. Please try again.";
		    			objStatus.add(Status.ERROR_AE,"","File is not uploaded. Please try again.");
	    			}
	    		}
	    		catch (FileNotFoundException e)
	    		{
	    			//addActionError("File is not uploaded. Please try again.");
	    			e.printStackTrace();
	    			strErrMsg = "File is not uploaded. Please try again.";
	    			objStatus.add(Status.ERROR_AE,"","File is not uploaded. Please try again.");
	    		}
	    		catch (IOException e)
	    		{
	    			//addActionError("File is not uploaded. Please try again.");
	    			strErrMsg = "File is not uploaded. Please try again.";
	    			objStatus.add(Status.ERROR_AE,"","File is not uploaded. Please try again.");
	    			e.printStackTrace();
	    		}
	    		catch (Exception e)
	    		{
	    			strErrMsg = "File is not uploaded. Please try again.";
	    			objStatus.add(Status.ERROR_AE,"","File is not uploaded. Please try again.");
	    			
	    			//addActionError("File is not uploaded. Please try again.");
	    			e.printStackTrace();
	    		}
	    		finally
	    		{
	    		}
	    	
	        
	        /***/
	        
	        
		}
        else
        {
        	WebUTIL.setAttributeInSession(_request,FileTransferConfig.KEY_UPLOADED_FILE_NAME, null);
			WebUTIL.setAttributeInSession(_request,FileTransferConfig.KEY_UPLOADED_FILE_CONTENT, null);
        }
		}
		catch(Exception e)
		{
			new HisException("Emg_Registration", "SaveShowImage()--> ", e.getMessage());
		}
	}

	public  static String getHTML(String urlToRead) throws Exception {
	      StringBuilder result = new StringBuilder();
	     // result.append("");
	      URL url = new URL(urlToRead);
	      System.out.println(url.toString());
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      //conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	      conn.setRequestMethod("GET");
	      try{
		      if(conn.getResponseCode()==200){
		    	  BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    	  String line;
		    	  while ((line = rd.readLine()) != null) {
		    		  result.append(line);
		    	  }
		    	  rd.close();
	    	  }
	      }
	      catch(Exception e)
	      {
	    	  System.out.println("Not able to connect to n/w");
	      }
	      System.out.println(urlToRead);
	      System.out.println(result.toString());
	      return result.toString();
	   }
	 /*public boolean isJSONValid(String test) {
		    try {
		    	System.out.println("json to validate"+test);
		        new JSONObject(test);
		    } catch (JSONException ex) {
		       
		        // e.g. in case JSONArray is valid as well...
		        try {
		            new JSONArray(test);
		        } catch (JSONException ex1) {
		            return false;
		        }
		    }
		    return true;
		}*/
	//edited by sandip naik on 1 may 2017
	public static int getSpecialCharacterCount(String s) {
		System.out.println("EmgRegUTIL :: getSpecialCharacterCount");
	     if (s == null || s.trim().isEmpty()) {
	         System.out.println("Incorrect format of string");
	         return 1;
	     }
	     Pattern p = Pattern.compile("^[a-zA-Z0-9]+(\\s+[a-zA-Z0-9]+)*$");//("[^A-Za-z0-9]");
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
	//end by sandip; 
}//end of class