<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<%@page import="new_investigation.transactions.controller.fb.OnlinePatientAcceptanceFB"%>
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
<%@page import="hisglobal.config.HISConfig"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="new_investigation.transactions.controller.fb.OnlinePatientAcceptanceFB"%>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<!-- Added by Prashant -->
<script type="text/javascript" src="media/jquery/3.4.1/jquery-3.4.1.min.js" ></script>
<script src="media/jquery-ui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<link href="media/jquery-ui/1.12.1/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<script src="media/jquery-ui/1.12.1/jquery.ui.datepicker.validation.min.js" type="text/javascript"></script>
<script src="media/jquery-ui/1.12.1/jquery.validate.min.js" type="text/javascript"></script>

<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />

<his:css src="/hisglobal/css/email.css" />
<his:css src="/hisglobal/css/textboxcss.css" />
<his:css src="/hisglobal/css/drop.css" />
<!-- <link rel="stylesheet" href="/new_investigation/css/Date/site-demos.css">
 --><his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/css-pop-inv.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<%-- <his:javascript src="/bloodbank/js/bloodRequisition.js" />
 --%><his:javascript src="/new_investigation/js/reportsValidation.js" />
<his:javascript src="/new_investigation/js/onlinePatientAcceptance.js" />
<%-- <his:javascript src="/hisglobal/js/jquery-1.11.1.min.js" />
 --%><his:javascript src="/hisglobal/js/additional-methods.min.js" />
<his:javascript src="/new_investigation/js/jquery.validate.email.js" />
<his:javascript src="/new_investigation/js/additional-methods.min.js" />

<script src="media/misc/datepicker1.js" type="text/javascript"></script>
<script type="text/javascript"
	src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js"
	djConfig="parseOnLoad: true"> </script>
	
<!-- <script src="scripts/js/jquery-3.3.1.js" type="text/javascript"></script>
<script src="scripts/js/jquery.dataTables.min.js" type="text/javascript"></script>
<link href="media/dataTables/jquery.dataTables.min.css" rel="stylesheet" type="text/css" /> -->

<!-- Added by Prashant -->
<script src="media/dataTables/DataTables-1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>
<link href="media/dataTables/DataTables-1.10.18/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />

<script src="media/dataTables/Responsive-2.2.2/js/dataTables.responsive.min.js" type="text/javascript"></script>
<script src="media/dataTables/Buttons-1.5.6/js/dataTables.buttons.min.js" type="text/javascript"></script>
<script src="media/dataTables/Buttons-1.5.6/js/buttons.colVis.min.js" type="text/javascript"></script>

<link href="media/dataTables/Responsive-2.2.2/css/responsive.dataTables.min.css" rel="stylesheet" type="text/css" />
<link href="media/dataTables/Buttons-1.5.6/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />

<!-- <link href="media/themes/AdvancedView.css" rel="stylesheet" type="text/css" />
 -->

  
<script>

$(document).ready( function () {

	if(document.getElementsByName("chkSamplePatientOnSave")!=undefined)
	   for(var a=0;a<document.getElementsByName("chkSamplePatientOnSave").length;a++)
	   {
	      	// alert(a);
	      	// alert(document.getElementsByName("chkSamplePatientOnSave")[a].value);   	
	        document.getElementsByName("chkSamplePatientOnSave")[a].checked=true;
	      	onClickSavecheckedgrp(document.getElementsByName("chkSamplePatientOnSave")[a],a,"1"); 	
	      	 //alert((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[6]);             	      	 
	    }

/* Added by PrashantMi */
var table = $('#table2').dataTable({
    
    "iDisplayLength" : 25,  
    /*  dom: 'Bfrtip',
    buttons: ['colvis'], */
    responsive: true,
    "columnDefs": [ 
    	{ "targets": [0], "searchable": false },
    	{ "targets": [0,1], "orderable": false }
    	]
 });
 
/* Added by Prashant */
setTopDatePicker();
//setDatePicker();
 
});

/* Added by Prashant */
function getIndexOfMonth(mon){
	
	mon=mon.toLowerCase();
	var months = ["jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"];
	var index = months.indexOf(mon)+1;
	//alert(index+" index");
	return index
}


/* Added by Prashant */
function setDatePicker(acceptancedateid, getrequisitionDate, k){
	
	var acceptedToNotAccepted = document.getElementsByName("acceptedToNotAccepted");
	if (acceptedToNotAccepted[0].value=="2"){
		//alert("already accapted");
	setDatePickerIfAlreadyAccepted(acceptancedateid, getrequisitionDate, k);
	return;
	}
	
	//alert("new acceptance");
	
	var getrequisitionDate =getrequisitionDate.split('-');

	getrequisitionDate[1]=getIndexOfMonth(getrequisitionDate[1]);
	
	if( $(acceptancedateid).length )         
	{
		$(acceptancedateid).datepicker({
	  		/* dateFormat: "dd-MM-yy" */
	  		dateFormat: "yy-mm-dd",
	  		minDate: new Date(getrequisitionDate[2],getrequisitionDate[1]-1,getrequisitionDate[0]),
	  		maxDate: 0
	    	}).datepicker("setDate", new Date());
	}
}


/* Added by Prashant */
function setDatePickerIfAlreadyAccepted(acceptancedateid, getrequisitionDate, k){
	
	var getrequisitionDate =getrequisitionDate.split('-');
	getrequisitionDate[1]=getIndexOfMonth(getrequisitionDate[1]);
	
	var acceptancedateHiddenId = "#"+k+"acceptancedateHidden";
	var getacceptancedateHidden =  $(acceptancedateHiddenId).val();
	
	if(getacceptancedateHidden=="" || getacceptancedateHidden==null || getacceptancedateHidden=="null")
	{ if( $(acceptancedateid).length )         
	  {
		$(acceptancedateid).datepicker({
	  		/* dateFormat: "dd-MM-yy" */
	  		dateFormat: "yy-mm-dd",
	  		minDate: new Date(getrequisitionDate[2],getrequisitionDate[1]-1,getrequisitionDate[0]),
	  		maxDate: 0
	    	}).datepicker("setDate", new Date());
	  }
	return;
	}
	
 
	var getacceptancedateHidden = getacceptancedateHidden.split('-');
	getacceptancedateHidden[1]=getIndexOfMonth(getacceptancedateHidden[1]);
	
	if( $(acceptancedateid).length )         
	{
		$(acceptancedateid).datepicker({
	  		/* dateFormat: "dd-MM-yy" */
	  		dateFormat: "yy-mm-dd",
	  		minDate: new Date(getrequisitionDate[2],getrequisitionDate[1]-1,getrequisitionDate[0]),
	  		maxDate: 0
	    	}).datepicker("setDate", new Date(getacceptancedateHidden[2],getacceptancedateHidden[1]-1,getacceptancedateHidden[0]));
	}
}

/* Added by Prashant */
function setTopDatePicker(){
	if( $('#-1acceptancedate').length )         
	{
		$('#-1acceptancedate').datepicker({
	  		/* dateFormat: "dd-MM-yy" */
	  		dateFormat: "yy-mm-dd",
	  		/* minDate: , */
	  		maxDate: 0
	    	}).datepicker("setDate", new Date());
	}
}


/* Added by Prashant */
function setAcceptanceDate(k, obj){

	var acceptancedateid= "#"+k+"acceptancedate";
	var requisitionDateId = "#"+k+"requisitionDate";

	var getrequisitionDate = $(requisitionDateId).val();
	
	var getdate="";
	var acceptancedate="";
	var dt = new Date();
	var gettime = " "+dt.getHours() + ":" + dt.getMinutes() + ":" + dt.getSeconds();

  	if( $(acceptancedateid).length )         
  	{
  		getdate = $(acceptancedateid).val();
 
  		
  		 if (getdate=="" || getdate==null){
  			setDatePicker(acceptancedateid, getrequisitionDate, k);
  			getdate = $(acceptancedateid).val();
  		 }
  		
  	  	acceptancedate=getdate+gettime+"#";
  	 	} 
  	return acceptancedate;
}


/* Added by Prashant */
function onChangeAcceptanceDate(obj, k , callingfrom){
	var checkboxid=k+"chkBox";
	var checkboxobj = document.getElementById(checkboxid);
	checkboxobj.onchange();
	//onClickSavecheckedgrp(checkboxobj,k,callingfrom);
}

/* Added by Prashant to change all acceptance date if Top one is changed */
function onChangeTopAcceptanceDate(obj,k, callingfrom){
	
	if(confirm("Test Raised After Dated "+obj.value+" Will Be Unselected")){}
		else{return;}
	
    var getdate=obj.value;
	var acceptancedateC = document.getElementsByClassName('acceptancedateC');
	var requisitionDateC = document.getElementsByClassName('requisitionDateC');
	var chkSamplePatientOnSave = document.getElementsByName('chkSamplePatientOnSave');
	for(var i=0; i<acceptancedateC.length; i++){
		
		
		
		var getdates = getdate.split('-');
		
		var getReqdates= requisitionDateC[i].value.split('-');
		//var monno=new Date(getReqdates[1].toLowerCase()+'-1-01').getMonth()+1

		monno=getIndexOfMonth(getReqdates[1]);
		
		if(new Date(getdates[0],getdates[1],getdates[2]) >= new Date(getReqdates[2],monno,getReqdates[0]))
		{
			acceptancedateC[i].value=getdate;
			chkSamplePatientOnSave[i].checked=true;
			acceptancedateC[i].onchange();
			chkSamplePatientOnSave[i].onchange();
		}else {
			chkSamplePatientOnSave[i].checked=false;
			chkSamplePatientOnSave[i].onchange();
		}
		
	}
 
}


/* Added by Prashant to get sub string till nth accurence of character */
function GetSubstringNthAccurence(str, substring, n) {
	    var times = 0, index = null;

	    while (times < n && index !== -1) {
	        index = str.indexOf("#", index+1);
	        times++;
	    }
	    
	var res = str.substring(0, index);
	return res;
	}


</script>



<script>
// just for the demos, avoids form submit
jQuery.validator.setDefaults({
debug: true,
success: "valid"
});
$( "#myform" ).validate({
rules: {
emailId: {
 
email: true
}
}
});
</script>
<style>

#blanket {
   background-color:#111;
   opacity: 0.65;
   *background:none;
   position:absolute;
   z-index: 9001;
   top:2px;
   left:0px;
   width:100%;
}

#popUpDiv5 {
	position:absolute;
	  
	background:#CCE6FF;
	width:400px;
	height:240px;
	border:5px solid #000;
	z-index: 9002;
}

#popUpDiv5 a {position:relative; top:1px; left:20px}

</style>
<script type="text/javascript">


var reqformtestnames="";
var reqformtestcodes="";



function reasonextrashw(obj,index)
{
	//alert("reasonextrashw");
	//var value = $("[name='rejectionReason'] option:selected").text();
    var value = $(obj).children("option:selected").val();
	
	var idd="rej"+index;
	var idd1="rejj"+index;

	if(value=="-2")
		{
	document.getElementById(idd).style.display="";
		document.getElementById(idd1).focus();

			}else
		{
		document.getElementById(idd).style.display="none";
		document.getElementById(idd1).value="";
		}

	}

function selectalll(obj)
{
   if(obj.checked)
	 {
		 //alert("checked");

        for(var a=0;a<	document.getElementsByName('chkSamplePatient').length;a++)

            {
        	document.getElementsByName('chkSamplePatient')[a].checked=true;
        	document.getElementById('nextDiv').style.display="";
            
            }
		 
	 }   
   else
	   {

       for(var a=0;a<	document.getElementsByName('chkSamplePatient').length;a++)
       {
   	document.getElementsByName('chkSamplePatient')[a].checked=false;
   	document.getElementById('nextDiv').style.display="none";
    
       }
	  // alert("unchecked");
	   }
}

function showpatdetails(obj)
{
  var id=obj.id;
  var counterr=id.split("#")[0];

  var idd=counterr+"show";
  
  document.getElementById(idd).style.display=""; 
      
}


function hidepatdetails(obj)
{
  var id=obj.id;
  var counterr=id.split("#")[0];

  var idd=counterr+"show";
  
  document.getElementById(idd).style.display="none"; 
      
}

function f2()
{
	document.getElementById('reqformss').style.display="none";
}



function f1()
{
	document.getElementById('reqformss').style.display="";
	$("#tbll").find("tr:not(:first)").remove();

	var checktest="";
	 var overalltest="";
	 var reqnoo="";
     for(var a=0;a<document.getElementsByName("chkSamplePatientOnSave").length;a++)
 {
    	// alert(a);
    	 //alert(document.getElementsByName("chkSamplePatientOnSave")[a].value);
    	 //alert((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[6]);
    	 overalltest+=(document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[6]+";";
    	 
  }


     for(var a=0;a<document.getElementsByName("chkSamplePatientOnSave").length;a++)
     { 
                //alert(a+"len"+document.getElementsByName("chkSamplePatientOnSave").length);
    	 var valuess=(document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#");

    	 var reqNo=valuess[1];
    	 var testCode=valuess[6];
    	 var testName=valuess[5];
    	 var labCode=valuess[3];
    	 var labName=valuess[8];
    	 var dNo=valuess[2];
          //alert("reqNo"+reqNo);
	 	   	 		
    	 
     var table = document.getElementById("tbll");

                      //   alert("testCodetestCode"+testCode);
                  	  var mappedcheckedtest=checkreqformTestType(testCode);
                  	  
                      var mappedTest=mappedcheckedtest.split("#")[2];
                     // alert("matchtest"+matchtest);
                      var formtype=mappedcheckedtest.split("#")[1];


                      if(mappedTest=="0")
             		 {
                  		// alert("mappedTest 0");
                            // not match
                            var rowCount = table.rows.length;
           	//alert("rowCount"+rowCount);
           var row = table.insertRow(rowCount);
           row.id=testCode+"#"+reqNo;
           row.name=testName;
           row.value=labName;
           row.testt=testCode;
           reqformtestnames+=testName;
           reqformtestcodes+=testCode;


           if((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[9]=="1")
               {
                var cell1 = row.insertCell(0);
           		var cell2 = row.insertCell(1);
           		cell1.innerHTML = (document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[5];

           		if((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[9]=="1")
           			cell2.innerHTML = "<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+","+"\""+(dNo)+"\""+","+"\""+(reqNo)+"\""+")'>";

           		checktest+=testCode+"@"+reqNo+"#";
               }
           
             		 }

                      else
             		 {
                    	  if(checktest=='')
                   		 {
                        		 // 1st time null
                            //   alert("mappedTest not 0 1tym null" );
                      		    var rowCount = table.rows.length;
           	//alert("rowCount"+rowCount);
           var row = table.insertRow(rowCount);
           
     		
           if(mappedTest.includes("@"))
        		row.id = testCode+"#"+reqNo;
       	   else
       		   row.id = mappedTest+"#"+reqNo;
           //row.id=testCode+"#"+reqNo;
           row.name=testName;
           row.value = labName;
    		row.testt=testCode;
           reqformtestnames+=testName;
           reqformtestcodes+=testCode;


           if((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[9]=="1")
               {
           var cell1 = row.insertCell(0);
           		var cell2 = row.insertCell(1);
           		cell1.innerHTML = (document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[5];

           		if((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[9]=="1")
           			cell2.innerHTML = "<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+","+"\""+(dNo)+"\""+","+"\""+(reqNo)+"\""+")'>";

           		checktest+=testCode+"#"+mappedTest+"#";
               }
           
                   		 }
                    	  else
                        		  {
                    			  if(!mappedTest.includes("@")) // if not conatin @
                                  {
   
                                    //  alert("not contain @");
                                    //  alert("not contain @ checktest"+checktest);
                                    //  alert("not contain @ mappedTest"+mappedTest);
                                      
                    			  if(checktest.includes(mappedTest)) //find dervied test
                             	   {
//
                               	   //  alert("match test");

                    				  var flag="2";
                                      //  alert("match");
                                    //    alert("match"+matchtest);
                                       reqformtestnames+=";"+testName;
                                        reqformtestcodes+=";"+testCode;
                                        var tr ="";
                                           // alert("match"+testCode+"#"+reqNo+document.getElementById(mappedTest+"#"+reqNo)+document.getElementById(mappedTest+"#"+reqNo));

                                             if(document.getElementById(mappedTest+"#"+reqNo)==null && document.getElementById(testCode+"#"+reqNo)==null)
                                                 {
                                                     // alert("req no. change ");

                                                      var rowCount = table.rows.length;
                                  		           	//alert("rowCount"+rowCount);
                                  		           var row = table.insertRow(rowCount);
                                                          if(mappedTest!="0")
                                                      {        
                                     		         if(mappedTest.includes("@"))
                                  	        		row.id = testCode+"#"+reqNo;
                                  	       	   else
                                  	       		   row.id = mappedTest+"#"+reqNo;
                                                      }
                                                          else
                                                              {
                                                        	  row.id = testCode+"#"+reqNo;
                                                              }     
                                                          row.value = labName;
                                          				row.testt=testCode;
                                  		           reqformtestnames+=testName;
                                  		           reqformtestcodes+=testCode;
                 

                                  		           if((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[9]=="1")
                                  		               {
                                  		           var cell1 = row.insertCell(0);
                                  		           		var cell2 = row.insertCell(1);
                                  		           		cell1.innerHTML = (document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[5];

                                  		           		if((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[9]=="1")
                                  		           			cell2.innerHTML = "<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+","+"\""+(dNo)+"\""+","+"\""+(reqNo)+"\""+")'>";

                                  		           	//	checktest+=testCode+"@"+reqNo+"#";
                                  		           		checktest+=testCode+"#"+mappedTest+"#";
                                  		               }
                              		               
                                                 }
                                             else
                                                 {
                                             if(document.getElementById(mappedTest+"#"+reqNo)==null)
                                          tr = document.getElementById(testCode+"#"+reqNo);
                                          else
                                          tr = document.getElementById(mappedTest+"#"+reqNo);    

                                             row.value = tr.value+";"+labName;
                             				 row.testt = tr.testt+";"+testCode;
                             				tr.testt=row.testt;
                                        var rwname=tr.name+"#";
                                        				rwname+=testCode;
                                        				rwnamee=rwname;
                                        				tr.name=rwname;
                                        				//alert("trridname"+tr.name+"@"+rwnamee);
                                        		//		var trr=document.getElementsByName(mappedTest).value;
                                        				
                                        				var td = tr.getElementsByTagName("td");
                                        				
                                        				if((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[9]=="1")
                                                       {	
                                        				for(var i=1;i<td.length;i++) {
                                        					var namee=td[0].innerHTML+",";
                                        					var nameee=td[0].innerHTML+";";
                                                            // alert(td[1].innerHTML);
                                        					namee+=testName;
                                        					nameee+=testName;
                                        					reqformtestnames=nameee;
                                        					//alert("reqformtestnames"+reqformtestnames);
                                        					//reqformtestcodes=rwnamee;
                                        	                   //alert("td[i].innerHTML"+td[i].innerHTML);
                                                     //     alert("name"+namee);
                                                           td[0].innerHTML=namee;
                                        				    //console.log(td[i].innerHTML);
                                        				}
                                                       }
                                        				//checktest+=testCode+"#";
                                        				checktest+=testCode+"#"+mappedTest+"#";
                                                 }	
                            	   }
                    			  
                    			  else // not find
                        			  {

                    				   var rowCount = table.rows.length;
                    		           	//alert("rowCount"+rowCount);
                    		           var row = table.insertRow(rowCount);
                    		           if(mappedTest!="0")
                                       {        
                      		         if(mappedTest.includes("@"))
                   	        		row.id = testCode+"#"+reqNo;
                   	       	   else
                   	       		   row.id = mappedTest+"#"+reqNo;
                                       }
                                           else
                                               {
                                         	  row.id = testCode+"#"+reqNo;
                                               } 
                    		           row.name=testName;
                    		           row.value=labName;
                          				row.testt=testCode;
                    		           reqformtestnames+=testName;
                    		           reqformtestcodes+=testCode;


                    		           if((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[9]=="1")
                    		               {
                    		           var cell1 = row.insertCell(0);
                    		           		var cell2 = row.insertCell(1);
                    		           		cell1.innerHTML = (document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[5];

                    		           		if((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[9]=="1")
                    		           			cell2.innerHTML = "<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+","+"\""+(dNo)+"\""+","+"\""+(reqNo)+"\""+")'>";

                    		           	//	checktest+=testCode+"@"+reqNo+"#";
                    		           		checktest+=testCode+"#"+mappedTest+"#";
                    		               }

                        			  }
                                  }
                    			  else // find @ master form
                        			  {


                    				 var flag="2";
                                      //  alert("match");
                                        matchtest=testCode;
                                      //  matchtest=matchtest.replace("@","#");
                                    //    alert("match"+matchtest);
                                       reqformtestnames+=";"+testName;
                                        reqformtestcodes+=";"+testCode;
                                        if(document.getElementById(matchtest+"#"+reqNo)==null)
                                            {

                                        	 var rowCount = table.rows.length;
                         		           	//alert("rowCount"+rowCount);
                         		           var row = table.insertRow(rowCount);
                         		          if(mappedTest!="0")
                                          {        
                         		         if(mappedTest.includes("@"))
                      	        		row.id = testCode+"#"+reqNo;
                      	       	   else
                      	       		   row.id = mappedTest+"#"+reqNo;
                                          }
                                              else
                                                  {
                                            	  row.id = testCode+"#"+reqNo;
                                                  }   
                                          
                         		           row.name=testName;
                         		           row.value=labName;
                               				row.testt=testCode;
                         		           reqformtestnames+=testName;
                         		           reqformtestcodes+=testCode;


                         		           if((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[9]=="1")
                         		               {
                         		           var cell1 = row.insertCell(0);
                         		           		var cell2 = row.insertCell(1);
                         		           		cell1.innerHTML = (document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[5];

                         		           		if((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[9]=="1")
                         		           			cell2.innerHTML = "<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+","+"\""+(dNo)+"\""+","+"\""+(reqNo)+"\""+")'>";

                         		           	//	checktest+=testCode+"@"+reqNo+"#";
                         		           		checktest+=testCode+"#"+mappedTest+"#";
                         		               }

                                            }
                                        else
                                            {
                                        var tr = document.getElementById(matchtest+"#"+reqNo);
                                        row.value = tr.value+","+labName;
                        				tr.value=row.value;
                        				row.testt = tr.testt+";"+testCode;
                        				tr.testt=row.testt;
                                        var rwname=tr.name+"#";
                                        				rwname+=testCode;
                                        				rwnamee=rwname;
                                        				tr.name=rwname;
                                        				//alert("trridname"+tr.name+"@"+rwnamee);
                                        		//		var trr=document.getElementsByName(mappedTest).value;
                                        				
                                        				var td = tr.getElementsByTagName("td");
                                        				
                                        				if((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[9]=="1")
                                                       {	
                                        				for(var i=1;i<td.length;i++) {
                                        					var namee=td[0].innerHTML+",";
                                        					var nameee=td[0].innerHTML+";";
                                                            // alert(td[1].innerHTML);
                                        					namee+=testName;
                                        					nameee+=testName;
                                        					reqformtestnames=nameee;
                                        					//alert("reqformtestnames"+reqformtestnames);
                                        					//reqformtestcodes=rwnamee;
                                        	                   //alert("td[i].innerHTML"+td[i].innerHTML);
                                                     //     alert("name"+namee);
                                                           td[0].innerHTML=namee;
                                        				    //console.log(td[i].innerHTML);
                                        				}
                                                       }
                                        				//checktest+=testCode+"#";
                                            }		

                        			  }                                 	   
                        		  }   
                          }    	 
                    		 
             		 }
                
     
}

/* function f1()
{
	document.getElementById('reqformss').style.display="";
	$("#tbll").find("tr:not(:first)").remove();

	var checktest="";
	 var overalltest="";
     for(var a=0;a<document.getElementsByName("chkSamplePatientOnSave").length;a++)
 {
    	// alert(a);
    	 //alert(document.getElementsByName("chkSamplePatientOnSave")[a].value);
    	 //alert((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[6]);
    	 overalltest+=(document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[6]+";";
    	 
  }


     for(var a=0;a<document.getElementsByName("chkSamplePatientOnSave").length;a++)
     { 
                //alert(a);
    	 var valuess=(document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#");

    	 var reqNo=valuess[1];
    	 var testCode=valuess[6];
    	 var testName=valuess[5];
    	// alert("testName"+testName);
    	 var labCode=valuess[3];
    	 var labName=valuess[8];
    	 var dNo=valuess[2];
          //alert("reqNo"+reqNo);
	 		 		
    	 
     var table = document.getElementById("tbll");

                      //   alert("testCodetestCode"+testCode);
                  	  var mappedcheckedtest=checkreqformTestType(testCode);
                  	  
                      var matchtest=mappedcheckedtest.split("#")[2];
                     // alert("matchtest"+matchtest);
                      var formtype=mappedcheckedtest.split("#")[1];
          if(checktest=='' )
              {

                   //alert("testCode"+testCode);
        	  var rowCount = table.rows.length;
           	//alert("rowCount"+rowCount);
           var row = table.insertRow(rowCount);
           row.id=testCode+"#"+reqNo;
           row.name=testName;
           reqformtestnames+=testName;
           reqformtestcodes+=testCode;


           if((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[9]=="1")
               {
           var cell1 = row.insertCell(0);
           		var cell2 = row.insertCell(1);
           		cell1.innerHTML = (document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[5];

           		if((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[9]=="1")
           			cell2.innerHTML = "<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+","+"\""+(dNo)+"\""+","+"\""+(reqNo)+"\""+")'>";

           		checktest+=testCode+"@"+reqNo+"#";
               } 
              }
          else if(matchtest!=0)
              {
              
              //alert("matchtest!=0");
        	//  alert("test2"+testCode);
        	//  alert("test2"+testName);
        //	  alert("matchtest"+matchtest);
        	  
                   var flag="1";
                  // alert("samechecktest"+checktest);
             
       //if(checktest.indexOf(matchtest)==0) 
           for(var aa=0;aa<matchtest.split("@").length;aa++)
           {
               //alert("a"+a);
                   var test=matchtest.split("@")[aa];
                      test=test+"@"+reqNo;
                 // alert("test for check"+test);
                   //alert("test check from"+checktest);
                 //  alert("test for check"+test);
                   //alert(checktest.indexOf(test));
                   
                   for(var b=0;b<checktest.split("#").length;b++)
                     {
                            var checktestt=checktest.split("#")[b];
                        //    alert("test check from new"+checktestt);
                        //    alert("test check new"+test);
                            
                                   if(checktestt.indexOf(test)==0)
                       {
                            flag="2";
                      //  alert("match");
                        matchtest=test;
                        matchtest=matchtest.replace("@","#");
                    //    alert("match"+matchtest);
                       reqformtestnames+=";"+testName;
                        reqformtestcodes+=";"+testCode;
                        var tr = document.getElementById(matchtest);
                        var rwname=tr.name+"#";
                        				rwname+=testCode;
                        				rwnamee=rwname;
                        				tr.name=rwname;
                        				//alert("trridname"+tr.name+"@"+rwnamee);
                        		//		var trr=document.getElementsByName(mappedTest).value;
                        				
                        				var td = tr.getElementsByTagName("td");
                        				
                        				if((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[9]=="1")
                                       {	
                        				for(var i=1;i<td.length;i++) {
                        					var namee=td[0].innerHTML+",";
                        					var nameee=td[0].innerHTML+";";
                                            // alert(td[1].innerHTML);
                        					namee+=testName;
                        					nameee+=testName;
                        					reqformtestnames=nameee;
                        					//alert("reqformtestnames"+reqformtestnames);
                        					//reqformtestcodes=rwnamee;
                        	                   //alert("td[i].innerHTML"+td[i].innerHTML);
                                     //     alert("name"+namee);
                                           td[0].innerHTML=namee;
                        				    //console.log(td[i].innerHTML);
                        				}
                                       }
                        				checktest+=testCode+"#";
                       }

                       }

                   
           }
        
           if(flag=="1")
           {
    	   var rowCount = table.rows.length;
    	   reqformtestnames+="!"+testName;
    	   reqformtestcodes+="!"+testCode;
    	   	//alert("rowCount"+rowCount);
    	   var row = table.insertRow(rowCount);
    	   row.id=testCode+"#"+reqNo;
    	   row.name=testName;
    	   if((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[9]=="1")
    	   {
    	  	 
    	   var cell1 = row.insertCell(0);
    	   		var cell2 = row.insertCell(1);
    	   		cell1.innerHTML = (document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[5];

    	   		if((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[9]=="1")
    	   			cell2.innerHTML = "<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+","+"\""+(dNo)+"\""+","+"\""+(reqNo)+"\""+")'>";
    	  		
    	   			checktest+=testCode+"@"+reqNo+"#";
    	         // alert("overalltest"+overalltest);
    	          }
           }
             
            }
          else 
          {
               
         //   alert("matchtest==0");
          
 var rowCount = table.rows.length;
 reqformtestnames+="!"+testName;
 reqformtestcodes+="!"+testCode;
 	//alert("rowCount"+rowCount);
 var row = table.insertRow(rowCount);
 row.id=testCode;
 row.name=testName;
 if((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[9]=="1")
 {
	 
 var cell1 = row.insertCell(0);
 		var cell2 = row.insertCell(1);
 		cell1.innerHTML = (document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[5];

 		if((document.getElementsByName("chkSamplePatientOnSave")[a].value).split("#")[9]=="1")
 			cell2.innerHTML = "<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+","+"\""+(dNo)+"\""+","+"\""+(reqNo)+"\""+")'>";
		
 			checktest+=testCode+"@"+reqNo+"#";
       // alert("overalltest"+overalltest);
        }
          
     } 
  }
 //    alert("golobalreqformtestnames"+reqformtestnames);
   //  alert("golobalreqformtestcodes"+reqformtestcodes);
     
} */



function checkreqformTestType(TestCode)
{
	
	//alert("setAptNoUsingAjax"+appoitmentNo+"labcode"+tmpLabCode+"testCode"+tmpTestCode);
	
	var flg = false;
	var remarks = "";
	var _mode = "AJX_CHECK_REQFORM_TESTTYPE";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/sampleCollection.cnt?hmode="+_mode+"&testCode="+TestCode, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert("checkreqformTestType"+data);
			remarks = data;
			flg = true;
		},
        error: function(error)
        {
            //remarks = tmpLabTestCodeArray;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
//	alert("amppedtest"+remarks);
	return remarks;
}

function showSearchDiv(obj)
{

	//updated by krishnan nema on 08/10/2018
	
	//var selectedMachineCode = document.getElementsByName("machineCode")[0].value;
		//document.getElementsByName("selectedmachineCode")[0].value = selectedMachineCode;

		//alert("heloo======"+document.getElementsByName("selectedmachineCode")[0].value);
		//alert("hello==="+document.getElementsByName("patientType")[0].value);
	//alert(obj.value);
	if(obj.value==-1)
	{
		document.getElementById('goButton').style.display="none";
	}
	else
	{
		// document.getElementById('goButton').style.display="";
		document.getElementsByName("sampleAreaName")[0].value=document.getElementsByName("sampleAreaCode")[0].options[document.getElementsByName("sampleAreaCode")[0].selectedIndex].text;
		document.getElementsByName("isSampleAreaSelected")[0].value="1";
		//document.getElementsByName("showStatus")[0].value="1";
		
		document.getElementsByName("hmode")[0].value="NEW";
		document.forms[0].submit();
	}
	
	
}

function ShowRequistionForm(testCode,testName,labCode,labName,requisitionDNo,reqNoo)
{
	var tr="";
	 var getData =  checkreqformTestType(testCode+"#"+reqNoo);
	 var newtest=getData.split("#")[2];
	 
	 var idd=testCode+"#"+reqNoo;
	 if(document.getElementById(idd)==null && (newtest!="0"))
		 {
		// alert("id null "+newtest);
	   tr = document.getElementById(newtest+"#"+reqNoo);
		 }
	 else
		 {
		// alert("id not null "+idd);
   	 tr = document.getElementById(idd);
		 }
   // alert("testt"+tr.testt);
   // alert("testt"+tr.value);
	 var td = tr.getElementsByTagName("td");
var nameee=td[0].innerHTML;
//alert(nameee);
testName=nameee;

   labName=tr.value;
	/* var idd=testCode+"#"+reqNoo;
	  var tr = document.getElementById(idd);
  var td = tr.getElementsByTagName("td");
   var nameee=td[0].innerHTML;
//alert(nameee);
   testName=nameee; */



	 var status=1;
	var hmode="EXISTINGREQUISITIONFORMDATA";
	//alert(testCode);
	var url1="/HISInvestigationG5/new_investigation/requisitionformprocess.cnt";
	mywindow=window.open (url1+"?testCode="+testCode+"&testName="+testName+"&labCode="+labCode+"&labName="+labName+"&hmode="+hmode+"&status="+status+"&requisitionDNo="+requisitionDNo,"_blank","scrollbars=1,directories=no, status=no,width=1000, height=500,top=200,left=500");
}

function showInstructions5(inst,collInst)
{
	//alert(inst);
	
	deleteTableInst();
	if(collInst.split('#')[1]=='' && inst.split('#')[1]!='')
	   {
	 		
			addRowToTableInst(inst);
			
     	}
		if(collInst.split('#')[1]!='' && inst.split('#')[1]=='')
	     {
			
				addRowToTableInst(collInst);
	     }
				
		if(collInst.split('#')[1]!='' && inst.split('#')[1]!='')
	       {      
			addRowToTableInst(inst);
			addRowToTableInst(collInst);
			}
	
	popup("popUpDiv5");
	
	
	}
	
	
	function closeInstructions()
{
	popup("popUpDiv5");

	
	}
	
	
function addRowToTableInst(inst)
{
	
	//alert("d");
	var nRow=0;
	var tableObj=document.getElementById('allInstructions');
	
	var tr=document.createElement("TR");
	 
	//tr.setAttribute("id","setPdf");
	tableObj.appendChild(tr);
	var numRows=tableObj.rows.length;
		nRow=numRows;
 
	var tabRow=tableObj.insertRow(numRows);
	tabRow.id=parseInt(nRow);
	 
	var td1=document.createElement("TD");
var td2=document.createElement("TD");
 
	td1.innerHTML="<div  align='left' >"+inst.split('#')[0]+"</div>";   
	td1.className='tdfont';
	td1.width="1";
	
	td2.innerHTML="<div  align='left' >"+inst.split('#')[1]+"</div>";   
	td2.className='tdfont';
	td2.colspan="1";
	   
   
	tabRow.appendChild(td1); 
	tabRow.appendChild(td2);  
	//document.forms[0].numberOfRow.value=numRows;
	}
	
	

function deleteTableInst()
{
	
	for(var i = document.getElementById("allInstructions").rows.length-1; i > 0; i--)
	{
		document.getElementById("allInstructions").deleteRow(i);
		
	}
	
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

function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}
//Function for displaying selected Lab List
function validateTodayOrDate()
{
	success=false;        	   
    
    valFromDate=document.getElementsByName('fromDate')[0];
	valToDate=document.getElementsByName('toDate')[0];
	
	<%String systemdate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");%>
	document.getElementsByName('sysDate')[0].value="<%=systemdate%>";
	valSysDate=document.getElementsByName('sysDate')[0];
      
    if(compareDateCall(valFromDate,valToDate,2,"From Date","To Date") && compareDateCall(valToDate,valSysDate,2,"To Date","System Date"))
    {
	    success=true;
    }             
    return success;        
}
function onClickGO(hospitalCode)
{
	
	 if(!validateTodayOrDate())
	 {return false;}
 
	
	 var crno =document.getElementsByName("tempPatCRNo")[0].value;
     var textLength = crno.length;
     var hosCodeLen=hospitalCode.length;
    
     if(hosCodeLen==3)
		{ 
          if (textLength==5||textLength==13||textLength==0)
	        {
        	  document.getElementsByName("patCRNo")[0].value="";
        	  if(textLength==13)
	           {
	             document.getElementsByName("patCRNo")[0].value=crno;
	            }
              if(document.getElementsByName("labCode")[0].value=="-1")
        	      {
        	     	alert("Select Lab   Name ... ");
        		    document.getElementsByName("labCode")[0].focus();
        		    return false;
        	      }
        	        document.getElementsByName('hmode')[0].value="GETDETAILS";
        	        document.forms[0].submit();
        	   
             }

	          else
	          {     
	            alert("InValid CR No");
	            if(document.getElementsByName("tempPatCRNo")[0])
	            {
	            document.getElementsByName("tempPatCRNo")[0].focus()
	             }    
	           
	          }
       }
      if(hosCodeLen==5)
    	{ 
			    if (textLength==7||textLength==15||textLength==0)
				 {
			    	 document.getElementsByName("patCRNo")[0].value="";
			    	if(textLength==15)
						 {
						 document.getElementsByName("patCRNo")[0].value=crno;
						 }
					           if(document.getElementsByName("labCode")[0].value=="-1")
					       	{
					       		alert("Select Lab   Name ... ");
					       		 
					       		document.getElementsByName("labCode")[0].focus();
					       		return false;
					      	}
					       	 
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

function validateMinLength(elem,minlen) {
    var isValid = true;
  if(elem)
         value=elem.value;
  else
         value="";
                         
   if ((value.length<minlen))
		{
     	isValid = false;
     }
return isValid;

} 	 

function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}

function httpRequest(reqType,url,asynch)
{
	//Mozilla-based browsers
	if(window.XMLHttpRequest)
	{
		request = new XMLHttpRequest();
		initReq(reqType,url,asynch);
	}
	else if (window.ActiveXObject)
	{
		request=new ActiveXObject("Msxml2.XMLHTTP");
		if (! request)
			 request=new ActiveXObject("Microsoft.XMLHTTP");
		if(request)
		{
			initReq(reqType,url,asynch);
			/* Unlikely to branch here, as IE uses will be able to use either 
			one of the constructors*/
		}
		else
			alert("Your browser does not permit the use of all of this application's features!");
 	}
 	else
		alert("Your browser does not permit the use of all of this application's features!");
}

function initReq(reqType,url,isAsynch)
{
	//alert("inside init request");
 /* Specify the function that will handle the HTTP response */
	request.onreadystatechange=handleResponse;
	request.open(reqType,url,isAsynch);
 /* set the Content-Type header for a POST request */
	request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
	var queryString;
	request.send(queryString);
}

function ValidateSameCrNo(obj)
{
	
	if(obj.checked)
	{
		document.getElementById('nextDiv').style.display="";
                
	}


	
  else
     	{
	  document.getElementById('gob').style.display="";
      	document.getElementById('cancel').style.display="";
      	
          }
	 
	var objCurrentCheckBox=obj.value;
	//alert(obj.checked);
	if(obj.checked)
	{
		
		var cbs = document.getElementsByTagName('input');
		for(var i=0; i < cbs.length; i++) 
		{
			    if(cbs[i].type == 'checkbox') 
			    {
			      
			    	 if(cbs[i].checked && (cbs[i].value.split("#")[0]!=objCurrentCheckBox.split("#")[0]))
			    	{
			    		 
			    //	alert("please select same cr no");
			    //	document.getElementById('nextDiv').style.display="none";
			    //	obj.checked=false;
			    //	return false;
			    	} 
				}
		}
	}
 
}
	 

function submitFor()
{
	document.getElementsByName('showStatus')[0].value='0';
	document.getElementsByName('hmode')[0].value='NEW';
	document.forms[0].submit();
	}
	
function cancelFunc()
{
	window.parent.closeTab();
}
	
function displaySamplePatDetails()
{	


	document.getElementsByName('showStatus')[0].value='1';
	var count=0;
	document.getElementsByName('isPatDetailPage')[0].value="1";
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


	//alert("concatenateChkBoxVal"+concatenateChkBoxVal);
	document.getElementsByName('selectedCheckbox')[0].value=concatenateChkBoxVal;
	document.getElementsByName('hmode')[0].value='SHOWPATDETAILS';
 	document.forms[0].submit();
	}

function handleResponse()
{
	//alert("inside Response");
	if(request.readyState == 4)
	{
		if(request.status == 200)
		{
			//alert("response sucessfull");
		}
		else
			alert("A problem occurred with communicating between the XMLHttpRequest object and the server program.");
	}
}



 
function showLegends(){
	  document.getElementById("divLegends").style.display="block"; 
}
function showLegendsNone(){
document.getElementById("divLegends").style.display="none";
}


function onChane(obj,k,classs)
{

    
  
	if(obj.value=='0')
		{
		
		//alert(obj.value);
		//alert(k);
		document.getElementById("showthis").style.display="";
		document.getElementById("showthi_"+k).style.display="";
		document.getElementById("showth_"+k).style.display="";
		document.getElementById("showthi").style.display="";

	       if(classs!="0" )  
	       {

	      
		for(var p=0;p<document.getElementsByName("accepted").length;p++)
		 {

		//	 alert(document.getElementsByName("accepted")[p].className==classs);
		 //document.getElementsByTagName("accepted")[p].setAttribute("class", "democlass"); 
                 if((document.getElementsByName("accepted")[p].className)==classs)
                     {
                	 document.getElementsByName("accepted")[p].value="0";
                	var idd= document.getElementsByName("accepted")[p].tabIndex;
                
                	document.getElementById("showthis").style.display="";
            		document.getElementById("showthi_"+idd).style.display="";
            		document.getElementById("showth_"+idd).style.display="";
            		document.getElementById("showthi").style.display="";


              	                		
                 
                  }
		 }
	       }		 
		
		}
	else{
		//alert(obj.value);
	 
		document.getElementById("showthi_"+k).style.display="none";
		
		document.getElementById("showth_"+k).style.display="none";

		  if(classs!="0" )  
	       {

	      
		for(var p=0;p<document.getElementsByName("accepted").length;p++)
		 {
		 //document.getElementsByTagName("accepted")[p].setAttribute("class", "democlass"); 
                if((document.getElementsByName("accepted")[p].className)==classs)
                    {
               	 document.getElementsByName("accepted")[p].value="1";
               	var idd= document.getElementsByName("accepted")[p].tabIndex;
               	
               	document.getElementById("showthi_"+idd).style.display="none";
        		
        		document.getElementById("showth_"+idd).style.display="none";


             	                		
                
                 }
		 }
	       }
	       
	}
	if(k==null)
		{
		document.getElementById("showthis").style.display="none";
		document.getElementById("showthi").style.display="none";
		
		}
	}



function onClickSavecheckedgrp(obj,k,callingfrom)
{

	var datanew=obj.value;
	var splitedAutoGenValuenewnew=datanew.split("#");
	var grpcodenewnew=splitedAutoGenValuenewnew[10];
	var reqnonewnew=splitedAutoGenValuenewnew[1];
	var flag="0";
	var cbsnew =document.getElementsByName('chkSamplePatientOnSave');
	var acceptancedateC = document.getElementsByClassName('acceptancedateC');
	
	if(obj.checked==true)
       {
		if(grpcodenewnew!="null" && grpcodenewnew!="0")
        {

			for(var i1=0; i1 < cbsnew.length; i1++)
		     { 
			  var data=cbsnew[i1].value;
	           var splitedAutoGenValuenew=data.split("#");
				var grpcodenew=splitedAutoGenValuenew[10];
					var reqnonew=splitedAutoGenValuenew[1];

					if(reqnonew==reqnonewnew && grpcodenew==grpcodenewnew )
	         	   {
					   flag="1";
	         		   cbsnew[i1].checked=true;
	         		   
	         		   /* Added by prashant to edit checkbox value when date change  */
   	         	       var res = GetSubstringNthAccurence(cbsnew[i1].value, "#", 12)
   	        	       if(res!=null && res!=""){ cbsnew[i1].value=res;}
	         		  
					   onClickSavenew(cbsnew[i1],k,callingfrom);
					   
					   /* Added by prashant to edit group date value when one date change  */
					   var groupAccepDateChangeId="#"+k+"acceptancedate";
					   var DateChange=$(groupAccepDateChangeId).val();
					  acceptancedateC[i1].value=DateChange;
					 }
			}
				//onClickSavenew(cbsnew[i1],k,callingfrom);
        }
		else
			{
				/* Added by prashant to edit when date change  */
   		    	 var res = GetSubstringNthAccurence(obj.value, "#", 12)
   		    	 if(res!=null && res!=""){  obj.value=res;} 
				
				onClickSave(obj,k,callingfrom);
				
			}
       }
         else
             {

        	 if(grpcodenewnew!="null" && grpcodenewnew!="0")
             {
     			for(var i1=0; i1 < cbsnew.length; i1++)
     		     {  var data=cbsnew[i1].value;

     	             var splitedAutoGenValuenew=data.split("#");
     					
     				  var grpcodenew=splitedAutoGenValuenew[10];
     				  var reqnonew=splitedAutoGenValuenew[1];
					
     				  if(reqnonew==reqnonewnew && grpcodenew==grpcodenewnew )
     	         	   {
     	         		   flag="1";
     	         		   cbsnew[i1].checked=false;
     	         		   
     	         		 /* Added by prashant to edit when checkbox unchecked */
     	         		 var res = GetSubstringNthAccurence(cbsnew[i1].value, "#", 12)
     	         		 if(res!=null && res!=""){ cbsnew[i1].value=res;}
     	        		     	         		 
     	         		/*  Added by prashant to edit group date value when one date change */
  					   	var groupAccepDateChangeId="#"+k+"acceptancedate";
  					    var DateChange=$(groupAccepDateChangeId).val();
  					    acceptancedateC[i1].value=DateChange;
     	         	   }
     			}

             } else
                 {
            	 /* Added by prashant to edit when checkbox unchecked */
            	 var res = GetSubstringNthAccurence(obj.value, "#", 12)
            	 if(res!=null && res!=""){  obj.value=res;} 

                  }
             }
}


function onClickSavenew(obj,k,callingfrom)
{

    document.getElementById("saveDiv").style.display=""; 
	//document.getElementById('gob').style.display="none";

	//alert(document.getElementById(k+"chkBox").value);
	var cbsnew=obj.value;
	var cbs=cbsnew.split("#");
	var idonnload=obj.id;
	idonnload=idonnload+"onload";
	//alert("idonnload");
	//alert("idonnload"+document.getElementById(idonnload).value);
	var labCode=cbs[3];
	var testCode=cbs[6];
	var testName=cbs[5];
	var config=cbs[7];
	var labName=cbs[8];
	var sampleareacode=document.getElementsByName('sampleAreaCode')[0].value;
	var sampleareaname=document.getElementsByName('sampleAreaName')[0].value;
	//alert(sampleareaname);
	if(config==2)
		{ 
	//var autoFormate=CheckAutoLabNoFormate(labCode,testCode,config,sampleareacode)
      
      var autoFormate=document.getElementById(idonnload).value;
      // alert("ccc"+autoFormate);
	var autoForm=autoFormate.split("#");
	//alert(autoForm[0]);
	if(autoFormate=="null"||autoForm[0]=='-')
		{

		if(callingfrom=="0")
			{
		alert("No Lab Number Formate Is Configured For    "+  testName  + "("+ labName +")   Please Configure From Lab Number Configuration Master ");
			}

		obj.checked=false;
		
		
		//document.getElementById(indexWithSubIndex+"unCheck").checked = false;
		}
	else
		{
		//document.getElementById(k+"chkBox").checked=true;
	
		
		var autoValue="#";
		autoValue+=autoFormate;
		 /* Added by Prashant */
		var acceptancedate =setAcceptanceDate(k, obj);
		obj.value+=autoValue+acceptancedate;
		}
		}
	else
		{

		//var autoFormate=CheckAutoLabNoFormate(labCode,testCode,config,sampleareacode)
		//alert(autoFormate);
		
		//var autoValue="#null#null#null#null#null#null#null#null#null#null#null";
		/* commented by Prashant */
		/* var autoValue="#null#null#null#null#null#null#null#null#null#null#null#null#null#null";
		autoValue+=autoFormate; */
		
		/* Added by Prashant */
		var autoValue="#null#null#null#null#null#null#null#null#null#null#null#null#null#";
		//autoValue+=autoFormate;
		
		 /* Added by Prashant */
		var acceptancedate =setAcceptanceDate(k, obj);
		obj.value+=autoValue+acceptancedate;
		//document.getElementById(k+"chkBox").checked=true;
		}
	
	/* var cbs =document.getElementsByName('chkSamplePatientOnSave');
	// alert(cbs.length);
	for(var i=0; i < cbs.length; i++) {
			
		if(cbs[i].checked)
    	{
		var id=cbs[i].id;
		//alert(id);
		var indexWithSubIndex=id.substring(0, 1);
    	}   
	}
	 */
}

function onClickSave(obj,k,callingfrom)
{

	//alert("onClickSave"+obj.checked);
	if(obj.checked)
		{
		document.getElementById("saveDiv").style.display=""; 
	           //     document.getElementById('gob').style.display="none";
		}
	  else
	     	{
	      	document.getElementById("saveDiv").style.display="none";

              }
	
	//alert(document.getElementById(k+"chkBox").value);
	
	var cbs=document.getElementById(k+"chkBox").value.split("#");


	var idonnload=obj.id;
	idonnload=idonnload+"onload";
	//alert("idonnload");
	//alert("idonnload"+document.getElementById(idonnload).value);
	
	
	var labCode=cbs[3];
	var testCode=cbs[6];
	var testName=cbs[5];
	var config=cbs[7];
	var labName=cbs[8];
	var sampleareacode=document.getElementsByName('sampleAreaCode')[0].value;
	var sampleareaname=document.getElementsByName('sampleAreaName')[0].value;
	//alert(sampleareaname);
	if(config==2)
		{ 
	//var autoFormate=CheckAutoLabNoFormate(labCode,testCode,config,sampleareacode)
	var autoFormate=document.getElementById(idonnload).value;
	
//	alert("ccc"+autoFormate);
	var autoForm=autoFormate.split("#");
	//alert(autoForm[0]);
	if(autoFormate=="null"||autoForm[0]=='-')
		{

		if(callingfrom=="0")
			{
		alert("No Lab Number Formate Is Configured For    "+  testName  + "("+ labName +")   Please Configure From Lab Number Configuration Master ");
			}

		document.getElementById(k+"chkBox").checked=false;
		
		
		//document.getElementById(indexWithSubIndex+"unCheck").checked = false;
		}
	else
		{
		//document.getElementById(k+"chkBox").checked=true;
	
		
		var autoValue="#";
		autoValue+=autoFormate;
		
		 /* Added by Prashant */
		var acceptancedate =setAcceptanceDate(k, obj);
		
		var checkbox=document.getElementById(k+"chkBox");
		checkbox.value+=autoValue+acceptancedate;
		}
		}
	else
		{

		//var autoFormate=CheckAutoLabNoFormate(labCode,testCode,config,sampleareacode)
		//alert(autoFormate);
		
		/* commented by Prashant */
		/* var autoValue="#null#null#null#null#null#null#null#null#null#null#null#null#null#null";
		autoValue+=autoFormate; */
		
		
		//var autoValue="#null#null#null#null#null#null#null#null#null#null#null";
		
		/* Added by Prashant */
		var autoValue="#null#null#null#null#null#null#null#null#null#null#null#null#null#";
		//autoValue+=autoFormate;
		
		 /* Added by Prashant */
		var acceptancedate =setAcceptanceDate(k, obj);
		
		var checkbox=document.getElementById(k+"chkBox")
		checkbox.value+=autoValue+acceptancedate;
		}
	
	/* var cbs =document.getElementsByName('chkSamplePatientOnSave');
	// alert(cbs.length);
	for(var i=0; i < cbs.length; i++) {
			
		if(cbs[i].checked)
    	{
		var id=cbs[i].id;
		//alert(id);
		var indexWithSubIndex=id.substring(0, 1);
    	}   
	}
	 */
}




function CheckAutoLabNoFormate(LabCode,TestCode ,config,sampleareacode)
{

	//alert("inside Ajax"+LabCode+TestCode+config);
	//alert(document.getElementsByName("patType")[0].value);
	var flg = false;
	var autoGenFormate = "";
	var _mode = "AJX_CHECK_AUTO_SAMPLENO_GEN";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/onlinePatientAcceptance.cnt?hmode="+_mode+"&labCode="+LabCode+"&tmpTestCode="+TestCode+"&config="+config+"&sampleAreaCode="+sampleareacode, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			autoGenFormate = data;
			flg = true;
		},
        error: function(error)
        {
            labTestCodeArray = tmpLabTestCodeArray;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return autoGenFormate;
}



function onSave()
{

	var count=0;
	 
	var concatenateChkBoxVal="";
	 
	var cbs =document.getElementsByName('chkSamplePatientOnSave');

	for(var i=0; i < cbs.length; i++) {
			
		if(cbs[i].checked)
    	{
    		
    	count++;	
		var id=cbs[i].id;
		//alert(id);
		var indexWithSubIndex=id.substring(0, 1);
    	//alert(indexWithSubIndex);
		//var index=indexWithSubIndex.split(".")[0];
		//var subIndex=indexWithSubIndex.split(".")[1];
		if(document.getElementById(indexWithSubIndex+"dailyNO").value=="")
	 	{
	 		alert("Enter Lab No  ");
      		  		 
	 		document.getElementById(indexWithSubIndex+"dailyNO").focus();
	 		return false;
	 	}

		//alert("zzz"+document.getElementsByName('accepted')[indexWithSubIndex].value);

		if(document.getElementsByName('accepted')[indexWithSubIndex].value=="0" && document.getElementsByName('rejectionReason')[indexWithSubIndex].value=="-1")
		{
			alert("Please Select Rejection Reason");
			document.getElementsByName('rejectionReason')[indexWithSubIndex].focus();
	 		return false;
		}

		if(document.getElementsByName('accepted')[indexWithSubIndex].value=="0" &&  document.getElementsByName('rejectionReason')[indexWithSubIndex].value=="-2" && document.getElementById("rejj"+indexWithSubIndex).value=="")
	 	{
	 		alert("Please Enter Rejection Reason");
      		  		 
	 		document.getElementById("rejj"+indexWithSubIndex).focus();
	 		return false;
	 	}
	 	
    	 }
   
      }
	
	if(count==0)
   	{
   	alert("please select a Atleast One record");
   	return false;
   	}
	document.getElementsByName('showStatus')[0].value='0';
	
	//Validations for CR NO and Combo should be done here
	document.getElementsByName('hmode')[0].value="SAVE";
	document.forms[0].submit();
	 
   return true;
	
	
}

function setlabCode(obj)
{
	var code=obj.value;
	//alert(code);
	//(obj.value);
	document.getElementsByName("labCode")[0].value=code;
	 
	}


//AJax Functions for checking Duplicacy
function chkDailyLabNoDuplicacyThroughAjax(obj,event)
{
	//added by prashantMi to stop ajax call in case of already accepted rejection
	var acceptedToNotAccepted = document.getElementsByName("acceptedToNotAccepted");
	if(acceptedToNotAccepted[0].value=="2"){ return;}
	
	var labNoConfiguration=obj.value;
	//alert(document.getElementsByName("sampleAreaCode")[0].value);
	//var sampleAreaCode=document.getElementsByName("sampleAreaCode")[0].value;
	//alert(labNoConfiguration.length);
	if(labNoConfiguration.length<0)
		return true;
	
		var isDailyLabNoPresent = chkDailyLabNo(labNoConfiguration);
		
		isDailyLabNoPresent=isDailyLabNoPresent=="true"?true:false;
		//alert(isDailyLabNoPresent);
		if(isDailyLabNoPresent)
		{
			alert("Daily Lab Number already present");
			obj.value="";
			obj.focus();
			return false;
		}
	return true;
}

function chkDailyLabNo(labNoConfiguration)
{
	var flg = false;
	var isSampleNoPresent = false;
	var _mode = "AJX_DUPLICACY_DAILYLABNO";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/onlinePatientAcceptance.cnt?hmode="+_mode+"&strDailyLabNo="+labNoConfiguration, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			isSampleNoPresent = data;
			flg = true;
		},
        error: function(error)
        {
            //if(typeof objKitchenList == 'undefined' || objKitchenList==null || objKitchenList=="" || objKitchenList.length==0)
            	//alert("No Kitchen Found");
            //alert(error+"Error while populating Meal Time Information");
            isSampleNoPresent = false;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return isSampleNoPresent;
}

function setCursor()
           {
	 
	if(document.getElementsByName("tempPatCRNo")[0]){
        document.getElementsByName("tempPatCRNo")[0].focus()
	}
  
	 	}

//End AjaxFunctions

function setHiddenValue(obj){
	//alert("hit"+obj);
	document.getElementsByName("selectedmachineCode")[0].value = obj;
}

</script>

<!-- Added by PrashantMi -->
<script>
$(document).ready(function (){
	
	/* var acceptedToNotAccepted= $('[name="acceptedToNotAccepted"]'); */
	var acceptedToNotAccepted = document.getElementsByName("acceptedToNotAccepted");
	var acceptedSellect = document.getElementsByName("accepted");
	
	//alert(acceptedToNotAccepted.length);
	if(acceptedToNotAccepted){
		$('input:radio[name="acceptedToNotAccepted"]').change(
			    function(){
			    	//alert("change");
			    	if(this.checked){
			    		acceptedToNotAccepted[0].value=this.value;
			    		$('#gob').click();
			    	}
			    });
		}
	
	if (acceptedSellect){
		//alert("hi1");
		/* sets all acceptance to no by deafult */
		/* if(acceptedToNotAccepted[0].value=="2"){
			//alert("hi2");
			for(var i=0; i<acceptedSellect.length; i++){
				acceptedSellect[i].selectedIndex = "1";
				acceptedSellect[i].onchange();
				acceptedSellect[i].options[0]=null;
				//acceptedSellect[i].disabled = true;
			   }
			} */
	   }

	
});
</script>
<!-- Added by PrashantMi -->
<style>
table.dataTable td:nth-child(2),td:nth-child(3),td:nth-child(4)
,td:nth-child(4),td:nth-child(6),td:nth-child(7)
,td:nth-child(9),td:nth-child(11) {
  white-space: nowrap;
}

table.dataTable th {
  white-space: nowrap;
}

table.dataTable td:nth-child(10) {
   word-break: break-word; 
   width: 300px;
  /* white-space: pre */
} 


</style>

<style>
.scroll_div {
	width: 800px;
	height: 50px;
	overflow-y: hidden;
	overflow-x: scroll;
	text-align: justify;
	margin: 0;
	padding: 10px 10px 10px 10px;
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
.textBoxCss {
	background: #ccc;
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
/* Tooltip container */
.tooltip {
  position: relative;
  display: inline-block;
  border-bottom: 1px dotted black; /* If you want dots under the hoverable text */
}

/* Tooltip text */
.tooltip .tooltiptext {
  visibility: hidden;
  width: 120px;
  background-color: black;
  color: #fff;
  text-align: center;
  padding: 5px 0;
  border-radius: 6px;
 
  /* Position the tooltip text - see examples below! */
  position: absolute;
  z-index: 1;
}

/* Show the tooltip text when you mouse over the tooltip container */
.tooltip:hover .tooltiptext {
  visibility: visible;
}
</style>
<%
	String strdivage="\"\"";
String strdivdob="\"\"";
%>



<body onload="setCursor()">
	<form method="POST">
		<html:form action="/onlinePatientAcceptance">

			<html:hidden name="OnlinePatientAcceptanceFB" property="hmode" />
				<html:hidden name="OnlinePatientAcceptanceFB" property="entryDate" />
			
			<html:hidden name="OnlinePatientAcceptanceFB"
				property="isPatDetailPage" />
			<html:hidden name="OnlinePatientAcceptanceFB"
				property="selectedCheckbox" />
			<html:hidden name="OnlinePatientAcceptanceFB" property="showStatus" />
			<html:hidden name="OnlinePatientAcceptanceFB" property="currentPage" />
			<html:hidden name="OnlinePatientAcceptanceFB" property="patCRNo" />
			<html:hidden name="OnlinePatientAcceptanceFB" property="sysDate" />
			
			<html:hidden name="OnlinePatientAcceptanceFB" property="tmpTestCode" />
			<html:hidden name="OnlinePatientAcceptanceFB" property="config" />
                <html:hidden name="OnlinePatientAcceptanceFB" property="sampleAreaName" />
               <html:hidden name="OnlinePatientAcceptanceFB" property="isSampleAreaSelected" />
               <!-- updated by krishnan nema on 08/10/2018 -->
               <html:hidden name="OnlinePatientAcceptanceFB" property="patientType" />
               <html:hidden name="OnlinePatientAcceptanceFB" property="selectedmachineCode" />
               
               <!-- Added by Prashant -->
               <html:hidden name="OnlinePatientAcceptanceFB" property="acceptedToNotAccepted" />
              
              <% boolean UserListDisplayed= false;%> <!-- To display userlist only one time -->
               
               <%int accCount=0; %>
               
			<%!boolean readOnly;%>
			<%
				this.readOnly=false;
			%>

			<logic:equal name="OnlinePatientAcceptanceFB" property="hmode"
				value="VIEW">
				<%
					this.readOnly=true;
				%>
			</logic:equal>
			<his:TitleTag name="Online Patient Acceptance">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag> 
			<his:ContentTag>

				<%
					String fromDateLabel="" ;
				              String toDateLabel="" ;
				              String fromDateControl="" ;
				              String toDateControl="" ;
				%>

				<bean:define name="OnlinePatientAcceptanceFB" property="fromDate"
					id="frDate" type="java.lang.String" />
				<bean:define name="OnlinePatientAcceptanceFB" property="toDate"
					id="tDate" type="java.lang.String" />
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

			<%-- 	<his:SubTitleTag name="Acceptance Details"></
  			</his:SubTitleTag> --%>
				<logic:equal name="OnlinePatientAcceptanceFB" property="showStatus"
					value="0">
					
					<!-- added by chandan -->
					<logic:present name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO %>">
					<table width="100%" >
				 		
				 			<tr>
					 			<td class="tdfont" width="25%">
					 				<div align="right">
					 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
													Patient Acceptance Area &nbsp;
													 </font>
					 					
					 				</div>
					 			</td>
					 			<td class="tdfont" width="25%">
					 				<logic:notEqual name="OnlinePatientAcceptanceFB" property="isSampleAreaSelected" value="1">
						 				  <span class="custom-dropdown small">
						 				<html:select    name="OnlinePatientAcceptanceFB" property="sampleAreaCode" tabindex="1"  onchange="showSearchDiv(this)">					
											<bean:define id="patSampleCollection" type="java.util.List" name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>"></bean:define>
												<%if(patSampleCollection.size()>1){ %>
													<html:option value="-1">Select Value</html:option>
												<%} %>
 											 
											<html:options collection="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>" property="value" labelProperty="label"  />
										</html:select> 
										</span>
									</logic:notEqual>
									 <logic:equal name="OnlinePatientAcceptanceFB" property="isSampleAreaSelected" value="1">
									 	<div align="left">
					 					  <bean:write name="OnlinePatientAcceptanceFB" property="sampleAreaName"/>
					 					  <html:hidden name="OnlinePatientAcceptanceFB" property="sampleAreaCode"/>
					 					</div>
									 </logic:equal>
					 			</td>
					 		<!-- Added by prashant mishra -->
					 		<logic:equal name="OnlinePatientAcceptanceFB" property="isSampleAreaSelected" value="1">
					 		
					 			<td class="tdfont" width="25%">
					 			<div align="right">
			              		 <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font> 
						    	 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						    	 List All&nbsp;
						   		</font>
				     			</div>
					 			</td>
					 			
					 			<td class="tdfont" width="25%">
					 			 <html:radio name="OnlinePatientAcceptanceFB" property="acceptedToNotAccepted" value="1" />
			      				<!--  <input type="radio" name="acceptance" tabindex="1" value="1" checked="checked"> -->
			      				 Yet To Accept
			      				 <html:radio name="OnlinePatientAcceptanceFB" property="acceptedToNotAccepted" value="2" />
			      				<!--  <input type="radio" name="acceptance" tabindex="1" value="2"> -->
			      				 Accepted
			      				</td>
			      				</logic:equal>
			      				<logic:notEqual name="OnlinePatientAcceptanceFB" property="isSampleAreaSelected" value="1">
			      				<td class="tdfont" width="25%"></td>
			      				<td class="tdfont" width="25%"></td>
			      				</logic:notEqual>
			      				<!-- End by prashant mishra -->
				 			 </tr>	
				 			 </table>
				 			 
					
					<!-- end chandan -->
					<logic:equal name="OnlinePatientAcceptanceFB" property="isSampleAreaSelected" value="1">
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfont">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="CrNo" />&nbsp;
									</font>
								</div>
							</td>
							<%
								UserVO uservo=ControllerUTIL.getUserVO(request);
								      Date todayDateobj = new Date();
										SimpleDateFormat dateob = new SimpleDateFormat("yy");
										String strDate= dateob.format(todayDateobj);
								      String hospitalCode=uservo.getHospitalCode();
								      String val=uservo.getHospitalCode()+strDate;
							%>
							<td width="25%" class="tdfonthead">
								<div align="left">
									<input type="text" id="textBoxCss" name="tempPatCRNo"
										value="<%=val%>" maxlength="15" size="20"
										onkeypress="return validateNumeric(event,this)" tabindex="1">
								</div>
							</td>
							<td width="25%" class="tdfont"></td>
							<td width="25%" class="tdfonthead"></td>
						</tr>
						<tr>
							<td width="25%" class="tdfont">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="LabType" />&nbsp;
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead"><logic:present
									name="<%=InvestigationConfig.LAB_COMBO%>">
									<div align="left">
										<span class="custom-dropdown small"> <html:select
												name="OnlinePatientAcceptanceFB" property="labCode"
												tabindex="1">
												<html:option value="%">All</html:option>
												<html:options
													collection="<%=InvestigationConfig.LAB_COMBO%>"
													property="value" labelProperty="label" />
											</html:select>
										</span>
									</div>
								</logic:present></td>

							<td width="25%" class="tdfont">
							<!-- added by krishnan nema on 08/10/2018 -->
							<div align="right">
									<font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="modalityMachine" />&nbsp;
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
							<!-- Added by krishnna nema on 08/10/2018 -->
						
									<div align="left">
										<span class="custom-dropdown small"> <html:select
												name="OnlinePatientAcceptanceFB" property="machineCode"
												tabindex="1" onchange = "setHiddenValue(this.value)">
												<html:option value="-1">No Machine</html:option>
													<logic:present
									name="<%=InvestigationConfig.LIST_LOCATION_BASED_MACHINE__COMBO%>">
												<html:options
													collection="<%=InvestigationConfig.LIST_LOCATION_BASED_MACHINE__COMBO%>"
													property="value" labelProperty="label" />
													</logic:present>
											</html:select>
										</span>
									</div>
							
							</td>
						</tr>
						<tr>
							<td class="tdfont" width="25%">
								<div id='divfromDate' style='<%=fromDateLabel%>' align="right">
									<font color="#FF0000" size="1"
										face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="fromDate" />
									</font>
								</div>

							</td>
							<td class="tdfonthead" width="25%">
								<div id='divfromDateControl' style='<%=fromDateControl%>'
									align="left">
									<his:date name='fromDate' dateFormate="%d-%b-%Y"
										value="<%=frDate%>" />
								</div>

							</td>

							<td class="tdfont" width="25%">
								<div id='divfromDate' style='<%=toDateLabel%>' align="right">
									<font color="#FF0000" size="1"
										face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="toDate" />
									</font>
								</div>
							</td>
							<td class="tdfonthead" width="25%">
								<div id='divfromDateControl' style='<%=toDateControl%>'
									align="left">
									<his:date name='toDate' dateFormate="%d-%b-%Y"
										value="<%=tDate%>" />
								</div>

							</td>

						</tr>
						<tr>
							<td class="tdfont" align="center" colspan="2" width="25%">
								<div align="right">
									<img class="button"
										src='<his:path src="/hisglobal/images/GO.png"/>' id="gob"
										style="cursor: pointer"
										onkeypress="if(event.keyCode==13) onClickGO('<%=hospitalCode%>')"
										onclick="onClickGO('<%=hospitalCode%>')" tabindex="1">
								</div>
							</td>
						</tr>
					</table>
				</logic:equal>
				</logic:present>
				<logic:present name="<%=InvestigationConfig.LIST_EPISODE_VO%>">
					<%
						boolean flag=false;
					%>
					<%-- <%
						//Pagination Logic
								 PaginationFB fbPage= new PaginationFB();
								pageContext.setAttribute("fbPagination",fbPage);
								fbPage.setCurrentPage(((OnlinePatientAcceptanceFB)request.getAttribute("OnlinePatientAcceptanceFB")).getCurrentPage());
								fbPage.setObjArrName(InvestigationConfig.LIST_EPISODE_VO);
								fbPage.setAppendInTitle("List ");
								int maxRecord=30;
								fbPage.setMaxRecords(maxRecord); 
					%> --%>
					 
						<%-- <his:PaginationTag name="fbPagination"></his:PaginationTag> --%>

						<his:SubTitleTag name="Investigation Raised Details">
  			</his:SubTitleTag>

						<logic:present name="<%=InvestigationConfig.LIST_EPISODE_VO%>">
						<%
							flag=true;
						%>
						</logic:present>
						
						<logic:empty name="<%=InvestigationConfig.LIST_EPISODE_VO%>">

							<center>
								<font color="red" size="4"> No Requistion Found For
									<bean:message key="patAccept"/></font>
							</center>
						</logic:empty>
						
											
						<logic:notEmpty name="<%=InvestigationConfig.LIST_EPISODE_VO%>">
						     <!-- Added by prashantMi -->
							<table id="table2" class="display responsive" >
								<thead>
								<tr>

									<th  align="left">
									<b><input type="checkbox" onclick="selectalll(this)"></b>
									</th>
									<th  align="left">
									<b>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="crNO" />
										</font>
									</b>
									</th>
									<th  align="left"><b> <font color="#000000"
											size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="labName" />
										</font></b>
										</th>
									<th align="left"><b> <font color="#000000"
											size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="reqDate" />
										</font></b>
										</th>
									<th  align="left"><b> <font color="#000000"
											size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="patName" />
										</font></b>
									</th>
									<th  align="left"><b> <font color="#000000"
											size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="age" />
										</font></b>
									</th>
									<th  align="left"><b><font color="#000000"
											size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="patStatus" />
										</font></b>
									</th>
										
									<th  align="left"><b><font color="#000000"
											size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="departmentunit" />
										</font></b>
									</th>
									<th  align="left"><b> <font color="#000000"
											size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="visitDate" />
										</font></b>
									</th>
									
										
								  <th  align="left" ><b> <font color="#000000"
											size="2" face="Verdana, Arial, Helvetica, sans-serif" >
												Test Names
										</font></b>
									</th>
									
								 <th   align="left"><b> <font color="#000000"
								   size="2" face="Verdana, Arial, Helvetica, sans-serif">
										Appointment Type/Time
								</font></b>
								</th>
								
								</tr>
								</thead>
								<tbody>
								<%
									List<OnlinePatientAcceptanceVO> lstPatVO=(List<OnlinePatientAcceptanceVO>)session.getAttribute(InvestigationConfig.LIST_EPISODE_VO);
									 		
											 int i,size=0;
									 		 
									 		if(lstPatVO!=null && lstPatVO.size()>0 )
									 			size=lstPatVO.size();
											//int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
											//int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
									 		
											for(int j=0;j<lstPatVO.size();j++)
											{
												//int i=j-startIndex;
												 
											/* if(j<size)
															{ */
												OnlinePatientAcceptanceVO voPat=lstPatVO.get(j);
												 
											String chkVal=voPat.getPatPuk()+"#"+voPat.getPatReqNo();
								%>
					
								<tr>

									<td  align="left"><font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <input
											type="checkbox" name="chkSamplePatient" value='<%=chkVal%>'
											onclick="ValidateSameCrNo(this)">
									</font></td>
									<td  align="left">
										<div align="left">
											<font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatPuk()%></font>
										</div>
									</td>

								
									<td  align="left">


										<div align="left">
											<font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatLabName()%></font>
										</div>
									</td>
									<td  align="left">

										<div align="left">
											<font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatReqDate()%></font>
										</div>

									</td>
									<td  align="left">


										<div align="left">
											<font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatName()%></font>
										</div>
									</td>
									<td  align="left">

										<div align="left">
											<font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatAge()%></font>
										</div>

									</td>
									<td  align="left">

										<div align="left">
											<font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatStatus()%></font>
										</div>

									</td>
									<td  align="left">

										<div align="left">
											<font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatUnitName()%></font>
										</div>
									</td>
									<td  align="left">

										<div align="left">
											<font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatVisitDat()%></font>
										</div>
									</td>

									<td  align="left">

										<div align="left" >
											<font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif" > <%=voPat.getTestName().replaceAll("[\\{\\}\"]","")%></font>
										</div>
									</td>

                                        <td  align="left">
                                               <%
                                               String apttime="-";
                                               if(voPat.getApttype()!=null && !voPat.getApttype().equals("null") && !voPat.getApttype().equals("") && voPat.getApttype().equals("4")){ 
                                               apttime="Investigation Lab Wise/"+voPat.getApttime()+"";
                                               } %>
                                               
										<div align="left">
											<font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <%=apttime%></font>
										</div>
									</td>
									
									
								</tr>
								
								<%
									  }
								%>
							</tbody>
							</table>
							
						</logic:notEmpty>
					</logic:present>
					 </logic:equal>

					<logic:present name="<%=InvestigationConfig.LIST_PATIENT_VO%>">
					
						<%
						String crno="";
						String crno11="";
						String crnounbilled="";
                         String crnounbilledd="";
						boolean flagg=false;
						boolean flaggunbilled=false;
							List<OnlinePatientAcceptanceVO> onlinePatientvvv= (List<OnlinePatientAcceptanceVO>)session.getAttribute(InvestigationConfig.LIST_PATIENT_VO);
							for(int l=0;l<onlinePatientvvv.size();l++)
							{
						OnlinePatientAcceptanceVO onlinePatientvo=onlinePatientvvv.get(l);
						
						
							if(onlinePatientvo.getPatStatus()!=null && (crno.equals("") || !crno.contains(onlinePatientvo.getPatPuk())) && (crno11.equals("") || !crno11.contains(onlinePatientvo.getPatPuk())))
							{
								
								if(flagg==false)
								{
									crno11+=onlinePatientvo.getPatPuk()+"#";
									
								}
								
								crno=onlinePatientvo.getPatPuk()+"#";
								
								flagg=true;
								flaggunbilled=true;
						%>
				
						<%
							String staffImage = (String) session.getAttribute(InvestigationConfig.STAFF_DEPENDENT_IMAGE);
							//System.out.println("staffImage jsp : "+staffImage);
				
						%>
				<div style="border-color: 	#2F4F4F;">
	<%-- <his:SubTitleTag name="Patient Details" >
	<div >	
		<img class="button" title="Show Patient Details"  src='<his:path src="/hisglobal/images/plusinv.png"/>' id="<%=l %>#showpatdetails"      tabindex="1" onclick ="showpatdetails(this)" > 
		<img class="button" title="Hide Patient Details"  src='<his:path src="/hisglobal/images/Minus.png"/>' id="<%=l %>#hidepatdetails"      tabindex="1" onclick ="hidepatdetails(this)" >
		</div>								
  			</his:SubTitleTag> --%>
  			
  			 <div style="font-size: 18px;width:100%;background-color:#006A9F;color: white;height: 20;margin-bottom: 20px" align="left">
  			<b>Patient Details</b>
  			</div>
						<div id="ipddDiv">
							<table width="100%">
								<tr>
									<td class="tdfont" width="25%">
										<div align="right"><bean:message key="crNO"/>&nbsp;</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatPuk()%>
										</div>
									</td>
									<td class="tdfont" width="25%">
										<div align="right"><bean:message key="patientName"/>&nbsp;</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatName()%>
										</div>
									</td>
								</tr>
								<tr>
									<td class="tdfont" width="25%">
										<div align="right"><bean:message key="age/gender"/>&nbsp;</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatAge()+"/"+onlinePatientvo.getPatGender()%>
										</div>
									</td>
									<td class="tdfont" width="25%">
										<div align="right"><bean:message key="patStatus"/>&nbsp;</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatStatus()%>
										</div>
									</td>
								</tr>
								
								<%if(staffImage != "")
								{
								%>
								
								 <tr>
								 		<td class="tdfont" width="25%">
							 				<div align="right">
							 					Staff Dependent's Photo
							 				</div>
							 			</td>
							 			<td class="tdfonthead" width="25%">
							 				<div align="left">
							 				<img id="imageid"  src="data:image/png;base64,<%=staffImage%>"  style="width:100px; height:100px;" border="1" >
							 				</div>
							 			</td>
								</tr>
								 <%}  %>
								 	</table>
								 	
								

						</div>
                             <div id="<%=l%>show" style="display: none">
						<his:SubTitleTag name="Patient Personal Details"></
  			</his:SubTitleTag>

						<table width="100%">

							<tr>
								<td class="tdfont" width="25%">
									<div align="right">  <bean:message key="mobile"/> </div>
								</td>
								<td class="tdfonthead" width="25%">
									<div align="left">
										<input type="text" class="textBoxCss" name="mobileNo"
											value="<%=onlinePatientvo.getMobileNo()%>" maxlength="10"
											size="25" onkeypress="return validateNumeric(event,this)"
											tabindex="1" />
									</div>
								</td>
								<td class="tdfont" width="15%">
									<div align="right">

										<label for="field"> <bean:message key="mail"/> </label>
									</div>
								</td>

								<td class="tdfonthead" width="35%">

									<div id="myform" align="left">
										<input type="text" class="textBoxCss" id="field"
											value="<%=onlinePatientvo.getEmailId()%>" maxlength="50"
											size="25" onkeypress="onKeyP()" tabindex="1" name="emailId" />
			
									</div>
								</td>
								 
								 
							</tr>
						</table>

						<%
							if(onlinePatientvo.getPatStatus().equals("IPD"))
							{
						%>
						<his:SubTitleTag name="Ipd Details"></
  			</his:SubTitleTag>
						<div id="ipddDiv">
							<table width="100%">
								<tr>
									<td class="tdfont" width="25%">
										<div align="right"><bean:message key="admitdept"/></div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatUnitName()%>
										</div>
									</td>
									<td class="tdfont" width="25%">
										<div align="right"><bean:message key="wardName"/></div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatWardName()%>
										</div>
									</td>
								</tr>
								<tr>
									<td class="tdfont" width="25%">
										<div align="right"><bean:message key="roomName"/></div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatRoomName()%>
										</div>
									</td>
									<td class="tdfont" width="25%">
										<div align="right"><bean:message key="bedName"/></div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatBedName()%>
										</div>
									</td>
								</tr>
								<tr>
									<td class="tdfont" width="25%">
										<div align="right"><bean:message key="consultant"/></div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatOrderByDoc()%>
										</div>
									</td>
									<td class="tdfont" width="25%"></td>
									<td class="tdfonthead" width="25%"></td>
									<%-- <td class="tdfonthead" width="25%">
			 				<div align="right">
			 					Diagnosis
			 				</div>
			 			</td>
			 			<td class="tdfont" width="25%">
			 				<div align="left">
			 					<%=patDtlVO.getDiagnosis() %>
			 				</div>
			 			</td> --%>
								</tr>

							</table>

						</div>
						<%
							}
						%>
						
						
						    <div class="subDivStyle">
                                <his:SubTitleTag name="Requisition Forms"> 
											<img class="button" title="Show Patinet Details"  src='<his:path src="/hisglobal/images/plusinv.png"/>' id="reqformshow"      tabindex="1" onclick ="f1()" > 
										<img class="button" title="Hide Patient Details"  src='<his:path src="/hisglobal/images/Minus.png"/>' id="reqformhide"      tabindex="1" onclick ="f2()" >
											 </his:SubTitleTag>                 
                 </div>
                           <div class="subDivStyle" id="reqformss" style="display: none">
                               <table width="100%" border="1" id="tbll">
                               <tr>
                       <td style="font-weight: bold;color: brown;">TEST NAME</td>
                      <td style="font-weight: bold;color: brown;">REQUISITION FORMS</td>
                      </tr>
                               </table>                 
                 </div>
                 
						<%
							if(onlinePatientvo.getPatStatus().equals("OPD"))
							{
						%>
						<his:SubTitleTag name="Opd Details"></
  			</his:SubTitleTag>
						<div id="opdEmerencyDIV">
							<table width="100%">
								<tr>
									<td class="tdfont" width="25%">
										<div align="right">Department/Unit Name</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatUnitName()%>
										</div>
									</td>
									<td class="tdfont" width="25%">
										<div align="right"><bean:message key="age/gender"/>&nbsp;</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatAge()+"/"+onlinePatientvo.getPatGender()%>
										</div>
									</td>
								</tr>
								<tr>
									<td class="tdfont" width="25%">
										<div align="right"><bean:message key="visitDate"/>&nbsp;</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatVisitDat()%>
										</div>
									</td>
									<td class="tdfont" width="25%">
										<div align="right"> <bean:message key="patCat"/> </div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=(onlinePatientvo.getPatCategory()==null?"":onlinePatientvo.getPatCategory())%>
										</div>
									</td>
								</tr>
								<tr>
									<td class="tdfont" width="25%">
										<div align="right"><bean:message key="consultant"/></div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=(onlinePatientvo.getPatOrderByDoc()==null?"":onlinePatientvo.getPatOrderByDoc())%>
										</div>
									</td>
									<td class="tdfont" width="25%"></td>
									<td class="tdfonthead" width="25%"></td>
									<%-- <td class="tdfonthead" width="25%">
			 				<div align="right">
			 					Diagnosis
			 				</div>
			 			</td>
			 			<td class="tdfont" width="25%">
			 				<div align="left">
			 					<%=patDtlVO.getDiagnosis() %>
			 				</div>
			 			</td> --%>
								</tr>

							</table>
						</div>
						
						<%
							}
						%>
						</div>
						<%
							}
							
						%>


						
						
						<%if (UserListDisplayed==false){ %>  <!-- To Display UserList Only One Time -->
						
						<!-- PrashantMi to give permission to user for result entry starts-->
						<table>
							<tr>
								<div
									style="font-size: 18px; width: 100%; background-color: #006A9F; color: white; height: 20; margin-bottom: 1px"
									align="left">
									<b></b>
								</div>
							</tr>
						</table>
						
						<table>
							<tr>

								<td class="tdfont">Set User For Result Entry</td>

								<td class="tdfonthead">
								
								<div class="tooltip">
								
									<%
									Map<String, String> userList = new HashMap<String, String>();
									userList = (HashMap<String, String>) session.getAttribute(InvestigationConfig.LIST_USERLIST);
									%>

									<html:select name="OnlinePatientAcceptanceFB"
										property="userHasPermission" style="width:160;">

										<html:option value="0">All Users</html:option>
                                                  <%UserVO userVO=null;
                               				userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO); %>
                                                  <html:option value="<%=userVO.getUserId()%>"><%=userVO.getUsrName()%></html:option>
                                                  
										<%
											for (Map.Entry<String, String> userListx : userList.entrySet()) {
										%>

										<html:option value="<%=userListx.getKey()%>"><%=userListx.getValue()%></html:option>

										<% } %>

									</html:select>
								
								<span class="tooltiptext">The user selected here can do the "Result Entry". Please select "All User" if you don't known what it is.</span>
								</div>
							</tr>
						</table>
						<%UserListDisplayed= true;} %>
<!-- PrashantMi to give permission to user for result entry ends-->


						<html:hidden name="OnlinePatientAcceptanceFB" property="sampleAreaCode"/>					<!--  Billed Details -->

<logic:notEmpty  name="<%=InvestigationConfig.LIST_REQUISITION_PATIENT_BILLED%>">   
						<logic:present
							name="<%=InvestigationConfig.LIST_REQUISITION_PATIENT_BILLED%>">
							
							<%
								List<OnlinePatientAcceptanceVO> listPatientCollectio= (List<OnlinePatientAcceptanceVO>)session.getAttribute(InvestigationConfig.LIST_REQUISITION_PATIENT_BILLED);
								 		int sizeSamp=listPatientCollectio.size();
								 		
								 		int x=0;
							 			if(x<sizeSamp && flagg==true)
								 		{
							%>
<html:hidden name="OnlinePatientAcceptanceFB" property="sampleAreaCode"/>
							<his:SubTitleTag name="Billed Details"></
  			</his:SubTitleTag>
							<table width="100%">
								<tr >
									<td class="tdfont" >
										<div align="left"><bean:message key="select"/></div>
									</td>


									<td class="tdfont">
										<div align="left"> <bean:message key="labName"/>&nbsp;</div>
									</td>
									<td class="tdfont">
										<div align="left"> <bean:message key="reqDate"/> </div>
									</td>

									<td class="tdfont">
										<div align="left"><bean:message key="TestName"/></div>
									</td>
									<!-- updated by krishnan nema on  -->
									<%-- <td class="tdfont">
										<div align="left"> <bean:message key="xrayAndLab"/> </div>
									</td> --%>
									<td class="tdfont">
										<div align="left"> <bean:message key="accessionNowise"/> </div>
									</td>

									<td class="tdfont">
										<div align="left"> <bean:message key="accepted"/> </div>
									</td>
									
									<!-- Added by Prashant -->
									<td class="tdfont">
									<div align="left">Accepted Date</div>
									
									<%if (accCount==0){ %>
									<% String straccept="onChangeTopAcceptanceDate(this,"+-1+",'"+2+"')"; %>
									<div align="left">
									<input type="text" id="-1acceptancedate" class="acceptancedateTC" onchange="<%=straccept%>" readonly>
									</div>
									</td>
										<%accCount++; } %>
									<td   id="showthi" style="display: none" class="tdfont">
										<div align="left"> <bean:message key="rejectionAction"/> </div>
									</td>

									<td id="showthis" style="display: none" class="tdfont">
										<div align="left"> <bean:message key="RejectionReason"/> </div>
									</td>

									<td class="tdfont">
										<div align="left"><bean:message key="priority"/></div>
									</td>
									
								</tr>
							<tr><td> </td></tr>
							<tr><td> </td></tr>
							<tr><td> </td></tr>
								<%
									}
								%>
								<%
								String crno1="";
									List<OnlinePatientAcceptanceVO> listPatientCollection= (List<OnlinePatientAcceptanceVO>)session.getAttribute(InvestigationConfig.LIST_REQUISITION_PATIENT_BILLED);
									 		int sizeSampleV=listPatientCollection.size();
									 		Map<String,String> checkforgrp=new HashMap<String,String>();
									 		
									 		for(int k=0;k<sizeSampleV;k++)
									 		{
									 			OnlinePatientAcceptanceVO voPatientCollection=listPatientCollection.get(k);
									 			String color="black";
									 			int priorityCode=1;
									 			
									 		 if(crno.contains(voPatientCollection.getPatPuk()) && flagg==true)
									 			{ 
									 			 
									 			if(checkforgrp.containsKey(voPatientCollection.getRequisitionNo()+voPatientCollection.getGroupCode()))
									 			{}
									 			else
									 			{
									 				checkforgrp.put(voPatientCollection.getRequisitionNo()+voPatientCollection.getGroupCode(), "1");
									 			
									 				}
									 			
									 			 
									 			
									 			if(voPatientCollection.getPriorityCode()!=null)   // Normal;Urgent;Confidential
									 			{
									 				priorityCode=Integer.parseInt(voPatientCollection.getPriorityCode());
									 				switch(priorityCode)
									 				{
										 				case 1: color="blue";  // Normal Priority;
										 				        break;
										 				case 2: color="red";  // Urgent Priority;
											        	break;
										 				case 3: color="brown";  // Confidential Priority;
											        	break;
											    default: color="blue";
											    		break;
									 				}
									 			
									 			
									 			
									 			}
									 			
									 			

									 			String chkSampleVal=voPatientCollection.getPatPuk()+"#"+voPatientCollection.getRequisitionNo()+"#"+voPatientCollection.getRequisitionDNo()+"#"+voPatientCollection.getLabCode()+"#"+voPatientCollection.getBillDetail()+"#"+voPatientCollection.getTestName()+"#"+voPatientCollection.getTestCode()+"#"+voPatientCollection.getLabNoConfiguration()+"#"+voPatientCollection.getLabName()+"#"+voPatientCollection.getIsrequisitionformneeded()+"#"+voPatientCollection.getGroupCode()+"#"+Integer.toString(k);
												System.out.println("chkSampleVal : "+chkSampleVal);
								%>




								<tr>
									<%String chkBox="onClickSavecheckedgrp(this,"+k+",'0')"; %>
									<td class="tdfont" align="left">
										<div align="left">
											<input type="checkbox" id="<%=k%>chkBox"
												name="chkSamplePatientOnSave" value='<%=chkSampleVal%>'
												onChange="<%=chkBox%>"/>
												<input type="hidden" id="<%=k%>chkBoxonload"
												name="chkSamplePatientOnSaveonload" value='<%=voPatientCollection.getCheckboxajaxvalues()%>'
												/>
										</div>
									</td>

									<td class="tdfonthead">
										<div align="left">
											<font color="<%=color%>"> <%=voPatientCollection.getLabName()%>
											</font>
										</div>
									</td>
									<td class="tdfonthead">
										<div align="left">
										<!-- Modified  by Prashant -->
										<p style="color:blue;"><%=voPatientCollection.getRequisitionDate()%></p>
										 <input type="hidden" id="<%=k%>requisitionDate" class="requisitionDateC" value="<%=voPatientCollection.getRequisitionDate()%>">
										</div>
									</td>

									<td class="tdfonthead">
										<div align="left">
											<font color="<%=color%>"> <%=voPatientCollection.getTestName()%>
											<% 
											 String inst=voPatientCollection.getInstructionPat();
											String collInstr=voPatientCollection.getInstructionColl();	
											inst=inst.replace("\r\n","<br>");
							 				collInstr=collInstr.replace("\r\n","<br>");
							 				
											/* String inst="d"; */
											%>
										<%
													 					if( (inst.equals("Patient#") && collInstr.equals("Technician#") ))
													 					{
													 				%>
											 
											<%  } else{%>
										<img  class="button" title="Show Instructions"  src='<his:path src="/hisglobal/images/add_remarks_sml.jpg"/>'    tabindex="1" onclick ="showInstructions5('<%=inst%>','<%=collInstr%>');">		
												
											<% }  %>
											
										<% if(!voPatientCollection.getIsrequisitionformneeded().equals("0")) {%>	
													 					<%-- &nbsp&nbsp<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick="ShowRequistionForm('<%=voPatientCollection.getTestCode()%>','<%=voPatientCollection.getTestName()%>','<%=voPatientCollection.getLabCode()%>','<%=voPatientCollection.getLabName()%>','<%=voPatientCollection.getRequisitionDNo()%>')"> --%>
													 					<%} %>	
											
											
											
											</font>
											
										</div>
										
																				
										<div id="blanket" style="display: none;"></div>
 		<div id="popUpDiv5"  style="display:none;" align="center">  
		 
		<his:TitleTag name="Instructions For Patients">
		<img src='/HISInvestigationG5/hisglobal/css/close.png'  onClick="closeInstructions();">
  		</his:TitleTag>
  			
  			
 		<table width="100%" id="allInstructions">
 	             
 		<tr>
 		
 		<!-- <td class="tdfonthead" width="20%">
		<div align="left"><b>Instructions for:</b></div>
		</td>
		
		<td class="tdfonthead" width="80%">
		<div align="center"><b>Instructions</b></div>
		</td> -->
						
		</tr>
		
 		
 		</table>
 		                             
<img src='/HISInvestigationG5/hisglobal/images/ok.gif'  onClick="closeInstructions();"> 		                        
				

		</div>
	
	
 	
									</td>

									<%
										String labNO=voPatientCollection.getLabNoConfiguration();
									
									String labNoConfiguration2=null;
									if(voPatientCollection.getAcceptedToNotAccepted().equals("2"))
									{
									  labNoConfiguration2=voPatientCollection.getLabNoConfiguration2();
									}else{ labNoConfiguration2=""; }
									
												 			
								if(labNO.equals(InvestigationConfig.MANUAL_LAB))
								 {
									%>
									<td class="tdfonthead">
										<div align="left">
											<font color="<%=color%>"> <input type="text"
												class="textBoxCss" id="<%=k%>dailyNO"
												name="labNoConfiguration" value="<%=labNoConfiguration2%>" maxlength="20" size="25"
												onkeypress="return validateAlphaNumericOnly(event,this)"
												onblur="chkDailyLabNoDuplicacyThroughAjax(this,event);"
												tabindex="1" />

											</font>
										</div>
									</td>
									<%
										}else{
									%>


									<td class="tdfonthead">
										<div align="left">
											<font color="<%=color%>"> <input type="hidden"
												class="textBoxCss" id="<%=k%>dailyNO"
												name="labNoConfiguration" value="<%=labNO%>" maxlength="20"
												size="25" onkeypress="return validateNumeric(event,this)"
												onblur="chkDailyLabNoDuplicacyThroughAjax(this,event);"
												tabindex="1" />


											</font>
										</div>
									</td>
									<%
										}
									%>

                                   <% 
									
									String classs="0";
									if(voPatientCollection.getGroupCode()!=null && !voPatientCollection.getGroupCode().equals("0")) 
									{
									classs=voPatientCollection.getRequisitionNo()+voPatientCollection.getGroupCode();
									%>
									
									<%}

									%>

									<td class="tdfonthead">
										<div align="left">
											<%
												String str="onChane(this,"+k+",'"+classs+"')";
											
											%>
											<span class="custom-dropdown small">
											 <select style="width:160;" name="accepted" tabindex="<%=Integer.toString(k)%>" class="<%=classs%>" onchange="<%=str%>">
                                               <option value="1">Yes</option>
                                               <option value="0">No</option>
                                            </select> 
											 <%-- <html:select 
													name="OnlinePatientAcceptanceFB" property="accepted"
													onchange="<%=str%>" style="width:160;" tabindex="<%=clasname %>">
													<html:option value="1"> <bean:message key="yes"/> </html:option>
													<html:option value="0"><bean:message key="no"/></html:option>
												</html:select> --%>
											</span>
										</div>
									</td>
									<!-- Added by Prashant -->
									<% String straccept="onChangeAcceptanceDate(this,"+k+",'"+0+"')"; %>
									<td class="tdfonthead">
									<div align="left">
									<input type="text" id="<%=k%>acceptancedate" class="acceptancedateC" onchange="<%=straccept%>" readonly>
									<input type="hidden"  id="<%=k%>acceptancedateHidden" value=<%=voPatientCollection.getAcceptanceDate()%>  >
									</div>
									</td>
									
									
									<td id="showthi_<%=k%>" style="display: none" class="tdfonthead">
										<div align="left">
											<span class="custom-dropdown small"> <html:select 
													name="OnlinePatientAcceptanceFB" property="rejectionAction"
													style="width:160;" tabindex="1">
													<html:option value="1" ><bean:message key="cancelled" /></html:option>
									<%-- <html:option value="2"> <bean:message key="rescheduled"/> </html:option> --%>
												</html:select>
											</span>
										</div>
									</td>


									<td id="showth_<%=k%>" style="display: none" class="tdfonthead">
										<logic:present
											name="<%=InvestigationConfig.TEST_REASON_COMBO%>">
											<div align="left">
											<%
												String strreason="reasonextrashw(this,"+k+")";
											%>
												<span class="custom-dropdown small"> <html:select onchange="<%=strreason%>"
														name="OnlinePatientAcceptanceFB"   
														property="rejectionReason" tabindex="1">
														<html:option value="-1">Select Value</html:option>
														<html:option value="-2">Other</html:option>
														<html:options
															collection="<%=InvestigationConfig.TEST_REASON_COMBO%>"
															property="value" labelProperty="label" />
													</html:select >
												</span>
											</div>
										</logic:present>
									
									<div id="rej<%=k%>" style="display:none " align="left" >	
										<input type="text"  id="rejj<%=k%>"   name="extrarejectionReason" />
									</div>
									
									</td>
									
										<%-- <td id="rej<%=k%>" style="display: none" class="tdfonthead">
							       <html:text name="OnlinePatientAcceptanceFB" property="extrarejectionReason" value="" ></html:text>
									
									</td> --%>
									
									<td class="tdfonthead">
										<div align="left">
											<font color="<%=color%>"> <%=voPatientCollection.getPriority()==null?"":voPatientCollection.getPriority()%>
											</font>
										</div>
									</td>
									

										
									
									
								</tr>
								<%
									}} flagg=false;
									 		
								%>
								<!-- unbilled -->
								<logic:notEmpty 	name="<%=InvestigationConfig.LIST_PAT_PATIENT_UNBILLED%>">
								<logic:present
									name="<%=InvestigationConfig.LIST_PAT_PATIENT_UNBILLED%>">

									<%
									String crnoo="";
									boolean flag=false;
										List<OnlinePatientAcceptanceVO> listPatientColle= (List<OnlinePatientAcceptanceVO>)session.getAttribute(InvestigationConfig.LIST_PAT_PATIENT_UNBILLED);
										 		int sizeS=listPatientColle.size();
										 		boolean isshw=true;
										 		
										 		for(int p=0;p<sizeS;p++)
										 		{
										 		
										 			OnlinePatientAcceptanceVO vp=listPatientColle.get(p);
										 			
										 			if(crno.contains(vp.getPatPuk()))
										 				flag=true;
										 			
										 			boolean fgg=false;
										 			if(crnounbilledd.contains(vp.getPatPuk()))
										 			{
										 				fgg=false;
										 				
										 			}
										 			else
										 				fgg=true;
										 			
										 			
										 		
										 		
										 		int z=0;
									 			if( (z<sizeS  && (crno.contains(vp.getPatPuk()) && fgg==true)))
									 			{
									 				crnounbilled=vp.getPatPuk()+"#";
									 				if(isshw)
									 				{
									%>

									<his:SubTitleTag name="Unbilled Details"></
  			</his:SubTitleTag>
									<table width="100%">
										<tr>
											<td class="tdfont">
												<div align="left"> <bean:message key="labName"/>&nbsp;</div>
											</td>
											<td class="tdfont">
												<div align="left"> <bean:message key="reqDate"/> </div>
											</td>
											<%-- <td class="tdfonthead">
												<div align="left"> <bean:message  key="reqDNo"/> </div>
											</td> --%>
											<td class="tdfont">
												<div align="left"><bean:message key="TestName"/>&nbsp;</div>
											</td>
											
											<td class="tdfont">
												<div align="left"><bean:message key="priority"/></div>
											</td>
										</tr>
										<%}
									 				isshw=false;
									 				
											}
									 			
										 		}
										%>
										<%
											List<OnlinePatientAcceptanceVO> listPateintCollection= (List<OnlinePatientAcceptanceVO>)session.getAttribute(InvestigationConfig.LIST_PAT_PATIENT_UNBILLED);
											 		int sizeSample=listPateintCollection.size();
											 		crnounbilledd="";
											 		for(int k=0;k<sizeSample;k++)
											 		{
											 			
											 			OnlinePatientAcceptanceVO voPatientCollection=listPateintCollection.get(k);
											 			boolean fg=false;
											 			if(crnounbilledd.contains(voPatientCollection.getPatPuk()))
											 			{
											 				fg=false;
											 				
											 			}
											 			else
											 				fg=true;
											 			String color="blue";
											 			if(crno.contains(voPatientCollection.getPatPuk()))
											 			{
											 				crnounbilledd+=voPatientCollection.getPatPuk()+"#";
											 			
											 			int priorityCode=1;
											 			if(voPatientCollection.getPriorityCode()!=null)   // Normal;Urgent;Confidential
											 			{
											 				priorityCode=Integer.parseInt(voPatientCollection.getPriorityCode());
											 				switch(priorityCode)
											 				{
												 				case 1: color="blue";  // Normal Priority;
												 				        break;
												 				case 2: color="red";  // Urgent Priority;
													        	break;
												 				case 3: color="brown";  // Confidential Priority;
													        	break;
													    default: color="blue";
													    		break;
											 				}
											 			}
											 			}
											 			//String chkSampleVal=voPatientCollection.getPatCRNo()+"#"+voPatientCollection.getRequisitionNo()+"#"+voPatientCollection.getSampleCode()+"#"+voSampleCollection.getRequisitionDNo();
									
											 			if(crno.contains(voPatientCollection.getPatPuk()) && flaggunbilled==true )
											 			{
											 			%>

										<tr>
											<td class="tdfonthead">
												<div align="left">
													<font color="<%=color%>"> <%=voPatientCollection.getLabName()%>
													</font>
												</div>
											</td>
											<td class="tdfonthead">
												<div align="left">
													<font color="<%=color%>"> <%=voPatientCollection.getRequisitionDate()%>
													</font>
												</div>
											</td>
										<%-- 	<td class="tdfont">
												<div align="left">
													<font color="<%=color%>"> <%=voPatientCollection.getRequisitionDNo()%>
													</font>
												</div>
											</td> --%>
											<td class="tdfonthead">
												<div align="left">
													<font color="<%=color%>"> <%=voPatientCollection.getTestName()%>
													</font>
												</div>
											</td>
											
											<td class="tdfonthead">
												<div align="left">
													<font color="<%=color%>"> <%=voPatientCollection.getPriority()==null?"":voPatientCollection.getPriority()%>
													</font>
												</div>
											</td>
											<td class="tdfonthead">
												<div align="left">
													<font color="<%=color%>">
													 <img  class="button" title="Show Instructions"  src='<his:path src="/hisglobal/images/add_remarks_sml.jpg"/>'       tabindex="1" onclick ="showInstructions5();">
													
													</font>
												</div>
											</td>
										</tr>
										<%}
											
											 			}flaggunbilled=false;
										%>
									</table>
								</logic:present>
</logic:notEmpty>

								
							</table>
							<!--  Un Billed Details -->
</logic:present>
</logic:notEmpty>
<%} %>

</div>

</logic:present>

							<his:ContentTag>
							<logic:empty 	name="<%=InvestigationConfig.LIST_REQUISITION_PATIENT_BILLED%>">
							
							<logic:notEmpty 	name="<%=InvestigationConfig.LIST_PAT_PATIENT_UNBILLED%>">
								<logic:present
									name="<%=InvestigationConfig.LIST_PAT_PATIENT_UNBILLED%>">

									<%
									String crnoo="";
									boolean flag=false;
										List<OnlinePatientAcceptanceVO> listPatientColle= (List<OnlinePatientAcceptanceVO>)session.getAttribute(InvestigationConfig.LIST_PAT_PATIENT_UNBILLED);
										 		int sizeS=listPatientColle.size();
										 		
										 		for(int p=0;p<sizeS;p++)
										 		{
										 			OnlinePatientAcceptanceVO vp=listPatientColle.get(p);
										 			
										 		
										 			
										 			
										 		
										 		
										 		int z=0;
									 			if(z<sizeS )
									 			{
									 				
									%>

									<his:SubTitleTag name="Unbilled Details"></
  			</his:SubTitleTag>
									<table width="100%">
										<tr>
											<td class="tdfont">
												<div align="left"> <bean:message key="labName"/>&nbsp;</div>
											</td>
											<td class="tdfont">
												<div align="left"> <bean:message key="reqDate"/> </div>
											</td>
											<%-- <td class="tdfonthead">
												<div align="left"> <bean:message  key="reqDNo"/> </div>
											</td> --%>
											<td class="tdfont">
												<div align="left"><bean:message key="TestName"/>&nbsp;</div>
											</td>
											
											<td class="tdfont">
												<div align="left"><bean:message key="priority"/></div>
											</td>
										</tr>
										<%
											}
									 			break;
										 		}
										%>
										<%
											List<OnlinePatientAcceptanceVO> listPateintCollection= (List<OnlinePatientAcceptanceVO>)session.getAttribute(InvestigationConfig.LIST_PAT_PATIENT_UNBILLED);
											 		int sizeSample=listPateintCollection.size();
											 		
											 		for(int k=0;k<sizeSample;k++)
											 		{
											 			
											 			OnlinePatientAcceptanceVO voPatientCollection=listPateintCollection.get(k);
											 			boolean fg=false;
											 			
											 			
											 			
											 				//crnounbilledd+=voPatientCollection.getPatPuk()+"#";
											 			String color="black";
											 			int priorityCode=1;
											 			if(voPatientCollection.getPriorityCode()!=null)   // Normal;Urgent;Confidential
											 			{
											 				priorityCode=Integer.parseInt(voPatientCollection.getPriorityCode());
											 				switch(priorityCode)
											 				{
												 				case 1: color="blue";  // Normal Priority;
												 				        break;
												 				case 2: color="red";  // Urgent Priority;
													        	break;
												 				case 3: color="brown";  // Confidential Priority;
													        	break;
													    default: color="blue";
													    		break;
											 				}
											 			}
											 			//String chkSampleVal=voPatientCollection.getPatCRNo()+"#"+voPatientCollection.getRequisitionNo()+"#"+voPatientCollection.getSampleCode()+"#"+voSampleCollection.getRequisitionDNo();
										%>

										<tr>
											<td class="tdfonthead">
												<div align="left">
													<font color="<%=color%>"> <%=voPatientCollection.getLabName()%>
													</font>
												</div>
											</td>
											<td class="tdfonthead">
												<div align="left">
													<font color="<%=color%>"> <%=voPatientCollection.getRequisitionDate()%>
													</font>
												</div>
											</td>
										<%-- 	<td class="tdfont">
												<div align="left">
													<font color="<%=color%>"> <%=voPatientCollection.getRequisitionDNo()%>
													</font>
												</div>
											</td> --%>
											<td class="tdfonthead">
												<div align="left">
													<font color="<%=color%>"> <%=voPatientCollection.getTestName()%>
													</font>
												</div>
											</td>
										
											<td class="tdfonthead">
												<div align="left">
													<font color="<%=color%>"> <%=voPatientCollection.getPriority()==null?"":voPatientCollection.getPriority()%>
													</font>
												</div>
											</td>
											<td class="tdfonthead">
												<div align="left">
													<font color="<%=color%>">
													 <img  class="button" title="Show Instructions"  src='<his:path src="/hisglobal/images/add_remarks_sml.jpg"/>'       tabindex="1" onclick ="showInstructions5();">
													
													</font>
												</div>
											</td>
										</tr>
										<%
											
											 			}
										%>
									</table>
								</logic:present>
</logic:notEmpty>
</logic:empty>

							</his:ContentTag>

						 
					</his:ContentTag>
			</div>
			<his:ButtonToolBarTag>
				 
				 <img class="button"
						src='<his:path src="/hisglobal/images/btn-next.png"/>'
						id="nextDiv" style="cursor: pointer; display: none" tabindex="1"
						onclick="displaySamplePatDetails();"> 
						
				<!-- <button type="button" style="position:fixed;top:18%; right:0; z-index:9999 cursor: pointer; display: none" 
					class="btn-custom btn_danger-custom" id="nextDiv" onclick="displaySamplePatDetails();"
					onkeypress="if(event.keyCode==13)  displaySamplePatDetails();">
					Next
				</button> -->

					<img class="button"
						src='<his:path src="/hisglobal/images/btn-sv.png"/>' id="saveDiv"
						style="cursor: pointer; display: none"
						onkeypress="if(event.keyCode==13) onSave();" tabindex="1"
						onclick="onSave();">
					
					<%-- <img class="button"
						src='<his:path src="/hisglobal/images/btn-mo.png"/>' id="saveDiv"
						style="cursor: pointer; display: none"
						onkeypress="if(event.keyCode==13) onSave();" tabindex="1"
						onclick="onSave();"> --%>
						
					<img class="button"
						src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="cancel"
						tabindex="1" style="cursor: pointer"
						onkeypress="if(event.keyCode==13) cancelFunc();" tabindex="1"
						onclick="cancelFunc();">
				

				 

			</his:ButtonToolBarTag>
			<logic:present name="<%=InvestigationConfig.LIST_EPISODE_VO%>">
				
			<his:SubTitleTag>
		
				<his:name>
					<bean:message key="legends" />
				</his:name>
				<table width="100%" cellspacing="0" cellpadding="0">
					<tr>
						<td width="70%"></td>
						<td width="30%">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">Show </font><img
									src='<his:path src="/hisglobal/images/arrow_down.gif"/>'
									tabindex="1" onclick="showLegends();"
									onkeypress="if(event.keyCode==13) showLegends();"> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">Hide </font><img
									src='<his:path src="/hisglobal/images/arrow_up.gif"/>'
									tabindex="1" onclick="showLegendsNone();"
									onkeypress="if(event.keyCode==13) showLegendsNone();">
							</div>
						</td>
					</tr>
				</table>
				
			</his:SubTitleTag>
			
			<div id="divLegends" style="display: none">
				<his:ContentTag>
					<table width="100%" colspacing="1" colpadding="0"
						style="clear: both; border-left: 1px solid #003366; border-top: 1px solid #003366">
						<tr>
							<td width="10%"><font color="blue" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
									<div align="left"> <bean:message key="blue"/> </div>
							</font></td>
							<td width="90%"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
									<div align="left"> <bean:message key="normal"/> </div>
							</font></td>
						</tr>

						<tr>
							<td width="10%"><font color="red" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
									<div align="left"> <bean:message key="red"/></div>
							</font></td>
							<td width="90%"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
									<div align="left"> <bean:message key="urgent"/></div>
							</font></td>
						</tr>
						<tr>
							<td width="10%"><font color="brown" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
									<div align="left"> <bean:message key="brown"/></div>
							</font></td>

							<td width="90%"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
									<div align="left"> <bean:message key="Confidential"/></div>
							</font></td>
						</tr>

					</table>
				</his:ContentTag>
				
			</div>
			</logic:present>
			<div align="left">
			<his:status /></div>
			<html:hidden name="OnlinePatientAcceptanceFB" property="labCode" />
			<html:hidden name="OnlinePatientAcceptanceFB" property="patType" />

		</html:form>
	</form>
	<script>
// just for the demos, avoids form submit

 
	 
jQuery.validator.setDefaults({
debug: true,
success: "valid"
});
$( "#field" ).validate({
rules: {
emailId: {
 
email: true
}
}
});
 
</script>
</body>