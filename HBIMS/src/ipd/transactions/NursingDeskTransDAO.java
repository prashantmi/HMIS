package ipd.transactions;

import ipd.IpdConfigUtil;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.*;

import javax.sql.rowset.WebRowSet;

public class NursingDeskTransDAO {

	// this function is used for Department code and a name
	public static void department(NursingDeskTransVO VO) {
		String strproc_name = "{call PKG_IPD_VIEW.Proc_Department(?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd","transactions.NursingDeskTransDAO.department()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value
			dao.setProcInValue(nprocIndex, "modeval", "1",1);
			dao.setProcInValue(nprocIndex, "hosp_code", VO.getStrHospitalCode(),2);
			dao.setProcInValue(nprocIndex, "puk", "",3);
			dao.setProcInValue(nprocIndex, "seatId", VO.getStrSeatId(),4);
			dao.setProcOutValue(nprocIndex, "err", 1,5); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "resultset", 2,6); // 2 for object
			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strerr = dao.getString(nprocIndex, "err");

			if (strerr == null)
				strerr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr.equals("")) {
				VO.setStrDepartmenttWs(ws);

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			VO.setStrMsgString("NursingDeskTransDAO.department() --> "+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	// this function is used for unit combo on the basis of department
	public static void unit(NursingDeskTransVO VO) {
		String strproc_name = "{call PKG_IPD_VIEW.Proc_Unit(?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd", "transactions.NursingDeskTransDAO.unit()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value
			dao.setProcInValue(nprocIndex, "modeval","1",1);
			dao.setProcInValue(nprocIndex, "dept_code", VO.getStrDepartment(),2);
			dao.setProcInValue(nprocIndex, "hosp_code", VO.getStrHospitalCode(),3);
			dao.setProcInValue(nprocIndex, "puk","",4);
			dao.setProcInValue(nprocIndex, "seatId", VO.getStrSeatId(),5);
			dao.setProcInValue(nprocIndex, "wardcode", "",6);
			dao.setProcInValue(nprocIndex, "unitcode", "",7);
			dao.setProcOutValue(nprocIndex, "err", 1,8); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "resultset", 2,9); // 2 for object
			
			
			
			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				VO.setStrUnitWs(ws);
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			VO.setStrMsgString("NursingDeskTransDAO.unit() --> "+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	public static void unitCombo(NursingDeskTransVO VO) {
		String strproc_name = "{call PKG_IPD_VIEW.Proc_Unit(?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd", "transactions.NursingDeskTransDAO.unit()");
			nprocIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nprocIndex, "modeval","3",1);
			dao.setProcInValue(nprocIndex, "dept_code", VO.getStrDepartment(),2);
			dao.setProcInValue(nprocIndex, "hosp_code", VO.getStrHospitalCode(),3);
			dao.setProcInValue(nprocIndex, "puk","",4);
			dao.setProcInValue(nprocIndex, "seatId", VO.getStrSeatId(),5);
			dao.setProcInValue(nprocIndex, "wardcode", VO.getStrWard(),6);
			dao.setProcInValue(nprocIndex, "unitcode", "",7);
			dao.setProcOutValue(nprocIndex, "err", 1,8); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "resultset", 2,9); // 2 for object
			
			
			
			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				VO.setStrUnitWs(ws);
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			VO.setStrMsgString("NursingDeskTransDAO.unit() --> "+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	

	// this function is used for ward combo on the basis of departmentunit code
	public static void ward(NursingDeskTransVO VO) {
		String strproc_name = "{call PKG_IPD_VIEW.proc_HIPT_WARD_MST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd", "transactions.NursingDeskTransDAO.ward()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value
			dao.setProcInValue(nprocIndex, "modeVal", "15",1);
			dao.setProcInValue(nprocIndex, "wardtypcode","0",2);
			dao.setProcInValue(nprocIndex, "deptcode",VO.getStrDepartment(),3);
			dao.setProcInValue(nprocIndex, "deptunitcode", VO.getStrUnit(),4);
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
				VO.setStrWardWs(ws);

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

	// this function is used for room combo on the basis of ward combo
	public static void room(NursingDeskTransVO VO) {
		String strproc_name = "{call  PKG_IPD_VIEW.proc_roomconfig(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd", "transactions.NursingDeskTransDAO.ward()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value
			dao.setProcInValue(nprocIndex, "modval", "2",1);
			dao.setProcInValue(nprocIndex, "roomtypcode","",2);
			dao.setProcInValue(nprocIndex, "wardcode", VO.getStrWard(),3);
			dao.setProcInValue(nprocIndex, "hosp_code", VO.getStrHospitalCode(),4);
			dao.setProcInValue(nprocIndex, "unitcode","",5);
			dao.setProcInValue(nprocIndex, "age","",6);
			dao.setProcInValue(nprocIndex, "deptcode","",7);
			dao.setProcInValue(nprocIndex, "deptunitcode","",8);
			dao.setProcInValue(nprocIndex, "gender","",9);
			dao.setProcInValue(nprocIndex, "treatment_cat","",10);
			dao.setProcInValue(nprocIndex, "puk_no","",11);
			dao.setProcInValue(nprocIndex, "diseasetypcode","0",12);
			dao.setProcOutValue(nprocIndex, "err", 1,13); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "resultset", 2,14); // 2 for object
			
			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				VO.setStrRoomWs(ws);

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			VO.setStrMsgString("NursingDeskTransDAO.room() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	// this function is used for setting admission detail of patient on the
	// basis of department,departmentunitcode,
	// ward and room
	public static void admitdetail(NursingDeskTransVO VO) 
	{
		String strproc_name = "{call  PKG_IPD_VIEW.Proc_tobe_accepted_Dtl(?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;
		IpdConfigUtil ipdUtil = new IpdConfigUtil(VO.getStrHospitalCode());
		
		System.out.println("Private Ward type "+ipdUtil.getStrPrivateWardType()+" "+VO.getStrWadType());

		try 
		{
			dao = new HisDAO("ipd","transactions.NursingDeskTransDAO.admitdetail()");			

			
			if(VO.getStrIsCancelMode()!=null && VO.getStrIsCancelMode().equals("CANCEL"))//In Transit Cancel
			{
				strproc_name = "{call  PKG_IPD_VIEW.Proc_tobe_accepted_Dtl(?,?,?,?,?,?,?,?,?)}";
				nprocIndex = dao.setProcedure(strproc_name);
				dao.setProcInValue(nprocIndex, "deptcode", VO.getStrDepartment(),1);
				dao.setProcInValue(nprocIndex, "mode_val", "2",2);
				dao.setProcInValue(nprocIndex, "deptunitcode", VO.getStrUnit(),3);
				dao.setProcInValue(nprocIndex, "wardcode", VO.getStrWard(),4);
				dao.setProcInValue(nprocIndex, "roomcode", VO.getStrRoom(),5);
				dao.setProcInValue(nprocIndex, "hosp_code", VO.getStrHospitalCode(),6);
				dao.setProcInValue(nprocIndex, "adm_no", VO.getStrAdmNo(),7);
			}
			
			else if (VO.getStrhtransinflag().equals("1") && VO.getStrWadType().equals(ipdUtil.getStrPrivateWardType())) {
				
				
				strproc_name = "{call  PKG_IPD_VIEW.Proc_tobe_accepted_Dtl(?,?,?,?,?,?,?,?,?)}";
				nprocIndex = dao.setProcedure(strproc_name);
				dao.setProcInValue(nprocIndex, "deptcode", VO.getStrDepartment(),1);
				dao.setProcInValue(nprocIndex, "mode_val", "3",2);
				dao.setProcInValue(nprocIndex, "deptunitcode", VO.getStrUnit(),3);
				dao.setProcInValue(nprocIndex, "wardcode", VO.getStrWard(),4);
				dao.setProcInValue(nprocIndex, "roomcode", VO.getStrRoom(),5);
				dao.setProcInValue(nprocIndex, "hosp_code", VO.getStrHospitalCode(),6);
				dao.setProcInValue(nprocIndex, "adm_no", VO.getStrAdmNo(),7);
			
		    }
			else
			{//Acceptance
				nprocIndex = dao.setProcedure(strproc_name);
				dao.setProcInValue(nprocIndex, "deptcode", VO.getStrDepartment(),1);
				dao.setProcInValue(nprocIndex, "mode_val", "1",2);
				dao.setProcInValue(nprocIndex, "deptunitcode", VO.getStrUnit(),3);
				dao.setProcInValue(nprocIndex, "wardcode", VO.getStrWard(),4);
				dao.setProcInValue(nprocIndex, "roomcode", VO.getStrRoom(),5);
				dao.setProcInValue(nprocIndex, "hosp_code", VO.getStrHospitalCode(),6);
				dao.setProcInValue(nprocIndex, "adm_no", "",7);
			}
			
			dao.setProcOutValue(nprocIndex, "err", 1,8); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "resultset", 2,9); // 2 for object
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");

			if (strerr == null)
				strerr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) 
			{
				VO.setStrAdmitDetailWs(ws);
			} 
			else
			{
				throw new Exception(strerr);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			VO.setStrMsgString("NursingDeskTransDAO.admitdetail() --> "	+ e.getMessage());
			VO.setStrMsgType("1");
		}
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;
			}
		}
	}
	
	
	
	/*
	 *  Used in IPD desk
	 *  While Acceptance Button is Clicked 
	 */
	public static void admitdetailIPD(NursingDeskTransVO VO) 
	{
		String strproc_name = "{call  PKG_IPD_VIEW.Proc_tobe_accepted_Dtl(?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;
		System.out.println("admitdetailIPD "+VO.getStrDepartment()+" "+VO.getStrUnit()+" "+VO.getStrWard()+" "+VO.getStrRoom()+" "+VO.getStrHospitalCode()+" "+VO.getStrAdmNo());

		try 
		{
			dao = new HisDAO("ipd","transactions.NursingDeskTransDAO.admitdetail()");			

			  
				strproc_name = "{call  PKG_IPD_VIEW.Proc_tobe_accepted_Dtl(?,?,?,?,?,?,?,?,?)}";
				nprocIndex = dao.setProcedure(strproc_name);
				dao.setProcInValue(nprocIndex, "deptcode", VO.getStrDepartment(),1);
				dao.setProcInValue(nprocIndex, "mode_val", "4",2);
				dao.setProcInValue(nprocIndex, "deptunitcode", VO.getStrUnit(),3);
				dao.setProcInValue(nprocIndex, "wardcode", VO.getStrWard(),4);
				dao.setProcInValue(nprocIndex, "roomcode", "0",5);
				dao.setProcInValue(nprocIndex, "hosp_code", VO.getStrHospitalCode(),6);
				dao.setProcInValue(nprocIndex, "adm_no", VO.getStrAdmNo(),7);
		     	dao.setProcOutValue(nprocIndex, "err", 1,8); // 1 for string return value
		     	dao.setProcOutValue(nprocIndex, "resultset", 2,9); // 2 for object
		     	dao.executeProcedureByPosition(nprocIndex);
		     	strerr = dao.getString(nprocIndex, "err");

				if (strerr == null)
					strerr = "";
				ws = dao.getWebRowSet(nprocIndex, "resultset");
				if (strerr.equals("")) 
				{
					VO.setStrAdmitDetailWs(ws);
				} 
				else
				{
					throw new Exception(strerr);
				}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			VO.setStrMsgString("NursingDeskTransDAO.admitdetail() --> "	+ e.getMessage());
			VO.setStrMsgType("1");
		}
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;
			}
		}
	}
	
	public static void admitdetailIPDNr(NursingDeskTransVO VO) 
	{
		String strproc_name = "{call  PKG_IPD_VIEW.Proc_tobe_accepted_Dtl(?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;
		System.out.println("admitdetailIPDNr "+VO.getStrDepartment()+" "+VO.getStrUnit()+" "+VO.getStrWard()+" "+VO.getStrRoom()+" "+VO.getStrHospitalCode()+" "+VO.getStrAdmNo());

		try 
		{
			dao = new HisDAO("ipd","transactions.NursingDeskTransDAO.admitdetailIPDNr()");			

			  
				strproc_name = "{call  PKG_IPD_VIEW.Proc_tobe_accepted_Dtl(?,?,?,?,?,?,?,?,?)}";
				nprocIndex = dao.setProcedure(strproc_name);
				dao.setProcInValue(nprocIndex, "deptcode", VO.getStrDepartment(),1);
				dao.setProcInValue(nprocIndex, "mode_val", "7",2);
				dao.setProcInValue(nprocIndex, "deptunitcode", VO.getStrUnit(),3);
				dao.setProcInValue(nprocIndex, "wardcode", VO.getStrWard(),4);
				dao.setProcInValue(nprocIndex, "roomcode", "0",5);
				dao.setProcInValue(nprocIndex, "hosp_code", VO.getStrHospitalCode(),6);
				dao.setProcInValue(nprocIndex, "adm_no", VO.getStrAdmNo(),7);
		     	dao.setProcOutValue(nprocIndex, "err", 1,8); // 1 for string return value
		     	dao.setProcOutValue(nprocIndex, "resultset", 2,9); // 2 for object
		     	dao.executeProcedureByPosition(nprocIndex);
		     	strerr = dao.getString(nprocIndex, "err");

				if (strerr == null)
					strerr = "";
				ws = dao.getWebRowSet(nprocIndex, "resultset");
				if (strerr.equals("")) 
				{
					VO.setStrAdmitDetailWs(ws);
				} 
				else
				{
					throw new Exception(strerr);
				}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			VO.setStrMsgString("NursingDeskTransDAO.admitdetailIpdNr() --> "	+ e.getMessage());
			VO.setStrMsgType("1");
		}
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;
			}
		}
	}

	// this function is used to set allocted bed value through Admission process
	public static void admitedbed(NursingDeskTransVO VO) {

		String strproc_name = "{call  PKG_IPD_VIEW.Proc_tobe_accepted_Dtl(?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd",
					"transactions.NursingDeskTransDAO.admitdetail()");
			nprocIndex = dao.setProcedure(strproc_name);

			// set value

			dao.setProcInValue(nprocIndex, "deptcode", VO.getStrDepartment(),1);
			dao.setProcInValue(nprocIndex, "mode_val", "1",2);
			dao.setProcInValue(nprocIndex, "deptunitcode", VO.getStrUnit(),3);
			dao.setProcInValue(nprocIndex, "wardcode", VO.getStrWard(),4);
			dao.setProcInValue(nprocIndex, "roomcode", VO.getStrRoom(),5);
			dao.setProcInValue(nprocIndex, "hosp_code", VO.getStrHospitalCode(),6);
			dao.setProcInValue(nprocIndex, "adm_no", "",7);
			dao.setProcOutValue(nprocIndex, "err", 1,8); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "resultset", 2,9); // 2 for object
		    // execute procedure
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strerr = dao.getString(nprocIndex, "err");

			if (strerr == null)
				strerr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr.equals("")) {
				String[] arr = new String[ws.size()];
				int count = 0;
				while (ws.next()) {
					arr[count] = ws.getString(6);
					count++;
					}
				VO.setStradmBed(arr);
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			VO.setStrMsgString("NursingDeskTransDAO.admitdetail() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}


	public static void checklistdetail(NursingDeskTransVO VO) 
	{

		String strproc_name = "{call pkg_ipd_view.Proc_nurse_checklist(?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("ipd",
					"transactions.NursingDeskTransDAO.checklistdetail()");
			nprocIndex = dao.setProcedure(strproc_name);

			// set value
			dao.setProcInValue(nprocIndex, "hosp_code", VO.getStrHospitalCode(),1);
			dao.setProcInValue(nprocIndex, "deptunit", VO.getStrUnit(),2);
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
				VO.setStrChecklistWs(ws);

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			VO.setStrMsgString("NursingDeskTransDAO.checklistdetail() --> "+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	
	/**
	 * This function is used to bring the bed Combo
	 * @param vo
	 */
	public static void getBed(NursingDeskTransVO VO) 
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try 
		{
			daoObj = new HisDAO("ipd","transactions.NursingDeskTransDAO.getBedValues()");
			nProcIndex = daoObj.setProcedure(strProcName);
			//daoObj.setProcInValue(nProcIndex, "modval", "3");
			daoObj.setProcInValue(nProcIndex, "modval", "7",1);
			daoObj.setProcInValue(nProcIndex, "wardcode", VO.getStrWard(),2);
			daoObj.setProcInValue(nProcIndex, "roomno", VO.getStrRoom(),3);
			daoObj.setProcInValue(nProcIndex, "bedstatcode", "10",4); // HARDCODED
			daoObj.setProcInValue(nProcIndex, "bedtypcode", "0",5); 
			daoObj.setProcInValue(nProcIndex, "hosp_code", VO.getStrHospitalCode(),6);
			daoObj.setProcInValue(nProcIndex, "deptunit", VO.getStrUnit(),7);
			daoObj.setProcInValue(nProcIndex, "bedCode", "",8);
			daoObj.setProcInValue(nProcIndex, "shr_chk", "",9);
			daoObj.setProcOutValue(nProcIndex, "err", 1,10);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,11);
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			
			if (strErr == null)
				strErr = "";
			
		
			if (strErr.equals("")) {
			
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");				
				VO.setStrBedDetailWS(ws);
			
				
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			e.printStackTrace();
			VO.setStrMsgString("NursingDeskTransDAO.getBedValues() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}


	/**
	 * This function is used to bring the bed details on bed details pop up
	 * window on the basis of ward code,room number,bed type code
	 * 
	 * @param vo
	 */
	public static void getBedValues(NursingDeskTransVO VO) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try {
			daoObj = new HisDAO("ipd","transactions.NursingDeskTransDAO.getBedValues()");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "4",1);
			daoObj.setProcInValue(nProcIndex, "wardcode", VO.getStrWard(),2);
			daoObj.setProcInValue(nProcIndex, "roomno", VO.getStrRoom(),3);
			daoObj.setProcInValue(nProcIndex, "bedtypcode", "0",4); // HARDCODED
			daoObj.setProcInValue(nProcIndex, "bedstatcode", "",5); // HARDCODED
			daoObj.setProcInValue(nProcIndex, "hosp_code", VO.getStrHospitalCode(),6);
			daoObj.setProcInValue(nProcIndex, "deptunit", VO.getStrUnit(),7);
			daoObj.setProcInValue(nProcIndex, "bedCode", "",8);
			daoObj.setProcInValue(nProcIndex, "shr_chk", "",9);
			daoObj.setProcOutValue(nProcIndex, "err", 1,10);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,11);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			
			if (strErr == null)
				strErr = "";		
			if (strErr.equals("")) {			
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");				
				VO.setStrBedDetailWS(ws);				
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			VO.setStrMsgString("NursingDeskTransDAO.getBedValues() --> "+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	public static void save(NursingDeskTransVO VO) {
		
		String strProcName = "";
		String strProcName1 = "";
		String strProcName2 ="{call pkg_ipd_dml.proc_ip_mac(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		int nProcIndex2 = 0;
		String strErr = "";
		String strErr1 = "";
		HisDAO daoObj = null;
		HisUtil util=new HisUtil("ipd","save");
		
		System.out.println("strroom"+VO.getStrRoom()+" "+VO.getStrhtransinflag()+" "+VO.getHiddenbed().replace("^","#").split("#")[1]);
		System.out.println("VO.getHiddenbed()"+VO.getHiddenbed());
		try {
			daoObj = new HisDAO("PATIENT ACCEPTANCE", "NursingDeskTransDAO");
			
			VO.getStrhtransinflag();
			if ((VO.getStrhtransinflag().equals("0")|| 
					VO.getStrhtransinflag().equals("2") || 
					VO.getStrhtransinflag().equals("5") || 
					VO.getStrhtransinflag().equals("6")) && 
					VO.getStrIsCancelMode()==null) 
			{
				if(!(VO.getHiddenbelonging().equals(null) || VO.getHiddenbelonging().equals(""))  ){
					
				
						strProcName1 = "{call pkg_ipd_dml.proc_hipt_pat_belonging_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
						
						nProcIndex = daoObj.setProcedure(strProcName1);
						daoObj.setProcInValue(nProcIndex, "modval", "4",1);
						daoObj.setProcInValue(nProcIndex, "hipstr_belong_desc", "",2);
						daoObj.setProcInValue(nProcIndex, "hipstr_belong_qty", "",3);
						daoObj.setProcInValue(nProcIndex, "hipstr_benlog_remark", "",4);
						daoObj.setProcInValue(nProcIndex, "hgnum_deptunitcode", "",5);
						daoObj.setProcInValue(nProcIndex, "hipnum_ward_code", "",6);
						daoObj.setProcInValue(nProcIndex, "to_deptunitcode", VO.getStrUnit(),7);
						daoObj.setProcInValue(nProcIndex, "to_ward_code", VO.getStrWard(),8);
						daoObj.setProcInValue(nProcIndex, "gdt_entry_date", util.getASDate("dd/mmm/yyyy"),9);	
						daoObj.setProcInValue(nProcIndex, "gnum_seatid", VO.getStrSeatId(),10);
						daoObj.setProcInValue(nProcIndex, "gnum_isvalid", "",11);
						daoObj.setProcInValue(nProcIndex, "hipdt_return_datetime", "",12);
						daoObj.setProcInValue(nProcIndex, "hipstr_return_to", "",13);
						daoObj.setProcInValue(nProcIndex, "hipnum_admno", VO.getHiddenadmno(),14);
						daoObj.setProcInValue(nProcIndex, "hipnum_belong_slno", "",15);
						daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", VO.getStrHospitalCode(),16);
						daoObj.setProcInValue(nProcIndex, "hrgnum_puk", "",17);
						daoObj.setProcInValue(nProcIndex, "return_rmks", "",18);
						daoObj.setProcInValue(nProcIndex, "gnum_relation_code", "",19);
						daoObj.setProcInValue(nProcIndex, "item_type", "",20);
						daoObj.setProcInValue(nProcIndex, "item_id", "",21);
						daoObj.setProcInValue(nProcIndex, "status", "",22);
						daoObj.setProcOutValue(nProcIndex, "err", 1,23);
						daoObj.setProcOutValue(nProcIndex, "result", 1,24);
						daoObj.setProcOutValue(nProcIndex, "dml_count", 1,25);
						          
						daoObj.execute(nProcIndex, 1);
				}
				
				strProcName = "{call  pkg_ipd_dml.proc_patient_acceptance(?,?,?,?,  ?,?,?,?,  ?,?,?,?,  ?,?,?,?,  ?,?,?,?,  ?,?,?,?,  ?)}";
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "hipnum_admno", VO.getHiddenadmno(),1);
				daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", VO.getStrHospitalCode(),2);
				daoObj.setProcInValue(nProcIndex, "hrgnum_puk", VO.getHiddencrno(),3);
				daoObj.setProcInValue(nProcIndex, "gnum_seatid", VO.getStrSeatId(),4);
				daoObj.setProcInValue(nProcIndex, "code_remarks", VO.getHiddenchkremark(),5);
				daoObj.setProcInValue(nProcIndex, "hipdt_bedalloc_datetime","",6);
				daoObj.setProcInValue(nProcIndex, "hipnum_isaccepted", "1",7);
				daoObj.setProcInValue(nProcIndex, "gnum_nurse_seatid", VO.getStrSeatId(),8);
				/*if(VO.getStrIsSharableFlag().equals("0"))
				daoObj.setProcInValue(nProcIndex, "hgnum_bed_status_code","12",9);
				else*/
				
				//if(VO.getStrhtransinflag().equals("2"))
				//daoObj.setProcInValue(nProcIndex, "hgnum_bed_status_code","12",9);
				//else
				daoObj.setProcInValue(nProcIndex, "hgnum_bed_status_code","12",9);
				
				daoObj.setProcInValue(nProcIndex, "hipnum_ward_code", VO.getStrWard(),10);
				
				daoObj.setProcInValue(nProcIndex, "hipnum_room_code",  VO.getHiddenbed().replace("^","#").split("#")[0],11);
				
   //             if(VO.getStrhtransinflag().equals("2"))
				if(VO.getHiddenbed().replace("^","#").split("#").length	>	3)
                daoObj.setProcInValue(nProcIndex, "hipnum_bed_code", VO.getHiddenbed().replace("^","#").split("#")[2],12);
				else
				daoObj.setProcInValue(nProcIndex, "hipnum_bed_code", VO.getHiddenbed().replace("^","#").split("#")[1],12);	
     //           else
		//		daoObj.setProcInValue(nProcIndex, "hipnum_bed_code", VO.getHiddenbed().replace("^","#").split("#")[2],12);
                
				daoObj.setProcInValue(nProcIndex, "belonging_remarks", "",13);
				daoObj.setProcInValue(nProcIndex, "hipdt_admstatus_code", "12",14);
				daoObj.setProcInValue(nProcIndex, "hgnum_deptunitcode",  VO.getStrUnit(),15);
				daoObj.setProcInValue(nProcIndex, "gnum_dept_code", VO.getStrDepartment(),16);
				daoObj.setProcInValue(nProcIndex, "gnum_owndept_code","",17);
				daoObj.setProcInValue(nProcIndex, "gnum_owndeptunit_code","",18);
				daoObj.setProcInValue(nProcIndex, "hipnum_ownward_code","",19);	
				daoObj.setProcInValue(nProcIndex, "mov_no", VO.getStrhmoveno(),20);
				daoObj.setProcInValue(nProcIndex, "billFlag",  VO.getStrBillFlag(),21);
				daoObj.setProcInValue(nProcIndex, "bedChargeFlag",  VO.getStrBedFlag(),22); //--0 MEANS MANUALLY
				
				daoObj.setProcInValue(nProcIndex, "consultant_id",  VO.getStrConsultantCode(),23);
				daoObj.setProcOutValue(nProcIndex, "err", 1,24);
				daoObj.setProcOutValue(nProcIndex, "dml_count", 1,25);
				//daoObj.setProcInValue(nProcIndex, "hipnum_room_code", VO.getStrRoom());
				//daoObj.setProcInValue(nProcIndex, "hipnum_bed_code", VO.getHiddenbed());
				daoObj.execute(nProcIndex, 1);
				
				strProcName1 = "{call pkg_ipd_dml.proc_hipt_pat_belonging_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
				if(VO.getStrItemName()!=null)
				{
					for (int i = 0; i < VO.getStrItemName().length; i++) {
						nProcIndex = daoObj.setProcedure(strProcName1);
						daoObj.setProcInValue(nProcIndex, "modval", "1",1);
						daoObj.setProcInValue(nProcIndex, "hipstr_belong_desc",VO.getStrItemName()[i],2);
						daoObj.setProcInValue(nProcIndex, "hipstr_belong_qty",VO.getStrItemQuantity()[i],3);
						daoObj.setProcInValue(nProcIndex, "hipstr_benlog_remark",VO.getStrItemRemarks()[i],4);
						daoObj.setProcInValue(nProcIndex, "hgnum_deptunitcode", VO.getStrUnit(),5);
						daoObj.setProcInValue(nProcIndex, "hipnum_ward_code", VO.getStrWard(),6);
						daoObj.setProcInValue(nProcIndex, "to_deptunitcode","",7);
						daoObj.setProcInValue(nProcIndex, "to_ward_code","",8);	
						daoObj.setProcInValue(nProcIndex, "gdt_entry_date", util.getASDate("dd/mmm/yyyy"),9);		
						daoObj.setProcInValue(nProcIndex, "gnum_seatid", VO.getStrSeatId(),10);
						daoObj.setProcInValue(nProcIndex, "gnum_isvalid", "1",11);
						daoObj.setProcInValue(nProcIndex, "hipdt_return_datetime", "",12);
						daoObj.setProcInValue(nProcIndex, "hipstr_return_to", "",13);
						daoObj.setProcInValue(nProcIndex, "hipnum_admno", VO.getHiddenadmno(),14);
						daoObj.setProcInValue(nProcIndex, "hipnum_belong_slno", "",15);
						daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", VO.getStrHospitalCode(),16);
						daoObj.setProcInValue(nProcIndex, "hrgnum_puk", VO.getHiddencrno(),17);
						daoObj.setProcInValue(nProcIndex, "return_rmks", "",18);
						daoObj.setProcInValue(nProcIndex, "gnum_relation_code", "",19);
						daoObj.setProcInValue(nProcIndex, "item_type",VO.getStrItemType()[i],20);
						daoObj.setProcInValue(nProcIndex, "item_id",VO.getStrItemId()[i],21);
						daoObj.setProcInValue(nProcIndex, "status", "",22);
						daoObj.setProcOutValue(nProcIndex, "err", 1,23);
						daoObj.setProcOutValue(nProcIndex, "result", 1,24);
						daoObj.setProcOutValue(nProcIndex, "dml_count", 1,25);
						
						daoObj.execute(nProcIndex, 1);
					}
				}
				synchronized(daoObj){
					daoObj.fire();
				 }
				
			}else if(VO.getStrIsCancelMode()==null){
				strProcName = "{call  pkg_ipd_dml.proc_movement_acceptance(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "hipnum_admno", VO.getHiddenadmno(),1);
				daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", VO.getStrHospitalCode(),2);
				daoObj.setProcInValue(nProcIndex, "hrgnum_puk", VO.getHiddencrno(),3);
				daoObj.setProcInValue(nProcIndex, "gnum_seatid", VO.getStrSeatId(),4);
				daoObj.setProcInValue(nProcIndex, "gnum_dept_code", VO.getStrDepartment(),5);
				daoObj.setProcInValue(nProcIndex, "gnum_deptunit_code", VO.getStrUnit(),6);
				daoObj.setProcInValue(nProcIndex, "hipnum_ward_code", VO.getStrWard(),7);
				//daoObj.setProcInValue(nProcIndex, "hipnum_room_code", VO.getStrRoom());
				//daoObj.setProcInValue(nProcIndex, "hipnum_bed_code", VO.getHiddenbed());
				if(VO.getHiddenbed().replace("^","#").split("#").length>=2)
				{
					
					System.out.println("inside");//if(VO.getStrRoom().equals("0"))
					//{
					daoObj.setProcInValue(nProcIndex, "hipnum_room_code",  VO.getHiddenbed().replace("^","#").split("#")[0],8);
					daoObj.setProcInValue(nProcIndex, "hipnum_bed_code", VO.getHiddenbed().replace("^","#").split("#")[1],9);
					/*}
					else
					{
						daoObj.setProcInValue(nProcIndex, "hipnum_room_code", VO.getStrRoom());
						daoObj.setProcInValue(nProcIndex, "hipnum_bed_code", VO.getHiddenbed().replace("^","#").split("#")[1]);
					}*/
				}
				else
				{
					daoObj.setProcInValue(nProcIndex, "hipnum_room_code", VO.getStrRoom(),8);
					daoObj.setProcInValue(nProcIndex, "hipnum_bed_code", VO.getHiddenbed(),9);
				}
				daoObj.setProcInValue(nProcIndex, "hipnum_transin_flg","10",10);
				daoObj.setProcInValue(nProcIndex, "code_remarks",VO.getHiddenchkremark(),11);
				daoObj.setProcInValue(nProcIndex, "belonging_remarks",VO.getHiddenbelonging(),12);
				daoObj.setProcOutValue(nProcIndex, "err", 1,13);
				daoObj.setProcOutValue(nProcIndex, "dml_count", 1,14);
				daoObj.executeProcedureByPosition(nProcIndex);
			} 
			else if(VO.getStrIsCancelMode()!=null && VO.getStrIsCancelMode().equals("CANCEL"))
			{
				strProcName = "{call  pkg_ipd_dml.proc_patient_cancel(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "hipnum_admno", VO.getHiddenadmno(),1);
				daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", VO.getStrHospitalCode(),2);
				daoObj.setProcInValue(nProcIndex, "hrgnum_puk", VO.getHiddencrno(),3);
				daoObj.setProcInValue(nProcIndex, "gnum_seatid", VO.getStrSeatId(),4);
				daoObj.setProcInValue(nProcIndex, "code_remarks", VO.getHiddenchkremark(),5);
				daoObj.setProcInValue(nProcIndex, "hipdt_bedalloc_datetime","",6);
				daoObj.setProcInValue(nProcIndex, "hipnum_isaccepted", "1",7);
				daoObj.setProcInValue(nProcIndex, "gnum_nurse_seatid", VO.getStrSeatId(),8);
				daoObj.setProcInValue(nProcIndex, "hgnum_bed_status_code","12",9);
				daoObj.setProcInValue(nProcIndex, "hipnum_ward_code", VO.getStrWard(),10);
				if(VO.getHiddenbed().replace("^","#").split("#").length>=2)
				{
					//if(VO.getStrRoom().equals("0"))
					//{
					daoObj.setProcInValue(nProcIndex, "hipnum_room_code",  VO.getHiddenbed().replace("^","#").split("#")[0],11);
					daoObj.setProcInValue(nProcIndex, "hipnum_bed_code", VO.getHiddenbed().replace("^","#").split("#")[1],12);
					/*}
					else
					{
						daoObj.setProcInValue(nProcIndex, "hipnum_room_code", VO.getStrRoom());
						daoObj.setProcInValue(nProcIndex, "hipnum_bed_code", VO.getHiddenbed().replace("^","#").split("#")[1]);
					}*/
				}
				else
				{
					daoObj.setProcInValue(nProcIndex, "hipnum_room_code", VO.getStrRoom(),11);
					daoObj.setProcInValue(nProcIndex, "hipnum_bed_code", VO.getHiddenbed(),12);
				}
				daoObj.setProcInValue(nProcIndex, "belonging_remarks", "",13);
				daoObj.setProcInValue(nProcIndex, "hipdt_admstatus_code", "12",14);
				daoObj.setProcInValue(nProcIndex, "hgnum_deptunitcode",  VO.getStrUnit(),15);
				daoObj.setProcInValue(nProcIndex, "gnum_dept_code", VO.getStrDepartment(),16);
				daoObj.setProcInValue(nProcIndex, "gnum_owndept_code","",17);
				daoObj.setProcInValue(nProcIndex, "gnum_owndeptunit_code","",18);
				daoObj.setProcInValue(nProcIndex, "hipnum_ownward_code","",19);
				daoObj.setProcInValue(nProcIndex, "mov_no", VO.getStrhmoveno(),20);
				daoObj.setProcInValue(nProcIndex, "billFlag",  VO.getStrBillFlag(),21);
				daoObj.setProcInValue(nProcIndex, "bedChargeFlag",  VO.getStrBedFlag(),22);// --0 MEANS MANUALLY
				//daoObj.setProcInValue(nProcIndex, "hipnum_room_code", VO.getStrRoom());
				daoObj.setProcInValue(nProcIndex, "consultant_id",  VO.getStrConsultantCode(),23);
				daoObj.setProcOutValue(nProcIndex, "err", 1,24);
				daoObj.setProcOutValue(nProcIndex, "dml_count", 1,25);
				
				daoObj.execute(nProcIndex, 1);
				
				strProcName1 = "{call pkg_ipd_dml.proc_hipt_pat_belonging_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

				nProcIndex = daoObj.setProcedure(strProcName1);
				daoObj.setProcInValue(nProcIndex, "modval", "5",1);
				daoObj.setProcInValue(nProcIndex, "hipstr_belong_desc","",2);
				daoObj.setProcInValue(nProcIndex, "hipstr_belong_qty","",3);
				daoObj.setProcInValue(nProcIndex, "hipstr_benlog_remark","",4);
				daoObj.setProcInValue(nProcIndex, "hgnum_deptunitcode", VO.getStrUnit(),5);
				daoObj.setProcInValue(nProcIndex, "hipnum_ward_code", VO.getStrWard(),6);
				daoObj.setProcInValue(nProcIndex, "to_deptunitcode","",7);
				daoObj.setProcInValue(nProcIndex, "to_ward_code","",8);
				daoObj.setProcInValue(nProcIndex, "gdt_entry_date", util.getASDate("dd/mmm/yyyy"),9);		
				daoObj.setProcInValue(nProcIndex, "gnum_seatid", "",10);
				daoObj.setProcInValue(nProcIndex, "gnum_isvalid", "1",11);	
				daoObj.setProcInValue(nProcIndex, "hipdt_return_datetime", "",12);
				daoObj.setProcInValue(nProcIndex, "hipstr_return_to", "",13);
				daoObj.setProcInValue(nProcIndex, "hipnum_admno", VO.getHiddenadmno(),14);
				daoObj.setProcInValue(nProcIndex, "hipnum_belong_slno", "",15);
				daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", VO.getStrHospitalCode(),16);
				daoObj.setProcInValue(nProcIndex, "hrgnum_puk","",17);
				daoObj.setProcInValue(nProcIndex, "return_rmks", "",18);
				daoObj.setProcInValue(nProcIndex, "gnum_relation_code", "",19);
				daoObj.setProcInValue(nProcIndex, "item_id","",20);
				daoObj.setProcInValue(nProcIndex, "item_type","",21);
				daoObj.setProcInValue(nProcIndex, "status", "",22);
				daoObj.setProcOutValue(nProcIndex, "err", 1,23);
				daoObj.setProcOutValue(nProcIndex, "result", 1,24);
				daoObj.setProcOutValue(nProcIndex, "dml_count", 1,25);
				
				daoObj.execute(nProcIndex, 1);
				
				
				synchronized(daoObj){
					daoObj.fire();
				}
			}
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			if (strErr.equals("")) {
			} else {
				throw new Exception(strErr);
			}
			
			nProcIndex2 = daoObj.setProcedure(strProcName2);
			daoObj.setProcInValue(nProcIndex2, "modeval", "1",1);									
			daoObj.setProcInValue(nProcIndex2, "adm_no", VO.getHiddenadmno(),2);	//							
			daoObj.setProcInValue(nProcIndex2, "hospital_code",  VO.getStrHospitalCode(),3);					
			daoObj.setProcInValue(nProcIndex2, "puk", VO.getHiddencrno(),4);		
			daoObj.setProcInValue(nProcIndex2, "mov_no", VO.getStrhmoveno(),5);	
			daoObj.setProcInValue(nProcIndex2, "ip", GetNetworkAddress.GetAddress("ip"),6);			
			daoObj.setProcInValue(nProcIndex2, "mac", GetNetworkAddress.GetAddress("mac"),7);		//				
			daoObj.setProcOutValue(nProcIndex2, "err", 1,8);		//			
			daoObj.executeProcedureByPosition(nProcIndex2);
			
			
			strErr1 = daoObj.getString(nProcIndex2, "err");
			if (strErr1 == null)
				strErr1 = "";
			if (strErr1.equals("")) {
			} else {
				throw new Exception(strErr1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			VO.setStrMsgString("NursingDeskTransDAO.save() --> "+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
	
	
public static void saveCancel(NursingDeskTransVO VO) {
		
		String strProcName = "";
		String strProcName1 = "";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		HisUtil util=new HisUtil("ipd","save");
		
		//System.out.println("strroom"+VO.getStrRoom()+" "+VO.getStrhtransinflag()+" "+VO.getHiddenbed().replace("^","#").split("#")[1]);
		//System.out.println("VO.getHiddenbed()"+VO.getHiddenbed());
		try {
			    daoObj = new HisDAO("PATIENT ACCEPTANCE", "NursingDeskTransDAO");
				strProcName = "{call  pkg_ipd_dml.proc_patient_cancel(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "hipnum_admno", VO.getHiddenadmno(),1);
				daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", VO.getStrHospitalCode(),2);
				daoObj.setProcInValue(nProcIndex, "hrgnum_puk", VO.getHiddencrno(),3);
				daoObj.setProcInValue(nProcIndex, "gnum_seatid", VO.getStrSeatId(),4);
				daoObj.setProcInValue(nProcIndex, "code_remarks", VO.getHiddenchkremark(),5);
				daoObj.setProcInValue(nProcIndex, "hipdt_bedalloc_datetime","",6);
				daoObj.setProcInValue(nProcIndex, "hipnum_isaccepted", "1",7);
				daoObj.setProcInValue(nProcIndex, "gnum_nurse_seatid", VO.getStrSeatId(),8);
				daoObj.setProcInValue(nProcIndex, "hgnum_bed_status_code","12",9);
				daoObj.setProcInValue(nProcIndex, "hipnum_ward_code", VO.getStrWard(),10);
				if(VO.getHiddenbed().replace("^","#").split("#").length>=2)
				{
					//if(VO.getStrRoom().equals("0"))
					//{
					daoObj.setProcInValue(nProcIndex, "hipnum_room_code",  VO.getHiddenbed().replace("^","#").split("#")[0],11);
					daoObj.setProcInValue(nProcIndex, "hipnum_bed_code", VO.getHiddenbed().replace("^","#").split("#")[2],12);
					/*}
					else
					{
						daoObj.setProcInValue(nProcIndex, "hipnum_room_code", VO.getStrRoom());
						daoObj.setProcInValue(nProcIndex, "hipnum_bed_code", VO.getHiddenbed().replace("^","#").split("#")[1]);
					}*/
				}
				else
				{
					daoObj.setProcInValue(nProcIndex, "hipnum_room_code", VO.getStrRoom(),11);
					daoObj.setProcInValue(nProcIndex, "hipnum_bed_code", VO.getHiddenbed(),12);
				}
				daoObj.setProcInValue(nProcIndex, "belonging_remarks", "",13);
				daoObj.setProcInValue(nProcIndex, "hipdt_admstatus_code", "12",14);
				daoObj.setProcInValue(nProcIndex, "hgnum_deptunitcode",  VO.getStrUnit(),15);
				daoObj.setProcInValue(nProcIndex, "gnum_dept_code", VO.getStrDepartment(),16);
				daoObj.setProcInValue(nProcIndex, "gnum_owndept_code","",17);
				daoObj.setProcInValue(nProcIndex, "gnum_owndeptunit_code","",18);
				daoObj.setProcInValue(nProcIndex, "hipnum_ownward_code","",19);
				daoObj.setProcInValue(nProcIndex, "mov_no", VO.getStrhmoveno(),20);
				daoObj.setProcInValue(nProcIndex, "billFlag",  VO.getStrBillFlag(),21);
				daoObj.setProcInValue(nProcIndex, "bedChargeFlag",  VO.getStrBedFlag(),22);// --0 MEANS MANUALLY
				//daoObj.setProcInValue(nProcIndex, "hipnum_room_code", VO.getStrRoom());
				daoObj.setProcInValue(nProcIndex, "consultant_id",  VO.getStrConsultantCode(),23);
				daoObj.setProcOutValue(nProcIndex, "err", 1,24);
				daoObj.setProcOutValue(nProcIndex, "dml_count", 1,25);
				
				daoObj.execute(nProcIndex, 1);
				
				strProcName1 = "{call pkg_ipd_dml.proc_hipt_pat_belonging_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

				nProcIndex = daoObj.setProcedure(strProcName1);
				daoObj.setProcInValue(nProcIndex, "modval", "5",1);
				daoObj.setProcInValue(nProcIndex, "hipstr_belong_desc","",2);
				daoObj.setProcInValue(nProcIndex, "hipstr_belong_qty","",3);
				daoObj.setProcInValue(nProcIndex, "hipstr_benlog_remark","",4);
				daoObj.setProcInValue(nProcIndex, "hgnum_deptunitcode", VO.getStrUnit(),5);
				daoObj.setProcInValue(nProcIndex, "hipnum_ward_code", VO.getStrWard(),6);
				daoObj.setProcInValue(nProcIndex, "to_deptunitcode","",7);
				daoObj.setProcInValue(nProcIndex, "to_ward_code","",8);
				daoObj.setProcInValue(nProcIndex, "gdt_entry_date", util.getASDate("dd/mmm/yyyy"),9);		
				daoObj.setProcInValue(nProcIndex, "gnum_seatid", "",10);
				daoObj.setProcInValue(nProcIndex, "gnum_isvalid", "1",11);	
				daoObj.setProcInValue(nProcIndex, "hipdt_return_datetime", "",12);
				daoObj.setProcInValue(nProcIndex, "hipstr_return_to", "",13);
				daoObj.setProcInValue(nProcIndex, "hipnum_admno", VO.getHiddenadmno(),14);
				daoObj.setProcInValue(nProcIndex, "hipnum_belong_slno", "",15);
				daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", VO.getStrHospitalCode(),16);
				daoObj.setProcInValue(nProcIndex, "hrgnum_puk","",17);
				daoObj.setProcInValue(nProcIndex, "return_rmks", "",18);
				daoObj.setProcInValue(nProcIndex, "gnum_relation_code", "",19);
				daoObj.setProcInValue(nProcIndex, "item_id","",20);
				daoObj.setProcInValue(nProcIndex, "item_type","",21);
				daoObj.setProcInValue(nProcIndex, "status", "",22);
				daoObj.setProcOutValue(nProcIndex, "err", 1,23);
				daoObj.setProcOutValue(nProcIndex, "result", 1,24);
				daoObj.setProcOutValue(nProcIndex, "dml_count", 1,25);
				
				daoObj.execute(nProcIndex, 1);
				
				synchronized(daoObj){
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
			e.printStackTrace();
			VO.setStrMsgString("NursingDeskTransDAO.save() --> "+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	public static void notreport(NursingDeskTransVO VO) {
		String strProcName = "";

		int nProcIndex = 0;
		String strErr = "";
		
		HisDAO daoObj = null;

		try {
			daoObj = new HisDAO("PATIENT ACCEPTANCE", "NursingDeskTransDAO");
			strProcName = "{call  pkg_ipd_dml.proc_notreporting(?,?,?,?,?,?,?,?,?,?)}";
			nProcIndex = daoObj.setProcedure(strProcName);
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "adm_no", VO.getHiddenadmno(),1);
			daoObj.setProcInValue(nProcIndex, "adm_status", "13",2);
			daoObj.setProcInValue(nProcIndex, "hospital_code", VO.getStrHospitalCode(),3);
			daoObj.setProcInValue(nProcIndex, "mov_no", VO.getStrhmoveno(),4);
			daoObj.setProcInValue(nProcIndex, "trans_out_flg", "13",5);
			daoObj.setProcInValue(nProcIndex, "puk", VO.getHiddencrno(),6);
			daoObj.setProcInValue(nProcIndex, "seatId", VO.getStrSeatId(),7);
			daoObj.setProcInValue(nProcIndex, "remarks",((VO.getStrRemarks() == null || VO.getStrRemarks().equals(" ")) ? "--" : VO.getStrRemarks()) ,8);
			daoObj.setProcInValue(nProcIndex, "billFlag", VO.getStrBillFlag(),9);
			daoObj.setProcOutValue(nProcIndex, "err", 1,10);

			daoObj.executeProcedureByPosition(nProcIndex);		

			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			if (strErr.equals("")) {
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			VO.setStrMsgString("NursingDeskTransDAO.notreport() --> "+ e.getMessage());
			VO.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
		
	}
	/**
	 * This function is used to bring the bed Values in bed combo
	 * 
	 * @param voObj
	 */
	public static void getBedCombo(NursingDeskTransVO voObj) 
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		
		//System.out.println(voObj.getStrWard()+" "+voObj.getStrRoom()+" "+voObj.getStrUnit()+" "+voObj.getStrHospitalCode());
		try 
		{
			daoObj = new HisDAO("Patient Acceptance","NursingDeskTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "13",1);
			daoObj.setProcInValue(nProcIndex, "wardcode", voObj.getStrWard(),2);
			daoObj.setProcInValue(nProcIndex, "roomno", voObj.getStrRoom(),3);
			daoObj.setProcInValue(nProcIndex, "bedtypcode", "0",4);
			daoObj.setProcInValue(nProcIndex, "bedstatcode", "10",5);
			//daoObj.setProcInValue(nProcIndex, "bedstatcode", "12",5);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),6);
			daoObj.setProcInValue(nProcIndex, "deptunit", voObj.getStrUnit(),7);
			daoObj.setProcInValue(nProcIndex, "bedCode","",8);
			daoObj.setProcInValue(nProcIndex, "shr_chk",voObj.getSharableChk(),9);
			//System.out.println("shr_chk from Dao---------------------------------------------------------->"+voObj.getSharableChk());
			daoObj.setProcOutValue(nProcIndex, "err", 1,10);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,11);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				voObj.setStrBedDetailWS(ws);
			} 
			else 
			{
				throw new Exception(strErr);
			}			
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("PatientTransferTransDAO.getBedValues() --> "+ e.getMessage());
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
	}
	
	
	/**
	 * This function is used to bring the bed Combo when we click plus or minus button in patient acceptance process
	 * added by debashis
	 * @param vo
	 */
	public static void BEDCOMBOAJAX(NursingDeskTransVO VO) {
		String strProcName = "{call  pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nProcIndex = 0;
		String strerr = "";
		WebRowSet ws = null;
		HisDAO daoObj = null;
		
		
		System.out.println(VO.getStrWard()+" "+VO.getStrRoom()+" "+VO.getStrUnit()+" "+VO.getStrHospitalCode());

		try {
			daoObj = new HisDAO("ipd","transactions.NursingDeskTransDAO.getBed()");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			if((VO.getStrBedFlag()).equals("0"))
		    daoObj.setProcInValue(nProcIndex, "modval", "9",1);
			else
			daoObj.setProcInValue(nProcIndex, "modval", "3",1);	
			daoObj.setProcInValue(nProcIndex, "wardcode", VO.getStrWard(),2);
			daoObj.setProcInValue(nProcIndex, "roomno", VO.getStrRoom(),3);
			daoObj.setProcInValue(nProcIndex, "bedtypcode", "0",4);
			if(VO.getStrBedFlag().equals("0"))
		    daoObj.setProcInValue(nProcIndex, "bedstatcode", "12",5); // HardCoded 12 Means Occupied.
			else
			daoObj.setProcInValue(nProcIndex, "bedstatcode", "10",5);	//HardCoded 10 Means Vacant.
			daoObj.setProcInValue(nProcIndex, "hosp_code", VO.getStrHospitalCode(),6);
			daoObj.setProcInValue(nProcIndex, "deptunit", VO.getStrUnit(),7);
			daoObj.setProcInValue(nProcIndex, "bedCode", "",8);
			daoObj.setProcInValue(nProcIndex, "shr_chk", "",9);
			daoObj.setProcOutValue(nProcIndex, "err", 1,10);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,11);
			daoObj.executeProcedureByPosition(nProcIndex);
			
			
			
			
			// get value
			strerr = daoObj.getString(nProcIndex, "err");
			if (strerr == null)
				strerr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strerr.equals("")) {
				VO.setStrBedDetailWS(ws);

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			VO.setStrMsgString("NursingDeskTransDAO.BEDCOMBOAJAX() --> "+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	public static void admitdetailIPDLeave(NursingDeskTransVO VO) 
	{
		String strproc_name = "{call  PKG_IPD_VIEW.Proc_tobe_accepted_Dtl(?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;
		System.out.println("admitdetailIPDLeave "+VO.getStrDepartment()+" "+VO.getStrUnit()+" "+VO.getStrWard()+" "+VO.getStrRoom()+" "+VO.getStrHospitalCode()+" "+VO.getStrAdmNo());

		try 
		{
			dao = new HisDAO("ipd","transactions.NursingDeskTransDAO.admitdetail()");			

			  
				strproc_name = "{call  PKG_IPD_VIEW.Proc_tobe_accepted_Dtl(?,?,?,?,?,?,?,?,?)}";
				nprocIndex = dao.setProcedure(strproc_name);
				dao.setProcInValue(nprocIndex, "deptcode", VO.getStrDepartment(),1);
				dao.setProcInValue(nprocIndex, "mode_val", "6",2);
				dao.setProcInValue(nprocIndex, "deptunitcode", VO.getStrUnit(),3);
				dao.setProcInValue(nprocIndex, "wardcode", VO.getStrWard(),4);
				dao.setProcInValue(nprocIndex, "roomcode", VO.getStrhmoveno(),5);//Movement No. check for Leave Not Reported
				dao.setProcInValue(nprocIndex, "hosp_code", VO.getStrHospitalCode(),6);
				dao.setProcInValue(nprocIndex, "adm_no", VO.getStrAdmNo(),7);
		     	dao.setProcOutValue(nprocIndex, "err", 1,8); // 1 for string return value
		     	dao.setProcOutValue(nprocIndex, "resultset", 2,9); // 2 for object
		     	dao.executeProcedureByPosition(nprocIndex);
		     	strerr = dao.getString(nprocIndex, "err");

				if (strerr == null)
					strerr = "";
				ws = dao.getWebRowSet(nprocIndex, "resultset");
				if (strerr.equals("")) 
				{
					VO.setStrAdmitDetailWs(ws);
				} 
				else
				{
					throw new Exception(strerr);
				}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			VO.setStrMsgString("NursingDeskTransDAO.admitdetail() --> "	+ e.getMessage());
			VO.setStrMsgType("1");
		}
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;
			}
		}
	}
	public static void getIcuPvtBillStatus(NursingDeskTransVO vo) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_geticupvtbillstatus(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			
				daoObj = new HisDAO("Nursing Desk",
					"NursingDeskTransDAO");
				
				nProcIndex = daoObj.setProcedure(strProcName);
				
				daoObj.setProcInValue(nProcIndex, "modeval","1",1);
				daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),2);
				daoObj.setProcInValue(nProcIndex, "crno",vo.getStrCrNo(),3);
				daoObj.setProcOutValue(nProcIndex, "err", 1,4);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {
					if(ws.size()==0)
					{
						vo.setBillcount("0");
					}
					else if(ws.next())
					{
						vo.setBillcount(ws.getString(1));
					}
				
				} else {
							throw new Exception(strErr);
						}
			
		} catch (Exception e) {
				vo.setStrMsgString("NursingDeskTransDAO.getIcuPvtBillStatus() --> "
						+ e.getMessage());
				vo.setStrMsgType("1");
		} 
		finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	public static void setConsultantName(NursingDeskTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws1 = null;

		String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String deptUnitCode = voObj.getStrUnit();

		String strErr = "";
    	//System.out.println("deptUnitCode"+voObj.getStrDeptUnitCode());
    	//System.out.println(voObj.getStrHospCode());
    	//System.out.println(voObj.getStrDeptUnitCode()==null?voObj.getStrDeptCode()+"%":voObj.getStrDeptUnitCode());;
		try {
			daoObj = new HisDAO("Nursing Desk Trans",
					"NursingDeskTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			if (deptUnitCode != null) {
				//daoObj.setProcInValue(nProcIndex, "modVal", "2");
				daoObj.setProcInValue(nProcIndex, "modVal", "7",1);  //Modeval 1 changed to 7 for designation
				if(voObj.getStrUnit().equals(""))
					daoObj.setProcInValue(nProcIndex, "deptunitcode",voObj.getStrDepartment(),2);
				else
				    daoObj.setProcInValue(nProcIndex, "deptunitcode",voObj.getStrUnit(),2);
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),3);
				daoObj.setProcInValue(nProcIndex, "seatId", "0",4);
				daoObj.setProcOutValue(nProcIndex, "err", 1,5);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
				//daoObj.setProcInValue(nProcIndex, "deptunitcode", "");

				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws1 = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {
					voObj.setConsultantWS(ws1);
						/*if (ws1.next())
						{
							voObj.setStrConsultantCode(ws1.getString(1));
							voObj.setStrConsultantName(ws1.getString(2));					
						}*/
				} else {
					throw new Exception(strErr);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			voObj.setStrMsgString("NursingDeskTransDAO.setConsultantName() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	public static void getConsultantName(NursingDeskTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws1 = null;

		String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String deptUnitCode = voObj.getStrUnit();

		String strErr = "";
    	//System.out.println("deptUnitCode"+voObj.getStrDeptUnitCode());
    	//System.out.println(voObj.getStrHospCode());
    	//System.out.println(voObj.getStrDeptUnitCode()==null?voObj.getStrDeptCode()+"%":voObj.getStrDeptUnitCode());;
		try {
			daoObj = new HisDAO("Nursing Desk Trans",
					"NursingDeskTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			if (deptUnitCode != null) {
				//daoObj.setProcInValue(nProcIndex, "modVal", "2");
				daoObj.setProcInValue(nProcIndex, "modVal", "8",1);  //Modeval 1 changed to 7 for designation
				if(voObj.getStrUnit().equals(""))
					daoObj.setProcInValue(nProcIndex, "deptunitcode",voObj.getStrDepartment(),2);
				else
				daoObj.setProcInValue(nProcIndex, "deptunitcode",voObj.getStrUnit(),2);
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),3);
				daoObj.setProcInValue(nProcIndex, "seatId", "0",4);
				daoObj.setProcOutValue(nProcIndex, "err", 1,5);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
				//daoObj.setProcInValue(nProcIndex, "deptunitcode", "");

				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws1 = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {
					voObj.setConsultantWS(ws1);
						/*if (ws1.next())
						{
							voObj.setStrConsultantCode(ws1.getString(1));
							voObj.setStrConsultantName(ws1.getString(2));					
						}*/
				} else {
					throw new Exception(strErr);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			voObj.setStrMsgString("NursingDeskTransDAO.setConsultantName() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

}