<%--
##		Modify Date				: 12-03-2015	
##		Reason	(CR/PRS)		: MORE ... buttom was not working [Added moreURL string to pass deskMenuId]
##		Modify By				: Akash Singh
--%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.*"%>
<%@page import="hisglobal.utility.Entry"%>


<%@page import="hisglobal.utility.generictemplate.GenericTemplateConfig"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<his:javascript src="/hisglobal/utility/generictemplate/js/templateValidations.js"/>
<his:javascript src="/emr/dataentry/spp/js/EHR_uni_page_prescription.js" />  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function printAllTemplates(e)
{
	if(!confirmSave())	return;
	var patCrno = document.getElementsByName("patCrNo")[0].value;
	var url="/HISClinical/hisglobal/utility/generictemplate/printingTile.cnt?errorMode=TRY&waterMarkText=CR No.:"+patCrno;
	openPopup(url,e,400,600);
}

function confirmSave()
{
	if(confirm("Data should be Saved before going for PRINT"))
		return true;
	else
		return false;
}
</script>
<body>
<%-- <his:SubTitleTag name="">
	<div align="left"><bean:write name="EHRSection_HistoryFB"  property="deskMenuName"/></div>
</his:SubTitleTag> --%>

<%-- <his:statusTransactionInProcess> --%>
<% 

String deskMenuId= "2";   //new EHRSection_HistoryFB();
List lstActiveTemps=null ;
	System.out.print("*********************"+deskMenuId);
	lstActiveTemps= (List)session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_DEFAULT_TEMPLATE_LIST+deskMenuId) ;
//if(deskMenuId.equals("1"))//"Complaints"
	//lstActiveTemps= (List)session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_DEFAULT_TEMPLATE_LIST_COMPLAINT) ;
	
	//else if(deskMenuId.equals("2"))//"History"
	//	lstActiveTemps= (List)session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_DEFAULT_TEMPLATE_LIST_HISTORY) ;
	
	//else if(deskMenuId.equals("3"))//"Examination"
	//	lstActiveTemps= (List)session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_DEFAULT_TEMPLATE_LIST_EXAMINATION) ;
	List lstInactiveTemps = (List)session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_UNDEFAULT_TEMPLATE_LIST+deskMenuId) ;
	String deskType ="";
	if(session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE)!=null)
		 deskType = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
	else
	deskType =DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK;
	
	if(lstActiveTemps!=null && lstActiveTemps.size()==0 && lstInactiveTemps.size()==0)
	{
%>
	

			<table width="98%" cellspacing="1" cellpadding="0">
				<tr>
				<td width="98%"  class="tdfont">
					&nbsp;&nbsp;&nbsp;<b><font color="#FF0000"> No Template exists for <bean:write name="EHRSection_HistoryFB"  property="deskMenuName"/></font></b>
				</td>
			</tr>
		</table>
	
	<%-- <his:ButtonToolBarTag>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer"  onclick ="submitToDesk('NEW','NEW');" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" >
	</his:ButtonToolBarTag> --%>
<%
	}
	else
	{
%><logic:present name="EHRSection_ComplaintsFB" property="episodeVisitNo">
	<bean:define name="EHRSection_HistoryFB" property="episodeVisitNo" id="visitNo" type="java.lang.String" />
	<%
	if(visitNo!=null && !visitNo.isEmpty()){
		int visit=Integer.parseInt(visitNo);
		if(visit>1 || deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK) || deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
		{
	%>
	
		<table width="98%" cellspacing="1" cellpadding="0">
			<tr>
				<td width="98%"  class="tdfonthead">
					<div align="right">
						<% if(visit>1)	{	%>
							<!-- <a style="cursor:pointer" onclick="submitFormOnValidate(true,'LASTVISIT');" >
								<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									LAST VISIT...
								</font>
							</a>	 -->
						<%	}	%>						
						<%	if(visit>1 || deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK) || deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))	{	%>
							&nbsp;&nbsp;
							<a style="cursor:pointer" onclick="submitFormOnValidate(true,'TEMPREPORT');" >
								<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									PREVIOUS ALL...
								</font>
							</a>
											
						<%	}	%>
					</div>
				</td>
			</tr>
		</table>
	
	<% 
		}}
	%></logic:present>
<!-- 	<table width="98%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="98%"  class="tdfonthead">
				<div align="right">
					<a style="cursor:pointer" onclick="printAllTemplates(event);" >
						<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							PRINT ALL...
							</font>
					</a>
				</div>
			</td>
		</tr>
	</table> -->
	
		<div id="pdfPrintingHTMLData">
		<table width="98%" cellspacing="1" cellpadding="0">
		<%	if(lstActiveTemps!=null){
		Iterator lstActiveTempsItr = lstActiveTemps.iterator();
			while(lstActiveTempsItr.hasNext())
			{
				Entry e = (Entry)lstActiveTempsItr.next();
		%>
			<tr>
				<td width="98%">
					<his:GenericTemplateTag templateId="<%=e.getValue()%>" ></his:GenericTemplateTag>
				</td>
			</tr>
		<%	}}	%>
		</table>
		</div>
		<table width="98%" cellspacing="1" cellpadding="0">
		<%	if(lstInactiveTemps!=null &&  lstInactiveTemps.size()>0)	{	%>
			<tr>
				<td width="98%">
				<% String moreURL="/HISClinical/opd/opdTemplateTab.cnt?hmode=MORETEMPLATE&deskMenuId="+deskMenuId; %>
					<a style="cursor:pointer" onclick="openPopup('<%=moreURL %>',event,300,500);" >
						<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							MORE ...
						</font>
					</a>							
				</td>
			</tr>
		<%	}	%>
		</table>
	
	
		
	<%-- <his:ButtonToolBarTag>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer"   onclick ="submitFormOnValidate(validatePARAMETERCompulsoryField(),'SAVE');" onkeypress="if(event.keyCode==13) submitFormOnValidate(validatePARAMETERCompulsoryField(),'SAVE');" >
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer"  onclick ="submitToDesk('NEW','NEW');" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" >
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer"  onclick ="submitForm('NEW');" onkeypress="if(event.keyCode==13) submitForm('NEW');" >
	</his:ButtonToolBarTag> --%>
<%
	}
%>
<%-- </his:statusTransactionInProcess> --%>

<%-- <html:hidden name="EHRSection_HistoryFB" property="hmode"/> --%>
<html:hidden name="EHRSection_HistoryFB" property="userSeatId"/>
<html:hidden name="EHRSection_HistoryFB" property="wardCode"/>
<html:hidden name="EHRSection_HistoryFB" property="deptUnitCode"/>
<html:hidden name="EHRSection_HistoryFB" property="episodeCode"/>
<html:hidden name="EHRSection_HistoryFB" property="episodeVisitNo"/>
<html:hidden name="EHRSection_HistoryFB" property="admissionNo"/>
<html:hidden name="EHRSection_HistoryFB" property="deskType"/>
<html:hidden name="EHRSection_HistoryFB" property="deskId"/>
<html:hidden name="EHRSection_HistoryFB" property="deskMenuName"/>
<html:hidden name="EHRSection_HistoryFB" property="deskMenuId"/>
<html:hidden name="EHRSection_HistoryFB" property="selUndefaultTemp" value=""/>
<html:hidden name="EHRSection_HistoryFB" property="reportMode"/>
<html:hidden name="EHRSection_HistoryFB" property="isSetHISTORY"/>


<script type="text/javascript">
HISTORYSetDataExist();
</script></body></html>