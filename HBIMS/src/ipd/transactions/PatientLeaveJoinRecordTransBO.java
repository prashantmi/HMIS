package ipd.transactions;

import ipd.IpdConfigUtil;

import javax.sql.rowset.WebRowSet;

public class PatientLeaveJoinRecordTransBO {

/*	public WebRowSet setReqValues(PatientLeaveJoinRecordTransVO voObj,int hlp_opt_sel,String strValmode)
	 {
		 WebRowSet web = null;
		 int flg=Integer.parseInt(strValmode);
			try {
				   PatientLeaveJoinRecordTransDAO cltDao = new PatientLeaveJoinRecordTransDAO();
				  
				   if (voObj.getStrMsgType().equals("1")) 
				   {
					 throw new Exception(voObj.getStrErrMsgString());
				   }
				} catch (Exception e) 
				  {
					voObj.setStrErrMsgString("PatientLeaveJoinRecordTransBO.setReqValues() -->"+ e.getMessage());
					voObj.setStrMsgType("1");
				  }
			    return web;
	  }

	//call dao function for department combo
	public void department(PatientLeaveJoinRecordTransVO voObj) {
		try {
			PatientLeaveJoinRecordTransDAO.department(voObj);
			// if there is error
			if (voObj.getStrMsgType().equals("1")) 
			   {
				 throw new Exception(voObj.getStrErrMsgString());
			   }
			} catch (Exception e) 
			  {
				voObj.setStrErrMsgString("PatientLeaveJoinRecordTransBO.department() -->"+ e.getMessage());
				voObj.setStrMsgType("1");
			  }
	}

	//call dao function for unit combo
	public void unit(PatientLeaveJoinRecordTransVO voObj) {
		try {
			PatientLeaveJoinRecordTransDAO.unit(voObj);
			// if there is error
			if (voObj.getStrMsgType().equals("1")) 
			   {
				 throw new Exception(voObj.getStrErrMsgString());
			   }
			} catch (Exception e) 
			  {
				voObj.setStrErrMsgString("PatientLeaveJoinRecordTransBO.unit() -->"+ e.getMessage());
				voObj.setStrMsgType("1");
			  }
	}
*/
	public void setBedStatusDtl(PatientLeaveJoinRecordTransVO voObj)
	{
		try
		{
		//	PatientLeaveJoinRecordTransDAO.setWardTypeDtl(voObj);
			PatientLeaveJoinRecordTransDAO.setRoomTypeDtl(voObj);
		  //PatientLeaveJoinRecordTransDAO.getWardValues(voObj);
		  //PatientLeaveJoinRecordTransDAO.getRoomValues(voObj);
			PatientLeaveJoinRecordTransDAO.setBedTypeDtl(voObj);
		//	PatientLeaveJoinRecordTransDAO.setServAreaName(voObj);
			if (voObj.getStrMsgType().equals("1")) 
			{
			  throw new Exception(voObj.getStrErrMsgString());
			}
		} catch (Exception e) 
			  {
				voObj.setStrErrMsgString("PatientLeaveJoinRecordTransBO.setBedStatusDtl() -->"+ e.getMessage());
				voObj.setStrMsgType("1");
			  }
	}

	//call dao function for ward combo
/*	public void ward(PatientLeaveJoinRecordTransVO voObj) {

		try {
			PatientLeaveJoinRecordTransDAO.getWardValues(voObj);
			// if there is error
			if (voObj.getStrMsgType().equals("1")) 
			{
			  throw new Exception(voObj.getStrErrMsgString());
			}
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString("PatientLeaveJoinRecordTransBO.ward() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
	}*/
	//call dao function for room combo

	public void room(PatientLeaveJoinRecordTransVO voObj) {
		IpdConfigUtil ipdC=new IpdConfigUtil(voObj.getStrHospCode());
		try 
		{
			/* Changed By Amit Kumar Ateria on 19 Jan 2011 to check MS Approval for Private Ward*/
			//if(voObj.getStrWardTypeCode().equals("11") && voObj.getStrMsApprovalFlag().equals("1"))
			if(voObj.getStrWardTypeCode().equals(ipdC.getStrPrivateWardType()) && voObj.getStrMsApprovalFlag().equals("1"))
			{
		    //    System.out.println("in private");
				PatientLeaveJoinRecordTransDAO.getPatDtl_Msapproval(voObj);
			}
			else
			{
			  PatientLeaveJoinRecordTransDAO.getRoomValues(voObj);
			}
			// if there is error
			if (voObj.getStrMsgType().equals("1")) 
			{
			  throw new Exception(voObj.getStrErrMsgString());
			}
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString("PatientLeaveJoinRecordTransBO.room() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
	}

public void bed(PatientLeaveJoinRecordTransVO voObj) {

		try 
		{
			PatientLeaveJoinRecordTransDAO.getBed(voObj);
			// if there is error
			if (voObj.getStrMsgType().equals("1")) 
			{
			  throw new Exception(voObj.getStrErrMsgString());
			}
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString("PatientLeaveJoinRecordTransBO.bed() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
	}
/*
public void servRoom(PatientLeaveJoinRecordTransVO voObj) {

	try 
	{
		PatientLeaveJoinRecordTransDAO.getServRoomValues(voObj);
		// if there is error
		if (voObj.getStrMsgType().equals("1")) 
		{
		  throw new Exception(voObj.getStrErrMsgString());
		}
	} catch (Exception e) 
	  {
		voObj.setStrErrMsgString("PatientLeaveJoinRecordTransBO.servRoom() -->"+ e.getMessage());
		voObj.setStrMsgType("1");
	  }
}*/

public void consltntID(PatientLeaveJoinRecordTransVO voObj) {

	try 
	{
		PatientLeaveJoinRecordTransDAO.setConsultantName(voObj);
		// if there is error
		if (voObj.getStrMsgType().equals("1")) 
		{
		  throw new Exception(voObj.getStrErrMsgString());
		}
	} catch (Exception e) 
	  {
		voObj.setStrErrMsgString("PatientLeaveJoinRecordTransBO.consltntID() -->"+ e.getMessage());
		voObj.setStrMsgType("1");
	  }
}

public void setBedDetails(PatientLeaveJoinRecordTransVO voObj)
{
	try
	{
		PatientLeaveJoinRecordTransDAO.getBedValues(voObj);
		if (voObj.getStrMsgType().equals("1")) 
		{
		  throw new Exception(voObj.getStrErrMsgString());
		}
	} catch (Exception e) 
	  {
		voObj.setStrErrMsgString("PatientLeaveJoinRecordTransBO.setBedDetails() -->"+ e.getMessage());
		voObj.setStrMsgType("1");
	  }
}

	

	public void getRsnRmk(PatientLeaveJoinRecordTransVO voObj)
	{
		try
		 {
		  PatientLeaveJoinRecordTransDAO cltDao = new PatientLeaveJoinRecordTransDAO();
		   cltDao.getRsnRmk(voObj);
		   if (voObj.getStrMsgType().equals("1")) 
			{
			  throw new Exception(voObj.getStrErrMsgString());
			}
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString("PatientLeaveJoinRecordTransBO.getRsnRmk() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
	}


    public static void admstat(PatientLeaveJoinRecordTransVO voObj)
    {
    	WebRowSet ws=null;
    	try
    	{
    	   PatientLeaveJoinRecordTransDAO cltDao = new PatientLeaveJoinRecordTransDAO();
 		   ws=cltDao.admStatus(voObj);
 		   while(ws.next())
		   {	   
		    voObj.setStrAdmStatCode(ws.getString(1));
		    voObj.setStrIsDead(ws.getString(2));
		    voObj.setStrBelongingClear(ws.getString(17));
		   } 
 		   if(voObj.getStrMsgType().equals("1")) 
			{
			  throw new Exception(voObj.getStrErrMsgString());
			}
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString("PatientLeaveJoinRecordTransBO.admstat() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
    }


	
	public static void upadateLeaveApprovalDtl( PatientLeaveJoinRecordTransVO voObj)
	{
		try
		 {
			/*System.out.println("roomCmb->"+voObj.getStrRoom());
			System.out.println("bedCmb->"+voObj.getStrBed());
			String bedCmb[]=voObj.getStrBed().replace('^','#').split("#");
			voObj.setStrBed(bedCmb[1]);
			voObj.setStrBedProperty(bedCmb[0]);
			System.out.println("dept->"+voObj.getStrDeptCode());
			System.out.println("deptUnit->"+voObj.getStrDeptUnitCode());
			System.out.println("wrd->"+voObj.getStrWardCode());
			System.out.println("room->"+voObj.getStrRoomCode());
			System.out.println("bed->"+voObj.getStrBedCode());
			System.out.println("isBedVacant->"+voObj.getStrIsBedVacant());
			System.out.println("ctDate->"+voObj.getStrCtDate());
			System.out.println("tmpLeaveOrJoin->"+voObj.getStrTempLeaveJoin());*/
			PatientLeaveJoinRecordTransDAO.upadateLeaveApprovalDtl(voObj);
		   if(voObj.getStrMsgType().equals("1")) 
		   {
			  throw new Exception(voObj.getStrErrMsgString());
		   }
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString(" PatientLeaveJoinRecordTransBO.upadateLeaveApprovalDtl() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
	}
	
	/*****select******/
	
	public boolean setLeaveDtl( PatientLeaveJoinRecordTransVO voObj)
	{
		WebRowSet ws = null;
		boolean retVal=false;
		try
		 {
		   retVal=PatientLeaveJoinRecordTransDAO.getLeaveStatus(voObj);
		   if(retVal==true)
		   {	   
			    PatientLeaveJoinRecordTransDAO.setLeaveDtl(voObj);
			    
			    ws=voObj.getStrLeaveApprovalWS();
			    ws.next();
			    voObj.setStrLeaveSlNo(ws.getString(2));
			    voObj.setStrRsn(ws.getString(8));
			    voObj.setStrPatCondL(ws.getString(9));
			    voObj.setStrPhoneNo(ws.getString(10));
			    voObj.setStrAddrLeave(ws.getString(11));
			    voObj.setStrIsBedVacant(ws.getString(14));
			    voObj.setStrEntryDate(ws.getString(15));
			    voObj.setStrValidFrom(ws.getString(21));
			    voObj.setStrValidTo(ws.getString(22));
			    if(voObj.getStrTempLeaveJoin().equals("leave"))
			   	 voObj.setStrLeaveTypeText(ws.getString(23));
				else	
					voObj.setStrLeaveTypeText(ws.getString(26));
		   }
		   else
		   {
			  if (voObj.getStrMsgType().equals("1")) 
			  {
				 throw new Exception(voObj.getStrErrMsgString());
			  } 
			  else
			  {
			    if(voObj.getStrTempLeaveJoin().equals("leave"))
			      voObj.setStrErrMsgString("Leave Request not Approved or Patient on Leave") ;
			    else
				  voObj.setStrErrMsgString("Leave Approval/Entry Pending or Patient has already Joined") ;
			  }  
		   }
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString("PatientLeaveJoinRecordTransBO.setLeaveDtl() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
		return retVal;
	}
}
