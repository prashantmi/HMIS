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
<%@page import="hisglobal.vo.HospitalMstVO"%>
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
<his:javascript src="/dutyroster/js/areaWiseEmpRosterReport.js" />


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
    
  <html:form action="/reports/AreaWiseEmpRosterReport" > 
    
   

   
     
     
<logic:empty name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS %>">     

  <his:TransactionContainer>
  
<his:css src="/hisglobal/css/Color.css" />

       <his:TitleTag name="AreaWise Employee Roster Report">
       
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
		   <html:select property="year" name="AreaWiseEmpRosterReportFB" tabindex="1" styleClass="textbox" >
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
		  <html:select property="month" name="AreaWiseEmpRosterReportFB" tabindex="1" styleClass="textbox" >
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
								<bean:message key="rosterCategory"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">        
	    	   
		  <html:select property="rosterCategory" name="AreaWiseEmpRosterReportFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_DUTY_AREA')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_ROSTER_CATEGORY%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_ROSTER_CATEGORY%>" property="value"  labelProperty="label"/>
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
								<bean:message key="dutyArea"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">		          
	    	   
		  <html:select property="areaCode" name="AreaWiseEmpRosterReportFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_ROSTERS')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_AREAS_ON_THE_BASIS_OF_ROSTER_CATEGORY%>" >
			    		<html:options collection="<%=DutyRosterConfig.LIST_OF_AREAS_ON_THE_BASIS_OF_ROSTER_CATEGORY%>" property="value"  labelProperty="label"/>
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
								<bean:message key="rosterName"/>
					</font>
				  </div>
			    </td>
			    
<%
List rosterList=new ArrayList();
rosterList=(ArrayList)session.getAttribute(DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY_AND_AREA);

%>
		    
			     <td width="25%" class="tdfont">
			      <div align="left">        
	    	    <html:select property="rosterId" name="AreaWiseEmpRosterReportFB" tabindex="1" styleClass="textbox" onchange="">
			    <html:option value="-1">Select Value</html:option>
		<%if(rosterList!=null && rosterList.size() > 1){ %>
			    <html:option value="ALL">ALL</html:option>
				<%}%>		    
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY_AND_AREA%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY_AND_AREA%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>	
	    	   
	    	   
		  
				   </div>
			     </td>  
			     
			
			      <td width="25%" class="tdfonthead">
			   
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			     
			   
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
	    	   
	    	   <bean:define id="startDateId" name="AreaWiseEmpRosterReportFB" property="startDate"></bean:define>
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
	    	     <bean:define id="endDateId" name="AreaWiseEmpRosterReportFB" property="endDate" ></bean:define>
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

	<table  width="100%" cellspacing="1" cellpadding="0">
		<tr>
		<td width="100%"  style="font-size:10px; font:Arial;" align="right">
		
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
		<tr>
		<td class="" width="50%" colspan="2">
		<div align="center" width="100%">
							Duty Roster for
		<bean:write name="AreaWiseEmpRosterReportFB" property="areaName"/>
		</div> 
	  	</td>
		</tr>
		<tr>
		<td class="" width="50%" colspan="2">
		<div align="center" width="100%">
						For the Month of 
		<bean:write name="AreaWiseEmpRosterReportFB" property="monthChar"/>
		</div> 
	  	</td>
		</tr>
		</table><!--




<table width="90%" cellpadding="1" cellspacing="1" bordercolor="black" style="color: black;">

<br>
<br>

<tr>
<td width="100%" class="reportNameHeader">
<bean:write name="AreaWiseEmpRosterReportFB" property="headerMsg"/>
</td>

</tr>


<br>
<br>
<br>
<br>
</table>

--><br>
<br>
<logic:notEqual value="ALL" name="AreaWiseEmpRosterReportFB" property="rosterId">
<table width="90%" cellpadding="1" cellspacing="1" border="1" bordercolor="black" style="color: black;">






<tr>

<td width="1%"class="reportColumnHeader">
Sl.No.
</td>

<td  width="15%" class="reportColumnHeader">
Employees(Designation)
</td>
<td  width="15%" class="reportColumnHeader">
Contact No.
</td>


<logic:iterate id="headerShiftId" 	name="<%=DutyRosterConfig.HEADER_LIST_OF_SHIFTS_FOR_REPORT%>" 	type="java.lang.String">

<td class="reportColumnHeader"><%=headerShiftId%></td>

</logic:iterate>

</tr>





<logic:iterate id="empWiseEntry" name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS %>" type="java.util.Map.Entry" indexId="index">


<bean:define id="empWiseKey" name="empWiseEntry" property="key" type="java.lang.String"></bean:define>
<bean:define id="shiftMap" name="empWiseEntry" property="value" type="java.util.LinkedHashMap"></bean:define>
<%
String[] empDetails=null;
empDetails=empWiseKey.split("@");
%>
<tr bordercolor="red;">

<td width="1%"  class="reportData">

<%=index+1 %>


</td>

<td  width="5%"  class="reportData">
	
	
	<%=empDetails[1].split("#")[0]%>
	

</td>
<td  width="5%"  class="reportData">
	
	
	<%=empDetails[1].split("#")[1]%>
	

</td>
<logic:iterate id="shiftWiseEntry" name="shiftMap" type="java.util.Map.Entry">

<bean:define id="shiftWiseKey"  name="shiftWiseEntry" property="key" ></bean:define>
<bean:define id="daysList" name="shiftWiseEntry" property="value"  type="java.lang.String"></bean:define>


<td  class="reportData">


	<%=daysList%>



</td>


</logic:iterate>



</tr>


</logic:iterate>






</table>
</logic:notEqual>
	
	





<logic:equal value="ALL" name="AreaWiseEmpRosterReportFB" property="rosterId">


<table width="100%" cellpadding="1" cellspacing="1" border="1" bordercolor="black" style="color: black;">

<tr>
<td class="reportColumnHeader">S.No.</td>
<td class="reportColumnHeader">Employees(Designation)</td>


<logic:iterate id="shiftEntry" name="<%=DutyRosterConfig.HEADER_LIST_OF_SHIFTS_FOR_REPORT %>" type="java.util.Map.Entry" >


<bean:define id="shiftId" name="shiftEntry" property="key" type="java.lang.String"></bean:define>
<bean:define id="shiftList" name="shiftEntry" property="value" type="java.util.ArrayList"></bean:define>

<logic:iterate id="shiftId" name="shiftList" type="java.lang.String" >
<%
String[] shiftArray=shiftId.split("@");

%>


<td class="reportColumnHeader" width="20%"><%=shiftArray[1]%>(<%=shiftArray[2]%>)</td>




</logic:iterate>





</logic:iterate>
</tr>




<%int counter=0; %>

<logic:iterate id="empWiseEntry" name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS %>" type="java.util.Map.Entry" >
<tr>


<bean:define id="empId" name="empWiseEntry" property="key" type="java.lang.String"></bean:define>
<bean:define id="shiftMap" name="empWiseEntry" property="value" type="java.util.LinkedHashMap"></bean:define>

<%
String[] empArray=empId.split("@");
%>


<td class="reportData"><%=++counter%></td>

<td class="reportData"><%=empArray[1]%></td>

<logic:iterate id="shiftWiseEntry" name="shiftMap" type="java.util.Map.Entry" >

<bean:define id="shiftId" name="shiftWiseEntry" property="key" type="java.lang.String"></bean:define>
<bean:define id="dayId" name="shiftWiseEntry" property="value" type="java.lang.String"></bean:define>

<td class="reportData"><%=dayId%></td>



</logic:iterate>

</tr>
</logic:iterate>






</table>
</logic:equal>

</logic:notEmpty>



<br> 





<logic:equal name="AreaWiseEmpRosterReportFB" property="hmode" value="GET_REPORT">
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
    
    <html:hidden name="AreaWiseEmpRosterReportFB" property="hmode" />
      <html:hidden name="AreaWiseEmpRosterReportFB" property="headerMsg"/>
        		    
		
   </html:form>
  </body>
</html>
		     
		   
		  