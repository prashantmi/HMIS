<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="inpatient.InpatientConfig"%>
<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/date_validator.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/time_validator.js"/>
<his:javascript src="/inpatient/js/anc_validations.js"/>
<his:javascript src="/inpatient/js/anc_neonatal_detail.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/registration.js" />
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:TitleTag key="ancdeliverydtl">
</his:TitleTag>

<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
	
<his:statusList>
	<his:SubTitleTag key="ancneonatal">
	</his:SubTitleTag> 

	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>&nbsp;</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="birdatetime"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="45%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="birnature"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>&nbsp;</b>
						</font>		
					</div>
				</td>
			</tr>
			<logic:iterate id="newNatDtlVO" indexId="idxNewNatDtl" name="<%=InpatientConfig.ANCDETAIL_DELIVERY_OUTCOME_LIST%>" type="hisglobal.vo.ANCNeonatalDetailVO">
			<tr>
				<td width="5%" class="tdfonthead">
					<div align="center">
						<html:radio name="ANCNeonatalDetailFB" property="selectedNeoNat" tabindex="1" value="<%=idxNewNatDtl.toString()%>" onclick="onchangeSelectNewNat(this)"/>
					</div>
				</td>
				<td width="25%" class="tdfonthead">			
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<%=newNatDtlVO.getBirthDateTime()%>
							</b>
						</font>		
					</div>
				</td>
				<td width="45%" class="tdfonthead">			
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<%=InpatientConfig.BIRTH_NATURE_ARR[Integer.parseInt(newNatDtlVO.getBirthNatureId())]%>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%
								if(newNatDtlVO.getBirthNatureId().equals(InpatientConfig.BIRTH_NATURE_LIVE_BIRTH))
								{
									String clickFun ="getBirthCertificate('"+idxNewNatDtl+"',event)"; 
							%>
							<a style="cursor:pointer" onclick="<%=clickFun%>" > NOTIFICATION </a>
							<%
								}
							%>
						</font>		
					</div>
				</td>
			</tr>
			</logic:iterate>
		</table>
	</his:ContentTag>
</his:statusList>
<his:statusTransactionInProcess>
	<% String entryDate=WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");  %>
	<html:hidden name="ANCNeonatalDetailFB" property="selectedNeoNat"/>
	<html:hidden name="ANCNeonatalDetailFB" property="selectedNeoNatSlNo"/>
	
	<his:SubTitleTag key="newnatalinfo">
		<table width="100%" height="100%" border="0" cellspacing="1" cellpadding="0">
			<tr><td width="1%" align="right" valign="middle">				
				<img id="imgNeoNatDtl" tabindex="1" style="cursor: pointer;" src="/HIS/hisglobal/images/avai/arrow-up.png"; onclick="onclickImage(this)"/>
			</td></tr>
		</table>
	</his:SubTitleTag> 

	<div id="divNeoNatDtl" style="display: block;">
	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="birdatetime"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:hidden name="ANCNeonatalDetailFB" property="birthDateTime"/>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>							
						&nbsp;<bean:write name="ANCNeonatalDetailFB" property="birthDateTime"/>
						</b></font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="birnature"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:hidden name="ANCNeonatalDetailFB" property="birthNatureId"/>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:define name="ANCNeonatalDetailFB" property="birthNatureId" id="idBirthNatureId" type="java.lang.String"/>							
							&nbsp;<%=InpatientConfig.BIRTH_NATURE_ARR[Integer.parseInt(idBirthNatureId)]%>
						</b></font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><font color="#FF0000">*</font><bean:message key="gender"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCNeonatalDetailFB" property="genderCode">
							<html:select name="ANCNeonatalDetailFB" property="genderCode" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_GENDER_LIST %>">
									<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_GENDER_LIST %>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</logic:empty>
						<logic:notEmpty name="ANCNeonatalDetailFB" property="genderCode">
							&nbsp;<bean:write name="ANCNeonatalDetailFB" property="gender"/>
							<html:hidden name="ANCNeonatalDetailFB" property="genderCode"/>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font><bean:message key="wetofbirth"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCNeonatalDetailFB" property="weight">
							<html:text name="ANCNeonatalDetailFB" property="weight" tabindex="1" maxlength="3" size="6" onkeypress="return (validatePositiveNumericOnly(this,event));"></html:text>
						</logic:empty>
						<logic:notEmpty name="ANCNeonatalDetailFB" property="weight">
							&nbsp;<bean:write name="ANCNeonatalDetailFB" property="weight"/>
							<html:hidden name="ANCNeonatalDetailFB" property="weight"/>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><font color="#FF0000">*</font><bean:message key="lenofbaby"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCNeonatalDetailFB" property="babylength">
							<html:text name="ANCNeonatalDetailFB" property="babylength" tabindex="1" maxlength="6" size="6" onkeypress="return (validatePositiveNumericOnly(this,event));"></html:text>
						</logic:empty>
						<logic:notEmpty name="ANCNeonatalDetailFB" property="babylength">
							&nbsp;<bean:write name="ANCNeonatalDetailFB" property="babylength"/>
							<html:hidden name="ANCNeonatalDetailFB" property="babylength"/>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font><bean:message key="headcircum"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCNeonatalDetailFB" property="headCircumferences">
							<html:text name="ANCNeonatalDetailFB" property="headCircumferences" tabindex="1" maxlength="4" size="6" onkeypress="return (validatePositiveNumericOnly(this,event));"></html:text>
						</logic:empty>
						<logic:notEmpty name="ANCNeonatalDetailFB" property="headCircumferences">
							&nbsp;<bean:write name="ANCNeonatalDetailFB" property="headCircumferences"/>
							<html:hidden name="ANCNeonatalDetailFB" property="headCircumferences"/>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="isanomoly"/>
						</b></font>		
					</div>
				</td>
				<td width="25%" class="tdfont" valign="middle">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCNeonatalDetailFB" property="isAnomolyPresent">
							<html:radio name="ANCNeonatalDetailFB" property="isAnomolyPresent" tabindex="1" value="<%=OpdConfig.YES%>" onchange="onchangeAnomolyFlag()"/>&nbsp;<bean:message key="yes"/>&nbsp;
							<html:radio name="ANCNeonatalDetailFB" property="isAnomolyPresent" tabindex="1" value="<%=OpdConfig.NO%>" onchange="onchangeAnomolyFlag()"/>&nbsp;<bean:message key="no"/>
						</logic:empty>
						<logic:notEmpty name="ANCNeonatalDetailFB" property="isAnomolyPresent">
							<logic:equal name="ANCNeonatalDetailFB" property="isAnomolyPresent" value="<%=OpdConfig.YES%>"><bean:message key="yes"/></logic:equal>
							<logic:equal name="ANCNeonatalDetailFB" property="isAnomolyPresent" value="<%=OpdConfig.NO%>"><bean:message key="no"/></logic:equal>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="unbiliaateris"/>
						</b></font>		
					</div>
				</td>
				<td width="25%" class="tdfont" valign="middle">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCNeonatalDetailFB" property="umbilicalArteries">
							<html:select name="ANCNeonatalDetailFB" property="umbilicalArteries" styleClass="cbo2" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<html:option value="<%=InpatientConfig.UMBILICAL_ARTERIES_TWO%>"><%=InpatientConfig.UMBILICAL_ARTERIES_ARR[Integer.parseInt(InpatientConfig.UMBILICAL_ARTERIES_TWO)]%></html:option>
	 							<html:option value="<%=InpatientConfig.UMBILICAL_ARTERIES_SINGLE%>"><%=InpatientConfig.UMBILICAL_ARTERIES_ARR[Integer.parseInt(InpatientConfig.UMBILICAL_ARTERIES_SINGLE)]%></html:option>
							</html:select>
						</logic:empty>
						<logic:notEmpty name="ANCNeonatalDetailFB" property="umbilicalArteries">
							<bean:define id="idUmbilicalArteries" name="ANCNeonatalDetailFB" property="umbilicalArteries" type="java.lang.String"></bean:define>
							&nbsp;<%=InpatientConfig.UMBILICAL_ARTERIES_ARR[Integer.parseInt(idUmbilicalArteries)] %>
							<html:hidden name="ANCNeonatalDetailFB" property="umbilicalArteries"/>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
			</tr>
		</table>
	
		<!-- Anomoly Detail -->
		<% String isAnomolyPresentDisplay="display: none;";%>
		<logic:notEmpty name="ANCNeonatalDetailFB" property="isAnomolyPresent">
			<logic:equal name="ANCNeonatalDetailFB" property="isAnomolyPresent" value="<%=OpdConfig.YES%>">
				<%isAnomolyPresentDisplay="display: block;";%>
			</logic:equal>
		</logic:notEmpty>									
		<div id="divAnomolyDtl" style="<%=isAnomolyPresentDisplay%>">
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font><bean:message key="anomolytype"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCNeonatalDetailFB" property="anomolyTypeId">
	   						<html:select name="ANCNeonatalDetailFB" property="anomolyTypeId" tabindex="1" styleClass="cbo2">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_ANOMOLY_TYPE_LIST %>">
									<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_ANOMOLY_TYPE_LIST %>" property="value" labelProperty="label" />
								</logic:present>								
	   						</html:select>
						</logic:empty>
						<logic:notEmpty name="ANCNeonatalDetailFB" property="anomolyTypeId">
							&nbsp;<bean:write name="ANCNeonatalDetailFB" property="anomolyType"/>
							<html:hidden name="ANCNeonatalDetailFB" property="anomolyTypeId"/>
						</logic:notEmpty>
						</b></font>						
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font><bean:message key="anomolyrem"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCNeonatalDetailFB" property="anomolyRemarks">
							<html:textarea name="ANCNeonatalDetailFB" property="anomolyRemarks" cols="25" rows="2" tabindex="1" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumOnly(this,event))" ></html:textarea>
						</logic:empty>
						<logic:notEmpty name="ANCNeonatalDetailFB" property="anomolyRemarks">
							&nbsp;<bean:write name="ANCNeonatalDetailFB" property="anomolyRemarks"/>
							<html:hidden name="ANCNeonatalDetailFB" property="anomolyRemarks"/>
						</logic:notEmpty>
						</b></font>						
					</div>
				</td>
			</tr>
		</table>
		</div>

	<!-- Activity Date Time -->
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<logic:notEqual name="ANCNeonatalDetailFB" property="birthNatureId" value="<%=InpatientConfig.BIRTH_NATURE_STILL_BIRTH%>">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="crydattim"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCNeonatalDetailFB" property="cryDateTime">
							<html:hidden name="ANCNeonatalDetailFB" property="cryDateTime"/>
							<html:text name="ANCNeonatalDetailFB" property="cryDate" value="dd-mm-yyyy" tabindex="1" maxlength="10" size="11" onfocus="DateValidator.setupOnFocus(this)"></html:text>
							<html:text name="ANCNeonatalDetailFB" property="cryTime" value="hh:mm" tabindex="1" maxlength="5" size="6" onfocus="TimeValidator.setupOnFocus(this)"></html:text>					
						</logic:empty>
						<logic:notEmpty name="ANCNeonatalDetailFB" property="cryDateTime">
							&nbsp;<bean:write name="ANCNeonatalDetailFB" property="cryDateTime"/>
							<html:hidden name="ANCNeonatalDetailFB" property="cryDateTime"/>
						</logic:notEmpty>
						</b></font>						
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="urinedattim"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCNeonatalDetailFB" property="urineDateTime">
							<html:hidden name="ANCNeonatalDetailFB" property="urineDateTime"/>
							<html:text name="ANCNeonatalDetailFB" property="urineDate" value="dd-mm-yyyy" tabindex="1" maxlength="10" size="11" onfocus="DateValidator.setupOnFocus(this)"></html:text>
							<html:text name="ANCNeonatalDetailFB" property="urineTime" value="hh:mm" tabindex="1" maxlength="5" size="6" onfocus="TimeValidator.setupOnFocus(this)"></html:text>					
						</logic:empty>
						<logic:notEmpty name="ANCNeonatalDetailFB" property="urineDateTime">
							&nbsp;<bean:write name="ANCNeonatalDetailFB" property="urineDateTime"/>
							<html:hidden name="ANCNeonatalDetailFB" property="urineDateTime"/>
						</logic:notEmpty>
						</b></font>						
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="feeddattim"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCNeonatalDetailFB" property="motherFeedDateTime">
							<html:hidden name="ANCNeonatalDetailFB" property="motherFeedDateTime"/>
							<html:text name="ANCNeonatalDetailFB" property="motherFeedDate" value="dd-mm-yyyy" tabindex="1" maxlength="10" size="11" onfocus="DateValidator.setupOnFocus(this)"></html:text>
							<html:text name="ANCNeonatalDetailFB" property="motherFeedTime" value="hh:mm" tabindex="1" maxlength="5" size="6" onfocus="TimeValidator.setupOnFocus(this)"></html:text>					
						</logic:empty>
						<logic:notEmpty name="ANCNeonatalDetailFB" property="motherFeedDateTime">
							&nbsp;<bean:write name="ANCNeonatalDetailFB" property="motherFeedDateTime"/>
							<html:hidden name="ANCNeonatalDetailFB" property="motherFeedDateTime"/>
						</logic:notEmpty>
						</b></font>						
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="motherfeeddattim"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCNeonatalDetailFB" property="feedDateTime">
							<html:hidden name="ANCNeonatalDetailFB" property="feedDateTime"/>
							<html:text name="ANCNeonatalDetailFB" property="feedDate" value="dd-mm-yyyy" tabindex="1" maxlength="10" size="11" onfocus="DateValidator.setupOnFocus(this)"></html:text>
							<html:text name="ANCNeonatalDetailFB" property="feedTime" value="hh:mm" tabindex="1" maxlength="5" size="6" onfocus="TimeValidator.setupOnFocus(this)"></html:text>					
						</logic:empty>
						<logic:notEmpty name="ANCNeonatalDetailFB" property="feedDateTime">
							&nbsp;<bean:write name="ANCNeonatalDetailFB" property="feedDateTime"/>
							<html:hidden name="ANCNeonatalDetailFB" property="feedDateTime"/>
						</logic:notEmpty>
						</b></font>						
					</div>
				</td>
			</tr>
			</logic:notEqual>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="isbirthtrauma"/>
						</b></font>		
					</div>
				</td>
				<td width="25%" class="tdfont" valign="middle">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCNeonatalDetailFB" property="isBirthTrauma">
							<html:radio name="ANCNeonatalDetailFB" property="isBirthTrauma" tabindex="1" value="<%=OpdConfig.YES%>" onchange="onchangeBitrthTrumaFlag()"/>&nbsp;<bean:message key="yes"/>&nbsp;
							<html:radio name="ANCNeonatalDetailFB" property="isBirthTrauma" tabindex="1" value="<%=OpdConfig.NO%>" onchange="onchangeBitrthTrumaFlag()"/>&nbsp;<bean:message key="no"/>
						</logic:empty>
						<logic:notEmpty name="ANCNeonatalDetailFB" property="isBirthTrauma">
							<logic:equal name="ANCNeonatalDetailFB" property="isBirthTrauma" value="<%=OpdConfig.YES%>"><bean:message key="yes"/></logic:equal>
							<logic:equal name="ANCNeonatalDetailFB" property="isBirthTrauma" value="<%=OpdConfig.NO%>"><bean:message key="no"/></logic:equal>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
				</td>
				<td width="25%"  class="tdfont">
				</td>
			</tr>
		</table>
	
		<!-- Induction Detail -->
		<% String isBirthTraumaDisplay="display: none;";%>
		<logic:notEmpty name="ANCNeonatalDetailFB" property="isBirthTrauma">
			<logic:equal name="ANCNeonatalDetailFB" property="isBirthTrauma" value="<%=OpdConfig.YES%>">
				<%isBirthTraumaDisplay="display: block;";%>
			</logic:equal>
		</logic:notEmpty>									
		<div id="divBitrthTrumaDtl" style="<%=isBirthTraumaDisplay%>">
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font><bean:message key="traumacause"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCNeonatalDetailFB" property="traumaCauseId">
	   						<html:select name="ANCNeonatalDetailFB" property="traumaCauseId" tabindex="1" styleClass="cbo2">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_BIRTH_TRAUMA_CAUSE_LIST %>">
									<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_BIRTH_TRAUMA_CAUSE_LIST %>" property="value" labelProperty="label" />
								</logic:present>								
	   						</html:select>
						</logic:empty>
						<logic:notEmpty name="ANCNeonatalDetailFB" property="traumaCauseId">
							&nbsp;<bean:write name="ANCNeonatalDetailFB" property="traumaCause"/>
							<html:hidden name="ANCNeonatalDetailFB" property="traumaCauseId"/>
						</logic:notEmpty>
						</b></font>						
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="traumarem"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCNeonatalDetailFB" property="birthTraumaRemarks">
							<html:textarea name="ANCNeonatalDetailFB" property="birthTraumaRemarks" cols="25" rows="2" tabindex="1" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumOnly(this,event))" ></html:textarea>
						</logic:empty>
						<logic:notEmpty name="ANCNeonatalDetailFB" property="birthTraumaRemarks">
							&nbsp;<bean:write name="ANCNeonatalDetailFB" property="birthTraumaRemarks"/>
							<html:hidden name="ANCNeonatalDetailFB" property="birthTraumaRemarks"/>
						</logic:notEmpty>
						</b></font>						
					</div>
				</td>
			</tr>
		</table>
		</div>

		<logic:equal name="ANCNeonatalDetailFB" property="birthNatureId" value="<%=InpatientConfig.BIRTH_NATURE_STILL_BIRTH%>">
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<font color="#FF0000">*</font><bean:message key="whendeathdetect"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCNeonatalDetailFB" property="whenStillBirthDetection">
							<html:select name="ANCNeonatalDetailFB" property="whenStillBirthDetection" tabindex="1" styleClass="cbo2">
								<html:option value="-1">Select Value</html:option>
								<html:option value="<%=InpatientConfig.STILL_BIRTH_DETECTION_DURING_DELIVERY%>"><%=InpatientConfig.STILL_BIRTH_DETECTION_ARR[Integer.parseInt(InpatientConfig.STILL_BIRTH_DETECTION_DURING_DELIVERY)]%></html:option>
								<html:option value="<%=InpatientConfig.STILL_BIRTH_DETECTION_AFTER_DELIVERY%>"><%=InpatientConfig.STILL_BIRTH_DETECTION_ARR[Integer.parseInt(InpatientConfig.STILL_BIRTH_DETECTION_AFTER_DELIVERY)]%></html:option>
							</html:select>
						</logic:empty>
						<logic:notEmpty name="ANCNeonatalDetailFB" property="whenStillBirthDetection">
							<bean:define id="idWhenStillBirthDetection" name="ANCNeonatalDetailFB" property="whenStillBirthDetection" type="java.lang.String"></bean:define>
							&nbsp;<%=InpatientConfig.STILL_BIRTH_DETECTION_ARR[Integer.parseInt(idWhenStillBirthDetection)] %>
							<html:hidden name="ANCNeonatalDetailFB" property="whenStillBirthDetection"/>
						</logic:notEmpty>
						</b></font>						
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<font color="#FF0000">*</font><bean:message key="causestlillbir"/>
						</b></font>		
					</div>
				</td>
				<td width="75%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCNeonatalDetailFB" property="deathStillBirthCause">
							<html:textarea name="ANCNeonatalDetailFB" property="deathStillBirthCause" cols="25" rows="2" tabindex="1" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumOnly(this,event))" ></html:textarea>
						</logic:empty>
						<logic:notEmpty name="ANCNeonatalDetailFB" property="deathStillBirthCause">
							&nbsp;<bean:write name="ANCNeonatalDetailFB" property="deathStillBirthCause"/>
							<html:hidden name="ANCNeonatalDetailFB" property="deathStillBirthCause"/>
						</logic:notEmpty>
						</b></font>						
					</div>
				</td>
			</tr>
		</table>
		</logic:equal>
	</his:ContentTag>
	</div>

	<logic:notEqual name="ANCNeonatalDetailFB" property="birthNatureId" value="<%=InpatientConfig.BIRTH_NATURE_STILL_BIRTH%>">
	<his:SubTitleTag key="newnatalapgardtl">
		<table width="100%" height="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<logic:notEmpty name="<%=InpatientConfig.ANCDETAIL_SELECTED_NEONAT_APGAR_DETAIL_LIST%>">
				<td width="99%" align="center" valign="middle">					
					<a style="cursor:pointer" onclick="popupApgarDetail(event)"><b> Added APGAR Detail </b></a>
				</td>
				</logic:notEmpty>
				<td width="1%" align="right" valign="middle">	
				<logic:notEmpty name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_SELECTED_NEO_NAT_APGAR_TIME_LIST %>">			
				<img id="imgNeoNatApgarDtl" tabindex="1" style="cursor: pointer;" src="/HIS/hisglobal/images/avai/arrow-down.png"; onclick="onclickImage(this)"/>
				</logic:notEmpty>
			</td></tr>
		</table>
	</his:SubTitleTag> 

	<!-- APGAR (Activity, Pulse, Grimace, Appearance, Respiration) -->
	<logic:notEmpty name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_SELECTED_NEO_NAT_APGAR_TIME_LIST %>">
	<div id="divNeoNatApgarDtl" style="display: none;">
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="apgarTime"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
   						<html:select name="ANCNeonatalDetailFB" property="apgarTime" tabindex="1" styleClass="cbo2">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_SELECTED_NEO_NAT_APGAR_TIME_LIST %>">
								<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_SELECTED_NEO_NAT_APGAR_TIME_LIST %>" property="value" labelProperty="label" />
							</logic:present>								
   						</html:select>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
				</td>
				<td width="25%" class="tdfont" valign="middle">
					<div align="right">
						<img id="imgNeoNatApgarDtlADD" tabindex="1" style="cursor: pointer;" src="/HIS/hisglobal/images/avai/plus.png"; onclick="submitFormOnValidate(addAgparDetail(),'ADDAPGAR');" onkeypress="if(event.keyCode==13)submitFormOnValidate(addAgparDetail(),'ADDAPGAR');") />
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="apgaractivity"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
   						<html:text name="ANCNeonatalDetailFB" property="activity" tabindex="1" styleClass="textbox11" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)"></html:text>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><font color="#FF0000">*</font><bean:message key="apgarscore"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
   						<html:text name="ANCNeonatalDetailFB" property="activityApgar" tabindex="1" size="6" maxlength="1" onkeypress="return validatePositiveIntegerOnly(this,event)" onchange="setTotalApgarScore()"></html:text>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="apgarpulse"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
   						<html:text name="ANCNeonatalDetailFB" property="heartRate" tabindex="1" styleClass="textbox11" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)"></html:text>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><font color="#FF0000">*</font><bean:message key="apgarscore"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
   						<html:text name="ANCNeonatalDetailFB" property="heartRateApgar" tabindex="1" size="6" maxlength="1" onkeypress="return validatePositiveIntegerOnly(this,event)" onchange="setTotalApgarScore()"></html:text>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="apgargrimace"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
   						<html:text name="ANCNeonatalDetailFB" property="grimace" tabindex="1" styleClass="textbox11" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)"></html:text>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><font color="#FF0000">*</font><bean:message key="apgarscore"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
   						<html:text name="ANCNeonatalDetailFB" property="grimaceApgar" tabindex="1" size="6" maxlength="1" onkeypress="return validatePositiveIntegerOnly(this,event)" onchange="setTotalApgarScore()"></html:text>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="apgarappearance"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
   						<html:text name="ANCNeonatalDetailFB" property="color" tabindex="1" styleClass="textbox11" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)"></html:text>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><font color="#FF0000">*</font><bean:message key="apgarscore"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
   						<html:text name="ANCNeonatalDetailFB" property="colorApgar" tabindex="1" size="6" maxlength="1" onkeypress="return validatePositiveIntegerOnly(this,event)" onchange="setTotalApgarScore()"></html:text>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="apgarrespiration"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
   						<html:text name="ANCNeonatalDetailFB" property="respiration" tabindex="1" styleClass="textbox11" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)"></html:text>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><font color="#FF0000">*</font><bean:message key="apgarscore"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
   						<html:text name="ANCNeonatalDetailFB" property="respirationApgar" tabindex="1" size="6" maxlength="1" onkeypress="return validatePositiveIntegerOnly(this,event)" onchange="setTotalApgarScore()"></html:text>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
				</td>
				<td width="25%"  class="tdfont">
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="tot"/> <bean:message key="apgarscore"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
   						<html:text name="ANCNeonatalDetailFB" property="apgarScore" tabindex="1" size="6" maxlength="2" disabled="true"></html:text>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
	</div>
	</logic:notEmpty>
	</logic:notEqual>

</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
	<his:statusList>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	</his:statusList>
	<his:statusTransactionInProcess>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer"  tabindex='2' onclick =  "submitFormOnValidate(validateAdd(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateAdd(),'SAVE');")>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm('GETNEONATALDTL')" onkeypress="if(event.keyCode==13) submitForm('GETNEONATALDTL');">
	</his:statusTransactionInProcess>
	<his:statusUnsuccessfull>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	</his:statusUnsuccessfull>
</his:ButtonToolBarTag>
	

<html:hidden name="ANCNeonatalDetailFB" property="hmode"/>
<html:hidden name="ANCNeonatalDetailFB" property="entryDate"/>
<html:hidden name="ANCNeonatalDetailFB" property="entryTime"/>
<html:hidden name="ANCNeonatalDetailFB" property="patCrNo"/>
<html:hidden name="ANCNeonatalDetailFB" property="episodeCode"/>
<html:hidden name="ANCNeonatalDetailFB" property="episodeVisitNo"/>
<html:hidden name="ANCNeonatalDetailFB" property="admissionNo"/>
<html:hidden name="ANCNeonatalDetailFB" property="departmentUnitCode"/>
<html:hidden name="ANCNeonatalDetailFB" property="wardCode"/>
<html:hidden name="ANCNeonatalDetailFB" property="userSeatId"/>

<html:hidden name="ANCNeonatalDetailFB" property="gravidaNo"/>