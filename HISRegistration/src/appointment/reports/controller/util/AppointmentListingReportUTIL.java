package appointment.reports.controller.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;

import org.w3c.dom.Document;

import vo.appointment.AppontmentListingReportVO;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.ReportUtil;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;

public class AppointmentListingReportUTIL extends ControllerUTIL {
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
	
	public static void showReport(AppontmentListingReportVO listReportVo,HttpServletRequest objRequest, HttpServletResponse objResponse){
		ReportUtil ts = new ReportUtil();
		String reportFormat = "html";
		Map<String, Object> params = new HashMap<String, Object>();
		String paraId[]={"0","0","0","0","0","0","0"};
		try {
			boolean footerVisible=false;
			UserVO uservo=ControllerUTIL.getUserVO(objRequest);	
			Date Date=(Date)objRequest.getSession().getAttribute("sysDate");
			String strReportType=listReportVo.getStrReportType();
			String strGraphOrText=listReportVo.getStrGraphOrText();		
			String strReportName=listReportVo.getTitle();
			String reportPath=listReportVo.getReportPath();
			String strHospitalCode =uservo.getHospitalCode();
			String userSeatId=uservo.getUserSeatId();
			String strFromDate =listReportVo.getStrFromDate();
			String strToDate =listReportVo.getStrToDate();
			String strTitle =listReportVo.getTitle();
			String strFromHour=listReportVo.getFromHour();
			String strFromMin=listReportVo.getStrFromMin();
			String strToHour=listReportVo.getToHour();
			String strToMin=listReportVo.getStrToMin();
			String strparaID=listReportVo.getParaId();
			String strRptDetail=objRequest.getParameter("reportTypeTitleMixed");
	
			String strAppointforID=listReportVo.getAppointmentDtl();
			String strstatus=listReportVo.getStatus();
			String _tmpParaId[]=strparaID.replace("^", "#").split("#");
			paraId=_tmpParaId;
			
			if(strAppointforID.indexOf("#")>0)
			strAppointforID=strAppointforID.split("#")[0];
						
			
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
			
			if(strstatus.equals("00"))
				strstatus="%";
			
			params.put("report_Name", strTitle);
			params.put("report_Footer_Visible", footerVisible);				
			//params.put("report_user_Remarks", strUserRemarks);
			params.put("rpt_format", reportFormat);	
			params.put("hospCode", strHospitalCode);	
			params.put("fromDate", strFromDate);	
			params.put("toDate", strToDate);
			params.put("ReportDetailsPara", strRptDetail.replaceAll("\\#", "\\-"));
			params.put("appointmentforid", strAppointforID);
			params.put("paraId1", paraId[0]);
			params.put("paraId2", paraId[1]);
			params.put("paraId3", paraId[2]);
			params.put("paraId4", paraId[3]);
			params.put("paraId5", paraId[4]);
			params.put("paraId6", paraId[5]);
			params.put("paraId7", paraId[6]);
			params.put("status",strstatus );
			params.put("seatid",userSeatId );
			
			
			
			
			reportPath = "/appointment/report/rptDesign/AppointmentListingReport.rptdesign";
			
			
			System.out.println("----strTitle-----"+strTitle+"--------");
			System.out.println("----strHospitalCode-----"+strHospitalCode+"--------");
			System.out.println("----strFromDate-----"+strFromDate+"--------");
			System.out.println("----strToDate-----"+strToDate+"--------");
			System.out.println("----strFromHour-----"+strFromHour+"--------");
			System.out.println("----strFromMin-----"+strFromMin+"--------");
			System.out.println("----strToHour-----"+strToHour+"--------");
			System.out.println("----strToMin-----"+strToMin+"--------");
			System.out.println("----strparaID-----"+strparaID+"--------");
			System.out.println("----strAppointforID-----"+strAppointforID+"--------");
			System.out.println("----ReportDetailsPara-----"+strRptDetail+"--------");
			System.out.println("----strstatus-----"+strstatus+"--------");
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
