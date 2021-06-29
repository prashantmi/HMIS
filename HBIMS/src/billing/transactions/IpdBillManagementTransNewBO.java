package billing.transactions;
/*
 * IPD Bill Management New BO
 * 
 * author:Debashis Sardar
 * 
 * dated:12th Mar 2013
 */
public class IpdBillManagementTransNewBO {

	public void getPatientDtls(IpdBillManagementTransVO voObj) 
	{
		try 
		{
			IpdBillManagementTransNewDAO.getPatientDtls(voObj);

			if (voObj.getStrMsgType().equals("1")) 
			{
				voObj.setStrMsgString("IpdBillManagementTransNewBO."+ "getPatientDtls() --> " + voObj.getStrMsgString());
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("IpdBillManagementTransNewBO.getPatientDtls() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
		} 

	}
	public void getAdmissionDtls(IpdBillManagementTransVO voObj) 
	{
		try 
		{
			IpdBillManagementTransNewDAO.getAdmissionDtls(voObj);

			if (voObj.getStrMsgType().equals("1")) 
			{
				voObj.setStrMsgString("IpdBillManagementTransNewBO."+ "getPatientDtls() --> " + voObj.getStrMsgString());
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("IpdBillManagementTransNewBO.getPatientDtls() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
		} 

	}
}
