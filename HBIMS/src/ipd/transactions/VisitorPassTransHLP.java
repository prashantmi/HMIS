package ipd.transactions;
import ipd.IpdConfigUtil;

import java.util.Calendar;

import javax.sql.rowset.WebRowSet;

public class VisitorPassTransHLP {
	
	public static String getVisitorPassDetail(VisitorPassTransVO vo)
	{
		StringBuffer br = new StringBuffer("");
		VisitorPassTransBO bo=new VisitorPassTransBO();
		bo.setvisitorPassdtl(vo);
		WebRowSet wb = vo.getPassDetailWS();
		
//		HisUtil util = null;
		
		int count = 1;
		
		try
		{
					
        	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MMM-yyyy");
			sdf.parse(vo.getStrCtDate());
			
			if (wb != null && wb.size()>0) {
			//	util = new HisUtil("Visitor Pass Transaction",
		//		"VisitorPassTransDATA");
				br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
				br.append("<tr class='TITLE'>\n");
				br.append("<td  colspan='8'>Pass Details</td>\n");
				br.append("</tr>\n");
				br.append("<tr>\n");
				br.append("<td width='10%' class='multiLabel'></td>");
				br.append("<td WIDTH='15%' class='multiLabel' colspan='1'>Pass No</td>\n");
				br.append("<td WIDTH='15%' class='multiLabel' colspan='1'>Pass Type</td>\n");
				br.append("<td WIDTH='15%' class='multiLabel' colspan='1'>Last Issue/Renew Date</td>\n");
				br.append("<td WIDTH='15%' class='multiLabel' colspan='1'>Valid From</td>\n");
				br.append("<td WIDTH='15%' class='multiLabel' colspan='1'>Valid Upto</td>\n");
				br.append("<td WIDTH='15%' class='multiLabel' colspan='1'>Amount(Rs)</td>\n");
				br.append("</tr>\n");

	        while(wb.next()){

	        	String strTempValidUpto = wb.getString(5);
	        	String strTempValidFrom = "";
	        	
	        	Calendar c = Calendar.getInstance(); 
	        	Calendar validUpto = Calendar.getInstance();
				c.setTime(sdf.parse(vo.getStrCtDate()));
	        	validUpto.setTime(sdf.parse(strTempValidUpto));
				
				
            			br.append("<tr>");
            			br.append("<td width='10%' class='multiControl'>");
        				br.append("<input type='checkbox' name='strchkvisit' value='"+count+"' id='checkid' onClick='return chkBoxClick(this,\""+count+"\");'></td>");
            			br.append("<td width='15%' class='multiControl'>"+ wb.getString(1)+" <input type='hidden' name='strPass_No' value='"+wb.getString(1)+"'> </td>");
            			
            			br.append("<td width='15%' class='multiControl'>");
            			
            			          			
            			
            		    if(wb.getString(2).equals("1")){
            				br.append("Free Pass");
            			
            				
            				if(c.equals(validUpto) && c.after(validUpto)){
            					validUpto.add(Calendar.DATE,1);
            					strTempValidFrom = sdf.format(validUpto.getTime());
            					validUpto.add(Calendar.DATE,Integer.parseInt(vo.getStrRFPassValidity()));
            					strTempValidUpto = sdf.format(validUpto.getTime());
            				}else{
            					c.add(Calendar.DATE,1);
            					strTempValidFrom = sdf.format(c.getTime());
            					c.add(Calendar.DATE,Integer.parseInt(vo.getStrRFPassValidity()));
            					strTempValidUpto = sdf.format(c.getTime());
            				}
            					
            			}else{
            				            				
            				br.append("Paid Pass");
            				
            				if(c.equals(validUpto) && c.after(validUpto)){
            					validUpto.add(Calendar.DATE,1);
            					strTempValidFrom = sdf.format(validUpto.getTime());
            					validUpto.add(Calendar.DATE,Integer.parseInt(vo.getStrRPPassValidity()));
            					strTempValidUpto = sdf.format(validUpto.getTime());
            				}else{
            					c.add(Calendar.DATE,1);
            					strTempValidFrom = sdf.format(c.getTime());
            					c.add(Calendar.DATE,Integer.parseInt(vo.getStrRPPassValidity()));
            					strTempValidUpto = sdf.format(c.getTime());
            					
            				}
            				
                		 
            			}
            		    br.append("<input type='hidden' name='strhPassType' value='"+wb.getString(2)+"'>");       			
            			br.append("</td>");
            			
            			
            			br.append("<td width='15%' class='multiControl'><div id='issueid0"+count+"' style='display:block;'>"+wb.getString(3)+"</div>");
            			br.append("<div id='issueid1"+count+"' style='display:none;'>"+vo.getStrCtDate()+"</div></td>");
            			br.append("<input type='hidden' name='strIssueRenewDt' value='"+vo.getStrCtDate()+"'/>");
            			
            			
            			br.append("<td width='15%' class='multiControl' nowrap><div id='fromid0"+count+"' style='display:block;'>"+wb.getString(4)+"</div>");
            			br.append("<div id='fromid1"+count+"' style='display:none;'>"+strTempValidFrom+"</div> <input type='hidden' name='strhValidFrom' value='"+strTempValidFrom+"'/> </td>");
            			
            				
            			//br.append("<div id='fromid1"+count+"' style='display:none;'>"+vo.getStrCtDate()+"</div></td>");
            			br.append("");
            			br.append("<td width='15%' class='multiControl'>");
            			br.append("<div id='uptoid2"+count+"' style='display:block;'><input type='text' name='strValidUpto0' value='"+wb.getString(5)+"' class='txtFldNormal' readonly></div>");
            			br.append("<div id='uptoid1"+count+"' style='display:none;'>"+strTempValidUpto+"</div> <input type='hidden' name='strValidUptoR' value='"+strTempValidUpto+"'/></td>");
            			
            		
            			//br.append("<input type='hidden' name='strValidUpto' value='"+result+"'/>");
            			//br.append("<input type='text' name='strValidUpto' id='uptoid1"+count+"' value='"+result+"' class='txtFldNormal'></td>");
            			/*br.append("<td width='15%' class='multiControl'><div id='uptoid0"+count+"' style='display:block;'>"+wb.getString(5)+"</div>");
            			br.append("<div id='uptoid1"+count+"' style='display:none;'>"+HisUtil.getDatePicker("strValidUpto", result, true)+"</div></td>");*/
            			if(wb.getString(2).equals("1")){
            				br.append("<td width='15%' class='multiControl'>");
            				br.append("<input type='text' name='strPaidAmount' id='strPaidAmount0"+count+"' value='"+wb.getString(6)+"' class='txtFldNormal' readOnly></td>");
            			}else{
            				br.append("<td width='15%' class='multiControl'>");
            				br.append("<input type='text' name='strPaidAmount' id='strPaidAmount1"+count+"' value='"+wb.getString(6)+"' class='txtFldNormal' maxlength='3' onkeypress='return validateData(event,5)' readOnly></td>");
            			}
            			br.append("</tr>");
            			
            			count = count + 1;
            			
            		}
            br.append("</table>");
		}
			else
			{
			String err = vo.getStrMsgString(); 
			br.append("ERROR####"+err); 
			// throw new Exception(voObj.getStrMsgString());
		} 
	}catch(Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("VisitorPassTransHLP.getVisitorPassDetail() --> "+e.getMessage());
			vo.setStrMsgType("1");
		}
	return br.toString();
	}

	
	public static String getVisitorNewPassDetail(VisitorPassTransVO vo){
		
		StringBuffer br = new StringBuffer("");
		IpdConfigUtil ipdConfig=null;
	    int count = 1;
		try
		{
			
			ipdConfig=new IpdConfigUtil(vo.getStrHospitalCode());
			System.out.println("vo.getStrHospitalCode() in getVisitorNewPassDetail() :"+vo.getStrHospitalCode());
			String result = "";
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MMM-yyyy");
			sdf.parse(vo.getStrCtDate());
			
				br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
				br.append("<tr class='TITLE'>\n");
				br.append("<td  colspan='8'>Pass Details</td>\n");
				br.append("</tr>\n");
				br.append("<tr>\n");
				br.append("<td width='4%' class='multiLabel'></td>");
				br.append("<td WIDTH='24%' class='multiLabel' colspan='1'>Pass Type</td>\n");
				br.append("<td WIDTH='24%' class='multiLabel' colspan='1'>Valid From</td>\n");
				br.append("<td WIDTH='24%' class='multiLabel' colspan='1'>Valid Upto</td>\n");
				br.append("<td WIDTH='24%' class='multiLabel' colspan='1'>Amount(Rs)</td>\n");
				br.append("<input type='hidden' name='strSelectedChkNo'  value=''/>");
	            br.append("</tr>");
	      
	         if (vo.getStrRemainderFreePass()!=0 || vo.getStrRemainderPaidPass()!=0 || vo.getStrAttendancePassCount().equals("0")) 
			 {
	        	for(int i=0;i<vo.getStrRemainderFreePass();i++){
	        	br.append("<tr>");
    			br.append("<td width='4%' class='multiControl'>");
				br.append("<input type='checkbox' name='strchkvisit' value='"+count+","+"1"+"' id='checkid' ></td>");
    			   			
    			br.append("<td width='24%' class='multiControl'>");
    			
    		  
    				br.append("Free Pass");
    				Calendar c = Calendar.getInstance(); 
    	        	c.setTime(sdf.parse(vo.getStrCtDate()));
    				c.add(Calendar.DATE,Integer.parseInt(vo.getStrRFPassValidity()));
        		    result = sdf.format(c.getTime());
        		br.append("</td>"); 
    
    		       
        		br.append("<input type='hidden' name='strhPassType' value='1' id='strhPassType"+count+"'>"); 
        		  			
    			
    			br.append("<td width='24%' class='multiControl'><div id='validFromid0"+count+"' style='display:block;'>"+vo.getStrCtDate()+"</div>");
    			br.append("<div id='validFromid1"+count+"' style='display:none;'>"+vo.getStrCtDate()+"</div></td>");
    			br.append("<input type='hidden' name='strhValidFrom' value='"+vo.getStrCtDate()+"' id='strhValidFrom"+count+"'/>");
    			 
    			br.append("<td width='24%' class='multiControl'><div id='validToid0"+count+"' style='display:block;'>"+result+"</div>");
    			//br.append("<div id='validToid1"+count+"' style='display:none;'>"+result+"</div></td>");
    			br.append("<input type='hidden' name='strValidUptoR' value='"+result+"' id='strValidUptoR"+count+"'/>");
    		    
    			br.append("<td width='24%' class='multiControl'>");
    			
    		    br.append("<input type='hidden' name='strPaidAmount' id='strPaidAmt0"+count+"' value='0' >0</td>");
    			
    			br.append("</tr>");
    			count=count+1;
	         }
	      
	        
             for(int j=0;j<vo.getStrRemainderPaidPass();j++){
	        	
             	 
	        	br.append("<tr>");
    			br.append("<td width='4%' class='multiControl'>");
				br.append("<input type='checkbox' name='strchkvisit' value='"+count+","+"2"+"' id='checkid' onClick='checkBox(this,\""+count+"\");'></td>");
    			   			
    			br.append("<td width='24%' class='multiControl'>");
    			//br.append("<input type='text' name='strPassType' value='"+vo.getStrPassType()+"'>");
    		   
    				br.append("Paid Pass");
    				Calendar c = Calendar.getInstance(); 
    	        	c.setTime(sdf.parse(vo.getStrCtDate()));
    				c.add(Calendar.DATE,Integer.parseInt(vo.getStrRPPassValidity()));
        		    result = sdf.format(c.getTime());
        		br.append("</td>");
    
    		   // br.append("<input type='hidden' name='strhPassType' value='"+vo.getStrhPassType()+"'>");  
        		 br.append("<input type='hidden' name='strhPassType' value='2' id='strhPassType"+count+"'>");
        		
    			
    			
    			br.append("<td width='24%' class='multiControl'><div id='validFromid0"+count+"' style='display:block;'>"+vo.getStrCtDate()+"</div>");
    			br.append("<div id='validFromid1"+count+"' style='display:none;'>"+vo.getStrCtDate()+"</div></td>");
    			br.append("<input type='hidden' name='strhValidFrom' value='"+vo.getStrCtDate()+"' id='strhValidFrom"+count+"'/>");
    			
    			br.append("<td width='24%' class='multiControl'><div id='validToid0"+count+"' style='display:block;'>"+result+"</div>");
    			//br.append("<div id='validToid1"+count+"' style='display:none;'>"+result+"</div></td>");
    			br.append("<input type='hidden' name='strValidUptoR' value='"+result+"' id='strValidUptoR"+count+"'/></td>");
    			
    			br.append("<td width='24%' class='multiControl'>");
    			br.append("<input type='hidden' name='strPaidAmount' id='strPaidAmt0"+count+"' value='"+ipdConfig.getStrPaidPassCharge()+"'>"+ipdConfig.getStrPaidPassCharge()+"</td>");
    			
    			br.append("</tr>");
    			count = count + 1;
	        }
             if(ipdConfig.getStrAttendentPass().equals("1") && !ipdConfig.getStrAttendentPassGenerateAtAdmissionTime().equals("1"))
	         {
	        	 br.append("<tr>");
	    			br.append("<td width='4%' class='multiControl'>");
					br.append("<input type='checkbox' name='strAttendentPassCheckBox' value='1' onClick='attendancePass();'>");
						
	    			br.append("<td width='24%' class='multiControl'>");
	    			//br.append("<input type='text' name='strPassType' value='"+vo.getStrPassType()+"'>");
	    		   
	    				br.append("Attendant Pass");
	    				Calendar c = Calendar.getInstance(); 
	    	        	c.setTime(sdf.parse(vo.getStrCtDate()));
	    				c.add(Calendar.DATE,Integer.parseInt(vo.getStrRPPassValidity()));
	        		    result = sdf.format(c.getTime());
	        		br.append("</td>");
	    
	    		   // br.append("<input type='hidden' name='strhPassType' value='"+vo.getStrhPassType()+"'>");  
	        		 br.append("<input type='hidden' name='strAttendentPassType' value='3' id='strhPassType"+count+"'>");
	        		
	    			
	    			
	    			br.append("<td width='24%' class='multiControl'><div  style='display:block;'>"+vo.getStrCtDate()+"</div>");
	    			br.append("</td>");
	    			br.append("<input type='hidden' name='strAttendentPassValidFrom' value='"+vo.getStrCtDate()+"' id='strhValidFrom"+count+"'/>");
	    			
	    			br.append("<td width='24%' class='multiControl'>");
	    			//br.append("<div id='validToid1"+count+"' style='display:none;'>"+result+"</div></td>");
	    			br.append("<input type='hidden' name='strAttendentPassValidUpto' value=''/></td>");
	    			
	    			br.append("<td width='24%' class='multiControl'>0.0");
	    			br.append("<input type='hidden' name='strAttendentPassPaidAmount'  value='0.0'</td>");
	    			
	    			br.append("</tr>");
	    			 }
	
		}
	     else
	     {
				br.append("<tr>");
				br.append("<td colspan='8'  class='multiControl' ><div class='errMsg' align='center'> NO RECORD FOUND </div></td>");
				br.append("</tr>");
	     }
	        
	          br.append("</table>");
		}catch(Exception e)
		{
			vo.setStrMsgString("VisitorPassTransHLP.getVisitorNewPassDetail() --> "+e.getMessage());
			vo.setStrMsgType("1");
		}
 //  System.out.println("stringHLP->"+br.toString());
	return br.toString();
	}
	
}
