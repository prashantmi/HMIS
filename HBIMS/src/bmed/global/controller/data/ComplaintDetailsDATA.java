package bmed.global.controller.data;

import javax.sql.rowset.WebRowSet;

public class ComplaintDetailsDATA 
{
	public static String getComplaintsDetails(WebRowSet viewValuesWS,String strMode)throws Exception
	{

		StringBuffer br = new StringBuffer("");
		//String strTableInfo;
		WebRowSet wb = null;
		int start = 1;
		final int REC_PER_PAGE = 10;
		final int PAGE_PER_BLOCK = 10;
		
		int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
		int totalRecordsToManipulate = totalFetchRecord - 1;
		try 
		{
			wb = viewValuesWS;
			
            /*New Function Values in WebRowSet
             * HEMNUM_REQ_ID,
             * HEMNUM_REQ_DATE,
             * GNUM_HOSPITAL_CODE,
             * HEMNUM_P_WAREHOUSE_ID,
             * HEMNUM_C_WAREHOUSE_ID,
             * HEMNUM_ITEM_ID,
             * HEMNUM_ITEMBRAND_ID,
             * HEMNUM_HEM_FLAG,
             * HEMSTR_BATCH_NO,
             * HEMSTR_COMPLAINT_DESC,
             * HEMNUM_COST, 
             * HEMDT_CLOSED_DATE, 
             * HEMSTR_CLOSE_REASON,
             * GSTR_REMARKS,
             * HEMSTR_VERIFIED_REMARKS		
               Old Functions value in WebRowSet----
                HEMNUM_REQ_ID:                 
				GNUM_HOSPITAL_CODE:            
				HEMNUM_REQ_TYPE:               
				HEMNUM_IS_ATTACHED:            
				HEMNUM_IS_ITEM:                
				HEMNUM_ENGG_ITEM_TYPE_ID:      
				HEMNUM_IS_PREVENTIVE:          
				HEMNUM_IS_ONLINE:              
				HEMNUM_ENGG_ITEM_SUB_TYPE_ID:  
				HEMNUM_ITEM_ID:                
				HEMNUM_HEM_FLAG:               
				HEMSTR_SERIAL_NO:              
				HEMNUM_REQ_DATE:               
				HEMSTR_MANUF_SERIAL_NO:        
				HEMNUM_STORE_ID:               
				HEMNUM_LANDMARK_DESC:          
				HEMSTR_BATCH_NO:               
				HEMNUM_WARRANTY_SLNO:          
				HEMNUM_MAIN_STATUS:            
				HEMNUM_MC_SLNO:                
				HEMNUM_VENDOR_ID:              
				HEMNUM_MAINTE_ID:              
				HEMSTR_COMPLAINT_DES:          
				HEMSTR_EMP_ID:                 
				HEMNUM_DEPT_ID:                
				HEMSTR_PREFER_TIME_FR:         
				HEMSTR_PREFER_TIME_TO:         
				HEMSTR_DOWN_TIME_FR:           
				HEMSTR_CONTACT_PERSON:         
				HEMNUM_IS_WORKING:             
				HEMSTR_CONTACT_NO:             
				HEMDT_NOTWORKING_DATE:         
				GSTR_REMARKS:                  
				GDT_ENTRY_DATE:                
				HEMDT_COMP_INTEMATION:         
				GNUM_ISVALID:                  
				GNUM_SEATID:                   
				HEMNUM_SUB_STATUS:             
				GSTR_STATUS_REMARKS:           
				HEMNUM_COST:                   
				HEMSTR_VERIFIED_ID:            
				HEMSTR_VERIFIED_REMARKS:       
				HEMNUM_CANCEL_ID:              
				HEMDT_CANCEL_DATE:             
				HEMSTR_CANCEL_REMARKS:         
				HEMNUM_CANCEL_SEATID:          
				HEMDT_CLOSED_DATE:             
				HEMDT_CLOSED_SEATID:           
				HEMSTR_CLOSE_REASON:           
				HEMSTR_VENDOR_INVOICE_NO:      
				ENGG_ITEM_TYPE_NAME:           
				ENGG_ITEM_SUB_TYPE_NAME:       
				ITEM_NAME:                     
				STORE_NAME:                    
				STATUS_NAME:                   
				VENDOR_NAME:                   
				MAINTENANCE_NAME:              
				SERVICE_ENGG_NAME:             
				DEPT_NAME:                     
         
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
			//	 VIVEK
/*				    strTableInfo = wb.getString("1")+"^"+ wb.getString("2")+"^"+wb.getString("3")+"^"+wb.getString("4")+"^"+wb.getString("5")+"^"+wb.getString("6")+"^"+wb.getString("7")+"^"+wb.getString("8")+"^"+wb.getString("9")+"^"+wb.getString("10")+"^"+wb.getString("11")+"^"+wb.getString("12")+"^"+wb.getString("13")+"^"+wb.getString("14")+"^"+wb.getString("15")+"^"+wb.getString("16")+"^"+
				    wb.getString("17")+"^"+wb.getString("18")+"^"+wb.getString("19")+"^"+wb.getString("20")+"^"+wb.getString("21")+"^"+wb.getString("22")+"^"+wb.getString("23")+"^"+wb.getString("24")+"^"+wb.getString("25")+"^"+ wb.getString("26")+"^"+ wb.getString("27")+"^"+wb.getString("28")+"^"+wb.getString("29")+"^"+wb.getString("30")+"^"+wb.getString("31")+"^"+wb.getString("32")+"^"+wb.getString("33")+"^"+wb.getString("34")+"^"+wb.getString("35")+"^"+wb.getString("36")+"^"+wb.getString("37")+"^"+wb.getString("38")+"^"+wb.getString("39")+"^"+wb.getString("40")+"^"+wb.getString("41")+"^"+
				    wb.getString("42")+"^"+wb.getString("43")+"^"+wb.getString("44")+"^"+wb.getString("45")+"^"+wb.getString("46")+"^"+wb.getString("47")+"^"+wb.getString("48")+"^"+wb.getString("49")+"^"+wb.getString("50")+"^"+wb.getString("51")+"^"+ wb.getString("52")+"^"+wb.getString("53")+"^"+wb.getString("54")+"^"+wb.getString("55")+"^"+wb.getString("56")+"^"+wb.getString("57")+"^"+wb.getString("58")+"^"+wb.getString("59");
*/			//	    
				 
				 
				 
				 
				 
				    br.append("<input type='hidden' name='fetchRecord'  value='"+actualFetchRecord+"'>");
				    br.append("<input type='hidden' name='fetchRecord'  value='"+actualFetchRecord+"'>");
				    br.append("<input type='hidden' name='TotalLayer'  value='"+totalPLayer+"'>");
				  //  br.append("<his:ContentTag>");
				    br.append("<div class='line'><table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'  border='0'>");
				    br.append("<tr class='line'>");
				    br.append("<td align='center' style='width: 5%'><img style='cursor: pointer;' id='imgStockDetails' src='../../hisglobal/images/plus.gif'" +
				    "onclick='showOrHideComplaintDetails(this)' title='Show'/>" +
				    "</td><td colspan='5' style='width: 95%'><div id='Id1' style='text-align:left'>Previous Complaint Details</div></td>");
				    br.append("</tr>");
					br.append("</table></div>");
					//br.append("</his:ContentTag>");
				 
				 br.append("<div id='prevComplaintDtlDivId' style='display:none'>");
				// br.append("<his:ContentTag>");					 
                 br.append("<table class='TABLEWIDTH' align='center'  border='0' cellpadding='1px' cellspacing ='1px'>");
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
					
					br.append("<table class='TABLEWIDTH'>");
					br.append("<td CLASS='LABEL' style='text-align:center' width='15%'>Complaint Id</td>");
					br.append("<td CLASS='LABEL' style='text-align:center' width='20%'>Complaint Date & Time</td>");
					br.append("<td CLASS='LABEL' style='text-align:center' width='20%'>Complaint Resolve Date</td>");
					br.append("<td CLASS='LABEL' style='text-align:center' width='20%'>Complaint Description</td>");
					br.append("<td CLASS='LABEL' style='text-align:center' width='20%'>Resolution Description</td>");
					br.append("<td CLASS='LABEL' style='text-align:center' width='10%'>Cost</td>");
					
					
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
					br.append("<table class='TABLE_STYLE'>");
					for (int j = 0; j < REC_PER_PAGE; j++) 
					{
						nTmpI = i+""+j;
						if(wb.next())
						{
						        
							    br.append("<table class='TABLEWIDTH'>");
							    
															    
							    br.append("<tr>");
							   
								br.append("<td CLASS='CONTROL' style='text-align:center' width='16%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='onUploadedFileName(this,"+nTmpI+",\""+0+"\");'/>"+wb.getString("HEMNUM_REQ_ID")+"</td>");
								br.append("<td CLASS='CONTROL' style='text-align:center' width='20%'>"+wb.getString("HEMNUM_REQ_DATE")+"</td>");
								br.append("<td CLASS='CONTROL' style='text-align:center' width='20%'>"+wb.getString("HEMDT_CLOSED_DATE")+"</td>");
								br.append("<td CLASS='CONTROL' style='text-align:center' width='20%'>"+wb.getString("HEMSTR_COMPLAINT_DES")+"</td>");
								br.append("<td CLASS='CONTROL' style='text-align:center' width='20%'>"+wb.getString("HEMSTR_CLOSE_REASON")+"</td>");
								br.append("<td CLASS='CONTROL' style='text-align:center' width='10%'>"+wb.getString("HEMNUM_COST")+"</td>");
                                //br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='29%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='onUploadedFileName(this,"+nTmpI+",\""+0+"\");'>"+wb.getString("HEMNUM_MAIN_STATUS")+"</font></td>");
                               
                                
                                if(wb.isLast()){
                                	br.append("<input type='hidden' name='strPreviousComplaintDetails' value='" + wb.getString("HEMNUM_REQ_ID")+"^"+wb.getString("HEMNUM_REQ_DATE")+ "' />");
                                }							
								br.append("</tr>");
								br.append("</table>");
								
						//		br.append("</his:ContentTag>");
								
						}
						else
						{
							break;
						}
					}
					br.append("</table>");
				//	br.append("</his:ContentTag>");
					br.append("</div>");

				} 
				else 
				{
					br.append("<div id='DivId" + i+ "' style='display:none'>");
				//	br.append("<his:ContentTag>");
					br.append("<table class='TABLEWIDTH'>");
					for (int k = 0; k < reminder; k++) 
					{
						wb.next();
						nTmpI = i+""+k;
						br.append("<table class='TABLEWIDTH'>");
						
		    
					    br.append("<tr>");
					   
						br.append("<td CLASS='CONTROL' style='text-align:center' width='10%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='onUploadedFileName(this,"+nTmpI+",\""+0+"\");'/>"+wb.getString("HEMNUM_REQ_ID")+"</td>");
						br.append("<td CLASS='CONTROL' style='text-align:center' width='20%'>"+wb.getString("HEMNUM_REQ_DATE")+"</td>");
						br.append("<td CLASS='CONTROL' style='text-align:center' width='20%'>"+wb.getString("HEMDT_CLOSED_DATE")+"</td>");
						br.append("<td CLASS='CONTROL' style='text-align:center' width='20%'>"+wb.getString("HEMSTR_COMPLAINT_DESC")+"</td>");
						br.append("<td CLASS='CONTROL' style='text-align:center' width='20%'>"+wb.getString("HEMSTR_CLOSE_REASON")+"</td>");
						br.append("<td CLASS='CONTROL' style='text-align:center' width='10%'>"+wb.getString("HEMNUM_COST")+"</td>");
                        //br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='29%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='onUploadedFileName(this,"+nTmpI+",\""+0+"\");'>"+wb.getString("HEMNUM_MAIN_STATUS")+"</font></td>");
                        if(wb.isLast()){
                        	br.append("<input type='hidden' name='strPreviousComplaintDetails' value='" + wb.getString("HEMNUM_REQ_ID")+"^"+wb.getString("HEMNUM_REQ_DATE")+ "' />");
                        }
						br.append("</tr>");
						br.append("</table>");
					//	br.append("</his:ContentTag>");
						
					}
					br.append("</table>");
				//	br.append("</his:ContentTag>");
					br.append("</div>");
					}
			   	}
			   br.append("</td>");
			   br.append("</tr>");
			   br.append("<tr class='TITLE'></tr>"); 
			   br.append("</table>");
			//   br.append("</his:ContentTag>");
			 }
			 else
			 {
				 
				  
				    
				  //  br.append("<his:ContentTag>");
				    br.append("<div class='line'><table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'  border='0'>");
				    br.append("<tr class='line'>");
				    br.append("<td align='center' style='width: 5%'><img style='cursor: pointer;' id='imgStockDetails' src='../../hisglobal/images/plus.gif'" +
				    "onclick='showOrHidePrevMaintenanceDetails(this)' title='Show'/>" +
				    "</td><td colspan='5' style='width: 95%'><div id='Id1' style='text-align:left'>Previous Complaint Details</div></td>");
				    br.append("</tr>");
					br.append("</table></div>");
					//br.append("</his:ContentTag>");
				    br.append("<div id='prevComplaintDtlDivId' style='display:none'>");
					//br.append("</his:ContentTag>");
					//br.append("<his:ContentTag>");
				    br.append("<table class='TABLEWIDTH'>");
					//br.append("<tr><td colspan='5' bgcolor='black'></td></tr>");
					br.append("<TR>");
					br.append("<TD WIDTH='25%' CLASS='CONTROL' style='text-align:center'><font color = 'red'>"
								+ "No Previous Complaint Details" + "</font></TD>");
					br.append("</TR>");
					//br.append("<tr><td colspan='5' bgcolor='black'></td></tr>");
					br.append("</table>");
					//br.append("<his:ContentTag>");
					
				
				   
					
			}
			br.append("</div>");
		   }
					
			else 
			{
				//br.append("<his:ContentTag>");
				br.append("<table class='TABLEWIDTH' align='center'  border='0' bgcolor='#000000' cellspacing ='1px'>");
				
				br.append("<td colspan='5'  CLASS='multiControl' ><DIV class='errMsg' align='center'>No Previous Complaint Details !!! </div></TD>");
				br.append("<input type='hidden' name='strPreviousComplaintDetails' value='NA^NA' />");
	
				br.append("</table>");
			//	br.append("<his:ContentTag>");

			}
		}
		catch (Exception e) 
		{
//			e.printStackTrace();
			throw new Exception("DocumentComparativeStatementTransHLP.getQuotedSupplierDtls()->"+e.getMessage());
			
		}
		return br.toString();
	}


}
