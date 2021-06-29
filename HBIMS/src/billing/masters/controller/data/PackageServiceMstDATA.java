package billing.masters.controller.data;
/* Package Service Master DATA
 * author: Debashis Sardar
 * Created on : 01-Sep-2011
 */
import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.masters.controller.hlp.PackageServiceMstHLP;
import billing.masters.bo.PackageServiceMstBO;
import billing.masters.vo.PackageServiceMstVO;
import billing.masters.controller.fb.PackageServiceMstFB;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;

public class PackageServiceMstDATA {
	
	/**
	 * Initializes the BO and VO and transfers control to BO for inserting data
	 * @param fb
	 * @return int 
	 */
		
		public int insertData(PackageServiceMstFB bean) {
			int count=-1;
			String strmsgText = null;
			


			boolean retvalue = false;

			PackageServiceMstVO voObj = null;
			try {
			
				voObj = new PackageServiceMstVO();
				voObj = (PackageServiceMstVO) TransferObjectFactory.populateData(
						"billing.masters.vo.PackageServiceMstVO", bean);
				
				retvalue = this.chkAddDuplicate(voObj);

				if (retvalue) {
					retvalue = PackageServiceMstBO.addData(voObj);
					
					if (retvalue) {
						count=0;
						
					}
				} else {
					count=1;
					
				}
				

			} catch (Exception e) {
				strmsgText = "billing.masters.controller.data.PackageServiceMstDATA.insertData(chk1,bean) --> "
						+ e.getMessage();
				HisException eObj = new HisException("Billing",
						"PackageServiceMst->insertData()", strmsgText);
				bean.setStrMsgType("1");
				bean.setErrmsg("Application Error [ERROR ID : "
						+ eObj.getErrorID() + "],Contact System Administrator! ");

				eObj = null;

			}
		
			return count;

		}

		/**
		 * to get group combo on add page
		 * @param fb
		 * @return string
		 */
		
		
		public String getpackgrpModuleCombo(PackageServiceMstFB bean) {
			String tempStr = "";
			HisUtil hisutil = new HisUtil("Billing", "VOpackservMst");
			HisDAO dao = new HisDAO("billing", "VOpackservMst");
			WebRowSet wb = null;
			
			int index = 0;
			try {
				String hosCode = bean.getStrHospitalCode();
				String Query = billing.qryHandler_billing.getQuery(1,
						"select.packservMst.15");

				index = dao.setQuery(Query);
				dao.setQryValue(index, 1, hosCode);
				wb = dao.executeQry(index);
				
				
				tempStr = hisutil.getOptionValue(wb, "0", "0^Select Value", false);
				
			} catch (Exception e) {
				new HisException("Billing",
						"VOpackservMst.getpackgrpModuleCombo()", e.getMessage());
			}
			
			return tempStr;
		}
		
		
		/**
		 * to Check for Existing Duplicate Record while adding
		 * @param vo
		 * @return boolean
		 */
		
		public boolean chkAddDuplicate(PackageServiceMstVO voObj) throws Exception 
		{
			boolean fretValue = false;
			try {
				fretValue = PackageServiceMstBO.chkAddDuplicateQuery(voObj);

			} catch (Exception e) {
				
				throw new Exception("PackageServiceMstDATA.chkAddDuplicate() -->"
						+ e.getMessage());
			}
			return fretValue;
		}

		

		/**
		 * Used to Edit Data
		 * @param fb
		 * @return boolean
		 */
		public boolean updateRecord(String chk1, PackageServiceMstFB bean) {
			boolean retvalue = false;
			String strmsgText = null;
		
			PackageServiceMstVO voObj = null;
			try {
				
				voObj = new PackageServiceMstVO();
				voObj = (PackageServiceMstVO) TransferObjectFactory.populateData(
						"billing.masters.vo.PackageServiceMstVO", bean);
				System.out.println("chk value..."+chk1);
				retvalue = PackageServiceMstBO.update(chk1,voObj);
				return retvalue;
			} catch (Exception e) {
				strmsgText = "billing.masters.controller.data.PackageServiceMstDATA.insertData(chk1,bean) --> "
						+ e.getMessage();
				HisException eObj = new HisException("Billing",
						"PacketServiceMaster->updateRecord()", strmsgText);
				bean.setErrmsg("Application Error [ERROR ID : "
						+ eObj.getErrorID() + "],Contact System Administrator! ");

				eObj = null;
				return retvalue;
			}
		}

		/**
		 * for getting option value of combo on add page
		 * @param fb
		 * @return 
		 */
		
		public void initAdd(PackageServiceMstFB bean) throws Exception		
		{
			WebRowSet wb = null;
			String pkgname = "";
			
			PackageServiceMstVO voObj = null;
			String strDefaultUnit="";
			String strtariffType="";
		
			try 
			{
				
				voObj = new PackageServiceMstVO();
				voObj = (PackageServiceMstVO) TransferObjectFactory.populateData("billing.masters.vo.PackageServiceMstVO", bean);
				wb = PackageServiceMstBO.initAddQuery(voObj);
				while (wb.next()) 
				{
					pkgname = wb.getString(1);
					strDefaultUnit=wb.getString(2);
					strtariffType=wb.getString(3);
				}
				
				bean.setStrtrfDefUnit(strDefaultUnit);
				bean.setStrpackageName(pkgname);
				bean.setStrtariffType(strtariffType);
				bean.setStrpackgrpModuleCombo(getpackgrpModuleCombo(bean));
				

			} 
			catch (Exception e) 
			{			
				throw new Exception("PackageServiceMstDATA.initAdd() -->" + e.getMessage());
			}

		}
		
		/**
		 * for getting option value of tariffName combo on add page
		 * @param fb
		 * @return 
		 */
		public void tariffNameCmb(PackageServiceMstFB bean) throws Exception
		
		{
			WebRowSet wb = null;
			
			PackageServiceMstVO voObj = null;
			

			try {
				
				voObj = new PackageServiceMstVO();
				voObj = (PackageServiceMstVO) TransferObjectFactory.populateData(
						"billing.masters.vo.PackageServiceMstVO", bean);
				wb = PackageServiceMstBO.tariffNameCmbQuery(voObj);

				HisUtil hisutil = new HisUtil("billing", "PackageServiceMstDATA");
				String cmb = hisutil.getOptionValue(wb, "0^Select Value",
						"Select Value", true);
				
				bean.setStrpacktariffModuleCombo(cmb);
				

			} catch (Exception e) {
				
				throw new Exception("PackageServiceMstDATA.tariffNameCmb() -->"
						+ e.getMessage());
			}

		}
		/**
		 * for getting option value of unit combo on add page
		 * @param fb
		 * @return 
		 */
		public void unitName(PackageServiceMstFB bean) throws Exception		
		{
			WebRowSet wb = null;
			String cmb = "";			
			PackageServiceMstVO voObj = null;			
			HisUtil hisutil = new HisUtil("billing", "PackageServiceMstDATA");			
			String strUnitId = bean.getStrunitId();

			
			try 
			{
				voObj = new PackageServiceMstVO();
				voObj = (PackageServiceMstVO) TransferObjectFactory.populateData("billing.masters.vo.PackageServiceMstVO", bean);
				//wb = PackageServiceMstBO.unitNameQuery(voObj);
				
				cmb=BillConfigUtil.getDefaultUnitCombo(strUnitId);
				/*if (strUnitId.equals("")) 
				{
					cmb = hisutil.getOptionValue(wb, "0^Select Value","Select Value", true);
				} 
				else 
				{
					cmb = hisutil.getOptionValue(wb, strUnitId, "Select Value",true);
				}		*/		
				bean.setStrpackunitModuleCombo(cmb);
			} 
			catch (Exception e) 
			{
				throw new Exception("PackageServiceMstDATA.unitName() -->" + e.getMessage());
			}
		}
		/**
		 * for getting option value of combo on add page
		 * @param fb
		 * @return string
		 */
		public String addedTariffDetails(PackageServiceMstFB bean) throws Exception
		
		{
			WebRowSet wb = null;
			String tariffDetail = "";
			
			PackageServiceMstVO voObj = null;
			try {
				
				voObj = new PackageServiceMstVO();
				voObj = (PackageServiceMstVO) TransferObjectFactory.populateData(
						"billing.masters.vo.PackageServiceMstVO", bean);
				wb = PackageServiceMstBO.addedTariffDetailsQuery(voObj);
				PackageServiceMstHLP hlp = new PackageServiceMstHLP();
				tariffDetail = hlp.getTariffDetails(wb);
				
			} catch (Exception e) {
			
				throw new Exception("PackageServiceMstDATA.addedTariffDetails() -->"
						+ e.getMessage());
			}
			return tariffDetail;

		}
		/**
		 * for getting option value of combo on add page
		 * @param fb
		 * @return string
		 */
		public String getTariffCombo(PackageServiceMstFB bean) throws Exception
		
		{
			WebRowSet wb = null;
			String tariffCmb = "";
			PackageServiceMstVO voObj = null;
			try {
				
				voObj = new PackageServiceMstVO();
				voObj = (PackageServiceMstVO) TransferObjectFactory.populateData(
						"billing.masters.vo.PackageServiceMstVO", bean);
				HisUtil hisutil = new HisUtil("billing", "PackageServiceMstDATA");
				wb = PackageServiceMstBO.getTariffComboQuery(voObj);
				System.out.println("Add here");
				tariffCmb = hisutil.getOptionValue(wb, "0^Select Value",
						"Select Value", true);
				System.out.println("!!!!!!Values of Combo Are "+tariffCmb);

			} catch (Exception e) {
			
				throw new Exception("PackageServiceMstDATA.addedTariffDetails() -->"
						+ e.getMessage());
			}
			
			return tariffCmb;

		}
		/**
		 * for getting option value of combo on add page
		 * @param fb
		 * @return 
		 */
		public void tariffName(PackageServiceMstFB bean) throws Exception
		
		{
			WebRowSet wb = null;
			String trfname = "";
			String defaultUnit = "";
			
			PackageServiceMstVO voObj = null;

			

			try {
				
				voObj = new PackageServiceMstVO();
				voObj = (PackageServiceMstVO) TransferObjectFactory.populateData(
						"billing.masters.vo.PackageServiceMstVO", bean);
				wb = PackageServiceMstBO.tariffNameQuery(voObj);
				while (wb.next()) {
					trfname = wb.getString(1);
					defaultUnit  = wb.getString(2);
				}
				
				bean.setStrtariffName(trfname);
				bean.setStrtrfDefUnit(defaultUnit);
				
				this.unitName(bean);

			} catch (Exception e) {
			
				throw new Exception("PackageServiceMstDATA.tariffName() -->"
						+ e.getMessage());
			}

		}
		/**
		 * To retrieve data from the database for the modify page.
		 * @param fb
		 * @param chk
		 * @return boolean
		 */
		public boolean getData(PackageServiceMstFB bean, String chk) {
			boolean fVal = false;
			String strmsgText = null;
			
			PackageServiceMstVO voObj = null;
			try {
				
				voObj = new PackageServiceMstVO();
				voObj = (PackageServiceMstVO) TransferObjectFactory.populateData(
						"billing.masters.vo.PackageServiceMstVO", bean);
				
				fVal = PackageServiceMstBO.getDataQuery(voObj, chk);
				HelperMethods.populate(bean,voObj);
				
			} catch (Exception e) {

				strmsgText = "billing.masters.PackageServiceMstDATA.getData(chk1,bean) --> "
						+ e.getMessage();
				HisException eObj = new HisException("Billing",
						"PacServiceMaster->getData()", strmsgText);
				bean.setStrMsgType("1");
				bean.setErrmsg("Application Error [ERROR ID : "
						+ eObj.getErrorID() + "],Contact System Administrator!");

				eObj = null;

			}
			return fVal;
		}
		/**
		 * To get unit combo
		 * @param fb
		 * @return String
		 */
		public String getUnitCombo(PackageServiceMstFB bean) 
		{			
			String strmsgText = null;
			String strUnitCmb = "";
			PackageServiceMstBO bo = null;
			PackageServiceMstVO voObj = null;
			HisUtil hisutil = new HisUtil("Billing", "PackageServiceMstDATA");		
			WebRowSet wb = null;

			try 
			{
				bo = new PackageServiceMstBO();
				voObj = new PackageServiceMstVO();
				voObj = (PackageServiceMstVO) TransferObjectFactory.populateData("billing.masters.vo.PackageServiceMstVO", bean);

				//wb = bo.unitCmb(voObj);
				//strUnitCmb = hisutil.getOptionValue(wb, bean.getStrunitId(),"0^Select Value", false);
				strUnitCmb=BillConfigUtil.getDefaultUnitCombo(bean.getStrunitId());
				
			} 
			catch (Exception e) 
			{
				strmsgText = "billing.masters.PackageServiceMstDATA.getUnitCombo(chk1,bean) --> "+ e.getMessage();
				HisException eObj = new HisException("Billing","PacServiceMaster->getUnitCombo()", strmsgText);
				bean.setStrMsgType("1");
				bean.setErrmsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator!");
				eObj = null;
			}
			return strUnitCmb;
		}		
	}