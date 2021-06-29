package ipd.transactions;

import hisglobal.utility.HisUtil;
import javax.sql.rowset.WebRowSet;

public class PatBelongingTransHLP {
	
	public static String getPatBelongingDetail(PatBelongingTransVO vo)
	{
		StringBuffer br = new StringBuffer("");
		PatBelongingTransBO bo = new PatBelongingTransBO(); 
		bo.setPatientblngDtl(vo);
		WebRowSet wb = vo.getStrPatBelonging();
		int count = 1;
		try
		{
			HisUtil util = new HisUtil("IPD", "PatBelongingTransHLP");
			String relationValues = util.getOptionValue(vo.getRelationWS(),"0", "", false);
			br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
			br.append("<tr class='TITLE'>\n");
			br.append("<td  colspan='8'>Patient Belonging Details</td>\n");
			br.append("</tr>\n");
			br.append("<tr>\n");
			br.append("<td width='5%' class='multiLabel'></td>");
			br.append("<td WIDTH='18%' class='multiLabel' colspan='1'>Item Name</td>\n");
			br.append("<td WIDTH='10%' class='multiLabel' colspan='1'>Item Qty</td>\n");
			br.append("<td WIDTH='18%' class='multiLabel' colspan='1'>Item Description</td>\n");
			br.append("<td WIDTH='18%' class='multiLabel' colspan='1'>Item Received Date</td>\n");
			br.append("<td WIDTH='18%' class='multiLabel' colspan='1'>Item Return Date</td>\n");
			br.append("<td WIDTH='18%' class='multiLabel' colspan='1'>Item ReturnTo</td>\n");
			br.append("<td WIDTH='5%' class='multiLabel' colspan='1'>Relation</td>\n");
            br.append("</tr>\n");	
			if (wb != null && wb.size()>0) 
			{
				while(wb.next())
				{
					String strTemp = wb.getString(5);
        			if(strTemp == null || strTemp.equals("null")) strTemp="";
					br.append("<tr>");
					br.append("<td width='5%' class='multiControl'>");
    				br.append("<input type='checkbox' name='strchkvisit' value='"+count+"' id='checkid' onClick='chkBoxClick(this,\""+count+"\");'></td>");
        			br.append("<td width='18%' class='multiControl'>"+ wb.getString(1)+"</td>");
        			br.append("<input type='hidden' name='strItemNameU' value='"+wb.getString(1)+"'>");
        			br.append("<td width='10%' class='multiControl'>"+wb.getString(2)+"</td>");
        			br.append("<input type='hidden' name='strItemQuantityU' value='"+wb.getString(2)+"'>");
        			br.append("<td width='18%' class='multiControl'>"+wb.getString(3)+"</td>");
        			br.append("<input type='hidden' name='strRemarksU' value='"+wb.getString(3)+"'>");
        			br.append("<td width='18%' class='multiControl'>"+wb.getString(6)+"</td>");
        			br.append("<input type='hidden' name='strItemReceivedDate'  value='"+wb.getString(6)+"'>");
        			br.append("<input type='hidden' name='strslno' value='"+wb.getString(4)+"'>");
        			//br.append("<td width='15%' class='multiControl'><div id='itemid0"+count+"' style='display:block;'><input type='text' name='strItemReturnDate' value='' class='txtFldNormal'></div>");
        			br.append("<td width='15%' class='multiControl'>");
        			//br.append("<div id='itemid0"+count+"' style='display:block;'><input type='text' name='strItemReturnDate' value='' class='txtFldNormal'></div>");
        			br.append("<div id='itemid1"+count+"' style='display:block;'>"+HisUtil.getDatePicker("strItemReturnDateU", vo.getStrCtDate(), true)+"</div></td>");
        			br.append("<input type='hidden' name='strItemReturnDate' value='"+vo.getStrCtDate()+"'/>");
        			br.append("<td width='18%' class='multiControl'>");
        			br.append("<input type='text' name='strItemReturnToU' id='strItemReturnTo0"+count+"' value='"+strTemp+"' class='txtFldNormal' onkeypress='return validateData(event,9);' maxlength='50' ></td>");
        			br.append("<input type='hidden' name='strItemReturnTo' value='"+strTemp+"'>");
        			br.append("<td width='5%' class='multiControl'>" );
        			br.append("<select name='strRelation' onChange='relationSet(this,"+count+")'>");
        			br.append(relationValues);
        			br.append("</select>");
        			br.append("</td>");
        			br.append("</tr>");
        			count = count + 1;
        		}
				br.append("<input type='hidden' name='strItemCount' value='"+count+"'/>");
				br.append("</table>");
			}
			else
			{
				br.append("<table class='TABLEWIDTH' align='center'  border='0'>"); 
				br.append("<tr>");
				br.append("<td colspan='8'  class='multiControl'>"
						+ "<DIV class='errMsg' align='center'>No Record Found for Returned the Item...</div>" + "</TD>");
				br.append("<input type='hidden' name='strNoSave' value='0'>");
				br.append("</tr>");
				br.append("</table></div>");
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatBelongingTransHLP.getPatBelongingDetail()"+e.getMessage());
			vo.setStrMsgType("1");
		}
		return br.toString();
	}
	
	public static String getPatBelongingDetailRow(PatBelongingTransVO vo)
	{
		StringBuffer br = new StringBuffer("");
		PatBelongingTransBO bo = new PatBelongingTransBO(); 
		bo.setPatientblngDtlRow(vo);
		WebRowSet wb = vo.getStrPatBelonging();
		try
		{
			br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
			while(wb.next())
			{
					String strTemp = wb.getString(5);
        			if(strTemp == null || strTemp.equals("null")) strTemp="";					
					br.append("<tr>");
					br.append("<td width='35%' class='multiControl'>"+ wb.getString(1)+"</td>");
        			br.append("<td width='30%' class='multiControl'>"+wb.getString(2)+"</td>");
        			br.append("<td width='30%' class='multiControl'>"+wb.getString(3)+"</td>");
        			br.append("<td width='5%'  class='multiControl'></td>");
        			br.append("</tr>");
        	}
			br.append("</table>");
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatBelongingTransHLP.getPatBelongingDetail()"+e.getMessage());
			vo.setStrMsgType("1");
		}		
		return br.toString();
	}

	public static String getPatIssuedItemDetailRow(PatBelongingTransVO vo)
	{
		StringBuffer br = new StringBuffer("");
		PatBelongingTransBO bo = new PatBelongingTransBO(); 
		bo.setPatientIssuedItemDtlRow(vo);
		WebRowSet wb = vo.getPatIssuedItemWS();
		try
		{
			br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
	        while(wb.next())
	        {
					String strTemp = wb.getString(5);				
	    			if(strTemp == null || strTemp.equals("null")) strTemp="";				
					br.append("<tr>");
					br.append("<td width='35%' class='multiControl'>"+ wb.getString(1)+"</td>");
	    			br.append("<td width='30%' class='multiControl'>"+wb.getString(2)+"</td>");
	    			br.append("<td width='30%' class='multiControl'>"+wb.getString(3)+"</td>");
	    			br.append("<td width='5%'  class='multiControl'></td>");
	    			br.append("</tr>");    		}
				 br.append("</table>");
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatBelongingTransHLP.getPatBelongingDetail()"+e.getMessage());
			vo.setStrMsgType("1");
		}	
	return br.toString();
	}
}