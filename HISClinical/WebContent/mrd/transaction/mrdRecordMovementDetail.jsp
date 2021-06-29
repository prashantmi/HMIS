<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ page import ="java.util.*,registration.*,mrd.*,hisglobal.presentation.*" %> 
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="mrd.MrdConfig"%>
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
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
<%int move_Destroy_Status=0; %>
window.onload=function()
{
	showSelectionRecord();
	checkedSelectedValue();
	document.getElementsByName("movementOptionFlag")[0].checked="true";
	showToLocation();
}

function checkedSelectedValue()
{
	var str=document.getElementsByName("tempChkValue")[0].value;
	var arr=str.split("@");
	var chks=document.getElementsByName('selectedMrdRecord');
	for(var i=0;i<arr.length;i++)
	{
		for(var j=0;j<chks.length;j++)
		{
			if(chks[j].value==arr[i])
			{
				chks[j].checked=true;
			}
		}
	}
}

function pageSubmission(val,hmode)
{
	submitPage(hmode)
}
function submitPage(mode)
{
	elmt=document.getElementsByName("hmode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function showSelectionRecord()
{
	if(typeof document.getElementsByName("recordSelectionFlag")[0] != 'undefined')
	{
		if(document.getElementsByName("recordSelectionFlag")[0].checked)
			document.getElementById("divSelectedId").style.display="none";
		else	
			document.getElementById("divSelectedId").style.display="block";
	}	
}

function validateSave()
{
	if(	//comboValidation(document.forms[0].recordType,"Record Type") &&
		validateFrom() 
		&& validateRecordSelection()
		&& (destroy() || validateTo())
		&& comboValidation(document.forms[0].putBy,"Done By")
		&& validateDuplicate()
		&& validateReason())
		{
		
		submitPage('SAVE');
		}
}

function validateFrom()
{
	var valid=true;
	if(validateFromMrd()
		&& comboValidation(document.forms[0].fromRackId,"From Rack")
		&& comboValidation(document.forms[0].fromShelfId,"From Shelf"))
	{
		valid=true;	
	}
	else
		valid=false;
	
	return valid;	
}

function validateTo()
{
	var valid=true;
	if(validateToMrd()
		&& comboValidation(document.forms[0].toRackId,"To Rack")
		&& comboValidation(document.forms[0].toShelfId,"To Shelf"))
	{
		valid=true;	
	}
	else
		valid=false;
	
	return valid;	
} 

function validateRecordSelection()
{
	var valid=true;
	if(document.getElementsByName("recordExistFlag")[0].value==<%=MrdConfig.YES %>)
	{
		if(document.getElementsByName("recordSelectionFlag")[0].checked)
			valid=true;
		else
			valid=validateSelectedRecord();
	}
	else
	{
		alert("No Record Found To Move");
		valid=false;
	}
	return valid;		
}

function validateSelectedRecord()
{
	var valid=true;
	var count=0;
	for(var i=0;i<document.getElementsByName("selectedMrdRecord").length;i++)
	{
		if(document.getElementsByName("selectedMrdRecord")[i].checked)
			count++
	}
	
	if(count==0)
	{
		alert("Please Select At Least a Record To Move");
		valid=false;
	}
	else
		valid=true;
	
	return valid;	
}

function validateFromMrd()
{
	var valid=true;
	if(document.getElementsByName("isMrdListOne")[0].value==<%=MrdConfig.YES%>)
		valid=true;
	else
	{
		if(comboValidation(document.forms[0].fromMrdCode,"From MRD"))
			valid=true;
		else
			valid=false;	
	}	
	
	return valid;
}

function validateToMrd()
{
	var valid=true;
	if(document.getElementsByName("isMrdListOne")[0].value==<%=MrdConfig.YES%>)
		valid=true;
	else
	{
		if(comboValidation(document.forms[0].toMrdCode,"To MRD"))
			valid=true;
		else
			valid=false;	
	}	
	
	return valid;
}

function validateDuplicate()
{
	var valid=true;
	var fromLoc=document.getElementsByName("fromMrdCode")[0].value+document.getElementsByName("fromRackId")[0].value+document.getElementsByName("fromShelfId")[0].value;
	var toLoc=document.getElementsByName("toMrdCode")[0].value+document.getElementsByName("toRackId")[0].value+document.getElementsByName("toShelfId")[0].value;
	if(fromLoc==toLoc)
	{
		alert("To Location Cannot Be Same As From Location.\n"+"Please Select Different To Location");
		document.getElementsByName("toShelfId")[0].focus();
		valid=false;
	}
	else
	{
		valid=true;
	}
	return valid;
}

function hideToLocation()
{
	document.getElementById("toLocationId").style.display = 'none';	
	document.getElementById("movedId").style.display="none";	
	document.getElementById("destroyedId").style.display="block";
	document.getElementById("destroyableData").style.display="";	
	document.getElementById("moveableData").style.display="none";
	if ((document.getElementsByName("status")[0].value)==0)
	{
		document.getElementById("destroyableDataStatus").style.display="";
		document.getElementById("divSelectedIdTag").style.display="none";	
	}
}

function showToLocation()
{
	<% move_Destroy_Status=0; %>
	document.getElementById("moveableData").style.display="";	
	document.getElementById("destroyableData").style.display="none";
	document.getElementById("destroyableDataStatus").style.display="none";	
	document.getElementById("divSelectedIdTag").style.display="";	
	if(document.getElementsByName("movementOptionFlag")[0].checked)
		{
			document.getElementById("toLocationId").style.display="block";
			document.getElementById("movedId").style.display="block";
		}
		else	
		{
			document.getElementById("toLocationId").style.display="none";			
			document.getElementById("movedId").style.display="none";	
			document.getElementById("destroyedId").style.display="block";	
		}
}
function destroy()
{
	if(document.getElementsByName("movementOptionFlag")[0].checked)
		{
			return false;
		}
		else
		{
			return true;
		}
}
function clearForm()
{
	//alert("Inside Clear");
	//document.getElementsByName("recordType")[0].value="-1";
	document.getElementsByName("fromRackId")[0].value="-1";
	document.getElementsByName("fromShelfId")[0].value="-1";
	document.getElementsByName("fromMrdCode")[0].value="-1";
	document.getElementsByName("toRackId")[0].value="-1";
	document.getElementsByName("toShelfId")[0].value="-1";
	document.getElementsByName("toMrdCode")[0].value="-1";
	document.getElementsByName("putBy")[0].value="-1";
	document.getElementsByName("remarks")[0].value="";
	
	
}
function validateReason()
{
var returnValue= true;
if((!document.getElementsByName("movementOptionFlag")[0].checked))
	{			
		var returnValue;
		if(document.getElementsByName("remarks")[0].value==null||document.getElementsByName("remarks")[0].value=="")
			{
			alert("Please give a reason for destroy !");				
			returnValue=false;
			}
		else
		returnValue= true;
	}
	return returnValue;
}
function CheckDate(date, index)
{
	if(!document.getElementsByName("movementOptionFlag")[0].checked)
		{
			if((date.split(' ')[1]=="years") || (date.split(' ')[1]=="year"))
			{
				var recordTime =date.split(' ')[0];
				if(recordTime < 4)				
				{
					alert("Please select record of 4 year before"+"  index :"+index);
					document.getElementsByName("selectedMrdRecord")[index].checked = false;
					return false;
				}
			}
			else
			{
					alert("Please select record of 4 year before"+"  index :"+index);
					document.getElementsByName("selectedMrdRecord").checked = false;
					return false;
			}
		}	
}


</script>
	<body>
		<html:form action="/mrdRecordMovementDetail">
			<his:TransactionContainer>
			<his:TitleTag name="MRD Record Movement In MRD">
			</his:TitleTag>
			
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead" >
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000" size="1">*</font>
									<bean:message key="recordType"/>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont" >
							<div align="left">
								<html:select name="MrdRecordMovementDetailFB" property="recordType" onchange="pageSubmission(this,'GETMRD');" tabindex="1">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.RECORD_TYPE%>">
										<html:options collection="<%=MrdConfig.RECORD_TYPE%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			<his:statusTransactionInProcess>
				<his:SubTitleTag name="From Location">
				
				<logic:equal name="MrdRecordMovementDetailFB" property="recordType" value="14" >				
				<div style="display: none;">
					<html:radio name="MrdRecordMovementDetailFB" property="movementOptionFlag" value="<%=MrdConfig.MOVEMENT_SELECTED %>" onclick="showToLocation()" tabindex="1"></html:radio>Movement
					<html:radio name="MrdRecordMovementDetailFB" property="movementOptionFlag" value="<%=MrdConfig.DESTROY_SELECTED %>" onclick="hideToLocation()" tabindex="1"></html:radio>Destroy
				</div>
				</logic:equal>
				<logic:notEqual name="MrdRecordMovementDetailFB" property="recordType" value="14">
				<div>
					<html:radio name="MrdRecordMovementDetailFB" property="movementOptionFlag" value="<%=MrdConfig.MOVEMENT_SELECTED %>" onclick="showToLocation()" tabindex="1"></html:radio>Movement
					<html:radio name="MrdRecordMovementDetailFB" property="movementOptionFlag" value="<%=MrdConfig.DESTROY_SELECTED %>" onclick="hideToLocation()" tabindex="1"></html:radio>Destroy
				</div>
				</logic:notEqual>				
				
				</his:SubTitleTag>
				<his:ContentTag>
					<logic:equal name="MrdRecordMovementDetailFB" value="<%=MrdConfig.NO %>" property="isMrdListOne">
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000" size="2">*</font>
											<bean:message key="mrd"/>
										</font>
									</div>
								</td>
								<td width="75%" class="tdfont" >
									<div align="left">
										<html:select name="MrdRecordMovementDetailFB" property="fromMrdCode" onchange="pageSubmission(this,'FROMRACK')" tabindex="1">
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MrdConfig.LIST_MRD_BASED_ON_RECORD_TYPE%>">
												<html:options collection="<%=MrdConfig.LIST_MRD_BASED_ON_RECORD_TYPE%>" property="value" labelProperty="label" />
											</logic:present>
										</html:select>
									</div>
								</td>
							</tr>
						</table>	
					</logic:equal>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000" size="2">*</font>
										<bean:message key="rack"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" >
								<div align="left">
									<html:select name="MrdRecordMovementDetailFB" property="fromRackId" onchange="pageSubmission(this,'FROMSHELF')" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.FROM_RACK_LIST_BASED_ON_MRD%>">
											<html:options collection="<%=MrdConfig.FROM_RACK_LIST_BASED_ON_MRD%>" property = "rackId" labelProperty = "rackName" />
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000" size="2">*</font>
										<bean:message key="shelf"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" >
								<div align="left">
									<html:select name="MrdRecordMovementDetailFB" property="fromShelfId" onchange="pageSubmission(this,'GETRECORD')" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.FROM_SHELF_LIST_BASED_ON_RACK%>">
											<html:options collection="<%=MrdConfig.FROM_SHELF_LIST_BASED_ON_RACK%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>
				
				<logic:notEqual name="MrdRecordMovementDetailFB" property="fromShelfId" value="-1">
					<logic:present name="<%=MrdConfig.ARR_MRD_RECORD_TO_BE_MOVED %>">
						<div id="divSelectedIdTag">
							<his:SubTitleTag name="Selected Record" >
								<div>
									<html:radio name="MrdRecordMovementDetailFB" property="recordSelectionFlag" value="<%=MrdConfig.RECORD_SELECTION_ALL %>" onclick="showSelectionRecord()" tabindex="1"></html:radio>All
									<html:radio name="MrdRecordMovementDetailFB" property="recordSelectionFlag" value="<%=MrdConfig.RECORD_SELECTION_SELECTED %>" onclick="showSelectionRecord()" tabindex="1"></html:radio>Selected
								</div>
							</his:SubTitleTag>
						</div>
						<div id="divSelectedId">
							<his:ContentTag>
								<table width="100%" border="0"  cellspacing="1" cellpadding="0">
									<tr>
										<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="select"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="recordId"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="35%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="name"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="crNo"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="gender/age"/>
													</b>		
												</font>
											</div>
										</td>
									</tr>									
									<%int index=0; %>
									<tr> <td id="moveableData" colspan="5"> <table  width="100%">
									<logic:iterate id="recordToBeMoved" name="<%=MrdConfig.ARR_MRD_RECORD_TO_BE_MOVED %>" type="hisglobal.vo.MrdRecordDtlVO" indexId="idx">
										<tr>										
											<td width="5%" class="tdfontheadExam" >
												<div align="center">
												<% Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);%>
												<%String str="CheckDate('"+recordToBeMoved.getRecordEntryDate()+" ', '"+(index++)+"')";%>
													<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
														<html:checkbox name="MrdRecordMovementDetailFB" property="selectedMrdRecord" value="<%=recordToBeMoved.getMrdRecordId() %>" onchange="<%=str%>" tabindex="1"></html:checkbox>
														<html:hidden name="MrdRecordMovementDetailFB" property="recordEntryDate" value="<%=recordToBeMoved.getRecordEntryDate() %>"/>
													</font>
												</div>
											</td>
											<td width="20%" class="tdfontheadExam" >
												<div align="center">
													<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
														<%=recordToBeMoved.getRecordDesc() %>
													</font>
												</div>
											</td>
											<td width="35%" class="tdfontheadExam" >
												<div align="center">
													<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
														<%=recordToBeMoved.getPatName() %>
													</font>
												</div>
											</td>
											<td width="20%" class="tdfontheadExam" >
												<div align="center">
													<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
														<%=recordToBeMoved.getPatCrNo() %>
													</font>
												</div>
											</td>
											<td width="20%" class="tdfontheadExam" >
												<div align="center">
													<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
														<%=recordToBeMoved.getPatGender() %>/
														<%=recordToBeMoved.getPatAge() %>
													</font>
												</div>
											</td>
										</tr>
									</logic:iterate>
									</table>
									</td></tr>
									<tr> <td id="destroyableData" colspan="5"  style="display: none;"> <table  width="100%">
									<logic:iterate id="recordToBeMoved" name="<%=MrdConfig.ARR_MRD_RECORD_TO_BE_MOVED %>" type="hisglobal.vo.MrdRecordDtlVO" indexId="idx">
										
										<% String entryDate= recordToBeMoved.getRecordEntryDate();
										String [] date= entryDate.split(" ");
										System.out.print("::::::::::::::::::::::::::::::::::::::::::::DATE"+entryDate.toString());
										if((date[1].contains("year")) && (Integer.parseInt(date[0])>=4))
										{	move_Destroy_Status++;										
											System.out.print("::::::::::::::::::::::::::::::::::::::::::::DATE jkjkjk"+date[0]+move_Destroy_Status);															
										%>
										<tr>										
											<td width="5%" class="tdfontheadExam" >
												<div align="center">												
													<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
														<html:checkbox name="MrdRecordMovementDetailFB" property="selectedMrdRecord" value="<%=recordToBeMoved.getMrdRecordId() %>"  tabindex="1"></html:checkbox>
														<html:hidden name="MrdRecordMovementDetailFB" property="recordEntryDate" value="<%=recordToBeMoved.getRecordEntryDate() %>"/>
													</font>
												</div>
											</td>
											<td width="20%" class="tdfontheadExam" >
												<div align="center">
													<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
														<%=recordToBeMoved.getRecordDesc() %>
													</font>
												</div>
											</td>
											<td width="35%" class="tdfontheadExam" >
												<div align="center">
													<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
														<%=recordToBeMoved.getPatName() %>
													</font>
												</div>
											</td>
											<td width="20%" class="tdfontheadExam" >
												<div align="center">
													<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
														<%=recordToBeMoved.getPatCrNo() %>
													</font>
												</div>
											</td>
											<td width="20%" class="tdfontheadExam" >
												<div align="center">
													<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
														<%=recordToBeMoved.getPatGender() %>/
														<%=recordToBeMoved.getPatAge() %>
													</font>
												</div>
											</td>
										</tr>
										<%} %>
									</logic:iterate>
									</table></td></tr>
								</table>
							</his:ContentTag>
						</div>
						<div id="destroyableDataStatus" style="display: none;">						
						<his:SubTitleTag name="Selected Record">
						</his:SubTitleTag>
						<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="100%" class="tdfonthead" >
									<div align="center">
										<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												No Record Found To Destroy
											</b>	
										</font>
									</div>	
								</td>
							</tr>	
						</table>
						</his:ContentTag>
						</div>
					</logic:present>
					
					<logic:notPresent name="<%=MrdConfig.ARR_MRD_RECORD_TO_BE_MOVED %>">
						<his:SubTitleTag name="Selected Record">
						</his:SubTitleTag>
						<his:ContentTag>
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr>
									<td width="100%" class="tdfonthead" >
										<div align="center">
											<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													No Record Found To Move
												</b>	
											</font>
										</div>	
									</td>
								</tr>
							</table>		
						</his:ContentTag>
					</logic:notPresent>
				</logic:notEqual>
			
			<div id="toLocationId">
				<his:SubTitleTag name="To Location">
				</his:SubTitleTag>
				<his:ContentTag>
					<logic:equal name="MrdRecordMovementDetailFB" value="<%=MrdConfig.NO %>" property="isMrdListOne">
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000" size="2">*</font>
											<bean:message key="mrd"/>
										</font>
									</div>
								</td>
								<td width="75%" class="tdfont" >
									<div align="left">
										<html:select name="MrdRecordMovementDetailFB" property="toMrdCode" onchange="pageSubmission(this,'TORACK')" tabindex="1">
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MrdConfig.LIST_MRD_BASED_ON_RECORD_TYPE%>">
												<html:options collection="<%=MrdConfig.LIST_MRD_BASED_ON_RECORD_TYPE%>" property="value" labelProperty="label" />
											</logic:present>
										</html:select>
									</div>
								</td>
							</tr>
						</table>	
					</logic:equal>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000" size="2">*</font>
										<bean:message key="rack"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" >
								<div align="left">
									<html:select name="MrdRecordMovementDetailFB" property="toRackId" onchange="pageSubmission(this,'TOSHELF')" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.TO_RACK_LIST_BASED_ON_MRD%>">
											<html:options collection="<%=MrdConfig.TO_RACK_LIST_BASED_ON_MRD%>" property = "rackId" labelProperty = "rackName" />
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000" size="2">*</font>
										<bean:message key="shelf"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" >
								<div align="left">
									<html:select name="MrdRecordMovementDetailFB" property="toShelfId" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.TO_SHELF_LIST_BASED_ON_RACK%>">
											<html:options collection="<%=MrdConfig.TO_SHELF_LIST_BASED_ON_RACK%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>
			</div>
			
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000" size="2">*</font>
										<bean:message key="doneBy"/>
									</font>
								</div>
							</td>
							<td width="75%" class="tdfont" >
								<div align="left">
									<html:select name="MrdRecordMovementDetailFB" property="putBy" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.LIST_RECORD_PUT_BY_IN_MRD%>">
											<html:options collection="<%=MrdConfig.LIST_RECORD_PUT_BY_IN_MRD%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right" id="movedId" style="display: none">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="remarks"/>
									</font>
								</div>
								<div align="right" id="destroyedId" style="display: none">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="reason"/>
									</font>
								</div>
							</td>
							<td width="75%" class="tdfont" >
								<div align="left">
									<html:textarea name="MrdRecordMovementDetailFB" property="remarks" rows="2" cols="70" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event))" tabindex="1"></html:textarea>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>
			</his:statusTransactionInProcess>
			
			<his:ButtonToolBarTag>
				<his:statusNew>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
				</his:statusNew>
				<his:statusTransactionInProcess>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick ="validateSave()" onkeypress="if(event.keyCode==13) validateSave()">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm21('NEW');" onkeypress="if(event.keyCode==13)submitForm21('CANCEL')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick =" clearForm();" onkeypress="if(event.keyCode==13)clearForm();">
				</his:statusTransactionInProcess>	
				<his:statusUnsuccessfull>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm21('NEW');" onkeypress="if(event.keyCode==13)submitForm21('CANCEL')">
				</his:statusUnsuccessfull>
			</his:ButtonToolBarTag>
			
			<html:hidden name="MrdRecordMovementDetailFB" property="hmode" />
			<html:hidden name="MrdRecordMovementDetailFB" property="recordExistFlag" />
			<html:hidden name="MrdRecordMovementDetailFB" property="tempChkValue" />
			<html:hidden name="MrdRecordMovementDetailFB" property="isMrdListOne" />
			<html:hidden name="MrdRecordMovementDetailFB" property="recordType" />
			<input type="hidden" name="status" value="<%=move_Destroy_Status %>"/>
			
			<logic:equal name="MrdRecordMovementDetailFB" value="<%=MrdConfig.YES %>" property="isMrdListOne">
						<html:hidden name="MrdRecordMovementDetailFB" property="toMrdCode" />
				<html:hidden name="MrdRecordMovementDetailFB" property="fromMrdCode" />
			</logic:equal>
			</his:TransactionContainer>
			
		</html:form>
		<his:status/>
	</body>		
			
</html>			