package ipd.transactions;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import ipd.IpdConfigUtil;

import java.util.ResourceBundle;

import javax.sql.rowset.WebRowSet;

public class MsApprovalTransDAO {

	
	public static void GETVERIFYNAME(MsApprovalTransVO VO) {
		String strproc_name = "{call pkg_simple_view.proc_consultant_name(?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.GETVERIFYNAME()");
			nprocIndex = dao.setProcedure(strproc_name);

			// set value
			dao.setProcInValue(nprocIndex, "deptunitCode", "0",1);
			dao.setProcInValue(nprocIndex, "hosp_code", VO.getStrHospitalCode(),2);
			dao.setProcInValue(nprocIndex, "seatId", "0",3);
			dao.setProcOutValue(nprocIndex, "err", 1,4); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2,5); // 2 for object
			
			
			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				// set values into VO
				VO.setStrVerifiedByWs(ws);
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			VO.setStrMsgString("MsApprovalTransDAO.GETVERIFYNAME() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	public static void deptunit(MsApprovalTransVO VO) {
		String strproc_name = "{call pkg_ipd_view.proc_deptunit_name(?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strcrNoValue = VO.getStrCrNo();
		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd",
					"transactions.MsApprovalTransDAO.DEPTUNIT()");
			nprocIndex = dao.setProcedure(strproc_name);

			// set value
			dao.setProcInValue(nprocIndex, "PUKNO", strcrNoValue,1);
			dao
					.setProcInValue(nprocIndex, "HOSP_CODE", VO
							.getStrHospitalCode(),2);

			dao.setProcOutValue(nprocIndex, "ERR", 1,3); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2,4); // 2 for object

			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "ERR");
			// System.out.println("err == " + strerr);
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
			if (strerr.equals("")) {
				// set values into VO
				VO.setStrDeptUnitWs(ws);

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			VO.setStrMsgString("MsApprovalTransDAO.DEPTUNIT() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	public static void demographicdtl(MsApprovalTransVO VO) {
		// String strStatus =VO.getStrPatStatus();
		String strcrNoValue = VO.getStrCrNo();
		// int ncrNoValue = Integer.parseInt(strcrNoValue);
		String strproc_name = "";
		// if(strStatus.equals("12"))
		// {
		strproc_name = "{call pkg_ipd_view.proc_pat_demographic1(?,?,?)}";
		// }
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd",
					"transactions.MsApprovalTransDAO.demographicdtl()");
			nprocIndex = dao.setProcedure(strproc_name);

			// set value

			dao.setProcInValue(nprocIndex, "puk", strcrNoValue,1);
			dao.setProcOutValue(nprocIndex, "err", 1,2); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2,3); // 2 for object

			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				// set values into VO
				while (ws.next()) {

					VO.setStrageSex(ws.getString(2));
					VO.setStrNname(ws.getString(3));
					VO.setStrFatherHusband(ws.getString(4));
					VO.setStrPatCategory(ws.getString(6));
					VO.setStrPatCategoryCode(ws.getString(7));
				}
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			VO.setStrMsgString("MsApprovalTransDAO.demographicdtl() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	// set episode no,departmentunit,advice no,booking no,ward type,pref adm
	// date
	
	
	
	public static void getDataFromMSApproval(MsApprovalTransVO _msApprovalTransVO) {

		String strproc_name = "";
		strproc_name = "{call pkg_ipd_view.Proc_MSApproval_Dtl(?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.getDataFromMSApproval()");
			nprocIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nprocIndex, "hosp_code", _msApprovalTransVO
					.getStrHospitalCode(),1);
			dao.setProcInValue(nprocIndex, "CRNO", _msApprovalTransVO
					.getStrCrNo(),2);
			dao.setProcInValue(nprocIndex, "ADVISE_NO", _msApprovalTransVO
					.getStrAdviceNo(),3);

			dao.setProcOutValue(nprocIndex, "Err", 1,4);
			dao.setProcOutValue(nprocIndex, "Resultset", 2,5);
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "Err");
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "Resultset");
			if (strerr.equals("")) {
				if(ws.next())
				{
					
					_msApprovalTransVO.setStrApprovedStatus(ws.getString(1));
					_msApprovalTransVO.setStrVerifyRemark(ws.getString(2));
					_msApprovalTransVO.setStrFormNo(ws.getString(3));
					_msApprovalTransVO.setStrRequestDate(ws.getString(4));
					_msApprovalTransVO.setStrWaitPeriod(ws.getString(5));
					_msApprovalTransVO.setStrVerifiedBy(ws.getString(6));
					_msApprovalTransVO.setStrAdmNo(ws.getString(7));
					_msApprovalTransVO.setStrVerifiedDate(ws.getString(8));
					_msApprovalTransVO.setStrOccID(ws.getString(9));
					_msApprovalTransVO.setStrVerifyByName(ws.getString(10));
					_msApprovalTransVO.setStrApproveDate(ws.getString(11));
					_msApprovalTransVO.setStrApprovedBY(ws.getString(12));
					_msApprovalTransVO.setStrRemark(ws.getString(13));
					_msApprovalTransVO.setStrBedCode(ws.getString(14));
					_msApprovalTransVO.setStrRoomNo(ws.getString(15));
					_msApprovalTransVO.setStrWardCode(ws.getString(16));
					_msApprovalTransVO.setStrWardType(ws.getString(17));
					_msApprovalTransVO.setStrPrefAdmDate(ws.getString(18));
					_msApprovalTransVO.setStrAdmDate(ws.getString(28));
					_msApprovalTransVO.setStrEpisodeNumber(ws.getString(19));
					_msApprovalTransVO.setStrWardName(ws.getString(20));
					_msApprovalTransVO.setStrDeptUnit(ws.getString(21));
					_msApprovalTransVO.setStrRoomBedNo(ws.getString(22) + "/"+ ws.getString(23));
					_msApprovalTransVO.setStrRoomType(ws.getString(24));
					_msApprovalTransVO.setStrBedType(ws.getString(25));	
					_msApprovalTransVO.setStrPatCategoryCode(ws.getString(26));
					_msApprovalTransVO.setAppStatus(ws.getString(29));
					_msApprovalTransVO.setStrVisitNo(ws.getString(27));
				}
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			
			_msApprovalTransVO
					.setStrMsgString("MsApprovalTransDAO.getDataFromMSApproval() --> "
							+ e.getMessage());
			_msApprovalTransVO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	
	public static void getProvisionalDiagnosis(MsApprovalTransVO _msApprovalTransVO) {

		String strproc_name =  "{call pkg_simple_view.proc_diagnosis_list(?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;
		boolean fIsFirst=true;
		try {
			dao = new HisDAO("ipd",
					"transactions.MsApprovalTransDAO.getDataFromMSApproval()");
			
			//System.out.println("dao : "+dao);
			
			nprocIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nprocIndex, "puk", _msApprovalTransVO.getStrCrNo(),1);
			dao.setProcInValue(nprocIndex, "episodecode", _msApprovalTransVO.getStrEpisodeNumber(),2);
			dao.setProcInValue(nprocIndex, "visitno", _msApprovalTransVO.getStrVisitNo(),3);

			dao.setProcOutValue(nprocIndex, "err", 1,4);
			dao.setProcOutValue(nprocIndex, "resultset", 2,5);
			
			dao.executeProcedureByPosition(nprocIndex);
			
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				while(ws.next()){
					if(fIsFirst)
						_msApprovalTransVO.setStrProvisionDiagnosis(ws.getString(1));
					else
						_msApprovalTransVO.setStrProvisionDiagnosis(_msApprovalTransVO.getStrProvisionDiagnosis()+", "+ws.getString(1));
				fIsFirst=false;
				}
				
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			
			_msApprovalTransVO
					.setStrMsgString("MsApprovalTransDAO.getDataFromMSApproval() --> "
							+ e.getMessage());
			_msApprovalTransVO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	
	public static void msApprovalDetails(MsApprovalTransVO _msApprovalTransVO) {

		String strproc_name = "";
		strproc_name = "{call pkg_ipd_view.Proc_MSApproval_Dtl(?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd",
					"transactions.MsApprovalTransDAO.msApprovalDetails()");
			nprocIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nprocIndex, "hosp_code", _msApprovalTransVO.getStrHospitalCode(),1);
			dao.setProcInValue(nprocIndex, "CRNO", _msApprovalTransVO.getStrCrNo(),2);
			
			dao.setProcInValue(nprocIndex, "ADVISE_NO", _msApprovalTransVO.getStrAdviceNo(),3);

			dao.setProcOutValue(nprocIndex, "Err", 1,4);
			dao.setProcOutValue(nprocIndex, "Resultset", 2,5);
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "Err");
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "Resultset");
			if (strerr.equals("")) {
				ws.next();
				_msApprovalTransVO.setStrRoomNo(ws.getString(15));
				_msApprovalTransVO.setStrWardCode(ws.getString(16));
				_msApprovalTransVO.setStrWardType(ws.getString(17));
				_msApprovalTransVO.setStrBedType(ws.getString(25));
				_msApprovalTransVO.setStrRoomType(ws.getString(24));
				_msApprovalTransVO.setStrPrefAdmDate(ws.getString(18));
				_msApprovalTransVO.setStrBedCode(ws.getString(14));
				_msApprovalTransVO.setStrApprovedStatus(ws.getString(1));
				_msApprovalTransVO.setStrEpisodeNumber(ws.getString(19));
				_msApprovalTransVO.setStrDeptUnit(ws.getString(21));
				_msApprovalTransVO.setStrAdmDate(ws.getString(28));
				_msApprovalTransVO.setStrWardName(ws.getString(20));
				_msApprovalTransVO.setStrRoomBedNo(ws.getString(22) + "/"+ ws.getString(23));
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
		
			_msApprovalTransVO
					.setStrMsgString("MsApprovalTransDAO.msApprovalDetails() --> "
							+ e.getMessage());
			_msApprovalTransVO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	public static void viewapprove(MsApprovalTransVO VO) {

		String strcrNoValue = VO.getStrCrNo();
		String stradvno = VO.getStrAdviceNo();
		//String strbookingno = VO.getStrBooking_date();

		String strproc_name = "";

		strproc_name = "{call Proc_view_Approval(?,?,?,?,?)}";
		// }
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.viewapprove()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value

			dao.setProcInValue(nprocIndex, "pukno", strcrNoValue);
			dao.setProcInValue(nprocIndex, "advno", stradvno);
			//
			// dao.setProcInValue(nprocIndex, "bookdate", "23-jul-2008");

			// dao.setProcInValue(nprocIndex, "hosp_code", "108");
			dao
					.setProcInValue(nprocIndex, "hosp_code", VO
							.getStrHospitalCode());
			dao.setProcOutValue(nprocIndex, "err", 1); // 1 for string return
			dao.setProcOutValue(nprocIndex, "resultset", 2); // 2 for object

			// execute procedure
			dao.executeProcedure(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				// set values into VO
				while (ws.next()) {

					VO.setStrApprovedStatus(ws.getString(1));
					VO.setStrApproveDate(ws.getString(2));
					VO.setStrApprovedBY(ws.getString(3));
					VO.setStrRemark(ws.getString(4));
					VO.setStrWardName(ws.getString(5)); // ward name
					VO.setStrBedName(ws.getString(6));

				}
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			VO.setStrMsgString("MsApprovalTransDAO.viewapprove() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
/*
	public static void wardbed(MsApprovalTransVO VO) {

		String strcrNoValue = VO.getStrCrNo();
		String stradvno = VO.getStrAdviceNo();
		//String strbookingno = VO.getStrBooking_date();

		
		 
		String strproc_name = "";

		strproc_name = "{call Proc_WardBed_detail(?,?,?,?,?)}";
		// }
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd", "transactions.MsApprovalTransDAO.wardbed()");
			nprocIndex = dao.setProcedure(strproc_name);
			// System.out.println("procIndex==" + nprocIndex);
			// set value

			dao.setProcInValue(nprocIndex, "pukno", strcrNoValue);
			dao.setProcInValue(nprocIndex, "advno", stradvno);
			dao.setProcInValue(nprocIndex, "hosp_code", "108");
			dao.setProcOutValue(nprocIndex, "err", 1); // 1 for string return
			dao.setProcOutValue(nprocIndex, "resultset", 2); // 2 for object

			// execute procedure
			dao.executeProcedure(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "err");
			// System.out.println("err == " + strerr);
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				// set values into VO

				while (ws.next()) {

					// System.out.println("WARD CODE=="+ws.getString(7));
					// System.out.println("BED CODE=="+ws.getString(8));
					VO.setStrWardName(ws.getString(1)); // ward name
					VO.setStrBedName(ws.getString(2));// bed name
					VO.setHstrbedType(ws.getString(3)); // Bed TYPE
					VO.setHstrRoomType(ws.getString(4)); //
					VO.setStrWardCode(ws.getString(5));// wardcode
					VO.setStrBedCode(ws.getString(6)); // bed code
					VO.setStrRoomNo(ws.getString(7));
				}
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			VO.setStrMsgString("MsApprovalTransDAO.wardcode() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
		// System.out.println("WARDCODE ===="+VO.getStrWardCode());

	}
*/
	// set list of patient for those patient whose status is not approved and
	// status has to be changed from not approved
	// to approved
	public static void msapprovalList(MsApprovalTransVO VO) {

		// System.out.println("strlength in dao " + strlength);
		// System.out.println("inside dao-->>msapprovallist");
		int nlength;
		String[] chk = VO.getStrChk1();
		String strlength = VO.getStrLength();
		nlength = Integer.parseInt(strlength);
		// System.out.println("nlength in dao==" + nlength);

		String strproc_name = "";
		// if(strStatus.equals("12"))
		// {
		strproc_name = "{call pkg_ipd_view.PROC_MSAPPROVE_LIST(?,?,?,?,?,?)}";
		// }
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet[] ws = new WebRowSet[nlength];

		try {
			dao = new HisDAO("ipd",
					"transactions.MsApprovalTransDAO.msapprovalList()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value
			for (int i = 0; i < nlength; i++) {
				String strcrno = chk[i].split("@")[0];
				String stradno = chk[i].split("@")[1];
				String strbkdt = chk[i].split("@")[2];

				dao.setProcInValue(nprocIndex, "PUKNO", strcrno,1);
				dao.setProcInValue(nprocIndex, "ADVNO", stradno,2);
				dao.setProcInValue(nprocIndex, "BOOKDT", strbkdt,3);
				dao.setProcInValue(nprocIndex, "HOSP_CODE", VO
						.getStrHospitalCode(),4);
				dao.setProcOutValue(nprocIndex, "ERR", 1,5); // 1 for string
				// return value
				dao.setProcOutValue(nprocIndex, "RESULTSET", 2,6); // 2 for
				// object

				// execute procedure
				dao.executeProcedureByPosition(nprocIndex);

				// get value
				strerr = dao.getString(nprocIndex, "ERR");
				if (strerr == null)
					strerr = "";

				if (strerr.equals("")) {
					// set values into VO
					ws[i] = dao.getWebRowSet(nprocIndex, "RESULTSET");
				} else {
					throw new Exception(strerr);
				}

			}

			VO.setApprovalListWs(ws);

		} catch (Exception e) {
		
			VO.setStrMsgString("MsApprovalTransDAO.msapprovalList() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/*
	 * public static void wardallotement(MsApprovalTransVO VO) {
	 * 
	 * System.out.println("inside dao-->>msapprovallist");
	 * 
	 * String strproc_name = ""; // if(strStatus.equals("12")) // { strproc_name =
	 * "{call Proc_allotement_List(?,?)}"; // } HisDAO dao = null; int
	 * nprocIndex = 0;
	 * 
	 * String strerr = ""; WebRowSet ws = null; try { dao = new HisDAO("ipd",
	 * "transactions.MsApprovalTransDAO.wardallotement()"); nprocIndex =
	 * dao.setProcedure(strproc_name); System.out.println("procIndex==" +
	 * nprocIndex); // set value
	 * 
	 * dao.setProcOutValue(nprocIndex, "ERR", 1); // 1 for string // return
	 * value dao.setProcOutValue(nprocIndex, "RESULTSET", 2); // 2 for object //
	 * execute procedure dao.executeProcedure(nprocIndex); // get value strerr =
	 * dao.getString(nprocIndex, "ERR"); System.out.println("err == " + strerr);
	 * if (strerr == null) strerr = "";
	 * 
	 * if (strerr.equals("")) { // set values into VO ws =
	 * dao.getWebRowSet(nprocIndex, "RESULTSET"); } else { throw new
	 * Exception(strerr); }
	 * 
	 * VO.setStrwardallotementListWs(ws); } catch (Exception e) {
	 * e.printStackTrace();
	 * VO.setStrMsgString("MsApprovalTransDAO.msapprovalList() --> " +
	 * e.getMessage()); VO.setStrMsgType("1"); } finally { if (dao != null) {
	 * dao.free(); dao = null; } } }
	 */

	// //provide ordered list of approved patient
	public static void popupallotementlist(MsApprovalTransVO VO) {

		// System.out.println("inside dao-->>popupallotementlist");

		String strproc_name = "";
		// if(strStatus.equals("12"))
		// {
		strproc_name = "{call Proc_AllotementOrdered_List(?,?,?)}";
		// }
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;
		try {
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.wardallotement()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value

			dao.setProcInValue(nprocIndex, "HOSP_CODE", VO.getStrHospitalCode(),1);
			dao.setProcOutValue(nprocIndex, "ERR", 1,2); // 1 for string
			// return value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2,3); // 2 for object

			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "ERR");
			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				// set values into VO
				ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
			} else {
				throw new Exception(strerr);
			}

			VO.setStrwardallotementListWs(ws);

		} catch (Exception e) {
			// e.printStackTrace();
			VO.setStrMsgString("MsApprovalTransDAO.popupallotementlist() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	public static void diagnosisdtl(MsApprovalTransVO VO) {
		// String strStatus =VO.getStrPatStatus();
		String strcrNoValue = VO.getStrCrNo();
		String deptunit = VO.getStrDeptUnitCmb();

		// if(strStatus.equals("12"))
		// {
		String strproc_name = "{call PROC_PROV_DIGNOSIS_VIEW(?,?,?,?,?)}";
		// }
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.diagnosisdtl()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value

			dao.setProcInValue(nprocIndex, "PUKNO", strcrNoValue);
			dao.setProcInValue(nprocIndex, "DEPTUNIT", deptunit);

			dao.setProcInValue(nprocIndex, "HOSP_CODE", VO.getStrHospitalCode());
			dao.setProcOutValue(nprocIndex, "ERR", 1); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2); // 2 for object

			// execute procedure
			dao.executeProcedure(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "ERR");
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
			if (strerr.equals("")) {
				// set values into VO
				while (ws.next()) {

					VO.setStrProvisionDiagnosis(ws.getString(1));
					// VO.setStraddressWs(ws);
				}

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			VO.setStrMsgString("MsApprovalTransDAO.diagnosisdtl() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
/*
	public static void viewdiagnosisdtl(MsApprovalTransVO VO) {
		// System.out.println("inside DAO DIAGNOSIS dtl......");
		// String strStatus =VO.getStrPatStatus();
		String strcrNoValue = VO.getStrCrNo();
		String deptunit = VO.getStrDeptUnitCode();
		;
		// System.out.println("strcrNoValue==" + strcrNoValue);
		// System.out.println("deptunit value==" + deptunit);

		// if(strStatus.equals("12"))
		// {
		String strproc_name = "{call PROC_PROV_DIGNOSIS_VIEW(?,?,?,?,?)}";
		// }
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd",
					"transactions.MsApprovalTransDAO.diagnosisdtl()");
			nprocIndex = dao.setProcedure(strproc_name);
			// System.out.println("procIndex==" + nprocIndex);
			// set value

			dao.setProcInValue(nprocIndex, "PUKNO", strcrNoValue);
			dao.setProcInValue(nprocIndex, "DEPTUNIT", deptunit);
			dao
					.setProcInValue(nprocIndex, "HOSP_CODE", VO
							.getStrHospitalCode());

			dao.setProcOutValue(nprocIndex, "ERR", 1); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2); // 2 for object

			// execute procedure
			dao.executeProcedure(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "ERR");
			// System.out.println("err == " + strerr);
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
			if (strerr.equals("")) {
				// set values into VO
				while (ws.next()) {

					// System.out.println("view diag" + ws.getString(1));
					VO.setStrProvisionDiagnosis(ws.getString(1));
					// VO.setStraddressWs(ws);
				}

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			VO.setStrMsgString("MsApprovalTransDAO.diagnosisdtl() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
*/
	public static void addressdtl(MsApprovalTransVO VO) {
		// String strStatus =VO.getStrPatStatus();
		String strcrNoValue = VO.getStrCrNo();
		// int ncrNoValue = Integer.parseInt(strcrNoValue);
		String strproc_name = "";
		// if(strStatus.equals("12"))
		// {
		strproc_name = "{call pkg_ipd_view.proc_pat_address(?,?,?,?)}";
		// }
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.addressdtl()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value

			dao.setProcInValue(nprocIndex, "pukno", strcrNoValue,1);
			dao.setProcInValue(nprocIndex, "addresstype", "1",2); // 2-for current
			// address
			dao.setProcOutValue(nprocIndex, "err", 1,3); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2,4); // 2 for object

			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				// set values into VO
				while (ws.next()) {
					VO.setStraddress(ws.getString(1));
					// VO.setStraddressWs(ws);
				}

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			VO.setStrMsgString("MsApprovalTransDAO.addressdtl() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	public static void viewconsultant(MsApprovalTransVO VO) {
		String strcrNoValue = VO.getStrCrNo();
		String deptunit = VO.getStrDeptUnitCode();
		String strproc_name = "";
		// if(strStatus.equals("12"))
		// {
		strproc_name = "{call pkg_ipd_view.PROC_CONSULTANT(?,?,?,?,?)}";
		// }
		HisDAO dao = null;
		int nprocIndex = 0;

		String err = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.consultant()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value

			dao.setProcInValue(nprocIndex, "PUKNO", strcrNoValue,1);
			dao.setProcInValue(nprocIndex, "DEPTUNIT", deptunit,2);
			dao.setProcInValue(nprocIndex, "HOSP_CODE", VO.getStrHospitalCode(),3);
			dao.setProcOutValue(nprocIndex, "ERR", 1,4); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2,5); // 2 for object

			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);

			// get value
			err = dao.getString(nprocIndex, "ERR");
			if (err == null)
				err = "";

			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
			if (err.equals("")) {
				// set values into VO
				while (ws.next()) {

					VO.setStrConsultantName(ws.getString(2));

				}
			} else {
				throw new Exception(err);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			VO.setStrMsgString("MsApprovalTransDAO.consultant() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	public static void consultant(MsApprovalTransVO VO) {
		String strcrNoValue = VO.getStrCrNo();
		String deptunit = VO.getStrDeptUnitCmb();
		String strproc_name = "";
		// if(strStatus.equals("12"))
		// {
		strproc_name = "{call pkg_ipd_view.PROC_CONSULTANT(?,?,?,?,?)}";
		// }
		HisDAO dao = null;
		int nprocIndex = 0;

		String err = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd",
					"transactions.MsApprovalTransDAO.consultant()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value

			dao.setProcInValue(nprocIndex, "PUKNO", strcrNoValue,1);
			dao.setProcInValue(nprocIndex, "DEPTUNIT", deptunit,2);
			dao.setProcInValue(nprocIndex, "HOSP_CODE", VO.getStrHospitalCode(),3);
			dao.setProcOutValue(nprocIndex, "ERR", 1,4); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2,5); // 2 for object
			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);

			// get value
			err = dao.getString(nprocIndex, "ERR");
			if (err == null)
				err = "";

			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
			if (err.equals("")) {
				// set values into VO
				while (ws.next()) {
					VO.setStrConsultantName(ws.getString(2));
				}

			} else {
				throw new Exception(err);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			VO.setStrMsgString("MsApprovalTransDAO.consultant() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	public  static void select(MsApprovalTransVO _MsApprovalTransVO) throws Exception
	{
		String strErr = "";
		WebRowSet ws = null;
		String strKeyname="FORM_NO";
		String strBlockkey="1";
		HisDAO dao=null;
		int nInsertedIndex=0;
		  String strProcName = "pkg_bill_view.Proc_Sblt_Primarykey_Dtl";
		  String strFileName = "billing.dao.PrimaryKeyDAO";
		try 
		{
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.select()");
			nInsertedIndex = dao.setProcedure("{call " + strProcName + "(?::character varying,?::character varying,?::character varying,?::character varying,?,?::character varying)}");
	
			//set the valueInput Value
			dao.setProcInValue(nInsertedIndex,"keyname",strKeyname,1);			
			dao.setProcInValue(nInsertedIndex,"blockkey",strBlockkey,2);
			dao.setProcInValue(nInsertedIndex,"commitFlag","1",3);
			dao.setProcInValue(nInsertedIndex,"hosp_code",_MsApprovalTransVO.getStrHospitalCode(),4);									
			//output value
			dao.setProcOutValue(nInsertedIndex,"err",1,6);			
			dao.setProcOutValue(nInsertedIndex,"resultset",2,5);
		
			//keep in batch
			dao.executeProcedureByPosition(nInsertedIndex);
		
			 
			ws = dao.getWebRowSet(nInsertedIndex,"resultset");

			if(ws.next())
			{
				_MsApprovalTransVO.setStrFormNo(ws.getString(1));
				//this.setStrPrimrayKeyValue(ws.getString(1));
			}
		} 
		catch(Exception e) {
			strErr = e.getMessage();
			throw new Exception(strFileName + ".insert() --> " + strErr);
		}
		
	}

	public static void patstatus(MsApprovalTransVO VO) {
		String strcrNoValue = VO.getStrCrNo();
		String strproc_name = "";
		strproc_name = "{call pkg_ipd_view.proc_pat_status(?,?,?,?,?)}";

		HisDAO dao = null;
		int nprocIndex = 0;

		String err = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd",
					"transactions.MsApprovalTransDAO.consultant()");
			nprocIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nprocIndex, "modeval", "1",1);
			dao.setProcInValue(nprocIndex, "puk", strcrNoValue,2);
			dao.setProcInValue(nprocIndex, "hosp_code", VO.getStrHospitalCode(),3);
			dao.setProcOutValue(nprocIndex, "err", 1,4);
			dao.setProcOutValue(nprocIndex, "resultset", 2,5);
			dao.executeProcedureByPosition(nprocIndex);
			err = dao.getString(nprocIndex, "err");
			if (err == null)
				err = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if(ws.size()==0)
			{
				VO.setStrMsgType("2");
			}
			if (err.equals("")) {
				while (ws.next()) {
					VO.setStrPatStatusCode(ws.getString(3));
					VO.setStrIsdead(ws.getString(6));
				}
			} else {
				throw new Exception(err);
			}
		} catch (Exception e) {
			VO.setStrMsgString("MsApprovalTransDAO.consultant() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			ws = null;
		}
	}

	/*
	 * public static void occupationdtl(MsApprovalTransVO VO) {
	 * System.out.println("inside DAO occupationdtl dtl......"); // String
	 * strStatus =VO.getStrPatStatus(); System.out.println(""); String
	 * strcrNoValue = VO.getStrCrNo(); // String deptunit =
	 * VO.getStrDeptUnitCmb(); System.out.println("strcrNoValue==" +
	 * strcrNoValue); // int ncrNoValue = Integer.parseInt(strcrNoValue); String
	 * proc_name = ""; // if(strStatus.equals("12")) // { proc_name = "{call
	 * proc_normalpat_occupation(?,?,?)}"; // } HisDAO dao = null; int procIndex =
	 * 0;
	 * 
	 * String err = ""; WebRowSet ws = null;
	 * 
	 * try { dao = new HisDAO("ipd",
	 * "transactions.MsApprovalTransDAO.occupationdtl()"); procIndex =
	 * dao.setProcedure(proc_name); System.out.println("procIndex==" +
	 * procIndex); // set value
	 * 
	 * dao.setProcInValue(procIndex, "PUKNO", strcrNoValue); //
	 * dao.setProcInValue(procIndex,"DEPTUNIT",deptunit);
	 * dao.setProcOutValue(procIndex, "ERR", 1); // 1 for string return // value
	 * dao.setProcOutValue(procIndex, "RESULTSET", 2); // 2 for object //
	 * execute procedure dao.executeProcedure(procIndex); // get value err =
	 * dao.getString(procIndex, "ERR"); System.out.println("err == " + err); if
	 * (err == null) err = "";
	 * 
	 * ws = dao.getWebRowSet(procIndex, "RESULTSET"); if (err.equals("")) { //
	 * set values into VO while (ws.next()) {
	 * 
	 * System.out.println("GOVT TYPE IN OCCUPATION" + ws.getString(11));
	 * VO.setStrEmp_No(ws.getString(1)); VO.setStrEmpName(ws.getString(2));
	 * VO.setStrDesigName(ws.getString(3));
	 * VO.setStrBasicIncom(ws.getString(4));
	 * VO.setStrOfficeName(ws.getString(5));
	 * VO.setStrOfficeAddress1(ws.getString(6));
	 * VO.setStrOfficeAddress2(ws.getString(7));
	 * VO.setStrRelation(ws.getString(8));
	 * VO.setStrGovtEmployee(ws.getString(9));
	 * VO.setStrIsDependent(ws.getString(10));
	 * VO.setStrGovtType(ws.getString(11));
	 * VO.setStrGovtTypeHidden(ws.getString(12));
	 * VO.setHstrRelation(ws.getString(10)); // VO.setStraddressWs(ws); } } else {
	 * throw new Exception(err); } } catch (Exception e) { e.printStackTrace();
	 * VO.setStrMsgString("MsApprovalTransDAO.occupationdtl() --> " +
	 * e.getMessage()); VO.setStrMsgType("1"); } finally { if (dao != null) {
	 * dao.free(); dao = null; } } }
	 * 
	 * public static void pgiempoccupationdtl(MsApprovalTransVO VO) {
	 * System.out.println("inside DAO occupationdtlPGI dtl......"); // String
	 * strStatus =VO.getStrPatStatus(); System.out.println(""); String
	 * strcrNoValue = VO.getStrCrNo(); System.out.println("strcrNoValue==" +
	 * strcrNoValue); // int ncrNoValue = Integer.parseInt(strcrNoValue); String
	 * proc_name = ""; // if(strStatus.equals("12")) // { proc_name = "{call
	 * proc_pgi_emp_occupation_dtl (?,?,?)}"; // } HisDAO dao = null; int
	 * procIndex = 0;
	 * 
	 * String err = ""; WebRowSet ws = null;
	 * 
	 * try { dao = new HisDAO("ipd",
	 * "transactions.MsApprovalTransDAO.pgiempoccupationdtl()"); procIndex =
	 * dao.setProcedure(proc_name); System.out.println("procIndex==" +
	 * procIndex); // set value
	 * 
	 * dao.setProcInValue(procIndex, "puk", strcrNoValue);
	 * dao.setProcOutValue(procIndex, "err", 1); // 1 for string return // value
	 * dao.setProcOutValue(procIndex, "resultset", 2); // 2 for object //
	 * execute procedure dao.executeProcedure(procIndex); // get value err =
	 * dao.getString(procIndex, "err"); System.out.println("err == " + err); if
	 * (err == null) err = "";
	 * 
	 * ws = dao.getWebRowSet(procIndex, "resultset"); if (err.equals("")) { //
	 * set values into VO while (ws.next()) {
	 * System.out.println("ws.getString(5) RELCODE" + ws.getString(5));
	 * System.out.println("pgi empno==" + ws.getString(1));
	 * VO.setStrEmp_No(ws.getString(1)); VO.setStrEmpName(ws.getString(2));
	 * VO.setStrRelation(ws.getString(3)); VO.setStrDesigName(ws.getString(4));
	 * VO.setStrIsDependent(ws.getString(5));
	 * VO.setHstrRelation(ws.getString(5)); } } else { throw new Exception(err); } }
	 * catch (Exception e) { e.printStackTrace();
	 * VO.setStrMsgString("MsApprovalTransDAO.pgiempoccupationdtl() --> " +
	 * e.getMessage()); VO.setStrMsgType("1"); } finally { if (dao != null) {
	 * dao.free(); dao = null; } } }
	 */
	public static void seatUser(MsApprovalTransVO VO) {
		// String strStatus =VO.getStrPatStatus();

		String strseatId = VO.getStrSeatId();

		// int ncrNoValue = Integer.parseInt(strcrNoValue);
		String strproc_name = "";

		strproc_name = "{call proc_seat_username (?,?,?,?,?)}";
		// }
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.seatUser()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value

			dao.setProcInValue(nprocIndex, "seatid", strseatId);
			dao.setProcInValue(nprocIndex, "hospitalcode", VO.getStrHospitalCode());
			dao.setProcInValue(nprocIndex, "seatdt", VO.getStrSeatId());
			dao.setProcOutValue(nprocIndex, "err", 1); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "resultset", 2); // 2 for object

			// execute procedure
			dao.executeProcedure(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				// set values into VO
				while (ws.next()) {

					VO.setStrVerifiedBy(ws.getString(1));

				}

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			VO.setStrMsgString("MsApprovalTransDAO.seatUser() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	/*
	 * public static void pgiaddress(MsApprovalTransVO VO) {
	 * 
	 * String proc_name = ""; // if(strStatus.equals("12")) // { proc_name =
	 * "{call proc_pgi_Address (?,?)}"; // } HisDAO dao = null; int procIndex =
	 * 0;
	 * 
	 * String err = ""; WebRowSet ws = null;
	 * 
	 * try { dao = new HisDAO("ipd",
	 * "transactions.MsApprovalTransDAO.pgiaddress()"); procIndex =
	 * dao.setProcedure(proc_name); System.out.println("procIndex==" +
	 * procIndex); // set value
	 * 
	 * dao.setProcOutValue(procIndex, "err", 1); // 1 for string return // value
	 * dao.setProcOutValue(procIndex, "resultset", 2); // 2 for object //
	 * execute procedure dao.executeProcedure(procIndex); // get value err =
	 * dao.getString(procIndex, "err"); System.out.println("err == " + err); if
	 * (err == null) err = "";
	 * 
	 * ws = dao.getWebRowSet(procIndex, "resultset"); if (err.equals("")) { //
	 * set values into VO while (ws.next()) {
	 * 
	 * VO.setStrOfficeName(ws.getString(1));
	 * VO.setStrOfficeAddress1(ws.getString(2));
	 * VO.setStrOfficeAddress2(ws.getString(3));
	 * VO.setStrPhoneNo(ws.getString(4)); } } else { throw new Exception(err); } }
	 * catch (Exception e) { e.printStackTrace();
	 * VO.setStrMsgString("MsApprovalTransDAO.pgiaddress() --> " +
	 * e.getMessage()); VO.setStrMsgType("1"); } finally { if (dao != null) {
	 * dao.free(); dao = null; } } }
	 */
	public static void mlcdtl(MsApprovalTransVO VO) {

		String strcrNoValue = VO.getStrCrNo();
		String strEpisodeNo = VO.getStrEpisodeNumber();

		// int ncrNoValue = Integer.parseInt(strcrNoValue);
		String strproc_name = "";
		// if(strStatus.equals("12"))
		// {
		strproc_name = "{call pkg_ipd_view.proc_pat_mlc(?,?,?,?,?)}";
		// }
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd", "transactions.MsApprovalTransDAO.mlcdtl()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value

			dao.setProcInValue(nprocIndex, "puk", strcrNoValue,1);
			dao.setProcInValue(nprocIndex, "episode", strEpisodeNo,2);
			dao
					.setProcInValue(nprocIndex, "hosp_code", VO
							.getStrHospitalCode(),3);
			dao.setProcOutValue(nprocIndex, "err", 1,4); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2,5); // 2 for object

			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				// set values into VO
				while (ws.next()) {

					VO.setStrMlcNo(ws.getString(1));

				}

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			VO.setStrMsgString("MsApprovalTransDAO.mlcdtl() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	public static boolean insertorupdate(MsApprovalTransVO VO) {

		String strcrNoValue = VO.getStrCrNo();
		String strAdvNo = VO.getStrAdviceNo();
		String strproc_name = "";
		strproc_name = "{call pkg_ipd_view.Proc_Select_msapprove(?,?,?,?,?)}";
		// }
		HisDAO dao = null;
		int nprocIndex = 0;
		int ncount = 0;
		boolean freturnValue = false;
		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.insertorupdate()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value

			dao.setProcInValue(nprocIndex, "PUKNO", strcrNoValue,1);
			dao.setProcInValue(nprocIndex, "ADV_NO", strAdvNo,2);
			dao.setProcInValue(nprocIndex, "HOSP_CODE", VO.getStrHiddenDesignation(),3);
			dao.setProcOutValue(nprocIndex, "ERR", 1,4); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2,5); // 2 for object

			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "ERR");

			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
			if (strerr.equals("")) {
				// set values into VO
				while (ws.next()) {

					ncount = Integer.parseInt((ws.getString(1)));
				}

				if (ncount == 0) {
					freturnValue = true;
				} else {
					freturnValue = false;
				}

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			VO.setStrMsgString("MsApprovalTransDAO.insertorupdate() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
		return freturnValue;
	}

	public static void insert(MsApprovalTransVO _msApprovalTransVO) {
		String strProcName = "{call pkg_ipd_dml.proc_ms_approval_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		//String strProcName2 = "{call proc_insertoccupationdtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
	//	int nProcIndex2 = 0;
		String strErr1 = "";
		String strErr2 = "";
		HisDAO daoObj = null;
		try {
			daoObj = new HisDAO("MS Approval Trans", "MsApprovalTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "puk", _msApprovalTransVO.getStrCrNo(),2);
			daoObj.setProcInValue(nProcIndex, "advice_no", _msApprovalTransVO.getStrAdviceNo(),3);
			daoObj.setProcInValue(nProcIndex, "ward_type", _msApprovalTransVO.getStrWard().replace("^", "#").split("#")[1],4);
			daoObj.setProcInValue(nProcIndex, "preference_admdt",_msApprovalTransVO.getStrPropAdminssionDate(),5);
			daoObj.setProcInValue(nProcIndex, "deptunit_code",_msApprovalTransVO.getStrUnitValue(),6);
			daoObj.setProcInValue(nProcIndex, "pat_incom", _msApprovalTransVO.getStrOccBasic(),7);
			daoObj.setProcInValue(nProcIndex, "govttype", _msApprovalTransVO.getStrGovtType(),8);
			daoObj.setProcInValue(nProcIndex, "remarks", _msApprovalTransVO.getStrRemark(),9);
			daoObj.setProcInValue(nProcIndex, "seatid", _msApprovalTransVO.getStrSeatId(),10);
			daoObj.setProcInValue(nProcIndex, "form_no", _msApprovalTransVO.getStrFormNo(),11);
			daoObj.setProcInValue(nProcIndex, "hosp_code", _msApprovalTransVO.getStrHospitalCode(),12);
			daoObj.setProcInValue(nProcIndex, "veri_by", _msApprovalTransVO.getStrVerifiedBy(),13);
			daoObj.setProcInValue(nProcIndex, "veri_date", _msApprovalTransVO.getStrVerifiedDate(),14);
			daoObj.setProcInValue(nProcIndex, "wait_period", _msApprovalTransVO.getStrWaitPeriod(),15);
			daoObj.setProcInValue(nProcIndex, "hipnum_admno",_msApprovalTransVO.getStrAdmNo(),16);
			daoObj.setProcInValue(nProcIndex, "req_date", _msApprovalTransVO.getStrRequestDate(),17);
			daoObj.setProcInValue(nProcIndex, "episode_code",_msApprovalTransVO.getStrEpisodeNumber(),18);
			daoObj.setProcInValue(nProcIndex, "relation_code",_msApprovalTransVO.getStrOccRelation(),19);
			daoObj.setProcInValue(nProcIndex, "emp_no", _msApprovalTransVO.getStrOccEmpNo(),20);
			daoObj.setProcInValue(nProcIndex, "Emp_Name", _msApprovalTransVO.getStrOccEmpName(),21);
			daoObj.setProcInValue(nProcIndex, "desig", _msApprovalTransVO.getStrOccDesc(),22);
			daoObj.setProcInValue(nProcIndex, "office_name", _msApprovalTransVO.getStrOccOffName(),23);
			daoObj.setProcInValue(nProcIndex, "office_add1", _msApprovalTransVO.getStrOccAdd1(),24);
			daoObj.setProcInValue(nProcIndex, "office_add2", _msApprovalTransVO.getStrOccAdd2(),25);
			daoObj.setProcInValue(nProcIndex, "office_city", _msApprovalTransVO.getStrOccCity(),26);
			daoObj.setProcInValue(nProcIndex, "office_state",_msApprovalTransVO.getStrOccState(),27);
			daoObj.setProcInValue(nProcIndex, "isgovt_emp", _msApprovalTransVO.getStrOccIsGovtEmp(),28);
			daoObj.setProcInValue(nProcIndex, "basic_pay", _msApprovalTransVO.getStrOccBasic(),29);
			daoObj.setProcInValue(nProcIndex, "org_type", _msApprovalTransVO.getStrOccOrgType(),30);
			daoObj.setProcInValue(nProcIndex, "offtel_no", _msApprovalTransVO.getStrOccOffPhNo(),31);
			daoObj.setProcInValue(nProcIndex, "occup_id","",32);
			daoObj.setProcInValue(nProcIndex, "desig_code","",33);
			//used in approval
			daoObj.setProcInValue(nProcIndex, "approve_by","",34);
			daoObj.setProcInValue(nProcIndex, "app_status","",35);
			daoObj.setProcInValue(nProcIndex, "app_remarks","",36);
			//used in ward allotement			
			System.out.println("ms approval ward"+_msApprovalTransVO.getStrWard());
			daoObj.setProcInValue(nProcIndex, "ward_code",_msApprovalTransVO.getStrWard().replace("^", "#").split("#")[0],37);
			daoObj.setProcInValue(nProcIndex, "room_no","",38);
			daoObj.setProcInValue(nProcIndex, "bed_code","",39);
			daoObj.setProcInValue(nProcIndex, "block_bed_expiry","",40);
			//used in cancel
			daoObj.setProcInValue(nProcIndex, "bed_type","",41);
			daoObj.setProcInValue(nProcIndex, "room_type","",42);
			daoObj.setProcInValue(nProcIndex, "cancel_by","",43);
			daoObj.setProcInValue(nProcIndex, "cancel_rmks","",44);
			daoObj.setProcOutValue(nProcIndex, "err", 1,45);
			daoObj.setProcInValue(nProcIndex, "priority_type",_msApprovalTransVO.getStrPriorityType(),46);
			daoObj.setProcInValue(nProcIndex, "urgent_reason",_msApprovalTransVO.getStrUrgentTypeReason(),47);
			daoObj.setProcInValue(nProcIndex, "urgent_remarks",_msApprovalTransVO.getStrUrgentTypeRemarks(),48);
			 
			daoObj.execute(nProcIndex, 1);

			synchronized (daoObj) {
				daoObj.fire();
			}
			strErr1 = daoObj.getString(nProcIndex, "err");

			if (strErr1 == null)
				strErr1 = "";
			if (strErr1.equals("")) {

			} else {
				throw new Exception(strErr1);
			}
			strErr2 = daoObj.getString(nProcIndex, "err");
			if (strErr2 == null)
				strErr2 = "";
			if (strErr2.equals("")) {
			} else {
				throw new Exception(strErr2);
			}

		} catch (Exception e) {

			_msApprovalTransVO
					.setStrMsgString("MsApprovalTransDAO.insert() --> "
							+ e.getMessage());
			_msApprovalTransVO.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	public static void MinReq(MsApprovalTransVO VO) {

		String strproc_name = "{call Proc_Min(?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd", "transactions.MsApprovalTransDAO.MinReq()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value

			dao.setProcOutValue(nprocIndex, "err", 1,1); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "resultset", 2,2); // 2 for object
			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				// set values into VO
				while (ws.next()) {

					VO.setStrCrNo(ws.getString(1));

				}
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			VO.setStrMsgString("MsApprovalTransDAO.MinReq() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	/* update method for ward allotement in MSAPPROVAL */
	/* update method for ward allotement in BOOKING DETAIL */
	/* update method for ward allotement IN WARD ROOM BED MST */

	public static void savewardallotement(MsApprovalTransVO _msApprovalTransVO) {
		String strProcName1 = "{call pkg_ipd_dml.Proc_Ms_Approval_Dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		int nProcIndex1 = 0;
		String strErr = "";
		HisDAO daoObj = null;
		IpdConfigUtil ipdConfig = null;
		try {
			ipdConfig = new IpdConfigUtil(_msApprovalTransVO.getStrHospitalCode());
			daoObj = new HisDAO("MS Approval Trans", "MsApprovalTransDAO");
			ResourceBundle mstRes = ResourceBundle
					.getBundle("ipd.hisIpdProperties");
			String strApprovalStatusCancel = mstRes
					.getString("Approval_Status_Cancel");
			String strWardType = mstRes.getString("Ward_Type");
			nProcIndex1 = daoObj.setProcedure(strProcName1);

			String strBlockedExpiryTime = ipdConfig.getStrBlockedExpiryTime();

			daoObj.setProcInValue(nProcIndex1, "modeval", "4",1);
			daoObj.setProcInValue(nProcIndex1, "puk", _msApprovalTransVO.getStrCrNo(),2); 
			daoObj.setProcInValue(nProcIndex1, "advice_no", _msApprovalTransVO.getStrAdviceNo(),3); 
			daoObj.setProcInValue(nProcIndex1, "ward_type", _msApprovalTransVO.getStrWardCode().replace("^", "#").split("#")[1],4);
			daoObj.setProcInValue(nProcIndex1, "preference_admdt","",5);
			daoObj.setProcInValue(nProcIndex1, "deptunit_code","",6);
			daoObj.setProcInValue(nProcIndex1, "pat_incom", "",7);
			daoObj.setProcInValue(nProcIndex1, "govttype", "",8);
			daoObj.setProcInValue(nProcIndex1, "remarks", "",9);
			daoObj.setProcInValue(nProcIndex1, "seatid", _msApprovalTransVO.getStrSeatId(),10); 
			daoObj.setProcInValue(nProcIndex1, "form_no", "",11);
			daoObj.setProcInValue(nProcIndex1, "hosp_code", _msApprovalTransVO.getStrHospitalCode(),12); 
			daoObj.setProcInValue(nProcIndex1, "veri_by","",13);
			daoObj.setProcInValue(nProcIndex1, "veri_date", "",14);
			daoObj.setProcInValue(nProcIndex1, "wait_period", "",15);
			daoObj.setProcInValue(nProcIndex1, "hipnum_admno","",16);
			daoObj.setProcInValue(nProcIndex1, "req_date", "",17);
			daoObj.setProcInValue(nProcIndex1, "episode_code","",18);
			daoObj.setProcInValue(nProcIndex1, "relation_code","",19);
			daoObj.setProcInValue(nProcIndex1, "emp_no", "",20);
			daoObj.setProcInValue(nProcIndex1, "Emp_Name", "",21);
			daoObj.setProcInValue(nProcIndex1, "desig", "",22);
			daoObj.setProcInValue(nProcIndex1, "office_name", "",23);
			daoObj.setProcInValue(nProcIndex1, "office_add1", "",24);
			daoObj.setProcInValue(nProcIndex1, "office_add2", "",25);
			daoObj.setProcInValue(nProcIndex1, "office_city", "",26);
			daoObj.setProcInValue(nProcIndex1, "office_state","",27);
			daoObj.setProcInValue(nProcIndex1, "isgovt_emp", "",28);
			daoObj.setProcInValue(nProcIndex1, "basic_pay", "",29);
			daoObj.setProcInValue(nProcIndex1, "org_type", "",30);
			daoObj.setProcInValue(nProcIndex1, "offtel_no", "",31);
			daoObj.setProcInValue(nProcIndex1, "occup_id","",32);
			daoObj.setProcInValue(nProcIndex1, "desig_code","",33);
			//used in approval
			daoObj.setProcInValue(nProcIndex1, "approve_by","",34);
			daoObj.setProcInValue(nProcIndex1, "app_status",strApprovalStatusCancel,35);
			daoObj.setProcInValue(nProcIndex1, "app_remarks","",36);
			//used in ward allotement			
			daoObj.setProcInValue(nProcIndex1, "ward_code", _msApprovalTransVO.getStrWardCode().replace("^", "#").split("#")[0],37); 
			daoObj.setProcInValue(nProcIndex1, "room_no",_msApprovalTransVO.getStrRoomNo(),38);
			daoObj.setProcInValue(nProcIndex1, "bed_code",_msApprovalTransVO.getStrBedCode(),39);
			daoObj.setProcInValue(nProcIndex1, "block_bed_expiry",strBlockedExpiryTime,40);
			//used in cancel
			daoObj.setProcInValue(nProcIndex1, "bed_type","",41);
			daoObj.setProcInValue(nProcIndex1, "room_type","",42);
			daoObj.setProcInValue(nProcIndex1, "cancel_by","",43);
			daoObj.setProcInValue(nProcIndex1, "cancel_rmks","",44);
			daoObj.setProcOutValue(nProcIndex1, "err", 1,45);
		
			daoObj.execute(nProcIndex1, 1);

			synchronized (daoObj) {
				daoObj.fire();
			}
			strErr = daoObj.getString(nProcIndex1, "err");

			if (strErr == null)
				strErr = "";
			if (strErr.equals("")) {
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			//e.printStackTrace();
			_msApprovalTransVO
					.setStrMsgString("MsApprovalTransDAO.savewardallotement() --> "
							+ e.getMessage());
			_msApprovalTransVO.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
			ipdConfig = null;
		}

	}

	/*
	 * public static boolean savewardallotement(MsApprovalTransVO VO) {
	 * System.out.println("inside dao INSERT"); boolean fRetValue = true; String
	 * strProcName1 = "{call Proc_Updatemsapprovaldtl1(?,?,?,?,?,?,?)}"; String
	 * strProcName2 = "{call Proc_Updatewardroombedmst(?,?,?,?,?,?,?)}"; String
	 * strProcName3 = "{call Proc_Updatebookingdtl(?,?,?,?,?,?)}";
	 * 
	 * int nProcIndex1 = 0; int nProcIndex2 = 0; int nProcIndex3 = 0; String
	 * strErr1 = ""; String strErr2 = ""; String strErr3 =""; HisDAO daoObj =
	 * null;
	 * 
	 * try { daoObj = new HisDAO("MS Approval Trans", "MsApprovalTransDAO");
	 * 
	 * nProcIndex1 = daoObj.setProcedure(strProcName1); /*
	 * 
	 * pref_bedtype
	 * 
	 * 
	 * 
	 * System.out.println(" VO.getStrprivateward()[i]==" + VO
	 * .getStrprivateward()); //System.out.println(" VO.getStrAdviceNo()==" +
	 * VO.getHstrAdviceNo()); //System.out.println(" VO.getStrBooking_date()==" // +
	 * VO.getHstrBookDate()); System.out.println(" VO.getStrCrNo()=="
	 * +VO.getStrCrNo()); System.out.println(" VO.getStrbedno()[i]==" +
	 * VO.getStrbedno()); System.out.println("VO.getStrroom()[i]==" +
	 * VO.getStrroom());
	 * 
	 * daoObj.setProcInValue(nProcIndex1, "ward_code", VO .getStrprivateward()); //
	 * daoObj.setProcInValue(nProcIndex1, "advice_no", // "1040800002");
	 * //daoObj.setProcInValue(nProcIndex1, "advice_no", VO.getHstrAdviceNo()); //
	 * daoObj.setProcInValue(nProcIndex1, "booking_date", // "14-Feb-2008");
	 * //daoObj.setProcInValue(nProcIndex1, "booking_date",
	 * VO.getHstrBookDate()); daoObj.setProcInValue(nProcIndex1,
	 * "puk",VO.getStrCrNo()); daoObj.setProcInValue(nProcIndex1, "bed_code",
	 * VO.getStrbedno());
	 * 
	 * daoObj.setProcInValue(nProcIndex1, "room_no", VO.getStrroom());
	 * daoObj.setProcInValue(nProcIndex1, "hospital_code", "108");
	 * 
	 * daoObj.setProcInValue(nProcIndex1, "approve_status", "2");//CONFIRMED
	 * 
	 * daoObj.setProcOutValue(nProcIndex1, "err", 1);
	 * 
	 * daoObj.execute(nProcIndex1, 1);
	 * 
	 * 
	 * 
	 * 
	 * nProcIndex2 = daoObj.setProcedure(strProcName2);
	 * 
	 * 
	 * 
	 * 
	 * System.out.println("3 proc
	 * :VO.getStrprivateward()[i]=="+VO.getStrprivateward());
	 * System.out.println("3 proc :VO.getStrroom()[i]=="+VO.getStrroom());
	 * System.out.println("3 proc :VO.getStrbedno()[i]=="+VO.getStrbedno());
	 * 
	 * daoObj.setProcInValue(nProcIndex2, "booking_flag ", "1");// booked // bed
	 * daoObj.setProcInValue(nProcIndex2, "bed_status", "13");// blocked // bed
	 * daoObj.setProcInValue(nProcIndex2, "ward_code", VO .getStrprivateward());
	 * daoObj.setProcInValue(nProcIndex2, "room_code", VO.getStrroom());
	 * daoObj.setProcInValue(nProcIndex2, "bed_code", VO.getStrbedno());
	 * daoObj.setProcInValue(nProcIndex2, "hosp_code", "108");
	 * 
	 * 
	 * 
	 * daoObj.setProcOutValue(nProcIndex2, "err", 1); // strOccOffPhNo
	 * 
	 * daoObj.execute(nProcIndex2, 1);
	 * 
	 * 
	 * nProcIndex3 = daoObj.setProcedure(strProcName3);
	 * 
	 * 
	 * 
	 * 
	 * daoObj.setProcInValue(nProcIndex3, "ward_code", VO .getStrprivateward());
	 * daoObj.setProcInValue(nProcIndex3, "room_no", VO.getStrroom());
	 * daoObj.setProcInValue(nProcIndex3, "bed_code", VO.getStrbedno()); //
	 * daoObj.setProcInValue(nProcIndex3, "booking_date", VO.getHstrBookDate());
	 * daoObj.setProcInValue(nProcIndex3, "puk", VO.getStrCrNo());
	 * //daoObj.setProcInValue(nProcIndex3, "advice_no", VO.getHstrAdviceNo());
	 * daoObj.setProcInValue(nProcIndex3, "hosp_code", "108");
	 * 
	 * 
	 * daoObj.setProcOutValue(nProcIndex3, "err", 1); // strOccOffPhNo
	 * 
	 * 
	 * 
	 * 
	 * daoObj.execute(nProcIndex3, 1); /* nProcIndex3 =
	 * daoObj.setProcedure(strProcName3);
	 * 
	 * for( i= 0;i<= VO.getStrprivateward().length;i++ ) {
	 * daoObj.setProcInValue(nProcIndex2, "ward_type_code", "11");//private ward
	 * daoObj.setProcInValue(nProcIndex2,
	 * "ward_code",VO.getStrprivateward()[i]);//blocked bed
	 * daoObj.setProcInValue(nProcIndex2, "bed_type_code", "11");
	 * daoObj.setProcInValue(nProcIndex2, "room_type_code", "11");
	 * daoObj.setProcInValue(nProcIndex2, "bed_code", VO.getStrbedno()[i]);
	 * 
	 * daoObj.setProcInValue(nProcIndex2, "room_no",VO.getStrroom()[i] );
	 * daoObj.setProcInValue(nProcIndex2, "puk",VO.getStrCrNo());
	 * 
	 * daoObj.setProcInValue(nProcIndex2, "advice_no",VO.getStrAdviceNo() );
	 * daoObj.setProcInValue(nProcIndex2, "booking_date",
	 * VO.getStrBooking_date() );daoObj.setProcInValue(nProcIndex2,
	 * "hosp_code","108" );
	 * 
	 * daoObj.setProcOutValue(nProcIndex2, "err", 1); }
	 * 
	 * 
	 * synchronized (daoObj) { daoObj.fire(); } strErr1 =
	 * daoObj.getString(nProcIndex1, "err");
	 * 
	 * if (strErr1 == null) strErr1 = ""; if (strErr1.equals("")) { } else {
	 * throw new Exception(strErr1); } strErr2 = daoObj.getString(nProcIndex1,
	 * "err"); if (strErr2 == null) strErr2 = ""; if (strErr2.equals("")) { }
	 * else { throw new Exception(strErr2); }
	 * 
	 * strErr3 = daoObj.getString(nProcIndex3, "err"); if (strErr3 == null)
	 * strErr3 = ""; if (strErr3.equals("")) { } else { throw new
	 * Exception(strErr3); } } catch (Exception e) {
	 * 
	 * fRetValue = false;
	 * 
	 * VO.setStrMsgString("MsApprovalTransDAO.insert() --> " + e.getMessage());
	 * VO.setStrMsgType("1");
	 * 
	 * e.printStackTrace(); } finally { if (daoObj != null) { daoObj.free();
	 * daoObj = null; } }
	 * 
	 * System.out.println("fRetValue=="+fRetValue); return fRetValue; }
	 * 
	 */
	/*
	public boolean wardallotementmsapproval(MsApprovalTransVO VO, HisDAO daoObj)

	{
		// System.out.println("inside wardallotementmsapproval=== ");

		boolean fretVal;
		String strProcName1 = "{call Proc_Updatemsapprovaldtl1(?,?,?,?,?,?,?,?)}";
		int nProcIndex1 = 0;
		// String strerr = "";

		// WebRowSet ws = null;

		try

		{
			// daoObj = new HisDAO("MS Approval Trans", "MsApprovalTransDAO");

			nProcIndex1 = daoObj.setProcedure(strProcName1);

			
			daoObj.setProcInValue(nProcIndex1, "ward_code", VO
					.getStrprivateward());

			daoObj.setProcInValue(nProcIndex1, "puk", VO.getStrCrNo());
			daoObj.setProcInValue(nProcIndex1, "bed_code", VO.getStrbedno());

			daoObj.setProcInValue(nProcIndex1, "room_no", VO.getStrroom());
			daoObj.setProcInValue(nProcIndex1, "hospital_code", "108");

			daoObj.setProcInValue(nProcIndex1, "approve_status", "2");// CONFIRMED
			daoObj.setProcInValue(nProcIndex1, "ward_type", "11");// Private
			// Ward

			daoObj.setProcOutValue(nProcIndex1, "err", 1);

			daoObj.execute(nProcIndex1, 1);

			fretVal = true;
		}

		catch (Exception e) {

			new HisException("", "", "IPD-->MsApprovalTransDAO() --> "

			+ e.getMessage());

			fretVal = false;

		}
		// System.out.println("fretVal in wardallotementmsapproval =="+fretVal);
		return fretVal;

	}

	public boolean wardallotementBookingDtl(MsApprovalTransVO VO, HisDAO daoObj)

	{

		boolean fretVal;

		String strProcName1 = "{call  Proc_Updatebookingdtl(?,?,?,?,?,?)}";
		int nProcIndex1 = 0;
		// String strerr = "";

		// WebRowSet ws = null;

		try

		{
			nProcIndex1 = daoObj.setProcedure(strProcName1);
			daoObj.setProcInValue(nProcIndex1, "ward_code", VO
					.getStrprivateward());
			daoObj.setProcInValue(nProcIndex1, "room_no", VO.getStrroom());
			daoObj.setProcInValue(nProcIndex1, "bed_code", VO.getStrbedno());
			// daoObj.setProcInValue(nProcIndex3, "booking_date",
			// VO.getHstrBookDate());
			daoObj.setProcInValue(nProcIndex1, "puk", VO.getStrCrNo());
			// daoObj.setProcInValue(nProcIndex3, "advice_no",
			// VO.getHstrAdviceNo());
			daoObj.setProcInValue(nProcIndex1, "hosp_code", "108");

			daoObj.setProcOutValue(nProcIndex1, "err", 1);
			// execute procedure
			daoObj.execute(nProcIndex1, 1);
			fretVal = true;

		}

		catch (Exception e)

		{
			// e.printStackTrace();
			new HisException("", "",
					"MsApprovalTransDAO.wardallotementBookingDtl () --> "

					+ e.getMessage());

			fretVal = false;

		}
		// System.out.println("fretVal in wardallotementBookingDtl=="+fretVal);
		return fretVal;

	}

	public boolean wardallotementWardRoomBed(MsApprovalTransVO VO, HisDAO daoObj)

	{

		// System.out.println(" inside wardallotementWardRoomBed");
		boolean fretVal;
		// String proc_name1 = "";
		String strProcName1 = "{call Proc_Updatewardroombedmst(?,?,?,?,?,?,?)}";
		int nProcIndex1 = 0;
		// String strerr = "";

		// WebRowSet ws = null;

		try

		{

			nProcIndex1 = daoObj.setProcedure(strProcName1);

			
			daoObj.setProcInValue(nProcIndex1, "booking_flag ", "1");// booked
			// bed
			daoObj.setProcInValue(nProcIndex1, "bed_status", "12");// occupy
			// bed
			daoObj.setProcInValue(nProcIndex1, "ward_code", VO
					.getStrprivateward());
			daoObj.setProcInValue(nProcIndex1, "room_code", VO.getStrroom());
			daoObj.setProcInValue(nProcIndex1, "bed_code", VO.getStrbedno());
			daoObj.setProcInValue(nProcIndex1, "hosp_code", "108");

			daoObj.setProcOutValue(nProcIndex1, "err", 1);

			// execute procedure

			daoObj.execute(nProcIndex1, 1);

			fretVal = true;

		}

		catch (Exception e)

		{

			new HisException("", "",
					"MsApprovalTransDAO.wardallotementWardRoomBed () --> "

					+ e.getMessage());

			fretVal = false;

		}
		// System.out.println("fretVal in wardallotementWardRoomBed=="+fretVal);
		return fretVal;

	}
*/
	public static void update(MsApprovalTransVO _msApprovalTransVO) {
		String strProcName = "{call pkg_ipd_dml.proc_ms_approval_dtl(?::character varying,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;

		try {
			daoObj = new HisDAO("MS Approval Trans", "MsApprovalTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "2",1);
			daoObj.setProcInValue(nProcIndex, "puk", _msApprovalTransVO.getStrCrNo(),2);
			daoObj.setProcInValue(nProcIndex, "advice_no", _msApprovalTransVO.getStrAdviceNo(),3);
			daoObj.setProcInValue(nProcIndex, "ward_type", _msApprovalTransVO.getStrWard().replace("^", "#").split("#")[1],4);
			daoObj.setProcInValue(nProcIndex, "preference_admdt",_msApprovalTransVO.getStrPropAdminssionDate(),5);
			daoObj.setProcInValue(nProcIndex, "deptunit_code",_msApprovalTransVO.getStrDeptUnitCmb(),6);
			daoObj.setProcInValue(nProcIndex, "pat_incom", _msApprovalTransVO.getStrOccBasic(),7);
			daoObj.setProcInValue(nProcIndex, "govttype", _msApprovalTransVO.getStrGovtType(),8);
			daoObj.setProcInValue(nProcIndex, "remarks", _msApprovalTransVO.getStrRemark(),9);
			daoObj.setProcInValue(nProcIndex, "seatid", _msApprovalTransVO.getStrSeatId(),10);
			daoObj.setProcInValue(nProcIndex, "form_no", _msApprovalTransVO.getStrFormNo(),11);
			daoObj.setProcInValue(nProcIndex, "hosp_code", _msApprovalTransVO.getStrHospitalCode(),12);
			daoObj.setProcInValue(nProcIndex, "veri_by", _msApprovalTransVO.getStrVerifiedBy(),13);
			daoObj.setProcInValue(nProcIndex, "veri_date", _msApprovalTransVO.getStrVerifiedDate(),14);
			daoObj.setProcInValue(nProcIndex, "wait_period", _msApprovalTransVO.getStrWaitPeriod(),15);
			daoObj.setProcInValue(nProcIndex, "hipnum_admno",_msApprovalTransVO.getStrAdmNo(),16);
			daoObj.setProcInValue(nProcIndex, "req_date", _msApprovalTransVO.getStrRequestDate(),17);
			daoObj.setProcInValue(nProcIndex, "episode_code",_msApprovalTransVO.getStrEpisodeNumber(),18);
			daoObj.setProcInValue(nProcIndex, "relation_code",_msApprovalTransVO.getStrOccRelation(),19);
			daoObj.setProcInValue(nProcIndex, "emp_no", _msApprovalTransVO.getStrOccEmpNo(),20);
			daoObj.setProcInValue(nProcIndex, "Emp_Name", _msApprovalTransVO.getStrOccEmpName(),21);
			daoObj.setProcInValue(nProcIndex, "desig", _msApprovalTransVO.getStrOccDesc(),22);
			daoObj.setProcInValue(nProcIndex, "office_name", _msApprovalTransVO.getStrOccOffName(),23);
			daoObj.setProcInValue(nProcIndex, "office_add1", _msApprovalTransVO.getStrOccAdd1(),24);
			daoObj.setProcInValue(nProcIndex, "office_add2", _msApprovalTransVO.getStrOccAdd2(),25);
			daoObj.setProcInValue(nProcIndex, "office_city", _msApprovalTransVO.getStrOccCity(),26);
			daoObj.setProcInValue(nProcIndex, "office_state",_msApprovalTransVO.getStrOccState(),27);
			daoObj.setProcInValue(nProcIndex, "isgovt_emp", _msApprovalTransVO.getStrOccIsGovtEmp(),28);
			daoObj.setProcInValue(nProcIndex, "basic_pay", _msApprovalTransVO.getStrOccBasic(),29);
			daoObj.setProcInValue(nProcIndex, "org_type", _msApprovalTransVO.getStrOccOrgType(),30);
			daoObj.setProcInValue(nProcIndex, "offtel_no", _msApprovalTransVO.getStrOccOffPhNo(),31);
			daoObj.setProcInValue(nProcIndex, "occup_id", _msApprovalTransVO.getStrOccID(),32);
			daoObj.setProcInValue(nProcIndex, "desig_code","",33);
			daoObj.setProcInValue(nProcIndex, "approve_by","",34);
			daoObj.setProcInValue(nProcIndex, "app_status","",35);
			daoObj.setProcInValue(nProcIndex, "app_remarks","",36);
			daoObj.setProcInValue(nProcIndex, "ward_code",_msApprovalTransVO.getStrWard().replace("^", "#").split("#")[0],37);
			daoObj.setProcInValue(nProcIndex, "room_no","",38);
			daoObj.setProcInValue(nProcIndex, "bed_code","",39);
			daoObj.setProcInValue(nProcIndex, "block_bed_expiry","",40);
			daoObj.setProcInValue(nProcIndex, "bed_type","",41);
			daoObj.setProcInValue(nProcIndex, "room_type","",42);
			daoObj.setProcInValue(nProcIndex, "cancel_by","",43);
			daoObj.setProcInValue(nProcIndex, "cancel_rmks","",44);
			daoObj.setProcOutValue(nProcIndex, "err", 1,45);
			
			daoObj.setProcInValue(nProcIndex, "priority_type",_msApprovalTransVO.getStrPriorityType(),46);
			daoObj.setProcInValue(nProcIndex, "urgent_reason",_msApprovalTransVO.getStrUrgentTypeReason(),47);
			daoObj.setProcInValue(nProcIndex, "urgent_remarks",_msApprovalTransVO.getStrUrgentTypeRemarks(),48);
			 
			
			
			
			
			
			
			
			
			daoObj.execute(nProcIndex, 1);

			synchronized (daoObj) {
				daoObj.fire();
			}
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";
			if (strErr.equals("")) {

			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {

			_msApprovalTransVO
					.setStrMsgString("MsApprovalTransDAO.insert() --> "
							+ e.getMessage());
			_msApprovalTransVO.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/*
	 * public static void setPriority(MsApprovalTransVO VO) {
	 * 
	 * String strProcName1 = "{call
	 * pkg_ipd_M.proc_updatebookingdtl(?,?,?,?,?,?,?,?,?)}"; String strProcName2 =
	 * "{call pkg_ipd_M.proc_updatewardroombedmst(?,?,?,?,?)}"; String
	 * strProcName3 = "{call
	 * pkg_ipd_M.proc_updatemsapprovaldtl1(?,?,?,?,?,?,?)}"; int nProcIndex1 =
	 * 0; int nProcIndex2 = 0; int nProcIndex3 = 0; String strErr1 = ""; String
	 * strErr2 = ""; String strErr3 = ""; HisDAO daoObj = null;
	 * 
	 * System.out.println("WARD CODE VALUE IN SET PRIORITY==" +
	 * VO.getStrprivatewardValue()); try { daoObj = new HisDAO("MS Approval
	 * Trans", "MsApprovalTransDAO");
	 * 
	 * System.out.println("VO.getStrprivatewardValue() ==" +
	 * VO.getStrprivatewardName()); System.out.println(" VO.getHstrbedType() ==" +
	 * VO.getHstrbedType()); System.out .println("VO.getHstrRoomType() ==" +
	 * VO.getHstrRoomType()); System.out .println("VO.getStrPrivateBed() ==" +
	 * VO.getHstrbedcode()); System.out.println("VO.getStrCrNo() ==" +
	 * VO.getHcrno()); System.out.println("VO.getStrAdviceNo() ==" +
	 * VO.getHadvno()); System.out.println("booking_date ==" + VO.getHbkdate());
	 * System.out.println("room name ==" + VO.getStrRoomName());
	 * 
	 * nProcIndex1 = daoObj.setProcedure(strProcName1);
	 * daoObj.setProcInValue(nProcIndex1, "ward_type_code", "11"); // for //
	 * private // ward daoObj.setProcInValue(nProcIndex1, "ward_code", VO
	 * .getStrprivatewardName()); daoObj.setProcInValue(nProcIndex1,
	 * "bed_type_code", VO .getHstrbedType());
	 * daoObj.setProcInValue(nProcIndex1, "room_type_code", VO
	 * .getHstrRoomType()); daoObj.setProcInValue(nProcIndex1, "bed_code",
	 * VO.getHstrbedcode()); daoObj.setProcInValue(nProcIndex1, "puk",
	 * VO.getHcrno()); daoObj.setProcInValue(nProcIndex1, "advice_no",
	 * VO.getHadvno()); daoObj.setProcInValue(nProcIndex1, "booking_date",
	 * VO.getHbkdate()); daoObj.setProcOutValue(nProcIndex1, "err", 1);
	 * 
	 * daoObj.execute(nProcIndex1, 1);
	 * 
	 * nProcIndex2 = daoObj.setProcedure(strProcName2);
	 * 
	 * System.out.println("VO.getStrRoomValue()==" + VO.getStrRoomName());
	 * 
	 * daoObj.setProcInValue(nProcIndex2, "bed_status", "1");// for // booked //
	 * room daoObj.setProcInValue(nProcIndex2, "ward_code", VO
	 * .getStrprivatewardName()); daoObj .setProcInValue(nProcIndex2,
	 * "room_code", VO .getStrRoomName()); daoObj.setProcInValue(nProcIndex2,
	 * "bed_code", VO.getHstrbedcode()); daoObj.setProcOutValue(nProcIndex2,
	 * "err", 1);
	 * 
	 * daoObj.execute(nProcIndex2, 1);
	 * 
	 * nProcIndex3 = daoObj.setProcedure(strProcName3);
	 * System.out.println("VO.getStrPriority() ==" + VO.getStrPriority());
	 * 
	 * daoObj.setProcInValue(nProcIndex3, "priority_code", VO
	 * .getStrPriority()); daoObj.setProcInValue(nProcIndex3, "priority_reason",
	 * VO .getStrPriorityReason()); daoObj.setProcInValue(nProcIndex3,
	 * "order_by", VO.getStrOrderBy()); daoObj.setProcInValue(nProcIndex3,
	 * "puk", VO.getHcrno()); daoObj.setProcInValue(nProcIndex3, "advice_no",
	 * VO.getHadvno()); daoObj.setProcInValue(nProcIndex3, "booking_date",
	 * VO.getHbkdate()); daoObj.setProcOutValue(nProcIndex3, "err", 1);
	 * daoObj.execute(nProcIndex3, 1);
	 * 
	 * synchronized (daoObj) { daoObj.fire(); } strErr1 =
	 * daoObj.getString(nProcIndex1, "err");
	 * 
	 * if (strErr1 == null) strErr1 = ""; if (strErr1.equals("")) { } else {
	 * throw new Exception(strErr1); } strErr2 = daoObj.getString(nProcIndex1,
	 * "err"); if (strErr2 == null) strErr2 = ""; if (strErr2.equals("")) { }
	 * else { throw new Exception(strErr2); } strErr3 =
	 * daoObj.getString(nProcIndex3, "err"); if (strErr3 == null) strErr3 = "";
	 * if (strErr3.equals("")) { } else { throw new Exception(strErr3); } }
	 * catch (Exception e) {
	 * 
	 * VO.setStrMsgString("MsApprovalTransDAO.setPriority() --> " +
	 * e.getMessage()); VO.setStrMsgType("1");
	 * 
	 * e.printStackTrace(); } finally { if (daoObj != null) { daoObj.free();
	 * daoObj = null; } } }
	 */

	// update approval status as approved
	public static void updatemsapproval(MsApprovalTransVO _msApprovalTransVO) {
		String strProcName1 = "{call pkg_ipd_dml.Proc_Ms_Approval_Dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		int nProcIndex1 = 0;
		String strErr = "";
		HisDAO daoObj = null;
		String strlengh = _msApprovalTransVO.getHrowlengthApproval();
		int nlength;
		try {
			nlength = Integer.parseInt(strlengh);
			daoObj = new HisDAO("MS Approval Trans", "MsApprovalTransDAO");
			for (int i = 0; i < nlength; i++) {
				nProcIndex1 = daoObj.setProcedure(strProcName1);
				daoObj.setProcInValue(nProcIndex1, "modeval", "3",1);
				daoObj.setProcInValue(nProcIndex1, "puk",  _msApprovalTransVO.getHcrnoArr()[i],2); 
				daoObj.setProcInValue(nProcIndex1, "advice_no", _msApprovalTransVO.getHadvnoArr()[i],3); 
				daoObj.setProcInValue(nProcIndex1, "ward_type", "",4);
				daoObj.setProcInValue(nProcIndex1, "preference_admdt","",5);
				daoObj.setProcInValue(nProcIndex1, "deptunit_code","",6);
				daoObj.setProcInValue(nProcIndex1, "pat_incom", "",7);
				daoObj.setProcInValue(nProcIndex1, "govttype", "",8);
				daoObj.setProcInValue(nProcIndex1, "remarks", "",9);
				daoObj.setProcInValue(nProcIndex1, "seatid", "",10);
				daoObj.setProcInValue(nProcIndex1, "form_no", "",11);
				daoObj.setProcInValue(nProcIndex1, "hosp_code", _msApprovalTransVO.getStrHospitalCode(),12); 
				daoObj.setProcInValue(nProcIndex1, "veri_by","",13);
				daoObj.setProcInValue(nProcIndex1, "veri_date", "",14);
				daoObj.setProcInValue(nProcIndex1, "wait_period", "",15);
				daoObj.setProcInValue(nProcIndex1, "hipnum_admno","",16);
				daoObj.setProcInValue(nProcIndex1, "req_date", "",17);
				daoObj.setProcInValue(nProcIndex1, "episode_code","",18);
				daoObj.setProcInValue(nProcIndex1, "relation_code","",19);
				daoObj.setProcInValue(nProcIndex1, "emp_no", "",20);
				daoObj.setProcInValue(nProcIndex1, "Emp_Name", "",21);
				daoObj.setProcInValue(nProcIndex1, "desig", "",22);
				daoObj.setProcInValue(nProcIndex1, "office_name", "",23);
				daoObj.setProcInValue(nProcIndex1, "office_add1", "",24);
				daoObj.setProcInValue(nProcIndex1, "office_add2", "",25);
				daoObj.setProcInValue(nProcIndex1, "office_city", "",26);
				daoObj.setProcInValue(nProcIndex1, "office_state","",27);
				daoObj.setProcInValue(nProcIndex1, "isgovt_emp", "",28);
				daoObj.setProcInValue(nProcIndex1, "basic_pay", "",29);
				daoObj.setProcInValue(nProcIndex1, "org_type", "",30);
				daoObj.setProcInValue(nProcIndex1, "offtel_no", "",31);
				daoObj.setProcInValue(nProcIndex1, "occup_id","",32);
				daoObj.setProcInValue(nProcIndex1, "desig_code","",33);
				//used in approval
				daoObj.setProcInValue(nProcIndex1, "approve_by",_msApprovalTransVO.getStrApprovedBY(),34);
				daoObj.setProcInValue(nProcIndex1, "app_status",_msApprovalTransVO.getStrApprovedStatus(),35);
				daoObj.setProcInValue(nProcIndex1, "app_remarks",_msApprovalTransVO.getStrRemark(),36);
				//used in ward allotement			
				daoObj.setProcInValue(nProcIndex1, "ward_code","",37);
				daoObj.setProcInValue(nProcIndex1, "room_no","",38);
				daoObj.setProcInValue(nProcIndex1, "bed_code","",39);
				daoObj.setProcInValue(nProcIndex1, "block_bed_expiry","",40);
				//used in cancel
				daoObj.setProcInValue(nProcIndex1, "bed_type","",41);
				daoObj.setProcInValue(nProcIndex1, "room_type","",42);
				daoObj.setProcInValue(nProcIndex1, "cancel_by","",43);
				daoObj.setProcInValue(nProcIndex1, "cancel_rmks","",44);
				daoObj.setProcOutValue(nProcIndex1, "err", 1,45);
				
				daoObj.execute(nProcIndex1, 1);
			}

			synchronized (daoObj) {
				daoObj.fire();
			}
			strErr = daoObj.getString(nProcIndex1, "err");

			if (strErr == null)
				strErr = "";
			if (strErr.equals("")) {
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			_msApprovalTransVO
					.setStrMsgString("MsApprovalTransDAO.updatemsapproval() --> "
							+ e.getMessage());
			_msApprovalTransVO.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
/*
	public static void updatemsapprovalallocation(MsApprovalTransVO VO) {
		// System.out.println("inside dao updatemsapproval");

		String strProcName1 = "{call Proc_UpdatemsapprovalAllocate(?,?,?,?,?,?)}";

		int nProcIndex1 = 0;
		String strErr = "";
		HisDAO daoObj = null;
		String strlengh = VO.getHrowlengthApproval();
		int nlength;
		nlength = Integer.parseInt(strlengh);
		// System.out.println("ROW LENGHT==" + strlengh);
		try {

			// System.out.println("inside tru update");
			daoObj = new HisDAO("MS Approval Trans", "MsApprovalTransDAO");

			for (int i = 0; i < nlength; i++) {

				nProcIndex1 = daoObj.setProcedure(strProcName1);

				daoObj.setProcInValue(nProcIndex1, "ward_code", VO
						.getStrApproveDate());
				daoObj.setProcInValue(nProcIndex1, "bed_code", VO
						.getStrSeatId());
				daoObj.setProcInValue(nProcIndex1, "puk", VO.getHcrno());
				daoObj.setProcInValue(nProcIndex1, "advice_no", VO.getHadvno());
				daoObj.setProcInValue(nProcIndex1, "booking_date", VO
						.getHbkdate());
				daoObj.setProcOutValue(nProcIndex1, "err", 1);
				daoObj.execute(nProcIndex1, 1);
			}

			synchronized (daoObj) {
				daoObj.fire();
			}
			strErr = daoObj.getString(nProcIndex1, "err");

			if (strErr == null)
				strErr = "";
			if (strErr.equals("")) {
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			VO
					.setStrMsgString("MsApprovalTransDAO.updatemsapprovalallocation() --> "
							+ e.getMessage());
			VO.setStrMsgType("1");

			// e.printStackTrace();

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
*/
	// Provide list of Private Ward in which ms approval is required and vacant
	// bed are available
	public static void privateward(MsApprovalTransVO _msApprovalTransVO) {
		String strproc_name = "{call PKG_IPD_VIEW.PROC_HIPT_WARD_MST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			//ResourceBundle mstRes = ResourceBundle.getBundle("ipd.hisIpdProperties");

			//String strWardType = mstRes.getString("Ward_Type");
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.privateward()");
			nprocIndex = dao.setProcedure(strproc_name);

			dao.setProcInValue(nprocIndex, "modeVal", "9",1);	
			dao.setProcInValue(nprocIndex, "wardtypcode", "0",2);
			dao.setProcInValue(nprocIndex, "deptcode", "0",3);
			dao.setProcInValue(nprocIndex, "deptunitcode", _msApprovalTransVO.getStrDeptUnitCode(),4);
			dao.setProcInValue(nprocIndex, "unitcode", _msApprovalTransVO.getStrAgeCode(),5);
			dao.setProcInValue(nprocIndex, "age", _msApprovalTransVO.getStrAge(),6);
			dao.setProcInValue(nprocIndex, "gender", _msApprovalTransVO.getStrSex(),7);
			dao.setProcInValue(nprocIndex, "treatment_cat", _msApprovalTransVO.getStrPatCatCode(),8);
			dao.setProcInValue(nprocIndex, "hosp_code", _msApprovalTransVO.getStrHospitalCode(),9);
			dao.setProcInValue(nprocIndex, "effect_from", "",10);
			dao.setProcInValue(nprocIndex, "effect_to", "",11);
			dao.setProcInValue(nprocIndex, "diseasetypcode", "0",12);
			dao.setProcInValue(nprocIndex, "wardcode", "0",13);
			dao.setProcInValue(nprocIndex, "puk_no", "0",14);
			dao.setProcInValue(nprocIndex, "charge_type_id", "0",15);
			dao.setProcOutValue(nprocIndex, "err", 1,16);
			dao.setProcOutValue(nprocIndex, "resultset", 2,17);
			   
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr.equals("")) {
				_msApprovalTransVO.setStrprivatewardWs(ws);
			} else {
				throw new Exception(strerr);
			}

		} catch (Exception e) {
			_msApprovalTransVO
					.setStrMsgString("MsApprovalTransDAO.privateward() --> "
							+ e.getMessage());
			_msApprovalTransVO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	// provide list of vacant bed on the basis of ward and room
	public static void bed(MsApprovalTransVO _msApprovalTransVO) {
		String strproc_name = "{call PKG_IPD_VIEW.PROC_BED_DTL(?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.proc_bed()");
			nprocIndex = dao.setProcedure(strproc_name);

			ResourceBundle mstRes = ResourceBundle.getBundle("ipd.hisIpdProperties");
			String strBedStatusCodeFree = mstRes.getString("Bed_Status_Code_Free");

			dao.setProcInValue(nprocIndex, "modval", "3",1);
			dao.setProcInValue(nprocIndex, "wardcode", _msApprovalTransVO.getStrWardCode(),2);
			dao.setProcInValue(nprocIndex, "roomno", _msApprovalTransVO.getStrRoomNo(),3);
			dao.setProcInValue(nprocIndex, "bedtypcode", "0",4);
			dao.setProcInValue(nprocIndex, "bedstatcode", strBedStatusCodeFree,5);
			dao.setProcInValue(nprocIndex, "hosp_code", _msApprovalTransVO.getStrHospitalCode(),6);
			dao.setProcInValue(nprocIndex, "deptunit", _msApprovalTransVO.getStrDeptUnitCode(),7);
			dao.setProcInValue(nprocIndex, "bedCode", "",8);
			dao.setProcOutValue(nprocIndex, "err", 1,9);
			dao.setProcOutValue(nprocIndex, "resultset", 2,10);
			dao.executeProcedureByPosition(nprocIndex);

			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr.equals("")) {
				_msApprovalTransVO.setStrBedWs(ws);
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			_msApprovalTransVO.setStrMsgString("MsApprovalTransDAO.Bed() --> "
					+ e.getMessage());
			_msApprovalTransVO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			ws = null;
		}
	}

	// Provide list of room on the basis of ward and in which vacant bed are
	// available
	public static void room(MsApprovalTransVO _msApprovalTransVO) {
		String strproc_name = "{call  PKG_IPD_VIEW.proc_roomconfig(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		String modVal = "3";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd", "transactions.MsApprovalTransDAO.room()");
			nprocIndex = dao.setProcedure(strproc_name);

			System.out.println(_msApprovalTransVO.getStrAge()+" "+_msApprovalTransVO.getStrSex() +" "+_msApprovalTransVO.getStrPatCatCode());
			
			System.out.println(_msApprovalTransVO.getStrprivatewardValue()+" "+_msApprovalTransVO.getStrAgeCode() +" "+_msApprovalTransVO.getStrDeptUnitCode());
			dao.setProcInValue(nprocIndex, "modval", modVal,1);
			dao.setProcInValue(nprocIndex, "roomtypcode", "",2);
			dao.setProcInValue(nprocIndex, "wardcode", _msApprovalTransVO.getStrprivatewardValue(),3);
			dao.setProcInValue(nprocIndex, "hosp_code", _msApprovalTransVO.getStrHospitalCode(),4);
			dao.setProcInValue(nprocIndex, "unitcode", _msApprovalTransVO.getStrAgeCode(),5);
			dao.setProcInValue(nprocIndex, "age", _msApprovalTransVO.getStrAge(),6);
			dao.setProcInValue(nprocIndex, "deptcode", "",7);
			dao.setProcInValue(nprocIndex, "deptunitcode", _msApprovalTransVO.getStrDeptUnitCode(),8);
			dao.setProcInValue(nprocIndex, "gender", _msApprovalTransVO.getStrSex(),9);
			dao.setProcInValue(nprocIndex, "treatment_cat", _msApprovalTransVO.getStrPatCatCode(),10);
			dao.setProcInValue(nprocIndex, "puk_no", "",11);
			dao.setProcInValue(nprocIndex, "diseasetypcode", "0",12);
			dao.setProcOutValue(nprocIndex, "err", 1,13);
			dao.setProcOutValue(nprocIndex, "resultset", 2,14);
			
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr.equals(""))
				_msApprovalTransVO.setStrRoomWs(ws);
			else
				throw new Exception(strerr);
		} catch (Exception e) {
			_msApprovalTransVO.setStrMsgString("MsApprovalTransDAO.room() --> "
					+ e.getMessage());
			_msApprovalTransVO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
/*
	public static void privateroom(MsApprovalTransVO VO) {
		// System.out.println("ward code in privateroom=="
		// + VO.getStrprivatewardValue());
		String strproc_name = "{call pkg_ipd_M.proc__room_dtl(?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd",
					"transactions.MsApprovalTransDAO.privateroom()");
			nprocIndex = dao.setProcedure(strproc_name);

			// set value

			dao.setProcInValue(nprocIndex, "ward_code", VO
					.getStrprivatewardValue());
			dao.setProcOutValue(nprocIndex, "err", 1); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2); // 2 for object

			// execute procedure
			dao.executeProcedure(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "err");
			// System.out.println("err == " + strerr);
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr.equals("")) {
				VO.setStrprivateRoomWs(ws);
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			VO.setStrMsgString("MsApprovalTransDAO.privateroom() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
*/
	/*
	public static void roomdetail(MsApprovalTransVO VO) {

		String strproc_name = "{call pkg_ipd_M.proc_room_detail1(?,?,?,?s)}";
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;
		// System.out.println("ROOM CODE codein ROOM DETAIL=="
		// + VO.getStrRoomValue());
		// System.out.println("ward codein ROOM DETAIL=="
		// + VO.getStrprivatewardValue());
		try {
			dao = new HisDAO("ipd",
					"transactions.MsApprovalTransDAO.roomdetail()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value
			dao.setProcInValue(nprocIndex, "ward_code", VO
					.getStrprivatewardValue());
			dao.setProcInValue(nprocIndex, "room_code", VO.getStrRoomValue());
			dao.setProcOutValue(nprocIndex, "err", 1); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2); // 2 for object

			// execute procedure
			dao.executeProcedure(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "err");
			// System.out.println("err == " + strerr);
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				// set values into VO
				while (ws.next()) {
					// System.out.println("ROOM TYPE==" + ws.getString(1));
					// System.out.println("ws.getString(1)room type=="
					// + ws.getString(1));

					VO.setHstrRoomType(ws.getString(1));
					VO.setStrRoomType(ws.getString(2));
					VO.setStrBuilding(ws.getString(3));
					VO.setStrBlock(ws.getString(4));
					VO.setStrFloor(ws.getString(5));
					VO.setStrPrivateBed(ws.getString(6));
					VO.setHstrbedcode(ws.getString(6));
					VO.setHstrbedType(ws.getString(7));
				}
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			VO.setStrMsgString("MsApprovalTransDAO.roomdetail() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	// proc_ordered_approve_list,proc_bed_tobe_vacant,proc_bed_allocation

	public static WebRowSet orderedapprovedlist(MsApprovalTransVO VO) {
		// public static void orderedapprovedlist(MsApprovalTransVO VO) {
		// System.out.println("orderedapprovedlist");
		String strproc_name = "{call proc_ordered_approve_list(?,?)}";

		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd",
					"transactions.MsApprovalTransDAO.orderedapprovedlist()");
			nprocIndex = dao.setProcedure(strproc_name);

			// set value

			dao.setProcOutValue(nprocIndex, "err", 1); // 1 for string
			// return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2); // 2 for object
			// // execute
			// procedure
			dao.executeProcedure(nprocIndex);
			// get value
			strerr = dao.getString(nprocIndex, "err");

			// System.out.println("err == " + strerr);
			if (strerr == null)
				strerr = "";
			if (strerr.equals("")) {
				ws = dao.getWebRowSet(nprocIndex, "resultset");

			} else
				throw new Exception(strerr);

		} catch (Exception e) {
			VO.setStrMsgString("MsApprovalTransDAO.orderedapprovedlist() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

		return ws;
	}
*/
	public static WebRowSet procbeddtobevacant(MsApprovalTransVO VO) {

		String strproc_name = "{call pkg_ipd_view.proc_bed_tobe_vacant(?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.procbeddtobevacant()");
			nprocIndex = dao.setProcedure(strproc_name);

			// set value
			dao.setProcOutValue(nprocIndex, "err", 1,1); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "resultset", 2,2); // 2 for object

			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";
			if (strerr.equals("")) {
				ws = dao.getWebRowSet(nprocIndex, "resultset");

			} else
				throw new Exception(strerr);

		} catch (Exception e) {
			VO.setStrMsgString("MsApprovalTransDAO.procbeddtobevacant() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
		return ws;

	}

	public static HisDAO myDao = null;

	/*
	 * previous list function // updation in booking detail public static void
	 * generateApproveList(MsApprovalTransVO VO) { System.out.println("inside
	 * dao generateApproveList dao");
	 * 
	 * String strproc_name1 = ""; String strproc_name2 = ""; String
	 * strproc_name3 = "";
	 * 
	 * int nprocIndex1 = 0; int nprocIndex2 = 0; int nprocIndex3 = 0; if
	 * (VO.getStrMode().equals("1")) {
	 * 
	 * System.out.println("inside mode 1");
	 * 
	 * myDao = new HisDAO("MS Approval Trans", "MsApprovalTransDAO");
	 * nprocIndex1 = 0; nprocIndex2 = 0; nprocIndex3 = 0; try { strproc_name1 =
	 * "{call proc_bed_allocation(?,?,?,?,?,?,?,?,?,?)}"; strproc_name2 = "{call
	 * proc_bed_allocation_ms(?,?,?,?,?,?,?,?)}"; strproc_name3 = "{call
	 * proc_updatewardroombedmst(?,?,?,?,?)}"; nprocIndex1 =
	 * myDao.setProcedure(strproc_name1); nprocIndex2 =
	 * myDao.setProcedure(strproc_name2); nprocIndex3 =
	 * myDao.setProcedure(strproc_name3);
	 * 
	 * 
	 * System.out.println("advice_no : " + VO.getStrListAdviceNo()); // set
	 * values for procIndex1-(update in booking detail
	 * myDao.setProcInValue(nprocIndex1, "ward_code ", VO
	 * .getStrListWardcode()); myDao.setProcInValue(nprocIndex1,
	 * "ward_type_code", VO .getStrListWardtype());
	 * myDao.setProcInValue(nprocIndex1, "room_type_code", VO
	 * .getStrListRoomtype()); myDao.setProcInValue(nprocIndex1, "bed_type_code ",
	 * VO .getStrListBedType()); myDao.setProcInValue(nprocIndex1, "bed_code ",
	 * VO .getStrListBedCode()); myDao.setProcInValue(nprocIndex1,
	 * "booking_status", VO .getStrListBookingStatus()); // booked and approved
	 * myDao.setProcInValue(nprocIndex1, "puk", VO.getStrListCrNo());
	 * myDao.setProcInValue(nprocIndex1, "advice_no", VO .getStrListAdviceNo());
	 * myDao.setProcInValue(nprocIndex1, "booking_date", VO
	 * .getStrListBookingDate()); myDao.setProcOutValue(nprocIndex1, "err", 1);
	 * myDao.execute(nprocIndex1, 1); // set values for procIndex2-(update in ms
	 * approval) myDao.setProcInValue(nprocIndex2, "ward_code ", VO
	 * .getStrListWardcode()); myDao.setProcInValue(nprocIndex2,
	 * "ward_type_code", VO .getStrListWardtype());
	 * myDao.setProcInValue(nprocIndex2, "bed_code", VO .getStrListBedCode());
	 * myDao.setProcInValue(nprocIndex2, "approve_status", VO
	 * .getStrListApprovalStatus()); // booked and approved
	 * myDao.setProcInValue(nprocIndex2, "puk", VO.getStrListCrNo());
	 * myDao.setProcInValue(nprocIndex2, "advice_no ", VO
	 * .getStrListAdviceNo()); myDao.setProcInValue(nprocIndex2, "booking_date",
	 * VO .getStrListBookingDate()); myDao.setProcOutValue(nprocIndex2, "err",
	 * 1); myDao.execute(nprocIndex2, 1); // set values for procIndex3-(update
	 * booking flag in Ward Room // Bed ) myDao.setProcInValue(nprocIndex3,
	 * "bed_status", "2");// booking // flag--booked // and // confirmed
	 * myDao.setProcInValue(nprocIndex3, "ward_code", VO .getStrListWardcode()); //
	 * myDao.setProcInValue(nprocIndex3, "room_code", VO .getStrListRoomCode());
	 * myDao.setProcInValue(nprocIndex3, "bed_code ", VO .getStrListBedCode()); //
	 * booked and approved myDao.setProcOutValue(nprocIndex3, "err", 1);
	 * myDao.execute(nprocIndex3, 1); } catch (Exception e) { VO
	 * .setStrMsgString("MsApprovalTransDAO.generateApproveList() --> " +
	 * e.getMessage()); VO.setStrMsgType("1"); } } else if
	 * (VO.getStrMode().equals("2")) {
	 * 
	 * System.out.println("inside mode 2"); try { // set values for
	 * procIndex1-(update in booking detail myDao.setProcInValue(nprocIndex1,
	 * "ward_code ", VO .getStrListWardcode());
	 * myDao.setProcInValue(nprocIndex1, "ward_type_code", VO
	 * .getStrListWardtype()); myDao.setProcInValue(nprocIndex1,
	 * "room_type_code", VO .getStrListRoomtype());
	 * myDao.setProcInValue(nprocIndex1, "bed_type_code ", VO
	 * .getStrListBedType()); myDao.setProcInValue(nprocIndex1, "bed_code ", VO
	 * .getStrListBedCode()); myDao.setProcInValue(nprocIndex1,
	 * "booking_status", VO .getStrListBookingStatus()); // booked and approved
	 * myDao.setProcInValue(nprocIndex1, "puk", VO.getStrListCrNo());
	 * myDao.setProcInValue(nprocIndex1, "advice_no", VO .getStrListAdviceNo());
	 * myDao.setProcInValue(nprocIndex1, "booking_date", VO
	 * .getStrListBookingDate()); myDao.setProcOutValue(nprocIndex1, "err", 1);
	 * myDao.execute(nprocIndex1, 1); // set values for procIndex2-(update in ms
	 * approval) myDao.setProcInValue(nprocIndex2, "ward_code ", VO
	 * .getStrListWardcode()); myDao.setProcInValue(nprocIndex2,
	 * "ward_type_code", VO .getStrListWardtype());
	 * myDao.setProcInValue(nprocIndex2, "bed_code", VO .getStrListBedCode());
	 * myDao.setProcInValue(nprocIndex2, "approve_status", VO
	 * .getStrListApprovalStatus()); // booked and approved
	 * myDao.setProcInValue(nprocIndex2, "puk", VO.getStrListCrNo());
	 * myDao.setProcInValue(nprocIndex2, "advice_no ", VO
	 * .getStrListAdviceNo()); myDao.setProcInValue(nprocIndex2, "booking_date",
	 * VO .getStrListBookingDate()); myDao.setProcOutValue(nprocIndex2, "err",
	 * 1); myDao.execute(nprocIndex2, 1); // set values for procIndex3-(update
	 * booking flag in Ward Room // Bed ) myDao.setProcInValue(nprocIndex3,
	 * "bed_status", "1");// booking // flag myDao.setProcInValue(nprocIndex3,
	 * "ward_code", VO .getStrListWardcode()); //
	 * myDao.setProcInValue(nprocIndex3, "room_code", VO .getStrListRoomCode());
	 * myDao.setProcInValue(nprocIndex3, "bed_code ", VO .getStrListBedCode()); //
	 * booked and approved myDao.setProcOutValue(nprocIndex3, "err", 1);
	 * myDao.execute(nprocIndex3, 1); } catch (Exception e) { VO
	 * .setStrMsgString("MsApprovalTransDAO.generateApproveList() --> " +
	 * e.getMessage()); VO.setStrMsgType("1"); } } else {
	 * 
	 * System.out.println("inside mode 3 " + myDao);
	 * 
	 * try { synchronized (myDao) { myDao.fire(); // } } } catch (Exception e) {
	 * VO .setStrMsgString("MsApprovalTransDAO.generateApproveList() --> " +
	 * e.getMessage()); VO.setStrMsgType("1"); } finally { if (myDao != null) {
	 * myDao.free(); myDao = null; } } } }
	 */

	public boolean generateApproveList(MsApprovalTransVO VO) {

		String strproc_name = "{call MSAPPROVAL_LIST_GEN(?,?,?,?,?)}";
		HisDAO dao = null;
		boolean fretvalue = false;
		int nprocIndex = 0;

		String strerr = "";
		// WebRowSet ws = null;
		try {
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.generateApproveList()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value
			dao.setProcInValue(nprocIndex, "HOSPCODE", VO.getStrHospitalCode());
			dao.setProcInValue(nprocIndex, "path", "c:/msa/");
			dao.setProcInValue(nprocIndex, "seatid", VO.getStrSeatId());
			dao.setProcInValue(nprocIndex, "GENERATE_DATE", VO.getStrListDate());
			dao.setProcOutValue(nprocIndex, "ERR", 1); // 1 for string return value

			// execute procedure
			dao.executeProcedure(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "ERR");
			if (strerr == null)
				strerr = "";
			if (strerr.equals("")) {
				fretvalue = true;
			}

			else {
				throw new Exception(strerr);			}
		} catch (Exception e) {
			VO.setStrMsgString("MsApprovalTransDAO.generateApproveList() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
		return fretvalue;

	}

	// cancel approval --> insert data in booking cancel table and update data
	// in
	// booking detail,ms approval, ward room bed master .
	public static void cancelapproval(MsApprovalTransVO _msApprovalTransVO) {
		String strproc_name = "{call PKG_IPD_DML.Proc_Ms_Approval_Dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";

		try {
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.proc_bed()");
			nprocIndex = dao.setProcedure(strproc_name);
			ResourceBundle mstRes = ResourceBundle.getBundle("ipd.hisIpdProperties");
			String strApprovalStatusCanceled = mstRes.getString("Approval_Status_Canceled");
			
			dao.setProcInValue(nprocIndex, "modeval", "5",1);
			dao.setProcInValue(nprocIndex, "puk", _msApprovalTransVO.getStrCrNo(),2);
			dao.setProcInValue(nprocIndex, "advice_no", _msApprovalTransVO.getStrAdviceNo(),3);
			dao.setProcInValue(nprocIndex, "ward_type", _msApprovalTransVO.getStrWardype(),4);
			dao.setProcInValue(nprocIndex, "preference_admdt",_msApprovalTransVO.getStrPropAdminssionDate(),5);
			dao.setProcInValue(nprocIndex, "deptunit_code","",6);
		    dao.setProcInValue(nprocIndex, "pat_incom", "",7);
			dao.setProcInValue(nprocIndex, "govttype","",8);
			dao.setProcInValue(nprocIndex, "remarks","",9);
			dao.setProcInValue(nprocIndex, "seatid", _msApprovalTransVO.getStrSeatId(),10);
			dao.setProcInValue(nprocIndex, "form_no", "",11);
			dao.setProcInValue(nprocIndex, "hosp_code", _msApprovalTransVO.getStrHospitalCode(),12);
			dao.setProcInValue(nprocIndex, "veri_by","",13);
			dao.setProcInValue(nprocIndex, "veri_date", "",14);
			dao.setProcInValue(nprocIndex, "wait_period","",15);
			dao.setProcInValue(nprocIndex, "hipnum_admno","",16);
			dao.setProcInValue(nprocIndex, "req_date","",17);
			dao.setProcInValue(nprocIndex, "episode_code","",18);
			dao.setProcInValue(nprocIndex, "relation_code","",19);
			dao.setProcInValue(nprocIndex, "emp_no","",20);
			dao.setProcInValue(nprocIndex, "Emp_Name","",21);
			dao.setProcInValue(nprocIndex, "desig", "",22);
			dao.setProcInValue(nprocIndex, "office_name","",23);
			dao.setProcInValue(nprocIndex, "office_add1", "",24);
			dao.setProcInValue(nprocIndex, "office_add2", "",25);
			dao.setProcInValue(nprocIndex, "office_city","",26);
			dao.setProcInValue(nprocIndex, "office_state","",27);
			dao.setProcInValue(nprocIndex, "isgovt_emp","",28);
			dao.setProcInValue(nprocIndex, "basic_pay", "",29);
			dao.setProcInValue(nprocIndex, "org_type","",30);
			dao.setProcInValue(nprocIndex, "offtel_no","",31);
			dao.setProcInValue(nprocIndex, "occup_id", "",32);
			dao.setProcInValue(nprocIndex, "desig_code","",33);
			dao.setProcInValue(nprocIndex, "approve_by","",34);
			dao.setProcInValue(nprocIndex, "app_status",strApprovalStatusCanceled,35);
			dao.setProcInValue(nprocIndex, "app_remarks","",36);
			dao.setProcInValue(nprocIndex, "ward_code",_msApprovalTransVO.getStrWardCode(),37);
			dao.setProcInValue(nprocIndex, "room_no",_msApprovalTransVO.getStrRoomNo(),38);
			dao.setProcInValue(nprocIndex, "bed_code",_msApprovalTransVO.getStrBedCode(),39);
			dao.setProcInValue(nprocIndex, "block_bed_expiry","",40);
			dao.setProcInValue(nprocIndex, "bed_type",_msApprovalTransVO.getStrBedType(),41);
			dao.setProcInValue(nprocIndex, "room_type",_msApprovalTransVO.getStrRoomType(),42);
			dao.setProcInValue(nprocIndex, "cancel_by", _msApprovalTransVO.getStrCancelBy(),43);
			dao.setProcInValue(nprocIndex, "cancel_rmks",_msApprovalTransVO.getStrCancelReason(),44);
			dao.setProcOutValue(nprocIndex, "err", 1,45);
			
			
			dao.execute(nprocIndex, 1);

			synchronized (dao) {
				dao.fire();
			}
			//dao.executeProcedure(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");

			if (!strerr.equals(""))
				throw new Exception(strerr);

		} catch (Exception e) {
			_msApprovalTransVO.setStrMsgString("MsApprovalTransDAO.Bed() --> "
					+ e.getMessage());
			_msApprovalTransVO.setStrMsgType("1");
		} finally {
			if (dao != null)
				dao.free();
			dao = null;
		}
	}

	public static void beddetail(MsApprovalTransVO VO) {
		// String strStatus =VO.getStrPatStatus();
		String strcrNoValue = VO.getHcrno();
		String strAdno = VO.getHadvno();
		String strBookDate = VO.getHbkdate();
		String strproc_name = "";

		strproc_name = "{call proc_beddetail(?,?,?,?,?)}";
		// }
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.beddetail()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value

			dao.setProcInValue(nprocIndex, "pukno", strcrNoValue);
			dao.setProcInValue(nprocIndex, "advno", strAdno);
			dao.setProcInValue(nprocIndex, "bookingdate", strBookDate);
			dao.setProcOutValue(nprocIndex, "err", 1); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "resultset", 2); // 2 for object

			// execute procedure
			dao.executeProcedure(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				// set values into VO
				while (ws.next()) {

					VO.setStrWardype(ws.getString(1));
					VO.setStrWardCode(ws.getString(2));
					VO.setStrRoomType(ws.getString(3));
					VO.setStrBedType(ws.getString(4));
					VO.setStrBedCode(ws.getString(5));

					// VO.setStrWardName(ws.getString(5)); //ward name
					// VO.setStrBedName(ws.getString(6));// bed name
					// VO.setStrWardCode(ws.getString(7));//wardcode
					// VO.setStrBedCode(ws.getString(8));

				}
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			VO.setStrMsgString("MsApprovalTransDAO.beddetail() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	// UPDATE Record in Ms Approval in case Record already exist in table

	public static void updatemode(MsApprovalTransVO VO) {
		String strcrNoValue = VO.getStrCrNo();
		String strAdno = VO.getStrAdviceNo();
	//	String strBookDate = VO.getStrBooking_date();
		String strproc_name = "";

		strproc_name = "{call Proc_Updatemode_MsApprove(?,?,?,?,?)}";
		// }
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.updatemode()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value

			dao.setProcInValue(nprocIndex, "PUKNO", strcrNoValue);
			dao.setProcInValue(nprocIndex, "ADV_NO", strAdno);

			// dao.setProcInValue(nprocIndex, "BOOK_DATE","23-jul-2008");
			dao
					.setProcInValue(nprocIndex, "HOSP_CODE", VO
							.getStrHospitalCode());
			dao.setProcOutValue(nprocIndex, "ERR", 1); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2); // 2 for object

			// execute procedure
			dao.executeProcedure(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "ERR");
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
			if (strerr.equals("")) {
				// set values into VO
				while (ws.next()) {

					/*
					 */// if(ws.getString(1)==null){
					// VO.setStrPropAdminssionDate(VO.getStrPrefAdmDate());
					// }else{
					// VO.setStrPropAdminssionDate(ws.getString(1));
					// }
					// if(ws.getString(2)==null){
					// VO.setStrRequestDate(VO.getStrCtDate());
					// }else{
					// VO.setStrRequestDate(ws.getString(2));
					// }
					VO.setStrPropAdminssionDate(ws.getString(1));
					VO.setStrRequestDate(ws.getString(2));
					VO.setStrRemark(ws.getString(3));
					VO.setStrFormNo(ws.getString(4));
					VO.setStrWaitPeriod(ws.getString(5));
					VO.setStrVerifiedDate(ws.getString(6));

				}
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			VO.setStrMsgString("MsApprovalTransDAO.updatemode() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	public static WebRowSet notapprovedlist(MsApprovalTransVO VO) {
		String strproc_name = "{call proc_notapprovedlist(?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.notapprovedlist()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value

			dao.setProcOutValue(nprocIndex, "err", 1,1); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "resultset", 2,2); // 2 for object

			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";
			if (strerr.equals("")) {

				ws = dao.getWebRowSet(nprocIndex, "resultset");

			}

			else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			VO.setStrMsgString("MsApprovalTransDAO.notapprovedlist() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
		return ws;
	}

	// canel list
/*
	public static WebRowSet strcancelList(MsApprovalTransVO VO) {

		// System.out.println("inside cancellist");
		String strproc_name = "{call proc_cancellist(?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;

		String err = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd",
					"transactions.MsApprovalTransDAO.cancellist)");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value

			dao.setProcOutValue(nprocIndex, "err", 1); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2); // 2 for object

			// execute procedure
			dao.executeProcedure(nprocIndex);
			// System.out.println("after dao.executeProcedure ");
			// get value
			err = dao.getString(nprocIndex, "err");
			// System.out.println("err == " + err);
			if (err == null)
				err = "";
			if (err.equals("")) {

				ws = dao.getWebRowSet(nprocIndex, "resultset");

			}

			else {
				throw new Exception(err);
			}
		} catch (Exception e) {
			VO.setStrMsgString("MsApprovalTransDAO.cancellist() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
		return ws;
	}
*/
	public static void getDataFromPatAdmission(
			MsApprovalTransVO _msApprovalTransVO) {
		String strproc_name = "{call pkg_ipd_view.PROC_HIPT_PATADMISSION_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.getDataFromPatAdmission()");
			nprocIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nprocIndex, "modeval","5",1);
			dao.setProcInValue(nprocIndex, "puk", _msApprovalTransVO.getStrCrNo(),2);
			dao.setProcInValue(nprocIndex, "seatId","",3);
			dao.setProcInValue(nprocIndex, "modifyTime","0",4);
			dao.setProcInValue(nprocIndex, "hosp_code", _msApprovalTransVO.getStrHospitalCode(),5);
			dao.setProcInValue(nprocIndex, "patListType","0",6); //--ONLINE ADVICE PATIENT LISTING
			dao.setProcInValue(nprocIndex, "searchStr","",7);
			dao.setProcInValue(nprocIndex, "searchType","1",8);
			dao.setProcInValue(nprocIndex, "toRows","0",9);
			dao.setProcInValue(nprocIndex, "frmRows","0",10);
			dao.setProcInValue(nprocIndex, "onlinedis","2",11);//--ONLINE DISCHARGE
			dao.setProcInValue(nprocIndex, "deptUnitCode","",12);
			dao.setProcInValue(nprocIndex, "wardCode","",13);
			dao.setProcOutValue(nprocIndex, "err", 1,14);
			dao.setProcOutValue(nprocIndex, "resultset", 2,15);
		   
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");

			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (ws != null)
				ws.next();
			if (strerr.equals("")) {
				_msApprovalTransVO.setStrAdviceNo(ws.getString(1));
				_msApprovalTransVO.setStrEpisodeNumber(ws.getString(2));
				//_msApprovalTransVO.setStrDeptUnitCode(ws.getString(3));
				_msApprovalTransVO.setStrWardName(ws.getString(4));
				_msApprovalTransVO.setStrWardCode(ws.getString(5));
							
				_msApprovalTransVO.setStrRoomBedNo(ws.getString(6) + "/"
						+ ws.getString(7));
				_msApprovalTransVO.setStrConsultantName(ws.getString(8));
				_msApprovalTransVO.setStrPatCategoryCode(ws.getString(9));
				_msApprovalTransVO.setStrPropAdminssionDate(ws.getString(10));
				_msApprovalTransVO.setStrDeptUnitCmb(ws.getString(11));
				_msApprovalTransVO.setStrAdmNo(ws.getString(12));
				_msApprovalTransVO.setStrWardType(ws.getString(13));
				_msApprovalTransVO.setStrProvisionDiagnosis(ws.getString(14));
				String arr[]=ws.getString(15).replace("^", "#").split("#");
				System.out.println("array is"+arr[0]+" "+arr[1]+" "+arr[2]);
				_msApprovalTransVO.setStrSurgeryDate((arr[1].equals("") ? "NA" : arr[1]));//surgery Date
				_msApprovalTransVO.setStrLengthOfStay((arr[0].equals("") ? "NA" :arr[0]));//length of stay
				_msApprovalTransVO.setStrAdmissionType(arr[2]);//Admission type
				_msApprovalTransVO.setStrDeptUnitCode(ws.getString(16));
				System.out.println("deptUnit------------------------------------------------------->"+ws.getString(16));
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			_msApprovalTransVO
					.setStrMsgString("MsApprovalTransDAO.getDataFromPatAdmission() --> "
							+ e.getMessage());
			_msApprovalTransVO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			ws = null;
		}
	}
/**
 * This procedure is used to bring details from admission advice table.
 * @param _msApprovalTransVO
 */
	public static void getDataFromBooking(MsApprovalTransVO _msApprovalTransVO) {
		String strproc_name = "{call pkg_ipd_view.PROC_HIPT_PAT_BOOKING_DTL(?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.getDataFromBooking()");
			nprocIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nprocIndex, "CRNO", _msApprovalTransVO.getStrCrNo(),1);
			dao.setProcInValue(nprocIndex, "HOSP_CODE", _msApprovalTransVO.getStrHospitalCode(),2);
			dao.setProcOutValue(nprocIndex, "err", 1,3);
			dao.setProcOutValue(nprocIndex, "resultset", 2,4);
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if(ws.size()==0)
			{
				_msApprovalTransVO.setStrMsgType("4");
			}
			else
			{
			  if (strerr.equals("")) {
				if(ws.next())
				{
					_msApprovalTransVO.setStrAdviceNo(ws.getString(1));
					_msApprovalTransVO.setStrEpisodeNumber(ws.getString(2));
					_msApprovalTransVO.setStrDeptUnitCode(ws.getString(3));
					_msApprovalTransVO.setStrWardName(ws.getString(4));
					_msApprovalTransVO.setStrWardCode(ws.getString(5));
					_msApprovalTransVO.setStrRoomBedNo(ws.getString(6));
					_msApprovalTransVO.setStrConsultantName(ws.getString(7));
					_msApprovalTransVO.setStrPatCategoryCode(ws.getString(8));
					_msApprovalTransVO.setStrPropAdminssionDate(ws.getString(9));
					_msApprovalTransVO.setStrDeptUnitCmb(ws.getString(10));
					_msApprovalTransVO.setStrProvisionDiagnosis(ws.getString(11));
					/*String arr[]=ws.getString(12).replace("^", "#").split("#");
					System.out.println("array is"+arr[0]+" "+arr[1]+" "+arr[2]);
					_msApprovalTransVO.setStrSurgeryDate((arr[1].equals("") ? "NA" : arr[1]));//surgery Date
					_msApprovalTransVO.setStrLengthOfStay((arr[0].equals("") ? "NA" :arr[0]));//length of stay
					_msApprovalTransVO.setStrAdmissionType(arr[2]);//Admission type*/
				}
			} else {
				throw new Exception(strerr);
			}
		 }
		} catch (Exception e) {
			//e.printStackTrace();
			_msApprovalTransVO
					.setStrMsgString("MsApprovalTransDAO.getDataFromBooking() --> "
							+ e.getMessage());
			_msApprovalTransVO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			ws = null;
		}
	}

	public static void getEmployeeCombo(MsApprovalTransVO _msApprovalTransVO) {
		String strproc_name = "{call pkg_ipd_view.Proc_Unit_Consulatant_View(?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;
		HisUtil util = null;

		try {
			util = new HisUtil("ipd","transactions.MsApprovalTransDAO.getEmployeeCombo()");
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.getEmployeeCombo()");
			nprocIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nprocIndex, "modVal", "4",1);
			dao.setProcInValue(nprocIndex, "deptunitcode", "",2);
			dao.setProcInValue(nprocIndex, "hosp_code", _msApprovalTransVO.getStrHospitalCode(),3);
			dao.setProcInValue(nprocIndex, "seatId", _msApprovalTransVO.getStrSeatId(),4);
			dao.setProcOutValue(nprocIndex, "err", 1,5);
			dao.setProcOutValue(nprocIndex, "resultset", 2,6);
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				_msApprovalTransVO.setStrEmployeeComboValues(util
						.getOptionValue(ws, _msApprovalTransVO
								.getStrVerifiedBy(), "0^Select Value", true));
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			_msApprovalTransVO
					.setStrMsgString("MsApprovalTransDAO.getEmployeeCombo() --> "
							+ e.getMessage());
			_msApprovalTransVO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			ws = null;
		}
	}

	public static void getFormDetails(MsApprovalTransVO _msApprovalTransVO)
	{
		String strproc_name = "{call PKG_IPD_VIEW.PROC_MSAPPROVAL_DTL(?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;
		HisUtil util = null;
		String advNo=_msApprovalTransVO.getStrAdviceNo();
		
		try 
		{
			util = new HisUtil("ipd","transactions.MsApprovalTransDAO.getFormDetails()");
			HisUtil.replaceNullValueWithEmptyString(_msApprovalTransVO);
			dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.getFormDetails()");
			nprocIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nprocIndex, "CRNO", _msApprovalTransVO.getStrCrNo(),2);
			dao.setProcInValue(nprocIndex, "HOSP_CODE", _msApprovalTransVO.getStrHospitalCode(),1);
			System.out.println("advNo.length()"+advNo.length());
			if(advNo.length()>0)
				dao.setProcInValue(nprocIndex, "ADVISE_NO", advNo,3);
			else
				dao.setProcInValue(nprocIndex, "ADVISE_NO", "0",3);
			dao.setProcOutValue(nprocIndex, "err", 1,4);
			dao.setProcOutValue(nprocIndex, "resultset", 2,5);
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");
			System.out.println("ws"+ws.size());
			System.out.println("ws !=null"+ws !=null);
			System.out.println("strerr"+strerr);
			
			if(ws.size()==0)
			{
				
			}
			if (ws != null && ws.size() != 0) {
				ws.next();
				if (strerr.equals("")) {
					
					System.out.println(_msApprovalTransVO.getStrCrNo()+" "+_msApprovalTransVO.getStrHospitalCode()+" "+_msApprovalTransVO.getStrAdviceNo());
					_msApprovalTransVO.setStrApprovedStatus(ws.getString(1));
					_msApprovalTransVO.setStrRemark(ws.getString(2));
					_msApprovalTransVO.setStrFormNo(ws.getString(3));
					_msApprovalTransVO.setStrRequestDate(ws.getString(4));
					_msApprovalTransVO.setStrWaitPeriod(ws.getString(5));
					_msApprovalTransVO.setStrVerifiedBy(ws.getString(6));
					_msApprovalTransVO.setStrAdmNo(ws.getString(7));
					_msApprovalTransVO.setStrVerifiedDate(ws.getString(8));
					_msApprovalTransVO.setStrOccID(ws.getString(9));
					_msApprovalTransVO.setStrPriorityType(ws.getString(31));
					_msApprovalTransVO.setStrUrgentTypeReason(ws.getString(32));
					_msApprovalTransVO.setStrUrgentTypeRemarks(ws.getString(33));
					
					System.out.println("urgent "+ws.getString(31)+" "+ws.getString(32)+" "+ws.getString(33));
				} else {
					throw new Exception(strerr);
				}
			} else {
				_msApprovalTransVO.setStrRequestDate(util.getASDate("dd-MMM-yyyy"));
				_msApprovalTransVO.setStrVerifiedDate(util.getASDate("dd-MMM-yyyy"));
				_msApprovalTransVO.setStrMsgType("2");
			}
		} catch (Exception e) {
			_msApprovalTransVO
					.setStrMsgString("MsApprovalTransDAO.getFormDetails() --> "
							+ e.getMessage());
			_msApprovalTransVO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			ws = null;
		}
	}

	public static void verifyCrNo(MsApprovalTransVO _msApprovalTransVO) {
		String strproc_name = "{call PROC_HRGT_PAT_DTL(?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("HIS", "GlobalUtility.verifyCrNo()");
			nprocIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nprocIndex, "CRNO", _msApprovalTransVO.getStrCrNo(),1);
			dao.setProcInValue(nprocIndex, "HOSP_CODE", _msApprovalTransVO.getStrHospitalCode(),2);
			dao.setProcOutValue(nprocIndex, "err", 1,3);
			dao.setProcOutValue(nprocIndex, "resultset", 2,4);
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (ws == null || ws.size() == 0)
				_msApprovalTransVO.setStrMsgType("2");
			else
				ws.next();
		} catch (Exception e) {
			_msApprovalTransVO
					.setStrMsgString("MsApprovalTransDAO.verifyCrNo() --> "
							+ e.getMessage());
			_msApprovalTransVO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			ws = null;
		}
	}

	public static void patApprovedList(MsApprovalTransVO _msApprovalTransVO) {
		String strproc_name = "{call pkg_ipd_view.Proc_Allotementordered_List(?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;

		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("HIS", "GlobalUtility.verifyCrNo()");
			nprocIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nprocIndex, "hosp_code", _msApprovalTransVO.getStrHospitalCode(),1);
			dao.setProcInValue(nprocIndex, "crno", _msApprovalTransVO.getStrCrNo(),2);
			dao.setProcOutValue(nprocIndex, "err", 1,3);
			dao.setProcOutValue(nprocIndex, "resultset", 2,4);
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (ws == null || ws.size() == 0)
				_msApprovalTransVO.setStrMsgType("1");
			else
				_msApprovalTransVO.setWsPatApprovedList(ws);
		} catch (Exception e) {
			_msApprovalTransVO
					.setStrMsgString("MsApprovalTransDAO.patApprovedList() --> "
							+ e.getMessage());
			_msApprovalTransVO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			ws = null;
		}
	}
	
public static void getListValues(MsApprovalTransVO voObj) {
		
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Ipd_Rpt.RPT_MSAPPROVAL_LISTNO(?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {

			daoObj = new HisDAO("IPD Reports","MsApprovalTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),1);
			daoObj.setProcOutValue(nProcIndex,"err", 1,2);
			daoObj.setProcOutValue(nProcIndex,"RESULTSET", 2,3);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				voObj.setStrListWs(ws);
				
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			//e.printStackTrace();
			voObj
					.setStrMsgString("MsApprovalTransDAO.getListValues() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}



///This Method is Being Used To Bring the Previous Diagnosis Details

public static void getPrevoiusDiagnosisDtl(MsApprovalTransVO voObj) {

	HisDAO daoObj = null;
	WebRowSet ws = null;
	String strProcName = "{call pkg_simple_view.Proc_Hrgt_Episode_Diag_Dtl(?,?,?,?,?,?,?)}";
	int nProcIndex = 0;
	String strErr = "";
	
	try {
		daoObj = new HisDAO("Admission Advice Trans",
				"AdmissionAdviceTransDAO");
		nProcIndex = daoObj.setProcedure(strProcName);

		daoObj.setProcInValue(nProcIndex, "modeval", "1", 1);
		daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(), 2);
		daoObj.setProcInValue(nProcIndex, "puk", voObj.getStrCrNo(), 3);
		daoObj.setProcInValue(nProcIndex, "episode_code", voObj.getStrEpisodeNumber().equals("") ? "0" : voObj.getStrEpisodeNumber(), 4);
		daoObj.setProcInValue(nProcIndex, "visitno", voObj.getStrVisitValue().equals("") ? "0" : voObj.getStrVisitValue(), 5);
		daoObj.setProcOutValue(nProcIndex, "err", 1, 6);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2, 7);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		if (strErr == null)
			strErr = "";
		ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		if (strErr.equals("")) {

			voObj.setPreviousDiagnosisWs(ws);

		} else {
			throw new Exception(strErr);
		}

	} catch (Exception e) {

		voObj.setStrMsgString("msapprovalTransDAO.getPrevoiusDiagnosisDtl() --> "+ e.getMessage());
		voObj.setStrMsgType("1");
	} finally {
		if (daoObj != null) {
			daoObj.free();
			daoObj = null;
		}
	}

}


public static void setApprovedDataInList(MsApprovalTransVO msApprovalTransVO)
{
	
	String strproc_name = "{call PKG_IPD_DML.proc_ms_approval_insert(?,?,?)}";
	HisDAO dao = null;
	int nprocIndex = 0;
	String strerr = "";

	try {
		dao = new HisDAO("ipd","transactions.MsApprovalTransDAO.setApprovedDataInList()");
		nprocIndex = dao.setProcedure(strproc_name);
		dao.setProcInValue(nprocIndex, "hosp_code", msApprovalTransVO.getStrHospitalCode(),1);
		dao.setProcInValue(nprocIndex, "seat_id", msApprovalTransVO.getStrSeatId(),2);
		dao.setProcOutValue(nprocIndex, "err", 1,3);
		dao.execute(nprocIndex, 1);

		synchronized (dao) {
			dao.fire();
		}

		strerr = dao.getString(nprocIndex, "err");
		if (!strerr.equals(""))
			throw new Exception(strerr);

	} catch (Exception e) {
		e.printStackTrace();
		msApprovalTransVO.setStrMsgString("MsApprovalTransDAO.setApprovedDataInList() --> "+ e.getMessage());
		msApprovalTransVO.setStrMsgType("1");
	} finally {
		if (dao != null)
			dao.free();
		dao = null;
	}

}
public static void setUnitComboValues(
		MsApprovalTransVO msApprovalTransVO) {
	HisDAO daoObj = null;
	WebRowSet ws = null;

	String strProcName = "{call pkg_ipd_view.Proc_Unit(?,?,?,?,?,?,?,?,?)}";
	int nProcIndex = 0;
	String strErr = "";

	try {
		daoObj = new HisDAO("MS Approval",
				"MsApprovalTransDAO");

		nProcIndex = daoObj.setProcedure(strProcName);
		daoObj.setProcInValue(nProcIndex, "modeval", "2", 1);
		daoObj.setProcInValue(nProcIndex, "dept_code",
				msApprovalTransVO.getStrDepartmentCode(), 2);
		daoObj.setProcInValue(nProcIndex, "hosp_code",
				msApprovalTransVO.getStrHospitalCode(), 3);
		daoObj.setProcInValue(nProcIndex, "puk", msApprovalTransVO
				.getStrCrNo(), 4);
		daoObj.setProcInValue(nProcIndex, "seatid", "", 5);
		daoObj.setProcInValue(nProcIndex, "wardcode", "", 6);
		daoObj.setProcInValue(nProcIndex, "unitcode", "", 7);
		daoObj.setProcOutValue(nProcIndex, "err", 1, 8);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2, 9);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		if (strErr == null)
			strErr = "";
		ws = daoObj.getWebRowSet(nProcIndex, "resultset");

		if (strErr.equals(""))
			msApprovalTransVO.setStrUnitComboValuesWs(ws);
		else
			throw new Exception(strErr);
	} catch (Exception e) {
		msApprovalTransVO
				.setStrMsgString("MsApprovalTransDAO.setUnitComboValues() --> "
						+ e.getMessage());
		msApprovalTransVO.setStrMsgType("1");
	} finally {
		if (daoObj != null) {
			daoObj.free();
			daoObj = null;
		}
	}
}
public static void checkDuplicate(MsApprovalTransVO vo) {
	String strProcName = "{call pkg_ipd_view.proc_duplicacy_dtl(?,?,?,?,?,?)}";
	int nProcIndex = 0;
	String strErr = "";
	HisDAO daoObj = null;
	WebRowSet ws = null;

	try {
		daoObj = new HisDAO("ADT", "AdmissionAdviceTransDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		daoObj.setProcInValue(nProcIndex, "puk", vo.getStrCrNo(), 1);
		daoObj.setProcInValue(nProcIndex, "wardcode", vo.getStrWard(), 2);
		daoObj.setProcInValue(nProcIndex, "deptunitcode", vo
				.getStrUnitValue(), 3);
		daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),
				4);
		daoObj.setProcOutValue(nProcIndex, "err", 1, 5);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2, 6);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		if (strErr == null)
			strErr = "";

		ws = daoObj.getWebRowSet(nProcIndex, "resultset");

		if (strErr.equals("")) {
			if (ws.next()) {
				vo.setStrFlag(ws.getString(1));
			}
		} else {
			throw new Exception(strErr);
		}
	} catch (Exception e) {
		vo.setStrMsgString("MsApprovalTransDAO.checkDuplicate() --> "
				+ e.getMessage());
		vo.setStrMsgType("1");
	}
}
public static void getWardValues(MsApprovalTransVO vo) {
	HisDAO daoObj = null;
	WebRowSet ws = null;

	String strProcName = "{call pkg_ipd_view.proc_HIPT_WARD_MST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	int nProcIndex = 0;

	String strErr = "";

	daoObj = new HisDAO("Patient Admission Transaction",
			"PatientAdmissionTransDAO.getWardValues()");
	try {

		daoObj = new HisDAO("Admission Advice Transaction",
				"PatientAdmissionTransDAO");

		nProcIndex = daoObj.setProcedure(strProcName);

		daoObj.setProcInValue(nProcIndex, "modeVal", "11", 1);
		daoObj.setProcInValue(nProcIndex, "wardtypcode", vo
				.getStrWardTypeCode(), 2);
		daoObj.setProcInValue(nProcIndex, "deptcode", vo
				.getStrDepartmentValue(), 3);
		daoObj.setProcInValue(nProcIndex, "deptunitcode", vo
				.getStrUnitValue(), 4);
		daoObj.setProcInValue(nProcIndex, "unitcode", vo.getStrUnitCode(),
				5);
		daoObj.setProcInValue(nProcIndex, "age", vo.getStrAge(), 6);
		daoObj.setProcInValue(nProcIndex, "gender", vo.getStrSex(), 7);
		daoObj.setProcInValue(nProcIndex, "treatment_cat", vo
				.getStrTreatmentCategoryCode(), 8);
		daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),
				9);
		daoObj.setProcInValue(nProcIndex, "effect_from", "", 10);
		daoObj.setProcInValue(nProcIndex, "effect_to", "", 11);
		daoObj.setProcInValue(nProcIndex, "diseasetypcode", "0", 12);
		daoObj.setProcInValue(nProcIndex, "wardcode", "0", 13);
		daoObj.setProcInValue(nProcIndex, "puk_no", vo.getStrCrNo(), 14);
		daoObj.setProcInValue(nProcIndex, "charge_type_id", "0", 15);
		daoObj.setProcOutValue(nProcIndex, "err", 1, 16);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2, 17);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		if (strErr == null)
			strErr = "";
		ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		if (strErr.equals("")) {
			vo.setWardWS(ws);
		} else {
			throw new Exception(strErr);
		}
	} catch (Exception e) {
		e.printStackTrace();
		vo.setStrMsgString("MsApprovalTransDAO.getWardValues() --> "
				+ e.getMessage());
		vo.setStrMsgType("1");
	} finally {
		if (daoObj != null) {
			daoObj.free();
			daoObj = null;
		}
	}
}
public static void setBedTypeDtl(MsApprovalTransVO voObj) {

	HisDAO daoObj = null;
	WebRowSet ws = null;
	String strProcName = "{call pkg_ipd_view.proc_bed_type_mst(?,?,?,?)}";
	int nProcIndex = 0;
	String strErr = "";
	try {
		daoObj = new HisDAO("Admission Advice Trans",
				"DAOAdmissionAdviceTrans");

		nProcIndex = daoObj.setProcedure(strProcName);
		daoObj.setProcInValue(nProcIndex, "modval", "1", 1);
		daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
				.getStrHospitalCode(), 2);
		daoObj.setProcOutValue(nProcIndex, "err", 1, 3);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2, 4);

		daoObj.executeProcedureByPosition(nProcIndex);

		strErr = daoObj.getString(nProcIndex, "err");

		if (strErr == null)
			strErr = "";

		ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		if (strErr.equals("")) {

			voObj.setBedTypeWs(ws);
		} else {
			throw new Exception(strErr);
		}

	} catch (Exception e) {

		voObj
				.setStrMsgString("MsApprovalTransDAO.setBedTypeDtl() --> "
						+ e.getMessage());
		voObj.setStrMsgType("1");
	} finally {
		if (daoObj != null) {
			daoObj.free();
			daoObj = null;
		}
	}

}
public static void setRoomTypeDtl(MsApprovalTransVO voObj) {

	HisDAO daoObj = null;
	WebRowSet ws = null;
	String strProcName = "{call pkg_ipd_view.proc_roomtype_a(?,?,?,?)}";
	int nProcIndex = 0;

	String strErr = "";

	try {
		daoObj = new HisDAO("Admission Advice Trans",
				"DAOAdmissionAdviceTrans");

		nProcIndex = daoObj.setProcedure(strProcName);
		daoObj.setProcInValue(nProcIndex, "modval", "1", 1);
		daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
				.getStrHospitalCode(), 2);
		daoObj.setProcOutValue(nProcIndex, "err", 1, 3);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2, 4);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");

		if (strErr == null)
			strErr = "";

		ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		if (strErr.equals("")) {

			voObj.setRoomTypeWs(ws);
		} else {
			throw new Exception(strErr);
		}

	} catch (Exception e) {

		voObj
				.setStrMsgString("MsApprovalTransDAO.setRoomTypeDtl() --> "
						+ e.getMessage());
		voObj.setStrMsgType("1");
	} finally {
		if (daoObj != null) {
			daoObj.free();
			daoObj = null;
		}
	}

}
public static void setWardDtlTypes(MsApprovalTransVO voObj) {

	HisDAO daoObj = null;
	WebRowSet ws = null;

	String strProcName = "{call pkg_ipd_view.PROC_HIPT_WARDTYPE_MST(?,?,?,?)}";
	int nProcIndex = 0;
	String strErr = "";
	// WebRowSet web=null;

	try {
		daoObj = new HisDAO("Admission Advice Trans",
				"AdmissionAdviceTransDAO");

		nProcIndex = daoObj.setProcedure(strProcName);
		daoObj.setProcInValue(nProcIndex, "modeVal", "1", 1);
		daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
				.getStrHospitalCode(), 2);
		daoObj.setProcOutValue(nProcIndex, "err", 1, 3);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2, 4);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		if (strErr == null)
			strErr = "";
		ws = daoObj.getWebRowSet(nProcIndex, "resultset");

		if (strErr.equals("")) {

			voObj.setWardTYPES(ws);

		} else {
			throw new Exception(strErr);
		}

	} catch (Exception e) {

		voObj.setStrMsgString("MsApprovalTransDAO.setWardDtl() --> "
				+ e.getMessage());
		voObj.setStrMsgType("1");
	} finally {
		if (daoObj != null) {
			daoObj.free();
			daoObj = null;
		}
	}

}
public static void getRoomValues(MsApprovalTransVO vo) {
	HisDAO daoObj = null;
	WebRowSet ws = null;

	String strProcName = "{call pkg_ipd_view.proc_roomconfig(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	int nProcIndex = 0;

	String strErr = "";

	try {
		daoObj = new HisDAO("Admission Advice Trans",
				"AdmissionAdviceTransDAO");
		nProcIndex = daoObj.setProcedure(strProcName);

		daoObj.setProcInValue(nProcIndex, "modval", "3", 1);
		daoObj.setProcInValue(nProcIndex, "roomtypcode", vo
				.getStrRoomType(), 2);
		daoObj.setProcInValue(nProcIndex, "wardcode", vo.getStrWard(), 3);
		daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),
				4);
		daoObj.setProcInValue(nProcIndex, "unitcode", vo.getStrUnitCode(),
				5);
		daoObj.setProcInValue(nProcIndex, "age", vo.getStrAge(), 6);
		daoObj.setProcInValue(nProcIndex, "deptcode", "", 7);
		daoObj.setProcInValue(nProcIndex, "deptunitcode", vo
				.getStrUnitValue(), 8);
		daoObj.setProcInValue(nProcIndex, "gender", vo.getStrSex(), 9);
		daoObj.setProcInValue(nProcIndex, "treatment_cat", vo
				.getStrTreatmentCategoryCode(), 10);
		daoObj.setProcInValue(nProcIndex, "puk_no", vo.getStrCrNo(), 11);
		daoObj.setProcInValue(nProcIndex, "diseasetypcode", "0", 12);
		daoObj.setProcOutValue(nProcIndex, "err", 1, 13);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2, 14);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");

		if (strErr == null)
			strErr = "";
		ws = daoObj.getWebRowSet(nProcIndex, "resultset");

		if (strErr.equals("")) {
			vo.setRoomWs(ws);
		} else {
			throw new Exception(strErr);
		}

	} catch (Exception e) {

		e.printStackTrace();
		vo.setStrMsgString("MsApprovalTransDAO.getRoomValues() --> "
				+ e.getMessage());
		vo.setStrMsgType("1");
	}
}
public static void setDepartmentNameType(MsApprovalTransVO voObj) {

	String strProcName = "{call pkg_simple_view.proc_dept_mst_view(?,?,?)}";
	int nProcIndex = 0;

	String strErr = "";

	HisDAO daoObj = null;
	WebRowSet ws = null;

	String strDeptCode = voObj.getStrDepartmentValue();
	try {
		daoObj = new HisDAO("Admission Advice Trans",
				"DAOAdmissionAdviceTrans");

		nProcIndex = daoObj.setProcedure(strProcName);

		if (strDeptCode != null) {

			daoObj.setProcInValue(nProcIndex, "deptcode", strDeptCode, 1);

			daoObj.setProcOutValue(nProcIndex, "err", 1, 2);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 3);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				voObj.setDepartTypeWS(ws);

			} else {
				throw new Exception(strErr);
			}
		}

	} catch (Exception e) {

		voObj
				.setStrMsgString("MsApprovalTransDAO.setDepartmentName() --> "
						+ e.getMessage());
		voObj.setStrMsgType("1");

	} finally {
		if (daoObj != null) {
			daoObj.free();
			daoObj = null;
		}
	}

}
public static void setEpisodeDtl(MsApprovalTransVO vo)
		throws Exception {
	String strProcName = "{call pkg_ipd_view.proc_episode_dtl(?,?,?,?,?,?,?)}";
	int nProcIndex = 0;
	String strErr = "";
	HisDAO daoObj = null;
	WebRowSet ws = null;
	String strCrNum = vo.getStrCrNo();
	try {

		daoObj = new HisDAO("Admission Advice Transaction",
				"AdmissionAdviceTransDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		if (vo.getStrUnitValue() == null
				|| vo.getStrUnitValue().equals("0") || vo.getStrUnitValue().equals(""))
			daoObj.setProcInValue(nProcIndex, "modval", "1", 1);
		else
			daoObj.setProcInValue(nProcIndex, "modval", "2", 1);
		daoObj.setProcInValue(nProcIndex, "pukno", strCrNum, 2);
		daoObj.setProcInValue(nProcIndex, "dept_code", vo
				.getStrDepartmentValue().equals("") ? "0" : vo
				.getStrDepartmentValue(), 3);
		daoObj.setProcInValue(nProcIndex, "unit_code",
				vo.getStrUnitValue(), 4);
		daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),
				5);
		daoObj.setProcOutValue(nProcIndex, "err", 1, 6);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2, 7);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		if (strErr == null)
			strErr = "";
		ws = daoObj.getWebRowSet(nProcIndex, "resultset");

		if (strErr.equals("")) {

			if (ws.next()) {
				vo.setStrEpisodeNumber(ws.getString(1));
				vo.setStrVisitValue(ws.getString(2));
				vo.setStrMlcNo(ws.getString(3));
				 //System.out.println(""+ws.getString(4));
				vo.setStrDepartmentValue(ws.getString(4));
				vo.setStrUnitValue(ws.getString(5));
				vo.setStrPrimaryCategory(ws.getString(6));
			}

		} else {
			throw new Exception(strErr);
		}
	} catch (Exception e) {

		 e.printStackTrace();
		vo.setStrMsgString("MsApprovalTransDAO.setEpisodeDtl() --> "
				+ e.getMessage());
		vo.setStrMsgType("1");
		throw e;
	} finally {
		if (daoObj != null) {
			daoObj.free();
			daoObj = null;
		}
	}
}
public static void setDepartmentName(MsApprovalTransVO voObj) {

	String strProcName = "{call pkg_simple_view.proc_GBLT_DEPARTMENT_MST(?,?,?,?,?,?,?,?,?,?)}";
	int nProcIndex = 0;

	String strErr = "";

	HisDAO daoObj = null;
	WebRowSet ws = null;
	String modVal = "3";
	String strDeptCode = voObj.getStrDepartmentValue().equals("") ? "0"
			: voObj.getStrDepartmentValue();
	String strDeptName = "";
	String hosp_code = voObj.getStrHospitalCode();

	try {
		daoObj = new HisDAO("Admission Advice Trans",
				"DAOAdmissionAdviceTrans");

		nProcIndex = daoObj.setProcedure(strProcName);
		if (strDeptCode != null) {

			daoObj.setProcInValue(nProcIndex, "modeVal", modVal, 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", hosp_code, 2);
			daoObj.setProcInValue(nProcIndex, "deptcode", strDeptCode, 3);

			daoObj.setProcInValue(nProcIndex, "puk_no", "", 4);
			daoObj.setProcInValue(nProcIndex, "charge_type_id", "", 5);
			daoObj.setProcInValue(nProcIndex, "effect_from", "", 6);
			daoObj.setProcInValue(nProcIndex, "effect_to", "", 7);
			daoObj.setProcInValue(nProcIndex, "userId", "", 8);

			daoObj.setProcOutValue(nProcIndex, "err", 1, 9);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 10);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {

				if (ws.next()) {

					strDeptName = ws.getString(2);

					voObj.setStrDepartment(strDeptName);
					//System.out.println("hasahsa========="+strDeptName);

				}
			} else {
				throw new Exception(strErr);
			}
		}

	} catch (Exception e) {

		e.printStackTrace();
		voObj
				.setStrMsgString("MsApprovalTransDAO.setDepartmentName() --> "
						+ e.getMessage());
		voObj.setStrMsgType("1");

	} finally {
		if (daoObj != null) {
			daoObj.free();
			daoObj = null;
		}
	}

}
public static void setUnitName(MsApprovalTransVO voObj) {

	String strProcName = "{call pkg_simple_view.proc_unit_mst_view(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	int nProcIndex = 0;

	String strErr = "";

	HisDAO daoObj = null;
	WebRowSet ws = null;
	
	String strDeptUnitCode="";

	//1 In Case Of OFFline Advice Mode  0 in Case Of Online Advice(OPD Desk)
	if(voObj.getStrMode().equals("1"))
	{
	strDeptUnitCode = voObj.getStrUnitValue().equals("") ? "0" : voObj.getStrUnitValue();
	}
	else
	{
	strDeptUnitCode = voObj.getStrDeptUnitCode().equals("") ? "0"	: voObj.getStrDeptUnitCode();
	}
		
	String strDeptUnitName = "";

	try {
		daoObj = new HisDAO("Admission Advice Trans",
				"DAOAdmissionAdviceTrans");

		nProcIndex = daoObj.setProcedure(strProcName);

		if (strDeptUnitCode != null) {

			daoObj.setProcInValue(nProcIndex, "dptunitcode",
					strDeptUnitCode, 1);
			daoObj.setProcInValue(nProcIndex, "deptcode", strDeptUnitCode,
					2);
			daoObj.setProcInValue(nProcIndex, "slno", strDeptUnitCode, 3);
			daoObj.setProcInValue(nProcIndex, "unitname", strDeptUnitCode,
					4);
			daoObj.setProcInValue(nProcIndex, "empno", strDeptUnitCode, 5);
			daoObj.setProcInValue(nProcIndex, "effctdate", strDeptUnitCode,
					6);
			daoObj.setProcInValue(nProcIndex, "seatid", strDeptUnitCode, 7);
			daoObj.setProcInValue(nProcIndex, "entry_dt", strDeptUnitCode,
					8);
			daoObj.setProcInValue(nProcIndex, "isgeneral", strDeptUnitCode,
					9);
			daoObj.setProcInValue(nProcIndex, "unitcode", strDeptUnitCode,
					10);
			daoObj.setProcInValue(nProcIndex, "isvalid", strDeptUnitCode,
					11);
			daoObj.setProcInValue(nProcIndex, "effectvfrm",
					strDeptUnitCode, 12);
			daoObj.setProcInValue(nProcIndex, "effectvto", strDeptUnitCode,
					13);
			daoObj.setProcInValue(nProcIndex, "lstmoddate",
					strDeptUnitCode, 14);
			daoObj.setProcInValue(nProcIndex, "lstmodseatid",
					strDeptUnitCode, 15);
			daoObj.setProcInValue(nProcIndex, "remarks", strDeptUnitCode,
					16);
			daoObj.setProcInValue(nProcIndex, "isexpiry", strDeptUnitCode,
					17);
			daoObj.setProcInValue(nProcIndex, "expiryday", strDeptUnitCode,
					18);
			daoObj.setProcInValue(nProcIndex, "expirymonth",
					strDeptUnitCode, 19);
			daoObj.setProcInValue(nProcIndex, "diagcodetyp",
					strDeptUnitCode, 20);
			daoObj.setProcInValue(nProcIndex, "defaultclose_day",
					strDeptUnitCode, 21);

			daoObj.setProcOutValue(nProcIndex, "err", 1, 22);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 23);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {

				if (ws.next()) {

					strDeptUnitName = ws.getString(2);
					voObj.setStrUnit(strDeptUnitName);
				}

			} else {
				throw new Exception(strErr);
			}
		}

	} catch (Exception e) {

		e.printStackTrace();
		voObj.setStrMsgString("MsApprovalTransDAO.setUnitName() --> "
				+ e.getMessage());
		voObj.setStrMsgType("1");

	} finally {
		if (daoObj != null) {
			daoObj.free();
			daoObj = null;
		}
	}

}
public static void setAgeandSex(MsApprovalTransVO voObj) {

	HisDAO daoObj = null;
	WebRowSet ws = null;

	String strProcName = "{call pkg_ipd_view.proc_pat_demo_address_dtl(?,?,?,?)}";
	int nProcIndex = 0;

	String strErr = "";

	String strCrNum = voObj.getStrCrNo();
	String strUnitCode = "";
	try {
		daoObj = new HisDAO("Admission Advice Trans",
				"AdmissionAdviceTransDAO");

		nProcIndex = daoObj.setProcedure(strProcName);

		if (strCrNum != null && !strCrNum.equals("")) {
			daoObj.setProcInValue(nProcIndex, "puk", strCrNum, 1);
			daoObj.setProcInValue(nProcIndex, "addresstype", "1", 2);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 4);
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {

				if (ws.next()) {

					String strAgeandSex = ws.getString(2);
					voObj.setStrPatName(ws.getString(3));
					
					voObj.setStrTreatmentCategoryCode(ws.getString(7));

					String[] temp = strAgeandSex.replace("/", "#").split(
							"#");
					// This body setting age code
					for (int i = 0; i < temp[0].length(); i++) {
						if (temp[0].charAt(i) == 'Y') {
							strUnitCode = "3";
							break;
						}
						if (temp[0].charAt(i) == 'M') {
							strUnitCode = "2";
							break;
						}
						if (temp[0].charAt(i) == 'D') {
							strUnitCode = "1";
							break;
						}
						if (temp[0].charAt(i) == 'W') {
							strUnitCode = "4";
							break;
						}
						if (temp[0].charAt(i) == 'H') {

							strUnitCode = "1";
							break;
						}
					}

					voObj.setStrUnitCode(strUnitCode);
					String temp1 = "0";
					if (temp[1].length() == 4) {
						temp1 = "1";
					} else {
						temp1 = "2";
					}

					voObj.setStrAge(strAgeandSex.substring(0, temp[0]
							.length() - 2));
					// voObj.setStrAge("24");
					voObj.setStrSex(temp1);

				}

			} else {
				throw new Exception(strErr);
			}
		}

	} catch (Exception e) {

		e.printStackTrace();
		voObj.setStrMsgString("MsApprovalTransDAO.setAgeandSex() --> "
				+ e.getMessage());
		voObj.setStrMsgType("1");

	} finally {
		if (daoObj != null) {
			daoObj.free();
			daoObj = null;
		}
	}

}
public static void setWardDtl(MsApprovalTransVO voObj) {

	HisDAO daoObj = null;
	WebRowSet ws = null;
	String strProcName = "{call pkg_ipd_view.proc_HIPT_WARD_MST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	int nProcIndex = 0;
	String strErr = "";
	String strDepartmentCode = voObj.getStrDepartment();
	String strDeptUnitCode = voObj.getStrUnitValue();
	String strUnitCode = voObj.getStrUnitCode();
	String strAge = voObj.getStrAge();
	String strSex = voObj.getStrSex();
	String strCategory = voObj.getStrTreatmentCategoryCode();
	String strDiseaseType = "0";
	try {
		daoObj = new HisDAO("MS Approval Trans",
				"MsApprovalTransDAO");
		if (strDepartmentCode != null && strDeptUnitCode != null && strAge != null && strSex != null && strCategory != null) 
		{
			nProcIndex = daoObj.setProcedure(strProcName);	
			
			System.out.println("strDepartmentCode"+strDepartmentCode);
			
			System.out.println("strDeptUnitCode"+strDeptUnitCode);
			
			System.out.println("voObj.getStrHospCode()"+voObj.getStrHospitalCode());
			
			daoObj.setProcInValue(nProcIndex, "modeVal", "9", 1);
			daoObj.setProcInValue(nProcIndex, "wardtypcode", "", 2);
			daoObj.setProcInValue(nProcIndex, "deptcode",strDepartmentCode, 3);
			daoObj.setProcInValue(nProcIndex, "deptunitcode",strDeptUnitCode, 4);
			daoObj.setProcInValue(nProcIndex, "unitcode", strUnitCode, 5);
			daoObj.setProcInValue(nProcIndex, "age", strAge, 6);
			daoObj.setProcInValue(nProcIndex, "gender", strSex, 7);
			daoObj.setProcInValue(nProcIndex, "treatment_cat", strCategory,8);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(), 9);
			daoObj.setProcInValue(nProcIndex, "effect_from", "", 10);
			daoObj.setProcInValue(nProcIndex, "effect_to", "", 11);
			daoObj.setProcInValue(nProcIndex, "diseasetypcode",strDiseaseType, 12);
			daoObj.setProcInValue(nProcIndex, "wardcode", "", 13);
			daoObj.setProcInValue(nProcIndex, "puk_no", "", 14);
			daoObj.setProcInValue(nProcIndex, "charge_type_id", "", 15);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 16);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 17);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {

				voObj.setWardWS(ws);

			} else {
				throw new Exception(strErr);
			}

		}
	} catch (Exception e) {

		e.printStackTrace();
		voObj.setStrMsgString("MsApprovalTransDAO.setWardDtl() --> "
				+ e.getMessage());
		voObj.setStrMsgType("1");
	} finally {
		if (daoObj != null) {
			daoObj.free();
			daoObj = null;
		}
	}

}
public static void setTreatmentCatDtl(MsApprovalTransVO voObj) {

	HisDAO daoObj = null;
	WebRowSet ws = null;
	String strProcName = "{call PKG_SIMPLE_VIEW.PROC_GBLT_PATIENT_CAT_MST(?,?,?,?,?,?,?,?)}";
	int nProcIndex = 0;

	String strErr = "";

	try {
		daoObj = new HisDAO("Admission Advice Trans",
				"DAOAdmissionAdviceTrans");

		nProcIndex = daoObj.setProcedure(strProcName);

		daoObj.setProcInValue(nProcIndex, "modeVal", "1", 1);
		daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospitalCode(), 2);
		daoObj.setProcInValue(nProcIndex, "puk_no", "", 3);
		daoObj.setProcInValue(nProcIndex, "charge_type_id", "0", 4);
		daoObj.setProcInValue(nProcIndex, "effect_from", "", 5);
		daoObj.setProcInValue(nProcIndex, "effect_TO", "", 6);
		daoObj.setProcOutValue(nProcIndex, "err", 1, 7);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2, 8);

		daoObj.executeProcedureByPosition(nProcIndex);

		strErr = daoObj.getString(nProcIndex, "err");

		if (strErr == null)
			strErr = "";

		ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		if (strErr.equals("")) {

			voObj.setTreatmentCategoryWs(ws);
		} else {
			throw new Exception(strErr);
		}

	} catch (Exception e) {

		e.printStackTrace();
		voObj
				.setStrMsgString("MsApprovalTransDAO.setTreatmentCatDtl() --> "
						+ e.getMessage());
		voObj.setStrMsgType("1");
	} finally {
		if (daoObj != null) {
			daoObj.free();
			daoObj = null;
		}
	}

}
public static void setDeptComboValues(MsApprovalTransVO vo) 
{
	HisDAO daoObj = null;
	WebRowSet ws = null;

	String strProcName = "{call pkg_ipd_view.Proc_Department(?,?,?,?,?,?)}";
	int nProcIndex = 0;
	String strErr = "";

	try 
	{
		daoObj = new HisDAO("ADT","AdmissionAdviceTransDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		daoObj.setProcInValue(nProcIndex, "modeval", "3", 1);
		daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(), 2);
		daoObj.setProcInValue(nProcIndex, "puk", vo.getStrCrNo(), 3);
		daoObj.setProcInValue(nProcIndex, "seatid", "", 4);
		daoObj.setProcOutValue(nProcIndex, "err", 1, 5);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2, 6);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		if (strErr == null)
			strErr = "";
		ws = daoObj.getWebRowSet(nProcIndex, "resultset");

		if (strErr.equals(""))
			vo.setStrDeptComboValuesWs(ws);
		else
			throw new Exception(strErr);
	} 
	catch (Exception e) 
	{
		vo.setStrMsgString("MsApprovalTransDAO.setDeptComboValues() --> "+ e.getMessage());
		vo.setStrMsgType("1");
	} 
	finally 
	{
		if (daoObj != null) 
		{
			daoObj.free();
			daoObj = null;
		}
	}
}


}


