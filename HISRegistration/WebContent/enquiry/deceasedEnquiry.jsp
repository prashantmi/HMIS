<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="enquiry.enquiryConfig"%>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="mortuary.MortuaryConfig"%>
<%@page import="hisglobal.vo.DeceasedDetailVO"%>

<html>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

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



</script>

	<body>
		<html:form action="/deceasedEnquiry">
			<his:TransactionContainer>
				<his:statusUnsuccessfull>
					<his:SubTitleTag name="Deceased Enquiry">
					</his:SubTitleTag>
				</his:statusUnsuccessfull>
				<his:statusList>
					<his:SubTitleTag name="Deceased Enquiry">
						<div align="right">
							<html:radio name="DeceasedEnquiryFB" property="chkRadioBtn" value="<%=enquiryConfig.DECEASED_TYPE_ALL %>" onclick="submitPage('FILTER')" tabindex="1" ></html:radio>
							<bean:message key="all"/>
							<html:radio name="DeceasedEnquiryFB" property="chkRadioBtn" value="<%=enquiryConfig.DECEASED_TYPE_NORMAL %>" onclick="submitPage('FILTER')" tabindex="1" ></html:radio>
							<bean:message key="normal"/>
							<html:radio name="DeceasedEnquiryFB" property="chkRadioBtn" value="<%=enquiryConfig.DECEASED_TYPE_UNKNOWN %>" onclick="submitPage('FILTER')" tabindex="1" ></html:radio>
							<bean:message key="unknown"/>
							<html:radio name="DeceasedEnquiryFB" property="chkRadioBtn" value="<%=enquiryConfig.DECEASED_TYPE_UNCLAIMED %>" onclick="submitPage('FILTER')" tabindex="1" ></html:radio>
							<bean:message key="unclaimed"/>
						</div>
					</his:SubTitleTag>
				
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<B>
												<bean:message key="deceased"/>
												<bean:message key="name"/>
											</B>
										</font>	
									</div>
								</td>
								<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
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
							</tr>
							<logic:iterate id="deceasedVO" name="<%=enquiryConfig.ENQUIRY_FILTER_DECEASED_DETAIL_VO %>" type="hisglobal.vo.DeceasedDetailVO">
								<tr>
									<td width="40%" class="tdfont" >
										<div align="center">
											<a style="cursor:pointer" onclick="getDeceasedDetail('<%=deceasedVO.getDeceasedNo()%>')" >
												<%=deceasedVO.getPatFirstName() %>
												<%=deceasedVO.getPatMiddleName()==null?"":deceasedVO.getPatMiddleName() %>
												<%=deceasedVO.getPatLastName()==null?"":deceasedVO.getPatLastName() %>
											</a>	
										</div>
									</td>
									<td width="20%" class="tdfont" >
										<div align="center">
											<%=deceasedVO.getPatGender() %>/
											<%=deceasedVO.getPatAge() %>
										</div>
									</td>
									<td width="20%" class="tdfont" >
										<div align="center">
											<%=deceasedVO.getDeathDate() %>
										</div>
									</td>
									<td width="20%" class="tdfont" >
										<div align="center">
											<%=deceasedVO.getReceivedDateTime() %>
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
										<%=genAppVO.getComplexion()==null?"":genAppVO.getComplexion() %>
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
										<%=genAppVO.getHairColorLength()==null?"":genAppVO.getHairColorLength() %>
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
										<%=genAppVO.getEyeColor()==null?"":genAppVO.getEyeColor() %>
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
										<%if(genAppVO.getBodyLooks()!=null) {%>
											<%if(genAppVO.getBodyLooks().equals(MortuaryConfig.DECEASED_BODY_LOOKS_FLAT)) {%>Flat<%} %>
											<%if(genAppVO.getBodyLooks().equals(MortuaryConfig.DECEASED_BODY_LOOKS_PLUMP)) {%>Plump<%} %>
											<%if(genAppVO.getBodyLooks().equals(MortuaryConfig.DECEASED_BODY_LOOKS_LEAN)) {%>Lean<%} %>
										<%}else{ %>
											&nbsp;
										<%} %>
											
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
										<%=genAppVO.getLength()==null?"":genAppVO.getLength()+" Cm" %>
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
										<%=genAppVO.getWeight()==null?"":genAppVO.getWeight()+" Kg" %>
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
										<%=genAppVO.getClothDetails()==null?"":genAppVO.getClothDetails() %>
									</div>
								</td>
							</tr>
						</table>
					</his:ContentTag>
					
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
										<%=strStorage.split("@")[0] %>
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
										<%=strStorage.split("@")[1] %>
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
										<%=strStorage.split("@")[2] %>
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
										<%=strStorage.split("@")[3] %>
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
				</his:statusTransactionInProcess>
				
				<his:ButtonToolBarTag>
					<his:statusList>	
						<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
					</his:statusList>
					<his:statusTransactionInProcess>
						<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('FILTER')" onkeypress="if(event.keyCode==13)submitPage('FILTER')">
					</his:statusTransactionInProcess>
					<his:statusUnsuccessfull>
					<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
				</his:statusUnsuccessfull>	
				</his:ButtonToolBarTag>
				
			</his:TransactionContainer>
			
			<html:hidden name="DeceasedEnquiryFB" property="hmode"/>
			<html:hidden name="DeceasedEnquiryFB" property="deceasedNo"/>
			<html:hidden name="DeceasedEnquiryFB" property="chkRadioBtn"/>
		</html:form>
	</body>
</html>