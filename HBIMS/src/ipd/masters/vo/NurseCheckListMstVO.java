package ipd.masters.vo;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;
import ipd.DAOIpd;
import ipd.masters.controller.hlp.NurseCheckListMstHLP;
import ipd.masters.dao.NurseCheckListMstDAO;

import java.sql.SQLException;

import javax.sql.rowset.WebRowSet;

public class NurseCheckListMstVO extends GenericFormBean{

	private static final long serialVersionUID = 02L;
	
	private String strCheckListName = "";
	private String strCheckListType = "0";
	private String strCheckListFor = "0";
	private String strEffectiveFromDate = null;
	private String strRemarks = "";
	private String[] strDepartment = null;
	private String[] strUnit = null;
	private String strActive = "0";
	private String strDeptTempName = "0";
	private String strUnitTempName = "0";
	private String strDeptmentValues = null;
	private String strHospitalCode="";
	private int nTotalRows = 0; 
	private String strTotalRows;
	private String strMultiRow = null; 
	private String strSeatId = "";
	private String strLastModSeatId = "";
	private String strErr = "";
	private String strMsg = "";
	private String strWarning = "";
	private String strCtDate = null;
	private String strCheckListUnit="";
	private String strCheckListDataType="1";
	private String strCheckListDefaultVal="";

	
	public String getStrCheckListUnit() {
		return strCheckListUnit;
	}

	public void setStrCheckListUnit(String strCheckListUnit) {
		this.strCheckListUnit = strCheckListUnit;
	}

	/**
	 * retrieves Current Date From Application Server and returns the Same.
	 * @return - Current Date in String Format 
	 */
	public String getStrCtDate() {
		
		HisUtil util = new HisUtil("Nurse Check List Master", "VONurseCheckListMst");

		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
		
		} catch (Exception e) {

			new HisException("Nurse Check List Master",
					"VONurseCheckListMst.getStrCtDate()", e.getMessage());
		}finally{
			util = null;
		}
		
		return strCtDate;
	}
	
	public String getStrErr() {
		return strErr;
	}
	public void setStrErr(String strErr) {
		this.strErr = strErr;
	}
	public String getStrMsg() {
		return strMsg;
	}
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
	public String getStrWarning() {
		return strWarning;
	}
	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}
	public String getStrCheckListName() {
		return strCheckListName;
	}
	public void setStrCheckListName(String strCheckListName) {
		this.strCheckListName = strCheckListName;
	}
	public String getStrCheckListType() {
		return strCheckListType;
	}
	public void setStrCheckListType(String strCheckListType) {
		this.strCheckListType = strCheckListType;
	}
	/**
	 * retrieves Current Date From Application Server and returns the Same.
	 * @return - Current Date in String Format 
	 */
	public String getStrEffectiveFromDate() {
		
		/*HisUtil util = new HisUtil("Nurse Check List Master", "VONurseCheckListMst");

		try {
			strEffectiveFromDate = util.getASDate("dd-MMM-yyyy");

		} catch (Exception e) {

			new HisException("Nurse Check List Master",
					"VONurseCheckListMst.getStrCtDate()", e.getMessage());
		} finally {
			util = null;
		}*/

		return strEffectiveFromDate;
	}
	
	public void setStrEffectiveFromDate(String strEffectiveFromDate) {
		this.strEffectiveFromDate = strEffectiveFromDate;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String[] getStrDepartment() {
		return strDepartment;
	}
	public void setStrDepartment(String[] strDepartment) {
		this.strDepartment = strDepartment;
	}
	public String[] getStrUnit() {
		return strUnit;
	}
	public void setStrUnit(String[] strUnit) {
		this.strUnit = strUnit;
	}
	public String getStrDeptTempName() {
		return strDeptTempName;
	}
	public void setStrDeptTempName(String strDeptTempName) {
		this.strDeptTempName = strDeptTempName;
	}
	public String getStrUnitTempName() {
		return strUnitTempName;
	}
	public void setStrUnitTempName(String strUnitTempName) {
		this.strUnitTempName = strUnitTempName;
	}
	/**
	 * used to return List of Values for Department Combo
	 * 
	 * @return Department Options in String format.
	 */
	public String getStrDeptmentValues() {

		DAOIpd dao = new DAOIpd("Nurse Check List Master", "VONurseCheckListMst");
		HisUtil util = new HisUtil("Nurse Check List Master",
				"VONurseCheckListMst");
		WebRowSet ws = null;
		try {
			ws = dao.getDepartmentDtl("", true,this.getStrHospitalCode());
			strDeptmentValues = util.getOptionValue(ws, this.getStrDeptTempName(), "0^Select Value",
					false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			new HisException("Nurse Check List Master",
					"VONurseCheckListMst.getStrDeptmentValues -->", e
							.getMessage());
		} finally {
			dao = null;
			util = null;
			if (ws != null) {
				try {
					ws.close();
				} catch (SQLException e) {
					new HisException("Nurse Check List Master",
							"VONurseCheckListMst.ws.close -->", e.getMessage());
				}
				ws = null;
			}
		}

		return strDeptmentValues;
	}

	/**
	 * used to return List of Values for Unit Combo
	 * 
	 * @return Unit Options in String format.
	 */
	public String getStrUnitValues() {

		String strUnitValues="";
		try {
			strUnitValues=NurseCheckListMstDAO.getUnitValues(this);
		} catch (Exception e) {

			new HisException("Nurse Check List Master",
					"VONurseCheckListMst.getStrUnitValues -->", e.getMessage());
		} finally {
			
		}

		return strUnitValues;
	}
	public String getStrActive() {
		return strActive;
	}
	public void setStrActive(String strActive) {
		this.strActive = strActive;
	}
	public int getNTotalRows() {
		return nTotalRows;
	}
	public void setNTotalRows(int totalRows) {
		nTotalRows = totalRows;
	}
	public String getStrMultiRow() {
		
		NurseCheckListMstHLP hlp = new NurseCheckListMstHLP();
		strMultiRow = hlp.getMultiRow(this, 1);
		
		return strMultiRow;
	}
	public String getStrCheckListFor() {
		return strCheckListFor;
	}
	public void setStrCheckListFor(String strCheckListFor) {
		this.strCheckListFor = strCheckListFor;
	}
	public String getStrLastModSeatId() {
		return strLastModSeatId;
	}
	public void setStrLastModSeatId(String strLastModSeatId) {
		this.strLastModSeatId = strLastModSeatId;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public String getStrTotalRows() {
		strTotalRows = String.valueOf(this.getNTotalRows());
		return strTotalRows;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrCheckListDataType() {
		return strCheckListDataType;
	}

	public void setStrCheckListDataType(String strCheckListDataType) {
		this.strCheckListDataType = strCheckListDataType;
	}

	public String getStrCheckListDefaultVal() {
		return strCheckListDefaultVal;
	}

	public void setStrCheckListDefaultVal(String strCheckListDefaultVal) {
		this.strCheckListDefaultVal = strCheckListDefaultVal;
	}
	
}
