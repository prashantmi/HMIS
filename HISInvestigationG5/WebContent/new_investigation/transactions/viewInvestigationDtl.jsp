<%@page import="investigationDesk.vo.Inv_RequisitionRaisingPatientVO"%>
<%@page import="investigationDesk.vo.Inv_EpisodeVO"%>
<%@page import="investigationDesk.vo.Inv_PatientAdmissionDtlVO"%>
<%@page
	import="investigationDesk.transactions.controller.fb.viewInvestigationFB"%>
<%@page import="investigationDesk.vo.viewInvestigationVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@ taglib uri="/WEB-INF/AppointmentTools.tld" prefix="appt"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page import="investigationDesk.InvestigationConfig"%>

<%@page import="java.util.*"%>

<%@page import="hisglobal.hisconfig.Config"%>

   
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>

<his:javascript src="/investigationDesk/js/appointment.js" />
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/invPopupReport.css" />
<his:css src="/hisglobal/css/easyui.css" />
<his:css src="/hisglobal/css/icon.css" />
<his:css src="/hisglobal/css/jquery-ui.css" />
<his:css src="/hisglobal/css/style.css" />
<his:css src="/hisglobal/css/jquery/bookMark.css" />
<his:css src="/hisglobal/css/jquery/search.css" />
<his:css src="/hisglobal/css/InvBookMark.css" />

<!--styleTextBox.css
 -->


<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/css-pop-report-inv.js" />




<!-- <script type="text/javascript" src="/HIS/appointment/js/jquery/jquery-2.1.1.min.js"></script>
 -->
<script type="text/javascript"
	src="/AHIMS/hisglobal/js/dojotoolkit/dojo/dojo.js"
	djConfig="parseOnLoad: true"> </script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>


 

<script type="text/javascript">


var isChecked = false;

function onload()
{

	var val=document.getElementsByName("callingmode")[0].value;

    
	  document.getElementById("setprevreqnew").selectedIndex = val;
	  
	}



function setprevreq(obj)
{
	var crno='<%=(String) session.getAttribute("patcrno")%>' ;
    //alert(crno);
	document.getElementsByName("patCrNo")[0].value=crno;
	
    
	document.getElementsByName("callingmode")[0].value=obj.value;
		document.getElementsByName('hmode')[0].value='NEW_ALL';
	     document.forms[0].action="/HISInvestigationG5/new_investigation/viewInvestigation.cnt"; 
		document.forms[0].submit();
		
}


function allSelected() 
{
	if(document.getElementById("nextDiv")==null)
	{
		alert("Pdf Is Not Generated");
	}
	else
		{
		
	document.getElementById("nextDiv").style.display=""; 
		}
	// this line is for toggle the check
    isChecked = !isChecked;
    //below line refers to 'jpCheckbox' class
    $('input:checkbox.jpCheckbox').prop('checked',isChecked);
    //OR,
    //$('input:checkbox.jpCheckbox').attr('checked','checked');
}

function refresh()
{
	   document.getElementById('selectAllCheckbox').checked=false;
	   
    $('input:checkbox.jpCheckbox').prop('checked',false);
    
    isChecked=false;
}

function ValidateSameCrNo(obj)
{
	
	if(obj.checked)
	{
		
    		
    	document.getElementById("nextDiv").style.display=""; 
    	    
	}
  else
     	{
  	//document.getElementById("nextDiv").style.display="none"; 

          }
	 
	
 
}

function displaySamplePatDetails()
{	
	var count=0;
	//var reportType=document.getElementsByName('reportType')[0].value;
	//document.getElementsByName('isPatDetailPage')[0].value="1";
	var strAllChkDetail="";
	var concatenateChkBoxVal="";
	//var cbs = document.getElementsByTagName('input');
	var cbs =document.getElementsByName('chkSamplePatient');
	for(var i=0; i < cbs.length; i++) {
	 // if(cbs[i].type == 'checkbox') 
    //{
    	
    	if(cbs[i].checked)
    	{
    		
    	count++;	
    	concatenateChkBoxVal =concatenateChkBoxVal.concat(cbs[i].value);
    	concatenateChkBoxVal+='@';
    	 }
  	//}
	  
 
      }
	
	if(count==0)
   	{
   	alert("please select a record");
   	return false;
   	}
	
	var src="/hisglobal/js/Payment.pdf";
	
	
	//demo1();
	document.getElementsByName('selectedCheckbox')[0].value=concatenateChkBoxVal;
	 //alert("concatenateChkBoxVal"+concatenateChkBoxVal);
	
	 
	strAllChkDetail=strAllChkDetail+"&selectedCheckbox="+document.getElementsByName('selectedCheckbox')[0].value;
	
	var mode="SHOWPATDETAILS_VIEWINV";
	
	var url='/HISInvestigationG5/new_investigation/invResultReportPrinting.cnt?hmode='+mode+strAllChkDetail;
	 

 
	 
		
//	alert("final url"+url);
	
	AddRowToTableAddMoreValues(url);
	popup('popUpDiv');
	 
    
	
	//document.forms[0].submit();
 	
	}



function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	   document.forms[0].action="/HISInvestigationG5/new_investigation/viewInvestigation.cnt"; 

	document.forms[0].submit();
}

function printReport(name)
{
	
	
	document.getElementsByName('fileName')[0].value=name;
//document.getElementsByName('hmode')[0].value='PRINTREPORT';
	//document.forms[0].action="/AHIMS/investigationDesk/viewInvestigation.cnt"; 

//	document.forms[0].submit();
	
	
	var mode='PRINTREPORT';
var strAllChkDetail = name;


var url = '/HISInvestigationG5/new_investigation/invResultReportPrinting.cnt?hmode='+mode+"&fileName="+name;

//	alert("final url"+url);

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
 

 
 
<style>
 .setAdvisedBy {
 
    position: fixed;
    top: 180px;
    right: 50px;
   z-index: 100;
}
</style>


<style>
.scroll_div {
	height: 50px;
	overflow-y: hidden;
	overflow-x: scroll;
	text-align: justify;
	margin: 0;
	border-style: groove;
	padding: 5px 5px 5px 5px;
	scrollbar-face-color: #666669;
	scrollbar-highlight-color: #030000;
	scrollbar-3dlight-color: #030000;
	scrollbar-darkshadow-color: #030000;
	scrollbar-shadow-color: #030000;
	scrollbar-arrow-color: #030000;
	scrollbar-track-color: #030000;
}
</style>
<style>
#colorCycle {
	background-color: #8C8984;
	border: medium solid #1277b5;
	padding-top: 5px;
	padding-right: 7px;
	padding-bottom: 7px;
	padding-left: 7px;
	color: #FFF;
	text-align: left;
	animation-name: homeCycle;
	animation-duration: 6s;
	animation-direction: alternate;
	animation-iteration-count: infinite;
	-webkit-animation-name: homeCycle;
	-webkit-animation-duration: 6s;
	-webkit-animation-direction: alternate;
	-webkit-animation-iteration-count: infinite;
}

@
keyframes homeCycle { 0% {
	background-color: #3366CC;
}

25%
{
background-color
 

:

 


#003399








;
}
50%
{
background-color








:








#6699FF








;
}
75%
{
background-color








:








#3366CC








;
}
}
@
-webkit-keyframes homeCycle { 0% {
	background-color: #3366CC;
}
25%
{
background-color








:




 




#003399








;
}
50%
{
background-color








:








#6699FF








;
}
75%
{
background-color








:








#3366CC








;
}
}
</style>
<style>
.textBoxCss {
	background: white;
	color: #135d8c;
	width: 180px;
	padding: 4px 10px 4px 15px;
	border-radius: 20px;
	box-shadow: 0 1px 0 #ccc inset;
	transition: 500ms all ease;
	outline: 0;
}
</style>


<style>
.NewTextBox {
	border:2px solid #456879;
	border-radius:10px;
	height: 22px;
	width: 330px;
}
</style>
<%
	String strdivage="\"\"";
String strdivdob="\"\""; 
String patCrNo="";  //style="width:90%;margin:auto;min-width:600px;max-width:2000px"
%>
<body style="" onload="onload()">

	<html:form action="/viewInvestigation">
		<his:statusNew>
			<his:TitleTag key="invRaising">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag>
			
			
			

		</his:statusNew>
		<his:statusTransactionInProcess>
		<%String deskType = (String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
			System.out.println(deskType);
			UserVO userVO=ControllerUTIL.getUserVO(request);
			String opdlocal=userVO.getUsrName();%>
		
 
<his:ContentTag>

	<logic:present name="<%=InvestigationConfig.LIST_REQ_DATA%>">
	
	<%
												//Pagination Logic

																PaginationFB fbPage = new PaginationFB();
																pageContext.setAttribute("fbPagination", fbPage);
																fbPage.setCurrentPage(((viewInvestigationFB) request
																		.getAttribute("viewInvestigationFB"))
																		.getCurrentPage());
																fbPage.setObjArrName(InvestigationConfig.LIST_REQ_DATA);
																fbPage.setAppendInTitle("Requisitions");
																int maxRecord = 10;
																fbPage.setMaxRecords(maxRecord);
											%>
											<his:PaginationTag name="fbPagination"></his:PaginationTag>
											
											
					<his:SubTitleTag name="Requisition History of the Patient">
					
						<select name="setprevreqnew" id="setprevreqnew" style='margin-right:80' onchange='setprevreq(this)'><option value='0'>From Last 3 Months</option><option value='1'>All</option></select>
					
					</his:SubTitleTag>
					<table width="100%">
					<tr>
					<%-- <% if(deskType.equals("1") || deskType.equals("2"))
						{%> --%>
						
						
						<td class="tdfonthead" width="5%">

								<div align="left">
									
					<input type="checkbox" id="selectAllCheckbox" onclick="allSelected()" />

								</div>
							</td>
							
							<td class="tdfonthead" width="5%">

								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><u>	Visit No</u></b></font>

								</div>
							</td>
							
							<%-- <%}else{ %> --%>
							
							<%-- 
							<td class="tdfonthead" width="10%">

								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><u>	Admission No</u></b></font>

								</div>
							</td>
							
							<%} %> --%>
							<td class="tdfonthead" width="15%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><u>	Requisition Date</u></b>
								</div>
							</td>


							<td class="tdfonthead" width="13%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><u>	Laboratory</u></b>
								</div>
							</td>
							<td class="tdfonthead" width="13%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><u>Test</u></b>
								</div>
							</td>
							<td class="tdfonthead" width="7%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><u>	System</u></b>
								</div>
							</td>
							<td class="tdfonthead" width="15%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><u>	Status</u></b>
								</div>
							</td>
							<td class="tdfonthead" width="15%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><u>Report Date</u></b>
								</div>
							</td>
							
							<!-- <td class="tdfonthead" width="12%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><u>	Report URL</u></b>
								</div>
							</td> -->

						</tr>

						<%
							List<viewInvestigationVO> lstPatInvDtlVO=(List<viewInvestigationVO>)session.getAttribute(InvestigationConfig.LIST_REQ_DATA);
						int i, size = 0;
						
						if (lstPatInvDtlVO != null
								&& lstPatInvDtlVO.size() > 0)
							size = lstPatInvDtlVO.size();
						
						int startIndex = ((Integer) request
								.getAttribute(PaginationTag.PAGINATION_START_INDEX))
								.intValue();
						int endIndex = ((Integer) request
								.getAttribute(PaginationTag.PAGINATION_END_INDEX))
								.intValue();
						
						for (int k = startIndex; k <= endIndex; k++) {
							//int i=j-startIndex;
							if (k < size) {
																						 
																						  
																						 			viewInvestigationVO voPatInv=lstPatInvDtlVO.get(k);
																						 		String chkVal=voPatInv.getPukNo()+"#"+voPatInv.getTestCode()+"#"+voPatInv.getAptDate()+" "+voPatInv.getSlotTime();
																						 		%>
																						 		
																						 		
																						 		
						<tr>


	<%-- <% if(deskType.equals("1") || deskType.equals("2"))
						{%> --%>
						
						
						
						
						<%if(voPatInv.getResURL().equals("-")==true){ %>
					
						<td class="tdfonthead" width="5%">
								<div align="left">
							
								</div>
							</td>
							
								
						<%}else{ %>
						<td class="tdfonthead" width="5%">
								<div align="left">
									<input type="checkbox" class="jpCheckbox" name="chkSamplePatient" value='<%=voPatInv.getResURL()%>' onclick="ValidateSameCrNo(this)"; >
								</div>
							</td>
							<%} %>
							
							<td class="tdfonthead" width="5%">
								<div align="left">
										<%=voPatInv.getVisitNo()==null?"-":voPatInv.getVisitNo() %>
								</div>
							</td>
							<%--  <%}else{ %>
							 
							 
							<td class="tdfonthead" width="10%">
								<div align="left">
										<%=voPatInv.getAdmNo()==null?"-":voPatInv.getAdmNo() %>
								</div>
							</td>
							
							<%} %> --%>
							 
								<td class="tdfonthead" width="15%">
								 
								<div align="left">
									<%=voPatInv.getReqDate() %>
								</div>
							</td>
								 

							<td class="tdfonthead" width="13%">
								<div align="left">
									<%=voPatInv.getLabName()%>
								</div>
							</td>


							<td class="tdfonthead" width="13%">
								<div align="left">
									<%=voPatInv.getTestName()%>
								</div>
							</td>

							<td class="tdfonthead" width="7%">
								<div align="left">
									<%=voPatInv.getSampleName()%>
								</div>
							</td>


							<td class="tdfonthead" width="15%">
								<div align="left">
									<%=voPatInv.getStatus()%>
								</div>
							</td>

							<td class="tdfonthead" width="15%">
								<div align="left">
									<%=voPatInv.getResultDate()%>
								</div>
							</td>
							

						<%-- 	<%if(voPatInv.getResURL().equals("-")==false){ %>

							
							<td class="tdfonthead" width="12%">
								<div align="left">
							<a onclick="printReport('<%=voPatInv.getResURL()%>')" >View Report</a>
								</div>
							</td>
						</tr>
						<%}else{ %>
								<td class="tdfonthead" width="12%">
								<div align="left">
							
								</div>
							</td>
						</tr>
						<%
						} --%>
							
						<%	}
													 	}
						%>
					</table>
	<div id="container">
				<div id="blanket" style="display: none;"></div>
				<div draggable="true" id="popUpDiv" style="display: none;">
					<his:SubTitleTag name="Patient Report">
						<div class="vertpan pic">
							<img src='/HISInvestigationG5/hisglobal/css/close.png'
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
				</logic:present>
				
				
				
        <logic:empty name="<%=InvestigationConfig.LIST_REQ_DATA%>"> 
            <font color="RED" size="6" face="Verdana, Arial, Helvetica, sans-serif"> 
                <b>No Requisitions Raised </b> 
            </font>     
        </logic:empty>
     
				
</his:ContentTag>
 
			<his:ButtonToolBarTag>
				    	 <img class="button"   src='<his:path src="/hisglobal/images/PrintResults.png"/>' id="nextDiv"  style="cursor:pointer;display:none"   tabindex="1" onclick ="displaySamplePatDetails();" >
				    
	</his:ButtonToolBarTag>
	
			

		</his:statusTransactionInProcess>


		<html:hidden name="viewInvestigationFB" property="hmode" />
				<html:hidden name="viewInvestigationFB" property="patCrNo" />
		
				<html:hidden name="viewInvestigationFB" property="callingmode" />
		
			<html:hidden name="viewInvestigationFB" property="selectedCheckbox" />
		
		<html:hidden name="viewInvestigationFB" property="currentPage" />
		<html:hidden name="viewInvestigationFB" property="numberOfRow" />
<html:hidden name="viewInvestigationFB" property="fileName" />
		  

		<his:ButtonToolBarTag>

			<his:statusNew>
			<%-- 	<img class="button"
					src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"
					style="cursor: pointer"
					onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')"
					tabindex="1" onclick="submitToDesk('NEW','NEW')">  --%>
			</his:statusNew>

			<his:statusTransactionInProcess>
				<logic:equal name="viewInvestigationFB" property="aptStatus"
					value="0">
					<logic:present name="<%=InvestigationConfig.LIST_APTBASED_TEST%>">
					<%-- 	<img class="button"
							src='<his:path src="/hisglobal/images/btn-next.png"/>'
							id="nextDiv" style="cursor: pointer; display: none" tabindex="1"
							onclick="displayAptDetails();"> --%>
					<%-- 	<img class="button"
							src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
							tabindex="1" style="cursor: pointer"
							onkeypress="if(event.keyCode==13) submitForm('NEW');"
							tabindex="1" onclick="submitForm('NEW');"> --%>
					</logic:present>
				</logic:equal>
				<logic:present name="<%=InvestigationConfig.LIST_EPISODE_VO%>">
					 
			<%-- 		
					<img class="button"  id="saveDiv"
						src='<his:path src="/hisglobal/images/btn-sv.png"/>'
						style="cursor: pointer"
						onkeypress="if(event.keyCode==13&&validateSave()) submitFormToSave('SAVE');"
						tabindex="1" onclick="if(validateSave())submitFormToSave('SAVE');"> --%>
					<%-- <img class="button" 
						src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"
						style="cursor: pointer"
						onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1"
						onclick="submitForm('NEW');"> --%>
						 
				</logic:present>

			</his:statusTransactionInProcess>

		</his:ButtonToolBarTag>


		<his:status />

	</html:form>

</body>