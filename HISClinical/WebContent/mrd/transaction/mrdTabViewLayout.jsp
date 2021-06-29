
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page autoFlush="true" %>
<%@ page import="java.util.*" %>

<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="org.apache.struts.tiles.ComponentContext"%>
<%@page import="hisglobal.presentation.TabGroup"%>
<%@page import="hisglobal.presentation.TabGroupSupport"%>

<%@page import="mrd.MrdConfig"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<link href="HISClinical/WebContent/hisglobal/css/tab.css" rel="stylesheet">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
 <style>
 
.tabGroup .tabGroupTop span{
	float:left;
	display: inline;
	background: url('/HISInvestigationG5/hisglobal/images/tl.gif');
	background-repeat: no-repeat;
    background-position: top left;
	padding:1px 10px 1px 10px;
}

.tabGroup .tabGroupTop{
	display: inline;
	clear:both;
	float:left;
	background:#1276b3 url('/HISInvestigationG5/hisglobal/images/tr.gif');
	background-repeat: no-repeat;
    background-position: top right;
	width:90%;
}


#tabs {
    padding-left: 10px;
    margin: 0;
    list-style: none;	
    background-color:CCE6FF;
  
}
/*    background: #CFDCED url("/hisglobal/images/tab-right.gif") no-repeat right top;*/
#tabs li  {
    float: left;
    background: #000000 ;
    background-repeat: no-repeat;
    background-position: top right;
    background-color: #e0ebeb;
    margin: 0 3px 0 0;
    padding: 0;
    	border: 2px solid #FFD700;
 
    padding: 0px 5px; 
     
border-radius: 25px 25px 2px 2px;
   width: 23.1em; /* IE/Win fix */
}

 

/*background: url("images/tab-left.gif") no-repeat left top;*/
#tabs li a  {
    float: left;
    display: block;
    font-size:12px;
    text-decoration: none;
    color:black;
    white-space: nowrap;
	font-weight: bold;
	 color: -webkit-linear-gradient(red, blue); /* For Safari 5.1 to 6.0 */
  color: -o-linear-gradient(red, blue); /* For Opera 11.1 to 12.0 */
  color: -moz-linear-gradient(red, blue); /* For Firefox 3.6 to 15 */
  color: linear-gradient(red, blue); /* Standard syntax */
    background-repeat: no-repeat;
    background-position: top left;
    padding: 5px 15px 4px;
    width: 23.1em; /* IE/Win fix */
     
	-webkit-transition: font-size 1s ease;
	-moz-transition: font-size 1s ease;
	-ms-transition: font-size 1s ease;
	-o-transition: font-size 1s ease;
	transition: font-size 1s ease;
}


 
 
 
/* #tabs li a:hover{
   
    
      color:#A52A2A;
      font-size: 13px;
     
   
}  
 */
  

#tabs .current{
    font-family: verdana, arial, sans-serif;
    font-size:12px;
    text-decoration: none;
    color: red;
    background-color: rex;
    white-space: nowrap;
    background-image: url('/HISInvestigationG5/hisglobal/images/rnd-003366-eff3f7-LT.gif');
    background-repeat: no-repeat;
    background-position: top Left;

}
#tabs .current span{
    font-family: verdana, arial, sans-serif;
    font-size:12px;
    text-decoration: none;
    color: black;
    background-image: url('/HISInvestigationG5/hisglobal/images/rnd-003366-eff3f7-RT.gif');
    background-repeat: no-repeat;
    background-position: top right;
    padding: 5px 15px 4px;
    width: .1em; /* IE/Win fix */
	width: auto;
}

 
 TR {
	FONT-SIZE: 11px; FONT-FAMILY: 'MS Sans Serif', Arial, Helvetica, 'sans-serif'
}
TD {
	FONT-SIZE: 11px; FONT-FAMILY: 'MS Sans Serif', Arial, Helvetica, 'sans-serif'
}
.DIVIDER {
	BACKGROUND: #707090
}
.TAB {
	BACKGROUND: #f0f0f0
}
.SELECTEDTAB {
	COLOR: black
}
 

 
 </style>
<script > 
window.history.forward()
<%
   TabGroup objTabGroup=(TabGroup)request.getAttribute(MrdConfig.TAB_GROUP);
%>

function <%=objTabGroup.getJsOnClickFuncName()%>(mode)
{
	document.getElementById("frmDeskMain").<%=objTabGroup.getHtmlModeFieldName()%>.value=mode;
	document.getElementById("frmDeskMain").submit();
}
</script>
<center>
<table width="110%" cellpadding="0" cellspacing="0">
					<tr>
						<td align="center" width="100%">
	<div class="tabGroup">
		<span class="tabGroupTop"> 
			<span>
			<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td width="100%" nowrap="nowrap"  align="center" width="100%">
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
</td></tr></table></center>
	
<%ComponentContext compContext = (ComponentContext)pageContext.getAttribute("org.apache.struts.taglib.tiles.CompContext", 2);
String action=(String)compContext.getAttribute("action");
System.out.println("Action is:" +action);
%>

  <jsp:include page="<%=action %>" flush="true"></jsp:include>
   
	


