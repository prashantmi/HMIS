
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/popup.js" />
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<%@page import="mrd.MrdConfig"%>
<script type="text/javascript">
function getAllergyDetail(event,path)
{
	openPopup(createFHashAjaxQuery(path),event,300,600);
}
function showLegends(){
  document.getElementById("divLegends").style.display=""; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}

</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<html:form action="/emrDesk">


<jsp:include page="/mrd/transaction/emrHeader.jsp" flush="true"></jsp:include>
<his:TitleTag name="External Investigation Detail">
</his:TitleTag>
<%String indexEpisodeOpen="-1"; %>
<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="20%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											
												<bean:message key="date"/>
											
										</font>
									</div>
								</td>
								<td width="20%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											
												<bean:message key="time"/>
												<bean:message key="timeFormat"/>
											
										</font>
									</div>
								</td>
								<td width="20%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											
												<bean:message key="para"/>
												
										</font>
									</div>
								</td>
								<td width="40%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											
												<bean:message key="value"/>
											
										</font>
									</div>
								</td>
								
							</tr>
							<logic:present name="<%=MrdConfig.PAT_EXT_INVESTIGATION_ARRAY%>" >
							<logic:iterate id="epiExtInvVO" name="<%=MrdConfig.PAT_EXT_INVESTIGATION_ARRAY%>" indexId="index" type="hisglobal.vo.EpisodeExtInvDtlVO">
								<tr>
									<td width="20%" class="tdfont">
										<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif" >
											<%=epiExtInvVO.getRecordDate() %>
											</font>
										</div>
									</td>
									<td width="20%" class="tdfont">
										<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=epiExtInvVO.getRecordTime()  %>
											</font>
										</div>
									</td>
									<td width="20%" class="tdfont">
										<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%//=epiExtInvVO.getParaName() %>
											<bean:write name="epiExtInvVO" property="paraName"/>
											</font>
										</div>
									</td>
									<td width="40%" class="tdfont">
										<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=epiExtInvVO.getParaValue() %>
										</font>
										</div>
									</td>
								</tr>
								<%indexEpisodeOpen=index.toString(); %>
							</logic:iterate>
							<%if(indexEpisodeOpen.equals("-1")){%>
								<his:ContentTag>
									<tr>
														<td class="tdfont" colspan="5" width="25%" nowrap>
														<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
														<bean:message key="noExternalInvestigationFound" /></font></div></td>
									</tr>
									</his:ContentTag>
							<%} %>
							</logic:present>
						</table>	
							<logic:notPresent name="<%=MrdConfig.PAT_EXT_INVESTIGATION_ARRAY%>" >
						<his:ContentTag>
						
									<tr>
														<td class="tdfont" width="100%" nowrap>
														<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
														<bean:message key="noExternalInvestigationFound" /></font></div></td>
									</tr>
					
									</his:ContentTag>
						</logic:notPresent>
					</his:ContentTag>
</html:form>					
<%@include file="/mrd/transaction/emrFooter.jsp"%> 