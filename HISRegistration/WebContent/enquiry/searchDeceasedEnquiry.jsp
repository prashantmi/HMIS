<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>


<%@page import="registration.RegistrationConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="enquiry.enquiryConfig"%>
<%@page import="mortuary.MortuaryConfig"%>
<%@page import="hisglobal.vo.DeceasedDetailVO"%>
<%@page import="hisglobal.vo.DeceasedHandoverDtlVO"%>

<html>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />

<script type="text/javascript">
function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function getDeceasedDetail(obj)
{
	document.getElementsByName("deceasedNo")[0].value=obj;
	submitPage('GETDECEASEDDTL');
}

function clearForm()
{
	document.getElementsByName("firstName")[0].value="";
	document.getElementsByName("middleName")[0].value="";
	document.getElementsByName("lastName")[0].value="";
	document.getElementsByName("genderCode")[0].value="-1";
	document.getElementsByName("fromDate")[0].value="";
	document.getElementsByName("toDate")[0].value="";
	document.getElementsByName("chkUnknown")[0].checked=false;
	document.getElementsByName("chkUnclaimed")[0].checked=false;
}

function submitSearch()
{
	var fName=document.getElementsByName("firstName")[0].value;
	var mName=document.getElementsByName("middleName")[0].value;
	var lName=document.getElementsByName("lastName")[0].value;
	var genderCode=document.getElementsByName("genderCode")[0].value;
	var frDate=document.getElementsByName("fromDate")[0].value;
	var toDate=document.getElementsByName("toDate")[0].value;
	var chkUnknown=document.getElementsByName("chkUnknown")[0].checked;
	var chkUnclaimed=document.getElementsByName("chkUnclaimed")[0].checked;
	
	if(!chkUnknown)
		document.getElementsByName("chkUnknown")[0].value="";
		
	if(!chkUnclaimed)
		document.getElementsByName("chkUnclaimed")[0].value="";	
		
	
	if(fName=="" && mName=="" && lName=="" && genderCode=="-1" && frDate=="" && toDate=="" && chkUnknown==false && chkUnclaimed==false)
	{
		alert("Please Enetr At Least One Field");
	}
	else
		submitPage('SEARCH');
}


</script>

	<body>
		<html:form action="/searchDeceasedEnquiry">
			<his:TransactionContainer>
				<his:statusDone>
					<his:TitleTag name="Search Deceased">
					</his:TitleTag>
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="17%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="firstName"/>
										</font>
									</div>
								</td>
								<td width="17%" class="tdfont">
									<div align="left">
										<html:text name="SearchDeceasedEnquiryFB" property="firstName" onkeypress="return validateAlphabetsWithDotsOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="17%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="middleName"/>
										</font>
									</div>
								</td>
								<td width="17%" class="tdfont">
									<div align="left">
										<html:text name="SearchDeceasedEnquiryFB" property="middleName" onkeypress="return validateAlphabetsWithDotsOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="16%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="lastName"/>
										</font>
									</div>
								</td>
								<td width="16%" class="tdfont">
									<div align="left">
										<html:text name="SearchDeceasedEnquiryFB" property="lastName" onkeypress="return validateAlphabetsWithDotsOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
							</tr>
							<tr>	
								<td width="17%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="gender"/>
										</font>
									</div>
								</td>
								<td width="17%" class="tdfont">
									<div align="left">
										<html:select name="SearchDeceasedEnquiryFB" property="genderCode" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER %>">
												<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER %>" property = "value" labelProperty = "label"/>
											</logic:present>
										</html:select>
									</div>
								</td>
								<bean:define name="SearchDeceasedEnquiryFB" property="fromDate" id="frDate" type="java.lang.String"/>
								<td width="17%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="fromDate"/>
										</font>
									</div>
								</td>
								<td width="17%" class="tdfont">
									<div align="left">
										<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate %>" />
									</div>
								</td>
								 <bean:define name="SearchDeceasedEnquiryFB" property="toDate" id="toDate" type="java.lang.String"/>
								<td width="16%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="toDate"/>
										</font>
									</div>
								</td>
								<td width="16%" class="tdfont">
									<div align="left">
										<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=toDate %>"/>
									</div>
								</td>
							</tr>
							<tr>
								<td width="17%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="unknown"/>
										</font>
									</div>
								</td>
								<td width="17%" class="tdfont">
									<div align="left">
										<html:checkbox name="SearchDeceasedEnquiryFB" property="chkUnknown" value="1" tabindex="1" ></html:checkbox>
									</div>
								</td>
								<td width="17%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="unclaimed"/>
										</font>
									</div>
								</td>
								<td width="17%" class="tdfont">
									<div align="left">
										<html:checkbox name="SearchDeceasedEnquiryFB" property="chkUnclaimed" value="0" tabindex="1" ></html:checkbox>
									</div>
								</td>
								<td width="16%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										</font>
									</div>
								</td>
								<td width="16%" class="tdfont">
									<div align="left">
									</div>
								</td>
							</tr>
						</table>
					</his:ContentTag>
					
					<his:ButtonToolBarTag>
						<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onclick="submitSearch()" tabindex="1" onkeypress="if(event.keyCode==13) submitSearch()" >
					 	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
			 			<img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' tabindex="1"  style=cursor:pointer onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
					</his:ButtonToolBarTag>
				</his:statusDone>
				
				<his:statusList>
					<his:SubTitleTag name="Search Result">
					</his:SubTitleTag>
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="35%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<B>
												<bean:message key="deceased"/>
												<bean:message key="name"/>
											</B>
										</font>	
									</div>
								</td>
								<td width="12%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<B>
												<bean:message key="gender"/>
												<bean:message key="slash"/>
												<bean:message key="age"/>
											</B>
										</font>	
									</div>
								</td>
								<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<B>
												<bean:message key="deathdate"/>
											</B>
										</font>	
									</div>
								</td>
								<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<B>
												<bean:message key="receiveDate"/>
											</B>
										</font>	
									</div>
								</td>
								<td width="13%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<B>
												<bean:message key="status"/>
											</B>
										</font>	
									</div>
								</td>
							</tr>
							<logic:iterate id="deceasedVO" name="<%=enquiryConfig.ENQUIRY_DECEASED_DETAIL_VO %>" type="hisglobal.vo.DeceasedDetailVO">
								<tr>
									<td width="35%" class="tdfont" >
										<div align="center">
											<a style="cursor:pointer" onclick="getDeceasedDetail('<%=deceasedVO.getDeceasedNo()%>')" >
												<%=deceasedVO.getPatFirstName() %>
												<%=deceasedVO.getPatMiddleName()==null?"":deceasedVO.getPatMiddleName() %>
												<%=deceasedVO.getPatLastName()==null?"":deceasedVO.getPatLastName() %>
											</a>	
										</div>
									</td>
									<td width="12%" class="tdfont" >
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<%=deceasedVO.getPatGender() %>/
												<%=deceasedVO.getPatAge() %>
											</font>
										</div>
									</td>
									<td width="20%" class="tdfont" >
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<%=deceasedVO.getDeathDate() %>
											</font>	
										</div>
									</td>
									<td width="20%" class="tdfont" >
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<%=deceasedVO.getReceivedDateTime() %>
											</font>	
										</div>
									</td>
									<td width="13%" class="tdfont" >
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<%=deceasedVO.getBodyStatusName() %>
											</font>	
										</div>
									</td>
								</tr>
							</logic:iterate>
						</table>
					</his:ContentTag>
				</his:statusList>
			
				<his:statusTransactionInProcess>
					<jsp:include page="/mortuary/deceasedTile.cnt" flush="true" />
			
					<his:SubTitleTag name="General Appearance">
					</his:SubTitleTag>
					
					<%DeceasedDetailVO genAppVO=(DeceasedDetailVO)session.getAttribute(enquiryConfig.DECEASED_GENERAL_APPEARANCE_VO); %>
					<his:ContentTag>
						<table  width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%"  class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="complexion"/>
										</font>
									</div>
								</td>
								<td width="35%"  class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=genAppVO.getComplexion()==null?"":genAppVO.getComplexion() %>
										</font>	
									</div>
								</td>
								<td width="40%" class="tdfont" rowspan="6" >
									<div align="center">
										<table width="50%" border="1" >
											<tr>
												<td>
													<%String imageIndex=Config.REQ_PARAMETER_IMAGE_INDEX; %>
													<%String imagepath="/image/showImage?"+ imageIndex+"=0"; %>
													<img src='<his:path src="<%=imagepath%>"/>' height="100%" width="100%" alt="No Image Found" />
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%"  class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											 <bean:message key="hairColorLength"/>
										</font>
									</div>
								</td>
								<td width="35%"  class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=genAppVO.getHairColorLength()==null?"":genAppVO.getHairColorLength() %>
										</font>	
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%"  class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="eyeColor"/>
										</font>
									</div>
								</td>
								<td width="35%"  class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=genAppVO.getEyeColor()==null?"":genAppVO.getEyeColor() %>
										</font>	
									</div>
								</td>
								
							</tr>
							<tr>
								<td width="25%"  class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="bodyLooks"/>
										</font>
									</div>
								</td>
								<td width="35%"  class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%if(genAppVO.getBodyLooks()!=null) {%>
												<%if(genAppVO.getBodyLooks().equals(MortuaryConfig.DECEASED_BODY_LOOKS_FLAT)) {%>Flat<%} %>
												<%if(genAppVO.getBodyLooks().equals(MortuaryConfig.DECEASED_BODY_LOOKS_PLUMP)) {%>Plump<%} %>
												<%if(genAppVO.getBodyLooks().equals(MortuaryConfig.DECEASED_BODY_LOOKS_LEAN)) {%>Lean<%} %>
											<%}else{ %>
												&nbsp;
											<%} %>
										</font>	
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%"  class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="length"/>
										</font>
									</div>
								</td>
								<td width="35%"  class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=genAppVO.getLength()==null?"":genAppVO.getLength()+" Cm" %>
										</font>	
									</div>
								</td>
								
							</tr>
							<tr>
								<td width="25%"  class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="weight"/>
										</font>
									</div>
								</td>
								<td width="35%"  class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=genAppVO.getWeight()==null?"":genAppVO.getWeight()+" Kg" %>
										</font>	
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%"  class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="clothDetails"/>
										</font>
									</div>
								</td>
								<td width="35%"  class="tdfont" colspan="2">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=genAppVO.getClothDetails()==null?"":genAppVO.getClothDetails() %>
										</font>	
									</div>
								</td>
							</tr>
						</table>
					</his:ContentTag>
					
					<%if(genAppVO.getBodyStatus().equals(MortuaryConfig.BODY_STATUS_HANDOVER)){ %>
						<his:SubTitleTag name="Handover Detail">
						</his:SubTitleTag>
						<%DeceasedHandoverDtlVO handoverVO=(DeceasedHandoverDtlVO)session.getAttribute(enquiryConfig.DECEASED_HANDOVER_DETAIL); %>
						<his:ContentTag>
							<%if(handoverVO.getIsHandoverTo().equals(MortuaryConfig.BODY_HANDOVER_TO_RELATIVE)){ %>
								<table width="100%" border="0"  cellspacing="1" cellpadding="0">
									<tr>
										<td width="25%" class="tdfonthead">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:message key="handoverto"/>
												</font>
											</div>
										</td>
										<td width="25%" class="tdfont">
											<div align="left">
												<%=handoverVO.getIsHandoverToLabel() %>
											</div>
										</td>
										<td width="25%" class="tdfonthead">
										</td>
										<td width="25%" class="tdfont">
										</td>
									</tr>
									<tr>
										<td width="25%" class="tdfonthead">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:message key="relativename"/>
												</font>
											</div>
										</td>
										<td width="25%" class="tdfont">
											<div align="left">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=handoverVO.getHandoverToName() %>
												</font>	
											</div>
										</td>
										<td width="25%" class="tdfonthead">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:message key="realtionship"/>
												</font>
											</div>
										</td>
										<td width="25%" class="tdfont">
											<div align="left">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=handoverVO.getRelativeCode() %>
												</font>
											</div>
										</td>
									</tr>
									<tr>
										<td width="25%" class="tdfonthead">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:message key="relativeaddress"/>
												</font>
											</div>
										</td>
										<td width="25%" class="tdfont">
											<div align="left">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=handoverVO.getHandoverToAddress() %>
												</font>
											</div>
										</td>
										<td width="25%" class="tdfonthead">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:message key="relativeContactNo"/>
												</font>
											</div>
										</td>
										<td width="25%" class="tdfont">
											<div align="left">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=handoverVO.getHandoverToPhone() %>
												</font>	
											</div>
										</td>
									</tr>
								</table>
							<%}else{ %>
								<table width="100%" border="0"  cellspacing="1" cellpadding="0">
									<tr>
										<td width="25%" class="tdfonthead">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:message key="handoverto"/>
												</font>
											</div>
										</td>
										<td width="25%" class="tdfont">
											<div align="left">
												<%=handoverVO.getIsHandoverToLabel() %>
											</div>
										</td>
										<td width="25%" class="tdfonthead">
										</td>
										<td width="25%" class="tdfont">
										</td>
									</tr>
									<tr>
										<td width="25%" class="tdfonthead">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:message key="officer"/>
													<bean:message key="name"/>
												</font>
											</div>
										</td>
										<td width="25%" class="tdfont">
											<div align="left">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=handoverVO.getHandoverToName() %>
												</font>
											</div>
										</td>
										<td width="25%" class="tdfonthead">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:message key="officer"/>
													<bean:message key="designation"/>
												</font>
											</div>
										</td>
										<td width="25%" class="tdfont">
											<div align="left">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=handoverVO.getOfficerDesignation() %>
												</font>	
											</div>
										</td>
									</tr>
									<tr>
										<td width="25%" class="tdfonthead">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:message key="officer"/>
													<bean:message key="batchno"/>
												</font>
											</div>
										</td>
										<td width="25%" class="tdfont">
											<div align="left">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=handoverVO.getOfficerBadgeNo() %>
												</font>	
											</div>
										</td>
										<td width="25%" class="tdfonthead">
										</td>
										<td width="25%" class="tdfont">
										</td>
									</tr>
								</table>
							<%} %>
						</his:ContentTag>
					<%}else{ %>
					<his:SubTitleTag name="Postmortem Status And Storage Detail">
					</his:SubTitleTag>
					
					<his:ContentTag>
					<%String postmortemStatus=(String)session.getAttribute(enquiryConfig.DECEASED_POSTMORTEM_STATUS); %>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="postmortemStatus"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<%=postmortemStatus %>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
								</td>
								<td width="25%" class="tdfont">
								</td>
							</tr>
						</table>
					</his:ContentTag>
					<his:ContentTag>
						<%String strStorage=(String)session.getAttribute(enquiryConfig.DECEASED_STORAGE_DETAIL); 
						if(strStorage!=null){
						%>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="mortuaryName"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=strStorage.split("@")[0] %>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="mortuary"/>
											<bean:message key="areaName"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=strStorage.split("@")[1] %>
										</font>	
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="chamber"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=strStorage.split("@")[2] %>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="rack"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=strStorage.split("@")[3] %>
										</font>	
									</div>
								</td>
							</tr>
						</table>
						<%}else{ %>
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr>
									<td width="100%" class="tdfont">
										<div align="center">
											<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												Deceased Is On Stretcher
											</font>
										</div>
									</td>
								</tr>
							</table>
						<%} %>
					</his:ContentTag>
					<%} %>
					<his:ButtonToolBarTag>
						<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('SEARCH');" tabindex="1" onclick ="submitPage('SEARCH');">
					</his:ButtonToolBarTag>
					<html:hidden name="SearchDeceasedEnquiryFB" property="chkUnknown"/>
					<html:hidden name="SearchDeceasedEnquiryFB" property="chkUnclaimed"/>
				</his:statusTransactionInProcess>
			
			
			
			</his:TransactionContainer>
			<html:hidden name="SearchDeceasedEnquiryFB" property="hmode"/>
			<html:hidden name="SearchDeceasedEnquiryFB" property="deceasedNo"/>
			<html:hidden name="SearchDeceasedEnquiryFB" property="firstName"/>
			<html:hidden name="SearchDeceasedEnquiryFB" property="middleName"/>
			<html:hidden name="SearchDeceasedEnquiryFB" property="lastName"/>
			<html:hidden name="SearchDeceasedEnquiryFB" property="genderCode"/>
			<html:hidden name="SearchDeceasedEnquiryFB" property="fromDate"/>
			<html:hidden name="SearchDeceasedEnquiryFB" property="toDate"/>
			
			
		</html:form>
		<his:status/>
	</body>
</html>			