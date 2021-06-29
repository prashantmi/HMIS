package mms.transactions.dao;

import hisglobal.transactionmgnt.HisDAO;

import java.util.ResourceBundle;

import javax.sql.rowset.WebRowSet;





//import billing.transactions.LFNoTransVO;
import mms.MmsConfigUtil;
import mms.dao.DmlHsttPatEmpIssueDtlDAO;
import mms.dao.DmlHsttPatEmpIssueDtlWithoutCrNoDAO;
import mms.dao.DmlHsttPatEmpIssueItemDtlDAO;
import mms.dao.DmlHsttPatEmpIssueItemDtlWithoutCrNoDAO;
import mms.transactions.vo.IssueTransVO;
import mms.transactions.vo.RequestForLPPatientVO;

public class IssueTransDAO 
{
	
	/**
	 * Gets the issue dtls list.
	 * 
	 * @param vo the vo
	 * 
	 * @return the issue dtls list
	 * This Function is Used To Get Ajax Voucher Details
	 */// Modified by Neha Sharma on 16th July 2013 ..
	public static void getIssueDtlsList(IssueTransVO vo) 
	{
		String err;
		//String strSlNoflg;
		HisDAO        dao = null;
		WebRowSet      ws = null;
		int    procIndex1 = 0;
		String proc_name1 = "{call pkg_mms_view.Proc_Emp_Issue_Detail(?,?,?,?,?,?)}"; //6
		try 
		{
			/*
			  Total 33 Value Return In Case of ModeVal 2 (Which Call in Case of Off-Line Issue Voucher)
			  1.Hospital Name ^ DDC Name ^ Prescribe For ^ Issue No ^ Issued By Doctor ^ Date ^ Cr No ^ Hindi Text  
			  2.Drug Name
			  3.Batch No 
			  4.Exp Date
			  5.Issue Qty
			 */				
			dao = new HisDAO("mms","global.IssueTransDAO.getStockItemDtlsList(IssueTransVO vo)");			
			procIndex1 = dao.setProcedure(proc_name1);
			// set value
			System.out.println("vo.getStrStoreId()::"+vo.getStrStoreId());
			System.out.println("issueNo::"+ vo.getStrIssueNo());
			System.out.println("hosp_code"+ vo.getStrHospitalCode());
			dao.setProcInValue(procIndex1, "modeval", "2",1);
			dao.setProcInValue(procIndex1, "strId", vo.getStrStoreId(),3);
			if(vo.getStrIssueNo()==null || vo.getStrIssueNo()=="")
				dao.setProcInValue(procIndex1, "issueNo", "0",2);
			else
			dao.setProcInValue(procIndex1, "issueNo", vo.getStrIssueNo(),2);
			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode(),4);
			dao.setProcOutValue(procIndex1,"ERR", 1,5); // 1 for string return
			dao.setProcOutValue(procIndex1, "RESULTSET", 2,6); // 2 for object
			// execute procedure
			dao.executeProcedureByPosition(procIndex1);
			// get value
			err = dao.getString(procIndex1, "ERR");
			if (err == null)
				err = "";
			ws = dao.getWebRowSet(procIndex1, "RESULTSET");
			vo.setWsIssueDetails(ws);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
            //e.printStackTrace();
			vo.setStrMsgString("IssueTransDAO.getIssueDtlsList() --> "+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}

	}
	public static void getStoreList(IssueTransVO voObj) {

		HisDAO      daoObj = null;
		WebRowSet       ws = null;
		String strProcName = "{call Pkg_Mms_View.proc_hstt_store_mst(?,?,?,?,?,?,?)}";
		int     nProcIndex = 0;	
		String strErr = "";
		try
		{
			    daoObj = new HisDAO("MMS Transactions","IssueTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			//System.out.println("In DAO==>"+nProcIndex);
             
			daoObj.setProcInValue(nProcIndex, "modeval", "7",1);
			daoObj.setProcInValue(nProcIndex, "seatId", voObj.getStrSeatId(),2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),3);
			
			/* Setting Default Value Start*/
			daoObj.setProcInValue(nProcIndex, "storeid", "0",4);
			daoObj.setProcInValue(nProcIndex, "storetype_id", "0",5);
			/* Setting Default Value End */
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			   strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) 
			{

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrStoreWs(ws);
							
			} 
			else
			{
				throw new Exception(strErr);
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			voObj.setStrMsgString("IssueTransDAO.getStoreList() --> "+ e.getMessage());
			voObj.setStrMsgType("1");

		} 
		finally 
		{
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	public static void getViewStoreList(IssueTransVO voObj) 
	{

		HisDAO  daoObj = null;
		WebRowSet   ws = null;

		String strProcName = "{call Pkg_Mms_View.proc_hstt_store_mst(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions","IssueTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			//System.out.println("In DAO==>"+nProcIndex);
             
			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "seatId", voObj.getStrSeatId(),2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),3);
			
			/* Setting Default Value Start*/
			daoObj.setProcInValue(nProcIndex, "storeid", "0",4);
			daoObj.setProcInValue(nProcIndex, "storetype_id", "0",5);
			/* Setting Default Value End */
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrStoreWs(ws);
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("IssueTransDAO.getViewStoreList() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	
	public static void getPatinetTypeCmb(IssueTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.proc_raol_catg_list(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions","IssueTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "1");
			daoObj.setProcInValue(nProcIndex, "seatid", voObj.getStrSeatId());
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode());			
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2);

			daoObj.executeProcedure(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) 
			{
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				if(ws!=null && ws.size()>0)
				{	
				   voObj.setStrPatientTypeWs(ws);
				}							
			} 
			else 
			{
				throw new Exception(strErr);
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			voObj.setStrMsgString("IssueTransDAO.getPatinetTypeCmb() --> "+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	
	public static void getItemCatDtls(IssueTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.proc_item_category_list(?,?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions","IssueTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "5",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),3);
			daoObj.setProcInValue(nProcIndex, "store_id", (voObj.getStrStoreId().equals("") || voObj.getStrStoreId() == null)?"001":voObj.getStrStoreId(),2);
			daoObj.setProcInValue(nProcIndex, "reqType", voObj.getStrReqTypeId(),4);
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrItemCatWs(ws);
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("IssueTransDAO.getItemCatDtls() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	public static void getItemCatDtls1(IssueTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.proc_item_category_list(?,?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions","IssueTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "3",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),3);
			daoObj.setProcInValue(nProcIndex, "store_id", (voObj.getStrStoreId().equals("") || voObj.getStrStoreId() == null)?"001":voObj.getStrStoreId(),2);
			daoObj.setProcInValue(nProcIndex, "reqType", voObj.getStrReqTypeId(),4);
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrItemCatWs(ws);
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("IssueTransDAO.getItemCatDtls() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
	public static void getissuetopatient(IssueTransVO vo) 
	{

		HisDAO dao = null;
		WebRowSet ws = null;

		String proc_name1 = "{call Pkg_Mms_View.proc_patient_issue_drug_dtl(?,?,? ,?,?,? ,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try 
		{
			    dao = new HisDAO("MMS",	"OfflineIssueIndentTransDAO.getissuetopatient(OfflineIssueIndentTransVO vo)");
			    nProcIndex = dao.setProcedure(proc_name1);
				// set value
				dao.setProcInValue(nProcIndex, "modeval", "3",1);                  //1
				dao.setProcInValue(nProcIndex, "storeid",  vo.getStrStoreId(),2);//2
				dao.setProcInValue(nProcIndex, "itemCatg", vo.getStrItemCat(),3);//3
				dao.setProcInValue(nProcIndex, "from_date",vo.getStrCrNo(),4);//4
				dao.setProcInValue(nProcIndex, "too_date", "",5);//5
				dao.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),6); //6
				dao.setProcInValue(nProcIndex, "crno",vo.getStrCrNo(),7); //6
				dao.setProcOutValue(nProcIndex,"err", 1,8); // 1 for string return //7
				dao.setProcOutValue(nProcIndex, "resultset", 2,9);//8
				dao.executeProcedureByPosition(nProcIndex);
				
			    strErr = dao.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = dao.getWebRowSet(nProcIndex, "resultset");
				System.out.println("wsssssss"+ws.size());
				vo.setWsissuetopatcount(ws);
			
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			vo
					.setStrMsgString("IssueTransDAO.getIssueDetailTwo() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	public static void getCancelIssueDtls(IssueTransVO voObj)
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.proc_issue_item_dtls(?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("MMS Transactions","IssueTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "3");
			daoObj.setProcInValue(nProcIndex, "hoscode", voObj.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "issueno", voObj.getStrIssueNo());			
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setWsCancelIssueDtl(ws);
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("IssueTransDAO.getCancelIssueDtls() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
	public static void getIssueDetail(IssueTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.Proc_Issue_Details(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions","IssueTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			
			
				System.out.println("modval"+ "1");
				System.out.println("storeId"+ voObj.getStrStoreId());
				System.out.println("hosp_code"+ voObj.getStrHospitalCode());
				System.out.println("crNo"+ voObj.getStrCrNum());
				System.out.println("catCode"+ voObj.getStrCatCode());
				System.out.println("days"+ voObj.getStrPatStaffDays());
				System.out.println("empNo"+ "");//default value.
			

			daoObj.setProcInValue(nProcIndex, "modval", "1",1);
			daoObj.setProcInValue(nProcIndex, "storeId", voObj.getStrStoreId(),2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),3);
			daoObj.setProcInValue(nProcIndex, "crNo", voObj.getStrCrNum(),4);
			daoObj.setProcInValue(nProcIndex, "catCode", voObj.getStrCatCode(),5);
			daoObj.setProcInValue(nProcIndex, "days", "10",6);
			daoObj.setProcInValue(nProcIndex, "empNo", "",7);//default value.
			daoObj.setProcOutValue(nProcIndex, "err", 1,8);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,9);
			

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			System.out.println("ws"+ws.size());
			/*while(ws.next())
			{
			System.out.println("ws.getstring()"+ws.getString(1));	
			System.out.println("ws.getstring()"+ws.getString(2));
			}*/
			voObj.setStrIssueDetailWs(ws);		
			
			//System.out.println("voObj.getStrIssueDetailWs()"+voObj.getStrIssueDetailWs().getString(1));
						
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("IssueTransDAO.getIssueDetail() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	public static void getIssueDetailTwo(IssueTransVO vo) 
	{

		HisDAO dao = null;
		WebRowSet ws = null;

		String proc_name1 = "{call Pkg_Mms_View.proc_patient_issue_drug_dtl(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try 
		{
			    dao = new HisDAO("MMS",	"OfflineIssueIndentTransDAO.getIssueDetailTwo(OfflineIssueIndentTransVO vo)");
			    nProcIndex = dao.setProcedure(proc_name1);
				// set value
				dao.setProcInValue(nProcIndex, "modeval", "1",1);                  //1
				dao.setProcInValue(nProcIndex, "storeid",  vo.getStrStoreName(),2);//2
				dao.setProcInValue(nProcIndex, "itemCatg", vo.getStrItemCategoryNo(),3);//3
				dao.setProcInValue(nProcIndex, "from_date",vo.getStrFromDate(),4);//4
				dao.setProcInValue(nProcIndex, "too_date", vo.getStrToDate(),5);//5
				dao.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),6); //6
				dao.setProcInValue(nProcIndex, "crno",vo.getStrCrNo(),7); //6
				dao.setProcOutValue(nProcIndex,"err", 1,8); // 1 for string return //7
				dao.setProcOutValue(nProcIndex, "resultset", 2,9);//8
				dao.executeProcedureByPosition(nProcIndex);
				
			    strErr = dao.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = dao.getWebRowSet(nProcIndex, "resultset");
						
				vo.setStrIssueDetailWs(ws);
			
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			vo
					.setStrMsgString("IssueTransDAO.getIssueDetailTwo() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	
	public static void getIssueDtlPopUp(IssueTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call  Pkg_Mms_View.Proc_IssuePopup_Dtls(?,?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions","IssueTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			System.out.println("modeval"+"1");
			System.out.println("hosp_code"+voObj.getStrHospitalCode());
			System.out.println("storeId"+voObj.getStrStoreId());
			System.out.println("issueNo"+voObj.getStrIssueNo());
			
			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "storeId", voObj.getStrStoreId(),4);
			daoObj.setProcInValue(nProcIndex, "issueno", voObj.getStrIssueNo(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset",2,6);
			
			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		       System.out.println("ws.sizeee ::"+ws.size());
				voObj.setStrIssueDtlPopUpWs(ws);
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("IssueTransDAO.getIssueDtlPopUp() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	public static void getRequestDtls(IssueTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.Proc_Issue_Request_Dtls(?,?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";
		int funcIndex;
		String BillingValue="";

		try {

			daoObj = new HisDAO("MMS Transactions","IssueTransDAO");


			funcIndex = daoObj.setFunction("{? = call bill_mst.get_pat_accountdetails_limit(?,?,?)}");

			daoObj.setFuncInValue(funcIndex, 2,voObj.getStrHospitalCode());
			daoObj.setFuncInValue(funcIndex, 3,"2");
			daoObj.setFuncInValue(funcIndex, 4,voObj.getStrCrNumber());
			//daoObj.setFuncInValue(funcIndex, 5,_LPIssueDeskTransVO.getStrItemCategNo());
			daoObj.setFuncOutValue(funcIndex, 1);
			daoObj.executeFunction(funcIndex);
			BillingValue = daoObj.getFuncString(funcIndex); 
			System.out.println("BillingValue"+BillingValue);
			voObj.setStrBillingHiddenValue(BillingValue);
			
			
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "crNo", voObj.getStrCrNumber(),3);
			daoObj.setProcInValue(nProcIndex, "days", voObj.getStrOnlineIssueReqDays(),4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrRequestWs(ws);
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("IssueTransDAO.getRequestDtls() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	public static void getRequestDetails(IssueTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.Proc_Request_Dtls(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions","IssueTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modval", "1");
			daoObj.setProcInValue(nProcIndex, "reqNo", voObj.getStrRequestNo());
			daoObj.setProcInValue(nProcIndex, "crNo", voObj.getStrCrNumber());
			daoObj.setProcInValue(nProcIndex, "days", voObj.getStrOnlineIssueReqDays());
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrRequestDetailWs(ws);
			
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("IssueTransDAO.getRequestDetails() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	public static void getFrequencyDetails(IssueTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet strFrequencyWs = null;

		String strProcName = "{call Pkg_Mms_View.proc_frequency_dtl(?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions","IssueTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),1);
			daoObj.setProcOutValue(nProcIndex, "err", 1,2);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,3);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				strFrequencyWs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrFrequencyWs(strFrequencyWs);
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("IssueTransDAO.getFrequencyDetails() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	public static void getDoseDetails(IssueTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet strDosageWs = null;

		String strProcName = "{call Pkg_Mms_View.proc_dosage_dtl(?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions","IssueTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),1);
			daoObj.setProcOutValue(nProcIndex, "err", 1,2);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,3);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				strDosageWs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrDosageWs(strDosageWs);
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("IssueTransDAO.getDoseDetails() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	public static void getDeptDetails(IssueTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.proc_department(?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions","IssueTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "seatid", voObj.getStrSeatId(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrDepartmentWs(ws);
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("IssueTransDAO.getDeptDetails() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	public static void getUnitDetails(IssueTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.Proc_Unit(?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions","IssueTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "dept_code", voObj.getStrDeptCode(),1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "seatId", voObj.getStrSeatId(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrUnitWs(ws);
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("IssueTransDAO.getUnitDetails() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	public static void getPrescribedBy(IssueTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.Proc_Consultant_Name(?,?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions","IssueTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "deptunitcode", voObj.getStrUnitCode(),2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),3);
			daoObj.setProcInValue(nProcIndex, "seatId", voObj.getStrSeatId(),4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrConsultantWs(ws);
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("IssueTransDAO.getPrescribedBy() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	
	public static void getItemDetails(IssueTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.Proc_IssueItem_Dtl(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions","IssueTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "1");
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "reqNo", voObj.getStrRequestNo());
			daoObj.setProcInValue(nProcIndex, "crNo", voObj.getStrCrNumber());
			daoObj.setProcInValue(nProcIndex, "strId", voObj.getStrStoreId());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrItemDetailWs(ws);
				
				//System.out.println("ws size--->"+ws.size());
											
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("IssueTransDAO.getItemDetails() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	public static void getUnitCombo(IssueTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_mms_view.proc_gblt_unit_mst(?,?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions","IssueTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "unit_id", voObj.getStrIssueUnitId());
			daoObj.setProcInValue(nProcIndex, "modeval", "1");
			daoObj.setProcInValue(nProcIndex, "module_id", "");//Default Value
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrIssueUnitWs(ws);
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("IssueTransDAO.getUnitCombo() --> "
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
	 * This function is fetch details from database for group combo
	 * @param vo
	 */

	public static void getStoreGroupList(IssueTransVO vo) {
		

		String err = "";
		String proc_name1 = "{call pkg_mms_view.proc_store_group_list(?,?,?,?,?,?,?)}";

		int procIndex1 = 0;
		HisDAO dao = null;
		WebRowSet ws = null;

		try {

				dao = new HisDAO("MMS Transactions","IssueTransDAO");
		
				procIndex1 = dao.setProcedure(proc_name1);
				dao.setProcInValue(procIndex1, "modeval", "2",1);
				// set value
				dao.setProcInValue(procIndex1, "item_category", vo.getStrItemCat(),3);
				dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode(),2);
				
				/* Setting Default Value Start*/
				dao.setProcInValue(procIndex1, "strPhyStockNo", "",4);
				dao.setProcInValue(procIndex1, "strStoreId", "",5);
				/* Setting Default Value End */
				
				dao.setProcOutValue(procIndex1, "ERR", 1,6); // 1 for string return
				// value
				dao.setProcOutValue(procIndex1, "RESULTSET", 2,7); // 2 for object
		
				// execute procedure
		
				dao.executeProcedureByPosition(procIndex1);
		
				// get value
				err = dao.getString(procIndex1, "ERR");
		
				if (err == null)
					err = "";
		
				ws = dao.getWebRowSet(procIndex1, "RESULTSET");
		
				vo.setStrGroupWs(ws);

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("IssueTransDAO.getStoreGroupList() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}
	}
	
	
	public static synchronized void insert(IssueTransVO vo) 
	{
		HisDAO         dao = null;		
		String strFuncName = "";
		int     nFuncIndex = 0;
		int         length = 0;
		String  paramVal[] = null;
		String userValue[] = null;
		String  strIssueNo = "";
		String strVisitDtl = "";
		Double     netCost = 0.00;
		DmlHsttPatEmpIssueItemDtlDAO dmlhsttpatempissueitemdtl = null;
		DmlHsttPatEmpIssueDtlDAO         dmlhsttpatempissuedtl = null;
		
		int debugPoint = 0;
		String itemValue = "";
		/*
		 * Added by shefali garg
		*/
		String strIssueUnitId = "0";
		String strBillTariff = "";
		String strBillBatch = "";
		String strBillRate = "";
		String strBillQty = "";
		String proc_name2 = "{call bill_interface.dml_online_billreq_drugs(?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?)}";
		MmsConfigUtil mcu;

		try 
		{
			debugPoint = 1;
			mcu =new MmsConfigUtil(vo.getStrHospitalCode());
			dao = new HisDAO("MMS Transactions","IssueTransDAO");
			dmlhsttpatempissueitemdtl = new DmlHsttPatEmpIssueItemDtlDAO();
			    dmlhsttpatempissuedtl = new DmlHsttPatEmpIssueDtlDAO();			
			              strFuncName = "{? = call MMS_MST.generate_issueNo(?::numeric,?::numeric,?::numeric,?::numeric)}";
			               nFuncIndex = dao.setFunction(strFuncName);
			               
			dao.setFuncInValue(nFuncIndex, 2, vo.getStrHospitalCode());
			dao.setFuncInValue(nFuncIndex, 3, vo.getStrStoreId());
			dao.setFuncInValue(nFuncIndex, 4, "1");
			dao.setFuncInValue(nFuncIndex, 5, vo.getStrItemCat());
			dao.setFuncOutValue(nFuncIndex, 1);
			dao.executeFunction(nFuncIndex);
			strIssueNo = dao.getFuncString(nFuncIndex);
			vo.setStrIssueNumber(strIssueNo.split("\\^")[0]);
			
			debugPoint = 2;
			
			
		
			          strVisitDtl = "0"	;	
			String strEpisodeCode = "0";
			String     strVisitNo = "0" ;
			
			                  dao = new HisDAO("MMS Transactions","IssueTransDAO");
			               length = vo.getItemParamValue().length;	
			               
			for(int k=0;k<length;k++)
			{				
				/**
				 * Hidden Values corresponding to particular
				 * ITEM DETAILS
				 */	
				debugPoint = 3;
						if(vo.getStrQtyText()[k] != null && vo.getStrQtyText()[k].length() > 0 && !vo.getStrQtyText()[k].equals("0"))
						{	
							itemValue = vo.getItemParamValue()[k];
							
											 paramVal = vo.getItemParamValue()[k].split("#");						
											userValue = paramVal[2].replace('^', '#').split("#");	
											
											debugPoint = 4;
											
											if(strBillTariff != null && strBillTariff != "")
												strBillTariff=strBillTariff + "^"+userValue[1];
												else
													strBillTariff = userValue[1];
												if(strBillRate != null && strBillRate != "")
													strBillRate=strBillRate + "^"+userValue[9];
													else
														strBillRate = userValue[9];
												if(strBillQty != null && strBillQty != "")
													strBillQty=strBillQty + "^"+vo.getStrQtyText()[k];
													else
														strBillQty = vo.getStrQtyText()[k];
												if(strBillBatch != null && strBillBatch != "")
													strBillBatch=strBillBatch + "^"+userValue[15];
													else
														strBillBatch = userValue[15];
											
											String strInventoryUnitId = userValue[11];
							
							dmlhsttpatempissueitemdtl.setStrStoreId(vo.getStrStoreId());
							dmlhsttpatempissueitemdtl.setStrIssueNo(vo.getStrIssueNumber());
							dmlhsttpatempissueitemdtl.setStrItemId(userValue[0]);
							dmlhsttpatempissueitemdtl.setStrItemBrandId(userValue[1]);
							dmlhsttpatempissueitemdtl.setStrBatchSlNo(userValue[15]);
							dmlhsttpatempissueitemdtl.setStrHospitalCode(vo.getStrHospitalCode());
							
							debugPoint = 5;
							
							dmlhsttpatempissueitemdtl.setStrItemSlNo(userValue[18]);
							dmlhsttpatempissueitemdtl.setStrStockStatusCode(userValue[32]);
							dmlhsttpatempissueitemdtl.setStrGroupId(userValue[2]);
							dmlhsttpatempissueitemdtl.setStrSubGroupId(userValue[3]);
							dmlhsttpatempissueitemdtl.setStrRate(userValue[9]);
							dmlhsttpatempissueitemdtl.setStrRateUnitId(userValue[10]);
							
							debugPoint = 6;
							
							dmlhsttpatempissueitemdtl.setStrBalQty("0");
							dmlhsttpatempissueitemdtl.setStrBalQtyUnitId("");
							dmlhsttpatempissueitemdtl.setStrIssueQty(vo.getStrQtyText()[k]);
							dmlhsttpatempissueitemdtl.setStrIssueQtyUnitId(strInventoryUnitId); //Aritra,22nd June							
							dmlhsttpatempissueitemdtl.setStrExpiryDate(userValue[16]);
							dmlhsttpatempissueitemdtl.setStrRemarks(vo.getStrRemarks());
							
							debugPoint = 7;
							
							dmlhsttpatempissueitemdtl.setStrCost("0");
							dmlhsttpatempissueitemdtl.setStrInHandQty(userValue[7]);
							dmlhsttpatempissueitemdtl.setStrInHandQtyUnitId(userValue[8]);
							dmlhsttpatempissueitemdtl.setStrManufDate(userValue[17]);
							dmlhsttpatempissueitemdtl.setStrReqNo(vo.getStrRequestNo());
							dmlhsttpatempissueitemdtl.setStrCrNo(vo.getStrPuk());
							
							debugPoint = 8;
							
							dmlhsttpatempissueitemdtl.setStrEmpNo(vo.getStrReqEmpNo());
							dmlhsttpatempissueitemdtl.setStrVisitNo(strVisitNo);
							dmlhsttpatempissueitemdtl.setStrEpisodeCode(strEpisodeCode);
							dmlhsttpatempissueitemdtl.setStrAdmNo(vo.getStrReqAdmNo());
							dmlhsttpatempissueitemdtl.setStrOrderBy(vo.getStrReqPrescribedBy());//Id od the consultant
							
							debugPoint = 9;
							
							dmlhsttpatempissueitemdtl.setStrDays(vo.getStrReqPrescribedFor());
							dmlhsttpatempissueitemdtl.setStrReqTypeId(vo.getStrReqTypeId());
							dmlhsttpatempissueitemdtl.setStrItemCatNo(vo.getStrItemCat());
							
							debugPoint = 10;
							
							dmlhsttpatempissueitemdtl.setStrDosage("0");
							dmlhsttpatempissueitemdtl.setStrFrequency("0");
							dmlhsttpatempissueitemdtl.setStrPresDays("0");
							
							dmlhsttpatempissueitemdtl.setStrPatientName(vo.getStrPatientDtlHidVal().split("\\^")[7]);//    									
							dmlhsttpatempissueitemdtl.setStrSeatId(vo.getStrSeatId());
							dmlhsttpatempissueitemdtl.setStrDrugIssueDate(vo.getStrDrugIssueDate());
							
							debugPoint = 11;
							
							//System.out.println("Issue Daet=in With Cr NO DAO 55=>"+vo.getStrDrugIssueDate());
							//System.out.println("Out Of Stoc Flag=in With Cr NO DAO 66=>"+vo.getStrOutOfStockFlag());
							dmlhsttpatempissueitemdtl.insert2(dao);
							
						 }	
					
				}	
			System.out.println("vo.getStrLFAccountNo() ++++>>>DAO::"+vo.getStrLFAccountNo());
			if(vo.getStrLFAccountNo() != null && vo.getStrLFAccountNo() !="")
			{
			if(mcu.getStrBillingIntegration().equals("1"))
			{
				int procIndex2;
				procIndex2 = dao.setProcedure(proc_name2);
				
				/*System.out.println("modval"+ "2"); // 1
				System.out.println("gnum_dept_code"+ vo.getStrDeptCode());
				System.out.println("sblnum_chargetype_id"+ "1");
				System.out.println("sblnum_service_id"+ "5");
				System.out.println("gstr_req_no"+ strIssueNo);
				System.out.println("gnum_treatment_cat"+ "11");
				System.out.println("hrgnum_episode_code"+"1");
				System.out.println("hrgnum_puk"+ vo.getStrCrNo());
				System.out.println("hblnum_pat_lfaccount_no"+ vo.getStrLFAccountNo());
				System.out.println("gstr_tariff"+ strBillTariff);
				System.out.println("gstr_tariff_batch"+ strBillBatch);
				System.out.println("gstr_tariff_rates"+ strBillRate);
				System.out.println("gstr_reqqty"+ strBillQty);
				System.out.println("hblstr_patient_name"+ "");
				System.out.println("hblstr_pat_address"+ "");
				System.out.println("hblstr_contact_no"+ "");
				System.out.println("age"+ "1");
				System.out.println("ageunit"+ "1");
				System.out.println("gender"+ "1");
				System.out.println("refdoctor"+ "1");
				System.out.println("refdoccontactno"+ "1");
				System.out.println("gnum_seatid"+ vo.getStrSeatId());
				System.out.println("hosp_code"+ vo.getStrHospitalCode());
				System.out.println("ward_code"+ vo.getStrWardCode());
				System.out.println("admno"+"1");
				System.out.println("visitno"+ vo.getStrVisitNo());*/

				
				
				dao.setProcInValue(procIndex2, "modval", "2", 1); // 1
				dao.setProcInValue(procIndex2, "gnum_dept_code", vo.getStrDeptCode(), 2);
				dao.setProcInValue(procIndex2, "sblnum_chargetype_id", "1", 3);
				dao.setProcInValue(procIndex2, "sblnum_service_id", "5", 4);
				dao.setProcInValue(procIndex2, "gstr_req_no", strIssueNo, 5);
				dao.setProcInValue(procIndex2, "gnum_treatment_cat", "11",6);
				dao.setProcInValue(procIndex2, "hrgnum_episode_code","1", 7);
				dao.setProcInValue(procIndex2, "hrgnum_puk", vo.getStrCrNo(), 8);
				dao.setProcInValue(procIndex2, "hblnum_pat_lfaccount_no", vo.getStrLFAccountNo(), 9);
				dao.setProcInValue(procIndex2, "gstr_tariff", strBillTariff, 10);
				dao.setProcInValue(procIndex2, "gstr_tariff_batch", strBillBatch, 11);
				dao.setProcInValue(procIndex2, "gstr_tariff_rates", strBillRate, 12);
				dao.setProcInValue(procIndex2, "gstr_reqqty", strBillQty, 13);
				dao.setProcInValue(procIndex2, "hblstr_patient_name", "", 14);
				dao.setProcInValue(procIndex2, "hblstr_pat_address", "", 15);
				dao.setProcInValue(procIndex2, "hblstr_contact_no", "", 16);
				dao.setProcInValue(procIndex2, "age", "1", 17);
				dao.setProcInValue(procIndex2, "ageunit", "1", 18);
				dao.setProcInValue(procIndex2, "gender", "1", 19);
				dao.setProcInValue(procIndex2, "refdoctor", "1", 20);
				dao.setProcInValue(procIndex2, "refdoccontactno", "1", 21);
				dao.setProcInValue(procIndex2, "gnum_seatid", vo.getStrSeatId(), 22);
				dao.setProcInValue(procIndex2, "hosp_code", vo.getStrHospitalCode(), 23);
				dao.setProcInValue(procIndex2, "ward_code", vo.getStrWardCode(), 24);
				dao.setProcInValue(procIndex2, "admno","1", 25);
				dao.setProcInValue(procIndex2, "visitno", vo.getStrVisitNo(), 26);
				dao.setProcOutValue(procIndex2, "err", 1, 27);
				dao.execute(procIndex2, 1);
				
			}
		}	
			debugPoint = 12;
			
					dmlhsttpatempissuedtl.setStrStoreId(vo.getStrStoreId());
					dmlhsttpatempissuedtl.setStrIssueNo(vo.getStrIssueNumber());
					dmlhsttpatempissuedtl.setStrHospitalCode(vo.getStrHospitalCode());
					dmlhsttpatempissuedtl.setStrRequestNo(vo.getStrRequestNo());
					dmlhsttpatempissuedtl.setStrOrderBy(vo.getStrReqPrescribedBy());
					
					debugPoint = 13;
					
					dmlhsttpatempissuedtl.setStrOrderDate(vo.getStrReqPrescriptionDate());
					dmlhsttpatempissuedtl.setStrDays(vo.getStrPrescriptionFor());
					dmlhsttpatempissuedtl.setStrCrNo(vo.getStrPuk());
					dmlhsttpatempissuedtl.setStrReqTypeId(vo.getStrReqTypeId());
					dmlhsttpatempissuedtl.setStrAdmNo(vo.getStrReqAdmNo());
					
					debugPoint = 14;
					
					dmlhsttpatempissuedtl.setStrEmpNo(vo.getStrReqEmpNo());
					dmlhsttpatempissuedtl.setStrItemCatNo(vo.getStrItemCat());
					dmlhsttpatempissuedtl.setStrFinalCost(DmlHsttPatEmpIssueItemDtlDAO.getIntCost()+"");
					dmlhsttpatempissuedtl.setStrFinStartDate(vo.getStrFinStartDate());
					dmlhsttpatempissuedtl.setStrFinEndDate(vo.getStrFinEndDate());
					
					debugPoint = 15;
					
					dmlhsttpatempissuedtl.setStrSeatId(vo.getStrSeatId());
					dmlhsttpatempissuedtl.setStrDeptUnitCode(vo.getStrUnitCode());
					dmlhsttpatempissuedtl.setStrVisitType(vo.getStrReqPrescriptionFrom());
					dmlhsttpatempissuedtl.setStrDeptCode(vo.getStrDeptCode());
					
					dmlhsttpatempissuedtl.setStrWardCode(vo.getStrReqWardCode());
					
					debugPoint = 16;
					
					dmlhsttpatempissuedtl.setStrReceiveBy(vo.getStrReceiveBy());
					dmlhsttpatempissuedtl.setStrRemarks(vo.getStrRemarks());
					dmlhsttpatempissuedtl.setStrReqDate("");
					dmlhsttpatempissuedtl.setStrVisitNo(strVisitNo);
					dmlhsttpatempissuedtl.setStrEpisoCode(strEpisodeCode);
					
					debugPoint = 17;
					
					// By Vivek		
                    // vo.getStrPatientHiddenValue1	    Patient Name ^Father Name^Category Code^Address^Mlc No						
					/* vo.getStrPatientDtlHidVal()
		              HRGDT_DOB || ''^'' ||GNUM_GENDER_CODE ||''^''||HGNUM_PAT_STATUS_CODE || ''^''||
			          HGNUM_PATIENT_CAT_CODE || ''^'' ||HRGNUM_IS_MLC    ||''^''||HRGNUM_ISNEWBORN      || ''^''|| HRGNUM_ID_NO
					*/
					// By Vivek	
					
					itemValue += " PatientHiddenValue1 :: " + vo.getStrPatientHiddenValue1() + " StrPatientDtlHidVal :: " + vo.getStrPatientDtlHidVal();

					dmlhsttpatempissuedtl.setStrFirstName(vo.getStrPatientDtlHidVal().split("\\^")[7]);
					dmlhsttpatempissuedtl.setStrMiddelName("");
					dmlhsttpatempissuedtl.setStrLastName("");
					dmlhsttpatempissuedtl.setStrPatientId(vo.getStrPatientId());				
					dmlhsttpatempissuedtl.setStrPatientType("18");		
					
					debugPoint = 18;
					
					dmlhsttpatempissuedtl.setStrPatientAge(vo.getStrPatientDtlHidVal().split("\\^")[0]);
					dmlhsttpatempissuedtl.setStrPatientAgeUnit("0");
					dmlhsttpatempissuedtl.setStrPatientFatherName(vo.getStrPatientDtlHidVal().split("\\^")[8]);
					dmlhsttpatempissuedtl.setStrPatientGenderCode(vo.getStrPatientDtlHidVal().split("\\^")[1]);
					
					debugPoint = 19;
					
					dmlhsttpatempissuedtl.setStrPatientAddress(vo.getStrPatientHiddenValue1().split("\\^")[3]);
					dmlhsttpatempissuedtl.setStrTransType("1");  // With CR No	
					dmlhsttpatempissuedtl.setStrDrugIssueDate(vo.getStrDrugIssueDate());
					dmlhsttpatempissuedtl.setStrOutOfStockFlag(vo.getStrOutOfStockFlag());
					
					debugPoint = 20;
					
					//System.out.println("Issue Daet=in With Cr NO DAO 77=>"+vo.getStrDrugIssueDate());
					//System.out.println("Out Of Stoc Flag=in With Cr NO DAO 88=>"+vo.getStrOutOfStockFlag());
					dmlhsttpatempissuedtl.insert(dao);
					
					
					// DAO Fire
					synchronized (dao) {
						dao.fire();
					}
					
					
					debugPoint = 21;
		  
		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			vo.setStrMsgType("1");
			vo.setStrMsgString("IssueTransDAO.insert() --> "
					+ e.getMessage() + " DAO_DEBUG_POINT :: " + debugPoint + " DAO_VALUE :: " + itemValue);
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
	
	public static synchronized void insertipd(IssueTransVO vo) 
	{
		HisDAO         dao = null;		
		String strFuncName = "";
		int     nFuncIndex = 0;
		int         length = 0;
		String  paramVal[] = null;
		String userValue[] = null;
		String  strIssueNo = "";
		String strVisitDtl = "";
		Double     netCost = 0.00;
		DmlHsttPatEmpIssueItemDtlDAO dmlhsttpatempissueitemdtl = null;
		DmlHsttPatEmpIssueDtlDAO         dmlhsttpatempissuedtl = null;
		
		int debugPoint = 0;
		String itemValue = "";
	
		String strIssueUnitId = "0";
		String strBillTariff = "";
		String strBillBatch = "";
		String strBillRate = "";
		String strBillQty = "";
		String proc_name2 = "{call bill_interface.dml_online_billreq_drugs(?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?)}";
		MmsConfigUtil mcu;

		try 
		{
			debugPoint = 1;
			mcu =new MmsConfigUtil(vo.getStrHospitalCode());
			dao = new HisDAO("MMS Transactions","IssueTransDAO");
			dmlhsttpatempissueitemdtl = new DmlHsttPatEmpIssueItemDtlDAO();
			    dmlhsttpatempissuedtl = new DmlHsttPatEmpIssueDtlDAO();			
			              strFuncName = "{? = call MMS_MST.generate_issueNo(?::numeric,?::numeric,?::numeric,?::numeric)}";
			               nFuncIndex = dao.setFunction(strFuncName);
			               
			dao.setFuncInValue(nFuncIndex, 2, vo.getStrHospitalCode());
			dao.setFuncInValue(nFuncIndex, 3, vo.getStrStoreId());
			dao.setFuncInValue(nFuncIndex, 4, "1");
			dao.setFuncInValue(nFuncIndex, 5, vo.getStrItemCat());
			dao.setFuncOutValue(nFuncIndex, 1);
			dao.executeFunction(nFuncIndex);
			strIssueNo = dao.getFuncString(nFuncIndex);
			vo.setStrIssueNumber(strIssueNo.split("\\^")[0]);
			
			debugPoint = 2;
			
			
		
			          strVisitDtl = "0"	;	
			String strEpisodeCode = "0";
			String     strVisitNo = "0" ;
			
			                  dao = new HisDAO("MMS Transactions","IssueTransDAO");
			               length = vo.getItemParamValue().length;	
			               
			               debugPoint = 12;
			   			
							dmlhsttpatempissuedtl.setStrStoreId(vo.getStrStoreId());
							dmlhsttpatempissuedtl.setStrIssueNo(vo.getStrIssueNumber());
							dmlhsttpatempissuedtl.setStrHospitalCode(vo.getStrHospitalCode());
							dmlhsttpatempissuedtl.setStrRequestNo(vo.getStrRequestNo());
							dmlhsttpatempissuedtl.setStrOrderBy(vo.getStrReqPrescribedBy());
							
							debugPoint = 13;
							
							dmlhsttpatempissuedtl.setStrOrderDate(vo.getStrReqPrescriptionDate());
							dmlhsttpatempissuedtl.setStrDays(vo.getStrPrescriptionFor());
							dmlhsttpatempissuedtl.setStrCrNo(vo.getStrPuk());
							dmlhsttpatempissuedtl.setStrReqTypeId(vo.getStrReqTypeId());
							dmlhsttpatempissuedtl.setStrAdmNo(vo.getStrAdmissionDtlHidVal().replace("^", "#").split("#")[0]);
							
							debugPoint = 14;
							
							dmlhsttpatempissuedtl.setStrEmpNo(vo.getStrReqEmpNo());
							dmlhsttpatempissuedtl.setStrItemCatNo(vo.getStrItemCat());
							dmlhsttpatempissuedtl.setStrFinalCost(DmlHsttPatEmpIssueItemDtlDAO.getIntCost()+"");
							dmlhsttpatempissuedtl.setStrFinStartDate(vo.getStrFinStartDate());
							dmlhsttpatempissuedtl.setStrFinEndDate(vo.getStrFinEndDate());
							
							debugPoint = 15;
							
							dmlhsttpatempissuedtl.setStrSeatId(vo.getStrSeatId());
							dmlhsttpatempissuedtl.setStrDeptUnitCode(vo.getStrUnitCode());
							dmlhsttpatempissuedtl.setStrVisitType("2");//hardcoded in case of ipd patient
							dmlhsttpatempissuedtl.setStrDeptCode(vo.getStrDeptCode());
							
							dmlhsttpatempissuedtl.setStrWardCode(vo.getStrReqWardCode());
							
							debugPoint = 16;
							
							dmlhsttpatempissuedtl.setStrReceiveBy(vo.getStrReceiveBy());
							dmlhsttpatempissuedtl.setStrRemarks(vo.getStrRemarks());
							dmlhsttpatempissuedtl.setStrReqDate("");
							dmlhsttpatempissuedtl.setStrVisitNo(strVisitNo);
							dmlhsttpatempissuedtl.setStrEpisoCode(strEpisodeCode);
							
							debugPoint = 17;
							
							// By Vivek		
		                    // vo.getStrPatientHiddenValue1	    Patient Name ^Father Name^Category Code^Address^Mlc No						
							/* vo.getStrPatientDtlHidVal()
				              HRGDT_DOB || ''^'' ||GNUM_GENDER_CODE ||''^''||HGNUM_PAT_STATUS_CODE || ''^''||
					          HGNUM_PATIENT_CAT_CODE || ''^'' ||HRGNUM_IS_MLC    ||''^''||HRGNUM_ISNEWBORN      || ''^''|| HRGNUM_ID_NO
							*/
							// By Vivek	
							
							itemValue += " PatientHiddenValue1 :: " + vo.getStrPatientHiddenValue1() + " StrPatientDtlHidVal :: " + vo.getStrPatientDtlHidVal();

							dmlhsttpatempissuedtl.setStrFirstName(vo.getStrPatientDtlHidVal().split("\\^")[7]);
							dmlhsttpatempissuedtl.setStrMiddelName("");
							dmlhsttpatempissuedtl.setStrLastName("");
							dmlhsttpatempissuedtl.setStrPatientId(vo.getStrPatientId());				
							dmlhsttpatempissuedtl.setStrPatientType("18");		
							
							debugPoint = 18;
							
							dmlhsttpatempissuedtl.setStrPatientAge(vo.getStrPatientDtlHidVal().split("\\^")[0]);
							dmlhsttpatempissuedtl.setStrPatientAgeUnit("0");
							dmlhsttpatempissuedtl.setStrPatientFatherName(vo.getStrPatientDtlHidVal().split("\\^")[8]);
							dmlhsttpatempissuedtl.setStrPatientGenderCode(vo.getStrPatientDtlHidVal().split("\\^")[1]);
							
							debugPoint = 19;
							
							dmlhsttpatempissuedtl.setStrPatientAddress(vo.getStrPatientHiddenValue1().split("\\^")[3]);
							dmlhsttpatempissuedtl.setStrTransType("1");  // With CR No	
							dmlhsttpatempissuedtl.setStrDrugIssueDate(vo.getStrDrugIssueDate());
							dmlhsttpatempissuedtl.setStrOutOfStockFlag(vo.getStrOutOfStockFlag());
							
							debugPoint = 20;
							
							//System.out.println("Issue Daet=in With Cr NO DAO 77=>"+vo.getStrDrugIssueDate());
							//System.out.println("Out Of Stoc Flag=in With Cr NO DAO 88=>"+vo.getStrOutOfStockFlag());
							dmlhsttpatempissuedtl.insert(dao);
							
			               
			for(int k=0;k<length;k++)
			{				
				/**
				 * Hidden Values corresponding to particular
				 * ITEM DETAILS
				 */	
				debugPoint = 3;
						if(vo.getStrQtyText()[k] != null && vo.getStrQtyText()[k].length() > 0 && !vo.getStrQtyText()[k].equals("0"))
						{	
							itemValue = vo.getItemParamValue()[k];
							
											 paramVal = vo.getItemParamValue()[k].split("#");						
											userValue = paramVal[2].replace('^', '#').split("#");	
											
											debugPoint = 4;
											
											if(strBillTariff != null && strBillTariff != "")
												strBillTariff=strBillTariff + "^"+userValue[1];
												else
													strBillTariff = userValue[1];
												if(strBillRate != null && strBillRate != "")
													strBillRate=strBillRate + "^"+userValue[9];
													else
														strBillRate = userValue[9];
												if(strBillQty != null && strBillQty != "")
													strBillQty=strBillQty + "^"+vo.getStrQtyText()[k];
													else
														strBillQty = vo.getStrQtyText()[k];
												if(strBillBatch != null && strBillBatch != "")
													strBillBatch=strBillBatch + "^"+userValue[15];
													else
														strBillBatch = userValue[15];
											
											String strInventoryUnitId = userValue[11];
							
							dmlhsttpatempissueitemdtl.setStrStoreId(vo.getStrStoreId());
							dmlhsttpatempissueitemdtl.setStrIssueNo(vo.getStrIssueNumber());
							dmlhsttpatempissueitemdtl.setStrItemId(userValue[0]);
							dmlhsttpatempissueitemdtl.setStrItemBrandId(userValue[1]);
							dmlhsttpatempissueitemdtl.setStrBatchSlNo(userValue[15]);
							dmlhsttpatempissueitemdtl.setStrHospitalCode(vo.getStrHospitalCode());
							
							debugPoint = 5;
							
							dmlhsttpatempissueitemdtl.setStrItemSlNo(userValue[18]);
							dmlhsttpatempissueitemdtl.setStrStockStatusCode(userValue[32]);
							dmlhsttpatempissueitemdtl.setStrGroupId(userValue[2]);
							dmlhsttpatempissueitemdtl.setStrSubGroupId(userValue[3]);
							dmlhsttpatempissueitemdtl.setStrRate(userValue[9]);
							dmlhsttpatempissueitemdtl.setStrRateUnitId(userValue[10]);
							
							debugPoint = 6;
							
							dmlhsttpatempissueitemdtl.setStrBalQty("0");
							dmlhsttpatempissueitemdtl.setStrBalQtyUnitId("");
							dmlhsttpatempissueitemdtl.setStrIssueQty(vo.getStrQtyText()[k]);
							dmlhsttpatempissueitemdtl.setStrIssueQtyUnitId(strInventoryUnitId); //Aritra,22nd June							
							dmlhsttpatempissueitemdtl.setStrExpiryDate(userValue[16]);
							dmlhsttpatempissueitemdtl.setStrRemarks(vo.getStrRemarks());
							
							debugPoint = 7;
							
							dmlhsttpatempissueitemdtl.setStrCost("0");
							dmlhsttpatempissueitemdtl.setStrInHandQty(userValue[7]);
							dmlhsttpatempissueitemdtl.setStrInHandQtyUnitId(userValue[8]);
							dmlhsttpatempissueitemdtl.setStrManufDate(userValue[17]);
							dmlhsttpatempissueitemdtl.setStrReqNo(vo.getStrRequestNo());
							dmlhsttpatempissueitemdtl.setStrCrNo(vo.getStrPuk());
							
							debugPoint = 8;
							
							dmlhsttpatempissueitemdtl.setStrEmpNo(vo.getStrReqEmpNo());
							dmlhsttpatempissueitemdtl.setStrVisitNo(strVisitNo);
							dmlhsttpatempissueitemdtl.setStrEpisodeCode(strEpisodeCode);
							dmlhsttpatempissueitemdtl.setStrAdmNo(vo.getStrReqAdmNo());
							dmlhsttpatempissueitemdtl.setStrOrderBy(vo.getStrReqPrescribedBy());//Id od the consultant
							
							debugPoint = 9;
							
							dmlhsttpatempissueitemdtl.setStrDays(vo.getStrReqPrescribedFor());
							dmlhsttpatempissueitemdtl.setStrReqTypeId(vo.getStrReqTypeId());
							dmlhsttpatempissueitemdtl.setStrItemCatNo(vo.getStrItemCat());
							
							debugPoint = 10;
							
							dmlhsttpatempissueitemdtl.setStrDosage("0");
							dmlhsttpatempissueitemdtl.setStrFrequency("0");
							dmlhsttpatempissueitemdtl.setStrPresDays("0");
							
							dmlhsttpatempissueitemdtl.setStrPatientName(vo.getStrPatientDtlHidVal().split("\\^")[7]);//    									
							dmlhsttpatempissueitemdtl.setStrSeatId(vo.getStrSeatId());
							dmlhsttpatempissueitemdtl.setStrDrugIssueDate(vo.getStrDrugIssueDate());
							
							debugPoint = 11;
							
							//System.out.println("Issue Daet=in With Cr NO DAO 55=>"+vo.getStrDrugIssueDate());
							//System.out.println("Out Of Stoc Flag=in With Cr NO DAO 66=>"+vo.getStrOutOfStockFlag());
							dmlhsttpatempissueitemdtl.insert2(dao);
							
						 }	
					
				}	
			System.out.println("vo.getStrLFAccountNo() ++++>>>DAO::"+vo.getStrLFAccountNo());
			if(mcu.getStrBillingIntegration().equals("1"))
			{
				int procIndex2;
				//procIndex2 = dao.setProcedure(proc_name2);
				//int procIndex2;
			//	String proc_name2 = "{call bill_interface.dml_online_billreq_drugs(?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?)}";
				procIndex2 = dao.setProcedure(proc_name2);
				dao.setProcInValue(procIndex2, "modval", "1", 1); // 1
				dao.setProcInValue(procIndex2, "gnum_dept_code", vo.getStrDeptCode(), 2);
				//System.out.println("gnum_dept_code"+vo.getStrDeptCode());
				dao.setProcInValue(procIndex2, "sblnum_chargetype_id", "2", 3);
				dao.setProcInValue(procIndex2, "sblnum_service_id", "5", 4);
				dao.setProcInValue(procIndex2, "gstr_req_no", strIssueNo, 5);
				dao.setProcInValue(procIndex2, "gnum_treatment_cat", vo.getStrPatientDtlHidVal().replace("^","#").split("#")[3],6);
				//System.out.println("gnum_treatment_cat"+"11");
				dao.setProcInValue(procIndex2, "hrgnum_episode_code", vo.getStrAdmissionDtlHidVal().replace("^", "#").split("#")[1], 7);
				//System.out.println("hrgnum_episode_code"+"1");
				dao.setProcInValue(procIndex2, "hrgnum_puk", vo.getStrPatientId(), 8);
				//System.out.println("hrgnum_puk"+vo.getStrCrNo());
				dao.setProcInValue(procIndex2, "hblnum_pat_lfaccount_no", "0", 9);
				dao.setProcInValue(procIndex2, "gstr_tariff", strBillTariff, 10);
				dao.setProcInValue(procIndex2, "gstr_tariff_batch", strBillBatch, 11);
				dao.setProcInValue(procIndex2, "gstr_tariff_rates", strBillRate, 12);
				dao.setProcInValue(procIndex2, "gstr_reqqty", strBillQty, 13);
				dao.setProcInValue(procIndex2, "hblstr_patient_name", "", 14);
				dao.setProcInValue(procIndex2, "hblstr_pat_address", "", 15);
				dao.setProcInValue(procIndex2, "hblstr_contact_no", "", 16);
				dao.setProcInValue(procIndex2, "age", vo.getStrPatientDtlHidVal().replace("^","#").split("#")[10].split(" ")[0], 17);
				dao.setProcInValue(procIndex2, "ageunit",vo.getStrPatientDtlHidVal().replace("^","#").split("#")[10].split(" ")[1].split("/")[0], 18);
				dao.setProcInValue(procIndex2, "gender", vo.getStrPatientDtlHidVal().replace("^","#").split("#")[1], 19);
				dao.setProcInValue(procIndex2, "refdoctor", "N/A", 20);
				dao.setProcInValue(procIndex2, "refdoccontactno", "0000000000", 21);
				dao.setProcInValue(procIndex2, "gnum_seatid", vo.getStrSeatId(), 22);
				//System.out.println("gnum_seatid"+vo.getStrSeatId());
				dao.setProcInValue(procIndex2, "hosp_code", vo.getStrHospitalCode(), 23);
				//System.out.println("hosp_code"+vo.getStrHospitalCode());
				dao.setProcInValue(procIndex2, "ward_code", vo.getStrAdmissionDtlHidVal().replace("^", "#").split("#")[6], 24);
				//System.out.println("ward_code"+vo.getStrWardCode());
				dao.setProcInValue(procIndex2, "admno", vo.getStrAdmissionDtlHidVal().replace("^", "#").split("#")[0], 25);
				///System.out.println("admno"+vo.getStrAdmissionNo());
				dao.setProcInValue(procIndex2, "visitno", vo.getStrVisitNo(), 26);
				//System.out.println("visitno"+vo.getStrVisitNo());
				dao.setProcOutValue(procIndex2, "err", 1, 27);
				dao.execute(procIndex2, 1);
				
			}
		 	
			
					
					// DAO Fire
					synchronized (dao) {
						dao.fire();
					}
					
					
					debugPoint = 21;
		  
		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			vo.setStrMsgType("1");
			vo.setStrMsgString("IssueTransDAO.insert() --> "
					+ e.getMessage() + " DAO_DEBUG_POINT :: " + debugPoint + " DAO_VALUE :: " + itemValue);
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
	
	public static synchronized void inserttemp(IssueTransVO vo) 
	{
		HisDAO         dao = null;		
		String strFuncName = "";
		int     nFuncIndex = 0;
		int         length = 0;
		String  paramVal[] = null;
		String userValue[] = null;
		String  strIssueNo = "";
		String strVisitDtl = "";
		Double     netCost = 0.00;
		DmlHsttPatEmpIssueItemDtlDAO dmlhsttpatempissueitemdtl = null;
		DmlHsttPatEmpIssueDtlDAO         dmlhsttpatempissuedtl = null;
		String visitType="1";
		int debugPoint = 0;
		String itemValue = "";
		/*
		 * Added by shefali garg
		*/
		String strIssueUnitId = "0";
		String strBillTariff = "";
		String strBillBatch = "";
		String strBillRate = "";
		String strBillQty = "";
		String proc_name2 = "{call bill_interface.dml_online_billreq_drugs(?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?)}";
		MmsConfigUtil mcu;

		try 
		{
			debugPoint = 1;
			mcu =new MmsConfigUtil(vo.getStrHospitalCode());
			dao = new HisDAO("MMS Transactions","IssueTransDAO");
			dmlhsttpatempissueitemdtl = new DmlHsttPatEmpIssueItemDtlDAO();
			    dmlhsttpatempissuedtl = new DmlHsttPatEmpIssueDtlDAO();			
			              strFuncName = "{? = call MMS_MST.generate_issueNo(?::numeric,?::numeric,?::numeric,?::numeric)}";
			               nFuncIndex = dao.setFunction(strFuncName);
			               
			dao.setFuncInValue(nFuncIndex, 2, vo.getStrHospitalCode());
			dao.setFuncInValue(nFuncIndex, 3, vo.getStrStoreId());
			dao.setFuncInValue(nFuncIndex, 4, "1");
			dao.setFuncInValue(nFuncIndex, 5, vo.getStrItemCat());
			dao.setFuncOutValue(nFuncIndex, 1);
			dao.executeFunction(nFuncIndex);
			strIssueNo = dao.getFuncString(nFuncIndex);
			vo.setStrIssueNumber(strIssueNo.split("\\^")[0]);
			
			debugPoint = 2;
			
			
		
			          strVisitDtl = "0"	;	
			String strEpisodeCode = "0";
			String     strVisitNo = "0" ;
			
			                  dao = new HisDAO("MMS Transactions","IssueTransDAO");
			               length = vo.getItemParamValue().length;	
			               
			for(int k=0;k<length;k++)
			{				
				/**
				 * Hidden Values corresponding to particular
				 * ITEM DETAILS
				 */	
				debugPoint = 3;
						if(vo.getStrQtyText()[k] != null && vo.getStrQtyText()[k].length() > 0 && !vo.getStrQtyText()[k].equals("0"))
						{	
							itemValue = vo.getItemParamValue()[k];
							
											 paramVal = vo.getItemParamValue()[k].split("#");						
											userValue = paramVal[2].replace('^', '#').split("#");	
											
											debugPoint = 4;
											
											if(strBillTariff != null && strBillTariff != "")
												strBillTariff=strBillTariff + "^"+userValue[1];
												else
													strBillTariff = userValue[1];
												if(strBillRate != null && strBillRate != "")
													strBillRate=strBillRate + "^"+userValue[9];
													else
														strBillRate = userValue[9];
												if(strBillQty != null && strBillQty != "")
													strBillQty=strBillQty + "^"+vo.getStrQtyText()[k];
													else
														strBillQty = vo.getStrQtyText()[k];
												if(strBillBatch != null && strBillBatch != "")
													strBillBatch=strBillBatch + "^"+userValue[15];
													else
														strBillBatch = userValue[15];
											
											String strInventoryUnitId = userValue[11];
							
							dmlhsttpatempissueitemdtl.setStrStoreId(vo.getStrStoreId());
							dmlhsttpatempissueitemdtl.setStrIssueNo(vo.getStrIssueNumber());
							dmlhsttpatempissueitemdtl.setStrItemId(userValue[0]);
							dmlhsttpatempissueitemdtl.setStrItemBrandId(userValue[1]);
							dmlhsttpatempissueitemdtl.setStrBatchSlNo(userValue[15]);
							dmlhsttpatempissueitemdtl.setStrHospitalCode(vo.getStrHospitalCode());
							
							debugPoint = 5;
							
							dmlhsttpatempissueitemdtl.setStrItemSlNo(userValue[18]);
							dmlhsttpatempissueitemdtl.setStrStockStatusCode(userValue[32]);
							dmlhsttpatempissueitemdtl.setStrGroupId(userValue[2]);
							dmlhsttpatempissueitemdtl.setStrSubGroupId(userValue[3]);
							dmlhsttpatempissueitemdtl.setStrRate(userValue[9]);
							dmlhsttpatempissueitemdtl.setStrRateUnitId(userValue[10]);
							
							debugPoint = 6;
							
							dmlhsttpatempissueitemdtl.setStrBalQty("0");
							dmlhsttpatempissueitemdtl.setStrBalQtyUnitId("");
							dmlhsttpatempissueitemdtl.setStrIssueQty(vo.getStrQtyText()[k]);
							dmlhsttpatempissueitemdtl.setStrIssueQtyUnitId(strInventoryUnitId); //Aritra,22nd June							
							dmlhsttpatempissueitemdtl.setStrExpiryDate(userValue[16]);
							dmlhsttpatempissueitemdtl.setStrRemarks(vo.getStrRemarks());
							
							debugPoint = 7;
							
							dmlhsttpatempissueitemdtl.setStrCost("0");
							dmlhsttpatempissueitemdtl.setStrInHandQty(userValue[7]);
							dmlhsttpatempissueitemdtl.setStrInHandQtyUnitId(userValue[8]);
							dmlhsttpatempissueitemdtl.setStrManufDate(userValue[17]);
							dmlhsttpatempissueitemdtl.setStrReqNo(vo.getStrRequestNo());
							dmlhsttpatempissueitemdtl.setStrCrNo(vo.getStrPuk());
							
							debugPoint = 8;
							
							dmlhsttpatempissueitemdtl.setStrEmpNo(vo.getStrReqEmpNo());
							dmlhsttpatempissueitemdtl.setStrVisitNo(strVisitNo);
							dmlhsttpatempissueitemdtl.setStrEpisodeCode(vo.getStrEpisodeCode());
							dmlhsttpatempissueitemdtl.setStrAdmNo(vo.getStrReqAdmNo());
							dmlhsttpatempissueitemdtl.setStrOrderBy(vo.getStrReqPrescribedBy());//Id od the consultant
							
							debugPoint = 9;
							
							dmlhsttpatempissueitemdtl.setStrDays(vo.getStrReqPrescribedFor());
							dmlhsttpatempissueitemdtl.setStrReqTypeId(vo.getStrReqTypeId());
							dmlhsttpatempissueitemdtl.setStrItemCatNo(vo.getStrItemCat());
							
							debugPoint = 10;
							
							dmlhsttpatempissueitemdtl.setStrDosage("0");
							dmlhsttpatempissueitemdtl.setStrFrequency("0");
							dmlhsttpatempissueitemdtl.setStrPresDays("0");
							
							dmlhsttpatempissueitemdtl.setStrPatientName(vo.getStrPatientDtlHidVal().split("\\^")[7]);//    									
							dmlhsttpatempissueitemdtl.setStrSeatId(vo.getStrSeatId());
							dmlhsttpatempissueitemdtl.setStrDrugIssueDate(vo.getStrDrugIssueDate());
							
							debugPoint = 11;
							
							//System.out.println("Issue Daet=in With Cr NO DAO 55=>"+vo.getStrDrugIssueDate());
							//System.out.println("Out Of Stoc Flag=in With Cr NO DAO 66=>"+vo.getStrOutOfStockFlag());
							dmlhsttpatempissueitemdtl.insert2temp(dao);
							
						 }	
					
				}	
			
			if(vo.getStrVisitType().equals("2") || vo.getStrVisitType().equals("3"))
				visitType = "3";
			else if(vo.getStrVisitType().equals("4"))
				visitType="4";
			
			System.out.println("vo.getStrLFAccountNo() ++++>>>DAO::"+vo.getStrLFAccountNo());
			if(vo.getStrLFAccountNo() != null && vo.getStrLFAccountNo() !="")
			{
			if(mcu.getStrBillingIntegration().equals("1"))
			{
				int procIndex2;
				procIndex2 = dao.setProcedure(proc_name2);
				
				System.out.println("modval"+ "2"); // 1
				System.out.println("gnum_dept_code"+ vo.getStrDeptCode());
				System.out.println("sblnum_chargetype_id"+ "1");
				System.out.println("sblnum_service_id"+ "5");
				System.out.println("gstr_req_no"+ strIssueNo);
				System.out.println("gnum_treatment_cat"+ vo.getStrCatgoryCode());
				System.out.println("hrgnum_episode_code"+"1");
				System.out.println("hrgnum_puk"+ vo.getStrCrNo());
				System.out.println("hblnum_pat_lfaccount_no"+ vo.getStrLFAccountNo());
				System.out.println("gstr_tariff"+ strBillTariff);
				System.out.println("gstr_tariff_batch"+ strBillBatch);
				System.out.println("gstr_tariff_rates"+ strBillRate);
				System.out.println("gstr_reqqty"+ strBillQty);
				System.out.println("hblstr_patient_name"+ "");
				System.out.println("hblstr_pat_address"+ "");
				System.out.println("hblstr_contact_no"+ "");
				System.out.println("age"+ "1");
				System.out.println("ageunit"+ "1");
				System.out.println("gender"+ "1");
				System.out.println("refdoctor"+ "1");
				System.out.println("refdoccontactno"+ "1");
				System.out.println("gnum_seatid"+ vo.getStrSeatId());
				System.out.println("hosp_code"+ vo.getStrHospitalCode());
				System.out.println("ward_code"+ vo.getStrWardCode());
				System.out.println("admno"+"1");
				System.out.println("visitno"+ vo.getStrVisitNo());

				
				
				dao.setProcInValue(procIndex2, "modval", "2", 1); // 1
				dao.setProcInValue(procIndex2, "gnum_dept_code", vo.getStrDeptCode(), 2);
				dao.setProcInValue(procIndex2, "sblnum_chargetype_id", visitType, 3);
				dao.setProcInValue(procIndex2, "sblnum_service_id", "5", 4);
				dao.setProcInValue(procIndex2, "gstr_req_no", strIssueNo, 5);
				dao.setProcInValue(procIndex2, "gnum_treatment_cat", vo.getStrCatgoryCode(),6);
				dao.setProcInValue(procIndex2, "hrgnum_episode_code",vo.getStrEpisodeCode(), 7);
				dao.setProcInValue(procIndex2, "hrgnum_puk", vo.getStrCrNo(), 8);
				dao.setProcInValue(procIndex2, "hblnum_pat_lfaccount_no", vo.getStrLFAccountNo(), 9);
				dao.setProcInValue(procIndex2, "gstr_tariff", strBillTariff, 10);
				dao.setProcInValue(procIndex2, "gstr_tariff_batch", strBillBatch, 11);
				dao.setProcInValue(procIndex2, "gstr_tariff_rates", strBillRate, 12);
				dao.setProcInValue(procIndex2, "gstr_reqqty", strBillQty, 13);
				dao.setProcInValue(procIndex2, "hblstr_patient_name", "", 14);
				dao.setProcInValue(procIndex2, "hblstr_pat_address", "", 15);
				dao.setProcInValue(procIndex2, "hblstr_contact_no", "", 16);
				dao.setProcInValue(procIndex2, "age", "1", 17);
				dao.setProcInValue(procIndex2, "ageunit", "1", 18);
				dao.setProcInValue(procIndex2, "gender", "1", 19);
				dao.setProcInValue(procIndex2, "refdoctor", "1", 20);
				dao.setProcInValue(procIndex2, "refdoccontactno", "1", 21);
				dao.setProcInValue(procIndex2, "gnum_seatid", vo.getStrSeatId(), 22);
				dao.setProcInValue(procIndex2, "hosp_code", vo.getStrHospitalCode(), 23);
				dao.setProcInValue(procIndex2, "ward_code", vo.getStrWardCode(), 24);
				dao.setProcInValue(procIndex2, "admno","0", 25);
				dao.setProcInValue(procIndex2, "visitno", vo.getStrVisitNo(), 26);
				dao.setProcOutValue(procIndex2, "err", 1, 27);
				dao.execute(procIndex2, 1);
				
			}
		}	
			
		else
			if(mcu.getStrBillingIntegration().equals("1"))
			{
				int procIndex2;
				procIndex2 = dao.setProcedure(proc_name2);
				
				System.out.println("modval"+ "1"); // 1
				System.out.println("gnum_dept_code"+ vo.getStrDeptCode());
				System.out.println("sblnum_chargetype_id"+ "1");
 				System.out.println("sblnum_service_id"+ "5");
				System.out.println("gstr_req_no"+ strIssueNo);
				System.out.println("gnum_treatment_cat"+ vo.getStrCatgoryCode());
				System.out.println("hrgnum_episode_code"+"1");
				System.out.println("hrgnum_puk"+ vo.getStrPuk());
				System.out.println("hblnum_pat_lfaccount_no"+ vo.getStrLFAccountNo());
				System.out.println("gstr_tariff"+ strBillTariff);
				System.out.println("gstr_tariff_batch"+ strBillBatch);
				System.out.println("gstr_tariff_rates"+ strBillRate);
				System.out.println("gstr_reqqty"+ strBillQty);
				System.out.println("hblstr_patient_name"+ "");
				System.out.println("hblstr_pat_address"+ "");
				System.out.println("hblstr_contact_no"+ "");
				System.out.println("age"+ "1");
				System.out.println("ageunit"+ "1");
				System.out.println("gender"+ "1");
				System.out.println("refdoctor"+ "1");
				System.out.println("refdoccontactno"+ "1");
				System.out.println("gnum_seatid"+ vo.getStrSeatId());
				System.out.println("hosp_code"+ vo.getStrHospitalCode());
				System.out.println("ward_code"+ vo.getStrWardCode());
				System.out.println("admno"+"1");
				System.out.println("visitno"+ vo.getStrVisitNo());
				
				
				
				dao.setProcInValue(procIndex2, "modval", "1", 1); // 1
				dao.setProcInValue(procIndex2, "gnum_dept_code", vo.getStrDeptCode(), 2);
				dao.setProcInValue(procIndex2, "sblnum_chargetype_id", visitType, 3);
				dao.setProcInValue(procIndex2, "sblnum_service_id", "5", 4);
				dao.setProcInValue(procIndex2, "gstr_req_no", strIssueNo, 5);
				dao.setProcInValue(procIndex2, "gnum_treatment_cat", vo.getStrCatgoryCode(),6);
				dao.setProcInValue(procIndex2, "hrgnum_episode_code",vo.getStrEpisodeCode(), 7);
				dao.setProcInValue(procIndex2, "hrgnum_puk", vo.getStrPuk(), 8);
				dao.setProcInValue(procIndex2, "hblnum_pat_lfaccount_no", vo.getStrLFAccountNo(), 9);
				dao.setProcInValue(procIndex2, "gstr_tariff", strBillTariff, 10);
				dao.setProcInValue(procIndex2, "gstr_tariff_batch", strBillBatch, 11);
				dao.setProcInValue(procIndex2, "gstr_tariff_rates", strBillRate, 12);
				dao.setProcInValue(procIndex2, "gstr_reqqty", strBillQty, 13);
				dao.setProcInValue(procIndex2, "hblstr_patient_name", "", 14);
				dao.setProcInValue(procIndex2, "hblstr_pat_address", "", 15);
				dao.setProcInValue(procIndex2, "hblstr_contact_no", "", 16);
				dao.setProcInValue(procIndex2, "age", "1", 17);
				dao.setProcInValue(procIndex2, "ageunit", "1", 18);
				dao.setProcInValue(procIndex2, "gender", "1", 19);
				dao.setProcInValue(procIndex2, "refdoctor", "1", 20);
				dao.setProcInValue(procIndex2, "refdoccontactno", "1", 21);
				dao.setProcInValue(procIndex2, "gnum_seatid", vo.getStrSeatId(), 22);
				dao.setProcInValue(procIndex2, "hosp_code", vo.getStrHospitalCode(), 23);
				dao.setProcInValue(procIndex2, "ward_code", vo.getStrWardCode(), 24);
				dao.setProcInValue(procIndex2, "admno","0", 25);
				dao.setProcInValue(procIndex2, "visitno", vo.getStrVisitNo(), 26);
				dao.setProcOutValue(procIndex2, "err", 1, 27);
				dao.execute(procIndex2, 1);
				
			}
		
			debugPoint = 12;
			
					dmlhsttpatempissuedtl.setStrStoreId(vo.getStrStoreId());
					dmlhsttpatempissuedtl.setStrIssueNo(vo.getStrIssueNumber());
					dmlhsttpatempissuedtl.setStrHospitalCode(vo.getStrHospitalCode());
					dmlhsttpatempissuedtl.setStrRequestNo(vo.getStrRequestNo());
					dmlhsttpatempissuedtl.setStrOrderBy(vo.getStrReqPrescribedBy());
					
					debugPoint = 13;
					
					dmlhsttpatempissuedtl.setStrOrderDate(vo.getStrReqPrescriptionDate());
					dmlhsttpatempissuedtl.setStrDays(vo.getStrPrescriptionFor());
					dmlhsttpatempissuedtl.setStrCrNo(vo.getStrPuk());
					dmlhsttpatempissuedtl.setStrReqTypeId(vo.getStrReqTypeId());
					dmlhsttpatempissuedtl.setStrAdmNo(vo.getStrReqAdmNo());
					
					debugPoint = 14;
					
					dmlhsttpatempissuedtl.setStrEmpNo(vo.getStrReqEmpNo());
					dmlhsttpatempissuedtl.setStrItemCatNo(vo.getStrItemCat());
					dmlhsttpatempissuedtl.setStrFinalCost(DmlHsttPatEmpIssueItemDtlDAO.getIntCost()+"");
					dmlhsttpatempissuedtl.setStrFinStartDate(vo.getStrFinStartDate());
					dmlhsttpatempissuedtl.setStrFinEndDate(vo.getStrFinEndDate());
					
					debugPoint = 15;
					
					dmlhsttpatempissuedtl.setStrSeatId(vo.getStrSeatId());
					dmlhsttpatempissuedtl.setStrDeptUnitCode(vo.getStrUnitCode());
					dmlhsttpatempissuedtl.setStrVisitType(vo.getStrVisitType());
					dmlhsttpatempissuedtl.setStrDeptCode(vo.getStrDeptCode());
					
					dmlhsttpatempissuedtl.setStrWardCode(vo.getStrReqWardCode());
					
					debugPoint = 16;
					if(vo.getStrEmpCode().equals("")||vo.getStrEmpCode() == null)
						vo.setStrEmpCode("0");
					if(vo.getStrDiagCode().equals("")||vo.getStrDiagCode() == null)
						vo.setStrDiagCode("0");
					dmlhsttpatempissuedtl.setStrReceiveBy(vo.getStrReceiveBy());
					dmlhsttpatempissuedtl.setStrRemarks(vo.getStrRemarks());
					dmlhsttpatempissuedtl.setStrReqDate("");
					dmlhsttpatempissuedtl.setStrVisitNo(strVisitNo);
					dmlhsttpatempissuedtl.setStrEpisoCode(strEpisodeCode);
					dmlhsttpatempissuedtl.setStrEmpCode(vo.getStrEmpCode());
					dmlhsttpatempissuedtl.setStrIcdCode(vo.getStrDiagCode());
					
					debugPoint = 17;
					
					// By Vivek		
                    // vo.getStrPatientHiddenValue1	    Patient Name ^Father Name^Category Code^Address^Mlc No						
					/* vo.getStrPatientDtlHidVal()
		              HRGDT_DOB || ''^'' ||GNUM_GENDER_CODE ||''^''||HGNUM_PAT_STATUS_CODE || ''^''||
			          HGNUM_PATIENT_CAT_CODE || ''^'' ||HRGNUM_IS_MLC    ||''^''||HRGNUM_ISNEWBORN      || ''^''|| HRGNUM_ID_NO
					*/
					// By Vivek	
					
					itemValue += " PatientHiddenValue1 :: " + vo.getStrPatientHiddenValue1() + " StrPatientDtlHidVal :: " + vo.getStrPatientDtlHidVal();

					dmlhsttpatempissuedtl.setStrFirstName(vo.getStrPatientDtlHidVal().split("\\^")[7]);
					dmlhsttpatempissuedtl.setStrMiddelName("");
					dmlhsttpatempissuedtl.setStrLastName("");
					dmlhsttpatempissuedtl.setStrPatientId(vo.getStrPatientId());				
					dmlhsttpatempissuedtl.setStrPatientType("18");		
					
					debugPoint = 18;
					
					dmlhsttpatempissuedtl.setStrPatientAge(vo.getStrPatientDtlHidVal().split("\\^")[0]);
					dmlhsttpatempissuedtl.setStrPatientAgeUnit("0");
					dmlhsttpatempissuedtl.setStrPatientFatherName(vo.getStrPatientDtlHidVal().split("\\^")[8]);
					dmlhsttpatempissuedtl.setStrPatientGenderCode(vo.getStrPatientDtlHidVal().split("\\^")[1]);
					
					debugPoint = 19;
					
					dmlhsttpatempissuedtl.setStrPatientAddress(vo.getStrPatientHiddenValue1().split("\\^")[3]);
					dmlhsttpatempissuedtl.setStrTransType("1");  // With CR No	
					dmlhsttpatempissuedtl.setStrDrugIssueDate(vo.getStrDrugIssueDate());
					dmlhsttpatempissuedtl.setStrOutOfStockFlag(vo.getStrOutOfStockFlag());
					
					debugPoint = 20;
					
					//System.out.println("Issue Daet=in With Cr NO DAO 77=>"+vo.getStrDrugIssueDate());
					//System.out.println("Out Of Stoc Flag=in With Cr NO DAO 88=>"+vo.getStrOutOfStockFlag());
					dmlhsttpatempissuedtl.inserttemp(dao);
					
					
					// DAO Fire
					synchronized (dao) {
						dao.fire();
					}
					
					
					debugPoint = 21;
		  
		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			vo.setStrMsgType("1");
			vo.setStrMsgString("IssueTransDAO.insert() --> "
					+ e.getMessage() + " DAO_DEBUG_POINT :: " + debugPoint + " DAO_VALUE :: " + itemValue);
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
	
	//Change Request
	/**
	 *  
	 * @param vo
	 */
	public static synchronized void insertWithoutCrNo(IssueTransVO vo)
	{
	
		HisDAO           dao  = null;
		String    strFuncName = "";
		int        nFuncIndex = 0;
		int       nFuncIndex2 = 0;
		String     strIssueNo = "",strCrNum="0";
		String     strVisitNo = "0";
		Double        netCost = 0.00;
		String strPatientName = "";
		String strFuncName2   = "";
		StringBuffer sb       = new StringBuffer("");
		ResourceBundle   res  = null;	
		String strTableWidth  = "825";
		int                 j = 1;
		int debug_point = 0;
		
		DmlHsttPatEmpIssueItemDtlWithoutCrNoDAO dmlhsttpatempissueitemdtlWithoutCrNo = null;
		DmlHsttPatEmpIssueDtlWithoutCrNoDAO      dmlHsttPatEmpIssueDtlWithoutCrNoDAO = null;

		try 
		{
			debug_point = 1;
			vo.setStrReqTypeId("32");
			
            dao = new HisDAO("MMS Transactions","IssueTransDAO");
            res = mms.qryHandler_mms.res;
			if(res == null) 
			{
				res = ResourceBundle.getBundle("hisglobal.hisconfig.hisProperties");
				mms.qryHandler_mms.res = res;
			}
            
			dmlhsttpatempissueitemdtlWithoutCrNo = new DmlHsttPatEmpIssueItemDtlWithoutCrNoDAO();
			dmlHsttPatEmpIssueDtlWithoutCrNoDAO  = new DmlHsttPatEmpIssueDtlWithoutCrNoDAO();			
			
			debug_point = 2;
			strFuncName2 = "{? = call MMS_MST.generate_new_CRNo(?,?)}";
			nFuncIndex2 = dao.setFunction(strFuncName2);
			dao.setFuncInValue(nFuncIndex2, 2, vo.getStrHospitalCode());
			dao.setFuncInValue(nFuncIndex2, 3, vo.getStrStoreId());
			dao.setFuncOutValue(nFuncIndex2, 1);
			dao.executeFunction(nFuncIndex2);
			strCrNum = dao.getFuncString(nFuncIndex2);
			vo.setStrCrNum(strCrNum);
			
			debug_point = 3;
			
		     strFuncName = "{? = call MMS_MST.generate_issueNo(?,?,?,?)}";
			 nFuncIndex = dao.setFunction(strFuncName);
			
			dao.setFuncInValue(nFuncIndex, 2, vo.getStrHospitalCode());
			dao.setFuncInValue(nFuncIndex, 3, vo.getStrStoreId());
			dao.setFuncInValue(nFuncIndex, 4, vo.getStrReqTypeId());
			dao.setFuncInValue(nFuncIndex, 5, vo.getStrItemCat());
			dao.setFuncOutValue(nFuncIndex, 1);
			dao.executeFunction(nFuncIndex);
			strIssueNo = dao.getFuncString(nFuncIndex);			
			vo.setStrIssueNumber(strIssueNo.split("\\^")[0]);			
			strPatientName = vo.getStrFirstName();	
			
			debug_point = 4;
			
			if(vo.getStrIssueMode().equals("0") || vo.getStrIssueMode().equals("1") ||vo.getStrIssueMode().equals("2"))
			{
				sb.append("<table align='center' width='").append(strTableWidth + "' border='0' cellspacing='0' cellpadding='0' height='69'> ");
				sb.append("<tr>");
				sb.append("<td width='10%'></td>");			
				sb.append("<td width='80%' align='center'> <b><font face='Verdana, Arial, Helvetica, sans-serif' size='2'> ");
				sb.append(res.getString("PAT_ISSUE_TITLE1"));
				sb.append("</font></b></td> <td width='10%'>&nbsp;</td> ");
				sb.append("</tr> ");
				sb.append("<tr>");
				sb.append("<td width='10%'></td>");					
				sb.append("<td width='90%' align='center'> <b><font face='Verdana, Arial, Helvetica, sans-serif' size='3'> ");
				sb.append(res.getString("PAT_ISSUE_TITLE2"));
				sb.append("</font></b></td> <td width='10%'>&nbsp;</td> ");
				sb.append("</tr> ");		
				sb.append("<tr>");
				sb.append("<td width='10%'></td>");					
				sb.append("<td width='90%' align='center'> <b><font face='Verdana, Arial, Helvetica, sans-serif' size='2'> ");
				sb.append(vo.getStrHospitalName());
				sb.append("</font></b></td> <td width='10%'>&nbsp;</td> ");
				sb.append("</tr> ");
				sb.append("</table>"); 	
				
				debug_point = 5;
				
				sb.append("<table border='0' width='" + strTableWidth + "' align='center'> ");
				sb.append("<tr> ");
				sb.append("<td align='right'>");
	 			sb.append("<div id='saveId' style='display : block'> <div id='printid1' class='hidecontrol' align='right'>");
				sb.append("<img style='cursor: pointer; '  name='PrintPage' tabindex='1' title='Print Page'");
				sb.append(" src='../../hisglobal/images/printer_symbol.gif' onClick='printDataOne(1);' onkeypress='initPrint(event);'/> <img style='cursor: pointer; ' title='Cancel Process'  ");
				sb.append(" src='../../hisglobal/images/stop.png' onClick='hideIssuePopupOne();' /></div>");
				sb.append(" </td> ");
				sb.append(" </tr> ");
				sb.append(" </table> ");
				sb.append(" <br> ");
				
				debug_point = 6;
				
				sb.append("<table width='").append(strTableWidth).append("' align='center' cellpadding='1px' cellspacing='1px'> ");	
				/*************************************************1*******************************************************************/
				sb.append("<tr> ");	
				sb.append("<td width='25%' align='right'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Patient:</b></font></td> ");
//				sb.append("<td width='25%' ><font face='Verdana, Arial, Helvetica, sans-serif' ><b>").append(formBean.getStrIssueNo())
//							.append("</b></font></td> ");
				sb.append("<td width='25%' ><font face='Verdana, Arial, Helvetica, sans-serif' >"+vo.getStrFirstName()+"</font></td> ");		
				sb.append("<td width='25%' align='right'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>").append("DDC:</b></font></td> ");
				sb.append("<td width='25%' ><font face='Verdana, Arial, Helvetica, sans-serif' >").append(vo.getStrStoreName()).append(
							"</font></td> ");
				sb.append("</tr> ");
				/**************************************************2*****************************************************************/
				
				debug_point = 7;
				
				sb.append("<tr> ");	
				sb.append("<td width='25%' align='right'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>").append("Doctor:</b></font></td> ");
				sb.append("<td width='25%' ><font face='Verdana, Arial, Helvetica, sans-serif' > Dr. ").append(vo.getStrReqPrescribedBy())
							.append("</font></td> ");		
				sb.append("<td width='25%' align='right'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>").append("Issue No:</b></font></td> ");
				sb.append("<td width='25%' ><font face='Verdana, Arial, Helvetica, sans-serif' >").append(vo.getStrIssueNumber()).append(
							"</font></td> ");
				sb.append("</tr> ");
				/**************************************************3******************************************************************/
				sb.append("<tr> ");	
				sb.append("<td width='25%' align='right'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>").append("Reg.No:</b></font></td> ");
//				sb.append("<td width='25%' ><font face='Verdana, Arial, Helvetica, sans-serif' ><b>").append(formBean.getStrIssueNo())
//							.append("</b></font></td> ");
				sb.append("<td width='25%' ><font face='Verdana, Arial, Helvetica, sans-serif' >"+vo.getStrPatientId()+"</font></td> ");		
				sb.append("<td width='25%' align='right'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>").append("Issue Date:</b></font></td> ");
				sb.append("<td width='25%' ><font face='Verdana, Arial, Helvetica, sans-serif' >").append(vo.getStrDrugIssueDate()).append(
							"</font></td> ");
				sb.append("</tr> ");
				/***************************************************4*****************************************************************/
				sb.append("<tr> ");			
				sb.append("<td width='25%' align='right'></td> ");
				sb.append("<td width='25%' ></td> ");
				sb.append("<td width='25%' align='right'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>").append("CR No:</b></font></td> ");
			     sb.append("<td width='25%' ><font face='Verdana, Arial, Helvetica, sans-serif' >").append(strCrNum)
						.append("</font></td> ");
				sb.append("</tr> ");
				/***************************************************5*****************************************************************/
				sb.append("</table> ");
				
				debug_point = 8;
				
				sb.append("<table width='").append(strTableWidth).append("' align='center' cellpadding='1px' cellspacing='1px' border='0px'> ");
				sb.append("<tr>");
				sb.append("<td colspan='4' align='left'><hr size='2'></td>");
				sb.append("<td colspan='1' align='center'><hr size='2'></td>");					
				sb.append("</tr>");
				sb.append("<tr bgcolor='#cdc9c9'> ");
				sb.append("<td align='center' width='5%'><font  face='Verdana, Arial, Helvetica, sans-serif' ><b>S.No.</b></font> ");
				sb.append("</td>");						
				sb.append("<td align='center' width='35%'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Drug Name</b></font> ");
				sb.append("</td>");
				sb.append("<td align='center' width='10%'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Batch No.</b></font> ");
				sb.append("</td> ");
				sb.append("<td align='center' width='10%'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Expiry Date</b></font>");
				sb.append("</td> ");
				sb.append("<td align='right' width='10%'><font  face='Verdana, Arial, Helvetica, sans-serif' ><b>Issue Qty</b></font>");
				sb.append("</td> ");
				sb.append("</tr> ");
				sb.append("<tr>");
				sb.append("<td colspan='4' align='left'><hr size='2'></td>");
				sb.append("<td colspan='1' align='center'><hr size='2'></td>");					
				sb.append("</tr>");
				
				debug_point = 9;
				
				for (int i = 0; i < vo.getStrItemIdArray().length; i++) 
				{
					debug_point = 10;
					
					if(vo.getStrIssueQty()[i] != null && vo.getStrIssueQty()[i].length() > 0 && !vo.getStrIssueQty()[i].equals("0"))
					{
						    String strModfExp;				    	  
				    		String[] temp1 = vo.getStrExpiryDate()[i].replace("-", "#").split("#");
				    		strModfExp   = temp1[1]+"/"+temp1[2];
				    	    sb.append("<tr> ");
							sb.append("<td style=\'text-align:center;\'  width='5%'><font face='Verdana, Arial, Helvetica, sans-serif' >");
							sb.append(j+".");
							sb.append("</font></td> ");					
							sb.append("<td style=\'text-align:left;\'    width='35%'><font face='Verdana, Arial, Helvetica, sans-serif' size='1' >");
							sb.append(vo.getStrItemNameArray()[i]);
							sb.append("</font></td> ");
							sb.append("<td style=\'text-align:center;\'  width='10%'><font face='Verdana, Arial, Helvetica, sans-serif' >");
							sb.append(vo.getStrBatchNo()[i]);
							sb.append("</font></td> ");
							sb.append("<td style=\'text-align:center;\'  width='10%'><font face='Verdana, Arial, Helvetica, sans-serif' >");
							sb.append(strModfExp);
							sb.append("</font></td> ");
							sb.append("<td style=\'text-align:right;\'   width='10%'><font face='Verdana, Arial, Helvetica, sans-serif' >");
							sb.append(vo.getStrIssueQty()[i]);
							sb.append("</font></td> ");
							sb.append("</tr> ");
							
							debug_point = 11;
						
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrStoreId(vo.getStrStoreId());
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrIssueNo(vo.getStrIssueNumber());
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrItemId(vo.getStrItemIdArray()[i]);
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrItemBrandId(vo.getStrBrandIdArray()[i]);
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrHospitalCode(vo.getStrHospitalCode());
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrGroupId(vo.getStrGroupIdArray()[i]);
							
							debug_point = 12;
							
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrSubGroupId(vo.getStrSubGroupIdArray()[i]);
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrStockStatusCode(vo.getStrStockStatusCode()[i]);
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrItemSlNo(vo.getStrItemSlNo()[i]);
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrItemSlNo(vo.getStrManufDate()[i]);
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrBalQty("0");
							
							debug_point = 13;
							
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrBalQtyUnitId("");
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrInHandQty(vo.getStrInhandQtyArray()[i]);
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrInHandQtyUnitId(vo.getStrInhandQtyUnitId()[i]);
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrBatchSlNo(vo.getStrBatchNo()[i]);							
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrManufDate(vo.getStrManufDate()[i]);
							
							debug_point = 14;
							
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrExpiryDate(vo.getStrExpiryDate()[i]);
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrRemarks(vo.getStrRemarks());
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrDescription(vo.getStrDescription());
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrReqNo(vo.getStrRequestNo());
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrItemCatNo(vo.getStrItemCat());
							
							debug_point = 15;
							
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrIssueQtyUnitId(vo.getStrInhandQtyUnitId()[i]);
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrCrNo(strCrNum);    // Generated CR No.
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrEmpNo(vo.getStrEmpNo());
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrVisitNo(vo.getStrVisitNo());
							
							debug_point = 16;
							
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrEpisodeCode(vo.getStrEpisCode());
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrAdmNo(vo.getStrAdmNo());							
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrOrderBy(vo.getStrReqPrescribedBy());
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrDays(vo.getStrReqPrescribedFor());
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrReqTypeId(vo.getStrReqTypeId());
							
							debug_point = 17;
							
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrRate(vo.getStrRate()[i]);
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrRateUnitId(vo.getStrRateUnitId()[i]);
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrIssueQty(vo.getStrIssueQty()[i]);	
							
							debug_point = 18;
							
							/*
							if(vo.getStrDose()!=null && !vo.getStrDose()[i].equals(""))
							{
								dmlhsttpatempissueitemdtlWithoutCrNo.setStrDosage(vo.getStrDose()[i].replace('^', '#').split("#")[0]);
								dmlhsttpatempissueitemdtlWithoutCrNo.setStrFrequency(vo.getStrFrequency()[i].replace('^', '#').split("#")[0]);
								dmlhsttpatempissueitemdtlWithoutCrNo.setStrPresDays(vo.getStrDays()[i]);
							}
							else
							{
								dmlhsttpatempissueitemdtlWithoutCrNo.setStrDosage("0");
								dmlhsttpatempissueitemdtlWithoutCrNo.setStrFrequency("0");
								dmlhsttpatempissueitemdtlWithoutCrNo.setStrPresDays("0");
							}		
												
												*/
							
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrDosage("0");
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrFrequency("0");
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrPresDays("0");
							
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrSeatId(vo.getStrSeatId());
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrPatientName(strPatientName);
							dmlhsttpatempissueitemdtlWithoutCrNo.setStrDrugIssueDate(vo.getStrDrugIssueDate());
							
							debug_point = 19;
							
							dmlhsttpatempissueitemdtlWithoutCrNo.insert1(dao);    // Dml_Patemp_Issue_Items_Dtls  Mode 1
							debug_point = 20;
							
							//System.out.println("Issue Daet=DAO=>"+vo.getStrDrugIssueDate());
							//System.out.println("Out Of Stoc Flag=DAO=>"+vo.getStrOutOfStockFlag());
							j++;
					}	
										
				}
				// Total 36 Variable
								
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrStoreId(vo.getStrStoreId());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrIssueNo(vo.getStrIssueNumber());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrHospitalCode(vo.getStrHospitalCode());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrRequestNo(vo.getStrRequestNo());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrOrderBy("Dr. "+vo.getStrReqPrescribedBy());	
				
				debug_point = 21;
				
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrOrderDate(vo.getStrReqPrescriptionDate());  // Check It
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrDays(vo.getStrReqPrescribedFor());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrCrNo(strCrNum);                        // Generated CR No
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrReqTypeId(vo.getStrReqTypeId());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrAdmNo(vo.getStrAdmNo());
				
				debug_point = 22;
				
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrEmpNo(vo.getStrEmpNo());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrItemCatNo(vo.getStrItemCat());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrCost(""+netCost);
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrFinStartDate(vo.getStrFinStartDate());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrFinEndDate(vo.getStrFinEndDate());
				
				debug_point = 23;
				
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrSeatId(vo.getStrSeatId());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrDeptUnitCode(vo.getStrReqUnitCode());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrVisitNo(strVisitNo);
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrVisitType(vo.getStrReqPrescriptionFrom());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrDeptCode(vo.getStrReqDeptCode());
				
				debug_point = 24;
				
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrWardCode("0");
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrReceiveBy(vo.getStrReceiveBy());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrRemarks(vo.getStrRemarks());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrReqDate(vo.getStrReqDate());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrVisitNo(vo.getStrVisitNo());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrEpisoCode(vo.getStrEpisCode());	
				
				debug_point = 25;
				// By Vivek			
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrPatientId(vo.getStrPatientId());				
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrPatientType(vo.getStrPatientType());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrFirstName(vo.getStrFirstName());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrMiddelName("");
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrLastName("");
				
				debug_point = 26;
				
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrPatientAge(vo.getStrPatientAge());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrPatientAgeUnit(vo.getStrPatientAgeUnit());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrPatientFatherName(vo.getStrPatientFatherName());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrPatientGenderCode(vo.getStrPatientGenderCode());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrPatientAddress(vo.getStrPatientAddress());
				
				debug_point = 27;
				
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrTransType("2"); //Without Cr No
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrOutOfStockFlag(vo.getStrOutOfStockFlag());
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.setStrDrugIssueDate(vo.getStrDrugIssueDate());
				debug_point = 28;
				//System.out.println("Issue Daet=DAO=>"+vo.getStrDrugIssueDate());
				//System.out.println("Out Of Stoc Flag=DAO=>"+vo.getStrOutOfStockFlag());
				// By Vivek end				
				dmlHsttPatEmpIssueDtlWithoutCrNoDAO.insert(dao);  // Dml_Patemp_Issue_Dtls  Mode =1
				debug_point = 29;
				//dmlHsttPatEmpIssueDtlWithoutCrNoDAO.update(dao);
				dao.fire();
				debug_point = 30;
				
				sb.append("<tr>");
				sb.append("<td colspan='4' align='left'><hr size='2'></td>");
				sb.append("<td colspan='1' align='center'><hr size='2'></td>");					
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td colspan='4' align='left'></td>");
				sb.append("<td colspan='1' align='center'></td>");					
				sb.append("</tr>");	
				sb.append("<tr>");
				sb.append("<td colspan='4' align='left'></td>");
				sb.append("<td colspan='1' align='center'></td>");					
				sb.append("</tr>");
				sb.append("</table> ");		
				
				debug_point = 31;
				/************************************************6********************************************************************/
				sb.append("<table align='center' width='").append(strTableWidth).append("' border='0' cellspacing='0' cellpadding='0' height='69'> ");
				sb.append("<tr>");
				sb.append("<td width='10%'></td>");			
				sb.append("<td width='80%' align='center'><b><font face='Verdana, Arial, Helvetica, sans-serif' size='3'> ");
				sb.append(strIssueNo.split("\\^")[1]);
				sb.append("</font></b></td> <td width='10%'>&nbsp;</td> ");
				sb.append("</tr> ");				
				sb.append("</table>"); 
				
				debug_point = 32;
				/************************************************7********************************************************************/

				//System.out.println("IssueTransDao_Issue No::::"+vo.getStrIssueNumber());
				//System.out.println("IssueTransDao_StoreId::::"+vo.getStrStoreId());
				//System.out.println("sb.toString()==>"+sb.toString());
				vo.setStrVocherHLPString(sb.toString());
			}
		} 
		catch (Exception e) 
		{
		 	  e.printStackTrace();
			  vo.setStrMsgType("1");
			  vo.setStrMsgString(e.getMessage() + " :: DAO_DEBUG_POINT = " + debug_point);
			  vo.setStrVocherHLPString("");
		} 
		finally 
		{

			if (dao != null) {
				dao.free();
				dao = null;
			}
			
			sb = null;
			res = null;
		}
	}
	
	
	public static void getGenderCombo(IssueTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.proc_get_Gender_Combo(?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions","IssueTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", voObj.getStrHospitalCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) 
			{

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrGenderWs(ws);
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("IssueTransDAO.getGenderCombo() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	
	public static void getLFAccountDetails(IssueTransVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_HBLT_PAT_LF_ACCOUNT_DTL(?,?,?,?,?,?)}";
		int nProcIndex = 0;

		//String strDeptCode = voObj.getStrOffLineRaisingDepartment();//Last Visited Department
		String strCrNo = voObj.getStrPuk();

		String strErr = "";

		try
		{
			daoObj = new HisDAO("Billing", "LFNoTransDAO.getEpisodeList");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 1);
			//daoObj.setProcInValue(nProcIndex, "deptCode", strDeptCode, 2);
			if(strCrNo!=null && strCrNo!="")
			{daoObj.setProcInValue(nProcIndex, "puk", strCrNo, 2);	
			}
			else
			{
				daoObj.setProcInValue(nProcIndex, "puk", "0", 2);
			}
			
			if(voObj.getStrLFNo()!=null && voObj.getStrLFNo()!="")
			daoObj.setProcInValue(nProcIndex, "accno",voObj.getStrLFNo(), 3);
			else
				daoObj.setProcInValue(nProcIndex, "accno","0", 3);
			daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospitalCode(),4);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 5);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 6);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");
          
			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");		
				System.out.println("ws.size()+++for LF++++>>>>>"+ws.size());
				if(ws.next())
				{
					voObj.setStrLFAccountNo (ws.getString(1));
					voObj.setStrLFAccountOpenDate(ws.getString(2));
					voObj.setStrLFDepositedAmount(ws.getString(3));
					voObj.setStrLFBalanceAmount(ws.getString(4));
					voObj.setStrLFAccountStatus(ws.getString(5));
					voObj.setStrCrNo(ws.getString(6));
					//ws.beforeFirst();
				}
				
			//	voObj.setAdmEpisodeTreatCatDeptDtls(ws);
			} 
			else
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			voObj.setStrMsgString("LFNoTransDAO.getEpisodeList() --> " + e.getMessage());
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
	
	public static void getLFAccountSummary(IssueTransVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_lf_account_summary(?,?,?,?,?)}";
		int nProcIndex = 0;

		//String strDeptCode = voObj.getStrOffLineRaisingDepartment();//Last Visited Department
		String strCrNo = voObj.getStrPuk();

		String strErr = "";

		try
		{
			daoObj = new HisDAO("Billing", "LFNoTransDAO.getEpisodeList");
			nProcIndex = daoObj.setProcedure(strProcName);
			System.out.println("voObj.getStrLFAccountNo()  DAO:: "+voObj.getStrLFAccountNo());
			
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospitalCode(),2);
			if(voObj.getStrLFAccountNo()!=null && voObj.getStrLFAccountNo()!="")
			daoObj.setProcInValue(nProcIndex, "lfaccount_no",voObj.getStrLFAccountNo(), 3);
			else
				daoObj.setProcInValue(nProcIndex, "lfaccount_no","0", 3);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");				
				voObj.setWsLFAccountSummary(ws);
				
			//	voObj.setAdmEpisodeTreatCatDeptDtls(ws);
			} 
			else
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e)
		{	e.printStackTrace();
			voObj.setStrMsgString("LFNoTransDAO.getEpisodeList() --> " + e.getMessage());
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
	 * To get Diagnosis details of patient 
	 * 
	 * @param IssueTransVO	the IssueTransVO
	 */
	public static void getPatientDiagDetails(IssueTransVO vo)
	{
		
		WebRowSet wb = null;
		HisDAO daoObj = null;
		int nProcIndex = 0;
		String strErr = "";
		
    	try 
		{
    		String strProcName = "{call PKG_MMS_VIEW.proc_diag_emp_view(?,?::varchar,?::varchar,?,?)}";

			daoObj = new HisDAO("MMS Transactions","IssueTransDAO");
			
			nProcIndex = daoObj.setProcedure(strProcName);
     		daoObj.setProcInValue(nProcIndex, "modval", "1",1);
	     	daoObj.setProcInValue(nProcIndex, "crno",vo.getStrCrNo() ,2);
			daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),3);
						
			daoObj.setProcOutValue(nProcIndex,"err", 1,4);
			daoObj.setProcOutValue(nProcIndex,"resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				wb = daoObj.getWebRowSet(nProcIndex, "resultset");
				
				vo.setDiagEmpWs(wb);
				
			} else {
				throw new Exception(strErr);
			}
    		
		
		
		} 
    	catch (Exception e) 
    	{
    		e.printStackTrace();
			vo.setStrMsgString("--> IssueTransDAO.getPatientDiagDetails()-->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null)
				daoObj.free();
				daoObj = null;
			}

	}
	
	/**
	 * To get Diagnosis combo  
	 * 
	 * @param IssueTransVO	the IssueTransVO
	 */
	public static void getIcdList(IssueTransVO vo)
	{
		
		WebRowSet wb = null;
		HisDAO daoObj = null;
		int nProcIndex = 0;
		String strErr = "";
		
    	try 
		{
    		String strProcName = "{call PKG_MMS_VIEW.proc_diag_mst(?,?,?)}";

			daoObj = new HisDAO("MMS Transactions","IssueTransDAO");
			
			nProcIndex = daoObj.setProcedure(strProcName);
     		daoObj.setProcInValue(nProcIndex, "modval", "1",1);						
			daoObj.setProcOutValue(nProcIndex,"err", 1,2);
			daoObj.setProcOutValue(nProcIndex,"resultset", 2,3);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				wb = daoObj.getWebRowSet(nProcIndex, "resultset");
				
				vo.setDiagMstWs(wb);
				
			} else {
				throw new Exception(strErr);
			}
    		
		
		
		} 
    	catch (Exception e) 
    	{
			vo.setStrMsgString("--> IssueTransDAO.getPatientDiagDetails()-->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null)
				daoObj.free();
				daoObj = null;
			}

	}
	
	/**
	 * To get doctor details 
	 * 
	 * @param IssueTransVO	the IssueTransVO
	 */
	public static void getEmpList(IssueTransVO vo)
	{
		
		WebRowSet wb = null;
		HisDAO daoObj = null;
		int nProcIndex = 0;
		String strErr = "";
		
    	try 
		{
    		String strProcName = "{call PKG_IPD_VIEW.proc_unit_consulatant_view(?,?,?,?,?,?)}";

			daoObj = new HisDAO("MMS Transactions","IssueTransDAO");
			
			nProcIndex = daoObj.setProcedure(strProcName);
     		daoObj.setProcInValue(nProcIndex, "modval", "2",1);
     		daoObj.setProcInValue(nProcIndex, "deptunitcode", "0",2);	
     		daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),3);
     		daoObj.setProcInValue(nProcIndex, "seatid", "0",4);
			daoObj.setProcOutValue(nProcIndex,"err", 1,5);
			daoObj.setProcOutValue(nProcIndex,"resultset", 2,6);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				wb = daoObj.getWebRowSet(nProcIndex, "resultset");
				
				vo.setEmpWs(wb);
				
			} else {
				throw new Exception(strErr);
			}
    		
		
		
		} 
    	catch (Exception e) 
    	{
			vo.setStrMsgString("--> IssueTransDAO.getPatientDiagDetails()-->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null)
				daoObj.free();
				daoObj = null;
			}

	}
	
	
	public static void getBilledItemDtls(IssueTransVO vo) 
	{

		HisDAO dao = null;
		WebRowSet ws = null;

		String proc_name1 = "{call Pkg_Mms_View.proc_patient_issue_drug_dtl(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try 
		{
			    dao = new HisDAO("MMS",	"OfflineIssueIndentTransDAO.getIssueDetailTwo(OfflineIssueIndentTransVO vo)");
			    nProcIndex = dao.setProcedure(proc_name1);
				// set value
				dao.setProcInValue(nProcIndex, "modeval", "2",1);                  //1
				dao.setProcInValue(nProcIndex, "storeid",  vo.getStrStoreId(),2);//2
				dao.setProcInValue(nProcIndex, "itemCatg", vo.getStrIssueNo(),3);//3   item category has been used as issue no here
				dao.setProcInValue(nProcIndex, "from_date","",4);//4
				dao.setProcInValue(nProcIndex, "too_date", "",5);//5
				dao.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),6); //6
				dao.setProcInValue(nProcIndex, "crno",vo.getStrCrNo(),7); //6
				dao.setProcOutValue(nProcIndex,"err", 1,8); // 1 for string return //7
				dao.setProcOutValue(nProcIndex, "resultset", 2,9);//8
				dao.executeProcedureByPosition(nProcIndex);
				
			    strErr = dao.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = dao.getWebRowSet(nProcIndex, "resultset");
						
				vo.setStrBilledItemDetailWs(ws);
			
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			//e.printStackTrace();
			vo
					.setStrMsgString("IssueTransDAO.getIssueDetailTwo() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	
	public static void save(IssueTransVO vo) 
	{

		HisDAO dao = null;
		WebRowSet ws = null;

		String proc_name1 = "{call Pkg_Mms_dml.dml_issue_billed_item(?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try 
		{
			    dao = new HisDAO("MMS",	"OfflineIssueIndentTransDAO.getIssueDetailTwo(OfflineIssueIndentTransVO vo)");
			    nProcIndex = dao.setProcedure(proc_name1);
				// set value
				dao.setProcInValue(nProcIndex, "modeval", "1",1);                  //1
				dao.setProcInValue(nProcIndex, "storeid",  vo.getStrStoreId(),4);//2
				dao.setProcInValue(nProcIndex, "issueNo", vo.getStrIssueNo(),3);//3   
				dao.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),2); //6
				dao.setProcOutValue(nProcIndex,"err", 1,5); // 1 for string return //7
				dao.executeProcedureByPosition(nProcIndex);
				
			    strErr = dao.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {
			
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			//e.printStackTrace();
			vo
					.setStrMsgString("IssueTransDAO.getIssueDetailTwo() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	
	public static void deleteIssueDtls(IssueTransVO vo) 
	{

		HisDAO dao = null;
		WebRowSet ws = null;

		String proc_name1 = "{call Pkg_Mms_dml.proc_patient_issue_delete(?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try 
		{
			    dao = new HisDAO("MMS",	"OfflineIssueIndentTransDAO.getIssueDetailTwo(OfflineIssueIndentTransVO vo)");
			    nProcIndex = dao.setProcedure(proc_name1);
				// set value
				dao.setProcInValue(nProcIndex, "modeval", "1",1);                  //1
				dao.setProcInValue(nProcIndex, "storeid",  vo.getStrStoreId(),4);//2
				dao.setProcInValue(nProcIndex, "issueNo", vo.getStrIssueNo(),3);//3   item category has been used as issue no here
				dao.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),2); //6
				dao.setProcOutValue(nProcIndex,"err", 1,5); // 1 for string return //7
				dao.executeProcedureByPosition(nProcIndex);
				
				 strErr = dao.getString(nProcIndex, "err");

					if (strErr == null)
						strErr = "";

					if (strErr.equals("")) {
					
									
					} else {
						throw new Exception(strErr);
					}

		} catch (Exception e) {
			//e.printStackTrace();
			vo
					.setStrMsgString("IssueTransDAO.getIssueDetailTwo() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	
	public static void getTariffDtls(IssueTransVO vo) 
	{

		HisDAO dao = null;
		WebRowSet ws = null;

		String proc_name1 = "{call Pkg_Mms_View.proc_tariff_list(?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try 
		{
			    dao = new HisDAO("MMS",	"OfflineIssueIndentTransDAO.getIssueDetailTwo(OfflineIssueIndentTransVO vo)");
			    nProcIndex = dao.setProcedure(proc_name1);
				// set value
				dao.setProcInValue(nProcIndex, "modeval", "1",1);                  //1
				dao.setProcInValue(nProcIndex, "tariffid", vo.getTariff() ,2);//2
				dao.setProcOutValue(nProcIndex,"err", 1,3); // 1 for string return //7
				dao.setProcOutValue(nProcIndex, "resultset", 2,4);//8
				dao.executeProcedureByPosition(nProcIndex);
				
			    strErr = dao.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = dao.getWebRowSet(nProcIndex, "resultset");
						
				vo.setStrTariffDtl(ws);
			
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("IssueTransDAO.getTariffDtls() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
}
