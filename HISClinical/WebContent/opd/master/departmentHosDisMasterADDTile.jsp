
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>


<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>    

<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/opd/opdJs/pagination.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>

<script>
function validatePage(mode)
{
	document.getElementsByName("hmode")[0].value='ADD';;
	document.forms[0].submit();	
}
function submitForm()
{	//alert(document.forms[0].numberOfSelection.value)
	self.document.getElementsByName("hmode")[0].value='SAVE';
	
	self.document.forms[0].submit();
	//alert("after popup submit");
	self.close();
	document.getElementsByName("hmode")[0].value='GETDTL';
	opener.document.forms[0].submit();	
}

function checkNumberOfSelection(elem)
{
	document.forms[0].numberOfSelection.value=parseInt(document.forms[0].numberOfSelection.value) + 1;

	var valid=true;
	if(document.getElementsByName("hosdisType")[0].checked)
	{
		if(document.forms[0].numberOfSelection.value>2)
		{
		alert("More than two Disease can not be added at a time");
		document.forms[0].numberOfSelection.value=parseInt(document.forms[0].numberOfSelection.value) - 1;
		elem.checked=false;
		valid=false;
		}
	}
	if(document.getElementsByName("hosdisType")[1].checked)
	{
		if(document.forms[0].numberOfSelection.value>5)
		{
		alert("More than five sub Diseases can not be added at a time");
		document.forms[0].numberOfSelection.value=parseInt(document.forms[0].numberOfSelection.value) - 1;
		elem.checked=false;
		valid=false;
		}
	}	
	return valid;
}

function saveHospitalDisease()
{
	//alert("inside save hospital disease");
	var flag = false;
	var lengthOfCheckbox=document.getElementsByName("selectedCheckbox");
	for(var i=0;i<lengthOfCheckbox.length;i++)
	{
		if(lengthOfCheckbox[i].checked )
		{
			flag=true;
			break;
		}
	}
	
	if(!flag)
	{
		alert("Please Select at Least One Hospital Disease ..");		
		return;
	}
	submitForm();
}

</script>

<body>

<html:form action="/master/DeptHosDisMaster">

 
<his:TransactionContainer>

<%@ page import ="java.util.*,registration.*,hisglobal.vo.*,hisglobal.presentation.*,opd.*" %>


    


<his:SubTitleTag name="Hospital Disease Type Selection">			
	<table width="100%" align="left"  border="0" cellspacing="1" cellpadding="0">   
  <tr>
		<td class="tdfonthead" align="left" width="25%">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
			<b><bean:message key="hospitalDiseaseType" /> </b> </font>
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
			<bean:message key="disease" /> </font> 
			<html:radio name="DeptHosDisMasterFB" property="hosdisType" tabindex="1" 
			value="<%=OpdConfig.CHOICE_DISEASE%>" onclick="validatePage('ADD')" /> 
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
			<bean:message key="subDisease" /> </font> 
			<html:radio name="DeptHosDisMasterFB" property="hosdisType" tabindex="1"
			value="<%=OpdConfig.CHOICE_SUBDISEASE%>" onclick="validatePage('ADD')" />
		</td>
  </tr>
 </table>
</his:SubTitleTag> 
<his:ContentTag>
  
  
 

<logic:notEmpty name="DeptHosDisMasterFB" property="displayList">
<%int i=0;
int noOfrecordsperpage=Integer.parseInt(opd.master.controller.fb.DepartmentHosDisMasterFB.recordPerPage);
System.out.println("noOfrecordsperpage  ="+noOfrecordsperpage);
%>
<logic:iterate id="List" name="DeptHosDisMasterFB" property="displayList" type="hisglobal.utility.Entry">
<%if(i%noOfrecordsperpage==0){if(i/noOfrecordsperpage==0){%>
       <table id='b<%=((i/noOfrecordsperpage)+1)%>' style='display:block' width="100%">
<%}else{%>
       <table id='b<%=((i/noOfrecordsperpage)+1)%>' style='display:none;' width="100%" >
     <%}
 }%>

<tr height="1">
	<td class='tdfonthead' width='5%'><div align='center'><input type="checkbox" name="selectedCheckbox" value='<%=(String) List.getValue() %>'></div></td>
	<td class='tdfonthead'  width='20%'><div align='center'><%=(String) List.getValue() %></div></td>
	<td nowrap="nowrap" class='tdfonthead' width='75%'><div align='center'><%=(String) List.getLabel() %></div></td>
	
</tr>

<%if(i%noOfrecordsperpage==(noOfrecordsperpage-1)){%></table><%}i++;%>

</logic:iterate>
</table>
</logic:notEmpty>
<logic:empty name="DeptHosDisMasterFB" property="displayList">
<table width='100%'>
<tr>
<td class='tdfonthead' colspan='3'  width='100%'><div align='center'><b>NO RECORD FOUND<b></div></td>
</tr>
</table>
</logic:empty>
<table width='100%'>
<tr><td  align="right" Class='HEADER'><%=opd.master.controller.fb.DepartmentHosDisMasterFB.pageString%></td></tr>
 <tr><td  align="center" ><img name="addTest" alt="OK" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' onclick="saveHospitalDisease()"></td></tr>
</table>

</his:ContentTag>



</his:TransactionContainer>

<html:hidden name="DeptHosDisMasterFB" property="hmode"/>
<html:hidden name="DeptHosDisMasterFB" property="departmentCode"/>
<html:hidden name="DeptHosDisMasterFB" property="departmentUnitCode"/>
<html:hidden name="DeptHosDisMasterFB" property="choice"/>
<html:hidden name="DeptHosDisMasterFB" property="currentPageNo"/>
<html:hidden name="DeptHosDisMasterFB" property="recordPerPage"/>
<html:hidden name="DeptHosDisMasterFB" property="pagesPerBlock"/>
<html:hidden name="DeptHosDisMasterFB" property="currentblock"/>
<html:hidden name="DeptHosDisMasterFB" property="valueChoice"/>
<html:hidden name="DeptHosDisMasterFB" property="numberOfSelection"/>

</html:form>
<center><b><his:status/></b></center>
</body>
</html>