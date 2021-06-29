<%@ page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="java.util.*" %>
<%@page import="org.apache.struts.tiles.ComponentContext"%>
<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.presentation.TabGroup"%>
<%@page import="hisglobal.presentation.TabGroupSupport"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/tab.css"/>
<his:css src="/hisglobal/utility/generictemplate/css/newDropDownSrch.css"/>
 
<script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/commonDesigner.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/newDropDownSrch.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script> 
window.history.forward()
<%
   TabGroup objTabGroup=(TabGroup)request.getAttribute(OpdConfig.TAB_GROUP);
%>

function <%=objTabGroup.getJsOnClickFuncName()%>(mode)
{
	 //PragyaDesigner.showData(document.getElementById("frmDeskMain"));
	document.getElementById("frmDeskMain").<%=objTabGroup.getHtmlModeFieldName()%>.value=mode;
	//alert("deskmode"+document.forms[0].deskmode.value);
	  //alert("hmode: "+document.forms[0].hmode.value);
	document.getElementById("frmDeskMain").submit();
}

</script>

<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td>
	<div class="tabGroup">
		<span class="tabGroupTop"> 
			<span>
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td width="100%" nowrap="nowrap" >
				<ul id="tabs">								
				<% 
	              
	              Collection col=objTabGroup.getTabsCollection();				
	               Iterator it= col.iterator();
	               while(it.hasNext())
	               {
		                 TabGroupSupport objTab=(TabGroupSupport)it.next();                

		                 if(objTab.getStatus()==TabGroupSupport.TAB_SELECTED)
	                 {
	                	 
	                	  %>
	                	 <li class="current">
	                	 <span><%=objTab.getTabLabel()%></span>
	                	 </li>
	                 <%                         	 
	                 }
	                 else
	                 {
	                	                	
	                	 %>	             	 
	                	 <li>
	                	 
	                     <a  href="javascript:<%=objTabGroup.getJsOnClickFuncName()%>('<%=objTab.getTabName()%>');"  ><%=objTab.getTabLabel()%></a>                
	                     </li>
	                     <%              	 
	                 }                
	                }				
					%>
				</ul>
 				</td>
					</tr>
				</table>
			</span>
		</span>
	</div> 
</td></tr></table>


<%ComponentContext compContext = (ComponentContext)pageContext.getAttribute("org.apache.struts.taglib.tiles.CompContext", 2);
String body=(String)compContext.getAttribute("body");
String action=(String)compContext.getAttribute("action");


%>

<jsp:include page="<%=action %>" flush="true"></jsp:include>
   
<his:status/>
	
