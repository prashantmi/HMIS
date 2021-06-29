package registration.transactions.controller.util;

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
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import registration.config.RegistrationConfig;
import registration.config.Exceptions.HisInvalidTokenNumberException;
import registration.config.slip.NewRegistrationSlip;
import registration.config.slip.RegistrationSlip;
import registration.transactions.controller.actionsupport.ExternalPatientRegistrationSUP;
import registration.transactions.controller.data.ExternalPatientRegistrationDATA;
import vo.registration.EpisodeVO;
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


public class ExternalPatientRegistrationUTIL extends ControllerUTIL
{
/**
 * sets all new registration essentials
 * @param _request -HttpServletRequest
 */
	public static void setAllNewRegistrationEssentials(ExternalPatientRegistrationSUP objExternalPatientRegistrationSUP,HttpServletRequest objRequest,
			HttpServletResponse objResponse, Map<Object, Object> mapSession){		
		Status objStatus=new Status();
		Map mp=new HashMap();
		try
		{
			UserVO userVO=getUserVO(objRequest);
			HospitalMstVO hosVo= getHospitalVO(objRequest);
			
			userVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
			userVO.setTariffID(RegistrationConfig.NEW_REGISTRATION_TARIFF_ID);
			HttpSession session =objRequest.getSession();
			
			mp=ExternalPatientRegistrationDATA.getRegistrationFormInitialEssentials(userVO,objExternalPatientRegistrationSUP.getIsUnitWiseRegistration());
			/*if(((String)session.getAttribute(RegistrationConfig.SESSION_FLAG_NEW_REGISTRATION_DUPLICATE))==null){
				mp=ExternalPatientRegistrationDATA.getAllNewRegistrationEssentials(userVO,hosVo.getState());
				WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.SESSION_FLAG_NEW_REGISTRATION_DUPLICATE, RegistrationConfig.SESSION_FLAG_YES);
				WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.REGISTRATION_ESSENTIAL_MAP, mp);
			}
			else{
				mp=(Map)session.getAttribute(RegistrationConfig.REGISTRATION_ESSENTIAL_MAP);
			}*/
			
			WebUTIL.setMapInSession(mp,objRequest,"ExternalPatientRegistrationAction");
			
			//NewRegistrationUTIL.setDeptOptions(_request, objExternalPatRegSupp_p);
			//isRegistrationAllowed(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL,getUserVO(_request));
		}
		catch(HisRecordNotFoundException e){
			objExternalPatientRegistrationSUP.addActionError(e.getMessage());
			//WebUTIL.setMapInSession(e.getEssentialMap(),objRequest,"ExternalPatientRegistrationAction");
			
		}		
		catch(HisDataAccessException e){
		//	WebUTIL.setMapInSession(e.getEssentialMap(),_request,"ExternalPatientRegistrationAction");
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
	
	public static void populateRegistrationFormEssentials_AJAX(ExternalPatientRegistrationSUP objExternalPatientRegistrationSUP,HttpServletRequest objRequest,
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
			
			
			// Calling DATA
			mp=ExternalPatientRegistrationDATA.getRegistrationFormEssentials_AJAX(userVO,hospitalVO.getState(),objRequest);
			//RenewalConfigVO objRenewalConfigVO = (RenewalConfigVO)mp.get(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
			
			RegistrationConfigVO objRegistrationConfigVO = (RegistrationConfigVO)mp.get(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
			strAlreadyRegisteredFlag=objRegistrationConfigVO.getAlreadyRegisteredFlag();
			System.out.println("strAlreadyRegisteredFlag :"+strAlreadyRegisteredFlag);
			renewalConfigElement.setAttribute("alreadyRegistered",strAlreadyRegisteredFlag );
			
			Map<String, RenewalConfigVO> mapOfRenewalVoOnPatCatGroupKey = (Map<String, RenewalConfigVO>)mp.get(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO);
			
			// Setting in Session
			mpToSetInSession.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT, mp.get(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT));
			mpToSetInSession.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY, mp.get(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY));
			mpToSetInSession.put(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO, mapOfRenewalVoOnPatCatGroupKey);
			
			
			// Creating Json Object for XML Node
			createDepartmentListObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT),responseDocument,"departmentCode");
			createPatientCategoryListObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY),responseDocument,"patPrimaryCatCode");
			
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS),responseDocument,"patMaritalStatusCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_GENDER),responseDocument,"patGenderCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION),responseDocument,"patReligionCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_CASTE),responseDocument,"patCasteCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY),responseDocument,"patAddCountryCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_STATE),responseDocument,"patAddStateCode");
			
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY),responseDocument,"patIsUrban");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE),responseDocument,"patAgeUnit");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL),responseDocument,"patRefGnctdHospitalCode");
			
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL),responseDocument,"patRefGnctdHospitalDept");
			//createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL),responseDocument,"countryCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL),responseDocument,"patOccupation");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE),responseDocument,"patAddDistrictCode");
			
			createVerificationObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS),responseDocument,"patDocType");
			
			//createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_CLIENT_OPTION_LIST),responseDocument,"clientCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL),responseDocument,"relationWithStaff");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE),responseDocument,"agsDistrictCode");

			WebUTIL.setMapInSession(mpToSetInSession,objRequest,"ExternalPatientRegistrationAction");
			
			
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
			
			List lstState=ExternalPatientRegistrationDATA.getState_AJAX(userVO,strCountryCode);
			
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
			
			List lstDistrict=ExternalPatientRegistrationDATA.getDistrict_AJAX(userVO,strStateCode,strCountryCode);
			
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
			
			List lstRefDept=ExternalPatientRegistrationDATA.getRefDept_AJAX(userVO, strRefHospCode);
			
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
								
			List lstDocList=ExternalPatientRegistrationDATA.getVerDocExceptCatDoc_AJAX(userVO, strDocCode);
			
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
			
			if(strAlreadyRegistrdFlag!=null && !strAlreadyRegistrdFlag.equals("") && !strAlreadyRegistrdFlag.equals("0"))
				strMode= strAlreadyRegistrdFlag;
			
			UserVO userVO=getUserVO(objRequest);
			
			if(patPrimaryCatCode!=null && !patPrimaryCatCode.equals(""))
				lstPatientJsonObjString=ExternalPatientRegistrationDATA.getPatDtlOnPatCatId(userVO,patPrimaryCatCode,strSearchCatName,strSearchCatId,strMode);
			
			
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
			option.setAttribute("value", "{\"patPrimaryCatCode\":\""+entryValueArray[0]+"\",\"patPrimaryCatName\": \""+entryValueArray[1]+"\",\"charges\":\""+entryValueArray[2]+"\",\"idReqFlag\":\""+entryValueArray[3]+"\",\"idSize\":\""+entryValueArray[4]+"\",\"idValidationType\":\""+entryValueArray[5]+"\",\"expiryFlag\":\""+entryValueArray[6]+"\",\"expiryDays\":\""+entryValueArray[7]+"\",\"expiryMonth\":\""+entryValueArray[8]+"\",\"patCatGroup\":\""+entryValueArray[9]+"\",\"patCatShortName\":\""+entryValueArray[10]+"\",\"patCatDocCode\":\""+entryValueArray[11]+"\",\"patCatDocIsAlternateId\":\""+entryValueArray[12]+"\",\"clientCode\":\""+entryValueArray[13]+"\",\"clientName\":\""+entryValueArray[14]+"\"}");
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
	

	
	/**
	 * saves New Patient Registration Details
	 * generates a String of Queue, Departement, unit and room 
	 * @param _request -HttpServletRequest
	 * @param objExternalPatRegSupp_p -NewRegistrationFBDuplicate form bean
	 * @return return true if any duplicate record found against the patient detail
	 * else save the patient details and register and return false
	 */
	public static void saveNewPatientRegistration(ExternalPatientRegistrationSUP objExternalPatRegSupp_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p ){
		
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
		boolean flagSaveMsgObjCreated=false;
		String strBillPrintDiv="";
		
		
		
		try
		{
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			//gets the hospital information
			HospitalMstVO objHospitalMstVO = getHospitalVO(objRequest_p);
			
			PatientIdVO objPatientIdVO = new PatientIdVO();
			objPatientIdVO.setPatIdNo(objExternalPatRegSupp_p.getPatCardNo());
			objPatientIdVO.setPatDocType(objExternalPatRegSupp_p.getPatDocType());
			String strUniqueIdDuplicyFlag = "0";
			
			if(objExternalPatRegSupp_p.getIsIdRequired()!=null && objExternalPatRegSupp_p.getIsIdRequired().equals("2"))
				objExternalPatRegSupp_p.setPatIdNo(objExternalPatRegSupp_p.getHiddenPatIdNo());
			
			if(objExternalPatRegSupp_p.getPatRefGnctdHospitalCode()!=null && !objExternalPatRegSupp_p.getPatRefGnctdHospitalCode().equals(""))
				objExternalPatRegSupp_p.setPatRefGnctdHospitalCode(objExternalPatRegSupp_p.getPatRefGnctdHospitalCode().split("\\#")[0]);
			System.out.println("PatRefGnctdHospitalCode :"+objExternalPatRegSupp_p.getPatRefGnctdHospitalCode());
			
			//To Check the Duplicacy on the National Id/Adhaar Id Basis
			if((objExternalPatRegSupp_p.getPatNationalId()!=null && !objExternalPatRegSupp_p.getPatNationalId().equals("")) || (objExternalPatRegSupp_p.getPatCardNo()!=null && !objExternalPatRegSupp_p.getPatCardNo().equals("")))
			{				
				objPatientIdVO.setPatIdNo(objExternalPatRegSupp_p.getPatCardNo());
				objPatientIdVO.setPatDocType(objExternalPatRegSupp_p.getPatDocType());
				strUniqueIdDuplicyFlag = ExternalPatientRegistrationDATA.checkUniqueIdDuplicy(userVO, objPatientIdVO,objExternalPatRegSupp_p.getPatNationalId());
					
				if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
					if(strUniqueIdDuplicyFlag.equals("1")){
						strErrMsg="Duplicate National Id/ Aadhar No";
						flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
						return;
					}else if(strUniqueIdDuplicyFlag.equals("2")){
						strErrMsg="Patient with this "+objExternalPatRegSupp_p.getPatDocTypeName()+"("+objExternalPatRegSupp_p.getPatCardNo()+ ") already registered.";
						flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
						return;
					}
				}
			
			}	
			
			//To Check the Duplicacy on the Secondary Alternate Id Basis
			if((objExternalPatRegSupp_p.getPatIdNo()!=null && !objExternalPatRegSupp_p.getPatIdNo().equals("") && objExternalPatRegSupp_p.getPatCatDocIsAlternateId().equals("1")))
			{
				objPatientIdVO.setPatIdNo(objExternalPatRegSupp_p.getPatIdNo());
				objPatientIdVO.setPatDocType(objExternalPatRegSupp_p.getPatCatDocCode());
				strUniqueIdDuplicyFlag = ExternalPatientRegistrationDATA.checkUniqueIdDuplicy(userVO, objPatientIdVO,objExternalPatRegSupp_p.getPatNationalId());
					
				if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
					if(strUniqueIdDuplicyFlag.equals("1")){
						strErrMsg="Duplicate National Id/ Aadhar No";
						flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
						return;
					}else if(strUniqueIdDuplicyFlag.equals("2")){
						strErrMsg="Patient with this Id No ("+objExternalPatRegSupp_p.getPatIdNo()+ ") already registered.";
						flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
						return;
					}
				}
			
			}			
					
			//For validating the pat amount collected
			/*if(objExternalPatRegSupp_p.getPatAmountCollected()==null || objExternalPatRegSupp_p.getPatAmountCollected().equals(""))
			{
				strErrMsg= "Registration Fee cannot be empty.\nPlease Enter this.";
				flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
				//objExternalPatRegSupp_p.setErrorMessage("Registration Fee cannot be empty.\nPlease relogin.");
				return ;
			}*/
			
			setSysdateAndDefaultCrNoFormat(objRequest_p);
			//isReferredDecider(objExternalPatRegSupp_p,objRequest_p);
				
			if(objExternalPatRegSupp_p.getPatRefGnctdHospitalDept().equals("0")){
				objExternalPatRegSupp_p.setPatRefGnctdHospitalDept(objExternalPatRegSupp_p.getPatRefHospitalDeptOther());
			}
			if(objExternalPatRegSupp_p.getPatAddState()!=null && objExternalPatRegSupp_p.getPatAddState().indexOf(',')!=-1){
				String[] strArrPatAddState= objExternalPatRegSupp_p.getPatAddState().split("\\,"); 
				if(!strArrPatAddState[0].trim().equals(""))
					objExternalPatRegSupp_p.setPatAddState(strArrPatAddState[0].trim());
				else
					objExternalPatRegSupp_p.setPatAddState(strArrPatAddState[1].trim());
			}
			
			if(objExternalPatRegSupp_p.getPatAddDistrict()!=null && objExternalPatRegSupp_p.getPatAddDistrict().indexOf(',')!=-1){
				String[] strArrPatAddDistrict= objExternalPatRegSupp_p.getPatAddDistrict().split("\\,"); 
				if(!strArrPatAddDistrict[0].trim().equals(""))
					objExternalPatRegSupp_p.setPatAddDistrict(strArrPatAddDistrict[0].trim());
				else
					objExternalPatRegSupp_p.setPatAddDistrict(strArrPatAddDistrict[1].trim());
			}
			
			setNotMandatoryDefaultCmbValue(objExternalPatRegSupp_p);
			PatientVO patientVO = new PatientVO();	
			HelperMethods.populate(patientVO, objExternalPatRegSupp_p);
			
			Map<String, RenewalConfigVO> mapOfRenewalVoOnPatCatGroupKey= (Map<String, RenewalConfigVO>)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO);
			objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get(objExternalPatRegSupp_p.getPatPrimaryCatCode());
			if(objRenewalConfigVO==null)
				objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get("10");
			patientVO.setRenewalConfig(objRenewalConfigVO);
			patientVO.getRenewalConfig().setStrRenewalGroup(RegistrationConfig.RENEWAL_CONFIG_GROUP_OPD);
			
			patientVO.setPatIsDead("0");
			patientVO.setPatIsMerged("0");
			patientVO.setTariffId(userVO.getTariffID());
			patientVO.setPatIdNo(objExternalPatRegSupp_p.getPatIdNo());
			
			HelperMethods.populate(patientVO.getPatAddress(), objExternalPatRegSupp_p);
			patientVO.getPatAddress().setPatVerificationStatus("2");
			if(objExternalPatRegSupp_p.getPatAddCountryCode()!=null && objExternalPatRegSupp_p.getPatAddCountryCode().equals(RegistrationConfig.COUNTRY_CODE_INDIA))
				patientVO.setPatNationalityCode("1");
			else
				patientVO.setPatNationalityCode("0");
			
			if(objHospitalMstVO.getDistrictCode()!=null && objExternalPatRegSupp_p.getPatAddDistrictCode()!=null && objHospitalMstVO.getDistrictCode().equals(objExternalPatRegSupp_p.getPatAddDistrictCode()) )
				patientVO.getPatAddress().setPatIsLocal("1");
			else
				patientVO.getPatAddress().setPatIsLocal("0");
				
			if(objExternalPatRegSupp_p.getPatRefGnctdHospitalDept().equals("0"))
				patientVO.setPatRefGnctdHospitalDept(objExternalPatRegSupp_p.getPatRefHospitalDeptOther());
			if(objExternalPatRegSupp_p.getIsReferred().equals(""))
				patientVO.setIsReferred("0");
			
			
			String[] deptToVisit = objExternalPatRegSupp_p.getDepartmentsToVisitStamp();
		
			EpisodeVO[] arrEpisodeVO;		
				
			Collection colDept		=(Collection)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT);
			//Collection colPrimaryCat=(Collection)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY);
			//HospitalMstVO objHospitalMstVO= (HospitalMstVO)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_VO_RENEWEL_CONFIG);
			
			String deptName="";
			//for radiotherapy
			String referDeptname=objExternalPatRegSupp_p.getPatRefGnctdHospitalDept();
			
			if(deptToVisit== null || deptToVisit.length==0)
			{
				arrEpisodeVO = new EpisodeVO[1];
				
				arrEpisodeVO[0]=new EpisodeVO();
				setDepartmentDtl(colDept, patientVO, objExternalPatRegSupp_p.getDepartmentCode());
				arrEpisodeVO[0].setDepartmentCode(objExternalPatRegSupp_p.getDepartmentCode());
				arrEpisodeVO[0].setDepartmentUnitCode(patientVO.getDepartmentUnitCode());
				
				//deptName=getEntryLabel(colDept,objExternalPatRegSupp_p.getDepartmentCode());
				//since department contains department name plus unit name
				//arrEpisodeVO[0].setDepartment(deptName);
				//arrEpisodeVO[0].setDepartment(deptName);
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
			
			/////setting vo in session for dupllicate registration or new department visit
			//WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, arrEpisodeVO);
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.REGISTRATIONDESK_PATIENT_VO, patientVO);
			
			PatientVO objPatientVO=ExternalPatientRegistrationDATA.registerNewPatient(patientVO,userVO,objRequest_p);
			arrEpisodeVO[0].setPatCrNo(objPatientVO.getPatCrNo());
			if(objExternalPatRegSupp_p.getIsActualDob().equals("1"))
				objExternalPatRegSupp_p.setPatAge(objPatientVO.getPatAge());
				
			strIsSavedSuccussfulFlag="1";
			strSavedOrErrMsg="Patient Registered";
			strIsFormFieldResetFlag="1";
			String tmpFileName=RegistrationConfig.CARD_NEW_REGISTRATION+userVO.getSeatId();
			
			strPrintDivContent="";
			strPrintDivContent=NewRegistrationSlip.print(preapareSlip(arrEpisodeVO,objPatientVO,objExternalPatRegSupp_p, objRequest_p), tmpFileName,objRequest_p,"NR");
			
			System.out.println("PrintSlip :"+strPrintDivContent);
			
			System.out.println("Amount COllected :"+objExternalPatRegSupp_p.getPatAmountCollected());
			System.out.println("Actual Amount :"+objExternalPatRegSupp_p.getPatActualAmount());
			//Bill Receipt Printing 
			if((objExternalPatRegSupp_p.getPatAmountCollected()!=null)&&!(objExternalPatRegSupp_p.getPatAmountCollected().equals("0"))&&!(objExternalPatRegSupp_p.getPatAmountCollected().equalsIgnoreCase("0.00")))
			{
				strBillPrintDiv=NewRegistrationSlip.printBillReceipt(preapareSlip(arrEpisodeVO,objPatientVO,objExternalPatRegSupp_p, objRequest_p), tmpFileName,objRequest_p,"NR");
				System.out.println("PrintBillSlip :"+strBillPrintDiv);
				WebUTIL.setAttributeInSession(objRequest_p,"billReceiptString", strBillPrintDiv);
				strPrintDivContent+="<br><br>"+strBillPrintDiv+"";
			}
			
			System.out.println("FullSlip :"+strPrintDivContent);
			flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strSavedOrErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"1",strIsDuplicatePatient);

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
	
	private static void setPrimaryCatDtl(Collection objCol_p, ExternalPatientRegistrationSUP objExternalPatRegSupp_p,  String strPrimaryCatCode_p){
		
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
				objExternalPatRegSupp_p.setPatPrimaryCat(strPrimaryCatValArray[1]);
				
				break;
				//return objEntry.getLabel();		
			}
		}		
		//System.out.println("getEntryLabel:  "+)
	}
	
	private static void setNotMandatoryDefaultCmbValue(ExternalPatientRegistrationSUP objExternalPatRegSupp_p){
		
		objExternalPatRegSupp_p.setPatMaritalStatusCode(objExternalPatRegSupp_p.getPatMaritalStatusCode().equals("-1")?"":objExternalPatRegSupp_p.getPatMaritalStatusCode());	
		objExternalPatRegSupp_p.setPatCasteCode(objExternalPatRegSupp_p.getPatCasteCode().equals("-1")?"":objExternalPatRegSupp_p.getPatCasteCode());
		objExternalPatRegSupp_p.setPatReligionCode(objExternalPatRegSupp_p.getPatReligionCode().equals("-1")?"":objExternalPatRegSupp_p.getPatReligionCode());
		objExternalPatRegSupp_p.setPatOccupation(objExternalPatRegSupp_p.getPatOccupation().equals("-1")?"":objExternalPatRegSupp_p.getPatOccupation());
		objExternalPatRegSupp_p.setPatIsUrban(objExternalPatRegSupp_p.getPatIsUrban().equals("-1")?"":objExternalPatRegSupp_p.getPatIsUrban());
		objExternalPatRegSupp_p.setPatAddPhoneOwner(objExternalPatRegSupp_p.getPatAddPhoneOwner().equals("-1")?"":objExternalPatRegSupp_p.getPatAddPhoneOwner());
		objExternalPatRegSupp_p.setPatRefGnctdHospitalCode(objExternalPatRegSupp_p.getPatRefGnctdHospitalCode().equals("-1")?"":objExternalPatRegSupp_p.getPatRefGnctdHospitalCode());
		objExternalPatRegSupp_p.setPatRefGnctdHospitalDept(objExternalPatRegSupp_p.getPatRefGnctdHospitalDept().equals("-1")?"":objExternalPatRegSupp_p.getPatRefGnctdHospitalDept());
		objExternalPatRegSupp_p.setPatAgeUnit(objExternalPatRegSupp_p.getPatAgeUnit().equals("-1")?"":objExternalPatRegSupp_p.getPatAgeUnit());
		objExternalPatRegSupp_p.setPatDocType(objExternalPatRegSupp_p.getPatDocType().equals("-1")?"":objExternalPatRegSupp_p.getPatDocType());
		
		//System.out.println("getEntryLabel:  "+)
	}
	
	public static RegistrationSlip preapareSlip(EpisodeVO episodeVO[],PatientVO _patVO,ExternalPatientRegistrationSUP objExternalPatRegSupp_p,HttpServletRequest _request){		
		
		ExternalPatientRegistrationDATA dataReg_op=new ExternalPatientRegistrationDATA();
		UserVO _userVO=ControllerUTIL.getUserVO(objExternalPatRegSupp_p.getObjRequest());
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

			
		String primaryCatName=objExternalPatRegSupp_p.getPatPrimaryCat();
		regSlip.setPatPrimaryCat(primaryCatName);				
		regSlip.setPatGender(objExternalPatRegSupp_p.getPatGender());
		//System.out.println("-------------"+objExternalPatRegSupp_p.getPatOccupation()+"--------------------");
		regSlip.setPatOccupation("");
		//System.out.println("-------------"+regSlip.getPatOccupation()+"--------------------");
		//System.out.println("-------------"+objExternalPatRegSupp_p.getPatCasteCode()+"--------------------");
		regSlip.setPatCasteCode(objExternalPatRegSupp_p.getPatCaste());
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
		
		if(!(patMiddleName==null || patMiddleName.equals("")))
		{
			patMiddleName=patMiddleName.substring(0,1).toUpperCase();
			regSlip.setPatMiddleName(patMiddleName);
		}
		
		////Address For card
		String cityLocation="";
		String district="";
		String state="";
	
		String country=objExternalPatRegSupp_p.getPatAddCountry();
		
		cityLocation=objExternalPatRegSupp_p.getPatAddCity();
		if(_patVO.getPatAddDistrictCode()==null || _patVO.getPatAddDistrictCode().equals(""))
			district=_patVO.getPatAddDistrict();
		else
			district=objExternalPatRegSupp_p.getPatAddDistrict();
		
			state=objExternalPatRegSupp_p.getPatAddState();
		
		//inorder to prevent null showing for the location textbox on the print slip
		cityLocation=cityLocation==null?" ":cityLocation;
		
		
		String address=_patVO.getPatAddHNo();
		address=address+" "+_patVO.getPatAddStreet();
		address=address+" "+cityLocation;
		
		address=address+" " +_patVO.getPatAddCity();
		if(district.equals("-1"))
			district="";
			String address2=district;
	
		if(!address2.equals(""))
			address2=address2+" "+state;
		else
			address2=state;
		
			address2=address2+" "+country;
			if(_patVO.getPatAddPIN() != null && _patVO.getPatAddPIN().equals(""))
			address2=address2+" "+_patVO.getPatAddPIN();
			
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
			
			/*String[] temp=episodeVO[i].getUnitWorkingDays().split("#");
			if(temp.length==0)
				temp=new String[]{"",""};
			System.out.println("temp[0]"+temp[0]);
			System.out.println("temp[1]"+temp[1]);
			workingDays[i]=temp[0];
			timing[i]=temp[1];*/
			workingDays[i]=episodeVO[i].getUnitWorkingDays();
			//episodeDate[i]=episodeVO[i].getEntryDate();
			episodeDate[i]=(String)_request.getSession().getAttribute(Config.SYSDATE);
			/*Calendar cal = Calendar.getInstance(); 
			String DATE_FORMAT = "dd/MM/yyyy HH:MM";
		    SimpleDateFormat sdf =new SimpleDateFormat(DATE_FORMAT);
			String date=sdf.format(cal.getTime());
			episodeDate[i]=date;*/
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
		
		//For Bill Receipt 
		@SuppressWarnings("static-access")
		String counterName=dataReg_op.getCounterName(_userVO,_patVO.getSystemIPAddress());
		regSlip.setBillNo(billNo);
		regSlip.setCounterName(counterName);	
		return regSlip;		
	}
	public static boolean validateSavePatDtl(ExternalPatientRegistrationSUP objPatSup_p,HttpServletResponse objResponse_p) {
		List lstErrMsg=new ArrayList();
		boolean flagValidationError=false;
		System.out.println("NewRegistrationUTIL :: validateSavePatDtl()");
		System.out.println("objPatSup_p.getPatFirstName() :"+objPatSup_p.getPatFirstName());
		/*if(objPatSup_p.getDepartmentCode().equals("-1") ||  objPatSup_p.getDepartmentCode() == null ||objPatSup_p.getDepartmentCode()=="" ){
			lstErrMsg.add("Visiting Department is Required");
		}
		else if(!objPatSup_p.getDepartmentCode().matches("^([1-9][0-9][1-9])$")){
			lstErrMsg.add("Visiting Department is Required. It Accepts Numeric Values Between 101 To 999 Only.");
		}
		if(objPatSup_p.getPatPrimaryCatCode().equals("-1") || objPatSup_p.getPatPrimaryCatCode() == null ||  objPatSup_p.getPatPrimaryCatCode()=="" ){
			lstErrMsg.add("Patient Category is Required");
		}
		else if(!objPatSup_p.getPatPrimaryCatCode().matches("^([1-9][0-9])$"))
		{
			lstErrMsg.add("Patient Category Is Required. It Accepts Numeric Values Between 11 To 99 Only.");
		}*/
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
			else if(!objPatSup_p.getPatAge().matches("^([1-9]|[1-9][0-9]|1[0-2][0-5])$"))
			{
				lstErrMsg.add("Age Is Required. It Accept Numeric Value Between 1 to 125 Only.");
			}
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
		else if(!objPatSup_p.getPatGenderCode().matches("^(F|I|M|N|T)$"))
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
			else if(!objPatSup_p.getPatAddDistrictCode().matches("^[0-9]{2,3}$"))
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
			else if(!objPatSup_p.getPatRefDoctor().matches("^[a-zA-Z]*"))
			{
				lstErrMsg.add("Docter Name Is Required. It Accept Alphabets Only.");
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
	
}//end of class