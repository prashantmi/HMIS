<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@page import="registration.config.RegistrationConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
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

<title>Barcode Generation</title>



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
.no-close .ui-dialog-titlebar-close {display: none; }
</style>
<script type="text/javascript">

$(window).on("load.loading1", function() {

		 var data=document.getElementsByName("printHtml")[0].value;
		var elem = document.getElementById("divPrintId");
		elem.innerHTML= data;
	
if(document.getElementsByName("afterGo")[0].value!='0')
{  
	document.getElementById("divAfterGo").style.display = "";
	document.getElementById("divAfterGoId").style.display ="";
	document.getElementById("divGoId").style.display = "none";
	barcodeGeneration.submitByChoice('6');	 //duplicate by default
}


var isPrintFlag	   = document.getElementsByName("isPrintFlag")[0].value
var choice	   	   = document.getElementsByName("isChoiceBack")[0].value

if(isPrintFlag=="1"){
	if(choice!='1') 
	get_object("divBarCodeControl").innerHTML=DrawCode39Barcode(get_object("divBarCodeControl").innerHTML, 0);
	 window.print();
	 document.getElementsByName("isPrintFlag")[0].value="0";
 }else{
	  $("#divPrintId").html("");
 }
 //alert("saveConfirmFlag: "+document.getElementsByName("saveConfirmFlag")[0].value);
 
 if(document.getElementsByName("saveConfirmFlag")[0].value =="1")
 {
	var url="/HISRegistration/registration/transactions/PRINTBarcodeGeneration.action";
	document.getElementsByName("saveConfirmFlag")[0].value="";
	document.getElementsByName("barCodeGenSize")[0].value="";
	openPopuper(url,180,500);
 }

});
function openPopuper(url,height, width)
{
   	
   	var child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
  	child.moveTo(250,250);
 	child.focus(); 
	if(!child.opener)
   		child.opener = self;
  	return child; 
  
}
function openPopuper_forDialogModal(url,height, width)
{
   	/* 
   	var child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
  	child.moveTo(250,250);
 	child.focus(); 
	if(!child.opener)
   		child.opener = self;
		//alert("done");
  	return child; 
  	*/

  	var windowWidth = 400;// $(window).width() - 50;
	var windowHeight = 250;

	var page = url;//"/HISRegistration/registration/transactions/PRINTBarcodeGeneration.action";
	var $dialog = $('<div></div>').dialog({autoOpen : false});
	$dialog.dialog('close');
	$dialog = $('<div></div>')
			.html(
					'<iframe id="barcodeDialog_iframe" style="border: 0px; " src="" width="100%" height="100%"></iframe>')
			.dialog(
					{
						autoOpen : false,
						modal : true,
						height : windowHeight,
						width : windowWidth,
						title : "Duplicate Patient Barcode Slip",
						/* show : {
							effect : "clip"
						}, */
						resizable : true,
						position : [ 'top', 100 ],
						dialogClass : 'no-close custbtncolor',
						buttons : {
							/* "Print" : function() {
								//$('#bc_cdac').html("");
								//$('#bc_cdac').dialog("destroy").remove();
								window.print() ;
								$("#barcodeDialog_iframe").attr('src', "");
								$(this).dialog("close");
								$(this).remove();//.remove();
							},
								 */	"Close" : function() {
									//$('#bc_cdac').html("");
									//$('#bc_cdac').dialog("destroy").remove();
									//window.print() ;
									$("#barcodeDialog_iframe").attr('src', "");
									$(this).dialog("close");
									$(this).remove();//.remove();
							}
						},
						open : function() {
							$('.ui-widget-overlay').addClass(
									'custoverlay');
							$('.ui-dialog-buttonpane')
									.find(
											'button:contains("Close")')
									.addClass('no-close custbtncolor');
							$('.ui-dialog-buttonpane')
									.find(
											'button:contains("Print")')
									.addClass('no-close custbtncolor');
						}, 
						close : function() {
							$(".ui-widget-overlay")
									.removeClass('custoverlay');
							$("#barcodeDialog_iframe").attr('src', "");
							$(this).dialog("close");
							$(this).remove();
						}
					});
	$("#barcodeDialog_iframe").attr('src', page);
	$dialog.dialog('open');
}

	function submitClear(cnt) {
		document.forms[0].action =  cnt + ".action";
		document.forms[0].submit();
	}
	function submitForm(mode) {
		document.forms[0].action = mode + "BarcodeGeneration.action";
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
		document.forms[0].action = mode + "BarcodeGeneration.action?crno="+obj.value;
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
		<s:form action="BarcodeGeneration">
			<s:set name="theme" value="simple" scope="page" />
			<div class="wrapper rounded" id="nonprintableDiv1">
				<div class="div-table">
					<div class="div-table-row ">
						<div class="div-table-col title width100 ">
						<s:text name="barcode"/>&nbsp;<s:text name="generation"/></div>
						
					</div>
				</div>

				<his:InputCrNoTag />
		
		<div id="divAfterGo" style="display: none">

			<s:if test="%{afterGo!=0}">
				<his:PatientTileTag crNo="${patCrNo}" ></his:PatientTileTag>
			
					<div class="div-table">
						<div class="div-table-row separatorBar">
							<div class="div-table-col width100"></div>
						</div>
					</div>
					
					<div class="div-table"  id="divEpisodeId"></div>
			</s:if>
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
		 		<a class="button" id="submitId" href="#" ><span class="print">Print</span></a>
				<a class="button" id="clearId" onclick="submitClearAction('BarcodeGeneration');"><span class="clear"><s:text name="clear" /></span></a> 
				<a class="button" href="#" onclick="submitCancelAction('BarcodeGeneration');"><span class="cancel"><s:text name="cancel" /></span></a>
			</div> 
			 <div id="divGoId" >  
				<a class="button" id="#"  onclick="submitClear('BarcodeGeneration');"><span class="clear"><s:text name="clear" /></span></a> 
				<a class="button" href="#" onclick="callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel"><s:text name="cancel" /></span></a>
			</div>
			</div>
		</div>

<s:hidden name="afterGo" value="%{tempAfterGo}" />
<s:hidden id="printHtmlId" name="printHtml" value="%{printHtmlTemp}"/>
<s:hidden name="isPrintFlag" value="%{isPrintFlag}" />
<s:hidden name="isChoiceBack" value="%{isChoiceBack}" />
<s:hidden name="saveConfirmFlag" value="%{saveConfirmFlag}"/>
<s:hidden name="barCodeGenSize" value="%{barCodeGenSize}" />
<s:hidden name="crNo" value="%{patCrNo}"/>
			
			</div>
			<div class="div-table" id="nonprintableDiv3">
				<div class="div-table-row ">
					<div class="div-table-col"> 
						<div class="div-table-col alignLeft fontError" style="width: 100%"><s:property value="errorMessage" /></div>
					</div>
				</div>
			</div>
		<div id="divPrintId"></div>
		<cmbPers:cmbPers></cmbPers:cmbPers>
		<s:token/>
		
		</s:form>
			
		<script type="text/javascript" src="./../../registration/transactions/js/barcodeGeneration.js" /></script>
	</center>
</body>
</html>