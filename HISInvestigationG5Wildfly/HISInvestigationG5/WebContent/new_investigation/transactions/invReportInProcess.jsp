<!-- 
 
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: CHANDAN GUPTA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : REPORT IN PROCESS form
 ## Purpose						        : 
 ## Date of Creation					: 14/10/2016
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 

  -->

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.nio.file.Files"%>
<%@page import="com.lowagie.text.PageSize"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="com.lowagie.text.rtf.RtfWriter2"%>
<%@page import="com.lowagie.text.pdf.PdfWriter"%>
<%@page import="com.lowagie.text.Document"%>
<%@page import="com.lowagie.text.Paragraph"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="new_investigation.vo.InvResultReportPrintingVO"%>
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
<%@page import="new_investigation.transactions.controller.fb.invReportInProcessFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>
<%@page import="new_investigation.vo.invReportInProcessVO"%>

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
<%-- <%@page import="hisglobal.tools.tag.PaginationFB"%> --%>
<%-- <%@page import="hisglobal.tools.tag.PaginationTag"%> --%>
 <%@page import="java.io.File"%>
<%@page import="java.io.*"%>


 
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
 
<his:css src="/hisglobal/css/icon.css" />
<his:css src="/hisglobal/css/email.css" />
<his:css src="/hisglobal/css/textboxcss.css" />
<his:css src="/hisglobal/css/drop.css" />
 <his:css src="/hisglobal/css/invPopupReport.css" />
<%--  <his:css src="/hisglobal/css/jquery-ui.css" /> --%>
 <link rel="stylesheet" href="/new_investigation/css/Date/site-demos.css"> 
 
 
<his:javascript src="/hisglobal/js/validation.js" /> 
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/bloodbank/js/bloodRequisition.js" />
<his:javascript src="/new_investigation/js/reportsValidation.js" />
<his:javascript src="/new_investigation/js/onlinePatientAcceptance.js" />
<his:javascript src="/new_investigation/js/jquery-1.11.1.min.js" />
<his:javascript src="/new_investigation/js/jquery.validate.email.js" />
<his:javascript src="/new_investigation/js/additional-methods.min.js" />
<his:javascript src="/hisglobal/js/jquery-1.7.2.js" />
<his:javascript src="/hisglobal/js/css-pop-report-inv.js" />
 <his:javascript src="/hisglobal/js/base64.js" />
 <his:javascript src="/hisglobal/js/sprintf.js" />
 
  <his:javascript src="/hisglobal/js/jspdf.js" />
 <script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>

<!--Added by prachi  -->
<script src="scripts/js/jquery-3.3.1.js" type="text/javascript"></script>
 <script src="scripts/js/jquery.dataTables.min.js" type="text/javascript"></script>
<link href="media/dataTables/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />  


<script src="media/jquery-ui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<link href="media/jquery-ui/1.12.1/jquery-ui.min.css" rel="stylesheet" type="text/css" />
 
 
<script type="text/javascript" src="media/misc/moment.min.js"></script>
<script type="text/javascript" src="media/misc/datetime-moment.js"></script>
<script src="media/common/js/commonDateOperations.js" type="text/javascript"></script>


<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="new_investigation.vo.template.ResultEntryVO"%>
<%@page import="new_investigation.vo.template.ResultEntryVOGroupByValidatedBy"%>



<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="new_investigation.transactions.controller.fb.InvResultReportPrintingFB"%>

  
 
<his:javascript src="/hisglobal/js/jquery-ui.js" />
<his:javascript src="/hisglobal/js/sweet-alert.min.js" />
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
 <script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script> 


<%@page import="new_investigation.InvestigationConfig"%>

<html>


<script type="text/javascript" language="javascript" class="init">

 /* Datatable Added by Prashant */
/*$(document).ready(function() {

     $('#tableList').dataTable(
       {//'iDisplayLength': 7,//Set Row Per Page
       //"bFilter": false,//Remove Search
       //"bPaginate" : false,//Remove Pagination
      // "bInfo": false,//Remove Page Info
       //"bLengthChange":false,//Show per Page Dropdown Remove
       "columnDefs": [ { "targets": 0, "orderable": false } ]//,//Remove Colum Orderable(Here Col 0 Remove)
      // "aoColumns": [{ "asSorting": [] },{ "asSorting": [ "asc" ] },{ "asSorting": [ "desc", "asc" ] },{ "asSorting": [ "desc" ] },null],//Set Colum Order By ASC Or DSC
      // "sPaginationType": "full_numbers"//Full Pagination
      } 
      );

			if($('#tableList').length && $('#tableList tr').length && $('#tableList tr').length>=10){
				//alert($('#tableList tr').length);
				$('#tablelistTfoot').show();}
			else{$('#tablelistTfoot').hide();}

			$('#tableList_filter input').on('click change focus blur keyup', function(e){
				if($('#tableList').length && $('#tableList tr').length && $('#tableList tr').length>=10){
					//alert($('#tableList tr').length);
					$('#tablelistTfoot').show();}
				else{$('#tablelistTfoot').hide();}
				
				});
    });
     */
    
    

    
    
$(document).ready(
	    function setDataTable1(){
	    	$.fn.dataTable.moment( 'DD-MMM-YYYY' );
	    	if ($('#tableList').length)
	    	var table = $('#tableList').DataTable( {
	        	/* "dom": '<<ip><lf>rt>', */
				pageLength : 10,
				
				"language": {
				      "emptyTable": "No Data Is Available "
				    },
	            lengthMenu: [
	              [ 10, 25, 50, -1 ],
	              [ '10 rows', '25 rows', '50 rows', 'Show all' ]
	       	     ],
	             pagingType: "full_numbers",
	             rowReorder: true,
	             columnDefs: [
	             /* { orderable: true, className: 'reorder', targets: 0 }, */
	             { orderable: false, targets: 0 }
	             /*  { type: 'date', 'targets': [9] } */
	             ],
	             "deferRender": true
	     });
	});

</script>




 <script type="text/javascript">
 function onClickGO(hospitalCode)
 {
 	 var crno =document.getElementsByName("tempPatCRNo")[0].value;
      var textLength = crno.length;
      var hosCodeLen=hospitalCode.length;
      
      
    /*  if(!validateTodayOrDate())
     	 {return false;} */
      
   
       if(hosCodeLen==5)
     	{ 
 			    if (textLength==7||textLength==15||textLength==0)
 				 {
 			    	 document.getElementsByName("patCRNo")[0].value="";
 			    	if(textLength==15)
 						 {
 						 document.getElementsByName("patCRNo")[0].value=crno;
 						 }
 					          
 					       	 
 					           
 					        
 					     
 					       	 if(document.getElementsByName("tempPatCRNo")[0].value=="-1")
 					            {
 					           	alert("Select    Name ... ");
 					       	    document.getElementsByName("tempPatCRNo")[0].focus();
 					       	    return false;
 					            }  
 					        
 					       	
 					      
 					        
 					       	
 					    
 					   


 					 //allPatient
 					           
 					       	document.getElementsByName('hmode')[0].value="GETDETAILS";
 					       	document.forms[0].submit();
 					       	   
 					     }
 					
 			     else
 			     {     
 				   		    alert("InValid CR No");
 				     		if(document.getElementsByName("tempPatCRNo")[0]){
 				            document.getElementsByName("tempPatCRNo")[0].focus()
 				                      }    
 			          
 			     }
           }
    return true;
 }

function submitForm1(mode)
{
      var count=0;
for(var i=0;i<document.getElementsByName('chkSamplePatient').length;i++)
	{
        if(document.getElementsByName('chkSamplePatient')[i].checked==true)
            {
              count++;
            }
	}

if(count==0)
	{
alert("Please Select Atleast one Record");
	}
else
submitForm(mode);

}
 
/*  function doPagination(e,p)
 {
 	
 	document.getElementsByName('currentPage')[0].value=p;
 	document.getElementsByName('hmode')[0].value='PAGINATION';
 	document.forms[0].submit();
 } */



 var isChecked = false;
 
 function allSelected() 
 {
 	document.getElementById("nextDiv").style.display=""; 
 	// this line is for toggle the check
     isChecked = !isChecked;
     //below line refers to 'jpCheckbox' class
     $('input:checkbox.jpCheckbox').prop('checked',isChecked);
     //OR,
     //$('input:checkbox.jpCheckbox').attr('checked','checked');
 }


 function ValidateSameCrNo(obj)
 {
 	
 	if(obj.checked)
 	{
 		if(document.getElementById("nextDiv")==null)
     	{
     		alert("Pdf Is Not Generated");
     	}
     	else
     		{
     		
     	document.getElementById("nextDiv").style.display=""; 
     		}
 		
 		  
                 
 	}
   else
      	{
 	  document.getElementById('gob').style.display="";
       	document.getElementById('cancel').style.display="";
       	
           }
 	 
 	var objCurrentCheckBox=obj.value;
 	//alert(obj.checked);
 	/* if(obj.checked)
 	{
 		
 		var cbs = document.getElementsByTagName('input');
 		for(var i=0; i < cbs.length; i++) 
 		{
 			    if(cbs[i].type == 'checkbox') 
 			    {
 			      
 			    	 if(cbs[i].checked && (cbs[i].value.split("#")[0]!=objCurrentCheckBox.split("#")[0]))
 			    	{
 			    		 
 			    	alert("please select same cr no");
 			    	document.getElementById('nextDiv').style.display="none";
 			    	obj.checked=false;
 			    	return false;
 			    	} 
 				}
 		}
 	} */
  
 }
 
</script>



 
 
   

  


      


<body onload="">
<html:form action="/invReportInProcess">
	<html:hidden name="invReportInProcessFB" property="hmode" />
	
	 <html:hidden name="invReportInProcessFB" property="showStatus" />
	 <%-- <html:hidden name="invReportInProcessFB" property="currentPage" /> --%>
	  <html:hidden name="invReportInProcessFB" property="patCRNo" />
	  	  <his:TitleTag name="Result Report Printing">
		<%-- 	<his:insertDateTag/> --%>
		</his:TitleTag>
		  
		  <%
			  String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
         %>
          <bean:define name="invReportInProcessFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="invReportInProcessFB" property="toDate" id="tDate" type="java.lang.String"/>          
	   <%
         if(frDate==null||frDate.equalsIgnoreCase(""))
         {  
        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
        	frDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
         }
  
		  if(tDate==null||tDate.equalsIgnoreCase(""))
		  {  
		  	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
		  	tDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		   } 
    	%> 
		<table width="100%" border="0" cellspacing="2" cellpadding="0" >
			    
			     <tr>
			     
			     <td class="tdfont" width="25%">
        		<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromDate"/>&nbsp;
					</font>
        		</div>
			</td>
			<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left">	               		 
					&nbsp;&nbsp;&nbsp;<his:date  name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
				</div>
			</td>
 			<td class="tdfont" width="25%">
        		<div id='divfromDate' style='<%=toDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="toDate"/>&nbsp;
					</font>
        		</div>
			</td>
			<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' style='<%=toDateControl %>'align="left">	               		 
					&nbsp;&nbsp;&nbsp;<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
				</div>
			</td>
			     </tr>
			     <tr>
			     <td width="25%" class="tdfont">
			    <div align="right" id="showOnLoad" >
			    
			    <bean:message key="crNO"/>&nbsp;
			    </div>
			    </td>
			      <td width="25%" class="tdfont">
			      <div align="left" id="patientwise" >                                                                                                           
					 &nbsp;&nbsp;&nbsp; <input type="text" id="textBoxCss" name="tempPatCRNo"  maxlength="15" size="20"  onkeypress="return validateNumeric(event,this)" tabindex="1"> 
				   </div>  
				   </td>
				   <td width="25%" class="tdfont">
			     
			       <div align="left" id="patientwisename" >                                                                                                           
					 Search Name&nbsp;<input type="text" id="textBoxCss" name="tempPatName"  maxlength="20" size="20"  onkeypress="return validateAlphabetsOnly(event,this)" tabindex="1">
				   </div>
				   </td>
				   <td width="25%" class="tdfont">
				   </td>
				   </tr>
			     <%
			      UserVO uservo=ControllerUTIL.getUserVO(request);
			      Date todayDateobj = new Date();
					SimpleDateFormat dateob = new SimpleDateFormat("yy");
					String strDate= dateob.format(todayDateobj);
			      String hospitalCode=uservo.getHospitalCode();
			      String val=uservo.getHospitalCode()+strDate;
			      %>
			      <tr>
			   <td class="tdfont" align="center" colspan="2" width="25%">
			  <div align="right">
			  <img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) onClickGO('<%=hospitalCode%>')" onclick="onClickGO('<%=hospitalCode%>') " tabindex="1">
			   </div>
			   </td>
		
			  
			   <td class="tdfont" align="center" colspan="2" width="25%">
			  <div align="left">
			 <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelFunc()" onclick="cancelFunc()" tabindex="1">
			   </div>
			   </td>
			   </tr> 
			     </table >
			    <!-- Commented by prashant -->
    	 <%boolean flag=false; %>
  	 <%-- <%
				//Pagination Logic
					PaginationFB fbPage= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage);
					fbPage.setCurrentPage(((invReportInProcessFB)request.getAttribute("invReportInProcessFB")).getCurrentPage());
					fbPage.setObjArrName(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO_INPROCESS);
					fbPage.setAppendInTitle("List ");
					int maxRecord=100;
					fbPage.setMaxRecords(maxRecord);
				 
				 %> --%>
				 
				       
				 	<%-- 	 <his:PaginationTag name="fbPagination"></his:PaginationTag>   --%>
			<logic:present name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO_INPROCESS %>">
			<%flag=true; %>
			<table id="tableList"  width="100%"   bgcolor="#EBEBEB" >
				<thead>
				<tr>
					<th width="3%" align="left"  >	
						<b> <font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif">
					<input type="checkbox" id="selectAllCheckbox" onclick="allSelected()" /> </font>
	                  </b>
					</th>
					<th width="15%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="crNO"/></font></b>
					</th>
					<th width="15%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="patientName"/></font></b>
					</th>
					<th width="10%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="age/gender"/></font></b>
					</th>
					<th width="15%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="departmentunit"/></font></b>
					</th>
					<th width="12%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="TestName"/></font></b>
					</th>
					<th width="11%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="labName"/></font></b>
					</th>
							<th width="8%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="sampLabNo"/></font></b>
					</th>
					
					<th width="9%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="patStatus"/></font></b>
					</th>
				</tr>
				</thead>
			<!-- Commented by prashant -->
			<%-- <logic:empty name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO_INPROCESS %>">
				<tfoot>
				<center><font color="red" size="4">
				<bean:message key="noRecord"/></font></center></tfoot>
			</logic:empty> --%>
			
			<logic:notEmpty name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO_INPROCESS %>">
		
			 <tbody>
					<%
					 List<invReportInProcessVO> lstPatVO=(List<invReportInProcessVO>)session.getAttribute(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO_INPROCESS);
					 int i,size=0;
			 		if(lstPatVO!=null && lstPatVO.size()>0 )
			 			size=lstPatVO.size();
			 		/* commented by prashant */
					/* int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
					int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue(); */
					String grpCode="";
				/* 	for(int j=startIndex;j<=endIndex;j++) */
						for(int j=0;j<size;j++)
				{
					//int i=j-startIndex;
				 	/* boolean firstTimeTravesall=true; */
				/*   if(j<size)
								{ */
					invReportInProcessVO voPat=lstPatVO.get(j);
					/* String  chkVal=voPat.getPatPuk()+","+voPat.getRequisitionNo()+","+voPat.getRequisitionDNo()+","+voPat.getGroupCode()+","+voPat.getReportUrl(); */
					String chkVal=voPat.getPatPuk()+"@"+voPat.getPatReqNo()+"@"+voPat.getRequisitionDNo()+"@"+voPat.getTestCode()+"@"+voPat.getSampleNo()+"@"+voPat.getLabCode()+"@"+voPat.getPatAge()+"@"+voPat.getPatGender()+"@"+voPat.getReportAvailableAfter()+"@"+voPat.getPatVisitDat()+"@"+voPat.getPatVisitNo()+"@"+voPat.getLabNo()+"@"+voPat.getEpisodeCode()+"@"+voPat.getDepartmentcode()+"@"+voPat.getPatdeptunitcode()+"@"+voPat.getRequisitionTypeCode()+"@"+voPat.getTestName()+"@"+voPat.getPatLabName()+"@"+voPat.getSampleName()+"@"+voPat.getTempSampleNo()+"@"+voPat.getGroupCode()+"@"+voPat.getTestParaMeterCode()+"@"+voPat.getPatName()+"@" +"@"+voPat.getDetpUnitCode()+"@"+voPat.getPatUnitName();


// 				if(voPat.getGroupCode()!=null)
// 				{   	 
// 					grpCode+='&';
// 					String[] SplitGrpCode=grpCode.split("&");
// 					int length=SplitGrpCode.length;
// 					if(SplitGrpCode.length>1)
// 					for(int x=0;x<SplitGrpCode.length;x++)
// 					{
//                     if(SplitGrpCode[x].equals(voPat.getRequisitionNo()+voPat.getGroupCode()))
//                     {
// 					 firstTimeTravesall=false;
//                     }
//                     else
//                     {
//                     	 firstTimeTravesall=true;	
//                     }
// 				} 	 
// 					 grpCode+=voPat.getRequisitionNo()+voPat.getGroupCode();  				 
// 				}
				 

				/*  if(firstTimeTravesall)
		 			{ */ 
				
				%>   
					<tr>
						<td width="3%" align="left"  >
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<input type="checkbox" class="jpCheckbox" name="chkSamplePatient" value='<%=chkVal%>' onclick="ValidateSameCrNo(this)" >
							</font>
						</td>
						<td width="15%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatPuk() %></font> 
				     		 
				  		</td>
				  		
				  		<td width="15%" align="left"  >
				  		 
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatName() %></font>
						  
				  		
				  		</td>
				  		<td width="10%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatAge()+"/"+voPat.getPatGender()%></font>
						  
				  		</td>
				  		 <td  width="15%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatUnitName() %></font>
						  
				  		</td>
				  		<td width="12%" align="left">
				  		 
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 		<%=voPat.getGroupName().equals("NA")?voPat.getTestName():voPat.getGroupName() %></font>
						  
				  		</td>
				  		<td width="11%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatLabName() %></font>
						 
				  		</td>
				  		
				  			<td width="8%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getTempSampleNo()==null?voPat.getLabNo():voPat.getTempSampleNo() %></font>
						  
						   
						  
				  		</td>
				  		
				  		
				  		<td width="9%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatStatus() %></font>
						  
						   
						  
				  		</td>
					</tr>
				<%-- 	<%}  } }%>  --%>
				<%}%> 
					
					
					</tbody>
					<tfoot id="tablelistTfoot">
				<tr>
					<th width="3%" align="left"  >	
						<b> <font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif">
					<input type="checkbox" id="selectAllCheckbox" onclick="allSelected()" /> </font>
	                  </b>
					</th>
					<th width="15%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="crNO"/></font></b>
					</th>
					<th width="15%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="patientName"/></font></b>
					</th>
					<th width="10%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="age/gender"/></font></b>
					</th>
					<th width="15%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="departmentunit"/></font></b>
					</th>
					<th width="12%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="TestName"/></font></b>
					</th>
					<th width="11%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="labName"/></font></b>
					</th>
							<th width="8%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="sampLabNo"/></font></b>
					</th>
					
					<th width="9%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="patStatus"/></font></b>
					</th>
				</tr>
				</tfoot>
				
			</logic:notEmpty>
			
			
			
			</table>
			</logic:present>
			
			 
			
				    
				    
	<his:ButtonToolBarTag>
	
				      <img class="button"   src='<his:path src="/hisglobal/images/btn-generate.png"/>' id="nextDiv"  style="cursor:pointer;display: none;"   tabindex="1" onclick ="submitForm1('SAVE');" >
	<img class="button"
					src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"
					style="cursor: pointer"
					onkeypress="if(event.keyCode==13) cancelFunc();"
					tabindex="1" onclick="submitForm('CANCEL');">
				    
	</his:ButtonToolBarTag>
    
	 	    
</html:form>
</body>
</html>