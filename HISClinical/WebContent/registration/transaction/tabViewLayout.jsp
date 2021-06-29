<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
	
<%@page autoFlush="true" %>
<%@ page import="registration.controller.action.*,java.util.*, registration.*" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="org.apache.struts.tiles.ComponentContext"%>

<his:css src="/hisglobal/css/tab.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head> 
<script > 
window.history.forward()
<%
   TabGroup objTabGroup=(TabGroup)request.getAttribute(registration.RegistrationConfig.TAB_GROUP);
%>
function <%=objTabGroup.getJsOnClickFuncName()%>(mode){
	 //alert(mode);
	document.forms[0].<%=objTabGroup.getHtmlModeFieldName()%>.value=mode;
	//alert("deskmode"+document.forms[0].deskmode.value);
	  //alert("hmode: "+document.forms[0].hmode.value);
	document.forms[0].submit();
}


</script>
<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td>
	<div id="tabHeader"  class="tabGroup">
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
		                 Tab objTab=(Tab)it.next();                
	            	   System.out.println("objTab.getStatus()"+objTab.getStatus());	            	   
	                 if(objTab.getStatus()==Tab.TAB_SELECTED)
	                 {
	                	 System.out.println("ssssssssss "+objTab.getTabLabel());
	                	  %>
	                	 <li class="current">
	                	 <span><font size="11px"><b><%=objTab.getTabLabel()%></b></font></span>
	                	 </li>
	                 <%                         	 
	                 }
	                 else
	                 {
	                	                	
	                	 %>	   <% System.out.println("tabname "+objTab.getTabName());%>             	 
	                	 <li>
	                	 
	                     <font size="11px"><a  href="javascript:<%=objTabGroup.getJsOnClickFuncName()%>('<%=objTab.getTabName()%>');"  ><%=objTab.getTabLabel()%></a> </font>               
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


System.out.println("body = "+body);
System.out.println("action = "+action);
%>

<jsp:include page="<%=action %>" flush="true"></jsp:include>
   
	


