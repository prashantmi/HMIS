package billing.transactions;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.dao.PrimaryKeyDAO;
import billing.dao.PrimaryKeyLogDAO;

public class IpdBillManagementTransBO 
{
	/**
	 * This class is used to for business opeartions. 
	 * and perform final Interaction with DAO .
	 */

	
	/**
	 * getValues(vo) -- >
	 * This Method interact with DAO for UpdateAccountStatus Page
	 * @param ValueObject
	 */	
	
	
	public void getValues(IpdBillManagementTransVO voObj) 
	{
			  try 
			  {
				 //    String DeptId1 = IpdBillManagementTransDAO.DepUnitId(voObj);
				     //voObj.setStrRemarksCombo(IpdBillManagementTransDAO.REMARKSCOMBO(voObj));
				     voObj.setStrApprovedByCombo(IpdBillManagementTransDAO.APPROVEDBY(voObj));
				     voObj.setStrAccStatusCombo(IpdBillManagementTransDAO.ACCOUNTSTATUS(voObj));
				     // Check Error
				     if(voObj.getStrMsgType().equals("1"))	
					 {
				       throw new Exception(voObj.getStrMsgString());
					 }				 
			    }
			    catch (Exception e)
			    {
                voObj.setStrMsgString("IpdBillManagementTransBO.getValues() --> "+ e.getMessage());
				voObj.setStrMsgType("1");
			}
	  }
	
	/**
	 * getApprovedBy(vo) -- >
	 * This Method interact with DAO to get Approved by Combo
	 * @param ValueObject
	 */	

	public void getApprovedBy(IpdBillManagementTransVO voObj) 
	 {
		
			  try
			  {				     
				     voObj.setStrApprovedByCombo(IpdBillManagementTransDAO.APPROVEDBY(voObj));
				     // Check Error
				     if(voObj.getStrMsgType().equals("1"))	
					 {
				       throw new Exception(voObj.getStrMsgString());
					 }
				 
			    }
			    catch (Exception e)
			    {
                
				voObj.setStrMsgString("IpdBillManagementTransBO.getValues() --> "
								+ e.getMessage());
				voObj.setStrMsgType("1");

			}

	  }
	
	
     ////////////////Bill Approval Insert  ///////////////////////
	
	/**
	 * insertBillApproval(vo) -- >
	 * This Method interact with DAO to insert Data  for Bill Approval Transaction
	 * @param ValueObject
	 */	
	
	public void insertBillApproval(IpdBillManagementTransVO voObj) 
	 {
			try 
			{
				if(voObj.getIsApproved().equals("1"))
		            IpdBillManagementTransDAO.InsertBillApproval(voObj);
				else
					IpdBillManagementTransDAO.InsertBillReject(voObj);
		           if(voObj.getStrMsgType().equals("1"))	
				   {
				       throw new Exception(voObj.getStrMsgString());
				   }				     
			} 
			catch (Exception e) 
			{
				  voObj.setStrMsgString("IpdBillManagementTransBO.insertPorcedure()--> "+ e.getMessage());
				  voObj.setStrMsgType("1");		
			}	  
	 }
	
	//////////////// ADD Service Insert  ///////////////////////
	
	/**
	 * insertAddService(vo) -- >
	 * This Method interact with DAO to insert Data  for Add Services Transaction
	 * @param ValueObject
	 */	
	
	public boolean insertAddService(IpdBillManagementTransVO voObj) 
	{
		boolean retVal = false;
		try 
		{
	        retVal = IpdBillManagementTransDAO.InsertAddServiceTrans(voObj);
	        if(voObj.getStrMsgType().equals("1"))	
		    {
		       throw new Exception(voObj.getStrMsgString());
		    }				     
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			voObj.setStrMsgString("IpdBillManagementTransBO.insertAddService()--> "+ e.getMessage());
			voObj.setStrMsgType("1");
			retVal = false;
		}
	  	return retVal;
	 }
	/////////////////////////////////////////////////////////////////
	
	/**
	 * insertPorcedure(vo) -- >
	 * This Method interact with DAO to insert Data  for Account Details Transaction
	 * @param ValueObject
	 */	
	
  	public boolean insertPorcedure(IpdBillManagementTransVO voObj) 
	 {
  	   // Declaring Variables 	
		//  WebRowSet ws = null;
		  boolean retVal = false;
		  
		    try 
			  {
		           // Call DAO insert method
		    	   retVal = IpdBillManagementTransDAO.InsertAccountDtl(voObj);
		    	   // Check Error
		           if(voObj.getStrMsgType().equals("1"))	
				   {
				       throw new Exception(voObj.getStrMsgString());
				   }
				     
			  } 
			  catch (Exception e) 
			  {
				// Set Error msg  
				voObj.setStrMsgString("IpdBillManagementTransBO.insertPorcedure()--> "
								+ e.getMessage());
				voObj.setStrMsgType("1");
				retVal = false;
			}
	  	return retVal;
	 }
  	//////////////////////////////NEW PART PAYMENT/////////////////////////////
  	/**
	 * insertPorcedureForPartPayment(vo) -- >
	 * This Method interact with DAO to insert Data  for Part Payment Transaction
	 * @param ValueObject
	 */	
  	
	public boolean insertPorcedureForPartPayment(IpdBillManagementTransVO voObj) 
	 {
		  // Declaring Variables
		//  WebRowSet ws = null;
		//  String s = null;
		  boolean retVal = false;
		    try 
		      {
//		        System.out.println("Approved By-In BO->"+voObj.getStrApprovalBy());
//		        System.out.println("Remarks-In BO->"+voObj.getStrRemarks());
//		        System.out.println("Part Payment-In BO->"+voObj.getStrPartPaymentAmt());

		    	if(voObj.getStrRemarks()==null || voObj.getStrRemarks().equals(""))
		    	   {
		    		    voObj.setStrApprovalBy("");
		    		    voObj.setStrApprovedDate("");
		       		    // Call DAO InsertDataPartPayment method
		    		    retVal = IpdBillManagementTransDAO.InsertDataPartPayment(voObj);
		    		    // Check Error
		    		    if(voObj.getStrMsgType().equals("1"))
					     {
		    		    	throw new Exception(voObj.getStrMsgString());
						 }	
		    	             
		    	   }
		    	   else
		    	   {
		    		    voObj.setStrApprovedDate(voObj.getStrCtDate());
		    		    // Call DAO Insert Data Part Payment with Approval
		    		    retVal = IpdBillManagementTransDAO.InsertDataPartPaymentWithApproval(voObj);
		    		    // Check Error
		    		    if(voObj.getStrMsgType().equals("1"))
					     {
							throw new Exception(voObj.getStrMsgString());
						 }	
		    	   }
			  } 
			  catch (Exception e) 
			  {
				retVal = false;
                // Set Error Msg
				voObj.setStrMsgString("IpdBillManagementTrnsBO.insertPorcedureForPartPayment()--> "+voObj.getStrMsgString());
				voObj.setStrMsgType("1");
              }
	  	return retVal;
	 }
 	////////////////////////////UPDATE ACCT STATUS////////////////////////////
	/**
	 * insertPorcedureForUpdateAcctStatus(vo) -- >
	 * This Method interact with DAO to insert Data  for Update Account Status Transaction
	 * @param ValueObject
	 */	
	
	public boolean insertPorcedureForUpdateAcctStatus(IpdBillManagementTransVO voObj)
	 {
  
		// Declare variables
	//	  WebRowSet ws = null;
		  boolean retVal = false;
		    try 
		      {
//				System.out.println("Remarks-Combo BO->"+voObj.getStrRemarks());
//				System.out.println("Approved By Combo -BO->"+voObj.getStrApprovalBy());
//				System.out.println("PresentAcct Status-BO->"+voObj.getStrPresentAcctStatus());
//			    System.out.println("New Acct Status-BO->"+voObj.getStrNewAcctStatus());	
 
		    		/*  if(voObj.getStrPresentAcctStatus()!= null)  
		    		  {	  
		    		    if (voObj.getStrPresentAcctStatus().equals("Approved Request")) 
		    		    {
		    				voObj.setStrPAcctStatus("4");
		    			}
		    			if (voObj.getStrPresentAcctStatus().equals("Active"))
		    			{
		    				voObj.setStrPAcctStatus("1");
		    			}
		    			if (voObj.getStrPresentAcctStatus().equals("On-Hold"))
		    			{
		    				voObj.setStrPAcctStatus("2");
		    			}
		    			if (voObj.getStrPresentAcctStatus().equals("Dormant"))
		    			{
		    				voObj.setStrPAcctStatus("3");
		    			}
		    			if (voObj.getStrPresentAcctStatus().equals("Cancelled"))
		    			{
		    				voObj.setStrPAcctStatus("5");
		    			}
		    		  }*/
		    	if(voObj.getStrPresentAcctStatus()!= null)  
	    		  {	  
	    		    if (voObj.getStrPresentAcctStatus().equals("In-Active")) 
	    		    {
	    				voObj.setStrPAcctStatus("2");
	    			}
	    		    else if (voObj.getStrPresentAcctStatus().equals("Active"))
	    			{
	    				voObj.setStrPAcctStatus("1");
	    			}
	    		    else if (voObj.getStrPresentAcctStatus().equals("On-Hold"))
	    			{
	    				voObj.setStrPAcctStatus("3");
	    			}
	    		    else if (voObj.getStrPresentAcctStatus().equals("Dormant"))
	    			{
	    				voObj.setStrPAcctStatus("4");
	    			}
	    		    else if (voObj.getStrPresentAcctStatus().equals("Request for Close") || voObj.getStrPresentAcctStatus().equals("Approved Request"))
	    			{
	    				voObj.setStrPAcctStatus("5");
	    			}
	    		    else if (voObj.getStrPresentAcctStatus().equals("Canceled"))
	    			{
	    				voObj.setStrPAcctStatus("6");
	    			}
	    			else
	    				voObj.setStrPAcctStatus("0");
	    		  }
					  // Call DAO Insert Update Acct Status
		    		  retVal = IpdBillManagementTransDAO.InsertUpdateAcctStatus(voObj);
					     if(voObj.getStrMsgType().equals("1"))
					     {
							throw new Exception(voObj.getStrMsgString());
						 }	
   			        	 
			  } 
			  catch (Exception e) 
			  {
				// Set Error Msg
				voObj.setStrMsgString("IpdBillManagementTransBO.insertPorcedureForUpdateAcctStatus()--> "+voObj.getStrMsgString());
				voObj.setStrMsgType("1");
    		  }
	  	return retVal;
	 }
	/**
	 * exeProc(vo) -- >
	 * This Method interact with DAO for exeProc
	 * @param ValueObject
	 */	

	
	public boolean exeProc(IpdBillManagementTransVO voObj) 
	 {
		
		  // Declare Variables
		  WebRowSet ws = null;
		  boolean retVal = false;
		    try 
			  {
		    	   // Call DAO method
		      	   IpdBillManagementTransDAO.getAccountDtlWithAcctNo(voObj);
				   ws = voObj.getStrDtlWs();
				   while(ws.next())
				   {
					   voObj.setStrChargeTypeID(ws.getString(1));
					   voObj.setStrPatientCatCode(ws.getString(7));
					  
				   }
				   retVal = true;  
				   // Check Error
				   if(voObj.getStrMsgType().equals("1"))
					{	
				   	   throw new Exception(voObj.getStrMsgString());
				    }
			  } 
			  catch (Exception e) {
				// Set Error Msg
				voObj.setStrMsgString("IpdBillManagementTransBO.exeProc()--> "
								+ e.getMessage());
				voObj.setStrMsgType("1");
				retVal = false;
				
				
			}
			  return retVal;
	 }
	/**
	 * getGroupCombo(vo) -- >
	 * This Method interact with DAO to get Data  for group Name Combo
	 * @param ValueObject
	 */		
	 
	 public void getGroupCombo(IpdBillManagementTransVO voObj) 
	 {	  
			  try 
			    {
				  	voObj.setStrGroupNameCombo(IpdBillManagementTransDAO.GROUPNAMECOMBO(voObj)); 
				   // Check Error
				  	if(voObj.getStrMsgType().equals("1"))
					{	
				   	   throw new Exception(voObj.getStrMsgString());
				    }
			    }
			    catch (Exception e) 
			    {
			    // Set Error Msg
				voObj.setStrMsgString("IpdBillManagementTransBO.getTariffCombo()--> "
								+ e.getMessage());
				voObj.setStrMsgType("1");
    			}
       
	  }	
	 /**
	     * getTariffCombo(vo) -- >
		 * This Method interact with DAO to get Data  for Tariff Combo
		 * @param ValueObject
		 */	
	 
	
	 public void getTariffCombo(IpdBillManagementTransVO voObj) 
	 {	 
			  try 
			    {
				  	voObj.setStrTariffNameCombo(IpdBillManagementTransDAO.TARIFFNAMECOMBO(voObj)); 
				   // Check Error
				  	if(voObj.getStrMsgType().equals("1"))
					{	
				  		throw new Exception(voObj.getStrMsgString());
				    }
			    }
			    catch (Exception e) 
			    {
			    // Set Error Msg
			     voObj.setStrMsgString("IpdBillManagementTransBO.getTariffCombo()--> "
								+ e.getMessage());
				 voObj.setStrMsgType("1");
    			}
       
	  }	
	 

		/**
		 * retrieves Tariff List.
		 * 
		 * @param voObj -
		 *            Value Object
		 */
		public void getTariffCodeDetails(IpdBillManagementTransVO voObj) 
		{
			IpdBillManagementTransDAO.getTariffListByCode(voObj);

			if (voObj.getStrMsgType().equals("1")) 
			{
				String err = "IpdBillManagementTransBO.getTariffCodeDetails()-->"+ voObj.getStrMsgString();
				voObj.setStrMsgString(err);
			}
		}
		
		public void getTariffCodeDetailsDrug(IpdBillManagementTransVO voObj) 
		{
			IpdBillManagementTransDAO.getTariffListByCodeD(voObj);

			if (voObj.getStrMsgType().equals("1")) 
			{
				String err = "IpdBillManagementTransBO.getTariffCodeDetails()-->"+ voObj.getStrMsgString();
				voObj.setStrMsgString(err);
			}
		}
		


		/**
		 * retrieves Tariff List.
		 * 
		 * @param voObj -
		 *            Value Object
		 */
		public void getDefaultTariffDetails(IpdBillManagementTransVO voObj) 
		{
			IpdBillManagementTransDAO.getDefaultTariffList(voObj);

			if (voObj.getStrMsgType().equals("1")) 
			{
				String err = "IpdBillManagementTransBO.getDefaultTariffDetails()-->"+ voObj.getStrMsgString();
				voObj.setStrMsgString(err);
			}
		}
	 
		
		/**
		 * retrieves Tariff List.
		 * 
		 * @param voObj -
		 *            Value Object
		 */
		public void getSpecialTariffDetails(IpdBillManagementTransVO voObj) {
			
			IpdBillManagementTransDAO.getSpecialTariffList(voObj);

			if (voObj.getStrMsgType().equals("1")) {

				String err = "IpdBillManagementTransBO.getSpecialTariffDetails()-->"
						+ voObj.getStrMsgString();

				voObj.setStrMsgString(err);
			}
		}
		
		
		
public void getPreviousDetails(IpdBillManagementTransVO voObj) 
{			
	IpdBillManagementTransDAO.getPreviousDetails(voObj);
	if (voObj.getStrMsgType().equals("1")) 
	{
		String err = "IpdBillManagementTransBO.getPreviousDetails()-->"+ voObj.getStrMsgString();
		voObj.setStrMsgString(err);
	}
}
	 
		
	public void deletePreviousDetails(IpdBillManagementTransVO voObj) {
			
			IpdBillManagementTransDAO.deletPreviousDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {

				String err = "IpdBillManagementTransBO.getPreviousDetails()-->"
						+ voObj.getStrMsgString();

				voObj.setStrMsgString(err);
			}
		}
	 
		
		
	 
		
	 /**
	     * getViewFunc(vo) -- >
		 * This Method interact with DAO to get Data  for View Function
		 * @param ValueObject
		 */	
	 
	 public void getViewFunc(IpdBillManagementTransVO voObj) 
	 {	
		 
			  try 
			    {
				  IpdBillManagementTransDAO.callingFunctionView(voObj); 
				   // Check Error
				  	if(voObj.getStrMsgType().equals("1"))
					{	
				   	   throw new Exception(voObj.getStrMsgString());
				    }
			    }
			    catch (Exception e) 
			    {
			    // Set Error Msg
				voObj.setStrMsgString("IpdBillManagementTransBO.getViewFunc()--> "
								+ e.getMessage());
				voObj.setStrMsgType("1");
    			}
       
	  }	
	 /**
	     * getComboValues(vo) -- >
		 * This Method interact with DAO to get Data  for Group Combo 
		 * @param ValueObject
		 */	
	public void getComboValues(IpdBillManagementTransVO voObj) 
	{
		 
		  try 
			  {
				     voObj.setStrHospitalServiceCombo(IpdBillManagementTransDAO.HOSPITALSERVICECOMBO(voObj));
				  // Check Error
				     if(voObj.getStrMsgType().equals("1"))
					 {	
					   	   throw new Exception(voObj.getStrMsgString());
					 }
				     voObj.setStrPatientCategoryCombo(IpdBillManagementTransDAO.PATIENTCATEOGRYCOMBO(voObj));
				  // Check Error
				     if(voObj.getStrMsgType().equals("1"))
					 {	
					   	   throw new Exception(voObj.getStrMsgString());
					 }
				     voObj.setStrWardnameCombo(IpdBillManagementTransDAO.WARDNAMECOMBO(voObj));
				  // Check Error
				     if(voObj.getStrMsgType().equals("1"))
					 {	
					   	   throw new Exception(voObj.getStrMsgString());
					 }
						  
			    }
			   catch (Exception e)
			   {
				// Set Error Msg
				voObj.setStrMsgString("IpdBillManagementTransBO.getComboValues()--> "
								+ e.getMessage());
				voObj.setStrMsgType("1");
     			}

	 }
	
	
	

	 public void getAccountDtls(IpdBillManagementTransVO voObj) 
	 {
		  try 
	      {   
			  IpdBillManagementTransDAO.getAccountDtl(voObj);			 
		       if(voObj.getStrMsgType().equals("1"))
			   {	
			   	   throw new Exception(voObj.getStrMsgString());
			   }
		  }
		  catch (Exception e) 
		  {
			  e.printStackTrace();
			  voObj.setStrMsgString("IpdBillManagementTransBO.getAccountDtls()--> "+ e.getMessage());
			  voObj.setStrMsgType("1");
		  }      
	  }	
	
	
	 /**
     * getTreatmentCatAndWardTypeValues(vo) -- >
	 * This Method interact with DAO to get Data  for Group Combo 
	 * @param ValueObject
	 */	
public void getTreatmentCatAndWardTypeValues(IpdBillManagementTransVO voObj) 
{
	 
	  try 
	  {
		  IpdBillManagementTransDAO.getTreatmentCategoryList(voObj);
		  
		  if(new BillConfigUtil(voObj.getStrHospitalCode()).getIpdGenAdtProcessType().equals("1"))
		  {			  
			  IpdBillManagementTransDAO.getChargeTypeName(voObj);			  
		  }
		  else
		  {			  
			  IpdBillManagementTransDAO.getWardTypes(voObj);
			  IpdBillManagementTransDAO.getDepartmentList(voObj);
			  IpdBillManagementTransDAO.getPreviousDatesList(voObj);
		  }						  
		}
		catch (Exception e)
		{
			voObj.setStrMsgString("IpdBillManagementTransBO.getTreatmentCatAndWardTypeValues()--> "+ e.getMessage());
			voObj.setStrMsgType("1");
 		}

 }
	
	
	/**
     * GrpOfTariffCombo(vo) -- >
	 * This Method interact with DAO to get Data  for Group Tariff Combo 
	 * @param ValueObject
	 */	
	
	 public void GrpOfTariffCombo(IpdBillManagementTransVO voObj) 
	 {
		  try 
	      {   
			  IpdBillManagementTransDAO.getOffLineGroup(voObj);
			//  IpdBillManagementTransDAO.getOffLineTariffList(voObj);
			  IpdBillManagementTransDAO.getOffLineDiscountApprovalList(voObj);
			  IpdBillManagementTransDAO.getOffLineDiscountRemarksList(voObj);
			 // IpdBillManagementTransDAO.getOffLineTariffUnitList(voObj);
		       // Check Error
		       if(voObj.getStrMsgType().equals("1"))
			   {	
			   	   throw new Exception(voObj.getStrMsgString());
			   }
		  }
		  catch (Exception e) 
		  {
			  e.printStackTrace();
			  
    			// Set Error Msg
		    	voObj.setStrMsgString("IpdBillManagementTransBO.GrpOfTariffCombo()--> "
								+ e.getMessage());
				voObj.setStrMsgType("1");

			}
       
	  }	
	 /**
	     * TariffUnit(vo) -- >
		 * This Method interact with DAO to get Data  for  Tariff Unit Combo 
		 * @param ValueObject
		 */	
	 
	 public void TariffUnit(IpdBillManagementTransVO voObj) 
	 {
		 
		  try 
	      {   
			 
			  IpdBillManagementTransDAO.getOffLineTariffUnitList(voObj);
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
	 /**
	     * TariffCombo(vo) -- >
		 * This Method interact with DAO to get Data  for  Tariff Combo 
		 * @param ValueObject
		 */	
	 
	 public void TariffCombo(IpdBillManagementTransVO voObj) 
	 {
		 
		  try 
	      {   
			 
			  IpdBillManagementTransDAO.getOffLineTariffList(voObj);
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
	 
	 /**
	     * PartPaymentCombo(vo) -- >
		 * This Method interact with DAO to get Data  for  Part Payment Combo 
		 * @param ValueObject
		 */	
	 
	 public void PartPaymentCombo(IpdBillManagementTransVO voObj) 
	 {
		
		  try 
	      {   
			    voObj.setStrPartPaymentWs(IpdBillManagementTransDAO.PARTPAYMENTCOMBO(voObj));
			    // Check Error
			    if(voObj.getStrMsgType().equals("1"))
				{	
			   	   throw new Exception(voObj.getStrMsgString());
			    }
		  }
		  catch (Exception e) 
		  {
			// Set Error Msg
		    	voObj.setStrMsgString("IpdBillManagementTransBO.PartPaymentCombo()--> "
								+ e.getMessage());
				voObj.setStrMsgType("1");

			}
       
	  }	
	

public void getParticularDtlsList(IpdBillManagementTransVO voObj)
{
	IpdBillManagementTransDAO.getParticularDtlsList(voObj);	 		
	if(voObj.getStrMsgType().equals("1"))
	{	 			
		String strErr = voObj.getStrMsgString();	 			
		voObj.setStrMsgString("IpdBillManagementTransBO.getParticularDtlsList()--> "+ strErr);	 			
	}	 		
}
	 	public static String getBillReopenFlag(IpdBillManagementTransVO vo) 
		 {
	 		String strReopenFlag="";
			
				  try
				  {		
					
					  strReopenFlag=IpdBillManagementTransDAO.getBillReopenFlag(vo);
					     // Check Error
					     if(vo.getStrMsgType().equals("1"))	
						 {
					       throw new Exception(vo.getStrMsgString());
						 }
					 
				    }
				    catch (Exception e)
				    {
	                
					vo.setStrMsgString("IpdBillManagementTransBO.getBillReopenFlag() --> "
									 + e.getMessage());
					vo.setStrMsgType("1");

		 		}
        return strReopenFlag;
		  }
		
            public void getAccDetailIpd(IpdBillManagementTransVO voObj){
	 		

	 		IpdBillManagementTransDAO.getAccDetailsIpd(voObj);
	 		
	 		
	 		if(voObj.getStrMsgType().equals("1")){
	 			
	 			String strErr = voObj.getStrMsgString();
	 			
	 			voObj.setStrMsgString("IpdBillManagementTransBO.getParticularDtlsList()--> "
						+ strErr);
	 			
	 		}
	 		
	 	
	 		
	 	}
            
public void getAllTrfList(IpdBillManagementTransVO voObj)
{
	IpdBillManagementTransDAO.getAllTrfList(voObj);	 		
	if(voObj.getStrMsgType().equals("1"))
	{	 			
		String strErr = voObj.getStrMsgString();	 			
		voObj.setStrMsgString("IpdBillManagementTransBO.getAllTrfList()--> "+ strErr);	 			
	}	 		
}
public void getPrevBedTransfers(IpdBillManagementTransVO voObj)
{
	IpdBillManagementTransDAO.getPrevBedTransfers(voObj);	 		
	if(voObj.getStrMsgType().equals("1"))
	{	 			
		String strErr = voObj.getStrMsgString();	 			
		voObj.setStrMsgString("IpdBillManagementTransBO.getPrevBedTransfers()--> "+ strErr);	 			
	}	 		
}

public boolean InsertBedTransfer(IpdBillManagementTransVO voObj) 
{
	boolean retVal = false;
	try 
	{
        retVal = IpdBillManagementTransDAO.InsertBedTransfer(voObj);
        if(voObj.getStrMsgType().equals("1"))	
	    {
	       throw new Exception(voObj.getStrMsgString());
	    }				     
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
		voObj.setStrMsgString("IpdBillManagementTransBO.InsertBedTransfer()--> "+ e.getMessage());
		voObj.setStrMsgType("1");
		retVal = false;
	}
  	return retVal;
 }

public void admissionList(IpdBillManagementTransVO vo)
{
	try
	{
		IpdBillManagementTransDAO.admissionList(vo);
		if(vo.getStrMsgType().equals("1"))
		{
			vo.setStrMsgString("IpdBillManagementTransBO.admissionList---->"+vo.getStrMsgString());
			vo.setStrMsgType("1");
		}
	}
	catch(Exception e)
	{
		vo.setStrMsgString("IpdBillManagementTransBO.admissionList---->"+e.getMessage());
		vo.setStrMsgType("1");
	}
}
public void insertNoDuesPrint(IpdBillManagementTransVO vo)
{
	try
	{
		IpdBillManagementTransDAO.InsertNoDuesPrint(vo);
		if(vo.getStrMsgType().equals("1"))
		{
			vo.setStrMsgString("IpdBillManagementTransBO.admissionList---->"+vo.getStrMsgString());
			vo.setStrMsgType("1");
		}
	}
	catch(Exception e)
	{
		vo.setStrMsgString("IpdBillManagementTransBO.admissionList---->"+e.getMessage());
		vo.setStrMsgType("1");
	}

    }
}

