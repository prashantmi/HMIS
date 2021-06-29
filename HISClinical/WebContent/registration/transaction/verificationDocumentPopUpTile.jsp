<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ page import ="java.util.*,registration.*,hisglobal.vo.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/tab.css"/>
	
	<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>
	<his:css src="/hisglobal/css/calendar-blue2.css"/>
    
<his:javascript src="/registration/js/commonvalidation.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/popup.js"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script>

function verificationDocumentsSelected(){ 

arrSelection=new Array();
elem = document.getElementsByName("verificationDocumentList");


for(i=0;i<elem.length;i++){
	if(elem[i].checked){
	//alert("1---"+arrSelection)
	//alert("arrSelection.length1---"+arrSelection.length)
	  
	  	arrSelection[arrSelection.length]=elem[i].value;
	  		
	//  alert("arrSelection.length2---"+arrSelection.length)				
	// alert(elem[i].value);
	// alert("3---"+arrSelection[arrSelection.length])
	}
}

//alert("4--"+arrSelection);

if(arrSelection.length!=0){
callToPopulate(arrSelection);
opener.document.getElementById("saveButton").style.display='block';
opener.document.getElementsByName("verificationDocumentAdded")[0].value='1'
self.close();
}
else
alert("Select Verification Document(s)");
}


</script>

<input type="hidden" name="hmode"/>
<his:statusTransactionInProcess>
<his:TitleTag>

		  <his:name>
		  <bean:message key="verification"/>
		  <bean:message key="documents"/>
		  <bean:message key="required"/>
      	  </his:name>
      	  
</his:TitleTag>

<his:ContentTag>
<table cellspacing="1" width="100%">
<logic:iterate id="verificationDocument" name="<%=RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS%>" indexId="idx">
<% String styleCls ="tdfont";
if(idx.intValue()%2==0)
	styleCls="tdfonthead";%>

<tr>
<td class='<%=styleCls %>' width="40%">
<b>
<div align="left">
<bean:define name="verificationDocument" property="value" id="varificationDocumentValue" type="java.lang.String"/>
<his:checkbox name="verificationDocumentFB" property="verificationDocumentList" value='<%=varificationDocumentValue%>'/>
<bean:write name="verificationDocument" property="label"/>
</div>
</b>
</td>
</tr>
	
</logic:iterate>
</table>

</his:ContentTag>

<his:ButtonToolBarTag>

	<div align="center">
	<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>' style=cursor:pointer tabindex="1" onkeypress="if(event.keyCode==13) verificationDocumentsSelected();" onclick = "verificationDocumentsSelected()">
	</div>

</his:ButtonToolBarTag>
</his:statusTransactionInProcess>
<his:status/>