package mrd.reports.controller.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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


import hisglobal.config.HISConfig;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;

import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

/**

 * 
 */
public class PatientListReportUTIL extends ControllerUTIL{		
	
	@SuppressWarnings("rawtypes")
	public static void getEssentials(HttpServletRequest request) {
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		//PatientListReportDATA objPatientListReportData_p = null;
		HttpSession session = request.getSession(true);  
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:MM");
		Map SessionMap=new HashMap();
		Document responseDocument=null;
		String outputString="";	
		List lstServiceArea=new ArrayList();
		
		try{
			
			//objPatientListReportData_p = new PatientListReportDATA();
			setSysdate(request);
			//UserVO uservo = getUserVO(request);
			// ServiceAreaVO _serviceAreaVO=new ServiceAreaVO();
			//List departmentList,patCategoryList=new ArrayList();			
			//departmentList=objPatientListReportData_p.getDeptList(uservo);	
				//Map mp=ServiceAreaWiseTotPatientStatisticsReportDATA.getAllDeptList(uservo);
				//departmentList = (List)mp.get(ServiceAreaConfig.OPTION_DEPARTMENT);
			//patCategoryList=objPatientListReportData_p.getPatCategoryList(uservo);
			//patCategoryList=ServiceAreaWiseTotPatientStatisticsReportDATA.getDeptWiseServiceAreaList(_serviceAreaVO,uservo);
		
			
			
			//Date sysDate=(Date)formatter.parse((String)request.getSession().getAttribute(HISConfig.SYSDATEOBJECT));
			
			Date sysDate=(Date)(request.getSession().getAttribute(Config.SYSDATEOBJECT));
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			
			System.out.println("---"+sysDate+"------");
			System.out.println("Yesterday's date = "+ cal.getTime());			
			
			//session.setAttribute("departmentList",departmentList);
			//session.setAttribute("unitList",new ArrayList());	
			//session.setAttribute("patCategoryList",patCategoryList);	
		
			session.setAttribute("sysDate",sysDate);	
			session.setAttribute("yesterDay",cal.getTime());	

			//createOptionObject((List<Entry>)departmentList,responseDocument,"strDeptCode");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
		
	}

	
	private static void createOptionObjectForProc(List<Entry> list,Document responseDocument, String fieldName) {
		 
		Element fieldElement=responseDocument.createElement(fieldName);
		
		
			Element option1= responseDocument.createElement("option");
			fieldElement.appendChild(option1);
			
			option1.setAttribute("value", "0");
			option1.setAttribute("label", "All");
		
		for(Entry entry:list)
		{
			Element option= responseDocument.createElement("option");
			fieldElement.appendChild(option);
			
			option.setAttribute("value", entry.getValue());
			option.setAttribute("label", entry.getLabel());
		}
		responseDocument.getFirstChild().appendChild(fieldElement);
	}
	private static void createOptionObject(List<Entry> list,Document responseDocument, String fieldName) {
	 
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
			//System.out.println(output);
			resp.getWriter().write(output);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
}
