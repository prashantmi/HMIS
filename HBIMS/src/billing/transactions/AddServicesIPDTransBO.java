package billing.transactions;

public class AddServicesIPDTransBO {

	public void initAddService(AddServicesIPDTransVO vo){
		
		AddServicesIPDTransDAO.getOffLineGroup(vo);
		AddServicesIPDTransDAO.getPatientCateogry(vo);
		
		if(!vo.getStrMsgType().equals("0")){
			
			vo.setStrMsgString("AddServicesIPDTransBO.initAddService()-->"+vo.getStrMsgString());
			
		}
		
	}

public void getPreviousDetails(AddServicesIPDTransVO vo){
		
		AddServicesIPDTransDAO.getPreviousAddServices(vo);
				
		if(!vo.getStrMsgType().equals("0")){
			
			vo.setStrMsgString("AddServicesIPDTransBO.getPreviousDetails()-->"+vo.getStrMsgString());
			
		}
		
	}
	


/**
 * TariffCombo(vo) -- >
 * This Method interact with DAO to get Data  for  Tariff Combo 
 * @param ValueObject
 */	

public void getTariffDetails(AddServicesIPDTransVO voObj) 
{
 
  try 
  {   
	 
	  AddServicesIPDTransDAO.getOffLineTariffList(voObj);
   // Check Error
       if(voObj.getStrMsgType().equals("1"))
	   {	
	   	   throw new Exception(voObj.getStrMsgString());
	   }
  }
  catch (Exception e) 
  {
		// Set Error Msg
    	voObj.setStrMsgString("AddServicesIPDTransBO.getTariffDetails()--> "
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
public void getTariffCodeDetails(AddServicesIPDTransVO voObj) {
	AddServicesIPDTransDAO.getTariffListByCode(voObj);

	if (voObj.getStrMsgType().equals("1")) {

		String err = "AddServicesIPDTransBO.getTariffCodeDetails()-->"
				+ voObj.getStrMsgString();

		voObj.setStrMsgString(err);
	}
}



/**
 * retrieves Tariff List.
 * 
 * @param voObj -
 *            Value Object
 */
public void getDefaultTariffDetails(AddServicesIPDTransVO voObj) {
	AddServicesIPDTransDAO.getDefaultTariffList(voObj);

	if (voObj.getStrMsgType().equals("1")) {

		String err = "AddServicesIPDTransBO.getDefaultTariffDetails()-->"
				+ voObj.getStrMsgString();

		voObj.setStrMsgString(err);
	}
}


/**
 * getTariffUnit(vo) -- >
 * This Method interact with DAO to get Data  for  Tariff Unit Combo 
 * @param ValueObject
 */	

public void getTariffUnit(AddServicesIPDTransVO voObj) 
{
 
  try 
  {   
	 
	  AddServicesIPDTransDAO.getTariffUnitList(voObj);
 
       if(voObj.getStrMsgType().equals("1"))
	   {	
	   	   throw new Exception(voObj.getStrMsgString());
	   }
  }
  catch (Exception e) 
  {
		// Set Error Msg
    	voObj.setStrMsgString("AddServicesIPDTransBO.getTariffUnit()--> "
						+ e.getMessage());
		voObj.setStrMsgType("1");

	}

}	



public void getQuantity(AddServicesIPDTransVO vo){
		
		AddServicesIPDTransDAO.getCalculatedQuantity(vo);
				
		if(!vo.getStrMsgType().equals("0")){
			
			vo.setStrMsgString("AddServicesIPDTransBO.getQuantity()-->"+vo.getStrMsgString());
			
		}
		
	}

}
