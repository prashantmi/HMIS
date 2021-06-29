package ipd.transactions;

import ipd.IpdConfigUtil;

import javax.sql.rowset.WebRowSet;

public class PatientTransferTransBO {

	//call dao function for department combo 
	public void department(PatientTransferTransVO VO) {
	
		try {
			
			PatientTransferTransDAO.department(VO);
			// if there is error
			if (VO.getStrMsgType().equals("1")) {
				throw new Exception(VO.getStrErrMsgString());
			}
		} catch (Exception e) {
			VO.setStrErrMsgString(" PatientTransferTransBO.department() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
			
		}
	}
	
	//call dao function for unit combo
	public void unit(PatientTransferTransVO VO) {
		
		try {
			
			PatientTransferTransDAO.unit(VO);
			// if there is error
			if (VO.getStrMsgType().equals("1")) {
				throw new Exception(VO.getStrErrMsgString());
			}
		} catch (Exception e) {
			VO.setStrErrMsgString(" PatientTransferTransBO.unit() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		}
	}
public void unitChangeCombo(PatientTransferTransVO VO) {
		
		try {
			
			PatientTransferTransDAO.unitChangeCombo(VO);
			// if there is error
			if (VO.getStrMsgType().equals("1")) {
				throw new Exception(VO.getStrErrMsgString());
			}
		} catch (Exception e) {
			VO.setStrErrMsgString(" PatientTransferTransBO.unitChangeCombo() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		}
	}
public void roomCombo(PatientTransferTransVO VO) {
	
	try {
		
		PatientTransferTransDAO.roomCombo(VO);
		System.out.println("room ws ====>>>>");
		// if there is error
		if (VO.getStrMsgType().equals("1")) {
			throw new Exception(VO.getStrErrMsgString());
		}
	} catch (Exception e) {
		VO.setStrErrMsgString(" PatientTransferTransBO.roomCombo() --> "
				+ e.getMessage());
		VO.setStrMsgType("1");
	}
}
	public void setBedStatusDtl(PatientTransferTransVO vo)
	{
		try
		{
			PatientTransferTransDAO.setWardTypeDtl(vo);
			PatientTransferTransDAO.setRoomTypeDtl(vo);
		//	PatientTransferTransDAO.getWardValues(vo);
			//PatientTransferTransDAO.getRoomValues(vo);
			PatientTransferTransDAO.setBedTypeDtl(vo);
			PatientTransferTransDAO.setServAreaName(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrErrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrErrMsgString("PatientTransferTransBO.setBedStatusDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	
	//call dao function for ward combo
	public void ward(PatientTransferTransVO VO) {
		
		try {
			
			PatientTransferTransDAO.getWardValues(VO);
			// if there is error
			if (VO.getStrMsgType().equals("1")) {
				throw new Exception(VO.getStrErrMsgString());
			}
		} catch (Exception e) {
			VO.setStrErrMsgString(" PatientTransferTransBO.ward() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		}
	}
	//call dao function for room combo
	
	public void room(PatientTransferTransVO VO) {
		
		IpdConfigUtil ipdC=new IpdConfigUtil(VO.getStrHospCode());
		try {
			/* Changed By Amit Kumar Ateria on 19 Jan 2011 to check MS Approval for Private Ward*/
			//if(VO.getStrWardTypeCode().equals("11") && VO.getStrMsApprovalFlag().equals("1"))
			if(VO.getStrWardTypeCode().equals(ipdC.getStrPrivateWardType()) && VO.getStrMsApprovalFlag().equals("1"))
			{
		       //System.out.println("in private");
				PatientTransferTransDAO.getPatDtl_Msapproval(VO);
			}
			else
			{
			  PatientTransferTransDAO.getRoomValues(VO);
			}
			
			// if there is error
			if (VO.getStrMsgType().equals("1")) {
				throw new Exception(VO.getStrErrMsgString());
			}
		} catch (Exception e) {
			VO.setStrErrMsgString(" PatientTransferTransBO.room() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		}
	}
	
public void bed(PatientTransferTransVO VO) 
{
		try 
		{
			PatientTransferTransDAO.getBed(VO);
			// if there is error
			if (VO.getStrMsgType().equals("1")) 
			{
				throw new Exception(VO.getStrErrMsgString());
			}
		} 
		catch (Exception e) 
		{
			VO.setStrErrMsgString(" PatientTransferTransBO.bed() --> "+ e.getMessage());
			VO.setStrMsgType("1");
		}
}

public void servRoom(PatientTransferTransVO VO) {
	
	try {
		
		PatientTransferTransDAO.getServRoomValues(VO);
		// if there is error
		if (VO.getStrMsgType().equals("1")) {
			throw new Exception(VO.getStrErrMsgString());
		}
	} catch (Exception e) {
		VO.setStrErrMsgString(" PatientTransferTransBO.servRoom() --> "
				+ e.getMessage());
		VO.setStrMsgType("1");
	}
}

public void consltntID(PatientTransferTransVO VO) {
	
	try {
       //  System.out.println("in bo --modconsltntID");
		PatientTransferTransDAO.setConsultantName(VO);
		// if there is error
		if (VO.getStrMsgType().equals("1")) {
			throw new Exception(VO.getStrErrMsgString());
		}
	} catch (Exception e) {
		VO.setStrErrMsgString(" PatientTransferTransBO.consltntID() --> "
				+ e.getMessage());
		VO.setStrMsgType("1");
	}
}
/*
public void setBedDetails(PatientTransferTransVO vo)
{
	try
	{
		PatientTransferTransDAO.getBedValues(vo);
		
	}
	catch(Exception e)
	{
		vo.setStrErrMsgString("PatientTransferTransBO.setBedDetails---->"+e.getMessage());
		vo.setStrMsgType("1");
	}

}*/
	
	public void getRsnRmk(PatientTransferTransVO voObj)
	{
		try
		 {
		  //PatientTransferTransDAO cltDao = new PatientTransferTransDAO(); 
		   //cltDao.getRsnRmk(voObj);
		   PatientTransferTransDAO.setTransferTypeCombo(voObj);
		   
	     }
		catch(Exception e)
		{
			voObj.setStrErrMsgString("PatientTransferTrans.getRsnRmk---->"+e.getMessage());
			voObj.setStrMsgType("1");
		}
	}
	public void getAdvisedBy(PatientTransferTransVO voObj)
	{
		try
		 {
		  PatientTransferTransDAO cltDao = new PatientTransferTransDAO(); 
		   cltDao.getRsnRmk(voObj);
		   
	     }
		catch(Exception e)
		{
			voObj.setStrErrMsgString("PatientTransferTrans.getRsnRmk---->"+e.getMessage());
			voObj.setStrMsgType("1");
		}
	}
	public static void admstat(PatientTransferTransVO voObj)
    {
    	WebRowSet ws=null;
    	try
    	{
    		PatientTransferTransDAO cltDao = new PatientTransferTransDAO();
 		   ws=cltDao.admStatus(voObj);
 		   while(ws.next())
 		   {	   
 		     voObj.setStrAdmStatCode(ws.getString(1));
 		     voObj.setStrIsDead(ws.getString(2));
 		     voObj.setStrPrevDblOcc(ws.getString(3));
 		     voObj.setStrHoldRoom(ws.getString(4));
 		     voObj.setStrConsultant(ws.getString(19));
 		   }  
 		   if(voObj.getStrMsgType().equals("1")) 
			{
			  throw new Exception(voObj.getStrErrMsgString());
			}
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString("PatientTransferTransBO.admstat() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
    }

	public static boolean insert(PatientTransferTransVO voObj)
	{
		boolean retVal=false;
		try
		 {
		  PatientTransferTransDAO cltDao = new PatientTransferTransDAO(); 
		  retVal=cltDao.patMov(voObj);
	     }
		catch(Exception e)
		{
			voObj.setStrErrMsgString("PatientTransferTransBO.insert---->"+e.getMessage());
			voObj.setStrMsgType("1");
		}
		return retVal;
	}
	
	
	public void getServiceType(PatientTransferTransVO VO) {
		
		try {
			
			PatientTransferTransDAO.getServiceType(VO);
			//PatientTransferTransDAO.getServiceName(VO);
			// if there is error
			if (VO.getStrMsgType().equals("1")) {
				throw new Exception(VO.getStrErrMsgString());
			}
		} catch (Exception e) {
			VO.setStrErrMsgString(" PatientTransferTransBO.getServiceType() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
			
		}
	}
	public void getServiceName(PatientTransferTransVO VO) {
		
		try {
			
			PatientTransferTransDAO.getServiceName(VO);
			// if there is error
			if (VO.getStrMsgType().equals("1")) {
				throw new Exception(VO.getStrErrMsgString());
			}
		} catch (Exception e) {
			VO.setStrErrMsgString(" PatientTransferTransBO.getServiceName() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
			
		}
	}

	public void serviceValidation(PatientTransferTransVO VO) {
		
		try {
			
			PatientTransferTransDAO.serviceValidation(VO);
			// if there is error
			if (VO.getStrMsgType().equals("1")) {
				throw new Exception(VO.getStrErrMsgString());
			}
		} catch (Exception e) {
			VO.setStrErrMsgString(" PatientTransferTransBO.serviceValidation() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
			
		}
	}

     
}
