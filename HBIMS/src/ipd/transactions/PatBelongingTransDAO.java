package ipd.transactions;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

public class PatBelongingTransDAO {

	/**
	 * This function is used to set Department name on main page
	 * 
	 * @param vo
	 */
	public static void setPatAdmCodeDtl(PatBelongingTransVO vo) {
		String strProcName = "{call pkg_ipd_view.Proc_Pat_Admstatus_Code_Dtl(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try {
			daoObj = new HisDAO("Patient Belonging", "PatBelongingTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "1");
			daoObj.setProcInValue(nProcIndex, "hrgnum_puk", strCrNum);
			daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", vo.getStrHospitalCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			
			daoObj.setProcInValue(nProcIndex, "hipnum_admno", "");
			daoObj.setProcInValue(nProcIndex, "issuereq", "2");

			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(ws.size()==0)
			{
				vo.setStrInvalidCrNo("1");
			}
			if (strErr.equals("")) {

				while(ws.next()) {

					vo.setStrPatAdmCode(ws.getString(1));
					vo.setStrIsDead(ws.getString(2));
					
				}

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			vo.setStrMsgString("PatBelongingTransDAO.setPatAdmCodeDtl() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	
	
	/**
	 * This function is used to set Department name on main page
	 * 
	 * @param vo
	 */
	public static void setPatientblngName(PatBelongingTransVO vo) {
		String strProcName = "{call pkg_ipd_view.proc_visitadmission_advice_dtl(?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try {
			daoObj = new HisDAO("Patient Belonging", "PatBelongingTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "puk", strCrNum);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			
			daoObj.setProcInValue(nProcIndex, "modval", "1");
			daoObj.setProcInValue(nProcIndex, "admNo", "");

			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");			

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {

				if (ws.next()) {

					vo.setStrAdmnNo(ws.getString(1));
					vo.setStrAdmnDate(ws.getString(2));
					vo.setStrRoomBed(ws.getString(8));
					vo.setStrWard(ws.getString(9));
				}

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			vo.setStrMsgString("PatBelongingTransDAO.setPatientblngName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	/**
	 * This method is used to set Patient Belonging name 
	 * 
	 * @param vo
	 */
	public static void setPatBelongingName(PatBelongingTransVO vo) {

		String strProcName = "{call Pkg_Ipd_View.Proc_patBelonging_Dtl(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		try {
			daoObj = new HisDAO("Patient Belonging", "PatBelongingTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "admNo", vo.getStrAdmnNo());
			daoObj.setProcInValue(nProcIndex, "dept_unit", vo.getStrDeptUnitCode());
			daoObj.setProcInValue(nProcIndex, "Ward", vo.getStrWardCode());
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			
			//daoObj.setProcInValue(nProcIndex, "mode_val", "1");
			
			if(vo.getStrBelMode().equals("1"))
			{
				daoObj.setProcInValue(nProcIndex, "mode_val", "4");
				daoObj.setProcInValue(nProcIndex, "item_type", "2");
			}
			else if(vo.getStrBelMode().equals("2"))
			{
				daoObj.setProcInValue(nProcIndex, "mode_val", "4");
				daoObj.setProcInValue(nProcIndex, "item_type", "1");
			}
			else
			{
				daoObj.setProcInValue(nProcIndex, "mode_val", "1");
				daoObj.setProcInValue(nProcIndex, "item_type", "");
			}
			
			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				vo.setStrPatBelonging(ws);
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("PatBelongingTransDAO.setPatBelongingName() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	/**
	 * This method is used to set Patient Belonging Detail 
	 * 
	 * @param vo
	 */
	public static void setPatBelongingRow(PatBelongingTransVO vo) {

		String strProcName = "{call Pkg_Ipd_View.Proc_patBelonging_Dtl(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		try {
			daoObj = new HisDAO("Patient Belonging", "PatBelongingTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "admNo", vo.getStrAdmnNo());
			daoObj.setProcInValue(nProcIndex, "dept_unit", vo.getStrDeptUnitCode());
			daoObj.setProcInValue(nProcIndex, "Ward", vo.getStrWardCode());
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			
			daoObj.setProcInValue(nProcIndex, "mode_val", "4");
			daoObj.setProcInValue(nProcIndex, "item_type", "2");//Belonging
		      
			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				vo.setStrPatBelonging(ws);
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("PatBelongingTransDAO.setPatBelongingName() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	/**
	 * This method is used to set Patient Belonging name 
	 * 
	 * @param vo
	 */
	public static void setPatIssuedItemsRow(PatBelongingTransVO vo) {

		String strProcName = "{call Pkg_Ipd_View.Proc_patBelonging_Dtl(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		try {
			daoObj = new HisDAO("Patient Belonging", "PatBelongingTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "admNo", vo.getStrAdmnNo());
			daoObj.setProcInValue(nProcIndex, "dept_unit", vo.getStrDeptUnitCode());
			daoObj.setProcInValue(nProcIndex, "Ward", vo.getStrWardCode());
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			
			daoObj.setProcInValue(nProcIndex, "mode_val", "4");
			daoObj.setProcInValue(nProcIndex, "item_type", "1");//Issued Items
		      
			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				vo.setPatIssuedItemWS(ws);
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("PatBelongingTransDAO.setPatBelongingName() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * This method is used to set Admission Details
	 * 
	 * @param vo
	 */
	public static void setAdmissionDtl(PatBelongingTransVO voObj) {

		String strProcName = "{call pkg_ipd_view.proc_visitadmission_advice_dtl(?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNo = voObj.getStrCrNo();
		try {
			daoObj = new HisDAO("Patient Belonging", "PatBelongingTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "puk", strCrNo);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			
			daoObj.setProcInValue(nProcIndex, "modval", "1");
			daoObj.setProcInValue(nProcIndex, "admNo", "");
			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (ws.next()) {
				voObj.setStrAdmnNo(ws.getString(1));
				voObj.setStrAdmnDate(ws.getString(2));
				voObj.setStrWardCode(ws.getString(3));
				voObj.setStrDeptUnitCode(ws.getString(7));
			}
			if (strErr.equals("")) {
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			voObj.setStrMsgString("DAOIpd.setAdmissionDtl() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * This method is used to insert the Patient Belonging Details
	 * 
	 * @param vo
	 */
	public static boolean insertpatbelongdtl(PatBelongingTransVO vo) {
		boolean fretValue = false;
		String strProcName1 = "{call pkg_ipd_dml.proc_hipt_pat_belonging_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		String err = "";

		String[] itemname = vo.getStrItemName();
		String[] itemqnty = vo.getStrItemQuantity();
		String[] remarks = vo.getStrRemarks();

		int nProcIndex = 0;
		HisDAO daoObj = null;

		try {
			daoObj = new HisDAO("Patient Belonging", "PatBelongingTransDAO");
			if (vo.getStrValue().equals("0")) {
				for (int i = 0; i < itemname.length; i++) {
					nProcIndex = daoObj.setProcedure(strProcName1);
					
					daoObj.setProcInValue(nProcIndex, "modval", "1");
					daoObj.setProcInValue(nProcIndex, "hipnum_admno", vo.getStrAdmnNo());
					daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", vo.getStrHospitalCode());
					daoObj.setProcInValue(nProcIndex, "hrgnum_puk", vo.getStrCrNo());
					daoObj.setProcInValue(nProcIndex, "hgnum_deptunitcode", vo.getStrDeptUnitCode());
					daoObj.setProcInValue(nProcIndex, "hipnum_ward_code", vo.getStrWardCode());
					daoObj.setProcInValue(nProcIndex, "hipstr_belong_desc",itemname[i]);
					daoObj.setProcInValue(nProcIndex, "hipstr_belong_qty",itemqnty[i]);
					daoObj.setProcInValue(nProcIndex, "hipstr_benlog_remark",remarks[i]);
					daoObj.setProcInValue(nProcIndex, "item_id",vo.getStrItemId()[i]);
					daoObj.setProcInValue(nProcIndex, "item_type",vo.getStrItemType()[i]);
					daoObj.setProcInValue(nProcIndex, "gnum_seatid", vo.getStrSeatId());
					daoObj.setProcInValue(nProcIndex, "gnum_isvalid", "1");
					
					daoObj.setProcInValue(nProcIndex, "to_deptunitcode", "");
					daoObj.setProcInValue(nProcIndex, "to_ward_code", "");
					HisUtil util =new HisUtil("ADT","insertpatbelongdtl");
					daoObj.setProcInValue(nProcIndex, "gdt_entry_date", util.getASDate("dd/MM/yyyy"));
					daoObj.setProcInValue(nProcIndex, "hipdt_return_datetime", "");
					daoObj.setProcInValue(nProcIndex, "hipstr_return_to", "");
					daoObj.setProcInValue(nProcIndex, "hipnum_belong_slno", "");
					daoObj.setProcInValue(nProcIndex, "return_rmks", "");
					daoObj.setProcInValue(nProcIndex, "gnum_relation_code", "0");
					daoObj.setProcInValue(nProcIndex, "status", "");

				      
					daoObj.setProcOutValue(nProcIndex, "err", 1);
					daoObj.setProcOutValue(nProcIndex, "result", 1);
					daoObj.setProcOutValue(nProcIndex, "dml_count", 1);
					daoObj.execute(nProcIndex, 1);
				}
			}
			synchronized (daoObj) {
				daoObj.fire();
				fretValue = true;
			}
			if (err.equals("")) {
				fretValue = true;
				vo.setStrMsgType("0");
			} else {
				throw new Exception(err);
			}
		} catch (Exception e) {
			fretValue = false;
			vo.setStrMsgString("PatBelongingTransDAO.insertpatbelongdtl() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
		return fretValue;
	}

	/**
	 * This method is used to update the Patient Belonging Details
	 * 
	 * @param vo
	 */
	public static boolean updatepatbelongdtl(PatBelongingTransVO vo) {


		boolean fretValue = false;
		String strProcName1 = "{call pkg_ipd_dml.proc_hipt_pat_belonging_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		String err = "";

		String[] itemname = vo.getStrItemNameU();
		String[] itemreturndate = vo.getStrItemReturnDateU();
		String[] itemreturnto = vo.getStrItemReturnToU();
		String[] strChk = vo.getStrchkvisit();
		String[] slno = vo.getStrslno();
		int nProcIndex = 0;
		HisDAO daoObj = null;

		try {
			daoObj = new HisDAO("Patient Belonging", "PatBelongingTransDAO");
			if (vo.getStrValue().equals("1")) {

				for (int i = 0; i < strChk.length; i++) {
					nProcIndex = daoObj.setProcedure(strProcName1);
					daoObj.setProcInValue(nProcIndex, "modval", "2");
					daoObj.setProcInValue(nProcIndex, "hipnum_admno", vo.getStrAdmnNo());
					daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", vo.getStrHospitalCode());
					daoObj.setProcInValue(nProcIndex, "hrgnum_puk", vo.getStrCrNo());
					daoObj.setProcInValue(nProcIndex, "hgnum_deptunitcode", vo.getStrDeptUnitCode());
					daoObj.setProcInValue(nProcIndex, "hipnum_ward_code", vo.getStrWardCode());
					daoObj.setProcInValue(nProcIndex, "hipstr_belong_desc",itemname[i]);
					daoObj.setProcInValue(nProcIndex, "hipnum_belong_slno",	slno[i]);
					daoObj.setProcInValue(nProcIndex, "hipdt_return_datetime",itemreturndate[i]);
					daoObj.setProcInValue(nProcIndex, "hipstr_return_to",itemreturnto[i]);
					daoObj.setProcInValue(nProcIndex, "gnum_seatid", vo.getStrSeatId());
					daoObj.setProcInValue(nProcIndex, "gnum_isvalid", "1");
					daoObj.setProcInValue(nProcIndex, "return_rmks", vo.getStrReturnRmks());
					daoObj.setProcInValue(nProcIndex, "gnum_relation_code", vo.getStrRelation()[i]);
					daoObj.setProcOutValue(nProcIndex, "err", 1);
					daoObj.setProcOutValue(nProcIndex, "RESULT", 1);
					daoObj.setProcOutValue(nProcIndex, "dml_count", 1);
					
					daoObj.setProcInValue(nProcIndex, "hipstr_belong_qty","");
					daoObj.setProcInValue(nProcIndex, "hipstr_benlog_remark","");
					daoObj.setProcInValue(nProcIndex, "item_id",vo.getStrItemId()[i]);
					daoObj.setProcInValue(nProcIndex, "item_type",vo.getStrItemType()[i]);					
					daoObj.setProcInValue(nProcIndex, "to_deptunitcode", "");
					daoObj.setProcInValue(nProcIndex, "to_ward_code", "");
					HisUtil util =new HisUtil("ADT","insertpatbelongdtl");
					daoObj.setProcInValue(nProcIndex, "gdt_entry_date", util.getASDate("DD/MMM/YYYY"));
					daoObj.setProcInValue(nProcIndex, "status", "");				      
					
					
					daoObj.execute(nProcIndex, 1);
					}
			}
			synchronized (daoObj) {
				
				daoObj.fire();
				fretValue = true;
			}
			if (err.equals("")) {
				fretValue = true;
				vo.setStrMsgType("0");
			} else {
				throw new Exception(err);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			fretValue = false;
			vo.setStrMsgString("PatBelongingTransDAO.updatepatbelongdtl() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
		return fretValue;
	}
	/**
	 * sets Relation List in IpdVO objects relationWs attribute.
	 * 
	 *  @param voObj - IpdVO Value Object 
	 */
	public static void setRelationList(PatBelongingTransVO voObj){
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_gblt_relation_list(?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {
			daoObj = new HisDAO("Patient Belonging","PatBelongingTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code","");
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");			
			strErr = daoObj.getString(nProcIndex, "err");			
			if (strErr == null)
				strErr = "";

	
			if (strErr.equals("")) {
					voObj.setRelationWS(ws);				
			} else {				
				throw new Exception(strErr);
				}
			
		} catch (Exception e) {
			voObj
					.setStrMsgString("PatBelongingTransDAO.getRelationList() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}		
	}
}