package billing.transactions;
import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

public class HbltBillReqDtlDAO
{
	public boolean insert(HbltBillReqDtlVO hbvo,HisDAO dao)  
	{

		boolean retVal = true;
		
		String proc_name1 = "";
	//	System.out.println("<------insertHbltBillReqDtl----4--->");

		proc_name1 = "{call PKG_BILL_DML.PROC_HBLT_BILLREQ_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		int procIndex1 = 0;

	//	String strerr = "";

	//	WebRowSet ws = null;

		try {
			
			String strTemp[] = hbvo.getStrChk().replace('^', '#').split("#");
			hbvo.setStrChargeTypeId(strTemp[4]);
			hbvo.setStrPatCatCode(strTemp[3]);
			hbvo.setStrPukNo(strTemp[1]);
			hbvo.setStrAdmNo(strTemp[5]);
			hbvo.setStrPatAcctNo(strTemp[0]);
        //    System.out.println("Seat IDinsertHbltBillReqDtl----4--->"+hbvo.getStrSeatId());
         //   System.out.println("Req No insertHbltBillReqDtl----4--->"+hbvo.getStrReqNo());
			
			procIndex1 = dao.setProcedure(proc_name1);

			// set value

			dao.setProcInValue(procIndex1, "modval","1");	 
			
            dao.setProcInValue(procIndex1, "hblnum_req_no",hbvo.getStrReqNo());
			
			dao.setProcInValue(procIndex1, "gnum_dept_code ",hbvo.getStrDeptCode());
			
			dao.setProcInValue(procIndex1, "sblnum_chargetype_id",strTemp[4]);
			
			dao.setProcInValue(procIndex1, "sblnum_bservice_id","20");
			
			dao.setProcInValue(procIndex1, "gnum_patient_cat_code",strTemp[3]);
			
			dao.setProcInValue(procIndex1, "hrgnum_puk",strTemp[1]);
			
			dao.setProcInValue(procIndex1, "hastr_adm_no",strTemp[5]);

			dao.setProcInValue(procIndex1, "hblnum_pataccount_no ",strTemp[0]);
			
			dao.setProcInValue(procIndex1, "hblstr_remarks","");
						
			dao.setProcInValue(procIndex1, "hblnum_status","1");
			
			dao.setProcInValue(procIndex1, "gnum_seatid",hbvo.getStrSeatId());
			
			dao.setProcInValue(procIndex1, "gnum_isvalid","1");
			
            dao.setProcInValue(procIndex1, "hblstr_approved_by ","");
			
			dao.setProcInValue(procIndex1, "hbldt_approved_date","");
		
			dao.setProcInValue(procIndex1, "hblnum_req_type","1");

			dao.setProcOutValue(procIndex1, "ERR",1); // 1 for string return

			// execute procedure

			dao.execute(procIndex1,1);

			// get value

			
		} catch (Exception e) 
		{
			new HisException("","","IpdBillManagementTransDAO.insertHbltBillReqDtl() --> "
					+ e.getMessage());

			hbvo.setStrMsg("HbltBillReqDtlDAO.insert() --> "
							+ e.getMessage());
			hbvo.setStrMsgType("1");
			retVal = false;

		}
		 return retVal;
		

	}
	public boolean select(SbltInBoundDtlVO vo,HisDAO dao) 
	{
		boolean retVal = false; 
		String proc_name1 = "";

		proc_name1 = "{call PKG_BILL_DML.PROC_SBLT_INBOUND_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		
		int procIndex1 = 0;

		String strerr = "";

	//	WebRowSet ws = null;

		try {

			procIndex1 = dao.setProcedure(proc_name1);

			// set value
			
		//	dao.setProcInValue(procIndex1, "hblnum_req_no","");
			
			dao.setProcOutValue(procIndex1, "ERR",1); // 1 for string return
														// value
	

			// execute procedure

			dao.execute(procIndex1,1);
            retVal = true;
			// get value

			strerr = dao.getString(procIndex1, "ERR");

			if (strerr == null)
			{	
				strerr = "";
			}
			 else
			 {
				throw new Exception(strerr);
			}

		}
		catch (Exception e) 
		{

	//		vo.setStrMsgString("IpdBillManagementTransDAO.getAccountDtlWithAcctNo() --> "
	//						+ e.getMessage());
    //		vo.setStrMsgType("1");
			retVal = false;

		}

		return retVal;	
	}
	public boolean update(SbltInBoundDtlVO vo,HisDAO dao) 
	{
		boolean retVal = false; 
		String proc_name1 = "";

		proc_name1 = "";

		
		int procIndex1 = 0;

		String strerr = "";

	//	WebRowSet ws = null;

		try {

			procIndex1 = dao.setProcedure(proc_name1);

			// set value
			
			dao.setProcInValue(procIndex1, "hblnum_req_no","");
			
			dao.setProcInValue(procIndex1, "gnum_dept_code ","");
			
			dao.setProcInValue(procIndex1, "sblnum_chargetype_id","");
			
			dao.setProcInValue(procIndex1, "sblnum_bservice_id","20");
			
			dao.setProcInValue(procIndex1, "gnum_patient_cat_code","");
			
			dao.setProcInValue(procIndex1, "hrgnum_puk","");
			
			dao.setProcInValue(procIndex1, "hastr_adm_no","");

			dao.setProcInValue(procIndex1, "hblnum_pataccount_no ","");
			
			dao.setProcInValue(procIndex1, "hblnum_approval_id","");
			
			dao.setProcInValue(procIndex1, "hblnum_status","1");
			
			dao.setProcInValue(procIndex1, "gnum_seatid","");
			
			dao.setProcInValue(procIndex1, "hblstr_approved_by ","");
			
			dao.setProcInValue(procIndex1, "hbldt_approved_date","");
			
			dao.setProcInValue(procIndex1, "hblnum_req_type","1");

			dao.setProcOutValue(procIndex1, "ERR",1); // 1 for string return
														// value
	

			// execute procedure

			dao.execute(procIndex1,1);
            retVal = true;
			// get value

			strerr = dao.getString(procIndex1, "ERR");

			if (strerr == null)
			{	
				strerr = "";
			}
			 else
			 {
				throw new Exception(strerr);
			}

		}
		catch (Exception e) 
		{

	//		vo.setStrMsgString("IpdBillManagementTransDAO.getAccountDtlWithAcctNo() --> "
	//						+ e.getMessage());
    //		vo.setStrMsgType("1");
			retVal = false;

		}

		return retVal;	
	}	
	public boolean delete(SbltInBoundDtlVO vo,HisDAO dao) 
	{
		boolean retVal = false; 
		String proc_name1 = "";

		proc_name1 = "";

		
		int procIndex1 = 0;

		String strerr = "";

	//	WebRowSet ws = null;

		try {

			procIndex1 = dao.setProcedure(proc_name1);

			// set value
			
			dao.setProcInValue(procIndex1, "hblnum_req_no","");
			
			dao.setProcInValue(procIndex1, "gnum_dept_code ","");
			
			dao.setProcInValue(procIndex1, "sblnum_chargetype_id","");
			
			dao.setProcInValue(procIndex1, "sblnum_bservice_id","20");
			
			dao.setProcInValue(procIndex1, "gnum_patient_cat_code","");
			
			dao.setProcInValue(procIndex1, "hrgnum_puk","");
			
			dao.setProcInValue(procIndex1, "hastr_adm_no","");

			dao.setProcInValue(procIndex1, "hblnum_pataccount_no ","");
			
			dao.setProcInValue(procIndex1, "hblnum_approval_id","");
			
			dao.setProcInValue(procIndex1, "hblnum_status","1");
			
			dao.setProcInValue(procIndex1, "gnum_seatid","");
			
			dao.setProcInValue(procIndex1, "hblstr_approved_by ","");
			
			dao.setProcInValue(procIndex1, "hbldt_approved_date","");
			
			dao.setProcInValue(procIndex1, "hblnum_req_type","1");

			dao.setProcOutValue(procIndex1, "ERR",1); // 1 for string return
														// value
	

			// execute procedure

			dao.execute(procIndex1,1);
            retVal = true;
			// get value

			strerr = dao.getString(procIndex1, "ERR");

			if (strerr == null)
			{	
				strerr = "";
			}
			 else
			 {
				throw new Exception(strerr);
			}

		}
		catch (Exception e) 
		{

	//		vo.setStrMsgString("IpdBillManagementTransDAO.getAccountDtlWithAcctNo() --> "
	//						+ e.getMessage());
    //		vo.setStrMsgType("1");
			retVal = false;

		}

		return retVal;	
	}
	

}
