package ipd.transactions;
import javax.sql.rowset.WebRowSet;
public class  PatientLeaveApprovalTransBO {

public void consltntID( PatientLeaveApprovalTransVO voObj) {

	try 
	{
		 PatientLeaveApprovalTransDAO.setConsultantName(voObj);
		// if there is error
		if (voObj.getStrMsgType().equals("1")) 
		{
		  throw new Exception(voObj.getStrErrMsgString());
		}
	} catch (Exception e) 
	  {
		voObj.setStrErrMsgString(" PatientLeaveApprovalTransBO.consltntID() -->"+ e.getMessage());
		voObj.setStrMsgType("1");
	  }
}


	public void getRsnRmk( PatientLeaveApprovalTransVO voObj)
	{
		try
		 {
		   PatientLeaveApprovalTransDAO cltDao = new  PatientLeaveApprovalTransDAO();
		   cltDao.getRsnRmk(voObj);
		   if (voObj.getStrMsgType().equals("1")) 
			{
			  throw new Exception(voObj.getStrErrMsgString());
			}
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString(" PatientLeaveApprovalTransBO.getRsnRmk() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
	}

    public static void admstat( PatientLeaveApprovalTransVO voObj)
    {
    	WebRowSet ws=null;
    	try
    	{
    	   PatientLeaveApprovalTransDAO cltDao = new  PatientLeaveApprovalTransDAO();
 		   ws=cltDao.admStatus(voObj);
 		   while(ws.next())
 		   {	   
 		    voObj.setStrAdmStatCode(ws.getString(1));
 		    voObj.setStrIsDead(ws.getString(2));
 		   } 
 		   if(voObj.getStrMsgType().equals("1")) 
			{
			  throw new Exception(voObj.getStrErrMsgString());
			}
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString(" PatientLeaveApprovalTransBO.admstat() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
    }

	public static void upadateLeaveApprovalDtl( PatientLeaveApprovalTransVO voObj)
	{
		try
		 {
		   PatientLeaveApprovalTransDAO.upadateLeaveApprovalDtl(voObj);
		   if(voObj.getStrMsgType().equals("1")) 
		   {
			  throw new Exception(voObj.getStrErrMsgString());
		   }
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString(" PatientLeaveApprovalTransBO.upadateLeaveApprovalDtl() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
	}
	
	/*****select******/
	
	public boolean setLeaveDtl( PatientLeaveApprovalTransVO voObj)
	{
		WebRowSet ws = null;
		boolean retVal=false;
		try
		 {
		   PatientLeaveApprovalTransDAO.setLeaveDtl(voObj);
		   ws=voObj.getStrLeaveApprovalWS();
		 //  System.out.println("ws.size->"+ws.size());
		   if(ws.size()==0)
			   retVal=false;
		   else
			   retVal=true;
		  if(retVal==true)
		  {	   
		   while(ws.next())
		   {	   
		    voObj.setStrLeaveSlNo(ws.getString(2));
		    voObj.setStrRsn(ws.getString(8));
		    voObj.setStrPatCondL(ws.getString(9));
		    voObj.setStrPhoneNo(ws.getString(10));
		    voObj.setStrAddrLeave(ws.getString(11));
		    voObj.setStrIsBedVacant(ws.getString(14));
		    voObj.setStrEntryDate(ws.getString(15));
		    voObj.setStrLeavReqFrmDate(ws.getString(6));
		    voObj.setStrLeavReqToDate(ws.getString(7));
		    voObj.setStrLeaveTypeText(ws.getString(21));
		    //voObj.setStrAdmNo(ws.getString(1));
		   }
		  }
		  else
		  {  
		 //  System.out.println("MsgType->"+voObj.getStrMsgType());
		   if (voObj.getStrMsgType().equals("1")) 
		   {
			  throw new Exception(voObj.getStrErrMsgString());
		   }
		   else
			   voObj.setStrErrMsgString("Leave Request not Raised or Request Already Approved") ;
		  } 
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString(" PatientLeaveApprovalTransBO.setLeaveDtl() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
	 return retVal;	
	}
}
