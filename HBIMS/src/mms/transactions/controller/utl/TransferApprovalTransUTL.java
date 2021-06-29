/**
 * 
 */
package mms.transactions.controller.utl;

import hisglobal.transactionutil.TransInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import mms.MmsConfigUtil;

/**
 * @author Balasubramaniam M Creation Date:- 14-08-2012 Modifying Date:-
 * 
 */
public class TransferApprovalTransUTL extends TransInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 02L;
	HttpSession httpSession = null;
	String[] cmbVal = null;

	public void setHttpSession(HttpSession session) {
		this.httpSession = session;
	}

	public void setCombo(String[] cmb) {
		this.cmbVal = cmb;
	}

	public String getMasterName() {
		String masterName = "Transfer Order Generation";
		return masterName;
	}

	public int getRecord_per_page() {
		return 12;
	}

	public List<String> getViewHeader() {

		List<String> viewHdr = new ArrayList<String>();
		return viewHdr;
	}

	public String getMainQuery(String cmb[]) {
		String strHospCode = (String) httpSession.getAttribute("HOSPITAL_CODE");
		StringBuffer brMainQuery = new StringBuffer("");
		MmsConfigUtil mmsConfig = new MmsConfigUtil(strHospCode);
		// Main Store hardcoded value.
		String strStoreId = "99901134";

		cmbVal = cmb;

		if (cmbVal != null) {

			if (cmbVal[0] != null && cmbVal[0].equals("1")) { // Request Type :
				// Order

				brMainQuery
						.append(" SELECT HSTNUM_ORDER_NO||'@'||HSTNUM_SLNO||'@'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_ORDER_NO||'^'|| ")
						.append(" ORDER_DATE||'^'||DRUG_NAME||'^'||ORDER_QTY||'^'||DEMAND_STORE||'^'||TRANS_STORE AS DATA   ")
						.append(" FROM ( SELECT HSTNUM_ORDER_NO, HSTNUM_SLNO, GNUM_HOSPITAL_CODE, TO_CHAR(HSTDT_ORDER_DATE,'DD-Mon-YYYY') AS ORDER_DATE, ")
						.append(" MMS_MST.GET_ITEM_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_ITEMBRAND_ID) AS DRUG_NAME, ")
						.append(" HSTNUM_ORDER_QTY || ' ' || MMS_MST.GETUNITNAME(GNUM_HOSPITAL_CODE,HSTNUM_ORDERQTY_UNITID) AS ORDER_QTY, ")
						.append(" MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_DEMAND_STORE_ID) AS DEMAND_STORE,  ")
						.append(" MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_TRANS_STORE_ID) AS TRANS_STORE , GNUM_STATUS , ")
						.append(" HSTDT_FINANCIAL_START_DATE , HSTDT_FINANCIAL_END_DATE , GNUM_ISVALID ")
						.append(" FROM HSTT_TRANSFER_ORDER_DTL ORDER BY HSTDT_ORDER_DATE ) C WHERE GNUM_ISVALID = 1 ");

				if (cmbVal[1] != null) {

					brMainQuery.append(" AND GNUM_STATUS = ").append(cmbVal[1]);

					if (cmbVal[1].equals("99") || cmbVal[1].equals("50")) {

						String strFinancialStartDate = mmsConfig
								.getStrFinancialStartDate(strStoreId,
										strHospCode);
						String strFinancialEndDate = mmsConfig
								.getStrFinancialEndDate(strStoreId, strHospCode);

						brMainQuery.append(
								" AND HSTDT_FINANCIAL_START_DATE = TO_DATE('"
										+ strFinancialStartDate
										+ "','DD-MON-YYYY') ").append(
								" AND HSTDT_FINANCIAL_END_DATE = TO_DATE('"
										+ strFinancialEndDate
										+ "','DD-MON-YYYY') ");

					}

				} else {

					brMainQuery.append(" AND GNUM_STATUS = 0 ");

				}

			} else if (cmbVal[0] != null && cmbVal[0].equals("2")) { // Request
																		// Type
																		// :
																		// Transfer

				brMainQuery
						.append(" SELECT HSTNUM_STORE_ID||'@'||HSTNUM_REQUEST_NO||'@'||HSTNUM_SLNO||'@'||GNUM_HOSPITAL_CODE||'$'||APP_QTY||'^'||STR_NAME||'^'|| ")
						.append(" HSTNUM_REQUEST_NO||'^'||REQ_DATE||'^'||DRUG_NAME||'^'||REQ_QTY||'^'||ORDER_QTY||'^'||APP_QTY||'^'||STS AS DATA ")
						.append(" FROM ( SELECT HSTNUM_STORE_ID,  HSTNUM_SLNO, GNUM_HOSPITAL_CODE,  ")
						.append(" MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID) AS STR_NAME, ")
						.append(" HSTNUM_REQUEST_NO, TO_CHAR(HSTDT_REQUEST_DATE,'DD-Mon-YYYY') AS REQ_DATE, ")
						.append(" MMS_MST.GET_ITEM_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_ITEMBRAND_ID) AS DRUG_NAME, ")
						.append(" NVL(HSTNUM_REQ_QTY,0) || ' ' || MMS_MST.GETUNITNAME(GNUM_HOSPITAL_CODE,HSTNUM_REQ_QTY_UNITID) AS REQ_QTY, ")
						.append(" NVL(HSTNUM_APPROVED_QTY,0) || ' ' || MMS_MST.GETUNITNAME(GNUM_HOSPITAL_CODE,HSTNUM_APPQTY_UNITID) AS ORDER_QTY, ")
						.append(" NVL(HSTNUM_APPROVED_QTY,0) AS APP_QTY,NVL(HSTNUM_IS_FORCE_CLOSE,0) AS STS , GNUM_ISVALID , GNUM_STATUS ,  ")
						.append(" HSTDT_FINANCIAL_START_DATE , HSTDT_FINANCIAL_END_DATE ")
						.append(" FROM HSTT_TRANSFER_EXCESS_REQ_DTL  ORDER BY HSTDT_REQUEST_DATE) C WHERE GNUM_ISVALID = 1 ");

				if (cmbVal[1] != null) {

					if (cmbVal[1].equals("99") || cmbVal[1].equals("50")) {

						brMainQuery.append(" AND GNUM_STATUS = ").append(
								cmbVal[1]);

						String strFinancialStartDate = mmsConfig
								.getStrFinancialStartDate(strStoreId,
										strHospCode);
						String strFinancialEndDate = mmsConfig
								.getStrFinancialEndDate(strStoreId, strHospCode);

						brMainQuery.append(
								" AND HSTDT_FINANCIAL_START_DATE = TO_DATE('"
										+ strFinancialStartDate
										+ "','DD-MON-YYYY') ").append(
								" AND HSTDT_FINANCIAL_END_DATE = TO_DATE('"
										+ strFinancialEndDate
										+ "','DD-MON-YYYY') ");

					} else {

						brMainQuery
								.append(" AND GNUM_STATUS IN (0 , 40 , 45) ");

					}

				} else {

					brMainQuery.append(" AND GNUM_STATUS IN (0 , 40 , 45) ");

				}

			} else { // Request Type : Demand

				brMainQuery
						.append(" SELECT HSTNUM_STORE_ID||'@'||HSTNUM_REQUEST_NO||'@'||HSTNUM_SLNO||'@'||GNUM_HOSPITAL_CODE||'^'|| ")
						.append(" STR_NAME||'^'||HSTNUM_REQUEST_NO||'^'||REQ_DATE||'^'||DRUG_NAME||'^'||REQ_QTY AS DATA  ")
						.append(" FROM ( SELECT HSTNUM_STORE_ID, HSTNUM_SLNO, GNUM_HOSPITAL_CODE,  ")
						.append(" MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID) AS STR_NAME, ")
						.append(" HSTNUM_REQUEST_NO, TO_CHAR(HSTDT_REQUEST_DATE,'DD-Mon-YYYY') AS REQ_DATE, ")
						.append(" MMS_MST.GET_ITEM_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_ITEMBRAND_ID) AS DRUG_NAME, ")
						.append(" NVL(HSTNUM_REQ_QTY,0) || ' ' || MMS_MST.GETUNITNAME(GNUM_HOSPITAL_CODE,HSTNUM_REQ_QTY_UNITID) AS REQ_QTY, ")
						.append(" GNUM_ISVALID , GNUM_STATUS , HSTDT_FINANCIAL_START_DATE, HSTDT_FINANCIAL_END_DATE ")
						.append(" FROM HSTT_TRANSFER_SHORT_REQ_DTL ORDER BY HSTDT_REQUEST_DATE ) C WHERE GNUM_ISVALID = 1 ");

				if (cmbVal[1] != null) {

					brMainQuery.append(" AND GNUM_STATUS = ").append(cmbVal[1]);

					if (cmbVal[1].equals("99") || cmbVal[1].equals("50")) {

						String strFinancialStartDate = mmsConfig
								.getStrFinancialStartDate(strStoreId,
										strHospCode);
						String strFinancialEndDate = mmsConfig
								.getStrFinancialEndDate(strStoreId, strHospCode);

						brMainQuery.append(
								" AND HSTDT_FINANCIAL_START_DATE = TO_DATE('"
										+ strFinancialStartDate
										+ "','DD-MON-YYYY') ").append(
								" AND HSTDT_FINANCIAL_END_DATE = TO_DATE('"
										+ strFinancialEndDate
										+ "','DD-MON-YYYY') ");

					}

				} else {

					brMainQuery.append(" AND GNUM_STATUS = 0 ");

				}

			}

		} else {

			brMainQuery
					.append(" SELECT HSTNUM_ORDER_NO||'@'||HSTNUM_SLNO||'@'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_ORDER_NO||'^'|| ")
					.append(" ORDER_DATE||'^'||DRUG_NAME||'^'||ORDER_QTY||'^'||DEMAND_STORE||'^'||TRANS_STORE AS DATA   ")
					.append(" FROM ( SELECT HSTNUM_ORDER_NO, HSTNUM_SLNO, GNUM_HOSPITAL_CODE, TO_CHAR(HSTDT_ORDER_DATE,'DD-Mon-YYYY') AS ORDER_DATE, ")
					.append(" MMS_MST.GET_ITEM_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_ITEMBRAND_ID) AS DRUG_NAME, ")
					.append(" HSTNUM_ORDER_QTY || ' ' || MMS_MST.GETUNITNAME(GNUM_HOSPITAL_CODE,HSTNUM_ORDERQTY_UNITID) AS ORDER_QTY, ")
					.append(" MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_DEMAND_STORE_ID) AS DEMAND_STORE,  ")
					.append(" MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_TRANS_STORE_ID) AS TRANS_STORE , GNUM_STATUS , ")
					.append(" HSTDT_FINANCIAL_START_DATE , HSTDT_FINANCIAL_END_DATE , GNUM_ISVALID ")
					.append(" FROM HSTT_TRANSFER_ORDER_DTL ORDER BY HSTDT_ORDER_DATE ) C WHERE GNUM_ISVALID = 1  AND GNUM_STATUS = 0  ");

		}

		brMainQuery.append(" AND GNUM_HOSPITAL_CODE = ").append(strHospCode);

	//	System.out.println(brMainQuery.toString());

		return brMainQuery.toString();
	}

	public String[] getSearchField() {
		String search_field[] = null;

		if (cmbVal != null) {

			if (cmbVal[0] != null && cmbVal[0].equals("1")) {

				// request type = order
				search_field = new String[6];

				search_field[0] = "3";
				search_field[1] = "DRUG_NAME";
				search_field[2] = "5";
				search_field[3] = "DEMAND_STORE";
				search_field[4] = "6";
				search_field[5] = "TRANS_STORE";

			} else {

				// request type = demand
				search_field = new String[4];

				search_field[0] = "1";
				search_field[1] = "STR_NAME";
				search_field[2] = "4";
				search_field[3] = "DRUG_NAME";
			}

		} else {

			search_field = new String[6];

			search_field[0] = "3";
			search_field[1] = "DRUG_NAME";
			search_field[2] = "5";
			search_field[3] = "DEMAND_STORE";
			search_field[4] = "6";
			search_field[5] = "TRANS_STORE";

		}

		return search_field;
	}

	/**
	 * 0^0 0 Means Combo From Query, 1 Means User Defined Combo,0 After ^ Means
	 * Select Value, 1 Means All,2 Means Default Selected
	 * */
	public String[] getComboHeader() {

		String[] strComboHeader = new String[4];

		strComboHeader[0] = "1";
		strComboHeader[1] = "Request Type";
		strComboHeader[2] = "1";
		strComboHeader[3] = "Status";

		return strComboHeader;
	}

	public String[] getColumnHeader() {

		String[] strColumnHeader = null;

		if (cmbVal != null && cmbVal[0] != null) {

			// Order
			if (cmbVal[0].equals("1")) {

				strColumnHeader = new String[6];

				strColumnHeader[0] = "Order No.";
				strColumnHeader[1] = "Order Date";
				strColumnHeader[2] = "Item Name";
				strColumnHeader[3] = "Order Qty.";
				strColumnHeader[4] = "Demanding DDW";
				strColumnHeader[5] = "Trans. DDW";

				// Transfer
			} else if (cmbVal[0].equals("2")) {

				strColumnHeader = new String[6];

				strColumnHeader[0] = "DDW Name";
				strColumnHeader[1] = "Request No.";
				strColumnHeader[2] = "Request Date";
				strColumnHeader[3] = "Item Name";
				strColumnHeader[4] = "Request Qty.";
				strColumnHeader[5] = "Order Qty.";

				// Demand
			} else {

				strColumnHeader = new String[5];

				strColumnHeader[0] = "DDW Name";
				strColumnHeader[1] = "Request No.";
				strColumnHeader[2] = "Request Date";
				strColumnHeader[3] = "Item Name";
				strColumnHeader[4] = "Request Qty.";

			}

		} else {

			strColumnHeader = new String[6];

			strColumnHeader[0] = "Order No.";
			strColumnHeader[1] = "Order Date";
			strColumnHeader[2] = "Item Name";
			strColumnHeader[3] = "Order Qty.";
			strColumnHeader[4] = "Demanding DDW";
			strColumnHeader[5] = "Trans. DDW";

		}

		return strColumnHeader;
	}

	public String[] getComboQuery() {

		String[] strComboQry = new String[2];

		strComboQry[0] = "1^Order#2^Transfer#3^Demand";

		if (cmbVal != null) {

			if (cmbVal[0] != null && cmbVal[0].equals("1")) {

				// request type = order
				strComboQry[1] = "0^Active#40^In-Transit#50^Processed";

			} else {

				// request type = transfer / demand
				strComboQry[1] = "0^Active#50^Processed";

			}

		} else {

			// request type = transfer / demand
			strComboQry[1] = "0^Active#50^Processed";
		}

		return strComboQry;
	}

	public String getViewQuery() {
		return "";
	}

	/*
	 * public String getButtons() { //String strButtons =
	 * "<a style=cursor:pointer><img src='../../hisglobal/images/btn-ccl.png' style='cursor:pointer' border=0 tabindex='1' onKeyPress='cancelPage();' onClick='cancelPage();' ></a>"
	 * ; return ""; }
	 */

	public String[] getDeleteQuery() {
		String deleteQuery[] = new String[1];
		deleteQuery[0] = "";

		return deleteQuery;
	}

	public List<String> getDeleteData(String chk) {
		List<String> deleteData = new ArrayList<String>();
		return deleteData;
	}

	public String getJsFiles() {
		String files = "../../mms/js/TransferApproval.js";
		return files;

	}

	public String[] getRowStatus() {

		String[] status = null;

		if (cmbVal != null) {

			// request type = Transfer (2) and status = Processed (50)
			if ((cmbVal[0] != null && cmbVal[0].equals("2"))
					&& (cmbVal[1] != null && cmbVal[1].equals("50"))) {

				status = new String[4];
				
				status[0] = "1";
				status[1] = "9";
				status[2] = "lightblue";
				status[3] = "Force Fully Closed";

			}
		}

		return status;
	}

	public String getEventState() {
		String str = "";
		return str;
	}

	public String getButtonConfiguration() {
		return "left";
	}

	public String[] getUserDefinedButtons() {
		String[] str = null;

		if (cmbVal != null && cmbVal[0] != null) {

			if (cmbVal[0].equals("1")) {

				str = new String[4];

				str[0] = "Generate@buttonClick('ORDER_GENERATE')@0@3b5998@glyphicon-plus";
				str[1] = "Modify@buttonClick('ORDER_MODIFY')@1@ff5057@glyphicon-edit";
				str[2] = "Cancel@buttonClick('ORDER_CANCEL')@1@bb0000@glyphicon-remove";
				str[3] = "View@buttonClick('ORDER_VIEW')@1@007bb6@glyphicon-fullscreen";

			} else if (cmbVal[0].equals("2")) {

				str = new String[3];

				str[0] = "Reject@buttonClick('TRANSFER_REJECT')@1";
				str[1] = "Force_Fully_Close@buttonClick('TRANSFER_FFCLOSE')@1";
				str[2] = "View@buttonClick('TRANSFER_VIEW')@1@007bb6@glyphicon-fullscreen";

			} else {
				str = new String[2];

				str[0] = "Reject@buttonClick('DEMAND_REJECT')@1";
				str[1] = "View@buttonClick('DEMAND_VIEW')@1@007bb6@glyphicon-fullscreen";
			}

		} else {

			str = new String[4];

			str[0] = "Generate@buttonClick('ORDER_GENERATE')@0@3b5998@glyphicon-plus";
			str[1] = "Modify@buttonClick('ORDER_MODIFY')@1@ff5057@glyphicon-edit";
			str[2] = "Cancel@buttonClick('ORDER_CANCEL')@1@bb0000@glyphicon-remove";
			str[3] = "View@buttonClick('ORDER_VIEW')@1@007bb6@glyphicon-fullscreen";

		}

		return str;
	}

	public String[] getDbButtons() {
		return null;
	}

	public int getMinPanelButton() {
		return 1;
	}

	public String getMenuOption() {
		String menuType = "Tiles";
		return menuType;
	}

	public String getComboEventState() {
		return "";
	}

	public String[] getOrderBy() {
		String orderBy[] = null;

		if (cmbVal != null) {

			if (cmbVal[0] != null && cmbVal[0].equals("1")) {

				// request type = order
				orderBy = new String[8];

				orderBy[0] = "2";
				orderBy[1] = "ORDER_DATE";
				orderBy[2] = "3";
				orderBy[3] = "DRUG_NAME";
				orderBy[4] = "5";
				orderBy[5] = "DEMAND_STORE";
				orderBy[6] = "6";
				orderBy[7] = "TRANS_STORE";

			} else {

				// request type = demand
				orderBy = new String[6];

				orderBy[0] = "3";
				orderBy[1] = "REQ_DATE";
				orderBy[2] = "1";
				orderBy[3] = "STR_NAME";
				orderBy[4] = "4";
				orderBy[5] = "DRUG_NAME";
			}

		} else {

			// request type = order
			orderBy = new String[8];

			orderBy[0] = "2";
			orderBy[1] = "ORDER_DATE";
			orderBy[2] = "3";
			orderBy[3] = "DRUG_NAME";
			orderBy[4] = "5";
			orderBy[5] = "DEMAND_STORE";
			orderBy[6] = "6";
			orderBy[7] = "TRANS_STORE";

		}

		return orderBy;
	}

}
