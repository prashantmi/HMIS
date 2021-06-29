<!-- 
 
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: CHANDAN GUPTA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : 	VIEW EXTERNAL INV PROCESS
 ## Purpose						        : 
 ## Date of Creation					: 30/06/2016
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 

  -->
  <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <%@page import="new_investigation.vo.Inv_RequisitionRaisingPatientVO"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page import="new_investigation.vo.InvValueAuditVO"%>
<%@page
	import="new_investigation.transactions.controller.fb.viewExternalInvFB"%>
 <%@page import="new_investigation.vo.viewExternalInvVO"%>
	
<%@page import="new_investigation.vo.LabTestVO"%>
<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
<%@page import="java.awt.BorderLayout"%>
<%@page import="java.awt.TextArea"%>
<%@page import="java.awt.Frame"%>
<%@page import="java.awt.Color"%>
<%@page import="javax.swing.JTextArea"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page import="new_investigation.vo.RequisitionListVO"%>
<%@page import="new_investigation.vo.OnlinePatientAcceptanceVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page import="new_investigation.transactions.controller.fb.InvValueAuditFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
 
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
							
<his:css src="/hisglobal/css/jquery/jquery.ui.menu.css" />
<his:css src="/hisglobal/css/jquery-ui.css" />
<his:css src="/hisglobal/css/style.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.autocomplete.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
 <his:css src="/hisglobal/css/invPopupReport.css" />
 
<his:css src="/hisglobal/css/icon.css" />
<his:css src="/hisglobal/css/email.css" />
<his:css src="/hisglobal/css/textboxcss.css" />
<his:css src="/hisglobal/css/drop.css" />
 <his:css src="/hisglobal/css/Cannedstyle.css" />
 <link rel="stylesheet" href="/new_investigation/css/Date/site-demos.css"> 
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/commonUtility.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/cannedMacroValidation.js" />
<his:javascript src="/hisglobal/js/cannedMacroAutocomplete.js" />
<his:javascript src="/bloodbank/js/bloodRequisition.js" />
<his:javascript src="/new_investigation/js/reportsValidation.js" />
<his:javascript src="/new_investigation/js/onlinePatientAcceptance.js" />
<his:javascript src="/new_investigation/js/jquery-1.11.1.min.js" />
<his:javascript src="/new_investigation/js/jquery.validate.email.js" />
<his:javascript src="/new_investigation/js/additional-methods.min.js" />
<his:javascript src="/new_investigation/js/ckeditor/ckeditor.js"/>
<his:javascript src="/new_investigation/js/wysiwyg.js"/>
<his:javascript src="/new_investigation/js/wysiwyg-settings.js" />
<his:javascript src="/hisglobal/js/css-pop-report-inv.js" />


<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
	 
	 
<his:javascript src="/hisglobal/js/jquery-1.7.2.js" />

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="new_investigation.vo.template.ResultEntryVO"%>
<%@page import="new_investigation.vo.template.ResultEntryVOGroupByValidatedBy"%>



<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="new_investigation.transactions.controller.fb.InvValueAuditFB"%>

 
 
<script src="scripts/js/jquery-3.3.1.js" type="text/javascript"></script>
<script src="scripts/js/jquery.dataTables.min.js" type="text/javascript"></script>
<link href="media/dataTables/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />

<his:javascript src="/hisglobal/js/jquery-ui.js" />
<his:javascript src="/hisglobal/js/sweet-alert.min.js" />
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

<%@page import="new_investigation.InvestigationConfig"%>

<html>
<script>

function download(filename,id,contenttype)
{
	
	// alert(filename+id);
	 var idd=id+"_"+filename;
	document.getElementsByName('dowloadFile')[0].value=filename;
	document.getElementsByName('dowloadFileid')[0].value=idd;
	document.getElementsByName('dowloadFilecontent')[0].value=contenttype;
     submitForm("showFiles");

}


function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}


function cancelFunctn()
{
	submitForm("NEW");
	
	}


function pageLoad()
{
	
	  var element=document.getElementsByName("patCrNo")[0];
 	   //alert(element);
    element.setAttribute("onfocus","this.selectionStart = this.selectionEnd = this.value.length;this.select()");
    
    element.focus();
    	
	}

function showTestDetails(k,filenames,idd)
{

		 
//document.getElementById("headings"+k).style.display="";
document.getElementById("headingss"+k).style.display="";
document.getElementById("hideTest"+k).style.display="";
document.getElementById("showTest"+k).style.display="none";	
	
}


function hideTestDetails(k,filenames,idd)
{

		 
	//document.getElementById("headings"+k).style.display="none";
	document.getElementById("headingss"+k).style.display="none";
	document.getElementById("hideTest"+k).style.display="none";
	document.getElementById("showTest"+k).style.display="";	
	
}


function printReport(names,id,contenttype)
{
	var name=id+"_"+names;
	//alert(name);
	//document.getElementsByName('fileName')[0].value=name;
//document.getElementsByName('hmode')[0].value='PRINTREPORT';
	//document.forms[0].action="/AHIMS/investigationDesk/viewInvestigation.cnt"; 

//	document.forms[0].submit();
	
	
 	var mode='PRINTREPORT';
var strAllChkDetail = name;


var url = '/HISInvestigationG5/new_investigation/viewexternalprocess.cnt?hmode='+mode+"&fileName="+name+"&contentType="+contenttype;
 
//	alert("final url"+url);

AddRowToTableAddMoreValues(url,contenttype);
popup('popUpDiv');
 
//document.forms[0].submit();

}


function AddRowToTableAddMoreValues(finalMadCode,contenttype) {
       
	//alert(contenttype);
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

	td1.innerHTML = "<iframe id='setPdf' src='"+ finalMadCode + "' width='100%' height='450px' type='"+ contenttype + "'    ></iframe> ";
	td1.className = 'tdfont';
	td1.colspan = "1";

	tabRow.appendChild(td1);

	//document.forms[0].numberOfRow.value=numRows;
	}
	
	


function openPopup(url, eventObj, height, width) {
	if (eventObj.type == "click" || eventObj.keyCode == 13) {
		child = window.open(url, 'popupWindow',
				'status=yes,scrollbars=yes,height=' + height + ',width='
						+ width + ',left=10,top=10');
		child.moveTo(250, 250);
		child.focus();

		if (!child.opener)
			child.opener = self;
	}
}
 
 function validateCRNoCHE(hospitalCode)
 {
 	 var crno =document.getElementsByName("patCrNo")[0].value;
      var textLength = crno.length;
      var hosCodeLen=hospitalCode.length;
      
      if(hosCodeLen==3)
 		{ 
         	  if(textLength==13)
 	           {
          	document.getElementsByName('hmode')[0].value='SHOWPATDETAILS';
          	document.forms[0].submit();
 	            }
         	   
              

 	          else
 	          {     
 	            alert("InValid CR No");
 	            if(document.getElementsByName("patCrNo")[0])
 	            {
 	            document.getElementsByName("patCrNo")[0].focus()
 	             }    
 	           
 	          }
        }
       if(hosCodeLen==5)
     	{ 
 			   
 			    	
 			    	if(textLength==15)
 						 {
 						   
 						document.getElementsByName('hmode')[0].value='SHOWPATDETAILS';
 						document.forms[0].submit();
 					     }
 					
 			     else
 			     {     
 				   		    alert("InValid CR No");
 				     		if(document.getElementsByName("patCrNo")[0]){
 				            document.getElementsByName("patCrNo")[0].focus()
 				                      }    
 			          
 			     }
           }
       
   
  
    return true;
 }
 
</script>

<style type="text/css">
 a:hover {
  cursor:pointer;
 }
</style>
   
<style>
.NewTextBox {
	border:2px solid #456879;
	border-radius:10px;
	height: 22px;
	width: 330px;
}
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
  .ui-autocomplete {
    max-height: 100px;
    overflow-y: auto;
    /* prevent horizontal scrollbar */
    overflow-x: hidden;
  }
  
  
</style>

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

<script>
$(document).ready( function () {
    $('#example').DataTable({
    	"pageLength": 25
        });
} );

function viewUploadFile(val){
	//alert(val);
	   val="data:application/pdf;base64,"+val;
	  // alert(val);
	    var newwindow= window.open(val,"_blank", "menubar=no,location=no,resizable=no,scrollbars=no,status=yes,top=100,left=200,width=600,height=600");
}
</script>
<%
	String strdivage="\"\"";
String strdivdob="\"\""; 
String patCrNo="";  //style="width:90%;margin:auto;min-width:600px;max-width:2000px"
%>

<his:SubTitleTag name="Uploaded Document Viewing Process">
<body onload="pageLoad()">
</his:SubTitleTag>
<html:form action="/viewexternalprocess">
	<html:hidden name="viewExternalInvFB" property="hmode" />  
	<html:hidden name="viewExternalInvFB" property="currentPage" />
	<html:hidden name="viewExternalInvFB" property="showStatus" />
	<html:hidden name="viewExternalInvFB" property="dowloadFile" />
	<html:hidden name="viewExternalInvFB" property="dowloadFileid" />	
	<html:hidden name="viewExternalInvFB" property="dowloadFilecontent" />
	<%-- <his:InputCrNoTag name="viewExternalInvFB"></his:InputCrNoTag>		
	 --%>
	
	 <logic:notEqual name="viewExternalInvFB" property="hmode" value="SHOWPATDETAILS">
	 <logic:notEqual name="viewExternalInvFB" property="hmode" value="GETPATDTL">
	   		<his:InputCrNoInvestigationTag name="viewExternalInvFB">
			</his:InputCrNoInvestigationTag>
	 
	
	<his:ButtonToolBarTag>
	
<img class="button"
							src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
							tabindex="1" style="cursor: pointer"
							onkeypress="if(event.keyCode==13) submitForm('NEW');"
							tabindex="1" onclick="submitForm('NEW');">


	</his:ButtonToolBarTag>
	</logic:notEqual>	
	</logic:notEqual>	  		    
<logic:notEmpty name="<%=InvestigationConfig.LIST_PATIENT_VIEW_EXTERNAL_PROCESS_ESSENTIALS_VO%>">
<%
List<viewExternalInvVO> lstPatVO1=(List<viewExternalInvVO>)session.getAttribute(InvestigationConfig.LIST_PATIENT_VIEW_EXTERNAL_PROCESS_ESSENTIALS_VO);
viewExternalInvVO vo1=lstPatVO1.get(0);
%>
<table   width="100%" bgcolor="#b3b3ff">

<tr>

<td width="25%" align="right" >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						Cr NO </font></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
						
						<td width="25%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=vo1.getPatCrNo() %></font> 
						 
				  		</td>
				  		
				  		<!-- </tr>
				  		<tr> -->

<td width="25%" align="right"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						Patient Name </font></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>

						<td width="25%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=vo1.getPatName() %></font> 
						 
				  		</td>


</tr>



</table>	
</logic:notEmpty>	
	
	 <%boolean flag=false; %>
  	 <%
				//Pagination Logic
				/* 	PaginationFB fbPage= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage);
					fbPage.setCurrentPage(((viewExternalInvFB)request.getAttribute("viewExternalInvFB")).getCurrentPage());
					fbPage.setObjArrName(InvestigationConfig.LIST_PATIENT_VIEW_EXTERNAL_PROCESS_ESSENTIALS_VO);
					fbPage.setAppendInTitle("List ");
					int maxRecord=10;
					fbPage.setMaxRecords(maxRecord); */
				 
				 %>
				 
				 <logic:equal name="viewExternalInvFB" property="showStatus" value="0">
				 		<%-- <his:PaginationTag name="fbPagination"></his:PaginationTag> --%>
			
			<% flag=true; %>
			<logic:notEmpty name="<%=InvestigationConfig.LIST_PATIENT_VIEW_EXTERNAL_PROCESS_ESSENTIALS_VO%>">
			<table id="example" class="display " style="width:100%">
			<thead>
			<tr>
				<!-- <td width="5%" align="left"></td> -->
					<td width="20%">	
						<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"></font>
						Requisition Date 
					  	</b>
					</td>
					
					<td width="15%">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						Lab Name</font></b>
					</td>
					
					<td width="20%">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="TestName"/></font></b>
					</td>
					<!-- <td width="11%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						Requisition Date </font></b>
					</td> -->
					<td>
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						Files </font></b>
					</td>
				</tr>
				</thead>
			<!-- </table> -->
			<tbody>
			
			<!-- <table   width="100%" bgcolor="#EBEBEB" cellspacing="0" style="border-spacing: 0;"> -->
					<%
					 List<viewExternalInvVO> lstPatVO=(List<viewExternalInvVO>)session.getAttribute(InvestigationConfig.LIST_PATIENT_VIEW_EXTERNAL_PROCESS_ESSENTIALS_VO);
					 int i,size=0;
						if(lstPatVO!=null && lstPatVO.size()>0 )
			 			size=lstPatVO.size();
						
						//int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
						//int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
						String grpCode="";
						for(int j=0;j<lstPatVO.size();j++)
						{
							
							
							 /* List<String> lstfilesname=(List<String>)session.getAttribute(InvestigationConfig.LIST_PATIENT_VIEW_EXTERNAL_PROCESS_ESSENTIALS_VO_FILESNAME);			
							 List<String> lstfilesid=(List<String>)session.getAttribute(InvestigationConfig.LIST_PATIENT_VIEW_EXTERNAL_PROCESS_ESSENTIALS_VO_FILESID);			
							 List<String> lstfilescontenttype=(List<String>)session.getAttribute(InvestigationConfig.LIST_PATIENT_VIEW_EXTERNAL_PROCESS_ESSENTIALS_VO_FILESCONTENTTYPE); */			

                            String filename="";
                            String fileid="";
                            String fileconteenttype="";
                            String[] filesnameNew ;
                            String[] filesUploadData;
						//int i=j-startIndex;
						boolean firstTimeTravesall=true;
						//if(j<size)
						//{
							viewExternalInvVO voPat=lstPatVO.get(j);
						
							/* if(voPat.getFileName().contains("#@#")){
								filesnameNew=voPat.getFileName().split("#@#");
							}else{
								filename  = voPat.getFileName();
							}
						
							if(voPat.getFileUploadData().contains("#@#")){
								filesUploadData = voPat.getFileUploadData().split("#@#");
							}else{
								fileid = voPat.getFileUploadData();
							}
						 */
						
							//String  chkVal=filename+"_"+fileid;
							String value=voPat.getRequisitionNo();
							//String labCode=voPat.getLabCode();

						if(firstTimeTravesall)
			 			{
						   
					
						%>
					 
					
					<tr>
					<!-- <td></td> -->
						<td width="20%">
						<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getReqDate() %></font>
						</td>
				  		<td width="15%">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getLabName() %></font>
				  		</td>
				  		
				  		<td width="20%">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getTestName() %></font>
						</td>
				  		
				  		<%-- <td width="11%" align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getReqDate() %></font>
						 
				  		</td> --%>
				  		
				  		<td>
						<% 
						/* String showTest="showTestDetails("+j+",'"+filename+"','"+fileid+"')";
						 String hideTest="hideTestDetails("+j+",'"+filename+"','"+fileid+"')"; */
						 if(voPat.getFileName().contains("#@#")){
						 	for(int p = 0; p<voPat.getFileName().split("#@#").length;p++){
						 		
						%>
									
								 	<a href="javascript:viewUploadFile('<%=voPat.getFileUploadData().split("#@#")[p]%>');" > <%=voPat.getFileName().split("#@#")[p] %> </a> 
								<%--  <a onclick = "viewUploadFile('hello');" href="#"> <%=voPat.getFileName().split("#@#")[p] %> </a> --%>
						<%  	 
							 }
						}else{
			
			 			%> 
			 					<a href="javascript:viewUploadFile('<%=voPat.getFileUploadData()%>');"><%=voPat.getFileName() %></a>
			 						<%-- <a onclick = "viewUploadFile('hello');" href="#"><%=voPat.getFileName() %></a> --%>
			 			<%} %>
			  					<%-- <img class="button" title="Show Files Details"
									src='<his:path src="/hisglobal/images/plusinv.png"/>'
									id="showTest<%=j%>" tabindex="1" onclick="<%=showTest%>">
									<img class="button" title="Hide Test Details"
									src='<his:path src="/hisglobal/images/Minus.png"/>'
									id="hideTest<%=j%>" style="display: none;" tabindex="1"
									onclick="<%=hideTest %>"> --%>
						</td>
				  	</tr>
				  	<%} }%>
				  	</tbody>
					</table>
					  
							<%-- <tr>
								<th colspan="1"></th>
								<th colspan="7">
									<table bgcolor="#F8F8F8" style="display: none;" width="100%"
										id="headings<%=j%>" border="1">
										<tr>

											<th width="20%"><font color="red" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <u>
															Files </u></font></th>

										</tr> --%>
                     



										<!-- to display test and its value -->

										<%
				/* 	String[] idds=fileid.split("#");
					//String[] contentypes=fileconteenttype.split("#");					
					String[] filesname=filename.split("#");
					int paraSize=filesname.length;
					int count=1; */
						 
											%>
                <%--  <table bgcolor="#F8F8F8" style="display: none;" width="100%" 
										id="headingss<%=j%>" style="border='1';"> --%>
										 <%-- <tr>    
								
										                <%                                       
                 for(int iterate=0;iterate<paraSize;iterate++)
                    {
                	 int tempVal=count%2;
                	 if(tempVal==1)
			 	 	    {%><tr>
			 	 	    <%}%>
                     
                     
                                    <div align="center">
											<td width="25%"><font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif">	
												<input type="button" onclick="download('<%=filesname[iterate]%>','<%=idds[iterate]%>','<%=contentypes[iterate]%>')" name="Download"></input>
												<%=count+ "." %>&nbsp;<button onclick="download('<%=filesname[iterate]%>','<%=idds[iterate]%>','<%=contentypes[iterate]%>')">Download</button>
												<a onclick="printReport('<%=filesname[iterate]%>','<%=idds[iterate]%>','<%=contentypes[iterate]%>')" >	<%=filesname[iterate]%></a>
												
												
												</font>

											</td>
                                  </div>
									
                               <%if(tempVal==0)
				 	 	    {%></tr>
							<%} count++;
										}  %>
								</tr>	 --%>		
									<!-- </table> -->
								<!-- </th> -->
							<!-- </tr>		 -->
									
									
									
				<%-- 					<% }  }%>
					</table> --%>
		
		 <div id="container">


	 
    <div id="blanket" style="display:none;"></div>
	<div draggable="true" id="popUpDiv" style="display:none;height:650px " >
	<his:SubTitleTag name="Patient Upload Files">
    	
 
    	 <img src='/HISInvestigationG5/hisglobal/css/close.png'  onclick="popupClose('popUpDiv')"'>
 
  			</his:SubTitleTag>
	<table id="addMoreValue" width="100%">
<tr id="deleteRow">
 
</tr>	 
	
	 
	</table>
<left>
    	 
</left>
	</div>	
    <!-- end #mainContent --></div>
					
					 <his:ButtonToolBarTag>
     		 <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelFunctn() " onclick="cancelFunctn()" tabindex="1">  
	             </his:ButtonToolBarTag>
		
			</logic:notEmpty>
			<logic:empty name="<%=InvestigationConfig.LIST_PATIENT_VIEW_EXTERNAL_PROCESS_ESSENTIALS_VO%>">
				<center>
				<font color="red" size="4">
				<bean:message key="noRecord"/></font></center>
			</logic:empty>
			
			 </logic:equal>
					
					
					
	
	
	
	
</html:form>
</body>
</html>  