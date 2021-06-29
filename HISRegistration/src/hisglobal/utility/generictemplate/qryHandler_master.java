/*
 	This file is used to load query resource file
 */

package hisglobal.utility.generictemplate;

import java.util.ResourceBundle;

// TODO: Auto-generated Javadoc
/**
 * The Class qryHandler_mms.
 */
public class qryHandler_master {

	/** The mst query. */
	static String mstQuery = "hisglobal.utility.generictemplate.generic_template_qry";

	
	/** The mst res. */
	static ResourceBundle mstRes = null;

	/** The trn res. */
	static ResourceBundle trnRes = null;

	/** The rpt res. */
	static ResourceBundle rptRes = null;

	/*
	 * index defined for master = 1 index defined for transaction = 2 index
	 * defined for report = 3
	 */

	/**
	 * Gets the query.
	 * 
	 * @param index
	 *            the index
	 * @param qryIndex
	 *            the qry index
	 * 
	 * @return the query
	 */
	public static String getQuery(int index, String qryIndex) {

		String qry = "";

		switch (index) {
		case 1:
			if (mstRes == null)
				mstRes = ResourceBundle.getBundle(mstQuery);
			qry = mstRes.getString(qryIndex);

			break;
		
		}

		return qry;
	}

}
