<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="org.apache.struts.tiles.ComponentContext"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>

<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/HISClinical/hisglobal/css/layout.css" rel="stylesheet" type="text/css">

<his:css src="/hisglobal/css/calendar-blue2.css" />
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">

<link rel="stylesheet" href="/HISClinical/hisglobal/css/basic.css">
<!-- <link rel="stylesheet" href="/HISClinical/hisglobal/js/dojotoolkit/dojo/resources/dojo.css"/> -->
<link rel="stylesheet" href="/HISClinical/hisglobal/js/dojotoolkit/dijit/themes/tundra/tundra.css"/>

<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/js/basic.js'></script> 
<script type="text/javascript" src="/HIS/hisglobal/js/hashFunctions.js"></script>

<%-- <his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/> --%>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/utility/dynamicdesk/js/desk.js" />
<script type='text/javascript' src='/HIS/hisglobal/js/dynamicdesk/dynamicDeskPanelGenerator.js'></script>
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js"/> 
<his:javascript src="/hisglobal/js/commonFunctions.js"/> 

<his:javascript src="/hisglobal/js/validationCalls.js"/>
<his:javascript src="/hisglobal/js/validationCommon.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar-setup.js"/> 
<his:javascript src="/registration/js/registration.js"/>

<his:javascript src="/inpatient/js/nursingDesk.js" />


<style type="text/css">
.border .div-table-col{
border: 1px solid black;
}


.modal-box {
	display: none;
	position: absolute;
	z-index: 1000;
	width: 98%;
	background: white;
	border-bottom: 1px solid #aaa;
	border-radius: 4px;
	box-shadow: 0 3px 9px rgba(0, 0, 0, 0.5);
	border: 1px solid rgba(0, 0, 0, 0.1);
	background-clip: padding-box;
}

.modal-box{
	padding: 1em 1em;
	border-bottom: 1px solid #ddd;
}



.modal-box {
	padding: 1em 1em;
}

.modal-box footer, .modal-box .modal-footer {
	padding: 1em;
	border-top: 1px solid #ddd;
	background: rgba(0, 0, 0, 0.02);
	text-align: right;
}

.modal-overlay {
	opacity: 0;
	filter: alpha(opacity = 0);
	position: absolute;
	top: 20;
	left: 0;
	z-index: 900;
	width: 100%;
	height: 100%;
	background: rgb(12, 1, 1) !important;
}

a.close {
	line-height: 1;
	font-size: 1.5em;
	position: absolute;
	top: 5%;
	right: 2%;
	text-decoration: none;
	color: #bbb;
}

a.close:hover {
	color: #222;
	-webkit-transition: color 1s ease;
	-moz-transition: color 1s ease;
	transition: color 1s ease;
}

@media ( min-width : 32em) {
	.modal-box {
		width: 70%;
	}
}

</style>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type='text/javascript'>

var arrPatGlobalJsonObj=[];
var alreadyRegisteredFlag1 = "0";



function cancelLoginPopup(){
	///parent.parent.closeModal();
	parent.closeModal();
}


function tagetURL(){
	//alert(document.getElementsByName("targetMode")[0].value);
	
	//alert(menuid,selectinfo,deskmode);
	if(document.getElementsByName("targetMode")[0].value=="VALIDROLE")
	{
		
		//alert(document.forms[0].menuId.value);
	 //	alert(document.forms[0].deskMode.value);
	 	//alert(document.forms[0].deskInfo.value);
	 	//alert(document.forms[0].deskTitle.value);
	 	var selectinfo=document.forms[0].deskInfo.value;
	    var a=/,/gi;
	 	selectinfo=selectinfo.replace(a,"&");
	 //	alert(selectinfo);
		
		var menuid=document.forms[0].deskMenuId.value;
		var deskmode=document.forms[0].deskMode.value;
		var title=document.forms[0].deskTitle.value;
		//parent.addTab('Patient List','DESKPATLIST','0','-1');
	//	addTab(title, url, deskMenuId, deskMenuType, flgRefresh, deskSelectInfo, deskUrlType, deskExternalMenuURL, isLoginBound, dutyRoleId)
		parent.addTab(title,deskmode,menuid,1,false,selectinfo,-1,'',0,'');
		//parent.closeModal();	
		/* targetURL = "/HISClinical/hisglobal/utility/dynamicdesk/centerNew.cnt?mode="+url+"&deskMenuId="+deskMenuId+"&hmode=NEW"+deskSelectInfo;
		var content = '<iframe scrolling="yes" frameborder="0" src="'+targetURL+'" style="width:100%;height:'+height1+'; margin-top:2px;"></iframe>';
		patDeskRefreshFlag = "1"; //Making this flag 1 bec while opening a new Tab this file will reload & flag becomes 0.
	 */

		
	
	
	}
	
}



function ajaxCallForPopup(obj1,obj2)
{
	if(arrPatGlobalJsonObj=="")
	{
	var action 	= "/HISClinical/registration/transactions/getPatDtlOnPatMobileNewRegistration.action?"+
	"searchId="+obj1+"&searchValue="+obj2;
	$.ajax({url: createFHashAjaxQuery(action),type:"POST",async:true,dataType:"json" ,success:function(data){
		arrPatGlobalJsonObj=data;
		createPatientEmpRow(data);	
			
	},error: function(errorMsg,textstatus,errorthrown) {
        alert('go '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
        
    }
	});
	}
else
	{
	
	 var arrPatJsonObj=[];
	 var isRecordFound=false;
	if(obj1=="0")
	{
	createPatientEmpRow(arrPatGlobalJsonObj);	
	}
	else
		{
		 for ( var i = 0; i < arrPatGlobalJsonObj.length; i++) {
		 var firstName=(arrPatGlobalJsonObj[i].patFirstName).toUpperCase();
		 if ((obj1=="1" && arrPatGlobalJsonObj[i].patAddMobileNo == obj2) ||(obj1=="2" && (firstName.indexOf(obj2.toUpperCase())> -1)) ||(obj1=="3" && arrPatGlobalJsonObj[i].patCrNo == obj2)) 
		 {	
		      isRecordFound=true;
			  arrPatJsonObj[i]=arrPatGlobalJsonObj[i];
			  createPatientEmpRow(arrPatJsonObj);
		 } 
		
	 } 
	 
	if(isRecordFound==false)
	{
	createPatientEmpRow("");
	}
	}
	}
}

function go(){
	
	var searchId=$('[name="searchId"]')[0].value;
	
	if(searchId==1)
		var searchValue=$('[name="searchMobile"]')[0].value;
	else if(searchId==2)
		var searchValue=$('[name="searchName"]')[0].value;
	else
		var searchValue=$('[name="searchReference"]')[0].value;
	
	if(searchValue=="")
		{
		searchId=0;
		}
	ajaxCallForPopup(searchId,searchValue);
	
	
}

/* $('.simplemodal-close').click(function(){
	//alert("hello");
}); 
  */
 
 function changeSearchField(obj)
 {
	 var searchId=obj.value;
	 document.getElementById("searchMobileId").value ="";
	 document.getElementById("searchNameId").value = "";
	 document.getElementById("searchReferenceId").value="";
	
	 if(searchId=="1")
	 	{
		 document.getElementById("searchMobileId").style.display = "";
		 document.getElementById("searchNameId").style.display = "none";
		 document.getElementById("searchReferenceId").style.display = "none";
		 }
	 else if(searchId=="2")
		 {
		 
		 document.getElementById("searchNameId").style.display = "";
		 document.getElementById("searchReferenceId").style.display = "none";
		 document.getElementById("searchMobileId").style.display = "none";
		
		 }
	 else if(searchId=="3")
		 {
		 document.getElementById("searchReferenceId").style.display = "";
		 document.getElementById("searchMobileId").style.display = "none";
		 document.getElementById("searchNameId").style.display = "none";
		 }
 }
 
/* function cancelPopup(){
	var alreadyRegisteredFlag = $('[name="alreadyRegisteredFlag"]')[0].value;
	if(parseInt(alreadyRegisteredFlag)> 0){
		if(parent.document.getElementsByName("hiddenPatIdNo")[0].value == "")
			parent.document.getElementsByName("alreadyRegisteredFlag")[0].checked=false;
	}
	parent.closeModal(); 
}*/

function callOpenerFetchPatDtlBasedOnPatCatCardNo(patIndexId){

	$("#patAgeId",parent.document).val($('[name="patAge"]')[patIndexId.value].value);
	parent.document.getElementsByName("hiddenPatIdNo")[0].value=$('[name="patIdNo"]')[patIndexId.value].value;
	parent.document.getElementsByName("patAgeUnit")[0].value=$('[name="patAgeUnit"]')[patIndexId.value].value;
	window.parent.opdregistration.fetchPatDtlBasedOnPatCatCardNo(arrPatGlobalJsonObj[patIndexId.value]);
	//closeModal();
	parent.document.getElementsByName("searchUsingMobile")[0].checked=false;
	parent.closeModal();
}



function createPatientEmpRow(arrStrPatJsonObj)
{
	var empHeaderRow =	"<div class='div-table-row title' style='background:'blue''>"+
							"<div class='div-table-col width10 alignCenter'>Select</div>"+
							"<div class='div-table-col width20 alignCenter'>Name</div>"+
							//"<div class='div-table-col width20 alignCenter'>Id</div>"+
							"<div class='div-table-col width20 alignCenter'>Gender</div>"+
							"<div class='div-table-col width10 alignCenter'>Age</div>"+
							"<div class='div-table-col width20 alignCenter'>Reference No</div>"+
							"<div class='div-table-col width20 alignCenter'>Cr No.</div>"+
						"</div>";
	var empDtlRow="";
	//var arrPatJsonObj = eval('(' + arrStrPatJsonObj + ')');
	var arrPatJsonObj = arrStrPatJsonObj;
	var varId="";
	if(arrPatJsonObj!="")
	{
	for (i in arrPatJsonObj)
	{
		var disabled="";
		 if(arrPatJsonObj[i]["patRegistered"]=="1" ){
			disabled="disabled='disabled'";
		}
		if(parseInt($('[name="alreadyRegisteredFlag"]')[0].value)> 0){
			varId=arrPatJsonObj[i]["prevCrNo"];
		}else{
			varId=arrPatJsonObj[i]["patIdNo"];
		}
		
			var objPatAge = arrPatJsonObj[i]["patAgeWithUnit"].split(" ");
			var patAge	  =	objPatAge[0];
			var patAgeUnit= objPatAge[1];
			//alert("patCrNo :"+ arrPatJsonObj[i]["patCrNo"]);
			empDtlRow +=	"<div class='div-table-row listData'>"+
							"<div class='div-table-col width10 alignCenter'>"+
								"<input type='radio' name='patIndex' value='"+i+"' onclick='callOpenerFetchPatDtlBasedOnPatCatCardNo(this);'"+disabled+" />"+
								"<input type='hidden' name='patIdNo' value='"+arrPatJsonObj[i]["patIdNo"]+"' />"+
								"<input type='hidden' name='patAge' value='"+patAge+"' />"+
								"<input type='hidden' name='patAgeUnit' value='"+patAgeUnit+"' />"+
								"<input type='hidden' name='patRefNo' value='"+arrPatJsonObj[i]["patRefNo"]+"' />"+
							"</div>"+
							"<div class='div-table-col width20 alignCenter'>"+arrPatJsonObj[i]["patFirstName"]+" "+arrPatJsonObj[i]["patMiddleName"]+" "+arrPatJsonObj[i]["patLastName"]+ "</div>"+
							//"<div class='div-table-col width20 alignCenter'>"+varId+"</div>"+
							"<div class='div-table-col width20 alignCenter'>"+arrPatJsonObj[i]["patGenderCode"]+"</div>"+
							"<div class='div-table-col width10 alignCenter'>"+arrPatJsonObj[i]["patAgeWithUnit"]+"</div>"+
							"<div class='div-table-col width20 alignCenter'>"+arrPatJsonObj[i]["patRefNo"]+"</div>"+
							"<div class='div-table-col width20 alignCenter'>"+arrPatJsonObj[i]["patCrNo"]+"</div>"+
						"</div>";
	}
	}
	else{
		empDtlRow +=	"<div class='div-table-row listData'><b>No Record Found<b></div>";
	}
	$("#divCatDetilId").html(empHeaderRow+empDtlRow);
	
}


function validateNumeric(e)
{
	var key;
	var keychar;

	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);
	keychar = keychar.toLowerCase();

	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) )
	   return true;

	// numbers
	else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
}
function validateAlphabetsOnly(e)
{
	//alphabets only
		
	var key;
	var keychar;

	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);

	keychar = keychar.toUpperCase();
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32))
	   return true;
	
	if((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(keychar) > -1))
				return true;
	else
	   return false;
}
</script>

<%
	/* ComponentContext compContext = (ComponentContext)pageContext.getAttribute("org.apache.struts.taglib.tiles.CompContext", 2);
	String header=(String)compContext.getAttribute("header");
	String body=(String)compContext.getAttribute("body");
	String footer=(String)compContext.getAttribute("footer");
	
	 */
	String msg=(String)request.getAttribute("MESSAGE");
	String validUser=(String)request.getAttribute("VALID_USER");
	if(msg==null)		msg="";
	if(validUser==null)	validUser="";
%>

</head>

 <body onload="tagetURL()">

<!--  <body> -->


<html:form action="/validateUser">

<%-- <table width="100%">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									User Id
								</font>
							</div>
						</td>
						<td width="50%" class="tdfonthead">
							<div align="left">
								<select name="uid" id="userName" class="textbox">
									<option value='-1'>Select Employee</option>
									<%=session.getAttribute(DynamicDeskConfig.ESSENTIAL_BO_LOGIN_USER_LIST) %>
								</select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									Password
								</font>
							</div>
						</td>
						<td width="50%" class="tdfonthead">
							<div align="left">
								<input type="password" name="pwd" id="password"	maxlength="20" tabindex="1" onkeypress="finalSubmit(event)" >
							</div> 
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead"></td>
						<td width="50%" class="tdfonthead">
							<div align="left">
								<img class="button" tabindex="1" src="/HIS/hisglobal/images/GoNew.png" style="cursor: pointer; position: absolute;" onkeypress="finalSubmit(event)" onclick="finalSubmit(event)">		
							</div> 
						</td>
					</tr>
				</table> --%>
   <!--  <s:set name="theme" value="simple" scope="page" /> -->

    <!-- <div class="wrapper rounded">
	 -->    <!-- <h1>Patient Registration</h1> -->
	 
	 <div class="wrapper rounded">
			<div class="div-table">
			<div class="div-table-row ">
			<table style="width: 100%">
			<tr>
			<td width="50%" >
				<div id="divTitleId" class="div-table-col title width100 ">
						Login
				</div>
			</td></tr></table>
			</div>
			
			
			
			<div class="div-table-row separatorBar ">
					<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row emptyBar ">
					<div class="div-table-col"> </div> 
			</div>
			
			<table style="width: 100%">
			<tr>
			<td width="50%" >
							<div align="right">
								<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									User Id
								</font>
							</div>
						</td>
			
			<td style="width: 35%">
			<select name="uid" id="userName" class="select100prcnt" >
									<option value='-1'>Select Employee</option>
									<%=session.getAttribute(DynamicDeskConfig.ESSENTIAL_BO_LOGIN_USER_LIST) %>
								</select>
				<!-- <select name="searchId" tabindex="1" class="select100prcnt" onchange="changeSearchField(this)">
						<option value="1">Mobile No.</option>
						<option value="2">First Name</option>
						<option value="3">Reference No</option>
				</select> -->
					
			</td>
			
			<tr>
						<td width="50%">
							<div align="right">
								<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									Password
								</font>
							</div>
						</td>
						<td width="50%" >
							<div align="left">
								<input type="password" name="pwd" id="password"	maxlength="20" tabindex="1" onkeypress="finalSubmit(event)" >
							</div> 
						</td>
					</tr>	
					<html:hidden name="ValidateUserFB" property="roleId" /> 
					<tr><td colspan="2">
						<div id="divCatDetilId" class="div-table-listing rounded"></div></td></tr>
					<tr>
					
					<td width="25%" >
					
							<div align="right">
							<img class="button" src="/../HIS/hisglobal/images/buttons/btn-ccl.png" tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelLoginPopup();" onclick="cancelLoginPopup()">
						</div> 
						</td>
					
						<td width="25%">
						<div align="left">
						<img class="button" tabindex="1" src="/HIS/hisglobal/images/GoNew.png" style="cursor: pointer;" onkeypress="finalSubmit(event)" onclick="finalSubmit(event)">		
							</div>
							
						</td>		
						
						</tr>
				<!-- <td style="width:55%;">
				
			
						<input type="text" name="searchMobile" id="searchMobileId"  tabindex="1" maxlength="10" onkeypress="return validateNumeric(event)" >
						
						<input type="text" name="searchName" id="searchNameId" tabindex="2"  onkeypress="return validateAlphabetsOnly(event)">
				
						<input type="text" name="searchReference" id="searchReferenceId" tabindex="2" maxlength="10" onkeypress="return validateNumeric(event)">
				
						<input type="button" name="Go" id="goId" tabindex="1"  value="Go" onclick="go();">
				
				</td> -->
				
			</table>
			</div>
	<div align="left" style="color: red;font-weight: bold;">
					<%=msg%>
				</div>
			<!-- <div id="divCatDetilId" class="div-table-listing rounded"></div>
			<div class="div-table-button">
			<div class="div-table-row footerBar">
					<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row emptyBar">
				<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row" align="center">
				<a href="#" class="button" id="cancelPopupId" onclick="cancelPopup();"><span class="cancel">Cancel</span></a>
			</div> -->
			
		
			<div class="div-table-button">
			<div class="div-table-row footerBar">
					<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row emptyBar">
				<div class="div-table-col"> </div>
			</div>
			<!-- <div class="div-table-row" align="center">
				<a href="#" class="button" id="cancelPopupId" onclick="cancelPopup();"><span class="cancel">Cancel</span></a>
			</div> -->
			
		</div>
			
		</div>
		<!-- </div>
		 -->
	<!-- </div> -->
	
			<html:hidden name="ValidateUserFB" property="mode" />
			<html:hidden name="ValidateUserFB" property="hmode" />
			 <input type="hidden" name="deskMenuId" value="<%=request.getParameter("deskMenuId")%>">
			  <input type="hidden" name="deskMode" value="<%=request.getParameter("deskMode")%>">
			 <input type="hidden" name="deskInfo" value="<%=request.getParameter("deskInfo")%>">
			<input type="hidden" name="deskTitle" value="<%=request.getParameter("deskTitle")%>">
		   <html:hidden name="ValidateUserFB" property="targetMode" /> 
			
			<%-- <html:hidden name="ValidateUserFB" property="transactionMode" /> --%>
		<%-- 	<html:hidden name="ValidateUserFB" property="roleId" />  --%>
			<%-- <input type="hidden" name="deskSelectInfo" value="<%=request.getParameter("deskSelectInfo")%>">
			 --%><html:hidden name="ValidateUserFB" property="menuRoleId" /> 
		  <%--   <input type="hidden" name="transactionMode" value="<%=request.getParameter("transactionMode")%>">
			<input type="hidden" name="menuMode" value="<%=request.getParameter("menuMode")%>">
				
			
			<html:hidden name="ValidateUserFB" property="processId" /> 
			 --%>
<%-- 	<html:hidden name="patPrimaryCatCode" value="%{patPrimaryCatCode}" />
	<html:hidden name="alreadyRegisteredFlag" value="%{alreadyRegisteredFlag}" />
	 --%>
	 
	     <%-- working    <input type="hidden" name="mode" value="" />
			<input type="hidden" name="hmode" value="" />
			<input type="hidden" name="deskMenuId" value="" />
			<input type="hidden" name="patCrNo" value="" />
			<input type="hidden" name="crNoSelected" value="" />
			<input type="hidden" name="enableBlanket" value="" />
			<input type="hidden" name="roleId" value="" />
			
			
			
			<html:hidden name="NursingDeskFB" property="enableBlanket" />			
			<input type="hidden" name="transactionMode" value="<%=request.getParameter("transactionMode")%>">
			<input type="hidden" name="menuMode" value="<%=request.getParameter("menuMode")%>">
			<input type="hidden" name="menuId" value="<%=request.getParameter("menuId")%>"> working--%>
	 
	   
			
		<%-- 	<html:hidden name="ValidateUserFB" property="patCrNo" />
			<html:hidden name="ValidateUserFB" property="crNoSelected" />
			 <%-- <html:hidden name="ValidateUserFB" property="enableBlanket" />		 --%>	
		
			
			
			
</html:form>
</body>
</html>
