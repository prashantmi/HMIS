<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />

<link href="/HISClinical/ipd/css/transaction.css" rel="stylesheet" type="text/css">
<link href="/HISClinical/hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="/HISClinical/hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />

<his:javascript src="/hisglobal/transactionutil/js/master.js"/>

<his:javascript src="/opd/opdJs/opd.js" />
<his:javascript src="/opd/opdJs/opdAjax.js" />

<%@ page import="opd.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript"><!--
var isPopulated=false;
var queryString;
function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function sendData(valSelected)
{
	var url='/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=POPULATESEARCH&selectedDrugIndex='+valSelected;
	httpRequest("GET",url,true);
	//self.close();
}
	
function handleResponse()
{
	if(request.readyState == 4)
	{
		//alert("ready state");
		if(request.status == 200)
		{
			//alert("request status");
			var resp=request.responseText;
			var drugName=resp.substring(0,resp.indexOf('^'));
			var drudId=resp.substring(resp.indexOf('^')+1);
	
			opener.setSearchValues(drudId,drugName);
 			isPopulated=true;
			//alert("hhhhhhhhh");
			//opener.document.forms[0].submit();
			window.close();
			//window.self.focus();
			//alert("after");
			//document.forms[0].submit();
			
		}
		else
		{
			alert("A problem occurred with communicating between "+"the XMLHttpRequest object and the server program.");
		}
	}
}

function validateCode()
{
	var valSelected=-1;
	var drudId = "";
	var drugName = "";
	
	// var isPopulated=false;
	for(i=0;i<self.document.getElementsByName('selectedDrugIndex').length;i++)
	{
		if(self.document.getElementsByName('selectedDrugIndex')[i].checked)
		{
			valSelected=self.document.getElementsByName('selectedDrugIndex')[i].value;
			drudId = self.document.getElementsByName('searchListDrugId')[i].value;
			drugName = self.document.getElementsByName('searchListDrugName')[i].value; 
			break;
		}
	}
	if(valSelected!="-1")
	{
		//sendData(valSelected);
		opener.setSearchValues(drudId,drugName);
 		//isPopulated=true;
		window.close();
	}	
}

function validateSearch(code)
{
	if(code=='1')
		document.getElementsByName("searchDrugItemTypeId")[0].value="";
	else if(code=='2')
		document.getElementsByName("searchDrugName")[0].value="";
	
	if(document.getElementsByName("searchDrugName")[0].value==null || document.getElementsByName("searchDrugName")[0].value=="")
		if(document.getElementsByName("searchDrugItemTypeId")[0].value==null || document.getElementsByName("searchDrugItemTypeId")[0].value=="-1") 
		{
			alert('Enter Drug Name or Select Item Type');
			return false;
		}
		else
			return true;
	else
		return true;
}

/********************for getting drug property********************************/
var modeGbl="";
var itemIdGbl="";
function myAjaxFunction(myurl,mode) {

	var result = "";
	modeGbl=mode;
	// checking browser
	if (navigator.userAgent.indexOf("Opera") >= 0) {
		alert("This example doesn't work in Opera");
		return;
	}
	if (navigator.userAgent.indexOf("MSIE") >= 0) {
		var strName = "Msxml2.XMLHTTP"
		if (navigator.appVersion.indexOf("MSIE 5.5") >= 0) {
			strName = "Microsoft.XMLHTTP"
		}
		try {
			objXmlHttp = new ActiveXObject(strName)
			objXmlHttp.onreadystatechange = function() {
				if (objXmlHttp.readyState == 4 || objXmlHttp.readyState == 200) {
					result = objXmlHttp.responseText;
					ajaxResponse(result,modeGbl);
				}

			}
		} catch (e) {
			alert("Error. Scripting for ActiveX might be disabled");
			return;
		}
	} else {
		if (navigator.userAgent.indexOf("Mozilla") >= 0) {
			objXmlHttp = new XMLHttpRequest();
			objXmlHttp.onload = function() {
				if (objXmlHttp.readyState == 4 || objXmlHttp.readyState == 200) {
					result = objXmlHttp.responseText;
					//alert(result);
					ajaxResponse(result,mode)
					
				}

			}
			//objXmlHttp.onerror=sendMyReq
		}
	}
	
	objXmlHttp.open("GET", myurl, false)
	
	objXmlHttp.send(null)
	
	return result;

}

function ajaxResponse(res,mode){
	if(mode==1){
		//alert("res"+res);
		//alert("mode "+mode);
		document.getElementById("drugProfileId").innerHTML=res;
			var mode="saftyData";
						
	        var url="/HISClinical/mms/transactions/DrugProfileCNT.cnt?hmode="+mode+"&strItemID="+itemIdGbl;
	        // alert("url "+url);
	         myAjaxFunction(url,"2");
	}
	if(mode==2){
		//alert("res"+res);
		document.getElementById("drugSafetyId").innerHTML=res;
		
	}
}
function getDrugDetail(itemId,index)
{
	document.getElementById("drugSafetyId").innerHTML="";
	document.getElementById("drugProfileId").innerHTML="";
	
	var mode="dosageData";
	itemIdGbl=itemId;
	var url="/HISClinical/mms/transactions/DrugProfileCNT.cnt?hmode="+mode+"&strItemID="+itemIdGbl;
	
	myAjaxFunction(url,"1");
	
	document.getElementById('blanket').style.display="block";
	document.getElementById('userIdDiv').style.display="block";
		
	/*
	var path='/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=DRUGPROPERTIES&propertyItemId='+itemId+'&selectedSearchIndex='+index;
	//alert("path "+path);
	
	child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus(); 
	if(!child.opener)
		child.opener = self;
	return child
	*/
}

function cancel()
{
	document.getElementById('blanket').style.display="none";
	document.getElementById('userIdDiv').style.display="none";
}

/****************end of getting drug property*******************************/

function activatePopUpTab(tabObj){
	
		var divDrugSearch = document.getElementById('divDrugSearch');
		var divDrugProfileSearch = document.getElementById('divDrugProfileSearch');
			
	
	if(typeof divDrugSearch != 'undefined' && typeof divDrugProfileSearch != 'undefined')
	{
		if(tabObj.id=="tabDrugSearch")
		{
			
			document.getElementById('tabDrugSearch').className="highlight";
			document.getElementById('tabDrugProfileSearch').className="dehighlite";
						
			divDrugSearch.style.display="block";
			divDrugProfileSearch.style.display="none";
						
		}
		else if(tabObj.id=="tabDrugProfileSearch")
		{
			document.getElementById('tabDrugSearch').className="dehighlite";
			document.getElementById('tabDrugProfileSearch').className="highlight";
						
			divDrugSearch.style.display="none";
			divDrugProfileSearch.style.display="block";
		}
	}
}

window.onload=function()
{
	document.getElementById('tabDrugSearch').className="highlight";
	document.getElementById('tabDrugProfileSearch').className="dehighlite";
	
	activatePopUpTab(document.getElementById('<bean:write name="PatientTreatmentDetailFB" property="activatePopUpTab"/>'));
}

function getDrugListDetail(drugListId)
{
	document.getElementsByName("drugListId")[0].value=drugListId
	submitPage('GETDRUGLISTDETAIL');
}

function populateDrugList(obj)
{
	//For isEmptyStomach field in drug Advice
	var len=opener.document.getElementsByName("selIsEmptyStomach").length;
	var emptyStomachIndex="";
	for(i=0;i<len;i++)
	{
		if(opener.document.getElementsByName("selIsEmptyStomach")[i].checked)
		{	
			emptyStomachIndex=emptyStomachIndex+"1"+"#";
		}
		else
		{
			emptyStomachIndex=emptyStomachIndex+"0"+"#";
		}
	}
	opener.document.getElementsByName("emptyStomachIndexArray")[0].value=emptyStomachIndex;
	
	
	opener.document.getElementsByName("drugListId")[0].value=obj.value;
	opener.document.getElementsByName('hmode')[0].value='POPULATEDRUGLIST';
	opener.document.forms[0].submit();
	self.close();
}

function submitPage(mode)
{
	document.getElementsByName('hmode')[0].value=mode
	document.forms[0].submit();
}

--></script>

<STYLE TYPE="text/css">
	.highlight {
		line-height: 16px;
		font-weight: bold;
		font-family: arial;
		font-size: 11px;
		padding-top: 1px;
		vertical-align: middle;
		padding-left: 6px;
   		background:#E0EBEB;//FFEBD5;
   		border-style:solid;
		border-width:1px;
		
	
   		}
</STYLE>
<STYLE TYPE="text/css">
	.dehighlite {
	font-family: verdana, arial, sans-serif;
    font-size:12px;
    text-decoration: none;
    color: black;
    background-color: #E0EBEB;//FFEBD5;
    white-space: nowrap;
    background:#E0EBEB;
    border-style:solid;
	border-width:1px;
   		}
</STYLE>

<html:form action="/patTreatmentDetailTile">
	<his:TitleTag>
		<his:name>
			<bean:message key="search" />
		</his:name>
	</his:TitleTag>
	<table width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td id="tabDrugSearch" onclick="activatePopUpTab(this)" width="25%" class="tdfonthead"><div align="center" >Drug Search</div></td>
			<td id="tabDrugProfileSearch" onclick="activatePopUpTab(this)" width="25%" class="tdfonthead"><div align="center">Drug Profile Search</div></td>
		</tr>
	</table>
	
	
	<div id="divDrugSearch" style="display: block;">
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="30%" class="addtoolbar" style="border-top: outset black 2px; border-bottom: inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="drugname" />
						</font>
					</div>
				</td>
				<td width="10%" class="addtoolbar" style="border-top: outset black 2px; border-bottom: inset black 2px;">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						&nbsp; &nbsp;
					</font>
				</td>
				<td width="30%" class="addtoolbar" style="border-top: outset black 2px; border-bottom: inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="itemtype" />
						</font>
					</div>
				</td>
				<td width="10%" class="addtoolbar" style="border-top: outset black 2px; border-bottom: inset black 2px;">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						&nbsp; &nbsp;
					</font>
				</td>
			</tr>
			<tr>
				<td width="30%" class="tdfonthead">
					<div align="center">
						<html:text name="PatientTreatmentDetailFB" property="searchDrugName" tabindex="1" 
							maxlength="100" onkeypress="return validateAlphaOnly(this,event)"/>
					</div>
				</td>
				<td width="10%" class="tdfonthead">
					<div align="center">
						<img src='<his:path src="/hisglobal/images/forward3.gif"/>' tabindex="1" 
							onClick="if(validateSearch('1')) submitForm21('SEARCHDRUG');"
							onkeypress="if(event.keyCode==13) if(validateSearch('1')) submitForm21('SEARCHDRUG');">
					</div>
				</td>
				<td width="30%" class="tdfonthead">
					<div align="center">
						<html:select name="PatientTreatmentDetailFB" property="searchDrugItemTypeId">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_ALL_ITEM_TYPE%>" >
							<html:options collection="<%=OpdConfig.ESSENTIALS_LIST_ALL_ITEM_TYPE%>" property="value" labelProperty="label"/>
							</logic:present>
						</html:select>
					</div>
				</td>
				<td width="10%" class="tdfonthead">
					<div align="center">
						<img src='<his:path src="/hisglobal/images/forward3.gif"/>' tabindex="1" 
							onClick="if(validateSearch('2')) submitForm21('SEARCHDRUG');"
							onkeypress="if(event.keyCode==13) if(validateSearch('2')) submitForm21('SEARCHDRUG');">
					</div>
				</td>
			</tr>
		</table>
		<!-- ********************adding drug property*************************** -->
			<div id="blanket" style="height: 500;width: 800;display: none;"></div>
			<div id="userIdDiv" style="display: none; height: 110px;width: 310px;position:relative; top: 5%;left: 5%;z-index: 9100;background-color: #E0EBEB;">
				
				<table  width="700">
					<tr CLASS="HEADER">
						<td >Drug Safety Alert</td>
					</tr>
				</table>
					<div id="drugSafetyId"></div>
				<table  width="700">
					<tr CLASS="HEADER">
						<td>Drug Dose Indication</td>
					</tr>
				</table>	
					<div id="drugProfileId"></div>
				
				<table  width="700">
					<tr>
						<td align="center">
							<div align="center">
								<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>'  style=cursor:pointer tabindex="1" onclick ="cancel()">
							</div>
						</td>
					</tr>
				</table>		
						
			</div>
	<!-- ********************end drug property******************************** -->			

		<his:statusTransactionInProcess>
			<table width="100%" cellpadding="0" cellspacing="1">
				<logic:notEmpty name="<%=OpdConfig.PAT_TREATMENT_DTL_SERACH_DRUG_DETAIL_LIST%>">
				<logic:iterate id="drug" name="<%=OpdConfig.PAT_TREATMENT_DTL_SERACH_DRUG_DETAIL_LIST%>" indexId="idx" type="hisglobal.utility.Entry">
					<tr>
						<td width="10%" class="tdfonthead">
							<div align="center">
								<input type="hidden" name="searchListDrugId" value="<%=(String) drug.getValue()%>" />
								<input type="hidden" name="searchListDrugName" value="<%=(String) drug.getLabel()%>" /> 
								<html:radio name="PatientTreatmentDetailFB" property="selectedDrugIndex" value="<%=idx.toString()%>" onclick="validateCode()" />
							</div>
						</td>
						<td width="90%" class="tdfont">
							<div align="center">
								<%=(String) drug.getLabel()%>									
							</div>	
						</td>
					</tr>
				</logic:iterate>
				</logic:notEmpty>
			</table>
		</his:statusTransactionInProcess>
	</his:ContentTag>
	</div>
	
	<div id="divDrugProfileSearch" style="display:none;">
		<his:ContentTag>
		<his:statusTransactionInProcess>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="10%" class="addtoolbar" style="border-top: outset black 2px; border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="select" />
							</font>
						</div>
					</td>
					<td width="95%" class="addtoolbar" style="border-top: outset black 2px; border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="drugList" />
							</font>
						</div>
					</td>
				</tr>
				<logic:notEmpty name="<%=OpdConfig.DRUGLIST_LIST_FOR_SEARCH%>">
				<logic:iterate id="drug" name="<%=OpdConfig.DRUGLIST_LIST_FOR_SEARCH%>" indexId="idx" type="hisglobal.utility.Entry">
					<tr>
						<td width="10%" class="tdfonthead">
							<div align="center">
							<%String maxEntryDate=(String)session.getAttribute(OpdConfig.MAX_ENTRY_DATE_BY_CRNO); %>
							<%
								if(drug.getValue().split("#")[1].equals(OpdConfig.IS_DEFAULT_YES) && maxEntryDate==null)
								{
							%>
								<html:radio name="PatientTreatmentDetailFB" property="selectedDrugIndex" value='<%=(String) drug.getValue().split("#")[0]%>' onclick="populateDrugList(this)"  disabled="true" />
							<%		
								}
								else
								{
							%>
								<html:radio name="PatientTreatmentDetailFB" property="selectedDrugIndex" value='<%=(String) drug.getValue().split("#")[0]%>' onclick="populateDrugList(this)"  />							
							<%		
								}
							%>	
								
							</div>
						</td>
						<td width="90%" class="tdfont">
							<div align="center">
								<%
									if(drug.getValue().split("#")[1].equals(OpdConfig.IS_DEFAULT_YES))
									{
								%>
									<%=(String) drug.getLabel() +" ( Default ) " %>
								<%		
									}
									else
									{
								%>
									<%=(String) drug.getLabel()%>
								<%		
									}
								%>
							</div>	
						</td>
					</tr>
				</logic:iterate>
				</logic:notEmpty>
			</table>
			<logic:present name="<%=OpdConfig.PARTICULAR_DRUGLIST_DEATIL%>">
			<logic:notEmpty name="<%=OpdConfig.PARTICULAR_DRUGLIST_DEATIL%>">
			<his:SubTitleTag name="Drug Deatil">
			</his:SubTitleTag>
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
				
					
					
					<logic:iterate id="drugDtlVO" indexId="i" name="<%=OpdConfig.PARTICULAR_DRUGLIST_DEATIL%>" type="hisglobal.vo.PatDrugTreatmentDetailVO">
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
				</table>
				</logic:notEmpty>
				</logic:present>
				
		</his:statusTransactionInProcess>	
		</his:ContentTag>	
	</div>
		
	
	<html:hidden name="PatientTreatmentDetailFB" property="hmode" />
	<html:hidden name="PatientTreatmentDetailFB" property="activatePopUpTab" />
	<html:hidden name="PatientTreatmentDetailFB" property="drugListId" />
	<his:status />
</html:form>