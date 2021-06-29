package mms.transactions.controller.hlp;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.sql.SQLException;

import javax.sql.rowset.WebRowSet;

import mms.MmsConfigUtil;
import mms.transactions.bo.IssueDeskTransBO;
import mms.transactions.bo.OfflineIssueIndentTransBO;
import mms.transactions.vo.IssueDeskTransVO;
import mms.transactions.vo.OfflineIssueIndentTransVO;
import mms.transactions.vo.QcDetailOnlineTransVO;

public class QcDetailOnlineTransHLP
{
	static int i = 0;
	
	/**
	 * Gets the issue dtls init view.
	 * 
	 * @param formBean
	 *            the form bean
	 * 
	 * @return the issue dtls init view
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public static String getPrintSampleSentLabel(String strHiddenValue) throws Exception 
	{
		StringBuffer sb = new StringBuffer("");
		
 		String strTableWidth = "700";
 		String strResponse[] = null;
		try 
		{
			/*
			1- Item Id
			2- Lab Sent Date
			3- Sent Store Name
			4- Generic Name
			5-Brand Name
			6- Batch
			7-Exp Date
			8-Transfer Qty
			9-Store Id Sent
			10-Item Id
			11-Item Brand ID
			12-Rate With Unit
			13-Rate Base value
			14-Consumed Qty
			15=Consumed Qty Wit Unit
			16-Qty Base Value
			17-Item Sl No
			18-Item Sl No
			19-Catg Code
			20- Lab Send No
			21-Lab Name
			22-CTR Number
			23-Net Cost
			24-PO No
			25-PO Date
			26-Mfd Date
			27-Is Send Decode Value
            28 Lab No
            29 Manufacter By
            30. ITEM_STATUS
            31. REPORT_DATE
            32. GNUM_HOSPITAL_CODE            
            33.	HSTNUM_STOCK_STATUS_CODE
            34.	HSTNUM_INHAND_QTY, 
            35.	HSTNUM_INHAND_QTY_UNITID, 
            36.	HSTNUM_SUPPLIER_ID, 
            37.	HSTNUM_MANUFACTURER_ID, 
            38.	HSTSTR_SAMPLE_CODE_NO, 
            39.	HSTSTR_CTR_NO, 
            40.	GSTR_SEND_REAMRKS            
            41.	HSTNUM_RATE, 
			42.	HSTNUM_RATE_UNITID
			43.CTR No
        */
			    strResponse = strHiddenValue.replace("^", "#").split("#");
		        System.out.println("strResponse"+strResponse);
			
	    		sb.append("<table border='0' width='").append(strTableWidth).append("' align='center'> ");
				sb.append("<tr> ");
				sb.append("<td align='right'>");
	 			sb.append("<div id='saveId' style='display : block'> <div id='printid1' class='hidecontrol' align='right'>");
				sb.append("<img style='cursor: pointer; ' title='Print Page'  ");
				sb.append(" src='../../hisglobal/images/printer_symbol.gif' onClick='printData(1);' /> <img style='cursor: pointer; ' title='Cancel Process'  ");
				sb.append(" src='../../hisglobal/images/stop.png' onClick='hidePopup(1);' /> </div></div>");
				sb.append(" </td> ");
				sb.append(" </tr> ");
				sb.append(" </table> ");
				sb.append(" <br> ");
			
				sb.append("<table width='").append(strTableWidth).append("' align='center' cellpadding='1px' cellspacing='1px'> ");
				sb.append("<tr> ");
				sb.append("<td align='center' width='50%' bgcolor='#cdc9c9'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>DECODING OF SECRET CODE NO::</b>"+strResponse[19]+"</font> ");
				sb.append("</td>");
				sb.append("<td align='center' width='50%' bgcolor='#cdc9c9'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>CTR NO::</b>"+strResponse[42]+"</font> ");
				sb.append("</td>");
				sb.append("</tr> ");
 	            sb.append("</table> ");
				
				sb.append("<table width='").append(strTableWidth).append("' align='center' cellpadding='1px' cellspacing='1px'> ");
				sb.append("<tr> ");
				sb.append("<td  colspan='4' bgcolor='#cdc9c9'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Drug Name::  "+strResponse[4]+"</b></font><b></td>");
				sb.append("</tr> ");				
				sb.append(" </table> ");
						
				sb.append("<table width='").append(strTableWidth).append("' align='center' cellpadding='1px' cellspacing='1px'> ");
				sb.append("<tr> ");
				sb.append("<td align='center' width='25%' bgcolor='#cdc9c9'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Batch No</b></font> ");
				sb.append("</td> ");				
				sb.append("<td align='center' width='25%'><font face='Verdana, Arial, Helvetica, sans-serif' >"+strResponse[5]+"</font> ");
				sb.append("</td> ");
				
				sb.append("<td align='center' width='25%'bgcolor='#cdc9c9'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Mfg. Date</b></font> ");
				sb.append("</td> ");				
				sb.append("<td align='center' width='25%'><font face='Verdana, Arial, Helvetica, sans-serif' >"+strResponse[25]+"</font> ");
				sb.append("</td> ");
				sb.append("</tr> ");
				
				sb.append("<tr> ");
				sb.append("<td align='center' width='25%' bgcolor='#cdc9c9'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>M.L. No.</b></font> ");
				sb.append("</td> ");				
				sb.append("<td align='center' width='25%'><font face='Verdana, Arial, Helvetica, sans-serif' >"+strResponse[27]+"</font> ");
				sb.append("</td> ");
				
				sb.append("<td align='center' width='25%' bgcolor='#cdc9c9'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Exp Date</b></font> ");
				sb.append("</td> ");				
				sb.append("<td align='center' width='25%'><font face='Verdana, Arial, Helvetica, sans-serif' >"+strResponse[6]+"</font> ");
				sb.append("</td> ");
				sb.append("</tr> ");
				sb.append("</table> ");
				sb.append("<table width='").append(strTableWidth).append("' align='center' cellpadding='1px' cellspacing='1px'> ");
				sb.append("<tr> ");
				sb.append("<td  width='25%' bgcolor='#cdc9c9'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Mfd By::"+strResponse[28]+"</b></font><b></td>");
				sb.append("</tr> ");				
				sb.append(" </table> ");

		} 
		catch (Exception e) 
		{

			e.printStackTrace();

			throw e;
		}

		return sb.toString();
	}
	
	
	/**
	 * Gets the issue dtls init view.
	 * 
	 * @param formBean
	 *            the form bean
	 * 
	 * @return the issue dtls init view
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public static String getPrintSampleSentLabelView(String strHiddenValue) throws Exception 
	{
		StringBuffer sb = new StringBuffer("");
		String strResponse[] = null;
		
 		String strTableWidth = "700";
		try 
		{
			
			sb.append("<table border='0' width='").append(strTableWidth).append("' align='center'> ");
			sb.append("<tr> ");
			sb.append("<td align='right'>");
 			sb.append("<div id='saveId' style='display : block'> <div id='printid1' class='hidecontrol' align='right'>");
			sb.append("<img style='cursor: pointer; ' title='Print Page'  ");
			sb.append(" src='../../hisglobal/images/printer_symbol.gif' onClick='printData(1);' /> <img style='cursor: pointer; ' title='Cancel Process'  ");
			sb.append(" src='../../hisglobal/images/stop.png' onClick='hidePopup(1);' /> </div></div>");
			sb.append(" </td> ");
			sb.append(" </tr> ");
			sb.append(" </table> ");
			sb.append(" <br> ");			
			/*
			   
		    1. LAB_NAME	
		    2. ITEMNAME
		    3. BATCHNO	
		   	4. EXPIRYDATE	
		    5. CTR_NO	
		    6. QC_STATUS	
		    7. REPORT_DATE_TIME			    
		    8. STORENAME	
		    9. MFGDATE	
		   10. HSTNUM_ITEM_ID	
		   11. HSTNUM_STORE_ID	
		   12. HSTNUM_LAB_SEND_NO	
		   13. HSTNUM_ITEMBRAND_ID	
		   14. HSTDT_LAB_SEND_DATE	
		   15. HSTSTR_ITEM_SL_NO	
		   16. HSTNUM_STOCK_STATUS_CODE	
		   17. HSTNUM_INHAND_QTY	
		   18. MMS_MST.GETUNITNAME(GNUM_HOSPITAL_CODE,HSTNUM_INHAND_QTY_UNITID)	
		   19. HSTNUM_RATE	
		   20. HSTNUM_RATE_UNITID	
		   21. HSTNUM_SUPPLIER_ID	
		   22. GSTR_SEND_REAMRKS	
		   23. GSTR_RECEIVE_REAMRKS	
		   24. HSTNUM_IS_RESEND	
		   25. HSTNUM_PO_NO
		   26. HSTDT_PO_DATE
		   27. HSTNUM_MANUFACTURER_ID
		   28. In Hand Qty Unit
		   29. Consumed Qty
		   30. Consumed Qty Unit
		   31. Sample Code
		   32. CTR No.
		   33. IS_Online
		   34. Remarks
		   35. Is_Resend
		   36. Lab Code
		   37. Manufactrer Name
		   38. Store Name
				   			
          */
						
			    strResponse = strHiddenValue.replace("$$$$", "#").split("#");
			    for (int i = 1; i < strResponse.length; i++) 
				{
			    	String ctrNumber ="";
				    if(strResponse[i].replace("^", "#").split("#")[1].equals("0")||strResponse[i].replace("^", "#").split("#")[1].equals(""))
				    {
				    	ctrNumber = "---";
				    }
				    else
				    {
				    	ctrNumber = strResponse[i].replace("^", "#").split("#")[1];
				    }
						sb.append(" <br> ");
						sb.append("<table width='").append(strTableWidth).append("' align='center' cellpadding='1px' cellspacing='1px'> ");
						sb.append("<tr> ");
						sb.append("<td align='center' width='50%' bgcolor='#cdc9c9'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>DECODING OF SECRET CODE NO::</b>"+strResponse[i].replace("^", "#").split("#")[11]+"</font> ");
						sb.append("</td>");
						sb.append("<td align='center' width='50%' bgcolor='#cdc9c9'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>CTR NO::</b>"+strResponse[i].replace("^", "#").split("#")[4]+"</font> ");
						sb.append("</td>");
						sb.append("</tr> ");
		 	            sb.append("</table> ");
						
						sb.append("<table width='").append(strTableWidth).append("' align='center' cellpadding='1px' cellspacing='1px'> ");
						sb.append("<tr> ");
						sb.append("<td  colspan='4' bgcolor='#cdc9c9'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Drug Name::  "+strResponse[i].replace("^", "#").split("#")[1]+"</b></font><b></td>");
						sb.append("</tr> ");				
						sb.append(" </table> ");
								
						sb.append("<table width='").append(strTableWidth).append("' align='center' cellpadding='1px' cellspacing='1px'> ");
						sb.append("<tr> ");
						sb.append("<td align='center' width='25%' bgcolor='#cdc9c9'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Batch No</b></font> ");
						sb.append("</td> ");				
						sb.append("<td align='center' width='25%'><font face='Verdana, Arial, Helvetica, sans-serif' >"+strResponse[i].replace("^", "#").split("#")[2]+"</font> ");
						sb.append("</td> ");
						
						sb.append("<td align='center' width='25%' bgcolor='#cdc9c9'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Mfg. Date</b></font> ");
						sb.append("</td> ");				
						sb.append("<td align='center' width='25%'><font face='Verdana, Arial, Helvetica, sans-serif' >"+strResponse[i].replace("^", "#").split("#")[8]+"</font> ");
						sb.append("</td> ");
						sb.append("</tr> ");
						
						sb.append("<tr> ");
						sb.append("<td align='center' width='25%' bgcolor='#cdc9c9'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>M.L. No.</b></font> ");
						sb.append("</td> ");				
						sb.append("<td align='center' width='25%'><font face='Verdana, Arial, Helvetica, sans-serif' >"+strResponse[i].replace("^", "#").split("#")[35]+"</font> ");
						sb.append("</td> ");
						
						sb.append("<td align='center' width='25%'bgcolor='#cdc9c9'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Exp Date</b></font> ");
						sb.append("</td> ");				
						sb.append("<td align='center' width='25%'><font face='Verdana, Arial, Helvetica, sans-serif' >"+strResponse[i].replace("^", "#").split("#")[3]+"</font> ");
						sb.append("</td> ");
						sb.append("</tr> ");
						sb.append("</table> ");
						sb.append("<table width='").append(strTableWidth).append("' align='center' cellpadding='1px' cellspacing='1px'> ");
						sb.append("<tr> ");
						sb.append("<td  width='25%' bgcolor='#cdc9c9'><font face='Verdana, Arial, Helvetica, sans-serif' ><b>Mfd By::"+strResponse[i].replace("^", "#").split("#")[36]+"</b></font><b></td>");
						sb.append("</tr> ");				
						sb.append(" </table> ");
				}	

		} 
		catch (Exception e) 
		{

			e.printStackTrace();

			throw e;
		}

		return sb.toString();
	}
	
	public static String getViewSampleSentDetailsTwo(QcDetailOnlineTransVO vo) throws Exception 
	{
		
		StringBuffer br = new StringBuffer();
		int count = 0;
		final int REC_PER_PAGE = 30;
		final int PAGE_PER_BLOCK = 30;		
		int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
		int totalRecordsToManipulate = totalFetchRecord - 1;
		WebRowSet ws =null;
		String strItemName="";
		String strItemNameWithBatch="";
		
		try
	    {   		
			    ws = vo.getWsLabSentHlp();
			if (ws != null) 
			{
				if(ws.size() != 0)
				{
				     int actualFetchRecord = ws.size();			
			         if(totalFetchRecord != actualFetchRecord)
					 {
						totalFetchRecord = actualFetchRecord;
						totalRecordsToManipulate = actualFetchRecord;
					 }
				    int totalLayer = totalRecordsToManipulate / REC_PER_PAGE;
				    int reminder = totalRecordsToManipulate % REC_PER_PAGE;
				    if (reminder > 0)
				    	
					totalLayer = totalLayer + 1;
				 
				    br.append("<input type='hidden' name='TotalLayer'  value='"+totalLayer+"'>");
				    br.append("<input type='hidden' name='RecordShowOnPage'  value='"+REC_PER_PAGE+"'>");
				    br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding='1px'>");
				    br.append("<tr>");			   
					br.append("<td class='TITLE' style=\"text-align:left;\" colspan='8'>Batch Number [ Item Name ]::::<select name='strItemNameCmb' class='comboTooMax'  onchange='openItemDivForItem(this);'>");
					br.append("<option value='0^0'>Select Value</option>");
					while(ws.next())
					{	
						if(count==0)
					    {
						    strItemName  = ws.getString(6);
					    }
					    if(strItemName.equals(ws.getString(6)))
					    {
					    	strItemNameWithBatch =  ws.getString(6)+" [ "+ws.getString(5)+" ] ";
					    	br.append("<option value='"+ws.getString(6)+"^"+ws.getString(5)+"'>"+strItemNameWithBatch+"</option>");
					    }
					    else
					    {
					    	strItemName  = ws.getString(2);
					    	strItemNameWithBatch =  ws.getString(6)+" [ "+ws.getString(5)+" ] ";
					    	br.append("<option value='"+ws.getString(6)+"^"+ws.getString(5)+"'>"+strItemNameWithBatch+"</option>");
					    }
					    
					    count++;
					}
						
					br.append("</select></td></tr>");
					br.append("</table>");
					ws.beforeFirst();
					
					
					
			    for (int i = 1; i <= totalLayer; i++) 
			    {
				 if (i <= totalLayer) 
				 {
					 
					if (i == 1) 
					{
						br.append("<div id='DivId" +i+ "' style='display:block'>");
					} 
					else
					{
						br.append("<div id='DivId" +i+ "' style='display:none'>");
					}
					
					br.append("<table class='TABLEWIDTH' bgcolor='#6097BC' align='center' cellspacing='1px' cellpadding='1px'>");
					br.append("<tr>");
			    	br.append("<td width='10%'class='multiRPTLabel'>Select");
			    	br.append("</td>");
			    	br.append("<td width='10%'class='multiRPTLabel'>Sent Date");
			    	br.append("</td>");
			    	br.append("<td width='15%'class='multiRPTLabel'>Expiry Date");
			    	br.append("</td>");
			    	br.append("<td width='30%'class='multiRPTLabel'>Drug Name");
			    	br.append("</td>");
			    	br.append("<td width='10%'class='multiRPTLabel'>Batch No.");
			    	br.append("</td>");
			    	br.append("<td width='15%'class='multiRPTLabel'>Sent Qty");
			    	br.append("</td>");					
			    	br.append("</tr>");				

					for (int j = 0; j < REC_PER_PAGE; j++) 
					{
					/*
						1- Item Id
						2- Lab Sent Date
						3- Sent Store Name
						4- Generic Name
						5-Brand Name
						6- Batch
						7-Exp Date
						8-Transfer Qty
						9-Store Id Sent
						10-Item Id
						11-Item Brand ID
						12-Rate With Unit
						13-Rate Base value
						14-Consumed Qty
						15=Consumed Qty Wit Unit
						16-Qty Base Value
						17-Item Sl No
						18-Item Sl No
						19-Catg Code
						20- Lab Send No
						21-Lab Name
						22-CTR Number
						23-Net Cost
						24-PO No
						25-PO Date
						26-Mfd Date
						27-Is Send Decode Value
	                    28 Lab No
	                    29 Manufacter By
	                    30. ITEM_STATUS
	                    31. REPORT_DATE
	                    32. GNUM_HOSPITAL_CODE
	                    
	                    33.	HSTNUM_STOCK_STATUS_CODE
	                    34.	HSTNUM_INHAND_QTY, 
	                    35.	HSTNUM_INHAND_QTY_UNITID, 
	                    36.	HSTNUM_SUPPLIER_ID, 
	                    37.	HSTNUM_MANUFACTURER_ID, 
	                    38.	HSTSTR_SAMPLE_CODE_NO, 
	                    39.	HSTSTR_CTR_NO, 
	                    40.	GSTR_SEND_REAMRKS
	                    
	                    41.	HSTNUM_RATE, 
       				    42.	HSTNUM_RATE_UNITID
                     */
						
						if(ws.next())
						{					
							br.append("<input type='hidden' name='itemName'     id='itemName"+i+"-"+j+"' value='"+ws.getString(5)+"^"+i+"-"+j+"'>");
							br.append("<input type='hidden' name='batchNo'      id='batchNo"+i+"-"+j+"' value='"+ws.getString(6)+"^"+i+"-"+j+"'>");
							br.append("<input type='hidden' name='divIndex'     id='divIndex"+i+"' value='"+i+"'>");
							
							String strPrimaryKey = ws.getString(9)+"^"+ws.getString(20)+"^"+ws.getString(32);	
							
							String strCheckHidValue = ws.getString(1)+"^"+ws.getString(2)+"^"+ws.getString(3)+"^"+ws.getString(4)+"^"+ws.getString(5)+"^"+ws.getString(6)+"^"+ws.getString(7)+"^"+ws.getString(8)+"^"+ws.getString(9)
						                          +"^"+ws.getString(10)+"^"+ws.getString(11)+"^"+ws.getString(12)+"^"+ws.getString(13)+"^"+ws.getString(14)+"^"+ws.getString(15)+"^"+ws.getString(16)+"^"+ws.getString(17)+"^"+ws.getString(18)
						                          +"^"+ws.getString(19)+"^"+ws.getString(20)+"^"+ws.getString(21)+"^"+ws.getString(22)+"^"+ws.getString(23)+"^"+ws.getString(24)+"^"+ws.getString(25)+"^"+ws.getString(26)+"^"+ws.getString(27)
						                          +"^"+ws.getString(28)+"^"+ws.getString(29)+"^"+ws.getString(30)+"^"+ws.getString(31)+"^"+ws.getString(32)
						                          +"^"+ws.getString(33)+"^"+ws.getString(34)+"^"+ws.getString(35)+"^"+ws.getString(36)+"^"+ws.getString(37)
						                          +"^"+ws.getString(38)+"^"+ws.getString(39)+"^"+ws.getString(40)+"^"+ws.getString(41)+"^"+ws.getString(42);			
						
						    //                           Lab Sne No +      CTR No  + Item Brand Name  + Batch No  + Mfd Date +  Lab Number + Exp Date + Mfd By
						    String strPrintLabelValue = ws.getString(20)+"^"+ws.getString(22)+"^"+ws.getString(5)+"^"+ws.getString(6)+"^"+ws.getString(26)+"^"+ws.getString(28)+"^"+ws.getString(7)+"^"+ws.getString(29);
						    br.append("<tr>");
						    br.append("<input type='hidden' name='strCheckHidValue' id='strCheckHidValue"+count+"' value='"+strCheckHidValue+"'>");
						    br.append("<input type='hidden' name='strPrintLabelValue' id='strPrintLabelValue"+count+"' value='"+strPrintLabelValue+"'>");
						    br.append("<td width='10%' class='multiPOControl'  name='tdId' id='tdId1"+i+"-"+j+"'  ><input name='chkFlg' id='chkFlg"+count+"' value = '"+strPrimaryKey+"' onclick='CheckedFlg(this,\""+count+"\");' type='radio'>");
						    br.append("</td>");
						    br.append("<td class='multiPOControl' width='10%' name='tdId' id='tdId2"+i+"-"+j+"'>");
						    br.append(ws.getString(2));
						    br.append("</td>");
						    br.append("<td class='multiPOControl' width='15%' name='tdId' id='tdId3"+i+"-"+j+"'>");
						    br.append(ws.getString(7));
						    br.append("</td>");
						    br.append("<td class='multiPOControl' width='30%' name='tdId' id='tdId4"+i+"-"+j+"'>");
						    br.append("<a STYLE='cursor:pointer;cursor:hand;color:blue' name='strDrugName' id='strDrugName"+count+"' onClick='drugDtl(this,\""+count+"\");'>"+ws.getString(5)+"</a> &nbsp;");
						    br.append("</td>");
						    br.append("<td class='multiPOControl' width='10%' name='tdId' id='tdId5"+i+"-"+j+"'>");
						    br.append(ws.getString(6));
						    br.append("</td>");
						    br.append("<td class='multiPOControl' width='15%' name='tdId' id='tdId6"+i+"-"+j+"'>");
						    br.append(ws.getString(14));
						    br.append("</td>");
							
						    br.append("</tr>");				
							        count++ ;
							
						}
						else
						{
							break;
						}
  					  }
 					}
						br.append("</table>");						
						br.append("</div>");	
				} 
			    br.append("</div>");			    
			    br.append("<table class='TABLEWIDTH' align='center' bgcolor='#6097BC'  border='0' cellpadding='1px' cellspacing ='1px'>");
			    br.append("<tr>");			   
			    br.append("<td class='LABEL'><span onclick='prevFunction();' class='pg-normal'> &#171 Prev </span><select name='pageIndeCmb' class='comboMin' onchange='openItemDiv(this);'>");
				for (int k = 1; k <= totalLayer; k++)
				{
					br.append("<option value='"+k+"'>"+k+"</option>");						
				}
			    br.append("</select>");
			    br.append("<span onclick='nextFunction();' class='pg-normal'> Next &#187;</span>");
			    br.append("</td></tr>");
			    br.append("</table>");	
			   
			}
			else
			{
				 
				
				br.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px' bgcolor='black'>");
		    	br.append("<tr>");
		    	br.append("<td width='10%'class='multiLabel'>Select");
		    	br.append("</td>");
		    	br.append("<td width='10%'class='multiLabel'>Sent Date");
		    	br.append("</td>");
		    	br.append("<td width='15%'class='multiLabel'>Expiry Date");
		    	br.append("</td>");
		    	br.append("<td width='30%'class='multiLabel'>Drug Name");
		    	br.append("</td>");
		    	br.append("<td width='10%'class='multiLabel'>Batch No.");
		    	br.append("</td>");
		    	br.append("<td width='15%'class='multiLabel'>Sent Qty");
		    	br.append("</td>");					
		    	br.append("</tr>");
			    br.append("<tr>");  
           	    br.append("<td class='multiRPTControl' colspan='9'><font color='red'><b>No Detail(s) Available</b></font></td></tr>");
           	    br.append("</table>");
	           	
					
			   }
			} 
		    else
				{
					 
					 
			    	br.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px' bgcolor='black'>");
			    	br.append("<tr>");
			    	br.append("<td width='10%'class='multiLabel'>Select");
			    	br.append("</td>");
			    	br.append("<td width='10%'class='multiLabel'>Sent Date");
			    	br.append("</td>");
			    	br.append("<td width='15%'class='multiLabel'>Expiry Date");
			    	br.append("</td>");
			    	br.append("<td width='30%'class='multiLabel'>Drug Name");
			    	br.append("</td>");
			    	br.append("<td width='10%'class='multiLabel'>Batch No.");
			    	br.append("</td>");
			    	br.append("<td width='15%'class='multiLabel'>Sent Qty");
			    	br.append("</td>");					
			    	br.append("</tr>");
				    br.append("<tr>");  
	           	    br.append("<td class='multiRPTControl' colspan='9'><font color='red'><b>No Detail(s) Available</b></font></td></tr>");
	           	    br.append("</table>");
		          	
						
				   }
	     }
		
	catch (Exception e) {	
		    e.printStackTrace();
			throw new Exception("QcDetailOnlineTransHLP.getIndentItemList()->"+e.getMessage());
		}
		return br.toString();
	}
	
	
	public static String getViewSampleSentDetails(QcDetailOnlineTransVO vo) 
	 {
		StringBuffer sb = new StringBuffer("");
		int start = 1;
		final int REC_PER_PAGE = 100;
		final int PAGE_PER_BLOCK = 100;
		int count=0;
		
		MmsConfigUtil mcu = null;
		WebRowSet ws = null;
		int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
		int totalRecordsToManipulate = totalFetchRecord - 1;
		
	    try
	    {
	    	mcu = new MmsConfigUtil(vo.getStrHospitalCode());
	    	ws = vo.getWsLabSentHlp();
	    	
		if (ws != null) 
		{
			if(ws.size() != 0)
			{
				//System.out.println("Hello1");
				int actualFetchRecord = ws.size();
		
	         if(totalFetchRecord != actualFetchRecord)
			 {
				totalFetchRecord = actualFetchRecord;
				totalRecordsToManipulate = actualFetchRecord;
			 }
			 int totalLayer = totalRecordsToManipulate / REC_PER_PAGE;
			 int reminder = totalRecordsToManipulate % REC_PER_PAGE;
			 if (reminder > 0)
				totalLayer = totalLayer + 1;
			 
			 sb.append("<input type='hidden' name='TotalLayer'  value='").append(totalLayer).append("'>");
			 sb.append("<input type='hidden' name='RecordShowOnPage'  value='").append(REC_PER_PAGE).append("'>");
//			 sb.append("<table class='TABLEWIDTH' align='center'  border='0' cellspacing ='1px' cellpadding='1px'>");
//			 sb.append("<tr>");			   
//			 sb.append("<td class='TITLE' style=\"text-align:right;\" >Searching Criteria :::: <select name='strSearchNameCmb' class='comboMax'>");
//			 sb.append("<option value='1'>Drug Name</option>");
//			 sb.append("<option value='2'>Batch Number</option>");
//			 sb.append("</select>");
//			 sb.append("<input type='text' class='txtFldMax' name='strSearchString' value ='' maxlength='100' onkeypress='if(event.keyCode==13) getSampleSent_With_Search();'>");					
//			 sb.append("</td>");
//			 sb.append("</tr>");					
//			 sb.append("</table>");			 
			 sb.append("<table class='TABLEWIDTH' align='center' cellspacing ='1px' cellpadding='1px'>");
			 sb.append("<tr>");
			 sb.append("<td class='LABEL' >");
			 for (int i = 1; i <= totalLayer; i++)
			 {
				sb.append("<a name='pg' id='pg" + i + "'  STYLE='cursor:pointer;cursor:pointer;' class='pg-normal' title='Record Indexing' onClick='GetIndex(\""+i+"\",\""+totalLayer+"\")'>" + (i+start-1)
						+ "</a>|&nbsp;");
			 }
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px' bgcolor='#6097BC'>");
	        sb.append("<tr>");
	        sb.append("<td width='5%'class='multiRPTLabel'>Select");
		    sb.append("</td>");
	        sb.append("<td width='10%'class='multiRPTLabel'>Sent Date");
		    sb.append("</td>");
	        sb.append("<td width='15%'class='multiRPTLabel'>Expiry Date");
	        sb.append("</td>");
	        sb.append("<td width='40%'class='multiRPTLabel'>Drug Name");
	        sb.append("</td>");
	        sb.append("<td width='10%'class='multiRPTLabel'>Batch No.");
	        sb.append("</td>");
	        sb.append("<td width='10%'class='multiRPTLabel'>Sent Qty");
		    sb.append("</td>");
		   // sb.append("<td width='10%'class='multiLabel'>CTR No.");
		    //sb.append("</td>");
		    
	        sb.append("</tr>");
	        sb.append("</table>");
	        
		    for (int i = 1; i <= totalLayer; i++) 
		    {
			 if (i <= totalLayer) 
			 {
				 
				if (i == 1) 
				{
					sb.append("<div id='DivId" ).append(i).append( "' style='display:block'>");
				} 
				else
				{
					sb.append("<div id='DivId" ).append(i).append( "' style='display:none'>");
				}
				
				sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' bgcolor='#6097BC' cellpadding='1px'>");
				for (int j = 0; j < REC_PER_PAGE; j++) 
				{
					/*
						1- Item Id
						2- Lab Sent Date
						3- Sent Store Name
						4- Generic Name
						5-Brand Name
						6- Batch
						7-Exp Date
						8-Transfer Qty
						9-Store Id Sent
						10-Item Id
						11-Item Brand ID
						12-Rate With Unit
						13-Rate Base value
						14-Consumed Qty
						15=Consumed Qty Wit Unit
						16-Qty Base Value
						17-Item Sl No
						18-Item Sl No
						19-Catg Code
						20- Lab Send No
						21-Lab Name
						22-CTR Number
						23-Net Cost
						24-PO No
						25-PO Date
						26-Mfd Date
						27-Is Send Decode Value
	                    28 Lab No
	                    29 Manufacter By
	                    30. ITEM_STATUS
	                    31. REPORT_DATE
	                    32. GNUM_HOSPITAL_CODE
	                    
	                    33.	HSTNUM_STOCK_STATUS_CODE
	                    34.	HSTNUM_INHAND_QTY, 
	                    35.	HSTNUM_INHAND_QTY_UNITID, 
	                    36.	HSTNUM_SUPPLIER_ID, 
	                    37.	HSTNUM_MANUFACTURER_ID, 
	                    38.	HSTSTR_SAMPLE_CODE_NO, 
	                    39.	HSTSTR_CTR_NO, 
	                    40.	GSTR_SEND_REAMRKS
	                    
	                    41.	HSTNUM_RATE, 
       				42.	HSTNUM_RATE_UNITID
                   */
					if(ws.next())
					{
						String strPrimaryKey = ws.getString(9)+"^"+ws.getString(20)+"^"+ws.getString(32);	
						
						String strCheckHidValue = ws.getString(1)+"^"+ws.getString(2)+"^"+ws.getString(3)+"^"+ws.getString(4)+"^"+ws.getString(5)+"^"+ws.getString(6)+"^"+ws.getString(7)+"^"+ws.getString(8)+"^"+ws.getString(9)
					                          +"^"+ws.getString(10)+"^"+ws.getString(11)+"^"+ws.getString(12)+"^"+ws.getString(13)+"^"+ws.getString(14)+"^"+ws.getString(15)+"^"+ws.getString(16)+"^"+ws.getString(17)+"^"+ws.getString(18)
					                          +"^"+ws.getString(19)+"^"+ws.getString(20)+"^"+ws.getString(21)+"^"+ws.getString(22)+"^"+ws.getString(23)+"^"+ws.getString(24)+"^"+ws.getString(25)+"^"+ws.getString(26)+"^"+ws.getString(27)
					                          +"^"+ws.getString(28)+"^"+ws.getString(29)+"^"+ws.getString(30)+"^"+ws.getString(31)+"^"+ws.getString(32)
					                          +"^"+ws.getString(33)+"^"+ws.getString(34)+"^"+ws.getString(35)+"^"+ws.getString(36)+"^"+ws.getString(37)
					                          +"^"+ws.getString(38)+"^"+ws.getString(39)+"^"+ws.getString(40)+"^"+ws.getString(41)+"^"+ws.getString(42);			
					
					//                           Lab Sne No +      CTR No  + Item Brand Name  + Batch No  + Mfd Date +  Lab Number + Exp Date + Mfd By
					String strPrintLabelValue = ws.getString(20)+"^"+ws.getString(22)+"^"+ws.getString(5)+"^"+ws.getString(6)+"^"+ws.getString(26)+"^"+ws.getString(28)+"^"+ws.getString(7)+"^"+ws.getString(29);
                  		sb.append("<tr>");
					sb.append("<input type='hidden' name='strCheckHidValue' id='strCheckHidValue"+count+"' value='"+strCheckHidValue+"'>");
					sb.append("<input type='hidden' name='strPrintLabelValue' id='strPrintLabelValue"+count+"' value='"+strPrintLabelValue+"'>");
					sb.append("<td width='5%'class='multiPOControl'><input name='chkFlg' id='chkFlg"+count+"' value = '"+strPrimaryKey+"' onclick='CheckedFlg(this,\""+count+"\");' type='radio'>");
				    sb.append("</td>");
					sb.append("<td class='multiPOControl' width='10%'>");
					sb.append(ws.getString(2));
					sb.append("</td>");
					sb.append("<td class='multiPOControl' width='15%'>");
					sb.append(ws.getString(7));
					sb.append("</td>");
					sb.append("<td class='multiPOControl' width='40%' style=\"text-align:left;\">");
					sb.append("<a STYLE='cursor:pointer;cursor:hand;color:blue' name='strDrugName' id='strDrugName"+count+"' onClick='drugDtl(this,\""+count+"\");'>"+ws.getString(5)+"</a> &nbsp;");
					sb.append("</td>");
					sb.append("<td class='multiPOControl' width='10%'>");
					sb.append(ws.getString(6));
					sb.append("</td>");
					sb.append("<td class='multiPOControl' width='10%'>");
					sb.append(ws.getString(14));
					sb.append("</td>");
					
					sb.append("</tr>");
					}
					else
					{
						break;
					}
					count++;
				}
				sb.append("</table>");
				sb.append("</div>");

			} 
			else 
			{
				sb.append("<div id='DivId" ).append( i).append( "' style='display:none'>");
				
				sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' bgcolor='#6097BC' cellpadding='1px'>");
				for (int k = 0; k < reminder; k++) 
				{
					ws.next();
					String strPrimaryKey = ws.getString(9)+"^"+ws.getString(20)+"^"+ws.getString(32);
					
					String strCheckHidValue = ws.getString(1)+"^"+ws.getString(2)+"^"+ws.getString(3)+"^"+ws.getString(4)+"^"+ws.getString(5)+"^"+ws.getString(6)+"^"+ws.getString(7)+"^"+ws.getString(8)+"^"+ws.getString(9)
                  +"^"+ws.getString(10)+"^"+ws.getString(11)+"^"+ws.getString(12)+"^"+ws.getString(13)+"^"+ws.getString(14)+"^"+ws.getString(15)+"^"+ws.getString(16)+"^"+ws.getString(17)+"^"+ws.getString(18)
                  +"^"+ws.getString(19)+"^"+ws.getString(20)+"^"+ws.getString(21)+"^"+ws.getString(22)+"^"+ws.getString(23)+"^"+ws.getString(24)+"^"+ws.getString(25)+"^"+ws.getString(26)+"^"+ws.getString(27)
					+"^"+ws.getString(28)+"^"+ws.getString(29)+"^"+ws.getString(30)+"^"+ws.getString(31)+"^"+ws.getString(32);
					
					String strPrintLabelValue = ws.getString(20)+"^"+ws.getString(22)+"^"+ws.getString(5)+"^"+ws.getString(6)+"^"+ws.getString(26)+"^"+ws.getString(28)+"^"+ws.getString(7)+"^"+ws.getString(29);
					sb.append("<tr>");
			        sb.append("<input type='hidden' name='strCheckHidValue' id='strCheckHidValue"+count+"' value='"+strCheckHidValue+"'>");
			        sb.append("<input type='hidden' name='strPrintLabelValue' id='strPrintLabelValue"+count+"' value='"+strPrintLabelValue+"'>");
			        sb.append("<td width='5%'class='multiPOControl'><input name='chkFlg' id='chkFlg"+count+"' value = '"+strPrimaryKey+"' onclick='CheckedFlg(this,\""+count+"\");' type='radio'>");
				    sb.append("</td>");
					sb.append("<td class='multiPOControl' width='10%'>");
					sb.append(ws.getString(2));
					sb.append("</td>");
					sb.append("<td class='multiPOControl' width='15%'>");
					sb.append(ws.getString(7));
					sb.append("</td>");
					sb.append("<td class='multiPOControl' width='40%'>");
					sb.append("<a STYLE='cursor:pointer;cursor:hand;color:blue' name='strDrugName' id='strDrugName"+count+"' onClick='drugDtl(this,\""+count+"\");'>"+ws.getString(5)+"</a> &nbsp;");
					sb.append("</td>");
					sb.append("<td class='multiPOControl' width='10%'>");
					sb.append(ws.getString(6));
					sb.append("</td>");
					sb.append("<td class='multiPOControl' width='15%'>");
					sb.append(ws.getString(14));
					sb.append("</td>");
					sb.append("<td class='multiPOControl' width='10%'>");
					sb.append(ws.getString(22));
					sb.append("</td>");
					
					sb.append("</tr>");
					count++;
				
				}
				sb.append("</table>");
				sb.append("</div>");
				}
		   	}
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("</table>");
		}
		else
		{
			sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' bgcolor='#6097BC'  cellpadding='1px'>");
			sb.append("<tr>");
			sb.append("<td class='multiPOControl' align='center' ><font color='red'>No Record Found</font></td>");
			sb.append("</tr>");
			sb.append("</table>");
		}
	   } 
		
	 }
	 catch(Exception e)
	 {
		    e.printStackTrace();
		    new HisException("QCDetailsOnLineHLP Transaction","QCDetailsOnLineHLP.getViewSampleSentDetails()-->",e.getMessage());
	    }
	     //System.out.println("sb"+sb);
	    return sb.toString();
	 	}
	
	/**
	 * This method is used to show issued item Details on VIEW PAGE2
	 * 
	 * @param wb
	 * @param strItemCategory
	 * @param strHospitalCode
	 * @return
	 */
	public static String getQcOnlineDetail(QcDetailOnlineTransVO vo)throws SQLException, Exception 
	{
		StringBuffer sb = new StringBuffer("");
		int start = 1;
		final int REC_PER_PAGE = 100;
		final int PAGE_PER_BLOCK = 100;
		int count=0;
		
		
		WebRowSet ws = null;
		int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
		int totalRecordsToManipulate = totalFetchRecord - 1;
		
	    try
	    {
	    	
	    	ws = vo.getWrsViewIssueSampleDetail();
	    	sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding='1px'>");
	    	sb.append("<tr><td colspan='4' width='100%' class='TITLE'>QC View Detail</td></tr></table>");
		if (ws != null) 
		{
			if(ws.size() != 0)
			{
				//System.out.println("Hello1");
				int actualFetchRecord = ws.size();
		
	         if(totalFetchRecord != actualFetchRecord)
			 {
				totalFetchRecord = actualFetchRecord;
				totalRecordsToManipulate = actualFetchRecord;
			 }
			 int totalLayer = totalRecordsToManipulate / REC_PER_PAGE;
			 int reminder = totalRecordsToManipulate % REC_PER_PAGE;
			 if (reminder > 0)
				totalLayer = totalLayer + 1;
			 
			 sb.append("<input type='hidden' name='TotalLayer'  value='").append(totalLayer).append("'>");
			 sb.append("<input type='hidden' name='RecordShowOnPage'  value='").append(REC_PER_PAGE).append("'>");
			 sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
			 sb.append("<tr>");
			 sb.append("<td class='LABEL' >");

//			 for (int i = 1; i <= totalLayer; i++)
//			 {
//				sb.append("<a STYLE='cursor:pointer;cursor:pointer;' color:'blue' onClick='GetIndex(\"").append(i).append("\",\"")
//				.append(totalLayer).append("\")'>" ).append( (i+start-1)).append( "</a> &nbsp;");
//			 }
			 
			 for (int i = 1; i <= totalLayer; i++)
			 {
				sb.append("<a name='pg' id='pg" + i + "'  STYLE='cursor:pointer;cursor:pointer;' class='pg-normal' title='Record Indexing' onClick='GetIndex(\""+i+"\",\""+totalLayer+"\")'>" + (i+start-1)
						+ "</a>|&nbsp;");
			 }
			sb.append("</td>");
			sb.append("</tr>");
			
//			Sample Issued Detail
			
			  
			sb.append("</table>");
			
			sb.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px' bgcolor='#6097BC'>");
			sb.append("<tr>");
		       //sb.append("<td width='12%'class='multiRPTLabel'>Lab Name</td>");
		        sb.append("<td width='32%'class='multiRPTLabel'>Drug Name</td>");
		        sb.append("<td width='15%'class='multiRPTLabel'>Batch No.</td>");
		        sb.append("<td width='12%'class='multiRPTLabel'>Expiry Date</td>");
			    sb.append("<td width='12%'class='multiRPTLabel'>CTR NO</td>");
			    sb.append("<td width='12%'class='multiRPTLabel'>QC Status</td>");
			    sb.append("<td width='12%'class='multiRPTLabel'>Report Date</td>");
			    sb.append("<td width='5%'class='multiRPTLabel'><input name='chkmain' onclick='CheckedAll(this);' type='CHECKBOX'>");
			    sb.append("</td>");
		    sb.append("</tr>");
	        sb.append("</table>");
	        
		    for (int i = 1; i <= totalLayer; i++) 
		    {
			 if (i <= totalLayer) 
			 {
				 
				if (i == 1) 
				{
					sb.append("<div id='DivId" ).append(i).append( "' style='display:block'>");
				} 
				else
				{
					sb.append("<div id='DivId" ).append(i).append( "' style='display:none'>");
				}
				
				sb.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px' bgcolor='#6097BC'>");
				for (int j = 0; j < REC_PER_PAGE; j++) 
				{
			
			/*
				      			   
			    1. LAB_NAME	
			    2. ITEMNAME
			    3. BATCHNO	
			   	4. EXPIRYDATE	
			    5. CTR_NO	
			    6. QC_STATUS	
			    7. REPORT_DATE_TIME			    
			    8. STORENAME	
			    9. MFGDATE	
			   10. HSTNUM_ITEM_ID	
			   11. HSTNUM_STORE_ID	
			   12. HSTNUM_LAB_SEND_NO	
			   13. HSTNUM_ITEMBRAND_ID	
			   14. HSTDT_LAB_SEND_DATE	
			   15. HSTSTR_ITEM_SL_NO	
			   16. HSTNUM_STOCK_STATUS_CODE	
			   17. HSTNUM_INHAND_QTY	
			   18. MMS_MST.GETUNITNAME(GNUM_HOSPITAL_CODE,HSTNUM_INHAND_QTY_UNITID)	
			   19. HSTNUM_RATE	
			   20. HSTNUM_RATE_UNITID	
			   21. HSTNUM_SUPPLIER_ID	
			   22. GSTR_SEND_REAMRKS	
			   23. GSTR_RECEIVE_REAMRKS	
			   24. HSTNUM_IS_RESEND	
			   25. HSTNUM_PO_NO
			   26. HSTDT_PO_DATE
			   27. HSTNUM_MANUFACTURER_ID
			   28. In Hand Qty Unit
			   29. Consumed Qty
			   30. Consumed Qty Unit
			   31. Sample Code
			   32. CTR No.
			   33. IS_Online
			   34. Remarks
			   35. Is_Resend
			   36. Lab Code
			   37. Manufactrer Name
					   			
              */
					
					
					if(ws.next())
					{
						String strCheckHidValue = ws.getString(1)+"^"+ws.getString(2)+"^"+ws.getString(3)+"^"+ws.getString(4)+"^"+ws.getString(5)+"^"+ws.getString(6)+"^"+ws.getString(7)+"^"+ws.getString(8)+"^"+ws.getString(9)+"^"+ws.getString(10)+"^"+ws.getString(11)+"^"+ws.getString(12)+"^"+ws.getString(13)+"^"+ws.getString(14)+"^"+ws.getString(15)+"^"+ws.getString(16)+"^"+ws.getString(17)+"^"+ws.getString(18)+"^"+ws.getString(19)+"^"+ws.getString(20)+"^"+ws.getString(21)+"^"+ws.getString(22)+"^"+ws.getString(23)+"^"+ws.getString(24)+"^"+ws.getString(25)+"^"+ws.getString(26)+"^"+ws.getString(27)+"^"+ws.getString(28)+"^"+ws.getString(29)+"^"+ws.getString(30)+"^"+ws.getString(31)+"^"+ws.getString(32)+"^"+ws.getString(33)+"^"+ws.getString(34)+"^"+ws.getString(35)+"^"+ws.getString(36)+"^"+ws.getString(37)+"^"+vo.getStrStoreName()+"^"+vo.getStrItemCategoryCmb();
						String strPrintLabelValue = ws.getString(25)+"^"+ws.getString(26)+"^"+ws.getString(4)+"^"+ws.getString(9);
						//System.out.println("strCheckHidValue :"+strCheckHidValue);
					sb.append("<tr>");
					sb.append("<input type='hidden' name='strCheckHidValue' id='strCheckHidValue"+count+"' value='"+strCheckHidValue+"'>");
					sb.append("<input type='hidden' name='strPrintLabelValue' id='strPrintLabelValue"+count+"' value='"+strPrintLabelValue+"'>");
//					sb.append("<td class='multiPOControl' style=\"text-align:left;\" width='12%'>");
//					sb.append(ws.getString(1));
//					sb.append("</td>");
					
					sb.append("<td style=\"text-align:left;\" class='multiPOControl' width='32%'>");
					//sb.append("<a STYLE='cursor:pointer;cursor:hand;color:blue' name='strDrugName' id='strDrugName"+count+"' onClick='drugDtl(this,\""+count+"\");'>"+ws.getString(2)+"</a> &nbsp;");
					sb.append("<a STYLE='color:blue' name='strDrugName' id='strDrugName"+count+"' >"+ws.getString(2)+"</a> &nbsp;");
					sb.append("</td>");
					
					sb.append("<td class='multiPOControl' width='15%'>");
					sb.append(ws.getString(3));
					sb.append("</td>");
					
					sb.append("<td class='multiPOControl' width='12%'>");
					sb.append(ws.getString(4));
					sb.append("</td>");
					
					sb.append("<td class='multiPOControl' width='12%'>");
					sb.append(ws.getString(5));
					sb.append("</td>");
					
					sb.append("<td class='multiPOControl' width='12%'>");
					sb.append(ws.getString(6));
					sb.append("</td>");
					
					
					sb.append("<td class='multiPOControl' width='12%'>");
					sb.append(ws.getString(7));
					sb.append("</td>");
					sb.append("<td width='5%'class='multiControl'><input name='chkFlg' id='chkFlg"+count+"' onclick='CheckedFlgValue(this,\""+count+"\");' type='CHECKBOX'>");
				    sb.append("</td>");
					
				    
					sb.append("</tr>");
					}
					else
					{
						break;
					}
					count++;
				}
				sb.append("</table>");
				sb.append("</div>");

			} 
			else 
			{
				sb.append("<div id='DivId" ).append( i).append( "' style='display:none'>");
				
				sb.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px' bgcolor='#6097BC'>");
				for (int k = 0; k < reminder; k++) 
				{
					ws.next();
					String strCheckHidValue = ws.getString(1)+"^"+ws.getString(2)+"^"+ws.getString(3)+"^"+ws.getString(4)+"^"+ws.getString(5)+"^"+ws.getString(6)+"^"+ws.getString(7)+"^"+ws.getString(8)+"^"+ws.getString(9)+"^"+ws.getString(10)+"^"+ws.getString(11)+"^"+ws.getString(12)+"^"+ws.getString(13)+"^"+ws.getString(14)+"^"+ws.getString(15)+"^"+ws.getString(16)+"^"+ws.getString(17)+"^"+ws.getString(18)+"^"+ws.getString(19)+"^"+ws.getString(20)+"^"+ws.getString(21)+"^"+ws.getString(22)+"^"+ws.getString(23)+"^"+ws.getString(24)+"^"+ws.getString(25)+"^"+ws.getString(26)+"^"+ws.getString(27)+"^"+vo.getStrStoreName()+"^"+vo.getStrItemCategoryCmb();
					String strPrintLabelValue = ws.getString(25)+"^"+ws.getString(26)+"^"+ws.getString(4)+"^"+ws.getString(9);
					sb.append("<tr>");
					sb.append("<input type='hidden' name='strCheckHidValue' id='strCheckHidValue"+count+"' value='"+strCheckHidValue+"'>");
					sb.append("<input type='hidden' name='strPrintLabelValue' id='strPrintLabelValue"+count+"' value='"+strPrintLabelValue+"'>");
//					sb.append("<td class='multiPOControl' style=\"text-align:left;\" width='12%'>");
//					sb.append(ws.getString(1));
//					sb.append("</td>");
					
					sb.append("<td style=\"text-align:left;\" class='multiPOControl' width='32%'>");
					//sb.append("<a STYLE='cursor:pointer;cursor:hand;color:blue' name='strDrugName' id='strDrugName"+count+"' onClick='drugDtl(this,\""+count+"\");'>"+ws.getString(2)+"</a> &nbsp;");
					sb.append("<a STYLE='color:blue' name='strDrugName' id='strDrugName"+count+"' >"+ws.getString(2)+"</a> &nbsp;");
					sb.append("</td>");
					
					sb.append("<td class='multiPOControl' width='15%'>");
					sb.append(ws.getString(3));
					sb.append("</td>");
					
					sb.append("<td class='multiPOControl' width='12%'>");
					sb.append(ws.getString(4));
					sb.append("</td>");
					
					sb.append("<td class='multiPOControl' width='12%'>");
					sb.append(ws.getString(5));
					sb.append("</td>");
					
					sb.append("<td class='multiPOControl' width='12%'>");
					sb.append(ws.getString(6));
					sb.append("</td>");
					
					
					sb.append("<td class='multiControl' width='12%'>");
					sb.append(ws.getString(7));
					sb.append("</td>");
					sb.append("<td width='5%'class='multiPOControl'><input name='chkFlg' id='chkFlg"+count+"' onclick='CheckedFlgValue(this,\""+count+"\");' type='CHECKBOX'>");
				    sb.append("</td>");				    
					sb.append("</tr>");
					
					count++;
				
				}
				sb.append("</table>");
				sb.append("</div>");
				}
		   	}
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("</table>");
		}
		else
		{
			sb.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px' bgcolor='#6097BC'>");
			sb.append("<tr>");
			sb.append("<td class='multiPOControl'><font color='red'>No Record Found</font></td>");
			sb.append("</tr>");
			sb.append("</table>");
		}
	   } 
		
	 }
	 catch(Exception e)
	 {
		    e.printStackTrace();
		    new HisException("Sample Receive Detail Process","SampleReceiveDetailProcessTransHLP.getViewSampleReceiveDetailsHlp()-->",e.getMessage());
	 }
	     //System.out.println("sb"+sb);
     return sb.toString();
	}

	
	public static String getPendingDemands(WebRowSet ws) throws Exception 
	{
		
		StringBuffer br = new StringBuffer();
		int count = 0;
		int start = 1;
		final int REC_PER_PAGE = 5;
		final int PAGE_PER_BLOCK = 5;
		String strUrgentFlg,strLastIssueDate;
		
		int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
		int totalRecordsToManipulate = totalFetchRecord - 1;
		try
	    {
	    	/* Value Pass in Web Row Set
	    	   1. C.HSTNUM_REQ_NO 
	    	   2. C.HSTNUM_STORE_ID , 
               3. C.GNUM_HOSPITAL_CODE,
               4. count_urgent 
               5. c.URGENT_FLG 
               6. C.HSTNUM_REQ_NO 
               7. C.REQ_DATE 
               8. C.RAISING_STORE 
               9. C.CATEGORY 
               10.C.LST_ISSUE_DATE  
	    	 */    	
			br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding='1px'>");
		 	br.append("<tr><td colspan='1' width='5%' class='TITLE'>");
			br.append("<div id='plusPrevTechEntryDtlId' align='center' style='display:block;'>");
			br.append("<img src='../../hisglobal/images/plus.gif' style='cursor: pointer; '"); 
			br.append("onClick=view1('plusPrevTechEntryDtlId','minusPrevTechEntryDtlId','prevTechEntryDtlId');>");
			br.append("</div>");
			br.append("<div id='minusPrevTechEntryDtlId' style='display:none;' align='center'>");
			br.append("<img src='../../hisglobal/images/minus.gif' style='cursor: pointer; '"); 
			br.append("onClick=view2('plusPrevTechEntryDtlId','minusPrevTechEntryDtlId','prevTechEntryDtlId');>");
			br.append("</div></td><td colspan='3' width='95%' class='TITLE'>Pending Demand Details</td></tr></table>");	
			
		if (ws != null) 
		{
			if(ws.size() != 0)
			{
			 int actualFetchRecord = ws.size();
		
	         if(totalFetchRecord != actualFetchRecord)
			 {
				totalFetchRecord = actualFetchRecord;
				totalRecordsToManipulate = actualFetchRecord;
			 }
			 int totalLayer = totalRecordsToManipulate / REC_PER_PAGE;
			 int reminder = totalRecordsToManipulate % REC_PER_PAGE;
			 if (reminder > 0)
				totalLayer = totalLayer + 1;
			 
			    br.append("<input type='hidden' name='TotalLayer'  value='"+totalLayer+"'>");
			    br.append("<input type='hidden' name='RecordShowOnPage'  value='"+REC_PER_PAGE+"'>");
			    br.append("<div id='prevTechEntryDtlId' style='display:none;'>");
			 br.append("<table class='TABLEWIDTH' align='center'  border='0' cellpadding='1px' cellspacing ='1px'>");
			 br.append("<tr>");
			 br.append("<td class='LABEL'>");
			 		 
			 
			 for (int i = 1; i <= totalLayer; i++)
			 {
				br.append("<a name='pg' id='pg" + i + "'  STYLE='cursor:pointer;cursor:pointer;' class='pg-normal' title='Record Indexing' onClick='GetIndex(\""+i+"\",\""+totalLayer+"\")'>" + (i+start-1)
						+ "</a>|&nbsp;");
			 }
			br.append("</td></tr>");
			br.append("</table>");			
			
				
				br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding='1px'>");
				//br.append("<tr class='TITLE'>");
				//br.append("<td colspan='4'></td></tr>");
				br.append("<tr>");
				
				br.append("<td CLASS='multiLabel' colspan='1' width='5%'></td>");
				br.append("<td CLASS='multiLabel' colspan='1' width='20%'>Indenting Store</td>");
				br.append("<td CLASS='multiLabel' colspan='1' width='20%'>Indent Date</td>");
				br.append("<td CLASS='multiLabel' colspan='1' width='20%'>Indent No.</td>");
				br.append("<td CLASS='multiLabel' colspan='1' width='20%'>Indent Status</td>");
				br.append("<td CLASS='multiLabel' colspan='1' width='15%'>Last Issue Date</td>");
				br.append("</tr>");
				br.append("</table>");
		    for (int i = 1; i <= totalLayer; i++) 
		    {
			 if (i <= totalLayer) 
			 {
				 
				if (i == 1) 
				{
					br.append("<div id='DivId" +i+ "' style='display:block'>");
				} 
				else
				{
					br.append("<div id='DivId" +i+ "' style='display:none'>");
				}
				
				br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
				for (int j = 0; j < REC_PER_PAGE; j++) 
				{
					if(ws.next())
					{
						
						/* Value Pass in Web Row Set
				    	   1. C.HSTNUM_REQ_NO 
				    	   2. C.HSTNUM_STORE_ID , 
			               3. C.GNUM_HOSPITAL_CODE,
			               4. count_urgent 
			               5. c.URGENT_FLG 
			               6. C.HSTNUM_REQ_NO 
			               7. C.REQ_DATE 
			               8. C.RAISING_STORE 
			               9. C.CATEGORY 
			               10.C.LST_ISSUE_DATE  
				    	 */ 
						
						if(ws.getString(5)==null ||ws.getString(5).equals(""))
						{
							strUrgentFlg = "---";
						}
						else
						{
							strUrgentFlg = ws.getString(5);
						}
						if(ws.getString(10)==null||ws.getString(10).equals(""))
						{
							strLastIssueDate = "---";
						}
						else
						{
							strLastIssueDate =ws.getString(10);
						}
						String strCheckHidValue = ws.getString(1)+"^"+ws.getString(2)+"^"+ws.getString(3)+"^"+ws.getString(3)+"^"+strUrgentFlg+"^"+ws.getString(6)+"^"+ws.getString(7)+"^"+ws.getString(8)+"^"+ws.getString(9)+"^"+strLastIssueDate;	
						//System.out.println("strCheckHidValue:::::"+strCheckHidValue);
						br.append("<tr>");
						br.append("<input type='hidden' name='demandFlg'  value='1'>");
						br.append("<input type='hidden' name='strCheckHidValue' id='strCheckHidValue"+count+"' value='"+strCheckHidValue+"'>");
						br.append("<td class='multiControl' colspan='1' width='5%'>");
						br.append("<input type='checkbox' name='checkid' id='checkid"+count+"' value='0' onClick='chkBoxClick(this,\""+count+"\");'> </td>");
						
						
						br.append("<td class='multiControl' colspan='1' width='20%'>"+ws.getString(8)+"</td>");
						br.append("<td class='multiControl' colspan='1' width='20%'>"+ws.getString(7)+"</td>");
						br.append("<td class='multiControl' colspan='1' width='20%'>"+ws.getString(1)+"</td>");
						br.append("<td class='multiControl' colspan='1' width='20%'>"+strUrgentFlg+"</td>");
						br.append("<td class='multiControl' colspan='1' width='15%'>"+strLastIssueDate+"</td>");
						
						br.append("</tr>");
						count++ ;
					}else{
						break;
					}
				}
					br.append("</table>");
					br.append("</div>");

			} 
			else 
			{
				br.append("<div id='DivId" + i+ "' style='display:none'>");
				
				br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
				for (int k = 0; k < reminder; k++) 
				{
					ws.next();
					
					/* Value Pass in Web Row Set
			    	   1. C.HSTNUM_REQ_NO 
			    	   2. C.HSTNUM_STORE_ID , 
		               3. C.GNUM_HOSPITAL_CODE,
		               4. count_urgent 
		               5. c.URGENT_FLG 
		               6. C.HSTNUM_REQ_NO 
		               7. C.REQ_DATE 
		               8. C.RAISING_STORE 
		               9. C.CATEGORY 
		               10.C.LST_ISSUE_DATE  
			    	 */ 
					
					if(ws.getString(5)==null ||ws.getString(5).equals(""))
					{
						strUrgentFlg = "---";
					}
					else
					{
						strUrgentFlg = ws.getString(5);
					}
					if(ws.getString(10)==null||ws.getString(10).equals(""))
					{
						strLastIssueDate = "---";
					}
					else
					{
						strLastIssueDate =ws.getString(10);
					}
					
					String strCheckHidValue = ws.getString(1)+"^"+ws.getString(2)+"^"+ws.getString(3)+"^"+ws.getString(3)+"^"+strUrgentFlg+"^"+ws.getString(6)+"^"+ws.getString(7)+"^"+ws.getString(8)+"^"+ws.getString(9)+"^"+strLastIssueDate;		

					
					br.append("<tr>");
					br.append("<input type='hidden' name='demandFlg'  value='1'>");
					br.append("<input type='hidden' name='strCheckHidValue' id='strCheckHidValue"+count+"' value='"+strCheckHidValue+"'>");
					br.append("<td class='multiControl' colspan='1' width='5%'>");
					br.append("<input type='checkbox' name='checkid' id='checkid"+count+"' value='0' onClick='chkBoxClick(this,\""+count+"\");'> </td>");
					
					
					br.append("<td class='multiControl' colspan='1' width='20%'>"+ws.getString(8)+"</td>");
					br.append("<td class='multiControl' colspan='1' width='20%'>"+ws.getString(7)+"</td>");
					br.append("<td class='multiControl' colspan='1' width='20%'>"+ws.getString(1)+"</td>");
					br.append("<td class='multiControl' colspan='1' width='20%'>"+strUrgentFlg+"</td>");
					br.append("<td class='multiControl' colspan='1' width='15%'>"+strLastIssueDate+"</td>");
					
					br.append("</tr>");
					count++ ;
				}
				br.append("</table>");
				br.append("</div>");
				}
		   	}
			  br.append("</div>");
			}
			else
			{
				 
				 br.append("<div id='prevTechEntryDtlId' style='display:none;'>");
				 br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding='1px'>");
				 br.append("<tr>");
				 br.append("<td CLASS='multiLabel' colspan='1' width='5%'></td>");
					br.append("<td CLASS='multiLabel' colspan='1' width='20%'>Indenting Store</td>");
					br.append("<td CLASS='multiLabel' colspan='1' width='20%'>Indent Date</td>");
					br.append("<td CLASS='multiLabel' colspan='1' width='20%'>Indent No.</td>");
					br.append("<td CLASS='multiLabel' colspan='1' width='20%'>Indent Status</td>");
					br.append("<td CLASS='multiLabel' colspan='1' width='15%'>Last Issue Date</td>");
				 
				 br.append("</tr>");
				 br.append("<input type='hidden' name='demandFlg'  value='0'>");
	           	 br.append("<tr>");  
	           	 br.append("<td class='multiControl' colspan='6'><font color='red'><b>No Detail(s) Available</b></font></td></tr>");
	           	 br.append("</table>");
	           	 br.append("</div>");
					
			   }
			} 
	     }
		
	catch (Exception e) {	
		 
			throw new Exception("OffLineIssueIndentTransHLP.getPendingDemands()->"+e.getMessage());
		}
		return br.toString();
	}
	
	/**
	 * This method is used to show item Details on view PAGE
	 * 
	 * @param wb
	 * @param strItemCategory
	 * @param strHospitalCode
	 * @return
	 */
	public static String getViewItemDetailsTwo(String strItemCategoryNo,String strHospitalCode, WebRowSet wb, String strStoreId,WebRowSet pendingDemandDtlWs,String strAvlBudget,String strBudgetFlag)throws SQLException 
	{
		StringBuffer br = new StringBuffer();
		String strRaisingStoreID="";

		try 
		{
			
			
			if(pendingDemandDtlWs.size()!= 0)
			{	
				while (pendingDemandDtlWs.next()) 
				{
					/*
					vo.setStrIndentNo(ws.getString(1));
					vo.setStrIndentDate(ws.getString(2));
					vo.setStrIndentType(ws.getString(3));
					vo.setStrItemCategory(ws.getString(4));
					vo.setStrRaisingStoreId(ws.getString(5));
					vo.setStrRaisingStoreName(ws.getString(6));
					vo.setStrItemCategoryNo(ws.getString(7));
					vo.setStrReqStatusName(ws.getString(8));
					Indent Period Value
					*/
					strRaisingStoreID = pendingDemandDtlWs.getString(5);
					br.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px' border='0'>");
					br.append("<tr class='TITLE'>");
					br.append("<td colspan='4'>Existing Demand Issue Details</td></tr>");
					br.append("<tr>");
					br.append("<td width='25%' class='LABEL'>Indent Type</td>");
					br.append("<td width='25%' class='CONTROL'>Routine</td>");
					br.append("<td width='25%' class='LABEL'>Indent Status</td>");
					br.append("<td width='25%' class='CONTROL'>"+pendingDemandDtlWs.getString(8)+"</td>");
					br.append("</tr>");
					br.append("<tr>");
					br.append("<td width='25%' class='LABEL'>Indent Period</td>");
					br.append("<td width='25%' class='CONTROL'>"+pendingDemandDtlWs.getString(9)+"</td>");
					br.append("<td class='LABEL' width='25%'>Indent No.</td>");
					br.append("<td width='25%' class='CONTROL'>"+pendingDemandDtlWs.getString(1)+"</td>");
					br.append("<tr>");
					br.append("<td width='25%' class='LABEL'>Indent Date</td>");
				 	br.append("<td width='25%' class='CONTROL' >"+ pendingDemandDtlWs.getString(2) + "</td>");
				 	if(strBudgetFlag.equals("1"))
					{
					br.append("<td width='25%' class='LABEL'>Budget Avalaible</td>");
					br.append("<td width='25%' class='CONTROL' style='color:blue;'><a STYLE='cursor:pointer;cursor:pointer;color:blue'  onClick='avlBudgetDtl(this);' TITLE='Click Here Get Budget Details' >"+strAvlBudget+"</a></td>");
					}
				 	else
				 	{
				 		br.append("<td width='25%' class='CONTROL'></td>");
						br.append("<td width='25%' class='CONTROL'></td>");

				 	}	
					br.append("</tr>");
					br.append("</table>");
				}
			}
			else
			{
				strRaisingStoreID = "0";
			}
			
			

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "ERROR";

		}

		return br.toString();
	}
	
	
		
	/**
	 * This method is used to show item Details on view PAGE
	 * 
	 * @param wb
	 * @param strItemCategory
	 * @param strHospitalCode
	 * @return
	 */
	public static String getViewItemDetails(String strItemCategoryNo,String strHospitalCode, WebRowSet wb, String strStoreId,WebRowSet pendingDemandDtlWs,String strAvlBudget,String strBudgetFlag)throws SQLException 
	{
		StringBuffer br = new StringBuffer();
		OfflineIssueIndentTransBO bo = null;
		OfflineIssueIndentTransVO vo = null;
		HisUtil hisutil = null;

		String strUnitComboValues = "";

		String strHiddenId = ""; // item id^brand
		// id^strItemCategory^strStoreId
		//String strItemName = "";
		String strBrandName = "";
		String strAvlQty = "";
		String strBalQty = "";
		String strSancUnitId = "";
		String strUnitName = "";
		String strRaisingStoreID="";
		String strBatchNo="";

		String[] temp = null; 
		int i = 0;

		try 
		{
			hisutil = new HisUtil("mms", "IssueDeskTransHLP");
			bo = new OfflineIssueIndentTransBO();
			vo = new OfflineIssueIndentTransVO();

			
			if(pendingDemandDtlWs.size()!= 0)
			{	
				while (pendingDemandDtlWs.next()) 
				{
					/*
					vo.setStrIndentNo(ws.getString(1));
					vo.setStrIndentDate(ws.getString(2));
					vo.setStrIndentType(ws.getString(3));
					vo.setStrItemCategory(ws.getString(4));
					vo.setStrRaisingStoreId(ws.getString(5));
					vo.setStrRaisingStoreName(ws.getString(6));
					vo.setStrItemCategoryNo(ws.getString(7));
					vo.setStrReqStatusName(ws.getString(8));
					Indent Period Value
					*/
					strRaisingStoreID = pendingDemandDtlWs.getString(5);
					br.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px' border='0'>");
					br.append("<tr>");
					br.append("<td width='25%' class='LABEL'>Indent Type</td>");
					br.append("<td width='25%' class='CONTROL'>Routine</td>");
					br.append("<td width='25%' class='LABEL'>Indent Status</td>");
					br.append("<td width='25%' class='CONTROL'>"+pendingDemandDtlWs.getString(8)+"</td>");
					br.append("</tr>");
					br.append("<tr>");
					br.append("<td width='25%' class='LABEL'>Indent Period</td>");
					br.append("<td width='25%' class='CONTROL'>"+pendingDemandDtlWs.getString(9)+"</td>");
					br.append("<td class='LABEL' width='25%'>Indent No.</td>");
					br.append("<td width='25%' class='CONTROL'>"+pendingDemandDtlWs.getString(1)+"</td>");
					br.append("<tr>");
					br.append("<td width='25%' class='LABEL'>Indent Date</td>");
				 	br.append("<td width='25%' class='CONTROL' >"+ pendingDemandDtlWs.getString(2) + "</td>");
				 	if(strBudgetFlag.equals("1"))
					{
					br.append("<td width='25%' class='LABEL'>Budget Avalaible</td>");
					br.append("<td width='25%' class='CONTROL' style='color:blue;'><a STYLE='cursor:pointer;cursor:pointer;color:blue'  onClick='avlBudgetDtl(this);' TITLE='Click Here Get Budget Details' >"+strAvlBudget+"</a></td>");
					}
				 	else
				 	{
				 		br.append("<td width='25%' class='CONTROL'></td>");
						br.append("<td width='25%' class='CONTROL'></td>");

				 	}	
					br.append("</tr>");
					br.append("</table>");
				}
			}
			else
			{
				strRaisingStoreID = "0";
			}
			
			
			
			vo.setStrIndentingStoreID(strRaisingStoreID);
			vo.setStrHospitalCode(strHospitalCode);
			if (wb.size() != 0) 
			{
				 if(strBudgetFlag.equals("1"))
				 {	
						br.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px' border='0'>");
						br.append("<tr>");
						br.append("<td class='multiLabel' width='28%'>Drug Name</td>");
						br.append("<td class='multiLabel' width='10%'>Avl. Qty.</td>");
						br.append("<td class='multiLabel' width='10%'>Balance Qty.</td>");
						br.append("<td class='multiLabel' width='12%'>Issue Qty</td>");
						br.append("<td class='multiLabel' width='10%'>Unit</td>");
						br.append("<td class='multiLabel' width='10%'>Batch</td>");
						br.append("<td class='multiLabel' width='12%'>Cost</td>");
						
						br.append("</tr>");
				 }
				 else
				 {
					    br.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px' border='0'>");
						br.append("<tr>");
						br.append("<td class='multiLabel' width='32%'>Drug Name</td>");
						br.append("<td class='multiLabel' width='10%'>Avl. Qty.</td>");
						br.append("<td class='multiLabel' width='10%'>Balance Qty.</td>");
						br.append("<td class='multiLabel' width='14%'>Issue Qty</td>");
						br.append("<td class='multiLabel' width='12%'>Unit</td>");
						br.append("<td class='multiLabel' width='14%'>Batch</td>");						
						br.append("</tr>");
					 
				 }	 
				
				while (wb.next()) 
				{
					
				 if(strBudgetFlag.equals("1"))
				 {		
					
					strHiddenId = wb.getString(1); // item id^brand id
		//			strItemName = wb.getString(2);
					strBrandName = wb.getString(3);
					strAvlQty = wb.getString(4);
					//System.out.println("strAvlQty"+strAvlQty);
					temp = strAvlQty.replace("@", "#").split("#");
					temp = temp[0].replace("^", "#").split("#");
					strBalQty = wb.getString(5);
					strSancUnitId = wb.getString(6);
					strUnitName = wb.getString(7);
								
					
					
					
					br.append("<input type='hidden' name='strUnitName' id='strUnitName" + i+ "' value='"+strUnitName+"'>");
					br.append("<input type='hidden' name='strReqQty'   id='strReqQty" + i+ "' value='"+strBalQty+"'>");
					br.append("<input type='hidden' name='strAvlQty'   id='strAvlQty" + i+ "' value='"+temp[0]+"'>");
					br.append("<input type='hidden' name='strRate'     id='strRate" + i+ "' value='"+wb.getString(12)+"'>");
										
                    /*
                      1. Hidden value ::: HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID||'^'||
                                          HSTNUM_RESERVED_FLAG||'^'||  HSTNUM_GROUP_ID||'^'||
                                          HSTNUM_SUBGROUP_ID||'^'||HSTNUM_CONSUMABLE_FLAG
                      2. Item Name
                      3. Item Brand Name
                      4. Avl Qty.
                      5. Bal Qty.
                      6. Sanc Qty
                      7. Snac Qty Unit
                      8. Snc - Issue 
                      9. Req Qty / Sanc Qty
                     10. Re-Order Flag
                     11. Unit Id
                     12. Rate
                     13. Batch No
                     */
					
					
					vo.setStrSancUnitId(strSancUnitId);

					bo.getUnitCombo(vo);
					

					if (vo.getStrMsgType().equals("1")) 
					{
						throw new Exception(vo.getStrMsgString());
					}

					if (vo.getUnitComboWS() != null	&& vo.getUnitComboWS().size() > 0) 
					{
						strUnitComboValues = hisutil.getOptionValue(vo.getUnitComboWS(), wb.getString(11), "0^Select", true);
					} 
					else 
					{
						strUnitComboValues = "<option value='0'>Select</option>";
					}
					
					if(wb.getString(13).equals("-1"))
					{
						strBatchNo = "---";
					}
					else
					{
						strBatchNo = wb.getString(13);
					}

					/*
					 * br.append("<TR>"); br .append(" <input type='hidden'
					 * name='strItemDetailsChk' id='strItemDetailsChk" + i + "'
					 * onclick='ClickCheckBox(this,\"" + i + "\");' value= '" +
					 * strHiddenId + "^" + strItemCategoryNo + "^" + strStoreId + "' />
					 * "); br.append(" <input type='hidden' name='flag'
					 * id='flag"+i+"' value=" + "0" + " >");
					 */

					br.append("<TD WIDTH='28%' CLASS='multiControl'  style='color:blue;'>"+strBrandName+"</TD>");

					br.append("<TD WIDTH='10%' CLASS='multiControl' >"+ temp[0] + "</TD>");
					br.append("<TD WIDTH='10%' CLASS='multiControl' >"+ strBalQty + " " + strUnitName + "</TD>");
					br.append("<TD WIDTH='12%' CLASS='multiControl' ><input type='text' class='txtFldMin' name='strPendIssueQty' id='strPendIssueQty" + i+ "'  value=" + "0" + "  onkeyup='return issueQtyValidationTwo(\""+i+"\");' onkeypress='return validateData(event,7);' onblur='setDefaultValue(this);' ></TD>");
					br.append("<TD WIDTH='10%' CLASS='multiControl' >");
					br.append("<select name='strPendUnit' id='strPendUnit"+i+ "' class='comboMin'>"+strUnitComboValues+"</select>");
					br.append("<TD WIDTH='10%' CLASS='multiControl' >"+ strBatchNo + "</TD>");
					br.append("<TD WIDTH='12%' CLASS='multiControl' ><input type='text' disabled='disabled' class='txtFldMin' name='strPendQtyCost' id='strPendQtyCost" + i+ "'  value=" + "0.00" + "></TD>");
					
					br.append("</TR>");
					i++;
				  }
				  else
				  {
					  
						strHiddenId = wb.getString(1); // item id^brand id
						//			strItemName = wb.getString(2);
									strBrandName = wb.getString(3);
									strAvlQty = wb.getString(4);
									//System.out.println("strAvlQty"+strAvlQty);
									temp = strAvlQty.replace("@", "#").split("#");
									temp = temp[0].replace("^", "#").split("#");
									strBalQty     = wb.getString(5);
									strSancUnitId = wb.getString(6);
									strUnitName   = wb.getString(7);						
									
									
				                    /*
				                      1. Hidden value ::: HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID||'^'||
				                                          HSTNUM_RESERVED_FLAG||'^'||  HSTNUM_GROUP_ID||'^'||
				                                          HSTNUM_SUBGROUP_ID||'^'||HSTNUM_CONSUMABLE_FLAG
				                      2. Item Name
				                      3. Item Brand Name
				                      4. Avl Qty.
				                      5. Bal Qty.
				                      6. Sanc Qty
				                      7. Snac Qty Unit
				                      8. Snc - Issue 
				                      9. Req Qty / Sanc Qty
				                     10. Re-Order Flag
				                     11. Unit Id
				                     12. Rate
				                     13. Batch No
				                     */
									
									
									vo.setStrSancUnitId(strSancUnitId);

									bo.getUnitCombo(vo);
									

									if (vo.getStrMsgType().equals("1")) 
									{
										throw new Exception(vo.getStrMsgString());
									}

									if (vo.getUnitComboWS() != null	&& vo.getUnitComboWS().size() > 0) 
									{
										strUnitComboValues = hisutil.getOptionValue(vo.getUnitComboWS(), wb.getString(11), "0^Select", true);
									} 
									else 
									{
										strUnitComboValues = "<option value='0'>Select</option>";
									}
									
									if(wb.getString(13).equals("-1"))
									{
										strBatchNo = "---";
									}
									else
									{
										strBatchNo = wb.getString(13);
									}
								

									br.append("<TD WIDTH='32%' CLASS='multiControl'  style='color:blue;'>"+strBrandName+"</TD>");

									br.append("<TD WIDTH='10%' CLASS='multiControl' >"+ temp[0] + "</TD>");
									br.append("<TD WIDTH='10%' CLASS='multiControl' >"+ strBalQty + " " + strUnitName + "</TD>");
									br.append("<TD WIDTH='14%' CLASS='multiControl' ><input type='text' class='txtFldMin' name='strPendIssueQty' id='strPendIssueQty" + i+ "'  value=" + "0" + " ></TD>");
									br.append("<TD WIDTH='12%' CLASS='multiControl' >");
									br.append("<select name='strPendUnit' id='strPendUnit"+i+ "' class='comboMin'>"+strUnitComboValues+"</select>");
									br.append("<TD WIDTH='14%' CLASS='multiControl' >"+ strBatchNo + "</TD>");
									br.append("<input type='hidden' disabled='disabled' class='txtFldMin' name='strPendQtyCost' id='strPendQtyCost" + i+ "'  value=" + "0.00" + ">");
									
									br.append("</TR>");
									i++;
					  
				  } 
				}

				br.append("</table> ");
				if(strBudgetFlag.equals("1"))
				{
					br.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
					br.append("<tr> ");
					br.append("<td width='90%' class='LABEL'>Total Cost(Rs):</td> ");
					br.append("<td width='13%' class='CONTROL' style='color: red; font-weight: bold'> ");
					br.append("<input type='text' name='strTotalPendCostDivId' class='txtFldNormal'  value='0.00'  disabled='disabled' id='strTotalPendCostDivId' >");
					br.append("<input type='hidden' name='strApproxPendAmt' value='0.00'></td> ");
					br.append("</tr> ");
					br.append("</table> ");
				}	
				
				
			} 
			else 
			{
				br
						.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px'>");
				br.append("<TR>");
				br.append("<TD  CLASS='multiControl' colspan='3'>"
						+ "No Record Found" + "</TD>");
				br.append("</TR>");
				br.append("</table> ");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "ERROR";

		}

		return br.toString();
	}
	
	
	
	/**
	 * This method is used to show item Details on ISSUE PAGE
	 * 
	 * @param wb
	 * @param strItemCategory
	 * @param strHospitalCode
	 * @return
	 */
	public static String getExistingItemDetails(String strItemCategoryNo,
			String strHospitalCode, WebRowSet wb, String strRaisingStoreId,String strBudgetFlg)
			throws SQLException {
		StringBuffer br = new StringBuffer();

		IssueDeskTransBO bo = null;
		IssueDeskTransVO vo = null;
		HisUtil hisutil = null;

		String strUnitComboValues = "";

		String strHiddenId = ""; // item id^brandid^RESERVED FLAG^GROUPID^SUBGROUP ID^CONSUMABLE FLAG
									// ^strItemCategory^strRaisingStoreId
	//	String strItemName = "";
		String strBrandName = "";
		String strAvlQty = "";
		String strAvlQtyWithUnitId = "";
		String strAvlQtyBaseVal = "";
		String strBalQty = "";
		String strSancUnitId = "";
		String strSancUnitName = "";
		String strBalQtyBaseVal = "";
		String strReqSancQty;
	//	String strRateBaseVal = "";
	//	String strRate = "";
	//	String strRateUnitId = "";
		String[] temp = null;
		String strReOrderFlg;
		String strApplyClass;
		String strCompSancUnit;

		int i = 0;

		try {
			hisutil = new HisUtil("mms", "IssueDeskTransHLP");
			bo = new IssueDeskTransBO();
			vo = new IssueDeskTransVO();

			vo.setStrHospitalCode(strHospitalCode);
			if (wb.size() != 0) 
			{

				br.append("<table class='TABLEWIDTH' bgcolor='#6097BC' align='center'cellpadding='1px' cellspacing='1px'>");
				
				while (wb.next()) 
				{
					strHiddenId = wb.getString(1); // item id^brand id
			//		strItemName = wb.getString(2);
					strBrandName = wb.getString(3);
					strAvlQty = wb.getString(4);
					strBalQty = wb.getString(5);
					if (wb.getString(6) == null) 
					{
						strSancUnitId = "0";
					}
					else 
					{
						strSancUnitId = wb.getString(6);
					}
					strSancUnitName  = wb.getString(7);
					strBalQtyBaseVal = wb.getString(8);
					strReqSancQty    = wb.getString(9);
					
					strCompSancUnit  = wb.getString(11);  // Adding in Change Request 28-July-2011
									
					
					if (strBalQty.equals("0")) {
						strSancUnitName = " ";
					}
					
					
					/*
					 *  Change Color for Re-Order Level
					   System.out.println("Inside Issue Desk Trans HLP:::::"+wb.getString(10));
					   
					   if(wb.getString(10).equals("0")) 
				       {
				    	 strApplyClass = "Approved";				    	   
				       }
				       else
				       {
				    	 strApplyClass = "NotApproved";    				    	   
				       }
					*/

					//strBalQty = HisUtil.getAmountWithDecimal(strBalQty, 2); // By Amit Kr Date 23-Feb-2011
					//System.out.println("strHiddenId-" + strHiddenId);
					//System.out.println("strItemName-" + strItemName);
					//System.out.println("strBrandName-" + strBrandName);
					//System.out.println("strAvlQty-" + strAvlQty);
					//System.out.println("strBalQty-" + strBalQty);
					//System.out.println("strSancUnitId-" + strSancUnitId);
					//System.out.println("strSancUnitName-" + strSancUnitName);
					//System.out.println("strBalQtyBaseVal-" + strBalQtyBaseVal);
					//System.out.println("strItemCategory-" + strItemCategoryNo);
					//System.out.println("strRate-" + strRate);
					//System.out.println("strRateUnitId-" + strRateUnitId);
					temp = strAvlQty.replace("^", "#").split("#");

					strAvlQty = temp[0];
					strAvlQtyWithUnitId = temp[1];
					strAvlQtyBaseVal = temp[2];
					vo.setStrSancUnitId(strSancUnitId);
					// Calling BO Method
					bo.getUnitCombo(vo);
                     
					if (vo.getStrMsgType().equals("1")) 
					{
						throw new Exception(vo.getStrMsgString());
					}

					if (vo.getUnitComboWS() != null	&& vo.getUnitComboWS().size() > 0) 
					{
					
						strUnitComboValues = hisutil.getOptionValue(vo
								.getUnitComboWS(), strCompSancUnit, "0^Select", true);
						vo.getUnitComboWS().beforeFirst(); // needed bcoz
															// sometimes it
															// gives invalid
															// cursor position
															// when there is
															// value in heap due
															// to loop
						
					} 
					else 
					{

						strUnitComboValues = "<option value='0'>Select Value</option>";
					}
					
					if(strBudgetFlg.equals("0"))
					{	
							br.append("<TR>");
							
							
		                    
							
							br.append("<TD WIDTH='32%' id='td1" + i + "' CLASS='multiControl'  >");
							br.append(" <input type='hidden' name='strItemDetailsChk' id='strItemDetailsChk"
											+ i
											+ "' value= '"
											+ strHiddenId
											+ "^"
											+ strItemCategoryNo
											+ "^"
											+ strRaisingStoreId + "' /> ");
							br.append(" <input type='hidden' name='flag' id='flag" + i + "' value=" + "0" + " />");
							
							br.append(" <input type='hidden' name='reOrderFlag' id='reOrderFlag" + i + "' value=" + wb.getString(10) + " />");
		
							
							br.append("<a STYLE='cursor:pointer;cursor:pointer;color:blue' title='Batch Wise Item Details' onClick='showPopUp(this,\""
											+ i + "\");'>" + strBrandName + "</a></TD>");
							
							br.append("<TD WIDTH='10%' id='td2" + i + "' CLASS='multiControl'  >"
											+ strAvlQty
											+ "<input type='hidden' name='strAvlQty' id='strAvlQty"
											+ i
											+ "' value='"
											+ strAvlQtyWithUnitId
											+ "' /><input type='hidden' name='strAvlQtyBaseVal' id='strAvlQtyBaseVal"
											+ i + "' value='" + strAvlQtyBaseVal
											+ "' /></TD>");
							
							br.append("<input type='hidden' name='strBalQty' id='strBalQty"
											+ i
											+ "' value='"
											+ strBalQty
											+ "' /><input type='hidden' name='strSancUnitId' id='strSancUnitId"
											+ i
											+ "' value='"
											+ strSancUnitId
											+ "' /> <input type='hidden' name='strBalQtyBaseVal' id='strBalQtyBaseVal"
											+ i + "' value='" + strBalQtyBaseVal
											+ "' />");
		                    
							br.append("<TD WIDTH='14%' id='td3" + i + "' CLASS='multiControl'  >"+strReqSancQty+"</TD>");
							
		                    
							
							br.append("<TD WIDTH='10%' id='td4" + i + "' CLASS='multiControl' ><input type='text' maxlength='8' onkeypress='return QtyValidation("
											+ i
											+ ");' onkeyup='return QtyValidation("
											+ i
											+ ");' name='strIssueQty' id='strIssueQty"
											+ i
											+ "' class='txtFldMin' value='' > </TD>");
		                   
							br.append("<TD WIDTH='10%' id='td5" + i + "' CLASS='multiControl'  ><Select name='strIssueUnitId' onChange='return QtyValidation("
											+ i
											+ ");' id='strIssueUnitId"
											+ i
											+ "' >"
											+ strUnitComboValues + "</select></TD>");
		                    					
							
		                   
							br.append("<TD WIDTH='4%' id='td6" + i + "' CLASS='multiControl'  ><input type='hidden' name='stockDtlsId' id='stockDtlsId"
											+ i
											+ "' value='' /> <a STYLE='cursor:pointer;cursor:pointer;color:blue'  onClick='callStockDetails(this,\""
											+ i + "\");' TITLE='Click Here For Item Preferences' >#</TD>");
							
							
							br.append("</TR>");
							br.append("</div>");
							i++;
				    }
					else
					{
						br.append("<TR>");
						
						               
						
						br.append("<TD WIDTH='27%' id='td1" + i + "' CLASS='multiControl'  >");
						br.append(" <input type='hidden' name='strItemDetailsChk' id='strItemDetailsChk"
										+ i
										+ "' value= '"
										+ strHiddenId
										+ "^"
										+ strItemCategoryNo
										+ "^"
										+ strRaisingStoreId + "' /> ");
						br.append(" <input type='hidden' name='flag' id='flag" + i + "' value=" + "0" + " />");
						
						br.append(" <input type='hidden' name='reOrderFlag' id='reOrderFlag" + i + "' value=" + wb.getString(10) + " />");
	
						
						br.append("<a STYLE='cursor:pointer;cursor:pointer;color:blue' title='Batch Wise Item Details' onClick='showPopUp(this,\""
										+ i + "\");'>" + strBrandName + "</a></TD>");
						
						br.append("<TD WIDTH='7%' id='td2" + i + "' CLASS='multiControl'  >"
										+ strAvlQty
										+ "<input type='hidden' name='strAvlQty' id='strAvlQty"
										+ i
										+ "' value='"
										+ strAvlQtyWithUnitId
										+ "' /><input type='hidden' name='strAvlQtyBaseVal' id='strAvlQtyBaseVal"
										+ i + "' value='" + strAvlQtyBaseVal
										+ "' /></TD>");
						
						br.append("<input type='hidden' name='strBalQty' id='strBalQty"
										+ i
										+ "' value='"
										+ strBalQty
										+ "' /><input type='hidden' name='strSancUnitId' id='strSancUnitId"
										+ i
										+ "' value='"
										+ strSancUnitId
										+ "' /> <input type='hidden' name='strBalQtyBaseVal' id='strBalQtyBaseVal"
										+ i + "' value='" + strBalQtyBaseVal
										+ "' />");
	                    
						br.append("<TD WIDTH='12%' id='td3" + i + "' CLASS='multiControl'  >"+strReqSancQty+"</TD>");
						
	                    
						
						br.append("<TD WIDTH='10%' id='td4" + i + "' CLASS='multiControl' ><input type='text' maxlength='8' onkeypress='return QtyValidation("
										+ i
										+ ");' onkeyup='return QtyValidation("
										+ i
										+ ");' name='strIssueQty' id='strIssueQty"
										+ i
										+ "' class='txtFldMin' value='' > </TD>");
	                   
						br.append("<TD WIDTH='10%' id='td5" + i + "' CLASS='multiControl'  ><Select name='strIssueUnitId' onChange='return QtyValidation("
										+ i
										+ ");' id='strIssueUnitId"
										+ i
										+ "' >"
										+ strUnitComboValues + "</select></TD>");
						               					
						
	                   
						br.append("<TD WIDTH='10%' id='td7" + i + "' CLASS='multiControl'><input type='text' disabled='disabled' class='txtFldNormal'  value='0.00' name='finalCostDivId' id='finalCostDivId"+i+"' ></TD>");
						br.append("<input type='hidden' name='strFinalCost' id='strFinalCost"	+ i	+ "' value='0.00' />");
						
						br.append("<TD WIDTH='4%' id='td8" + i + "' CLASS='multiControl'  ><input type='hidden' name='stockDtlsId' id='stockDtlsId"
										+ i
										+ "' value='' /> <a STYLE='cursor:pointer;cursor:pointer;color:blue'  onClick='callStockDetails(this,\""
										+ i + "\");' TITLE='Click Here For Item Preferences' >#</TD>");
						
						
						br.append("</TR>");
						br.append("</div>");
						i++;
					}	
					
				}

				br.append("</table> ");
				if(strBudgetFlg.equals("1"))
				{
					br.append("<table class='TABLEWIDTH' align='center' cellpadding='1' cellspacing='1'>");
					br.append("<tr>");
					br.append("<td width='85%' class='LABEL'>Total Approx Cost(Rs):</td>");
					br.append("<td width='15%' class='CONTROL' style='color: red; font-weight: bold' align='center'>");
					br.append("<input type='text' style='color: red; font-weight: bold'  disabled='disabled' class='txtFldNormal'  value='0.00' name='strApproxAmtDiv' id='strApproxAmtDiv' >");
					br.append("<input type='hidden' name='strFinalApproxAmtDiv'></td><td width='4%' class='CONTROL'></td>");
					br.append("</tr>");
					br.append("</table>");	
				}
				
				
				
			} 
			else 
			{
				br.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px'>");
				br.append("<TR>");
				br.append("<TD  CLASS='multiControl' colspan='8'>"
						+ "No Record Found" + "</TD>");
				br.append("</TR>");
				br.append("</table> ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";

		}
        //System.out.println("O/P String:::::"+br.toString());
		return br.toString();
	}

	
	
	
	
	/**
	 * This method is used to show a PopUp (ON CLICK OF AN ITEM NAME)
	 * 
	 * @param wb
	 * @param index
	 * @return
	 */
	public static String getPopUpInfo(WebRowSet wb, String index,String issueNo) 
	{
		StringBuffer br = null;
		String strItemName = "";
		String strBatchNo = "";
		String strReqQty = "";
		String strIssueQty = "";

		try 
		{
			br  = new StringBuffer();
			

			br.append("<table width='400' align='center' cellpadding='1px' cellspacing='1px'>");
			br.append("<tr class='HEADER' align='left'><td style='color:blue;cursor:pointer;cursor:pointer;font-family: Arial, Helvetica, sans-serif;font-size:13px;'>Issue No.&nbsp;"+issueNo+"&nbsp;::::&nbsp;Item Details</td> ");
			br.append("<td align='right'><img style='cursor: pointer; '  src='../../hisglobal/images/popUp_cancel.JPG' title='To Close PopUp Window' align='middle' onclick='hide_popup_menu(\"IssueItempopup\");'> </td></tr>");
			br.append("</tr>");
			br.append("</table> ");
			
			br.append("<table width='400' align='center' bgcolor='black'  border='0'  cellspacing ='1px'>");
			br.append("<tr>");
			br.append("<td class='multiLabel' WIDTH='30%' >Item Name</td>");
			br.append("<td class='multiLabel' WIDTH='30%' >Batch No.</td>");
			br.append("<td class='multiLabel' WIDTH='20%' >Req Qty.</td>");
			br.append("<td class='multiLabel' WIDTH='20%' >Issued Qty.</td>");
			br.append("</tr>");
			br.append("</table> ");
			br.append("<table width='400' align='center' bgcolor='#6097BC'  border='0'  cellspacing ='1px'>");
			
			if (wb != null && wb.size() != 0) 
			{
				while (wb.next())

				{
					strItemName = wb.getString(7);
					strBatchNo  = wb.getString(8);
					strReqQty   = wb.getString(11);
					strIssueQty = wb.getString(9);

					if (strItemName == null || strItemName.equals("null"))
						strItemName = "-----";
					if (strBatchNo == null || strBatchNo.equals("null"))
						strBatchNo = "----";
					if (strReqQty == null|| strReqQty.equals("null"))
						strReqQty = "----";
					if (strIssueQty == null || strIssueQty.equals("null"))
						strIssueQty = "----";
					
					br.append("<tr>");
					br.append("<td WIDTH='30%' CLASS='multiControl' >"+ strItemName + "</td>");
					br.append("<td WIDTH='30%' CLASS='multiControl' >"+ strBatchNo  + "</td>");
					br.append("<td WIDTH='20%' CLASS='multiControl' >"+ strReqQty   + "</td>");
					br.append("<td WIDTH='20%' CLASS='multiControl' >"+ strIssueQty + "</td>");
					br.append("</tr>");
					i++;
				}
				
				br.append("</table>");
				br.append("<table width='400' align='center'  border='0'  cellspacing ='1px'>");
				br.append("<tr class='FOOTER'><td colspan='6'></td></tr>");
				br.append("</table>");
				
			} 
			else
			{
				br.append("<tr>");
				br.append("<td colspan='6'  CLASS='multiControl' ><DIV class='errMsg' align='center'> NO RECORD FOUND</div></td>");
				br.append("</tr>");
				br.append("</table> ");				
			}	
			
			br.append("@");
			br.append(index);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return "ERROR";

		}
		
		return br.toString();

	}
	

}
