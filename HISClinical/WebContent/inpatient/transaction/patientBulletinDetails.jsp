<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 
<his:javascript src="/registration/js/dateFunctions.js"/> 
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>
	<his:css src="/hisglobal/css/calendar-blue2.css"/>
<script type="text/javascript">

function validateBulletin(mode)
{
//alert("hi");
	var valid=true;
if(
	
	isSelected(document.forms[0].patStatusCode,"Patient Status") &&
	isEmpty(document.forms[0].patRemarks,"Remarks") 
	 )
	{
	 submitForm(mode);
	}
}
function patientRevoke()
{
if(document.forms[0].patStatusCode.disabled==false)
  {
	document.forms[0].patStatusCode.disabled=true;
	document.forms[0].patRemarks.disabled=true;
  }
  else
  if(document.forms[0].patStatusCode.disabled==true)
  {
	document.forms[0].patStatusCode.disabled=false;
	document.forms[0].patRemarks.disabled=false;
  } 

}	

function submitTile(mode)
{
if(mode=="CLEAR")
   {
    document.forms[0].patStatusCode.value=-1;
	document.forms[0].patRemarks.value="";
	document.forms[0].patRevoke.checked=false;
	document.forms[0].patStatusCode.disabled=false;
	document.forms[0].patRemarks.disabled=false;
   }
else
   {
    submitForm(mode);
   }   

}
function getPatientBulletinDetails()
{

}
</script>

<%@page import="inpatient.InpatientConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>


	<his:SubTitleTag name="All Bulletin Details">
	</his:SubTitleTag>
	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
		
		<td width="15%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="entryDate"/>
				</b>
				</font>
				</div>
	  		</td>
	  		<td width="15%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="patient"/>
				<bean:message key="status"/>
				</b>
				</font>
				</div>
	  		</td>
	  		<td width="55%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="bulletin"/>
				<bean:message key="remarks"/>
				</b>
				</font>
				</div>
	  		</td>
	  		<td width="15%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="enterBy"/>
				</b>
				</font>
				</div>
	  		</td>
	  </tr>
	  <logic:present name="<%=InpatientConfig.INPATIENT_BULLETIN_DETAIL_VO%>">
	  <logic:iterate id="patientBulletinVO" name="<%=InpatientConfig.INPATIENT_BULLETIN_DETAIL_VO%>" type="hisglobal.vo.PatientBulletinDetailVO">
	  <tr>
	  		<td width="15%" class="tdfont">
	  			<div align="center">
	  			<bean:write name="patientBulletinVO" property="entryDate"/>
	  			</div>
	  		</td>
	  		
	  		<td width="15%" class="tdfont">
	  			<div align="center">
	  			<bean:write name="patientBulletinVO" property="patStatusName"/>
	  			</div>
	  		</td>
	  		
	  		<td width="55%" class="tdfont">
	  			<div align="center">
	  			<bean:write name="patientBulletinVO" property="patRemarks"/>
	  			</div>
	  		</td>
	  		
	  		<td width="15%" class="tdfont">
	  			<div align="center">
	  			<bean:write name="patientBulletinVO" property="enterByName"/>
	  			</div>
	  		</td>
	  		
	  			
	  	</tr>
	  	</logic:iterate>	
	  	</logic:present>
	  	</table>
	</his:ContentTag>
	
	</body>
</html>
