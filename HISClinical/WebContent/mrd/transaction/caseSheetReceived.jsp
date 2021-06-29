<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>



<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="mrd.transaction.controller.fb.CaseSheetReceivedFB"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="hisglobal.vo.CaseSheetDispatchVO"%>
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
function getCaseSheetDtl(){
	var crNo=document.getElementsByName("recordId")[0].value.split("#")[1];
	document.getElementsByName("patCrNo")[0].value=crNo;
	submitPage('GETCASESHEETDTL');

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
			tdObj.innerHTML="<select name='shelfId' tabindex='1' value='-1' class='regcbo'><option value='-1'>Select Value</option>"+ responseString + "</select>";
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
	if(document.getElementsByName("isAccept")[0].value=="-1"){
		alert("Please Select Acceptence");
		document.getElementsByName("isAccept")[0].focus();
		return false;
	}
	if(document.getElementsByName("isAccept")[0].value==<%=MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_RETURN%>){
		if(document.getElementsByName("returnReason")[0].value==""){
			alert("Please Enter Return Reason");
			document.getElementsByName("returnReason")[0].focus();
			return false;
		}
	
	}
	else{
		var enclosureLength=document.getElementsByName('selectedEnclosureId').length;
  		var length=document.getElementsByName('selectedCheckListId').length;
     	var count=0;
     	for(var j=0;j<enclosureLength;j++)
     	{
      		if(document.getElementsByName('selectedEnclosureId')[j].checked)
 	 		{
	 	 		if(document.getElementsByName('receivedPages')[j].value=="")
	 			{
	 	 			alert("Please Enter No. of Received Pages");
	 	 			document.getElementsByName('receivedPages')[j].focus()
	 	 			enclosureValid=false;
	 	 			return enclosureValid; 
	 	 			break;
	 	  	 	}
	 	 		else if(parseInt(document.getElementsByName('receivedPages')[j].value)<0)
	 			{
	 	 			alert("No. of Received Pages should be greater than or equal to Zero");
	 	 			document.getElementsByName('receivedPages')[j].focus();
	 	 			return false; 
	 	 			break;
	 	  	 	 }
	 	  	 	 else
	 	  	 	 {
	 	  	 	 	if(parseInt(document.getElementsByName('receivedPages')[j].value) > parseInt(document.getElementsByName('pages')[j].value))
	 	  	 	 	{
	 	  	 	 		alert("Received No. of Pages can not be greater than No. of Pages");
	 	  	 	 		document.getElementsByName('receivedPages')[j].focus()
	 	  	 	 		return false;
	 	  	 	 	}
	 	  	 	 }
	 	  	 	 count++;
	 	   }
	 	 }
     	for(var j=0;j<length;j++)
     	{
      		if(!document.getElementsByName('selectedCheckListId')[j].checked)
 	 		{
	 	 		alert("Please Select CheckList");	
	 	 		return false;
	 	    }
	 	 }
	 	if(count==0){
	 		alert("Select at least one enclosure");
	 	  	return false;
	 	} 
	 	else if(count < enclosureLength){
	 		alert("You have not selected all enclosure");
	 	}
 	  	if(document.getElementsByName('rackId')[0].value=="-1"){
	 	  	alert("Please Select Rack Name");
	 	  	document.getElementsByName('rackId')[0].focus();
	 	  	return false;
 	  	}
 	   if(document.getElementsByName('shelfId')[0].value=="-1"){
	 	  	alert("Please Select Shelf Name");
	 	  	document.getElementsByName('shelfId')[0].focus();
	 	  	return false;
 	   }
 	 	
 	 
	}
	return valid;
}

function showDiv(obj){

	if(obj.value==<%=MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_RETURN%>){
		document.getElementById("returnDiv").style.display="block";		
	}
	else{
		document.getElementById("returnDiv").style.display="none";	
	}
}

function showPageTextBox(enclosureId)
 {
    var length=document.getElementsByName('selectedEnclosureId').length;
    for(var i=0;i<length;i++)
    {
    if(document.getElementsByName('selectedEnclosureId')[i].checked)
     {
       document.getElementsByName('receivedPages')[i].disabled=false;
     
     }else{
       document.getElementsByName('receivedPages')[i].disabled=true;
       document.getElementsByName('receivedPages')[i].value = "";
       
     }
     }
 }
 
 function checkAllEnclosure(obj){
	var enclosure=document.getElementsByName("selectedEnclosureId");
	if(obj.checked){
		for(var i=0;i<enclosure.length;i++){
			document.getElementsByName("selectedEnclosureId")[i].checked=true;
			showPageTextBox(obj);
		}	
	}
	else{
		for(var i=0;i<enclosure.length;i++){
			document.getElementsByName("selectedEnclosureId")[i].checked=false;
			showPageTextBox(obj);
		}	
	
	}
}
function checkAllChecklist(obj){
	var checkList=document.getElementsByName("selectedCheckListId");
	if(obj.checked){
		for(var i=0;i<checkList.length;i++){
			document.getElementsByName("selectedCheckListId")[i].checked=true;
			showRemarksTextBox(obj);
		}	
	}
	else{
		for(var i=0;i<checkList.length;i++){
			document.getElementsByName("selectedCheckListId")[i].checked=false;
			showRemarksTextBox(obj);
		}	
	
	}
}

function showRemarksTextBox(enclosureId)
 {
     var length=document.getElementsByName('selectedCheckListId').length;
    for(var i=0;i<length;i++)
    {
    if(document.getElementsByName('selectedCheckListId')[i].checked)
     {
       document.getElementsByName('checkListremarks')[i].disabled=false;
     
     }else{
       document.getElementsByName('checkListremarks')[i].disabled=true;
       
     }
     }
 }

function clearForm(){
	var enclosureLength=document.getElementsByName('selectedEnclosureId').length;
    var length=document.getElementsByName('selectedCheckListId').length;
  
     for(var j=0;j<enclosureLength;j++)
     {
     	if(document.getElementsByName('selectedEnclosureId')[j].checked)
     	{
     	document.getElementsByName('receivedPages')[j].value="";
     	}
     }
     for(var i=0;i<length;i++)
     {
      if(document.getElementsByName('selectedCheckListId')[i].checked)
     	{
     	document.getElementsByName('checkListremarks')[i].value="";
     	}
     }
     
     document.getElementsByName("selectedAllEnclosureId")[0].checked=false;
     if( document.getElementsByName("selectedAllCheckListId")[0]){
     	document.getElementsByName("selectedAllCheckListId")[0].checked=false;
     }	
     checkAllEnclosure(document.getElementsByName("selectedAllEnclosureId")[0])
     checkAllChecklist(document.getElementsByName("selectedAllCheckListId")[0])
	 document.getElementsByName("isAccept")[0].value="-1"
	 showDiv(document.getElementsByName("isAccept")[0])
	  document.getElementsByName("returnReason")[0].value=""
	  document.getElementsByName("rackId")[0].value="-1"
	  document.getElementsByName("shelfId")[0].value="-1"

}	

function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}
</script>

<body>
	<his:TransactionContainer>
	<html:form action="/caseSheetReceived">
			<his:TitleTag name="Case Sheet Acceptence">
			</his:TitleTag>
			<his:statusList>
			<logic:present name="<%=MrdConfig.CASE_SHEET_DISPATCH_VO_LIST%>">
			<% 	PaginationFB fbPage= new PaginationFB();
				pageContext.setAttribute("fbPagination",fbPage);
				fbPage.setCurrentPage(((CaseSheetReceivedFB)request.getAttribute("caseSheetReceivedFB")).getCurrentPage());
				fbPage.setObjArrName(MrdConfig.CASE_SHEET_DISPATCH_VO_LIST);
				fbPage.setAppendInTitle("Records");
				fbPage.setMaxRecords(15);
				%>
			<his:ContentTag>
			<his:PaginationTag name="fbPagination"></his:PaginationTag>	
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
										<bean:message key="recordId"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="crNo"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="21%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="dept(unit)"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="10%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="ward"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="10%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="original/duplicate"/>
									</b>	
								</font>
							</div>
						</td>
						
					</tr>
					<%	List list=(List)session.getAttribute(MrdConfig.CASE_SHEET_DISPATCH_VO_LIST);
						int startIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
						int endIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX);
						for(int i=startIndex;i<=endIndex;i++)
						{
							CaseSheetDispatchVO caseSheetDispatchVO=(CaseSheetDispatchVO)list.get(i);
						%>
					<tr>
						<td width="4%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="caseSheetReceivedFB" property="recordId" value="<%=caseSheetDispatchVO.getRecordId()+"#"+
									caseSheetDispatchVO.getPatCrNo()+"#"+caseSheetDispatchVO.getSlNo()+"#"+caseSheetDispatchVO.getCaseSheetId()%>" onclick="getCaseSheetDtl()"></html:radio>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=caseSheetDispatchVO.getRecordId() %>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=caseSheetDispatchVO.getPatCrNo() %>
								</font>
							</div>
						</td>
						<td width="21%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=caseSheetDispatchVO.getDepartment() %>(
									<%=caseSheetDispatchVO.getDepartmentUnit()%>)
								</font>
							</div>
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=caseSheetDispatchVO.getWard()%>
								</font>
							</div>
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=caseSheetDispatchVO.getIsDuplicate() %>
								</font>
							</div>
						</td>
						
					</tr>
					<%} %>
				</table>					
			</his:ContentTag>
		<%-- 	<his:ContentTag>
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
									<html:text name="caseSheetReceivedFB" property="receiveFrom" maxlength="50" size="35" onkeypress="return validateAlphaNumOnly(this,event)"  tabindex="1"></html:text>
								</div>
							</td>
						</tr>
					</table>		
				</his:ContentTag>
				--%>
				</logic:present>	
			</his:statusList>
				<his:statusDone>
				 <his:ContentTag>
	                <table width="100%" border="0"  cellspacing="1" cellpadding="0">
	                	<tr>
	                		<td width="50%" class="tdfonthead">
								<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="acceptence"/>
									</b>	
								</font>
							</td>
							<td width="50%" class="tdfont">
								<html:select name="caseSheetReceivedFB" property="isAccept" tabindex="1" onchange="showDiv(this)" styleClass="regcbo">
									<html:option value="-1">Select Value</html:option>
									<html:option value="<%=MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_READY_TO_DIPATCH %>">Received</html:option>
									<html:option value="<%=MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_RETURN %>">Return</html:option>
								</html:select>
							</td>
						</tr>
					</table>
					<div id="returnDiv" style="display: none">
						 <table width="100%" border="0"  cellspacing="1" cellpadding="0">
	                	<tr>
	                		<td width="50%" class="tdfonthead">
								<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="returnReason"/></b>	
								</font>
							</td>
							<td width="50%" class="tdfont">
								<html:textarea name="caseSheetReceivedFB" property="returnReason" onkeypress="return CheckMaxLength(event,this,100)" cols="30"></html:textarea>
							</td>
						</tr>
					</table>
					</div>
					</his:ContentTag>
				 <jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>  
					
                 <his:SubTitleTag name="Enclosure Summary">
			   </his:SubTitleTag>
				<logic:present name="<%=MrdConfig.ENCLOSURE_NAMES_CASESHEET_DISPATCH_VO_ARRAY %>">			  
			   <his:ContentTag>
                <table width="100%" border="0"  cellspacing="1" cellpadding="0">
                
                <tr>
                <td width="10%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
						<input type="checkbox" name="selectedAllEnclosureId" value="0" onclick="checkAllEnclosure(this)" />
					</b>	
					</font>
					</div>
					</td>
					<td width="40%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<bean:message key="enclosure"/>
					</b>	
					</font>
					</div>
					</td>
					<td width="25%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<bean:message key="pages"/>
					</b>	
					</font>
					</div>
					</td>
					<td width="25%" class="tdfonthead">
					<div align="center">
					<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<bean:message key="noOfPagesReceived"/>
					</b>	
					</font>
					</div>
					</td>
					
                </tr>
                 <logic:iterate id="enclosureDtlVOs" indexId="idxx" name="<%=MrdConfig.ENCLOSURE_NAMES_CASESHEET_DISPATCH_VO_ARRAY %>" type="hisglobal.vo.RecordTypeWiseEnclosureMstVO" >
             			   
                <tr>
                <td width="10%" class="tdfont">
						<div align="center">
						<html:checkbox name="caseSheetReceivedFB" property="selectedEnclosureId" value='<%=enclosureDtlVOs.getEnclosureId()+"#"+enclosureDtlVOs.getSlNo()%>' onclick="showPageTextBox(this)" ></html:checkbox>
									
						</div>
					</td>
               
               		 <td width="40%" class="tdfont">
						 <div align="center">
					         <bean:write name="enclosureDtlVOs" property="enclosure"/>
					     </div>
					</td>
					<td width="25%" class="tdfont">
						<div align="center">
			         	 <bean:write name="enclosureDtlVOs" property="pages"/>
			         	 <html:hidden name="caseSheetReceivedFB" property="pages" value="<%=enclosureDtlVOs.getPages() %>"/>
						</div>
						</td>
					<td width="25%" class="tdfont">
					<div align="center">
			          &nbsp;<html:text name="caseSheetReceivedFB" property="receivedPages" disabled="true" maxlength="3" size="3"
							onkeypress="return validateNumeric(event)">
						</html:text>
						</div>
						</td>
                </tr>
                </logic:iterate>
                </table>
                </his:ContentTag>  
                </logic:present>        
			   <logic:notEmpty name="<%=MrdConfig.CHECKLIST_CASESHEET_DISPATCH_VO_ARRAY %>">
                <his:SubTitleTag name="CheckList Details">
			   </his:SubTitleTag>
			   <his:ContentTag>
                <table width="100%" border="0"  cellspacing="1" cellpadding="0">
                
                <tr>
					<td width="10%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<input type="checkbox" name="selectedAllCheckListId" value="0" onclick="checkAllChecklist(this)" />
					</b>	
					</font>
					</div>
					</td>
					<td width="40%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<bean:message key="checklist"/>
					</b>	
					</font>
					</div>
					</td>
					<td width="50%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<bean:message key="remarks"/>
					</b>	
					</font>
					</div>
					</td>
                </tr>
                 <logic:iterate id="checklistDtlVOs" indexId="idxxx" name="<%=MrdConfig.CHECKLIST_CASESHEET_DISPATCH_VO_ARRAY %>" type="hisglobal.vo.RecordTypeCheckListMstVO" >
             	
             			    
                <tr>
                	<td width="10%" class="tdfont">
						<div align="center">
						<html:checkbox name="caseSheetReceivedFB" property="selectedCheckListId" value="<%=checklistDtlVOs.getChecklistId()%>" onclick="showRemarksTextBox(this)" ></html:checkbox>
						
						</div>
					</td>
				   <td width="40%" class="tdfont">
						 <div align="center">
					     <bean:write name="checklistDtlVOs" property="checklistName"/>
					     </div>
					</td>
					<td width="50%" class="tdfont">
						<div align="center" >
				          &nbsp;<html:text name="caseSheetReceivedFB" property="checkListremarks" styleClass="textbox" disabled="true" maxlength="50" size="30"
							onkeypress="return validateAlphaNumericOnly(event,this)">
						</html:text>
						</div>
					</td>
			
                </tr>
                </logic:iterate>
                </table>
                </his:ContentTag>
                </logic:notEmpty>
                <his:SubTitleTag name="Storage Detail">
                </his:SubTitleTag>
                  <his:ContentTag>
	                <table width="100%" border="0"  cellspacing="1" cellpadding="0">
	                	<tr>
	                		<td width="15%" class="tdfonthead">
							
								<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="rackName"/>
									</b>	
								</font>
							
						</td>
						<td width="15%" class="tdfont">
							
								<% String temp="sendDataForShelfList(this,"+0+")"; %>
								<html:select name="caseSheetReceivedFB" property="rackId" tabindex="1" onchange="<%=temp %>" styleClass="regcbo">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.ALL_RACK_LIST_VO%>">
									<html:options collection="<%=MrdConfig.ALL_RACK_LIST_VO %>" property="rackId" labelProperty="rackName"/>
									</logic:present>
								</html:select>
							
						</td>
						<%String id="rackShelfId"+0; %>
						<td width="15%" class="tdfonthead">
							
								<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="shelf"/>
									</b>	
								</font>
							
						</td>
						<td width="15%" class="tdfont" id="<%=id %>">
							<html:select name="caseSheetReceivedFB"  property="shelfId" tabindex="1" styleClass="regcbo">
								<html:option value="-1">Select Value</html:option>
							</html:select>
						</td>
	                	</tr>
					</table>
					</his:ContentTag>	
						               
            <%--   
			
			<his:SubTitleTag>
			<his:name>
				<bean:message key="legends"/>
			</his:name>
			<table width="100%" cellspacing="0" cellpadding="0">
			<tr>
			<td width="70%"> </td>
				<td width="30%">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font><img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' onclick="showLegends();">		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font><img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' onclick="showLegendsNone();">
				</div>
				</td>			
			</tr>
			</table>
			</his:SubTitleTag>
			<div id="divLegends" style="display:none">
			<his:ContentTag>
				<table width="100%" cellpadding="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
					<tr>
						<td width="50%" style="color:#990000">
							Compulsory
						</td>
									
					</tr>
					<tr>
						<td width="50%" style="color:#000000">
							Not Compulsory
						</td>				
					</tr>
				</table>
			</his:ContentTag>
			</div> --%>
		 </his:statusDone> 
			
		
		<his:ButtonToolBarTag>
			<his:statusList>
			  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('CANCEL');" onkeypress="if(event.keyCode==13) submitPage('CANCEL');">
			</his:statusList>
			<his:statusDone>
			  <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='1' onclick ="if(validateAdd())submitPage('SAVE');" onkeypress="if(event.keyCode==13)if(validateAdd())submitPage('SAVE');")>
			  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('NEW');" onkeypress="if(event.keyCode==13) submitPage('NEW');">
			  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="clearForm();" onkeypress="if(event.keyCode==13) clearForm();">
			</his:statusDone>
			
			<his:statusUnsuccessfull>
				 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('CANCEL');" onkeypress="if(event.keyCode==13) submitPage('CANCEL');">
			</his:statusUnsuccessfull>
		</his:ButtonToolBarTag>	
		
		<html:hidden name="caseSheetReceivedFB" property="hmode"/>
		<html:hidden name="caseSheetReceivedFB" property="index"/>
		<html:hidden name="caseSheetReceivedFB" property="patCrNo"/>
		<html:hidden name="caseSheetReceivedFB" property="recordId"/>
		<html:hidden name="caseSheetReceivedFB" property="currentPage"/>
		
	</html:form>
	<his:status/>
	</his:TransactionContainer>
</body>
</html>

