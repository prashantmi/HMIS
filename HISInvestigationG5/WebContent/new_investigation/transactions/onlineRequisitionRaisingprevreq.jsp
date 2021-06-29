<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="new_investigation.vo.Inv_RequisitionRaisingPatientVO"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>

<%@page import="new_investigation.vo.Inv_ictc_VO"%>
<%@page import="new_investigation.transactions.controller.fb.InvestigationRaisingDtlXrayFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/AppointmentTools.tld" prefix="appt"%>
<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>

<%@page import="java.util.*"%>
<his:javascript src="/hisglobal/js/calendar.js" />
<%@page import="hisglobal.hisconfig.Config"%>

<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Xray Raising Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<his:css src="/new_investigation/media/echo/bootstrap.min.css" />

<his:javascript src="/new_investigation/media/bootstrap4/echo/jquery.min.js" />
<his:javascript src="/new_investigation/media/bootstrap4/echo/bootstrap.min.js" />
<his:css src="/new_investigation/media/echo/glymph.css" />
<his:css src="/new_investigation/media/echo/bootstrap-multiselect.css" />

<his:css src="/new_investigation/media/xray/font-awesome.min.css" />


<his:javascript src="/new_investigation/media/bootstrap4//echo/bootstrap-multiselect.js" />


<his:css src="/new_investigation/media/xray/display.css" />


	
	<his:javascript src="/new_investigation/media/xray/jquery.dataTables.min.js" />
	
	<his:javascript src="/new_investigation/media/xray/dataTables.buttons.min.js" />
	
	<his:javascript src="/new_investigation/media/xray/jszip.min.js" />
	<his:javascript src="/new_investigation/media/xray/pdfmake.min.js" />
	<his:javascript src="/new_investigation/media/xray/vfs_fonts.js" />
	<his:javascript src="/new_investigation/media/xray/buttons.html5.min.js" />
	<his:javascript src="/new_investigation/media/xray/buttons.print.min.js" />


<his:css src="/new_investigation/media/xray/sweetalert.css" />

	<his:javascript src="/new_investigation/media/xray/sweetalert.js" />

<his:css src="/new_investigation/media/xray/jquery.dataTables.min.css" />
<his:css src="/new_investigation/media/xray/buttons.dataTables.min.css" />

	
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
	<his:css src="/hisglobal/css/jquery/jquery.ui.autocomplete.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.menu.css" />
<his:css src="/hisglobal/css/jquery-ui.css" />

<script type="text/javascript"
	src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js"
	djConfig="parseOnLoad: true"> </script>
	
		<his:javascript src="/new_investigation/js/appointment.js" />
	
	<his:css src="/new_investigation/media/xray/jquery.fancybox.css" />

	<his:javascript src="/new_investigation/media/xray/jquery.fancybox.js" />
	
	<his:javascript src="/new_investigation/media/xray/modernizr.min.js" />
	<his:css src="/new_investigation/media/xray/css/font-awesome.min.css" />
<his:css src="/new_investigation/media/xray/css/font-awesome.css" />
<his:css src="/new_investigation/media/xray/animate.min.css" />
		
	
	
	
<style>


#snackbar {
  visibility: hidden;
  min-width: 250px;
  margin-left: -125px;
  background-color: #333;
  color: #fff;
  text-align: center;
  border-radius: 2px;
  padding: 16px;
  position: fixed;
  z-index: 1;
  left: 50%;
  bottom: 30px;
  font-size: 17px;
}

#snackbar.show {
  visibility: visible;
  -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
  animation: fadein 0.5s, fadeout 0.5s 2.5s;
}

@-webkit-keyframes fadein {
  from {bottom: 0; opacity: 0;} 
  to {bottom: 30px; opacity: 1;}
}

@keyframes fadein {
  from {bottom: 0; opacity: 0;}
  to {bottom: 30px; opacity: 1;}
}

@-webkit-keyframes fadeout {
  from {bottom: 30px; opacity: 1;} 
  to {bottom: 0; opacity: 0;}
}

@keyframes fadeout {
  from {bottom: 30px; opacity: 1;}
  to {bottom: 0; opacity: 0;}
}


select.custom{
  background-image: url("data:image/svg+xml;charset=utf-8,%3Csvg%20version%3D%221.1%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20xmlns%3Axlink%3D%22http%3A%2F%2Fwww.w3.org%2F1999%2Fxlink%22%20x%3D%220px%22%20y%3D%220px%22%20fill%3D%22%23555555%22%20%0A%09%20width%3D%2224px%22%20height%3D%2224px%22%20viewBox%3D%22-261%20145.2%2024%2024%22%20style%3D%22enable-background%3Anew%20-261%20145.2%2024%2024%3B%22%20xml%3Aspace%3D%22preserve%22%3E%0A%3Cpath%20d%3D%22M-245.3%2C156.1l-3.6-6.5l-3.7%2C6.5%20M-252.7%2C159l3.7%2C6.5l3.6-6.5%22%2F%3E%0A%3C%2Fsvg%3E");
  padding-right: 25px;
  background-repeat: no-repeat;
  background-position: right center;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
}



select.custom::-ms-expand {
  display: none;
}

 .ui-autocomplete {
     max-height: 100px;
     overflow-y: auto;
     /* prevent horizontal scrollbar */
     overflow-x: hidden;
 }
 
 .ui-autocomplete{width:180px!important;margin-left:16}
 
  .ui-autocompletesearch {
     max-height: 100px;
     overflow-y: auto;
     /* prevent horizontal scrollbar */
     overflow-x: hidden;
 }
 
 .ui-autocompletesearch{width:200px!important;margin-left:}
 
 
.navbarcrno{

  
   /* Permalink - use to edit and share this gradient: https://colorzilla.com/gradient-editor/#cfe7fa+0,6393c1+100;Grey+Blue+3D */
background: rgb(207,231,250); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(207,231,250,1) 0%, rgba(99,147,193,1) 100%); /* FF3.6-15 */
background: -webkit-linear-gradient(top,  rgba(207,231,250,1) 0%,rgba(99,147,193,1) 100%); /* Chrome10-25,Safari5.1-6 */
background: linear-gradient(to bottom,  rgba(207,231,250,1) 0%,rgba(99,147,193,1) 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#cfe7fa', endColorstr='#6393c1',GradientType=0 ); /* IE6-9 */

    }
    
    .navbar{

  
background: #36D1DC;  /* fallback for old browsers */
background: -webkit-linear-gradient(to bottom, #5B86E5, #36D1DC);  /* Chrome 10-25, Safari 5.1-6 */
background: linear-gradient(to bottom, #5B86E5, #36D1DC); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */



}
    
    
    
    .cc{

  
   /* Permalink - use to edit and share this gradient: https://colorzilla.com/gradient-editor/#cfe7fa+0,6393c1+100;Grey+Blue+3D */
background: rgb(207,231,250); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(207,231,250,1) 0%, rgba(99,147,193,1) 100%); /* FF3.6-15 */
background: -webkit-linear-gradient(top,  rgba(207,231,250,1) 0%,rgba(99,147,193,1) 100%); /* Chrome10-25,Safari5.1-6 */
background: linear-gradient(to bottom,  rgba(207,231,250,1) 0%,rgba(99,147,193,1) 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#cfe7fa', endColorstr='#6393c1',GradientType=0 ); /* IE6-9 */

    }
	
	
	a:hover {
	cursor: pointer;
}



.body{
font-family: 'Gentium Basic', serif;
font-family: 'Sanchez', serif;}

.navbar-nav > li > a {padding-top:0px !important; padding-bottom:1px !important;color:black;}
.navbar-header >a {color:black}
a:hover {
	cursor: pointer;
}

li:hover {
	cursor: pointer;
}


.anyClassscrollable {
	height: 170px;
	overflow-y: scroll;
}

div {margin: 0px 0px 0px 0px; } 

/* .anyClassscrollabletestshow {
	height: 250px;
	overflow-y: scroll;
} */

.anybookmarkClassscrollable {
	height: 150px;
	overflow-y: scroll;
}

.anyappClassscrollable {
	height: 150px;
	overflow-y: scroll;
}

.anyClassscrollablepatdetails {
	height: 100px;
	overflow-y: scroll;
}

.bd-labWiseTestModal-modal-lg .modal-header {
	background-color: #9999ff;
	color: black;
}

.bd-labWiseTestModal-modal-lg .modal-body #testList {
	font-weight: bold;
	font-size: 10;
}

@-moz-document url-prefix () {fieldset {
	display: table-cell;
}
}
</style>
<script type="text/javascript">
var test="";

function setprevreqall()
{

	var prevreqsetval= document.getElementsByName("prevreqfromwhichcall")[0].value;
	 //  alert(prevreqsetval);
	  document.getElementById("setprevreqnew").value = prevreqsetval;
		
}




function setprevreq(obj)
{

 
	document.getElementsByName("prevreqfromwhichcall")[0].value=obj.value;
		
	document.getElementsByName('hmode')[0].value="DELETEAFTERCALL";
	//alert("aab"+document.getElementsByName("hmode")[0].value);
	//document.forms[0].hmode.value='SAVEXRAYPROCESS';
	document.forms[0].submit();

}



function deleteRowPrvReqDtl(labCode,testCode,reqNo,testGroupCode1,testname,viewcount,totalreqviewcount,type,crno)

{
	test=testname;
	// alert("testGroupCode1"+testGroupCode1);
	var msgg="Are You Sure to Delete Test "+testname+" ?";
	var sumsgg="Test "+testname+" has been Successfully deleted.";
	
	swal({
  title: msgg,
  text: "",
  type: "warning",
  showCancelButton: true,
  confirmButtonClass: "btn-danger",
  confirmButtonText: "Yes, delete it!",
  closeOnConfirm: false
},
function(){

	var isdelete=false;
	  if(checkBillingDetail(labCode,testCode,reqNo) == 1){

		//  alert("checkbill");
  		  isdelete= updateReqTable(labCode,testCode,reqNo,"1",testGroupCode1,test,viewcount,totalreqviewcount,type,crno);

	  }
	  else
  	  {
		////  alert("not checkbill");
		  isdelete= updateReqTable(labCode,testCode,reqNo,"2",testGroupCode1,test,viewcount,totalreqviewcount,type,crno);
  	  }
	//  alert("checkbillfinal"+isdelete);
	//  showprevreqmodal(testname);
 // swal("Deleted!", sumsgg, "success");

 setTimeout(function () { 
swal({
  title: "Deleted!",
  text: "",
  type: "success",
  confirmButtonText: "OK"
},
function(isConfirm){
  if (isConfirm) {

	  var x = document.getElementById("setprevreqnew").selectedIndex;

	  document.getElementsByName("prevreqfromwhichcall")[0].value=x;

	  
	  document.getElementsByName('hmode')[0].value="DELETEAFTERCALL";
		//alert("aab"+document.getElementsByName("hmode")[0].value);
		//document.forms[0].hmode.value='SAVEXRAYPROCESS';
		document.forms[0].submit();
	   
  }
}); }, 1000);
 
  
  

});
	
	 /* var retVal = "";
	 if(testGroupCode1=="0")
		 {
		  retVal = confirm("Are You Sure to Delete it?");
		 }
	 else
		 {
		  retVal = confirm("Are You Sure to Delete Group?");
		 }
		 
	 // var retVal = confirm("Are You Sure to Delete it?");
    if( retVal == true ){

  	  document.getElementsByName('delTestCode')[0].value=testCode;
  	  document.getElementsByName('delLabCode')[0].value=labCode;
  	  if(checkBillingDetail(labCode,testCode,reqNo) == 1){

  		  updateReqTable(labCode,testCode,reqNo,"1",testGroupCode1);
      	  
      	  showPrvDetail(); 
        }
  	  else
      	  {
      	 // alert("The Requisition can be cancelled only if billing has been done.");
  		  updateReqTable(labCode,testCode,reqNo,"2",testGroupCode1);
      	  
      	  showPrvDetail(); 

      	  }

      	
    }
    else{
   	  //alert("User does not want to continue!....");
       return false;
    } */
	 
	   
	  
}

function updateReqTable(labCode,testCode,reqNo,isbilledornot,groupraisedalready,testName,viewcount,totalreqviewcount,type,crno)
{
	//  alert("inside del");
	//alert(reqNo);
	/* document.getElementsByName("requisitionNo")[0].value=reqNo; */
	//document.getElementsByName('requisitionNo')[0].value=reqNo;
	var flg = false;
	var isRequisitonRaisingPresent = false;
	var _mode = "DELETEREQDTL";  
	var objXHR = {url: "/HISInvestigationG5/new_investigation/onlineRequisitionRaisingprevreq.cnt?hmode="+_mode+"&delLabCode="+labCode+"&delTestCode="+testCode+"&requisitionNo="+reqNo+"&isbilledornot="+isbilledornot+"&groupraisedalready="+groupraisedalready+"&searchTestName="+testName+"&totalviewcount="+viewcount+"&totalreqviewcount="+totalreqviewcount+"&totalreqviewtyp="+type+"&patCrNo="+crno, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			isRequisitonRaisingPresent = data;
			flg = true;
		},
        error: function(error)
        {
            //if(typeof objKitchenList == 'undefined' || objKitchenList==null || objKitchenList=="" || objKitchenList.length==0)
            	//alert("No Kitchen Found");
            //alert(error+"Error while populating Meal Time Information");
            isRequisitonRaisingPresent = false;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return flg;
}


 function checkBillingDetail(labCode,testCode,reqNo)
{
	//alert("checkbill");
	var flg = false;
	var isBillingDone = false;
	var _mode = "CHECKBILLING";  
	var objXHR = {url: "/HISInvestigationG5/new_investigation/onlineRequisitionRaisingprevreq.cnt?hmode="+_mode+"&delLabCode="+labCode+"&delTestCode="+testCode+"&requisitionNo="+reqNo, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			isBillingDone = data;
			flg = true;
		},
        error: function(error)
        {
            //if(typeof objKitchenList == 'undefined' || objKitchenList==null || objKitchenList=="" || objKitchenList.length==0)
            	//alert("No Kitchen Found");
            //alert(error+"Error while populating Meal Time Information");
            isBillingDone = false;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return isBillingDone;
}

 

$(document).ready(function() {

	var tests='<%=(String)session.getAttribute("testName")%>';

//alert("tests"+tests);

    if(tests=="")
        {
	$('#preqvreqtbl').dataTable( {
	    "destroy": true,
	    "pageLength":6,
	    columnDefs: [ { type: 'date', 'targets': [5] } ],
	    order: [[ 5, 'desc' ]],
	    "pageLength":6,
	    "lengthMenu": [6,12, 18, 24, 30, 100],
	    
	  } );
        }
    else
        {

    	$('#preqvreqtbl').dataTable( {
    	    "destroy": true,
    	    "pageLength":6,
    	    columnDefs: [ { type: 'date', 'targets': [5] } ],
    	    order: [[ 5, 'desc' ]],
            "oSearch": {"sSearch": tests },
    	    "lengthMenu": [6,12, 18, 24, 30, 100],
    	    
    	    
    	  } );
  	  

        }
	  
	   
});


</script>


</head>

<body onload="setprevreqall()">
	<html:form action="/onlineRequisitionRaisingprevreq">



	 <!-- <div class="modal fade" id="myModalHorizontalprevreq" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog"  style="width:1250px;background-color: ">
			<div class="modal-content">
				Modal Header
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
					</button>
					 <span class="modal-title" id="myModalLabel" style="font-size: 17"><b>Previous Requisition</b></span>
					  <span class="modal-title" id="" style="font-size: 17"><b>&nbsp;</b></span>
				</div>
        
				Modal Body
				<div class="modal-body"> -->

				    <div><h4>Previous Requisition </h4>
				    
				    	<select name="setprevreqnew" id="setprevreqnew" style='margin-right:80' onchange='setprevreq(this)'><option value='0'>From Last 3 Months</option><option value='1'>All</option></select>
				    </div><div></div><div></div>
					
    <div><h4>&nbsp;</h4></div><div></div><div></div>

  <%
    
  Map<String,LabTestVO> _mppprevreq=(Map<String,LabTestVO>)session.getAttribute(InvestigationConfig.LIST_PREV_REQ_XRAY);
    
    
    if(_mppprevreq!=null && _mppprevreq.size()>0)
	{
 	%>
 	 <script>
 	
    </script>
    
 	<div
					style="overflow-x:auto; background-color: ;margin-top:-20"
					class="anyClassscrollabletestshow">
					<table id="preqvreqtbl" class="table table-condensed  table-xs  table-hover table-responsive " >
			
    <thead>
      <tr>
              <th>Test</th>
      
         <th>Laboratory</th>
         <th> Test Type </th>
         <th>Status</th>
         <th> Priority </th>
                 <th>  Requisition Date  </th>
                         <th>   </th>
        
      </tr>
    </thead> 
    <tbody>
    
   
    
    <%
    	   boolean flg=true;

       Iterator itrBookMark=_mppprevreq.keySet().iterator();
	  while(itrBookMark.hasNext())
	  {
    		  
    		//  String test=testlab.split("#")[0];
    		String keyyreqno=(String )itrBookMark.next();
    		LabTestVO voPatEpisodeprev=_mppprevreq.get(keyyreqno);
 			
    		
 	
    %>
    
    
      
       
       <%
       String prvReqStatus=voPatEpisodeprev.getPrvReqStatus();
      String testgrpcode= (voPatEpisodeprev.getTestGroupCode()==null?"0":voPatEpisodeprev.getTestGroupCode());
    	 String color="";
  		 if(prvReqStatus.equals("2")||prvReqStatus.equals("5"))
  		 color="skyblue";
  		 if(prvReqStatus.equals("3"))
  			 color="silver";
  		 if(prvReqStatus.equals("4"))
  			 color="#CC99FF";
  		 if(prvReqStatus.equals("6"))
  			 color="#ffe6e6";
  		 if(prvReqStatus.equals("7"))
  			 color="aqua";
  		 if(prvReqStatus.equals("8"))
  			 color="purple";
  		 if(prvReqStatus.equals("9"))
  			 color="fuchsia";
  		 if(prvReqStatus.equals("10")||prvReqStatus.equals("11"))
  			 color="blue";
  		 if(prvReqStatus.equals("12"))
  			 color="olive";
  		 if(prvReqStatus.equals("13"))
  			 color="lime";
  		 if(prvReqStatus.equals("14"))
  			 color="gold";
  		 if(prvReqStatus.equals("15"))
  			 color="teal";
  		 if(prvReqStatus.equals("16"))
  			 color="#EB7273";
  		 if(prvReqStatus.equals("26"))
  			 color="brown";
  		 if(prvReqStatus.equals("17"))
  			 color="#FFA500";
  		 if(prvReqStatus.equals("18"))
  			 color="#FFA599";
  		 if(prvReqStatus.equals("55"))
  			 color="#9999FF";
  	
  		 String color1="";
  		 //color1="background-color:grey;color:"+color;
		 
       %>
        
      <tr style="<%=color1%>">
   
        <td width=20%> 
        
          <% if(voPatEpisodeprev.getTestGroupCode()==null || voPatEpisodeprev.getTestGroupCode().equals("0") || voPatEpisodeprev.getTestGroupCode().equals("")) {%>
         &nbsp;&nbsp;<%= voPatEpisodeprev.getTestName()%></td>
       <%}else{ %>
      <%= voPatEpisodeprev.getTestName().trim()%>
       <%} %>
       
       </td>
       
       <td ><%=voPatEpisodeprev.getLabName() %></td>
             <%--  <td><%="Patient Based" %></td> --%>
              <td ><%=voPatEpisodeprev.getSampleCode() %></td>

              <td><%=voPatEpisodeprev.getStatus() %></td>
              <td><%=voPatEpisodeprev.getPriority() %></td>
              <td><%=voPatEpisodeprev.getReqDate() %></td>
            <!--   <td></td> -->
   <%        if(prvReqStatus.equals("2")||prvReqStatus.equals("5"))
	{ 
	%>  
	     <td><a class='' title='Delete' data-toggle='tooltip' onClick="deleteRowPrvReqDtl('<%=voPatEpisodeprev.getPrvLabCode()%>','<%=voPatEpisodeprev.getPrvTestCode()%>','<%=voPatEpisodeprev.getReqNo()%>','<%=testgrpcode%>','<%=voPatEpisodeprev.getTestName()%>','<%=voPatEpisodeprev.getTotalviewcount()%>','<%=voPatEpisodeprev.getTotalreqviewcount()%>','<%=voPatEpisodeprev.getTotalreqviewtyp()%>','<%=voPatEpisodeprev.getPatCrNo()%>')"><i class="fa fa-trash" aria-hidden="true"></i></a></td>
	     
         <%}else{ %>
          <td></td> 
         <%} %>
	<!-- td9.innerHTML="<div align='center' ><img src='/HISInvestigationG5/hisglobal/images/minus.gif' id='minusButton' onClick='deleteRowPrvReqDtl("+(parseInt(nRow)-1)+","+(labCode)+","+(testCode)+","+"\""+(reqNo)+"\""+","+"\""+(testGroupCode)+"\""+")'></div>";
	
	} -->
	
              
       
      </tr>
			
  
  <%    flg=false;
 		}%>
 		
 		 </tbody>
  </table>
  </div>
 		<%
 	}
    



  %>
  




				<!-- </div>

				Modal Footer
				<div class="modal-footer">
					
					 <button type="button" class="btn btn-primary" onclick="closeprevreq()">Close
					</button> 
				</div>
			</div>
		</div>
	</div> 
	 -->
   <html type="hidden" name="hmode" />
      
      
   		<html:hidden name="InvestigationRaisingDtlXrayFB" property="prevreqfromwhichcall" />
      		<html:hidden name="InvestigationRaisingDtlXrayFB" property="patCrNo" />
   
	<input type="hidden" name="searchTestName" />
	
</html:form>

</body>


</html>
