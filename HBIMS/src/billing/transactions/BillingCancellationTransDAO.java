package billing.transactions;

import javax.sql.rowset.WebRowSet;

import billing.dao.*;
import hisglobal.transactionmgnt.HisDAO;

/**
 * @author Anshul Jindal
 * 
 */
public class BillingCancellationTransDAO {

	/** This method calls the procedure to find BILL DETAILS according to CR No. 
	 * in case 1 with mode value =3 by passing the argument named crno
	 * and according to Guarantor Name in case 2 with mode value =3 by passing the 
	 * argument named searchStr  
	 * @param vo
	 */
	public static void getBillDetailsProc(BillingCancellationTransVO vo)
	{
		String strCrNo = null;
		String strGuarantorName = null;
		WebRowSet wb = null;
		String PROC_NAME = "";
		HisDAO dao = null;
		int procIndex = 0;
		String strErr = "";
		String modeValue = "";

		try {
			PROC_NAME = "{call pkg_bill_view.proc_SBLT_OUTBOUND_DTL(?,?,?,?,?,?,?,?,?,?,?,?)}";
			dao = new HisDAO("billing",
					"BillingCancellationTransDAO.getBillDetailsProc");
			procIndex = dao.setProcedure(PROC_NAME);

			if (vo.getStrCase().equals("1")) 
			{
				strCrNo = vo.getStrCrNo();
				modeValue = "3";
				dao.setProcInValue(procIndex, "crno", strCrNo,1);
				dao.setProcInValue(procIndex, "searchStr", "",5);
			}

			if (vo.getStrCase().equals("2")) 
			{
				modeValue = "3";
				strGuarantorName = vo.getStrGuarantorName();
				dao.setProcInValue(procIndex, "crno", "",1);
				dao.setProcInValue(procIndex, "searchStr", strGuarantorName,5);
			}

			dao.setProcInValue(procIndex, "modeval", modeValue,9);
			dao.setProcInValue(procIndex,"hosp_code",vo.getStrHospitalCode(),10);  // New Value
		
			dao.setProcInValue(procIndex, "to_be_refund_pkg", "",2);
			dao.setProcInValue(procIndex, "chargeTypeId", "",3);
			dao.setProcInValue(procIndex, "patListType", "",4);
			dao.setProcInValue(procIndex, "searchType", "",6);
			dao.setProcInValue(procIndex, "frmRows", "",7);
			dao.setProcInValue(procIndex, "toRows", "",8);
			
			dao.setProcOutValue(procIndex, "err", 1,11);
			dao.setProcOutValue(procIndex, "resultset", 2,12);

			dao.executeProcedureByPosition(procIndex);

			strErr = dao.getString(procIndex, "err");

			if (strErr == null)
				strErr = "";
			wb = dao.getWebRowSet(procIndex, "resultset");

			if (strErr.equals("")) 
			{

				vo.setStrBillDtlWs(wb);

			} 
			else 
			{
				throw new Exception(strErr);
			}

		} 
		catch (Exception e)
		{
			 vo.setStrMsgString("BillingCancellationTransDAO.getBillDetailsProc() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}
	
	public static void getCrBillDetailsProc(BillingCancellationTransVO vo)
	{
		String strCrNo = null;
		String strGuarantorName = null;
		WebRowSet wb = null;
		String PROC_NAME = "";
		HisDAO dao = null;
		int procIndex = 0;
		String strErr = "";
		String modeValue = "";

		try {
			PROC_NAME = "{call pkg_bill_view.proc_SBLT_OUTBOUND_DTL(?,?,?,?,?,?,?,?,?,?,?,?)}";
			dao = new HisDAO("billing",
					"BillingCancellationTransDAO.getBillDetailsProc");
			procIndex = dao.setProcedure(PROC_NAME);

			if (vo.getStrCase().equals("1")) 
			{
				strCrNo = vo.getStrCrNo();
				modeValue = "8";
				dao.setProcInValue(procIndex, "crno", strCrNo,1);
				dao.setProcInValue(procIndex, "searchStr", "",5);
			}

			if (vo.getStrCase().equals("2")) 
			{
				modeValue = "3";
				strGuarantorName = vo.getStrGuarantorName();
				dao.setProcInValue(procIndex, "crno", "",1);
				dao.setProcInValue(procIndex, "searchStr", strGuarantorName,5);
			}

			dao.setProcInValue(procIndex, "modeval", modeValue,9);
			dao.setProcInValue(procIndex,"hosp_code",vo.getStrHospitalCode(),10);  // New Value
		
			dao.setProcInValue(procIndex, "to_be_refund_pkg", "",2);
			dao.setProcInValue(procIndex, "chargeTypeId", "",3);
			dao.setProcInValue(procIndex, "patListType", "",4);
			dao.setProcInValue(procIndex, "searchType", "",6);
			dao.setProcInValue(procIndex, "frmRows", "",7);
			dao.setProcInValue(procIndex, "toRows", "",8);
			
			dao.setProcOutValue(procIndex, "err", 1,11);
			dao.setProcOutValue(procIndex, "resultset", 2,12);

			dao.executeProcedureByPosition(procIndex);

			strErr = dao.getString(procIndex, "err");

			if (strErr == null)
				strErr = "";
			wb = dao.getWebRowSet(procIndex, "resultset");

			if (strErr.equals("")) 
			{

				vo.setStrBillDtlWs(wb);

			} 
			else 
			{
				throw new Exception(strErr);
			}

		} 
		catch (Exception e)
		{
			e.printStackTrace();
			 vo.setStrMsgString("BillingCancellationTransDAO.getCrBillDetailsProc() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * This method calls a procedure to find Consultant codes and Names used to
	 * generate Canceled By combo. Set the WebRowSet in vo which can be access
	 * in BO to generate combo options
	 * 
	 * @param vo
	 */
	public static void getCancelledByCmbProc(BillingCancellationTransVO vo) 
	{
		WebRowSet wb = null;
		String PROC_NAME = "";
		PROC_NAME = "{call pkg_simple_view.Proc_user_list(?,?,?,?,?)}";
		HisDAO dao = null;
		int procIndex = 0;
		String strErr = "";
		try 
		{
			dao = new HisDAO("billing","BillingCancellationTransDAO.getCancelledByCmbProc");
			procIndex = dao.setProcedure(PROC_NAME);
			
			dao.setProcInValue(procIndex,"hosp_code",vo.getStrHospitalCode(),2);  // New Value
			dao.setProcInValue(procIndex,"seatId",vo.getStrSeatId(),3);
			dao.setProcInValue(procIndex,"processId","3",1);
			dao.setProcOutValue(procIndex, "err", 1,4);
			dao.setProcOutValue(procIndex, "resultset", 2,5);
			dao.executeProcedureByPosition(procIndex);
			strErr = dao.getString(procIndex, "err");
			if (strErr == null) 
			{
				strErr = "";
			}
			wb = dao.getWebRowSet(procIndex, "resultset");

			if (strErr.equals("")) 
			{
				vo.setStrCancelledByWs(wb);

			}
			else 
			{
				throw new Exception(strErr);
			}

		} 
		catch (Exception e) 
		{
			vo.setStrMsgString("BillingCancellationTransDAO.getCancelledByCmbProc() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		}

		finally 
		{
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * This method calls a procedure to find Remarks used to generate Cancel
	 * Reason Combo. Set the WebRowSet in vo which can be access in BO to 
	 * generate combo options
	 * @param vo
	 */

	public static void getCancelReasonProc(BillingCancellationTransVO vo) {

		WebRowSet wb = null;

		String PROC_NAME = "";
		PROC_NAME = "{call pkg_simple_view.proc_hblt_remarks_mst(?,?,?,?,?)}";

		HisDAO dao = null;

		int procIndex = 0;

		String strErr = "";

		try {
			dao = new HisDAO("billing",
					"BillingCancellationTransDAO.getCancelReasonProc");
			procIndex = dao.setProcedure(PROC_NAME);

			dao.setProcInValue(procIndex, "rmkstype", "2",1);
			dao.setProcInValue(procIndex, "modeval", "1",2);
			dao.setProcInValue(procIndex,"hosp_code",vo.getStrHospitalCode(),3);  // New Value
			dao.setProcOutValue(procIndex, "err", 1,4);
			dao.setProcOutValue(procIndex, "resultset", 2,5);

			dao.executeProcedureByPosition(procIndex);

			strErr = dao.getString(procIndex, "err");

			if (strErr == null)
				strErr = "";
			wb = dao.getWebRowSet(procIndex, "resultset");

			if (strErr.equals("")) {
				vo.setStrCancelReasonWs(wb);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			vo
					.setStrMsgString("BillingCancellationTransDAO.getCancelReasonProc() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/** This method calls when we click on a Bill No. hyperlink to access 
	 * the tariff details used to generate a pop up in HLP
	 * @param vo
	 */
	public static void getPopUpInfoProc(BillingCancellationTransVO vo) {

		WebRowSet wb = null;
		HisDAO dao = null;
		String PROC_NAME = "";
		int procIndex = 0;
		String strErr = "";
		String strPatAccNo = "";

		try {
			dao = new HisDAO("billing","BillingCancellationTransDAO.getPopUpInfoProc");

			strPatAccNo = vo.getStrPatAccNo();

			if (strPatAccNo.equals("0")) 
			{
				
				PROC_NAME = "{call pkg_bill_view.proc_HBLT_BILL_SERVICE_DTL(?,?,?,?,?,?,?,?)}";
				procIndex = dao.setProcedure(PROC_NAME);

				dao.setProcInValue(procIndex, "billNo", vo.getStrBillNo(),1);
				dao.setProcInValue(procIndex, "modeval", "3",3);
				dao.setProcInValue(procIndex, "recNo", vo.getStrReceiptNo(),5);
				dao.setProcInValue(procIndex, "recType", vo.getStrReceiptType(),6);
				dao.setProcInValue(procIndex, "to_be_refund_pkg", "",2);
				// below lines added by nitin
				dao.setProcInValue(procIndex,"hosp_code",vo.getStrHospitalCode(),4);  // New Value
				dao.setProcOutValue(procIndex, "err", 1,7);
				dao.setProcOutValue(procIndex, "resultset", 2,8);
			
				
			}
			else 
			{
			
				PROC_NAME = "{call pkg_bill_view.Proc_Hblt_Pataccount_Service(?,?,?,?,?,?,?,?,?,?)}";
				procIndex = dao.setProcedure(PROC_NAME);

				dao.setProcInValue(procIndex, "billNo", vo.getStrBillNo(),1);
				dao.setProcInValue(procIndex, "accNo", strPatAccNo,2);
				dao.setProcInValue(procIndex, "modeval", "6",5);
				dao.setProcInValue(procIndex, "recNo", vo.getStrReceiptNo(),7);
				dao.setProcInValue(procIndex, "recType", vo.getStrReceiptType(),8);
				dao.setProcInValue(procIndex, "to_be_refund_pkg", "",3);
				dao.setProcInValue(procIndex, "groupId", "",4);
				dao.setProcInValue(procIndex,"hosp_code",vo.getStrHospitalCode(),6);  // New Value
				dao.setProcOutValue(procIndex, "err", 1,9);
				dao.setProcOutValue(procIndex, "resultset", 2,10);
			}
			/*--commented by Nitin
			dao.setProcInValue(procIndex,"hosp_code",vo.getStrHospitalCode());  // New Value
			dao.setProcOutValue(procIndex, "err", 1);
			dao.setProcOutValue(procIndex, "resultset", 2);*/

			dao.executeProcedureByPosition(procIndex);

			strErr = dao.getString(procIndex, "err");

			if (strErr == null) {
				strErr = "";
			}
			wb = dao.getWebRowSet(procIndex, "resultset");

			if (strErr.equals("")) {
				vo.setStrPopUpWs(wb);

			} 
			else
			{
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			vo
					.setStrMsgString("BillingCancellationTransDAO.getPopUpInfoProc() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}

		finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/** This method calls on SAVE button to cancel Bill Details and insert the data 
	 *   into database.
	 * In CRIRETIA 1 -- user can select multiple bills to cancel so procedure will be
	 *  called in a loop according to the no. of selected bills.
	 *  In this case Trans No. & Receipt No. will come from check box values 
	 * In CRITERIA 2 -- user will enter a single Bill No. to cancel so procedure will 
	 *  be called once .
	 *  In this case Trans No. & Receipt No. will come from text box value
	 *  (what the user will enter)
	 * In both cases to generate a CANCEL No. we call some methods of PrimaryKeyDAO
	 * class and to rollback generated CANCEL No.s (in case of any failure) we call 
	 * some method of PrimaryKeyLogDAO class.This will write the error in log file.
	 * @param vo
	 */
	public static void insertDataProc(BillingCancellationTransVO vo) 
	{
		HisDAO dao = null;
		PrimaryKeyDAO keyDAO = null;
		PrimaryKeyLogDAO keyLogDAO = null;
		
		String PROC_NAME = "";
		int procIndex = 0;
		String strErr = "";
		
		 
		String temp[] = new String[9];
		String temp1[] = new String[20];

		String strTransNo = "";
		String strReceiptNo = "";
		String strCancelNo = "";
		try 
		{
			
			dao = new HisDAO("billing","BillingCancellationTransDAO.insertDataProc");
			keyDAO = new PrimaryKeyDAO();
			keyLogDAO = new PrimaryKeyLogDAO();

			 
			
            if(vo.getStrCriteria().equals("2") || (vo.getChk()!=null))//|| vo.getBillcancelflag().equals("1"))
            	PROC_NAME = "{call Pkg_Bill_Dml.DML_ONLINE_BILL_CANCELLATION(?,?,?,?,?,?,?,?,?)}";
            else
            	PROC_NAME = "{call Pkg_Bill_Dml.dml_credit_bill_service_cancel(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)}";
			procIndex = dao.setProcedure(PROC_NAME);
            
	   if(vo.getStrCriteria().equals("1"))
	   {
		 if(vo.getChk()!=null)  
		 {
			  for (int i = 0; i < vo.getChk().length; i++) 
			  {
					temp = vo.getChk()[i].replace("^", "#").split("#");
					strTransNo = temp[0];
					strReceiptNo = temp[8];
					keyDAO.setStrKeyname("CANCELNO");
					keyDAO.setStrBlockkey("1");
					keyDAO.setStrHospCode(vo.getStrHospitalCode());
					keyDAO.select(dao);
					strCancelNo = keyDAO.getStrPrimrayKeyValue();
				/*	
					System.out.println("in dao hoscode--"+vo.getStrHospitalCode()); 
					System.out.println("in dao cancel no--"+strCancelNo);
					System.out.println("in dao trans no--"+strTransNo); 
					System.out.println("in dao receipt no--"+strReceiptNo); 
					System.out.println("in dao cancelled  by--"+vo.getStrCancelledBy());
					System.out.println("in dao cancel reason--"+vo.getStrCancelReason());
					System.out.println("in dao seat id--"+vo.getStrSeatId());
					*/ 
					dao.setProcInValue(procIndex, "hosp_code", vo.getStrHospitalCode(),1);
					dao.setProcInValue(procIndex, "cancelNo", strCancelNo,2);
					dao.setProcInValue(procIndex, "billNo", strTransNo,3);
					dao.setProcInValue(procIndex, "receiptNo", strReceiptNo,4);
					dao.setProcInValue(procIndex, "empNo", vo.getStrCancelledBy(),5);
					dao.setProcInValue(procIndex, "cancelReason", vo.getStrCancelReason(),6);
					dao.setProcInValue(procIndex, "seatId", vo.getStrSeatId(),7);
					dao.setProcInValue(procIndex, "ipAddr", vo.getStrIpAddress(),8);
					dao.setProcOutValue(procIndex, "err", 1,9);
	
					dao.execute(procIndex, 1);
					
				 }
		 }
		 else
		 {
			 /*if(vo.getBillcancelflag().equals("1"))
			 {
				 for (int i = 0; i < vo.getCrchk().length; i++) 
				  {
						temp = vo.getCrchk()[i].replace("^", "#").split("#");
						strTransNo = temp[0];
						strReceiptNo = temp[8];
						keyDAO.setStrKeyname("CANCELNO");
						keyDAO.setStrBlockkey("1");
						keyDAO.setStrHospCode(vo.getStrHospitalCode());
						keyDAO.select(dao);
						strCancelNo = keyDAO.getStrPrimrayKeyValue();*/
					/*	
						System.out.println("in dao hoscode--"+vo.getStrHospitalCode()); 
						System.out.println("in dao cancel no--"+strCancelNo);
						System.out.println("in dao trans no--"+strTransNo); 
						System.out.println("in dao receipt no--"+strReceiptNo); 
						System.out.println("in dao cancelled  by--"+vo.getStrCancelledBy());
						System.out.println("in dao cancel reason--"+vo.getStrCancelReason());
						System.out.println("in dao seat id--"+vo.getStrSeatId());
						*/ 
						/*dao.setProcInValue(procIndex, "hosp_code", vo.getStrHospitalCode(),1);
						dao.setProcInValue(procIndex, "cancelNo", strCancelNo,2);
						dao.setProcInValue(procIndex, "billNo", strTransNo,3);
						dao.setProcInValue(procIndex, "receiptNo", strReceiptNo,4);
						dao.setProcInValue(procIndex, "empNo", vo.getStrCancelledBy(),5);
						dao.setProcInValue(procIndex, "cancelReason", vo.getStrCancelReason(),6);
						dao.setProcInValue(procIndex, "seatId", vo.getStrSeatId(),7);
						dao.setProcInValue(procIndex, "ipAddr", vo.getStrIpAddress(),8);
						dao.setProcOutValue(procIndex, "err", 1,9);
		
						dao.execute(procIndex, 1);
						
					 }
			 }
			 else
			 {*/
				 for (int i = 0; i < vo.getCehk().length; i++) 
				  {
					    procIndex = dao.setProcedure("{call Pkg_Bill_Dml.dml_credit_bill_service_cancel(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)}");
					    for (int j = 0; j < vo.getCrchk().length; j++) 
					    {
								temp = vo.getCrchk()[j].replace("^", "#").split("#");
								strTransNo = temp[0];
								strReceiptNo = temp[8];
					    }
					    temp1 = vo.getCehk()[i].replace("^", "#").split("#");
					    String grpid=temp1[5];
					    String trfid=temp1[0];
					    String refqty=temp1[1];
					    String refqtyunitid=temp1[2];
					    String rate=temp1[3];
					    String rateunitid=temp1[4];
					    Double net=Double.parseDouble(rate)*Double.parseDouble(refqty);
					    String netamt = String.valueOf(net);
					    String ispackage=temp1[14];
						keyDAO.setStrKeyname("CANCELNO");
						keyDAO.setStrBlockkey("1");
						keyDAO.setStrHospCode(vo.getStrHospitalCode());
						keyDAO.select(dao);
						strCancelNo = keyDAO.getStrPrimrayKeyValue();
					/*	
						System.out.println("in dao hoscode--"+vo.getStrHospitalCode()); 
						System.out.println("in dao cancel no--"+strCancelNo);
						System.out.println("in dao trans no--"+strTransNo); 
						System.out.println("in dao receipt no--"+strReceiptNo); 
						System.out.println("in dao cancelled  by--"+vo.getStrCancelledBy());
						System.out.println("in dao cancel reason--"+vo.getStrCancelReason());
						System.out.println("in dao seat id--"+vo.getStrSeatId());
						*/ 
						dao.setProcInValue(procIndex, "modval", "1",1);
						dao.setProcInValue(procIndex, "hosp_code", vo.getStrHospitalCode(),2);
						dao.setProcInValue(procIndex, "cancelNo", strCancelNo,3);
						dao.setProcInValue(procIndex, "billNo", strTransNo,4);
						dao.setProcInValue(procIndex, "trfrecno", strReceiptNo,5);
						dao.setProcInValue(procIndex, "pataccno", "",6);
						dao.setProcInValue(procIndex, "puk", vo.getStrCrNo(),7);
						dao.setProcInValue(procIndex, "grpid", grpid ,8);
						dao.setProcInValue(procIndex, "trfid", trfid ,9);
						dao.setProcInValue(procIndex, "refqty", refqty , 10);
						dao.setProcInValue(procIndex, "refqtyunitid", refqtyunitid , 11);
						dao.setProcInValue(procIndex, "rate", rate , 12);
						dao.setProcInValue(procIndex, "rateunitid", rateunitid , 13);
						dao.setProcInValue(procIndex, "netamt", netamt , 14);
						dao.setProcInValue(procIndex, "appid", vo.getStrCancelledBy(),15);
						//dao.setProcInValue(procIndex, "cancelReason", vo.getStrCancelReason(),6);
						dao.setProcInValue(procIndex, "ispackage", ispackage , 16);
						dao.setProcInValue(procIndex, "seatId", vo.getStrSeatId(),17);
						dao.setProcInValue(procIndex, "clno", vo.getClNo()[i] , 18);
						dao.setProcInValue(procIndex, "cldate", vo.getClDate()[i] ,19);
						dao.setProcOutValue(procIndex, "err", 1,20);
						dao.setProcInValue(procIndex, "billcancelflag", vo.getBillcancelflag() ,21);
						dao.setProcInValue(procIndex, "cancelReason", vo.getStrCancelReason(),22);
						dao.setProcInValue(procIndex, "ipAddr", vo.getStrIpAddress(),23);
						dao.setProcInValue(procIndex, "is_service", vo.getStrService(),24);
		
						dao.execute(procIndex, 1);
						
					 }
			 
		 }
			synchronized (dao) 
			{
				dao.fire();
			}
	     
		}
	    if(vo.getStrCriteria().equals("2"))
	    {
	    	//System.out.println("dao insertData criteria 2");
			strTransNo = vo.getStrTransNo();
			strReceiptNo = vo.getStrReceiptNo();

			keyDAO.setStrKeyname("CANCELNO");
			keyDAO.setStrBlockkey("1");
			keyDAO.setStrHospCode(vo.getStrHospitalCode());
			keyDAO.select(dao);
			strCancelNo = keyDAO.getStrPrimrayKeyValue();
			
		/*	System.out.println("in dao hoscode--"+vo.getStrHospitalCode()); 
			System.out.println("in dao cancel no--"+strCancelNo);
			System.out.println("in dao trans no--"+strTransNo); 
			System.out.println("in dao receipt no--"+strReceiptNo); 
			System.out.println("in dao cancelled  by--"+vo.getStrCancelledBy());
			System.out.println("in dao cancel reason--"+vo.getStrCancelReason());
			System.out.println("in dao seat id--"+vo.getStrSeatId());*/
			 
			dao.setProcInValue(procIndex, "hosp_code", vo.getStrHospitalCode(),1);
			dao.setProcInValue(procIndex, "cancelNo", strCancelNo,2);
			dao.setProcInValue(procIndex, "billNo", strTransNo,3);
			dao.setProcInValue(procIndex, "receiptNo", strReceiptNo,4);
			dao.setProcInValue(procIndex, "empNo", vo.getStrCancelledBy(),5);
			dao.setProcInValue(procIndex, "cancelReason", vo.getStrCancelReason(),6);
			dao.setProcInValue(procIndex, "seatId", vo.getStrSeatId(),7);
			dao.setProcInValue(procIndex, "ipAddr", vo.getStrIpAddress(),8);
			
			
			dao.setProcOutValue(procIndex, "err", 1,9);

			dao.executeProcedureByPosition(procIndex);
			
		
	   }
			strErr = dao.getString(procIndex, "err");
			//System.out.println("dao insertData strErr before catch");
			//System.out.println("strErr="+strErr);
			
			if (strErr == null) 
			{
				strErr = "";
			}
			if (strErr.equals("")) 
			{
				vo.setStrMsg("Selected Bill has been cancelled successfully");
			}
			
			else 
			{
				throw new Exception(strErr);
			}
		}
		catch (Exception e) 
		{
			
			
			//System.out.println("e.getMessage()-"+e.getMessage());
			String arr[] =e.getMessage().split("#"); // for invalid Bill No.
			if(arr.length>1)
			{
				vo.setStrMsgType("3");
				vo.setStrMsgString(arr[1]);
				
			}
			else
			{
			vo.setStrPrimaryKeyMsg(e.getMessage());
			try
			{
				keyLogDAO.setStrBlockkey("1");
				keyLogDAO.setStrError(vo.getStrPrimaryKeyMsg());
				keyLogDAO.setStrKeyname("CANCELNO");
				keyLogDAO.setStrStartkey(strCancelNo);
				keyLogDAO.setStrHospCode(vo.getStrHospitalCode());
				keyLogDAO.setStrSeatid(vo.getStrSeatId());
				keyLogDAO.insert(dao);
			}
			catch (Exception e1) 
			{
			  vo.setStrMsgString("BillingCancellationTransDAO.insertDataProc() --> "+ e1.getMessage());
			  vo.setStrMsgType("1");
			}
		 vo.setStrMsgString("BillingCancellationTransDAO.insertDataProc() --> "+ e.getMessage());
		 vo.setStrMsgType("1");
			}
		}
		finally {

			if (dao != null)
			{
				dao.free();
				dao = null;
			}
			
				keyDAO = null;
			    keyLogDAO = null;
			
		}

	}
	public static void getTariffDetails_NoAcc(BillingCancellationTransVO vo) throws Exception
	{
		WebRowSet ws = null;
		String strTemp[] = vo.getStrChkValues().replace('^', '#').split("#");
		String proc_name = "";
		proc_name = "{call PKG_BILL_VIEW.PROC_HBLT_BILL_SERVICE_DTL(?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		try
		{
			dao = new HisDAO("billing", "transactions.BillingCancellationTransDAO .getTariffDetails_NoAcc()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "billNo ", strTemp[0], 1);
			dao.setProcInValue(nprocIndex, "modeval", "4", 3);
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "", 2);
			dao.setProcInValue(nprocIndex, "recNo", "", 5);
			dao.setProcInValue(nprocIndex, "recType", "", 6);
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(), 4); 
			dao.setProcOutValue(nprocIndex, "ERR", 1, 7);
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 8);
			dao.executeProcedureByPosition(nprocIndex);
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
			vo.setStrTrfDtls(ws);
			if (ws != null)
			{
			}
		} 
		catch (Exception e)
		{
			vo.setStrMsgString("BillingCancellationTransDAO.getTariffDetails_NoAcc() --> " + e.getMessage());
			vo.setStrMsgType("1");
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
	public static void getTariffDetails_Acc(BillingCancellationTransVO vo)
	{
		WebRowSet ws = null;
		String valMode = vo.getStrChkValues();

		String strTemp[] = valMode.replace('^', '#').split("#");

		String proc_name = "";
		proc_name = "{call PKG_BILL_VIEW.PROC_HBLT_PATACCOUNT_SERVICE(?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		try
		{

			dao = new HisDAO("billing", "transactions.BillingCancellationTransDAO .getTariffDetails_Acc()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "billNo ", strTemp[0], 1);
			dao.setProcInValue(nprocIndex, "accNo ", strTemp[4], 2);
			dao.setProcInValue(nprocIndex, "modeval", "12", 5);
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "", 3);
			dao.setProcInValue(nprocIndex, "recNo", "", 7);
			dao.setProcInValue(nprocIndex, "recType", "", 8);
			dao.setProcInValue(nprocIndex, "groupId", "", 4);
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(), 6); // New Value
			dao.setProcOutValue(nprocIndex, "ERR", 1, 9); // 1 for string return
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 10); // 2 for object
			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");

			if (ws != null)
			{
				vo.setStrTrfDtls(ws);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("BillingCancellationTransDAO.getTariffDetails_Acc() --> " + e.getMessage());
			vo.setStrMsgType("1");
		} finally
		{
			if (dao != null)
			{
				dao.free();
				dao = null;
			}
		}

	}
}
