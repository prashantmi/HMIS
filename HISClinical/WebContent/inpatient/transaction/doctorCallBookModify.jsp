<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/inpatient/js/doctorCall.js" />
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<%@page import="inpatient.InpatientConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:TitleTag>
	<his:name>
		<bean:message key="doctorCallBook"/>
	</his:name>
</his:TitleTag>

<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />

	<his:SubTitleTag name="Call Details">
	</his:SubTitleTag>
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="con"/>
						</font>
					</div>
				</td>
				<td width="75%" class="tdfont">
			        <div align="left">
			        	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					    	 &nbsp;<bean:write name="DoctorCallBookFB" property="consultantName"/>
					 	</font>
					</div>				
			    </td>
			</tr>
			<tr>    
			    <td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="priority"/>
						</font>
					</div>
				</td>
			    <td width="75%" class="tdfont">
			        <div align="left">
				    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					    	&nbsp;<bean:write name="DoctorCallBookFB" property="callPriorityName"/>
					 	</font>
				    </div>
			    </td>  
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<font color="#FF0000">*</font><bean:message key="callRemarks"/>
						</font>
					</div>
				</td>
				<td width="75%" class="tdfont">
			        <div align="left">
			          &nbsp;<html:textarea name="DoctorCallBookFB" property="callRemarks" rows="2" cols="80" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))">
						   </html:textarea>
					</div>				
			    </td>
			</tr>
		</table>
	</his:ContentTag>
	
	<his:SubTitleTag name="">
		<div align="left">
			<html:checkbox name="DoctorCallBookFB" property="isDocCallByPeon" onclick="togglePeon(this)"></html:checkbox>
			<bean:message key="isCallByPeon" />
			
			<logic:equal name="DoctorCallBookFB" property="isDocCallByPeon"  value="on">
				<html:hidden name="DoctorCallBookFB" property="isDocCallByPeon"/>
			</logic:equal>
		</div>
	</his:SubTitleTag>
		<logic:equal name="DoctorCallBookFB" property="isDocCallByPeon" value="off">		       	
			<div id="showPeon" style="display:none;">
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
					   		<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="status"/>
									</font>
								</div>
							</td>
						    <td width="75%" class="tdfont">
								<div align="left">
								   <html:radio name="DoctorCallBookFB" property="docCallByPeonStatus" tabindex="1"  value="<%=InpatientConfig.CALL_COMMUNICATED_TO_DR%>" />Call Communicated to Doctor
								   <html:radio name="DoctorCallBookFB" property="docCallByPeonStatus" tabindex="1"  value="<%=InpatientConfig.DOCTOR_NOT_AVAILABLE%>" />Doctor not Available
								</div>
									<logic:notEqual name="DoctorCallBookFB" property="docCallByPeonStatus" value="0">
										<html:hidden name="DoctorCallBookFB" property="docCallByPeonStatus"/>
									</logic:notEqual>
							</td>
						</tr>
						<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<font color="#FF0000">*</font><bean:message key="peon"/>
						</font>
					</div>
				</td>
				<td width="75%" class="tdfont">
			          <div align="left">
			          &nbsp;<html:select name="DoctorCallBookFB" property="peonEmpNo" style="width:160;" styleClass="registrationCmb" > 
								<html:option value="-1">Select Value</html:option>
								  <logic:present name="<%=InpatientConfig.PEON_LIST%>">
								<html:options collection="<%=InpatientConfig.PEON_LIST %>" labelProperty="label" property="value"  />
								</logic:present>
							</html:select> 
						</div>				
			        </td>
			</tr>
						<tr>	
						    <td width="25%" class="tdfonthead">
								<div align="right">
								
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="remarks"/>
									</font>
								</div>
							</td>
						    <td width="75%" class="tdfont">
								<div align="left">
								   
							   &nbsp;<html:textarea name="DoctorCallBookFB" property="docCallByPeonRemarks" rows="2" cols="80" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))">
						   </html:textarea>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>
			</div>
		</logic:equal>
		
		<logic:equal name="DoctorCallBookFB" property="isDocCallByPeon" value="on">		       	
			<div id="showPeon" >
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
						    <td width="25%" class="tdfonthead">
								<div align="right">
								
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="status"/>
									</font>
								</div>
							</td>
						    <td width="75%" class="tdfont">
								<div align="left">
								   <html:radio name="DoctorCallBookFB" property="docCallByPeonStatus" tabindex="1"  value="<%=InpatientConfig.CALL_COMMUNICATED_TO_DR%>" />Call Communicated to Doctor
								   <html:radio name="DoctorCallBookFB" property="docCallByPeonStatus" tabindex="1"  value="<%=InpatientConfig.DOCTOR_NOT_AVAILABLE%>" />Doctor not Available
								</div>
									<logic:notEqual name="DoctorCallBookFB" property="docCallByPeonStatus" value="0">
										<html:hidden name="DoctorCallBookFB" property="docCallByPeonStatus"/>
									</logic:notEqual>
							</td>
						</tr>
						<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<font color="#FF0000">*</font><bean:message key="peon"/>
						</font>
					</div>
				</td>
				<td width="75%" class="tdfont">
			          <div align="left">
			          &nbsp;<html:select name="DoctorCallBookFB" property="peonEmpNo" disabled="true" style="width:160;" styleClass="registrationCmb" > 
								<html:option value="-1">Select Value</html:option>
								  <logic:present name="<%=InpatientConfig.PEON_LIST%>">
								<html:options collection="<%=InpatientConfig.PEON_LIST %>" labelProperty="label" property="value"  />
								</logic:present>
							</html:select> 
						</div>				
			        </td>
			</tr>
						<tr>		
						    <td width="25%" class="tdfonthead">
								<div align="right">
						
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="remarks"/>
									</font>
								</div>
							</td>
						    <td width="75%" class="tdfont">
								<div align="left">
								 &nbsp;<html:textarea name="DoctorCallBookFB" property="docCallByPeonRemarks" rows="2" cols="80" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))">
						   </html:textarea>
								
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>	
			</div>
		</logic:equal>
			
		<his:SubTitleTag name="">
			<div align="left">
				<html:checkbox name="DoctorCallBookFB" property="isDocCallbyPhone" onclick="togglePhone(this)"></html:checkbox>
				<bean:message key="isCallByPhone" />
				
				<logic:equal name="DoctorCallBookFB" property="isDocCallbyPhone"  value="on">
					<html:hidden name="DoctorCallBookFB" property="isDocCallbyPhone"/>
				</logic:equal>
			</div>
		</his:SubTitleTag>
			<logic:equal name="DoctorCallBookFB" property="isDocCallbyPhone" value="off">		
				<div id="showPhone" style="display:none;">
					<his:ContentTag>
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								 <td width="25%" class="tdfonthead">
									<div align="right">
									
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="status"/>
										</font>
									</div>
								</td>
							    <td width="75%" class="tdfont">
									<div align="left">
									   <html:radio name="DoctorCallBookFB" property="docCallByPhoneStatus" tabindex="1"  value="<%=InpatientConfig.CALL_COMMUNICATED_TO_DR%>" />Call Communicated to Doctor
									   <html:radio name="DoctorCallBookFB" property="docCallByPhoneStatus" tabindex="1"  value="<%=InpatientConfig.DOCTOR_NOT_AVAILABLE%>" />Doctor not Available
									</div>
									<logic:notEqual name="DoctorCallBookFB" property="docCallByPhoneStatus" value="0">
										<html:hidden name="DoctorCallBookFB" property="docCallByPhoneStatus"/>
									</logic:notEqual>
								</td>
							</tr>
							<tr>	
							    <td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="remarks"/>
										</font>
									</div>
								</td>
							    <td width="75%" class="tdfont">
									<div align="left">
									&nbsp;<html:textarea name="DoctorCallBookFB" property="docCallByPhoneRemarks" rows="2" cols="80" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))">
						   </html:textarea>
									   
									</div>
								</td>
							</tr>
					  	</table>
					</his:ContentTag>  	
				</div>
			</logic:equal>
			
			<logic:equal name="DoctorCallBookFB" property="isDocCallbyPhone" value="on">		
				<div id="showPhone" >
					<his:ContentTag>
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
									
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="status"/>
										</font>
									</div>
								</td>
							    <td width="75%" class="tdfont">
									<div align="left">
									   <html:radio name="DoctorCallBookFB" property="docCallByPhoneStatus" tabindex="1"  value="<%=InpatientConfig.CALL_COMMUNICATED_TO_DR%>" />Call Communicated to Doctor
									   <html:radio name="DoctorCallBookFB" property="docCallByPhoneStatus" tabindex="1"  value="<%=InpatientConfig.DOCTOR_NOT_AVAILABLE%>" />Doctor not Available
									</div>
									<logic:notEqual name="DoctorCallBookFB" property="docCallByPhoneStatus" value="0">
										<html:hidden name="DoctorCallBookFB" property="docCallByPhoneStatus"/>
									</logic:notEqual>
								</td>
							</tr>
							<tr>	
							    <td width="25%" class="tdfonthead">
									<div align="right">
									
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="remarks"/>
										</font>
									</div>
								</td>
								<td width="75%" class="tdfont">
									<div align="left">
									&nbsp;<html:textarea name="DoctorCallBookFB" property="docCallByPhoneRemarks" rows="2" cols="80" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))">
						   			</html:textarea>
									   
									</div>
								</td>
							</tr>
					 	</table>
					</his:ContentTag> 	 
				</div>
			</logic:equal>
	



<his:ButtonToolBarTag>
		  <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer onclick ="submitForm21('MODIFYSAVE')" onkeypress="if(event.keyCode==13) submitForm21('MODIFYSAVE')") tabindex="1">
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer onclick ="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW')" tabindex="1">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer onclick ="clearModifyForm()" onkeypress="if(event.keyCode==13) clearModifyForm();" tabindex="1">
</his:ButtonToolBarTag>

<html:hidden name="DoctorCallBookFB" property="hmode"/>
<html:hidden name="DoctorCallBookFB" property="empNo"/>
<html:hidden name="DoctorCallBookFB" property="callPriority"/>
<html:hidden name="DoctorCallBookFB" property="callNo"/>
<html:hidden name="DoctorCallBookFB" property="status"/>

