package mms.transactions.controller.hlp;

import javax.sql.rowset.WebRowSet;

import mms.transactions.controller.fb.ReceiveFromThirdPartyTransFB;

public class ReceiveFromThirdPartyTransHLP 
{

	private static final String strColor = "red";
	private static final String defaultColor = "blue";

	
	public static String getNewReceivedItemsList(ReceiveFromThirdPartyTransFB formBean) {

		StringBuffer sb = null;

		final int LMIT_VAL = 10;

		WebRowSet ws = formBean.getWsReceivedItemList();

		try {

			sb = new StringBuffer("");

			
				if (ws != null && ws.size() > 0) {

					ws.beforeFirst();

					int noOfRecords = ws.size();

					int layerNo = noOfRecords / LMIT_VAL;

					int reminder = noOfRecords % LMIT_VAL;
					int totalLayer = layerNo;

					if (reminder > 0)
						totalLayer = totalLayer + 1;

					sb.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
					sb.append("<tr> ");
					sb.append("<td class='TITLE' colspan='6'>&nbsp;Received Item Details");
					sb.append("</td> ");
					sb.append("</tr>  ");
					sb.append("<tr>");
					sb.append("<td class='LABEL' colspan='6'>&nbsp;");
					for (int i = 1; i <= totalLayer; i++) {

						if (i == 1) {
							sb.append("<a STYLE='CURSOR:POINTER; color:"
									+ strColor + "' name='linId' id='linId" + i
									+ "' onClick='layerIndexNavigator(\"" + i
									+ "\",\"" + totalLayer + "\")'>" + i
									+ "</a> &nbsp;");
						} else {
							sb.append("<a STYLE='CURSOR:POINTER; color:"
									+ defaultColor + "' name='linId' id='linId"
									+ i + "' onClick='layerIndexNavigator(\""
									+ i + "\",\"" + totalLayer + "\")'>" + i
									+ "</a> &nbsp;");
						}
					}
					sb.append("</td>");
					sb.append("</tr>");

					sb.append("<tr> ");
					sb.append("<td class='multiLabel' width='20%'>Received No. ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Received Date ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Institute");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Item Name ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='10%'>Received Qty. ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='10%'>Status");
					sb.append("</td> ");
					sb.append("</tr>  ");
					sb.append("</table> ");

					for (int i = 1; i <= totalLayer; i++) {

						if (i != totalLayer && totalLayer != 1) {
							if (i == 1) {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
							} else {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
							}
							sb
									.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
							for (int j = 0; j < LMIT_VAL; j++) {
								ws.next();

								sb.append("<tr> ");
								sb
										.append("<td class='multiControl' width='20%'>").append(ws.getString(1));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='20%'>").append(ws.getString(2));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='20%'>").append(ws.getString(3));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='20%'>").append(ws.getString(4));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='10%'>").append(ws.getString(5));
								sb.append("</td> ");
								if(ws.getString(6).equals("0"))
								{	
								  sb.append("<td class='multiControl' width='10%'>Active");
						          sb.append("</td> ");
								} 
								if(ws.getString(6).equals("40"))
								{	
								  sb.append("<td class='multiControl' width='10%'>Pending");
						          sb.append("</td> ");
								} 
								sb.append("</tr> ");
							}
							sb.append("</table>");
							sb.append("</div>");

						} else {
							if (i == 1) {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
							} else {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
							}

							sb.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
							for (int k = 0; k < reminder; k++) {
								ws.next();
								sb.append("<tr> ");
								sb
										.append("<td class='multiControl' width='20%'>").append(ws.getString(1));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='20%'>").append(ws.getString(2));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='20%'>").append(ws.getString(3));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='20%'>").append(ws.getString(4));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='10%'>").append(ws.getString(5));
								sb.append("</td> ");
								if(ws.getString(6).equals("0"))
								{	
								  sb.append("<td class='multiControl' width='10%'>Active");
						          sb.append("</td> ");
								} 
								if(ws.getString(6).equals("40"))
								{	
								  sb.append("<td class='multiControl' width='10%'>Pending");
						          sb.append("</td> ");
								} 
								sb.append("</tr> ");

							}
							sb.append("</table>");
							sb.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
							sb.append("<tr> ");
							sb.append("<td class='TITLE' colspan='6'>");
							sb.append("</td> ");
							sb.append("</tr>  ");
							sb.append("</table>");
							sb.append("</div>");

						}

					}

				}
				else 
				{
					sb
					.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
					sb.append("<tr> ");
					sb.append("<td class='TITLE' colspan='6'>&nbsp;Received Item Details");
					sb.append("</td> ");
					sb.append("</tr>  ");
					sb.append("<tr> ");
					sb.append("<td class='multiLabel' width='20%'>Gifted No. ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Gifted Date ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Gifted By ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Item Name ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='10%'>Qty. ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='10%'>Status");
			        sb.append("</td> ");
					sb.append("</tr>  ");
					sb.append("</table> ");
					
					sb
							.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
					sb
							.append("<tr><td class='multiControl' colspan='5' ><font color='red'>No Matching Record Found</font></td></tr>");
					sb.append("</table>");
					
				}

		
		} catch (Exception e) {

		} 

		return sb.toString();

	}

	public static String getNewReceivedItemsListNEW(ReceiveFromThirdPartyTransFB formBean) {

		StringBuffer sb = null;

		final int LMIT_VAL = 10;

		WebRowSet ws = formBean.getWsReceivedItemList();

		try {

			sb = new StringBuffer("");

			
				if (ws != null && ws.size() > 0) {

					ws.beforeFirst();

					int noOfRecords = ws.size();

					int layerNo = noOfRecords / LMIT_VAL;

					int reminder = noOfRecords % LMIT_VAL;
					int totalLayer = layerNo;

					if (reminder > 0)
						totalLayer = totalLayer + 1;

					sb.append("<table class='table table-striped' align='center' cellpadding='1px' cellspacing='1px'> ");
					sb.append("<tr> ");
					sb.append("<td class='float-left' colspan='6'>&nbsp;Received Item Details");
					sb.append("</td> ");
					sb.append("</tr>  ");
					sb.append("<tr>");
					sb.append("<td class='LABEL' colspan='6'>&nbsp;");
					for (int i = 1; i <= totalLayer; i++) {

						if (i == 1) {
							sb.append("<a STYLE='CURSOR:POINTER; color:"
									+ strColor + "' name='linId' id='linId" + i
									+ "' onClick='layerIndexNavigator(\"" + i
									+ "\",\"" + totalLayer + "\")'>" + i
									+ "</a> &nbsp;");
						} else {
							sb.append("<a STYLE='CURSOR:POINTER; color:"
									+ defaultColor + "' name='linId' id='linId"
									+ i + "' onClick='layerIndexNavigator(\""
									+ i + "\",\"" + totalLayer + "\")'>" + i
									+ "</a> &nbsp;");
						}
					}
					sb.append("</td>");
					sb.append("</tr>");

					sb.append("<tr> ");
					sb.append("<td class='' width='20%'>Received No. ");
					sb.append("</td> ");
					sb.append("<td class='' width='20%'>Received Date ");
					sb.append("</td> ");
					sb.append("<td class='' width='20%'>Institute");
					sb.append("</td> ");
					sb.append("<td class='' width='20%'>Item Name ");
					sb.append("</td> ");
					sb.append("<td class='' width='10%'>Received Qty. ");
					sb.append("</td> ");
					sb.append("<td class='' width='10%'>Status");
					sb.append("</td> ");
					sb.append("</tr>  ");
					sb.append("</table> ");

					for (int i = 1; i <= totalLayer; i++) {

						if (i != totalLayer && totalLayer != 1) {
							if (i == 1) {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
							} else {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
							}
							sb
									.append("<table class='table table-striped' align='center' cellpadding='1px' cellspacing='1px'> ");
							for (int j = 0; j < LMIT_VAL; j++) {
								ws.next();

								sb.append("<tr> ");
								sb
										.append("<td class='' width='20%'>").append(ws.getString(1));
								sb.append("</td> ");
								sb
										.append("<td class='' width='20%'>").append(ws.getString(2));
								sb.append("</td> ");
								sb
										.append("<td class='' width='20%'>").append(ws.getString(3));
								sb.append("</td> ");
								sb
										.append("<td class='' width='20%'>").append(ws.getString(4));
								sb.append("</td> ");
								sb
										.append("<td class='' width='10%'>").append(ws.getString(5));
								sb.append("</td> ");
								if(ws.getString(6).equals("0"))
								{	
								  sb.append("<td class='' width='10%'>Active");
						          sb.append("</td> ");
								} 
								if(ws.getString(6).equals("40"))
								{	
								  sb.append("<td class='' width='10%'>Pending");
						          sb.append("</td> ");
								} 
								sb.append("</tr> ");
							}
							sb.append("</table>");
							sb.append("</div>");

						} else {
							if (i == 1) {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
							} else {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
							}

							sb.append("<table class='table table-striped' align='center' cellpadding='1px' cellspacing='1px'> ");
							for (int k = 0; k < reminder; k++) {
								ws.next();
								sb.append("<tr> ");
								sb
										.append("<td class='' width='20%'>").append(ws.getString(1));
								sb.append("</td> ");
								sb
										.append("<td class='' width='20%'>").append(ws.getString(2));
								sb.append("</td> ");
								sb
										.append("<td class='' width='20%'>").append(ws.getString(3));
								sb.append("</td> ");
								sb
										.append("<td class='' width='20%'>").append(ws.getString(4));
								sb.append("</td> ");
								sb
										.append("<td class='' width='10%'>").append(ws.getString(5));
								sb.append("</td> ");
								if(ws.getString(6).equals("0"))
								{	
								  sb.append("<td class='' width='10%'>Active");
						          sb.append("</td> ");
								} 
								if(ws.getString(6).equals("40"))
								{	
								  sb.append("<td class='' width='10%'>Pending");
						          sb.append("</td> ");
								} 
								sb.append("</tr> ");

							}
							sb.append("</table>");
							sb.append("<table class='table table-striped' align='center' cellpadding='1px' cellspacing='1px'> ");
							sb.append("<tr> ");
							sb.append("<td class='TITLE' colspan='6'>");
							sb.append("</td> ");
							sb.append("</tr>  ");
							sb.append("</table>");
							sb.append("</div>");

						}

					}

				}
				else 
				{
					sb
					.append("<table class='table table-striped text-uppercase' align='center' cellpadding='1px' cellspacing='1px'> ");
					sb.append("<tr> ");
					sb.append("<td class='' colspan='6'>&nbsp;Received Item Details");
					sb.append("</td> ");
					sb.append("</tr>  ");
					sb.append("<tr> ");
					sb.append("<td class='' width='20%' style='font-weight:bold;'>Gifted No. ");
					sb.append("</td> ");
					sb.append("<td class='' width='20%' style='font-weight:bold;'>Gifted Date ");
					sb.append("</td> ");
					sb.append("<td class='' width='20%' style='font-weight:bold;'>Gifted By ");
					sb.append("</td> ");
					sb.append("<td class='' width='20%' style='font-weight:bold;'>Item Name ");
					sb.append("</td> ");
					sb.append("<td class='' width='10%' style='font-weight:bold;'>Qty. ");
					sb.append("</td> ");
					sb.append("<td class='' width='10%' style='font-weight:bold;'>Status");
			        sb.append("</td> ");
					sb.append("</tr>  ");
					sb.append("</table> ");
					
					sb
							.append("<table class='table' align='center' cellpadding='1px' cellspacing='1px'> ");
					sb
							.append("<tr><td class='' colspan='5' ><font color='red'>No Matching Record Found</font></td></tr>");
					sb.append("</table>");
					
				}

		
		} catch (Exception e) {

		} 

		return sb.toString();

	}
	
	public static String getUpdateReceivedItemsList(ReceiveFromThirdPartyTransFB formBean) {

		StringBuffer sb = null;

		final int LMIT_VAL = 10;

		WebRowSet ws = formBean.getWsReceivedItemList();

		try {

			sb = new StringBuffer("");

			
				if (ws != null && ws.size() > 0) {

					ws.beforeFirst();

					int noOfRecords = ws.size();

					int layerNo = noOfRecords / LMIT_VAL;

					int reminder = noOfRecords % LMIT_VAL;
					int totalLayer = layerNo;

					if (reminder > 0)
						totalLayer = totalLayer + 1;

					sb.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
					sb.append("<tr> ");
					sb.append("<td class='TITLE' colspan='6'>&nbsp;Received Item Details");
					sb.append("</td> ");
					sb.append("</tr>  ");
					sb.append("<tr>");
					sb.append("<td class='LABEL' colspan='6'>&nbsp;");
					for (int i = 1; i <= totalLayer; i++) {

						if (i == 1) {
							sb.append("<a STYLE='CURSOR:POINTER; color:"
									+ strColor + "' name='linId' id='linId" + i
									+ "' onClick='layerIndexNavigator(\"" + i
									+ "\",\"" + totalLayer + "\")'>" + i
									+ "</a> &nbsp;");
						} else {
							sb.append("<a STYLE='CURSOR:POINTER; color:"
									+ defaultColor + "' name='linId' id='linId"
									+ i + "' onClick='layerIndexNavigator(\""
									+ i + "\",\"" + totalLayer + "\")'>" + i
									+ "</a> &nbsp;");
						}
					}
					sb.append("</td>");
					sb.append("</tr>");

					sb.append("<tr> ");
					sb.append("<td class='multiLabel' width='10%'>#");
					sb.append("</td> ");					
					sb.append("<td class='multiLabel' width='15%'>Received No");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='15%'>Received Date ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Institute");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Item Name ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Received Qty");
					sb.append("</td> ");
										
					sb.append("</tr>  ");
					sb.append("</table> ");

					for (int i = 1; i <= totalLayer; i++) {

						if (i != totalLayer && totalLayer != 1) {
							if (i == 1) {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
							} else {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
							}
							sb
									.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
							for (int j = 0; j < LMIT_VAL; j++) {
								ws.next();

								sb.append("<tr> ");
								
								sb.append("<td class='multiControl' width='10%'>");
								sb.append("<input type='radio'  name='strReceivedItemApprovedMode' value='1'/>");
						        sb.append("</td> ");
								
								sb
										.append("<td class='multiControl' width='15%'>").append(ws.getString(1));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='15%'>").append(ws.getString(2));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='20%'>").append(ws.getString(3));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='20%'>").append(ws.getString(4));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='20%'>").append(ws.getString(5));
								sb.append("</td> ");
								
								
								
								
								sb.append("</tr> ");
							}
							sb.append("</table>");
							sb.append("</div>");

						} else {
							if (i == 1) {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
							} else {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
							}

							sb.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
							for (int k = 0; k < reminder; k++) {
								ws.next();
								sb.append("<tr> ");
								sb.append("<td class='multiControl' width='10%'>");
								sb.append("<input type='radio'  name='strReceivedItemApprovedMode' value='1'/>");
						        sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='15%'>").append(ws.getString(1));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='15%'>").append(ws.getString(2));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='20%'>").append(ws.getString(3));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='20%'>").append(ws.getString(4));
								sb.append("</td> ");
								
								sb.append("<td class='multiControl' width='20%'>").append(ws.getString(5));
						        sb.append("</td> ");
																
								sb.append("</tr> ");

							}
							sb.append("</table>");
							sb.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
							sb.append("<tr> ");
							sb.append("<td class='TITLE' colspan='6'>");
							sb.append("</td> ");
							sb.append("</tr>  ");
							sb.append("</table>");
							sb.append("</div>");

						}

					}

				}
				else 
				{
					sb
					.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
					sb.append("<tr> ");
					sb.append("<td class='TITLE' colspan='6'>&nbsp;Received Item Details");
					sb.append("</td> ");
					sb.append("</tr>  ");
					sb.append("<tr> ");
					sb.append("<td class='multiLabel' width='20%'>Gifted No. ");
					sb.append("</td> ");
					sb
							.append("<td class='multiLabel' width='20%'>Gifted Date ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Gifted By ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Item Name ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Qty. ");
					sb.append("</td> ");
					sb.append("</tr>  ");
					sb.append("</table> ");
					
					sb
							.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
					sb
							.append("<tr><td class='multiControl' colspan='5' ><font color='red'>No Matching Record Found</font></td></tr>");
					sb.append("</table>");
				}

		
		} catch (Exception e) {

		} 

		return sb.toString();

	}

	public static String getUpdateReceivedItemsListNEW(ReceiveFromThirdPartyTransFB formBean) {

		StringBuffer sb = null;

		final int LMIT_VAL = 10;

		WebRowSet ws = formBean.getWsReceivedItemList();

		try {

			sb = new StringBuffer("");

			
				if (ws != null && ws.size() > 0) {

					ws.beforeFirst();

					int noOfRecords = ws.size();

					int layerNo = noOfRecords / LMIT_VAL;

					int reminder = noOfRecords % LMIT_VAL;
					int totalLayer = layerNo;

					if (reminder > 0)
						totalLayer = totalLayer + 1;

					sb.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
					sb.append("<tr> ");
					sb.append("<td class='TITLE' colspan='6'>&nbsp;Received Item Details");
					sb.append("</td> ");
					sb.append("</tr>  ");
					sb.append("<tr>");
					sb.append("<td class='LABEL' colspan='6'>&nbsp;");
					for (int i = 1; i <= totalLayer; i++) {

						if (i == 1) {
							sb.append("<a STYLE='CURSOR:POINTER; color:"
									+ strColor + "' name='linId' id='linId" + i
									+ "' onClick='layerIndexNavigator(\"" + i
									+ "\",\"" + totalLayer + "\")'>" + i
									+ "</a> &nbsp;");
						} else {
							sb.append("<a STYLE='CURSOR:POINTER; color:"
									+ defaultColor + "' name='linId' id='linId"
									+ i + "' onClick='layerIndexNavigator(\""
									+ i + "\",\"" + totalLayer + "\")'>" + i
									+ "</a> &nbsp;");
						}
					}
					sb.append("</td>");
					sb.append("</tr>");

					sb.append("<tr> ");
					sb.append("<td class='multiLabel' width='10%'>#");
					sb.append("</td> ");					
					sb.append("<td class='multiLabel' width='15%'>Received No");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='15%'>Received Date ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Institute");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Item Name ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Received Qty");
					sb.append("</td> ");
										
					sb.append("</tr>  ");
					sb.append("</table> ");

					for (int i = 1; i <= totalLayer; i++) {

						if (i != totalLayer && totalLayer != 1) {
							if (i == 1) {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
							} else {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
							}
							sb
									.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
							for (int j = 0; j < LMIT_VAL; j++) {
								ws.next();

								sb.append("<tr> ");
								
								sb.append("<td class='multiControl' width='10%'>");
								sb.append("<input type='radio'  name='strReceivedItemApprovedMode' value='1'/>");
						        sb.append("</td> ");
								
								sb
										.append("<td class='multiControl' width='15%'>").append(ws.getString(1));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='15%'>").append(ws.getString(2));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='20%'>").append(ws.getString(3));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='20%'>").append(ws.getString(4));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='20%'>").append(ws.getString(5));
								sb.append("</td> ");
								
								
								
								
								sb.append("</tr> ");
							}
							sb.append("</table>");
							sb.append("</div>");

						} else {
							if (i == 1) {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
							} else {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
							}

							sb.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
							for (int k = 0; k < reminder; k++) {
								ws.next();
								sb.append("<tr> ");
								sb.append("<td class='multiControl' width='10%'>");
								sb.append("<input type='radio'  name='strReceivedItemApprovedMode' value='1'/>");
						        sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='15%'>").append(ws.getString(1));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='15%'>").append(ws.getString(2));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='20%'>").append(ws.getString(3));
								sb.append("</td> ");
								sb
										.append("<td class='multiControl' width='20%'>").append(ws.getString(4));
								sb.append("</td> ");
								
								sb.append("<td class='multiControl' width='20%'>").append(ws.getString(5));
						        sb.append("</td> ");
																
								sb.append("</tr> ");

							}
							sb.append("</table>");
							sb.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
							sb.append("<tr> ");
							sb.append("<td class='TITLE' colspan='6'>");
							sb.append("</td> ");
							sb.append("</tr>  ");
							sb.append("</table>");
							sb.append("</div>");

						}

					}

				}
				else 
				{
					sb
					.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
					sb.append("<tr> ");
					sb.append("<td class='TITLE' colspan='6'>&nbsp;Received Item Details");
					sb.append("</td> ");
					sb.append("</tr>  ");
					sb.append("<tr> ");
					sb.append("<td class='multiLabel' width='20%'>Gifted No. ");
					sb.append("</td> ");
					sb
							.append("<td class='multiLabel' width='20%'>Gifted Date ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Gifted By ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Item Name ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Qty. ");
					sb.append("</td> ");
					sb.append("</tr>  ");
					sb.append("</table> ");
					
					sb
							.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
					sb
							.append("<tr><td class='multiControl' colspan='5' ><font color='red'>No Matching Record Found</font></td></tr>");
					sb.append("</table>");
				}

		
		} catch (Exception e) {

		} 

		return sb.toString();

	}
	
	
	
	public static String getReceivedItemsList(ReceiveFromThirdPartyTransFB formBean) {

		StringBuffer sb = null;

		final int LMIT_VAL = 10;

		WebRowSet ws = formBean.getWsReceivedItemList();

		try {

			sb = new StringBuffer("");

			
				if (ws != null && ws.size() > 0) {

					ws.beforeFirst();

					int noOfRecords = ws.size();

					int layerNo = noOfRecords / LMIT_VAL;

					int reminder = noOfRecords % LMIT_VAL;
					int totalLayer = layerNo;

					if (reminder > 0)
						totalLayer = totalLayer + 1;

					sb.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
					sb.append("<tr> ");
					sb.append("<td class='TITLE' colspan='7'>&nbsp;Received Item Details");
					sb.append("</td> ");
					sb.append("</tr>  ");
					sb.append("<tr>");
					sb.append("<td class='LABEL' colspan='7'>&nbsp;");
					for (int i = 1; i <= totalLayer; i++) {

						if (i == 1) {
							sb.append("<a STYLE='CURSOR:POINTER; color:"+ strColor + "' name='linId' id='linId" + i
									+ "' onClick='layerIndexNavigator(\"" + i
									+ "\",\"" + totalLayer + "\")'>" + i
									+ "</a> &nbsp;");
						}
						else 
						{
							sb.append("<a STYLE='CURSOR:POINTER; color:"+ defaultColor + "' name='linId' id='linId"	+ i + "' onClick='layerIndexNavigator(\""
									+ i + "\",\"" + totalLayer + "\")'>" + i
									+ "</a> &nbsp;");
						}
					}
					sb.append("</td>");
					sb.append("</tr>");

					sb.append("<tr> ");
					sb.append("<td class='multiLabel' width='20%'>Received No. ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='10%'>Received Date ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Third Party Name");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Drug Name ");
					sb.append("</td> ");
					
					sb.append("<td class='multiLabel' width='10%'>Batch No. ");
					sb.append("</td> ");
					
					sb.append("<td class='multiLabel' width='10%'>Expiry Date ");
					sb.append("</td> ");
					
					sb.append("<td class='multiLabel' width='10%'>Received Qty. ");
					sb.append("</td> ");
					sb.append("</tr>  ");
					sb.append("</table> ");

					for (int i = 1; i <= totalLayer; i++) {

						if (i != totalLayer && totalLayer != 1) {
							if (i == 1) {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
							} else {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
							}
							sb
									.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
							for (int j = 0; j < LMIT_VAL; j++) {
								ws.next();

								sb.append("<tr> ");
								sb.append("<td class='multiControl' width='20%'>").append(ws.getString(1));
								sb.append("</td> ");
								sb.append("<td class='multiControl' width='10%'>").append(ws.getString(2));
								sb.append("</td> ");
								sb.append("<td class='multiControl' width='20%'>").append(ws.getString(3));
								sb.append("</td> ");
								sb.append("<td class='multiControl' width='20%'>").append(ws.getString(4));
								sb.append("</td> ");
								sb.append("<td class='multiControl' width='10%'>").append(ws.getString(6));
								sb.append("</td> ");
								sb.append("<td class='multiControl' width='10%'>").append(ws.getString(8));
								sb.append("</td> ");
								sb.append("<td class='multiControl' width='10%'>").append(ws.getString(5));
								sb.append("</td> ");
								sb.append("</tr> ");
							}
							sb.append("</table>");
							sb.append("</div>");

						} else {
							if (i == 1) {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
							} else {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
							}

							sb.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
							for (int k = 0; k < reminder; k++) {
								ws.next();
								sb.append("<tr> ");
								sb.append("<td class='multiControl' width='20%'>").append(ws.getString(1));
								sb.append("</td> ");
								sb.append("<td class='multiControl' width='10%'>").append(ws.getString(2));
								sb.append("</td> ");
								sb.append("<td class='multiControl' width='20%'>").append(ws.getString(3));
								sb.append("</td> ");
								sb.append("<td class='multiControl' width='20%'>").append(ws.getString(4));
								sb.append("</td> ");
								sb.append("<td class='multiControl' width='10%'>").append(ws.getString(6));
								sb.append("</td> ");
								sb.append("<td class='multiControl' width='10%'>").append(ws.getString(8));
								sb.append("</td> ");
								sb.append("<td class='multiControl' width='10%'>").append(ws.getString(5));
								sb.append("</td> ");
								sb.append("</tr> ");

							}
							sb.append("</table>");
							sb.append("</div>");

						}

					}

				} else {
					sb.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
					sb.append("<tr> ");
					sb.append("<td class='TITLE' colspan='7'>&nbsp;Received Item Details");
					sb.append("</td> ");
					sb.append("</tr>  ");
					sb.append("<tr> ");
					sb.append("<td class='multiLabel' width='20%'>Received No. ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Received Date ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Received By ");
					sb.append("</td> ");
					sb.append("<td class='multiLabel' width='20%'>Drug Name ");
					sb.append("</td> ");
					
					sb.append("<td class='multiLabel' width='10%'>Batch No. ");
					sb.append("</td> ");
					
					sb.append("<td class='multiLabel' width='10%'>Expiry Date ");
					sb.append("</td> ");
					
					sb.append("<td class='multiLabel' width='20%'>Received Qty.");
					sb.append("</td> ");
					sb.append("</tr>  ");
					sb.append("</table> ");
					
					sb.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
					sb.append("<tr><td class='multiControl' colspan='7' ><font color='red'>No Matching Record Found</font></td></tr>");
					sb.append("</table>");
				}

		
		} catch (Exception e) {

		} 

		return sb.toString();

	}

}
