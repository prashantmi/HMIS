<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ page import ="registration.*" %>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<his:css src="/hisglobal/css/tab.css"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">





function validateCode()
{
	//alert("populate")
	self.document.getElementsByName("hmode")[0].value='POPULATE';
	self.document.forms[0].submit();
	//alert("sdsdd")	
	opener.document.getElementsByName("hmode")[0].value='POPULATE';
	opener.document.forms[0].submit();
	self.close();
	
}

function validateSearch(){
if(document.getElementsByName("searchCode")[0].value==null || document.getElementsByName("searchCode")[0].value=="")
	if(document.getElementsByName("searchDisease")[0].value==null || document.getElementsByName("searchDisease")[0].value=="") 
		
		{
			alert('Enter Diagnosis Code or Diagnosis Name');
			return false;
		}
		else
		{
		
			return true;
		}
	else
	{
	
		return true;
	}


}
</script>
<html:form action="/YellowSlipDiagnosisPopUp" >
<his:TitleTag>

		  <his:name>
		  <bean:message key="search"/>
		 
      	  </his:name>
      	  
</his:TitleTag>
<his:ContentTag>
<table width="100%" cellpadding="0" cellspacing="1">
<tr>
		<td width="30%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
		<div align="center">
		<bean:message key="diagnosisCode"/>
		</div></font>
		</td>
		<td width="10%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
		&nbsp; &nbsp;</font>
		</td>
		<td width="30%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
		<div align="center">
		<bean:message key="diagnosisName"/>
		</div></font>
		</td>
		
		<td width="10%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
		&nbsp; &nbsp;</font>
		</td>
		</tr>
<tr>
		<td width="30%" class="tdfonthead">
		<div align="center">
		<html:text name="YellowSlipDiagnosisPopupFB" property="searchCode" tabindex="1" onkeypress="return CheckMaxLength(event,this,6,1)"/>
		</div>
		</td>
		
		<td width="10%" class="tdfonthead">
		<div align="center">
		<img src='<his:path src="/hisglobal/images/forward3.gif"/>' tabindex="1" onClick="if(validateSearch()) submitForm('SEARCH');" onkeypress="if(event.keyCode==13) if(validateSearch()) submitForm('SEARCH');">
		</div>
		</td>
		<td width="30%" class="tdfonthead">
		<div align="center">
		<html:text name="YellowSlipDiagnosisPopupFB" property="searchDisease" maxlength="30" tabindex="1" onkeypress="return CheckMaxLength(event,this,30,1)"/>
		</div>
		</td>
		
		<td width="10%" class="tdfonthead">
		<div align="center">
		<img src='<his:path src="/hisglobal/images/forward3.gif"/>' tabindex="1" onClick="if(validateSearch()) submitForm('SEARCH');" onkeypress="if(event.keyCode==13) if(validateSearch()) submitForm('SEARCH');">
		</div>
		</td>
		</tr>

	</table>
<his:statusTransactionInProcess>


<table width="100%" cellpadding="0" cellspacing="1">
   <logic:iterate id="diagnosisCodeList" name="<%=RegistrationConfig.SEARCH_LIST_DIAGNOSIS_CODE%>" indexId="idx" type="hisglobal.utility.Entry">
   
   <%String id=idx.toString(idx.intValue()); %>


			<tr>
				<td width="10%" class="tdfonthead" ><div align="center"><html:radio name="YellowSlipDiagnosisPopupFB" property="selectedCode" value="<%=id %>" onclick="validateCode()" tabindex="1"/></div></td>
				<td width="30%" class="tdfont"><div align="center"><%=(String) diagnosisCodeList.getValue() %> </div></td>
				<td width="30%" class="tdfont" ><div align="center"><%=(String) diagnosisCodeList.getLabel() %></div></td>
			</tr>		
								
	</logic:iterate>
</table>


</his:statusTransactionInProcess>	
</his:ContentTag>
<input type="hidden" name="hmode"/>
<html:hidden name="YellowSlipDiagnosisPopupFB" property="patCrNo"/>
<his:status/>

</html:form>