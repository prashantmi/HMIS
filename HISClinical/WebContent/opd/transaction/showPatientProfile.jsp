<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">

function closeForm()
{
	self.close();
}

function validateForm()
{
	var len=document.getElementsByName("selectedRow").length
	var count=0;
	var selectedProfile="";
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("selectedRow")[i].checked)
		{
			count++;
		}
	}
	
	if(count==0)
	{
		alert("Please Select Profile");
	}
	else
	{
		for(var i=0;i<len;i++)
		{
			if(document.getElementsByName("selectedRow")[i].checked)
			{
				selectedProfile=selectedProfile+ "#"+ document.getElementsByName("selectedRow")[i].value;
			}
		}
	
		selectedProfile=selectedProfile.substring(1,selectedProfile.length);
		opener.document.getElementsByName("selectedProfile")[0].value=selectedProfile;
		self.close();
	}
}


</script>

<html:form action="/opdCosultation">
	<body>

<his:TitleTag name="Add Patient Profile" width="100%">
</his:TitleTag>

<his:SubTitleTag name="Patient Profiles">
</his:SubTitleTag>

<logic:notEmpty name="<%=OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST%>" >
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td width="5%"  class="tdfonthead">
					<div align="center">	
						<input type="checkbox" name="selectCheck" onclick="selectAllCheckboxes(this,'selectedRow')"> 
					</div>
				</td>
			
				
				<td width="20%"  class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="profileHeader"/></b>
						</font>
					</div>
				</td>
				<%-- 
				<td width="75%"  class="tdfonthead">
					<div align="center">	   
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="profileType"/></b>
						</font>
					</div>
				</td>
				--%>
			</tr>
		</table>
		
		<table width="100%" cellpadding="0" cellspacing="0">
			<logic:iterate id="patProfileDtlVO" name="<%=OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST%>" indexId="i" type="hisglobal.vo.PatientProfileDetailVO" >
				<tr>
					<td width="5%"  class="tdfont">
						<div align="center">	           
							<html:checkbox name="OpdConsultationFB" property="selectedRow" value="<%=patProfileDtlVO.getProfileId()%>" ></html:checkbox>
						</div>
					</td>  
					<td width="20%"  class="tdfont" nowrap="nowrap">
						<div align="center">	           
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="patProfileDtlVO" property="profileHeader" />
							</font>
						</div>
					</td>
					<%-- 
					<td width="75%"  class="tdfont">
						<div align="center">	           
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="patProfileDtlVO" property="profileTypeDesc" />
							</font>
						</div>
						
					</td>
					--%>
				</tr>
			</logic:iterate>
		</table>
	</his:ContentTag>
</logic:notEmpty>

<his:ButtonToolBarTag>
	<logic:notEmpty name="<%=OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST%>" >
		<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) validateForm()" onclick="validateForm();" tabindex="1" />		
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="closeForm()" onkeypress="if(event.keyCode==13) closeForm();">
	</logic:notEmpty>	
		<logic:empty name="<%=OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST%>" >
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="closeForm()" onkeypress="if(event.keyCode==13) closeForm();">
		</logic:empty>
</his:ButtonToolBarTag>
</body>

<html:hidden name="OpdConsultationFB"	property="hmode" />
<his:status/>
</html:form>
