package bmed.global.controller.data;

import javax.sql.rowset.WebRowSet;

public class PerviousMaintenanceDetailsDATA 
{
	
	public static String getPreviousMaintenanceDetails(WebRowSet viewValuesWS,String strMode)throws Exception
	{		
		StringBuffer br = new StringBuffer("");
		//String strReaminInfo;
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
			1. Sl No
			2. Terms Cond
			3. IsItem
			4. Mc Name
			5. Manufac Id
			6. Penalty Cond
			7. Start Date
			8. End Date
			9. Up-Load No
		   10. DocRefNo
		   11. Doc Ref date
		   12. Supplier
		   13. AMC Type
		   14. Cost
		   15. (HEMNUM_ITEM_ID, HEMSTR_BATCH_NO, HEMNUM_ITEM_SL_NO, HEMNUM_SL_NO, GNUM_HOSPITAL_CODE)	
		   16. HEMNUM_ROUTINE_VISIT
		   17. HEMNUM_BREAK_VISIT
           18. HEMSTR_RESPONSE_TIME 
           19. HEMNUM_ROUTINE_FREQ
           20. Frequency Unit Name  
           21. Response Time Unit Name 
           22. HEMSTR_TENDER_NO 
           23. HPURNUM_UPLOAD_NO 
           24. HPURSTR_DOC_REF_NO  
           25. HEMDT_TENDER_DATE,
           26. HPURDT_DOC_REF_DATE  
           27. HEMSTR_ORDER_NO  
           28. HEMDT_ORDER_DATE 
           29. GSTR_REMARKS 
           30. HEMNUM_IS_RENEWED
           31. HEMSTR_FREQ_UNIT 
           32. HEMSTR_RES_TIME_UNIT
						       
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
					
				 
				 	br.append("<input type='hidden' name='prevRecordExists'  value='1'>"); 
				    br.append("<input type='hidden' name='fetchRecord'  value='"+actualFetchRecord+"'>");
				    br.append("<input type='hidden' name='fetchRecord'  value='"+actualFetchRecord+"'>");
				    br.append("<input type='hidden' name='TotalLayer'  value='"+totalPLayer+"'>");
				    //br.append("<his:ContentTag>");
				    br.append("<div class='line'>");
				    br.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='1px' cellspacing='1px'>");
				    br.append("<tr>");
				    br.append("<td align='center' style='width: 5%'><img style='cursor: pointer;' id='imgStockDetails' src='../../hisglobal/images/minus.gif'" +
				    "onclick='showOrHidePrevMaintenanceDetails(this)' title='Hide'/>" +
				    "</td><td colspan='5' style='width: 95%'><div id='Id1' style='text-align:left'>Previous Maintenance Details</div></td>");
				    br.append("</tr>");
					br.append("</table>");
					br.append("</div>");
					//br.append("</his:ContentTag>");
				 
				 br.append("<div id='prevMantDtlDivId' style='display:block'>");
				 //br.append("<his:ContentTag>");					 
                 br.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='1px' cellspacing='1px'>");
				 br.append("<tr>");
				 br.append("<td class='LABEL'>");
					 		 
					 
					 for (int i = 1; i <= totalPLayer; i++)
					 {
						br.append("<a name='pg' id='pg" + i + "'  STYLE='cursor:pointer;cursor:pointer;' class='pg-normal' title='Record Indexing' onClick='GetIndex(\""+i+"\",\""+totalPLayer+"\")'>" + (i+start-1)
								+ "</a>|&nbsp;");
					 }
					br.append("</td></tr>");
					br.append("</table>");
					//br.append("</his:ContentTag>");
					//br.append("<his:ContentTag>");
					
					br.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='20px' cellspacing='1px'>");
					br.append("<td CLASS='LABEL' style='text-align:center' width='21%'>Supplier Name</td>");
					br.append("<td CLASS='LABEL' style='text-align:center' width='21%'>Mant.Cont.Type/Cost</td>");
					br.append("<td CLASS='LABEL' style='text-align:center' width='19%'>Start</td>");
					br.append("<td CLASS='LABEL' style='text-align:center' width='18%'>Uploded File</td>");
					br.append("<td CLASS='LABEL' style='text-align:center' width='21%'>End Date</td>");
					
					br.append("</tr>");
					
					br.append("</table>");
					//br.append("</his:ContentTag>");
		        String nTmpI = "0";
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
					//br.append("<his:ContentTag>");
					br.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='1px' cellspacing='1px'>");
					for (int j = 0; j < REC_PER_PAGE; j++) 
					{
						nTmpI = i+""+j;
						if(wb.next())
						{
						        
							    br.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='1px' cellspacing='1px' width='100%'>");
							    
							    br.append("<input type='hidden' name='strTermsCond' id='strTermsCond" + nTmpI + "' value='"+wb.getString(2).replace("&","and")+"'>");
							    br.append("<input type='hidden' name='strPenelty' id='strPenelty" + nTmpI + "' value='"+wb.getString(6).replace("&","and")+"'>");
							    
							    br.append("<input type='hidden' name='strReaminInfo' id='strReaminInfo" + nTmpI + "' value='"+wb.getString(16)+"^"+wb.getString(17)+"^"+wb.getString(18)+"^"+wb.getString(19)+"^"+wb.getString(31)+"^"+wb.getString(32)+"^"+wb.getString(22)+"^"+wb.getString(23)+"^"+wb.getString(24)+"^"+wb.getString(25)+"^"+wb.getString(26)+"^"+wb.getString(27)+"^"+wb.getString(28)+"^"+wb.getString(29).replace("&","AND")+"^"+wb.getString(30)+"^"+wb.getString(2).replace("&","and")+"^"+wb.getString(6).replace("&","and")+"^"+wb.getString(1)+"'>");
							    br.append("<input type='hidden' name='strPKey' id='strPKey" + nTmpI + "' value='"+wb.getString(15)+"'>");
							    
							    br.append("<input type='hidden' name='strMantStartDate' id='strMantStartDate" + nTmpI + "' value='"+wb.getString(7)+"'>");
							    br.append("<input type='hidden' name='strMantEndDate' id='strMantEndDate" + nTmpI + "' value='"+wb.getString(8)+"'>");
							    br.append("<input type='hidden' name='strMantCost' id='strMantCost" + nTmpI + "' value='"+wb.getString(14)+"'>");

							    br.append("<input type='hidden' name='strMantContSupplier' id='strMantContSupplier" + nTmpI + "' value='"+wb.getString(12)+"'>");
							    br.append("<input type='hidden' name='strMantContName' id='strMantContName" + nTmpI + "' value='"+wb.getString(13)+"'>");
															    
							    br.append("<tr>");
							   
								br.append("<td CLASS='CONTROL' style='text-align:center' width='21%'>"+wb.getString(12)+"</td>");
								br.append("<td CLASS='CONTROL' style='text-align:center' width='21%'>"+wb.getString(13)+"/"+wb.getString(14)+"</td>");
								br.append("<td CLASS='CONTROL' style='text-align:center' width='19%'>"+wb.getString(7)+"</td>");
								/*if(wb.getString(10).equals("")||wb.getString(10).equals(" ")||wb.getString(10).equals("   "))
						    	{
								br.append("<td CLASS='CONTROL' style='text-align:center' width='18%'>"+wb.getString(9)+"</td><input type='hidden' name='strReqUpLoadFileID' value='"+wb.getString(10)+"'>");
						    	}
								else
								{
									br.append("<td CLASS='CONTROL' style='text-align:center' width='18%'>"+wb.getString(10)+"</td><input type='hidden' name='strReqUpLoadFileID' value='"+wb.getString(10)+"'>");
								}*/
								 // Here we Check The Empty Uploaded  file Name 								
								String s;
								if(wb.getString(10).equals("")||wb.getString(10).equals(" ")||wb.getString(10).equals("   "))
						    	{
						    		s = "0";
						    		br.append("<td CLASS='CONTROL' style='text-align:center' width='18%'>NA</font><input type='hidden' id='strReqUpLoadFileName" + nTmpI + "' name='strReqUpLoadFileName' value='"+wb.getString(10)+"'><input type='hidden' id='strReqUpLoadFileID" + nTmpI + "' name='strReqUpLoadFileID' value='"+wb.getString(10)+"'></td>");
						    	}
						    	else
						    	{
						    		s=wb.getString(16);
						    		br.append("<td CLASS='CONTROL' style='text-align:center' width='18%'><div id='strReqUpLoadDiv" + nTmpI + "'><a style='color:blue;cursor: pointer;' onClick=getUploadFile(this"+","+nTmpI+",'"+wb.getString(10)+"');>"
						    				+wb.getString(10)+"</a></div></font><input type='hidden' id='strReqUpLoadFileName" + nTmpI + "' name='strReqUpLoadFileName' value='"+wb.getString(10)+"'>"
						    						+ "<input type='hidden' id='strReqUpLoadFileID" + nTmpI + "' name='strReqUpLoadFileID' value='"+wb.getString(10)+"'></td>");
						    	}
								
								br.append("<td CLASS='CONTROL' style='text-align:center' width='21%'>"+wb.getString(8)+"</td>");
								
                            
						    	/*								
							  	  if(strMode.equals("1"))
							  	  {	  
									br.append("<td CLASS='CONTROL' style='text-align:center' width='7%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='openBlanketPopUp(this,"+nTmpI+",1);'>Renew</font></td>");
							  	  }
							  	  else
							  	  {
							  		br.append("<td CLASS='CONTROL' style='text-align:center' width='7%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='openBlanketPopUp(this,"+nTmpI+",1);'>Extend</font></td>");
							  	  }	  
									br.append("<td CLASS='CONTROL' style='text-align:center' width='7%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='openBlanketPopUp(this,"+nTmpI+",2);'>Cancel</font></td>");
									br.append("<td CLASS='CONTROL' style='text-align:center' width='7%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='openBlanketPopUp(this,"+nTmpI+",3);'>View</font></td>");
								*/
								br.append("</tr>");
								br.append("</table>");
								//br.append("</his:ContentTag>");
						}
						else
						{
							break;
						}
					}
					br.append("</table>");
					//br.append("</his:ContentTag>");
					br.append("</div>");

				} 
				else 
				{
					br.append("<div id='DivId" + i+ "' style='display:none'>");
					//br.append("<his:ContentTag>");
					br.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='1px' cellspacing='1px'>");
					for (int k = 0; k < reminder; k++) 
					{
						wb.next();
						nTmpI = i+""+k;
						br.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='1px' cellspacing='1px'>");
						
					    br.append("<input type='hidden' name='strTermsCond' id='strTermsCond" + nTmpI + "' value='"+wb.getString(2).replace("&","and")+"'>");
					    br.append("<input type='hidden' name='strPenelty' id='strPenelty" + nTmpI + "' value='"+wb.getString(6).replace("&","and")+"'>");
					    
					    br.append("<input type='hidden' name='strReaminInfo' id='strReaminInfo" + nTmpI + "' value='"+wb.getString(16)+"^"+wb.getString(17)+"^"+wb.getString(18)+"^"+wb.getString(19)+"^"+wb.getString(31)+"^"+wb.getString(32)+"^"+wb.getString(22)+"^"+wb.getString(23)+"^"+wb.getString(24)+"^"+wb.getString(25)+"^"+wb.getString(26)+"^"+wb.getString(27)+"^"+wb.getString(28)+"^"+wb.getString(29).replace("&","and")+"^"+wb.getString(30)+"^"+wb.getString(2).replace("&","and")+"^"+wb.getString(6).replace("&","and")+"^"+wb.getString(1)+"'>");
					    br.append("<input type='hidden' name='strMantStartDate' id='strMantStartDate" + nTmpI + "' value='"+wb.getString(7)+"'>");
					    br.append("<input type='hidden' name='strMantEndDate' id='strMantEndDate" + nTmpI + "' value='"+wb.getString(8)+"'>");
					    br.append("<input type='hidden' name='strMantCost' id='strMantCost" + nTmpI + "' value='"+wb.getString(14)+"'>");

						
					    br.append("<input type='hidden' name='strPKey' id='strPKey" + nTmpI + "' value='"+wb.getString(15)+"'>");
					    br.append("<input type='hidden' name='strMantContSupplier' id='strMantContSupplier" + nTmpI + "' value='"+wb.getString(12)+"'>");
					    br.append("<input type='hidden' name='strMantContName' id='strMantContName" + nTmpI + "' value='"+wb.getString(13)+"'>");
		    
					    br.append("<tr>");
					   
						br.append("<td CLASS='CONTROL' style='text-align:center' width='21%'>"+wb.getString(12)+"</td>");
						br.append("<td CLASS='CONTROL' style='text-align:center' width='21%'>"+wb.getString(13)+"/"+wb.getString(14)+"</td>");
						br.append("<td CLASS='CONTROL' style='text-align:center' width='19%'>"+wb.getString(7)+"/"+wb.getString(8)+"</td>");
                       // Here we Check The Empty Uploaded  file Name 								
							String s;
							if(wb.getString(10).equals("")||wb.getString(10).equals(" ")||wb.getString(10).equals("   "))
					    	{
					    		s = "0";
					    		br.append("<td CLASS='CONTROL' style='text-align:center' width='18%'>NA</font>"
					    				+ "<div style='color:blue;cursor: pointer;'><input type='hidden' id='strReqUpLoadFileName" + nTmpI + "' name='strReqUpLoadFileName' value='"+wb.getString(9)+"'><input type='hidden' id='strReqUpLoadFileID" + nTmpI + "' name='strReqUpLoadFileID' value='"+wb.getString(10)+"'></div></td>");
					    	}
					    	else
					    	{
					    		s=wb.getString(16);
					    		br.append("<td CLASS='CONTROL' style='text-align:center' width='18%'><div id='strReqUpLoadDiv" + nTmpI + "' style='color:blue;cursor: pointer;' onclick='getUploadFile(this);'>"
					    				+wb.getString(9)+"</div></font><input type='hidden' id='strReqUpLoadFileName" + nTmpI + "' name='strReqUpLoadFileName' value='"+wb.getString(9)+"'><input type='hidden' id='strReqUpLoadFileID" + nTmpI + "' name='strReqUpLoadFileID' value='"+wb.getString(10)+"'></td>");
					    	}						
					    	  if(strMode.equals("1"))
						  	  {	  
								br.append("<td CLASS='CONTROL' style='text-align:center' width='7%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='openBlanketPopUp(this,"+nTmpI+",1);'>Renew</font></td>");
						  	  }
						  	  else
						  	  {
						  		br.append("<td CLASS='CONTROL' style='text-align:center' width='7%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='openBlanketPopUp(this,"+nTmpI+",1);'>Extend</font></td>");
						  	  }	  
								br.append("<td CLASS='CONTROL' style='text-align:center' width='7%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='openBlanketPopUp(this,"+nTmpI+",2);'>Cancel</font></td>");
								br.append("<td CLASS='CONTROL' style='text-align:center' width='7%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='openBlanketPopUp(this,"+nTmpI+",3);'>View</font></td>");
						
						br.append("</tr>");
						br.append("</table>");
						//br.append("</his:ContentTag>");
						
								
					}
					br.append("</table>");
					//br.append("</his:ContentTag>");
					br.append("</div>");
					}
			   	}
			   br.append("</td>");
			   br.append("</tr>");
			   br.append("<tr class='TITLE'></tr>"); 
			   br.append("</table>");
			   //br.append("</his:ContentTag>");
			 }
			 else
			 {
				    
				    //br.append("<his:ContentTag>");
				 	br.append("<div class='line'>");
				    br.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='1px' cellspacing='1px'>");
				    br.append("<tr>");
				    br.append("<td align='center' style='width: 5%'><img style='cursor: pointer;' id='imgStockDetails' src='../../hisglobal/images/minus.gif'" +
				    "onclick='showOrHidePrevMaintenanceDetails(this)' title='Hide'/>" +
				    "</td><td colspan='5' style='width: 95%'><div id='Id1' style='text-align:left'>Previous Maintenance Details</div></td>");
				    br.append("</tr>");
					br.append("</table>");
					//br.append("</his:ContentTag>");
					br.append("</div>");
				    br.append("<div id='prevMantDtlDivId' style='display:block'>");
					//br.append("</his:ContentTag>");
					//br.append("<his:ContentTag>");
				    br.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='1px' cellspacing='1px'>");
					//br.append("<tr><td colspan='5' bgcolor='black'></td></tr>");
					br.append("<TR>");
					br.append("<TD WIDTH='25%' CLASS='CONTROL' style='text-align:center'><font color = 'red'>"
								+ "No Previous Maintenance Details Found." + "</font></TD>");
					br.append("</TR>");
					//br.append("<tr><td colspan='5' bgcolor='black'></td></tr>");
					br.append("</table>");
					//br.append("<his:ContentTag>");
					br.append("<input type='hidden' name='prevRecordExists'  value='0'>");
				
				   
					
			}
			br.append("</div>");
		   }
					
			else 
			{
				//br.append("<his:ContentTag>");
				br.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='1px' cellspacing='1px'>");
				
				br.append("<td colspan='5'  CLASS='multiControl' ><DIV class='errMsg' align='center'>No Previous Validated Details Found !!! </div></TD>");
	
				br.append("</table>");
				//br.append("<his:ContentTag>");
				br.append("<input type='hidden' name='prevRecordExists'  value='0'>");

			}
			br.append("<hr style='width:95%'>");
		}
		catch (Exception e) 
		{
			throw new Exception("DocumentComparativeStatementTransHLP.getQuotedSupplierDtls()->"+e.getMessage());
			
		}
		return br.toString();
	}

}
