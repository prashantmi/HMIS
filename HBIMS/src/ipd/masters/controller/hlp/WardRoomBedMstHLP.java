package ipd.masters.controller.hlp;

import ipd.masters.vo.WardRoomBedMstVO;

import javax.sql.rowset.WebRowSet;

public class WardRoomBedMstHLP
{
	   
	private static int noOfRow=0;

	/**
	 * is used to view bed details on add page
	 */
	public String getBedDetails(WebRowSet wb) throws Exception
	{
		 StringBuffer br = new StringBuffer();
		  
		
		   try
		   {
		   int i = 0;
		   br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>"); 
		   br.append("<tr>");
		   br.append("<td class='multiLabel' WIDTH='25%' align='LEFT'>Bed Name</td>");
		   br.append("<td class='multiLabel' WIDTH='25%' align='LEFT'>Bed Type</td>");
		   br.append("<td class='multiLabel" +
		   		"" +
		   		"" +
		   		"' WIDTH='25%' align='LEFT'>Bed Status</td>");
		   br.append("<td class='multiLabel' WIDTH='25%' align='LEFT'>Is Sharable</td>");
		   while (wb.next())
		   {
			   br.append("<TR>");
			     br.append("<TD WIDTH='25%' CLASS='multiControl'>"
			      + wb.getString(2) + "</TD>");
			    br.append("<TD WIDTH='25%' CLASS='multiControl'>"
					      + wb.getString(3) + "</TD>");
			    br.append("<TD CLASS='multiControl' nowrap>"
					      + wb.getString(4) + "</TD>");
			    br.append("<TD CLASS='multiControl' nowrap>"
					      + wb.getString(5) + "</TD>");
		    //br.append("<td align='right'><img name='Property' src='../../hisglobal/images/btn-search.png' onClick='' />");
		    br.append("</TR>");
		    br.append("<input type='hidden' name='bedd' value='"+wb.getString(2)+"' >");
		    i++;
		   }
		   br.append("<input type='hidden' name='length' value='"+i+"'>");
		   br.append("</table>");
		  } catch (Exception e) {
		   e.printStackTrace();
		   throw new Exception("ipd.HLPWardRoomBedMst.getBedDetails()"+ e
		     .getMessage());   
		  }
		return br.toString();
	}
	// this function developed by Anurudra   
	// 
	
	/**
	 * is used to create text boxes on add page to enter in new bed in particular ward
	 */
	public String enterBedDetail(String pk1,WardRoomBedMstVO vo)throws Exception
	{
		 StringBuffer br = new StringBuffer(2000);
		 StringBuffer strPagination = new StringBuffer(1000);
		 int intBedLimit = 10;
		try
		{
			
			String roomId=vo.getStrRoomId();
			//String temp[]=pk1.replace('^', '#').split("#");
			String bedtype=vo.getStrBedType();
			String bedstatus=vo.getStrBedStatus();
			int strMaxBed=Integer.parseInt(roomId.replace('^', '#').split("#")[1]);
			int  strAlottedBed=Integer.parseInt(roomId.replace('^', '#').split("#")[2]);
			int k=strMaxBed-strAlottedBed;
			int count=0;
			if(k > 0) 
			{
				while(true) 
				{					
					if(k>0) 
					{
						if(count == 0)
							br.append("<div id ='hlpid"+(++count)+"' style='display:block;'>");
						else
							br.append("<div id ='hlpid"+(++count)+"' style='display:none;'>");
						
						strPagination.append(createHyperLink(count));
						if(count==35)
						{
							strPagination.append("</td></tr>");
							strPagination.append("<tr class='HEADER'><td align='right' colspan='5'>");
						}
						if(count==70)
						{
							strPagination.append("</td></tr>");
							strPagination.append("<tr class='HEADER'><td align='right' colspan='5'>");
						}						
					}
					else 
					{						
						strPagination.append("</td></tr></table>");
						strPagination.append(br);
						strPagination.append("<input type='hidden' name='totPage' value='"+count+"'>");
						break;
					}
					
					if(k >= intBedLimit) 
					{						
						br.append(createBedRows(((count - 1) * intBedLimit) + 1, intBedLimit, bedtype, bedstatus));
						k = k - intBedLimit;
					}
					else 
					{						
						br.append(createBedRows(((count - 1) * intBedLimit) + 1, k, bedtype, bedstatus));
						k = 0;
					}					
					br.append("</div>");
				}				
				strPagination.append("<input type='hidden' name='strNoOfRow' value='"+noOfRow+"'>");
			}			
		}
		catch(Exception e)
		{
			throw new Exception("ipd.HLPWardRoomBedMst"+e.getMessage());
		}
		
		return strPagination.toString();
	}
	/**
	 * This function is used to create number of rows on add page 
	 * @param startBed
	 * @param bedRow
	 * @param bedType
	 * @param bedStatus
	 * @return
	 */
	public String createBedRows(int startBed, int bedRow, String bedType, String bedStatus) 
	{		
		String strTemp = "";	
		int x=startBed;
		strTemp = "<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n";
		strTemp += "<tr><td colspan='6' class='multiLabel' width='100%'><div align='left'>Bed Details (" + startBed + " - " + (bedRow + startBed - 1)  + ")</div></td></tr>";
		strTemp += "<tr><td width='20%' class='multiLabel'><font color='red' size='1'>^</font>PreFix Bed Name+Start No</td>";
		strTemp += "<td width='25%' class='multiLabel'><font color='red' size='2'>*</font>Bed Name</td>";
		//strTemp += "<td width='10%' class='multiLabel'><font color='red' size='2'>*</font>Bed No.</td>";
		strTemp += "<td width='20%' class='multiLabel'><font color='red' size='2'>*</font>Bed Type</td>";
		strTemp += "<td class='multiLabel' width='15%'><font color='red' size='2'>*</font>Bed Status</td>";
		strTemp += "<td class='multiLabel' width='10%'>Sharable</td>";
		strTemp += "<td class='multiLabel' width='10%'>Property</td></tr>";		
		
		
		System.out.println("startbed"+x+"starrtbed two"+startBed);
		for(int i = 0;i<bedRow;i++) 
		{
			noOfRow++;
			strTemp += "<tr><TD WIDTH='10%' CLASS='multiControl'>";	
			if(i==0 && startBed==1)
			{
				strTemp += "<input type = 'text' onkeyup='lTrim(this);changeValues();' class='txtFldNormal' onblur='Trim(this);' name='preFixBedName' onkeypress='return validateData(event,9);' value='' maxlength='10'>";
				strTemp += "<input type = 'text' onkeyup='lTrim(this);changeValues();' class='txtFldSmall' onblur='Trim(this);' name='startBedNo' onkeypress='return validateData(event,5);' value='1' maxlength='2'>";
			}
			
			
			if(x==1)
			{
				strTemp += "</td>";
				strTemp += "<TD WIDTH='25%' CLASS='multiControl'>";	
				strTemp += "<input type = 'text' onkeyup='lTrim(this);' class='txtFldNormal' onblur='Trim(this);' name='bedName' onkeypress='return validateData(event,9);' value='' maxlength='10'>";
				strTemp += "</td>";
				/*strTemp += "<TD WIDTH='10%' CLASS='multiControl'>";	
				strTemp += "<input type = 'text' onkeyup='lTrim(this);' class='txtFldMin' onblur='Trim(this);' name='bedNo' onkeypress='return validateData(event,9);' value='"+(startBed++)+"' maxlength='2'>";
				strTemp += "<input type = 'hidden' name='bedName'>";
				strTemp += "</td>";*/
			}
			else
			{
				strTemp += "&nbsp;</td>";
				strTemp += "<TD WIDTH='25%' CLASS='multiControl'>";
				strTemp += "<input type = 'text' onkeyup='lTrim(this);' class='txtFldNormal' onblur='Trim(this);' name='bedName' onkeypress='return validateData(event,9);' value='' maxlength='10'>";
				strTemp += "</td>";
				/*strTemp += "<TD WIDTH='10%' CLASS='multiControl'>";	
				strTemp += "<input type = 'text' onkeyup='lTrim(this);' disabled class='txtFldMin' onblur='Trim(this);' name='bedNo' onkeypress='return validateData(event,9);' value='"+(startBed++)+"' maxlength='2'>";
				strTemp += "<input type = 'hidden' name='bedName' disabled>";
				strTemp += "</td>";*/
			}
			
			strTemp += "<TD WIDTH='20%' CLASS='multiControl'>";
			strTemp+="<div id='bedTypeId"+(i+1)+"' >"; 
			strTemp += "<select type name='bedType' class='comboNormal'>" + bedType + "</select>";
			strTemp+="</div>";
			strTemp += "</td>";
			
			strTemp += "<TD CLASS='multiControl' WIDTH='20%'>";
			strTemp+="<div id='bedStatusId"+(i+1)+"' >";
			strTemp += "<select type name='bedStatus' class='comboNormal'>" + bedStatus + "</select>";
			strTemp+="</div>";
			strTemp += "</td>";
			
			strTemp += "<TD CLASS='multiControl' WIDTH='10%'>";
			strTemp+="<div id='isSharableId"+(i+1)+"' >";
			strTemp += "<select type name='strIsSharable' class='comboMin'><option value='0'>No</option><option value='1'>Yes</option></select>";
			strTemp+="</div>";
			strTemp += "</td>";
			
			strTemp += "<td class='multiControl' align='right' WIDTH='10%'><img id='search"+i+"' name='Property' src='../../hisglobal/images/property.png' onClick='ajaxProperty(this,"+i+");' style='cursor:pointer' />";
			strTemp += "<div id='divProperty"+i+"' class = 'popup' align = 'center' style='display:  none;;'></div>";
			strTemp += "</td>";
			strTemp += "</tr>";
			strTemp +="<input type='hidden' name='strCheckedHidden' id='strCheckedHidden"+i+"' value='' />";
		}
		
		strTemp += "</table>";
		return strTemp;
	}
	/**
	 *  this function is used to create concept of pagination like 1,2,3 on target jsp 
	 * @param count
	 * @return
	 */
	// 
	// this function is developed by Anurudra Goel
	public String createHyperLink(int count) 
	 {
		String strTemp = "";
		
		if(count == 1) {
			strTemp = "<table class='TABLEWIDTH' align='center' cellspacing='1px'><tr class='HEADER'><td align='right' colspan='5'>";
			strTemp += "<a id='aa"+count+"' href='#' style='text-decoration:none;font: caption;color: red;' name='link"+count+"' onClick=displayList(this,'" + count + "')><b><u>"+count+"</u></b></a>";
		}
		//else if(count%35>=0)
			//strTemp += "</td></tr><tr class='HEADER'><td align='right' colspan='5'><a id='aa"+count+"' href='#' style='text-decoration:none;font: caption;color: white;' name='link"+count+"' onClick=displayList(this,'" + count + "')><b>&nbsp;<u>"+count+"</u></b></a>";
		else 
			strTemp += "<a id='aa"+count+"' href='#' style='text-decoration:none;font: caption;color: white;' name='link"+count+"' onClick=displayList(this,'" + count + "')><b>&nbsp;<u>"+count+"</u></b></a>";	 
		//strPagination.append("</td></tr></table>");
		
		return strTemp;
	 }
	
	public String showSearchProperty(WebRowSet ws,String searchIndex) 
	throws Exception {
		   StringBuffer br = new StringBuffer();	   
		   try
		   {
			   br.append("<table border='0' width='400'  cellspacing='1px' cellpadding='1px'>");
			   br.append("<tr class='HEADER'>");
			   br.append("<td width='95%'>Bed Properties</td>");
			   br.append("<td width='5%'><img src='../../hisglobal/images/FrStopAutoHide.png' onClick ='closeProperties("+searchIndex+");' align='right'/>");
			   br.append("</td>");
			   br.append("</tr>");
			   br.append("</table>");
			   br.append("<table border='0' width='400'  cellspacing='1px' cellpadding='1px'>");
			   br.append("<tr>");
			   br.append("<td width='5%' class='multiLabel' nowrap='nowrap'></td>");
			   br.append("<td width='95%' class='multiLabel'>Property Name</td>");
			   br.append("</tr>");
			  int i = 0;
			   while(ws.next()){
				   br.append("<tr>");
				   br.append("<td width='5%' class='multiControl' nowrap='nowrap'>");
				   br.append("<input type='checkbox' name='strPropertyId"+searchIndex+"' value='"+ws.getString(1)+"' id='checkid"+searchIndex+"'></td>");
				   br.append("<td width='95%' class='multiControl'>"+ ws.getString(2)+"</td>");
				   br.append("</tr>");
				   i++;
			   }
			   br.append("<tr>");
			   br.append("<td colspan='2' align='center'>");
//			   br.append("<input type='hidden' name='strCheckedHidden"+searchIndex+"' id='strCheckedHidden"+searchIndex+"' >");
			   br.append("<img src='../../hisglobal/images/btn-ok.png'  style='cursor:hand;cursor:pointer;'  title ='save data' onClick ='okProperty("+searchIndex+");' />&nbsp;");
			   br.append("<img src='../../hisglobal/images/btn-ccl.png' style='cursor:hand;cursor:pointer;'  title ='clear data' onClick ='cancelPopUP("+searchIndex+");' />");
			   br.append("</td></tr>");
			   br.append("</table>");
		  } catch (Exception e) {
			  throw new Exception("ipd.HLPWardRoomBedMst"+e.getMessage());
		  }
		  return br.toString();
		 }
	
	public String showModifyProperty(WardRoomBedMstVO vo) 
	throws Exception {
		   StringBuffer br = new StringBuffer();	
		   WebRowSet ws = null;
		   WebRowSet wb = null;
		   String strSelectedIdArray[]=null;
		   String strSelectedNameArray[]=null;
		   int i=0;
		  // int j=0;
		   try
		   {
			  ws = vo.getPropertyDetails();
			   wb=vo.getStrPropertyIdWS();
			   int selectedPropSize = wb.size();
			   strSelectedIdArray=new String [selectedPropSize];
			   strSelectedNameArray = new String [selectedPropSize];
			   while(wb.next())
			   {
				   strSelectedIdArray[i]=wb.getString(1);
				   strSelectedNameArray[i]=wb.getString(2);
				     i++;
			   }
			   br.append("<table border='0' width='400'  cellspacing='1px' cellpadding='1px'>");
			   br.append("<tr class='HEADER'>");
			   br.append("<td width='95%'>Bed Properties</td>");
			   br.append("<td width='5%'><img src='../../hisglobal/images/FrStopAutoHide.png' onClick ='closeProperties();' align='right'/>");
			   br.append("</td>");
			   br.append("</tr>");
			   br.append("</table>");
			   br.append("<table border='0' width='400'  cellspacing='1px' cellpadding='1px'>");
			   br.append("<tr>");
			   br.append("<td width='5%' class='multiLabel' nowrap='nowrap'></td>");
			   br.append("<td width='95%' class='multiLabel'>Property Name</td>");
			   br.append("</tr>");
			  
			 /*  while(ws.next()){
				   String strPropId = ws.getString(1);
				   String strPropName = ws.getString(2);
				   
				   
				  // for( i=0;i<strArray.length;i++){
					   if(strArray[j].equals(strPropId)){
						   br.append("<tr>"); 
						   br.append("<td width='5%' class='multiControl' nowrap='nowrap'>");
						   br.append("<input type='checkbox' name='strPropertyId' value='"+strArray[j]+"' id='checkid' checked='true'></td>");
						   br.append("<td width='95%' class='multiControl'>"+ strPropName+"</td>");
						   br.append("</tr>");
					   }
					   else
					   {
						   br.append("<tr>");
						   br.append("<td width='5%' class='multiControl' nowrap='nowrap'>");
						   br.append("<input type='checkbox' name='strPropertyId' value='"+strPropId+"' id='checkid'></td>");
						   br.append("<td width='95%' class='multiControl'>"+ strPropName+"</td>");
						   br.append("</tr>");
					   }
				 //  }
					   j++;
				   
			   }*/
			   
			   for(int k=0; k<strSelectedNameArray.length;k++)
			   {
				   br.append("<tr>"); 
				   br.append("<td width='5%' class='multiControl' nowrap='nowrap'>");
				   br.append("<input type='checkbox' name='strPropertyId' value='"+strSelectedIdArray[k]+"' id='checkid' checked='true'></td>");
				   br.append("<td width='95%' class='multiControl'>"+ strSelectedNameArray[k]+"</td>");
				   br.append("</tr>");
			   }
			   while(ws.next())
			   {
				   String strPropId = ws.getString(1);
				   String strPropName = ws.getString(2);
				   int flag = 0;
				   for(int k=0; k<strSelectedIdArray.length;k++)
				   {
					   if(strPropId.equals(strSelectedIdArray[k]))
							   {
						   			flag = 1;
						   			break;
							   }
				   }
				   if(flag==0)
				   {
					   br.append("<tr>");
					   br.append("<td width='5%' class='multiControl' nowrap='nowrap'>");
					   br.append("<input type='checkbox' name='strPropertyId' value='"+strPropId+"' id='checkid'></td>");
					   br.append("<td width='95%' class='multiControl'>"+ strPropName+"</td>");
					   br.append("</tr>");
				   }
			   }
			   
			   br.append("<tr>");
			   br.append("<td colspan='2' align='center'>");
			   br.append("<img src='../../hisglobal/images/btn-ok.png'  style='cursor:hand;cursor:pointer;'  title ='save data' onClick ='okProperty();' />&nbsp;");
			   br.append("<img src='../../hisglobal/images/btn-ccl.png' style='cursor:hand;cursor:pointer;'  title ='clear data' onClick ='cancelPopUP();' />");
			   br.append("</td></tr>");
			   br.append("</table>");
		  } catch (Exception e) {
			  throw new Exception("ipd.HLPWardRoomBedMst"+e.getMessage());
		  }
		//System.out.println("br:IN HLP=> "+br.toString());
		  return br.toString();
		 }
}