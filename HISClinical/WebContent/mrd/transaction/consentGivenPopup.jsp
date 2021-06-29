<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="java.util.*" %>
<%@page import="java.util.Date"%>

<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="opd.transaction.controller.fb.ConsentRequestFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="hisglobal.vo.ConsentRequestVO"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/datepicker1.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
function closeForm()
{
	self.close();
}

function doPagination(e,p)
{	
	
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='POPUPPAGINATION';
	document.forms[0].submit();
}

function enableRelation(index)
{
	//alert("index "+index);
	var givenByRowId="givenByRowId"+index;
	//alert("givenByRowId "+givenByRowId);
	
	var givenByValueRowId="givenByValueRowId"+index;
	document.getElementById(givenByRowId).style.display="";
		document.getElementById(givenByValueRowId).style.display="";
	
	
	
		
		/*
		var len=document.getElementsByName("chk").length;
		
		for(i=0;i<len;i++)
		{
			if(document.getElementsByName("chk")[i].checked) 
			{
				
				if(document.getElementsByName("givenBy")[i].value=="1")
				{
					var giveByRowId="releationshipRowId"+Index;
					var relationShipValueRowId="relationShipValueRowId"+i;
					
					document.getElementById(releationshipRowId).style.display="";
					document.getElementById(relationShipValueRowId).style.display="";
				}
				else
				{
					var releationshipRowId="releationshipRowId"+i;
					var relationShipValueRowId="relationShipValueRowId"+i;
					
					document.getElementById(releationshipRowId).style.display="none";
					document.getElementById(relationShipValueRowId).style.display="none";
				}
				
			} 
				
		}
		*/
	}

</script>

<html:form action="/consentRequest">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	<body>
				
		<% 	PaginationFB fbPage= new PaginationFB();
				pageContext.setAttribute("fbPagination",fbPage);
				fbPage.setCurrentPage(((ConsentRequestFB)request.getAttribute("consentRequestFB")).getCurrentPage());
				fbPage.setObjArrName(OpdConfig.RECEIVED_CONSENT_REQUEST_VO_LIST);
				fbPage.setAppendInTitle("Consent Given");
				fbPage.setMaxRecords(2);
		%>
			<html:hidden name="consentRequestFB" property="currentPage"/>

			<his:PaginationTag name="fbPagination"></his:PaginationTag>
			
		<bean:define id="patCrNo" property="patCrNo" name="consentRequestFB"></bean:define>
		
		<logic:present name="<%=OpdConfig.RECEIVED_CONSENT_REQUEST_VO_LIST %>">
		<logic:notEmpty name="<%=OpdConfig.RECEIVED_CONSENT_REQUEST_VO_LIST %>">
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
			
				
				<td width="12%"  class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="serviceType"/></b>
					</font>
					</div>
		  		</td>
				<td width="12%"  class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="service"/></b>
					</font>
					</div>
		  		</td>
	  		
				<td width="12%"  class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="consentName"/></b>
					</font>
					</div>
		  		</td>
				<td width="12%"  class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="raisedBy"/></b>
					</font>
					</div>
		  		</td>
				<td width="12%"  class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="date&time"/></b>
						</font>
						</div>
		  		</td>
				<td width="12%"  class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="status"/></b>
					</font>
					</div>
		  		</td>
		  		<td width="12%"  class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="givenBy"/></b>
					</font>
					</div>
		  		</td>
		</tr>
		
		
	<%
	 			List consentRequestVos=(List)session.getAttribute(OpdConfig.RECEIVED_CONSENT_REQUEST_VO_LIST);
						
						int startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
						int endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
						
						Map map = new HashMap();
						if(session.getAttribute(OpdConfig.SELECTED_CONSENT_REQUEST)!=null)
							map=(HashMap)session.getAttribute(OpdConfig.SELECTED_CONSENT_REQUEST);
						
						for(int j=startIndex;j<=endIndex;j++)
						{
							Integer ind=new Integer(j);
							String index=ind.toString();
							ConsentRequestVO consentRequestVO=(ConsentRequestVO)consentRequestVos.get(j);
	%>	
		
	<tr>
	
	
	 <td width="12%" class="tdfont">
				         <div align="center">	
				         <%=consentRequestVO.getServiceTypeDesc() %>
				         </div>
					 </td>
	 <td width="12%" class="tdfont">
				         <div align="center">	
				         <%=consentRequestVO.getServiceDesc() %>
				         </div>
					 </td>
	<td width="12%" class="tdfont">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<% String Path="/HISClinical/opd/consentRequest.cnt?hmode=POPUP&patCrNo="+patCrNo+"&templateId="+consentRequestVO.getConsentId();%>
					<a style="cursor:pointer" onclick="openPopup('<%=Path%>',event,300,600)" >
					<%=consentRequestVO.getTemplateDesc()%>
					</a>
					</font>
					</div>
		  		</td>
					<td width="12%" class="tdfont">
				         <div align="center">
				         <%=consentRequestVO.getRaisedBy() %>	
				         </div>
					 </td>
					<td width="12%" class="tdfont">
				         <div align="center">
				         <%=consentRequestVO.getRaisedDate()%>	
				         </div>
					 </td>
					<td width="12%" class="tdfont">
				         <div align="center">	
				         <%=consentRequestVO.getConsentStatus()%>
				        <html:hidden name="consentRequestFB" property="givenBy" value="<%=consentRequestVO.getGivenBy() %>"/>
				         </div>
					 </td>	
					 <td width="12%" class="tdfont">
				         <div align="center">
				         <%
				         	if(consentRequestVO.getGivenBy().equals("Self"))
				         	{
				         %>
				         <%=consentRequestVO.getGivenBy()%>
				         <%		
				         	}
				         	else
				         	{
				         %>
				         	 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<a style="cursor:pointer" onclick="enableRelation(<%=index%>)" >
	      							<%=consentRequestVO.getGivenBy()%>
	      						</a>
							</b>
						</font>	
				         <%		
				         	}
				         %>
				         
				          				        
				         </div>
				    	 </td>
			</tr>
			
			<tr id="givenByRowId<%=index%>" style="display: none;">
		<td width="20%"  class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="realtionship"/></b>
					</font>
					</div>
		  		</td>
		<td width="20%"  class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<font color="#FF0000">*</font>
					<bean:message key="relativename"/>
					</b>
					</font>
					</div>
		  		</td>
		<td width="20%"  class="tdfonthead" colspan="2" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<font color="#FF0000">*</font>
					<bean:message key="relativeaddress"/>
					</b>
					</font>
					</div>
		  		</td>
		  	<td width="20%"  class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="relativeContactNo"/></b>
					</font>
					</div>
		  		</td>
		  	<td width="20%"  class="tdfonthead" colspan="2" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="relativeIdMark"/></b>
					</font>
					</div>
		  		</td>
		  	
			
		</tr>
		<tr id="givenByValueRowId<%=index%>" style="display: none;">
			<td width="12%" class="tdfont">
				         <div align="center">	
				         <% 
				         	String relationshipName="";
				         	if(consentRequestVO.getRelationshipName()!=null)
					         {
				         		relationshipName=consentRequestVO.getRelationshipName();
					         }
				        	
				         %>
				         	<%=relationshipName %>
				         </div>
					 </td>
			<td width="12%" class="tdfont">
				         <div align="center">	
				         <% 
				         	String relativeName="";
				         	if(consentRequestVO.getRelativeName()!=null)
					         {
				         		relativeName=consentRequestVO.getRelativeName();
					         }
				        	
				         %>
				         <%=relativeName%>
				         </div>
					 </td>
			<td width="12%" class="tdfont" colspan="2">
				         <div align="center">	
				         <% 
				         	String relativeAddr="";
				         	if(consentRequestVO.getRelativeAddr()!=null)
					         {
				         		relativeAddr=consentRequestVO.getRelativeAddr();
					         }
				        	
				         %>
				         <%=relativeAddr%>
				         </div>
					 </td>
			<td width="12%" class="tdfont">
				         <div align="center">	
				         <% 
				         	String relativeContactNo="";
				         	if(consentRequestVO.getRelativeContactNo()!=null)
					         {
				         		relativeContactNo=consentRequestVO.getRelativeContactNo();
					         }
				        	
				         %>
				         <%=relativeContactNo %>
				         </div>
					 </td>
			<td width="12%" class="tdfont" colspan="2">
				         <div align="center">	
				         <% 
				         	String relativeIdRemark="";
				         	if(consentRequestVO.getRelativeIdRemark()!=null)
					         {
				         		relativeIdRemark=consentRequestVO.getRelativeIdRemark();
					         }
				        	
				         %>
				         <%=relativeIdRemark%>
				         </div>
					 </td>		 		 		 		 
		</tr>
	<%} %>		
		
	</table>
	</logic:notEmpty>
	</logic:present>
		<logic:empty name="<%=OpdConfig.RECEIVED_CONSENT_REQUEST_VO_LIST%>">
		<table width="100%" cellpadding="0" cellspacing="1" >
					<tr>
						<td>
						<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>No consent is received</b>
						</font>
						</td>
					</tr>
				</table>
	</logic:empty>
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
        
        <html:hidden property="hmode" name="consentRequestFB" />
        <html:hidden property="patCrNo" name="consentRequestFB" />
	</body>
</html:form>		