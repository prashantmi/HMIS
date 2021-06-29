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
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import registration.reports.controller.data.CategoryWiseCashCollectionReportDATA;

import vo.registration.CategoryWiseCashCollectionReportVO;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.ReportUtil;

import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

public class CategoryWiseCashCollectionReportUTIL extends ControllerUTIL
{
	@SuppressWarnings("rawtypes")
	public static void getEssentials(HttpServletRequest request) {
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		CategoryWiseCashCollectionReportDATA objCategoryWiseCashCollectionReportData_p = null;
		HttpSession session = request.getSession(true);  
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:MM");
		Map SessionMap=new HashMap();
		Document responseDocument=null;
		String outputString="";	
		
		try{
			
			objCategoryWiseCashCollectionReportData_p = new CategoryWiseCashCollectionReportDATA();
			setSysdate(request);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			 List UserList ,patCategoryList=new ArrayList();			
			
			patCategoryList=objCategoryWiseCashCollectionReportData_p.getPatCategoryList(uservo);
			UserList=objCategoryWiseCashCollectionReportData_p.getUserList(uservo);
			
			
			
			Date sysDate=(Date)(request.getSession().getAttribute(Config.SYSDATEOBJECT));
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			
			System.out.println("---"+sysDate+"------");
			System.out.println("Yesterday's date = "+ cal.getTime());			
			
			
			
			session.setAttribute("patCategoryList",patCategoryList);
			session.setAttribute("UserList",UserList);
			session.setAttribute("sysDate",sysDate);	
			session.setAttribute("yesterDay",cal.getTime());	

			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
	
	
	public static void showReport(CategoryWiseCashCollectionReportVO listReportVo,HttpServletRequest objRequest, HttpServletResponse objResponse){
		ReportUtil ts = new ReportUtil();
		String reportFormat = "html";
		

		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
				boolean footerVisible=false;
				UserVO uservo=ControllerUTIL.getUserVO(objRequest);	
				Date Date=(Date)objRequest.getSession().getAttribute("sysDate");

				
				String strPatCategoryCode=listReportVo.getStrPatCategoryCode();
				String strUserCode=listReportVo.getStrUserCode();
				String strSeatId=listReportVo.getStrSeatId();
				String strReportType=listReportVo.getStrReportType();
					
				String strReportName=listReportVo.getTitle();
				String reportPath=listReportVo.getReportPath();
				String strHospitalCode =uservo.getHospitalCode();
				String strFromDate =listReportVo.getStrFromDate();
				String strToDate =listReportVo.getStrToDate();
				String strTitle =listReportVo.getTitle();				
				
				if(strPatCategoryCode.equalsIgnoreCase("00"))
					strPatCategoryCode="%";
				if(strUserCode.equalsIgnoreCase("00"))
					strUserCode="%";
				
				
				
				System.out.println("----strReportType-----"+strReportType+"--------");

				if(strReportType.equalsIgnoreCase("1"))
					reportFormat = "pdf";
				else if(strReportType.equalsIgnoreCase("3"))
					reportFormat = "xls";

				
											
				params.put("report_Name", strTitle);
				params.put("report_Footer_Visible", footerVisible);				
				//params.put("report_user_Remarks", strUserRemarks);
				params.put("rpt_format", reportFormat);	
				params.put("hospCode", strHospitalCode);	
				params.put("fromDate", strFromDate);	
				params.put("toDate", strToDate);
				
				
				params.put("pat_cat_code", strPatCategoryCode);
				params.put("seatid", strUserCode);
				params.put("orderby", "1");

				
				
				reportPath = "/registration/reports/rptDesign/CategoryWiseCashCollectionReport.rptdesign";
				
				System.out.println("----strTitle-----"+strTitle+"--------");
				System.out.println("----strHospitalCode-----"+strHospitalCode+"--------");
				System.out.println("----strFromDate-----"+strFromDate+"--------");
				System.out.println("----strToDate-----"+strToDate+"--------");
				
				
				System.out.println("----strPatCategoryCode-----"+strPatCategoryCode+"--------");
				System.out.println("----strUserCode-----"+strUserCode+"--------");

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

