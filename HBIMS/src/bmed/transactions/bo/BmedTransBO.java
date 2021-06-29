package bmed.transactions.bo;

import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import org.apache.struts.upload.FormFile;

import bmed.dao.ComplaintAttendDtlDAO;
import bmed.dao.ComplaintRequestDtlDAO;
import bmed.dao.ComplaintScheduleDtlDAO;
import bmed.dao.HemtComplaintApprovalDtlDAO;
import bmed.dao.HemtComplaintEscalationDtlDAO;
import bmed.dao.HemtComplaintStatusDtlDAO;
import bmed.dao.HemtItemMcDtlDAO;
import bmed.dao.HemtItemSparePartDtlDAO;
import bmed.dao.HemtReminderDtlDAO;
import bmed.dao.ItemBrandMstDAO;
import bmed.dao.ItemCurrStockDtlMstDAO;
import bmed.dao.PistEmpPersonalDtlDAO;
import bmed.dao.SemtCancelTypeMstDAO;
import bmed.dao.SemtConfigPropertyMstDAO;
import bmed.dao.SemtEscalationLevelTypeMstDAO;
import bmed.dao.SemtMaintTypeMstDAO;
import bmed.dao.ServiceEnggMstDAO;
import bmed.dao.TaskMstDAO;
import bmed.dao.TestDtlDAO;
import bmed.dao.TestParameterDtlDAO;
import bmed.dao.UnitMstDAO;
import bmed.dao.WarrantyDtlDAO;
import bmed.global.controller.data.ComplaintDetailsDATA;
import bmed.global.controller.data.PerviousMaintenanceDetailsDATA;
import bmed.global.controller.data.PreviousWarrantyDATA;
import bmed.global.controller.data.StockDetailsDATA;
import bmed.transactions.controller.data.HemComplaintApprovalDeskDATA;
import bmed.transactions.controller.data.HemDeskDATA;
import bmed.vo.ComplaintAttendDtlVO;
import bmed.vo.ComplaintRequestDtlVO;
import bmed.vo.ComplaintScheduleDtlVO;
import bmed.vo.HemComplaintApprovalDeskVO;
import bmed.vo.HemDeskVO;
import bmed.vo.HemtComplaintApprovalDtlVO;
import bmed.vo.HemtComplaintEscalationDtlVO;
import bmed.vo.HemtComplaintStatusDtlVO;
import bmed.vo.HemtItemMcDtlVO;
import bmed.vo.HemtItemSparePartDtlVO;
import bmed.vo.HemtReminderDtlVO;
import bmed.vo.ItemBrandMstVO;
import bmed.vo.ItemCurrStockDtlMstVO;
import bmed.vo.SemtCancelTypeMstVO;
import bmed.vo.SemtConfigPropertyMstVO;
import bmed.vo.SemtEscalationLevelTypeMstVO;
import bmed.vo.SemtMaintTypeMstVO;
import bmed.vo.ServiceEnggMstVO;
import bmed.vo.TaskMstVO;
import bmed.vo.TestDtlVO;
import bmed.vo.UnitMstVO;
import bmed.vo.WarrantyDtlVO;



public class BmedTransBO {

	/*
	 * ########################################################################
	 * -- Aritra Start --
	 * ########################################################################
	 */

	public void getComplaintRequestData(
			ComplaintRequestDtlVO complaintRequestDtlVO_p) throws Exception {

		HisDAO hisDao = null;

		try {
			/* Transaction Start */
			hisDao = new HisDAO("bmed", "BmedTransBO");

			complaintRequestDtlVO_p.setStrMode("1");
			ComplaintRequestDtlDAO.getData(complaintRequestDtlVO_p, hisDao);

		} catch (Exception e) {
			throw new Exception(
					"BmedTransBO.getComplaintRequestData(ComplaintRequestDtlVO)-->"
							+ e.getMessage());
		} finally {
			/* Closing Transaction */
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}

	}

	/*public void getWarrantyDtl(WarrantyDtlVO warrantyDtlVO_p) throws Exception {

		HisDAO hisDao = null;

		try {
			 Transaction Start 
			hisDao = new HisDAO("bmed", "BmedTransBO");

			warrantyDtlVO_p.setStrMode("1");
			WarrantyDtlDAO.getData(warrantyDtlVO_p, hisDao);

		} catch (Exception e) {
			throw new Exception(
					"BmedTransBO.getComplaintRequestData(ComplaintRequestDtlVO)-->"
							+ e.getMessage());
		} finally {
			 Closing Transaction 
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}

	}

	*/
	// used in complaint cancel, complaint schedule. 
	public void initializeComplaintActions(
			ComplaintRequestDtlVO complaintRequestDtlVO_p,
			WarrantyDtlVO warrantyDtlVO_p, HemtItemMcDtlVO hemtItemMcDtlVO_p,
			ComplaintScheduleDtlVO complaintScheduleDtlVO_p,
			ComplaintAttendDtlVO complaintAttendDtlVO_p,
			ServiceEnggMstVO serviceEnggMstVO_p,
			boolean fServiceEngDetailsRequired,
			HemtItemSparePartDtlVO itemSparePartDtlVO_p,
			boolean fSparePartDtlRequired_p,TaskMstVO taskMstVO_p,
			boolean fTaskMstRequired_p) throws Exception {

		HisDAO hisDao = null;
		String strWarrantySlNo = null;
		String strMcSlNo = null;

		try {
			/* Transaction Start */
			hisDao = new HisDAO("bmed", "BmedTransBO");

			complaintRequestDtlVO_p.setStrMode("3");
			ComplaintRequestDtlDAO.getData(complaintRequestDtlVO_p, hisDao);

			if (complaintRequestDtlVO_p.getWrsData() != null) {
				if (complaintRequestDtlVO_p.getWrsData().next()) {
					strWarrantySlNo = complaintRequestDtlVO_p.getWrsData()
							.getString("HEMNUM_WARRANTY_SLNO");
					strMcSlNo = complaintRequestDtlVO_p.getWrsData().getString(
							"HEMNUM_MC_SLNO");
				}
				complaintRequestDtlVO_p.getWrsData().beforeFirst();
				ComplaintRequestDtlDAO
						.getDataSingleRow(complaintRequestDtlVO_p);
			}
			/*
			 * A complaint can be registered against warranty or maintenance
			 * contract, not both. Either one of this is null, not both.
			 */
			if (strWarrantySlNo != null) {
				warrantyDtlVO_p.setStrSlNo(strWarrantySlNo);

				/*
				 * Mode '4' is to get data in the basis of strWarrantySlNo and
				 * hospital code.
				 */
				warrantyDtlVO_p.setStrMode("4");

				WarrantyDtlDAO.getData(warrantyDtlVO_p, hisDao);

			} else if (strMcSlNo != null) {
				hemtItemMcDtlVO_p.setStrSlNo(strMcSlNo);

				/*
				 * Mode 'x' is to get data in the basis of strWarrantySlNo and
				 * hospital code.
				 */
				hemtItemMcDtlVO_p.setStrMode("4");

				if (hemtItemMcDtlVO_p.getStrItemId() == null) {
					hemtItemMcDtlVO_p.setStrItemId("");
				}
				if (hemtItemMcDtlVO_p.getStrItemSlNo() == null) {
					hemtItemMcDtlVO_p.setStrItemId("");
				}

				HemtItemMcDtlDAO.getPrevMantDtl(hemtItemMcDtlVO_p, hisDao);
			}

			complaintScheduleDtlVO_p.setStrMode("2");
			ComplaintScheduleDtlDAO.getData(complaintScheduleDtlVO_p, hisDao);

			complaintAttendDtlVO_p.setStrMode("2");
			ComplaintAttendDtlDAO.getData(complaintAttendDtlVO_p, hisDao);

			/* If Service Engineer Details is Required */
			if (fServiceEngDetailsRequired) {
				serviceEnggMstVO_p.setStrMode("3");
				serviceEnggMstVO_p.setStrEnggItemTypeId(complaintRequestDtlVO_p
						.getStrEnggItemTypeId());
				serviceEnggMstVO_p
						.setStrEnggItemSubTypeId(complaintRequestDtlVO_p
								.getStrEnggItemSubTypeId());
				ServiceEnggMstDAO.getData(serviceEnggMstVO_p, hisDao);
			}

			/* If Spare Parts Details is Required */
			if (fSparePartDtlRequired_p) {
				itemSparePartDtlVO_p.setStrMode("4");
				itemSparePartDtlVO_p.setStrStoreId(complaintRequestDtlVO_p
						.getStrStoreId());
				itemSparePartDtlVO_p.setStrItemId(complaintRequestDtlVO_p
						.getStrItemId());
				itemSparePartDtlVO_p.setStrItemSlNo(complaintRequestDtlVO_p
						.getStrSerialNo());
				HemtItemSparePartDtlDAO.getData(itemSparePartDtlVO_p, hisDao);
			}
			
			/* If Task List Details is Required */
			if (fTaskMstRequired_p) {
				taskMstVO_p.setStrMode("1");
				taskMstVO_p.setStrEnggItemTypeId(complaintRequestDtlVO_p.getStrEnggItemTypeId());
				taskMstVO_p.setStrEnggItemSubTypeId(complaintRequestDtlVO_p.getStrEnggItemSubTypeId());
				TaskMstDAO.getData(taskMstVO_p, hisDao);
			}

		} catch (Exception e) {
			throw new Exception(
					"BmedTransBO.getComplaintRequestData(ComplaintRequestDtlVO)-->"
							+ e.getMessage());
		} finally {
			/* Closing Transaction */
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}

	}
	
	
	public void sparePartsDetailsData(HemtItemSparePartDtlVO itemSparePartDtlVO_p)  throws Exception {
		
		HisDAO hisDao = null;
		
		// If Spare Parts Details is Required 
		try{
		if (true) {
			
			// Transaction Start 
			hisDao = new HisDAO("bmed", "BmedTransBO");

			itemSparePartDtlVO_p.setStrMode("4");
			
			HemtItemSparePartDtlDAO.getData(itemSparePartDtlVO_p, hisDao);
		}
		}
		catch(Exception e){
			e.printStackTrace();
			
			throw new Exception(
					"BmedTransBO.getComplaintRequestData(ComplaintRequestDtlVO)-->"
							+ e.getMessage());
		}
		
	}/*
	
	
public void tasklDetailsData(TaskMstVO taskMstVO_p,ComplaintRequestDtlVO complaintRequestDtlVO_p)  throws Exception {
		
		HisDAO hisDao = null;
		
		 If Spare Parts Details is Required 
		try{
		if (true) {
			
			 Transaction Start 
			hisDao = new HisDAO("bmed", "BmedTransBO");

			taskMstVO_p.setStrMode("1");
			taskMstVO_p.setStrEnggItemTypeId(complaintRequestDtlVO_p.getStrEnggItemTypeId());
			taskMstVO_p.setStrEnggItemSubTypeId(complaintRequestDtlVO_p.getStrEnggItemSubTypeId());
			TaskMstDAO.getData(taskMstVO_p, hisDao);
		}
		}
		catch(Exception e){
			e.printStackTrace();
			
			throw new Exception(
					"BmedTransBO.getComplaintRequestData(ComplaintRequestDtlVO)-->"
							+ e.getMessage());
		}
		
	}

	
	*/
	
	public void saveComplaintCancelProcess(
			ComplaintRequestDtlVO complaintRequestDtlVO_p,
			HemtComplaintApprovalDtlVO complaintApprovalDtlVO_p,
			HemtComplaintStatusDtlVO complaintStatusDtlVO_p) throws Exception {
		HisDAO hisDao = null;

		try {
			 //Transaction Start 
			hisDao = new HisDAO("bmed", "BmedTransBO");

			ComplaintRequestDtlDAO.save(complaintRequestDtlVO_p, hisDao);
			HemtComplaintApprovalDtlDAO.save(complaintApprovalDtlVO_p, hisDao);
			HemtComplaintStatusDtlDAO.save(complaintStatusDtlVO_p, hisDao);

			hisDao.fire();

		} catch (Exception e) {
			throw new Exception(
					"BmedTransBO.saveComplaintCancelProcess(ComplaintRequestDtlVO,HemtComplaintApprovalDtlVO,HemtComplaintStatusDtlVO)-->"
							+ e.getMessage());
		} finally {
			 //Closing Transaction 
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}

	}

	 //This Method is used in Schedule and Hem Schedule Process 
	public void saveComplaintScheduleProcess(
			ComplaintRequestDtlVO complaintRequestDtlVO_p,
			ComplaintScheduleDtlVO complaintScheduleDtlVO_p,
			HemtComplaintStatusDtlVO complaintStatusDtlVO_p) throws Exception {
		HisDAO hisDao = null;

		try {
			 //Transaction Start 
			hisDao = new HisDAO("bmed", "BmedTransBO");

			
			/* * Update hemt_complaint_request_dtl table Setting HEMNUM_HEM_FLAG,
			 * HEMNUM_MAIN_STATUS='2',HEMNUM_SUB_STATUS='20' and
			 * GSTR_STATUS_REMARKS*/
			 
			complaintRequestDtlVO_p.setStrMode("3");
			ComplaintRequestDtlDAO.save(complaintRequestDtlVO_p, hisDao);

			
			/* * Update HEMT_COMPLAINT_SCHEDULE_DTL, set HEMT_STATUS=2 for all old
			 * schedules corresponding to this request. HEMT_STATUS=2 means
			 * closed. This value is hard coded at procedure level.
			 */
			complaintScheduleDtlVO_p.setStrMode("3");
			ComplaintScheduleDtlDAO.save(complaintScheduleDtlVO_p, hisDao);

			/*
			 * Insert HEMT_COMPLAINT_SCHEDULE_DTL HEMT_STATUS=1 is hard coded at
			 * procedure level. HEMT_STATUS=1 means new.
			 */

			// Generating New Schedule Id 
			complaintScheduleDtlVO_p.setStrMode("1");
			ComplaintScheduleDtlDAO.setNewScheduleId(complaintScheduleDtlVO_p,
					hisDao);

			// Insert 
			complaintScheduleDtlVO_p.setStrMode("1");
			ComplaintScheduleDtlDAO.save(complaintScheduleDtlVO_p, hisDao);

			
			// * Insert HEMT_COMPLAINT_STATUS_DTL
			 
			complaintStatusDtlVO_p.setStrMode("1");
			complaintStatusDtlVO_p.setStrTransId(complaintScheduleDtlVO_p
					.getStrScheduleId());
			complaintStatusDtlVO_p.setStrActionId("2");
			complaintStatusDtlVO_p.setStrIsValid("1");
			HemtComplaintStatusDtlDAO.save(complaintStatusDtlVO_p, hisDao);

			hisDao.fire();

		} catch (Exception e) {
			 e.printStackTrace();
			throw new Exception(
					"BmedTransBO.saveComplaintScheduleProcess(ComplaintRequestDtlVO,ComplaintScheduleDtlVO,HemtComplaintStatusDtlVO)-->"
							+ e.getMessage());
		} finally {
			 //Closing Transaction 
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}

	}

	public void getServiceEnggData(ServiceEnggMstVO serviceEnggMstVO_p)
			throws Exception {

		HisDAO hisDao = null;

		try {
			// Transaction Start 
			hisDao = new HisDAO("bmed", "BmedTransBO");

			ServiceEnggMstDAO.getData(serviceEnggMstVO_p, hisDao);

		} catch (Exception e) {
			throw new Exception(
					"BmedTransBO.getServiceEnggData(ServiceEnggMstVO)-->"
							+ e.getMessage());
		} finally {
			// Closing Transaction 
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}

	}

	 //This Method is used in Schedule and Hem Schedule Process 
	public void saveAttendDetailsProcess(
			ComplaintAttendDtlVO complaintAttendDtlVO_p,
			ComplaintRequestDtlVO complaintRequestDtlVO_p,
			HemtComplaintStatusDtlVO complaintStatusDtlVO_p,
			HemtItemSparePartDtlVO hemtItemSparePartDtlVO_p,
			HemtItemSparePartDtlVO oldHemtItemSparePartDtlVO_p)
			throws Exception {
		HisDAO hisDao = null;
		final int nStatus = Integer.parseInt(complaintAttendDtlVO_p
				.getStrStatus());

		try {
			 //Transaction Start 
			hisDao = new HisDAO("bmed", "BmedTransBO");

			// Generating New Schedule Id 
			complaintAttendDtlVO_p.setStrMode("1");
			ComplaintAttendDtlDAO.setNewAttendId(complaintAttendDtlVO_p, hisDao);

			// Update Old Record With HEMT_STATUS=2 in Attend Detail Table 
			complaintAttendDtlVO_p.setStrMode("2");
			ComplaintAttendDtlDAO.save(complaintAttendDtlVO_p, hisDao);

			// Insert New Row In Attend Detail Table 
			complaintAttendDtlVO_p.setStrMode("1");
			ComplaintAttendDtlDAO.save(complaintAttendDtlVO_p, hisDao);

			// Update Complaint Request Detail 
			complaintRequestDtlVO_p.setStrMode("5");
			ComplaintRequestDtlDAO.save(complaintRequestDtlVO_p, hisDao);

			// Insert into Status Details 
			complaintStatusDtlVO_p.setStrMode("1");
			complaintStatusDtlVO_p.setStrTransId(complaintAttendDtlVO_p.getStrAttendId());
			complaintStatusDtlVO_p.setStrIsValid("1");
			HemtComplaintStatusDtlDAO.save(complaintStatusDtlVO_p, hisDao);

			// Insert Into Spare Part Details 
			if (nStatus != 0) {

				hemtItemSparePartDtlVO_p.setStrIsvalid("1");
				if (nStatus == 1) {
					
					 //* New Spare Part
					 

					// Inset 
					hemtItemSparePartDtlVO_p.setStrMode("1");
					HemtItemSparePartDtlDAO.save(hemtItemSparePartDtlVO_p,
							hisDao);
				} else if (nStatus == 2) {
					
					// * Replace Spare Part
					 

					// Update Old 
					oldHemtItemSparePartDtlVO_p.setStrMode("3");
					HemtItemSparePartDtlDAO.save(oldHemtItemSparePartDtlVO_p,
							hisDao);

					// Inset New 
					hemtItemSparePartDtlVO_p.setStrMode("1");
				//	 New Element should always be active 
					hemtItemSparePartDtlVO_p.setStrStatus("1");
					hemtItemSparePartDtlVO_p.setStrSpareId(oldHemtItemSparePartDtlVO_p.getStrSpareId());
					HemtItemSparePartDtlDAO.save(hemtItemSparePartDtlVO_p,
							hisDao);

				} else if (nStatus == 3) {
					
					// * Repair Spare Part
					 

					// Update Old 
					oldHemtItemSparePartDtlVO_p.setStrMode("3");
					HemtItemSparePartDtlDAO.save(oldHemtItemSparePartDtlVO_p,
							hisDao);

				}

			}

			// Firing transaction 
			hisDao.fire();

		} catch (Exception e) {
			throw new Exception(
					"BmedTransBO.saveAttendDetailsProcess(ComplaintAttendDtlVO,ComplaintRequestDtlVO,HemtComplaintStatusDtlVO,HemtItemSparePartDtlVO,HemtItemSparePartDtlVO)-->"
							+ e.getMessage());
		} finally {
			// Closing Transaction 
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}

	}
	
	/*
	 * This method is used to close a complaint.
	 */
	public void saveComplaintCloseProcess(
			ComplaintRequestDtlVO complaintRequestDtlVO_p) throws Exception {
		HisDAO hisDao = null;

		try {
			/* Transaction Start */
			hisDao = new HisDAO("bmed", "BmedTransBO");

			complaintRequestDtlVO_p.setStrMode("6");
			ComplaintRequestDtlDAO.save(complaintRequestDtlVO_p, hisDao);
			
			hisDao.fire();

		} catch (Exception e) {
			throw new Exception(
					"BmedTransBO.saveComplaintCloseProcess(ComplaintRequestDtlVO)-->"
							+ e.getMessage());
		} finally {
			/* Closing Transaction */
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}

	}
	
	/**
	 * This method will give Complaint Request Detail Data according to  HospCode 
	 * 
	 * @param complaintRequestDtlVO_p	the ComplaintRequestDtlVO
	 * 
	 * @throws Exception
	 */
	public void getComplaintRequestDtl(
			ComplaintRequestDtlVO complaintRequestDtlVO_p) throws Exception {

		HisDAO hisDao = null;
		

		try {
			/* Transaction Start */
			hisDao = new HisDAO("bmed", "BmedTransBO");

			complaintRequestDtlVO_p.setStrMode("3");
			ComplaintRequestDtlDAO.getData(complaintRequestDtlVO_p, hisDao);

			if (complaintRequestDtlVO_p.getWrsData() != null) {
				complaintRequestDtlVO_p.getWrsData().beforeFirst();
				ComplaintRequestDtlDAO
						.getDataSingleRow(complaintRequestDtlVO_p);
			}

		} catch (Exception e) {
			throw new Exception(
					"BmedTransBO.getComplaintRequestDtl(ComplaintRequestDtlVO)-->"
							+ e.getMessage());
		} finally {
			/* Closing Transaction */
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}

	}
	
	/**
	 * This method will give Complaint Request Detail Data according to  HospCode 
	 * 
	 * @param complaintRequestDtlVO_p	the ComplaintRequestDtlVO
	 * 
	 * @throws Exception
	 */
	public void getComplaintRequestDtlForReport(
			ComplaintRequestDtlVO complaintRequestDtlVO_p) throws Exception {

		HisDAO hisDao = null;
		

		try {
			/* Transaction Start */
			hisDao = new HisDAO("bmed", "BmedTransBO");
			
			ComplaintRequestDtlDAO.getData(complaintRequestDtlVO_p, hisDao);

		} 
		catch (Exception e) 
		{
			throw new Exception("BmedTransBO.getComplaintRequestDtlForReport(ComplaintRequestDtlVO)-->"	+ e.getMessage());
		}
		finally 
		{
			/* Closing Transaction */
			if (hisDao != null) 
			{
				hisDao.free();
				hisDao = null;
			}
		}
	}
	
	
	/*
	 * This method will give Complaint Reminder Detail Data according to reqId and HospCode. 
	 */
	public void getComplaintReminderDtl(
			HemtReminderDtlVO hemtReminderDtlVO_p) throws Exception {

		HisDAO hisDao = null;
		

		try {
			 //Transaction Start 
			hisDao = new HisDAO("bmed", "BmedTransBO");

			hemtReminderDtlVO_p.setStrMode("2");
			HemtReminderDtlDAO.getData(hemtReminderDtlVO_p, hisDao);

		} catch (Exception e) {
			throw new Exception(
					"BmedTransBO.getComplaintReminderDtl(HemtReminderDtlVO)-->"
							+ e.getMessage());
		} finally {
			// Closing Transaction 
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}

	}

	/*
	 * ########################################################################
	 * ---------- Aritra End -------------
	 * ########################################################################
	 */


	/****************************************************************************************************/
	/* Method Used in Vivek Aggarwal Trnasaction [Start ] */
	/****************************************************************************************************/
	
	
	/**
	 * To Get Property Value from semtConfigPropertyMst
	 * 
	 * @param	semtConfigPropertyMstVO_p	the SemtConfigPropertyMstVO
	 * 
	 */
	public void getPropertyValue(SemtConfigPropertyMstVO semtConfigPropertyMstVO_p) throws Exception 
	{
		HisDAO hisDao = null;

		try {
			// Transaction Start 
			hisDao = new HisDAO("bmed", "BmedTransBO");


			SemtConfigPropertyMstDAO.getData(semtConfigPropertyMstVO_p, hisDao);

		} catch (Exception e) {
			throw new Exception(
					"BmedTransBO.getPropertyValue(semtConfigPropertyMstVO_p)-->"
							+ e.getMessage());
		} finally {
			// Closing Transaction 
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}
	}
	
	/**
	 * To Get Designation based on EmpId
	 * 
	 * @param	strTemp_p	the String
	 * @param 	strHospitalCode_p String
	 * 
	 * @return	strRetVal the String
	 * 
	 */
	public String getDesignation(String strTemp_p, String strHospitalCode_p) throws Exception 
	{
		String strRetVal = null;
		HisDAO hisDAO_p = null;
		//
		int funcIndex = 0;
		//
		try {
			hisDAO_p = new HisDAO("BMED", "BmedTransBO");
			funcIndex = hisDAO_p.setFunction("{? = call bmed_function.get_designation(?,?,?::numeric)}");
		
			hisDAO_p.setFuncInValue(funcIndex, 2, "1");
		
			hisDAO_p.setFuncInValue(funcIndex, 3, strTemp_p);
			
			hisDAO_p.setFuncInValue(funcIndex, 4, strHospitalCode_p);
		
			hisDAO_p.setFuncOutValue(funcIndex, 1);
		
			// Execute Function
		
			hisDAO_p.executeFunction(funcIndex);
			
			hisDAO_p.getFuncString(funcIndex);
			
			strRetVal = hisDAO_p.getFuncString(funcIndex);
			}
		catch (Exception ex)
		
		
		{
			
			ex.printStackTrace();
			throw new Exception("BmedTransBO.getDesignation(String,String)-->" + ex.getMessage());
		} 
		finally 
		{
			if (hisDAO_p != null) 
			{
				hisDAO_p.free();
				hisDAO_p = null;
			}
		}
		return strRetVal;
}

	/**
	 * To get Item Name on the basis of Dept code
	 * 
	 * @param 	strHospitalCode_p String
 	 * @param	strDepartmentId_p	the String
	 * 
	 * @return	strItemBrandComboOptions the String
	 */
	
	public String getItemBrandComboOptionsOnDepartment(String strHospitalCode_p, String strDepartmentId_p)	throws Exception 
	{
		String strItemBrandComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		ItemBrandMstVO gbltItemBrandMstVO;
		WebRowSet wrsItemBrandComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedTransBO");
			gbltItemBrandMstVO = new ItemBrandMstVO();

			gbltItemBrandMstVO.setStrMode("6");
			gbltItemBrandMstVO.setStrItemCatNo("0");
			gbltItemBrandMstVO.setStrStoreId("0");
			gbltItemBrandMstVO.setStrDepartmentId(strDepartmentId_p);
			gbltItemBrandMstVO.setStrHospitalCode(strHospitalCode_p);

			HisDAO hisDAO = new HisDAO("BMED", "BmedTransBO");
			ItemBrandMstDAO.getItemBrandCombo(gbltItemBrandMstVO, hisDAO);
			wrsItemBrandComboOptions = gbltItemBrandMstVO
					.getWrsItemBrandComboOptions();
			if (wrsItemBrandComboOptions != null) {
				strItemBrandComboOptions = hisUtil.getOptionValue(
						wrsItemBrandComboOptions, "0", "0^Select Value", false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedTransBO.getItemBrandComboOptionsOnDepartment(String strHospitalCode_p, String strDepartmentId_p)-->"
							+ ex.getMessage());
		}
		return strItemBrandComboOptions;

	}
	
	
	/**
	 * To get Previous Complaint Detail For Item Complaint Register
	 * 
	 * @param 	strHospitalCode_p String
 	 * @param	complaintRequestDtlVO_p	the ComplaintRequestDtlVO
	 * 
	 * @return	strPrevCompDtl the String
	 */
	public String getPrevCompDtlForItemComplaintRegister(ComplaintRequestDtlVO complaintRequestDtlVO_p,String strHospCode_p)throws Exception 
	{
		HisDAO hisDao = null;

		String strPrevCompDtl;
		try {
			
			/* Transaction Start */
			hisDao = new HisDAO("bmed", "BmedTransBO");

			ComplaintRequestDtlDAO.getData(complaintRequestDtlVO_p, hisDao);

			strPrevCompDtl = ComplaintDetailsDATA.getComplaintsDetails(	complaintRequestDtlVO_p.getWrsData(), "1");
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new Exception(
					"BmedTransBO.getPrevCompDtl(strHospCode_p,strStockInfo_p)-->"
							+ e.getMessage());
		} finally {
			/* Closing Transaction */
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}
		return strPrevCompDtl;
	}

	/**
	 * To get Warranty Detail For Item Complaint Register
	 * 
 	 * @param	warrantyDtlVO_p	the WarrantyDtlVO
	 * 
	 */
	public void getWarrantyDetailForItemComplaintRegister(WarrantyDtlVO warrantyDtlVO_p) throws Exception 
	{

		HisDAO hisDao = null;
	
		
		try {
			
			// Transaction Start 
			hisDao = new HisDAO("bmed", "BmedTransBO");
			
			WarrantyDtlDAO.getData(warrantyDtlVO_p, hisDao);

		} catch (Exception e) {
			throw new Exception(
					"BmedTransBO.getWarrantyDtl(warrantyDtlVO_p)-->"
							+ e.getMessage());
		} finally {
			 //Closing Transaction 
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}

	}

	/**
	 * To get Maintenance Contract Details For Item Complaint Register
	 * 
 	 * @param	hemtItemMcDtlVO_p	the HemtItemMcDtlVO
	 * 
	 */
	public void getMaintenanceContractDetails(HemtItemMcDtlVO hemtItemMcDtlVO_p)	throws Exception 
	{
		HisDAO hisDao = null;
		try {
			
			
			 //Transaction Start 
			hisDao = new HisDAO("bmed", "BmedTransBO");

			HemtItemMcDtlDAO.getPrevMantDtl(hemtItemMcDtlVO_p, hisDao);

		} catch (Exception e) {
			throw new Exception(
					"BmedTransBO.getMaintenanceContractDetails(hemtItemMcDtlVO_p)-->"
							+ e.getMessage());
		} finally {
			 //Closing Transaction 
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}

	}
	
	/**
	 * To save Data For Item Complaint Register
	 * 
 	 * @param	complaintRequestDtlVO_p	the ComplaintRequestDtlVO
 	 * @param  	hemtComplaintStatusDtlVO_p the HemtComplaintStatusDtlVO
 	 * @param	hemtComplaintApprovalDtlVO_p the HemtComplaintApprovalDtlVO
	 * 
	 */
	public static void saveDataForItemComplaintRegister(ComplaintRequestDtlVO complaintRequestDtlVO_p,HemtComplaintStatusDtlVO hemtComplaintStatusDtlVO_p,HemtComplaintApprovalDtlVO hemtComplaintApprovalDtlVO_p)	throws Exception 
	{
		String strRetVal = null;
		HisDAO hisDAO_p = null;
		//
		int funcIndex = 0;
		//
		try {
			hisDAO_p = new HisDAO("BMED", "BmedTransBO");
			funcIndex = hisDAO_p.setFunction("{? = call bmed_function.gen_req_id(?,?)}");

			hisDAO_p.setFuncInValue(funcIndex, 2, "1");
			hisDAO_p.setFuncInValue(funcIndex, 3,complaintRequestDtlVO_p.getStrHospitalCode());
			hisDAO_p.setFuncOutValue(funcIndex, 1);

			// Execute Function
			hisDAO_p.executeFunction(funcIndex);
			strRetVal = hisDAO_p.getFuncString(funcIndex);

			complaintRequestDtlVO_p.setStrReqId(strRetVal);
			hemtComplaintStatusDtlVO_p.setStrReqId(strRetVal);
			hemtComplaintStatusDtlVO_p.setStrTransId(strRetVal);
			hemtComplaintApprovalDtlVO_p.setStrReqId(strRetVal);

			ComplaintRequestDtlDAO.save(complaintRequestDtlVO_p, hisDAO_p);
			HemtComplaintStatusDtlDAO.save(hemtComplaintStatusDtlVO_p, hisDAO_p);
			HemtComplaintApprovalDtlDAO.save(hemtComplaintApprovalDtlVO_p,hisDAO_p);

			synchronized (hisDAO_p) {
				hisDAO_p.fire(); // Here we Execute in Batch
				complaintRequestDtlVO_p.setStrMsgType("0");
			}

		}
		catch (Exception ex)
		{			
			complaintRequestDtlVO_p.setStrMsgType("1");
			throw new Exception("BmedTransBO.saveDataForItemComplaintRegister(String,String)-->"+ ex.getMessage());
		} finally {

			if (hisDAO_p != null) {

				hisDAO_p.free();

				hisDAO_p = null;

			}

		}
	}

	
	 
		public static void saveDataForComplaintLogOffline(ComplaintRequestDtlVO complaintRequestDtlVO_p,HemtComplaintStatusDtlVO hemtComplaintStatusDtlVO_p,HemtComplaintApprovalDtlVO hemtComplaintApprovalDtlVO_p,ComplaintAttendDtlVO complaintAttendDtlVO_p 
				,HemtItemSparePartDtlVO hemtItemSparePartDtlVO_p,HemtItemSparePartDtlVO oldHemtItemSparePartDtlVO_p )	throws Exception 
		{
			String strRetVal = null;
			String strRetVal1 = null;
			HisDAO hisDAO_p = null;
			//
			int funcIndex = 0;
			int funcIndex1 = 0;
			//
			
			final int nStatus = Integer.parseInt(complaintAttendDtlVO_p
					.getStrStatus());
			try {
				hisDAO_p = new HisDAO("BMED", "BmedTransBO");
				
				
				
				funcIndex = hisDAO_p
						.setFunction("{? = call bmed_function.gen_req_id(?,?)}");

				hisDAO_p.setFuncInValue(funcIndex, 2, "1");

				hisDAO_p.setFuncInValue(funcIndex, 3,complaintRequestDtlVO_p.getStrHospitalCode());

				hisDAO_p.setFuncOutValue(funcIndex, 1);

				// Execute Function
				hisDAO_p.executeFunction(funcIndex);
				strRetVal = hisDAO_p.getFuncString(funcIndex);
				
				funcIndex1  =hisDAO_p.setFunction("{? = call bmed_function.gen_attended_id(?,?,?)}");
				
				hisDAO_p.setFuncInValue(funcIndex1, 2, "1");

				hisDAO_p.setFuncInValue(funcIndex1, 3,complaintAttendDtlVO_p.getStrHospitalCode());

				hisDAO_p.setFuncInValue(funcIndex1, 4,strRetVal);
				
				hisDAO_p.setFuncOutValue(funcIndex1, 3);
				// Execute Function
				hisDAO_p.executeFuncForNumeric(funcIndex1);
				strRetVal1 = hisDAO_p.getFuncNumeric(funcIndex1);
				complaintAttendDtlVO_p.setStrAttendId(strRetVal1);
				
				
				complaintRequestDtlVO_p.setStrReqId(strRetVal);
				hemtComplaintStatusDtlVO_p.setStrReqId(strRetVal);
				hemtComplaintStatusDtlVO_p.setStrTransId(strRetVal);
				hemtComplaintApprovalDtlVO_p.setStrReqId(strRetVal);
				complaintAttendDtlVO_p.setStrReqId(strRetVal);
				ComplaintRequestDtlDAO.save(complaintRequestDtlVO_p, hisDAO_p);
				HemtComplaintStatusDtlDAO.save(hemtComplaintStatusDtlVO_p, hisDAO_p);
				complaintAttendDtlVO_p.setStrMode("1");
				ComplaintAttendDtlDAO.save(complaintAttendDtlVO_p, hisDAO_p);
				//HemtComplaintApprovalDtlDAO.save(hemtComplaintApprovalDtlVO_p,hisDAO_p);
				
				// Insert Into Spare Part Details 
				if (nStatus != 0) {

					hemtItemSparePartDtlVO_p.setStrIsvalid("1");
					if (nStatus == 1) {
						
						 //* New Spare Part
						 

						// Inset 
						hemtItemSparePartDtlVO_p.setStrMode("1");
						HemtItemSparePartDtlDAO.save(hemtItemSparePartDtlVO_p,
								hisDAO_p);
					} else if (nStatus == 2) {
						
						// * Replace Spare Part
						 

						// Update Old 
						oldHemtItemSparePartDtlVO_p.setStrMode("3");
						HemtItemSparePartDtlDAO.save(oldHemtItemSparePartDtlVO_p,
								hisDAO_p);

						// Inset New 
						hemtItemSparePartDtlVO_p.setStrMode("1");
						// New Element should always be active 
						hemtItemSparePartDtlVO_p.setStrStatus("1");
						hemtItemSparePartDtlVO_p.setStrSpareId(oldHemtItemSparePartDtlVO_p.getStrSpareId());
						HemtItemSparePartDtlDAO.save(hemtItemSparePartDtlVO_p,
								hisDAO_p);

					} else if (nStatus == 3) {
						
						// * Repair Spare Part
						 

						// Update Old 
						oldHemtItemSparePartDtlVO_p.setStrMode("3");
						HemtItemSparePartDtlDAO.save(oldHemtItemSparePartDtlVO_p,
								hisDAO_p);

					}
				
				
				}

				synchronized (hisDAO_p) {
					hisDAO_p.fire(); // Here we Execute in Batch
				}

			} catch (Exception ex) {
				throw new Exception(
						"BmedTransBO.saveDataForItemComplaintRegister(String,String)-->"
								+ ex.getMessage());
			} finally {

				if (hisDAO_p != null) {

					hisDAO_p.free();

					hisDAO_p = null;

				}

			}
		}
	
		
		/**
		 * To save Data For 	Equipment Inspection Test Details
		 * 
	 	 * @param	TestDtlVO_p	the TestDtlVO
	 	 * 
		 */
		public static void saveDataForEquipmentInspectionTestDetails(TestDtlVO testDtlVO_p, WarrantyDtlVO warrantyDtlVO_p)	throws Exception 
		{
			String strRetVal = null;
			String strRetVal1 = null;
			HisDAO hisDAO_p = null;
			//
			int funcIndex = 0;
			int funcIndex1 = 0;
			//
			try {
				hisDAO_p = new HisDAO("BMED", "BmedTransBO");
				funcIndex = hisDAO_p
						.setFunction("{? = call bmed_function.gen_equ_test_id(?,?)}");

				hisDAO_p.setFuncInValue(funcIndex, 2, "1");

				hisDAO_p.setFuncInValue(funcIndex, 3,testDtlVO_p.getStrHospitalCode());

				hisDAO_p.setFuncOutValue(funcIndex, 1);
				
				

				// Execute Function
				hisDAO_p.executeFunction(funcIndex);
				strRetVal = hisDAO_p.getFuncString(funcIndex);
		
				
							
				testDtlVO_p.setStrEquTestId(strRetVal);
				WarrantyDtlDAO.update(warrantyDtlVO_p, hisDAO_p);
				TestDtlDAO.save(testDtlVO_p, hisDAO_p);
				for(int i=0;i < testDtlVO_p.getTestParameterDtlVO().size();i++)
				{
					
					funcIndex1 = hisDAO_p
					.setFunction("{? = call bmed_function.gen_equ_test_para_id(?,?)}");
					
					hisDAO_p.setFuncInValue(funcIndex1, 2, "1");

					hisDAO_p.setFuncInValue(funcIndex1, 3,testDtlVO_p.getStrHospitalCode());

					hisDAO_p.setFuncOutValue(funcIndex1, 1);
					
					hisDAO_p.executeFunction(funcIndex1);   
					strRetVal1 = hisDAO_p.getFuncString(funcIndex1);
					testDtlVO_p.getTestParameterDtlVO().get(i).setStrTestParaEquId(strRetVal1);
					TestParameterDtlDAO.save(testDtlVO_p.getTestParameterDtlVO().get(i), hisDAO_p);
				}
			 
				synchronized (hisDAO_p) {
					hisDAO_p.fire(); // Here we Execute in Batch
				}

			} catch (Exception ex) {
				throw new Exception(
						"BmedTransBO.saveDataForItemComplaintRegister(String,String)-->"
								+ ex.getMessage());
			} finally {

				if (hisDAO_p != null) {

					hisDAO_p.free();

					hisDAO_p = null;

				}

			}
		}
		
		
		
	
	/**
	 * To Get Stock Details on the basis of Item Id and Dept Id
	 * 
	 * @param 	strHospitalCode_p	the String
	 * @param	strItemId_p the String
	 * @param	strDeptId_p the String
	 */
	public String getStockDetails(String strHospitalCode_p,	String strItemId_p, String strDeptId_p) throws Exception 
	{

		ItemCurrStockDtlMstVO gblItemCurrStockMstVO;
		WebRowSet wrsStockDtl;
		String strStockDtl;
		try {

			gblItemCurrStockMstVO = new ItemCurrStockDtlMstVO();
			gblItemCurrStockMstVO.setStrMode("4");
			gblItemCurrStockMstVO.setStrItemBrandId("");
			gblItemCurrStockMstVO.setStrDeptId(strDeptId_p);
			gblItemCurrStockMstVO.setStrHospCode(strHospitalCode_p);
			gblItemCurrStockMstVO.setStrItemId(strItemId_p);
			gblItemCurrStockMstVO.setStrBatchNo("0");
			gblItemCurrStockMstVO.setStrStoreId("0");
			gblItemCurrStockMstVO.setStrItemSlNo("0");
			gblItemCurrStockMstVO.setStrItemCatgNo("0");
			gblItemCurrStockMstVO.setStrStockStatusCode("0");

			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			ItemCurrStockDtlMstDAO.getStockDtl(gblItemCurrStockMstVO, hisDAO);
			wrsStockDtl = gblItemCurrStockMstVO.getWrsStockDtl();

			strStockDtl = StockDetailsDATA.getStockDtlWarranty(wrsStockDtl);

		} catch (Exception ex) {
			throw new Exception("BmedGlobalBO.getStockDetails(String)-->"
					+ ex.getMessage());
		}
		return strStockDtl;

	}
	
	/**
	 * To Get Spar ePart Stock Details
	 * 
	 * @param 	hemtItemSparePartDtlVO_p	the HemtItemSparePartDtlVO
	 */
	public void getSparePartStockDetails(HemtItemSparePartDtlVO hemtItemSparePartDtlVO_p) throws Exception 
	{

		HisDAO hisDao = null;
	
		try {
			
			// Transaction Start 
			hisDao = new HisDAO("bmed", "BmedTransBO");
			
	
			HemtItemSparePartDtlDAO.getData(hemtItemSparePartDtlVO_p, hisDao);

		} catch (Exception e) {
			throw new Exception(
					"BmedTransBO.getSparePartStockDetails(hemtItemSparePartDtlVO_p)-->"	+ e.getMessage());
		} finally {
			// Closing Transaction 
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}

	}
	
		
	/**
	 * To Get  Spare Part Name Data on basis of Item Name
	 * 
	 * @param strHospitalCode_p the String
	 * @param strItemId_p the String
	 * 
	 * @return	strItemCategoryComboOptions	the String
	 */
	
	public String getSparePartNameComboOptionsOnBasisOfItemName(String strHospitalCode_p, String strItemId_p) throws Exception
	{
		String strItemCategoryComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		ItemBrandMstVO itemBrandMstVO;
		WebRowSet wrsItemComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedTransBO");
			itemBrandMstVO = new ItemBrandMstVO();
	
			itemBrandMstVO.setStrMode("7");
			itemBrandMstVO.setStrItemId(strItemId_p);// Default
			itemBrandMstVO.setStrHospitalCode(strHospitalCode_p);
			
			HisDAO hisDAO = new HisDAO("BMED", "BmedTransBO");
			ItemBrandMstDAO.getItemBrandCombo(itemBrandMstVO, hisDAO);
			wrsItemComboOptions = itemBrandMstVO.getWrsItemBrandComboOptions();
			if (wrsItemComboOptions != null)
			{
				strItemCategoryComboOptions = hisUtil.getOptionValue(
				wrsItemComboOptions, "0", "0^Select Value", false);
			}

		} 
		catch (Exception ex) 
		{
			throw new Exception("BmedTransBO.getItemCategoryComboOptionsOnBasisOfStore(String strHospitalCode_p, String strStoreId_p)-->" + ex.getMessage());
		}
		return strItemCategoryComboOptions;
	}
	
	
	/**
	 * To Get Manufacturer Name
	 * 
	 * @param hemtItemSparePartDtlVO_p the HemtItemSparePartDtlVO
	 */
	public static void saveDataForOfflineSparePartAddTrans(HemtItemSparePartDtlVO hemtItemSparePartDtlVO_p) throws Exception
	{

		HisDAO hisDAO_p = null;

		try 
		{
			hisDAO_p = new HisDAO("BMED", "BmedTransBO");
			
			HemtItemSparePartDtlDAO.save(hemtItemSparePartDtlVO_p, hisDAO_p);
			
			synchronized (hisDAO_p) {
				hisDAO_p.fire(); // Here we Execute in Batch
			}

		}
		catch (Exception ex) 
		{
			throw new Exception("BmedTransBO.saveDataForOfflineSparePartAddTrans(HemtItemSparePartDtlVO hemtItemSparePartDtlVO_p)-->"+ ex.getMessage());
		} 
		finally 
		{
			if (hisDAO_p != null) 
			{
				hisDAO_p.free();
				hisDAO_p = null;
			}
		}	
	}
	
	/**
	 * To Get Spare Part Name 
	 *
	 *@param	strHospitalCode_p the String
	 *@param	strSpareId_p the String
	 */
	
	public String getSparePartName(String strHospitalCode_p, String strSpareId_p) throws Exception
	{
		String strRetVal = null;
		HisDAO hisDAO_p = null;
		//
		int funcIndex = 0;
		//
		try {
			hisDAO_p = new HisDAO("BMED", "BmedTransBO");
			funcIndex = hisDAO_p
					.setFunction("{? = call bmed_function.get_sparePartName(?,?,?)}");

			hisDAO_p.setFuncInValue(funcIndex, 2, "1");

			hisDAO_p.setFuncInValue(funcIndex, 3, strSpareId_p);

			hisDAO_p.setFuncInValue(funcIndex, 4,strHospitalCode_p);

			hisDAO_p.setFuncOutValue(funcIndex, 1);

			// Execute Function
			hisDAO_p.executeFunction(funcIndex);
			strRetVal = hisDAO_p.getFuncString(funcIndex);

			
		} 
		catch (Exception ex)
		{
			throw new Exception("BmedTransBO.getSparePartName(String,String)-->"+ ex.getMessage());
			
		}
		finally 
		{

			if (hisDAO_p != null) 
			{

				hisDAO_p.free();

				hisDAO_p = null;
			}
		}
		return strRetVal;
	}

	/**
	 * To initialize Complaint Details View Page
	 * 
	 * @param complaintRequestDtlVO_p the vo
	 * @param warrantyDtlVO_p the vo
	 * @param hemtItemMcDtlVO_p the vo
	 * @param hemtComplaintApprovalDtlVO_p the vo
	 * @param complaintScheduleDtlVO_p the vo
	 * @param complaintAttendDtlVO_p the vo
	 * @param hemtReminderDtlVO_p the vo
	 * @param hemtComplaintEscalationDtlVO_p the vo
	 * @param hemtItemSparePartDtlVO_p the vo
	 * 
	 * @return null
	 * 
	 */
	public void initializeComplaintDetailsView(	ComplaintRequestDtlVO complaintRequestDtlVO_p,WarrantyDtlVO warrantyDtlVO_p, HemtItemMcDtlVO hemtItemMcDtlVO_p,HemtComplaintApprovalDtlVO hemtComplaintApprovalDtlVO_p, ComplaintScheduleDtlVO complaintScheduleDtlVO_p,ComplaintAttendDtlVO complaintAttendDtlVO_p,HemtReminderDtlVO hemtReminderDtlVO_p, HemtComplaintEscalationDtlVO hemtComplaintEscalationDtlVO_p,HemtItemSparePartDtlVO hemtItemSparePartDtlVO_p) throws Exception 
	{
		HisDAO hisDao = null;
		String strWarrantySlNo = null;
		String strMcSlNo = null;
		String strItemId;
		

		try {
			// Transaction Start 
			hisDao = new HisDAO("bmed", "BmedTransBO");

			complaintRequestDtlVO_p.setStrMode("3");
			ComplaintRequestDtlDAO.getData(complaintRequestDtlVO_p, hisDao);

			if (complaintRequestDtlVO_p.getWrsData() != null) 
			{
				if (complaintRequestDtlVO_p.getWrsData().next()) 
				{
					strWarrantySlNo = complaintRequestDtlVO_p.getWrsData().getString("HEMNUM_WARRANTY_SLNO");
					strMcSlNo = complaintRequestDtlVO_p.getWrsData().getString("HEMNUM_MC_SLNO");
				}
				
				complaintRequestDtlVO_p.getWrsData().beforeFirst();
				ComplaintRequestDtlDAO.getDataSingleRow(complaintRequestDtlVO_p);
			}
			/*
			 * A complaint can be registered against warranty or maintenance
			 * contract, not both. Either one of this is null, not both.*/
			 
			if (strWarrantySlNo != null) {
				warrantyDtlVO_p.setStrSlNo(strWarrantySlNo);

				
				/* * Mode '4' is to get data in the basis of strWarrantySlNo and
				 * hospital code.*/
				 
				warrantyDtlVO_p.setStrMode("4");

				WarrantyDtlDAO.getData(warrantyDtlVO_p, hisDao);

			} else if (strMcSlNo != null) {
				hemtItemMcDtlVO_p.setStrSlNo(strMcSlNo);

				
				/* * Mode '4' is to get data in the basis of strMcSlNo and
				 * hospital code.
				 */
				hemtItemMcDtlVO_p.setStrMode("4");

				if (hemtItemMcDtlVO_p.getStrItemId() == null) {
					hemtItemMcDtlVO_p.setStrItemId("");
				}
				if (hemtItemMcDtlVO_p.getStrItemSlNo() == null) {
					hemtItemMcDtlVO_p.setStrItemId("");
				}

				HemtItemMcDtlDAO.getPrevMantDtl(hemtItemMcDtlVO_p, hisDao);
			}
			
			complaintScheduleDtlVO_p.setStrMode("2");
			ComplaintScheduleDtlDAO.getData(complaintScheduleDtlVO_p, hisDao);

			complaintAttendDtlVO_p.setStrMode("2");
			ComplaintAttendDtlDAO.getData(complaintAttendDtlVO_p, hisDao);
			
			hemtComplaintApprovalDtlVO_p.setStrMode("1");
			HemtComplaintApprovalDtlDAO.getData(hemtComplaintApprovalDtlVO_p, hisDao);
			
			hemtReminderDtlVO_p.setStrMode("1");
			HemtReminderDtlDAO.getData(hemtReminderDtlVO_p,hisDao);
			
			hemtComplaintEscalationDtlVO_p.setStrMode("2");
			HemtComplaintEscalationDtlDAO.getData(hemtComplaintEscalationDtlVO_p, hisDao);
			
			// For Spare Part Detail
			strItemId=complaintRequestDtlVO_p.getStrItemId();

			hemtItemSparePartDtlVO_p.setStrItemId(strItemId);
			
			hemtItemSparePartDtlVO_p.setStrMode("5");
			HemtItemSparePartDtlDAO.getData(hemtItemSparePartDtlVO_p, hisDao);	 
			
			
		}
		catch (Exception e)
		{
			throw new Exception("BmedTransBO.initializeComplaintDetailsView(complaintRequestDtlVO_p,warrantyDtlVO_p,hemtItemMcDtlVO_p)-->"+ e.getMessage());
		}
		finally
		{
			// Closing Transaction 
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}

	}

	
	/**
	 * To initialize Reminder Page
	 * 
	 * @param complaintRequestDtlVO_p the vo
	 * @param warrantyDtlVO_p the vo
	 * @param hemtItemMcDtlVO_p the vo
	 * @param hemtReminderDtlVO_p the vo
	 * 
	 * @return null
	 * 
	 */
	public void initializeReminder(ComplaintRequestDtlVO complaintRequestDtlVO_p,	WarrantyDtlVO warrantyDtlVO_p, HemtItemMcDtlVO hemtItemMcDtlVO_p, HemtReminderDtlVO hemtReminderDtlVO_p) throws Exception 
	{
		HisDAO hisDao = null;
		String strWarrantySlNo = null;
		String strMcSlNo = null;
		

		try {
			// Transaction Start 
			hisDao = new HisDAO("bmed", "BmedTransBO");

			complaintRequestDtlVO_p.setStrMode("3");
			ComplaintRequestDtlDAO.getData(complaintRequestDtlVO_p, hisDao);

			if (complaintRequestDtlVO_p.getWrsData() != null) 
			{
				if (complaintRequestDtlVO_p.getWrsData().next()) 
				{
					strWarrantySlNo = complaintRequestDtlVO_p.getWrsData().getString("HEMNUM_WARRANTY_SLNO");
					strMcSlNo = complaintRequestDtlVO_p.getWrsData().getString("HEMNUM_MC_SLNO");
				}
				
				complaintRequestDtlVO_p.getWrsData().beforeFirst();
				ComplaintRequestDtlDAO.getDataSingleRow(complaintRequestDtlVO_p);
			}
			
			/* * A complaint can be registered against warranty or maintenance
			 * contract, not both. Either one of this is null, not both.*/
			 
			if (strWarrantySlNo != null) {
				warrantyDtlVO_p.setStrSlNo(strWarrantySlNo);

				
				 /** Mode '4' is to get data in the basis of strWarrantySlNo and
				 * hospital code.*/
				 
				warrantyDtlVO_p.setStrMode("4");

				WarrantyDtlDAO.getData(warrantyDtlVO_p, hisDao);

			} else if (strMcSlNo != null) {
				hemtItemMcDtlVO_p.setStrSlNo(strMcSlNo);

				/*
				 * Mode '4' is to get data in the basis of strMcSlNo and
				 * hospital code.*/
				 
				hemtItemMcDtlVO_p.setStrMode("4");

				if (hemtItemMcDtlVO_p.getStrItemId() == null) {
					hemtItemMcDtlVO_p.setStrItemId("");
				}
				if (hemtItemMcDtlVO_p.getStrItemSlNo() == null) {
					hemtItemMcDtlVO_p.setStrItemId("");
				}

				HemtItemMcDtlDAO.getPrevMantDtl(hemtItemMcDtlVO_p, hisDao);
			}
			
				
			hemtReminderDtlVO_p.setStrMode("1");
			HemtReminderDtlDAO.getData(hemtReminderDtlVO_p,hisDao);
			
				
		}
		catch (Exception e)
		{
			throw new Exception("BmedTransBO.initializeReminder(complaintRequestDtlVO_p,warrantyDtlVO_p,hemtItemMcDtlVO_p)-->"+ e.getMessage());
		}
		//finally
		{
			 //Closing Transaction 
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}
	}
	
	/**
	 * To Save Reminder Details
	 * 
	 * @param hemtReminderDtlVO_p the HemtReminderDtlVO
	 */
	public void saveReminder(HemtReminderDtlVO hemtReminderDtlVO_p) throws Exception 
	{
		HisDAO hisDao = null;
		try 
		{
			hisDao = new HisDAO("BMED", "BmedTransBO");

			HemtReminderDtlDAO.save(hemtReminderDtlVO_p, hisDao);
			hisDao.fire();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new Exception("BmedTransBO.saveReminder(hemtReminderDtlVO_p)-->"	+ e.getMessage());
		} 
		finally 
		{
			if (hisDao != null) 
			{
				hisDao.free();
				hisDao = null;
			}
		}
	}
	
	
	/**
	 * To get Maintenance Contract Details For Item Complaint Register
	 * 
 	 * @param	hemtItemMcDtlVO_p	the HemtItemMcDtlVO
	 * 
	 */
	public void getMaintenanceContractDetailsForReport(HemtItemMcDtlVO hemtItemMcDtlVO_p)	throws Exception 
	{
		HisDAO hisDao = null;
		try {
			
			// Transaction Start 
			hisDao = new HisDAO("bmed", "BmedTransBO");

			HemtItemMcDtlDAO.getPrevMantDtl(hemtItemMcDtlVO_p, hisDao);

		} 
		catch (Exception e)
		{
			throw new Exception("BmedTransBO.getMaintenanceContractDetailsForReport(hemtItemMcDtlVO_p)-->" + e.getMessage());
		} 
		finally 
		{
			// Closing Transaction 
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}

	}
	
	//****************************************************************************************************//*
	// Method Used in Vivek Aggarwal Trnasaction [ END] 
	//****************************************************************************************************//*

	//****************************************************************************************************//*
	                          //  Method Used in Amit Kr Trnasaction [Start ]                           
	//****************************************************************************************************//* 
	 
	
	/**
	 * saveItemMaintenanceContactDetails() Method is used to cancel data form table [ hemt_item_mc_dtl ]
	 * @param hemtItemMcGlobalVO_p
	 * @return
	 */
	public static boolean save(HemtComplaintEscalationDtlVO hemtComplaintEscalationDtlVO_p) throws Exception
	{		
		boolean retVal = false;
		HisDAO hisDAO_p = null;
		try 
		{	
			hisDAO_p = new HisDAO("BMED", "BmedTransBO");
	
			HemtComplaintEscalationDtlDAO.save(hemtComplaintEscalationDtlVO_p, hisDAO_p);
			
			hisDAO_p.fire(); // Here we Execute in Batch
			retVal = true;

		} 
		catch (Exception ex) 
		{			 
			throw new Exception("BmedTransBO.save(String,String)-->"+ex.getMessage());
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
	
	/**
	 * saveItemMaintenanceContactDetails() Method is used to cancel data form table [ hemt_item_mc_dtl ]
	 * @param hemtItemMcGlobalVO_p
	 * @return
	 */
	public static boolean saveComplaintApprovalData(HemtComplaintStatusDtlVO hemtComplaintStatusDtlsVO,ComplaintRequestDtlVO hemtComplaintRequestDtlsVO,HemtComplaintApprovalDtlVO hemtComplaintApprovalDtlVO_p) throws Exception
	{		
		boolean retVal = false;
		HisDAO hisDAO_p = null;
		try 
		{	
			hisDAO_p = new HisDAO("BMED", "BmedGlobalBO");
			
			HemtComplaintStatusDtlDAO.save(hemtComplaintStatusDtlsVO, hisDAO_p);
			
			HemtComplaintApprovalDtlDAO.save(hemtComplaintApprovalDtlVO_p, hisDAO_p);
			
			ComplaintRequestDtlDAO.save(hemtComplaintRequestDtlsVO, hisDAO_p);
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
	/**
	 * This Method is Used to get stock details for HLP
	 * @param strHospitalCode_p
	 * @param strItemBrandId_p
	 * @param strDeptId_p
	 * @param strMode_p
	 * @return
	 * @throws Exception
	 */
	public String getStockDetails(String strHospitalCode_p,String strItemBrandId_p,String strDeptId_p,String strMode_p) throws Exception 
	{
		
		ItemCurrStockDtlMstVO gblItemCurrStockMstVO;
		WebRowSet wrsStockDtl;
		String strStockDtl;
		try 
		{
			
			gblItemCurrStockMstVO = new ItemCurrStockDtlMstVO();
			if(strMode_p.equals("1"))
			{	
			   gblItemCurrStockMstVO.setStrMode("2");
			}
			else
			{
			   gblItemCurrStockMstVO.setStrMode("3");
			}	
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
			
			strStockDtl = StockDetailsDATA.getStockDtlWarranty(wrsStockDtl);
			

		} catch (Exception ex) {
			throw new Exception("BmedGlobalBO.getStockDetails(String)-->"+ex.getMessage());
		}
		return strStockDtl;

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
			/*   0                        1                   2
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
				gblItemMCDtlVO.setStrItemId(strStockInfo_p.split("\\^")[1]);
			}
			else
			{
				
				gblItemMCDtlVO.setStrMode("3");
				gblItemMCDtlVO.setStrItemId(strStockInfo_p.split("\\^")[1]);
			}
//			System.out.println("getPrevMantDetails()--:>");
//			System.out.println("Item Id:::::"+gblItemMCDtlVO.getStrItemId());
//			System.out.println("Hosp Code:::"+strHospitalCode_p);
//			System.out.println("Item Brand Id:::"+strStockInfo_p.split("\\^")[2]);
//			System.out.println("Batch:::"+strStockInfo_p.split("\\^")[3]);
//            System.out.println("Item Sl No:::"+strStockInfo_p.split("\\^")[4]);
//            System.out.println("Mode::::"+gblItemMCDtlVO.getStrMode());
            
			gblItemMCDtlVO.setStrBatchNo(strStockInfo_p.split("\\^")[3]);
			gblItemMCDtlVO.setStrItemSlNo(strStockInfo_p.split("\\^")[4]);
			gblItemMCDtlVO.setStrSlNo("0");
			gblItemMCDtlVO.setStrHospCode(strHospitalCode_p);
								
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
					
			HemtItemMcDtlDAO.getPrevMantDtl(gblItemMCDtlVO, hisDAO);
					
			strPrevMantDtl = PerviousMaintenanceDetailsDATA.getPreviousMaintenanceDetails(gblItemMCDtlVO.getWrsMCDetails(),strMode);
			

		} catch (Exception ex) {
			throw new Exception("BmedGlobalBO.getPrevMantDetails(String)-->"+ex.getMessage());
		}
		return strPrevMantDtl;

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
			
			 /*//  0                        1                   2
         	  HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
         	 //              3                   4
         	   ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
         	  //           5                      6                                 7
                GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE||'^'||Is_Item(1 : for Item 2:for Non-Item)
         	 
			*/
			gblWarrantyDtlVO = new WarrantyDtlVO();
			            
            gblWarrantyDtlVO.setStrMode("5");
//            System.out.println("Stock Info:::::"+strStockInfo_p);
//            System.out.println("Item ID:::"+strStockInfo_p.split("\\^")[1]);
//            System.out.println("Item Brand ID:::"+strStockInfo_p.split("\\^")[2]);
//            System.out.println("Batch Sl No::"+strStockInfo_p.split("\\^")[3]);
//            System.out.println("Item ID:::"+strStockInfo_p.split("\\^")[4]);
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
			throw new Exception("BmedGlobalBO.getPrevMantDetails(String)-->"+ex.getMessage());
		}
		return strPrevMantDtl;

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
				 
			/*  Value in strInfoVal_p Concated by ^ Symbol
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
		           17. SL NO*/
			 
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltUnitMstVO = new UnitMstVO();
			gbltUnitMstVO.setStrMode("1");
			gbltUnitMstVO.setStrHospitalCode(Config.SUPER_USER_HOSPITAL_CODE);
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
			throw new Exception("BmedGlobalBO.getSupplierComboOptions(String,String)-->"+ex.getMessage());
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
		           17. SL NO*/
			 
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltUnitMstVO = new UnitMstVO();
			gbltUnitMstVO.setStrMode("1");
			gbltUnitMstVO.setStrHospitalCode(Config.SUPER_USER_HOSPITAL_CODE);
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
			throw new Exception("BmedGlobalBO.getSupplierComboOptions(String,String)-->"+ex.getMessage());
		}
		
		return strResTimeComboOptions;

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
			throw new Exception("BmedGlobalBO.getSupplierComboOptions(String,String)-->"+ex.getMessage());
		}
		return strMaintTypeComboOptions+"@@"+strUnitComboOptions;

	}
	
	/**
	 * Method is used to get previous maintenance details HLP
	 * @param strHospitalCode_p
	 * @param strStockInfo_p
	 * @param strMode
	 * @return
	 * @throws Exception
	 */
	public void getProcessSpecificDtl(HemComplaintApprovalDeskVO hemComplaintApprovalDeskVO_p,String strFlag_p) throws Exception 
	{
		
		ComplaintRequestDtlVO complaintRequestDtlVO_p=null;
		HisDAO hisDAO = null;
		String strComplaintAppDtls;
		try 
		{			 			
								
			                 hisDAO = new HisDAO("BMED", "BmedTransBO");
			complaintRequestDtlVO_p = new ComplaintRequestDtlVO();		
			complaintRequestDtlVO_p.setStrMode("3");
			complaintRequestDtlVO_p.setStrHospitalCode(hemComplaintApprovalDeskVO_p.getStrHospCode());
			complaintRequestDtlVO_p.setStrReqId(hemComplaintApprovalDeskVO_p.getStrComplaintId());
			complaintRequestDtlVO_p.setStrBatchNo(hemComplaintApprovalDeskVO_p.getStrItemBatchNo());
			complaintRequestDtlVO_p.setStrItemSlNo(hemComplaintApprovalDeskVO_p.getStrItemSerialNo());
		//	complaintRequestDtlVO_p.setStrItemId(hemComplaintApprovalDeskVO_p.getstrite);
			
			// Calling DAO method here
			ComplaintRequestDtlDAO.getData(complaintRequestDtlVO_p, hisDAO);
			ComplaintRequestDtlDAO.getPrevMantDtl(complaintRequestDtlVO_p, hisDAO);
			// Prepare String as HLP
			strComplaintAppDtls = HemComplaintApprovalDeskDATA.getCoplaintSpecificDtl(complaintRequestDtlVO_p.getWrsData(),strFlag_p);
			hemComplaintApprovalDeskVO_p.setStrComplaintAppDtls(strComplaintAppDtls);
		      
		} catch (Exception ex) {
			throw new Exception("BmedGlobalBO.getProcessSpecificDtl(String)-->"+ex.getMessage());
		}
		

	}
	
	/**
	 * Method is used to get previous maintenance details HLP
	 * @param strHospitalCode_p
	 * @param strStockInfo_p
	 * @param strMode
	 * @return
	 * @throws Exception
	 */
	public void getCommunicationIDCmb(HemDeskVO hemDeskVO_p,String strMode_p) throws Exception 
	{
		String strComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		ComplaintScheduleDtlVO complaintScheduleDtlVO_p=null;
		HisDAO hisDAO = null;
		WebRowSet wrsMainCommunicationComboOptions;
		HisUtil hisUtil;
		try 
		{
			                 hisUtil = new HisUtil("BMED", "BmedTransBO");				
			                  hisDAO = new HisDAO("BMED", "BmedTransBO");
			                 complaintScheduleDtlVO_p = new ComplaintScheduleDtlVO();		
			                 complaintScheduleDtlVO_p.setStrMode(strMode_p);
			                 complaintScheduleDtlVO_p.setStrHospitalCode(hemDeskVO_p.getStrHospCode());
			                 complaintScheduleDtlVO_p.setStrReqId(hemDeskVO_p.getStrComplaintId());
			                 
			              // Calling DAO method here
			                 ComplaintScheduleDtlDAO.getData(complaintScheduleDtlVO_p, hisDAO);
			                 wrsMainCommunicationComboOptions = complaintScheduleDtlVO_p.getWrsData();
			     			if (wrsMainCommunicationComboOptions != null) 
			     			{
			     				strComboOptions = hisUtil.getOptionValue(wrsMainCommunicationComboOptions, "0", "0^Select Value", false);

			     			}
			     			
			     			hemDeskVO_p.setStrCommunicationCmb(strComboOptions);               
			                
		      
		} 
		catch (Exception ex) 
		{
		//	ex.printStackTrace();
			throw new Exception("BmedGlobalBO.getCommunicationIDCmb(HemDeskVO)-->"+ex.getMessage());
		}
		

	}
	/*//**
	 * Method is used to get previous maintenance details HLP
	 * @param strHospitalCode_p
	 * @param strStockInfo_p
	 * @param strMode
	 * @return
	 * @throws Exception
	 */
	public void getEscLevelTypeCmb(HemDeskVO hemDeskVO_p,String strMode_p) throws Exception 
	{
		String strComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		SemtEscalationLevelTypeMstVO semtEscalationLevelTypeMstVO_p=null;
		HisDAO hisDAO = null;
		WebRowSet wrsEscLevelTypeComboOptions;
		HisUtil hisUtil;
		try 
		{
			                 hisUtil = new HisUtil("BMED", "BmedTransBO");				
			                  hisDAO = new HisDAO("BMED", "BmedTransBO");
			                  semtEscalationLevelTypeMstVO_p = new SemtEscalationLevelTypeMstVO();		
			                  semtEscalationLevelTypeMstVO_p.setStrMode(strMode_p);
			                  semtEscalationLevelTypeMstVO_p.setStrHospCode(Config.SUPER_USER_HOSPITAL_CODE);
			                  semtEscalationLevelTypeMstVO_p.setStrLevelTypeId("0");
			                 
			              // Calling DAO method here
			                  SemtEscalationLevelTypeMstDAO.getSemtCancelTypeMstCombo(semtEscalationLevelTypeMstVO_p, hisDAO);
			                  wrsEscLevelTypeComboOptions = semtEscalationLevelTypeMstVO_p.getWrsData();
			     			if (wrsEscLevelTypeComboOptions != null) 
			     			{
			     				strComboOptions = hisUtil.getOptionValue(wrsEscLevelTypeComboOptions, "0", "0^Select Value", false);

			     			}
			     			
			     			hemDeskVO_p.setStrEscalationLevelCmb(strComboOptions);               
			                
		      
		} 
		catch (Exception ex) 
		{
//			ex.printStackTrace();
			throw new Exception("BmedGlobalBO.getPreviousEscInternalDtl(HemDeskVO)-->"+ex.getMessage());
		}
		

	}
	
	
	/*
	  Method is used to get previous maintenance details HLP
	  @param strHospitalCode_p
	  @param strStockInfo_p
	  @param strMode
	  @return
	  @throws Exception
	*/ 
	public void getPreviousEscInternalDtl(HemDeskVO hemDeskVO_p,String strMode_p) throws Exception 
	{
		
		HemtComplaintEscalationDtlVO hemtComplaintEscalationDtlVO_p=null;
		HisDAO hisDAO = null;
		String strPrevEscDtls;
		try 
		{			 			
								
			                 hisDAO = new HisDAO("BMED", "BmedTransBO");
			                 hemtComplaintEscalationDtlVO_p = new HemtComplaintEscalationDtlVO();		
			                 hemtComplaintEscalationDtlVO_p.setStrMode("1");
			                 hemtComplaintEscalationDtlVO_p.setStrHospitalCode(hemDeskVO_p.getStrHospCode());
			                 hemtComplaintEscalationDtlVO_p.setStrReqId(hemDeskVO_p.getStrComplaintId());
			                 hemtComplaintEscalationDtlVO_p.setStrEscId("0");
			              // Calling DAO method here
			                 HemtComplaintEscalationDtlDAO.getData(hemtComplaintEscalationDtlVO_p, hisDAO);
			              // Prepare String as HLP
			                 if(hemDeskVO_p.getStrReqType().equals("1"))
			                 {	 
			                    strPrevEscDtls = HemDeskDATA.getPreviousEsc(hemtComplaintEscalationDtlVO_p.getWrsData(),"1");
			                 }
			                 else
			                 {
			                	 strPrevEscDtls = HemDeskDATA.getPreviousEsc(hemtComplaintEscalationDtlVO_p.getWrsData(),"2");
			                 } 
			                 
			                 hemDeskVO_p.setStrPrevEsclationDtl(strPrevEscDtls);
		      
		} 
		catch (Exception ex) 
		{
//			ex.printStackTrace();
			throw new Exception("BmedGlobalBO.getPreviousEscInternalDtl(HemDeskVO)-->"+ex.getMessage());
		}
		

	}
	
	
	
	 /* Method is used to get previous maintenance details HLP
	  @param strHospitalCode_p
	  @param strStockInfo_p
	  @param strMode
	  @return
	  @throws Exception
	*/
	public String getDtl(String strMode_p,String strHospCode_p,String strEmpId_p) throws Exception 
	{
		
		HemDeskVO hemDeskVO_p=null;
		HisDAO hisDAO = null;
		String strEmpDtls=null;
		String strEmail,strMobNo,strDesg,strDept;
		WebRowSet ws = null;
		try 
		{			 			
								
			                      hisDAO = new HisDAO("BMED", "BmedTransBO");
			                 hemDeskVO_p = new HemDeskVO();		
			                 
			                 hemDeskVO_p.setStrMode(strMode_p);
			                 hemDeskVO_p.setStrHospCode(strHospCode_p);
			                 hemDeskVO_p.setStrEmpId(strEmpId_p);
			// Calling DAO method here
			                 PistEmpPersonalDtlDAO.getEmpDtl(hemDeskVO_p, hisDAO);
			                 ws = hemDeskVO_p.getWrsData();
			                 if(ws.size()>0)
			                 {	 
					                 while(ws.next())
					                 {
					                	  if(!ws.getString(1).equals("")||!ws.getString(1).equals(" "))
					                	  {
					                		  strEmail = ws.getString(1);
					                	  } 
					                	  else
					                	  {
					                		  strEmail = "N/A";
					                	  } 
					                	  if(!ws.getString(2).equals("")||!ws.getString(2).equals(" "))
					                	  {
					                		  strMobNo = ws.getString(2);
					                	  } 
					                	  else
					                	  {
					                		  strMobNo = "N/A";
					                	  } 
					                	  if(!ws.getString(3).equals("")||!ws.getString(3).equals(" "))
					                	  {
					                		  strDesg = ws.getString(3);
					                	  } 
					                	  else
					                	  {
					                		  strDesg = "N/A";
					                	  } 
					                	  if(!ws.getString(4).equals("")||!ws.getString(4).equals(" "))
					                	  {
					                		  strDept = ws.getString(4);
					                	  } 
					                	  else
					                	  {
					                		  strDept = "N/A";
					                	  } 
					                	    	  
					                	  
					                	//            E-Mail       Mob No     Designation  Department
					                	 strEmpDtls = strEmail+"^"+strMobNo+"^"+strDesg+"^"+strDept;
					                 }
					                 
			                   }      
			                   else
			                   {
			                	  strEmpDtls= "N/A"+"^"+"N/A"+"^"+"N/A"+"^"+"N/A";
			                   }
		      
		}
		catch (Exception ex)
		{
//			ex.printStackTrace();
			throw new Exception("BmedGlobalBO.getProcessSpecificDtl(String)-->"+ex.getMessage());
		}
		
		return strEmpDtls;

	}
	
	
	 //used in complaint cancel, complaint schedule. 
	public void initializeComplaintApprovalActions(ComplaintRequestDtlVO complaintRequestDtlVO_p,WarrantyDtlVO warrantyDtlVO_p, HemtItemMcDtlVO hemtItemMcDtlVO_p) throws Exception {

		HisDAO hisDao = null;
		String strWarrantySlNo = null;
		String strMcSlNo = null;

		try {
			 //Transaction Start 
			hisDao = new HisDAO("bmed", "BmedTransBO");

			complaintRequestDtlVO_p.setStrMode("3");
			ComplaintRequestDtlDAO.getData(complaintRequestDtlVO_p, hisDao);

			if (complaintRequestDtlVO_p.getWrsData() != null) 
			{
				if (complaintRequestDtlVO_p.getWrsData().next()) 
				{
					strWarrantySlNo = complaintRequestDtlVO_p.getWrsData().getString("HEMNUM_WARRANTY_SLNO");
					      strMcSlNo = complaintRequestDtlVO_p.getWrsData().getString(	"HEMNUM_MC_SLNO");
				}
				complaintRequestDtlVO_p.getWrsData().beforeFirst();
				ComplaintRequestDtlDAO.getDataSingleRow(complaintRequestDtlVO_p);
			}
			
			 /* A complaint can be registered against warranty or maintenance
			  contract, not both. Either one of this is null, not both.
			 */
			if (strWarrantySlNo != null) {
				warrantyDtlVO_p.setStrSlNo(strWarrantySlNo);

				
				/* Mode '4' is to get data in the basis of strWarrantySlNo and
				  hospital code.
				 */
				warrantyDtlVO_p.setStrMode("4");

				WarrantyDtlDAO.getData(warrantyDtlVO_p, hisDao);

			} else if (strMcSlNo != null) {
				hemtItemMcDtlVO_p.setStrSlNo(strMcSlNo);

				
				 /* Mode 'x' is to get data in the basis of strWarrantySlNo and
				  hospital code.
				 */
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
			e.printStackTrace();
			throw new Exception(					
					"BmedTransBO.getComplaintRequestData(ComplaintRequestDtlVO)-->"
							+ e.getMessage());
		} finally {
			// Closing Transaction 
			if (hisDao != null) {
				hisDao.free();
				hisDao = null;
			}
		}

	}
	
	
	/**
	 * To Get  Doc Reference No in Case of File UpLoading
	 * Developer Adil Wasi
	 * 
	 * @param 	strHospitalCode_p String
	 * 
	 * @return	strRetVal the String
	 * 
	 */
	public static String getStrDocRefNo(String strHospitalCode_p, String strMode) throws Exception 
	{
		String strRetVal = null;
		HisDAO hisDAO_p = null;
		//
		int funcIndex = 0;
		//
		try {
			hisDAO_p = new HisDAO("BMED", "BmedTransBO");
			funcIndex = hisDAO_p.setFunction("{? = call bmed_function.gen_fileupload_no(?,?)}");
		
			hisDAO_p.setFuncInValue(funcIndex, 2, strMode);
		
			hisDAO_p.setFuncInValue(funcIndex, 3, strHospitalCode_p);
			
			hisDAO_p.setFuncOutValue(funcIndex, 1);
		
			// Execute Function
		
			hisDAO_p.executeFunction(funcIndex);
			
			hisDAO_p.getFuncString(funcIndex);
			
			strRetVal = hisDAO_p.getFuncString(funcIndex);
			System.out.println("strRetVal= "+strRetVal);
			}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception("BmedTransBO.getDocRefNo(String)-->" + ex.getMessage());
		} 
		finally 
		{
			if (hisDAO_p != null) 
			{
				hisDAO_p.free();
				hisDAO_p = null;
			}
		}
		return strRetVal;
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
			              System.out.println("strFileName (Inside BO) ::::::"+strFileName);
			               return strFileName;
			               
		}catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("BmedTransBO.getFileName(String)-->" + ex.getMessage());
		}
		
	}


	
	
	/****************************************************************************************************/
	/*                            Method Used in Amit Kr Trnasaction [ END]                            */
	/****************************************************************************************************/ 

	
}
