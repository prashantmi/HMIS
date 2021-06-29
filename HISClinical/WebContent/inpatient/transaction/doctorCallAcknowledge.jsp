<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="inpatient.transaction.controller.fb.DoctorCallBookFB"%>
<%@page import="hisglobal.vo.DoctorCallBookVO"%>
<his:javascript src="/inpatient/js/doctorCallAcknowledge.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/registration.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@page import="inpatient.InpatientConfig"%>



	<logic:equal name="DoctorCallBookFB" property="hmode" value="GETALLCALLS">
	<his:TitleTag key="doctorCallBookAllList" >
	  </his:TitleTag>
	</logic:equal>
	<logic:notEqual name="DoctorCallBookFB" property="hmode" value="GETALLCALLS">
	            
	            <his:TitleTag key="doctorCallBook" >
	            	 <table width="30%" cellpadding="0" cellspacing="1">
                  
                <tr>
                    <td width="100%" class="tdfonthead" style="background-color: #96BAEA">
                   		 <div align="right"><u>
                   		   <a style="cursor:pointer" onclick="submitForm21('GETALLCALLS')" >
							<font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  				<bean:message key="allCalls"/>
						</font>
						</a></u>
                   		 
                    	</div>
                    </td>
				</tr>
				</table>

	            </his:TitleTag>
     		
                 
	</logic:notEqual>






  	
<logic:present name="<%=InpatientConfig.CALL_ACKNOWLEDGE_VO_ARRAY %>">
	<% 	PaginationFB fbPage= new PaginationFB();
				pageContext.setAttribute("fbPagination",fbPage);
				fbPage.setCurrentPage(((DoctorCallBookFB)request.getAttribute("DoctorCallBookFB")).getCurrentPage());
				fbPage.setObjArrName(InpatientConfig.CALL_ACKNOWLEDGE_VO_ARRAY);
				fbPage.setAppendInTitle("Records");
				fbPage.setMaxRecords(5);
				%>
				<html:hidden name="DoctorCallBookFB" property="currentPage"/>
    <his:statusNew>
    <his:PaginationTag name="fbPagination"></his:PaginationTag>
      <his:ContentTag>
      
      <table width="100%" cellpadding="0" cellspacing="1">
                  
                <tr>
                   
                   <td width="5%" class="tdfonthead">
                   		 <div align="center">
                   		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                   		<b>
                   		 <bean:message key="select"/>
                    	</b>
                    	</font>
                    	</div>
                    </td>
					<td width="12%" class="tdfonthead">
                   		 <div align="center">
                   		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                   		 <b>
                   		 <bean:message key="callRaiseDate" />
                   		 </b> 
                    	</font>
                    	</div>
                    </td>
                                     
                  	<td width="15%" class="tdfonthead">
                     	<div align="center">
                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                        <b>
                        <bean:message key="crNo" />
                        </b> 
                        </font>
                        </div>
                   		</td>
                   		
                   		<td width="15%" class="tdfonthead">
                     	<div align="center">
                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                        <b>
                        <bean:message key="patientName" />
                        </b> 
                        </font>
              	        </div>
               	    </td>  
                   			<td width="6%" class="tdfonthead">
                     	<div align="center">
                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                        <b>
                        <bean:message key="callingWard" />
                        </b> 
                        </font>
              	        </div>
               	    </td>  
                   
                   	<td width="8%" class="tdfonthead">
                     	<div align="center">
                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                        <b>
                        <bean:message key="callType" />
                        </b> 
                        </font>
                        </div>
                   		</td>
                   	                  		
                   		<td width="8%" class="tdfonthead">
                     	<div align="center">
                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                        <b>
                        <bean:message key="priority" />
                        </b> 
                        </font>
                        </div>
                   		</td>
                   		<td width="8%" class="tdfonthead">
                     	<div align="center">
                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                        <b>
                        <bean:message key="status" />
                        </b> 
                        </font>
                        </div>
                   		</td>
                   		<td width="8%" class="tdfonthead">
                     	<div align="center">
                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                        <b>
                        <bean:message key="receiver" />
                        </b> 
                        </font>
                        </div>
                   		</td>
                   		
                   		  
             </tr>
                <logic:present name="<%=InpatientConfig.CALL_ACKNOWLEDGE_VO_ARRAY %>">
						<%
						DoctorCallBookVO VO[]=(DoctorCallBookVO[])session.getAttribute(InpatientConfig.CALL_ACKNOWLEDGE_VO_ARRAY);
						
						int startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
						int endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
						String fontCss="";
						for(int i=startIndex;i<=endIndex;i++)
						{
							DoctorCallBookVO callDtlVOs=VO[i];
							if(callDtlVOs.getCallPriority().equals(InpatientConfig.CALL_PRIORITY_NAME_LOW)&& !(callDtlVOs.getStatus().equals(InpatientConfig.CALL_ACKNOLWEDGED))){
						%>

                 <tr>
					
					
						<td width="5%" class="tdfont">
						<div align="center">
							 <% if(callDtlVOs.getStatus().equals(InpatientConfig.CALL_PENDING)){ %>
						<html:radio name="DoctorCallBookFB" property="selectedPatCrNo" value="<%=String.valueOf(i) %>" onclick="submitForm21('GETDETAILS')" ></html:radio>
						
						 <%} else { %>
						 <html:radio name="DoctorCallBookFB" property="selectedPatCrNo" value="<%=String.valueOf(i) %>" disabled="true" ></html:radio>
						
						 <%} %>
						</div>
					</td>
					<td width="12%" class="tdfont">
					  	 <div align="center">
					     <font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					     <%=callDtlVOs.getCallDate() %>
					<html:hidden name="DoctorCallBookFB" property="callDateFormat" value="<%=callDtlVOs.getCallDateFormat() %>" />
					<html:hidden name="DoctorCallBookFB" property="callNo" value="<%=callDtlVOs.getCallNo() %>" />
					<html:hidden name="DoctorCallBookFB" property="empNo" value="<%=callDtlVOs.getEmpNo() %>" />
					<html:hidden name="DoctorCallBookFB" property="callDate" value="<%=callDtlVOs.getCallDate() %>" />
                      	 </font>
					     </div>   
					</td>
					
										
					<td width="15%" class="tdfont">
					  	 <div align="center">
					     <font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					   <%=callDtlVOs.getPatCrNo() %>
					 
                      	 </font>
					     </div>   
					</td>
					<td width="15%" class="tdfont">
					  	 <div align="center">
					     <font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					   <%=callDtlVOs.getPatientName() %>
					 
                      	 </font>
					     </div>   
					</td>
					
					
					<td width="6%" class="tdfont">
					  	 <div align="center">
					     <font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					   <%=callDtlVOs.getWardCode() %>
                      	 </font>
					     </div>   
					</td>
					
					<td width="8%" class="tdfont">
					  	 <div align="center">
					     <font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					       <%=callDtlVOs.getCallType() %>
                      	 </font>
					     </div>   
					</td>
					
				
					<td width="8%" class="tdfont">
					  	 <div align="center">
					     <font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					     <%=callDtlVOs.getCallPriority() %>
                      	 </font>
					     </div>   
					</td>
				<td width="10%" class="tdfont">
					  	  <div align="center">
					  	 <% if(callDtlVOs.getStatus().equals(InpatientConfig.CALL_PENDING) || callDtlVOs.getCallType().equals(InpatientConfig.CALL_AUTOMATIC)){ %>
					     <font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					       <%=callDtlVOs.getStatus() %>
                      	 </font>
                      	 <%} else { 
                      	 String index=String.valueOf(i);
                      
                      	 %>
                      	 <u>
					      <a style="cursor:pointer" onclick="getRemarks(<%=index %>)" >
							<font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  				<%=callDtlVOs.getStatus() %>
						</font>
						</a> </u>
					     <%} %>
					     </div>      
					     
					</td>
					<td width="8%" class="tdfont">
					  	 <div align="center">
					     <font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					     <%=callDtlVOs.getReceiver() %>
                      	 </font>
					     </div>   
					</td>
				</tr>
                   <%}
							if(callDtlVOs.getCallPriority().equals(InpatientConfig.CALL_PRIORITY_NAME_HIGH)&& !(callDtlVOs.getStatus().equals(InpatientConfig.CALL_ACKNOLWEDGED))) { %>
				<tr>
					
						<td width="5%" class="tdfont">
						<div align="center">
							 <% if(callDtlVOs.getStatus().equals(InpatientConfig.CALL_PENDING)){ %>
						<html:radio name="DoctorCallBookFB" property="selectedPatCrNo" value="<%=String.valueOf(i) %>" onclick="submitForm21('GETDETAILS')" ></html:radio>
						
						 <%} else { %>
						 <html:radio name="DoctorCallBookFB" property="selectedPatCrNo" value="<%=String.valueOf(i) %>" disabled="true" ></html:radio>
						
						 <%} %>
						</div>
					</td>
					<td width="20%" class="tdfont">
					  	 <div align="center">
					     <font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					     <%=callDtlVOs.getCallDate() %>
					<html:hidden name="DoctorCallBookFB" property="callDateFormat" value="<%=callDtlVOs.getCallDateFormat() %>" />
					<html:hidden name="DoctorCallBookFB" property="callNo" value="<%=callDtlVOs.getCallNo() %>" />
					<html:hidden name="DoctorCallBookFB" property="empNo" value="<%=callDtlVOs.getEmpNo() %>" />
					<html:hidden name="DoctorCallBookFB" property="callDate" value="<%=callDtlVOs.getCallDate() %>" />
                      	 </font>
					     </div>   
					</td>
					
										
					<td width="15%" class="tdfont">
					  	 <div align="center">
					     <font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					   <%=callDtlVOs.getPatCrNo() %>
					 
                      	 </font>
					     </div>   
					</td>
					<td width="15%" class="tdfont">
					  	 <div align="center">
					     <font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					   <%=callDtlVOs.getPatientName() %>
					 
                      	 </font>
					     </div>   
					</td>
					
					
					<td width="6%" class="tdfont">
					  	 <div align="center">
					     <font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					   <%=callDtlVOs.getWardCode() %>
                      	 </font>
					     </div>   
					</td>
					
					<td width="8%" class="tdfont">
					  	 <div align="center">
					     <font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					       <%=callDtlVOs.getCallType() %>
                      	 </font>
					     </div>   
					</td>
					
				
					<td width="8%" class="tdfont">
					  	 <div align="center">
					     <font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					     <%=callDtlVOs.getCallPriority() %>
                      	 </font>
					     </div>   
					</td>
				<td width="15%" class="tdfont">
					  	  <div align="center">
					  	 <% if(callDtlVOs.getStatus().equals(InpatientConfig.CALL_PENDING)){ %>
					     <font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					       <%=callDtlVOs.getStatus() %>
                      	 </font>
                      	 <%} else { 
                      	 String index=String.valueOf(i);
                      
                      	 %>
                      	 <u>
					      <a style="cursor:pointer" onclick="getRemarks(<%=index %>)" >
							<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  				<%=callDtlVOs.getStatus() %>
						</font>
						</a> </u>
					     <%} %>
					     </div>      
					     
					</td>
				</tr>
							<%}if(callDtlVOs.getCallPriority().equals(InpatientConfig.CALL_PRIORITY_NAME_MEDIUM)&& !(callDtlVOs.getStatus().equals(InpatientConfig.CALL_ACKNOLWEDGED))){  %>	
						<tr>
					
						<td width="5%" class="tdfont">
						<div align="center">
							 <% if(callDtlVOs.getStatus().equals(InpatientConfig.CALL_PENDING)){ %>
						<html:radio name="DoctorCallBookFB" property="selectedPatCrNo" value="<%=String.valueOf(i) %>"  onclick="submitForm21('GETDETAILS')" ></html:radio>
						
						 <%} else { %>
						 <html:radio name="DoctorCallBookFB" property="selectedPatCrNo" value="<%=String.valueOf(i) %>" disabled="true" ></html:radio>
						
						 <%} %>
						</div>
					</td>
					<td width="20%" class="tdfont">
					  	 <div align="center">
					     <font color="green" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					     <%=callDtlVOs.getCallDate() %>
					<html:hidden name="DoctorCallBookFB" property="callDateFormat" value="<%=callDtlVOs.getCallDateFormat() %>" />
					<html:hidden name="DoctorCallBookFB" property="callNo" value="<%=callDtlVOs.getCallNo() %>" />
					<html:hidden name="DoctorCallBookFB" property="empNo" value="<%=callDtlVOs.getEmpNo() %>" />
					<html:hidden name="DoctorCallBookFB" property="callDate" value="<%=callDtlVOs.getCallDate() %>" />
                      	 </font>
					     </div>   
					</td>
					
										
					<td width="15%" class="tdfont">
					  	 <div align="center">
					     <font color="green" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					   <%=callDtlVOs.getPatCrNo() %>
					 
                      	 </font>
					     </div>   
					</td>
					<td width="15%" class="tdfont">
					  	 <div align="center">
					     <font color="green" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					   <%=callDtlVOs.getPatientName() %>
					 
                      	 </font>
					     </div>   
					</td>
					
					
					<td width="6%" class="tdfont">
					  	 <div align="center">
					     <font color="green" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					   <%=callDtlVOs.getWardCode() %>
                      	 </font>
					     </div>   
					</td>
					
					<td width="8%" class="tdfont">
					  	 <div align="center">
					     <font color="green" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					       <%=callDtlVOs.getCallType() %>
                      	 </font>
					     </div>   
					</td>
					
				
					<td width="8%" class="tdfont">
					  	 <div align="center">
					     <font color="green" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					     <%=callDtlVOs.getCallPriority() %>
                      	 </font>
					     </div>   
					</td>
			<td width="15%" class="tdfont">
					  	  <div align="center">
					  	 <% if(callDtlVOs.getStatus().equals(InpatientConfig.CALL_PENDING)){ %>
					     <font color="green" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					       <%=callDtlVOs.getStatus() %>
                      	 </font>
                      	 <%} else { 
                      	 String index=String.valueOf(i);
                      
                      	 %>
                      	 <u>
					      <a style="cursor:pointer" onclick="getRemarks(<%=index %>)" >
							<font color="green" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  				<%=callDtlVOs.getStatus() %>
						</font>
						</a> </u>
					     <%} %>
					     </div>      
					     
					</td>
				</tr>
				
				<%}if(callDtlVOs.getStatus().equals(InpatientConfig.CALL_ACKNOLWEDGED)){  %>	
						<tr>
					
						<td width="5%" class="tdfont">
						<div align="center">
							 <% if(callDtlVOs.getStatus().equals(InpatientConfig.CALL_PENDING)){ %>
						<html:radio name="DoctorCallBookFB" property="selectedPatCrNo" value="<%=String.valueOf(i) %>"  onclick="submitForm21('GETDETAILS')" ></html:radio>
						
						 <%} else { %>
						 <html:radio name="DoctorCallBookFB" property="selectedPatCrNo" value="<%=String.valueOf(i) %>" disabled="true" ></html:radio>
						
						 <%} %>
						</div>
					</td>
					<td width="20%" class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					     <%=callDtlVOs.getCallDate() %>
					<html:hidden name="DoctorCallBookFB" property="callDateFormat" value="<%=callDtlVOs.getCallDateFormat() %>" />
					<html:hidden name="DoctorCallBookFB" property="callNo" value="<%=callDtlVOs.getCallNo() %>" />
					<html:hidden name="DoctorCallBookFB" property="empNo" value="<%=callDtlVOs.getEmpNo() %>" />
					<html:hidden name="DoctorCallBookFB" property="callDate" value="<%=callDtlVOs.getCallDate() %>" />
                      	 </font>
					     </div>   
					</td>
					
										
					<td width="15%" class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					   <%=callDtlVOs.getPatCrNo() %>
					 
                      	 </font>
					     </div>   
					</td>
					<td width="15%" class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					   <%=callDtlVOs.getPatientName() %>
					 
                      	 </font>
					     </div>   
					</td>
					
					
					<td width="6%" class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					   <%=callDtlVOs.getWardCode() %>
                      	 </font>
					     </div>   
					</td>
					
					<td width="8%" class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					       <%=callDtlVOs.getCallType() %>
                      	 </font>
					     </div>   
					</td>
					
				
					<td width="8%" class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					     <%=callDtlVOs.getCallPriority() %>
                      	 </font>
					     </div>   
					</td>
			<td width="15%" class="tdfont">
					  	  <div align="center">
					  	 <% if(callDtlVOs.getStatus().equals(InpatientConfig.CALL_PENDING)){ %>
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					       <%=callDtlVOs.getStatus() %>
                      	 </font>
                      	 <%} else { 
                      	 String index=String.valueOf(i);
                      
                      	 %>
                      	 <u>
					      <a style="cursor:pointer" onclick="getRemarks(<%=index %>)" >
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  				<%=callDtlVOs.getStatus() %>
						</font>
						</a> </u>
					     <%} %>
					     </div>      
					     
					</td>
				</tr>
				<%}} %>							
               </logic:present>
          
          
      </table>
     </his:ContentTag> 
      </his:statusNew>
      
      <his:statusRecordFound>
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="callRemarks"/>
						</font>
					</div>
				</td>
				<td width="25%" colspan="2" class="tdfont">
			          <div align="left">
			           &nbsp;<html:textarea name="DoctorCallBookFB" property="callRemarks" rows="2" cols="70" disabled="true" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))">
						   </html:textarea>
						</div>				
			   
				</tr>
			<tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="doctorRemarks"/>
						</font>
					</div>
				</td>
				<td width="25%" colspan="2" class="tdfont">
			          <div align="left">
			           &nbsp;<html:textarea name="DoctorCallBookFB" property="doctorRemarks" rows="2" cols="70" disabled="true" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))">
						   </html:textarea>
						</div>				
			        </td>
			       
			      
				</tr>
				</table>
		</his:ContentTag>
  </his:statusRecordFound>
      
     <his:statusDone>
    <his:SubTitleTag name="Call Details">
	</his:SubTitleTag>
		
		
	<his:ContentTag>
    	<table width="100%" border="0" cellspacing="1" cellpadding="0">
    	<tr> 
			        <td width="25%" class="tdfonthead">
					<div align="right"">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="callRaisedBy"/>
						</font>
					</div>
				</td>
			        <td width="75%" class="tdfont">
			         <div align="left">
				     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					     <bean:write name="DoctorCallBookFB" property="raiseByEmpNo"/>
					 
                      	 </font>
				     </div>
			       </td>  
			       </tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="con"/>
						</font>
					</div>
				</td>
				<td width="75%" class="tdfont">
			          <div align="left">
			         <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					     <bean:write name="DoctorCallBookFB" property="consultantName"/>
					 
                      	 </font>
						</div>				
			        </td>
			</tr>
				<tr> 
			        <td width="25%" class="tdfonthead">
					<div align="right"">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="priority"/>
						</font>
					</div>
				</td>
			        <td width="75%" class="tdfont">
			         <div align="left">
				     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					     <bean:write name="DoctorCallBookFB" property="callPriorityName"/>
					 
                      	 </font>
				     </div>
			       </td> 
			         <td width="10%"  class="tdfont"></td> 
			       </tr>
			       <tr>
					<td width="25%" class="tdfonthead">
						<div align="right"">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="callRemarks"/>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont">
				          <div align="left">
        			           
 								&nbsp;<html:textarea name="DoctorCallBookFB" property="callRemarks" rows="2" cols="70" disabled="true" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))">
						   </html:textarea>
							</div>				
				        </td>
				          <td width="10%"  class="tdfont"></td>
				    </tr>
			 	
					<tr>
						<td width="25%" class="tdfonthead">
					 		<div align="right">
					 			<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="doctorRemarks"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont" colspan="3">
							<div align="left">
							  &nbsp;<html:textarea name="DoctorCallBookFB" property="doctorRemarks" rows="2" cols="70" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))">
						   </html:textarea>
						   <html:button value="Add Doctor Remarks"  property="mybutton" onclick="addProgressNotes(event)" style='cursor:pointer'  tabindex='1'/>
							</div>
						</td>
						
					 </tr>
				</table>	
			</his:ContentTag>	
	  </his:statusDone>     
      
      
      
  </logic:present>

  

<his:ButtonToolBarTag>
		 		<logic:equal value="GETDETAILS" name="DoctorCallBookFB"  property="hmode" >
		 		<logic:equal value="<%=InpatientConfig.CALL_PENDING %>" name="DoctorCallBookFB"  property="status" >
				  <img class='button' src='<his:path src="/hisglobal/images/Acknowledge.png"/>'  style=cursor:pointer onclick ="finalSubmit('ACKNOWLEDGE')" onkeypress="if(event.keyCode==13) finalSubmit('ACKNOWLEDGE')") tabindex="1">
				  </logic:equal>
				  <logic:equal value="PAGINATION" name="DoctorCallBookFB"  property="hmode" >
				  <img class='button' src='<his:path src="/hisglobal/images/Acknowledge.png"/>'  style=cursor:pointer onclick ="finalSubmit('ACKNOWLEDGE')" onkeypress="if(event.keyCode==13) finalSubmit('ACKNOWLEDGE')") tabindex="1">
				  </logic:equal>
		</logic:equal>
		  	<logic:equal value="GETDETAILS" name="DoctorCallBookFB"  property="hmode" >
		 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer onclick ="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW')" tabindex="1">
		 </logic:equal>
		 	<logic:equal value="NEW" name="DoctorCallBookFB"  property="hmode" >
           <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')" tabindex="1">
          </logic:equal>
          <logic:equal name="DoctorCallBookFB" property="hmode" value="GETCALLREMARKS">
            <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer onclick ="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW')" tabindex="1">
          </logic:equal>
          <logic:equal name="DoctorCallBookFB" property="hmode" value="GETALLCALLS">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer onclick ="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW')" tabindex="1">
          </logic:equal>
          <logic:equal name="DoctorCallBookFB" property="hmode" value="ACKNOWLEDGE">
            <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')" tabindex="1">
          </logic:equal>
         
</his:ButtonToolBarTag>

<html:hidden name="DoctorCallBookFB" property="hmode"/>
<html:hidden name="DoctorCallBookFB" property="empNo"/>
<html:hidden name="DoctorCallBookFB" property="callNo"/>

<html:hidden name="DoctorCallBookFB" property="callDateFormat"/>
<html:hidden name="DoctorCallBookFB" property="selectedPatCrNo"/>
<html:hidden name="DoctorCallBookFB" property="index"/>