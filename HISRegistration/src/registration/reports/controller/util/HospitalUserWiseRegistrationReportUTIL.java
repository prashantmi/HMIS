package registration.reports.controller.util;
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

import registration.reports.controller.data.HospitalUserWiseRegistrationReportDATA;
import vo.registration.AgeWiseRegistrationReportVO;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.ReportUtil;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

public class HospitalUserWiseRegistrationReportUTIL extends ControllerUTIL
{@SuppressWarnings("rawtypes")
public static void getEssentials(HttpServletRequest request) {
	
	DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
	TransformerFactory trf= TransformerFactory.newInstance();
	HospitalUserWiseRegistrationReportDATA objAgeWiseRegistrationReportData_p = null;
	HttpSession session = request.getSession(true);  
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:MM");
	Map SessionMap=new HashMap();
	Document responseDocument=null;
	String outputString="";	


	try{
		
		objAgeWiseRegistrationReportData_p = new HospitalUserWiseRegistrationReportDATA();
		setSysdate(request);
		UserVO uservo = ControllerUTIL.getUserVO(request);	
		List departmentList=new ArrayList();			
		departmentList=objAgeWiseRegistrationReportData_p.getDeptList(uservo);	
		Date sysDate=(Date)(request.getSession().getAttribute(Config.SYSDATEOBJECT));
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		
		System.out.println("---"+sysDate+"------");
		System.out.println("Yesterday's date = "+ cal.getTime());			
		
		session.setAttribute("departmentList",departmentList);
		session.setAttribute("unitList",new ArrayList());	
			
		session.setAttribute("sysDate",sysDate);	
		session.setAttribute("yesterDay",cal.getTime());
	}
	catch (Exception e) {
		e.printStackTrace();
	}		
}
public static void getUnit_AJAX(HttpServletRequest objRequest, HttpServletResponse objResponse) {
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		String outputString="";
		try{
			String strDeptCode= (String)objRequest.getParameter("deptCode");
			UserVO userVO=getUserVO(objRequest);
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			List unitList=HospitalUserWiseRegistrationReportDATA.getUnitList(strDeptCode,userVO);			
			createOptionObject((List<Entry>)unitList,responseDocument,"strUnitCode");						
			
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



public static void showReport(AgeWiseRegistrationReportVO agewiseregVo,HttpServletRequest objRequest, HttpServletResponse objResponse){
		ReportUtil ts = new ReportUtil();
		String reportFormat = "html";
		

		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
				boolean footerVisible=false;
				UserVO uservo=ControllerUTIL.getUserVO(objRequest);	
				Date Date=(Date)objRequest.getSession().getAttribute("sysDate");

				String strDepartmentCode=agewiseregVo.getStrDeptCode();
				String strDepartmentUnitCode=agewiseregVo.getStrUnitCode();
				String strMode=agewiseregVo.getStrMode();
				String strServiceType=agewiseregVo.getStrServiceType();
				String strReportType=agewiseregVo.getStrReportType();
				String strGraphOrText=agewiseregVo.getStrGraphOrText();	
				String strPatVisit=agewiseregVo.getStrPatVisit();
				String strReportName=agewiseregVo.getTitle();
				String reportPath=agewiseregVo.getReportPath();
				String strHospitalCode =uservo.getHospitalCode();
				String strFromDate =agewiseregVo.getStrFromDate();
				String strToDate =agewiseregVo.getStrToDate();
				String strTitle =agewiseregVo.getTitle();
				String strFromHour=agewiseregVo.getFromHour();
				String strFromMin=agewiseregVo.getStrFromMin();
				String strToHour=agewiseregVo.getToHour();
				String strToMin=agewiseregVo.getStrToMin();
				String strFromMonth=agewiseregVo.getFromMonth();
				String strToMonth=agewiseregVo.getToMonth();
				String strFromMonthYear=agewiseregVo.getFromMonthYear();
				String strToMonthYear=agewiseregVo.getToMonthYear();
				String strFromYear=agewiseregVo.getFromYear();
				String strToYear=agewiseregVo.getToYear();				

						
				System.out.println("----strReportType-----"+strReportType+"--------");

				if(strReportType.equalsIgnoreCase("1"))
					reportFormat = "pdf";
				else if(strReportType.equalsIgnoreCase("3"))
					reportFormat = "xls";

				params.put("mode", "1");
				if(agewiseregVo.getStrChoice().equalsIgnoreCase("D"))//Date Wise
				{
					params.put("period_Choice", "D");
					System.out.println("----mode Date Wise-----------");

				}
				else if(agewiseregVo.getStrChoice().equalsIgnoreCase("T"))//Today Mode
				{
					System.out.println("----mode Today-----------");
					params.put("period_Choice", "T");
					String sysDate=WebUTIL.getCustomisedSysDate(Date, "dd-MMM-yyyy");
					strFromDate=sysDate+" "+strFromHour+":"+strFromMin;
					strToDate=sysDate+" "+strToHour+":"+strToMin;

				}	
				else if(agewiseregVo.getStrChoice().equalsIgnoreCase("M"))//Month Wise
				{
					params.put("period_Choice", "M");
					System.out.println("----mode Month Wise-----------");
					strFromYear=strFromMonthYear;strToYear=strToMonthYear;
					strFromDate=strFromMonth+"-"+strFromYear;
					strToDate=strToMonth+"-"+strToYear;
				}	
				else if(agewiseregVo.getStrChoice().equalsIgnoreCase("Y"))//Year Wise
				{
					params.put("period_Choice", "Y");
					System.out.println("----mode Year Wise-----------");
					strFromDate="1-JAN"+"-"+strFromYear;
					strToDate="31-DEC"+"-"+strToYear;
				}	
				
				params.put("report_Name", strTitle);
				params.put("report_Footer_Visible", footerVisible);				
				params.put("rpt_format", reportFormat);	
				params.put("hospCode", strHospitalCode);	
				params.put("fromDate", strFromDate);	
				params.put("toDate", strToDate);
				params.put("fromMonth", strFromMonth);	
				params.put("toMonth", strToMonth);
				params.put("fromYear", strFromYear);	
				params.put("toYear", strToYear);
				params.put("visit_no", strPatVisit);
				params.put("dept_unit_code", strDepartmentUnitCode);
				params.put("report_mode", strMode);
				//params.put("report_mode", "1");
				params.put("service_type", strServiceType);
				
				reportPath = "/registration/reports/rptDesign/HospitalUserWiseCrossTab.rptdesign";
				
				
				System.out.println("----strTitle-----"+strTitle+"--------");
				System.out.println("----strHospitalCode-----"+strHospitalCode+"--------");
				System.out.println("----strFromDate-----"+strFromDate+"--------");
				System.out.println("----strToDate-----"+strToDate+"--------");
				System.out.println("----strFromHour-----"+strFromHour+"--------");
				System.out.println("----strFromMin-----"+strFromMin+"--------");
				System.out.println("----strToHour-----"+strToHour+"--------");
				System.out.println("----strToMin-----"+strToMin+"--------");
				System.out.println("----strFromMonth-----"+strFromMonth+"--------");
				System.out.println("----strToMonth-----"+strToMonth+"--------");
				System.out.println("----strFromYear-----"+strFromYear+"--------");
				System.out.println("----strToYear-----"+strToYear+"--------");
				//System.out.println("----strPatVisit-----"+strPatVisit+"--------");
			
				System.out.println("----strDepartmentCode-----"+strDepartmentCode+"--------");
				System.out.println("----strDepartmentUnitCode-----"+strDepartmentUnitCode+"--------");
				System.out.println("----strMode-----"+strMode+"--------");
				System.out.println("----reportPath-----"+reportPath+"--------");
				System.out.println("----servicetype-----"+strServiceType+"--------");


				ts.displayReport(objRequest, objResponse, reportPath, reportFormat,
						params,strHospitalCode);

			} catch (Exception e) {			
					e.printStackTrace();
			}
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
