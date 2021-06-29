<%try{ %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="mrd.MrdConfig"%>
<%@page import="mrd.transaction.controller.fb.RecordRequestApprovalFB"%>
<html>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
window.onload=function(){
	//if(document.getElementsByName("hmode")[0].value!="GETREQDETAIL"){
		//document.getElementById("cancelButtonDiv").style.display="block"
	//}
	if(document.getElementsByName("selectedRecord")){
		var selectedRecord=document.getElementsByName("selectedRecord")
		for(var i=0;i<selectedRecord.length;i++){
			selectedRecord[i].checked=true;
			enableAcceptDiv(selectedRecord[i],i)	
		}
	}
	if(document.getElementsByName("isUserEmp")[0].value=="0"){
		document.getElementById("isEmp").style.display="block";
	}
}
function getRecordDetail(obj){
	/*if(document.getElementsByName("isAcceptAll")[0].checked==false && 
		document.getElementsByName("isAcceptAll")[1].checked==false){
		alert("Please select Accept or Reject");
		obj.checked=false;
		return false;	
	}	*/
	//if(document.getElementsByName("isAcceptAll")[0].checked){
		var i=obj.value.split("#");
		//alert( obj.value);
		//alert(i[1]);
		document.getElementsByName("hmode")[0].value="GETREQDETAIL"
		document.getElementsByName("requestId")[0].value=i[0];
		document.getElementsByName("recordId")[0].value=i[1];
		document.forms[0].submit();
	//}	
}

function validateSave(){
	
//added by sandip naik on 14/06/2017 purpose:to check logged user is emp or not
// 	if(document.getElementsByName("isUserEmp")[0].value=="0" || document.getElementsByName("isUserEmp")[0].value==""){
// 		document.getElementById("isEmp").style.display="block";
// 		return true;
// 	}//end by sandip naik

		//if(document.getElementsByName("isAcceptAll")[1]){
		//alert("isAcceptAll")
		if(document.getElementsByName("isAcceptAll")[1].checked){
			if(!isEmpty(document.getElementsByName("cancelReason")[0],"Reason")){
				return false;	
			}
			
			document.getElementsByName("hmode")[0].value="SAVE"
			if(window.confirm("Are you sure want to save?")){
				document.forms[0].submit()
			}
		}
	//}
	else{
		if(validateForm()){
			document.getElementsByName("hmode")[0].value="SAVE"
			if(window.confirm("Are you sure want to save?")){
				document.forms[0].submit()
			}	
		}
		else{	
			return false;
		}
	}	
}
function checkSelectedRecordId(){
	var valid=false;
	var selectedRecord=document.getElementsByName("selectedRecordId")
	for(var i=0;i<selectedRecord.length;i++){
		if(selectedRecord[i].checked){
			valid=true;
			break;
		}			
	}
	return valid

}
function validateForm(){
	var valid=false;
	var selectedRecord=document.getElementsByName("selectedRecord");
	//alert(selectedRecord.length)
	for(var i=0;i<selectedRecord.length;i++){
		if(selectedRecord[i].checked){
			valid=true;
		}
	}
// 	if(!valid)
// 	{
// 		alert("Please Select at least one record")
// 		return false;	
// 	}
	
	for(var i=0;i<selectedRecord.length;i++){
		if(!selectedRecord[i].checked){
				
		}
	}
	if(!isEmpty(document.getElementsByName("acceptForDays")[0],"Accept For Days")){
		return false;
	}
	
	return true
}

function clearForm(){
	var selectedRecord=document.getElementsByName("selectedRecord")
	for(var i=0;i<selectedRecord.length;i++){
		selectedRecord[i].checked=false;
		//document.getElementsByName("acceptForDays")[i].value=""
		enableAcceptDiv(selectedRecord[i],i);
		document.getElementsByName("rejectReason")[i].value=""
	}
	document.getElementsByName("isAcceptAll")[0].checked=true
	showRejectDiv(document.getElementsByName("isAcceptAll")[0])
	document.getElementsByName("cancelReason")[0].value="";
	document.getElementsByName("acceptForDays")[0].value=""
}

function enableAcceptDiv(obj,index){
	var isAcceptDiv="isAcceptDiv"+index
	var reasonTextDiv="reasonTextDiv"+index
	/*
	//alert(isAcceptDiv)
	if(obj.checked){
		document.getElementById(isAcceptDiv).style.display="block";
		if(document.getElementsByName("isAccept_"+index)[1].checked){
			document.getElementById(reasonTextDiv).style.display="block";
		}
	}
	else{
		document.getElementById(isAcceptDiv).style.display="none";
		document.getElementById(reasonTextDiv).style.display="none";
	}*/
	
	if(obj.checked){
		//document.getElementById(reasonTextDiv).style.display="none";
		//document.getElementById(isAcceptDiv).style.display="block";
		document.getElementsByName("rejectReason")[index].readOnly=true;
	}
	else{
		//document.getElementById(reasonTextDiv).style.display="block";
		//document.getElementById(isAcceptDiv).style.display="none";
		document.getElementsByName("rejectReason")[index].readOnly=false;
	}
	
	
}
function showTextBox(obj,index){
	var reasonTextDiv="reasonTextDiv"+index
	if(obj.value=="0"){
		document.getElementById(reasonTextDiv).style.display="block";
		//document.getElementsByName(rejectReason)[0].readOnly=false;
	}
	else{
		document.getElementById(reasonTextDiv).style.display="none";
		//document.getElementsByName(rejectReason)[0].readOnly=true;
	}
}
function showRejectDiv(obj){
	var selectedRecord=document.getElementsByName("selectedRecord")
	if(obj.value=="0"){
		document.getElementById("rejectDiv").style.display="block";
		document.getElementById("acceptDiv").style.display="none";
		for(var i=0;i<selectedRecord.length;i++){
			selectedRecord[i].checked=false;
			selectedRecord[i].disabled=true;
			//enableAcceptDiv(selectedRecord[i],i);
			document.getElementsByName("rejectReason")[i].value=""
			document.getElementsByName("rejectReason")[i].readOnly=true;
		}
	}
	else{
		document.getElementById("rejectDiv").style.display="none";
		document.getElementById("acceptDiv").style.display="block";
		for(var i=0;i<selectedRecord.length;i++){
			selectedRecord[i].disabled=false;
			selectedRecord[i].checked=true;
			enableAcceptDiv(selectedRecord[i],i);
		}
	}
}

function validateReason(){


}

function getRecordStatus(recordId,requestId,recordType,e){
	
	var path="/HISClinical/mrd/recordRequestApproval.cnt?hmode=GETRECORDSTAUS&mrdRecordId=" +recordId+"&requestId="+requestId+"&recordType="+recordType;
	openPopup(createFHashAjaxQuery(path),e, "300", "700");

}
</script>
<body>
<html:form action="/recordRequestApproval">
	<his:TitleTag name="Record Request Approval At Department Unit Level">
	</his:TitleTag>
	<%RecordRequestApprovalFB fb=(RecordRequestApprovalFB)pageContext.findAttribute("recordRequestApprovalFB"); %>
		<his:statusNew>
			<logic:present name="<%=MrdConfig.MRD_RECORD_REQUEST_VO_LIST%>">
				<his:SubTitleTag name="Request Detail">
				</his:SubTitleTag>
				<his:ContentTag>
		     		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="5%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="select"/></b>
								</font>
								</div>
							</td>
								<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="recordId"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="recordType"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="requestDate"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="for"/>
									<bean:message key="days"/>
									</b>	
									</font>
								</div>
							</td>
							
							<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="reqPurpose"/>
									</b>	
									</font>
								</div>
							</td>
							
<!-- 							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;"> -->
<!-- 								<div align="center"> -->
<!-- 									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> -->
<!-- 									<b> -->
<%-- 									<bean:message key="priority"/> --%>
<!-- 									</b>	 -->
<!-- 									</font> -->
<!-- 								</div> -->
<!-- 							</td> -->
							
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="requestBy"/>
									</b>	
									</font>
								</div>
							</td>
<!-- 							added by sandip naik on 17/7/17 -->

							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="admissionNo"/>
									</b>	
									</font>
								</div>
							</td>
							
							
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="recordUnit"/>
									</b>	
									</font>
								</div>
							</td>
<!-- 							end by sandip naik on 17/7/17 -->
						</tr>
					<logic:iterate id="mrdRecordRequestVO" name="<%=MrdConfig.MRD_RECORD_REQUEST_VO_LIST%>" indexId="idx" type="hisglobal.vo.MrdRecordRequestDtlVO">
			 		 <tr>
						<td class="tdfontheadExam">
						<div align="center">
						
						<html:radio name="recordRequestApprovalFB" property="selectedRecordId" value="<%=mrdRecordRequestVO.getRequestId()+'#'+ mrdRecordRequestVO.getRecordId()%>" onclick="getRecordDetail(this)" tabindex="1"/>
						
						</div>
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
					    	 <bean:write name="mrdRecordRequestVO" property="recordId"/>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
						     <bean:write name="mrdRecordRequestVO" property="recordTypeName"/>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
						     <bean:write name="mrdRecordRequestVO" property="reqDate"/>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
						     <bean:write name="mrdRecordRequestVO" property="forDays"/>
					    	</font>
					     </div>   
						</td>
						
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
					    	 <bean:write name="mrdRecordRequestVO" property="purpose"/>
					    	</font>
					     </div>   
						</td>
						
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 <font color="#000000">
					  		<bean:write name="mrdRecordRequestVO" property="requestByName"/>
					     </font>
					     </div>   
						</td>
						
<!-- 						<td  class="tdfontheadExam"> -->
<!-- 					  	 <div align="center"> -->
<!-- 					  	 	<font color="#000000"> -->
<%-- <%-- 				    	 <%=MrdConfig.RECORD_REQUEST_PRIORITY_ARRAY[Integer.parseInt(mrdRecordRequestVO.getPriority())] %> --%>
<%-- 				    	 <bean:write name="mrdRecordRequestVO" property="priority"/> --%>
				    	 
<!-- 					    	</font> -->
<!-- 					     </div>    -->
<!-- 						</td> -->
<!-- 						added by sandip naik on 17/7/17 -->
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
					    	 <bean:write name="mrdRecordRequestVO" property="admno"/>
					    	</font>
					     </div>   
						</td>
						
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
					    	 <bean:write name="mrdRecordRequestVO" property="deptunitname"/>
					    	</font>
					     </div>   
						</td>
<!-- 						end by sandip naik on 17/7/17 -->
					 </tr>
              	</logic:iterate>
                </table>
            	</his:ContentTag>
			</logic:present>
			
		</his:statusNew>
		
		<his:statusRecordFound>
		<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="recordId"/>
							</font>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="recordRequestApprovalFB" property="recordId"/>
							</font>
						</td>
						<td width="25%" class="tdfonthead">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="requestBy"/>
							</font>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="recordRequestApprovalFB" property="requestByName"/>
							</font>
						</td>
						
					</tr>
					<tr>	
					<td width="25%" class="tdfonthead">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="requestDate"/>
							</font>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="recordRequestApprovalFB" property="reqDate"/>
							</font>
						</td>
						<td width="25%" class="tdfonthead">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="for"/><bean:message key="days"/>
							</font>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="recordRequestApprovalFB" property="forDays"/>
							</font>
						</td>
						
					</tr>	
					<tr>	
					<td width="25%" class="tdfonthead">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="priority"/>
							</font>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							
							&nbsp; <%=MrdConfig.RECORD_REQUEST_PRIORITY_ARRAY[Integer.parseInt(fb.getPriority())] %>
							</font>
						</td>
						<td width="25%" class="tdfonthead">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="recordType"/>
							</font>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="recordRequestApprovalFB" property="recordTypeName"/>
							</font>
						</td>
					
					</tr>	
					<tr>	
					
						<td width="25%" class="tdfonthead">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="reqPurpose"/>
							</font>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="recordRequestApprovalFB" property="purpose"/>
							</font>
						</td>
						<td width="25%" class="tdfonthead">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="recordUnit"/>
							</font>
						</td>
						<td width="25%" class="tdfont" >
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="recordRequestApprovalFB" property="deptunitname"/>
							
							</font>
						</td>
						
						
					</tr>	
					
				</table>
				<his:SubTitleTag name="Record Request Detail">
				</his:SubTitleTag>
		     	<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
						  
<!-- 							 <td width="5%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;"> -->
<!-- 								<div align="center" style="display:none"> -->
<!-- 								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> -->
<%-- 								<b><bean:message key="select"/></b> --%>
<!-- 								</font></div> -->
<!-- 							</td>   -->
							<td width="11%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="crNo"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="patient"/> <bean:message key="name"/>
									</b>	
									</font>
								</div>
							</td>
							
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<logic:equal name="recordRequestApprovalFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
											<bean:message key="fileNo"/>
										</logic:equal>
										<logic:notEqual name="recordRequestApprovalFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
											<bean:message key="admNo"/>
										</logic:notEqual>
									</b>	
									</font>
								</div>
							</td>
							<td width="13%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="remarks"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="6%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="recordStatus"/>
									</b>	
									</font>
								</div>
							</td>
							 
						</tr>
					<logic:iterate id="requestRecordVO" name="<%=MrdConfig.REQUEST_RECORD_VO_LIST%>" indexId="idx" type="hisglobal.vo.RequestRecordDtlVO">
			 		 <tr>
				
<!--  						<td class="tdfont"> -->
<!-- 						<div align="center" style="display:none"> -->
<%-- 						<%String enableAcceptDiv="enableAcceptDiv(this,"+idx+")";%> --%>
<%-- 						<input type="hidden" name="selectedRecord" value="<%=String.valueOf(idx)%>" onclick="<%=enableAcceptDiv %>" tabindex="1" disabled /> --%>
<!-- 						</div> -->
<!-- 						</td> -->
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
						     <bean:write name="requestRecordVO" property="patCrNo"/>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
						    	<bean:write name="requestRecordVO" property="patName"/>
						   </font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
					    	 <logic:equal name="recordRequestApprovalFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
									<bean:write name="requestRecordVO" property="fileNo"/>
								</logic:equal>
								<logic:notEqual name="recordRequestApprovalFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
									<bean:write name="requestRecordVO" property="patAdmNo"/>
								</logic:notEqual>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
					    	 <bean:write name="requestRecordVO" property="remarks"/>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfont">
					  	 <div align="center">
					  	 	<font color="#000000">
					    	 <a style="cursor: pointer" onclick="getRecordStatus(<%=requestRecordVO.getMrdRecordId()%>,<%=requestRecordVO.getRequestId()%>,'<bean:write name="recordRequestApprovalFB" property="recordType"/>',event)"> View</a>
					    	</font>
					     </div>   
						</td>
						 
					 </tr>
              	</logic:iterate>
                </table>
            	</his:ContentTag>
            	<his:ContentTag>
            		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="5%" class="tdfonthead">
								<font color="red">*</font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="isAccept"/>
								</font>
							</td>
							<td width="5%" class="tdfont">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<input type="radio"  name="isAcceptAll" value="1" tabindex="1" onclick="showRejectDiv(this)" checked="checked"/><bean:message key="accept"/>
									<input type="radio"  name="isAcceptAll" value="0" tabindex="1" onclick="showRejectDiv(this)"/><bean:message key="reject"/>
								</font>
								</div>
							</td>
						</tr>
					</table>
					<div id="acceptDiv" style="display: block">     
            		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
	            			<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="accept"/>
										<bean:message key="for"/>
										<bean:message key="days"/>
									</font>
								</div>
							</td>
	            			<td width="50%" class="tdfont">
						  	 	<input type="text" name="acceptForDays" maxlength="3" size="3" value="<%=fb.getForDays() %>" onkeypress="return validateNumeric(event)" tabindex="1">
						    </td>
						</tr>
					</table>
					</div>
					<div id="rejectDiv" style="display: none">              
				    <table width="100%" border="0"  cellspacing="1" cellpadding="0">
                	<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<bean:message key="reason"/>	
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<html:textarea property="cancelReason" onkeypress="return CheckMaxLength(event,this,100,1)" cols="50" tabindex="1"></html:textarea>
						</td>
					</tr>
					</table>
				</div>	 
            	</his:ContentTag>
		</his:statusRecordFound>
	
		<his:ButtonToolBarTag>
			<his:statusRecordFound>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick =" validateSave()" onkeypress="if(event.keyCode==13)validateSave()">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
			</his:statusRecordFound>
			<his:statusNew>
			<div id="saveButtonId" style="display: none">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick =" validateSave()" onkeypress="if(event.keyCode==13)validateSave()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm('CANCEL')" onkeypress="if(event.keyCode==13)submitForm('CANCEL')">
			</div>
			<div id="cancelButtonDiv" style="display: block">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
			</div>
			</his:statusNew>
			
		</his:ButtonToolBarTag>

	<html:hidden name="recordRequestApprovalFB" property="hmode"/>
	<html:hidden name="recordRequestApprovalFB"  property="requestId" />
	<html:hidden name="recordRequestApprovalFB"  property="recordId" />
	<html:hidden name="recordRequestApprovalFB"  property="isUserEmp" />
		
</html:form>
<div id="isEmp" style="display: none">
	<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									The Logged-In User is not an employee
								</font>
</div>
<his:status/>


</body>
</html>
<%}catch(Exception e){e.printStackTrace();} %>
