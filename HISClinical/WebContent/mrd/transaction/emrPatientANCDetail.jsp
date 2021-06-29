 
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:css src="/inpatient/css/anc_style.css"/>


<%@page import="mrd.MrdConfig"%>
<%@page import="inpatient.InpatientConfig"%>
<%@page import="opd.OpdConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<html:form action="/emrDesk">

<jsp:include page="/mrd/transaction/emrHeader.jsp" flush="true"></jsp:include>
<his:TitleTag name="ANC Detail">
</his:TitleTag>
	<%String indexEpisodeOpen="-1"; %>
	<logic:present name="<%=MrdConfig.PAT_ANC_DETAIL_VO_ARRAY%>" >
		<logic:notEmpty name="<%=MrdConfig.PAT_ANC_DETAIL_VO_ARRAY%>" >
	<his:ContentTag>
		<logic:iterate id="ancVOId" indexId="idx" name="<%=MrdConfig.PAT_ANC_DETAIL_VO_ARRAY%>" type="hisglobal.vo.ANCDetailVO">
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="ancno"/>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">&nbsp
					<b>
					<bean:write  name="ancVOId"  property="ancNo" />
					</b>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="gravidano"/>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp
					<b>
					<bean:write  name="ancVOId"  property="gravidaNo" />
					</b>
					</font>	
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="parityno"/>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp
					<bean:write  name="ancVOId"  property="parityNo" />
					</font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="abortusno"/>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp
					<bean:write  name="ancVOId"  property="abortusNo" />
					</font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="detectionMode"/>
						</font>		
					</div>
				</td>
			<logic:present name="ancVOId" property="detectionMethod">
				<logic:equal name="ancVOId" property="detectionMethod" value="<%=InpatientConfig.PREGNANCY_DETECTION_METHOD_UPT%>">
					<td width="25%"  class="tdfont">
						<div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp
							<%=InpatientConfig.PREGNANCY_DETECTION_ARR[Integer.parseInt(InpatientConfig.PREGNANCY_DETECTION_METHOD_UPT)]%>
						</font>
						</div>
					</td>
				</logic:equal>
				<logic:equal name="ancVOId" property="detectionMethod" value="<%=InpatientConfig.PREGNANCY_DETECTION_METHOD_ULTRA_SOUND%>">
					<td width="25%"  class="tdfont">
						<div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp
							<%=InpatientConfig.PREGNANCY_DETECTION_ARR[Integer.parseInt(InpatientConfig.PREGNANCY_DETECTION_METHOD_ULTRA_SOUND)]%>
						</font>
						</div>
					</td>
				</logic:equal>
				<logic:equal name="ancVOId" property="detectionMethod" value="<%=InpatientConfig.PREGNANCY_DETECTION_METHOD_URINE_TEST%>">
					<td width="25%"  class="tdfont">
						<div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp
							<%=InpatientConfig.PREGNANCY_DETECTION_ARR[Integer.parseInt(InpatientConfig.PREGNANCY_DETECTION_METHOD_URINE_TEST)]%>
						</font>
						</div>
					</td>
				</logic:equal>
			</logic:present>
			<logic:notPresent name="ancVOId" property="detectionMethod">
						<td width="25%" class="tdfont">
					
				</td>
			</logic:notPresent>
				
				
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="detectionDate"/>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp
						<bean:write  name="ancVOId"  property="detectionDate" />
					</font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							
								<bean:message key="lmp"/>
							
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp
						<bean:write  name="ancVOId"  property="lmpDate" />
					</font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							
								<bean:message key="edd"/>
							
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp
						<bean:write  name="ancVOId"  property="expectedDeliveryDate" />
					</font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="gestationstrtdt"/>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp
					<bean:write  name="ancVOId"  property="gestationStartDate" />
					</font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="gestationweek"/>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp		
						<bean:write  name="ancVOId"  property="gestationPeriod" />				
					</font></div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							
								<bean:message key="usedd"/>
							
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp
						<bean:write  name="ancVOId"  property="ultraSoundEDD" />
					</font>							
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							
								<bean:message key="isantidgiven"/>
							
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont" valign="middle">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp
							<bean:write  name="ancVOId"  property="isAntiDGiven" />
							
						</font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							
								<bean:message key="queckeningWeek"/>
							
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp
					<bean:write  name="ancVOId"  property="queckeningWeek" />
						</font>
					</div>
				</td>
				<td width="25%" class="tdfonthead"></td>
				<td width="25%" class="tdfont"></td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							
								<bean:message key="contraceptiveused"/>
							
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont" valign="middle">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp
						<bean:write  name="ancVOId"  property="isContraceptiveUsed" />
						</font>
					</div>
				</td>
			<logic:equal name="ancVOId" property="isContraceptiveUsed" value="Yes">
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							
								<bean:message key="contraceptiveRemarks"/>
							
						</font>		
					</div>
				</td>
				<td width="15%" class="tdfont">
							<div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:textarea name="ancVOId" property="contraceptiveRemarks" styleClass="ancTextareaLong" readonly="true"></html:textarea>
								</font>
							</div>
						</td>
				
			</logic:equal>
				<td width="25%" class="tdfonthead"></td>
				<td width="25%" class="tdfont"></td>
			</tr>
			
			
			
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="highriskpreg"/>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp
					<bean:write  name="ancVOId"  property="isHighRiskPregnancy" />
					 </font>
						
					</div>
				</td>
				<logic:present name="ancVOId" property="isHighRiskPregnancy">
					
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									
										<bean:message key="complications"/>
									
								</font>		
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:textarea name="ancVOId" property="complications" styleClass="ancTextareaLong" readonly="true"></html:textarea>
								</font>
							</div>
						</td>
					
			</logic:present>
				<td width="25%" class="tdfonthead"></td>
				<td width="25%"  class="tdfont"></td>
			</tr>
			
			
		</table>
		<%indexEpisodeOpen=idx.toString(); %>
			</logic:iterate>
		</his:ContentTag>
		</logic:notEmpty>
    	</logic:present>	
		<%if(indexEpisodeOpen.equals("-1")){%>
			<his:ContentTag>
				<tr>
		
						<td class="tdfont" width="25%" colspan="5" nowrap>
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>	<bean:message key="noAncDetailsFound" /></b></font></div></td>
					
				</tr>
			</his:ContentTag>
			<%} %>
			
		
	
</html:form>
<%@include file="/mrd/transaction/emrFooter.jsp"%> 