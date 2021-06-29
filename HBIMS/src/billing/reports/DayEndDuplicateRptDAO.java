package billing.reports;

import javax.sql.rowset.WebRowSet;

import billing.reports.DayEndDuplicateRptVO;
import hisglobal.transactionmgnt.HisDAO;

public class DayEndDuplicateRptDAO {
	
	public static void getSummaryDetails(DayEndDuplicateRptVO vo) 
	{		
		String proc_name = "";
		proc_name = "{call pkg_bill_view.proc_dayend_summ_dtl(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		WebRowSet ws = null;

		try 
		{
			dao = new HisDAO("billing","reports.DayEndDuplicateRptDAO .getSummaryDetails()");
			nprocIndex = dao.setProcedure(proc_name);

			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(),1);
			dao.setProcInValue(nprocIndex, "moduleId", vo.getStrBillModuleId(),2);
			dao.setProcInValue(nprocIndex, "userId", vo.getStrUserId(),3);
			dao.setProcInValue(nprocIndex, "frmDate", vo.getStrFromDate(),4); 
			dao.setProcInValue(nprocIndex, "toDate", vo.getStrToDate(),5); 
			dao.setProcOutValue(nprocIndex, "err", 1,6); 
			dao.setProcOutValue(nprocIndex, "resultset", 2,7); 

			dao.executeProcedureByPosition(nprocIndex);

			strErr = dao.getString(nprocIndex, "err");
			if (strErr == null)
				strErr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");
			vo.setStrSummaryDtlWs(ws);			
			
		}
		catch (Exception e) 
		{
			vo.setStrMsgString("DayEndDuplicateRptDAO.getSummaryDetails() --> "+ e.getMessage());
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
		// return ws;
	}
	public static void getHospitalName(DayEndDuplicateRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_rpt.RPT_GET_HOSPITAL_LIST(?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("Billing Reports","DayEndDuplicateRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue( nProcIndex, "modeval","1",1);
			daoObj.setProcInValue( nProcIndex, "hosp_code",voObj.getStrHospitalCode(),2);
			daoObj.setProcInValue( nProcIndex, "seatId",voObj.getStrSeatId(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		

				voObj.setStrHospitalWs(ws);
				
				
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("DayEndDuplicateRptDAO.getHospitalName() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

}
