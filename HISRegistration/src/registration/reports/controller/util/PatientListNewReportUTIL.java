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

import registration.reports.controller.data.PatientListNewReportDATA;
import registration.transactions.controller.data.NewRegistrationDATA;
import vo.registration.PatientListReportVO;
import hisglobal.config.HISConfig;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.ReportUtil;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;


/**
* this patient listing new report util file for multiple hospitals
*/

public class PatientListNewReportUTIL extends ControllerUTIL {
	     @SuppressWarnings("rawtypes")
public static void getEssentials(HttpServletRequest request) {
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		PatientListNewReportDATA objPatientListReportData_p = null;
		HttpSession session = request.getSession(true);  
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:MM");
		Map SessionMap=new HashMap();
		Document responseDocument=null;
		String outputString="";	
		
		try{
			
			objPatientListReportData_p = new PatientListNewReportDATA();
			setSysdate(request);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			List departmentList,patCategoryList,HospitalList= new ArrayList();				
			departmentList=objPatientListReportData_p.getDeptList(uservo);	
			patCategoryList=objPatientListReportData_p.getPatCategoryList(uservo);
			HospitalList=objPatientListReportData_p.getHospitalList(uservo);
			//Date sysDate=(Date)formatter.parse((String)request.getSession().getAttribute(HISConfig.SYSDATEOBJECT));
			
			Date sysDate=(Date)(request.getSession().getAttribute(Config.SYSDATEOBJECT));
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			
			System.out.println("---"+sysDate+"------");
			System.out.println("Yesterday's date = "+ cal.getTime());			
			
			session.setAttribute("departmentList",departmentList);
			session.setAttribute("unitList",new ArrayList());	
			session.setAttribute("patCategoryList",patCategoryList);
			session.setAttribute("HospitalList",HospitalList);
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
		
		List unitList=PatientListNewReportDATA.getUnitList(strDeptCode,userVO);			
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
public static void showReport(PatientListReportVO listReportVo,HttpServletRequest objRequest, HttpServletResponse objResponse){
	ReportUtil ts = new ReportUtil();
	String reportFormat = "html";
	

	Map<String, Object> params = new HashMap<String, Object>();
	try {
		
			boolean footerVisible=false;
			UserVO uservo=ControllerUTIL.getUserVO(objRequest);	
			Date Date=(Date)objRequest.getSession().getAttribute("sysDate");

			String strDepartmentCode=listReportVo.getStrDeptCode();
			String strUnitCode=listReportVo.getStrUnitCode();
			String strPatCategoryCode=listReportVo.getStrPatCategoryCode();
			String strPatVisit=listReportVo.getStrPatVisit();
			String strReportType=listReportVo.getStrReportType();
			String strGraphOrText=listReportVo.getStrGraphOrText();		
			String strReportName=listReportVo.getTitle();
			String reportPath=listReportVo.getReportPath();
			String strHospitalCode =listReportVo.getHospitalCode();
			String strFromDate =listReportVo.getStrFromDate();
			String strToDate =listReportVo.getStrToDate();
			String strTitle =listReportVo.getTitle();
			String strFromHour=listReportVo.getFromHour();
			String strFromMin=listReportVo.getStrFromMin();
			String strToHour=listReportVo.getToHour();
			String strToMin=listReportVo.getStrToMin();
			String strOrderBy=listReportVo.getStrOrderBy();
			String strGroupBy=listReportVo.getStrGroupBy();
			if(strGroupBy==""||strGroupBy==null)
				strGroupBy="3";
			
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
			params.put("dept_code", strDepartmentCode);
			params.put("dept_unit_code", strUnitCode);
			params.put("pat_cat_code", strPatCategoryCode);
			params.put("pat_visit_type", strPatVisit);
			params.put("orderBy", strOrderBy);
			params.put("groupBy", strGroupBy);

			
			reportPath = "/registration/reports/rptDesign/patientListingReportNew.rptdesign";
			
			System.out.println("----strTitle-----"+strTitle+"--------");
			System.out.println("----strHospitalCode-----"+strHospitalCode+"--------");
			System.out.println("----strFromDate-----"+strFromDate+"--------");
			System.out.println("----strToDate-----"+strToDate+"--------");
			System.out.println("----strFromHour-----"+strFromHour+"--------");
			System.out.println("----strFromMin-----"+strFromMin+"--------");
			System.out.println("----strToHour-----"+strToHour+"--------");
			System.out.println("----strToMin-----"+strToMin+"--------");
		
			System.out.println("----strDepartmentCode-----"+strDepartmentCode+"--------");
			System.out.println("----strUnitCode-----"+strUnitCode+"--------");
			System.out.println("----strPatCategoryCode-----"+strPatCategoryCode+"--------");
			System.out.println("----strPatVisit-----"+strPatVisit+"--------");
			System.out.println("----strOrderBy-----"+strOrderBy+"--------");
			System.out.println("----strGroupBy-----"+strGroupBy+"--------");

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
