package ipd.transactions;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import javax.sql.rowset.WebRowSet;


public class AdmissionReprintTransHLP{

	public static  String DATE_FORMAT_NOWwt = "dd-MMM-yyyy/HH:mm:ss";
	public static  String DATE_FORMAT_NOW = "dd-MMM-yyyy";
    
	public static  String now(String frmt)
    {
    	HisUtil util=null;
    	String a="";
    	util=new HisUtil("transaction","PatientLeaveHLP");
    	try{
    	 a= util.getASDate(frmt);
    	}
    	catch(Exception e){
    		new HisException("Patient Leave Trans","PatientLeaveTransHLP.now()-->",e.getMessage());
    	}
    	/*Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    return sdf.format(cal.getTime());*/
    	return a;
    }
	 public static String visitPass(AdmissionReprintTransVO vo) 
	 {
		StringBuffer br=null;
		int count=0;
		WebRowSet wb=null;
		String tmp=null;
	    try
	    {
	     br=new StringBuffer();
	     wb=vo.getStrPassDetailWS();
	     br.append("<input type='hidden' name='strPassNo' value=''/>");
	     br.append("<input type='hidden' name='strPassType' value=''/>");
	     br.append("<input type='hidden' name='strIssueDate' value=''/>");
	     br.append("<input type='hidden' name='strValidFrom' value=''/>");
	     br.append("<input type='hidden' name='strValidUpto' value=''/>");
	     br.append("<input type='hidden' name='strPassAmount' value=''/>");
	     if(wb!=null && wb.size()>0)
	     {
	     br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
		 br.append("<tr class='TITLE'>\n");
		 br.append("<td  colspan='8'>Pass Details</td>\n");
		 br.append("</tr>\n");
		 br.append("<tr>\n");
		 br.append("<td width='10%' class='multiLabel'></td>");
		 br.append("<td WIDTH='18%' class='multiLabel' colspan='1'>Pass No</td>\n");
	     br.append("<td WIDTH='18%' class='multiLabel' colspan='1'>Pass Type</td>\n");
		 br.append("<td WIDTH='18%' class='multiLabel' colspan='1'>Issue Date</td>\n");
		 br.append("<td WIDTH='18%' class='multiLabel' colspan='1'>Valid From</td>\n");
		 br.append("<td WIDTH='18%' class='multiLabel' colspan='1'>Valid Upto</td>\n");
		// br.append("<td WIDTH='15%' class='multiLabel' colspan='1'>Amount(Rs)</td>\n");
         br.append("</tr>\n");
         for(count=1;wb.next();count++)
         {
            tmp=wb.getString(1)+"^"+wb.getString(2)+"^"+wb.getString(3)+"^"+wb.getString(4)+"^"+wb.getString(5)+"^"+wb.getString(6);
        	br.append("<tr>");
			br.append("<td width='10%' class='multiControl'>");
			br.append("<input type='checkbox' name='strChkPass' value='"+tmp+"' onClick='return chkBoxClick(this,\""+count+"\");'></td>");
			//br.append("<input type='checkbox' name='strChkPass' value=''  onClick='return chkBoxClick(this,\""+count+"\");'></td>");
			br.append("<td width='18%' class='multiControl'>"+wb.getString(1)+"</td>");
			br.append("<td width='18%' class='multiControl'>");
		    if(wb.getString(2).equals("1")){
				br.append("Free Pass");
			}else{
				br.append("Paid Pass");
			}
			br.append("</td>");
			br.append("<td width='18%' class='multiControl'>"+wb.getString(3)+"</td>");
			br.append("<td width='18%' class='multiControl' nowrap>"+wb.getString(4)+"</td>");
			br.append("<td width='18%' class='multiControl'>"+wb.getString(5)+"</td>");
			//br.append("<td width='15%' class='multiControl'>"+wb.getString(6)+"</td>");
			br.append("</tr>");
         }
		 br.append("</table>");
	     }
	    }
	    catch(Exception e)
	    {
	    	
	    }
		 return br.toString();
	 }
	 public static String admissionList(AdmissionReprintTransVO vo)
	 {
	      StringBuffer sBuffer = new StringBuffer();
	      WebRowSet ws=vo.getAdmissionListWS();
	      
	      try
	      {       
	    	      sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>"); 
	              sBuffer.append("<tr><td  width='100%' colspan='4' class='TITLE'><div align='left'>Patient Admission List</div></td></tr>");
	              sBuffer.append("<tr>" );
	              sBuffer.append("<td  width='5%' class='LABEL'><div align='center'>#</div></td>");
	              sBuffer.append("<td  width='25%' class='LABEL'><div align='center'>Admission No.</div></td>");
	              sBuffer.append("<td  width='25%' class='LABEL'><div align='center'>Admission Date/Time</div></td>");
	              sBuffer.append("<td  width='45%' class='LABEL'><div align='center'>Discharge Date/Time</div></td>");
	              sBuffer.append("</tr></table>");	              
	              sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");	              
	             
	              if(ws.size()>0)
	              {
		              while (ws.next()) 
		              {		            	 
		            	  sBuffer.append("<tr>");
		            	  System.out.println("ws.getString(1)"+ws.getString(1));
		            	  if(vo.getStrAdmnNo().equals(ws.getString(1)))
		            		  sBuffer.append("<td  width='5%' class='CONTROL'><div align='left'><input type='radio' checked name='chk' value='"+ws.getString(1)+"' onclick='getgoDetails(this);'></div></td>");
		            	  else
		            		  sBuffer.append("<td  width='5%' class='CONTROL'><div align='left'><input type='radio' name='chk' value='"+ws.getString(1)+"' onclick='getgoDetails(this);'></div></td>");
		            	  
			              
			              sBuffer.append("<td  width='25%' class='CONTROL'><div align='left'>"+ws.getString(1)+"</div></td>");
			              sBuffer.append("<td  width='25%' class='CONTROL'><div align='left'>"+ws.getString(2)+"</div></td>");
			              sBuffer.append("<td  width='45%' class='CONTROL'><div align='center'>"+ws.getString(3)+"</div></td>");
			              sBuffer.append("</tr>");			             
		              }
	              }
	              else
	              {
	            	  sBuffer.append("<tr>");
		              sBuffer.append("<td  width='25%' class='CONTROL'><div align='left'><font color='red'>No Record Found</font></div></td>");
		              sBuffer.append("</tr>");		              
	              }
	              sBuffer.append("</table>");	              
		     }
			 catch(Exception e)
			 {
				 new HisException("ADT","AdmissionReprintTransHLP.admissionList()-->",e.getMessage());
		     }
		     return sBuffer.toString();
	 }
	 public static String admissionList_BS(AdmissionReprintTransVO vo)
	 {
	      StringBuffer sBuffer = new StringBuffer();
	      WebRowSet ws=vo.getAdmissionListWS();
	      
	      try
	      {       
	    	      sBuffer.append("<p class='subHeaders'><i class='fas fa-list' style='font-size: 26px;'>&nbsp;</i>Admission Patient List</p>");
	    	      sBuffer.append("<table class='table'>"); 
	              sBuffer.append("<thead>" );
	              sBuffer.append("<tr>" );
	              sBuffer.append("<th><div>#</div></th>");
	              sBuffer.append("<th><div>Admission No.</div></th>");
	              sBuffer.append("<th><div>Admission Date/Time</div></th>");
	              sBuffer.append("<th><div>Discharge Date/Time</div></th>");
	              sBuffer.append("</tr>");	
	              sBuffer.append("</thead>" );              
	              sBuffer.append("<tbody>");	              
	             
	              if(ws.size()>0)
	              {
		              while (ws.next()) 
		              {		            	 
		            	  sBuffer.append("<tr>" );
		            	  System.out.println("ws.getString(1)"+ws.getString(1));
		            	  if(vo.getStrAdmnNo().equals(ws.getString(1)))
		            		  sBuffer.append("<td><div><input type='radio' checked name='chk' value='"+ws.getString(1)+"' onclick='getgoDetails(this);'></div></td>");
		            	  else
		            		  sBuffer.append("<td><div><input type='radio' name='chk' value='"+ws.getString(1)+"' onclick='getgoDetails(this);'></div></td>");
		            	  
			              
			              sBuffer.append("<td><div>"+ws.getString(1)+"</div></td>");
			              sBuffer.append("<td><div>"+ws.getString(2)+"</div></td>");
			              sBuffer.append("<td><div>"+ws.getString(3)+"</div></td>");
			              sBuffer.append("</tr>" );        
		              }
	              }
	              
	              else
	              {
	            	  sBuffer.append("<tr>");
		              sBuffer.append("<td><div align='left'><font color='red'>No Record Found</font></div></td>");
		              sBuffer.append("</tr>");		              
	              }
	              sBuffer.append("</tbody>");
	              sBuffer.append("</table>");	              
		     }
			 catch(Exception e)
			 {
				 new HisException("ADT","AdmissionReprintTransHLP.admissionList()-->",e.getMessage());
		     }
		     return sBuffer.toString();
	 }
}