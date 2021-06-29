/**
 * 
 */

package ipd.transactions;

import hisglobal.transactionutil.TransInterface;
import ipd.IpdConfigUtil;
import ipd.setup.IPDConfig;

import java.util.ArrayList;
import java.util.List;



import javax.servlet.http.HttpSession;

/**
 * @author pankaj
 * 
 */
public class NursingDeskFinalTransUTL extends TransInterface {

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
		String masterName = "Nursing Desk";
		return masterName;
	}

	public int getRecord_per_page() {
		return 12;
	}

	public List<String> getViewHeader() {

		List<String> viewHdr = new ArrayList<String>();
		return viewHdr;
	}

	public String getMainQuery(String cmb[]) 
	{
		String strHospCode = (String)httpSession.getAttribute("HOSPITAL_CODE");
		String strSeatId = (String)httpSession.getAttribute("SEATID");
		StringBuffer brMainQuery = new StringBuffer(1500);
		IpdConfigUtil ipdConfigUtil=new IpdConfigUtil(strHospCode);
		String strSuperHospCode=ipdConfigUtil.SUPER_HOSPITAL_CODE;
		
		if (cmb != null) 
		{
			// --NON-ACCEPTED
			if (cmb[4].equals("2")) 
			{
				brMainQuery.append("SELECT DATA FROM (");
				brMainQuery.append("SELECT HRGNUM_PUK || '@' || HIPNUM_ADMNO || '@0@'|| HIPNUM_MOV_NO||'$'||ipd_mst.getismov(HIPNUM_ADMNO,"+strHospCode+") ||'*'|| b.gnum_deptunit_code|| '*'|| (NVL(b.hipnum_roomno,'0')) || '*'||((SELECT count(*) FROM hipt_patadmission_dtl WHERE hipnum_admno = b.hipnum_admno AND gnum_hospital_code = b.gnum_hospital_code and (EXTRACT(epoch FROM  ((SYSDATE) - (hipdt_admdatetime))))/3600 ::numeric <="+ipdConfigUtil.getStrNotReportedTimeLimit()+"))||'*'||(select hipnum_is_double_occupancy from hipt_Patadmission_dtl where hipnum_admno=B.HIPNUM_ADMNO and gnum_hospital_code= b.gnum_hospital_code and gnum_isvalid=1)||'*'||(select hipnum_wardtype_code  from hipt_ward_mst where hipnum_ward_code = '"+cmbVal[2]+"' and  gnum_hospital_code=b.gnum_hospital_code and gnum_isvalid=1) ||'^' || HIPNUM_ADMNO || '^' || HRGNUM_PUK || '^' || ");
				brMainQuery.append(" NVL((SELECT INITCAP(HRGSTR_FNAME || ' ' || HRGSTR_MNAME || ' ' || HRGSTR_LNAME) || '^' || ");
				brMainQuery.append(" (SELECT SUBSTR(INITCAP (GSTR_GENDER_NAME),0,1) FROM GBLT_GENDER_MST");
				//brMainQuery.append(" WHERE GNUM_HOSPITAL_CODE = "+strSuperHospCode+"");
				brMainQuery.append(" WHERE GNUM_ISVALID=1 AND GSTR_GENDER_CODE = A.GSTR_GENDER_CODE)");
				brMainQuery.append(" || '/' || ipd_mst.dob_age_on (HRGDT_DOB, SYSDATE) AS AGE_SEX ");
				
				brMainQuery.append(" FROM HRGT_PATIENT_DTL A");
				brMainQuery.append(" WHERE HRGNUM_PUK = B.HRGNUM_PUK),'-^-')");
				//added depunitcode
				//brMainQuery.append(" || '^' || (b.gnum_deptunit_code)");
				brMainQuery.append(" || '^' || (SELECT TO_CHAR(HIPDT_ADMDATETIME,'DD-MM-YY / HH24:MI') ");
				brMainQuery.append("");
				brMainQuery.append(" FROM HIPT_PATADMISSION_DTL ");
				brMainQuery.append(" WHERE HIPNUM_ADMNO=B.HIPNUM_ADMNO AND GNUM_HOSPITAL_CODE= B.GNUM_HOSPITAL_CODE) ||'^'||");
				brMainQuery.append(" NVL((SELECT IPD_MST.GETDEPTNAME(X.GNUM_DEPTUNIT_CODE,X.GNUM_HOSPITAL_CODE)|| '/'|| IPD_MST.GETWARDNAME (X.HIPNUM_WARD_CODE, ");
				brMainQuery.append(" X.GNUM_HOSPITAL_CODE,SYSDATE::character varying,NULL::character varying )||'/'||TO_CHAR(B.HIPDT_TRANS_DATETIME,'DD-MM-YY') ");
				brMainQuery.append(" FROM HIPT_PATADMISSION_DTL X WHERE X.HIPDT_ADMSTATUS_CODE IN (11, 17) AND B.GNUM_HOSPITAL_CODE = X.GNUM_HOSPITAL_CODE AND B.HIPNUM_ADMNO=X.HIPNUM_ADMNO ");
				brMainQuery.append(" AND B.HIPNUM_TRANSIN_FLG = 2 AND B.HIPNUM_MOV_NO > 0 AND (   B.HIPNUM_TRANSOUT_FLG IS NULL OR B.HIPNUM_TRANSOUT_FLG = null::character varying ) ),'-')||'^'||nvl(ahis_function.GETDEPTUNITNAME(b.GNUM_DEPTUNIT_CODE,b.GNUM_HOSPITAL_CODE),'0')  ||'^'||(SELECT HRGNUM_IS_DEAD FROM HRGT_PATIENT_DTL A WHERE A.HRGNUM_PUK=B.HRGNUM_PUK AND A.GNUM_HOSPITAL_CODE=B.GNUM_HOSPITAL_CODE)||'^'||IPD_MST.GETISMOV(HIPNUM_ADMNO,"+strHospCode+") as DATA,");
				//brMainQuery.append(" Ahis_Function.fun_pat_name(B.HRGNUM_PUK) AS pat_name,HIPNUM_ADMNO AS adm_no,GNUM_ISVALID AS IS_VALID,HRGNUM_PUK AS CR_NO ");
	            //brMainQuery.append(" AND B.HIPNUM_TRANSIN_FLG = 2 AND B.HIPNUM_MOV_NO > 0 AND (   B.HIPNUM_TRANSOUT_FLG IS NULL OR B.HIPNUM_TRANSOUT_FLG = '' ) ),'-')||'^'||nvl(ahis_function.GETDEPTUNITNAME(b.GNUM_DEPTUNIT_CODE,b.GNUM_HOSPITAL_CODE),'0')  ||'^0^'||IPD_MST.GETISMOV(HIPNUM_ADMNO,"+strHospCode+")  || '^'|| b.gnum_deptunit_code || '^' || b.hipnum_roomno as DATA,");
				brMainQuery.append(" Ahis_Function.fun_pat_name(B.HRGNUM_PUK) AS pat_name,HIPNUM_ADMNO AS adm_no,HRGNUM_PUK cr_no,GNUM_ISVALID AS IS_VALID");
				brMainQuery.append(" FROM HIPT_PAT_MOV_DTL B ");
				brMainQuery.append(" WHERE B.GNUM_ISVALID=1 AND GNUM_HOSPITAL_CODE  = ");
				brMainQuery.append((String)httpSession.getAttribute("HOSPITAL_CODE"));
				//brMainQuery.append(" AND (select to_char(Ahis_Gbl_Util.Num_Default(HIPNUM_TRANSOUT_FLG::character varying,-1)) from dual)::numeric = -1::numeric");
				brMainQuery.append(" AND HIPDT_TRANSIN_DATETIME IS NULL AND HIPNUM_TRANSIN_FLG IN (0,2,5,8) "); 
				brMainQuery.append(" AND HIPNUM_ADMNO IN (SELECT HIPNUM_ADMNO FROM HIPT_PATADMDISC_DTL WHERE GNUM_ISVALID=1 AND HIPDT_ADMSTATUS_CODE in (11,12,17) AND GNUM_HOSPITAL_CODE="+(String)httpSession.getAttribute("HOSPITAL_CODE")+") ");
				brMainQuery.append(" AND GNUM_DEPT_CODE = ");
				brMainQuery.append(cmbVal[0]);
				/*brMainQuery.append(" AND GNUM_DEPTUNIT_CODE = ");
				brMainQuery.append(cmbVal[1]);
				brMainQuery.append(" AND HIPNUM_WARDCODE = ");
				brMainQuery.append(cmbVal[2]);*/
				
				/*if(cmbVal[0]!=null || !cmbVal[0].equals("") || !cmbVal[0].equals("0"))
				{
					if(!cmbVal[0].equals("0"))
					{
						brMainQuery.append(" AND GNUM_DEPT_CODE = ");
						brMainQuery.append(cmbVal[0]);
					}
				}*/
				if(cmbVal[1]!=null || !cmbVal[1].equals("") || !cmbVal[1].equals("0"))
				{
					if(!cmbVal[1].equals("0"))
					{
						brMainQuery.append(" AND GNUM_DEPTUNIT_CODE = ");
						brMainQuery.append(cmbVal[1]);
					}
				}
				if(cmbVal[2]!=null || !cmbVal[2].equals("") || !cmbVal[2].equals("0"))
				{
					if(!cmbVal[2].equals("0"))
					{
						brMainQuery.append(" AND HIPNUM_WARDCODE = ");
						brMainQuery.append(cmbVal[2]);
					}
				}
				/*brMainQuery.append(" AND( gnum_deptunit_code IN ( SELECT to_char(hgnum_deptunitcode) FROM hgbt_unit_mst a ");
				brMainQuery.append(" WHERE gnum_isvalid = 1 AND TRUNC (SYSDATE) BETWEEN gdt_effective_frm AND TRUNC (NVL (gdt_effective_to, ");
				brMainQuery.append(" SYSDATE )) AND gnum_hospital_code = '"+strHospCode+"' AND hgnum_dept_code = '"+cmbVal[0]+"' AND EXISTS ( ");
				brMainQuery.append(" SELECT 'X' FROM gblt_role_seat_table_dtl p,gblt_metatable_column_mst q WHERE p.gnum_metatable_id = q.gnum_metatable_id ");
				brMainQuery.append(" AND gstr_table_name = 'HGBT_UNIT_MST' AND gstr_column_name = 'HGNUM_DEPTUNITCODE' AND p.gnum_seatid = ");
				brMainQuery.append(" pkg_usermgmt.fun_getseatid('"+strSeatId+"',a.gnum_hospital_code ) AND p.gnum_hospital_code = q.gnum_hospital_code ");
				brMainQuery.append(" AND p.gnum_hospital_code = a.gnum_hospital_code AND gnum_column_value = a.hgnum_deptunitcode) ) OR gnum_deptunit_code='0') ");
				brMainQuery.append(" AND hipnum_wardcode IN ( SELECT DISTINCT hipnum_ward_code FROM hipt_duwrbed_mst a WHERE gnum_isvalid = 1 ");
				brMainQuery.append(" AND ipd_mst.getwardname (hipnum_ward_code, gnum_hospital_code ) NOT LIKE '/'  AND TRUNC (SYSDATE) >= gdt_effective_frm ");
				brMainQuery.append(" AND gnum_hospital_code = '"+strHospCode+"' AND gnum_dept_code = '"+cmbVal[0]+"' AND hgnum_deptunitcode LIKE ");
				brMainQuery.append(" DECODE ('"+cmbVal[1]+"','0', '%','"+cmbVal[1]+"' ) AND EXISTS ( ");
				brMainQuery.append(" SELECT 'X' FROM gblt_role_seat_table_dtl p, gblt_metatable_column_mst q WHERE p.gnum_metatable_id = ");
				brMainQuery.append(" q.gnum_metatable_id AND gstr_table_name = 'HIPT_WARD_MST' AND gstr_column_name = 'HIPNUM_WARD_CODE' ");
				brMainQuery.append(" AND p.gnum_seatid = pkg_usermgmt.fun_getseatid('"+strSeatId+"',a.gnum_hospital_code) ");
				brMainQuery.append(" AND p.gnum_hospital_code = q.gnum_hospital_code AND p.gnum_hospital_code = a.gnum_hospital_code ");
				brMainQuery.append(" AND ahis_gbl_util.num_default(gnum_column_value) = a.hipnum_ward_code))"); */ 
				/*brMainQuery.append(" AND( gnum_deptunit_code IN (  SELECT TO_CHAR(HGNUM_DEPTUNITCODE) FROM HGBT_UNIT_MST WHERE GNUM_ISVALID=1");
				brMainQuery.append(" AND gnum_hospital_code = '"+strHospCode+"' AND HGNUM_DEPTUNITCODE IN (SELECT GNUM_COLUMN_VALUE ");
				brMainQuery.append(" FROM GBLT_ROLE_SEAT_TABLE_DTL A, GBLT_METATABLE_COLUMN_MST B  WHERE A.GNUM_METATABLE_ID = B.GNUM_METATABLE_ID AND A.GNUM_MODULE_ID=14");
				brMainQuery.append("  AND B.GSTR_TABLE_NAME = 'HGBT_UNIT_MST' AND B.GSTR_COLUMN_NAME = 'HGNUM_DEPTUNITCODE' ");
				brMainQuery.append(" AND A.GNUM_SEATID = pkg_usermgmt.fun_getseatid('"+strSeatId+"',a.gnum_hospital_code ) AND ");
				brMainQuery.append(" A.GNUM_HOSPITAL_CODE= "+strHospCode);
                brMainQuery.append(")) OR gnum_deptunit_code='0') ");*/
                
                /*brMainQuery.append(" AND( hipnum_wardcode IN (  SELECT TO_CHAR(HIPNUM_WARD_CODE) FROM HIPT_WARD_MST WHERE GNUM_ISVALID=1");
				brMainQuery.append(" AND gnum_hospital_code = '"+strHospCode+"' AND HIPNUM_WARD_CODE IN (SELECT GNUM_COLUMN_VALUE ");
				brMainQuery.append(" FROM GBLT_ROLE_SEAT_TABLE_DTL A, GBLT_METATABLE_COLUMN_MST B  WHERE A.GNUM_METATABLE_ID = B.GNUM_METATABLE_ID AND A.GNUM_MODULE_ID=14");
				brMainQuery.append("  AND B.GSTR_TABLE_NAME = 'HIPT_WARD_MST' AND B.GSTR_COLUMN_NAME = 'HIPNUM_WARD_CODE' ");
				brMainQuery.append(" AND A.GNUM_SEATID = pkg_usermgmt.fun_getseatid('"+strSeatId+"',a.gnum_hospital_code ) AND ");
				brMainQuery.append("  A.GNUM_HOSPITAL_CODE= "+strHospCode);*/
                //brMainQuery.append("))) ");
                
				/*if(cmbVal[3]!=null || !cmbVal[3].equals("") || !cmbVal[3].equals("0"))
				{
					if(!cmbVal[3].equals("0"))
					{
						brMainQuery.append(" AND HIPNUM_ROOMNO = ");				
						brMainQuery.append(cmbVal[3]);
					}
				}*/
				brMainQuery.append(") where IS_VALID =1 ");
				System.out.println("main query========="+brMainQuery.toString());
				// --ADMITTED
			} else if (cmb[4].equals("1")) {
				brMainQuery.append("SELECT DATA FROM (");
				brMainQuery.append("SELECT HRGNUM_PUK || '@' || HIPNUM_ADMNO || '@' || NVL(HIPNUM_IS_DEAD,0) ||'*'|| b.gnum_deptunit_code|| '*'|| b.hipnum_room_code || '^' || HIPNUM_ADMNO || '^' ||");
				brMainQuery.append(" HRGNUM_PUK || '^' || ");
				brMainQuery.append(" NVL((SELECT INITCAP(HRGSTR_FNAME || ' ' || HRGSTR_MNAME || ' ' || HRGSTR_LNAME) || '^' ||");
				brMainQuery.append(" (SELECT SUBSTR(INITCAP (GSTR_GENDER_NAME),0,1) FROM GBLT_GENDER_MST");
				//brMainQuery.append(" WHERE GNUM_HOSPITAL_CODE = "+strSuperHospCode+"");
				brMainQuery.append(" WHERE GNUM_ISVALID=1 AND  GSTR_GENDER_CODE = A.GSTR_GENDER_CODE)");
				brMainQuery.append(" || '/' || ipd_mst.dob_age_on (HRGDT_DOB, SYSDATE) AS AGE_SEX");
				brMainQuery.append(" FROM HRGT_PATIENT_DTL A");
				brMainQuery.append(" WHERE HRGNUM_PUK = B.HRGNUM_PUK),'-^-') || '^' || Ipd_Mst.getBedName(b.HIPNUM_BED_CODE,b.GNUM_HOSPITAL_CODE) || '^' ||");
				brMainQuery.append(" TO_CHAR(HIPDT_ADMDATETIME,'DD-MM-YY / HH24:MI')||'^'|| nvl(ahis_function.GETDEPTUNITNAME(b.GNUM_DEPTUNIT_CODE,b.gnum_hospital_code),'0')||'^'||(SELECT HRGNUM_IS_DEAD FROM HRGT_PATIENT_DTL A WHERE A.HRGNUM_PUK=B.HRGNUM_PUK AND A.GNUM_HOSPITAL_CODE=B.GNUM_HOSPITAL_CODE)||'^0' || '^' || b.gnum_deptunit_code  || '^' || b.hipnum_room_code AS DATA,");
				brMainQuery.append(" Ahis_Function.fun_pat_name(B.HRGNUM_PUK) AS pat_name,HIPNUM_ADMNO AS adm_no,HRGNUM_PUK AS cr_no,");
				//brMainQuery.append(" ahis_gbl_util.num_default(ipd_mst.REMOVEALPHABETS(ipd_mst.getbedname(b.hipnum_bed_code, b.gnum_hospital_code) )) as bed_name,");
				brMainQuery.append(" (ipd_mst.getbedname (b.hipnum_bed_code,b.gnum_hospital_code)) as bed_no,GNUM_ISVALID AS IS_VALID");
				brMainQuery.append(" FROM HIPT_PATADMISSION_DTL B");
				brMainQuery.append(" WHERE GNUM_ISVALID = 1");
				brMainQuery.append(" AND GNUM_HOSPITAL_CODE = "+strHospCode);
				brMainQuery.append(" AND HIPNUM_ISACCEPTED = 1");
				brMainQuery.append(" AND HIPDT_ADMSTATUS_CODE = 12");
				brMainQuery.append(" AND HIPDT_DISDATETIME IS NULL");
				brMainQuery.append(" AND GNUM_DEPT_CODE = ");
				brMainQuery.append(cmbVal[0]);
				/*brMainQuery.append(" AND GNUM_DEPTUNIT_CODE = ");
				brMainQuery.append(cmbVal[1]);
				brMainQuery.append(" AND HIPNUM_WARD_CODE = ");
				brMainQuery.append(cmbVal[2]);
				brMainQuery.append(" AND HIPNUM_ROOM_CODE = ");
				brMainQuery.append(cmbVal[3]);*/				
				/*if(cmbVal[0]!=null || !cmbVal[0].equals("") || !cmbVal[0].equals("0"))
				{
					if(!cmbVal[0].equals("0"))
					{
						brMainQuery.append(" AND GNUM_DEPT_CODE = ");
						brMainQuery.append(cmbVal[0]);
					}
				}*/
				if(cmbVal[1]!=null || !cmbVal[1].equals("") || !cmbVal[1].equals("0"))
				{
					if(!cmbVal[1].equals("0"))
					{
						brMainQuery.append(" AND GNUM_DEPTUNIT_CODE = ");
						brMainQuery.append(cmbVal[1]);
					}
				}
				if(cmbVal[2]!=null || !cmbVal[2].equals("") || !cmbVal[2].equals("0"))
				{
					if(!cmbVal[2].equals("0"))
					{
						brMainQuery.append(" AND HIPNUM_WARD_CODE = ");
						brMainQuery.append(cmbVal[2]);
					}
				}
				if(cmbVal[3]!=null || !cmbVal[3].equals("") || !cmbVal[3].equals("0"))
				{
					if(!cmbVal[3].equals("0"))
					{
						brMainQuery.append(" AND HIPNUM_ROOM_CODE = ");				
						brMainQuery.append(cmbVal[3]);
					}
				}
			/*	brMainQuery.append(" AND gnum_deptunit_code IN ( SELECT hgnum_deptunitcode FROM hgbt_unit_mst a ");
				brMainQuery.append(" WHERE gnum_isvalid = 1 AND TRUNC (SYSDATE) BETWEEN gdt_effective_frm AND TRUNC (NVL (gdt_effective_to, ");
				brMainQuery.append(" SYSDATE )) AND gnum_hospital_code = '"+strHospCode+"' AND hgnum_dept_code = '"+cmbVal[0]+"' ");
				brMainQuery.append(" AND EXISTS ( SELECT 'X' FROM gblt_role_seat_table_dtl p, gblt_metatable_column_mst q ");
				brMainQuery.append(" WHERE p.gnum_metatable_id = q.gnum_metatable_id AND gstr_table_name = 'HGBT_UNIT_MST' ");
				brMainQuery.append(" AND gstr_column_name = 'HGNUM_DEPTUNITCODE' AND p.gnum_seatid =  ");
				brMainQuery.append(" pkg_usermgmt.fun_getseatid ('"+strSeatId+"',a.gnum_hospital_code) ");
				brMainQuery.append(" AND p.gnum_hospital_code = q.gnum_hospital_code AND p.gnum_hospital_code = a.gnum_hospital_code ");
				brMainQuery.append(" AND gnum_column_value = a.hgnum_deptunitcode)) AND hipnum_ward_code IN ( ");
				brMainQuery.append(" SELECT DISTINCT hipnum_ward_code FROM hipt_duwrbed_mst a WHERE gnum_isvalid = 1 ");
				brMainQuery.append(" AND ipd_mst.getwardname (hipnum_ward_code,gnum_hospital_code ");
				brMainQuery.append(" ) NOT LIKE '/' AND TRUNC (SYSDATE) >= gdt_effective_frm AND gnum_hospital_code = '"+strHospCode+"' ");
				brMainQuery.append(" AND gnum_dept_code = '"+cmbVal[0]+"' AND hgnum_deptunitcode LIKE DECODE ('"+cmbVal[1]+"','0', '%','"+cmbVal[1]+"') ");
				brMainQuery.append(" AND EXISTS ( SELECT 'X' FROM gblt_role_seat_table_dtl p, gblt_metatable_column_mst q ");
				brMainQuery.append(" WHERE p.gnum_metatable_id = q.gnum_metatable_id AND gstr_table_name = 'HIPT_WARD_MST' ");
				brMainQuery.append(" AND gstr_column_name = 'HIPNUM_WARD_CODE' AND p.gnum_seatid = pkg_usermgmt.fun_getseatid ('"+strSeatId+"', a.gnum_hospital_code ) ");
				brMainQuery.append(" AND p.gnum_hospital_code = q.gnum_hospital_code AND p.gnum_hospital_code =a.gnum_hospital_code ");
				brMainQuery.append(" AND ahis_gbl_util.num_default(gnum_column_value) = a.hipnum_ward_code))");*/
				/*brMainQuery.append(" AND( gnum_deptunit_code IN (  SELECT TO_CHAR(HGNUM_DEPTUNITCODE) FROM HGBT_UNIT_MST WHERE GNUM_ISVALID=1");
				brMainQuery.append(" AND gnum_hospital_code = '"+strHospCode+"' AND HGNUM_DEPTUNITCODE IN (SELECT GNUM_COLUMN_VALUE ");
				brMainQuery.append(" FROM GBLT_ROLE_SEAT_TABLE_DTL A, GBLT_METATABLE_COLUMN_MST B  WHERE A.GNUM_METATABLE_ID = B.GNUM_METATABLE_ID AND A.GNUM_MODULE_ID=14");
				brMainQuery.append("  AND B.GSTR_TABLE_NAME = 'HGBT_UNIT_MST' AND B.GSTR_COLUMN_NAME = 'HGNUM_DEPTUNITCODE' ");
				brMainQuery.append(" AND A.GNUM_SEATID = pkg_usermgmt.fun_getseatid('"+strSeatId+"',a.gnum_hospital_code ) AND ");
				brMainQuery.append(" A.GNUM_HOSPITAL_CODE= "+strHospCode);
                brMainQuery.append(")) OR gnum_deptunit_code='0') ");
                
                brMainQuery.append(" AND( hipnum_ward_code IN (  SELECT TO_CHAR(HIPNUM_WARD_CODE) FROM HIPT_WARD_MST WHERE GNUM_ISVALID=1");
				brMainQuery.append(" AND gnum_hospital_code = '"+strHospCode+"' AND HIPNUM_WARD_CODE IN (SELECT GNUM_COLUMN_VALUE ");
				brMainQuery.append(" FROM GBLT_ROLE_SEAT_TABLE_DTL A, GBLT_METATABLE_COLUMN_MST B  WHERE A.GNUM_METATABLE_ID = B.GNUM_METATABLE_ID AND A.GNUM_MODULE_ID=14");
				brMainQuery.append("  AND B.GSTR_TABLE_NAME = 'HIPT_WARD_MST' AND B.GSTR_COLUMN_NAME = 'HIPNUM_WARD_CODE' ");
				brMainQuery.append(" AND A.GNUM_SEATID = pkg_usermgmt.fun_getseatid('"+strSeatId+"',a.gnum_hospital_code ) AND ");
				brMainQuery.append(" A.GNUM_HOSPITAL_CODE= "+strHospCode);
                brMainQuery.append("))) ");*/
				brMainQuery.append(") where IS_VALID =1 ");
				// --LEAVE
			} else if (cmb[4].equals("3")) {
				brMainQuery.append("SELECT DATA FROM (");
				brMainQuery.append(" SELECT HRGNUM_PUK || '@' || HIPNUM_ADMNO || '@0@'|| (SELECT nvl(max(HIPNUM_MOV_NO),1) FROM hipt_pat_mov_dtl X where X.HRGNUM_PUK= b.HRGNUM_PUK"+
									" AND X.hipnum_admno=b.hipnum_admno AND X.GNUM_DEPT_CODE =b.gnum_dept_code AND X.HIPNUM_WARDCODE= b.hipnum_ward_code and HIPNUM_TRANSIN_FLG=4)|| '@'|| HIPNUM_ADMNO  ||'*'|| b.gnum_deptunit_code|| '*'|| b.hipnum_room_code || '^' || HIPNUM_ADMNO || '^' ||");
				brMainQuery.append(" HRGNUM_PUK || '^' || ");
				brMainQuery.append(" NVL((SELECT INITCAP(HRGSTR_FNAME || ' ' || HRGSTR_MNAME || ' ' || HRGSTR_LNAME) || '^' ||");
				brMainQuery.append(" (SELECT SUBSTR(INITCAP (GSTR_GENDER_NAME),0,1) FROM GBLT_GENDER_MST");
				//brMainQuery.append(" WHERE GNUM_HOSPITAL_CODE = "+strSuperHospCode+"");
				brMainQuery.append(" WHERE GNUM_ISVALID=1 AND GSTR_GENDER_CODE = A.GSTR_GENDER_CODE)");
				brMainQuery.append(" || '/' || ipd_mst.dob_age_on (HRGDT_DOB, SYSDATE) AS AGE_SEX");
				brMainQuery.append(" FROM HRGT_PATIENT_DTL A");
				brMainQuery.append(" WHERE HRGNUM_PUK = B.HRGNUM_PUK),'-^-') || '^' || Ipd_Mst.getBedName(b.HIPNUM_BED_CODE,b.GNUM_HOSPITAL_CODE) || '^' ||");
				brMainQuery.append(" TO_CHAR(HIPDT_ADMDATETIME,'DD-MM-YY / HH24:MI')||'^^^^'||ipd_mst.getismov(HIPNUM_ADMNO,"+strHospCode+")|| '^' || b.gnum_deptunit_code || '^'|| b.hipnum_room_code AS DATA,");
				brMainQuery.append("Ahis_Function.fun_pat_name(B.HRGNUM_PUK) AS pat_name,HRGNUM_PUK AS cr_no,");
				//brMainQuery.append(" ahis_gbl_util.num_default(ipd_mst.REMOVEALPHABETS(ipd_mst.getbedname(b.hipnum_bed_code, b.gnum_hospital_code) )) as bed_name,GNUM_ISVALID AS IS_VALID,HIPNUM_ADMNO as adm_no ");
				brMainQuery.append(" (ipd_mst.getbedname (b.hipnum_bed_code,b.gnum_hospital_code)) as bed_no,GNUM_ISVALID AS IS_VALID,HIPNUM_ADMNO as adm_no ");
				brMainQuery.append(" FROM HIPT_PATADMISSION_DTL b");
				brMainQuery.append(" WHERE GNUM_ISVALID = 1");
				brMainQuery.append(" AND GNUM_HOSPITAL_CODE = "+strHospCode);
				brMainQuery.append(" AND HIPNUM_ISACCEPTED = 1");
				brMainQuery.append(" AND HIPDT_ADMSTATUS_CODE = 15");
				brMainQuery.append(" AND HIPDT_DISDATETIME IS NULL");
				brMainQuery.append(" AND GNUM_DEPT_CODE = ");
				brMainQuery.append(cmbVal[0]);
				/*brMainQuery.append(" AND GNUM_DEPTUNIT_CODE = ");
				brMainQuery.append(cmbVal[1]);
				brMainQuery.append(" AND HIPNUM_WARD_CODE = ");
				brMainQuery.append(cmbVal[2]);
				brMainQuery.append(" AND HIPNUM_ROOM_CODE = ");
				brMainQuery.append(cmbVal[3]);*/
				/*if(cmbVal[0]!=null || !cmbVal[0].equals("") || !cmbVal[0].equals("0"))
				{
					if(!cmbVal[0].equals("0"))
					{
						brMainQuery.append(" AND GNUM_DEPT_CODE = ");
						brMainQuery.append(cmbVal[0]);
					}
				}*/
				if(cmbVal[1]!=null || !cmbVal[1].equals("") || !cmbVal[1].equals("0"))
				{
					if(!cmbVal[1].equals("0"))
					{
						brMainQuery.append(" AND GNUM_DEPTUNIT_CODE = ");
						brMainQuery.append(cmbVal[1]);
					}
				}
				if(cmbVal[2]!=null || !cmbVal[2].equals("") || !cmbVal[2].equals("0"))
				{
					if(!cmbVal[2].equals("0"))
					{
						brMainQuery.append(" AND HIPNUM_WARD_CODE = ");
						brMainQuery.append(cmbVal[2]);
					}
				}				
				if(cmbVal[3]!=null || !cmbVal[3].equals("") || !cmbVal[3].equals("0"))
				{
					if(!cmbVal[3].equals("0"))
					{
						brMainQuery.append(" AND HIPNUM_ROOM_CODE = ");				
						brMainQuery.append(cmbVal[3]);
					}
				}
				/*brMainQuery.append(" AND gnum_deptunit_code IN ( SELECT hgnum_deptunitcode FROM hgbt_unit_mst a ");
				brMainQuery.append(" WHERE gnum_isvalid = 1 AND TRUNC (SYSDATE) BETWEEN gdt_effective_frm AND TRUNC (NVL (gdt_effective_to, ");
				brMainQuery.append(" SYSDATE )) AND gnum_hospital_code = '"+strHospCode+"' AND hgnum_dept_code = '"+cmbVal[0]+"' AND EXISTS ( ");
				brMainQuery.append(" SELECT 'X' FROM gblt_role_seat_table_dtl p,gblt_metatable_column_mst q WHERE p.gnum_metatable_id = q.gnum_metatable_id ");
				brMainQuery.append(" AND gstr_table_name = 'HGBT_UNIT_MST' AND gstr_column_name = 'HGNUM_DEPTUNITCODE' AND p.gnum_seatid = ");
				brMainQuery.append(" pkg_usermgmt.fun_getseatid('"+strSeatId+"',a.gnum_hospital_code ) AND p.gnum_hospital_code = q.gnum_hospital_code ");
				brMainQuery.append(" AND p.gnum_hospital_code = a.gnum_hospital_code AND gnum_column_value = a.hgnum_deptunitcode)) ");
				brMainQuery.append(" AND hipnum_ward_code IN ( SELECT DISTINCT hipnum_ward_code FROM hipt_duwrbed_mst a WHERE gnum_isvalid = 1 ");
				brMainQuery.append(" AND ipd_mst.getwardname (hipnum_ward_code, gnum_hospital_code ) NOT LIKE '/'  AND TRUNC (SYSDATE) >= gdt_effective_frm ");
				brMainQuery.append(" AND gnum_hospital_code = '"+strHospCode+"' AND gnum_dept_code = '"+cmbVal[0]+"' AND hgnum_deptunitcode LIKE ");
				brMainQuery.append(" DECODE ('"+cmbVal[1]+"','0', '%','"+cmbVal[1]+"' ) AND EXISTS ( ");
				brMainQuery.append(" SELECT 'X' FROM gblt_role_seat_table_dtl p, gblt_metatable_column_mst q WHERE p.gnum_metatable_id = ");
				brMainQuery.append(" q.gnum_metatable_id AND gstr_table_name = 'HIPT_WARD_MST' AND gstr_column_name = 'HIPNUM_WARD_CODE' ");
				brMainQuery.append(" AND p.gnum_seatid = pkg_usermgmt.fun_getseatid('"+strSeatId+"',a.gnum_hospital_code) ");
				brMainQuery.append(" AND p.gnum_hospital_code = q.gnum_hospital_code AND p.gnum_hospital_code = a.gnum_hospital_code ");
				brMainQuery.append(" AND ahis_gbl_util.num_default(gnum_column_value) = a.hipnum_ward_code))");*/ 
				/*brMainQuery.append(" AND( gnum_deptunit_code IN (  SELECT TO_CHAR(HGNUM_DEPTUNITCODE) FROM HGBT_UNIT_MST WHERE GNUM_ISVALID=1");
				brMainQuery.append(" AND gnum_hospital_code = '"+strHospCode+"' AND HGNUM_DEPTUNITCODE IN (SELECT GNUM_COLUMN_VALUE ");
				brMainQuery.append(" FROM GBLT_ROLE_SEAT_TABLE_DTL A, GBLT_METATABLE_COLUMN_MST B  WHERE A.GNUM_METATABLE_ID = B.GNUM_METATABLE_ID AND A.GNUM_MODULE_ID=14");
				brMainQuery.append("  AND B.GSTR_TABLE_NAME = 'HGBT_UNIT_MST' AND B.GSTR_COLUMN_NAME = 'HGNUM_DEPTUNITCODE' ");
				brMainQuery.append(" AND A.GNUM_SEATID = pkg_usermgmt.fun_getseatid('"+strSeatId+"',a.gnum_hospital_code ) AND ");
				brMainQuery.append(" A.GNUM_HOSPITAL_CODE= "+strHospCode);
                brMainQuery.append(")) OR gnum_deptunit_code='0') ");
                
                brMainQuery.append(" AND( hipnum_ward_code IN (  SELECT TO_CHAR(HIPNUM_WARD_CODE) FROM HIPT_WARD_MST WHERE GNUM_ISVALID=1");
				brMainQuery.append(" AND gnum_hospital_code = '"+strHospCode+"' AND HIPNUM_WARD_CODE IN (SELECT GNUM_COLUMN_VALUE ");
				brMainQuery.append(" FROM GBLT_ROLE_SEAT_TABLE_DTL A, GBLT_METATABLE_COLUMN_MST B  WHERE A.GNUM_METATABLE_ID = B.GNUM_METATABLE_ID AND A.GNUM_MODULE_ID=14");
				brMainQuery.append("  AND B.GSTR_TABLE_NAME = 'HIPT_WARD_MST' AND B.GSTR_COLUMN_NAME = 'HIPNUM_WARD_CODE' ");
				brMainQuery.append(" AND A.GNUM_SEATID = pkg_usermgmt.fun_getseatid('"+strSeatId+"',a.gnum_hospital_code ) AND ");
				brMainQuery.append(" A.GNUM_HOSPITAL_CODE= "+strHospCode);
                brMainQuery.append("))) ");*/
				brMainQuery.append(") where IS_VALID =1 ");
			}else if (cmb[4].equals("4")) {//intransit
				brMainQuery.append("SELECT DATA FROM ( ");
				brMainQuery.append("SELECT HRGNUM_PUK || '@' || HIPNUM_ADMNO || '@0' || HIPNUM_ADMNO ||'*'|| b.gnum_deptunit_code|| '*'|| b.hipnum_room_code  || '^' || hipnum_admno || '^'|| ");  
				brMainQuery.append("HRGNUM_PUK || '^' ||  ");
				brMainQuery.append(" NVL((SELECT INITCAP(HRGSTR_FNAME || ' ' || HRGSTR_MNAME || ' ' || HRGSTR_LNAME)  AS AGE_SEX  ");
				brMainQuery.append("FROM HRGT_PATIENT_DTL A ");
				brMainQuery.append("WHERE HRGNUM_PUK = B.HRGNUM_PUK),'-')||'^'|| ");
				brMainQuery.append("(SELECT IPD_MST.GETDEPTNAME(A.GNUM_DEPT_CODE,A.GNUM_HOSPITAL_CODE) || '^' || "); 
				brMainQuery.append("IPD_MST.GETWARDNAME(A.HIPNUM_WARDCODE,A.GNUM_HOSPITAL_CODE,SYSDATE::character varying,NULL::character varying)||'^'|| ");
				brMainQuery.append("IPD_MST.GETROOMNAME(A.HIPNUM_ROOMNO,A.GNUM_HOSPITAL_CODE)||'^'||to_char(A.HIPDT_TRANS_DATETIME,'DD-MM-YY / HH24:MI') from HIPT_PAT_MOV_DTL A ");
				brMainQuery.append(" WHERE A.HIPNUM_ADMNO = b.HIPNUM_ADMNO  ");
				brMainQuery.append("AND A.GNUM_HOSPITAL_CODE = b.GNUM_HOSPITAL_CODE ");
				brMainQuery.append("AND (A.HIPNUM_TRANSOUT_FLG IS NULL OR A.HIPNUM_TRANSOUT_FLG = null::character varying) ");
				brMainQuery.append(" )||'^'|| ");
				brMainQuery.append("HIPDT_ADMSTATUS_CODE || '^'|| b.gnum_deptunit_code || '^'|| b.hipnum_room_code AS DATA, ");
				brMainQuery.append("Ahis_Function.fun_pat_name(B.HRGNUM_PUK) AS "); 
				brMainQuery.append("pat_name,HRGNUM_PUK AS cr_no,GNUM_ISVALID AS IS_VALID,"); 
				brMainQuery.append("hipnum_admno as adm_no ");
				brMainQuery.append("FROM HIPT_PATADMISSION_DTL B ");
				brMainQuery.append("WHERE GNUM_ISVALID = 1 ");
				brMainQuery.append("AND GNUM_HOSPITAL_CODE =  ");
				brMainQuery.append(strHospCode);
				brMainQuery.append(" AND HIPDT_ADMSTATUS_CODE in (11,17) ");
				brMainQuery.append(" AND B.GNUM_DEPT_CODE = ");
				brMainQuery.append(cmbVal[0]);
				/*brMainQuery.append(" AND GNUM_DEPTUNIT_CODE = ");
				brMainQuery.append(cmbVal[1]);
				brMainQuery.append(" AND HIPNUM_WARD_CODE = ");
				brMainQuery.append(cmbVal[2]);
				brMainQuery.append(" AND HIPNUM_ROOM_CODE = ");
				brMainQuery.append(cmbVal[3]);*/
				/*if(cmbVal[0]!=null || !cmbVal[0].equals("") || !cmbVal[0].equals("0"))
				{
					if(!cmbVal[0].equals("0"))
					{
						brMainQuery.append(" AND GNUM_DEPT_CODE = ");
						brMainQuery.append(cmbVal[0]);
					}
				}*/
				if(cmbVal[1]!=null || !cmbVal[1].equals("") || !cmbVal[1].equals("0"))
				{
					if(!cmbVal[1].equals("0"))
					{
						brMainQuery.append(" AND B.GNUM_DEPTUNIT_CODE = ");
						brMainQuery.append(cmbVal[1]);
					}
				}
				if(cmbVal[2]!=null || !cmbVal[2].equals("") || !cmbVal[2].equals("0"))
				{
					if(!cmbVal[2].equals("0"))
					{
						brMainQuery.append(" AND B.HIPNUM_WARD_CODE = ");
						brMainQuery.append(cmbVal[2]);
					}
				}				
				if(cmbVal[3]!=null || !cmbVal[3].equals("") || !cmbVal[3].equals("0"))
				{
					if(!cmbVal[3].equals("0"))
					{
						brMainQuery.append(" AND B.HIPNUM_ROOM_CODE = ");				
						brMainQuery.append(cmbVal[3]);
					}
				}
				/*brMainQuery.append(" AND B.gnum_deptunit_code IN ( SELECT hgnum_deptunitcode FROM hgbt_unit_mst a ");
				brMainQuery.append(" WHERE gnum_isvalid = 1 AND TRUNC (SYSDATE) BETWEEN gdt_effective_frm AND TRUNC (NVL (gdt_effective_to, ");
				brMainQuery.append(" SYSDATE )) AND gnum_hospital_code = '"+strHospCode+"' AND hgnum_dept_code = '"+cmbVal[0]+"' ");
				brMainQuery.append(" AND EXISTS ( SELECT 'X' FROM gblt_role_seat_table_dtl p, gblt_metatable_column_mst q ");
				brMainQuery.append(" WHERE p.gnum_metatable_id = q.gnum_metatable_id AND gstr_table_name = 'HGBT_UNIT_MST' ");
				brMainQuery.append(" AND gstr_column_name = 'HGNUM_DEPTUNITCODE' AND p.gnum_seatid =  ");
				brMainQuery.append(" pkg_usermgmt.fun_getseatid ('"+strSeatId+"',a.gnum_hospital_code) ");
				brMainQuery.append(" AND p.gnum_hospital_code = q.gnum_hospital_code AND p.gnum_hospital_code = a.gnum_hospital_code ");
				brMainQuery.append(" AND gnum_column_value = a.hgnum_deptunitcode)) AND hipnum_ward_code IN ( ");
				brMainQuery.append(" SELECT DISTINCT hipnum_ward_code FROM hipt_duwrbed_mst a WHERE gnum_isvalid = 1 ");
				brMainQuery.append(" AND ipd_mst.getwardname (hipnum_ward_code,gnum_hospital_code ");
				brMainQuery.append(" ) NOT LIKE '/' AND TRUNC (SYSDATE) >= gdt_effective_frm AND gnum_hospital_code = '"+strHospCode+"' ");
				brMainQuery.append(" AND gnum_dept_code = '"+cmbVal[0]+"' AND hgnum_deptunitcode LIKE DECODE ('"+cmbVal[1]+"','0', '%','"+cmbVal[1]+"') ");
				brMainQuery.append(" AND EXISTS ( SELECT 'X' FROM gblt_role_seat_table_dtl p, gblt_metatable_column_mst q ");
				brMainQuery.append(" WHERE p.gnum_metatable_id = q.gnum_metatable_id AND gstr_table_name = 'HIPT_WARD_MST' ");
				brMainQuery.append(" AND gstr_column_name = 'HIPNUM_WARD_CODE' AND p.gnum_seatid = pkg_usermgmt.fun_getseatid ('"+strSeatId+"', a.gnum_hospital_code ) ");
				brMainQuery.append(" AND p.gnum_hospital_code = q.gnum_hospital_code AND p.gnum_hospital_code =a.gnum_hospital_code ");
				brMainQuery.append(" AND ahis_gbl_util.num_default(gnum_column_value) = a.hipnum_ward_code))");*/
				/*brMainQuery.append(" AND( gnum_deptunit_code IN (  SELECT TO_CHAR(HGNUM_DEPTUNITCODE) FROM HGBT_UNIT_MST WHERE GNUM_ISVALID=1");
				brMainQuery.append(" AND gnum_hospital_code = '"+strHospCode+"' AND HGNUM_DEPTUNITCODE IN (SELECT GNUM_COLUMN_VALUE ");
				brMainQuery.append(" FROM GBLT_ROLE_SEAT_TABLE_DTL A, GBLT_METATABLE_COLUMN_MST B  WHERE A.GNUM_METATABLE_ID = B.GNUM_METATABLE_ID AND A.GNUM_MODULE_ID=14");
				brMainQuery.append("  AND B.GSTR_TABLE_NAME = 'HGBT_UNIT_MST' AND B.GSTR_COLUMN_NAME = 'HGNUM_DEPTUNITCODE' ");
				brMainQuery.append(" AND A.GNUM_SEATID = pkg_usermgmt.fun_getseatid('"+strSeatId+"',a.gnum_hospital_code ) AND ");
				brMainQuery.append(" A.GNUM_HOSPITAL_CODE= "+strHospCode);
                brMainQuery.append(")) OR gnum_deptunit_code='0') ");
                
                brMainQuery.append(" AND( hipnum_ward_code IN (  SELECT TO_CHAR(HIPNUM_WARD_CODE) FROM HIPT_WARD_MST WHERE GNUM_ISVALID=1");
				brMainQuery.append(" AND gnum_hospital_code = '"+strHospCode+"' AND HIPNUM_WARD_CODE IN (SELECT GNUM_COLUMN_VALUE ");
				brMainQuery.append(" FROM GBLT_ROLE_SEAT_TABLE_DTL A, GBLT_METATABLE_COLUMN_MST B  WHERE A.GNUM_METATABLE_ID = B.GNUM_METATABLE_ID AND A.GNUM_MODULE_ID=14");
				brMainQuery.append("  AND B.GSTR_TABLE_NAME = 'HIPT_WARD_MST' AND B.GSTR_COLUMN_NAME = 'HIPNUM_WARD_CODE' ");
				brMainQuery.append(" AND A.GNUM_SEATID = pkg_usermgmt.fun_getseatid('"+strSeatId+"',a.gnum_hospital_code ) AND ");
				brMainQuery.append("  A.GNUM_HOSPITAL_CODE= "+strHospCode);
                brMainQuery.append("))) ");*/
				brMainQuery.append(") where IS_VALID =1 "); 
			}
		} else {
			brMainQuery.append("SELECT DATA FROM (");
			brMainQuery.append("SELECT HRGNUM_PUK || '@' || HIPNUM_ADMNO || '@0' || '^' || HIPNUM_ADMNO || '^' || HRGNUM_PUK || '^' ||");
			brMainQuery.append(" NVL((SELECT INITCAP(HRGSTR_FNAME || ' ' || HRGSTR_MNAME || ' ' || HRGSTR_LNAME) || '^' || ");
			brMainQuery.append(" (SELECT SUBSTR(INITCAP (GSTR_GENDER_NAME),0,1) FROM GBLT_GENDER_MST");
			//brMainQuery.append(" WHERE GNUM_HOSPITAL_CODE = B.GNUM_HOSPITAL_CODE");
			brMainQuery.append(" WHERE GNUM_ISVALID=1 AND  GSTR_GENDER_CODE = A.GSTR_GENDER_CODE)");
			brMainQuery.append(" || '/' || ipd_mst.dob_age_on (HRGDT_DOB, SYSDATE) AS AGE_SEX");
			brMainQuery.append(" FROM HRGT_PATIENT_DTL A");
			brMainQuery.append(" WHERE HRGNUM_PUK = B.HRGNUM_PUK),'-^-') || '^' || Ipd_Mst.getBedName(b.HIPNUM_BED_CODE,b.GNUM_HOSPITAL_CODE) || '^' ||");
			brMainQuery.append(" TO_CHAR(HIPDT_ADMDATETIME,'dd/mm/yyyy hh24:mi') AS DATA,");
			brMainQuery.append("Ahis_Function.fun_pat_name(B.HRGNUM_PUK) AS pat_name,HIPNUM_ADMNO AS adm_no,");
			brMainQuery.append(" ahis_gbl_util.num_default(ipd_mst.REMOVEALPHABETS(ipd_mst.getbedname(b.hipnum_bed_code, b.gnum_hospital_code) )) as bed_name ,GNUM_ISVALID AS IS_VALID");
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
		System.out.println("main query"+brMainQuery.toString());
		return brMainQuery.toString();
	}
	public String[] getSearchField() {
		String[] search_field = { "1", "adm_no", "3", "pat_name" ,"2","cr_no"};
		if((cmbVal != null && cmbVal[4].equals("4")) || (cmbVal != null && cmbVal[4].equals("3"))){
			search_field = new String[6];
			search_field[0] = "1";
			search_field[1] = "adm_no";
			search_field[2] = "2";
			search_field[3] = "pat_name";
			search_field[4] = "3";
			search_field[5] = "cr_no";
		}
		
		return search_field;
	}
	//1^0 means User defined combo not reset
	//strComboHeader[0] = "0^2" MEANS 0- QUERY BASED COMBO VALUES,1-HARD CODED COMBO VALUES. AFTER ^ 1-ALL,2-DEFAULT SELECTED FIRST VALUE,0-SELECT VALUE
	//Combo Name^0 Means Combo Non Mnadatory-non need to mention it if non mandatory
	//Combo Name^1 Means Combo Mnadatory-Specify only if mandatory
	public String[] getComboHeader() {
		IpdConfigUtil ipdConfigUtil=new IpdConfigUtil(this.httpSession.getAttribute("HOSPITAL_CODE").toString());
		if(ipdConfigUtil.getStrUnitNameReq().equals("0") && ipdConfigUtil.getStrRoomNoReq().equals("0"))
		{
			String[] strComboHeader = { "0^0", "Department^1", "0^1", "&nbsp; Unit", "0^0",
					"Ward^1", "0^1", "Room", "1", "Services^1" };
			return strComboHeader;
		}
		else if(ipdConfigUtil.getStrUnitNameReq().equals("0") && ipdConfigUtil.getStrRoomNoReq().equals("1"))
		{
			String[] strComboHeader = { "0^0", "Department^1", "0^1", "&nbsp; Unit", "0^0",
					"Ward^1", "0^0", "Room", "1", "Services^1" };
			return strComboHeader;
		}
		else if(ipdConfigUtil.getStrUnitNameReq().equals("1") && ipdConfigUtil.getStrRoomNoReq().equals("0"))
		{
			String[] strComboHeader = { "0^0", "Department^1", "0^0", "&nbsp; Unit", "0^0",
					"Ward^1", "0^1", "Room", "1", "Services^1" };
			return strComboHeader;
		}
		else
		{
			String[] strComboHeader = { "0", "Department^1", "0", "&nbsp; Unit", "0",
				"Ward^1", "0", "Room", "1", "Services^1" };
			return strComboHeader;
		}

		
	}
	
	public String[] getComboReset() {
		String[] strComboReset = { "0","0","0","0","1" };

		return strComboReset;
	}
	public String[] getColumnHeader() {
		/*String[] strColumnHeader = { "Admission No.", "CR No.", "Patient Name",
				"Sex/Age", "Bed Name", "Admission Date","Transfer" };*/
		/*String[] strColumnHeader = { "Admission No.^15", "CR No.^15", "Patient Name^17",
				"Sex/Age^7", "Admission Date^15","Transfer From Dept/Ward/Time^16","Unit^15" };
		if (cmbVal != null && cmbVal[4].equals("3")) {//Leave
			strColumnHeader = new String[6];
			strColumnHeader[0] = "Admission No.^15";
			strColumnHeader[1] = "CR No.^15";
			strColumnHeader[2] = "Patient Name^25";
			strColumnHeader[3] = "Sex/Age^15";
			strColumnHeader[4] = "Bed Name^15";
			strColumnHeader[5] = "Admission Date^15";
		}
		if (cmbVal != null && cmbVal[4].equals("1")) {//Admitted
			strColumnHeader = new String[7];
			strColumnHeader[0] = "Admission No.^15";
			strColumnHeader[1] = "CR No.^15";
			strColumnHeader[2] = "Patient Name^20";
			strColumnHeader[3] = "Sex/Age^10";
			strColumnHeader[4] = "Bed Name^10";
			strColumnHeader[5] = "Admission Date^15";
			strColumnHeader[6] = "Unit^15";
		}
		if (cmbVal != null && cmbVal[4].equals("4")) {//Intransit
			strColumnHeader = new String[7];
			strColumnHeader[0] = "Admission No.^12";
			strColumnHeader[1] = "CR No.^13";
			strColumnHeader[2] = "Patient Name^20";
			strColumnHeader[3] = "Transfer To Dept^15";
			strColumnHeader[4] = "Ward^15";
			strColumnHeader[5] = "Room^10";
			strColumnHeader[6] = "Transfer Date/Time^15";
		}
		if (cmbVal != null && cmbVal[4].equals("2")) {//Non Accepted
			strColumnHeader = new String[7];
			strColumnHeader[0] = "Admission No.^15";
			strColumnHeader[1] = "CR No.^15";
			strColumnHeader[2] = "Patient Name^17";
			strColumnHeader[3] = "Sex/Age^7";
			//strColumnHeader[4] = "Bed Name";
			strColumnHeader[4] = "Admission Date^15";
			strColumnHeader[5] = "Transfer From Dept/Ward/Time^16";
			strColumnHeader[6] = "Unit^15";
		}*/
		String[] strColumnHeader = { "Admission No.", "CR No.", "Patient Name",
				"Sex/Age", "Admission Date","Transfer From Dept/Ward/Time","Unit" };
		if (cmbVal != null && cmbVal[4].equals("3")) {//Leave
			strColumnHeader = new String[6];
			strColumnHeader[0] = "Admission No.";
			strColumnHeader[1] = "CR No.";
			strColumnHeader[2] = "Patient Name";
			strColumnHeader[3] = "Sex/Age";
			strColumnHeader[4] = "Bed Name";
			strColumnHeader[5] = "Admission Date";
		}
		if (cmbVal != null && cmbVal[4].equals("1")) {//Admitted
			strColumnHeader = new String[7];
			strColumnHeader[0] = "Admission No.";
			strColumnHeader[1] = "CR No.";
			strColumnHeader[2] = "Patient Name";
			strColumnHeader[3] = "Sex/Age";
			strColumnHeader[4] = "Bed Name";
			strColumnHeader[5] = "Admission Date";
			strColumnHeader[6] = "Unit";
		}
		if (cmbVal != null && cmbVal[4].equals("4")) {//Intransit
			strColumnHeader = new String[7];
			strColumnHeader[0] = "Admission No.";
			strColumnHeader[1] = "CR No.";
			strColumnHeader[2] = "Patient Name";
			strColumnHeader[3] = "Transfer To Dept";
			strColumnHeader[4] = "Ward";
			strColumnHeader[5] = "Room";
			strColumnHeader[6] = "Transfer Date/Time";
		}
		if (cmbVal != null && cmbVal[4].equals("2")) {//Non Accepted
			strColumnHeader = new String[7];
			strColumnHeader[0] = "Admission No.";
			strColumnHeader[1] = "CR No.";
			strColumnHeader[2] = "Patient Name";
			strColumnHeader[3] = "Sex/Age";
			//strColumnHeader[4] = "Bed Name";
			strColumnHeader[4] = "Admission Date";
			strColumnHeader[5] = "Transfer From Dept/Ward/Time";
			strColumnHeader[6] = "Unit";
		}
		return strColumnHeader;
	}
	/*
	public String[] getColumnHeader() {
		/*String[] strColumnHeader = { "Admission No.", "CR No.", "Patient Name",
				"Sex/Age", "Bed Name", "Admission Date","Transfer" };
		String[] strColumnHeader = { "Admission No.", "CR No.", "Patient Name",
				"Sex/Age", "Admission Date","Transfer From Dept/Ward/Time","Unit" };
		if (cmbVal != null && cmbVal[4].equals("3")) {//Leave
			strColumnHeader = new String[6];
			strColumnHeader[0] = "Admission No.";
			strColumnHeader[1] = "CR No.";
			strColumnHeader[2] = "Patient Name";
			strColumnHeader[3] = "Sex/Age";
			strColumnHeader[4] = "Bed Name";
			strColumnHeader[5] = "Admission Date";
		}
		if (cmbVal != null && cmbVal[4].equals("1")) {//Admitted
			strColumnHeader = new String[7];
			strColumnHeader[0] = "Admission No.";
			strColumnHeader[1] = "CR No.";
			strColumnHeader[2] = "Patient Name";
			strColumnHeader[3] = "Sex/Age";
			strColumnHeader[4] = "Bed Name";
			strColumnHeader[5] = "Admission Date";
			strColumnHeader[6] = "Unit";
		}
		if (cmbVal != null && cmbVal[4].equals("4")) {//Intransit
			strColumnHeader = new String[7];
			strColumnHeader[0] = "Admission No.";
			strColumnHeader[1] = "CR No.";
			strColumnHeader[2] = "Patient Name";
			strColumnHeader[3] = "Transfer To Dept";
			strColumnHeader[4] = "Ward";
			strColumnHeader[5] = "Room";
			strColumnHeader[6] = "Transfer Date/Time";
		}
		if (cmbVal != null && cmbVal[4].equals("2")) {//Non Accepted
			strColumnHeader = new String[7];
			strColumnHeader[0] = "Admission No.";
			strColumnHeader[1] = "CR No.";
			strColumnHeader[2] = "Patient Name";
			strColumnHeader[3] = "Sex/Age";
			//strColumnHeader[4] = "Bed Name";
			strColumnHeader[4] = "Admission Date";
			strColumnHeader[5] = "Transfer From Dept/Ward/Time";
			strColumnHeader[6] = "Unit";
		}
		return strColumnHeader;
	}*/
	public String[] getComboQuery() {
		String[] strComboQry = new String[5];
		String strHospCode = (String) httpSession.getAttribute("HOSPITAL_CODE");
		String strSeatId = (String) httpSession.getAttribute("SEATID");
		/*strComboQry[0] = " select    GNUM_DEPT_CODE,GSTR_DEPT_NAME    from     GBLT_DEPARTMENT_MST    where     GNUM_HOSPITAL_CODE = '"
				+ strHospCode
				+ "' AND GNUM_DEPT_TYPE = 1 AND   GNUM_ISVALID =1  AND "
				+ " TRUNC(SYSDATE) BETWEEN GDT_EFFECTIVE_FRM AND TRUNC(NVL(GDT_EFFECTIVE_TO,SYSDATE)) "
				+ " ORDER BY GSTR_DEPT_NAME";

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
				+ " AND Ipd_Mst.getWardName(HIPNUM_WARD_CODE,GNUM_HOSPITAL_CODE ) not like '/' ";*/
		
		strComboQry[0] = " SELECT GNUM_DEPT_CODE,GSTR_DEPT_NAME FROM GBLT_DEPARTMENT_MST A "+
						 " WHERE GNUM_ISVALID = 1 "+
						// " AND TRUNC(SYSDATE) BETWEEN GDT_EFFECTIVE_FRM AND TRUNC(NVL(GDT_EFFECTIVE_TO,SYSDATE)) "+
						 " AND GNUM_HOSPITAL_CODE = '" + strHospCode+"' AND GNUM_DEPT_TYPE = 1 "+
						 " AND EXISTS " +
						 " (SELECT 'X' "+
						 " FROM GBLT_ROLE_SEAT_TABLE_DTL P,GBLT_METATABLE_COLUMN_MST Q "+
						 " WHERE P.GNUM_METATABLE_ID = Q.GNUM_METATABLE_ID "+
						 "AND  P.GNUM_MODULE_ID=14 " +
						 " AND Q.GSTR_TABLE_NAME = 'GBLT_DEPARTMENT_MST' "+						
						 " AND Q.GSTR_COLUMN_NAME = 'GNUM_DEPT_CODE' "+						
						 " AND P.GNUM_SEATID = PKG_USERMGMT.FUN_GETSEATID('" + strSeatId+"',A.GNUM_HOSPITAL_CODE) "+						
						// " AND P.GNUM_HOSPITAL_CODE = Q.GNUM_HOSPITAL_CODE "+						
						 //" AND P.GNUM_HOSPITAL_CODE =  '" + strHospCode+"'"+						
						 " AND SUBSTR(GNUM_COLUMN_VALUE,1,3) = A.GNUM_DEPT_CODE )"+					
						 " ORDER BY GSTR_DEPT_NAME ";
		
		strComboQry[1] = " SELECT  TO_CHAR(HGNUM_DEPTUNITCODE) , HGSTR_UNITNAME "+						
						 " FROM HGBT_UNIT_MST A WHERE GNUM_ISVALID = 1 "+						
						 //" AND TRUNC(SYSDATE) BETWEEN GDT_EFFECTIVE_FRM AND TRUNC(NVL(GDT_EFFECTIVE_TO,SYSDATE)) "+						
						 " AND GNUM_HOSPITAL_CODE ='" + strHospCode+"' "+						
						 " AND GNUM_DEPT_CODE =#1# " +						
						 " AND EXISTS "+						
						 " (SELECT 'X' "+						
						 " FROM GBLT_ROLE_SEAT_TABLE_DTL P,GBLT_METATABLE_COLUMN_MST Q "+						
						 " WHERE P.GNUM_METATABLE_ID = Q.GNUM_METATABLE_ID "+
						 "AND  P.GNUM_MODULE_ID=14 " +
						 " AND Q.GSTR_TABLE_NAME = 'HGBT_UNIT_MST' "+						
						 " AND Q.GSTR_COLUMN_NAME = 'HGNUM_DEPTUNITCODE' "+						
						 " AND P.GNUM_SEATID = PKG_USERMGMT.FUN_GETSEATID('" + strSeatId+"',A.GNUM_HOSPITAL_CODE) "+						
						 //" AND P.GNUM_HOSPITAL_CODE = Q.GNUM_HOSPITAL_CODE "+						
						// " AND P.GNUM_HOSPITAL_CODE =  '" + strHospCode+"' "+						
						 " AND GNUM_COLUMN_VALUE = A.HGNUM_DEPTUNITCODE ) "+	
						 " ORDER BY HGSTR_UNITNAME ";
		
		strComboQry[2] = " SELECT distinct HIPNUM_WARD_CODE, "+
						 " Ipd_Mst.getWardName(HIPNUM_WARD_CODE,GNUM_HOSPITAL_CODE ) AS WARD_NAME "+
						 " FROM HIPT_DUWRBED_MST  A	WHERE GNUM_ISVALID = 1 "+						
						 " AND Ipd_Mst.getWardName(HIPNUM_WARD_CODE,GNUM_HOSPITAL_CODE ) not like '/' "+						
						 " AND TRUNC(SYSDATE) >= GDT_EFFECTIVE_FRM "+						
						 " AND GNUM_HOSPITAL_CODE = '" + strHospCode+"' "+						
						 " AND GNUM_DEPT_CODE = #1# "+	
						 " AND HGNUM_DEPTUNITCODE LIKE DECODE( #2# ,'0','%', #2#) "+
						 " AND EXISTS "+						
						 " (SELECT 'X' "+						
						 " FROM GBLT_ROLE_SEAT_TABLE_DTL P,GBLT_METATABLE_COLUMN_MST Q "+						
						 " WHERE P.GNUM_METATABLE_ID = Q.GNUM_METATABLE_ID "+
						 " AND  P.GNUM_MODULE_ID=14 " +
						 " AND Q.GSTR_TABLE_NAME = 'HIPT_WARD_MST' "+						
						 " AND Q.GSTR_COLUMN_NAME = 'HIPNUM_WARD_CODE' "+						
						 " AND P.GNUM_SEATID = PKG_USERMGMT.FUN_GETSEATID('" + strSeatId+"',A.GNUM_HOSPITAL_CODE) "+						
						// " AND P.GNUM_HOSPITAL_CODE = Q.GNUM_HOSPITAL_CODE "+						
						 //" AND P.GNUM_HOSPITAL_CODE =  '" + strHospCode+"' "+						
						 " AND GNUM_COLUMN_VALUE = A.HIPNUM_WARD_CODE  "+ 					
						 " ) ORDER BY WARD_NAME ";

		strComboQry[3] = " SELECT b.hipnum_room_no, b.hipstr_room_desc "
				+ " FROM HIPT_ROOM_CONFIG_MST b "
				+ " WHERE gnum_hospital_code = '" + strHospCode
				+ "' AND gnum_isvalid = 1 " 
				+ " AND EXISTS (SELECT    'x' "
				+ " FROM HIPT_DUWRBED_MST "
				+ " WHERE hipnum_room_no=b.hipnum_room_no "
				+ " AND GNUM_DEPT_CODE = #1# "	
				+ " AND HGNUM_DEPTUNITCODE LIKE DECODE( #2# ,'0','%', #2#) AND "
				+ " HIPNUM_WARD_CODE= #3# AND " + " gnum_isvalid=1) "; 
	
		strComboQry[4] = "2^Non-Accepted#1^Admitted#3^Permit Go Home#4^In Transit";
		//strComboQry[4] = "2^Non-Accepted#1^Admitted#4^In Transit";
		
		/*System.out.println("dept qry"+strComboQry[0]);
		System.out.println("unit qry "+strComboQry[1]);
		System.out.println("ward qry "+strComboQry[2]);
		System.out.println("room qry "+strComboQry[3]);*/
		//System.out.println("unit qry"+strComboQry[1]);
		//System.out.println("ward qry"+strComboQry[2]);
		//System.out.println("room qry"+strComboQry[3]);
		return strComboQry;
	}

	public String getViewQuery() {
		return "";
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
		String files = "../../ipd/js/nursingDesk.js";
		return files;

	}

	public String[] getRowStatus() {
		String[] status = { "1", "1", "red", "Patient is Dead","1", "10", "#C0FBD3", "Patient is Transfered"};
		if(cmbVal != null && cmbVal[4].equals("4"))
		{//intransit
			status[0] = "11";
			status[1] = "9";
			status[2] = "#FFCCFF";
			status[3] = "Inside Hospital";
			status[4] = "17";
			status[5] = "9";
			status[6] = "#7AB6C4";
			status[7] = "Outside Hospital";
		}
		if(cmbVal != null && cmbVal[4].equals("2"))
		{//Non Accepted
			status[0] = "1";
			status[1] = "9";
			status[2] = "red";
			status[3] = "Patient is Dead";
			status[4] = "1";
			status[5] = "10";
			status[6] = "#C0FBD3";
			status[7] = "Patient is Transfered";
		}
		if(cmbVal != null && cmbVal[4].equals("1"))
		{//Admitted
			status[0] = "1";
			status[1] = "9";
			status[2] = "red";
			status[3] = "Patient is Dead";
			status[4] = "1";
			status[5] = "10";
			status[6] = "#C0FBD3";
			status[7] = "Patient is Transfered";
		}
		return status;
	}

	public String getEventState() 
	{
		IpdConfigUtil ipdConfigUtil=new IpdConfigUtil(this.httpSession.getAttribute("HOSPITAL_CODE").toString());
		String str="";
		if(ipdConfigUtil.getStrUnitNameReq().equals("1") && ipdConfigUtil.getStrRoomNoReq().equals("1"))
		{
			 str = "chkUserDefinedFuncAll";
		}
		if(ipdConfigUtil.getStrUnitNameReq().equals("1") && ipdConfigUtil.getStrRoomNoReq().equals("0"))
		{
			 str = "chkUserDefinedFuncUnit";
		}
		if(ipdConfigUtil.getStrUnitNameReq().equals("0") && ipdConfigUtil.getStrRoomNoReq().equals("1"))
		{
			 str = "chkUserDefinedFuncRoom";
		}
		if(ipdConfigUtil.getStrUnitNameReq().equals("0") && ipdConfigUtil.getStrRoomNoReq().equals("0"))
		{
			str = "chkUserDefinedFunction";
		}
		return str;
	}

	public String getButtonConfiguration() {
		return "left";
	}

	public String[] getUserDefinedButtons() {
		//return null;
		String[] strButtons = null; 
	    // strButtons = new String[6];
         strButtons = new String[7];
         
		 strButtons[0] = "Acceptance@buttonClick('ACCEPTANCE')@1@007bb6@glyphicon-bed";
		 strButtons[1] = "Not Reported@confirmNotReport('NOTREPORTED','After admission Patient did not come','After Transfer Patient did not come')@1@32506d@glyphicon-remove-circle";
	  	 strButtons[2] = "Movement@buttonClick('MOVEMENT')@1@bb0000@glyphicon-transfer";
	  	 strButtons[3] = "Cancel@buttonClick('CANCEL');@1@0c8d20@glyphicon-remove";
	  	 strButtons[4] = "Permit To Go Home@buttonClick('LEAVEREQUEST')@1@3b5998@glyphicon-home";
	  	 strButtons[5] = "Join Record@buttonClick('JOINRECORD')@1@0c8d20@glyphicon-plus-sign";
	  	 strButtons[6] = "Not Reporting@confirmNotReport('NOTREPORTING','After leave Patient did not come','After Transfer Patient did not came')@1@007bb6@glyphicon-remove-circle";
	  	 
	  	 
	  //	strButtons[5] = "Place Regular Indent@buttonLogicsOnClick2(6,'PLACEREGULARINDENT','')@1";
		
         return strButtons;
	}

	public String[] getDbButtons() 
	{
		String tempMenuId="";
		String tempDeskID="";
		if(httpSession!=null)
		{
			IpdConfigUtil ipdConfigUtil=new IpdConfigUtil(httpSession.getAttribute("HOSPITAL_CODE").toString());
			
			if(!ipdConfigUtil.getStrBelongingRequired().equals("") && !ipdConfigUtil.getStrIssueItemRequired().equals(""))
			{
				if(!ipdConfigUtil.getStrBelongingRequired().equals("1") && !ipdConfigUtil.getStrIssueItemRequired().equals("1"))
				{//Nothing Required
						tempMenuId="3#27";
				}
				if(ipdConfigUtil.getStrBelongingRequired().equals("1") && !ipdConfigUtil.getStrIssueItemRequired().equals("1"))
				{//Belonging Required--Not In Menu Id=27
							tempMenuId="27";
				}
				if(!ipdConfigUtil.getStrBelongingRequired().equals("1") && ipdConfigUtil.getStrIssueItemRequired().equals("1"))
				{//Issued Items Required--Not In Menu Id=3
							tempMenuId="3";
				}
				if(ipdConfigUtil.getStrBelongingRequired().equals("1") && ipdConfigUtil.getStrIssueItemRequired().equals("1"))
				{//Both Required
							tempMenuId="";
				}
			}
		}
		if(!tempMenuId.equals("") && tempMenuId!=null)
			tempDeskID="2"+"#"+tempMenuId;
		else
			tempDeskID="2";
		String[] str = { tempDeskID };
		return str;
		//return null;
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
		String orderBy[] = { "1", "adm_no"};
		if(cmbVal != null && (cmbVal[4].equals("3") || cmbVal[4].equals("4"))){
			orderBy[0] = "1";
			orderBy[1] = "cr_no";
		}
		if(cmbVal != null && (cmbVal[4].equals("1"))){
			orderBy[0] = "5";
			//orderBy[1] = "substr(bed_no,0,1),bed_name";
			orderBy[1] = "substr(bed_no,0,1)";
			//String temp="to_number(regexp_substr(bed_no,'^[0-9]'))";
			//orderBy[1] = temp;
			
		}
		if(cmbVal != null && (cmbVal[4].equals("2"))){
			orderBy[0] = "1";
			orderBy[1] = "adm_no";
		}
		return orderBy;
	}
	public String getVisibilityMode() {
				return "0";
	}
	public String showHelp()
	{
		if (cmbVal != null && cmbVal[4].equals("3")) 
		{//Leave
			return "<font color='red'>*</font> Mandatory Fields";
		}
		else if (cmbVal != null && cmbVal[4].equals("1")) 
		{//Admitted
			return "<font color='red'>*</font> Mandatory Fields";
		}
		else if (cmbVal != null && cmbVal[4].equals("4")) 
		{//Intransit
			return "<font color='red'>*</font> Mandatory Fields";
		}
		else if (cmbVal != null && cmbVal[4].equals("2")) 
		{//Non Accepted
			return "<font color='red'>*</font> Mandatory Fields";
		}
		else
		{
			return "<font color='red'>*</font> Mandatory Fields";
		}
			
	}
	
	/*public String[] getExtraInformationName()
	{
		String extraInfoName[] = {"Total Admitted Patient", "<b>Total Non Accepted Patient</b>"};
		if(cmbVal != null)
		{
			if(cmbVal[4].equals("1"))//Admitted
			{
				extraInfoName[0] = "<b>Total Admitted Patient</b>";
				extraInfoName[1] = "Total Non Accepted Patient";
			}
			if(cmbVal[4].equals("2"))//Accepted
			{
				extraInfoName[0] = "Total Admitted Patient</b>";
				extraInfoName[1] = "<b>Total Non Accepted Patient</b>";
			}
		}
		return extraInfoName;
	}
	public String[] getExtraInformationQuery()
	{
		String extraInfoQuery[] = new String[2];
		String strSeatId = (String)httpSession.getAttribute("SEATID");
		StringBuffer sBuffer=new StringBuffer(1500);
		String strHospCode = (String) httpSession.getAttribute("HOSPITAL_CODE");
		if(cmbVal != null)
		{
		sBuffer.append(" SELECT COUNT(DISTINCT(B.HIPNUM_ADMNO)) FROM HIPT_PATADMISSION_DTL B "+
				   " WHERE B.GNUM_ISVALID = 1 AND B.HIPNUM_ISACCEPTED =1 AND "+
				   " B.HIPDT_ADMSTATUS_CODE =12 AND B.HIPDT_DISDATETIME IS NULL "+
				   " AND B.GNUM_HOSPITAL_CODE ="+strHospCode );
		
		if(cmbVal[0]!=null || !cmbVal[0].equals("") || !cmbVal[0].equals("0"))
			{
				if(!cmbVal[0].equals("0"))
				{
					sBuffer.append(" AND B.GNUM_DEPT_CODE=");
					sBuffer.append(cmbVal[0]);
				}
		}
		if(cmbVal[1]!=null || !cmbVal[1].equals("") || !cmbVal[1].equals("0"))
		{
			if(!cmbVal[1].equals("0"))
			{
				sBuffer.append(" AND B.GNUM_DEPTUNIT_CODE = ");
				sBuffer.append(cmbVal[1]);
			}
		}
		if(cmbVal[2]!=null || !cmbVal[2].equals("") || !cmbVal[2].equals("0"))
		{
			if(!cmbVal[2].equals("0"))
			{
				sBuffer.append(" AND B.HIPNUM_WARD_CODE = ");
				sBuffer.append(cmbVal[2]);
			}
		}
		if(cmbVal[3]!=null || !cmbVal[3].equals("") || !cmbVal[3].equals("0"))
		{
			if(!cmbVal[3].equals("0"))
			{
				sBuffer.append(" AND B.HIPNUM_ROOMNO = ");				
				sBuffer.append(cmbVal[3]);
			}
		}
		}
		else
		{
			sBuffer.append(" SELECT COUNT(DISTINCT(B.HIPNUM_ADMNO)) FROM HIPT_PATADMISSION_DTL B "+
					   " WHERE B.GNUM_ISVALID = 1 AND B.HIPNUM_ISACCEPTED =1 AND "+
					   " B.HIPDT_ADMSTATUS_CODE =12 AND B.HIPDT_DISDATETIME IS NULL "+
					   " AND B.GNUM_HOSPITAL_CODE ="+strHospCode+" AND GNUM_DEPT_CODE=0 " +
					   " AND B.GNUM_DEPTUNIT_CODE =0 AND B.HIPNUM_WARD_CODE =0 AND B.HIPNUM_ROOMNO =0 ");		
		}
		extraInfoQuery[0]=sBuffer.toString();
		
		sBuffer.delete(0, sBuffer.length());
		
		if(cmbVal != null)
		{
			sBuffer.append(" SELECT COUNT(DISTINCT(HIPNUM_ADMNO)) FROM HIPT_PAT_MOV_DTL B ");
			sBuffer.append(" WHERE B.GNUM_ISVALID=1 AND  GNUM_HOSPITAL_CODE = "+strHospCode);
			sBuffer.append(" AND (SELECT TO_CHAR (AHIS_GBL_UTIL.NUM_DEFAULT (HIPNUM_TRANSOUT_FLG,'-1'))");
			sBuffer.append(" FROM DUAL) = '-1' AND HIPDT_TRANSIN_DATETIME IS NULL ");
			sBuffer.append(" AND HIPNUM_TRANSIN_FLG IN (0, 2, 5, 8) AND HIPNUM_ADMNO IN ( ");
			sBuffer.append(" SELECT HIPNUM_ADMNO FROM HIPT_PATADMDISC_DTL WHERE GNUM_ISVALID = 1 ");
			sBuffer.append(" AND GNUM_HOSPITAL_CODE = B.GNUM_HOSPITAL_CODE AND B.GNUM_ISVALID=1 AND hipdt_admstatus_code IN (11, 12, 17))");
			   
		
		
		if(cmbVal[0]!=null || !cmbVal[0].equals("") || !cmbVal[0].equals("0"))
			{
				if(!cmbVal[0].equals("0"))
				{
					sBuffer.append(" AND B.GNUM_DEPT_CODE=");
					sBuffer.append(cmbVal[0]);
				}
		}
		if(cmbVal[1]!=null || !cmbVal[1].equals("") || !cmbVal[1].equals("0"))
		{
			if(!cmbVal[1].equals("0"))
			{
				sBuffer.append(" AND B.GNUM_DEPTUNIT_CODE = ");
				sBuffer.append(cmbVal[1]);
			}
		}
		if(cmbVal[2]!=null || !cmbVal[2].equals("") || !cmbVal[2].equals("0"))
		{
			if(!cmbVal[2].equals("0"))
			{
				sBuffer.append(" AND B.HIPNUM_WARDCODE = ");
				sBuffer.append(cmbVal[2]);
			}
		}
		if(cmbVal[3]!=null || !cmbVal[3].equals("") || !cmbVal[3].equals("0"))
		{
			if(!cmbVal[3].equals("0"))
			{
				sBuffer.append(" AND B.HIPNUM_ROOMNO = ");				
				sBuffer.append(cmbVal[3]);
			}
		}
		
	
	/*
		sBuffer.append(" AND( gnum_deptunit_code IN (  SELECT TO_CHAR(HGNUM_DEPTUNITCODE) FROM HGBT_UNIT_MST WHERE GNUM_ISVALID=1");
		sBuffer.append(" AND gnum_hospital_code = '"+strHospCode+"' AND HGNUM_DEPTUNITCODE IN (SELECT GNUM_COLUMN_VALUE ");
		sBuffer.append(" FROM GBLT_ROLE_SEAT_TABLE_DTL A, GBLT_METATABLE_COLUMN_MST B  WHERE A.GNUM_METATABLE_ID = B.GNUM_METATABLE_ID AND A.GNUM_MODULE_ID=14");
		sBuffer.append("  AND B.GSTR_TABLE_NAME = 'HGBT_UNIT_MST' AND GSTR_COLUMN_NAME = 'HGNUM_DEPTUNITCODE' ");
		sBuffer.append(" AND A.GNUM_SEATID = pkg_usermgmt.fun_getseatid('"+strSeatId+"',b.gnum_hospital_code ) AND ");
		sBuffer.append("a.gnum_hospital_code = b.gnum_hospital_code AND A.GNUM_HOSPITAL_CODE= "+strHospCode);
        sBuffer.append(")) OR gnum_deptunit_code='0') ");
        
        sBuffer.append(" AND( hipnum_wardcode IN (  SELECT TO_CHAR(HIPNUM_WARD_CODE) FROM HIPT_WARD_MST WHERE GNUM_ISVALID=1");
		sBuffer.append(" AND gnum_hospital_code = '"+strHospCode+"' AND HIPNUM_WARD_CODE IN (SELECT GNUM_COLUMN_VALUE ");
		sBuffer.append(" FROM GBLT_ROLE_SEAT_TABLE_DTL A, GBLT_METATABLE_COLUMN_MST B  WHERE A.GNUM_METATABLE_ID = B.GNUM_METATABLE_ID AND A.GNUM_MODULE_ID=14");
		sBuffer.append("  AND B.GSTR_TABLE_NAME = 'HIPT_WARD_MST' AND GSTR_COLUMN_NAME = 'HIPNUM_WARD_CODE' ");
		sBuffer.append(" AND A.GNUM_SEATID = pkg_usermgmt.fun_getseatid('"+strSeatId+"',b.gnum_hospital_code ) AND ");
		sBuffer.append("a.gnum_hospital_code = b.gnum_hospital_code AND A.GNUM_HOSPITAL_CODE= "+strHospCode);
        sBuffer.append("))) ");
		}
		else
		{
			sBuffer.append(" SELECT COUNT(DISTINCT(B.HIPNUM_ADMNO)) FROM HIPT_PATADMISSION_DTL B "+
					   " WHERE B.GNUM_ISVALID = 1 AND B.HIPNUM_ISACCEPTED =0 "+
					   " AND B.HIPDT_DISDATETIME IS NULL AND B.HIPDT_ADMSTATUS_CODE NOT IN (13,14)"+
					   " AND B.GNUM_HOSPITAL_CODE ="+strHospCode+" AND GNUM_DEPT_CODE=0 " +
					   " AND B.GNUM_DEPTUNIT_CODE =0 AND B.HIPNUM_WARD_CODE =0 AND B.HIPNUM_ROOMNO =0 ");	
		}
		extraInfoQuery[1]=sBuffer.toString();
		sBuffer.delete(0, sBuffer.length());
		System.out.println("extra Info query0"+extraInfoQuery[0]);
		System.out.println("extra Info query1"+extraInfoQuery[1]);
		return extraInfoQuery;
	}*/
	public String[] getButtonIcons() 
	{
		/*if (cmbVal != null && cmbVal[4].equals("3")) 
		{//Leave
			String[] str={"icon-default.png","icon-default.png"};
			return str;
		}
		else if (cmbVal != null && cmbVal[4].equals("1")) 
		{//Admitted
			String[] str={"icon-default.png"};
			return str;
		}
		else if (cmbVal != null && cmbVal[4].equals("4")) 
		{//Intransit
			String[] str={"CancelOnDesk.png"};
			return str;
		}
		else if (cmbVal != null && cmbVal[4].equals("2")) 
		{//Non Accepted
			String[] str={"AcceptanceOnDesk.png","icon-default.png"};
			return str;
		}
		else
		{*/
			//String[] str={"MovementOnDesk.png","AcceptanceOnDesk.png","NotReportedOnDesk.png","NotReportedOnDesk.png","CancelOnDesk.png"};
		String[] str={"Leave_RequestOnDesk.png","MovementOnDesk.png","NotReportedOnDesk.png","AcceptanceOnDesk.png","NotReportedOnDesk.png","icon-patlist.png","CancelOnDesk.png"};
			return str;
		//}
		//return str;
	//}
}
}