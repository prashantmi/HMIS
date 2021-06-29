package bmed.masters.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

// TODO: Auto-generated Javadoc
/**
 * The Class MmsConfigMstDAO.
 */
public class BmedConfigMstDAO 
{

	public static String getDateDetails(String strMode_p,String strHospitalCode_p)
	{

		HisDAO dao = null;
		String strPropVal = "";
		int nqryIndex;
		WebRowSet wb = null;
		String strquery = new String();

		try {
			dao = new HisDAO("BMED", "BmedConfigMstDAO");

			strquery = bmed.qryHandler_bmed.getQuery(1, "hemConfigMst.getRecord.1");
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, strHospitalCode_p);
			dao.setQryValue(nqryIndex, 2, "1");
			dao.setQryValue(nqryIndex, 3, strMode_p);
			wb = dao.executeQry(nqryIndex);

			if (wb != null && wb.next()) {

				strPropVal = wb.getString(1);

			}

		} catch (Exception e) {

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

		return strPropVal;
	}


}
