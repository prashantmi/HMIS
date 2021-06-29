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
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 

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
}
function submitPage(mode){
	elmt=document.getElementsByName("reportMode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}
function showpdfrtfdiv(){


  val=document.getElementsByName("graphOrText")[0].selectedIndex;
  if(val==1){
  	document.getElementById("divrtfpdf").style.display="";
 	document.getElementById("mydiv").style.display=""; 
    document.getElementById("divGraph").style.display="none";
    document.getElementById("deptControl").style.display="none";  
   	document.getElementById("deptLabel").style.display="none";  
   	document.getElementById("chartdiv").style.display="none";  
   	document.getElementById("chartcontrol").style.display="none";  
   	document.getElementById("deptControl").style.display="";  
   	document.getElementById("deptLabel").style.display="";  
   	
   
  }
  if(val==2){
 
    document.getElementById("divGraph").style.display="";
 		document.getElementById("divrtfpdf").style.display="none";
    	document.getElementById("mydiv").style.display="none";  		
    	document.getElementById("deptControl").style.display="";  
    	document.getElementById("deptLabel").style.display="";  
    	document.getElementById("chartdiv").style.display="";  
    	document.getElementById("chartcontrol").style.display="";  
    	document.getElementById("deptControl").style.display="";  
  	 	document.getElementById("deptLabel").style.display=""; 
  }
}

function departmentWiseTotalPatReportHandler(){	 
	       if(document.getElementsByName('choice')[0].checked){
               document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.DEPARTMENT_WISE_TOTAL_PAT_TODAY%>";
	          }
	        else{
	                 
	          document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.DEPARTMENT_WISE_TOTAL_PAT_DATEWISE%>";         
	         }	   		
	   success=false;
	   if(validateGraphOrText()){ 
	       if(document.getElementsByName('graphOrText')[0].selectedIndex==1){
	          document.getElementsByName('reportMode')[0].value="<%=Config.TEXT%>";
	          if(validateTextual()){      
	           	   if(validateTodayOrDate()){
	           	     return true;           	   
	           	   }          	  
                }
           }//end of selection is for text based report
           if(document.getElementsByName('graphOrText')[0].selectedIndex==2){
	          document.getElementsByName('reportMode')[0].value="<%=Config.GRAPH%>";
	           if(validateGraphForDepartmentWiseTotalPatReport()){
		               if(validateTodayOrDate())        
		                success=true;	                
	                }
           }//end of selection is for graph based report	  
           
           
	  }
	  return success;
	 }//end of function departmentWiseTotalPatReportHandler() 




	</script>

<his:TitleTag name="Department Wise Total Patient Statistics">

</his:TitleTag>
  <%
              String pdfrtflabel="" ;
              String pdfrtfcontrol="" ;
              String deptlabel="" ;
              String deptcontrol="" ;
              String category="";

              String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
              String fromLabel="" ;
              String toLabel="" ;
              String fromControl="" ;
              String toControl="" ;    
              String chartlabel="";
              String chartcontrol="";
           %>		
           
      <logic:equal name="DepartmentWiseTotalPatFB" property="choice" value="<%=Config.CHOICE_TODAY%>">
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
      
      <logic:equal name="DepartmentWiseTotalPatFB" property="choice" value="<%=Config.CHOICE_DATE_WISE%>">
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
  
	   <bean:define name="DepartmentWiseTotalPatFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="DepartmentWiseTotalPatFB" property="toDate" id="tDate" type="java.lang.String"/>          
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

    
<his:SubTitleTag name="Report Details"> </his:SubTitleTag>	
<his:ContentTag>	
   <table width="100% cellspacing="1" cellpadding="0">
<tr>            
 <td class="tdfonthead" nowrap width="25%">       	      
		      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      <bean:message key="today"/>
		      </font>
		      <html:radio name="DepartmentWiseTotalPatFB" property="choice" tabindex="1" value="<%=Config.CHOICE_TODAY%>" onclick="showdivtoday()"/> 
		         <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       <bean:message key="dateWise"/></font> 
		        <html:radio name="DepartmentWiseTotalPatFB" property="choice" tabindex="1" value="<%=Config.CHOICE_DATE_WISE%>" onclick="showdivdatewise()"/>
 </td> 
 </tr>
 <tr>
 <td class="tdfonthead" width="25%">
        <div id='divfromDate' style='<%=fromDateLabel %>' align="right">
	        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="fromDate"/></font>
        </div>
        <div id='divfrom' style='<%=fromLabel %>' align="right">
	        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="from"/></font>
         </div>
 </td>
        
 <td class="tdfont" width="25%">
	    <div id='divfromDateControl' style='<%=fromDateControl %>'align="left">	               		 
			<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
		</div>
		 <span id='divfromhrcontrol' style='<%=fromControl %>'align="left">	            
		   <html:text name="DepartmentWiseTotalPatFB" tabindex="1" property="fromHour"  maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>	
		   <b>	<bean:message key="colon"/></b>
		</span>
		 <span id='divfromMinControl' style='<%=fromControl %>' align="left">         
			<html:text name="DepartmentWiseTotalPatFB" tabindex="1" property="fromMin"   maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>				
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
		<bean:message key="to"/></font>
         </div>
 </td>
		
 <td class="tdfont" width="25%">
		 <div id='divtoDateControl' style='<%=toDateControl %>' align="left">    
			<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
    	 </div>
    	  <span id='divtohrcontrol' style='<%=toControl%>' align="left">	            
		   <html:text name="DepartmentWiseTotalPatFB" tabindex="1" property="toHour"  maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>	
		   	<b><bean:message key="colon"/>	  </b>
		</span>
		 <span id='divtoMinControl' style='<%=toControl%>' align="left">         
			<html:text name="DepartmentWiseTotalPatFB" tabindex="1" property="toMin" maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>				
				<bean:message key="timeFormat"/>	  
    	 </span>
    	 
 </td>                 			
</tr> 
<tr>
   <td class="tdfonthead">
      <div id ="deptLabel"  align="right"  style='<%=deptlabel%>'>
		        <font color="#FF0000">*</font>
		           <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  <bean:message key="department"/>
				  </font>
      </div>
    </td>
    <td class="tdfont">
       <div id="deptControl" align="left"  style='<%=deptcontrol%>'>
     		 <html:select name="DepartmentWiseTotalPatFB"  property="departmentCode"  tabindex="1"  styleClass ="regcbo" >   
					  <html:option value="%">All Departments</html:option>
					  <logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>">
					  <html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" property = "value" labelProperty = "label"/>
			  		</logic:present>	
			  </html:select> 
	   </div>       
    </td>
</tr>
</table>
</his:ContentTag>
<his:SubTitleTag name="Report generation options"> </his:SubTitleTag>
  <his:ContentTag>
   <table width="100%" cellspacing="1" cellpadding="0">
       <tr>       
        <td width="25%" class="tdfonthead">
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="reportType"/>      
	        </font>
        </td>                
        <td width="25%" class="tdfont" > 
	        <html:select name="DepartmentWiseTotalPatFB"  property="graphOrText" tabindex="1" styleClass="regcbo" onchange='showpdfrtfdiv()'>
		        <html:option value="-1">Select Value</html:option>
		        <html:option value="<%=Config.TEXT%>">Textual</html:option>
		        <html:option value="<%=Config.GRAPH%>">Graphical</html:option>	
		       
			</html:select> 		
        </td>
       
       
        <logic:equal name="DepartmentWiseTotalPatFB" property="graphOrText" value="<%=Config.TEXT%>">
           <%
             pdfrtflabel="" ;
             pdfrtfcontrol="" ;
             chartcontrol="display:none";
             chartlabel="display:none";
             deptcontrol="";
             deptlabel="";
           %>
        </logic:equal>
        
        <logic:equal name="DepartmentWiseTotalPatFB" property="graphOrText" value="<%=Config.GRAPH%>">
           <%
             pdfrtflabel="display:none" ;
             pdfrtfcontrol="display:none" ;
             chartcontrol="";
             chartlabel="";
             deptcontrol="";
             deptlabel="";
             System.out.println("fghyfghgkgkgjkgjkgjkgjkgjkgjkgjkgjkgjkfhkfhfhkfg");
             
           %>
        </logic:equal>       
        <td width="25%" class="tdfonthead">
		    <div id='mydiv' style='<%=pdfrtflabel%>'  align="right">
		    <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       <bean:message key="pdfOrWord"/>
	        </font>
	        </div>
	        <div id='chartdiv' style='<%=chartlabel%>'  align="right">
		    <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       <bean:message key="charttype"/>
	        </font>
	        </div>
	        
	        
       </td>  
       
		    
      
               

	   <td class="tdfont" nowrap width="25%">       
	    
	       	  <div id='divrtfpdf' style='<%=pdfrtfcontrol%>' align="left">
		      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			     <html:select name="DepartmentWiseTotalPatFB"  property="reportType" tabindex="1" styleClass="regcbo">
		        <html:option value="-1">Select Value</html:option>
		        <html:option value="<%=Config.PDF%>">Acrobat Reader</html:option>
		        <html:option value="<%=Config.RTF%>">HTML</html:option>	    
			</html:select> 		
		      </font>		      
     		</div>
     	 
     		<div id='chartcontrol' style='<%=chartcontrol%>' align="left">
		      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			     <html:select name="DepartmentWiseTotalPatFB"  property="chartType" tabindex="1" styleClass="regcbo">
		        <html:option value="-1">Select Value</html:option>
		        <html:option value="<%=Config.PIE%>">PIE CHART</html:option>
		        <html:option value="<%=Config.BAR%>">BAR CHART</html:option>	    
		          <html:option value="<%=Config.LINE%>">LINE CHART</html:option>	    
			</html:select> 		
		      </font>		      
     		</div>
	   </td> 
	   </tr>
	   </table>
	   <div id="divGraph" style='<%=category%>' align="right">
	   <table width="100%" cellspacing="1" cellpadding="0">
	  
	<%--	<td width="14%" class="tdfonthead" >
		       <div align="right">
		       <font color="#FF0000">*</font>
			       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			       <bean:message key="gender"/>
		       </font>
		       </div>
         </td>
       
       <td width="17%" class="tdfont">
	       <html:select name="DepartmentWiseTotalPatFB" property="patGenderCode" tabindex="1" styleClass="regcbo">
	       <html:option value="-1">Select Value</html:option>
		   <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER %>" property  = "value" labelProperty = "label"/>
		   </html:select>
       </td> --%>
	  <%--  <tr>
         <td width="25%"    class="tdfonthead">
	         <div align="right">
	         <font color="#FF0000">*</font>
	         <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	         <bean:message key="primary"/> <bean:message key="category"/>
	         </font>
	         </div>
         </td>
        
        <td width="25%"  class = "tdfont">
        <font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
          <html:select name="DepartmentWiseTotalPatFB"  property="patPrimaryCatCode"  tabindex="1"  styleClass ="regcbo" onchange="if(this.value!='-1') submitPage('GETSECCAT')">   
		  <html:option value="-1">Select Value</html:option>
		  <html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY%>" property = "value" labelProperty = "label"/>
		</html:select>
        </font></td>
        
        <td width="25%"  class="tdfonthead">
        <div align="right">
        <font color="#FF0000">*</font>
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="secondary"/><bean:message key="category"/>
        </font>
        </div>
        </td>
        
        <td width="25%" class="tdfont">
        <html:select name="DepartmentWiseTotalPatFB" property="patSecondaryCatCode" tabindex="1" styleClass ="regcbo">
        <html:option value="">Select Value</html:option>
		<html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY%>" property = "value" labelProperty = "label"/>		   
		</html:select>
		</td>
		</tr>--%>
</table>
</div>		 
	  

</his:ContentTag> 

<input type="hidden"  name="reportMode">
<input type="hidden"  name="mode" value="DEPARTMENTWISETOTALPATREPORT">
<html:hidden name="DepartmentWiseTotalPatFB" property="jrxmlPath" value="<%=RegistrationConfig.REGISTRATION_JRXML_PATH %>"/>
<html:hidden name="DepartmentWiseTotalPatFB" property="jrxmlName" />
<html:hidden name="DepartmentWiseTotalPatFB" property="sysDate" value="<%=tDate%>"/>
