package billing.transactions;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import hisglobal.utility.GetNetworkAddress;

import javax.sql.rowset.WebRowSet;

import billing.dao.ApprovalDAO;
import billing.dao.BillDAO;
import billing.dao.BillReceiptDAO;
import billing.dao.BillRequisitionTariffDAO;
import billing.dao.ClientPatientDAO;
import billing.dao.OutBoundDAO;
import billing.dao.PatientAccountDAO;
import billing.dao.PatientAccountServiceDAO;
import billing.dao.PatientAccountStatusDAO;
import billing.dao.PaymentDAO;
import billing.dao.PrimaryKeyDAO;
import billing.dao.PrimaryKeyLogDAO;
import hisglobal.utility.SMSHttpsNICPostClient;


public class PatWalletTransDAO
{

	/**
	 * retrieves Patient Account Details
	 * 
	 * @param voObj -
	 *           Value Object
	 */


	/**
	 * retrieves Client Patient Details using Client Patient Number.
	 * 
	 * @param voObj -
	 *           Value Object
	 */
	public static void getClientPatientDetailsWithClientPatNo(PatWalletTransVO voObj)
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_hblt_client_patient_dtl(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strClientPatNo = voObj.getStrClientPatientNo();
		String strChargeTypeId = voObj.getStrChargeTypeId();
		String strHospCode = voObj.getStrHospitalCode();
		String strBillNo = voObj.getStrBillNo();
		String strErr = "";

		try
		{

			daoObj = new HisDAO("Cash Collection Transaction",
					"PatWalletTransDAO.getClientPatientDetailsWithClientPatNo");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "clientpatno", strClientPatNo, 2);
			daoObj.setProcInValue(nProcIndex, "chargetypeid", strChargeTypeId, 4);
			daoObj.setProcInValue(nProcIndex, "billNo", strBillNo, 6);
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 5);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospCode, 7);

			daoObj.setProcInValue(nProcIndex, "puk", "", 1);
			daoObj.setProcInValue(nProcIndex, "clientpatslno", "", 3);

			daoObj.setProcOutValue(nProcIndex, "err", 1, 8);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 9);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				if (ws != null)
				{

					voObj.setOnlineClientDetails(ws);

				} else
				{
					voObj.setOnlineClientDetails(null);
				}

			} else
			{
				throw new Exception(strErr);
			}

		} catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.getClientPatientDetailsWithClientPatNo() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * retrieves Client Patient Details using Cr Number
	 * 
	 * @param voObj -
	 *           Value Object
	 */
	public static void getClientPatientDetailsWithCrNo(PatWalletTransVO voObj)
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_hblt_client_patient_dtl(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strCrNumber = voObj.getStrCrNo();
		String strChargeTypeId = voObj.getStrChargeTypeId();
		String strHospCode = voObj.getStrHospitalCode();
		String strBillNo = voObj.getStrBillNo();
		String strErr = "";

		try
		{

			daoObj = new HisDAO("Cash Collection Transaction", "PatWalletTransDAO.getClientPatientDetailsWithCrNo");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "puk", strCrNumber, 1);
			daoObj.setProcInValue(nProcIndex, "chargetypeid", strChargeTypeId, 4);
			daoObj.setProcInValue(nProcIndex, "billNo", strBillNo, 6);
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 5);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospCode, 7);

			daoObj.setProcInValue(nProcIndex, "clientpatno", "", 2);
			daoObj.setProcInValue(nProcIndex, "clientpatslno", "", 3);

			daoObj.setProcOutValue(nProcIndex, "err", 1, 8);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 9);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				if (ws != null)
				{

					voObj.setOnlineClientDetails(ws);

				} else
				{
					voObj.setOnlineClientDetails(null);
				}

			} else
			{
				throw new Exception(strErr);
			}

		} catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.getClientPatientDetailsWithCrNo() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * retrieves Hospital Service List
	 * 
	 * @param voObj -
	 *           Value Object
	 */
	
	public static void getHospitalServiceList(PatWalletTransVO voObj)
	{
		//If Role Based Defined Then Role Based Data Otherwise All Data.Logic defined in Procedure
		
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_SBLT_CHARGETYPE_MST(?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strSeatId = voObj.getStrSeatId();
		String strErr = "";

		try
		{
			daoObj = new HisDAO("Billing", "PatWalletTransDAO.getHospitalServiceList");
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 1);//Global Hospital Code Hard Coded in Procedure
			daoObj.setProcInValue(nProcIndex, "seatId", strSeatId, 3);
			daoObj.setProcInValue(nProcIndex, "crNo", voObj.getStrCrNo(), 4);
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 2);
			daoObj.setProcOutValue(nProcIndex,"err", 1, 5);
			daoObj.setProcOutValue(nProcIndex,"RESULTSET", 2, 6);

			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				voObj.setOfflineHospitalServiceList(ws);
			} 
			else
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e)
		{
			voObj.setStrMsgString("PatWalletTransDAO.getHospitalServiceList() --> " + e.getMessage());
			voObj.setStrMsgType("1");
		} 
		finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * retrieves Billing Service List
	 * 
	 * @param voObj -
	 *           Value Object
	 */
	public static void getBillingServiceList(PatWalletTransVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_SBLT_BILLSERVICE_MST(?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strChargeId = voObj.getStrOffLineHospitalService();
		String strRequestType = voObj.getStrOffLineRequestType();

		String strErr = "";

		try
		{
			daoObj = new HisDAO("Billing", "PatWalletTransDAO.getBillingServiceList");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 1);//Global Hospital Code Hard Coded in Procedure
			daoObj.setProcInValue(nProcIndex, "charge_id", "1", 2);
			daoObj.setProcInValue(nProcIndex, "req_type", strRequestType, 3);
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 4);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 5);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 6);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				voObj.setOfflineBillingServiceList(ws);
			} 
			else
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e)
		{
			voObj.setStrMsgString("PatWalletTransDAO.getBillingServiceList() --> " + e.getMessage());
			voObj.setStrMsgType("1");
		} 
		finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * retrieves Raising Department List
	 * 
	 * @param voObj -
	 *           Value Object
	 */
	public static void getRaisingDepartmentList(PatWalletTransVO voObj)
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_simple_view.proc_GBLT_DEPARTMENT_MST(?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strCrNo = voObj.getStrCrNo();

		String strChargeTypeId = voObj.getStrOffLineHospitalService();

		String strErr = "";

		try
		{

			daoObj = new HisDAO("Billing", "PatWalletTransDAO.getRaisingDepartmentList");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "4", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 2);
			daoObj.setProcInValue(nProcIndex, "puk_no", strCrNo, 4);
			daoObj.setProcInValue(nProcIndex, "charge_type_id", strChargeTypeId, 5);
			daoObj.setProcInValue(nProcIndex, "deptcode", "", 3);
			daoObj.setProcInValue(nProcIndex, "effect_from", "", 6);
			daoObj.setProcInValue(nProcIndex, "effect_to", "", 7);
			daoObj.setProcInValue(nProcIndex, "userId", "", 8);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 9);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 10);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				if (ws.next())
				{

					voObj.setStrOffLineRaisingDepartment(ws.getString(1));

					ws.beforeFirst();

				}

				voObj.setOfflineRaisingDepartmentList(ws);

			} else
			{
				throw new Exception(strErr);
			}

		} catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.getRaisingDepartmentList() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}

	// Added new method by garima gotra for HIS_PGI_BILL_CR_2 on 19 Aug 2011 Get Last Episode
	public static void getRaisingDepartment(PatWalletTransVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_simple_view.proc_GBLT_DEPARTMENT_MST(?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strHospitalCode = voObj.getStrHospitalCode();
		String strCrNo = voObj.getStrCrNo();
		String strChargeTypeId = voObj.getStrOffLineHospitalService();
		String strErr = "";

		try
		{
			daoObj = new HisDAO("Billing", "PatWalletTransDAO.getRaisingDepartment");
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "5", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 2);
			daoObj.setProcInValue(nProcIndex, "puk_no", strCrNo, 4);
			daoObj.setProcInValue(nProcIndex, "charge_type_id", strChargeTypeId, 5);
			daoObj.setProcInValue(nProcIndex, "deptcode", "", 3);
			daoObj.setProcInValue(nProcIndex, "effect_from", "", 6);
			daoObj.setProcInValue(nProcIndex, "effect_to", "", 7);
			daoObj.setProcInValue(nProcIndex, "userId", "", 8);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 9);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 10);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				if (ws.next())
				{
					voObj.setStrOffLineRaisingDepartment(ws.getString(1));
					ws.beforeFirst();
				}
			} 
			else
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e)
		{
			voObj.setStrMsgString("PatWalletTransDAO.getRaisingDepartment() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} 
		finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * retrieves Episode List
	 * 
	 * @param voObj -
	 *           Value Object
	 */
	public static void getEpisodeList(PatWalletTransVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_simple_view.proc_HRGT_EPISODE_DTL(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strDeptCode = voObj.getStrOffLineRaisingDepartment();//Admitted Department/Last Visited Department
		String strCrNo = voObj.getStrCrNo();

		String strErr = "";

		try
		{
			daoObj = new HisDAO("Billing", "PatWalletTransDAO.getEpisodeList");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "deptCode", strDeptCode, 2);
			daoObj.setProcInValue(nProcIndex, "puk", strCrNo, 3);
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 1);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				/* This Logic Implemented in method getAdmEpisodeTreatmentCatDtl to set last Admitted episode/visited episode..Amit Kumar Ateria
				/*if (ws.next())
				{
					voObj.setStrOffLineEpisode(ws.getString(1) + "@" + ws.getString(3)+ "@" + ws.getString(4));//Episode Code@TreatmentCat@CatGroup
					ws.beforeFirst();
				}*/
				voObj.setOfflineEpisodeList(ws);
			} 
			else
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e)
		{
			voObj.setStrMsgString("PatWalletTransDAO.getEpisodeList() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} 
		finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * retrieves Treatment Category List
	 * 
	 * @param voObj -
	 *           Value Object
	 */
	public static void getTreatmentCategoryList(PatWalletTransVO voObj)
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_simple_view.PROC_GBLT_PATIENT_CAT_MST_DATA(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strCrNo = voObj.getStrCrNo();
		String strChargeTypeId = voObj.getStrOffLineHospitalService();
		//System.out.println("charge type id is "+strChargeTypeId+" "+voObj.getStrTreatmentCategory());
		String strErr = "";
		try
		{
			daoObj = new HisDAO("Billing", "PatWalletTransDAO.getTreatmentCategoryList");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "3", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 2);
			daoObj.setProcInValue(nProcIndex, "puk_no", strCrNo, 3);
			daoObj.setProcInValue(nProcIndex, "charge_type_id", strChargeTypeId, 4);
			daoObj.setProcInValue(nProcIndex, "effect_from", "", 5);
			daoObj.setProcInValue(nProcIndex, "effect_TO", "", 6);
			daoObj.setProcInValue(nProcIndex, "cat_code", voObj.getStrTreatmentCategory(), 7);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 8);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 9);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				//This Logic implemented in method getAdmEpisodeTreatmentCatDtl to select Admitted Treatment Category/Last Visited Episode Category/Primary Category.Amit Kumar Ateria
				/*if (ws.next())
				{
					voObj.setStrOffLineTreatmentCategory(ws.getString(1));
					ws.beforeFirst();
				}*/
				voObj.setOfflineTreatmentCategoryList(ws);

			} 
			else
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e)
		{
			voObj.setStrMsgString("PatWalletTransDAO.getTreatmentCategoryList() --> " + e.getMessage());
			voObj.setStrMsgType("1");
		} 
		finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * retrieves Ward List
	 * 
	 * @param voObj -
	 *           Value Object
	 */
	public static void getWardList(PatWalletTransVO voObj)
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_ipd_chargetype_list(?,?,?,?)}";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();

		String strErr = "";

		try
		{

			daoObj = new HisDAO("Billing", "PatWalletTransDAO.getWardList");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "mode_val", "1", 1);

			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 2);

			daoObj.setProcOutValue(nProcIndex, "err", 1, 3);

			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				if (ws.next())
				{

					voObj.setStrOffLineWard(ws.getString(1));

					ws.beforeFirst();

				}

				voObj.setOfflineWardList(ws);

			} else
			{
				throw new Exception(strErr);
			}

		} catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.getWardList() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * retrieves Special Ward List
	 * 
	 * @param voObj -
	 *           Value Object
	 */
	public static void getSplWardList(PatWalletTransVO voObj)
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_ward_list(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strDeptCode = voObj.getStrRaisingDepartment();

		String strErr = "";

		try
		{

			daoObj = new HisDAO("Cash Collection Transaction", "PatWalletTransDAO.getSplWardList");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "mode_val", "1", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 3);
			daoObj.setProcInValue(nProcIndex, "dept_code", strDeptCode, 2);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				voObj.setOfflineSplWardList(ws);

			} else
			{
				throw new Exception(strErr);
			}

		} catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.getSplWardList() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * retrieves Payment Mode List
	 * 
	 * @param voObj -
	 *           Value Object
	 */
	public static void getPaymentModeList(PatWalletTransVO voObj)
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_sblt_paymentmode_mst(?,?,?,?)}";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();

		String strErr = "";

		try
		{

			daoObj = new HisDAO("Billing", "PatWalletTransDAO.getPaymentModeList");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "2", 1);  // CHANGED FROM 1
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 2);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 3);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				voObj.setStrPaymentModeList(ws);

			} else
			{
				throw new Exception(strErr);
			}

		} catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.getPaymentModeList() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * retrieves Client Name List
	 * 
	 * @param voObj -
	 *           Value Object
	 */
	public static void getClientNameList(PatWalletTransVO voObj)
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_hblt_client_mst(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();

		String strErr = "";

		try
		{

			daoObj = new HisDAO("Billing", "PatWalletTransDAO.getClientNameList");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "1", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 2);
			daoObj.setProcInValue(nProcIndex, "client_no", "", 3);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				voObj.setStrClientNameList(ws);

			} else
			{
				throw new Exception(strErr);
			}

		} catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.getClientNameList() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * retrieves Remarks List
	 * 
	 * @param voObj -
	 *           Value Object
	 */
	public static void getRemarksList(PatWalletTransVO voObj)
	{

		String proc_name = "";

		proc_name = "{call PKG_SIMPLE_VIEW.PROC_HBLT_REMARKS_MST(?,?,?,?,?)}";

		HisDAO dao = null;

		int nprocIndex = 0;

		String strerr = "";
		String strHospCode = voObj.getStrHospitalCode();

		WebRowSet ws = null;

		try
		{

			dao = new HisDAO("Billing", "PatWalletTransDAO.getRemarksList()");

			nprocIndex = dao.setProcedure(proc_name);

			// set value

			dao.setProcInValue(nprocIndex, "rmkstype", "3", 1);
			dao.setProcInValue(nprocIndex, "modeVal", "1", 2);
			dao.setProcInValue(nprocIndex, "hosp_code", strHospCode, 3);
			dao.setProcOutValue(nprocIndex, "ERR", 1, 4); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 5); // 2 for object

			// execute procedure

			dao.executeProcedureByPosition(nprocIndex);

			// get value

			strerr = dao.getString(nprocIndex, "ERR");

			if (strerr == null)
				strerr = "";

			if (strerr.equals(""))
			{

				ws = dao.getWebRowSet(nprocIndex, "RESULTSET");

				if (ws.next())
				{
					voObj.setStrOffLineRemarks(ws.getString(2));

					ws.beforeFirst();
				}

				voObj.setOfflineRemarksList(ws);

				voObj.setStrMsgType("0");

			} else
			{

				throw new Exception(strerr);
			}

		} catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.getRemarksList() --> "

			+ e.getMessage());

			voObj.setStrMsgType("1");

		} finally
		{

			if (dao != null)
			{

				dao.free();

				dao = null;

			}

		}

	}

	/**
	 * retrieves Approver Employee List
	 * 
	 * @param voObj -
	 *           Value Object
	 */
	public static void getApprovedBy(PatWalletTransVO voObj)
	{

		String proc_name = "";
		proc_name = "{call pkg_simple_view.Proc_user_list(?,?,?,?,?)}";

		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		String strHospCode = voObj.getStrHospitalCode();
		String strSeatId = voObj.getStrSeatId();
		WebRowSet ws = null;

		try
		{

			dao = new HisDAO("Billing", "PatWalletTransDAO.getApprovedBy()");

			nprocIndex = dao.setProcedure(proc_name);

			// set value

			dao.setProcInValue(nprocIndex, "processId", "2", 1);
			dao.setProcInValue(nprocIndex, "seatId", strSeatId, 3);
			dao.setProcInValue(nprocIndex, "hosp_code", strHospCode, 2);
			dao.setProcOutValue(nprocIndex, "ERR", 1, 4); // 1 for string return

			// value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 5); // 2 for object

			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "ERR");

			if (strerr == null)
				strerr = "";

			if (strerr.equals(""))
			{

				ws = dao.getWebRowSet(nprocIndex, "RESULTSET");

				voObj.setOfflineApprovedByList(ws);

				voObj.setStrMsgType("0");

			} else
			{

				throw new Exception(strerr);

			}

		} catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.getApprovedBy() --> "

			+ e.getMessage());

			voObj.setStrMsgType("1");

		} finally
		{

			if (dao != null)
			{

				dao.free();
				dao = null;

			}

		}

	}

	/**
	 * retrieves Part Payment or Advance Amount
	 * 
	 * @param voObj -
	 *           Value Object
	 */
	public static void getPartPaymentOrAdvanceAmount(PatWalletTransVO voObj)

	{

		String proc_name = "";

		proc_name = "{call PKG_BILL_VIEW.proc_hblt_advance_mst (?,?,?,?,?,?,?,?)}";

		HisDAO dao = null;

		int nprocIndex = 0;

		String strerr = "";

		WebRowSet ws = null;

		String wardid = voObj.getStrOffLineWard();
		String patCatCode = voObj.getStrOffLineTreatmentCategory();
		String strHospCode = voObj.getStrHospitalCode();
		String splWardId = voObj.getStrOffLineSpecialWard();

		try
		{

			dao = new HisDAO("Cash Collection Transaction",
					"transactions.PatWalletTransDAO.getPartPaymentOrAdvanceAmount()");

			nprocIndex = dao.setProcedure(proc_name);

			// set value
			dao.setProcInValue(nprocIndex, "wardid", wardid, 1);
			dao.setProcInValue(nprocIndex, "catcode", patCatCode, 2);
			dao.setProcInValue(nprocIndex, "chargeId", "2", 3);
			dao.setProcInValue(nprocIndex, "modeval", "1", 6);
			dao.setProcInValue(nprocIndex, "hosp_code", strHospCode, 4);
			dao.setProcInValue(nprocIndex, "splwardid", splWardId, 5);
			dao.setProcOutValue(nprocIndex, "ERR", 1, 7); // 1 for string return
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 8); // 2 for object

			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "ERR");

			if (strerr == null)
				strerr = "";

			if (strerr.equals(""))
			{

				ws = dao.getWebRowSet(nprocIndex, "RESULTSET");

				if (ws != null)
					if (ws.next())
					{

						voObj.setStrOffLineAdvanceAmount(ws.getString(1));
						voObj.setStrOffLinePartPaymentAmount(ws.getString(2));
					}

				voObj.setStrMsgType("0");

			} else
			{

				throw new Exception(strerr);

			}

		}

		catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.getPartPaymentOrAdvanceAmount() --> " + e.getMessage());

			voObj.setStrMsgType("1");

		} finally
		{

			if (dao != null)
			{

				dao.free();
				dao = null;

			}

		}

	}

	/**
	 * retrieves Patient Account Details
	 * 
	 * @param voObj -
	 *           Value Object
	 */
	/*
	 * public static void
	 * getOffLineClientPatientNumber(PatWalletTransVO voObj) {
	 * 
	 * HisDAO daoObj = null; WebRowSet ws = null;
	 * 
	 * String strProcName = "{call
	 * pkg_bill_view.proc_hblt_client_patient_dtl(?,?,?,?)}"; int nProcIndex = 0;
	 * 
	 * String strAccNo = voObj.getStrAccountNo();
	 * 
	 * String strErr = "";
	 * 
	 * try {
	 * 
	 * if (strAccNo != null) {
	 * 
	 * daoObj = new HisDAO("Cash Collection Transaction",
	 * "PatWalletTransDAO");
	 * 
	 * nProcIndex = daoObj.setProcedure(strProcName);
	 * 
	 * daoObj.setProcInValue(nProcIndex, "accno", strAccNo);
	 * daoObj.setProcInValue(nProcIndex, "modeval", "1");
	 * daoObj.setProcOutValue(nProcIndex, "err", 1);
	 * daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2);
	 * 
	 * daoObj.executeProcedure(nProcIndex);
	 * 
	 * strErr = daoObj.getString(nProcIndex, "err");
	 * 
	 * if (strErr == null) strErr = "";
	 * 
	 * if (strErr.equals("")) {
	 * 
	 * ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
	 * 
	 * if (ws != null) { System.out.println(" ws VAl : "+ws.size()); if
	 * (ws.next()) {
	 * 
	 * voObj.setStrOffLineClientPatientNo(ws.getString(4)); System.out.println("
	 * Pat No : "+voObj.getStrOffLineClientPatientNo()); } } else {
	 * voObj.setStrOffLineClientPatientNo(null); } } else { throw new
	 * Exception(strErr); } } else {
	 * 
	 * voObj.setOnlineTariffDetails(null); } } catch (Exception e) {
	 * 
	 * voObj
	 * .setStrMsgString("PatWalletTransDAO.getOffLineClientPatientNumber()
	 * --> " + e.getMessage()); voObj.setStrMsgType("1"); } finally { if (daoObj !=
	 * null) { daoObj.free(); daoObj = null; } } }
	 */

	/**
	 * retrieves Client Patient Details using Client Patient Number.
	 * 
	 * @param voObj -
	 *           Value Object
	 */
	public static void getOffLineClientPatientDetailsWithClientPatNo(PatWalletTransVO voObj)
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_hblt_client_patient_dtl(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strClientPatNo = voObj.getStrOffLineClientPatientNo();
		String strChargeTypeId = voObj.getStrOffLineHospitalService();
		String strHospCode = voObj.getStrHospitalCode();
		String strBillNo = voObj.getStrOffLineBillNumber();
		String strErr = "";

		try
		{

			daoObj = new HisDAO("Billing", "PatWalletTransDAO.getOffLineClientPatientDetailsWithClientPatNo");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "clientpatno", strClientPatNo, 2);
			daoObj.setProcInValue(nProcIndex, "clientpatslno", "", 3);
			daoObj.setProcInValue(nProcIndex, "puk", "0", 1);
			daoObj.setProcInValue(nProcIndex, "chargetypeid", strChargeTypeId, 4);
			daoObj.setProcInValue(nProcIndex, "billNo", strBillNo, 6);
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 5);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospCode, 7);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 8);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 9);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				if (ws != null)
				{

					voObj.setOfflineClientDetails(ws);

				} else
				{
					voObj.setOfflineClientDetails(null);
				}

			} else
			{
				throw new Exception(strErr);
			}

		} catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.getOffLineClientPatientDetailsWithClientPatNo() --> " + e
					.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * retrieves Client Patient Details using Cr Number
	 * 
	 * @param voObj -
	 *           Value Object
	 */
	public static void getOffLineClientPatientDetailsWithCrNo(PatWalletTransVO voObj)
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_hblt_client_patient_dtl(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strCrNumber = voObj.getStrCrNo();
		String strChargeTypeId = voObj.getStrOffLineHospitalService();
		String strHospCode = voObj.getStrHospitalCode();
		String strBillNo = voObj.getStrOffLineBillNumber();
		String strErr = "";

		try
		{

			daoObj = new HisDAO("Billing", "PatWalletTransDAO.getOffLineClientPatientDetailsWithCrNo");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "puk", strCrNumber, 1);
			daoObj.setProcInValue(nProcIndex, "clientpatno", "", 2);
			daoObj.setProcInValue(nProcIndex, "clientpatslno", "", 3);
			daoObj.setProcInValue(nProcIndex, "chargetypeid", strChargeTypeId, 4);
			daoObj.setProcInValue(nProcIndex, "billNo", strBillNo, 6);
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 5);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospCode, 7);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 8);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 9);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				if (ws != null)
				{

					voObj.setOfflineClientDetails(ws);

				} else
				{
					voObj.setOfflineClientDetails(null);
				}

			} else
			{
				throw new Exception(strErr);
			}

		} catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.getOffLineClientPatientDetailsWithCrNo() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * retrieves Off Line Client Patient Details using Cr Number and Hospital
	 * Service
	 * 
	 * @param voObj -
	 *           Value Object
	 */
	public static void getOffLineClientPatientNumber(PatWalletTransVO voObj)
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_hblt_pataccount_dtl(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strCrNumber = voObj.getStrCrNo();
		String strChargeTypeId = voObj.getStrOffLineHospitalService();
		String strHospCode = voObj.getStrHospitalCode();
		String strErr = "";

		if (strChargeTypeId.equals(""))
			strChargeTypeId = "1";

		try
		{

			daoObj = new HisDAO("Billing", "PatWalletTransDAO.getOffLineClientPatientNumber");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "puk", strCrNumber, 2);
			daoObj.setProcInValue(nProcIndex, "accno", "", 1);
			daoObj.setProcInValue(nProcIndex, "activeAccount", "1", 4);
			daoObj.setProcInValue(nProcIndex, "chargetypeid", strChargeTypeId, 3);
			daoObj.setProcInValue(nProcIndex, "modeval", "2", 5);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospCode, 6);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 7);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 8);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				if (ws != null)
				{

					if (ws.next())
					{

						voObj.setStrOffLineClientPatientHidden(ws.getString(25));

					} else
					{
						voObj.setStrOffLineClientPatientHidden("");
					}

					ws.beforeFirst();

					voObj.setAdmissionCancellationDetails(ws);

				}

			} else
			{
				throw new Exception(strErr);
			}

		} catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.getOffLineClientPatientNumber() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * retrieves Off Line Refund Advance Patient Account Details using Cr Number
	 * and Hospital Service
	 * 
	 * @param voObj -
	 *           Value Object
	 */
	public static void getOffLineRefundAdvancePatAccDtls(PatWalletTransVO voObj)
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_hblt_pataccount_dtl(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strCrNumber = voObj.getStrCrNo();
		String strChargeTypeId = voObj.getStrOffLineHospitalService();
		String strHospCode = voObj.getStrHospitalCode();
		String strErr = "";

		try
		{

			daoObj = new HisDAO("Billing", "PatWalletTransDAO.getOffLineRefundAdvancePatAccDtls");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "puk", strCrNumber, 2);
			daoObj.setProcInValue(nProcIndex, "chargetypeid", strChargeTypeId, 3);
			daoObj.setProcInValue(nProcIndex, "accno", "", 1);
			daoObj.setProcInValue(nProcIndex, "activeAccount", "1", 4);
			daoObj.setProcInValue(nProcIndex, "modeval", "3", 5);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospCode, 6);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 7);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 8);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				if (ws != null)
				{

					voObj.setAdmissionCancellationDetails(ws);

				}

			} else
			{
				throw new Exception(strErr);
			}

		} catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.getOffLineClientPatientNumber() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * retrieves Off Line Group Combo
	 * 
	 * @param voObj -
	 *           ValueObject
	 */
	public static void getOffLineGroup(PatWalletTransVO voObj)
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strChargeTypeId = voObj.getStrOffLineHospitalService();
		String strIsPackageWise = voObj.getStrOffLineIsPackageGroup();
		String strProcName = "{call pkg_bill_view.proc_HBLT_HSERVICE_GROUP_MST(?,?,?,?,?,?)}";

		int nProcIndex = 0;

		String strErr = "";

		try
		{

			daoObj = new HisDAO("Cash Collection Transaction", "PatWalletTransDAO.getOffLineGroup");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 1);
			daoObj.setProcInValue(nProcIndex, "charge_id", strChargeTypeId, 2);
			daoObj.setProcInValue(nProcIndex, "pkg_wise_group", strIsPackageWise, 3);
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 4);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 5);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 6);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				if (ws != null)
				{

					if (strIsPackageWise.equals("0"))
					{
						voObj.setOfflineGroupList(ws);
					} else
					{
						voObj.setOfflinePackageGroup(ws);
					}

				}

			} else
			{
				throw new Exception(strErr);
			}

		} catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.getOffLineGroup() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * retrieves Off Line Tariff List
	 * 
	 * @param voObj -
	 *           ValueObject
	 */
	public static void getOffLineTariffList(PatWalletTransVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strGroupId = voObj.getStrOffLineGroup();
		String strChargeTypeId = voObj.getStrOffLineHospitalService();
		String strCategoryCode = voObj.getStrOffLineTreatmentCategory();
		String strWardCode = voObj.getStrOffLineWard();
		String strPackage = voObj.getStrOffLinePackageIndex();
		String strSearchLetter = voObj.getStrSearchLetter();

		if (strChargeTypeId == null)
			strChargeTypeId = "";
		if (strCategoryCode == null)
			strCategoryCode = "";

		if (strGroupId == null)
			strGroupId = "";
		if (strWardCode == null)
			strWardCode = "";

		if (strPackage == null || strPackage.equals(""))
			strPackage = "0";

		String strErr = "";
		String mode = "1";

		strPackage = "2";

		strProcName = "{call PKG_BILL_VIEW.PROC_HBLT_CHARGE_MST(?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		try
		{
			daoObj = new HisDAO("Billing", "AddServicesIPDTransDAO.getOffLineTariffList");
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "chargeTypeId", strChargeTypeId, 1);
			daoObj.setProcInValue(nProcIndex, "catCode", strCategoryCode, 2);
			daoObj.setProcInValue(nProcIndex, "wardCode", strWardCode, 3);
			daoObj.setProcInValue(nProcIndex, "groupId", strGroupId, 4);
			daoObj.setProcInValue(nProcIndex, "searchtrfname", strSearchLetter, 5);
			daoObj.setProcInValue(nProcIndex, "searchMode", "1", 6);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 7);
			daoObj.setProcInValue(nProcIndex, "pkg_flag", strPackage, 8);
			daoObj.setProcInValue(nProcIndex, "advance_flag", "1", 9);
			daoObj.setProcInValue(nProcIndex, "modeVal", mode, 10);
			daoObj.setProcInValue(nProcIndex, "end_date", "", 11);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 12);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 13);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (ws != null)
			{
				voObj.setOfflineTariffList(ws);
			}
		} 
		catch (Exception e)
		{
			voObj.setStrMsgString("PatWalletTransDAO.getOffLineTariffList() --> " + e.getMessage());
			voObj.setStrMsgType("1");
		} 
		finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * retrieves Off Line Tariff List
	 * 
	 * @param voObj -
	 *           ValueObject
	 */
	public static void getOffLineTariffListByCode(PatWalletTransVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
 
		String strProcName = "";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strGroupId = voObj.getStrOffLineGroup();
		String strChargeTypeId = voObj.getStrOffLineHospitalService();
		String strCategoryCode = voObj.getStrOffLineTreatmentCategory();
		String strWardCode = voObj.getStrOffLineWard();
		String strPackage = voObj.getStrOffLinePackageIndex();
		String strTariffCode = voObj.getStrTariffCode();

		if (strChargeTypeId == null)
			strChargeTypeId = "";
		if (strCategoryCode == null)
			strCategoryCode = "";

		if (strGroupId == null)
			strGroupId = "";
		if (strWardCode == null)
			strWardCode = "";

		String strErr = "";
		String mode = "3";
		strPackage = "2";
		strProcName = "{call PKG_BILL_VIEW.PROC_HBLT_CHARGE_MST(?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		try
		{
			daoObj = new HisDAO("Billing", "PatWalletTransDAO.getOffLineTariffListByCode");
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "chargeTypeId", strChargeTypeId, 1);
			daoObj.setProcInValue(nProcIndex, "catCode", strCategoryCode, 2);
			daoObj.setProcInValue(nProcIndex, "wardCode", strWardCode, 3);
			daoObj.setProcInValue(nProcIndex, "groupId", strGroupId, 4);
			daoObj.setProcInValue(nProcIndex, "searchtrfname", strTariffCode, 5);
			daoObj.setProcInValue(nProcIndex, "searchMode", "1", 6);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 7);
			daoObj.setProcInValue(nProcIndex, "pkg_flag", strPackage, 8);
			daoObj.setProcInValue(nProcIndex, "advance_flag", "1", 9);
			daoObj.setProcInValue(nProcIndex, "modeVal", mode, 10);
			daoObj.setProcInValue(nProcIndex, "end_date", "", 11);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 12);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 13);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			/*
			 * System.out.println("charge Type Id "+strChargeTypeId);
			 * System.out.println("categoryCode "+strCategoryCode);
			 * System.out.println("group code "+strGroupId);
			 * System.out.println("ward code "+strWardCode);
			 * System.out.println("hospitalCode "+strHospitalCode);
			 * System.out.println("pkgFlag "+strPackage);
			 */

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (ws != null)
			{
				voObj.setOfflineTariffList(ws);
			}
		} 
		catch (Exception e)
		{
			voObj.setStrMsgString("PatWalletTransDAO.getOffLineTariffListByCode() --> " + e.getMessage());
			voObj.setStrMsgType("1");
		} 
		finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * retrieves discount approval list
	 * 
	 * @param voObj
	 */
	public static void getOffLineDiscountApprovalList(PatWalletTransVO voObj)
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_simple_view.proc_consultant_name(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strHospCode = voObj.getStrHospitalCode();

		String strErr = "";

		try
		{

			daoObj = new HisDAO("Cash Collection Transaction", "PatWalletTransDAO.getOffLineDiscountApprovalList");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "deptunitCode", "", 1);
			daoObj.setProcInValue(nProcIndex, "seatId", "", 3);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospCode, 2);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				if (ws.next())
				{

					voObj.setStrOffLineWard(ws.getString(1));

					ws.beforeFirst();

				}

				voObj.setOfflineDiscountApprovedBy(ws);

			} else
			{
				throw new Exception(strErr);
			}

		} catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.getOffLineDiscountApprovalList() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * retrieves discount remarks list
	 * 
	 * @param voObj
	 */
	public static void getOffLineDiscountRemarksList(PatWalletTransVO voObj)
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_simple_view.proc_hblt_remarks_mst(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strHospCode = voObj.getStrHospitalCode();

		String strErr = "";

		try
		{

			daoObj = new HisDAO("Cash Collection Transaction", "PatWalletTransDAO.getOffLineDiscountRemarksList");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "rmkstype", "1", 1);
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospCode, 3);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				if (ws.next())
				{

					voObj.setStrOffLineWard(ws.getString(1));

					ws.beforeFirst();

				}

				voObj.setOfflineDiscountRemarks(ws);

			} else
			{
				throw new Exception(strErr);
			}

		} catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.getOffLineDiscountRemarksList() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * retrieves unit list based on unit id and hospital code
	 * 
	 * @param voObj
	 */
	public static void getOffLineTariffUnitList(PatWalletTransVO voObj)
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_GBLT_UNIT_MST(?,?,?,?,?)}";
		int nProcIndex = 0;

		// String strHospitalCode = voObj.getStrHospitalCode();

		String strErr = "";

		try
		{

			System.out.println(voObj.getStrOffLineTariffUnitTempId());
			daoObj = new HisDAO("Cash Collection Transaction", "PatWalletTransDAO.getOffLineTariffUnitList");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(), 1);
			daoObj.setProcInValue(nProcIndex, "unit_id", voObj.getStrOffLineTariffUnitTempId(), 2);
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 3);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				voObj.setOfflineTariffUnit(ws);

			} else
			{
				throw new Exception(strErr);
			}

		} catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.getOffLineTariffUnitList() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * retrieves Bill Details based on Cr Number and Charge Type Id.
	 * 
	 * @param voObj -
	 *           Value Object.
	 */
	public static void getBillDetails(PatWalletTransVO voObj)
	{
		String proc_name = "";
		proc_name = "{call pkg_bill_view.proc_SBLT_OUTBOUND_DTL(?,?,?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		WebRowSet ws = null;

		try
		{
			dao = new HisDAO("billing", "transactions.PatWalletTransDAO.getBillDetails()");

			/*
			 * System.out.println("cr No : "+voObj.getStrCrNo());
			 * System.out.println("charge Type ;
			 * "+voObj.getStrOffLineHospitalService());
			 */

			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "crNo", voObj.getStrCrNo(), 1);
			dao.setProcInValue(nprocIndex, "chargeTypeId", voObj.getStrOffLineHospitalService(), 3);
			dao.setProcInValue(nprocIndex, "modeval", "1", 9);
			dao.setProcInValue(nprocIndex, "hosp_code", voObj.getStrHospitalCode(), 10);
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "1", 2);
			dao.setProcInValue(nprocIndex, "searchStr", "", 5);
			dao.setProcInValue(nprocIndex, "patListType", "0", 4);
			dao.setProcInValue(nprocIndex, "searchType", "1", 6);
			dao.setProcInValue(nprocIndex, "frmRows", "0", 7);
			dao.setProcInValue(nprocIndex, "toRows", "0", 8);
			dao.setProcOutValue(nprocIndex, "err", 1, 11); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2, 12); // 2 for object
			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "err");
			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = dao.getWebRowSet(nprocIndex, "resultset");
				// System.out.println("ws size is-->"+ws.size());
				voObj.setOfflineBillList(ws);
			} else
			{
				throw new Exception(strErr);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			voObj.setStrMsgString("PatWalletTransDAO.getBillDetails() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (dao != null)
			{
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * retrieves Bill Details based on Cr Number and Charge Type Id.
	 * 
	 * @param voObj -
	 *           Value Object.
	 */
	public static void getPartPayBillDetails(PatWalletTransVO voObj)
	{
		String proc_name = "";
		proc_name = "{call pkg_bill_view.proc_SBLT_OUTBOUND_DTL(?,?,?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		WebRowSet ws = null;

		try
		{
			dao = new HisDAO("billing", "transactions.PatWalletTransDAO.getPartPayBillDetails()");

			/*
			 * System.out.println("cr No : "+voObj.getStrCrNo());
			 * System.out.println("charge Type ;
			 * "+voObj.getStrOffLineHospitalService());
			 */

			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "crNo", voObj.getStrCrNo(), 1);
			dao.setProcInValue(nprocIndex, "chargeTypeId", voObj.getStrOffLineHospitalService(), 3);
			dao.setProcInValue(nprocIndex, "modeval", "6", 9);
			dao.setProcInValue(nprocIndex, "hosp_code", voObj.getStrHospitalCode(), 10);
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "1", 2);
			// dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "0");
			dao.setProcInValue(nprocIndex, "searchStr", "", 5);
			dao.setProcInValue(nprocIndex, "patListType", "0", 4);
			dao.setProcInValue(nprocIndex, "searchType", "1", 6);
			dao.setProcInValue(nprocIndex, "frmRows", "0", 7);
			dao.setProcInValue(nprocIndex, "toRows", "0", 8);
			dao.setProcOutValue(nprocIndex, "err", 1, 11); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2, 12); // 2 for object

			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);

			// get value
			strErr = dao.getString(nprocIndex, "err");
			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = dao.getWebRowSet(nprocIndex, "resultset");
				// System.out.println("ws size is-->"+ws.size());
				voObj.setOfflineBillList(ws);
			} else
			{
				throw new Exception(strErr);
			}
		} catch (Exception e)
		{
			voObj.setStrMsgString("PatWalletTransDAO.getBillDetails() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (dao != null)
			{
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * retrieves Tariff Details based on Bill Number.
	 * 
	 * @param voObj -
	 *           Value Object.
	 */
	public static void getTariffDetails_NoAcc(PatWalletTransVO voObj)
	{
		WebRowSet ws = null;
		String proc_name = "";
		proc_name = "{call PKG_BILL_VIEW.PROC_HBLT_BILL_SERVICE_DTL(?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		try
		{
			dao = new HisDAO("billing", "transactions.PatWalletTransDAO.getTariffDetails_NoAcc()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "billNo ", voObj.getStrOffLineBillNumber(), 1);
			dao.setProcInValue(nprocIndex, "modeval", "1", 3);
			dao.setProcInValue(nprocIndex, "hosp_code", voObj.getStrHospitalCode(), 4);
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "1", 2);
			dao.setProcInValue(nprocIndex, "recNo", "0", 5);
			dao.setProcInValue(nprocIndex, "recType", "1", 6);
			dao.setProcOutValue(nprocIndex, "ERR", 1, 7); // 1 for string return
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 8); // 2 for object
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{

				ws = dao.getWebRowSet(nprocIndex, "RESULTSET");

				voObj.setOfflineBillTariffList(ws);
			} else
			{
				voObj.setOfflineBillTariffList(null);
				throw new Exception(strErr);
			}

		} catch (Exception e)
		{
			voObj.setStrMsgString("PatWalletTransDAO.getTariffDetails_NoAcc() --> " + e.getMessage());
			voObj.setStrMsgType("1");
		} finally
		{
			if (dao != null)
			{
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * retrieves Tariff Details based on Bill Number and Account Number.
	 * 
	 * @param voObj
	 */
	public static void getTariffDetails_Acc(PatWalletTransVO voObj)
	{
		WebRowSet ws = null;

		String proc_name = "";
		proc_name = "{call PKG_BILL_VIEW.PROC_HBLT_PATACCOUNT_SERVICE(?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		try
		{
			dao = new HisDAO("billing", "transactions.PatWalletTransDAO .getTariffDetails_Acc()");

			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "billNo ", voObj.getStrOffLineBillNumber(), 1);
			dao.setProcInValue(nprocIndex, "accNo ", voObj.getStrOffLineAccountNo(), 2);
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "1", 3);
			dao.setProcInValue(nprocIndex, "groupId", "", 4);
			dao.setProcInValue(nprocIndex, "modeval", "1", 5);
			dao.setProcInValue(nprocIndex, "hosp_code", voObj.getStrHospitalCode(), 6);
			dao.setProcInValue(nprocIndex, "recNo", "0", 7);
			dao.setProcInValue(nprocIndex, "recType", "1", 8);
			dao.setProcOutValue(nprocIndex, "ERR", 1, 9); 
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 10); 
			dao.executeProcedureByPosition(nprocIndex);
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
				voObj.setOfflineBillTariffList(ws);
			} 
			else
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e)
		{
			voObj.setStrMsgString("PatWalletTransDAO.getTariffDetails_Acc() --> " + e.getMessage());
			voObj.setStrMsgType("1");
		} 
		finally
		{
			if (dao != null)
			{
				dao.free();
				dao = null;
			}
		}
	}

	public static void getRequestNoList(PatWalletTransVO voObj)
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_sblt_inbound_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try
		{

			daoObj = new HisDAO("Cash Collection Transaction", "PatWalletTransDAO.getRequestNoList");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "mode_type", "5", 1);
			daoObj.setProcInValue(nProcIndex, "seatId", "0", 11);
			daoObj.setProcInValue(nProcIndex, "crno", "", 2);
			daoObj.setProcInValue(nProcIndex, "chargeTypeId", "", 3);
			daoObj.setProcInValue(nProcIndex, "patListType", "", 4);
			daoObj.setProcInValue(nProcIndex, "searchStr", "", 5);
			daoObj.setProcInValue(nProcIndex, "searchType", "", 6);
			daoObj.setProcInValue(nProcIndex, "frmRows", "", 7);
			daoObj.setProcInValue(nProcIndex, "toRows", "", 8);
			daoObj.setProcInValue(nProcIndex, "deptCode", "", 10);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(), 9);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 12);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 13);
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				voObj.setStrRequestNoList(ws);

			} else
			{
				throw new Exception(strErr);
			}

		} catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.getRequestNoList() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * retrieves Bill Listing Details from Out Bound Table
	 * 
	 * @param voObj
	 */
	public static void getBillListingDtl_from_OutBound(PatWalletTransVO voObj)
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strErr = "";

		String strSearchString = voObj.getStrBillSearchString();

		String strFromRows = voObj.getStrBillFromRow();
		String strToRows = voObj.getStrBillToRow();
		String strModeType = "5";

		if (strSearchString == null || strSearchString.equals(""))
			strSearchString = "";

		String strProcName = "{call pkg_bill_view.Proc_Sblt_Outbound_Dtl(?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		try
		{
			daoObj = new HisDAO("Cash Collection Trans ", "PatWalletTransDAO.getBillListingDtl_from_OutBound");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", strModeType, 9);
			daoObj.setProcInValue(nProcIndex, "searchStr", strSearchString, 5);
			daoObj.setProcInValue(nProcIndex, "frmRows", strFromRows, 7);
			daoObj.setProcInValue(nProcIndex, "toRows", strToRows, 8);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(), 10);
			daoObj.setProcInValue(nProcIndex, "crNo", "", 1);
			daoObj.setProcInValue(nProcIndex, "to_be_refund_pkg", "", 2);
			daoObj.setProcInValue(nProcIndex, "chargeTypeId", "", 3);
			daoObj.setProcInValue(nProcIndex, "patListType", "", 4);
			daoObj.setProcInValue(nProcIndex, "searchType", "", 6);
			daoObj.setProcOutValue(nProcIndex, "ERR", 1, 11);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 12);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "ERR");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				voObj.setBillSearchList(ws);

			} else
			{
				throw new Exception(strErr);
			}

		} catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.setPatientListingDtl_from_OutBound() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}

	// Insert Methods

	/**
	 * Inserts Off-line Advance Details
	 * 
	 * @param voObj
	 */
	public static void insertWalletAdvance(PatWalletTransVO voObj)
	{

		int nAccFlag = 0;
		int nBillFlag = 0;
		int nAppFlag = 0;

		boolean fClientFlag = false;

		String strAccountNo = "0";
		String strBillNo = "0";
		String strAppId = "0";

		String counterId = "0";

		HisDAO dao = null;

		PrimaryKeyDAO pKey = null;
		PrimaryKeyLogDAO pKeyLogDao = null;
		PatientAccountDAO patAccDao = null;
		//ClientPatientDAO clientPatDao = null;
		BillDAO billDao = null;
		BillReceiptDAO billReceiptDao = null;
		PatientAccountServiceDAO patAccSerDao = null;
		OutBoundDAO outBoundDao = null;
		PaymentDAO paymentDao = null;
		BillRequisitionTariffDAO billReqTrfDao = null;
		ApprovalDAO approvalDao = null;

		try
		{

			dao = new HisDAO("Bill Transaction", "PatWalletTransDAO.insertOfflineAdvance");
			pKey = new PrimaryKeyDAO();
			pKeyLogDao = new PrimaryKeyLogDAO();
			patAccDao = new PatientAccountDAO();
			//clientPatDao = new ClientPatientDAO();
			billDao = new BillDAO();
			billReceiptDao = new BillReceiptDAO();
			patAccSerDao = new PatientAccountServiceDAO();
			outBoundDao = new OutBoundDAO();
			paymentDao = new PaymentDAO();
			billReqTrfDao = new BillRequisitionTariffDAO();
			approvalDao = new ApprovalDAO();

			HisUtil hisUtil = new HisUtil("Bill Transaction", "PatWalletTransDAO");

			voObj.setStrCurrentDate(hisUtil.getASDate("dd-MMM-yyyy"));

			String strTemp = voObj.getStrOffLineEpisode().replace("^", "#").split("#")[0];

			voObj.setStrOffLineEpisode(strTemp);

			// Counter Id Generation.

			PatWalletTransDAO.getCounterId(voObj);

			counterId = voObj.getStrCounterId().replace("^", "#").split("#")[0];

			// Open or Retive Existing Account.
			strAccountNo = voObj.getStrOffLineAccountNo();

			if (strAccountNo == null || strAccountNo.equals("0"))
			{

				pKey.setStrBlockkey("1");
				pKey.setStrKeyname("ACCOUNT");
				pKey.setStrHospCode(voObj.getStrHospitalCode());

				pKey.select(dao);

				strAccountNo = pKey.getStrPrimrayKeyValue();

				nAccFlag = 1;

			}
			if (voObj.getStrClientPatientNo().equals(""))
				voObj.setStrClientPatientNo("0");

			if (!voObj.getStrClientPatientNo().equals("0"))
			{
				fClientFlag = true;
			}
			voObj.setStrOffLineTreatmentCategory(voObj.getStrOffLineTreatmentCategory().split("\\^")[0]);
			
			patAccDao.setStrPatAccNo(strAccountNo);
			patAccDao.setStrChargeTypeId(voObj.getStrChargeType());//voObj.getStrOffLineHospitalService());
			patAccDao.setStrDeptCode(voObj.getStrOffLineRaisingDepartment());
			patAccDao.setStrPuk(voObj.getStrCrNo());
			//patAccDao.setStrAccountStatus("1");

			if (fClientFlag)
			{
				patAccDao.setStrClientPatientNo(voObj.getStrOffLineClientPatientNo());
				patAccDao.setStrSancAmt(voObj.getStrOffLineSancAmount());
				patAccDao.setStrClientBalance(voObj.getStrOffLineBalanceAmount());
				patAccDao.setStrClientPatBoundDt(voObj.getStrCurrentDate());
			}

			patAccDao.setStrAdmNo(voObj.getStrOffLineAdmissionNo());
			patAccDao.setStrEpisodeCode(voObj.getStrOffLineEpisode());
			patAccDao.setStrCatCode(voObj.getStrOffLineTreatmentCategory());

			patAccDao.setStrPatRecdAmt(voObj.getStrAdvacnceAmount());//getStrOfflineTotalRecAmount());
			patAccDao.setStrPatAccountStatus("1");
			patAccDao.setStrSeatId(voObj.getStrSeatId());
			patAccDao.setStrHospitalCode(voObj.getStrHospitalCode());
			patAccDao.setStrIsValid("1");
			patAccDao.setStrMobileNo(voObj.getStrMobileNo1());
			patAccDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
			patAccDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));
			patAccDao.setStrAccType("2");
			patAccDao.insert(dao);
			

			//if (fClientFlag)
			//{

			//	clientPatDao.setStrClientPatNo(voObj.getStrOffLineClientPatientNo());

			//	clientPatDao.insert(dao);
			//}

			// Bill Generation.

			pKey.setStrBlockkey("1");
			pKey.setStrKeyname("BILL");
			pKey.setStrHospCode(voObj.getStrHospitalCode());

			pKey.select(dao);

			strBillNo = pKey.getStrPrimrayKeyValue();

			voObj.setStrBillNo(strBillNo);

			nBillFlag = 1;

			billDao.setStrBillNo(strBillNo);
			billDao.setStrPatAccNo(strAccountNo);
			if (fClientFlag)
			{

				billDao.setStrClientPatNo(voObj.getStrOffLineClientPatientNo());
				billDao.setStrSancAmt(voObj.getStrOffLineSancAmount());
				billDao.setStrClientBalance(voObj.getStrOffLineBalanceAmount());
			}
			billDao.setStrAdmNo(voObj.getStrOffLineAdmissionNo());
			billDao.setStrBServiceId("30");//voObj.getStrOffLineBillingService());
			billDao.setStrDeptCode(voObj.getStrOffLineRaisingDepartment());
			billDao.setStrPatientCatCode(voObj.getStrOffLineTreatmentCategory());
			billDao.setStrEpisodeCode(voObj.getStrOffLineEpisode());
			billDao.setStrChargeTypeId(voObj.getStrChargeType());//voObj.getStrOffLineHospitalService());
			billDao.setStrPuk(voObj.getStrCrNo());

			billDao.setStrRemarks(voObj.getStrOffLineRemarks());
			billDao.setStrPatientNetAmt(voObj.getStrAdvacnceAmount());//getStrOfflineTotalRecAmount());

			billDao.setStrStatus("1");
			billDao.setStrSeatId(voObj.getStrSeatId());
			billDao.setStrIsValid("1");
			billDao.setStrWardCode(voObj.getStrOffLineSpecialWard());
			billDao.setStrIpdChargeTypeId(voObj.getStrOffLineWard());//voObj.getStrOffLineWard());
			billDao.setStrHospitalCode(voObj.getStrHospitalCode());
			billDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
			billDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));

			billDao.insert(dao);

			billReceiptDao.setStrBillNo(strBillNo);
			billReceiptDao.setStrReceiptNo("1");
			billReceiptDao.setStrReceiptType(voObj.getStrOffLineRequestType());
			billReceiptDao.setStrPuk(voObj.getStrCrNo());
			billReceiptDao.setStrAdmNo(voObj.getStrOffLineAdmissionNo());
			billReceiptDao.setStrPatAccNo(strAccountNo);
			if (fClientFlag)
			{

				billReceiptDao.setStrClientPatNo(voObj.getStrOffLineClientPatientNo());

				billReceiptDao.setStrClientBalance(voObj.getStrOffLineBalanceAmount());
			}

			billReceiptDao.setStrChargeTypeId(voObj.getStrChargeType());//voObj.getStrOffLineHospitalService());
			billReceiptDao.setStrBServiceId("30");//voObj.getStrOffLineBillingService());
			billReceiptDao.setStrDeptCode(voObj.getStrOffLineRaisingDepartment());
			billReceiptDao.setStrPatientCatCode(voObj.getStrOffLineTreatmentCategory());
			billReceiptDao.setStrPatientTAmt(voObj.getStrAdvacnceAmount());

			billReceiptDao.setStrCounterId(counterId);

			if (voObj.getStrChk_value().equals("1"))
			{

				pKey.setStrBlockkey("1");
				pKey.setStrKeyname("APPROVAL");
				pKey.setStrHospCode(voObj.getStrHospitalCode());

				pKey.select(dao);

				strAppId = pKey.getStrPrimrayKeyValue();

				nAppFlag = 1;

				billReceiptDao.setStrApprovalId(strAppId);
				billReceiptDao.setStrApprovedBy(voObj.getStrOffLineApprovedBy());
				billReceiptDao.setStrApprovedDate(voObj.getStrCurrentDate());
				billReceiptDao.setStrRemarks(voObj.getStrOffLineRemarks());
			}
			billReceiptDao.setStrStatus("1");
			billReceiptDao.setStrSeatId(voObj.getStrSeatId());
			billReceiptDao.setStrIsValid("1");
			billReceiptDao.setStrWardCode(voObj.getStrOffLineSpecialWard());
			billReceiptDao.setStrIpdChargeTypeId(voObj.getStrOffLineWard());//voObj.getStrOffLineWard());
			billReceiptDao.setStrIsOnline("0");
			billReceiptDao.setStrHospitalCode(voObj.getStrHospitalCode());
			billReceiptDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
			billReceiptDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));

			billReceiptDao.insert(dao);

			patAccSerDao.setStrPatAccountNo(strAccountNo);
			patAccSerDao.setStrReqNo(strBillNo);
			patAccSerDao.setStrChargeTypeId(voObj.getStrChargeType());//voObj.getStrOffLineHospitalService());
			patAccSerDao.setStrTariffId("0");
			patAccSerDao.setStrDeptCode(voObj.getStrOffLineRaisingDepartment());
			patAccSerDao.setStrReqType("1");
			patAccSerDao.setStrGroupId(voObj.getStrGroupId());
			patAccSerDao.setStrRateUnitCode(voObj.getStrQtyUnitId());
			patAccSerDao.setStrBServiceId("30");//voObj.getStrOffLineBillingService());
			patAccSerDao.setStrIpdChargeTypeId(voObj.getStrOffLineWard());//voObj.getStrOffLineWard());
			// patAccSerDao.setStrTariffRate(String.valueOf(0 -
			// Integer.parseInt(voObj.getStrOffLineAdvanceAmount())));
			patAccSerDao.setStrTariffRate(voObj.getStrAdvacnceAmount());
			patAccSerDao.setStrCatCode(voObj.getStrOffLineTreatmentCategory());
			patAccSerDao.setStrBillQty("1");
			patAccSerDao.setStrProcessedQty("0");
			patAccSerDao.setStrRemainedQty("1");
			patAccSerDao.setStrPuk(voObj.getStrCrNo());
			patAccSerDao.setStrRemarks(voObj.getStrOffLineRemarks());
			patAccSerDao.setStrSeatId(voObj.getStrSeatId());
			patAccSerDao.setStrIsValid("1");
			patAccSerDao.setStrIsRefundable("0");
			patAccSerDao.setStrQtyUnitId(voObj.getStrQtyUnitId());
			patAccSerDao.setStrBillFlag("1");
			patAccSerDao.setStrReceiptNo("1");
			patAccSerDao.setStrWardCode(voObj.getStrOffLineSpecialWard());
			patAccSerDao.setStrStatus("1");
			// patAccSerDao.setStrNetAmt(String.valueOf(0 -
			// Integer.parseInt(voObj.getStrOffLineAdvanceAmount())));
			patAccSerDao.setStrNetAmt(voObj.getStrAdvacnceAmount());
			patAccSerDao.setStrHospitalCode(voObj.getStrHospitalCode());
			patAccSerDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
			patAccSerDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));

			patAccSerDao.insert(dao);
			
			/*
			 * As Per Billing Expert Comment Surcharge is Not Applicable 
			 * 
			 if(!voObj.getStrOfflineTotalRecAmount().equals("0"))
			{
				patAccSerDao.setStrPatAccountNo(strAccountNo);
				patAccSerDao.setStrReqNo(strBillNo);
				patAccSerDao.setStrChargeTypeId(voObj.getStrChargeType());//voObj.getStrOffLineHospitalService());
				patAccSerDao.setStrTariffId("1090001"); //Surcharge Tariff for Card based payments
				patAccSerDao.setStrDeptCode(voObj.getStrOffLineRaisingDepartment());
				patAccSerDao.setStrReqType("1");
				patAccSerDao.setStrGroupId("109");
				patAccSerDao.setStrRateUnitCode(voObj.getStrQtyUnitId());
				patAccSerDao.setStrBServiceId("11");//voObj.getStrOffLineBillingService());
				patAccSerDao.setStrIpdChargeTypeId(voObj.getStrOffLineWard());//voObj.getStrOffLineWard());
				// patAccSerDao.setStrTariffRate(String.valueOf(0 -
				// Integer.parseInt(voObj.getStrOffLineAdvanceAmount())));
				patAccSerDao.setStrTariffRate(voObj.getStrOfflineTotalRecAmount());
				patAccSerDao.setStrCatCode(voObj.getStrOffLineTreatmentCategory());
				patAccSerDao.setStrBillQty("1");
				patAccSerDao.setStrProcessedQty("0");
				patAccSerDao.setStrRemainedQty("1");
				patAccSerDao.setStrPuk(voObj.getStrCrNo());
				patAccSerDao.setStrRemarks("Surcharge Tariff");
				patAccSerDao.setStrSeatId(voObj.getStrSeatId());
				patAccSerDao.setStrIsValid("1");
				patAccSerDao.setStrIsRefundable("0");
				patAccSerDao.setStrQtyUnitId(voObj.getStrQtyUnitId());
				patAccSerDao.setStrBillFlag("1");
				patAccSerDao.setStrReceiptNo("1");
				patAccSerDao.setStrWardCode(voObj.getStrOffLineSpecialWard());
				patAccSerDao.setStrStatus("1");
				// patAccSerDao.setStrNetAmt(String.valueOf(0 -
				// Integer.parseInt(voObj.getStrOffLineAdvanceAmount())));
				patAccSerDao.setStrNetAmt(voObj.getStrOfflineTotalRecAmount());
				patAccSerDao.setStrHospitalCode(voObj.getStrHospitalCode());
				patAccSerDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
				patAccSerDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));

				patAccSerDao.insert(dao);
			}
			*/

			outBoundDao.setStrTransNo(strBillNo);
			outBoundDao.setStrReceiptNo("1");
			outBoundDao.setStrPatAccNo(strAccountNo);
			outBoundDao.setStrPuk(voObj.getStrCrNo());
			outBoundDao.setStrAdmNo(voObj.getStrOffLineAdmissionNo());
			outBoundDao.setStrEpisodeCode(voObj.getStrOffLineEpisode());
			outBoundDao.setStrPatientCatCode(voObj.getStrOffLineTreatmentCategory());
			outBoundDao.setStrTransAmt(voObj.getStrAdvacnceAmount());
			outBoundDao.setStrChargeTypeId(voObj.getStrChargeType());//voObj.getStrOffLineHospitalService());
			outBoundDao.setStrIpdChargeTypeId(voObj.getStrOffLineWard());//voObj.getStrOffLineWard());
			outBoundDao.setStrCounterId(counterId);
			outBoundDao.setStrBServiceId("30");//voObj.getStrOffLineBillingService());
			outBoundDao.setStrStatus("1");
			outBoundDao.setStrRemarks(voObj.getStrOffLineRemarks());
			outBoundDao.setStrDayEndFlag("0");
			outBoundDao.setStrSeatId(voObj.getStrSeatId());
			outBoundDao.setStrIsValid("1");
			outBoundDao.setStrTransType("1");
			outBoundDao.setStrWardCode(voObj.getStrOffLineSpecialWard());
			outBoundDao.setStrIsBill("1");
			outBoundDao.setStrIsOnline("0");
			outBoundDao.setStrHospitalCode(voObj.getStrHospitalCode());
			outBoundDao.setStrDeptCode(voObj.getStrOffLineRaisingDepartment());
			outBoundDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
			outBoundDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));

			outBoundDao.insert(dao);

			// Payment Details

			int nSlNo = 0;
			int nPaymentLenght = voObj.getStrOfflinePaymentMode().length;
            int i=0;
			//for (int i = 0; i < nPaymentLenght; i++)
			//{

				nSlNo = nSlNo + 1;

				paymentDao.setStrBillNo(strBillNo);

				paymentDao.setStrPaymentMode((voObj.getStrOfflinePaymentMode()[i]).split("#")[0]);
				paymentDao.setStrRecieptNo("1");
				paymentDao.setStrSerialNo(String.valueOf(nSlNo));
				paymentDao.setStrChargeTypeId(voObj.getStrChargeType());//voObj.getStrOffLineHospitalService());
				paymentDao.setStrBServiceId("30");//voObj.getStrOffLineBillingService());

				String payDtl[] = { "", "" };

				if (voObj.getStrOfflinePaymentDtls() != null)
				{
					paymentDao.setStrPaymentDetails(voObj.getStrOfflinePaymentDtls()[i]);
					payDtl = voObj.getStrOfflinePaymentDtls()[i].replace(",", "#").split("#");
				} else
				{
					paymentDao.setStrPaymentDetails("");
				}

				if (voObj.getStrOfflinePaymentMode()[i].equals("2") || voObj.getStrOfflinePaymentMode()[i].equals("3"))
				{
					paymentDao.setStrPaymentModeNo(payDtl[1]);
					paymentDao.setStrPaymentStatus("0");

				} else if (voObj.getStrOfflinePaymentMode()[i].equals("4") || voObj.getStrOfflinePaymentMode()[i]
						.equals("5"))
				{
					paymentDao.setStrPaymentModeNo(payDtl[1]);
					paymentDao.setStrPaymentStatus("0");

				} else if (voObj.getStrOfflinePaymentMode()[i].equals("6"))
				{

					paymentDao.setStrPaymentModeNo(payDtl[0]);
				} else
				{
					paymentDao.setStrPaymentModeNo("");
					paymentDao.setStrPaymentStatus("1");
				}

				paymentDao.setStrRecieptType("1");
				
				paymentDao.setStrRecieptAmount(voObj.getStrAdvacnceAmount());

				paymentDao.setStrPuk(voObj.getStrCrNo());
				paymentDao.setStrProcessedBy("0");
				paymentDao.setStrCounterId(counterId);
				paymentDao.setStrSeatId(voObj.getStrSeatId());
				paymentDao.setStrIsValid("1");
				paymentDao.setStrHospitalCode(voObj.getStrHospitalCode());
				paymentDao.setStrNetAmount(voObj.getStrAdvacnceAmount());
				paymentDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
				paymentDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));
				System.out.println("voObj.getStrOfflineTotalRecAmount()>>>>>>>>>>>>>>>"+voObj.getStrAdvacnceAmount());
				paymentDao.insert(dao);
			//}

			/*
			 * if (voObj.getStrOffLineClientPatientNo().length() > 1 &&
			 * !voObj.getStrOfflineMaxClientBenefitAmount().equals("0")) {
			 * 
			 * nSlNo = nSlNo + 1;
			 * 
			 * paymentDao.setStrBillNo(strBillNo);
			 * 
			 * paymentDao.setStrPaymentMode("6"); paymentDao.setStrRecieptNo("1");
			 * paymentDao.setStrSerialNo(String.valueOf(nSlNo));
			 * paymentDao.setStrChargeTypeId(voObj
			 * .getStrOffLineHospitalService()); paymentDao
			 * .setStrBServiceId(voObj.getStrOffLineBillingService()); paymentDao
			 * .setStrPaymentDetails(voObj.getStrOfflineClientName());
			 * paymentDao.setStrPaymentModeNo(voObj
			 * .getStrOffLineClientPatientNo());
			 * paymentDao.setStrPaymentStatus("0");
			 * paymentDao.setStrRecieptType("1");
			 * paymentDao.setStrRecieptAmount(voObj
			 * .getStrOfflineMaxClientBenefitAmount());
			 * paymentDao.setStrPuk(voObj.getStrCrNo());
			 * paymentDao.setStrProcessedBy("0");
			 * paymentDao.setStrCounterId(counterId);
			 * paymentDao.setStrSeatId(voObj.getStrSeatId());
			 * paymentDao.setStrHospitalCode(voObj.getStrHospitalCode());
			 * paymentDao.setStrIsValid("1");
			 * paymentDao.setStrNetAmount(voObj.getStrOfflineTotalRecAmount());
			 * 
			 * paymentDao.insert(dao); }
			 */

			// Approval Details
			if (voObj.getStrChk_value().equals("1"))
			{

				approvalDao.setStrApprovalId(strAppId);
				approvalDao.setStrApprovalSlNo("1");
				approvalDao.setStrReceiptType("1");
				approvalDao.setStrBServiceId("19");//);
				approvalDao.setStrChargeTypeId(voObj.getStrChargeType());//voObj.getStrOffLineHospitalService());
				approvalDao.setStrEmpNo(voObj.getStrApprovedByCombo());
				approvalDao.setStrUserLevel(voObj.getStrUserLevel());
				approvalDao.setStrRemarks(voObj.getStrRemarksCombo2());
				approvalDao.setStrApprovalType("5");
				approvalDao.setStrStatus("1");
				approvalDao.setStrSeatId(voObj.getStrSeatId());
				approvalDao.setStrIsValid("1");
				approvalDao.setStrHospitalCode(voObj.getStrHospitalCode());

				approvalDao.insert(dao);

			}

			String proc_name1 = "";

			proc_name1 = "{call PKG_BILL_DML.dml_update_account_dtl(?,?,?,?,?,?,?,?)}";

			int procIndex1 = 0;

			procIndex1 = dao.setProcedure(proc_name1);

			dao.setProcInValue(procIndex1, "modval", "1", 1);
			dao.setProcInValue(procIndex1, "hosp_code", voObj.getStrHospitalCode(), 2);
			dao.setProcInValue(procIndex1, "accNo", strAccountNo, 3);
			dao.setProcInValue(procIndex1, "wardId", voObj.getStrOffLineSpecialWard(), 4);
			dao.setProcInValue(procIndex1, "ipdChargeTypeId", voObj.getStrOffLineWard(), 5);
			dao.setProcInValue(procIndex1, "patCat", voObj.getStrOffLineTreatmentCategory(), 6);
			dao.setProcInValue(procIndex1, "deptId", voObj.getStrOffLineRaisingDepartment(), 7);
			dao.setProcOutValue(procIndex1, "ERR", 1, 8); // 1 for string return
			dao.execute(procIndex1, 1);
			synchronized (dao)
			{
				dao.fire();
			}

		} catch (Exception e)
		{

			String err = "err:" + e.getMessage();

			voObj.setStrMsgType("1");
			voObj.setStrMsgString("PatWalletTransDAO.insertOfflineAdvance() --> BILL_IPD -->" + err);

			if (nAccFlag == 1)
			{

				try
				{
					pKeyLogDao.setStrKeyname("ACCOUNT");
					pKeyLogDao.setStrStartkey(strAccountNo);
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrError(err);
					pKeyLogDao.setStrHospCode(voObj.getStrHospitalCode());
					pKeyLogDao.setStrSeatid(voObj.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);

				} catch (Exception e1)
				{

					voObj
							.setStrMsgString("PatWalletTransDAO.insertOfflineAdvance()--> " + e1.getMessage() + "-->" + err);
					voObj.setStrMsgType("1");

				}

			}
			if (nBillFlag == 1)
			{

				voObj.setStrBillNo("0");

				try
				{

					pKeyLogDao.setStrKeyname("BILL_IPD");
					pKeyLogDao.setStrStartkey(strBillNo);
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrError(err);
					pKeyLogDao.setStrHospCode(voObj.getStrHospitalCode());
					pKeyLogDao.setStrSeatid(voObj.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);

				} catch (Exception e1)
				{

					voObj
							.setStrMsgString("PatWalletTransDAO.insertOfflineAdvance()--> " + e1.getMessage() + "-->" + err);
					voObj.setStrMsgType("1");

				}

			}

			if (nAppFlag == 1)
			{

				try
				{
					pKeyLogDao.setStrKeyname("APPROVAL");
					pKeyLogDao.setStrStartkey(strAppId);
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrError(err);
					pKeyLogDao.setStrHospCode(voObj.getStrHospitalCode());
					pKeyLogDao.setStrSeatid(voObj.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);

				} catch (Exception e1)
				{

					voObj
							.setStrMsgString("PatWalletTransDAO.insertOfflineAdvance()--> " + e1.getMessage() + "-->" + err);
					voObj.setStrMsgType("1");

				}

			}

		} finally
		{

			if (dao != null)
			{
				dao.free();
				dao = null;
			}

			if (pKey != null)
				pKey = null;
			if (pKeyLogDao != null)
				pKeyLogDao = null;
			if (patAccDao != null)
				patAccDao = null;
		//	if (clientPatDao != null)
		//		clientPatDao = null;
			if (billDao != null)
				billDao = null;
			if (billReceiptDao != null)
				billReceiptDao = null;
			if (patAccSerDao != null)
				patAccSerDao = null;
			if (outBoundDao != null)
				outBoundDao = null;
			if (paymentDao != null)
				paymentDao = null;
			if (billReqTrfDao != null)
				billReqTrfDao = null;
			if (approvalDao != null)
				approvalDao = null;

		}
	}

	
	/**
	 *Wallet part payment
	 * 
	 * **/
	public static void insertWalletPartPay(PatWalletTransVO voObj)
	{

		int nAccFlag = 0;
		int nBillFlag = 0;
		int nAppFlag = 0;

		boolean fClientFlag = false;

		String strAccountNo = "0";
		String strBillNo = "0";
		String strAppId = "0";

		String counterId = "0";

		HisDAO dao = null;

		PrimaryKeyDAO pKey = null;
		PrimaryKeyLogDAO pKeyLogDao = null;
		PatientAccountDAO patAccDao = null;
		//ClientPatientDAO clientPatDao = null;
		BillDAO billDao = null;
		BillReceiptDAO billReceiptDao = null;
		PatientAccountServiceDAO patAccSerDao = null;
		OutBoundDAO outBoundDao = null;
		PaymentDAO paymentDao = null;
		BillRequisitionTariffDAO billReqTrfDao = null;
		ApprovalDAO approvalDao = null;

		try
		{

			dao = new HisDAO("Bill Transaction", "PatWalletTransDAO.insertOfflineAdvance");
			pKey = new PrimaryKeyDAO();
			pKeyLogDao = new PrimaryKeyLogDAO();
			patAccDao = new PatientAccountDAO();
			//clientPatDao = new ClientPatientDAO();
			billDao = new BillDAO();
			billReceiptDao = new BillReceiptDAO();
			patAccSerDao = new PatientAccountServiceDAO();
			outBoundDao = new OutBoundDAO();
			paymentDao = new PaymentDAO();
			billReqTrfDao = new BillRequisitionTariffDAO();
			approvalDao = new ApprovalDAO();

			HisUtil hisUtil = new HisUtil("Bill Transaction", "PatWalletTransDAO");

			voObj.setStrCurrentDate(hisUtil.getASDate("dd-MMM-yyyy"));

			String strTemp = voObj.getStrOffLineEpisode().replace("^", "#").split("#")[0];

			voObj.setStrOffLineEpisode(strTemp);

			// Counter Id Generation.

			PatWalletTransDAO.getCounterId(voObj);

			counterId = voObj.getStrCounterId().replace("^", "#").split("#")[0];

			// Open or Retive Existing Account.
			strAccountNo = voObj.getStrWalletAccountNo();

			if (strAccountNo == null || strAccountNo.equals("0"))
			{

				pKey.setStrBlockkey("1");
				pKey.setStrKeyname("ACCOUNT");
				pKey.setStrHospCode(voObj.getStrHospitalCode());

				pKey.select(dao);

				strAccountNo = pKey.getStrPrimrayKeyValue();

				nAccFlag = 1;

			}
			voObj.setStrOffLineTreatmentCategory(voObj.getStrOffLineTreatmentCategory().split("\\^")[0]);
			patAccDao.setStrMobileNo(voObj.getStrMobileNo1());
			if (nAccFlag == 1)
			{
				patAccDao.setStrPatAccNo(strAccountNo);
				patAccDao.setStrChargeTypeId(voObj.getStrChargeType());//voObj.getStrOffLineHospitalService());
				patAccDao.setStrDeptCode(voObj.getStrOffLineRaisingDepartment());
				patAccDao.setStrPuk(voObj.getStrCrNo());

				patAccDao.setStrAdmNo(voObj.getStrOffLineAdmissionNo());
				patAccDao.setStrEpisodeCode(voObj.getStrOffLineEpisode());
				patAccDao.setStrCatCode(voObj.getStrOffLineTreatmentCategory());

				//patAccDao.setStrPatRecdAmt(voObj.getStrOfflineTotalRecAmount());
				patAccDao.setStrPatAccountStatus("2");
				patAccDao.setStrSeatId(voObj.getStrSeatId());
				patAccDao.setStrIsValid("1");
				patAccDao.setStrHospitalCode(voObj.getStrHospitalCode());
				
				if (voObj.getStrClientPatientNo().equals(""))
					voObj.setStrClientPatientNo("0");

				if (!voObj.getStrClientPatientNo().equals("0"))
				{
					fClientFlag = true;
				}
				
	           //patAccDao.setStrAccountStatus(voObj.getStrAccountStatus());
				if (fClientFlag)
				{
					patAccDao.setStrClientPatientNo(voObj.getStrOffLineClientPatientNo());
					patAccDao.setStrSancAmt(voObj.getStrOffLineSancAmount());
					patAccDao.setStrClientBalance(voObj.getStrOffLineBalanceAmount());
					patAccDao.setStrClientPatBoundDt(voObj.getStrCurrentDate());
				}

	            System.out.println("hblnum_patient_recd_amt::"+voObj.getStrAdvacnceAmount());
	 			patAccDao.setStrPatRecdAmt(voObj.getStrAdvacnceAmount());//getStrOfflineTotalRecAmount());
	 			patAccDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
	 			patAccDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));
				patAccDao.insert(dao);
			} 
			else
			{
				patAccDao.setStrPatAccNo(strAccountNo);
				//patAccDao.setStrPatRecdAmt(voObj.getStrOfflineTotalRecAmount());
				patAccDao.setStrHospitalCode(voObj.getStrHospitalCode());
				if (fClientFlag)
				{
					patAccDao.setStrClientPatientNo(voObj.getStrOffLineClientPatientNo());
					patAccDao.setStrSancAmt(voObj.getStrOffLineSancAmount());
					patAccDao.setStrClientBalance(voObj.getStrOffLineBalanceAmount());
					patAccDao.setStrClientPatBoundDt(voObj.getStrCurrentDate());
				}

	            System.out.println("hblnum_patient_recd_amt::"+voObj.getStrAdvacnceAmount());
	 			patAccDao.setStrPatRecdAmt(voObj.getStrAdvacnceAmount());//getStrOfflineTotalRecAmount());
	 			patAccDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
	 			patAccDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));
				patAccDao.update4(dao);
			}
			

			//if (fClientFlag)
			//{

			//	clientPatDao.setStrClientPatNo(voObj.getStrOffLineClientPatientNo());

			//	clientPatDao.insert(dao);
			//}

			// Bill Generation.

			pKey.setStrBlockkey("1");
			pKey.setStrKeyname("BILL_IPD");
			pKey.setStrHospCode(voObj.getStrHospitalCode());

			pKey.select(dao);

			strBillNo = pKey.getStrPrimrayKeyValue();

			voObj.setStrBillNo(strBillNo);

			nBillFlag = 1;

			billDao.setStrBillNo(strBillNo);
			billDao.setStrPatAccNo(strAccountNo);
			if (fClientFlag)
			{

				billDao.setStrClientPatNo(voObj.getStrOffLineClientPatientNo());
				billDao.setStrSancAmt(voObj.getStrOffLineSancAmount());
				billDao.setStrClientBalance(voObj.getStrOffLineBalanceAmount());
			}
			billDao.setStrAdmNo(voObj.getStrOffLineAdmissionNo());
			billDao.setStrBServiceId("31");//voObj.getStrOffLineBillingService());
			billDao.setStrDeptCode(voObj.getStrOffLineRaisingDepartment());
			billDao.setStrPatientCatCode(voObj.getStrOffLineTreatmentCategory());
			billDao.setStrEpisodeCode(voObj.getStrOffLineEpisode());
			billDao.setStrChargeTypeId(voObj.getStrChargeType());//voObj.getStrOffLineHospitalService());
			billDao.setStrPuk(voObj.getStrCrNo());

			billDao.setStrRemarks(voObj.getStrOffLineRemarks());
			billDao.setStrPatientNetAmt(voObj.getStrAdvacnceAmount());//getStrOfflineTotalRecAmount());

			billDao.setStrStatus("1");
			billDao.setStrSeatId(voObj.getStrSeatId());
			billDao.setStrIsValid("1");
			billDao.setStrWardCode(voObj.getStrOffLineSpecialWard());
			billDao.setStrIpdChargeTypeId(voObj.getStrOffLineWard());//voObj.getStrOffLineWard());
			billDao.setStrHospitalCode(voObj.getStrHospitalCode());
			billDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
			billDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));
			billDao.insert(dao);

			billReceiptDao.setStrBillNo(strBillNo);
			billReceiptDao.setStrReceiptNo("1");
			billReceiptDao.setStrReceiptType(voObj.getStrOffLineRequestType());
			billReceiptDao.setStrPuk(voObj.getStrCrNo());
			billReceiptDao.setStrAdmNo(voObj.getStrOffLineAdmissionNo());
			billReceiptDao.setStrPatAccNo(voObj.getStrWalletAccountNo());
			if (fClientFlag)
			{

				billReceiptDao.setStrClientPatNo(voObj.getStrOffLineClientPatientNo());

				billReceiptDao.setStrClientBalance(voObj.getStrOffLineBalanceAmount());
			}

			billReceiptDao.setStrChargeTypeId(voObj.getStrChargeType());//voObj.getStrOffLineHospitalService());
			billReceiptDao.setStrBServiceId("31");//voObj.getStrOffLineBillingService());
			billReceiptDao.setStrDeptCode(voObj.getStrOffLineRaisingDepartment());
			billReceiptDao.setStrPatientCatCode(voObj.getStrOffLineTreatmentCategory());
			billReceiptDao.setStrPatientTAmt(voObj.getStrAdvacnceAmount());

			billReceiptDao.setStrCounterId(counterId);

			if (voObj.getStrChk_value().equals("1"))
			{

				pKey.setStrBlockkey("1");
				pKey.setStrKeyname("APPROVAL");
				pKey.setStrHospCode(voObj.getStrHospitalCode());

				pKey.select(dao);

				strAppId = pKey.getStrPrimrayKeyValue();

				nAppFlag = 1;

				billReceiptDao.setStrApprovalId(strAppId);
				billReceiptDao.setStrApprovedBy(voObj.getStrOffLineApprovedBy());
				billReceiptDao.setStrApprovedDate(voObj.getStrCurrentDate());
				billReceiptDao.setStrRemarks(voObj.getStrOffLineRemarks());
			}
			billReceiptDao.setStrStatus("1");
			billReceiptDao.setStrSeatId(voObj.getStrSeatId());
			billReceiptDao.setStrIsValid("1");
			billReceiptDao.setStrWardCode(voObj.getStrOffLineSpecialWard());
			billReceiptDao.setStrIpdChargeTypeId(voObj.getStrOffLineWard());//voObj.getStrOffLineWard());
			billReceiptDao.setStrIsOnline("0");
			billReceiptDao.setStrHospitalCode(voObj.getStrHospitalCode());
			billReceiptDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
			billReceiptDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));
			billReceiptDao.insert(dao);
			patAccSerDao.setStrPatAccountNo(voObj.getStrWalletAccountNo());
			patAccSerDao.setStrReqNo(strBillNo);
			patAccSerDao.setStrChargeTypeId(voObj.getStrChargeType());//voObj.getStrOffLineHospitalService());
			patAccSerDao.setStrTariffId("0");
			patAccSerDao.setStrDeptCode(voObj.getStrOffLineRaisingDepartment());
			patAccSerDao.setStrReqType("1");
			patAccSerDao.setStrGroupId(voObj.getStrGroupId());
			patAccSerDao.setStrRateUnitCode(voObj.getStrQtyUnitId());
			patAccSerDao.setStrBServiceId("31");//voObj.getStrOffLineBillingService());
			patAccSerDao.setStrIpdChargeTypeId(voObj.getStrOffLineWard());//voObj.getStrOffLineWard());
		//  patAccSerDao.setStrTariffRate(String.valueOf(0 -
		//  Integer.parseInt(voObj.getStrOffLineAdvanceAmount())));
			patAccSerDao.setStrTariffRate(voObj.getStrAdvacnceAmount());
			patAccSerDao.setStrCatCode(voObj.getStrOffLineTreatmentCategory());
			patAccSerDao.setStrBillQty("1");
			patAccSerDao.setStrProcessedQty("0");
			patAccSerDao.setStrRemainedQty("1");
			patAccSerDao.setStrPuk(voObj.getStrCrNo());
			patAccSerDao.setStrRemarks(voObj.getStrOffLineRemarks());
			patAccSerDao.setStrSeatId(voObj.getStrSeatId());
			patAccSerDao.setStrIsValid("1");
			patAccSerDao.setStrIsRefundable("0");
			patAccSerDao.setStrQtyUnitId(voObj.getStrQtyUnitId());
			patAccSerDao.setStrBillFlag("1");
			patAccSerDao.setStrReceiptNo("1");
			patAccSerDao.setStrWardCode(voObj.getStrOffLineSpecialWard());
			patAccSerDao.setStrStatus("1");
		//  patAccSerDao.setStrNetAmt(String.valueOf(0 -
	   //   Integer.parseInt(voObj.getStrOffLineAdvanceAmount())));
			patAccSerDao.setStrNetAmt(voObj.getStrAdvacnceAmount());
			patAccSerDao.setStrHospitalCode(voObj.getStrHospitalCode());
			patAccSerDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
			patAccSerDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));
			patAccSerDao.insert(dao);
			/*if(!voObj.getStrOfflineTotalRecAmount().equals("0"))
			{
				patAccSerDao.setStrPatAccountNo(strAccountNo);
				patAccSerDao.setStrReqNo(strBillNo);
				patAccSerDao.setStrChargeTypeId(voObj.getStrChargeType());//voObj.getStrOffLineHospitalService());
				patAccSerDao.setStrTariffId("1090001"); //Surcharge Tariff for Card based payments
				patAccSerDao.setStrDeptCode(voObj.getStrOffLineRaisingDepartment());
				patAccSerDao.setStrReqType("1");
				patAccSerDao.setStrGroupId("109");
				patAccSerDao.setStrRateUnitCode(voObj.getStrQtyUnitId());
				patAccSerDao.setStrBServiceId("11");//voObj.getStrOffLineBillingService());
				patAccSerDao.setStrIpdChargeTypeId(voObj.getStrOffLineWard());//voObj.getStrOffLineWard());
				// patAccSerDao.setStrTariffRate(String.valueOf(0 -
				// Integer.parseInt(voObj.getStrOffLineAdvanceAmount())));
				patAccSerDao.setStrTariffRate(voObj.getStrOfflineTotalRecAmount());
				patAccSerDao.setStrCatCode(voObj.getStrOffLineTreatmentCategory());
				patAccSerDao.setStrBillQty("1");
				patAccSerDao.setStrProcessedQty("0");
				patAccSerDao.setStrRemainedQty("1");
				patAccSerDao.setStrPuk(voObj.getStrCrNo());
				patAccSerDao.setStrRemarks("Surcharge Tariff");
				patAccSerDao.setStrSeatId(voObj.getStrSeatId());
				patAccSerDao.setStrIsValid("1");
				patAccSerDao.setStrIsRefundable("0");
				patAccSerDao.setStrQtyUnitId(voObj.getStrQtyUnitId());
				patAccSerDao.setStrBillFlag("1");
				patAccSerDao.setStrReceiptNo("1");
				patAccSerDao.setStrWardCode(voObj.getStrOffLineSpecialWard());
				patAccSerDao.setStrStatus("1");
				// patAccSerDao.setStrNetAmt(String.valueOf(0 -
				// Integer.parseInt(voObj.getStrOffLineAdvanceAmount())));
				patAccSerDao.setStrNetAmt(voObj.getStrOfflineTotalRecAmount());
				patAccSerDao.setStrHospitalCode(voObj.getStrHospitalCode());
				patAccSerDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
				patAccSerDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));

				patAccSerDao.insert(dao);
			}*/
			outBoundDao.setStrTransNo(strBillNo);
			outBoundDao.setStrReceiptNo("1");
			outBoundDao.setStrPatAccNo(voObj.getStrWalletAccountNo());
			outBoundDao.setStrPuk(voObj.getStrCrNo());
			outBoundDao.setStrAdmNo(voObj.getStrOffLineAdmissionNo());
			outBoundDao.setStrEpisodeCode(voObj.getStrOffLineEpisode());
			outBoundDao.setStrPatientCatCode(voObj.getStrOffLineTreatmentCategory());
			outBoundDao.setStrTransAmt(voObj.getStrAdvacnceAmount());
			outBoundDao.setStrChargeTypeId(voObj.getStrChargeType());//voObj.getStrOffLineHospitalService());
			outBoundDao.setStrIpdChargeTypeId(voObj.getStrOffLineWard());//voObj.getStrOffLineWard());
			outBoundDao.setStrCounterId(counterId);
			outBoundDao.setStrBServiceId("31");//voObj.getStrOffLineBillingService());
			outBoundDao.setStrStatus("1");
			outBoundDao.setStrRemarks(voObj.getStrOffLineRemarks());
			outBoundDao.setStrDayEndFlag("0");
			outBoundDao.setStrSeatId(voObj.getStrSeatId());
			outBoundDao.setStrIsValid("1");
			outBoundDao.setStrTransType("1");
			outBoundDao.setStrWardCode(voObj.getStrOffLineSpecialWard());
			outBoundDao.setStrIsBill("1");
			outBoundDao.setStrIsOnline("0");
			outBoundDao.setStrHospitalCode(voObj.getStrHospitalCode());
			outBoundDao.setStrDeptCode(voObj.getStrOffLineRaisingDepartment());
			outBoundDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
			outBoundDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));

			outBoundDao.insert(dao);

			// Payment Details

			int nSlNo = 0;
			int nPaymentLenght = voObj.getStrOfflinePaymentMode().length;
            int i=0;
			//for (int i = 0; i < nPaymentLenght; i++)
			//{

				nSlNo = nSlNo + 1;

				paymentDao.setStrBillNo(strBillNo);

				paymentDao.setStrPaymentMode((voObj.getStrOfflinePaymentMode()[i]).split("#")[0]);
				paymentDao.setStrRecieptNo("1");
				paymentDao.setStrSerialNo(String.valueOf(nSlNo));
				paymentDao.setStrChargeTypeId(voObj.getStrChargeType());//voObj.getStrOffLineHospitalService());
				paymentDao.setStrBServiceId("31");//voObj.getStrOffLineBillingService());

				String payDtl[] = { "", "" };

				if (voObj.getStrOfflinePaymentDtls() != null)
				{
					paymentDao.setStrPaymentDetails(voObj.getStrOfflinePaymentDtls()[i]);
					payDtl = voObj.getStrOfflinePaymentDtls()[i].replace(",", "#").split("#");
				} else
				{
					paymentDao.setStrPaymentDetails("");
				}

				if (voObj.getStrOfflinePaymentMode()[i].equals("2") || voObj.getStrOfflinePaymentMode()[i].equals("3"))
				{
					paymentDao.setStrPaymentModeNo(payDtl[1]);
					paymentDao.setStrPaymentStatus("0");

				} else if (voObj.getStrOfflinePaymentMode()[i].equals("4") || voObj.getStrOfflinePaymentMode()[i]
						.equals("5"))
				{
					paymentDao.setStrPaymentModeNo(payDtl[1]);
					paymentDao.setStrPaymentStatus("0");

				} else if (voObj.getStrOfflinePaymentMode()[i].equals("6"))
				{

					paymentDao.setStrPaymentModeNo(payDtl[0]);
				} else
				{
					paymentDao.setStrPaymentModeNo("");
					paymentDao.setStrPaymentStatus("1");
				}

				paymentDao.setStrRecieptType("1");
				
				paymentDao.setStrRecieptAmount(voObj.getStrAdvacnceAmount());
//System.out.println("strOfflineAmount>>>>>>>>>>>>>>>"+voObj.getStrOfflineAmount()[i]);
				paymentDao.setStrPuk(voObj.getStrCrNo());
				paymentDao.setStrProcessedBy("0");
				paymentDao.setStrCounterId(counterId);
				paymentDao.setStrSeatId(voObj.getStrSeatId());
				paymentDao.setStrIsValid("1");
				paymentDao.setStrHospitalCode(voObj.getStrHospitalCode());
				paymentDao.setStrNetAmount(voObj.getStrAdvacnceAmount());
				paymentDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
				paymentDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));
				System.out.println("voObj.getStrOfflineTotalRecAmount()>>>>>>>>>>>>>>>"+voObj.getStrAdvacnceAmount());
				paymentDao.insert(dao);
			//}

			/*
			 * if (voObj.getStrOffLineClientPatientNo().length() > 1 &&
			 * !voObj.getStrOfflineMaxClientBenefitAmount().equals("0")) {
			 * 
			 * nSlNo = nSlNo + 1;
			 * 
			 * paymentDao.setStrBillNo(strBillNo);
			 * 
			 * paymentDao.setStrPaymentMode("6"); paymentDao.setStrRecieptNo("1");
			 * paymentDao.setStrSerialNo(String.valueOf(nSlNo));
			 * paymentDao.setStrChargeTypeId(voObj
			 * .getStrOffLineHospitalService()); paymentDao
			 * .setStrBServiceId(voObj.getStrOffLineBillingService()); paymentDao
			 * .setStrPaymentDetails(voObj.getStrOfflineClientName());
			 * paymentDao.setStrPaymentModeNo(voObj
			 * .getStrOffLineClientPatientNo());
			 * paymentDao.setStrPaymentStatus("0");
			 * paymentDao.setStrRecieptType("1");
			 * paymentDao.setStrRecieptAmount(voObj
			 * .getStrOfflineMaxClientBenefitAmount());
			 * paymentDao.setStrPuk(voObj.getStrCrNo());
			 * paymentDao.setStrProcessedBy("0");
			 * paymentDao.setStrCounterId(counterId);
			 * paymentDao.setStrSeatId(voObj.getStrSeatId());
			 * paymentDao.setStrHospitalCode(voObj.getStrHospitalCode());
			 * paymentDao.setStrIsValid("1");
			 * paymentDao.setStrNetAmount(voObj.getStrOfflineTotalRecAmount());
			 * 
			 * paymentDao.insert(dao); }
			 */

			// Approval Details
			if (voObj.getStrChk_value().equals("1"))
			{

				approvalDao.setStrApprovalId(strAppId);
				approvalDao.setStrApprovalSlNo("1");
				approvalDao.setStrReceiptType("1");
				approvalDao.setStrBServiceId("31");//);
				approvalDao.setStrChargeTypeId(voObj.getStrChargeType());//voObj.getStrOffLineHospitalService());
				approvalDao.setStrEmpNo(voObj.getStrApprovedByCombo());
				approvalDao.setStrUserLevel(voObj.getStrUserLevel());
				approvalDao.setStrRemarks(voObj.getStrRemarksCombo2());
				approvalDao.setStrApprovalType("5");
				approvalDao.setStrStatus("1");
				approvalDao.setStrSeatId(voObj.getStrSeatId());
				approvalDao.setStrIsValid("1");
				approvalDao.setStrHospitalCode(voObj.getStrHospitalCode());

				approvalDao.insert(dao);

			}

			String proc_name1 = "";

			proc_name1 = "{call PKG_BILL_DML.dml_update_account_dtl(?,?,?,?,?,?,?,?)}";

			int procIndex1 = 0;

			procIndex1 = dao.setProcedure(proc_name1);

			dao.setProcInValue(procIndex1, "modval", "1", 1);
			dao.setProcInValue(procIndex1, "hosp_code", voObj.getStrHospitalCode(), 2);
			dao.setProcInValue(procIndex1, "accNo", voObj.getStrWalletAccountNo(), 3);
			dao.setProcInValue(procIndex1, "wardId", voObj.getStrOffLineSpecialWard(), 4);
			dao.setProcInValue(procIndex1, "ipdChargeTypeId", voObj.getStrOffLineWard(), 5);
			dao.setProcInValue(procIndex1, "patCat", voObj.getStrOffLineTreatmentCategory(), 6);
			dao.setProcInValue(procIndex1, "deptId", voObj.getStrOffLineRaisingDepartment(), 7);
			dao.setProcOutValue(procIndex1, "ERR", 1, 8); // 1 for string return
			dao.execute(procIndex1, 1);
			synchronized (dao)
			{
				dao.fire();
			}

		} catch (Exception e)
		{

			String err = "err:" + e.getMessage();

			voObj.setStrMsgType("1");
			voObj.setStrMsgString("PatWalletTransDAO.insertOfflineAdvance() --> BILL_IPD -->" + err);

			if (nAccFlag == 1)
			{

				try
				{
					pKeyLogDao.setStrKeyname("ACCOUNT");
					pKeyLogDao.setStrStartkey(voObj.getStrWalletAccountNo());
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrError(err);
					pKeyLogDao.setStrHospCode(voObj.getStrHospitalCode());
					pKeyLogDao.setStrSeatid(voObj.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);

				} catch (Exception e1)
				{

					voObj
							.setStrMsgString("PatWalletTransDAO.insertOfflineAdvance()--> " + e1.getMessage() + "-->" + err);
					voObj.setStrMsgType("1");

				}

			}
			if (nBillFlag == 1)
			{

				voObj.setStrBillNo("0");

				try
				{

					pKeyLogDao.setStrKeyname("BILL_IPD");
					pKeyLogDao.setStrStartkey(strBillNo);
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrError(err);
					pKeyLogDao.setStrHospCode(voObj.getStrHospitalCode());
					pKeyLogDao.setStrSeatid(voObj.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);

				} catch (Exception e1)
				{

					voObj
							.setStrMsgString("PatWalletTransDAO.insertOfflineAdvance()--> " + e1.getMessage() + "-->" + err);
					voObj.setStrMsgType("1");

				}

			}

			if (nAppFlag == 1)
			{

				try
				{
					pKeyLogDao.setStrKeyname("APPROVAL");
					pKeyLogDao.setStrStartkey(strAppId);
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrError(err);
					pKeyLogDao.setStrHospCode(voObj.getStrHospitalCode());
					pKeyLogDao.setStrSeatid(voObj.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);

				} catch (Exception e1)
				{

					voObj
							.setStrMsgString("PatWalletTransDAO.insertOfflineAdvance()--> " + e1.getMessage() + "-->" + err);
					voObj.setStrMsgType("1");

				}

			}

		} finally
		{

			if (dao != null)
			{
				dao.free();
				dao = null;
			}

			if (pKey != null)
				pKey = null;
			if (pKeyLogDao != null)
				pKeyLogDao = null;
			if (patAccDao != null)
				patAccDao = null;
		//	if (clientPatDao != null)
		//		clientPatDao = null;
			if (billDao != null)
				billDao = null;
			if (billReceiptDao != null)
				billReceiptDao = null;
			if (patAccSerDao != null)
				patAccSerDao = null;
			if (outBoundDao != null)
				outBoundDao = null;
			if (paymentDao != null)
				paymentDao = null;
			if (billReqTrfDao != null)
				billReqTrfDao = null;
			if (approvalDao != null)
				approvalDao = null;

		}
	}
	
	/**
	 * Inserts Off-line Part Payment Details
	 * 
	 * @param voObj
	 */
	
	
	
	public static void insertOfflinePartPayment(PatWalletTransVO voObj)
	{

		int nAccFlag = 0;
		int nBillFlag = 0;
		int nAppFlag = 0;

		String strAccountNo = "0";
		String strBillNo = "0";
		String strAppId = "0";

		String counterId = "0";
		// Advance Security Flag added by garima gotra for HIS_PGI_BILL_CR_1 on
		// 17 Aug 2011
		String advSecFlag = "0";
		HisDAO dao = null;

		PrimaryKeyDAO pKey = null;
		PrimaryKeyLogDAO pKeyLogDao = null;
		PatientAccountDAO patAccDao = null;
		ClientPatientDAO clientPatDao = null;
		BillDAO billDao = null;
		BillReceiptDAO billReceiptDao = null;
		PatientAccountServiceDAO patAccSerDao = null;
		OutBoundDAO outBoundDao = null;
		PaymentDAO paymentDao = null;
		BillRequisitionTariffDAO billReqTrfDao = null;
		ApprovalDAO approvalDao = null;
		// Added by garima gotra for HIS_PGI_BILL_CR_1 on 18 Aug 2011
		PatientAccountStatusDAO pActStatusDAO = null;
		PatientAccountDAO patAccDao2 = null;

		try
		{

			dao = new HisDAO("Bill Transaction", "PatWalletTransDAO.insertOfflinePartPayment");
			pKey = new PrimaryKeyDAO();
			pKeyLogDao = new PrimaryKeyLogDAO();
			patAccDao = new PatientAccountDAO();
			clientPatDao = new ClientPatientDAO();
			billDao = new BillDAO();
			billReceiptDao = new BillReceiptDAO();
			patAccSerDao = new PatientAccountServiceDAO();
			outBoundDao = new OutBoundDAO();
			paymentDao = new PaymentDAO();
			billReqTrfDao = new BillRequisitionTariffDAO();
			approvalDao = new ApprovalDAO();
			pActStatusDAO = new PatientAccountStatusDAO();
			patAccDao2 = new PatientAccountDAO();

			HisUtil hisUtil = new HisUtil("Bill Transaction", "PatWalletTransDAO");

			voObj.setStrCurrentDate(hisUtil.getASDate("dd-MMM-yyyy"));

			// Get Counter Id.
			getCounterId(voObj);

			counterId = voObj.getStrCounterId().replace("^", "#").split("#")[0];

			// Open Account
			strAccountNo = voObj.getStrOffLineAccountNo();

			// Added by garima gotra for HIS_PGI_BILL_CR_1 on 17 Aug 2011
			advSecFlag = voObj.getadvSecFlag();

			if (strAccountNo == null || strAccountNo.equals(""))
			{

				pKey.setStrBlockkey("1");
				pKey.setStrKeyname("ACCOUNT");
				pKey.setStrHospCode(voObj.getStrHospitalCode());

				pKey.select(dao);

				strAccountNo = pKey.getStrPrimrayKeyValue();

				nAccFlag = 1;

			}

			if (nAccFlag == 1)
			{
				patAccDao.setStrPatAccNo(strAccountNo);
				patAccDao.setStrChargeTypeId("1");
				patAccDao.setStrDeptCode(voObj.getStrOffLineRaisingDepartment());
				patAccDao.setStrPuk(voObj.getStrCrNo());

				patAccDao.setStrAdmNo(voObj.getStrOffLineAdmissionNo());
				patAccDao.setStrEpisodeCode(voObj.getStrOffLineEpisode());
				patAccDao.setStrCatCode(voObj.getStrOffLineTreatmentCategory());

				patAccDao.setStrPatRecdAmt(voObj.getStrOfflineTotalRecAmount());
				patAccDao.setStrPatAccountStatus("2");
				patAccDao.setStrSeatId(voObj.getStrSeatId());
				patAccDao.setStrIsValid("1");
				patAccDao.setStrHospitalCode(voObj.getStrHospitalCode());
				patAccDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
				patAccDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));
				patAccDao.insert(dao);

			} else
			{
				patAccDao.setStrPatAccNo(strAccountNo);
				patAccDao.setStrPatRecdAmt(voObj.getStrOfflineTotalRecAmount());
				patAccDao.setStrHospitalCode(voObj.getStrHospitalCode());
				patAccDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
				patAccDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));
				patAccDao.update4(dao);

				// Code Change by garima gotra for HIS_PGI_BILL_CR_1 on 17 Aug
				// 2011
				if (advSecFlag != null && advSecFlag.equals("1"))
				{
					patAccDao2.setStrPatAccNo(strAccountNo);
					patAccDao2.setStrHospitalCode(voObj.getStrHospitalCode());
					patAccDao2.setStrPatAccountStatus("4");
					patAccDao2.setAdvSecFlag(advSecFlag);
					patAccDao2.update2(dao);
					pActStatusDAO.setStrAppBy(voObj.getStrSeatId());
					pActStatusDAO.setStrPatAccountNo(strAccountNo);
					// 1 stands for active account
					pActStatusDAO.setStrOldStatus("1");
					// 4 stands for Dormant
					pActStatusDAO.setStrNewStatus("4");
					pActStatusDAO.setStrRemarks(voObj.getStrRemarks());
					pActStatusDAO.setStrHospitalCode(voObj.getStrHospitalCode());
					pActStatusDAO.setStrSeatId(voObj.getStrSeatId());
					pActStatusDAO.insert(dao);
				}
				// End of code by garima gotra for HIS_PGI_BILL_CR_1 on 17 Aug
				// 2011

			}

			// Bill Generation
			pKey.setStrBlockkey("1");
			pKey.setStrKeyname("BILL_IPD");
			pKey.setStrHospCode(voObj.getStrHospitalCode());

			pKey.select(dao);

			strBillNo = pKey.getStrPrimrayKeyValue();

			voObj.setStrBillNo(strBillNo);

			nBillFlag = 1;

			billDao.setStrBillNo(strBillNo);
			billDao.setStrPatAccNo(strAccountNo);

			billDao.setStrAdmNo(voObj.getStrOffLineAdmissionNo());
			billDao.setStrBServiceId(voObj.getStrOffLineBillingService());
			billDao.setStrDeptCode(voObj.getStrOffLineRaisingDepartment());
			billDao.setStrPatientCatCode(voObj.getStrOffLineTreatmentCategory());
			billDao.setStrChargeTypeId(voObj.getStrOffLineHospitalService());
			billDao.setStrEpisodeCode(voObj.getStrOffLineEpisode());
			billDao.setStrPuk(voObj.getStrCrNo());

			if (voObj.getStrRemarks() != null && voObj.getStrRemarks().trim().length() > 0)
			{

				billDao.setStrRemarks(voObj.getStrRemarks());

			} else
			{

				billDao.setStrRemarks(voObj.getStrOffLineRemarks());
			}

			billDao.setStrPatientNetAmt(voObj.getStrOfflineTotalRecAmount());
			billDao.setStrStatus("1");
			billDao.setStrSeatId(voObj.getStrSeatId());
			billDao.setStrIsValid("1");
			billDao.setStrWardCode(voObj.getStrOffLineSpecialWard());
			billDao.setStrIpdChargeTypeId(voObj.getStrOffLineWard());
			billDao.setStrHospitalCode(voObj.getStrHospitalCode());
			billDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
			billDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));
			billDao.insert(dao);

			billReceiptDao.setStrBillNo(strBillNo);
			billReceiptDao.setStrReceiptNo("1");
			billReceiptDao.setStrReceiptType(voObj.getStrOffLineRequestType());
			billReceiptDao.setStrPuk(voObj.getStrCrNo());
			billReceiptDao.setStrAdmNo(voObj.getStrOffLineAdmissionNo());
			billReceiptDao.setStrPatAccNo(strAccountNo);

			billReceiptDao.setStrChargeTypeId(voObj.getStrOffLineHospitalService());
			billReceiptDao.setStrBServiceId(voObj.getStrOffLineBillingService());
			billReceiptDao.setStrDeptCode(voObj.getStrOffLineRaisingDepartment());
			billReceiptDao.setStrPatientCatCode(voObj.getStrOffLineTreatmentCategory());
			billReceiptDao.setStrPatientTAmt(voObj.getStrOfflineTotalRecAmount());

			billReceiptDao.setStrCounterId(counterId);

			if (voObj.getStrChk_value().equals("1"))
			{

				pKey.setStrBlockkey("1");
				pKey.setStrKeyname("APPROVAL");
				pKey.setStrHospCode(voObj.getStrHospitalCode());

				pKey.select(dao);

				strAppId = pKey.getStrPrimrayKeyValue();

				nAppFlag = 1;

				billReceiptDao.setStrApprovalId(strAppId);
				billReceiptDao.setStrApprovedBy(voObj.getStrOffLineApprovedBy());
				billReceiptDao.setStrApprovedDate(voObj.getStrCurrentDate());
				// billReceiptDao.setStrRemarks(voObj.getStrOffLineRemarks());
			}
			billReceiptDao.setStrStatus("1");
			billReceiptDao.setStrSeatId(voObj.getStrSeatId());
			billReceiptDao.setStrIsValid("1");
			billReceiptDao.setStrWardCode(voObj.getStrOffLineSpecialWard());
			billReceiptDao.setStrIpdChargeTypeId(voObj.getStrOffLineWard());
			billReceiptDao.setStrIsOnline("0");
			billReceiptDao.setStrIsBill("0");
			billReceiptDao.setStrHospitalCode(voObj.getStrHospitalCode());

			if (voObj.getStrRemarks() != null && voObj.getStrRemarks().trim().length() > 0)
			{

				billReceiptDao.setStrRemarks(voObj.getStrRemarks());

			}
			billReceiptDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
			billReceiptDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));
			billReceiptDao.insert(dao);

			patAccSerDao.setStrPatAccountNo(strAccountNo);
			patAccSerDao.setStrReqNo(strBillNo);
			patAccSerDao.setStrChargeTypeId(voObj.getStrOffLineHospitalService());
			patAccSerDao.setStrTariffId("0");
			patAccSerDao.setStrDeptCode(voObj.getStrOffLineRaisingDepartment());
			patAccSerDao.setStrReqType("1");
			patAccSerDao.setStrGroupId(voObj.getStrGroupId());
			patAccSerDao.setStrBServiceId(voObj.getStrOffLineBillingService());
			patAccSerDao.setStrRateUnitCode(voObj.getStrQtyUnitId());
			patAccSerDao.setStrIpdChargeTypeId(voObj.getStrOffLineWard());
			// patAccSerDao.setStrTariffRate(String.valueOf(0 -
			// Integer.parseInt(voObj.getStrOffLinePartPaymentAmount())));
			patAccSerDao.setStrTariffRate(voObj.getStrOfflineTotalRecAmount());
			patAccSerDao.setStrCatCode(voObj.getStrOffLineTreatmentCategory());
			patAccSerDao.setStrBillQty("1");
			patAccSerDao.setStrProcessedQty("0");
			patAccSerDao.setStrRemainedQty("1");
			patAccSerDao.setStrPuk(voObj.getStrCrNo());
			patAccSerDao.setStrRemarks(voObj.getStrOffLineRemarks());
			patAccSerDao.setStrSeatId(voObj.getStrSeatId());
			patAccSerDao.setStrIsValid("1");
			// patAccSerDao.setStrIsRefundable("0");
			patAccSerDao.setStrIsRefundable("1");
			patAccSerDao.setStrQtyUnitId(voObj.getStrQtyUnitId());
			patAccSerDao.setStrBillFlag("1");
			patAccSerDao.setStrReceiptNo("1");
			patAccSerDao.setStrWardCode(voObj.getStrOffLineSpecialWard());
			patAccSerDao.setStrStatus("1");
			// patAccSerDao.setStrNetAmt(String.valueOf(0 -
			// Integer.parseInt(voObj.getStrOffLinePartPaymentAmount())));
			patAccSerDao.setStrNetAmt(voObj.getStrOfflineTotalRecAmount());
			patAccSerDao.setStrHospitalCode(voObj.getStrHospitalCode());
			patAccSerDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
			patAccSerDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));
			patAccSerDao.insert(dao);

			outBoundDao.setStrTransNo(strBillNo);
			outBoundDao.setStrReceiptNo("1");
			outBoundDao.setStrPatAccNo(voObj.getStrOffLineAccountNo());
			outBoundDao.setStrPuk(voObj.getStrCrNo());
			outBoundDao.setStrAdmNo(voObj.getStrOffLineAdmissionNo());
			outBoundDao.setStrEpisodeCode(voObj.getStrOffLineEpisode());
			outBoundDao.setStrPatientCatCode(voObj.getStrOffLineTreatmentCategory());
			outBoundDao.setStrTransAmt(voObj.getStrOfflineTotalRecAmount());
			outBoundDao.setStrDeptCode(voObj.getStrOffLineRaisingDepartment());
			outBoundDao.setStrChargeTypeId(voObj.getStrOffLineHospitalService());
			outBoundDao.setStrIpdChargeTypeId(voObj.getStrOffLineWard());
			outBoundDao.setStrCounterId(counterId);
			outBoundDao.setStrBServiceId(voObj.getStrOffLineBillingService());
			outBoundDao.setStrStatus("1");
			outBoundDao.setStrRemarks(voObj.getStrOffLineRemarks());
			outBoundDao.setStrDayEndFlag("0");
			outBoundDao.setStrSeatId(voObj.getStrSeatId());
			outBoundDao.setStrIsValid("1");
			outBoundDao.setStrTransType("1");
			outBoundDao.setStrWardCode(voObj.getStrOffLineSpecialWard());
			outBoundDao.setStrIsBill("1");
			outBoundDao.setStrIsOnline("0");
			outBoundDao.setStrHospitalCode(voObj.getStrHospitalCode());
			outBoundDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
			outBoundDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));
			outBoundDao.insert(dao);

			// Payment Details

			int nSlNo = 0;
			int nPaymentLenght = voObj.getStrOfflinePaymentMode().length;

			for (int i = 0; i < nPaymentLenght; i++)
			{

				nSlNo = nSlNo + 1;

				paymentDao.setStrBillNo(strBillNo);

				paymentDao.setStrPaymentMode(voObj.getStrOfflinePaymentMode()[i]);
				paymentDao.setStrRecieptNo("1");
				paymentDao.setStrSerialNo(String.valueOf(nSlNo));
				paymentDao.setStrChargeTypeId(voObj.getStrOffLineHospitalService());
				paymentDao.setStrBServiceId(voObj.getStrOffLineBillingService());

				String payDtl[] = { "", "" };

				if (voObj.getStrOfflinePaymentDtls() != null)
				{
					paymentDao.setStrPaymentDetails(voObj.getStrOfflinePaymentDtls()[i]);
					payDtl = voObj.getStrOfflinePaymentDtls()[i].replace(",", "#").split("#");
				} else
				{
					paymentDao.setStrPaymentDetails("");
				}

				if (voObj.getStrOfflinePaymentMode()[i].equals("2") || voObj.getStrOfflinePaymentMode()[i].equals("3"))
				{
					paymentDao.setStrPaymentModeNo(payDtl[1]);
					paymentDao.setStrPaymentStatus("0");

				} else if (voObj.getStrOfflinePaymentMode()[i].equals("4") || voObj.getStrOfflinePaymentMode()[i]
						.equals("5"))
				{
					paymentDao.setStrPaymentModeNo(payDtl[1]);
					paymentDao.setStrPaymentStatus("0");

				} else if (voObj.getStrOfflinePaymentMode()[i].equals("6"))
				{

					paymentDao.setStrPaymentModeNo(payDtl[0]);
				} else
				{
					paymentDao.setStrPaymentModeNo("");
					paymentDao.setStrPaymentStatus("1");
				}

				paymentDao.setStrRecieptType("1");
				paymentDao.setStrRecieptAmount(voObj.getStrOfflineAmount()[i]);

				paymentDao.setStrPuk(voObj.getStrCrNo());
				paymentDao.setStrProcessedBy("0");
				paymentDao.setStrCounterId(counterId);
				paymentDao.setStrSeatId(voObj.getStrSeatId());
				paymentDao.setStrIsValid("1");
				paymentDao.setStrHospitalCode(voObj.getStrHospitalCode());
				paymentDao.setStrNetAmount(voObj.getStrOfflineTotalRecAmount());
				paymentDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
				paymentDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));
				paymentDao.insert(dao);
			}

			/*
			 * if (voObj.getStrOffLineClientPatientNo().length() > 1 &&
			 * !voObj.getStrOfflineMaxClientBenefitAmount().equals("0")) {
			 * 
			 * nSlNo = nSlNo + 1;
			 * 
			 * paymentDao.setStrBillNo(strBillNo);
			 * 
			 * paymentDao.setStrPaymentMode("6"); paymentDao.setStrRecieptNo("1");
			 * paymentDao.setStrSerialNo(String.valueOf(nSlNo));
			 * paymentDao.setStrChargeTypeId(voObj
			 * .getStrOffLineHospitalService()); paymentDao
			 * .setStrBServiceId(voObj.getStrOffLineBillingService()); paymentDao
			 * .setStrPaymentDetails(voObj.getStrOfflineClientName());
			 * paymentDao.setStrPaymentModeNo(voObj
			 * .getStrOffLineClientPatientNo());
			 * paymentDao.setStrPaymentStatus("0");
			 * paymentDao.setStrRecieptType("1");
			 * paymentDao.setStrRecieptAmount(voObj
			 * .getStrOfflineMaxClientBenefitAmount());
			 * paymentDao.setStrPuk(voObj.getStrCrNo());
			 * paymentDao.setStrProcessedBy("0");
			 * paymentDao.setStrCounterId(counterId);
			 * paymentDao.setStrSeatId(voObj.getStrSeatId());
			 * paymentDao.setStrHospitalCode(voObj.getStrHospitalCode());
			 * paymentDao.setStrIsValid("1");
			 * paymentDao.setStrNetAmount(voObj.getStrOfflineTotalRecAmount());
			 * 
			 * paymentDao.insert(dao); }
			 */

			if (voObj.getStrChk_value().equals("1"))
			{

				approvalDao.setStrApprovalId(strAppId);
				approvalDao.setStrApprovalSlNo("1");
				approvalDao.setStrReceiptType("1");
				approvalDao.setStrBServiceId(voObj.getStrOffLineBillingService());
				approvalDao.setStrChargeTypeId(voObj.getStrOffLineHospitalService());
				approvalDao.setStrEmpNo(voObj.getStrApprovedByCombo());
				approvalDao.setStrUserLevel(voObj.getStrUserLevel());
				approvalDao.setStrRemarks(voObj.getStrRemarksCombo2());
				approvalDao.setStrApprovalType("4");
				approvalDao.setStrStatus("1");
				approvalDao.setStrSeatId(voObj.getStrSeatId());
				approvalDao.setStrIsValid("1");
				approvalDao.setStrHospitalCode(voObj.getStrHospitalCode());

				approvalDao.insert(dao);

			}

			// Commented as per Change request HIS_PGIMER_Patient Billing_
			// DCD_16Nov11.doc by Amit Kumar Ateria.
			// This is for problem The start day in the bill should be the date
			// of deposit of the advance Payment

			/*
			 * String proc_name1 = "";
			 * 
			 * proc_name1 = "{call
			 * PKG_BILL_DML.dml_update_account_dtl(?,?,?,?,?,?,?,?)}";
			 * 
			 * int procIndex1 = 0;
			 * 
			 * 
			 * procIndex1 = dao.setProcedure(proc_name1);
			 * 
			 * dao.setProcInValue(procIndex1, "modval", "1");
			 * 
			 * dao.setProcInValue(procIndex1, "hosp_code",
			 * voObj.getStrHospitalCode()); dao.setProcInValue(procIndex1, "accNo",
			 * strAccountNo); dao.setProcInValue(procIndex1, "wardId",
			 * voObj.getStrOffLineSpecialWard()); dao.setProcInValue(procIndex1,
			 * "ipdChargeTypeId", voObj.getStrOffLineWard());
			 * dao.setProcInValue(procIndex1, "patCat",
			 * voObj.getStrOffLineTreatmentCategory());
			 * dao.setProcInValue(procIndex1, "deptId",
			 * voObj.getStrOffLineRaisingDepartment());
			 * dao.setProcOutValue(procIndex1, "ERR", 1); // 1 for string return
			 * 
			 * dao.execute(procIndex1,1);
			 */

			synchronized (dao)
			{
				dao.fire();
			}

		} catch (Exception e)
		{

			String err = "err:" + e.getMessage();

			voObj.setStrMsgString("--offline--PatWalletTransDAO.insertOfflinePartPayment()--> " + err);
			voObj.setStrMsgType("1");

			if (nAccFlag == 1)
			{

				try
				{
					pKeyLogDao.setStrKeyname("ACCOUNT");
					pKeyLogDao.setStrStartkey(strAccountNo);
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrError(err);
					pKeyLogDao.setStrHospCode(voObj.getStrHospitalCode());
					pKeyLogDao.setStrSeatid(voObj.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);

				} catch (Exception e1)
				{
					voObj
							.setStrMsgString("PatWalletTransDAO.insertOfflinePartPayment()--> " + e1.getMessage() + "-->" + err);
					voObj.setStrMsgType("1");

				}

			}
			if (nBillFlag == 1)
			{

				voObj.setStrBillNo("0");

				try
				{
					pKeyLogDao.setStrKeyname("BILL_IPD");
					pKeyLogDao.setStrStartkey(strBillNo);
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrError(err);
					pKeyLogDao.setStrHospCode(voObj.getStrHospitalCode());
					pKeyLogDao.setStrSeatid(voObj.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);

				} catch (Exception e1)
				{
					voObj
							.setStrMsgString("PatWalletTransDAO.insertOfflinePartPayment()--> " + e1.getMessage() + "-->" + err);
					voObj.setStrMsgType("1");

				}

			}

			if (nAppFlag == 1)
			{

				try
				{
					pKeyLogDao.setStrKeyname("APPROVAL");
					pKeyLogDao.setStrStartkey(strAppId);
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrError(err);
					pKeyLogDao.setStrHospCode(voObj.getStrHospitalCode());
					pKeyLogDao.setStrSeatid(voObj.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);

				} catch (Exception e1)
				{
					voObj
							.setStrMsgString("PatWalletTransDAO.insertOfflinePartPayment()--> " + e1.getMessage() + "-->" + err);
					voObj.setStrMsgType("1");

				}

			}

		} finally
		{

			if (dao != null)
			{
				dao.free();
				dao = null;
			}

			if (pKey != null)
				pKey = null;
			if (pKeyLogDao != null)
				pKeyLogDao = null;
			if (patAccDao != null)
				patAccDao = null;
			if (clientPatDao != null)
				clientPatDao = null;
			if (billDao != null)
				billDao = null;
			if (billReceiptDao != null)
				billReceiptDao = null;
			if (patAccSerDao != null)
				patAccSerDao = null;
			if (outBoundDao != null)
				outBoundDao = null;
			if (paymentDao != null)
				paymentDao = null;
			if (billReqTrfDao != null)
				billReqTrfDao = null;
			if (approvalDao != null)
				approvalDao = null;

		}

	}

	/**
	 * Inserts Off-line Receipt Services Details
	 * 
	 * @param voObj
	 */
	public static void insertOfflineReceiptServices(PatWalletTransVO voObj)
	{
		int nBillFlag = 0;
		int nAppFlag = 0;

		String strBillNo = "0";
		String strDiscAppId = "0";

		int nInsertedIndex1 = 0;
		int nInsertedIndex2 = 0;

		HisDAO dao = null;

		PrimaryKeyDAO pKey = null;
		PrimaryKeyLogDAO pKeyLogDao = null;
		PaymentDAO paymentDao = null;

		try
		{

			dao = new HisDAO("BILLING", "PatWalletTransDAO.insertOfflineReceiptServices");
			pKey = new PrimaryKeyDAO();
			pKeyLogDao = new PrimaryKeyLogDAO();
			paymentDao = new PaymentDAO();

			// Bill Generation
			pKey.setStrBlockkey("1");
			pKey.setStrHospCode(voObj.getStrHospitalCode());
			pKey.setStrAppendHospcodeFlag("1");

			if (voObj.getStrOffLineHospitalService().equals("2"))
			{
				pKey.setStrKeyname("BILL_IPD");
			} 
			else
			{
				pKey.setStrKeyname("BILL");
			}

			pKey.select(dao);
			strBillNo = pKey.getStrPrimrayKeyValue();
			voObj.setStrBillNo(strBillNo);

			nBillFlag = 1;

			PatWalletTransDAO.getCounterId(voObj);

			String strCounterId = voObj.getStrCounterId().replace("^", "#").split("#")[0];

			nInsertedIndex1 = dao.setProcedure("{call pkg_bill_dml.dml_offline_bill(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			dao.setProcInValue(nInsertedIndex1, "modval", "1", 1);
			dao.setProcInValue(nInsertedIndex1, "billNo", strBillNo, 2);
			dao.setProcInValue(nInsertedIndex1, "hosp_code", voObj.getStrHospitalCode(), 3);
			dao.setProcInValue(nInsertedIndex1, "patAccNo", voObj.getStrOffLineAccountNo(), 4);
			dao.setProcInValue(nInsertedIndex1, "puk", voObj.getStrCrNo(), 5);
			dao.setProcInValue(nInsertedIndex1, "admNo", voObj.getStrOffLineAdmissionNo(), 6);
			dao.setProcInValue(nInsertedIndex1, "episodeCode", voObj.getStrOffLineEpisode(), 7);
			dao.setProcInValue(nInsertedIndex1, "catCode", voObj.getStrOffLineTreatmentCategory().replace("^", "#").split("#")[0], 8);
			dao.setProcInValue(nInsertedIndex1, "netAmt", voObj.getStrOfflineTotalRecAmount(), 9);
			dao.setProcInValue(nInsertedIndex1, "clientPaidAmt", voObj.getStrOfflineMaxClientBenefitAmount(), 10);
			dao.setProcInValue(nInsertedIndex1, "patPaidAmt", voObj.getStrOfflinePatNetPayAmount(), 11);
			dao.setProcInValue(nInsertedIndex1, "actTrfCost", voObj.getStrOfflineTotalActualTariffAmount(), 12);
			dao.setProcInValue(nInsertedIndex1, "disAmt", voObj.getStrOfflineTotalDiscountAmount(), 13);
			dao.setProcInValue(nInsertedIndex1, "serTaxAmt", voObj.getStrOfflineTotalServiceTaxAmount(), 14);
			dao.setProcInValue(nInsertedIndex1, "deptCode", voObj.getStrOffLineRaisingDepartment(), 15);
			dao.setProcInValue(nInsertedIndex1, "chargeTypeId", voObj.getStrOffLineHospitalService(), 16);
			dao.setProcInValue(nInsertedIndex1, "wardId", voObj.getStrOffLineSpecialWard(), 17);
			dao.setProcInValue(nInsertedIndex1, "bServiceId", voObj.getStrOffLineBillingService(), 18);
			// System.out.println("valuessssssssssssss"+voObj.getStrOffLineClientPatientNo());
			if (voObj.getStrOffLineClientPatientNo() == null || voObj.getStrOffLineClientPatientNo().trim().equalsIgnoreCase(""))
				voObj.setStrOffLineClientPatientNo("0");
			// System.out.println("valuessssssssssssss"+voObj.getStrOffLineClientPatientNo());
			dao.setProcInValue(nInsertedIndex1, "clientPatNo", voObj.getStrOffLineClientPatientNo(), 19);
			dao.setProcInValue(nInsertedIndex1, "depGrpId", voObj.getStrGroupId(), 24);
			dao.setProcInValue(nInsertedIndex1, "defUnitId", voObj.getStrQtyUnitId(), 25);
			dao.setProcInValue(nInsertedIndex1, "seatId", voObj.getStrSeatId(), 20);
			dao.setProcInValue(nInsertedIndex1, "moduleId", voObj.getStrModuleId(), 21);
			dao.setProcInValue(nInsertedIndex1, "ipAddr", voObj.getStrIpAddress(), 22);
			dao.setProcInValue(nInsertedIndex1, "counter_id", strCounterId, 23);
			dao.setProcInValue(nInsertedIndex1, "ipdChargeType_Id", voObj.getStrOffLineWard(), 26);
			//Billed In Credit Category
			if(voObj.getStrOffLineTreatmentCategory().replace("^","#").split("#")[1].equals("3") || voObj.getStrOffLineTreatmentCategory().replace("^","#").split("#")[1].equals("4"))
			{
				dao.setProcInValue(nInsertedIndex1, "staff_card_no_id", voObj.getStrEmployeeId(), 27);
				dao.setProcInValue(nInsertedIndex1, "staff_card_holder_name", voObj.getStrEmployeeName(), 28);
				dao.setProcInValue(nInsertedIndex1, "relation_with_card_holder", voObj.getStrRelationId(), 29);
				dao.setProcInValue(nInsertedIndex1, "card_validity", voObj.getStrCurrentDate(), 30);
			}
			else
			{
				dao.setProcInValue(nInsertedIndex1, "staff_card_no_id", "", 27);
				dao.setProcInValue(nInsertedIndex1, "staff_card_holder_name", "", 28);
				dao.setProcInValue(nInsertedIndex1, "relation_with_card_holder", "", 29);
				dao.setProcInValue(nInsertedIndex1, "card_validity", "", 30);
			}
			
			dao.setProcOutValue(nInsertedIndex1, "err", 1, 31);
			dao.execute(nInsertedIndex1, 1);
			nInsertedIndex2 = dao.setProcedure("{call pkg_bill_dml.dml_offline_bill_service(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			System.out.println("rows to be iterated dao::"+voObj.getStrOfflineTariffDetailsHidden().length);
			if (voObj.getStrOfflineTariffDetailsHidden() != null)
			{
				for (int i = 0, len = voObj.getStrOfflineTariffDetailsHidden().length; i < len; i++)
				{
					String[] strTariffDtlsVal = voObj.getStrOfflineTariffDetailsHidden()[i].replace('^', '#').split("#");
					String strTempTariffId = strTariffDtlsVal[0];
					String strTempGroupId = strTariffDtlsVal[1];
					// String strTempTariffRate = strTariffDtlsVal[3];
					String strTempRate = strTariffDtlsVal[4];
					String strUnit = strTariffDtlsVal[5];
					// String strTempBaseUnitVal = strTariffDtlsVal[6];
					String strTempIsPackage = strTariffDtlsVal[7];
					// String strTempIsAdvance = strTariffDtlsVal[8];
					String strTempIsRefundable = strTariffDtlsVal[9];
					// String strTempDefaultRate = strTariffDtlsVal[10];
					String strTempTariffActualRate = strTariffDtlsVal[11];
					// String strTempMaxDiscount = strTariffDtlsVal[12];
					String strTempServiceTax = strTariffDtlsVal[13];
					String strTempServiceId = strTariffDtlsVal[15];
					String strTempGTariffId = strTariffDtlsVal[16];
					
					System.out.println("Letter number is "+voObj.getStrCreditLetterNo()[i]);
					System.out.println(voObj.getStrCreditRefDate1()[i]);
				
					dao.setProcInValue(nInsertedIndex2, "modval", "1", 1);
					dao.setProcInValue(nInsertedIndex2, "billNo", strBillNo, 3);
					dao.setProcInValue(nInsertedIndex2, "hosp_code", voObj.getStrHospitalCode(), 2);
					dao.setProcInValue(nInsertedIndex2, "recNo", "1", 4);
					dao.setProcInValue(nInsertedIndex2, "patAccNo", voObj.getStrOffLineAccountNo(), 5);
					dao.setProcInValue(nInsertedIndex2, "puk", voObj.getStrCrNo(), 6);
					dao.setProcInValue(nInsertedIndex2, "catCode", voObj.getStrOffLineTreatmentCategory().replace("^", "#").split("#")[0], 7);
					dao.setProcInValue(nInsertedIndex2, "deptCode", voObj.getStrOffLineRaisingDepartment(), 8);
					dao.setProcInValue(nInsertedIndex2, "grpId", strTempGroupId, 9);
					dao.setProcInValue(nInsertedIndex2, "trfId", strTempTariffId, 10);
					dao.setProcInValue(nInsertedIndex2, "billQty", voObj.getStrOfflineTariffQty()[i], 11);
					dao.setProcInValue(nInsertedIndex2, "billQtyUnitId", voObj.getStrOfflineTariffUnit()[i].replace("^", "#").split("#")[0], 12);
					dao.setProcInValue(nInsertedIndex2, "rate", strTempRate, 13);
					dao.setProcInValue(nInsertedIndex2, "actRate", strTempTariffActualRate, 14);
					dao.setProcInValue(nInsertedIndex2, "rateUnitId", strUnit, 15);
					dao.setProcInValue(nInsertedIndex2, "serTax", strTempServiceTax, 16);
					dao.setProcInValue(nInsertedIndex2, "serTaxAmt", voObj.getStrOfflineTariffServiceTaxAmtVal()[i], 17);
					dao.setProcInValue(nInsertedIndex2, "trfNetAmt", voObj.getStrOfflineTariffNetAmount()[i], 18);
					dao.setProcInValue(nInsertedIndex2, "disAmt", voObj.getStrOfflineTariffDiscountAmtVal()[i], 21);
					if (voObj.getStrOfflineTariffDiscountConfigDetails() != null && !voObj.getStrOfflineTariffDiscount()[i].equals("0"))
					{
						String[] strDiscountDtlsValue = voObj.getStrOfflineTariffDiscountConfigDetails()[i].replace(',', '#').split("#");
						String strTempDiscUnit = strDiscountDtlsValue[0];
						String strTempDiscType = strDiscountDtlsValue[1];
						String strTempDiscBy = strDiscountDtlsValue[2];
						String strTempDiscReason = strDiscountDtlsValue[4];
						String strTempDiscDate = strDiscountDtlsValue[5];
						if (!voObj.getStrOfflineTariffDiscount()[i].equals("0"))
						{
							pKey.setStrKeyname("APPROVAL");
							pKey.setStrBlockkey("1");
							pKey.setStrHospCode(voObj.getStrHospitalCode());
							pKey.select(dao);
							strDiscAppId = pKey.getStrPrimrayKeyValue();
							if (!strDiscAppId.equals("") || strDiscAppId != null)
							{
								nAppFlag = 1;
							}
							dao.setProcInValue(nInsertedIndex2, "appId", strDiscAppId, 19);
						} else
						{
							dao.setProcInValue(nInsertedIndex2, "appId", "", 19);
						}
						dao.setProcInValue(nInsertedIndex2, "disUnit", strTempDiscUnit, 20);
						dao.setProcInValue(nInsertedIndex2, "disType", strTempDiscType, 22);
						dao.setProcInValue(nInsertedIndex2, "disBy", strTempDiscBy, 23);
						dao.setProcInValue(nInsertedIndex2, "disReason", strTempDiscReason, 24);
						dao.setProcInValue(nInsertedIndex2, "disDate", strTempDiscDate, 25);
					} 
					else
					{

						dao.setProcInValue(nInsertedIndex2, "appId", "0", 19);
						dao.setProcInValue(nInsertedIndex2, "disUnit", "0", 20);
						dao.setProcInValue(nInsertedIndex2, "disType", "0", 22);
						dao.setProcInValue(nInsertedIndex2, "disBy", "0", 23);
						dao.setProcInValue(nInsertedIndex2, "disReason", "NA", 24);
						dao.setProcInValue(nInsertedIndex2, "disDate", "sysdate", 25);
					}

					dao.setProcInValue(nInsertedIndex2, "chargeTypeId", voObj.getStrOffLineHospitalService(), 26);
					dao.setProcInValue(nInsertedIndex2, "wardId", voObj.getStrOffLineSpecialWard(), 27);
					dao.setProcInValue(nInsertedIndex2, "bServiceId", voObj.getStrOffLineBillingService(), 28);
					dao.setProcInValue(nInsertedIndex2, "serviceId", strTempServiceId, 29);
					dao.setProcInValue(nInsertedIndex2, "gblTrfId", strTempGTariffId, 30);
					dao.setProcInValue(nInsertedIndex2, "isRefund", strTempIsRefundable, 31);
					dao.setProcInValue(nInsertedIndex2, "isPackage", strTempIsPackage, 32);
					dao.setProcInValue(nInsertedIndex2, "seatId", voObj.getStrSeatId(), 33);
					dao.setProcInValue(nInsertedIndex2, "ipdChargeType_Id", voObj.getStrOffLineWard(), 34);
					
					//Billed In Credit Category
					//if(voObj.getStrOffLineTreatmentCategory().replace("^","#").split("#")[1].equals("3") || voObj.getStrOffLineTreatmentCategory().replace("^","#").split("#")[1].equals("4"))
					if(voObj.getStrCreditPaymentType()[i].equals("11"))
					{
						dao.setProcInValue(nInsertedIndex2, "credit_letter_no",voObj.getStrCreditLetterNo()[i],35);
						dao.setProcInValue(nInsertedIndex2, "credit_ref_date", voObj.getStrCreditRefDate1()[i],36);
						dao.setProcInValue(nInsertedIndex2, "amt_paid_by_client","0",37);
						dao.setProcInValue(nInsertedIndex2, "amt_paid_by_emp", strTempTariffActualRate,38);
						dao.setProcInValue(nInsertedIndex2, "credit_bill_status", "2",39);//Offline No Approval--0-Not Approved,1-Approved,2-No Apporval Required---//1-Approved,0-Pending,2-Direct(No Approval)
						dao.setProcInValue(nInsertedIndex2, "uploaded_file_path", "",40);
						dao.setProcInValue(nInsertedIndex2, "credit_bill_flag", "1",41);
					}
					else
					{
						dao.setProcInValue(nInsertedIndex2, "credit_letter_no","",35);
						dao.setProcInValue(nInsertedIndex2, "credit_ref_date", "",36);
						dao.setProcInValue(nInsertedIndex2, "amt_paid_by_client", strTempTariffActualRate,37);
						dao.setProcInValue(nInsertedIndex2, "amt_paid_by_emp", "0",38);
						dao.setProcInValue(nInsertedIndex2, "credit_bill_status", "0",39);//1-Approved,0-Pending,2-Direct(No Approval)
						dao.setProcInValue(nInsertedIndex2, "uploaded_file_path", "",40);
						dao.setProcInValue(nInsertedIndex2, "credit_bill_flag", "0",41);
					}					
					dao.setProcOutValue(nInsertedIndex2, "err", 1, 42);
					dao.execute(nInsertedIndex2, 1);
				}
			}

			// Payment Details

			int nSlNo = 0;
			int nPaymentLenght = voObj.getStrOfflinePaymentMode().length;

			for (int i = 0; i < nPaymentLenght; i++)
			{

				nSlNo = nSlNo + 1;
				paymentDao.setStrBillNo(strBillNo);

				paymentDao.setStrPaymentMode(voObj.getStrOfflinePaymentMode()[i]);
				paymentDao.setStrRecieptNo("1");
				paymentDao.setStrSerialNo(String.valueOf(nSlNo));
				paymentDao.setStrChargeTypeId(voObj.getStrOffLineHospitalService());
				paymentDao.setStrBServiceId(voObj.getStrOffLineBillingService());

				String payDtl[] = { "", "" };

				if (voObj.getStrOfflinePaymentDtls() != null)
				{
					paymentDao.setStrPaymentDetails(voObj.getStrOfflinePaymentDtls()[i]);
					payDtl = voObj.getStrOfflinePaymentDtls()[i].replace(",", "#").split("#");
				} 
				else
				{
					paymentDao.setStrPaymentDetails("");
				}

				if (voObj.getStrOfflinePaymentMode()[i].equals("2") || voObj.getStrOfflinePaymentMode()[i].equals("3"))
				{
					paymentDao.setStrPaymentModeNo(payDtl[1]);
					paymentDao.setStrPaymentStatus("0");

				} 
				else if (voObj.getStrOfflinePaymentMode()[i].equals("4") || voObj.getStrOfflinePaymentMode()[i].equals("5"))
				{
					paymentDao.setStrPaymentModeNo(payDtl[1]);
					paymentDao.setStrPaymentStatus("0");

				} 
				else if (voObj.getStrOfflinePaymentMode()[i].equals("6"))
				{

					paymentDao.setStrPaymentModeNo(payDtl[0]);
				} 
				else
				{
					paymentDao.setStrPaymentModeNo("");
					paymentDao.setStrPaymentStatus("1");
				}

				paymentDao.setStrRecieptType("1");
				paymentDao.setStrRecieptAmount(voObj.getStrOfflineAmount()[i]);

				paymentDao.setStrPuk(voObj.getStrCrNo());
				paymentDao.setStrProcessedBy("0");
				paymentDao.setStrCounterId(strCounterId);
				paymentDao.setStrSeatId(voObj.getStrSeatId());
				paymentDao.setStrIsValid("1");
				paymentDao.setStrHospitalCode(voObj.getStrHospitalCode());
				paymentDao.setStrNetAmount(voObj.getStrOfflineTotalRecAmount());
				paymentDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
				paymentDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));
				paymentDao.insert(dao);
			}

			/*
			 * if (voObj.getStrOffLineClientPatientNo().length() > 1 &&
			 * !voObj.getStrOfflineMaxClientBenefitAmount().equals("0")) {
			 * 
			 * nSlNo = nSlNo + 1;
			 * 
			 * paymentDao.setStrBillNo(strBillNo);
			 * 
			 * paymentDao.setStrPaymentMode("6"); paymentDao.setStrRecieptNo("1");
			 * paymentDao.setStrSerialNo(String.valueOf(nSlNo));
			 * paymentDao.setStrChargeTypeId(voObj
			 * .getStrOffLineHospitalService()); paymentDao
			 * .setStrBServiceId(voObj.getStrOffLineBillingService()); paymentDao
			 * .setStrPaymentDetails(voObj.getStrOfflineClientName());
			 * paymentDao.setStrPaymentModeNo(voObj
			 * .getStrOffLineClientPatientNo());
			 * paymentDao.setStrPaymentStatus("0");
			 * paymentDao.setStrRecieptType("1");
			 * paymentDao.setStrRecieptAmount(voObj
			 * .getStrOfflineMaxClientBenefitAmount());
			 * paymentDao.setStrPuk(voObj.getStrCrNo());
			 * paymentDao.setStrProcessedBy("0");
			 * paymentDao.setStrCounterId(strCounterId);
			 * paymentDao.setStrSeatId(voObj.getStrSeatId());
			 * paymentDao.setStrHospitalCode(voObj.getStrHospitalCode());
			 * paymentDao.setStrNetAmount(voObj.getStrOfflineTotalRecAmount());
			 * paymentDao.setStrIsValid("1");
			 * 
			 * paymentDao.insert(dao); }
			 */

			// Commented as per Change request HIS_PGIMER_Patient Billing_
			// DCD_16Nov11.doc.
			// This is for problem The start day in the bill should be the date
			// of deposit of the advance Payment
			/*
			 * if(voObj.getStrOffLineHospitalService().equals("2")){
			 * 
			 * String proc_name1 = "";
			 * 
			 * proc_name1 = "{call
			 * PKG_BILL_DML.dml_update_account_dtl(?,?,?,?,?,?,?,?)}";
			 * 
			 * int procIndex1 = 0;
			 * 
			 * 
			 * procIndex1 = dao.setProcedure(proc_name1);
			 * 
			 * dao.setProcInValue(procIndex1, "modval", "1");
			 * 
			 * dao.setProcInValue(procIndex1, "hosp_code",
			 * voObj.getStrHospitalCode()); dao.setProcInValue(procIndex1, "accNo",
			 * voObj.getStrOffLineAccountNo()); dao.setProcInValue(procIndex1,
			 * "wardId", voObj.getStrOffLineSpecialWard());
			 * dao.setProcInValue(procIndex1, "ipdChargeTypeId",
			 * voObj.getStrOffLineWard()); dao.setProcInValue(procIndex1, "patCat",
			 * voObj.getStrOffLineTreatmentCategory());
			 * dao.setProcInValue(procIndex1, "deptId",
			 * voObj.getStrOffLineRaisingDepartment());
			 * dao.setProcOutValue(procIndex1, "ERR", 1); // 1 for string return
			 * 
			 * dao.execute(procIndex1,1);
			 *  }
			 */

			synchronized (dao)
			{
				dao.fire();
			}

		} catch (Exception e)
		{
			 e.printStackTrace();
			String err = "err:" + e.getMessage();

			voObj.setStrMsgString("PatWalletTransDAO.insertOfflineReceiptServices()--> " + err);
			voObj.setStrMsgType("1");

			if (nBillFlag == 1)
			{

				voObj.setStrBillNo("0");

				try
				{

					if (voObj.getStrOffLineHospitalService().equals("2"))
					{
						pKeyLogDao.setStrKeyname("BILL_IPD");
					} else
					{
						pKeyLogDao.setStrKeyname("BILL");
					}

					pKeyLogDao.setStrStartkey(strBillNo);
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrError(err);
					pKeyLogDao.setStrHospCode(voObj.getStrHospitalCode());
					pKeyLogDao.setStrSeatid(voObj.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);

				} catch (Exception e1)
				{

					voObj
							.setStrMsgString("PatWalletTransDAO.insertOfflineReceiptServices()--> " + e1.getMessage() + "-->" + err);
					voObj.setStrMsgType("1");

				}

			}

			if (nAppFlag == 1)
			{

				try
				{
					pKeyLogDao.setStrKeyname("APPROVAL");
					pKeyLogDao.setStrStartkey(strDiscAppId);
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrError(err);
					pKeyLogDao.setStrHospCode(voObj.getStrHospitalCode());
					pKeyLogDao.setStrSeatid(voObj.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);

				} catch (Exception e1)
				{

					voObj
							.setStrMsgString("PatWalletTransDAO.insertOfflineReceiptServices()--> " + e1.getMessage() + "-->" + err);
					voObj.setStrMsgType("1");

				}

			}

		} finally
		{

			if (dao != null)
			{
				dao.free();
				dao = null;
			}

			pKey = null;
			pKeyLogDao = null;
			paymentDao = null;
		}

	}

	/**
	 * 
	 * @param voObj
	 */
	public static void insertOfflineRefundServices(PatWalletTransVO voObj)
	{

		int nAppFlag = 0;

		String strAppId = "0";

		int nInsertedIndex1 = 0;
		int nInsertedIndex2 = 0;

		HisDAO dao = null;

		PrimaryKeyDAO pKey = null;
		PrimaryKeyLogDAO pKeyLogDao = null;
		PaymentDAO paymentDao = null;

		try
		{

			dao = new HisDAO("Bill Transaction", "PatWalletTransDAO.insertOfflineRefundServices");
			pKey = new PrimaryKeyDAO();
			pKeyLogDao = new PrimaryKeyLogDAO();
			paymentDao = new PaymentDAO();

			String[] strBillDtls = voObj.getStrOfflineRefundBillDetails().replace("^", "#").split("#");

			String strBillNo = strBillDtls[0];
			// String strTransType = strBillDtls[2];
			String strPatAccNo = strBillDtls[4];
			// String strRequestNo = strBillDtls[5];
			// String strIsBill = strBillDtls[9];
			// String strIsOnline = strBillDtls[10];
			String strAdmissionNo = strBillDtls[11];
			// String strServiceId = strBillDtls[16];

			PatWalletTransDAO.getCounterId(voObj);

			String strCounterId = voObj.getStrCounterId().replace("^", "#").split("#")[0];

			voObj.setStrBillNo(strBillNo);

			PatWalletTransDAO.getRefundReceiptNo(voObj);

			String strRefundReceiptNo = voObj.getStrRefundReceiptNo();

			pKey.setStrKeyname("APPROVAL");
			pKey.setStrBlockkey("1");
			pKey.setStrHospCode(voObj.getStrHospitalCode());
			pKey.select(dao);

			strAppId = pKey.getStrPrimrayKeyValue();

			if (!strAppId.equals("") || strAppId != null)
			{
				nAppFlag = 1;
			}

			nInsertedIndex1 = dao
					.setProcedure("{call pkg_bill_dml.dml_offline_refund(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			dao.setProcInValue(nInsertedIndex1, "modval", "1", 1);
			dao.setProcInValue(nInsertedIndex1, "billNo", strBillNo, 3);
			dao.setProcInValue(nInsertedIndex1, "hosp_code", voObj.getStrHospitalCode(), 2);
			dao.setProcInValue(nInsertedIndex1, "refRecNo", strRefundReceiptNo, 4);
			dao.setProcInValue(nInsertedIndex1, "refAppId", strAppId, 5);
			dao.setProcInValue(nInsertedIndex1, "refundDate", voObj.getStrOfflineRefundDate(), 6);
			dao.setProcInValue(nInsertedIndex1, "refundBy", voObj.getStrOffLineRefundBy(), 7);
			dao.setProcInValue(nInsertedIndex1, "refundReason", voObj.getStrOffLineRefundReasonText(), 8);
			dao.setProcInValue(nInsertedIndex1, "patAccNo", strPatAccNo, 9);
			dao.setProcInValue(nInsertedIndex1, "puk", voObj.getStrCrNo(), 10);
			dao.setProcInValue(nInsertedIndex1, "admNo", strAdmissionNo, 11);
			dao.setProcInValue(nInsertedIndex1, "episodeCode", voObj.getStrOffLineEpisode(), 12);
			dao.setProcInValue(nInsertedIndex1, "catCode", voObj.getStrOffLineTreatmentCategory().replace("^", "#").split("#")[0], 13);
			dao.setProcInValue(nInsertedIndex1, "netAmt", voObj.getStrOfflineTotalRecAmount(), 14);
			dao.setProcInValue(nInsertedIndex1, "actTrfCost", voObj.getStrOfflineRefundTotalActualTariffAmount(), 15);
			dao.setProcInValue(nInsertedIndex1, "disAmt", voObj.getStrOfflineRefundTotalDiscountAmount(), 16);
			dao.setProcInValue(nInsertedIndex1, "serTaxAmt", voObj.getStrOfflineRefundTotalServiceTaxAmount(), 17);
			dao.setProcInValue(nInsertedIndex1, "penAmt", voObj.getStrOfflineRefundTotalPenaltyAmount(), 18);
			dao.setProcInValue(nInsertedIndex1, "deptCode", voObj.getStrOffLineRaisingDepartment(), 19);
			dao.setProcInValue(nInsertedIndex1, "chargeTypeId", voObj.getStrOffLineHospitalService(), 20);
			dao.setProcInValue(nInsertedIndex1, "wardId", voObj.getStrOffLineWard(), 21);
			dao.setProcInValue(nInsertedIndex1, "bServiceId", voObj.getStrOffLineBillingService(), 22);
			dao.setProcInValue(nInsertedIndex1, "clientPatNo", voObj.getStrOffLineClientPatientNo(), 23);
			dao.setProcInValue(nInsertedIndex1, "clientBalAmt", voObj.getStrOffLineBalanceAmount(), 24);
			dao.setProcInValue(nInsertedIndex1, "depGrpId", voObj.getStrGroupId(), 29);
			dao.setProcInValue(nInsertedIndex1, "defUnitId", voObj.getStrQtyUnitId(), 30);
			dao.setProcInValue(nInsertedIndex1, "seatId", voObj.getStrSeatId(), 25);
			dao.setProcInValue(nInsertedIndex1, "moduleId", voObj.getStrModuleId(), 26);
			dao.setProcInValue(nInsertedIndex1, "ipAddr", voObj.getStrIpAddress(), 27);
			dao.setProcInValue(nInsertedIndex1, "counter_id", strCounterId, 28);
			// output value
			dao.setProcOutValue(nInsertedIndex1, "err", 1, 31);
			// keep in batch
			dao.execute(nInsertedIndex1, 1);
			nInsertedIndex2 = dao
					.setProcedure("{call pkg_bill_dml.dml_offline_refund_service(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			if (voObj.getStrBillTariffVal() != null)
				for (int i = 0, len = voObj.getStrBillTariffVal().length; i < len; i++)
				{

					String[] strTariffDtlsVal = voObj.getStrBillTariffVal()[i].replace('^', '#').split("#");

					String strTempTariffId = strTariffDtlsVal[0];
					// String strTempRemQty = strTariffDtlsVal[1].replace("*",
					// "#").split("#")[0];
					// String strTempQtyUnitId = strTariffDtlsVal[2];
					String strTempTariffRate = strTariffDtlsVal[3];
					String strTempRateUnit = strTariffDtlsVal[4];
					String strTempGroupId = strTariffDtlsVal[5];
					String strTempActualRate = strTariffDtlsVal[6];
					String strTempServiceTax = strTariffDtlsVal[7];
					String strTempDiscUnit = strTariffDtlsVal[8];
					String strTempDiscType = strTariffDtlsVal[9];
					String strTempGTariffId = strTariffDtlsVal[10];
					String strTempAppId = strTariffDtlsVal[11];
					// String strTempUnitName = strTariffDtlsVal[12];
					// String strTempBaseUnitVal = strTariffDtlsVal[13];
					String strTempIsPackage = strTariffDtlsVal[14];
					// String strTempNetAmt = strTariffDtlsVal[15];
					// String strTempPenlty = strTariffDtlsVal[16];
					// String strTempPenltyAmt = strTariffDtlsVal[17];
					String strTempReceiptNo = strTariffDtlsVal[18];
					String strTempSServiceId = strTariffDtlsVal[19];

					dao.setProcInValue(nInsertedIndex2, "modval", "1", 1);
					dao.setProcInValue(nInsertedIndex2, "billNo", strBillNo, 3);
					dao.setProcInValue(nInsertedIndex2, "hosp_code", voObj.getStrHospitalCode(), 2);
					dao.setProcInValue(nInsertedIndex2, "trfRecNo", strTempReceiptNo, 4);
					dao.setProcInValue(nInsertedIndex2, "refRecNo", strRefundReceiptNo, 5);
					dao.setProcInValue(nInsertedIndex2, "patAccNo", strPatAccNo, 6);
					dao.setProcInValue(nInsertedIndex2, "puk", voObj.getStrCrNo(), 7);
					dao.setProcInValue(nInsertedIndex2, "catCode", voObj.getStrOffLineTreatmentCategory().replace("^", "#").split("#")[0], 8);
					dao.setProcInValue(nInsertedIndex2, "deptCode", voObj.getStrOffLineRaisingDepartment(), 9);
					dao.setProcInValue(nInsertedIndex2, "grpId", strTempGroupId, 10);
					dao.setProcInValue(nInsertedIndex2, "trfId", strTempTariffId, 11);
					dao.setProcInValue(nInsertedIndex2, "refQty", voObj.getStrBillTariffRefund()[i], 12);
					dao.setProcInValue(nInsertedIndex2, "refQtyUnitId", voObj.getStrBillTariffUnit()[i].replace("^", "#")
							.split("#")[0], 13);
					dao.setProcInValue(nInsertedIndex2, "rate", strTempTariffRate, 14);
					dao.setProcInValue(nInsertedIndex2, "actRate", strTempActualRate, 15);
					dao.setProcInValue(nInsertedIndex2, "rateUnitId", strTempRateUnit, 16);
					dao.setProcInValue(nInsertedIndex2, "serTax", strTempServiceTax, 17);
					dao.setProcInValue(nInsertedIndex2, "serTaxAmt", voObj.getStrOfflineRefundServiceTaxAmount()[i], 18);
					dao.setProcInValue(nInsertedIndex2, "trfNetAmt", voObj.getStrBillTariffRefundAmount()[i], 19);
					dao.setProcInValue(nInsertedIndex2, "penelty", voObj.getStrBillTariffPenalty()[i], 20);
					dao.setProcInValue(nInsertedIndex2, "penAmt", voObj.getStrOfflineRefundPenaltyAmount()[i], 21);
					dao.setProcInValue(nInsertedIndex2, "disAmt", voObj.getStrOfflineRefundDiscountAmount()[i], 24);
					dao.setProcInValue(nInsertedIndex2, "appId", strTempAppId, 22);
					dao.setProcInValue(nInsertedIndex2, "disUnit", strTempDiscUnit, 23);
					dao.setProcInValue(nInsertedIndex2, "disType", strTempDiscType, 25);
					dao.setProcInValue(nInsertedIndex2, "chargeTypeId", voObj.getStrOffLineHospitalService(), 26);
					dao.setProcInValue(nInsertedIndex2, "wardId", voObj.getStrOffLineWard(), 27);
					dao.setProcInValue(nInsertedIndex2, "bServiceId", voObj.getStrOffLineBillingService(), 28);
					dao.setProcInValue(nInsertedIndex2, "serviceId", strTempSServiceId, 29);
					dao.setProcInValue(nInsertedIndex2, "gblTrfId", strTempGTariffId, 30);
					dao.setProcInValue(nInsertedIndex2, "isPackage", strTempIsPackage, 31);
					dao.setProcInValue(nInsertedIndex2, "seatId", voObj.getStrSeatId(), 32);
					// output value
					dao.setProcOutValue(nInsertedIndex2, "err", 1, 33);
					dao.execute(nInsertedIndex2, 1);
				}

			// Payment Details

			int nSlNo = 0;
			int nPaymentLenght = voObj.getStrOfflinePaymentMode().length;

			for (int i = 0; i < nPaymentLenght; i++)
			{

				nSlNo = nSlNo + 1;

				paymentDao.setStrBillNo(strBillNo);

				paymentDao.setStrPaymentMode(voObj.getStrOfflinePaymentMode()[i]);
				paymentDao.setStrRecieptNo(strRefundReceiptNo);
				paymentDao.setStrSerialNo(String.valueOf(nSlNo));
				paymentDao.setStrChargeTypeId(voObj.getStrOffLineHospitalService());
				paymentDao.setStrBServiceId(voObj.getStrOffLineBillingService());

				String payDtl[] = { "", "" };

				if (voObj.getStrOfflinePaymentDtls() != null)
				{
					paymentDao.setStrPaymentDetails(voObj.getStrOfflinePaymentDtls()[i]);
					payDtl = voObj.getStrOfflinePaymentDtls()[i].replace(",", "#").split("#");
				} else
				{
					paymentDao.setStrPaymentDetails("");
				}

				if (voObj.getStrOfflinePaymentMode()[i].equals("2") || voObj.getStrOfflinePaymentMode()[i].equals("3"))
				{
					paymentDao.setStrPaymentModeNo(payDtl[1]);
					paymentDao.setStrPaymentStatus("0");

				} else if (voObj.getStrOfflinePaymentMode()[i].equals("4") || voObj.getStrOfflinePaymentMode()[i]
						.equals("5"))
				{
					paymentDao.setStrPaymentModeNo(payDtl[1]);
					paymentDao.setStrPaymentStatus("0");

				} else if (voObj.getStrOfflinePaymentMode()[i].equals("6"))
				{

					paymentDao.setStrPaymentModeNo(payDtl[0]);
				} else
				{
					paymentDao.setStrPaymentModeNo("");
					paymentDao.setStrPaymentStatus("1");
				}

				paymentDao.setStrRecieptType("2");
				paymentDao.setStrRecieptAmount(voObj.getStrOfflineAmount()[i]);

				paymentDao.setStrPuk(voObj.getStrCrNo());
				paymentDao.setStrProcessedBy("0");
				paymentDao.setStrCounterId(strCounterId);
				paymentDao.setStrSeatId(voObj.getStrSeatId());
				paymentDao.setStrIsValid("1");
				paymentDao.setStrHospitalCode(voObj.getStrHospitalCode());
				paymentDao.setStrNetAmount(voObj.getStrOfflineTotalRecAmount());

				paymentDao.insert(dao);
			}

			/*
			 * if (voObj.getStrOffLineClientPatientNo().length() > 1 &&
			 * !voObj.getStrOfflineMaxClientBenefitAmount().equals("0")) {
			 * 
			 * nSlNo = nSlNo + 1;
			 * 
			 * paymentDao.setStrBillNo(strBillNo);
			 * 
			 * paymentDao.setStrPaymentMode("6");
			 * paymentDao.setStrRecieptNo(strRefundReceiptNo);
			 * paymentDao.setStrSerialNo(String.valueOf(nSlNo));
			 * paymentDao.setStrChargeTypeId(voObj
			 * .getStrOffLineHospitalService()); paymentDao
			 * .setStrBServiceId(voObj.getStrOffLineBillingService()); paymentDao
			 * .setStrPaymentDetails(voObj.getStrOfflineClientName());
			 * paymentDao.setStrPaymentModeNo(voObj
			 * .getStrOffLineClientPatientNo());
			 * paymentDao.setStrPaymentStatus("0");
			 * paymentDao.setStrRecieptType("2");
			 * paymentDao.setStrRecieptAmount(voObj
			 * .getStrOfflineMaxClientBenefitAmount());
			 * paymentDao.setStrPuk(voObj.getStrCrNo());
			 * paymentDao.setStrProcessedBy("0");
			 * paymentDao.setStrCounterId(strCounterId);
			 * paymentDao.setStrSeatId(voObj.getStrSeatId());
			 * paymentDao.setStrHospitalCode(voObj.getStrHospitalCode());
			 * paymentDao.setStrIsValid("1");
			 * paymentDao.setStrNetAmount(voObj.getStrOfflineTotalRecAmount());
			 * 
			 * paymentDao.insert(dao); }
			 */

			synchronized (dao)
			{
				dao.fire();
			}

		} catch (Exception e)
		{

			String err = "err:" + e.getMessage();

			 e.printStackTrace();

			voObj.setStrMsgString("PatWalletTransDAO.insertOfflineRefundServices()--> " + e.getMessage());
			voObj.setStrMsgType("1");

			if (nAppFlag == 1)
			{

				try
				{
					pKeyLogDao.setStrKeyname("APPROVAL");
					pKeyLogDao.setStrStartkey(strAppId);
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrError(err);
					pKeyLogDao.setStrHospCode(voObj.getStrHospitalCode());
					pKeyLogDao.setStrSeatid(voObj.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);

				} catch (Exception e1)
				{
e1.printStackTrace();
					voObj
							.setStrMsgString("PatWalletTransDAO.insertOfflineReceiptServices()--> " + e1.getMessage() + "-->" + err);
					voObj.setStrMsgType("1");

				}

			}

		} finally
		{

			if (dao != null)
			{
				dao.free();
				dao = null;
			}

			pKey = null;
			pKeyLogDao = null;
			paymentDao = null;
		}

	}

	/**
	 * Inserts Offline Refund Advance
	 * 
	 * @param voObj
	 */
	public static void insertOfflineRefundAdmissionCancellation(PatWalletTransVO voObj)
	{

		int nInsertedIndex1 = 0;
		HisDAO dao = null;

		PaymentDAO paymentDao = null;

		try
		{

			dao = new HisDAO("Bill Transaction", "PatWalletTransDAO.insertOfflineRefundAdmissionCancellation");
			paymentDao = new PaymentDAO();

			PatWalletTransDAO.getCounterId(voObj);

			String strCounterId = voObj.getStrCounterId().replace("^", "#").split("#")[0];

			PatWalletTransDAO.getRefundReceiptNo(voObj);

			String strRefundReceiptNo = voObj.getStrRefundReceiptNo();

			nInsertedIndex1 = dao
					.setProcedure("{call pkg_bill_dml.dml_offline_pataccount_close(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			dao.setProcInValue(nInsertedIndex1, "modval", "1", 1);
			dao.setProcInValue(nInsertedIndex1, "hosp_code", voObj.getStrHospitalCode(), 2);
			dao.setProcInValue(nInsertedIndex1, "grpId", voObj.getStrGroupId(), 5);
			dao.setProcInValue(nInsertedIndex1, "billNo", voObj.getStrBillNo(), 3);
			dao.setProcInValue(nInsertedIndex1, "recNo", strRefundReceiptNo, 4);
			dao.setProcInValue(nInsertedIndex1, "defUnitId", voObj.getStrQtyUnitId(), 6);
			dao.setProcInValue(nInsertedIndex1, "refundDate", voObj.getStrOfflineRefundDate(), 7);
			dao.setProcInValue(nInsertedIndex1, "refundBy", voObj.getStrOffLineRefundBy(), 8);
			dao.setProcInValue(nInsertedIndex1, "refundReason", voObj.getStrOffLineRefundReasonText(), 9);
			dao.setProcInValue(nInsertedIndex1, "refundAmt", voObj.getStrOfflineTotalRecAmount(), 10);
			dao.setProcInValue(nInsertedIndex1, "patAccNo", voObj.getStrOffLineRefundAdvanceAccountNo(), 11);
			dao.setProcInValue(nInsertedIndex1, "puk", voObj.getStrCrNo(), 12);
			dao.setProcInValue(nInsertedIndex1, "penelty", voObj.getStrAdmissionCancellationPenalty(), 13);
			dao.setProcInValue(nInsertedIndex1, "penAmt", voObj.getStrRefundAdvancePaneltyAmt(), 14);
			// dao.setProcInValue(nInsertedIndex1, "wardId",
			// voObj.getStrOffLineWard(),15);
			dao.setProcInValue(nInsertedIndex1, "wardId", voObj.getStrOffLineSpecialWard(), 15);
			dao.setProcInValue(nInsertedIndex1, "chargeTypeId", voObj.getStrOffLineHospitalService(), 16);
			dao.setProcInValue(nInsertedIndex1, "bServiceId", voObj.getStrOffLineBillingService(), 17);
			dao.setProcInValue(nInsertedIndex1, "advance_refund", voObj.getStrOffLineBillingService(), 18);
			dao.setProcInValue(nInsertedIndex1, "acc_cat", "",19);
			dao.setProcInValue(nInsertedIndex1, "counterId", strCounterId, 20);
			dao.setProcInValue(nInsertedIndex1, "seatId", voObj.getStrSeatId(), 21);
			dao.setProcInValue(nInsertedIndex1, "str_adm_no", "", 22);
			dao.setProcOutValue(nInsertedIndex1, "err", 1, 23);
			dao.execute(nInsertedIndex1, 1);

			// Payment Details

			int nSlNo = 0;
			int nPaymentLenght = voObj.getStrOfflinePaymentMode().length;

			for (int i = 0; i < nPaymentLenght; i++)
			{

				nSlNo = nSlNo + 1;

				paymentDao.setStrBillNo(voObj.getStrBillNo());

				paymentDao.setStrPaymentMode(voObj.getStrOfflinePaymentMode()[i]);
				paymentDao.setStrRecieptNo(strRefundReceiptNo);
				paymentDao.setStrSerialNo(String.valueOf(nSlNo));
				paymentDao.setStrChargeTypeId(voObj.getStrOffLineHospitalService());
				paymentDao.setStrBServiceId(voObj.getStrOffLineBillingService());

				String payDtl[] = { "", "" };

				if (voObj.getStrOfflinePaymentDtls() != null)
				{
					paymentDao.setStrPaymentDetails(voObj.getStrOfflinePaymentDtls()[i]);
					payDtl = voObj.getStrOfflinePaymentDtls()[i].replace(",", "#").split("#");
				} else
				{
					paymentDao.setStrPaymentDetails("");
				}

				if (voObj.getStrOfflinePaymentMode()[i].equals("2") || voObj.getStrOfflinePaymentMode()[i].equals("3"))
				{
					paymentDao.setStrPaymentModeNo(payDtl[1]);
					paymentDao.setStrPaymentStatus("0");

				} else if (voObj.getStrOfflinePaymentMode()[i].equals("4") || voObj.getStrOfflinePaymentMode()[i]
						.equals("5"))
				{
					paymentDao.setStrPaymentModeNo(payDtl[1]);
					paymentDao.setStrPaymentStatus("0");

				} else if (voObj.getStrOfflinePaymentMode()[i].equals("6"))
				{

					paymentDao.setStrPaymentModeNo(payDtl[0]);
				} else
				{
					paymentDao.setStrPaymentModeNo("");
					paymentDao.setStrPaymentStatus("1");
				}

				paymentDao.setStrRecieptType("2");
				paymentDao.setStrRecieptAmount(voObj.getStrOfflineAmount()[i]);

				paymentDao.setStrPuk(voObj.getStrCrNo());
				paymentDao.setStrProcessedBy("0");
				paymentDao.setStrCounterId(strCounterId);
				paymentDao.setStrSeatId(voObj.getStrSeatId());
				paymentDao.setStrIsValid("1");
				paymentDao.setStrHospitalCode(voObj.getStrHospitalCode());
				paymentDao.setStrNetAmount(voObj.getStrOfflineTotalRecAmount());

				paymentDao.insert(dao);
			}

			/*
			 * if (voObj.getStrOffLineClientPatientNo().length() > 1 &&
			 * !voObj.getStrOfflineMaxClientBenefitAmount().equals("0")) {
			 * 
			 * nSlNo = nSlNo + 1;
			 * 
			 * paymentDao.setStrBillNo(voObj.getStrBillNo());
			 * 
			 * paymentDao.setStrPaymentMode("6");
			 * paymentDao.setStrRecieptNo(strRefundReceiptNo);
			 * paymentDao.setStrSerialNo(String.valueOf(nSlNo));
			 * paymentDao.setStrChargeTypeId(voObj
			 * .getStrOffLineHospitalService()); paymentDao
			 * .setStrBServiceId(voObj.getStrOffLineBillingService()); paymentDao
			 * .setStrPaymentDetails(voObj.getStrOfflineClientName());
			 * paymentDao.setStrPaymentModeNo(voObj
			 * .getStrOffLineClientPatientNo());
			 * paymentDao.setStrPaymentStatus("0");
			 * paymentDao.setStrRecieptType("2");
			 * paymentDao.setStrRecieptAmount(voObj
			 * .getStrOfflineMaxClientBenefitAmount());
			 * paymentDao.setStrPuk(voObj.getStrCrNo());
			 * paymentDao.setStrProcessedBy("0");
			 * paymentDao.setStrCounterId(strCounterId);
			 * paymentDao.setStrSeatId(voObj.getStrSeatId());
			 * paymentDao.setStrHospitalCode(voObj.getStrHospitalCode());
			 * paymentDao.setStrIsValid("1");
			 * paymentDao.setStrNetAmount(voObj.getStrOfflineTotalRecAmount());
			 * 
			 * paymentDao.insert(dao); }
			 */

			synchronized (dao)
			{
				dao.fire();
			}

		} catch (Exception e)
		{

			String err = "err:" + e.getMessage();

			voObj.setStrMsgType("1");

			voObj.setStrMsgString("PatWalletTransDAO.insertOfflineReceiptServices()--> " + err);

		} finally
		{

			if (dao != null)
			{
				dao.free();
				dao = null;
			}

			paymentDao = null;
		}

	}

	/**
	 * Inserts Offline Refund Advance
	 * 
	 * @param voObj
	 */
	public static void insertOfflineRefundPartPayment(PatWalletTransVO voObj)
	{

		int nInsertedIndex1 = 0;
		HisDAO dao = null;

		PaymentDAO paymentDao = null;

		try
		{

			dao = new HisDAO("Bill Transaction", "PatWalletTransDAO.insertOfflineRefundAdmissionCancellation");
			paymentDao = new PaymentDAO();

			String[] strBillDtls = voObj.getStrOfflineRefundBillDetails().replace("^", "#").split("#");

			String strBillNo = strBillDtls[0];
			String strPatAccNo = strBillDtls[4];
			voObj.setStrBillNo(strBillNo);

			PatWalletTransDAO.getCounterId(voObj);

			String strCounterId = voObj.getStrCounterId().replace("^", "#").split("#")[0];

			PatWalletTransDAO.getRefundReceiptNo(voObj);

			String strRefundReceiptNo = voObj.getStrRefundReceiptNo();

			nInsertedIndex1 = dao
					.setProcedure("{call pkg_bill_dml.dml_offline_partpayment_refund(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			dao.setProcInValue(nInsertedIndex1, "modval", "1", 1);
			dao.setProcInValue(nInsertedIndex1, "hosp_code", voObj.getStrHospitalCode(), 2);
			dao.setProcInValue(nInsertedIndex1, "grpId", voObj.getStrGroupId(), 5);
			dao.setProcInValue(nInsertedIndex1, "billNo", strBillNo, 3);
			dao.setProcInValue(nInsertedIndex1, "recNo", strRefundReceiptNo, 4);
			dao.setProcInValue(nInsertedIndex1, "defUnitId", voObj.getStrQtyUnitId(), 6);
			dao.setProcInValue(nInsertedIndex1, "refundDate", voObj.getStrOfflineRefundDate(), 7);
			dao.setProcInValue(nInsertedIndex1, "refundBy", voObj.getStrOffLineRefundBy(), 8);
			dao.setProcInValue(nInsertedIndex1, "refundReason", voObj.getStrOffLineRefundReasonText(), 9);
			dao.setProcInValue(nInsertedIndex1, "refundAmt", voObj.getStrOfflineTotalRecAmount(), 10);
			dao.setProcInValue(nInsertedIndex1, "patAccNo", strPatAccNo, 11);
			dao.setProcInValue(nInsertedIndex1, "puk", voObj.getStrCrNo(), 12);
			dao.setProcInValue(nInsertedIndex1, "penelty", voObj.getStrAdmissionCancellationPenalty(), 13);
			dao.setProcInValue(nInsertedIndex1, "penAmt", voObj.getStrRefundAdvancePaneltyAmt(), 14);
			dao.setProcInValue(nInsertedIndex1, "wardId", voObj.getStrOffLineWard(), 15);
			dao.setProcInValue(nInsertedIndex1, "chargeTypeId", voObj.getStrOffLineHospitalService(), 16);
			dao.setProcInValue(nInsertedIndex1, "bServiceId", voObj.getStrOffLineBillingService(), 17);
			dao.setProcInValue(nInsertedIndex1, "counterId", strCounterId, 18);
			dao.setProcInValue(nInsertedIndex1, "seatId", voObj.getStrSeatId(), 19);
			dao.setProcOutValue(nInsertedIndex1, "err", 1, 20);
			dao.execute(nInsertedIndex1, 1);

			// Payment Details

			int nSlNo = 0;
			int nPaymentLenght = voObj.getStrOfflinePaymentMode().length;

			for (int i = 0; i < nPaymentLenght; i++)
			{

				nSlNo = nSlNo + 1;

				paymentDao.setStrBillNo(strBillNo);

				paymentDao.setStrPaymentMode(voObj.getStrOfflinePaymentMode()[i]);
				paymentDao.setStrRecieptNo(strRefundReceiptNo);
				paymentDao.setStrSerialNo(String.valueOf(nSlNo));
				paymentDao.setStrChargeTypeId(voObj.getStrOffLineHospitalService());
				paymentDao.setStrBServiceId(voObj.getStrOffLineBillingService());

				String payDtl[] = { "", "" };

				if (voObj.getStrOfflinePaymentDtls() != null)
				{
					paymentDao.setStrPaymentDetails(voObj.getStrOfflinePaymentDtls()[i]);
					payDtl = voObj.getStrOfflinePaymentDtls()[i].replace(",", "#").split("#");
				} else
				{
					paymentDao.setStrPaymentDetails("");
				}

				if (voObj.getStrOfflinePaymentMode()[i].equals("2") || voObj.getStrOfflinePaymentMode()[i].equals("3"))
				{
					paymentDao.setStrPaymentModeNo(payDtl[1]);
					paymentDao.setStrPaymentStatus("0");

				} else if (voObj.getStrOfflinePaymentMode()[i].equals("4") || voObj.getStrOfflinePaymentMode()[i]
						.equals("5"))
				{
					paymentDao.setStrPaymentModeNo(payDtl[1]);
					paymentDao.setStrPaymentStatus("0");

				} else if (voObj.getStrOfflinePaymentMode()[i].equals("6"))
				{

					paymentDao.setStrPaymentModeNo(payDtl[0]);
				} else
				{
					paymentDao.setStrPaymentModeNo("");
					paymentDao.setStrPaymentStatus("1");
				}

				paymentDao.setStrRecieptType("2");
				paymentDao.setStrRecieptAmount(voObj.getStrOfflineAmount()[i]);

				paymentDao.setStrPuk(voObj.getStrCrNo());
				paymentDao.setStrProcessedBy("0");
				paymentDao.setStrCounterId(strCounterId);
				paymentDao.setStrSeatId(voObj.getStrSeatId());
				paymentDao.setStrIsValid("1");
				paymentDao.setStrHospitalCode(voObj.getStrHospitalCode());
				paymentDao.setStrNetAmount(voObj.getStrOfflineTotalRecAmount());

				paymentDao.insert(dao);
			}

			/*
			 * if (voObj.getStrOffLineClientPatientNo().length() > 1 &&
			 * !voObj.getStrOfflineMaxClientBenefitAmount().equals("0")) {
			 * 
			 * nSlNo = nSlNo + 1;
			 * 
			 * paymentDao.setStrBillNo(voObj.getStrBillNo());
			 * 
			 * paymentDao.setStrPaymentMode("6");
			 * paymentDao.setStrRecieptNo(strRefundReceiptNo);
			 * paymentDao.setStrSerialNo(String.valueOf(nSlNo));
			 * paymentDao.setStrChargeTypeId(voObj
			 * .getStrOffLineHospitalService()); paymentDao
			 * .setStrBServiceId(voObj.getStrOffLineBillingService()); paymentDao
			 * .setStrPaymentDetails(voObj.getStrOfflineClientName());
			 * paymentDao.setStrPaymentModeNo(voObj
			 * .getStrOffLineClientPatientNo());
			 * paymentDao.setStrPaymentStatus("0");
			 * paymentDao.setStrRecieptType("2");
			 * paymentDao.setStrRecieptAmount(voObj
			 * .getStrOfflineMaxClientBenefitAmount());
			 * paymentDao.setStrPuk(voObj.getStrCrNo());
			 * paymentDao.setStrProcessedBy("0");
			 * paymentDao.setStrCounterId(strCounterId);
			 * paymentDao.setStrSeatId(voObj.getStrSeatId());
			 * paymentDao.setStrHospitalCode(voObj.getStrHospitalCode());
			 * paymentDao.setStrIsValid("1");
			 * paymentDao.setStrNetAmount(voObj.getStrOfflineTotalRecAmount());
			 * 
			 * paymentDao.insert(dao); }
			 */

			synchronized (dao)
			{
				dao.fire();
			}

		} catch (Exception e)
		{

			String err = "err:" + e.getMessage();

			voObj.setStrMsgType("1");

			voObj.setStrMsgString("PatWalletTransDAO.insertOfflineReceiptServices()--> " + err);

		} finally
		{

			if (dao != null)
			{
				dao.free();
				dao = null;
			}

			paymentDao = null;
		}

	}

	/**
	 * executes the BILL_MST.getCounterDtl() by passing Module Id, IP Address,
	 * Hospital Code and Mode 1. which generates the Counter and set to Value
	 * Object's counterId.
	 * 
	 * @param voObj -
	 *           Value Object
	 */
	public static void getCounterId(PatWalletTransVO voObj)
	{

		String strCounterId = "";
		String strMode = "1";

		String strFunc = "";
		strFunc = "{? = call BILL_MST.getCounterDtl(?,?,?,?)}";
		HisDAO dao = null;
		int nfuncIndex = 0;

		try
		{
			dao = new HisDAO("billing", "transactions.PatWalletTransDAO .getCounterId()");

			nfuncIndex = dao.setFunction(strFunc);

			dao.setFuncInValue(nfuncIndex, 2, voObj.getStrModuleId());
			dao.setFuncInValue(nfuncIndex, 3, voObj.getStrIpAddress());
			dao.setFuncInValue(nfuncIndex, 4, voObj.getStrHospitalCode());
			dao.setFuncInValue(nfuncIndex, 5, strMode);
			dao.setFuncOutValue(nfuncIndex, 1);

			dao.executeFunction(nfuncIndex);

			strCounterId = dao.getFuncString(nfuncIndex);

			voObj.setStrCounterId(strCounterId);

		} catch (Exception e)
		{
			voObj.setStrMsgString("PatWalletTransDAO.getCounterId() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (dao != null)
			{
				dao.free();
				dao = null;
			}
		}

	}

	public static void getRefundReceiptNo(PatWalletTransVO voObj)
	{

		String strRefundReceiptNo = "";

		String strFunc = "";
		strFunc = "{? = call BILL_MST.get_refund_receiptno(?,?)}";
		HisDAO dao = null;
		int nfuncIndex = 0;

		try
		{
			dao = new HisDAO("billing", "transactions.PatWalletTransDAO .getRefundReceiptNo()");

			nfuncIndex = dao.setFunction(strFunc);

			dao.setFuncInValue(nfuncIndex, 2, voObj.getStrHospitalCode());
			dao.setFuncInValue(nfuncIndex, 3, voObj.getStrBillNo());
			dao.setFuncOutValue(nfuncIndex, 3);

			dao.executeFuncForNumeric(nfuncIndex);
			strRefundReceiptNo = dao.getFuncNumeric(nfuncIndex);

			voObj.setStrRefundReceiptNo(strRefundReceiptNo);

		} catch (Exception e)
		{

			voObj.setStrMsgString("PatWalletTransDAO.getRefundReceiptNo() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (dao != null)
			{
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * Inserts Off-line Advance Details
	 * 
	 * @param voObj
	 */
	public static PatWalletTransVO openZeroBalanceAccount(PatWalletTransVO voObj)
	{

		int nAccFlag = 0;
		int nBillFlag = 0;
		int nAppFlag = 0;

		boolean fClientFlag = false;

		String strAccountNo = "0";
		String strBillNo = "0";
		String strAppId = "0";

		//String counterId = "0";

		HisDAO dao = null;

		PrimaryKeyDAO pKey = null;
		PrimaryKeyLogDAO pKeyLogDao = null;
		PatientAccountDAO patAccDao = null;

		try
		{

			dao = new HisDAO("Bill Transaction", "PatWalletTransDAO.openZeroBalanceAccount");
			pKey = new PrimaryKeyDAO();
			pKeyLogDao = new PrimaryKeyLogDAO();
			patAccDao = new PatientAccountDAO();

			HisUtil hisUtil = new HisUtil("Bill Transaction", "PatWalletTransDAO");

			voObj.setStrCurrentDate(hisUtil.getASDate("dd-MMM-yyyy"));

			String strTemp = voObj.getStrOffLineEpisode().replace("^", "#").split("#")[0];

			voObj.setStrOffLineEpisode(strTemp);

			// Counter Id Generation.

			PatWalletTransDAO.getCounterId(voObj);

			//counterId = voObj.getStrCounterId().replace("^", "#").split("#")[0];

			// Open or Retive Existing Account.
			strAccountNo = voObj.getStrOffLineAccountNo();

			if (strAccountNo == null || strAccountNo.equals("0"))
			{

				pKey.setStrBlockkey("1");
				pKey.setStrKeyname("ACCOUNT");
				pKey.setStrHospCode(voObj.getStrHospitalCode());

				pKey.select(dao);

				strAccountNo = pKey.getStrPrimrayKeyValue();

				nAccFlag = 1;

			}

			voObj.setStrOffLineAccountNo(strAccountNo);

			if (voObj.getStrClientPatientNo().equals(""))
				voObj.setStrClientPatientNo("0");

			if (!voObj.getStrClientPatientNo().equals("0"))
			{
				fClientFlag = true;
			}

			patAccDao.setStrPatAccNo(strAccountNo);
			patAccDao.setStrChargeTypeId(voObj.getStrOffLineHospitalService());
			patAccDao.setStrDeptCode(voObj.getStrOffLineRaisingDepartment());
			patAccDao.setStrPuk(voObj.getStrCrNo());

			if (fClientFlag)
			{
				patAccDao.setStrClientPatientNo(voObj.getStrOffLineClientPatientNo());
				patAccDao.setStrSancAmt("0");
				patAccDao.setStrClientBalance("0");
				patAccDao.setStrClientPatBoundDt(voObj.getStrCurrentDate());
			}

			patAccDao.setStrAdmNo(voObj.getStrOffLineAdmissionNo());
			patAccDao.setStrEpisodeCode(voObj.getStrOffLineEpisode());
			patAccDao.setStrCatCode(voObj.getStrOffLineTreatmentCategory());
			// seting amount to 0 for opening zero balance account
			patAccDao.setStrPatRecdAmt("0");
			patAccDao.setStrPatAccountStatus("1");
			patAccDao.setStrSeatId(voObj.getStrSeatId());
			patAccDao.setStrHospitalCode(voObj.getStrHospitalCode());
			patAccDao.setStrIsValid("1");

			patAccDao.insert(dao);

			synchronized (dao)
			{
				dao.fire();
			}

		} catch (Exception e)
		{

			String err = "err:" + e.getMessage();

			voObj.setStrMsgType("1");
			voObj.setStrMsgString("PatWalletTransDAO.insertOfflineAdvance() --> BILL_IPD -->" + err);

			if (nAccFlag == 1)
			{

				try
				{
					pKeyLogDao.setStrKeyname("ACCOUNT");
					pKeyLogDao.setStrStartkey(strAccountNo);
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrError(err);
					pKeyLogDao.setStrHospCode(voObj.getStrHospitalCode());
					pKeyLogDao.setStrSeatid(voObj.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);

				} catch (Exception e1)
				{

					voObj
							.setStrMsgString("PatWalletTransDAO.insertOfflineAdvance()--> " + e1.getMessage() + "-->" + err);
					voObj.setStrMsgType("1");

				}

			}
			if (nBillFlag == 1)
			{

				voObj.setStrBillNo("0");

				try
				{

					pKeyLogDao.setStrKeyname("BILL_IPD");
					pKeyLogDao.setStrStartkey(strBillNo);
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrError(err);
					pKeyLogDao.setStrHospCode(voObj.getStrHospitalCode());
					pKeyLogDao.setStrSeatid(voObj.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);

				} catch (Exception e1)
				{

					voObj
							.setStrMsgString("PatWalletTransDAO.insertOfflineAdvance()--> " + e1.getMessage() + "-->" + err);
					voObj.setStrMsgType("1");

				}

			}

			if (nAppFlag == 1)
			{

				try
				{
					pKeyLogDao.setStrKeyname("APPROVAL");
					pKeyLogDao.setStrStartkey(strAppId);
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrError(err);
					pKeyLogDao.setStrHospCode(voObj.getStrHospitalCode());
					pKeyLogDao.setStrSeatid(voObj.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);

				} catch (Exception e1)
				{

					voObj
							.setStrMsgString("PatWalletTransDAO.insertOfflineAdvance()--> " + e1.getMessage() + "-->" + err);
					voObj.setStrMsgType("1");

				}

			}

		} finally
		{

			if (dao != null)
			{
				dao.free();
				dao = null;
			}

			if (pKey != null)
				pKey = null;
			if (pKeyLogDao != null)
				pKeyLogDao = null;
			if (patAccDao != null)
				patAccDao = null;

		}
		return voObj;
	}
	
	
	
	
	
	
	public   void getWaivedBy(PatWalletTransVO vo) 
	{
		HisUtil hisutil = null;
		WebRowSet w1;
		WebRowSet w2;
		String str2 = null;
		String str1 = null;
		
		try 
		{
			hisutil = new HisUtil("transaction", "PatWalletTransDAO");
			
			w2 = getWaiverBy(vo);
			w1=  getRelations(vo);
			
			if (w2 != null && w2.size() > 0) 
			{
				str1 = hisutil.getOptionValue(w2, "0", "0^Select Value", true);
			} 
			else 
			{
				str1 = "";
			}
			
			if (w1 != null && w1.size() > 0) 
			{
				str2 = hisutil.getOptionValue(w1, "0", "0^Select Value", true);
			} 
			else 
			{
				str2 = "";
			}
			
			//waiver by field..
			vo.setStrRmk(str1);
			vo.setStrRelationWs(str2);
		} 
		catch (Exception e) 
		{
			vo.setStrMsgString("CreditBillApprovalTransDAO.getWaivedBy() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		}
		finally
		{
			w2=null;
		}
	}
	
	public WebRowSet getWaiverBy(PatWalletTransVO vo) 
	{
		WebRowSet ws = null;
		String proc_name = "";
		proc_name = "{call PKG_SIMPLE_VIEW.PROC_CONSULTANT_NAME(?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		
		try 
		{
			dao = new HisDAO("billing","transactions.CreditBillApprovalTransDAO .getWaiverBy()");
			nprocIndex = dao.setProcedure(proc_name);
			
			dao.setProcInValue(nprocIndex, "deptunitCode", "0",1);
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nprocIndex, "seatId", vo.getStrSeatId(),3);			
			dao.setProcOutValue(nprocIndex, "err", 1,4);
			dao.setProcOutValue(nprocIndex, "resultset", 2,5);
			
			dao.executeProcedureByPosition(nprocIndex);
			strErr = dao.getString(nprocIndex, "err");
			
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
		} 
		catch (Exception e) 
		{
			vo.setStrMsgString("PatWalletTransDAO.getWaiverBy() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		} 
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;
			}
		}
		return ws;
	}
	
	
	
	
	
	
	public WebRowSet getRelations(PatWalletTransVO vo) 
	{
		WebRowSet ws = null;
		String proc_name = "";
		proc_name = "{call PKG_BILL_VIEW.proc_get_relations(?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		
		try 
		{
			dao = new HisDAO("billing","transactions.CreditBillApprovalTransDAO .getWaiverBy()");
			nprocIndex = dao.setProcedure(proc_name);
			
			dao.setProcInValue(nprocIndex, "modeval", "1",1);
			dao.setProcOutValue(nprocIndex, "err", 1,2);
			dao.setProcOutValue(nprocIndex, "resultset", 2,3);
			
			dao.executeProcedureByPosition(nprocIndex);
			strErr = dao.getString(nprocIndex, "err");
			
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			vo.setStrMsgString("PatWalletTransDAO.getWaiverBy() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		} 
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;
			}
		}
		return ws;
	}
	
	public static void getAdmEpisodeTreatmentCatDtl(PatWalletTransVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_simple_view.proc_HRGT_EPISODE_DTL(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strDeptCode = voObj.getStrOffLineRaisingDepartment();//Last Visited Department
		String strCrNo = voObj.getStrCrNo();

		String strErr = "";

		try
		{
			daoObj = new HisDAO("Billing", "PatWalletTransDAO.getEpisodeList");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "modeval", "2", 1);
			daoObj.setProcInValue(nProcIndex, "deptCode", strDeptCode, 2);
			daoObj.setProcInValue(nProcIndex, "puk", strCrNo, 3);			
			daoObj.setProcOutValue(nProcIndex, "err", 1, 4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");				
				if(ws.next())
				{
					voObj.setStrOffLineRaisingDepartment(ws.getString(1));
					voObj.setStrOffLineEpisode(ws.getString(2));
					voObj.setStrOffLineTreatmentCategory(ws.getString(4)+"^"+ws.getString(5));
					voObj.setStrTreatmentCategory(ws.getString(4));
					ws.beforeFirst();
				}
				
				voObj.setAdmEpisodeTreatCatDeptDtls(ws);
			} 
			else
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e)
		{
			voObj.setStrMsgString("PatWalletTransDAO.getEpisodeList() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} 
		finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	public static void getWalletAccountDetails(PatWalletTransVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_HBLT_PATACCOUNT_DTL(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strDeptCode = voObj.getStrOffLineRaisingDepartment();//Last Visited Department
		String strCrNo = voObj.getStrCrNo();

		String strErr = "";

		try
		{
			daoObj = new HisDAO("Billing", "PatWalletTransDAO.getEpisodeList");
			nProcIndex = daoObj.setProcedure(strProcName);
			if(voObj.getStrAccountStatus().equals("4"))
			daoObj.setProcInValue(nProcIndex, "modeval", "9", 5);
			else
		    daoObj.setProcInValue(nProcIndex, "modeval", "10", 5);	
			//daoObj.setProcInValue(nProcIndex, "deptCode", strDeptCode, 2);
			daoObj.setProcInValue(nProcIndex, "puk", strCrNo, 2);			
			daoObj.setProcInValue(nProcIndex, "accno", "0", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospitalCode(),6);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 7);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 8);
			daoObj.setProcInValue(nProcIndex, "chargeTypeId", voObj.getStrChargeTypeId(), 3);
			daoObj.setProcInValue(nProcIndex, "activeAccount", "1", 4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");				
				if(ws.next())
				{
					voObj.setStrWalletAccountNo (ws.getString(1));
					voObj.setStrWalletAccountOpenDate(ws.getString(2));
					voObj.setStrWalletDepositedAmount(ws.getString(3));
					voObj.setStrWalletBalanceAmount(ws.getString(4));
					voObj.setStrWalletAccountStatus(ws.getString(5));
					voObj.setStrMobileNo1(ws.getString(7));
					voObj.setStrChargeType(ws.getString(8));
					voObj.setStrOffLineWard(ws.getString(9));
					voObj.setStrOffLineSpecialWard(ws.getString(10));
					//ws.beforeFirst();
				}
				
			//	voObj.setAdmEpisodeTreatCatDeptDtls(ws);
			} 
			else
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e)
		{
			voObj.setStrMsgString("PatWalletTransDAO.getEpisodeList() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} 
		finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}
	}

	public static void getWalletAccountSummary(PatWalletTransVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_Wallet_account_summary(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strDeptCode = voObj.getStrOffLineRaisingDepartment();//Last Visited Department
		String strCrNo = voObj.getStrCrNo();

		String strErr = "";

		try
		{
			daoObj = new HisDAO("Billing", "PatWalletTransDAO.getEpisodeList");
			nProcIndex = daoObj.setProcedure(strProcName);
			System.out.println("voObj.getStrWalletAccountNo()  DAO:: "+voObj.getStrWalletAccountNo());
			if(voObj.getStrAccountStatus().equals("4") )
			{
            System.out.println("voObj.getStrAccountStatus()"+voObj.getStrAccountStatus());
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 1);
			}
			else if(voObj.getStrAccountStatus().equals("2"))
				daoObj.setProcInValue(nProcIndex, "modeval", "2", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode() ,2);
			if(voObj.getStrWalletAccountNo()!=null && voObj.getStrWalletAccountNo()!="")
			daoObj.setProcInValue(nProcIndex, "Walletaccount_no",voObj.getStrWalletAccountNo(), 3);
			else
				daoObj.setProcInValue(nProcIndex, "Walletaccount_no","0", 3);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");		
				System.out.println("ws.size>>>>"+ws.size());
				voObj.setWsWalletAccountSummary(ws);
				
			//	voObj.setAdmEpisodeTreatCatDeptDtls(ws);
			} 
			else
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e)
		{
			voObj.setStrMsgString("PatWalletTransDAO.getEpisodeList() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} 
		finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}
	}

	public static void CLOSEACCOUNT(PatWalletTransVO voObj) 
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_dml.dml_online_lf_finalsettlement(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		HisDAO dao = null;
		PrimaryKeyDAO pKey = null;

		String strDeptCode = voObj.getStrOffLineRaisingDepartment();//Last Visited Department
		String strCrNo = voObj.getStrCrNo();

		String strErr = "";
		String strBillNo="";
		String counterId = "0";

		try
		{
			daoObj = new HisDAO("Billing", "PatWalletTransDAO.getEpisodeList");
			dao = new HisDAO("Bill Transaction", "PatWalletTransDAO.CLOSEACCOUNT");
			nProcIndex = daoObj.setProcedure(strProcName);
			System.out.println("voObj.getStrWalletAccountNo()  DAO:: "+voObj.getStrWalletAccountNo());
			
			pKey = new PrimaryKeyDAO();
			pKey.setStrBlockkey("1");
			pKey.setStrKeyname("BILL");
			pKey.setStrHospCode(voObj.getStrHospitalCode());
 System.out.println("voObj.getStrHospitalCode()"+voObj.getStrHospitalCode());
			pKey.select(dao);

			strBillNo = pKey.getStrPrimrayKeyValue();

			voObj.setStrBillNo(strBillNo);
			PatWalletTransDAO.getCounterId(voObj);

			counterId = voObj.getStrCounterId().replace("^", "#").split("#")[0];
			
			daoObj.setProcInValue(nProcIndex, "modeval", "2", 1);
			daoObj.setProcInValue(nProcIndex, "patAccNo",voObj.getStrWalletAccountNo() , 2);
			daoObj.setProcInValue(nProcIndex, "puk",voObj.getStrCrNo() , 3); //voObj.getStrCrNo()
			daoObj.setProcInValue(nProcIndex, "catCode", voObj.getStrCatgoryCode(), 4); // voObj.getStrCatgoryCode()
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(), 5);
			daoObj.setProcInValue(nProcIndex, "reqno", "", 6);
			daoObj.setProcInValue(nProcIndex, "billno", voObj.getStrBillNo(), 7);
			daoObj.setProcInValue(nProcIndex, "grpid", "100", 8);
			daoObj.setProcInValue(nProcIndex, "defunitid", "1701", 9);
			daoObj.setProcInValue(nProcIndex, "expenseamt", "0", 10);
			daoObj.setProcInValue(nProcIndex, "patpaidamt", voObj.getRefundAmount(), 11);
			daoObj.setProcInValue(nProcIndex, "counterid", counterId, 12);
			daoObj.setProcInValue(nProcIndex, "seatid", voObj.getStrSeatId(), 13);
			daoObj.setProcInValue(nProcIndex, "ip", GetNetworkAddress.GetAddress("ip"), 14);
			daoObj.setProcInValue(nProcIndex, "mac", GetNetworkAddress.GetAddress("mac"), 15);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 16);
			
			

		
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			
		} 
		catch (Exception e)
		{
			voObj.setStrMsgString("PatWalletTransDAO.getEpisodeList() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} 
		finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}
		
	}
	
	
	public static void GetCrFromWalletNo(PatWalletTransVO voObj) 
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_HBLT_PATACCOUNT_DTL(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;		
		String Walletno= voObj.getStrWalletNo();
		String strErr = "";

		try
		{
			daoObj = new HisDAO("Billing", "PatWalletTransDAO.GetCrFromWalletNo");
			nProcIndex = daoObj.setProcedure(strProcName);
			if(voObj.getStrAccountStatus().equals("4"))
			{
				System.out.println("inside if"+voObj.getStrWalletNo()+"--"+voObj.getStrHospitalCode());
				daoObj.setProcInValue(nProcIndex, "modeval", "9", 5);
			}
			else
			{
				System.out.println("inside else"+voObj.getStrWalletNo()+"--"+voObj.getStrHospitalCode());
				daoObj.setProcInValue(nProcIndex, "modeval", "10", 5);
			}
			daoObj.setProcInValue(nProcIndex, "puk", "0", 2);			
			daoObj.setProcInValue(nProcIndex, "accno", Walletno, 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospitalCode(),6);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 7);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 8);
			daoObj.setProcInValue(nProcIndex, "chargeTypeId", voObj.getStrChargeTypeId(), 3);
			daoObj.setProcInValue(nProcIndex, "activeAccount", "1", 4);
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");				
				if(ws.next())
				{					
					voObj.setStrCrNo(ws.getString(6));
				}
			} 
			else
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e)
		{
			voObj.setStrMsgString("PatWalletTransDAO.GetCrFromWalletNo() --> " + e.getMessage());
			voObj.setStrMsgType("1");
		} 
		finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}		
	}
	
	public static void chkExistingMobileNo(PatWalletTransVO voObj) 
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_check_existing_mobile(?,?,?,?,?)}";
		int nProcIndex = 0;		
		String Walletno= voObj.getStrWalletNo();
		String strErr = "";

		try
		{
			daoObj = new HisDAO("Billing", "PatWalletTransDAO.chkExistingMobileNo");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "0", 1);			
			daoObj.setProcInValue(nProcIndex, "mobileNo", voObj.getStrMobileNo1(), 2);
			daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospitalCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 5);
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");				
				if(ws.next())
				{					
					voObj.setStrCheckMobileNO(ws.getString(1));
				}
			} 
			else
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e)
		{
			voObj.setStrMsgString("PatWalletTransDAO.chkExistingMobileNo() --> " + e.getMessage());
			voObj.setStrMsgType("1");
		} 
		finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}		
	}
}

