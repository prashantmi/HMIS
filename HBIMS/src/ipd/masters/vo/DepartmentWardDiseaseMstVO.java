package ipd.masters.vo;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;
import ipd.DAOIpd;

import java.sql.SQLException;

import javax.sql.rowset.WebRowSet;

public class DepartmentWardDiseaseMstVO extends GenericFormBean {

	private static final long serialVersionUID = 02L;

	private String strDeptWardValue = "0";
	private String strDeptWardName = null;
	private String strDepartment = "0";
	private String strUnit = "0";
	private String[] strDisease = null;
	private String[] strEffectiveFromDate = null;
	private String strRemarks = "";
	private String strActive = "";

	private String strDeptmentValues = null;
	private String strUnitValues = null;
	private String strDiseaseValues = null;

	private String strDiseaseTempName = null;
	private String strEffTempDate = null;

	private String strSeatId = "";
	private String strLastModSeatId = "";

	private String strErr = "";
	private String strMsg = "";
	private String strWarning = "";

	private int nTotalRow = 0;

	private String strCtDate = null;

	/**
	 * retrieves Current Date From Application Server and returns the Same.
	 * 
	 * @return - Current Date in String Format
	 */
	public String getStrCtDate() {

		HisUtil util = new HisUtil("Department Ward Disease Master",
				"VODepartmentWardDiseaseMst");

		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {

			new HisException("Department Ward Disease Master",
					"VODepartmentWardDiseaseMst.getStrCtDate()", e.getMessage());
		}

		return strCtDate;
	}

	public String getStrDiseaseTempName() {
		return strDiseaseTempName;
	}

	public void setStrDiseaseTempName(String strDiseaseTempName) {
		this.strDiseaseTempName = strDiseaseTempName;
	}

	public int getNTotalRow() {
		return nTotalRow;
	}

	public void setNTotalRow(int totalRow) {

		nTotalRow = totalRow;
	}

	public String[] getStrDisease() {
		return strDisease;
	}

	public void setStrDisease(String[] strDisease) {
		this.strDisease = strDisease;
	}

	public String getStrDeptWardValue() {
		return strDeptWardValue;
	}

	public void setStrDeptWardValue(String strDeptWardValue) {
		this.strDeptWardValue = strDeptWardValue;
	}

	public String getStrDepartment() {
		return strDepartment;
	}

	public void setStrDepartment(String strDepartment) {
		this.strDepartment = strDepartment;
	}

	public String getStrUnit() {
		return strUnit;
	}

	public void setStrUnit(String strUnit) {
		this.strUnit = strUnit;
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

	public String[] getStrEffectiveFromDate() {
		return strEffectiveFromDate;
	}

	public void setStrEffectiveFromDate(String[] strEffectiveFromDate) {
		this.strEffectiveFromDate = strEffectiveFromDate;
	}

	/**
	 * retrieves and returns the Application Server Current Date if the Existing
	 * Date is Null
	 * 
	 * @return - Current Date in String Format.
	 */
	public String getStrEffTempDate() {

		if (this.strEffTempDate == null) {

			HisUtil util = new HisUtil("Department Ward Disease Master",
					"VODepartmentWardDiseaseMst");

			try {
				strEffTempDate = util.getASDate("dd-MMM-yyyy");

			} catch (Exception e) {

				new HisException("Department Ward  Disease Master",
						"VODepartmentWardDiseaseMst.getStrEffTempDate()", e
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

	public String getStrRemarks() {
		return strRemarks;
	}

	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public String getStrActive() {
		return strActive;
	}

	public void setStrActive(String strActive) {
		this.strActive = strActive;
	}

	/**
	 * returns required Department Values by executing the respective Queries.
	 * 
	 * @return - Dapartment Values in String
	 * 
	 */
	public String getStrDeptmentValues() {

		DAOIpd dao = new DAOIpd("Department Ward Disease Master",
				"VODepartmentWardDiseaseMst");
		HisUtil util = new HisUtil("Department Ward Disease Master",
				"VODepartmentWardDiseaseMst");
		WebRowSet ws = null;
		try {
			ws = dao.getDepartmentDtl("", true,"");
			strDeptmentValues = util.getOptionValue(ws, "0", "0^Select Value",
					false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			new HisException("Department Ward Disease Master",
					"VODepartmentWardDiseaseMst.getStrDeptmentValues -->", e
							.getMessage());
		} finally {
			dao = null;
			util = null;
			if (ws != null) {
				try {
					ws.close();
				} catch (SQLException e) {
					new HisException("Department Ward Disease Master",
							"VODepartmentWardDiseaseMst.ws.close -->", e
									.getMessage());
				}
				ws = null;
			}
		}

		return strDeptmentValues;
	}

	/**
	 * returns required Unit Values by executing the respective Queries.
	 * 
	 * @return - Unit Values in String
	 */
	public String getStrUnitValues() {

		DAOIpd dao = new DAOIpd("Department Ward Disease Master",
				"VODepartmentWardDiseaseMst");
		HisUtil util = new HisUtil("Department Ward Diosease Master",
				"VODepartmentWardDiseaseMst");
		WebRowSet ws = null;
		try {
			// System.out.println(" inside getStrUnitValues");
			String[] strTemp = new String[2];
			strTemp[0] = this.getStrDepartment().replace('^', '#').split("#")[0];
			strTemp[1] = this.getStrDeptWardValue().replace('^', '#')
					.split("#")[0];

			ws = dao.getIpdWardDeptDiseaseUnitDtl(strTemp, true, true);
			strUnitValues = util.getOptionValue(ws, this.getStrUnit(),
					"0^Select Value", false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			new HisException("Department Ward Disease Master",
					"VODepartmentWardDiseaseMst.getStrUnitValues -->", e
							.getMessage());
		} finally {
			dao = null;
			util = null;
			if (ws != null) {
				try {
					ws.close();
				} catch (SQLException e) {
					new HisException("Department Ward Disease Master",
							"VODepartmentWardDiseaseMst.ws.close -->", e
									.getMessage());
				}
				ws = null;
			}
		}

		return strUnitValues;
	}

	/**
	 * returns required Disease Values by executing the respective Queries.
	 * 
	 * @return - Disease Values in String
	 */
	public String getStrDiseaseValues() {

		DAOIpd dao = new DAOIpd("Department Ward Disease Master",
				"VODepartmentWardDiseaseMst");
		HisUtil util = new HisUtil("Department Ward Disease Master",
				"VODepartmentWardDiseaseMst");
		WebRowSet ws = null;
		try {
			ws = dao.getIpdHospitalDiseaseDtl(true, "", true);
			strDiseaseValues = util.getOptionValue(ws, "0", "0^Select Value",
					false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			new HisException("Department Ward Disease Master",
					"VODepartmentWardDiseaseMst.getStrDeptmentValues -->", e
							.getMessage());
		} finally {
			dao = null;
			util = null;
			if (ws != null) {
				try {
					ws.close();
				} catch (SQLException e) {
					new HisException("Department Ward Disease Master",
							"VODepartmentWardDiseaseMst.ws.close -->", e
									.getMessage());
				}
				ws = null;
			}
		}

		return strDiseaseValues;
	}

	public String getStrDeptWardName() {
		return strDeptWardName;
	}

	public void setStrDeptWardName(String strDeptWardName) {
		this.strDeptWardName = strDeptWardName;
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

}
