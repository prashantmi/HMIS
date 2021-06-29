package billing.masters.dao;
/* Charge Master DAO
 * author: Debashis Sardar
 * Created on : 06-Sep-2011
 */
import hisglobal.transactionmgnt.HisDAO;
import javax.sql.rowset.WebRowSet;
import billing.BillConfigUtil;
import billing.qryHandler_billing;
import billing.masters.controller.hlp.ClientChargeMstHLP;
import billing.masters.vo.ClientChargeMstVO;

public class ClientChargeMstDAO {

	/**
	 * to retrieve package data from database.
	 * @param vo
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static WebRowSet getDataOnPackage(String str, ClientChargeMstVO vo)
			throws Exception {
		String strQuery = billing.qryHandler_billing.getQuery(1,
				"select.chargeMst.4");
		HisDAO hisdao = new HisDAO("billing.DAOClientChargeMst", "getDataOnPackage()");
		WebRowSet wb = null;
		int nIndex = 0;
		try {
			nIndex = hisdao.setQuery(strQuery);

			hisdao.setQryValue(nIndex, 1, vo.getStrHospitalCode());
			hisdao.setQryValue(nIndex, 2, str);
			wb = hisdao.executeQry(nIndex);

		} catch (Exception e) {
			
			vo.setStrMsgType("1");
			
			vo.setStrMsgString("DAOClientChargeMst.getDataOnPackage() --> "
				+ e.getMessage());
		} finally {
			hisdao.free();
			hisdao = null;
		}
		return wb;
	}

	/**
	 * insert data on submitting the add page.
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static boolean insertRecord(ClientChargeMstVO vo) throws Exception 
	{
		boolean fretVal = false;
		String[] strAllCatCode=null;
		WebRowSet wb = null;
		WebRowSet WBinsetedCatCode = null;
		HisDAO hisdao = new HisDAO("billing.DAOClientChargeMst", "insertRecord()");
		int nIndex1, nIndex2, nIndex3,nIndex4;
		String strQuery1 = billing.qryHandler_billing.getQuery(1,"insert.clientchargeMst.0");
		String strQuery2 = billing.qryHandler_billing.getQuery(1,"insert.chargeMst.1");
		
		try 
		{
			nIndex1 = hisdao.setQuery(strQuery1);
			if (vo.getStrIsPackage().equals("0")) 
			{
				if (!vo.getStrTariffName().equals("0")) 
				{
					String[] strTempTrf = vo.getStrTariffName().replace('^','#').split("#");
					vo.setStrTariffName(strTempTrf[0]);
				}
				int nj = 0;
				if (vo.getStrTariffCost().length > 0) 
				{
					while (nj < vo.getStrTariffCost().length) 
					{
						hisdao.setQryValue(nIndex1, 1, vo.getStrHospitalCode());
						hisdao.setQryValue(nIndex1, 2, vo.getHospitalService());
						hisdao.setQryValue(nIndex1, 3, vo.getStrTariffName());
						hisdao.setQryValue(nIndex1, 4, vo.getStrWardType());
						hisdao.setQryValue(nIndex1, 5, vo.getHospitalService());
						hisdao.setQryValue(nIndex1, 6, vo.getStrTariffName());
						hisdao.setQryValue(nIndex1, 7, vo.getStrPatientCategory()[nj]);
						hisdao.setQryValue(nIndex1, 8, vo.getStrWardType());
						hisdao.setQryValue(nIndex1, 9, vo.getStrHospitalCode());
						hisdao.setQryValue(nIndex1, 10, vo.getStrGroupName());
						hisdao.setQryValue(nIndex1, 11, vo.getStrUnit());
						hisdao.setQryValue(nIndex1, 12, vo.getStrPatientCategory()[nj]);
						//hisdao.setQryValue(nIndex1, 13,	vo.getStrProductCost()[nj]);
						hisdao.setQryValue(nIndex1, 13,	"0");
						hisdao.setQryValue(nIndex1, 14,	vo.getStrTariffCost()[nj]);
						//hisdao.setQryValue(nIndex1, 15,	vo.getStrTotalCost()[nj]);
						hisdao.setQryValue(nIndex1, 15,	"0");
						//hisdao.setQryValue(nIndex1, 16,	vo.getStrIsAdvance()[nj]);
						//hisdao.setQryValue(nIndex1, 17,	vo.getStrIsRefundable()[nj]);
						hisdao.setQryValue(nIndex1, 16, vo.getStrEffectiveFrm());
						//hisdao.setQryValue(nIndex1, 19, vo.getStrEffectiveTo());
						hisdao.setQryValue(nIndex1, 17, vo.getStrRemarks());
						hisdao.setQryValue(nIndex1, 18, vo.getStrSeatId());
						hisdao.setQryValue(nIndex1, 19, "1");
						//hisdao.setQryValue(nIndex1, 22, vo.getStrIsPackage());
						hisdao.setQryValue(nIndex1, 20, vo.getStrOrg());
						hisdao.execute(nIndex1, 0);

						nj++;
					}
					nj = 0;
				}
			} 
			else 
			{
				String[] strTempGrp = vo.getStrPackGroupName().replace('^', '#').split("#");
				vo.setStrPackGroupName(strTempGrp[0]);
				String[] strTempTrf = vo.getStrPackTariffName().replace('^','#').split("#");
				vo.setStrPackTariffName(strTempTrf[0]);				
				hisdao.setQryValue(nIndex1, 1, vo.getStrHospitalCode());
				hisdao.setQryValue(nIndex1, 2, vo.getStrPackHospService());
				hisdao.setQryValue(nIndex1, 3, vo.getStrPackTariffName());
				hisdao.setQryValue(nIndex1, 4, vo.getStrPackWardType());
				hisdao.setQryValue(nIndex1, 5, vo.getStrPackHospService());
				hisdao.setQryValue(nIndex1, 6, vo.getStrPackTariffName());
				hisdao.setQryValue(nIndex1, 7, vo.getStrPackPatientCategory());
			    hisdao.setQryValue(nIndex1, 8, vo.getStrPackWardType());
				hisdao.setQryValue(nIndex1, 9, vo.getStrHospitalCode());
				hisdao.setQryValue(nIndex1, 10, vo.getStrPackGroupName());
				hisdao.setQryValue(nIndex1, 11, vo.getStrPackUnit());
				hisdao.setQryValue(nIndex1, 12, vo.getStrPackPatientCategory());
				//hisdao.setQryValue(nIndex1, 13, vo.getStrPackProcdCost());
				hisdao.setQryValue(nIndex1, 14, vo.getStrPackTariffCost());
				//hisdao.setQryValue(nIndex1, 15, vo.getStrPackTotalCost());
				//hisdao.setQryValue(nIndex1, 16, vo.getStrPackIsAdvance());
				//hisdao.setQryValue(nIndex1, 17, vo.getStrPackIsRefundable());
				hisdao.setQryValue(nIndex1, 16, vo.getStrEffectiveFrm());
				//hisdao.setQryValue(nIndex1, 19, vo.getStrEffectiveTo());
				hisdao.setQryValue(nIndex1, 17, vo.getStrRemarks());
				hisdao.setQryValue(nIndex1, 18, vo.getStrSeatId());
				hisdao.setQryValue(nIndex1, 19, "1");
				//hisdao.setQryValue(nIndex1, 22, vo.getStrIsPackage());
				hisdao.setQryValue(nIndex1, 20, vo.getStrOrg());
			    hisdao.execute(nIndex1, 0);

				int nj = 0;
				
					
				nIndex2 = hisdao.setQuery(strQuery2);
				if (vo.getStrPackTrfId() != null) 
				{
					for (nj = 0; nj < vo.getStrPackTotal().length; nj++) 
					{					
						hisdao.setQryValue(nIndex2, 1, vo.getStrHospitalCode());
						hisdao.setQryValue(nIndex2, 2, vo.getStrPackHospService());
						hisdao.setQryValue(nIndex2, 3, vo.getStrPackPatientCategory());
						hisdao.setQryValue(nIndex2, 4, vo.getStrPackWardType());
						hisdao.setQryValue(nIndex2, 5, vo.getStrPackTariffName());
						hisdao.setQryValue(nIndex2, 6, vo.getStrPackUnit());
						hisdao.setQryValue(nIndex2, 7, vo.getStrPackHospService());
					    hisdao.setQryValue(nIndex2, 8, vo.getStrPackPatientCategory());
						hisdao.setQryValue(nIndex2, 9, vo.getStrPackWardType());
						hisdao.setQryValue(nIndex2, 10, vo.getStrPackTariffName());
						hisdao.setQryValue(nIndex2, 11, vo.getStrPackTrfId()[nj]);
						hisdao.setQryValue(nIndex2, 12, vo.getStrHospitalCode());
					    hisdao.setQryValue(nIndex2, 13,	vo.getStrPackTrfId()[nj]);
						hisdao.setQryValue(nIndex2, 14, vo.getStrPackGroupName());
						hisdao.setQryValue(nIndex2, 15,	vo.getStrPackProcdCost());//vo.getStrPackProcCost()[nj] removed since procedure cost is disabled.
						hisdao.setQryValue(nIndex2, 16, vo.getStrPackTrfCost()[nj]);
						hisdao.setQryValue(nIndex2, 17, vo.getStrPackTotal()[nj]);
						hisdao.setQryValue(nIndex2, 18, vo.getStrRefundable()[nj]);
						hisdao.setQryValue(nIndex2, 19, vo.getStrEffectiveFrm());
					    //hisdao.setQryValue(nIndex2, 20, vo.getStrEffectiveTo());
						hisdao.setQryValue(nIndex2, 20, vo.getStrRemarks());
						hisdao.setQryValue(nIndex2, 21, vo.getStrSeatId());
						hisdao.setQryValue(nIndex2, 22, "1");
						hisdao.setQryValue(nIndex2, 23, vo.getStrPackQty()[nj]);
						hisdao.execute(nIndex2, 0);
					}
				}
			}

			synchronized (hisdao) 
			{
				hisdao.fire();
				fretVal = true;
			}
		} 
		catch (Exception e) 
		{			
			vo.setStrMsgType("1");			
			vo.setStrMsgString("DAOpackservMst.addData() --> "+ e.getMessage());			
		} 
		finally 
		{
			hisdao.free();
			hisdao = null;
		}
		
		return fretVal;
	}

	/**
	 * retrieve data from the database,which is shown in modify page.
	 * 
	 * @param vo
	 * @param chk
	 * @param chkValue
	 * @return
	 * @throws Exception
	 */
	public static boolean getData(ClientChargeMstVO vo, String[] chk , String chkValue )
			throws Exception {
		boolean fResult = false;
		String strQuery = "";
		HisDAO hisdao = new HisDAO("billing.DAOClientChargeMst", "getData()");
		WebRowSet wb = null;
		int nIndex = 0;
		try 
		{
		//	vo.setStrIsPackage(chk[7].replace("@", "#").split("#")[0]);
			vo.setStrIsPackage("0");
			if(!vo.getStrIsPackage().equals("0"))//Package
			{
				strQuery = billing.qryHandler_billing.getQuery(1,"select.chargeMst.55");
				nIndex = hisdao.setQuery(strQuery);
		
				//hisdao.setQryValue(nIndex, 1, BillConfigUtil.SUPER_HOSPITAL_CODE);
				hisdao.setQryValue(nIndex, 1, chk[6]);
				hisdao.setQryValue(nIndex, 2, chk[0]);
				hisdao.setQryValue(nIndex, 3, chk[4]);
				hisdao.setQryValue(nIndex, 4, chk[1]);
				hisdao.setQryValue(nIndex, 5, chk[3]);
				hisdao.setQryValue(nIndex, 6, chk[2]);
				hisdao.setQryValue(nIndex, 7, chk[5]);
				//hisdao.setQryValue(nIndex, 8, chk[2]);	
			}
			else//Non Package
			{
				strQuery = billing.qryHandler_billing.getQuery(1,"select.clientchargeMst.5");
				nIndex = hisdao.setQuery(strQuery);
		
				String strVal = chkValue.replace("|", "#").split("#")[3].replace("$", "#").split("#")[0];
				
				hisdao.setQryValue(nIndex, 1, BillConfigUtil.SUPER_HOSPITAL_CODE);
				hisdao.setQryValue(nIndex, 2, vo.getStrHospitalCode());
				hisdao.setQryValue(nIndex, 3, chk[2]);
				hisdao.setQryValue(nIndex, 4, strVal);
				hisdao.setQryValue(nIndex, 5, chk[5]); // added by Pawan Kumar BN for CLIENT CODE
			}

			wb = hisdao.executeQry(nIndex);
			vo.setWbModifyData(wb);
			
			String[] strProcCost = new String[wb.size()];
			String[] strTrfCost = new String[wb.size()];
			String[] strTotCost = new String[wb.size()];
		//	String[] strIsAdv = new String[wb.size()];
		//	String[] strIsRef = new String[wb.size()];
			int ni = 0;
			while (wb.next()) 
			{
				vo.setHospitalService(wb.getString(1));
				vo.setStrPackHospService(wb.getString(1));
				vo.setStrTariffName(wb.getString(2));
				vo.setStrPackTariffName(wb.getString(2));
				vo.setStrPatientCategoryMod(wb.getString(3));
				vo.setStrPackPatientCategory(wb.getString(3));
				vo.setStrWardType(wb.getString(4));
				vo.setStrPackWardType(wb.getString(4));
				vo.setStrChargeSlNo(wb.getString(5));
				vo.setStrGroupName(wb.getString(6));
				vo.setStrPackGroupName(wb.getString(6));
				strProcCost[ni] = wb.getString(7);
				vo.setStrPackProcdCost(wb.getString(7));
				strTrfCost[ni] = wb.getString(8);
				vo.setStrPackTariffCost(wb.getString(8));
				strTotCost[ni] = wb.getString(9);
				vo.setStrUnit(wb.getString(10));
				vo.setStrPackUnit(wb.getString(10));
			//	strIsAdv[ni] = wb.getString(11);
			//	vo.setStrPackIsAdvance(wb.getString(11));
			//	strIsRef[ni] = wb.getString(12);
			//	vo.setStrPackIsRefundable(wb.getString(12));
				vo.setStrEffectiveFrm(wb.getString(11));
				vo.setStrEffectiveTo(wb.getString(12));
				vo.setStrRemarks(wb.getString(13));
				vo.setStrSeatId(wb.getString(14));
				vo.setStrPatCatName(wb.getString(18));
				vo.setStrOrg(wb.getString(21));
				vo.setStrOrgName(wb.getString(22));
				ni++;
			}

			vo.setStrProductCost(strProcCost);
			vo.setStrTariffCost(strTrfCost);
			vo.setStrTotalCost(strTotCost);
		//	vo.setStrIsAdvance(strIsAdv);
			//vo.setStrIsRefundable(strIsRef);

			wb = null;

			if (!vo.getStrIsPackage().equals("0")) 
			{
				int nIndex1 = 0;
				String strQuery1 = billing.qryHandler_billing.getQuery(1,"select.chargeMst.6");
				nIndex1 = hisdao.setQuery(strQuery1);
				for (int nj = 0; nj < chk.length - 2; nj++) 
				{
					hisdao.setQryValue(nIndex1, nj + 1, chk[nj]);
				}
				wb = hisdao.executeQry(nIndex1);
				vo.setPackageDetails(wb);

				ClientChargeMstHLP.getDataFrmPackModify(vo);

				
			}
			fResult = true;
		} catch (Exception e) {
			fResult = false;
            vo.setStrMsgType("1");
			
			vo.setStrMsgString("DAOClientChargeMst.getData() --> "
				+ e.getMessage());
			
		}
		
		return fResult;
	}

	/**
	 * checks conditions if data can be updated in modify page or not.
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static boolean isValidUpdation(ClientChargeMstVO vo) throws Exception {
		int nIndex = 0;
		boolean fRes = false;
		String strQry = "";
		WebRowSet wb = null;
		int nCount = 0;
		HisDAO hisdao = new HisDAO("billing.DAOClientChargeMst", "isValidUpdation()");
		try {
			strQry = billing.qryHandler_billing.getQuery(1,
					"select.chargeMst.7");
			nIndex = hisdao.setQuery(strQry);

			hisdao.setQryValue(nIndex, 1, vo.getHospitalService());
			hisdao.setQryValue(nIndex, 2, vo.getStrPatientCategoryMod());
			hisdao.setQryValue(nIndex, 3, vo.getStrWardType());
			hisdao.setQryValue(nIndex, 4, vo.getStrHospitalCode());
			hisdao.setQryValue(nIndex, 5, vo.getStrTariffName());
			hisdao.setQryValue(nIndex, 6, vo.getStrEffectiveFrm());
			hisdao.setQryValue(nIndex, 7, vo.getStrEffectiveTo());
			hisdao.setQryValue(nIndex, 8, vo.getStrEffectiveTo());

			wb = hisdao.executeQry(nIndex);
			if (wb.next()) {
				nCount = Integer.parseInt(wb.getString(1));
			}
			if (nCount == 0) {
				fRes = true;
			} else {
				fRes = false;
			}
		} catch (Exception e) {
			fRes = false;
            vo.setStrMsgType("1");
			
			vo.setStrMsgString("DAOClientChargeMst.isValidUpdation() --> "
				+ e.getMessage());
			
		}
		return fRes;
	}
	/**
	 * insert data in modification mode of modify page.
	 * 
	 * @param vo
	 * @param chk
	 * @return
	 * @throws Exception
	 */
	public static boolean newInsertData(ClientChargeMstVO vo, String chk)
			throws Exception {
		boolean fVal = false;
		int nIndex1 = 0;
		int nIndex2 = 0;
		int nIndex3 = 0;
		
		int nIndex5 = 0;

		String strQry1 = "";
		String strQry2 = "";
		String strQry3 = "";
		
		String strQry5 = "";

		HisDAO hisdao = new HisDAO("Billing.DAOClientChargeMst()", "updateData()");
		try {
			String[] strT1 = chk.replace('$', '#').split("#");
			String[] strT2 = strT1[0].replace('@', '#').split("#");

			vo.setStrHospitalCode(strT2[0]);
			vo.setHospitalService(strT2[1]);
			vo.setStrTariffName(strT2[2]);
			vo.setStrTempCategory(strT2[3]);
			vo.setStrWardType(strT2[4]);
			vo.setStrIsPackage(strT2[5].replace('|', '#').split("#")[1]);

			vo.setStrGroupId(strT2[5].replace('|', '#').split("#")[4]);
			
			vo.setStrGroupName(vo.getStrGroupId());
			
			strQry3 = qryHandler_billing.getQuery(1, "update.ClientChargeMst.2");
			nIndex3 = hisdao.setQuery(strQry3);
			hisdao.setQryValue(nIndex3, 1, vo.getStrEffectiveFrm());
			hisdao.setQryValue(nIndex3, 2, vo.getStrHospitalCode());
			hisdao.setQryValue(nIndex3, 3, vo.getHospitalService());
			hisdao.setQryValue(nIndex3, 4, vo.getStrTariffName());
			hisdao.setQryValue(nIndex3, 5, vo.getStrPatientCategoryMod());
			hisdao.setQryValue(nIndex3, 6, vo.getStrWardType());
			hisdao.setQryValue(nIndex3, 7, vo.getStrEffectiveFrm());

			

			hisdao.execute(nIndex3, 0);

			if (vo.getStrIsPackage() == "0") {

				strQry1 = billing.qryHandler_billing.getQuery(1,
						"insert.ClientChargeMst.0");
				nIndex1 = hisdao.setQuery(strQry1);
				
				hisdao.setQryValue(nIndex1, 1, vo.getStrHospitalCode());
				hisdao.setQryValue(nIndex1, 2, vo.getHospitalService());
				hisdao.setQryValue(nIndex1, 3, vo.getStrTariffName());
				hisdao.setQryValue(nIndex1, 4, vo.getStrWardType());
				hisdao.setQryValue(nIndex1, 5, vo.getHospitalService());
				hisdao.setQryValue(nIndex1, 6, vo.getStrTariffName());
				hisdao.setQryValue(nIndex1, 7, vo.getStrPatientCategoryMod());
				hisdao.setQryValue(nIndex1, 8, vo.getStrWardType());
				hisdao.setQryValue(nIndex1, 9, vo.getStrHospitalCode());
				hisdao.setQryValue(nIndex1, 10, vo.getStrGroupName());
				hisdao.setQryValue(nIndex1, 11, vo.getStrUnit());
				hisdao.setQryValue(nIndex1, 12, vo.getStrPatientCategoryMod());
				hisdao.setQryValue(nIndex1, 13, vo.getStrProductCost()[0]);
				hisdao.setQryValue(nIndex1, 14, vo.getStrTariffCost()[0]);
				hisdao.setQryValue(nIndex1, 15, vo.getStrTotalCost()[0]);
				hisdao.setQryValue(nIndex1, 16, vo.getStrIsAdvance()[0]);
				hisdao.setQryValue(nIndex1, 17, vo.getStrIsRefundable()[0]);
				hisdao.setQryValue(nIndex1, 18, vo.getStrEffectiveFrm());
				hisdao.setQryValue(nIndex1, 19, vo.getStrEffectiveTo());
				hisdao.setQryValue(nIndex1, 20, vo.getStrRemarks());
				hisdao.setQryValue(nIndex1, 21, vo.getStrSeatId());
				hisdao.setQryValue(nIndex1, 22, "1");
				hisdao.setQryValue(nIndex1, 23, vo.getStrIsPackage());

				hisdao.execute(nIndex1, 0);

			} else {
				strQry2 = billing.qryHandler_billing.getQuery(1,
						"insert.ClientChargeMst.0");

				nIndex2 = hisdao.setQuery(strQry2);

				

				hisdao.setQryValue(nIndex2, 1, vo.getStrHospitalCode());
				hisdao.setQryValue(nIndex2, 2, vo.getStrPackHospService());
				hisdao.setQryValue(nIndex2, 3, vo.getStrPackTariffName());
				hisdao.setQryValue(nIndex2, 4, vo.getStrPackWardType());
				hisdao.setQryValue(nIndex2, 5, vo.getStrPackHospService());
				hisdao.setQryValue(nIndex2, 6, vo.getStrPackTariffName());
				hisdao.setQryValue(nIndex2, 7, vo.getStrPackPatientCategory());
				hisdao.setQryValue(nIndex2, 8, vo.getStrPackWardType());
				hisdao.setQryValue(nIndex2, 9, vo.getStrHospitalCode());
				hisdao.setQryValue(nIndex2, 10, vo.getStrPackGroupName());
				hisdao.setQryValue(nIndex2, 11, vo.getStrPackUnit());
				hisdao.setQryValue(nIndex2, 12, vo.getStrPackPatientCategory());
				hisdao.setQryValue(nIndex2, 13, vo.getStrPackProcdCost());
				hisdao.setQryValue(nIndex2, 14, vo.getStrPackTariffCost());
				hisdao.setQryValue(nIndex2, 15, vo.getStrPackTotalCost());
				hisdao.setQryValue(nIndex2, 16, vo.getStrPackIsAdvance());
				hisdao.setQryValue(nIndex2, 17, vo.getStrPackIsRefundable());
				hisdao.setQryValue(nIndex2, 18, vo.getStrEffectiveFrm());
				hisdao.setQryValue(nIndex2, 19, vo.getStrEffectiveTo());
				hisdao.setQryValue(nIndex2, 20, vo.getStrRemarks());
				hisdao.setQryValue(nIndex2, 21, vo.getStrSeatId());
				hisdao.setQryValue(nIndex2, 22, "1");
				hisdao.setQryValue(nIndex2, 23, vo.getStrIsPackage());

				hisdao.execute(nIndex2, 0);

				
				strQry5 = billing.qryHandler_billing.getQuery(1,
						"insert.ClientChargeMst.3");
				nIndex5 = hisdao.setQuery(strQry5);

				
				if(vo.getStrPackTotal() != null && vo.getStrPackTotal().length > 0)
				for (int ni = 0; ni < vo.getStrPackTotal().length; ni++) {

					

					hisdao.setQryValue(nIndex5, 1, vo.getStrHospitalCode());
					hisdao.setQryValue(nIndex5, 2, vo.getHospitalService());
					hisdao.setQryValue(nIndex5, 3, vo
							.getStrPatientCategoryMod());
					hisdao.setQryValue(nIndex5, 4, vo.getStrWardType());
					hisdao.setQryValue(nIndex5, 5, vo.getStrTariffName());
					hisdao.setQryValue(nIndex5, 6, vo.getStrUnit());
					hisdao.setQryValue(nIndex5, 7, vo.getHospitalService());
					hisdao.setQryValue(nIndex5, 8, vo
							.getStrPatientCategoryMod());
					hisdao.setQryValue(nIndex5, 9, vo.getStrWardType());
					hisdao.setQryValue(nIndex5, 10, vo.getStrTariffName());
					hisdao.setQryValue(nIndex5, 11, vo.getStrPackTrfId()[ni]);
					hisdao.setQryValue(nIndex5, 12, vo.getStrHospitalCode());
					hisdao.setQryValue(nIndex5, 13, vo.getStrPackTrfId()[ni]);
					hisdao.setQryValue(nIndex5, 14, vo.getStrGroupName());
					hisdao
							.setQryValue(nIndex5, 15,
									vo.getStrPackProcCost()[ni]);
					hisdao.setQryValue(nIndex5, 16, vo.getStrPackTrfCost()[ni]);
					hisdao.setQryValue(nIndex5, 17, vo.getStrPackTotal()[ni]);
					hisdao.setQryValue(nIndex5, 18, vo.getStrRefundable()[ni]);
					hisdao.setQryValue(nIndex5, 19, vo.getStrEffectiveFrm());
					hisdao.setQryValue(nIndex5, 20, vo.getStrEffectiveTo());
					hisdao.setQryValue(nIndex5, 21, vo.getStrSeatId());
					hisdao.setQryValue(nIndex5, 22, "1");
					hisdao.execute(nIndex5, 0);

				}
			}

			synchronized (hisdao) {
				hisdao.fire();
				fVal = true;
			}

		} catch (Exception e) {
			fVal = false;
	           vo.setStrMsgType("1");
				
				vo.setStrMsgString("DAOClientChargeMst.updateData() --> "
					+ e.getMessage());
  
			
		}
		
		return fVal;

	}

	/**
	 * for correction part.
	 * 
	 * @param vo
	 * @param chk
	 * @return
	 * @throws Exception
	 */
	public static boolean modifyData(ClientChargeMstVO vo, String chk)
			throws Exception {
		boolean fVal = false;
		int nIndex1 = 0;
		int nIndex2 = 0;
		int nIndex3 = 0;
		int nIndex4 = 0;

		String strQry1 = "";
		String strQry2 = "";
		String strQry3 = "";
		String strQry4 = "";

		HisDAO hisdao = new HisDAO("billing.DAOClientChargeMst", "modifyData");

		try {

			String[] strT1 = chk.replace('$', '#').split("#");
			String[] strT2 = strT1[0].replace('@', '#').split("#");

			vo.setStrHospitalCode(strT2[0]);
			vo.setHospitalService(strT2[1]);
			vo.setStrTariffName(strT2[2]);
			vo.setStrWardType(strT2[4]);
			vo.setStrChargeSlNo(strT2[5].replace('|', '#').split("#")[0]);
			vo.setStrIsPackage(strT2[5].replace('|', '#').split("#")[1]);

			if (vo.getStrIsPackage().equals("0")) {
				strQry1 = billing.qryHandler_billing.getQuery(1,
						"update.ClientChargeMst.0");
				nIndex1 = hisdao.setQuery(strQry1);

				hisdao.setQryValue(nIndex1, 1, vo.getStrProductCost()[0]);
				hisdao.setQryValue(nIndex1, 2, vo.getStrTariffCost()[0]);
				
				hisdao.setQryValue(nIndex1, 3, vo.getStrTotalCost()[0]);
				hisdao.setQryValue(nIndex1, 4, vo.getStrIsAdvance()[0]);
				hisdao.setQryValue(nIndex1, 5, vo.getStrIsRefundable()[0]);
				hisdao.setQryValue(nIndex1, 6, vo.getStrEffectiveFrm());
				hisdao.setQryValue(nIndex1, 7, vo.getStrEffectiveTo());
				hisdao.setQryValue(nIndex1, 8, vo.getStrSeatId());
				hisdao.setQryValue(nIndex1, 9, vo.getStrRemarks());
				hisdao.setQryValue(nIndex1, 10, vo.getStrHospitalCode());
				hisdao.setQryValue(nIndex1, 11, vo.getHospitalService());
				hisdao.setQryValue(nIndex1, 12, vo.getStrTariffName());
				hisdao.setQryValue(nIndex1, 13, vo.getStrPatientCategoryMod());
				hisdao.setQryValue(nIndex1, 14, vo.getStrWardType());
				hisdao.setQryValue(nIndex1, 15, vo.getStrChargeSlNo());

				hisdao.execute(nIndex1, 0);

			} else {

				strQry2 = billing.qryHandler_billing.getQuery(1,
						"update.ClientChargeMst.1");

				nIndex2 = hisdao.setQuery(strQry2);


				hisdao.setQryValue(nIndex2, 1, vo.getStrPackProcdCost());
				hisdao.setQryValue(nIndex2, 2, vo.getStrPackTariffCost());
				hisdao.setQryValue(nIndex2, 3, vo.getStrPackTotalCost());
				hisdao.setQryValue(nIndex2, 4, vo.getStrPackIsRefundable());
				hisdao.setQryValue(nIndex2, 5, vo.getStrPackIsAdvance());
				hisdao.setQryValue(nIndex2, 6, vo.getStrEffectiveFrm());
				hisdao.setQryValue(nIndex2, 7, vo.getStrEffectiveTo());
				hisdao.setQryValue(nIndex2, 8, vo.getStrRemarks());
				hisdao.setQryValue(nIndex2, 9, vo.getStrLastModiSeatId());
				hisdao.setQryValue(nIndex2, 10, vo.getStrHospitalCode());
				hisdao.setQryValue(nIndex2, 11, vo.getHospitalService());
				hisdao.setQryValue(nIndex2, 12, vo.getStrPatientCategoryMod());
				hisdao.setQryValue(nIndex2, 13, vo.getStrWardType());
				hisdao.setQryValue(nIndex2, 14, vo.getStrTariffName());
				hisdao.setQryValue(nIndex2, 15, vo.getStrChargeSlNo());

				hisdao.execute(nIndex2, 0);

		

				strQry4 = billing.qryHandler_billing.getQuery(1,
						"update.ClientChargeMst.11");

				nIndex4 = hisdao.setQuery(strQry4);

				
				hisdao.setQryValue(nIndex4, 1, vo.getStrLastModiSeatId());
				hisdao.setQryValue(nIndex4, 2, vo.getStrHospitalCode());
				hisdao.setQryValue(nIndex4, 3, vo.getHospitalService());
				hisdao.setQryValue(nIndex4, 4, vo.getStrPatientCategoryMod());
				hisdao.setQryValue(nIndex4, 5, vo.getStrWardType());
				hisdao.setQryValue(nIndex4, 6, vo.getStrTariffName());

				hisdao.execute(nIndex4, 0);

				strQry3 = billing.qryHandler_billing.getQuery(1,
						"insert.ClientChargeMst.3");
				nIndex3 = hisdao.setQuery(strQry3);

				for (int ni = 0; ni < vo.getStrPackTotal().length; ni++) {

					

					hisdao.setQryValue(nIndex3, 1, vo.getStrHospitalCode());
					hisdao.setQryValue(nIndex3, 2, vo.getHospitalService());
					hisdao.setQryValue(nIndex3, 3, vo
							.getStrPatientCategoryMod());
					hisdao.setQryValue(nIndex3, 4, vo.getStrWardType());
					hisdao.setQryValue(nIndex3, 5, vo.getStrTariffName());
					hisdao.setQryValue(nIndex3, 6, vo.getStrUnit());
					hisdao.setQryValue(nIndex3, 7, vo.getHospitalService());
					hisdao.setQryValue(nIndex3, 8, vo
							.getStrPatientCategoryMod());
					hisdao.setQryValue(nIndex3, 9, vo.getStrWardType());
					hisdao.setQryValue(nIndex3, 10, vo.getStrTariffName());
					hisdao.setQryValue(nIndex3, 11, vo.getStrPackTrfId()[ni]);
					hisdao.setQryValue(nIndex3, 12, vo.getStrHospitalCode());
					hisdao.setQryValue(nIndex3, 13, vo.getStrPackTrfId()[ni]);
					hisdao.setQryValue(nIndex3, 14, vo.getStrGroupName());
					hisdao
							.setQryValue(nIndex3, 15,
									vo.getStrPackProcCost()[ni]);
					hisdao.setQryValue(nIndex3, 16, vo.getStrPackTrfCost()[ni]);
					hisdao.setQryValue(nIndex3, 17, vo.getStrPackTotal()[ni]);
					hisdao.setQryValue(nIndex3, 18, vo.getStrRefundable()[ni]);
					hisdao.setQryValue(nIndex3, 19, vo.getStrEffectiveFrm());
					hisdao.setQryValue(nIndex3, 20, vo.getStrEffectiveTo());
					hisdao.setQryValue(nIndex3, 21, vo.getStrSeatId());
					hisdao.setQryValue(nIndex3, 22, "1");

					hisdao.execute(nIndex3, 0);

				}
			}

			synchronized (hisdao) {
				hisdao.fire();
				fVal = true;
			}

		} catch (Exception e) {

			fVal = false;
	           vo.setStrMsgType("1");
				
				vo.setStrMsgString("DAOClientChargeMst.modifyData() --> "
					+ e.getMessage());
			
		} finally {
			hisdao.free();

			hisdao = null;
		}
		return fVal;
	}

	/**
	 * for correction as well as update part.
	 * 
	 * @param vo
	 * @param chk
	 * @return
	 * @throws Exception
	 */
	public static boolean saveModifiedData(ClientChargeMstVO vo, String chk)
			throws Exception {
		boolean fVal = false;
		int nIndex1 = 0;
		int nIndex2 = 0;
		int nIndex3 = 0;
		int nIndex4 = 0;

		String strQry1 = "";
		String strQry2 = "";
		String strQry3 = "";
		String strQry4 = "";

		HisDAO hisdao = new HisDAO("billing.DAOClientChargeMst", "modifyData");

		try {

			String[] strT1 = chk.replace('$', '#').split("#");
			String[] strT2 = strT1[0].replace('@', '#').split("#");

			
			vo.setHospitalService(strT2[1]);
			vo.setStrTariffName(strT2[2]);
			vo.setStrWardType(strT2[4]);
			vo.setStrChargeSlNo(strT2[6].replace('|', '#').split("#")[0]);
			vo.setStrIsPackage(strT2[6].replace('|', '#').split("#")[1]);
			vo.setStrGroupId(strT2[6].replace('|', '#').split("#")[3]);
			vo.setStrOrg(strT2[5]);
			vo.setStrIsPackage("0");
			if (vo.getStrIsPackage().equals("0")) {
				strQry1 = billing.qryHandler_billing.getQuery(1,
						"update.clientchargeMst.0");
				for(int nTmpI=0; nTmpI<vo.getStrPKPatCatArr().length; nTmpI++){
					nIndex1 = hisdao.setQuery(strQry1);
	
					hisdao.setQryValue(nIndex1, 1, vo.getStrProductCostArr()[nTmpI]);
					hisdao.setQryValue(nIndex1, 2, vo.getStrTariffCostArr()[nTmpI]);
					String cost_difference= Integer.toString(Integer.parseInt(vo.getStrTariffCostArr()[nTmpI])-Integer.parseInt(vo.getStrProductCostArr()[nTmpI]));
					hisdao.setQryValue(nIndex1, 3, cost_difference);
					hisdao.setQryValue(nIndex1, 4, vo.getStrOrg());
					hisdao.setQryValue(nIndex1, 5, vo.getStrFromDateArr()[nTmpI]);
					/*if(vo.getStrUpdateMode().equals("1"))
						hisdao.setQryValue(nIndex1, 7, vo.getStrFromDateArr()[nTmpI]);
					else
						hisdao.setQryValue(nIndex1, 7, "");*/
					hisdao.setQryValue(nIndex1, 6, vo.getStrSeatId());
					hisdao.setQryValue(nIndex1, 7, vo.getStrRemarks());
					hisdao.setQryValue(nIndex1, 8, vo.getStrHospitalCode());
					hisdao.setQryValue(nIndex1, 9, vo.getStrPkChargeTypeArr()[nTmpI]);
					hisdao.setQryValue(nIndex1, 10, vo.getStrPKTariffArr()[nTmpI]);
					hisdao.setQryValue(nIndex1, 11, vo.getStrPKPatCatArr()[nTmpI]);
					hisdao.setQryValue(nIndex1, 12, vo.getStrPKIpdChargeTypeArr()[nTmpI]);
					hisdao.setQryValue(nIndex1, 13, vo.getStrPKChargeSlNoArr()[nTmpI]);
	
					hisdao.execute(nIndex1, 0);
				}
				for(int nTmpI=0; vo.getStrUpdateMode().equals("1") && nTmpI<vo.getStrPKPatCatArr().length; nTmpI++){
					strQry1 = billing.qryHandler_billing.getQuery(1,
							"insert.clientchargeMst.0");
					nIndex1 = hisdao.setQuery(strQry1);
					hisdao.setQryValue(nIndex1, 1, vo.getStrHospitalCode());
					hisdao.setQryValue(nIndex1, 2, vo.getStrPkChargeTypeArr()[nTmpI]);
					hisdao.setQryValue(nIndex1, 3, vo.getStrPKTariffArr()[nTmpI]);
					hisdao.setQryValue(nIndex1, 4, vo.getStrPKIpdChargeTypeArr()[nTmpI]);
					
					
					hisdao.setQryValue(nIndex1, 5, vo.getStrPkChargeTypeArr()[nTmpI]);
					hisdao.setQryValue(nIndex1, 6, vo.getStrPKTariffArr()[nTmpI]);
					hisdao.setQryValue(nIndex1, 7, vo.getStrPKPatCatArr()[nTmpI]);
					hisdao.setQryValue(nIndex1, 8, vo.getStrPKIpdChargeTypeArr()[nTmpI]);
					hisdao.setQryValue(nIndex1, 9, vo.getStrHospitalCode());
					
					
					hisdao.setQryValue(nIndex1, 10, vo.getStrGroupId());
					hisdao.setQryValue(nIndex1, 11, vo.getStrUnitIdArr()[nTmpI]);
					hisdao.setQryValue(nIndex1, 12, vo.getStrPKPatCatArr()[nTmpI]);
					hisdao.setQryValue(nIndex1, 13, vo.getStrProductCostArr()[nTmpI]);
					hisdao.setQryValue(nIndex1, 14, vo.getStrTariffCostArr()[nTmpI]);
					String cost_difference= Integer.toString(Integer.parseInt(vo.getStrTariffCostArr()[nTmpI])-Integer.parseInt(vo.getStrProductCostArr()[nTmpI]));
					hisdao.setQryValue(nIndex1, 15, cost_difference);
					hisdao.setQryValue(nIndex1, 16, vo.getStrFromDateArr()[nTmpI]);
					hisdao.setQryValue(nIndex1, 17, vo.getStrRemarks());
					hisdao.setQryValue(nIndex1, 18, vo.getStrSeatId());
					hisdao.setQryValue(nIndex1, 19, "1");
					hisdao.setQryValue(nIndex1, 20, vo.getStrOrg());
			
					hisdao.execute(nIndex1, 0);
				}
				
			} else {

				strQry2 = billing.qryHandler_billing.getQuery(1,
						"update.ClientChargeMst.1");

				nIndex2 = hisdao.setQuery(strQry2);

				

				hisdao.setQryValue(nIndex2, 1, vo.getStrPackProcdCost());
				hisdao.setQryValue(nIndex2, 2, vo.getStrPackTariffCost());
				hisdao.setQryValue(nIndex2, 3, vo.getStrPackTotalCost());
				hisdao.setQryValue(nIndex2, 4, vo.getStrPackIsRefundable());
				hisdao.setQryValue(nIndex2, 5, vo.getStrPackIsAdvance());
				hisdao.setQryValue(nIndex2, 6, vo.getStrEffectiveFrm());
				//hisdao.setQryValue(nIndex2, 7, vo.getStrEffectiveTo());
				hisdao.setQryValue(nIndex2, 7, vo.getStrRemarks());
				hisdao.setQryValue(nIndex2, 8, vo.getStrLastModiSeatId());
				hisdao.setQryValue(nIndex2, 9, vo.getStrHospitalCode());
				hisdao.setQryValue(nIndex2, 10, vo.getHospitalService());
				hisdao.setQryValue(nIndex2, 11, vo.getStrPatientCategoryMod());
				hisdao.setQryValue(nIndex2, 12, vo.getStrWardType());
				hisdao.setQryValue(nIndex2, 13, vo.getStrTariffName());
				hisdao.setQryValue(nIndex2, 14, vo.getStrChargeSlNo());

				hisdao.execute(nIndex2, 0);

				

				strQry4 = billing.qryHandler_billing.getQuery(1,
						"update.ClientChargeMst.11");

				nIndex4 = hisdao.setQuery(strQry4);

				
				hisdao.setQryValue(nIndex4, 1, vo.getStrLastModiSeatId());
				hisdao.setQryValue(nIndex4, 2, vo.getStrHospitalCode());
				hisdao.setQryValue(nIndex4, 3, vo.getHospitalService());
				hisdao.setQryValue(nIndex4, 4, vo.getStrPatientCategoryMod());
				hisdao.setQryValue(nIndex4, 5, vo.getStrWardType());
				hisdao.setQryValue(nIndex4, 6, vo.getStrTariffName());

				hisdao.execute(nIndex4, 0);

				strQry3 = billing.qryHandler_billing.getQuery(1,
						"insert.ClientChargeMst.3");
				nIndex3 = hisdao.setQuery(strQry3);

				

				for (int ni = 0; ni < vo.getStrPackTotal().length; ni++) {

					

					hisdao.setQryValue(nIndex3, 1, vo.getStrHospitalCode());
					hisdao.setQryValue(nIndex3, 2, vo.getHospitalService());
					hisdao.setQryValue(nIndex3, 3, vo
							.getStrPatientCategoryMod());
					hisdao.setQryValue(nIndex3, 4, vo.getStrWardType());
					hisdao.setQryValue(nIndex3, 5, vo.getStrTariffName());
					hisdao.setQryValue(nIndex3, 6, vo.getStrUnit());
					hisdao.setQryValue(nIndex3, 7, vo.getHospitalService());
					hisdao.setQryValue(nIndex3, 8, vo.getStrPatientCategoryMod());
					hisdao.setQryValue(nIndex3, 9, vo.getStrWardType());
					hisdao.setQryValue(nIndex3, 10, vo.getStrTariffName());
					hisdao.setQryValue(nIndex3, 11, vo.getStrPackTrfId()[ni]);
					hisdao.setQryValue(nIndex3, 12, vo.getStrHospitalCode());
					hisdao.setQryValue(nIndex3, 13, vo.getStrPackTrfId()[ni]);
					hisdao.setQryValue(nIndex3, 14, vo.getStrGroupName());
					hisdao
							.setQryValue(nIndex3, 15,
									vo.getStrPackProcCost()[ni]);
					hisdao.setQryValue(nIndex3, 16, vo.getStrPackTrfCost()[ni]);
					hisdao.setQryValue(nIndex3, 17, vo.getStrPackTotal()[ni]);
					hisdao.setQryValue(nIndex3, 18, vo.getStrRefundable()[ni]);
					hisdao.setQryValue(nIndex3, 19, vo.getStrEffectiveFrm());
					hisdao.setQryValue(nIndex3, 20, vo.getStrEffectiveTo());
					hisdao.setQryValue(nIndex3, 21, vo.getStrSeatId());
					hisdao.setQryValue(nIndex3, 22, "1");

					hisdao.execute(nIndex3, 0);

				}
			}

			synchronized (hisdao) {
				hisdao.fire();
				fVal = true;
			}

		} catch (Exception e) {
	 
			fVal = false;
	           vo.setStrMsgType("1");
				
				vo.setStrMsgString("DAOClientChargeMst.saveModifiedData() --> "
					+ e.getMessage());
			
		} finally {
			hisdao.free();

			hisdao = null;
		}
		return fVal;
	}
	
	/**
	 * validation no.1 to be checked before modifying the data.
	 * 
	 * @param vo
	 * @param chk
	 * @return
	 * @throws Exception
	 */
	public static boolean qryBeforeUpdate1(ClientChargeMstVO vo, String chk)
			throws Exception {
		boolean fVal = false;
		int nIndex = 0;
		int nCount = 0;
		String strQry = "";
		WebRowSet wb = null;
		HisDAO hisdao = new HisDAO("Billing", "DAOClientChargeMst");
		strQry = qryHandler_billing.getQuery(1, "select.chargeMst.8");

		String[] strT1 = chk.replace('$', '#').split("#");
		String[] strT2 = strT1[0].replace('@', '#').split("#");

		vo.setStrHospitalCode(strT2[0]);
		vo.setHospitalService(strT2[1]);
		vo.setStrTariffName(strT2[2]);
		vo.setStrPatientCategoryMod(strT2[3]);
		vo.setStrWardType(strT2[4]);
		vo.setStrIsPackage(strT2[5].replace('|', '#').split("#")[1]);

		try {

			nIndex = hisdao.setQuery(strQry);
			

			hisdao.setQryValue(nIndex, 1, vo.getStrHospitalCode());
			hisdao.setQryValue(nIndex, 2, vo.getHospitalService());
			hisdao.setQryValue(nIndex, 3, vo.getStrTariffName());
			hisdao.setQryValue(nIndex, 4, vo.getStrPatientCategoryMod());
			hisdao.setQryValue(nIndex, 5, vo.getStrWardType());
			hisdao.setQryValue(nIndex, 6, vo.getStrEffectiveFrm());
			hisdao.setQryValue(nIndex, 7, vo.getStrEffectiveFrm());
			hisdao.setQryValue(nIndex, 8, vo.getStrEffectiveFrm());

			wb = hisdao.executeQry(nIndex);

			if (wb.next()) {
				nCount = Integer.parseInt(wb.getString(1));

			}
			if (nCount == 0) {
				fVal = true;
			} else {
				fVal = false;
			}
		} catch (Exception e) {
	           vo.setStrMsgType("1");
				
				vo.setStrMsgString("DAOClientChargeMst.qryBeforeUpdate1() --> "
					+ e.getMessage());
 
			
		} finally {
			hisdao.free();
			hisdao = null;
		}
		return fVal;
	}

	/**
	 * validation no.2 to be checked before modifying the data.
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static boolean qryBeforeUpdate2(ClientChargeMstVO vo) throws Exception {
		boolean fVal = false;
		int nCount = 0;
		int nIndex = 0;
		String strQry = "";
		WebRowSet wb = null;
		HisDAO hisdao = new HisDAO("Billing", "DAOClientChargeMst");
		strQry = qryHandler_billing.getQuery(1, "select.chargeMst.9");
		try {
			nIndex = hisdao.setQuery(strQry);

			

			hisdao.setQryValue(nIndex, 1, vo.getStrHospitalCode());
			hisdao.setQryValue(nIndex, 2, vo.getHospitalService());
			hisdao.setQryValue(nIndex, 3, vo.getStrTariffName());
			hisdao.setQryValue(nIndex, 4, vo.getStrPatientCategoryMod());
			hisdao.setQryValue(nIndex, 5, vo.getStrWardType());
			hisdao.setQryValue(nIndex, 6, vo.getStrEffectiveFrm());
			hisdao.setQryValue(nIndex, 7, vo.getStrEffectiveTo());
			hisdao.setQryValue(nIndex, 8, vo.getStrEffectiveTo());

			wb = hisdao.executeQry(nIndex);

			if (wb.next()) {
				nCount = Integer.parseInt(wb.getString(1));
			}
			if (nCount == 0) {
				fVal = true;
			} else {
				fVal = false;
			}
		} catch (Exception e) {
	           vo.setStrMsgType("1");
				
				vo.setStrMsgString("DAOClientChargeMst.qryBeforeUpdate2() --> "
					+ e.getMessage());
			
		} finally {
			hisdao.free();
			hisdao = null;
		}
		return fVal;
	}

	/**
	 * batch update method. CASE 1- Apply New Change.
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static boolean batchUpdateQry(ClientChargeMstVO vo) throws Exception {

		boolean fVal = false;
		int nIndex1 = 0;
		int nIndex2 = 0;
		int nIndex3 = 0;
		int nIndex4 = 0;
		int nIndex5 = 0;
		int nIndex6 = 0;
		String strQry1 = "";
		String strQry2 = "";
		String strQry3 = "";
		String strQry4 = "";
		String strQry5 = "";
		String strQry6 = "";

		HisDAO hisdao = new HisDAO("Billing", "ClientChargeMstDAO");
		try {
			
			
			
			

			// update query 2.
			strQry3 = qryHandler_billing.getQuery(1, "update.ClientChargeMst.4");
			nIndex3 = hisdao.setQuery(strQry3);
			hisdao.setQryValue(nIndex3, 1, vo.getStrEffectiveFrm());
			hisdao.setQryValue(nIndex3, 2, vo.getStrSeatId());
			hisdao.setQryValue(nIndex3, 3, vo.getStrHospitalCode());
			hisdao.setQryValue(nIndex3, 4, vo.getHospitalService());
			hisdao.setQryValue(nIndex3, 5, vo.getStrTempPatientCategory());
			hisdao.setQryValue(nIndex3, 6, vo.getStrWardType());
			hisdao.setQryValue(nIndex3, 7, vo.getStrEffectiveFrm());
			hisdao.execute(nIndex3, 0);

			
			
			

			
			
			
			
			// insert query.
			strQry1 = qryHandler_billing.getQuery(1, "insert.ClientChargeMst.4");

			nIndex1 = hisdao.setQuery(strQry1);
			System.out.println("::::vo.getStrNewProcCost():::::::::="+vo.getStrNewProcCost());
			System.out.println("::::vo.getStrNewTrfCost()::::::::::="+vo.getStrNewTrfCost());
			System.out.println("::::vo.getStrEffectiveFrm()::::::::::="+vo.getStrEffectiveFrm());
			System.out.println("::::vo.getStrEffectiveTo()::::::::::="+vo.getStrEffectiveTo());
			System.out.println("::::vo.getStrTempPatientCategory():::::==="+vo.getStrTempPatientCategory());
			
			hisdao.setQryValue(nIndex1, 1, vo.getStrNewProcCost());
			hisdao.setQryValue(nIndex1, 2, vo.getStrNewTrfCost());
			hisdao.setQryValue(nIndex1, 3, vo.getStrNewProcCost());
			hisdao.setQryValue(nIndex1, 4, vo.getStrNewTrfCost());
			hisdao.setQryValue(nIndex1, 5, vo.getStrEffectiveFrm());
			hisdao.setQryValue(nIndex1, 6, vo.getStrEffectiveTo());
			hisdao.setQryValue(nIndex1, 7, vo.getStrSeatId());
			hisdao.setQryValue(nIndex1, 8, vo.getStrHospitalCode());
			hisdao.setQryValue(nIndex1, 9, vo.getHospitalService());
			hisdao.setQryValue(nIndex1, 10, vo.getStrTempPatientCategory());
			hisdao.setQryValue(nIndex1, 11, vo.getStrWardType());
			hisdao.setQryValue(nIndex1, 12, vo.getStrGroupName());
			hisdao.execute(nIndex1, 0);

			
			// update query 1.
			strQry2 = qryHandler_billing.getQuery(1, "update.ClientChargeMst.3");
			nIndex2 = hisdao.setQuery(strQry2);
			hisdao.setQryValue(nIndex2, 1, vo.getStrSeatId());
			hisdao.setQryValue(nIndex2, 2, vo.getStrHospitalCode());
			hisdao.setQryValue(nIndex2, 3, vo.getHospitalService());
			hisdao.setQryValue(nIndex2, 4, vo.getStrTempPatientCategory());
			hisdao.setQryValue(nIndex2, 5, vo.getStrWardType());
			hisdao.setQryValue(nIndex2, 6, vo.getStrEffectiveFrm());
			hisdao.setQryValue(nIndex2, 7, vo.getStrEffectiveTo());
			hisdao.setQryValue(nIndex2, 8, vo.getStrEffectiveTo());
			hisdao.execute(nIndex2, 0);
			

			// checks condition whether package exists or not.
			if (vo.getStrIsPackage().equals("1")) {
				// insert query for package_charge_mst table.
				strQry4 = qryHandler_billing.getQuery(1, "insert.ClientChargeMst.5");
				nIndex4 = hisdao.setQuery(strQry4);
				hisdao.setQryValue(nIndex4, 1, vo.getStrNewProcCost());
				hisdao.setQryValue(nIndex4, 2, vo.getStrNewTrfCost());
				hisdao.setQryValue(nIndex4, 3, vo.getStrNewProcCost());
				hisdao.setQryValue(nIndex4, 4, vo.getStrNewTrfCost());
				hisdao.setQryValue(nIndex4, 5, vo.getStrEffectiveFrm());
				hisdao.setQryValue(nIndex4, 6, vo.getStrEffectiveTo());
				hisdao.setQryValue(nIndex4, 7, vo.getStrSeatId());
				hisdao.setQryValue(nIndex4, 8, vo.getStrHospitalCode());
				hisdao.setQryValue(nIndex4, 9, vo.getHospitalService());
				hisdao.setQryValue(nIndex4, 10, vo.getStrPatientCategoryMod());
				hisdao.setQryValue(nIndex4, 11, vo.getStrWardType());
				hisdao.setQryValue(nIndex4, 12, vo.getStrGroupName());
				hisdao.execute(nIndex4, 0);

				

				// update query 1.
				strQry5 = qryHandler_billing.getQuery(1, "update.ClientChargeMst.5");
				nIndex5 = hisdao.setQuery(strQry5);
				hisdao.setQryValue(nIndex5, 1, vo.getStrSeatId());
				hisdao.setQryValue(nIndex5, 2, vo.getStrHospitalCode());
				hisdao.setQryValue(nIndex5, 3, vo.getHospitalService());
				hisdao.setQryValue(nIndex5, 4, vo.getStrPatientCategoryMod());
				hisdao.setQryValue(nIndex5, 5, vo.getStrWardType());
				hisdao.setQryValue(nIndex5, 6, vo.getStrEffectiveFrm());
				hisdao.setQryValue(nIndex5, 7, vo.getStrEffectiveTo());
				hisdao.setQryValue(nIndex5, 8, vo.getStrEffectiveTo());
				hisdao.execute(nIndex5, 0);

				
				// update query 2.
				strQry6 = qryHandler_billing.getQuery(1, "update.ClientChargeMst.6");
				nIndex6 = hisdao.setQuery(strQry6);
				hisdao.setQryValue(nIndex6, 1, vo.getStrEffectiveFrm());
				hisdao.setQryValue(nIndex6, 2, vo.getStrSeatId());
				hisdao.setQryValue(nIndex6, 3, vo.getStrHospitalCode());
				hisdao.setQryValue(nIndex6, 4, vo.getHospitalService());
				hisdao.setQryValue(nIndex6, 5, vo.getStrPatientCategoryMod());
				hisdao.setQryValue(nIndex6, 6, vo.getStrWardType());
				hisdao.setQryValue(nIndex6, 7, vo.getStrEffectiveFrm());
				hisdao.execute(nIndex6, 0);

				

			}

			synchronized (hisdao) {

				hisdao.fire();
				fVal = true;
			}
		} catch (Exception e) {
			fVal = false;
			
	           vo.setStrMsgType("1");
				
				vo.setStrMsgString("DAOClientChargeMst.batchUpdateQry() --> "
					+ e.getMessage());
			
		} finally {
			hisdao.free();
			hisdao = null;
		}

		return fVal;
	}

	/**
	 * batch update method. CASE 2- Copy To Change.
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static boolean batchCopyToQry(ClientChargeMstVO vo) throws Exception {

		boolean fVal = false;
		int nIndex1 = 0;
		int nIndex2 = 0;
		String strQry1 = "";
		String strQry2 = "";

		HisDAO hisdao = new HisDAO("Billing", "DAOClientChargeMst");
		try {
			strQry1 = qryHandler_billing.getQuery(1, "insert.ClientChargeMst.6");
			nIndex1 = hisdao.setQuery(strQry1);
			hisdao.setQryValue(nIndex1, 1, vo.getStrCopyPatCat());
			hisdao.setQryValue(nIndex1, 2, vo.getStrCopyWardType());
			hisdao.setQryValue(nIndex1, 3, vo.getStrCopyPatCat());
			hisdao.setQryValue(nIndex1, 4, vo.getStrCopyWardType());
			hisdao.setQryValue(nIndex1, 5, vo.getStrCopyProcCost());
			hisdao.setQryValue(nIndex1, 6, vo.getStrCopyTrfCost());
			hisdao.setQryValue(nIndex1, 7, vo.getStrCopyProcCost());
			hisdao.setQryValue(nIndex1, 8, vo.getStrCopyTrfCost());
			hisdao.setQryValue(nIndex1, 9, vo.getStrEffectiveFrm());
			hisdao.setQryValue(nIndex1, 10, vo.getStrEffectiveTo());
			hisdao.setQryValue(nIndex1, 11, vo.getStrSeatId());
			hisdao.setQryValue(nIndex1, 12, vo.getStrHospitalCode());
			hisdao.setQryValue(nIndex1, 13, vo.getHospitalService());
			hisdao.setQryValue(nIndex1, 14, vo.getStrTempPatientCategory());
			hisdao.setQryValue(nIndex1, 15, vo.getStrWardType());
			hisdao.setQryValue(nIndex1, 16, vo.getStrGroupName());
			hisdao.setQryValue(nIndex1, 17, vo.getStrHospitalCode());
			hisdao.setQryValue(nIndex1, 18, vo.getHospitalService());
			hisdao.setQryValue(nIndex1, 19, vo.getStrCopyPatCat());
			hisdao.setQryValue(nIndex1, 20, vo.getStrCopyWardType());
			hisdao.setQryValue(nIndex1, 21, vo.getStrGroupName());
			hisdao.setQryValue(nIndex1, 22, vo.getStrEffectiveFrm());
			hisdao.setQryValue(nIndex1, 23, vo.getStrEffectiveFrm());
			hisdao.setQryValue(nIndex1, 24, vo.getStrEffectiveFrm());
			hisdao.setQryValue(nIndex1, 25, vo.getStrEffectiveTo());
			hisdao.setQryValue(nIndex1, 26, vo.getStrEffectiveFrm());
			hisdao.setQryValue(nIndex1, 27, vo.getStrEffectiveTo());
			hisdao.setQryValue(nIndex1, 28, vo.getStrEffectiveTo());
			hisdao.setQryValue(nIndex1, 29, vo.getStrEffectiveTo());
			hisdao.setQryValue(nIndex1, 30, vo.getStrEffectiveTo());
			hisdao.execute(nIndex1, 0);


			// checks condition whether package exists or not.
			if (vo.getStrIsPackage().equals("1")) {
				// insert query for package_charge_mst table.
				strQry2 = qryHandler_billing.getQuery(1, "insert.ClientChargeMst.7");
				nIndex2 = hisdao.setQuery(strQry2);
				hisdao.setQryValue(nIndex2, 1, vo.getStrCopyPatCat());
				hisdao.setQryValue(nIndex2, 2, vo.getStrCopyWardType());
				hisdao.setQryValue(nIndex2, 3, vo.getStrCopyPatCat());
				hisdao.setQryValue(nIndex2, 4, vo.getStrCopyWardType());
				hisdao.setQryValue(nIndex2, 5, vo.getStrCopyProcCost());
				hisdao.setQryValue(nIndex2, 6, vo.getStrCopyTrfCost());
				hisdao.setQryValue(nIndex2, 7, vo.getStrCopyProcCost());
				hisdao.setQryValue(nIndex2, 8, vo.getStrCopyTrfCost());
				hisdao.setQryValue(nIndex2, 9, vo.getStrEffectiveFrm());
				hisdao.setQryValue(nIndex2, 10, vo.getStrEffectiveTo());
				hisdao.setQryValue(nIndex2, 11, vo.getStrSeatId());
				hisdao.setQryValue(nIndex2, 12, vo.getStrHospitalCode());
				hisdao.setQryValue(nIndex2, 13, vo.getHospitalService());
				hisdao.setQryValue(nIndex2, 14, vo.getStrTempPatientCategory());
				hisdao.setQryValue(nIndex2, 15, vo.getStrCopyWardType());
				hisdao.setQryValue(nIndex2, 16, vo.getStrGroupName());
				hisdao.setQryValue(nIndex2, 17, vo.getStrHospitalCode());
				hisdao.setQryValue(nIndex2, 18, vo.getHospitalService());
				hisdao.setQryValue(nIndex2, 19, vo.getStrCopyPatCat());
				hisdao.setQryValue(nIndex2, 20, vo.getStrCopyWardType());
				hisdao.setQryValue(nIndex2, 21, vo.getStrGroupName());
				hisdao.setQryValue(nIndex2, 22, vo.getStrEffectiveFrm());
				hisdao.setQryValue(nIndex2, 23, vo.getStrEffectiveFrm());
				hisdao.setQryValue(nIndex2, 24, vo.getStrEffectiveFrm());
				hisdao.setQryValue(nIndex2, 25, vo.getStrEffectiveTo());
				hisdao.setQryValue(nIndex2, 26, vo.getStrEffectiveFrm());
				hisdao.setQryValue(nIndex2, 27, vo.getStrEffectiveTo());
				hisdao.setQryValue(nIndex2, 28, vo.getStrEffectiveTo());
				hisdao.setQryValue(nIndex2, 29, vo.getStrEffectiveTo());
				hisdao.setQryValue(nIndex2, 30, vo.getStrEffectiveTo());
				hisdao.execute(nIndex2, 0);
			}
			
			
			synchronized (hisdao) {
				hisdao.fire();
			}
			
			
			
			
			fVal = true;
		} catch (Exception e) {
			fVal = false;
	           vo.setStrMsgType("1");
				
				vo.setStrMsgString("DAOClientChargeMst.batchCopyToQry() --> "
					+ e.getMessage());
			
			
		} finally {
			hisdao.free();
			hisdao = null;
		}
		return fVal;
	}

	// //////////////////////methods by kapil and anshul/////////////////////
	/**
	 * Group combo
	 * 
	 * @param vo
	 * @param isPackage
	 * @return
	 * @throws Exception
	 */
	public static WebRowSet groupCmb(ClientChargeMstVO vo, String isPackage)
			throws Exception {
		int nIndex = 0;
		String strQry = "";
		
		WebRowSet wb = null;
		HisDAO hisdao = new HisDAO("Billing", "DAOClientChargeMst");

		try {

			strQry = qryHandler_billing.getQuery(1, "select.chargegroupcmb.0");
			nIndex = hisdao.setQuery(strQry);

			
			hisdao.setQryValue(nIndex, 1, vo.getStrHospitalCode());
			hisdao.setQryValue(nIndex, 2, vo.getHospitalService());

			wb = hisdao.executeQry(nIndex);

		} catch (Exception e) {
			
	           vo.setStrMsgType("1");
				
				vo.setStrMsgString("DAOClientChargeMst.groupCmb() --> "+ e.getMessage());
			
		} finally {
			hisdao.free();
			hisdao = null;
		}
		return wb;

	}
	/**
	 * Group combo1
	 * 
	 * @param vo
	 * @param isPackage
	 * @return
	 * @throws Exception
	 */
	public static WebRowSet groupCmb1(ClientChargeMstVO vo, String isPackage)
			throws Exception {
		int nIndex = 0;
		String strQry = "";
		
		WebRowSet wb = null;
		HisDAO hisdao = new HisDAO("Billing", "DAOClientChargeMst");

		try {

			strQry = qryHandler_billing.getQuery(1, "select.chargegroupcmb1.0");
			nIndex = hisdao.setQuery(strQry);

			hisdao.setQryValue(nIndex, 1, vo.getStrHospitalCode());
			hisdao.setQryValue(nIndex, 2, vo.getStrPackHospService());
			
			wb = hisdao.executeQry(nIndex);

		} catch (Exception e) {
			
	           vo.setStrMsgType("1");
				
				vo.setStrMsgString("DAOClientChargeMst.groupCmb1() --> "
					+ e.getMessage());
			
		} finally {
			hisdao.free();
			hisdao = null;
		}
		return wb;

	}
	/**
	 * tariff combo
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static WebRowSet tariffCmb(ClientChargeMstVO vo) throws Exception 
	{
		int nIndex = 0;
		String strQry = "";
		
		WebRowSet wb = null;
		HisDAO hisdao = new HisDAO("Billing", "DAOClientChargeMst");

		try 
		{
			strQry = qryHandler_billing.getQuery(1, "gbl.tariff.0");
			strQry =  strQry + qryHandler_billing.getQuery(1, "gbl.tariff.cond.0");
			
			nIndex = hisdao.setQuery(strQry);

			if(vo.getStrGroupName().equals(BillConfigUtil.PHARMACY_GROUP_ID))//107-THEN FETCH DATA AT HOSPCODE=100
				hisdao.setQryValue(nIndex, 1,BillConfigUtil.SUPER_HOSPITAL_CODE);
			else
				hisdao.setQryValue(nIndex, 1, vo.getStrHospitalCode());						
			hisdao.setQryValue(nIndex, 2, vo.getStrGroupName());		

			wb = hisdao.executeQry(nIndex);
		} 
		catch (Exception e) 
		{			
	           vo.setStrMsgType("1");				
	           vo.setStrMsgString("DAOClientChargeMst.tariffCmb() --> "+ e.getMessage());			
		} 
		finally 
		{
			hisdao.free();
			hisdao = null;
		}
		return wb;

	}
	/**
	 * tariffCmb1
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static WebRowSet tariffCmb1(ClientChargeMstVO vo) throws Exception 
	{
		int nIndex = 0;
		String strQry = "";
		WebRowSet wb = null;
		HisDAO hisdao = new HisDAO("Billing", "DAOClientChargeMst");

		try 
		{
			strQry = qryHandler_billing.getQuery(1, "gbl.tariff1.0");
			nIndex = hisdao.setQuery(strQry);
			if(vo.getStrPackGroupName().equals(BillConfigUtil.PHARMACY_GROUP_ID))//107-THEN FETCH DATA AT HOSPCODE=100
				hisdao.setQryValue(nIndex, 1,BillConfigUtil.SUPER_HOSPITAL_CODE);
			else
				hisdao.setQryValue(nIndex, 1, vo.getStrHospitalCode());
			hisdao.setQryValue(nIndex, 2, vo.getStrPackGroupName());
			System.out.println("strQry"+strQry);
			wb = hisdao.executeQry(nIndex);
		} 
		catch (Exception e) 
		{			
	           vo.setStrMsgType("1");
	           vo.setStrMsgString("DAOClientChargeMst.tariffCmb1() --> "+ e.getMessage());			
		} 
		finally 
		{
			hisdao.free();
			hisdao = null;
		}
		return wb;

	}
	/**
	 * unitCmb
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */

	public static WebRowSet unitCmb(ClientChargeMstVO vo) throws Exception 
	{
		int nIndex = 0;
		String strQry = "";		
		WebRowSet wb = null;
		HisDAO hisdao = new HisDAO("Billing", "DAOClientChargeMst");

		try 
		{			
			strQry = qryHandler_billing.getQuery(1, "gbl.unit.0");
			nIndex = hisdao.setQuery(strQry);
			hisdao.setQryValue(nIndex, 1, BillConfigUtil.SUPER_HOSPITAL_CODE);
			//hisdao.setQryValue(nIndex, 2, vo.getStrUnit());
		//	hisdao.setQryValue(nIndex, 3, vo.getStrUnit());	
			hisdao.setQryValue(nIndex, 2, "1701");
			hisdao.setQryValue(nIndex, 3, "1701");			
			wb = hisdao.executeQry(nIndex);
			
			
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
			vo.setStrMsgType("1");			
			vo.setStrMsgString("DAOClientChargeMst.unitCmb() --> "+ e.getMessage());			
		} 
		finally 
		{
			hisdao.free();
			hisdao = null;
		}
		return wb;

	}
	/**
	 * ward combo
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static WebRowSet wardCmb(ClientChargeMstVO vo) throws Exception {
		int nIndex = 0;
		String strQry = "";
		
		WebRowSet wb = null;
		HisDAO hisdao = new HisDAO("Billing", "DAOClientChargeMst");

		try {

			strQry = qryHandler_billing.getQuery(1, "gbl.ipdchargetype.0");
			nIndex = hisdao.setQuery(strQry);

			hisdao.setQryValue(nIndex, 1, vo.getHospitalService());
			hisdao.setQryValue(nIndex, 2, vo.getStrHospitalCode());

			wb = hisdao.executeQry(nIndex);

		} catch (Exception e) {
			
            vo.setStrMsgType("1");
			
			vo.setStrMsgString("DAOClientChargeMst.wardCmb() --> "
				+ e.getMessage());
			
		} finally {
			hisdao.free();
			hisdao = null;
		}
		return wb;

	}
	/**
	 * to get PreviousData
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static WebRowSet getPreviousData(ClientChargeMstVO vo) throws Exception 
	{
		String strQuery = billing.qryHandler_billing.getQuery(1,"select.clientchargeMst.12");
		HisDAO hisdao = new HisDAO("billing.DAOClientChargeMst", "getPreviousData()");
		WebRowSet wb = null;
		int nIndex = 0;
		
		try 
		{
			nIndex = hisdao.setQuery(strQuery);
			hisdao.setQryValue(nIndex, 1, vo.getStrHospitalCode());
			hisdao.setQryValue(nIndex, 2, vo.getStrWardType());
			hisdao.setQryValue(nIndex, 3, vo.getHospitalService());
			hisdao.setQryValue(nIndex, 4, vo.getStrTariffName());
			wb = hisdao.executeQry(nIndex);
		} 
		catch (Exception e) 
		{
            vo.setStrMsgType("1");
			vo.setStrMsgString("DAOClientChargeMst.getPreviousData() --> "+ e.getMessage());			
		} 
		finally 
		{
			hisdao.free();
			hisdao = null;
		}
		return wb;
	}
	/**
	 * query before modify
	 * 
	 * @param vo
	 * @param chk
	 * @return
	 * @throws Exception
	 */
	public static boolean qryBeforeModify(ClientChargeMstVO vo, String chk)
			throws Exception {
		boolean fVal = false;
		int nIndex = 0;
		int nCount = 0;
		String strQry = "";
		WebRowSet wb = null;
		HisDAO hisdao = new HisDAO("Billing", "DAOClientChargeMst");
		strQry = qryHandler_billing.getQuery(1, "select.clientchargeMst.7");
        System.out.println("chk:::"+chk);
		String[] strT1 = chk.replace('$', '#').split("#");
		String[] strT2 = strT1[0].replace('@', '#').split("#");

		vo.setStrHospitalCode(strT2[0]);
		vo.setHospitalService(strT2[1]);
		vo.setStrTariffName(strT2[2]);
		vo.setStrTempCategory(strT2[3]);
		vo.setStrWardType(strT2[4]);
		vo.setStrIsPackage(strT2[6].replace('|', '#').split("#")[1]);
		vo.setStrOrg(strT2[5]);

		try {

			nIndex = hisdao.setQuery(strQry);
			

			hisdao.setQryValue(nIndex, 1, vo.getStrHospitalCode());
			hisdao.setQryValue(nIndex, 2, vo.getHospitalService());
			hisdao.setQryValue(nIndex, 3, vo.getStrPatientCategoryMod());
			hisdao.setQryValue(nIndex, 4, vo.getStrWardType());
			hisdao.setQryValue(nIndex, 5, vo.getStrTariffName());
			hisdao.setQryValue(nIndex, 6, vo.getStrOrg());
			hisdao.setQryValue(nIndex, 7, vo.getStrEffectiveFrm());
			hisdao.setQryValue(nIndex, 8, vo.getStrEffectiveTo());
			hisdao.setQryValue(nIndex, 9, vo.getStrEffectiveTo());
			wb = hisdao.executeQry(nIndex);

			if (wb.next()) {
				nCount = Integer.parseInt(wb.getString(1));
				
			}
			if (nCount == 0) {
				fVal = true;
			} else {
				fVal = false;
			}
		} catch (Exception e) {
			
			 vo.setStrMsgType("1");
				
				vo.setStrMsgString("DAOClientChargeMst.qryBeforeUpdate1() --> "
					+ e.getMessage());
			
		} finally {
			hisdao.free();
			hisdao = null;
		}
		return fVal;
	}
	/**
	 * delete Data.
	 * @param vo
	 * @param str
	 */
	public static void deleteData(ClientChargeMstVO vo, String[] chk){
		int nIndex1 = 0;
		int nIndex2 = 0;
		String strQry1 = "";
		String strQry2 = "";

		HisDAO hisdao = new HisDAO("billing.DAOClientChargeMst", "deleteData");

		try {
			strQry1 = billing.qryHandler_billing.getQuery(1,"delete.ClientChargeMst.0");

			for(int nTmpI = 0; nTmpI < chk.length; nTmpI++){
								
				String[] strTemp =  chk[nTmpI].replace("@", "#").replace("|", "#").split("#");
								
				nIndex1 = hisdao.setQuery(strQry1);
				hisdao.setQryValue(nIndex1, 1, strTemp[0]);
				hisdao.setQryValue(nIndex1, 2, strTemp[1]);
				hisdao.setQryValue(nIndex1, 3, strTemp[2]);
				hisdao.setQryValue(nIndex1, 4, strTemp[3]);
				hisdao.setQryValue(nIndex1, 5, strTemp[4]);
				hisdao.setQryValue(nIndex1, 6, strTemp[8]);
	
				hisdao.execute(nIndex1, 0);
			}

			strQry2 = billing.qryHandler_billing.getQuery(1,"delete.ClientChargeMst.1");

			for(int nTmpI = 0; nTmpI < chk.length; nTmpI++){
				
				String[] strTemp =  chk[nTmpI].replace("@", "#").replace("|", "#").split("#");
				
				nIndex2 = hisdao.setQuery(strQry2);
				hisdao.setQryValue(nIndex2, 1, strTemp[0]);
				hisdao.setQryValue(nIndex2, 2, strTemp[1]);
				hisdao.setQryValue(nIndex2, 3, strTemp[2]);
				hisdao.setQryValue(nIndex2, 4, strTemp[3]);
				hisdao.setQryValue(nIndex2, 5, strTemp[4]);
				hisdao.setQryValue(nIndex2, 6, strTemp[5]);
	
				hisdao.execute(nIndex2, 0);
			}	
			
			synchronized (hisdao) {
				hisdao.fire();
			}

		} catch (Exception e) {
			 
			vo.setStrErrMsg("billing.DAOClientChargeMst.deleteData -->" + e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			hisdao.free();
			hisdao = null;
		}
	}
}
