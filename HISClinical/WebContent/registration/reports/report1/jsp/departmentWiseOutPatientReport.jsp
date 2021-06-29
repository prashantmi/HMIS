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
<%@page import="mrd.MrdConfig"%>
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
	
	      
function showDeptWise()
{
	checkTodayDatewise();
/*
	if(document.getElementsByName('option')[0].checked)
		{
		document.getElementById("deptLabelDeptWise").style.display="block";
		document.getElementById("deptControlDeptWise").style.display="block";
		
		
		document.getElementById("unitLabelUnitWise").style.display="none";
		document.getElementById("unitControlUnitWise").style.display="none";
		document.getElementById("specControlUnitWise").style.display="none";
		document.getElementById("specLabelUnitWise").style.display="none";
		}
	if(document.getElementsByName('option')[1].checked)
		{
		document.getElementById("deptLabelDeptWise").style.display="none";
		document.getElementById("deptControlDeptWise").style.display="none";
		
		
		document.getElementById("unitLabelUnitWise").style.display="block";
		document.getElementById("unitControlUnitWise").style.display="block";
		document.getElementById("specControlUnitWise").style.display="block";
		document.getElementById("specLabelUnitWise").style.display="block";
		}
		*/
}
	
function showUnit()
{
	document.getElementById("deptLabelDeptWise").style.display="none";
	document.getElementById("deptControlDeptWise").style.display="none";	
	document.getElementById("unitLabelUnitWise").style.display="block";
	document.getElementById("unitControlUnitWise").style.display="block";
	document.getElementById("specControlUnitWise").style.display="block";
	document.getElementById("specLabelUnitWise").style.display="block";
}	
	   
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

function departmentWiseOutPatientReportValidtion()
{

	
	if(document.getElementsByName('choice')[0].checked)
	{
    	document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.DEPARTMENT_WISE_OUT_PATIENT_TODAY_REPORT%>";
	}
	else
	{
		document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.DEPARTMENT_WISE_OUT_PATIENT_DATE_WISE_REPORT%>";         
	}
	
	
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
	        if(validateGraphForAgeWiseReport())
	        {
		    	if(validateTodayOrDate())        
		        success=true;	                
	        }
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
}//end of function departmentWiseOutPatientReportFB() 
		

function deptSelected(cboSpec)
{
	var specId=cboSpec.value;
	var comboVar=document.forms[0].combo.value.split("#");
	var newCombo;
		
	newCombo= "<option value='-1' > Select Value </option> "+"<option value='%' > All Units </option> ";
	
	for(var j=0;j<comboVar.length;j++)
	{
		var comboValue=comboVar[j];
		comboValue=comboValue.split("@");
		if(specId==comboValue[1])
		{
			newCombo= newCombo+"<option value="+comboValue[0]+">"+comboValue[2]+"</option>";
		}
		else if(specId=='%')
		{
			newCombo= newCombo+"<option value="+comboValue[0]+">"+comboValue[2]+"</option>";
		}
					
		
	}
	document.getElementsByName('unit')[0].innerHTML=newCombo;
	
}

</script>

<body onload="showDeptWise()">


<%String title="";
if(Config.CLIENT.equals(Config.CLIENT_GNCTD)){
	title="Period Wise Use Of Services By The Patient";
}else{
	title="Department Wise Outpatients";
}%>

<his:TitleTag name="<%=title %>" >
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
          <logic:equal name="departmentWiseOutPatientReportFB" property="choice" value="<%=Config.CHOICE_TODAY%>">
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
      
      <logic:equal name="departmentWiseOutPatientReportFB" property="choice" value="<%=Config.CHOICE_DATE_WISE%>">
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
      
       <bean:define name="departmentWiseOutPatientReportFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="departmentWiseOutPatientReportFB" property="toDate" id="tDate" type="java.lang.String"/>          
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
 			<td class="tdfonthead" nowrap width="25%">       	      
		    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      		<bean:message key="today"/>
		      	</font>
		      		<html:radio name="departmentWiseOutPatientReportFB" property="choice" tabindex="1" value="<%=Config.CHOICE_TODAY%>" onclick="showdivtoday()"/> 
		        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       		<bean:message key="dateWise"/></font> 
		        	<html:radio name="departmentWiseOutPatientReportFB" property="choice" tabindex="1" value="<%=Config.CHOICE_DATE_WISE%>" onclick="showdivdatewise()"/>
			</td> 
			
			<td class="tdfonthead" nowrap width="25%">       	      
		    </td> 
			
			<td class="tdfonthead" nowrap width="25%">       	      
		    </td> 
		    
		    <td class="tdfonthead" nowrap width="25%">       	      
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
		   			<html:text name="departmentWiseOutPatientReportFB" tabindex="1" property="fromHour"  maxlength="2" size="3"  onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)" />	
		  			 <b>	<bean:message key="colon"/></b>
				</span>
		 		<span id='divfromMinControl' style='<%=fromControl %>' align="left">         
					<html:text name="departmentWiseOutPatientReportFB" tabindex="1" property="fromMin"   maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>				
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
		   			<html:text name="departmentWiseOutPatientReportFB" tabindex="1" property="toHour"  maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>	
	   				<b><bean:message key="colon"/>	  </b>
				</span>
		 		<span id='divtoMinControl' style='<%=toControl%>' align="left">         
					<html:text name="departmentWiseOutPatientReportFB" tabindex="1" property="toMin" maxlength="2" size="3"  onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>				
						<bean:message key="timeFormat"/>	  
    	 		</span>
			</td>                 			
		</tr>
	</table>
	
	<table width="100%" cellspacing="1" cellpadding="0">
	
<%-- 	
<tr>            
 			<td class="tdfonthead"  width="25%">       	      
		    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      		<bean:message key="deptWise"/>
		      	</font></td>
		      	<td class="tdfont" nowrap width="25%">
		      		<html:radio  name="departmentWiseOutPatientReportFB" property="option" tabindex="1"  value="<%=RegistrationConfig.DEPT_WISE%>"  onclick="showDeptWise()" />
		      		</td>
		      		<td class="tdfonthead"  width="25%">
		        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       		<bean:message key="unitWise"/>
		       	</font> </td>
		       	<td class="tdfont" nowrap width="25%">
		        	<html:radio name="departmentWiseOutPatientReportFB" property="option" tabindex="1" value="<%=RegistrationConfig.UNIT_WISE%>" onclick="showUnit()"/>
			</td> 
			 
 		</tr>
--%> 		
	<tr>	
	
 		<td class="tdfonthead" nowrap width="25%" >  
 		<div id="deptLabelDeptWise" style="">
      			      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="department"/>
				  </font>
      			</div>
 <%-- 			
 		<div id="specLabelUnitWise" style="display:none;">
      			      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		Speciality Type
				  </font>
      			</div>
--%> 	 		
 			</td>
      	<td class="tdfont"  width="25%" >
      	<div id="deptControlDeptWise" >
     		 		<html:select name="departmentWiseOutPatientReportFB"  property="departmentCode"  tabindex="1"  styleClass="regcbo" >   
					  <logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT%>">
					  <html:option value="%">All Departments</html:option>
					  <html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT%>" property = "value" labelProperty = "label"/>
					  </logic:present>
			  		</html:select> 
		</div>
			  	
    	<div id="specControlUnitWise" style="display:none;">
    	 	<html:select name="departmentWiseOutPatientReportFB"  property="speciality"  tabindex="1" onchange="deptSelected(this);" onkeypress="if(event.keyCOde==13)deptSelected(this);"  styleClass="regcbo" >   
			
			 <html:option value="%">All </html:option>
			 <html:option value="1">General </html:option>
			 <html:option value="2">Special </html:option>
			 <html:option value="3">Causality </html:option>
			  
			</html:select>
		</div> 
		</td>
			<td class="tdfonthead" nowrap width="25%" >
 			
      			<div id="unitLabelUnitWise" style="display:none;">
 		      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="unit"/>
				  </font>
    	</div>
    			</td>
      			<td class="tdfont"  width="25%">
       			<div id="unitControlUnitWise" style="display:none;">
    			<html:select name="departmentWiseOutPatientReportFB"  property="unit"  tabindex="1"  styleClass ="regcbo" >
    			<html:option value="%">All Units</html:option>
				<logic:present name="<%=MrdConfig.UNITS_BASED_ON_SPECIALITY%>">
				  <html:options collection ="<%=MrdConfig.UNITS_BASED_ON_SPECIALITY%>" property = "value" labelProperty = "label"/>
				</logic:present>  
				</html:select>
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
	        	<html:select name="departmentWiseOutPatientReportFB"  property="graphOrText" tabindex="1" styleClass="regcbo" >
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
        		<html:select name="departmentWiseOutPatientReportFB" property="reportType" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>        	
					<html:option value="<%=Config.PDF %>">Acrobat Reader</html:option>
					<html:option value="<%=Config.RTF %>">HTML</html:option>
        		</html:select>
        	
        	</td>
    </tr>    	
	</table>        
</his:ContentTag>

<input type="hidden"  name="reportMode">
<input type="hidden"  name="mode" value="DEPARTMENTWISEOUTPATIENTSREPORT">
<html:hidden name="departmentWiseOutPatientReportFB" property="jrxmlPath" value="<%=RegistrationConfig.REGISTRATION_JRXML_PATH_REPORT1 %>"/>
<html:hidden name="departmentWiseOutPatientReportFB" property="jrxmlName"/>
<html:hidden name="departmentWiseOutPatientReportFB" property="sysDate" value="<%=tDate%>"/>
<html:hidden name="departmentWiseOutPatientReportFB" property="combo" />
</body>
<%
}catch(Exception e){e.printStackTrace();}
%>


      
         		
