<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="mrd.MrdConfig"%>

<%@page import="mrd.transaction.controller.fb.DuplicateDischargePrintingFB"%>
<%@page import="hisglobal.vo.DischargePrintingVO"%>
<%@page import="java.util.List"%>
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
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
window.onload=function(){
	
	if(document.getElementsByName("hmode")[0].value=="PRINT"){
		var path="/HISClinical/mrd/transaction/patientDischargePrintingPopup.jsp";
		var height=550;
		var width=700
		var winl = (screen.width - width) / 2;
	  	var wint = (screen.height - height) / 2;
		var child=window.open(createFHashAjaxQuery(path),"","height=" + height + ",width=" + width + ",top=" + wint + ",left=" + winl + "alwaysRaised=yes,scrollbars=yes");
		/*var isSuccessfull=window.confirm("Is printing Successfull")
		child.focus()
		if(isSuccessfull){
			document.getElementsByName("hmode")[0].value="SAVE"
			document.forms[0].submit();
		}*/
	}
	var handOverTo=document.getElementsByName("handOverTo")[0]
	if(handOverTo){
		showDiv(handOverTo)
		if(handOverTo.value=="1"){
			document.getElementsByName("handOverTo")[0].disabled=true;
		}
		if(handOverTo.value=="0"){
			document.getElementsByName("handOverTo")[0].disabled=true;
		}
	}	
}

function validateForm(){
	var valid=false;
	var handOverTo=document.getElementsByName("handOverTo")[0].value
	if(comboValidation(document.getElementsByName("handOverTo")[0],"Handed Over To")){
		valid=true	
	}
	else{
		return false;
	}
	if(handOverTo=="0"){
		if(comboValidation(document.getElementsByName("relativeCode")[0],"Relationship")
		&& (isEmpty(document.getElementsByName("relativeName")[0],"Relative Name"))
		&& (isEmpty(document.getElementsByName("relativeAddress")[0],"Address of Relative"))
		){
			valid=true;			
		}
		else{
			valid=false;
		}
	}
	if(handOverTo=="1"){
		if(isEmpty(document.getElementsByName("officerName")[0],"Officer Name")
		&& isEmpty(document.getElementsByName("officerDesign")[0],"Officer Designation")
		&& isEmpty(document.getElementsByName("batchNo")[0]," Badge No")
		){
			valid=true;	
		}
		else{
			valid=false;
		}
	}
	if(valid){
		valid=true;
	}
	else{
		valid=false;
	}
	return valid;

}

function showDiv(obj){
	if(obj.value=="0"){
		document.getElementById("relativeDiv").style.display="block";
		document.getElementById("otherInfo").style.display="block";
		document.getElementById("policeDiv").style.display="none";
	}
	if(obj.value=="1"){
		document.getElementById("relativeDiv").style.display="none";
		document.getElementById("otherInfo").style.display="block";
		document.getElementById("policeDiv").style.display="block";
	}
	if(obj.value=="2"){
		document.getElementById("relativeDiv").style.display="none";
		document.getElementById("otherInfo").style.display="block";
		document.getElementById("policeDiv").style.display="none";
	}
	/*if(obj.value=="-1"){
		document.getElementById("relativeDiv").style.display="none";
		document.getElementById("otherInfo").style.display="none";
		document.getElementById("policeDiv").style.display="none";
	}*/
	
}
 
function clearForm()
{
 	 document.getElementsByName("relativeCode")[0].value="-1";
 	 document.getElementsByName("relativeName")[0].value="";
 	 document.getElementsByName("relativeAddress")[0].value="";
 	 document.getElementsByName("isReceiptTaken")[0].checked=false;
 	 document.getElementsByName("remarks")[0].value="";
 	 document.getElementsByName("officerName")[0].value="";
 	 document.getElementsByName("officerDesign")[0].value="";
 	 document.getElementsByName("batchNo")[0].value="";
}

function getPrintDtl(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function getDischargeDtl(obj){
	var array=obj.value.split("#");
	var patCrNo= array[0]
	var profileId=array[1]
	var profileStatus=array[2]
	document.getElementsByName("patCrNo")[0].value=patCrNo
	document.getElementsByName("profileId")[0].value=profileId
	document.getElementsByName("profileStatus")[0].value=profileStatus
	document.getElementsByName("hmode")[0].value="GETDISCHARGEDTL"
	document.forms[0].submit()

}
function checkIsRecieptTaken(obj){
	if(obj.checked){
		obj.value="1"
	}	
	else{
		obj.value="0"
	}	

}
</script>
<body>
<his:TransactionContainer>
<html:form action="/duplicateDischargePrinting">
	<his:TitleTag name="Duplicate Discharge Printing">
	</his:TitleTag>
		<his:statusNew>
			<his:ContentTag>
				<his:InputCrNoTag name="duplicateDischargePrintingFB">
				</his:InputCrNoTag>
			</his:ContentTag>
		</his:statusNew>
		
		<his:statusRecordFound>
		<his:SubTitleTag name="List of Records">
		</his:SubTitleTag>
					<% 	PaginationFB fbPage= new PaginationFB();
						pageContext.setAttribute("fbPagination",fbPage);
						fbPage.setCurrentPage(((DuplicateDischargePrintingFB)request.getAttribute("duplicateDischargePrintingFB")).getCurrentPage());
						fbPage.setObjArrName(MrdConfig.DISCHARGE_PAT_LIST);
						fbPage.setAppendInTitle("Records");
						fbPage.setMaxRecords(15);
					%>
					<logic:present name="<%=MrdConfig.DISCHARGE_PAT_LIST%>">
<%--					<his:PaginationTag name="fbPagination"></his:PaginationTag>		--%>
					<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr style="height: 20px;">
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
							<td width="19%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
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
							<td width="13%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									
									<bean:message key="dischargeDate"/>
										
									</font>
								</div>
							</td>
						</tr>
						<%
						
						List list=(List)session.getAttribute(MrdConfig.DISCHARGE_PAT_LIST);
						
						//int startIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
						//int endIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX);
						int startIndex =0;
						int endIndex =list.size()-1;
						boolean disabled=false;
						for(int i=startIndex;i<=endIndex;i++)
						{
							DischargePrintingVO dischargeVO=(DischargePrintingVO)list.get(i);
							if(dischargeVO.getProfileId()==null){
								disabled=true;
							}else{
								disabled=false;
							}
						%>
			 		 <tr>
						<td class="tdfont">
						<div align="center">
						<html:radio name="duplicateDischargePrintingFB" property="selectCRNo" value='<%=dischargeVO.getPatCrNo()+"#"+(dischargeVO.getProfileId()==null?" ":dischargeVO.getProfileId())%>'
							onclick="getDischargeDtl(this)" disabled="<%=disabled%>"></html:radio>
						</div>
						</td>
						<td  class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					   <%=dischargeVO.getPatCrNo() %>
					    </font>
					     </div>   
						</td>
						<td class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					     <%=dischargeVO.getPatAdmNo() %>
					    </font>
					     </div>   
						</td>
						<td class="tdfont">
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
						<td class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					      <%=dischargeVO.getDepartment()%> /<%=dischargeVO.getDepartmentUnit()%>
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
						<td  class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					      <%=dischargeVO.getDischargeDate() %>
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
						<html:select name="duplicateDischargePrintingFB" property="handOverTo" onchange="showDiv(this)" styleClass="regcbo">
							<html:option value="-1">Select Value</html:option>
							<html:option value="<%=MrdConfig.HAND_OVER_TO_RELATIVE %>">Relative</html:option>
							<html:option value="<%=MrdConfig.HAND_OVER_TO_POLICE%>">Police</html:option>
							<html:option value="<%=MrdConfig.HAND_OVER_TO_SELF%>">Self</html:option>
						</html:select>
						<html:hidden name="duplicateDischargePrintingFB" property="handOverTo" />
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
								<bean:message key="officer"/>
								<bean:message key="name"/>
							</font>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:text name="duplicateDischargePrintingFB" property="officerName" tabindex="1" size="25" maxlength="60" onkeypress="return validateAlphabetsOnly(event,this)"></html:text>	
							</font>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="policeDesignation"/>	
							</font>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:text name="duplicateDischargePrintingFB" property="officerDesign" tabindex="1" size="25" maxlength="50" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
							</font>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="batchno"/>	
							</font>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:text name="duplicateDischargePrintingFB" property="batchNo" tabindex="1" size="25" maxlength="50" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
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
								<bean:message key="realtionship"/>	
							</font>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:select  name="duplicateDischargePrintingFB" property="relativeCode" styleClass="regcbo" tabindex="1">
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
								<bean:message key="relativename"/>	
							</font>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:text name="duplicateDischargePrintingFB" tabindex="1" property="relativeName" size="25" maxlength="80" onkeypress="return validateAlphabetsOnly(event,this)"></html:text>	
							</font>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="relativeaddress"/>	
							</font>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:textarea name="duplicateDischargePrintingFB" cols="35" tabindex="1" property="relativeAddress" onkeypress="return CheckMaxLength(event,this,100)"></html:textarea>	
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
								<bean:message key="isReceiptTaken"/>	
							</font>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%-- <html:select  name="duplicateDischargePrintingFB" property="isReceiptTaken" styleClass="regcbo" tabindex="1">
									<html:option value="-1">Select Value</html:option>
									<html:option value="1">Yes</html:option>
									<html:option value="0">No</html:option>
								</html:select>--%>
								<input type="checkbox" name="isReceiptTaken" tabindex="1" onclick="checkIsRecieptTaken(this)" value="0"/>
							</font>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="remarks"/>	
							</font>
						</td>
						<td width="50%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:textarea name="duplicateDischargePrintingFB" property="remarks" cols="35" tabindex="1" onkeypress="return CheckMaxLength(event,this,100)"></html:textarea>	
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
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm('GETPATDTL')" onkeypress="if(event.keyCode==13)submitForm('GETPATDTL')">
			</his:statusTransactionInProcess>
			<his:statusRecordFound>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
			</his:statusRecordFound>
			<his:statusNew>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm('CANCEL')" onkeypress="if(event.keyCode==13)submitForm('CANCEL')">
			</his:statusNew>
			
		</his:ButtonToolBarTag>


	<html:hidden name="duplicateDischargePrintingFB" property="hmode"/>
	<html:hidden name="duplicateDischargePrintingFB" property="departmentUnitCode"/>
	<html:hidden name="duplicateDischargePrintingFB" property="wardCode"/>
	<html:hidden name="duplicateDischargePrintingFB" property="currentPage"/>
	<html:hidden name="duplicateDischargePrintingFB" property="patCrNo"/>
	<html:hidden name="duplicateDischargePrintingFB" property="patAdmNo"/>
	<html:hidden name="duplicateDischargePrintingFB" property="selectCRNo"/>
	<html:hidden name="duplicateDischargePrintingFB" property="profileId"/>
	<html:hidden name="duplicateDischargePrintingFB" property="isDuplicate"/>
	<html:hidden name="duplicateDischargePrintingFB"  property="profileStatus" />
		
<his:status/>
</html:form>
</his:TransactionContainer>
</body>
</html>