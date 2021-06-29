/*
 * This is a FormBean Class of Tariff Master.
 * It contains Fields, their Getter Methods and, Setter Methods.
 * This file also contains Combo Functions and a function to retrieve current date.
 */
package billing.masters.controller.fb;

/*import java.util.ArrayList;
import java.util.List;*/

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.masters.controller.hlp.LocalTariffMstHLP;
import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

public class LocalTariffMstFB extends GenericFormBean
{

	private static final long serialVersionUID = 02L;

	private String isPackage = "0";
	private String multiRow = "";
	private String strDataMode = "0";
	private String tariffId = "0";
	private String groupId = "0";
	private String strGroupId = "0";// To persist gp id on modify
	private String trfPkgName = "";
	private String strDiscountLimit = "0";
	private String strDiscountPrivilege = "0";
	private String strUpdateChargeUnit = "0";
	private String defaultUnit = "";
	private String defaultPackUnit = "";
	private String serviceName = "";
	private String lengthOfStay = "";
	private String tariffName = "";
	private String groupName = "";
	private String[] chargeTypeId = null;
	private String[] hospitalService = null;
	private String[] rate = { "0" };
	private String[] actualCost = { "0" };
	private String[] maxDisc = { "0" };
	private String[] serviceTax = { "0" };
	private String effectiveFromDfltDet = "";
	private String[] department = null;
	private String[] share = { "0" };
	private String effectiveFromContbDet = "";
	private String strEffectiveFrom = "";
	private String seatId = "";
	private String isValid = "1";
	private String errMsg = "";
	private String isContribution = "";
	private String remarks = "";
	private int totalRows = 0;
	private String strTotalRows = "";
	private String tempDept = "0";
	private String lstModDate = "";
	private String lstModSeat = "";
	private String trfId = "";
	private String strServiceId = "0";
	private String normalMsg = "";
	private String strHospitalCode = "";
	private String strChoiceNum = "1";
	private String strChK = "";
	private String strIsOpdVisible = "0";
	private String strIsIpdVisible = "0";
	private String strIsEmergVisible = "0";
	private String strIsDefaultForIpd = "0";
	private String strMultiRow = "";
	private String tariffCode = "";
	private String flag = "";
	private String strUndefinedCharges = "0";
	private String strCostType;
	private String globalTariff = "";
	private String strIsEstimationFlag="";
	private String gstrTariffName="";

	
	
	


	public String getStrIsEstimationFlag() {
		return strIsEstimationFlag;
	}

	public void setStrIsEstimationFlag(String strIsEstimationFlag) {
		this.strIsEstimationFlag = strIsEstimationFlag;
	}

	/**
	 * @return the strUndefinedCharges
	 */
	public String getStrUndefinedCharges()
	{
		return strUndefinedCharges;
	}

	/**
	 * @param strUndefinedCharges
	 *           the strUndefinedCharges to set
	 */
	public void setStrUndefinedCharges(String strUndefinedCharges)
	{
		this.strUndefinedCharges = strUndefinedCharges;
	}

	/**
	 * @return the strCostType
	 */
	public String getStrCostType()
	{
		return strCostType;
	}

	/**
	 * @param strCostType
	 *           the strCostType to set
	 */
	public void setStrCostType(String strCostType)
	{
		this.strCostType = strCostType;
	}

	/**
	 * @return the flag
	 */
	public String getFlag()
	{
		return flag;
	}

	/**
	 * @param flag
	 *           the flag to set
	 */
	public void setFlag(String flag)
	{
		this.flag = flag;
	}

	public String getStrHospitalCode()
	{
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode)
	{
		this.strHospitalCode = strHospitalCode;
	}

	/**
	 * @return Returns the addGroupCombo.
	 */
	public String getAddGroupCombo()
	{
		String grpStr = "";
		String errMsg = "";
		String comboValues = "<option value='0'> Select Value </option>";
		HisUtil hisutil = new HisUtil("Billing", "LocalTariffMstFB");
		try
		{
			String hosCode = this.getStrHospitalCode();
			grpStr = billing.qryHandler_billing.getQuery(1, "select.tariffMst.1").replace("?", hosCode);

			// grpStr = grpStr.concat(" and
			// ").concat(billing.qryHandler_billing.getQuery(1,
			// "select.tariffMstGrpCmb.cond.1").replace("?",this.isPackage));

			comboValues = hisutil.getOptionValue(grpStr, this.getGroupId(), "0^Select Value");

		} catch (Exception e)
		{
			errMsg = "Billing.LocalTariffMstFB() -->" + e.getMessage();
			new HisException("billing", "LocalTariffMstFB.getAddGroupCombo()", errMsg);
		}
		return comboValues;
	}

	/**
	 * @return Returns the getAddDefaultUnitComboWithPkg().
	 */
	public String getAddDefaultUnitComboWithPkg()
	{
		String comboValues2 = "<option value='0'>Select Value</option>";
		//HisUtil hisutil = new HisUtil("Billing", "LocalTariffMstFB");
		//String strDefaultUnitId = "";
		//String strquery = "";
		//int nqryIndex = 0;
		WebRowSet wb = null;
		//HisDAO dao = new HisDAO("billing", "LocalTariffMstFB");
		try
		{
			//strDefaultUnitId = BillConfigUtil.DEFAULT_UNIT_ID;
			/*strquery = billing.qryHandler_billing.getQuery(1, "select.tariffunitname.1").replace("?", BillConfigUtil.SUPER_HOSPITAL_CODE);
			strquery = strquery.concat(" and ").concat(billing.qryHandler_billing.getQuery(1, "select.tariffunitname.cond.1").replace("?",BillConfigUtil.BILL_MODULE_ID));
			strquery = strquery.concat(" and ").concat(billing.qryHandler_billing.getQuery(1, "select.defaulttariffunitname.cond.1").replace("?",strDefaultUnitId));

			nqryIndex = dao.setQuery(strquery);
			wb = dao.executeQry(nqryIndex);*/

			/*if (this.getDefaultUnit() == null || this.getDefaultUnit() == "")
			{
				comboValues2 = hisutil.getOptionValue(wb, "0", "0^Select Value", false);
			} 
			else
			{
				comboValues2 = hisutil.getOptionValue(wb, this.getDefaultUnit(), "0^Select Value", false);
			}*/

			comboValues2 = BillConfigUtil.getDefaultUnitComboForPkg(BillConfigUtil.DEFAULT_UNIT_ID);						
		} 
		catch (Exception e)
		{
			new HisException("billing", "LocalTariffMstFB.getAddDefaultUnitComboWithPkg()", e.getMessage());
		}

		return comboValues2;
	}

	/**
	 * @return Returns the getAddPackageGroupCombo.
	 */
	public String getAddPackageGroupCombo()
	{
		String comboValues1 = "<option value='0'> Select Value </option>";
		HisUtil hisutil = new HisUtil("Billing", "LocalTariffMstFB");
		try
		{
			String hosCode = this.getStrHospitalCode();

			// System.out.println("this.getStrHospitalCode()-1->"+this.getStrHospitalCode());

			if (this.getGroupId() == null || this.getGroupId() == "")
				comboValues1 = hisutil.getOptionValue(billing.qryHandler_billing.getQuery(1, "select.tariffMst.1").replace(
						"?", hosCode), "0", "0^Select Value");

			else
				comboValues1 = hisutil.getOptionValue(billing.qryHandler_billing.getQuery(1, "select.tariffMst.1").replace(
						"?", hosCode), this.getGroupId(), "0^Select Value");

		} catch (Exception e)
		{
			new HisException("billing", "LocalTariffMstFB.getAddPackageGroupCombo()", e.getMessage());
		}
		return comboValues1;
	}

	/**
	 * @return Returns the getAddDefaultUnitCombo.
	 */
	public String getAddDefaultUnitCombo()
	{
		String comboValues2 = "<option value='0'> Select Value </option>";
		//HisUtil hisutil = new HisUtil("Billing", "LocalTariffMstFB");
		//String strquery = "";
		//int nqryIndex = 0;
		//WebRowSet wb = null;
		
		//HisDAO dao = new HisDAO("billing", "LocalTariffMstFB");
		
		try
		{
			/*strquery = billing.qryHandler_billing.getQuery(1, "select.tariffunitname.1").replace("?", BillConfigUtil.SUPER_HOSPITAL_CODE);
			strquery = strquery.concat(" and ").concat(billing.qryHandler_billing.getQuery(1, "select.tariffunitname.cond.1").replace("?", BillConfigUtil.BILL_MODULE_ID));

			nqryIndex = dao.setQuery(strquery);
			wb = dao.executeQry(nqryIndex);

			if (this.getDefaultUnit() == null || this.getDefaultUnit() == "")
			{
				comboValues2 = hisutil.getOptionValue(wb, "0", "0^Select Value", false);
			} 
			else
			{
				comboValues2 = hisutil.getOptionValue(wb, this.getDefaultUnit(), "0^Select Value", false);
			}*/
			comboValues2=BillConfigUtil.getDefaultUnitCombo(this.getDefaultUnit());
		} 
		catch (Exception e)
		{
			new HisException("billing", "LocalTariffMstFB.getAddDefaultUnitCombo()", e.getMessage());
		}
		return comboValues2;
	}

	/**
	 * @return Returns the getAddServiceCombo.
	 */
	public String getAddServiceCombo()
	{

		String comboValues3 = "<option value='0'> Select Value </option>";

		HisUtil hisutil = new HisUtil("Billing", "LocalTariffMstFB");
		try
		{
			//String hosCode = this.getStrHospitalCode();
			String hosCode = BillConfigUtil.SUPER_HOSPITAL_CODE;

			if (this.getServiceName() == null || this.getServiceName() == "")
			{
				comboValues3 = hisutil.getOptionValue(billing.qryHandler_billing.getQuery(1, "select.tariffMst.30")
						.replace("?", hosCode), this.getServiceName() + "^" + hosCode, "0^Select Value");
			} 
			else
			{
				comboValues3 = hisutil.getOptionValue(billing.qryHandler_billing.getQuery(1, "select.tariffMst.30")
						.replace("?", hosCode), this.getServiceName() + "^" + hosCode, "0^Select Value");
			}
		} catch (Exception e)
		{
			new HisException("billing", "LocalTariffMstFB.getAddServiceCombo()", e.getMessage());
		}
		return comboValues3;
	}

	/**
	 * @return Returns the getAddTariffCombo.
	 */
	public String getAddTariffCombo()
	{
		String strTrfQry = "";
		String strIsGlobal = "0";
		String val1 = this.getServiceName();

		String comboValues = "<option value='0'> Select Value </option>";
		HisUtil hisutil = new HisUtil("Billing", "LocalTariffMstFB");
		try
		{
			String hosCode = this.getStrHospitalCode();
			String qry = billing.qryHandler_billing.getQuery(1, "select.tariffMst.25");

			int qryIndex;
			HisDAO dao = null;

			dao = new HisDAO("Billing", "LocalTariffMstFB");

			qryIndex = dao.setQuery(qry);

			dao.setQryValue(qryIndex, 1, hosCode);
			dao.setQryValue(qryIndex, 2, val1);

			WebRowSet wb = dao.executeQry(qryIndex);

			qry = "";
			if (wb != null)
			{
				if (wb.size() > 0 && wb.next())
				{
					strTrfQry = wb.getString(1);
					strIsGlobal = wb.getString(2);
					qry = billing.qryHandler_billing.getQuery(1, strTrfQry.trim());
				}
			}			
			if (!qry.equals(""))
			{
				if (strIsGlobal.equals("1"))
					hosCode = BillConfigUtil.SUPER_HOSPITAL_CODE;

				qryIndex = dao.setQuery(qry);
				System.out.println("qry is "+(qry)+"---"+val1+"-----"+hosCode+"---"+this.getTariffId()+"------"+this.getStrHospitalCode());

				if (val1.equals("6"))
				{
					// dao.setQryValue(qryIndex,1,val1);
					dao.setQryValue(qryIndex, 1, hosCode);
					dao.setQryValue(qryIndex, 2, val1);
					dao.setQryValue(qryIndex, 3, this.getStrHospitalCode());					
					dao.setQryValue(qryIndex, 4, hosCode);
					dao.setQryValue(qryIndex, 5, this.getTariffId());
				}
				else if (val1.equals("3"))
				{
					//dao.setQryValue(qryIndex, 1, hosCode);
					dao.setQryValue(qryIndex, 1, this.getStrHospitalCode());
					dao.setQryValue(qryIndex, 2, val1);										
					//dao.setQryValue(qryIndex, 4, hosCode);
					dao.setQryValue(qryIndex, 3, this.getTariffId());
					dao.setQryValue(qryIndex, 4, this.getStrHospitalCode());
					dao.setQryValue(qryIndex, 5, val1);										
					dao.setQryValue(qryIndex, 6, this.getTariffId());
				}
				else if (val1.equals("7"))
				{
					// dao.setQryValue(qryIndex,1,val1);
					dao.setQryValue(qryIndex, 1, hosCode);
					dao.setQryValue(qryIndex, 2, val1);
					dao.setQryValue(qryIndex, 3, this.getStrHospitalCode());
					dao.setQryValue(qryIndex, 4, hosCode);
					dao.setQryValue(qryIndex, 5, this.getTariffId());					
				}
				else if (val1.equals("8")|| val1.equals("9"))
				{
					// dao.setQryValue(qryIndex,1,val1);
					dao.setQryValue(qryIndex, 1, hosCode);
					dao.setQryValue(qryIndex, 2, val1);
					dao.setQryValue(qryIndex, 3, hosCode);
					//dao.setQryValue(qryIndex, 4, this.getTariffId());					
				}
				else
				{
					dao.setQryValue(qryIndex, 1, val1);
					dao.setQryValue(qryIndex, 2, this.getStrHospitalCode());
					dao.setQryValue(qryIndex, 3, this.getTariffId());	
				}
					
				wb = dao.executeQry(qryIndex);
				
				
			}
			if (wb != null)
			{
				val1 = "0";
				comboValues = hisutil.getOptionValue(wb, this.getTariffId(), "0^Select Value", false);
			} else
			{
				comboValues = "<option value='0'>Select Value</option>";
			}
			hisutil = null;
		} catch (Exception e)
		{
			new HisException("Billing", "LocalTariffMstFB.getAddTariffCombo()", e.getMessage());
		}
		return comboValues;
	}

	/**
	 * @return Returns the getAddDepartmentCombo.
	 */
	public String getAddDepartmentCombo()
	{

		// String dptStr = "";
		String comboValues5 = "<option value='0'> Raising Department </option>";
		HisUtil hisutil = new HisUtil("Billing", "LocalTariffMstFB");
		try
		{
			String hosCode = this.getStrHospitalCode();
			// System.out.println("this.getStrHospitalCode()-5->"+this.getStrHospitalCode());
			comboValues5 = hisutil.getOptionValue(billing.qryHandler_billing.getQuery(1, "select.tariffMst.5").replace(
					"?", hosCode), this.getTempDept(), "0^Select Value");
		} catch (Exception e)
		{
			new HisException("billing", "LocalTariffMstFB.getAddDepartmentCombo()", e.getMessage());
		}
		// System.out.println("in vo getAddDepartmentCombo--=="+comboValues5);
		return comboValues5;
	}

	/*
	 * to get default data in case of modify.
	 */
	public String getToDefault()
	{
		String str = "";
		try
		{
			str = LocalTariffMstHLP.getDefaultData(this, this.getStrDataMode());
		} catch (Exception e)
		{
			new HisException("billing", "LocalTariffMstFB.getDefault()", e.getMessage());
		}

		// System.out.println("String in FB:::"+str);
		return str;
	}

	public String getMultiRowData()
	{
		LocalTariffMstHLP hlp = new LocalTariffMstHLP();
		String data = "";
		try
		{
			data = hlp.getMultiRow(this, 1);
		} catch (Exception e)
		{
			new HisException("billing", "LocalTariffMstFB.getMultiRowData()", e.getMessage());
		}
		return data;
	}

	public String getCurrentDate()
	{
		HisUtil util = new HisUtil("billing", "LocalTariffMstFB");
		String strCurDate = "";
		try
		{
			strCurDate = util.getASDate("dd-MMM-yyyy");
		} catch (Exception e)
		{
			new HisException("Billing", "LocalTariffMstFB.getCurrentDate -->", e.getMessage());
		}
		return strCurDate;
	}

	// ------------- getter methods ---------------//

	/**
	 * @return Returns the normalMsg.
	 */
	public String getNormalMsg()
	{
		return normalMsg;
	}

	/**
	 * @return Returns the trfId.
	 */
	public String getTrfId()
	{
		return trfId;
	}

	/**
	 * @return Returns the tempDept.
	 */
	public String getTempDept()
	{
		return tempDept;
	}

	/**
	 * @return Returns the lstModDate.
	 */
	public String getLstModDate()
	{
		return lstModDate;
	}

	/**
	 * @return Returns the lstModSeat.
	 */
	public String getLstModSeat()
	{
		return lstModSeat;
	}

	/**
	 * @return Returns the remarks.
	 */
	public String getRemarks()
	{
		return remarks;
	}

	/**
	 * @return Returns the isContribution.
	 */
	public String getIsContribution()
	{
		return isContribution;
	}

	/**
	 * @return Returns the errMsg.
	 */
	public String getErrMsg()
	{
		return errMsg;
	}

	/**
	 * @return Returns the isValid.
	 */
	public String getIsValid()
	{
		return isValid;
	}

	/**
	 * @return Returns the seatId.
	 */
	public String getSeatId()
	{
		return seatId;
	}

	/**
	 * @return Returns the isPackage.
	 */
	public String getIsPackage()
	{
		return isPackage;
	}

	/**
	 * @return Returns the tariffId.
	 */
	public String getTariffId()
	{
		return tariffId;
	}

	/**
	 * @return Returns the actualCost.
	 */
	public String[] getActualCost()
	{
		return actualCost;
	}

	/**
	 * @return Returns the chargeTypeId.
	 */
	public String[] getChargeTypeId()
	{
		return chargeTypeId;
	}

	/**
	 * @return Returns the defaultUnit.
	 */
	public String getDefaultUnit()
	{
		return defaultUnit;
	}

	/**
	 * @return Returns the department.
	 */
	public String[] getDepartment()
	{
		return department;
	}

	/**
	 * @return Returns the effectiveFromContbDet.
	 */
	public String getEffectiveFromContbDet()
	{
		return effectiveFromContbDet;
	}

	/**
	 * @return Returns the effectiveFromDfltDet.
	 */
	public String getEffectiveFromDfltDet()
	{
		return effectiveFromDfltDet;
	}

	/*
	 * public int getEffectiveFromDfltDet_len() { return
	 * getEffectiveFromDfltDet().length; }
	 */
	/**
	 * @return Returns the groupId.
	 */
	public String getGroupId()
	{
		return groupId;
	}

	/**
	 * @return Returns the hospitalService.
	 */
	public String[] getHospitalService()
	{
		return hospitalService;
	}

	/**
	 * @return Returns the lengthOfStay.
	 */
	public String getLengthOfStay()
	{
		return lengthOfStay;
	}

	/**
	 * @return Returns the maxDisc.
	 */
	public String[] getMaxDisc()
	{
		return maxDisc;
	}

	/**
	 * @return Returns the rate.
	 */
	public String[] getRate()
	{
		return rate;
	}

	/**
	 * @return Returns the serviceName.
	 */
	public String getServiceName()
	{
		return serviceName;
	}

	/**
	 * @return Returns the serviceTax.
	 */
	public String[] getServiceTax()
	{
		return serviceTax;
	}

	/**
	 * @return Returns the share.
	 */
	public String[] getShare()
	{
		return share;
	}

	/**
	 * @return Returns the tariffName.
	 */
	public String getTariffName()
	{
		return tariffName;
	}

	/**
	 * @return Returns the trfPkgName.
	 */
	public String getTrfPkgName()
	{
		return trfPkgName;
	}

	/**
	 * @return Returns the totalRows.
	 */
	public int getTotalRows()
	{
		return totalRows;
	}

	// ------------- setter methods ---------------//

	/**
	 * @param actualCost
	 *           The actualCost to set.
	 */
	public void setActualCost(String[] actualCost)
	{
		this.actualCost = actualCost;
	}

	/**
	 * @param chargeTypeId
	 *           The chargeTypeId to set.
	 */
	public void setChargeTypeId(String[] chargeTypeId)
	{
		this.chargeTypeId = chargeTypeId;
	}

	/**
	 * @param defaultUnit
	 *           The defaultUnit to set.
	 */
	public void setDefaultUnit(String defaultUnit)
	{
		this.defaultUnit = defaultUnit;
	}

	/**
	 * @param department
	 *           The department to set.
	 */
	public void setDepartment(String[] department)
	{
		this.department = department;
	}

	/**
	 * @param effectiveFromContbDet
	 *           The effectiveFromContbDet to set.
	 */
	public void setEffectiveFromContbDet(String effectiveFromContbDet)
	{
		this.effectiveFromContbDet = effectiveFromContbDet;
	}

	/**
	 * @param effectiveFromDfltDet
	 *           The effectiveFromDfltDet to set.
	 */
	public void setEffectiveFromDfltDet(String effectiveFromDfltDet)
	{
		this.effectiveFromDfltDet = effectiveFromDfltDet;
	}

	/**
	 * @param groupId
	 *           The groupId to set.
	 */
	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}

	/**
	 * @param hospitalService
	 *           The hospitalService to set.
	 */
	public void setHospitalService(String[] hospitalService)
	{
		this.hospitalService = hospitalService;
	}

	/**
	 * @param lengthOfStay
	 *           The lengthOfStay to set.
	 */
	public void setLengthOfStay(String lengthOfStay)
	{
		this.lengthOfStay = lengthOfStay;
	}

	/**
	 * @param maxDisc
	 *           The maxDisc to set.
	 */
	public void setMaxDisc(String[] maxDisc)
	{
		this.maxDisc = maxDisc;
	}

	/**
	 * @param rate
	 *           The rate to set.
	 */
	public void setRate(String[] rate)
	{
		this.rate = rate;
	}

	/**
	 * @param serviceName
	 *           The serviceName to set.
	 */
	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}

	/**
	 * @param serviceTax
	 *           The serviceTax to set.
	 */
	public void setServiceTax(String[] serviceTax)
	{
		this.serviceTax = serviceTax;
	}

	/**
	 * @param share
	 *           The share to set.
	 */
	public void setShare(String[] share)
	{
		this.share = share;
	}

	/**
	 * @param tariffName
	 *           The tariffName to set.
	 */
	public void setTariffName(String tariffName)
	{
		this.tariffName = tariffName;
	}

	/**
	 * @param trfPkgName
	 *           The trfPkgName to set.
	 */
	public void setTrfPkgName(String trfPkgName)
	{
		this.trfPkgName = trfPkgName;
	}

	/**
	 * @param isPackage
	 *           The isPackage to set.
	 */
	public void setIsPackage(String isPackage)
	{
		this.isPackage = isPackage;
	}

	/**
	 * @param tariffId
	 *           The tariffId to set.
	 */
	public void setTariffId(String tariffId)
	{
		this.tariffId = tariffId;
	}

	/**
	 * @param isValid
	 *           The isValid to set.
	 */
	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	/**
	 * @param seatId
	 *           The seatId to set.
	 */
	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	/**
	 * @param errmsg
	 *           The errMsg to set.
	 */
	public void setErrMsg(String errMsg)
	{
		this.errMsg = errMsg;
	}

	/**
	 * @param isContribution
	 *           The isContribution to set.
	 */
	public void setIsContribution(String isContribution)
	{
		this.isContribution = isContribution;
	}

	/**
	 * @param remarks
	 *           The remarks to set.
	 */
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	/**
	 * @param totalRows
	 *           The totalRows to set.
	 */
	public void setTotalRows(int totalRows)
	{
		this.totalRows = totalRows;
	}

	/**
	 * @param lstModDate
	 *           The lstModDate to set.
	 */
	public void setLstModDate(String lstModDate)
	{
		this.lstModDate = lstModDate;
	}

	/**
	 * @param lstModSeat
	 *           The lstModSeat to set.
	 */
	public void setLstModSeat(String lstModSeat)
	{
		this.lstModSeat = lstModSeat;
	}

	/**
	 * @param string
	 *           The tempDept to set.
	 */
	public void setTempDept(String tempDept)
	{
		this.tempDept = tempDept;
	}

	/**
	 * @param trfId
	 *           The trfId to set.
	 */
	public void setTrfId(String trfId)
	{
		this.trfId = trfId;
	}

	/**
	 * @param normalMsg
	 *           The normalMsg to set.
	 */
	public void setNormalMsg(String normalMsg)
	{
		this.normalMsg = normalMsg;
	}

	public String getStrChoiceNum()
	{
		return strChoiceNum;
	}

	public void setStrChoiceNum(String strChoiceNum)
	{
		this.strChoiceNum = strChoiceNum;
	}

	public String getStrServiceId()
	{
		return strServiceId;
	}

	public void setStrServiceId(String strServiceId)
	{
		this.strServiceId = strServiceId;
	}

	public String getGroupName()
	{
		return groupName;
	}

	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

	public String getStrChK()
	{
		return strChK;
	}

	public void setStrChK(String strChK)
	{
		this.strChK = strChK;
	}

	public String getStrGroupId()
	{
		return strGroupId;
	}

	public void setStrGroupId(String strGroupId)
	{
		this.strGroupId = strGroupId;
	}

	public String getMultiRow()
	{
		return multiRow;
	}

	public void setMultiRow(String multiRow)
	{
		this.multiRow = multiRow;
	}

	public String getStrTotalRows()
	{
		return strTotalRows;
	}

	public void setStrTotalRows(String strTotalRows)
	{
		this.strTotalRows = strTotalRows;
	}

	public String getStrMultiRow()
	{
		return strMultiRow;
	}

	public void setStrMultiRow(String strMultiRow)
	{
		this.strMultiRow = strMultiRow;
	}

	public String getStrEffectiveFrom()
	{
		return strEffectiveFrom;
	}

	public void setStrEffectiveFrom(String strEffectiveFrom)
	{
		this.strEffectiveFrom = strEffectiveFrom;
	}

	public String getStrDataMode()
	{
		return strDataMode;
	}

	public void setStrDataMode(String strDataMode)
	{
		this.strDataMode = strDataMode;
	}

	public String getDefaultPackUnit()
	{
		return defaultPackUnit;
	}

	public void setDefaultPackUnit(String defaultPackUnit)
	{
		this.defaultPackUnit = defaultPackUnit;
	}

	public String getStrDiscountLimit()
	{
		return strDiscountLimit;
	}

	public void setStrDiscountLimit(String strDiscountLimit)
	{
		this.strDiscountLimit = strDiscountLimit;
	}

	public String getStrDiscountPrivilege()
	{
		return strDiscountPrivilege;
	}

	public void setStrDiscountPrivilege(String strDiscountPrivilege)
	{
		this.strDiscountPrivilege = strDiscountPrivilege;
	}

	/**
	 * @return the tariffCode
	 */
	public String getTariffCode()
	{
		return tariffCode;
	}

	/**
	 * @param tariffCode
	 *           the tariffCode to set
	 */
	public void setTariffCode(String tariffCode)
	{
		this.tariffCode = tariffCode;
	}

	public String getStrIsDefaultForIpd()
	{
		return strIsDefaultForIpd;
	}

	public void setStrIsDefaultForIpd(String strIsDefaultForIpd)
	{
		this.strIsDefaultForIpd = strIsDefaultForIpd;
	}

	public String getStrIsOpdVisible()
	{
		return strIsOpdVisible;
	}

	public void setStrIsOpdVisible(String strIsOpdVisible)
	{
		this.strIsOpdVisible = strIsOpdVisible;
	}

	public String getStrIsEmergVisible()
	{
		return strIsEmergVisible;
	}

	public void setStrIsEmergVisible(String strIsEmergVisible)
	{
		this.strIsEmergVisible = strIsEmergVisible;
	}

	public String getStrIsIpdVisible()
	{
		return strIsIpdVisible;
	}

	public void setStrIsIpdVisible(String strIsIpdVisible)
	{
		this.strIsIpdVisible = strIsIpdVisible;
	}

	public String getStrUpdateChargeUnit()
	{
		return strUpdateChargeUnit;
	}

	public void setStrUpdateChargeUnit(String strUpdateChargeUnit)
	{
		this.strUpdateChargeUnit = strUpdateChargeUnit;
	}

	public String getAddGlobalTariffCombo()
	{
		StringBuffer trfStr = new StringBuffer();
		String errMsg = "";
		String comboValues = "<option value='0'>Select Value </option>";
		HisUtil hisutil = new HisUtil("Billing", "LocalTariffMstFB");
		try
		{
			trfStr.append(billing.qryHandler_billing.getQuery(1, "select.global.tariffMst.1").replace("?", this.getGroupId()));
			trfStr.append(billing.qryHandler_billing.getQuery(1, "select.global.tariffMst.1.cond").replace("?", this.getStrHospitalCode()));
			comboValues = hisutil.getOptionValue(trfStr.toString(), this.getGlobalTariff(), "0^Select Value");
		} 
		catch (Exception e)
		{
			errMsg = "Billing.LocalTariffMstFB() -->" + e.getMessage();
			new HisException("billing", "LocalTariffMstFB.getAddGlobalTariffCombo()", errMsg);
		}
		return comboValues;
	}

	public String getGlobalTariff()
	{
		return globalTariff;
	}

	public void setGlobalTariff(String globalTariff)
	{
		this.globalTariff = globalTariff;
	}

	public String getGstrTariffName() {
		return gstrTariffName;
	}

	public void setGstrTariffName(String gstrTariffName) {
		this.gstrTariffName = gstrTariffName;
	}

}