
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.*"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="opd.transaction.controller.fb.GenericTemplateTileFB"%>

<%@page import="hisglobal.utility.generictemplate.GenericTemplateConfig"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
  <link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">

<his:javascript src="/hisglobal/utility/generictemplate/js/templateValidations.js"/>
<% String deskTy = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
if(!deskTy.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK)) 
{%>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> 
<%}%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function printAllTemplates(e)
{
	if(!confirmSave())	return;
	
	var patCrno = document.getElementsByName("patCrNo")[0].value;
	var url="/HISClinical/hisglobal/utility/generictemplate/printingTile.cnt?errorMode=TRY&waterMarkText=CR No.:"+patCrno;
	openPopup(createFHashAjaxQuery(url),e,400,600);
}

function confirmSave()
{
	if(confirm("Data should be Saved before going for PRINT"))
		return true;
	else
		return false;
}

function submitFormOnValidateForSave(flag,mode)
{
 
 	if(flag)
	{
	
		submitFormForSave(mode);
	}
	else{

		return false;
	}
	
}
function submitFormForSave(mode)
{
    
   //alert("submitform...."+mode);
  
   //  document.getElementsByName("hmode")[0].value=
   //  alert(document.getElementsByName("hmode")[0].value);
   
  // alert(document.forms[0].action);

   document.forms[0].action=document.forms[0].action+"?hmode=SAVETEMPLATEDATASPD";

  // alert(document.forms[0].action);
   
	 document.forms[0].submit();
	 
}

function submitFormForRefresh(mode)
{
    
  // alert("submitform...."+mode);
  
   //  document.getElementsByName("hmode")[0].value=
   //  alert(document.getElementsByName("hmode")[0].value);
   
   //alert(document.forms[0].action);

   document.forms[0].action=document.forms[0].action+"?hmode=CLEARTEMPLATESPD";

  // alert(document.forms[0].action);
   
	 document.forms[0].submit();
	 
}

function closeModal()
{
	$('#myModal_portal').hide();
}
</script>
<style>
.table.table-borderless td, .table.table-borderless th {
    border: 0 !important;
}

.table.table-borderless {
    margin-bottom: 0px;
}
</style>

<%-- <his:SubTitleTag name="">
	<div align="left"><bean:write name="GenericTemplateTileFB"  property="deskMenuName"/></div>
</his:SubTitleTag> --%>
<!--  <form id="formTemplates" action="/opdTemplateTab" method="POST">  -->

 <form id="formTemplates" action="/HISClinical/opd/opdTemplateTab.cnt" method="POST"> 
<his:statusTransactionInProcess>
	<table width="100%" cellspacing="1" cellpadding="0" style="position: fixed;"><tr><td width="100%" align="right"  class="tdfont">
	<logic:equal name="GenericTemplateTileFB" property="isSaveFlag" value="True">
	 <div align="center">
	  <p align="right">
	    <font color="#0000FF">
	      Record Saved Successfully
	    </font>
	  </p>
	 </div>
	</logic:equal>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer"   onclick ="submitFormOnValidateForSave(validatePARAMETERCompulsoryField(),'SAVETEMPLATEDATASPD');" onkeypress="if(event.keyCode==13) submitFormOnValidateForSave(validatePARAMETERCompulsoryField(),'SAVETEMPLATEDATASPD');" >
		<%-- <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer"  onclick ="closeModal();" onkeypress="if(event.keyCode==13) closeModal();" > --%>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer"  onclick ="submitFormForRefresh('CLEARTEMPLATESPD');" onkeypress="if(event.keyCode==13) submitFormForRefresh('CLEARTEMPLATESPD');" >
        <%-- <button class="btn btn-primary btn-sm" id="refreshQuestionnaireId" style="padding-top:0px;margin-top:0px;" data-dismiss="modal" aria-label="Close">Cancel</button>--%>
	</td></tr></table>

<% 

String deskMenuId= ((GenericTemplateTileFB)request.getAttribute("GenericTemplateTileFB")).getDeskMenuId();//new GenericTemplateTileFB();
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
	String deskType = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
	if(lstActiveTemps!=null && lstActiveTemps.size()==0 && lstInactiveTemps.size()==0)
	{
%>
	
	<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
				<td width="100%"  class="tdfont">
					&nbsp;&nbsp;&nbsp;<b><font color="#FF0000"> No Template exists for <bean:write name="GenericTemplateTileFB"  property="deskMenuName"/></font></b>
				</td>
			</tr>
		</table>
	</his:ContentTag>
<%
	}
	else
	{
%>
	<bean:define name="GenericTemplateTileFB" property="episodeVisitNo" id="visitNo" type="java.lang.String" />
	<%
		int visit=Integer.parseInt(visitNo);
		if(visit>1 || deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK) || deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
		{
	%>
	<%-- <his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td width="100%"  class="tdfonthead">
					<div align="right">
						<% if(visit>1)	{	%>
							<!-- <a style="cursor:pointer" onclick="submitFormOnValidate(true,'LASTVISIT');" >
								<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									LAST VISIT...
								</font>
							</a>	 -->
						<%	}	%>						
						 <%	if(visit>1 || deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK) || deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))	{	%>
							&nbsp;&nbsp;   <!-- commented for TS_TSH -->
							<a style="cursor:pointer" onclick="submitFormOnValidate(true,'TEMPREPORT');" >
								<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									PREVIOUS ALL...
								</font>
							</a>
											<!-- end commented for TS_TSH -->
						<%	}	%> 
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag> --%>
	<% 
		}
	%>
<!-- 	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="100%"  class="tdfonthead">
				<div align="right">
				commented for TS_TSH
					<a style="cursor:pointer" onclick="printAllTemplates(event);" >
						<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							PRINT ALL...
							</font>
					</a>  end commented for TS_TSH
				</div>
			</td>
		</tr>
	</table> -->
	<his:ContentTag>
		<div id="pdfPrintingHTMLData">
		<table width="100%" cellspacing="1" cellpadding="0">
		<%	Iterator lstActiveTempsItr = lstActiveTemps.iterator();
			while(lstActiveTempsItr.hasNext())
			{
				Entry e = (Entry)lstActiveTempsItr.next();
		%>
			<tr>
				<td width="100%">
					<his:GenericTemplateTag templateId="<%=e.getValue()%>" ></his:GenericTemplateTag>
				</td>
			</tr>
		<%	}	%>
		</table>
		</div>
		<%-- <table width="100%" cellspacing="1" cellpadding="0">
		<%	if(lstInactiveTemps.size()>0)	{	%>
			<tr>
				<td width="100%">
				<% String moreURL="/HISClinical/opd/opdTemplateTab.cnt?hmode=MORETEMPLATE&deskMenuId="+deskMenuId; %>
					<a style="cursor:pointer" onclick="openPopup(createFHashAjaxQuery('<%=moreURL %>'),event,300,500);" >
						<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							MORE ...
						</font>
					</a>							
				</td>
			</tr>
		<%	}	%>
		</table> --%>
	</his:ContentTag>
	
		
	
	
<%
	}
%>
</his:statusTransactionInProcess>


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
<html:hidden name="GenericTemplateTileFB" property="deskMenuId"/>
<html:hidden name="GenericTemplateTileFB" property="selUndefaultTemp" value=""/>
<html:hidden name="GenericTemplateTileFB" property="reportMode"/>
<html:hidden name="GenericTemplateTileFB" property ="patCrNo"/>
</form>