package hr.pis.empReg.reports.controller.util;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.ReportUtil;
import hisglobal.presentation.Status;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import hr.pis.config.PisConfig;
import hr.pis.empReg.config.PrConfig;
import hr.pis.empReg.reports.controller.actionsupport.ListOfRegisteredEmpRptSUP;
import hr.pis.empReg.reports.controller.data.ListOfRegisteredEmpRptDATA;





import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.JSONValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ListOfRegisteredEmpRptUTIL extends ControllerUTIL
{
	
	private static final String prop_File_Path = "hr.pis.common.config.ResourceBundle_Pr";
	private static final String prop_File_Path_de = "hr.pis.common.config.ResourceBundle_Pr_de";
	
	private static final String prop_File_Path_global = "hr.pis.common.config.ResourceBundle_Common";
	private static final String prop_File_Path_de_global = "hr.pis.common.config.ResourceBundle_Common_de";
	
	public static void populateFormEssentials_AJAX(ListOfRegisteredEmpRptSUP objSUP, HttpServletRequest objRequest, HttpServletResponse objResponse, Map<Object, Object> mapSession) 
	{
		Status objStatus = new Status();
		String outputString = "";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		TransformerFactory trf = TransformerFactory.newInstance();
		Document responseDocument = null;
		Map<String, List<Entry>> mp = new HashMap<>();
		//Map<String, String> mpFilter = new HashMap<>();
		//Map<String, List<Entry>> mpToSetInSession = new HashMap<>();
		String strErrMsg = "";
		Element msgElement = null;
		//String filterIncWhReason = "";
		try {
			UserVO userVO = getUserVO(objRequest);
			userVO.setModuleId(PisConfig.MODULE_ID_PIS);
			responseDocument = dbf.newDocumentBuilder().newDocument();
			Element rootElement = responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);

			// Creating message Element specially for Error Message
			msgElement = responseDocument.createElement("msgs");
			rootElement.appendChild(msgElement);
			msgElement.setAttribute("msg", strErrMsg);

			Element defaultElement = responseDocument.createElement("defaults");
			rootElement.appendChild(defaultElement);
			
			//defaultElement.setAttribute("defaultIntSalaryTypeId",PisConfig.SALARY_TYPE_ID_FOR_GRADE);
			
			// Filter Element Value Setting
			Element filterElement = responseDocument.createElement("filters");
			rootElement.appendChild(filterElement);
						
			mp = ListOfRegisteredEmpRptDATA.getRegistrationRptFormEssentials(userVO);
						

			
			//mpToSetInSession.put(IncrementConfig.ESSENTIAL_BO_OPTION_DESIGNATION, mp.get(IncrementConfig.ESSENTIAL_BO_OPTION_DESIGNATION));						
			createOptionObject(mp.get(PrConfig.ESSENTIAL_BO_OPTION_DESIGNATION), responseDocument, "intEmpDesig");
			
			//mpToSetInSession.put(IncrementConfig.ESSENTIAL_BO_OPTION_DEPARTMENT, mp.get(IncrementConfig.ESSENTIAL_BO_OPTION_DEPARTMENT));						
			createOptionObject(mp.get(PrConfig.ESSENTIAL_BO_OPTION_DEPARTMENT), responseDocument, "intEmpDept");
			
			createOptionObject(mp.get(PrConfig.ESSENTIAL_BO_OPTION_GENDER), responseDocument, "intGender");
			
			
			//mpToSetInSession.put(IncrementConfig.ESSENTIAL_BO_OPTION_EMP_CLASS, mp.get(IncrementConfig.ESSENTIAL_BO_OPTION_EMP_CLASS));						
			createOptionObject(mp.get(PrConfig.ESSENTIAL_BO_OPTION_FINAL_STATUS), responseDocument, "intFinalStatus");
			
			//mpToSetInSession.put(IncrementConfig.ESSENTIAL_BO_OPTION_NATURE_OF_JOB, mp.get(IncrementConfig.ESSENTIAL_BO_OPTION_NATURE_OF_JOB));						
			createOptionObject(mp.get(PrConfig.ESSENTIAL_BO_OPTION_NATURE_OF_JOB), responseDocument, "intNatureOfJob");
			
			
			
			//WebUTIL.setMapInSession(mpToSetInSession, objRequest);

			java.io.CharArrayWriter baos = new java.io.CharArrayWriter();
			trf.newTransformer().transform(new DOMSource(responseDocument), new StreamResult(baos));
			outputString = baos.toString();
			System.out.println("outputString " + outputString);
		} catch (Exception e) {
			objStatus.add(Status.ERROR, "", "Error");
			e.printStackTrace();
		} finally {
			writeResponse(objResponse, outputString);
		}
	}
	
	
	
	private static void createOptionObject(List<Entry> list, Document responseDocument, String fieldName) {
		Element fieldElement = responseDocument.createElement(fieldName);
		for (Entry entry : list) {
			Element option = responseDocument.createElement("option");
			fieldElement.appendChild(option);

			option.setAttribute("value", entry.getValue());
			option.setAttribute("label", entry.getLabel());
		}
		responseDocument.getFirstChild().appendChild(fieldElement);
	}
	
	public static void writeResponse(HttpServletResponse resp, String output)
	{
		try
		{
			resp.reset();
			resp.setContentType("text/xml");
			resp.setCharacterEncoding("UTF-8");
			resp.setHeader("Cache-Control", "no-cache");
			System.out.println(output);
			resp.getWriter().write(output);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	public static void createReport(ListOfRegisteredEmpRptSUP form,HttpServletRequest objRequest,HttpServletResponse objResponse) throws Exception
	{
		try{
			
			UserVO userVO = getUserVO(objRequest);
			
			String strHospitalCode =userVO.getHospitalCode();
			
			
			Boolean Htype;
			
			if(form.getStrIsHeaderReq().equals("on"))
			{
				Htype=true;
			}
			else
			{
				Htype=false;
			}
			
			Boolean Ftype;
			
			if(form.getStrIsFooterReq().equals("on"))
			{
				Ftype=true;
			}
			else
			{
				Ftype=false;
			}
			
			Boolean Ltype;
			
			if(form.getStrIsLogoReq().equals("on"))
			{
				Ltype=true;
			}
			else
			{
				Ltype=false;
			}
			
			Boolean Wtype;
			
			if(form.getStrIsWatermarkReq().equals("on"))
			{
				Wtype=true;
			}
			else
			{
				Wtype=false;
			}
			
			String ReportType;
			if(form.getStrReportType().equals("2"))
			{
				 ReportType="PDF";
			}
			else if(form.getStrReportType().equals("3"))
			{
				 ReportType="xls";
			}
			else
			{
				 ReportType="HTML";
			}
			
			Boolean periodReq;
			
			if(form.getStrIsPeriodReq().equals("on"))
			{
				periodReq=true;
			}
			else
			{
				periodReq=false;
			}
			
			//e-english ,r-retional ,b-both
			
		String elabel1,elabel2,elabel3,elabel4,elabel5,elabel6,elabel7,elabel8,elabel9,elabel10,elabel11,elabel12,elabel13,elabel14,elabel15,elabel16,elabel17,elabel18,elabel19,elabel20,elabel21,elabel22,elabel23,elabel24;
		String rlabel1,rlabel2,rlabel3,rlabel4,rlabel5,rlabel6,rlabel7,rlabel8,rlabel9,rlabel10,rlabel11,rlabel12,rlabel13,rlabel14,rlabel15,rlabel16,rlabel17,rlabel18,rlabel19,rlabel20,rlabel21,rlabel22,rlabel23,rlabel24;
		String blabel1,blabel2,blabel3,blabel4,blabel5,blabel6,blabel7,blabel8,blabel9,blabel10,blabel11,blabel12,blabel13,blabel14,blabel15,blabel16,blabel17,blabel18,blabel19,blabel20,blabel21,blabel22,blabel23,blabel24;
			
		
	
		elabel1=HelperMethods.getQuery(1, "registration.rpt.title" ,prop_File_Path  );
		elabel2=HelperMethods.getQuery(1, "global.footer.heading" ,prop_File_Path_global );
		elabel3=HelperMethods.getQuery(1, "global.date" ,prop_File_Path_global  )+"/"+HelperMethods.getQuery(1, "global.time" ,prop_File_Path_global  );
		elabel4=HelperMethods.getQuery(1, "global.pageno" ,prop_File_Path_global  );
		
		
		
		
		rlabel1=HelperMethods.getQuery(1, "registration.rpt.title" ,prop_File_Path_de  );
		rlabel2=HelperMethods.getQuery(1, "global.footer.heading" ,prop_File_Path_de_global );
		rlabel3=HelperMethods.getQuery(1, "global.date" ,prop_File_Path_de_global  )+"/"+HelperMethods.getQuery(1, "global.time" ,prop_File_Path_de_global  );
		rlabel4=HelperMethods.getQuery(1, "global.pageno" ,prop_File_Path_de_global  );

		
		
		 blabel1=elabel1+" \n "+rlabel1;
		 blabel2=elabel2+" \n "+rlabel2;
		 blabel3=elabel3+" \n "+rlabel3;
		 blabel4=elabel4+" \n "+rlabel4;
		
		
		
		
		
		
		Map<String, Object> params = new HashMap<String, Object>();
		String reportPath ="/hr/pis/empReg/reports/rptDesigns/listOfRegisteredEmpRpt.rptdesign";
		
		
		params.put("isHeaderReq", Htype);
		params.put("isFooterReq", Ftype);
		params.put("isLogoReq",Ltype);
		params.put("isWatermarkReq",Wtype);
		params.put("isPeriodReq", periodReq);
		
		
	
		params.put("empno",form.getStrEmpNo());
		params.put("periodFrom",form.getDtPeriodFrom());
		params.put("periodTo",form.getDtPeriodTo());
		
		
		
		
		params.put("desigId", form.getIntEmpDesig());
		
		if(form.getIntEmpDesig().equals("-1"))
		{
			params.put("desig", "ALL");
		}
		else
		{
			params.put("desig", form.getStrEmpDesig());
		}
		
		params.put("deptId", form.getIntEmpDept());
		
		if(form.getIntEmpDept().equals("-1"))
		{
			params.put("dept", "ALL");
		}
		else
		{
			params.put("dept", form.getStrEmpDept());
		}
		
		params.put("genderId", form.getIntGender());
		
		if(form.getIntGender().equals("-1"))
		{
			params.put("gender", "ALL");
		}
		else
		{
			params.put("gender", form.getStrGender());
		}
		
		params.put("finalStatusId", form.getIntFinalStatus());
		
		if(form.getIntFinalStatus().equals("-1"))
		{
			params.put("finalStatus", "ALL");
		}
		else
		{
			params.put("finalStatus", form.getStrFinalStatus());
		}
		
		params.put("natureOfJobId", form.getIntNatureOfJob());
		
		if(form.getIntNatureOfJob().equals("-1"))
		{
			params.put("natureOfJob", "ALL");
		}
		else
		{
			params.put("natureOfJob", form.getStrNatureOfJob());
		}
		
		params.put("rptGenTypeId", form.getStrRptGenType());
		
		
		
		
		
		
		if((form.getStrReportType().equals("1")||form.getStrReportType().equals("3"))&& form.getStrLabelLang().equals("3"))
		{
		params.put("lRptHeading", blabel1);
		params.put("lFHeading", blabel2);
		params.put("lRptDateAndTime", blabel3);
		params.put("lRptPageNo", blabel4);
		
		
	
		}
		else if((form.getStrReportType().equals("1")||form.getStrReportType().equals("3"))&& form.getStrLabelLang().equals("2"))
		{
			params.put("lRptHeading", rlabel1);
			params.put("lFHeading", rlabel2);
			params.put("lRptDateAndTime", rlabel3);
			params.put("lRptPageNo", rlabel4);
			
			
			
		}
		else
		{
			params.put("lRptHeading", elabel1);
			params.put("lFHeading", elabel2);
			params.put("lRptDateAndTime", elabel3);
			params.put("lRptPageNo", elabel4);
			
			
			
		}
		
		
	
		System.out.println("form.getStrIsHeaderReq()" +Htype);
		System.out.println("form.getStrIsFooterReq()" +Ftype);
		System.out.println("Report Type" +ReportType);
		System.out.println("logo" +Ltype);
		System.out.println("Watermark" +Wtype);
		
		
		ReportUtil ts = new ReportUtil();
		ts.displayReport(objRequest, objResponse, reportPath, ReportType, params,strHospitalCode);
	
		
		}
		catch(Exception e)
		{
			System.out.println("Exception Occured in createReport method : ");
			 e.printStackTrace();
		}
	}
	
	
	
	
}
