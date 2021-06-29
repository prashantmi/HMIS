<!-- 
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: PUNEET SINGH KHURANA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : Film Used
 ## Purpose						        : Film No Entry
 ## Date of Creation					: 25/04/2016
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 


 -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
<%@page import="java.awt.BorderLayout"%>
<%@page import="java.awt.TextArea"%>
<%@page import="java.awt.Frame"%>
<%@page import="java.awt.Color"%>
<%@page import="javax.swing.JTextArea"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page import="new_investigation.vo.RequisitionListVO"%>
<%@page import="new_investigation.vo.filmUsedVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page import="new_investigation.transactions.controller.fb.filmUsedFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="hisglobal.utility.Entry"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="new_investigation.transactions.controller.fb.filmUsedFB"%>
<%@page import="new_investigation.transactions.controller.fb.filmUsedFB"%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:javascript src="/hisglobal/js/css-pop-inv.js" />
<his:css src="/hisglobal/css/icon.css" />
<his:css src="/hisglobal/css/email.css" />
<his:css src="/hisglobal/css/textboxcss.css" />
<his:css src="/hisglobal/css/drop.css" />
<link rel="stylesheet" href="/new_investigation/css/Date/site-demos.css">
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/bloodbank/js/bloodRequisition.js" />
<his:javascript src="/new_investigation/js/reportsValidation.js" />
<his:javascript src="/new_investigation/js/onlinePatientAcceptance.js" />
<his:javascript src="/hisglobal/js/jquery-1.11.1.min.js" />
<his:javascript src="/hisglobal/js/additional-methods.min.js" />
<his:javascript src="/new_investigation/js/jquery.validate.email.js" />
<his:javascript src="/new_investigation/js/additional-methods.min.js" />
<script src="media/misc/datepicker1.js" type="text/javascript"></script>
<script type="text/javascript"
	src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js"
	djConfig="parseOnLoad: true"> </script>
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
	
	
	
	<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<%-- <his:css src="/hisglobal/css/easyui.css" /> --%>  <!--  calender problem -->
<his:css src="/hisglobal/css/icon.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.autocomplete.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.menu.css" />
<his:css src="/hisglobal/css/jquery-ui.css" />
<his:css src="/hisglobal/css/style.css" />
<his:css src="/hisglobal/css/jquery/bookMark.css" />
<his:css src="/hisglobal/css/jquery/search.css" />
<his:css src="/hisglobal/css/InvBookMark.css" />
<his:css src="/hisglobal/css/invPopup.css" />

<style> 


  #disable:hover{
  	background-color: yellow;
  }
</style>
<!--styleTextBox.css  newHisRadioStyle.css
 -->

<script>



function showLegends1(){
	  document.getElementById("divLegends1").style.display="block"; 
}
function showLegendsNone1(){
document.getElementById("divLegends1").style.display="none";
}

$(function() {

	  $("#dialog1").dialog({
	     autoOpen: false,
	     modal: true,
	     width: 1200,
	        height: 200
	   }).prev(".ui-dialog-titlebar").css("color","yellow");

	  $("#myButton").on("click", function(e) {
	      e.preventDefault();
	      $("#dialog1").dialog("open");
	  });

	});
	
// just for the demos, avoids form submit
jQuery.validator.setDefaults({
debug: true,
success: "valid"
});
$( "#myform" ).validate({
rules: {
emailId: {
 
email: true
}
}
});
</script>
<script type="text/javascript">
function openPopup(url, eventObj, height, width) {
	if (eventObj.type == "click" || eventObj.keyCode == 13) {
		child = window.open(url, 'popupWindow',
				'status=yes,scrollbars=yes,height=' + height + ',width='
						+ width + ',left=10,top=10');
		child.moveTo(250, 250);
		child.focus();

		if (!child.opener)
			child.opener = self;
	}
}
<%List<String> batchDetailsValues = (List<String>) session.getAttribute(InvestigationConfig.LIST_STRING_BATCH_DETAILS);%>

//Function for displaying selected Lab List
function validateTodayOrDate()
{
	success=false;        	   
    
    valFromDate=document.getElementsByName('fromDate')[0];
	valToDate=document.getElementsByName('toDate')[0];
	
	<%String systemdate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");%>
	document.getElementsByName('sysDate')[0].value="<%=systemdate%>";
		valSysDate = document.getElementsByName('sysDate')[0];

		if (compareDateCall(valFromDate, valToDate, 2, "From Date", "To Date")
				&& compareDateCall(valToDate, valSysDate, 2, "To Date",
						"System Date")) {
			success = true;
		}
		return success;
	}
	function onClickGO(hospitalCode) {

		if (!validateTodayOrDate()) {
			return false;
		}

		var crno = document.getElementsByName("tempPatCrNo")[0].value;
		var textLength = crno.length;
		var hosCodeLen = hospitalCode.length;

		if (hosCodeLen == 3) {
			if (textLength == 5 || textLength == 13 || textLength == 0) {
				document.getElementsByName("patCrNo")[0].value = "";
				if (textLength == 13) {
					document.getElementsByName("patCrNo")[0].value = crno;
				}
				if (document.getElementsByName("labCode")[0].value == "-1") {
					alert("Select Lab   Name ... ");
					document.getElementsByName("labCode")[0].focus();
					return false;
				}
				document.getElementsByName('hmode')[0].value = "GETDETAILS";
				document.forms[0].submit();

			}

			else {
				alert("InValid CR No");
				if (document.getElementsByName("tempPatCrNo")[0]) {
					document.getElementsByName("tempPatCrNo")[0].focus()
				}

			}
		}
		if (hosCodeLen == 5) {
			if (textLength == 7 || textLength == 15 || textLength == 0) {
				document.getElementsByName("patCrNo")[0].value = "";
				if (textLength == 15) {
					document.getElementsByName("patCrNo")[0].value = crno;
				}
				if (document.getElementsByName("labCode")[0].value == "-1") {
					alert("Select Lab Name");

					document.getElementsByName("labCode")[0].focus();
					return false;
				}

				document.getElementsByName('hmode')[0].value = "GETDETAILS";
				document.forms[0].submit();

			}

			else {
				alert("InValid CR No");
				if (document.getElementsByName("tempPatCrNo")[0]) {
					document.getElementsByName("tempPatCrNo")[0].focus()
				}

			}
		}
		return true;
	}

	function validateMinLength(elem, minlen) {
		var isValid = true;
		if (elem)
			value = elem.value;
		else
			value = "";

		if ((value.length < minlen)) {
			isValid = false;
		}
		return isValid;

	}

	function doPagination(e, p) {
		document.getElementsByName('currentPage')[0].value = p;
		document.getElementsByName('hmode')[0].value = 'PAGINATION';
		document.forms[0].submit();
	}





	function ValidateSameCrNo(obj) {

		if (obj.checked) {
			document.getElementById('nextDiv').style.display = "";

		} else {
			document.getElementById('gob').style.display = "";
			document.getElementById('cancel').style.display = "";

		}

		var objCurrentCheckBox = obj.value;
		//alert(obj.checked);
		if (obj.checked) {

			var cbs = document.getElementsByTagName('input');
			for (var i = 0; i < cbs.length; i++) {
				if (cbs[i].type == 'checkbox') {

					if (cbs[i].checked
							&& (cbs[i].value.split("#")[0] != objCurrentCheckBox
									.split("#")[0])) {

						alert("please select same cr no");
						document.getElementById('nextDiv').style.display = "none";
						obj.checked = false;
						return false;
					}
				}
			}
		}

	}

	function submitFor() {
		
		document.getElementsByName('currentPage')[0].value = '1';
		document.getElementsByName('labCode')[0].value = '%';
		document.getElementsByName('showStatus')[0].value = '0';
		document.getElementsByName('hmode')[0].value = 'NEW';
		document.forms[0].submit();
	}

	function cancelFunc() {
		window.parent.closeTab();
	}

	function displaySamplePatDetails() {

		document.getElementsByName('showStatus')[0].value = '1';
		var count = 0;
		document.getElementsByName('isPatDetailPage')[0].value = "1";
		var concatenateChkBoxVal = "";
		//var cbs = document.getElementsByTagName('input');
		var cbs = document.getElementsByName('chkSamplePatient');
		for (var i = 0; i < cbs.length; i++) {
			// if(cbs[i].type == 'checkbox') 
			//{

			if (cbs[i].checked) {

				count++;
				concatenateChkBoxVal = concatenateChkBoxVal
						.concat(cbs[i].value);
				concatenateChkBoxVal += '@';
			}
			//}

		}

		if (count == 0) {
			alert("please select a record");
			return false;
		}

		document.getElementsByName('selectedCheckbox')[0].value = concatenateChkBoxVal;
		document.getElementsByName('hmode')[0].value = 'SHOWPATDETAILS';
		document.forms[0].submit();
	}

	function handleResponse() {
		//alert("inside Response");
		if (request.readyState == 4) {
			if (request.status == 200) {
				//alert("response sucessfull");
			} else
				alert("A problem occurred with communicating between the XMLHttpRequest object and the server program.");
		}
	}

	function showLegends() {
		document.getElementById("divLegends").style.display = "block";
	}
	function showLegendsNone() {
		document.getElementById("divLegends").style.display = "none";
	}

	function onChane(obj, k) {

		if (obj.value == '0') {

			//alert(obj.value);
			//alert(k);
			document.getElementById("showthis").style.display = "";
			document.getElementById("showthi_" + k).style.display = "";
			document.getElementById("showth_" + k).style.display = "";
			document.getElementById("showthi").style.display = "";

		} else {
			//alert(obj.value);

			document.getElementById("showthi_" + k).style.display = "none";

			document.getElementById("showth_" + k).style.display = "none";

		}
		if (k == null) {
			document.getElementById("showthis").style.display = "none";
			document.getElementById("showthi").style.display = "none";

		}
	}

	function onClickSave(obj, k) {
		if (obj.checked) {
			document.getElementById("saveDiv").style.display = "";
			//     document.getElementById('gob').style.display="none";
		} else {
			document.getElementById("saveDiv").style.display = "none";

		}

		//alert(document.getElementById(k+"chkBox").value);

		var cbs = document.getElementById(k + "chkBox").value.split("#");

		var labCode = cbs[3];
		var testCode = cbs[6];
		var testName = cbs[5];
		var config = cbs[7];
		var labName = cbs[8];

		if (config == 2) {
			var autoFormate = CheckAutoLabNoFormate(labCode, testCode, config)
			//alert(autoFormate);
			var autoForm = autoFormate.split("#");
			//alert(autoForm[0]);
			if (autoFormate == "null" || autoForm[0] == '-') {

				alert("No Lab Number Formate Is Configured For    "
						+ testName
						+ "("
						+ labName
						+ ")   Please Configure From Lab Number Configuration Master ");
				document.getElementById(k + "chkBox").checked = false;

				//document.getElementById(indexWithSubIndex+"unCheck").checked = false;
			} else {
				//document.getElementById(k+"chkBox").checked=true;

				var autoValue = "#";
				autoValue += autoFormate;
				//alert(autoValue);
				document.getElementById(k + "chkBox").value += autoValue;
			}
		} else {
			var autoValue = "#null#null#null#null#null#null#null#null#null#null#null";
			autoValue += autoFormate;
			//alert(autoValue);
			document.getElementById(k + "chkBox").value += autoValue;
			//document.getElementById(k+"chkBox").checked=true;
		}

		/* var cbs =document.getElementsByName('chkSamplePatientOnSave');
		// alert(cbs.length);
		for(var i=0; i < cbs.length; i++) {
				
			if(cbs[i].checked)
			{
			var id=cbs[i].id;
			//alert(id);
			var indexWithSubIndex=id.substring(0, 1);
			}   
		}
		 */
	}

	function onSave() {

		
		var i =0;
		
		for(i=0;i<document.getElementsByName("filmSize").length;i++)
			{
			
			if(document.getElementsByName("filmSize")[i].value=="-1")
				{
				alert("Select Film Size");
				document.getElementsByName("filmSize")[i].focus;
				return false;
				
				
				}
			
			
			
			}
		
		
		document.getElementsByName('showStatus')[0].value = '0';
		document.getElementsByName('hmode')[0].value = "SAVE";
		document.forms[0].submit();

		return true;

	}

// 	function setlabCode(obj) {
// 		var code = obj.value;
// 		//alert(code);
// 		//(obj.value);
// 		document.getElementsByName("labCode")[0].value = code;

// 	}

// 	function setCursor() {

// 		if (document.getElementsByName("tempPatCrNo")[0]) {
// 			document.getElementsByName("tempPatCrNo")[0].focus()
// 		}

// 	}

	//End AjaxFunctions
</script>


<script>


function AddRowToTable()
{ 
		document.getElementsByName("noOfFilms")[0].value=parseInt(document.getElementsByName("noOfFilms")[0].value)+1;
		document.getElementsByName("tempFilmNo")[0].value=parseInt(document.getElementsByName("tempFilmNo")[0].value)+1;
		var filmNoValue=document.getElementsByName("tempFilmNo")[0].value;
	    var nRow=0;
	  	var tableObj=document.getElementById('filmUsedDetails1');
	  	var numRows=tableObj.rows.length;
	  	
	  	
	  
	  		nRow=numRows;
	  	

	  	var tabRow=tableObj.insertRow(numRows);
	  	tabRow.id="film1"+parseInt(nRow)+1;

	  	var indexVolSpecific=numRows-1;
		var row=parseInt(indexVolSpecific)+1;
	  	var index=parseInt(numRows-1);
	  	

	  	var td1=document.createElement("TD");
	  	var td2=document.createElement("TD");
	  	var td3=document.createElement("TD");
	  	var td4=document.createElement("TD");
	  	var td5=document.createElement("TD");
	  	var td6=document.createElement("TD");
	 	var batch="batchNo"+row;
	 	var inv="inventory"+row;
	  	  	
		td1.innerHTML="<input type='text' name='filmNo' id='filmno"+(parseInt(indexVolSpecific)+1)+"'  value='"+filmNoValue+"'  />";
	  	td1.className='tdfont';
	  	td1.colspan="1";
	  	
	  	
	  	td2.innerHTML="<div align='left'><select name = 'filmSize' onchange='AddRowToTableBatchValue(this,\""+batch+"\",\""+inv+"\","+row+")' ><option value='-1'>Select Value</option>"+getFilmSizeList(row)+"</select></div>";
	  	td2.className='tdfont';
	  	td2.colspan="1";
	
		
	  	td3.innerHTML="";
	  	td3.className='tdfont';
	  	td3.colspan="1";
	  	
	  	td4.innerHTML="<input type='text' name='batchNo' id='batchNo"+(parseInt(indexVolSpecific)+1)+"'  onfocus='AddRowToTableBatchValueOnFocus(this,\""+batch+"\",\""+inv+"\","+row+")'/>";
	  	td4.className='tdfont';
	  	td4.colspan="1";
	  	
	  	td5.innerHTML="<input type='text' name='inventory' id='inventory"+(parseInt(indexVolSpecific)+1)+"'   />";
	  	td5.className='tdfont';
	  	td5.colspan="1";
	  	
	  	
   		  	
	  	td6.className='tdfont';
	  	td6.colspan="1";
	  	td6.innerHTML="<div align='left'><img src='/HISInvestigationG5/hisglobal/images/minus.gif' onClick='deleteRow("+	tabRow.id+")'></div>";
	  	
	  	tabRow.appendChild(td1);  
	  	tabRow.appendChild(td2);
	  	tabRow.appendChild(td3);
	  	tabRow.appendChild(td4);
	  	tabRow.appendChild(td5);
	  	tabRow.appendChild(td6);
	  	
	  	document.forms[0].numberOfRow.value=numRows;
}



function deleteRow(Str)
{	
	document.getElementsByName("noOfFilms")[0].value=parseInt(document.getElementsByName("noOfFilms")[0].value)-1;
	document.getElementsByName("tempFilmNo")[0].value=parseInt(document.getElementsByName("tempFilmNo")[0].value)-1;
	var filmNoValue=document.getElementsByName("tempFilmNo")[0].value;
	
	var numRows=parseInt(document.getElementsByName('numberOfRow')[0].value);
	var tableObj=document.getElementById('filmUsedDetails1');
    var temp=Str;
    tableObj.deleteRow(temp.rowIndex);
    document.forms[0].numberOfRow.value=(numRows-1);
        
    return true;
    
}


function AddRowToTableAdd()
{ 
	document.getElementsByName("noOfFilms")[0].value=parseInt(document.getElementsByName("noOfFilms")[0].value)+1;
	document.getElementsByName("tempFilmNo")[0].value=parseInt(document.getElementsByName("tempFilmNo")[0].value)+1;
	var filmNoValue=document.getElementsByName("tempFilmNo")[0].value;
	    var nRow=0;
	  	var tableObj=document.getElementById('filmUsedDetails2');
	  	var numRows=tableObj.rows.length;
	  	
	  	
	  
	  		nRow=numRows;
	  	

	  	var tabRow=tableObj.insertRow(numRows);
	  	tabRow.id="film2"+parseInt(nRow)+1;

	  	var indexVolSpecific=numRows-1;

	  	var index=parseInt(numRows-1);
	  	var row=parseInt(indexVolSpecific)+1;
		var batch="batchNoAdd"+row;
	 	var inv="inventoryAdd"+row;
	 	
	  	var td1=document.createElement("TD");
	  	var td2=document.createElement("TD");
	  	var td3=document.createElement("TD");
	  	var td4=document.createElement("TD");
	  	var td5=document.createElement("TD");
	  	var td6=document.createElement("TD");
	 	
	  	  	
		td1.innerHTML="<input type='text' name='additionalFilmNo' id='addfilmno"+(parseInt(indexVolSpecific)+1)+"'  value='"+filmNoValue+"'  />";
	  	td1.className='tdfont';
	  	td1.colspan="1";
	  	
	  	
	  	td2.innerHTML="<div align='left'><select name = 'filmSize' onchange='AddRowToTableBatchValue(this,\""+batch+"\",\""+inv+"\","+row+")' ><option value='-1'>Select Value</option>"+getFilmSizeList(row)+"</select></div>";
	  	td2.className='tdfont';
	  	td2.colspan="1";
	
		
		td3.innerHTML="<div align='left'>"+"<select name='filmReason'><option value = '1'>Over Exposure</option></div>";
	  	td3.className='tdfont';
	  	td3.colspan="1";
	  	
	 	td4.innerHTML="<input type='text' name='batchNo' id='batchNoAdd"+(parseInt(indexVolSpecific)+1)+"' onfocus='AddRowToTableBatchValueOnFocus(this,\""+batch+"\",\""+inv+"\","+row+")' />";
	  	td4.className='tdfont';
	  	td4.colspan="1";
	  	
	  	td5.innerHTML="<input type='text' name='inventory' id='inventoryAdd"+(parseInt(indexVolSpecific)+1)+"'   />";
	  	td5.className='tdfont';
	  	td5.colspan="1";
	  	
   		  	
	  	td6.className='tdfont';
	  	td6.colspan="1";
	  	td6.innerHTML="<div align='left'><img src='/HISInvestigationG5/hisglobal/images/minus.gif' onClick='deleteRowAdd(document.getElementById(\""+	tabRow.id+"\"))'></div>";
	  	
	  	tabRow.appendChild(td1);  
	  	tabRow.appendChild(td2);
	  	tabRow.appendChild(td3);
	  	tabRow.appendChild(td4);
	  	tabRow.appendChild(td5);
	  	tabRow.appendChild(td6);

	  	
	  	document.forms[0].numberOfRowAdd.value=numRows;
}



function deleteRowAdd(Str)
{	
    
	document.getElementsByName("noOfFilms")[0].value=parseInt(document.getElementsByName("noOfFilms")[0].value)-1;
	document.getElementsByName("tempFilmNo")[0].value=parseInt(document.getElementsByName("tempFilmNo")[0].value)-1;
	var filmNoValue=document.getElementsByName("tempFilmNo")[0].value;
	
	var numRows=parseInt(document.getElementsByName('numberOfRowAdd')[0].value);
	var tableObj=document.getElementById('filmUsedDetails2');
    var temp=Str;
 
    tableObj.deleteRow(temp.rowIndex);
    document.forms[0].numberOfRowAdd.value=(numRows-1);
        
    return true;
    
}





function AddRowToTableWaste()
{ 
	document.getElementsByName("noOfFilms")[0].value=parseInt(document.getElementsByName("noOfFilms")[0].value)+1;
	document.getElementsByName("tempFilmNo")[0].value=parseInt(document.getElementsByName("tempFilmNo")[0].value)+1;
	var filmNoValue=document.getElementsByName("tempFilmNo")[0].value;
	    var nRow=0;
	  	var tableObj=document.getElementById('filmUsedDetails3');
	  	var numRows=tableObj.rows.length;
	  	
	  	
	 
	  		nRow=numRows;
	  	

	  	var tabRow=tableObj.insertRow(numRows);
	  	tabRow.id="film3"+parseInt(nRow)+1;

	  	var indexVolSpecific=numRows-1;

	  	var index=parseInt(numRows-1);
		var row=parseInt(indexVolSpecific)+1;
		var batch="batchNoWaste"+row;
	 	var inv="inventoryWaste"+row;

	  	var td1=document.createElement("TD");
	  	var td2=document.createElement("TD");
	  	var td3=document.createElement("TD");
	  	var td4=document.createElement("TD");
	  	var td5=document.createElement("TD");
	  	var td6=document.createElement("TD");

	  	  	
		td1.innerHTML="<input type='text' name='wasteFilmNo' id='wastefilmno"+(parseInt(indexVolSpecific)+1)+"'  value='"+filmNoValue+"'  />";
	  	td1.className='tdfont';
	  	td1.colspan="1";
	  	
	  	
	  	td2.innerHTML="<div align='left'><select name = 'filmSize' onchange='AddRowToTableBatchValue(this,\""+batch+"\",\""+inv+"\","+row+")' ><option value='-1'>Select Value</option>"+getFilmSizeList(row)+"</select></div>";
	  	td2.className='tdfont';
	  	td2.colspan="1";
	
		
	  	td3.innerHTML="<div align='left'>"+"<select name='filmReasonWaste'><option value = '1'>Over Exposure</option></div>";
	  	td3.className='tdfont';
	  	td3.colspan="1";
	  	
	 	td4.innerHTML="<input type='text' name='batchNo' id='batchNoWaste"+(parseInt(indexVolSpecific)+1)+"' onfocus='AddRowToTableBatchValueOnFocus(this,\""+batch+"\",\""+inv+"\","+row+")' />";
	  	td4.className='tdfont';
	  	td4.colspan="1";
	  	
	  	td5.innerHTML="<input type='text' name='inventory' id='inventoryWaste"+(parseInt(indexVolSpecific)+1)+"'   />";
	  	td5.className='tdfont';
	  	td5.colspan="1";
	  	
   		  	
	  	td6.className='tdfont';
	  	td6.colspan="1";
	  	td6.innerHTML="<div align='left'><img src='/HISInvestigationG5/hisglobal/images/minus.gif' onClick='deleteRowWaste(document.getElementById(\""+	tabRow.id+"\"))'></div>";
	  	
	  	tabRow.appendChild(td1);  
	  	tabRow.appendChild(td2);
	  	tabRow.appendChild(td3);
	  	tabRow.appendChild(td4);
		tabRow.appendChild(td5);
	  	tabRow.appendChild(td6);
	  	
	  	document.forms[0].numberOfRowWaste.value=numRows;
}



function deleteRowWaste(Str)
{	
    
	document.getElementsByName("noOfFilms")[0].value=parseInt(document.getElementsByName("noOfFilms")[0].value)-1;
	document.getElementsByName("tempFilmNo")[0].value=parseInt(document.getElementsByName("tempFilmNo")[0].value)-1;
	var filmNoValue=document.getElementsByName("tempFilmNo")[0].value;
	
	var numRows=parseInt(document.getElementsByName('numberOfRowWaste')[0].value);
	var tableObj=document.getElementById('filmUsedDetails3');
    var temp=Str;
 
    tableObj.deleteRow(temp.rowIndex);
    document.forms[0].numberOfRowWaste.value=(numRows-1);
        
    return true;
    
}



function getBatchDetails(obj,batchId,invId,row)
{
	
	var sizeHashValue=obj.value;
	
	var itemId=sizeHashValue.split("#")[1];
	var storeId=sizeHashValue.split("#")[2];
	var j=0;
	var maxValue=0;
	var totalBatch=0;

	
	if(itemId!="-1" || storeId!="-1")
		{
<%

List<String> tempList=new ArrayList<String>();
if(batchDetailsValues!=null)
{
	
for(int i=0;i<batchDetailsValues.size();i++)

{String batchHash=batchDetailsValues.get(i);
%>

if(itemId==<%=batchHash.split("#")[1]%> && storeId==<%=batchHash.split("#")[2]%>)
	{
	
	document.getElementById(batchId).value="<%=batchHash.split("#")[0]%>";
	document.getElementById(invId).value="<%=batchHash.split("#")[3]%>";

	<%-- var updateValue="<%=batchHash.split("#")[0]+"#"+batchHash.split("#")[1]+"#"+batchHash.split("#")[2]+"#"%>";
	updateValue+=document.getElementById(invId).value-1; --%>
	 maxValue="<%=batchHash.split("#")[3]%>";
		
	for(j=0;j<document.getElementsByName("batchNo").length;j++)
		{
		
		
		
		
		 if(document.getElementsByName("batchNo")[j].value=="<%=batchHash.split("#")[0]%>")
		 {	 totalBatch++;
		 
			if(totalBatch>maxValue)
				{
				
				alert("No more Films available for this Film Size in the inventory. Please select different Film Size.");
				document.getElementsByName("filmSize")[j].value="-1";
				document.getElementById(batchId).value="";
				document.getElementById(invId).value="";
							
				}
		
		 
		 }
		}
	
	
	
	
	}
		
	<%
	}
	}
	%>
	
		}
	else
		{
		document.getElementById(batchId).value="-";
		document.getElementById(invId).value="-";
		}
	
	
	
	if(document.getElementsByName("filmSize")[j].value!="-1")
		{
		
		if(document.getElementById(batchId).value=="")
			document.getElementById(batchId).value="-";
		if(document.getElementById(invId).value=="")
			document.getElementById(invId).value="-";
		}
	
}




function closePopup()
{
	
	document.getElementById('batchField').style.display='none';
}



function AddRowToTableBatchValue(obj,id,sd,r)
{
	
	for(var z = document.getElementById("addBatchList").rows.length-1; z > 0; z--)
			{
				document.getElementById("addBatchList").deleteRow(z);
			
			}
	
	
	
	
	var sizeHashValue=obj.value;
	
	var itemId=sizeHashValue.split("#")[1];
	var storeId=sizeHashValue.split("#")[2];
	
	
	

<%

 	if(batchDetailsValues!=null)
 {
	
 for(int k=0;k<batchDetailsValues.size();k++)

 {
	
	
 	String batchHash=batchDetailsValues.get(k);
 	String batchName=batchHash.split("#")[0];
 	String batchItemId=batchHash.split("#")[1];
 	String batchStoreId=batchHash.split("#")[2];
 	String inventory=batchHash.split("#")[3];

 	%>

	
 	if(itemId==<%=batchItemId%> && storeId==<%=batchStoreId%>)

 {
 		document.getElementById('batchField').style.display='block';
 		
	var nRow=0;
 	var tableObj=document.getElementById('addBatchList');
	
 	var tr=document.createElement("TR");
	 
 	tr.setAttribute("id","setPdf");
 	tableObj.appendChild(tr);
 	var numRows=tableObj.rows.length;
 		nRow=numRows;
 
 	var tabRow=tableObj.insertRow(numRows);
 	tabRow.id=parseInt(nRow);
	 
 	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
 
 	td1.innerHTML="<div  align='left' ><input id='<%=batchHash%>' type='radio' name='chkBoxBatchValue' value='<%=batchHash%>' onclick='setBatchValue(this,\""+id+"\",\""+sd+"\","+r+")'   /></div>";   
 	td1.className='tdfont';
 	td1.width="1";
	
 	td2.innerHTML="<div align='left' ><%=batchName%></div>";   
 	td2.className='tdfont';
 	td2.colspan="1";
   
 	tabRow.appendChild(td1); 
 	tabRow.appendChild(td2);  
 
  
 
}
 	else
 	 	{
 		document.getElementById(id).value="";
 		document.getElementById(sd).value="";
 		

 	 	}
 	 <%
 }
 
}
%>

}



function setBatchValue(obj,id,sd,r)
{
	
	document.getElementById(id).value=obj.value.split("#")[0];
	document.getElementById(sd).value=obj.value.split("#")[3];
	
	
	var maxValue=obj.value.split("#")[3];
		var totalBatch="";
		for(var j=0;j<document.getElementsByName("batchNo").length;j++)
			{
			
			
			
			
			 if(document.getElementsByName("batchNo")[j].value==obj.value.split("#")[0])
			 {	 totalBatch++;
			
				if(totalBatch>maxValue)
					{
					
					alert("No more Films available for this Film Size in the inventory. Please select different Film Size.");
					document.getElementsByName("filmSize")[j].value="-1";
					document.getElementById(id).value="";
					document.getElementById(sd).value="";
								
					}
			
			 
			 }
			}
		
	
	
	}
	
function AddRowToTableBatchValueOnFocus(obj,id,sd,r)
{
	if(obj.value!="")
	document.getElementById('batchField').style.display='block';

}
	

function AddRowToTableBatchValueOnFocus2(obj,id,sd,r)
{
	
	document.getElementById('batchField').style.display='block';
	
	for(var z = document.getElementById("addBatchList").rows.length-1; z > 0; z--)
			{
				document.getElementById("addBatchList").deleteRow(z);
			
			}
	
	var itemId="";
	var storeId="";
	
	
	var batchNameSelected=obj.value;

	<%

	 	if(batchDetailsValues!=null)
	 {
		
	 for(int kk=0;kk<batchDetailsValues.size();kk++)

	 {
		
		
	 	String tempBatchHash=batchDetailsValues.get(kk);
	 	String tempBatchName=tempBatchHash.split("#")[0];
	 
	 	%>
	 	if(batchNameSelected==<%=tempBatchName%>)
	 		{
	 		
	 	itemId=<%=tempBatchHash.split("#")[1]%>;
		storeId=<%=tempBatchHash.split("#")[2]%>;
	 		}
	 	<%
	 	
	 }
	 }
%>

	

	

<%

 	if(batchDetailsValues!=null)
 {
	
 for(int k=0;k<batchDetailsValues.size();k++)

 {
	
	
 	String batchHash=batchDetailsValues.get(k);
 	String batchName=batchHash.split("#")[0];
 	String batchItemId=batchHash.split("#")[1];
 	String batchStoreId=batchHash.split("#")[2];
 	String inventory=batchHash.split("#")[3];

 	%>
 	if(itemId==<%=batchItemId%> && storeId==<%=batchStoreId%>)

 {

	var nRow=0;
 	var tableObj=document.getElementById('addBatchList');
	
 	var tr=document.createElement("TR");
	 
 	tr.setAttribute("id","setPdf");
 	tableObj.appendChild(tr);
 	var numRows=tableObj.rows.length;
 		nRow=numRows;
 
 	var tabRow=tableObj.insertRow(numRows);
 	tabRow.id=parseInt(nRow);
	 
 	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
 
 	td1.innerHTML="<div  align='left' ><input id='<%=batchHash%>' type='radio' name='chkBoxBatchValue' value='<%=batchHash%>' onclick='setBatchValue(this,\""+id+"\",\""+sd+"\","+r+")'   /></div>";   
 	td1.className='tdfont';
 	td1.width="1";
	
 	td2.innerHTML="<div align='left' ><%=batchName%></div>";   
 	td2.className='tdfont';
 	td2.colspan="1";
   
 	tabRow.appendChild(td1); 
 	tabRow.appendChild(td2);  
 
  
 
}
 	 <%
 }
 
}
%>

}


</script>
<style>

#batchField{
    display: none;
    position: absolute;
    top: 30%;
    left: 75%;
    width: 350px;
    height: 200px;
    margin-left: -100px;
    margin-top: -60px;                 
    padding: 10px;
    border: 2px solid #000;
    background: #E0EBEB;
 
}


.scroll_div {
	width: 800px;
	height: 50px;
	overflow-y: hidden;
	overflow-x: scroll;
	text-align: justify;
	margin: 0;
	padding: 10px 10px 10px 10px;
	scrollbar-face-color: #666669;
	scrollbar-highlight-color: #030000;
	scrollbar-3dlight-color: #030000;
	scrollbar-darkshadow-color: #030000;
	scrollbar-shadow-color: #030000;
	scrollbar-arrow-color: #030000;
	scrollbar-track-color: #030000;
}
</style>
<style>
.textBoxCss {
	background: #ccc;
	color: #135d8c;
	width: 180px;
	padding: 4px 10px 4px 15px;
	border-radius: 20px;
	box-shadow: 0 1px 0 #ccc inset;
	transition: 500ms all ease;
	outline: 0;
}
</style>
<%
	String strdivage = "\"\"";
	String strdivdob = "\"\"";
%>



<body >
	<form>
		<html:form method="post" action="/filmUsed">

			<html:hidden name="filmUsedFB" property="hmode" />
		   <html:hidden name="filmUsedFB" property="labcodefilm" />
			
			<html:hidden name="filmUsedFB" property="isPatDetailPage" />
			<html:hidden name="filmUsedFB" property="selectedCheckbox" />
			<html:hidden name="filmUsedFB" property="showStatus" />
			<html:hidden name="filmUsedFB" property="currentPage" />
			<html:hidden name="filmUsedFB" property="patCrNo" />
			<html:hidden name="filmUsedFB" property="sysDate" />
			<html:hidden name="filmUsedFB" property="config" />
			<html:hidden name="filmUsedFB" property="numberOfRow" />
			<html:hidden name="filmUsedFB" property="numberOfRowAdd" />
			<html:hidden name="filmUsedFB" property="numberOfRowWaste" />
			<html:hidden name="filmUsedFB" property="reqNo" />
			<html:hidden name="filmUsedFB" property="requisitionDNo" />
			<html:hidden name="filmUsedFB" property="tempFilmNo" />
			



			<logic:equal name="filmUsedFB" property="hmode" value="VIEW">
				<%
					//this.readOnly = true;
				%>
			</logic:equal>
			<his:TitleTag name="Film Used Process">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag>
			<his:ContentTag>

				<%
					String fromDateLabel = "";
													String toDateLabel = "";
													String fromDateControl = "";
													String toDateControl = "";
				%>

				<bean:define name="filmUsedFB" property="fromDate" id="frDate"
					type="java.lang.String" />
				<bean:define name="filmUsedFB" property="toDate" id="tDate"
					type="java.lang.String" />
				<%
					if (frDate == null || frDate.equalsIgnoreCase("")) {
														Date dt = (Date) session
																.getAttribute(Config.SYSDATEOBJECT);
														frDate = WebUTIL.getCustomisedSysDate((Date) session
																.getAttribute(Config.SYSDATEOBJECT),
																"dd-MMM-yyyy");
													}

													if (tDate == null || tDate.equalsIgnoreCase("")) {
														Date dt = (Date) session
																.getAttribute(Config.SYSDATEOBJECT);
														tDate = WebUTIL.getCustomisedSysDate((Date) session
																.getAttribute(Config.SYSDATEOBJECT),
																"dd-MMM-yyyy");
													}
				%>

				<his:SubTitleTag name="Details"></
  			</his:SubTitleTag>
				<logic:equal name="filmUsedFB" property="showStatus" value="0">
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfont">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="CrNo" />&nbsp;
									</font>
								</div>
							</td>
							<%
								UserVO uservo = ControllerUTIL.getUserVO(request);
																										Date todayDateobj = new Date();
																										SimpleDateFormat dateob = new SimpleDateFormat("yy");
																										String strDate = dateob.format(todayDateobj);
																										String hospitalCode = uservo.getHospitalCode();
																										String val = uservo.getHospitalCode() + strDate;
							%>
							<td width="25%" class="tdfonthead">
								<div align="left">
									<input type="text" id="textBoxCss" name="tempPatCrNo"
										value="<%=val%>" maxlength="15" size="20"
										onkeypress="return validateNumeric(event,this)" tabindex="1">
								</div>
							</td>
							<td width="25%" class="tdfont"></td>
							<td width="25%" class="tdfonthead"></td>
						</tr>
						<tr>
							<td width="25%" class="tdfont">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="LabType" />&nbsp;
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead"><logic:present
									name="<%=InvestigationConfig.FILM_USED_LAB_COMBO%>">
									<div align="left">
										<span class="custom-dropdown small"> <html:select
												name="filmUsedFB" property="labCode" tabindex="1">
												<html:option value="%">All</html:option>
												<html:options
													collection="<%=InvestigationConfig.FILM_USED_LAB_COMBO%>"
													property="value" labelProperty="label" />
											</html:select>
										</span>
									</div>
								</logic:present></td>

							<td width="25%" class="tdfont"></td>
							<td width="25%" class="tdfonthead"></td>
						</tr>
						<tr>
							<td class="tdfont" width="25%">
								<div id='divfromDate' style='<%=fromDateLabel%>' align="right">
									<font color="#FF0000" size="1"
										face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="fromDate" />
									</font>
								</div>

							</td>
							<td class="tdfonthead" width="25%">
								<div id='divfromDateControl' style='<%=fromDateControl%>'
									align="left">
									<his:date name='fromDate' dateFormate="%d-%b-%Y"
										value="<%=frDate%>" />
								</div>

							</td>

							<td class="tdfont" width="25%">
								<div id='divfromDate' style='<%=toDateLabel%>' align="right">
									<font color="#FF0000" size="1"
										face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="toDate" />
									</font>
								</div>
							</td>
							<td class="tdfonthead" width="25%">
								<div id='divfromDateControl' style='<%=toDateControl%>'
									align="left">
									<his:date name='toDate' dateFormate="%d-%b-%Y"
										value="<%=tDate%>" />
								</div>

							</td>

						</tr>
						<tr>
							<td class="tdfont" align="center" colspan="2" width="25%">
								<div align="right">
									<img class="button"
										src='<his:path src="/hisglobal/images/GO.png"/>' id="gob"
										style="cursor: pointer"
										onkeypress="if(event.keyCode==13) onClickGO('<%=hospitalCode%>')"
										onclick="onClickGO('<%=hospitalCode%>')" tabindex="1">
								</div>
							</td>
						</tr>
					</table>


					<%
						boolean flag = false;
					%>
					<%
						//Pagination Logic
																		PaginationFB fbPage = new PaginationFB();
																		pageContext.setAttribute("fbPagination", fbPage);
																		fbPage.setCurrentPage(((filmUsedFB) request
																				.getAttribute("filmUsedFB")).getCurrentPage());
																		fbPage.setObjArrName(InvestigationConfig.LIST_TEST_DETAILS_FILM_USED);
																		fbPage.setAppendInTitle("List ");
																		int maxRecord = 10;
																		fbPage.setMaxRecords(maxRecord);
					%>


					<his:PaginationTag name="fbPagination"></his:PaginationTag>

					<his:SubTitleTag name="Test Details"></
  			</his:SubTitleTag>

					<logic:present
						name="<%=InvestigationConfig.LIST_TEST_DETAILS_FILM_USED%>">
						<%
							flag = true;
						%>

						<table id="table2" width="100%" border="0" cellspacing="1"
							cellpadding="0" bgcolor="#EBEBEB">
							<tr>

								<td width="5%" align="left"><b> <font color="#000000"
										size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="select" />
									</font>
								</b></td>
								<td width="10%" align="left"><b><font color="#000000"
										size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="crNO" />
									</font></b></td>

								</font>
								</b>
								</td>
								<td width="10%" align="left"><b> <font color="#000000"
										size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="labName" />
									</font></b></td>

								<td width="10%" align="left"><b> <font color="#000000"
										size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="patName" />
									</font></b></td>
								<td width="10%" align="left"><b> <font color="#000000"
										size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="age" />
									</font></b></td>
								<td width="10%" align="left"><b><font color="#000000"
										size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="patStatus" />
									</font></b></td>
								<td width="15%" align="left"><b><font color="#000000"
										size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="departmentunit" />
									</font></b></td>
								<td width="25%" align="left"><b> <font color="#000000"
										size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="testName" />
									</font></b></td>

							</tr>
						</table>
					</logic:present>




					<logic:empty
						name="<%=InvestigationConfig.LIST_TEST_DETAILS_FILM_USED%>">

						<center>
							<font color="red" size="4">No Details Found</font>
						</center>
					</logic:empty>



		
					<logic:notEmpty
						name="<%=InvestigationConfig.LIST_TEST_DETAILS_FILM_USED%>">

						<table id="table3" style=""  width="100%">

							<%
								List<filmUsedVO> lstPatVO = (List<filmUsedVO>) session.getAttribute(InvestigationConfig.LIST_TEST_DETAILS_FILM_USED);

																											int i, size = 0;

																											if (lstPatVO != null && lstPatVO.size() > 0)
																												size = lstPatVO.size();
																											int startIndex = ((Integer) request
																													.getAttribute(PaginationTag.PAGINATION_START_INDEX))
																													.intValue();
																											int endIndex = ((Integer) request
																													.getAttribute(PaginationTag.PAGINATION_END_INDEX))
																													.intValue();

																											for (int j = startIndex; j <= endIndex; j++) {
																												//int i=j-startIndex;

																												if (j < size) {
																													filmUsedVO voPat = lstPatVO
																															.get(j);

																													String chkVal = voPat.getPatCrNo() + "#"
																															+ voPat.getReqNo()+"#"+voPat.getFilmdataused()+"#"+voPat.getLabCode()+"#"+voPat.getPatLabName()+"#"+voPat.getTestName()+"#"+voPat.getRequisitionDNo();
							
																												String filmusedornot=voPat.getIsfilmused();
																												int filmused=1;
																												if(voPat.getIsfilmused()!=null)
																												{
																													if(filmusedornot.equals("1"))
																													filmused=2;
																												}
																												String color="black";

																													switch(filmused)
																									 				{
																										 				case 1: color="black";  // report change addendum
																										 				        break;
																										 				case 2: color="red";  //  report change addendum
																													        	break;
																										 				  default: color="black";
																													    		break;
																									 				}
																													
							
							%>
							<tr>

								<td width="5%" align="left"><font color="<%=color%>" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <input
										type="radio" name="chkSamplePatient" value='<%=chkVal%>' onclick="displaySamplePatDetails()"> 
								</font></td>
								<td width="10%" align="left">
									<div align="left">
										<font color="<%=color%>" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatCrNo()%></font>
									</div>
								</td>


								<td width="10%" align="left">


									<div align="left">
										<font color="<%=color%>" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatLabName()%></font>
									</div>
								</td>

								<td width="10%" align="left">


									<div align="left">
										<font color="<%=color%>" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatName()%></font>
									</div>
								</td>
								<td width="10%" align="left">

									<div align="left">
										<font color="<%=color%>" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatAge()%></font>
									</div>

								</td>
								<td width="10%" align="left">

									<div align="left">
										<font color="<%=color%>" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatStatus()%></font>
									</div>

								</td>
								<td width="20%" align="left">

									<div align="left">
										<font color="<%=color%>" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatUnitName()%></font>
									</div>
								</td>
								<td width="25%" align="left">

									<div align="left">
										<font color="<%=color%>" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getTestName()%></font>
									</div>
								</td>

							</tr>
							<%
								}
																											}
							%>

						</table>
						
					
		
					</logic:notEmpty>
				</logic:equal>





				<his:statusTransactionInProcess>
					<logic:present name="<%=InvestigationConfig.LIST_PATIENT_VO%>">
						<his:SubTitleTag name="Patient Details">

<%-- <%
                     if(session.getAttribute("totalaused")!=null)
                    	 {%>					
							<div align="right" >
					     <!-- <button id="myButton">click!</button> -->
<a id="myButton" style="display: ;box-shadow: 0 6px 10px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);background-color: #556B2F;border: none;color: white;text-align: center; text-decoration: none;font-size: 14px;margin: 4px 2px;cursor: pointer;-webkit-transition-duration: 0.4s;transition-duration: 0.4s;height: 30px;" href="">Previous Film Used Details</a>


											</div>
											<%} %> --%>
											
						</
  			</his:SubTitleTag>
						<%
							filmUsedVO onlinePatientvo = (filmUsedVO) session
																									.getAttribute(InvestigationConfig.LIST_PATIENT_VO);

																							if (onlinePatientvo.getPatStatus() != null) {
						%>

						<div id="ipddDiv">
							<table width="100%">
								<tr>
									<td class="tdfont" width="25%">
										<div align="right">
											<bean:message key="crNO" />
											&nbsp;
										</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatCrNo()%>
										</div>
									</td>
									<td class="tdfont" width="25%">
										<div align="right">
											<bean:message key="patientName" />
											&nbsp;
										</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatName()%>
										</div>
									</td>
								</tr>
								<tr>
									<td class="tdfont" width="25%">
										<div align="right">
											<bean:message key="age/gender" />
											&nbsp;
										</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatAge() + "/"
										+ onlinePatientvo.getPatGender()%>
										</div>
									</td>
									<td class="tdfont" width="25%">
										<div align="right">
											<bean:message key="patStatus" />
											&nbsp;
										</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatStatus()%>
										</div>
									</td>
								</tr>	
								
								
								<tr>
									<td class="tdfont" width="25%">
										<div align="right">
											Lab Name
											&nbsp;
										</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatLabName() %>
										</div>
									</td>
									<td class="tdfont" width="25%">
										<div align="right">
											Test Name
											&nbsp;
										</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getTestName()%>
										</div>
									</td>
								</tr>
								
								<tr>

								</tr>
							</table>

						</div>



						<%
							if (onlinePatientvo.getPatStatus()
																										.equals("IPD")) {
						%>
						<his:SubTitleTag name="Ipd Details"></
  			</his:SubTitleTag>
						<div id="ipddDiv">
							<table width="100%">
								<tr>
									<td class="tdfont" width="25%">
										<div align="right">
											<bean:message key="admitdept" />
										</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatUnitName()%>
										</div>
									</td>
									<td class="tdfont" width="25%">
										<div align="right">
											<bean:message key="wardName" />
										</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatWardName()==null?"":onlinePatientvo.getPatWardName()%>
										</div>
									</td>
								</tr>
								<tr>
									<td class="tdfont" width="25%">
										<div align="right">
											<bean:message key="roomName" />
										</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatRoomName()==null?"":onlinePatientvo.getPatRoomName()%>
										</div>
									</td>
									<td class="tdfont" width="25%">
										<div align="right">
											<bean:message key="bedName" />
										</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatBedName()==null?"":onlinePatientvo.getPatBedName()%>
										</div>
									</td>
								</tr>
								<tr>
									<td class="tdfont" width="25%">
										<div align="right">
											<bean:message key="consultant" />
										</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatOrderByDoc()==null?"":onlinePatientvo.getPatOrderByDoc()%>
										</div>
									</td>
									<td class="tdfont" width="25%"></td>
									<td class="tdfonthead" width="25%"></td>
									<%-- <td class="tdfonthead" width="25%">
			 				<div align="right">
			 					Diagnosis
			 				</div>
			 			</td>
			 			<td class="tdfont" width="25%">
			 				<div align="left">
			 					<%=patDtlVO.getDiagnosis() %>
			 				</div>
			 			</td> --%>
								</tr>

							</table>

						</div>
						<%
							}
						%>
						<%
							if (onlinePatientvo.getPatStatus()
																										.equals("OPD")) {
						%>
						<his:SubTitleTag name="Opd Details"></
  			</his:SubTitleTag>
						<div id="opdEmerencyDIV">
							<table width="100%">
								<tr>
									<td class="tdfont" width="25%">
										<div align="right">Department/Unit Name</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatUnitName()%>
										</div>
									</td>
									<td class="tdfont" width="25%">
										<div align="right">
											<bean:message key="age/gender" />
											&nbsp;
										</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatAge() + "/"
											+ onlinePatientvo.getPatGender()%>
										</div>
									</td>
								</tr>
								<tr>
									<td class="tdfont" width="25%">
										<div align="right">
											<bean:message key="visitDate" />
											&nbsp;
										</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatVisitDat()%>
										</div>
									</td>
									<td class="tdfont" width="25%">
										<div align="right">
											<bean:message key="patCat" />
										</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=(onlinePatientvo.getPatCategory() == null ? ""
											: onlinePatientvo.getPatCategory())%>
										</div>
									</td>
								</tr>
							

							</table>
						</div>
						<%
							}
						%>
						<%
							}
																							
																							
						String bId="batch00";
						String invId="inv00";
						%>

                     
                     <%
                     
                     String totalaused="";
                     if(session.getAttribute("totalaused")!=null)
                    	 totalaused= (String)session.getAttribute("totalaused");
                     String wasteused="";
                     if(session.getAttribute("wasteused")!=null)
                    	 wasteused= (String)session.getAttribute("wasteused");
                     String addtionalused="";
                     if(session.getAttribute("addtionalused")!=null)
                    	 addtionalused= (String)session.getAttribute("addtionalused");
                     %>



						<his:SubTitleTag name="Film Used Detail"    >
					
					<%-- <%
                     if(session.getAttribute("totalaused")!=null)
                    	 {%>					
							<div style="margin-right: 100;" align="right" >
					     <!-- <button id="myButton">click!</button> -->
<a id="myButton" style="display: ;box-shadow: 0 6px 10px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);background-color: #556B2F;border: none;color: white;text-align: center; text-decoration: none;font-size: 10px;margin: 4px 2px;cursor: pointer;-webkit-transition-duration: 0.4s;transition-duration: 0.4s;height: 30px;" href="">Previous Film Used Details</a>


											</div>
											<%} %> --%>
											
					<div style="margin-right: 50;" align="right" >
					
						<%
                     if(session.getAttribute("totalaused")!=null)
                    	 {%>
					
					<a id="myButton" style="color:yellow" href=""><u>View Previous Film Details</u></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					<%} %>
					
					<%
                     if(session.getAttribute("totalaused")!=null)
                    	 {%>
                    	 
					<% if(!totalaused.equals("")) {%>
											 Film Used:<%=totalaused %>
											
											<%}} %>
											</div>
											
						
						</his:SubTitleTag>
						<div align="center">
							<table id="filmUsedDetails1" width="100%">

								<!-- row 1 col name -->
								<tr>

									<td width="20%">Film No</td>
									<td width="20%">Film Size (LxB inches)</td>
									<td width="20%">Test Name</td>
									<td width="20%">Batch No.</td>
									<td width="15%">Inventory</td>
									<td width="5%"><img
										src='/HISInvestigationG5/hisglobal/images/plus.gif'
										alt="Add Film" title="Add Film" name="addRow"
										onkeypress="if(event.keyCode==13)AddRowToTable() ;"
										onclick="return AddRowToTable();"></td>

								</tr>

								<tr>

									<td colspan="1"><html:text name="filmUsedFB" value="<%=onlinePatientvo.getTempFilmNo() %>"
									property="filmNo" onkeypress="return validateNumeric(event)" tabindex="1" ></html:text></td>

									<td colspan="1">
										<div align="left">
										
										<%
										String batchFunc="AddRowToTableBatchValue(this,'"+bId+"','"+invId+"',0)";
										String batchChange="AddRowToTableBatchValueOnFocus(this,'"+bId+"','"+invId+"',0)";
												
										%>
										
										
											<html:select name="filmUsedFB" property="filmSize"
												tabindex="1" onchange='<%=batchFunc %>'>
												<html:option value="-1">Select Value</html:option>
												
												
												
												
												
												
												
												
												
												<logic:present
									name="<%=InvestigationConfig.LIST_FILM_SIZE_FILM %>">
												<html:options
												collection="<%=InvestigationConfig.LIST_FILM_SIZE_FILM %>"
												property="value" labelProperty="label" />
</logic:present>
											</html:select>

										</div>
									</td>

									<td colspan="1"><%=onlinePatientvo.getTestName() %></td>
									<td colspan="1">
									
									<html:text name="filmUsedFB" property="batchNo" styleId="batch00" readonly="" tabindex="1" onfocus='<%=batchChange %>'></html:text>
									
									</td>

									<td colspan="1">
									
									<html:text name="filmUsedFB" property="inventory"  styleId="inv00" readonly="" onkeypress="return validateNumeric(event)" tabindex="1" ></html:text>
									
									</td>

								</tr>



							</table>


						</div>
						<his:SubTitleTag name="Additional Film Used Detail">
						
						<div style="margin-right: 50;" align="right" >
						<% if(!addtionalused.equals("")) {%>
											Additional Film Used:<%=addtionalused %>
											
											<%} %>
							</div>			
						</his:SubTitleTag>
						<div align="center">
							<table id="filmUsedDetails2" width="100%">

								<!-- row 1 col name -->
								<tr>

									<td width="20%">Film No</td>
									<td width="20%">Film Size (LxB inches)</td>
									<td width="20%">Reason</td>
									<td width="20%">Batch No.</td>
									<td width="15%">Inventory</td>
									<td width="5%"><img
										src='/HISInvestigationG5/hisglobal/images/plus.gif'
										alt="Add Film" title="Add Film" name="addRow"
										onkeypress="if(event.keyCode==13)AddRowToTableAdd() ;"
										onclick="return AddRowToTableAdd();"></td>

								</tr>





							</table>

						</div>

						<his:SubTitleTag name="Film Wasted">
						
						<div style="margin-right: 50;" align="right" >
						<% if(!wasteused.equals("")) {%>
											Wasted Film Used:<%=wasteused %>
											
											<%} %>
											</div>
						</his:SubTitleTag>
						<div align="center">
							<table id="filmUsedDetails3" width="100%">

								<!-- row 1 col name -->
								<tr>

									<td width="20%">Film No</td>
									<td width="20%">Film Size (LxB inches)</td>
									<td width="20%">Reason</td>
										<td width="20%">Batch No.</td>
									<td width="15%">Inventory</td>
									<td width="5%"><img
										src='/HISInvestigationG5/hisglobal/images/plus.gif'
										alt="Add Film" title="Add Film" name="addRow"
										onkeypress="if(event.keyCode==13)AddRowToTableWaste() ;"
										onclick="return AddRowToTableWaste();"></td>

								</tr>





							</table>

						</div>


						<his:SubTitleTag name="Film Details">
						</his:SubTitleTag>

						<table id="filmUsedDetails3" width="100%">


							<tr>
								<td width="10%"></td>
								<td width="40%">Total Films Used</td>

								<td width="50%"> 
								
								<html:text name="filmUsedFB" property="noOfFilms" value="1"
									></html:text>
									
									
									 </td>

							</tr>

							<tr>
<td width="10%"></td>
								<td width="40%">Remarks</td>

								<td width="50%">	<html:text name="filmUsedFB" property="remarks" 
									></html:text> </td>

							</tr>


							<tr>
<td width="10%"></td>
								<td width="40%">Test Status</td>

								<td width="50%">
								
								
								
								  
			             <html:radio name="filmUsedFB"   tabindex="1" property="testStatus" value="1"  ></html:radio>
						
						Done&nbsp;
						
						<html:radio name="filmUsedFB" tabindex="1" property="testStatus" value="0" ></html:radio>
						
						Pending&nbsp;
								
								</td>

							</tr>




						</table>






					</logic:present>











				</his:statusTransactionInProcess>
			</his:ContentTag>








			<div id="batchField" style="display: none;">
				<div draggable="true">

					<his:TitleTag name="Batch List">

						<img src='/HISInvestigationG5/hisglobal/css/close.png'
							onClick="closePopup();">

					</his:TitleTag>

			

					<table id="addBatchList" width="100%"></table>
					<div align="center">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-ok.png"  />'
							onclick="closePopup();">
					</div>

				</div>
			</div>


















			

			

	<logic:equal name="filmUsedFB" property="showStatus" value="0">
		<his:ButtonToolBarTag>
		<img class="button"
					src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="cancel"
					tabindex="1" style="cursor: pointer"
					onkeypress="if(event.keyCode==13) cancelFunc();" tabindex="1"
					onclick="cancelFunc();">
			</his:ButtonToolBarTag>		
					<his:SubTitleTag>
			<his:name>
				<bean:message key="legends" />
			</his:name>
			
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td width="70%"></td>
					<td width="30%">
						<div align="right">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">Show </font><img
								src='<his:path src="/hisglobal/images/arrow_down.gif"/>'
								tabindex="1" onclick="showLegends1();"
								onkeypress="if(event.keyCode==13) showLegends1();"> <font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">Hide </font><img
								src='<his:path src="/hisglobal/images/arrow_up.gif"/>'
								tabindex="1" onclick="showLegendsNone1();"
								onkeypress="if(event.keyCode==13) showLegendsNone1();">
						</div>
					</td>
				</tr>
			</table>
		</his:SubTitleTag>
	</logic:equal>
	
	
	
	<logic:notEqual name="filmUsedFB" property="showStatus" value="0">
				<his:ButtonToolBarTag>
				<img class="button"
					src='<his:path src="/hisglobal/images/btn-sv.png"/>' id="saveDiv"
					style="cursor: pointer;"
					onkeypress="if(event.keyCode==13) onSave();" tabindex="1"
					onclick="onSave();">
				<img class="button"
					src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="cancel"
					tabindex="1" style="cursor: pointer"
					onkeypress="if(event.keyCode==13) submitFor();" tabindex="1"
					onclick="submitFor();">
					</his:ButtonToolBarTag>
</logic:notEqual>



			
		   
		   
		     <%
                     
                     String totalauseddialog1="";
                     if(session.getAttribute("totalaused")!=null)
                    	 totalauseddialog1= (String)session.getAttribute("totalaused");
                     String wasteuseddialog1="";
                     if(session.getAttribute("wasteused")!=null)
                    	 wasteuseddialog1= (String)session.getAttribute("wasteused");
                     String addtionaluseddialog1="";
                     if(session.getAttribute("addtionalused")!=null)
                    	 addtionaluseddialog1= (String)session.getAttribute("addtionalused");
                     %>
                     
                     
                <div id="dialog1" title="Previous Film Used Details&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Film used:<%=totalauseddialog1 %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Wasted Film used:<%=wasteuseddialog1 %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Additional Film used:<%=addtionaluseddialog1 %>" align="center" style="font-size: large;width:1000px;height: 401px;">
                

               
               <%
               
               List<filmUsedVO> data = (List<filmUsedVO>) session.getAttribute(InvestigationConfig.PAT_PREV_REQUISITION);
               
               if(data!=null && data.size()>0)
               {
               
               %>
<div style="width: 100%;  margin-left: -150;font-size: large;">

<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr >
							<td width="20%" class="tdfont">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> Batch No.
									</font>
								</div>
							</td>
							<td width="15%" class="tdfont">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> Film Type
									</font>
								</div>
							</td>
							<td width="15%" class="tdfont">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> Film Used Type 
									</font>
								</div>
							</td>
							<td width="15%" class="tdfont">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> Film No.
									</font>
								</div>
							</td>
								<td width="20%" class="tdfont">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> Entry Date
									</font>
								</div>
							</td>
							<td width="15%" class="tdfont">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> Reason
									</font>
								</div>
							</td>
							</tr>
							
</div>

                <%} %>
                
                <%
                                     if(data!=null && data.size()>0)
                                	for(int p=0;p<data.size();p++)
                                                {
                                                	filmUsedVO vo=data.get(p);
                                                	
                                               
                                %>
                
                <tr class="name" >
							<td width="20%" class="tdfont">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
										color="blue" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=vo.getBatchNo()==null?"-": vo.getBatchNo()%>
									</font>
								</div>
							</td>
							<td width="15%" class="tdfont">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
										color="blue" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=vo.getFilmtypename() %>
									</font>
								</div>
							</td>
							<td width="15%" class="tdfont">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
										color="blue" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=vo.getFilmUsedType() %>
									</font>
								</div>
							</td>
							<td width="15%" class="tdfont">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
										color="blue" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=vo.getTempFilmNo() %>
									</font>
								</div>
							</td>
								<td width="20%" class="tdfont">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
										color="blue" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=vo.getEntryDate() %>
									</font>
								</div>
							</td>
							<td width="15%" class="tdfont">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
										color="blue" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=vo.getFilmReason()==null?"-":(vo.getFilmReason().equals("1")?"Over Exposure":"-") %>
									</font>
								</div>
							</td>
							</tr>
							
                <%} %>
                </table>
    
 
</div>

<div id="divLegends1" style="display: none">
			<his:ContentTag>
				<table width="100%" colspacing="1" colpadding="0"
					style="clear: both; border-left: 1px solid #003366; border-top: 1px solid #003366">
					<tr>
						<td width="20%"><font color="black" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Black Color Code</div>
						</font></td>
						<td width="80%"><font color="black" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left"> Normal  Record</div>
						</font></td>
					</tr>
					
					<tr>
						<td width="20%"><font color="red" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Red Color Code</div>
						</font></td>
						<td width="80%"><font color="red" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Film Used Record</div>
						</font></td>
					</tr>
					

				</table>
			</his:ContentTag>
		</div>
		
			<div align="left">
				<his:status />
			</div>
			<html:hidden name="filmUsedFB" property="labCode" />
			<html:hidden name="filmUsedFB" property="patType" />

		</html:form>
	</form>
	<script>
		// just for the demos, avoids form submit

		jQuery.validator.setDefaults({
			debug : true,
			success : "valid"
		});
		$("#field").validate({
			rules : {
				emailId : {

					email : true
				}
			}
		});
	
		</script>
		<script>

		function getFilmSizeList(obj)
		{
			
			var filmSizeCombo="";
/* 			var filmSizeCombo = "<div align='left'><select name = 'filmSize' onchange='getBatchDetails(this,\""+batch+"\",\""+inv+"\","+obj+")' ><option value='-1'>Select Value</option>";
 */			<%java.util.List<Entry>  lstFilmSize= (java.util.List<Entry>)session.getAttribute(InvestigationConfig.LIST_FILM_SIZE_FILM);
			if(lstFilmSize!=null)
			{
			for(int i=0;i<lstFilmSize.size();i++)
			{
				%>
				filmSizeCombo+="<option value = '<%=lstFilmSize.get(i).getValue()%>'><%=lstFilmSize.get(i).getLabel()%></option>"
				<%
			}
			}
			%>
		/* 	filmSizeCombo+="</select></div>"; */
		      return filmSizeCombo;
		}
		
		



		
		</script>
	
	
	
</body>