
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
	   
	function showdivtoday(){	
	document.getElementById("divfrom").style.display="";
	document.getElementById("divfromhrcontrol").style.display="";
	document.getElementById("divfromMinControl").style.display="";
	document.getElementById("divto").style.display="";
	document.getElementById("divtohrcontrol").style.display="";
	document.getElementById("divtoMinControl").style.display="";
	document.getElementById("divfromDate").style.display="none";
	document.getElementById("divfromDateControl").style.display="none";
	document.getElementById("divtoDate").style.display="none";
	document.getElementById("divtoDateControl").style.display="none";
	
	document.getElementById("divfromDateMonthly").style.display="none";
	document.getElementById("divfromDateMonthlyControl").style.display="none";
	document.getElementById("divtoMonthlyDate").style.display="none";
	document.getElementById("divtoDateMonthlyControl").style.display="none";
	document.getElementById("divfromDateYearly").style.display="none";
	document.getElementById("divfromDateYearlyControl").style.display="none";
	document.getElementById("divtoDateYearly").style.display="none";
	document.getElementById("divtoDateYearlyControl").style.display="none";
} 
           
function showdivdatewise(){
	document.getElementById("divfrom").style.display="none";
	document.getElementById("divfromhrcontrol").style.display="none";
	document.getElementById("divfromMinControl").style.display="none";
	document.getElementById("divto").style.display="none";
	document.getElementById("divtohrcontrol").style.display="none";
	document.getElementById("divtoMinControl").style.display="none";
	document.getElementById("divfromDate").style.display="";
	document.getElementById("divfromDateControl").style.display="";
	document.getElementById("divtoDate").style.display="";
	document.getElementById("divtoDateControl").style.display="";
	
	document.getElementById("divfromDateMonthly").style.display="none";
	document.getElementById("divfromDateMonthlyControl").style.display="none";
	document.getElementById("divtoMonthlyDate").style.display="none";
	document.getElementById("divtoDateMonthlyControl").style.display="none";
	document.getElementById("divfromDateYearly").style.display="none";
	document.getElementById("divfromDateYearlyControl").style.display="none";
	document.getElementById("divtoDateYearly").style.display="none";
	document.getElementById("divtoDateYearlyControl").style.display="none";
}

function showdivMonthwise(){
	document.getElementById("divfrom").style.display="none";
	document.getElementById("divfromhrcontrol").style.display="none";
	document.getElementById("divfromMinControl").style.display="none";
	document.getElementById("divto").style.display="none";
	document.getElementById("divtohrcontrol").style.display="none";
	document.getElementById("divtoMinControl").style.display="none";
	document.getElementById("divfromDate").style.display="none";
	document.getElementById("divfromDateControl").style.display="none";
	document.getElementById("divtoDate").style.display="none";
	document.getElementById("divtoDateControl").style.display="none";
	document.getElementById("divfromDateMonthly").style.display="";
	document.getElementById("divfromDateMonthlyControl").style.display="";
	document.getElementById("divtoMonthlyDate").style.display="";
	document.getElementById("divtoDateMonthlyControl").style.display="";
	document.getElementById("divfromDateYearly").style.display="none";
	document.getElementById("divfromDateYearlyControl").style.display="none";
	document.getElementById("divtoDateYearly").style.display="none";
	document.getElementById("divtoDateYearlyControl").style.display="none";
}

function showdivYearwise(){
	document.getElementById("divfrom").style.display="none";
	document.getElementById("divfromhrcontrol").style.display="none";
	document.getElementById("divfromMinControl").style.display="none";
	document.getElementById("divto").style.display="none";
	document.getElementById("divtohrcontrol").style.display="none";
	document.getElementById("divtoMinControl").style.display="none";
	document.getElementById("divfromDate").style.display="none";
	document.getElementById("divfromDateControl").style.display="none";
	document.getElementById("divtoDate").style.display="none";
	document.getElementById("divtoDateControl").style.display="none";
	document.getElementById("divfromDateMonthly").style.display="none";
	document.getElementById("divfromDateMonthlyControl").style.display="none";
	document.getElementById("divtoMonthlyDate").style.display="none";
	document.getElementById("divtoDateMonthlyControl").style.display="none";
	document.getElementById("divfromDateYearly").style.display="";
	document.getElementById("divfromDateYearlyControl").style.display="";
	document.getElementById("divtoDateYearly").style.display="";
	document.getElementById("divtoDateYearlyControl").style.display="";
}

  function validateTodayOrDate(){
     
     success=false;        	   
            if(document.getElementsByName('choice')[0].checked){            
            //   alert('Today option selectedwwwwwwwwww');               
                if(validateHrMin())
                {
            //      alert("validateHrMin validated");
                  success=true;     
                 }
            }
            //case Date
            if(document.getElementsByName('choice')[1].checked){
    //           alert('DateWise option selected:: Code for validation to be added later'); 
               
               valFromDate=document.getElementsByName('fromDate')[0];
	           valToDate=document.getElementsByName('toDate')[0];
	           valSysDate=document.getElementsByName('sysDate')[0];
      
               if(compareDateCall(valFromDate,valToDate,2,"from date","to date") && compareDateCall(valToDate,valSysDate,2,"to date","system date"))
               {
                success=true;
               }             
            }
            
               if(document.getElementsByName('choice')[2].checked){
    //           alert('DateWise option selected:: Code for validation to be added later'); 
               
               valFromDate=document.getElementsByName('fromDate')[0];
	           valToDate=document.getElementsByName('toDate')[0];
	           valSysDate=document.getElementsByName('sysDate')[0];
      
               if(compareDateCall(valFromDate,valToDate,2,"from date","to date") && compareDateCall(valToDate,valSysDate,2,"to date","system date"))
               {
                success=true;
               }             
            }
               if(document.getElementsByName('choice')[3].checked){
    //           alert('DateWise option selected:: Code for validation to be added later'); 
               
               valFromDate=document.getElementsByName('fromDate')[0];
	           valToDate=document.getElementsByName('toDate')[0];
	           valSysDate=document.getElementsByName('sysDate')[0];
      
               if(compareDateCall(valFromDate,valToDate,2,"from date","to date") && compareDateCall(valToDate,valSysDate,2,"to date","system date"))
               {
                success=true;
               }             
            }  
            return success;        
    } 
   
   function showUnit()
{
	
	submitPage('UNITAA');
}


function submitPage(mode){

	elmt=document.getElementsByName("reportMode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function newhospitalPatStatStateReportHandler()
{

  if(document.getElementsByName('choice')[0].checked)
	{
		if(document.getElementsByName('patientType')[0].value=="%")
		{
    	document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.HOSPITAL_PATIENT_STATISTICS_STATE_WISE_TODAY%>";
    	}
    	if(document.getElementsByName('patientType')[0].value=="0")
    	{
    	document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.HOSPITAL_PATIENT_STATISTICS_STATE_WISE_TODAY_NEW_PATIENT%>";
    	}
    	if(document.getElementsByName('patientType')[0].value=="1")
    	{
    	document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.HOSPITAL_PATIENT_STATISTICS_STATE_WISE_TODAY_OLD_PATIENT%>";
    	}
	}
	else
  if(document.getElementsByName('choice')[1].checked)
	{	
		if(document.getElementsByName('patientType')[0].value=="%")
		{
    	document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.HOSPITAL_PATIENT_STATISTICS_STATEWSIE_DATEWISE%>";
    	}
    	if(document.getElementsByName('patientType')[0].value=="0")
    	{
    	document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.HOSPITAL_PATIENT_STATISTICS_STATEWSIE_DATEWISE_NEW_PATIENT%>";
    	}
    	if(document.getElementsByName('patientType')[0].value=="1")
    	{
    	document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.HOSPITAL_PATIENT_STATISTICS_STATEWSIE_DATEWISE_OLD_PATIENT%>";
    	}
		         
	}
	else
  if(document.getElementsByName('choice')[2].checked)
	{	
		if(document.getElementsByName('patientType')[0].value=="%")
		{
    	document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.HOSPITAL_PATIENT_STATISTICS_STATEWSIE_MONTHWISE%>";
    	}
    	if(document.getElementsByName('patientType')[0].value=="0")
    	{
    	document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.HOSPITAL_PATIENT_STATISTICS_STATEWSIE_MONTHWISE_NEW_PATIENT%>";
    	}
    	if(document.getElementsByName('patientType')[0].value=="1")
    	{
    	document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.HOSPITAL_PATIENT_STATISTICS_STATEWSIE_MONTHWISE_OLD_PATIENT%>";
    	}
		         
	}
	else
  if(document.getElementsByName('choice')[3].checked)
	{	
		if(document.getElementsByName('patientType')[0].value=="%")
		{
    	document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.HOSPITAL_PATIENT_STATISTICS_STATEWSIE_YEARWISE%>";
    	}
    	if(document.getElementsByName('patientType')[0].value=="0")
    	{
    	document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.HOSPITAL_PATIENT_STATISTICS_STATEWSIE_YEARWISE_NEW_PATIENT%>";
    	}
    	if(document.getElementsByName('patientType')[0].value=="1")
    	{
    	document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.HOSPITAL_PATIENT_STATISTICS_STATEWSIE_YEARWISE_OLD_PATIENT%>";
    	}
		         
	}
	
	success=false;
    if(validateGraphOrText())
    { 
		if(document.getElementsByName('graphOrText')[0].selectedIndex==1)
		{
	    	document.getElementsByName('reportMode')[0].value="<%=Config.TEXT%>";
	    	 success=true;
	    	 if(document.getElementsByName('choice')[0].checked)
	    	 {
	    	  if(validateTextual())
		        { 
			    	 if(validateTodayOrDate())
			        	{
			          		success=true; 
			           		return success;
			           		       	   
			           	}          	  
			    	 else
			    	 {
			    	 	success=false;
			    	 	return success;
			    	 }
		    	} 
	    	 }
	   	 else
	    	 {
	    	  if(validateTextual())
		        { 
			    	 if(validateTodayOrDate())
			        	{
			           		success=true; 
			           		return success;  
			           		       	   
			           	}          	  
			    	 else
			    	 {
			    	 	success=false;
			    	 	return success;
			    	 }
			    }	 
	    	 }
	   
        }//end of selection is for text based report	
        
       if(document.getElementsByName('graphOrText')[0].selectedIndex==2)
		{	 
			 success=true;
			 return success;
	    	document.getElementsByName('reportMode')[0].value="<%=Config.GRAPH%>";
	     
        }//end of selection is for graph based report	  
           
	  }
	  
}//end of function OutPatStatReportHandler() 
		

</script>

<his:TitleTag name="State Wise Report" >
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
              
              String fromDateMonthlyControl="" ;
              String toDateMonthlyControl="" ;
              String fromDateYearlyControl="" ;
              String toDateYearlyControl="" ;
              
         %>
          <logic:equal name="hospitalPatStatStateNewFB" property="choice" value="<%=Config.CHOICE_TODAY%>">
             <%
               fromDateLabel="display:none" ;
               toDateLabel="display:none" ;
               fromDateControl="display:none" ;
               toDateControl="display:none" ;
               fromLabel="" ;
               toLabel="" ;
               fromControl="" ;
               toControl="" ;  
               
               fromDateMonthlyControl="display:none" ;
               toDateMonthlyControl="display:none" ;
               fromDateYearlyControl="display:none" ;
               toDateYearlyControl="display:none" ;
            %>
      </logic:equal>     
      
      <logic:equal name="hospitalPatStatStateNewFB" property="choice" value="<%=Config.CHOICE_DATE_WISE%>">
        <%     fromDateLabel="" ;
               toDateLabel="" ;
               fromDateControl="" ;
               toDateControl="" ;
               fromLabel="display:none" ;
               toLabel="display:none" ;
               fromControl="display:none" ;
               toControl="display:none" ;  
               
               fromDateMonthlyControl="display:none" ;
               toDateMonthlyControl="display:none" ;
               fromDateYearlyControl="display:none" ;
               toDateYearlyControl="display:none" ;
              %>
      </logic:equal>
      
       <logic:equal name="hospitalPatStatStateNewFB" property="choice" value="<%=Config.CHOICE_MONTH_WISE%>">
        <%     fromDateLabel="" ;
               toDateLabel="" ;
               fromDateControl="display:none" ;
               toDateControl="display:none" ;
               fromLabel="display:none" ;
               toLabel="display:none" ;
               fromControl="display:none" ;
               toControl="display:none" ;  
               
               fromDateMonthlyControl="" ;
               toDateMonthlyControl="" ;
               fromDateYearlyControl="display:none" ;
               toDateYearlyControl="display:none" ;
              %>
      </logic:equal>
      
       <logic:equal name="hospitalPatStatStateNewFB" property="choice" value="<%=Config.CHOICE_YEAR_WISE%>">
        <%     fromDateLabel="" ;
               toDateLabel="" ;
               fromDateControl="display:none" ;
               toDateControl="display:none" ;
               fromLabel="display:none" ;
               toLabel="display:none" ;
               fromControl="display:none" ;
               toControl="display:none" ;  
               
               fromDateMonthlyControl="display:none" ;
               toDateMonthlyControl="display:none" ;
               fromDateYearlyControl="" ;
               toDateYearlyControl="" ;
              %>
      </logic:equal> 
      
       <bean:define name="hospitalPatStatStateNewFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="hospitalPatStatStateNewFB" property="toDate" id="tDate" type="java.lang.String"/>
	   
	   <bean:define name="hospitalPatStatStateNewFB" property="fromDateMonthly" id="frDateMonth" type="java.lang.String"/>
	   <bean:define name="hospitalPatStatStateNewFB" property="toDateMonthly" id="tDateMonth" type="java.lang.String"/>
	   
	   <bean:define name="hospitalPatStatStateNewFB" property="fromDateYearly" id="frDateYear" type="java.lang.String"/>
	   <bean:define name="hospitalPatStatStateNewFB" property="toDateYearly" id="tDateYear" type="java.lang.String"/>          
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

		  if(frDateMonth==null||frDateMonth.equalsIgnoreCase("")){  
	         	System.out.println("dt::::::::::::::::");        	
	        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
	        	System.out.println("dt:::::::::"+dt);        	
	        	frDateMonth = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
	         }
	  
			  if(tDateMonth==null||tDateMonth.equalsIgnoreCase("")){  
			   	System.out.println("dt::::::::::::::::");        	
			  	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
			  	System.out.println("dt:::::::::"+dt);        	
			  	tDateMonth = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			   }

			  if(frDateYear==null||frDateYear.equalsIgnoreCase("")){  
		         	System.out.println("dt::::::::::::::::");        	
		        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
		        	System.out.println("dt:::::::::"+dt);        	
		        	frDateYear = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		         }
		  
				  if(tDateYear==null||tDateYear.equalsIgnoreCase("")){  
				   	System.out.println("dt::::::::::::::::");        	
				  	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
				  	System.out.println("dt:::::::::"+dt);        	
				  	tDateYear = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
				   }
			  
			  
		  
		%>  

<his:SubTitleTag name="Report Details">
</his:SubTitleTag>

<his:ContentTag>	
	<table width="100% cellspacing="1" cellpadding="0">
		<tr>            
 			<td class="tdfonthead" nowrap width="25%">       	      
		    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      		<bean:message key="today"/>
		      	</font>
		      		<html:radio name="hospitalPatStatStateNewFB" property="choice" tabindex="1" value="<%=Config.CHOICE_TODAY%>" onclick="showdivtoday()"/>
		      		</td>
		      	<td class="tdfonthead" nowrap width="25%">
		      		 
		        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       		<bean:message key="dateWise"/></font> 
		        	<html:radio name="hospitalPatStatStateNewFB" property="choice" tabindex="1" value="<%=Config.CHOICE_DATE_WISE%>" onclick="showdivdatewise()"/>
		        	</td>
		        	<td class="tdfonthead" nowrap width="25%">
		        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      		<bean:message key="monthWise"/>
		      	</font>
		      		<html:radio name="hospitalPatStatStateNewFB" property="choice" tabindex="1" value="<%=Config.CHOICE_MONTH_WISE%>" onclick="showdivMonthwise()"/>
		      		</td>
		      		<td class="tdfonthead" nowrap width="25%"> 
		        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       		<bean:message key="yearWise"/></font> 
		        	<html:radio name="hospitalPatStatStateNewFB" property="choice" tabindex="1" value="<%=Config.CHOICE_YEAR_WISE%>" onclick="showdivYearwise()"/>
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
        			<div id='divfromDateMonthly' style='<%=fromDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromDate"/>
					</font>
        		</div>
        		<div id='divfromDateYearly' style='<%=fromDateLabel %>' align="right">
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
				<div id='divfromDateMonthlyControl' style='<%=fromDateControl %>'align="left">	               		 
					<his:date name='fromDateMonthly' dateFormate="%d-%b-%Y" value="<%=frDateMonth%>"/>
				</div>
				<div id='divfromDateYearlyControl' style='<%=fromDateControl %>'align="left">	               		 
					<his:date name='fromDateYearly' dateFormate="%d-%b-%Y" value="<%=frDateYear%>"/>
				</div>
		 		<span id='divfromhrcontrol' style='<%=fromControl %>'align="left">	            
		   			<html:text name="hospitalPatStatStateNewFB" tabindex="1" property="fromHour"  maxlength="2" size="3"  onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)" />	
		  			 <b>	<bean:message key="colon"/></b>
				</span>
		 		<span id='divfromMinControl' style='<%=fromControl %>' align="left">         
					<html:text name="hospitalPatStatStateNewFB" tabindex="1" property="fromMin"   maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>				
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
    			<div id='divtoMonthlyDate' style='<%=toDateLabel %>' align="right">
         			<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
         			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="toDate"/>
		 			</font>
    			</div>
    			<div id='divtoDateYearly' style='<%=toDateLabel %>' align="right">
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
    	 		<div id='divtoDateMonthlyControl' style='<%=toDateControl %>' align="left">    
					<his:date name='toDateMonthly' dateFormate="%d-%b-%Y" value="<%=tDateMonth%>" />
    	 		</div>
    	 		<div id='divtoDateYearlyControl' style='<%=toDateControl %>' align="left">    
					<his:date name='toDateYearly' dateFormate="%d-%b-%Y" value="<%=tDateYear%>" />
    	 		</div>
    	  		<span id='divtohrcontrol' style='<%=toControl%>' align="left">	            
		   			<html:text name="hospitalPatStatStateNewFB" tabindex="1" property="toHour"  maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>	
	   				<b><bean:message key="colon"/>	  </b>
				</span>
		 		<span id='divtoMinControl' style='<%=toControl%>' align="left">         
					<html:text name="hospitalPatStatStateNewFB" tabindex="1" property="toMin" maxlength="2" size="3"  onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>				
						<bean:message key="timeFormat"/>	  
    	 		</span>
			</td>                 			
		</tr>
	</table>
	
	<table width="100%" cellspacing="1" cellpadding="0">
	<tr>	
	
 		<td class="tdfonthead" nowrap width="25%" align="right" >  
			<div align="right">
      			      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="department"/>
				  </font>
			</div>
 			</td>
      	<td class="tdfont"  width="25%" >

     		 		<html:select name="hospitalPatStatStateNewFB"  property="departmentCode"  tabindex="1"  styleClass="regcbo" onchange="showUnit()" >   
					 
					  <html:option value="%">All Departments</html:option>
					  	<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT%>">
					
					  <html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT%>" property = "value" labelProperty = "label"/>
					  </logic:present>
			  		</html:select> 

			  	
		</td>
			<td class="tdfonthead" nowrap width="25%"  >  
				<div align="right">
      			      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="unit"/>
				  </font>
				</div>
 			</td>
      	<td class="tdfont"  width="25%" >

     		 		<html:select name="hospitalPatStatStateNewFB"  property="unitCode"  tabindex="1"  styleClass="regcbo" >
     		 		<logic:notPresent name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>">
     		 			
					  	<html:option value="%">All Units</html:option>
     		 		</logic:notPresent>  
     		 		<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>">
						
						<html:option value="%">All Units</html:option>
						<html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>" property = "value" labelProperty = "label"/>
					</logic:present>
			  		</html:select> 

			  	
		</td>
      	
      </tr>
      <tr>	
			 <td class="tdfonthead" nowrap width="25%" >  
					<div align="right">
	      			      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
			            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  		<bean:message key="patientType"/>
					  </font>
					</div>
				</td>
	      		<td class="tdfont"  width="25%" >
					<html:select name="hospitalPatStatStateNewFB"  property="patientType"  tabindex="1"  styleClass="regcbo" onchange="changePatientType(this)" >   
				  		<html:option value="%">All</html:option>
					  		<html:option value="0">New</html:option>
					  		<html:option value="1">Revisit</html:option>
					  </html:select> 
			</td>
			<td class="tdfonthead" nowrap width="25%" >  
			</td>
	      	<td class="tdfont"  width="25%" >
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
	        	<html:select name="hospitalPatStatStateNewFB"  property="graphOrText" tabindex="1" styleClass="regcbo" >
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
        		<html:select name="hospitalPatStatStateNewFB" property="reportType" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>        	
					<html:option value="<%=Config.PDF %>">Acrobat Reader</html:option>
					<html:option value="<%=Config.RTF %>">HTML</html:option>
        		</html:select>
        	
        	</td>
    </tr>    	
	</table>        
</his:ContentTag>

<input type="hidden"  name="reportMode">
<input type="hidden"  name="mode" value="HOSPITALPATSTATSTATEREPORT">
<html:hidden name="hospitalPatStatStateNewFB" property="jrxmlPath" value="<%=RegistrationConfig.REGISTRATION_JRXML_PATH_REPORT1 %>"/>
<html:hidden name="hospitalPatStatStateNewFB" property="jrxmlName"/>
<html:hidden name="hospitalPatStatStateNewFB" property="sysDate" value="<%=tDate%>"/>



      
         		
