<%try{ %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page autoFlush="true" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="mrd.MrdConfig"%>
<%@page import="java.util.List"%>
<his:css src="/hisglobal/css/tab.css" />

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
 <!-- JS Added -->
<his:javascript src="/hisglobal/js/utilityFunctions.js" />

<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function setSelectedUnit(obj){
	//alert(obj.value)
	if(document.getElementById("deptUnitDiv"))
		document.getElementById("deptUnitDiv").style.display="none";
	document.getElementById("crNoTagDiv").style.display="block";
	document.getElementsByName("departmentUnitCode")[0].value=obj.value
	//alert("document.getElementsByName(\"departmentUnitCode\")[0].value" +document.getElementsByName("departmentUnitCode")[0].value)
}
function submitFormToCancel(mode){
	document.forms[0].hmode=mode
	document.forms[0].submit()
}	
function submitCancel(mode){
	//alert("clear clicked")
	document.getElementsByName("isNew")[0].value="0"
	document.getElementsByName("departmentUnitCode")[0].value="-1"
	document.getElementsByName("departmentUnitCode")[1].value="-1"
	document.forms[0].hmode=mode
	document.forms[0].submit()
	
	/* Page reloded to clear data */
	location = self.location
}	

window.onload=function(){
	
	if(document.getElementsByName("isNew")[0].value=="1"){
		setSelectedUnit(document.getElementsByName("departmentUnitCode")[1])
	}	
}
</script>	
<html:form action="/emrDesk">
<his:TitleTag name="EMR Details">
</his:TitleTag>

<input type="hidden" name="departmentUnitCode"/>
<his:statusNew>
<%List list=(List)session.getAttribute(MrdConfig.ESSENTIAL_DEPARTMENT_UNIT_LIST);%>
<%if(list!=null && list.size()>1){%>
<div id="deptUnitDiv" style="display: none;">
	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td class="tdfonthead" width="50%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="dept/unit"/>
					</font>
				</td>
				<td class="tdfont" width="50%"> 
				<html:select property="departmentUnitCode" onchange="setSelectedUnit(this)" styleClass="regcbo" style="width:180px;"> 
					<html:option value="-1">Select</html:option>
					<logic:present name="<%=MrdConfig.ESSENTIAL_DEPARTMENT_UNIT_LIST %>">
					<html:options collection="<%=MrdConfig.ESSENTIAL_DEPARTMENT_UNIT_LIST %>" property="value" labelProperty="label"/>
					</logic:present>
				</html:select>
				</td>
			</tr>	
		</table>
	</his:ContentTag>
	
	<his:ButtonToolBarTag>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('CANCEL');" onclick ="submitForm('CANCEL');">
	</his:ButtonToolBarTag>
</div>
<%} %>	
</his:statusNew>

<div id="crNoTagDiv" style="display: block">

<his:InputCrNoTag name="EmrCommonDeskFB"></his:InputCrNoTag>
<his:ButtonToolBarTag>
<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor:pointer" onkeypress="if(event.keyCode==13) cancelFunc();" onclick ="cancelFunc();">
<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer"   tabindex="1" onclick ="submitCancel('NEW')" onkeypress="if(event.keyCode==13) submitCancel('NEW');">
</his:ButtonToolBarTag>
<html:hidden name="EmrCommonDeskFB" property="hmode" value="unspecified" />
<html:hidden name="EmrCommonDeskFB" property="isNew"/>
</div>
<his:status/>
</html:form>
<%}catch(Exception e){e.printStackTrace();}%>