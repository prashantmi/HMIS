/**
 * 
 */

package ipd.transactions;

import hisglobal.transactionutil.TransInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author Anurudra Goel
 * 
 */
public class PatientAcceptanceDeskFinalTransUTL extends TransInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 02L;
	HttpSession httpSession = null;
	String[] cmbVal = null;

	public void setHttpSession(HttpSession session) {
		this.httpSession = session;
	}

	public void setCombo(String[] cmb) {
		
		this.cmbVal = cmb;
	}

	public String getMasterName() {
		String masterName = "Patient Acceptance Desk";
		return masterName;
	}

	public int getRecord_per_page() {
		return 12;
	}

	public List<String> getViewHeader() {

		List<String> viewHdr = new ArrayList<String>();
		return viewHdr;
	}

	public String getMainQuery(String cmb[]) {
		String strHospCode = (String)httpSession.getAttribute("HOSPITAL_CODE");
		StringBuffer brMainQuery = new StringBuffer(1500);
		if (cmb != null) {
			// --NON-ACEPTED
			brMainQuery.append("SELECT DATA FROM (");
			brMainQuery
					.append("SELECT HRGNUM_PUK || '@' || HIPNUM_ADMNO || '@0' || '^' || HIPNUM_ADMNO || '^' || HRGNUM_PUK || '^' || ");
			brMainQuery
					.append(" (SELECT INITCAP(HRGSTR_FNAME || ' ' || HRGSTR_MNAME || ' ' || HRGSTR_LNAME) || '^' || ");
			brMainQuery
					.append(" (SELECT INITCAP(GSTR_GENDER_NAME) FROM GBLT_GENDER_MST");
			brMainQuery
					.append(" WHERE GNUM_HOSPITAL_CODE = B.GNUM_HOSPITAL_CODE");
			brMainQuery
					.append(" AND GNUM_GENDER_CODE = A.GNUM_GENDER_CODE)");
			brMainQuery
					.append(" || '/' || Ahis_Gbl_Util.dob_age_on (HRGDT_DOB, SYSDATE) AS AGE_SEX ");
			brMainQuery.append(" FROM HRGT_PATIENT_DTL A");
			brMainQuery
					.append(" WHERE HRGNUM_PUK = B.HRGNUM_PUK)  || '^' || Ipd_Mst.getBedName(b.HIPNUM_BED_CODE,b.GNUM_HOSPITAL_CODE)");
			brMainQuery
					.append(" || '^' || (SELECT TO_CHAR(HIPDT_ADMDATETIME,'DD/MM/YYYY hh24:mi') ");
			brMainQuery.append("");
			brMainQuery.append(" FROM HIPT_PATADMISSION_DTL ");
			brMainQuery.append(" WHERE HIPNUM_ADMNO=B.HIPNUM_ADMNO AND GNUM_HOSPITAL_CODE= B.GNUM_HOSPITAL_CODE) as DATA,");
			brMainQuery
			.append(" Ahis_Function.fun_pat_name(B.HRGNUM_PUK) AS pat_name,HIPNUM_ADMNO AS adm_no,GNUM_ISVALID AS IS_VALID");
	
			brMainQuery.append(" FROM HIPT_PAT_MOV_DTL B ");
			brMainQuery.append(" WHERE GNUM_HOSPITAL_CODE  = ");
			brMainQuery.append((String)httpSession.getAttribute("HOSPITAL_CODE"));
			brMainQuery.append(" AND (select to_char(Ahis_Gbl_Util.Num_Default(HIPNUM_TRANSOUT_FLG,'-1')) from dual) = '-1'");
			brMainQuery.append(" AND HIPDT_TRANSIN_DATETIME IS NULL AND HIPNUM_TRANSIN_FLG IN (0,2,5,8) "); 
			brMainQuery.append(" AND GNUM_DEPT_CODE = ");
			brMainQuery.append(cmbVal[0]);
			brMainQuery.append(" AND GNUM_DEPTUNIT_CODE = ");
			brMainQuery.append(cmbVal[1]);
			brMainQuery.append(" AND HIPNUM_WARDCODE = ");
			brMainQuery.append(cmbVal[2]);
		
			brMainQuery.append(") where IS_VALID =1 ");
		
		} else {
			brMainQuery.append("SELECT DATA FROM (");
			brMainQuery
					.append("SELECT HRGNUM_PUK || '@' || HIPNUM_ADMNO || '@0' || '^' || HIPNUM_ADMNO || '^' || HRGNUM_PUK || '^' ||");
			brMainQuery
					.append(" (SELECT INITCAP(HRGSTR_FNAME || ' ' || HRGSTR_MNAME || ' ' || HRGSTR_LNAME) || '^' || ");
			brMainQuery
					.append(" (SELECT INITCAP(GSTR_GENDER_NAME) FROM GBLT_GENDER_MST");
			brMainQuery
					.append(" WHERE GNUM_HOSPITAL_CODE = B.GNUM_HOSPITAL_CODE");
			brMainQuery.append(" AND GNUM_GENDER_CODE = A.GNUM_GENDER_CODE)");
			brMainQuery
					.append(" || '/' || Ahis_Gbl_Util.dob_age_on (HRGDT_DOB, SYSDATE) AS AGE_SEX");
			brMainQuery.append(" FROM HRGT_PATIENT_DTL A");
			brMainQuery
					.append(" WHERE HRGNUM_PUK = B.HRGNUM_PUK) || '^' || Ipd_Mst.getBedName(b.HIPNUM_BED_CODE,b.GNUM_HOSPITAL_CODE) || '^' ||");
			brMainQuery
					.append(" TO_CHAR(HIPDT_ADMDATETIME,'dd/mm/yyyy hh24:mi') AS DATA,");
			brMainQuery
					.append("Ahis_Function.fun_pat_name(B.HRGNUM_PUK) AS pat_name,HIPNUM_ADMNO AS adm_no,GNUM_ISVALID AS IS_VALID");
			brMainQuery.append(" FROM HIPT_PATADMISSION_DTL B");
			brMainQuery.append(" WHERE GNUM_ISVALID = 1");
			brMainQuery.append(" AND GNUM_HOSPITAL_CODE = "+strHospCode);
			brMainQuery.append(" AND HIPNUM_ISACCEPTED = 0");
			brMainQuery.append(" AND HIPDT_DISDATETIME IS NULL");
			brMainQuery.append(" AND GNUM_DEPT_CODE = 0");
			brMainQuery.append(" AND GNUM_DEPTUNIT_CODE = 0");
			brMainQuery.append(" AND HIPNUM_WARD_CODE = 0");
			brMainQuery.append(" AND HIPNUM_ROOM_CODE = 0");
			brMainQuery.append(") where IS_VALID =1 ");
		}
		return brMainQuery.toString();
	}

	public String[] getSearchField() {
		String search_field[] = { "1", "adm_no", "3", "pat_name" };
		return search_field;
	}

	public String[] getComboHeader() {
		String[] strComboHeader = { "0", "Department", "0", "&nbsp; Unit", "0",
				"Ward", "0", "Room"};

		return strComboHeader;
	}

	public String[] getColumnHeader() {
		String[] strColumnHeader = { "Admission No.", "CR No.", "Patient Name",
				"Sex/Age", "Bed Name", "Admission Date" };
		return strColumnHeader;
	}

	public String[] getComboQuery() {
		String[] strComboQry = new String[4];
		String strHospCode = (String) httpSession.getAttribute("HOSPITAL_CODE");
		strComboQry[0] = " select    GNUM_DEPT_CODE,GSTR_DEPT_NAME    from     GBLT_DEPARTMENT_MST    where     GNUM_HOSPITAL_CODE = '"
			+ strHospCode
			+ "' AND GNUM_DEPT_TYPE = 1 AND   GNUM_ISVALID =1  AND "
			+ " TRUNC(SYSDATE) BETWEEN GDT_EFFECTIVE_FRM AND TRUNC(NVL(GDT_EFFECTIVE_TO,SYSDATE)) "
			+ " ORDER BY " + " GSTR_DEPT_NAME";

	strComboQry[1] = " SELECT      HGNUM_DEPTUNITCODE  ,   HGSTR_UNITNAME    FROM         HGBT_UNIT_MST  WHERE "
			+ " HGNUM_DEPT_CODE  = #1#  AND  GNUM_HOSPITAL_CODE  = '"
			+ strHospCode
			+ "'    AND    GNUM_ISVALID   =1   AND "
			+ " TRUNC(SYSDATE) BETWEEN GDT_EFFECTIVE_FRM AND TRUNC(NVL(GDT_EFFECTIVE_TO,SYSDATE)) "
			+ " ORDER  BY   HGSTR_UNITNAME ";

	strComboQry[2] = " SELECT  distinct HIPNUM_WARD_CODE, "
			+ " Ipd_Mst.getWardName(HIPNUM_WARD_CODE,GNUM_HOSPITAL_CODE ) "
			+ "   FROM HIPT_DUWRBED_MST "
			+ "	WHERE HGNUM_DEPTUNITCODE =#2# AND "
			+ "	GNUM_HOSPITAL_CODE ='" + strHospCode
			+ "' AND GNUM_ISVALID = 1 AND "
			+ "	TRUNC(SYSDATE) >= GDT_EFFECTIVE_FRM" 
			+ " AND LENGTH(Ipd_Mst.getWardName(HIPNUM_WARD_CODE,GNUM_HOSPITAL_CODE ))>1 ";

	strComboQry[3] = " SELECT b.hipnum_room_no, b.hipstr_room_desc "
			+ " FROM HIPT_ROOM_CONFIG_MST b "
			+ " WHERE gnum_hospital_code = '" + strHospCode
			+ "' AND gnum_isvalid = 1 AND " + " EXISTS (SELECT    'x' "
			+ " FROM HIPT_WARD_ROOM_BED_MST "
			+ " WHERE hipnum_room_no=b.hipnum_room_no AND "
			+ " hgnum_ward_code= #3# AND " + " gnum_isvalid=1) ";
		
		
		return strComboQry;
	}

	public String getViewQuery() {
		return "";
	}

	public String getButtons() {
		String strButtons = "<a style=cursor:hand><img src='../../hisglobal/images/btn-ccl.png' style='cursor:pointer' border=0 tabindex='1' onKeyPress='cancelPage();' onClick='cancelPage();' ></a>";
		return strButtons;
	}

	public String[] getDeleteQuery() {
		String deleteQuery[] = new String[1];
		deleteQuery[0] = "";

		return deleteQuery;
	}

	public List<String> getDeleteData(String chk) {
		List<String> deleteData = new ArrayList<String>();
		return deleteData;
	}

	public String getJsFiles() {
		String files = "../../ipd/js/patientAcceptanceDesk.js";
		return files;

	}

	public String[] getRowStatus() {
		String[] status = { "Autrin Cap", "1", "brown", "Acceptance Desk" };
		return status;
	}

	public String getEventState() {
		String str = "";
		return str;
	}

	public String getButtonConfiguration() {
		return "left";
	}

	public String[] getUserDefinedButtons() {
		/*String[] str = {"Add@@0",
				"Delete@deleteRecords('DELETE')@1",
				"View@view('VIEWDATA'),enableButton('Add')@1",
				"Report@report('REPORT')@0"};*/
		return null;
	}

	public String[] getDbButtons() {
		String[] str = { "5" };
		return str;
	}

	public int getMinPanelButton() {
		return 1;
	}

	public String getMenuOption() {
		String menuType = "Tiles";
		return menuType;
	}

	public String getComboEventState() {
		return "showMenu";
	}

	public String[] getOrderBy() {
		String orderBy[] = { "1", "adm_no" };
		return orderBy;
	}

}
