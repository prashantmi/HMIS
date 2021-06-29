<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.OpdConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/js/commonFunctions.js"/> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function closeForm()
{
	window.close();
}
function closePopUp()
{
	self.close();
}
function deleteRow(obj)
{
	document.getElementsByName("deleteIndex")[0].value=obj;
	document.getElementsByName("hmode")[0].value="DELETEROW"
			document.forms[0].submit()	
}
</script>

<html:form action="/master/opdIcdMappingMaster">
	<html:hidden name="OpdIcdMappingMasterFB" property="deleteIndex"/>
	<html:hidden name="OpdIcdMappingMasterFB" property="hmode"/>
	<body >
		<his:TitleTag name="Mapped Disease">
		</his:TitleTag>
		<his:ContentTag>
				
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			
			<logic:iterate id="disease" name="<%=OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE%>" indexId="index">
			<tr>
				<td class="tdfont">
					<bean:write name="disease" />
				</td>
				<td class="tdfont" width="3%" >
										<div align="center">
											<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRow(<%=index.toString() %>) ;" onclick=" deleteRow(<%=index.toString() %>)" tabindex="1">
										</div>	
									</td>
			</tr>
			</logic:iterate>
		
		</table>
		</his:ContentTag>
		
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>'  style=cursor:pointer tabindex="1" onclick ="closePopUp()" onkeypress="if(event.keyCode==13) closePopUp()">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm('CANCEL')" onkeypress="if(event.keyCode==13) closeForm('CANCEL')">
        </his:ButtonToolBarTag>
    </body>	
    <logic:empty name="<%=OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE%>">
		<table width="100%" cellpadding="0" cellspacing="1" >
					<tr>
						<td>
						<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>No Disease</b>
						</font>
						</td>
					</tr>
				</table>
	</logic:empty>
</html:form>