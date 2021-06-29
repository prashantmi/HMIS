package hr.pis.empReg.transactions.controller.util;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisInvalidTokenNumberException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;
import hr.pis.config.PisConfig;
import hr.pis.empReg.config.PrConfig;
import hr.pis.empReg.config.slip.EmpRegistrationSlip;
import hr.pis.empReg.transactions.controller.actionsupport.EmpRegistrationSUP;
import hr.pis.empReg.transactions.controller.data.EmpRegistrationDATA;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.JSONValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import vo.hr.pis.empReg.transactions.EmpRegistrationVO;
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


public class EmpRegistrationUTIL extends ControllerUTIL
{
/**
 * sets all new registration essentials
 * @param _request -HttpServletRequest
 */
			
	public static void populateRegistrationFormEssentials_AJAX(EmpRegistrationSUP objEmployeeRegistrationSUP,HttpServletRequest objRequest,
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
		
		try
		{
			/*String NatureOfJobId=(String)objRequest.getParameter("natureOfJobId");
			
			System.out.println("natureOfJobIdin UTIL->"+NatureOfJobId);*/
			
			UserVO userVO=getUserVO(objRequest);
			userVO.setModuleId(PisConfig.MODULE_ID_PIS);
			//userVO.setTariffID(JoiningConfig.NEW_REGISTRATION_TARIFF_ID);
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			//Creating message Element specially for Error Message
			msgElement=responseDocument.createElement("msgs");
			rootElement.appendChild(msgElement);
			msgElement.setAttribute("msg", strErrMsg);
			
			
			Element defaultElement=responseDocument.createElement("defaults");
			rootElement.appendChild(defaultElement);
			
			defaultElement.setAttribute("defaultintNationalityId", PrConfig.EMPLOYEEREGISTRATION_DEFAULT_NATIONALITY_ID);
			defaultElement.setAttribute("defaultstrEmployeeFinalStatus", PrConfig.EMPLOYEEREGISTRATION_DEFAULT_EMPLOYEE_FINAL_STATUS_CODE);
			defaultElement.setAttribute("defaultstrLastEmploymentType", PrConfig.EMPLOYEEREGISTRATION_DEFAULT_EMPLOYEE_LAST_EMPLOYMENt_TYPE);
			defaultElement.setAttribute("defaultintNatureOfJobId", PrConfig.EMPLOYEEREGISTRATION_DEFAULT_EMPLOYEE_NATURE_OF_JOB);
			//defaultElement.setAttribute("defaultpatAddDistrictCode", JoiningConfig.REGISTRATIONDESK_DEFAULT_DISTRICT_CODE);
			
			//mp=EmpRegistrationDATA.getRegistrationFormEssentials_AJAX(userVO);
			mp=EmpRegistrationDATA.getRegistrationFormEssentials_AJAX(userVO);
			
			mpToSetInSession.put(PrConfig.ESSENTIAL_BO_OPTION_DEPARTMENT, mp.get(PrConfig.ESSENTIAL_BO_OPTION_DEPARTMENT));
			mpToSetInSession.put(PrConfig.ESSENTIAL_BO_OPTION_NATURE_OF_JOB, mp.get(PrConfig.ESSENTIAL_BO_OPTION_NATURE_OF_JOB));
			mpToSetInSession.put(PrConfig.ESSENTIAL_BO_OPTION_APPELLATION1, mp.get(PrConfig.ESSENTIAL_BO_OPTION_APPELLATION1));
			mpToSetInSession.put(PrConfig.ESSENTIAL_BO_OPTION_APPELLATION2, mp.get(PrConfig.ESSENTIAL_BO_OPTION_APPELLATION2));
			mpToSetInSession.put(PrConfig.ESSENTIAL_BO_OPTION_SUFFIX, mp.get(PrConfig.ESSENTIAL_BO_OPTION_SUFFIX));
			mpToSetInSession.put(PrConfig.ESSENTIAL_BO_OPTION_NATIONALITY, mp.get(PrConfig.ESSENTIAL_BO_OPTION_NATIONALITY));
			mpToSetInSession.put(PrConfig.ESSENTIAL_BO_OPTION_GENDER, mp.get(PrConfig.ESSENTIAL_BO_OPTION_GENDER));
			mpToSetInSession.put(PrConfig.ESSENTIAL_BO_OPTION_SERVICE_GROUP, mp.get(PrConfig.ESSENTIAL_BO_OPTION_SERVICE_GROUP));
			mpToSetInSession.put(PrConfig.ESSENTIAL_BO_OPTION_DESIGNATION, mp.get(PrConfig.ESSENTIAL_BO_OPTION_DESIGNATION));
			mpToSetInSession.put(PrConfig.ESSENTIAL_BO_OPTION_STATUS, mp.get(PrConfig.ESSENTIAL_BO_OPTION_STATUS));
			mpToSetInSession.put(PrConfig.ESSENTIAL_BO_OPTION_FINAL_STATUS, mp.get(PrConfig.ESSENTIAL_BO_OPTION_FINAL_STATUS));
			mpToSetInSession.put(PrConfig.ESSENTIAL_BO_OPTION_LAST_EMPLOYMENT_TYPE, mp.get(PrConfig.ESSENTIAL_BO_OPTION_LAST_EMPLOYMENT_TYPE));
			
			
			createOptionObject((List<Entry>)mp.get(PrConfig.ESSENTIAL_BO_OPTION_DEPARTMENT),responseDocument,"intDepartmentCode");
			createOptionObject((List<Entry>)mp.get(PrConfig.ESSENTIAL_BO_OPTION_NATURE_OF_JOB),responseDocument,"intNatureOfJobId");
			createOptionObject((List<Entry>)mp.get(PrConfig.ESSENTIAL_BO_OPTION_APPELLATION1),responseDocument,"intAppellationCode1");
			createOptionObject((List<Entry>)mp.get(PrConfig.ESSENTIAL_BO_OPTION_APPELLATION2),responseDocument,"intAppellationCode2");
			createOptionObject((List<Entry>)mp.get(PrConfig.ESSENTIAL_BO_OPTION_SUFFIX),responseDocument,"intSuffixCode");
			createOptionObject((List<Entry>)mp.get(PrConfig.ESSENTIAL_BO_OPTION_NATIONALITY),responseDocument,"intNationalityId");
			createOptionObject((List<Entry>)mp.get(PrConfig.ESSENTIAL_BO_OPTION_GENDER),responseDocument,"strGenderCode");
			createOptionObject((List<Entry>)mp.get(PrConfig.ESSENTIAL_BO_OPTION_SERVICE_GROUP),responseDocument,"strServiceGroupCode");
			createOptionObject((List<Entry>)mp.get(PrConfig.ESSENTIAL_BO_OPTION_DESIGNATION),responseDocument,"intDesignationCode");
			createOptionObject((List<Entry>)mp.get(PrConfig.ESSENTIAL_BO_OPTION_STATUS),responseDocument,"strEmployeeStatus");
			createOptionObject((List<Entry>)mp.get(PrConfig.ESSENTIAL_BO_OPTION_FINAL_STATUS),responseDocument,"strEmployeeFinalStatus");
			createOptionObject((List<Entry>)mp.get(PrConfig.ESSENTIAL_BO_OPTION_LAST_EMPLOYMENT_TYPE),responseDocument,"strLastEmploymentType");
			
			
			WebUTIL.setMapInSession(mpToSetInSession,objRequest);
			
			java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
			trf.newTransformer().transform( new DOMSource(responseDocument),new StreamResult(baos));
			outputString=baos.toString();
			System.out.println("outputString "+outputString);
		}
		
		catch(Exception e){
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			
			writeResponse(objResponse, outputString);
		}	
	}
	
	public static void getDeptDesigFinalStatus_AJAX(EmpRegistrationSUP objEmployeeRegistrationSUP,HttpServletRequest objRequest,
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
		
		try
		{
			 String NatureOfJobId=(String)objRequest.getParameter("natureOfJobId");
			System.out.println("natureOfJobIdin UTIL->"+NatureOfJobId);
			
			UserVO userVO=getUserVO(objRequest);
			userVO.setModuleId(PisConfig.MODULE_ID_PIS);
			//userVO.setTariffID(JoiningConfig.NEW_REGISTRATION_TARIFF_ID);
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			//Creating message Element specially for Error Message
			msgElement=responseDocument.createElement("msgs");
			rootElement.appendChild(msgElement);
			msgElement.setAttribute("msg", strErrMsg);
			
			
			Element defaultElement=responseDocument.createElement("defaults");
			rootElement.appendChild(defaultElement);
			
			defaultElement.setAttribute("defaultintNationalityId", PrConfig.EMPLOYEEREGISTRATION_DEFAULT_NATIONALITY_ID);
			defaultElement.setAttribute("defaultstrEmployeeFinalStatus", PrConfig.EMPLOYEEREGISTRATION_DEFAULT_EMPLOYEE_FINAL_STATUS_CODE);
			//defaultElement.setAttribute("defaultpatAddDistrictCode", JoiningConfig.REGISTRATIONDESK_DEFAULT_DISTRICT_CODE);
			
			//mp=EmpRegistrationDATA.getRegistrationFormEssentials_AJAX(userVO);
			mp=EmpRegistrationDATA.getDeptDesigFinalStatus_AJAX(userVO,NatureOfJobId);
			
			mpToSetInSession.put(PrConfig.ESSENTIAL_BO_OPTION_DEPARTMENT, mp.get(PrConfig.ESSENTIAL_BO_OPTION_DEPARTMENT));
			mpToSetInSession.put(PrConfig.ESSENTIAL_BO_OPTION_DESIGNATION, mp.get(PrConfig.ESSENTIAL_BO_OPTION_DESIGNATION));
			mpToSetInSession.put(PrConfig.ESSENTIAL_BO_OPTION_FINAL_STATUS, mp.get(PrConfig.ESSENTIAL_BO_OPTION_FINAL_STATUS));
				
			
			createOptionObject((List<Entry>)mp.get(PrConfig.ESSENTIAL_BO_OPTION_DEPARTMENT),responseDocument,"intDepartmentCode");	
			createOptionObject((List<Entry>)mp.get(PrConfig.ESSENTIAL_BO_OPTION_DESIGNATION),responseDocument,"intDesignationCode");		
			createOptionObject((List<Entry>)mp.get(PrConfig.ESSENTIAL_BO_OPTION_FINAL_STATUS),responseDocument,"strEmployeeFinalStatus");
			
			
			
			WebUTIL.setMapInSession(mpToSetInSession,objRequest);
			
			java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
			trf.newTransformer().transform( new DOMSource(responseDocument),new StreamResult(baos));
			outputString=baos.toString();
			System.out.println("outputString "+outputString);
		}
		
		catch(Exception e){
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			
			writeResponse(objResponse, outputString);
		}	
	}
	
	public static void writeResponse(HttpServletResponse resp, String output){
		try{
			resp.reset();
			resp.setContentType("text/xml");
			resp.setCharacterEncoding("UTF-8");
			resp.setHeader("Cache-Control", "no-cache");
			System.out.println(output);
		resp.getWriter().write(output);
		}
		catch(Exception e){
			System.out.println(e);
		}
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
	
	
	
/*public static void getDeptDesigFinalStatus_AJAX(HttpServletRequest objRequest, HttpServletResponse objResponse,Map<Object, Object> mapSession) {
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		String outputString="";
		Map mp=new HashMap();
		Map<String, ?> mpToSetInSession=new HashMap();
		try{
            String NatureOfJobId=(String)objRequest.getParameter("natureOfJobId");
			System.out.println("natureOfJobIdin UTIL->"+NatureOfJobId);
			
			UserVO userVO=getUserVO(objRequest);
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			mp=EmpRegistrationDATA.getDeptDesigFinalStatus_AJAX(userVO,NatureOfJobId);
			
			mpToSetInSession.put(PrConfig.ESSENTIAL_BO_OPTION_DEPARTMENT, mp.get(PrConfig.ESSENTIAL_BO_OPTION_DEPARTMENT));
			
			createOptionObject((List<Entry>)mp.get(PrConfig.ESSENTIAL_BO_OPTION_DEPARTMENT),responseDocument,"intDepartmentCode");
			
			mpToSetInSession.put(PrConfig.ESSENTIAL_BO_OPTION_DESIGNATION, mp.get(PrConfig.ESSENTIAL_BO_OPTION_DESIGNATION));
			
			createOptionObject((List<Entry>)mp.get(PrConfig.ESSENTIAL_BO_OPTION_DESIGNATION),responseDocument,"intDesignationCode");
			
			List lstDepartment=EmpRegistrationDATA.getDeptDesigFinalStatus_AJAX(userVO,NatureOfJobId);
			
			createOptionObject((List<Entry>)lstDepartment,responseDocument,"intDepartmentCode");
			
            List lstDesignation=EmpRegistrationDATA.getDesig_AJAX(userVO,NatureOfJobId);
			
			createOptionObject((List<Entry>)lstDesignation,responseDocument,"intDesignationCode");
			
            WebUTIL.setMapInSession(mpToSetInSession,objRequest);
			
			java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
			trf.newTransformer().transform( new DOMSource(responseDocument),new StreamResult(baos));
			outputString=baos.toString(); 
			System.out.println("outputString "+outputString);
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
*/


	
	
	public static void saveEmployeeRegistration(EmpRegistrationSUP objEmpRegSupp_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p )
	{
		
		System.out.println("EmployeeRegistrationUTIL :: saveEmployeeRegistration()");
		
		boolean exists=false;
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		String outputString="";
		
		UserVO userVO=getUserVO(objRequest_p);
		//RenewalConfigVO objRenewalConfigVO=null;
		
		//userVO.setTariffID(RegistrationConfig.NEW_REGISTRATION_TARIFF_ID);
		userVO.setModuleId(PisConfig.MODULE_ID_PIS);
		Status objStatus =new Status();	
		
		String strIsSavedSuccussfulFlag="0", strSavedOrErrMsg="",strIsFormFieldResetFlag="0", strPrintDivContent="", strErrMsg="";
		boolean flagSaveMsgObjCreated=false;
		
		try
		{
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			//gets the hospital information
			HospitalMstVO objHospitalMstVO = getHospitalVO(objRequest_p);
			
			setSysdate(objRequest_p);
			//isReferredDecider(objNewPatRegSupp_p,objRequest_p);
			
			setNotMandatoryDefaultCmbValue(objEmpRegSupp_p);
			
			EmpRegistrationVO employeeRegisterVO = new EmpRegistrationVO();	
			HelperMethods.populate(employeeRegisterVO, objEmpRegSupp_p);
			
			//EpisodeVO episodeVO[]=NewRegistrationDATA.registerNewPatient(patientVO,arrEpisodeVO,userVO,objRequest_p);
			EmpRegistrationDATA.registerNewEmployee(employeeRegisterVO,userVO,objRequest_p);
			System.out.println("EmployeeRegistrationUTIL :: saveEmployeeRegistration()  ::  Employee Number = "+employeeRegisterVO.getStrEmployeeNumber());
			
			EmpRegistrationVO employeeRegisterVOForPrintData=EmpRegistrationDATA.getEmployeeDataForPrint(employeeRegisterVO, userVO);
			
			strIsSavedSuccussfulFlag="1";
			strSavedOrErrMsg="Employee Registered with Emp. No. - "+employeeRegisterVO.getStrEmployeeNumber();
			strIsFormFieldResetFlag="1";
			//String tmpFileName=JoiningConfig.CARD_NEW_REGISTRATION+userVO.getSeatId();
			
			//strPrintDivContent="";
			HelperMethods.setNullToEmpty(employeeRegisterVO);
			strPrintDivContent=EmpRegistrationSlip.print(employeeRegisterVO, objHospitalMstVO, objRequest_p);
			
			System.out.println("PrintSlip :"+strPrintDivContent);
			
			flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strSavedOrErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"1");
		}
		catch(HisDuplicateRecordException e){
			strErrMsg = e.getMessage();
		}
		catch(HisInvalidTokenNumberException e){
			strErrMsg = "INVALID TOKEN NUMBER";
		}
		catch(HisUpdateUnsuccesfullException e){
			strErrMsg = "Update Unsuccessful";
		}		
		catch(HisDataAccessException e){
			strErrMsg = "Registration Failed";
			objStatus.add(Status.ERROR_DA,"","Employee Registration Failed");		
		}
		catch(HisApplicationExecutionException e){	
			if(e.getMessage().contains("Foreign_Key_Violation") )
				strErrMsg = "Foreign Key Voilation"; 
			else if(e.getMessage().contains("Unique_Key_Violation") )
				strErrMsg = "Unique Key Voilation";
			else
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
				createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0");
			
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

	public static void updateEmployeeRegistration(EmpRegistrationSUP objEmpRegSupp_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p )
	{
		
		System.out.println("EmployeeRegistrationUTIL :: deleteEmployeeRegistration()");
		
		boolean exists=false;
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		String outputString="";
		
		UserVO userVO=getUserVO(objRequest_p);
		//RenewalConfigVO objRenewalConfigVO=null;
		
		//userVO.setTariffID(RegistrationConfig.NEW_REGISTRATION_TARIFF_ID);
		userVO.setModuleId(PisConfig.MODULE_ID_PIS);
		Status objStatus =new Status();	
		
		String strIsSavedSuccussfulFlag="0", strSavedOrErrMsg="",strIsFormFieldResetFlag="0", strPrintDivContent="", strErrMsg="";
		boolean flagSaveMsgObjCreated=false;
		
		try
		{
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			//gets the hospital information
			HospitalMstVO objHospitalMstVO = getHospitalVO(objRequest_p);
			
			setSysdate(objRequest_p);
			//isReferredDecider(objNewPatRegSupp_p,objRequest_p);
			
			setNotMandatoryDefaultCmbValue(objEmpRegSupp_p);
			
			EmpRegistrationVO employeeRegisterVO = new EmpRegistrationVO();	
			HelperMethods.populate(employeeRegisterVO, objEmpRegSupp_p);
			
			//EpisodeVO episodeVO[]=NewRegistrationDATA.registerNewPatient(patientVO,arrEpisodeVO,userVO,objRequest_p);
			EmpRegistrationDATA.updateEmpDtl(employeeRegisterVO,userVO,objRequest_p);
			System.out.println("EmployeeRegistrationUTIL :: updateEmployeeRegistration()  ::  Employee Number = "+employeeRegisterVO.getStrEmployeeNumber());
			
			strIsSavedSuccussfulFlag="1";
			strSavedOrErrMsg="Employee Details Updated for Employee Number - "+employeeRegisterVO.getStrEmployeeNumber();
			strIsFormFieldResetFlag="1";
			//String tmpFileName=JoiningConfig.CARD_NEW_REGISTRATION+userVO.getSeatId();
			
			strPrintDivContent="";
			//strPrintDivContent=NewRegistrationSlip.print(preapareSlip(employeeRegisterVO,objEmpRegSupp_p, objRequest_p), tmpFileName,objRequest_p,"NR");
			
			//System.out.println("PrintSlip :"+strPrintDivContent);
			
			flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strSavedOrErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0");
		}
		catch(HisDuplicateRecordException e){
			strErrMsg = e.getMessage();
		}
		catch(HisInvalidTokenNumberException e){
			strErrMsg = "INVALID TOKEN NUMBER";
		}
		catch(HisUpdateUnsuccesfullException e){
			strErrMsg = "Update Unsuccessful";
		}		
		catch(HisDataAccessException e){
			strErrMsg = "Employee Details Deletion Failed";
			objStatus.add(Status.ERROR_DA,"","Employee Details Updation Failed");		
		}
		catch(HisApplicationExecutionException e){	
			if(e.getMessage().contains("Foreign_Key_Violation") )
				strErrMsg = "Foreign Key Voilation"; 
			else if(e.getMessage().contains("Unique_Key_Violation") )
				strErrMsg = "Unique Key Voilation";
			else
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
				createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0");
			
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
	
	public static void deleteEmployeeRegistration(EmpRegistrationSUP objEmpRegSupp_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p )
	{
		
		System.out.println("EmployeeRegistrationUTIL :: deleteEmployeeRegistration()");
		
		boolean exists=false;
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		String outputString="";
		
		UserVO userVO=getUserVO(objRequest_p);
		//RenewalConfigVO objRenewalConfigVO=null;
		
		//userVO.setTariffID(RegistrationConfig.NEW_REGISTRATION_TARIFF_ID);
		userVO.setModuleId(PisConfig.MODULE_ID_PIS);
		Status objStatus =new Status();	
		
		String strIsSavedSuccussfulFlag="0", strSavedOrErrMsg="",strIsFormFieldResetFlag="0", strPrintDivContent="", strErrMsg="";
		boolean flagSaveMsgObjCreated=false;
		
		try
		{
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			//gets the hospital information
			HospitalMstVO objHospitalMstVO = getHospitalVO(objRequest_p);
			
			setSysdate(objRequest_p);
			//isReferredDecider(objNewPatRegSupp_p,objRequest_p);
			
			setNotMandatoryDefaultCmbValue(objEmpRegSupp_p);
			
			EmpRegistrationVO employeeRegisterVO = new EmpRegistrationVO();	
			HelperMethods.populate(employeeRegisterVO, objEmpRegSupp_p);
			
			//EpisodeVO episodeVO[]=NewRegistrationDATA.registerNewPatient(patientVO,arrEpisodeVO,userVO,objRequest_p);
			EmpRegistrationDATA.deleteEmpDtl(employeeRegisterVO,userVO,objRequest_p);
			System.out.println("EmployeeRegistrationUTIL :: deleteEmployeeRegistration()  ::  Employee Number = "+employeeRegisterVO.getStrEmployeeNumber());
			strIsSavedSuccussfulFlag="1";
			strSavedOrErrMsg="Employee Details Deleted for Employee Number - "+employeeRegisterVO.getStrEmployeeNumber();
			strIsFormFieldResetFlag="1";
			//String tmpFileName=JoiningConfig.CARD_NEW_REGISTRATION+userVO.getSeatId();
			
			strPrintDivContent="";
			//strPrintDivContent=NewRegistrationSlip.print(preapareSlip(employeeRegisterVO,objEmpRegSupp_p, objRequest_p), tmpFileName,objRequest_p,"NR");
			
			//System.out.println("PrintSlip :"+strPrintDivContent);
			
			flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strSavedOrErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0");
		}
		catch(HisDuplicateRecordException e){
			strErrMsg = e.getMessage();
		}
		catch(HisInvalidTokenNumberException e){
			strErrMsg = "INVALID TOKEN NUMBER";
		}
		catch(HisUpdateUnsuccesfullException e){
			strErrMsg = "Update Unsuccessful";
		}		
		catch(HisDataAccessException e){
			strErrMsg = "Employee Details Deletion Failed";
			objStatus.add(Status.ERROR_DA,"","Employee Details Deletion Failed");		
		}
		catch(HisApplicationExecutionException e){	
			if(e.getMessage().contains("Foreign_Key_Violation") ) 
				strErrMsg = "This record is in Use and can't be Deleted.";
			else if(e.getMessage().contains("Unique_Key_Violation") )
				strErrMsg = "Unique Key Voilation";
			else
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
				createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0");
			
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
	
	private static boolean createSaveMsgObject(Document responseDocument, String strIsSavedSuccussfulFlag,String strSavedMsg, String strIsFormFieldRestFlag, String strPrintDivContent, String strPrintFlag) 
	{
		Element elementSaveMsg =responseDocument.createElement("savedMsgDtl");
		elementSaveMsg.setAttribute("isSavedSuccussful", strIsSavedSuccussfulFlag);
		elementSaveMsg.setAttribute("savedMessage", strSavedMsg);
		elementSaveMsg.setAttribute("isFormFieldTobeReset", strIsFormFieldRestFlag);
		elementSaveMsg.setAttribute("isPrintFlag", strPrintFlag);
		elementSaveMsg.setAttribute("printDivContent", strPrintDivContent);
		
		responseDocument.getFirstChild().appendChild(elementSaveMsg);
		
		return true;
	}
	
	private static void setNotMandatoryDefaultCmbValue(EmpRegistrationSUP objEmpRegSupp_p){
		
		objEmpRegSupp_p.setIntAppellationCode2(objEmpRegSupp_p.getIntAppellationCode2().equals("-1")?"":objEmpRegSupp_p.getIntAppellationCode2());	
		objEmpRegSupp_p.setIntSuffixCode(objEmpRegSupp_p.getIntSuffixCode().equals("-1")?"":objEmpRegSupp_p.getIntSuffixCode());
		//System.out.println("getEntryLabel:  "+)
	}
	
	public static void getEmpDtl(HttpServletRequest objRequest, HttpServletResponse objResponse) 
	{
		
		System.out.println("EmployeeRegistrationUTIL :: getEmpDtl");
		List<String> lstEmployeeJsonObjString = new ArrayList<String>();
		StringBuilder strResponse = new StringBuilder();
		String strMode="0";
		try{
		
			//String patPrimaryCatCode= (String)objRequest.getParameter("patPrimaryCatCode");
			String searchEmpName =	(String)objRequest.getParameter("searchEmpName");
			String searchEmpNo	=	(String)objRequest.getParameter("searchEmpNo");
			//String strAlreadyRegistrdFlag = (String)objRequest.getParameter("alreadyRegisteredFlag");
			
			//if(strAlreadyRegistrdFlag!=null && !strAlreadyRegistrdFlag.equals("") && !strAlreadyRegistrdFlag.equals("0"))
				//strMode= strAlreadyRegistrdFlag;
			
			UserVO userVO=getUserVO(objRequest);
			
			//if(patPrimaryCatCode!=null && !patPrimaryCatCode.equals(""))
			lstEmployeeJsonObjString=EmpRegistrationDATA.getEmpDtl(userVO,searchEmpName,searchEmpNo,strMode);
			
			
			strResponse.append("[");
			if(lstEmployeeJsonObjString!=null && lstEmployeeJsonObjString.size()>0){
				for(int i=0; i<lstEmployeeJsonObjString.size(); i++){
					if(i==0)
						strResponse.append(lstEmployeeJsonObjString.get(i));
					else
						strResponse.append(","+lstEmployeeJsonObjString.get(i));
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
	
	public static void validateGuestLoginDtl(HttpServletRequest objRequest, HttpServletResponse objResponse) 
	{
		
		System.out.println("EmployeeRegistrationUTIL :: validateGuestLoginDtl");
		List<String> lstEmployeeJsonObjString = new ArrayList<String>();
		StringBuilder strResponse = new StringBuilder();
		String strMode="0";
		try{
		
			//String patPrimaryCatCode= (String)objRequest.getParameter("patPrimaryCatCode");
			String guestModifyEmpNo =	(String)objRequest.getParameter("guestModifyEmpNo");
			String guestModifyDoB	=	(String)objRequest.getParameter("guestModifyDoB");
			//String strAlreadyRegistrdFlag = (String)objRequest.getParameter("alreadyRegisteredFlag");
			
			//if(strAlreadyRegistrdFlag!=null && !strAlreadyRegistrdFlag.equals("") && !strAlreadyRegistrdFlag.equals("0"))
				//strMode= strAlreadyRegistrdFlag;
			
			UserVO userVO=getUserVO(objRequest);
			
			//if(patPrimaryCatCode!=null && !patPrimaryCatCode.equals(""))
			lstEmployeeJsonObjString=EmpRegistrationDATA.validateGuestLoginDtl(userVO,guestModifyEmpNo,guestModifyDoB,strMode);
			
			
			strResponse.append("[");
			if(lstEmployeeJsonObjString!=null && lstEmployeeJsonObjString.size()>0){
				for(int i=0; i<lstEmployeeJsonObjString.size(); i++){
					if(i==0)
						strResponse.append(lstEmployeeJsonObjString.get(i));
					else
						strResponse.append(","+lstEmployeeJsonObjString.get(i));
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
	
	public static void getEmpDtlForValidation(HttpServletRequest objRequest, HttpServletResponse objResponse) 
	{
		
		System.out.println("EmployeeRegistrationUTIL :: getEmpDtlForValidation");
		List<String> lstEmployeeJsonObjString = new ArrayList<String>();
		StringBuilder strResponse = new StringBuilder();
		String strMode="0";
		try{
		
			//String patPrimaryCatCode= (String)objRequest.getParameter("patPrimaryCatCode");
			String empValidationStatus =	(String)objRequest.getParameter("empValidationStatus");
			String empNumber	=	(String)objRequest.getParameter("empNumber");
			//String strAlreadyRegistrdFlag = (String)objRequest.getParameter("alreadyRegisteredFlag");
			
			//if(strAlreadyRegistrdFlag!=null && !strAlreadyRegistrdFlag.equals("") && !strAlreadyRegistrdFlag.equals("0"))
				//strMode= strAlreadyRegistrdFlag;
			
			UserVO userVO=getUserVO(objRequest);
			
			//if(patPrimaryCatCode!=null && !patPrimaryCatCode.equals(""))
			lstEmployeeJsonObjString=EmpRegistrationDATA.getEmpDtlForValidation(userVO,empValidationStatus,empNumber,strMode);
			
			
			setSysdate(objRequest);
			
			Date dateObj = null;
			if((objRequest.getSession().getAttribute(Config.SYSDATEOBJECT))!=null)
				dateObj = (Date)objRequest.getSession().getAttribute(Config.SYSDATEOBJECT);
			else
				dateObj=null;
			
			SimpleDateFormat sf = (SimpleDateFormat) DateFormat.getInstance();
			sf.applyPattern("dd-MMM-yyyy"); String date = sf.format(dateObj);
			sf.applyPattern("HH:mm:ss"); String time = sf.format(dateObj);
			//System.out.println("date:"+date); System.out.println("time:"+time);
			strResponse.append("{\"Date\":\""+date+"\",\"Time\":\""+time+"\"");
		
			
			strResponse.append(",\"Rows\":[");
			if(lstEmployeeJsonObjString!=null && lstEmployeeJsonObjString.size()>0){
				for(int i=0; i<lstEmployeeJsonObjString.size(); i++){
					if(i==0)
						strResponse.append(lstEmployeeJsonObjString.get(i));
					else
						strResponse.append(","+lstEmployeeJsonObjString.get(i));
				}
			}
		
			strResponse.append("]");		
			strResponse.append("}");
		}
		
		catch(Exception e){
			e.printStackTrace();
		}		
		finally
		{
			writeResponse(objResponse, strResponse.toString());
		}	
		
	}
	
	public static void getReportData(HttpServletRequest objRequest, HttpServletResponse objResponse) 
	{
		
		System.out.println("EmployeeRegistrationUTIL :: getReportData");
		List<String> lstEmployeeJsonObjString = new ArrayList<String>();
		StringBuilder strResponse = new StringBuilder();
		String strMode="1";
		String filterCondition="";
		try{
		
			String empValidationStatus =	(String)objRequest.getParameter("empValidationStatus");
			

			System.out.println(filterCondition);
			
			
			if (empValidationStatus.equalsIgnoreCase("Pending") )
				filterCondition=objRequest.getSession().getAttribute("filterCondition_p").toString();
			else if (empValidationStatus.equalsIgnoreCase("Validated"))
				filterCondition=objRequest.getSession().getAttribute("filterCondition_v").toString();
			else if (empValidationStatus.equalsIgnoreCase("Rejected")) 
				filterCondition=objRequest.getSession().getAttribute("filterCondition_r").toString();
			else
				filterCondition=objRequest.getSession().getAttribute("filterCondition_p").toString();
		                     
						
			
			
			
			UserVO userVO=getUserVO(objRequest);
			
			//if(patPrimaryCatCode!=null && !patPrimaryCatCode.equals(""))
			lstEmployeeJsonObjString=EmpRegistrationDATA.getReportData(userVO,empValidationStatus,strMode ,filterCondition);
			
			
			setSysdate(objRequest);
			
			Date dateObj = null;
			if((objRequest.getSession().getAttribute(Config.SYSDATEOBJECT))!=null)
				dateObj = (Date)objRequest.getSession().getAttribute(Config.SYSDATEOBJECT);
			else
				dateObj=null;
			
			SimpleDateFormat sf = (SimpleDateFormat) DateFormat.getInstance();
			sf.applyPattern("dd-MMM-yyyy"); String date = sf.format(dateObj);
			sf.applyPattern("HH:mm:ss"); String time = sf.format(dateObj);
			//System.out.println("date:"+date); System.out.println("time:"+time);
			strResponse.append("{\"Date\":\""+date+"\",\"Time\":\""+time+"\"");
		
			
			strResponse.append(",\"Rows\":[");
			if(lstEmployeeJsonObjString!=null && lstEmployeeJsonObjString.size()>0){
				for(int i=0; i<lstEmployeeJsonObjString.size(); i++){
					if(i==0)
						strResponse.append(lstEmployeeJsonObjString.get(i));
					else
						strResponse.append(","+lstEmployeeJsonObjString.get(i));
				}
			}
		
		strResponse.append("]");		
		strResponse.append("}");
		
		}		
		catch(Exception e){
			e.printStackTrace();
		}		
		finally
		{
			writeResponse(objResponse, strResponse.toString());
		}	
		
	}
	
	/*
	public static void getValidatorDetails(HttpServletRequest objRequest, HttpServletResponse objResponse) 
	{
		
		System.out.println("EmployeeRegistrationUTIL :: getValidatorDetails");
		List<String> lstEmployeeJsonObjString = new ArrayList<String>();
		StringBuilder strResponse = new StringBuilder();
		String strMode="0";
		try{
		
			UserVO userVO=getUserVO(objRequest);
			lstEmployeeJsonObjString=EmpRegistrationDATA.getValidatorDetails(userVO,strMode);
			strResponse.append("[");
			if(lstEmployeeJsonObjString!=null && lstEmployeeJsonObjString.size()>0){
				for(int i=0; i<lstEmployeeJsonObjString.size(); i++){
					if(i==0)
						strResponse.append(lstEmployeeJsonObjString.get(i));
					else
						strResponse.append(","+lstEmployeeJsonObjString.get(i));
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
	*/
	
	public static void getPendingEmployeeList_AJAX(EmpRegistrationSUP objEmpRegSupp_p,HttpServletRequest objRequest,HttpServletResponse objResponse, Map<Object, Object> mapSession)
	{		
		Status objStatus=new Status();
		String outputString="";
		Map<String, Object> map1 = new HashMap<String, Object>();
		  		
		try
		{
			
			UserVO userVO=getUserVO(objRequest);
			userVO.setModuleId(PisConfig.MODULE_ID_PIS);	
			
			//System.out.println("Employee Number = "+objRequest.getParameter("selectedEmpNo"));
			//System.out.println("Employee Number Attribute= "+objRequest.getAttribute("selectedEmpNo"));
			
			//String _selecedEmpNo = objRequest.getParameter("selectedEmpNo");
			
			String _page = objRequest.getParameter("page"), _limit = objRequest.getParameter("rows"), 
				   _sidx = objRequest.getParameter("sidx"), _sord = objRequest.getParameter("sord"), _where = "";
			//_where=HelperMethods.getListFilterCriteria(objRequest.getParameter("filters"), objRequest.getParameter("_search"),1);
			_where=HelperMethods.getListFilterCriteria(objRequest.getParameter("filters"), objRequest.getParameter("_search"),1);
			objRequest.getSession().setAttribute("filterCondition_p", _where);
			int i_page=Integer.parseInt(_page),i_limit=Integer.parseInt(_limit);			
			Map<String, Object> mapObj=EmpRegistrationDATA.getPendingEmployeeList_AJAX(userVO,i_page,i_limit,_sidx,_sord,_where);
			String r_count =(String)mapObj.get("count"),r_total_page =(String)mapObj.get("total_pages"),r_page =(String)mapObj.get("page");
			List<List<String>> lstDtl= (List<List<String>>) mapObj.get("listObj");;
			
			if(lstDtl!=null && lstDtl.size()>0){
				//if(!$sidx) $sidx =1;
				 List<Map<String, Object>> ListObj2 = new ArrayList<Map<String, Object>>();
				 for (List<String> list2 : lstDtl) {Map<String, Object> map2 = new HashMap<String, Object>();
				 List<String> ListObj1 = new ArrayList<String>();for (String temp : list2) {ListObj1.add(temp);}
				 map2.put("id", list2.get(0));map2.put("cell", ListObj1);ListObj2.add(map2);}
				 map1.put("page", r_page);map1.put("total", r_total_page);map1.put("records", r_count);map1.put("rows", ListObj2);
			}else{System.out.println("List is Null or Empty.");}
			
			outputString=JSONValue.toJSONString(map1);
			System.out.println("outputString "+outputString);
		}		
		catch(Exception e){
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			writeResponse(objResponse, outputString);
		}	
	}
	
	public static void getValidatedEmployeeList_AJAX(EmpRegistrationSUP objEmpRegSupp_p,HttpServletRequest objRequest,HttpServletResponse objResponse, Map<Object, Object> mapSession)
	{		
		Status objStatus=new Status();
		String outputString="";
		Map<String, Object> map1 = new HashMap<String, Object>();
		  		
		try
		{
			
			UserVO userVO=getUserVO(objRequest);
			userVO.setModuleId(PisConfig.MODULE_ID_PIS);	
			
			//System.out.println("Employee Number = "+objRequest.getParameter("selectedEmpNo"));
			//System.out.println("Employee Number Attribute= "+objRequest.getAttribute("selectedEmpNo"));
			
			//String _selecedEmpNo = objRequest.getParameter("selectedEmpNo");
			
			String _page = objRequest.getParameter("page"), _limit = objRequest.getParameter("rows"), 
				   _sidx = objRequest.getParameter("sidx"), _sord = objRequest.getParameter("sord"), _where = "";
			//_where=HelperMethods.getListFilterCriteria(objRequest.getParameter("filters"), objRequest.getParameter("_search"),1);
			_where=HelperMethods.getListFilterCriteria(objRequest.getParameter("filters"), objRequest.getParameter("_search"),1);
			objRequest.getSession().setAttribute("filterCondition_v", _where);
			int i_page=Integer.parseInt(_page),i_limit=Integer.parseInt(_limit);			
			Map<String, Object> mapObj=EmpRegistrationDATA.getValidatedEmployeeList_AJAX(userVO,i_page,i_limit,_sidx,_sord,_where);
			String r_count =(String)mapObj.get("count"),r_total_page =(String)mapObj.get("total_pages"),r_page =(String)mapObj.get("page");
			List<List<String>> lstDtl= (List<List<String>>) mapObj.get("listObj");;
			
			if(lstDtl!=null && lstDtl.size()>0){
				//if(!$sidx) $sidx =1;
				 List<Map<String, Object>> ListObj2 = new ArrayList<Map<String, Object>>();
				 for (List<String> list2 : lstDtl) {Map<String, Object> map2 = new HashMap<String, Object>();
				 List<String> ListObj1 = new ArrayList<String>();for (String temp : list2) {ListObj1.add(temp);}
				 map2.put("id", list2.get(0));map2.put("cell", ListObj1);ListObj2.add(map2);}
				 map1.put("page", r_page);map1.put("total", r_total_page);map1.put("records", r_count);map1.put("rows", ListObj2);
			}else{System.out.println("List is Null or Empty.");}
			
			outputString=JSONValue.toJSONString(map1);
			System.out.println("outputString "+outputString);
		}		
		catch(Exception e){
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			writeResponse(objResponse, outputString);
		}	
	}
	
	public static void getRejectedEmployeeList_AJAX(EmpRegistrationSUP objEmpRegSupp_p,HttpServletRequest objRequest,HttpServletResponse objResponse, Map<Object, Object> mapSession)
	{		
		Status objStatus=new Status();
		String outputString="";
		Map<String, Object> map1 = new HashMap<String, Object>();
		  		
		try
		{
			
			UserVO userVO=getUserVO(objRequest);
			userVO.setModuleId(PisConfig.MODULE_ID_PIS);	
			
			//System.out.println("Employee Number = "+objRequest.getParameter("selectedEmpNo"));
			//System.out.println("Employee Number Attribute= "+objRequest.getAttribute("selectedEmpNo"));
			
			//String _selecedEmpNo = objRequest.getParameter("selectedEmpNo");
			
			String _page = objRequest.getParameter("page"), _limit = objRequest.getParameter("rows"), 
				   _sidx = objRequest.getParameter("sidx"), _sord = objRequest.getParameter("sord"), _where = "";
			//_where=HelperMethods.getListFilterCriteria(objRequest.getParameter("filters"), objRequest.getParameter("_search"),1);
			_where=HelperMethods.getListFilterCriteria(objRequest.getParameter("filters"), objRequest.getParameter("_search"),1);
			objRequest.getSession().setAttribute("filterCondition_r", _where);
			int i_page=Integer.parseInt(_page),i_limit=Integer.parseInt(_limit);			
			Map<String, Object> mapObj=EmpRegistrationDATA.getRejectedEmployeeList_AJAX(userVO,i_page,i_limit,_sidx,_sord,_where);
			String r_count =(String)mapObj.get("count"),r_total_page =(String)mapObj.get("total_pages"),r_page =(String)mapObj.get("page");
			List<List<String>> lstDtl= (List<List<String>>) mapObj.get("listObj");;
			
			if(lstDtl!=null && lstDtl.size()>0){
				//if(!$sidx) $sidx =1;
				 List<Map<String, Object>> ListObj2 = new ArrayList<Map<String, Object>>();
				 for (List<String> list2 : lstDtl) {Map<String, Object> map2 = new HashMap<String, Object>();
				 List<String> ListObj1 = new ArrayList<String>();for (String temp : list2) {ListObj1.add(temp);}
				 map2.put("id", list2.get(0));map2.put("cell", ListObj1);ListObj2.add(map2);}
				 map1.put("page", r_page);map1.put("total", r_total_page);map1.put("records", r_count);map1.put("rows", ListObj2);
			}else{System.out.println("List is Null or Empty.");}
			
			outputString=JSONValue.toJSONString(map1);
			System.out.println("outputString "+outputString);
		}		
		catch(Exception e){
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			writeResponse(objResponse, outputString);
		}	
	}
	
	public static void validateEmployeeRegistrationDetails(EmpRegistrationSUP objEmpRegSupp_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p )
	{
		
		System.out.println("EmployeeRegistrationUTIL :: validateEmployeeRegistrationDetails()");
		
		boolean exists=false;
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		String outputString="";
		
		UserVO userVO=getUserVO(objRequest_p);
		//RenewalConfigVO objRenewalConfigVO=null;
		
		//userVO.setTariffID(RegistrationConfig.NEW_REGISTRATION_TARIFF_ID);
		userVO.setModuleId(PisConfig.MODULE_ID_PIS);
		Status objStatus =new Status();	
		
		String strIsSavedSuccussfulFlag="0", strSavedOrErrMsg="",strIsFormFieldResetFlag="0", strPrintDivContent="", strErrMsg="";
		boolean flagSaveMsgObjCreated=false;
		
		try
		{
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			//gets the hospital information
			HospitalMstVO objHospitalMstVO = getHospitalVO(objRequest_p);
			
			setSysdate(objRequest_p);
			//isReferredDecider(objNewPatRegSupp_p,objRequest_p);
			
			setNotMandatoryDefaultCmbValue(objEmpRegSupp_p);
			
			EmpRegistrationVO employeeRegisterVO = new EmpRegistrationVO();	
			HelperMethods.populate(employeeRegisterVO, objEmpRegSupp_p);
			
			//EpisodeVO episodeVO[]=NewRegistrationDATA.registerNewPatient(patientVO,arrEpisodeVO,userVO,objRequest_p);
			EmpRegistrationDATA.validateEmployeeRegistrationDetails(employeeRegisterVO,userVO,objRequest_p);
			System.out.println("EmployeeRegistrationUTIL :: validateEmployeeRegistrationDetails()  ::  Employee Number(s) = "+employeeRegisterVO.getStrEmployeeNumber());
			
			strIsSavedSuccussfulFlag="1";
			
			if(employeeRegisterVO.getStrValidateStatus().equalsIgnoreCase("Y"))
				strSavedOrErrMsg="Employee Registration Details Validated for Employee Number(s) - "+employeeRegisterVO.getStrEmployeeNumber();
			else
				strSavedOrErrMsg="Employee Registration Details Rejected for Employee Number(s) - "+employeeRegisterVO.getStrEmployeeNumber();
			
			strIsFormFieldResetFlag="1";
			//String tmpFileName=JoiningConfig.CARD_NEW_REGISTRATION+userVO.getSeatId();
			
			strPrintDivContent="";
			//strPrintDivContent=NewRegistrationSlip.print(preapareSlip(employeeRegisterVO,objEmpRegSupp_p, objRequest_p), tmpFileName,objRequest_p,"NR");
			
			//System.out.println("PrintSlip :"+strPrintDivContent);
			
			flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strSavedOrErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0");
		}
		catch(HisDuplicateRecordException e){
			strErrMsg = e.getMessage();
		}
		catch(HisInvalidTokenNumberException e){
			strErrMsg = "INVALID TOKEN NUMBER";
		}
		catch(HisUpdateUnsuccesfullException e){
			strErrMsg = "Update Unsuccessful";
		}		
		catch(HisDataAccessException e){
			strErrMsg = "Employee Details Validation Failed";
			objStatus.add(Status.ERROR_DA,"","Employee Details Validation Failed");		
		}
		catch(HisApplicationExecutionException e){	
			if(e.getMessage().contains("Foreign_Key_Violation") )
				strErrMsg = "Foreign Key Voilation"; 
			else if(e.getMessage().contains("Unique_Key_Violation") )
				strErrMsg = "Unique Key Voilation";
			else
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
				createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0");
			
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
	
	
}//end of class