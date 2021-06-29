
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@page import ="opd.*" %>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function submitPage(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function submitMyForm(flag,mode)
{
	if(flag)
	{
		document.getElementsByName('hmode')[0].value=mode;
		submitFormOnValidate(flag,mode);
	}
}

function validateSaveForm()
{
	if(document.forms[0].selectedKeyword.options.length==0)
	{
		alert("Choose at Least One Keyword");
		document.forms[0].selectedKeyword.focus();
		return false;
	}
	MoveToSelected();
	return true;
}

function MoveToSelected()
{
	// Unselect Remaining Keywords
	for(var i=0;i<document.getElementsByName("unselectedKeyword")[0].length;i++)
		document.getElementsByName("unselectedKeyword")[0].options[i].selected=false;
	
	// Select All Keywords in Selected
	for(var i=0;i<document.getElementsByName("selectedKeyword")[0].length;i++)
		document.getElementsByName("selectedKeyword")[0].options[i].selected=true;
}
</script>

<body>

		<html:form action="/master/AddOPDUnitKeywordMaster.cnt">
			<html:hidden  name="UnitEpisodeKeywordFB" property="hmode"/>
			<his:TransactionContainer>
	
		<logic:equal name="UnitEpisodeKeywordFB" property="hmode" value="MODIFY">
			<his:TitleTag name="MODIFY UNIT WISE KEYWORD">
			</his:TitleTag>
			
			
	
	
	<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="50%"  class="tdfonthead"></td>
					<td width="50%"  class="tdfont"></td>
				</tr>
				<tr>
					<td width="50%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b> <bean:message key="unit"/>&nbsp;</b>
							</font>
						</div>
						
				    </td>
					<td width="50%"  class="tdfont">
						<div align="left">
							<html:hidden name="UnitEpisodeKeywordFB" property="deptUnitCode"/>
							&nbsp;<b><bean:write name="UnitEpisodeKeywordFB" property="deptUnitName"/></b>
						</div>
					</td>
				</tr>
				<tr>
					<td width="50%"  class="tdfonthead"></td>
					<td width="50%"  class="tdfont"></td>
				</tr>
			</table>
				
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="40%"  class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>&nbsp;&nbsp;<bean:message key="sel"/> <bean:message key="keywords"/>&nbsp;&nbsp;</b>
							</font>
						</div>
					</td>
					<td width="20%"  class="tdfont"></td>
					<td width="40%"  class="tdfonthead"></td>
				</tr>
				<tr>
					<td width="40%"  class="tdfonthead">
						<div align="center">
							<html:select name="UnitEpisodeKeywordFB" property="unselectedKeyword" multiple="true" size="6" >
								<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_UNSELECTED_KEYWORD_LIST%>" >
								<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_UNSELECTED_KEYWORD_LIST%>" property="episodeKeywordID" labelProperty="episodeKeyword" />
								</logic:present>
							</html:select>
						</div>
					 </td>
						<td width="20%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveSingle(document.forms[0].unselectedKeyword,document.forms[0].selectedKeyword);'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveAll(document.forms[0].selectedKeyword,document.forms[0].unselectedKeyword);'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveSingle(document.forms[0].selectedKeyword,document.forms[0].unselectedKeyword);'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveAll(document.forms[0].unselectedKeyword,document.forms[0].selectedKeyword);'/> 	
							</div>
						</td>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<html:select name="UnitEpisodeKeywordFB" property="selectedKeyword" multiple="true" size="6">
								<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_SELECTED_KEYWORD_LIST%>" >
								<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_SELECTED_KEYWORD_LIST%>" property="episodeKeywordID" labelProperty="episodeKeyword" />
								</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="40%"  class="tdfonthead"></td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%" class="tdfonthead"></td>
					</tr>
				</table>
			</his:ContentTag>				
			<his:ButtonToolBarTag>
				<span id="saveDiv">
					<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitMyForm(validateSaveForm(),'MODIFYSAVE')" onclick="submitMyForm(validateSaveForm(),'MODIFYSAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
				</span>
			</his:ButtonToolBarTag>	
		</logic:equal>
		
		
		
		<logic:equal name="UnitEpisodeKeywordFB" property="hmode" value="VIEW">
			<his:TitleTag name="VIEW UNIT WISE KEYWORD">
			</his:TitleTag>
			
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0" align="center">
				<tr>
					<td width="50%"  class="tdfonthead"  align="center"></td>
					<td width="50%"  class="tdfont"  align="center"></td>
					
				</tr>
				<tr>
					<td width="50%"  class="tdfonthead">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 <b> <bean:message key="unit"/>&nbsp;&nbsp;</b>
						</font>
				    </td>
				    
					<td width="50%"  class="tdfont">
						&nbsp;&nbsp;<b><bean:write name="UnitEpisodeKeywordFB" property="deptUnitName" /></b>
					</td>
				</tr>
				<%int i=0; %>
				<logic:iterate name="<%=OpdConfig.OPD_ESSENTIAL_SELECTED_KEYWORD_LIST%>" id="tempBean" type="hisglobal.vo.EpisodeKeywordsMasterVO">
				<% i++; %>
				<tr>
					<td width="50%"  class="tdfonthead">
						<% if(i==1){%>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="keywords"/>&nbsp;&nbsp;</b>
						</font>
						<%}%>
				    </td>
					<td width="50%"  class="tdfont">&nbsp;&nbsp;
						<b><bean:write name="tempBean" property="episodeKeyword"/></b>
					</td>
				</tr>
				</logic:iterate>
			  	<tr>
			    	<td width="50%"  class="tdfonthead"></td>
					<td width="50%"  class="tdfont"></td>
				</tr>
			</table>
		</his:ContentTag>
						
			<his:ButtonToolBarTag>
				<span id="saveDiv">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
				</span>
			</his:ButtonToolBarTag>	
			</logic:equal>
			
			<html:hidden name="UnitEpisodeKeywordFB" property="slNo"/>
		<center><b><his:status/></b></center>
	</his:TransactionContainer>
	</html:form>
	
	</body>
	