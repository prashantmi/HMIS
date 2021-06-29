
package billing.reports;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import billing.transactions.IpdBillManagementTransVO;

	public class IPDPatientProvisionalBillRptDAO 
	{	

		public static void getPatientDtls(IPDPatientProvisionalBillRptVO vo) 
		{
			String proc_name = "";
			proc_name = "{call pkg_bill_view.proc_get_Ipd_Bill_Desk_Pat_Dtl_old(?,?,?,?)}";
			HisDAO dao = null;
			int nprocIndex = 0;
			String strErr = "";
			WebRowSet ws = null;
			try 
			{
				dao = new HisDAO("billing","transactions.IPDPatientProvisionalBillRptDAO .getPatientDtls()");
				nprocIndex = dao.setProcedure(proc_name);
				dao.setProcInValue(nprocIndex, "crNo", vo.getStrCrNo());
				dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode()); 
				dao.setProcOutValue(nprocIndex, "err", 1); 
				dao.setProcOutValue(nprocIndex, "resultset", 2); 
				
				dao.executeProcedure(nprocIndex);
				
				strErr = dao.getString(nprocIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = dao.getWebRowSet(nprocIndex, "resultset");
				
				vo.setStrChk("0");
				while (ws.next()) 
				{	
					//if there is an Active account present. 
					if(ws.getString(2).equals("1") || ws.getString(2).equals("2")) {
						vo.setStrChk(ws.getString(1));
						vo.setStrPatAcctStatus(ws.getString(2));
						break;
					}
					vo.setStrChk(ws.getString(1));
					vo.setStrPatAcctStatus(ws.getString(2));
				}
			} 
			catch (Exception e) 
			{
				vo.setStrMsgString("IPDPatientProvisionalBillRptDAO.getPatientDtls() --> "+ e.getMessage());
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
		}
	}
