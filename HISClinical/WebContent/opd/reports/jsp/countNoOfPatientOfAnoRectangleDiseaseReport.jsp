<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import ="java.util.*,registration.*,hisglobal.presentation.*" %>
<%@page import="hisglobal.hisconfig.Config, opd.*"%>
<%@page import="opd.OpdConfig"%>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 
<his:javascript src="/registration/js/dateFunctions.js"/> 
<his:javascript  src="/registration/js/reportsValidation.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/commonDesigner.js" />
<!-- <script language="JavaScript" src="/HISClinical/hisglobal/utility/generictemplate/js/commonDesigner.js"></script> -->
<script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>


function submitPage(mode)
{
	//alert("inside submit page")
	elmt=document.getElementsByName("reportMode")[0];  
    elmt.value=mode;
    //alert(mode);
    document.forms[0].submit();
}

function countNoOfPatientOfAnoRectangleDiseaseReportHandler()
{
	success=false;
	
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
	        if(validateGraphForAgeWiseReport()){
	        		success=true;	
	        }
	    }//end of selection is for graph based report	 
    }
	return success;
} 

function setToYear(){
	document.getElementsByName('strToYear')[0].value=parseInt(document.getElementsByName('strFromYear')[0].value)+1+"";
}

</script>

<his:TitleTag name="Count Of No Of Patients Of AnoRectangle Disease Report">
</his:TitleTag>

		
	   <bean:define name="countNoOfPatientOfAnoRectangleDiseaseReportFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="countNoOfPatientOfAnoRectangleDiseaseReportFB" property="toDate" id="tDate" type="java.lang.String"/>          
	   

<his:SubTitleTag name="Report Details">
</his:SubTitleTag>
      
 <his:ContentTag>	
	<table width="100% cellspacing="1" cellpadding="0">
		
		
		<tr>
 			<td class="tdfonthead" width="25%">
        		
        		<div id='divfrom' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="from"/><b>	<bean:message key="colon"/></b>
					</font>
         		</div>
         	</td>
         
         	<td class="tdfont" width="25%">
         		<div id='divFromYearControl'>April
					<html:select name="countNoOfPatientOfAnoRectangleDiseaseReportFB" property="strFromYear" tabindex="1" onchange="setToYear()">
						<logic:present name="<%=OpdConfig.REPORTS_LIST_OF_MIN_YEARS%>">
							<html:options collection="<%=OpdConfig.REPORTS_LIST_OF_MIN_YEARS%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</div>
			</td>
			
				
			<td class="tdfonthead" width="25%">
    			
    			<div id='divto' align="right">
        			<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
        			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="to"/><b>	<bean:message key="colon"/></b>
					</font>
         		</div>
			</td>
		
			<td class="tdfont" width="25%">
		 		<div id='divTo' align="left">    
					Mar <html:text name="countNoOfPatientOfAnoRectangleDiseaseReportFB" tabindex="1" property="strToYear" size="5" readonly="true" />
    	 		</div>
    	  		
			</td>                 			
 		</tr>
 		
 		<tr>
 			
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
					<html:select name="countNoOfPatientOfAnoRectangleDiseaseReportFB" property="multipleHospitalCode" tabindex="1"   multiple="true" size="5" style="width: 150px;">
						<html:option value="0">All</html:option>
						<logic:notEmpty name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>">
						<logic:present name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>">
							<html:options collection="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>"
								property="value" labelProperty="label" />
						</logic:present>
						</logic:notEmpty>
					</html:select></div>
					
			</td>
			<td width="25%" class="tdfonthead" colspan="2"></td>
				
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
	        	<html:select name="countNoOfPatientOfAnoRectangleDiseaseReportFB"  property="graphOrText" tabindex="1" styleClass="regcbo" >
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
        		<html:select name="countNoOfPatientOfAnoRectangleDiseaseReportFB" property="reportType" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>        	
					<html:option value="<%=Config.PDF %>">Acrobat Reader</html:option>
					<html:option value="<%=Config.RTF %>">HTML</html:option>
        		</html:select>
        	
        	</td>
    	</tr>    	
	</table>        
</his:ContentTag>
<his:status/>
<input type="hidden"  name="reportMode">
<input type="hidden"  name="mode" value="COUNTNOOFPATIENTOFANORECTANGLEDISEASEREPORT">
<html:hidden name="countNoOfPatientOfAnoRectangleDiseaseReportFB" property="jrxmlPath" value="<%=OpdConfig.OPD_JRXML_PATH%>"/>
<html:hidden name="countNoOfPatientOfAnoRectangleDiseaseReportFB" property="jrxmlName" value="<%=OpdConfig.COUNT_NO_OF_PATIENTS_OF_DISEASE_IN_FINANCIAL_YEAR%>"/>
<html:hidden name="countNoOfPatientOfAnoRectangleDiseaseReportFB" property="sysDate" value="<%=tDate%>"/>
<input type="hidden"  name="fromHr">
<input type="hidden"  name="toHr">
<input type="hidden"  name="toMin">
<input type="hidden"  name="fromMin">


