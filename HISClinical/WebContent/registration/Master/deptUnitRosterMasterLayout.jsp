<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page autoFlush="true" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="org.apache.struts.tiles.ComponentContext"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/DateValidation.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ page import ="registration.*" %>
<script>

function compareDatewithforeDate(d1,d2,mode,l1,l2){
 //alert("compare called    "+l1 +"      " +l2);
var valid=true;
if(isEmpty(d1,l1) && isEmpty(d2,l2)){
//alert("inside first if of copmparedate "+compareDate(d1,d2, mode));
 //alert("return compareDate(d1,d2, mode)"+compareDate(d1,d2, mode))
 if(compareDate(d1,d2, mode)){
    //alert("valid Date");
		valid = true;
	}
 else {
	 //alert(l1+" should be greater than or equal to  "+l2);
	valid = false;
	}
} 

else
valid=false;
//alert("valid    "+valid);
return valid;
}

	function validateIt(){
		//alert("Validate");
			
		var checkdate=document.getElementsByName("checkdate")[0];
		var effectiveDate=document.getElementsByName("effectDate")[0];		
		//if(!compareDatewithforeDate(effectiveDate,checkdate,2,"Effective Date" ,document.getElementsByName("checkdate")[0].value))
		//	return false;
		
		 if(sysAfter(document.getElementsByName("effectDate")[0],"")==false)
 		return;				
		return checkShiftWeekOfMonthConsistency();
	}
	
	function confirmEcecuteRoster()
	{
	 var answer = confirm ("This will execute the roster for all the departments, \n Are you sure want to do this")
	 if(answer==true)
	 	submitForm('EXECUTE_ROSTER');	  
	}
	
	function clearForm(){
		//alert("clear")
		var elements=document.forms[0].elements
		for(var i=0;i<elements.length;i++){
			if(elements[i].type=="checkbox"){
				//alert(elements[i].value)
				elements[i].checked=false;		
			
			}
		}
	}
	
	
</script>
<html:form action="/master/deptUnitRoster">
<his:TransactionContainer>	
	<his:TitleTag>
		<his:name>
		<bean:message key="titleDeptUnitShiftAssignment"/>
		</his:name>
	</his:TitleTag>
<his:ContentTag>
<table width="100%" cellpadding="1" cellspacing="1">
	<tr>
		<td width="17%" class="tdfonthead">
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="department"/>
		</font>
		</div>
		</td>
		
		<td width="17%" class="tdfont">
		<html:select property="departmentCode" tabindex="1" styleClass="regCbo" onchange="if(this.value!='-1') submitForm('GET_DEPT_UNITS');"> 		  
		<html:option value="-1">Select Value</html:option>
		<html:options collection = "<%=Config.ESSENTIALBO_OPTION_DEPARTMENT%>" property = "value" labelProperty = "label"/>
		</html:select>
		</td>
		
		<td width="17%" class="tdfonthead">
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="unit"/>
		</font>
		</div>
		</td>
		
		<td width="16%" class="tdfont">
		<html:select property="departmentUnitCode" tabindex="1" styleClass="regCbo" onchange="if(this.value!='-1') submitForm('GETROOM');"> 		  
		<html:option value="-1">Select Value</html:option>
		<html:options collection = "<%=Config.ESSENTIALBO_OPTION_DEPT_UNIT %>" property = "value" labelProperty = "label"/>
		</html:select>
		</td>
		
		<td width="16%" class="tdfonthead">
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="roomName"/>
		</font>
		</div>
		</td>
		
		<td width="16%" class="tdfont">
		<html:select property="roomCode" tabindex="1" styleClass="regCbo" onchange="if(this.value!='-1') submitForm('GET_UNIT_ROSTER');"> 		  
		<html:option value="-1">Select Value</html:option>
		<logic:present name="<%=RegistrationConfig.ESSENTIAL_OPTION_ROOM_LIST %>">
		<html:options collection = "<%=RegistrationConfig.ESSENTIAL_OPTION_ROOM_LIST%>" property = "value" labelProperty = "label"/>
		</logic:present>
		</html:select>
		</td>
</tr>
</table>
</his:ContentTag>

<%ComponentContext compContext = (ComponentContext)pageContext.getAttribute("org.apache.struts.taglib.tiles.CompContext", 2);
String body=(String)compContext.getAttribute("body");
String footer=(String)compContext.getAttribute("footer");

%>
<jsp:include page="<%=body %>" flush="true"></jsp:include>
<jsp:include page="<%=footer %>" flush="true"></jsp:include>

<his:status/>
<input type="hidden" name="hmode"/>
<html:hidden name="DeptUnitRosterFB" property="checkdate" />
<html:hidden name="DeptUnitRosterFB" property="roomCapacity" />
<html:hidden name="DeptUnitRosterFB" property="roomCapacityInUnitRoomMst" />
</his:TransactionContainer>
</html:form>