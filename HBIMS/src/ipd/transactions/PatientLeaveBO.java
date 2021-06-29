package ipd.transactions;

import ipd.IpdConfigUtil;

import javax.sql.rowset.WebRowSet;

public class PatientLeaveBO {

	
	public void setBedStatusDtl(PatientLeaveVO voObj)
	{
		try
		{
		//	PatientLeaveDAO.setWardTypeDtl(voObj);
			PatientLeaveDAO.setRoomTypeDtl(voObj);
		  //PatientLeaveDAO.getWardValues(voObj);
		  //PatientLeaveDAO.getRoomValues(voObj);
			PatientLeaveDAO.setBedTypeDtl(voObj);
			//PatientLeaveDAO.setServAreaName(voObj);
			if (voObj.getStrMsgType().equals("1")) 
			{
			  throw new Exception(voObj.getStrErrMsgString());
			}
		} catch (Exception e) 
			  {
				voObj.setStrErrMsgString("PatientLeaveBO.setBedStatusDtl() -->"+ e.getMessage());
				voObj.setStrMsgType("1");
			  }
	}


	//call dao function for room combo

	public void room(PatientLeaveVO voObj) {
		IpdConfigUtil ipdC=new IpdConfigUtil(voObj.getStrHospCode());
		try 
		{
			/* Changed By Amit Kumar Ateria on 19 Jan 2011 to check MS Approval for Private Ward*/
			//if(voObj.getStrWardTypeCode().equals("11") && voObj.getStrMsApprovalFlag().equals("1"))
			if(voObj.getStrWardTypeCode().equals(ipdC.getStrPrivateWardType()) && voObj.getStrMsApprovalFlag().equals("1"))
			{
		     //   System.out.println("in private");
				PatientLeaveDAO.getPatDtl_Msapproval(voObj);
			}
			else
			{
			  PatientLeaveDAO.getRoomValues(voObj);
			}

			// if there is error
			if (voObj.getStrMsgType().equals("1")) 
			{
			  throw new Exception(voObj.getStrErrMsgString());
			}
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString("PatientLeaveBO.room() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
	}

public void bed(PatientLeaveVO voObj) {
		try 
		{
			PatientLeaveDAO.getBed(voObj);
			// if there is error
			if (voObj.getStrMsgType().equals("1")) 
			{
			  throw new Exception(voObj.getStrErrMsgString());
			}
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString("PatientLeaveBO.bed() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
	}

public void consltntID(PatientLeaveVO voObj) {

	try 
	{
     //    System.out.println("in bo --modconsltntID");
		PatientLeaveDAO.setConsultantName(voObj);
		// if there is error
		if (voObj.getStrMsgType().equals("1")) 
		{
		  throw new Exception(voObj.getStrErrMsgString());
		}
	} catch (Exception e) 
	  {
		voObj.setStrErrMsgString("PatientLeaveBO.consltntID() -->"+ e.getMessage());
		voObj.setStrMsgType("1");
	  }
}

public void setBedDetails(PatientLeaveVO voObj)
{
	try
	{
		PatientLeaveDAO.getBedValues(voObj);
		if (voObj.getStrMsgType().equals("1")) 
		{
		  throw new Exception(voObj.getStrErrMsgString());
		}
	} catch (Exception e) 
	  {
		voObj.setStrErrMsgString("PatientLeaveBO.setBedDetails() -->"+ e.getMessage());
		voObj.setStrMsgType("1");
	  }
}

	public void getRsnRmk(PatientLeaveVO voObj)
	{
		try
		 {
		  PatientLeaveDAO cltDao = new PatientLeaveDAO();
		//  System.out.println("rsnRmk BO");
		   cltDao.getRsnRmk(voObj);
		   if (voObj.getStrMsgType().equals("1")) 
			{
			  throw new Exception(voObj.getStrErrMsgString());
			}
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString("PatientLeaveBO.getRsnRmk() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
	}

	
    public static void admstat(PatientLeaveVO voObj)
    {
    	WebRowSet ws=null;
    	try
    	{
    	   PatientLeaveDAO cltDao = new PatientLeaveDAO();
 		   ws=cltDao.admStatus(voObj);
 		   ws.next();
 		   voObj.setStrAdmStatCode(ws.getString(1));
 		   voObj.setStrIsDead(ws.getString(2));
		   voObj.setStrBelongingClear(ws.getString(17));
 		   if(voObj.getStrMsgType().equals("1")) 
			{
			  throw new Exception(voObj.getStrErrMsgString());
			}
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString("PatientLeaveBO.admstat() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
    }

	public static void insertLJ(PatientLeaveVO voObj)
	{
		/*System.out.println("dept->"+voObj.getStrDeptCode());
		System.out.println("deptUnit->"+voObj.getStrDeptUnitCode());
		System.out.println("wrd->"+voObj.getStrWardCode());
		System.out.println("room->"+voObj.getStrRoomCode());
		System.out.println("bed->"+voObj.getStrBedCode());
		System.out.println("isBedVacant->"+voObj.getStrIsBedVacant());
		System.out.println("ctDate->"+voObj.getStrCtDate());
		System.out.println("Leave By->"+voObj.getStrRmkL());
		System.out.println("Leave Reason->"+voObj.getStrRsnL());*/
		try
		 {
		  PatientLeaveDAO cltDao = new PatientLeaveDAO();
		  cltDao.patMov_LJ(voObj);
		   if(voObj.getStrMsgType().equals("1")) 
		   {
			  throw new Exception(voObj.getStrErrMsgString());
		   }
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString("PatientLeaveBO.insertLJ() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
	}
	
	public static void upadateLeaveApprovalDtl( PatientLeaveVO voObj)
	{
		try
		 {
		/*	System.out.println("roomCmb->"+voObj.getStrRoom());
			System.out.println("bedCmb->"+voObj.getStrBed());
			String bedCmb[]=voObj.getStrBed().replace('^','#').split("#");
			voObj.setStrBed(bedCmb[1]);
			voObj.setStrBedProperty(bedCmb[0]);
			System.out.println("admno->"+voObj.getCurAdmNo());
			System.out.println("puk->"+voObj.getStrCrNo());
			System.out.println("dept->"+voObj.getStrDeptCode());
			System.out.println("deptUnit->"+voObj.getStrDeptUnitCode());
			System.out.println("wrd->"+voObj.getStrWardCode());
			System.out.println("room->"+voObj.getStrRoomCode());
			System.out.println("bed->"+voObj.getStrBedCode());
			System.out.println("isBedVacant->"+voObj.getStrIsBedVacant());
			System.out.println("ctDate->"+voObj.getStrCtDate());
			System.out.println("Join By->"+voObj.getStrRmkJ());
			System.out.println("Join Rsn->"+voObj.getStrRsnJ());*/
			PatientLeaveDAO.upadateLeaveApprovalDtl(voObj);
		   if(voObj.getStrMsgType().equals("1")) 
		   {
			  throw new Exception(voObj.getStrErrMsgString());
		   }
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString(" PatientLeaveBO.upadateLeaveApprovalDtl() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
	}
	
	/*****select******/
	
	public boolean setLeaveDtl( PatientLeaveVO voObj)
	{
		WebRowSet ws = null;
		boolean retVal=true;
		try
		 {
		 //  retVal=PatientLeaveDAO.getLeaveStatus(voObj);
		//   System.out.println("retVal in DAO->"+retVal);
		 //  if(retVal==true)
		   //{	   
			   PatientLeaveDAO.setLeaveDtl(voObj);
		    ws=voObj.getStrLeaveApprovalWS();
		    ws.next();
		    voObj.setStrLeaveSlNo(ws.getString(2));
		    voObj.setStrRsn(ws.getString(8));
		    voObj.setStrPatCondL(ws.getString(9));
		    voObj.setStrPhoneNo(ws.getString(10));
		    voObj.setStrAddrLeave(ws.getString(11));
		    voObj.setStrIsBedVacant(ws.getString(14));
		    voObj.setStrEntryDate(ws.getString(15));
		    voObj.setStrValidFrom(ws.getString(6));
		    voObj.setStrValidTo(ws.getString(7));
		    voObj.setStrLeaveBy(ws.getString(24));
		    voObj.setStrAdviceL(ws.getString(25));
		     if (voObj.getStrMsgType().equals("1")) 
			 {
			   throw new Exception(voObj.getStrErrMsgString());
			 }
		 /*  }
		   else
		   {
			  if(voObj.getStrTempLeaveJoin().equals("leave"))
			     voObj.setStrErrMsgString("Leave Request not Approved or Patient on Leave") ;
			  else
				  voObj.setStrErrMsgString("Leave Approval/Entry Pending or Patient has already Joined") ;
		   }*/
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString("PatientLeaveJoinRecordTransBO.setLeaveDtl() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
		return retVal;
	}
}
