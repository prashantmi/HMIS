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
<%@page import="new_investigation.vo.Inv_RequisitionRaisingPatientVO"%>
<%@page import="new_investigation.vo.Inv_ictc_VO"%>


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
<%@page import="new_investigation.transactions.controller.fb.InvpidFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="new_investigation.InvestigationpidConfig"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
 <%@page import="java.io.File"%>
<%@page import="java.io.*"%>


<!DOCTYPE html>

<html lang="en">

<head>

  <title>PID Process</title>

  <meta charset="utf-8">

  <meta name="viewport" content="width=device-width, initial-scale=1">


<his:javascript src="/new_investigation/reportprinting/invpid.js" />



<his:css src="/new_investigation/pid/4.3.11bootstrap.min.css" />



<his:javascript src="/new_investigation/reportprinting/3.4.jquery.min.js" />

<his:javascript src="/new_investigation/reportprinting/popper.min.js" />

<his:css src="/new_investigation/pid/animate.min.css" />

  
    
 <!--   <link href='https://fonts.googleapis.com/css?family=Open Sans' rel='stylesheet'>
  -->
<!--imp tools-->

	<!-- <link rel="stylesheet" type="text/css" href="drDeskAssets/css/style.css">
	<script src="new_opd/js/opdDrDeskSave.js"></script>
	<link rel="stylesheet" href="drDeskAssets/css/perfect-scrollbar.css">
	<script src="drDeskAssets/perfectScrollbar/perfect-scrollbar.js"></script>
	UIkit CSS
	<link rel="stylesheet" href="drDeskAssets/uikit/css/uikit.min.css" />
	UIkit JS
	<script src="drDeskAssets/uikit/js/uikit.min.js"></script>
	<script src="drDeskAssets/uikit/js/uikit-icons.min.js"></script>
	<script src="drDeskAssets/tippy/tippy.all.min.js"></script> -->


<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/drDeskAssets/css/style.css">
	<script src="/HISDRDESK/new_opd/js/opdDrDeskSave.js"></script>
	<link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/css/perfect-scrollbar.css">
	<script src="/HIS/hisglobal/drDeskAssets/perfectScrollbar/perfect-scrollbar.js"></script>
	<!-- UIkit CSS -->
	<link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/uikit/css/uikit.min.css" />
	<!-- UIkit JS -->
	<script src="/HIS/hisglobal/drDeskAssets/uikit/js/uikit.min.js"></script>
	<script src="/HIS/hisglobal/drDeskAssets/uikit/js/uikit-icons.min.js"></script>
	<script src="/HIS/hisglobal/drDeskAssets/tippy/tippy.all.min.js"></script>
	
<!-- imp tools-->
	

<his:javascript src="/new_investigation/pid/4.3.1bootstrap.min.js" />

<his:javascript src="/new_investigation/pid/jquery-3.3.1.js" />

 <!-- <link rel="stylesheet" type="text/css" href="drDeskAssets/DataTables-1.10.18/css/dataTables.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="drDeskAssets/Responsive-2.2.2/css/responsive.bootstrap4.min.css"/>
	<script type="text/javascript" src="drDeskAssets/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="drDeskAssets/DataTables-1.10.18/js/dataTables.bootstrap4.min.js"></script>
	<script type="text/javascript" src="drDeskAssets/Responsive-2.2.2/js/dataTables.responsive.min.js"></script>
	<script type="text/javascript" src="drDeskAssets/Responsive-2.2.2/js/responsive.bootstrap4.min.js"></script>
 -->
 <!--  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  -->
   <link rel="stylesheet" type="text/css" href="/HIS/hisglobal/drDeskAssets/DataTables-1.10.18/css/dataTables.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="/HIS/hisglobal/drDeskAssets/Responsive-2.2.2/css/responsive.bootstrap4.min.css"/>
	<script type="text/javascript" src="/HIS/hisglobal/drDeskAssets/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="/HIS/hisglobal/drDeskAssets/DataTables-1.10.18/js/dataTables.bootstrap4.min.js"></script>
	<script type="text/javascript" src="/HIS/hisglobal/drDeskAssets/Responsive-2.2.2/js/dataTables.responsive.min.js"></script>
	<script type="text/javascript" src="/HIS/hisglobal/drDeskAssets/Responsive-2.2.2/js/responsive.bootstrap4.min.js"></script>
	

<!--
  <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>

  <script src="https://cdn.datatables.net/rowreorder/1.2.5/js/dataTables.rowReorder.min.js"></script>
  
      <script src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
	  
  <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">

  <link rel="stylesheet" href="https://cdn.datatables.net/rowreorder/1.2.5/css/rowReorder.dataTables.min.css">

  <link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.dataTables.min.css">
-->
  

<his:css src="/new_investigation/pid/all.css" />


	

<his:css src="/new_investigation/pid/cdn3.3.4bootstrap.min.css" />


<link rel="stylesheet" type="text/css" href="/new_investigation/pid/cdn3.3.4bootstrap.min.css" />

<his:javascript src="/new_investigation/pid/cdn3.3.4bootstrap.min.js" />

<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/drDeskAssets/jstree/themes/default/style.min.css" />


<his:javascript src="/new_investigation/pid/jquery-1.11.1.min.js" />
<his:javascript src="/new_investigation/pid/ui1.11.1jquery-ui.min.js" />

	
<his:css src="/new_investigation/pid/smothjquery-ui.css" />
	
	<his:css src="/new_investigation/pid/5.9.0fontawesome.css" />
	
	
	<script type="text/javascript"
	src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js"
	djConfig="parseOnLoad: true"> </script>

<script>
// Initialize tooltip component
$(function () {
 // $('[data-toggle="tooltip"]').tooltip()
})



</script>




<style>

 
 .dot {
  height: 10px;
  width: 10px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
}

.popover{
   max-width: 120%;
   background: black;
    
}



.popover.bottom .arrow:after {
    border-bottom-color: black;
}


.searchtile {
  box-shadow: 0 4px 8px 0 gba(210,255,82,1);
  transition: 2.0s;

}

.searchtile:hover {
  box-shadow: 0 8px 16px 0 gba(210,255,82,1);

  -ms-transform: scale(1.5); /* IE 9 */
  -webkit-transform: scale(2.0); /* Safari 3-8 */
  transform: scale(1.01);
  }
  
  
#myBtn {
  display: none;
  position: fixed;
  bottom: 20px;
  right: 30px;
  z-index: 99;
  font-size: 18px;
  border: none;
  outline: none;
  background-color: red;
  color: white;
  cursor: pointer;
  padding: 15px;
  border-radius: 4px;
}

#myBtn:hover {
  background-color: #555;
}



</style>
  
  <script>

  window.onscroll = function() {scrollFunction()};

  
  
  
  function isNumberKey(evt){
	    var charCode = (evt.which) ? evt.which : event.keyCode
	    if (charCode > 31 && (charCode < 48 || charCode > 57))
	        return false;
	    return true;
	}  
  
  
  function closemodal()
  {
		document.getElementsByName('hmode')[0].value="NEWCALL";
		document.forms[0].submit();

  }
  
  
  function calldata()
  {
	  alert("hi");
  }
  
  
function onloadd()
{
	
	
    
	var savesmg="0";
	
	savesmg='<%=(String)session.getAttribute("issave_pidform")==null?"":session.getAttribute("issave_pidform")%>';

    if(savesmg=="1")
	{
	
    	document.getElementById("hidemsg").style.display = "";

    	setTimeout(function() {
    	    $('#hidemsg').fadeOut('fast');
    	}, 5000); // 
      
	}
    
    

    
    
	var shwe="0";
	
	shwe='<%=(String)session.getAttribute("shw_pattile_piform")%>';

    if(shwe=="1")
	{
	
    	document.getElementById("pattile").style.display = "";

	
	}
    
	
	var ff="0";
	
	      ff='<%=(String)session.getAttribute("iscall_pidfrom")%>';
	
//	alert("ff"+ff);
	
	if(ff=="1")
		{
		
		var theDialog = $("#dialog").dialog(opt);
		   
		theDialog.dialog("open");

		
		}
	
}


  function isdeleteall(obj,gender)
  {
  	
  	 /*var d = new Date();
  	    var n = d.getFullYear();
  	
  		 var ghhh="GC/SA/ICTC/BH/PTN/016/"+n+"/";

  		document.getElementById('pidenter').value = val;
  	 
  		
  	var vall=obj.value;
  	var idd=obj.id;
     //  alert(obj.value);	
       var val=obj.value;
       
       if(val.length<27)
      	 {
      //	 alert("le"+val.length);
      	 obj.value=vall;
      		document.getElementById('pidenter').value = vall;
      		//return true;
      		if(val.length<27)
      			{
          		document.getElementById('pidenter').value = ghhh;
          		return true;
      	//	return false;
      			}
      		else if(val.length==27)
      			return true;
      	 }
       else 
      	 return true;
       */
       return true;
       
  }
  
  function setpidd(crno,reqno,pidno)
  {
	  

	    
		document.getElementsByName('patcrno_pid')[0].value=crno;
		document.getElementsByName('reqno_pid')[0].value=reqno;
		if(pidno=="0")
			{
			document.getElementsByName('pid')[0].value="";
			}
		else
			{
			document.getElementsByName('pid')[0].value=pidno;		
			}
	

		document.getElementsByName('hmode')[0].value="setpidd";
		document.forms[0].submit();


	  
	  
  }
  
  
  function ipdesxistonfocus(obj)
  {
	 // alert(obj.value);
        var remarks=""
	  var dataa=obj.value;
		//alert(dataa.length);
		var len=dataa.length;
		var spli=obj.value;
		spli=spli.substring(0,22);
		
		if(len!=32)
			{
			//alert("Please Enter Valid PID");
			
			}
		else
			{
	//alert("isduplicatetestrasiedgroupwise"+"labcode"+labcode+"testCode"+testcode+"grpcode"+grpcode);
	 var patcrno=document.getElementsByName('patCrNo')[0].value

	var _mode = "ispidesixt";
	
		 
		 objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&patCrNo="+patcrno+"&ispidexist="+dataa, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert("data"+data);
			remarks = data;
			flg = true;
		},
        error: function(error)
        {
            //remarks = tmpLabTestCodeArray;
            flg = false;
        }};

		 
	var objDojoAjax = dojo.xhrPost(objXHR);
	
	if(remarks=="1")
	{
		document.getElementById("pidenter").value=spli;		
	 alert("PID already Exist.Please Enter Different PID");	
           
	}
			}
	
	return remarks;
	
  }

  
  

  $(document).ready(function() {
	//  alert("hi");
		$("#pidenter").on("keyup", function() {
	  	var value = $(this).val();
	    //alert(value);
	  	var iswome="0";
	  	if(document.getElementById('iswomenpregnantpid')!=null)
	  		{
	  		 iswome=document.getElementById('iswomenpregnantpid').value;
	  		}
	  	
	  	if(iswome=="0")
	  	$(this).val("GC/SA/ICTC/BH/PTN/016/" +  value.substring(22));
	  	else
	  		$(this).val("PW/SA/ICTC/BH/PTN/016/" +  value.substring(22));
	  	
	  	
	  });
	});
  
  
  function cancelFunc()
  {
  	window.parent.closeTab();
  }
  
  
  var opt = {
	        autoOpen: true,
	        modal: true,
	        width: 900,
	        height:400,
	        title: 'PID Form',
	        close: function() {
	           
	           closemodal();
	        	
	          }
	};
  
  function fff(crno,reqno,pidno)
  {
	//  alert("dd");
	setpidd(crno,reqno,pidno);
	

	 // $("#dialog").dialog("open");  
  }
  
  

  function followupfn(obj){
  if(obj.value=="y"){
  	document.getElementById('followupcheckn').checked=false;
  	document.getElementById('yesno0').disabled=true;
  	document.getElementById('yesno1').disabled=true;
  }
  else {
  	document.getElementById('followupchecky').checked=false;
  	document.getElementById('yesno0').disabled=false;
  	document.getElementById('yesno1').disabled=false;
  }
  //alert("Followup value"+document.getElementsByName('followup')[0].value);
  }
  

  function setpidtxt(thiss)
  {
  	
  	 var d = new Date();
  	    var n = d.getFullYear();
  	//alert("thiss.value"+thiss.value);
  	var val="GC/SA/ICTC/BH/PTN/016"+"/"+n+"/";
  	 if(thiss.value=="1")
  		 {
  		 val="PW/SA/ICTC/BH/PTN/016"+"/"+n+"/";
  		 
  		 }
  	 else
  		 val="GC/SA/ICTC/BH/PTN/016"+"/"+n+"/";
   
  		document.getElementById('pidenter').value = val;
  	 
  }

  
  function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
      document.getElementById("myBtn").style.display = "block";
    } else {
      document.getElementById("myBtn").style.display = "none";
    }
  }

  function topFunction() {
	  document.body.scrollTop = 0;
	  document.documentElement.scrollTop = 0;
	}
	
  </script>
  
  
   <style> 
   
   .ui-dialog > .ui-widget-header {background: #7C9CDE ;}
   
  .hoverr:hover{
  	background-color: yellow !important;
  }
</style> 

</head>


<body onload="onloadd()">

<html:form action="/pidform" >

<html:hidden name="InvpidFB" property="hmode" />
<html:hidden name="InvpidFB" property="dateType" />
<html:hidden name="InvpidFB" property="patcrno_pid" />
<html:hidden name="InvpidFB" property="reqno_pid" />
<html:hidden name="InvpidFB" property="pid" />
<html:hidden name="InvpidFB" property="pidd" />
<html:hidden name="InvpidFB" property="followup" />


<div class="card shadow p-3 mb-5 bg-white rounded" style="margin:10px 10px 10px 10px">
  <div class="card-body" style="padding-top:0px">
    
 <div class="col-sm-12 " style="; padding-bottom: 0px; padding-top: 15px; padding-right: 0px;padding-left: 5px;margin-bottom: -5px;" >
      
<div class="row">
          	<div class="col-sm-2" style="padding-right: 0px;padding-left: 0px;">
          		<h2 style="margin:0px 15px;font-size: 1.6rem;display:inline-block"> <a style="text-decoration:none;color: rgba(23, 97, 194, 0.74);font-weight: bold;font-size: 20px;" href="javascript:;"> PID Form</a></h2>

            </div>

            <div class="col-xs-12 col-sm-1">
          		<span><img width="23" src='/HISInvestigationG5/new_investigation/pid/expand.svg'></span>


          	</div> 
<div class="clearfix visible-xs" style="margin: 10px;"></div>
          	<div class="col-xs-12 col-sm-5 form-horizontal">
          		<div class="form-group" style="margin-bottom:0px;">
          		

 <div class="container-fluid col-xs-6 col-sm-3" style="font-size: 1.6rem">

  <select class="custom-select" style="font-size: 1.6rem" >
  <option selected>Cr NO.</option>
  <!-- <option value="1">Billing NO</option> -->
  
</select>
</div>

<div class="col-xs-6 col-sm-7" style="">
                  <input type="text" class="form-control" id="usr" name="patCrNo" maxlength="15" onkeypress="return isNumberKey(event)"  >


<!-- <p class="text-center" style="font-size:0.95em;margin-top: 2px;margin-bottom: 0px;float: right;font-weight:bold; margin-right:-5px"><font style="color: rgba(35, 104, 194, 0.8)"></font> <a href="#">Advanced Search</a></p> -->
                </div>

</div>


</div>

<div class="col-xs-6 col-sm-3 " style="margin-bottom: 0px">
          		
               <button  width="100%" type="button" class="btn btn-info block button col-xs-6 col-sm-3" onclick="showall()"><span>Go</span></button>
           
          		
              <button  width="100%" type="button" style="float:right" class="btn btn-info block button col-xs-6 col-sm-3" onclick="cancelFunc()"><span>Cancel</span></button>


                </div>





</div>





</div>


</div>


  </div>


</div>


<div class="card searchtile shadow p-3 mb-5  " style="display:none;background-color:light green;margin:-19px 10px 10px 10px;padding-bottom:0px;" id="pattile"  ">
  <div class="card-body" style="padding-top:0px" >


<div class="col-sm-5" style="padding-right: 0px;padding-left: 0px;margin-left:-20px;" >
          	
<p id="header_pid" class="text-center" style="margin:0px 15px;font-size:1.6em;font-weight:bold;display:inline-block"><font style="color: grey"></font>
          		<span><img width="20" src='/HISInvestigationG5/new_investigation/pid/user.svg'></span>

<a>Patient Details:</a></p>

            </div>


 <div class="col-sm-12 uk-card uk-card-default leftPanel"  style="height:; padding-top: 2px">  


<table id="example1" class="table table-hover table-condensed table-checkable patientListMainTable" style="width:100%;margin-bottom:0px">
					  <thead>
					    <tr style="display:none">
					<th></th>
					
<th></th>
<th></th>
<th></th>
<th></th>
<th></th>
<th></th>
<th></th>
<th></th>
					     </tr>
					    </thead>
					     <tbody>
					     
					     
<%

List<InvResultReportPrintingVO> lstPatAppotmentVO=(List<InvResultReportPrintingVO>)session.getAttribute(InvestigationpidConfig.LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO_PIDDD);
int i,size=0;

if(lstPatAppotmentVO!=null && lstPatAppotmentVO.size()>0 )
{
//	System.out.println(lstPatAppotmentVO.size());
	size=lstPatAppotmentVO.size();

	InvResultReportPrintingVO vo=lstPatAppotmentVO.get(0);
	  
	for(int k=0;k<lstPatAppotmentVO.size();k++)
	{
		InvResultReportPrintingVO vo1=lstPatAppotmentVO.get(k);
  
		if(vo1.getPidno()!=null && !vo1.getPidno().equals(""))
		{
			vo=lstPatAppotmentVO.get(k);
		}
	}
	

	
	
	if(vo!=null)
	{
%>



   <tr>
			 
			    
                 <td>
<button type="button" class="btn btn-outline-info opdVitalBtn" style="background-color:#7C9CDE !important; border:0px;" onclick="openModalForVital(this,event);"  ><span style="color:white"><b>CR NO: <%=vo.getPatCRNo() %></b></span></button>

 
                <td><span style="font-weight:bold">Patient Name: </span><%=vo.getPatName() %>
</td>
               
				<td><span style="font-weight:bold">Age/Gender: </span><%=vo.getPatAge()+"/"+vo.getPatGender() %>
</td>
				<td><span style="font-weight:bold">Mobile No: </span><%=vo.getMobileNo() %>
</td>
				<td >
  <button type="button" class="btn btn-outline-info opdVitalBtn" style="background-color:#7C9CDE !important; border:0px;" onclick="openModalForVital(this,event);"  ><span style="color:white"><b>Patient Status:<%=vo.getPatStatus() %></b></span></button>
</td>

<% if(vo.getPatStatus().equals("IPD"))
{
%>
				<td><span style="font-weight:bold">Ward: </span><%=vo.getWardName()%>
</td>
<%} %>				
            </tr>

<% if(vo.getPidno()!=null && !vo.getPidno().equals("")) {%>
<tr>
                <td>
                  <button type="button" class="btn btn-outline-info opdVitalBtn" style="background-color:#7C9CDE !important; border:0px;" onclick="openModalForVital(this,event);"  ><span style="color:white"><b>PID No:<%=vo.getPidno() %></b></span></button>
                
<%-- <span style="font-weight:bold">PID No: </span><%=vo.getPidno()%>
 --%>
</td>
<td>
</td>
<td>
</td>
<td>
</td>
<td>
</td>
<td>
</td>
</tr>
 <%}
	}
}
else
{
%>                      
<tr><div align="center"><b><span style="color:blue">No Records Found related PID</span></b></div>

<script>
document.getElementById("header_pid").style.display = "none";

</script>

</tr>

<%} %>
                                        

       	                                
					                   </tbody>
					                  </table>  


	<!-- <div class="col-xs-2 col-sm-2">
<span style="font-weight:bold">CR NO:</span>9610145236985623
</div>
<div class=" col-xs-2 col-sm-2">
<span style="font-weight:bold">Patient Name: </span>Tiger Nixon
</div><div class="col-xs-2 col-sm-2">
<span style="font-weight:bold">Age/Gender: </span>M/26
</div><div class="col-xs-2 col-sm-2">
<span style="font-weight:bold">Mobile No: </span>8439697443
</div><div class="col-xs-2 col-sm-2">
<span style="font-weight:bold">Patient Status: </span>IPD
</div><div class="col-xs-2 col-sm-2">
<span style="font-weight:bold">Ward /Bed No: </span>5694
</div> -->


</div>

</div>
</div>






<div class="card searchtile shadow p-3 mb-5 bg-white rounded" style="margin:-19px 10px 1px 10px;display:" id="crnodatattile">
  <div class="card-body" style="padding-top:0px" >
	<div class="col-xs-12 col-sm-12" >
<div align="right"><b><u>Legends</u><b>:&nbsp;
<span class="dot" style="background-color:#008b1c "></span>&nbsp;PID Mapped &nbsp;&nbsp;
<span class="dot" style="background-color:black"></span>&nbsp;PID Not Mapped
</div>   
 <div class="col-sm-12 uk-card uk-card-default leftPanel"  style="padding-top: 5px">  
					<table id="example" class="table table-hover table-condensed table-checkable patientListMainTable" style="width:100%">
					  <thead>
					    <tr>
					 	<th></th>
			   
                
                <th>Test Name</th>
                  <th>Department-Unit</th>
              <th>Lab Name</th>
                 <th>Status</th>
              <th>Priority</th>
                <th>Sample No./ Lab No.</th>
               <th>Requisition Date</th>			  
            <th>PID Form</th>	
			 
					     </tr>
					    </thead>
					     <tbody>
					     
					     
					     
<%
String genderrr="";
List<InvResultReportPrintingVO> lstPatAppotmentVOO=(List<InvResultReportPrintingVO>)session.getAttribute(InvestigationpidConfig.LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO_PIDDD);


if(lstPatAppotmentVOO!=null && lstPatAppotmentVOO.size()>0 )
{
//	System.out.println(lstPatAppotmentVO.size());
	


	for(int k=0;k<lstPatAppotmentVOO.size();k++)
	{
		InvResultReportPrintingVO voo=lstPatAppotmentVOO.get(k);
		genderrr=voo.getPatGender();

		
		String color="black";
		String ispid="0";
		if(voo.getPidno()!=null && !voo.getPidno().equals(""))
		{
			color="#008b1c" ;
			ispid="1";
		}
%>
					     
   <tr style="color:<%=color%>" class="hoverr">
			 <td></td>
			    
            
                <td><%=voo.getTestName() %></td>
               <!-- <td class="patStatus"><a style="cursor: default;font-weight:bold;color:white" class="label label-success">Report Generated</a></td> -->
				<td style="width:20% !important"><%=voo.getPatUnitName() %></td>
				<td><%=voo.getPatLabName() %></td>
				<td><%=voo.getReqDtlStatus() %></td>
				<td><%=voo.getPriority() %></td>
				<td><%=voo.getTempSampleNo()==null?"-":voo.getTempSampleNo() %></td>
				<td><%=voo.getRequisitionDate() %></td>
				<% if(ispid.equals("1"))
					{%>
				<td>
				<a id='myButton' style='box-shadow: rgba(0, 0, 0, 0.24) 0px 6px 10px 0px, rgba(0, 0, 0, 0.19) 0px 17px 50px 0px; background-color: rgb(85, 107, 47); border: medium none; color: white; text-align: center; text-decoration: none; font-size: 14px; margin: 4px 2px; cursor: pointer; transition-duration: 0.4s; height: 30px;' href="javascript:fff('<%=voo.getPatCRNo()%>','<%=voo.getRequisitionNo() %>','<%=voo.getPidno() %>')">&nbsp;PID Form&nbsp; </a>
				
				</td>
				<%}else{ %>
				<td>
				<a id='myButton' style='box-shadow: rgba(0, 0, 0, 0.24) 0px 6px 10px 0px, rgba(0, 0, 0, 0.19) 0px 17px 50px 0px; background-color: black; border: medium none; color: white; text-align: center; text-decoration: none; font-size: 14px; margin: 4px 2px; cursor: pointer; transition-duration: 0.4s; height: 30px;' href="javascript:fff('<%=voo.getPatCRNo()%>','<%=voo.getRequisitionNo() %>','0')"> &nbsp;PID Form&nbsp;</a>
				
				</td>
				
				<%} %>
            </tr>
                                        
<%}%>
	
	
	<%} 
else
{
	%>
   
   <script>
document.getElementById("crnodatattile").style.display = "none";

</script>                                     
<%} %>
       	                                
					                   </tbody>
					                  </table>  
					                 
					                 <div id="hidemsg" style="display: none" ><span style="color:red">Data Save successfully</span></div>
					                 
								    </div>  


</div>
</div>

<!-- 
pid from -->



    <div id="dialog" title="PID FORM" align="left" style="font-size: large;width:auto;display:none ">

                <%
                
                String pidoldreqset="";
                String gendertypforpid="";
               // pidoldreqset=  "961012000001038#F#0#GC/SA/ICTC/BH/PTN/016/2020/56565#0#";
                //pidoldreqset=  "null#null#null#null#null#";
                
                if(request.getAttribute("getpidata_piform")==null)
                {
                	pidoldreqset=  "null#null#null#null#null#";
                    
                	
                }
                else
                {
                	pidoldreqset=  (String)request.getAttribute("getpidata_piform");
                }
               
	 			if(genderrr.equalsIgnoreCase("m"))
	 			gendertypforpid="m";

	 			if(genderrr.equalsIgnoreCase("f"))
		 			gendertypforpid="f";

	 			
                String ispreg="";
                String piddddddd="";
                String initiatestypeeee="";
                if(pidoldreqset.length()>=3 && !pidoldreqset.split("#")[3].equals("null"))
                {
                     ispreg=pidoldreqset.split("#")[2];
                     piddddddd=pidoldreqset.split("#")[3];
                     initiatestypeeee=pidoldreqset.split("#")[4];

                }

                %>

                <% if(gendertypforpid.equalsIgnoreCase("f") && pidoldreqset.length()>=3  && pidoldreqset.split("#")[3].equals("null"))
                { %>
    <div style="width: 100%;  margin-left: -150;font-size: large;">
 Is Women Pregnant:<select id="iswomenpregnantpid" style="width: 480px;" onchange="setpidtxt(this)" >
  <option value="0" >No</option>
    <option value="1">Yes</option>

</select>
 </div>
 <%} else if(gendertypforpid.equalsIgnoreCase("f") && pidoldreqset.length()>=3 && (!pidoldreqset.split("#")[3].equals("null")))
 {

	 if(ispreg.equals("0"))
	 {%>

		<div style="width: 100%;  margin-left: -150;font-size: large;">
 Is Women Pregnant:<select id="iswomenpregnantpid" style="width: 480px;"  >
  <option value="0" selected="selected">No</option>
    <option value="1">Yes</option>

</select>
 </div>
	 <% }else
	 {%>
			<div style="width: 100%;  margin-left: -150;font-size: large;">
 Is Women Pregnant:<select id="iswomenpregnantpid" style="width: 480px;"   >
  <option value="1" selected="selected">Yes</option>
    <option value="0">No</option>

</select>
 </div>


	 <% }

 }
 %>
   <br>

 <%
 Date date = new Date();
 Calendar calendar = new GregorianCalendar();
 calendar.setTime(date);
 int year = calendar.get(Calendar.YEAR);
 String piddddate="";
 String piddddateoldval="";
 if(pidoldreqset.split("#")[3].equals("null"))
 { piddddate="GC/SA/ICTC/BH/PTN/016"+"/"+year+"/"; }
 else { piddddateoldval=piddddddd; }

 if(pidoldreqset.split("#")[3].equals("null")) { %>
 PID &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
 <input type="text" name="pidenter" maxlength="32" id="pidenter" value="<%=piddddate%>" data-initial="<%=piddddate%>" onkeypress="return isdeleteall(this,'<%=gendertypforpid%>')" size="40" onblur="ipdesxistonfocus(this)" >
 
 <% } else { %>
 PID &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
	 <input type="text" name="pidenter" maxlength="32" id="pidenter" value="<%=piddddateoldval%>" data-initial="<%=piddddate%>"  disabled onkeypress="return isdeleteall(this,'<%=gendertypforpid%>')" size="40" onblur="ipdesxistonfocus(this)" >
  <% } %>
 <br><br>

<% if(pidoldreqset.split("#")[3].equals("null")) { %>

    Initiated Type&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
    <input type="radio" name="yes_no"  value="0" id="yesno0"> Client Initiated&nbsp;&nbsp;
    <input type="radio" name="yes_no" value="1" id="yesno1"> Provider Initiated

<% } else if(initiatestypeeee.equals("0")) { %>
  	
  	Initiated Type &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
	<input type="radio" name="yes_no"  value="0" id="yesno0" checked disabled> Client Initiated&nbsp;&nbsp;
	<input type="radio" name="yes_no" value="1" id="yesno1" disabled> Provider Initiated
    <br><br>
	<div id="followupdiv" title="To make changes in initiated type change FollowUp To No " style="font-size: large;" class="" >FollowUp &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
	<input type="radio"  name="followupcheck" id="followupchecky" onclick="followupfn(this)" value="y" checked> Yes&nbsp;&nbsp;
    <input type="radio"  name="followupcheck" id="followupcheckn" onclick="followupfn(this)" value="n" checked> No
    
    <!-- <span class="tooltiptext">To make changes in initiated type change FollowUp To "No" </span> -->
	</div>
	
<% } else if (initiatestypeeee.equals("1")) { %>
    
    Initiated Type &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
	<input type="radio" name="yes_no"  value="0" id="yesno0" disabled> Client Initiated&nbsp;&nbsp;
	<input type="radio" name="yes_no" value="1" id="yesno1" checked disabled> Provider Initiated
    <br><br> 
    <div id="followupdiv" title="To make changes in initiated type change FollowUp To No" style="font-size: large;" class="" >FollowUp &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
	<input type="radio"  name="followupcheck" id="followupchecky" onclick="followupfn(this)" value="y" checked> Yes&nbsp;&nbsp;
    <input type="radio"  name="followupcheck" id="followupcheckn" onclick="followupfn(this)" value="n"> No
    
   <!--  <span class="tooltiptext">To make changes in initiated type change FollowUp To "No" </span> -->
	</div>

<% } %>	
	


<br>
<div align="center">
<img  class="button"
							src='<his:path src="/hisglobal/images/btn-sv.png"/>'
							id="" style="cursor: pointer;height:28" tabindex="1"
							onclick="savepidd()">
</div>
</div>


<!-- <div class="modal fade" id="printPrescriptionMainDeskModal" role="dialog">
    <div class="modal-dialog modal-lg">       Modal content
      <div class="modal-content" >
        <div class="modal-header" style="height:5px">
          <button type="button" class="close" data-dismiss="modal">&times;</button> 
        </div>
        <div class="modal-body">
       		 <div class="text-center">
       			<button type="button" class="btn btn-info" onclick="$('#printPrescriptionMainDeskModal').modal('hide');$('#printPrescFrameId').remove();">Cancel</button>
			 </div> 
        </div>  
      </div> 
    </div>
  </div> 
  -->
  	
</html:form>
</body>


</html>

​

