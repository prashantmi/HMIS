package ipd.masters.bo;

import ipd.masters.dao.WardCriteriaMstDAO;
import ipd.masters.vo.WardCriteriaMstVO;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;

public class WardCriteriaMstBO {

	/**
	 * First it will check duplicate record is there or not,<br>
	 * If duplicate record is there then it display a message "Record Not Saved!Data Already Exist!!"<br>
	 * If there is no duplicate record then it saved the record<br>
	 * And get back to the list page<br>
	 * But if some error is occurs then it will show the message "Record Not Saved!!!"<br>
	 * @param vo - FormBean Object
	 * @return true- Record Saved Successfully!!
	 * false- Record Not Saved!!
	 */
public String InsertRecord(WardCriteriaMstVO vo) {

	String target = "";
	boolean retvalue;
    String msg ="";
    String strErrorMsg ="";
	try{
		

	retvalue = WardCriteriaMstDAO.insert(vo);
	
	if (!retvalue) {
		strErrorMsg = "Record not saved successfully!";
		vo.setStrErrorMsg(strErrorMsg);
		target = "add";
	} else {
		msg ="Record Saved Successfully!";
		vo.setStrmsg(msg);
		
		target = "add";
	}
   
}catch(Exception e){
	vo.setStrErrorMsg(e.getMessage());
	   HisException eObj = new HisException("IPD-->Ward Criteria", "BOWardCriteria-->InsertRecord()", vo.getStrErrorMsg());
	   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
	   eObj = null;
}		
	return target;
}

/**
 * Retrieve the content from the record to modify it.
 * 
 * @param strChk1 - Primary Keys Concatenated with '@'.
 * @param vo - FormBean Object
 * @throws Exception
 */
public void modifyRecord(String chk1, WardCriteriaMstVO vo)  {
	try{
	  WardCriteriaMstDAO.modifyQuery(chk1, vo);
	}catch(Exception e){
		vo.setStrErrorMsg(e.getMessage());
		   HisException eObj = new HisException("IPD-->Ward Criteria", "BOWardCriteria-->modifyRecord()", vo.getStrErrorMsg());
		   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		   eObj = null;
    }		
}

/**
 * Update method returns true if Record Updated Successfully false if Record
 * Not Updated Successfully
 * 
 * @param strChk1 - Primary Keys Concatenated with '@'.
 * @param vo - Form Object of the Current Master
 * @return boolean Value
 */
public String updateRecord(String chk1, WardCriteriaMstVO vo)
{
String strtarget = "";
boolean fretvalue;
String strerrmsg ="";
try{

fretvalue = WardCriteriaMstDAO.update(chk1,vo);
if (!fretvalue) {

strerrmsg = "Record not modified successfully!";
	vo.setStrErrorMsg(strerrmsg);
	strtarget = "modify";
} else {

	strtarget = "list";
}

}catch(Exception e){
	vo.setStrErrorMsg(e.getMessage());
	   HisException eObj = new HisException("IPD-->Ward Criteria", "BOWardCriteria-->updateRecord()", vo.getStrErrorMsg());
	   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
	   eObj = null;
}
return strtarget;
}

public void getRoomValues(WardCriteriaMstVO _Vo,HttpServletResponse response){
	try{
		WardCriteriaMstDAO.getRoomValues(_Vo);
		response.getWriter().println(_Vo.getStrRoomNoValues());
	}catch(Exception _E){
		_E.printStackTrace();
		HisException eObj = new HisException("IPD-->Ward Criteria", "BOWardCriteria-->getRoomValues()", _E.getMessage());
		_Vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
	}
}
public static void showReport(WardCriteriaMstVO formBean,
		HttpServletRequest request, HttpServletResponse response) {

	ReportUtil ts = new ReportUtil();

	String reportPath = "/ipd/reports/wardcriteria_ipdrpt.rptdesign";
	String reportFormat = "html";
	
	Map<String, Object> params = new HashMap<String, Object>();
	try {

		reportFormat = formBean.getStrReportFormat();
		
		String strHospitalCode = formBean.getStrHospitalCode();
		String strReportId = formBean.getStrReportId();
		String strReportName = "Criteria for Patient Admission in Ward / Room";
		String strUserRemarks = formBean.getStrUserRemarks();

		boolean footerVisible = true;
		
		if (formBean.getStrIsFooter().equals("1")) {
			footerVisible = false;
		}

		reportFormat = formBean.getStrReportFormat();

		params.put("report_id", strReportId);
		params.put("report_Name", strReportName);
		params.put("report_Footer_Visible", footerVisible);
		params.put("report_user_Remarks", strUserRemarks);
		params.put("hosp_Code", strHospitalCode);
	
		ts.displayReport(request, response, reportPath, reportFormat,
				params,strHospitalCode);

	} catch (Exception e) {

		e.printStackTrace();

	}
}
}