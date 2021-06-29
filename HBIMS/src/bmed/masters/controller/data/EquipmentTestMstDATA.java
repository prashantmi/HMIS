package bmed.masters.controller.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javax.sql.rowset.WebRowSet;

import hisglobal.ReportUtil;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.masters.bo.EquipmentTestMstBO;
import bmed.transactions.bo.EquipmentInspectionTestDtlsBO;
import bmed.masters.controller.fb.EquipmentTestMstFB;
import bmed.masters.vo.EquipmentTestMstVO;
import bmed.vo.TestDtlVO;


/**
 * @author:-	Vivek Aggarwal   
 * Creation Date:- 11-Jan-2011 
 * Modifying Date:- 24-Jan-2011
 * Used For:-	
 * Team Lead By:-	
 *  Module:- BMED(HIS Project)
 * 
 */
public class EquipmentTestMstDATA 
{

	/**
	 * To get Engineering Item Type Combo populated.
	 * 
	 * @param formBean the EquipmentTest Master FormBean
	 * @param request the HttpServletRequest
	 */
	public static void initializeAdd(EquipmentTestMstFB formBean, HttpServletRequest request) 
	{
		
		String strMsgText;
		HisUtil hisutil = null;
		EquipmentTestMstBO bo = null;
		EquipmentTestMstVO vo = null;
		
		
		String strEngineeringItemTypeCmb=null;
		String arr_strListPageCombos[]=null;
		String strEngineeringItemSubTypeCmb=null;

		try 
		{
			hisutil = new HisUtil("bmed", "EquipmentTestMstDATA");
			
			vo = new EquipmentTestMstVO();
			bo = new EquipmentTestMstBO();
			
			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String ctDate = hisutil.getASDate("dd-MMM-yyyy");
			
			arr_strListPageCombos=formBean.getCombo();
			
			if(arr_strListPageCombos!=null && arr_strListPageCombos.length >= 2 ) {
				formBean.setStrEngineeringItemTypeId(arr_strListPageCombos[0]);
				formBean.setStrEngineeringItemSubTypeId(arr_strListPageCombos[1]);
				
			}
			else {
				formBean.setStrEngineeringItemTypeId("0");
				formBean.setStrEngineeringItemSubTypeId("0");
			}
			
			formBean.setStrCtDate(ctDate);
			
			TransferObjectFactory.populateData(vo, formBean);

			vo.setStrHospitalCode(hosCode);
			
// Calling BO object
			bo.initializeAdd(vo);
			
			if (vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			
			if (vo.getStrEngineeringItemTypeWS() != null && vo.getStrEngineeringItemTypeWS().size() > 0) 
			{
				strEngineeringItemTypeCmb = hisutil.getOptionValue(vo.getStrEngineeringItemTypeWS(), vo.getStrEngineeringItemTypeId(),"0^Select Value", false);
			}
			
			else
			{
				strEngineeringItemTypeCmb = "<option value='0'>Select Value</option>";
			}
			
			if (vo.getStrEngineeringItemSubWS() != null && vo.getStrEngineeringItemSubWS().size() > 0) 
			{
				strEngineeringItemSubTypeCmb = hisutil.getOptionValue(vo.getStrEngineeringItemSubWS(), vo.getStrEngineeringItemSubTypeId(),"0^Select Value", false);
			}
			
			else
			{
				strEngineeringItemSubTypeCmb = "<option value='0'>Select Value</option>";
			}
			
			formBean.setStrEngineeringItemTypeCmb(strEngineeringItemTypeCmb); 
			formBean.setStrEngineeringItemSubCmb(strEngineeringItemSubTypeCmb); 

		} 
		catch (Exception e) 
		{
			strMsgText = "EquipmentTestMstDATA.getEnggItemTypeCmbValues(vo) --> " + e.getMessage();
		HisException eObj = new HisException("bmed","EquipmentTestMstDATA.getEnggItemTypeCmbValues()", strMsgText);
		formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
		
		eObj = null;
		}
		finally 
		{
			hisutil = null;
			bo = null;
			vo = null;
		}
	}
		
	
	/**
	 * To get Engineering Item Sub Type Combo populated.
	 * 
	 * @param formBean the EquipmentTest Master form bean
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 */
	public static void getEnggItemSubTypeCmb(EquipmentTestMstFB formBean,HttpServletRequest request, HttpServletResponse response) 
	{
		String strMsgText = "";
		HisUtil hisutil = null;
		EquipmentTestMstBO bo = null;
		EquipmentTestMstVO vo = null;
		
		String strEngineeringItemTypeId = "";
		String strEngineeringItemSubTypeWS = "";
		
		try 
		{
			hisutil = new HisUtil("bmed", "EquipmentTestMstDATA");
			
			vo = new EquipmentTestMstVO();
			bo = new EquipmentTestMstBO();
			
			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
	
			strEngineeringItemTypeId = request.getParameter("enggItemType");
			vo.setStrHospitalCode(hosCode);
			vo.setStrEngineeringItemTypeId(strEngineeringItemTypeId);
			
			bo.getStrEngineeringItemSubType(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			
			if (vo.getStrEngineeringItemSubWS() != null && vo.getStrEngineeringItemSubWS().size() > 0) 
			{
				strEngineeringItemSubTypeWS = hisutil.getOptionValue(vo.getStrEngineeringItemSubWS(),"","0^Select Value", false);
			}
			
			else
			{
				strEngineeringItemSubTypeWS = "<option value='0'>Select Value</option>";
			}
			
			   response.setHeader("Cache-Control", "no-cache");
			   response.getWriter().print(strEngineeringItemSubTypeWS);
			
		} 
		catch (Exception e) 
		{
			strMsgText = "EquipmentTestMstDATA.getEnggItemSubTypeCmb(vo) --> " + e.getMessage();
		HisException eObj = new HisException("bmed", "EquipmentTestMstDATA.getEnggItemSubTypeCmb()", strMsgText);
		
			try 
			{
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");
	
			}
			catch (Exception e1)
			{
				new HisException("bmed","EquipmentTestMstDATA.getEnggItemSubTypeCmb()", strMsgText);
			}

		eObj = null;
		} 
		finally
		{
			hisutil = null;
			vo = null;
			bo = null;
		}

	}

	
	/**
	 * to insert the data.
	 * 
	 * @param formBean the form bean
	 * @param request the request
	 */
	public static void insertRecord(EquipmentTestMstFB formBean,	HttpServletRequest request) 
	{
		EquipmentTestMstBO bo = null;
		EquipmentTestMstVO vo = null;
		String strmsgText = "";
		try {
			bo = new EquipmentTestMstBO();
			vo = new EquipmentTestMstVO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String seatid = request.getSession().getAttribute("SEATID").toString();

			formBean.setStrHospitalCode(hosCode);
			formBean.setStrSeatId(seatid);

			vo.setStrTestName(formBean.getStrTestName());	
		//	vo.setStrEngineeringItemTypeId(formBean.getStrEngineeringItemTypeId());
		//	vo.setStrEngineeringItemSubTypeId(formBean.getStrEngineeringItemSubTypeId());
			vo.setStrEffectiveFrom(formBean.getStrEffectiveFrom());
			vo.setStrRemarks(formBean.getStrRemarks());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());	//from session
			vo.setStrSeatId(formBean.getStrSeatId());	//from session

			bo.insertRecord(vo);
			
			if (vo.getStrMsgType().equals("1")) 
			{
				formBean.setStrRetValue("0");
				throw new Exception(vo.getStrMsgString());
			}

			if (vo.getBExistStatus() == false) {
				formBean.setStrWarningMsg("EquipmentTest Name already exist !");
			}
			else {
				formBean.setStrRetValue("1");
				formBean.setStrNormalMsg("Record Saved Successfully!");
			}

		}
		catch (Exception e) 
		{

			//e.printStackTrace();
			
			strmsgText = "EquipmentTestMstDATA.insertRecord(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"EquipmentTestMstDATA->insertRecord()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}
		finally 
		{
			bo = null;
			vo = null;
		}

	}

	/**
	 * to get the data for modify page.
	 * 
	 * @param formBean the form bean
	 * @param request the request
	 */
	public static void modifyRecord(EquipmentTestMstFB formBean,	HttpServletRequest request) 
	{
		EquipmentTestMstBO bo = null;
		EquipmentTestMstVO vo = null;
		String strTemp[] = null;
		String strTemp2[] = null;
		String strmsgText = "";
		String strChk = "";
		String seatid = null;

		
		try {
			bo = new EquipmentTestMstBO();
			vo = new EquipmentTestMstVO();
            //String strCombos[] = request.getParameterValues("combo");

			strChk = request.getParameter("chk");
			
			seatid=request.getSession().getAttribute("SEATID").toString();
			
			//chk value::		10@108$6
            // Primary Key::	HEMNUM_TESK_ID@GNUM_HOSPITAL_CODE

			if(strChk!=null && !strChk.trim().equals("")) {
				
				strTemp = strChk.replace('@', '#').split("#");
				strTemp2 = strTemp[1].replace("$", "#").split("#");

				vo.setStrTestId(strTemp[0]);
				vo.setStrHospitalCode(strTemp2[0]);
				
			} else {
				vo.setStrTestId(formBean.getStrTestId());
				vo.setStrHospitalCode(formBean.getStrHospitalCode());
			}
			
			vo.setStrSeatId(seatid);

			//vo.setStrEngineeringItemTypeId(strCombos[0]);
			//vo.setStrEngineeringItemSubTypeId(strCombos[1]);
			
			
			

			bo.modifyRecord(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			
			formBean.setStrEngineeringItemTypeName(vo.getStrEngineeringItemTypeName());
			formBean.setStrEngineeringItemSubTypeName(vo.getStrEngineeringItemSubTypeName());
			formBean.setStrTestName(vo.getStrTestName());
			formBean.setStrEffectiveFrom(vo.getStrEffectiveFrom());
			formBean.setStrRemarks(vo.getStrRemarks());
			formBean.setStrIsValid(vo.getStrIsValid());
			formBean.setStrEngineeringItemTypeId(vo.getStrEngineeringItemTypeId());
			formBean.setStrEngineeringItemSubTypeId(vo.getStrEngineeringItemSubTypeId());

		} 
		catch (Exception e) 
		{
			strmsgText = "EquipmentTestMstDATA.modifyRecord(fb,request) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed","EquipmentTestMstDATA->modifyRecord()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}
		finally 
		{
			bo = null;
			vo = null;
		}

	}

	/**
	 * to update the record after modifying it.
	 * 
	 * @param formBean the form bean
	 * @param request the request
	 * 
	 * @return true, if update record
	 */
	public static boolean updateRecord(EquipmentTestMstFB formBean,HttpServletRequest request) 
	{
		EquipmentTestMstBO bo = null;
		EquipmentTestMstVO vo = null;
		boolean bReturnValue = true;
		String strTemp[] = null;
		String strTemp2[] = null;
		String strMsgText;
		String strChk = "";

		//String strCombos[] = request.getParameterValues("combo");

		try {
			bo = new EquipmentTestMstBO();
			vo = new EquipmentTestMstVO();
			
            strChk = request.getParameter("chk");
			
			//chk value::		10@108$6
            // Primary Key::	HEMNUM_TEST_ID@GNUM_HOSPITAL_CODE
            
			strTemp = strChk.replace('@', '#').split("#");
			
			strTemp2 = strTemp[1].replace("$", "#").split("#");

			String seatid = request.getSession().getAttribute("SEATID").toString();

			vo.setStrTestId(strTemp[0]);
			vo.setStrHospitalCode(strTemp2[0]);
			vo.setStrSeatId(seatid);

			//vo.setStrEngineeringItemTypeId(strCombos[0]);
			//vo.setStrEngineeringItemSubTypeId(strCombos[1]);
						
			formBean.setStrChk(strChk);

			vo.setStrTestName(formBean.getStrTestName());
			vo.setStrEffectiveFrom(formBean.getStrEffectiveFrom());
			vo.setStrRemarks(formBean.getStrRemarks());
			vo.setStrIsValid(formBean.getStrIsValid());
			
			bo.updateRecord(vo);

				if (vo.getStrMsgType().equals("1")) 
				{
					formBean.setStrRetValue("0");
					throw new Exception(vo.getStrMsgString());
				}
				
				if (vo.getBExistStatus() == false) 
				{
					formBean.setStrWarningMsg("EquipmentTest Name already exist !");
					bReturnValue = false;

				}
				else 
				{
						formBean.setStrNormalMsg("Record Modified Successfully");
						formBean.setStrRetValue("1");

				}
			} 
			catch (Exception e) 
			{
			bReturnValue = false;
			strMsgText = "EquipmentTestMstDATA.updateRecord(vo) --> " + e.getMessage();
			HisException eObj = new HisException("bmed","EquipmentTestMstDATA->updateRecord()", strMsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
			} 
			finally 
			{
			bo = null;
			vo = null;
			}
				
			return bReturnValue;
	}
	
	/**
	 * This method is used to show Report using BIRT Template
	 * 
	 * @param EquipmentTestMstFB the EquipmentTestMstFB
	 * @param request	the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 */
	
	public static void showReport(EquipmentTestMstFB equipmentTestMstFB_p,HttpServletRequest request_p, HttpServletResponse response_p) {
	
		
		ReportUtil ts = new ReportUtil();
		
		String reportFormat = "html";
		String strPassId="";
	
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
			 
			String strHospitalCode = equipmentTestMstFB_p.getStrHospitalCode();
			String strReportId     = equipmentTestMstFB_p.getStrReportId();
			String strUserRemarks  = equipmentTestMstFB_p.getStrRemarks();
			
			
		    reportFormat = equipmentTestMstFB_p.getStrReportFormat();
			          
			
			
			boolean footerVisible = true;
			
			if (equipmentTestMstFB_p.getStrIsFooter().equals("1")) 
			{
				footerVisible = false;
				
			}
			
			String strReportName = "Equipment Test and Inspection Desk";
	
			//params.put("mode", "1");
			//params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", true);
			params.put("report_user_Remarks", strUserRemarks);
		//	params.put("hospCode", strHospitalCode);
			//params.put("strPassValue", strPassId);
			
			
			
				
				String reportPath = "/bmed/reports/jsp/Test_and_Inspection_TestCreation_report.rptdesign";
				//ts.displayReport(request_p, response_p, reportPath, reportFormat,params);
			
		
		} 
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("BMED", "EquipmentTestMstDATA->setInitDtl()", strmsgText);
			equipmentTestMstFB_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
		
		
	}
	public static void print(HttpServletRequest request, HttpServletResponse response,EquipmentTestMstFB fb)
	{
		EquipmentInspectionTestDtlsBO bo = null;
		TestDtlVO vo = null;
        String strPvalues,strmsgText = "";
        String strPrintPage;
		try 
		{
			vo = new TestDtlVO();
			bo = new EquipmentInspectionTestDtlsBO();

			strPvalues= request.getParameter("checkedVal");
			System.out.println(" Combo Values as ...."+strPvalues);

			vo.setStrEquTestId(strPvalues.split("\\@")[0]);
			vo.setStrTestId(strPvalues.split("\\@")[1]);
			vo.setStrHospitalCode(strPvalues.split("\\@")[2].split("\\$")[0]);
			bo.getPrintData(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			
			strPrintPage = getPrintPage(vo);
			System.out.println("To be send to the response"+strPrintPage);
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strPrintPage);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "ComplaintMaintenanceStatusDATA.getUploadedFile --> "+ e.getMessage();
			HisException eObj = new HisException("bmed","ComplaintMaintenanceStatusDATA->getUploadedFile()", strmsgText);
			fb.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
	
		} 
		finally
		{
			vo = null;
			bo = null;
		}
	}
	private static String getPrintPage(TestDtlVO vo)
	{
		StringBuffer sb = new StringBuffer();
		ResourceBundle res = null;
		Date date;
		SimpleDateFormat ft = new SimpleDateFormat ("dd.MMM.yyyy hh:mm a");
		     
		if (res == null) 
		{
			res = ResourceBundle.getBundle("hisglobal.hisconfig.hisProperties");
		}
		String strTableWidth = "650";
		String test = "Test";
		String prvsTestDt;
		/*   	0			1			2			3			4			5		  6				7
		 * Group Name@ Item Name @ Model Name@ Serial No.@ Manufacturer @ Price @ Supplied By @ Contact Person
		 *  8	  9	     10			11			 12			  13			 14				  15		16
		 * From @ To @ Address @ Contact No. @ Email Id @ Hospital Name @ Hospital Type @ Lab Name @ Location 
		 *   17		  18		19	   20		  21			22				 23						24			   25
		 * Block @ Building @ Floor @ Room @ Dr. InCharge @ Received Date @ Installation Date @ Company Contact No @ Remarks
		
		Computer@Dell@Vostro N Series@223@Dell Ltd@34500@Dell Ltd@Anshuman@04-Oct-2013@04-Oct-2017@Mohali@N/A@N/A@SMS Jaipur DH@Dist. Hospital@SMS Jaipur Labs@ICU@A1@A1-2@1@101@0@04-Oct-2013@16-Apr-2014@987654321
		*/
		try 
		{
			WebRowSet dtlResultSet = vo.getWrsData();
			WebRowSet paramResultSet = vo.getWrsParamData();
			WebRowSet otherResultSet = vo.getWrsOthersData();
			WebRowSet prvsResultSet = vo.getWrsPrvsRsultData();
			date = new Date();
			// CSS
			dtlResultSet.next();otherResultSet.next();prvsResultSet.next();
			if(prvsResultSet.size()<1)
			{
				prvsTestDt = "N/A";
			}
			sb.append("<style > .heading1{font-size: 16px;font-weight: bold;} " +
					 ".heading2{font-size: large;font-weight: bold;font-family: Verdana;}" +
					 ".heading3{font-size: 14px;font-family: Times New Roman;}"+
					 ".heading4{position: relative;left: 275px;top: -60px;font-size: 25px;"+
					 "font-style: oblique;font-variant: small-caps;font-weight: bold;width:700px;}"+
					 ".heading5{left: 415px;position: relative;top: -85px;font-size: 20px;"+
					 "font-variant: small-caps;font-weight: bolder;text-decoration: underline;width:520px;}"+
					 ".right{border-right: medium none;}.left{border-left: medium none;}</style>");
			// Header Section
			sb.append("<img width='50px' height='50px' style='position: relative;left: 220px;top: 15px;' src='../../hisglobal/images/osmc.gif' >");
			sb.append("<p class='heading4' width='720px'>"+"ODHISHA STATE MEDICAL CORPORATION"+"</p>" + "<p class='heading5' width='520px'>Equipment " + dtlResultSet.getString(33) + " Report</p>");
			sb.append("<div id='printid1'  style='position: relative;top: -80px;'  align='right'>"+
						"<img onclick='window.print();' src='../../hisglobal/images/printer_symbol.gif' title='Print Page' style='cursor: pointer;'>"+
						"<img onclick='window.close();' src='../../hisglobal/images/stop.png' title='Cancel Process' style='cursor: pointer;'></div>");
			
			//Page Section
			sb.append("<center style='position: relative;top: -70px;'>");
			sb.append("<table width='" + strTableWidth + "px' border='1px' style='border-spacing: 0px;border-bottom: medium none;'>");
			
			sb.append("<tr><td colspan='2' width='25%' class='heading2 right' >Equipment Details</td>");
			sb.append("<td colspan='2' width='25%' align='right' class='left'>Date & Time: "+ ft.format(date)+"</td></tr>");
		
			sb.append("<tr><td width='18%' class='heading1 right'>Hospital Name :</td>");
			sb.append("<td width='32%' class='left heading3' >"+ dtlResultSet.getString(26) +"</td>");
			sb.append("<td width='15%' class='heading1 right'>Hospital Type :</td>" +
					  "<td width='35%' class='left heading3' >"+ otherResultSet.getString(1) +"</td></tr>");	
			
			
			sb.append("<tr><td width='18%' class='heading1 right'>Lab Name :</td>");
			sb.append("<td width='32%' class='left heading3' >"+ dtlResultSet.getString(27) +"</td>");
			sb.append("<td width='15%' class='heading1 right'>Location :</td>" +
					  "<td width='35%' class='left heading3' >"+ otherResultSet.getString(2) +"</td></tr>");	

			sb.append("<tr><td width='18%' class='heading1 right'>Group :</td>");
			sb.append("<td width='32%' class='left heading3' >"+ otherResultSet.getString(3) +"</td>");
			sb.append("<td width='15%' class='heading1 right'>Item Name :</td>" +
					  "<td width='35%' class='left heading3' >"+ dtlResultSet.getString(31) +"</td></tr>");	

			sb.append("<tr><td width='18%' class='heading1 right'>Model :</td>");
			sb.append("<td width='32%' class='left heading3' >"+ dtlResultSet.getString(32) +"</td>");
			sb.append("<td width='15%' class='heading1 right'>Serial No. :</td>" +
					  "<td width='35%' class='left heading3' >"+ dtlResultSet.getString(10) +"</td></tr>");	
			
			sb.append("<tr><td width='18%' class='heading1 right'>Manufacturer :</td>");
			sb.append("<td width='32%' class='left heading3' >"+ otherResultSet.getString(4) +"</td>");
			sb.append("<td width='15%' class='heading1 right'>Dr. In Charge :</td>" +
					  "<td width='35%' class='left heading3' >"+ otherResultSet.getString(5) +"</td></tr>");	

			sb.append("<tr><td colspan='4' width='25%' class='heading2 right'>"+ dtlResultSet.getString(33) + " Details</td></tr>");

			sb.append("<tr><td width='20%' class='heading1 right'>" + dtlResultSet.getString(33) + " Name :</td>");
			sb.append("<td width='30%' class='left heading3' >"+ dtlResultSet.getString(25) +"</td>");
			sb.append("<td width='20%' class='heading1 right'>" + dtlResultSet.getString(33) + " Result :</td>" +
					  "<td width='30%' class='left heading3' >"+ dtlResultSet.getString(19) +"</td></tr>");	
			
			sb.append("<tr><td width='20%' class='heading1 right'>" + dtlResultSet.getString(33) +" Date :</td>");
			sb.append("<td width='30%' class='left heading3' >"+ dtlResultSet.getString(17) +"</td>");
			sb.append("<td width='20%' class='heading1 right'>" + dtlResultSet.getString(33) + " By :</td>" +
					  "<td width='30%' class='left heading3' >"+ dtlResultSet.getString(16) +"</td></tr>");	

			sb.append("<tr><td width='20%' class='heading1 right'>Recommend By :</td>");
			sb.append("<td width='30%' class='left heading3' >"+ "--" +"</td>");
			sb.append("<td width='20%' class='heading1 right'>Previous " + dtlResultSet.getString(33) +"/Result :</td>"); 
			if(prvsResultSet.size()<1)
			{
				sb.append("<td width='30%' class='left heading3' > N/A </td></tr>");	
			}
			else
			{
				sb.append("<td width='30%' class='left heading3' >"+ prvsResultSet.getString(2)+"/"+prvsResultSet.getString(3) +"</td></tr>");	
			}
			sb.append("<tr><td colspan='1' width='25%' class='heading1 right'>Remarks :</td>");
			sb.append("<td colspan='3' class='left heading3' >"+ dtlResultSet.getString(20) +"</td></tr></table>");


			sb.append("<table width='" + strTableWidth + "px' border='1px' style='border-spacing: 0px;border-top: medium none;'>");
			sb.append("<tr><td colspan='4' width='25%' class='heading2 right'>Parameter Details</td></tr>");
			
			sb.append("<tr><td width='8%' class='heading1' style='text-align: center;'>Srl No.</td>");
			sb.append("<td width='62%'  colspan='2' class='heading1' style='text-align: center;'>Parameter Name</td>");
			sb.append("<td width='30%' class='heading1' style='text-align: center;'>Output :</td></tr>");
			int i = 1;
			while(paramResultSet.next())
			{
				sb.append("<tr><td width='8%' class='heading3' style='text-align: center;'>"+ i++ +"</td>");
				sb.append("<td width='62%'  colspan='2' class='heading3' style='text-align: center;'>"+ paramResultSet.getString(1) +"</td>");
				sb.append("<td width='30%' class='heading3' style='text-align: center;'>"+ paramResultSet.getString(2) +"</td></tr>");
			}	
			sb.append("</table><table width='"+ strTableWidth +"'px style='margin-top:25px;'>"+
						"<tr><td width='50%' align='left'>Approved By :</td>"+
						"<td width='50%' align='center'>Date :</td></tr></table></center>");

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sb.toString();
	}
	
}
