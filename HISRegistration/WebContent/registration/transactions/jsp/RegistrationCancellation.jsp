<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@page import="registration.config.RegistrationConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@page%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/layout.css" rel="stylesheet"	type="text/css">
<link href="./../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">

<script language="JavaScript" type="text/javascript"
	src="../../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>

<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script>

<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>
<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>
<script type="text/javascript" src="../../hisglobal/js/barcode_code39.js"></script>

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<title>Registration Cancellation</title>
<s:head />


</head>
<style type="text/css">

@media print { 
		#nonprintableDiv1 
		{
		 display: none; 
		}
		#nonprintableDiv2 
		{
		 display: none; 
		}
		#nonprintableDiv3 
		{
		 display: none; 
		}
		
}

.border .div-table-col{
border: 1px solid black;
}
</style>
<script type="text/javascript">

$(window).on("load.loading1", function() {
		/* var data=document.getElementsByName("printHtml")[0].value;
		var elem = document.getElementById("divPrintId");
		elem.innerHTML= data; */
//alert(document.getElementsByName("afterGo")[0].value);
				
if(document.getElementsByName("afterGo")[0].value!='0')
{  
	document.getElementById("divAfterGo").style.display = "";
	document.getElementById("billPrint").style.display = "none";
	
	document.getElementById("divAfterGoId").style.display ="";
	document.getElementById("divGoId").style.display = "none";

	//alert("Before calling regcancl"+document.getElementsByName("afterGo")[0].value);
	regCancel.getEpisodes();	 //duplicate by default
}


 var isPrintFlag= document.getElementsByName("isPrintFlag")[0].value;
//alert(isPrintFlag);

 if(isPrintFlag=="1"){
	 //alert("2");
		//get_object("divBarCodeControl").innerHTML=DrawCode39Barcode(get_object("divBarCodeControl").innerHTML, 0);
	 window.print();
	 document.getElementsByName("isPrintFlag")[0].value="0";
 }else{
	  $("#divPrintId").html("");
 } 

});





function submitClear(cnt)
{
	document.forms[0].action =  cnt + ".action";
	document.forms[0].submit();
}

	function submitForm(mode) {
		document.forms[0].action = mode + "RegistrationCancellation.action";
		document.forms[0].submit();

	}
	function submitCancelAction(cnt) {
		document.forms[0].action = "cancel" + cnt + ".action";
		document.forms[0].submit();
	}
	function submitClearAction(cnt) {
		document.forms[0].action = "Clear" + cnt + ".action";
		document.forms[0].submit();
	}
	function submitFormonRadio(mode,obj) {
		document.forms[0].action = mode + "RegistrationCancellation.action?crno="+obj.value;
		document.forms[0].submit();

	}
	function populate(selectedarray)
	{
		var strHtml ="";
		var elem = document.getElementById("hiddenDivVerification");
		for(i=0; i<selectedarray.length; i++)
		{
			var arrayOfDocsData=selectedarray[i].split("|");
	  	  
			strHtml+="<input type='hidden' name='arrSelectedVerifyDocs' value='"+selectedarray[i]+"'/>	";
			strHtml+=arrayOfDocsData[1]+"&nbsp; &nbsp;";
		}
		elem.innerHTML= ":: &nbsp;" + strHtml;
		
	}  
	
</script>
<body>
	<center>
		<s:form action="RegistrationCancellation">
			<%-- <s:set name="theme" value="%{myTheme}" scope="page" /> --%>
			<s:set name="theme" value="simple" scope="page" />
			<div class="wrapper rounded " id="nonprintableDiv1">

				<div class="div-table">
					<div class="div-table-row ">
						<div class="div-table-col title width100 ">
						Registration Cancellation</div>
						
					</div>
				</div>

	<his:InputCrNoTag />
		
	<div id="divAfterGo" style="display: none"> 
	<!--By Mukund on 21.12.2016  -->
	<s:if test="%{afterGo!=0}">
	<%-- <s:include value="/registration/transactions/jsp/patientDetailsTile.jsp"/> --%>
	<his:PatientTileTag crNo="${patCrNo }" ></his:PatientTileTag>
			<div class="div-table">
				<div class="div-table-row separatorBar">
						<div class="div-table-col width100"></div>
					</div>
										
				</div>
				
				<div class="div-table"  id="divEpisodeId">
				</div></s:if>
				</div>
				
				
				<div class="div-table-button">
					<div class="div-table-row footerBar">
						<div class="div-table-col"></div>
					</div>
					<div class="div-table-row emptyBar">
						<div class="div-table-col"></div>
					</div>
					
					<div class="div-table-row" align="center">
					 <div id="divAfterGoId" style="display:none">  
				 	<a href="#" class="button" id="submitId"><span
								class="save">Save</span></a>
								<a id="clearId" class="button"	onclick="submitClearAction('RegistrationCancellation');"><span class="clear">Clear</span></a> 
						<a href="#" class="button" onclick="submitCancelAction('RegistrationCancellation');"><span class="cancel">Cancel</span></a>
					</div> 
					 <div id="divGoId" >  
						<a id="#" class="button"	onclick="submitClear('RegistrationCancellation');"><span class="clear">Clear</span></a> 
						<a href="#" class="button" onclick="callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel">Cancel</span></a>
					</div>
					</div>
				</div>





<s:hidden name="afterGo" value="%{tempAfterGo}" />
 <s:hidden id="printHtmlId" name="printHtml" value="%{printHtmlTemp}"/>
<s:hidden name="isPrintFlag" value="%{isPrintFlag}" />
<%-- <s:hidden name="isChoiceBack" value="%{isChoiceBack}" />  --%>



</div>
<div class="div-table" id="nonprintableDiv1">
				<div class="div-table-row ">
					<div class="div-table-col"> 
						<div class="div-table-col alignLeft fontError" style="width: 100%"><s:property value="errorMessage" /></div>
					</div>
				</div>
</div>
<div id="divPrintId">
<div id="billPrint">
<%
   String str=(String)session.getAttribute("billReceiptString");
   if(str==null)
	   str="";
%>
<%=str%>
</div>
</div>
<cmbPers:cmbPers></cmbPers:cmbPers>
	<s:token/>
		</s:form>
			
		<script type="text/javascript"
	src="./../../registration/transactions/js/RegistrationCancellation.js" /></script>
	</center>
</body>
</html>