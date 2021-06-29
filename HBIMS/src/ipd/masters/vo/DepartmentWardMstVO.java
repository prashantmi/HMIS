package ipd.masters.vo;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;
import ipd.DAOIpd;

import java.sql.SQLException;

import javax.sql.rowset.WebRowSet;

public class DepartmentWardMstVO extends GenericFormBean {

	private static final long serialVersionUID = 02L;

	private String strDeptWardValue = "0";
	private String strDeptWardName = null;
	private String[] strDepartment = null;
	private String[] strUnit = null;
	private String[] strEffectiveFromDate = null;
	private String strRemarks = "";
	private String strActive = "";
	private int nTotalRow = 0;

	private String strDeptTempName = "0";
	private String strUnitTempName = "0";
	private String strEffTempDate = null;

	private String strWardValues = null;
	private String strDeptmentValues = null;
	private String strUnitValues = null;

	private String strSeatId = "";
	private String strLastModSeatId = "";

	private String strErr = "";
	private String strMsg = "";
	private String strWarning = "";

	private String strCtDate = null;

	/**
	 * retrieves Current Date From Application Server and returns the Same.
	 * 
	 * @return - Current Date in String Format
	 */
	public String getStrCtDate() {

		HisUtil util = new HisUtil("Department Master", "VODepartmentWardMst");

		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");

		} catch (Exception e) {

			new HisException("Department Ward Master",
					"VODepartmentWardMst.getStrCtDate()", e.getMessage());
		} finally {
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

	/**
	 * used to return List of Values for Ward Combo
	 * 
	 * @return Ward Options in String format.
	 */
	public String getStrWardValues() {

		DAOIpd dao = new DAOIpd("Department Ward Master", "VODepartmentWardMst");
		HisUtil util = new HisUtil("Department Ward Master",
				"VODepartmentWardMst");
		WebRowSet ws = null;
		try {
			ws = dao.getWardDtl(true);
			strWardValues = util.getOptionValue(ws, this.getStrDeptWardValue(),
					"0^Select Value", false);
		} catch (Exception e) {
			new HisException("Department Ward Master",
					"VODepartmentWardMst.getStrWardValues -->", e.getMessage());
		} finally {
			dao = null;
			util = null;
			if (ws != null) {
				try {
					ws.close();
				} catch (SQLException e) {
					new HisException("Department Ward Master",
							"VODepartmentWardMst.ws.close -->", e.getMessage());
				}
				ws = null;
			}
		}

		return strWardValues;
	}

	/**
	 * used to return List of Values for Department Combo
	 * 
	 * @return Department Options in String format.
	 */
	public String getStrDeptmentValues() {

		DAOIpd dao = new DAOIpd("Department Ward Master", "VODepartmentWardMst");
		HisUtil util = new HisUtil("Department Ward Master",
				"VODepartmentWardMst");
		WebRowSet ws = null;
		try {
			ws = dao.getDepartmentDtl("", true,"");
			strDeptmentValues = util.getOptionValue(ws, "0", "0^Select Value",
					false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			new HisException("Department Ward Master",
					"VODepartmentWardMst.getStrDeptmentValues -->", e
							.getMessage());
		} finally {
			dao = null;
			util = null;
			if (ws != null) {
				try {
					ws.close();
				} catch (SQLException e) {
					new HisException("Department Ward Master",
							"VODepartmentWardMst.ws.close -->", e.getMessage());
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

		DAOIpd dao = new DAOIpd("Department Ward Master", "VODepartmentWardMst");
		HisUtil util = new HisUtil("Department Ward Master",
				"VODepartmentWardMst");
		WebRowSet ws = null;
		try {

			String[] strTemp = new String[2];
			strTemp[0] = this.getStrDeptTempName();
			strTemp[1] = this.getStrDeptWardValue();

			ws = dao.getIpdUnitDtl(strTemp, true, true);
			strUnitValues = util.getOptionValue(ws, this.getStrUnitTempName(),
					"0^Select Value", false);
		} catch (Exception e) {

			new HisException("Department Ward Master",
					"VODepartmentWardMst.getStrUnitValues -->", e.getMessage());
		} finally {
			dao = null;
			util = null;
			if (ws != null) {
				try {
					ws.close();
				} catch (SQLException e) {
					new HisException("Department Ward Master",
							"VODepartmentWardMst.ws.close -->", e.getMessage());
				}
				ws = null;
			}
		}

		return strUnitValues;
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

	public int getNTotalRow() {
		return nTotalRow;
	}

	public void setNTotalRow(int totalRow) {
		nTotalRow = totalRow;
	}

	public String getStrDeptWardValue() {
		return strDeptWardValue;
	}

	public void setStrDeptWardValue(String strDeptWardValue) {
		this.strDeptWardValue = strDeptWardValue;
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

	public String getStrRemarks() {
		return strRemarks;
	}

	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public String[] getStrEffectiveFromDate() {
		return strEffectiveFromDate;
	}

	public void setStrEffectiveFromDate(String[] strEffectiveFromDate) {
		this.strEffectiveFromDate = strEffectiveFromDate;
	}

	/**
	 * retrieves Current Date From Application Server and returns the Same if
	 * the Existing Date is Null.
	 * 
	 * @return - Current Date or Existing Date in String Format
	 */
	public String getStrEffTempDate() {

		if (this.strEffTempDate == null) {

			HisUtil util = new HisUtil("Department Ward Master",
					"VODepartmentWardMst");

			try {
				strEffTempDate = util.getASDate("dd-MMM-yyyy");

			} catch (Exception e) {

				new HisException("Department Ward Master",
						"VODepartmentWardMst.getStrEffTempDate()", e
								.getMessage());
			} finally {

				util = null;

			}
		}

		return strEffTempDate;
	}

	public void setStrEffTempDate(String strEffTempDate) {
		this.strEffTempDate = strEffTempDate;
	}

	public String getStrActive() {
		return strActive;
	}

	public void setStrActive(String strActive) {
		this.strActive = strActive;
	}

	public String getStrDeptWardName() {
		return strDeptWardName;
	}

	public void setStrDeptWardName(String strDeptWardName) {
		this.strDeptWardName = strDeptWardName;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public String getStrLastModSeatId() {
		return strLastModSeatId;
	}

	public void setStrLastModSeatId(String strLastModSeatId) {
		this.strLastModSeatId = strLastModSeatId;
	}

}
