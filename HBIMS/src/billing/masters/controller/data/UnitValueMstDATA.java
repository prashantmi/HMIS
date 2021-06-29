package billing.masters.controller.data;
/* Unit Value Master DATA
 * author: Debashis Sardar
 * Created on : 16-Sep-2011
 */
import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.masters.bo.UnitValueMstBO;
import billing.masters.controller.fb.UnitValueMstFB;
import billing.masters.vo.UnitValueMstVO;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

public class UnitValueMstDATA {
	/**
	 * Initializes the BO and VO and transfers control to BO for Modification
	 * 
	 * @param fb
	 *  @param chk
	 * @return 
	 */
	public void modifyRecord(String chk1, UnitValueMstFB fb) throws Exception {
		UnitValueMstVO vo = null;
		try {
			vo = new UnitValueMstVO();
			vo = (UnitValueMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.UnitValueMstVO", fb);
			
			UnitValueMstBO.modifyQuery(chk1, vo);
			
			fb.setStrmoduleName(vo.getStrmoduleName());
			fb.setStrfromUnit(vo.getStrfromUnit());
			fb.setStrtoUnit(vo.getStrtoUnit());
			fb.setStrconvertedValue(vo.getStrconvertedValue());
			fb.setStreffectiveFrom(vo.getStreffectiveFrom());
			fb.setStrSeatId(vo.getStrSeatId());
			fb.setStrremark(vo.getStrremark());
			fb.setStrisValid(vo.getStrisValid());
			
		} catch (Exception e) {
			throw new Exception("UnitValueMstDATA.modifyRecord() -->"
					+ e.getMessage());
		}

	}

	/**
	 * Initializes the BO and VO and transfers control to BO for Updation
	 * 
	 * @param fb
	 *  @param chk
	 * @return String
	 */
	public String updateRecord(String chk1, UnitValueMstFB fb) {
		String target = "";
		String strmsgText = null;
		boolean retvalue;
		UnitValueMstVO vo = null;
		try {
			
			vo = new UnitValueMstVO();
			vo = (UnitValueMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.UnitValueMstVO", fb);
			retvalue = UnitValueMstBO.updateQuery(chk1, vo);
			if (!retvalue) {
				String strErrorMsg = "Record not modified successfully!";
				fb.setStrErrorMsg(strErrorMsg);
				target = "modify";
			} else {

				target = "list";

			}

		} catch (Exception e) {

			strmsgText = "billing.masters.UnitValueMstDATA.updateRecord(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"UnitValueMaster->updateRecord()", strmsgText);
			fb.setStrErrorMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		}

		return target;

	}

	/**
	 * Initializes the BO and VO and transfers control to BO for insertion
	 * 
	 * @param fb
	 * @return String
	 */

	public String InsertRecord(UnitValueMstFB fb) {
		String target = "add";
		boolean retvalue = true;
		String strErrorMsg = "";
		String strmsgText = "";
		String strMsg = "";
		
		UnitValueMstVO vo = null;
		try {
			vo = new UnitValueMstVO();
			vo = (UnitValueMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.UnitValueMstVO", fb);
			retvalue = this.initialAdd(fb); // check for duplicate record
			if (!retvalue) {
				strErrorMsg = "Record already exist!";
				fb.setStrWarning(strErrorMsg);
				target = "add";
			} else

			{
				retvalue = UnitValueMstBO.insertQuery(vo);
				if (!retvalue) {
					strErrorMsg = "Record not saved successfully!";
					fb.setStrErrorMsg(strErrorMsg);
					target = "add";
				} else {
					strMsg = "Record Saved Successfully!";
					fb.setStrMsg(strMsg);
					target = "add";
				}
			}
		} catch (Exception e) {
			retvalue = false;
			strmsgText = "billing.masters.UnitValueMstDATA.InsertRecord(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"UnitValueMaster->InsertRecord()", strmsgText);
			fb.setStrErrorMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		}
		return target;
	}

	/**
	 * to check for duplicate record while adding
	 * 
	 * @param fb
	 * @return boolean
	 */

	public boolean initialAdd(UnitValueMstFB fb) 
	{
		boolean retvalue = true;
		String strmsgText = null;
		UnitValueMstVO vo = null;
		try {
			vo = new UnitValueMstVO();
			vo = (UnitValueMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.UnitValueMstVO", fb);
			retvalue = UnitValueMstBO.initialAddQuery(vo);

		} catch (Exception e) {
			strmsgText = "billing.masters.UnitValueMstDATA.initialAdd(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"UnitValueMaster->initialAdd()", strmsgText);
			fb.setStrErrorMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		}
		return retvalue;

	}
	/**
	 * for fromunit combo 
	 * @param fb
	 * @return string
	 */
	
	public String getCmbval1(UnitValueMstFB fb) {
		String errMsg = "";
		String val1 = fb.getStrmoduleName();

		String comboValues = "<option value='0'> Select Value </option>";
		HisUtil hisUtil = new HisUtil("Unit Value Master", "CNTUnitValueMst");

		try {
			String qry = billing.qryHandler_billing.getQuery(1,
					"select.unitvalue.3");

			int qryIndex;
			HisDAO dao = null;

			dao = new HisDAO("Billing", "DAOUnitValueMst");

			qryIndex = dao.setQuery(qry);

			dao.setQryValue(qryIndex, 1, "100");
			dao.setQryValue(qryIndex, 2, val1);
			WebRowSet wb = dao.executeQry(qryIndex);

			if (val1 == null)
				val1 = "0";
			comboValues = hisUtil.getOptionValue(wb, "0", "0^Select Value",
					false);

			hisUtil = null;

		} catch (Exception e) {
			errMsg = "billing.UnitValueMstDATA() -->" + e.getMessage();
			new HisException("billing", "UnitValueMstDATA().getCmbval1()", errMsg);

		}

		return comboValues;
	}
	/**
	 * for tounit combo 
	 * @param fb
	 * @return string
	 */
	public String getCmbval2(UnitValueMstFB fb) {
		String errMsg = "";
		String val = fb.getStrfromUnit();
		String val1 = fb.getStrmoduleName();

		String qry = new String();

		String comboValues = "<option value='0'> Select Value </option>";
		HisUtil hisUtil = new HisUtil("Unit Value Master", "CNTUnitValueMst");
		try {
			qry = billing.qryHandler_billing.getQuery(1, "select.unitvalue.4");

			comboValues = hisUtil.getOptionValue(qry.replace("?", val), "0",
					"0^Select Value");

			int qryIndex;
			HisDAO dao = null;

			dao = new HisDAO("Billing", "DAOUnitValueMst");

			qryIndex = dao.setQuery(qry);

			dao.setQryValue(qryIndex, 1, "100");
			dao.setQryValue(qryIndex, 2, val1);
			dao.setQryValue(qryIndex, 3, val);

			WebRowSet wb = dao.executeQry(qryIndex);

			if (val1 == null)
				val1 = "0";
			comboValues = hisUtil.getOptionValue(wb, "0", "0^Select Value",
					false);

			hisUtil = null;

		} catch (Exception e) {
			errMsg = "billing.UnitValueMstDATA() -->" + e.getMessage();
			new HisException("billing", "UnitValueMstDATA().getCmbval2()", errMsg);

		}

		return comboValues;
	}
	/**
	 * for getting combo on add page
	 * @param fb
	 * @return string
	 */
	public String getStrCmbval(UnitValueMstFB fb) {
		String cmbstr = "";
		HisUtil hisutil = new HisUtil("Billing", "VOUnitValueMst");

	   String hospCode=BillConfigUtil.SUPER_HOSPITAL_CODE ;
		String qry = billing.qryHandler_billing.getQuery(1,
				"select.unitvalue.1").replace("?",
				"" + hospCode + "");
		qry=qry.concat(" AND "+ billing.qryHandler_billing.getQuery(1,
				"select.unitValMst.cond.2").replace("?",
				"" + fb.getStrModuleId() + ""));

	
		HisDAO dao = null;

		try 
		{
			dao = new HisDAO("Billing", "DAOUnitValueMst");
			dao.setQuery(qry);

			cmbstr = hisutil.getOptionValue(qry, "0", "0^Select Value");
		}
		catch (Exception e) 
		{

		}

		return cmbstr;
	}
}
