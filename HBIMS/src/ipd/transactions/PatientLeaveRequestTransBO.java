package ipd.transactions;

import javax.sql.rowset.WebRowSet;

public class  PatientLeaveRequestTransBO {

	

public void consltntID( PatientLeaveRequestTransVO voObj) {

	try 
	{
       //  System.out.println("in bo --modconsltntID");
		 PatientLeaveRequestTransDAO.setConsultantName(voObj);
		// if there is error
		if (voObj.getStrMsgType().equals("1")) 
		{
		  throw new Exception(voObj.getStrErrMsgString());
		}
	} catch (Exception e) 
	  {
		voObj.setStrErrMsgString(" PatientLeaveRequestTransBO.consltntID() -->"+ e.getMessage());
		voObj.setStrMsgType("1");
	  }
}


	public void getRsnRmk( PatientLeaveRequestTransVO voObj)
	{
		try
		 {
		   PatientLeaveRequestTransDAO cltDao = new  PatientLeaveRequestTransDAO();
		   cltDao.getRsnRmk(voObj);
		   if (voObj.getStrMsgType().equals("1")) 
			{
			  throw new Exception(voObj.getStrErrMsgString());
			}
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString(" PatientLeaveRequestTransBO.getRsnRmk() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
	}

	
	public boolean getLeaveRequestValidate( PatientLeaveRequestTransVO voObj)
	{
		boolean retVal=false;
		try
		 {
		   retVal=PatientLeaveRequestTransDAO.getLeaveRequestValidate(voObj);
		   if(voObj.getStrMsgType().equals("1"))
			   throw new Exception(voObj.getStrErrMsgString());
		   else
		   {	   
		    if(retVal==false)
		    {
			  voObj.setStrErrMsgString("Either Pending Leave Request or Patient Already on Leave") ;
		    }
		   } 
		 }
		catch(Exception e)
		{
			voObj.setStrErrMsgString(" PatientLeaveRequestTransBO.getLeaveRequestValidate() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		}
		return retVal;
	}	
    public static void admstat( PatientLeaveRequestTransVO voObj)
    {
    	WebRowSet ws=null;
    	try
    	{
    	   PatientLeaveRequestTransDAO cltDao = new  PatientLeaveRequestTransDAO();
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
			voObj.setStrErrMsgString(" PatientLeaveRequestTransBO.admstat() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
    }

	public static void insertLJ( PatientLeaveRequestTransVO voObj)
	{
		try
		 {
		   PatientLeaveRequestTransDAO.patMov_LJ(voObj);
		   if(voObj.getStrMsgType().equals("1")) 
		   {
			  throw new Exception(voObj.getStrErrMsgString());
		   }
		} catch (Exception e) 
		  {
			voObj.setStrErrMsgString(" PatientLeaveRequestTransBO.insertLJ() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		  }
	}
}
