package mms.transactions.controller.hlp;

import javax.sql.rowset.WebRowSet;

import mms.transactions.vo.IndentViewTransVO;

public class IndentViewTransHLP 
{
	
	   public static String getItemDetails1(IndentViewTransVO vo)
		{
		    StringBuffer sb = new StringBuffer("");
		    String strHiddenValue =""; 
			WebRowSet ws1 = vo.getStrItemDetailsWs();
			int i=0,k=0;
			try 
			{
				if (ws1 != null && ws1.size()>0) 
				{
					   String strCrNo   = null;
				       String strPatName = null;
				       String strEmpID = null;
				       String strEmpName = null;
				       if(k==0)
				       {
				       while(ws1.next())
				       { 
				    	   if(vo.getStrReqTypeId().equals("14"))
				    	   {	   
				    		   strCrNo    = ws1.getString(10);
					    	   strPatName = ws1.getString(11);
					    	   strEmpID   = ws1.getString(12);
					    	   strEmpName = ws1.getString(13);
				    	   }
				    	   if(vo.getStrReqTypeId().equals("12")||vo.getStrReqTypeId().equals("13"))
				    	   {
				    		   strCrNo    = ws1.getString(8);
					    	   strPatName = ws1.getString(9);
					    	   strEmpID   = ws1.getString(10);
					    	   strEmpName = ws1.getString(11);
				    		   
				    	   } 
				    	   if(vo.getStrReqTypeId().equals("13"))
				    	   {
				    		   strCrNo    = ws1.getString(8);
					    	   strPatName = ws1.getString(9);
					    	   strEmpID   = ws1.getString(10);
					    	   strEmpName = ws1.getString(11);
					    	   		    		   
				    	   } 
				    	   if(vo.getStrReqTypeId().equals("10"))
				    	   {	   
				    		   strCrNo    = ws1.getString(10);
					    	   strPatName = ws1.getString(11);
					    	   strEmpID   = ws1.getString(12);
					    	   strEmpName = ws1.getString(13);
				    	   }
				    	   
				    	    if(strCrNo == null || strCrNo.equals("")|| strCrNo.equals("0"))  strCrNo = "-------";
							if(strPatName == null || strPatName.equals("")) strPatName = "-------";
							if(strEmpID == null || strEmpID.equals("")|| strEmpID.equals("0")) strEmpID = "-------";
							if(strEmpName == null || strEmpName.equals("")) strEmpName = "-------";
							//System.out.println("Before Condition--->>"+vo.getStrReqTypeId());
						  if(!vo.getStrReqTypeId().equals("10"))
					      {
							if(!vo.getStrReqTypeId().equals("14"))
							{	
							 if(k==0)
						     {	  
				             sb.append("<table class='TABLEWIDTH' align='center'   border='0'  cellspacing ='1px'>");
				             sb.append("<tr><td width='25%' class='LABEL'>CR NO</td>");
				             sb.append("<td width='25%' class='CONTROL'>");
				             sb.append(strCrNo);
				             sb.append("</td>");
				             sb.append("<td width='25%' class='LABEL'>Patient Name</td>");
				             sb.append("<td width='25%' class='CONTROL'>");
				             sb.append(strPatName);
				             sb.append("</td></tr>");
				             ////System.out.println("Insde View HLP:-->>"+vo.getStrReqTypeId());
				             if(vo.getStrReqTypeId().equals("12"))
				             {	   
				              sb.append("<tr><td width='25%' class='LABEL'>Emp ID</td>");
				              sb.append("<td width='25%' class='CONTROL'>");
				              sb.append(strEmpID);
				              sb.append("</td>");
				              sb.append("<td width='25%' class='LABEL'>Emp Name</td>");
				              sb.append("<td width='25%' class='CONTROL'>");
				              sb.append(strEmpName);
				              sb.append("</td></tr>");
				            }
					       }
				          }
						  k++;
					     } 
					  }
				   sb.append("</table>");
				 }
				}
				
				   ws1.beforeFirst();
				
			       sb.append("<table class='TABLEWIDTH' align='center' bgcolor='#6097BC'  border='0' cellspacing ='1px'>"); 
				   sb.append("<tr>");
				   sb.append("<td width='25%' class='multiLabel'>Item Name</td>");
				  // sb.append("<td width='20%' class='multiLabel'>Avalaible Qty</td>"); 
				   sb.append("<td width='25%' class='multiLabel'>Req Qty</td>");
				   sb.append("<td width='25%' class='multiLabel'>Approved Qty</td>");
				 //  sb.append("<td width='25%' class='multiLabel'>Rate/Unit</td></tr>");
				  
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
					       while(ws1.next())
					       {
					    	   if(vo.getStrReqTypeId().equals("10"))
					    	    {
					    		    strIssueQty	        = ws1.getString(1);	
					    	    	strLstRecevQty      = ws1.getString(2);
					    	    	strLstRecevDate     = ws1.getString(3);
					    	    	strLstRetQtyUnitId  = ws1.getString(4);
					    	    	strItemName   = ws1.getString(5);
							        strAvlQty     = ws1.getString(6);
							        strReqQty     = ws1.getString(7);
							        strSancQty    = ws1.getString(8);
							        strRate       = ws1.getString(9);
					    	    	
							        strHiddenValue = strIssueQty+"^"+strLstRecevQty+"^"+strLstRecevDate+"^"+strLstRetQtyUnitId+"^"+strItemName;
					    	    }	
					    	   
					    	   
					    	    if(vo.getStrReqTypeId().equals("12") || vo.getStrReqTypeId().equals("13"))
					    	    {
					    	    	strIssueQty	  = ws1.getString(1);	
					    	    	strRetQty     = ws1.getString(2);
					    	    	strItemName   = ws1.getString(3);
							        strAvlQty     = ws1.getString(4);
							        strReqQty     = ws1.getString(5);
							        strSancQty    = ws1.getString(6);
							        strRate       = ws1.getString(7);
							        strHiddenValue = strIssueQty+"^"+strRetQty+"^"+strItemName;
							        
					    	    }	
					    	    if(vo.getStrReqTypeId().equals("14"))
					    	    {
					    	    	strIssueQty	        = ws1.getString(1);	
					    	    	strLstRecevQty      = ws1.getString(2);
					    	    	strLstRecevDate     = ws1.getString(3);
					    	    	strLstRetQtyUnitId  = ws1.getString(4);
					    	    	strItemName   = ws1.getString(5);
							        strAvlQty     = ws1.getString(6);
							        strReqQty     = ws1.getString(7);
							        strSancQty    = ws1.getString(8);
							        strRate       = ws1.getString(9);
					    	    	
					    	    	strHiddenValue = strIssueQty+"^"+strLstRecevQty+"^"+strLstRecevDate+"^"+strLstRetQtyUnitId+"^"+strItemName;
					    	    }	
					    	    
					    	    
					    	    
					    	    if(strItemName == null || strItemName.equals(""))  strItemName = "-----";
								if(strAvlQty == null || strAvlQty.equals("")) strAvlQty = "-----";
								if(strReqQty == null || strReqQty.equals("")) strReqQty = "-----";
								if(strSancQty == null || strSancQty.equals("")) strSancQty = "-----";
								if(strRate == null || strRate.equals("")) strRate = "-----";
								sb.append("<input type='hidden' name ='strHiddenValue'  value='"+strHiddenValue+"'>");
								sb.append("<tr>");
								sb.append("<td width='25%' class='multiControl'>");
			     		   		//sb.append("<a name='tarriff' STYLE='cursor:pointer;cursor:pointer;color:blue;' title='Get Item Details' value=''  onClick='callingPoPUp(this,"+i+",\""+strHiddenValue+"\",\""+vo.getStrReqTypeId()+"\");'>"+strItemName+"</a>");
								sb.append(strItemName);
			     		   		sb.append("</td>");
//								sb.append("<td width='20%' class='multiControl'>");
//								sb.append(strAvlQty);
//								sb.append("</td>");
								sb.append("<td width='25%' class='multiControl'>");
								sb.append(strReqQty);
								sb.append("</td>");
					
								sb.append("<td width='25%' class='multiControl'>");
								sb.append(strSancQty);
								sb.append("</td>");
//								sb.append("<td  width='25%' class='multiControl'>");
//								sb.append(strRate);
//								sb.append("</td>");
								sb.append("</tr>");
								i++;
							}
						 sb.append("</table>");
				  	     
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
			    vo.setStrMsgString("IndentViewTransHLP.getItemDetails() --> "+e.getMessage());
				vo.setStrMsgType("1");
			}
		return sb.toString();
		}
	
	
	
    public static String getItemDetails(IndentViewTransVO vo)
	{
	    StringBuffer sb = new StringBuffer("");
	    String strHiddenValue =""; 
		WebRowSet ws1 = vo.getStrItemDetailsWs();
		int i=0;
		try 
		{
		       sb.append("<table class='TABLEWIDTH' bgcolor='#6097BC' align='center'  border='0' cellspacing ='1px'>"); 
			   if(vo.getStrReqTypeId().equals("16") || vo.getStrReqTypeId().equals("18")||vo.getStrReqTypeId().equals("19"))
			   {
				    sb.append("<tr>");
				    sb.append("<td width='20%' class='multiLabel'>Item Name</td>");
				    sb.append("<td width='15%' class='multiLabel'>Batch No</td>"); 
				 //   sb.append("<td width='15%' class='multiLabel'>Aval Qty</td>"); 
				    sb.append("<td width='15%' class='multiLabel'>Req Qty</td>");
				    sb.append("<td width='15%' class='multiLabel'>Approved Qty</td>");
				 //   sb.append("<td width='20%' class='multiLabel'>Rate/Unit</td>");
				    sb.append("</tr>");   
			   }
			   else
			   {	   
		        sb.append("<tr>");
			    sb.append("<td width='40%' class='multiLabel'>Item Name</td>");
			  //  sb.append("<td width='15%' class='multiLabel'>Avalaible Qty</td>"); 
			    sb.append("<td width='15%' class='multiLabel'>Req Qty</td>");
			    sb.append("<td width='15%' class='multiLabel'>Approved Qty</td>");
			   // sb.append("<td width='15%' class='multiLabel'>Rate/Unit</td>");
			    sb.append("</tr>");
			   }
			   
			  
			      if (ws1 != null) 
				  {
					   String strItemName   = null;
				       String strAvlQty = null;
				       String strReqQty = null;
				       String strSancQty = null;
				       String strRate = null;
				       String strIssueQty = null;
				       String strReOrderLevel = null;
				       String strLstIndentQty = null;
				       String strLstIssueQty = null;
				       String strLstYerConsump = null;
				       String strLstPoNo = null;
				       String strLstPODate = null;
				       String strLstRecQty = null;
				       String strLstRecDate = null;
				       String strLstSupplBy =null;
				       String strExpDate = null;
				       String strGrpName = null;
				       String strSubGrpName = null;
				       String strBatchNo = null;
				       			       
				       while(ws1.next())
				       {
				    	    if(vo.getStrReqTypeId().equals("11")||vo.getStrReqTypeId().equals("86")||vo.getStrReqTypeId().equals("90"))
				    	    {
				    	    	strLstPoNo       = ws1.getString(1);
				    	    	strLstPODate     = ws1.getString(2);
				    	    	strLstRecDate    = ws1.getString(3);
				    	    	strLstSupplBy    = ws1.getString(4);
				    	    	strLstYerConsump = ws1.getString(5);
				    	    	strReOrderLevel  = ws1.getString(6);
				    	    	strLstRecQty     = ws1.getString(7);
				    	    	
				    	    	strItemName    = ws1.getString(8);
						        strAvlQty      = ws1.getString(9);
						        strReqQty      = ws1.getString(10);
						        strSancQty     = ws1.getString(11);
						        strRate        = ws1.getString(12);
						        strHiddenValue = strLstPoNo+"^"+strLstPODate+"^"+strLstRecDate+"^"+strLstSupplBy+"^"+strLstYerConsump+"^"+strReOrderLevel+"^"+strLstRecQty+"^"+strItemName;
						        
				    	    }	
				    	    if(vo.getStrReqTypeId().equals("16") || vo.getStrReqTypeId().equals("19") )
				    	    {
				    	    	strLstPoNo      = ws1.getString(1);
				    	    	strLstPODate    = ws1.getString(2);
				    	    	strLstRecDate   = ws1.getString(3);
				    	    	strExpDate      = ws1.getString(4);
				    	    	strLstSupplBy   = ws1.getString(5);
				    	    	strGrpName      = ws1.getString(6);
				    	    	strSubGrpName   = ws1.getString(7);
				    	    	strBatchNo      = ws1.getString(8);
				    	    	
				    	    	strItemName     = ws1.getString(9);
						        strAvlQty       = ws1.getString(10);
						        strReqQty       = ws1.getString(11);
						        strSancQty      = ws1.getString(12);
						        strRate         = ws1.getString(13);
					            strHiddenValue  = strLstPoNo+"^"+strLstPODate+"^"+strLstRecDate+"^"+strExpDate+"^"+strLstSupplBy+"^"+strGrpName+"^"+strSubGrpName+"^"+strItemName;
				    	    }	
				    	    
				    	    if(vo.getStrReqTypeId().equals("15"))
				    	    {
				    	    	strLstPoNo      = ws1.getString(1);
				    	    	strLstPODate    = ws1.getString(2);
				    	    	strLstRecDate   = ws1.getString(3);
				    	    	strLstSupplBy   = ws1.getString(4);
				    	    	strGrpName      = ws1.getString(5);
				    	    	strSubGrpName   = ws1.getString(6);
				    	    	
				    	    	strItemName     = ws1.getString(7);
						        strAvlQty       = ws1.getString(8);
						        strReqQty       = ws1.getString(9);
						        strSancQty      = ws1.getString(10);
						        strRate         = ws1.getString(11);
					            strHiddenValue  = strLstPoNo+"^"+strLstPODate+"^"+strLstRecDate+"^"+strLstSupplBy+"^"+strGrpName+"^"+strSubGrpName+"^"+strItemName;
				    	    }	
				    	    	    
				    	    
				    	    if(vo.getStrReqTypeId().equals("18"))
				    	    {
				    	    	strLstPoNo      = ws1.getString(1);
				    	    	strLstPODate    = ws1.getString(2);
				    	    	strGrpName      = ws1.getString(3);
				    	    	strSubGrpName   = ws1.getString(4);
				    	    	strExpDate      = ws1.getString(5);
				    	    	strBatchNo      = ws1.getString(6);
				    	    	strItemName     = ws1.getString(7);
						        strAvlQty       = ws1.getString(8);
						        strReqQty       = ws1.getString(9);
						        strSancQty      = ws1.getString(10);
						        strRate         = ws1.getString(11);
						        strHiddenValue  = strLstPoNo+"^"+strLstPODate+"^"+strLstRecDate+"^"+strGrpName+"^"+strSubGrpName+"^"+strExpDate+"^"+strItemName;
				    	    }	
				    	    if(vo.getStrReqTypeId().equals("17"))
				    	    {
				    	    	strIssueQty      = ws1.getString(1);
				    	    	strReOrderLevel  = ws1.getString(2);
				    	    	strLstIndentQty  = ws1.getString(3);
				    	    	strLstIssueQty   = ws1.getString(4);
				    	    	strItemName      = ws1.getString(5);
						        strAvlQty        = ws1.getString(6);
						        strReqQty        = ws1.getString(7);
						        strSancQty       = ws1.getString(8);
						        strRate          = ws1.getString(9);
						        strHiddenValue   = strIssueQty+"^"+strReOrderLevel+"^"+strLstIndentQty+"^"+strLstIssueQty+"^"+strItemName;
				    	    }	
				    	    			    	     		    	   
					  	    if(strItemName == null || strItemName.equals("")) strItemName = "-----";
							if(strAvlQty == null || strAvlQty.equals("")) strAvlQty = "-----";
							if(strReqQty == null || strReqQty.equals("")) strReqQty = "-----";
							if(strSancQty == null || strSancQty.equals("")) strSancQty = "-----";
							if(strRate == null || strRate.equals("")) strRate = "-----";
							if(strBatchNo == null || strBatchNo.equals("")) strBatchNo = "-----";
							
							if(vo.getStrReqTypeId().equals("16") || vo.getStrReqTypeId().equals("18")|| vo.getStrReqTypeId().equals("19"))
							{
							 
								 sb.append("<input type='hidden' name ='strHiddenValue'  value='"+strHiddenValue+"'>");
								 sb.append("<tr>");
								 sb.append("<td width='20%' class='multiControl'>");
			     		   		// sb.append("<a name='tarriff' value='' href='#' onClick='callingPoPUp(this,"+i+",\""+strHiddenValue+"\",\""+vo.getStrReqTypeId()+"\");'>"+strItemName+"</a>");
			     		   		 //sb.append("<a name='tarriff' STYLE='cursor:pointer;cursor:pointer;color:blue;' title='Get Item Details' value=''  onClick='callingPoPUp(this,"+i+",\""+strHiddenValue+"\",\""+vo.getStrReqTypeId()+"\");'>"+strItemName+"</a>");
								 sb.append(strItemName);
								 sb.append("</td>");
								 sb.append("<td width='15%' class='multiControl'>");
								 sb.append(strBatchNo);
								// sb.append("</td>");								 
								// sb.append("<td width='15%' class='multiControl'>");
								// sb.append(strAvlQty);
								 sb.append("</td>");
								 sb.append("<td width='15%' class='multiControl'>");
								 sb.append(strReqQty);
								 sb.append("</td>");
					
								 sb.append("<td width='15%' class='multiControl'>");
								 sb.append(strSancQty);
								 sb.append("</td>");
//								 sb.append("<td  width='20%' class='multiControl'>");
//								 sb.append(strRate);
//								 sb.append("</td>");
								 sb.append("</tr>");
																
							}
							else
							{	
							 sb.append("<input type='hidden' name ='strHiddenValue'  value='"+strHiddenValue+"'>");
							 sb.append("<tr>");
							 sb.append("<td width='40%' class='multiControl' style=\'text-align:left;\'>");
		     		   		 //sb.append("<a name='tarriff' value='' href='#' onClick='callingPoPUp(this,"+i+",\""+strHiddenValue+"\",\""+vo.getStrReqTypeId()+"\");'>"+strItemName+"</a>");
		     		   		 //sb.append("<a name='tarriff' STYLE='cursor:pointer;cursor:pointer;color:blue;' title='Get Item Details' value=''  onClick='callingPoPUp(this,"+i+",\""+strHiddenValue+"\",\""+vo.getStrReqTypeId()+"\");'>"+strItemName+"</a>");
							 sb.append(strItemName);
//							 sb.append("</td>");
//							 sb.append("<td width='15%' class='multiControl'>");
//							 sb.append(strAvlQty);
							 sb.append("</td>");
							 sb.append("<td width='15%' class='multiControl'>");
							 sb.append(strReqQty);
							 sb.append("</td>");
				
							 sb.append("<td width='15%' class='multiControl'>");
							 sb.append(strSancQty);
							 sb.append("</td>");
//							 sb.append("<td  width='15%' class='multiControl'>");
//							 sb.append(strRate);
//							 sb.append("</td>");
							 sb.append("</tr>");
							}
							i++;
						}
					 sb.append("</table>");
			  	     
		 	  }else {
				    sb.append("<table class='TABLEWIDTH' bgcolor='#6097BC' align='center'  border='0'  cellspacing ='1px'>"); 
				    sb.append("<tr>");
				    sb.append("<td colspan='5'  CLASS='multiControl' >"
							+ "<DIV class='errMsg' align='center'> NO RECORD FOUND FOR SELECTED INDENT NO. </div>" + "</TD>");

				    sb.append("</tr>");
				    sb.append("</table>");
					
			   } 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		    vo.setStrMsgString("IndentViewTransHLP.getItemDetails() --> "+e.getMessage());
			vo.setStrMsgType("1");
		}
	return sb.toString();
	}
    public static String getIndentDetailsModify(IndentViewTransVO vo)
    {
		
		StringBuffer sb = new StringBuffer("");
		
		try {
			WebRowSet ws = vo.getStrIndentDetailsWs();
			if (ws != null && ws.size() > 0) 
			{
				if (ws.next()) 
				{
										
					String strReqNo       = ws.getString(1);
					String strStoreName   = ws.getString(2);
					String strIndentDate  = ws.getString(3);
					String strItemCatg    = ws.getString(4);
					String strIndentType  = ws.getString(5);
					String strToStore     = ws.getString(6);
					
					if (strStoreName == null)
						strStoreName = "----";
					if (strItemCatg == null)
						strItemCatg = "----";
					if (strReqNo == null)
						strReqNo = "----";
					if (strIndentDate == null)
						strIndentDate = "----";
					if (strIndentType == null)
						strIndentType = "----";
					if (strToStore == null)
						strToStore = "----";
					sb.append("<table class='TABLEWIDTH'  align='center'  border='0'  cellspacing ='1px'>");
					if(vo.getStrItemCategory()!=null && vo.getStrItemCategory().equals("10"))
						sb.append("<tr><td width='25%' class='LABEL'>Drug Warehouse Name</td>");
					else
						sb.append("<tr><td width='25%' class='LABEL'>Store Name</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strStoreName);
					sb.append("</td>");
					sb.append("<td width='25%' class='LABEL'>Item Category</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strItemCatg);
					sb.append("</td></tr>");
					sb.append("<tr><td width='25%' class='LABEL'>Indent No</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strReqNo);
					sb.append("</td>");
					sb.append("<td width='25%' class='LABEL'>Indent Date</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strIndentDate);
					sb.append("</td></tr>");
					sb.append("<tr><td width='25%' class='LABEL'>Indent Type</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strIndentType);
					sb.append("</td>");
					if(vo.getStrItemCategory()!=null && vo.getStrItemCategory().equals("10"))
						sb.append("<td width='25%' class='LABEL'>To Drug Warehouse Name</td>");
					else
						sb.append("<td width='25%' class='LABEL'>To Store Name</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strToStore);
					sb.append("</td></tr>");
					sb.append("<tr>");
					 sb.append("<input type='hidden' name ='strIndentNo'  value='"+strReqNo+"'>");
					 sb.append("<input type='hidden' name ='strRaisingStore'  value='"+vo.getStrStoreId()+"'>");
					 //private String strIndentTypeId="";
					 sb.append("<input type='hidden' name ='strIndentTypeId'  value='"+vo.getStrReqTypeId()+"'>");
					 sb.append("</tr>");
					sb.append("</table>");
				}
			}
			else {
				    sb.append("<table class='TABLEWIDTH' align='center'  border='0' bgcolor='#6097BC' cellspacing ='1px'>"); 
				    sb.append("<tr>");
				    sb.append("<td colspan='5'  CLASS='multiControl' >"
							+ "<DIV class='errMsg' align='center'> NO RECORD FOUND FOR SELECTED INDENT NO. </div>" + "</TD>");

				    sb.append("</tr>");
				    sb.append("</table>");
					
			   } 
		}catch(Exception e){
			
			vo
				.setStrMsgString("IndentViewTransHLP.getItemDetails() --> "
						+e.getMessage());
			vo.setStrMsgType("1");
		}
	return sb.toString();
	}

public static String getApprovalDetails(IndentViewTransVO vo){
		
		StringBuffer br = new StringBuffer("");
		
		try {
								
			WebRowSet wb = vo.getStrApprovalDetailsWs();
			
			if(wb != null && wb.size() > 0)
			{
				br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding='1px'>");
				while (wb.next())
				{
						
					/* 
					String strApprovedBy = wb.getString(1);
					String strApprovedDate = wb.getString(2);
					String strStatus = wb.getString(3);
					
					if(strApprovedBy == null || strApprovedBy.equals("null"))strApprovedBy = "";
					if(strApprovedDate == null || strApprovedDate.equals("null"))strApprovedDate = "";
					if(strStatus == null || strStatus.equals("null"))strStatus = "";
				
					br.append("<tr>");
					br.append("<td width='5%' class='multiControl'>");
					
					br.append("<td width='30%' class='multiControl'>"+strApprovedBy+"</td>");
					br.append("<td width='30%' class='multiControl'>"+strApprovedDate+"</td>");
					br.append("<td width='30%' class='multiControl'>"+strStatus+"</td>");

					br.append("</tr>");	
					count++;
					br.append("<input type='hidden' name='strSize' value='"+count+"'>");*/
					
				}
				br.append("</table>");
			}
			else
			{
				    br.append("<table class='TABLEWIDTH' align='center'  border='0' bgcolor='#6097BC' cellspacing ='1px'>"); 
				    br.append("<tr>");
				    br.append("<td colspan='5'  CLASS='multiControl' >"
							+ "<DIV class='errMsg' align='center'> NO RECORD FOUND FOR SELECTED INDENT NO. </div>" + "</TD>");

				    br.append("</tr>");
				    br.append("</table>");
					
			 } 
		}catch(Exception e){
			
			vo
				.setStrMsgString("IndentViewTransHLP.getApprovalDetails() --> "
						+e.getMessage());
			vo.setStrMsgType("1");
		}
	return br.toString();
	}

public static String getItemDetailsModify(IndentViewTransVO vo)
{
    StringBuffer sb = new StringBuffer("");
    String strHiddenValue =""; 
	WebRowSet ws1 = vo.getStrItemDetailsWs();
	int i=0;
	try 
	{
	       sb.append("<table class='TABLEWIDTH' bgcolor='#6097BC' align='center'  border='0' cellspacing ='1px'>"); 
		   if(vo.getStrReqTypeId().equals("16") || vo.getStrReqTypeId().equals("18")||vo.getStrReqTypeId().equals("19"))
		   {
			    sb.append("<tr>");
			    sb.append("<td width='20%' class='multiLabel'>Item Name</td>");
			    sb.append("<td width='15%' class='multiLabel'>Batch No</td>"); 
			  //  sb.append("<td width='15%' class='multiLabel'>Aval Qty</td>"); 
			    sb.append("<td width='15%' class='multiLabel'>Req Qty</td>");
			    sb.append("<td width='15%' class='multiLabel'>Approved Qty</td>");
			  //  sb.append("<td width='20%' class='multiLabel'>Rate/Unit</td>");
			    sb.append("</tr>");   
		   }
		   else
		   {	   
	        sb.append("<tr>");
		    sb.append("<td width='40%' class='multiLabel'>Item Name</td>");
		  //  sb.append("<td width='15%' class='multiLabel'>Avalaible Qty</td>"); 
		    sb.append("<td width='15%' class='multiLabel'>Req Qty</td>");
		   
		 //   sb.append("<td width='15%' class='multiLabel'>Rate/Unit</td>");
		    sb.append("</tr>");
		   }
		   
		  
		      if (ws1 != null) 
			  {
				   String strItemName   = null;
			       String strAvlQty = null;
			       String strReqQty = null;
			       String strSancQty = null;
			       String strRate = null;
			       String strIssueQty = null;
			       String strReOrderLevel = null;
			       String strLstIndentQty = null;
			       String strLstIssueQty = null;
			       String strLstYerConsump = null;
			       String strLstPoNo = null;
			       String strLstPODate = null;
			       String strLstRecQty = null;
			       String strLstRecDate = null;
			       String strLstSupplBy =null;
			       String strExpDate = null;
			       String strGrpName = null;
			       String strSubGrpName = null;
			       String strBatchNo = null;
			       int IntRecQty=0;
			       String strItemBrandIds="";
			       			       
			       while(ws1.next())
			       {
			    	    if(vo.getStrReqTypeId().equals("11")||vo.getStrReqTypeId().equals("86"))
			    	    {
			    	    	strLstPoNo       = ws1.getString(1);
			    	    	strLstPODate     = ws1.getString(2);
			    	    	strLstRecDate    = ws1.getString(3);
			    	    	strLstSupplBy    = ws1.getString(4);
			    	    	strLstYerConsump = ws1.getString(5);
			    	    	strReOrderLevel  = ws1.getString(6);
			    	    	strLstRecQty     = ws1.getString(7);
			    	    	
			    	    	strItemName    = ws1.getString(8);
					        strAvlQty      = ws1.getString(9);
					        strReqQty      = ws1.getString(10);
					        strReqQty = strReqQty.split(" ")[0];
					        strItemBrandIds = ws1.getString(14);
					        strSancQty     = ws1.getString(11);
					        strRate        = ws1.getString(12);
					        strHiddenValue = strLstPoNo+"^"+strLstPODate+"^"+strLstRecDate+"^"+strLstSupplBy+"^"+strLstYerConsump+"^"+strReOrderLevel+"^"+strLstRecQty+"^"+strItemName;
					        
			    	    }	
			    	    if(vo.getStrReqTypeId().equals("16") || vo.getStrReqTypeId().equals("19") )
			    	    {
			    	    	strLstPoNo      = ws1.getString(1);
			    	    	strLstPODate    = ws1.getString(2);
			    	    	strLstRecDate   = ws1.getString(3);
			    	    	strExpDate      = ws1.getString(4);
			    	    	strLstSupplBy   = ws1.getString(5);
			    	    	strGrpName      = ws1.getString(6);
			    	    	strSubGrpName   = ws1.getString(7);
			    	    	strBatchNo      = ws1.getString(8);
			    	    	
			    	    	strItemName     = ws1.getString(9);
					        strAvlQty       = ws1.getString(10);
					        strReqQty       = ws1.getString(11);
					        strSancQty      = ws1.getString(12);
					        strRate         = ws1.getString(13);
				            strHiddenValue  = strLstPoNo+"^"+strLstPODate+"^"+strLstRecDate+"^"+strExpDate+"^"+strLstSupplBy+"^"+strGrpName+"^"+strSubGrpName+"^"+strItemName;
			    	    }	
			    	    
			    	    if(vo.getStrReqTypeId().equals("15"))
			    	    {
			    	    	strLstPoNo      = ws1.getString(1);
			    	    	strLstPODate    = ws1.getString(2);
			    	    	strLstRecDate   = ws1.getString(3);
			    	    	strLstSupplBy   = ws1.getString(4);
			    	    	strGrpName      = ws1.getString(5);
			    	    	strSubGrpName   = ws1.getString(6);
			    	    	
			    	    	strItemName     = ws1.getString(7);
					        strAvlQty       = ws1.getString(8);
					        strReqQty       = ws1.getString(9);
					        strSancQty      = ws1.getString(10);
					        strRate         = ws1.getString(11);
				            strHiddenValue  = strLstPoNo+"^"+strLstPODate+"^"+strLstRecDate+"^"+strLstSupplBy+"^"+strGrpName+"^"+strSubGrpName+"^"+strItemName;
			    	    }	
			    	    	    
			    	    
			    	    if(vo.getStrReqTypeId().equals("18"))
			    	    {
			    	    	strLstPoNo      = ws1.getString(1);
			    	    	strLstPODate    = ws1.getString(2);
			    	    	strGrpName      = ws1.getString(3);
			    	    	strSubGrpName   = ws1.getString(4);
			    	    	strExpDate      = ws1.getString(5);
			    	    	strBatchNo      = ws1.getString(6);
			    	    	strItemName     = ws1.getString(7);
					        strAvlQty       = ws1.getString(8);
					        strReqQty       = ws1.getString(9);
					        strSancQty      = ws1.getString(10);
					        strRate         = ws1.getString(11);
					        strHiddenValue  = strLstPoNo+"^"+strLstPODate+"^"+strLstRecDate+"^"+strGrpName+"^"+strSubGrpName+"^"+strExpDate+"^"+strItemName;
			    	    }	
			    	    if(vo.getStrReqTypeId().equals("17"))
			    	    {
			    	    	strIssueQty      = ws1.getString(1);
			    	    	strReOrderLevel  = ws1.getString(2);
			    	    	strLstIndentQty  = ws1.getString(3);
			    	    	strLstIssueQty   = ws1.getString(4);
			    	    	strItemName      = ws1.getString(5);
					        strAvlQty        = ws1.getString(6);
					        System.out.println("strReqQty+++>>"+strReqQty);
					         
					        strReqQty        = ws1.getString(7).split(" ")[0];
					        strSancQty       = ws1.getString(8);
					        strRate          = ws1.getString(9);
					        //added by shefali garg on 13 Nov 2014
					        if(strItemBrandIds!="")
					        strItemBrandIds = strItemBrandIds+"^"+ws1.getString(13);
					        else
					        strItemBrandIds = ws1.getString(13);
					      //  strItemQtyModified= ws1.getString(arg0)
					        strHiddenValue   = strIssueQty+"^"+strReOrderLevel+"^"+strLstIndentQty+"^"+strLstIssueQty+"^"+strItemName;
			    	    }	
			    	    			    	     		    	   
				  	    if(strItemName == null || strItemName.equals("")) strItemName = "-----";
						if(strAvlQty == null || strAvlQty.equals("")) strAvlQty = "-----";
						if(strReqQty == null || strReqQty.equals("")) strReqQty = "-----";
						if(strSancQty == null || strSancQty.equals("")) strSancQty = "-----";
						if(strRate == null || strRate.equals("")) strRate = "-----";
						if(strBatchNo == null || strBatchNo.equals("")) strBatchNo = "-----";
						
						if(vo.getStrReqTypeId().equals("16") || vo.getStrReqTypeId().equals("18")|| vo.getStrReqTypeId().equals("19"))
						{
						 
							 sb.append("<input type='hidden' name ='strHiddenValue'  value='"+strHiddenValue+"'>");
							 
							 sb.append("<tr>");
							 sb.append("<td width='20%' class='multiControl'>");
		     		   		// sb.append("<a name='tarriff' value='' href='#' onClick='callingPoPUp(this,"+i+",\""+strHiddenValue+"\",\""+vo.getStrReqTypeId()+"\");'>"+strItemName+"</a>");
		     		   		 //sb.append("<a name='tarriff' STYLE='cursor:pointer;cursor:pointer;color:blue;' title='Get Item Details' value=''  onClick='callingPoPUp(this,"+i+",\""+strHiddenValue+"\",\""+vo.getStrReqTypeId()+"\");'>"+strItemName+"</a>");
							 sb.append(strItemName);
							 sb.append("</td>");
							 sb.append("<td width='15%' class='multiControl'>");
							 sb.append(strBatchNo);
							 sb.append("</td>");								 
							// sb.append("<td width='15%' class='multiControl'>");
							// sb.append(strAvlQty);
							// sb.append("</td>");
							 sb.append("<td width='15%' class='multiControl'>");
							 sb.append("input type='text' name ='strReqQty'  value='"+strReqQty+"'>");

						
							 sb.append("</td>");
				
							 sb.append("<td width='15%' class='multiControl'>");
							 sb.append(strSancQty);
							 sb.append("</td>");
//							 sb.append("<td  width='20%' class='multiControl'>");
//							 sb.append(strRate);
//							 sb.append("</td>");
							 sb.append("</tr>");
															
						}
						else
						{	
						 sb.append("<input type='hidden' name ='strHiddenValue'  value='"+strHiddenValue+"'>");
						 sb.append("<tr>");
						 sb.append("<td width='40%' class='multiControl' style=\'text-align:left;\'>");
	     		   	   //sb.append("<a name='tarriff' value='' href='#' onClick='callingPoPUp(this,"+i+",\""+strHiddenValue+"\",\""+vo.getStrReqTypeId()+"\");'>"+strItemName+"</a>");
	     		   	   //sb.append("<a name='tarriff' STYLE='cursor:pointer;cursor:pointer;color:blue;' title='Get Item Details' value=''  onClick='callingPoPUp(this,"+i+",\""+strHiddenValue+"\",\""+vo.getStrReqTypeId()+"\");'>"+strItemName+"</a>");
						 sb.append(strItemName);
						 sb.append("</td>");
						// sb.append("<td width='15%' class='multiControl'>");
					   //sb.append("<input type='text' name ='strAvlQty'  value='"+strAvlQty+"'>");
						// sb.append(strAvlQty);
						// sb.append("</td>");
						 sb.append("<td width='15%' class='multiControl'>");
						 sb.append("<input type='text' name ='strReqQty'  onkeypress='return validateData(event,5);' value='"+strReqQty+"'>");
						 sb.append("</td>");
			
					   /*sb.append("<td width='15%' class='multiControl'>");
						 sb.append(strSancQty);
						 sb.append("</td>");*/
//						 sb.append("<td  width='15%' class='multiControl'>");
//						 sb.append(strRate);
//						 sb.append("</td>");
						 sb.append("</tr>");
						 sb.append("<tr>");
						}
						
			     /*  else
			       { 
						 sb.append("<input type='hidden' name ='strHiddenValue'  value='"+strHiddenValue+"'>");
						 sb.append("<tr>");
						 sb.append("<td width='40%' class='multiControl' style=\'text-align:left;\'>");
	     		   	   //sb.append("<a name='tarriff' value='' href='#' onClick='callingPoPUp(this,"+i+",\""+strHiddenValue+"\",\""+vo.getStrReqTypeId()+"\");'>"+strItemName+"</a>");
	     		   	   //sb.append("<a name='tarriff' STYLE='cursor:pointer;cursor:pointer;color:blue;' title='Get Item Details' value=''  onClick='callingPoPUp(this,"+i+",\""+strHiddenValue+"\",\""+vo.getStrReqTypeId()+"\");'>"+strItemName+"</a>");
						 sb.append(strItemName);
						 sb.append("</td>");
						 sb.append("<td width='15%' class='multiControl'>");
					   //sb.append("<input type='text' name ='strAvlQty'  value='"+strAvlQty+"'>");
						 sb.append(strAvlQty);
						 sb.append("</td>");
						 sb.append("<td width='15%' class='multiControl'>");
						 sb.append("<input type='text' name ='strReqQty'  onkeypress='return validateData(event,5);' value='"+strReqQty+"'>");
						 sb.append("</td>");
			
					   sb.append("<td width='15%' class='multiControl'>");
						 sb.append(strSancQty);
						 sb.append("</td>");
						 sb.append("<td  width='15%' class='multiControl'>");
						 sb.append(strRate);
						 sb.append("</td>");
						 sb.append("</tr>");
						 sb.append("<tr>");
					}*/
						i++;
					}
			     sb.append("<tr><input type='hidden' name ='strItemBrandIds'  value='"+strItemBrandIds+"'>");
				 sb.append("<input type='hidden' name ='strModifedQty'  value=''></tr>");

			     sb.append("</table>");
		  	     
	 	  }else {
			    sb.append("<table class='TABLEWIDTH' bgcolor='#6097BC' align='center'  border='0'  cellspacing ='1px'>"); 
			    sb.append("<tr>");
			    sb.append("<td colspan='5'  CLASS='multiControl' >"
						+ "<DIV class='errMsg' align='center'> NO RECORD FOUND FOR SELECTED INDENT NO. </div>" + "</TD>");

			    sb.append("</tr>");
			    sb.append("</table>");
				
		   } 
	}
	catch(Exception e)
	{
		e.printStackTrace();
	    vo.setStrMsgString("IndentViewTransHLP.getItemDetails() --> "+e.getMessage());
		vo.setStrMsgType("1");
	}
return sb.toString();
}

public static String getIndentDetails(IndentViewTransVO vo)
{
	
	StringBuffer sb = new StringBuffer("");
	
	try {
		WebRowSet ws = vo.getStrIndentDetailsWs();
		if (ws != null && ws.size() > 0) 
		{
			if (ws.next()) 
			{
									
				String strReqNo       = ws.getString(1);
				String strStoreName   = ws.getString(2);
				String strIndentDate  = ws.getString(3);
				String strItemCatg    = ws.getString(4);
				String strIndentType  = ws.getString(5);
				String strToStore     = ws.getString(6);
				
				if (strStoreName == null)
					strStoreName = "----";
				if (strItemCatg == null)
					strItemCatg = "----";
				if (strReqNo == null)
					strReqNo = "----";
				if (strIndentDate == null)
					strIndentDate = "----";
				if (strIndentType == null)
					strIndentType = "----";
				if (strToStore == null)
					strToStore = "----";
				sb.append("<table class='TABLEWIDTH'  align='center'  border='0'  cellspacing ='1px'>");
				if(vo.getStrItemCategory()!=null && vo.getStrItemCategory().equals("10"))
					sb.append("<tr><td width='25%' class='LABEL'>Drug Warehouse Name</td>");
				else
					sb.append("<tr><td width='25%' class='LABEL'>Store Name</td>");
				sb.append("<td width='25%' class='CONTROL'>");
				sb.append(strStoreName);
				sb.append("</td>");
				sb.append("<td width='25%' class='LABEL'>Item Category</td>");
				sb.append("<td width='25%' class='CONTROL'>");
				sb.append(strItemCatg);
				sb.append("</td></tr>");
				sb.append("<tr><td width='25%' class='LABEL'>Indent No</td>");
				sb.append("<td width='25%' class='CONTROL'>");
				sb.append(strReqNo);
				sb.append("</td>");
				sb.append("<td width='25%' class='LABEL'>Indent Date</td>");
				sb.append("<td width='25%' class='CONTROL'>");
				sb.append(strIndentDate);
				sb.append("</td></tr>");
				sb.append("<tr><td width='25%' class='LABEL'>Indent Type</td>");
				sb.append("<td width='25%' class='CONTROL'>");
				sb.append(strIndentType);
				sb.append("</td>");
				if(vo.getStrItemCategory()!=null && vo.getStrItemCategory().equals("10"))
					sb.append("<td width='25%' class='LABEL'>To Drug Warehouse Name</td>");
				else
					sb.append("<td width='25%' class='LABEL'>To Store Name</td>");
				sb.append("<td width='25%' class='CONTROL'>");
				sb.append(strToStore);
				sb.append("</td></tr>");
				sb.append("</table>");
			}
		}
		else {
			    sb.append("<table class='TABLEWIDTH' align='center'  border='0' bgcolor='#6097BC' cellspacing ='1px'>"); 
			    sb.append("<tr>");
			    sb.append("<td colspan='5'  CLASS='multiControl' >"
						+ "<DIV class='errMsg' align='center'> NO RECORD FOUND FOR SELECTED INDENT NO. </div>" + "</TD>");

			    sb.append("</tr>");
			    sb.append("</table>");
				
		   } 
	}catch(Exception e){
		
		vo
			.setStrMsgString("IndentViewTransHLP.getItemDetails() --> "
					+e.getMessage());
		vo.setStrMsgType("1");
	}
return sb.toString();
}

}