/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: Consumable Charge Discount Rule Master UTIL
 * 
 * Created on: 08-09-2011
 */
package billing.masters.dao;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import billing.masters.vo.ConsumableChargeDiscountRuleMstVO;


/*
 * Class : ConsumableChargeDiscountRuleMasterDAO
 */

public class ConsumableChargeDiscountRuleMstDAO {
	
	/**
	 * function to insert data into DataBase
	 * 
	 * @param vo_p
	 * @return retvalue (boolean)
	 */
	
	public static boolean insertRecord(ConsumableChargeDiscountRuleMstVO vo_p) throws Exception {
		
		HisDAO dao = null;
		int qryIndex;
		boolean retvalue = true;
		String query = new String();
		try {
			dao = new HisDAO("Billing", "ConsumableChargeDiscountRuleMstDAO");
			
			query = billing.qryHandler_billing.getQuery(1, "insert.ccRuleMst.1");
			
			qryIndex = dao.setQuery(query);
		
			dao.setQryValue(qryIndex, 1, vo_p.getStrHospitalCode());
			dao.setQryValue(qryIndex, 2, vo_p.getStrPatientCatCode());
			dao.setQryValue(qryIndex, 3, vo_p.getStrChargeTypeId());
			if(vo_p.getStrChargeTypeId().equals("2"))
			{
				dao.setQryValue(qryIndex, 4, vo_p.getStrIpdChargeTypeId());
			}
			else
				dao.setQryValue(qryIndex, 4, "0");
			dao.setQryValue(qryIndex, 5, vo_p.getStrHospitalCode());
			dao.setQryValue(qryIndex, 6, vo_p.getStrDiscountAmt());
			dao.setQryValue(qryIndex, 7, vo_p.getStrSeatId());
			dao.setQryValue(qryIndex, 8, "1");
			

			dao.execute(qryIndex, 0);

			synchronized (dao) {
				dao.fire();
			}
			
			retvalue = true;
		} catch (Exception e) {
			
	 
			e.printStackTrace();
			retvalue = false;

			throw new Exception("billing.ConsumableChargeDiscountRuleMstDAO.insertRecord"
					+ e.getMessage());

		} finally {
			dao.free();
			dao = null;
		}
		return retvalue;

	}
	
	/**
	 * function to view the modification data. 
	 * 
	 * @param vo_p, strChk_p
	 * @return boolean retvalue
	 */

	public static boolean ModifyRecord(ConsumableChargeDiscountRuleMstVO vo_p,String strChk_p) throws Exception {
	
	HisDAO dao = null;
	String strQuery = null;
	int nQryIndex;
	String strTemp[] = null;
	String strTemp2[] = null;
	boolean retvalue=false;
	try {

		dao = new HisDAO("Consumable Charge Discount Rule Master", "ConsumableChargeDiscountRuleMstDAO.ModifyRecord()");

		strQuery = billing.qryHandler_billing.getQuery(1,
				"select.ccRuleMst.7");

		strTemp = strChk_p.replace('@', '#').split("#");
		strTemp2 = strTemp[4].replace("$", "#").split("#");
		strTemp[4] = strTemp2[0];
		nQryIndex = dao.setQuery(strQuery);
		dao.setQryValue(nQryIndex, 1, strTemp[0]);
		dao.setQryValue(nQryIndex, 2, strTemp[1]);
		dao.setQryValue(nQryIndex, 3, strTemp[2]);
		dao.setQryValue(nQryIndex, 4, strTemp[3]);
		dao.setQryValue(nQryIndex, 5, strTemp[4]);

		WebRowSet web = dao.executeQry(nQryIndex);

		while (web.next()) {
			retvalue=true;
			vo_p.setStrChargeTypeId(web.getString(1));
			vo_p.setStrIpdChargeTypeId(web.getString(2));
			vo_p.setStrPatientCatCode(web.getString(3));
			vo_p.setStrDiscountAmt(web.getString(4));
			vo_p.setStrIsValid(web.getString(5));

		}
		
		
	} catch (Exception e) {

		new HisException("Consumable Charge Discount Rule Master", "ConsumableChargeDiscountRuleMstDAO.ModifyRecord()", e
				.getMessage());
	} finally {
		dao.free();
		dao = null;
	}
	return retvalue;

}
	
	/**
	 * function to check the duplicacy of insertion data. 
	 * 
	 * @param vo_p
	 * @return boolean rval
	 */

	public boolean chkAddCCRule(ConsumableChargeDiscountRuleMstVO vo_p) throws Exception {
	// TODO Auto-generated method stub
	boolean rval=false;
	HisDAO hisdao=new HisDAO("Billing","ConsumableChargeDiscountRuleMstDAO");
	int index;
	int count=0;
	WebRowSet wb=null;
	String query=billing.qryHandler_billing.getQuery(1, "select.ccRuleMst.5");
	try
	{
		index=hisdao.setQuery(query);
		hisdao.setQryValue(index, 1, vo_p.getStrChargeTypeId());
		if(vo_p.getStrChargeTypeId().equals("2"))
		{
			hisdao.setQryValue(index, 2, vo_p.getStrIpdChargeTypeId());
		}
		else
			hisdao.setQryValue(index, 2, "0");
		
		hisdao.setQryValue(index, 3, vo_p.getStrPatientCatCode());
		
		wb=hisdao.executeQry(index);
		while(wb.next()){
			count=Integer.parseInt(wb.getString(1));
		}
		if(count==0)
			rval=true;
		else rval=false;
	} catch (Exception e) {
		 rval=false;
		throw new Exception("billing.ConsumableChargeDiscountRuleMstDAO.chkAddCCRule"
				+ e.getMessage());

	} finally {
		hisdao.free();
		hisdao = null;
	}
	
	
	return rval;
}
	
	/**
	 * function to update the data. 
	 * 
	 * @param vo_p,strChk_p
	 * @return boolean rval
	 */
	public boolean Update(ConsumableChargeDiscountRuleMstVO vo_p, String strChk_p) throws Exception {
	HisDAO hisdao = null;
	String strTemp[] = null;
	String strTemp2[] = null;
	int qryIndex;
	boolean retvalue = true;
	String query = new String();
	try {
		hisdao = new HisDAO("Billing", "ConsumableChargeDiscountRuleMstDAO");
		strTemp = strChk_p.replace('@', '#').split("#");
		strTemp2 = strTemp[4].replace("$", "#").split("#");
		strTemp[4] = strTemp2[0];
		
		query = billing.qryHandler_billing.getQuery(1, "update.ccRuleMst.1");
		
		qryIndex = hisdao.setQuery(query);
	
		hisdao.setQryValue(qryIndex, 1, vo_p.getStrDiscountAmt());
		
		hisdao.setQryValue(qryIndex, 2, vo_p.getStrLstModSeatId());
		hisdao.setQryValue(qryIndex, 3, vo_p.getStrSeatId());
		hisdao.setQryValue(qryIndex, 4, vo_p.getStrIsValid());
		hisdao.setQryValue(qryIndex, 5, strTemp[0]);
		hisdao.setQryValue(qryIndex, 6, strTemp[1]);
		hisdao.setQryValue(qryIndex, 7, strTemp[2]);
		hisdao.setQryValue(qryIndex, 8, strTemp[3]);
		hisdao.setQryValue(qryIndex, 9, strTemp[4]);
		

		hisdao.execute(qryIndex, 0);

		synchronized (hisdao) {
			hisdao.fire();
		}
		
		retvalue = true;
	} catch (Exception e) {
		
 
		
		retvalue = false;

		throw new Exception("billing.ConsumableChargeDiscountRuleMstDAO.Update"
				+ e.getMessage());

	} finally {
		hisdao.free();
		hisdao = null;
	}
	return retvalue;

	
}

}
