package bmed.masters.controller.utl;

import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class ComplaintEscalationMstUTL implements MasterInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 02L;

	/*
	 * The http session.
	 */
	HttpSession httpSession = null;

	public String getButtons() {

		StringBuilder br = new StringBuilder();

		br
				.append("<img src='../../hisglobal/images/btn-add.png' style='cursor: pointer; ' tabindex='1' title='Add Record' onKeyPress='if(event.keyCode==13) add(\"ADD\");' onClick='add(\"ADD\");' />");
		br
				.append("<img src='../../hisglobal/images/btn-mo.png' style='cursor: pointer; ' tabindex='1' title='Update Record' onKeyPress='if(event.keyCode==13) edit(\"MODIFY\");' onClick='edit(\"MODIFY\");' />");
		br
				.append("<img src='../../hisglobal/images/btn-del.png' style='cursor: pointer; ' tabindex='1' title='Select One Or More CheckBox To Delete Record(s)' onKeyPress='if(event.keyCode==13) deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");' />");
		br
				.append("<img src='../../hisglobal/images/btn-view.png' style='cursor: pointer; ' tabindex='1' title='Select A Record To View' onKeyPress='if(event.keyCode==13) view(\"VIEWDATA\");' onClick='view1(\"VIEWDATA\");' />");//view(\"VIEWDATA\");
		br
				.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor: pointer; ' tabindex='1' title='Select A Record To Generate Reoprts' onKeyPress='if(event.keyCode==13) report(\"REPORT\");'  onClick='report(\"REPORT\");' />");
		return br.toString();
	}

	public String[] getColsWidth() {

		return null;
	}

	public String[] getColumnHeader() {
		String[] arrColumnHdr = { "Employee Name", "Level Type", "Period" };
		return arrColumnHdr;
	}

	public String[] getComboHeader() {
		String[] arrStrColumnHdr = { "0", "Engineering Item Type", "0",
				"Engineering Item Sub Type", "1", "Record Status" };
		return arrStrColumnHdr;
	}

	public String[] getComboQuery() {

		String[] arrStrComboQuery = new String[3];
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE")
				.toString();

		arrStrComboQuery[0] = bmed.qryHandler_bmed.getQuery(1,
				"complaintEscalationMst.enggItemtype.combo.0");
		arrStrComboQuery[0] = arrStrComboQuery[0].replace("?", Config.SUPER_USER_HOSPITAL_CODE);
		arrStrComboQuery[1] = bmed.qryHandler_bmed.getQuery(1,
				"complaintEscalationMst.enggItemSubtype.combo.1");
		arrStrComboQuery[1] = arrStrComboQuery[1].replace("?", hosCode);
		arrStrComboQuery[2] = "1^Active#2^Inactive";

		return arrStrComboQuery;
	}

	public String[] getDeleteQuery() {

		String[] arrStrDeleteQuery = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();
		arrStrDeleteQuery[0] = bmed.qryHandler_bmed.getQuery(1,
				"complaintEscalationMst.delete.0").replace("?", seatId);
		arrStrDeleteQuery[0] = arrStrDeleteQuery[0].concat(" WHERE "
				+ bmed.qryHandler_bmed.getQuery(1,
						"complaintEscalationMst.delete.cond.0"));

		return arrStrDeleteQuery;
	}

	public String getJsFiles() {

		String jsFile = new String("/HBIMS/bmed/js/complaintEscalation_mst.js");
		return jsFile;
	}

	public String getMainQuery(String[] cmb) {

		String strHosCode = httpSession.getAttribute("HOSPITAL_CODE")
				.toString();

		String strHospCode = Config.SUPER_USER_HOSPITAL_CODE;
		
		StringBuffer brMainQuery = new StringBuffer();
		brMainQuery.append(
				bmed.qryHandler_bmed.getQuery(1, "complaintEscalationMst.main").replace("?", strHospCode).replace("?", strHospCode)
						).append("WHERE GNUM_HOSPITAL_CODE = ");
		brMainQuery.append(strHosCode).append(
				" AND "
						+ bmed.qryHandler_bmed.getQuery(1,
								"complaintEscalationMst.main.cond.1").replace(
								"?", "1"));

		if (cmb != null) {

			if (!cmb[2].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[2]);

			}
		}

		brMainQuery
				.append(" AND "
						+ bmed.qryHandler_bmed.getQuery(1,
								"complaintEscalationMst.main.cond.2").replace(
								"?", "0"));

		if (cmb != null) {
			if (!cmb[0].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("0"), brMainQuery
						.lastIndexOf("0") + 1, cmb[0]);

			}
		}
		brMainQuery
				.append(" AND "
						+ bmed.qryHandler_bmed.getQuery(1,
								"complaintEscalationMst.main.cond.3").replace(
								"?", "0"));

		if (cmb != null) {
			if (!cmb[1].equals("0")) {

				brMainQuery.replace(brMainQuery.lastIndexOf("0"), brMainQuery
						.lastIndexOf("0") + 1, cmb[1]);

			}
		}
		System.out.println("brMainQuery.toString()--"+brMainQuery.toString());
		return brMainQuery.toString();
	}

	public String getMasterName() {
		String strMasterName = "Complaint Escalation Master";
		return strMasterName;
	}

	public String[] getOrderBy() {
		String arrStrOrderBy[] = { "1",
				"UPPER(BMED_FUNCTION.get_Emp_Name(A.GNUM_HOSPITAL_CODE,A.HEMSTR_EMP_ID))" };
		return arrStrOrderBy;
	}

	public int getPage_per_block() {

		return 10;
	}

	public int getRecord_per_page() {

		return 10;
	}

	public String[] getSearchField() {
		String strSearchField[] = { "1",
				"BMED_FUNCTION.get_Emp_Name(A.GNUM_HOSPITAL_CODE,A.HEMSTR_EMP_ID)" };
		return strSearchField;
	}

	public List<String> getViewHeader() {

		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Engineering Item Type");
		viewHdr.add("D");
		viewHdr.add("Engineering Item Sub Type");
		viewHdr.add("D");
		viewHdr.add("Emp Name");
		viewHdr.add("D");
		viewHdr.add("Period");
		viewHdr.add("D");
		viewHdr.add("Level Type");
		viewHdr.add("D");
		viewHdr.add("Remarks");
		viewHdr.add("D");
		viewHdr.add("Record Status");
		viewHdr.add("D");
		return viewHdr;
	}

	public String getViewQuery() {

		String strViewQuery = bmed.qryHandler_bmed.getQuery(1,
				"complaintEscalationMst.view");

		return strViewQuery;
	}

	public boolean reportInterFaceRequired() {

		return true;
	}

	public void setHttpSession(HttpSession session) {

		httpSession = session;
	}

	public String isGlobal() {
		// TODO Auto-generated method stub
		return null;
	}

}
