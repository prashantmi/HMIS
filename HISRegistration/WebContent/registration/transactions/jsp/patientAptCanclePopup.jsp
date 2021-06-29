<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page import="registration.config.RegistrationConfig"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<html>
<head>
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/basic.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.border .div-table-col{
border: 1px solid black;
}
</style>

<script language="JavaScript" type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/js/basic.js'></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<script type="text/javascript">



function submitForm(mode) {
	document.forms[0].action = mode + "AppointmentCancellation.action";
	document.forms[0].submit();
 }

function cancelPopupBut(obj)
{		
		//alert("1");
		
	parent.closeModal();
}

function aptCancelation(mode)
{
	var _parFrm=parent.document.forms[0];
	//alert( parent.document.getElementsByName("patAptNo")[0].value);
	parent.document.getElementsByName("remarks")[0].value=document.getElementsByName("remarks")[0].value;
	parent.document.forms[0].action = "SAVEAppointmentCancellation.action";
	parent.document.forms[0].submit();
	parent.closeModal();
	
}


function clearFormField(obj)
{
	//alert{"1"};
	$('[name="remarks"]')[0].value = "";
	
	}

</script>
</head>
<body>


<s:form action="AppointmentCancellation" >
    
    <div class="wrapper rounded">
	    
		<div class="div-table">
			  <div class="div-table-row ">
				<div id="divTitleId" class="div-table-col title width100 ">
						<s:text name="Apt_appointment"/>&nbsp;<s:text name="cancellation"/>  
				</div>
			  </div>
			  <div class="div-table-row ">
				<div class="div-table-col label" style="width: 25%;"><font color="red">*</font><s:text name="global.remarks"/></div>
				<div class="div-table-col control" style="width: 25%;">
					   <textarea rows="1"   tabindex="1" cols="15" name="remarks"></textarea>
					</div>	
			  </div>
			  </div>
			  
			<div class="div-table-button">
			<div class="div-table-row footerBar">
					<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row emptyBar">
				<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row" align="center">
				<a  tabindex="1" href="#" class="button" id="submitId" onclick="aptCancelation('SAVE');"><span class="save"><s:text name="save"/></span></a>
				<a  tabindex="1" href="#" class="button" id="clearId" onclick="clearFormField(this);"><span class="clear"><s:text name="clear"/></span></a>
				<a  tabindex="1" href="#" class="button" id="cancelId" onclick="cancelPopupBut(this);"><span class="cancel"><s:text name="cancel"/></span></a>
			</div>
			
		</div>  
			  
			  </div>
			  <s:hidden name="patAptNo"/>
			  <cmbPers:cmbPers></cmbPers:cmbPers>
			  <s:token/> 
			  </s:form>
			  

</body>
</html>