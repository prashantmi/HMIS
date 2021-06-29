<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="mrd.MrdConfig"%>

<%@page import="mrd.transaction.controller.fb.CaseSheetMonitoringFB"%>
<%@page import="hisglobal.vo.CaseSheetDtlVO"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.Date"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/js/jquery/jquery.dataTables.css"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/mrd/js/caseSheetMonitoring.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.dataTables.js"></script>


<script type="text/javascript">
$(document).ready(function(){
    console.log("DataTables framework loaded...");
    $('<thead></thead>').prependTo('#example').append($('#example tr:first'));
    $("#example").DataTable(); 
    $("#example_length").css({"font-weight":"bold","font-size":"12px"}) ;
    $("#example_filter").css({"font-weight":"bold","font-size":"12px"});
    $("#example_info").css({"font-weight":"bold","font-size":"12px"}) ;
    $("#example_paginate").css({"font-weight":"bold","font-size":"12px"}) ;

    console.log("Interactive table is ready!"); 
});

</script>
<body>
<his:TransactionContainer>
<html:form action="/caseSheetMonitoring">
	<his:TitleTag name="Case Sheet Monitoring">
	</his:TitleTag>
		<his:statusNew>
		<his:SubTitleTag name="Choose Mode">
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
		    		<td width="65%">
			        	<div align="right">
			        	<html:radio name="caseSheetMonitoringFB" property="selection" value="0" onclick="submitMode(this)"></html:radio>
							<b><bean:message key="unitwardwise"/></b>	
				     	<html:radio name="caseSheetMonitoringFB" property="selection" value="1" onclick="submitMode(this)"></html:radio>
				     	<b><bean:message key="crNo"/>
						<bean:message key="wise"/></b>
						</div>
		    		</td>
				</tr>
		</table>
		</his:SubTitleTag>
		<div id="unitWardWise" style="display: block;">
		<his:ContentTag>
			<table  width="100%" border="0"  cellspacing="1" cellpadding="0">
				<logic:notEqual name="caseSheetMonitoringFB" property="hmode" value="SHOWPATIENTDETAILS">
				 <tr>
			    	    <td width="25%" class="tdfonthead" style="display:none;">
			        	<div align="right">
			            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="unit"/>&nbsp;
						</font>
				     	</div>
			    		</td>
			    		<td width="25%" class="tdfont" style="display:none;">
			      		<div align="left">
			  			  <html:select name="caseSheetMonitoringFB" property="departmentUnitCode"  styleClass="regcbo"  onchange="getWardByUnit()">
							<html:option value="%">All</html:option>
							<logic:present name="<%=MrdConfig.MRD_DEPT_UNIT_LIST %>" >
						 	 <html:options collection="<%=MrdConfig.MRD_DEPT_UNIT_LIST%>" labelProperty="label" property="value"/>
						   </logic:present>
					  		</html:select>
					  	</div>
			    		</td>
			    		<td width="25%" class="tdfonthead">
			        	<div align="right">
			            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="ward"/>&nbsp;
						</font>
				     	</div>
			    		</td>
			    		<td width="25%" class="tdfont">
			      		<div align="left">
			     			  <html:select name="caseSheetMonitoringFB" property="wardCode" styleClass="regcbo" onchange="submitForm21('GETCASESHEETDTL')">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MrdConfig.MRD_WARD_LIST_ON_BASIS_UNIT %>" >
								<html:options collection="<%=MrdConfig.MRD_WARD_LIST_ON_BASIS_UNIT %>" labelProperty="label" property="value"/>
					     		</logic:present>
					  		</html:select>
					  	</div>
			    		</td>
			    	</tr>
			    	</logic:notEqual>
				</table>	
				</his:ContentTag>
			</div>
			<div id="crNoWise" style="display: none;">
			<his:InputCrNoTag name="caseSheetMonitoringFB">
			</his:InputCrNoTag>
			</div>
			</his:statusNew>
			 <his:statusRecordFound>
				<%-- 	 <% 	PaginationFB fbPage= new PaginationFB();
						pageContext.setAttribute("fbPagination",fbPage);
						fbPage.setCurrentPage(((CaseSheetMonitoringFB)request.getAttribute("caseSheetMonitoringFB")).getCurrentPage());
						fbPage.setObjArrName(MrdConfig.CASE_SHEET_DETAIL_VO_LIST);
						fbPage.setAppendInTitle("Records");
						  fbPage.setMaxRecords(15);
					%> --%>
					<logic:present name="<%=MrdConfig.CASE_SHEET_DETAIL_VO_LIST%>">
					<%--  <his:PaginationTag name="fbPagination"></his:PaginationTag>	 --%> 
					<his:ContentTag>
					<table id="example" width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="6%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="select"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="18%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="crNo"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="18%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="admNo"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="20%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="patientName"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="10%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="gender/age"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="original/duplicate"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="caseSheetStatus"/>
									</b>	
									</font>
								</div>
							</td>
						</tr>
						<%
						
						List list=(List)session.getAttribute(MrdConfig.CASE_SHEET_DETAIL_VO_LIST);
						
						/* int startIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
						int endIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX); */
						String fontCss="";
						boolean disabled=false;
						for(int i=0;i<list.size();i++)
						{
							CaseSheetDtlVO caseSheetDtlVO=(CaseSheetDtlVO)list.get(i);
							if(caseSheetDtlVO.getCaseSheetStatus().equals(MrdConfig.CASE_SHEET_DISPATCHED)){
								disabled=true; 
								/* disable=false; */
							}	
							else disabled=false;
						%>
			 		 <tr>
						<td width="6%" class="tdfont">
						<div align="center">
						<html:radio name="caseSheetMonitoringFB" property="selectCRNo" value='<%=caseSheetDtlVO.getPatCrNo()+"#"+caseSheetDtlVO.getCaseSheetStatus()
						+"#"+caseSheetDtlVO.getIsDuplicateValue()+"#"+caseSheetDtlVO.getPatAdmNo()+"#"+caseSheetDtlVO.getCaseSheetId()+"#"+caseSheetDtlVO.getCreationDate()%>' 
						onclick="getAction(this)" disabled="<%=disabled%>"></html:radio>
						</div>
						</td>
						<td width="18%" class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					   <%=caseSheetDtlVO.getPatCrNo() %>
					    </font>
					     </div>   
						</td>
						<td width="18%" class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					     <%=caseSheetDtlVO.getPatAdmNo() %>
					    </font>
					     </div>   
						</td>
						<td width="20%" class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					    <%=caseSheetDtlVO.getPatName() %>
					    </font>
					     </div>   
						</td>
						<td width="10%" class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					      <%=caseSheetDtlVO.getPatGenderAge() %>
                      	 </font>
					     </div>   
						</td>
						<td width="15%" class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					    	<%String isDuplicate=caseSheetDtlVO.getIsDuplicate();%>
					    	<%=isDuplicate%>
                      	 </font>
					     </div>   
						</td>
						<td width="15%" class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					    <%=caseSheetDtlVO.getCaseSheetStatusName()%>
                      	 </font>
					     </div>   
						</td>
					 </tr>
              <%} %>
                </table>
            	</his:ContentTag>
			</logic:present>
		</his:statusRecordFound>
		<his:statusTransactionInProcess>
			<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
			<his:SubTitleTag name="Case Sheet Update">
				<%String foundLabel=((CaseSheetMonitoringFB)pageContext.findAttribute("caseSheetMonitoringFB")).getFoundLabel(); 
				System.out.println("found label "+foundLabel);%>
				<table width="100%">
				   <tr>
			             <td width="20%"></td>       
			             <td align="right"  width="20%" nowrap="nowrap">	
			             	<div id="lostLabel" >
			             	<html:radio name="caseSheetMonitoringFB" property="updateType" value="0" onclick="showDiv(this)" tabindex="1"></html:radio>  
			             	<b><bean:message key="lost"/></b>
			             	</div>
			             </td>       
			 			 <td align="right"  width="20%" nowrap="nowrap">
			 			 	 <div id="foundLabel">
			 			 	 <html:radio name="caseSheetMonitoringFB" property="updateType" value="1" onclick="showDiv(this)" tabindex="1"></html:radio>     	
							 <b><bean:message key="found"/></b>
							 </div>
			             </td>
			             <td align="right"  width="20%" nowrap="nowrap">
			             	<div id="duplicateLabel">
			             	<html:radio name="caseSheetMonitoringFB" property="updateType" value="2" onclick="showDiv(this)" tabindex="1"></html:radio>
							<b><bean:message key="duplicate"/></b>
							</div>
			             </td>
			             <td align="right"  width="20%" nowrap="nowrap">     
			             	<div id="removeLabel">
			             	<html:radio name="caseSheetMonitoringFB" property="updateType" value="3" onclick="showDiv(this)" tabindex="1"></html:radio>
						 	<b><bean:message key="remove"/></b>
						 	</div>              
			             </td>
		            </tr>
	           </table>
			</his:SubTitleTag>
			<div id="lostDiv" style="display: none;">
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
							<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="reportedBy"/></b>	
							</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:text name="caseSheetMonitoringFB" property="reportedBy" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" maxlength="14"></html:text>	
							</font>
							
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
							<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="lostDate"/></b>	
							</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<his:date name="lostDate"></his:date>
							</font>
							&nbsp;&nbsp;&nbsp;
							<html:text name="caseSheetMonitoringFB" property="lostTimeHr" size="2" maxlength="2" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
						 	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="hr"/>
							</font>
								:
							<html:text name="caseSheetMonitoringFB" property="lostTimeMin" size="2" maxlength="2" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="min"/>
							</font>
						</td>
										
						<html:hidden name="caseSheetMonitoringFB"  property="creationDate" />
									
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
							<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="lostType"/></b>	
							</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:select  name="caseSheetMonitoringFB" property="lostType" styleClass="regcbo" tabindex="1">
									<html:option value="-1">Select Value</html:option>
									<html:option value="1">Partial</html:option>
									<html:option value="2">Complete</html:option>
								</html:select>
							</font>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="lastSeenDate"/></b>	
							</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<his:date name="lastSeenDate"></his:date>
							</font>
							&nbsp;&nbsp;&nbsp;
							<html:text name="caseSheetMonitoringFB" property="lastSeenTimeHr" size="2" maxlength="2" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
						 	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="hr"/>
							</font>
								:
							<html:text name="caseSheetMonitoringFB" property="lastSeenTimeMin" size="2" maxlength="2" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="min"/>
							</font>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="lostArea"/></b>	
							</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:text name="caseSheetMonitoringFB" property="lostArea" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" maxlength="50"></html:text>
							</font>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="remarks"/></b>	
							</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:textarea name="caseSheetMonitoringFB" property="lostRemarks" tabindex="1" onkeypress="return CheckMaxLength(event,this,100)" cols="30"></html:textarea>	
							</font>
						</td>
					</tr>
				</table>	
				</his:ContentTag>
			</div>
			
			<div id="foundDiv" style="display: none;">
				<his:ContentTag>
				<logic:present name="<%=MrdConfig.CASE_SHEET_LOST_FOUND_VO %>">
					<bean:define id="lostFoundVO" name="<%=MrdConfig.CASE_SHEET_LOST_FOUND_VO %>" type="hisglobal.vo.CaseSheetLostFoundVO"></bean:define>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="reportedBy"/></b>	
							</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="lostFoundVO" property="reportedBy" />	
							</font>
						</td>
					</tr>
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="lostType"/></b>	
							</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<logic:equal name="lostFoundVO" property="lostType" value="1">
							Partial
							</logic:equal>
							<logic:equal name="lostFoundVO" property="lostType" value="2">
							Complete
							</logic:equal>
							</font>
							
						</td>
					</tr>	
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="remarks"/></b>	
							</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								&nbsp;<bean:write name="lostFoundVO" property="lostRemarks"/>		
							</font>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="lostDate"/></b>	
							</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							      &nbsp;<bean:write name="lostFoundVO" property="lostDate"/>
						</td>
					</tr>	
					</table>			
				</logic:present>
				</his:ContentTag>
				<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
							<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="foundBy"/></b>	
							</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:text name="caseSheetMonitoringFB" property="foundBy" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" maxlength="50"></html:text>	
							</font>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
							<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="foundDate"/></b>	
							</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<input type="hidden" name="lstDate" value="${lostFoundVO.lostDate}"/>
								<his:date name="foundDate"></his:date>
							</font>
							&nbsp;&nbsp;&nbsp;
							<html:text name="caseSheetMonitoringFB" property="foundTimeHr" tabindex="1" size="2" maxlength="2" onkeypress="return validateNumeric(event)"></html:text>
						 	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="hr"/>
							</font>
								:
							<html:text name="caseSheetMonitoringFB" property="foundTimeMin" tabindex="1" size="2" maxlength="2" onkeypress="return validateNumeric(event)"></html:text>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="min"/>
							</font>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="remarks"/></b>	
							</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:textarea name="caseSheetMonitoringFB" tabindex="1" property="foundRemarks" onkeypress="return CheckMaxLength(event,this,100)" cols="30"></html:textarea>	
							</font>
						</td>
					</tr>
				</table>	
				</his:ContentTag>
			</div>
			<div id="duplicateDiv" style="display: none;">
				<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
					<td width="50%" class="tdfonthead">
					<div align="right">
						<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="remarks"/></b>	
						</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<html:textarea name="caseSheetMonitoringFB" property="remarks" onkeypress="return CheckMaxLength(event,this,100)" cols="30"></html:textarea>	
						</font>
					</td>
				</tr>
				</table>
				</his:ContentTag>
			</div>
			<div id="removeDiv" style="display: none;">
				<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
						<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="remarks"/></b>	
						</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<html:textarea name="caseSheetMonitoringFB" property="cancelRemarks" onkeypress="return CheckMaxLength(event,this,100)" cols="30"></html:textarea>	
						</font>
					</td>
				</tr>
				</table>
				</his:ContentTag>
			</div>
			
		</his:statusTransactionInProcess>
		<his:ButtonToolBarTag>
			<his:statusTransactionInProcess>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick ="updateCaseSheet()" onkeypress="if(event.keyCode==13)updateCaseSheet()">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
			<logic:equal name="caseSheetMonitoringFB" property="searchMode" value="0">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm21('GETCASESHEETDTL')" onkeypress="if(event.keyCode==13)submitForm21('GETCASESHEETDTL')">
			</logic:equal>
			<logic:equal name="caseSheetMonitoringFB" property="searchMode" value="1">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm21('GETPATDTL')" onkeypress="if(event.keyCode==13)submitForm21('GETPATDTL')">
			</logic:equal>
			</his:statusTransactionInProcess>
			<his:statusRecordFound>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm21('NEW')" onkeypress="if(event.keyCode==13)submitForm21('NEW')">
			</his:statusRecordFound>
			<his:statusNew>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
			</his:statusNew>
			
		</his:ButtonToolBarTag>


	<html:hidden name="caseSheetMonitoringFB" property="hmode"/>
	<html:hidden name="caseSheetMonitoringFB" property="departmentUnitCode"/>
	<html:hidden name="caseSheetMonitoringFB" property="departmentCode"/>
	<html:hidden name="caseSheetMonitoringFB" property="wardCode"/>
	<html:hidden name="caseSheetMonitoringFB" property="currentPage"/>
	<html:hidden name="caseSheetMonitoringFB" property="patCrNo"/>
	<html:hidden name="caseSheetMonitoringFB" property="selectCRNo"/>
	<html:hidden name="caseSheetMonitoringFB" property="caseSheetStatus"/>
	<html:hidden name="caseSheetMonitoringFB" property="isDuplicate"/>
	<html:hidden name="caseSheetMonitoringFB"  property="searchMode" />
	<html:hidden name="caseSheetMonitoringFB"  property="hideDuplicateLabel" />
	<html:hidden name="caseSheetMonitoringFB"  property="hiddenTimeHr" />
	<html:hidden name="caseSheetMonitoringFB"  property="hiddenTimeMin" />
	<html:hidden name="caseSheetMonitoringFB"  property="creationDate" />
	
	
	
	<% 
	   String toDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
	%>
	<html:hidden name="caseSheetMonitoringFB" property="sysDate" value="<%=toDate%>"/>	
	
<his:status/>
</html:form>
</his:TransactionContainer>
</body>
</html>