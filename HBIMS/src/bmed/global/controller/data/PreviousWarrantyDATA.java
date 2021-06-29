package bmed.global.controller.data;

import javax.sql.rowset.WebRowSet;

public class PreviousWarrantyDATA 
{
	
	public static String getPreviousWarrantyDetails(WebRowSet viewValuesWS,String strMode)throws Exception
	{		
		StringBuffer br = new StringBuffer("");
		String strWarrantyTableInfo;
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
			1.HSTNUM_ITEM_ID:              
			2.HSTNUM_ITEMBRAND_ID:         
			3.HSTSTR_BATCH_SL_NO:          
			4.GNUM_HOSPITAL_CODE:          
			5.HSTNUM_ITEM_SL_NO:           
			6.HSTNUM_MANUF_ID:             
			7.HSTNUM_MANUF_SL_NO:          
			8.HSTNUM_SL_NO:                
			9.HSTDT_WARRENTY_DATE:         
		    10.HSTNUM_WARRENTY_UPTO:        
		    11.HSTNUM_WARRENTY_UPTO_UNIT:   
		    12.HSTNUM_IS_ITEM:              
			13.HSTDT_FINANCIAL_START_YEAR:  
			14.HSTDT_FINANCIAL_END_YEAR:    
			15.HPURNUM_UPLOAD_NO:           
			16.HPURSTR_DOC_REF_NO:          
			17.HEMSTR_TERM_N_CON:           
			18.HPURDT_DOC_REF_DATE:         
			19.GSTR_REMARKS:                
			20.GDT_ENTRY_DATE:              
			21.GNUM_SEATID:                 
			22.GNUM_ISVALID:                
			23.HEMSTR_TENDER_NO:            
			24.GDT_LSTMOD_DATE:             
			25.HEMDT_TENDER_DATE:           
			26.GNUM_LSTMOD_SEATID:          
			27.HEMSTR_ORDER_NO:             
			28.HEMDT_ORDER_DATE:            
			29.HEMNO_CANCEL_ID:             
			30.HEMDT_CANCEL_DATE:           
			31.HEMSTR_EXT_TERM_N_CON:       
			32.HEMSTR_CANCEL_REMARKS:       
			33.HEMNUM_IS_EXTENDED:          
			34.HEMDT_EXTENDED_START_DATE:   
			35.HEMNUM_EXTENDED_UPTO:        
			36.HEMNUM_EXTENDED_UPTO_UNIT:   
			37.HPURNUM_EXT_UPLOAD_NO:       
			38.HPURNUM_EXT_DOC_REF_NO:   This Act As File Name   
			39.HPURDT_EXT_DOC_REF_DATE:     
			40.ITEM_NAME:                   
			41.VENDOR_NAME:                 
			42.WARRENTY_UPTO_UNIT_NAME:   
			43.WARRANTY_EXTEND_DATE:
			44.GUARANTY_UPTO

						       
            */
			
			
			
			
			
			
			if (wb != null) 
			{
				if(wb.size() != 0)
				{
				 int actualFetchRecord = wb.size();
			//	 System.out.println("actualFetchRecord::"+actualFetchRecord);
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
				    "onclick='showOrHidePrevWarrantyDetails(this)' title='Hide'/>" +
				    "</td><td colspan='5' style='width: 95%'><div id='Id1' style='text-align:left'>Previous Guaranty Details</div></td>");
				    br.append("</tr>");
					br.append("</table>");
					br.append("</div>");
					//br.append("</his:ContentTag>");
				 
				 br.append("<div id='prevWarrantyDtlDivId' style='display:block'>");
				 //br.append("<his:ContentTag>");					 
                 br.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='1px' cellspacing='1px'>");
				 br.append("<tr>");
				 br.append("<td class='LABEL'>");
					 		 
					 
					 for (int i = 1; i <= totalPLayer; i++)
					 {
						br.append("<a name='mg' id='mg" + i + "'  STYLE='cursor:pointer;cursor:pointer;' class='pg-normal' title='Record Indexing' onClick='GetIndex2(\""+i+"\",\""+totalPLayer+"\")'>" + (i+start-1)
								+ "</a>|&nbsp;");
					 }
					br.append("</td></tr>");
					br.append("</table>");
					//br.append("</his:ContentTag>");
					//br.append("<his:ContentTag>");
					
					br.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='10px' cellspacing='1px'>");
					br.append("<tr  width='100%'><td CLASS='LABEL' style='text-align:center' width='25%'>Supplier Name</td>");
					br.append("<td CLASS='LABEL' style='text-align:center' width='15%'>Start Date</td>");
					br.append("<td CLASS='LABEL' style='text-align:center' width='15%'>Guaranty Upto</td>");
					br.append("<td CLASS='LABEL' style='text-align:center' width='15%'>Extended Date</td>");
					br.append("<td CLASS='LABEL' style='text-align:center' width='15%'>Installation Date</td>");
					br.append("<td CLASS='LABEL' style='text-align:center' width='15%'>Uploded File</td>");								
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
						br.append("<div id='DivId2" +i+ "' style='display:block'>");
					} 
					else
					{
						br.append("<div id='DivId2" +i+ "' style='display:none'>");
					}
					//br.append("<his:ContentTag>");
					br.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='1px' cellspacing='1px'>");
					for (int j = 0; j < REC_PER_PAGE; j++) 
					{
						nTmpI = i+""+j;
						if(wb.next())
						{
						      
							    br.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='1px' cellspacing='1px'>");
							    strWarrantyTableInfo = wb.getString(1)+"^"+wb.getString(2)+"^"+wb.getString(3)+"^"+wb.getString(4)+"^"+wb.getString(5)+"^"+wb.getString(6)+"^"+wb.getString(7)+"^"+wb.getString(8)+"^"+wb.getString(9)+"^"+wb.getString(10)+"^"+wb.getString(11)+"^"+wb.getString(12)+"^"
							    +wb.getString(13)+"^"+wb.getString(14)+"^"+wb.getString(15)+"^"+wb.getString(16)+"^"+wb.getString("HEMSTR_TERM_N_CON").replace("&","and")+"^"+wb.getString(18)+"^"+wb.getString(19).replace("&","and")+"^"+wb.getString(20)+"^"+wb.getString(21)+"^"+wb.getString(22)+"^"+wb.getString(23)+"^"+wb.getString(24)+"^"+wb.getString(25)+"^"+wb.getString(26)+"^"
							    +wb.getString(27)+"^"+wb.getString(28)+"^"+wb.getString(29)+"^"+wb.getString(30)+"^"+wb.getString(31)+"^"+wb.getString(32)+"^"+wb.getString(33)+"^"+wb.getString(34)+"^"+wb.getString(35)+"^"+wb.getString(36)+"^"+wb.getString(37)+"^"+wb.getString(38)+"^"+wb.getString(39)+"^"+wb.getString(40)+"^"+wb.getString(41)+"^"+wb.getString(42)+"^"+wb.getString(43)+"^"+wb.getString(44);
							    br.append("<input type='hidden' name='strWarrantyTableInfo' id='strWarrantyTableInfo" + nTmpI + "' value='"+strWarrantyTableInfo+"'>");
							     			    
							    br.append("<tr>");
							   
								br.append("<td CLASS='CONTROL' style='text-align:center' width='25%'>"+wb.getString(41)+"</td>");
								br.append("<td CLASS='CONTROL' style='text-align:center' width='15%'>"+wb.getString(9)+"</td>");
								br.append("<td CLASS='CONTROL' style='text-align:center' width='15%'>"+wb.getString(44)+" ("+wb.getString(10)+""+wb.getString(42)+") </td>");								
								br.append("<td CLASS='CONTROL' style='text-align:center' width='15%'>"+wb.getString(43)+"</td>");
							//	br.append("<td CLASS='CONTROL' style='text-align:center' width='15%'>"+wb.getString(46)+"</td>");
                             // Here we Check The Empty Uploaded  file Name 								
								if(wb.getString(33).equals("0"))
								{
										String s;
										if(wb.getString(16)!=null)
								    	{
								    		s = "0";
								    		br.append("<td CLASS='CONTROL' style='text-align:center' width='21%'>NA</font><input type='hidden' id='strReqUpLoadFileName" + nTmpI + "' name='strReqUpLoadFileName' value='"+wb.getString(16)+"'><input type='hidden' id='strReqUpLoadFileID" + nTmpI + "' name='strReqUpLoadFileID' value='"+wb.getString(15)+"'></td>");
								    	}
								    	else
								    	{
								    		s=wb.getString(16);
								    		br.append("<td CLASS='CONTROL' style='text-align:center' width='21%'><div id='strReqUpLoadDiv" + nTmpI + "'"
								    				+ " style='color:blue;cursor: pointer;' onclick=getUploadFile(this"+','+nTmpI+','+wb.getString(15)+");>"
								    				+wb.getString(16)+"</div></font><input type='hidden' id='strReqUpLoadFileName" + nTmpI + "' "
								    						+ "name='strReqUpLoadFileName' value='"+wb.getString(16)+"'><input type='hidden' id='strReqUpLoadFileID" + nTmpI + "' "
								    								+ "name='strReqUpLoadFileID' value='"+wb.getString(15)+"'></td>");
								    	}
						    		
								}
								else
								{
									String s;
									if(wb.getString(38).equals("")||wb.getString(38).equals(" ")||wb.getString(38).equals("   "))
							    	{
							    		s = "0";
							    		br.append("<td CLASS='CONTROL' style='text-align:center' width='21%'>NA</font><input type='hidden' id='strReqUpLoadFileName" + nTmpI + "' name='strReqUpLoadFileName' value='"+wb.getString(37)+"'><input type='hidden' id='strReqUpLoadFileID" + nTmpI + "' name='strReqUpLoadFileID' value='"+wb.getString(38)+"'></td>");
							    	}
							    	else
							    	{
							    		s=wb.getString(38);
							    		br.append("<td CLASS='CONTROL' style='text-align:center' width='21%'><div id='strReqUpLoadDiv" + nTmpI + "' style='color:blue;cursor: pointer;' onclick='getUploadFile(this);'>"
							    				+wb.getString(37)+"</div></font><input type='hidden' id='strReqUpLoadFileName" + nTmpI + "' name='strReqUpLoadFileName' value='"+wb.getString(37)+"'><input type='hidden' id='strReqUpLoadFileID" + nTmpI + "' name='strReqUpLoadFileID' value='"+wb.getString(38)+"'></td>");
							    	}
									
									
									
								}	
							  	  
							  		/*br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='7%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='openBlanketPopUp(this,"+nTmpI+",1);'>Extend</font></td>");
							  	  	br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='7%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='openBlanketPopUp(this,"+nTmpI+",2);'>Cancel</font></td>");
									br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='7%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='openBlanketPopUp(this,"+nTmpI+",3);'>View</font></td>");*/

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
					br.append("<div id='DivId2" + i+ "' style='display:none'>");
					//br.append("<his:ContentTag>");
					br.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='1px' cellspacing='1px'>");
					for (int k = 0; k < reminder; k++) 
					{
						wb.next();
						nTmpI = i+""+k;
						br.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='1px' cellspacing='1px'>");
						
					    strWarrantyTableInfo = wb.getString(1)+"^"+wb.getString(2)+"^"+wb.getString(3)+"^"+wb.getString(4)+"^"+wb.getString(5)+"^"+wb.getString(6)+"^"+wb.getString(7)+"^"+wb.getString(8)+"^"+wb.getString(9)+"^"+wb.getString(10)+"^"+wb.getString(11)+"^"+wb.getString(12)+"^"
					    +wb.getString(13)+"^"+wb.getString(14)+"^"+wb.getString(15)+"^"+wb.getString(16)+"^"+wb.getString("HEMSTR_TERM_N_CON").replace("&","and")+"^"+wb.getString(18)+"^"+wb.getString(19).replace("&","and")+"^"+wb.getString(20)+"^"+wb.getString(21)+"^"+wb.getString(22)+"^"+wb.getString(23)+"^"+wb.getString(24)+"^"+wb.getString(25)+"^"+wb.getString(26)+"^"
					    +wb.getString(27)+"^"+wb.getString(28)+"^"+wb.getString(29)+"^"+wb.getString(30)+"^"+wb.getString(31)+"^"+wb.getString(32)+"^"+wb.getString(33)+"^"+wb.getString(34)+"^"+wb.getString(35)+"^"+wb.getString(36)+"^"+wb.getString(37)+"^"+wb.getString(38)+"^"+wb.getString(39)+"^"+wb.getString(40)+"^"+wb.getString(41)+"^"+wb.getString(42)+"^"+wb.getString(43)+"^"+wb.getString(44);
					    br.append("<input type='hidden' name='strWarrantyTableInfo' id='strWarrantyTableInfo" + nTmpI + "' value='"+strWarrantyTableInfo+"'>");
					   
					    br.append("<tr>");
					   
						br.append("<td CLASS='CONTROL' style='text-align:center' width='25%'>"+wb.getString(41)+"</td>");
						br.append("<td CLASS='CONTROL' style='text-align:center' width='18%'>"+wb.getString(9)+"</td>");
						br.append("<td CLASS='CONTROL' style='text-align:center' width='18%'>"+wb.getString(44)+" ("+wb.getString(10)+""+wb.getString(42)+") </td>");
						br.append("<td CLASS='CONTROL' style='text-align:center' width='18%'>"+wb.getString(43)+"</td>");
                     // Here we Check The Empty Uploaded  file Name 								
						//String s;
						if(wb.getString(33).equals("0"))
						{
								String s;
								if(wb.getString(16).equals("")||wb.getString(16).equals(" ")||wb.getString(16).equals("   "))
						    	{
						    		s = "0";
						    		br.append("<td CLASS='CONTROL' style='text-align:center' width='21%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='onUploadedFileName(this,"+nTmpI+",\""+s+"\");'>NA</font><input type='hidden' id='strReqUpLoadFileName" + nTmpI + "' name='strReqUpLoadFileName' value='"+wb.getString(15)+"'><input type='hidden' id='strReqUpLoadFileID" + nTmpI + "' name='strReqUpLoadFileID' value='"+wb.getString(16)+"'></td>");
						    	}
						    	else
						    	{
						    		s=wb.getString(16);
						    		br.append("<td CLASS='CONTROL' style='text-align:center' width='21%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='onUploadedFileName(this,"+nTmpI+",\""+s+"\");'>"+wb.getString(15)+"</font><input type='hidden' id='strReqUpLoadFileName" + nTmpI + "' name='strReqUpLoadFileName' value='"+wb.getString(15)+"'><input type='hidden' id='strReqUpLoadFileID" + nTmpI + "' name='strReqUpLoadFileID' value='"+wb.getString(16)+"'></td>");
						    	}
				    		
						}
						else
						{
							String s;
							if(wb.getString(38).equals("")||wb.getString(38).equals(" ")||wb.getString(38).equals("   "))
					    	{
					    		s = "0";
					    		br.append("<td CLASS='CONTROL' style='text-align:center' width='21%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='onUploadedFileName(this,"+nTmpI+",\""+s+"\");'>NA</font><input type='hidden' id='strReqUpLoadFileName" + nTmpI + "' name='strReqUpLoadFileName' value='"+wb.getString(37)+"'><input type='hidden' id='strReqUpLoadFileID" + nTmpI + "' name='strReqUpLoadFileID' value='"+wb.getString(38)+"'></td>");
					    	}
					    	else
					    	{
					    		s=wb.getString(38);
					    		br.append("<td CLASS='CONTROL' style='text-align:center' width='21%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='onUploadedFileName(this,"+nTmpI+",\""+s+"\");'>"+wb.getString(37)+"</font><input type='hidden' id='strReqUpLoadFileName" + nTmpI + "' name='strReqUpLoadFileName' value='"+wb.getString(37)+"'><input type='hidden' id='strReqUpLoadFileID" + nTmpI + "' name='strReqUpLoadFileID' value='"+wb.getString(38)+"'></td>");
					    	}
							
							
							
						}
				    									
					  	  
					  		br.append("<td CLASS='CONTROL' style='text-align:center' width='7%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='openBlanketPopUp(this,"+nTmpI+",1);'>Extend</font></td>");
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
				    
					br.append("<his:ContentTag>");
				    br.append("<table class='TABLE_STYLE' align='center' border='0' cellpadding='1px' cellspacing='1px'>");
				    br.append("<tr>");
				    br.append("<td align='center' style='width: 5%'><img style='cursor: pointer;' id='imgStockDetails' src='../../hisglobal/images/minus.gif'" +
				    "onclick='showOrHidePrevWarrantyDetails(this)' title='Hide'/>" +
				    "</td><td colspan='5' style='width: 95%'><div id='Id1' style='text-align:left'>Previous Guaranty Details</div></td>");
				    br.append("</tr>");
					br.append("</table>");
					br.append("</his:ContentTag>");
				    br.append("<div id='prevWarrantyDtlDivId' style='display:block'>");
				    br.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='1px' cellspacing='1px'>");
					br.append("<TR>");
					br.append("<TD WIDTH='25%' CLASS='CONTROL' style='text-align:center'><font color = 'red'>"
								+ "No Previous Guaranty Details Found" + "</font></TD>");
					br.append("</TR>");
					br.append("</table>");
					br.append("<input type='hidden' name='prevRecordExists'  value='0'>");
					
			}
			br.append("</div>");
		   }
					
			else 
			{
				//br.append("<his:ContentTag>");
				br.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='1px' cellspacing='1px'>");
				
				br.append("<td colspan='5'  CLASS='multiControl' ><DIV class='errMsg' align='center'>No Previous Guaranty Details Found !!! </div></TD>");
	
				br.append("</table>");
				//br.append("<his:ContentTag>");
				br.append("<input type='hidden' name='prevRecordExists'  value='0'>");

			}
			br.append("<hr style='width:95%'>");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new Exception("PreviousWarrantyDATA.getPreviousWarrantyDetails()->"+e.getMessage());
			
		}
		return br.toString();
	}


}
