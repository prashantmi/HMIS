<!-- 
/**
 * @copyright CDAC
 * @developer Hruday Meher
 */
-->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ page import ="opd.*" %>

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

<his:javascript src="/opd/opdJs/opdAjax.js"/>
<his:javascript src="/opd/opdJs/opd.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
var isPopulated=false;
var queryString;
	function sendData(valSelected)
	{
		alert("inside send")
	var url='/HISClinical/opd/opdDiagnosis.cnt?hmode=POPULATE&selectedCode='+valSelected+'&icdNHospitalFlagValue='+document.forms[0].icdNHospitalFlagValue.value;
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
  	 var index=parseInt(opener.document.forms[0].numberOfRow.value);
  	
	if(document.forms[0].icdNHospitalFlagValue.value==0)
  	 {
  	 opener.document.getElementsByName('icdCode')[index-1].value=icd;
   	 opener.document.getElementsByName('dignosisName')[index-1].value=name;
   	 }
   	 else
   	 {
   	 opener.document.getElementsByName('hospitalDiagnosisCode')[index-1].value=icd;
   	 opener.document.getElementsByName('hospitalDiagnosisName')[index-1].value=name;
   	 }
   	 
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
	var valSelected=-1;
	var icd = "";
	var name = "";
	
	// var isPopulated=false;
	for(i=0;i<self.document.getElementsByName('selectedCode').length;i++)
	{
		if(self.document.getElementsByName('selectedCode')[i].checked)
		{
			valSelected=self.document.getElementsByName('selectedCode')[i].value;
			icd = self.document.getElementsByName('searchListDrugId')[i].value;
			name = self.document.getElementsByName('searchListDrugName')[i].value; 
			break;
		}
	}
	if(valSelected!="-1")
	{
		//sendData(valSelected);
		var index=parseInt(opener.document.forms[0].numberOfRow.value);
		if(document.forms[0].icdNHospitalFlagValue.value==0)
		{
			opener.document.getElementsByName('icdCode')[index-1].value=icd;
			opener.document.getElementsByName('dignosisName')[index-1].value=name;
		}
		else
		{
			opener.document.getElementsByName('hospitalDiagnosisCode')[index-1].value=icd;
			opener.document.getElementsByName('hospitalDiagnosisName')[index-1].value=name;
		}
 		//isPopulated=true;
		window.close();
	}	
}

/*function validateCode()
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
	
}*/

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
<html:form action="/opdDiagnosis" >
<his:TitleTag>

		  <his:name>
		  <bean:message key="search"/>
		 
      	  </his:name>
      	  
</his:TitleTag>

<his:ContentTag>
<table width="100%" cellpadding="0" cellspacing="1">
<tr>
		<td width="30%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="diagnosisCode"/>
				</font>
			</div>
		</td>
		<td width="10%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
		&nbsp; &nbsp;</font>
		</td>
		<td width="30%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="diagnosisName"/>
				</font>
			</div>
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
		<html:text name="OpdDiagnosisFB" property="searchCode" tabindex="1" maxlength="6" onkeypress="return validateAlphaNumOnly(this,event)" />
		</div>
		</td>
		
		<td width="10%" class="tdfonthead">
		<div align="center">
		<img src='<his:path src="/hisglobal/images/forward3.gif"/>' tabindex="1" onClick="if(validateSearch()) submitForm('SEARCH');" onkeypress="if(event.keyCode==13) if(validateSearch()) submitForm('SEARCH');">
		</div>
		</td>
		<td width="30%" class="tdfonthead">
		<div align="center">
		<html:text name="OpdDiagnosisFB" property="searchDisease" maxlength="100" tabindex="1" onkeypress="return validateAlphaNumOnly(this,event)" />
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

<logic:equal name="OpdDiagnosisFB" property="icdNHospitalFlagValue" value="<%=OpdConfig.CHOICE_ICD_CODE %>">
<table width="100%" cellpadding="0" cellspacing="1">
   <logic:iterate id="icdCodeList" name="<%=OpdConfig.EssentialBO_DIAGNOSIS_CODE_LIST_UNIT_WISE%>" indexId="idx" type="hisglobal.utility.Entry">
   
   <%String id=Integer.toString(idx.intValue()); %>


			<tr>
				<td width="10%" class="tdfonthead" ><div align="center">
					<input type="hidden" name="searchListDrugId" value="<%=(String) icdCodeList.getValue()%>" />
					<input type="hidden" name="searchListDrugName" value="<%=(String) icdCodeList.getLabel()%>" /> 
					<html:radio name="OpdDiagnosisFB" property="selectedCode" value="<%=id %>" onclick="validateCode()"/>
				</div></td>
				<td width="30%" class="tdfont"><div align="center"><%=(String) icdCodeList.getValue() %> </div></td>
				<td width="30%" class="tdfont" ><div align="center"><%=(String) icdCodeList.getLabel() %></div></td>
			</tr>		
								
	</logic:iterate>
</table>
</logic:equal>

<logic:equal name="OpdDiagnosisFB" property="icdNHospitalFlagValue" value="<%=OpdConfig.CHOICE_HOSPITAL_CODE %>">

<table width="100%" cellpadding="0" cellspacing="1">
   <logic:iterate id="hospitalCodeList" name="<%=OpdConfig.EssentialBO_HOSPITAL_DIAGNOSIS_CODE_LIST%>" indexId="idx" type="hisglobal.utility.Entry">
   
   <%String id=Integer.toString(idx.intValue()); %>
					<input type="hidden" name="searchListDrugId" value="<%=(String) hospitalCodeList.getValue()%>" />
					<input type="hidden" name="searchListDrugName" value="<%=(String) hospitalCodeList.getLabel()%>" />

			<tr>
				<td width="10%" class="tdfonthead" ><div align="center"><html:radio name="OpdDiagnosisFB" property="selectedCode" value="<%=id %>" onclick="validateCode()"/></div></td>
				<td width="30%" class="tdfont"><div align="center"><%=(String) hospitalCodeList.getValue() %> </div></td>
				<td width="30%" class="tdfont" ><div align="center"><%=(String) hospitalCodeList.getLabel() %></div></td>
			</tr>		
								
	</logic:iterate>
</table>

</logic:equal>
</his:statusTransactionInProcess>	
</his:ContentTag>


<html:hidden name="OpdDiagnosisFB" property="hmode"/>
<html:hidden name="OpdDiagnosisFB" property="unitDiagnosisCodeType"/>
<html:hidden name="OpdDiagnosisFB" property="icdNHospitalFlagValue"/>



<his:status/>

</html:form>