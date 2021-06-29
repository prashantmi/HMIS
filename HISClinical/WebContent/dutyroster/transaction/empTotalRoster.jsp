<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="dutyroster.DutyRosterConfig;"%>
<his:javascript src="/dutyroster/js/empDutyRoster.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> 

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>	
<style type="text/css">
@import url(../css/calendar-blue2.css);
</style>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>


<his:css src="/css/calendar-blue2.css" />

 <body onload="checkDateOnLoad()">

  <%
 String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
String mappedAreas=(String)request.getAttribute(DutyRosterConfig.EMP_MAPPED_AREA);
String[] arrayOfAreas=mappedAreas.split(",");
  %>
    
  <html:form action="/transaction/EmpDutyRoster" > 
    
   
    
 
     

 <his:TitleTag name="The Employee is Mapped with the following Areas">
 <%if(arrayOfAreas!=null && arrayOfAreas.length >0){ %>
       
       <div align="left">
       <table width="100%" cellspacing="1" cellpadding="0">
       	<%for(int i=0; i< arrayOfAreas.length ;i++){ %>
       			<tr><td><%=i+1%>&nbsp;&nbsp;<%=arrayOfAreas[i]%></td></tr>
       	<%}%>
       	
       </table>
       </div>
<%}%>      
       
        </his:TitleTag>

   
		
	  
	
		

	  
<logic:notEmpty name="<%=DutyRosterConfig.MAP_OF_EMPWISE_MONTHLY_ROSTERS %>">
		
		
<table width="100%" border="0" cellspacing="1" cellpadding="0">
<tr>
<td class="tdfonthead" width="10%"><div align="left">Roster Category</div></td>
<td class="tdfonthead" width="32%"><div align="left">Roster Name / Area Name</div></td>
<td class="tdfonthead" width="48%"><div align="left">Days</div></td>		
</tr>
		
<logic:iterate id="catgMap" name="<%=DutyRosterConfig.MAP_OF_EMPWISE_MONTHLY_ROSTERS%>" type="java.util.Map.Entry">		

<bean:define id="catgKey" name="catgMap" property="key" type="java.lang.String"></bean:define>		
<bean:define id="areaMapId" name="catgMap" property="value" type="java.util.Map"></bean:define>		
		<%
		String [] catgArray=catgKey.split("@");
		%>
		<tr>
		<td class="tdfont">
			
		<%=catgArray[1]%>
			
		</td>
		
		<td colspan="2" >
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
		<logic:iterate id="areaMap" name="areaMapId" type="java.util.Map.Entry">
		
<bean:define id="areaKey" name="areaMap" property="key" type="java.lang.String"></bean:define>		
<bean:define id="dayslist" name="areaMap" property="value" type="java.lang.String"></bean:define>
<%
String [] areaArray=areaKey.split("@");

%>			
					 <tr>
						<td class="tdfont" width="40%">
						 <%=areaArray[1] %>		
						</td>
						
						
						<td class="tdfont" valign="top" width="60%">
						<div style="font-size: 1px;">
						<%=dayslist%>
						</div>
						</td>
			
			
		  		     </tr>
		</logic:iterate>	
			
			
				</table>	
		</td>
		
		
		
		</tr>
		</logic:iterate>
		
		 
		
		</table>
</logic:notEmpty>
		
		
		
<logic:notEmpty name="<%=DutyRosterConfig.EMP_RELIVER_LIST %>">
		
<bean:define id="reliverRange" name="<%=DutyRosterConfig.EMP_RELIVER_LIST %>"></bean:define>		
		
<table width="100%" border="0" cellspacing="1" cellpadding="0">
<tr>
<td class="tdfonthead" width="11%"><div align="left">Reliver Days</div></td>
<td class="tdfont"><%=reliverRange%></td>

</tr>

</table>
</logic:notEmpty>

     
      
 
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="window.close()" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			</span>
		</his:ButtonToolBarTag>


    <his:status/>
    
    <html:hidden name="EmpDutyRosterFB"   property="hmode" value="GET_TOTAL_EMPLOYEE_ROSTER"/>
	<html:hidden name="EmpDutyRosterFB"   property="empId"/>
   
		
   </html:form>
  </body>
</html>
		     
		   
		  