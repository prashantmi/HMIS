/**
 * 
 */
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

import registration.reports.controller.data.UnitwiseOPDStatReportDATA;
import vo.registration.StatisticsReportVO;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.ReportUtil;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

/**
 * @author s.singaravelan
 * Creation Date:- 01-May-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 * 
 */
public class UnitwiseOPDStatReportUTIL extends ControllerUTIL{		
	
	@SuppressWarnings("rawtypes")
	public static void getEssentials(HttpServletRequest request) {
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		UnitwiseOPDStatReportDATA objUnitwiseOPDStatReportData_p = null;
		HttpSession session = request.getSession(true);  
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:MM");
		Map SessionMap=new HashMap();
		Document responseDocument=null;
		String outputString="";	
		
		try{
			
			objUnitwiseOPDStatReportData_p = new UnitwiseOPDStatReportDATA();
			setSysdate(request);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			List departmentList,patCategoryList=new ArrayList();			
			departmentList=objUnitwiseOPDStatReportData_p.getDeptList(uservo);	
			patCategoryList=objUnitwiseOPDStatReportData_p.getPatCategoryList(uservo);
			
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
			
			List unitList=UnitwiseOPDStatReportDATA.getUnitList(strDeptCode,userVO);			
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
	
	public static void showReport(StatisticsReportVO unitwiseOPDStatVo,HttpServletRequest objRequest, HttpServletResponse objResponse){
		ReportUtil ts = new ReportUtil();
		String reportFormat = "html";
		

		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
				boolean footerVisible=false;
				UserVO uservo=ControllerUTIL.getUserVO(objRequest);	
				Date Date=(Date)objRequest.getSession().getAttribute("sysDate");

				String strDepartmentCode=unitwiseOPDStatVo.getStrDeptCode();
				String strDepartmentUnitCode=unitwiseOPDStatVo.getStrUnitCode();
				String strPatCategoryCode=unitwiseOPDStatVo.getStrPatCategoryCode();
				String strServiceType=unitwiseOPDStatVo.getStrServiceType();
				String strMode=unitwiseOPDStatVo.getStrMode();

				String strReportType=unitwiseOPDStatVo.getStrReportType();
				String strGraphOrText=unitwiseOPDStatVo.getStrGraphOrText();		
				String strReportName=unitwiseOPDStatVo.getTitle();
				String reportPath=unitwiseOPDStatVo.getReportPath();
				String strHospitalCode =uservo.getHospitalCode();
				String strFromDate =unitwiseOPDStatVo.getStrFromDate();
				String strToDate =unitwiseOPDStatVo.getStrToDate();
				String strTitle =unitwiseOPDStatVo.getTitle();
				String strFromHour=unitwiseOPDStatVo.getFromHour();
				String strFromMin=unitwiseOPDStatVo.getStrFromMin();
				String strToHour=unitwiseOPDStatVo.getToHour();
				String strToMin=unitwiseOPDStatVo.getStrToMin();
				String strFromMonth=unitwiseOPDStatVo.getFromMonth();
				String strToMonth=unitwiseOPDStatVo.getToMonth();
				String strFromMonthYear=unitwiseOPDStatVo.getFromMonthYear();
				String strToMonthYear=unitwiseOPDStatVo.getToMonthYear();
				String strFromYear=unitwiseOPDStatVo.getFromYear();
				String strToYear=unitwiseOPDStatVo.getToYear();				

						
				System.out.println("----strReportType-----"+strReportType+"--------");

				if(strReportType.equalsIgnoreCase("1"))
					reportFormat = "pdf";
				else if(strReportType.equalsIgnoreCase("3"))
					reportFormat = "xls";

				params.put("mode", "1");
				if(unitwiseOPDStatVo.getStrChoice().equalsIgnoreCase("D"))//Date Wise
				{
					params.put("period_Choice", "D");
					System.out.println("----mode Date Wise-----------");

				}
				else if(unitwiseOPDStatVo.getStrChoice().equalsIgnoreCase("T"))//Today Mode
				{
					System.out.println("----mode Today-----------");
					params.put("period_Choice", "T");
					String sysDate=WebUTIL.getCustomisedSysDate(Date, "dd-MMM-yyyy");
					strFromDate=sysDate+" "+strFromHour+":"+strFromMin;
					strToDate=sysDate+" "+strToHour+":"+strToMin;

				}	
				else if(unitwiseOPDStatVo.getStrChoice().equalsIgnoreCase("M"))//Month Wise
				{
					params.put("period_Choice", "M");
					System.out.println("----mode Month Wise-----------");
					strFromYear=strFromMonthYear;strToYear=strToMonthYear;
					strFromDate=strFromMonth+"-"+strFromYear;
					strToDate=strToMonth+"-"+strToYear;
				}	
				else if(unitwiseOPDStatVo.getStrChoice().equalsIgnoreCase("Y"))//Year Wise
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
				params.put("pat_cat_code", strPatCategoryCode);
				params.put("service_type", strServiceType);
				params.put("report_mode", strMode);
				
				if(strMode.equalsIgnoreCase("1"))
					reportPath = "/registration/reports/rptDesign/unitwiseOPDStatReport.rptdesign";
				else
					reportPath = "/registration/reports/rptDesign/unitwiseOPDStatisticsDetailedReport.rptdesign";
				
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
				System.out.println("----strPatCategoryCode-----"+strPatCategoryCode+"--------");
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
