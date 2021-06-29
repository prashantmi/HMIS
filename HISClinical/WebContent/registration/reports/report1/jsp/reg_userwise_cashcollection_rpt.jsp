<!--
	@author Pragya Sharma
	Creation Date: 16-Aug-2011
 -->
 <%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ page import ="java.util.*,registration.*,hisglobal.presentation.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 
<his:javascript src="/hisglobal/js/utilityFunctions.js"/> 
<his:javascript src="/registration/reports/report1/js/userWiseCashCollection.js"/> 

<script type="text/javascript">
<!--

//-->


function showView()
{
	if(document.forms[0].multipleHospitalCode.options[document.forms[0].multipleHospitalCode.selectedIndex].value == "%"){
				document.forms[0].multipleHospitalCode.options[document.forms[0].multipleHospitalCode.selectedIndex].selected=false;
			for(var i=0;i<document.getElementsByName("multipleHospitalCode")[0].options.length;i++){
					if(document.getElementsByName("multipleHospitalCode")[0].options[i].value!="%")
			   				document.getElementsByName("multipleHospitalCode")[0].options[i].selected = true;
			   			//	alert(document.getElementsByName("multipleHospitalCode")[0].options[i].value);
			   		}
			   		}
			   		
			   		showReport();
}
</script>
<his:TitleTag name="User Wise Cash Collection Report">
</his:TitleTag>

<%
	String pdfrtflabel="" ;
	String pdfrtfcontrol="" ;
	String fromDateLabel="" ;
	String toDateLabel="" ;
	String fromDateControl="" ;
	String toDateControl="" ;
	String fromLabel="" ;
	String toLabel="" ;
	String fromControl="" ;
	String toControl="" ;  
%>		
           
<logic:equal name="UserWiseCashCollectionFB" property="choice" value="<%=Config.CHOICE_TODAY%>">
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

<logic:equal name="UserWiseCashCollectionFB" property="choice" value="<%=Config.CHOICE_DATE_WISE%>">
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
				
<bean:define name="UserWiseCashCollectionFB" property="fromDate" id="frDate" type="java.lang.String"/>
<bean:define name="UserWiseCashCollectionFB" property="toDate" id="tDate" type="java.lang.String"/>          
<%
	String sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"); 
	if(frDate==null||frDate.equalsIgnoreCase(""))
		frDate = sysDate;
	if(tDate==null||tDate.equalsIgnoreCase(""))
		tDate = sysDate;
%>  

<his:SubTitleTag name="Report Details"> </his:SubTitleTag>	

<his:ContentTag>	
	<table width="100% cellspacing="1" cellpadding="0">
		<tr>            
			<td class="tdfonthead" nowrap width="100%" colspan="4">
				<div align="left">	      
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="today"/>
					</font>
					<html:radio name="UserWiseCashCollectionFB" property="choice" tabindex="1" value="<%=Config.CHOICE_TODAY%>" onclick="showdivtoday()"/> 
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="dateWise"/>
					</font> 
					<html:radio name="UserWiseCashCollectionFB" property="choice" tabindex="1" value="<%=Config.CHOICE_DATE_WISE%>" onclick="showdivdatewise()"/>
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
					<html:text name="UserWiseCashCollectionFB" tabindex="1" property="fromHour"  maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>	
					<b><bean:message key="colon"/></b>
				</span>
				<span id='divfromMinControl' style='<%=fromControl %>' align="left">         
					<html:text name="UserWiseCashCollectionFB" tabindex="1" property="fromMin"   maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>				
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
				<html:text name="UserWiseCashCollectionFB" tabindex="1" property="toHour"  maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>	
				<b><bean:message key="colon"/>	  </b>
				</span>
				<span id='divtoMinControl' style='<%=toControl%>' align="left">         
				<html:text name="UserWiseCashCollectionFB" tabindex="1" property="toMin" maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>				
				<bean:message key="timeFormat"/>	  
				</span>
			</td>                 			
		</tr>

		<tr>
		 	<%--<logic:equal name="UserWiseCashCollectionFB" property="isSupervisor" value="1">--%>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#FF0000">*</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="usrName" />
						</font>
					</div>	
				</td>
				<td class="tdfont" width="25%">
					<div>
						<html:select name="UserWiseCashCollectionFB" property="userCode" tabindex="1" styleClass="regcbo" onchange="setUserName(this)">
							<html:option value="0">All</html:option>
							<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_USER%>">
								<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_USER%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
						<html:hidden name="UserWiseCashCollectionFB" property="userName" value="All"/>
					</div>
				</td>	
			<%--</logic:equal>
	 		<logic:notEqual name="UserWiseCashCollectionFB" property="isSupervisor" value="1">
				<td width="25%"  class="tdfonthead">
				</td>
				<td width="25%" class="tdfont">
				</td>
	 		</logic:notEqual>--%>
			<td width="25%" class="tdfonthead">
				<div align="right">
					<font color="#FF0000">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<%--<bean:message key="regCat"/>--%>
					</font>
				</div>
			</td>
			<td width="25%"  class = "tdfont">
				<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
				<html:hidden name="UserWiseCashCollectionFB"  property="patRegCatCode" value="0" />
					<%--<html:select name="UserWiseCashCollectionFB"  property="patRegCatCode"  tabindex="1"  styleClass ="regcbo">   
						<html:option value="0">All</html:option>
						<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY%>">
							<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY%>" property = "value" labelProperty = "label"/>
						</logic:present>
					</html:select>--%>
				</font>
			</td>
		</tr>
		<tr>
			<td width="25%" class="tdfonthead">
				<div align="right">
					<font color="#FF0000">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="hospital" />
					</font>
				</div>	
			</td>
			<td class="tdfont" width="25%">
				<div>
					<html:select name="UserWiseCashCollectionFB" property="multipleHospitalCode" tabindex="1" multiple="true" size="5">
					
					<!--
					<html:option value="%">All</html:option>
					
					-->
					
					<%if(((List)pageContext.findAttribute(RegistrationConfig.HOSPITAL_COMBO_LIST)).size()!=1){
					%>
					<html:option value="%">All</html:option>
				<%}%>
					
					
						<logic:present name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>">
							<html:options collection="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</div>
			</td>	
			<td width="25%"  class="tdfonthead">
			</td>
			<td width="25%" class="tdfont">
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
				<html:select name="UserWiseCashCollectionFB"  property="graphOrText" tabindex="1" styleClass="regcbo" onchange='showpdfrtfdiv()'>
					<html:option value="-1">Select Value</html:option>
					<html:option value="<%=Config.TEXT%>">Textual</html:option>
					<%-- <html:option value="<%=Config.GRAPH%>">Graphical</html:option>	    --%>
				</html:select> 		
			</td>
			<logic:equal name="UserWiseCashCollectionFB" property="graphOrText" value="<%=Config.TEXT%>">
				<%
					pdfrtflabel="" ;
					pdfrtfcontrol="" ;
				%>
			</logic:equal>
			<logic:equal name="UserWiseCashCollectionFB" property="graphOrText" value="<%=Config.GRAPH%>">
				<%
					pdfrtflabel="display:none" ;
					pdfrtfcontrol="display:none" ;
				%>
			</logic:equal>       
		
			<td width="25%" class="tdfonthead">
				
			</td>
		
			<td class="tdfont" nowrap="nowrap" width="25%">       
				
			</td> 
	
		</tr>
	</table>
</his:ContentTag> 

<his:ButtonToolBarTag>        
    <img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>' style='cursor:pointer' onkeypress="if(event.keyCode==13) showView();" tabindex="1" onclick = "showView();" />         
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style='cursor:pointer' tabindex="1" onclick ="cancelReport()" onkeypress="if(event.keyCode==13) cancelReport()"/>
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style='cursor:pointer' tabindex="1" onclick ="clearReport()" onkeypress="if(event.keyCode==13) clearReport();"/>
</his:ButtonToolBarTag>

<html:hidden name="UserWiseCashCollectionFB" property="reportMode"/>
<html:hidden name="UserWiseCashCollectionFB" property="sysDate" value="<%=sysDate%>"/>
<html:hidden name="UserWiseCashCollectionFB" property="choice"/>
<html:hidden name="UserWiseCashCollectionFB" property="isSupervisor"/>

<input type="hidden"  name="mode" value="USERWISECASHCOLLECTION">
