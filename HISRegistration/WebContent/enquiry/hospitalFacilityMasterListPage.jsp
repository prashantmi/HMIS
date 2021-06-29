<%@page import="enquiry.enquiryConfig"%>
<%@page import="enquiry.vo.*"%>
<%@page import="hisglobal.hisconfig.*"%>
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
		<his:SubTitleTag name="">
		<table width="100%">
			<tr>
				<td width="90%">
					<div align="right">
						<font color="#ffffff"
						size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="isActive"/></b></font>
					</div>
				</td>
				<td width="10%">
					<div align="left">
						<html:select name="hospitalFacilityMasterFB" property="isValid" onchange="getFacilityList(this)">
							<html:option value="<%=Config.IS_VALID_ACTIVE %>">Active</html:option>
							<html:option value="<%=Config.IS_VALID_INACTIVE %>">In Active</html:option>
						</html:select>
					</div>
				</td>
			</tr>
		</table>
		</his:SubTitleTag>	
		
	<his:statusList>
	<his:ContentTag>
		
	<table width="100%" cellspacing="1" cellpadding="0">
	<tr>
		<td width="10%" class="tdfonthead">
			<div align="left">
				<b><bean:message key="select"/></b>
<!--				<html:checkbox name="hospitalFacilityMasterFB" property="selectedFaclityId"> </html:checkbox>-->
			</div>
		</td>
		<td width="30%" class="tdfonthead">
			<div align="left">
			<b><bean:message key="facilityName"/></b>
			</div>
		</td>
	
	<td width="60%" class="tdfonthead">
		<div align="left">
		<font color="#00000"/>
		<b><bean:message key="facilityDesc"/></b>
		</font>
		</div>
	</td>
	</tr>
	<logic:iterate id="hospitalFacilityMasterVO" name="<%=enquiryConfig.HOSPITAL_FACILITY_MST_VO_LIST %>" type="enquiry.vo.HospitalFacilityMasterVO" >
		<tr >
			<td width="10%" class="tdfont">
				<div align="left">
					<html:checkbox name="hospitalFacilityMasterFB" property="selectedFaclityId" value="<%=hospitalFacilityMasterVO.getFacilityId() %>" onclick="setFacilityId(this)"> </html:checkbox>
				</div>
			 </td>
			<td width="30%" class="tdfont">
			<div align="left">
				<bean:write name="hospitalFacilityMasterVO" property="facilityName"/>
			</div>
			 </td>
			<td width="60%" class="tdfont">
			<div align="left">
				<bean:write name="hospitalFacilityMasterVO" property="facilityDesc"/>
			</div>
			 </td>
		</tr>
		</logic:iterate>
	</table>
	</his:ContentTag>
	</his:statusList>
	
	<html:hidden name="hospitalFacilityMasterFB" property="hmode"/>
	<html:hidden name="hospitalFacilityMasterFB" property="facilityId"/>

</html:form>

<his:ButtonToolBarTag>

	<img class="button" src='<his:path src="/hisglobal/images/btn-add.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('ADD');" tabindex="1" onclick="submitPage('ADD');">
	<img class="button" src='<his:path src="/hisglobal/images/btn-mo.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('MODIFY');" tabindex="1" onclick="submitPage('MODIFY');">
	<img class="button" src='<his:path src="/hisglobal/images/btn-del.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('DELETE');" tabindex="1" onclick="submitPage('DELETE');">
	<img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('VIEW');" tabindex="1" onclick="submitPage('VIEW');">
	<img class="button" src='<his:path src="/hisglobal/images/ChangOrder.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('CHANGEORDER');" tabindex="1" onclick="submitPage('CHANGEORDER');">
	<!--<input type="button" value="Change Order" onclick="submitPage('CHANGEORDER')"/> 
--></his:ButtonToolBarTag>
<his:status/>
</his:TransactionContainer>
</body>
</html>