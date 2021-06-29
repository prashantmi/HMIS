package bmed.masters.controller.utl;

import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author Aritra Kumar Dhawa
 * 
 */
public class ServiceEngineerExpertiseMstUTL implements MasterInterface {

	/**
	 * The serialization runtime associates with each serializable class a
	 * version number, called a serialVersionUID, which is used during
	 * deserialization to verify that the sender and receiver of a serialized
	 * object have loaded classes for that object that are compatible with
	 * respect to serialization. If the receiver has loaded a class for the
	 * object that has a different serialVersionUID than that of the
	 * corresponding sender's class, then deserialization will result in an
	 * InvalidClassException. A serializable class can declare its own
	 * serialVersionUID explicitly by declaring a field named "serialVersionUID"
	 * that must be static, final, and of type long.
	 */
	private static final long serialVersionUID = 1635289807562353838L;

	/**
	 * The HttpSession object for this request.
	 */
	private HttpSession httpSession = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getButtons()
	 */
	public String getButtons() {

		StringBuilder br = new StringBuilder();

		br.append("<a style=cursor:pointer><img src='../../hisglobal/images/btn-add.png'	title='Add' 		tabindex='0' onKeyPress='if(event.keyCode==13) addServiceEngineerExpertiseMst(\"initializeAdd\");'	onClick='addServiceEngineerExpertiseMst(\"initializeAdd\");' 	style='cursor: pointer;'></a>");
		br.append("<a style=cursor:pointer><img src='../../hisglobal/images/btn-mo.png'  	title='Modify' 		tabindex='0' onKeyPress='if(event.keyCode==13) edit(\"initializeModify\");' 						onClick='edit(\"initializeModify\");' 							style='cursor: pointer;' ></a>");
		br.append("<a style=cursor:pointer><img src='../../hisglobal/images/btn-del.png' 	title='Delete'   	tabindex='0' onKeyPress='if(event.keyCode==13) deleteRecords();' 								    onClick='deleteRecords();' 										style='cursor: pointer;'></a>");
		br.append("<a style=cursor:pointer><img src='../../hisglobal/images/btn-view.png' 	title='View'   		tabindex='0' onKeyPress='if(event.keyCode==13) view(\"VIEW\");' 									onClick='view(\"VIEW\");' 										style='cursor: pointer;'></a>");
		br.append("<a style=cursor:pointer><img src='../../hisglobal/images/btn-rpt.png' 	title='Reoprts'   	tabindex='0' onKeyPress='if(event.keyCode==13) report(\"REPORT\");'  								onClick='report(\"REPORT\");' 									style='cursor: pointer;'></a>");

		return br.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getColumnHeader()
	 */
	public String[] getColumnHeader() {
		String arrStringColumnHeader[] = { "Expertise Name", "Effective From" };
		return arrStringColumnHeader;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getComboHeader()
	 */
	public String[] getComboHeader() {
		String[] arrStringComboHeader = { "0", "Service Engineer Name", "1",
				"Record Status" };
		return arrStringComboHeader;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getComboQuery()
	 */
	public String[] getComboQuery() {

		String strHospitalCode = httpSession.getAttribute("HOSPITAL_CODE")
				.toString();

		/* List page contains 3 combos. */
		String[] arrStringComboQuery = new String[2];

		/*
		 * Engineering Item Type Combo
		 */
		arrStringComboQuery[0] = bmed.qryHandler_bmed.getQuery(1,
				"serviceEngineerExpertiseMst.serviceEngineerName.combo.0")
				.replace("?", strHospitalCode);

		/*
		 * Record Status Combo
		 */
		arrStringComboQuery[1] = "1^Active#2^In Active";
		return arrStringComboQuery;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getDeleteQuery()
	 */
	public String[] getDeleteQuery() {

		/*
		 * On deletion of a record from master, only one deletion query needs to
		 * be fired.
		 */
		String[] arrStrDelQuery = new String[1];

		/*
		 * Seat Id of the user. This data is required to track who is
		 * responsible for deletion of a data.
		 */
		/*
		 * String strSeatId = httpSession.getAttribute("SEATID").toString();
		 * 
		 * arrStrDelQuery[0] = bmed.qryHandler_bmed.getQuery(1,
		 * "serviceEngineerExpertiseMst.delete").replace("?", strSeatId);
		 */
		arrStrDelQuery[0] = bmed.qryHandler_bmed.getQuery(1,
				"serviceEngineerExpertiseMst.delete");
		arrStrDelQuery[0] = arrStrDelQuery[0].concat("  where "
				+ bmed.qryHandler_bmed.getQuery(1,
						"serviceEngineerExpertiseMst.delete.cond"));

		return arrStrDelQuery;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getJsFiles()
	 */
	public String getJsFiles() {

		String strJsFile = "../../bmed/js/serviceEngineerExpertiseMst.js";
		return strJsFile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hisglobal.masterutil.MasterInterface#getMainQuery(java.lang.String[])
	 */
	public String getMainQuery(String[] arrStringCombo_p) {

		StringBuffer brMainQuery = new StringBuffer();
		String strHospitalCode = httpSession.getAttribute("HOSPITAL_CODE")
				.toString();

		/* Main Query */
		String strMainQuery = bmed.qryHandler_bmed.getQuery(1,
				"serviceEngineerExpertiseMst.main"); // 1 for master.

		/* Conditions for Main Query */
		String strCondition1 = null; // Is Valid.
		String strCondition2 = null; // Service Engineer Name
		String strCondition3 = null; // Joining condition

		String strAndKey = " AND ";

		strMainQuery = strMainQuery.replaceFirst("\\?", strHospitalCode);

		/* Setting Main Query to String Buffer */
		brMainQuery.append(strMainQuery);

		/* If Combo is selected */
		if (arrStringCombo_p != null) {

			/* Setting the 'is valid' condition */
			strCondition1 = bmed.qryHandler_bmed.getQuery(1,
					"serviceEngineerExpertiseMst.main.cond.1"); // Is Valid
			strCondition1 = strCondition1.replaceFirst("\\?",
					arrStringCombo_p[1]);

			brMainQuery.append(strAndKey);
			brMainQuery.append(strCondition1);

			strCondition2 = bmed.qryHandler_bmed.getQuery(1,
					"serviceEngineerExpertiseMst.main.cond.2"); // Service
																// Engineer Name
			strCondition2 = strCondition2.replaceFirst("\\?",
					arrStringCombo_p[0]);

			strCondition3 = bmed.qryHandler_bmed.getQuery(1,
					"serviceEngineerExpertiseMst.main.cond.3"); // Joining
																// condition

			brMainQuery.append(strAndKey);
			brMainQuery.append(strCondition2);

			brMainQuery.append(strAndKey);
			brMainQuery.append(strCondition3);

		} else {

			/* Setting the 'is valid' condition */
			strCondition1 = bmed.qryHandler_bmed.getQuery(1,
					"serviceEngineerExpertiseMst.main.cond.1"); // Is Valid
			strCondition1 = strCondition1.replaceFirst("\\?", "1");

			strCondition2 = bmed.qryHandler_bmed.getQuery(1,
					"serviceEngineerExpertiseMst.main.cond.2"); // Service
																// Engineer Name
			strCondition2 = strCondition2.replaceFirst("\\?", "0");

			strCondition3 = bmed.qryHandler_bmed.getQuery(1,
					"serviceEngineerExpertiseMst.main.cond.3"); // Joining
																// condition

			brMainQuery.append(strAndKey);
			brMainQuery.append(strCondition1);

			brMainQuery.append(strAndKey);
			brMainQuery.append(strCondition2);

			brMainQuery.append(strAndKey);
			brMainQuery.append(strCondition3);

		}

		return brMainQuery.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getMasterName()
	 */
	public String getMasterName() {

		String strMasterName = "Service Engineer Expertise Master";

		return strMasterName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getOrderBy()
	 */
	public String[] getOrderBy() {
		String strOrderByExpertiseName = " UPPER(B.HEMSTR_SKILL_NAME) ";

		String arrStrOrderBy[] = { "1", strOrderByExpertiseName };
		return arrStrOrderBy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getPage_per_block()
	 */
	public int getPage_per_block() {

		return 10;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getRecord_per_page()
	 */
	public int getRecord_per_page() {

		return 10;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getSearchField()
	 */
	public String[] getSearchField() {

		/*
		 * Searching to be done on Location and Maintenance Name.
		 */

		/*
		 * &#39; is the single quote in HTML. Refer
		 * http://www.w3schools.com/css/pr_gen_quotes.asp
		 */
		String strSearchByExpertiseName = " B.HEMSTR_SKILL_NAME ";

		String arrStringSearchField[] = { "1", strSearchByExpertiseName };
		return arrStringSearchField;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getViewHeader()
	 */
	public List<String> getViewHeader() {

		List<String> listViewHdr = new ArrayList<String>();

		listViewHdr.add("Service Engineer Name");
		listViewHdr.add("D");
		listViewHdr.add("Expertise");
		listViewHdr.add("D");
		listViewHdr.add("Effective From");
		listViewHdr.add("D");
		listViewHdr.add("Remarks");
		listViewHdr.add("D");
		listViewHdr.add("Record Status");
		listViewHdr.add("D");

		return listViewHdr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getViewQuery()
	 */
	public String getViewQuery() {

		String strViewQuery = bmed.qryHandler_bmed.getQuery(1,
				"serviceEngineerExpertiseMst.view");

		return strViewQuery;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hisglobal.masterutil.MasterInterface#setHttpSession(javax.servlet.http
	 * .HttpSession)
	 */
	public void setHttpSession(HttpSession session) {
		this.httpSession = session;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getColsWidth()
	 */
	public String[] getColsWidth() {
		/* Column width is auto. */
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#reportInterFaceRequired()
	 */
	public boolean reportInterFaceRequired() {
		/* Report Interface Required. */
		return true;
	}

	public String isGlobal() {
		// TODO Auto-generated method stub
		return null;
	}

}
