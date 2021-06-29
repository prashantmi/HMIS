package ipd.transactions;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class AdmissionReprintTransDAO {


		/**
		 * This function is used to set patient details in hidden fields on the main page
		 * @param vo
		 */
		public static void setpatAdmnCode(AdmissionReprintTransVO vo)
		{
			String strProcName = "{call pkg_ipd_view.Proc_Pat_Admstatus_Code_Dtl(?,?,?,?,?,?,?)}";
			int nProcIndex = 0;
			String strErr = "";
			HisDAO daoObj = null;
			WebRowSet ws = null;

			try 
			{
				    daoObj = new HisDAO("ipd", "transactions.AdmissionReprintTransDAO.setpatAdmnCode()");
					nProcIndex = daoObj.setProcedure(strProcName);
					daoObj.setProcInValue(nProcIndex, "modval","4",1);
					daoObj.setProcInValue(nProcIndex, "hipnum_admno",vo.getStrAdmnNo(),2);
					daoObj.setProcInValue(nProcIndex, "hrgnum_puk",vo.getStrCrNo(),3);
					daoObj.setProcInValue(nProcIndex, "gnum_hospital_code",vo.getStrHospitalCode(),4);
					daoObj.setProcInValue(nProcIndex, "issuereq","",5);
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
							vo.setStrCrNo(ws.getString(3));
						}
					  }
					  /*else
					  {
						  AdmissionReprintTransDAO.setCRNofrmAdmNo(vo);
					  }*/
					  else
					  {
						  vo.setStrInvalidAdmno("1");
					  }					  
					}
					else 
					{
						throw new Exception(strErr);
					}
			} 
			catch (Exception e) 
			{
				vo.setStrMsgString("AdmissionReprintTransDAO.setpatAdmnCode() --> "+ e.getMessage());
				vo.setStrMsgType("1");
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
		
		public static void setCRNofrmAdmNo(AdmissionReprintTransVO vo)
		{
			String strProcName = "{call pkg_ipd_view.Proc_Pat_Admstatus_Code_Dtl(?,?,?,?,?,?,?)}";
			int nProcIndex = 0;

			String strErr = "";

			HisDAO daoObj = null;
			WebRowSet ws = null;

			try {
				    daoObj = new HisDAO("ipd", "transactions.AdmissionReprintTransDAO.setCRNofrmAdmNo()");
					nProcIndex = daoObj.setProcedure(strProcName);
					daoObj.setProcInValue(nProcIndex, "modval","3",1);
					daoObj.setProcInValue(nProcIndex, "hipnum_admno",vo.getStrAdmnNo(),2);
					daoObj.setProcInValue(nProcIndex, "hrgnum_puk","",3);
					daoObj.setProcInValue(nProcIndex, "gnum_hospital_code",vo.getStrHospitalCode(),4);
					daoObj.setProcInValue(nProcIndex, "issuereq","",5);
					daoObj.setProcOutValue(nProcIndex, "err", 1,6);
					daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
					daoObj.executeProcedureByPosition(nProcIndex);
					strErr = daoObj.getString(nProcIndex, "err");
					if (strErr == null)
						strErr = "";
					ws = daoObj.getWebRowSet(nProcIndex, "resultset");
					if (strErr.equals(""))
					{
					  if(ws.size()!=0)
					  {
						while(ws.next())
						{
					      	vo.setStrAdmnStatusCode(ws.getString(1));
							vo.setStrPatDeadCode(ws.getString(2));
							vo.setStrCrNo(ws.getString(10));
						}
					  }
					  else
					  {
						  vo.setStrInvalidAdmno("1");
					  }
					}
					 else {
						throw new Exception(strErr);
					}

			} catch (Exception e) {
				vo.setStrMsgString("AdmissionReprintTransDAO.setCRNofrmAdmNo() --> "
						+ e.getMessage());
				vo.setStrMsgType("1");
			} finally {
				if (daoObj != null) {
					daoObj.free();
					daoObj = null;
				}
			}
		}

		public static void setvisitorPassdtl(AdmissionReprintTransVO vo)
		{
			String strProcName = "{call pkg_ipd_view.proc_visitor_passprev_dtl(?,?,?,?,?)}";
			int nProcIndex = 0;
			String strErr = "";
			HisDAO daoObj = null;
			WebRowSet ws = null;
			try {
					daoObj = new HisDAO("AdmissionReprintTrans","AdmissionReprintTransDAO");
					nProcIndex = daoObj.setProcedure(strProcName);
					daoObj.setProcInValue(nProcIndex, "modval", "1",1);
					daoObj.setProcInValue(nProcIndex, "admno", vo.getStrAdmnNo(),2);
					daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),3);
					daoObj.setProcOutValue(nProcIndex, "err", 1,4);
					daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
					daoObj.executeProcedureByPosition(nProcIndex);
					strErr = daoObj.getString(nProcIndex, "err");
					if (strErr == null)
						strErr = "";
					ws = daoObj.getWebRowSet(nProcIndex, "resultset");
					if (strErr.equals("")) {
						//System.out.println("ws.size()->"+ws.size());
						vo.setStrPassDetailWS(ws);
					} else {
						throw new Exception(strErr);
					}
				} catch (Exception e) {
				vo.setStrMsgString("AdmissionReprintTransDAO.setvisitorPassdtl() --> "
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
		 * This function is used to display consultant name on main page who give advice to patient
		 * @param vo
		 */
		public static void setConsultantName(AdmissionReprintTransVO vo)
		{
			String strProcName = "{call pkg_ipd_view.proc_consultant(?,?,?,?,?)}";
			int nProcIndex = 0;

			String strErr = "";

			HisDAO daoObj = null;
			WebRowSet ws = null;

			String strCrNum = vo.getStrCrNo();
			String strDeptUnitCode=vo.getStrDeptUnitCode();
			//System.out.println("strCrNum"+strCrNum);
		//	System.out.println("DEPTUNIT"+strDeptUnitCode);
			try {
					daoObj = new HisDAO("Patient Admission Re-Print Transaction",
						"AdmissionReprintTransDAO");
					
					nProcIndex = daoObj.setProcedure(strProcName);
					daoObj.setProcInValue(nProcIndex, "PUKNO", strCrNum,1);
					daoObj.setProcInValue(nProcIndex, "DEPTUNIT", strDeptUnitCode,2);
					daoObj.setProcInValue(nProcIndex, "HOSP_CODE", vo.getStrHospitalCode(),3);
					daoObj.setProcOutValue(nProcIndex, "ERR", 1,4);
					daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2,5);
					daoObj.executeProcedureByPosition(nProcIndex);
					strErr = daoObj.getString(nProcIndex, "ERR");
					if (strErr == null)
						strErr = "";
					ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
					if (strErr.equals("")) {
					if (ws.next()) {
							vo.setStrConsultantCode(ws.getString(1));
							vo.setStrConsultantName(ws.getString(2));
						}
					} else {
						throw new Exception(strErr);
					}
			} catch (Exception e) {
				//e.printStackTrace();
				vo.setStrMsgString("AdmissionReprintTransDAO.setConsultantName() --> "
						+ e.getMessage());
				vo.setStrMsgType("1");

			} finally {
				if (daoObj != null) {
					daoObj.free();
					daoObj = null;
				}
			}
		}
		

		public static void insert(AdmissionReprintTransVO vo)
		{
			String strProcName1 = "{call PKG_IPD_DML.Proc_hipt_reprint_dtl(?,?,?,?,?,?,?,?)}";
			String err="";

			int nProcIndex = 0;
			HisDAO daoObj = null;

			try
			{
				daoObj=new HisDAO("Admission Reprint","AdmissionReprintTransDAO");

				nProcIndex = daoObj.setProcedure(strProcName1);
				daoObj.setProcInValue(nProcIndex, "modval","1",1);
				daoObj.setProcInValue(nProcIndex, "admNo", vo.getStrAdmnNo(),2);
				daoObj.setProcInValue(nProcIndex, "puk", vo.getStrCrNo(),3);
				daoObj.setProcInValue(nProcIndex, "printType", vo.getRePrintType(),4);
				daoObj.setProcInValue(nProcIndex, "printCharge", vo.getStrRePrintCharge(),5);
				daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),6);
				daoObj.setProcInValue(nProcIndex, "seat_id", vo.getStrSeatId(),7);
				daoObj.setProcOutValue(nProcIndex, "err",1,8);
				daoObj.executeProcedureByPosition(nProcIndex);
				if(err.equals(""))
				{
					vo.setStrMsgType("0");
				}
				else
				{
					throw new Exception(err);
				}
			}
			catch(Exception e)
			{
				vo.setStrErrMsgString("AdmissionReprintTransDAO.insert() --> "
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

		public static void getStatusWhetherAdvanceAmountGiven(AdmissionReprintTransVO vo)
		{
			String strProcName = "{? = call BILL_INTERFACE.FUNC_ADVANCE_RECEIPT_DTL(?::numeric,?::numeric)}";
			int nProcIndex = 0;
			String val="0";
			

			HisDAO daoObj = null;
			try {

					daoObj = new HisDAO("Patient Admission Transaction",
									"PatientAdmissionTransDAO");
					nProcIndex = daoObj.setFunction(strProcName);
					daoObj.setFuncInValue(nProcIndex, 2, vo.getStrHospitalCode());
					daoObj.setFuncInValue(nProcIndex, 3, vo.getStrCrNo());
					daoObj.setFuncOutValue(nProcIndex, 1);
					daoObj.executeFunction(nProcIndex);
					val=daoObj.getFuncString(nProcIndex);
					
					String[] adv = val.replace("^","#").split("#");
					vo.setStrAdvanceAmountReceiptNo(adv[0]);
					vo.setStrAdvanceAmountDate(adv[1]);
					vo.setStrAdvanceAmount(adv[2]);
			} catch (Exception e) {
				
				vo.setStrMsgString("AdmissionReprintTransDAO.getStatusWhetherAdvanceAmountGiven() --> "
						+ e.getMessage());
				vo.setStrMsgType("1");

			} finally {
				if (daoObj != null) {
					daoObj.free();
					daoObj = null;
				}
			}
		}
		
		
		public static void admissionList(AdmissionReprintTransVO vo)
		{
			String strProcName = "{call pkg_ipd_view.Proc_Pat_Admstatus_Code_Dtl1(?,?,?,?,?)}";
			int nProcIndex = 0;

			String strErr = "";

			HisDAO daoObj = null;
			WebRowSet ws = null;

			try 
			{
					daoObj = new HisDAO("IPD--->Admission Reprint","AdmissionReprintTransDAO");
					nProcIndex = daoObj.setProcedure(strProcName);
					daoObj.setProcInValue(nProcIndex, "modval","1",1);
					daoObj.setProcInValue(nProcIndex, "hrgnum_puk",vo.getStrCrNo(),2);
					daoObj.setProcInValue(nProcIndex, "gnum_hospital_code",vo.getStrHospitalCode(),3);
					daoObj.setProcOutValue(nProcIndex, "err", 1,4);
					daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
					daoObj.executeProcedureByPosition(nProcIndex);
					strErr = daoObj.getString(nProcIndex, "err");
                    
					if (strErr == null)
						strErr = "";

					ws = daoObj.getWebRowSet(nProcIndex, "resultset");
					if (strErr.equals("")) 
					{
						vo.setAdmissionListWS(ws);
					} 
					else 
					{
						throw new Exception(strErr);
					}
			} 
			catch (Exception e) 
			{
				vo.setStrMsgString("AdmissionReprintTransDAO.setpatAdmnCode() --> "+ e.getMessage());
				vo.setStrMsgType("1");
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