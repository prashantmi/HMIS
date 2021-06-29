package billing.reports;
/*
 * Bill Enquiry Report HLP
 * 
 * author: Debashis Sardar
 * 
 * dated: 05/03/2012
 */
import java.sql.SQLException;
import javax.sql.rowset.WebRowSet;

public class BillEnquiryRptHLPNew {
	
	public static String getBillDtls(BillEnquiryRptVO VO) throws Exception {

		WebRowSet ws = VO.getStrBillDetailsWs();

		StringBuffer sBuffer = new StringBuffer("");
		ws.beforeFirst();
		
		sBuffer
		.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px' > ");

		if (ws != null && ws.size() != 0) {		
			sBuffer.append("<tr> ");
			sBuffer.append("<td class='multiLabel' width ='5%'><input type='checkbox' name='chkmain' onclick='checkall(this);' onkeypress='checkallevent(event,this)'></td> ");
			sBuffer.append("<td class='multiLabel' width ='5%'>Sl. No.</td> ");
			sBuffer.append("<td class='multiLabel' width ='25%'>Bill Date</td> ");
			sBuffer.append(" <td CLASS='multiLabel' width ='20%'>Bill No / Receipt No</td> ");
			sBuffer.append(" <td CLASS='multiLabel' width ='10%'>Bill Amount(Rs.)</td> ");
			sBuffer.append("<td class='multiLabel' width ='20%'>Payment Mode</td> ");
			sBuffer.append(" <td CLASS='multiLabel' width ='5%'>Save Pdf</td> ");
			sBuffer.append(" </tr>");
			
			try {
                double totalAmt=0; 
				int i = 0;
				while (ws.next()){
					
					String strBillDate=ws.getString(1);
					String strBillNo = ws.getString(2);
					String strBillAmt = ws.getString(3);
					String paymode= ws.getString(4);
					String strFiletype="pdf";
					totalAmt=totalAmt+ Double.parseDouble(strBillAmt);
					int slNo=i+1;
				//	int serialNo[]=new int [20];
				//	 serialNo[i]=slNo;
					if (strBillDate == null)
						strBillDate = "-";
					if (strBillNo == null)
						strBillNo = "-";
					if (strBillAmt == null)
						strBillAmt = "-";
		       String strBill=strBillNo.replace("/", "@").split("@")[0];
		       String strRcpt=strBillNo.replace("/", "@").split("@")[1];
		      
		       
						sBuffer.append("<tr class ='multiControl'> ");
							
								
							sBuffer.append(" <td width ='5%'><input type='checkbox' name='chk' value='"+strBillNo+"'></td> ");
							sBuffer.append(" <td width ='5%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + slNo + "</font></td> ");
							sBuffer.append(" <td width ='25%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strBillDate + "</font></td> ");
							sBuffer.append(" <td width ='20%'><a id='viewBill' style='cursor: pointer;' onclick='viewBill(event," + strBill + ","+strRcpt+")' title='View Bill'><font color=\"blue\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\"><u>" + strBillNo + "</u></font></a> </td> ");
							sBuffer.append(" <td width ='10%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + Double.parseDouble(strBillAmt) + "</font></td> ");
							sBuffer.append(" <td width ='20%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + paymode + "</font></td> ");
							sBuffer.append(" <input type='hidden' name='billDate' value='"+strBillDate+"'> ");
						//	sBuffer.append(" <input type='hidden' name='serialNo' value='"+serialNo+"'> ");
							//sBuffer.append(" <td width ='15%'><input type='button' value='Save' onclick='forceDownloadPdf(" + strBill + ","+strRcpt+")'></td> ");
							sBuffer.append(" <td width ='5%'><img	style='cursor: hand; cursor: pointer'	src='../../hisglobal/images/pdf.jpg' onClick='forceDownloadPdf(" + strBill + ","+strRcpt+")'></td> ");
							sBuffer.append("</td>");
							sBuffer.append(" </tr> ");
							
							i++;					
						}
				sBuffer.append("<tr class ='multiControl'> ");
				sBuffer.append("<td class='multiLabel' width ='5%'></td> ");
				sBuffer.append("<td class='multiLabel' width ='10%'></td> ");
				sBuffer.append("<td class='multiLabel' width ='25%'></td> ");
				/*sBuffer.append("<td class='multiLabel' width ='25%' align ='right'>Grand Total(Rs.) </td> ");
				sBuffer.append(" <td width ='25%' ><b><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + totalAmt + "</font></b></td> ");*/
				sBuffer.append(" </tr> ");
				
			} catch (SQLException e) {
				e.printStackTrace();
				VO.setStrMsgString("BillEnquiryRptHLP.getBillDtls() --> "
						+ e.getMessage());
				VO.setStrMsgType("1");
				throw new Exception("BillEnquiryRptHLP.getBillDtls() -->"
						+ e.getMessage());
			}
		} 
		else{
			sBuffer.append("<tr><div align=\"center\"><font color=\"red\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">No Bill Details Found!</font></div></tr>");
				
		}
		sBuffer.append("</table > ");
		sBuffer
		.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px' style='display:none;'> ");
		sBuffer.append("<tr class='TITLE'>");
		sBuffer.append("<td  colspan='2'>Report Details</td>\n");
		sBuffer.append("</tr>");
		sBuffer.append("</table>");
		sBuffer
		.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px' style='display:none;'> ");
		sBuffer.append("<tr> ");
		sBuffer.append("<td class='multiLabel' width ='50%' align ='right'>Report Type </td> ");
		sBuffer.append("<td width='50%' class='CONTROL' >");
        sBuffer.append("<select style='cursor:pointer;cursor:hand' name = 'strReportFormat' class='comboNormal' onChange =''><option value='html'>Html</option><option value='pdf'>Pdf</option></select>");
        sBuffer.append("</td>");
        sBuffer.append("<tr> ");
        sBuffer.append("</table > ");
        
		sBuffer
		.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px' > ");
		sBuffer.append("<tr class='FOOTER'>");
		sBuffer.append("<td  colspan='2'> </td>\n");
		sBuffer.append("</tr>");
		sBuffer.append("</table>");
		sBuffer
		.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
		sBuffer.append("<tr>");
		sBuffer.append("<td colspan='2' align='center'>");
		//sBuffer.append("<img src='../../hisglobal/images/btn-generate.png' style='cursor:hand;cursor:pointer;'  title ='generate ' onClick ='generate();' />&nbsp;");
		sBuffer.append("<br><a href='#' class='button' onClick='generate();' ><span class='generate'>Generate</span></a>");
		sBuffer.append("<a href='#' class='button' onClick='clearPage();' ><span class='clear'>Clear</span></a>");
		sBuffer.append("<a href='#' class='button' onClick='cancelFunc();' ><span class='cancel'>Cancel</span></a>");
		//sBuffer.append("<img src='../../hisglobal/images/btn-clr.png'  style='cursor:hand;cursor:pointer;'  title ='clear ' onClick ='clearPage();' />&nbsp;");
		//sBuffer.append("<img src='../../hisglobal/images/btn-ccl.png' style='cursor:hand;cursor:pointer;'  title ='cancel' onClick ='cancelPage();' />&nbsp;");
		sBuffer.append("</td></tr>");
		sBuffer.append("</table>");
		return sBuffer.toString();
	}
	
	
	public static String getBillDtls_BS(BillEnquiryRptVO VO) throws Exception {

		WebRowSet ws = VO.getStrBillDetailsWs();

		StringBuffer sBuffer = new StringBuffer("");
		ws.beforeFirst();
		
		sBuffer.append("<table class='table'> ");

		if (ws != null && ws.size() != 0) {		
			sBuffer.append("<thead> ");
			sBuffer.append("<tr> ");
			sBuffer.append("<th><input type='checkbox' name='chkmain' onclick='checkall(this);' onkeypress='checkallevent(event,this)'></th> ");
			
			/*sBuffer.append("<th><div class='custom-control custom-checkbox mb-3'>");
			sBuffer.append("<input type='checkbox' class='custom-control-input' name=' chkmain'onclick='checkall(this);' onkeypress='checkallevent(event,this)' id='customCheck1' >");
			sBuffer.append("<label class='custom-control-label' for='customCheck1'></label>");
			sBuffer.append("</div></th>");
			*/
			
			
			sBuffer.append("<th><font color='#000000'  size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>Sl. No.</font></th> ");
			sBuffer.append("<th><font color='#000000'  size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>Bill Date</font></th> ");
			sBuffer.append("<th><font color='#000000'  size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>Bill No / Receipt No</font></th> ");
			sBuffer.append("<th><font color='#000000'  size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>Bill Amount(Rs.)</font></th> ");
			sBuffer.append("<th><font color='#000000'  size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>Payment Mode</font></th> ");
			sBuffer.append("<th><font color='#000000'  size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>Save Pdf</font></th> ");
			sBuffer.append(" </tr>");
			sBuffer.append("</thead> ");
			sBuffer.append("<tbody> ");
			
			try {
                double totalAmt=0; 
				int i = 0;
				while (ws.next()){
					
					String strBillDate=ws.getString(1);
					String strBillNo = ws.getString(2);
					String strBillAmt = ws.getString(3);
					String paymode= ws.getString(4);
					String strFiletype="pdf";
					totalAmt=totalAmt+ Double.parseDouble(strBillAmt);
					int slNo=i+1;
				//	int serialNo[]=new int [20];
				//	 serialNo[i]=slNo;
					if (strBillDate == null)
						strBillDate = "-";
					if (strBillNo == null)
						strBillNo = "-";
					if (strBillAmt == null)
						strBillAmt = "-";
		       String strBill=strBillNo.replace("/", "@").split("@")[0];
		       String strRcpt=strBillNo.replace("/", "@").split("@")[1];
		      
		                   
				          //sBuffer.append("<td  width='1%'><label  style='background-color: #"+arr[i]+";color: #fff;border-radius: .15em;padding: 1px 5px;font-weight: 600;'>"+ws.getString(5)+"</label></td>");

						    sBuffer.append("<tr> ");
							sBuffer.append("<td><input type='checkbox' name='chk' value='"+strBillNo+"'></td> ");
							/*sBuffer.append("<td><div class='custom-control custom-checkbox mb-3'>");
							sBuffer.append("<input type='checkbox' class='custom-control-input'name='chk' value='"+strBillNo+"' id='customCheck' >");
							sBuffer.append("<label class='custom-control-label' for='customCheck'></label>");
							sBuffer.append("</div></td>");*/
							sBuffer.append("<td><font color='#000000'  size=\"2\" face='Helvetica Neue, Helvetica, Arial, sans-serif'>" + slNo + "</font></td> ");
							sBuffer.append("<td><font color='#000000'  size=\"2\" face='Helvetica Neue, Helvetica, Arial, sans-serif'>" + strBillDate + "</font></td> ");
							sBuffer.append("<td><a id='viewBill' class='btn btn-info viewbill' data-toggle='modal' data-target='#billModal' onclick='viewBill(event," + strBill + ","+strRcpt+")' title='View Bill'>" + strBillNo + "</a> </td> ");
							sBuffer.append("<td><font color='#000000'  size=\"2\" face='Helvetica Neue, Helvetica, Arial, sans-serif'>" + Double.parseDouble(strBillAmt) + "</font></td> ");
							sBuffer.append("<td><font color='#000000' size=\"2\" face='Helvetica Neue, Helvetica, Arial, sans-serif'>" + paymode + "</font></td> ");
							sBuffer.append("<input type='hidden' name='billDate' value='"+strBillDate+"'> ");
							sBuffer.append(" <td><button typ='button' class='btn btn-outline-default' onClick='forceDownloadPdf("+strBill+","+strRcpt+")'><i class='fas fa-file-pdf'></i></button></td> ");
							sBuffer.append(" </tr> ");
							
							i++;					
						}
				/*sBuffer.append("<tr class ='multiControl'> "); 
				sBuffer.append("<td class='multiLabel' width ='5%'></td> ");
				sBuffer.append("<td class='multiLabel' width ='10%'></td> ");
				sBuffer.append("<td class='multiLabel' width ='25%'></td> ");
				sBuffer.append("<td class='multiLabel' width ='25%' align ='right'>Grand Total(Rs.) </td> ");
				sBuffer.append(" <td width ='25%' ><b><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + totalAmt + "</font></b></td> ");
				sBuffer.append(" </tr> ");*/
				
			}
			
			catch (SQLException e) {
				e.printStackTrace();
				VO.setStrMsgString("BillEnquiryRptHLP.getBillDtls() --> "
						+ e.getMessage());
				VO.setStrMsgType("1");
				throw new Exception("BillEnquiryRptHLP.getBillDtls() -->"
						+ e.getMessage());
			}
		} 
		else{
			sBuffer.append("<div class='alert alert-danger'>No Bill Details Found!</font></div>");
				
		}
		sBuffer.append("</body > ");
		sBuffer.append("</table > ");
		/*sBuffer.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px' style='display:none;'> ");
		sBuffer.append("<tr class='TITLE'>");
		sBuffer.append("<td  colspan='2'>Report Details</td>\n");
		sBuffer.append("</tr>");
		sBuffer.append("</table>");
		sBuffer.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px' style='display:none;'> ");
		sBuffer.append("<tr> ");
		sBuffer.append("<td class='multiLabel' width ='50%' align ='right'>Report Type </td> ");
		sBuffer.append("<td width='50%' class='CONTROL' >");
        sBuffer.append("<select style='cursor:pointer;cursor:hand' name = 'strReportFormat' class='comboNormal' onChange =''><option value='html'>Html</option><option value='pdf'>Pdf</option></select>");
        sBuffer.append("</td>");
        sBuffer.append("<tr> ");
        sBuffer.append("</table > ");*/
        
		/*sBuffer.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px' > ");
		sBuffer.append("<tr class='FOOTER'>");
		sBuffer.append("<td  colspan='2'> </td>\n");
		sBuffer.append("</tr>");
		sBuffer.append("</table>");*/
		/*sBuffer.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
		sBuffer.append("<tr>");
		sBuffer.append("<td colspan='2' align='center'>");
		//sBuffer.append("<img src='../../hisglobal/images/btn-generate.png' style='cursor:hand;cursor:pointer;'  title ='generate ' onClick ='generate();' />&nbsp;");
		sBuffer.append("<br><a href='#' class='button' onClick='generate();' ><span class='generate'>Generate</span></a>");
		sBuffer.append("<a href='#' class='button' onClick='clearPage();' ><span class='clear'>Clear</span></a>");
		sBuffer.append("<a href='#' class='button' onClick='cancelFunc();' ><span class='cancel'>Cancel</span></a>");
		//sBuffer.append("<img src='../../hisglobal/images/btn-clr.png'  style='cursor:hand;cursor:pointer;'  title ='clear ' onClick ='clearPage();' />&nbsp;");
		//sBuffer.append("<img src='../../hisglobal/images/btn-ccl.png' style='cursor:hand;cursor:pointer;'  title ='cancel' onClick ='cancelPage();' />&nbsp;");
		sBuffer.append("</td></tr>");
		sBuffer.append("</table>");*/
		return sBuffer.toString();
	}
	
	public static String getBillContent(BillEnquiryRptVO VO) throws Exception 
	{
			BillEnquiryRptBO bo = null;

			StringBuffer sBuffer = new StringBuffer("");
		
		
			sBuffer.append("<table width='90%' align ='center' cellspacing ='1px' >");	
			sBuffer.append("<tr>");
			sBuffer.append("<td  width ='10%' style='font-weight: normal; border-top: 1px solid rgb(0, 0, 0); border-right: 1px solid rgb(0, 0, 0); border-bottom: 1px solid rgb(0, 0, 0); border-left: 1px solid rgb(0, 0, 0);background-color: gray;'><font size='2'><b>Sl. No.</b></font></td>");
			sBuffer.append("<td  width ='25%' style='font-weight: normal; border-top: 1px solid rgb(0, 0, 0); border-right: 1px solid rgb(0, 0, 0); border-bottom: 1px solid rgb(0, 0, 0); border-left: 1px solid rgb(0, 0, 0);background-color: gray;'><font size='2'><b>Date Range</b></font></td>");
			sBuffer.append("<td  width ='5%' style='font-weight: normal; border-top: 1px solid rgb(0, 0, 0); border-right: 1px solid rgb(0, 0, 0); border-bottom: 1px solid rgb(0, 0, 0); border-left: 1px solid rgb(0, 0, 0);background-color: gray;'><font size='2'><b>Tariff Code</b></font></td>");
			sBuffer.append("<td  width ='25%' style='font-weight: normal; border-top: 1px solid rgb(0, 0, 0); border-right: 1px solid rgb(0, 0, 0); border-bottom: 1px solid rgb(0, 0, 0); border-left: 1px solid rgb(0, 0, 0);background-color: gray;'><font size='2'><b>Tariff Name</b></font></td>");
			sBuffer.append("<td  width ='5%' style='font-weight: normal; border-top: 1px solid rgb(0, 0, 0); border-right: 1px solid rgb(0, 0, 0); border-bottom: 1px solid rgb(0, 0, 0); border-left: 1px solid rgb(0, 0, 0);background-color: gray;'><font size='2'><b>Quantity</b></font></td>");
			sBuffer.append("<td  width ='10%' style='font-weight: normal; border-top: 1px solid rgb(0, 0, 0); border-right: 1px solid rgb(0, 0, 0); border-bottom: 1px solid rgb(0, 0, 0); border-left: 1px solid rgb(0, 0, 0);background-color: gray;'><font size='2'><b>Amount(Rs.)</b></font></td>");
			sBuffer.append("<td  width ='10%' style='font-weight: normal; border-top: 1px solid rgb(0, 0, 0); border-right: 1px solid rgb(0, 0, 0); border-bottom: 1px solid rgb(0, 0, 0); border-left: 1px solid rgb(0, 0, 0);background-color: gray;'><font size='2'><b>Payment Type</b></font></td>");
			sBuffer.append("</tr>");
			sBuffer.append("<tr>");
			sBuffer.append("<td  width ='100%' colspan='6'></td>");
			sBuffer.append("</tr>");
			
			try 
			{
				bo = new BillEnquiryRptBO();
				String[] billno=VO.getBillNo();
				for(int i=0;i<billno.length;i++)
				{
					VO.setStrTransNo(billno[i].replace("/","#").split("#")[0]);
					VO.setStrRcptNo(billno[i].replace("/","#").split("#")[1]);
					bo.getBillContent(VO);
					WebRowSet ws = VO.getBillContent();
					ws.beforeFirst();
					if (ws != null && ws.size() != 0) 
					{
						int cnt = 0;
					//	int j=0;
						int size=ws.size();
						double subTotal=0;
						while (ws.next())
						{
							int slNo=cnt+1;
							String billNo=ws.getString(1);
							String dateRange=ws.getString(2);
							String tariffCode = ws.getString(3);
							String tariffName = ws.getString(4);
							String qty=ws.getString(5);
							String amount=ws.getString(6);
							String crNo=ws.getString(7);
							String patName=ws.getString(8);
							String service=ws.getString(9);
							String strPayType=ws.getString(10);
							/*int slenght=VO.getSerialNo().length;
							int serialNo=0;*/
							
							
							if(cnt==0)
							{
							/*	for(j;j<slenght;j++)
								{
									serialNo=VO.getSerialNo()[j];
								}*/
								
								sBuffer.append("<tr>");
								sBuffer.append("<td  width ='100%' colspan='6'></td>");
								sBuffer.append("</tr>");
								sBuffer.append("<tr>");
								sBuffer.append("<td  width ='100%' colspan='6'><font size='2'><b>CR/Name/Bill No./Service&nbsp;:&nbsp;&nbsp;"+crNo+"/"+patName+"/"+billNo+"/"+service+"</b></font></td>");
							//	sBuffer.append("<td  width ='100%' colspan='6'><font size='2'><b>CR/Name/Bill No./Service&nbsp;:&nbsp;&nbsp;"+VO.getBillDate()[i]+"</b></font></td>");
								sBuffer.append("</tr>");
								sBuffer.append("<tr>");
								sBuffer.append("<td  width ='100%' colspan='6'></td>");
								sBuffer.append("</tr>");
								if(strPayType.equals("ACCOUNT") || tariffCode.equals("ADV") || tariffCode.equals("PART"))
								{
									subTotal=Double.parseDouble(amount);
								}
							}
							else
							{
								if(strPayType.equals("ACCOUNT"))
								{
									subTotal=subTotal+Double.parseDouble(amount);
								}
								if(tariffCode.equals("ADV") || tariffCode.equals("PART"))
								{
									subTotal=(Double.parseDouble(amount))+subTotal;
								}
								
							}
							sBuffer.append("<tr>");
							sBuffer.append("<td  width ='10%'>"+slNo+"</td>");							
							sBuffer.append("<td  width ='25%'>"+dateRange+"</td>");
							sBuffer.append("<td  width ='5%'>"+tariffCode+"</td>");
							sBuffer.append("<td  width ='25%'>"+tariffName+"</td>");
							sBuffer.append("<td  width ='5%'>"+qty+"</td> ");
							sBuffer.append("<td  width ='10%'><div align='right'>"+Double.parseDouble(amount)+"</right></td> ");
							sBuffer.append("<td  width ='10%'><div align='right'>"+strPayType+"</right></td> ");
							sBuffer.append("</tr>");
							
							cnt++;
							if(slNo==size)
							{
								sBuffer.append("<tr>");
								sBuffer.append("<td  width ='10%' style='border-top: 1px solid rgb(0, 0, 0); border-bottom: 1px solid rgb(0, 0, 0);'></td> ");							
								sBuffer.append("<td  width ='25%' style='border-top: 1px solid rgb(0, 0, 0); border-bottom: 1px solid rgb(0, 0, 0);'></td> ");
								sBuffer.append("<td  width ='5%' style='border-top: 1px solid rgb(0, 0, 0); border-bottom: 1px solid rgb(0, 0, 0);'></td> ");
								sBuffer.append("<td  width ='25%' style='border-top: 1px solid rgb(0, 0, 0); border-bottom: 1px solid rgb(0, 0, 0);'></td> ");
								sBuffer.append("<td  width ='5%' style='border-top: 1px solid rgb(0, 0, 0); border-bottom: 1px solid rgb(0, 0, 0);'><b>IPD Bill Settlement:</b></td> ");
								sBuffer.append("<td  width ='10%' style='border-top: 1px solid rgb(0, 0, 0); border-bottom: 1px solid rgb(0, 0, 0);'><div align='right'><b>"+subTotal+"</b></div></td> ");
								sBuffer.append("<td  width ='10%' style='border-top: 1px solid rgb(0, 0, 0); border-bottom: 1px solid rgb(0, 0, 0);'></td> ");
								sBuffer.append("</tr>");
							}
						}
					}
					else
						sBuffer.append("<tr><div align='center'><font color='red' size='2' face=\"Verdana, Arial, Helvetica, sans-serif\">No Bill Details Found!</font></div></tr>");
				}				
			} catch (SQLException e) {
				e.printStackTrace();
				VO.setStrMsgString("BillEnquiryRptHLP.getBillContent() --> "+ e.getMessage());
				VO.setStrMsgType("1");
				throw new Exception("BillEnquiryRptHLP.getBillContent() -->"+ e.getMessage());
			}
		
		sBuffer.append("</table>");
		/*sBuffer	.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
		sBuffer.append("<tr>");
		sBuffer.append("<td colspan='2' align='center'>");
		sBuffer.append("<img src='../../hisglobal/images/btn-generate.png' style='cursor:hand;cursor:pointer;'  title ='generate ' onClick ='generate();' />");
		sBuffer.append("<img src='../../hisglobal/images/btn-clr.png'  style='cursor:hand;cursor:pointer;'  title ='clear ' onClick ='clearPage();' />");
		sBuffer.append("<img src='../../hisglobal/images/btn-ccl.png' style='cursor:hand;cursor:pointer;'  title ='cancel' onClick ='cancelPage();' />");
		sBuffer.append("</td></tr>");
		sBuffer.append("</table>");*/
		return sBuffer.toString();
	}
	
	public static String getBillHTML(BillEnquiryRptVO VO) throws Exception {

		WebRowSet ws = VO.getBillDetailsHtmlWb();
		String strTempStr="viewReport";

		StringBuffer sBuffer = new StringBuffer("");
		
		ws.beforeFirst();
		
		sBuffer.append("<table class ='TABLEWIDTH' style='width:70vw;' align ='center' cellspacing ='2px' > ");

		if (ws != null && ws.size() != 0) 
		{		
			sBuffer.append("<tr> ");
			sBuffer.append("<td class='multiLabel' width ='5%'>Sl. No.</td> ");
			sBuffer.append("<td class='multiLabel' width ='20%'>Date Range</td> ");
			sBuffer.append("<td class='multiLabel' width ='20%'>Tariff Code</td> ");
			sBuffer.append(" <td CLASS='multiLabel' width ='20%'>Tariff Name</td> ");
			sBuffer.append(" <td CLASS='multiLabel' width ='20%'>Quantity</td> ");
			sBuffer.append(" <td CLASS='multiLabel' width ='15%'>Amount</td> ");
			sBuffer.append(" <td CLASS='multiLabel' width ='15%'>Payment Type</td> ");
			sBuffer.append(" </tr>");
			
			try 
			{
                double totalAmt=0; 
				int i = 0;
				while (ws.next())
				{
					String strBillNo = ws.getString(1);
					String strBillDate=ws.getString(2);
					String strTariffCode=ws.getString(3);
					String strTariffName=ws.getString(4);
					String strBillQty=ws.getString(5);
					String strBillAmt = ws.getString(6);
					String strPayType=ws.getString(7);
					
					String strFiletype="pdf";
					totalAmt=totalAmt+ Double.parseDouble(strBillAmt);
					
					int slNo=i+1;
					
					if (strBillDate == null)
						strBillDate = "-";
					
					if (strBillNo == null)
						strBillNo = "-";
					
					if (strBillAmt == null)
						strBillAmt = "-";
		      
					sBuffer.append("<tr class ='multiControl'> ");
					sBuffer.append(" <td width ='5%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + slNo + "</font></td> ");
					sBuffer.append(" <td width ='30%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"  + strBillDate +"</font></td> ");
					sBuffer.append(" <td width ='5%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strTariffCode + "</font></td> ");
					sBuffer.append(" <td width ='20%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strTariffName + "</font></td> ");
					sBuffer.append(" <td width ='10%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strBillQty + "</font></td> ");
					sBuffer.append(" <td width ='15%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strBillAmt + "</font></td> ");
					sBuffer.append(" <td width ='15%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strPayType + "</font></td> ");
					sBuffer.append("</td>");
					sBuffer.append(" </tr> ");
							
					i++;					
				}
				
				/*sBuffer.append("<tr class ='multiControl'> ");
				sBuffer.append("<td class='multiLabel' width ='5%'></td> ");
				sBuffer.append("<td class='multiLabel' width ='30%'></td> ");
				sBuffer.append("<td class='multiLabel' width ='5%'></td> ");
				sBuffer.append("<td class='multiLabel' width ='20%'></td> ");
				sBuffer.append("<td class='multiLabel' width ='10%' align ='right'> </td> ");
				sBuffer.append(" <td width ='15%' ><b><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\"></font></b></td> ");
		//		sBuffer.append("<td class='multiLabel' width ='20%' align ='right'>Grand Total(Rs.) </td> ");
		//		sBuffer.append(" <td width ='15%' ><b><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + totalAmt + "</font></b></td> ");
				sBuffer.append("<td class='multiLabel' width ='15%'></td> ");
				sBuffer.append(" </tr> ");
				*/
			} catch (SQLException e) 
			{
				VO.setStrMsgString("BillEnquiryRptHLP.getBillHTML() --> "+ e.getMessage());
				VO.setStrMsgType("1");
				
				throw new Exception("BillEnquiryRptHLP.getBillDtls() -->"+ e.getMessage());
			}
		} 
		else{
			sBuffer.append("<tr><div align=\"center\"><font color=\"red\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">No Bill Details Found!</font></div></tr>");
				
		}
		sBuffer.append("</table > ");
		
		        
		sBuffer.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px' > ");
		sBuffer.append("<tr class='FOOTER'>");
		sBuffer.append("<td  colspan='2'> </td>\n");
		sBuffer.append("</tr>");
		sBuffer.append("</table>");
		
		sBuffer.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
		sBuffer.append("<tr>");
		sBuffer.append("<td colspan='2' align='center'>");
		sBuffer.append("<img src='../../hisglobal/images/close_tab.png' style='cursor:hand;cursor:pointer;'  title ='Close' onClick =\"hide_popup('viewReport');\" />");
		sBuffer.append("</td></tr>");
		sBuffer.append("</table>");
		
		return sBuffer.toString();
	}	
	
	public static String getBillHTML_BS(BillEnquiryRptVO VO) throws Exception {

		WebRowSet ws = VO.getBillDetailsHtmlWb();
		String strTempStr="viewReport";

		StringBuffer sBuffer = new StringBuffer("");
		
		ws.beforeFirst();
		
		sBuffer.append("<table class ='table'> ");

		if (ws != null && ws.size() != 0)
		{	
			sBuffer.append("<thead> ");
			sBuffer.append("<tr> ");
			sBuffer.append("<th>Sl. No.</th> ");
			sBuffer.append("<th>Date Range</th> ");
			sBuffer.append("<th>Tariff Code</th> ");
			sBuffer.append("<th>Tariff Name</th> ");
			sBuffer.append("<th>Quantity</th> ");
			sBuffer.append("<th>Amount</th> ");
			sBuffer.append("<th>Payment Type</th> ");
			sBuffer.append("</tr>");
			sBuffer.append("</thead> ");
			sBuffer.append("<tbody> ");
			try 
			{
                double totalAmt=0; 
				int i = 0;
				while (ws.next())
				{
					String strBillNo = ws.getString(1);
					String strBillDate=ws.getString(2);
					String strTariffCode=ws.getString(3);
					String strTariffName=ws.getString(4);
					String strBillQty=ws.getString(5);
					String strBillAmt = ws.getString(6);
					String strPayType=ws.getString(7);
					
					String strFiletype="pdf";
					totalAmt=totalAmt+ Double.parseDouble(strBillAmt);
					
					int slNo=i+1;
					
					if (strBillDate == null)
						strBillDate = "-";
					
					if (strBillNo == null)
						strBillNo = "-";
					
					if (strBillAmt == null)
						strBillAmt = "-";
		      
					sBuffer.append("<tr> ");
					sBuffer.append("<td>" + slNo + "</font></td> ");
					sBuffer.append("<td>"  + strBillDate +"</font></td> ");
					sBuffer.append("<td>" + strTariffCode + "</font></td> ");
					sBuffer.append("<td>" + strTariffName + "</font></td> ");
					sBuffer.append("<td>" + strBillQty + "</font></td> ");
					sBuffer.append("<td>" + strBillAmt + "</font></td> ");
					sBuffer.append("<td>" + strPayType + "</font></td> ");
					sBuffer.append(" </tr> ");
							
					i++;					
				}
				
				/*sBuffer.append("<tr class ='multiControl'> ");
				sBuffer.append("<td class='multiLabel' width ='5%'></td> ");
				sBuffer.append("<td class='multiLabel' width ='30%'></td> ");
				sBuffer.append("<td class='multiLabel' width ='5%'></td> ");
				sBuffer.append("<td class='multiLabel' width ='20%'></td> ");
				sBuffer.append("<td class='multiLabel' width ='10%' align ='right'> </td> ");
				sBuffer.append(" <td width ='15%' ><b><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\"></font></b></td> ");
		//		sBuffer.append("<td class='multiLabel' width ='20%' align ='right'>Grand Total(Rs.) </td> ");
		//		sBuffer.append(" <td width ='15%' ><b><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + totalAmt + "</font></b></td> ");
				sBuffer.append("<td class='multiLabel' width ='15%'></td> ");
				sBuffer.append(" </tr> ");
				*/
			} catch (SQLException e) 
			{
				VO.setStrMsgString("BillEnquiryRptHLP.getBillHTML() --> "+ e.getMessage());
				VO.setStrMsgType("1");
				
				throw new Exception("BillEnquiryRptHLP.getBillDtls() -->"+ e.getMessage());
			}
		} 
		else{
			sBuffer.append("<div>No Bill Details Found!</div>");
				
		}
		sBuffer.append("</tbody > ");
		sBuffer.append("</table > ");
		
		        
		/*sBuffer.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px' > ");
		sBuffer.append("<tr class='FOOTER'>");
		sBuffer.append("<td  colspan='2'> </td>\n");
		sBuffer.append("</tr>");
		sBuffer.append("</table>");
		
		sBuffer.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
		sBuffer.append("<tr>");
		sBuffer.append("<td colspan='2' align='center'>");
		sBuffer.append("<img src='../../hisglobal/images/close_tab.png' style='cursor:hand;cursor:pointer;'  title ='Close' onClick =\"hide_popup('viewReport');\" />");
		sBuffer.append("</td></tr>");
		sBuffer.append("</table>");*/
		
		return sBuffer.toString();
	}
	
}
