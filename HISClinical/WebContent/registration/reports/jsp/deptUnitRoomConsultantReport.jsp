<%try{ %>
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

function submitPage(mode)
{
	elmt=document.getElementsByName("reportMode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function showUnit()
{
	val=document.getElementsByName("departmentCode")[0].value;
	if(val!="%")
	{
		submitPage('UNIT')
	}
	else
	{	
		submitPage('NEW')

	}
}

function deptUnitRoomConsultantTimingReportHandler()
{
	
	
	document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.DEPT_UNIT_ROOM_CONSULTANT_ROSTER_REPORT%>";
	success=false;
	//if(document.getElementsByName('departmentCode')[0].value==-1)
	//{
	//	alert("Please select the Department");
	//	document.getElementsByName('departmentCode')[0].focus();
	//	return false;
	//}
	if(validateGraphOrText())
    { 
		if(document.getElementsByName('graphOrText')[0].selectedIndex==1)
		{
	    	document.getElementsByName('reportMode')[0].value="<%=Config.TEXT%>";
	        if(validateTextual())
	        {      
	        	//if(validateTodayOrDate())
	        	//{
	           		return true;           	   
	           //	}          	  
            }
        }//end of selection is for text based report	
        if(document.getElementsByName('graphOrText')[0].selectedIndex==2)
		{
	    	document.getElementsByName('reportMode')[0].value="<%=Config.GRAPH%>";
	        if(validateGraphForAgeWiseReport())
	        {
		    	//if(validateTodayOrDate())        
		        success=true;	                
	        }
        }//end of selection is for graph based report	 
    }
	return success;
}//end of function deptUnitWiseTotalPatientReportHandler()

</script>

<his:TitleTag name="Department Unit Room,Consultant Roster Report">
</his:TitleTag>

         <%
     		  String deptlabel="" ;
        	  String deptcontrol="" ;
              String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
              String fromLabel="" ;
              String toLabel="" ;
              String fromControl="" ;
              String toControl="" ; 
         %>

<logic:equal name="deptUnitRoomConsultantReportFB" property="choice" value="<%=Config.CHOICE_TODAY%>">
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
<logic:equal name="deptUnitRoomConsultantReportFB" property="choice" value="<%=Config.CHOICE_DATE_WISE%>">
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
      
<his:SubTitleTag name="Report Details">
</his:SubTitleTag>    	

<his:ContentTag>
	<table width="100% cellspacing="1" cellpadding="0">
		<tr>
 			<td width="25%" class="tdfonthead">
      			<div id ="deptLabel"  align="right"  style='<%=deptlabel%>'>
		        	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="department"/>
				  </font>
      			</div>
    		</td>
    		<td width="25%" class="tdfont">
       			<div id="deptControl" align="left"  style='<%=deptcontrol%>'>
     		 		<html:select name="deptUnitRoomConsultantReportFB"  property="departmentCode"  tabindex="1"  styleClass ="regcbo" onchange="if(this.value!='-1')showUnit()">   
					  <html:option value="-1">Select value</html:option>
					  <html:options collection ="<%=RegistrationConfig.CLINICAL_DEPT_LIST%>" property = "value" labelProperty = "label"/>
			  		</html:select> 
	   			</div>       
    		</td>

<his:statusTransactionInProcess>
    		<td width="25%" class="tdfonthead">
    			<div id="unitLabel" align="right" >
    				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="unit"/>
				  </font>
    			</div>
    		</td>
    		<td width="25%" class="tdfont">
    			<div id="unitControl" align="left" >
    				<html:select name="deptUnitRoomConsultantReportFB"  property="unit"  tabindex="1"  styleClass ="regcbo" >
						<html:option value="%">All Unit</html:option>
						<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>">
						  <html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>" property = "value" labelProperty = "label"/>
						</logic:present>  
					</html:select>
				</div>			  
    		</td>
</his:statusTransactionInProcess>	
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
	        	<html:select name="deptUnitRoomConsultantReportFB"  property="graphOrText" tabindex="1" styleClass="regcbo" >
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
        		<html:select name="deptUnitRoomConsultantReportFB" property="reportType" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>        	
					<html:option value="<%=Config.PDF %>">Acrobat Reader</html:option>
					<html:option value="<%=Config.RTF %>">HTML</html:option>
        		</html:select>
        	</td>
    	</tr>    	
	</table>        
</his:ContentTag>

<input type="hidden"  name="reportMode">
<input type="hidden"  name="mode" value="DEPTUNITROOMCONSULTANTTIMINGREPORT">
<html:hidden name="deptUnitRoomConsultantReportFB" property="jrxmlPath" value="<%=RegistrationConfig.REGISTRATION_JRXML_PATH %>"/>
<html:hidden name="deptUnitRoomConsultantReportFB" property="jrxmlName"/>


<%}
catch(Exception e){
	e.printStackTrace();
	}%>