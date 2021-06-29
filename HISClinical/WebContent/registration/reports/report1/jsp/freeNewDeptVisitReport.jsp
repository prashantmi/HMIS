<%
try{
%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import ="java.util.*,registration.*,hisglobal.presentation.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 
<his:javascript src="/registration/js/dateFunctions.js"/> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	<script>
	
	      
function submitPage(mode){
	elmt=document.getElementsByName("reportMode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function freeNewDeptVisitReportHandler()
{

	
	//if(document.getElementsByName('choice')[0].checked)
	//{
    	document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.FREE_NEW_DEPT_VISIT_REPORT%>";
	//}
	//else
	//{
	//	document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.DEPARTMENT_WISE_OUT_PATIENT_DATE_WISE_REPORT%>";         
	//}
	
	
	var success=false;
	
    if(validateGraphOrText())
    { 
		if(document.getElementsByName('graphOrText')[0].selectedIndex==1)
		{
	    	document.getElementsByName('reportMode')[0].value="<%=Config.TEXT%>";
	        if(validateTextual())
	        {      
	        	if(validateTodayOrDate())
	        	{
	           	  success=true;	         	   
	           	}          	  
            }
        }//end of selection is for text based report	   		
		if(document.getElementsByName('graphOrText')[0].selectedIndex==2)
		{
	    	document.getElementsByName('reportMode')[0].value="<%=Config.GRAPH%>";
	       // if(validateGraphForAgeWiseReport())
	        //{
		    	//if(validateTodayOrDate())        
		        success=true;	                
	       // }
        }//end of selection is for graph based report	  
    }     
      
       
        
if(success==true )
 {
	 if(document.getElementsByName('departmentCode')[0].value==-1)
	  		{
			alert("Please select the Department");
			document.getElementsByName('departmentCode')[0].focus();
			   success=false;
			}
	
}
	/*
	else
if(success==true &&  document.getElementsByName('option')[1].checked)
{
	
 if(document.getElementsByName('unit')[0].value==-1)
	{
		alert("Please select the Unit");
		document.getElementsByName('unit')[0].focus();
		   success=false;
	}
	if(document.getElementsByName('speciality')[0].value==-1)
	{
		alert("Please select the Speciality Type");
		document.getElementsByName('speciality')[0].focus();
		   success=false;
	}
	
}*/
	 
	  return success;
}//end of function freeNewVisitReportFB() 


</script>

<body onload="showDeptWise()">

<his:TitleTag name="List Of Transfer Patient " >
</his:TitleTag>

         <%
              
              String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
              String fromLabel="" ;
              String toLabel="" ;
              String fromControl="" ;
              String toControl="" ; 
         %>
          <logic:equal name="freeNewVisitReportFB" property="choice" value="<%=Config.CHOICE_TODAY%>">
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
      
      <logic:equal name="freeNewVisitReportFB" property="choice" value="<%=Config.CHOICE_DATE_WISE%>">
        <%     fromDateLabel="" ;
               toDateLabel="" ;
               fromDateControl="" ;
               toDateControl="" ;
               fromLabel="display:none" ;
               toLabel="display:none" ;
               fromControl="display:none" ;
               toControl="display:none" ;  
              %>
      </logic:equal> 
      
       <bean:define name="freeNewVisitReportFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="freeNewVisitReportFB" property="toDate" id="tDate" type="java.lang.String"/>          
	   <%
         if(frDate==null||frDate.equalsIgnoreCase("")){  
         	System.out.println("dt::::::::::::::::");        	
        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
        	System.out.println("dt:::::::::"+dt);        	
        	frDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
         }
  
		  if(tDate==null||tDate.equalsIgnoreCase("")){  
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
	
	<table width="100%" cellspacing="1" cellpadding="0">
	<tr>	
	
 		<td class="tdfonthead" nowrap width="25%" >  
 		<div align="right">
      			      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="department"/>
				  </font>
      			</div>
 			</td>
      	<td class="tdfont"  width="25%" >
      	<div >
     		 		<html:select name="freeNewVisitReportFB"  property="departmentCode"  tabindex="1"  styleClass="regcbo" >   
					  <logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT%>">
					  <html:option value="-1">Select Value</html:option>
					  <html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT%>" property = "value" labelProperty = "label"/>
					  </logic:present>
			  		</html:select> 
		</div>
		</td>
			<td class="tdfonthead" nowrap width="25%" >
 			</td>
      		<td class="tdfont"  width="25%">
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
        		<div align="right">
        		<font color="#FF0000">*</font>
	        	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          		<bean:message key="reportType"/>      
	        	</font>
	        	</div>
        	</td>                
        	<td width="25%" class="tdfont" > 
	        	<html:select name="freeNewVisitReportFB"  property="graphOrText" tabindex="1" styleClass="regcbo" >
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
        		<html:select name="freeNewVisitReportFB" property="reportType" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>        	
					<html:option value="<%=Config.PDF %>">Acrobat Reader</html:option>
					<html:option value="<%=Config.RTF %>">HTML</html:option>
        		</html:select>
        	
        	</td>
    </tr>    	
	</table>        
</his:ContentTag>

<input type="hidden"  name="reportMode">
<input type="hidden"  name="choice" >
<input type="hidden"  name="choice" checked="checked">
<input type="hidden"  name="fromHour">
<input type="hidden"  name="mode" value="FREENEWDEPTVISITREPORT">
<html:hidden name="freeNewVisitReportFB" property="jrxmlPath" value="<%=RegistrationConfig.REGISTRATION_JRXML_PATH_REPORT1 %>"/>
<html:hidden name="freeNewVisitReportFB" property="jrxmlName"/>
<html:hidden name="freeNewVisitReportFB" property="sysDate" value="<%=tDate%>"/>

</body>
<%
}catch(Exception e){e.printStackTrace();}
%>


      
         		
