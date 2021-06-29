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
<his:javascript src="/dutyroster/js/empWiseSelfEmpRosterReport.js" />


<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:css src="/css/calendar-blue2.css" />

 <body >

  <%
 String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
  HospitalMstVO HVO = (HospitalMstVO)session.getAttribute(DutyRosterConfig.HOSPITAL_MST_VO);
  %>
    
  <html:form action="/reports/EmpWiseSelfEmpRosterReport" > 
    
   
   
     
     
<logic:empty name="<%=DutyRosterConfig.TOTAL_MAP_OF_EMP_ROSTER %>">     

  <his:TransactionContainer>
  
<his:css src="/hisglobal/css/Color.css" />

       <his:TitleTag name="Employee Self Roster Report">
       
     		 
       </his:TitleTag>

   
		
	  
	
		
	  <his:ContentTag>
	  
	  <logic:empty name="<%=DutyRosterConfig.TOTAL_MAP_OF_EMP_ROSTER %>">  
	  
	  
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
	<div align="right" valign="top" style="background-color: #FFEBD5"><b>Printing Format:</b>&nbsp;Area Wise<html:radio property="printingFormat" value="A" name="EmpWiseSelfEmpRosterReportFB" />Date Wise<html:radio property="printingFormat" value="D" name="EmpWiseSelfEmpRosterReportFB"/></div>	
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
		   <html:select property="year" name="EmpWiseSelfEmpRosterReportFB" tabindex="1" styleClass="textbox" >
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
		  <html:select property="month" name="EmpWiseSelfEmpRosterReportFB" tabindex="1" styleClass="textbox" >
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
			        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="reportPrinting"/> <bean:message key="fromDate"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">        
	    	   
	    	   <bean:define id="startDateId" name="EmpWiseSelfEmpRosterReportFB" property="startDate"></bean:define>
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
	    	     <bean:define id="endDateId" name="EmpWiseSelfEmpRosterReportFB" property="endDate" ></bean:define>
				 <his:date name="endDate" dateFormate="%d-%b-%Y"  value="<%=(String)endDateId%>" />	   
							
				
				   </div>
			     </td>  
			    
			 
		 </tr>  
				      
		     
		     
		 
		 
		
		 
		
	</table>
		</logic:empty>
		
		
		
      </his:ContentTag>
   </his:TransactionContainer>   
      
</logic:empty>



        
<%

String printingDateAndTime="Report Printing Date and Time :"+systemDate;

String width="";
%>
        



      
<logic:notEmpty name="<%=DutyRosterConfig.TOTAL_MAP_OF_EMP_ROSTER %>">
<br>

<div id="noPrint" align="right"> 
 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-pnt.png"/>'  tabindex="1" style="cursor: pointer" onclick="printPage()" onkeypress="if(event.keyCode==13) printPage()">
<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="backButton()" onkeypress="if(event.keyCode==13) backButton()">
</div>

<br>
<br>

<table width="100%">
<tr>
<td width="100%" class="reportNameHeader">
<bean:write name="EmpWiseEmpRosterReportFB" property="headerMsg"/>
</td>

</tr>

<tr>
<td class="reportDate" width="100%" align="right">

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

<logic:equal value="A" name="EmpWiseSelfEmpRosterReportFB" property="printingFormat">

<table width="100%" cellpadding="1" cellspacing="1" border="1" bordercolor="black" style="color: black;">
<tr>
<td width="1%" class="reportColumnHeader">S.No.</td>
<td width="20%" class="reportColumnHeader">Area</td>
<td width="20%" class="reportColumnHeader">Roster Name</td>
<td width="20%" class="reportColumnHeader">Shift</td>
<td width="39%" class="reportColumnHeader">Days</td>

</tr>

<%int i=0; %>

<logic:iterate id="areaMapEntry" name="<%=DutyRosterConfig.TOTAL_MAP_OF_EMP_ROSTER %>" type="java.util.Map.Entry">




		<bean:define id="areaId" name="areaMapEntry" property="key" type="java.lang.String"></bean:define>
		<bean:define id="rosterMap" name="areaMapEntry" property="value" type="java.util.LinkedHashMap"></bean:define>


<logic:iterate id="rosterMapEntry" name="rosterMap" type="java.util.Map.Entry">

		<bean:define id="rosterId" name="rosterMapEntry" property="key" type="java.lang.String"></bean:define>
		<bean:define id="shiftMap" name="rosterMapEntry" property="value" type="java.util.LinkedHashMap"></bean:define>
		

		
<logic:iterate id="shiftMapEntry" name="shiftMap" type="java.util.Map.Entry">

		<bean:define id="shiftId" name="shiftMapEntry" property="key" type="java.lang.String"></bean:define>
		<bean:define id="daysId" name="shiftMapEntry" property="value" type="java.lang.String"></bean:define>

<tr>

<%
String[] areaArray=areaId.split("@");
String[] rosterArray=rosterId.split("@");
String[] shiftArray=shiftId.split("@");

%>

<td class="reportData"><%=++i%></td>

<td class="reportData"><%=areaArray[2]%></td>

<td class="reportData"><%=rosterArray[1]%></td>

<td class="reportData"><%=shiftArray[1]%></td>

<td class="reportData"><%=daysId%></td>

</tr>

</logic:iterate>		
		




</logic:iterate>



	
</logic:iterate>


</logic:equal>	



<logic:equal value="D" name="EmpWiseSelfEmpRosterReportFB" property="printingFormat">

<table width="100%" cellpadding="1" cellspacing="1" border="1" bordercolor="black" style="color: black;">
<tr>
<td width="1%" class="reportColumnHeader">Day</td>
<td width="15%" class="reportColumnHeader">Area</td>
<td width="20%" class="reportColumnHeader">Roster Name</td>
<td width="20%" class="reportColumnHeader">Shifts</td>


</tr>

<logic:iterate id="dayMapEntry" name="<%=DutyRosterConfig.TOTAL_MAP_OF_EMP_ROSTER %>" type="java.util.Map.Entry">

<bean:define id="dateId" name="dayMapEntry" property="key" type="java.lang.String"></bean:define>
<bean:define id="areaMap" name="dayMapEntry" property="value" type="java.util.LinkedHashMap"></bean:define>



<logic:iterate id="areaMapEntry" name="areaMap" type="java.util.Map.Entry">
<tr>

<td class="reportData"><%=dateId%></td>

<bean:define id="areaId" name="areaMapEntry" property="key" type="java.lang.String"></bean:define>
<bean:define id="rosterMap" name="areaMapEntry" property="value" type="java.util.LinkedHashMap"></bean:define>
<%
String[] areaArray=areaId.split("@");
%>

<td class="reportData"><%=areaArray[2]%></td>

<logic:iterate id="rosterMapEntry" name="rosterMap" type="java.util.Map.Entry">


<bean:define id="rosterId" name="rosterMapEntry" property="key" type="java.lang.String"></bean:define>
<bean:define id="shiftId" name="rosterMapEntry" property="value" type="java.lang.String"></bean:define>

<%
String[] rosterArray=rosterId.split("@");
%>

<td class="reportData"><%=rosterArray[1]%></td>
<td class="reportData"><%=shiftId%></td>





</logic:iterate>



</tr>

</logic:iterate>

</logic:iterate>

</table>
</logic:equal>



</logic:notEmpty>



<br> 


<logic:equal name="EmpWiseSelfEmpRosterReportFB" property="hmode" value="GET_REPORT">
<logic:empty name="<%=DutyRosterConfig.TOTAL_MAP_OF_EMP_ROSTER %>">
<div align="left" style="color: red; font-family: arial;font-size: 20px;font-weight: bold;">
No Roster Found
</div>

</logic:empty>
</logic:equal>







    
<logic:empty name="<%=DutyRosterConfig.TOTAL_MAP_OF_EMP_ROSTER %>">  

       <his:ButtonToolBarTag>
			<span id="saveDiv">
			
			 		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-view.png"/>' tabindex="1"  style="cursor: pointer" onclick="submitPage('GET_REPORT')" onkeypress="if(event.keyCode==13) submitPage('GET_REPORT')">
			 
	
			   	  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
			  
		
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
		
			</span>
		</his:ButtonToolBarTag>
</logic:empty>		
      
  
    <his:status/>
    
    <html:hidden name="EmpWiseSelfEmpRosterReportFB" property="hmode" />
      <html:hidden name="EmpWiseSelfEmpRosterReportFB" property="headerMsg"/>
      		    

   </html:form>
  </body>
</html>
		     
		   
		  