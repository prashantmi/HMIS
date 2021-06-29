
	package ipd.reports;
	/*
	 * author : Debashis sardar
	 * 
	 * New Admission Detail Ward Wise DAO
	 * 
	 * date: 02/01/2012
	 */
	import hisglobal.transactionmgnt.HisDAO;

	import javax.sql.rowset.WebRowSet;

	public class NewAdmissionDetailWardWiseRptDAO {
		
	
		
		public static void getWardDetails(NewAdmissionDetailWardWiseRptVO voObj) {

			HisDAO daoObj = null;
			WebRowSet ws = null;

			String strProcName = "{call pkg_ipd_view. proc_hipt_ward_mst(?,?,?,?)}";
			int nProcIndex = 0;

			String strErr = "";

			try {

				daoObj = new HisDAO("IPD Reports",
						"NewAdmissionDetailWardWiseRptDAO");

				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeval", "2");
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode());
				daoObj.setProcOutValue(nProcIndex,"err", 1);
				daoObj.setProcOutValue(nProcIndex,"RESULTSET", 2);

				daoObj.executeProcedure(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				if (strErr.equals("")) {

					ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

					voObj.setStrWardWs(ws);
					
					
				} else {
					throw new Exception(strErr);
				}

			} catch (Exception e) {

				e.printStackTrace();
				
				voObj
						.setStrMsgString("NewAdmissionDetailWardWiseRptDAO.getClerkDetails() --> "
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




