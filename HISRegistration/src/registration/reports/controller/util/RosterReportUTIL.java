package registration.reports.controller.util;
/**
 * @author Raj Kumar	
 * Creation Date:- Aug-07-2018
 * Modifying Date:- 
 * Used For:- Roster Report	
 * Team Lead By:- Garima Gotra
 * Module:- Registration(HIS Project)
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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



import registration.reports.controller.data.RosterReportDATA;
import vo.registration.RosterReportVO;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.ReportUtil;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;
public class RosterReportUTIL extends ControllerUTIL{		
	
	@SuppressWarnings("rawtypes")
	public static void getEssentials(HttpServletRequest request) {
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		RosterReportDATA objRosterReportData_p = null;
		HttpSession session = request.getSession(true);  
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:MM");
		Map SessionMap=new HashMap();
		Document responseDocument=null;
		String outputString="";	
		
		try{
			
			objRosterReportData_p = new RosterReportDATA();
			setSysdate(request);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			List departmentList,patCategoryList=new ArrayList();			
			departmentList=objRosterReportData_p.getDeptList(uservo);	
			patCategoryList=objRosterReportData_p.getPatCategoryList(uservo);
			
			//Date sysDate=(Date)formatter.parse((String)request.getSession().getAttribute(HISConfig.SYSDATEOBJECT));
			
			Date sysDate=(Date)(request.getSession().getAttribute(Config.SYSDATEOBJECT));
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			
			System.out.println("---"+sysDate+"------");
			System.out.println("Yesterday's date = "+ cal.getTime());			
			
			session.setAttribute("departmentList",departmentList);
			session.setAttribute("unitList",new ArrayList());	
			session.setAttribute("patCategoryList",patCategoryList);	
			session.setAttribute("sysDate",sysDate);	
			session.setAttribute("yesterDay",cal.getTime());	

			//createOptionObject((List<Entry>)departmentList,responseDocument,"strDeptCode");
			
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
			
			List unitList=RosterReportDATA.getUnitList(strDeptCode,userVO);			
			createOptionObject((List<Entry>)unitList,responseDocument,"strUnitCode");						
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
	
	public static void showReport(RosterReportVO rosterReportVo,HttpServletRequest objRequest, HttpServletResponse objResponse){
		ReportUtil ts = new ReportUtil();
		String reportFormat = "html";
		

		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
				boolean footerVisible=false;
				UserVO uservo=ControllerUTIL.getUserVO(objRequest);	
				Date Date=(Date)objRequest.getSession().getAttribute("sysDate");

				String strDepartmentCode=rosterReportVo.getStrDeptCode();
				String strDepartmentUnitCode=rosterReportVo.getStrUnitCode();
				//String strPatCategoryCode=unitwiseOPDStatVo.getStrPatCategoryCode();
				String strServiceType=rosterReportVo.getStrServiceType();
				String strMode=rosterReportVo.getStrMode();

				String strReportType=rosterReportVo.getStrReportType();
				String strGraphOrText=rosterReportVo.getStrGraphOrText();		
				String strReportName=rosterReportVo.getTitle();
				String reportPath=rosterReportVo.getReportPath();
				String strHospitalCode =uservo.getHospitalCode();
				String strFromDate =rosterReportVo.getStrFromDate();
				String strToDate =rosterReportVo.getStrToDate();
				String strTitle =rosterReportVo.getTitle();
				String strFromHour=rosterReportVo.getFromHour();
				String strFromMin=rosterReportVo.getStrFromMin();
				String strToHour=rosterReportVo.getToHour();
				String strToMin=rosterReportVo.getStrToMin();
				String strFromMonth=rosterReportVo.getFromMonth();
				String strToMonth=rosterReportVo.getToMonth();
				String strFromMonthYear=rosterReportVo.getFromMonthYear();
				String strToMonthYear=rosterReportVo.getToMonthYear();
				String strFromYear=rosterReportVo.getFromYear();
				String strToYear=rosterReportVo.getToYear();				

						
				System.out.println("----strReportType-----"+strReportType+"--------");

				if(strReportType.equalsIgnoreCase("1"))
					reportFormat = "pdf";
				else if(strReportType.equalsIgnoreCase("3"))
					reportFormat = "xls";

				params.put("mode", 1);
				if(rosterReportVo.getStrChoice().equalsIgnoreCase("D"))//Date Wise
				{
					params.put("period_Choice", "D");
					System.out.println("----mode Date Wise-----------");

				}
				else if(rosterReportVo.getStrChoice().equalsIgnoreCase("T"))//Today Mode
				{
					System.out.println("----mode Today-----------");
					params.put("period_Choice", "T");
					String sysDate=WebUTIL.getCustomisedSysDate(Date, "dd-MMM-yyyy");
					strFromDate=sysDate+" "+strFromHour+":"+strFromMin;
					strToDate=sysDate+" "+strToHour+":"+strToMin;

				}	
				else if(rosterReportVo.getStrChoice().equalsIgnoreCase("M"))//Month Wise
				{	
					int monthnum=0;
					if(strToMonth.equals("jan"))
					 monthnum=1;
					else if(strToMonth.equals("Feb"))
						monthnum=2;
					else if(strToMonth.equals("March"))
						monthnum=3;
					else if(strToMonth.equals("Apr"))
						monthnum=4;
					else if(strToMonth.equals("May"))
						monthnum=5;
					else if(strToMonth.equals("June"))
						monthnum=6;
					else if(strToMonth.equals("July"))
						monthnum=7;
					else if(strToMonth.equals("Aug"))
						monthnum=8;
					else if(strToMonth.equals("Sep"))
						monthnum=9;
					else if(strToMonth.equals("Oct"))
						monthnum=10;
					else if(strToMonth.equals("Nov"))
						monthnum=11;
						else if(strToMonth.equals("Dev"))
							monthnum=12;
						
						
										
					String date = (monthnum+"/"+01+"/"+strToYear);
					SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
					Date convertedDate = dateFormat.parse(date);
					Calendar c = Calendar.getInstance();
					c.setTime(convertedDate);
					int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
				    System.out.println(lastDay);
					c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
					
					params.put("period_Choice", "M");
					System.out.println("----mode Month Wise-----------");
					strFromYear=strFromMonthYear;strToYear=strToMonthYear;
					strFromDate="01-"+strFromMonth+"-"+strFromYear;
					strToDate=lastDay+"-"+strToMonth+"-"+strToYear;
				}	
				else if(rosterReportVo.getStrChoice().equalsIgnoreCase("Y"))//Year Wise
				{
					params.put("period_Choice", "Y");
					System.out.println("----mode Year Wise-----------");
					strFromDate="1-JAN"+"-"+strFromYear;
					strToDate="31-DEC"+"-"+strToYear;
				}
				params.put("report_Name", strTitle);
				params.put("report_Footer_Visible", footerVisible);				
				//params.put("report_user_Remarks", strUserRemarks);
				params.put("rpt_format", reportFormat);	
				params.put("hospCode", strHospitalCode);	
				params.put("fromDate", strFromDate);	
				params.put("toDate", strToDate);
				params.put("fromMonth", strFromMonth);	
				params.put("toMonth", strToMonth);
				params.put("fromYear", strFromYear);	
				params.put("toYear", strToYear);
				params.put("dept_code", strDepartmentCode);
				params.put("dept_unit_code", strDepartmentUnitCode);
				params.put("pat_cat_code", "0");
				params.put("service_type", strServiceType);
				params.put("report_mode", "1");
				
				//if(strMode.equalsIgnoreCase("1"))
					reportPath = "/registration/reports/rptDesign/RosterReport.rptdesign";
				//else
					//reportPath = "/registration/reports/rptDesign/RosterReport.rptdesign";
				
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
			
				System.out.println("----strDepartmentCode-----"+strDepartmentCode+"--------");
				System.out.println("----strDepartmentUnitCode-----"+strDepartmentUnitCode+"--------");
				//System.out.println("----strPatCategoryCode-----"+strPatCategoryCode+"--------");
				System.out.println("----strServiceType-----"+strServiceType+"--------");
				System.out.println("----strMode-----"+strMode+"--------");
				System.out.println("----reportPath-----"+reportPath+"--------");


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

