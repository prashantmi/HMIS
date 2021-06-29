
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:javascript src="/opd/opdJs/opdAjax.js"/>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script>


function validateChangePatientRoom(mode)
{	
	if(document.forms[0].roomCode.value==-1)
	  {
	  alert("Select Room No.");
	  document.forms[0].roomCode.focus();
	  }else
	if(document.forms[0].remarks.value=="")
	  {
	  alert("Enter Remarks");
	  document.forms[0].remarks.focus();
	  }else
	{  
	  	document.getElementsByName("hmode")[0].value=mode;
		document.forms[0].submit();
	}
	
}

function clearForm()
{
document.forms[0].reset();
}
</script>

<%@page	import="opd.*"%>
<his:TitleTag name="Change Room">
</his:TitleTag>	

	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
<his:statusTransactionInProcess>
	<his:SubTitleTag name="Room Details"> </his:SubTitleTag>
	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
		
		<td width="33%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="old"/>
				<bean:message key="roomNo"/></b>
				</font>
				</div>
	  		</td>
			<td width="33%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="new"/>
				<bean:message key="roomNo"/></b>
				</font>
				</div>
	  		</td>
	  		<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="remarks"/></b>
				</font>
				</div>
	  		</td>
	  		
	  	</tr>
	  		
	  	<tr>
	  	<td width="33%" class="tdfont">
	  			<div align="center">
	  			<b>
	  			<bean:write name="changeRoomPatFB" property="oldRoomName"></bean:write>
	  			</b>
	  			</div>
	  		</td>
	  		
	  		<td width="33%" class="tdfont">
	  			<div align="center">
	  			<html:select name="changeRoomPatFB" tabindex="1" property="roomCode" styleClass="regcbo" >
	  				
	  				<html:option value="-1">Select Value</html:option>
				<logic:present name="<%=OpdConfig.ESSENTIALBO_LIST_CHANGE_ROOM%>">	
					<html:options  collection="<%=OpdConfig.ESSENTIALBO_LIST_CHANGE_ROOM%>" property="value" labelProperty="label" />
	  			</logic:present>
	  			</html:select>
	  			</div>
	  		</td>
	  		
	  		
	  		<td width="33%" class="tdfont">
	  			<div align="center">
	  			<html:text property="remarks" name="changeRoomPatFB" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)"></html:text>
	  			</div>
	  		</td>
	  		</tr>
	  	
	  	</table>
	</his:ContentTag>
	
	
	
</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
<his:statusTransactionInProcess>
		  <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "validateChangePatientRoom('SAVE');" onkeypress="if(event.keyCode==13)validateChangePatientRoom('SAVE');")>
 		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
</his:statusTransactionInProcess>
<his:statusList>
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
</his:statusList>

<his:statusUnsuccessfull>
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
</his:statusUnsuccessfull>

</his:ButtonToolBarTag>	
<html:hidden name="changeRoomPatFB" property="hmode"/>
<html:hidden name="changeRoomPatFB" property="patCrNo"/>
