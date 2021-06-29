package bmed.global.controller.data;

import javax.sql.rowset.WebRowSet;

public class StockDetailsDATA 
{
	
	public static String getStockDtl(WebRowSet viewValuesWS)throws Exception
	{

		StringBuffer br = new StringBuffer("");
		WebRowSet wb = null;
		int start = 1;
		final int REC_PER_PAGE = 5;
		final int PAGE_PER_BLOCK = 5;
		
		int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
		int totalRecordsToManipulate = totalFetchRecord - 1;
		try 
		{
			wb = viewValuesWS;
			
            /*			
         
			Value in WebRowSet----

			1. Store Name
			2. Item SL No
			3. Batch No
			4. Manufacter No
			5. HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
			   ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
               GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE		       
            */
			
			
			if (wb != null) 
			{
				if(wb.size() != 0)
				{
				 int actualFetchRecord = wb.size();
			
		         if(totalFetchRecord != actualFetchRecord)
				 {
					totalFetchRecord = actualFetchRecord;
					totalRecordsToManipulate = actualFetchRecord;
				 }
				 int totalPLayer = totalRecordsToManipulate / REC_PER_PAGE;
				 int reminder = totalRecordsToManipulate % REC_PER_PAGE;
				 if (reminder > 0)
					totalPLayer = totalPLayer + 1;
				 
				     
					    br.append("<input type='hidden' name='fetchRecord'  value='"+actualFetchRecord+"'>");
					    br.append("<input type='hidden' name='fetchRecord'  value='"+actualFetchRecord+"'>");
					    br.append("<input type='hidden' name='TotalLayer'  value='"+totalPLayer+"'>");
					    br.append("<his:ContentTag>");
					    br.append("<table class='TABLE_STYLE'>");
					    br.append("<tr class='FOOTER_TR'>");
					    br.append("<td align='center' style='text-align: center;'width:5%;'><img style='cursor: pointer;' id='imgStockDetails' src='/HBIMS/hisglobal/images/minus.gif'" +
					    "onclick='showOrHideStockDetails(this)' title='Hide'/>" +
					    "</td><td colspan='5' style='width: 95%'><div id='Id1' style='text-align:left'>Stock Details</div></td>");
					    br.append("</tr>");
						br.append("</table>");
						br.append("</his:ContentTag>");
					 
					 br.append("<div id='prevLnkDivId' style='display:block'>");
					 br.append("<his:ContentTag>");					 
                     br.append("<table class='TABLE_STYLE' align='center'  border='0' cellpadding='1px' cellspacing ='1px'>");
					 br.append("<tr>");
					 br.append("<td class='LABEL_TD'>");
					 		 
					 
					 for (int i = 1; i <= totalPLayer; i++)
					 {
						br.append("<a name='pg' id='pg" + i + "'  STYLE='cursor:pointer;cursor:pointer;' class='pg-normal' title='Record Indexing' onClick='GetIndex(\""+i+"\",\""+totalPLayer+"\")'>" + (i+start-1)
								+ "</a>|&nbsp;");
					 }
					br.append("</td></tr>");
					br.append("</table>");
					br.append("</his:ContentTag>");
					br.append("<his:ContentTag>");
					br.append("<table class='TABLE_STYLE'>");
					br.append("<td CLASS='LABEL_TD' style='text-align:center' width='5%'>#</td>");
					br.append("<td CLASS='LABEL_TD' style='text-align:center' width='40%'>Store Name</td>");
					br.append("<td CLASS='LABEL_TD' style='text-align:center' width='20%'>Item Serial No</td>");
					br.append("<td CLASS='LABEL_TD' style='text-align:center' width='12%'>Batch No</td>");
					br.append("<td CLASS='LABEL_TD' style='text-align:center' width='23%'>Manufac No.</td>");
					
					br.append("</tr>");
					
					br.append("</table>");
					br.append("</his:ContentTag>");
		        String strNum = "0";
		       // int nCount=1;
			    for (int i = 1; i <= totalPLayer; i++) 
			    {
				 if (i <= totalPLayer) 
				 {
					 
					if (i == 1) 
					{
						br.append("<div id='DivId" +i+ "' style='display:block'>");
					} 
					else
					{
						br.append("<div id='DivId" +i+ "' style='display:none'>");
					}
					br.append("<his:ContentTag>");
					br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
					for (int j = 0; j < REC_PER_PAGE; j++) 
					{
						strNum = i+""+j;
						if(wb.next())
						{
						        
							    br.append("<table class='TABLE_STYLE'>");
								
							    br.append("<input type='hidden' name='StoreName' id='StoreName" + strNum + "' value='"+wb.getString(1)+"'>");
								br.append("<input type='hidden' name='ItemSlNo' id='ItemSlNo" + strNum + "' value='"+wb.getString(2)+"'>");
								br.append("<input type='hidden' name='BatchNo' id='BatchNo" + strNum + "' value='"+wb.getString(3)+"'>");
								br.append("<input type='hidden' name='ManufactNo' id='ManufactNo" + strNum + "' value='"+wb.getString(4)+"'>");
								br.append("<input type='hidden' name='varIndex' id='varIndex" + strNum + "' value='"+strNum+"'>");
							    
							    br.append("<tr>");
								br.append("<td CLASS='LABEL_TD' width='5%' style='text-align:center'>");
								br.append("<input align='center' type='radio' name='checkid' value='"+0+"' id='checkid"+strNum+"' onClick='radioBtnClick(this,\""+strNum+"\");'></td>");
								br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='40%'>"+wb.getString(1)+"</td>");
								br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='20%'>"+wb.getString(2)+"</td>");
							    br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='12%'>"+wb.getString(3)+"</td>");
								br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='23%'>"+wb.getString(4)+"</td>");
								br.append("<input type='hidden' name='strStockInf'  id='strStockInf" + strNum + "'  value='"+wb.getString(5)+"'>");
								br.append("</tr>");
								br.append("</table>");
								br.append("</his:ContentTag>");
						}
						else
						{
							break;
						}
					}
					br.append("</table>");
					br.append("</his:ContentTag>");
					br.append("</div>");

				} 
				else 
				{
					br.append("<div id='DivId" + i+ "' style='display:none'>");
					br.append("<his:ContentTag>");
					br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
					for (int k = 0; k < reminder; k++) 
					{
						wb.next();
						strNum = i+""+k;
						br.append("<table class='TABLE_STYLE'>");
						
					    br.append("<input type='hidden' name='StoreName' id='StoreName" + strNum + "' value='"+wb.getString(1)+"'>");
						br.append("<input type='hidden' name='ItemSlNo' id='ItemSlNo" + strNum + "' value='"+wb.getString(2)+"'>");
						br.append("<input type='hidden' name='BatchNo' id='BatchNo" + strNum + "' value='"+wb.getString(3)+"'>");
						br.append("<input type='hidden' name='ManufactNo' id='ManufactNo" + strNum + "' value='"+wb.getString(4)+"'>");
						br.append("<input type='hidden' name='varIndex' id='varIndex" + strNum + "' value='"+strNum+"'>");
					    
					    br.append("<tr>");
						br.append("<td CLASS='LABEL_TD' width='5%' style='text-align:center'>");
						br.append("<input align='center' type='radio' name='checkid' value='"+0+"' id='checkid"+strNum+"' onClick='radioBtnClick(this,\""+strNum+"\");'></td>");
						br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='40%'>"+wb.getString(1)+"</td>");
						br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='20%'>"+wb.getString(2)+"</td>");
					    br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='12%'>"+wb.getString(3)+"</td>");
						br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='23%'>"+wb.getString(4)+"</td>");
						br.append("<input type='hidden' name='strStockInf'  id='strStockInf" + strNum + "'  value='"+wb.getString(5)+"'>");
						br.append("</tr>");
						br.append("</table>");
								
								
					}
					br.append("</table>");
					br.append("</his:ContentTag>");
					br.append("</div>");
					}
			   	}
			   br.append("</td>");
			   br.append("</tr>");
			   br.append("<tr class='TITLE'></tr>"); 
			   br.append("</table>");
			   br.append("</his:ContentTag>");
			 }
			 else
			 {
				    br.append("<his:ContentTag>");
					br.append("<table class='TABLE_STYLE'>");
				    br.append("<tr class='FOOTER_TR'>"); 
				    br.append("<th colspan='8' align='left'><div id='Id1' style='color:blue;'>Stock Details</div></th>");
				    //br.append("<th align='right'><img  style='cursor:pointer;cursor:pointer' src='../../hisglobal/images/stop.png' onclick='hideStockPopup();'>");
					//br.append("</th>");
				    br.append("</tr>");
					br.append("</table>");
					br.append("</his:ContentTag>");
					br.append("<his:ContentTag>");
				    br.append("<table class='TABLE_STYLE'>");
					//br.append("<tr><td colspan='5' bgcolor='black'></td></tr>");
					br.append("<TR>");
					br.append("<TD WIDTH='25%' CLASS='CONTROL_TD' style='text-align:center'><font color = 'red'>"
								+ "No Previous Stock Details Found" + "</font></TD>");
					br.append("</TR>");
					//br.append("<tr><td colspan='5' bgcolor='black'></td></tr>");
					br.append("</table>");
					br.append("<his:ContentTag>");
				
				   
					
			}
			br.append("</div>");
		   }
					
			else 
			{
				br.append("<his:ContentTag>");
				br.append("<table class='TABLE_STYLE'>");
				
				br.append("<td colspan='5'  CLASS='CONTROL_TD' style='text-align:center' ><DIV class='errMsg' align='center'>No Previous Validated Details !!! </div></TD>");
	
				br.append("</table>");
				br.append("</his:ContentTag>");

			}
		}
		catch (Exception e) 
		{
			throw new Exception("DocumentComparativeStatementTransHLP.getQuotedSupplierDtls()->"+e.getMessage());
			
		}
		return br.toString();
	}
	/**
	 * 
	 * @param viewValuesWS
	 * @return
	 * @throws Exception
	 */
	
	public static String getStockDtlWarranty(WebRowSet viewValuesWS)throws Exception
	{

		StringBuffer br = new StringBuffer("");
		WebRowSet wb = null;
		int start = 1;
		final int REC_PER_PAGE = 5;
		final int PAGE_PER_BLOCK = 5;
		
		int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
		int totalRecordsToManipulate = totalFetchRecord - 1;
		try 
		{
			wb = viewValuesWS;
			
            /*			
         
			Value in WebRowSet----

			1. Store Name
			2. Item SL No
			3. Batch No
			4. Manufacter No
			5. HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
			   ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
               GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE		       
            */
			
			
			if (wb != null) 
			{
				if(wb.size() != 0)
				{
				 int actualFetchRecord = wb.size();
			
		         if(totalFetchRecord != actualFetchRecord)
				 {
					totalFetchRecord = actualFetchRecord;
					totalRecordsToManipulate = actualFetchRecord;
				 }
				 int totalPLayer = totalRecordsToManipulate / REC_PER_PAGE;
				 int reminder = totalRecordsToManipulate % REC_PER_PAGE;
				 if (reminder > 0)
					totalPLayer = totalPLayer + 1;
				 
				     
					    br.append("<input type='hidden' name='fetchRecord'  value='"+actualFetchRecord+"'>");
					    br.append("<input type='hidden' name='fetchRecord'  value='"+actualFetchRecord+"'>");
					    br.append("<input type='hidden' name='TotalLayer'  value='"+totalPLayer+"'>");
					    br.append("<his:ContentTag>");
					    br.append("<table class='TABLE_STYLE'>");
					    br.append("<tr class='FOOTER_TR'>");
					    br.append("<td align='center' style='text-align: center;'width:5%;'><img style='cursor: pointer;' id='imgStockDetails' src='/HBIMS/hisglobal/images/minus.gif'" +
					    "onclick='showOrHideStockDetails(this)' title='Hide'/>" +
					    "</td><td colspan='5' style='width: 95%'><div id='Id1' style='text-align:left'>Stock Details</div></td>");
					    br.append("</tr>");
						br.append("</table>");
						br.append("</his:ContentTag>");
					 
					 br.append("<div id='prevLnkDivId' style='display:block'>");
					 br.append("<his:ContentTag>");					 
                     br.append("<table class='TABLE_STYLE' align='center'  border='0' cellpadding='1px' cellspacing ='1px'>");
					 br.append("<tr>");
					 br.append("<td class='LABEL_TD'>");
					 		 
					 
					 for (int i = 1; i <= totalPLayer; i++)
					 {
						br.append("<a name='pg' id='pg" + i + "'  STYLE='cursor:pointer;cursor:pointer;' class='pg-normal' title='Record Indexing' onClick='GetIndex(\""+i+"\",\""+totalPLayer+"\")'>" + (i+start-1)
								+ "</a>|&nbsp;");
					 }
					br.append("</td></tr>");
					br.append("</table>");
					br.append("</his:ContentTag>");
					br.append("<his:ContentTag>");
					br.append("<table class='TABLE_STYLE'>");
					br.append("<td CLASS='LABEL_TD' style='text-align:center' width='9%'>#</td>");
					br.append("<td CLASS='LABEL_TD' style='text-align:center' width='32%'>Item Serial No</td>");
					br.append("<td CLASS='LABEL_TD' style='text-align:center' width='24%'>Batch No</td>");
					br.append("<td CLASS='LABEL_TD' style='text-align:center' width='35%'>Manufac No.</td>");
					
					br.append("</tr>");
					
					br.append("</table>");
					br.append("</his:ContentTag>");
		        String strNum = "0";
		        //int nCount=1;
			    for (int i = 1; i <= totalPLayer; i++) 
			    {
				 if (i <= totalPLayer) 
				 {
					 
					if (i == 1) 
					{
						br.append("<div id='DivId" +i+ "' style='display:block'>");
					} 
					else
					{
						br.append("<div id='DivId" +i+ "' style='display:none'>");
					}
					br.append("<his:ContentTag>");
					br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
					for (int j = 0; j < REC_PER_PAGE; j++) 
					{
						strNum = i+""+j;
						if(wb.next())
						{
						        
							    br.append("<table class='TABLE_STYLE'>");
								
							    br.append("<input type='hidden' name='StoreName' id='StoreName" + strNum + "' value='"+wb.getString(1)+"'>");
								br.append("<input type='hidden' name='ItemSlNo' id='ItemSlNo" + strNum + "' value='"+wb.getString(2)+"'>");
								br.append("<input type='hidden' name='BatchNo' id='BatchNo" + strNum + "' value='"+wb.getString(3)+"'>");
								br.append("<input type='hidden' name='ManufactNo' id='ManufactNo" + strNum + "' value='"+wb.getString(4)+"'>");
								br.append("<input type='hidden' name='varIndex' id='varIndex" + strNum + "' value='"+strNum+"'>");
							    
							    br.append("<tr>");
								br.append("<td CLASS='LABEL_TD' width='9%' style='text-align:center'>");
								br.append("<input align='center' type='radio' name='checkid' value='"+0+"' id='checkid"+strNum+"' onClick='radioBtnClick(this,\""+strNum+"\");'></td>");
								
								br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='32%'>"+wb.getString(2)+"</td>");
							    br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='24%'>"+wb.getString(3)+"</td>");
								br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='35%'>"+wb.getString(4)+"</td>");
								br.append("<input type='hidden' name='strStockInf'  id='strStockInf" + strNum + "'  value='"+wb.getString(5)+"'>");
								br.append("</tr>");
								br.append("</table>");
								br.append("</his:ContentTag>");
						}
						else
						{
							break;
						}
					}
					br.append("</table>");
					br.append("</his:ContentTag>");
					br.append("</div>");

				} 
				else 
				{
					br.append("<div id='DivId" + i+ "' style='display:none'>");
					br.append("<his:ContentTag>");
					br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
					for (int k = 0; k < reminder; k++) 
					{
						wb.next();
						strNum = i+""+k;
						br.append("<table class='TABLE_STYLE'>");
						
					    br.append("<input type='hidden' name='StoreName' id='StoreName" + strNum + "' value='"+wb.getString(1)+"'>");
						br.append("<input type='hidden' name='ItemSlNo' id='ItemSlNo" + strNum + "' value='"+wb.getString(2)+"'>");
						br.append("<input type='hidden' name='BatchNo' id='BatchNo" + strNum + "' value='"+wb.getString(3)+"'>");
						br.append("<input type='hidden' name='ManufactNo' id='ManufactNo" + strNum + "' value='"+wb.getString(4)+"'>");
						br.append("<input type='hidden' name='varIndex' id='varIndex" + strNum + "' value='"+strNum+"'>");
					    
					    br.append("<tr>");
						br.append("<td CLASS='LABEL_TD' width='9%' style='text-align:center'>");
						br.append("<input align='center' type='radio' name='checkid' value='"+0+"' id='checkid"+strNum+"' onClick='radioBtnClick(this,\""+strNum+"\");'></td>");
						
						br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='32%'>"+wb.getString(2)+"</td>");
					    br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='24%'>"+wb.getString(3)+"</td>");
						br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='35%'>"+wb.getString(4)+"</td>");
						br.append("<input type='hidden' name='strStockInf'  id='strStockInf" + strNum + "'  value='"+wb.getString(5)+"'>");
						br.append("</tr>");
						br.append("</table>");
								
								
					}
					br.append("</table>");
					br.append("</his:ContentTag>");
					br.append("</div>");
					}
			   	}
			   br.append("</td>");
			   br.append("</tr>");
			   br.append("<tr class='TITLE'></tr>"); 
			   br.append("</table>");
			   br.append("</his:ContentTag>");
			 }
			 else
			 {
				    br.append("<his:ContentTag>");
					br.append("<table class='TABLE_STYLE'>");
				    br.append("<tr class='FOOTER_TR'>"); 
				    br.append("<th colspan='8' align='left'><div id='Id1' style=''>Stock Details</div></th>");
				    //br.append("<th align='right'><img  style='cursor:pointer;cursor:pointer' src='../../hisglobal/images/stop.png' onclick='hideStockPopup();'>");
					//br.append("</th>");
				    br.append("</tr>");
					br.append("</table>");
					br.append("</his:ContentTag>");
					br.append("<his:ContentTag>");
				    br.append("<table class='TABLE_STYLE'>");
					//br.append("<tr><td colspan='5' bgcolor='black'></td></tr>");
					br.append("<TR>");
					br.append("<TD WIDTH='25%' CLASS='CONTROL_TD' style='text-align:center'><font color = 'red'>"
								+ "No Previous Stock Details Found" + "</font></TD>");
					br.append("</TR>");
					//br.append("<tr><td colspan='5' bgcolor='black'></td></tr>");
					br.append("</table>");
					br.append("<his:ContentTag>");
				
				   
					
			}
			br.append("</div>");
		   }
					
			else 
			{
				br.append("<his:ContentTag>");
				br.append("<table class='TABLE_STYLE'>");
				
				br.append("<td colspan='5'  CLASS='CONTROL_TD' style='text-align:center' ><DIV class='errMsg' align='center'>No Previous Validated Details !!! </div></TD>");
	
				br.append("</table>");
				br.append("</his:ContentTag>");

			}
		}
		catch (Exception e) 
		{
			throw new Exception("DocumentComparativeStatementTransHLP.getQuotedSupplierDtls()->"+e.getMessage());
			
		}
		return br.toString();
	}
	
	

}
