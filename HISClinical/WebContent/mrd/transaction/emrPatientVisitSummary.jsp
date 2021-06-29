
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%try{ %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="mrd.MrdConfig"%>
<%@page import="hisglobal.vo.EpisodeVO"%>
<%@page import="hisglobal.vo.PatAdmissionDtlVO"%>
<%@page import="hisglobal.vo.EpisodeRefDtlVO"%>
<%@page import="registration.RegistrationConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>	

<script type="text/javascript"><!--
	function showLegends(){
  document.getElementById("divLegends").style.display=""; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}

function createDiv(){
	var divElem=document.createElement('div')
	divElem.id="div1"
	document.body.appendChild(divElem);	
	divElem.innerHTML="<div id='progressNoteDiv' style='display:none;position:absolute; " +
					"width:250px; height:22px; z-index:2; left:340px; top:180px;'>"+
					"<table width='100%'><tr><td width='100%' id='progressNoteTdId' " +
					" style='background-color:#E0EBEB;border-top: outset black " + 
					"2px;border-bottom: inset black 2px;border-left: outset black 2px;" +
					"border-right: inset black 2px;'></td></tr></table></div>"
	<%--
	divElem.innerHTML="<div id='progressNoteDiv' style='display:none;position:absolute; " +
					"width:250px; height:22px; z-index:2; left:340px; top:180px;'><table width='100%' ><tr>" +
					"<td id='progressNoteTdId' width='100%' style='background-color:#E0EBEB;border-top: outset black " + 
					"2px;border-bottom: inset black 2px;border-left: outset black 2px;border-right: inset black 2px;'>"+
					"</td></tr></table></div>";
			--%>	
	//document.body.appendChild(divElem);			
}

function showPopupDiv(mode,elem,divId,index){
	if(document.getElementById("progressNoteDiv")==null){
		createDiv()
	}
	
	if(mode=='show'){
		//value="dfjdskl flkgjflk lkghsjdfl hglfkdjshg lkdfjlkhg fdlkjhl fdklhjfd lhkjdflkjh lkfdj hlkfdlkjh lkdfjh lkfdjlkh fdlkj hlkdfjhlk dfjlkh jdflkjh ldfkjhlkfdjhlk dhlkjdflkhj"
		document.getElementById("progressNoteDiv").style.display=""
		value=document.getElementsByName(elem)[index].value
		value="<div align='left'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> "+ value + "</font></div>"
		document.getElementById("progressNoteTdId").innerHTML=value
		
		var left=0
		var top=0
		var td=document.getElementById(divId)
		while(td!=null){
			left+=td.offsetLeft
			top+=td.offsetTop
			td=td.offsetParent
		}
		//alert("left "+left)
		//alert("top "+top)
		
		document.getElementById("progressNoteDiv").style.top= (top+15)
		document.getElementById("progressNoteDiv").style.left= left
	}
	else{
		document.getElementById("progressNoteDiv").style.display="none"
	}	
}	
	
function sortOnDepartmentUnit(mode){

	document.getElementsByName("hmode")[0].value="SORTONDEPTUNIT"
	document.getElementsByName("sortOn")[0].value=mode
	document.forms[0].submit();
}	
--></script>
	


<html:form action="/emrDesk">
<%int index=0; %>
	<body>
		<jsp:include page="/mrd/transaction/emrHeader.jsp" flush="true"></jsp:include>
		
		<his:TitleTag name="Patient Visit Summary">
		</his:TitleTag>
		<his:ContentTag>

			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					
					<td width="10%" class="tdfonthead" onclick="sortOnDepartmentUnit('entryDate')" 
						style="border-top: outset black 2px;border-bottom: inset black 2px;cursor: default;"
						title="Click here to Sort" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="visitDate"/>
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead" onclick="sortOnDepartmentUnit('departmentUnit')" 
						style="border-top: outset black 2px;border-bottom: inset black 2px;cursor: default;"
						title="Click here to Sort">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="visit"/> <bean:message key="department"/>
							</font>
						</div>
					</td>
					
					<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="visit"/> <bean:message key="type"/>
							</font>
						</div>
					</td>
					
					<td width="5%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="visitNo"/>
							</font>
						</div>
					</td>
					
					<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="keyword"/>
							</font>
						</div>
					</td>
					
					<td width="17%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="progressNotes"/>
							</font>
						</div>
					</td>
					
					<td width="18%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="summary"/>
							</font>
						</div>
					</td>
					
					<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="refer/Admission"/>
							</font>
						</div>
					</td>
					
				</tr>
				<%EpisodeVO[] episodeVOs=(EpisodeVO[])session.getAttribute(MrdConfig.EPISODE_VO_ARRAY); 
				pageContext.setAttribute("episodeVOs",episodeVOs);%>
				<%PatAdmissionDtlVO[] patAdmissionDtlVOs=(PatAdmissionDtlVO[])session.getAttribute(MrdConfig.PAT_ADMISSION_DTL_VO_ARRAY); 
				pageContext.setAttribute("patAdmissionDtlVOs",patAdmissionDtlVOs);%>
				<%EpisodeRefDtlVO[] episodeRefDtlVOs=(EpisodeRefDtlVO[])session.getAttribute(MrdConfig.EPISODE_REF_DTL_VO_ARRAY); 
				pageContext.setAttribute("episodeRefDtlVOs",episodeRefDtlVOs);%>
				<logic:present name="<%=MrdConfig.EPISODE_VO_ARRAY %>" >
				<logic:iterate name="<%= MrdConfig.EPISODE_VO_ARRAY%>" id="episodeVO" type="hisglobal.vo.EpisodeVO">
					<c:set var="color" value="#000000"></c:set>
					<c:set var="bgColor" value="background-color:#F1ECE2"></c:set>
					<c:if test="${episodeVO.episodeVisitNo>1}">
						<c:set var="bgColor" value="background-color:#E0EBEB;">	
						</c:set>
					</c:if>  
					<%if(episodeVO.getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_IPD)){ %>
						 <c:set var="bgColor" value="background-color:#FFC0CB;">	
						</c:set>
					<%} %>
					<tr>
						<td class="tdfont" style="${bgColor}" >
							<div align="center">
							  <b>
								<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="episodeVO" property="entryDate"/>
								</font>
								</b>
							</div>
						</td>
						<td class="tdfont" style="${bgColor}" >
							<div align="center">
							  <b>
								<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="episodeVO" property="departmentUnit"/>
								</font>
								</b>
							</div>
						</td>
						<%//boolean flag=false; %>
						<c:set var="flag" value="${false}"> </c:set>
						<td class="tdfont" style="${bgColor}" >
							<div align="center">
							<b>
								<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif">
									<%String visitType=MrdConfig.EPISODE_VISIT_TYPE[Integer.parseInt(episodeVO.getEpisodeVisitType())];%>
									<%=visitType %>
								</font>
								</b>
							</div>
						</td>
						<td class="tdfont" style="${bgColor}" >
							<div align="center">
							<b>
								<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="episodeVO" property="episodeVisitNo"/>
								</font>
								</b>
							</div>
						</td>
						<td class="tdfont" style="${bgColor}" >
							<div align="center" id="keyWordDiv<%=index%>">
							<input type="hidden" name="keyword" value="<bean:write name="episodeVO" property="episodeName"/>">
							<b>
								<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<a onmouseover="showPopupDiv('show','keyword','keyWordDiv<%=index%>','<%=index %>')" 
										onmouseout="showPopupDiv('hide','','','')" style="cursor:default" tabindex="1" 
											onfocus="showPopupDiv('show','keyword','keyWordDiv<%=index%>','<%=index %>')"
											onblur="showPopupDiv('hide','','','')">
									<c:if test="${episodeVO.episodeName!=null}">
									<%=episodeVO.getEpisodeName().substring(0,(episodeVO.getEpisodeName().length()>10?10:episodeVO.getEpisodeName().length()))+"...."%>
									</c:if>
								</a>
								</font>
								</b>
							</div>
						</td>
						<td class="tdfont" style="${bgColor}" >
							<div align="center" id="progressNoteDivId<%=index %>">
								<b>
								<%String progressNote=episodeVO.getComplainDetail(); %>
								<%//System.out.println(progressNote); %>
								<input type="hidden" name="progressNote" value="<%=progressNote%>">
								<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<a onmouseover="showPopupDiv('show','progressNote','progressNoteDivId<%=index %>','<%=index %>')" 
										onmouseout="showPopupDiv('hide','','','')" style="cursor:default" tabindex="1">
									<c:if test="${episodeVO.complainDetail!=null}">
									<%=episodeVO.getComplainDetail().substring(0,(episodeVO.getComplainDetail().length()>10?10:episodeVO.getComplainDetail().length()))+"...."%>
									</c:if>
								</a>
								</font>
								</b>
							</div>
						</td>
						<td class="tdfont" style="${bgColor}" >
							<div align="center" id="episodeSummaryDivId<%=index %>">
								<b>
								<%String episodeSummary=episodeVO.getEpisodeSummary(); %>
								<input type="hidden" name="episodeSummary" value="<%=episodeSummary%>">
								<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<a onmouseover="showPopupDiv('show','episodeSummary','episodeSummaryDivId<%=index %>','<%=index %>')" 
										onmouseout="showPopupDiv('hide','','','')" style="cursor:default" tabindex="1">
									<c:if test="${episodeVO.episodeSummary!=null}">
									<%=episodeVO.getEpisodeSummary().substring(0,(episodeVO.getEpisodeSummary().length()>10?10:episodeVO.getEpisodeSummary().length()))+"...."%>
									</c:if>
								</a>
								</font>
								</b>
							</div>
						</td>
						
						<td class="tdfont" style="${bgColor}" >
							<div align="center">
								<%//flag=false;%>
								<c:set var="flag" value="${false}"></c:set>
								<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif">
									<c:forEach items="${patAdmissionDtlVOs}" var="patAdmissionDtlVO">
									<%//if(!flag) {%>
									<c:if test="${flag==false}">
									<c:if test="${patAdmissionDtlVO.episodeCode==episodeVO.episodeCode && patAdmissionDtlVO.episodeVisitNo==episodeVO.episodeVisitNo}">
										Admission (<c:out value="${patAdmissionDtlVO.patAdmNo}"></c:out>)
<!--										<c:if test="${patAdmissionDtlVO.disDateTime!=null}">-->
<!--											(Discharge) <c:out value="${patAdmissionDtlVO.disDateTime}"></c:out>-->
<!--										</c:if>-->
										<%//flag=true; %>
										<c:set var="flag" value="${true}"> </c:set>
										</c:if>
									</c:if>
									<%//}%>
								</c:forEach>
								</font>
								<%//flag=false;%>
								<c:set var="flag" value="${false}"> </c:set>
								<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif">
									<c:forEach items="${episodeRefDtlVOs}" var="episodeRefDtlVO">
									<c:if test="${flag==false}">
									<%//if(!flag) {%>
									<c:if test="${episodeRefDtlVO.episodeCode==episodeVO.episodeCode && episodeRefDtlVO.episodeVisitNo==episodeVO.episodeVisitNo}">
										Refer To <c:out value="${episodeRefDtlVO.toDepartment}"></c:out>
										<c:if test="${(episodeRefDtlVO.toDepartmentUnit)!=null}">
											(<c:out value="${episodeRefDtlVO.toDepartmentUnit}"></c:out>)
										</c:if>
										<%//flag=true; %>
										<c:set var="flag" value="${true}"> </c:set>
									</c:if>
									</c:if>
									<%//}%>
								</c:forEach>
								</font>
								<%//flag=false;%>
								<c:set var="flag" value="${false}"> </c:set>
								<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif">
									<c:forEach items="${episodeRefDtlVOs}" var="episodeRefDtlVO">
									<%//if(!flag) {%>
									<c:if test="${flag==false}">
									<c:if test="${episodeRefDtlVO.toDepartmentUnitCode==episodeVO.departmentUnitCode && episodeVO.isReferred==1}">
										Refer From <c:out value="${episodeRefDtlVO.fromDepartment}"></c:out>
										<c:if test="${(episodeRefDtlVO.fromDepartmentUnit)!=null}">
											(<c:out value="${episodeRefDtlVO.fromDepartmentUnit}"></c:out>)
										</c:if>
										<%//flag=true; %>
										<c:set var="flag" value="${true}"> </c:set>
									</c:if>
									</c:if>
									<%//}%>
								</c:forEach>
								</font>
							</div>
						</td>
					
					</tr>
					<%index++; %>
				</logic:iterate>
			</logic:present>
			</table>
			
			
		</his:ContentTag>		
	
	<%if(index==0){ %>
		<his:ContentTag>
		<table width="100%" >
			<tr>
			<td class="tdfont" width="100%">
			 	<div align="center">
				 	<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
					<b>	No Detail Found </b>
					</font>
				</div>
			</td>
			</tr>
		</table>
		</his:ContentTag>
	<%} %>
		
		<his:SubTitleTag>
		<his:name>
			<bean:message key="legends"/>
		</his:name>
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td width="70%"> </td>
			<td width="30%">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font><img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' style="cursor: pointer;" onclick="showLegends();">		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font><img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' style="cursor: pointer;" onclick="showLegendsNone();">
			</div>
			</td>			
		</tr>
		</table>
	</his:SubTitleTag>
		<div id="divLegends" style="display:block;">
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
			<tr>
				<td width="10%" style="background-color: #F1ECE2">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 
					</font>
				</td>
				<td width="90%">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						 New Episode
						</font>
					</div>
				</td>				
			</tr>
			<tr>
				<td width="10%" align="center" style="background-color: #E0EBEB">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					</font>
				</td>
				<td width="90%">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Old Visit
					</font>
						</div>
				</td>				
			</tr><!--
			<tr>
				<td width="5%" style="background-color: #FFC0CB" align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					
					</font>
				</td>
				<td width="90%">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						IPD Patient
					</font>
						</div>
				</td>				
			</tr>
		--></table>
	</his:ContentTag>
	</div>
	</body>	
	<his:status/>
</html:form>
<%@include file="/mrd/transaction/emrFooter.jsp"%> 
<%}catch(Exception e){e.printStackTrace();}%>