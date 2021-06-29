<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import ="java.util.*,registration.*,hisglobal.presentation.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="mrd.MrdConfig"%>
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


function ASRDORWiseHospitalRegistrationReportHandler()
{
	if(document.getElementsByName('religionCode')[0].value=='%')
	{
		if(document.getElementsByName('ageRange')[0].value=='%')
			document.getElementsByName('jrxmlName')[0].value="<%=MrdConfig.MLC_All_AGE_SEX_ALLRELIGION_HOS_REGISTRATION_REPORT%>";
		else
			document.getElementsByName('jrxmlName')[0].value="<%=MrdConfig.MLC_AGE_SEX_ALLRELIGION_HOS_REGISTRATION_REPORT%>";
	}
	else 
	{
		if(document.getElementsByName('ageRange')[0].value=='%')	
		document.getElementsByName('jrxmlName')[0].value="<%=MrdConfig.MLC_ALL_AGE_SEX_RELIGION_HOS_REGISTRATION_REPORT%>";
		else
			document.getElementsByName('jrxmlName')[0].value="<%=MrdConfig.MLC_AGE_SEX_RELIGION_HOS_REGISTRATION_REPORT%>";
	}
	
	success=false;
	if(!validateTodayOrDate())  return false;
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
}

 function validateTodayOrDate(){
  //   alert("inside validateTodayOrDate");
     success=false;        	   
           
               valFromDate=document.getElementsByName('fromDate')[0];
	           valToDate=document.getElementsByName('toDate')[0];
	           valSysDate=document.getElementsByName('sysDate')[0];
      
               if(compareDateCall(valFromDate,valToDate,2,"From Date","To Date") && compareDateCall(valToDate,valSysDate,2,"To Date","System Date"))
               {
                success=true;
               }             
           return success;        
    } 

</script>

<his:TitleTag name=" Age Sex Religion DOR Wise Hospital Registration Report">
</his:TitleTag>

         <%
     		
              String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
             
         %>

        <%     
        	   fromDateLabel="" ;
               toDateLabel="" ;
               fromDateControl="" ;
               toDateControl="" ;
                 
          %>

      <bean:define name="ASRDORWiseHospitalRegistrationReportFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="ASRDORWiseHospitalRegistrationReportFB" property="toDate" id="tDate" type="java.lang.String"/>          
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
 	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>            
 			<td class="tdfonthead" width="25%">
        		<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromDate"/>
					</font>
        		</div>
        		
			</td>
			<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left">	               		 
					<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
				</div>
		 		
			</td>
 		            
 			<td class="tdfonthead" width="25%">
        		<div id='divfromDate' style='<%=toDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="toDate"/>
					</font>
        		</div>
			</td>
			<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' style='<%=toDateControl %>'align="left">	               		 
					<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
				</div>
		 		
			</td>
			
 		</tr>
 	
</table> 	
<table width="100%" cellspacing="1" cellpadding="0">
<tr>	
	
 		<td class="tdfonthead" nowrap width="25%" >  
			 <div align="right">	
            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		  		<bean:message key="ageRange"/>
				  </font>
 			</div>
 			</td>
      	<td class="tdfont"  width="25%" >
     
     		 		<html:select name="ASRDORWiseHospitalRegistrationReportFB"  property="ageRange"  tabindex="1"  styleClass="regcbo" >   
					  <html:option value="%">All</html:option>
					  	<logic:present name="<%=MrdConfig.AGE_RANGE_COMBO%>">
					  
					  <html:options collection ="<%=MrdConfig.AGE_RANGE_COMBO%>" property = "value" labelProperty = "label"/>
					  </logic:present>
			  		</html:select> 
		
		 
		</td>
			<td class="tdfonthead" nowrap width="25%" >
 			   <div align="right">	
 			   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="Apt_patSex"/>
				  </font>
    			</div>
    			</td>
      			<td class="tdfont"  width="25%">
    
    			<html:select name="ASRDORWiseHospitalRegistrationReportFB"  property="genderCode"  tabindex="1"  styleClass ="regcbo" >
    			<html:option value="%">All</html:option>
				<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER%>">
				  <html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER%>" property = "value" labelProperty = "label"/>
				</logic:present>  
				</html:select>
			</td>
      	
      </tr>
      <tr>
      
      		<td class="tdfonthead" nowrap width="25%" >
 			       <div align="right">	
 			      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  		<bean:message key="religion"/>
				  </font>
    				</div>
    			</td>
      			<td class="tdfont"  width="25%">
    
    			<html:select name="ASRDORWiseHospitalRegistrationReportFB"  property="religionCode"  tabindex="1"  styleClass ="regcbo" >
    			<html:option value="%">All</html:option>
				<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELIGION%>">
				  <html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELIGION%>" property = "value" labelProperty = "label"/>
				</logic:present>  
				</html:select>
			</td>
	   		<td class="tdfonthead" nowrap width="25%" ></td>
	   			<td class="tdfont"  width="25%"></td>
      
      </tr>
	</table> 


 <his:SubTitleTag name="Report Generation Options">
</his:SubTitleTag> 
		<table width="100%" cellspacing="1" cellpadding="0">
    <tr>   
		<td width="25%" class="tdfonthead">
        	 <div align="right">	
        	<font color="#FF0000">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          	<bean:message key="reportType"/>      
	        </font>
	        </div>
        </td>                
        <td width="25%" class="tdfont" > 
	        <html:select name="ASRDORWiseHospitalRegistrationReportFB"  property="graphOrText" tabindex="1" styleClass="regcbo" >
		       	<html:option value="-1">Select Value</html:option>
		       	<html:option value="<%=Config.TEXT%>">Textual</html:option>
			</html:select> 		
        </td>
        <td width="25%" class="tdfonthead">
        	 <div align="right">	
        	<font color="#FF0000">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	        	<bean:message key="pdfOrWord"/>      
	        </font>
	        </div>
        </td>
        <td width="25%" class="tdfont">
        		<html:select name="ASRDORWiseHospitalRegistrationReportFB" property="reportType" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>        	
					<html:option value="<%=Config.PDF %>">Acrobat Reader</html:option>
					<html:option value="<%=Config.RTF %>">HTML</html:option>
        		</html:select>
        </td>
    	</tr>    	
	</table>               
</his:ContentTag>

<input type="hidden"  name="reportMode">
<input type="hidden"  name="hmode">

<input type="hidden"  name="mode" value="AGESEXRELIGIONHOSPREGREPORT">
<html:hidden name="ASRDORWiseHospitalRegistrationReportFB" property="jrxmlPath" value="<%=MrdConfig.MRD_JRXML_PATH%>"/>
<html:hidden name="ASRDORWiseHospitalRegistrationReportFB" property="jrxmlName"/>
<html:hidden name="ASRDORWiseHospitalRegistrationReportFB" property="sysDate" value="<%=tDate%>"/>



