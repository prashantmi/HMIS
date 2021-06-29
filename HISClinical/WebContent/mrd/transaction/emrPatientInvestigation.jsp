<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:css src="/hisglobal/css/invPopupReport.css" />
<his:javascript src="/hisglobal/js/css-pop-report-inv.js" />
<his:javascript src="/registration/js/popup.js" />
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<%@page import="mrd.MrdConfig"%>
<%@page import="hisglobal.vo.PatientDetailVO"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function showLegends(){
  document.getElementById("divLegends").style.display=""; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}

/*function showResult(reqDNo,testName,event)
{
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	//alert("patCrNo "+patCrNo)
	// alert("testName "+testName)
	 var path="/HISClinical/mrd/emrDesk.cnt?hmode=INVESTIGATIONPOPUP&reqDNo="+reqDNo+"&patCrNo="+patCrNo+"&testName="+testName;
	 //alert("path "+path)
	 openPopup(createFHashAjaxQuery(path),event,800,900);
}*/

function printReport(name,isConf)
{
	//alert(name);
	
	//document.getElementsByName('fileName')[0].value=name;
//document.getElementsByName('hmode')[0].value='PRINTREPORT';
	//document.forms[0].action="/HISClinical/investigationDesk/viewInvestigation.cnt"; 

//	document.forms[0].submit();
	<%PatientDetailVO vo=(PatientDetailVO)session.getAttribute(MrdConfig.PATIENT_DTL_VO);
	vo.setPatientName();
	String patname=vo.getPatName();
	//System.out.println("sddddddddd"+patname);
	%>
	
	var mode='PRINTREPORT';
var strAllChkDetail = name;
var pname='<%=patname%>';
var url = '/HISInvestigationG5/new_investigation/invResultReportPrinting.cnt?hmode='+mode+"&fileName="+name;

	//alert("final url"+url);

AddRowToTableAddMoreValues(url);
popup('popUpDiv');

//document.forms[0].submit();

}
function AddRowToTableAddMoreValues(finalMadCode) {

	// alert("working fine"+finalMadCode);

	//var mode = "SHOWPATDETAILS";

	//var url ='/HISInvestigationG5/new_investigation/invResultReportPrinting.cnt?hmode='+mode

	var nRow = 0;
	var tableObj = document.getElementById('addMoreValue');
	var numRows = tableObj.rows.length;
	//alert(document.getElementById("setPdf"));
	if (document.getElementById("setPdf") != null) {

	document.getElementById("setPdf").remove();
	//document.getElementById("deleteRow").deleteRow(1);
	}

	//alert("total length"+numRows);
	nRow = numRows;

	var tabRow = tableObj.insertRow(numRows);
	tabRow.id = parseInt(nRow);

	var td1 = document.createElement("TD");

	td1.innerHTML = "<iframe id='setPdf' src='"+ finalMadCode + "' width='100%' height='450px' type='application/pdf'    ></iframe> ";
	td1.className = 'tdfont';
	td1.colspan = "1";

	tabRow.appendChild(td1);

	//document.forms[0].numberOfRow.value=numRows;
	}


</script>

<html:form action="/emrDesk">

<jsp:include page="/mrd/transaction/emrHeader.jsp" flush="true"></jsp:include>
<his:TitleTag name="Investigation Detail">
</his:TitleTag>	

	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
			
				<td width="15%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%-- <bean:message key="visitDate"/> --%>
							Requisition Date
						</font>
					</div>
				</td>
				<td width="15%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Laboratory 
						</font>
					</div>
				</td>
				<td width="25%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	   
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%-- <bean:message key="testName"/> --%>
							Test
						</font>
					</div>
				</td>
				<td width="15%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	   
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Sample Name
						</font>
					</div>
				</td>
				<td width="20%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	   
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Test Status
						</font>
					</div>
				</td>
				<td width="15%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	   
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Report
						</font>
					</div>
				</td>
			<%--	<td width="32%"  class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="resultStatus"/></b>
						</font>
					</div>
				</td> --%>
				
			</tr>
				<logic:present name="<%=MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY%>" >
				<logic:notEmpty name="<%=MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY%>" >
	   <%String indexEpisodeOpen="-1"; %>
			<logic:iterate id="profileInvestigationVOs" name="<%=MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY%>" indexId="id" type="hisglobal.vo.ProfileInvestigationVO" >
				<tr>
				 
						<td width="15%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="profileInvestigationVOs" property="visitDate" />
							</font>
						</div>
					</td>   
					<td width="15%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="profileInvestigationVOs" property="labName" />
							</font>
						</div>
					</td>   
					<td width="25%"  class="tdfont">
						<div align="center"> 
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">          
								<bean:write name="profileInvestigationVOs" property="testName" />
							</font>
						</div>
					</td>
					<td width="15%"  class="tdfont">
						<div align="center"> 
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">          
								<bean:write name="profileInvestigationVOs" property="sampleName" />
							</font>
						</div>
					</td>	
					<td width="20%"  class="tdfont">
						<div align="center"> 
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">          
								<bean:write name="profileInvestigationVOs" property="resultStatus" />
							</font>
						</div>
					</td>					   
					<td width="15%"  class="tdfont">
						<div align="center"> 
						<% if(profileInvestigationVOs.getReportURL()!=null && !profileInvestigationVOs.getReportURL().trim().equals("")){ %>
							<a   style="cursor: pointer;" onclick=" printReport('<%=profileInvestigationVOs.getReportURL() %>','<%=profileInvestigationVOs.getIsConfidential()%>')" onkeypress="printReport('<%=profileInvestigationVOs.getReportURL() %>' ,'<%=profileInvestigationVOs.getIsConfidential()%>')" >
							<font color="#0066FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">          
							<u>	View Report </u>
							</font>
						</a>
						<%} %>
						</div>
					</td>   
				<%--	<td width="32%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">	           
								<bean:write name="profileInvestigationVOs" property="resultStatus" />
								NA
							</font>
						</div>
					</td> --%>   
					
				</tr>
					<%indexEpisodeOpen=id.toString(); %>
			</logic:iterate>
			<%if(indexEpisodeOpen.equals("-1")){%>
			<his:ContentTag>
				<tr>
					
									<td class="tdfont" width="25%" colspan="5" nowrap>
									<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>	<bean:message key="noInvestigationDetailsFound" /></b></font></div></td>
					
				</tr>
			</his:ContentTag>
			<%} %>
				</logic:notEmpty>
    		</logic:present>
    </table>
	<div id="container">
				<div id="blanket" style="display: none;"></div>
				<div draggable="true" id="popUpDiv" style="display: none;">
					<his:SubTitleTag name="Patient Report">
						<div class="vertpan pic">
							<img src='/HISClinical/hisglobal/images/close1.png'
								onclick="popupClose('popUpDiv')"'>
						</div>
					</his:SubTitleTag>
					<table id="addMoreValue" width="100%">
						<tr id="deleteRow">
						</tr>
					</table>
					
					<left></left>
				</div>
				<!-- end #mainContent -->
			</div>
    
    <logic:notPresent name="<%=MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY%>" >
    	<his:ContentTag>
    	
				<tr>
					
									<td class="tdfont" width="100%"  nowrap>
									<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>	<bean:message key="noInvestigationDetailsFound" /></b></font></div></td>
					
				</tr>
		
			</his:ContentTag>
    </logic:notPresent>
		</his:ContentTag>
	
</html:form>
<%@include file="/mrd/transaction/emrFooter.jsp"%> 