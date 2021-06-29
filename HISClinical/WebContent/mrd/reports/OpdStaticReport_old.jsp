<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ page import ="java.util.*,registration.*,hisglobal.presentation.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="mrd.MrdConfig"%>

<!--
							Author: CDAC NOIDA
							Developer: Pawan kumar B N
							Created on : 09-Jul-2012
							JSP: Opd Static Report

-->
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/commonUtility.js" />
<his:javascript  src="/registration/js/reportsValidation.js"/>
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/masterXml.css" />
<script>

</script>




<html>
<head>




<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="/HISClinical/hisglobal/css/calendar-blue2.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

<script language="JavaScript" src="/HISClinical/dietkitchen/reports/js/deptWardDietReq.js"></script>

</head>

<body >


<html:form action="/departmentWardWiseDietReqReport">


	   <bean:define name="DepartmentWardDietReqReportFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="DepartmentWardDietReqReportFB" property="toDate" id="tDate" type="java.lang.String"/>          
<% 
String sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"); 

DepartmentWardDietReqReportFB objFB = (DepartmentWardDietReqReportFB) request.getAttribute("DepartmentWardDietReqReportFB");
%>
<his:TransactionContainer>	
<his:TitleTag name="Department Ward Wise Diet Request Report"> </his:TitleTag>
<his:statusNew>      
 <his:ContentTag>	
	<table width="100% cellspacing="1" cellpadding="0">
		<tr>            
 			<td class="tdfonthead"  width="25%">       	      
		    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      		<bean:message key="today"/>
		      	</font>
		      		<html:radio name="DepartmentWardDietReqReportFB" property="choice" tabindex="1" value="<%=Config.CHOICE_TODAY%>" onclick="showdivtoday()"/> 
		        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       		<bean:message key="dateWise"/></font> 
		        	<html:radio name="DepartmentWardDietReqReportFB" property="choice"  tabindex="1" value="<%=Config.CHOICE_DATE_WISE%>" onclick="showdivdatewise()"/>
			</td> 
 		</tr>
 		</table>
 		<div id="divDate" >
 		<table width="100% cellspacing="1" cellpadding="0">
		<tr>
 			<td class="tdfonthead" width="25%">
        		<div align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromDt"/>
					</font>
        		</div>
        	</td>
        
 			<td class="tdfont" width="25%">
 			<div align="left" >
				&nbsp;<bean:define id="fromDate" name="DepartmentWardDietReqReportFB" property="fromDate" type="java.lang.String"></bean:define>
				<his:date name="fromDate" value="<%=sysDate%>"></his:date>
			</div>
			</td>
				
			<td class="tdfonthead" width="25%">
    			<div align="right">
         			<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
         			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="toDt"/>
		 			</font>
    			</div>
    			
			</td>
		
			<td class="tdfont" width="25%">
			<div align="left" >
					&nbsp;<bean:define id="toDate" name="DepartmentWardDietReqReportFB" property="toDate" type="java.lang.String"></bean:define>
					<his:date name="toDate" value="<%=sysDate%>"></his:date>
			</div>
    	  	</td>                 			
		</tr></table></div>
		<div id="divTime" style="display: none;" >
		<table width="100% cellspacing="1" cellpadding="0">
		<tr>
 			<td class="tdfonthead" width="25%">
        		<div align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromTime"/>
					</font>
        		</div>
        	</td>
        
 			<td class="tdfont" width="25%">
 			<div align="left">
 			 	<span align="left">	            
		   			<html:text name="DepartmentWardDietReqReportFB" tabindex="1" property="fromHour"  maxlength="2" size="3"  onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)" />	
		  			 <b><bean:message key="colon"/></b>
			 	</span>
		 		<span align="left">         
					<html:text name="DepartmentWardDietReqReportFB" tabindex="1" property="fromMin"   maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>				
    			</span>
			</div>
			</td>
				
			<td class="tdfonthead" width="25%">
    			<div align="right">
         			<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
         			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="toTime"/>
		 			</font>
    			</div>
    			
			</td>
		
			<td class="tdfont" width="25%">
			<div align="left">
				<span align="left">	            
		   			<html:text name="DepartmentWardDietReqReportFB" tabindex="1" property="toHour"  maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>	
	   				<b><bean:message key="colon"/>	  </b>
				</span>
		 		<span align="left">         
					<html:text name="DepartmentWardDietReqReportFB" tabindex="1" property="toMin" maxlength="2" size="3"  onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>				
    	 		</span>
			</div>
    	  	</td> 
    	  </tr> 
    	  </table></div>
    	  <table width="100% cellspacing="1" cellpadding="0">
    	  <tr>
				<td class="tdfonthead" width="25%">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<font color="red" size="2">*</font><bean:message key="dept"/>
					</font>
					</div>
				</td>
				<td class="tdfont" width="25%">
					<div align="left">
						&nbsp;<html:select name="DepartmentWardDietReqReportFB" property="departmentCode" tabindex="1" onchange="onchangeDept(this)">
							<html:option value="0">All </html:option>
							<logic:present name="<%=DietKitchenConfig.LIST_OF_DEPARTMENT %>">
								<html:options collection="<%=DietKitchenConfig.LIST_OF_DEPARTMENT %>" labelProperty="department" property="departmentCode"/>
							</logic:present>
						</html:select>
					</div>
				</td>
				<td class="tdfonthead" width="25%">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<font color="red" size="2">*</font><bean:message key="ward"/>
					</font>	
					</div>
				</td>
				<td class="tdfont" width="25%">
					<div align="left">
						&nbsp;<html:select name="DepartmentWardDietReqReportFB" property="wardCode" tabindex="1">
							<html:option value="0">All </html:option>
							<logic:present name="<%=DietKitchenConfig.LIST_ALL_WARD_TYPES %>">
								<html:options collection="<%=DietKitchenConfig.LIST_ALL_WARD_TYPES %>" labelProperty="labelvalue" property="value"/>
							</logic:present>
						</html:select>
					</div>
				</td>
		</tr>               			
		<tr>
			<td class="tdfonthead" width="25%">
      			<div align="right" >
		        	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		        	<font color="red" size="2">*</font>
				  		<bean:message key="mealType"/>
				  </font>
      			</div>
    		</td>
    		<td class="tdfont" width="25%">
       			<div id="mealtypecontrol" align="left" >
     		 		<html:select name="DepartmentWardDietReqReportFB"  property="strMealTypeId"  tabindex="1"  styleClass ="regcbo" >   
					  <html:option value="0">All </html:option>
					<logic:present name="<%=DietKitchenConfig.DIET_ESSENTIAL_LIST_MEAL_TYPE%>">  
					  <html:options collection ="<%=DietKitchenConfig.DIET_ESSENTIAL_LIST_MEAL_TYPE%>" property = "strMealTypeId" labelProperty = "strMealType"/>
			  		</logic:present>
			  		</html:select> 
			  		<html:hidden name="DepartmentWardDietReqReportFB" property="strMealType" />
	   			</div>       
    		</td>	
    		<td class="tdfonthead"></td>
    		<td class="tdfont"></td>
    	</tr>
	</table>
</his:ContentTag>
<his:SubTitleTag name="Report Generation Option">
</his:SubTitleTag>
 <his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
			
    	<tr>       
        	<td width="25%" class="tdfonthead">
        	<div align="right" >
        		<font color="#FF0000">*</font>
	        	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          		<bean:message key="reportType"/>      
	        	</font>
	        	</div>
        	</td>                
        	<td width="25%" class="tdfont" > 
	        	<html:select name="DepartmentWardDietReqReportFB"  property="graphOrText" tabindex="1" styleClass="regcbo" >
		        	<html:option value="<%=Config.TEXT%>">Textual</html:option>
		    
				</html:select> 		
        	</td>
        	<td width="25%" class="tdfonthead">
        	<div align="right" >
        		<font color="#FF0000">*</font>
	        	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          		<bean:message key="pdfOrWord"/>      
	        	</font>
	        	</div>
        	</td>
        	<td width="25%" class="tdfont">
        		<html:select name="DepartmentWardDietReqReportFB" property="reportType" tabindex="1" styleClass="regcbo">
					<%--<html:option value="<%=Config.PDF %>">Acrobat Reader</html:option>--%>
					<html:option value="<%=Config.RTF %>">HTML</html:option>
        		</html:select>
        	
        	</td>
    	</tr>    	
	</table>        
</his:ContentTag>
</his:statusNew>

<his:ButtonToolBarTag>
	<his:statusNew>
	<img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitPage('PRINTREPORT');" tabindex="1" onclick = "submitPage('PRINTREPORT');" />         
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('NEW')" onkeypress="if(event.keyCode==13) submitPage('NEW');"/>
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')"/>
	</his:statusNew>
</his:ButtonToolBarTag>
<html:hidden name="DepartmentWardDietReqReportFB" property="hmode"/>
<html:hidden name="DepartmentWardDietReqReportFB" property="jrxmlName"/>
<html:hidden name="DepartmentWardDietReqReportFB" property="sysDate" value="<%=tDate%>"/>

</his:TransactionContainer>
</html:form>
</body>
</html>