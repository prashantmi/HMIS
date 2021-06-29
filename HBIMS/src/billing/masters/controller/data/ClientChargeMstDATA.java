package billing.masters.controller.data;
/* Charge Master DATA
 * author: Debashis Sardar
 * Created on : 06-Sep-2011
 */
import javax.sql.rowset.WebRowSet;
import billing.BillConfigUtil;
import billing.BillingComboDAO;
import billing.masters.controller.hlp.ClientChargeMstHLP;
import billing.masters.bo.ClientChargeMstBO;
import billing.masters.controller.fb.ClientChargeMstFB;
import billing.masters.vo.ClientChargeMstVO;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;

public class ClientChargeMstDATA {
	/**
	 * function to insert data. This function retrieve result from DAO class and
	 * forwards to controller.
	 * 
	 * @param fb
	 * @return int
	 */
	public int insertData(ClientChargeMstFB fb) 
	{
		boolean fval = false;
		int tmp_counter=-1;
		ClientChargeMstVO vo = null;
		try 
		{
            vo = new ClientChargeMstVO();			
			vo = (ClientChargeMstVO) TransferObjectFactory.populateData("billing.masters.vo.ClientChargeMstVO", fb);
			fval = ClientChargeMstBO.insertRecord(vo);
			fb = (ClientChargeMstFB) TransferObjectFactory.populateData("billing.masters.controller.fb.ClientChargeMstFB", vo);
			HelperMethods.populate(fb,vo);
			
			if (fval) 
			{
				tmp_counter=0;				
			} 
			else 
			{
				if(fb.getStrMsgType().equals("1"))
				{
					tmp_counter=1;					
				}
				else
				{
					tmp_counter=2;				
				}
			}
		} 
		catch (Exception e) 
		{			
			if(fb.getStrMsgType().equals("1"))
			{
				fb.setStrWarningMsg("Record already Exist"); 
			}
			else
			{
				HisException eObj = new HisException("Billing","ClientChargeMstDATA.insertData -->", e.getMessage());
				fb.setStrErrMsg("Error [ERROR ID : " + eObj.getErrorID()+ "], Contact System Administrator !");			
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
	public boolean getDataToModify(ClientChargeMstFB fb, String chk) {
		boolean fRes = false;
		ClientChargeMstVO vo = null;
		try {
            vo = new ClientChargeMstVO();
			
			vo = (ClientChargeMstVO) TransferObjectFactory.populateData("billing.masters.vo.ClientChargeMstVO", fb);
			
			String[] strTemp1 = chk.replace('|', '#').split("#"); // extra information is splitted.
			String[] strTemp2 = strTemp1[0].replace('@', '#').split("#"); // primary keys are splitted here.

			String[] strTemp3 = new String[strTemp2.length + 1];
			for (int ni = 0; ni < strTemp2.length; ni++) {
				strTemp3[ni] = strTemp2[ni];
			}
			String[] strT = strTemp1[1].replace('$', '#').split("#"); // index no. is splitted here.
			strTemp3[strTemp2.length] = strT[0];
			fRes = ClientChargeMstBO.getData(vo, strTemp3 , chk);
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
	//		fb.setStrPackUnit(vo.getStrPackUnit());
		
	//		fb.setStrPackIsAdvance(vo.getStrPackIsAdvance());
			
		//	fb.setStrPackIsRefundable(vo.getStrPackIsRefundable());
			fb.setStrEffectiveFrm(vo.getStrEffectiveFrm());
			fb.setStrEffectiveTo(vo.getStrEffectiveTo());
			fb.setStrRemarks(vo.getStrRemarks());
			fb.setStrSeatId(vo.getStrSeatId());
			fb.setStrPatCatName(vo.getStrPatCatName());
			fb.setStrProductCost(vo.getStrProductCost());
			fb.setStrTariffCost(vo.getStrTariffCost());
			fb.setStrTotalCost(vo.getStrTotalCost());
	//		fb.setStrIsAdvance(vo.getStrIsAdvance());
		//	fb.setStrIsRefundable(vo.getStrIsRefundable());
	//		fb.setPackageDetails(vo.getPackageDetails());
		//	fb.setStrPackageDetails(vo.getStrPackageDetails());
		//	fb.setStrIsPackage(vo.getStrIsPackage());
			fb.setWbModifyData(vo.getWbModifyData());
			fb.setStrOrg(vo.getStrOrg());
			fb.setStrOrgName(vo.getStrOrgName());
			
		
			
		} catch (Exception e) {
			
			HisException eObj = new HisException("Billing",
					"ClientChargeMstDATA.getDataToModify -->", e.getMessage());

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
	public boolean modifyData1(ClientChargeMstFB fb, String chk) {
		boolean fVal = false;
		ClientChargeMstVO vo = null;
		try {
             vo = new ClientChargeMstVO();
			
			vo = (ClientChargeMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.ClientChargeMstVO", fb);
			if (fb.getStrUpdateMode().equals("0") || (fb.getStrIsPackage().equals("0") && !fb.getStrUpdateMode().equals("0"))) {

				if (ClientChargeMstBO.qryBeforeModify(vo, chk)) {

					fVal = ClientChargeMstBO.saveModifiedData(vo, chk);

				} else {
					fb.setStrWarningMsg("Effective From Date Exists Between The Effective From Date AND Effective To Date Of Existing Records.");
				}

			} else if (!fb.getStrIsPackage().equals("0")) {

				fVal = ClientChargeMstBO.qryBeforeUpdate1(vo, chk); // condition to

				

				if (fVal) {
					fVal = ClientChargeMstBO.qryBeforeUpdate2(vo); // to check

					

					if (fVal) {

						

						fVal = ClientChargeMstBO.newInsertData(vo, chk); // if above conditions return true then update data.
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
			fb = (ClientChargeMstFB) TransferObjectFactory.populateData(
					"billing.masters.controller.fb.ClientChargeMstFB", vo);
			HelperMethods.populate(fb,vo);
		} catch (Exception e) {
			
			fVal = false;
			HisException eObj = new HisException("Billing",
					"ClientChargeMstDATA.modifyData1 -->", e.getMessage());

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
	public boolean batchData(ClientChargeMstFB fb) {
		boolean fVal = false;
		ClientChargeMstVO vo = null;
		
		try {
            vo = new ClientChargeMstVO();
			
			vo = (ClientChargeMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.ClientChargeMstVO", fb);
			if (fb.getStrSelectOption().equals("0")) {

				fVal = ClientChargeMstBO.batchUpdateQry(vo);
			} else {

				fVal = ClientChargeMstBO.batchCopyToQry(vo);
			}
			
			fb = (ClientChargeMstFB) TransferObjectFactory.populateData(
					"billing.masters.controller.fb.ClientChargeMstFB", vo);
			HelperMethods.populate(fb,vo);
		} catch (Exception e) {
			HisException eObj = new HisException("Billing",
					"ClientChargeMstDATA.batchData -->", e.getMessage());

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
	public void deleteData(ClientChargeMstFB fb,String[] strChk) {
		ClientChargeMstVO vo = null;
		vo = new ClientChargeMstVO();
		
		vo = (ClientChargeMstVO) TransferObjectFactory.populateData(
				"billing.masters.vo.ClientChargeMstVO", fb);
		ClientChargeMstBO.deleteData(vo,strChk);
		fb = (ClientChargeMstFB) TransferObjectFactory.populateData(
				"billing.masters.controller.fb.ClientChargeMstFB", vo);
		HelperMethods.populate(fb,vo);
		if(fb.getStrMsgType().equals("1"))
			fb.setStrErrMsg("ClientChargeMstDATA.deleteData() -->"+fb.getStrErrMsg());
		
	}
	/**
	 * to get Group combo.
	 * 
	 * @param fb
	 * @return String
	 */
	public String getGroupCombo(ClientChargeMstFB fb) {
		

		String strGpCmb = "";
		HisUtil hisutil = new HisUtil("Billing", "VOClientChargeMst");
		
		ClientChargeMstBO bo = new ClientChargeMstBO();
		WebRowSet wb = null;
		ClientChargeMstVO vo = null;

		try {
            vo = new ClientChargeMstVO();
			
			vo = (ClientChargeMstVO) TransferObjectFactory.populateData("billing.masters.vo.ClientChargeMstVO", fb);
			wb = bo.groupCmb(vo, "0");
			strGpCmb = hisutil.getOptionValue(wb, vo.getStrGroupName(),"0^Select Value", false);
			
			//fb = (ClientChargeMstFB) TransferObjectFactory.populateData("billing.masters.controller.fb.ClientChargeMstFB", vo);
			//HelperMethods.populate(fb,vo);
		} catch (Exception e) {
			new HisException("billing", "ClientChargeMstDATA.getGroupCombo()", e
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
	public String getGroupCombo1(ClientChargeMstFB fb) {

		
		String grpStr1 = "";
		HisUtil hisutil = new HisUtil("Billing", "VOClientChargeMst");
		
		ClientChargeMstBO bo = new ClientChargeMstBO();
		
		ClientChargeMstVO vo = null;
		WebRowSet wb = null;
		try 
		{
            vo = new ClientChargeMstVO();
			vo = (ClientChargeMstVO) TransferObjectFactory.populateData("billing.masters.vo.ClientChargeMstVO", fb);
			wb = bo.groupCmb1(vo,"1");

			grpStr1 = hisutil.getOptionValue(wb, vo.getStrPackGroupName(),"0^Select Value", true);
			 vo.setGroupCombo1(grpStr1);
			 //fb = (ClientChargeMstFB) TransferObjectFactory.populateData("billing.masters.controller.fb.ClientChargeMstFB", vo);
			 //HelperMethods.populate(fb,vo);
		} 
		catch (Exception e) 
		{
			new HisException("billing", "ClientChargeMstDATA.getGroupCombo1()", e.getMessage());
		}
         
		return grpStr1;
	}
	/**
	 * to get TariffCombo.
	 * 
	 * @param fb
	 * @return String
	 */
	public String getTariffCombo(ClientChargeMstFB fb) {
		

		String strTariffCmb = "";
		HisUtil hisutil = new HisUtil("Billing", "VOClientChargeMst");
		
         ClientChargeMstBO bo = new ClientChargeMstBO();
		
		ClientChargeMstVO vo = null;
		
		WebRowSet wb = null;

		try {
            vo = new ClientChargeMstVO();
			
			vo = (ClientChargeMstVO) TransferObjectFactory.populateData("billing.masters.vo.ClientChargeMstVO", fb);
			wb = bo.tariffCmb(vo);

			strTariffCmb = hisutil.getOptionValue(wb, vo.getStrTariffName(),"0^Select Value", true, false);
			
			//fb = (ClientChargeMstFB) TransferObjectFactory.populateData("billing.masters.controller.fb.ClientChargeMstFB", vo);
			//HelperMethods.populate(fb,vo);
		} catch (Exception e) {
			new HisException("billing", "ClientChargeMstDATA.getTariffCombo()", e
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
	public String getTariffCombo1(ClientChargeMstFB fb) 
	{		
        ClientChargeMstBO bo = new ClientChargeMstBO();		
		ClientChargeMstVO vo = null;
		String strTariffCmb1 = "";
		HisUtil hisutil = new HisUtil("Billing", "VOClientChargeMst");
		WebRowSet wb = null;

		try 
		{
	            vo = new ClientChargeMstVO();
				vo = (ClientChargeMstVO) TransferObjectFactory.populateData("billing.masters.vo.ClientChargeMstVO", fb);
				wb = bo.tariffCmb1(vo);
				strTariffCmb1 = hisutil.getOptionValue(wb, vo.getStrTariffName(),"0^Select Value", true, false);
				vo.setTariffCombo1(strTariffCmb1);			
		} 
		catch (Exception e) 
		{
			new HisException("billing", "ClientChargeMstDATA.getTariffCombo()", e.getMessage());
		}

		return strTariffCmb1;
	}
	/**
	 * to get UnitCombo.
	 * 
	 * @param fb
	 * @return String
	 */
	public String getUnitCombo(ClientChargeMstFB fb) 
	{		
		ClientChargeMstBO bo = new ClientChargeMstBO();		
		ClientChargeMstVO vo = null;
		String strUnitCmb = "";
		HisUtil hisutil = new HisUtil("Billing", "ClientChargeMstFB");		
		WebRowSet wb = null;

		try 
		{
			 	vo = new ClientChargeMstVO();
				vo = (ClientChargeMstVO) TransferObjectFactory.populateData("billing.masters.vo.ClientChargeMstVO", fb);
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
			
			//fb = (ClientChargeMstFB) TransferObjectFactory.populateData("billing.masters.controller.fb.ClientChargeMstFB", vo);
			//HelperMethods.populate(fb,vo);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			new HisException("billing", "ClientChargeMstDATA.getUnitCombo()", e.getMessage());
		}

		return strUnitCmb;
	}
	/**
	 * to get WardCombo.
	 * 
	 * @param fb
	 * @return String
	 */
	public String getWardCombo(ClientChargeMstFB fb) {
		
         ClientChargeMstBO bo = new ClientChargeMstBO();
		
		ClientChargeMstVO vo = null;
		String strWardCmb = "";
		HisUtil hisutil = new HisUtil("Billing", "VOClientChargeMst");
		
	
		WebRowSet wb = null;

		try {
			vo = new ClientChargeMstVO();
			
			vo = (ClientChargeMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.ClientChargeMstVO", fb);
			wb = bo.wardCmb(vo);
			strWardCmb = hisutil.getOptionValue(wb, vo.getStrWardType(),
					"0^Select Value", false);
			
			//fb = (ClientChargeMstFB) TransferObjectFactory.populateData("billing.masters.controller.fb.ClientChargeMstFB", vo);
			//HelperMethods.populate(fb,vo);
		} catch (Exception e) {
			new HisException("billing", "ClientChargeMstDATA.getWardCombo()", e
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
	public String getDataOnPack(ClientChargeMstFB fb) {
		String strData = "";
        
		
		ClientChargeMstVO vo = null;
		ClientChargeMstHLP hlp = new ClientChargeMstHLP();
		try {
        vo = new ClientChargeMstVO();
			
			vo = (ClientChargeMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.ClientChargeMstVO", fb);
			
			strData = hlp.getDataFrmPack(vo, vo.getStrTariffName());
			fb = (ClientChargeMstFB) TransferObjectFactory.populateData(
					"billing.masters.controller.fb.ClientChargeMstFB", vo);
			HelperMethods.populate(fb,vo);

		} catch (Exception e) {
			new HisException("Billing", "ClientChargeMstDATA.getDataOnPack -->", e
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
	public String getStrHospitalServiceCombo(ClientChargeMstFB fb) {
		HisUtil hisutil = new HisUtil("Billing", "VOClientChargeMst.getHospService()");
		BillingComboDAO cmb = new BillingComboDAO("billing", "VOClientChargeMst");
		WebRowSet wb = null;
		String strHserv = "";
		ClientChargeMstVO vo = null;
		try 
		{
			vo = new ClientChargeMstVO();
			
			vo = (ClientChargeMstVO) TransferObjectFactory.populateData("billing.masters.vo.ClientChargeMstVO", fb);
			wb = cmb.getClientChargeTypeDtl(vo, false);
			if(fb.getHospitalService()==null)
				fb.setHospitalService("0");
	
			strHserv = hisutil.getOptionValue(wb, fb.getHospitalService(),"0^Select Value", false);
			
			fb.setStrHospitalServiceCombo(strHserv);
			//fb = (ClientChargeMstFB) TransferObjectFactory.populateData("billing.masters.controller.fb.ClientChargeMstFB", vo);
			//HelperMethods.populate(fb,vo);
		} 
		catch (Exception e) 
		{
			new HisException("billing", "ClientChargeMstDATA.getStrHospitalService()",e.getMessage());
		}

		return strHserv;

	}
	/**
	 * to get PatientCategory Combo.
	 * 
	 * @param fb
	 * @return String
	 */
	public String getPatientCategory(ClientChargeMstFB fb) 
	{
		HisUtil hisutil = new HisUtil("Billing", "VOClientChargeMst");
		HisDAO hisdao = new HisDAO("billing.DAOClientChargeMst", "getPreviousData()");
		int nIndex;
		WebRowSet wb = null;
		ClientChargeMstVO vo = null;
		String patientCategory="";
		
		try 
		{
           vo = new ClientChargeMstVO();
           vo = (ClientChargeMstVO) TransferObjectFactory.populateData("billing.masters.vo.ClientChargeMstVO", fb);

			String str = billing.qryHandler_billing.getQuery(1, "gbl.creditcat.1");
			nIndex = hisdao.setQuery(str);
			
			hisdao.setQryValue(nIndex, 1, fb.getStrHospitalCode());
			hisdao.setQryValue(nIndex, 2, fb.getStrHospitalCode());
			hisdao.setQryValue(nIndex, 3, vo.getStrGroupId());
			hisdao.setQryValue(nIndex, 4, vo.getStrTariffName());
			hisdao.setQryValue(nIndex, 5, vo.getStrPackHospService());		
			hisdao.setQryValue(nIndex, 6, vo.getStrWardType());
			wb=hisdao.executeQry(nIndex);
		
			patientCategory = hisutil.getOptionValue(wb, vo.getStrTempCategory(), "0^Select Value", false);
			fb = (ClientChargeMstFB) TransferObjectFactory.populateData("billing.masters.controller.fb.ClientChargeMstFB", vo);
			HelperMethods.populate(fb,vo);
		} 
		catch (Exception e) 
		{
	 		new HisException("Billing", "ClientChargeMstDATA.getPatientCategory -->", e.getMessage());
		}
		
		return patientCategory;
	}
	public String getOrganization(ClientChargeMstFB fb) 
	{
		HisUtil hisutil = new HisUtil("Billing", "VOClientChargeMst");
		HisDAO hisdao = new HisDAO("billing.DAOClientChargeMst", "getOrganization()");
		int nIndex;
		WebRowSet wb = null;
		ClientChargeMstVO vo = null;
		String organization="";
		
		try 
		{
           vo = new ClientChargeMstVO();
           vo = (ClientChargeMstVO) TransferObjectFactory.populateData("billing.masters.vo.ClientChargeMstVO", fb);

			String str = billing.qryHandler_billing.getQuery(1, "gbl.org.1");
			nIndex = hisdao.setQuery(str);
			
			hisdao.setQryValue(nIndex, 1, fb.getStrHospitalCode());
			wb=hisdao.executeQry(nIndex);
		
			organization = hisutil.getOptionValue(wb, vo.getStrOrg(), "0^Select Value", false);
			fb = (ClientChargeMstFB) TransferObjectFactory.populateData("billing.masters.controller.fb.ClientChargeMstFB", vo);
			HelperMethods.populate(fb,vo);
		} 
		catch (Exception e) 
		{
	 		new HisException("Billing", "ClientChargeMstDATA.getOrganization -->", e.getMessage());
		}
		
		return organization;
	}
}
