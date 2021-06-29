<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ page import ="java.util.*,registration.*,hisglobal.vo.*,opd.*,hisglobal.utility.Entry" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/opd/opdJs/opdAjax.js"/>
<his:javascript src="/opd/opdJs/opd.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
var isPopulated=false;
var queryString;
	function sendData(valSelected)
	{
	var url='/HISClinical/mrd/medicalCertificate.cnt?hmode=POPULATE&selectedCode='+valSelected+'&unitDiagnosisCodeType='+document.forms[0].unitDiagnosisCodeType.value;
	httpRequest("GET",url,true);
	}
	
	function handleResponse()
	{
		
	 if(request.readyState == 4){
	 if(request.status == 200)
	 {
	 var resp=request.responseText;
  	 var name=resp.substring(0,resp.indexOf('^'));
  	 var icd=resp.substring(resp.indexOf('^')+1);
  	
	
   	 opener.document.getElementsByName('sufferingFrom')[0].value=opener.document.getElementsByName('sufferingFrom')[0].value+" "+name;
   	 opener.document.getElementsByName('newSufferingFrom')[0].value=opener.document.getElementsByName('newSufferingFrom')[0].value+" "+name;
   	 opener.document.getElementsByName('modSufferingFrom')[0].value=opener.document.getElementsByName('modSufferingFrom')[0].value+" "+name;
   	 
   	 isPopulated=true;
   	 window.close();
   	}
else
	{
 	alert("A problem occurred with communicating between "+"the XMLHttpRequest object and the server program.");
 	}

 }//end outer if
 
 
}

function validateCode()
{
	valSelected=0;
	isPopulated=false;
	
	for(i=0;i<self.document.forms[0].selectedCode.length;i++)
	{
	
	if(self.document.getElementsByName('selectedCode')[i].checked)
		{
		
		valSelected=self.document.getElementsByName('selectedCode')[i].value;
		break;
		}
	}
	
	sendData(valSelected);	
	
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
<html:form action="/medicalCertificate" >
<his:TitleTag>

		  <his:name>
		  <bean:message key="search"/>
		 
      	  </his:name>
      	  
</his:TitleTag>

<his:ContentTag>
	<table width="100%" cellpadding="0" cellspacing="1">
		<tr>
			<td width="30%" class="addtoolbar"	style="border-top:outset black 2px; border-bottom:inset black 2px;">
				<font color="#000000" size="2"	face="Verdana, Arial, Helvetica, sans-serif">
					<div align="center">
						<bean:message key="diagnosisCode"/>
					</div>
				</font>
			</td>
			<td width="10%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					&nbsp; &nbsp;
				</font>
			</td>
			<td width="30%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="center">
						<bean:message key="diagnosisName"/>
					</div>
				</font>
			</td>
		
			<td width="10%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					&nbsp; &nbsp;
				</font>
			</td>
		</tr>
		<tr>
			<td width="30%" class="tdfonthead">
				<div align="center">
					<html:text name="medicalCertificateFB" property="searchCode" tabindex="1" maxlength="6" onkeypress="return validateAlphaNumOnly(this,event)" />
				</div>
			</td>
		
			<td width="10%" class="tdfonthead">
				<div align="center">
					<img src='<his:path src="/hisglobal/images/forward3.gif"/>' tabindex="1" onClick="if(validateSearch()) submitForm('SEARCH');" onkeypress="if(event.keyCode==13) if(validateSearch()) submitForm('SEARCH');">
				</div>
			</td>
			<td width="30%" class="tdfonthead">
				<div align="center">
					<html:text name="medicalCertificateFB" property="searchDisease" maxlength="100" tabindex="1" onkeypress="return validateAlphaNumOnly(this,event)" />
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

<logic:equal name="medicalCertificateFB" property="unitDiagnosisCodeType" value="<%=OpdConfig.CHOICE_ICD_CODE %>">
<table width="100%" cellpadding="0" cellspacing="1">
   <logic:iterate id="icdCodeList" name="<%=OpdConfig.EssentialBO_DIAGNOSIS_CODE_LIST_UNIT_WISE%>" indexId="idx" type="hisglobal.utility.Entry">
   
   <%String id=idx.toString(idx.intValue()); %>


			<tr>
				<td width="10%" class="tdfonthead" ><div align="center"><html:radio name="medicalCertificateFB" property="selectedCode" value="<%=id %>" onclick="validateCode()"/></div></td>
				<td width="30%" class="tdfont"><div align="center"><%=(String) icdCodeList.getValue() %> </div></td>
				<td width="30%" class="tdfont" ><div align="center"><%=(String) icdCodeList.getLabel() %></div></td>
			</tr>		
								
	</logic:iterate>
</table>
</logic:equal>

<logic:equal name="medicalCertificateFB" property="unitDiagnosisCodeType" value="<%=OpdConfig.CHOICE_HOSPITAL_CODE %>">

<table width="100%" cellpadding="0" cellspacing="1">
   <logic:iterate id="hospitalCodeList" name="<%=OpdConfig.EssentialBO_HOSPITAL_DIAGNOSIS_CODE_LIST%>" indexId="idx" type="hisglobal.utility.Entry">
   
   <%String id=idx.toString(idx.intValue()); %>


			<tr>
				<td width="10%" class="tdfonthead" ><div align="center"><html:radio name="medicalCertificateFB" property="selectedCode" value="<%=id %>" onclick="validateCode()"/></div></td>
				<td width="30%" class="tdfont"><div align="center"><%=(String) hospitalCodeList.getValue() %> </div></td>
				<td width="30%" class="tdfont" ><div align="center"><%=(String) hospitalCodeList.getLabel() %></div></td>
			</tr>		
								
	</logic:iterate>
</table>

</logic:equal>
</his:statusTransactionInProcess>	
</his:ContentTag>


<html:hidden name="medicalCertificateFB" property="hmode"/>
<html:hidden name="medicalCertificateFB" property="unitDiagnosisCodeType"/>



<his:status/>

</html:form>