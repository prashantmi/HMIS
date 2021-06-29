package billing.masters.bo;

import billing.masters.dao.LocalTariffMstDAO;
import billing.masters.vo.LocalTariffMstVO;
import hisglobal.exceptions.HisException;

public class LocalTariffMstBO {

	/*
	 * receive add function from DAO and sends to the controller.
	 */
	public boolean addData(LocalTariffMstVO vo) 
	{
		boolean retValue = false;
		try 
		{
			retValue = LocalTariffMstDAO.insertData(vo);
		} 
		catch (Exception e) 
		{
			HisException eObj = new HisException("Billing","LocalTariffMstBO->addData()", e.getMessage());			
			vo.setErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		}
		return retValue;
	}

	/*
	 * to check uniqueness of data while adding.
	 */
	public boolean chkAddTariffName(LocalTariffMstVO vo) {
		boolean retValue = false;
		try {
			retValue = LocalTariffMstDAO.chkAddUniqueData(vo);
			if (retValue) 
			{
				retValue = addData(vo);
				if (retValue == false)
				{
					vo.setErrMsg("Record Not Saved!");
				}
				else
				{
					vo.setNormalMsg("Record Added Successfully!");
				}
			} 
			else
			{
				vo.setErrMsg("Tariff Name /Code/Global Tariff already exists!");
			}	
		} catch (Exception e) {
			
			HisException eObj = new HisException("Billing",
					"LocalTariffMstBO->chkAddTariffName()", e.getMessage());
			
			
			vo.setErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}
		return retValue;
	}

	/*
	 * to modify data.
	 */
	public boolean toModifyData(LocalTariffMstVO vo, String val) 
	{
		boolean retVal = false;
		String chk = val;
		try 
		{
			retVal = LocalTariffMstDAO.getData(vo, chk);
		} 
		catch (Exception e) 
		{
			HisException eObj = new HisException("Billing","LocalTariffMstBO->getData()", e.getMessage());			
			vo.setErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		}
		return retVal;
	}

	/*
	 * sends modified data to the controller.
	 */
	public boolean modifyData(LocalTariffMstVO vo) 
	{
		boolean retValue = false;
		try 
		{
			retValue = LocalTariffMstDAO.updateData(vo);
		} 
		catch (Exception e) 
		{
			HisException eObj = new HisException("Billing","LocalTariffMstBO->updateData()", e.getMessage());			
			vo.setErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		}		
		return retValue;
	}

	/*
	 * to check uniqueness of data while modifying.
	 */
	public boolean chkModifyTariffName(LocalTariffMstVO vo)
	{
		boolean retValue = false;
		try 
		{
			retValue = LocalTariffMstDAO.chkModifyUniqueData(vo);
			if (retValue) 
			{
				retValue = modifyData(vo);
				if (retValue == false)
					vo.setErrMsg("error on modify page.");
			} else
				vo.setErrMsg("Tariff Name / Code already exists.");
		} catch (Exception e) {
			HisException eObj = new HisException("Billing",
					"LocalTariffMstBO->chkModifyTariffName()", e.getMessage());
			
			
			vo.setErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}
		
		return retValue;
	}
	
	/*public String getMultiRow(LocalTariffMstVO vo) {
		LocalTariffMstHLP hlp = new LocalTariffMstHLP();
		int i=1;
		String multiRow = "";
		try{
		multiRow = 	hlp.getMultiRow(vo,i);
		vo.setMultiRow(multiRow);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return multiRow;
	}*/
}
