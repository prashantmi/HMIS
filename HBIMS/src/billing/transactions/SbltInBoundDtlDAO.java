package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;


public class SbltInBoundDtlDAO 
{
	public boolean insert(SbltInBoundDtlVO sivo,HisDAO dao) 
	{
		boolean retVal = true; 
		String proc_name1 = "";
	//	System.out.println("<-------Inside SbltInBoundDtl---1--->");

		proc_name1 = "{call PKG_BILL_DML.PROC_SBLT_INBOUND_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		
		int procIndex1 = 0;

//		String strerr = "";

//		WebRowSet ws = null;
		
//		int n = Integer.parseInt(sivo.getStrRowInserted());  //Found How Many Times Insert Loop Executed

		try 
		{
			String strTemp[] = sivo.getStrChk().replace('^', '#').split("#");

			/*System.out.println("ReqNo-->"+sivo.getStrReqNo());
			System.out.println("DepCode-->"+sivo.getStrDeptCode());
            System.out.println("ChargeTypeID-->"+sivo.getStrChargeTypeId());
            System.out.println("Patine Cat Code-->"+sivo.getStrPatCatCode());
            System.out.println("Puk no-->"+sivo.getStrPukNo()); 
            System.out.println("Add no-->"+sivo.getStrAdmNo());
            System.out.println("Pat Acct no-->"+sivo.getStrPatAcctNo());
            System.out.println("App Id-->"+sivo.getStrApprId());
            System.out.println("Puk no-->"+sivo.getStrPukNo());
            System.out.println("Seatd Id-->"+sivo.getStrSeatId());*/
            
            sivo.setStrChargeTypeId(strTemp[4]);
		    sivo.setStrPatCatCode(strTemp[3]);
		    sivo.setStrPukNo(strTemp[1]);
		    sivo.setStrAdmNo(strTemp[5]);
		    sivo.setStrPatAcctNo(strTemp[0]);
            
			
            
            procIndex1 = dao.setProcedure(proc_name1);

			// set value
		
            //	System.out.println("No of Loop--->"+n);
		//	for(int i=0;i<n;i++)
		//	{	
			
         
            
			dao.setProcInValue(procIndex1, "modval","1");	
				
			dao.setProcInValue(procIndex1, "hblnum_req_no",sivo.getStrReqNo());
			
			dao.setProcInValue(procIndex1, "gnum_dept_code ",sivo.getStrDeptCode());
			
			dao.setProcInValue(procIndex1, "sblnum_chargetype_id",sivo.getStrChargeTypeId());
			
			dao.setProcInValue(procIndex1, "sblnum_bservice_id","20");
			
			dao.setProcInValue(procIndex1, "gnum_patient_cat_code",sivo.getStrPatCatCode());
			
			dao.setProcInValue(procIndex1, "hrgnum_puk",sivo.getStrPukNo());
			
			dao.setProcInValue(procIndex1, "hastr_adm_no",sivo.getStrAdmNo());

			dao.setProcInValue(procIndex1, "hblnum_pataccount_no ",sivo.getStrPatAcctNo());
			
			dao.setProcInValue(procIndex1, "hblnum_approval_id",sivo.getStrApprId());
			
			dao.setProcInValue(procIndex1, "hblnum_status","1");
			
			dao.setProcInValue(procIndex1, "gnum_seatid",sivo.getStrSeatId());
			
			dao.setProcInValue(procIndex1, "hblstr_approved_by ","");
			
			dao.setProcInValue(procIndex1, "hbldt_approved_date","");
			
			dao.setProcInValue(procIndex1, "hblnum_req_type","1");

			dao.setProcOutValue(procIndex1, "err",1); // 1 for string return
														// value
		//	}

			// execute procedure
			//System.out.println("insert Sblt--1->");
			dao.execute(procIndex1,1);
			//System.out.println("insert Sblt--2->");
			// get value

			//strerr = dao.getString(procIndex1, "err");
			//System.out.println("Err-insert Sblt--3->"+strerr);

		/*	if (strerr == null)
			{	
				strerr = "";
				retVal = true;
			}
			 else
			 {
				throw new Exception(strerr);
			}*/

		}
		catch (Exception e) 
		{

			new HisException("","","IpdBillManagementTransDAO.SbltInBoundDtlDAO () --> "
							+ e.getMessage());
			/*sivo.setStrMsg("");
    		sivo.setStrMsgType("1");*/
			retVal = false;

		}
       // System.out.println("O/p of sivo-->"+retVal);
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
