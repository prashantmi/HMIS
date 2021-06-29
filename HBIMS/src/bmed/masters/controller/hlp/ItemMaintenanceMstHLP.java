package bmed.masters.controller.hlp;

import javax.sql.rowset.WebRowSet;
/**
 * @author  Amit Kumar 
 * Creation Date:- 17-Jan-2011 
 * Modifying Date:- 0-0-2011
 * Used For:-	
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */
public class ItemMaintenanceMstHLP
{
	/**
	 * Gets the added Task details.
	 * 
	 * @param wb the wb
	 * 
	 * @return the added Task details
	 */
	public static String getTaskDetails(WebRowSet wb) 
	{
		StringBuffer br = new StringBuffer();
        int i=1;
		try {
			if (wb != null) {
				if (wb.size() != 0) {
					br.append("<table class='TABLEWIDTH' align='center' bgcolor='black' cellpadding='1px' cellspacing='1px' border='0'>");
					br.append("<tr> ");
					br.append("<td WIDTH='25%' class='multiLabel'>Sl No. </td>");
					br.append("<td WIDTH='75%' class='multiLabel'>Task Name</td>");
					

					br.append("</tr> ");
					br.append("</table> ");
					br.append("<table class='TABLEWIDTH' align='center' bgcolor='#CC9966' cellpadding='1px' cellspacing='1px' border='0'>");
					
					while (wb.next())
					{ 						
						br.append("<TR>");
						br.append("<TD WIDTH='25%' CLASS='multiLabel'>"+ i + "</TD>");
						br.append("<TD WIDTH='75%' CLASS='multiControl'>"+ wb.getString(2) + "</TD>");
						
						br.append("</TR>");
						i++;
					}
					
					br.append("</table> ");

				}
			}
		} catch (Exception e) {

		}
		return br.toString();
	}
	
	
	public static String getStockDetails(WebRowSet viewValuesWS)throws Exception
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
				     
					    br.append("<table width='800' align='center' cellspacing='1px' cellpadding='1px'>");
					    br.append("<tr class='TITLE'>");
					    br.append("<td align='center' style='width: 5%'><img style='cursor: pointer;' id='imgStockDetails' src='/HBIMS/hisglobal/images/plus.gif'" +
					    "onclick='showOrHideStockDetails(this)' title='Show'/>" +
					    "</td><td colspan='5' style='width: 90%'><div id='Id1' style='color:blue;'>Stock Details</div></td><td align='center' style='width: 5%'><img  style='cursor:pointer;cursor:pointer' src='../../hisglobal/images/stop.png' onclick='hideStockPopup();'></td>");
					    br.append("</tr>");
						br.append("</table>");
					 										
					 
					 br.append("<div id='prevLnkDivId' style='display:block'>");
					 					 
                     br.append("<table width='800' align='center'  border='0' cellpadding='1px' cellspacing ='1px'>");
					 br.append("<tr>");
					 br.append("<td class='LABEL'>");
					 		 
					 
					 for (int i = 1; i <= totalPLayer; i++)
					 {
						br.append("<a name='pg' id='pg" + i + "'  STYLE='cursor:pointer;cursor:pointer;' class='pg-normal' title='Record Indexing' onClick='GetIndex(\""+i+"\",\""+totalPLayer+"\")'>" + (i+start-1)
								+ "</a>|&nbsp;");
					 }
					br.append("</td></tr>");
					br.append("</table>");
					br.append("<table width='800' align='center' border='0' bgcolor='#000000' cellpadding='1px' cellspacing ='1px'>");
					br.append("<td CLASS='multiLabel' width='5%'>#</td>");
					br.append("<td CLASS='multiLabel' width='40%'>Store Name</td>");
					br.append("<td CLASS='multiLabel' width='20%'>Item Serial No</td>");
					br.append("<td CLASS='multiLabel' width='15%'>Batch No</td>");
					br.append("<td CLASS='multiLabel' width='20%'>Manufac No.</td>");
					
					br.append("</tr>");
					
					br.append("</table>");
		        String nTmpI = "0";
		      //  int count=1;
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
						nTmpI = i+""+j;
						if(wb.next())
						{
						        
							    br.append("<table width='800' align='center' bgcolor='#000000' border='0' cellpadding='1px' cellspacing ='1px'>");
								
							    br.append("<input type='hidden' name='StoreName' id='StoreName" + nTmpI + "' value='"+wb.getString(1)+"'>");
								br.append("<input type='hidden' name='ItemSlNo' id='ItemSlNo" + nTmpI + "' value='"+wb.getString(2)+"'>");
								br.append("<input type='hidden' name='BatchNo' id='BatchNo" + nTmpI + "' value='"+wb.getString(3)+"'>");
								br.append("<input type='hidden' name='ManufactNo' id='ManufactNo" + nTmpI + "' value='"+wb.getString(4)+"'>");
							    
							    br.append("<tr>");
								br.append("<td class='multiLabel' width='5%'>");
								br.append("<input type='radio' name='checkid' value='"+0+"' id='checkid"+nTmpI+"' onClick='radioBtnClick(this,\""+nTmpI+"\");getMaintenanceNameCmb(\""+wb.getString(5)+"\");'>");
								br.append("<td class='multiControl' width='40%'>"+wb.getString(1)+"</td>");
								br.append("<td class='multiControl' width='20%'>"+wb.getString(2)+"</td>");
							    br.append("<td class='multiControl' width='15%'>"+wb.getString(3)+"</td>");
								br.append("<td class='multiControl' width='20%'>"+wb.getString(4)+"</td>");
								br.append("<input type='hidden' name='stockInf'  id='stockInf" + nTmpI + "'  value='"+wb.getString(5)+"'>");
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
						nTmpI = i+""+k;
						br.append("<table width='800' align='center' bgcolor='#000000' border='0' cellpadding='1px' cellspacing ='1px'>");
								
						br.append("<input type='hidden' name='StoreName' id='StoreName" + nTmpI + "' value='"+wb.getString(1)+"'>");
						br.append("<input type='hidden' name='ItemSlNo' id='ItemSlNo" + nTmpI + "' value='"+wb.getString(2)+"'>");
						br.append("<input type='hidden' name='BatchNo' id='BatchNo" + nTmpI + "' value='"+wb.getString(3)+"'>");
						br.append("<input type='hidden' name='ManufactNo' id='ManufactNo" + nTmpI + "' value='"+wb.getString(4)+"'>");
						
						
						br.append("<tr>");
								
								br.append("<td class='multiLabel' width='5%'>");
								br.append("<input type='radio' name='checkid' value='"+0+"' id='checkid"+nTmpI+"' onClick='radioBtnClick(this,\""+nTmpI+"\");'>");
								br.append("<td class='multiControl' width='40%'>"+wb.getString(1)+"</td>");
								br.append("<td class='multiControl' width='20%'>"+wb.getString(2)+"</td>");
							    br.append("<td class='multiControl' width='15%'>"+wb.getString(3)+"</td>");
								br.append("<td class='multiControl' width='20%'>"+wb.getString(4)+"</td>");
								br.append("<input type='hidden' name='stockInf'  id='stockInf" + nTmpI + "'  value='"+wb.getString(5)+"'>");
								br.append("</tr>");
								
								
					}
					br.append("</table>");
					br.append("</div>");
					}
			   	}
			   br.append("</td>");
			   br.append("</tr>");
			   br.append("<tr class='TITLE'></tr>"); 
			   br.append("</table>");
			 }
			 else
			 {
			 	
					br.append("<table width='800' align='center' cellspacing='1px' cellpadding='1px'>");
				    br.append("<tr class='TITLE'>"); 
				    br.append("<th colspan='6' align='left'><div id='Id1' style='color:blue;'>Stock Details</div></th>");
				    br.append("<th align='right'><img  style='cursor:pointer;cursor:pointer' src='../../hisglobal/images/stop.png' onclick='hideStockPopup();'>");
					br.append("</th>");
				    br.append("</tr>");
					br.append("</table>");
				    br.append("<table width='800' align='center' cellspacing='1px'>");
					//br.append("<tr><td colspan='5' bgcolor='black'></td></tr>");
					br.append("<TR>");
					br.append("<TD WIDTH='25%' align='center' CLASS='multiControl' colspan='5'><font color = 'red'>"
								+ "No Previous Validated Details Found" + "</font></TD>");
					br.append("</TR>");
					//br.append("<tr><td colspan='5' bgcolor='black'></td></tr>");
					br.append("</table>");
				
				   
					
			}
			br.append("</div>");
		   }
					
			else 
			{
				br.append("<table class='TABLEWIDTH' align='center'  border='0' bgcolor='#000000' cellspacing ='1px'>");
				
				br.append("<td colspan='5'  CLASS='multiControl' ><DIV class='errMsg' align='center'>No Previous Validated Details !!! </div></TD>");
	
				br.append("</table>");

			}
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			throw new Exception("DocumentComparativeStatementTransHLP.getQuotedSupplierDtls()->"+e.getMessage());
			
		}
		return br.toString();
	}
	
	
public static String getWarrantyData(WebRowSet webRowSet) throws Exception {
		
		
		String strMainteContractUptoUnit = null;
		String strMainteContractUpto = null;
		String strSlNo = null;
		
		String strManufSlNo = null;
		String strManufId = null;
		
		String strItemSlNo = null;
		
		String strItemId = null;
		
		String strIsItem = null;
		String strMainteContractDate = null;
		String strDocRefNo = null;
		
		String strUploadNo = null;
		
		String strExtUploadNo = null;
		String strExtDocRefNo = null;
		
		String strExtDocRefDate = null;
		
		String strDocRefDate = null;
		String strTermNcon = null;
		String strExtTermNcon = null;
		String strIsExtended = null;
		
		String strExtendedUptoUnit = null;
		String strExtendedUpto = null;
		String strExtendedStartDate = null;
		String strMainteContractUptoUnitName = null;
		String strMainteContractExtendedUptoUnit = null;
		String strSupplierName = null;
		String strMaintenanceContractDetails = null;

		@SuppressWarnings("unused")
		String strTermsNCondition;
		String strFile;
		String strWarrantyStartDateUpto;
		String strExtentionStartDateUpto;

		StringBuffer sbWarrantyData = new StringBuffer(1000);
		int index = 0;
		try 
		{
			sbWarrantyData.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding='1px'>");
			sbWarrantyData.append("<thead><tr class='TITLE'><td align='center' style='width: 5%'>" +
					"<img style='cursor: pointer;'	id='imgWarrantyDetails' src='/HBIMS/hisglobal/images/minus.gif'	onclick=showOrHideWarrantyDtl(\'HIDE\',this); title='Hide'/></td>" +
							"<td colspan='5' style='width: 95%'>Warranty Details</td>" +
							"</tr>" +
							"</thead>" +
							"<tr>" +
							"<td class='LABEL' style='text-align: center; width: 5%'>&nbsp;</td>" +
							"<td class='LABEL' style='text-align: center; width: 19%'>Supplier" +
							"Name</td>" + 
							"<td class='LABEL' style='text-align: center; width: 19%;'>Warranty" +
							"Start Date/Upto</td>" +
							"<td class='LABEL' style='text-align: center; width: 19%'>Extention	Upto</td>" +
							"<td class='LABEL' style='text-align: center; width: 19%'>Term" +
							"&amp; Condition</td>" +
							"<td class='LABEL' style='text-align: center; width: 19%'>File</td>" +
							"</tr></table>");
			
			sbWarrantyData.append("<div id='id11' style='display:block;'>");
			sbWarrantyData.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding='1px'>");
			
			if (webRowSet.size() > 0) 
			{
				
				
				while (webRowSet.next()) 
				{

					strMainteContractUptoUnit = webRowSet.getString(1);
					strMainteContractUpto = webRowSet.getString(2);
					strSlNo = webRowSet.getString(3);
					strManufSlNo = webRowSet.getString(4);
					strManufId = webRowSet.getString(5);
					strItemSlNo = webRowSet.getString(6);
					strItemId = webRowSet.getString(7);
					strIsItem = webRowSet.getString(8);
					strMainteContractDate = webRowSet.getString(9);
					strDocRefNo = webRowSet.getString(10);
					strUploadNo = webRowSet.getString(11);
					strExtUploadNo = webRowSet.getString(12);
					strExtDocRefNo = webRowSet.getString(13);
					strExtDocRefDate = webRowSet.getString(14);
					strDocRefDate = webRowSet.getString(15);
					strTermNcon = webRowSet.getString(16);
					strExtTermNcon = webRowSet.getString(17);
					strIsExtended = webRowSet.getString(18);
					strExtendedUptoUnit = webRowSet.getString(19);
					strExtendedUpto = webRowSet.getString(20);
					strExtendedStartDate = webRowSet.getString(21);
					strMainteContractUptoUnitName = webRowSet.getString(22);
					strMainteContractExtendedUptoUnit = webRowSet.getString(23);
					strSupplierName = webRowSet.getString(24);
					
					/*
					 * Checking for null value.
					 * 
					 */
					if(strMainteContractUptoUnit==null) {
						strMainteContractUptoUnit="---";
					}
					
					
					if(strMainteContractUpto==null) {
						strMainteContractUpto="---";
					}
					if(strSlNo==null) {
						strSlNo="---";
					}
					if(strManufSlNo==null) {
						strManufSlNo="---";
					}
					if(strManufId==null) {
						strManufId="---";
					}
					if(strItemSlNo==null) {
						strItemSlNo="---";
					}
					if(strItemId==null) {
						strItemId="---";
					}
					if(strIsItem==null) {
						strIsItem="---";
					}
					if(strMainteContractDate==null) {
						strMainteContractDate="---";
					}
					if(strDocRefNo==null) {
						strDocRefNo="---";
					}
					if(strUploadNo==null) {
						strUploadNo="---";
					}
					if(strExtUploadNo==null) {
						strExtUploadNo="---";
					}
					if(strExtDocRefNo==null) {
						strExtDocRefNo="---";
					}
					if(strExtDocRefDate==null) {
						strExtDocRefDate="---";
					}
					
					if(strDocRefDate==null) {
						strDocRefDate="---";
					}
					if(strTermNcon==null) {
						strTermNcon="---";
					}
					if(strExtTermNcon==null) {
						strExtTermNcon="---";
					}
					if(strIsExtended==null) {
						strIsExtended="---";
					}
					if(strExtendedUptoUnit==null) {
						strExtendedUptoUnit="---";
					}
					if(strExtendedUpto==null) {
						strExtendedUpto="---";
					}
					if(strExtendedStartDate==null) {
						strExtendedStartDate="---";
					}
					if(strMainteContractUptoUnitName==null) {
						strMainteContractUptoUnitName="---";
					}
					if(strMainteContractExtendedUptoUnit==null) {
						strMainteContractExtendedUptoUnit="---";
					}
					if(strSupplierName==null) {
						strSupplierName="---";
					}
					
					

					if ("1".equals(strIsExtended)) {
						strTermsNCondition = strExtTermNcon;
						strFile = strExtUploadNo;
					} else {
						strTermsNCondition = strTermNcon;
						strFile = strUploadNo;
					}

					strWarrantyStartDateUpto = strMainteContractDate + "/ "
							+ strMainteContractUpto + " " + strMainteContractUptoUnitName;
					strExtentionStartDateUpto = strExtendedStartDate + "/ "
							+ strExtendedUpto + " "
							+ strMainteContractExtendedUptoUnit;
					strMaintenanceContractDetails = strManufId + "^" + strSlNo
							+ "^"+"WARRANTY";
					
					
														
					sbWarrantyData
							.append("<tr><td class='LABEL' style='text-align: center; width: 5%'><input type='radio' value='"
									+ strMaintenanceContractDetails
									+ "' name='strMaintenanceContractDetails' /></td><td class='CONTROL' style='text-align: center; width: 19%'>"
									+ strSupplierName
									+ "</td><td class='CONTROL' style='text-align: center; width: 19%'>"
									+ strWarrantyStartDateUpto
									+ "/</td><td class='CONTROL' style='text-align: center; width: 19%'>"
									+ strExtentionStartDateUpto
									+ "</td><td class='CONTROL' style='text-align: center; width: 19%'>"
									+ strTermNcon
									+ "</td><td class='CONTROL' style='text-align: center; width: 19%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='onUploadedFileName(this,"+index+",\""+strFile+"\");'>"
									+ strFile + "</a></td></tr>");
					index++;
				}
				sbWarrantyData.append("</table></div>");
			} 
			else 
			{
				sbWarrantyData.append("<tr><td class='CONTROL' colspan='6' style='text-align: center;color: red;'>No Record Found.</td></tr>");
				sbWarrantyData.append("</table></div>");
			}
			
			
		} catch (Exception e) {
			throw new Exception(
					"MainteWarrantyContractDATA.getWarrantyData() --> "
							+ e.getMessage());
		} finally {
			if (webRowSet != null) {
				webRowSet.close();
			}
		}

		return sbWarrantyData.toString();

	}

	public static String getMainteContractData(WebRowSet webRowSet) throws Exception {
		
		String strSlNo;                                         
		String strTermNCon;                                         
//		String strIsItem;                                         
//		String strMcName;                                         
		String strManufId;                                         
		//String strPenaltyCon;                                         
		String strStartDate;                                         
		String strEndDate;                                         
		String strUploadNo;                                         
		String strDocRefNo;                                         
		//String strDocRefDate;
		String strMaintenanceContractDetails;
		String strSupplierName;

		StringBuffer sbMainteContractData = new StringBuffer(1000);
		int index = 0;
		try 
		{
			
			sbMainteContractData.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'" +
					"cellpadding='1px'>" +
					"<thead>" +
					"<tr class='TITLE'>" +
					"<td align='center' style='width: 5%'><img style='cursor: pointer;' id='imgMaintenanceContractDetails' src='/HEMMS_ODISHA/hisglobal/images/minus.gif' " +
					"onclick=showOrHideMaintenanceContractDtl(\'HIDE\',this); title='Hide' /></td>" +
					"<td colspan='5' style='width: 95%'>Maintenance Contract Details</td>" +
					"</tr>" +
					"</thead>");
			sbMainteContractData.append("<tr>" +
					"<td class='LABEL' style='text-align: center; width: 5%'>&nbsp;</td>" +
					"<td class='LABEL' style='text-align: center; width: 19%'>Supplier" +
					"Name</td>" +
					"<td class='LABEL' style='text-align: center; width: 19%'>Start" +
					"Date</td>" +
					"<td class='LABEL' style='text-align: center; width: 19%'>End" +
					"Date</td>" +
					"<td class='LABEL' style='text-align: center; width: 19%'>Tern" +
					"&amp; Condition</td>" +
					"<td class='LABEL' style='text-align: center; width: 19%'>File</td>" +
					"</tr></table>");
			
			sbMainteContractData.append("<div id='id22' style='display:block;'>");
			sbMainteContractData.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding='1px'>");
			if (webRowSet.size() > 0) 
			{
				while (webRowSet.next()) 
				{

					 strSlNo=webRowSet.getString(1);                                         
					 strTermNCon=webRowSet.getString(2);                                         
//					 strIsItem=webRowSet.getString(3);                                         
//					 strMcName=webRowSet.getString(4);                                         
					 strManufId=webRowSet.getString(5);                                         
					 //strPenaltyCon=webRowSet.getString(6);                                         
					 strStartDate=webRowSet.getString(7);                                         
					 strEndDate=webRowSet.getString(8);                                         
					 strUploadNo=webRowSet.getString(9);                                         
					 strDocRefNo=webRowSet.getString(10);                                         
					// strDocRefDate=webRowSet.getString(11);
					 strSupplierName=webRowSet.getString(12);
					

					
					strMaintenanceContractDetails = strManufId + "^" + strSlNo
							+ "^"+"MC";
					
					
					
					
					sbMainteContractData
							.append("<tr><td class='LABEL' style='text-align: center; width: 5%'><input type='radio' value='"
									+ strMaintenanceContractDetails
									+ "' name='strMaintenanceContractDetails' /></td><td class='CONTROL' style='text-align: center; width: 19%'>"
									+ strSupplierName
									+ "</td><td class='CONTROL' style='text-align: center; width: 19%'>"
									+ strStartDate
									+ "/</td><td class='CONTROL' style='text-align: center; width: 19%'>"
									+ strEndDate
									+ "</td><td class='CONTROL' style='text-align: center; width: 19%'>"
									+ strTermNCon
									+ "</td><td class='CONTROL' style='text-align: center; width: 19%'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='onUploadedFileName(this,"+index+",\""+strDocRefNo+"\");'>"
									+ strUploadNo + "</a></td></tr>");
					index++;
				}

				sbMainteContractData.append("</table></div>");
			} 
			else 
			{
				sbMainteContractData.append("<tr><td class='CONTROL' colspan='6' style='text-align: center;color: red;'>No Record Found.</td></tr>");
				sbMainteContractData.append("</table></div>");
			}
			sbMainteContractData.append("</div></table>");
		} catch (Exception e) {
			throw new Exception(
					"MainteMainteContractContractDATA.getMainteContractData() --> "
							+ e.getMessage());
		} finally {
			if (webRowSet != null) {
				webRowSet.close();
			}
		}
		return sbMainteContractData.toString();
	}
	
	
}
