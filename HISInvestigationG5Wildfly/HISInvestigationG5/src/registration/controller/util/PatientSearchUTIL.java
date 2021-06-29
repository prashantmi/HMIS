package registration.controller.util;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import registration.controller.actionsupport.PatientSearchSUP;
import registration.controller.data.PatientSearchDATA;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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


import vo.registration.AddressVO;

import vo.registration.PatientVO;

public class PatientSearchUTIL extends ControllerUTIL
{
	

public static void getEssentials(PatientSearchSUP objSUP_p,HttpServletRequest objRequest_p){
		
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		
		try{
			
			essentialMap=PatientSearchDATA.getPatientSearchEssentials(getUserVO(objRequest_p),getHospitalVO(objRequest_p).getState());
			WebUTIL.setMapInSession(essentialMap, objRequest_p,"PatientSearchACTION");
			objSUP_p.setHospitalCode(getUserVO(objRequest_p).getHospitalCode());
			objSUP_p.setIsDemographicSearch("1");
			objSUP_p.setIsGlobal("0");

			//objSUP_p.setPatAddStateCode(getUserVO(objRequest_p).getStrHospitalMstVo().getState());
			//objSUP_p.setPatAddDistrictCode(getUserVO(objRequest_p).getStrHospitalMstVo().getDistrictCode());
			//objSUP_p.setDefaultpatAddStateCode(objSUP_p.getPatAddStateCode());
			//objSUP_p.setDefaultpatAddDistrictCode(objSUP_p.getPatAddDistrictCode());
			

		}catch(HisRecordNotFoundException e)		{
			objStatus.add(Status.TRANSINPROCESS,e.getMessage(),"");
			essentialMap=e.getEssentialMap();
			
			WebUTIL.setMapInSession(essentialMap,objRequest_p,"PatientSearchACTION");
		}catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally{
			WebUTIL.setStatus(objRequest_p,objStatus);
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
		
		List lstState=PatientSearchDATA.getState_AJAX(userVO,strCountryCode);
		
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
		
		List lstDistrict=PatientSearchDATA.getDistrict_AJAX(userVO,strStateCode,strCountryCode);
		
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
	

	public static void writeResponse(HttpServletResponse resp, String output){
		try{
			resp.reset();
			resp.setContentType("text/xml");
			resp.setHeader("Cache-Control", "no-cache");
			System.out.println(output);
		resp.getWriter().write(output);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}	
	
		
	
		
	
}
