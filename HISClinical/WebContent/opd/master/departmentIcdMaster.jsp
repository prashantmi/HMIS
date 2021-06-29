
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="hisglobal.hisconfig.Config"%>
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
    
<his:javascript src="/registration/js/popup.js"/>
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
	function showDeptUnit()
	{
		if(document.getElementsByName('choice')[0].checked)
		{
  			document.getElementById('divDeptLabel').style.display="block";
			document.getElementById('divDeptControl').style.display="block";
			document.getElementById('divUnitLabel').style.display="none";
			document.getElementById('divUnitControl').style.display="none";
			document.getElementsByName("departmentUnitCode")[0].value='-1';
	 	}else{
	  		document.getElementById('divDeptLabel').style.display="none";
			document.getElementById('divDeptControl').style.display="none";
			document.getElementById('divUnitLabel').style.display="block";
			document.getElementById('divUnitControl').style.display="block";
			document.getElementsByName("departmentCode")[0].value='-1';
		}
	}
	
	
	
	function comboValidation(obj, str)
	{	var valid= true
		if(obj.value==-1)
		{
		alert("Please Select : "+str)
		valid=false
		}
	return valid
	}
	
	function validateDeptIcdMaster()
	{
	
		if(document.getElementsByName('choice')[0].checked)
		{
			valid=comboValidation(document.getElementsByName("departmentCode")[0],"Department")
			//alert("dept choice:"+document.getElementsByName('choice')[0].value)
			document.getElementsByName("valueChoice")[0].value=document.getElementsByName('choice')[0].value
		}
		if(document.getElementsByName('choice')[1].checked)
		{
		//	alert("dept choice:"+document.getElementsByName('choice')[1].value)
			valid=comboValidation(document.getElementsByName("departmentUnitCode")[0],"Unit")
			document.getElementsByName("valueChoice")[0].value=document.getElementsByName('choice')[1].value
		}
	//	alert ("end validateDeptIcdMaster"+document.getElementsByName("valueChoice")[0].value)
		return valid
	}
	
	function validateSearch()
	{
		valid=false;
		valid=isEmpty(document.forms[0].searchKey,"Enter Search Code")
		return valid;
	}
	
	
	
</script>

<body>

<html:form action="/master/DeptIcdMaster">

 <his:TransactionContainer>

<%@ page import ="java.util.*,registration.*,hisglobal.vo.*,hisglobal.presentation.*,opd.*" %>
	


<%		String deptLabel="";
		String deptControl="";
		String unitLabel="";
		String unitControl="";
%>


<logic:equal name="DeptIcdMasterFB" property="choice" value="<%=OpdConfig.CHOICE_DEPARTMENT%>">
  			<% deptLabel="display:block";
			 deptControl="display:block";
			 unitLabel="display:none";
			 unitControl="display:none";  %>
  </logic:equal>
  <logic:equal name="DeptIcdMasterFB" property="choice" value="<%=OpdConfig.CHOICE_UNIT%>">
  		<%	 deptLabel="display:none";
			 deptControl="display:none";
			 unitLabel="display:block";
			 unitControl="display:block";  %>
  </logic:equal>
  



<his:TitleTag name="Department ICD Master">			
</his:TitleTag> 

  <table width="100%" border="0" cellspacing="1" cellpadding="0">   
  
  <tr>
		<td class="tdfonthead" align="left" width="50%">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
			<b><bean:message key="dept/unit" /> </b> </font>
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
			<bean:message key="department" /> </font> 
			<html:radio name="DeptIcdMasterFB" property="choice" tabindex="1" 
			value="<%=OpdConfig.CHOICE_DEPARTMENT%>" onclick="showDeptUnit()" /> 
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
			<bean:message key="unit" /> </font> 
			
			<html:radio name="DeptIcdMasterFB" property="choice" tabindex="1"
			value="<%=OpdConfig.CHOICE_UNIT%>" onclick="showDeptUnit()" />
		</td>
		<td class="tdfont" align="left" width="50%"></td>
  </tr>
  
  <tr>
      <td width="50%" class="tdfonthead">
	  <div id="divDeptLabel" align="right" style="<%= deptLabel %>"> 	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <font color="#FF0000">*</font>
	  <b>
	  <bean:message key="department"/>
	  </b>
	  </font>
	  </div>
	 
	  <div id="divUnitLabel" align="right" style="<%= unitLabel %>">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <font color="#FF0000">*</font>
	  <b>
	  <bean:message key="unit"/>
	  </b>
	  </font>
	  </div>
      
      </td>  
      
      <td width="50%" class="tdfont" >	  
	  <div id="divDeptControl" align="left" style="<%= deptControl %>">
	  
	  <html:select name="DeptIcdMasterFB" tabindex="1" property="departmentCode" styleClass="registrationCmb" onchange="if(validateDeptIcdMaster()) submitForm('GETDTL')">
			<html:option value="-1">Select Value</html:option>
			<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT%>" >
  			<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT%>" property="value" labelProperty="label" />
  			</logic:present>
	   </html:select>
	     
	  </div>
	  
	  <div id="divUnitControl" align="left" style="<%= unitControl %>">
	  <html:select name="DeptIcdMasterFB" tabindex="1" property="departmentUnitCode" styleClass="registrationCmb" onchange="if(validateDeptIcdMaster()) submitForm('GETDTL')">
			<html:option value="-1">Select Value</html:option>
			<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_SEATID%>" >
  			<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_SEATID%>" property="value" labelProperty="label" />
  			</logic:present>
	   </html:select>
	     
	  </div>
      
       </td>
                  
  </tr>
</table>

<his:statusInProcessWithJsp>
<table width="100%" border="0" cellspacing="1" cellpadding="0">
	
	
	<tr>
		<td width="30%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="group"/></b></font></td>
		<td width="30%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="subGroup"/></b></font></td>
		<td width="30%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="disease"/></b></font></td>
	</tr>
	
	<logic:notEmpty name="DeptIcdMasterFB" property="displayVO">
<%int i=0;
int noOfrecordsperpage=Integer.parseInt(opd.master.controller.fb.DepartmentIcdMasterFB.recordPerPage);
System.out.println("noOfrecordsperpage  ="+noOfrecordsperpage);
%>
<logic:iterate id="arrayDisplayVO" name="DeptIcdMasterFB" property="displayVO" indexId="id" type="hisglobal.vo.DepartmentIcdMasterVO" >
<%int idx=id.intValue(); %>
<%if(i%noOfrecordsperpage==0){if(i/noOfrecordsperpage==0){%>
       <table id='b<%=((i/noOfrecordsperpage)+1)%>' style='display:block' width="100%">
<%}else{%>
       <table id='b<%=((i/noOfrecordsperpage)+1)%>' style='display:none;' width="100%" >
     <%}
 }%>


	<tr>
		
		<td class='tdfonthead'  width='33%'><div align='center'><%= arrayDisplayVO.getIcdGroup() %></div></td>
		<td class='tdfonthead' width='33%'><div align='center'><%=arrayDisplayVO.getIcdSubGroup() %></div></td>
		<td class='tdfonthead' width='33%'><div align='center'><%=arrayDisplayVO.getDisease() %></div></td>
	</tr>

<%if(i%noOfrecordsperpage==(noOfrecordsperpage-1)){%></table><%}i++;%>

</logic:iterate>
</table>
</logic:notEmpty>
<logic:empty name="DeptIcdMasterFB" property="displayVO">
<table width='100%'>
<tr>
<td class='tdfonthead' colspan='3'  width='100%'><div align='center'><b>NO RECORD FOUND<b></div></td>
</tr>
</table>
</logic:empty>
<table width='100%'>
<tr><td  align="right" Class='HEADER'><%=opd.master.controller.fb.DepartmentIcdMasterFB.pageString%></td></tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0">
	<tr>
		<td align="right" >
		<html:select name="DeptIcdMasterFB" tabindex="1" property="searchType" styleClass="registrationCmb" >
			<html:option value="1">Group Name</html:option>
			<html:option value="2">Subgroup Name</html:option>
			<html:option value="3">Disease Name</html:option>
		</html:select>
		<html:text name="DeptIcdMasterFB" tabindex="1" property="searchKey"  maxlength="10" size="10"  onkeypress="return validateAlphaNumericOnly(event)"/>	
		<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onclick="if(validateSearch())submitForm('SEARCH')" tabindex="1">
		</td>
		
		
	</tr>
</table>	
	

</table>

	
</his:statusInProcessWithJsp>



<his:ButtonToolBarTag>
	<img class="button" style="cursor:pointer" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>' alt="Add Record" title="Add Record" onclick="if(validateDeptIcdMaster())openPopup('/HISClinical/opd/master/DeptIcdMaster.cnt?hmode=ADD&departmentCode='+document.forms[0].departmentCode.value+'&valueChoice='+document.forms[0].valueChoice.value+'&departmentUnitCode='+document.forms[0].departmentUnitCode.value ,event,300,800);">
	<img class='button' style="cursor:pointer" src='<his:path src="/hisglobal/images/btn-del.png"/>'  alt="Delete Record"  title="Delete Record" onclick="if(validateDeptIcdMaster())openPopup('/HISClinical/opd/master/DeptIcdMaster.cnt?hmode=REMOVE&departmentCode='+document.forms[0].departmentCode.value+'&valueChoice='+document.forms[0].valueChoice.value+'&departmentUnitCode='+document.forms[0].departmentUnitCode.value ,event,300,800);">
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
       
</his:ButtonToolBarTag>

</his:TransactionContainer>
<html:hidden name="DeptIcdMasterFB" property="hmode"/>
<html:hidden name="DeptIcdMasterFB" property="currentPageNo"/>
<html:hidden name="DeptIcdMasterFB" property="recordPerPage"/>
<html:hidden name="DeptIcdMasterFB" property="pagesPerBlock"/>
<html:hidden name="DeptIcdMasterFB" property="currentblock"/>
<html:hidden name="DeptIcdMasterFB" property="valueChoice"/>
</html:form>
<center><b><his:status/></b></center>
</body>
</html>