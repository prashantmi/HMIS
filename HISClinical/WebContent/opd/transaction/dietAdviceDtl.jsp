<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:javascript src="/hisglobal/transactionutil/js/master.js"/>

<%@page import="opd.OpdConfig"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
		<logic:notEmpty name="<%=OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DIET_ADVICE_DETAIL_LIST %>">
		<his:SubTitleTag name="Previous Diet Advice Detail">
		</his:SubTitleTag>
		<his:ContentTag>
		<table id="prevDietDtlTableId" width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="dietType" />
							</b>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="startday" />
							</b>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="endday" />
							</b>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="remark" />
							</b>
						</font>
					</div>
				</td>	
			</tr>
			<logic:iterate id="dietDtlVO" indexId="idx" name="<%=OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DIET_ADVICE_DETAIL_LIST%>" type="hisglobal.vo.PatDietAdviceDetailVO">
			<tr>
				<td width="25%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:write name="dietDtlVO" property="dietTypeDesc"/>
							</b>
							</font>		
						</div>
					</td>
					
				<td width="25%" class="tdfont" nowrap="nowrap" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:write name="dietDtlVO" property="startDate"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:write name="dietDtlVO" property="endDate"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="40%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:write name="dietDtlVO" property="remarks"/>
							</b>
						</font>		
					</div>
				</td>
			</tr>
		</logic:iterate>
		</table>
		</his:ContentTag>
		</logic:notEmpty>
		<his:SubTitleTag name="Diet Advice Detail">
		</his:SubTitleTag>
		<his:ContentTag>
		<table id="dietDtlTableId" width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="dietType" />
							</b>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="from" />
							</b>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="dosedays" />
							</b>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="remark" />
							</b>
						</font>
					</div>
				</td>
			</tr>
			<logic:empty name="<%=OpdConfig.PAT_TREATMENT_DTL_TODAY_DIET_DETAIL_LIST %>">
			<tr>
				<td width="10%" class="tdfont">
					<div align="center">
						<html:select name="PatientTreatmentDetailFB" property="dietTypeId" tabindex="1" disabled="false">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.PAT_TREATMENT_DTL_ALL_DIET_TYPE_LIST%>" >
							<html:options collection="<%=OpdConfig.PAT_TREATMENT_DTL_ALL_DIET_TYPE_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</div>
					<html:hidden name="PatientTreatmentDetailFB" property="dietSerialNo" value="" />
				</td>
				<td width="25%" class="tdfont" nowrap="nowrap" >
					<div align="center">
						<html:text name="PatientTreatmentDetailFB" property="dietStartDate" maxlength="100" size="12" readonly="true" tabindex="1"></html:text>
					</div>
				 </td>
				<td width="10%" class="tdfont">
					<div align="center">
						<html:text name="PatientTreatmentDetailFB" property="dietDays" onkeypress="return validateIntegerOnly(this,event)" maxlength="2" size="4" tabindex="1"></html:text>
					</div>
				</td>
				<td width="40%" class="tdfont">
					<div align="center">
						<html:text name="PatientTreatmentDetailFB" property="dietRemark" maxlength="500" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"></html:text>
					</div>
				</td>
				</tr>
				</logic:empty>
				<logic:notEmpty name="<%=OpdConfig.PAT_TREATMENT_DTL_TODAY_DIET_DETAIL_LIST %>">
				<logic:iterate id="dietDtlVO" indexId="idx" name="<%=OpdConfig.PAT_TREATMENT_DTL_TODAY_DIET_DETAIL_LIST%>" type="hisglobal.vo.PatDietAdviceDetailVO">	
				<tr>
					<td width="10%" class="tdfont">
						<div align="center">
							<html:select name="PatientTreatmentDetailFB" property="dietTypeId" tabindex="1" disabled="false"
							value="<%=dietDtlVO.getDietTypeCode() %>">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.PAT_TREATMENT_DTL_ALL_DIET_TYPE_LIST%>" >
								<html:options collection="<%=OpdConfig.PAT_TREATMENT_DTL_ALL_DIET_TYPE_LIST%>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</div>
						<html:hidden name="PatientTreatmentDetailFB" property="dietSerialNo" value="<%=dietDtlVO.getSerialNo() %>"/>
					</td>
					<td width="25%" class="tdfont" nowrap="nowrap" >
						<div align="center">
							<html:text name="PatientTreatmentDetailFB" property="dietStartDate" maxlength="100" size="12" readonly="true" tabindex="1"></html:text>
						</div>
					 </td>
					<td width="10%" class="tdfont">
						<div align="center">
							<html:text name="PatientTreatmentDetailFB" property="dietDays" onkeypress="return validateIntegerOnly(this,event)" maxlength="2" size="4" value="<%=dietDtlVO.getDays() %>" tabindex="1"></html:text>
						</div>
					</td>
					<td width="40%" class="tdfont">
						<div align="center">
							<html:text name="PatientTreatmentDetailFB" property="dietRemark" maxlength="500" value="<%=dietDtlVO.getRemarks() %>" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"></html:text>
						</div>
					</td>
				</tr>
				</logic:iterate>	
				</logic:notEmpty>
		</table>
		</his:ContentTag>
</body>
</html>