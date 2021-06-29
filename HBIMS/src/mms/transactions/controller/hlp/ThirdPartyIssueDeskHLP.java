package mms.transactions.controller.hlp;

import hisglobal.exceptions.HisException;

import javax.sql.rowset.WebRowSet;

import mms.transactions.vo.ThirdPartyIssueDeskVO;

/**
 * 
 * @author dell
 *
 */
public class ThirdPartyIssueDeskHLP {
	
	
	public static String getItemDetails(ThirdPartyIssueDeskVO voObj)
	 {
		StringBuffer sBuffer = new StringBuffer("");
	//	String strUnit=null;
		String strItemsSancId="";
		 try {
			 WebRowSet wb=voObj.getStrItemDetailsWS();
			 //System.out.println("ItemDetlWS_Size->"+wb.size());
			if(wb.size() != 0)
			{ 
				sBuffer.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");
				while(wb.next())
				{		     
	                    voObj.setStrGroupName(wb.getString(8));
	                    voObj.setStrRemarks(wb.getString(17));
	                    sBuffer.append("<input type='hidden' name='strReqNo'  value='"+voObj.getStrReqNo()+"' />");
					    sBuffer.append("<tr>");
	  					sBuffer.append("<td width='15%' class='multiControl'>"+wb.getString(2)+"</td>");
	  					sBuffer.append("<td width='15%' class='multiControl'>"+wb.getString(3)+"</td>");
	  					sBuffer.append("<td width='10%' class='multiControl'>"+wb.getString(14)+"</td>");
	  					sBuffer.append("<td width='15%' class='multiControl'>"+wb.getString(15)+" "+wb.getString(16)+"</td>");
	  					sBuffer.append("<td width='15%' class='multiControl'>"+wb.getString(4)+" "+wb.getString(12)+"</td>");
	  					sBuffer.append("<td width='15%' class='multiControl'>"+wb.getString(6)+" "+wb.getString(13)+"</td>");
	  					sBuffer.append("<td width='15%' class='multiControl'>"+wb.getString(9)+" "+wb.getString(11)+"</td>");
	  					sBuffer.append("</tr>");	
	  			}
                sBuffer.append("</table>");
               
	     }else {
			    sBuffer.append("<table class='TABLEWIDTH' align='center'  border='0' bgcolor='#000000' cellspacing ='1px'>"); 
			    sBuffer.append("<tr>");
			    sBuffer.append("<td colspan='7'  CLASS='multiControl' >"
						+ "<div class='errMsg' align='center'> NO RECORD FOUND </div>" + "</td>");

			    sBuffer.append("</tr>");
			    sBuffer.append("</table>");
		   } 
			voObj.setStrItemsSancId(strItemsSancId);
		 }
		 catch(Exception e)
		 {
			 new HisException("Third Party Issue Transaction","ThirdPartyIssueSancTransHLP.getItemDetails()-->",e.getMessage());
	     }
	    return sBuffer.toString();
	 	}
	

}



