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
<his:javascript src="/registration/js/registration.js" />
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/> 
<his:javascript src="/hisglobal/js/util.js"/>  --%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function validateGoGetTemplate()
{
	if('<bean:write name="GenericTemplateTileFB" property="reportMode"/>' == '<%=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE%>')
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
	else if('<bean:write name="GenericTemplateTileFB" property="reportMode"/>' == '<%=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_PARAMETER_WISE%>')
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

<his:TitleTag name='Template Report'>
</his:TitleTag>
	
<his:statusTransactionInProcess>
	<his:ContentTag>
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
					<html:radio name="GenericTemplateTileFB" property="reportMode" value="<%=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE %>" onchange="submitForm21('CHANGEREPORTMODE');"></html:radio>
					<bean:message key="tempWise"/> &nbsp;&nbsp;&nbsp;&nbsp;
					<html:radio name="GenericTemplateTileFB" property="reportMode" value="<%=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_PARAMETER_WISE %>" onchange="submitForm21('CHANGEREPORTMODE');"></html:radio>
					<bean:message key="paraWise"/>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead"></td>
				<td width="75%" class="tdfont"></td>
			</tr>
		</table>
	</his:ContentTag>
	<logic:equal name="GenericTemplateTileFB" property="reportMode" value="<%=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE %>">
		<his:SubTitleTag name="">
			<div align="left"><bean:message key="seltemp"/></div>	
		</his:SubTitleTag>
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
	
	<logic:equal name="GenericTemplateTileFB" property="reportMode" value="<%=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_PARAMETER_WISE%>">
		<his:SubTitleTag name="">
			<div align="left"><bean:message key="selpara"/></div>		
		</his:SubTitleTag>
		<his:ContentTag>
 			<table width="100%" cellspacing="1" cellpadding="0">
			<%
			Map mapTemps = (Map)session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_DTL_MAP);
			Map mapAllTempPara = (Map)session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_PARA_MAP);
			Iterator mapAllTempParaKeyItr = mapAllTempPara.keySet().iterator();
			while(mapAllTempParaKeyItr.hasNext())
			{
				String tempId = (String)mapAllTempParaKeyItr.next();
				Map mapPara = (Map)mapAllTempPara.get(tempId);
				String tempName = ((TemplateMasterVO)mapTemps.get(tempId)).getTemplateName();
				if(mapPara!=null && mapPara.keySet().size()>0)
				{					 
			%>
	 			<tr>
					<td width="10%" class="tdfonthead">
						<div align="right">
							<input type="checkbox" id='tempParaSelect' value='<%=tempId%>' onchange="selUnselTempPara(event,this);"/>
						</div>
					</td>
					<td width="90%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>&nbsp;&nbsp;<%=tempName%></b>
							</font>
						</div>
					</td>
				</tr>
	 			<tr>
					<td colspan="2" width="100%">
			 			<table id='tblParas#<%=tempId%>' width="100%" cellspacing="1" cellpadding="0">
			<%		
					Iterator mapParaKeyItr = mapPara.keySet().iterator();
					while(mapParaKeyItr.hasNext())
					{
						String paraId = (String)mapParaKeyItr.next();
						String paraName = (String)mapPara.get(paraId);
			%>
							<tr>
								<td width="30%" class="tdfont">
									<div align="right">
										<input type="checkbox" name="selectedParas" value='<%=tempId+"#"+paraId%>' />
									</div>
								</td>
								<td width="70%"  class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>&nbsp;<%=paraName%> </b>
										</font>
									</div>
								</td>
							</tr>
			<%
					}
			%>
						</table>
					</td>
				</tr>
			<%				
				}
			}
			%>
			</table>
		</his:ContentTag>		
	</logic:equal>	
</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
	<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onclick ="submitFormOnValidate(validateGoGetTemplate(),'GOGETREPORT');" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateGoGetTemplate(),'GOGETREPORT');" >
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick ="submitForm21('NEW');" onkeypress="if(event.keyCode==13) submitForm21('NEW');" >
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" onclick ="submitForm21('TEMPREPORT');" onkeypress="if(event.keyCode==13) submitForm21('TEMPREPORT');" >
</his:ButtonToolBarTag>

<html:hidden name="GenericTemplateTileFB" property="hmode"/>
<html:hidden name="GenericTemplateTileFB" property="userSeatId"/>
<html:hidden name="GenericTemplateTileFB" property="wardCode"/>
<html:hidden name="GenericTemplateTileFB" property="deptUnitCode"/>
<html:hidden name="GenericTemplateTileFB" property="episodeCode"/>
<html:hidden name="GenericTemplateTileFB" property="episodeVisitNo"/>
<html:hidden name="GenericTemplateTileFB" property="admissionNo"/>
<html:hidden name="GenericTemplateTileFB" property="deskType"/>
<html:hidden name="GenericTemplateTileFB" property="deskId"/>
<html:hidden name="GenericTemplateTileFB" property="deskMenuName"/>
