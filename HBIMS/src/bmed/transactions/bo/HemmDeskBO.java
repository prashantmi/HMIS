package bmed.transactions.bo;


import hisglobal.utility.HisUtil;

import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.WebRowSet;

import org.apache.struts.upload.FormFile;

//import bmed.dao.HemmsHEMMDeskDAO;
import bmed.dao.HemtComplaintApprovalDtlDAO;
import bmed.dao.HemtComplaintStatusDtlDAO;
import bmed.dao.HemtItemMcDtlDAO;
import bmed.dao.HemtNonItemMstDAO;
import bmed.dao.HsttSupplierItemMsDAO;
import bmed.dao.ItemBrandMstDAO;
import bmed.dao.ItemCurrStockDtlMstDAO;
import bmed.dao.ItemSubTypeMstDAO;
import bmed.dao.ItemTypeMstDAO;
import bmed.dao.SemtCancelTypeMstDAO;
import bmed.dao.SemtMaintTypeMstDAO;
import bmed.dao.StoreMstDAO;
import bmed.dao.UnitMstDAO;
import bmed.dao.WarrantyDtlDAO;
import bmed.global.controller.data.PerviousMaintenanceDetailsDATA;
import bmed.global.controller.data.PreviousWarrantyDATA;
import bmed.global.controller.data.StockDetailsDATA;
import hisglobal.transactionmgnt.HisDAO;
import bmed.transactions.dao.ItemMaintContractDtlsTransDAO;
import bmed.transactions.dao.ItemWarrantyDtlsTransDAO;
import bmed.transactions.vo.ItemMaintContractDtlsTransVO;
import bmed.transactions.vo.ItemWarrantyDtlsTransVO;
import bmed.vo.ComplaintRequestDtlVO;
import bmed.vo.HemDeskVO;
import bmed.vo.HemmsSparePartMgmtDeskVO;
import bmed.vo.HemtComplaintStatusDtlVO;
import bmed.vo.HemtItemMcDtlVO;
import bmed.vo.HemtNonItemMstVO;
import bmed.vo.HsttSupplierItemMstVO;
import bmed.vo.ItemBrandMstVO;
import bmed.vo.ItemCurrStockDtlMstVO;
import bmed.vo.ItemMaintContractDtlsVO;
import bmed.vo.ItemSubTypeMstVO;
import bmed.vo.ItemTypeMstVO;
import bmed.vo.SemtCancelTypeMstVO;
import bmed.vo.SemtMaintTypeMstVO;
import bmed.vo.StoreMstVO;
import bmed.vo.UnitMstVO;
import bmed.vo.WarrantyDtlVO;

public class HemmDeskBO 
{
	/*public static boolean saveHemDeskData(HemtComplaintStatusDtlVO hemtComplaintStatusDtlsVO,ComplaintRequestDtlVO hemtComplaintRequestDtlsVO,HemDeskVO hemDeskVO_p) throws Exception
	{		
		boolean retVal = false;
		HisDAO hisDAO_p = null;
		try 
		{	
			hisDAO_p = new HisDAO("BMED", "BmedGlobalBO");
			
			//HemtComplaintStatusDtlDAO.save(hemtComplaintStatusDtlsVO, hisDAO_p);
			
			HemmsHEMMDeskDAO.save(hemDeskVO_p, hisDAO_p);
			
			HemtComplaintApprovalDtlDAO.save(hemtComplaintRequestDtlsVO, hisDAO_p);
			hemtComplaintStatusDtlsVO.setStrMsgType("0");
			hisDAO_p.fire(); // Here we Execute in Batch
			retVal = true;

		} 
		catch (Exception ex) 
		{		
//			ex.printStackTrace();
			hemtComplaintStatusDtlsVO.setStrMsgType("1");
			throw new Exception("BmedGlobalBO.saveItemMaintenanceContactDetails(String,String)-->"+ex.getMessage());
		}
		finally
		{

			if (hisDAO_p != null) {

				hisDAO_p.free();

				hisDAO_p = null;

			}

		}
		return retVal;

	}*/
	
	/*public static boolean saveHemDeskSchedule(HemtComplaintStatusDtlVO hemtComplaintStatusDtlsVO,ComplaintRequestDtlVO hemtComplaintRequestDtlsVO,HemDeskVO hemDeskVO_p) throws Exception
	{		
		boolean retVal = false;
		HisDAO hisDAO_p = null;
		try 
		{	
			hisDAO_p = new HisDAO("BMED", "BmedGlobalBO");
			
			HemtComplaintStatusDtlDAO.save(hemtComplaintStatusDtlsVO, hisDAO_p);
			
			HemmsHEMMDeskDAO.saveSchedule(hemDeskVO_p, hisDAO_p);
			
			HemtComplaintApprovalDtlDAO.save(hemtComplaintRequestDtlsVO, hisDAO_p);
			hemtComplaintStatusDtlsVO.setStrMsgType("0");
			hisDAO_p.fire(); // Here we Execute in Batch
			retVal = true;

		} 
		catch (Exception ex) 
		{		
//			ex.printStackTrace();
			hemtComplaintStatusDtlsVO.setStrMsgType("1");
			throw new Exception("BmedGlobalBO.saveItemMaintenanceContactDetails(String,String)-->"+ex.getMessage());
		}
		finally
		{

			if (hisDAO_p != null) {

				hisDAO_p.free();

				hisDAO_p = null;

			}

		}
		return retVal;

	}
	
	public static boolean saveHemDeskAttend(HemtComplaintStatusDtlVO hemtComplaintStatusDtlsVO,ComplaintRequestDtlVO hemtComplaintRequestDtlsVO,HemDeskVO hemDeskVO_p) throws Exception
	{		
		boolean retVal = false;
		HisDAO hisDAO_p = null;
		try 
		{	
			hisDAO_p = new HisDAO("BMED", "BmedGlobalBO");
			
			HemtComplaintStatusDtlDAO.save(hemtComplaintStatusDtlsVO, hisDAO_p);
			
			HemmsHEMMDeskDAO.saveAttend(hemDeskVO_p, hisDAO_p);
			
			HemtComplaintApprovalDtlDAO.save(hemtComplaintRequestDtlsVO, hisDAO_p);
			hemtComplaintStatusDtlsVO.setStrMsgType("0");
			hisDAO_p.fire(); // Here we Execute in Batch
			retVal = true;

		} 
		catch (Exception ex) 
		{		
//			ex.printStackTrace();
			hemtComplaintStatusDtlsVO.setStrMsgType("1");
			throw new Exception("BmedGlobalBO.saveItemMaintenanceContactDetails(String,String)-->"+ex.getMessage());
		}
		finally
		{

			if (hisDAO_p != null) {

				hisDAO_p.free();

				hisDAO_p = null;

			}

		}
		return retVal;

	}
	//use for data saving for HEMM Desk Close process
	public static boolean saveHemDeskClose(HemtComplaintStatusDtlVO hemtComplaintStatusDtlsVO,ComplaintRequestDtlVO hemtComplaintRequestDtlsVO,HemDeskVO hemDeskVO_p) throws Exception
	{		
		boolean retVal = false;
		HisDAO hisDAO_p = null;
		try 
		{	
			hisDAO_p = new HisDAO("BMED", "BmedGlobalBO");
			
			HemtComplaintStatusDtlDAO.save(hemtComplaintStatusDtlsVO, hisDAO_p);
			
			HemmsHEMMDeskDAO.saveClose(hemDeskVO_p, hisDAO_p);
			
			HemtComplaintApprovalDtlDAO.save(hemtComplaintRequestDtlsVO, hisDAO_p);
			hemtComplaintStatusDtlsVO.setStrMsgType("0");
			hisDAO_p.fire(); // Here we Execute in Batch
			retVal = true;

		} 
		catch (Exception ex) 
		{		
//			ex.printStackTrace();
			hemtComplaintStatusDtlsVO.setStrMsgType("1");
			throw new Exception("BmedGlobalBO.saveItemMaintenanceContactDetails(String,String)-->"+ex.getMessage());
		}
		finally
		{

			if (hisDAO_p != null) {

				hisDAO_p.free();

				hisDAO_p = null;

			}

		}
		return retVal;

	}
	 used in complaint cancel, complaint schedule. 
	public void initializeHemDeskSchedule(HemDeskVO hemDeskVO_p,WarrantyDtlVO warrantyDtlVO_p, HemtItemMcDtlVO hemtItemMcDtlVO_p) throws Exception {

		HisDAO hisDao = null;
		String strWarrantySlNo = null;
		String strMcSlNo = null;

		try {
			 Transaction Start 
			hisDao = new HisDAO("bmed", "HemmDeskBO");

			hemDeskVO_p.setStrMode("1");
			System.out.println("Before calling getDATA in BO");
			HemmsHEMMDeskDAO.getData(hemDeskVO_p, hisDao);
			System.out.println("After calling getDATA in BO");
			if (hemDeskVO_p.getWrsData() != null) 
			{
				if (hemDeskVO_p.getWrsData().next()) 
				{
					strWarrantySlNo = hemDeskVO_p.getWrsData().getString("HEMNUM_WARRANTY_SLNO");
					      strMcSlNo = hemDeskVO_p.getWrsData().getString(	"HEMNUM_MC_SLNO");
				}
				hemDeskVO_p.getWrsData().beforeFirst();
				HemmsHEMMDeskDAO.getDataSingleRow(hemDeskVO_p);
			}
			
			 * A complaint can be registered against warranty or maintenance
			 * contract, not both. Either one of this is null, not both.
			 
			if (strWarrantySlNo != null) {
				warrantyDtlVO_p.setStrSlNo(strWarrantySlNo);

				
				 * Mode '4' is to get data in the basis of strWarrantySlNo and
				 * hospital code.
				 
				warrantyDtlVO_p.setStrMode("4");

				WarrantyDtlDAO.getData(warrantyDtlVO_p, hisDao);

			} else if (strMcSlNo != null) {
				hemtItemMcDtlVO_p.setStrSlNo(strMcSlNo);

				
				 * Mode 'x' is to get data in the basis of strWarrantySlNo and
				 * hospital code.
				 
				hemtItemMcDtlVO_p.setStrMode("4");

				if (hemtItemMcDtlVO_p.getStrItemId() == null) {
					hemtItemMcDtlVO_p.setStrItemId("");
				}
				if (hemtItemMcDtlVO_p.getStrItemSlNo() == null) {
					hemtItemMcDtlVO_p.setStrItemId("");
				}

				HemtItemMcDtlDAO.getPrevMantDtl(hemtItemMcDtlVO_p, hisDao);
			}			

		} catch (Exception e) {
			throw new Exception(
					"HemmDeskBO.initializeHemDeskSchedule(HemDeskVO)-->"
							+ e.getMessage());
		} finally {
			 Closing Transaction 
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}


	}*/
	
	/* used in complaint cancel, complaint schedule. */
	/*public void initializeHemDeskClose(HemDeskVO hemDeskVO_p,WarrantyDtlVO warrantyDtlVO_p, HemtItemMcDtlVO hemtItemMcDtlVO_p) throws Exception {

		HisDAO hisDao = null;
		String strWarrantySlNo = null;
		String strMcSlNo = null;

		try {
			 Transaction Start 
			hisDao = new HisDAO("bmed", "HemmDeskBO");

			hemDeskVO_p.setStrMode("2");
			System.out.println("Before calling getDATA in BO");
			HemmsHEMMDeskDAO.getData(hemDeskVO_p, hisDao);
			System.out.println("After calling getDATA in BO");
			if (hemDeskVO_p.getWrsData() != null) 
			{
				if (hemDeskVO_p.getWrsData().next()) 
				{
					hemDeskVO_p.setStrEngineerName(hemDeskVO_p.getWrsData().getString(1));
					hemDeskVO_p.setStrEngineerAddress(hemDeskVO_p.getWrsData().getString(2));
					hemDeskVO_p.setStrMobileNo(hemDeskVO_p.getWrsData().getString(3));
					hemDeskVO_p.setStrAttendDate(hemDeskVO_p.getWrsData().getString(4));
					hemDeskVO_p.setStrAttendTime(hemDeskVO_p.getWrsData().getString(5));
					hemDeskVO_p.setStrActualProblemDesc(hemDeskVO_p.getWrsData().getString(6));
					hemDeskVO_p.setStrAttendRemarks(hemDeskVO_p.getWrsData().getString(7));
					hemDeskVO_p.setStrVendorServiceEngName(hemDeskVO_p.getWrsData().getString(8));
					hemDeskVO_p.setStrCommunicateIdContactId(hemDeskVO_p.getWrsData().getString(9));
					hemDeskVO_p.setStrServiceAttendDate(hemDeskVO_p.getWrsData().getString(10));
					hemDeskVO_p.setStrServiceAttendTime(hemDeskVO_p.getWrsData().getString(11));
					hemDeskVO_p.setStrServiceClosingDate(hemDeskVO_p.getWrsData().getString(12));
					hemDeskVO_p.setStrServiceClosingTime(hemDeskVO_p.getWrsData().getString(13));
					hemDeskVO_p.setStrIsSparePartsMaintenanceInvolved(hemDeskVO_p.getWrsData().getString(14));
					hemDeskVO_p.setStrProblemDescription(hemDeskVO_p.getWrsData().getString(15));
					hemDeskVO_p.setStrSolutionProvided(hemDeskVO_p.getWrsData().getString(16));
					hemDeskVO_p.setStrServiceRemarks(hemDeskVO_p.getWrsData().getString(17));
					hemDeskVO_p.setStrReasonForClosing(hemDeskVO_p.getWrsData().getString(18));
					hemDeskVO_p.setStrIsItemInWorkingCondition(hemDeskVO_p.getWrsData().getString(19));
					
					hemDeskVO_p.setStrTotalCost(hemDeskVO_p.getWrsData().getString(20));
					hemDeskVO_p.setStrFromDate(hemDeskVO_p.getWrsData().getString(21));
					hemDeskVO_p.setStrFromTime(hemDeskVO_p.getWrsData().getString(22));
					hemDeskVO_p.setStrIsPenality(hemDeskVO_p.getWrsData().getString(23));
					hemDeskVO_p.setStrPenalityAmount(hemDeskVO_p.getWrsData().getString(24));
					hemDeskVO_p.setStrOfficeOrderNo(hemDeskVO_p.getWrsData().getString(25));
					hemDeskVO_p.setStrOfficeOrderDt(hemDeskVO_p.getWrsData().getString(26));
					
					hemDeskVO_p.setStrNetCost(hemDeskVO_p.getWrsData().getString(27));
					hemDeskVO_p.setStrVerifiedBy(hemDeskVO_p.getWrsData().getString(28));
					hemDeskVO_p.setStrVerifyDate(hemDeskVO_p.getWrsData().getString(29));
					hemDeskVO_p.setStrVerifyTime(hemDeskVO_p.getWrsData().getString(30));
					hemDeskVO_p.setStrServiceRemarks(hemDeskVO_p.getWrsData().getString(31));
					hemDeskVO_p.setStrPenaltyRemarks(hemDeskVO_p.getWrsData().getString(32));
					hemDeskVO_p.setStrOtherCharges(hemDeskVO_p.getWrsData().getString(33));
					
				}
				//hemDeskVO_p.getWrsData().beforeFirst();
			}
		} catch (Exception e) {
			throw new Exception(
					"HemmDeskBO.initializeHemDeskClose(HemDeskVO)-->"
							+ e.getMessage());
		} finally {
			 Closing Transaction 
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}


	}
	 used in complaint cancel, complaint schedule. 
	public void initializeHemDeskCloseMultiRow(HemDeskVO hemDeskVO_p,WarrantyDtlVO warrantyDtlVO_p, HemtItemMcDtlVO hemtItemMcDtlVO_p) throws Exception {

		HisDAO hisDao = null;
		String strWarrantySlNo = null;
		String strMcSlNo = null;
		
		List  strSpareSeqNo=new ArrayList();
		List strSparePartName=new ArrayList();
		List strSpareManufName= new ArrayList();
		List strSpareSlNo=new ArrayList();
		List strSpareManufSlNo=new ArrayList();
		List strStatus=new ArrayList();
		List strSpareCost=new ArrayList();
		
		try {
			 Transaction Start 
			hisDao = new HisDAO("bmed", "HemmDeskBO");

			hemDeskVO_p.setStrMode("3");
			System.out.println("Before calling getDATA in BO");
			HemmsHEMMDeskDAO.getData(hemDeskVO_p, hisDao);
			System.out.println("After calling getDATA in BO");
			while(hemDeskVO_p.getWrsData()!=null && hemDeskVO_p.getWrsData().next())
					{
				strSpareSeqNo.add(hemDeskVO_p.getWrsData().getString(1));
				strSparePartName.add(hemDeskVO_p.getWrsData().getString(2));
				strSpareManufName.add(hemDeskVO_p.getWrsData().getString(3));
				strSpareSlNo.add(hemDeskVO_p.getWrsData().getString(4));
				strSpareManufSlNo.add(hemDeskVO_p.getWrsData().getString(5));
				strStatus.add(hemDeskVO_p.getWrsData().getString(6));
				strSpareCost.add(hemDeskVO_p.getWrsData().getString(7));
					}

			hemDeskVO_p.setStrSpareSeqNo((String[])strSpareSeqNo.toArray(new String[0]));
			hemDeskVO_p.setStrSparePartName((String[])strSparePartName.toArray(new String[0]));
			hemDeskVO_p.setStrSpareManufName((String[])strSpareManufName.toArray(new String[0]));
			hemDeskVO_p.setStrSpareSlNo((String[])strSpareSlNo.toArray(new String[0]));
			hemDeskVO_p.setStrSpareManufSlNo((String[])strSpareManufSlNo.toArray(new String[0]));
			hemDeskVO_p.setStrStatus((String[])strStatus.toArray(new String[0]));
			hemDeskVO_p.setStrSpareCost((String[]) strSpareCost.toArray(new String[0]));
			
		} catch (Exception e) {
			throw new Exception(
					"HemmDeskBO.initializeHemDeskCloseMultiRow(HemDeskVO)-->"
							+ e.getMessage());
		} finally {
			 Closing Transaction 
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}


	}
	
	 used in HEMM complaint view process
	public void initializeHemDeskView(HemDeskVO hemDeskVO_p,WarrantyDtlVO warrantyDtlVO_p, HemtItemMcDtlVO hemtItemMcDtlVO_p) throws Exception {

		HisDAO hisDao = null;
		String strWarrantySlNo = null;
		String strMcSlNo = null;

		try {
			 Transaction Start 
			hisDao = new HisDAO("bmed", "HemmDeskBO");

			hemDeskVO_p.setStrMode("4");
			System.out.println("Before calling getDATA in BO");
			HemmsHEMMDeskDAO.getData(hemDeskVO_p, hisDao);
			System.out.println("After calling getDATA in BO");
			if (hemDeskVO_p.getWrsData() != null) 
			{
				if (hemDeskVO_p.getWrsData().next()) 
				{
					hemDeskVO_p.setStrClosingDate(hemDeskVO_p.getWrsData().getString(1));
					hemDeskVO_p.setStrClosingTime(hemDeskVO_p.getWrsData().getString(2));
					hemDeskVO_p.setStrHEMReasonForClosing(hemDeskVO_p.getWrsData().getString(3));
					hemDeskVO_p.setStrHEMVendorId(hemDeskVO_p.getWrsData().getString(4));
					hemDeskVO_p.setStrHEMTotalCost(hemDeskVO_p.getWrsData().getString(5));
					hemDeskVO_p.setStrIsHEMItemInWorkingCondition(hemDeskVO_p.getWrsData().getString(6));
					hemDeskVO_p.setStrDownFromDate(hemDeskVO_p.getWrsData().getString(7));
					hemDeskVO_p.setStrDownToDate(hemDeskVO_p.getWrsData().getString(8));
					hemDeskVO_p.setStrDownTotalTime(hemDeskVO_p.getWrsData().getString(9));
					hemDeskVO_p.setStrHEMVerifiedBy(hemDeskVO_p.getWrsData().getString(10));
					hemDeskVO_p.setStrHEMClosingRemarks(hemDeskVO_p.getWrsData().getString(11));
					hemDeskVO_p.setStrClosingComplaintStatus(hemDeskVO_p.getWrsData().getString(12));
					hemDeskVO_p.setStrPendencyRemarks(hemDeskVO_p.getWrsData().getString(13));
					
				}
				//hemDeskVO_p.getWrsData().beforeFirst();
			}
		} catch (Exception e) {
			throw new Exception(
					"HemmDeskBO.initializeHemDeskView(HemDeskVO)-->"
							+ e.getMessage());
		} finally {
			 Closing Transaction 
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}


	}*/
	/**
	 * @param strHospitalCode_p
	 *            The hospital code
	 * @return list of department options.
	 * @throws Exception
	 */
	public String getDepartmentComboOptions(String strHospitalCode_p,String strSeatId,int strMode)
			throws Exception {

		String strDepartmentComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		ItemWarrantyDtlsTransVO vo;
		WebRowSet wrsDepartmentComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			vo = new ItemWarrantyDtlsTransVO();
			vo.setStrHospitalCode(strHospitalCode_p);
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			
			
			vo.setStrSeatId(strSeatId);
				vo.setStrMode(Integer.toString(strMode));
			
			
				ItemWarrantyDtlsTransDAO.getBmedHospitalsCombo(vo, hisDAO);
			wrsDepartmentComboOptions = vo.getWrsDepartmentOptions();
			if (wrsDepartmentComboOptions != null) {
				strDepartmentComboOptions = hisUtil.getOptionValue(wrsDepartmentComboOptions, "0","0^Select Value", false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getDepartmentComboOptions(String)-->"
							+ ex.getMessage());
		}
		return strDepartmentComboOptions;

	}
	
	/**
	 * @param strHospitalCode_p
	 *            The hospital code
	 * @return list of department options.
	 * @throws Exception
	 */
	public String getBmedHospitalsCombo(ItemMaintContractDtlsTransVO vo)
			throws Exception {

		String strDepartmentComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		;
		WebRowSet wrsDepartmentComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			
			
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
					
			
				ItemMaintContractDtlsTransDAO.getBmedHospitalsCombo(vo, hisDAO);
			wrsDepartmentComboOptions = vo.getWrsDepartmentOptions();
			if (wrsDepartmentComboOptions != null) {
				strDepartmentComboOptions = hisUtil.getOptionValue(wrsDepartmentComboOptions,vo.getStrDeptCode(),"0^Select Value", false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getDepartmentComboOptions(String)-->"
							+ ex.getMessage());
		}
		return strDepartmentComboOptions;

	}
	
	
	
	public String getItemTypeComboOptions(String strHospitalCode_p)
	throws Exception {
		
		String strItemTypeComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		ItemTypeMstVO gbltItemTypeMstVO;
		WebRowSet wrsItemTypeComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltItemTypeMstVO = new ItemTypeMstVO();
			gbltItemTypeMstVO.setStrHospitalCode(strHospitalCode_p);
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			ItemTypeMstDAO.getItemTypeCombo(gbltItemTypeMstVO, hisDAO);
			wrsItemTypeComboOptions = gbltItemTypeMstVO
					.getWrsItemTypeComboOptions();
			if (wrsItemTypeComboOptions != null) {
				strItemTypeComboOptions = hisUtil.getOptionValue(
						wrsItemTypeComboOptions, "0", "0^Select Value", false);
		
			}
		
		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getItemTypeComboOptions(String)-->"
							+ ex.getMessage());
		}
		return strItemTypeComboOptions;
		
		}
	
	/**
	 * Method by Amit Kumar
	 * 
	 * @param strHospitalCode_p
	 * @return
	 * @throws Exception
	 */
	public static String getUnitComboOptions(String strHospitalCode_p)
			throws Exception {

		String strUnitComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		UnitMstVO gbltUnitMstVO;
		WebRowSet wrsUnitComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltUnitMstVO = new UnitMstVO();
			gbltUnitMstVO.setStrMode("1");
			gbltUnitMstVO.setStrHospitalCode(strHospitalCode_p);
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			UnitMstDAO.getUnitMstCombo(gbltUnitMstVO, hisDAO);
			wrsUnitComboOptions = gbltUnitMstVO.getWrsUnitComboOptions();
			if (wrsUnitComboOptions != null) {
				strUnitComboOptions = hisUtil.getOptionValue(
						wrsUnitComboOptions, "0", "0^Select Value", false);

			}

		} catch (Exception ex) {
			throw new Exception("BmedGlobalBO.getUnitComboOptions(String)-->"
					+ ex.getMessage());
		}
		return strUnitComboOptions;

	}
	
	public String getMaintenanceContTypeComboOptions(String strHospitalCode_p)
	throws Exception {
		
		String strMaintTypeComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		SemtMaintTypeMstVO gbltSemtMaintTypeMstVO;
		WebRowSet wrsMainContractComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltSemtMaintTypeMstVO = new SemtMaintTypeMstVO();
			gbltSemtMaintTypeMstVO.setStrMode("1");
			gbltSemtMaintTypeMstVO.setStrHospCode(strHospitalCode_p);
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			SemtMaintTypeMstDAO.getSemtMaintTypeMstCombo(
					gbltSemtMaintTypeMstVO, hisDAO);
			wrsMainContractComboOptions = gbltSemtMaintTypeMstVO
					.getWrsMaintTypeComboOptions();
			if (wrsMainContractComboOptions != null) {
				strMaintTypeComboOptions = hisUtil.getOptionValue(
						wrsMainContractComboOptions, "0", "0^Select Value",
						false);
		
			}
		
		} catch (Exception ex) {
			throw new Exception("BmedGlobalBO.getUnitComboOptions(String)-->"
					+ ex.getMessage());
		}
		return strMaintTypeComboOptions;
		
		}
	
		public String getStoreComboOptions(String strHospitalCode_p,
				String strSeatid_p, String strDeptCode_p) throws Exception {

			String strStoreComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
			StoreMstVO storeMstVO;
			WebRowSet wrsStoreComboOptions;
			HisUtil hisUtil;
			try {
				hisUtil = new HisUtil("BMED", "BmedGlobalBO");
				storeMstVO = new StoreMstVO();

				storeMstVO.setStrHospitalCode(strHospitalCode_p);
				storeMstVO.setStrMode("5");
				storeMstVO.setStrSeatid(strSeatid_p);
				storeMstVO.setStrDeptCode(strDeptCode_p);

				/* Default Value */
				storeMstVO.setStrStoreId("0");
				storeMstVO.setStrStoretypeId("0");

				HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
				StoreMstDAO.getStoreCombo(storeMstVO, hisDAO);
				wrsStoreComboOptions = storeMstVO.getWrsStoreComboOptions();
				
				if (wrsStoreComboOptions != null) {
					strStoreComboOptions = hisUtil.getOptionValue(wrsStoreComboOptions,storeMstVO.getStrStoreId(), "0^Select Value", false);
				}
				
				/* Sets The WebRowSet in StoreMstVO */
				storeMstVO.setWrsStoreComboOptions(wrsStoreComboOptions);
				
				

			} catch (Exception ex) {
				throw new Exception("BmedGlobalBO.getStoreComboOptions(String)-->"
						+ ex.getMessage());
			}
			return strStoreComboOptions;

		}
				
		
		public String getItemSubTypeComboOptions(String strHospitalCode_p,
				String strEnggItemTypeId_p) throws Exception {

			String strItemSubTypeComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
			ItemSubTypeMstVO itemSubTypeMstVO;
			WebRowSet wrsItemSubTypeComboOptions;
			HisUtil hisUtil;
			try {
				hisUtil = new HisUtil("BMED", "BmedGlobalBO");
				itemSubTypeMstVO = new ItemSubTypeMstVO();
				itemSubTypeMstVO.setStrHospitalCode(strHospitalCode_p);
				itemSubTypeMstVO.setStrEnggItemTypeId(strEnggItemTypeId_p);
				HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
				ItemSubTypeMstDAO.getItemSubTypeCombo(itemSubTypeMstVO, hisDAO);
				wrsItemSubTypeComboOptions = itemSubTypeMstVO
						.getWrsItemSubTypeComboOptions();
				if (wrsItemSubTypeComboOptions != null) {
					strItemSubTypeComboOptions = hisUtil.getOptionValue(
							wrsItemSubTypeComboOptions, "0", "0^Select Value",
							false);

				}

			} catch (Exception ex) {
				throw new Exception(
						"BmedGlobalBO.getItemSubTypeComboOptions(String,String)-->"
								+ ex.getMessage());
			}
			return strItemSubTypeComboOptions;

		}
		
		public String getItemBrandComboOptionsOnStore(String strHospitalCode_p,
				String strStoreId_p) throws Exception {

			String strItemBrandComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
			ItemBrandMstVO gbltItemBrandMstVO;
			WebRowSet wrsItemBrandComboOptions;
			HisUtil hisUtil;
			try {
				hisUtil = new HisUtil("BMED", "BmedGlobalBO");
				gbltItemBrandMstVO = new ItemBrandMstVO();

//				gbltItemBrandMstVO.setStrMode("5");
				gbltItemBrandMstVO.setStrMode("10");
				gbltItemBrandMstVO.setStrItemCatNo("0");
				gbltItemBrandMstVO.setStrStoreId(strStoreId_p);
				gbltItemBrandMstVO.setStrDepartmentId("0");
				gbltItemBrandMstVO.setStrHospitalCode(strHospitalCode_p);
				
				HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
				ItemBrandMstDAO.getItemBrandCombo(gbltItemBrandMstVO, hisDAO);
				wrsItemBrandComboOptions = gbltItemBrandMstVO
						.getWrsItemBrandComboOptions();
				if (wrsItemBrandComboOptions != null) {
					strItemBrandComboOptions = hisUtil.getOptionValue(
							wrsItemBrandComboOptions, "0", "0^Select Value", false);

				}

			} catch (Exception ex) {
				throw new Exception(
						"ItemMaintContractDtlsBO.getItemBrandComboOptions(String)-->"
								+ ex.getMessage());
			}
			return strItemBrandComboOptions;

		}
		/**
		 * for getting value of item brand combo 
		 * 
		 * @param vo
		 * @throws Exception
		 */

		public void getItemBrandName(ItemMaintContractDtlsVO vo) {
			ItemMaintContractDtlsTransDAO.getItemBrandName(vo);
			if (vo.getStrMsgType().equals("1")) {

				String strErr = vo.getStrMsgString();

				vo.setStrMsgString("ItemInventoryTransBO.getItemBrandName() --> "
						+ strErr);
			}

		}
		
		/*public String getItemBrandComboOptionsOnStore(String strHospitalCode_p,
				String strStoreId_p,String strEquipmentId_p) throws Exception {

			String strItemBrandComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
			ItemBrandMstVO gbltItemBrandMstVO;
			WebRowSet wrsItemBrandComboOptions;
			HisUtil hisUtil;
			try {
				hisUtil = new HisUtil("BMED", "BmedGlobalBO");
				gbltItemBrandMstVO = new ItemBrandMstVO();

//				gbltItemBrandMstVO.setStrMode("5");
				gbltItemBrandMstVO.setStrMode("10");
				gbltItemBrandMstVO.setStrItemCatNo("0");
				gbltItemBrandMstVO.setStrStoreId(strStoreId_p);
				gbltItemBrandMstVO.setStrDepartmentId("0");
				gbltItemBrandMstVO.setStrHospitalCode(strHospitalCode_p);
				gbltItemBrandMstVO.setStrItemId(strEquipmentId_p);
				
				HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
				ItemBrandMstDAO.getItemBrandCombo(gbltItemBrandMstVO, hisDAO);
				wrsItemBrandComboOptions = gbltItemBrandMstVO
						.getWrsItemBrandComboOptions();
				if (wrsItemBrandComboOptions != null) {
					strItemBrandComboOptions = hisUtil.getOptionValue(
							wrsItemBrandComboOptions, "0", "0^Select Value", false);

				}

			} catch (Exception ex) {
				throw new Exception(
						"ItemMaintContractDtlsBO.getItemBrandComboOptions(String)-->"
								+ ex.getMessage());
			}
			return strItemBrandComboOptions;

		}*/
		
		/*public String getEquipmentNameComboOptionsOnStore(String strHospitalCode_p,
				String strStoreId_p,String strEquipmentNameId_p) throws Exception {

			String strItemBrandComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
			ItemBrandMstVO gbltItemBrandMstVO;
			WebRowSet wrsItemBrandComboOptions;
			HisUtil hisUtil;
			try {
				hisUtil = new HisUtil("BMED", "BmedGlobalBO");
				gbltItemBrandMstVO = new ItemBrandMstVO();

//				gbltItemBrandMstVO.setStrMode("5");
				gbltItemBrandMstVO.setStrMode("10");
				gbltItemBrandMstVO.setStrItemCatNo("0");
				gbltItemBrandMstVO.setStrStoreId(strStoreId_p);
				gbltItemBrandMstVO.setStrDepartmentId("0");
				gbltItemBrandMstVO.setStrHospitalCode(strHospitalCode_p);
				gbltItemBrandMstVO.setStrItemId(strEquipmentNameId_p);

				HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
				ItemBrandMstDAO.getEquipmentNameCombo(gbltItemBrandMstVO, hisDAO);
				wrsItemBrandComboOptions = gbltItemBrandMstVO
						.getWrsItemBrandComboOptions();
				if (wrsItemBrandComboOptions != null) {
					strItemBrandComboOptions = hisUtil.getOptionValue(
							wrsItemBrandComboOptions, "0", "0^Select Value", false);

				}

			} catch (Exception ex) {
				throw new Exception(
						"ItemMaintContractDtlsBO.getEquipmentNameComboOptionsOnStore(String)-->"
								+ ex.getMessage());
			}
			return strItemBrandComboOptions;

		}*/
		
		public String getItemBrandComboOptionsOnDepartment(
				String strHospitalCode_p, String strDepartmentId_p)
				throws Exception {

			String strItemBrandComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
			ItemBrandMstVO gbltItemBrandMstVO;
			WebRowSet wrsItemBrandComboOptions;
			HisUtil hisUtil;
			try {
				hisUtil = new HisUtil("BMED", "BmedGlobalBO");
				gbltItemBrandMstVO = new ItemBrandMstVO();

				gbltItemBrandMstVO.setStrMode("4");
				gbltItemBrandMstVO.setStrItemCatNo("0");
				gbltItemBrandMstVO.setStrStoreId("0");
				gbltItemBrandMstVO.setStrDepartmentId(strDepartmentId_p);
				gbltItemBrandMstVO.setStrHospitalCode(strHospitalCode_p);

				HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
				ItemBrandMstDAO.getItemBrandCombo(gbltItemBrandMstVO, hisDAO);
				wrsItemBrandComboOptions = gbltItemBrandMstVO
						.getWrsItemBrandComboOptions();
				if (wrsItemBrandComboOptions != null) {
					strItemBrandComboOptions = hisUtil.getOptionValue(
							wrsItemBrandComboOptions, "0", "0^Select Value", false);

				}

			} catch (Exception ex) {
				throw new Exception(
						"ItemMaintContractDtlsBO.getItemBrandComboOptionsOnDepartment(String)-->"
								+ ex.getMessage());
			}
			return strItemBrandComboOptions;

		}
		
		public String getItemBrandComboOptionsOnBasisOfEngg(
				String strHospitalCode_p, String strEnggItemType_p,
				String strEnggItemSubType_p) throws Exception {

			String strItemBrandComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
			HemtNonItemMstVO hemtNonItemMstVO_p;
			WebRowSet wrsItemBrandComboOptions;
			HisUtil hisUtil;
			try {
				hisUtil = new HisUtil("BMED", "BmedGlobalBO");
				hemtNonItemMstVO_p = new HemtNonItemMstVO();

				hemtNonItemMstVO_p.setStrMode("1");
				hemtNonItemMstVO_p.setStrEnggItemTypeId(strEnggItemType_p);
				hemtNonItemMstVO_p.setStrEnggItemSubTypeId(strEnggItemSubType_p);
				hemtNonItemMstVO_p.setStrHospCode(strHospitalCode_p);

				HisDAO hisDAO_p = new HisDAO("BMED", "BmedGlobalBO");
				HemtNonItemMstDAO.getNonItemCmb(hemtNonItemMstVO_p, hisDAO_p);
				wrsItemBrandComboOptions = hemtNonItemMstVO_p
						.getWrsNonItemDetails();

				if (wrsItemBrandComboOptions != null) {
					strItemBrandComboOptions = hisUtil.getOptionValue(
							wrsItemBrandComboOptions, "0", "0^Select Value", false);

				}

			} catch (Exception ex) {
				throw new Exception(
						"ItemMaintContractDtlsBO.getItemBrandComboOptionsOnDepartment(String)-->"
								+ ex.getMessage());
			}
			return strItemBrandComboOptions;

		}
		
		public String getStockDetails(String strHospitalCode_p,
				String strItemBrandId_p, String strDeptId_p) throws Exception {

			ItemCurrStockDtlMstVO gblItemCurrStockMstVO;
			WebRowSet wrsStockDtl;
			String strStockDtl;
			try {

				gblItemCurrStockMstVO = new ItemCurrStockDtlMstVO();
				gblItemCurrStockMstVO.setStrMode("2");
				gblItemCurrStockMstVO.setStrItemBrandId(strItemBrandId_p);
				gblItemCurrStockMstVO.setStrDeptId(strDeptId_p);
				gblItemCurrStockMstVO.setStrHospCode(strHospitalCode_p);
				gblItemCurrStockMstVO.setStrItemId("0");
				gblItemCurrStockMstVO.setStrBatchNo("0");
				gblItemCurrStockMstVO.setStrStoreId("0");
				gblItemCurrStockMstVO.setStrItemSlNo("0");
				gblItemCurrStockMstVO.setStrItemCatgNo("0");
				gblItemCurrStockMstVO.setStrStockStatusCode("0");

				HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
				ItemCurrStockDtlMstDAO.getStockDtl(gblItemCurrStockMstVO, hisDAO);
				wrsStockDtl = gblItemCurrStockMstVO.getWrsStockDtl();

				strStockDtl = StockDetailsDATA.getStockDtl(wrsStockDtl);

			} catch (Exception ex) {
				throw new Exception("ItemMaintContractDtlsBO.getStockDetails(String)-->"
						+ ex.getMessage());
			}
			return strStockDtl;

		}
		
		/**
		 * This Method is Used to get stock details for HLP
		 * @param strHospitalCode_p
		 * @param strItemBrandId_p
		 * @param strDeptId_p
		 * @param strMode_p
		 * @return
		 * @throws Exception
		 */
		public String getStockDetails(String strHospitalCode_p,String strItemBrandId_p,String strStoreId_p,String strMode_p) throws Exception 
		{
			
			ItemCurrStockDtlMstVO gblItemCurrStockMstVO;
			WebRowSet wrsStockDtl;
			String strStockDtl;
			try 
			{
				
				gblItemCurrStockMstVO = new ItemCurrStockDtlMstVO();
				if(strMode_p.equals("1"))
				{	
//				   gblItemCurrStockMstVO.setStrMode("2");
				   gblItemCurrStockMstVO.setStrMode("5");	// For DWH_BMED
				}
				else
				{
				   gblItemCurrStockMstVO.setStrMode("3");
				}	
				gblItemCurrStockMstVO.setStrItemBrandId(strItemBrandId_p);
//				gblItemCurrStockMstVO.setStrDeptId(strDeptId_p);
				gblItemCurrStockMstVO.setStrDeptId("0");
				gblItemCurrStockMstVO.setStrHospCode(strHospitalCode_p);
				gblItemCurrStockMstVO.setStrItemId("0");
				gblItemCurrStockMstVO.setStrBatchNo("0");
//				gblItemCurrStockMstVO.setStrStoreId("0");
				gblItemCurrStockMstVO.setStrStoreId(strStoreId_p);
				gblItemCurrStockMstVO.setStrItemSlNo("0");
				gblItemCurrStockMstVO.setStrItemCatgNo("0");
				gblItemCurrStockMstVO.setStrStockStatusCode("0");
						
				HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
				ItemCurrStockDtlMstDAO.getStockDtl(gblItemCurrStockMstVO, hisDAO);
				wrsStockDtl = gblItemCurrStockMstVO.getWrsStockDtl();
				
				strStockDtl = StockDetailsDATA.getStockDtlWarranty(wrsStockDtl);
				

			} catch (Exception ex) {
				throw new Exception("ItemMaintContractDtlsBO.getStockDetails(String)-->"+ex.getMessage());
			}
			return strStockDtl;

		}
		
		/**
		 * Method is used to get previous Warranty details
		 * @param strHospitalCode_p
		 * @param strStockInfo_p
		 * @param strMode
		 * @return
		 * @throws Exception
		 */
		public String getPrevWarrantyDetails(String strHospitalCode_p,String strStockInfo_p,String strMode) throws Exception 
		{
			
			WarrantyDtlVO gblWarrantyDtlVO;
			String strPrevMantDtl=null;
			try 
			{
				
				 /*  0                        1                   2
	         	 * HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
	         	 *              3                   4
	         	   ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
	         	             5                      6                                 7
	                GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE||'^'||Is_Item(1 : for Item 2:for Non-Item)
	         	 */
				
				gblWarrantyDtlVO = new WarrantyDtlVO();
				            
	            gblWarrantyDtlVO.setStrMode("5");
//	            System.out.println("Stock Info:::::"+strStockInfo_p);
//	            System.out.println("Item ID:::"+strStockInfo_p.split("\\^")[1]);
//	            System.out.println("Item Brand ID:::"+strStockInfo_p.split("\\^")[2]);
//	            System.out.println("Batch Sl No::"+strStockInfo_p.split("\\^")[3]);
//	            System.out.println("Item ID:::"+strStockInfo_p.split("\\^")[4]);
	            if(strStockInfo_p.split("\\^")[7].equals("2"))
	            {	
				 gblWarrantyDtlVO.setStrItemId(strStockInfo_p.split("\\^")[2]);
				 gblWarrantyDtlVO.setStrItemBrandId("0");
	            }
	            else
	            {
	             gblWarrantyDtlVO.setStrItemId(strStockInfo_p.split("\\^")[1]);
	   			 gblWarrantyDtlVO.setStrItemBrandId(strStockInfo_p.split("\\^")[2]);
	            }
				gblWarrantyDtlVO.setStrBatchSlNo(strStockInfo_p.split("\\^")[3]);
				gblWarrantyDtlVO.setStrItemSlNo(strStockInfo_p.split("\\^")[4]);
				gblWarrantyDtlVO.setStrSlNo("0");
				gblWarrantyDtlVO.setStrHospitalCode(strHospitalCode_p);
									
				HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
						
				WarrantyDtlDAO.getData(gblWarrantyDtlVO, hisDAO);
				
				strPrevMantDtl = PreviousWarrantyDATA.getPreviousWarrantyDetails(gblWarrantyDtlVO.getWrsResultData(),strMode);
				

			} catch (Exception ex) {
				throw new Exception("ItemMaintContractDtlsBO.getPrevMantDetails(String)-->"+ex.getMessage());
			}
			return strPrevMantDtl;

		}
		
		/**
		 * Method by Amit Kumar
		 * 
		 * @param strHospitalCode_p
		 * @param strItemBrandId_p
		 * @return
		 * @throws Exception
		 */
		public String getSupplierComboOptions(String strHospitalCode_p,
				String strItemBrandId_p, String strCheckIsItem_p) throws Exception {

			String strSupplierComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
			HsttSupplierItemMstVO supplierItemMstVO;
			WebRowSet wrsItemSubTypeComboOptions;
			HisUtil hisUtil;
			try {
				hisUtil = new HisUtil("BMED", "BmedGlobalBO");
				supplierItemMstVO = new HsttSupplierItemMstVO();
				if (strCheckIsItem_p.equals("1")) {
					supplierItemMstVO.setStrItemId("0");
					supplierItemMstVO.setStrItemBrandId(strItemBrandId_p);
				} else {
					supplierItemMstVO.setStrItemId(strItemBrandId_p);
					supplierItemMstVO.setStrItemBrandId("0");
				}
				supplierItemMstVO.setStrMode("2");
				supplierItemMstVO.setStrHospCode(strHospitalCode_p);
				supplierItemMstVO.setStrSupplierId("0");
				supplierItemMstVO.setStrSupplItemSlNo("0");

				HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
				HsttSupplierItemMsDAO.getSupplierCmb(supplierItemMstVO, hisDAO);
				
				wrsItemSubTypeComboOptions = supplierItemMstVO.getWrsSupplierDetails();

				if (wrsItemSubTypeComboOptions != null)
				{
					strSupplierComboOptions = hisUtil.getOptionValue(
							wrsItemSubTypeComboOptions, "0", "0^Select Value",
							false);
				}

			} catch (Exception ex) {
				throw new Exception(
						"ItemMaintContractDtlsBO.getSupplierComboOptions(String,String)-->"
								+ ex.getMessage());
			}
			return strSupplierComboOptions;

		}
		
		/**
		 * To Create File Name on the basis of Doc ref No
		 * Developer Adil Wasi
		 * 
		 * @param 	strHospitalCode_p String
		 * 
		 * @return	strRetVal the String
		 * 
		 */
		
		public static String getFileName(FormFile myFile, String strHospitalCode_p, String strMode)
				throws Exception 
		{
			
			String strFileExt;
			String[] arrStrTmp=null;
			String strFileName;
			try
			{					     
				              strFileExt = myFile.getFileName();
				               arrStrTmp = strFileExt.replace('.', '#').split("#");
				              strFileExt = arrStrTmp[arrStrTmp.length-1];
				              strFileName = BmedTransBO.getStrDocRefNo(strHospitalCode_p,strMode)+"."+strFileExt;
				              //System.out.println("strFileName (Inside BO) ::::::"+strFileName);
				               return strFileName;
				               
			}catch (Exception ex) {
				throw new Exception("BmedTransBO.getFileName(String)-->" + ex.getMessage());
			}
			
		}
		
		/**
		 * saveItemMaintenanceContactDetails() Method is used to cancel data form table [ hemt_item_mc_dtl ]
		 * @param hemtItemMcGlobalVO_p
		 * @return
		 */
		public static boolean saveItemWarrantyDetails(WarrantyDtlVO hsttWarrantyDtlVO_p) throws Exception
		{		
			boolean retVal = false;
			HisDAO hisDAO_p = null;
			try 
			{	
				hisDAO_p = new HisDAO("BMED", "BmedGlobalBO");
				WarrantyDtlDAO.insert(hsttWarrantyDtlVO_p, hisDAO_p);
				
				hisDAO_p.fire(); // Here we Execute in Batch
				retVal = true;
				hsttWarrantyDtlVO_p.setStrMsgType("0");

			} 
			catch (Exception ex) 
			{			 
				hsttWarrantyDtlVO_p.setStrMsgType("1");
				throw new Exception("ItemMaintContractDtlsBO.saveItemMaintenanceContactDetails(String,String)-->"+ex.getMessage());
			}
			finally
			{

				if (hisDAO_p != null) {

					hisDAO_p.free();

					hisDAO_p = null;

				}

			}
			return retVal;

		}
		
		/**
		 * Method is used to get Renew page source
		 * @param strHospitalCode_p
		 * @param strItemBrandId_p
		 * @return
		 * @throws Exception
		 */
		public String getRenewPageSrc(String strHospitalCode_p,String strItemBrandId_p) throws Exception {

			String strMaintTypeComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
			SemtMaintTypeMstVO gbltSemtMaintTypeMstVO_p;
			String strUnitComboOptions;
			WebRowSet wrsMainContractComboOptions;
			
			HisUtil hisUtil;
			try {
				hisUtil = new HisUtil("BMED", "BmedGlobalBO");
				gbltSemtMaintTypeMstVO_p = new SemtMaintTypeMstVO();
				gbltSemtMaintTypeMstVO_p.setStrMode("1");
				gbltSemtMaintTypeMstVO_p.setStrHospCode(strHospitalCode_p);
				HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
				SemtMaintTypeMstDAO.getSemtMaintTypeMstCombo(gbltSemtMaintTypeMstVO_p, hisDAO);
				strUnitComboOptions = BmedGlobalBO.getUnitComboOptions(strHospitalCode_p);
				wrsMainContractComboOptions = gbltSemtMaintTypeMstVO_p.getWrsMaintTypeComboOptions();
				if (wrsMainContractComboOptions != null) 
				{
					strMaintTypeComboOptions = hisUtil.getOptionValue(wrsMainContractComboOptions, "0", "0^Select Value", false);

				}
				

			} catch (Exception ex) {
				throw new Exception("ItemMaintContractDtlsBO.getSupplierComboOptions(String,String)-->"+ex.getMessage());
			}
			return strMaintTypeComboOptions+"@@"+strUnitComboOptions;

		}
		
		/**
		 * Method is used to get Cancel Page Src
		 * @param strHospitalCode_p
		 * @param strItemBrandId_p
		 * @return
		 * @throws Exception
		 */
		
		public String getCancelPageSrc(String strHospitalCode_p,String strItemBrandId_p) throws Exception {

			String strCancelTypeComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
			SemtCancelTypeMstVO gbltSemtCancelTypeMstVO_p;
			WebRowSet wrsCancelComboOptions;
			
			HisUtil hisUtil;
			try {
				hisUtil = new HisUtil("BMED", "BmedGlobalBO");
				gbltSemtCancelTypeMstVO_p = new SemtCancelTypeMstVO();
				gbltSemtCancelTypeMstVO_p.setStrMode("1");
				gbltSemtCancelTypeMstVO_p.setStrHospCode(strHospitalCode_p);
				HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
				SemtCancelTypeMstDAO.getSemtCancelTypeMstCombo(gbltSemtCancelTypeMstVO_p, hisDAO);
				wrsCancelComboOptions = gbltSemtCancelTypeMstVO_p.getWrsCancelTypeInfo();
				if (wrsCancelComboOptions != null) 
				{
					strCancelTypeComboOptions = hisUtil.getOptionValue(wrsCancelComboOptions, "0", "0^Select Value", false);

				}
				

			} catch (Exception ex) {
				throw new Exception("BmedGlobalBO.getSupplierComboOptions(String,String)-->"+ex.getMessage());
			}
			return strCancelTypeComboOptions;

		}
		/**
		 * Method is used to get previous maintenance details HLP
		 * @param strHospitalCode_p
		 * @param strStockInfo_p
		 * @param strMode
		 * @return
		 * @throws Exception
		 */
		public String getPrevMantDetails(String strHospitalCode_p,String strStockInfo_p,String strMode) throws Exception 
		{
			
			HemtItemMcDtlVO gblItemMCDtlVO;
			String strPrevMantDtl=null;
			try 
			{
				 /*  0                        1                   2
	         	 * HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
	         	 *              3                   4
	         	   ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
	         	             5                      6                                 7
	                GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE||'^'||Is_Item(1 : for Item 2:for Non-Item)
	         	 */
				gblItemMCDtlVO = new HemtItemMcDtlVO();
				
				if(strStockInfo_p.split("\\^")[7].equals("1"))  // For Item
				{
					
					gblItemMCDtlVO.setStrMode("2");
					gblItemMCDtlVO.setStrItemId(strStockInfo_p.split("\\^")[2]);
				}
				else
				{
					
					gblItemMCDtlVO.setStrMode("3");
					gblItemMCDtlVO.setStrItemId(strStockInfo_p.split("\\^")[2]);
				}
//				System.out.println("getPrevMantDetails()--:>");
//				System.out.println("Item Id:::::"+gblItemMCDtlVO.getStrItemId());
//				System.out.println("Hosp Code:::"+strHospitalCode_p);
//				System.out.println("Item Brand Id:::"+strStockInfo_p.split("\\^")[2]);
//				System.out.println("Batch:::"+strStockInfo_p.split("\\^")[3]);
//	            System.out.println("Item Sl No:::"+strStockInfo_p.split("\\^")[4]);
//	            System.out.println("Mode::::"+gblItemMCDtlVO.getStrMode());
	            
				gblItemMCDtlVO.setStrBatchNo(strStockInfo_p.split("\\^")[3]);
				gblItemMCDtlVO.setStrItemSlNo(strStockInfo_p.split("\\^")[4]);
				gblItemMCDtlVO.setStrSlNo("0");
				gblItemMCDtlVO.setStrHospCode(strHospitalCode_p);
									
				HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
						
				HemtItemMcDtlDAO.getPrevMantDtl(gblItemMCDtlVO, hisDAO);
						
				strPrevMantDtl = PerviousMaintenanceDetailsDATA.getPreviousMaintenanceDetails(gblItemMCDtlVO.getWrsMCDetails(),strMode);
				

			} catch (Exception ex) {
				throw new Exception("ItemMaintContractDtlsBO.getPrevMantDetails(String)-->"+ex.getMessage());
			}
			return strPrevMantDtl;

		}
		
		/**
		 * saveItemMaintenanceContactDetails() Method is used to cancel data form table [ hemt_item_mc_dtl ]
		 * @param hemtItemMcGlobalVO_p
		 * @return
		 */
		public static boolean saveItemMaintenanceContactDetails(HemtItemMcDtlVO hemtItemMcGlobalVO_p) throws Exception
		{		
			boolean retVal = false;
			HisDAO hisDAO_p = null;
			try 
			{	
				hisDAO_p = new HisDAO("BMED", "BmedGlobalBO");
				HemtItemMcDtlDAO.insert(hemtItemMcGlobalVO_p, hisDAO_p);
				hemtItemMcGlobalVO_p.setStrMsgType("0");
				hisDAO_p.fire(); // Here we Execute in Batch
				retVal = true;

			} 
			catch (Exception ex) 
			{			 
				throw new Exception("ItemMaintContractDtlsBO.saveItemMaintenanceContactDetails(String,String)-->"+ex.getMessage());
			}
			finally
			{

				if (hisDAO_p != null) {

					hisDAO_p.free();

					hisDAO_p = null;

				}

			}
			return retVal;

		}
		
		/**
		 * Method is used to get getRenewPageSelectedCombo
		 * @param strHospitalCode_p
		 * @param strItemBrandId_p
		 * @return
		 * @throws Exception
		 */
		
		public String getRenewPageSelectedComboOne(String strHospitalCode_p,String strInfoVal_p) throws Exception {

			String strRoutineFreqComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
			
			UnitMstVO gbltUnitMstVO;
			WebRowSet wrsUnitComboOptions;
			
			
			HisUtil hisUtil;
			try {
				/*	 
				  Value in strInfoVal_p Concated by ^ Symbol
				       0. HEMNUM_ROUTINE_VISIT
					   1. HEMNUM_BREAK_VISIT
			           2. HEMSTR_RESPONSE_TIME 
			           3. HEMNUM_ROUTINE_FREQ
			           4. Frequency Unit Name  
			           5. Response Time Unit Name 
			           6. HEMSTR_TENDER_NO 
			           7. HPURNUM_UPLOAD_NO 
			           8. HPURSTR_DOC_REF_NO  
			           9. HEMDT_TENDER_DATE,
			           10. HPURDT_DOC_REF_DATE  
			           11. HEMSTR_ORDER_NO  
			           12. HEMDT_ORDER_DATE 
			           13. GSTR_REMARKS 
			           14. HEMNUM_IS_RENEWED
			           15. HEMSTR_FREQ_UNIT 
			           16. HEMSTR_RES_TIME_UNIT
			           17. SL NO
				 */
				hisUtil = new HisUtil("BMED", "BmedGlobalBO");
				gbltUnitMstVO = new UnitMstVO();
				gbltUnitMstVO.setStrMode("1");
				gbltUnitMstVO.setStrHospitalCode(strHospitalCode_p);
				HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
				UnitMstDAO.getUnitMstCombo(gbltUnitMstVO, hisDAO);
				wrsUnitComboOptions  = gbltUnitMstVO.getWrsUnitComboOptions();
							
				if (wrsUnitComboOptions != null) 
				{				
					strRoutineFreqComboOptions = hisUtil.getOptionValue(wrsUnitComboOptions, strInfoVal_p.split("\\^")[4], "0^Select Value", true);
					
				}
				

			} 
			catch (Exception ex)
			{		
				//ex.printStackTrace();
				throw new Exception("ItemMaintContractDtlsBO.getSupplierComboOptions(String,String)-->"+ex.getMessage());
			}
			
			return strRoutineFreqComboOptions;

		}
		
		/**
		 * Method is used to get getRenewPageSelectedCombo
		 * @param strHospitalCode_p
		 * @param strItemBrandId_p
		 * @return
		 * @throws Exception
		 */
		
		public String getRenewPageSelectedComboTwo(String strHospitalCode_p,String strInfoVal_p) throws Exception {

			
			String strResTimeComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
			UnitMstVO gbltUnitMstVO;
			WebRowSet wrsUnitComboOptions;
			
			
			HisUtil hisUtil;
			try {
				/*	 
				  Value in strInfoVal_p Concated by ^ Symbol
				       0. HEMNUM_ROUTINE_VISIT
					   1. HEMNUM_BREAK_VISIT
			           2. HEMSTR_RESPONSE_TIME 
			           3. HEMNUM_ROUTINE_FREQ
			           4. Frequency Unit Name  
			           5. Response Time Unit Name 
			           6. HEMSTR_TENDER_NO 
			           7. HPURNUM_UPLOAD_NO 
			           8. HPURSTR_DOC_REF_NO  
			           9. HEMDT_TENDER_DATE,
			           10. HPURDT_DOC_REF_DATE  
			           11. HEMSTR_ORDER_NO  
			           12. HEMDT_ORDER_DATE 
			           13. GSTR_REMARKS 
			           14. HEMNUM_IS_RENEWED
			           15. HEMSTR_FREQ_UNIT 
			           16. HEMSTR_RES_TIME_UNIT
			           17. SL NO
				 */
				hisUtil = new HisUtil("BMED", "BmedGlobalBO");
				gbltUnitMstVO = new UnitMstVO();
				gbltUnitMstVO.setStrMode("1");
				gbltUnitMstVO.setStrHospitalCode(strHospitalCode_p);
				HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
				UnitMstDAO.getUnitMstCombo(gbltUnitMstVO, hisDAO);
				wrsUnitComboOptions  = gbltUnitMstVO.getWrsUnitComboOptions();
							
				if (wrsUnitComboOptions != null) 
				{				
					
					strResTimeComboOptions = hisUtil.getOptionValue(wrsUnitComboOptions, strInfoVal_p.split("\\^")[5], "0^Select Value", true);

				}
				

			} 
			catch (Exception ex)
			{		
				//ex.printStackTrace();
				throw new Exception("ItemMaintContractDtlsBO.getSupplierComboOptions(String,String)-->"+ex.getMessage());
			}
			
			return strResTimeComboOptions;

		}
		
		public String getStoreComboOptionsInit(String strHospitalCode_p,
				String strSeatid_p, String strDeptCode_p, StoreMstVO storeMstVO) throws Exception {

			String strStoreComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
			
			WebRowSet wrsStoreComboOptions;
			HisUtil hisUtil;
			try {
				hisUtil = new HisUtil("BMED", "BmedGlobalBO");
				
				storeMstVO.setStrHospitalCode(strHospitalCode_p);
//				storeMstVO.setStrMode("3");
				
				storeMstVO.setStrMode("5"); // FOR DWH_BMED
				storeMstVO.setStrSeatid(strSeatid_p);
				storeMstVO.setStrDeptCode(strDeptCode_p);

				/* Default Value */
				storeMstVO.setStrStoreId("0");
				storeMstVO.setStrStoretypeId("0");

				HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
				StoreMstDAO.getStoreCombo(storeMstVO, hisDAO);
				wrsStoreComboOptions = storeMstVO.getWrsStoreComboOptions();
				if (wrsStoreComboOptions != null) {
					strStoreComboOptions = hisUtil.getOptionValue(wrsStoreComboOptions, storeMstVO.getStrStoreId(), "0^Select Value", false);

				}
				

			} catch (Exception ex) {
				throw new Exception("ItemMaintContractDtlsBO.getStoreComboOptionsInit(String)-->"
						+ ex.getMessage());
			}
			return strStoreComboOptions;

		}

		/**
		 * to get data for view page
		 * 
		 * @param vo
		 * @throws Exception
		 */

		public void viewRecord(ItemMaintContractDtlsVO vo) {
			
			/*ItemInventoryTransDAO.getSuppliedByList(vo);
			ItemInventoryTransDAO.getWarrantyManufList(vo);
			ItemInventoryTransDAO.getCurrencyList(vo);
			ItemInventoryTransDAO.getStockStatusList(vo);
			ItemInventoryTransDAO.getEquipmentName(vo);
			ItemInventoryTransDAO.modifyRecord(vo);
			ItemInventoryTransDAO.unitRateNameCombo(vo);
			ItemInventoryTransDAO.unitInHandNameCombo(vo);
			ItemInventoryTransDAO.unitSaleNameCombo(vo);
			
			ItemInventoryTransDAO.getItemBrandName(vo); //Added on 31-July-2013 for Item Brand Name combo
			*/
			ItemMaintContractDtlsTransDAO.viewRecord(vo);
			
			
			if (vo.getStrMsgType().equals("1")) {

				String strErr = vo.getStrMsgString();

				vo.setStrMsgString("ItemMaintContractDtlsBO.viewRecord() --> " + strErr);
			}

		}
		/*public void getCommunicationMediumCmb(HemDeskVO hemDeskVO_p) throws Exception 
		{
			String strComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
			HisDAO hisDAO = null;
			WebRowSet wrsMainCommunicationComboOptions;
			HisUtil hisUtil;
			try 
			{
				                 hisUtil = new HisUtil("BMED", "BmedTransBO");				
				                  hisDAO = new HisDAO("BMED", "BmedTransBO");
				                 
				              // Calling DAO method here
				                 HemmsHEMMDeskDAO.getData1(hemDeskVO_p, hisDAO);
				                 wrsMainCommunicationComboOptions = hemDeskVO_p.getWrsMultiRowData();
				     			if (wrsMainCommunicationComboOptions != null) 
				     			{
				     				strComboOptions = hisUtil.getOptionValue(wrsMainCommunicationComboOptions, "0", "0^Select Value", false);
				     			}
				     			
				     			hemDeskVO_p.setStrCommunicationCmb(strComboOptions);               
				                
			      
			} 
			catch (Exception ex) 
			{
			//	ex.printStackTrace();
				throw new Exception("BmedGlobalBO.getCommunicationMediumCmb(HemDeskVO)-->"+ex.getMessage());
			}
		}	*/	
		
		/*public void getSparePartSerialNo(HemmsSparePartMgmtDeskVO vo)
		{
			HemmsHEMMDeskDAO.getSparePartSerialNo(vo);
			if (vo.getStrMsgType().equals("1")) 
			{
				vo.setStrMsgString("HemmDeskBO.getSparePartSerialNo() --> " + vo.getStrMsgString());
			}
		}
		public void getSparePartDtl(HemmsSparePartMgmtDeskVO vo)
		{
			HemmsHEMMDeskDAO.getSparePartDtl(vo);
			if (vo.getStrMsgType().equals("1")) 
			{
				vo.setStrMsgString("HemmDeskBO.getSparePartDtl() --> " + vo.getStrMsgString());
			}
		}*/

}
