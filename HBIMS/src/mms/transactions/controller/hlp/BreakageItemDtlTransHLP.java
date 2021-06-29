package mms.transactions.controller.hlp;

import hisglobal.exceptions.HisException;

import javax.sql.rowset.WebRowSet;

import mms.MmsConfigUtil;

public class BreakageItemDtlTransHLP {

	public static String getBreakageDetails(WebRowSet ws , String strStoreId , String strHospitalCode) 
	 {
		StringBuffer sb = new StringBuffer("");
		int start = 1;
		final int REC_PER_PAGE = 10;
		final int PAGE_PER_BLOCK = 10;
		
		MmsConfigUtil mcu = null;
		
		int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
		int totalRecordsToManipulate = totalFetchRecord - 1;
		
	    try
	    {
	    	mcu = new MmsConfigUtil(strHospitalCode);
	    	
	    	String[] strStartYr = mcu.getStrFinancialStartDate(strStoreId , strHospitalCode ).replace('-','@').split("@");
	    	String[] strEndYr = mcu.getStrFinancialEndDate(strStoreId , strHospitalCode ).replace('-','@').split("@");
	    	
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
			 sb.append("<td class='TITLE' >Breakage/Lost Details ");
			 sb.append("</td></tr>");
			 sb.append("<tr>");
			 sb.append("<td class='LABEL' >");

			 for (int i = 1; i <= totalLayer; i++)
			 {
				sb.append("<a STYLE='cursor:pointer;cursor:pointer;' color:'blue' onClick='GetIndex(\"").append(i).append("\",\"").append(totalLayer).append("\")'>" ).append( (i+start-1)
						).append( "</a> &nbsp;");
			 }
			sb.append("</td>");
			sb.append("</tr>");
		//	sb.append("<tr><td class='LABEL'><div id='pageNavPosition'></div></td></tr>");
			sb.append("</table>");
			sb.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px' bgcolor='black'>");
	        sb.append("<tr>");
	        sb.append("<td width='10%'class='multiLabel'>Date");
		    sb.append("</td>");
		    sb.append("<td width='7%'class='multiLabel'>Type");
		    sb.append("</td>");
	        sb.append("<td width='15%'class='multiLabel'>Item Name");
	        sb.append("</td>");
	        sb.append("<td width='15%'class='multiLabel'>Batch Sl. No");
	        sb.append("</td>");
	        sb.append("<td width='17%'class='multiLabel'>Rate/Unit");
	        sb.append("</td>");
	        sb.append("<td width='21%'class='multiLabel'>Breakage Qty");
		    sb.append("</td>");
		    sb.append("<td width='15%'class='multiLabel'>Cost");
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
				
				sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' bgcolor='black' cellpadding='1px'>");
				for (int j = 0; j < REC_PER_PAGE; j++) 
				{
					if(ws.next()){
					sb.append("<tr>");
					sb.append("<td class='multiControl' width='10%'>");
					sb.append(ws.getString(1));
					sb.append("</td>");
					sb.append("<td class='multiControl' width='7%'>");
					sb.append(ws.getString(9));
					sb.append("</td>");
					sb.append("<td class='multiControl' width='15%'>");
					sb.append(ws.getString(2));
					sb.append("</td>");
					sb.append("<td class='multiControl' width='15%'>");
					sb.append(ws.getString(3));
					sb.append("</td>");
					sb.append("<td class='multiControl' width='17%'>");
					sb.append(ws.getString(4)).append("/").append(ws.getString(5));
					sb.append("</td>");
					sb.append("<td class='multiControl' width='21%'>");
					sb.append(ws.getString(6)).append(" ").append(ws.getString(7));
					sb.append("</td>");
					sb.append("<td class='multiControl' width='15%'>");
					sb.append(ws.getString(8));
					sb.append("</td>");
					sb.append("</tr>");
					}else{
						break;
					}
				}
				sb.append("</table>");
				sb.append("</div>");

			} 
			else 
			{
				sb.append("<div id='DivId" ).append( i).append( "' style='display:none'>");
				
				sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' bgcolor='black' cellpadding='1px'>");
				for (int k = 0; k < reminder; k++) 
				{
					ws.next();
					sb.append("<tr>");
					sb.append("<td class='multiControl' width='17%'>");
					sb.append(ws.getString(1));
					sb.append("</td>");
					sb.append("<td class='multiControl' width='15%'>");
					sb.append(ws.getString(2));
					sb.append("</td>");
					sb.append("<td class='multiControl' width='15%'>");
					sb.append(ws.getString(3));
					sb.append("</td>");
					sb.append("<td class='multiControl' width='17%'>");
					sb.append(ws.getString(4)).append("/").append(ws.getString(5));
					sb.append("</td>");
					sb.append("<td class='multiControl' width='21%'>");
					sb.append(ws.getString(6)).append(" ").append(ws.getString(7));
					sb.append("</td>");
					sb.append("<td class='multiControl' width='15%'>");
					sb.append(ws.getString(8));
					sb.append("</td>");
					sb.append("</tr>");
				
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
			sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' bgcolor='black' cellpadding='1px'>");
			 sb.append("<tr>");
			 sb.append("<td class='TITLE' >Breakage Details For Financial Year : ").append(strStartYr[2]).append("-").append(strEndYr[2]);
			 sb.append("</td></tr>");
			sb.append("<tr>");
			sb.append("<td class='multiControl'><font color='red'>No Record Found</font></td>");
			sb.append("</tr>");
			sb.append("</table>");
		}
	   } 
		
	 }
	 catch(Exception e)
	 {
		    e.printStackTrace();
		    new HisException("Breakage Details Transaction","BreakageItemDtlTransHLP.getBreakageDetails()-->",e.getMessage());
	    }
	     //System.out.println("sb"+sb);
	    return sb.toString();
	 	}


	public static String getBreakageDetailsNEW(WebRowSet ws , String strStoreId , String strHospitalCode) 
	 {
		StringBuffer sb = new StringBuffer("");
		int start = 1;
		final int REC_PER_PAGE = 10;
		final int PAGE_PER_BLOCK = 10;
		
		MmsConfigUtil mcu = null;
		
		int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
		int totalRecordsToManipulate = totalFetchRecord - 1;
		
	    try
	    {
	    	mcu = new MmsConfigUtil(strHospitalCode);
	    	
	    	String[] strStartYr = mcu.getStrFinancialStartDate(strStoreId , strHospitalCode ).replace('-','@').split("@");
	    	String[] strEndYr = mcu.getStrFinancialEndDate(strStoreId , strHospitalCode ).replace('-','@').split("@");
	    	
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
			 sb.append("<table class='table table-striped' align='center' cellspacing='1px'>");
			 sb.append("<tr>");
			 sb.append("<td class='' style='bgcolor:white; color:#666;font-weight: bold;text-align:left'>Breakage/Lost Details ");
			 sb.append("</td></tr>");
			 sb.append("<tr>");
			 sb.append("<td class='LABEL' >");

			 for (int i = 1; i <= totalLayer; i++)
			 {
				sb.append("<a STYLE='cursor:pointer;cursor:pointer;' color:'blue' onClick='GetIndex(\"").append(i).append("\",\"").append(totalLayer).append("\")'>" ).append( (i+start-1)
						).append( "</a> &nbsp;");
			 }
			sb.append("</td>");
			sb.append("</tr>");
		//	sb.append("<tr><td class='LABEL'><div id='pageNavPosition'></div></td></tr>");
			sb.append("</table>");
			sb.append("<table class='table text-uppercase' style='font-weight:bold;' align='center'cellpadding='1px' cellspacing='1px' bgcolor='black'>");
	        sb.append("<tr>");
	        sb.append("<td width='10%'class=''>Date");
		    sb.append("</td>");
		    sb.append("<td width='7%'class=''>Type");
		    sb.append("</td>");
	        sb.append("<td width='15%'class=''>Item Name");
	        sb.append("</td>");
	        sb.append("<td width='15%'class=''>Batch Sl. No");
	        sb.append("</td>");
	        sb.append("<td width='17%'class=''>Rate/Unit");
	        sb.append("</td>");
	        sb.append("<td width='21%'class=''>Breakage Qty");
		    sb.append("</td>");
		    sb.append("<td width='15%'class=''>Cost");
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
				
				sb.append("<table id='example' class='table table-striped text-uppercase' align='center' cellspacing='1px' bgcolor='black' cellpadding='1px'>");
				for (int j = 0; j < REC_PER_PAGE; j++) 
				{
					if(ws.next()){
					sb.append("<tr>");
					sb.append("<td class='' width='10%'>");
					sb.append(ws.getString(1));
					sb.append("</td>");
					sb.append("<td class='' width='7%'>");
					sb.append(ws.getString(9));
					sb.append("</td>");
					sb.append("<td class='' width='15%'>");
					sb.append(ws.getString(2));
					sb.append("</td>");
					sb.append("<td class='' width='15%'>");
					sb.append(ws.getString(3));
					sb.append("</td>");
					sb.append("<td class='' width='17%'>");
					sb.append(ws.getString(4)).append("/").append(ws.getString(5));
					sb.append("</td>");
					sb.append("<td class='' width='21%'>");
					sb.append(ws.getString(6)).append(" ").append(ws.getString(7));
					sb.append("</td>");
					sb.append("<td class='' width='15%'>");
					sb.append(ws.getString(8));
					sb.append("</td>");
					sb.append("</tr>");
					}else{
						break;
					}
				}
				sb.append("</table>");
				sb.append("</div>");

			} 
			else 
			{
				sb.append("<div id='DivId" ).append( i).append( "' style='display:none'>");
				
				sb.append("<table id='example' class='table table-striped text-uppercase' align='center' cellspacing='1px' bgcolor='black' cellpadding='1px'>");
				for (int k = 0; k < reminder; k++) 
				{
					ws.next();
					sb.append("<tr>");
					sb.append("<td class='' width='17%'>");
					sb.append(ws.getString(1));
					sb.append("</td>");
					sb.append("<td class='' width='15%'>");
					sb.append(ws.getString(2));
					sb.append("</td>");
					sb.append("<td class='' width='15%'>");
					sb.append(ws.getString(3));
					sb.append("</td>");
					sb.append("<td class='' width='17%'>");
					sb.append(ws.getString(4)).append("/").append(ws.getString(5));
					sb.append("</td>");
					sb.append("<td class='' width='21%'>");
					sb.append(ws.getString(6)).append(" ").append(ws.getString(7));
					sb.append("</td>");
					sb.append("<td class='' width='15%'>");
					sb.append(ws.getString(8));
					sb.append("</td>");
					sb.append("</tr>");
				
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
			sb.append("<table class='table table-striped text-uppercase' cellspacing='1px' bgcolor='black' cellpadding='1px'>");
			 sb.append("<tr>");
			 sb.append("<td class='' style='bgcolor:white; color:#666;font-weight: bold;text-align:left'><label>Breakage Details For Financial Year : ").append(strStartYr[2]).append("-").append(strEndYr[2]);
			 sb.append("</label></td></tr>");
			sb.append("<tr>");
			sb.append("<td class=''><font color='red'>No Record Found</font></td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			
			
			}
	   } 
		
	 }
	 catch(Exception e)
	 {
		    e.printStackTrace();
		    new HisException("Breakage Details Transaction","BreakageItemDtlTransHLP.getBreakageDetails()-->",e.getMessage());
	    }
	     //System.out.println("sb"+sb);
	    return sb.toString();
	 	}
	
	
}
