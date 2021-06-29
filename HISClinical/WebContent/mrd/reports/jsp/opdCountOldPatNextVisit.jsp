<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import ="java.util.*,registration.*,opd.*,hisglobal.presentation.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 
<his:javascript src="/registration/js/dateFunctions.js"/> 
<his:javascript  src="/registration/js/reportsValidation.js"/>
<his:javascript src="/opd/opdJs/opd.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>

function submitPage(mode)
{
	elmt=document.getElementsByName("reportMode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function showUnit()
{
	val=document.getElementsByName("departmentCode")[0].value;
	if(val!=-1)
	{
		
		submitPage('UNIT');
	}
	else
	{	
		
		submitPage('NEW');
	}
}

function opdCountOldPatNextVisitReportHandler()
{
	
	success=false;
	if(document.getElementsByName('departmentCode')[0].value==-1)
	{
		alert("Select the Department");
		document.getElementsByName('departmentCode')[0].focus();
		return false;
	}
	else
	{
		document.getElementsByName('jrxmlName')[0].value="<%=OpdConfig.OPD_COUNT_OLD_PAT_NEXT_VISIT%>"
	}
	if(validateGraphOrText())
    { 
		if(document.getElementsByName('graphOrText')[0].selectedIndex==1)
		{
	    	document.getElementsByName('reportMode')[0].value="<%=Config.TEXT%>";
	        if(validateTextual())
	        {      
	        	
	           		return true;           	   
	           	          	  
            }
        }//end of selection is for text based report	
        if(document.getElementsByName('graphOrText')[0].selectedIndex==2)
		{
	    	document.getElementsByName('reportMode')[0].value="<%=Config.GRAPH%>";
	        if(validateGraphForAgeWiseReport())
	        {
		    	if(validateTodayOrDate())        
		        success=true;	                
	        }
        }//end of selection is for graph based report	 
    }
	return success;
}//end of function opdCountOldPatNextVisitReportHandler()

</script>

<his:TitleTag name="Count of Old Patient on the Basis of Next Visit Date Report">
</his:TitleTag>

         <%
			  String deptlabel="" ;
        	  String deptcontrol="" ;
              String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
              String fromLabel="" ;
              String toLabel="" ;
              String fromControl="" ;
              String toControl="" ; 
         %>

<!--<logic:equal name="opdCountOldPatNextVisitFB" property="choice" value="<%=Config.CHOICE_TODAY%>">
             <%
               fromDateLabel="display:none" ;
               toDateLabel="display:none" ;
               fromDateControl="display:none" ;
               toDateControl="display:none" ;
               fromLabel="" ;
               toLabel="" ;
               fromControl="" ;
               toControl="" ;  
            %>
 </logic:equal>              
<logic:equal name="opdCountOldPatNextVisitFB" property="choice" value="<%=Config.CHOICE_DATE_WISE%>">
        <%     
        	   fromDateLabel="" ;
               toDateLabel="" ;
               fromDateControl="" ;
               toDateControl="" ;
               fromLabel="display:none" ;
               toLabel="display:none" ;
               fromControl="display:none" ;
               toControl="display:none" ;  
          %>
      </logic:equal> 
      -->
       <bean:define name="opdCountOldPatNextVisitFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="opdCountOldPatNextVisitFB" property="toDate" id="tDate" type="java.lang.String"/>          
	   <%
         if(frDate==null||frDate.equalsIgnoreCase(""))
         {  
         	System.out.println("dt::::::::::::::::");        	
        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
        	System.out.println("dt:::::::::"+dt);        	
        	frDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
         }
  
		  if(tDate==null||tDate.equalsIgnoreCase(""))
		  {  
		   	System.out.println("dt::::::::::::::::");        	
		  	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
		  	System.out.println("dt:::::::::"+dt);        	
		  	tDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		   }
    	%>  
    	

<his:SubTitleTag name="Report Details">
</his:SubTitleTag>   

<his:ContentTag>
	<table width="100% cellspacing="1" cellpadding="0">
		<!--<tr>            
 			<td class="tdfonthead" nowrap width="25%">       	      
		    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      		<bean:message key="today"/>
		      	</font>
		      		<html:radio name="opdCountOldPatNextVisitFB" property="choice" tabindex="1" value="<%=Config.CHOICE_TODAY%>" onclick="showdivtoday()"/>
		        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       		<bean:message key="dateWise"/>
		       	</font> 
		        	<html:radio name="opdCountOldPatNextVisitFB" property="choice" tabindex="1" value="<%=Config.CHOICE_DATE_WISE%>" onclick="showdivdatewise()"/>
			</td> 
 		</tr>
 		<tr>
 			<td class="tdfonthead" width="25%">
        		<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromDate"/>
					</font>
        		</div>
        		<div id='divfrom' style='<%=fromLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="from"/>
					</font>
         		</div>
			</td>
			<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left">	               		 
					<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
				</div>
		 		<span id='divfromhrcontrol' style='<%=fromControl %>'align="left">	            
		   			<html:text name="opdCountOldPatNextVisitFB" tabindex="1" property="fromHour"  maxlength="2" size="3"  onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)" />	
		  			 <b>	<bean:message key="colon"/></b>
				</span>
		 		<span id='divfromMinControl' style='<%=fromControl %>' align="left">         
					<html:text name="opdCountOldPatNextVisitFB" tabindex="1" property="fromMin"   maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>				
						<bean:message key="timeFormat"/>
    			</span>
		
			</td>
				
			<td class="tdfonthead" width="25%">
    			<div id='divtoDate' style='<%=toDateLabel %>' align="right">
         			<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
         			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="toDate"/>
		 			</font>
    			</div>
    			<div id='divto' style='<%=toLabel %>' align="right">
        			<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
        			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="to"/>
					</font>
         		</div>
			</td>
		
			<td class="tdfont" width="25%">
		 		<div id='divtoDateControl' style='<%=toDateControl %>' align="left">    
					<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>" />
    	 		</div>
    	  		<span id='divtohrcontrol' style='<%=toControl%>' align="left">	            
		   			<html:text name="opdCountOldPatNextVisitFB" tabindex="1" property="toHour"  maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>	
	   				<b><bean:message key="colon"/>	  </b>
				</span>
		 		<span id='divtoMinControl' style='<%=toControl%>' align="left">         
					<html:text name="opdCountOldPatNextVisitFB" tabindex="1" property="toMin" maxlength="2" size="3"  onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>				
						<bean:message key="timeFormat"/>	  
    	 		</span>
			</td>                 			
 		</tr>
 		--><tr>
 			<td class="tdfonthead" width="25%">
      			<div id ="deptLabel"  align="right"  style='<%=deptlabel%>'>
		        	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="department"/>
				  </font>
      			</div>
    		</td>
    		<td class="tdfont" width="25%">
       			<div id="deptControl" align="left"  style='<%=deptcontrol%>'>
     		 		<html:select name="opdCountOldPatNextVisitFB"  property="departmentCode"  tabindex="1"  styleClass ="regcbo" onchange="showUnit()">   
					  <html:option value="-1">Select Value</html:option>
					  <logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" >
					  <html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" property = "value" labelProperty = "label"/>
					  </logic:present>
			  		</html:select> 
	   			</div>       
    		</td>
 	
<his:statusTransactionInProcess>
    		<td class="tdfonthead" width="25%">
    			<div id="unitLabel" align="right" >
    				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="unit"/>
				  </font>
    			</div>
    		</td>
    		<td class="tdfont" width="25%">
    			<div id="unitControl" align="left" >
    				<html:select name="opdCountOldPatNextVisitFB"  property="unit"  tabindex="1"  styleClass ="regcbo" >
						<html:option value="%">All Unit</html:option>
						<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>" >
						  <html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>" property = "value" labelProperty = "label"/>
						 </logic:present>
					</html:select>
				</div>			  
    		</td>
</his:statusTransactionInProcess>	
 		</tr>
 		<tr>
 			<td class="tdfonthead" width="25%">
 				<div align="right">
 					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
 						<bean:message key="primCat"/>
 					</font>
 				</div>
 			</td>
 			<td class="tdfont" width="25%">
 				<div align="left">
 					<html:select name="opdCountOldPatNextVisitFB" property="patPrimaryCatCode" tabindex="1"  styleClass ="regcbo">
 						<html:option value="%">All Category</html:option>
 						<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_PATIENT_CATEGORY%>" >
 						<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_PATIENT_CATEGORY %>" property = "value" labelProperty = "label"/>
 						</logic:present>
 					</html:select>
 				</div>
 			</td>
 			<td class="tdfonthead" width="25%">
 				<div align="right">
 					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
 					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
 						<bean:message key="nextVisitDate"/>
 					</font>
 				</div>
 			</td>
 			<td class="tdfont" width="25%">
 				<div align="left">
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
        	<td width="25%" class="tdfonthead">
        		<font color="#FF0000">*</font>
	        	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          		<bean:message key="reportType"/>      
	        	</font>
        	</td>                
        	<td width="25%" class="tdfont" > 
	        	<html:select name="opdCountOldPatNextVisitFB"  property="graphOrText" tabindex="1" styleClass="regcbo" >
		        	<html:option value="-1">Select Value</html:option>
		        	<html:option value="<%=Config.TEXT%>">Textual</html:option>
				</html:select> 		
        	</td>
        	<td width="25%" class="tdfonthead">
        		<font color="#FF0000">*</font>
	        	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          		<bean:message key="pdfOrWord"/>      
	        	</font>
        	</td>
        	<td width="25%" class="tdfont">
        		<html:select name="opdCountOldPatNextVisitFB" property="reportType" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>        	
					<html:option value="<%=Config.PDF %>">Acrobat Reader</html:option>
					<html:option value="<%=Config.RTF %>">HTML</html:option>
        		</html:select>
        	</td>
    	</tr>    	
	</table>        
</his:ContentTag>
<his:status/>
<html:hidden name="opdCountOldPatNextVisitFB" property="reportMode"/>
<html:hidden name="opdCountOldPatNextVisitFB" property="mode" value="OPDCOUNTOLDPATNEXTVISITREPORT"/>
<html:hidden name="opdCountOldPatNextVisitFB" property="jrxmlPath" value="<%=OpdConfig.OPD_JRXML_PATH%>"/>
<html:hidden name="opdCountOldPatNextVisitFB" property="jrxmlName"/>
<html:hidden name="opdCountOldPatNextVisitFB" property="sysDate" value="<%=tDate%>"/>
