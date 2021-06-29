<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="hisglobal.hisconfig.Config"%>
<html>
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

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
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
		//alert("Please Select : "+str)
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
	
	function diplayFunc()
	{
	if(document.getElementsByName('hospitalDiseaseCode')[0].value!=-1)
		{
	 	document.getElementById("displayButton").style.display='block';
		 }  
	}
	function diplayStatus()
	{
	if(document.getElementsByName('hospitalDiseaseCode')[0].value!=-1)
		{
			document.getElementById("recordStatus").style.display='block';
		}
	}
	function submitForm1(mode)
	{
		if(document.getElementsByName('hospitalDiseaseCode')[0].value!=-1)
		{
	 	document.getElementById("displayButton").style.display='block';
		 }  
     document.getElementsByName('hmode')[0].value=mode;
     document.forms[0].submit();
	 
	}
	function populate(selectedarray)
	{
		
		var strHtml="";
		elem = document.getElementById("divDiagnosisList");
		for(i=0;i<selectedarray.length;i++)
		{
			arr= selectedarray[i].split("|");
			strHtml=strHtml+ "<input type='hidden' name='diagnosticCode' value='"+arr[0]+"'/><input type='hidden' name='diagnosticName' value='"+arr[1]+"'/>";
		}  
      elem.innerHTML= strHtml;
      
      submitForm1("SAVE");      
}  
	
	
	
	
</script>

<body onload="diplayFunc(),diplayStatus()">

<html:form action="/master/IcdHospitalMaster">

<his:TransactionContainer>

<%@ page import ="java.util.*,registration.*,hisglobal.vo.*,hisglobal.presentation.*,opd.*" %>
	


<%
		String deptLabel="";
		String deptControl="";
		String unitLabel="";
		String unitControl="";
%>

  
	<his:TitleTag name="ICD Hospital Master">			
	</his:TitleTag> 

<table width="100%" border="0" cellspacing="1" cellpadding="0"> 
  <tr>
  <html:hidden name="IcdHospitalMasterFB" property="hmode"/>
  	<td width="50%" class="tdfonthead" >
      <div id="divUnitLabel" align="right" style="<%= unitLabel %>">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <font color="#FF0000">*</font>
	  <b>
	  <bean:message key="hospitaldiseases"/>
	  </b>
	  </font>
	  </div>
      
      </td>  
      
      <td width="50%" class="tdfont" >	  
	  <div id="divDeptControl" align="left" style="<%= deptControl %>">
	  
	  <html:select name="IcdHospitalMasterFB" tabindex="1" property="hospitalDiseaseCode" styleClass="registrationCmb" onchange="submitForm1('GETDTL')">
	  
			<html:option value="-1">Select Value</html:option>
			<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_HOS_DISEASE_LIST%>" >
  			<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_HOS_DISEASE_LIST%>" property="value" labelProperty="label" />
  			</logic:present>
	   </html:select>
	  </div>
	  
	  
      
       </td>
                  
  </tr>  
  <logic:equal name="IcdHospitalMasterFB" property="displayList" value="true">
  			<tr>	 
		  	   <td width="50%" class="tdfonthead">
				     <div align="center">
				      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				       <bean:message key="diseaseCode" />&nbsp;
				      </font>
				     </div>
				   </td>
				   <td width="50%" class="tdfonthead">
				     <div align="center">
				      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				       <bean:message key="diseaseName" />&nbsp;
				      </font>
				     </div>
				   </td> 
				  </tr>
  <logic:iterate id="List" name="<%=OpdConfig.OPD_ICD_DISEASE_LIST_BY_HOSDIS %>" type="hisglobal.utility.Entry">
	<tr>	
		<td class='tdfont'  width='50%'><div align='center'><%=List.getValue() %></div></td>
		<td nowrap="nowrap" class='tdfont' width='50%'><div align='center'><%=List.getLabel() %></div></td>	
	</tr>
</logic:iterate>
</logic:equal>

<logic:equal name="IcdHospitalMasterFB" property="displayList" value="false">
<tr>
	<td colspan='2'>
		<div align='center' style="display:none" id="recordStatus">
			<font color="red"><b>No Record Found</b></font>
		</div>
	</td>
</tr>
</logic:equal>

</table>
</his:TransactionContainer>
<div id="divDiagnosisList">
</div>

<table width="80%" border="0" cellspacing="1" cellpadding="0" align="center"><tr><td> 
<his:ButtonToolBarTag>
	<table>
	<tr>
	<td>
	<div id="displayButton" style="display:none" >
	<logic:equal name="IcdHospitalMasterFB" property="displayList" value="false">
	<img class="button" style="cursor:pointer" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>'onkeypress="if(event.keyCode==13) openPopup('<his:path src="/registration/episodeActPopup.cnt"/>',event,300,600);" onclick="openPopup('<his:path src="/registration/episodeActPopup.cnt"/>',event,300,600);" tabindex="1">
	</logic:equal>
	<logic:equal name="IcdHospitalMasterFB" property="displayList" value="true">
	<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) openPopup('<his:path src="/registration/episodeActPopup.cnt"/>',event,300,600);" onclick="openPopup('<his:path src="/registration/episodeActPopup.cnt"/>',event,300,600);" tabindex="2">
	</logic:equal>
	</div>
	</td>
	<td>
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
    </td>
    </tr>
    </table>
    </his:ButtonToolBarTag>
    </td></tr></table>


<center><b><his:status/></b></center>
</html:form>
</body>
</html>