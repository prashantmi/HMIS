package billing.masters.controller.data;
/* Charge Master DATA
 * author: Debashis Sardar
 * Created on : 06-Sep-2011
 */
import javax.sql.rowset.WebRowSet;
import billing.BillConfigUtil;
import billing.BillingComboDAO;
import billing.masters.controller.hlp.ChargeMstHLP;
import billing.masters.bo.ChargeMstBO;
import billing.masters.controller.fb.ChargeMstFB;
import billing.masters.vo.ChargeMstVO;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;

public class ChargeMstDATA {
	/**
	 * function to insert data. This function retrieve result from DAO class and
	 * forwards to controller.
	 * 
	 * @param fb
	 * @return int
	 */
	public int insertData(ChargeMstFB fb) {
		boolean fval = false;
		int tmp_counter=-1;
		ChargeMstVO vo = null;
		try {
           vo = new ChargeMstVO();
			
			vo = (ChargeMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.ChargeMstVO", fb);
			fval = ChargeMstBO.insertRecord(vo);
			fb = (ChargeMstFB) TransferObjectFactory.populateData(
					"billing.masters.controller.fb.ChargeMstFB", vo);
			 HelperMethods.populate(fb,vo);
			
			if (fval) {
				tmp_counter=0;
				
			} else {
				if(fb.getStrMsgType().equals("1"))
				{
					tmp_counter=1;
					
				}
				else{
					tmp_counter=2;
				
				}
			}
		} catch (Exception e) {
			
			if(fb.getStrMsgType().equals("1"))
			{
				fb.setStrWarningMsg("Record already Exist"); 
			}else{
			HisException eObj = new HisException("Billing",
					"ChargeMstDATA.insertData -->", e.getMessage());

			fb.setStrErrMsg("Error [ERROR ID : " + eObj.getErrorID()
					+ "], Contact System Administrator !");
			
			
			if (eObj != null)
				eObj = null;
			}
		}
		return tmp_counter;
	}

	/**
	 * function to retrieve data from database.
	 * 
	 * @param fb
	 * @param chk
	 * @return boolean
	 */
	public boolean getDataToModify(ChargeMstFB fb, String chk) {
		boolean fRes = false;
		ChargeMstVO vo = null;
		try {
            vo = new ChargeMstVO();
			
			vo = (ChargeMstVO) TransferObjectFactory.populateData("billing.masters.vo.ChargeMstVO", fb);
			
			String[] strTemp1 = chk.replace('|', '#').split("#"); // extra information is splitted.
			String[] strTemp2 = strTemp1[0].replace('@', '#').split("#"); // primary keys are splitted here.

			String[] strTemp3 = new String[strTemp2.length + 1];
			for (int ni = 0; ni < strTemp2.length; ni++) {
				strTemp3[ni] = strTemp2[ni];
			}
			String[] strT = strTemp1[1].replace('$', '#').split("#"); // index no. is splitted here.
			strTemp3[strTemp2.length] = strT[0];
			fRes = ChargeMstBO.getData(vo, strTemp3 , chk);
			//TransferObjectFactory.populateData(
				//	fb, vo);
			
			fb.setHospitalService(vo.getHospitalService());
			fb.setStrPackHospService(vo.getStrPackHospService());
			fb.setStrTariffName(vo.getStrTariffName());
			fb.setStrPackTariffName(vo.getStrPackTariffName());
			fb.setStrPatientCategoryMod(vo.getStrPatientCategoryMod());
			fb.setStrPackPatientCategory(vo.getStrPackPatientCategory());
			fb.setStrWardType(vo.getStrWardType());
			fb.setStrPackWardType(vo.getStrPackWardType());
			fb.setStrChargeSlNo(vo.getStrChargeSlNo());
			fb.setStrGroupName(vo.getStrGroupName());
			fb.setStrPackGroupName(vo.getStrPackGroupName());
			
			fb.setStrPackProcdCost(vo.getStrPackProcdCost());
			
			fb.setStrPackTariffCost(vo.getStrPackTariffCost());
			
			fb.setStrUnit(vo.getStrUnit());
			fb.setStrPackUnit(vo.getStrPackUnit());
		
			fb.setStrPackIsAdvance(vo.getStrPackIsAdvance());
			
			fb.setStrPackIsRefundable(vo.getStrPackIsRefundable());
			fb.setStrEffectiveFrm(vo.getStrEffectiveFrm());
			fb.setStrEffectiveTo(vo.getStrEffectiveTo());
			fb.setStrRemarks(vo.getStrRemarks());
			fb.setStrSeatId(vo.getStrSeatId());
			fb.setStrPatCatName(vo.getStrPatCatName());
			fb.setStrProductCost(vo.getStrProductCost());
			fb.setStrTariffCost(vo.getStrTariffCost());
			fb.setStrTotalCost(vo.getStrTotalCost());
			fb.setStrIsAdvance(vo.getStrIsAdvance());
			fb.setStrIsRefundable(vo.getStrIsRefundable());
			fb.setPackageDetails(vo.getPackageDetails());
			fb.setStrPackageDetails(vo.getStrPackageDetails());
			fb.setStrIsPackage(vo.getStrIsPackage());
			fb.setWbModifyData(vo.getWbModifyData());
			
			
		
			
		} catch (Exception e) {
			
			HisException eObj = new HisException("Billing",
					"ChargeMstDATA.getDataToModify -->", e.getMessage());

			fb.setStrErrMsg("Error [ERROR ID : " + eObj.getErrorID()
					+ "], Contact System Administrator !");

			if (eObj != null)
				eObj = null;
		}
		
		return fRes;
	}

	
	/**
	 * check all the modification cases in modify page.
	 * 
	 * @param fb
	 * @param chk
	 * @return boolean
	 */
	public boolean modifyData1(ChargeMstFB fb, String chk) {
		boolean fVal = false;
		ChargeMstVO vo = null;
		try {
             vo = new ChargeMstVO();
			
			vo = (ChargeMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.ChargeMstVO", fb);
			if (fb.getStrUpdateMode().equals("0") || (fb.getStrIsPackage().equals("0") && !fb.getStrUpdateMode().equals("0"))) {

				if (ChargeMstBO.qryBeforeModify(vo, chk)) {

					fVal = ChargeMstBO.saveModifiedData(vo, chk);

				} else {
					fb.setStrWarningMsg("Effective From Date Exists Between The Effective From Date AND Effective To Date Of Existing Records.");
				}

			} else if (!fb.getStrIsPackage().equals("0")) {

				fVal = ChargeMstBO.qryBeforeUpdate1(vo, chk); // condition to

				

				if (fVal) {
					fVal = ChargeMstBO.qryBeforeUpdate2(vo); // to check

					

					if (fVal) {

						

						fVal = ChargeMstBO.newInsertData(vo, chk); // if above conditions return true then update data.
						if (!fVal) {
							fb.setStrErrMsg("Error in modify page!!");
						}
					} else {
						fb
								.setStrWarningMsg("Effective To Date Exists Between Effective From Date And Effective To Date Of Existing Records.");
					}
				} else {
					fb
							.setStrWarningMsg("Effective From Date Exists Between The Effective From Date AND Effective To Date Of Existing Records.");
				}
			}
			fb = (ChargeMstFB) TransferObjectFactory.populateData(
					"billing.masters.controller.fb.ChargeMstFB", vo);
			HelperMethods.populate(fb,vo);
		} catch (Exception e) {
			
			fVal = false;
			HisException eObj = new HisException("Billing",
					"ChargeMstDATA.modifyData1 -->", e.getMessage());

			fb.setStrErrMsg("Error [ERROR ID : " + eObj.getErrorID()
					+ "], Contact System Administrator !");

			if (eObj != null)
				eObj = null;
		}
		return fVal;
	}

	/**
	 * checks batch updation cases.
	 * 
	 * @param fb
	 * @return boolean
	 */
	public boolean batchData(ChargeMstFB fb) {
		boolean fVal = false;
		ChargeMstVO vo = null;
		
		try {
            vo = new ChargeMstVO();
			
			vo = (ChargeMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.ChargeMstVO", fb);
			if (fb.getStrSelectOption().equals("0")) {

				fVal = ChargeMstBO.batchUpdateQry(vo);
			} else {

				fVal = ChargeMstBO.batchCopyToQry(vo);
			}
			
			fb = (ChargeMstFB) TransferObjectFactory.populateData(
					"billing.masters.controller.fb.ChargeMstFB", vo);
			HelperMethods.populate(fb,vo);
		} catch (Exception e) {
			HisException eObj = new HisException("Billing",
					"ChargeMstDATA.batchData -->", e.getMessage());

			fb.setStrErrMsg("Error [ERROR ID : " + eObj.getErrorID()
					+ "], Contact System Administrator !");

			if (eObj != null)
				eObj = null;
		}

		fb.setHospitalService("0");
		fb.setStrEffectiveFrm(vo.getCurrentDate());

		return fVal;
	}

	/**
	 * to delete Records.
	 * 
	 * @param fb
	 * @param strChk
	 * @return
	 */
	public void deleteData(ChargeMstFB fb,String[] strChk) {
		ChargeMstVO vo = null;
		vo = new ChargeMstVO();
		
		vo = (ChargeMstVO) TransferObjectFactory.populateData(
				"billing.masters.vo.ChargeMstVO", fb);
		ChargeMstBO.deleteData(vo,strChk);
		fb = (ChargeMstFB) TransferObjectFactory.populateData(
				"billing.masters.controller.fb.ChargeMstFB", vo);
		HelperMethods.populate(fb,vo);
		if(fb.getStrMsgType().equals("1"))
			fb.setStrErrMsg("ChargeMstDATA.deleteData() -->"+fb.getStrErrMsg());
		
	}
	/**
	 * to get Group combo.
	 * 
	 * @param fb
	 * @return String
	 */
	public String getGroupCombo(ChargeMstFB fb) {
		

		String strGpCmb = "";
		HisUtil hisutil = new HisUtil("Billing", "VOChargeMst");
		
		ChargeMstBO bo = new ChargeMstBO();
		WebRowSet wb = null;
		ChargeMstVO vo = null;

		try {
            vo = new ChargeMstVO();
			
			vo = (ChargeMstVO) TransferObjectFactory.populateData("billing.masters.vo.ChargeMstVO", fb);
			wb = bo.groupCmb(vo, "0");
			strGpCmb = hisutil.getOptionValue(wb, vo.getStrGroupName(),"0^Select Value", false);
			
			//fb = (ChargeMstFB) TransferObjectFactory.populateData("billing.masters.controller.fb.ChargeMstFB", vo);
			//HelperMethods.populate(fb,vo);
		} catch (Exception e) {
			new HisException("billing", "ChargeMstDATA.getGroupCombo()", e
					.getMessage());
		}

		return strGpCmb;
	}
	/**
	 * to get Groupcombo1.
	 * 
	 * @param fb
	 * @return String
	 */
	public String getGroupCombo1(ChargeMstFB fb) {

		
		String grpStr1 = "";
		HisUtil hisutil = new HisUtil("Billing", "VOChargeMst");
		
		ChargeMstBO bo = new ChargeMstBO();
		
		ChargeMstVO vo = null;
		WebRowSet wb = null;
		try 
		{
            vo = new ChargeMstVO();
			vo = (ChargeMstVO) TransferObjectFactory.populateData("billing.masters.vo.ChargeMstVO", fb);
			wb = bo.groupCmb1(vo,"1");

			grpStr1 = hisutil.getOptionValue(wb, vo.getStrPackGroupName(),"0^Select Value", true);
			 vo.setGroupCombo1(grpStr1);
			 //fb = (ChargeMstFB) TransferObjectFactory.populateData("billing.masters.controller.fb.ChargeMstFB", vo);
			 //HelperMethods.populate(fb,vo);
		} 
		catch (Exception e) 
		{
			new HisException("billing", "ChargeMstDATA.getGroupCombo1()", e.getMessage());
		}
         
		return grpStr1;
	}
	/**
	 * to get TariffCombo.
	 * 
	 * @param fb
	 * @return String
	 */
	public String getTariffCombo(ChargeMstFB fb) {
		

		String strTariffCmb = "";
		HisUtil hisutil = new HisUtil("Billing", "VOChargeMst");
		
         ChargeMstBO bo = new ChargeMstBO();
		
		ChargeMstVO vo = null;
		
		WebRowSet wb = null;

		try {
            vo = new ChargeMstVO();
			
			vo = (ChargeMstVO) TransferObjectFactory.populateData("billing.masters.vo.ChargeMstVO", fb);
			wb = bo.tariffCmb(vo);

			strTariffCmb = hisutil.getOptionValue(wb, vo.getStrTariffName(),"0^Select Value", true, false);
			
			//fb = (ChargeMstFB) TransferObjectFactory.populateData("billing.masters.controller.fb.ChargeMstFB", vo);
			//HelperMethods.populate(fb,vo);
		} catch (Exception e) {
			new HisException("billing", "ChargeMstDATA.getTariffCombo()", e
					.getMessage());
		}

		return strTariffCmb;
	}
	/**
	 * to get TariffCombo1.
	 * 
	 * @param fb
	 * @return String
	 */
	public String getTariffCombo1(ChargeMstFB fb) {
		
       ChargeMstBO bo = new ChargeMstBO();
		
		ChargeMstVO vo = null;
		String strTariffCmb1 = "";
		HisUtil hisutil = new HisUtil("Billing", "VOChargeMst");
		

		WebRowSet wb = null;

		try {
	            vo = new ChargeMstVO();
				vo = (ChargeMstVO) TransferObjectFactory.populateData("billing.masters.vo.ChargeMstVO", fb);
			wb = bo.tariffCmb1(vo);
			strTariffCmb1 = hisutil.getOptionValue(wb, vo.getStrTariffName(),"0^Select Value", true, false);
			vo.setTariffCombo1(strTariffCmb1);
			//fb = (ChargeMstFB) TransferObjectFactory.populateData("billing.masters.controller.fb.ChargeMstFB", vo);
			//HelperMethods.populate(fb,vo);
			
		} catch (Exception e) {
			new HisException("billing", "ChargeMstDATA.getTariffCombo()", e
					.getMessage());
		}

		return strTariffCmb1;
	}
	/**
	 * to get UnitCombo.
	 * 
	 * @param fb
	 * @return String
	 */
	public String getUnitCombo(ChargeMstFB fb) 
	{		
		ChargeMstBO bo = new ChargeMstBO();		
		ChargeMstVO vo = null;
		String strUnitCmb = "";
		HisUtil hisutil = new HisUtil("Billing", "ChargeMstFB");		
		WebRowSet wb = null;

		try 
		{
			 	vo = new ChargeMstVO();
				vo = (ChargeMstVO) TransferObjectFactory.populateData("billing.masters.vo.ChargeMstVO", fb);
				wb = bo.unitCmb(vo);
				
				strUnitCmb=BillConfigUtil.getDefaultUnitCombo(vo.getStrUnit());
			
			/*if(wb != null && wb.size() > 0)
			{
				strUnitCmb = hisutil.getOptionValue(wb, vo.getStrUnit(),"0^Select Value", false);
			}
			else
			{
				strUnitCmb = "<option value='0'>Select Value</option>";
			}*/
			
			//fb = (ChargeMstFB) TransferObjectFactory.populateData("billing.masters.controller.fb.ChargeMstFB", vo);
			//HelperMethods.populate(fb,vo);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			new HisException("billing", "ChargeMstDATA.getUnitCombo()", e.getMessage());
		}

		return strUnitCmb;
	}
	/**
	 * to get WardCombo.
	 * 
	 * @param fb
	 * @return String
	 */
	public String getWardCombo(ChargeMstFB fb) {
		
         ChargeMstBO bo = new ChargeMstBO();
		
		ChargeMstVO vo = null;
		String strWardCmb = "";
		HisUtil hisutil = new HisUtil("Billing", "VOChargeMst");
		
	
		WebRowSet wb = null;

		try {
			vo = new ChargeMstVO();
			
			vo = (ChargeMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.ChargeMstVO", fb);
			wb = bo.wardCmb(vo);
			strWardCmb = hisutil.getOptionValue(wb, vo.getStrWardType(),
					"0^Select Value", false);
			
			//fb = (ChargeMstFB) TransferObjectFactory.populateData("billing.masters.controller.fb.ChargeMstFB", vo);
			//HelperMethods.populate(fb,vo);
		} catch (Exception e) {
			new HisException("billing", "ChargeMstDATA.getWardCombo()", e
					.getMessage());
		}

		return strWardCmb;
	}
	/**
	 * to get DataOnPack.
	 * 
	 * @param fb
	 * @return String
	 */
	public String getDataOnPack(ChargeMstFB fb) {
		String strData = "";
        
		
		ChargeMstVO vo = null;
		ChargeMstHLP hlp = new ChargeMstHLP();
		try {
        vo = new ChargeMstVO();
			
			vo = (ChargeMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.ChargeMstVO", fb);
			
			strData = hlp.getDataFrmPack(vo, vo.getStrTariffName());
			fb = (ChargeMstFB) TransferObjectFactory.populateData(
					"billing.masters.controller.fb.ChargeMstFB", vo);
			HelperMethods.populate(fb,vo);

		} catch (Exception e) {
			new HisException("Billing", "ChargeMstDATA.getDataOnPack -->", e
					.getMessage());
		}
		return strData;
	}
	/**
	 * to get HospitalServiceCombo.
	 * 
	 * @param fb
	 * @return String
	 */
	public String getStrHospitalServiceCombo(ChargeMstFB fb) {
		HisUtil hisutil = new HisUtil("Billing", "VOChargeMst.getHospService()");
		BillingComboDAO cmb = new BillingComboDAO("billing", "VOChargeMst");
		WebRowSet wb = null;
		String strHserv = "";
		ChargeMstVO vo = null;
		try 
		{
			vo = new ChargeMstVO();
			
			vo = (ChargeMstVO) TransferObjectFactory.populateData("billing.masters.vo.ChargeMstVO", fb);
			wb = cmb.getChargeTypeDtl(vo, false);
			if(fb.getHospitalService()==null)
				fb.setHospitalService("0");
	
			strHserv = hisutil.getOptionValue(wb, fb.getHospitalService(),"0^Select Value", false);
			
			fb.setStrHospitalServiceCombo(strHserv);
			//fb = (ChargeMstFB) TransferObjectFactory.populateData("billing.masters.controller.fb.ChargeMstFB", vo);
			//HelperMethods.populate(fb,vo);
		} 
		catch (Exception e) 
		{
			new HisException("billing", "ChargeMstDATA.getStrHospitalService()",e.getMessage());
		}

		return strHserv;

	}
	/**
	 * to get PatientCategory Combo.
	 * 
	 * @param fb
	 * @return String
	 */
	public String getPatientCategory(ChargeMstFB fb) 
	{
		HisUtil hisutil = new HisUtil("Billing", "VOChargeMst");
		HisDAO hisdao = new HisDAO("billing.DAOChargeMst", "getPreviousData()");
		int nIndex;
		WebRowSet wb = null;
		ChargeMstVO vo = null;
		String patientCategory="";
		
		try 
		{
           vo = new ChargeMstVO();
           vo = (ChargeMstVO) TransferObjectFactory.populateData("billing.masters.vo.ChargeMstVO", fb);

			String str = billing.qryHandler_billing.getQuery(1, "gbl.cat.1");
			nIndex = hisdao.setQuery(str);
			
			hisdao.setQryValue(nIndex, 1, fb.getStrHospitalCode());
			hisdao.setQryValue(nIndex, 2, fb.getStrHospitalCode());
			hisdao.setQryValue(nIndex, 3, vo.getStrGroupId());
			hisdao.setQryValue(nIndex, 4, vo.getStrTariffName());
			hisdao.setQryValue(nIndex, 5, vo.getStrPackHospService());		
			hisdao.setQryValue(nIndex, 6, vo.getStrWardType());
			wb=hisdao.executeQry(nIndex);
		
			patientCategory = hisutil.getOptionValue(wb, vo.getStrTempCategory(), "0^Select Value", false);
			fb = (ChargeMstFB) TransferObjectFactory.populateData("billing.masters.controller.fb.ChargeMstFB", vo);
			HelperMethods.populate(fb,vo);
		} 
		catch (Exception e) 
		{
	 		new HisException("Billing", "ChargeMstDATA.getPatientCategory -->", e.getMessage());
		}
		
		return patientCategory;
	}

}
