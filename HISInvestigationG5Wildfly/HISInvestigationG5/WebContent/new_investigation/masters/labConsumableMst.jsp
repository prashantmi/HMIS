<!-- 
 /****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	LOCAL LAB MASTER
 ## Name of Developer		 			:	Jatin Kumar	
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	
 ## Purpose								:	This process is used for mapping of lab test wise consumable items.  
 ## Date of Creation					: 	27-Mar-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/**************************************************************************************************************/ 
-->


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="new_investigation.InvestigationConfig"%>
<html>
<head>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/new_investigation/js/FilmMst.js" />
<script>
function submitForm(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
	
}
function finalSubmit(mode)
{
	if(validateForm()){
		
	submitForm(mode);
	}
}
function validateForm()
{
	if(document.getElementsByName("itemName")[0].value=="")
		{
			document.getElementsByName("itemName")[0].focus();
			alert('Please Enter Item Name');
			return false;
		}
	if(document.getElementsByName("itemType")[0].value==-1)
		{
		document.getElementsByName("itemType")[0].focus();
		alert("Please Select Item Type");
		return false;
		}
	if(document.getElementsByName("defaultQuantity")[0].value!=""){
				
		if(document.getElementsByName("uomCode")[0].value==-1)
			{
				alert("Please Select Unit");
				document.getElementsByName("uomCode")[0].focus();
				return false;
			}
	}
	/* if(document.getElementsByName("itemIdFromStore")[0].value==-1)
		{
			alert("Please Select Item ID From Store");
			document.getElementsByName("itemIdFromStore")[0].focus();
			return false;
		} */
	return true;
}

</script>
</head>
<body>
<html:form action="/masters/labConsumableMstACT">
<his:TransactionContainer>
	<his:TitleTag name="Lab Consumable Master">
				
			</his:TitleTag>
<table width="100%" border="0" cellspacing="1" cellpadding="0">

					

					<tr>
						<%-- <td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="itemId"/>
									
								</font>
							</div>
						</td> --%>
						<%-- <td width="70%" class="tdfont">
								<div align="left">
								<html:text property="otherItemID" name="LabConsumableMstFB"></html:text>
								</div>
						</td> --%>
						</tr>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="itemName"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:text onkeypress="return validateAlphabetsOnly(event,this)" property="itemName" name="LabConsumableMstFB"></html:text>
								</div>
						</td>
						</tr>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								 <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="itemType"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:select name="LabConsumableMstFB" style="width:29%"
										property="itemType" tabindex="1" >
										<html:option value="-1">Select Value</html:option>
										<logic:notEmpty name="<%=InvestigationConfig.ITEMTYPE_COMBO_HIVT_ITEMTYPE_MST %>">
										<html:options
											collection="<%=InvestigationConfig.ITEMTYPE_COMBO_HIVT_ITEMTYPE_MST %>"
											property="value" labelProperty="label" />
											</logic:notEmpty>
									</html:select>
								
								</div>
						</td>
						</tr>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="defaultQuantity"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:text property="defaultQuantity" maxlength="1" onkeypress="return validateNumeric(event)" name="LabConsumableMstFB"></html:text>
								
								<logic:present
								name="<%=InvestigationConfig.LIST_UOM_COMBO%>">
								

									<html:select name="LabConsumableMstFB" property="uomCode" style="width : 20%"
										tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_UOM_COMBO%>"
											property="value" labelProperty="label" />
									</html:select>
								
							</logic:present>
							</div>
						</td>
						</tr>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="storeItemName"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:select name="LabConsumableMstFB" style="width:29%"
										property="itemIdFromStore" tabindex="1" >
										<html:option value="-1">Select Value</html:option>
										<logic:notEmpty name="<%=InvestigationConfig.ITEMNAME_COMBO_HSTT_ITEM_MST %>">
										<html:options
											collection="<%=InvestigationConfig.ITEMNAME_COMBO_HSTT_ITEM_MST %>"
											property="value" labelProperty="label" />
											</logic:notEmpty>
									</html:select>
									
								<%-- <html:text property="itemIdFromStore" maxlength='8' onkeypress="return validateNumeric(event)" name="LabConsumableMstFB"></html:text> --%>
								</div>
						</td>
						</tr>
						</table>
						
<his:ButtonToolBarTag>
				<span id="saveDiv"> <logic:notEqual name="LabConsumableMstFB"
						property="hmode" value="MODIFY">
						<logic:notEqual name="LabConsumableMstFB" property="hmode" value="VIEW">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-sv.png"/>'
								style="cursor: pointer"
								onkeypress="if(event.keyCode==13) finalSubmit('SAVE')"
								onclick="finalSubmit('SAVE')" tabindex="1">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-clr.png"/>'
								style="cursor: pointer" onclick="submitForm('NEW')"
								onkeypress="if(event.keyCode==13) submitForm('NEW')" tabindex="1">
						</logic:notEqual>
					</logic:notEqual> <logic:equal name="LabConsumableMstFB" property="hmode" value="MODIFY">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-mo.png"/>'
							style="cursor: pointer"
							onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')"
							onclick="finalSubmit('MODIFYSAVE')" tabindex="1">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-clr.png"/>'
							style="cursor: pointer" onclick="submitForm('CLEAR')"
							onkeypress="if(event.keyCode==13) submitForm('CLEAR')" tabindex="1">
					</logic:equal> <img class="button"
					src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
					style="cursor: pointer" onclick="submitForm('CANCEL');"
					onkeypress="if(event.keyCode==13) submitForm('CANCEL');"
					tabindex="1">
				</span>
			</his:ButtonToolBarTag>

</his:TransactionContainer>
<his:status/>
<html:hidden property="hmode" name='LabConsumableMstFB'/>
<html:hidden property="selectedChk" name='LabConsumableMstFB'/>
</html:form>
</body>
</html>