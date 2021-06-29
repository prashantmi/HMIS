package ipd.transactions;

import java.util.ArrayList;
import java.util.List;

import ipd.IpdConfigUtil;
import hisglobal.transactionutil.TransInterface;

import javax.servlet.http.HttpSession;

public class MsApprovalTransUTL extends TransInterface {

	HttpSession session = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 02L;

	@Override
	public String getButtonConfiguration() {
		// TODO Auto-generated method stub
		return "left";
	}

	@Override
	public String[] getColumnHeader() {
		String[] strColumnHeader = { "CR No.", "Name", "Age/Sex", "Dept/Unit" };
		return strColumnHeader;
	}

	@Override
	public String[] getComboHeader() {
		String[] strComboHeader = { "1", "Record Status" };

		return strComboHeader;
	}

	@Override
	public String[] getComboQuery() {
		//String[] strComboQry = { "0^Non-Approved#1^Approved#5^Rejected#2^Allotted#6^Allotted But Bed Expired#4^Cancel#7^Advice Cancel" };
		String[] strComboQry = { "0^Non-Approved#1^Approved#5^Rejected#2^Allotted#6^Allotted But Bed Expired#4^Cancel" };
		return strComboQry;
	}

	@Override
	public String[] getDbButtons() {
		String[] str = { "2" };
		return str;
		//return null;
	}

	@Override
	public String getEventState() {
	//	String strEvent = "IpdMSRecordChkStatus";
		return null;
		// return "Test";
	}

	@Override
	public String getJsFiles() {

		return "../../ipd/js/msapproval_trans.js";
	}

	@Override
	public String getMainQuery(String[] cmb) {
		String strhcode = session.getAttribute("HOSPITAL_CODE").toString();

		IpdConfigUtil ipdUtil = new IpdConfigUtil(strhcode);

		StringBuffer sb = null;

		if (cmb == null || cmb[0].equals("0")) { // Non-Approved
			sb = new StringBuffer("");
			sb
					.append("SELECT (HRGNUM_PUK||'@'||HIPNUM_ADM_ADVNO||'@'||GNUM_HOSPITAL_CODE||'^'||HRGNUM_PUK||'^'|| ");
			sb
					.append("Ahis_Function.fun_pat_name(HRGNUM_PUK) || '^' || Ahis_Function.fun_pat_age_gen(HRGNUM_PUK) || '^' || ");
			sb
			.append("IPD_MST.getDeptUnitName(GNUM_DEPTUNIT_CODE,GNUM_HOSPITAL_CODE)) AS DATA  ");
			sb.append("FROM HIPT_MSAPPROVAL ");
			sb.append("WHERE GNUM_HOSPITAL_CODE = ");
			sb.append(strhcode);
			sb.append(" AND GNUM_ISVALID = 1 ");
			sb
					.append("AND (select Ipd_Mst.get_MSApproval_Status(HRGNUM_PUK,HIPNUM_ADM_ADVNO, ");
			sb.append(ipdUtil.getStrAdmissionAdviceValidityFrom());
			sb.append(",");
			sb.append(ipdUtil.getStrAdmissionAdviceValidityTo());
			sb.append(",GNUM_HOSPITAL_CODE) from dual) = 1 ");
			sb.append("AND HIPNUM_APPROVAL_STATUS = 0 ");

		} else if (cmb == null || cmb[0].equals("1")) { // Approved
			sb = new StringBuffer("");
			sb
					.append("SELECT (HRGNUM_PUK||'@'||HIPNUM_ADM_ADVNO||'@'||GNUM_HOSPITAL_CODE||'^'||HRGNUM_PUK||'^'|| ");
			sb
					.append("Ahis_Function.fun_pat_name(HRGNUM_PUK) || '^' || Ahis_Function.fun_pat_age_gen(HRGNUM_PUK) || '^' || ");
			sb
			.append("IPD_MST.getDeptUnitName(GNUM_DEPTUNIT_CODE,GNUM_HOSPITAL_CODE)) AS DATA  ");
			sb.append("FROM HIPT_MSAPPROVAL ");
			sb.append("WHERE GNUM_HOSPITAL_CODE = ");
			sb.append(strhcode);
			sb.append(" AND GNUM_ISVALID = 1 ");
			sb
					.append("AND (select Ipd_Mst.get_MSApproval_Status(HRGNUM_PUK,HIPNUM_ADM_ADVNO, ");
			sb.append(ipdUtil.getStrAdmissionAdviceValidityFrom());
			sb.append(",");
			sb.append(ipdUtil.getStrAdmissionAdviceValidityTo());
			sb.append(",GNUM_HOSPITAL_CODE) from dual) = 1 ");
			sb.append("AND HIPNUM_APPROVAL_STATUS = 1 ");

		} else if (cmb[0].equals("2")) { // Allotted
			sb = new StringBuffer("");
			sb
					.append("SELECT (HRGNUM_PUK||'@'||HIPNUM_ADM_ADVNO||'@'||GNUM_HOSPITAL_CODE||'^'||HRGNUM_PUK||'^'|| ");
			sb
					.append("Ahis_Function.fun_pat_name(HRGNUM_PUK) || '^' || Ahis_Function.fun_pat_age_gen(HRGNUM_PUK) || '^' || ");
			sb
			.append("IPD_MST.getDeptUnitName(GNUM_DEPTUNIT_CODE,GNUM_HOSPITAL_CODE)) AS DATA  ");
			sb.append("FROM HIPT_MSAPPROVAL ");
			sb.append("WHERE GNUM_HOSPITAL_CODE = ");
			sb.append(strhcode);
			sb.append(" AND GNUM_ISVALID = 1 ");
			sb
					.append("AND (select Ipd_Mst.getWardRoomBedStatus(GNUM_HOSPITAL_CODE,HIPNUM_WARD_CODE,HIPNUM_ROOM_NO,HIPNUM_BED_CODE,13) from dual) = 13 ");
			sb
					.append("AND (select Ipd_Mst.get_MSApproval_Status(HRGNUM_PUK,HIPNUM_ADM_ADVNO, ");
			sb.append(ipdUtil.getStrAdmissionAdviceValidityFrom());
			sb.append(",");
			sb.append(ipdUtil.getStrAdmissionAdviceValidityTo());
			sb.append(",GNUM_HOSPITAL_CODE) from dual) = 1 ");
			sb.append("AND HIPNUM_APPROVAL_STATUS = 2 ");

		} else if (cmb[0].equals("4")) { // Cancel
			sb = new StringBuffer("");

			sb = new StringBuffer("");
			sb
					.append("SELECT (HRGNUM_PUK||'@'||HIPNUM_ADM_ADVNO||'@'||GNUM_HOSPITAL_CODE||'^'||HRGNUM_PUK||'^'|| ");
			sb
					.append("Ahis_Function.fun_pat_name(HRGNUM_PUK) || '^' || Ahis_Function.fun_pat_age_gen(HRGNUM_PUK) || '^' || ");
			sb
			.append("IPD_MST.getDeptUnitName(GNUM_DEPTUNIT_CODE,GNUM_HOSPITAL_CODE)) AS DATA  ");
			sb.append("FROM HIPT_MSAPPROVAL ");
			sb.append("WHERE GNUM_HOSPITAL_CODE = ");
			sb.append(strhcode);
			sb.append(" AND GNUM_ISVALID = 1 ");
			sb
					.append("AND (select Ipd_Mst.get_MSApproval_Status(HRGNUM_PUK,HIPNUM_ADM_ADVNO, ");
			sb.append(ipdUtil.getStrAdmissionAdviceValidityFrom());
			sb.append(",");
			sb.append(ipdUtil.getStrAdmissionAdviceValidityTo());
			sb.append(",GNUM_HOSPITAL_CODE) from dual) = 1 ");
			sb.append("AND HIPNUM_APPROVAL_STATUS = 4 ");

		} else if (cmb[0].equals("5")) { // Rejected
			sb = new StringBuffer("");

			sb = new StringBuffer("");
			sb
					.append("SELECT (HRGNUM_PUK||'@'||HIPNUM_ADM_ADVNO||'@'||GNUM_HOSPITAL_CODE||'^'||HRGNUM_PUK||'^'|| ");
			sb
					.append("Ahis_Function.fun_pat_name(HRGNUM_PUK) || '^' || Ahis_Function.fun_pat_age_gen(HRGNUM_PUK) || '^' || ");
			sb
			.append("IPD_MST.getDeptUnitName(GNUM_DEPTUNIT_CODE,GNUM_HOSPITAL_CODE)) AS DATA  ");
			sb.append("FROM HIPT_MSAPPROVAL ");
			sb.append("WHERE GNUM_HOSPITAL_CODE = ");
			sb.append(strhcode);
			sb.append(" AND GNUM_ISVALID = 1 ");
			sb
					.append("AND (select Ipd_Mst.get_MSApproval_Status(HRGNUM_PUK,HIPNUM_ADM_ADVNO, ");
			sb.append(ipdUtil.getStrAdmissionAdviceValidityFrom());
			sb.append(",");
			sb.append(ipdUtil.getStrAdmissionAdviceValidityTo());
			sb.append(",GNUM_HOSPITAL_CODE) from dual) = 1 ");
			sb.append("AND HIPNUM_APPROVAL_STATUS = 5 ");

		} else if (cmb[0].equals("6")) { // Alloted But Bed Expired

			sb = new StringBuffer("");
			sb
					.append("SELECT (HRGNUM_PUK||'@'||HIPNUM_ADM_ADVNO||'@'||GNUM_HOSPITAL_CODE||'^'||HRGNUM_PUK||'^'|| ");
			sb
					.append("Ahis_Function.fun_pat_name(HRGNUM_PUK) || '^' || Ahis_Function.fun_pat_age_gen(HRGNUM_PUK) || '^' || ");
			sb
			.append("IPD_MST.getDeptUnitName(GNUM_DEPTUNIT_CODE,GNUM_HOSPITAL_CODE)) AS DATA  ");
			sb.append("FROM HIPT_MSAPPROVAL ");
			sb.append("WHERE GNUM_HOSPITAL_CODE = ");
			sb.append(strhcode);
			sb.append(" AND GNUM_ISVALID = 1 ");
			sb
					.append("AND (select Ipd_Mst.getWardRoomBedStatus(GNUM_HOSPITAL_CODE,HIPNUM_WARD_CODE,HIPNUM_ROOM_NO,HIPNUM_BED_CODE,13) from dual) = 10 ");
			sb
					.append("AND (select Ipd_Mst.get_MSApproval_Status(HRGNUM_PUK,HIPNUM_ADM_ADVNO, ");
			sb.append(ipdUtil.getStrAdmissionAdviceValidityFrom());
			sb.append(",");
			sb.append(ipdUtil.getStrAdmissionAdviceValidityTo());
			sb.append(",GNUM_HOSPITAL_CODE) from dual) = 1 ");
			sb.append("AND HIPNUM_APPROVAL_STATUS = 2 ");

		} else if (cmb[0].equals("7")) { // Advice Canceled
			sb = new StringBuffer("");

			sb = new StringBuffer("");
			sb
					.append("SELECT (HRGNUM_PUK||'@'||HIPNUM_ADM_ADVNO||'@'||GNUM_HOSPITAL_CODE||'^'||HRGNUM_PUK||'^'|| ");
			sb
					.append("Ahis_Function.fun_pat_name(HRGNUM_PUK) || '^' || Ahis_Function.fun_pat_age_gen(HRGNUM_PUK) || '^' || ");
			sb
			.append("IPD_MST.getDeptUnitName(GNUM_DEPTUNIT_CODE,GNUM_HOSPITAL_CODE)) AS DATA  ");
			sb.append("FROM HIPT_MSAPPROVAL ");
			sb.append("WHERE GNUM_HOSPITAL_CODE = ");
			sb.append(strhcode);
			sb.append(" AND GNUM_ISVALID = 1 ");
			sb.append("AND HIPNUM_APPROVAL_STATUS = 7 ");
			sb
					.append("AND HIPDT_REQUEST_DT BETWEEN TRUNC(SYSDATE - 30) AND TRUNC(SYSDATE) ");
		}
System.out.println("mainquery"+sb.toString());
		return sb.toString();
	}

	@Override
	public String getMasterName() {
		// TODO Auto-generated method stub
		return "MS Approval";
	}

	@Override
	public String getMenuOption() {
		// TODO Auto-generated method stub
		return "tiles";
	}

	@Override
	public int getMinPanelButton() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String[] getSearchField() {
		String[] strSearch = { "1", "HRGNUM_PUK" };
		return strSearch;
	}

	@Override
	public String[] getUserDefinedButtons() 
	{
		String[] strButtons = new String[5];
		strButtons[0] = "Add@add('ADD')@0";
		strButtons[1] = "Approve@add('APPROVE')@1";
		strButtons[2] = "Ward Allote...@add('ALLOTEMENT')@1";
		strButtons[3] = "List@add('APPROVLIST')@0";
		strButtons[4] = "Cancel@add('MODECNCEL')@1";
		//strButtons[5] = "View@add('VIEW')@1";
		return strButtons;
	}

	@Override
	public void setHttpSession(HttpSession session) {

		this.session = session;
	}

	@Override
	public String getComboEventState() {
		// TODO Auto-generated method stub
	//	return "IpdMSRecordComboStatus";
		 return null;
	}

	@Override
	public void setCombo(String[] cmb) {
		cmb = null;
	}

	public String[] getOrderBy() {
		String orderBy[] = { "1", "HRGNUM_PUK" };
		return orderBy;
	}

	@Override
	public List<String> getViewHeader() {
		// TODO Auto-generated method stub
		List<String> ls= new ArrayList<String>(); 
		return ls;
	}

	@Override
	public String getViewQuery() {
		// TODO Auto-generated method stub
		return "";
	}
	
	public String[] getButtonIcons() 
	{
		String[] str={"AddOnDesk.png","ApprovalOnDesk.png","WardBedAllotOnDesk.png","ListOnDesk.png","CancelOnDesk.png"};
		return str;
	}
}
