<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="dutyroster.DutyRosterConfig"%>
<%@page import="hisglobal.vo.HospitalMstVO" %>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>	
<style type="text/css">
@import url(../css/calendar-blue2.css);
</style>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/dutyroster/js/empWiseEmpRosterReport.js" />


<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:css src="/css/calendar-blue2.css" />

<body onload="focusOnLoad()">

  <%
 String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
  HospitalMstVO HVO = (HospitalMstVO)session.getAttribute(DutyRosterConfig.HOSPITAL_MST_VO);
  %>
    
  <html:form action="/reports/EmpWiseEmpRosterReport" > 
    
   

   
     
     
<logic:empty name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS %>">     

  <his:TransactionContainer>
  
<his:css src="/hisglobal/css/Color.css" />

       <his:TitleTag name="EmployeeWise Employee Roster Report">
       
      
       </his:TitleTag>

   
		
	  
	
		
	  <his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		
		  <tr>
		      <td width="25%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="year"/>&nbsp;
					</font>
				  </div>
			    </td> 
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">			       		
		   <html:select property="year" name="EmpWiseEmpRosterReportFB" tabindex="1" styleClass="textbox" >
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_YEARS_EMPLOYEE_DUTY_ROSTER_MASTER%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_YEARS_EMPLOYEE_DUTY_ROSTER_MASTER%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>
			    		   
				   </div>
			     </td>  
			     
			     
			     
			      <td width="25%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="month"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">	    	   
		  <html:select property="month" name="EmpWiseEmpRosterReportFB" tabindex="1" styleClass="textbox" >
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_MONTHS_EMPLOYEE_DUTY_ROSTER_MASTER%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_MONTHS_EMPLOYEE_DUTY_ROSTER_MASTER%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>		   
				
				   </div>
			     </td>  
			     
		</tr>
				
				 
				      
		      <tr>     
		      	     
		      <td width="25%" class="tdfonthead">
			     <div align="right">
			     <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="designation"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">        
	    	   
		  <html:select property="designationId" name="EmpWiseEmpRosterReportFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_EMP_LIST')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_ALL_DESIGNATIONS%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_ALL_DESIGNATIONS%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>		   
				
				   </div>
			     </td>  
			     
			     
			      <td width="25%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="employeename"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">		          
	    	   
		  <html:select property="empId" name="EmpWiseEmpRosterReportFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_DUTY_AREA')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_EMP_BASED_ON_DESIGNATION%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_EMP_BASED_ON_DESIGNATION%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>	
		   		
				   </div>
			     </td>  
			    
			     
		 </tr>
		     
		 
		 
		  <tr>     
		      	     
		      <td width="25%" class="tdfonthead">
			     <div align="right">
			     <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="dutyArea"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">        
	    	   
		   <html:select property="areaCode" name="EmpWiseEmpRosterReportFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_ROSTER_LIST')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_AREA_BASED_ON_EMPLOYEE%>" >
			    			<html:options collection="<%=DutyRosterConfig.LIST_OF_AREA_BASED_ON_EMPLOYEE%>" property="value"  labelProperty="label"/>
			        </logic:present>
		   </html:select>		   
				
				   </div>
			     </td>  
			     
			     
			      <td width="25%" class="tdfonthead">
			   <div align="right">
			   <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="rosterName"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			     
			     <div align="left">        
	    	   
		  <html:select property="rosterId" name="EmpWiseEmpRosterReportFB" tabindex="1" styleClass="textbox" >
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_ROSTERS_BASED_ON_AREA_EMPLOYEE%>" >
			    	          <html:options collection="<%=DutyRosterConfig.LIST_OF_ROSTERS_BASED_ON_AREA_EMPLOYEE%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>		   
				
				   </div>
			     </td>  
			    
			     
		 </tr>
		 
		<tr>     
		      	     
		      <td width="25%" class="tdfonthead">
			     <div align="right">
			   	   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="reportPrinting"/> <bean:message key="fromDate"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">        
	    	   
	    	   <bean:define id="startDateId" name="EmpWiseEmpRosterReportFB" property="startDate"></bean:define>
	    	   		<his:date name="startDate" dateFormate="%d-%b-%Y" value="<%=(String)startDateId%>" />	
	    	   			    	   				
				   </div>
			     </td>  
			     
			     
			      <td width="25%" class="tdfonthead">
			     <div align="right">
					
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="reportPrinting"/> <bean:message key="toDate"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">		          
	    	     <bean:define id="endDateId" name="EmpWiseEmpRosterReportFB" property="endDate" ></bean:define>
				 <his:date name="endDate" dateFormate="%d-%b-%Y"  value="<%=(String)endDateId%>" />	   
							
				
				   </div>
			     </td>  
			    
			 
		 </tr> 
	</table>
		
		
		
		
      </his:ContentTag>
   </his:TransactionContainer>   
      
</logic:empty>



        
<%

String printingDateAndTime="Report Printing Date and Time :"+systemDate;

String width="";
%>
        
<his:ContentTag>


      
<logic:notEmpty name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS %>">

<div id="noPrint" align="right"> 
 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-pnt.png"/>'  tabindex="1" style="cursor: pointer" onclick="printPage()" onkeypress="if(event.keyCode==13) printPage()">
<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="backButton()" onkeypress="if(event.keyCode==13) backButton()">
</div>

<br>
<br>

<table width="100%" cellpadding="1" cellspacing="1" bordercolor="black" style="color: black;">
<tr>
<td width="100%" class="reportNameHeader">
<bean:write name="EmpWiseEmpRosterReportFB" property="headerMsg"/>
</td>

</tr>


<br>
<br>

<tr>
<td class="reportDate">
<%=printingDateAndTime%>
</td>
</tr>
<tr >
		 <td class="" width="50%" colspan="2">
	 	 	<div align="center" width="100%">
		   <img style="width: auto; height: 100px; align:center" src='<his:path src="/hisglobal/images/NIMSLOGO.png"/>'>
			</div>			
		  </td>
		 </tr>
		<tr >
		 <td class="" width="50%" colspan="2">
	 	 	<div align="center" width="100%">
		    <%=HVO.getHospitalName() %>
			</div>			
		  </td>
		 </tr>
	
		 <tr>
		 <td class="" width="50%" colspan="2">
		 	<div align="center" width="100%">	
		 	  <%=HVO.getAddress1() %>
			</div> 
		  </td>
		 </tr> 
		 
		 
		 <tr>
		 <td class="" width="50%" colspan="2">
		 	<div align="center" width="100%">	
		 	  <%=HVO.getAddress2() %>,<%=HVO.getCity() %> - <%=HVO.getPinCode() %>,
							<%=HVO.getStateName() %>, INDIA	
			</div> 
		  </td>
		 </tr> 
</table>
<br>
<br>

<table width="90%" cellpadding="1" cellspacing="1" border="1" bordercolor="black" style="color: black;">
<br>


<logic:notEqual value="ALL" name="EmpWiseEmpRosterReportFB" property="areaCode">

<logic:notEmpty name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS %>">


<tr>
			<td width="1%" class="reportColumnHeader">S.No.</td>
			<td width="15%" class="reportColumnHeader">Employee</td>
	<logic:iterate id="shiftNameId" name="<%=DutyRosterConfig.HEADER_LIST_OF_SHIFTS_FOR_REPORT %>" type="java.lang.String">
			<td class="reportColumnHeader"><%=shiftNameId%></td>
	</logic:iterate>
</tr>


<logic:iterate id="empMapEntry" name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS %>" type="java.util.Map.Entry" indexId="idx">

<tr>
<td class="reportData"><%=++idx%></td>
<bean:define id="empId" name="empMapEntry" property="key" type="java.lang.String"></bean:define>
<bean:define id="shiftMap" name="empMapEntry" property="value" type="java.util.LinkedHashMap"></bean:define>
<%String[] empArray=empId.split("@"); %>

<td class="reportData"><%=empArray[1]%></td>

	<logic:iterate id="shiftMapEntry" name="shiftMap" type="java.util.Map.Entry" >

		<bean:define id="shiftId" name="shiftMapEntry" property="key" type="java.lang.String"></bean:define>
		<bean:define id="dayId" name="shiftMapEntry" property="value" type="java.lang.String"></bean:define>

<td class="reportData"><%=dayId%></td>


	</logic:iterate>


</tr>
</logic:iterate>




</logic:notEmpty>


</logic:notEqual>

	</table>





      



<logic:equal value="ALL" name="EmpWiseEmpRosterReportFB" property="areaCode">

<table width="100%" cellpadding="1" cellspacing="1" border="1" bordercolor="black" style="color: black;">

<tr>
<td width="1%" class="reportColumnHeader">S.No.</td>
<td width="10%" class="reportColumnHeader">Area Name</td>
<td width="15%" class="reportColumnHeader">Employee Name</td>
<logic:iterate id="shiftNameId" name="<%=DutyRosterConfig.HEADER_LIST_OF_SHIFTS_FOR_REPORT %>" type="java.lang.String">
<td class="reportColumnHeader"><%=shiftNameId%></td>
</logic:iterate>
</tr>


<%int counter=0; %>
<logic:iterate id="areaMap" name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS %>" type="java.util.Map.Entry" indexId="idx">

	<bean:define id="areaName" name="areaMap" property="key" type="java.lang.String"></bean:define>
	<bean:define id="empMap" name="areaMap" property="value" type="java.util.Map"></bean:define>


	<logic:iterate id="empMapEntry" name="empMap" type="java.util.Map.Entry" >
	<bean:define id="empName" name="empMapEntry" property="key" type="java.lang.String"></bean:define>
	<bean:define id="shiftMap" name="empMapEntry" property="value" type="java.util.Map"></bean:define>
	 
	 
	 <%String[] areaNameArray=areaName.split("@");%>
	 <%String[] empNameArray=empName.split("@");%>
	
	<tr>
		
		
		<td  class="reportData">
		<%=++counter %>
		</td>
	 <td align="center" class="reportData">
	
	 
	 	<%=areaNameArray[1]%>
	 </td>
		<td class="reportData">
		<%=empNameArray[1] %>
		</td>
		
		<logic:iterate id="shiftMapEntry" name="shiftMap" type="java.util.Map.Entry">
		<bean:define id="shiftId" name="shiftMapEntry" property="key"></bean:define>
		<bean:define id="days" name="shiftMapEntry" property="value" type="java.lang.String"></bean:define>
	
		
		<td  class="reportData">
		<%=days %>
		</td>
		
		
		
		
		</logic:iterate>
		</tr>
	</logic:iterate>


</logic:iterate>

</table>

	
</logic:equal>	
	

</logic:notEmpty>



<br> 

<logic:notEmpty name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS %>">
	<logic:notEmpty name="<%=DutyRosterConfig.MAP_OF_ROSTER_PRINT_DETAILS %>">
			
			
<logic:iterate id="rosterPrintMapId" name="<%=DutyRosterConfig.MAP_OF_ROSTER_PRINT_DETAILS %>" type="java.util.Map.Entry">
	
	<bean:define id="rosterPrintMapKey" name="rosterPrintMapId" property="key" type="java.lang.String"></bean:define>
	<bean:define id="rosterOrderMap" name="rosterPrintMapId" property="value" type="java.util.Map"></bean:define>

<%
String[] propertyTypeConcate=rosterPrintMapKey.split("@");
String alignMent="";

if(propertyTypeConcate[1].equals("1"))
   alignMent="left";
		else
	if(propertyTypeConcate[1].equals("2"))
		   alignMent="right";
			else
		if(propertyTypeConcate[1].equals("3"))
	    	alignMent="center";



String displayTitle="";

if(propertyTypeConcate[0].equals("1"))
	displayTitle="Instructions";
		else
	if(propertyTypeConcate[0].equals("2"))
		displayTitle="Roster By";
		else
	  	if(propertyTypeConcate[0].equals("3"))
				displayTitle="Copy To";


%>	

	


	
			
			<his:ContentTag>
	

	
	
	<div align="<%=alignMent%>"><b><%=displayTitle%></b></div>
	
<table width="100%" border="0" cellspacing="0" cellpadding="0" >

<logic:iterate id="rosterOrderMapId" name="rosterOrderMap" type="java.util.Map.Entry">

<bean:define id="rosterOrderMapKey" name="rosterOrderMapId" property="key" type="java.lang.String"></bean:define>
<bean:define id="displayValue" name="rosterOrderMapId" property="value" type="java.lang.String"></bean:define>


<tr>
<td  width="100%" >
	
	<div align="<%=alignMent%>" >
		
<%=displayValue %>

	</div>

</td>


</tr>

</logic:iterate>	
			</table>
	</his:ContentTag>

</logic:iterate>
	
	
 	



	</logic:notEmpty>     
</logic:notEmpty>	


<logic:equal name="EmpWiseEmpRosterReportFB" property="hmode" value="GET_REPORT">
<logic:empty name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS %>">
<div align="left" style="color: red; font-family: arial;font-size: 20px;font-weight: bold;">
No Roster Found
</div>

</logic:empty>
</logic:equal>



</his:ContentTag>
    
<logic:empty name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS %>">  
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			
			
				 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-view.png"/>' tabindex="1"  style="cursor: pointer" onclick="submitPage('GET_REPORT')" onkeypress="if(event.keyCode==13) submitPage('GET_REPORT')">
				 	
	
			   	  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
			  
		
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
		
			</span>
		</his:ButtonToolBarTag>
</logic:empty>		
      
  
    <his:status/>
    
    <html:hidden name="EmpWiseEmpRosterReportFB" property="hmode" />
      <html:hidden name="EmpWiseEmpRosterReportFB" property="headerMsg"/>
        		    
		
   </html:form>
  </body>
</html>
		     
		   
		  