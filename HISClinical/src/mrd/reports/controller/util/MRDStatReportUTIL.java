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

import mrd.vo.MRDDiagnosticStatisticsReportVO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;



import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.NewReportUtil;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

/**
 * @author s.singaravelan
 * Creation Date:- 16-Apr-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 * 
 */
public class MRDStatReportUTIL extends ControllerUTIL{		
	
	@SuppressWarnings("rawtypes")
	public static void getEssentials(HttpServletRequest request) {
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
	
		HttpSession session = request.getSession(true);  
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:MM");
		Map SessionMap=new HashMap();
		Document responseDocument=null;
		String outputString="";	
		
		try{
			
			//objDeptwiseOPDStatReportData_p = new DeptwiseOPDStatReportDATA();
			setSysdate(request);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			
			
			//Date sysDate=(Date)formatter.parse((String)request.getSession().getAttribute(HISConfig.SYSDATEOBJECT));
			
			Date sysDate=(Date)(request.getSession().getAttribute(Config.SYSDATEOBJECT));
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			
			System.out.println("---"+sysDate+"------");
			System.out.println("Yesterday's date = "+ cal.getTime());			
			
			//session.setAttribute("departmentList",departmentList);
			session.setAttribute("unitList",new ArrayList());	
			//session.setAttribute("patCategoryList",patCategoryList);	
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
			
			//List unitList=DeptwiseOPDStatReportDATA.getUnitList(strDeptCode,userVO);			
			//createOptionObject((List<Entry>)unitList,responseDocument,"strUnitCode");						
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
	
	public static void showReport(MRDDiagnosticStatisticsReportVO deptwiseOpdStatVo,HttpServletRequest objRequest, HttpServletResponse objResponse){
		NewReportUtil ts = new NewReportUtil();
		String reportFormat = "html";
		

		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
				boolean footerVisible=false;
				UserVO uservo=ControllerUTIL.getUserVO(objRequest);	
				Date Date=(Date)objRequest.getSession().getAttribute("sysDate");

				String strMode=deptwiseOpdStatVo.getStrOrder().replace(",","");
				String strReportType=deptwiseOpdStatVo.getStrReportType();
				String strGraphOrText=deptwiseOpdStatVo.getStrGraphOrText();		
				String strReportName=deptwiseOpdStatVo.getTitle();
				String reportPath=deptwiseOpdStatVo.getReportPath();
				String strHospitalCode =uservo.getHospitalCode();
				String strFromDate =deptwiseOpdStatVo.getStrFromDate();
				String strToDate =deptwiseOpdStatVo.getStrToDate();
				String strTitle =deptwiseOpdStatVo.getTitle();
				String strFromHour=deptwiseOpdStatVo.getFromHour();
				String strFromMin=deptwiseOpdStatVo.getStrFromMin();
				String strToHour=deptwiseOpdStatVo.getToHour();
				String strToMin=deptwiseOpdStatVo.getStrToMin();
				String strFromMonth=deptwiseOpdStatVo.getFromMonth();
				String strToMonth=deptwiseOpdStatVo.getToMonth();
				String strFromMonthYear=deptwiseOpdStatVo.getFromMonthYear();
				String strToMonthYear=deptwiseOpdStatVo.getToMonthYear();
				String strFromYear=deptwiseOpdStatVo.getFromYear();
				String strToYear=deptwiseOpdStatVo.getToYear();				

						
				System.out.println("----strReportType-----"+strReportType+"--------");

				if(strReportType.equalsIgnoreCase("1"))
					reportFormat = "pdf";
				else if(strReportType.equalsIgnoreCase("3"))
					reportFormat = "xls";

				params.put("mode", "1");
				if(deptwiseOpdStatVo.getStrChoice().equalsIgnoreCase("D"))//Date Wise
				{
					params.put("period_Choice", "D");
					System.out.println("----mode Date Wise-----------");

				}
				else if(deptwiseOpdStatVo.getStrChoice().equalsIgnoreCase("T"))//Today Mode
				{
					System.out.println("----mode Today-----------");
					params.put("period_Choice", "T");
					String sysDate=WebUTIL.getCustomisedSysDate(Date, "dd-MMM-yyyy");
					strFromDate=sysDate+" "+strFromHour+":"+strFromMin;
					strToDate=sysDate+" "+strToHour+":"+strToMin;

				}	
				else if(deptwiseOpdStatVo.getStrChoice().equalsIgnoreCase("M"))//Month Wise
				{
					params.put("period_Choice", "M");
					System.out.println("----mode Month Wise-----------");
					strFromYear=strFromMonthYear;strToYear=strToMonthYear;
					strFromDate=strFromMonth+"-"+strFromYear;
					strToDate=strToMonth+"-"+strToYear;
				}	
				else if(deptwiseOpdStatVo.getStrChoice().equalsIgnoreCase("Y"))//Year Wise
				{
					params.put("period_Choice", "Y");
					System.out.println("----mode Year Wise-----------");
					strFromDate="1-JAN"+"-"+strFromYear;
					strToDate="31-DEC"+"-"+strToYear;
				}	
				
				
		/*		p_report_mode character varying, 
				p_hospital_code character varying, 
				p_service_area_code character varying, 
				p_procedure_code character varying,
				 p_department_code character varying,
				 p_service_type character varying, 
				 p_choice_filter character varying,
				 p_choice_filter_value character varying, 
				 p_choice_interval character varying,
				 p_from_date character varying,
				 p_from_mon character varying, 
				 p_from_year character varying, 
				 p_to_date character varying,
				 p_to_mon character varying, 
				 p_to_year character varying,
				 p_report_type character varying*/
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
				//params.put("dept_code", "0");
				/*if(strPatCategoryCode.equals("0"))
				params.put("pat_cat_code", strPatCategoryCode);
				else
					params.put("pat_cat_code", strPatCategoryCode.substring(0,7 ));*/
				/*if(strPatCategoryCode.equals("0"))
					params.put("service_Area",strPatCategoryCode); 
					else
						params.put("service_Area",strPatCategoryCode.substring(0,7 ));*/ 
				//params.put("service_type", strServiceType);
				params.put("report_mode", "1");
			//	params.put("service_Area",service_Area); 
				
				params.put("p_report_type",reportFormat); 
				//params.put("p_department_code","0"); 
				params.put("p_choice_interval",deptwiseOpdStatVo.getStrChoice()); 
				params.put("p_choice_filter_value",strMode); 
				params.put("p_choice_filter","1"); 
				//params.put("p_service_type",strServiceType); 
				params.put("orderBy", strMode);
				 
				//if(strMode.equalsIgnoreCase("1, 1"))
					reportPath = "/mrd/reports/rptDesign/mRDDiagnosticStatisticsReport.rptdesign";
				
				
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
			
				//System.out.println("----strDepartmentCode-----"+strDepartmentCode+"--------");
				//System.out.println("----strPatCategoryCode-----"+strPatCategoryCode+"--------");
				//System.out.println("----strServiceType-----"+strServiceType+"--------");
				//System.out.println("----strMode-----"+strMode+"--------");
				System.out.println("----reportPath-----"+reportPath+"--------");


				ts.displayReport(objRequest, objResponse, reportPath, reportFormat,
						params,strHospitalCode);

			} catch (Exception e) {			
					e.printStackTrace();
			}
	}
	
	public static void showMorbirityReport(MRDDiagnosticStatisticsReportVO morbiVo,HttpServletRequest objRequest, HttpServletResponse objResponse){
		NewReportUtil ts = new NewReportUtil();
		String reportFormat = "html";
		

		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
				boolean footerVisible=false;
				UserVO uservo=ControllerUTIL.getUserVO(objRequest);	
				Date Date=(Date)objRequest.getSession().getAttribute("sysDate");

				String strMode=morbiVo.getStrOrder().replace(",","");
				String strReportType=morbiVo.getStrReportType();
				String strGraphOrText=morbiVo.getStrGraphOrText();		
				String strReportName=morbiVo.getTitle();
				String reportPath=morbiVo.getReportPath();
				String strHospitalCode =uservo.getHospitalCode();
				String strFromDate =morbiVo.getStrFromDate();
				String strToDate =morbiVo.getStrToDate();
				String strTitle =morbiVo.getTitle();
				String strFromHour=morbiVo.getFromHour();
				String strFromMin=morbiVo.getStrFromMin();
				String strToHour=morbiVo.getToHour();
				String strToMin=morbiVo.getStrToMin();
				String strFromMonth=morbiVo.getFromMonth();
				String strToMonth=morbiVo.getToMonth();
				String strFromMonthYear=morbiVo.getFromMonthYear();
				String strToMonthYear=morbiVo.getToMonthYear();
				String strFromYear=morbiVo.getFromYear();
				String strToYear=morbiVo.getToYear();				

						
				System.out.println("----strReportType-----"+strReportType+"--------");

				if(strReportType.equalsIgnoreCase("1"))
					reportFormat = "pdf";
				else if(strReportType.equalsIgnoreCase("3"))
					reportFormat = "xls";

				params.put("mode", "1");
				if(morbiVo.getStrChoice().equalsIgnoreCase("D"))//Date Wise
				{
					params.put("period_Choice", "D");
					System.out.println("----mode Date Wise-----------");

				}
				else if(morbiVo.getStrChoice().equalsIgnoreCase("T"))//Today Mode
				{
					System.out.println("----mode Today-----------");
					params.put("period_Choice", "T");
					String sysDate=WebUTIL.getCustomisedSysDate(Date, "dd-MMM-yyyy");
					strFromDate=sysDate+" "+strFromHour+":"+strFromMin;
					strToDate=sysDate+" "+strToHour+":"+strToMin;

				}	
				else if(morbiVo.getStrChoice().equalsIgnoreCase("M"))//Month Wise
				{
					params.put("period_Choice", "M");
					System.out.println("----mode Month Wise-----------");
					strFromYear=strFromMonthYear;strToYear=strToMonthYear;
					strFromDate=strFromMonth+"-"+strFromYear;
					strToDate=strToMonth+"-"+strToYear;
				}	
				else if(morbiVo.getStrChoice().equalsIgnoreCase("Y"))//Year Wise
				{
					params.put("period_Choice", "Y");
					System.out.println("----mode Year Wise-----------");
					strFromDate="1-JAN"+"-"+strFromYear;
					strToDate="31-DEC"+"-"+strToYear;
				}	
				
				
		/*		p_report_mode character varying, 
				p_hospital_code character varying, 
				p_service_area_code character varying, 
				p_procedure_code character varying,
				 p_department_code character varying,
				 p_service_type character varying, 
				 p_choice_filter character varying,
				 p_choice_filter_value character varying, 
				 p_choice_interval character varying,
				 p_from_date character varying,
				 p_from_mon character varying, 
				 p_from_year character varying, 
				 p_to_date character varying,
				 p_to_mon character varying, 
				 p_to_year character varying,
				 p_report_type character varying*/
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
				//params.put("dept_code", "0");
				/*if(strPatCategoryCode.equals("0"))
				params.put("pat_cat_code", strPatCategoryCode);
				else
					params.put("pat_cat_code", strPatCategoryCode.substring(0,7 ));*/
				/*if(strPatCategoryCode.equals("0"))
					params.put("service_Area",strPatCategoryCode); 
					else
						params.put("service_Area",strPatCategoryCode.substring(0,7 ));*/ 
				//params.put("service_type", strServiceType);
				params.put("report_mode", "2");
			//	params.put("service_Area",service_Area); 
				
				params.put("p_report_type",reportFormat); 
				//params.put("p_department_code","0"); 
				params.put("p_choice_interval",morbiVo.getStrChoice()); 
				params.put("p_choice_filter_value",strMode); 
				params.put("p_choice_filter","1"); 
				//params.put("p_service_type",strServiceType); 
				params.put("p_orderby", strMode);
				 
				//if(strMode.equalsIgnoreCase("1, 1"))
					reportPath = "/mrd/reports/rptDesign/morbidityDiagnosticReportWithPtDetail.rptdesign";
				
				
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
			
				//System.out.println("----strDepartmentCode-----"+strDepartmentCode+"--------");
				//System.out.println("----strPatCategoryCode-----"+strPatCategoryCode+"--------");
				//System.out.println("----strServiceType-----"+strServiceType+"--------");
				//System.out.println("----strMode-----"+strMode+"--------");
				System.out.println("----reportPath-----"+reportPath+"--------");


				ts.displayReport(objRequest, objResponse, reportPath, reportFormat,
						params,strHospitalCode);

			} catch (Exception e) {			
					e.printStackTrace();
			}
	}
	
	public static void showMortalityReport(MRDDiagnosticStatisticsReportVO morbiVo,HttpServletRequest objRequest, HttpServletResponse objResponse){
		NewReportUtil ts = new NewReportUtil();
		String reportFormat = "html";
		

		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
				boolean footerVisible=false;
				UserVO uservo=ControllerUTIL.getUserVO(objRequest);	
				Date Date=(Date)objRequest.getSession().getAttribute("sysDate");

				String strMode=morbiVo.getStrOrder().replace(",","");
				String strReportType=morbiVo.getStrReportType();
				String strGraphOrText=morbiVo.getStrGraphOrText();		
				String strReportName=morbiVo.getTitle();
				String reportPath=morbiVo.getReportPath();
				String strHospitalCode =uservo.getHospitalCode();
				String strFromDate =morbiVo.getStrFromDate();
				String strToDate =morbiVo.getStrToDate();
				String strTitle =morbiVo.getTitle();
				String strFromHour=morbiVo.getFromHour();
				String strFromMin=morbiVo.getStrFromMin();
				String strToHour=morbiVo.getToHour();
				String strToMin=morbiVo.getStrToMin();
				String strFromMonth=morbiVo.getFromMonth();
				String strToMonth=morbiVo.getToMonth();
				String strFromMonthYear=morbiVo.getFromMonthYear();
				String strToMonthYear=morbiVo.getToMonthYear();
				String strFromYear=morbiVo.getFromYear();
				String strToYear=morbiVo.getToYear();				

						
				System.out.println("----strReportType-----"+strReportType+"--------");

				if(strReportType.equalsIgnoreCase("1"))
					reportFormat = "pdf";
				else if(strReportType.equalsIgnoreCase("3"))
					reportFormat = "xls";

				params.put("mode", "1");
				if(morbiVo.getStrChoice().equalsIgnoreCase("D"))//Date Wise
				{
					params.put("period_Choice", "D");
					System.out.println("----mode Date Wise-----------");

				}
				else if(morbiVo.getStrChoice().equalsIgnoreCase("T"))//Today Mode
				{
					System.out.println("----mode Today-----------");
					params.put("period_Choice", "T");
					String sysDate=WebUTIL.getCustomisedSysDate(Date, "dd-MMM-yyyy");
					strFromDate=sysDate+" "+strFromHour+":"+strFromMin;
					strToDate=sysDate+" "+strToHour+":"+strToMin;

				}	
				else if(morbiVo.getStrChoice().equalsIgnoreCase("M"))//Month Wise
				{
					params.put("period_Choice", "M");
					System.out.println("----mode Month Wise-----------");
					strFromYear=strFromMonthYear;strToYear=strToMonthYear;
					strFromDate=strFromMonth+"-"+strFromYear;
					strToDate=strToMonth+"-"+strToYear;
				}	
				else if(morbiVo.getStrChoice().equalsIgnoreCase("Y"))//Year Wise
				{
					params.put("period_Choice", "Y");
					System.out.println("----mode Year Wise-----------");
					strFromDate="1-JAN"+"-"+strFromYear;
					strToDate="31-DEC"+"-"+strToYear;
				}	
				
				
		/*		p_report_mode character varying, 
				p_hospital_code character varying, 
				p_service_area_code character varying, 
				p_procedure_code character varying,
				 p_department_code character varying,
				 p_service_type character varying, 
				 p_choice_filter character varying,
				 p_choice_filter_value character varying, 
				 p_choice_interval character varying,
				 p_from_date character varying,
				 p_from_mon character varying, 
				 p_from_year character varying, 
				 p_to_date character varying,
				 p_to_mon character varying, 
				 p_to_year character varying,
				 p_report_type character varying*/
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
				//params.put("dept_code", "0");
				/*if(strPatCategoryCode.equals("0"))
				params.put("pat_cat_code", strPatCategoryCode);
				else
					params.put("pat_cat_code", strPatCategoryCode.substring(0,7 ));*/
				/*if(strPatCategoryCode.equals("0"))
					params.put("service_Area",strPatCategoryCode); 
					else
						params.put("service_Area",strPatCategoryCode.substring(0,7 ));*/ 
				//params.put("service_type", strServiceType);
				params.put("report_mode", "3");
			//	params.put("service_Area",service_Area); 
				
				params.put("p_report_type",reportFormat); 
				//params.put("p_department_code","0"); 
				params.put("p_choice_interval",morbiVo.getStrChoice()); 
				params.put("p_choice_filter_value",strMode); 
				params.put("p_choice_filter","1"); 
				//params.put("p_service_type",strServiceType); 
				params.put("p_orderby", strMode);
				 
				//if(strMode.equalsIgnoreCase("1, 1"))
					reportPath = "/mrd/reports/rptDesign/mortalityDiagnosticReportWithPtDetail.rptdesign";
				
				
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
			
				//System.out.println("----strDepartmentCode-----"+strDepartmentCode+"--------");
				//System.out.println("----strPatCategoryCode-----"+strPatCategoryCode+"--------");
				//System.out.println("----strServiceType-----"+strServiceType+"--------");
				//System.out.println("----strMode-----"+strMode+"--------");
				System.out.println("----reportPath-----"+reportPath+"--------");


				ts.displayReport(objRequest, objResponse, reportPath, reportFormat,
						params,strHospitalCode);

			} catch (Exception e) {			
					e.printStackTrace();
			}
	}
	
	public static void showNotifyReport(MRDDiagnosticStatisticsReportVO morbiVo,HttpServletRequest objRequest, HttpServletResponse objResponse){
		NewReportUtil ts = new NewReportUtil();
		String reportFormat = "html";
		

		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
				boolean footerVisible=false;
				UserVO uservo=ControllerUTIL.getUserVO(objRequest);	
				Date Date=(Date)objRequest.getSession().getAttribute("sysDate");

				String strMode=morbiVo.getStrOrder().replace(",","");
				String strReportType=morbiVo.getStrReportType();
				String strGraphOrText=morbiVo.getStrGraphOrText();		
				String strReportName=morbiVo.getTitle();
				String reportPath=morbiVo.getReportPath();
				String strHospitalCode =uservo.getHospitalCode();
				String strFromDate =morbiVo.getStrFromDate();
				String strToDate =morbiVo.getStrToDate();
				String strTitle =morbiVo.getTitle();
				String strFromHour=morbiVo.getFromHour();
				String strFromMin=morbiVo.getStrFromMin();
				String strToHour=morbiVo.getToHour();
				String strToMin=morbiVo.getStrToMin();
				String strFromMonth=morbiVo.getFromMonth();
				String strToMonth=morbiVo.getToMonth();
				String strFromMonthYear=morbiVo.getFromMonthYear();
				String strToMonthYear=morbiVo.getToMonthYear();
				String strFromYear=morbiVo.getFromYear();
				String strToYear=morbiVo.getToYear();				

						
				System.out.println("----strReportType-----"+strReportType+"--------");

				if(strReportType.equalsIgnoreCase("1"))
					reportFormat = "pdf";
				else if(strReportType.equalsIgnoreCase("3"))
					reportFormat = "xls";

				params.put("mode", "1");
				if(morbiVo.getStrChoice().equalsIgnoreCase("D"))//Date Wise
				{
					params.put("period_Choice", "D");
					System.out.println("----mode Date Wise-----------");

				}
				else if(morbiVo.getStrChoice().equalsIgnoreCase("T"))//Today Mode
				{
					System.out.println("----mode Today-----------");
					params.put("period_Choice", "T");
					String sysDate=WebUTIL.getCustomisedSysDate(Date, "dd-MMM-yyyy");
					strFromDate=sysDate+" "+strFromHour+":"+strFromMin;
					strToDate=sysDate+" "+strToHour+":"+strToMin;

				}	
				else if(morbiVo.getStrChoice().equalsIgnoreCase("M"))//Month Wise
				{
					params.put("period_Choice", "M");
					System.out.println("----mode Month Wise-----------");
					strFromYear=strFromMonthYear;strToYear=strToMonthYear;
					strFromDate=strFromMonth+"-"+strFromYear;
					strToDate=strToMonth+"-"+strToYear;
				}	
				else if(morbiVo.getStrChoice().equalsIgnoreCase("Y"))//Year Wise
				{
					params.put("period_Choice", "Y");
					System.out.println("----mode Year Wise-----------");
					strFromDate="1-JAN"+"-"+strFromYear;
					strToDate="31-DEC"+"-"+strToYear;
				}	
				
				
		/*		p_report_mode character varying, 
				p_hospital_code character varying, 
				p_service_area_code character varying, 
				p_procedure_code character varying,
				 p_department_code character varying,
				 p_service_type character varying, 
				 p_choice_filter character varying,
				 p_choice_filter_value character varying, 
				 p_choice_interval character varying,
				 p_from_date character varying,
				 p_from_mon character varying, 
				 p_from_year character varying, 
				 p_to_date character varying,
				 p_to_mon character varying, 
				 p_to_year character varying,
				 p_report_type character varying*/
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
				//params.put("dept_code", "0");
				/*if(strPatCategoryCode.equals("0"))
				params.put("pat_cat_code", strPatCategoryCode);
				else
					params.put("pat_cat_code", strPatCategoryCode.substring(0,7 ));*/
				/*if(strPatCategoryCode.equals("0"))
					params.put("service_Area",strPatCategoryCode); 
					else
						params.put("service_Area",strPatCategoryCode.substring(0,7 ));*/ 
				//params.put("service_type", strServiceType);
				params.put("report_mode", "4");
			//	params.put("service_Area",service_Area); 
				
				params.put("p_report_type",reportFormat); 
				//params.put("p_department_code","0"); 
				params.put("p_choice_interval",morbiVo.getStrChoice()); 
				params.put("p_choice_filter_value","1"); 
				params.put("p_choice_filter","1"); 
				//params.put("p_service_type",strServiceType); 
			
				 
				//if(strMode.equalsIgnoreCase("1, 1"))
					reportPath = "/mrd/reports/rptDesign/notifiableDiseaseStaticReport.rptdesign";
				
				
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
			
				//System.out.println("----strDepartmentCode-----"+strDepartmentCode+"--------");
				//System.out.println("----strPatCategoryCode-----"+strPatCategoryCode+"--------");
				//System.out.println("----strServiceType-----"+strServiceType+"--------");
				//System.out.println("----strMode-----"+strMode+"--------");
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
