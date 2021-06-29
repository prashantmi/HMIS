package mms.transactions.controller.hlp;

import hisglobal.exceptions.HisException;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.WebRowSet;

import mms.transactions.bo.ApprovalDtlHlpBO;
import mms.transactions.vo.ApprovalDtlHlpVO;
/**
 * @author Amit Kumar
 * Date of Creation : 4/6/2009
 * Date of Modification :  /  / 2009
 * Version : 1.0
 * Module  : Store
 * Description : ApprovalDtlHLP file used globally to provide Approval Details
 *               for Specific Request Type.
 */


public class ApprovalDtlHLP 
{
  	
  public static String getApprovalDtl(String strFrmStoreId,String strHospCode,String strToStoreId,String strItemCatgCode,String strReqTypeId,String strReqNo) throws IOException 
  {
	 /* Creating VO & BO Object Here */
	 ApprovalDtlHlpVO vo = new ApprovalDtlHlpVO();
	 ApprovalDtlHlpBO bo = new ApprovalDtlHlpBO();
	 /* Declaring Variable */
	 StringBuffer sb = new StringBuffer("");
	 String strLevelType     = "";
	 String strApplyClass    = "";
	 String strAuthorityName = "";
	 String strLevel         = "";
	 String strApprovalDate  = "";
	 WebRowSet ws = null;
	 WebRowSet ws1 = null;	
	 int i=0,j=0,k=0,l=0;
	
//	 System.out.println("From Store Id-->>"+strFrmStoreId);
//	 System.out.println("Hsp Code-->>"+strHospCode);
//	 System.out.println("Item Catg Id-->>"+strItemCatgCode);
//	 System.out.println("Req No-->>"+strReqNo);
//	 System.out.println("Req  Type Id-->>"+strReqTypeId);	
//	 System.out.println("To Store Id-->>"+strToStoreId);
	
	 
	 /* Setting Value in vo Object */
	 vo.setStrFrmStoreId(strFrmStoreId);
	 vo.setStrHospCode(strHospCode);
	 vo.setStrItemCatgCode(strItemCatgCode);
	 vo.setStrReqNo(strReqNo);
	 vo.setStrReqTypeId(strReqTypeId);
	 vo.setStrToStoreId(strToStoreId);
	
	 /* Calling BO Method  */
	 bo.getApprovalDtlLevel1(vo);
	
	 if(!strToStoreId.equals("0"))
	 {	
	   bo.getApprovalDtlLevel2(vo);
	 }  
	
	  ws = vo.getGblWs1();
	 ws1 = vo.getGblWs2();
	 
	   sb.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding ='1px' cellspacing ='1px'>"); 
	   sb.append("<tr><td width='35%' class='multiLabel'>Authority Name</td>");
	   sb.append("<td width='25%' class='multiLabel'>Level</td>");
	   sb.append("<td width='25%' class='multiLabel'>Approval Date</td></tr>");
	  try 
	   {
		  
		  if(vo.getStrMsgType().equals("0"))
		  {
			  if (ws != null && ws.size() != 0) 
			  {				     	
				 for(i=0;ws.next();i++)
                 {
				    	
					       strLevelType     = ws.getString(1).trim();
					       strAuthorityName = ws.getString(2).trim(); 
 					       strLevel         = ws.getString(3).trim();
 						   strApprovalDate  = ws.getString(4).trim();

 						   if(strApprovalDate.equals("-")) 
	  				       {
	  				    	 strApplyClass = "Approved";				    	   
	  				       }
	  				       else
	  				       {
	  				    	 strApplyClass = "NotApproved";    				    	   
	  				       }	 
					        if(strLevelType.equals("Raising Level"))
	  				        {
					        	
					        	if(i==0)
					        	{	
	  				              sb.append("<tr><td  colspan='3' class='CONTROL'><b>Raising Level 1</b></td></tr>");
					        	} 
	  					      	
	  											
	  						    if(strAuthorityName == null || strAuthorityName.equals("")) strAuthorityName = "-----";
	  	  				        if(strLevel == null || strLevel.equals("")) strLevel = "-----";
	  	  				        if(strApprovalDate == null || strApprovalDate.equals("")) strApprovalDate = "-----"; 
	  	                      
	  					    
							    sb.append("<tr>");
							 
							    sb.append("<td width='35%' class='"+strApplyClass+"'>");
							    sb.append(strAuthorityName);
							    sb.append("</td>");
							    sb.append("<td width='25%' class='"+strApplyClass+"'>");
							    sb.append(strLevel);
							    sb.append("</td>");
							    sb.append("<td width='25%' class='"+strApplyClass+"'>");
							    sb.append(strApprovalDate);
							    sb.append("</td>");
							    sb.append("</tr>");
	  	                      
	  				        }
					        else
	  				        {	
					        	if(k==0)
					        	{	
					        	  sb.append("<tr><td  colspan='3' class='CONTROL'><b>Admin Level(Raising) 1</b></td></tr>");
					        	 } 
	  				        	 	k++;  	  					    	 											
	  						        if(strAuthorityName == null || strAuthorityName.equals("")) strAuthorityName = "-----";
	  	  				            if(strLevel == null || strLevel.equals("")) strLevel = "-----";
	  	  				            if(strApprovalDate == null || strApprovalDate.equals("")|| strApprovalDate.equals("----")) strApprovalDate = "-----"; 
	                     
	  	  				         sb.append("<tr>");
	  							 sb.append("<td class='"+strApplyClass+"' width='35%' >");
	  							 sb.append(strAuthorityName);
	  							 sb.append("</td>");
	  							 sb.append("<td width='25%' class='"+strApplyClass+"'>");
	  							 sb.append(strLevel);
	  							 sb.append("</td>");
	  							 sb.append("<td width='25%' class='"+strApplyClass+"'>");
	  							 sb.append(strApprovalDate);
	  							 sb.append("</td>");
	  							 sb.append("</tr>");
	  	                         
	  				        }
                      }
			      }	
			      else
			      {
			    	  if(i==0)
			    	  {	  
			    	     sb.append("<tr><td colspan='3' class='multiControl'><font color='red'>No Record Found For Raising Level 1</font></td></tr>");
			    	  }
			    	  if(k==0)
			    	  {
			    		  sb.append("<tr><td colspan='3' class='multiControl'><font color='red'>No Record Found For Raising Admin (Level 1)</font></td></tr>");  
			    	  }	  
	 			     
			      } 	 
		    }
		  else
		  {
				  String err = vo.getStrMsgString();   
				  sb.append("ERROR####"+err);   
				
		   }     
		  
		 }
		 catch (SQLException e) 
         {
				new HisException("IndentViewTransaction","ApprovalDtlHLP .getApprovalDtl() -->",e.getMessage());
		 }
		try 
		{
			if(vo.getStrMsgType().equals("0"))
			{
				  if (ws1 != null && ws1.size() != 0) 
				  {				     	
					    for(j=0;ws1.next();i++)
	                    {
					     
						       strLevelType     = ws1.getString(1).trim();
						       strApprovalDate  = ws1.getString(4).trim();
						       //System.out.println("strLevelType in 2"+strLevelType);
						       //System.out.println("strApprovalDate in 2"+strApprovalDate);
						       if(strApprovalDate.equals("-")) 
		  				       {
		  				    	 strApplyClass = "Approved";				    	   
		  				       }
		  				       else
		  				       {
		  				    	 strApplyClass = "NotApproved";    				    	   
		  				       }	 
						        if(strLevelType.equals("Recieving Level"))
		  				        {
						        	if(j==0)
						        	{	
		  				             sb.append("<tr><td  colspan='3' class='CONTROL'><b>Recieving Level 2</b></td></tr>");
						        	} 
		  					      	strAuthorityName = ws1.getString(2); 
		  					    	strLevel         = ws1.getString(3);
		  							strApprovalDate  = ws1.getString(4);
		  											
		  						    if(strAuthorityName == null || strAuthorityName.equals("")) strAuthorityName = "-----";
		  	  				        if(strLevel == null || strLevel.equals("")) strLevel = "-----";
		  	  				        if(strApprovalDate == null || strApprovalDate.equals("")|| strApprovalDate.equals("-")) strApprovalDate = "-----"; 
		  	                      
		  					    
								    sb.append("<tr>");
								 
								    sb.append("<td width='35%' class='"+strApplyClass+"'>");
								    sb.append(strAuthorityName);
								    sb.append("</td>");
								    sb.append("<td width='25%' class='"+strApplyClass+"'>");
								    sb.append(strLevel);
								    sb.append("</td>");
								    sb.append("<td width='25%' class='"+strApplyClass+"'>");
								    sb.append(strApprovalDate);
								    sb.append("</td>");
								    sb.append("</tr>");
		  	                      
		  				        }
						        else
		  				        {	
						        	
		  				        	if(l==0)
		  				        	{	
		  	  					      sb.append("<tr><td  colspan='3' class='CONTROL'><b>Admin Level(Recieving) 2</b></td></tr>");
		  				        	}
		  				        	l++;
		  	  					    	strAuthorityName = ws1.getString(2);  
		  					    	    strLevel         = ws1.getString(3);
		  							    strApprovalDate  = ws1.getString(4);
		  											
		  						        if(strAuthorityName == null || strAuthorityName.equals("")) strAuthorityName = "-----";
		  	  				            if(strLevel == null || strLevel.equals("")) strLevel = "-----";
		  	  				            if(strApprovalDate == null || strApprovalDate.equals("")|| strApprovalDate.equals("-")) strApprovalDate = "-----"; 
		                     
		  	  				         sb.append("<tr>");
		  							 sb.append("<td class='"+strApplyClass+"' width='35%' >");
		  							 sb.append(strAuthorityName);
		  							 sb.append("</td>");
		  							 sb.append("<td width='25%' class='"+strApplyClass+"'>");
		  							 sb.append(strLevel);
		  							 sb.append("</td>");
		  							 sb.append("<td width='25%' class='"+strApplyClass+"'>");
		  							 sb.append(strApprovalDate);
		  							 sb.append("</td>");
		  							 sb.append("</tr>");
		  	                         
		  				        }
					        }
					  }	
				      else
				      {
				    	  if(j==0)
				    	  {	  
				    	     sb.append("<tr><td colspan='3' class='multiControl'><font color='red'>No Record Found For Recieving Level 2</font></td></tr>");
				    	  }
				    	  if(l==0)
				    	  {
				    		  sb.append("<tr><td colspan='3' class='multiControl'><font color='red'>No Record Found For Recieving Admin (Level 2)</font></td></tr>");  
				    	  }	  
		 			     
				      } 	 
			    }
			  else
			  {
					  String err = vo.getStrMsgString();   
					  sb.append("ERROR####"+err);   
					
			   }     
			  
		  }
          catch (SQLException e) 
          {
			new HisException(
					"IndentViewTransaction",
					"ApprovalDtlHLP.getApprovalDtl() -->",
					e.getMessage());
		   }
          
	    
	    sb.append("</table>");
	 
		return sb.toString();
	}
  
  public static String getPreTechApprovalDtl(String strFrmStoreId,String strHospCode,String strToStoreId,String strItemCatgCode,String strReqTypeId,String strReqNo,String strSeatId) throws IOException 
  {
	 /* Creating VO & BO Object Here */
	 ApprovalDtlHlpVO vo = new ApprovalDtlHlpVO();
	 ApprovalDtlHlpBO bo = new ApprovalDtlHlpBO();
	 /* Declaring Variable */
	 StringBuffer sb = new StringBuffer("");
	
	 WebRowSet ws = null;
     String	 strCommitteName;
     String	 strUserName; 
     String	 strApprovalDate ;
     String	 strApprovalStatus = null;
	 
	
//	 System.out.println("From Store Id-->>"+strFrmStoreId);
//	 System.out.println("Hsp Code-->>"+strHospCode);
//	 System.out.println("Item Catg Id-->>"+strItemCatgCode);
//	 System.out.println("Req No-->>"+strReqNo);
//	 System.out.println("Req  Type Id-->>"+strReqTypeId);	
//	 System.out.println("To Store Id-->>"+strToStoreId);
	
	 
		 /* Setting Value in vo Object */
		 vo.setStrFrmStoreId(strFrmStoreId);
		 vo.setStrHospCode(strHospCode);
		 vo.setStrItemCatgCode(strItemCatgCode);
		 vo.setStrSeatId(strSeatId);
		 vo.setStrReqNo(strReqNo);
		 vo.setStrReqTypeId(strReqTypeId);
		 vo.setStrToStoreId(strToStoreId);
		
		 /* Calling BO Method  */
		 bo.getPreTechApprovalDtl(vo);
	
		   ws = vo.getGblWs3();
		     
		   try 
		   {
		  
		    if(vo.getStrMsgType().equals("0"))
		    {
			    if (ws != null && ws.size() != 0) 
			    {	
			    	if(ws.next())
					{
				  	
							   strCommitteName     = ws.getString(1).trim();
						       strUserName         = ws.getString(2).trim(); 
						       strApprovalDate     = ws.getString(3).trim();
						       strApprovalStatus   = ws.getString(4).trim();
						       
					        	
						    if(!strUserName.equals("-"))
						    {   
	  											
	  						    if(strCommitteName == null || strCommitteName.equals("")) strCommitteName = "-----";
	  	  				        if(strApprovalStatus == null || strApprovalStatus.equals("")) strApprovalStatus = "-----";
	  	  				        if(strApprovalDate == null || strApprovalDate.equals("")) strApprovalDate = "-----"; 
	  	                      
		  	  				    sb.append("<table class='TABLEWIDTH' align='center'  border='0' cellspacing='1px' align='center' >");
		  	  				    sb.append("<tr class='TITLE'>");
		  	  				    sb.append("<td colspan='4'>Pre-Tech Approval</td>");
		  	  				    sb.append("</tr>");
		  	  				    sb.append("</table>");
	  					    
		  	  				    sb.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center' >");
								sb.append("<tr><td width='25%' class='LABEL'>Committe Name</td>");
								sb.append("<td width='25%' class='CONTROL'>");
								sb.append("<font color='blue'>"+strCommitteName+"</font>");
								sb.append("</td>");
								sb.append("<td width='25%' class='LABEL'>User name</td>");
								sb.append("<td width='25%' class='CONTROL'>");
								sb.append("<font color='blue'>"+strUserName+"</font>");
								sb.append("</td></tr>");
								sb.append("<tr><td width='25%' class='LABEL'>Approval Date</td>");
								sb.append("<td width='25%' class='CONTROL'>");
								sb.append("<font color='blue'>"+strApprovalDate+"</font>");
								sb.append("</td>");
								sb.append("<td width='25%' class='LABEL'>Approval Status</td>");
								sb.append("<td width='25%' class='CONTROL'>");
								sb.append("<font color='blue'>"+strApprovalStatus+"</font>");
								
								sb.append("</td></tr>");
								sb.append("</table>"); 
	  	                   
						    }
					}    
                     
			      }	
			      else
			      {
			    	      sb.append("<table class='TABLEWIDTH' align='center'  border='0' cellspacing='1px' align='center' >");
			    		  sb.append("<tr><td colspan='4' class='multiControl'><font color='red'>No Record Found For Pre-Tech Approval</font></td></tr>");  
			    		  sb.append("</table>"); 
	 			     
			      } 	 
		    }
		  else
		  {
				  String err = vo.getStrMsgString();   
				  sb.append("ERROR####"+err);   
				
		   }     
		  
		 }
		 catch (SQLException e) 
         {
			   // e.printStackTrace();
				new HisException("IndentViewTransaction","ApprovalDtlHLP .getPreTechApprovalDtl() -->",e.getMessage());
		 }
		//System.out.println("HLP OP/////"+sb.toString());	 
		return sb.toString();
	}
	

}
