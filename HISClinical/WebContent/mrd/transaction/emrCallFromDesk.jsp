<%--##		Modify Date				: 	16-01-2015
	##		Reason	(CR/PRS)		: 	Close Button Added
	##		Modify By				:	Akash Singh
--%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript"> 
window.onload=function(){
	
	//child=openDependentPopup("/HISClinical/mrd/emrDesk.cnt?hmode=CALLFROMDESK",window.event,800,900,false);
	
	child = window.open(createFHashAjaxQuery("/HISClinical/mrd/emrDesk.cnt?hmode=CALLFROMDESK"),'popupWindow',
			'status=yes,scrollbars=yes,height= '+ screen.availHeight + ',width='+screen.availWidth +',left=10,top=10');  
  	//child.moveTo(250,250);
 	child.focus(); 

	submitToDesk('NEW', 'NEW')
	if(!child.opener)
	   child.opener = self;
}
function submitDesk(mode)
{
	document.forms[0].mode.value=mode;
	document.forms[0].submit();
}

function submitToDesk(mode,hmode)
{
	//alert("A--"+" mode:- "+mode+" hmode:- "+hmode);
	if(goToDashBoard)	goToDashBoard();
		
	if(parent.gotToMainTab)
		parent.gotToMainTab("Patient Dashboard", true);
	else if(parent.parent.gotToMainTab)
		parent.parent.gotToMainTab("Patient Dashboard", false);
}
</script>
</head>
</html>	 
