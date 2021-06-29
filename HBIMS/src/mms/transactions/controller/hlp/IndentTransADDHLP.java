package mms.transactions.controller.hlp;

import javax.sql.rowset.WebRowSet;

import mms.transactions.vo.IndentTransADDVO;


/**
 * @author Amit Kumar
 * Date of Creation : 31/3/2009
 * Date of Modification :  /  / 
 * Version : 1.0
 * Module  : Store
 */
public class IndentTransADDHLP {
	
	public static String getItemDetails1(IndentTransADDVO vo)
	{
	    StringBuffer sb = new StringBuffer("");
	    String strHiddenValue =""; 
		WebRowSet ws1 = vo.getStrItemDetailsWs();
		String[] tmpqty = null;
		int i=0,k=0;
		try 
		{

				sb.append("<table class='TABLEWIDTH' align='center' bgcolor='#6097BC'  border='0' cellspacing ='1px'>"); 
			   sb.append("<tr><input type='hidden' name='strBSReqNo' value='"+vo.getStrIndentNo()+"'>");
			   sb.append("<td width='20%' class='multiLabel'>Item Name</td>");
			   sb.append("<td width='20%' class='multiLabel'>Avalaible Qty</td>"); 
			   sb.append("<td width='20%' class='multiLabel'>Req Qty</td>");
			   sb.append("<td width='20%' class='multiLabel'>Approved Qty</td>");
			   sb.append("<td width='20%' class='multiLabel'>Rate/Unit</td></tr>");
			  
			      if (ws1 != null && ws1.size()>0) 
				  {
					   String strItemName   = null;
				       String strAvlQty = null;
				       String strReqQty = null;
				       String strSancQty = null;
				       String strRate = null;
				       String strIssueQty = null;
				       String strRetQty = null;
				       String strLstRecevDate = null;
				       String strLstRecevQty = null;
				       String strLstRetQtyUnitId = null;
				       String itemParamValue = null;
				       String strUnitName = null;
				       while(ws1.next())
				       {
				    	    if(vo.getStrReqType().equals("12") || vo.getStrReqType().equals("13") ||vo.getStrReqType().equals("86")||vo.getStrReqType().equals("11") )
				    	    {
				    	    	strIssueQty	  = ws1.getString(1);	
				    	    	strRetQty     = ws1.getString(2);
				    	    	strItemName   = ws1.getString(3);
						        strAvlQty     = ws1.getString(4);
						        strReqQty     = ws1.getString(5);
						        strSancQty    = ws1.getString(6);
						        strRate       = ws1.getString(7);
						        strHiddenValue = strIssueQty+"^"+strRetQty+"^"+strItemName;
						        itemParamValue = ws1.getString(24);
						        strUnitName = ws1.getString(15);
						        
						        
				    	    }	
				    	   
				    	    
				    	    
				    	    if(strItemName == null || strItemName.equals(""))  strItemName = "-----";
							if(strAvlQty == null || strAvlQty.equals("")) strAvlQty = "-----";
							if(strReqQty == null || strReqQty.equals("")) strReqQty = "-----";
							if(strSancQty == null || strSancQty.equals("")) strSancQty = "-----";
							if(strRate == null || strRate.equals("")) strRate = "-----";
							sb.append("<input type='hidden' name ='strHiddenValue'  value='"+strHiddenValue+"'>");
							sb.append("<tr>");
							sb.append("<td width='20%' class='multiControl'><input type = 'hidden' name='itemParamValue' id='itemParamValue-"+i+"' value='"+itemParamValue+"'>");
		     		   		//sb.append("<a name='tarriff' STYLE='cursor:pointer;cursor:pointer;color:blue;' title='Get Item Details' value=''  onClick='callingPoPUp(this,"+i+",\""+strHiddenValue+"\",\""+vo.getStrReqTypeId()+"\");'>"+strItemName+"</a>");
							sb.append(strItemName);
		     		   		sb.append("</td>");
							sb.append("<td width='20%' class='multiControl'>");
							sb.append(strAvlQty);
							sb.append("</td>");
							sb.append("<td width='20%' class='multiControl'><input type='hidden' name='strReqQty' id='strReqQty"+i+"' value='"+strReqQty+"'> ");
							sb.append(strReqQty);
							sb.append("</td>");
				
							sb.append("<td width='20%' class='multiControl'><input type='hidden' name='strUnitName' id='strUnitName"+i+"' value='"+strUnitName+"'>");
							sb.append(strSancQty);
							sb.append("</td>");
							sb.append("<td  width='20%' class='multiControl'>");
							sb.append(strRate);
							sb.append("</td>");
							sb.append("</tr>");
							i++;
						}
					 sb.append("</table>");
					 vo.setStrReqQty(tmpqty);
			  	     
		 	  }
			      else 
			      {
				    sb.append("<table class='TABLEWIDTH' align='center'  border='0'  cellspacing ='1px'>"); 
				    sb.append("<tr>");
				    sb.append("<td colspan='5'  CLASS='multiControl' >"
							+ "<DIV class='errMsg' align='center'> NO RECORD FOUND FOR SELECTED INDENT NO. </div>" + "</TD>");

				    sb.append("</tr>");
				    sb.append("</table>");
					
			   } 
		}
		catch(Exception e)
		{
		    vo.setStrMsgString("IndentTransADDHLP.getItemDetails() --> "+e.getMessage());
			vo.setStrMsgType("1");
		}
		
	return sb.toString();
	}
	
	
}
