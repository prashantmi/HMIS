
	package ipd.reports;
	/*
	 * author : Debashis sardar
	 * 
	 * New Admission Detail User Wise DAO
	 * 
	 * date: 30/12/2011
	 */
	import hisglobal.transactionmgnt.HisDAO;

	import javax.sql.rowset.WebRowSet;

	public class NewAdmissionDetailRptDAO {
		
	
		
		public static void getClerkDetails(NewAdmissionDetailRptVO voObj) {

			HisDAO daoObj = null;
			WebRowSet ws = null;

			String strProcName = "{call Pkg_Bill_Rpt.RPTB_user_list(?,?,?,?)}";
			int nProcIndex = 0;

			String strErr = "";

			try {

				daoObj = new HisDAO("IPD Reports",
						"NewAdmissionDetailRptDAO");

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

					voObj.setStrClerkWs(ws);
					
					
				} else {
					throw new Exception(strErr);
				}

			} catch (Exception e) {

				e.printStackTrace();
				
				voObj
						.setStrMsgString("NewAdmissionDetailRptDAO.getClerkDetails() --> "
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


