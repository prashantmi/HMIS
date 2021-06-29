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

<%@page import="java.util.List"%>
<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.vo.PatDrugTreatmentDetailVO;"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<html lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<style  type="text/css">
	table, th, td {
			font-family: Verdana,Arial,Helvetica,sans-serif;
			font-size : 10px;
			font-weight: bold;
	}
</style>

</head>

<body>
	
<div id="treatmentDetailId" style="margin-top: 1px;" align="center">
	<%
		List patTreatmentDetail = (List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST);
		//System.out.print("On Treatment Detail list Size is :-"+patTreatmentDetail.size());
	%>
	<%if(patTreatmentDetail!=null && patTreatmentDetail.size()>0)
			{%>
	
				<table style="width:100%" >
						<tr align="center">							
							<td class="deskTile" width="25%" >
								<a><font color="#000000">Drug Name</font></a>
							</td>
							
							<td class="deskTile" width="20%" >
								<a><font color="#000000">Dosage</font></a>
							</td>
							
							<td class="deskTile" width="15%">
								<a><font color="#000000">Frequency</font></a>
							</td>
							
							<td class="deskTile" width="20%" >
								<a><font color="#000000">Start Date</font></a>
							</td>
							
							<td class="deskTile" width="20%" >
								<a><font color="#000000">End Date</font></a>
							</td>
						</tr>
			<%
				for(int x=0;x<patTreatmentDetail.size();x++)
				{
					PatDrugTreatmentDetailVO vo = (PatDrugTreatmentDetailVO)patTreatmentDetail.get(x);
			%>
						<tr align="center" >							
							<td class="deskTile" width="25%" >
								<div><a><font color="#000000"><%=(vo.getDrugName()==null)?"NA":vo.getDrugName()  %></font></a></div>
							</td>
							
							<td class="deskTile" width="20%" >
								<div><a><font color="#000000"><%=(vo.getDoseName()==null)?"NA":vo.getDoseName()  %></font></a></div>
							</td>
							
							<td class="deskTile" width="15%" >
								<div><a><font color="#000000"><%=(vo.getFrequencyName()==null)?"NA":vo.getFrequencyName()  %></font></a></div>
							</td>
							
							<td class="deskTile" width="20%" >
								<div><a><font color="#000000"><%=(vo.getStartDate()==null)?"NA":vo.getStartDate()  %></font></a></div>
							</td>
							
							<td class="deskTile" width="20%" >
								<div><a><font color="#000000"><%=(vo.getEndDate()==null)?"NA":vo.getEndDate()  %></font></a></div>
							</td>
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
      $('#treatmentDetailId').slimscroll({
        railDraggable: true,
        height: '85%'
      });
    });
</script>

</body>
</html>