package billing.reports;

import hisglobal.exceptions.HisException;

import javax.sql.rowset.WebRowSet;

import billing.reports.DayEndDuplicateRptVO;

public class DayEndDuplicateRptHLP {
	
	public static String getSummaryDetails(DayEndDuplicateRptVO vo) 
	{
			StringBuffer sb = new StringBuffer("");
			WebRowSet ws = null;
			ws = vo.getStrSummaryDtlWs();			
			int start = 1;
			final int REC_PER_PAGE = 10;
			final int PAGE_PER_BLOCK = 10;
			
			int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
			int totalRecordsToManipulate = totalFetchRecord - 1;
			
		    try
		    {
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
					 
					 	 sb.append("<table width='600' align='center' cellspacing='1px'>");
					 	 sb.append("<tr>");
					 	 sb.append("<td class='TITLE' >Scroll Details ");
					 	 sb.append("</td></tr>");
					 	 sb.append("<tr>");
					 	 sb.append("<td class='LABEL' >");		
					 				 
						 for (int i = 1; i <= totalLayer; i++)
						 {
							 if(i == 1){
							sb.append("<a STYLE='CURSOR:POINTER; color:red' name='linId' id='linId").append(i).append("'  onClick='GetIndex(\"").append(i).append("\",\"").append(totalLayer).append("\")'>" ).append( (i+start-1)
									).append( "</a> &nbsp;");
							 }else{
								 
								 sb.append("<a STYLE='CURSOR:POINTER; color:blue' name='linId' id='linId").append(i).append("'  onClick='GetIndex(\"").append(i).append("\",\"").append(totalLayer).append("\")'>" ).append( (i+start-1)
											).append( "</a> &nbsp;");
							 }
						 }
						sb.append("</td>");
						sb.append("</tr>");
					//	sb.append("<tr><td class='LABEL'><div id='pageNavPosition'></div></td></tr>");
						sb.append("</table>");
						sb.append("<table width='600' align='center' cellspacing='1px'>");
						sb.append("<tr><td width='5%' class='multiLabel'>#</td>");
						sb.append("<td width='20%' class='multiLabel'>Scroll No.</td>");
						sb.append("<td width='30%' class='multiLabel'>Scroll Date</td>");
						//sb.append("<td width='15%' class='multiLabel'>Session</td>");
						sb.append("<td width='25%' class='multiLabel'>Counter Name</td>");
						sb.append("<td width='25%' class='multiLabel'>User Name</td>");
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
								
								sb.append("<table width='600' align='center' cellspacing='1px'>");
								for (int j = 0; j < REC_PER_PAGE; j++) 
								{
									if(ws.next()){
										sb.append("<tr><td width='5%' class='multiLabel'><input type='radio' name='strSummNoVal' value='" + ws.getString(1) + "'></td>");
										sb.append("<td width='20%' class='multiControl'>");
										sb.append(ws.getString(1));
										sb.append("</td>");
										sb.append("<td width='30%' class='multiControl'>");
										sb.append(ws.getString(2));
										sb.append("</td>");
										//sb.append("<td width='15%' class='multiControl'>");
										//sb.append(ws.getString(3));
										//sb.append("</td>");
										sb.append("<td width='25%' class='multiControl'>");
										sb.append(ws.getString(4));
										sb.append("<input type='hidden' name='strScrollDate' value='"+ws.getString(2)+"'></td>");
										sb.append("<td width='25%' class='multiControl'>");
										sb.append(ws.getString(5));
										sb.append("</td></tr>");
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
								
								sb.append("<table width='600' align='center' cellspacing='1px'>");
								for (int k = 0; k < reminder; k++) 
								{
									ws.next();
									sb.append("<tr><td width='5%' class='multiLabel'><input type='radio' name='strSummNoVal' value='" + ws.getString(1) + "'></td>");
									sb.append("<td width='20%' class='multiControl'>");
									sb.append(ws.getString(1));
									sb.append("</td>");
									sb.append("<td width='30%' class='multiControl'>");
									sb.append(ws.getString(2));
									sb.append("</td>");
									//sb.append("<td width='15%' class='multiControl'>");
									//sb.append(ws.getString(3));
									//sb.append("</td>");
									sb.append("<td width='25%' class='multiControl'>");
									sb.append(ws.getString(4));
									sb.append("</td>");
									sb.append("<td width='25%' class='multiControl'>");
									sb.append(ws.getString(5));
									sb.append("</td></tr>");


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
						sb.append("<tr><td colspan='6' class='multiControl' style='color:red;font-weight : bold' >No Summary Details Available</td>");					 
					}
					sb.append("</table>");
				}			 
		} 
		catch (Exception e) 
		{
			new HisException("Day End Duplicate Report","DayEndDuplicateRptHLP.getSummaryDetails()-->", e.getMessage());
		}

		return sb.toString();
	}


}
