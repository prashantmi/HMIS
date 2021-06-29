package billing.transactions;


public class ClientApprovalDetailsTransBO {

	//used
	public void initClientApprovalDetails(ClientApprovalDetailsTransVO voObj)
	{
    	ClientApprovalDetailsTransDAO.getClientList(voObj);
    	ClientApprovalDetailsTransDAO.checkApprovalDtl(voObj);
    	
    }
	//used
	public void getReApproval(ClientApprovalDetailsTransVO voObj) 
	 {
		 
		  try 
	      {   
			 
			  ClientApprovalDetailsTransDAO.getReApproval(voObj);
		   // Check Error
		       if(voObj.getStrMsgType().equals("1"))
			   {	
			   	   throw new Exception(voObj.getStrMsgString());
			   }
		  }
		  catch (Exception e) 
		  {
  			// Set Error Msg
		    	voObj.setStrMsgString("IpdBillManagementTransBO.GrpOfTariffCombo()--> "
								+ e.getMessage());
				voObj.setStrMsgType("1");

			}
     
	  }	
	
	//used
	public void UNITVAL12(ClientApprovalDetailsTransVO voObj) 
	 {
		 
		  try 
	      {   
		      ClientApprovalDetailsTransDAO.UNITVAL12(voObj);
		   // Check Error
		       if(voObj.getStrMsgType().equals("1"))
			   {	
			   	   throw new Exception(voObj.getStrMsgString());
			   }
		  }
		  catch (Exception e) 
		  {
   			// Set Error Msg
		    	voObj.setStrMsgString("IpdBillManagementTransBO.UNITVAL12()--> "
								+ e.getMessage());
				voObj.setStrMsgType("1");

			}
      
	  }	
	 
///////////////////////////////////////////////////////////////////used
	
  	public boolean insertForApproval(ClientApprovalDetailsTransVO voObj) 
	 {
  	   // Declaring Variables 	
	//	  WebRowSet ws = null;
		  boolean retVal = false;
	   // Createing Object for Table Specific DAO
		    try 
			  {
		           // Call DAO insert method
		    	   retVal = ClientApprovalDetailsTransDAO.InsertApproval(voObj);
		    	   // Check Error
		           if(voObj.getStrMsgType().equals("1"))	
				   {
				       throw new Exception(voObj.getStrMsgString());
				   }
				     
			  } 
			  catch (Exception e) 
			  {
				// Set Error msg  
				voObj.setStrMsgString("ClientApprovalDetailsTransBO.insertForApproval()--> "
								+ e.getMessage());
				voObj.setStrMsgType("1");
				retVal = false;
			}
	  	return retVal;
	 }
	
  //used
	public boolean insertForReApproval(ClientApprovalDetailsTransVO voObj) 
	 {
 	   // Declaring Variables 	
		//  WebRowSet ws = null;
		  boolean retVal = false;
	   // Createing Object for Table Specific DAO
		 	  
		    try 
			  {
		           // Call DAO insert method
		    	   retVal = ClientApprovalDetailsTransDAO.InsertReApproval(voObj);
		    	   // Check Error
		           if(voObj.getStrMsgType().equals("1"))	
				   {
				       throw new Exception(voObj.getStrMsgString());
				   }
				     
			  } 
			  catch (Exception e) 
			  {
				// Set Error msg  
				voObj.setStrMsgString("ClientApprovalDetailsTransBO.insertForReApproval()--> "
								+ e.getMessage());
				voObj.setStrMsgType("1");
				retVal = false;
			}
	  	return retVal;
	 }
	//used
	public boolean insertForClose(ClientApprovalDetailsTransVO voObj) 
	 {
	   // Declaring Variables 	
		//  WebRowSet ws = null;
		  boolean retVal = false;
	   // Createing Object for Table Specific DAO
		 
		//  ClientPatientDAO  pCltPntDtl =  new ClientPatientDAO();
		  
		    try 
			  {
		           // Call DAO insert method
		    	   retVal = ClientApprovalDetailsTransDAO.InsertClose(voObj);
		    	   // Check Error
		           if(voObj.getStrMsgType().equals("1"))	
				   {
				       throw new Exception(voObj.getStrMsgString());
				   }
				     
			  } 
			  catch (Exception e) 
			  {
				// Set Error msg  
				voObj.setStrMsgString("ClientApprovalDetailsTransBO.insertForClose()--> "
								+ e.getMessage());
				voObj.setStrMsgType("1");
				retVal = false;
			}
	  	return retVal;
	 }
	
	
	
	/*public void GrpOfTariffCombo(ClientApprovalDetailsTransVO voObj) 
	 {
		  try 
	      {   
			 // ClientApprovalDetailsTransDAO.getOffLineDiscountApprovalList(voObj);
			 // ClientApprovalDetailsTransDAO.getOffLineDiscountRemarksList(voObj);
			 // IpdBillManagementTransDAO.getOffLineTariffUnitList(voObj);
		       // Check Error
		       if(voObj.getStrMsgType().equals("1"))
			   {	
			   	   throw new Exception(voObj.getStrMsgString());
			   }
		  }
		  catch (Exception e) 
		  {
   			// Set Error Msg
		    	voObj.setStrMsgString("ClientApprovalDetailsTransBO.GrpOfTariffCombo()--> "
								+ e.getMessage());
				voObj.setStrMsgType("1");

			}
      
	  }	
	public void GrpOfTariffCombo1(ClientApprovalDetailsTransVO voObj) 
	 {
		  try 
	      {   
			//  ClientApprovalDetailsTransDAO.getOffLineGroup(voObj);
			 
		       if(voObj.getStrMsgType().equals("1"))
			   {	
			   	   throw new Exception(voObj.getStrMsgString());
			   }
		  }
		  catch (Exception e) 
		  {
  			// Set Error Msg
		    	voObj.setStrMsgString("ClientApprovalDetailsTransBO.GrpOfTariffCombo()--> "
								+ e.getMessage());
				voObj.setStrMsgType("1");

			}
     
	  }	


	public void setTariffList(ClientApprovalDetailsTransVO voObj) {

		ClientApprovalDetailsTransDAO.setTariffDtl(voObj);

	}

	public void initReApprovalDetails(ClientApprovalDetailsTransVO voObj) {

		ClientApprovalDetailsTransDAO.initReApprovalDetails(voObj);
		// ClientApprovalDetailsTransDAO.setPatientDtl(voObj);
		//ClientApprovalDetailsTransDAO.setGroupDtl(voObj);

	}

	public void initReFundDetails(ClientApprovalDetailsTransVO voObj) {

		ClientApprovalDetailsTransDAO.initReFundDetails(voObj);

		int nRefundAmount = Integer.parseInt(voObj
				.getStrAmountReceivedFromClient())
				- Integer.parseInt(voObj.getStrClientExpenseAmount())
				+ Integer.parseInt(voObj.getStrAmountReceivedFromPatient())
				- Integer.parseInt(voObj.getStrRefundAmount());

		voObj.setStrRefundAmount(String.valueOf(nRefundAmount));

		// ClientApprovalDetailsTransDAO.setPatientDtl(voObj);

	}

	public void initCloseDetails(ClientApprovalDetailsTransVO voObj) {

		ClientApprovalDetailsTransDAO.initReFundDetails(voObj);

		// ClientApprovalDetailsTransDAO.setPatientDtl(voObj);

	}
*/
	//used
	public void initViewDetails(ClientApprovalDetailsTransVO voObj) 
	{
		ClientApprovalDetailsTransDAO.setClientApprovalViewDtls(voObj);
	}

}
