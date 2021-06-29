<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
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
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">

window.onload=function()
{
	checkedBoundedRecord();
	showBoundingDiv();
}

function getBoundedRecordType()
{
	submitForm21('BOUNDREC');
}


function checkedBoundedRecord()
{
	var str=document.getElementsByName("mrdBoundedRecType")[0].value;
	var arr=str.split("@");
	var chks=document.getElementsByName('recordType');
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

function showBoundingDiv()
{
	if(document.getElementsByName("boundingMode")[0].checked)
	{
		document.getElementById("divMrdBounding").style.display="block";
		document.getElementById("divRackBounding").style.display="none";
		document.getElementById("divShelfBounding").style.display="none";
		
		//RACK
		document.getElementsByName("mrdCodeRack")[0].value="-1";
		document.getElementsByName("rackId")[0].value="-1";
		//SHELF
		document.getElementsByName("mrdCodeShelf")[0].value="-1";
		document.getElementsByName("rackIdShelf")[0].value="-1";
		document.getElementsByName("shelfId")[0].value="-1";
		
		if(document.getElementsByName("mrdCode")[0].value=="-1")
			document.getElementById("divRecordType").style.display="none";
		else	
			document.getElementById("divRecordType").style.display="block";
	}
	
	if(document.getElementsByName("boundingMode")[1].checked)
	{
		document.getElementById("divMrdBounding").style.display="none";
		document.getElementById("divRackBounding").style.display="block";
		document.getElementById("divShelfBounding").style.display="none";
		
		//MRD
		document.getElementsByName("mrdCode")[0].value="-1";
		//SHELF
		document.getElementsByName("mrdCodeShelf")[0].value="-1";
		document.getElementsByName("rackIdShelf")[0].value="-1";
		document.getElementsByName("shelfId")[0].value="-1";
		
		if(document.getElementsByName("rackId")[0].value=="-1")
			document.getElementById("divRecordType").style.display="none";
		else	
			document.getElementById("divRecordType").style.display="block";	
	}
	
	if(document.getElementsByName("boundingMode")[2].checked)
	{
		document.getElementById("divMrdBounding").style.display="none";
		document.getElementById("divRackBounding").style.display="none";
		document.getElementById("divShelfBounding").style.display="block";
		
		//MRD
		document.getElementsByName("mrdCode")[0].value="-1";
		//RACK
		document.getElementsByName("mrdCodeRack")[0].value="-1";
		document.getElementsByName("rackId")[0].value="-1";
		
		if(document.getElementsByName("shelfId")[0].value=="-1")
			document.getElementById("divRecordType").style.display="none";
		else	
			document.getElementById("divRecordType").style.display="block";	
	}
}

function getRack()
{
	if(document.getElementsByName("mrdCodeRack")[0].value!="-1")
		submitForm21('GETRACK');
}

function getRackForShelf()
{
	if(document.getElementsByName("mrdCodeShelf")[0].value!="-1")
		submitForm21('GETRACK');
}

function getShelf()
{
	if(document.getElementsByName("rackIdShelf")[0].value!="-1")
		submitForm21('GETSHELF');
}

function validateSave()
{
	if(validateBounding() && validateSelectRecordType())
		submitForm21('SAVE');
}

function validateSelectRecordType()
{
	var valid=false;
	var count=0;
	
	if(document.getElementsByName("isRecordTypeExist")[0].value==<%=MrdConfig.YES%> )
	{
		for(var i=0;i<document.getElementsByName("recordType").length;i++)
		{
			if(document.getElementsByName("recordType")[i].checked)
				count++;
		}
		
		if(count==0)
		{
			alert("Please Select At Least a Record Type");
			valid=false;
		}
		else
			valid=true;
	}
	else
	{
		alert("No Record Type Found To Bound");
		valid=false;
	}		
		
	 return valid;	
}

function validateBounding()
{
	var valid=false;
	if(document.getElementsByName("boundingMode")[0].checked)
	{
		if(comboValidation(document.forms[0].mrdCode,"MRD"))
			valid=true;
		else
			valid=false;	
	}
	
	if(document.getElementsByName("boundingMode")[1].checked)
	{
		if(comboValidation(document.forms[0].mrdCodeRack,"MRD")
			&& comboValidation(document.forms[0].rackId,"Rack"))
			valid=true;
		else
			valid=false;
	}
	
	if(document.getElementsByName("boundingMode")[2].checked)
	{
		if(comboValidation(document.forms[0].mrdCodeShelf,"MRD")
			&& comboValidation(document.forms[0].rackIdShelf,"Rack")
			&& comboValidation(document.forms[0].shelfId,"Shelf"))
			valid=true;
		else
			valid=false;
	}
	
	return valid;
}
</script>

<body>
	<his:TransactionContainer>
		<html:form action="/master/recordBounding">
		<his:SubTitleTag name="MRD Record Bounding">
		</his:SubTitleTag>
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
  									<bean:message key="boundingMode"/>
  								</b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont">
						<div align="left">
							&nbsp;<html:radio name="RecordBoundingFB" property="boundingMode" value="<%=MrdConfig.RECORD_BOUNDING_MODE_MRD_WISE %>" onclick="showBoundingDiv()"></html:radio>
								<bean:message key="mrd"/>
							&nbsp;<html:radio name="RecordBoundingFB" property="boundingMode" value="<%=MrdConfig.RECORD_BOUNDING_MODE_RACK_WISE %>" onclick="showBoundingDiv()"></html:radio>
								<bean:message key="rack"/>
							&nbsp;<html:radio name="RecordBoundingFB" property="boundingMode" value="<%=MrdConfig.RECORD_BOUNDING_MODE_SHELF_WISE %>" onclick="showBoundingDiv()"></html:radio>
								<bean:message key="shelf"/>
						</div>
					</td>
				</tr>
			</table>	
		</his:ContentTag>
		
		<div id="divMrdBounding">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="mrd"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont">
							<div align="left">
								<html:select name="RecordBoundingFB" property="mrdCode" tabindex="1" onchange="getBoundedRecordType()">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.LIST_ALL_MRD %>">
										<html:options collection="<%=MrdConfig.LIST_ALL_MRD %>" labelProperty="label" property="value"/>
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
				</table>	
			</his:ContentTag>
		</div>
		<div id="divRackBounding">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="mrd"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:select name="RecordBoundingFB" property="mrdCodeRack" tabindex="1" onchange="getRack()">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.LIST_ALL_MRD %>">
										<html:options collection="<%=MrdConfig.LIST_ALL_MRD %>" labelProperty="label" property="value"/>
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="rack"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:select name="RecordBoundingFB" property="rackId" tabindex="1" onchange="getBoundedRecordType()">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.ALL_RACK_LIST_BASED_ON_MRD_CODE %>">
										<html:options collection="<%=MrdConfig.ALL_RACK_LIST_BASED_ON_MRD_CODE %>" labelProperty="label" property="value"/>
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
				</table>	
			</his:ContentTag>
		</div>
		
		<div id="divShelfBounding">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="16%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="mrd"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="17%" class="tdfont">
							<div align="left">
								<html:select name="RecordBoundingFB" property="mrdCodeShelf" tabindex="1" onchange="getRackForShelf()">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.LIST_ALL_MRD %>">
										<html:options collection="<%=MrdConfig.LIST_ALL_MRD %>" labelProperty="label" property="value"/>
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="16%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="rack"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="17%" class="tdfont">
							<div align="left">
								<html:select name="RecordBoundingFB" property="rackIdShelf" tabindex="1" onchange="getShelf()">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.ALL_RACK_LIST_BASED_ON_MRD_CODE %>">
										<html:options collection="<%=MrdConfig.ALL_RACK_LIST_BASED_ON_MRD_CODE %>" labelProperty="label" property="value"/>
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="16%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="shelf"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="17%" class="tdfont">
							<div align="left">
								<html:select name="RecordBoundingFB" property="shelfId" tabindex="1" onchange="getBoundedRecordType()">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.ALL_SHELF_LIST_BASED_ON_RACK %>">
										<html:options collection="<%=MrdConfig.ALL_SHELF_LIST_BASED_ON_RACK %>" labelProperty="label" property="value"/>
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
				</table>	
			</his:ContentTag>
		</div>	
		
		<div id="divRecordType" style="display: none;">
		<his:statusList>
			<%List lstrecordType=(List)session.getAttribute(MrdConfig.RECORD_TYPE_FOR_BOUNDING_LIST); 
				if(lstrecordType.size()>0){
			%>
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="10%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="select"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="90%" class="tdfonthead">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="recordType"/>
	  								</b>
								</font>
							</div>
						</td>
					</tr>
					<%
					for(int i=0;i<lstrecordType.size();i++)
					{
						Entry recordType = (Entry) lstrecordType.get(i);
				%>
					<tr>
						<td class="tdfont">
							<div align="center">
								<html:checkbox name="RecordBoundingFB" property="recordType" value="<%=recordType.getValue() %>" tabindex="1"></html:checkbox>
							</div>
						</td>	
						<td class="tdfont">
							<div align="left">
								<%=recordType.getLabel() %>
							</div>
						</td>	
					</tr>
				<%} %>
				</table>
			</his:ContentTag>	
			<% }%>		
		</his:statusList>
		</div>
		
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="validateSave()" onkeypress="if(event.keyCode==13) validateSave()">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW')">
			
		</his:ButtonToolBarTag>
		<html:hidden name="RecordBoundingFB" property="hmode"/>
		<html:hidden name="RecordBoundingFB" property="mrdBoundedRecType"/>
		<html:hidden name="RecordBoundingFB" property="isRecordTypeExist"/>
		
		</html:form>
		<his:status/>
	</his:TransactionContainer>
</body>		

</html>