
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ page import="registration.*"%>
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>BPL Card Search Popup</title> 
  
<link href="../hisglobal/css/Color.css" rel="stylesheet" type="text/css">
<link href="../hisglobal/css/master.css" rel="stylesheet" type="text/css">
<link href="../hisglobal/css/hisStyle.css" rel="stylesheet" type="text/css">

</head>
<script language="JavaScript" src="../registration/js/registration.js"></script>
<script language="JavaScript" src="../registration/js/validationCommon.js"></script>
<script language="JavaScript" src="../registration/js/validationCalls.js"></script>
<script language="JavaScript" src="../registration/js/commonFunctions.js"></script>
<script language="JavaScript" src="../registration/js/popup.js"></script>

<script>

function clearForm()
{
		document.forms[0].patBPLCardNo.value="";
		document.forms[0].memberName.value="";
		document.forms[0].familyHeadName.value="";
		document.forms[0].bplFamilyId.value="-1";
		document.forms[0].patIsUrban.value="-1";
		document.forms[0].bplRelationCode.value="-1";
		document.forms[0].patGenderCode.value="-1";
}

function searchBPL()
{
		var hmode = "GETBPLSEARCHDETAILS"; 
		var bplCardNo=document.forms[0].patBPLCardNo.value;
		var member=document.forms[0].memberName.value;
		var familyHead=document.forms[0].familyHeadName.value;
		var bplFamilyId=document.forms[0].bplFamilyId.value;
		var areaCat=document.forms[0].patIsUrban.value;
		var familyHeadRelation=document.forms[0].bplRelationCode.value;
		var gender=document.forms[0].patGenderCode.value;
		
		var url = "/HISClinical/registration/newRegistrationDeskDuplicate.cnt?hmode="+hmode+"&bplCardNo="+bplCardNo+"&member="+member+"&familyHead="+familyHead+"&bplFamilyId="+bplFamilyId+"&areaCat="+areaCat+"&familyHeadRelation="+familyHeadRelation+"&gender="+gender;
		ajaxFunction(url,"4");
}
function addBPL(obj,index)
{
		if(typeof(obj)=='undefined')
		{
			window.opener.document.getElementsByName("patBPLCardNo")[0].value=document.forms[0].patBPLCardNo.value;
			window.opener.document.getElementsByName("familyHeadName")[0].value=document.forms[0].familyHeadName.value;
			if(document.forms[0].bplFamilyId.value!='-1')
			window.opener.document.getElementsByName("bplFamilyId")[0].value=document.forms[0].bplFamilyId.value;
			window.opener.document.getElementsByName("patIsUrban")[0].value=document.forms[0].patIsUrban.value;
			window.opener.document.getElementsByName("bplDetailsFound")[0].value="0";
		}
		else
		{
			window.opener.document.getElementsByName("patBPLCardNo")[0].value=document.forms[0].patBPLCardNo.value;
			window.opener.document.getElementsByName("familyHeadName")[0].value=document.getElementsByName("familyHead")[index].value;
			window.opener.document.getElementsByName("bplStateId")[0].value=document.getElementsByName("stateId")[index].value;
			window.opener.document.getElementsByName("bplMMJRKNo")[0].value=document.getElementsByName("mmjrkNo")[index].value;
			window.opener.document.getElementsByName("bplFamilyId")[0].value=document.getElementsByName("familyId")[index].value;
			window.opener.document.getElementsByName("patIsUrban")[0].value=document.getElementsByName("areaCat")[index].value;
			window.opener.document.getElementsByName("bplRelationCode")[0].value=document.getElementsByName("familyHeadRelation")[index].value;
			window.opener.document.getElementsByName("bplDetailsFound")[0].value="1";
		}
		window.close();
}
function getAjaxResponse(res,mode)
{
		if(mode == '4')
		{
			document.getElementById("bplMemberDtl").innerHTML=res;
			document.getElementById("bplMemberDtl").style.display="";
		}
} 
</script>

<body>
<html:form action="/newRegistrationDeskDuplicate.cnt">	
<div class="errMsg" id="errMsg"></div>
<div class="warningMsg" id="warningMsg"></div>
<div class="normalMsg" id="normalMsg"></div>
<his:SubTitleTag name="BPL Description">
</his:SubTitleTag> 
 		 <his:ContentTag>
    		<table width="100%" border="0"  cellspacing="1px" cellpadding="0"> 			
 			<tr> 			
 			<td width="15%" class="LABEL"><bean:message key="patCardNo"/></td>        
            <td width="20%" class="tdfont"><html:text name='NewRegistrationFBDuplicate' property="patBPLCardNo" tabindex="1"></html:text></td>
            <td width="15%" class="LABEL"><bean:message key="memberName"/></td>        
            <td width="20%" class="tdfont"><input type="text" name='memberName' tabindex="1"></td>
            <td width="15%" class="LABEL"><bean:message key="familyHeadName"/></td>        
            <td width="15%" class="tdfont"><input type="text" name='familyHeadName' maxlength='80'  onkeypress="return validateAlphabetsOnly(event,this)" tabindex="1"> </td>
	 	   </tr>
	 	   <tr> 			
 			<td width="15%" class="LABEL"><bean:message key="bplFamilyId"/></td>
            <td width="20%" class="tdfont">
            <html:select name="NewRegistrationFBDuplicate" property='bplFamilyId' styleClass='regcbo' tabindex="1">
				<html:option value='-1'>Select Value</html:option>
				<html:option value='0'>Self</html:option>
				<html:option value='1'>1</html:option>
				<html:option value='2'>2</html:option>
				<html:option value='3'>3</html:option>
				<html:option value='4'>4</html:option>
				<html:option value='5'>5</html:option>
				<html:option value='6'>6</html:option>
				<html:option value='7'>7</html:option>
				<html:option value='8'>8</html:option>
				<html:option value='9'>9</html:option>
				<html:option value='10'>10</html:option>
			</html:select></td>
			<td width="15%" class="LABEL"><bean:message key="areaCategory" /></td>
            <td width="20%" class="tdfont">
            <html:select name="NewRegistrationFBDuplicate" property="patIsUrban" styleClass="regcbo" tabindex="1">
				<html:option value="-1">Select Value</html:option>
				<html:options collection="<%= RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY %>" property="value" labelProperty="label" />
					</html:select></td>
			<td width="15%" class="LABEL"><bean:message key="familyHeadRelation" /></td>
            <td width="15%" class="tdfont">
            <html:select name="NewRegistrationFBDuplicate" property='bplRelationCode' tabindex="1" styleClass='regcbo'>
			<html:option value='-1'>Select Value</html:option>
			<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL %>" property="value" labelProperty="label" />
			</html:select></td>
			</tr>
			<tr>
            <td width="15%" class="LABEL"><bean:message key="gender"/></td>
            <td width="20%" class="tdfont">
            <html:select name="NewRegistrationFBDuplicate" property="patGenderCode" tabindex="1" styleClass="regcbo">
			<html:option value="-1">Select Value</html:option>
			<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER %>" property="value" labelProperty="label" />
			</html:select></td>
			 <td width="15%" class="LABEL"></td>
            <td width="20%" class="tdfont"></td>
             <td width="15%" class="LABEL"></td>
            <td width="15%" class="tdfont"></td>
	 	   </tr>
	 	   </table>
	 	   <div id="bplMemberDtl" style="display: none;"></div>
 		</his:ContentTag>
	
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
	<tr>
		<td align="center">
         <div align="center">
			 <img class="button" src='../hisglobal/images/btn-search.png' style="cursor:pointer;" 
			 onkeypress="if(event.keyCode==13) searchBPL();" 
			 onclick =  "searchBPL();" tabindex="1"/>
			 <img class="button" src='../HIS/hisglobal/images/buttons/btn-ccl.png' style="cursor:pointer;" 
			 onkeypress="if(event.keyCode==13) self.close();" 
			 onclick =  "self.close();" tabindex="1"/>
			 <img class="button" src='../../HIS/hisglobal/images/buttons/btn-clr.png' style="cursor:pointer;" 
			 onkeypress="if(event.keyCode==13) clearForm();" 
			 onclick =  "clearForm();" tabindex="1"/>
         </div>
         </td>
    </tr>
    </table>

<input type= "hidden" name="hmode" value="unspecified"/>
</html:form>
</body>
</html>