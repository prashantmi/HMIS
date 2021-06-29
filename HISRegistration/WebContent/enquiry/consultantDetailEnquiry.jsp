<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<%-- 
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/opd/opdJs/opdAjax.js" />
<his:javascript src="/opd/opdJs/opd.js" />
--%>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />

<script>
function submitPage(hmode){
	document.forms[0].hmode.value=hmode;
	document.forms[0].submit();
}
function validateConsultantEnquiry()
{
	submitForm('SEARCH');
}
function showState(){
	var contryCode=document.getElementsByName("gnum_country_code")[0].value;
	var defaltcontryCode="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
	//alert("chcking conuntry code "+contryCode+"  "+defaltcontryCode);
	if(contryCode==defaltcontryCode)
	{
		
		document.getElementById("divpatAddStateCodeCombo").style.display="";
		document.getElementById("divpatAddStateCodeText").style.display="none";
		
		document.getElementById("divpatAddCityLocCode").style.display=""; 
	  	document.getElementById("divpatAddCityLocation").style.display="none"; 
		
	}
	else{
		
		document.getElementById("divpatAddStateCodeCombo").style.display="none";
		document.getElementById("divpatAddStateCodeText").style.display="";
		
		document.getElementById("divpatAddCityLocCode").style.display="none"; 
	  	document.getElementById("divpatAddCityLocation").style.display=""; 
	  
	  	 var elmt= document.getElementsByName("gnum_state_code")[0];   
	  	 elmt.options[elmt.selectedIndex].value="";
	}
	
}

function showLocation(){
// alert("inside  showLocation");
  var elmt= document.getElementsByName("gnum_state_code")[0];
  var contryCode=document.getElementsByName("gnum_country_code")[0].value;
	var defaltcontryCode="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
	//alert("chcking conuntry code "+contryCode+"  "+defaltcontryCode);
	if(contryCode==defaltcontryCode){
  if(elmt.options[elmt.selectedIndex].value!=<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>){  
	  document.getElementById("divpatAddCityLocCode").style.display="none"; 
	  document.getElementById("divpatAddCityLocation").style.display=""; 
	  document.getElementsByName("patAddCityLoc")[0].value="";	 
  }
  else{
	  document.getElementById("divpatAddCityLocCode").style.display=""; 
	  document.getElementById("divpatAddCityLocation").style.display="none";   
  }
  }
}
</script>

<body>
<html:form action="/consultantDetailEnquiry">
	<%@page import="registration.*,enquiry.*"%>


	<his:TitleTag name="Consultant Enquiry">
	</his:TitleTag>

	<his:SubTitleTag name="Consultant Detail">
	</his:SubTitleTag>

	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="20%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><b> <bean:message
					key="fname" /></b></font></div>
				</td>
				<td width="20%" class="tdfont">
				<div align="left"><html:text name="ConsultantDetailEnquiryFB"
					maxlength="30" size="30" styleClass="textbox"
					property="str_first_name" tabindex="1"
					onkeypress="return validateAlphabetsOnly(event,this)" /></div>
				</td>
				<td width="20%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><b> <bean:message
					key="mname" /></b></font></div>
				</td>
				<td width="20%" class="tdfont">
				<div align="left"><html:text name="ConsultantDetailEnquiryFB"
					maxlength="20" size="30" styleClass="textbox"
					property="str_middle_name" tabindex="1"
					onkeypress="return validateAlphabetsOnly(event,this)" /></div>
				</td>
				<td width="20%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><b> <bean:message
					key="lname" /></b></font></div>
				</td>
				<td width="20%" class="tdfont">
				<div align="left"><html:text name="ConsultantDetailEnquiryFB"
					maxlength="30" size="30" styleClass="textbox"
					property="str_last_name" tabindex="1"
					onkeypress="return validateAlphabetsOnly(event,this)" /></div>
				</td>
			</tr>
		</table>
	</his:ContentTag>

	<his:SubTitleTag name="Address Detail">
		<table width="100%">
			<tr>
				<td>
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><b> <bean:message
					key="country" /></b></font></div>
				</td>
				<td>
				<div align="left"><html:select
					name="ConsultantDetailEnquiryFB" tabindex="1"
					property="num_cur_country_id" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY %>"
						property="value" labelProperty="label" />
				</html:select></div>
				</td>
				<td>
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><b> <bean:message
					key="state" /></b></font></div>
				</td>
				<td>
				<div id="divpatAddStateCodeCombo" align="left"><html:select
					name="ConsultantDetailEnquiryFB" tabindex="1"
					property="num_cur_state_id" styleClass="regcbo"
					>
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_STATE %>"
						property="value" labelProperty="label" />
				</html:select></div>
				</td>
			</tr>
		</table>
	</his:SubTitleTag>
	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<%--
             <td  class="tdfonthead" width="17%">
	            <div align="right">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="location"/>
	            </font>
	            </div>
             </td> --%>
				<%--      <td  class="tdfont" width="17%">
                <div id="divpatAddCityLocCode" style="">                
	            <html:select name="ConsultantDetailEnquiryFB" tabindex="1" styleClass="regcbo" property="gnum_location_code">
				<html:option value="-1">Select Value</html:option>
				<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION%>" property = "value" labelProperty = "label"/>
	            </html:select>
	            </div>	                    	                    	                    	                    	                    	                    	                    
	            
	            <div id="divpatAddCityLocation" style='display:none'>	                     
				<html:text  name="ConsultantDetailEnquiryFB" property="gstr_city_loc_name" tabindex="1"  maxlength="30" onkeypress="return validateAlphaNumericOnly(event);" styleClass="textbox" />
				</div>
			</td>
			--%>

			</tr>

			<tr>
				<td class="tdfonthead" width="14%">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message
					key="address" /></b> </font></div>
				</td>

				<td class="tdfont" width="17%"><html:text
					name="ConsultantDetailEnquiryFB" tabindex="1"
					property="str_cur_locality" onchange="isAlpha(this,'City')"
					onkeypress="return validateAlphabetsOnly(event,this);" maxlength="30"
					styleClass="textbox" /></td>
				<td class="tdfonthead" width="14%">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message
					key="district" /></b> </font></div>
				</td>
				<td class="tdfont" width="17%"><html:text
					name="ConsultantDetailEnquiryFB" tabindex="1"
					property="num_cur_dist_id" onchange="isAlpha(this,'District')"
					onkeypress="return validateAlphabetsOnly(event,this)" maxlength="30"
					styleClass="textbox" /></td>
				<td class="tdfonthead" width="18%">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message
					key="city" /></b> </font></div>
				</td>

				<td class="tdfont" width="17%"><html:text
					name="ConsultantDetailEnquiryFB" tabindex="1"
					property="num_cur_city_id" onchange="isAlpha(this,'City')"
					onkeypress="return validateAlphabetsOnly(event,this);" maxlength="30"
					styleClass="textbox" /></td>

			</tr>


		</table>

	</his:ContentTag>
	<his:ButtonToolBarTag>

		<img class="button" style="cursor: pointer"
			src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search"
			title="Search" onclick="validateConsultantEnquiry();">
		<img class="button"
			src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"
			style="cursor: pointer"
			onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
			onclick="submitPage('CANCEL');">
		<his:statusTransactionInProcess>
			<img class="button"
				src='<his:path src="/hisglobal/images/btn-clr.png"/>' tabindex="1"
				style="cursor: pointer" onclick="submitForm('NEW')"
				onkeypress="if(event.keyCode==13) submitForm('NEW');">
		</his:statusTransactionInProcess>
	</his:ButtonToolBarTag>
	<his:statusTransactionInProcess>
		<b><strong>Search Result</strong></b>
		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>

					<td class="tdfonthead" width="10%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="SNo" /> </font></div>
					</td>

					<td class="tdfonthead" width="25%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="name" /> </font></div>
					</td>
					<td class="tdfonthead" width="35%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="address" /> </font></div>
					</td>

					<td class="tdfonthead" width="20%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="fatherName" /> </font></div>
					</td>
				</tr>
				<logic:iterate id="consultantDetailVo" indexId="idx"
					name="<%=enquiryConfig.CONSULTANT_DETAIL_VO_ARRAY %>"
					type="hisglobal.vo.ConsultantDetailVO">
					<tr>

						<td class="tdfont">
						<div align="center"><%=idx.intValue() + 1%></div>
						</td>
						<td class="tdfont">
						<div align="center"><%=consultantDetailVo.getStr_first_name() + " "
							+ consultantDetailVo.getStr_middle_name() + " "
							+ consultantDetailVo.getStr_last_name()%></div>
						</td>
						<td class="tdfont">
						<%
									String address = "";
									if (consultantDetailVo.getPsrstr_add1() != null)
								address = address + consultantDetailVo.getPsrstr_add1();
									if (consultantDetailVo.getNum_cur_city_id() != null)
								address = address + ","
										+ consultantDetailVo.getNum_cur_city_id();
									if (consultantDetailVo.getNum_cur_state_id() != null)
								address = address + ","
										+ consultantDetailVo.getNum_cur_state_id();
									if (consultantDetailVo.getNum_cur_country_id() != null)
								address = address + ","
										+ consultantDetailVo.getNum_cur_country_id();
						%>

						<div align="center"><%=address%></div>
						</td>

						<td class="tdfont">
						<div align="center"><%=consultantDetailVo.getPatfathername()%>
						</div>
						</td>
					</tr>
				</logic:iterate>
			</table>
		</his:ContentTag>
	</his:statusTransactionInProcess>
	<html:hidden name="ConsultantDetailEnquiryFB" property="hmode" />
</html:form>
<his:status />
</body>
</html>