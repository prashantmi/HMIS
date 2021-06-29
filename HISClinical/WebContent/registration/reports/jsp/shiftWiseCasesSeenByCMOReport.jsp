
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import ="java.util.*,registration.*,hisglobal.presentation.*" %>

<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/css/calendar-blue2.css"/>
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

function submitPage(mode)
{
	elmt=document.getElementsByName("reportMode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}
function showpdfrtfdiv()
{

  val=document.getElementsByName("graphOrText")[0].selectedIndex;
  if(val==1)
  {

  	document.getElementById("divrtfpdf").style.display="";
 	document.getElementById("mydiv").style.display=""; 
//    document.getElementById("divGraph").style.display="none";
//    document.getElementById("userControl").style.display="none";  
//    document.getElementById("userLabel").style.display="none";   	
   	document.getElementById("chartdiv").style.display="none"; 
   	document.getElementById("divpiebar").style.display="none"; 
   	
  }
  if(val==2)
  {

//    document.getElementById("divGraph").style.display="";
 	document.getElementById("divrtfpdf").style.display="none";
    document.getElementById("mydiv").style.display="none";  		
//    document.getElementById("userControl").style.display="";  
//    document.getElementById("userLabel").style.display="";
    document.getElementById("chartdiv").style.display=""; 
    document.getElementById("divpiebar").style.display="";   
  }
}
function shiftWiseCasesByCmoReportHandler()
{
	if(document.getElementsByName('shift')[0].value=="%")
	{
    	//alert("shift="+document.getElementsByName('shift')[0].value)
    	document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.SHIFT_WISE_CASES_SEEN_BY_CMO_ALL_SHIFT_REPORT%>";
	}
	else 
	{
		//alert("shift="+document.getElementsByName('shift')[0].value)
		document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.SHIFT_WISE_CASES_SEEN_BY_CMO_REPORT%>";         
	}	
	success=false;
	if(validateGraphOrText())
	{
		if(document.getElementsByName('graphOrText')[0].selectedIndex==1)
		{
	    	document.getElementsByName('reportMode')[0].value="<%=Config.TEXT%>";
	        if(validateTextual())
	        {      
	        	if(validateTodayOrDate())
	        	{
	           		return true;           	   
	        	}          	  
            }
        }//end of selection is for text based report
        if(document.getElementsByName('graphOrText')[0].selectedIndex==2)
		{
	    	document.getElementsByName('reportMode')[0].value="<%=Config.GRAPH%>";
	        	if(validateGraphForCategoryWiseReport())
	        	{
		        	if(validateTodayOrDate())        
		            success=true;	                
	            }
        }//end of selection is for graph based report	  
	}
	alert(document.getElementsByName('reportMode')[0].value)
	return success;
}//end of function categoryWisePatRegReportHandler() 
function validateTodayOrDate(){
 
    return true;
}

</script>

<his:TitleTag name="ShiftWise Cases seen By CMO Report">
</his:TitleTag>
	<%
		String pdfrtflabel="" ;
    	String pdfrtfcontrol="" ;
    	String emergencylabel="";
    	String emergencycontrol="";
    	String chartlabel="";
    	String chartcontrol="";
    	String fromDateLabel="" ;
    	String toDateLabel="" ;
    	String fromDateControl="" ;
    	String toDateControl="" ;
    	String fromLabel="" ;
    	String toLabel="" ;
    	String fromControl="" ;
    	String toControl="" ; 
	%>
	
	  <logic:equal name="shiftWiseCasesByCmoReportFB" property="choice" value="<%=Config.CHOICE_TODAY%>">
            <% fromDateLabel="display:none" ;
               toDateLabel="display:none" ;
               fromDateControl="display:none" ;
               toDateControl="display:none" ;
               fromLabel="" ;
               toLabel="" ;
               fromControl="" ;
               toControl="" ;  
            %>
      </logic:equal> 
      <logic:equal name="shiftWiseCasesByCmoReportFB" property="choice" value="<%=Config.CHOICE_DATE_WISE%>">
        	<% fromDateLabel="" ;
               toDateLabel="" ;
               fromDateControl="" ;
               toDateControl="" ;
               fromLabel="display:none" ;
               toLabel="display:none" ;
               fromControl="display:none" ;
               toControl="display:none" ;  
            %>
      </logic:equal> 
      
      <bean:define name="shiftWiseCasesByCmoReportFB" property="fromDate" id="frDate" type="java.lang.String"/>
	  <bean:define name="shiftWiseCasesByCmoReportFB" property="toDate" id="tDate" type="java.lang.String"/>          
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
 	<tr>
 		<td class="tdfonthead" width="25%">
              	<div id='divtoDate' align="right">
              	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="fromDate"/></font>
				</div>
	    </td>
           
        <td class="tdfont" width="25%">
	    	<div id='divfromDateControl' align="left">	               		 
				<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
			</div>
		 	
		</td>   
           
        <td class="tdfonthead" width="25%">
    		<div id='divtoDate' align="right">
         		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
         		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 		<bean:message key="toDate"/>
		 		</font>
    		</div>
    		
 		</td>   
        <td class="tdfont" width="25%">
			<div id='divtoDateControl' align="left">    
				<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>" />
    	 	</div>
    	</td>                 			
	</tr>  
	<tr>
		<td  width="25%"  class="tdfonthead">
		    <div align="right">
		    		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		    		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       		<bean:message key="cmoName"/>
	        		</font>
	        </div>
        </td>  
   		<td  width="25%"  class="tdfont" >  
   			<div id='divrtfpdf' style='<%=pdfrtfcontrol%>' align="left">
	      		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		    		<html:select name="shiftWiseCasesByCmoReportFB"  property="cmoCode" tabindex="1" styleClass="regcbo">
	        			<html:option value="%">All</html:option>
	        			<logic:present name="<%=RegistrationConfig.CMO_LIST %>">	
	        				<html:options collection="<%=RegistrationConfig.CMO_LIST %>" property="value" labelProperty="label"/>
	        			</logic:present>	
	        		</html:select> 		
	      		</font>		      
    		</div>
   		</td> 
   		<td  width="25%"  class="tdfonthead">
		    <div align="right">
		    		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		    		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       		<bean:message key="shift"/>
	        		</font>
	        </div>
        </td>  
   		<td  width="25%"  class="tdfont" >  
   			<div id='divrtfpdf' style='<%=pdfrtfcontrol%>' align="left">
	      		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		    		<html:select name="shiftWiseCasesByCmoReportFB"  property="shift" tabindex="1" styleClass="regcbo">
	        			<html:option value="%">All</html:option>
	        			<logic:present name="<%=RegistrationConfig.SHIFT_LIST %>">	
	        				<html:options collection="<%=RegistrationConfig.SHIFT_LIST %>" property="value" labelProperty="label"/>
	        			</logic:present>	
	        		</html:select> 		
	      		</font>		      
    		</div>
   		</td> 
	</tr>
 </table>       

</his:ContentTag>

<his:SubTitleTag name="Report generation options"> </his:SubTitleTag>
<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">     
		<tr>       
        	<td width="25%" nowrap="nowrap" class="tdfonthead" >
        		<font color="#FF0000">*</font>
	        	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          	<bean:message key="reportType"/>      
	        	</font>
        	</td>                
        	<td  width="25%" nowrap="nowrap" class="tdfont" > 
	        	<html:select name="shiftWiseCasesByCmoReportFB"  property="graphOrText" tabindex="1" styleClass="regcbo" onchange='showpdfrtfdiv()'>
		        	<html:option value="-1">Select Value</html:option>
		        	<html:option value="<%=Config.TEXT%>">Textual</html:option>
		        </html:select> 		
        	</td>  
        	<td  width="25%"  class="tdfonthead">
		    	<div id='mydiv' style='<%=pdfrtflabel%>'  align="right">
		    		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		    		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       		<bean:message key="pdfOrWord"/>
	        		</font>
	        	</div>
	        </td>  
	   		
	   		<td  width="25%"  class="tdfont" >  
	   			<div id='divrtfpdf' style='<%=pdfrtfcontrol%>' align="left">
		      		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			    		<html:select name="shiftWiseCasesByCmoReportFB"  property="reportType" tabindex="1" styleClass="regcbo">
		        			<html:option value="-1">Select Value</html:option>
		        			<html:option value="<%=Config.PDF%>">Acrobat Reader</html:option>
		        			<html:option value="<%=Config.RTF%>">HTML</html:option>	    
						</html:select> 		
		      		</font>		      
     			</div>
      		</td> 
	   	</tr>	
	</table>           
</his:ContentTag>

<his:status/>
<html:hidden name="shiftWiseCasesByCmoReportFB" property="reportMode"/>
<html:hidden name="shiftWiseCasesByCmoReportFB" property="mode" value="SHIFTWISECAESEBYCMOREPORT"/>
<html:hidden name="shiftWiseCasesByCmoReportFB" property="jrxmlPath" value="<%=RegistrationConfig.REGISTRATION_JRXML_PATH %>"/>
<html:hidden name="shiftWiseCasesByCmoReportFB" property="jrxmlName"/>
<html:hidden name="shiftWiseCasesByCmoReportFB" property="sysDate" value="<%=tDate%>"/>
      
    
    	 