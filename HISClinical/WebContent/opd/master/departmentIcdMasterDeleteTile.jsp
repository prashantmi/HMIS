
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
<his:css src="/css/calendar-blue2.css"/>

<script>
function validatePage(mode)
{
 	document.getElementsByName("hmode")[0].value='REMOVE';
	document.forms[0].submit();
	
}



function submitForm()
{	
	self.document.getElementsByName("hmode")[0].value='DELETE';
	var deptcode=opener.document.getElementsByName("departmentCode")[0].value;
	var unitcode=opener.document.getElementsByName("departmentUnitCode")[0].value;
	var valueChoice=opener.document.getElementsByName("valueChoice")[0].value;
	self.document.getElementsByName("departmentCode")[0].value=deptcode;
	self.document.getElementsByName("departmentUnitCode")[0].value=unitcode;
	self.document.getElementsByName("valueChoice")[0].value=valueChoice;
	alert("hmode:popup:"+document.getElementsByName("hmode")[0].value);
	self.document.forms[0].submit();
	alert("after popup submit")
	self.close();
	document.getElementsByName("hmode")[0].value='GETDTL';
	opener.document.forms[0].submit();
	
}

</script>

<body>

<html:form action="/master/DeptIcdMaster">

 
<his:TransactionContainer>

<%@ page import ="java.util.*,registration.*,hisglobal.vo.*,hisglobal.presentation.*,opd.*" %>


    


<his:SubTitleTag name="Icd Code Type Selection">			
	<table width="100%" border="0" cellspacing="1" cellpadding="0">   
  <tr>
		<td class="tdfonthead" align="left" width="25%">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
			<b><bean:message key="icdCodeType" /> </b> </font>
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
			<bean:message key="group" /> </font> 
			<html:radio name="DeptIcdMasterFB" property="icdType" tabindex="1" 
			value="<%=OpdConfig.CHOICE_GROUP%>" onclick="validatePage('ADD')" /> 
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
			<bean:message key="subGroup" /> </font> 
			<html:radio name="DeptIcdMasterFB" property="icdType" tabindex="1"
			value="<%=OpdConfig.CHOICE_SUBGROUP%>" onclick="validatePage('ADD')" />
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
			<bean:message key="disease" /> </font> 
			<html:radio name="DeptIcdMasterFB" property="icdType" tabindex="1" 
			value="<%=OpdConfig.CHOICE_DISEASE%>" onclick="validatePage('ADD')" /> 
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
			<bean:message key="subDisease" /> </font> 
			<html:radio name="DeptIcdMasterFB" property="icdType" tabindex="1"
			value="<%=OpdConfig.CHOICE_SUBDISEASE%>" onclick="validatePage('ADD')" />
		</td>
  </tr>
 </table>
</his:SubTitleTag> 
<his:ContentTag>
  
  
 

<logic:notEmpty name="DeptIcdMasterFB" property="displayList">
<%int i=0;
int noOfrecordsperpage=Integer.parseInt(opd.master.controller.fb.DepartmentIcdMasterFB.recordPerPage);
System.out.println("noOfrecordsperpage  ="+noOfrecordsperpage);
%>
<logic:iterate id="List" name="DeptIcdMasterFB" property="displayList" type="hisglobal.utility.Entry">
<%if(i%noOfrecordsperpage==0){if(i/noOfrecordsperpage==0){%>
       <table id='b<%=((i/noOfrecordsperpage)+1)%>' style='display:block' width="100%">
<%}else{%>
       <table id='b<%=((i/noOfrecordsperpage)+1)%>' style='display:none;' width="100%" >
     <%}
 }%>

<tr height="1">
	<td class='tdfonthead' width='5%'><div align='center'><input type="checkbox" name="selectedCheckbox" value='<%=(String) List.getValue() %>' ></div></td>
	<td class='tdfonthead'  width='20%'><div align='center'><%=(String) List.getValue() %></div></td>
	<td nowrap="nowrap" class='tdfonthead' width='75%'><div align='center'><%=(String) List.getLabel() %></div></td>
	
</tr>

<%if(i%noOfrecordsperpage==(noOfrecordsperpage-1)){%></table><%}i++;%>

</logic:iterate>
</table>
</logic:notEmpty>
<logic:empty name="DeptIcdMasterFB" property="displayList">
<table width='100%'>
<tr>
<td class='tdfonthead' colspan='3'  width='100%'><div align='center'><b>NO RECORD FOUND<b></div></td>
</tr>
</table>
</logic:empty>
<table width='100%'>
<tr><td  align="right" Class='HEADER'><%=opd.master.controller.fb.DepartmentIcdMasterFB.pageString%></td></tr>
<tr><td  align="center" ><img name="addTest" alt="OK" src='<his:path src="/hisglobal/images/btn-del.png"/>' onclick="submitForm()"></td></tr>
</table>

</his:ContentTag>



</his:TransactionContainer>

<html:hidden name="DeptIcdMasterFB" property="hmode"/>
<html:hidden name="DeptIcdMasterFB" property="departmentCode"/>
<html:hidden name="DeptIcdMasterFB" property="departmentUnitCode"/>
<html:hidden name="DeptIcdMasterFB" property="choice"/>
<html:hidden name="DeptIcdMasterFB" property="currentPageNo"/>
<html:hidden name="DeptIcdMasterFB" property="recordPerPage"/>
<html:hidden name="DeptIcdMasterFB" property="pagesPerBlock"/>
<html:hidden name="DeptIcdMasterFB" property="currentblock"/>
<html:hidden name="DeptIcdMasterFB" property="valueChoice"/>
</html:form>
<center><b><his:status/></b></center>
</body>
</html>