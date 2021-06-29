package ipd.transactions;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

public class DischargeCancelTransDAO {

	/**
	 * This function is used to set Department name on main page
	 * @param vo
	 */
	/*public static void setPatientName(DischargeCancelTransVO vo)
	{
		String strProcName = "{call proc_visitadmission_advice_dtl(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try {
				daoObj = new HisDAO("IPD--->Discharge Cancellation",
					"DischargeCancelTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "puk", strCrNum);
				daoObj.setProcInValue(nProcIndex, "hosp_code", "108");
				daoObj.setProcOutValue(nProcIndex, "err", 1);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2);
				daoObj.executeProcedure(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {

					if (ws.next()) {

						vo.setStrAdmnNo(ws.getString(1));
						vo.setStrAdmnDate(ws.getString(2));
						vo.setStrRoomBed(ws.getString(6));
						vo.setStrWard(ws.getString(7));
					}

				} else {
					throw new Exception(strErr);
				}

		} catch (Exception e) {
			
			vo.setStrMsgString("VisitorPassTransDAO.setDeptName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}*/

	 public void getRsnRmk(DischargeCancelTransVO vo)
	   {
		   HisUtil hisutil = new HisUtil("transaction", "DischargeCancelTransDAO");
		 //  WebRowSet w;
		   WebRowSet w1;
		   try
		  {
		//	 w=getTransferRsn(vo);
		     w1=getAdvisedBy(vo);
		   	 vo.setStrDisBy(w1);
			 String str1 = hisutil.getOptionValue(vo.getStrDisBy(), "0", "0^Select value", true);
			 vo.setStrRmk(str1);
		  }
		  catch(Exception e)
		  {
		  }
		  w1=null;
	   }


	 public WebRowSet getTransferRsn(DischargeCancelTransVO vo)
	   {
		   WebRowSet ws = null;
		   HisDAO dao = null;
			int nQryIndex;
			try {
				dao = new HisDAO("ipd","transactions.DischargeCancelTransDAO.getTransferRsn()");
				nQryIndex = dao.setQuery("select HBLSTR_REMARKS from HBLT_REMARKS_MST where GNUM_ISVALID="+1);
			    ws = dao.executeQry(nQryIndex);
			    }
			catch (Exception e)
			{
				vo.setStrMsgString("DischargeCancelTransDAO.getAdvisedBy() --> "
						+ e.getMessage());
		      vo.setStrMsgType("1");
			} finally {
				if(dao!=null){
				dao.free();
				dao = null;
			}
		}
	       return ws;
	   }

	 public WebRowSet getAdvisedBy(DischargeCancelTransVO vo)
	   {
		    WebRowSet ws = null;
		    String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
			HisDAO daoObj = null;
			int nProcIndex = 0;
			String strErr = "";
			try {
			  daoObj = new HisDAO("ipd","transactions.DischargeCancelTransDAO .getAdvisedBy()");
			  nProcIndex = daoObj.setProcedure(strProcName);
			  daoObj.setProcInValue(nProcIndex, "modVal", "3",1);
			  daoObj.setProcInValue(nProcIndex, "deptunitcode", vo.getStrDeptUnitCode(),2);
		      daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),3);
		      daoObj.setProcInValue(nProcIndex, "seatId", "",4);
		      daoObj.setProcOutValue(nProcIndex, "err", 1,5);
		      daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
		      
		      daoObj.executeProcedureByPosition(nProcIndex);
		      strErr = daoObj.getString(nProcIndex, "err");
		      if (strErr == null)
		        strErr = "";
		      ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			} catch (Exception e) {
				vo.setStrMsgString("DischargeCancelTransDAO.getAdvisedBy() --> "
						+ e.getMessage());
		vo.setStrMsgType("1");
			} finally {
				if(daoObj!=null){
					daoObj.free();
					daoObj = null;
				}
			}
	       return ws;
	   }

	 /**
		 * This function is used to details in room type combo on bed status pop up window
		 * @param vo
		 */
		public static void setRoomTypeDtl(DischargeCancelTransVO vo) {
			HisDAO daoObj = null;
			WebRowSet ws = null;
			String strProcName = "{call pkg_ipd_view.proc_roomtype_a(?,?,?,?)}";
			int nProcIndex = 0;
			String temp="";
			HisUtil  util=null;
			util = new HisUtil("ipd","DischargeCancelTransDAO");
			String strErr = "";
			try {
				daoObj = new HisDAO("Discharge Cancellation Transaction",
						"DischargeCancelTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modval","1",1);
				daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),2);
				daoObj.setProcOutValue(nProcIndex, "err", 1,3);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {
					vo.setRoomTypeWS(ws);
					temp=util.getOptionValue(ws,"0","0^Select Value", false);
					vo.setStrRoomType(temp);

				} else {
					throw new Exception(strErr);
				}
			} catch (Exception e) {
				vo.setStrMsgString("PatientTransferTransDAO.setRoomTypeDtl() --> "
								+ e.getMessage());
				vo.setStrMsgType("1");
			} finally {
				if (daoObj != null) {
					daoObj.free();
					daoObj = null;
				}
			}

		}

		/**
		 * This function is used to bring details in bed type combo in bed details pop up window
		 * @param voObj
		 */
		public static void setBedTypeDtl(DischargeCancelTransVO voObj) {

			HisDAO daoObj = null;
			WebRowSet ws = null;
			String temp="";
			HisUtil  util=null;
			util = new HisUtil("ipd","DischargeCancelTransDAO");
			String strProcName = "{call pkg_ipd_view.proc_bed_type_mst(?,?,?,?)}";
			int nProcIndex = 0;
			String strErr = "";
			try {
				daoObj = new HisDAO("Discharge Cancellation Transaction",
						"DischargeCancelTransDAO");

				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex,"modval","1",1);
				daoObj.setProcInValue(nProcIndex,"hosp_code",voObj.getStrHospitalCode(),2);
				daoObj.setProcOutValue(nProcIndex, "err", 1,3);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				temp=util.getOptionValue(ws,"0","0^Select Value", false);
				voObj.setStrBedType(temp);
				if (strErr.equals("")) {

					voObj.setBedTypeWS(ws);
				} else {
					throw new Exception(strErr);
				}

			} catch (Exception e) {

				voObj
						.setStrMsgString("DischargeCancelTransDAO.setBedTypeDtl() --> "
								+ e.getMessage());
				voObj.setStrMsgType("1");
			} finally {
				if (daoObj != null) {
					daoObj.free();
					daoObj = null;
				}
			}

		}

		/**
		 * This function is used to bring ward,room and bed details in case of Ms Approval
		 * @param vo
		 */
		public static void getPatDtl_Msapproval(DischargeCancelTransVO vo) {

			HisDAO daoObj = null;
			WebRowSet ws = null;
			String strProcName = "{call pkg_ipd_view.proc_pat_adm_msapproval(?,?,?,?,?,?,?)}";
			int nProcIndex = 0;
			String strErr = "";
			try {
					daoObj = new HisDAO("Discharge Cancellation Transaction",
						"DischargeCancelTransDAO");
					nProcIndex = daoObj.setProcedure(strProcName);
					daoObj.setProcInValue(nProcIndex, "modeVal","1",1);
					daoObj.setProcInValue(nProcIndex, "pukno",vo.getStrCrNo(),2);
					daoObj.setProcInValue(nProcIndex, "advno",vo.getStrAdviceAdmNo(),3);
					daoObj.setProcInValue(nProcIndex, "bookingdate","",4);
					//daoObj.setProcInValue(nProcIndex, "bookingdate","24-Jul-2008");
					daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),5);
					daoObj.setProcOutValue(nProcIndex, "err", 1,6);
					daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
					
					daoObj.executeProcedureByPosition(nProcIndex);
					strErr = daoObj.getString(nProcIndex, "err");
					if (strErr == null)
						strErr = "";
					ws = daoObj.getWebRowSet(nProcIndex, "resultset");
					
					if (strErr.equals("")) {
						if(ws.next())
						{
							vo.setStrWardTypeCode(ws.getString(1));
							vo.setStrWardCode(ws.getString(2));
							vo.setStrDeptCode(ws.getString(3));
							vo.setStrRoomCode(ws.getString(4));
							vo.setStrBedCode(ws.getString(5));
							vo.setStrMsApprovalStatus(ws.getString(6));
							vo.setStrWardName(ws.getString(7));
							vo.setStrRoom(ws.getString(8));
						}

					} else {
								throw new Exception(strErr);
							}

			} catch (Exception e) {
					vo.setStrMsgString("DischargeCancelTransDAO.getPatDtl_Msapproval() --> "
							+ e.getMessage());
					vo.setStrMsgType("1");
			}
			finally {
				if (daoObj != null) {
					daoObj.free();
					daoObj = null;
				}
			}

		}

		/**
		 * This function is used to bring room details in room combo on the basis of room type code
		 * @param vo
		 */
		public static void getRoomValues(DischargeCancelTransVO vo)
		{
			HisDAO daoObj = null;
			WebRowSet ws = null;
			String temp="";
			HisUtil  util=null;
			util = new HisUtil("ipd","DischargeCancelTransDAO");
			String strProcName = "{call pkg_ipd_view.proc_roomconfig(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			int nProcIndex = 0;
			String strErr = "";

			try
			{
				daoObj = new HisDAO("Discharge Cancellation Transaction",
					   "DischargeCancelTransDAO");
	        nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval","3",1);
		   daoObj.setProcInValue(nProcIndex, "roomtypcode",vo.getStrRoomTypeCode(),2);
		   daoObj.setProcInValue(nProcIndex, "wardcode",vo.getStrWardCode(),3);
		   daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),4);
			daoObj.setProcInValue(nProcIndex, "unitcode",vo.getStrUnitCode(),5);
			daoObj.setProcInValue(nProcIndex, "age",vo.getStrAge(),6);
			daoObj.setProcInValue(nProcIndex, "deptcode","",7);
			daoObj.setProcInValue(nProcIndex, "deptunitcode",vo.getStrUnitValue(),8);
			daoObj.setProcInValue(nProcIndex, "gender",vo.getStrSex(),9);
			daoObj.setProcInValue(nProcIndex, "treatment_cat",vo.getStrTreatmentCategoryCode(),10);
			daoObj.setProcInValue(nProcIndex, "puk_no",vo.getStrCrNo(),11);
			daoObj.setProcInValue(nProcIndex, "diseasetypcode","0",12);
		   daoObj.setProcOutValue(nProcIndex, "err", 1,13);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,14);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
	        ws = daoObj.getWebRowSet(nProcIndex, "resultset");
	    	temp=util.getOptionValue(ws,"0","0^Select Value", false);
			vo.setStrRoom(temp);
	        if (strErr.equals("")) {
	        	vo.setRoomWs(ws);
			} else {
				throw new Exception(strErr);
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				vo.setStrMsgString("DischargeCancelTransDAO.getRoomValues() --> "
						+ e.getMessage());
				vo.setStrMsgType("1");
			}
			finally {
				if (daoObj != null) {
					daoObj.free();
					daoObj = null;
				}
			}
		}

		/**
		 * This function is used to bring the bed details in bed combo
		 * @param vo
		 */
		public static void getBed(DischargeCancelTransVO vo)
		{
			HisDAO daoObj = null;
			WebRowSet ws = null;
			String temp="";
			HisUtil  util=null;
			util = new HisUtil("ipd","DischargeCancelTransDAO");
			String strProcName = "{call pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
			int nProcIndex = 0;
			String strErr = "";

			try
			{
				daoObj = new HisDAO("Discharge Cancellation Transaction",
					   "DischargeCancelTransDAO");
	        nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval","12",1);
		    daoObj.setProcInValue(nProcIndex, "wardcode",vo.getStrWardCode(),2);
		    daoObj.setProcInValue(nProcIndex, "roomno",vo.getStrRoomCode(),3);
		    daoObj.setProcInValue(nProcIndex, "bedtypcode",vo.getStrBedTypeCode(),4);
		    daoObj.setProcInValue(nProcIndex, "bedstatcode","10",5);
		    daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),6);
		    daoObj.setProcInValue(nProcIndex, "deptunit",vo.getStrDeptUnitCode(),7);
		    daoObj.setProcInValue(nProcIndex, "bedCode",vo.getStrRoomTypeCode(),8);
		    daoObj.setProcInValue(nProcIndex, "shr_chk",vo.getSharableChk(),9);
		    daoObj.setProcOutValue(nProcIndex, "err", 1,10);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,11);
			//This value contains room type code only to show bed combo according to room type selection for mode 12 only
			
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
	        ws = daoObj.getWebRowSet(nProcIndex, "resultset");
	        if (strErr.equals("")) {
				vo.setBedDetailWS(ws);
			} else {
				throw new Exception(strErr);
			}
	      //  if(vo.getStrPopUp().equals("0"))
	        {
	        temp=util.getOptionValue(ws,"0","0^Select Value", false);
	        vo.setStrBed(temp);
	        }
			}
			catch(Exception e)
			{
			vo.setStrMsgString("DischargeCancelTransDAO.getBedValues() --> "
						+ e.getMessage());
				vo.setStrMsgType("1");
			}
			finally {
				if (daoObj != null) {
					daoObj.free();
					daoObj = null;
				}
			}
		}

		/**
		 * This function is used to bring the bed details on bed details pop up window on the basis of ward code,room number,bed type code
		 * @param vo
		 */
		public static void getBedValues(DischargeCancelTransVO vo)
		{
			HisDAO daoObj = null;
			WebRowSet ws = null;
			String temp="";
			HisUtil  util=null;
			util = new HisUtil("ipd","DischargeCancelTransDAO");
			String strProcName = "{call pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
			int nProcIndex = 0;
			String strErr = "";

			try
			{
				daoObj = new HisDAO("Discharge Cancellation Transaction",
					   "DischargeCancelTransDAO");
	        nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval","4",1);
		    daoObj.setProcInValue(nProcIndex, "wardcode",vo.getStrWardCode(),2);
		    daoObj.setProcInValue(nProcIndex, "roomno",vo.getStrRoomCode(),3);
		    daoObj.setProcInValue(nProcIndex, "bedtypcode",vo.getStrBedTypeCode(),4);
		    daoObj.setProcInValue(nProcIndex, "bedstatcode","",5);
		    daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),6);
		    daoObj.setProcInValue(nProcIndex, "deptunit",vo.getStrDeptUnitCode(),7);
		    daoObj.setProcInValue(nProcIndex, "bedCode","",8);
		    daoObj.setProcInValue(nProcIndex, "shr_chk", "",9);
		    daoObj.setProcOutValue(nProcIndex, "err", 1,10);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,11);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
	        ws = daoObj.getWebRowSet(nProcIndex, "resultset");
	        if (strErr.equals("")) {
				vo.setBedDetailWS(ws);
			} else {
				throw new Exception(strErr);
			}
	        if(vo.getStrPopUp().equals("0"))
	        {
	        temp=util.getOptionValue(ws,"0","0^Select Value", false);
	        vo.setStrBed(temp);
	        }
			}
			catch(Exception e)
			{
			vo.setStrMsgString("DischargeCancelTransDAO.getBedValues() --> "
						+ e.getMessage());
				vo.setStrMsgType("1");
			}
			finally {
				if (daoObj != null) {
					daoObj.free();
					daoObj = null;
				}
			}
		}

		/**
		 * This function is used to set patient details in hidden fields on the main page
		 * @param vo
		 */
		public static void setpatStatusCode(DischargeCancelTransVO vo)
		{
			String strProcName = "{call Pkg_Ipd_View.Proc_Pat_Discharge_Cancel_Dtl(?,?,?,?,?,?)}";
			int nProcIndex = 0;

			String strErr = "";

			HisDAO daoObj = null;
			WebRowSet ws = null;

			String strAdmnNo = vo.getStrAdmnNo();
			String strDischargeCancelTime = vo.getStrDischargeCancellationTime();

			try {
				    daoObj = new HisDAO("ipd", "transactions.DischargeCancelTransDAO.setpatStatusCode()");
					nProcIndex = daoObj.setProcedure(strProcName);
					daoObj.setProcInValue(nProcIndex, "modval","1",1);
					daoObj.setProcInValue(nProcIndex, "cancel_time",strDischargeCancelTime,2);
					daoObj.setProcInValue(nProcIndex, "hipnum_admno",strAdmnNo,3);
					daoObj.setProcInValue(nProcIndex, "gnum_hospital_code",vo.getStrHospitalCode(),4);
					daoObj.setProcOutValue(nProcIndex, "err", 1,5);
					daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
					daoObj.executeProcedureByPosition(nProcIndex);
					strErr = daoObj.getString(nProcIndex, "err");
					if (strErr == null)
						strErr = "";
					ws = daoObj.getWebRowSet(nProcIndex, "resultset");
					if(ws.size()==0)
					{
						vo.setStrInvalidAdmNo("1");
					}
					else
					{
						if (strErr.equals(""))
						{
							if(ws.next())
							{
								vo.setStrAdmnStatusCode(ws.getString(1));
								vo.setStrPatDeadCode(ws.getString(2));
								vo.setStrCrNo(ws.getString(3));
								vo.setDeadStatus(ws.getString(4));
								vo.setDisStatus(ws.getString(5));
							}
						}
						else {
							throw new Exception(strErr);
						}
					}

			} catch (Exception e) {
				vo.setStrMsgString("DischargeCancelTransDAO.setpatStatusCode() --> "
						+ e.getMessage());
				vo.setStrMsgType("1");
			} finally {
				if (daoObj != null) {
					daoObj.free();
					daoObj = null;
				}
			}
		}

		/**
		 * This function is used to set patient details in hidden fields on the main page
		 * @param vo
		 */
		public static void setpatAdmnCode(DischargeCancelTransVO vo)
		{
			String strProcName = "{call pkg_ipd_view.Proc_Pat_Admstatus_Code_Dtl(?,?,?,?,?,?,?)}";
			int nProcIndex = 0;

			String strErr = "";

			HisDAO daoObj = null;
			WebRowSet ws = null;

			try {
				    daoObj = new HisDAO("ipd", "transactions.DischargeCancelTransDAO.setpatAdmnCode()");
					nProcIndex = daoObj.setProcedure(strProcName);
					daoObj.setProcInValue(nProcIndex, "modval","2",1);
					daoObj.setProcInValue(nProcIndex, "hipnum_admno",vo.getStrAdmnNo(),2);
					daoObj.setProcInValue(nProcIndex, "hrgnum_puk","",3);
					daoObj.setProcInValue(nProcIndex, "issuereq","2",4);			
					daoObj.setProcInValue(nProcIndex, "gnum_hospital_code",vo.getStrHospitalCode(),5);
					daoObj.setProcOutValue(nProcIndex, "err", 1,6);
					daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
					
					daoObj.executeProcedureByPosition(nProcIndex);
					strErr = daoObj.getString(nProcIndex, "err");
					if (strErr == null)
						strErr = "";
					ws = daoObj.getWebRowSet(nProcIndex, "resultset");
					vo.setStrDisAdm(ws);
					if (strErr.equals(""))
					{
					  if(ws.size()!=0)
					  {	  
						while(ws.next())
						{
					      	vo.setStrAdmnStatusCode(ws.getString(1));
							vo.setStrPatDeadCode(ws.getString(2));
						}
					  }	
					}
					 else {
						throw new Exception(strErr);
					}

			} catch (Exception e) {
				vo.setStrMsgString("DischargeCancelTransDAO.setpatAdmnCode() --> "
						+ e.getMessage());
				vo.setStrMsgType("1");
			} finally {
				if (daoObj != null) {
					daoObj.free();
					daoObj = null;
				}
			}
		}


		public static void insertdischargecancel(DischargeCancelTransVO vo)
		{
			String strProcName1 = "{call PKG_IPD_DML.Proc_discharge_cancellation(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

			String err="";
			String strCancelNo = "";
			int nProcIndex = 0;
			HisDAO daoObj = null;
			
			try
			{
				daoObj=new HisDAO("Discharge Cancellation","DischargeCancelTransDAO");

				nProcIndex = daoObj.setProcedure(strProcName1);
				daoObj.setProcInValue(nProcIndex, "modval","1",1);
				daoObj.setProcInValue(nProcIndex, "admNo", vo.getStrAdmnNo(),2);
				daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),3);
				daoObj.setProcInValue(nProcIndex, "puk", vo.getStrCrNo(),4);
				daoObj.setProcInValue(nProcIndex, "deptCode", vo.getStrDeptCode(),5);
				daoObj.setProcInValue(nProcIndex, "deptunit", vo.getStrDeptUnitCode(),6);
				daoObj.setProcInValue(nProcIndex, "wardcode", vo.getStrWardCode(),7);
				daoObj.setProcInValue(nProcIndex, "roomNo", vo.getStrRoom(),8);
				daoObj.setProcInValue(nProcIndex, "bedNo", vo.getStrBedCode(),9);
				daoObj.setProcInValue(nProcIndex, "seatId", vo.getStrSeatId(),10);
				daoObj.setProcInValue(nProcIndex, "cancelBy",vo.getStrRmk(),11);
				daoObj.setProcInValue(nProcIndex, "cancelReason", vo.getStrRsn(),12);
				daoObj.setProcInValue(nProcIndex, "billflag", vo.getStrBillIntegration(),13);
				daoObj.setProcOutValue(nProcIndex, "err",1,14);
				daoObj.setProcOutValue(nProcIndex, "slno",1,15);
				daoObj.executeProcedureByPosition(nProcIndex);
				
				err = daoObj.getString(nProcIndex, "err");
				
				
				if(err == null) err = "";
				
				if(err.equals(""))
				{
					
					strCancelNo = daoObj.getString(nProcIndex, "slno");
					
					vo.setStrCancelNo(strCancelNo);
					vo.setStrMsgType("0");
					
				}
				else
				{
					throw new Exception(err);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				vo.setStrErrMsgString("DischargeCancelTransDAO.insertdischargecancel() --> "
						+ e.getMessage());
				vo.setStrMsgType("1");
			}
			finally {
			     if (daoObj != null) {
			    	 daoObj.free();
			    	 daoObj = null;
			     }
			    }
		}

}