<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.utility.generictemplate.GenericTemplateConfig"%>
<%@page import="opd.OpdConfig"%>
<%@page import="java.util.Map"%>
<%@page import="hisglobal.vo.TemplateMasterVO"%>

<%@page import="java.util.Iterator"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function validateGoGetTemplate()
{
	if('<bean:write name="EHRSection_ComplaintsFB" property="reportMode"/>' == '<%=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE%>')
	{
		var selTemps = document.getElementsByName('selectedTemplates');
		var count=0;
		for(var i=0;i<selTemps.length;i++)
			if(selTemps[i].checked)
				count++;
		if(count==0)
		{
			alert("Select at Least One Template ");
			selTemps[0].focus();
			return false;
		}
	}
	else if('<bean:write name="EHRSection_ComplaintsFB" property="reportMode"/>' == '<%=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_PARAMETER_WISE%>')
	{
		var selParas = document.getElementsByName('selectedParas');
		var count=0;
		for(var i=0;i<selParas.length;i++)
			if(selParas[i].checked)
				count++;
		if(count==0)
		{
			alert("Select at Least One Parameter ");
			selParas[0].focus();
			return false;
		}
	}	
	return true;
}

function submitForm1(mode)
{
  	 //alert(mode);
     document.getElementsByName("hmode")[0].value='GOGETREPORT';
	 document.forms[0].submit();
	 
}

function GoGetTemplate()
{

	$.ajax({ 
    url: "/HISClinical/emr/ehrComposition_COMPLAINTS.cnt?hmode=GOGETREPORT",
    type : 'POST',
     async: false,
	success: function(data) {
    	
    	
      }

}); 
}
function selUnselTempPara(evnt,tempChk)
{
	var tblParas = document.getElementById('tblParas#'+tempChk.value);
	var elemParas = tblParas.getElementsByTagName("INPUT");	
	for(var i=0;i<elemParas.length;i++)
		if(tempChk.checked)
			elemParas[i].checked=true;
		else
			elemParas[i].checked=false;
}
</script>

<%-- <his:TitleTag name='Template Report'>
</his:TitleTag> --%>
<form id="formTemplate" action="/ehrComposition_COMPLAINTS" method="POST" >	
<his:statusTransactionInProcess>
	<%-- <his:ContentTag>
 		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead"></td>
				<td width="75%" class="tdfont"></td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="repMode"/>
					</font>
				</td>
				<td width="75%" class="tdfont">
					<html:radio name="EHRSection_ComplaintsFB" property="reportMode" value="<%=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE %>" onchange="submitForm('CHANGEREPORTMODE');"></html:radio>
					<bean:message key="tempWise"/> &nbsp;&nbsp;&nbsp;&nbsp;
					<html:radio name="EHRSection_ComplaintsFB" property="reportMode" value="<%=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_PARAMETER_WISE %>" onchange="submitForm('CHANGEREPORTMODE');"></html:radio>
					<bean:message key="paraWise"/>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead"></td>
				<td width="75%" class="tdfont"></td>
			</tr>
		</table>
	</his:ContentTag> --%>
	<logic:equal name="EHRSection_ComplaintsFB" property="reportMode" value="<%=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE %>">
		<%-- <his:SubTitleTag name="">
			<div align="left"><bean:message key="seltemp"/></div>	
		</his:SubTitleTag> --%>
		<his:ContentTag>
 			<table width="100%" cellspacing="1" cellpadding="0">
			<%
				Map map = (Map)session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_DTL_MAP);
				Iterator mapKeyItr = map.keySet().iterator();
				while(mapKeyItr.hasNext())
				{
					String key = (String)mapKeyItr.next();
					TemplateMasterVO vo = (TemplateMasterVO)map.get(key);
			%>
	 			<tr>
					<td width="30%" class="tdfonthead">
						<div align="right">
							<input type="checkbox" name="selectedTemplates" value="<%=vo.getTemplateId()%>" checked="checked" />
						</div>
					</td>
					<td width="70%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>&nbsp;<%=vo.getTemplateName()%> </b>
							</font>
						</div>
					</td>
				</tr>
			<%
				}
			%>
			</table>
		</his:ContentTag>		
	</logic:equal>
	
		
</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
	<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onclick ="submitForm1('GOGETREPORT');" onkeypress="if(event.keyCode==13) submitForm1('GOGETREPORT');" >
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick ="submitForm('NEW');" onkeypress="if(event.keyCode==13) submitForm('NEW');" >
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" onclick ="submitForm('TEMPREPORT');" onkeypress="if(event.keyCode==13) submitForm('TEMPREPORT');" >
</his:ButtonToolBarTag>
</form>
<html:hidden name="EHRSection_ComplaintsFB" property="hmode"/>
<html:hidden name="EHRSection_ComplaintsFB" property="userSeatId"/>
<html:hidden name="EHRSection_ComplaintsFB" property="wardCode"/>
<html:hidden name="EHRSection_ComplaintsFB" property="deptUnitCode"/>
<html:hidden name="EHRSection_ComplaintsFB" property="episodeCode"/>
<html:hidden name="EHRSection_ComplaintsFB" property="episodeVisitNo"/>
<html:hidden name="EHRSection_ComplaintsFB" property="admissionNo"/>
<html:hidden name="EHRSection_ComplaintsFB" property="deskType"/>
<html:hidden name="EHRSection_ComplaintsFB" property="deskId"/>
<html:hidden name="EHRSection_ComplaintsFB" property="deskMenuName"/>
