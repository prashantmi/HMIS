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

function consultantRosterReportHandler()
{
	
    	document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.CONSULTANT_ROSTER_REPORT%>";
	
	success=false;
   if(validateGraphOrText())
	    { 
			if(document.getElementsByName('graphOrText')[0].selectedIndex==1)
				{
		    	 document.getElementsByName('reportMode')[0].value="<%=Config.TEXT%>";
		    	 success=true;
		    	 return success;
		   	 
		    	 }
		   
	    }//end of selection is for text based report	
	    	else    
     if(document.getElementsByName('graphOrText')[0].selectedIndex==2)
		{	 
			 success=true;
			 return success;
	    	document.getElementsByName('reportMode')[0].value="<%=Config.GRAPH%>";
	     
        }//end of selection is for graph based report	  
           
	  
	  
}//end of function OutPatStatReportHandler() 
		

</script>

<his:TitleTag name="Consultant Roster Report" >
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
          <logic:equal name="consultantRosterFB" property="choice" value="<%=Config.CHOICE_TODAY%>">
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
      
      <logic:equal name="consultantRosterFB" property="choice" value="<%=Config.CHOICE_DATE_WISE%>">
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
      
       <logic:equal name="consultantRosterFB" property="choice" value="<%=Config.CHOICE_MONTH_WISE%>">
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
      
       <logic:equal name="consultantRosterFB" property="choice" value="<%=Config.CHOICE_YEAR_WISE%>">
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
      
       <bean:define name="consultantRosterFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="consultantRosterFB" property="toDate" id="tDate" type="java.lang.String"/>
	   
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
	
	<table width="100%" cellspacing="1" cellpadding="0">
	<tr>	
	
 		<td class="tdfonthead" nowrap width="25%" >  

      			      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="department"/>
				  </font>

 			</td>
      	<td class="tdfont"  width="25%" >

     		 		<html:select name="consultantRosterFB"  property="departmentCode"  tabindex="1"  styleClass="regcbo" onchange="showUnit()" >   
					 
					  <html:option value="%">All Departments</html:option>
					  	<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT%>">
					
					  <html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT%>" property = "value" labelProperty = "label"/>
					  </logic:present>
			  		</html:select> 

			  	
		</td>
			<td class="tdfonthead" nowrap width="25%" >  

      			      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="unit"/>
				  </font>

 			</td>
      	<td class="tdfont"  width="25%" >

     		 		<html:select name="consultantRosterFB"  property="unitCode"  tabindex="1"  styleClass="regcbo" >
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
	        	<html:select name="consultantRosterFB"  property="graphOrText" tabindex="1" styleClass="regcbo" >
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
        		<html:select name="consultantRosterFB" property="reportType" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>        	
					<html:option value="<%=Config.PDF %>">Acrobat Reader</html:option>
					<html:option value="<%=Config.RTF %>">HTML</html:option>
        		</html:select>
        	
        	</td>
    </tr>    	
	</table>        
</his:ContentTag>

<input type="hidden"  name="reportMode">
<input type="hidden"  name="mode" value="CONSULTANTROSTERREPORT">
<html:hidden name="consultantRosterFB" property="jrxmlPath" value="<%=RegistrationConfig.REGISTRATION_JRXML_PATH_REPORT1 %>"/>
<html:hidden name="consultantRosterFB" property="jrxmlName"/>
<html:hidden name="consultantRosterFB" property="sysDate" value="<%=tDate%>"/>
<%
}catch(Exception e){e.printStackTrace();}
%>


      
         		
