package mms.masters.controller.utl;

import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import mms.MmsConfigUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemTypeMstUTL.
 */
public class ItemTypeMstUTL implements MasterInterface {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 02L;

	/** The http session. */
	HttpSession httpSession = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hisglobal.masterutil.MasterInterface#setHttpSession(javax.servlet.http
	 * .HttpSession)
	 */
	public void setHttpSession(HttpSession session) {

		httpSession = session;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getMasterName()
	 */
	public String getMasterName() {

		//String masterName = " Drug Type Master ";
		MmsConfigUtil mmscofigutil = new  MmsConfigUtil(httpSession.getAttribute("HOSPITAL_CODE").toString());	
		String masterName = null;
		if (mmscofigutil.getStrStoreConfigCatg().equals("10")) 
		{	
		   
		   masterName = "Item Type Master";	
		}
		else
		{
			masterName = "Item Type Master";
		}
		return masterName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getColumnHeader()
	 */
	public String[] getColumnHeader() {

		//String[] columnHdr = { "Drug Type Name", "Short Name","Effective From" };
		MmsConfigUtil mmscofigutil = new  MmsConfigUtil(httpSession.getAttribute("HOSPITAL_CODE").toString());	
		String[] columnHdr = new String[3];
		if (mmscofigutil.getStrStoreConfigCatg().equals("10")){
			columnHdr[0] = "Drug Type Name";
		}else{
			columnHdr[0] = "Item Type Name";
		}
		columnHdr[1] = "Short Name";
		columnHdr[2] = "Effective From";
		return columnHdr;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getSearchField()
	 */
	public String[] getSearchField() {

		String search_field[] = { "1", "HSTSTR_ITEMTYPE_NAME", "2",
				"HSTSTR_SHORT_NAME" };
		return search_field;

		// return null;
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
	 * @see hisglobal.masterutil.MasterInterface#getPage_per_block()
	 */
	public int getPage_per_block() {

		return 10;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getComboHeader()
	 */
	public String[] getComboHeader() {

		//String[] comboHdr = { "0", "Drug Category", "1", "Record Status" };
		
		MmsConfigUtil mmscofigutil = new  MmsConfigUtil(httpSession.getAttribute("HOSPITAL_CODE").toString());	
		String[] comboHdr = new String[4];
		if (mmscofigutil.getStrStoreConfigCatg().equals("10")) {
			comboHdr[0] = "0";
			comboHdr[1] = "Category";
			comboHdr[2] = "1";
		comboHdr[3] = "Record Status";
		}else{
			comboHdr[0] = "0";
			comboHdr[1] = "Item Category";
			comboHdr[2] = "1";
		comboHdr[3] = "Record Status";
		}
		
		
		return comboHdr;

		// return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getComboQuery()
	 */
	public String[] getComboQuery() {

		String[] comboQuery = new String[2];

		String hosCode = MmsConfigUtil.GLOBAL_HOSPITAL_CODE;
		//String strSeatId = httpSession.getAttribute("SEATID").toString();

		String strCatCodes = "";
		if (httpSession.getAttribute("USERVALUE").toString() != null) {
			strCatCodes = httpSession.getAttribute("USERVALUE").toString();
		} else {
			strCatCodes = "0";
		}

		comboQuery[0] = mms.qryHandler_mms.getQuery(1,
				"select.ItemType.ItemCategory.0");

		comboQuery[0] = comboQuery[0].replace("?", hosCode);

		/*comboQuery[0] = comboQuery[0].concat(mms.qryHandler_mms.getQuery(1,
				"select.ItemType.ItemCategory.cond.0"));
		comboQuery[0] = comboQuery[0].replace("?", strSeatId);*/    //Commented By Adil Wasi, 
																	//because of not getting drug category.And
																	//i.e because User Management is not Integrated yet.

		//comboQuery[0] = comboQuery[0].concat(mms.qryHandler_mms.getQuery(1,
		//		"select.ItemType.ItemCategory.cond.1"));
		// System.out.println("comboQuery"+comboQuery[0]);
		//comboQuery[0] = comboQuery[0].replace("?", strCatCodes);

		System.out.println("comboQuery[0]item type mst-"+comboQuery[0]);
		comboQuery[1] = "1^Active#2^Inactive";

		return comboQuery;
		// return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hisglobal.masterutil.MasterInterface#getMainQuery(java.lang.String[])
	 */
	public String getMainQuery(String[] cmb) {

		String hosCode = MmsConfigUtil.GLOBAL_HOSPITAL_CODE;

		StringBuffer brMainQuery = new StringBuffer();

		brMainQuery.append(
				mms.qryHandler_mms.getQuery(1, "select.ItemType.0").replace(
						"?", hosCode)).append(
				" AND "
						+ mms.qryHandler_mms.getQuery(1,
								"select.ItemType.cond.1").replace("?", "1"));
		if (cmb != null) {

			if (!cmb[1].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("1"),
						brMainQuery.lastIndexOf("1") + 1, cmb[1]);

			}
		}

		brMainQuery.append(" AND "
				+ mms.qryHandler_mms.getQuery(1, "select.ItemType.cond.2")
						.replace("?", "0"));

		if (cmb != null) {

			if (!cmb[0].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("0"),
						brMainQuery.lastIndexOf("0") + 1, cmb[0]);

			}
		}
		System.out.println("main query item type mst-->"+brMainQuery.toString());
		return brMainQuery.toString();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getViewQuery()
	 */
	public String getViewQuery() {

		return mms.qryHandler_mms.getQuery(1, "select.ItemType.1");
		// return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getViewHeader()
	 */
	public List<String> getViewHeader() {

		List<String> viewHdr = new ArrayList<String>();
		MmsConfigUtil mmscofigutil = new  MmsConfigUtil(httpSession.getAttribute("HOSPITAL_CODE").toString());	
		
		if (mmscofigutil.getStrStoreConfigCatg().equals("10")) 
		{
			viewHdr.add("Category Name");
			viewHdr.add("D");
			viewHdr.add("Drug Type Name");
			viewHdr.add("D");
		}else{
			viewHdr.add("Item Category Name");
			viewHdr.add("D");
			viewHdr.add("Item Type Name");
			viewHdr.add("D");
		}
		
		viewHdr.add("Short Name");
		viewHdr.add("D");
		viewHdr.add("Effective From ");
		viewHdr.add("D");
		viewHdr.add("Remarks ");
		viewHdr.add("D");
		viewHdr.add("Record Status ");
		viewHdr.add("D");

		return viewHdr;
		// return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getOrderBy()
	 */
	public String[] getOrderBy() {

		String orderBy[] = { "1", "HSTSTR_ITEMTYPE_NAME", "2",
				"HSTSTR_SHORT_NAME" };
		return orderBy;
		// return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getDeleteQuery()
	 */
	public String[] getDeleteQuery() {

		String[] delQuery = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();

		delQuery[0] = mms.qryHandler_mms.getQuery(1, "delete.ItemType.0")
				.replace("?", seatId);

		delQuery[0] = delQuery[0].concat(" WHERE "
				+ mms.qryHandler_mms.getQuery(1, "delete.ItemType.cond.0"));
		return delQuery;
		// return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getButtons()
	 */
	public String getButtons() {

		StringBuilder br = new StringBuilder();
			/*br.append("<img src='../../hisglobal/images/btn-add.png'	tabindex='0' style='cursor: pointer;'	title='Add' 	onKeyPress='if(event.keyCode==13) return callComboAdd(document.forms[0],10);'		onClick='return callComboAdd(document.forms[0],10);' />");
		br.append("<img src='../../hisglobal/images/btn-mo.png'		tabindex='0' style='cursor: pointer;'	title='Modify' 	onKeyPress='if(event.keyCode==13) return callComboModify(document.forms[0],10);'	onClick='return callComboModify(document.forms[0],10);' />");
		br.append("<img src='../../hisglobal/images/btn-del.png'	tabindex='0' style='cursor: pointer;'	title='Delete' 	onKeyPress='if(event.keyCode==13) deleteRecords(\"DELETE\");'						onClick='deleteRecords(\"DELETE\");' />");
		br.append("<img src='../../hisglobal/images/btn-view.png'	tabindex='0' style='cursor: pointer;'	title='View' 	onKeyPress='if(event.keyCode==13) view(\"VIEWDATA\");'								onClick='view(\"VIEWDATA\");' />");
		br.append("<img src='../../hisglobal/images/btn-rpt.png'	tabindex='0' style='cursor: pointer;'	title='Report' 	onKeyPress='if(event.keyCode==13) report(\"REPORT\");'								onClick='report(\"REPORT\");' />");
		*/
		
		br.append("<br><a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='if(event.keyCode==13) return callComboAdd(document.forms[0],10);'		onClick='return callComboAdd(document.forms[0],10);'><span class='add'>Add</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='if(event.keyCode==13) return callComboModify(document.forms[0],10);'	onClick='return callComboModify(document.forms[0],10);'><span class='modify'>Modify</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='if(event.keyCode==13) deleteRecords(\"DELETE\");'						onClick='deleteRecords(\"DELETE\");'><span class='delete'>Delete</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='if(event.keyCode==13) view(\"VIEWDATA\");' 							onClick='view(\"VIEWDATA\");'><span class='view'>View</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='if(event.keyCode==13) report(\"REPORT\");'  						onClick='report(\"REPORT\");'><span class='report'>Report</span></a>");
		return br.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getJsFiles()
	 */
	public String getJsFiles() {

		String jsFile = new String("../../mms/js/mms.js");
		return jsFile;
		// return null;

	}

	public String[] getColsWidth() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean reportInterFaceRequired() {
		// TODO Auto-generated method stub
		return false;
	}
	public String isGlobal() {
		return "1";
	}

}
