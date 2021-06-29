package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;

public class IpdBillManagementTransNewHLP {

	
	public static String patAdmissionList(IpdBillManagementTransVO vo)
	 {
	      StringBuffer sBuffer = new StringBuffer();
	      WebRowSet ws=vo.getStrPatAdmWs();
	      
	      try
	      {       
	    	      int count = 0;	
	    	      sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>"); 
	              sBuffer.append("<tr class='HEADER'><td  width='100%' colspan='8' class='TITLE'><div align='left'>Patient Admission List</div></td></tr>");
	              sBuffer.append("<tr>" );
	              sBuffer.append("<td  width='5%' class='LABEL'><div align='center'>#</div></td>");
	              sBuffer.append("<td  width='11%' class='LABEL'><div align='center'>Admission No.</div></td>");
	              sBuffer.append("<td  width='11%' class='LABEL'><div align='center'>Account No.</div></td>");
	              sBuffer.append("<td  width='18%' class='LABEL'><div align='center'>Acc Opening Date/Time</div></td>");
	              sBuffer.append("<td  width='20%' class='LABEL'><div align='center'>Admission Date/Time</div></td>");
	              sBuffer.append("<td  width='10%' class='LABEL'><div align='center'>A/C Status</div></td>");
	              sBuffer.append("<td  width='10%' class='LABEL'><div align='center'>Admission Status</div></td>");
	              sBuffer.append("<td  width='15%' class='LABEL'><div align='center'>Discharge Date/Time</div></td>");
	              sBuffer.append("</tr></table>");	              
	              sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");	              
	             
	              if(ws.size()>0)
	              {
		              while (ws.next()) 
		              {		         
		            	  String dischargedate=ws.getString(5);
		            	  String admdate=ws.getString(4);
		            	  String admno=ws.getString(2);
		            	  //System.out.println("discharge date"+ws.getString(5));
		            	  if(ws.getString(5)==null)
		            	  {		            		  
		            		  dischargedate="";		            		  
		            	  }
		            	  if(ws.getString(4)==null)
		            	  {		            		  
		            		  admdate="";		            		  
		            	  }
		            	  if(admno.equals("0"))
		            		  admno="-"; 
			              if(dischargedate.equals(""))
			             {
			            	  sBuffer.append("<tr>");			            	 
			            	  sBuffer.append("<td  width='5%'  class='CONTROL'><div align='center'><input type='radio' tabindex='1' name='chkadm' id='chkadm"+count+"' value='"+ws.getString(1)+'^'+ws.getString(2)+'^'+ws.getString(3)+'^'+ws.getString(4)+'^'+ws.getString(5)+'^'+ws.getString(6)+"' onclick='getgoDetails(this);'></div></td>");
			            	  sBuffer.append("<td  width='11%' class='CONTROL'><div align='center'>"+admno+"</div></td>");
				              sBuffer.append("<td  width='11%' class='CONTROL'><div align='center'>"+ws.getString(1)+"</div></td>");
				              sBuffer.append("<td  width='18%' class='CONTROL'><div align='center'>"+ws.getString(3)+"</div></td>");
				              sBuffer.append("<td  width='20%' class='CONTROL'><div align='center'>"+admdate+"</div></td>"); 
				              sBuffer.append("<td  width='10%' class='CONTROL'><div align='center'>"+ws.getString(7)+"</div></td>"); 
				              sBuffer.append("<td  width='10%' class='CONTROL'><div align='center'>"+ws.getString(8)+"</div></td>"); 
				              sBuffer.append("<td  width='15%' class='CONTROL'><div align='center'>"+dischargedate+"</div></td>");
			                  sBuffer.append("</tr>");
			              }
			              else
			              {
			            	  sBuffer.append("<tr bgcolor='#FFD700'>");
			            	  sBuffer.append("<td  width='5%'  class='FONT'><div align='center'><input type='radio' tabindex='1' name='chkadm' id='chkadm"+count+"' value='"+ws.getString(1)+'^'+ws.getString(2)+'^'+ws.getString(3)+'^'+ws.getString(4)+'^'+ws.getString(5)+'^'+ws.getString(6)+"' onclick='getgoDetails(this);'></div></td>");
			            	  sBuffer.append("<td  width='11%' class='FONT'><div align='center'>"+ws.getString(2)+"</div></td>");
				              sBuffer.append("<td  width='11%' class='FONT'><div align='center'>"+ws.getString(1)+"</div></td>");
				              sBuffer.append("<td  width='18%' class='FONT'><div align='center'>"+ws.getString(3)+"</div></td>");
				              sBuffer.append("<td  width='20%' class='FONT'><div align='center'>"+ws.getString(4)+"</div></td>"); 
				              sBuffer.append("<td  width='10%' class='FONT'><div align='center'>"+ws.getString(7)+"</div></td>"); 
				              sBuffer.append("<td  width='10%' class='FONT'><div align='center'>"+ws.getString(8)+"</div></td>");
				              sBuffer.append("<td  width='15%' class='FONT'><div align='center'>"+dischargedate+"</div></td>");
				              sBuffer.append("</tr>");
			              }
			              	count = count + 1;
		              }
	              }
	              else
	              {
	            	  sBuffer.append("<tr>");
		              sBuffer.append("<td  width='25%' class='CONTROL'><div align='left'><font color='red'>No Record Found</font></div></td>");
		              sBuffer.append("</tr>");
		              vo.setPrintFlag("0");
	              }
	              sBuffer.append("</table>");	              
		     }
			 catch(Exception e)
			 {
				 new HisException("Billing","IpdBillManagementTransNewHLP.admissionList()-->",e.getMessage());
		     }
		     return sBuffer.toString();
	 }
}


