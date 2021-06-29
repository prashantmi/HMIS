<!-- 
/**
 * @author CDAC
 */
-->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<%@page import="investigationDesk.vo.viewInvestigationVO"%>
<%@page import="investigationDesk.InvestigationConfig"%>
<%@page import="java.util.List"%>

<html lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>

<body>
	


<div id="investigationDetalId" style="margin-top: 1px;" align="center">
	<%
		List patInvestigationDetail = (List)session.getAttribute(InvestigationConfig.LIST_REQ_DATA);
		//System.out.print("On Investigation Detail list Size is :-"+patInvestigationDetail.size());
	%>
	<%if(patInvestigationDetail!=null && patInvestigationDetail.size()>0)
			{%>
	
				<table style="width:100%" >
						<tr align="center">							
							<td class="deskTile" width="25%" >
								<a><font color="#000000">Requisition Date</font></a>
							</td>
							
							<td class="deskTile" width="20%" >
								<a><font color="#000000">Laboratory</font></a>
							</td>
							
							<td class="deskTile" width="15%">
								<a><font color="#000000">Test</font></a>
							</td>
							
							<td class="deskTile" width="20%" >
								<a><font color="#000000">Status</font></a>
							</td>
							
							<!-- <td class="deskTile" width="20%" >
								<a><font color="#000000">End Date</font></a>
							</td> -->
						</tr>
			<%
				for(int x=0;x<patInvestigationDetail.size();x++)
				{
					viewInvestigationVO vo = (viewInvestigationVO)patInvestigationDetail.get(x);
			%>
						<tr align="center" >							
							<td class="deskTile" width="25%" >
								<div><a><font color="#000000"><%=(vo.getReqDate()==null)?"NA":vo.getReqDate()  %></font></a></div>
							</td>							
							<td class="deskTile" width="20%" >
								<div><a><font color="#000000"><%=(vo.getLabName()==null)?"NA":vo.getLabName()  %></font></a></div>
							</td>
							
							<td class="deskTile" width="15%" >
								<div><a><font color="#000000"><%=(vo.getTestName()==null)?"NA":vo.getTestName()  %></font></a></div>
							</td>
							
							<td class="deskTile" width="20%" >
								<div><a><font color="#000000"><%=(vo.getStatus()==null)?"NA":vo.getStatus()  %></font></a></div>
							</td>
							
							<%-- <td class="deskTile" width="20%" >
								<div><a><font color="#000000"><%=(vo.getEndDate()==null)?"NA":vo.getEndDate()  %></font></a></div>
							</td> --%>
						</tr>
			<%
				}
			%>
				</table>
		<%
		}
		else
		{
		%>
			<div class="div-table width100" >
				<div class="div-table-row ">							
						<div class="div-table-col width100 deskTile" align="center">
								<a><font color="#000000">No Data Found</font></a>
						</div>
				</div>
			</div>
		<%
			}
		%>
</div>





<html:hidden name="DoctorDeskFB" property="episodeCode"/>
<html:hidden name="DoctorDeskFB" property="episodeVisitNo"/>
<html:hidden name="DoctorDeskFB" property="departmentUnitCode"/>
<html:hidden name="DoctorDeskFB" property="roomCode"/>

<script type="text/javascript">
    $(function(){      
      $('#investigationDetalId').slimscroll({
        railDraggable: true,
        height: '85%'
      });
    });
</script>

</body>
</html>