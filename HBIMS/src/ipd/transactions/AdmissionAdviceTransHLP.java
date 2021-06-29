package ipd.transactions;

import hisglobal.exceptions.HisException;

import javax.sql.rowset.WebRowSet;

public class AdmissionAdviceTransHLP {

	public static String getBedStatusView(WebRowSet ws){
		
		StringBuffer sb = new StringBuffer("");
		
		String selectedDate = "";
		
		try{
		
		if(ws != null){
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td width='30%' class='multiLabel'>Date</td>");
			sb.append("<td width='30%' class='multiLabel'>Booked</td>");
			sb.append("<td width='30%' class='multiLabel'>To Be Vacant</td>");
			sb.append("<td width='10%' class='multiLabel'></td>");			
			sb.append("</tr>");
			
			while(ws.next()){
				
				selectedDate = ws.getString(1);
				
				sb.append("<tr>");
				sb.append("<td  class='multiControl'>");
				sb.append(selectedDate);
				sb.append("</td>");
				
				sb.append("<td   class='multiControl'>");
				sb.append("<a name='"+selectedDate+"'href='#' onClick='myFunc(\"2\",this,\"B\");'>");
				sb.append(ws.getString(2));
				sb.append("</a></td>");
				
				sb.append("<td   class='multiControl'>");
				sb.append("<a name='"+selectedDate+"' href='#' onClick='myFunc(\"2\",this,\"V\");'>");
				sb.append(ws.getString(3));
				sb.append("</a></td>");
				
				sb.append("<td width=='20'  class='multiControl'>");
				sb.append("<input type='checkbox' name='bedStatusCheck' ");
				sb.append("value='").append(selectedDate).append("'>");
				sb.append("</td>");
				sb.append("</tr>");
				
				
				
			}
			
			sb.append("</table>");
			
		}
		
		}catch(Exception e){
			
			new HisException("Admission Advice Trans","AdmissionAdviceTransHLP.getBedStatusView()-->",e.getMessage());
		}
		return sb.toString();
	}
	/**
	 * This function is used to search bed 
	 * @param vo
	 * @return
	 */
	public static String getBedDetails(AdmissionAdviceTransVO vo)
	{
		StringBuffer sb = new StringBuffer("");
		WebRowSet ws=vo.getBedDetailWs();
		int size=4;
		int count=1;
		try
		{
			sb.append("<center><table width='20%' border='5' bordercolor='#9DAFC6' cellspacing='1px'>");
			sb.append("<tr>");
			//do
			//{
				int i=0;
				ws.next();
				while(i<ws.size())
				{
				//for(int i=0;i<size;i++)
				//{
						
					while(count<=size)
					{
						String temp[]=ws.getString(1).replace("^", "#").split("#");
						sb.append(getCSS(temp[0],ws.getString(2)));
						//boolean chk=ws.next();
						//if(chk==false)
						if(!(ws.next()))	
						{
							sb.append("</tr>");
							break;
							
						}
						
						
						i++;
						count++;
						
					}
					if(count>size)
					{
					
						sb.append("</tr>");
						count=1;
						sb.append("<tr>");
					}
									
				//}
					
				}
			//}while(true);
				
		
		}
		
		catch(Exception e)
		{
			new HisException("Admission Advice Trans","AdmissionAdviceTransHLP.getBedDetails()-->",e.getMessage());
		}
		sb.append("</table>");
		sb.append("<table>");
		sb.append("<tr><td bgcolor='#FFA980' width='10'></td><td class='multiLabel' width='60'>Blocked</td>");
  		sb.append("<td width='10'></td>");
  		sb.append("<td bgcolor='#CB5C5C' width='10'></td>");
  		sb.append("<td class='multiLabel' width='60'>Occupied</td>");
  		sb.append("<td width='10'></td>");
		sb.append("<td bgcolor='#8CE3E3' width='10'></td>");
  		sb.append("<td class='multiLabel' width='60'>Booked</td><td width='10'></td>");
  		sb.append("<td bgcolor='#88F78D' width='10'></td>\n");
  		sb.append("<td class='multiLabel' width='60'>Vacant</td>");
  		sb.append("<td width='10'></td><td bgcolor='#FEEB9E' width='10'></td>");
  		sb.append("	<td class='multiLabel' width='60'>Vacant But Dirty</td>	</tr>");
  		sb.append("</table></center>");
		//System.out.println("--------->"+sb.toString());
		return sb.toString();
	}
	public static String getCSS(String bedName,String status)
	{
		int strBedStatusCode=0;
		strBedStatusCode=Integer.parseInt(status);
		return  makeCss(bedName,strBedStatusCode);
	}
	//make css for bed detail grid 
	public static String makeCss(String bedName,int bedStatusCode)
	{
		String css="";
		switch(bedStatusCode)
		{
			case 10:
				css="<td bgcolor='#9EFEA6' width='5%'><input style='background-color:#9EFEA6;;width: 70px' " +
				"type='button' name='bedDetails' disabled value='"+
				bedName+"'></td>" ;
				break;
					
			case 11:
				css="<td bgcolor='#FEEB9E' width='5%'><input style='background-color:#FEEB9E;;width: 70px' " +
				"type='button' name='bedDetails' disabled value='"+
				bedName+"'></td>" ;
				break;
						
			case 12:
				css="<td bgcolor='#CB5C5C' width='5%'><input style='background-color:#CB5C5C;;width: 70px' " +
						"type='button' name='bedDetails' disabled value='"+
				bedName+"'></td>" ;
				break;
			case 13:
				css="<td bgcolor='#FFA980' width='5%'><input style='background-color:#FFA980;;width: 70px' " +
				"type='button' name='bedDetails' disabled value='"+
				bedName+"'></td>" ;
				break;
			case 14:
				css="<td bgcolor='#8CE3E3' width='5%'><input style='background-color:#8CE3E3;;width: 70px' " +
				"type='button' name='bedDetails' disabled value='"+
				bedName+"'></td>" ;
				break;
		}
		return css;
	}
	public static String getVacentPatientDtls(WebRowSet ws){
			
		StringBuffer sb = new StringBuffer("");
	
		try{
			
			sb.append(AdmissionAdviceTransHLP.getPopupHeader("Patient(s) To Be Vacant","menu1"));
			
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td class='multiLabel'>CR.No.</td>");
			sb.append("<td class='multiLabel'>Admission No.</td>");
			sb.append("<td class='multiLabel'>Name</td>");
			sb.append("<td class='multiLabel'>Age/Sex</td>");
			sb.append("<td class='multiLabel'>Ward/Bed</td>");
			sb.append("<td class='multiLabel'>Admission Date</td>");
			sb.append("<td class='multiLabel'>Prop Dis Date</td>");
			sb.append("</tr>");
			
			
		if(ws != null && ws.size()!= 0){
				
			while(ws.next()){
				
				sb.append("<tr>");
				sb.append("<td class='multiControl'>");
				sb.append(ws.getString(1));
				sb.append("</td>");
				sb.append("<td class='multiControl'>");
				sb.append(ws.getString(2));
				sb.append("</td>");
				sb.append("<td class='multiControl'>");
				sb.append(ws.getString(3));
				sb.append("</td>");
				sb.append("<td class='multiControl'>");
				sb.append(ws.getString(4));
				sb.append("</td>");
				sb.append("<td class='multiControl'>");
				sb.append(ws.getString(5));
				sb.append("</td>");
				sb.append("<td class='multiControl'>");
				sb.append(ws.getString(6));
				sb.append("</td>");
				sb.append("<td class='multiControl'>");
				sb.append(ws.getString(7));
				sb.append("</td>");
				sb.append("</tr>");				
			}

		}else{
			sb.append("<tr>");
			sb.append("<td colspan='7' class='multiControl'>");
			sb.append("<font color='red'>No Details For the Current Record</font>");
			sb.append("</td>");
			sb.append("</tr>");	
		}
		
		sb.append("</table>");
		
		sb.append(AdmissionAdviceTransHLP.getPopupFooter());
		
		}catch(Exception e){
			
			new HisException("Admission Advice Trans","AdmissionAdviceTransHLP.getVacentPatientDtls()-->",e.getMessage());
		}
		
			return sb.toString();
	}
	
	
public static String getBookedPatientDtls(WebRowSet ws){
	
	
		StringBuffer sb = new StringBuffer("");
		
		try{
			
			sb.append(AdmissionAdviceTransHLP.getPopupHeader("Booked Patient(s)","menu1"));
			
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td class='multiLabel'>Q.No.</td>");
			sb.append("<td class='multiLabel'>CR.No.</td>");
			sb.append("<td class='multiLabel'>Name</td>");
			sb.append("<td class='multiLabel'>Age/Sex</td>");
			sb.append("<td class='multiLabel'>Prop Adm Date</td>");
			sb.append("</tr>");
			
			
			if(ws != null && ws.size()!= 0){
			
			while(ws.next()){
				
				sb.append("<tr>");
				sb.append("<td class='multiControl'>");
				sb.append(ws.getString(1));
				sb.append("</td>");
				sb.append("<td class='multiControl'>");
				sb.append(ws.getString(2));
				sb.append("</td>");
				sb.append("<td class='multiControl'>");
				sb.append(ws.getString(3));
				sb.append("</td>");
				sb.append("<td class='multiControl'>");
				sb.append(ws.getString(4));
				sb.append("</td>");
				sb.append("<td class='multiControl'>");
				sb.append(ws.getString(5));
				sb.append("</td>");
				sb.append("</tr>");				
			}
		
		}else{
			sb.append("<tr>");
			sb.append("<td colspan='7' class='multiControl'>");
			sb.append("<font color='red'>No Details For the Current Record</font>");
			sb.append("</td>");
			sb.append("</tr>");	
		}
		
		sb.append("</table>");
		
		sb.append(AdmissionAdviceTransHLP.getPopupFooter());
		
		}catch(Exception e){
			
			new HisException("Admission Advice Trans","AdmissionAdviceTransHLP.getBookedPatientDtls()-->",e.getMessage());
		}
		
		return sb.toString();
	}
	

	public static String getPopupHeader(String title, String divName){
			
		StringBuffer header = new StringBuffer("");
		
		header.append("<table width='100%' border='0' cellpadding='0' cellspacing='0'>");
		header.append("<tr class='HEADER'>");
		header.append("<th align='left'>&nbsp;");
		header.append(title);
		header.append("</th>");
		header.append("<th align='right'>");
		header.append("<span onclick='hide_popup_menu(\""+divName+"\");'>");
		header.append("[CLOSE]");
		header.append("</span>");
		header.append("</th>");
		header.append("</tr>");
		header.append("<tr>");
		header.append("<td colspan='2'>");
	
		return header.toString();
	}
	
	
	public static String getPopupFooter(){
		
		String footer = new String("</td></tr></table>");
			
		return footer;
	}
	
	
	public static String getProvisionDetails(WebRowSet ws){
		
		StringBuffer sb = new StringBuffer("");
		
		try{
		if(ws != null && ws.size()!= 0){
			
			while(ws.next()){
				
					sb.append("<tr><td colspan='2' class='multiControl'>");
					sb.append(ws.getString(1));
					sb.append("</td></tr>");
			}
		
		}
		}catch(Exception e){
			
			new HisException("Admission Advice Trans","AdmissionAdviceTransHLP.getProvisionDetails()-->",e.getMessage());
		}
		return sb.toString();
	}
public static String getListView(WebRowSet ws){
		
		StringBuffer sb = new StringBuffer("");
		
		String selectedSrNo = "";
		
		try{
		
		if(ws != null)
		{
			sb.append("<table width='100%' align='center' bgcolor='black' cellspacing='1px' border='0'>");
			sb.append("<tr>");
			sb.append("<td width='10%' class='LABEL'><div align='center'>CR No</div></td>");
			sb.append("<td width='10%'class='LABEL'><div align='center'>Name </div></td>");
			sb.append("<td width='10%' class='LABEL'><div align='center'>Age</div></td>");
			sb.append("<td width='10%' class='LABEL'><div align='center'>Treat. Category</div></td>");
			sb.append("<td width='10%' class='LABEL'><div align='center'>Dept/Unit</div></td>");
			sb.append("<td width='10%' class='LABEL'><div align='center'>Ward</div></td>");
			sb.append("<td width='10%' class='LABEL'><div align='center'>Date Of Advice</div></td>");
			sb.append("<td width='10%' class='LABEL'><div align='center'>Proposed Admission Date</div></td>");
			sb.append("<td width='10%' class='LABEL'><div align='center'>Proposed Surgery Date</div></td>");
			sb.append("<td width='10%' class='LABEL'><div align='center'>Admission Status</div></td>");
			sb.append("</tr></table>");			
		  if(ws.size()!=0)
		  {	  
			  sb.append("<table width='100%' align='center' bgcolor='black' cellspacing='1px' border='0'>");
			while(ws.next()){
				
				selectedSrNo = ws.getString(1);
				
				sb.append("<tr>");
				sb.append("<td  class='multiControl' width='10%'>");
				sb.append(selectedSrNo);
				sb.append("</td>");
				
				sb.append("<td   class='multiControl' width='10%'>");
				sb.append(ws.getString(2));
				sb.append("</td>");
				
				sb.append("<td   class='multiControl' width='10%'>");
				sb.append(ws.getString(3));
				sb.append("</td>");
				
				sb.append("<td   class='multiControl' width='10%'>");
				sb.append(ws.getString(4));
				sb.append("</td>");
				
				sb.append("<td   class='multiControl' width='10%'>");
				sb.append(ws.getString(5));
				sb.append("</td>");
				
				sb.append("<td   class='multiControl' width='10%'>");
				sb.append(ws.getString(6));
				sb.append("</td>");
				
				sb.append("<td   class='multiControl' width='10%'>");
				sb.append(ws.getString(7));
				sb.append("</td>");
				
				sb.append("<td   class='multiControl' width='10%'>");
				sb.append(ws.getString(8));
				sb.append("</td>");
				
				sb.append("<td   class='multiControl' width='10%'>");
				sb.append(ws.getString(9));
				sb.append("</td>");
				
				sb.append("<td   class='multiControl' width='10%'>");
				sb.append(ws.getString(10));
				sb.append("</td>");
				/*sb.append("<td   class='multiControl'>");
				sb.append("<a name='"+selectedSrNo+"' href='#' onClick='myFunc(\"1\",this,\"V\");'>");
				sb.append(ws.getString(8));
				sb.append("</a></td>");*/
				sb.append("</tr>");
			}
			sb.append("</table>");
		  }	
		  else
		  {
			  sb.append("<table width='90%' align='center' bgcolor='pink' cellspacing='1px' border='0'>");
			  sb.append("<tr><td width='90%' colspan='7' class='CONTROL' bgcolor='black'><div align='center'><font color='red' weight='bold' size='2'>No Advice Generated Today!!</font></div></td></tr>");
			  sb.append("</table>");	
		  }			
		}
		
		
		}catch(Exception e){
			
			new HisException("Admission Advice Trans","AdmissionAdviceTransHLP.getListView()-->",e.getMessage());
		}
		return sb.toString();
	}

public static String getListView_BS(WebRowSet ws){
	
	StringBuffer sb = new StringBuffer("");
	
	String selectedSrNo = "";
	
	try{
	
	if(ws != null)
	{
		sb.append("<table class='table table-striped'>");
		sb.append("<thead>");
		sb.append("<tr>");
			sb.append("<th>CR No</div></th>");
			sb.append("<th>Name </div></th>");
			sb.append("<th>Age</div></th>");
			sb.append("<th>Treat. Category</div></th>");
			sb.append("<th>Dept/Unit</div></th>");
			sb.append("<th>Ward</div></th>");
			sb.append("<th>Date Of Advice</div></th>");
			sb.append("<th>Proposed Admission Date</div></th>");
			sb.append("<th>Proposed Surgery Date</div></th>");
			sb.append("<th>Admission Status</div></th>");
		sb.append("</tr></thead>");
		
	  if(ws.size()!=0)
	  {	  
		  
		while(ws.next()){
			
			selectedSrNo = ws.getString(1);
			sb.append("<tbody>");
			sb.append("<tr>");
			sb.append("<td  class='multiControl' width='10%'>");
			sb.append(selectedSrNo);
			sb.append("</td>");
			
			sb.append("<td   class='multiControl' width='10%'>");
			sb.append(ws.getString(2));
			sb.append("</td>");
			
			sb.append("<td   class='multiControl' width='10%'>");
			sb.append(ws.getString(3));
			sb.append("</td>");
			
			sb.append("<td   class='multiControl' width='10%'>");
			sb.append(ws.getString(4));
			sb.append("</td>");
			
			sb.append("<td   class='multiControl' width='10%'>");
			sb.append(ws.getString(5));
			sb.append("</td>");
			
			sb.append("<td   class='multiControl' width='10%'>");
			sb.append(ws.getString(6));
			sb.append("</td>");
			
			sb.append("<td   class='multiControl' width='10%'>");
			sb.append(ws.getString(7));
			sb.append("</td>");
			
			sb.append("<td   class='multiControl' width='10%'>");
			sb.append(ws.getString(8));
			sb.append("</td>");
			
			sb.append("<td   class='multiControl' width='10%'>");
			sb.append(ws.getString(9));
			sb.append("</td>");
			
			sb.append("<td   class='multiControl' width='10%'>");
			sb.append(ws.getString(10));
			sb.append("</td>");
			/*sb.append("<td   class='multiControl'>");
			sb.append("<a name='"+selectedSrNo+"' href='#' onClick='myFunc(\"1\",this,\"V\");'>");
			sb.append(ws.getString(8));
			sb.append("</a></td>");*/
			sb.append("</tr>");
			sb.append("</tbody>");
		}
		
	  }	
	  
	  else
	  {
		  
		  sb.append("<div class='alert alert-danger'>No Advice Generated Today!!</div>");
		  
	  }		
	  sb.append("</table>");
	}
	
	
	}catch(Exception e){
		
		new HisException("Admission Advice Trans","AdmissionAdviceTransHLP.getListView()-->",e.getMessage());
	}
	return sb.toString();
}

public static String getDiagnosisDtl(WebRowSet ws,String visitNo){
	
	StringBuffer sb = new StringBuffer("");
	//System.out.println("ws size"+ws.size());
	
	//String selectedSrNo = "";
	
	try{
	
	if(ws != null)
	{
		sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding='1px' bgcolor=''>");
	  if(ws.size()!=0)
	  {	  
		  
		  	sb.append("<tr>");
		  	sb.append("<td class='multiLabel' width='25%'>Diagnosis Code</td>");
			sb.append("<td class='multiLabel' width='25%'>Diagnosis Name</td>");
			sb.append("<td class='multiLabel' width='25%'>Diagnosis Type</td>");
	  		sb.append("</tr>");
		  while(ws.next()){
			
			
			sb.append("<tr>");
			sb.append("<td colspan='1' class='multiControl' width='25%'>"+ws.getString(2).trim()+"</td>");
			sb.append("<td colspan='1' class='multiControl' width='25%'><div align='left'>"+ws.getString(1)+"</div><input type='hidden' name=strDiagnosis value='"+ws.getString(2).trim()+"^"+ws.getString(3).trim()+"' /></td>");
			if(ws.getString(3).equals("0"))
				sb.append("<td colspan='1' class='multiControl' width='25%'>ICD 10 Diagnosis</td>");
			else
				sb.append("<td colspan='1' class='multiControl' width='25%'>Hospital Diagnosis</td>");
			if(ws.getString(8)!=null)
			{
			if(!visitNo.equals(ws.getString(8)))
			{
				sb.append("<input type='hidden' name='strProvisionDiagnosis' value='"+ws.getString(2)+"'>");
				sb.append("<input type='hidden' name='strDiagCodeType' value='"+ws.getString(3)+"'>");
				sb.append("<input type='hidden' name='strDiagTypeCode' value='"+ws.getString(4)+"'>");
				if(ws.getString(7)==null)
				sb.append("<input type='hidden' name='strDiseaseSite' value=''>");
				else
				sb.append("<input type='hidden' name='strDiseaseSite' value='"+ws.getString(7)+"'>");
				if(ws.getString(6).equals("0"))
				sb.append("<input type='hidden' name='strisRepaeat' value='1'>");
				else
				sb.append("<input type='hidden' name='strisRepaeat' value='"+ws.getString(6)+"'>");
				if(ws.getString(5)==null)
				sb.append("<input type='hidden' name='strDiagRemarks' value=''>");
				else
					sb.append("<input type='hidden' name='strDiagRemarks' value='"+ws.getString(5)+"'>");
				sb.append("<input type='hidden' name='strICD10CodeHidden' value='"+ws.getString(2)+"'>");
			}
			}
			sb.append("</tr>");
		}
	  }	
	  else
	  {
		 sb.append("<tr><td colspan='3' class='CONTROL' ><div align='center'><font color='red' weight='bold' size='3'>No Record Found</font></div></td></tr>");
			
	  }
		sb.append("</table>");
		
	}
	
	
	}catch(Exception e){
		
		new HisException("Admission Advice Trans","AdmissionAdviceTransHLP.getListView()-->",e.getMessage());
	}
	return sb.toString();
}

}
