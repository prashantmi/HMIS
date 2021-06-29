/**
 * 
 */
package billing.masters.dao;

import hisglobal.transactionmgnt.HisDAO;
import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.masters.vo.*;
import billing.masters.controller.fb.*;
/**
 * Developer : Deepika gaba
 * Process Name : Charge Rule Master
 * Date : 2 Sep 2011
 * Version : 1.0
 * Modify Date : 
 *
 */
public class ChargeRuleMstDAO {
	
	/**
	 * To Get Rule Details for Main Page.
	 * 
	 * @param vo the vo
	 */
	public static void getRuleDetails(ChargeRuleMstVO vo) 
	throws Exception {

		HisDAO dao = null;
		int nqryIndex;
		String strquery = "";
		WebRowSet wb = null;

		try {
			dao = new HisDAO("billing", "ChargeRuleMstDAO");

			strquery = billing.qryHandler_billing.getQuery(1,
					"select.ChargeRuleMst.0");
			nqryIndex = dao.setQuery(strquery);
			
			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			
			wb = dao.executeQry(nqryIndex);
			
			vo.setRuleDetails(wb);
			
		} catch (Exception e) {
			throw new Exception("Billing.ChargeRuleMstDAO.getRuleDetails --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
	}
	
	/**
	 * To Get Hospital Service for Rule For (New Charge Type).
	 * 
	 * @param vo the vo
	 */
	public static void getNewChargeType(ChargeRuleMstVO vo) 
	throws Exception {

		HisDAO dao = null;
		int nqryIndex;
		String strquery = "";
		WebRowSet wb = null;

		try {
			dao = new HisDAO("billing", "ChargeRuleMstDAO");

			strquery = billing.qryHandler_billing.getQuery(1,"select.ChargeRuleMst.HospServ.1");
			nqryIndex = dao.setQuery(strquery);
			
			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			
			wb = dao.executeQry(nqryIndex);
			
			vo.setNewChargeTypeWS(wb);
			
		} catch (Exception e) {
			throw new Exception("Billing.ChargeRuleMstDAO.getNewChargeType --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
	}
	
	/**
	 * To Get Hospital Service for Rule With (Old Charge Type).
	 * 
	 * @param vo the vo
	 */
	public static void getOldChargeType(ChargeRuleMstVO vo) 
	throws Exception {

		HisDAO dao = null;
		int nqryIndex;
		String strquery = "";
		WebRowSet wb = null;

		try {
			dao = new HisDAO("billing", "ChargeRuleMstDAO");

			strquery = billing.qryHandler_billing.getQuery(1,
					"select.ChargeRuleMst.HospServ.1");
			nqryIndex = dao.setQuery(strquery);
			
			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			
			wb = dao.executeQry(nqryIndex);
			
			vo.setOldChargeTypeWS(wb);
			
		} catch (Exception e) {
			throw new Exception("Billing.ChargeRuleMstDAO.getNewChargeType --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
	}
	
	/**
	 * To Get Ward Type Combo for Rule For (New IPD Charge Type) on the basis of Hospital service.
	 * 
	 * @param vo the vo
	 */
	public static void getNewWardTypeValues(ChargeRuleMstVO vo) 
	throws Exception {

		HisDAO dao = null;
		int nqryIndex;
		String strquery = "";
		WebRowSet wb = null;

		try {
			dao = new HisDAO("billing", "ChargeRuleMstDAO");

			strquery = billing.qryHandler_billing.getQuery(1,
					"select.ChargeRuleMst.WardType.1");
			nqryIndex = dao.setQuery(strquery);
			
			dao.setQryValue(nqryIndex, 1, vo.getStrNewChargeTypeId());
			dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
			
			wb = dao.executeQry(nqryIndex);
			
			vo.setNewIPDChargeTypeWS(wb);
			
			
			
		} catch (Exception e) {
			throw new Exception("Billing.ChargeRuleMstDAO.getNewWardTypeValues --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
	}
	
	/**
	 * To Get Ward Type Combo for Rule For (New IPD Charge Type) on the basis of Hospital service.
	 * 
	 * @param vo the vo
	 */
	public static void getOldWardTypeValues(ChargeRuleMstVO vo) 
	throws Exception {

		HisDAO dao = null;
		int nqryIndex;
		String strquery = "";
		WebRowSet wb = null;

		try {
			dao = new HisDAO("billing", "ChargeRuleMstDAO");

			strquery = billing.qryHandler_billing.getQuery(1,
					"select.ChargeRuleMst.WardType.1");
			nqryIndex = dao.setQuery(strquery);
			
			
			dao.setQryValue(nqryIndex, 1, vo.getStrOldChargeTypeId());
			dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
			
			wb = dao.executeQry(nqryIndex);
			
			vo.setOldIPDChargeTypeWS(wb);
			
		} catch (Exception e) {
			throw new Exception("Billing.ChargeRuleMstDAO.getOldWardTypeValues --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
	}
	
	/**
	 * To Get Ward Type Combo for Rule For (New IPD Charge Type) on the basis of Hospital service.
	 * 
	 * @param vo the vo
	 */
	public static void getNewPatCatValues(ChargeRuleMstVO vo) throws Exception 
	{
		HisDAO dao = null;
		int nqryIndex;
		String strquery = "";
		WebRowSet wb = null;

		try 
		{
			dao = new HisDAO("billing", "ChargeRuleMstDAO");

			strquery = billing.qryHandler_billing.getQuery(1,"select.ChargeRuleMst.NewPatCat.1");
			nqryIndex = dao.setQuery(strquery);
			
			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			//dao.setQryValue(nqryIndex, 1, BillConfigUtil.SUPER_HOSPITAL_CODE);
			dao.setQryValue(nqryIndex, 2, vo.getStrNewChargeTypeId());
			
			if(vo.getStrNewChargeTypeId().equals("2"))
			{
			   dao.setQryValue(nqryIndex, 3, vo.getStrNewIPDChargeTypeId());
			}
			else
			{
				dao.setQryValue(nqryIndex, 3, "0");
			}			
			
			wb = dao.executeQry(nqryIndex);			
			vo.setNewPatCatWS(wb);			
		} 
		catch (Exception e) 
		{
			throw new Exception("Billing.ChargeRuleMstDAO.getNewPatCatValues --> "+ e.getMessage());
		} 
		finally 
		{
			dao.free();
			dao = null;
		}
	}
	
	/**
	 * To Get Ward Type Combo for Rule For (New IPD Charge Type) on the basis of Hospital service.
	 * 
	 * @param vo the vo
	 */
	public static void getOldPatCatValues(ChargeRuleMstVO vo)  throws Exception 
	{
		HisDAO dao = null;
		int nqryIndex;
		String strquery = "";
		WebRowSet wb = null;

		try 
		{
			dao = new HisDAO("billing", "ChargeRuleMstDAO");
			strquery = billing.qryHandler_billing.getQuery(1,"select.ChargeRuleMst.OldPatCat.1");
			nqryIndex = dao.setQuery(strquery);
			
			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			//dao.setQryValue(nqryIndex, 1, BillConfigUtil.SUPER_HOSPITAL_CODE);
			/*dao.setQryValue(nqryIndex, 2, vo.getStrOldChargeTypeId());
			
			if(vo.getStrOldChargeTypeId().equals("2"))
			{
			   dao.setQryValue(nqryIndex, 3, vo.getStrOldIPDChargeTypeId());
			}
			else
			{
				dao.setQryValue(nqryIndex, 3, "0");
			}*/
			
			wb = dao.executeQry(nqryIndex);			
			vo.setOldPatCatWS(wb);			
		} 
		catch (Exception e) 
		{
			throw new Exception("Billing.ChargeRuleMstDAO.getOldPatCatValues --> "+ e.getMessage());
		} 
		finally 
		{
			dao.free();
			dao = null;
		}
	}
	
	public static boolean insertRecord(ChargeRuleMstVO vo) throws Exception {
		
		HisDAO dao = null;
		int qryIndex;
		boolean retvalue = true;
		String query = new String();
		try {
			dao = new HisDAO("Billing", "ChargeRuleMstDAO");
			
			query = billing.qryHandler_billing.getQuery(1, "insert.ChargeRuleMst.1");
			
			qryIndex = dao.setQuery(query);

		
			dao.setQryValue(qryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(qryIndex, 2, vo.getStrHospitalCode());
			dao.setQryValue(qryIndex, 3, vo.getStrRuleName());
			dao.setQryValue(qryIndex, 4, vo.getStrNewChargeTypeId());
			dao.setQryValue(qryIndex, 5, vo.getStrNewPatientCatcode());
			if(vo.getStrNewChargeTypeId().equals("2")){
			    dao.setQryValue(qryIndex, 6, vo.getStrNewIPDChargeTypeId());
			}
			else
			{
				dao.setQryValue(qryIndex, 6, "0");
			}
			dao.setQryValue(qryIndex, 7, vo.getStrOldChargeTypeId());
			dao.setQryValue(qryIndex, 8, vo.getStrOldPatientCatcode());
			if(vo.getStrOldChargeTypeId().equals("2")){
				dao.setQryValue(qryIndex, 9, vo.getStrOldIPDChargeTypeId());
			}
			else
			{
				dao.setQryValue(qryIndex, 9, "0");
			}
			
			dao.setQryValue(qryIndex, 10, vo.getStrVariation());
			dao.setQryValue(qryIndex, 11, vo.getStrRemarks());
			dao.setQryValue(qryIndex, 12, vo.getStrEffectiveFrom());
			dao.setQryValue(qryIndex, 13, vo.getStrSeatId());
		    dao.setQryValue(qryIndex, 14, vo.getStrIsValid());

			dao.execute(qryIndex, 0);

			synchronized (dao) {
				dao.fire();
			}
			
			retvalue = true;
		} catch (Exception e) {
			
	 
			
			retvalue = false;

			throw new Exception("billing.ChargeRuleMstDAO.insertQuery"
					+ e.getMessage());

		} finally {
			dao.free();
			dao = null;
		}
		return retvalue;

	}
	
public static boolean CorrectRecord(ChargeRuleMstVO vo) throws Exception {
		
		HisDAO dao = null;
		int qryIndex;
		boolean retvalue = true;
		String query = new String();
		try {
			dao = new HisDAO("Billing", "ChargeRuleMstDAO");
			
			query = billing.qryHandler_billing.getQuery(1, "update.ChargeRuleMst.Correction.1");
			
			qryIndex = dao.setQuery(query);

			dao.setQryValue(qryIndex, 1, vo.getStrVariation());
			dao.setQryValue(qryIndex, 2, vo.getStrRuleName());
			dao.setQryValue(qryIndex, 3, vo.getStrRemarks());
			dao.setQryValue(qryIndex, 4, vo.getStrHidRuleId());
			dao.setQryValue(qryIndex, 5, vo.getStrHospitalCode());
			
			dao.execute(qryIndex, 0);

			synchronized (dao) {
				dao.fire();
			}
			
			retvalue = true;
		} catch (Exception e) {
			 
			retvalue = false;

			throw new Exception("billing.ChargeRuleMstDAO.CorrectRecord"
					+ e.getMessage());

		} finally {
			dao.free();
			dao = null;
		}
		return retvalue;

	}

public static boolean ModifyRecord(ChargeRuleMstVO vo) throws Exception {
	
	HisDAO dao = null;
	int qryIndex;
	boolean retvalue = true;
	String query = new String();
	/*String query1 = new String();
	int qryIndex1;*/
	try {
		dao = new HisDAO("Billing", "ChargeRuleMstDAO");
		
		query = billing.qryHandler_billing.getQuery(1, "update.ChargeRuleMst.Modification.1");
		
		qryIndex = dao.setQuery(query);

		dao.setQryValue(qryIndex, 1, vo.getStrRemarks());
		dao.setQryValue(qryIndex, 2, vo.getStrEffectiveFrom());
		dao.setQryValue(qryIndex, 3, vo.getStrLastModSeatId());
		dao.setQryValue(qryIndex, 4, vo.getStrHidRuleId());
		dao.setQryValue(qryIndex, 5, vo.getStrHospitalCode());
		
		dao.execute(qryIndex, 0);
		
		vo.setStrNewPatientCatcode(vo.getStrHNewPatientCatcode());
		vo.setStrNewIPDChargeTypeId(vo.getStrHNewIPDChargeTypeId());
		vo.setStrOldIPDChargeTypeId(vo.getStrHOldIPDChargeTypeId());
		vo.setStrOldPatientCatcode(vo.getStrHOldPatientCatcode());
		
		ChargeRuleMstDAO.insertRecord(vo);
		
		/*query1 = billing.qryHandler_billing.getQuery(1, "insert.ChargeRuleMst.1");
		
		qryIndex1 = dao.setQuery(query1);

	
		dao.setQryValue(qryIndex1, 1, vo.getStrHospitalCode());
		dao.setQryValue(qryIndex1, 2, vo.getStrHospitalCode());
		dao.setQryValue(qryIndex1, 3, vo.getStrRuleName());
		dao.setQryValue(qryIndex1, 4, vo.getStrNewChargeTypeId());
		dao.setQryValue(qryIndex1, 5, vo.getStrNewPatientCatcode());
		dao.setQryValue(qryIndex1, 6, vo.getStrNewIPDChargeTypeId());
		dao.setQryValue(qryIndex1, 7, vo.getStrOldChargeTypeId());
		dao.setQryValue(qryIndex1, 8, vo.getStrOldPatientCatcode());
		dao.setQryValue(qryIndex1, 9, vo.getStrOldIPDChargeTypeId());
		dao.setQryValue(qryIndex1, 10, vo.getStrVariation());
		dao.setQryValue(qryIndex1, 11, vo.getStrRemarks());
		dao.setQryValue(qryIndex1, 12, vo.getStrEffectiveFrom());
		dao.setQryValue(qryIndex1, 13, vo.getStrSeatId());
	    dao.setQryValue(qryIndex1, 14, vo.getStrIsValid());

		dao.execute(qryIndex1, 0);*/

		synchronized (dao) {
			dao.fire();
		}
		
		retvalue = true;
	} catch (Exception e) {
		 
		retvalue = false;

		throw new Exception("billing.ChargeRuleMstDAO.CorrectRecord"
				+ e.getMessage());

	} finally {
		dao.free();
		dao = null;
	}
	return retvalue;

}

public static boolean DeleteRecord(ChargeRuleMstVO vo) throws Exception {
	
	HisDAO dao = null;
	int qryIndex;
	boolean retvalue = true;
	String query = new String();
	String query1 = new String();
	int qryIndex1;
	try {
		dao = new HisDAO("Billing", "ChargeRuleMstDAO");
		
		query = billing.qryHandler_billing.getQuery(1, "delete.ChargeRuleMst.0");
		
		qryIndex = dao.setQuery(query);

		/*
		 * UPDATE HBLT_CHARGE_RULE_MST SET GDT_EFFECTIVE_TO = NULL \
						 WHERE GNUM_HOSPITAL_CODE = ? \
						 AND SBLNUM_NEW_CHARGETYPE_ID = ? \
						 AND GNUM_NEW_PATIENT_CAT_CODE = ? \
						 AND SBLNUM_NEW_IPD_CHARGETYPE_ID = ? \
						 AND GDT_EFFECTIVE_TO = TO_DATE(?,'dd-Mon-yyyy') - 1
		 * */
		
		/*System.out.println("vo.getStrNewChargeTypeId()--"+vo.getStrNewChargeTypeId());
		System.out.println("vo.getStrHNewPatientCatcode()--"+vo.getStrHNewPatientCatcode());
		System.out.println("vo.getStrNewIPDChargeTypeId()--"+vo.getStrHNewIPDChargeTypeId());
		System.out.println("vo.getStrEffectiveFrom()--"+vo.getStrEffectiveFrom());
		*/
		dao.setQryValue(qryIndex, 1, vo.getStrEffectiveFrom());
		dao.setQryValue(qryIndex, 2, vo.getStrHospitalCode());
		dao.setQryValue(qryIndex, 3, vo.getStrNewChargeTypeId());
		dao.setQryValue(qryIndex, 4, vo.getStrHNewPatientCatcode());
		dao.setQryValue(qryIndex, 5, vo.getStrHNewIPDChargeTypeId());
		
		
		dao.execute(qryIndex, 0);
		
		
		query1 = billing.qryHandler_billing.getQuery(1, "delete.ChargeRuleMst.1");
		
		qryIndex1 = dao.setQuery(query1);

		/*System.out.println("vo.getStrHidRuleId()--"+vo.getStrHidRuleId());
		System.out.println("vo.getStrHospitalCode()--"+vo.getStrHospitalCode());
		*/
		dao.setQryValue(qryIndex1, 1, vo.getStrHidRuleId());
		dao.setQryValue(qryIndex1, 2, vo.getStrHospitalCode());
	    
		dao.execute(qryIndex1, 0);

		synchronized (dao) {
			dao.fire();
		}
		
		retvalue = true;
	} catch (Exception e) {
		 
		retvalue = false;

		throw new Exception("billing.ChargeRuleMstDAO.CorrectRecord"
				+ e.getMessage());

	} finally {
		dao.free();
		dao = null;
	}
	return retvalue;

}

}
