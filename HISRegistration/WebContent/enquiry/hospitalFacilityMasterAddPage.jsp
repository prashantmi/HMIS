<%@page import="enquiry.enquiryConfig"%>
<%@page import="enquiry.vo.*"%>
<%@page import="hisglobal.hisconfig.*"%>
<%@page import="enquiry.setup.controller.fb.HospitalFacilityMasterFB"%>
<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/enquiry/js/hospitalFacilityMasterAdd.js" />

<body>
<his:TransactionContainer>

<html:form action="/hospitalFacilityMaster">
<his:TitleTag name="Hospital Facility Master">
</his:TitleTag>
	<%! boolean readOnly; %>
	<%this.readOnly=false; %>
	<logic:equal name="hospitalFacilityMasterFB" property="hmode" value="VIEW">
	<%this.readOnly=true; %>
	</logic:equal>	
		
	<his:ContentTag>
		
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="50%" class="tdfonthead">
				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
				<b><bean:message key="facilityName"/></b>
			</td>
			<td width="50%" class="tdfont">
				<html:text  name="hospitalFacilityMasterFB" property="facilityName" size="25" maxlength="100" readonly="<%=this.readOnly %>" ></html:text>					
	
			</td>
		</tr>	
		<tr>
			<td width="50%" class="tdfonthead">
				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
				<b><bean:message key="descType"/></b>
			</td>
			<td width="50%" class="tdfont">
				<html:select name="hospitalFacilityMasterFB" property="facilityDescType" styleClass="regcbo" onchange="showFacilityDesc(this)" disabled="<%=this.readOnly %>">
					<html:option value="1">Textual</html:option>
					<html:option value="2">Bullet</html:option>
				</html:select>		
			</td>
		</tr>
		<tr>
			<td width="50%" class="tdfonthead">
				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
				<b><bean:message key="facilityDesc"/></b>
			</td>
			<td width="50%" class="tdfont">
			  <div id="descArea">
				<html:textarea name="hospitalFacilityMasterFB" property="facilityDesc" readonly="<%=this.readOnly %>"></html:textarea>
			  </div>		
			  <div id="descText" style="display: none">
			    <%String desc=((HospitalFacilityMasterFB)pageContext.findAttribute("hospitalFacilityMasterFB")).getFacilityDesc(); %>
				<html:text name="hospitalFacilityMasterFB" property="facilityDescMultiple" size="25" maxlength="99" readonly="<%=this.readOnly %>" value="<%=desc%>"/>
				<logic:notEqual name="hospitalFacilityMasterFB" property="hmode" value="VIEW">
				<img class="button" src='<his:path src="/hisglobal/images/icn-add.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) addFacilityDesc();" tabindex="1" onclick="addFacilityDesc()">
				</logic:notEqual>
			  </div>
			</td>
		</tr>
		</table>
		<table width="100%" cellspacing="1" cellpadding="0" id="facilityMasterAdd">
			<logic:notEqual name="hospitalFacilityMasterFB" property="hmode" value="ADD">
				<logic:equal name="hospitalFacilityMasterFB" property="facilityDescType" value="2">
				<logic:iterate id="facilityDescArray" name="hospitalFacilityMasterFB" property="desc" indexId="index">
					<tr>
						<td width="50%" class="tdfonthead"></td>
						
						<td width="50%" class="tdfont">
						<html:text name="hospitalFacilityMasterFB" property="facilityDescMultiple" maxlength="99" size="25" readonly="<%=this.readOnly %>" value="<%=facilityDescArray.toString() %>"/>
						<logic:equal name="hospitalFacilityMasterFB" property="hmode" value="MODIFY">
						<img class="button" src='<his:path src="/hisglobal/images/icn-min.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) addFacilityDesc();" tabindex="1" onclick="removeFacilityDesc(<%=index %>)">
						</logic:equal>
						</td>
					</tr>
				</logic:iterate>
			</logic:equal>
			</logic:notEqual>
		</table>
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="50%" class="tdfonthead">
				<b><bean:message key="isEmergencyService"/></b>
			</td>
			<td width="50%" class="tdfont">
				<html:checkbox name="hospitalFacilityMasterFB" property="isEmergencyService" onclick="setEmergencyServiceFlag(this)" disabled="<%=this.readOnly %>"> </html:checkbox>	
			</td>
		</tr>
		<tr>
			<td width="50%" class="tdfonthead">
				<b><bean:message key="isLocationSpecific"/></b>
			</td>
			<td width="50%" class="tdfont">
				<html:select name="hospitalFacilityMasterFB" property="isLocationSpecific" styleClass="regcbo" disabled="<%=this.readOnly %>">
					<html:option value="-1">Select Value</html:option>
				</html:select>		
			</td>
		</tr>
		<tr>
			<td width="50%" class="tdfonthead">
				<b><bean:message key="locationQuery"/></b>
			</td>
			<td width="50%" class="tdfont">
				<html:textarea name="hospitalFacilityMasterFB" property="locationQuery" readonly="<%=this.readOnly %>"></html:textarea>		
			</td>
		</tr>
		<tr>
			<td width="50%" class="tdfonthead">
				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
				<b><bean:message key="isActive"/></b>
			</td>
			<td width="50%" class="tdfont">
				<html:select name="hospitalFacilityMasterFB" property="isValid" styleClass="regcbo" disabled="<%=this.readOnly %>">
					<html:option value="-1">Select Value</html:option>
					<html:option value="<%=Config.IS_VALID_ACTIVE %>">Active</html:option>
					<html:option value="<%=Config.IS_VALID_INACTIVE %>">In Active</html:option>
				</html:select>
			</td>
		</tr>
				
	</table>
</his:ContentTag>
<html:hidden name="hospitalFacilityMasterFB" property="hmode"/>
<html:hidden name="hospitalFacilityMasterFB" property="facilityId"/>
<html:hidden name="hospitalFacilityMasterFB" property="selectedFaclityId"/>
<html:hidden name="hospitalFacilityMasterFB" property="displayOrder"/>
<html:hidden name="hospitalFacilityMasterFB" property="noOfRows"/>
	
</html:form>

<his:ButtonToolBarTag>
	<logic:notEqual name="hospitalFacilityMasterFB" property="hmode" value="MODIFY">
	<logic:notEqual name="hospitalFacilityMasterFB" property="hmode" value="VIEW">
	<img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitForm('SAVE');" tabindex="1" onclick="submitForm('SAVE');">
	</logic:notEqual>
	</logic:notEqual>
	<logic:equal name="hospitalFacilityMasterFB" property="hmode" value="MODIFY">
	<img class="button" src='<his:path src="/hisglobal/images/btn-mo.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitForm('MODIFYSAVE');" tabindex="1" onclick="submitForm('MODIFYSAVE');">
	</logic:equal>
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) cancel();" tabindex="1" onclick="cancel();">
	<logic:notEqual name="hospitalFacilityMasterFB" property="hmode" value="MODIFY">
	<logic:notEqual name="hospitalFacilityMasterFB" property="hmode" value="VIEW">
	<img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) clearForm();" tabindex="1" onclick="clearForm();">
	</logic:notEqual>
	</logic:notEqual>
	
</his:ButtonToolBarTag>
<his:status/>
</his:TransactionContainer>
</body>
</html>