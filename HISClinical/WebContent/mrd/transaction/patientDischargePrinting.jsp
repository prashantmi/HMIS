<%try{ %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="mrd.MrdConfig"%>

<%@page import="mrd.transaction.controller.fb.PatientDischargePrintingFB"%>
<%@page import="hisglobal.vo.DischargePrintingVO"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.Date"%>
<%@page import="registration.RegistrationConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
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
<his:javascript src="/mrd/js/dischargePrinting.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">

</script>
<body>
<his:TransactionContainer>
<html:form action="/patientDischargePrinting">
	<his:TitleTag name="Discharge Printing">
	</his:TitleTag>
		<his:statusNew>
		<his:SubTitleTag name="Choose Mode">
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
		    		<td width="65%">
			        	<div align="right">
			        	<html:radio name="patientDischargePrintingFB" property="selection" value="0" onclick="submitMode(this)"></html:radio>
							<b><bean:message key="unitwardwise"/></b>	
				     	<html:radio name="patientDischargePrintingFB" property="selection" value="1" onclick="submitMode(this)"></html:radio>
				     	<b><bean:message key="crNo"/>
						<bean:message key="wise"/></b>
						</div>
		    		</td>
				</tr>
		</table>
		</his:SubTitleTag>
		<div id="unitWardWise" style="display: block;">
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<logic:notEqual name="patientDischargePrintingFB" property="hmode" value="SHOWPATIENTDETAILS">
				 <tr>
			    	    <td width="25%" class="tdfonthead">
			        	<div align="right">
			            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="unit"/>&nbsp;
						</font>
				     	</div>
			    		</td>
			    		<td width="25%" class="tdfont">
			      		<div align="left">
			  			  <html:select name="patientDischargePrintingFB" property="departmentUnitCode"  styleClass="regcbo"  onchange="getWardByUnit()">
							<html:option value="-1">Select Value</html:option>
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
			     			  <html:select name="patientDischargePrintingFB" property="wardCode" styleClass="regcbo" onchange="submitForm21('GETDISCHARGEPAT')">
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
			<his:InputCrNoTag name="patientDischargePrintingFB">
			</his:InputCrNoTag>
			</div>
			</his:statusNew>
			<his:statusRecordFound>
					<% 	PaginationFB fbPage= new PaginationFB();
						pageContext.setAttribute("fbPagination",fbPage);
						fbPage.setCurrentPage(((PatientDischargePrintingFB)request.getAttribute("patientDischargePrintingFB")).getCurrentPage());
						fbPage.setObjArrName(MrdConfig.DISCHARGE_PAT_LIST);
						fbPage.setAppendInTitle("Records");
						fbPage.setMaxRecords(15);
					%>
					<logic:present name="<%=MrdConfig.DISCHARGE_PAT_LIST%>">
					<his:PaginationTag name="fbPagination"></his:PaginationTag>		
					<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="6%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="select"/>
									
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									
									<bean:message key="crNo"/>
									
									</font>
								</div>
							</td>
							<td width="12%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									
									<bean:message key="admNo"/>
									
									</font>
								</div>
							</td>
							<td width="20%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="patientName"/>
									
									</font>
								</div>
							</td>
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="gender/age"/>
										
									</font>
								</div>
							</td>
							<td width="12%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="dept/unit"/>
									
									</font>
								</div>
							</td>
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="ward"/>
										
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="dischargeDate"/>
									
									</font>
								</div>
							</td>
							<td width="12%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="status"/>
									
									</font>
								</div>
							</td>
						</tr>
						<%
						
						List list=(List)session.getAttribute(MrdConfig.DISCHARGE_PAT_LIST);
						
						int startIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
						int endIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX);
						
						boolean disabled=false;
						for(int i=startIndex;i<=endIndex;i++)
						{
							DischargePrintingVO dischargeVO=(DischargePrintingVO)list.get(i);
							if(dischargeVO.getPrintStatus().equals(MrdConfig.IS_PRINTED_YES)){
								disabled=true;
							}	
							else if(dischargeVO.getProfileId()==null){
								disabled=true;
							}else{
								disabled=false;
							} 
						%>
			 		 <tr>
						<td class="tdfont">
						<div align="center">
						<html:radio name="patientDischargePrintingFB" property="selectCRNo" value='<%=dischargeVO.getPatCrNo()+"#"+(dischargeVO.getProfileId()==null?" ":dischargeVO.getProfileId())%>'
							onclick="getDischargeDtl(this)" disabled="<%=disabled%>"></html:radio>
						</div>
						</td>
						<td class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					   <%=dischargeVO.getPatCrNo() %>
					    </font>
					     </div>   
						</td>
						<td  class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					     <%=dischargeVO.getPatAdmNo() %>
					    </font>
					     </div>   
						</td>
						<td  class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					    <%=dischargeVO.getPatName() %>
					    </font>
					     </div>   
						</td>
						<td  class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					      <%=dischargeVO.getGenderAge() %>
                      	 </font>
					     </div>   
						</td>
						<td  class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					      <%=dischargeVO.getDepartment() %>/<%=dischargeVO.getDepartmentUnit() %>
                      	 </font>
					     </div>   
						</td>
						<td  class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					      <%=dischargeVO.getWard() %>
                      	 </font>
					     </div>   
						</td>
						<td class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					      <%=dischargeVO.getDischargeDate() %>
                      	 </font>
					     </div>   
						</td>
						<td class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					    	<% String printStatus="";
					    	if(dischargeVO.getPrintStatus().equals(MrdConfig.IS_PRINTED_NO)){
					    		printStatus="Not Printed";	
					    	}
					    	else{
					    		printStatus="Printed";
					    	}
					    	if(dischargeVO.getProfileId()==null){
					    		printStatus="Profile Not Generated";
					    	}%>
					    	<%=printStatus%>
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
		<his:SubTitleTag name="Discharge Print Detail">
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr> 
	    			<td width="75%">
	    				<div align="right">
	    					<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
	    					<b><bean:message key="handoverto"/></b>
	    				</div>
	    			</td>
	    			<td width="25%">
						<div align="right">
						<html:select name="patientDischargePrintingFB" property="handOverTo" onchange="showDiv(this)" styleClass="regcbo">
							<html:option value="-1">Select Value</html:option>
							<html:option value="<%=MrdConfig.HAND_OVER_TO_RELATIVE %>">Relative</html:option>
							<html:option value="<%=MrdConfig.HAND_OVER_TO_POLICE%>">Police</html:option>
							<html:option value="<%=MrdConfig.HAND_OVER_TO_SELF%>">Self</html:option>
						</html:select>
						<html:hidden name="patientDischargePrintingFB" property="handOverTo" />
						</div>
		        	</td>
			   </tr>
			</table>
		</his:SubTitleTag>
			<his:ContentTag>
			<div id="policeDiv" style="display: none;">
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="officer"/>
								<bean:message key="name"/></b>	
							</font>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:text name="patientDischargePrintingFB" property="officerName" tabindex="1" size="25" maxlength="60" onkeypress="return validateAlphabetsOnly(event,this)"></html:text>	
							</font>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="policeDesignation"/></b>	
							</font>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:text name="patientDischargePrintingFB" property="officerDesign" tabindex="1" size="25" maxlength="50" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
							</font>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="batchno"/></b>	
							</font>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:text name="patientDischargePrintingFB" property="batchNo" tabindex="1" size="25" maxlength="50" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
							</font>
						</td>
					</tr>
					
				</table>	
			</div>
			
			<div id="relativeDiv" style="display: none;">
				
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="realtionship"/></b>	
							</font>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:select  name="patientDischargePrintingFB" property="relativeCode" styleClass="regcbo" tabindex="1">
									<html:option value="-1">Select Value</html:option>
									<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL %>" labelProperty="label" property="value"/>
								</html:select>	
							</font>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="relativename"/></b>	
							</font>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:text name="patientDischargePrintingFB" tabindex="1" property="relativeName" size="25" maxlength="80" onkeypress="return validateAlphabetsOnly(event,this)"></html:text>	
							</font>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="relativeaddress"/></b>	
							</font>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:textarea name="patientDischargePrintingFB" cols="35" tabindex="1" property="relativeAddress" onkeypress="return CheckMaxLength(event,this,100)"></html:textarea>	
							</font>
						</td>
					</tr>
				</table>	
				
			</div>
			<div id="otherInfo" style="display: block;">
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
<!--							<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>-->
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="isReceiptTaken"/></b>	
							</font>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%--<html:select  name="patientDischargePrintingFB" property="isReceiptTaken" styleClass="regcbo" tabindex="1">
									<html:option value="-1">Select Value</html:option>
									<html:option value="1">Yes</html:option>
									<html:option value="0">No</html:option>
								</html:select>
								--%>
								<input type="checkbox" name="isReceiptTaken" tabindex="1" onclick="checkIsRecieptTaken(this)" value="0"/>
							</font>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="remarks"/></b>	
							</font>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:textarea name="patientDischargePrintingFB" property="remarks" cols="35" tabindex="1" onkeypress="return CheckMaxLength(event,this,100)"></html:textarea>	
							</font>
						</td>
					</tr>
				</table>
			</div>	
			</his:ContentTag>	
		</his:statusTransactionInProcess>
		<his:ButtonToolBarTag>
			<his:statusTransactionInProcess>
			<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>'  style="cursor:pointer" tabindex="1" onclick ="if(validateForm()) getPrintDtl('SAVE')" onkeypress="if(event.keyCode==13)if(validateForm()) getPrintDtl('SAVE')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
			<logic:equal name="patientDischargePrintingFB" property="searchMode" value="0">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm('GETDISCHARGEPAT')" onkeypress="if(event.keyCode==13)submitForm('GETDISCHARGEPAT')">
			</logic:equal>
			<logic:equal name="patientDischargePrintingFB" property="searchMode" value="1">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm('GETPATDTL')" onkeypress="if(event.keyCode==13)submitForm('GETPATDTL')">
			</logic:equal>
			</his:statusTransactionInProcess>
			<his:statusRecordFound>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
			</his:statusRecordFound>
			<his:statusNew>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm('CANCEL')" onkeypress="if(event.keyCode==13)submitForm('CANCEL')">
			</his:statusNew>
			
		</his:ButtonToolBarTag>


	<html:hidden name="patientDischargePrintingFB" property="hmode"/>
	<html:hidden name="patientDischargePrintingFB" property="hiddenDeptUnitCode"/>
	<html:hidden name="patientDischargePrintingFB" property="departmentUnitCode"/>
	<html:hidden name="patientDischargePrintingFB" property="departmentCode"/>
	<html:hidden name="patientDischargePrintingFB" property="hiddenWardCode"/>
	<html:hidden name="patientDischargePrintingFB" property="wardCode"/>
	<html:hidden name="patientDischargePrintingFB" property="currentPage"/>
	<html:hidden name="patientDischargePrintingFB" property="patCrNo"/>
	<html:hidden name="patientDischargePrintingFB" property="patAdmNo"/>
	<html:hidden name="patientDischargePrintingFB" property="selectCRNo"/>
	<html:hidden name="patientDischargePrintingFB" property="profileId"/>
	<html:hidden name="patientDischargePrintingFB" property="isDuplicate"/>
	<html:hidden name="patientDischargePrintingFB"  property="searchMode" />
	<html:hidden name="patientDischargePrintingFB"  property="profileStatus" />
	
	<% 
	   String toDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
	%>
	<html:hidden name="patientDischargePrintingFB" property="sysDate" value="<%=toDate%>"/>	
<his:status/>
</html:form>
</his:TransactionContainer>
</body>
</html>
<%}catch(Exception e){e.printStackTrace();}%>