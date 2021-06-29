package billing.transactions;
/*
 * COMPULSORY CHARGES BY CONSULTANT
 * 
 * author:Manisha Gangwar
 * 
 * dated:23rd Jan 2019
 */


import hisglobal.transactionmgnt.HisDAO;
import hisglobal.vo.UserVO;

import javax.sql.rowset.WebRowSet;




public class CompulsoryChargesByConsultantDAO {
  
	public static void getPatientDtls(CompulsoryChargesByConsultantVO vo) {
		String proc_name = "";

		proc_name = "{call  pkg_bill_auto_charge_dml.proc_get_Ipd_Bill_Desk_Pat_Dtl_old(?,?,?,?)}";   //pkg_bill_view

		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		try {
			dao = new HisDAO("billing",
					"transactions.IpdBillManagementTransNewDAO .getPatientDtls()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "crNo", vo.getStrCrNo());
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode()); 
			/*dao.setProcInValue(nprocIndex, "acc_no", vo.getStrAccountNo());
			dao.setProcInValue(nprocIndex, "adm_no", vo.getStrAddmissionNo()); */
			
			dao.setProcOutValue(nprocIndex, "err", 1); 
			dao.setProcOutValue(nprocIndex, "resultset", 2); 
			
			dao.executeProcedure(nprocIndex);
			
			strErr = dao.getString(nprocIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			
			vo.setStrChk("0");
			while (ws.next()) {
				vo.setStrChk(ws.getString(1));
			}
		} catch (Exception e) {
			vo.setStrMsgString("IpdBillManagementTransNewDAO.getPatientDtls() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}
	
	public static WebRowSet getCompulsoryTariffCombo(IpdBillManagementTransVO VO) {

		// String str = VO.getStrGroupId();

		String proc_name = "";

		proc_name = "{call pkg_bill_auto_charge_dml.proc_hblt_compulsorytariff_mst(?,?,?,?,?,?)}";  //pkg_bill_view

		HisDAO dao = null;

		int nprocIndex = 0;

		String strerr = "";

		WebRowSet ws = null;

		String[] groupid = VO.getStrGroupId().replace("^", "#").split("#");

		String strIsPack = "0";

		if (groupid.length > 1 && groupid[1].equals("1")) {
			VO.setStrIsPackaged(groupid[1]);
			strIsPack = groupid[1];
		}
		try {

			dao = new HisDAO("IpdBillManagementTrans",
					"transactions.IpdBillManagementTransDAO.TARIFFNAMECOMBO(VO)");

			nprocIndex = dao.setProcedure(proc_name);

			// set value

			dao.setProcInValue(nprocIndex, "hosp_code",
					VO.getStrHospitalCode(), 1);

			dao.setProcInValue(nprocIndex, "group_id", VO.getGrpid(), 2);

			dao.setProcInValue(nprocIndex, "modeval", "1", 3);
			dao.setProcInValue(nprocIndex, "p_crno", VO.getStrCrNo(), 4);

			dao.setProcOutValue(nprocIndex, "err", 1, 5); // 1 for string
															// return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2, 6); // 2 for object

			// execute procedure

			dao.executeProcedureByPosition(nprocIndex);

			// get value

			strerr = dao.getString(nprocIndex, "err");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao.getWebRowSet(nprocIndex, "resultset");
				VO.setStrTariffNameCombo(ws);
				
				
				
				
			} else {
				throw new Exception(strerr);
			}
		}

		catch (Exception e) {

			VO
					.setStrMsgString("IpdBillManagementTransDAO.TARIFFNAMECOMBO() --> "
							+ e.getMessage());

			VO.setStrMsgType("1");

		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}

		return ws;
	}

	
	
	
	public static void getDefaultCompulsoryTariff(CompulsoryChargesByConsultantVO VO) {

		String proc_name = "";
		
		proc_name = "{call pkg_bill_auto_charge_dml.proc_hblt_compulsorytariff_mst(?,?,?,?,?,?)}";   //pkg_bill_view

		HisDAO dao = null;

		int nprocIndex = 0;

		String strerr = "";

		WebRowSet ws = null;

		String[] groupid = VO.getStrGroupId().replace("^", "#").split("#");

		String strIsPack = "0";

		if (groupid.length > 1 && groupid[1].equals("1")) {
			VO.setStrIsPackaged(groupid[1]);
			strIsPack = groupid[1];
		}
		try {

			dao = new HisDAO("IpdBillManagementTrans",
					"transactions.IpdBillManagementTransDAO.TARIFFNAMECOMBO(VO)");

			nprocIndex = dao.setProcedure(proc_name);

			// set value

			dao.setProcInValue(nprocIndex, "hosp_code",	VO.getStrHospitalCode(), 1);

			dao.setProcInValue(nprocIndex, "group_id", VO.getGrpid(), 2);

			dao.setProcInValue(nprocIndex, "modeval", "2", 3);
			dao.setProcInValue(nprocIndex, "p_crno", VO.getStrCrNo(), 4);

			dao.setProcOutValue(nprocIndex, "err", 1, 5); // 1 for string
															// return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2, 6); // 2 for object

			// execute procedure

			dao.executeProcedureByPosition(nprocIndex);

			// get value

			strerr = dao.getString(nprocIndex, "err");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao.getWebRowSet(nprocIndex, "resultset");
				
				if (ws.next()) {

					VO.setStrDefaultCompTariffId(ws.getString(1));

					ws.beforeFirst();

				}
				
				
				
				
				
			} else {
				throw new Exception(strerr);
			}
		}

		catch (Exception e) {

			VO
					.setStrMsgString("IpdBillManagementTransDAO.TARIFFNAMECOMBO() --> "
							+ e.getMessage());

			VO.setStrMsgType("1");

		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}

		
	}
	
	public static boolean InsertCompChargesByConsultantDtl(CompulsoryChargesByConsultantVO vo,UserVO userVO) {

		String strTemp[] = vo.getStrClientPatNo().replace('^', '#').split("#");
		String proc_name1 = "";

		proc_name1 = "{call pkg_bill_auto_charge_dml.dml_hblt_auto_charge_by_consultant_trn(?,?,?,?,?,?,?,?,?,?)}";   // new package call   pkg_bill_dml
		HisDAO dao = null; 
		int procIndex1 = 0;
		String strerr = "";
		// WebRowSet ws = null;
		boolean retVal = false;
		try {

			int len=   Integer.parseInt(vo.getStrNumRows());
			for (int i = 0; i < len; i++)
			{
			
			if(vo.getStrCompulsoryTariffCode()[i]!="")
			{
				
			dao = new HisDAO("billing",	"transactions.CompulsoryChargesByConsultantDAO.InsertCompChargesByConsultantDtl()");

			procIndex1 = dao.setProcedure(proc_name1);

			// set value
			String strgroupid=""; 
			if(userVO.getUserEmpID()==null)
				userVO.setUserEmpID("");
			if(userVO.getUserEmpID()==null)
				userVO.setUserEmpID("");
			
			dao.setProcInValue(procIndex1, "modval", "1", 1);
			dao.setProcInValue(procIndex1, "p_crno", vo.getStrCrNo(), 2);
			dao.setProcInValue(procIndex1, "p_pataccount_no", vo.getStrPatAcctNo(), 3);
			dao.setProcInValue(procIndex1, "p_group_id", vo.getStrGrpId()[i],4);//(strgroupid.equals("") || strgroupid == null ? "": strgroupid), 4);
			dao.setProcInValue(procIndex1, "p_tariff_id", vo.getStrCompulsoryTariffCode()[i], 5);
			dao.setProcInValue(procIndex1, "p_seatid", userVO.getSeatId(), 6);
			dao.setProcInValue(procIndex1, "p_emp_no", userVO.getUserEmpID() , 7);
			dao.setProcInValue(procIndex1, "p_isvalid", "1", 8);
			dao.setProcInValue(procIndex1, "hosp_code",	vo.getStrHospitalCode(), 9);
			
			dao.setProcOutValue(procIndex1, "ERR", 1, 10); // 1 for string
			// return
			
			/*dao.setProcInValue(procIndex1, "sblnum_chargetype_id", "0", 3);
			dao.setProcInValue(procIndex1, "hbldt_pataccount_opdate", "", 4);
			dao.setProcInValue(procIndex1, "gnum_dept_code", "0", 5);
			dao.setProcInValue(procIndex1, "hrgnum_puk", "0", 6);
			dao.setProcInValue(procIndex1, "hastr_adm_no", "0", 8);
			dao.setProcInValue(procIndex1, "hrgnum_episode_code", "0", 9);
			dao.setProcInValue(procIndex1, "hblstr_approved_by", "", 18);
		
			dao.setProcInValue(procIndex1, "gdt_entry_date", "", 23);
			
			dao.setProcInValue(procIndex1, "ip",GetNetworkAddress.GetAddress("ip"),36);*/
		
			// value
			// execute procedure
			dao.executeProcedureByPosition(procIndex1);
			// get value

			strerr = dao.getString(procIndex1, "ERR");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				retVal = true;
				vo.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}
			}
			}
		} catch (Exception e) {
			vo.setStrMsgString("IpdBillManagementTransDAO.InsertAccountDtl() --> "+ e.getMessage());
			vo.setStrMsgType("1");
			e.printStackTrace();
		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}
		return retVal;

	}

	// Getting the List of ward on the basis of unitCode
	public static void  getWardOnBasisOfUnitCode(CompulsoryChargesByConsultantVO VO)
		{
			// this function is used for ward combo on the basis of departmentunit code
				String strproc_name = "{call PKG_IPD_VIEW.proc_HIPT_WARD_MST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
				HisDAO dao = null;
				int nprocIndex = 0;
				String strerr = "";
				WebRowSet ws = null;

				try {
					String unitCode="%";
					dao = new HisDAO("ipd", "transactions.NursingDeskTransDAO.ward()");
					nprocIndex = dao.setProcedure(strproc_name);
					// set value
					dao.setProcInValue(nprocIndex, "modeVal", "15",1);
					dao.setProcInValue(nprocIndex, "wardtypcode","0",2);
					dao.setProcInValue(nprocIndex, "deptcode",VO.getStrDepartment(),3);
					dao.setProcInValue(nprocIndex, "deptunitcode", unitCode,4);
					dao.setProcInValue(nprocIndex, "unitcode","0",5);
					dao.setProcInValue(nprocIndex, "age","0",6);
					dao.setProcInValue(nprocIndex, "gender","0",7);
					dao.setProcInValue(nprocIndex, "treatment_cat","0",8);
					dao.setProcInValue(nprocIndex, "hosp_code", VO.getStrHospitalCode(),9);
					dao.setProcInValue(nprocIndex, "effect_from","",10);
					dao.setProcInValue(nprocIndex, "effect_to","",11);
					dao.setProcInValue(nprocIndex, "diseasetypcode","0",12);
					dao.setProcInValue(nprocIndex, "wardcode","0",13);
					dao.setProcInValue(nprocIndex, "puk_no","0",14);		
					dao.setProcInValue(nprocIndex, "charge_type_id","0",15);
					dao.setProcOutValue(nprocIndex, "err", 1,16); // 1 for string return value
					dao.setProcOutValue(nprocIndex, "resultset", 2,17); // 2 for object
					
					// execute procedure
					dao.executeProcedureByPosition(nprocIndex);
					// get value
					strerr = dao.getString(nprocIndex, "err");

					if (strerr == null)
						strerr = "";

					ws = dao.getWebRowSet(nprocIndex, "resultset");

					if (strerr.equals("")) {
						VO.setStrWardnameCombo(ws);

					} else {
						throw new Exception(strerr);
					}
				} catch (Exception e) {
					VO.setStrMsgString("NursingDeskTransDAO.ward() --> "
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
	
	public static void getCompulsoryTariffList(CompulsoryChargesByConsultantVO voObj) 
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_credit_letters_list(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		// String strHospitalCode = voObj.getStrHospitalCode();

		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Billing","DAOBilling");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			/*System.out.println("cr no"+voObj.getStrValue1());
			System.out.println("catcode"+voObj.getStrCatCode());
			System.out.println("hosp_code"+voObj.getStrHospitalCode());*/
		/*	daoObj.setProcInValue(nProcIndex, "puk", voObj.getStrValue1(),2);//CR No
			daoObj.setProcInValue(nProcIndex, "catcode", voObj.getStrCatCode().replace("^","#").split("#")[0],3);//SENT FROM JS FUNCTION AND SET IN VO	
			daoObj.setProcInValue(nProcIndex, "clientcode", "",4);
			daoObj.setProcInValue(nProcIndex, "serviceType", voObj.getStrServiceType(),5);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),6);
			daoObj.setProcOutValue(nProcIndex, "err", 1,7);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2,8);
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) 
			{
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				voObj.setCreditLetterListWS(ws);
				
				
			} 
			else 
			{
				throw new Exception(strErr);
			}

		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("DAOBilling.getCreditLettersList() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}*/
	
}
