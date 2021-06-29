<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.OpdConfig"%>
<%@page import="java.util.List"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="hisglobal.vo.DynamicVisitSummaryVO"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/js/commonFunctions.js"/> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function closeForm()
{
	self.close();
}

function viewAtBottom(data)
{
	document.getElementsByName("view")[0].value=data;
	document.getElementById("divViewNotes").style.display="block"; 
}
</script>

<html:form action="/opdNextVisitDetail">
	<body >
		<his:TitleTag name="Visit Summary">
		</his:TitleTag>
		<%
			LinkedHashMap mpVisitDtl = (LinkedHashMap)session.getAttribute(OpdConfig.OPD_DYNAMIC_VISIT_DETAIL_MAP);
			if(mpVisitDtl==null || mpVisitDtl.keySet().size()==0)
			{
		%>
		<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="tdfont">
					No Record of This Visit Found
				</td>
			</tr>
		
		</table>
		</his:ContentTag>
		<%
			}
			else
			{
				Set keySet = mpVisitDtl.keySet();
				Iterator itrKeys = keySet.iterator();
				while(itrKeys.hasNext())
				{
					String header = (String)itrKeys.next();
					List lstData = (List)mpVisitDtl.get(header);
		%>
		<his:SubTitleTag name="<%=header%>">
		</his:SubTitleTag>
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" >
				<tr>
		<%
					List lstColNames = (List)lstData.get(0);
					String width = Integer.toString(100/lstColNames.size())+"%";
					for(int i=0; i<lstColNames.size(); i++)
					{
						DynamicVisitSummaryVO.ColumnLogic col = (DynamicVisitSummaryVO.ColumnLogic)lstColNames.get(i);						
		%>				
					<td width="<%=(col.getWidth()==null)?width:col.getWidth()%>" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">					
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><%=col.getColumn()%></b>
							</font>
						</div>						
					</td>
		<%
					}
		%>
				</tr>
		<%
					for(int i=1; i<lstData.size(); i++)
					{
		%>				
				<tr>
					<%
						List lstDtl = (List)lstData.get(i);
						for(int j=0; j<lstDtl.size(); j++)
						{
							String data = (lstDtl.get(j)==null)?"":(String)lstDtl.get(j);
							DynamicVisitSummaryVO.ColumnLogic col = (DynamicVisitSummaryVO.ColumnLogic)lstColNames.get(j);							
					%>
					<td width="<%=(col.getWidth()==null)?width:col.getWidth()%>" class="tdfont">					
						<div align="center">
							<b>
						<%
							if(col.isRemarkable() && data!=null && !data.trim().equals(""))
							{
						%>				
								<img class="button" src='<his:path src="/hisglobal/images/icon-vrf.png"/>' style='cursor:pointer' title="<%=col.getColumn()%>" 
								onclick="viewAtBottom('<%=data%>')" onkeypress="if(event.keyCode==13) viewAtBottom('<%=data%>')" >
						<%
							}
							else 
							{
						%>				
									<%=data%>
						<%
							}
						%>
							</b>
						</div>						
					</td>
		<%				}	%>
				</tr>
		<%			}	%>
			</table>
		</his:ContentTag>
		<%
				}
			}
		%>
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
        
       	<div id="divViewNotes" style="display: none;">
	        <his:ContentTag>
				<table width="100%" cellpadding="0" cellspacing="1">
					<tr>
						<td width="100%">
							<div align="center">
								<textarea name="view" rows="3" cols="110" readonly="readonly"></textarea>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>		
		</div>
    </body>	
</html:form>