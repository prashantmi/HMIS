<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="registration.*"%>
<%@page import="opd.*"%>

<%@page import="java.util.List"%>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/opd/opdJs/opd.js" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script type="text/javascript">
function validateIt()
{
	if (document.getElementsByName("isRefferInOut")[1].checked==true)
	{
		document.getElementsByName("isRefferInOut")[1].value=<%=RegistrationConfig.IS_REFERRED_IN_OUT_REFER_EXTERNAL%>;
		if (document.getElementsByName("referringInstType")[1].checked==true)
		{	
			document.getElementsByName("isAssociated")[0].value=<%=RegistrationConfig.IS_ASSOCIATED_FALSE%>;
			if(isEmpty(document.getElementsByName("patRefHospitalName")[0],'Institute Name') &&
			(isEmpty(document.getElementsByName("remarks")[0],'Remarks')))
				{
				return true;
			}
			else
			{
				return false
			}
		}	
		else if (document.getElementsByName("referringInstType")[0].checked==true)
		{
			document.getElementsByName("isAssociated")[0].value=<%=RegistrationConfig.IS_ASSOCIATED_TRUE%>;
			if(comboValidation(document.getElementsByName("patRefGnctdHospitalCode")[0],'Institute Name') )
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		else
		{
		alert("Please Select Referring Institute Type");
		return false;
		}
	}
	else if (document.getElementsByName("isRefferInOut")[0].checked==true)
	{
		document.getElementsByName("isRefferInOut")[0].value=<%=RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL%>;
		if(document.getElementsByName("choice")[0].checked==true)
		{
			if(comboValidation(document.getElementsByName('departmentCode')[0],'Department'))
			{
				
				return true;
			}
			else
			{
				return false
			}
			
		}
		else if(document.getElementsByName("choice")[1].checked==true)
		{
			if(comboValidation(document.getElementsByName("departmentUnitCode")[0],'Special Clinic'))
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
	}
	else
	{
		alert("Please Select Referring Institute Type");
		return false;
	}
	
	
	return true;
}

function selectDept(obj)
{
	if(obj.value=="0")
	{
		document.getElementById("divSpecialClinicId").style.display="none";
		document.getElementById("divInternalDeptId").style.display="block";
		document.getElementById("divExternalInstId").style.display="none";
		
		
		document.getElementById("deptTableId").style.display="";
		document.getElementById("unitTableId").style.display="none";
		//document.getElementById("departmentField").style.display="";
		//document.getElementById("depatrmentLable").style.display="";
		//document.getElementById("specialUnitField").style.display="none";
		//document.getElementById("specialUnitLable").style.display="none";
	}
	else
	{
		if(obj.value=="1")
		{
			document.getElementById("divSpecialClinicId").style.display="block";
			document.getElementById("divInternalDeptId").style.display="none";
			document.getElementById("divExternalInstId").style.display="none";
			
			document.getElementById("deptTableId").style.display="none";
			document.getElementById("unitTableId").style.display="";
			//document.getElementById("departmentField").style.display="none";
			//document.getElementById("depatrmentLable").style.display="none";
			//document.getElementById("specialUnitField").style.display="";
			//document.getElementById("specialUnitLable").style.display="";
		}
	}
}

function getEpisodeRefDtl(mode,check)
{
	// alert("check value of rfere"+document.getElementsByName("isReferred")[0].value);
	if(check.checked==true)
	{
		//alert("mode "+mode);
		document.getElementsByName("isReferred")[0].value="1";
		submitForm(mode);	
	}
	else
	{
		//alert("in else");
		document.getElementsByName("isReferred")[0].value="0";
		submitForm(mode);
	}	
}

function checkIsReferred(e)
{
	if (e.checked==true)
	{
		//document.getElementById("common").style.display="none";
		document.getElementById("checkReferral").style.display="block";
	}
	else
	{	
		//document.getElementById("common").style.display="block";
		document.getElementById("checkReferral").style.display="none";
		document.getElementById("commonRefer").style.display="none";
		document.getElementById("associated").style.display="none";
		document.getElementById("externalRefer").style.display="none";
		document.getElementById("internalRefer").style.display="none";
		document.getElementsByName("referringInstType")[0].checked=false;
		document.getElementsByName("referringInstType")[1].checked=false;
		document.getElementsByName("isRefferInOut")[0].checked=false;
		document.getElementsByName("isRefferInOut")[1].checked=false;
	}	
}

function showInternal(e)
{
	document.getElementById("divSpecialClinicId").style.display="none";
	document.getElementById("divInternalDeptId").style.display="block";
	document.getElementById("divExternalInstId").style.display="none";
	
	
	
	document.getElementById("internalRefer").style.display="block";
	document.getElementById("commonRefer").style.display="none";
	document.getElementById("commonReferExternal_associated").style.display="none";
	document.getElementById("commonReferExternal_other").style.display="none";
	document.getElementById("externalRefer").style.display="none";
	document.getElementsByName("choice")[0].checked=true;
	document.getElementById("deptTableId").style.display="";
	document.getElementById("unitTableId").style.display="none";
	//document.getElementById("departmentField").style.display="";
	//document.getElementById("depatrmentLable").style.display="";
	//document.getElementById("specialUnitField").style.display="none";
	//document.getElementById("specialUnitLable").style.display="none";
	document.getElementById("RemarkTableId").style.display="none";
	
}

function showExternal(e)
{
	document.getElementById("divSpecialClinicId").style.display="none";
	document.getElementById("divInternalDeptId").style.display="none";
	document.getElementById("divExternalInstId").style.display="block";
	
	document.getElementById("RemarkTableId").style.display="";
	document.getElementById("commonRefer").style.display="none";
	document.getElementById("internalRefer").style.display="none";
	document.getElementById("externalRefer").style.display="block";
	document.getElementsByName("referringInstType")[0].checked=true;
	
	showAssociated(e);
}

function showAssociated(e)
{
	document.getElementById("divSpecialClinicId").style.display="none";
	document.getElementById("divInternalDeptId").style.display="none";
	document.getElementById("divExternalInstId").style.display="block";
	
	document.getElementById("commonRefer").style.display="block";
	document.getElementById("commonReferExternal_associated").style.display="block";		
	document.getElementById("commonReferExternal_other").style.display="none";
	document.getElementById("internalRefer").style.display="none";
}

function showOthers(e)
{
	document.getElementById("divSpecialClinicId").style.display="none";
	document.getElementById("divInternalDeptId").style.display="none";
	document.getElementById("divExternalInstId").style.display="none";
	
	document.getElementById("commonRefer").style.display="block";
	document.getElementById("commonReferExternal_other").style.display="block";
	document.getElementById("commonReferExternal_associated").style.display="none";		
	document.getElementById("internalRefer").style.display="none";
}

function validateAdd()
{
	
		if(document.getElementsByName("choice")[0].checked==true)
		{
			if(comboValidation(document.getElementsByName('departmentCode')[0],'Department') 
				//&& isEmpty(document.getElementsByName("deptRemarks")[0],'Remarks')
				)
			{
				
				return true;
			}
			else
			{
				return false
			}
			
		}
		else if(document.getElementsByName("choice")[1].checked==true)
		{
			if(comboValidation(document.getElementsByName("departmentUnitCode")[0],'Special Clinic')
				//&& isEmpty(document.getElementsByName("unitRemarks")[0],'Remarks')
				)
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
}

function AddRowToTable()
{
	var index=parseInt(document.getElementsByName('numberOfRow')[0].value);
	var elementName
	var tableName
	var remarksElementName
	if(document.getElementsByName('choice')[0].checked==true)
	{
	elementName="departmentCode"
	remarksElementName="deptRemarks"
	tableName="deptTableId"
	}
	else
	{
	elementName="departmentUnitCode"
	remarksElementName="unitRemarks"
	tableName="unitTableId"
	}
	//var resp=document.getElementsByName('comboOptionString')[0].value;
	
	var nRow=0;
	var tableObj=document.getElementById(tableName);
	var numRows=tableObj.rows.length;
	
	if(numRows>2)
	{
		nRow=tableObj.rows[numRows-1].id;
	}
	else
	{
		nRow=numRows;
	}
	
	var tabRow=tableObj.insertRow(numRows);
	tabRow.id=parseInt(nRow)+1;

	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
	var td3=document.createElement("TD");
	
	
	
	var selectObject=document.getElementsByName(elementName)[0]
	var remarksObject=document.getElementsByName(remarksElementName)[0]
	var remarksValue=remarksObject.value
	
	var value=selectObject.value
	var selectedIndex=selectObject.selectedIndex;
	var optionsArray=selectObject.options;
	var label=optionsArray[selectedIndex].text;
	selectObject.remove(selectedIndex);
	remarksObject.value=""
	td1.innerHTML="<div align='center'>"
		+ "<input type='hidden' name='"+elementName+"' value='"+value+"'> "
		+ "<input type='hidden' name='"+elementName+"Label' value='"+label+"'> "
		+ label
		+ "</div>";
		td1.className='tdfont';
		td1.colspan="1";
	
	td2.innerHTML="<div align='center'>"
		+"<input type='hidden' name='"+remarksElementName+"' value='"+remarksValue+"'> "
		+ remarksValue
		+"</div>";
	td2.className='tdfont';
	td2.colspan="1";
	
	

	td3.className='tdfont';
	td3.colspan="1";
	td3.innerHTML="<div align='center'><img src='/HISClinical/hisglobal/images/RoundMinus12x12.png'  onClick='deleteRow(document.getElementById(\""+(parseInt(nRow)+1)+"\"))' style='cursor: pointer'></div>";
   
	tabRow.appendChild(td1);
	tabRow.appendChild(td2);
	tabRow.appendChild(td3);
	document.getElementsByName('numberOfRow')[0].value=numRows;
}

function deleteRow(Str)
{	
	var temp=Str;
	var elementName
	var tableName
	if(document.getElementsByName('choice')[0].checked==true)
	{
	elementName="departmentCode"
	tableName="deptTableId"
	}
	else
	{
	elementName="departmentUnitCode"
	tableName="unitTableId"
	}
	var elementLabel=elementName+"Label"
	var selectObject=document.getElementsByName(elementName)[0]
	//alert("tableName="+tableName)
	var tableObj=document.getElementById(tableName);
	var newOption=document.createElement('option')
	newOption.text=document.getElementsByName(elementLabel)[temp.rowIndex-2].value
	newOption.value=document.getElementsByName(elementName)[temp.rowIndex-1].value
	//alert("newOption"+newOption)
	selectObject.add(newOption,null)
	
	//alert("temp.rowIndex="+temp.rowIndex)
	tableObj.deleteRow(temp.rowIndex);
	var index=parseInt(document.getElementsByName('numberOfRow')[0].value);
	document.getElementsByName('numberOfRow')[0].value=index-1;
	return true;
}


</script>
<% 
	String divisrefdisplay="\"\"";
	String externalReferdisplay="none";
	String commonReferdisplay="none";
	String internalReferdisplay="none";
%>
<his:TransactionContainer>
	<his:TitleTag>
		<his:name>
			<bean:message key="opdReferPatient" />
		</his:name>
	</his:TitleTag>
	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>

	<his:statusTransactionInProcess>
		
		<his:SubTitleTag name="Select Refer Type">
		</his:SubTitleTag>
		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td>
						<div id="checkReferral" style="display: <%= divisrefdisplay %>">
							<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="referInternal" />
							</font>
							<html:radio name="OpdReferPatientFB" property="isRefferInOut" tabindex="1" value="I" onclick="showInternal(this)" />
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="referExternal" />
							</font>
							<html:radio name="OpdReferPatientFB" property="isRefferInOut" tabindex="1" value="E" onclick="showExternal(this)" />
						</div>
					</td>
					<td>
						<div id="externalRefer" style="display: <%= externalReferdisplay %>">
						</div>
					</td>
				</tr>
			</table>
		</his:ContentTag>

		<div id="commonRefer" style="display: <%= commonReferdisplay %>">
			<his:SubTitleTag name="Refer To">
				<bean:message key="gnctd" />
				<html:radio name="OpdReferPatientFB" property="referringInstType" tabindex="1" value="G" onclick="showAssociated(this)" />
				<bean:message key="other" />
				<html:radio name="OpdReferPatientFB" property="referringInstType" value="O" tabindex="1" onclick="showOthers(this)" />
				<html:hidden name="OpdReferPatientFB" property="isAssociated" />
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" cellspacing="1" cellpadding="1">
					<tr>
						<td width="16%" class="tdfonthead" nowrap>
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="referToDept" />
								</font>
							</div>
						</td>
						<td width="17%" class="tdfonthead">
							<div align="center">
								<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="hospital" /> <bean:message key="name" />
								</font>
							</div>
						</td>
					</tr>
					<tr>
						<td width="16%" class="tdfont">
							<div align="center">
								<html:text name="OpdReferPatientFB" property="patRefGnctdHospitalDept" maxlength="20" styleClass="textbox" onblur="isAlpha(this,'From Department')" tabindex="1" onkeypress="return validateAlphabetsOnly(event)" />
							</div>
						</td>
						<td width="17%" class="tdfont">
							<div id="commonReferExternal_associated" style="display: none" align="center">
								<html:select name="OpdReferPatientFB" tabindex="1" property="patRefGnctdHospitalCode" styleClass="regcbo">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL%>">
									<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
							<div id="commonReferExternal_other" style="display: none" align="center">
								<html:text name="OpdReferPatientFB" tabindex="1" property="patRefHospitalName" onkeypress="return validateAlphabetsOnly(event)" maxlength="100" styleClass="textbox" size="20" />
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
		</div>

		<div id="internalRefer" style="display: <%= internalReferdisplay %>">
			<his:SubTitleTag name="Refer To">
				<bean:message key="department" />
				<html:radio name="OpdReferPatientFB" property="choice" value="0" onclick="selectDept(this)"></html:radio>
				<bean:message key="specialClinic" />
				<html:radio name="OpdReferPatientFB" property="choice" value="1" onclick="selectDept(this)"></html:radio>
			</his:SubTitleTag>
			<his:ContentTag>
				<table id="deptTableId" width="100%" cellspacing="1" cellpadding="1" style="display: none;">
					<tr>
						<td id="depatrmentLable" width="40%" class="tdfonthead">
							<div align="center">
								<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="department" />
								</font>
							</div>
						</td>
						<td id="internalRemarksLable" width="39%" class="tdfonthead">
						<div align="center">
							
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="reason" />
							</font>
						</div>
					</td>
					<td  width="1%" class="tdfonthead">
						
					</td>
					</tr>
					<tr>
						<td id="departmentField" width="40%" class="tdfont">
							<div align="center">
								<html:select name="OpdReferPatientFB" property="departmentCode" tabindex="1" styleClass="regCbo">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT%>">
									<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
						
						
					<td  width="39%" class="tdfont">
						<div align="center">
							<input type="textarea" name="deptRemarks"  onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event))" style="height: 30px;" size="40" maxlength="100" tabindex="1"/>
						</div>
					</td>
					<td  width="1%" class="tdfont" >
					<div align="center">
						<img class="button" id="addButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/RoundPlus12x12.png"/>' tabindex='1' alt="Add Refer" title="Add Refer" onkeypress="if(event.keyCode==13)if(validateAdd()) AddRowToTable() ;" onclick="if(validateAdd())AddRowToTable()">
					</div>
				</td>
				</tr>
					
				</table>
				<table id="unitTableId" width="100%" cellspacing="1" cellpadding="1" style="display: none;">
					<tr>
						<td id="specialUnitLable" width="40%" class="tdfonthead">
							<div align="center">
								<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="specialClinic" />
								</font>
							</div>
						</td>
						<td id="internalRemarksLable" width="39%" class="tdfonthead">
						<div align="center">
							
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="reason" />
							</font>
						</div>
					</td>
					<td  width="1%" class="tdfonthead">
						
					</td>
					</tr>
					<tr>
						<td id="specialUnitField" width="40%" class="tdfont">
							<div align="center">
								<html:select name="OpdReferPatientFB" property="departmentUnitCode" tabindex="1" styleClass="regCbo">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_UNIT_WITH_SPECIALITY%>">
									<html:options collection="<%=OpdConfig.OPD_UNIT_WITH_SPECIALITY%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
						
					
					<td  width="39%" class="tdfont">
						<div align="center">
							<input type="textarea" name="unitRemarks"  onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event))" style="height: 30px;" size="40" maxlength="100" tabindex="1"/>
						</div> 
					</td>
					<td  width="1%" class="tdfont" >
					<div align="center">
						<img class="button" id="addButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/RoundPlus12x12.png"/>' tabindex='1' alt="Add Refer" title="Add Refer" onkeypress="if(event.keyCode==13)if(validateAdd()) AddRowToTable() ;" onclick="if(validateAdd())AddRowToTable()">
					</div>
				</td>
				</tr>
					
				</table>
			</his:ContentTag>
		</div>
		<his:ContentTag>
			<table id="RemarkTableId" width="100%" cellspacing="1" cellpadding="1" style="display: none;">
				<tr>
					<td id="remarksLable" width="20%" class="tdfonthead">
						<div align="right">
							
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="reason" />
							</font>
						</div>
					</td>
					<td id="remarksField" width="35%" class="tdfont">
						<div align="left">
							<input type="textarea" name="remarks" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event))" style="height: 30px;" size="40" maxlength="100" tabindex="1"/>
						</div>
					</td>
				</tr>
			</table>
		</his:ContentTag>
	</his:statusTransactionInProcess>

	<his:ButtonToolBarTag>
		<his:statusTransactionInProcess>
			<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex='2' onclick="submitFormOnValidate(validateIt(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateIt(),'SAVE');")>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
		</his:statusTransactionInProcess>
		<his:statusUnsuccessfull>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		</his:statusUnsuccessfull>
	</his:ButtonToolBarTag>
</his:TransactionContainer>
<html:hidden name="OpdReferPatientFB" property="hmode" />
<html:hidden name="OpdReferPatientFB" property="patCrNo" />
<html:hidden name="OpdReferPatientFB" property="episodeVisitNo" />
<html:hidden name="OpdReferPatientFB" property="numberOfRow" />
<html:hidden name="OpdReferPatientFB" property="isPatDead" />

<logic:notEqual name="OpdReferPatientFB" property="isPatDead" value="<%=RegistrationConfig.YES %>">
<div id="divSpecialClinicId">
<%
List specialClinicLst=(List)session.getAttribute(OpdConfig.OPD_UNIT_WITH_SPECIALITY);
if( (specialClinicLst==null) || (specialClinicLst.size()==0))
{
%>
<br>
<b><font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">No Record Special Clinic</font></b>

<%
}
%>
</div>
<div id="divInternalDeptId">
<%
List InternalDepartmenLst=(List)session.getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT);
if( (InternalDepartmenLst==null) || (InternalDepartmenLst.size()==0))
{
%>
<br><b><font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">No Record For Department</font></b>
<%
}
%>
</div>
<div id="divExternalInstId">
<%
List externalInstituteLst=(List)session.getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL);
if( (externalInstituteLst==null) || (externalInstituteLst.size()==0))
{
%>
<br><b><font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">No Record For External Institute</font></b>

<%
}
%>
</div>
</logic:notEqual>