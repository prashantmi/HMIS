<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="java.util.List"%>
<%@page import="opd.OpdConfig"%>

<%@page import="hisglobal.vo.EpisodeKeywordsMasterVO"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value = mode;
	document.forms[0].submit();
}

function closeForm()
{
	self.close();
}

function addKeywords()
{
	var keywords="";
	var separator=", ";
	alert(document.getElementsByName("chkKeywords").length);
	for(var i=0;i<document.getElementsByName("chkKeywords").length;i++)
	{
		if(document.getElementsByName("chkKeywords")[i].checked==true)
			keywords+=document.getElementsByName("chkKeywords")[i].value+separator;
			alert(keywords);
	}
	if(keywords!="")
	{
		keywords = keywords.substring(0,keywords.length-separator.length);
		if(!window.opener.closed)
			opener.<bean:write name="OpdNextVisitDetailFB" property="targetFunction" filter="false"/>keywords);
		closeForm();		
	}
	else
	{
		alert("Select at least One Keyword");
	}	
}
</script>
</head>

<body>

<html:form action="/opdNextVisitDetail">
		<%
			List lstKeywords = (List)session.getAttribute(OpdConfig.OPD_ESSENTIAL_ALL_KEYWORDS_LIST);
			int countInRow = 3;
			if(lstKeywords.size()>0)
			{
		%>
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="10%" class="tdfonthead" colspan="<%=countInRow*2%>" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								Select Keywords
							</font>
						</div>
					</td>
				</tr>
				<%
					for(int i=0;i<lstKeywords.size();i++)
					{
						EpisodeKeywordsMasterVO voEpiKW = (EpisodeKeywordsMasterVO) lstKeywords.get(i);
				%>
					<tr>
						<td class="tdfont">
							<div align="center">
								<input type="checkbox" name="chkKeywords" value="<%=voEpiKW.getEpisodeKeyword()%>" />
							</div>
						</td>
						<td class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=voEpiKW.getEpisodeKeyword() %>
								</font>
							</div>
						</td>
						<%
							for(int j=0;j<countInRow-1;j++)
							{
								i++;	
								if(i<lstKeywords.size())
								{
									voEpiKW = (EpisodeKeywordsMasterVO) lstKeywords.get(i);
						%>
						<td class="tdfont">
							<div align="center">
								<input type="checkbox" name="chkKeywords" value="<%=voEpiKW.getEpisodeKeyword()%>" />
							</div>
						</td>
						<td class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=voEpiKW.getEpisodeKeyword() %>
								</font>
							</div>
						</td>
						<%	
								}
							}
						%>
					</tr>
				<%	} %>
			</table>
		</his:ContentTag>
		<%	} %>
	
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>'  style="cursor:pointer" tabindex="1" onclick ="addKeywords()" onkeypress="if(event.keyCode==13) addKeywords()">&nbsp;&nbsp;
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
		</his:ButtonToolBarTag>

	<his:status/>
</html:form>
</body>
</html>