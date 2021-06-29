/**
 * 
 */
package billing.masters.controller.hlp;

import javax.sql.rowset.WebRowSet;
/**
 * Developer : deepika gaba
 * Process Name : Charge Rule Master
 * Date : 2 sep 2011
 * Version : 1.0
 * Modify Date : 
 *
 */
public class ChargeRuleMstHLP {
	
	
	public static String getRuleDetails(WebRowSet valueWS) 
	throws Exception
	{

		StringBuffer br = new StringBuffer("");
		WebRowSet wb = null;
		int start = 1;
		final int REC_PER_PAGE = 10;
		final int PAGE_PER_BLOCK = 10;
		
		int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
		int totalRecordsToManipulate = totalFetchRecord - 1;
		
		int n = 0;
		try 
		{
			wb = valueWS;
			
			
			if (wb != null) 
			{
				
				 br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
				 br.append("<tr>");
		         br.append("<td  colspan='1' class='TITLE' width='2%'>");
				 
				 br.append("<div id='RuleDtlsPlusId' align='center' style='display:none;'>");
				 br.append("<img src='../../hisglobal/images/plus.gif'"+ 
									"onClick=view1('RuleDtlsPlusId','RuleDtlsMinusId','RuleDtlsDiv'); style='cursor: pointer; cursor: hand'/>");
				 br.append("</div>");
				 br.append("<div id='RuleDtlsMinusId' style='display:block;' align='center'>"+
						"<img src='../../hisglobal/images/minus.gif'"+ 
								"onClick=view2('RuleDtlsPlusId','RuleDtlsMinusId','RuleDtlsDiv'); style='cursor: pointer; cursor: hand'/>");
		         br.append("</div>");
		         br.append("</td>");
				 
				 br.append("<th colspan='5' align='left' class='TITLE'>Rule Details</th>");
				
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
				 
				     br.append("<input type='hidden' name='TotalLayer'  value='"+totalPLayer+"'>");
					 br.append("<input type='hidden' name='RecordShowOnPage'  value='"+REC_PER_PAGE+"'>");
					
					 //br.append("<tr>");
					 br.append("<td class='TITLE'>");

					 for (int i = 1; i <= totalPLayer; i++)
					 {
						br.append("<a STYLE='cursor:pointer;' color:'blue' onClick='GetIndex(\""+i+"\",\""+totalPLayer+"\")'>" + (i+start-1)
								+ "</a> &nbsp;");
					 }
					br.append("</td></tr>");
					br.append("<tr><td class='LABEL'><div id='pageNavPosition'></div></td></tr>");
					br.append("</table>");
					
					br.append("<div id='RuleDtlsDiv'>");
					br.append("<table class='TABLEWIDTH' align='center'  cellspacing='1px'>");
					br.append("<td CLASS='multiLabel' width='2%'>#</td>");
					br.append("<td CLASS='multiLabel' width='20%'>Rule Name</td>");
					br.append("<td CLASS='multiLabel' width='16%'>Rule For Hospital Service</td>");
					br.append("<td CLASS='multiLabel' width='20%'>Rule For Category</td>");
					br.append("<td CLASS='multiLabel' width='16%'>Ward Type</td>");
					br.append("<td CLASS='multiLabel' width='16%'>Rule With & Variation</td>");
					br.append("<td CLASS='multiLabel' width='10%'></td>");
					
					br.append("</tr>");
					
					br.append("</table>");
		       // String nTmpI = "0";
		        //int count=1;
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
					
					br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
					
					for (int j = 0; j < REC_PER_PAGE; j++) 
					{
						//nTmpI = i+""+j;
						if(wb.next())
						{
						        
								br.append("<table class='TABLEWIDTH' align='center'  cellspacing='1px'>");
								br.append("<tr CLASS='style_x'>");
								
								br.append("<td class='multiControlLeft' width='2%'>");
								br.append("<input type='radio' name='strRadioRuleDetail' id='strRadioRuleDetail"+n+"' value='"+wb.getString(6)+"' onClick='showRequestDetail("+n+")'>");
								
								//String[] temp = wb.getString(6).replace("^", "#").split("#");
								
								//br.append("<input type='hidden' name='strHidRuleId' value='"+temp[0]+"'></td>");
								br.append("<td class='multiControlLeft' width='20%'>"+wb.getString(1)+"</td>");
								br.append("<td class='multiControlLeft' width='16%'>"+wb.getString(2)+"</td>");
								br.append("<td class='multiControlLeft' width='20%'>"+wb.getString(3)+"</td>");
								br.append("<td class='multiControlLeft' width='16%'>"+wb.getString(4)+"</td>");
								br.append("<td class='multiControlLeft' width='16%'>"+wb.getString(5)+"</td>");
								br.append("<td class='multiControlLeft' width='10%'><a name='strLink' style='cursor:pointer;color:blue' title='To Modify the Rule Details' "
			    		                  + "onClick='modifyData(this);'>Modify</a>/<a name='strDelete' style='cursor:pointer;color:blue' title='To Delete the Rule Details' "
			    		                  + "onClick='deleteData(this);'>Delete</a></td>");
								
								br.append("</tr>");
								br.append("</table>");
								
						}
						else
						{
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
						wb.next();
						//nTmpI = i+""+k;
								br.append("<table class='TABLEWIDTH' align='center'  cellspacing='1px'>");
								br.append("<tr>");
								
								br.append("<td class='multiControl' width='2%'>");
								br.append("<input type='radio' name='strRadioRuleDetail' id='strRadioRuleDetail"+n+"' value='"+wb.getString(6)+"' onClick='showRequestDetail("+n+")'>");
								
								br.append("<td class='multiControl' width='20%'>"+wb.getString(1)+"</td>");
								br.append("<td class='multiControl' width='16%'>"+wb.getString(2)+"</td>");
								br.append("<td class='multiControl' width='20%'>"+wb.getString(3)+"</td>");
								br.append("<td class='multiControl' width='16%'>"+wb.getString(4)+"</td>");
								br.append("<td class='multiControl' width='16%'>"+wb.getString(5)+"</td>");
								br.append("<td class='multiControl' width='10%'><a name='strLink' style='cursor:pointer;color:blue' title='To Modify the Rule Details' "
			    		                  + "onClick='modifyData(this);'>Modify</a>/<a style='cursor:pointer;color:blue' title='To Delete the Rule Details' "
			    		                  + "onClick='deleteData(this);'>Delete</a></td>");
								
								br.append("</tr>");
								
					}
					br.append("</table>");
					br.append("</div>");
					}
			   	}
			   br.append("</td>");
			   br.append("</tr>");
			   br.append("</table>");
			 }else{
				 br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
				 br.append("<tr><td colspan='5' bgcolor='black'></td></tr>");
				   br.append("<TR>");
				   br.append("<TD WIDTH='25%' align='center' CLASS='multiControl' colspan='5'><font color = 'red'>"
							+ "No Record Found" + "</font></TD>");
				   br.append("</TR>");
				   br.append("<tr><td colspan='5' bgcolor='black'></td></tr>");
				   br.append("</table>");
					
			   }
		   }
					
			else 
			{
				br.append("<table class='TABLEWIDTH' align='center'  border='0' bgcolor='#000000' cellspacing ='1px'>");
				
				br.append("<td colspan='5'  CLASS='multiControl' ><DIV class='errMsg' align='center'>No Previous Validated Details !!! </div></TD>");
	
				br.append("</table>");

			}
			br.append("</div>");
		}
		catch (Exception e) 
		{
			throw new Exception("ChargeRuleMstHLP.getRuleDetails()->"+e.getMessage());
			
		}
		return br.toString();
	}

}
