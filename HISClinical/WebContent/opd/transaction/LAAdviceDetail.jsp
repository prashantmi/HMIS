<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.List"%>

<his:javascript src="/hisglobal/transactionutil/js/master.js"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
		<logic:notEmpty name="<%=OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_EXT_TREATMENT_DETAIL_LIST %>">
		<his:SubTitleTag name="Previous Other Advice Detail">
		</his:SubTitleTag>
			
			<his:ContentTag>
			<table id="prevLADtlTableId" width="100%" border="0" cellspacing="1" cellpadding="0" >
				<tr>
					<td width="20%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="ExtTreatmentName" />
								</b>
							</font>
						</div>
					</td>
					
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="frequency" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="startday" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="endday" />
								</b>
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="instructions" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="revoke" />
							</b>
						</font>
					</div>
				</td>

			</tr>
			<logic:iterate id="extTretDtlVO" indexId="idx" name="<%=OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_EXT_TREATMENT_DETAIL_LIST%>" type="hisglobal.vo.PatExtTreatmentDetailVO">
			<%String ind=idx.toString(); %>
			<tr>
				<td width="20%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:write name="extTretDtlVO" property="extTreatmentName"/>
							<html:hidden name="PatientTreatmentDetailFB" property="prevSelExtTreatmentName" value="<%=extTretDtlVO.getExtTreatmentName() %>"/>
							</b>
						</font>
					</div>
				</td>
				
				<td width="10%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:write name="extTretDtlVO" property="frequencyName"/>
								<html:hidden name="PatientTreatmentDetailFB" property="endDate" value=""/>
								<html:hidden  name="PatientTreatmentDetailFB" property="rxContinueFlag" value="<%=extTretDtlVO.getRxContinueFlag() %>"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:write name="extTretDtlVO" property="startDate"/>
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:write name="extTretDtlVO" property="endDate"/>
							</b>
						</font>
					</div>
				</td>
				<td width="20%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:write name="extTretDtlVO" property="remarks"/>
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="center">
						<logic:notEqual value="before" name="extTretDtlVO" property="date">
							<html:checkbox property="extRevoke" name="PatientTreatmentDetailFB" value="<%=ind %>" tabindex="1"></html:checkbox>
						</logic:notEqual>
					</div>
				</td>
			</tr>
			</logic:iterate>
		</table>
		</his:ContentTag>
		</logic:notEmpty>
		<his:SubTitleTag name="Other/Pre-Operative Advice">
		</his:SubTitleTag>
		<his:ContentTag>
			<html:hidden name="PatientTreatmentDetailFB" property="extDrugDetailRows" />
		<table id="tblExtTreatMentDetailId" width="100%" border="0" cellspacing="1" cellpadding="0" style="display:block;">
			<tr>
				<td width="10%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="ExtTreatmentName" />
							</b>
						</font>
					</div>
				</td>
				
				<td width="10%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="frequency" />
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="dosedays" />
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="startday" />
							</b>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="instructions" />
							</b>
						</font>
					</div>
				</td>
				<td width="5%" class="tdfonthead">
				</td>
			</tr>
			<%int countNewExt=0;%>
			<logic:iterate id="extTretDtlVO" indexId="i" name="<%=OpdConfig.PAT_TREATMENT_DTL_EXT_DETAIL_LIST%>" type="hisglobal.vo.PatExtTreatmentDetailVO">
			<tr id="trRowID<%=i%>">
				<%countNewExt++;%>
				<td width="10%" class="tdfont">
					<div id="extTreatList" style="display: none; position: absolute;">
									<select name="extTreatList" id="extTreatList" multiple="multiple" size="4">
										<logic:iterate name="<%=OpdConfig.ESSENTIALS_LIST_ALL_EXT_TREATMENT%>" id="list">
											<option value="<bean:write name='list' property='value'/>" > <bean:write name='list' property='label'/></option>
										</logic:iterate>
									</select>
								</div>
					<div align="center">
						<html:text name="PatientTreatmentDetailFB" property="selExtTreatmentName" maxlength="100" size="25" tabindex="1" 
							value="<%=extTretDtlVO.getExtTreatmentName() %>" 
							onkeypress="return validateAlphaOnly(this,event)" 
						onkeyup="getEXTTreatmentDropDownData(event,this);" 
						onblur="callOnBlurExt()"></html:text>
						<html:hidden name="PatientTreatmentDetailFB" property="selExtTreatmentId" value="<%=extTretDtlVO.getExtTreatmentId() %>"/>

					</div>			
				</td>				
				
				
				<td width="10%" class="tdfont">
					<div align="center">
						<html:select name="PatientTreatmentDetailFB" property="selExtFrequencyId" tabindex="1" 
						value="<%=extTretDtlVO.getFrequencyId() %>" >
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" >
							<html:options collection="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" property="value" labelProperty="label"/>
							</logic:present>
						</html:select>
							<html:hidden name="PatientTreatmentDetailFB" property="endDate" value=""/>
							<html:hidden  name="PatientTreatmentDetailFB" property="rxContinueFlag" value="<%=extTretDtlVO.getRxContinueFlag() %>"/>
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="center">
						<html:text name="PatientTreatmentDetailFB" property="selExtDays" maxlength="2" size="4" tabindex="1" 
						value="<%=extTretDtlVO.getDays() %>" onkeypress="return validateIntegerOnly(this,event)" ></html:text>
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="center">
						<html:text name="PatientTreatmentDetailFB" property="selExtStartDay" maxlength="2" size="4" tabindex="1" 
						value="<%=extTretDtlVO.getStartDate() %>" onkeypress="return validateIntegerOnly(this,event)"></html:text>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="center">
						<html:text name="PatientTreatmentDetailFB" property="selExtInstructions" maxlength="500" size="25" tabindex="1" 
						value="<%=extTretDtlVO.getRemarks() %>" onkeypress="return validateAlphaNumOnly(this,event)" onkeyup="goToNextIndex(event);"></html:text>
					</div>
				</td>
				<td width="5%" class="tdfont" id="tdButtonPlus">
					<%if(countNewExt==1){%>
						<img class="button" id="addButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateExtTreatmentRows(),'ADDEXTTREATMENTROW');" onclick="submitFormOnValidate(validateExtTreatmentRows(),'ADDEXTTREATMENTROW');" >
					<% } else { %>
						<img class="button" id="deleteButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/minus.gif"/>' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) deleteExtRow('DELETEEXTROW',<%=i %>)" onclick="deleteExtRow('DELETEEXTROW',<%=i %>)">
					<%} %>
				</td>
			</tr>
			</logic:iterate>
		</table>
		</his:ContentTag>	
</body>
</html>