
<%
try {
%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ page import="java.util.*,registration.*,hisglobal.presentation.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
	
function submitPage(mode){
	elmt=document.getElementsByName("reportMode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function getUnitForDepartment(obj){
	document.getElementsByName("reportMode")[0].value='GETUNIT'
	document.forms[0].submit();
}


function validateReportForm()
{
	// Validate dates
	//if(document.getElementsByName('choice')[1].checked)
	{
		var dtCurrent = convertStrToDate(document.getElementsByName("sysDate")[0].value,'dd-Mon-yyyy');
		var dtFrom = convertStrToDate(document.getElementsByName("fromDate")[0].value,'dd-Mon-yyyy');
		var dtTo = convertStrToDate(document.getElementsByName("toDate")[0].value,'dd-Mon-yyyy');
		if(dtFrom>dtCurrent)
		{
			alert("From Date should less than or equals to Current Date");
			document.getElementsByName("fromDate")[0].focus();
			return false;
		}
		if(dtTo>dtCurrent)
		{
			alert("To Date should less than or equals to Current Date");
			document.getElementsByName("toDate")[0].focus();
			return false;
		}
		if(dtTo<dtFrom)
		{
			alert("To Date should greater than or equals to From Date");
			document.getElementsByName("toDate")[0].focus();
			return false;
		}
	}
	/*else
	{
		if(document.getElementsByName("fromHour")[0].value=="")
		{
			alert("Enter From Hour");
			document.getElementsByName("fromHour")[0].focus();
			return false;
		}
		if(document.getElementsByName("fromMin")[0].value=="")
		{
			alert("Enter From Minute");
			document.getElementsByName("fromMin")[0].focus();
			return false;
		}
		if(document.getElementsByName("toHour")[0].value=="")
		{
			alert("Enter To Hour");
			document.getElementsByName("toHour")[0].focus();
			return false;
		}
		if(document.getElementsByName("toMin")[0].value=="")
		{
			alert("Enter To Minute");
			document.getElementsByName("toMin")[0].focus();
			return false;
		}
	}*/
	return true;
} 

function patientListingReportHandler()
{
	if(!validateReportForm()) return false;
	
    if(validateGraphOrText())
    { 
		if(document.getElementsByName('graphOrText')[0].selectedIndex==1)
		{
	    	document.getElementsByName('reportMode')[0].value="<%=Config.TEXT%>";
	        if(!validateTextual())
	        {      
	        	//if(!validateTodayOrDate())
	        		return false;
            }
        }//end of selection is for text based report	   		
		if(document.getElementsByName('graphOrText')[0].selectedIndex==2)
		{
	    	document.getElementsByName('reportMode')[0].value="<%=Config.GRAPH%>";
	        if(!validateGraphForAgeWiseReport())
	        {
		    	//if(validateTodayOrDate())        
		        return false;	                
	        }
        }//end of selection is for graph based report	  
    }
    else
    	return false;


  if(document.forms[0].multipleHospitalCode.options[document.forms[0].multipleHospitalCode.selectedIndex].value == "%"){
				document.forms[0].multipleHospitalCode.options[document.forms[0].multipleHospitalCode.selectedIndex].selected=false;
			for(var i=0;i<document.getElementsByName("multipleHospitalCode")[0].options.length;i++){
					if(document.getElementsByName("multipleHospitalCode")[0].options[i].value!='%')
			   				document.getElementsByName("multipleHospitalCode")[0].options[i].selected = true;
			   		}
			   		}

	return true;
} 
		
function checkIsConsolidate()
{
//alert(document.getElementsByName('isConsolidate')[0].checked)
 /*
 if(document.getElementsByName('isSupervisor')[0].value==1)
 {
	if(document.getElementsByName('isConsolidate')[0].checked==true)
	{
		document.getElementsByName('patientType')[0].disabled=true
		document.getElementsByName('userCode')[0].disabled=true
		document.getElementsByName('patientType')[0].value='%'
		document.getElementsByName('userCode')[0].value='%'
		document.getElementsByName('orderBy')[1].disabled=true
		document.getElementsByName('orderBy')[2].disabled=true
		
	}
	if(document.getElementsByName('isConsolidate')[0].checked==false)
	{
		document.getElementsByName('patientType')[0].disabled=false
		document.getElementsByName('userCode')[0].disabled=false
		document.getElementsByName('orderBy')[1].disabled=false
		document.getElementsByName('orderBy')[2].disabled=false
		
	}
	}
	
	*/
}
function showdivtoday()
{	
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
           
function showdivdatewise()
{
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


function showDivRegCat()
{	
	document.getElementById("divRegCatLabel").style.display="";
	document.getElementById("divRegCatCombo").style.display="";
	
	document.getElementById("divPatCatLabel").style.display="none";
	document.getElementById("divPatCatCombo").style.display="none";
} 
           
function showDivPatCat()
{
	document.getElementById("divRegCatLabel").style.display="none";
	document.getElementById("divRegCatCombo").style.display="none";
	
	document.getElementById("divPatCatLabel").style.display="";
	document.getElementById("divPatCatCombo").style.display="";
}

</script>

<body onload="checkIsConsolidate()" >

<his:TitleTag name="Patient Listing Report">
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
           
<logic:equal name="PatientListingReportFB" property="choice" value="<%=Config.CHOICE_TODAY%>">
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

<logic:equal name="PatientListingReportFB" property="choice" value="<%=Config.CHOICE_DATE_WISE%>">
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

<bean:define name="PatientListingReportFB" property="fromDate" id="frDate" type="java.lang.String"/>
<bean:define name="PatientListingReportFB" property="toDate" id="tDate" type="java.lang.String"/>          
<%
	String sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"); 
	if(frDate==null||frDate.equalsIgnoreCase(""))
		frDate = sysDate;
	if(tDate==null||tDate.equalsIgnoreCase(""))
		tDate = sysDate;
%>  

<his:SubTitleTag name="Report Details"> 
<%--<logic:equal name="PatientListingReportFB" property="isSupervisor" value="1">--%>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
	 		<td width="90%">
	 	 		<div align="right">
	 	 			<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	      				<bean:message key="isConsolidate"/>      
	        		</font>
	        	</div>
	 	 	</td>
			<td>
				<div align="left">
	        		<html:checkbox name="PatientListingReportFB" property="isConsolidate" value="1" onchange="checkIsConsolidate()"></html:checkbox> 
				</div>
	  		</td>
 	 	</tr>
	</table>
<%--	</logic:equal>--%>
</his:SubTitleTag>	

<his:ContentTag>	
	<table width="100% cellspacing="1" cellpadding="0">
		<tr>
	      	<td width="25%" class="tdfonthead">
				<div align="right">
				<font color="#FF0000">*</font>
				<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="hospital" /> </font>
				</div>	
			</td>
			<td class="tdfont" width="25%">
				<div >
				<html:select name="PatientListingReportFB" property="multipleHospitalCode" tabindex="1"   multiple="true" size="5" >
					<html:option value="%">All</html:option>
					<logic:present name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>">
						<html:options collection="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>"
							property="value" labelProperty="label" />
					</logic:present>
				</html:select></div>
			</td>	
			<td class="tdfonthead" width="25%"></td>
			<td class="tdfont" width="25%"></td>
		</tr>
		
		<!-- <tr>            
			<td class="tdfonthead" nowrap width="100%" colspan="4">
				<div align="left">	      
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="today"/>
					</font>
					<html:radio name="PatientListingReportFB" property="choice" tabindex="1" value="<%=Config.CHOICE_TODAY%>" onclick="showdivtoday()"/> 
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="dateWise"/>
					</font> 
					<html:radio name="PatientListingReportFB" property="choice" tabindex="1" value="<%=Config.CHOICE_DATE_WISE%>" onclick="showdivdatewise()"/>
				</div>
			</td> 
		</tr> -->
		
		<tr>
			<td class="tdfonthead" width="25%">
		       <div id='divfromDate' style='<%=fromDateLabel %>' align="right">
		       		<html:hidden name="PatientListingReportFB" property="choice"/>
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
				<span id='divfromhrcontrol' style='<%=fromControl %>' align="left">	            
					<html:text name="PatientListingReportFB" tabindex="1" property="fromHour"  maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>	
					<b><bean:message key="colon"/></b>
				</span>
				<span id='divfromMinControl' style='<%=fromControl %>' align="left">         
					<html:text name="PatientListingReportFB" tabindex="1" property="fromMin"   maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>				
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
					<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
				</div>
				<span id='divtohrcontrol' style='<%=toControl%>' align="left">	            
				<html:text name="PatientListingReportFB" tabindex="1" property="toHour"  maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>	
				<b><bean:message key="colon"/>	  </b>
				</span>
				<span id='divtoMinControl' style='<%=toControl%>' align="left">         
				<html:text name="PatientListingReportFB" tabindex="1" property="toMin" maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>				
				<bean:message key="timeFormat"/>	  
				</span>
			</td>                 			
		</tr>
		<%--<tr>            
			<td class="tdfonthead" nowrap width="25%">
				<div align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="category" />
					</font>
				</div>
			</td>
			<td class="tdfonthead" nowrap width="75%" colspan="3">
				<div align="left">	      
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<html:radio name="PatientListingReportFB" property="catChoice" tabindex="1" value="0" onclick="showDivRegCat()"/><bean:message key="regCat"/>
					&nbsp;&nbsp;
					<html:radio name="PatientListingReportFB" property="catChoice" tabindex="1" value="1" onclick="showDivPatCat()"/><bean:message key="primCat"/>
					</font>
				</div>
			</td> 
		</tr>--%>
		<html:hidden name="PatientListingReportFB" property="catChoice"/>
		<tr>
			<td class="tdfonthead" width="25%">
				<div id='divPatCatLabel' style='' align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="primCat"/>
					</font>
		        </div>
			</td>
			<td class="tdfont" width="25%">
			    <div id='divPatCatCombo' style='' align="left">	               		 
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<html:select name="PatientListingReportFB"  property="patPrimaryCatCode"  tabindex="1"  styleClass ="regcbo">   
							<html:option value="%">All</html:option>
							<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY%>">
								<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY%>" property = "value" labelProperty = "label"/>
							</logic:present>
						</html:select>
					</font>
				</div>
			</td>
			<td class="tdfonthead" width="25%">
		       <div id='divRegCatLabel' align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="regCat"/>
					</font>
				</div>
			</td>
			<td class="tdfont" width="25%">
			    <div id='divRegCatCombo' align="left">	               		 
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<html:select name="PatientListingReportFB"  property="patRegCatCode"  tabindex="1"  styleClass ="regcbo">   
							<html:option value="%">All</html:option>
							<html:option value="<%=RegistrationConfig.EPISODE_VISIT_TYPE_OPD%>">Normal</html:option>
							<html:option value="<%=RegistrationConfig.EPISODE_VISIT_TYPE_EMG%>">Emergency</html:option>
						</html:select>
					</font>
				</div>
			</td>
		</tr>
		<tr>
			<td class="tdfonthead" nowrap="nowrap" width="25%">
				<div align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="department" />
					</font>
				</div>
			</td>
			<td class="tdfont" width="25%">
				<html:select
					name="PatientListingReportFB" property="departmentCode"
					tabindex="1" styleClass="regcbo" onchange="getUnitForDepartment(this)">
					<logic:present
						name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>">
						<html:option value="%">All</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>"
							property="value" labelProperty="label" />
					</logic:present>
				</html:select>
			</td>
			<td class="tdfonthead" nowrap="nowrap" width="25%" >  
				<div align="right">
      			      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="unit"/>
				  </font>
				</div>
			</td>
      		<td class="tdfont"  width="25%" >
				<html:select name="PatientListingReportFB"  property="unitCode"  tabindex="1"  styleClass="regcbo">   
			  		<html:option value="%">All</html:option>
			  		<logic:present
					name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>">
					<html:options
						collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>"
						property="value" labelProperty="label" />
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
				<html:select name="PatientListingReportFB"  property="patientType"  tabindex="1"  styleClass="regcbo">   
				 		<html:option value="%">All</html:option>
				  		<html:option value="0">New</html:option>
				  		<html:option value="1">Revisit</html:option>
				  		<%-- <html:option value="2">Old Registered</html:option> --%>
				</html:select> 
			</td>
		 	<%--<logic:equal name="PatientListingReportFB" property="isSupervisor" value="1">--%>
			<td class="tdfonthead" nowrap width="25%">
				<div align="right"><font
					color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif\">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="user" /> </font></div>
				</td>
				<td class="tdfont" width="25%">
				<div >
				<html:select
					name="PatientListingReportFB" property="userCode" tabindex="1"
					styleClass="regcbo">
					<html:option value="%">All</html:option>
					<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_USER%>">
						<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_USER%>"
							property="value" labelProperty="label" />
					</logic:present>
				</html:select></div>
			
			</td>
	      <%--</logic:equal>
 		<logic:notEqual name="PatientListingReportFB" property="isSupervisor" value="1">
 		<td class="tdfonthead" nowrap width="25%">
 		</td>
 		<td class="tdfont" nowrap width="25%"></td>
 		</logic:notEqual>--%>
			
      </tr>
      <tr>
			<td width="25%" class="tdfonthead">
				<div align="right">
				<font color="#FF0000">*</font>
				<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="registration" /> </font>
				</div>	
			</td>
			<td width="25%" class="tdfont" colspan="3">
				<div align="left">
					<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> 
					<html:radio name="PatientListingReportFB" property="selectField" value="0"></html:radio>
					Registration Date-Wise
					<html:radio name="PatientListingReportFB" property="selectField" value="1"></html:radio>
					Registration Expiry Date-Wise
					</font>
				</div>
			</td>
			
		</tr>		
      	<tr>
			<td width="25%" class="tdfonthead">
				<div align="right">
				<font color="#FF0000">*</font>
				<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="orderBy" /> </font>
				</div>	
			</td>
			<td width="25%" class="tdfont" colspan="3">
				<div align="left">
				<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> 
					<html:radio name="PatientListingReportFB" property="orderBy" value="0"></html:radio>
					Date &nbsp;&nbsp;
					<html:radio name="PatientListingReportFB" property="orderBy" value="1"></html:radio>
					Name &nbsp;&nbsp;
					<html:radio name="PatientListingReportFB" property="orderBy" value="2"></html:radio>
					CR No.
				</font>
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
			<td width="25%" class="tdfonthead"><font color="#FF0000">*</font>
			<font color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
				key="reportType" /> </font></td>
			<td width="25%" class="tdfont"><html:select
				name="PatientListingReportFB" property="graphOrText"
				tabindex="1" styleClass="regcbo">
				<html:option value="-1">Select Value</html:option>
				<html:option value="<%=Config.TEXT%>">Textual</html:option>

			</html:select></td>
			<td width="25%" class="tdfonthead"><font color="#FF0000">*</font>
			<font color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
				key="pdfOrWord" /> </font></td>
			<td width="25%" class="tdfont"><html:select
				name="PatientListingReportFB" property="reportType"
				tabindex="1" styleClass="regcbo">
				<html:option value="-1">Select Value</html:option>
				<html:option value="<%=Config.PDF %>">Acrobat Reader</html:option>
				<html:option value="<%=Config.RTF %>">HTML</html:option>
			</html:select></td>
		</tr>
	</table>
</his:ContentTag>

<input type="hidden" name="reportMode">
<input type="hidden" name="choice" >
<input type="hidden" name="choice" checked="checked">
<input type="hidden" name="mode" value="PATIENTLISTINGREPORT">
<html:hidden name="PatientListingReportFB" property="isSupervisor"/>
<html:hidden name="PatientListingReportFB" property="dynamicQuery"/>
<html:hidden name="PatientListingReportFB" property="patRegCatCode"/>

<html:hidden name="PatientListingReportFB" property="jrxmlPath" value="<%=RegistrationConfig.REGISTRATION_JRXML_PATH_REPORT1 %>" />
<html:hidden name="PatientListingReportFB" property="jrxmlName" />
<html:hidden name="PatientListingReportFB" property="sysDate" value="<%=tDate%>" />
<script type="text/javascript">

</script>
<%
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
%>
