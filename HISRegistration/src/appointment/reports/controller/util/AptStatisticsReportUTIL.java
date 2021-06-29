package appointment.reports.controller.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.ReportUtil;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;

import org.w3c.dom.Document;

import vo.appointment.AptStatisticsVo;

public class AptStatisticsReportUTIL extends ControllerUTIL
{
	@SuppressWarnings("rawtypes")
	public static void getEssentials(HttpServletRequest request)
	{
	DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
	TransformerFactory trf= TransformerFactory.newInstance();
	
	HttpSession session = request.getSession(true);  
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:MM");
	Map SessionMap=new HashMap();
	Document responseDocument=null;
	String outputString="";	
   
  try 
   {   setSysdate(request);
       UserVO uservo = ControllerUTIL.getUserVO(request);	
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
	
	public static void showReport(AptStatisticsVo statReportVo,HttpServletRequest objRequest, HttpServletResponse objResponse){
		ReportUtil ts = new ReportUtil();
		String reportFormat = "html";
		

		Map<String, Object> params = new HashMap<String, Object>();
		String paraId[]={"0","0","0","0","0","0","0"};
		
		
		try {
			
				boolean footerVisible=false;
				UserVO uservo=ControllerUTIL.getUserVO(objRequest);	
				Date Date=(Date)objRequest.getSession().getAttribute("sysDate");

				String strReportType=statReportVo.getStrReportType();
				String strGraphOrText=statReportVo.getStrGraphOrText();		
				String strReportName=statReportVo.getTitle();
				String reportPath=statReportVo.getReportPath();
				String strHospitalCode =uservo.getHospitalCode();
				String strFromDate =statReportVo.getStrFromDate();
				String strToDate =statReportVo.getStrToDate();
				String strTitle =statReportVo.getTitle();
				String strFromHour=statReportVo.getFromHour();
				String strFromMin=statReportVo.getStrFromMin();
				String strToHour=statReportVo.getToHour();
				String strToMin=statReportVo.getStrToMin();
				String strFromMonth=statReportVo.getFromMonth();
				String strToMonth=statReportVo.getToMonth();
				String strFromMonthYear=statReportVo.getFromMonthYear();
				String strToMonthYear=statReportVo.getToMonthYear();
				String strFromYear=statReportVo.getFromYear();
				String strToYear=statReportVo.getToYear();				
				String strparaID=statReportVo.getParaId();
				String strAppointforID=statReportVo.getAppointmentDtl();
				String strMode=statReportVo.getStrMode();
				String strRptDetail=objRequest.getParameter("reportForTitle");
				
				String _tmpParaId[]=strparaID.replace("^", "#").split("#");
				paraId=_tmpParaId;
				
				if(strAppointforID.indexOf("#")>0)
					strAppointforID=strAppointforID.split("#")[0];
				
				System.out.println("----strReportType-----"+strReportType+"--------");

				if(strReportType.equalsIgnoreCase("1"))
					reportFormat = "pdf";
				else if(strReportType.equalsIgnoreCase("3"))
					reportFormat = "xls";

				params.put("mode", "1");
				if(statReportVo.getStrChoice().equalsIgnoreCase("D"))//Date Wise
				{
					params.put("period_choice", "D");
					System.out.println("----mode Date Wise-----------");

				}
				else if(statReportVo.getStrChoice().equalsIgnoreCase("T"))//Today Mode
				{
					System.out.println("----mode Today-----------");
					params.put("period_choice", "T");
					String sysDate=WebUTIL.getCustomisedSysDate(Date, "dd-MMM-yyyy");
					strFromDate=sysDate+" "+strFromHour+":"+strFromMin;
					strToDate=sysDate+" "+strToHour+":"+strToMin;

				}	
				else if(statReportVo.getStrChoice().equalsIgnoreCase("M"))//Month Wise
				{
					params.put("period_choice", "M");
					System.out.println("----mode Month Wise-----------");
					strFromYear=strFromMonthYear;strToYear=strToMonthYear;
					strFromDate=strFromMonth+"-"+strFromYear;
					strToDate=strToMonth+"-"+strToYear;
				}	
				else if(statReportVo.getStrChoice().equalsIgnoreCase("Y"))//Year Wise
				{
					params.put("period_choice", "Y");
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
				params.put("ReportDetailsPara", strRptDetail.replaceAll("\\#", "\\-"));
				params.put("report_mode", strMode);
				params.put("fromMonth", strFromMonth);	
				params.put("toMonth", strToMonth);
				params.put("fromYear", strFromYear);	
				params.put("toYear", strToYear);
				params.put("appointmentforid", strAppointforID);
				params.put("paraId1", paraId[0]);
				params.put("paraId2", paraId[1]);
				params.put("paraId3", paraId[2]);
				params.put("paraId4", paraId[3]);
				params.put("paraId5", paraId[4]);
				params.put("paraId6", paraId[5]);
				params.put("paraId7", paraId[6]);
				
				if(strMode.equalsIgnoreCase("1"))
				reportPath = "/appointment/report/rptDesign/AptStatisticsReport.rptdesign";
				else
					reportPath = "/appointment/report/rptDesign/AptStatisticsDetailedReport.rptdesign";
				
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
				System.out.println("----strparaID-----"+strparaID+"--------");
				System.out.println("----strAppointforID-----"+strAppointforID+"--------");
				System.out.println("----strMode-----"+strMode+"--------");

				ts.displayReport(objRequest, objResponse, reportPath, reportFormat,
						params,strHospitalCode);

			} catch (Exception e) {			
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

