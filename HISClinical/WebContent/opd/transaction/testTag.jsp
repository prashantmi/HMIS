<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ page import ="opd.*" %>
<html>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>	
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>

<script type="text/javascript">

function validateAlphabetsWithNumberOnly(e)
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

	keychar = keychar.toUpperCase();
//	alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32))
	   return true;

	// alphas and space
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890").indexOf(keychar) > -1))

	   return true;
	 
	else
	   return false;
}
function validatePositiveNumberOnly(e)
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

	keychar = keychar.toUpperCase();
//	alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32))
	   return true;

	// alphas and space
	else if ((("1234567890").indexOf(keychar) > -1))

	   return true;
	 
	else
	   return false;
}

function abc()
{
	document.getElementsByName('checkItemTypeId')[0].value=document.getElementsByName('itemTypeId')[0].value
}
function submitForm(mode)
{	
	document.getElementsByName('hmode')[0].value=mode;
	document.forms[0].submit();
}

function AddRowToTable(mode)
{
document.getElementsByName('hmode')[0].value=mode
document.forms[0].submit();

}

function DelRowFromTable(mode,obj)
{

document.getElementsByName('hmode')[0].value=mode
document.getElementsByName('index')[0].value=obj
document.forms[0].submit();

}


function validateForm(mode)
{
	//alert(document.forms[0].itemTypeId.value);
	if(document.forms[0].itemTypeId.value=="-1")
	{
		alert("Please Select Item Type");
		document.forms[0].itemTypeId.focus();
		return false;
	}
	if(document.forms[0].doseName.value=="" )
	{
		 alert("Please Enter Dose Name");
		 document.forms[0].doseName.focus();
		 return false;                          
	}
	//alert(document.getElementsByName("isFrequencyBound")[0].value);
	if(document.getElementsByName("isFrequencyBound")[0].checked)
	{
		if(document.forms[0].doseQty.value=="" )
		{	
			alert("Please Enter Dose Quantity");
			document.forms[0].doseQty.focus();
			return false;                          
		}
	}
	
	submitForm(mode);
	
}

function addNewRecord(mode)
{
   
	if(document.forms[0].doseName.value=="")
	 {
	 alert("Please Enter Dose Name");
	 document.forms[0].doseName.focus();
	 return false;                          
	 }
	 
	 else if(document.forms[0].doseInstruction.value=="")
	 {
	 alert("Please Enter Instruction");
	 document.forms[0].doseInstruction.focus();
	 return false;                          
	 }
	 
	
	 
	else
	{
	AddRowToTable(mode);
	} 


}

function clearForm()
{
	document.forms[0].doseName.value=""; 
	document.forms[0].doseInstruction.value="";
	document.forms[0].doseQty.value="";
}

function showDoseQty(obj)
{
	//alert(obj);
	//alert(obj.value);
	if(obj.value=="0")
	{
		document.getElementById("doseQtyRowId").style.display="none";
	}
	else
	{
		document.getElementById("doseQtyRowId").style.display="";
	}
	
}

</script>

<body >
		<html:form action="/master/DrugDoseMaster">
		
		
		<his:TitleTag name="Drug Dose Master">
		</his:TitleTag>
		
		<his:ConsentTag>
		</his:ConsentTag>
		
		</html:form>
		
		</body>
		</html>
		