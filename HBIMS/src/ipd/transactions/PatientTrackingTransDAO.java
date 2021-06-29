package ipd.transactions;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class PatientTrackingTransDAO 
{

	/**
	 * This function is used to get Movement Details
	 * @param vo
	 */
	public static void setMovementDetail(PatientTrackingTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.Proc_Pat_Tracking_Movement_Dtl(?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		try 
		{
				daoObj = new HisDAO("IPD--->Patient Tracking","PatientTrackingTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modval","1",1);				
				daoObj.setProcInValue(nProcIndex, "hrgnum_puk", vo.getStrCrNo(),2);
				daoObj.setProcInValue(nProcIndex, "hipnum_admno", vo.getStrAdmnNo(),3);
				daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", vo.getStrHospitalCode(),4);
				daoObj.setProcOutValue(nProcIndex, "err", 1,5);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) 
				{
					vo.setMovementDetailsWS(ws);
				} 
				else 
				{
					throw new Exception(strErr);
				}
		} 
		catch (Exception e) 
		{
			vo.setStrMsgString("PatientTrackingTransDAO.setMovementDetail() --> "+ e.getMessage());
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

		/**
		 * This function is used to set patient details in hidden fields on the main page
		 * @param vo
		 */
		public static void setpatStatusCode(PatientTrackingTransVO vo)
		{
			String strProcName = "{call Pkg_Ipd_View.Proc_Pat_Tracking_Dtl(?,?,?,?,?,?)}";
			int nProcIndex = 0;

			String strErr = "";

			HisDAO daoObj = null;
			WebRowSet ws = null;

			String strAdmnNo = vo.getStrAdmnNo();
			String strCrNo = vo.getStrCrNo();
			
			try {
				    daoObj = new HisDAO("ipd", "transactions.PatientTrackingTransDAO.setpatStatusCode()");
					nProcIndex = daoObj.setProcedure(strProcName);
					daoObj.setProcInValue(nProcIndex, "modval","1",1);
					daoObj.setProcInValue(nProcIndex, "hipnum_admno",strAdmnNo,2);
					daoObj.setProcInValue(nProcIndex, "puk",strCrNo,3);
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
								vo.setStrAdmnDate(ws.getString(5));
								vo.setStrDischargeTime(ws.getString(6));
							}
						}
						else {
							throw new Exception(strErr);
						}
					}

			} catch (Exception e) {
				vo.setStrMsgString("PatientTrackingTransDAO.setpatStatusCode() --> "
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
		 * This function is used to get Movement Details
		 * @param vo
		 */
		public static void admissionList(PatientTrackingTransVO vo)
		{
			String strProcName = "{call pkg_ipd_view.Proc_Pat_Tracking_Movement_Dtl(?,?,?,?,?,?)}";
			int nProcIndex = 0;

			String strErr = "";

			HisDAO daoObj = null;
			WebRowSet ws = null;

			try 
			{
					daoObj = new HisDAO("IPD--->Patient Tracking","PatientTrackingTransDAO");
					nProcIndex = daoObj.setProcedure(strProcName);
					daoObj.setProcInValue(nProcIndex, "modval","2",1);				
					daoObj.setProcInValue(nProcIndex, "hrgnum_puk", vo.getStrCrNo(),2);
					daoObj.setProcInValue(nProcIndex, "hipnum_admno", vo.getStrAdmnNo(),3);
					daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", vo.getStrHospitalCode(),4);
					daoObj.setProcOutValue(nProcIndex, "err", 1,5);
					daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
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
				vo.setStrMsgString("PatientTrackingTransDAO.setMovementDetail() --> "+ e.getMessage());
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