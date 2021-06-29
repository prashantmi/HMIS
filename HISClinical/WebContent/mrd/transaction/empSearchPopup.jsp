<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.vo.SummonDetailVO"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="mrd.transaction.controller.fb.SummonDtlFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<%@page import="mrd.MrdConfig"%>

<script type="text/javascript">

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function closeForm()
{
	self.close();
}

function validateSearch(mode)
{
	if(document.getElementsByName("str_first_name")[0].value=="" && document.getElementsByName("str_middle_name")[0].value=="" && document.getElementsByName("str_last_name")[0].value=="")
	{
		alert("Please enter employee name");
		document.getElementsByName("str_first_name")[0].focus();
		return false;
	}
	submitPage(mode);
}



function getEmployeeDtl(mode)
{
	var len=document.getElementsByName("empIdArray").length;
	//alert("len "+len);
	
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("empIdArray")[i].checked)
		{
			//alert("array value "+ document.getElementsByName("empGenderCodeArray")[i].value);
			//alert(opener.document.getElementsByName("empGenderCode")[0].value);
			opener.document.getElementsByName("empName")[0].value=document.getElementsByName("empNameArray")[i].value;
			opener.document.getElementsByName("empDesignation")[0].value=document.getElementsByName("empDesigArray")[i].value;
			opener.document.getElementsByName("empGenderCode")[0].value=document.getElementsByName("empGenderCodeArray")[i].value;
			opener.document.getElementsByName("empAddress")[0].value=document.getElementsByName("empAddressArray")[i].value;
			opener.document.getElementsByName("empAge")[0].value=document.getElementsByName("empAgeArray")[i].value;
			opener.document.getElementsByName("selectedEmpId")[0].value=document.getElementsByName("empIdArray")[i].value;
			//alert("array value "+ document.getElementsByName("empGenderCodeArray")[i].value);
			//alert(opener.document.getElementsByName("empGenderCode")[0].value);
		}
	}
	
	
	
	
	closeForm();
}

function clearForm()
{
	document.getElementsByName("str_first_name")[0].value="";
	document.getElementsByName("str_middle_name")[0].value="";
	document.getElementsByName("str_last_name")[0].value="";
}
 
</script>
<body >
		

<his:TransactionContainer>
		<html:form action="/summonAssignmentDtl">
		
		<his:TitleTag name="Employee Search">
		</his:TitleTag>
		<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="1">
		<tr>
			<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="fname"/></b></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
				<html:text name="SummonAssigmentFB"  maxlength="32" size="30" property="str_first_name" styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" />
	  			</div>
	  		</td>
	  		<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="mname"/></b></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
				<html:text name="SummonAssigmentFB"  maxlength="32" size="30" property="str_middle_name" styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" />
	  			</div>
	  		</td>
	  		<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="lname"/></b></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
				<html:text name="SummonAssigmentFB"  maxlength="32" size="30" property="str_last_name" styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" />
	  			</div>
	  		</td>
	  		
	  	</tr>
	  	</table>
		</his:ContentTag>
		<logic:notEmpty name="<%=MrdConfig.SEARCH_EMPLOYEE_lIST %>">
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="tdfonthead" width="5%" >
			  		<div align="center">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="select"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	<td class="tdfonthead" width="20%" >
			  		<div align="center">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="empName"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	<td class="tdfonthead" width="20%" >
			  		<div align="center">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="empDesignation"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	<td class="tdfonthead" width="20%" >
			  		<div align="center">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="gender/age"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	<td class="tdfonthead" width="20%" >
			  		<div align="center">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="empAddress"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
			</tr>
			<logic:iterate id="summonDtlVO" indexId="i" name="<%=MrdConfig.SEARCH_EMPLOYEE_lIST %>" type="hisglobal.vo.SummonDetailVO">
			<tr>
				<td class="tdfont" width="5%" >
				 	<div align="center">
				 		<html:radio property="empIdArray" value="<%=summonDtlVO.getEmpId() %>" onclick="getEmployeeDtl('GETEMPLOYEE')"></html:radio>
				 		
				 	</div>
				 </td>
				 <td class="tdfont" width="20%" >
				 	<div align="center">
				 		<bean:write name="summonDtlVO" property="empName"/>
				 		<html:hidden name="SummonAssigmentFB" property="empNameArray" value="<%=summonDtlVO.getEmpName() %>"/>
				 	</div>
				 </td>	
				 <td class="tdfont" width="20%" >
				 	<div align="center">
				 		<bean:write name="summonDtlVO" property="empDesignation"/>
				 		<html:hidden name="SummonAssigmentFB" property="empDesigArray" value="<%=summonDtlVO.getEmpDesignation() %>"/>
				 	</div>
				 </td>	
				 <td class="tdfont" width="20%" >
				 	<div align="center">
				 	<%String genderAndAge=summonDtlVO.getEmpGender()+"/"+summonDtlVO.getEmpAge(); %>
				 		<%=genderAndAge %>
				 		<html:hidden name="SummonAssigmentFB" property="empGenderCodeArray" value="<%=summonDtlVO.getEmpGenderCode() %>"/>
				 		<html:hidden name="SummonAssigmentFB" property="empAgeArray" value="<%=summonDtlVO.getEmpAge() %>"/>
				 	</div>
				 </td>	
				 <td class="tdfont" width="20%" >
				 	<div align="center">
				 		<bean:write name="summonDtlVO" property="empAddress"/>
				 		<html:hidden name="SummonAssigmentFB" property="empAddressArray" value="<%=summonDtlVO.getEmpAddress() %>"/>
				 	</div>
				 </td>		
			</tr>
			</logic:iterate>
		</table>
		</logic:notEmpty>
		
		<his:ButtonToolBarTag>
	 		<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onclick="validateSearch('SEARCH');" >
	 		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) closeForm();" tabindex="1" onclick ="closeForm();">
	 		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
 		</his:ButtonToolBarTag>
		
		
		
		

	<html:hidden name="SummonAssigmentFB" property="hmode" />
	
	
	
</html:form>
</his:TransactionContainer>


</body>
</html>