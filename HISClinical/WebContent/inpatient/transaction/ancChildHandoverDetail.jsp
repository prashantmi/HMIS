<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="inpatient.InpatientConfig"%>

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/inpatient/js/anc_child_handover.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<his:TitleTag key="ancchildhandoverdtl">
</his:TitleTag>

<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
	
<his:statusTransactionInProcess>
	<his:SubTitleTag key="handoverdetail">
	</his:SubTitleTag> 

	<his:ContentTag>
		<logic:notPresent name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_NEONAT_HANDOVER_DETAIL%>">
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td class="tdfonthead" width="25%" >
		  			<div align="right">	           
		 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 					<b>
		 						<font color="#FF0000">*</font>
		 						<bean:message key="handoverdate"/>
		 					</b>	
		  				</font>
		  			</div>
	      		</td> 
	      		<td width="25%" class="tdfont">
					<div align="left">
						<html:hidden name="ANCChildHandoverDetailFB" property="handoverDateTime"/>
						<his:date name="handoverDate" dateFormate="%d-%b-%Y" ></his:date>
					</div>
				</td>
				<td class="tdfonthead" width="25%" >
		  			<div align="right">	           
		 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 					<b>
		 						<font color="#FF0000">*</font>
		 						<bean:message key="handovertime"/>
		 					</b>	
		  				</font>
		  			</div>
	      		</td>
	      		<td width="25%" class="tdfont">
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<span id='divStarthrcontrol' align="left">	            
				   			<html:text name="ANCChildHandoverDetailFB" tabindex="1" property="handoverTimeHr" maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeHourCheck(this)" />	
				  				<b>
				  			 		<bean:message key="colon"/>
				  			 	</b>
						</span>
				 		<span id='divStartMinControl' align="left">         
							<html:text name="ANCChildHandoverDetailFB" tabindex="1" property="handoverTimeMin" maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeMinCheck(this)"/>				
								<bean:message key="timeFormat"/>
		    			</span>
					</font>
				</td> 
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<font color="#FF0000">*</font>
							<b><bean:message key="relativename"/></b>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<html:text name="ANCChildHandoverDetailFB" property="relativeName" tabindex="1" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event)"></html:text>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<font color="#FF0000">*</font>
							<b><bean:message key="realtionship"/></b>	
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<html:select name="ANCChildHandoverDetailFB" property="relativeCode" tabindex="1">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_RELATIONSHIP_LIST%>">
								<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_RELATIONSHIP_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<font color="#FF0000">*</font>
							<b><bean:message key="relativeaddress"/></b>	
						</font>
					</div>
				</td>
				<td width="75%" class="tdfont" colspan="3">
					<div align="left">
						<html:textarea name="ANCChildHandoverDetailFB" property="relativeAddress" tabindex="1" rows="1" cols="50" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event))"></html:textarea>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="remarks"/></b>
						</font>
					</div>
				</td>
				<td width="75%" class="tdfont" colspan="3">
					<div align="left">
						<html:textarea name="ANCChildHandoverDetailFB" property="remarks" tabindex="1" rows="1" cols="50" onkeypress="return (validateTextArea(event,this,'50') && validateAlphaNumericOnly(event))"></html:textarea>
					</div>
				</td>
			</tr>
		</table>
		</logic:notPresent>
		
		<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_NEONAT_HANDOVER_DETAIL%>">
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td class="tdfonthead" width="25%" >
		  			<div align="right">	           
		 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 					<b>
		 						<bean:message key="handoverdate"/>
		 					</b>	
		  				</font>
		  			</div>
	      		</td> 
	      		<td width="25%" class="tdfont">
					<div align="left">
						<html:hidden name="ANCChildHandoverDetailFB" property="handoverDateTime"/>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="ANCChildHandoverDetailFB" property="handoverDate"/>
						</font>
					</div>
				</td>
				<td class="tdfonthead" width="25%" >
		  			<div align="right">	           
		 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 					<b>
		 						<bean:message key="handovertime"/>
		 					</b>	
		  				</font>
		  			</div>
	      		</td>
	      		<td width="25%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="ANCChildHandoverDetailFB" property="handoverTime"/>
						</font>
					</div>
				</td> 
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="relativename"/></b>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<html:hidden name="ANCChildHandoverDetailFB" property="relativeName"/>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="ANCChildHandoverDetailFB" property="relativeName"/>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="realtionship"/></b>	
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<html:hidden name="ANCChildHandoverDetailFB" property="relativeCode"/>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="ANCChildHandoverDetailFB" property="relationship"/>
						</font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="relativeaddress"/></b>	
						</font>
					</div>
				</td>
				<td width="75%" class="tdfont" colspan="3">
					<div align="left">
						<html:hidden name="ANCChildHandoverDetailFB" property="relativeAddress"/>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="ANCChildHandoverDetailFB" property="relativeAddress"/>
						</font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="remarks"/></b>
						</font>
					</div>
				</td>
				<td width="75%" class="tdfont" colspan="3">
					<div align="left">
						<html:hidden name="ANCChildHandoverDetailFB" property="remarks"/>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="ANCChildHandoverDetailFB" property="remarks"/>
						</font>
					</div>
				</td>
			</tr>
		</table>
		</logic:present>
	</his:ContentTag>
</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
	<his:statusTransactionInProcess>
		<logic:notPresent name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_NEONAT_HANDOVER_DETAIL%>">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer"  tabindex='2' onclick =  "submitFormOnValidate(validateSave(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateSave(),'SAVE');")>
		</logic:notPresent>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
	</his:statusTransactionInProcess>
	<his:statusUnsuccessfull>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	</his:statusUnsuccessfull>
</his:ButtonToolBarTag>
	


<html:hidden name="ANCChildHandoverDetailFB" property="hmode"/>
<html:hidden name="ANCChildHandoverDetailFB" property="entryDate"/>
<html:hidden name="ANCChildHandoverDetailFB" property="entryTime"/>
<html:hidden name="ANCChildHandoverDetailFB" property="entryTimeHr"/>
<html:hidden name="ANCChildHandoverDetailFB" property="entryTimeMin"/>
<html:hidden name="ANCChildHandoverDetailFB" property="patCrNo"/>
<html:hidden name="ANCChildHandoverDetailFB" property="episodeCode"/>
<html:hidden name="ANCChildHandoverDetailFB" property="episodeVisitNo"/>
<html:hidden name="ANCChildHandoverDetailFB" property="admissionNo"/>
<html:hidden name="ANCChildHandoverDetailFB" property="departmentUnitCode"/>
<html:hidden name="ANCChildHandoverDetailFB" property="wardCode"/>
<html:hidden name="ANCChildHandoverDetailFB" property="userSeatId"/>

<html:hidden name="ANCChildHandoverDetailFB" property="gravidaNo"/>
