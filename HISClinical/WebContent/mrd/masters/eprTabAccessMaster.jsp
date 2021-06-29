<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>


<%@page import="mrd.MrdConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
	<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>
	<his:css src="/hisglobal/css/calendar-blue2.css"/>    

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
window.onload=function (){
	
	var tabIdAccess=document.getElementsByName("tabIdAccess")[0].value
	//alert(tabIdAccess)
	var tabIdArray=document.getElementsByName("tabId")
	if(tabIdArray){
		var accessTypeArray=document.getElementsByName("accessType")
		var userLevelArray=document.getElementsByName("userLevel")
				
		var tabIdAccessArray=tabIdAccess.split("@");
		for(var i=0;i<tabIdAccessArray.length;i++){
			for(var j=0;j<tabIdArray.length;j++){
				if(tabIdAccessArray[i].split("#")[0]==tabIdArray[j].value.split("#")[0]){
					tabIdArray[j].checked=true;
					accessTypeArray[j].value=tabIdAccessArray[i].split("#")[1]
					if(accessTypeArray[j].value==<%=MrdConfig.EPR_RECORD_ACCESS_USER_BOUND%>){
						document.getElementsByName("isEmpSelected")[j].value="1"
					}
					userLevelArray[j].value=tabIdAccessArray[i].split("#")[2]
					document.getElementsByName("hiddenUserLevel")[j].value=tabIdAccessArray[i].split("#")[2]
					enableAccessType(j)
					enableUser(accessTypeArray[j],j)
				}
			}
		}
	}
}
function setEprData()
{
	if(isSelected(document.getElementsByName("departmentUnitCode")[0],"Department/Unit") &&
	isSelected(document.getElementsByName("toDepartmentUnitCode")[0],"To Department/Unit")){
		document.getElementsByName("hmode")[0].value='GETEPRTAB';
		document.forms[0].submit();
	}	
}
function selectAlltabId(obj){
	var tabId=document.getElementsByName('tabId')
	if(obj.checked){
		for(var i=0;i<tabId.length;i++){
			tabId[i].checked=true;
			enableAccessType(i)
		}
	}
	else{
		for(var i=0;i<tabId.length;i++){
			tabId[i].checked=false;
			disableAccessType(i)
		}
	}
}

function enableAccessType(index){
	document.getElementsByName("accessType")[index].disabled=false;
	
}
function disableAccessType(index){
	document.getElementsByName("accessType")[index].disabled=true;
	document.getElementsByName("hiddenUserLevel")[index].disabled=true;

}
function toggleCheckBox(obj,index){
	if(obj.checked){
		enableAccessType(index)
	}
	else{
		disableAccessType(index)
	}
}

function validateForm(mode){
	if(isSelected(document.getElementsByName("departmentUnitCode")[0],"Department/Unit") &&
		isSelected(document.getElementsByName("toDepartmentUnitCode")[0],"To Department/Unit") &&
	    isSelected(document.getElementsByName("policyType")[0],"Policy Type") &&
		validateTabId() && validateAccessType())
		submitForm21(mode)	
}
function validateAccessType(){
	var accessType=document.getElementsByName("accessType")
	var tabId=document.getElementsByName("tabId")
	var valid=false;
	for(var i=0;i<tabId.length;i++){
		if(tabId[i].checked){
			if(isSelected(accessType[i],"Access Type For "+ document.getElementsByName("accessTypeName")[i].value)){
				valid= true;
			}
			else{
				return false;
			}
			if(accessType[i].value==<%=MrdConfig.EPR_RECORD_ACCESS_UNIT_BOUND%>){
				if(isSelected(document.getElementsByName("hiddenUserLevel")[i],"User Level For "+ document.getElementsByName("accessTypeName")[i].value)){
					valid= true;
				}
				else{
					return false;
				}
			}
			if(accessType[i].value==<%=MrdConfig.EPR_RECORD_ACCESS_USER_BOUND%> && document.getElementsByName("selectAllUser")[0].checked==false){
				if(isEmpty(document.getElementsByName("isEmpSelected")[i],"User For "+ document.getElementsByName("accessTypeName")[i].value)){
					valid= true;
				}
				else{
					return false;
				}
			}
		}
	}
	
	return valid
}

function validateTabId(){
	var tabId=document.getElementsByName("tabId")
	var valid=false;
	var count=0
	for(var i=0;i<tabId.length;i++){
		if(tabId[i].checked)
			count++
	}
	if(count>0)
		return true
	else{
		alert("Please select at least one tab");
		return false
	}		
}

function setPolicyType(){
	document.getElementsByName("policyType")[0].value="-1"
}

function enableUser(obj,index){
	//alert(index)
	if(obj.value==<%=MrdConfig.EPR_RECORD_ACCESS_USER_BOUND%>){
		document.getElementsByName("hiddenUserLevel")[index].disabled=true
		document.getElementById("user"+index).style.display="block"
		document.getElementById("userLevelDiv"+index).style.display="block"
	}
	else if(obj.value==<%=MrdConfig.EPR_RECORD_ACCESS_UNIT_BOUND%>){
		document.getElementById("userLevelDiv"+index).style.display="block"
		document.getElementsByName("hiddenUserLevel")[index].disabled=false
		document.getElementById("user"+index).style.display="none"
	}
	else{
		document.getElementsByName("hiddenUserLevel")[index].disabled=true
		document.getElementById("user"+index).style.display="none"
		document.getElementById("userLevelDiv"+index).style.display="none"
	}
}

function selectEmp(e,index){
	var tabId=document.getElementsByName("tabId")[index].value
	document.getElementsByName("selectedtabId")[0].value=tabId.split("#")[0]
	var path="/HISClinical/mrd/master/eprTabAccessMater.cnt?hmode=GETUSERS&selectedTabId=" + tabId.split("#")[0] +"&selectedIndex=" + index;
	openPopup(createFHashAjaxQuery(path),e,300,600);

}

function setUserLevel(obj,index){
	document.getElementsByName("userLevel")[index].value=obj.value

}
</script>
<title>Epr Tab Access Master</title>
<his:TransactionContainer>
<his:TitleTag name="Epr Tab Access Master">
</his:TitleTag>
<body>
	<html:form action="/master/eprTabAccessMater">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="from"/> <bean:message key="dept/unit"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
							<html:select name="eprTabAccessMasterFB" property="departmentUnitCode" styleClass="regcbo" tabindex="1" onchange="setPolicyType()">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MrdConfig.ESSENTIAL_DEPARTMENT_UNIT_LIST %>">
								<html:options collection="<%=MrdConfig.ESSENTIAL_DEPARTMENT_UNIT_LIST %>" labelProperty="label" property="value"/>
								</logic:present>
							</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="to"/> <bean:message key="dept/unit"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
							<html:select name="eprTabAccessMasterFB" property="toDepartmentUnitCode" styleClass="regcbo" tabindex="1" onchange="setPolicyType()">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MrdConfig.ESSENTIAL_DEPARTMENT_UNIT_LIST %>">
								<html:options collection="<%=MrdConfig.ESSENTIAL_DEPARTMENT_UNIT_LIST %>" labelProperty="label" property="value"/>
								</logic:present>
							</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="policyType"/>
	  									
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="eprTabAccessMasterFB" property="policyType" onchange="setEprData()" styleClass="regcbo" tabindex="1">
									<html:option value="-1">Select Value</html:option>
									<html:option value="<%=MrdConfig.EPR_PATIENT_CATEGORY_NORMAL %>">Normal</html:option>
									<html:option value="<%=MrdConfig.EPR_PATIENT_CATEGORY_RESTRICTED %>">Restricted</html:option>
								</html:select>
							</div>
						</td>
					</tr>
				</table>	
			</his:ContentTag>
			<his:statusTransactionInProcess>
				<his:ContentTag>
				  <table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="10%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  								<input type="checkbox" name="selectAll" onclick="selectAlltabId(this)" tabindex="1">	
	  								</b>
								</font>
							</div>
						</td>
						<td width="30%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="tabName"/>
	  							</font>
							</div>
						</td>		
						<td width="25%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
									<bean:message key="accessType"/>
	  							</font>
							</div>
						</td>		
						<td width="20%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
									<bean:message key="userlevel"/>
	  							</font>
							</div>
						</td>		
						<td width="15%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
									Select User(s)
									<html:checkbox property="selectAllUser" value="1" title="Select all users for each tab"></html:checkbox>
	  							</font>
							</div>
						</td>		
					</tr>
					
					<logic:iterate id="eprTab" name="<%=MrdConfig.ESSENTIAL_EPR_TAB_LIST %>" type="hisglobal.utility.Entry" indexId="index">
					<tr>
						<td class="tdfont">
							<div align="center">
								<%String toggleCheckBox="toggleCheckBox(this,"+index +")"; %>
								<html:checkbox property="tabId" value='<%=eprTab.getValue()+"#"+index %>' tabindex="1" onclick="<%=toggleCheckBox %>"></html:checkbox>
	  						</div>
						</td>
						<td class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:write name="eprTab" property="label"/>
	  									<input type="hidden" name="accessTypeName" value="<bean:write name="eprTab" property="label"/>">
	  								</b>
								</font>
							</div>
						</td>
						<td class="tdfont">
							<div align="center">
								<%String enableUser="enableUser(this,"+ index +")";%>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:select name="eprTabAccessMasterFB" property="accessType" onchange="<%=enableUser %>" styleClass="regcbo" tabindex="1" disabled="true">
									<html:option value="-1">Select Value</html:option>
									<html:option value="<%=MrdConfig.EPR_RECORD_ACCESS_UNIT_BOUND %>">Unit Bound</html:option>
									<html:option value="<%=MrdConfig.EPR_RECORD_ACCESS_USER_BOUND %>">User Bound</html:option>
								</html:select>
								</font>
							</div>
						</td>
						<td class="tdfont">
							<div align="center">
								<%String setUserLevel="setUserLevel(this,"+ index +")"; %>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:select name="eprTabAccessMasterFB" property="hiddenUserLevel" styleClass="regcbo" tabindex="1" disabled="true" onchange="<%=setUserLevel %>">
									<html:option value="-1">Select Value</html:option>
									<html:option value="0">No Level</html:option>
									<html:option value="1">Level 1</html:option>
									<html:option value="2">Level 2</html:option>
									<html:option value="3">Level 3</html:option>
									<html:option value="4">Level 4</html:option>
									<html:option value="5">Level 5</html:option>
									<html:option value="6">Level 6</html:option>
								</html:select>
								</font>
							</div>
							<div id="userLevelDiv<%=index%>" style="display: none">
								<html:hidden name="eprTabAccessMasterFB" property="userLevel"/>
							</div>
						</td>
						<td class="tdfont" >
							<input type="hidden" name="isEmpSelected">
							<div align="center" style="display: none" id="user<%=index%>">
							<%String selectEmp="selectEmp(event,"+index + ")"; %>
								<img class="button" src='<his:path src="/hisglobal/images/ico_myfriends.gif"/>' style=cursor:pointer tabindex="1" onclick="<%=selectEmp %>" onkeypress="if(event.keyCode==13) <%=selectEmp %>">
							</div>
						</td>
					</tr>
					</logic:iterate>
					</table>
				</his:ContentTag>
			</his:statusTransactionInProcess>
			<his:ButtonToolBarTag>
				<his:statusTransactionInProcess>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateForm('SAVE')" onkeypress="if(event.keyCode==13) validateForm('SAVE')">
				</his:statusTransactionInProcess>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick=" submitForm21('NEW')" onkeypress="if(event.keyCode==13)  submitForm('NEW');">
			</his:ButtonToolBarTag>
			
			<html:hidden name="eprTabAccessMasterFB" property="hmode"/>
			<html:hidden name="eprTabAccessMasterFB" property="tabIdAccess"/>
			<html:hidden name="eprTabAccessMasterFB" property="selectedtabId"/>
			<input type="hidden" name="selectedUserId"/>
		
		<his:status/>
	</html:form>
</body>
</his:TransactionContainer>
</html>			