<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ page import ="java.util.*,registration.*,hisglobal.vo.*" %>

<%@page import="hisglobal.hisconfig.Config"%>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%try{ %>
<script>
	function submit4SecCat(idx){
		code = document.getElementsByName("primaryCatCode")[idx].value;
		document.getElementsByName("codeToManipulate")[0].value = code;
		document.getElementsByName("secondaryCatCode")[idx].value = -1;
		submitTile("GETSECCAT");
	}
	
	function submitTile(mode){
		alert("entry")
		document.getElementsByName("hmode")[0].value = mode;
		document.forms[0].submit();
	}
	
	function callThisOnload()
	{
	focusCrNo();
	}
</script>
<%@ page import ="java.util.*,registration.*,hisglobal.vo.*, registration.controller.fb.changePatientCatFB.*, registration.controller.util.changePatientCatUTIL" %>
<%
String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>
	<his:TitleTag >
		<his:name>
			<bean:message key="titleChangePatientCategory"/>
		</his:name>
	    <!--<b>
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<a onclick="openPopup('registration/searchByNamePopup.cnt',event)" style=cursor:pointer>
		<bean:message key="search"/>
		<bean:message key="by"/>
		<bean:message key="name"/>
		</a>
		</font>
		</b>-->
	 
	    <b>
	    <font size="2" face="Verdana, Arial, Helvetica, sans-serif">
	    
		</font>
		</b>
	</his:TitleTag>

	<his:InputCrNoTag name="changePatientCatFB"></his:InputCrNoTag>	
	
	<his:statusInProcessWithJsp>
	<jsp:include page="/registration/patientDetail.cnt" flush="true" />
	
	
	
	
	<his:SubTitleTag name="Select Category">
	
	</his:SubTitleTag>
	
	<his:ContentTag>
	<table width="100%" cellpadding="1" cellspacing="1">
	<tr>
	<td width="20%" class="tdfonthead" nowrap>
	<div align="center">
	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	<b>
	<bean:message key="epicode"/>
	</b>
	</font>
	</div>
	</td>
	
	<td width="20%" class="tdfonthead" nowrap>
	<div align="center">
	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	<b>
	<bean:message key="department"/>
	</b>
	</font>
	</div>
	</td>
	
	<td width="20%" class="tdfonthead" nowrap>
	<div align="center">
	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	<font color="#FF0000">*</font>
	<b>
	<bean:message key="primary"/>
	<bean:message key="category"/>
	</b>
	</font>
	</div>
	</td>
	
		
	<td width="20%" class="tdfonthead" nowrap>
	<div align="center">
	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	<font color="#FF0000">*</font>
	<b>
	<bean:message key="secondary"/> 
	<bean:message key="category"/>
	</b>
	</font>
	</div>
	</td>
	
	<td width="20%" class="tdfonthead" nowrap>
	<div align="center">
	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	<b>
	<bean:message key="remarks"/>
	</b>
	</font>
	</div>
	</td>	
	</tr>
	
	<logic:iterate name="changePatientCatFB" property="changePatCategoryRow" id="episodeRow" type="ChangePatCategoryRow" indexId="rowIdx">
	<tr>
	<td width="20%" nowrap class="tdfont">
		<div align="center">
			<bean:write name="episodeRow" property="episodeCode" />
			<html:hidden name="episodeRow" property="episodeCode" />
		</div>
	</td>
	<td width="20%" nowrap class="tdfont">
		<div align="center">
			<bean:write name="episodeRow" property="episodeDepartment" />
			<html:hidden name="episodeRow" property="episodeDepartment" />
		</div>
	</td>
	
	<td width="20%" class="tdfont" nowrap>
		<div align="center">
		<bean:define name="episodeRow"  property="primaryCatCode" id="primCatCode" type="java.lang.String"/>
		<html:select name="episodeRow"  property="primaryCatCode"  tabindex="1"  styleClass ="regCbo" onchange='<%="submit4SecCat("+rowIdx.intValue()+");"%>'>
		<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY%>">   
		<html:option value="-1">Select Value</html:option>
		<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY%>" property = "value" labelProperty = "label"/>
		</logic:present>
		</html:select>
		</div>
	</td>
	
	<td width="20%" class="tdfont" nowrap>
		<div align="center">
		<html:select name="episodeRow" property="secondaryCatCode" tabindex="1" styleClass ="regCbo">
	    <html:option value="-1">Select Value</html:option>
	    <html:options collection = "<%=changePatientCatUTIL.getSecCatOptionsNameInRequest((String)primCatCode)%>" property = "value" labelProperty = "label"/> 
	    </html:select>
		</div>
	</td>
	
		<td width="20%" class="tdfont" nowrap>
		<div align="center">
			<html:text name="episodeRow" property="remarks" maxlength="40" onkeypress="return validateAlphabetsOnly(event,this)" tabindex="1"/>
		</div>
		</td>
		
	</tr>
 </logic:iterate>
		
</table>	
</his:ContentTag>
</his:statusInProcessWithJsp>
 

<his:ButtonToolBarTag>
<%
	String varStatus ="";
%>
<his:statusInProcessWithJsp>
	<%
		varStatus="InProcess";
	%>
</his:statusInProcessWithJsp>
<%if(varStatus.equals("InProcess")){%>
       <div align="center">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) validateChangePatCat(), submitTile('SAVE');" tabindex="1" onclick=" validateChangePatCat(), submitTile('SAVE');" >
         	         <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer   tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	  </div>
	  	  <%} else{ %>
 	         <div align="center">
         <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	  </div>
	  <%} %>
  </his:ButtonToolBarTag>


<his:status/>
<input type="hidden" name="hmode" value=""/>
<html:hidden name="changePatientCatFB" property="codeToManipulate"/>

<%}catch(Exception e){
	System.out.println("expceoption");
	e.printStackTrace();
}%>