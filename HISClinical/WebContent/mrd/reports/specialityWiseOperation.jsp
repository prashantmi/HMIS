
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
      
function showDeptWise()
{

if(document.getElementsByName('option')[0].checked)
{
document.getElementById("deptLabelDeptWise").style.display="block";
document.getElementById("deptControlDeptWise").style.display="block";
document.getElementById("opTypeLabelDeptWise").style.display="block";
document.getElementById("opTypeControlDeptWise").style.display="block";
document.getElementById("opTypeControlUnitWise").style.display="none";
document.getElementById("unitLabelUnitWise").style.display="none";
document.getElementById("unitControlUnitWise").style.display="none";
document.getElementById("specControlUnitWise").style.display="none";
document.getElementById("specLabelUnitWise").style.display="none";
}
if(document.getElementsByName('option')[1].checked)
{
document.getElementById("deptLabelDeptWise").style.display="none";
document.getElementById("deptControlDeptWise").style.display="none";
document.getElementById("opTypeLabelDeptWise").style.display="none";
document.getElementById("opTypeControlDeptWise").style.display="none";
document.getElementById("unitLabelUnitWise").style.display="block";
document.getElementById("unitControlUnitWise").style.display="block";
document.getElementById("specControlUnitWise").style.display="block";
document.getElementById("specLabelUnitWise").style.display="block";
document.getElementById("opTypeControlUnitWise").style.display="block";
}
}


function submitPage(mode)
{
	
	elmt=document.getElementsByName("reportMode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function showUnit()
{
document.getElementById("deptLabelDeptWise").style.display="none";
document.getElementById("deptControlDeptWise").style.display="none";
document.getElementById("opTypeControlUnitWise").style.display="block";
document.getElementById("opTypeLabelDeptWise").style.display="none";
document.getElementById("opTypeControlDeptWise").style.display="none";
document.getElementById("unitLabelUnitWise").style.display="block";
document.getElementById("unitControlUnitWise").style.display="block";
document.getElementById("specControlUnitWise").style.display="block";
document.getElementById("specLabelUnitWise").style.display="block";
}


function SpecialityWiseOperationReportHandler()
{
	if(document.getElementsByName('operationType')[0].value=='%')
		document.getElementsByName('jrxmlName')[0].value="<%=MrdConfig.SPECIALITY_WISE_OPERATION_REPORT%>";
	else
		document.getElementsByName('jrxmlName')[0].value="<%=MrdConfig.SPECIFIC_SPECIALITY_WISE_OPERATION_REPORT%>";
		
	if(document.getElementsByName('operationTypeForDept')[0].value!='%')
		document.getElementsByName('jrxmlName')[0].value="<%=MrdConfig.SPECIFIC_SPECIALITY_WISE_OPERATION_REPORT%>";
	
	success=false;
	if(document.getElementsByName('option')[0].checked)
	{
	if(document.getElementsByName('deptCode')[0].value==-1)
	{
		alert("Please select the Department");
		document.getElementsByName('deptCode')[0].focus();
		return false;
	}
	if(document.getElementsByName('speciality')[0].value==-1)
	{
		alert("Please select the Speciality Type");
		document.getElementsByName('speciality')[0].focus();
		return false;
	}
	}
	if(document.getElementsByName('option')[1].checked)
	{
	
	if(document.getElementsByName('unit')[0].value==-1)
	{
		alert("Please select the Unit");
		document.getElementsByName('unit')[0].focus();
		return false;
	}
	if(document.getElementsByName('speciality')[0].value==-1)
	{
		alert("Please select the Speciality Type");
		document.getElementsByName('speciality')[0].focus();
		return false;
	}
	}
	
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
    
    function deptSelected(cboSpec)
{
	var specId=cboSpec.value;
	var comboVar=document.forms[0].combo.value.split("#");
	var newCombo;
		
	newCombo= "<option value='%' > All Units </option> ";
	
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

<his:TitleTag name="Speciality Wise Operations Between Two Dates">
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

      <bean:define name="SpecialityWiseOperationFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="SpecialityWiseOperationFB" property="toDate" id="tDate" type="java.lang.String"/>          
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
 			<td class="tdfonthead"  width="25%">       	      
		    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      		<bean:message key="deptWise"/>
		      	</font></td>
		      	<td class="tdfont" nowrap width="25%">
		      		<html:radio  name="SpecialityWiseOperationFB" property="option" tabindex="1"  value="<%=MrdConfig.DEPT_WISE%>"  onclick="showDeptWise()" />
		      		</td>
		      		<td class="tdfonthead"  width="25%">
		        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       		<bean:message key="unitWise"/>
		       	</font> </td>
		       	<td class="tdfont" nowrap width="25%">
		        	<html:radio name="SpecialityWiseOperationFB" property="option" tabindex="1" value="<%=MrdConfig.UNIT_WISE%>" onclick="showUnit()"/>
			</td> 
			 
 		</tr>
 		
	<tr>	
	
 		<td class="tdfonthead" nowrap width="25%" >  
 		<div id="deptLabelDeptWise" style="display:none;">
      			      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="department"/>
				  </font>
      			</div>
 		
 		<div id="specLabelUnitWise" style="display:none;">
      			      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="specialityType"/>
				  </font>
      			</div>
 		
 			</td>
      	<td class="tdfont"  width="25%" >
      	<div id="deptControlDeptWise" style="display:none;">
     		 		<html:select name="SpecialityWiseOperationFB"  property="deptCode"  tabindex="1"  styleClass="regcbo" >   
				
					  	<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT%>">
					  <html:option value="%">All Departments</html:option>
					  <html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT%>" property = "value" labelProperty = "label"/>
					  </logic:present>
			  		</html:select> 
		</div>
			  	
    	<div id="specControlUnitWise" style="display:none;">
    	 	<html:select name="SpecialityWiseOperationFB"  property="speciality"  tabindex="1" onchange="deptSelected(this);" onkeypress="if(event.keyCOde==13)deptSelected(this);"  styleClass="regcbo" >   
			
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
    		<div id="opTypeLabelDeptWise" style="display:none;">
 		      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="operationType"/>
				  </font>
    	</div>
    			</td>
      			<td class="tdfont"  width="25%">
       			<div id="unitControlUnitWise" style="display:none;">
    			<html:select name="SpecialityWiseOperationFB"  property="unit"  tabindex="1"  styleClass ="regcbo" >
    			
				<html:option value="%">All Units</html:option>
				<logic:present name="<%=MrdConfig.UNITS_BASED_ON_SPECIALITY%>">
				  <html:options collection ="<%=MrdConfig.UNITS_BASED_ON_SPECIALITY%>" property = "value" labelProperty = "label"/>
				</logic:present>  
				</html:select>
			  	</div>
			  	<div id="opTypeControlDeptWise" style="display:none;">
    			<html:select name="SpecialityWiseOperationFB"  property="operationTypeForDept"  tabindex="1"  styleClass ="regcbo" >
				<html:option value="%">All </html:option>
				<html:option value="10">Major</html:option>
				<html:option value="11">Minor</html:option>
				</html:select>
			  	</div>
			  		 
	   		</td>
	   	 </tr>
	   	 </table>
	   	 <div id="opTypeControlUnitWise" style="display:none;">
	   	 <table width="100%" cellspacing="1" cellpadding="0">
	 
	   	<tr>
	   			  	
	   			<td class="tdfonthead" nowrap width="25%" >
	   	
	   			<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="operationType"/>
				  </font>
	   			</td>
	   			<td class="tdfont" nowrap width="25%" >
	   			<html:select name="SpecialityWiseOperationFB"  property="operationType"  tabindex="1"  styleClass ="regcbo" >
				<html:option value="%">All </html:option>
				<html:option value="10">Major</html:option>
				<html:option value="11">Minor</html:option>
				</html:select>
	   			</td>
	   			<td class="tdfonthead" nowrap width="25%" >
	   			</td>
	   			<td class="tdfont" nowrap width="25%" >
	   			</td>
	   		
	   	</tr>
	   	
	</table> 
</div>


 <his:SubTitleTag name="Report Generation Options">
</his:SubTitleTag> 
		<table width="100%" cellspacing="1" cellpadding="0">
    <tr>   
		<td width="25%" class="tdfonthead">
        	<font color="#FF0000">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          	<bean:message key="reportType"/>      
	        </font>
        </td>                
        <td width="25%" class="tdfont" > 
	        <html:select name="SpecialityWiseOperationFB"  property="graphOrText" tabindex="1" styleClass="regcbo" >
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
        		<html:select name="SpecialityWiseOperationFB" property="reportType" tabindex="1" styleClass="regcbo">
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

<input type="hidden"  name="mode" value="SPECIALITYWISEOPERATION">
<html:hidden name="SpecialityWiseOperationFB" property="jrxmlPath" value="<%=MrdConfig.MRD_JRXML_PATH%>"/>
<html:hidden name="SpecialityWiseOperationFB" property="jrxmlName"/>
<html:hidden name="SpecialityWiseOperationFB" property="sysDate" value="<%=tDate%>"/>
<html:hidden name="SpecialityWiseOperationFB" property="combo" />
