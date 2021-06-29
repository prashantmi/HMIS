package billing.masters.controller.data;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import billing.masters.bo.LocalTariffMstBO;
import billing.masters.controller.fb.LocalTariffMstFB;
import billing.masters.controller.hlp.LocalTariffMstHLP;
import billing.masters.vo.LocalTariffMstVO;

public class LocalTariffMstDATA
{
		
	public boolean chkAddTariffName(LocalTariffMstFB fb) 
	{
		boolean retValue = false;
		LocalTariffMstBO bo=null;
		LocalTariffMstVO voObj=null;
		try 
		{
			bo=new LocalTariffMstBO();
			voObj= new LocalTariffMstVO();
			voObj = (LocalTariffMstVO) TransferObjectFactory.populateData("billing.masters.vo.LocalTariffMstVO", fb);
			
			if(fb.getStrIsEstimationFlag().equals("on"))
				voObj.setStrIsEstimationFlag("1");
			else
				voObj.setStrIsEstimationFlag("0");
			
			retValue = bo.chkAddTariffName(voObj);
				if (retValue == false)
				{
					if(voObj.getFlag().equals("1") || voObj.getFlag().equals("1") || voObj.getFlag().equals("3"))
					fb.setErrMsg("Tariff Name /Code/Global Tariff already exists!");
					else
						fb.setErrMsg("Record Not Saved!");
				}
				else
				{				
					fb.setNormalMsg("Record Saved Successfully!");
				}			
		} 
		catch (Exception e) 
		{
			HisException eObj = new HisException("Billing","LocalTariffMstDATA->chkAddTariffName()", e.getMessage());		
			fb.setErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		}
		return retValue;
	}

	public boolean toModifyData(LocalTariffMstFB fb, String val) 
	{
		boolean retVal = false;
		LocalTariffMstBO bo=null;
		LocalTariffMstVO voObj=null;
		
		try 
		{
			bo=new LocalTariffMstBO();
			voObj= new LocalTariffMstVO();
			voObj = (LocalTariffMstVO) TransferObjectFactory.populateData("billing.masters.vo.LocalTariffMstVO", fb);
			retVal=bo.toModifyData(voObj, val);
			TransferObjectFactory.populateData(fb, voObj);	
			fb.setStrIsEstimationFlag(voObj.getStrIsEstimationFlag());
		} 
		catch (Exception e) 
		{
			HisException eObj = new HisException("Billing","LocalTariffMstBO->getData()", e.getMessage());
			fb.setErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		}
		return retVal;
	}
	public String getMultiRow(LocalTariffMstFB fb) {
		String multiRow = "";
		int i=1;
		try{
			LocalTariffMstHLP hlp = new LocalTariffMstHLP();
			try
			{
				multiRow = 	hlp.getMultiRow(fb,i);
				fb.setMultiRow(multiRow);
			}
			catch (Exception e) 
			{
			}
		}
		catch (Exception e) 
		{
		}
		return multiRow;
	}
	public boolean chkModifyTariffName(LocalTariffMstFB fb)
	{
		LocalTariffMstBO bo=null;
		LocalTariffMstVO voObj=null;
		boolean retValue = false;
		try 
		{	
			bo=new LocalTariffMstBO();
			voObj= new LocalTariffMstVO();
			voObj = (LocalTariffMstVO) TransferObjectFactory.populateData("billing.masters.vo.LocalTariffMstVO", fb);
			retValue=bo.chkModifyTariffName(voObj);
			if (retValue == false)
					fb.setErrMsg("Error on Modify Page.");
			 else
				fb.setErrMsg("Tariff Name / Code already exists.");
		} 
		catch (Exception e) 
		{
			HisException eObj = new HisException("Billing","LocalTariffMstDATA->chkModifyTariffName()", e.getMessage());			
			fb.setErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		}
		
		return retValue;
	}
	public String getToDefault(LocalTariffMstVO vo) {
		String str = "";
		try {
			LocalTariffMstFB fb=new LocalTariffMstFB();
			TransferObjectFactory.populateData(fb, vo);
			str = LocalTariffMstHLP.getDefaultData(fb, fb.getStrDataMode());
		} catch (Exception e) {
			new HisException("billing", "VOTariffMst.getDefault()", e
					.getMessage());
		}
		return str;
	}
	public String getMultiRowData(LocalTariffMstVO vo) {
		LocalTariffMstHLP hlp = new LocalTariffMstHLP();
		String data = "";
		LocalTariffMstFB fb=new LocalTariffMstFB();
		TransferObjectFactory.populateData(fb, vo);
		try {
			data = hlp.getMultiRow(fb, 1);
		} catch (Exception e) {
			new HisException("billing", "VOTariffMst.getMultiRowData()", e
					.getMessage());
		}
		return data;
	}

	
}
