
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="inpatient.InpatientConfig"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="hisglobal.presentation.Status"%>
<%@page import="hisglobal.hisconfig.Config"%>
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
<script type="text/javascript">

function submitPage(mode)
{
	elmt=document.getElementsByName("hmode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function closeForm()
{
	self.close();
}

function isFormClose()
{
	var formclose=true;
	 
  	<%     
		Status objStatus=(Status)request.getAttribute(Config.STATUS_OBJECT);
    	if(objStatus.contains(Status.NEW)){
    	%>    	
    		formclose=false;  
    		 	   	
    	<%
    }    
    %>
    
    if(formclose)
    {
    	if(!window.opener.closed)
    	{
    		self.close();
    	}
    }
}

function addRemarks()
{
	var notes="";
	for(var i=0;i<document.getElementsByName("macroHead").length;i++)
	{
		if(document.getElementsByName("macroHead")[i].checked==true)
			notes=document.getElementsByName("macroHead")[i].value;
	}
	opener.document.getElementsByName("patRemarks")[0].value=notes;
	submitPage('SHOWREMARKS');
}
</script>

<html:form action="/inpatientBulletinDetail">
	<body onload="isFormClose()">
		<his:TitleTag name="Add Remarks">
		</his:TitleTag>
		<%List lstNotes=(List)session.getAttribute(InpatientConfig.PROGRESS_NOTES_LIST); 
			if(lstNotes.size()>0){
		%>
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="10%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="select"/>
							</font>
						</div>
					</td>
					<td width="90%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="macroHeader"/>
							</font>
						</div>
					</td>
				</tr>
				<%for(int i=0;i<lstNotes.size();i++)
					{
					Entry notes = (Entry) lstNotes.get(i);		
				%>
					<tr>
						<td class="tdfont">
							<div align="center">
								<html:radio name="InpatientBulletinFB" property="macroHead" value="<%=notes.getLabel() %>" onclick="addRemarks()"></html:radio>
							</div>
						</td>	
						<td class="tdfont">
							<div align="left">
								<%=notes.getValue() %>
							</div>
						</td>	
					</tr>
				<%} %>
			</table>	
		</his:ContentTag>
		<%} %>
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
        
        <html:hidden name="InpatientBulletinFB" property="hmode"/>
        <html:hidden name="InpatientBulletinFB" property="patRemarks"/>
		
	</body>
<his:status/>
</html:form>