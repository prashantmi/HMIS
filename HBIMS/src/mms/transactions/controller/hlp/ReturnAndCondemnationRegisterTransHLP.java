package mms.transactions.controller.hlp;


import hisglobal.utility.HisUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javax.sql.rowset.WebRowSet;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import sun.org.mozilla.javascript.internal.regexp.SubString;
import mms.transactions.bo.ReturnAndCondemnationRegisterTransBO;
import mms.transactions.controller.fb.ReturnAndCondemnationRegisterTransFB;
import mms.transactions.vo.ReturnAndCondemnationRegisterTransVO;


public class ReturnAndCondemnationRegisterTransHLP {
	public static String getReturnOrCondemnDrugListHlp(ReturnAndCondemnationRegisterTransVO vo) {
		StringBuffer br = new StringBuffer();
		int i = 0;
		WebRowSet wb = vo.getWsNOSQItemDetail();					
		try
		{
			
			if (wb != null && wb.size() > 0) 
			{
				
					br.append("<table class='TABLEWIDTH' align='center' border='0' bgcolor='#6097BC' cellspacing ='1px' cellpadding='1px'>");
					br.append("<tr class='TITLE'>");
					br.append("<td colspan='8'><div id='' align='left'>Order Details</div></td></tr>");
					br.append("<tr>");
					br.append("<td WIDTH='5%'   colspan='1' class='multiRPTLabel'>#</td>");
					br.append("<td WIDTH='25%'  colspan='1' class='multiRPTLabel'>Drug Name</td>");
					br.append("<td WIDTH='5%'  colspan='1' class='multiRPTLabel'>RC S.No.</td>");
					br.append("<td WIDTH='10%'  colspan='1' class='multiRPTLabel'>Batch No</td>");
					br.append("<td WIDTH='10%'  colspan='1' class='multiRPTLabel'>Avl Qty</td>");	
					br.append("<td WIDTH='10%'  colspan='1' class='multiRPTLabel'>Return/Condemnation Qty</td>");
					br.append("<td WIDTH='10%'  colspan='1' class='multiRPTLabel'>Action</td>");
					br.append("<td WIDTH='10%'  colspan='1' class='multiRPTLabel'>Order No.</td>");
					br.append("<td WIDTH='10%'  colspan='1' class='multiRPTLabel'>Order Date</td>");
					br.append("</tr> ");
					br.append("</table> ");
					br.append("<table class='TABLEWIDTH' align='center' border='0' bgcolor='#6097BC' cellspacing ='1px' cellpadding='1px' >");
					while (wb.next()) 
					{
					  /*
						 1 ITEM_NAME
                    	 2 HSTNUM_ITEMBRAND_ID
                    	 3 HSTSTR_BATCH_SL_NO
                    	 4 DECODE(HSTNUM_NEW_ORDER_TYPE,1,'Replacement',2,'Condemnation')
                    	 5 HSTNUM_NEW_ORDER_TYPE
                    	 6 HSTNUM_NEW_ORDER_NO
                    	 7 HSTDT_NEW_ORDER_DATE
                    	 8 DECODE(HSTNUM_DRUG_TYPE_FLAG,1,'NOSQ',2,'Rejected',3,'Expired')
                    	 9 STORE_NAME
                    	10 HSTNUM_STORE_ID
                    	11 AVL_QTY
                    	12 HSTNUM_NEW_ORDER_TYPE
                    	13 HSTNUM_DRUG_TYPE_FLAG
                    	14 HIDDEN_VALUE   (HSTNUM_ITEM_ID || ''^'' || HSTNUM_ITEMBRAND_ID || ''^'' || HSTSTR_BATCH_SL_NO || ''^'' || 
			                    HSTNUM_OLD_PO_NO || ''^'' || HSTNUM_NEW_ORDER_NO || ''^'' || HSTNUM_SUPPLIER_ID || ''^'' || 
			                    HSTNUM_DRUG_TYPE_FLAG||''^''||HSTNUM_NEW_ORDER_TYPE)
			            15 HSTNUM_RET_CONDEMN_QTY        
					  */
						
						
					String strHiddenValue = wb.getString(14)+"^"+wb.getString(11);
						
					br.append("<TR>");										
					br.append("<td width='5%' class='multiRPTLabel' id='check"+i+"'><input type='checkbox' title='View' name='strNosqDrugs' value='"+strHiddenValue+ "' id='strNosqDrugs' onclick='onCheckRadioButton(\""+strHiddenValue+"\",\""+ i + "\");'></td>");
					br.append("</td>");						
					br.append("<TD WIDTH='25%'  colspan='1' CLASS='multiRPTControl'>"+ wb.getString(1) + "</TD>");
					br.append("<TD WIDTH='5%'  colspan='1' CLASS='multiRPTControl'>"+ wb.getString(16) + "</TD>");
					br.append("<TD WIDTH='10%'  colspan='1' CLASS='multiRPTControl'>"+ wb.getString(3) + "</TD>");
					br.append("<TD WIDTH='10%'  colspan='1' CLASS='multiRPTControl'>"+ wb.getString(11) + "</TD>");
					br.append("<TD WIDTH='10%'  colspan='1' CLASS='multiRPTControl'>"+ wb.getString(15) + "</TD>");
					br.append("<TD WIDTH='10%'  colspan='1' CLASS='multiRPTControl'>"+ wb.getString(4) + "</TD>");
					br.append("<TD WIDTH='10%'  colspan='1' CLASS='multiRPTControl'>"+ wb.getString(6) + "</TD>");
					br.append("<TD WIDTH='10%'  colspan='1' CLASS='multiRPTControl'>"+ wb.getString(7) + "</TD>");
					
					br.append("</TR>");
					i++;
				}  					
					br.append("</table> ");

						

			}
			if (wb.size() == 0) 
			{
				br.append("<table class='TABLEWIDTH' align='center' border='0' bgcolor='#6097BC' cellspacing ='1px' cellpadding='1px'>");
				br.append("<tr class='TITLE'>");
				br.append("<td colspan='8'><div id='' align='left'>Drug List</div></td></tr>");
				br.append("<tr>");
				br.append("<td WIDTH='5%'   colspan='1' class='multiRPTLabel'>#</td>");
				br.append("<td WIDTH='25%'  colspan='1' class='multiRPTLabel'>Drug Name</td>");
				br.append("<td WIDTH='5%'  colspan='1' class='multiRPTLabel'>RC S.No.</td>");
				br.append("<td WIDTH='10%'  colspan='1' class='multiRPTLabel'>Batch No</td>");
				br.append("<td WIDTH='10%'  colspan='1' class='multiRPTLabel'>Avl Qty</td>");	
				br.append("<td WIDTH='10%'  colspan='1' class='multiRPTLabel'>Return/Condemnation Qty</td>");
				br.append("<td WIDTH='10%'  colspan='1' class='multiRPTLabel'>Action</td>");
				br.append("<td WIDTH='10%'  colspan='1' class='multiRPTLabel'>Order No.</td>");
				br.append("<td WIDTH='10%'  colspan='1' class='multiRPTLabel'>Order Date</td>");
				br.append("</tr> ");
				br.append("</table> ");
				br.append("<table class='TABLEWIDTH' align='center' border='0' cellspacing ='1px' cellpadding='1px' >");
				br.append("<TR>");
				br.append("<td class='multiRPTControl' colspan='7><b><div id='id' align='center' color='Red'> drug list not found</div></b></td>");
				br.append("</TR>");
				br.append("</table> ");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return br.toString();
	}

	public static String getvoucherPrintDetails(ReturnAndCondemnationRegisterTransVO vo) 
	{
		double sum1=0;
		StringBuffer sb = new StringBuffer("");
		String strReceivedQty = "";
		String strOrderNo="" , strOrderDate="";
		HisUtil util = new HisUtil("mms", "getvoucherPrintDetails");
		WebRowSet ws = null ;
		String strTableWidth = "700";
		String Remarks="";
		int sno=1;
		try 
		{
			ResourceBundle res = mms.qryHandler_mms.res;
			if(res == null) 
			{
				res = ResourceBundle.getBundle("hisglobal.hisconfig.hisProperties");
				mms.qryHandler_mms.res = res;
			}
			ws	=	vo.getWsNOSQItemDetail();
			if (ws != null && ws.size() > 0) 
			{
				ws.next();
			sb.append("<table align='center' width='").append(strTableWidth).append("' border='0' cellspacing='0' cellpadding='0' height='69'> ");
			sb.append("<tr><td width='8%'>&nbsp;</td> ");
			sb.append("<td width='82%' align='center'> <b><font face='Verdana, Arial, Helvetica, sans-serif' size='4'> ");
			sb.append(res.getString("REPORT_TITLE"));
			sb.append("</font></b></td> <td width='10%'>&nbsp;</td> ");
			sb.append("</tr> ");
			sb.append("<tr> ");
			sb.append("<td width='8%'>&nbsp;</td> ");
			sb.append("<td width='82%' align='center'> <b><font face='Verdana, Arial, Helvetica, sans-serif' size='4'> ");
			sb.append(res.getString("FULL_TITLE"));
			sb.append("</font></b></td> <td width='10%'>&nbsp;</td> ");
			sb.append("</tr> ");
			sb.append("<tr> ");
			sb.append("<td width='8%'>&nbsp;</td> ");
			sb.append("<td width='82%' align='center'> <b><font face='Verdana, Arial, Helvetica, sans-serif' size='4'> ");
			sb.append(res.getString("CITY"));
			sb.append("</font></b></td><td width='10%'>&nbsp; ");
			sb.append("</td> ");
			sb.append("</tr> ");
			sb.append("<tr> ");
			sb.append("<td width='8%'>&nbsp;</td> ");
			if(ws.getString(16).equals("2"))
			{
			sb.append("<td width='82%' align='center'> <b><font face='Verdana, Arial, Helvetica, sans-serif' size='2'><u>Condemnation voucher</u>");
			}
			else
			{
				sb.append("<td width='82%' align='center'> <b><font face='Verdana, Arial, Helvetica, sans-serif' size='2'><u>Supplier Debit Note</u>");
			}
			
			sb.append("</font></b></td><td width='10%'>&nbsp; ");
			sb.append("</td> ");
			sb.append("</tr> ");
			sb.append("</table> ");		
			
			sb.append("<TABLE ALIGN='CENTER' cellspacing='1px' cellpadding='1px' border='0' WIDTH='").append(strTableWidth).append(
			"'></table> ");
			sb.append("<div id='saveId' style='display : block'> <div id='printid1' class='hidecontrol' align='right'>");
			sb.append("<img style='cursor: pointer; ' title='Print Page'  ");
			sb.append(" src='../../hisglobal/images/printer_symbol.gif' onClick='printDataForTransfer();' /> <img style='cursor: pointer; ' title='Cancel Process'  ");
			sb.append(" src='../../hisglobal/images/stop.png' onClick='hideTransferPopup();' /> </div></div>");
			sb.append(" </td> ");
			sb.append(" </tr> ");
			sb.append(" </table> ");
			
            sb.append("<table width='").append(strTableWidth).append("' align='center' cellpadding='1px' cellspacing='1px'> ");

            /*
             1 STORE_NAME,
             2 HSTNUM_STORE_ID
			 3 ITEM_NAME 
             4 BATCH_NO
             5 GNUM_HOSPITAL_CODE
             6 SUPPLIER_NAME
             7 Exp_Date
             8 RET_CONDEMN_QTY
             9 ACTION
             10 ORDER_NO
             11 ORDER_DATE
             12 RET_CONDEMN_DATE
             13 RETURN_TO
             14 CONDEMNATION_TYPE
             15 CONDEMN_REMARKS
             16 HSTNUM_RET_CONDEMN_FLAG
            */
            
            
			}
			ws.beforeFirst();
            if (ws != null && ws.size() > 0) 
			{

				/*while (ws.next()) 
				{
					 strTransferNo    = ws.getString(1);
					 strTransferDate  = ws.getString(2);
					 strTransferTo    = ws.getString(3);
					 strTransferFrom  = ws.getString(4);
					 //strRemarks       = ws.getString(21);
					 //strReceivedBy    = ws.getString(22);					 
					 strOrderNo       = ws.getString("ORDER_NO");
					 strOrderDate     = ws.getString("ORDER_DATE");
				     strDemandNo      = ws.getString("DEMAND_REQ_NO");
					 strDemandDate    = ws.getString("DEMAND_DATE");
					 strTransferredBy = ws.getString("TRANSFER_BY"); 
					 
	                          
					
				}
				ws.beforeFirst();*/
				
            	if(ws.next())
            	{
            		 Remarks=ws.getString(23);
            		if(ws.getString(16).equals("1"))
            		{
            		 sb.append("<tr > ");
            		 sb.append("<tdwidth='25%' align='LEFT' colspan='4'><font face='Verdana, Arial, Helvetica, sans-serif' >").append(
								util.appendSpace("<b>Supplier Name</b>", 15,0)).append(" : "+ws.getString(6)+"</font></td> ");
					 sb.append("</tr> ");
					
					 sb.append("<tr colspan='4'> ");
            		 sb.append("<td width='25%' align='LEFT' colspan='4'><font face='Verdana, Arial, Helvetica, sans-serif' >").append(
								util.appendSpace("<b>Supplier Address</b>", 15,0)).append(" : "+ws.getString(22)+"</font></td> ");
					 sb.append("</tr> ");
            		}
					 
					sb.append("<tr> ");
					/*sb.append("<td width='25%' align='LEFT'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>").append(
							util.appendSpace("Store Name", 15,0)).append(" : "+ws.getString(1)+"</b></font></td> ");*/
					
					if(ws.getString(16).equals("1"))
					{
						sb.append("<td width='25%' align='LEFT'><font face='Verdana, Arial, Helvetica, sans-serif' >").append(
							util.appendSpace("<b>Store Name</b>", 15,0)).append(" : "+ws.getString(1)+"</font></td> ");
						 sb.append("<td width='25%' align='LEFT'><font face='Verdana, Arial, Helvetica, sans-serif' >").append(
									util.appendSpace("<b>Order Date</b>", 15,0)).append(" : "+ws.getString(11)+"</font></td> ");
					}
					else
					{
						/*sb.append("<td width='25%' align='LEFT'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>").append(
								util.appendSpace("Condemnation Type", 15,0)).append(" : "+ws.getString(14)+"</b></font></td> ");						
						*/
						sb.append("<td width='25%' align='LEFT'><font face='Verdana, Arial, Helvetica, sans-serif' >").append(
								util.appendSpace("<b>Store Name</b>", 15,0)).append(" : "+ws.getString(1)+"</font></td> ");
							 sb.append("<td width='25%' align='LEFT'><font face='Verdana, Arial, Helvetica, sans-serif' >").append(
										util.appendSpace("<b>Order Date</b>", 15,0)).append(" : "+ws.getString(11)+"</font></td> ");
					}
					
				     sb.append("</tr> ");
				     if(ws.getString(16).equals("1"))
						{
				     
				     sb.append("<tr> ");
				     sb.append("<td width='25%' align='LEFT'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>").append(
								util.appendSpace("Debit Note No", 15,0)+"</b>").append(" : "+ws.getString(27)+"</font></td> ");
				     sb.append("</tr> ");
						}
				     else
						{
				     
				     sb.append("<tr> ");
				     sb.append("<td width='25%' align='LEFT'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>").append(
								util.appendSpace("Condemnation voucher No.", 15,0)+"</b>").append(" : "+ws.getString(27)+"</font></td> ");
				     sb.append("</tr> ");
						}
						
					
					 //sb.append("<tr> ");
					 //sb.append("<td width='25%' align='LEFT'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>").append(
					//			util.appendSpace("Order No", 15,0)).append(" : "+ws.getString(10)+"</b></font></td> ");
					 
					
					 //sb.append("</tr> ");
					
					/*sb.append("<td width='25%' align='right'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>").append(
							util.appendSpace("Transfer Date", 15,0)).append(":</b></font></td> ");
					sb.append("<td width='25%' ><font face='Verdana, Arial, Helvetica, sans-serif' ><b>").append(strTransferDate).append(
							"</b></font></td> ");*/
					//sb.append("</tr> ");
            	}	
							
			
				sb.append("<table width='").append(strTableWidth).append("' align='center' cellpadding='1px' cellspacing='1px'> ");
				/*sb.append("<tr>");		
				sb.append("<td colspan='8' align='center'><hr size='2' width='100%'></td>");
				sb.append("</tr>");*/
				
				sb.append("<tr bgcolor='#cdc9c9'> ");
				sb.append("<td  width='5%' align='left'><font face='Verdana, Arial, Helvetica, sans-serif' ><b align='left'>S.No.</b></font> ");
				sb.append("</td>");
				sb.append("<td  width='25%' align='left'><font face='Verdana, Arial, Helvetica, sans-serif' ><b align='left'>Item Name</b></font> ");
				sb.append("</td>");
				sb.append("<td  width='5%' align='left'><font face='Verdana, Arial, Helvetica, sans-serif' ><b align='left'>RC S.No.</b></font> ");
				sb.append("</td>");
				
				sb.append("<td width='8%' align='left'><font face='Verdana, Arial, Helvetica, sans-serif' ><b align='left'>Batch No.</b></font> ");
				sb.append("</td> ");
				//sb.append("<td align='center' width='5%'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Return/Condemn Qty</b></font> ");
				sb.append("</td> ");
				sb.append("<td align='center' width='5%'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Invoice No</b></font> ");
				sb.append("</td> ");
				
				/*if(ws.getString(16).equals("2"))
				{
					sb.append("<td align='center' width='12%'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Condemnation Type</b></font> ");
					sb.append("</td> ");
					sb.append("<td align='center' width='10%'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Condemn Date</b></font> ");
					sb.append("</td> ");
				}*/
				//else
				//{
					//sb.append("<td align='center' width='5%'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Return Date</b></font> ");
				sb.append("<td align='center' width='10%'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Expiry</b></font> ");
					sb.append("<td align='center' width='5%'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Rate/Unit</b></font> ");
					sb.append("<td align='center' width='5%'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Tax</b></font> ");
					sb.append("<td align='center' width='5%'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Rate(With Tax)</b></font> ");
					sb.append("<td align='center' width='5%'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Qty.</b></font> ");
					sb.append("<td align='center' width='5%'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Total</b></font> ");
					sb.append("<td align='center' width='27%'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Remarks</b></font> ");
					sb.append("</td> ");
				//}
				
				sb.append("</tr> ");

				sb.append("<tr>");		
				sb.append("<td colspan='12' align='center'><hr size='2' width='100%'></td>");
				sb.append("</tr>");
						
				ws.beforeFirst();	
				while (ws.next()) 
				{
					sb.append("<tr> ");
					sb.append("<td align='left' width='5%'><font face='Verdana, Arial, Helvetica, sans-serif' >");
					sb.append(sno++);
					sb.append("</font></td> ");
					sb.append("<td align='left' width='25%'><font face='Verdana, Arial, Helvetica, sans-serif' >");
					sb.append(ws.getString(3));
					sb.append("</font></td> ");
					sb.append("<td align='left' width='5%'><font face='Verdana, Arial, Helvetica, sans-serif' >");
					sb.append(ws.getString(28));
					sb.append("</font></td> ");
					sb.append("<td align='left' width='8%'><font face='Verdana, Arial, Helvetica, sans-serif' >");
					sb.append(ws.getString(4));
					sb.append("</font></td> ");
					/*sb.append("<td align='center' width='5%'><font face='Verdana, Arial, Helvetica, sans-serif' >");
					sb.append(ws.getString(8));
					sb.append("</font></td> ");*/
					sb.append("<td align='center' width='5%'><font face='Verdana, Arial, Helvetica, sans-serif' >");
					sb.append(ws.getString(21));
					sb.append("</font></td> ");
					
					//if(ws.getString(16).equals("2"))
					//{
					//	sb.append("<td align='center' width='12%'><font face='Verdana, Arial, Helvetica, sans-serif' >");
					//	sb.append(ws.getString(14));
				//		sb.append("</font></td> ");
				//	}
					
					//sb.append("<td align='center' width='5%'><font face='Verdana, Arial, Helvetica, sans-serif' >");
					//sb.append(ws.getString(12));
					//sb.append("</font></td> ");
					sb.append("<td align='center' width='10%'><font face='Verdana, Arial, Helvetica, sans-serif' >");
					sb.append(ws.getString(7));
					sb.append("</font></td> ");
					
					sb.append("<td align='center' width='5%'><font face='Verdana, Arial, Helvetica, sans-serif' >");
					sb.append(ws.getString(17));
					sb.append("</font></td> ");
					
					sb.append("<td align='center' width='5%'><font face='Verdana, Arial, Helvetica, sans-serif' >");
					sb.append(ws.getString(24));
					sb.append("</font></td> ");
					
					sb.append("<td align='center' width='5%'><font face='Verdana, Arial, Helvetica, sans-serif' >");
					sb.append(ws.getString(25));
					sb.append("</font></td> ");
					
					sb.append("<td align='center' width='5%'><font face='Verdana, Arial, Helvetica, sans-serif' >");
					sb.append(ws.getString(18));
					sb.append("</font></td> ");
					
					sb.append("<td align='center' width='5%'><font face='Verdana, Arial, Helvetica, sans-serif' >");
					sb.append(ws.getString(19));
					sum1=sum1+Double.parseDouble(ws.getString(19));
					sb.append("</font></td> ");
					
					sb.append("<td align='center' width='27%'><font face='Verdana, Arial, Helvetica, sans-serif' >");
					sb.append(ws.getString(26));
					sb.append("</font></td> ");
					
					sb.append("</tr> ");
					
					//sb.append("<tr><br/><br/>");
					//sb.append("<td colspan='8' align='left'><font face='Verdana, Arial, Helvetica, sans-serif' ><br/><b>Remarks : "+ws.getString(15)+"</b><br/><br/></font></td> ");
					//sb.append("</tr> ");
								
				 }
				sb.append("<tr><br/>");
				sb.append("<td colspan='15' align='right'><font face='Verdana, Arial, Helvetica, sans-serif' ><br/><b>Return Total: "+HisUtil.getAmountWithDecimal(sum1,2)+"</font></td> ");
				sb.append("</tr> ");
				sb.append("<tr>");
				HisUtil his= new HisUtil("", "");
				sb.append("<td colspan='15' align='left'><font size='2' face='Verdana, Arial, Helvetica, sans-serif' ><br/><b>Return Amount:</b> "+his.getAmountStr( String.valueOf(HisUtil.getAmountWithDecimal(sum1,0)))+"</b></font></td> ");
				sb.append("</tr> ");
				sb.append("<tr>");
				//HisUtil his= new HisUtil("", "");
				sb.append("<td colspan='15' align='left'><font size='2' face='Verdana, Arial, Helvetica, sans-serif' ><br/><b>Remarks:</b> "+Remarks+"</b></font></td> ");
				sb.append("</tr> ");
			} 
			else 
			{

				sb.append("<tr> ");
				sb.append("<td colspan='11' align='center'><font face='Verdana, Arial, Helvetica, sans-serif' ><br/>Details Not Available</font></td> ");
				sb.append("</tr> ");

			}
            
			
			sb.append("</table> ");

		} catch (Exception e) {

			e.printStackTrace();
		}

		return sb.toString();
	}	
}