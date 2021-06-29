/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: Client Master DATA
 * 
 * Created on: 26-08-2011
 */
            
package billing.masters.controller.data;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.masters.bo.ClientMstBO;
import billing.masters.controller.fb.ClientMstFB;
import billing.masters.controller.fb.GroupMstFB;
import billing.masters.vo.ClientMstVO;

public class ClientMstDATA{
		
	@SuppressWarnings("finally")
	/**
	 * Initializes the BO and VO and transfers control to BO for insertion
	 * 
	 * @param fb
	 * @param request
	 * @return boolean 
	 * 'true' if data is inserted otherwise 'false'
	 */
	
	public void insertRecord(HttpServletRequest request,ClientMstFB fb) {
		 
		// String target;
		boolean rval=false;
		ClientMstBO bo = null;
		ClientMstVO voObj = null;

		try {

			
			bo = new ClientMstBO();
			voObj = new ClientMstVO();
			voObj = (ClientMstVO) TransferObjectFactory.populateData("billing.masters.vo.ClientMstVO", fb);

			rval=bo.insert(voObj);
			if(rval)
			{
				fb.setStrMsg(voObj.getStrMsg());
				fb.setStrWarning(voObj.getStrMsg());
			}
			else
				fb.setStrWarning(voObj.getStrWarning());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing","ClientMstDATA->insertRecord()", msgStr);
			fb.setStrErr(eObj.getMessage());
			eObj=null;
		} finally {

			bo = null;
			voObj = null;
			
		}
		
	}
	
	/**
	 * Initializes the BO and VO and transfers control to BO for Modification
	 * 
	 * @param request
	 * @param chk
	 * @param fb
	 */
	
	public void modifyRecord(String chk,ClientMstFB fb) {
		
		ClientMstBO bo = null;
		ClientMstVO voObj = null;

		try {

			
			bo = new ClientMstBO();
			voObj = new ClientMstVO();
			voObj = (ClientMstVO) TransferObjectFactory.populateData("billing.masters.vo.ClientMstVO", fb);
			
			bo.modify(chk,voObj);
			TransferObjectFactory.populateData(fb, voObj);
			
		} catch (Exception e) {
			e.printStackTrace();
			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing","ClientMstDATA->error in modify", msgStr);
			fb.setStrErr(eObj.getMessage());
			eObj=null;
		} finally {

			bo = null;
			voObj = null;

		}
	}

	/**
	 * Initializes the BO and voObj and transfers control to BO for updation
	 * 
	 * @param request
	 * @param fb
	 * @return boolean 
	 * 'true' if data is updated otherwise 'false'
	 */
public String updateRecord(String chk,ClientMstFB fb) {
	
		String target="";
		
		ClientMstBO bo = null;
		ClientMstVO voObj = null;
		try {

			
			bo = new ClientMstBO();
			voObj = new ClientMstVO();
			voObj = (ClientMstVO) TransferObjectFactory.populateData("billing.masters.vo.ClientMstVO", fb);
			
			target=bo.update(chk,voObj);
			TransferObjectFactory.populateData(fb, voObj);
			
		} catch (Exception e) {
			e.printStackTrace();
			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing","ClientMstDATA->error in update", msgStr);
			fb.setStrErr(eObj.getMessage());
			eObj=null;
		} finally {

			bo = null;
			voObj= null;

		}
		return target;
	}

//to set strClientTypeId which user selects on List page..
public static ClientMstVO setClientTypeId(HttpServletRequest objRequest)
{
	ClientMstVO clientMstObj = new ClientMstVO();
	
	
	try
	{			
		
		// when you have more than one component with same name then 'getParameterValues' is used to retrieve values..
		// it will return an array of components with same name..
		// here it returns value of the 1st combo i.e. 'clientType' combo selected..
		String comboValue[] = objRequest.getParameterValues("combo");
		clientMstObj.setStrClientTypeId(comboValue[0]);
		clientMstObj=setClientTypeId(clientMstObj);
		
					
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return clientMstObj;
}

public static ClientMstVO setClientTypeId(ClientMstVO vo)
{
	ClientMstVO clientMstVo = null;
	ClientMstBO bo = new ClientMstBO();
	try{
		clientMstVo=bo.setClientTypeId(vo);
	
		if(vo.getStrMsgType().equals("1"))
		{
			vo.setStrMsgString("clientMstDATA.setClientTypeId(vo) --> " + vo.getStrMsgString());
		}
	}
	catch(Exception e){
		e.printStackTrace();
	}
	finally
	{
	}
	return clientMstVo;
}

public static String getCategory() {
	String tempStr = "";
	HisUtil hisutil = new HisUtil("Billing", "ClientMstDATA");
	HisDAO dao = new HisDAO("billing", "ClientMstDATA");
	WebRowSet wb = null;
	
	int index = 0;
	try {
		
		String Query = billing.qryHandler_billing.getQuery(1,"get.category.list");

		index = dao.setQuery(Query);
		wb = dao.executeQry(index);
				
		tempStr = hisutil.getOptionValue(wb, "0", "0^Select Value", false);
		
	} catch (Exception e) {
		new HisException("Billing",
				"ClientMstDATA.getCategory()", e.getMessage());
	}
	
	return tempStr;
}



}
