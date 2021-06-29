package billing.transactions;

import ipd.transactions.NursingDeskTransDAO;
import ipd.transactions.NursingDeskTransVO;
import hisglobal.vo.UserVO;



/*
 * COMPULSORY CHARGES BY CONSULTANT
 * 
 * author:Manisha Gangwar
 * 
 * dated:23rd Jan 2019
 */
public class CompulsoryChargesByConsultantBO {

	public void getPatientDtls(CompulsoryChargesByConsultantVO voObj) {

		try {

			CompulsoryChargesByConsultantDAO.getPatientDtls(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				voObj.setStrMsgString("IpdBillManagementTransNewBO."
						+ "getPatientDtls() --> " + voObj.getStrMsgString());
			}
		} catch (Exception e) {
			voObj.setStrMsgString("IpdBillManagementTransNewBO.getPatientDtls() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");
		} 

	}
	
	
	
	public void getCompulsoryTariffCombo(IpdBillManagementTransVO voObj) 
	 {	 
			  try 
			    {
				  	voObj.setStrTariffNameCombo(CompulsoryChargesByConsultantDAO.getCompulsoryTariffCombo(voObj)); 
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
	
	
	
	
	
	public void getDefaultCompulsoryTariff(CompulsoryChargesByConsultantVO voObj) 
	 {	 
			  try 
			    {
				  	CompulsoryChargesByConsultantDAO.getDefaultCompulsoryTariff(voObj); 
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
	
	
	public boolean insertPorcedure(CompulsoryChargesByConsultantVO voObj,UserVO userVO) 
	 {
 	   // Declaring Variables 	
		//  WebRowSet ws = null;
		  boolean retVal = false;
		  
		    try 
			  {
		           // Call DAO insert method
		    	   retVal = CompulsoryChargesByConsultantDAO.InsertCompChargesByConsultantDtl(voObj, userVO);
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
	
	
	
	public void getWardOnBasisOfUnitCode(CompulsoryChargesByConsultantVO VO) {

	 {

		 CompulsoryChargesByConsultantDAO.getWardOnBasisOfUnitCode(VO);
			// if there is error
			if (VO.getStrMsgType().equals("1")) {

				String strMsg = VO.getStrMsgString();

				VO.setStrMsgString(" NursingDeskTransBO.ward() --> " + strMsg);
			}
		}


	}
	
}
