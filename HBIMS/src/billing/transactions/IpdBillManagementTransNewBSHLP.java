package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;

public class IpdBillManagementTransNewBSHLP {

	
	public static String patAdmissionList(IpdBillManagementTransVO vo)
	 {
	      StringBuffer sBuffer = new StringBuffer();
	      WebRowSet ws=vo.getStrPatAdmWs();
	      
	      try
	      {       
	    	      int count = 0;	
	    	      sBuffer.append("<p class='subHeaders'><i class='fas fa-list' style='font-size: 26px;'></i>&nbsp;Patient Admission List</p>");
	    	      sBuffer.append("<div style='overflow-x:auto;'>");
	    	      sBuffer.append("<table class='table'>"); 
	              
	             
	              if(ws.size()>0)
	              {
	            	  
		    	      sBuffer.append("<thead>" );
		              sBuffer.append("<tr >" );
		              sBuffer.append("<th class='accTileHead'>#</th>");
		              sBuffer.append("<th class='accTileHead'>Admission No.</th>");
		              sBuffer.append("<th class='accTileHead'>Account No.</th>");
		              sBuffer.append("<th class='accTileHead'>Acc Opening Date/Time</th>");
		              sBuffer.append("<th class='accTileHead'>Admission Date/Time</th>");
		              sBuffer.append("<th class='accTileHead'>A/C Status</th>");
		              sBuffer.append("<th class='accTileHead'>Admission Status</th>");
		              sBuffer.append("<th class='accTileHead'>Discharge Date/Time</th>");
		              sBuffer.append("</tr>");	 
		              sBuffer.append("</thead>" );
		              sBuffer.append("<tbody>");
		              
		              while (ws.next()) 
		              {		         
		            	  String dischargedate=ws.getString(5);
		            	  String admDate=ws.getString(4);
		            	  String accOpenDate=ws.getString(3);
		            	  if(ws.getString(3)==null)     		  
		            		  accOpenDate="";	
		            	  if(ws.getString(4)==null)     		  
		            		  admDate="";	
		            	  if(ws.getString(5)==null)     		  
		            		  dischargedate="";		
	 
		            	  	if(ws.getString(9).equals("91") && ws.getString(8).equals("Discharged"))
		            	  		sBuffer.append("<tr style='background-color:#28a745'>");	
		            	  	else if(ws.getString(9).equals("91"))
	            	  			sBuffer.append("<tr style='background-color:#FFD700'>");	

		            	  	else
			            	  sBuffer.append("<tr>");			            	 
			            	  sBuffer.append("<td class='accTileTd'><input type='radio' tabindex='1' name='chkadm' id='chkadm"+count+"' value='"+ws.getString(1)+'^'+ws.getString(2)+'^'+ws.getString(3)+'^'+ws.getString(4)+'^'+ws.getString(5)+'^'+ws.getString(6)+"' onclick='getgoDetails(this);'></td>");
			            	  sBuffer.append("<td class='accTileTd'>"+ws.getString(2)+"</td>");
				              sBuffer.append("<td class='accTileTd'>"+ws.getString(1)+"</td>");
				              sBuffer.append("<td class='accTileTd'>"+accOpenDate+"</td>");
				              sBuffer.append("<td class='accTileTd'>"+admDate+"</td>"); 
				              sBuffer.append("<td class='accTileTd'>"+ws.getString(7)+"</td>");
				              sBuffer.append("<td class='accTileTd'>"+ws.getString(8)+"</td>"); 
			                  sBuffer.append("<td class='accTileTd'>"+dischargedate+"</td>");
			                  sBuffer.append("</tr>");
			 
			              	count = count + 1;
		              }
		              sBuffer.append("</tbody>" );
	              }
	              else
	              {
	            	  sBuffer.append("<thead><th><font color='red'>No Record Found</th></font></thead><tbody>" );
	            	  sBuffer.append("<tr></tr>");
		              sBuffer.append("</tbody>" );
		              vo.setPrintFlag("0");
	              }
	            
	              sBuffer.append("</table>");
	              sBuffer.append("</div>" );

		     }
			 catch(Exception e)
			 {
				 new HisException("Billing","IpdBillManagementTransNewHLP.admissionList()-->",e.getMessage());
		     }
		     return sBuffer.toString();
	 }
}


