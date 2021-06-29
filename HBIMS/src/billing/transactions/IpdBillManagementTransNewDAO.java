package billing.transactions;
/*
 * IPD Bill Management New DAO
 * 
 * author:Debashis Sardar
 * 
 * dated:12th Mar 2013
 */
import hisglobal.transactionmgnt.HisDAO;
import javax.sql.rowset.WebRowSet;

public class IpdBillManagementTransNewDAO {
  
	public static void getPatientDtls(IpdBillManagementTransVO vo) {
		String proc_name = "";
		proc_name = "{call pkg_bill_view.proc_get_Ipd_Bill_Desk_Pat_Dtl(?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		try {
			dao = new HisDAO("billing",
					"transactions.IpdBillManagementTransNewDAO .getPatientDtls()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "crNo", vo.getStrCrNo());
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode()); 
			dao.setProcInValue(nprocIndex, "acc_no", vo.getStrAccountNo()); 
			dao.setProcInValue(nprocIndex, "adm_no", vo.getStrAddmissionNo()); 
			dao.setProcOutValue(nprocIndex, "err", 1); 
			dao.setProcOutValue(nprocIndex, "resultset", 2); 
			
			dao.executeProcedure(nprocIndex);
			
			strErr = dao.getString(nprocIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			
			vo.setStrChk("0");
			while (ws.next()) {
				vo.setStrChk(ws.getString(1));
			}
		} catch (Exception e) {
			vo.setStrMsgString("IpdBillManagementTransNewDAO.getPatientDtls() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}
	public static void getAdmissionDtls(IpdBillManagementTransVO vo) 
	{
		String proc_name = "";
		proc_name = "{call pkg_bill_view.proc_get_Ipd_Bill_Desk_adm_Dtl(?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		try 
		{
			dao = new HisDAO("billing","transactions.IpdBillManagementTransNewDAO .getPatientDtls()");
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
			vo.setStrPatAdmWs(ws);
		} 
		catch (Exception e) 
		{
			vo.setStrMsgString("IpdBillManagementTransNewDAO.getPatientDtls() --> "+ e.getMessage());
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
