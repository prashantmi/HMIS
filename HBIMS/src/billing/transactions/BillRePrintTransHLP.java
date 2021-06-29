package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

public class BillRePrintTransHLP {

	String popUpData = "";

	private static final String strColor = "red";
	private static final String defaultColor = "blue";
	
	static int i = 0;

//  TO DISPLAY BILL DETAILS BY CR No.
	public static String getBillListingView1(BillRePrintTransVO voObj) 
	{
		StringBuffer sb = new StringBuffer("");
		WebRowSet ws = voObj.getBillSearchList();
		try 
		{
			int start = Integer.parseInt(voObj.getStrBillFromRow());
			int actualBlockSet = Integer.parseInt(voObj.getStrBillCtBlockSet());

			final int REC_PER_PAGE = Integer.parseInt(voObj.getStrBillRowPerPage());
			final int PAGE_PER_BLOCK = 10;

			int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
			int totalRecordsToManipulate = totalFetchRecord - 1;

			int previous = 1;
			int next = 0;

			if (ws != null) 
			{
			//	System.out.println("bill re print--wb size--"+ws.size());
				if (ws.size() != 0) 
				{
					int actualFetchRecord = ws.size();

					if (actualBlockSet == 1)
						actualBlockSet = actualBlockSet + 1;

					next = ((actualBlockSet * PAGE_PER_BLOCK) - PAGE_PER_BLOCK) + 1;
					previous = (((actualBlockSet - 2) * PAGE_PER_BLOCK) - PAGE_PER_BLOCK) + 1;

					if (totalFetchRecord != actualFetchRecord) 
					{
						totalFetchRecord = actualFetchRecord;
						totalRecordsToManipulate = actualFetchRecord;
						next = 0;

					}
					int totalLayer = totalRecordsToManipulate / REC_PER_PAGE;
					int reminder = totalRecordsToManipulate % REC_PER_PAGE;

					if (reminder > 0)
						totalLayer = totalLayer + 1;

			 

					
					sb
					.append("<table width='100%'align='center' cellspacing='1px'>");

			sb.append("<tr>");
			sb.append("<td class='LABEL'>&nbsp;");

			if (start != 1){
				sb
						.append("<a STYLE='CURSOR:POINTER'  onClick='fetchPatientList(\""+previous+"\",\""+(actualBlockSet - 1)+"\");'> <FONT COLOR='"+strColor+"'> &lt;&lt; Previous</FONT> </a> &nbsp;");
				
			}
			for (int i = 1; i <= totalLayer; i++) {
				
				if(i == 1){
					sb.append("<a STYLE='CURSOR:POINTER; color:"+strColor+"' name='linId' id='linId"+i+"' onClick='layerIndexNavigator(\"" + i
							+ "\",\"" + totalLayer + "\")'>");	
				sb.append((i + start - 1));
				
				}else{
					sb.append("<a STYLE='CURSOR:POINTER; color:"+defaultColor+"' name='linId' id='linId"+i+"' onClick='layerIndexNavigator(\"" + i
							+ "\",\"" + totalLayer + "\")'>");
					sb.append((i + start - 1));
				}
				sb.append("</a> &nbsp;");
			}

			if (next > 0 )
				sb.append("<a STYLE='CURSOR:POINTER' onClick='fetchPatientList(\""+next+"\",\""+(actualBlockSet + 1)+"\");'> <FONT COLOR='"+strColor+"'> Next &gt;&gt;</FONT></a>");

			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
					
					
					sb.append("<table width='100%'align='center' cellspacing='1px'>");
					sb.append("<tr>");
					sb.append("<td width='5%'class='multiLabel'>");
					
					sb.append("<td width='15%'class='multiLabel'>CR. No.");
					sb.append("</td>");
					sb.append("<td width='25%'class='multiLabel'>Patient Name");
					sb.append("</td>");
					
					sb.append("<td width='15%'class='multiLabel'>Bill No.");
					sb.append("</td>");
					sb.append("<td width='10%'class='multiLabel'>Bill Date");
					sb.append("</td>");
					sb.append("</td>");
					sb.append("<td width='25%'class='multiLabel'>Service Name");
					sb.append("</td>");
					sb.append("<td width='15%'class='multiLabel'>Amount");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("</table>");
					for (int i = 1; i <= totalLayer; i++) 
					{
						if (i < totalLayer|| (i == totalLayer && reminder == 0)) 
						{
						 if (i == 1) 
						 {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
						  }
						  else 
						  {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
						  }

							sb.append("<table width='100%'align='center' cellspacing='1px'>");
							for (int j = 0; j < REC_PER_PAGE; j++) 
							{
								ws.next();
								// ++index;
								sb
										.append("<tr ><td class='multiLabel' width='5%'><input type='radio' name='radio' value='");
								sb.append(ws.getString(5));
								sb.append("'></td>");
								
								sb
								.append("<td class='multiControl' width='15%'>");
						sb.append(ws.getString(6));
						sb.append("</td>");
						
						sb
						.append("<td class='multiControl' width='25%'>");
				sb.append(ws.getString(7));
				sb.append("</td>");
								
								sb
										.append("<td class='multiControl' width='15%'>");
								sb.append(ws.getString(1));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='15%'>");
								sb.append(ws.getString(2));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='25%'>");
								sb.append(ws.getString(3));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='15%'>");
								sb.append(HisUtil.getAmountWithDecimal(ws.getString(4), 2));
								sb.append("</td>");
								sb.append("</tr>");

							}
							sb.append("</table>");
							sb.append("</div>");

						} 
						else 
						{
							if (i == 1) 
							{
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
							} 
							else 
							{
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
							}
							sb.append("<table width='100%'align='center' cellspacing='1px'>");
							for (int k = 0; k < reminder; k++)
							{
								ws.next();

								sb
								.append("<tr ><td class='multiLabel' width='5%'><input type='radio' name='radio' value='");
						sb.append(ws.getString(5));
						sb.append("'></td>");
						
						sb
						.append("<td class='multiControl' width='15%'>");
				sb.append(ws.getString(6));
				sb.append("</td>");
				
				sb
				.append("<td class='multiControl' width='25%'>");
		sb.append(ws.getString(7));
		sb.append("</td>");
						
						sb
								.append("<td class='multiControl' width='15%'>");
						sb.append(ws.getString(1));
						sb.append("</td>");
						sb
								.append("<td class='multiControl' width='15%'>");
						sb.append(ws.getString(2));
						sb.append("</td>");
						sb
								.append("<td class='multiControl' width='25%'>");
						sb.append(ws.getString(3));
						sb.append("</td>");
						sb
								.append("<td class='multiControl' width='15%'>");
						sb.append(HisUtil.getAmountWithDecimal(ws.getString(4), 2));
						sb.append("</td>");
						sb.append("</tr>");
							}
							sb.append("</table>");
							sb.append("</div>");

						}

					}

					sb.append("</td>");
					sb.append("</'tr>");

					sb.append("</table>");

				} 
				else 
				{
					sb.append("<table width='100%'align='center' cellspacing='1px'>");
					sb.append("<tr><td class='multiControl'><font color='red'>No Record of Bill Details Found For Entered Cr No</font></td></tr>");
					sb.append("</table>");
				}

			} 
			else 
			{
				sb.append("<table width='100%'align='center' cellspacing='1px'>");
				sb.append("<tr><td class='multiControl'><font color='red'>No Record of Bill Details Found For Entered Cr No</font></td></tr>");
				sb.append("</table>");
    		}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			new HisException("Global Patient Listing",
					"billing.BillRePrintTransHLP.getPatientListingView()-->", e
							.getMessage());
		}

		return sb.toString();

	}

	// ( FROM BALA) TO DISPLAY BILL DETAILS BY GUARANTOR NAME

	public static String getBillListingView2(BillRePrintTransVO voObj) {

		String temp[] = new String[3];
		int index = 0;
		StringBuffer sb = new StringBuffer("");

		WebRowSet ws = voObj.getBillSearchList();

		try {

			int start = Integer.parseInt(voObj.getStrBillFromRow());
			int actualBlockSet = Integer.parseInt(voObj.getStrBillCtBlockSet());

			final int REC_PER_PAGE = Integer.parseInt(voObj
					.getStrBillRowPerPage());
			final int PAGE_PER_BLOCK = 10;

			int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
			int totalRecordsToManipulate = totalFetchRecord - 1;

			int previous = 1;
			int next = 0;

			if (ws != null) {

				if (ws.size() != 0) {

					int actualFetchRecord = ws.size();

					if (actualBlockSet == 1)
						actualBlockSet = actualBlockSet + 1;

					next = ((actualBlockSet * PAGE_PER_BLOCK) - PAGE_PER_BLOCK) + 1;
					previous = (((actualBlockSet - 2) * PAGE_PER_BLOCK) - PAGE_PER_BLOCK) + 1;

					if (totalFetchRecord != actualFetchRecord) {

						totalFetchRecord = actualFetchRecord;
						totalRecordsToManipulate = actualFetchRecord;
						next = 0;

					}

					int totalLayer = totalRecordsToManipulate / REC_PER_PAGE;
					int reminder = totalRecordsToManipulate % REC_PER_PAGE;

					if (reminder > 0)
						totalLayer = totalLayer + 1;

				/*	sb
							.append("<table width='100%'align='center' cellspacing='1px'>");

					sb.append("<tr class='HEADER'> ");
					sb.append("<td colspan='5'>Bill Details &gt&gt</td>");

					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td class='LABEL'>&nbsp;");
					sb
							.append(" <input type='hidden' name='noOfRows' id='noOfRows' value="
									+ ws.size() + " >");

					if (start != 1) {
						sb.append("<a href='#' onClick='fetchBillList(\""
								+ previous + "\",\"" + (actualBlockSet - 1)
								+ "\");'> &lt;&lt; Previous</a> &nbsp;");

					}
					for (int i = 1; i <= totalLayer; i++) {
						sb.append("<a href='#' onClick='layerIndexNavigator(\""
								+ i + "\",\"" + totalLayer + "\")'>"
								+ (i + start - 1) + "</a> &nbsp;");
					}

					if (next > 0)
						sb.append("<a href='#' onClick='fetchBillList(\""
								+ next + "\",\"" + (actualBlockSet + 1)
								+ "\");'> Next &gt;&gt;</a>");

					sb.append("</td>");
					sb.append("</tr>");
					sb.append("</table>");*/

					
					sb
					.append("<table width='100%'align='center' cellspacing='1px'>");

			sb.append("<tr>");
			sb.append("<td class='LABEL'>&nbsp;");

			if (start != 1){
				sb
						.append("<a STYLE='CURSOR:POINTER'  onClick='fetchPatientList(\""+previous+"\",\""+(actualBlockSet - 1)+"\");'> <FONT COLOR='"+strColor+"'> &lt;&lt; Previous</FONT> </a> &nbsp;");
				
			}
			for (int i = 1; i <= totalLayer; i++) {
				
				if(i == 1){
					sb.append("<a STYLE='CURSOR:POINTER; color:"+strColor+"' name='linId' id='linId"+i+"' onClick='layerIndexNavigator(\"" + i
							+ "\",\"" + totalLayer + "\")'>");	
				sb.append((i + start - 1));
				
				}else{
					sb.append("<a STYLE='CURSOR:POINTER; color:"+defaultColor+"' name='linId' id='linId"+i+"' onClick='layerIndexNavigator(\"" + i
							+ "\",\"" + totalLayer + "\")'>");
					sb.append((i + start - 1));
				}
				sb.append("</a> &nbsp;");
			}

			if (next > 0 )
				sb.append("<a STYLE='CURSOR:POINTER' onClick='fetchPatientList(\""+next+"\",\""+(actualBlockSet + 1)+"\");'> <FONT COLOR='"+strColor+"'> Next &gt;&gt;</FONT></a>");

			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
					
					sb
							.append("<table width='100%'align='center' cellspacing='1px'>");

					sb.append("<tr>");
					sb.append("<td width='5%'class='multiLabel'>");
					
					sb.append("<td width='15%'class='multiLabel'>CR. No.");
					sb.append("</td>");
										
					sb.append("<td width='25%'class='multiLabel'>Patient Name");
					sb.append("</td>");
					sb.append("<td width='15%'class='multiLabel'>Bill No.");
					sb.append("</td>");
					sb.append("<td width='10%'class='multiLabel'>Bill Date");
					sb.append("</td>");
					sb.append("</td>");
					sb
							.append("<td width='25%'class='multiLabel'>Service Name");
					sb.append("</td>");
					sb.append("<td width='15%'class='multiLabel'>Amount");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("</table>");

					for (int i = 1; i <= totalLayer; i++) {

						if (i < totalLayer
								|| (i == totalLayer && reminder == 0)) {
							if (i == 1) {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
							} else {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
							}

							sb
									.append("<table width='100%'align='center' cellspacing='1px'>");
							for (int j = 0; j < REC_PER_PAGE; j++) {
								ws.next();
								
								temp = ws.getString(5).replace("^", "#").split("#");
										
								sb
										.append("<tr ><td class='multiLabel' width='5%'><input type='radio' name='radio' value='");
								sb.append(ws.getString(5));
								sb.append("'></td>");
								
								sb
								.append("<td class='multiControl' width='15%'>");
						sb.append(ws.getString(6));
						sb.append("</td>");
						 								
								sb
										.append("<td class='multiControl' width='25%'>");
								sb.append(temp[2]);
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='15%'>");
								sb.append(ws.getString(1));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='15%'>");
								sb.append(ws.getString(2));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='25%'>");
								sb.append(ws.getString(3));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='15%'>");
								sb.append(HisUtil.getAmountWithDecimal(ws.getString(4), 2));
								sb.append("</td>");
								sb.append("</tr>");
								index++;
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
							sb
									.append("<table width='100%'align='center' cellspacing='1px'>");
							for (int k = 0; k < reminder; k++) {
								ws.next();
								temp = ws.getString(5).replace('^', '#').split(
										"#");

								sb
								.append("<tr ><td class='multiLabel' width='5%'><input type='radio' name='radio' value='");
						sb.append(ws.getString(5));
						sb.append("'></td>");
						
						sb
						.append("<td class='multiControl' width='15%'>");
				sb.append(ws.getString(6));
				sb.append("</td>");
				 								
						sb
								.append("<td class='multiControl' width='25%'>");
						sb.append(temp[2]);
						sb.append("</td>");
						sb
								.append("<td class='multiControl' width='15%'>");
						sb.append(ws.getString(1));
						sb.append("</td>");
						sb
								.append("<td class='multiControl' width='15%'>");
						sb.append(ws.getString(2));
						sb.append("</td>");
						sb
								.append("<td class='multiControl' width='25%'>");
						sb.append(ws.getString(3));
						sb.append("</td>");
						sb
								.append("<td class='multiControl' width='15%'>");
						sb.append(HisUtil.getAmountWithDecimal(ws.getString(4), 2));
						sb.append("</td>");
						sb.append("</tr>");
							}
							sb.append("</table>");
							sb.append("</div>");

						}

					}

					sb.append("</td>");
					sb.append("</'tr>");

					sb.append("</table>");

				} else {
					sb
							.append("<table width='100%'align='center' cellspacing='1px'>");
					sb
							.append("<tr><td class='multiControl'><font color='red'>No Record of Bill Details Found For Entered Data</font></td></tr>");
					sb.append("</table>");
				}

			} else {

				sb.append("<table width='100%'align='center' cellspacing='1px'>");
				sb.append("<tr><td class='multiControl'><font color='red'>No Record of Bill Details Found For Entered Cr No</font></td></tr>");
				sb.append("</table>");
			}

		} catch (Exception e) {
		
			new HisException("Global Patient Listing",
					"billing.BillRePrintTransHLP.getPatientListingView()-->", e
							.getMessage());
		}

		return sb.toString();

	}
	
	public static String patientDtlBillDetail(WebRowSet ws,String strBillNo,String strRcptNo) throws Exception
	{
		 StringBuffer sb = new StringBuffer("");
	   try
	   {
    	  if (ws != null) 
		   {
			if (ws.next())
				{
					String strPukNo          = ws.getString(1);
					String strPatientName    = ws.getString(2);
					String strCategoryName   = ws.getString(3);
					String strBillService    = ws.getString(4);
					String strRePrintChg     = ws.getString(5);
					String strBServiceId     = ws.getString(6);
					String strChargeTypeID   = ws.getString(7);
					String strPatientCatCode = ws.getString(8);
					String strPatActNo       = ws.getString(9);	
					String strAdmnNo         = ws.getString(10);	
					String strDeptCode       = ws.getString(11);
					String strWardCode       = ws.getString(12);
					String strRcptType       = ws.getString(13);
					String strRcptName       = ws.getString(14);
					String strAmount		 = ws.getString(15);
				
					if (strPukNo == null)
						strPukNo = "----";
					if (strPatientName == null)
						strPatientName = "----";
					if (strBillService == null)
						strBillService = "----";
					if (strRePrintChg == null)
						strRePrintChg = "----";
					if (strCategoryName == null)
						strCategoryName = "----";
					if (strBServiceId == null)
						strBServiceId = "0";
					if (strChargeTypeID == null)
						strChargeTypeID = "0";
					if (strPatientCatCode == null)
						strPatientCatCode = "0";
					if (strPatActNo == null)
						strPatActNo = "0";
					if (strAdmnNo == null)
						strAdmnNo = "0";
					if (strDeptCode == null)
						strDeptCode = "0";
					if (strWardCode == null)
						strWardCode = "0";
					
					if (strRcptType == null)
						strRcptType = "0";
					if (strRcptName == null)
						strRcptName = "----";
											
					sb.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center' ><tr><td width='25%' class='LABEL'>CR No</td>");
					sb.append("<td colspan='3' width='25%' class='CONTROL'> ");
					sb.append(strPukNo);
					sb.append("</td></tr>");
					sb.append("<tr><td width='25%' class='LABEL'>Patient Name</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strPatientName);
					sb.append("<td width='25%' class='LABEL'>Category</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strCategoryName);
					sb.append("</td></tr>");
					sb.append("<tr><td width='25%' class='LABEL'>Billing Service</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strBillService);
					sb.append("</td>");
					sb.append("<td width='25%' class='LABEL'>Recipt Type</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strRcptName);
					sb.append("</td></tr>");
					sb.append("<tr><td width='25%' class='LABEL'>Receipt Amount</td>");
					sb.append("<td colspan='3' width='75%' class='CONTROL'>");
					sb.append(HisUtil.getAmountWithDecimal(strAmount, 2));
					sb.append("</td>");
					sb.append("</tr>");
					
					
					sb.append("<input type='hidden' name='strPukNo'        value='"+strPukNo+"'>");
					sb.append("<input type='hidden' name='strPatientName'  value='"+strPatientName+"'>");
					sb.append("<input type='hidden' name='strCategoryName' value='"+strCategoryName+"'>");
					sb.append("<input type='hidden' name='strBillService'  value='"+strBillService+"'>");
					sb.append("<input type='hidden' name='strRePrintChg'   value='"+strRePrintChg+"'>");
								
					sb.append("<input type='hidden' name='strBServiceId'     value='"+strBServiceId+"'>");
					sb.append("<input type='hidden' name='strChargeTypeID'   value='"+strChargeTypeID+"'>");
					sb.append("<input type='hidden' name='strPatientCatCode' value='"+strPatientCatCode+"'>");
					sb.append("<input type='hidden' name='strBillNo'         value='"+strBillNo+"'>");
					sb.append("<input type='hidden' name='strPatActNo'       value='"+strPatActNo+"'>");
					
					sb.append("<input type='hidden' name='strAdmnNo'   value='"+strAdmnNo+"'>");
					sb.append("<input type='hidden' name='strDeptCode' value='"+strDeptCode+"'>");
					sb.append("<input type='hidden' name='strWardCode' value='"+strWardCode+"'>");
					
					sb.append("<input type='hidden' name='strRcptType' value='"+strRcptType+"'>");
					//sb.append("<input type='hidden' name='strRcptNo' value='"+strRcptNo+"'>");
					
				}
    		sb.append("</table>");
		  }
		  else
	      	 {
			        sb.append("<table class='TABLEWIDTH' align='center'  border='0' bgcolor='#000000' cellspacing ='1px'>"); 
					sb.append("<tr>");
					sb.append("<td colspan='4'  CLASS='multiControl' >"
							+ "<DIV class='errMsg' align='center'> NO RECORD FOUND FOR SELECTED Bill NO & Rcpt No!!!</div>" + "</TD>");
	     			sb.append("</tr>");
					sb.append("</table></div>");	
	      	 }	
		
		 
	
	   }
	   catch(Exception e)
	   {
		   new HisException("Global Patient Listing",
					"billing.BillRePrintTransHLP.patientDtlBillDetail()-->", e
							.getMessage());
	   }

		return sb.toString();
	}
	

}
