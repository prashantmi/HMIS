package ipd.transactions;

import java.util.ResourceBundle;

import hisglobal.transactionmgnt.HisDAO;
import javax.sql.rowset.WebRowSet;

public class DeathNotificationTransDAO {

	/**
	 * This function is used to set Department name on main page
	 * 
	 * @param vo
	 */
	public static void setPatAdmCodeDtl(DeathNotificationTransVO vo) {

		String strProcName = "{call pkg_ipd_view.Proc_Pat_Admstatus_Code_Dtl(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;                           

		String strCrNum = vo.getStrCrNo();
		try {
			daoObj = new HisDAO("Death Notification","DeathNotificationTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modval", "1");
			daoObj.setProcInValue(nProcIndex, "hrgnum_puk", strCrNum);
			daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", vo
					.getStrHospCode());
			daoObj.setProcInValue(nProcIndex, "hipnum_admno", "");
			daoObj.setProcInValue(nProcIndex, "issuereq", "");
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (ws.size() == 0) {
				vo.setStrInvalidCrNo("1");
			}
			if (strErr.equals("")) {

				while (ws.next()) {
					vo.setStrPatAdmCode(ws.getString(1));
					vo.setStrDead(ws.getString(2));
				}
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			vo
					.setStrMsgString("DeathNotificationTransDAO.setPatAdmCodeDtl() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	public static void setPatAdmDtl(DeathNotificationTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_hipt_patadmission_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		String temp = "";
		String temp1[] = null;
		int nProcIndex = 0;

		String strErr = "";
		try {
			daoObj = new HisDAO("Death Notification Trans","DeathNotificationTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			// if (deptUnitCode != null) {

			daoObj.setProcInValue(nProcIndex, "modeval", "1");
			daoObj.setProcInValue(nProcIndex, "puk", voObj.getStrCrNo());
			
			daoObj.setProcInValue(nProcIndex, "seatid", "");
			daoObj.setProcInValue(nProcIndex, "modifytime", "");
			daoObj.setProcInValue(nProcIndex, "patlisttype", "");
			daoObj.setProcInValue(nProcIndex, "searchstr", "");
			daoObj.setProcInValue(nProcIndex, "searchtype", "");
			daoObj.setProcInValue(nProcIndex, "torows", "");
			daoObj.setProcInValue(nProcIndex, "frmrows", "");
			daoObj.setProcInValue(nProcIndex, "onlinedis", "");			
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode());		      
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {
				if (ws.next()) {
					temp = ws.getString(9);
				}
				temp1 = temp.replace("^", "#").split("#");
				voObj.setStrAdmno(temp1[0]);
				voObj.setStrEpisodeCode(temp1[1]);
				voObj.setStrVistNo(temp1[2]);
				voObj.setStrAdmDate(temp1[3]);
				voObj.setStrDeptCode(temp1[5].substring(0, 3));
				voObj.setStrDeptUnitCode(temp1[5]);
				voObj.setStrWardCode(temp1[6]);
				voObj.setStrRoomeCode(temp1[7]);
				voObj.setStrBedCode(temp1[8]);
				voObj.setStrIsNewBorn(temp1[10]);
				voObj.setStrMlcNo(temp1[12]);

			} else {
				throw new Exception(strErr);
			}
			// }
		} catch (Exception e) {

			voObj
					.setStrMsgString("DeathNotificationTransDAO.setPatAdmDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * 
	 * @param voObj
	 */
	public static void setConsultantNameDtl(DeathNotificationTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		try {
			daoObj = new HisDAO("Death Notification Trans",
					"DeathNotificationTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			// if (deptUnitCode != null) {
			daoObj.setProcInValue(nProcIndex, "modval", "1");
			daoObj.setProcInValue(nProcIndex, "deptunitcode", voObj
					.getStrDeptUnitCode());
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
					.getStrHospCode());
			daoObj.setProcInValue(nProcIndex, "seatid", "");
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {
				voObj.setConsultantWS(ws);

			} else {
				throw new Exception(strErr);
			}
			// }
		} catch (Exception e) {

			voObj
					.setStrMsgString("DeathNotificationTransDAO.setConsultantNameDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	public static void setDeathMannerDtl(DeathNotificationTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_simple_view.proc_death_manner_view(?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		try {
			daoObj = new HisDAO("Death Notification Trans","DeathNotificationTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			// if (deptUnitCode != null) {

			// daoObj.setProcInValue(nProcIndex, "deptunitcode", "10411");
			
			
			daoObj.setProcInValue(nProcIndex, "deathmanner_code", "");
			daoObj.setProcInValue(nProcIndex, "deathmanner_desc", "");
			daoObj.setProcInValue(nProcIndex, "effective_frm", "");
			daoObj.setProcInValue(nProcIndex, "lstmod_dt", "");
			daoObj.setProcInValue(nProcIndex, "remarks", "");
			daoObj.setProcInValue(nProcIndex, "entry_dt", "");
			daoObj.setProcInValue(nProcIndex, "isvalid", "");
			daoObj.setProcInValue(nProcIndex, "seatid", "");
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			
		    daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {
				voObj.setDeathMannerWS(ws);
			} else {
				throw new Exception(strErr);
			}
			// }
		} catch (Exception e) {

			//	
			voObj
					.setStrMsgString("DeathNotificationTransDAO.setDeathMannerDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	public static void setDeathCauseDtl(DeathNotificationTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.proc_hospital_disease_mst(?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		try {
			daoObj = new HisDAO("Death Notification Trans","DeathNotificationTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
					.getStrHospCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {
				voObj.setDeathCauseWS(ws);
			} else {
				throw new Exception(strErr);
			}
			// }
		} catch (Exception e) {

			voObj
					.setStrMsgString("DeathNotificationTransDAO.setDeathCauseDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	public static void insert(DeathNotificationTransVO vo) {
		HisDAO daoObj = null;
		String strProcName = "{call pkg_ipd_dml.proc_pat_deathnotif_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			daoObj = new HisDAO("Death Notification Trans",	"DeathNotificationTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hrgnum_puk", vo.getStrCrNo());
			daoObj.setProcInValue(nProcIndex, "hipnum_admno", vo.getStrAdmno());
			daoObj.setProcInValue(nProcIndex, "hrgnum_episode_code", vo.getStrEpisodeCode());
			daoObj.setProcInValue(nProcIndex, "hrgnum_visit_no", vo.getStrVistNo());
			daoObj.setProcInValue(nProcIndex, "hipdt_admdatetime", vo.getStrAdmDate());
			daoObj.setProcInValue(nProcIndex, "gnum_dept_code", vo.getStrDeptCode());
			daoObj.setProcInValue(nProcIndex, "gnum_deptunit_code", vo.getStrDeptUnitCode());
			daoObj.setProcInValue(nProcIndex, "hipnum_ward_code", vo.getStrWardCode());
			daoObj.setProcInValue(nProcIndex, "hipnum_room_code", vo.getStrRoomeCode());
			daoObj.setProcInValue(nProcIndex, "hipnum_bed_code", vo.getStrBedCode());
			daoObj.setProcInValue(nProcIndex, "hipnum_isnewborn", vo.getStrIsNewBorn());
			daoObj.setProcInValue(nProcIndex, "hrgnum_mlc_no", vo.getStrMlcNo());
			daoObj.setProcInValue(nProcIndex, "hepdt_death_datetime", vo.getStrDeathDateTime());
			daoObj.setProcInValue(nProcIndex, "hepdt_death_onset_datetime", vo.getStrOnsetDeathDateTime());
			daoObj.setProcInValue(nProcIndex, "hipnum_death_cause_code_im", vo.getStrCauseDeath());
			daoObj.setProcInValue(nProcIndex, "hipnum_death_manner_code", vo.getStrDeathManner());
			daoObj.setProcInValue(nProcIndex, "hepstr_death_cause_an ", vo.getStrAntecedentCauseDeath());
			daoObj.setProcInValue(nProcIndex, "hepstr_death_cause_im  ", vo.getStrDescpCauseDeath());
			daoObj.setProcInValue(nProcIndex, "hepstr_injury_dtl", vo.getStrInjuryDetail());
			daoObj.setProcInValue(nProcIndex, "hepnum_ispregnent", vo.getStrIsPregnant());
			daoObj.setProcInValue(nProcIndex, "hepnum_isdelivery", "0");
			daoObj.setProcInValue(nProcIndex, "hepnum_consultant_id", vo.getStrConsultant());
			daoObj.setProcInValue(nProcIndex, "gnum_seatid	", vo.getStrSeatId());
			daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", vo.getStrHospCode());
			daoObj.setProcInValue(nProcIndex, "hgnum_bed_status_code", vo.getStrBedStatusVacantCode());
			daoObj.setProcInValue(nProcIndex, "hepdt_verify_datetime	", vo.getStrVerifyDateTime());
			daoObj.setProcInValue(nProcIndex, "hgnum_pat_status_code", vo.getStrDeathCode());
			daoObj.setProcInValue(nProcIndex, "hepdt_bodyshift_datetime	", vo.getStrShiftDateTime());
			daoObj.setProcInValue(nProcIndex, "hepstr_body_handover_to", vo.getStrHandOverTo());
			daoObj.setProcInValue(nProcIndex, "hepdt_body_handover_datetime",vo.getStrHandOverDateTime());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, " dml_count", 1);
			
			daoObj.setProcInValue(nProcIndex, "modval","1");
			daoObj.setProcInValue(nProcIndex, "hrgdt_visit_date","");
			daoObj.setProcInValue(nProcIndex, "gdt_entry_date","");
			daoObj.setProcInValue(nProcIndex, "gnum_isvalid","");
			daoObj.setProcInValue(nProcIndex, "gnum_nurse_seatid","");
			daoObj.setProcInValue(nProcIndex, "hepnum_is_shift_mortury","");
			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			
			if(strErr == null) strErr = "";
			
			if(!strErr.equals("")){
				
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			vo.setStrMsgString("DeathNotificationTransDAO.insert() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	public static void updateWardRoomBedMst(DeathNotificationTransVO vo) {
		HisDAO daoObj = null;
		String strProcName = "{call pkg_ipd_dml.proc_hipt_ward_room_bed_mst(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try {
			daoObj = new HisDAO("Death Notification Trans","DeathNotificationTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "3");
			daoObj.setProcInValue(nProcIndex, "hgnum_ward_code", vo.getStrWardCode());
			daoObj.setProcInValue(nProcIndex, "hipnum_room_no", vo.getStrRoomeCode());
			daoObj.setProcInValue(nProcIndex, "hgnum_bed_code", vo.getStrBedCode());
			daoObj.setProcInValue(nProcIndex, "hgnum_bed_status_code", "10");
			daoObj.setProcInValue(nProcIndex, "hrgnum_puk", vo.getStrCrNo());
			daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", vo.getStrHospCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "dml_count", 1);
			
			daoObj.setProcInValue(nProcIndex, "hgnum_bed_type_code", "");
			daoObj.setProcInValue(nProcIndex, "hgdt_probable_vacant_dt", "");
			daoObj.setProcInValue(nProcIndex, "gdt_effective_frm", "");
			daoObj.setProcInValue(nProcIndex, "gdt_lstmod_date", "");
			daoObj.setProcInValue(nProcIndex, "gnum_lstmod_seatid", "");
			daoObj.setProcInValue(nProcIndex, "gstr_remarks", "");
			daoObj.setProcInValue(nProcIndex, "gdt_entry_date", "");
			daoObj.setProcInValue(nProcIndex, "gnum_seatid", "");
			daoObj.setProcInValue(nProcIndex, "gnum_isvalid", "");
			daoObj.setProcInValue(nProcIndex, "hgnum_bed_name", "");
			daoObj.setProcInValue(nProcIndex, "hipnum_booking_flg", "");
			daoObj.setProcInValue(nProcIndex, "block_bed_expiry", "");

			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");

			if(strErr == null) strErr = "";
			
			if(!strErr.equals("")){
				
				throw new Exception(strErr);
			}
			
			
		} catch (Exception e) {

			vo
					.setStrMsgString("DeathNotificationTransDAO.updateWardRoomBedMst() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	public static void isPregnant(DeathNotificationTransVO vo) {
		String strProcName = "{call pkg_ipd_view.PROC_GENDER_CODE(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		try {
			String strGenderCode = "";
			ResourceBundle mstRes = ResourceBundle
					.getBundle("ipd.hisIpdProperties");
			strGenderCode = mstRes.getString("Gender_Code_Female");
			// System.out.println("DAO "+vo.getStrHospCode());
			// System.out.println("DAO "+vo.getStrCrNo());
			daoObj = new HisDAO("Patient Discharge", "PatientFinalDischargeDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode());
			daoObj.setProcInValue(nProcIndex, "cr_no", vo.getStrCrNo());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("") && ws.next()) {
				vo.setFFemale(ws.getString(1).equals(strGenderCode) ? true
						: false);
				// System.out.println("DAO "+vo.isFFemale());
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			vo.setStrMsgString("PatientFinalDischargeDAO.isPregnant() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

}
