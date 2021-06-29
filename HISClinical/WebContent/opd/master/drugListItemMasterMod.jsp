<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="opd.OpdConfig"%>
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

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>

<script type="text/javascript">
function findIndex(obj)
{
	var ctrlArr = document.getElementsByName(obj.name);
	var index=-1;
	for(var i=0;i<ctrlArr.length;i++)
	{
		if(ctrlArr[i]==obj)
		{
			index=i;
			break;
		}
	}
	return index;
}

// Move Control To Drop Down on Down Arrow Press
function changeControl()
{
	var elemDropDown=document.getElementById("cboDropDown");	
	if(typeof elemDropDown == 'undefined') return;
	
	var innerValue=elemDropDown.options[0].value;
	if(innerValue=="-1")
	{
		document.getElementById("divDropDown").style.display="none";
		popupList = null;
		selectIndexVal = -1;
		return;
	}
	selectIndexVal = -1;
	elemDropDown.focus();
}

// On Key Down of Drop Down
function onKeyDownDrop(eve)
{
	// 9 Tab
	if(eve.keyCode==13 || eve.keyCode==9)
	{
		setValues();
		document.getElementById("divDropDown").style.display="none";
		document.getElementById("cboDropDown").value="";
		elemSelText.focus();
		popupList = null;
		selectIndexVal = -1;
		elemSelText = null;
		selectedRowIndex = null;
	}
	if(eve.keyCode==27)
	{
		document.getElementById("divDropDown").style.display="none";
		elemSelText.focus();
		popupList = null;
		selectIndexVal = -1;
		elemSelText = null;
		selectedRowIndex = null;
	}
}

function onChangeDrop()
{
	var elemDropDown = document.getElementById("cboDropDown");
	selectIndexVal = elemDropDown.selectedIndex;
	elemSelText.value=elemDropDown.options[selectIndexVal].text;
}
// OnClick & Double click of Drop Downs

function setClickedValue()
{
	setValues();
	document.getElementById("divDropDown").style.display="none";
	document.getElementById("cboDropDown").value="";
	elemSelText.focus();
	popupList = null;
	selectIndexVal = -1;
	elemSelText = null;
	selectedRowIndex = null;
}

//getting drug list

function getDrugDropDownData(eve, obj)
{
	// 13 Enter, 27 Escape, 40 Down Arrow
	if(eve.keyCode!=13 && eve.keyCode!=27 && eve.keyCode!=40)
	{
		// Setting Selected Element
		selectIndexVal = null;
		
		elemSelText=obj;
		selectedRowIndex=findIndex(elemSelText);
		
		if(obj.value!=" " && obj.value!="")
			sendDataForDrugList(obj.value);
			
	}
	if(eve.keyCode==40)
		changeControl();
}

function sendDataForDrugList(searchText)
{	
	handleDrugList(searchText);
}

function handleDrugList(searchText)
{
	popupList=null;
	// Getting Data
	var list = sendRequestForDrugList(searchText);

	// Setting Drug Drop Down Position
	var elemDropDown=document.getElementById("cboDropDown");
	document.getElementById("divDropDown").style.display="none";
	var o=elemSelText;
	var l=0,t=0;
	while(o.offsetParent)
	{
		l+=o.offsetLeft;
		t+=o.offsetTop;
		o=o.offsetParent;
	}
	document.getElementById("divDropDown").style.left=l;
	document.getElementById("divDropDown").style.top=t+elemSelText.offsetHeight;
	document.getElementById("divDropDown").style.pixelWidth=elemSelText.style.pixelWidth;
			
	// Filling Data in DD
	var optionValueTextArray=list.split('$');
	elemDropDown.innerHTML="";
	popupList = optionValueTextArray;
	if(optionValueTextArray!="")
	{
		document.getElementById("divDropDown").style.display="block";
		for(i=0,j=1;i<optionValueTextArray.length && j<optionValueTextArray.length;i=i+2,j=j+2)
		{
			var op=document.createElement("option");
			op.value=optionValueTextArray[i];
			op.innerHTML=optionValueTextArray[j];
			elemDropDown.appendChild(op);
		}
		selectIndexVal = 0;
		elemDropDown.selectedIndex = selectIndexVal;
	}
	else
	{
		document.getElementById("divDropDown").style.display="none";				
		var op=document.createElement("option");
		op.value="-1";
		op.innerHTML="Select Value";
		elemDropDown.appendChild(op);
		selectIndexVal = -1;
	}
}

function sendRequestForDrugList(text)
{
	// Getting Drug DD List from "drugList" select object
	var values = document.getElementsByName("drugList")[0].options;
	alert("values "+values.lenght);
	var result="";
	var txtlen = text.length;
	for(var i=0; i<values.length; i++)
	{
		if(values[i].text.substr(0,txtlen).toLowerCase()==text.toLowerCase())
		{
			result+= values[i].value+"$"+values[i].text+"$";
		}
	}	
	if(result.length>0) result = result.substr(0,result.length-1);
	return result;
}

// Setting Value and Calling for Drug Doses
function setValues()
{
	if(selectIndexVal!=null && selectIndexVal!=-1 && selectedRowIndex!=null)
	{
		var elemDrugId = document.getElementsByName("selItemId")[selectedRowIndex];
		var elemDrugItemTypeId = document.getElementsByName("selDrugItemTypeId")[selectedRowIndex];
		//var elemDrugName = document.getElementsByName("selDrugName")[selectedRowIndex];
		
		var elemDropDown = document.getElementById("cboDropDown");
		selectIndexVal = elemDropDown.selectedIndex;
		elemDrugId.value=elemDropDown.value.split("#")[0];
		elemDrugItemTypeId.value=elemDropDown.value.split("#")[1];
		elemDrugName.value=elemDropDown.options[selectIndexVal].text;
		// Drug Dose
		selectedDoseRowIndex = selectedRowIndex;
		//sendDataForDrugRouteList(elemDrugItemTypeId.value);	
		//sendDataForDrugDoseList(elemDrugItemTypeId.value,elemDrugId.value);	
	}
}

function validateAddForm(mode)
{
	if(document.getElementsByName("drugListNameId")[0].value=="")
	{
		alert("Please Enter Drug List Name");
		document.getElementsByName("drugListNameId")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("length")[0].value=="0")
	{
		alert("Please Add atleast One Drug");
		document.getElementsByName("itemId")[0].focus();
		return false;
	}	
	
	submitForm21(mode);
}

function clearForm()
{
	submitForm21('ADD');
}

function getDoseData()
{
	if(document.getElementsByName("itemId")[0].value!="")
	{
		submitForm21('GETDOSEDATA');	
	}
	
}

function addRow()
{
	if(document.getElementsByName("itemId")[0].value=="")
	{
		alert("Please Select Drug Name");
		document.getElementsByName("itemId")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("doseId")[0].value=="")
	{
		alert("Please Select Dosage");
		document.getElementsByName("doseId")[0].focus();
		return false;
	}
	if(document.getElementsByName("frequencyId")[0].value=="")
	{
		alert("Please Select Frequency");
		document.getElementsByName("frequencyId")[0].focus();
		return false;
	}
	if(document.getElementsByName("days")[0].value=="")
	{
		alert("Please Enter Days");
		document.getElementsByName("days")[0].focus();
		return false;
	}
	
	submitForm21('ADDROW');
}

function deleteDrugRow(mode,index)
{
	document.getElementsByName("deleteIndex")[0].value=index;
	submitForm21(mode);
}

</script>
<title>Drug List Item Master</title>

<body>
	<html:form action="/master/drugListItemMaster">
		<his:TransactionContainer>
			<his:TitleTag name="Drug List Item Master ">
				</his:TitleTag>
			
			
				<logic:notEqual name="DrugListItemMasterFB" property="hmode" value="VIEW">
				<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="drugListName"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<bean:write name="DrugListItemMasterFB" property="drugListName"/>
							</div>
						</td>
					</tr>
				</table>
				</his:ContentTag>
				<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="20%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="drugname"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="dosage"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="frequency"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<font color="#FF0000">*</font>
										<bean:message key="dosedays" />
									</b>
								</font>
							</div>
						</td>
						<td class="tdfonthead" width="15%">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="emptyStomach"/>
									</b>
								</font>	
							</div>
						</td>
						<td width="15%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="instructions" />
									</b>
								</font>
							</div>
						</td>
						<td class="tdfonthead" width="5%">
						</td>
					</tr>
				
					<tr>
						<td width="10%" class="tdfont">
							<div align="center">
								<html:select name="DrugListItemMasterFB" property="itemId" tabindex="1" onchange="getDoseData()" style="width:100px">
									<html:option value="">Select</html:option>
									<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_ALL_DRUGS%>" >
									<html:options collection="<%=OpdConfig.ESSENTIALS_LIST_ALL_DRUGS%>" property="value" labelProperty="label"/>
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
								<html:select name="DrugListItemMasterFB" property="doseId" tabindex="1">
									<html:option value="">Select</html:option>
									<logic:present name="<%=OpdConfig.DRUG_DOSE_LIST_FOR_PARTICULAR_ITEMTYPE%>" >
									<html:options collection="<%=OpdConfig.DRUG_DOSE_LIST_FOR_PARTICULAR_ITEMTYPE%>" property="value" labelProperty="label"/>
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
								<html:select name="DrugListItemMasterFB" property="frequencyId" tabindex="1">
									<html:option value="">Select</html:option>
									<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" >
									<html:options collection="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" property="value" labelProperty="label"/>
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="7%" class="tdfont">
							<div align="center">
								<html:text name="DrugListItemMasterFB" property="days" maxlength="2" size="3" tabindex="1" 
									onkeypress="return validateNumberWithoutZeroOnly(event)" ></html:text>
							</div>
						</td>
						<td width="7%" class="tdfont">
							<div align="center">
								<html:checkbox property="isEmptyStomatch" name="DrugListItemMasterFB"></html:checkbox>
							</div>
						</td>
						<td width="7%" class="tdfont">
							<div align="center">
								<html:text name="DrugListItemMasterFB" property="remark" maxlength="500" size="15" tabindex="1" 
									onkeypress="return validateNumberWithoutZeroOnly(event)" ></html:text>
							</div>
						</td>
						<td width="5%" class="tdfont" id="tdButtonPlus">
							<img class="button" id="addButton" style="cursor: pointer" src='<his:path src="/../HIS/hisglobal/images/Pl_Green_Sqr.png"/>' alt="Add Drug" title="Add Drug" onkeypress="if(event.keyCode==13) addRow('ADDDRUGROW');" onclick="addRow('ADDDRUGROW');">
						</td>
					</tr>
					<logic:present name="<%=OpdConfig.SELECTED_DRUG_LIST%>">
					<logic:iterate id="drugDtlVO" indexId="i" name="<%=OpdConfig.SELECTED_DRUG_LIST%>" type="hisglobal.vo.DrugListItemMstVO">
						<tr>
							<td width="7%" class="tdfont">
								<div align="center">
									<bean:write name="drugDtlVO" property="drugName"/>
								</div>
							</td>
							<td width="7%" class="tdfont">
								<div align="center">
									<bean:write name="drugDtlVO" property="doseName"/>
								</div>
							</td>
							<td width="7%" class="tdfont">
								<div align="center">
									<bean:write name="drugDtlVO" property="frequencyName"/>
								</div>
							</td>
							<td width="7%" class="tdfont">
								<div align="center">
									<bean:write name="drugDtlVO" property="days"/>
								</div>
							</td>
							<td width="7%" class="tdfont">
								<div align="center">
									<bean:write name="drugDtlVO" property="isEmptyStomachName"/>
								</div>
							</td>
							<td width="7%" class="tdfont">
								<div align="center">
									<bean:write name="drugDtlVO" property="remarks"/>
								</div>
							</td>
							<td width="5%" class="tdfont" id="tdButtonPlus">
								<img class="button" id="deleteButton" style="cursor: pointer" src='<his:path src="/../HIS/hisglobal/images/minus.gif"/>' alt="Remove Drug" title="Remove Drug" onkeypress="if(event.keyCode==13) deleteDrugRow('DELETEDRUGROW',<%=i %>)" onclick="deleteDrugRow('DELETEDRUGROW',<%=i %>)">
							</td>
						</tr>
					</logic:iterate>
					</logic:present>
				</table>
				</his:ContentTag>
				</logic:notEqual>
				
				<logic:equal name="DrugListItemMasterFB" property="hmode" value="VIEW">
				<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="drugListName"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<bean:write name="DrugListItemMasterFB" property="drugListName"/>
							</div>
						</td>
					</tr>
				</table>
				</his:ContentTag>
				<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="20%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="drugname"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="dosage"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="frequency"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="dosedays" />
									</b>
								</font>
							</div>
						</td>
						<td class="tdfonthead" width="15%">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="emptyStomach"/>
									</b>
								</font>	
							</div>
						</td>
						<td width="15%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="instructions" />
									</b>
								</font>
							</div>
						</td>
						
					</tr>
				
					
					<logic:present name="<%=OpdConfig.SELECTED_DRUG_LIST%>">
					<logic:iterate id="drugDtlVO" indexId="i" name="<%=OpdConfig.SELECTED_DRUG_LIST%>" type="hisglobal.vo.DrugListItemMstVO">
						<tr>
							<td width="7%" class="tdfont">
								<div align="center">
									<bean:write name="drugDtlVO" property="drugName"/>
								</div>
							</td>
							<td width="7%" class="tdfont">
								<div align="center">
									<bean:write name="drugDtlVO" property="doseName"/>
								</div>
							</td>
							<td width="7%" class="tdfont">
								<div align="center">
									<bean:write name="drugDtlVO" property="frequencyName"/>
								</div>
							</td>
							<td width="7%" class="tdfont">
								<div align="center">
									<bean:write name="drugDtlVO" property="days"/>
								</div>
							</td>
							<td width="7%" class="tdfont">
								<div align="center">
									<bean:write name="drugDtlVO" property="isEmptyStomachName"/>
								</div>
							</td>
							<td width="7%" class="tdfont">
								<div align="center">
									<bean:write name="drugDtlVO" property="remarks"/>
								</div>
							</td>
							
						</tr>
					</logic:iterate>
					</logic:present>
				</table>
				</his:ContentTag>
				</logic:equal>
			
		
			
			
			<his:ButtonToolBarTag>
			<logic:notEqual value="VIEW" property="hmode" name="DrugListItemMasterFB">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateAddForm('MODIFYSAVE')" onkeypress="if(event.keyCode==13) validateAddForm('MODIFYSAVE')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitForm21('CANCEL')" onkeypress="if(event.keyCode==13) submitForm21('CANCEL')">
				
			</logic:notEqual>
			<logic:equal value="VIEW" property="hmode" name="DrugListItemMasterFB">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitForm21('CANCEL')" onkeypress="if(event.keyCode==13) submitForm21('CANCEL')">
			</logic:equal>
			</his:ButtonToolBarTag>
			
			
			
			
			<html:hidden name="DrugListItemMasterFB" property="hmode"/>
			<html:hidden name="DrugListItemMasterFB" property="deleteIndex"/>
			<html:hidden name="DrugListItemMasterFB" property="drugListNameId"/>
			<html:hidden name="DrugListItemMasterFB" property="statusFlag"/>
			<html:hidden name="DrugListItemMasterFB" property="drugListName"/>
			<html:hidden name="DrugListItemMasterFB" property="length"/>
		</his:TransactionContainer>
		<div align="center">
			<center><b><his:status/></b></center>
		</div>
		
	</html:form>
</body>
</html>			