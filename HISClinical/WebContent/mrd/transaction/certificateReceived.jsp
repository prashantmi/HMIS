
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
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

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function enabledDisabledCombo()
{
	var len=document.getElementsByName("chk").length;
	for(i=0;i<len;i++)
	{
		if(document.getElementsByName("chk")[i].checked) 
		{
			document.getElementsByName("rackId")[i].disabled=false;
			document.getElementsByName("shelfId")[i].disabled=false;
		}			
		else
		{
			document.getElementsByName("rackId")[i].disabled=true;
			document.getElementsByName("shelfId")[i].disabled=true;
			document.getElementsByName("rackId")[i].value="-1"; 
			document.getElementsByName("shelfId")[i].value="-1"; 
		}
			
	}
	
}

function getSelf(mode,obj)
{
	document.getElementsByName('hmode')[0].value=mode
	document.getElementsByName('index')[0].value=obj
	document.forms[0].submit();
}

var queryString="";
var request="";
function sendDataForShelfList(obj1,obj2)
{	
	var url='/HISClinical/mrd/certificateReceived.cnt?hmode=SHELF&rackId='+obj1.value;
	queryString='hmode=SHELF&rackId='+obj1.value
	document.getElementsByName('index')[0].value=obj2;
	httpShelfListRequest("GET",url,true);
}

function httpShelfListRequest(reqType,url,asynch)
{
	//Mozilla-based browsers
	if(window.XMLHttpRequest)
	{
		request = new XMLHttpRequest();
		intialShelfListReq(reqType,url,asynch);
	}
	else if(window.ActiveXObject)
	{
		request=new ActiveXObject("Msxml2.XMLHTTP");
		if (! request)
			request=new ActiveXObject("Microsoft.XMLHTTP");
		if(request)
			/* Unlikely to branch here, as IE uses will be able to use either 
			one of the constructors*/
			intialShelfListReq(reqType,url,asynch);
		else
			alert("Your browser does not permit the use of all of this application's features!");
	}
	else
		alert("Your browser does not permit the use of all of this application's features!");
}

function intialShelfListReq(reqType,url,isAsynch)
{
	/* Specify the function that will handle the HTTP response */
	request.onreadystatechange=handleShelfList;
	request.open(reqType,url,isAsynch);
	
	/* set the Content-Type header for a POST request */
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	request.send(null);
}

function handleShelfList()
{
	if(request.readyState == 4)
	{
		popupList=null;
		if(request.status == 200)
		{
			var str="rackShelfId"+document.getElementsByName("index")[0].value;
			var elemSelf=document.getElementsByName('shelfId')[document.getElementsByName("index")[0].value] ;// document.getElementById(str);
			var responseString = request.responseText;
		//	alert("temp list "+responseString)
		//	alert("str=="+str)
			var tdObj=document.getElementById(str);
		//	alert("document.getElementById(str).innnerHTML"+ tdObj.innerHTML )
			tdObj.innerHTML="<div align='center'><select name='shelfId' tabindex='1' value='-1'><option value='-1'>Select Value</option>"+ responseString + "</select></div>";
		//	alert("document.getElementById(str).innnerHTML"+ tdObj.innerHTML)
		}
		else
		{
			alert("A problem occurred with communicating between "+"the XMLHttpRequest object and the server program.");
		}
	}
}

function validateAdd()
{
	var valid=true;
	var count=0;
	var len=document.getElementsByName("chk").length;
	for(var i=0;i<len;i++)
		if(document.getElementsByName("chk")[i].checked)
			count++;
	if(count==0)
	{
		alert("Please Select At Least One Certificate");
		document.getElementsByName("chk")[0].focus();
		valid=false;
	}
	else
	{
		for(var i=0;i<len;i++)
		{
			if(document.getElementsByName("chk")[i].checked)
			{
				if(document.getElementsByName("rackId")[i].value=="-1")
				{
					alert("Please Select The Rack");
					document.getElementsByName("rackId")[i].focus();
					valid=false;
					return false;
				}
				else if(document.getElementsByName("shelfId")[i].value=="-1")
				{
					alert("Please Select The Shelf");
					document.getElementsByName("shelfId")[i].focus();
					valid=false;
					return false;
				}
				else
					valid=true;
			}
		}
		
		if(typeof document.getElementsByName("receiveFrom")[0] != 'undefined')
		{
			if(document.getElementsByName("receiveFrom")[0].value=="")
			{
				alert("Please Enter The Received From Name");
				document.getElementsByName("receiveFrom")[0].focus();
				valid=false;
			}
		}	
	}
	return valid;
}

</script>

<body onload="enabledDisabledCombo()">
	<html:form action="/certificateReceived">
		<his:TransactionContainer>
			<his:TitleTag name="Certificate Received">
			</his:TitleTag>
			<his:statusList>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="4%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="select"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="certificateNo"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="21%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="recordType"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="10%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="duplicate"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="20%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="handedOverTo"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="rackName"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="shelf"/>
									</b>	
								</font>
							</div>
						</td>
					</tr>
					<logic:iterate id="certIssueDtlVO" indexId="idx" name="<%=MrdConfig.ALL_CERTIFICATE_ISSUE_DTL_VO_LIST%>" type="hisglobal.vo.CertificateIssueDtlVO">
					<%String index=idx.toString();%>
					<tr>
						<td width="4%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:checkbox property="chk" name="certificateReceivedFB" value="<%=index %>" onclick="enabledDisabledCombo()"></html:checkbox>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="certIssueDtlVO" property="certificateDesc"/>
								</font>
							</div>
						</td>
						<td width="21%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="certIssueDtlVO" property="recordTypeName"/>
								</font>
							</div>
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="certIssueDtlVO" property="isDuplicateName"/>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="certIssueDtlVO" property="handoverTo"/>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfont">
							<div align="center">
								<% String temp="sendDataForShelfList(this,"+index+")"; %>
								<html:select name="certificateReceivedFB" property="rackId" tabindex="1" onchange="<%=temp %>"  >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.ALL_RACK_LIST_VO%>">
									<html:options collection="<%=MrdConfig.ALL_RACK_LIST_VO %>" property="rackId" labelProperty="rackName"/>
									</logic:present>
								</html:select>
							</div>
						</td>
						<%String id="rackShelfId"+index; %>
						
						<td width="15%" class="tdfont" id="<%=id %>">
							
							<div align="center" >
								<html:select name="certificateReceivedFB"  property="shelfId" tabindex="1">
									<html:option value="-1">Select Value</html:option>
								</html:select>
							</div>
						</td>
					</tr>
					</logic:iterate>
				</table>					
			</his:ContentTag>
			
			<logic:equal name="certificateReceivedFB" property="certificateReceivedMode" value="<%=Config.CERTIFICATE_RECEIVED_ONLINE %>">
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<b>
											<bean:message key="rcvFrom"/>
										</b>
									</font>
								</div>
							</td>
							<td width="75%" class="tdfont">
								<div align="left">
									<html:text name="certificateReceivedFB" property="receiveFrom" maxlength="50" size="35" onkeypress="return validateAlphaNumOnly(this,event)"  tabindex="1"></html:text>
								</div>
							</td>
						</tr>
					</table>		
				</his:ContentTag>
			</logic:equal>
			
			</his:statusList>
		
		<his:ButtonToolBarTag>
			<his:statusList>
			  <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='1' onclick ="if(validateAdd())submitPage('SAVE');" onkeypress="if(event.keyCode==13)if(validateAdd())submitPage('SAVE');")>
			  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('CANCEL');" onkeypress="if(event.keyCode==13) submitPage('CANCEL');">
			  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('NEW');" onkeypress="if(event.keyCode==13) submitPage('NEW');">
			</his:statusList>
			
			<his:statusUnsuccessfull>
				 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('CANCEL');" onkeypress="if(event.keyCode==13) submitPage('CANCEL');">
			</his:statusUnsuccessfull>
		</his:ButtonToolBarTag>	
	
	</his:TransactionContainer>
		
		<html:hidden name="certificateReceivedFB" property="hmode"/>
		<html:hidden name="certificateReceivedFB" property="index"/>
		<html:hidden name="certificateReceivedFB" property="certificateReceivedMode"/>
	</html:form>
	<his:status/>
</body>
</html>

