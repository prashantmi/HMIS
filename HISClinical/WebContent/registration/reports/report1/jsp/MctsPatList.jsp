<%try{ %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import ="java.util.*,registration.*,hisglobal.presentation.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<!-- 
/**
 * @author Deepika Gaba
 * Date of Creation : 14-Feb-2011
 * Date of Modification :  15-Feb-2011 
 * Version : 
 * Module  : Registration
 */
 -->


<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 
<his:javascript src="/registration/js/dateFunctions.js"/> 
<script>

function submitPage(mode){
	elmt=document.getElementsByName("reportMode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function mctsPatListHandler()
{

	document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.MCTS_PATIENT_LIST%>"; 
	success=true;
    document.getElementsByName("reportMode")[0].value='TEXT'
    
    
               
               valFromDate=document.getElementsByName('fromDate')[0];
	           valToDate=document.getElementsByName('toDate')[0];
	           valSysDate=document.getElementsByName('sysDate')[0];
      
               if(compareDateCall(valFromDate,valToDate,2,"From Date","To Date") && compareDateCall(valToDate,valSysDate,2,"To Date","System Date"))
               {
                success=true;
               }   
               else success=false          
            
	 return success;
} 
		
</script>

<his:TitleTag name="MCTS Patient Listing" >
</his:TitleTag>

 
      
       <bean:define name="MctsPatListFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="MctsPatListFB" property="toDate" id="tDate" type="java.lang.String"/>
	           
	   <%
         if(frDate==null||frDate.equalsIgnoreCase("")){  
                 	
        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
                	
        	frDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
         }
  
		  if(tDate==null||tDate.equalsIgnoreCase("")){  
		        	
		  	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
		        	
		  	tDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		   }
    
		%>  


<his:SubTitleTag name="Report Details">
</his:SubTitleTag>

<his:ContentTag>	
	<table width="100% cellspacing="1" cellpadding="0">

 		<tr>
 			<td class="tdfonthead" width="25%">
        		<div id='divfromDate'  align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromDate"/>
					</font>
        		</div>
        		
			</td>
        
 			<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' align="left">	               		 
					<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
				</div>
		 		
		
			</td>
				
			<td class="tdfonthead" width="25%">
    			<div id='divtoDate'  align="right">
         			<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
         			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="toDate"/>
		 			</font>
    			</div>
    			
			</td>
		
			<td class="tdfont" width="25%">
		 		<div id='divtoDateControl'  align="left">    
					<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>" />
    	 		</div>
    	  		
			</td>                 			
		</tr>
		 
	
	</table>
</his:ContentTag>
<his:SubTitleTag name="Report Generation Option">
</his:SubTitleTag>

<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
    	<tr>       
        	<td width="25%" class="tdfonthead" align="right">
        	<div align="right">
        		<font color="#FF0000">*</font>
	        	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          		<bean:message key="reportType"/>      
	        	</font>
	        </div>
        	</td>                
        	<td width="25%" class="tdfont" align="left"> 
	        	<html:select name="MctsPatListFB"  property="graphOrText" tabindex="1" styleClass="regcbo" >
		        	<html:option value="-1">Select Value</html:option>
		        	<html:option value="<%=Config.TEXT%>">Textual</html:option>
		    
				</html:select> 		
        	</td>
        	<td width="25%" class="tdfonthead" align="right">
        	<div align="right">
        		<font color="#FF0000">*</font>
	        	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          		<bean:message key="pdfOrWord"/>      
	        	</font>
	        </div>
        	</td>
        	<td width="25%" class="tdfont" align="left">
        		<html:select name="MctsPatListFB" property="reportType" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>        	
					<html:option value="<%=Config.PDF %>">Acrobat Reader</html:option>
					<html:option value="<%=Config.RTF %>">HTML</html:option>
        		</html:select>
        	
        	</td>
    </tr>    	
	</table>        
</his:ContentTag>

<input type="hidden"  name="reportMode">
<input type="hidden"  name="mode" value="MCTSPATLIST">
<html:hidden name="MctsPatListFB" property="jrxmlPath" value="<%=RegistrationConfig.REGISTRATION_JRXML_PATH_REPORT1 %>"/>
<html:hidden name="MctsPatListFB" property="jrxmlName"/>
<html:hidden name="MctsPatListFB" property="sysDate" value="<%=tDate%>"/>

<%}catch(Exception e)
{
e.printStackTrace();	
}%>