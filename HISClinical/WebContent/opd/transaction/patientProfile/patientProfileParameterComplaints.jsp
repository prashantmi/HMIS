
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="java.util.Map"%>
<%@page import="hisglobal.vo.TemplateMasterVO"%>

<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="java.util.Iterator"%>
<his:javascript src="/opd/js/generic_patient_profile.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function validateGoGetTemplate()
{
	
	if(('<bean:write name="GenericPatientProfileFB" property="reportMode"/>' != '<%=OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_DATE_WISE%>')&&('<bean:write name="GenericPatientProfileFB" property="reportMode"/>' != '<%=OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_PARAMETER_WISE%>'))
	{
		alert("Please Select Options");
		return false;
	}
	else
	{
		if('<bean:write name="GenericPatientProfileFB" property="reportMode"/>' == '<%=OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_DATE_WISE%>')
		{
			var selTemps = document.getElementsByName('selectedDates');
			var count=0;
			for(var i=0;i<selTemps.length;i++)
				if(selTemps[i].checked)
					count++;
			if(count==0)
			{
				alert("Please Select at Least One Record Date ");
				selTemps[0].focus();
				return false;
			}
		}
		else if('<bean:write name="GenericPatientProfileFB" property="reportMode"/>' == '<%=OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_PARAMETER_WISE%>')
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

function selUnselRecordDate()
{
	// alert("inside select")
	var allDates=document.getElementsByName("selectedAllDates")[0].value;
	var selectedDates=document.getElementsByName("selectedDates").length;
	
	if(document.getElementsByName("selectedAllDates")[0].checked)
	{
		for(var i=0;i<selectedDates;i++)
		{
			document.getElementsByName("selectedDates")[i].checked=true;
		}
	}
	else
	{
		for(var i=0;i<selectedDates;i++)
		{
			document.getElementsByName("selectedDates")[i].checked=false;
		}
	}
	

}

function callThisOnload()
{
		
		
		if(document.getElementsByName("reportMode")[0].value!="")
		{
				if(document.getElementsByName("reportMode")[0].checked)
				{
					var str=document.getElementsByName("complaintsDateWiseCheckFlag")[0].value;
					var arr=str.split("#");
					var chks1=document.getElementsByName('selectedDates');
					//alert(chks.length)
					for(var i=0;i<chks1.length;i++)
					{
						//alert(arr[i]);
						
						if(arr[i]==<%=OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_YES%>)
						{
						//alert("inisde")
								chks1[i].checked=true;
						}
						else
						{
						//alert("else")
						chks1[i].checked=false;
						}
						
						
					}
				}
				else if(document.getElementsByName("reportMode")[1].checked)
				{
					var str=document.getElementsByName("complaintsCheckFlag")[0].value;
					var arr=str.split("#");
					
					var chks=document.getElementsByName('selectedParas');
					//alert(chks.length)
					for(var i=0;i<chks.length;i++)
					{
						//alert(arr[i]);
						
						if(arr[i]==<%=OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_YES%>)
						{
						//alert("inisde")
								chks[i].checked=true;
						}
						else
						{
						//alert("else")
						chks[i].checked=false;
						}
					}
				}	
		}
}

function viewDateWiseComplaints(e,url)
{
	
	openDependentPopup(createFHashAjaxQuery(url),e,650,650,true);
	
}

</script>

<bean:define id="menuURL" name="GenericPatientProfileFB" property="hmode" type="java.lang.String"></bean:define>
<%	String targetHmode = "SET" + "GENERICTEMPLATE"; %>

<bean:define id="menuName" name="GenericPatientProfileFB" property="deskMenuName" type="java.lang.String"></bean:define>

<his:TitleTag name="<%=menuName%>" >
</his:TitleTag>
	
<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="25%" class="tdfonthead"></td>
			<td width="75%" class="tdfont"></td>
		</tr>
		<tr>
			<td width="25%" class="tdfonthead">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="options"/>
				</font>
			</td>
			<td width="75%" class="tdfont">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<html:radio name="GenericPatientProfileFB" property="reportMode" value="<%=OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_DATE_WISE %>" onchange="submitForm('CHANGEREPORTMODE');"></html:radio>
				<bean:message key="datewise"/> &nbsp;&nbsp;&nbsp;&nbsp;
				<%-- <html:radio name="GenericPatientProfileFB" property="reportMode" value="<%=OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_PARAMETER_WISE %>" onchange="submitForm('CHANGEREPORTMODE');"></html:radio>
				<bean:message key="paraWise"/> --%>
				</font>
			</td>
		</tr>
		<tr>
			<td width="25%" class="tdfonthead"></td>
			<td width="75%" class="tdfont"></td>
		</tr>
	</table>
</his:ContentTag>

<his:statusTransactionInProcess>
	<logic:equal name="GenericPatientProfileFB" property="reportMode" value="<%=OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_DATE_WISE %>">
		<his:ContentTag>
 			<table width="100%" cellspacing="1" cellpadding="0">
 				<tr>
					<td width="5%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<input type="checkbox" name="selectedAllDates" onchange="selUnselRecordDate()" />
							</font>	
						</div>
					</td>
					<td width="95%"  class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>&nbsp;
								<bean:message key="reportDate" />
								 </b>
							</font>
						</div>
					</td>
				</tr>
			<%
				List lstVisitDates = (List)session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_VISIT_DATES);
				Iterator lstVisitDatesItr = lstVisitDates.iterator();
				while(lstVisitDatesItr.hasNext())
				{
					Entry visitDate = (Entry)lstVisitDatesItr.next();
					
			%>
		
				<tr>
					<td width="5%" class="tdfont">
						<div align="right">
							<input type="checkbox" name="selectedDates" value="<%=visitDate.getLabel()%>"  />
						</div>
					</td>
					<td width="95%"  class="tdfont">
						<div align="left">
							<font color="#000000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
								  <% String url="/HISClinical/opd/opdPatientProfile.cnt?hmode=VIEWDATEWISECOMPLAINTS&recordDate="+visitDate.getLabel(); %>
									<!-- <a style="cursor: pointer;" onclick="viewDateWiseComplaints(event,'<%=url%>');"> -->
									<b>&nbsp;<%=visitDate.getLabel()%> </b>
								<!-- </a> -->
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
	<logic:equal name="GenericPatientProfileFB" property="reportMode" value="<%=OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_PARAMETER_WISE%>">
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
	<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onclick ="submitFormOnValidate(validateGoGetTemplate(),'<%=targetHmode%>');" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateGoGetTemplate(),'<%=targetHmode%>');" >
<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm21('PROFILEOPTIONS')" onkeypress="if(event.keyCode==13) submitForm21('PROFILEOPTIONS');">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm21('<%=menuURL%>')" onkeypress="if(event.keyCode==13) submitForm21('<%=menuURL%>')">
</his:ButtonToolBarTag>

<html:hidden name="GenericPatientProfileFB" property="hmode"/>

<html:hidden name="GenericPatientProfileFB" property="patCrNo" />
<html:hidden name="GenericPatientProfileFB" property="episodeCode" />
<html:hidden name="GenericPatientProfileFB" property="episodeVisitNo" />
<html:hidden name="GenericPatientProfileFB" property="departmentUnitCode" />
<html:hidden name="GenericPatientProfileFB" property="wardCode" />
<html:hidden name="GenericPatientProfileFB" property="admissionNo" />
<html:hidden name="GenericPatientProfileFB" property="deskType" />
<html:hidden name="GenericPatientProfileFB" property="deskId" />
<html:hidden name="GenericPatientProfileFB" property="profileId" />
<html:hidden name="GenericPatientProfileFB" property="serialNo" />
<html:hidden name="GenericPatientProfileFB" property="entryDate" />
<html:hidden name="GenericPatientProfileFB" property="profileType" />
<html:hidden name="GenericPatientProfileFB" property="dischargeModifyFlag" />
<html:hidden name="GenericPatientProfileFB"	property="profileHeader" />
<html:hidden name="GenericPatientProfileFB" property="accessType" />
<html:hidden name="GenericPatientProfileFB" property="userLevel" />
<html:hidden name="GenericPatientProfileFB" property="remarks" />
<html:hidden name="GenericPatientProfileFB" property="reportMode" />
<html:hidden name="GenericPatientProfileFB" property="complaintsCheckFlag" />
<html:hidden name="GenericPatientProfileFB" property="complaintsDateWiseCheckFlag" />
<html:hidden name="GenericPatientProfileFB" property="selectedMenu" />
<html:hidden name="GenericPatientProfileFB" property="selectedMenuId" />
<html:hidden name="GenericPatientProfileFB" property="diagnosisCheckFlag" />
<html:hidden name="GenericPatientProfileFB" property="reportMode" />
<html:hidden name="GenericPatientProfileFB" property="deskMenuName" />
