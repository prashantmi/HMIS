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

import registration.transactions.controller.data.NewRegistrationDATA;
import vo.registration.MlcPatientReportVO;
import hisglobal.config.HISConfig;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.ReportUtil;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

public class MlcPatientReportUTIL extends ControllerUTIL{
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
				setSysdate(request);
				Date sysDate=(Date)(request.getSession().getAttribute(Config.SYSDATEOBJECT));
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -1);
				
				System.out.println("---"+sysDate+"------");
				System.out.println("Yesterday's date = "+ cal.getTime());		
				session.setAttribute("sysDate",sysDate);	
				session.setAttribute("yesterDay",cal.getTime());	
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		
	}
	
	public static void showReport(MlcPatientReportVO listReportVo,HttpServletRequest objRequest, HttpServletResponse objResponse){
		ReportUtil ts = new ReportUtil();
		String reportFormat = "html";
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			boolean footerVisible=false;
			UserVO uservo=ControllerUTIL.getUserVO(objRequest);	
			Date Date=(Date)objRequest.getSession().getAttribute("sysDate");
			String strReportType=listReportVo.getStrReportType();
			String strGraphOrText=listReportVo.getStrGraphOrText();		
			String strReportName=listReportVo.getTitle();
			String reportPath=listReportVo.getReportPath();
			String strHospitalCode =uservo.getHospitalCode();
			String strFromDate =listReportVo.getStrFromDate();
			String strToDate =listReportVo.getStrToDate();
			String strTitle =listReportVo.getTitle();
			String strFromHour=listReportVo.getFromHour();
			String strFromMin=listReportVo.getStrFromMin();
			String strToHour=listReportVo.getToHour();
			String strToMin=listReportVo.getStrToMin();
			System.out.println("----strReportType-----"+strReportType+"--------");
			if(strReportType.equalsIgnoreCase("1"))
				reportFormat = "pdf";
			else if(strReportType.equalsIgnoreCase("3"))
				reportFormat = "xls";
			if(listReportVo.getStrChoice().equalsIgnoreCase("1"))//Date Wise
			{
				params.put("mode", "1");
				System.out.println("----mode 1-----------");

			}
			else//Today Mode
			{
				System.out.println("----mode 2-----------");
				params.put("mode", "2");
				String sysDate=WebUTIL.getCustomisedSysDate(Date, "dd-MMM-yyyy");
				strFromDate=sysDate+" "+strFromHour+":"+strFromMin;
				strToDate=sysDate+" "+strToHour+":"+strToMin;

			}						
			
			params.put("report_Name", strTitle);
			params.put("report_Footer_Visible", footerVisible);				
			//params.put("report_user_Remarks", strUserRemarks);
			params.put("rpt_format", reportFormat);	
			params.put("hospCode", strHospitalCode);	
			params.put("fromDate", strFromDate);	
			params.put("toDate", strToDate);
			reportPath = "/registration/reports/rptDesign/MlcPatientReport.rptdesign";
			
			
			System.out.println("----strTitle-----"+strTitle+"--------");
			System.out.println("----strHospitalCode-----"+strHospitalCode+"--------");
			System.out.println("----strFromDate-----"+strFromDate+"--------");
			System.out.println("----strToDate-----"+strToDate+"--------");
			System.out.println("----strFromHour-----"+strFromHour+"--------");
			System.out.println("----strFromMin-----"+strFromMin+"--------");
			System.out.println("----strToHour-----"+strToHour+"--------");
			System.out.println("----strToMin-----"+strToMin+"--------");
			
			ts.displayReport(objRequest, objResponse, reportPath, reportFormat,
					params,strHospitalCode);
		}
		 catch (Exception e) {			
				e.printStackTrace();
		       }
		
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
