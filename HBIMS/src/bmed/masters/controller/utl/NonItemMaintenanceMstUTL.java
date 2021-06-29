package bmed.masters.controller.utl;

import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;



import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author Aritra Kumar Dhawa
 * 
 */
public class NonItemMaintenanceMstUTL implements MasterInterface {

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

		br.append("<a style=cursor:pointer><img src='../../hisglobal/images/btn-add.png'	title='Add Record' 											tabindex='0' onKeyPress='if(event.keyCode==13) addNonItemMaintenanceMaster(\"initializeAdd\");'	onClick='addNonItemMaintenanceMaster(\"initializeAdd\");' 		style='cursor: pointer;'></a>");
		br.append("<a style=cursor:pointer><img src='../../hisglobal/images/btn-mo.png'  	title='Update Record' 										tabindex='0' onKeyPress='if(event.keyCode==13) edit(\"initializeModify\");' 						onClick='edit(\"initializeModify\");' 							style='cursor: pointer;' ></a>");
		br.append("<a style=cursor:pointer><img src='../../hisglobal/images/btn-del.png' 	title='Select One Or More CheckBox To Delete Record(s)'   	tabindex='0' onKeyPress='if(event.keyCode==13) deleteRecords();' 									onClick='deleteRecords();' 										style='cursor: pointer;'></a>");
		br.append("<a style=cursor:pointer><img src='../../hisglobal/images/btn-view.png' 	title='Select A Record To View'   							tabindex='0' onKeyPress='if(event.keyCode==13) viewNonItemMaintenanceMaster(\"view\");' 			onClick='viewNonItemMaintenanceMaster(\"view\");' 				style='cursor: pointer;'></a>");
		br.append("<a style=cursor:pointer><img src='../../hisglobal/images/btn-rpt.png' 	title='Select A Record To Generate Reoprts'   				tabindex='0' onKeyPress='if(event.keyCode==13) report(\"REPORT\");'  								onClick='report(\"REPORT\");' 									style='cursor: pointer;'></a>");

		return br.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getColumnHeader()
	 */
	public String[] getColumnHeader() {
		String col_header[] = { "Non-Item Name",
				"Maintenance Name", "Maintenance Period" };
		return col_header;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getComboHeader()
	 */
	public String[] getComboHeader() {
		String[] comboHeader = { "0", "Engineering Item Type", "0",
				"Engineering Item Sub Type", "1", "Record Status" };
		return comboHeader;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getComboQuery()
	 */
	public String[] getComboQuery() {

		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		
		//String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();;

		/* List page contains 3 combos. */
		String[] comboQuery = new String[3];

		/*
		 * Engineering Item Type Combo
		 */
		comboQuery[0] = bmed.qryHandler_bmed.getQuery(1,
				"nonItemMaintenanceMst.engineeringItemType.combo.0").replace(
				"?", Config.SUPER_USER_HOSPITAL_CODE);

		/*
		 * Engineering Item Sub Type Combo
		 */
		comboQuery[1] = bmed.qryHandler_bmed.getQuery(1,
				"nonItemMaintenanceMst.engineeringItemSubType.combo.0")
				.replace("?", hosCode);

		/*
		 * Record Status Combo
		 */
		comboQuery[2] = "1^Active#2^In Active";
		return comboQuery;
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
		String[] arrStrDelQuery = new String[2];

		

		arrStrDelQuery[0] = bmed.qryHandler_bmed.getQuery(1,
				"nonItemMaintenanceMst.delete.0");
		arrStrDelQuery[0] = arrStrDelQuery[0].concat("  WHERE "
				+ bmed.qryHandler_bmed.getQuery(1,
						"nonItemMaintenanceMst.delete.0.cond"));
		
		
		arrStrDelQuery[1] = bmed.qryHandler_bmed.getQuery(1,
		"nonItemMaintenanceMst.delete.1");
		arrStrDelQuery[1] = arrStrDelQuery[1].concat("  WHERE "
		+ bmed.qryHandler_bmed.getQuery(1,
				"nonItemMaintenanceMst.delete.1.cond"));

		return arrStrDelQuery;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getJsFiles()
	 */
	public String getJsFiles() {

		String jsFile = "../../bmed/js/nonItemMaintenanceMaster.js";
		return jsFile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hisglobal.masterutil.MasterInterface#getMainQuery(java.lang.String[])
	 */
	public String getMainQuery(String[] cmb) {

		StringBuffer brMainQuery = new StringBuffer();
		String strHospitalCode = httpSession.getAttribute("HOSPITAL_CODE")
				.toString();

		/* Main Query */
		String strMainQuery = bmed.qryHandler_bmed.getQuery(1,
				"nonItemMaintenanceMst.main"); // 1 for master.

		/* Conditions for Main Query */
		String strCondition1 = null; // Is Valid.
		String strCondition2 = null; // Item Type.
		String strCondition3 = null; // Item Sub Type.

		String strAndKey = " AND ";

		strMainQuery = strMainQuery.replaceFirst("\\?", Config.SUPER_USER_HOSPITAL_CODE);
		
		strMainQuery=strMainQuery.replaceFirst("\\?", strHospitalCode);

		/* Setting Main Query to String Buffer */
		brMainQuery.append(strMainQuery);

		/* If Combo is selected */
		if (cmb != null) {

			/* Setting the 'is valid' condition */
			strCondition1 = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMaintenanceMst.main.cond.1"); // Is Valid
			strCondition1 = strCondition1.replaceFirst("\\?", cmb[2]);

			brMainQuery.append(strAndKey);
			brMainQuery.append(strCondition1);

			/* If Item Type Combo is selected, append corresponding condition. */
			if (cmb[0] != null && !cmb[0].equals("0")) {

				strCondition2 = bmed.qryHandler_bmed.getQuery(1,
						"nonItemMaintenanceMst.main.cond.2"); // Item Type
				strCondition2 = strCondition2.replaceFirst("\\?", cmb[0]);

				brMainQuery.append(strAndKey);
				brMainQuery.append(strCondition2);

			}

			/*
			 * If Item Sub Type Combo is selected, append corresponding
			 * condition.
			 */
			if (cmb[1] != null && !cmb[1].equals("0")) {

				strCondition3 = bmed.qryHandler_bmed.getQuery(1,
						"nonItemMaintenanceMst.main.cond.3"); // Item Sub Type
				strCondition3 = strCondition3.replaceFirst("\\?", cmb[1]);

				brMainQuery.append(strAndKey);
				brMainQuery.append(strCondition3);

			}

		} else {

			/* Setting the 'is valid' condition */
			strCondition1 = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMaintenanceMst.main.cond.1"); // Is Valid
			strCondition1 = strCondition1.replaceFirst("\\?", "1");

			brMainQuery.append(strAndKey);
			brMainQuery.append(strCondition1);

		}

		return brMainQuery.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getMasterName()
	 */
	public String getMasterName() {

		String masterName = "Non Item Maintenance Contract Master";

		return masterName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getOrderBy()
	 */
	public String[] getOrderBy() {
		String strOrderByNonItemName = " NONITEM_NAME ";
		String strOrderByMaintenanceName = " MAINTENANCE_NAME ";
		String orderBy[] = { "1", strOrderByNonItemName, "2", strOrderByMaintenanceName };
		return orderBy;
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
		 * &#39; is the single quote in HTML. Refer http://www.w3schools.com/css/pr_gen_quotes.asp
		 */
		String strSearchByNonItemName = " NONITEM_NAME ";
		String strSearchByMaintenanceName = " MAINTENANCE_NAME "; 
		String search_field[] = { "1", strSearchByNonItemName,"2",strSearchByMaintenanceName };
		return search_field;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getViewHeader()
	 */
	public List<String> getViewHeader() {

		/* This master is using its own view pop-up. */
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getViewQuery()
	 */
	public String getViewQuery() {
		/* This master is using its own view pop-up. */
		String strViewQuery = null;
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
		
		/* Auto width */
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
