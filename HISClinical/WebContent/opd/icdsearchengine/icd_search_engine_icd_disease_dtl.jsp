<!-- 
/**
 * @copyright CDAC
 * @developer Pragya Sharma
 */
-->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.icdsearchengine.fb.ICDEngineDiseaseDetailFB"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>ICD Search Engine</title>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/opd/icdsearchengine/css/icdSearchEngine.css"/>

<his:javascript src="/opd/icdsearchengine/js/icdSearchEngine.js"/>
<his:javascript src="/opd/icdsearchengine/js/support.js"/>

</head>

<body>

<html:form action="/icdsearchengine/icdEngineDiseaseDtl">

<%	
	ICDEngineDiseaseDetailFB fb = (ICDEngineDiseaseDetailFB)request.getAttribute("ICDEngineDiseaseDetailFB");	
	String[] arrCode, arrName;
	%>
<table cellpadding="0" cellspacing="0" width="100%">
	<tr><td style="background-color: rgb(255, 255, 255);"></td></tr>
	<tr><td class="ShadedTitleTagImage">
		<table cellpadding="5" cellspacing="0" width="100%">
			<tr><td class="detailHeader">
				<div align="left">
					Disease Detail  :  <bean:write name="ICDEngineDiseaseDetailFB" property="diseaseCode"/>
				</div>
			</td></tr>
		</table>
	</td></tr>
</table>

<logic:notEmpty name="ICDEngineDiseaseDetailFB" property="fullDiseaseName">
	<his:ContentTag>
		<table id="tblDetail" width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="20%" class="dtlhead">
					Disease Code
				</td>
				<td width="80%" class="dtlVal">
					<bean:write name="ICDEngineDiseaseDetailFB" property="diseaseCode"/>
				</td>
			</tr>
			<tr>
				<td width="20%" class="dtlhead">
					Disease Name
				</td>
				<td width="80%" class="dtlVal">
					<bean:write name="ICDEngineDiseaseDetailFB" property="fullDiseaseName"/>
				</td>
			</tr>
			<tr>
				<td width="20%" class="dtlhead">
					Group
				</td>
				<td width="80%" class="dtlVal">
					<bean:write name="ICDEngineDiseaseDetailFB" property="icdGroup"/> 
					(<bean:write name="ICDEngineDiseaseDetailFB" property="icdGroupCode"/>)
				</td>
			</tr>
			<logic:notEmpty name="ICDEngineDiseaseDetailFB" property="haveSubGroups">
			<tr>
				<td width="20%" class="dtlhead">
					Sub Groups
				</td>
				<td width="80%" class="dtlVal">
					<table id="tblDetail" width="100%" cellpadding="0" cellspacing="0">
				<%					
					arrCode = fb.getIcdSubgroupCode(); 
					arrName = fb.getIcdSubgroup();
					for(int i=arrCode.length-1; i>=0; i--)
					{						
				%>
						<tr>
				<%	for(int j=0;j<(arrCode.length-i-1);j++)	{ %>
							<td class="dtlVal">&nbsp;&nbsp;</td>
				<%	} 
					//if(i!=(arrCode.length-1))
					{%>
							<td class="dtlVal">
								<img src='<his:path src="/hisglobal/images/ftv2lastnode.gif"/>'>
							</td>
					<%}%>
							<td class="dtlVal" colspan="<%=i+1%>">
					<%=arrName[i] %> (<%=arrCode[i]%>)
							</td>
						</tr>
				<%	} %>
					</table>
				</td>
			</tr>
			</logic:notEmpty>
			<logic:notEmpty name="ICDEngineDiseaseDetailFB" property="haveSynonyms">
			<tr>
				<td width="20%" class="dtlhead">
					Synonyms
				</td>
				<td width="80%" class="dtlVal">
				<%					
					arrCode = fb.getArrStrSynonymCodes(); 
					arrName = fb.getArrStrSynonyms();
					for(int i=0;i<arrCode.length; i++)
					{
				%>
					<%=arrName[i] %><br>
				<%	} %>
				</td>
			</tr>
			</logic:notEmpty>
			<logic:notEmpty name="ICDEngineDiseaseDetailFB" property="haveIncludes">
			<tr>
				<td width="20%" class="dtlhead">
					Includes
				</td>
				<td width="80%" class="dtlVal">
				<%					
					arrCode = fb.getArrStrIncludeCodes(); 
					arrName = fb.getArrStrIncludes();
					for(int i=0;i<arrCode.length; i++)
					{
				%>
					<%=arrName[i] %><br>
				<%	} %>
				</td>
			</tr>
			</logic:notEmpty>
			<tr>
				<td width="20%" class="dtlhead">
					Disease Type
				</td>
				<td width="80%" class="dtlVal">
					<bean:write name="ICDEngineDiseaseDetailFB" property="diseaseTypeName"/>
				</td>
			</tr>
			<logic:notEmpty name="ICDEngineDiseaseDetailFB" property="haveExcludes">
			<tr>
				<td width="20%" class="dtlhead">
					Excludes
				</td>
				<td width="80%" class="dtlVal">
				<%					
					arrCode = fb.getArrStrExcludeCodes(); 
					arrName = fb.getArrStrExcludes();
					for(int i=0;i<arrCode.length; i++)
					{
				%>
					<%=arrName[i] %> (<a id="ancDtlDisease" name="ancDtlDisease" tabindex="1" class="anchorNormal" onmouseover="mouseOverAnchor(this)" onmouseout="mouseOutAnchor(this)" 
						onclick="seeICDDiseaseDetail('<%=arrCode[i]%>')">
						<%=arrCode[i].split("~")[0]%>
					</a>)<br>
				<%	} %>
				</td>
			</tr>
			</logic:notEmpty>
			<logic:notEmpty name="ICDEngineDiseaseDetailFB" property="haveChronicDiseases">
			<tr>
				<td width="20%" class="dtlhead">
					Chronic Diseases
				</td>
				<td width="80%" class="dtlVal">
				<%					
					arrCode = fb.getArrStrChronicDiseaseCodes(); 
					arrName = fb.getArrStrChronicDiseases();
					for(int i=0;i<arrCode.length; i++)
					{
				%>
					<%=arrName[i] %><br>
				<%	} %>
				</td>
			</tr>
			</logic:notEmpty>
		</table>
	<div id="divMore">
	<!-- 	<table id="tblDetail" width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="100%" class="dtlhead" colspan="2">					
					<a id="ancDtlMore" name="ancDtlMore" tabindex="1" class="anchorNormal" onmouseover="mouseOverAnchor(this)" onmouseout="mouseOutAnchor(this)" 
						onclick="viewMoreDiseaseDtl()">
						More...
					</a>					
				</td>
			</tr>
		</table>
	</div>
	<div id="divMoreDtl" style="display: none;"> -->
		<table id="tblDetail" width="100%" cellpadding="0" cellspacing="1">
			<logic:notEmpty name="ICDEngineDiseaseDetailFB" property="haveChildren">
			<tr>
				<td width="20%" class="dtlhead">
					Sub Diseases
				</td>
				<td width="80%" class="dtlVal">
				<%					
					arrCode = fb.getIcdSubdiseaseCodes(); 
					arrName = fb.getIcdSubdiseases();
					for(int i=0;i<arrCode.length; i++)
					{
				%>
					<a id="ancDtlDisease" name="ancDtlDisease" tabindex="1" class="anchorNormal" onmouseover="mouseOverAnchor(this)" onmouseout="mouseOutAnchor(this)" 
						onclick="seeICDDiseaseDetail('<%=arrCode[i]%>')">
						<%=arrName[i] %> (<%=arrCode[i].split("~")[0]%>)
					</a><br>
				<%	} %>
				</td>
			</tr>
			</logic:notEmpty>
		</table>
	</div>
	</his:ContentTag>
</logic:notEmpty>

<logic:empty name="ICDEngineDiseaseDetailFB" property="fullDiseaseName">
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="100%" class="nofound">
					No Disease Detail is found
				</td>
			</tr>
		</table>
	</his:ContentTag>
</logic:empty>


<div align="right" class="NextPrev">
<logic:equal name="ICDEngineDiseaseDetailFB" property="strIsPrev" value="true">
<a id="ancDtlPrev" name="ancDtlPrev" tabindex="1" class="anchorNormal" onmouseover="mouseOverAnchor(this)" onmouseout="mouseOutAnchor(this)" 
	onclick="prevICDDiseaseDetail()">
	&lt;&lt; Prev
</a>
</logic:equal>
&nbsp;&nbsp;&nbsp;
<logic:equal name="ICDEngineDiseaseDetailFB" property="strIsNext" value="true" >
<a id="ancDtlNext" name="ancDtlNext" tabindex="1" class="anchorNormal" onmouseover="mouseOverAnchor(this)" onmouseout="mouseOutAnchor(this)" 
	onclick="nextICDDiseaseDetail()">
	Next &gt;&gt;
</a>
</logic:equal>
</div>


<his:ButtonToolBarTag>
	<img id="imgClose" name="imgClose" src='<his:path src="/hisglobal/images/close_tab.png"/>' tabindex="1" 
		onclick ="window.close()" onkeypress="if(event.keyCode==13) window.close()">
</his:ButtonToolBarTag>

<html:hidden name="ICDEngineDiseaseDetailFB" property="curIndex" />
<html:hidden name="ICDEngineDiseaseDetailFB" property="strIsNext" />
<html:hidden name="ICDEngineDiseaseDetailFB" property="strIsPrev" />
<html:hidden name="ICDEngineDiseaseDetailFB" property="diseaseCode" />
<html:hidden name="ICDEngineDiseaseDetailFB" property="recordType" />
<html:hidden name="ICDEngineDiseaseDetailFB" property="slNo" />
<html:hidden name="ICDEngineDiseaseDetailFB" property="strDetailMode" />

</html:form>
</body>
</html>