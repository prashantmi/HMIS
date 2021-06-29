<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import ="java.util.*,registration.*,hisglobal.presentation.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="inpatient.InpatientConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
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
<script>
      


function submitPage(mode)
{
	
	elmt=document.getElementsByName("reportMode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}


function RegisteredMlcPatientReportHandler()
{
	
    	document.getElementsByName('jrxmlName')[0].value="<%=InpatientConfig.REGISTERED_MLC_PATIENTS%>";
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
    
    function showDateWise()
    {
   		document.getElementById("trDateWise").style.display="";
   		document.getElementById("trWardWise").style.display="none";
    }
    function showWardWise()
    {
   		document.getElementById("trDateWise").style.display="none";
   		document.getElementById("trWardWise").style.display="";
    	document.getElementsByName("multipleHospitalCode")[0].value="0";
    	alert("multipleHospitalCode[0] :"+document.getElementsByName("multipleHospitalCode")[0].value);
    	alert("multipleHospitalCode[1] :"+document.getElementsByName("multipleHospitalCode")[1].value);
    }
    
    function showWard()
	{
		alert("inside showWard");
		var wardCode = document.getElementsByName("wardCode")[0].value;
		//document.getElementById("divWardControl")[0].innerHTML="";
		//PragyaDesigner.clearCombo(wardCode);
		//alert("Inside showWard()");
		var hospitalCode=document.getElementsByName("multipleHospitalCode")[0].value;
		if(hospitalCode=="-1"){
			alert("Please select Hospital");
			var str1='<html:select name="registeredMlcPatientsFB"  property="wardCode"  tabindex="1"  styleClass ="regcbo" ><html:option value="%">All</html:option></html:select>';
		  	document.getElementById("divWardControl").innerHTML=str1;
		  	
			return false;
		}
		
		var objWardList = showWardAjaxResponse(hospitalCode,departmentCode);
		str2='<html:select name="registeredMlcPatientsFB"  property="wardCode"  tabindex="1"  styleClass ="regcbo" ><html:option value="%">All</html:option>';   
		  	str2=str2+objWardList+"</html:select>";
		
		
		document.getElementById("divWardControl").innerHTML=str2;
	}
	
	/*************************************** AJAX Functions ***************************/
	
	//Gettting List of Ward based on Hospital & Dept 
	function showWardAjaxResponse(hospitalCode,departmentCode)
	{
		var flg = false;
		var objWardList = null;
		var reportMode = "GETALLWARD_AJAX";
		var objXHR = {url: "/HISClinical/inpatient/report/registeredMlcPatientReport.cnt?reportMode="+reportMode+"&hospitalCode="+hospitalCode, sync:true, postData: "", handleAs: "text",
			load: function(data) 
			{
				objWardList = data;
				flg = true;
			},
	   error: function(error)
	   {
		   objWardList = null;
	       flg = false;
	   }};

		var objDojoAjax = dojo.xhrPost(objXHR);
		//alert("wardList :"+objWardList);
		return objWardList;
	}

</script>

<his:TitleTag name="Registered Mlc Patients Between Two Dates">
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

      <bean:define name="registeredMlcPatientsFB" property="fromDate" id="frDate" type="java.lang.String"/>
	  <bean:define name="registeredMlcPatientsFB" property="toDate" id="tDate" type="java.lang.String"/>          
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
 			<td width="25%" class="tdfonthead" colspan="3">
				<div align="right">
				<html:radio name="registeredMlcPatientsFB" property="strDateOrWard" value="0" onclick="showDateWise();"></html:radio>
				<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="datewise" /> </font>
				</div>	
			</td>
			<td width="25%" class="tdfont">
				<div align="left">
				<html:radio name="registeredMlcPatientsFB" property="strDateOrWard" value="1" onclick="showWardWise();"></html:radio>
				<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="wardWiseOnly" /> </font>
				</div>	
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
 		
 		<tr id="trDateWise">
			<!-- New Change add Hospital Combo -->
		      <td width="25%" class="tdfonthead">
					<div align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="hospital" /> </font>
					</div>	
					</td>
					
				<td class="tdfont" width="25%">
					<div >
					<html:select name="registeredMlcPatientsFB" property="multipleHospitalCode" tabindex="1" multiple="true" size="4">
						<html:option value="0">All</html:option>
						<logic:present name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>">
							<html:options collection="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>"
								property="value" labelProperty="label" />
						</logic:present>
					</html:select></div>
					
				</td>
				<td class="tdfonthead" width="25%" colspan="2"></td>
		</tr>
		<tr id="trWardWise" style="display: none">
			<!-- New Change add Hospital Combo -->
		      <td width="25%" class="tdfonthead">
					<div align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="hospital" /> </font>
					</div>	
					</td>
					
				<td class="tdfont" width="25%">
					<div >
					<html:select name="registeredMlcPatientsFB" property="multipleHospitalCode" tabindex="1" onchange="showWard();">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>">
							<html:options collection="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>"
								property="value" labelProperty="label" />
						</logic:present>
					</html:select></div>
					
				</td>
				<td class="tdfonthead" width="25%">
	 				<div id="divPat" style="display: block;" align="right">
	 					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="ward"/>
			 			</font>
	 				</div>
	 			</td>
	 			<td class="tdfont" width="25%">
	 				<div id="divWardControl" style="display: block;" align="left">
	 					<html:select name="registeredMlcPatientsFB" property="wardCode" tabindex="1"  styleClass ="regcbo">
	 						<html:option value="%">All</html:option> 	
	 					</html:select>
	 				</div>
	 			</td>
		</tr>
 	
</table> 	

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
	        <html:select name="registeredMlcPatientsFB"  property="graphOrText" tabindex="1" styleClass="regcbo" >
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
        		<html:select name="registeredMlcPatientsFB" property="reportType" tabindex="1" styleClass="regcbo">
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

<input type="hidden"  name="mode" value="REGISTEREDMLCPATIENTREPORT">
<html:hidden name="registeredMlcPatientsFB" property="jrxmlPath" value="<%=InpatientConfig.INPATIENT_JRXML_PATH%>"/>
<html:hidden name="registeredMlcPatientsFB" property="jrxmlName"/>
<html:hidden name="registeredMlcPatientsFB" property="sysDate" value="<%=tDate%>"/>
<html:hidden name="registeredMlcPatientsFB" property="wardCode"/>

