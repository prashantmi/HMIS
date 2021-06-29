package billing.transactions;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class BillPrintTransDAO {

	public static void getOnLineReqDiscount(BillPrintTransVO vo) {
		// System.out.println("Inside DaoBilling.getOnLineReqDiscount");

		String proc_name1 = "";

		proc_name1 = "{call pkg_bill_view.PROC_SBLT_INBOUND_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		HisDAO dao = null;

		int procIndex1 = 0;

		String err = "";

		WebRowSet ws = null;

		try {

			dao = new HisDAO("billing",
					"transactions.DAObilling.getOnLineReqDiscount(BillingVO voHdrObj)");

			procIndex1 = dao.setProcedure(proc_name1);

			// set value
			dao.setProcInValue(procIndex1, "Mode_Type", "8");
			dao.setProcInValue(procIndex1, "CRNO", vo.getStrCrNo());
			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode());
			dao.setProcInValue(procIndex1, "seatId", "0");
			dao.setProcInValue(procIndex1, "chargeTypeId", "");
			dao.setProcInValue(procIndex1, "patListType", "");
			dao.setProcInValue(procIndex1, "searchStr", "");
			dao.setProcInValue(procIndex1, "searchType", "");
			dao.setProcInValue(procIndex1, "frmRows", "");
			dao.setProcInValue(procIndex1, "toRows", "");
			dao.setProcInValue(procIndex1, "deptCode", "");
			
			dao.setProcOutValue(procIndex1, "ERR", 1); // 1 for string return
			// value

			dao.setProcOutValue(procIndex1, "RESULTSET", 2); // 2 for object

			// execute procedure

			dao.executeProcedure(procIndex1);

			// get value

			err = dao.getString(procIndex1, "ERR");

			if (err == null)
				err = "";

			ws = dao.getWebRowSet(procIndex1, "RESULTSET");

			vo.setStrOnLineReqWs(ws);

		} catch (Exception e) {

			vo.setStrMsgString("BillHeaderDAO.getClientDtlProc() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}

	}

}
