<!-- 
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: PUNEET SINGH KHURANA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : RESULT RE VALIDATION
 ## Purpose						        : Re Validate the result entries
 ## Date of Creation					: 25/05/2015
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
	

 -->

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
<%@page import="new_investigation.transactions.controller.fb.InvResultValidationFB"%>
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
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/cannedMacroValidation.js" />
<his:javascript src="/hisglobal/js/cannedMacroAutocomplete.js" />

<his:javascript src="/new_investigation/js/reportsValidation.js" />
<his:javascript src="/new_investigation/js/onlinePatientAcceptance.js" />
<his:javascript src="/new_investigation/js/jquery-1.11.1.min.js" />
<his:javascript src="/new_investigation/js/jquery.validate.email.js" />
<his:javascript src="/new_investigation/js/additional-methods.min.js" />

<%-- <his:javascript src="/new_investigation/js/ckeditor/ckeditor.js"/> --%>
<script type="text/javascript"
	src="/HISInvestigationG5/new_investigation/js/ckeditor/ckeditor.js" charset="utf-8"></script>
<his:javascript src="/new_investigation/js/wysiwyg.js"/>
<his:javascript src="/new_investigation/js/wysiwyg-settings.js" />
<his:javascript src="/hisglobal/js/jquery-1.7.2.js" />
<his:javascript src="/hisglobal/js/css-pop-inv.js" />




<%@include file="/hisglobal/invsnomed.html" %>


<%@page import="hisglobal.hisconfig.Config"%>

<%@page import="new_investigation.vo.template.ResultEntryVO"%>
<%@page import="new_investigation.vo.template.ResultEntryVOGroupByValidatedBy"%>



<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="new_investigation.transactions.controller.fb.InvResultValidationFB"%>

 
 
<his:javascript src="/hisglobal/js/jquery-ui.js" />
<his:javascript src="/hisglobal/js/sweet-alert.min.js" />
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

<%@page import="new_investigation.InvestigationConfig"%>

<!-- added by krishnan nema on 12/02/2019  -->
	<his:javascript src="/new_investigation/js/specialCharacterRemover.js" />
	
	<script src="scripts/js/jquery-3.3.1.js" type="text/javascript"></script>
<script src="scripts/js/jquery.dataTables.min.js" type="text/javascript"></script>
<link href="media/dataTables/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
<link href="media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
<link href="media/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />
<link href="media/dataTables/responsive.bootstrap4.min.css" rel="stylesheet" type="text/css" />
<link href="media/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="media/dataTables/css/dataTables.rowGroup.min.js" type="text/javascript"></script>
<link href="media/dataTables/css/rowGroup.dataTables.min.css" rel="stylesheet" type="text/css" />
<script src="media/bootstrap/js/date-dd-MMM-yyyy.js" type="text/javascript"></script>

<html>
<head>
<meta charset="utf-8">
</head>
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
	height:250px;
	border:5px solid #000;
	z-index: 9002;
}

#popUpDiv5 a {position:relative; top:1px; left:20px}

#blanketcanned {
   background-color:#111;
   opacity: 0.65;
   *background:none;
   position:absolute;
   z-index: 9001;
   top:2px;
   left:0px;
   width:100%;
}

#popUpDiv5canned {
	position:absolute;
	  
	background:#CCE6FF;
	width:400px;
	height:200px;
	border:5px solid #000;
	z-index: 9002;
}

#popUpDiv5canned a {position:relative; top:1px; left:20px}


</style>
<script>
$(document).ready( function () {
	 var table = $('#table2').DataTable({
		 "pageLength": 25,
		 "orderClasses": false,
		 "order": [[ 7, "asc" ]],
		 "columnDefs": [{
		    'targets': [0,10,11,12,13], /* column index */
		    'orderable': false, /* true or false */

		 },{ type: 'date-dd-mmm-yyyy', targets: 9 }],
		 responsive: true,
		 });
	
	 $('#table2 tbody').on('click', 'td.details-control', function () {
          var tr = $(this).closest('tr');
          var row = table.row(tr);

          if (row.child.isShown()) {
              // This row is already open - close it
              row.child.hide();
              tr.removeClass('shown');
          } else {
              // Open this row
              row.child(format(tr.data('child-value'))).show();
              tr.addClass('shown');
          }
      });


	 $('#btn-show-all-children').on('click', function(){
		 document.getElementById("btn-show-all-children").style.display="none";	
		 document.getElementById("btn-hide-all-children").style.display="block";	
	        // Enumerate all rows
	        table.rows().every(function(){
	            // If row has details collapsed
	             var tr = $(this.node());
	            if(!this.child.isShown()){
	                // Open this row
	                this.child(format(tr.data('child-value'))).show();
	                tr.addClass('shown');
	            }
	        });
	    });

	    // Handle click on "Collapse All" button
	    $('#btn-hide-all-children').on('click', function(){
	    	document.getElementById("btn-show-all-children").style.display="block";	
			 document.getElementById("btn-hide-all-children").style.display="none";
	        // Enumerate all rows
	        table.rows().every(function(){
	            // If row has details expanded
	            if(this.child.isShown()){
	                // Collapse row details
	                this.child.hide();
	                $(this.node()).removeClass('shown');
	            }
	        });
	    });
} );
</script>

<script>





function myuploadremoveFunction(obj)
{

var idd=obj.id;
idd=idd.split("@@")[2];
document.getElementById(idd).value="";
document.getElementById(obj.id).style.display = "none";
	}
	
var reqformtestnames="";
var reqformtestcodes="";


var fileByteArray = [];
function callfileupload(obj)
{
	var dd=obj.id;
	//alert(dd);
 var rdd="view@@remove@@"+dd;

	var viewid="view@@"+dd;
//alert(document.getElementById(viewid));
	if(document.getElementById(viewid)==null || document.getElementById(viewid)=="null")
		{

		 var x = document.getElementById(dd);
		 var file = x.files[0];
		 getBase64(file,obj);
		 document.getElementById(rdd).style.display = "";
		}
	else
		{
		 var r = confirm("File is already Uploaded. Do you want to replace ? ");
		  if (r == true) {

			  var x = document.getElementById(dd);
				 var file = x.files[0];
				 getBase64(file,obj);
				 document.getElementById(rdd).style.display = "";
		    //txt = "You pressed OK!";
		  }  else
		  {
				 document.getElementById(dd).value="";
				 document.getElementById(rdd).style.display = "none";
				  }

		}
	
	
	// uploadFile3(file);
}

function getBase64(file,obj) {

	var dd=obj.id;
	 var x = document.getElementById(dd);
	  file = x.files[0];
	 
	   var flagisvalid=ValidateSingleInput(obj);

		if(flagisvalid=="0")
			{
			return null;
			}
		
	  // alert(file.name);
	   var reqdno=obj.id;
	   var testpatacode=reqdno.split("#")[3];
		   
	   reqdno=reqdno.split("#")[0];
	   var filename=file.name;
	   var reader = new FileReader();
	   reader.readAsDataURL(file);
	   reader.onload = function () {
	     console.log(reader.result);
	     if(document.getElementsByName('fileuploaddatabase64')[0].value=="")
	     document.getElementsByName('fileuploaddatabase64')[0].value=reqdno+"@@"+filename+"@@"+reader.result+"@@"+testpatacode;
	     else
	    	 document.getElementsByName('fileuploaddatabase64')[0].value=document.getElementsByName('fileuploaddatabase64')[0].value+"#@#@"+reqdno+"@@"+filename+"@@"+reader.result+"@@"+testpatacode;
		     
	   };
	   reader.onerror = function (error) {
	     console.log('Error: ', error);
	   };
	}
	

var _validFileExtensions = [".pdf"];    
function ValidateSingleInput(oInput) {
	var flag="0";
    if (oInput.type == "file") {
        var sFileName = oInput.value;
         if (sFileName.length > 0) {
            var blnValid = false;
            for (var j = 0; j < _validFileExtensions.length; j++) {
                var sCurExtension = _validFileExtensions[j];
                if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
                    blnValid = true;
                  
                    flag="1";
                    break;
                }
            }
             
            if (!blnValid) {
                alert("Sorry, File is invalid, allowed extensions are: " + _validFileExtensions.join(", "));
                oInput.value = "";
                flag="0";
               // return false;
            }
        }
    }
    return flag;
}


function uploadFile3(file){
	  var files = file;
	  var reader = new FileReader();
	  reader.onload = processFile(files);
	  reader.readAsDataURL(files); 
	  var gg=reader;
	//  alert("7896rr"+reader);
	  document.getElementsByName('fileuploaddatabase64')[0].value=reader;
	}
	
function processFile(theFile){
	  return function(e) { 
		  if (e.target.readyState == FileReader.DONE) {
	    var theBytes = e.target.result; //.split('base64,')[1]; // use with uploadFile2
	    fileByteArray.push(theBytes);
	    //document.getElementById('file').innerText = '';
	    for (var i=0; i<fileByteArray.length; i++) {
	        //alert(fileByteArray[i]);
	    }
	  }
	}
}


function myuploadFunction(obj)
{

	//alert(obj.className);

	var classnamee=obj.className;
	var reqdno=classnamee.split("@@")[1];
	var testparaname=reqdno.split("#")[3];
	
     reqdno=reqdno.split("#")[0];
     var uploaddata=getuploadfiledata(reqdno,testparaname);
     //alert(uploaddata);

     if(uploaddata=="null" ||  uploaddata=="")
         {
            alert("File Not Found");
         }
     else
         {
      uploaddata="data:application/pdf;base64,"+uploaddata;
     var newwindow= window.open(uploaddata,"_blank", "menubar=no,location=no,resizable=no,scrollbars=no,status=yes,top=100,left=200,width=600,height=600");
     newwindow.document.title = 'Uploaded File';
      if (window.focus) {newwindow.focus()}
	 /*  $("#dialogg").dialog();
	  
      $("#frame").attr("src", uploaddata); */
         }
}


function getuploadfiledata(reqdno,testParaMeterCode)
{
	//alert("setInSession"+dependentelementcodevalue);

	  //vall = vall.replace(/#/g, '^');
	//alert("new"+selectedText+"#"+reqdno);
	
	//alert(tmpSampleCode+"  "+tmpLabCode+"     "+tmpTestCode+"    "+tmpLabTestCodeArray);
	var remarks = "";
	var flg = false;
	var labTestCodeArray = "";
	var _mode = "GETFILEUPLOADDATATESTWISE";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/invResultValidationTemplateTile.cnt?hmode="+_mode+"&requisitionDNo="+reqdno+"&testParaMeterCode="+testParaMeterCode, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			remarks = data;
	///alert(data);
			//labTestCodeArray = data;
			
		},
        error: function(error)
        {
        	
        }};
	var objDojoAjax = dojo.xhrPost(objXHR) ;
	//alert("new"+remarks);
	return remarks; 
	
}


var reqformtestnames="";
var reqformtestcodes="";





function templatecombocall(obj)
{
	
//alert("id"+obj.id);
//alert(obj.name);

var testcode=obj.name
testcode=testcode.split("#")[3];
//alert(testcode);
 testcode = testcode.substring(0, 5);
// alert(testcode);
/* var al=obj.name+"#order"; */
var skillsSelect = document.getElementById(obj.id);
var selectedText = skillsSelect.options[skillsSelect.selectedIndex].text;
//alert(selectedText);


//document.getElementById("961011000118092610000301#null#template#100101004").disabled = true;

    var val=checkIsparameterDependent(testcode);
  //  alert(val);
  //  alert(val.includes("Hyper Link"));
     if(val.includes("Hyper Link") && val.includes("Text Area") )
       {

      //   alert("insidee");
    	 var idd=obj.name;

    	 var idd1=idd.split("#")[0];
    	 var idd2=idd.split("#")[1];
    	 var idd3=idd.split("#")[2];
    	 var idd4=idd.split("#")[3];


    	 var textareaparacode=val.split("@@@")[0];
    	// alert("sss"+textareaparacode);
    	 textareaparacode=textareaparacode.split("###")[1];
    	 var hyperparacode=val.split("@@@")[1];
    	 hyperparacode=hyperparacode.split("###")[1];
    	 
         var hyperfinalhide="td"+"#"+idd1+"#"+idd2+"#"+idd3+"#"+testcode+hyperparacode;
         var hyperfinalhidee=idd1+"#"+idd2+"#"+idd3+"#"+testcode+hyperparacode;

         var textareafinalhide=idd1+"#"+idd2+"#"+idd3+"#"+testcode+textareaparacode;

      //   alert("hyperfinalhide"+hyperfinalhide);
          if(selectedText=="Positive")
        {   
           //   alert("selectedText"+selectedText);
              document.getElementById(hyperfinalhide).style.display = "";
                 
        // document.getElementById(textareafinalhide).disabled = true;
         //document.getElementById(textareafinalhide).value = "";

        }
          else
              {
              document.getElementById(hyperfinalhide).style.display = "none";
              document.getElementById(hyperfinalhidee).style.value = "";
              
              document.getElementsByName('hyperLinkTableSession')[0].value=""
            	  document.getElementsByName('selectValuemapping')[0].value="";
        	  
              //  document.getElementById(textareafinalhide).disabled = false;
              }
     
 	   }

    
}



function checkIsparameterDependent(dependentelementcodevalue)
{
	//alert("setInSession"+dependentelementcodevalue);

	  //vall = vall.replace(/#/g, '^');
	//alert("new"+labarray1);
	
	//alert(tmpSampleCode+"  "+tmpLabCode+"     "+tmpTestCode+"    "+tmpLabTestCodeArray);
	var remarks = "";
	var flg = false;
	var labTestCodeArray = "";
	var _mode = "CHECKCISPARAMETERDEPENDENT";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/invResultEntryTemplateTile.cnt?hmode="+_mode+"&dependentelementcodevalue="+dependentelementcodevalue, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			remarks = data;
	///alert(data);
			//labTestCodeArray = data;
			
		},
        error: function(error)
        {
        	
        }};
	var objDojoAjax = dojo.xhrPost(objXHR) ;
	//alert("new"+remarks);
	return remarks; 
	
}



function call()
{
           //alert("call");
	var cbs =document.getElementsByName('chkResultValidationPatient');

    for(var i=0; i < cbs.length; i++)
   {
           
               // alert("ins");
                var dd=document.getElementsByTagName("select");
                for(var j=0;j<dd.length;j++)
                    {
              	  var name=dd[j].name;
                 //   alert("name"+dd[j].onchange);
                    var onchhangee=dd[j].onchange;
                  // alert( "contain"+onchhangee.trim());

               //    name="#"+name;

               var el=dd[j];
               el.dispatchEvent(new Event('change'));
	              //     alert("name"+name.trim());
                  // $("#"+name).trigger("change");
                //   $('#'+name).trigger(onchhangee);
                  //  templatecombocall(dd[0]);
                    }
                     
                   }
       
      
   
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
     for(var a=0;a<document.getElementsByName("chkResultValidationPatient").length;a++)
 {
    	// alert(a);
    	 //alert(document.getElementsByName("chkResultValidationPatient")[a].value);
    	 //alert((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[6]);
    	 overalltest+=(document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[6]+";";
    	 
  }


     for(var a=0;a<document.getElementsByName("chkResultValidationPatient").length;a++)
     { 
                //alert(a+"len"+document.getElementsByName("chkResultValidationPatient").length);
    	 var valuess=(document.getElementsByName("chkResultValidationPatient")[a].value).split("#");

    	 var reqNo=valuess[1];
    	 var testCode=valuess[3];
    	 var testName=valuess[17];
    	 var labCode=valuess[5];
    	 var labName=valuess[18];
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


           if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[28]=="1")
               {
                var cell1 = row.insertCell(0);
           		var cell2 = row.insertCell(1);
           		cell1.innerHTML = (document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[17];

           		if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[28]=="1")
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


           if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[28]=="1")
               {
           var cell1 = row.insertCell(0);
           		var cell2 = row.insertCell(1);
           		cell1.innerHTML = (document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[17];

           		if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[28]=="1")
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
                 

                                  		           if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[28]=="1")
                                  		               {
                                  		           var cell1 = row.insertCell(0);
                                  		           		var cell2 = row.insertCell(1);
                                  		           		cell1.innerHTML = (document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[17];

                                  		           		if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[28]=="1")
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
                                        				
                                        				if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[28]=="1")
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


                    		           if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[28]=="1")
                    		               {
                    		           var cell1 = row.insertCell(0);
                    		           		var cell2 = row.insertCell(1);
                    		           		cell1.innerHTML = (document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[17];

                    		           		if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[28]=="1")
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
                                    	  row.id = testCode+"#"+reqNo;
                                    	  
                         		           row.name=testName;
                         		           row.value=labName;
                               				row.testt=testCode;
                         		           reqformtestnames+=testName;
                         		           reqformtestcodes+=testCode;


                         		           if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[28]=="1")
                         		               {
                         		           var cell1 = row.insertCell(0);
                         		           		var cell2 = row.insertCell(1);
                         		           		cell1.innerHTML = (document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[17];

                         		           		if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[28]=="1")
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
                                        				
                                        				if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[28]=="1")
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
     for(var a=0;a<document.getElementsByName("chkResultValidationPatient").length;a++)
 {
    	 //alert(document.getElementsByName("chkSamplePatient")[a].value);
    	 //alert((document.getElementsByName("chkSamplePatient")[a].value).split("#")[6]);
    	 overalltest+=(document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[6]+";";
    	 
  }


     for(var a=0;a<document.getElementsByName("chkResultValidationPatient").length;a++)
     { 
                
    	 var valuess=(document.getElementsByName("chkResultValidationPatient")[a].value).split("#");

    	 var testCode=valuess[3];
    	 var testName=valuess[17];
    	 var labCode=valuess[5];
    	 var labName=valuess[18];
    	 var dNo=valuess[2];
    	 var reqNo=valuess[1];
	 		 		//alert("testCodetestCode"+reqNo);
	 		 	//	alert("testNametestName"+testName);
	 		 	//	alert("labCodelabCode"+labCode);
     var table = document.getElementById("tbll");

                        // alert("testCodetestCode"+testCode);
                  	  var mappedcheckedtest=checkreqformTestType(testCode);
                      var matchtest=mappedcheckedtest.split("#")[2];
          
          if(checktest=='')
              {


        	  var rowCount = table.rows.length;
           	//alert("rowCount"+rowCount);
           var row = table.insertRow(rowCount);
           row.id=testCode+"#"+reqNo;;
           row.name=testName;
           reqformtestnames+=testName;
           reqformtestcodes+=testCode;
           if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[28]=="1")
               {
           var cell1 = row.insertCell(0);
           		var cell2 = row.insertCell(1);
           		cell1.innerHTML = (document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[17]; 
         //alert("xx"+(document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[28]);
           		if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[28]=="1")
           			cell2.innerHTML = "<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+","+"\""+(dNo)+"\""+","+"\""+(reqNo)+"\""+")'>";

           		checktest+=testCode+"@"+reqNo+"#";
               }
              }
          else if(matchtest!=0)
          {
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
           //    alert("test for check"+test);
            //   alert("test check from"+checktest);

               //alert(checktest.indexOf(test));
                 for(var b=0;b<checktest.split("#").length;b++)
                    { var checktestt=checktest.split("#")[b];
                    //    alert("test check from new"+checktestt);
                    //    alert("test check new"+test);
                        
                      if(checktestt.indexOf(test)==0)
                   {
                        flag="2";
                  //  alert("match"+test);
                    matchtest=test;
                    matchtest=matchtest.replace("@","#");
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
                    				
                    				if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[28]=="1")
                                   {	
                    				for(var i=1;i<td.length;i++) {
                    					var namee=td[0].innerHTML+",";
                    					var nameee=td[0].innerHTML+";";
                                        // alert(td[1].innerHTML);
                    					//alert("testName"+testName);
                    					namee+=testName;
                    					nameee+=testName;
                    					reqformtestnames=nameee;
                    					//alert("reqformtestnames"+reqformtestnames);
                    					//reqformtestcodes=rwnamee;
                    	                   //alert("td[i].innerHTML"+td[i].innerHTML);
                                 //     alert("name"+namee);
                                 testName=namee;
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
	   row.id=testCode+"#"+reqNo;;
	   row.name=testName;
	   if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[28]=="1")
	   {
	  	 
	   var cell1 = row.insertCell(0);
	   		var cell2 = row.insertCell(1);
	   		cell1.innerHTML = (document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[17];

	   		if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[28]=="1")
	   			cell2.innerHTML = "<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+","+"\""+(dNo)+"\""+","+"\""+(reqNo)+"\""+")'>";
	  		
	   			checktest+=testCode+"@"+reqNo+"#";
	         // alert("overalltest"+overalltest);
	          }
       }
         
        }
      else 
      {
           

      
var rowCount = table.rows.length;
reqformtestnames+="!"+testName;
reqformtestcodes+="!"+testCode;
	//alert("rowCount"+rowCount);
var row = table.insertRow(rowCount);
row.id=testCode+"#"+reqNo;;
row.name=testName;
if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[28]=="1")
{
	 
var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		cell1.innerHTML = (document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[17];

		if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[28]=="1")
			cell2.innerHTML = "<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+","+"\""+(dNo)+"\""+","+"\""+(reqNo)+"\""+")'>";
		
			checktest+=testCode+"@"+reqNo+"#";
   // alert("overalltest"+overalltest);
    }
      
 }
     }
    // alert("golobalreqformtestnames"+reqformtestnames);
    // alert("golobalreqformtestcodes"+reqformtestcodes);
     
} */


function checkreqformTestType(TestCode)
{
	
	//alert("setAptNoUsingAjax"+appoitmentNo+"labcode"+tmpLabCode+"testCode"+tmpTestCode);
	
	var flg = false;
	var remarks = "";
	var _mode = "AJX_CHECK_REQFORM_TESTTYPE";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/invResultValidationTemplateTile.cnt?hmode="+_mode+"&testCodee="+TestCode, sync:true, postData: "", handleAs: "text",
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
	//alert("amppedtest"+remarks);
	return remarks;
}

function displaySamplePatDetailsPopUp(val)
{	
	document.getElementsByName('chkSamplePatient1')[0]

	var res = val.replace(/#/g, "%23");
     res = res.replace(/@/g, "%40");
	//document.getElementsByName('hmode')[0].value='SHOWPATDETAILS';
	
 //	document.forms[0].submit();

 	var hmode="SHOWPATDETAILSPOPUP";
	//alert(testCode);
	
	var url1="/HISInvestigationG5/new_investigation/invResultReValidationTemplateTile.cnt";
	//url1=url1+"?isPatDetailPage="+isPatDetailPage+"&selectedCheckbox="+res+"&hmode="+hmode;
	var isPatDetailPage="1";
	var ispreview="2";
	mywindow=window.open (url1+"?isPatDetailPage="+isPatDetailPage+"&selectedCheckbox="+res+"&ispreview="+ispreview+"&hmode="+hmode,"_blank","scrollbars=1,directories=no, status=no,width=700, height=500,top=200,left=500");
	//mywindow=window.open (url1+"?selectedCheckbox="+concatenateChkBoxVal+"&isPatDetailPage="+isPatDetailPage+"&hmode="+hmode,"_blank","scrollbars=1,directories=no, status=no,width=700, height=500,top=200,left=500");
     //var menuu="Result Validation";
	//callMenu(url1,menuu);
	
	}


function showSearchDiv(obj)
{
	//alert(obj.value);
	if(obj.value==-1)
	{
		document.getElementById('goButton').style.display="none";
	}
	else
	{
		var patientType = document.getElementsByName("sampleAreaCode")[0].value.split("#")[1];
 		document.getElementsByName("patientType")[0].value = patientType;
		// document.getElementById('goButton').style.display="";
		document.getElementsByName("sampleAreaName")[0].value=document.getElementsByName("sampleAreaCode")[0].options[document.getElementsByName("sampleAreaCode")[0].selectedIndex].text;
		document.getElementsByName("isSampleAreaSelected")[0].value="1";
		//document.getElementsByName("showStatus")[0].value="1";
		
		document.getElementsByName("hmode")[0].value="NEW";
		document.forms[0].submit();
	}
	
	
}


var snomedidd="";
function snomedctsingle(id)
{
	 snomedidd=id;
	// alert("in snomed");
	 if(document.getElementById('txt-snomed-ct-search_'+snomedidd).value=="")
	 document.getElementById('txt-snomed-ct-search_'+snomedidd).innerHTML = "Enter 3 characters to search";
	// alert('val'+document.getElementById('txt-snomed-ct-search_'+snomedidd).value);
	var callbck_index =function(ret_OUT){setValue(ret_OUT);};
	var semantictag_IN;
	var acceptability_IN;
	var state_IN ;
	var returnlimit_IN;
	var retterm_value ={};
	var elmtId = null;
	var semantictag='';
	if(elmtId==null || elmtId==undefined)
		{
		elmtId=id; semantictag="";
	
		}
	//elmtId="2";
	//alert(elmtId);
	
	selectSNOMEDCTauto('ACTIVE',semantictag,'SYNONYMS','10','null',elmtId,callbck_index);
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	}

function setValue(selectedSNOMEDTerm)
{
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
	/*var arr=selectedSNOMEDTerm.split(",");
	var str=arr[0];
	var str1=arr[1];
	*///alert(str[0]); alert(str1);
		var str=selectedSNOMEDTerm.term;
		var str1=selectedSNOMEDTerm.conceptId;
     //     alert(str);
   //      alert(str1);
	/* document.getElementsByName("prefferedTerm")[0].value=str;
	document.getElementsByName("conceptId")[0].value=str1;
	 */
	 var snomedname="txt-snomed-ct-search_"+snomedidd;
	 	 var snomedcode="txt-snomed-ct-search_code"+snomedidd;
	 document.getElementById(snomedname).value=str;
	    document.getElementById(snomedcode).value=str1;
	    //alert("val"+document.getElementsByName("tt1").value);
		}
	else
		{
		document.getElementById(snomedname).value="-";
		document.getElementById(snomedcode).value="";
	//	alert("blank");
		/* document.getElementsByName("prefferedTerm")[0].value="";
		 document.getElementsByName("conceptId")[0].value="";*/
		}
}

function ShowRequistionForm(testCode,testName,labCode,labName,requisitionDNo,reqNoo)
{
		// alert("reqformtestnames"+reqformtestnames);
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
 
var status=1;
var hmode="EXISTINGREQUISITIONFORMDATA";
//alert(testCode);
var url1="/HISInvestigationG5/new_investigation/requisitionformprocess.cnt";
var url=url1+"?testCode="+testCode+"&testName="+testName+"&labCode="+labCode+"&labName="+labName+"&hmode="+hmode+"&status="+status+"&requisitionDNo="+requisitionDNo+"&reqformtestnames="+reqformtestnames+"&reqformtestcodes="+reqformtestcodes;
var menuu="Requisition Form";
callMenu(url,menuu);
//mywindow=window.open (url1+"?testCode="+testCode+"&testName="+testName+"&labCode="+labCode+"&labName="+labName+"&hmode="+hmode+"&status="+status+"&requisitionDNo="+requisitionDNo,"_blank","scrollbars=1,directories=no, status=no,width=1000, height=500,top=200,left=500");
} 

/* function ShowRequistionForm(testCode,testName,labCode,labName,requisitionDNo)
{
	 var status=1;
	var hmode="EXISTINGREQUISITIONFORMDATA";
	//alert(testCode);
	var url1="/HISInvestigationG5/new_investigation/requisitionformprocess.cnt";
	var menuu="Requisition Form";
	url=url1+"?testCode="+testCode+"&testName="+testName+"&labCode="+labCode+"&labName="+labName+"&hmode="+hmode+"&status="+status+"&requisitionDNo="+requisitionDNo;
	callMenu(url,menuu);
	//mywindow=window.open (url1+"?testCode="+testCode+"&testName="+testName+"&labCode="+labCode+"&labName="+labName+"&hmode="+hmode+"&status="+status+"&requisitionDNo="+requisitionDNo,"_blank","scrollbars=1,directories=no, status=no,width=1000, height=500,top=200,left=500");
}    */  

function callMenu(url,menu)
{
	//alert("1");
	var selMenu=menu;
	parent.ajaxStartMenu();
	menu=menu.toString().replace(/_/g,' ').replace(/XXY/g,'/').replace(/XXX/g,'&');
	//url = Base64.decode(url).toString();
	parent.callMenu(url,menu);
	$("#"+selMenu).css("color","#6D00D5");
	parent.ajaxCompleteMenu();
	
	//document.getElementById("billingid").style.display="none";
}  

function hyper(thiss,url1)
{
	//url1="/HISInvestigationG5/new_investigation/antibioticprocesss.cnt";

	var matchid=thiss.id;
	var values= matchid.split('#');
	//alert(a);
//	var elementCode=thisobj.name.split('#')[3];
	mywindow=window.open (url1+"?requisitionDNo="+values[0]+"&testParaCode="+values[3],"_blank","scrollbars=1,directories=no, status=no,width=700, height=500,top=200,left=500");
/* 	mywindow.moveTo(300,300); */		
}


function callOnClick(obj)
{

    if(obj.checked==true)
	{
     //alert(obj.id);
    	showInstructions5canned(obj);
	}
	
}

function deleteTableInstcanned()
{
	document.getElementsByName('cannedCode')[0].value='';
    document.getElementsByName('cannedName')[0].value='';
    
	
}

function showInstructions5canned(obj)
{
	//alert(inst);
	
	
	deleteTableInstcanned();
	addRowToTableInstcanned(obj);
	
	popup("popUpDiv5canned");
	
	
}
	
	
	function closeInstructionscanned()
{

		if(document.getElementsByName('cannedCode')[0].value=='')
		    {
			alert("Enter Code");
			}
		else if(document.getElementsByName('cannedName')[0].value=='')
		    {
			alert("Enter Name");
			}
		else
		{
		var code=document.getElementsByName('cannedCode')[0].value;
   var name=document.getElementsByName('cannedName')[0].value;
   var val=document.getElementsByName('cannedvall')[0].value;
   // alert(code);
  //  alert(name);
 //alert("name"+val);
//chandan


//ajax fire save modify canned but check code n name if already exist code set -1 n name set -2 in bo    
  var valuee=  insertcanneddetailsAjax(val,code,name);

if(valuee=="-1")
  {
alert("Code Already Exist");
	  }
  if(valuee=="-2")
	  {
        alert("Name Already Exist.");
	  }
  if(valuee=="-3")
  {
    alert("Name and Code Already Exist.");
  }
if(valuee=="1")
	{
	
	val=val+"#chkbox"
	//alert(val);
	document.getElementById(val).checked=false;
	deleteTableInstcanned();
	popup("popUpDiv5canned");
	}

	
	}
}	

	function closecanned()
	
	{
		var val=document.getElementsByName('cannedvall')[0].value;
		val=val+"#chkbox"
		document.getElementById(val).checked=false;
		popup("popUpDiv5canned");
		}
	
function addRowToTableInstcanned(obj)
{
	
	document.getElementsByName('cannedvall')[0].value=obj.name;
	
 	}
	

function insertcanneddetailsAjax(val,code,name)
{
	var autoGenFormate = "";
	var resultEntryTemplateValue;

    // alert("ajax");
	var cbs =document.getElementsByName('chkResultValidationPatient');
	for(var i=0; i < cbs.length; i++)
		{

		var values=document.getElementsByName('chkResultValidationPatient')[i].value;

	    var labcode=values.split("#")[5];
	    
	var checkBoxId=cbs[i].id;
	var splitTheCheckBoxId=checkBoxId.replace('chkBOx', '');

	var n=0;
	          for(n;n<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea").length;n++)
	     	       {
				   get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea");
	            var name1=get_tags[n].name;
	           // alert(val);
	           // alert(name1);
				if(val==name1)
					{
	  var id1 = document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].id;
	 	           var editor = CKEDITOR.instances[id1];
					 
	 	         
	 	           if(editor!=null){
	 	        	 
	 	        	  
	            //  alert( editor.getData() );
	              
	               resultEntryTemplateValue=editor.getData();
	               if(resultEntryTemplateValue.length>12000)
	            	  {
	            	  alert("Editor Data limit is 12000, including spaces. Please don't exceed the limit");
	            	  return false;
	            	  
	            	  
	            	  }
		           //   alert(resultEntryTemplateValue);
				  }
					}
				  }
			 }

	
     val=val.split("#")[3];
	//alert("inside Ajax"+LabCode+TestCode+config);
	//alert(document.getElementsByName("patType")[0].value);
	var flg = false;
	resultEntryTemplateValue = resultEntryTemplateValue.replace(/&/g,"$");
	var _mode = "AJX_INSERT_MODIFY_CANNEDDETAILS";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/invResultEntryTemplateTile.cnt?hmode="+_mode+"&cannedCode="+code+"&cannedName="+name+"&cannedValue="+val+"&cannedContent="+resultEntryTemplateValue+"&modifycannedLabCode="+labcode, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert("aj"+data);
			autoGenFormate = data;
			flg = true;
		},
        error: function(error)
        {
            labTestCodeArray = tmpLabTestCodeArray;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	//alert("ajaxbv="+autoGenFormate);
	return autoGenFormate;
	
}

function showInstructions5(inst)
{
	//alert(inst);
	
	deleteTableInst();
	if(inst=="NA" || inst=="null")
		addRowToTableInst("No Instruction Available");
	else
	addRowToTableInst(inst);
	
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
//	var td2=document.createElement("TD");
 
	td1.innerHTML="<div  align='left' >"+inst+"</div>";   
	td1.className='tdfont';
	td1.width="1";
	
	
   
	tabRow.appendChild(td1); 
	//tabRow.appendChild(td2);  
	//document.forms[0].numberOfRow.value=numRows;
	}
	
	

function deleteTableInst()
{
	
	for(var i = document.getElementById("allInstructions").rows.length-1; i > 0; i--)
	{
		document.getElementById("allInstructions").deleteRow(i);
		
	}
	
}
function labBased()
{
	
	 if(!validateTodayOrDate())
	 {return false;}
	
	if(document.getElementsByName!="-1")
	{
		document.getElementsByName('onLoadValue')[0].value="ONLOAD";
	document.getElementsByName('hmode')[0].value="GETDETAILS";
     document.forms[0].submit();
	
	}
	}

//call this function to generate auto complete lists in runtime by passing the value of object list, id of the input tag and hidden tag. puneet.
function generateAuto(objList,autoId,hiddenId)
{
	setInterval(function() {
	    $(autoId).autocomplete({
	    	source:objList,
	        select: function(event, ui) {
	        	$(hiddenId).val(ui.item.value); 
	            event.preventDefault();  
	            $(autoId).val(ui.item.label); 
	        },

	    focus: function(event, ui) { 
	           event.preventDefault(); 
	           $(autoId).val(ui.item.label);}
	     });
	    }, 1000);
	
	
	}
	
	
function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}


	

function onClickGO(hospitalCode)
{
	 var crno =document.getElementsByName("tempPatCRNo")[0].value;
     var textLength = crno.length;
     var hosCodeLen=hospitalCode.length;
     document.getElementsByName('onLoadValue')[0].value="";
     
     if(!validateTodayOrDate())
    	 {return false;}

   //changed by ashu
     
    if(document.getElementsByName("searchBy")[0].checked==true)
  	{
  		showReqDate();
  		
  	}
  	else
  	{	
  		showCollDate();
  	}
     
     
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
	        // testWiseCode
 /* testGroupCode
 fromSampleNo
 fromLabNo
 toLabNo
 toSampleNo */
 var genTypeValue=document.getElementsByName('generationType')[0].value;
	//alert("onload"+genTypeValue); showOnLoad
 
 
 if(genTypeValue=="P")
	{
	 if(document.getElementsByName("tempPatCRNo")[0].value=="-1")
     {
    	alert("Select    Name ... ");
	    document.getElementsByName("tempPatCRNo")[0].focus();
	    return false;
     }  
 
	}
if(genTypeValue=="T")
	{
	 if(document.getElementsByName("testWiseCode")[0].value=="-1")
     {
    	alert("Select Test   Name ... ");
	    document.getElementsByName("testWiseCode")[0].focus();
	    return false;
     }
 
	}
if(genTypeValue=="L")
	{
	 if(document.getElementsByName("fromLabNo")[0].value=="-1")
     {
    	alert("Select From Lab No ... ");
	    document.getElementsByName("fromLabNo")[0].focus();
	    return false;
     }
 
	 if(document.getElementsByName("toLabNo")[0].value=="-1")
     {
    	alert("Select To Lab No ... ");
	    document.getElementsByName("toLabNo")[0].focus();
	    return false;
     }
	}
if(genTypeValue=="S")
	{
	  
	 if(document.getElementsByName("fromSampleNo")[0].value=="-1")
     {
    	alert("Select From Sample No ... ");
	    document.getElementsByName("fromSampleNo")[0].focus();
	    return false;
     }

	 if(document.getElementsByName("toSampleNo")[0].value=="-1")
     {
    	alert("Select To Sample No ... ");
	    document.getElementsByName("toSampleNo")[0].focus();
	    return false;
     }
	 
	}  


if(genTypeValue=="TG")
{
	 if(document.getElementsByName("testGroupCodeWise")[0].value=="-1")
     {
    	alert("Select Test Group  Name ... ");
	    document.getElementsByName("testGroupCodeWise")[0].focus();
	    return false;
     }
	 
}  //allPatient

              
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
					       	 
					           
					           var genTypeValue=document.getElementsByName('generationType')[0].value;
					       	//alert("onload"+genTypeValue); showOnLoad
					        
					        
					        if(genTypeValue=="P")
					       	{
					       	 if(document.getElementsByName("tempPatCRNo")[0].value=="-1")
					            {
					           	alert("Select    Name ... ");
					       	    document.getElementsByName("tempPatCRNo")[0].focus();
					       	    return false;
					            }  
					        
					       	}
					       if(genTypeValue=="T")
					       	{
					       	 if(document.getElementsByName("testWiseCode")[0].value=="-1")
					            {
					           	alert("Select Test   Name ... ");
					       	    document.getElementsByName("testWiseCode")[0].focus();
					       	    return false;
					            }
					        
					       	}
					       if(genTypeValue=="L")
					       	{
					       	 if(document.getElementsByName("fromLabNo")[0].value=="-1")
					            {
					           	alert("Select From Lab No ... ");
					       	    document.getElementsByName("fromLabNo")[0].focus();
					       	    return false;
					            }
					        
					       	 if(document.getElementsByName("toLabNo")[0].value=="-1")
					            {
					           	alert("Select To Lab No ... ");
					       	    document.getElementsByName("toLabNo")[0].focus();
					       	    return false;
					            }
					       	}
					       if(genTypeValue=="S")
					       	{
					       	  
					       	 if(document.getElementsByName("fromSampleNo")[0].value=="-1")
					            {
					           	alert("Select From Sample No ... ");
					       	    document.getElementsByName("fromSampleNo")[0].focus();
					       	    return false;
					            }

					       	 if(document.getElementsByName("toSampleNo")[0].value=="-1")
					            {
					           	alert("Select To Sample No ... ");
					       	    document.getElementsByName("toSampleNo")[0].focus();
					       	    return false;
					            }
					       	 
					       	}  


					       if(genTypeValue=="TG")
					       {
					       	 if(document.getElementsByName("testGroupCodeWise")[0].value=="-1")
					            {
					           	alert("Select Test Group  Name ... ");
					       	    document.getElementsByName("testGroupCodeWise")[0].focus();
					       	    return false;
					            }
					       	 
					       }  //allPatient
					           
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

function validateTodayOrDate()
{
	success=false;        	   
    
    valFromDate=document.getElementsByName('fromDate')[0];
	valToDate=document.getElementsByName('toDate')[0];
	valFromCollDate=document.getElementsByName('fromCollDate')[0];
	valToCollDate=document.getElementsByName('toCollDate')[0];
	
	<%String systemdate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");%>
	document.getElementsByName('sysDate')[0].value="<%=systemdate%>";
	valSysDate=document.getElementsByName('sysDate')[0];
      
	if(document.getElementsByName("searchBy")[0].checked==true){
		 if(compareDateCall(valFromDate,valToDate,2,"From Date","To Date") && compareDateCall(valToDate,valSysDate,2,"To Date","System Date"))
		    {
			    success=true;
		    }             
	}else{

		if(compareDateCall(valFromCollDate,valToCollDate,2,"From Date","To Date") && compareDateCall(valToCollDate,valSysDate,2,"To Date","System Date"))
	    {
		    success=true;
	    }  
	}
	            
    return success;        
}
function displaySamplePatDetails()
{	
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
	
	document.getElementsByName('selectedCheckbox')[0].value=concatenateChkBoxVal;
	document.getElementsByName('hmode')[0].value='SHOWPATDETAILS';
	
 	document.forms[0].submit();
 	
	}

function revalidateSamplePatDetails()
{	
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
	
	document.getElementsByName('selectedCheckbox')[0].value=concatenateChkBoxVal;
	document.getElementsByName('hmode')[0].value='REVALIDATE';
	
 	document.forms[0].submit();
 	
	}
	
function ValidateSameCrNo(obj)
{
	
	if(obj.checked)
	{
		document.getElementById('nextDiv').style.display="";
		document.getElementById('nextDiv1').style.display="";        
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
			    		 
			    	/* alert("please select same cr no");
			    	document.getElementById('nextDiv').style.display="none";
			    	document.getElementById('nextDiv1').style.display="none";
			    	obj.checked=false;
			    	return false; */
			    	} 
				}
		}
	}
 
}


function displayAllTest(size)
{var k=0;
for(k=0;k<size;k++)
			{
	 	if(document.getElementById("headings"+k)!=null)
	document.getElementById("headings"+k).style.display="";
	if(document.getElementById("hideTest"+k)!=null)
	document.getElementById("hideTest"+k).style.display="";
	if(document.getElementById("showTest"+k)!=null)
	document.getElementById("showTest"+k).style.display="none";	
	 
	document.getElementById("showAllTest").style.display="none";	
	document.getElementById("hideAllTest").style.display="";
			}
}
	



function hideAllTests(size)
{	
	var k=0;
	for(k=0;k<size;k++)
		{
		 
			if(document.getElementById("headings"+k)!=null)
document.getElementById("headings"+k).style.display="none";
			if(document.getElementById("hideTest"+k)!=null)
document.getElementById("hideTest"+k).style.display="none";
			if(document.getElementById("showTest"+k)!=null)
document.getElementById("showTest"+k).style.display="";	
			 
document.getElementById("showAllTest").style.display="";	
			 
document.getElementById("hideAllTest").style.display="none";
			 
		}
	}
	
	
function showTestDetails(k,val)
{
	var val1=k; 
	//alert(val);//chkValue
	// alert(document.getElementsByName("chkValue")[0].value);
	 var chkValue=document.getElementsByName("chkValue")[0].value.split("&");
	 for(i=0;i<chkValue.length;i++)
		 {
		 if(val==chkValue[i])
			 {
			 val1++;
			// alert(val1);
			 }
		  
		 
		 }
	 if(k!=val1)
		 {
	 for(j=k;j<val1;j++)
		 {
		 
document.getElementById("headings"+j).style.display="";
		 }
}
	 else
		 {
		 document.getElementById("headings"+k).style.display="";
		 
		 }
/* document.getElementById("values"+k).style.display="";
 */document.getElementById("hideTest"+k).style.display="";
document.getElementById("showTest"+k).style.display="none";	
	
}

function hideTestDetails(k,val)
{
	var val1=k; 
	//alert(val);//chkValue
	// alert(document.getElementsByName("chkValue")[0].value);
	 var chkValue=document.getElementsByName("chkValue")[0].value.split("&");
	 for(i=0;i<chkValue.length;i++)
	 {
	 if(val==chkValue[i])
		 {
		 val1++;
		// alert(val1);
		 }
	  
	 
	 }
 if(k!=val1)
	 {
 for(j=k;j<val1;j++)
	 {
	 
document.getElementById("headings"+j).style.display="none";
	 }
}
 else
	 {
	 document.getElementById("headings"+k).style.display="none";
	 
	 }
//document.getElementById("headings"+k).style.display="none";
/* document.getElementById("values"+k).style.display="none";
 */document.getElementById("hideTest"+k).style.display="none";
document.getElementById("showTest"+k).style.display="";	
}


function showPatDetails(k)
{
document.getElementById("showhide"+k).style.display="";
document.getElementById("hide"+k).style.display="";
document.getElementById("show"+k).style.display="none";	
	
}

function hidePatDetails(k)
{
document.getElementById("showhide"+k).style.display="none";
document.getElementById("hide"+k).style.display="none";
document.getElementById("show"+k).style.display="";	
}

function submitFor()
{
	document.getElementsByName('showStatus')[0].value='0';
	document.getElementsByName('hmode')[0].value='NEW';
	document.forms[0].submit();
}

function selectAll()
{	var cbs=document.getElementsByName('chkResultValidationPatient');

	
for(var i=0; i < cbs.length; i++)
cbs[i].checked=true;
	
	onSave();
	}
	
	
function cancelFunc()
{
	window.parent.closeTab();
}

function onSave()
{
	var mymapvalue = new Map();

       var count=0;
       var concatenateChkBoxVal="";
       var cbs =document.getElementsByName('chkResultValidationPatient');
      // alert(cbs);
      // alert(cbs.length);
        var name;
         var splitTemplateValue;
         var reqNO=[];
         var parameterValue=[];
         var parameterCode=[];
         var parantParameter=[];
         var resultValidationTemplateValue=[];
       //alert(cbs.length);
       for(var i=0; i < cbs.length; i++)
       {
       if(cbs[i].checked)
       {


    	// alert("dataaaa"+cbs[i].value);

           var commentsboxdata=cbs[i].value;

           if(commentsboxdata.split("#")[19]!="NA")
               {
        	   var sampleid=commentsboxdata.split("#")[20];
       		sampleid=sampleid.split("/")[0];
           commentsboxdata=commentsboxdata.split("#")[0]+"#"+commentsboxdata.split("#")[1]+"#"+sampleid;
               } else
           	commentsboxdata=commentsboxdata.split("#")[0]+"#"+commentsboxdata.split("#")[1]+"#";    

           if(document.getElementById(commentsboxdata)!=null  )
           {
       //    alert("dd");
       var commentBoxEditedValue = "";
       	if( document.getElementById(commentsboxdata).value==""){
       		document.getElementById(commentsboxdata).value=" ";	}
   		else{
   			commentBoxEditedValue = document.getElementById(commentsboxdata).value;
    		var check23 = checkReservedCahracters(commentBoxEditedValue);
      	  if(check23==true){
  			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
  			return;
          	}else{
  			
              } 
    		commentBoxEditedValue = removeSpecialCharacter(commentBoxEditedValue);
    		document.getElementById(commentsboxdata).value=commentBoxEditedValue;
   	   		}
       	
          cbs[i].value = cbs[i].value +"#"+document.getElementById(commentsboxdata).value; 

           }
       else
           {

           cbs[i].value = cbs[i].value +"#"+" "; 

           }
           
              //var values=document.getElementById(i+"chkBOx").value;
              //alert("The value is"+values);
              count++;      
               var k=0;
           //alert(document.getElementById(i+'templateValue').getElementsByTagName("input"));
             
//          
//         alert("get_tags length"+get_tags.length);
//            for (var i=0; i<get_tags.length; i++) {
//                // assigns style properties
//                 var name=get_tags[i].name;
//                 alert("i"+name);
//              }
               
             var checkBoxId=cbs[i].id;
        	
        	// var splitTheCheckBoxId=checkBoxId.substr(0,2);
        	 var splitTheCheckBoxId=checkBoxId.replace('chkBOx', '');
             for(k;k<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input").length;k++)
                  { 
             	
                 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
                 
                 get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input");
                   name=get_tags[k].name;
                   id=get_tags[k].id;
                   hiddenid="hiddenid"+id;
                   defaultid="default"+hiddenid;
              	  typ=get_tags[k].type;
              	  hidddentext="hidden";
              
                 //alert("type is " + typ);
                   if(typ!=hidddentext)
                   {
                  	 
               //   	 alert("not hidden");
                   splitTemplateValue=name.split("#");
                   reqNO.push(splitTemplateValue[0]);
                    parameterValue=splitTemplateValue[3];
                 //   alert("parameter value    "+parameterValue);
                    parameterCode.push(parameterValue.substring(0, 5));
                    parantParameter.push(parameterValue.substring(9,18));
                  
                      
                //  alert("parameterValue"+parameterValue+"reqno"+reqNO+"parameter"+parantParameter);
                 
                      /* if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value=="")
                             {
                                    alert("Enter the field Focussed");
                                    document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].focus();
                                 return false;
                              } */
                 
                         // document.getElementsByName('resultEntryTemplateValue').value.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);       
                    //  resultEntryTemplateValue.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);
                    var resultValidationTemplateValue="";
                    
                      if(id.indexOf('auto')!=-1)    
                  	  {
                  
                  	 if( document.getElementById(hiddenid).value!=null && document.getElementById(hiddenid).value!="" )
                  	  resultValidationTemplateValue=document.getElementById(hiddenid).value;
                  	 else 
                  		 resultValidationTemplateValue=document.getElementById(defaultid).value;
                  	  } 
                    else 
                        {
                    	  if(id.indexOf('chkbox')== -1 )
                          {

                             if(typ=='checkbox')
                              {
                               //  alert("checkbox");
                                  if(document.getElementById(id).checked==true)
                                      {
                                      //alert("check");
                                  	resultValidationTemplateValue="1";}
                                  if(document.getElementById(id).checked==false)
                                      {
                                  	resultValidationTemplateValue="0";}
                              }
                             else if(typ=='file')
                             {

                            	  var x = document.getElementById(name);
                   				 var file = x.files[0];

                   				 /*  alert("filefile"+file);
                   		       if( file!=undefined)
                       		       {
                       		       alert("yes");
                       		       }
                   		       else
                       		       {
                       		       alert("bno");
                       		       } */

                   		       var uploadid="view@@"+name;
                      		   // alert(document.getElementById(uploadid).className);
                      		
                   		       //return null; 
                   		       
                                 if( file!=undefined)
                           resultValidationTemplateValue="File Uploaded";
                                 else if( document.getElementById(uploadid)!=null && document.getElementById(uploadid).className !=null)
                                     resultValidationTemplateValue="File Uploaded";
                   
                                    }
                             
                             else    
                             {    resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value;
                             
                             setdepe(parameterValue,resultValidationTemplateValue.trim(),mymapvalue,values,checkBoxId);
                             
                             }
                             //added by krishnan nema on 16/10/2018
                             /* var res  = resultValidationTemplateValue.replace("$", " ", true);
                  	         var res2  = res.replace("#", " ", true);
                  	         var res3 = res2.replace("~", " ", true);
                  	       var res4 = res3.replace("`", " ", true);
                  	       resultValidationTemplateValue = res4.replace("@", " ", true); */
                             var check23 = checkReservedCahracters(resultValidationTemplateValue);
                         	  if(check23==true){
                     			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
                     			return;
                             	}else{
                     			
                                 } 
                  	     resultValidationTemplateValue = removeSpecialCharacter(resultValidationTemplateValue);
                  	        
                          name+='#'+resultValidationTemplateValue+'#'+values+"#"+"-";
                          
                       concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                       concatenateChkBoxVal+='@';
                        }
                        }
                   }
                          
                          
                           
                    
                     
                        
                        
                    }
        // alert(document.getElementById(i+'templateValue').getElementsByTagName("select").length);
       // alert("select value"+document.getElementById(i+'templateValue').getElementsByTagName("select")[0].value);
        var j=0;
         for(j;j<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select").length;j++)
              {   	 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
     		//alert("The value is"+values);
        	  get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select");
       	   name=get_tags[j].name;
        	 
        	// alert("inside here");
        //	alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length);
            	/*  if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value=="-1" || document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value=="")
	         	   {
				      alert("Enter the field Focussed");
				      document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].focus();
			          return false;
		           } */
	          //   document.getElementsByName('resultEntryTemplateValue')[0].value=document.getElementById(i+'templateValue').getElementsByTagName("input")[j].value;	 
              var multiValue="";
              var resultEntryTemplateValue;
	        if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[0].value!="-1")
              for (var kk=0;kk<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length ;kk++)
	        	  {
	        	  //&& document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[0].value!="-1"
	        	 if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].selected==true)
	        	  { // alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value)
	        	   	  multiValue+=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value;
	        	      multiValue+="$";   	  
	        	  
	        	  }
	        	 resultEntryTemplateValue=multiValue
	        	  }
	          
	        else
	       	resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value;
		   
	        
	      //  alert(resultEntryTemplateValue);
	        name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-";
	          
     		  	concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
    			concatenateChkBoxVal+='@';
  } 
         
         
        // alert("text Area value"+document.getElementById(i+'templateValue').getElementsByTagName("textarea")[0].value);
         var n=0;
          for(n;n<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea").length;n++)
              {
                    
                var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
      //     alert("The value is"+values);
                //alert("inside here");
                    get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea");
                        name=get_tags[n].name;
                      
                     
                     /* if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value=="")
                        {
                                 alert("Enter the field Focussed");
                                 document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].focus();
                               return false;
                         } */
                  //  document.getElementsByName('resultEntryTemplateValue')[0].value=document.getElementById(i+'templateValue').getElementsByTagName("input")[j].value;       
                
              
                   var id1 = document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].id;
 	           var editor = CKEDITOR.instances[id1];
 	       
 	         
 	           if(editor!=null){
 	        	 
 	        	  
            //  alert( editor.getData() );
              
              var resultEntryTemplateValue=editor.getData();
             /*  var res  = resultEntryTemplateValue.replace("$", " ", true);
              var res2  = res.replace("#", " ", true);
              var res3  = res2.replace("~", " ", true);
              var res4  = res3.replace("`", " ", true);
              resultEntryTemplateValue = res4.replace("@", " ", true); */
              resultEntryTemplateValue = resultEntryTemplateValue.replace("&#39;",
        	    	     "<img id='base64image'src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAYAAAAMCAIAAADONVt5AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAlSURBVBhXY/iPAUgSur8gKmrBfQgbr1DdQQiTNOORAZlC//8DADcC0LbmFlSzAAAAAElFTkSuQmCC' />",true);
       
              var check23 = checkReservedCahracters(resultEntryTemplateValue);
        	  if(check23==true){
    			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
    			return;
            	}else{
    			
                } 
              resultEntryTemplateValue = removeSpecialCharacterEditor(resultEntryTemplateValue);
              
              
              if(resultEntryTemplateValue.length>12000)
        	  {
        	  alert("Editor Data limit is 12000, including spaces. Please don't exceed the limit");
        	  return false;
        	  
        	  
        	  }
	          name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-";
          
 	          }

 	          else
 	        	  {
     	         var resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value;
//added by krishnan nema on 16/10/2018
     	         
     	        /*  var res  = resultEntryTemplateValue.replace("$", " ", true);
     	         var res2  = res.replace("#", " ", true);
     	         var res3  = res2.replace("~", " ", true);
     	        var res4  = res3.replace("`", " ", true);
     	        resultEntryTemplateValue = res4.replace("@", " ", true); */
     	        var check23 = checkReservedCahracters(resultEntryTemplateValue);
            	  if(check23==true){
        			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
        			return;
                	}else{
        			
                    } 
     	       resultEntryTemplateValue = removeSpecialCharacter(resultEntryTemplateValue);
     	        
		          name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-";
	          
 	        	  
 	        	  }
               /* var resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value;
                        name+='#'+resultValidationTemplateValue+'#'+values;*/
                 
                     concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                     concatenateChkBoxVal+='@';

              
              }



          /* added by chandan */
	       k = 0;
	       for(k;k<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a").length;k++)
  	    {

       	//alert("hyperlink");
       	
           var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
        //   alert(values);
           get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a");
             name=get_tags[k].name;
             id=get_tags[k].id;
           //  alert(id);
           if(id.includes("view@@")==false) // not to add file upload view hyperlink option by chandan 
                 {
           if(id.indexOf("template")!=-1)
                 {
               
             splitTemplateValue=id.split("#");
             reqNO.push(splitTemplateValue[0])
             parameterValue=splitTemplateValue[3];
         /*   //alert(name);
             id=get_tags[k].id;
             alert(id);
             //alert("id for "+k+"    "+id);
             hiddenid="hiddenid"+id;
             defaultid="default"+hiddenid;
        	  typ=get_tags[k].type;
        	  alert(typ);
        	  hidddentext="hidden";
        	  checkboxcheck="checkbox";
        
          // alert("type is " + typ);
             if(typ!=hidddentext)
             {
            	 
           // 	 alert("not hidden");
             splitTemplateValue=id.split("#");
             reqNO.push(splitTemplateValue[0]);
              parameterValue=splitTemplateValue[3];
            //  alert("parameter value    "+parameterValue);
              parameterCode.push(parameterValue.substring(0, 5));
              parantParameter.push(parameterValue.substring(9,18));
            
                
          //  alert("parameterValue"+parameterValue+"reqno"+reqNO+"parameter"+parantParameter);
           
                if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value=="")
                       {
                              alert("Enter the field Focussed");
                              document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].focus();
                           return false;
                        }
           
                   // document.getElementsByName('resultEntryTemplateValue').value.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);       
              //  resultEntryTemplateValue.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);
              var resultValidationTemplateValue="";

              //chandan comment auto    
          /*     if(id.contains('auto'))    
            	  {
          
            	 if( document.getElementById(hiddenid).value!=null && document.getElementById(hiddenid).value!="" )
            	  resultValidationTemplateValue=document.getElementById(hiddenid).value;
            	 else
            		 resultValidationTemplateValue=document.getElementById(defaultid).value;
            	  } 
              else */
            /*   if(typ!=checkboxcheck)
                  {
                resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value;
              */    //alert("1 " +name);
              var resultEntryTemplateValue=document.getElementsByName('hyperLinkTableSession')[0].value;

              if(document.getElementsByName('hyperLinkTableSession')[0].value=="")
              {
        	 // alert("true");
        	  var ide=id;
        	  ide="divId_"+id;
        	  var ider="idd"+id;
 //alert("ider"+document.getElementById(ider).innerHTML)
        	  if(document.getElementById(ider)!=null && document.getElementById(ider).innerHTML!='')
        	  {
        	  var idee1=id.split("#")[0]+"$$";
        	   idee1+=id.split("#")[3]+"$$";
        	//   alert("idee1"+idee1);
    		  resultEntryTemplateValue="hyperchanks" ;
    		  
             // alert("true1"+resultEntryTemplateValue);
        	  }
    	    
        	  else if(document.getElementById(ide)!=null && document.getElementById(ide).innerHTML!='')
            	  {
            	  var idee=id.split("#")[0]+"$$";
            	   idee+=id.split("#")[3]+"$$";
        		  resultEntryTemplateValue="hyperchanks" ;
        		  
                 // alert("true1"+resultEntryTemplateValue);
            	  }
        	    
              }
              if(id.includes("view@@")==false) // not to add file upload view hyperlink option by chandan 
              {
                    name+=id+"#"+resultEntryTemplateValue+"#"+values+"#hyperlink";
              // alert(name);    
                 concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                 concatenateChkBoxVal+='@';
              }
               // alert(concatenateChkBoxVal);
              /*     }
             }
               */      
            
                 }
                 } 
           } 
        



	       /* added by chandan */
	       k = 0;
	       for(k;k<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a").length;k++)
 	    {

      //	alert("hyperlink");
      	
          var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
         // alert("hyperlink"+values);
          get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a");
            name=get_tags[k].name;
            id=get_tags[k].id;
         //   alert("hyperlink"+id);
          if(id.includes("view@@")==false) // not to add file upload view hyperlink option by chandan 
                 {
          if(id.indexOf("template")!=-1)
                 {
            splitTemplateValue=id.split("#");
            reqNO.push(splitTemplateValue[0])
            parameterValue=splitTemplateValue[3];
        /*   //alert(name);
            id=get_tags[k].id;
            alert(id);
            //alert("id for "+k+"    "+id);
            hiddenid="hiddenid"+id;
            defaultid="default"+hiddenid;
       	  typ=get_tags[k].type;
       	  alert(typ);
       	  hidddentext="hidden";
       	  checkboxcheck="checkbox";
       
         // alert("type is " + typ);
            if(typ!=hidddentext)
            {
           	 
          // 	 alert("not hidden");
            splitTemplateValue=id.split("#");
            reqNO.push(splitTemplateValue[0]);
             parameterValue=splitTemplateValue[3];
           //  alert("parameter value    "+parameterValue);
             parameterCode.push(parameterValue.substring(0, 5));
             parantParameter.push(parameterValue.substring(9,18));
           
               
         //  alert("parameterValue"+parameterValue+"reqno"+reqNO+"parameter"+parantParameter);
          
               if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value=="")
                      {
                             alert("Enter the field Focussed");
                             document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].focus();
                          return false;
                       }
          
                  // document.getElementsByName('resultEntryTemplateValue').value.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);       
             //  resultEntryTemplateValue.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);
             var resultValidationTemplateValue="";

             //chandan comment auto    
         /*     if(id.contains('auto'))    
           	  {
         
           	 if( document.getElementById(hiddenid).value!=null && document.getElementById(hiddenid).value!="" )
           	  resultValidationTemplateValue=document.getElementById(hiddenid).value;
           	 else
           		 resultValidationTemplateValue=document.getElementById(defaultid).value;
           	  } 
             else */
           /*   if(typ!=checkboxcheck)
                 {
               resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value;
             */    //alert("1 " +name);
            

           //  var uploadidcheck="view@@"+id;
             if(id.includes("view@@")==false) // not to add file upload view hyperlink option by chandan 
                 {
                   name+=id+"#"+resultEntryTemplateValue+"#"+values+"#hyperlink";
             // alert(name);    
                concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                concatenateChkBoxVal+='@';
                 }
         //      alert(concatenateChkBoxVal);
             /*     }
            }
              */      
                   
                 }
                 }
          }
	          
         
         }
   
    
     }
       

      // alert("Finally concatenateChkBoxValues For Save "+concatenateChkBoxVal);
       
       
 var ischeckkk=ischeckk(mymapvalue);
  	 //alert("ischeckkk"+ischeckkk);
       if(ischeckkk)
  		 {
  		 return null;
  		 }
       
       document.getElementsByName('resultValidationTemplateValueWithHash')[0].value=concatenateChkBoxVal;
       
       
       /* for(j;j<reqNO.length;j++)
              { 
              
       
       document.getElementsByName('resultEntryTemplateValue').value=resultEntryTemplateValue[j];
    alert(document.getElementsByName('resultEntryTemplateValue').value);
       document.getElementsByName('parameterCode').value=parameterCode[j];
    document.getElementsByName('parantParameterCode').value=parantParameter[j];
    document.getElementsByName('requisitionDNo').value=reqNO[j];
              } */
       if(count==0)
       {
         alert("Please select a Atleast One record");
         return false;
       }
        
       
       document.getElementsByName('showStatus')[0].value='0';
       document.getElementsByName('hmode')[0].value="SAVE";
       document.forms[0].submit();
        
   return true;
        
  }


function modifyAll()
{	var cbs=document.getElementsByName('chkResultValidationPatient');

	
for(var i=0; i < cbs.length; i++)
cbs[i].checked=true;
	
	onModify();
	}

function onModify()
{
	
	var mymapvalue = new Map();

	 var count=0;
     var concatenateChkBoxVal="";
     var cbs =document.getElementsByName('chkResultValidationPatient');
    // alert(cbs);
    // alert(cbs.length);
      var name;
       var splitTemplateValue;
       var reqNO=[];
       var parameterValue=[];
       var parameterCode=[];
       var parantParameter=[];
       var resultValidationTemplateValue=[];
     //alert(cbs.length);
     for(var i=0; i < cbs.length; i++)
     {
     if(cbs[i].checked)
     {


    	// alert("dataaaa"+cbs[i].value);

         var commentsboxdata=cbs[i].value;

         if(commentsboxdata.split("#")[19]!="NA")
             {
        	 var sampleid=commentsboxdata.split("#")[20];
     		sampleid=sampleid.split("/")[0];
         commentsboxdata=commentsboxdata.split("#")[0]+"#"+commentsboxdata.split("#")[1]+"#"+sampleid;
             } else
         	commentsboxdata=commentsboxdata.split("#")[0]+"#"+commentsboxdata.split("#")[1]+"#"; 

         if(document.getElementById(commentsboxdata)!=null  )
         {
     //    alert("dd");
     var commentBoxEditedValue = "";
     	if( document.getElementById(commentsboxdata).value==""){
     		document.getElementById(commentsboxdata).value=" ";	
     	}else{
     		commentBoxEditedValue = document.getElementById(commentsboxdata).value;
    		var check23 = checkReservedCahracters(commentBoxEditedValue);
      	  if(check23==true){
  			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
  			return;
          	}else{
  			
              } 
    		commentBoxEditedValue = removeSpecialCharacter(commentBoxEditedValue);
    		document.getElementById(commentsboxdata).value=commentBoxEditedValue;
         	}
        cbs[i].value = cbs[i].value +"#"+document.getElementById(commentsboxdata).value; 

         }
     else
         {

         cbs[i].value = cbs[i].value +"#"+" "; 

         }
         
        
            //var values=document.getElementById(i+"chkBOx").value;
            //alert("The value is"+values);
            count++;      
             var k=0;
         //alert(document.getElementById(i+'templateValue').getElementsByTagName("input"));
           
//        
//       alert("get_tags length"+get_tags.length);
//          for (var i=0; i<get_tags.length; i++) {
//              // assigns style properties
//               var name=get_tags[i].name;
//               alert("i"+name);
//            }
             
           var checkBoxId=cbs[i].id;
      	
      	// var splitTheCheckBoxId=checkBoxId.substr(0,2);
      	 var splitTheCheckBoxId=checkBoxId.replace('chkBOx', '');
           for(k;k<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input").length;k++)
                { 
           	
               var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
               
               get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input");
                 name=get_tags[k].name;
                 id=get_tags[k].id;
                 hiddenid="hiddenid"+id;
                 defaultid="default"+hiddenid;
            	  typ=get_tags[k].type;
            	  hidddentext="hidden";
            
               //alert("type is " + typ);
                 if(typ!=hidddentext)
                 {
                	 
             //   	 alert("not hidden");
                 splitTemplateValue=name.split("#");
                 reqNO.push(splitTemplateValue[0]);
                  parameterValue=splitTemplateValue[3];
               //   alert("parameter value    "+parameterValue);
                  parameterCode.push(parameterValue.substring(0, 5));
                  parantParameter.push(parameterValue.substring(9,18));
                
                    
              //  alert("parameterValue"+parameterValue+"reqno"+reqNO+"parameter"+parantParameter);
               
                   /*  if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value=="")
                           {
                                  alert("Enter the field Focussed");
                                  document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].focus();
                               return false;
                            }
                */
                       // document.getElementsByName('resultEntryTemplateValue').value.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);       
                  //  resultEntryTemplateValue.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);
                  var resultValidationTemplateValue="";
                  
                    if(id.indexOf('auto')!=-1)    
                	  {
                
                	 if( document.getElementById(hiddenid).value!=null && document.getElementById(hiddenid).value!="" )
                	  resultValidationTemplateValue=document.getElementById(hiddenid).value;
                	 else 
                		 resultValidationTemplateValue=document.getElementById(defaultid).value;
                	  } 
                  else 
                      {
                  	  if(id.indexOf('chkbox')== -1 )
                        {

                           if(typ=='checkbox')
                            {
                             //  alert("checkbox");
                                if(document.getElementById(id).checked==true)
                                    {
                                    //alert("check");
                                	resultValidationTemplateValue="1";}
                                if(document.getElementById(id).checked==false)
                                    {
                                	resultValidationTemplateValue="0";}
                            }
                           else if(typ=='file')
                           {

                          	  var x = document.getElementById(name);
                 				 var file = x.files[0];

                 				 /*  alert("filefile"+file);
                 		       if( file!=undefined)
                     		       {
                     		       alert("yes");
                     		       }
                 		       else
                     		       {
                     		       alert("bno");
                     		       } */

                 		       var uploadid="view@@"+name;
                    		   // alert(document.getElementById(uploadid).className);
                    		
                 		       //return null; 
                 		       
                               if( file!=undefined)
                         resultValidationTemplateValue="File Uploaded";
                               else if( document.getElementById(uploadid)!=null && document.getElementById(uploadid).className !=null)
                                   resultValidationTemplateValue="File Uploaded";
                 
                                  }
                           
                           else    
                           {  resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value;

                           setdepe(parameterValue,resultValidationTemplateValue.trim(),mymapvalue,values,checkBoxId);

                           }
                          /*  var res  = resultValidationTemplateValue.replace("$", " ", true);
                	         var res2  = res.replace("#", " ", true);
                	         var res3 = res2.replace("~", " ", true);
                	       var res4 = res3.replace("`", " ", true);
                	       resultValidationTemplateValue = res4.replace("@", " ", true);  */
                           var check23 = checkReservedCahracters(resultValidationTemplateValue);
                       	  if(check23==true){
                   			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
                   			return;
                           	}else{
                   			
                               } 
                             	       
                	       resultValidationTemplateValue = removeSpecialCharacter(resultValidationTemplateValue);
                	       
                        name+='#'+resultValidationTemplateValue+'#'+values+"#"+"-";
                        
                     concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                     concatenateChkBoxVal+='@';
                      }
                      }
                 }
                        
                        
                         
                  
                   
                      
                      
                  }
      // alert(document.getElementById(i+'templateValue').getElementsByTagName("select").length);
     // alert("select value"+document.getElementById(i+'templateValue').getElementsByTagName("select")[0].value);
      var j=0;
       for(j;j<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select").length;j++)
            {   	 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
   		//alert("The value is"+values);
      	  get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select");
     	   name=get_tags[j].name;
      	 
      	// alert("inside here");
      //	alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length);
          	/*  if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value=="-1" || document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value=="")
	         	   {
				      alert("Enter the field Focussed");
				      document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].focus();
			          return false;
		           } */
	          //   document.getElementsByName('resultEntryTemplateValue')[0].value=document.getElementById(i+'templateValue').getElementsByTagName("input")[j].value;	 
            var multiValue="";
            var resultEntryTemplateValue;
	        if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[0].value!="-1")
            for (var kk=0;kk<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length ;kk++)
	        	  {
	        	  //&& document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[0].value!="-1"
	        	 if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].selected==true)
	        	  { // alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value)
	        	   	  multiValue+=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value;
	        	      multiValue+="$";   	  
	        	  
	        	  }
	        	 resultEntryTemplateValue=multiValue
	        	  }
	          
	        else
	       	resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value;
		   
	        
	      //  alert(resultEntryTemplateValue);
	        name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-";
	          
   		  	concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
  			concatenateChkBoxVal+='@';
} 
       
       
      // alert("text Area value"+document.getElementById(i+'templateValue').getElementsByTagName("textarea")[0].value);
       var n=0;
        for(n;n<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea").length;n++)
            {
                  
              var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
    //     alert("The value is"+values);
              //alert("inside here");
                  get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea");
                      name=get_tags[n].name;
                    
                   
                  /*  if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value=="")
                      {
                               alert("Enter the field Focussed");
                               document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].focus();
                             return false;
                       } */
                //  document.getElementsByName('resultEntryTemplateValue')[0].value=document.getElementById(i+'templateValue').getElementsByTagName("input")[j].value;       
              
            
                 var id1 = document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].id;
	           var editor = CKEDITOR.instances[id1];
	       
	         
	           if(editor!=null){
	        	 
	        	  
          //  alert( editor.getData() );
            
            var resultEntryTemplateValue=editor.getData();
           /*  var res  = resultEntryTemplateValue.replace("$", " ", true);
            var res2  = res.replace("#", " ", true);
            var res3  = res2.replace("~", " ", true);
            var res4  = res3.replace("`", " ", true);
            resultEntryTemplateValue = res4.replace("@", " ", true); */
            resultEntryTemplateValue = resultEntryTemplateValue.replace("&H39;",
    	    	     "<img id='base64image'src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAYAAAAMCAIAAADONVt5AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAlSURBVBhXY/iPAUgSur8gKmrBfQgbr1DdQQiTNOORAZlC//8DADcC0LbmFlSzAAAAAElFTkSuQmCC' />",true);
   
            var check23 = checkReservedCahracters(resultEntryTemplateValue);
      	 	if(check23==true){
  			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
  			return;
          	}else{
  			
              } 
            resultEntryTemplateValue = removeSpecialCharacterEditor(resultEntryTemplateValue);
           
            if(resultEntryTemplateValue.length>12000)
      	  {
      	  alert("Editor Data limit is 12000, including spaces. Please don't exceed the limit");
      	  return false;
      	  
      	  
      	  }
	          name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-";
        
	          }

	          else
	        	  {
   	         var resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value;

   	     /*  var res  = resultEntryTemplateValue.replace("$", " ", true);
	         var res2  = res.replace("#", " ", true);
	         var res3  = res2.replace("~", " ", true);
	        var res4  = res3.replace("`", " ", true);
	        resultEntryTemplateValue = res4.replace("@", " ", true);   	  */
   	      var check23 = checkReservedCahracters(resultEntryTemplateValue);
      	  if(check23==true){
  			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
  			return;
          	}else{
  			
              } 
        
	        resultEntryTemplateValue = removeSpecialCharacter(resultEntryTemplateValue);   
	             
		          name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-";
	          
	        	  
	        	  }
             /* var resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value;
                      name+='#'+resultValidationTemplateValue+'#'+values;*/
               
                   concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                   concatenateChkBoxVal+='@';

            
            }



        /* added by chandan */
	       k = 0;
	       for(k;k<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a").length;k++)
	    {

     	//alert("hyperlink");
     	
         var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
      //   alert(values);
         get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a");
           name=get_tags[k].name;
           id=get_tags[k].id;
         //  alert(id);
           if(id.includes("view@@")==false) // not to add file upload view hyperlink option by chandan 
                 {
         if(id.indexOf("template")!=-1)
               {
             
           splitTemplateValue=id.split("#");
           reqNO.push(splitTemplateValue[0])
           parameterValue=splitTemplateValue[3];
       /*   //alert(name);
           id=get_tags[k].id;
           alert(id);
           //alert("id for "+k+"    "+id);
           hiddenid="hiddenid"+id;
           defaultid="default"+hiddenid;
      	  typ=get_tags[k].type;
      	  alert(typ);
      	  hidddentext="hidden";
      	  checkboxcheck="checkbox";
      
        // alert("type is " + typ);
           if(typ!=hidddentext)
           {
          	 
         // 	 alert("not hidden");
           splitTemplateValue=id.split("#");
           reqNO.push(splitTemplateValue[0]);
            parameterValue=splitTemplateValue[3];
          //  alert("parameter value    "+parameterValue);
            parameterCode.push(parameterValue.substring(0, 5));
            parantParameter.push(parameterValue.substring(9,18));
          
              
        //  alert("parameterValue"+parameterValue+"reqno"+reqNO+"parameter"+parantParameter);
         
              if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value=="")
                     {
                            alert("Enter the field Focussed");
                            document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].focus();
                         return false;
                      }
         
                 // document.getElementsByName('resultEntryTemplateValue').value.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);       
            //  resultEntryTemplateValue.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);
            var resultValidationTemplateValue="";

            //chandan comment auto    
        /*     if(id.contains('auto'))    
          	  {
        
          	 if( document.getElementById(hiddenid).value!=null && document.getElementById(hiddenid).value!="" )
          	  resultValidationTemplateValue=document.getElementById(hiddenid).value;
          	 else
          		 resultValidationTemplateValue=document.getElementById(defaultid).value;
          	  } 
            else */
          /*   if(typ!=checkboxcheck)
                {
              resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value;
            */    //alert("1 " +name);
            if(id.includes("view@@")==false) // not to add file upload view hyperlink option by chandan 
            {
                  name+=id+"#"+resultEntryTemplateValue+"#"+values+"#hyperlink";
            // alert(name);    
               concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
               concatenateChkBoxVal+='@';
            }
               // alert(concatenateChkBoxVal);
            /*     }
           }
             */      
                  
               }
               } 
         } 
      


	       /* added by chandan */
	       k = 0;
	       for(k;k<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a").length;k++)
	    {

     	//alert("hyperlink");
     	
         var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
      //   alert(values);
         get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a");
           name=get_tags[k].name;
           id=get_tags[k].id;
         //  alert(id);
           if(id.includes("view@@")==false) // not to add file upload view hyperlink option by chandan 
                 {
         if(id.indexOf("template")!=-1)
               {
             
           splitTemplateValue=id.split("#");
           reqNO.push(splitTemplateValue[0])
           parameterValue=splitTemplateValue[3];
       /*   //alert(name);
           id=get_tags[k].id;
           alert(id);
           //alert("id for "+k+"    "+id);
           hiddenid="hiddenid"+id;
           defaultid="default"+hiddenid;
      	  typ=get_tags[k].type;
      	  alert(typ);
      	  hidddentext="hidden";
      	  checkboxcheck="checkbox";
      
        // alert("type is " + typ);
           if(typ!=hidddentext)
           {
          	 
         // 	 alert("not hidden");
           splitTemplateValue=id.split("#");
           reqNO.push(splitTemplateValue[0]);
            parameterValue=splitTemplateValue[3];
          //  alert("parameter value    "+parameterValue);
            parameterCode.push(parameterValue.substring(0, 5));
            parantParameter.push(parameterValue.substring(9,18));
          
              
        //  alert("parameterValue"+parameterValue+"reqno"+reqNO+"parameter"+parantParameter);
         
              if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value=="")
                     {
                            alert("Enter the field Focussed");
                            document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].focus();
                         return false;
                      }
         
                 // document.getElementsByName('resultEntryTemplateValue').value.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);       
            //  resultEntryTemplateValue.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);
            var resultValidationTemplateValue="";

            //chandan comment auto    
        /*     if(id.contains('auto'))    
          	  {
        
          	 if( document.getElementById(hiddenid).value!=null && document.getElementById(hiddenid).value!="" )
          	  resultValidationTemplateValue=document.getElementById(hiddenid).value;
          	 else
          		 resultValidationTemplateValue=document.getElementById(defaultid).value;
          	  } 
            else */
          /*   if(typ!=checkboxcheck)
                {
              resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value;
            */    //alert("1 " +name);
            if(id.includes("view@@")==false) // not to add file upload view hyperlink option by chandan 
            {
                  name+=id+"#"+resultEntryTemplateValue+"#"+values+"#hyperlink";
            // alert(name);    
               concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
               concatenateChkBoxVal+='@';

            } // alert(concatenateChkBoxVal);
            /*     }
           }
             */      
                  
               }
               } 
         } 
       
       }
 
  
   }
     

    // alert("Finally concatenateChkBoxValues For Save "+concatenateChkBoxVal);
     
     
 var ischeckkk=ischeckk(mymapvalue);
  	 //alert("ischeckkk"+ischeckkk);
       if(ischeckkk)
  		 {
  		 return null;
  		 }
       
     document.getElementsByName('resultValidationTemplateValueWithHash')[0].value=concatenateChkBoxVal;
     
     
     /* for(j;j<reqNO.length;j++)
            { 
            
     
     document.getElementsByName('resultEntryTemplateValue').value=resultEntryTemplateValue[j];
  alert(document.getElementsByName('resultEntryTemplateValue').value);
     document.getElementsByName('parameterCode').value=parameterCode[j];
  document.getElementsByName('parantParameterCode').value=parantParameter[j];
  document.getElementsByName('requisitionDNo').value=reqNO[j];
            } */
     if(count==0)
     {
       alert("Please select a Atleast One record");
       return false;
     }
      
     
     document.getElementsByName('showStatus')[0].value='0';
     document.getElementsByName('hmode')[0].value="MODIFY";
     document.forms[0].submit();
      
 return true;
        
  }
  
  

function getDetails(obj)
{
	
	
	document.getElementsByName('generationType')[0].value=obj.value;
	
	 if(document.getElementsByName("labCode")[0].value=="-1")
     {//testGroupWise
    	alert("Select Lab   Name ... ");
    	  document.getElementsByName("patientWise")[0].checked = true;
    	  document.getElementsByName("allPatient")[0].checked = false;
    	  document.getElementsByName("testGroupWise")[0].checked = false;
    	  document.getElementsByName("testWise")[0].checked = false;
    	  document.getElementsByName("sampleNoWise")[0].checked = false;
    	  document.getElementsByName("labNoWise")[0].checked = false;
	    document.getElementsByName("labCode")[0].focus();
	    return false;
     }
 //alert(document.getElementsByName('generationType')[0].value);
document.getElementsByName('hmode')[0].value='GETDETAILS';
	document.forms[0].submit();
	//document.getElementsByName('hmode')[0].value='GETTYPEWISEDETAIL';
	///document.forms[0].submit();
	  if(obj.value=="P")
		{
		  
document.getElementById("patientwise").style.display="";	
document.getElementById("patientwisename").style.display="";
document.getElementById("testwise").style.display="none";	
document.getElementById("labnowise").style.display="none";	
document.getElementById("labnowise2").style.display="none";	
document.getElementById("samplenowise").style.display="none";	
		}
	if(obj.value=="T")
		{
		   
	document.getElementById("testwise").style.display="";	
		document.getElementById("patientwise").style.display="none";	
		document.getElementById("patientwisename").style.display="none";
	document.getElementById("labnowise").style.display="none";	
	document.getElementById("labnowise2").style.display="none";	
	document.getElementById("samplenowise").style.display="none";	
		}
	if(obj.value=="L")
		{
		 
	document.getElementById("labnowise").style.display="";	
	document.getElementById("labnowise2").style.display="";	
		document.getElementById("patientwise").style.display="none";	
		document.getElementById("patientwisename").style.display="none";
	document.getElementById("testwise").style.display="none";	
	document.getElementById("samplenowise").style.display="none";	
		}
	if(obj.value=="S")
		{
		  
	document.getElementById("samplenowise").style.display="";	
		document.getElementById("patientwise").style.display="none";	
		document.getElementById("patientwisename").style.display="none";
	document.getElementById("testwise").style.display="none";	
	document.getElementById("labnowise").style.display="none";	
	document.getElementById("labnowise2").style.display="none";	
	
		}  
	
	if(obj.value=="TG")
	{
	  
		document.getElementById("testGrpWise").style.display="";
document.getElementById("samplenowise").style.display="none";	
document.getElementById("samplenowise2").style.display="none";	
document.getElementById("toLabSampleNo").style.display="none";	
	document.getElementById("patientwise").style.display="none";
	document.getElementById("patientwisename").style.display="none";
document.getElementById("testwise").style.display="none";	
document.getElementById("labnowise").style.display="none";	
document.getElementById("labnowise2").style.display="none";	

	}  
	
	
	if(obj.value=="AP")
	{
	  
		document.getElementById("testGrpWise").style.display="none";
	document.getElementById("samplenowise").style.display="none";	
	document.getElementById("samplenowise2").style.display="none";	
	document.getElementById("toLabSampleNo").style.display="none";	
	document.getElementById("patientwise").style.display="none";
	document.getElementById("patientwisename").style.display="none";
	document.getElementById("testwise").style.display="none";	
	document.getElementById("labnowise").style.display="none";	
	document.getElementById("labnowise2").style.display="none";	

	 
	labBased();
	}
}




var refrenceRangeColorCde = "";
//updated
<%
	Map<String,Map<String,List<String>>> mpDno=(Map<String,Map<String,List<String>>>)session.getAttribute("mapForRefRangeValidation");
	if(mpDno != null && mpDno.size()>0){
		String dno = (String)request.getAttribute("dnoForRangeValidation");
		String dnoListString = "";
		 Iterator itr=mpDno.keySet().iterator();
		String dnoList = "";
		if(dno != null && !dno.equals("")){
			List<String> dnoMapList = (List<String>)mpDno.get(dno);
			 //System.out.println("dnoLlisrt"+dnoMapList.get(0));
			 
			 for(int i=0; i<dnoMapList.size();i++){
				dnoListString += dnoMapList.get(i)+"@@@";	 
			}
			System.out.println("str"+dnoListString); 
	 	
		}
		
	
	
	if(!dnoListString.equals(""))
	{
		System.out.println("dnoListString"+dnoListString);
%>
refrenceRangeColorCde  = "<%=dnoListString%>";
document.getElementsByName('dnoForRangeChange')[0].value = "<%=dnoListString%>";

<%
	}}
	%>
	
function checkEntryType()
{

	if(refrenceRangeColorCde!=""){
		//alert(refrenceRangeColorCde);

		for(var k =0; k<refrenceRangeColorCde.split("@@@").length;k++){
		//	alert("red");
			document.getElementsByName(refrenceRangeColorCde.split("@@@")[k])[0].style.color="red";
		}
			
	}
	
call();
	if(document.getElementsByName("ispreview")[0].value=="2") // this check for preview only disable all inputs by chandan
	{
	
   $('input').attr('disabled', true);  
   $("a").attr('onclick',false);
   $('select').attr('disabled', true);  
   $('textarea').attr('disabled', true);
   // 
	}
	
	//changed by ashu
	
	if(document.getElementsByName("searchBy")[0].checked==true) 
	{
		//alert(document.getElementsByName("searchBy")[0].value);
	 // document.getElementsByName(searchBy)[0].value="1";
	 	document.getElementsByName("searchBy")[0].value=="1";
	 	document.getElementsByName("searchBy")[1].checked=false;
		
		showReqDate();
	}else{
		
		document.getElementsByName("searchBy")[0].value=="0";
	 	document.getElementsByName("searchBy")[0].checked=false;
	  	showCollDate();

	} 
	
	var genTypeValue=document.getElementsByName('generationType')[0].value;
	//alert("onload"+genTypeValue); showOnLoad
	 if(genTypeValue=='')
		 {
		 document.getElementById("showOnLoad").style.display="";	
		 document.getElementById("patient").checked = true;
		 }
	 
	 if(genTypeValue=="P")
		{
		  
document.getElementById("patientwise").style.display="";	
document.getElementById("patientwisename").style.display="";
document.getElementById("testwise").style.display="none";	
document.getElementById("labnowise").style.display="none";	
document.getElementById("labnowise2").style.display="none";	
document.getElementById("samplenowise").style.display="none";	
document.getElementById("samplenowise2").style.display="none";	
document.getElementById("toLabSampleNo").style.display="none";	
		}
	if(genTypeValue=="T")
		{
		   
	document.getElementById("testwise").style.display="";	
		document.getElementById("patientwise").style.display="none";	
		document.getElementById("patientwisename").style.display="none";
	document.getElementById("labnowise").style.display="none";	
	document.getElementById("labnowise2").style.display="none";	
	document.getElementById("samplenowise").style.display="none";	
	document.getElementById("samplenowise2").style.display="none";	
	document.getElementById("toLabSampleNo").style.display="none";	
		}
	if(genTypeValue=="L")
		{
		  
	document.getElementById("labnowise").style.display="";	
	document.getElementById("labnowise2").style.display="";	
		document.getElementById("patientwise").style.display="none";	
		document.getElementById("patientwisename").style.display="none";
	document.getElementById("testwise").style.display="none";	
	document.getElementById("samplenowise").style.display="none";	
	document.getElementById("samplenowise2").style.display="none";	
	document.getElementById("toLabSampleNo").style.display="";	
		}
	if(genTypeValue=="S")
		{
		  
	document.getElementById("samplenowise").style.display="";	
	document.getElementById("samplenowise2").style.display="";	
	document.getElementById("toLabSampleNo").style.display="";	
		document.getElementById("patientwise").style.display="none";
		document.getElementById("patientwisename").style.display="none";
	document.getElementById("testwise").style.display="none";	
	document.getElementById("labnowise").style.display="none";	
	document.getElementById("labnowise2").style.display="none";	
	
		}  
	
	
	if(genTypeValue=="TG")
	{
	  
		document.getElementById("testGrpWise").style.display="";
document.getElementById("samplenowise").style.display="none";	
document.getElementById("samplenowise2").style.display="none";	
document.getElementById("toLabSampleNo").style.display="none";	
	document.getElementById("patientwise").style.display="none";	
	document.getElementById("patientwisename").style.display="none";
document.getElementById("testwise").style.display="none";	
document.getElementById("labnowise").style.display="none";	
document.getElementById("labnowise2").style.display="none";	

	}  
	 
	if(genTypeValue=="AP")
	{
	  
		document.getElementById("testGrpWise").style.display="none";
	document.getElementById("samplenowise").style.display="none";	
	document.getElementById("samplenowise2").style.display="none";	
	document.getElementById("toLabSampleNo").style.display="none";	
	document.getElementById("patientwise").style.display="none";
	document.getElementById("patientwisename").style.display="none";
	document.getElementById("testwise").style.display="none";	
	document.getElementById("labnowise").style.display="none";	
	document.getElementById("labnowise2").style.display="none";	
	}
	}



function popupCallCanned()
{
	 
	 
	 openAutocompletePopup('CANNED');
		var data=getTheLabCannedList('CANNED');
		//alert("ajax data     "+ data);
		callCannedList(data);
		 document.getElementById("automplete-4").focus();
}

function popupCallMacro()
{
	
	
	openAutocompletePopup('MACRO');
	var data=getTheLabCannedList('MACRO');
	callMacroList(data);
	 document.getElementById("automplete-m").focus();
	
	}
	
	
	


function printReport(name)
{
	
	//alert(name);
	document.getElementsByName('fileName')[0].value=name;
//document.getElementsByName('hmode')[0].value='PRINTREPORT';
	//document.forms[0].action="/AHIMS/investigationDesk/viewInvestigation.cnt"; 

//	document.forms[0].submit();
	
	
	var mode='PRINTREPORT';
var reportName = name;


var url = '/HISInvestigationG5/new_investigation/invResultEntryTemplateTile.cnt?hmode='+mode+"&fileName="+name;

AddRowToTableAddReportValues(url);
popup('popUpDiv');



}




function AddRowToTableAddReportValues(fileurl) {

// alert("working fine"+finalMadCode);

//var mode = "SHOWPATDETAILS";

//var url ='/HISInvestigationG5/new_investigation/invResultReportPrinting.cnt?hmode='+mode

var nRow = 0;
var tableObj = document.getElementById('reportTable');
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

td1.innerHTML = "<iframe id='setPdf' src='"+ fileurl + "' width='100%' height='450px' type='application/pdf'    ></iframe> ";
td1.className = 'tdfont';
td1.colspan = "1";

tabRow.appendChild(td1);

//document.forms[0].numberOfRow.value=numRows;
}



	
</script>
<script type="text/javascript">
        var isChecked = false;
        function allSelected() 
        {

        	// this line is for toggle the check
            isChecked = !isChecked;
            //below line refers to 'jpCheckbox' class
            $('input:checkbox.jpCheckbox').prop('checked',isChecked);

        	document.getElementById("nextDiv").style.display=""; 
        	document.getElementById("nextDiv1").style.display=""; 
        	
            //OR,
            //$('input:checkbox.jpCheckbox').attr('checked','checked');
        }
    </script>
    
    
    
    
     <!-- keypad shortcut functionality -->
 
 
<script type="text/javascript">

$(document).ready(function() {

	var isCtrl=false;
	var isShift=false;
	
	$(document).keydown(function(e)
			{

		
		if(e.which==16)
			isShift=true;
		if(e.which==17)
			isCtrl=true;
	
	if(e.which!=17 && e.which!=120 && e.which!=123)//120 f9 key
		isCtrl=false;
	
	if(e.which!=16 && e.which!=120 && e.which!=123)//120 f9 key
		isShift=false;
	
	

	
	if(isCtrl && e.which==120)
		{
		
		//document.getElementById('cannedField').style.display="";
		popupCallCanned();
		
		isCtrl=false;
		isShift=false;
		
		}
		
	if(isShift && e.which==120)
	{
	
	//	document.getElementById('macroField').style.display="";
		popupCallMacro();
		
	isCtrl=false;
	isShift=false;
	
	}
	
	
	if(isCtrl && e.which==123)
	{
	
	//document.getElementById('cannedField').style.display="";
	autocompleteBox_close();
	
	isCtrl=false;
	isShift=false;
	
	}
	
if(isShift && e.which==123)
{

//	document.getElementById('macroField').style.display="";
	autocompleteBox_close_macro();
	
isCtrl=false;
isShift=false;

}

		
		});
			
			
	
  
});



//autocomplete functions for canned and macro
function callCannedList(data){

//canned
 $(function() {
 	      
 	           var cannedCodes=data;
          
         
            
           /*  var obj= JSON.parse(cannedCodes);
            cannedCodes=obj;
			
          var details="";  
            alert(cannedCodes.length); 
            
            for(var kk=0;kk<cannedCodes.length;kk++)
          {   	alert("label :     "+cannedCodes[kk].label);
           
          details+=cannedCodes[kk].label+"#"+cannedCodes[kk].value+"@";
          
          } 
            */ 
            
            
            //new
            
            
            
            
            var splitCannedDetails=cannedCodes.split("@");
            var cannedDetails="";
            
            if(splitCannedDetails.length-1!=0)
   		 { 
            	 document.getElementsByName("cannedDataCount")[0].value=splitCannedDetails.length-1;
            	 deleteTableCanned();
            	for(i=0;i<splitCannedDetails.length-2;i++)
   		 {
   		
   		
   	
   		 AddRowToTableAddMoreValue(splitCannedDetails[i]);
   		cannedDetails+="{\"label\":\""+splitCannedDetails[i].split("#")[0]+"\" ,  \"value\": \""+splitCannedDetails[i].split("#")[0]+"\" }";
   		cannedDetails+=","; 
   		
   		  }
            	
            	
            	 AddRowToTableAddMoreValue(splitCannedDetails[i]);
            		cannedDetails+="{\"label\":\""+splitCannedDetails[i].split("#")[0]+"\" ,  \"value\": \""+splitCannedDetails[i].split("#")[0]+"\" }";
          //  	alert(cannedDetails.length);
            	
            	cannedDetails="["+cannedDetails.toString()+"]";
            	//alert(cannedDetails.length);
            	cannedDetails=cannedDetails.toString();
           
            	
            	
   		 }
   	 else
   		 {
   		
   			 alert("No Canned Data Is Mapped For  "+document.getElementsByName("chkLabName")[0].value+"   Lab");
   		
   		 
   		 }
            
            
            
            
            
            
            
            
          
            
            
            
            
           
         //  alert("inside jquery canned details label valiu      "+cannedDetails);
            var obj= JSON.parse(cannedDetails);
            cannedCodes=obj;
            
       
        	
        	
 	           setInterval(function() {
             $( "#automplete-4" ).autocomplete({
                 source: cannedCodes,
                 select: function(event, ui) { 
                     $('#hiddenid4').val(ui.item.value); 
                     event.preventDefault(); 
                     $("#automplete-4").val(ui.item.label); 
                 },

             focus: function(event, ui) { 
                    event.preventDefault(); 
                    $("#automplete-4").val(ui.item.label);}
              });
             }, 1000);
          });
         
}
         
//macro
function callMacroList(data){
 $(function() {
 	      
 	           var macroCodes=data;
 	           
 	           
 	/*           var obj1= JSON.parse(macroCodes);
 	         macroCodes=obj1;
 	         
 	        var details="";  
            alert(macroCodes.length);            
            for(var kk=0;kk<macroCodes.length;kk++)
          {   	alert("label :     "+macroCodes[kk].label);
           
          details+=macroCodes[kk].label+"#"+macroCodes[kk].value+"@";
          
          } 
             */
                      
            var splitCannedDetails=macroCodes.split("@");
             var cannedDetails="";
        	 if(splitCannedDetails.length-1!=0)
        		 { 
        		 document.getElementsByName("macroDataCount")[0].value=splitCannedDetails.length-1;
        		 deleteTableMacro();
        	 for(i=0;i<splitCannedDetails.length-2;i++)
        		 {  		    
        		
        		    	AddRowToTableAddMoreValueMacro(splitCannedDetails[i]);
        		   
        		    	
        		    	cannedDetails+="{\"label\":\""+splitCannedDetails[i].split("#")[0]+"\" ,  \"value\": \""+splitCannedDetails[i].split("#")[1]+"\" }";
        		   		cannedDetails+=","; 
        		 	  
        		 }
        	 
        	 AddRowToTableAddMoreValueMacro(splitCannedDetails[i]);
        	 cannedDetails+="{\"label\":\""+splitCannedDetails[i].split("#")[0]+"\" ,  \"value\": \""+splitCannedDetails[i].split("#")[1]+"\" }";
        	
         	cannedDetails="["+cannedDetails.toString()+"]";
         	
         	
        		 }
        	 else
        		 {
        		
        		
        			 alert("No Macro Data Is Mapped For  "+document.getElementsByName("chkLabName")[0].value+"   Lab");
        		 
        		 }
        	 
        	 
        	// alert(cannedDetails);
        	 
        	   var obj= JSON.parse(cannedDetails);
        	   macroCodes=obj;
        	 
        	 
        	 
            
 	           setInterval(function() {
             $( "#automplete-m" ).autocomplete({
                 source: macroCodes,
                 select: function(event, ui) { 
                     $('#hiddenidm').val(ui.item.value); 
                     event.preventDefault(); 
                     $("#automplete-m").val(ui.item.label); 
                 },

             focus: function(event, ui) { 
                    event.preventDefault(); 
                    $("#automplete-m").val(ui.item.label);}
              });
             }, 1000);
          });
         
}



function showLegends(){
	  document.getElementById("divLegends").style.display="block"; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}




$(document).ready(function()
		{
for(var inst in CKEDITOR.instances) {

//alert(CKEDITOR.instances[inst].name);


CKEDITOR.instances[inst].on("focus",function()
	{
	document.getElementsByName("currentElement")[0].value="ckeditor";
	document.getElementsByName("currentElementName")[0].value=this.name;
	document.getElementsByName("editorName")[0].value=this.name;
	}
		)



}
		});


//changed by ashu

function showReqDate()
{
	document.getElementById("divfromDateControl").style.display="";
	document.getElementById("divfromDate").style.display="";
	document.getElementById("divToDate").style.display="";
	document.getElementById("divToDateControl").style.display="";
	
	document.getElementById("divfromCollDateControl").style.display="none";
	document.getElementById("divfromCollDate").style.display="none";
	document.getElementById("divToCollDateControl").style.display="none";
	document.getElementById("divToCollDate").style.display="none";

	
}

function showCollDate()
{
	/* document.getElementById("divfromDateControl").style.display="none";
	document.getElementById("divfromDate").style.display="none";
	document.getElementById("divToDate").style.display="none";
	document.getElementById("divToDateControl").style.display="none";
	
	document.getElementById("divfromCollDateControl").style.display="";
	document.getElementById("divfromCollDate").style.display="";
	document.getElementById("divToCollDateControl").style.display="";
	document.getElementById("divToCollDate").style.display=""; */
}

function callGetDetails()
{
	 document.getElementsByName('hmode')[0].value='GETDETAILS';
	 document.forms[0].submit();
}

function updateSelctBox(){
	document.getElementsByName('sampleAreaCode')[0].setAttribute('class', 'form-control');
	document.getElementsByName('sampleAreaCode')[0].style.width = "250px"; 
	document.getElementsByName('labCode')[0].setAttribute('class', 'form-control');
	document.getElementsByName('labCode')[0].style.width = "250px";
	document.getElementsByName('searchBy')[0].setAttribute('class', 'form-control');
	document.getElementsByName('searchBy')[0].style.width = "250px";
	
}

</script>

   
   
   
    
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
<body onload="updateSelctBox(); checkEntryType();">
<html:form action="/invResultReValidationTemplateTileAdvanced">
	<html:hidden name="InvResultValidationFB" property="hmode" />
	<html:hidden name="InvResultValidationFB" property="fileuploaddata" />
						<html:hidden name="InvResultValidationFB" property="fileuploaddatabase64" />
		
		
	<html:hidden name="InvResultValidationFB" property="isPatDetailPage" />
	<html:hidden name="InvResultValidationFB" property="selectedCheckbox" />
	 <html:hidden name="InvResultValidationFB" property="showStatus" />
	 <html:hidden name="InvResultValidationFB" property="currentPage" />
	  <html:hidden name="InvResultValidationFB" property="patCRNo" />
	  	  <html:hidden name="InvResultValidationFB" property="sysDate" />
	   <html:hidden name="InvResultValidationFB" property="getSearchType" />
	  	  	   <html:hidden name="InvResultValidationFB" property="generationType"    />
	  	  	     <html:hidden name="InvResultValidationFB" property="onLoadValue"    />
	  	  	     <input type="hidden" name="selectValuemapping" value=""/>
	  	  	     <input type="hidden" name="hyperLinkTableSession" value=""/>
	  	  	     	<!-- canned popup  -->
	
	<div id="blanketcanned" style="display: none;"></div>
 		<div id="popUpDiv5canned"  style="display:none;" align="center">  
		 
		<his:TitleTag name="Canned Details">
		<img src='/HISInvestigationG5/hisglobal/css/close.png'  onClick="closecanned();">
  		</his:TitleTag>
  			
  			
 		<table width="100%" id="allInstructionscanned">
 	             
 		<tr>
 		<td width="25%" class="tdfont">
			        <div align="right">
			             <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								Canned Code
						 </font>
				     </div>
			      </td>
 		<td width="25%" class="tdfont">
			        <div align="left">
			             <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<html:text property="cannedCode" name="InvResultValidationFB"></html:text>
						 </font>
				     </div>
			      </td>
						
		</tr>
		<tr>
 		<td width="25%" class="tdfont">
			        <div align="right">
			             <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								Canned Name
						 </font>
				     </div>
			      </td>
 		<td width="25%" class="tdfont">
			        <div align="left">
			             <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<html:text property="cannedName" name="InvResultValidationFB"></html:text>
                           <input type="hidden" name="cannedvall" >						
						 </font>
				     </div>
			      </td>
						
		</tr>
 		
 		</table>
 		                             
<img src='/HISInvestigationG5/hisglobal/images/btn-sv.png'  onClick="closeInstructionscanned();"> 		                        
				

		</div>
	     
	     
	    <!--  //end -->
	<%!
		boolean readOnly;
	%>
	<% this.readOnly=false;%>
	<logic:equal name="InvResultValidationFB" property="hmode" value="VIEW">
		<% this.readOnly=true; %>
	</logic:equal>
		<%-- <his:TitleTag name="Result Re Validation Process">
			<his:insertDateTag/>
		</his:TitleTag> --%>
		
		<logic:equal name="InvResultValidationFB" property="showStatus" value="0">
		<logic:present name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO %>">
		<logic:notEqual name="InvResultValidationFB" property="isSampleAreaSelected" value="1">
			<div class="container">
  					<div class="panel panel-primary">
				      <div class="panel-heading" style="background-color :#1E90FF">
				      		<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				      		Please select a collection area :
				      		</font>
				      </div>
				      <div class="panel-body" style="background-color: #d9e8f7">
						<table width="100%" >
				 		<tr align="right">
					 			<td>
					 				<%-- <font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
													<strong><bean:message key="sampleColl"/>&nbsp;</strong>
									</font> --%>
									<h6 class="text-primary font-weight-bold">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<strong><bean:message key="sampleColl"/>&nbsp;</strong>
								</font>
								</h6>
					 			</td>
					 			<td align="left">
					 				<span class="dropdown">
						 				<html:select    name="InvResultValidationFB" property="sampleAreaCode" tabindex="1"  onchange="showSearchDiv(this)">					
											<bean:define id="patSampleCollection" type="java.util.List" name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>"></bean:define>
																								<%-- <%if(patSampleCollection.size()>1){ %>
													<html:option value="-1">Select Value</html:option>
												<%} %> --%>
												<%
												int patTypeCount = 0;
												if(patSampleCollection.size()>1){
													
													for(int l = 0; l<patSampleCollection.size();l++){
														String obj = patSampleCollection.get(l).toString();
														System.out.println("remaining = "+obj.split("#")[1].split("]")[0]);
														if(obj.split("#")[1].split("]")[0].equals("2")){
															patTypeCount++;
															System.out.println("count + 1");
														}
													}
											%>
											<html:option value="-1">Select Value</html:option>
											<%
												}
												if(patTypeCount == patSampleCollection.size()){
													System.out.println("all");	
											%>
												<html:option value="-2#2">All</html:option>
											<% 
												}
												else{
											%>
											<html:option value="-2">All</html:option>
											<%
												}
											%>
												
 											<%--  <html:option value="-2">All</html:option> --%>
											<html:options collection="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>" property="value" labelProperty="label"  />
										</html:select> 
										</span>
								</td>
					 			<!-- <td class="tdfont" width="25%"></td>
					 			<td class="tdfont" width="25%"></td> -->
				 			 </tr>	
				 			 </table>
				 			 </div>
				    </div>
				</div>
		</logic:notEqual>
		<logic:equal name="InvResultValidationFB" property="isSampleAreaSelected" value="1">
		<div class="panel-group">
    					<div class="panel panel-primary">
							<div class="panel-heading" style="background-color: #1E90FF;" align="right">
				<table>
				<tbody>
					<tr>	
						 <td>
				     		 <div align="right">
				              	<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									*
							   	</font> 
							    <font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<strong><bean:message key="ResultValidation"/>&nbsp;</strong>
							   	</font>
					     	</div>
				 		</td>
			      		<td>
			      			<html:radio name="InvResultValidationFB"   tabindex="1" property="newEntry" value="1"  ></html:radio>
								<font
											color="#ffffff" size="2" 
											face="Verdana, Arial, Helvetica, sans-serif">
											<strong>
												<bean:message key="validate"/>&nbsp;
											</strong>
								</font>
							<html:radio name="InvResultValidationFB" tabindex="1" property="newEntry" value="2" ></html:radio>
							<font
											color="#ffffff" size="2" 
											face="Verdana, Arial, Helvetica, sans-serif"><strong>
								<bean:message key="modifyValidate"/>&nbsp;
								</strong></font>
			     		</td>
					</tr>
				</tbody>
			</table>
			</div>
		</div>
	</div>
		<%-- </logic:equal>
		</div>
		 --%>
		<%-- <his:ContentTag> --%>
		<div>
		  <%
			  String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
         %>
      <bean:define name="InvResultValidationFB" property="fromDate" id="frDate" type="java.lang.String"/>
	  <bean:define name="InvResultValidationFB" property="toDate" id="tDate" type="java.lang.String"/>  
	  <bean:define name="InvResultValidationFB" property="fromCollDate" id="frCollDate" type="java.lang.String" />
	  <bean:define name="InvResultValidationFB" property="toCollDate" id="tCollDate" type="java.lang.String" />     
	     
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
		  
		  if(frCollDate==null||frCollDate.equalsIgnoreCase(""))
	         {  
	        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
	        	frCollDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
	         }
	  
		if(tCollDate==null||tCollDate.equalsIgnoreCase(""))
		{  
			  	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
			  	tCollDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		}
		  
    	%> 
    	<%--  <logic:equal name="InvResultValidationFB" property="showStatus" value="0">
    		<logic:present name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO %>">
					<logic:notEqual name="InvResultValidationFB" property="isSampleAreaSelected" value="1">
				
				 	</logic:notEqual>
  			      <!-- end chandan -->
					<logic:equal name="InvResultValidationFB" property="isSampleAreaSelected" value="1">
				 <style>
  			 	.newGo{
					height: 45px;
					width: 45px;
					display: block;
  					margin-left: auto;
  					margin-right: auto;
				}
  			 </style>
  			 <div class="container" style="width: 100%;" > --%>
			<table class="table table-bordered" style="width: 100%;">
			    <tbody>
			    <tr>
			    <td>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<strong>
							<bean:message key="sampleColl" />
						</strong>&nbsp;
						</font>
				</td>
				<td>
			             <!-- <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font>  -->
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong><bean:message key="LabType"/>&nbsp;</strong>
						 </font>
				    
			      </td>
			     
			      <td>
			     	<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						   </font> 
						    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<strong><bean:message key="searchBy"/>&nbsp;&nbsp;&nbsp;</strong>
				   </font>
				 </td>
				<td>
        			<div id='divfromDate' style='<%=fromDateLabel %>' align="left">
	        			<!-- <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font> -->
	        			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<strong><bean:message key="fromDate"/>&nbsp;</strong>
						</font>
        			</div>
				</td>
				<td>
        			<div id='divToDate' style='<%=toDateLabel %>' align="left">
	        			<!-- <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font> -->
	        			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<strong><bean:message key="toDate"/>&nbsp;</strong>
						</font>
        			</div>
				</td>
			<%-- <td rowspan="2" >
								<% 
									UserVO uservoNew=ControllerUTIL.getUserVO(request);
			    					String hCode=uservoNew.getHospitalCode();
									%>
								<img src='media/images/ic_launcher_go.png' class="newGo" id="gob"
										style="cursor: pointer"
										onkeypress="if(event.keyCode==13) onClickGO('<%=hCode%>')"
										onclick="onClickGO('<%=hCode%>')" tabindex="1">
			</td> --%>
			<td style="vertical-align : middle;text-align:center;">
								<% 
									UserVO uservoNew=ControllerUTIL.getUserVO(request);
			    					String hCode=uservoNew.getHospitalCode();
									%>
								<%-- <img src='media/images/ic_launcher_go.png' class="newGo" id="gob"
										style="cursor: pointer"
										onkeypress="if(event.keyCode==13) onClickGO('<%=hCode%>')"
										onclick="onClickGO('<%=hCode%>')" tabindex="1"> --%>
										 <a href="#" onclick="onClickGO('<%=hCode%>')" 
										 	onkeypress="if(event.keyCode==13) onClickGO('<%=hCode%>')"
										 	class="btn btn-success btn-sm">
											<span class="glyphicon glyphicon-search"></span> Search 
        								</a>
							</td>
 		</tr>
 		<tr>
 			<td style="vertical-align : middle;text-align:left;">
 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="InvResultValidationFB" property="sampleAreaName"/>
				</font>
				<html:hidden name="InvResultValidationFB" property="sampleAreaCode"/>
			</td>
 			<td>
			      <logic:present name="<%=InvestigationConfig.LAB_COMBO_FOR_RESULT_ENTRY%>">
			     <html:select name="InvResultValidationFB" property="labCode"    tabindex="1"  onchange="labBased()">
				       					<html:option value="%">All</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.LAB_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				   </html:select>
				  </logic:present>
			  </td>
			  <td>
			    <%--  <bean:message key="reqNoDate"/>&nbsp; 
					<html:radio name="InvResultValidationFB" property="searchBy" onclick="showReqDate();callGetDetails();"  value="1"></html:radio>
					<bean:message key="sampleCollDateTest"/>&nbsp;
					<html:radio name="InvResultValidationFB" property="searchBy" onclick="showCollDate();callGetDetails();"  value="0"></html:radio>
			   --%>
			   <html:select name="InvResultValidationFB" property="searchBy" tabindex="1" onchange="showReqDate();callGetDetails();" >
					<html:option value="1"><bean:message key="reqNoDate"/></html:option>
					<html:option value="0">Acceptance Date</html:option>
			</html:select>
			  </td>
			  <td>
	    		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left">	               		 
					<his:date  name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
				</div>
			</td>
			<td>
	    		<div id='divToDateControl' style='<%=toDateControl %>'align="left">	               		 
					<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
				</div>
			</td>
			<td style="vertical-align : middle;text-align:center;">
								 <a href="#" onclick="cancelFunc()" 
										 	onkeypress="if(event.keyCode==13) cancelFunc()"
										 	class="btn btn-danger btn-sm">
											<span class="glyphicon glyphicon-remove-sign"></span> Cancel 
        								</a>
							</td>
			
		</tr>
 		</tbody>
		</table>
				</div>	
					<table style="display: none;">
					<tr style="display: none;">
						<td class="tdfont" width="25%">
							<div id='divfromCollDate' style='<%=fromDateLabel %>' align="right">
								<font color="#FF0000" size="1"
									face="Verdana, Arial, Helvetica, sans-serif\"> </font><font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									<!-- updated by krishnan nema on 01/10/2018 -->
									<%-- <bean:message
										key="fromSampleCollDate" />&nbsp; --%>
										<bean:message
										key="fromSampleCollDateAcceptance" />&nbsp;
								</font>
							</div>
						</td>
						<td class="tdfont" width="25%">
							<div id='divfromCollDateControl' style='<%=fromDateControl %>'
								align="left">
								&nbsp;&nbsp;&nbsp;
								<his:date name='fromCollDate' dateFormate="%d-%b-%Y"
									value="<%=frCollDate%>" />
							</div>
						</td>
						<td class="tdfont" width="25%">
							<div id='divToCollDate' style='<%=toDateLabel %>' align="right">
								<font color="#FF0000" size="1"
									face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									<!-- updated by krishnan nema -->
									<%-- <bean:message
										key="toSampleCollDate" />&nbsp; --%>
										<bean:message
										key="toSampleCollDateAcceptance" />&nbsp;
										
								</font>
							</div>
						</td>
						<td class="tdfont" width="25%">
							<div id='divToCollDateControl' style='<%=toDateControl %>'
								align="left">
								&nbsp;&nbsp;&nbsp;
								<his:date name='toCollDate' dateFormate="%d-%b-%Y"
									value="<%=tCollDate%>" />
							</div>
						</td>
					</tr> 
		
 		 <tr style="display: none;">
			    <td width="25%" class="tdfont">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="ResultEntryType"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="25%" class="tdfont" colspan="3">
			      <div align="left"   >                                                                                                           
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvResultValidationFB" property="generationType" value="P">
						 
						<input type="radio"  name="patientWise"  onclick="getDetails(this)" checked="checked" value="P" />
						</logic:equal>
						<logic:notEqual name="InvResultValidationFB" property="generationType" value="P">
						<input type="radio" name="patientWise" id="patient" onclick="getDetails(this)"   value="P" />
						</logic:notEqual>
						<bean:message key="PatientWise"/>
						
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvResultValidationFB" property="generationType" value="T">
						<input type="radio" name="testWise" onclick="getDetails(this)" checked="checked"  value="T" />
						</logic:equal>
						<logic:notEqual name="InvResultValidationFB" property="generationType" value="T">
					<input type="radio" name="testWise" onclick="getDetails(this)"   value="T" />
						</logic:notEqual>
						
						<bean:message key="testWise"/>
	     		 
	     		        <logic:notEqual name="InvResultValidationFB" property="patientType" value="2">                                                                                       
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvResultValidationFB" property="generationType" value="S">
						<input type="radio" name="sampleNoWise"  onclick="getDetails(this)"  checked="checked" value="S" />
						</logic:equal>
						<logic:notEqual name="InvResultValidationFB" property="generationType" value="S">
						<input type="radio" name="sampleNoWise"  onclick="getDetails(this)"  value="S" />
						</logic:notEqual>
						
						
						<bean:message key="sampleNoWise"/>
						</logic:notEqual>
						
						
													
	     		  
	     		        <logic:notEqual name="InvResultValidationFB" property="patientType" value="2">                                                                                               
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvResultValidationFB" property="generationType" value="L">
						<input type="radio" name="labNoWise"  onclick="getDetails(this)" checked="checked" value="L" />
						</logic:equal>
						<logic:notEqual name="InvResultValidationFB" property="generationType" value="L">
						<input type="radio" name="labNoWise"  onclick="getDetails(this)"  value="L" />
						</logic:notEqual>
						
						
						<bean:message key="labNOWise"/>
						</logic:notEqual>
						
						<logic:equal name="InvResultValidationFB" property="patientType" value="2">                                                                                               
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvResultValidationFB" property="generationType" value="L">
						<input type="radio" name="labNoWise"  onclick="getDetails(this)" checked="checked" value="L" />
						</logic:equal>
						<logic:notEqual name="InvResultValidationFB" property="generationType" value="L">
						<input type="radio" name="labNoWise"  onclick="getDetails(this)"  value="L" />
						</logic:notEqual>
						
						
						<bean:message key="labNOWiseAcc"/>
						</logic:equal>
						
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvResultValidationFB" property="generationType" value="TG">
						<input type="radio" name="testGroupWise"  onclick="getDetails(this)" checked="checked" value="TG" />
						</logic:equal>
						<logic:notEqual name="InvResultValidationFB" property="generationType" value="TG">
						<input type="radio" name="testGroupWise"  onclick="getDetails(this)"  value="TG" />
						</logic:notEqual>
						
						
						<bean:message key="testGrpWise"/>
						
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvResultValidationFB" property="generationType" value="AP">
						<input type="radio" name="allPatient"  onclick="getDetails(this)" checked="checked" value="AP" />
						</logic:equal>
						<logic:notEqual name="InvResultValidationFB" property="generationType" value="AP">
						<input type="radio" name="allPatient"  onclick="getDetails(this)"  value="AP" />
						</logic:notEqual>
						
						
						<bean:message key="allPatient"/>
	     		   </div>  
	     		    
			     </td>
			     
			     </tr>
 		     <tr style="display: none;">
			    <td width="25%" class="tdfont">
			    <div align="right" id="showOnLoad" style="display:none">
			    <bean:message key="crNO"/>&nbsp;
			    </div>
			        <div align="right" >
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 <logic:equal name="InvResultValidationFB" property="generationType" value="P">
								<bean:message key="crNO"/>&nbsp;
								</logic:equal>
								 <logic:equal name="InvResultValidationFB" property="generationType" value="T">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="testName"/>&nbsp;
								</logic:equal>
								<!-- updated by krishnan nema on 01/10/2018 -->
								
								<%-- <logic:equal name="InvResultValidationFB" property="generationType" value="L">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="fromLabNo"/>&nbsp;
								</logic:equal> --%>
								
								<logic:notEqual name="InvResultValidationFB" property="patientType" value="2">
								 <logic:equal name="InvResultValidationFB" property="generationType" value="L">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="fromLabNo"/>&nbsp;
								</logic:equal>
								</logic:notEqual>
								
								<logic:equal name="InvResultValidationFB" property="patientType" value="2">
								 <logic:equal name="InvResultValidationFB" property="generationType" value="L">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="fromAccessionNo"/>&nbsp;
								</logic:equal>
								</logic:equal>
								
								
								 <logic:equal name="InvResultValidationFB" property="generationType" value="S">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="fromSampleNo"/>&nbsp;
								</logic:equal>
								<logic:equal name="InvResultValidationFB" property="generationType" value="TG">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="tstgrpName"/>&nbsp;
								</logic:equal>
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
			      <td width="25%" class="tdfont">
			      <div align="left" id="patientwise" >                                                                                                           
					 &nbsp;&nbsp;&nbsp; <input type="text" id="textBoxCss" name="tempPatCRNo" value="<%=val%>" maxlength="15" size="20"  onkeypress="return validateNumeric(event,this)" tabindex="1"> 
				   </div>  
				   
				    <logic:present name="<%=InvestigationConfig.TEST_WISE_COMBO_FOR_RESULT_ENTRY%>">
			      <div align="left" id="testwise" style="display: none;">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="InvResultValidationFB" property="testWiseCode"    tabindex="1"   >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.TEST_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
				  
				   <logic:present name="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>">
			      <div align="left" id="samplenowise" style="display: none;">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="InvResultValidationFB" property="fromSampleNo"    tabindex="1"  >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
				  
				   <logic:present name="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>">
			      <div align="left" id="labnowise" style="display: none;">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="InvResultValidationFB" property="fromLabNo"    tabindex="1"   >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
				    
				    
				     <logic:present name="<%=InvestigationConfig.TEST_GROUP_COMBO_FOR_RESULT_ENTRY%>">
			      <div align="left" id="testGrpWise" style="display: none;">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="InvResultValidationFB" property="testGroupCodeWise"    tabindex="1"   >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.TEST_GROUP_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
				   
				  
			     </td>
			     <td width="25%" class="tdfont">
			     
			     <div align="left" id="patientwisename" >                                                                                                           
					 Search Name&nbsp;&nbsp;&nbsp;<input type="text" id="textBoxCss" name="tempPatName"  maxlength="20" size="20"  onkeypress="return validateAlphabetsOnly(event,this)" tabindex="1">
				   </div> 
				   
				   
			        <div align="right" id="toLabSampleNo"  style="display: none;">
			         <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
			         			<!-- updated by krishnan nema on 01/10/2018 -->
			         			<%-- <logic:equal name="InvResultValidationFB" property="generationType" value="L">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="toLabNo"/>&nbsp;
								</logic:equal> --%>
								
			     				<logic:notEqual name="InvResultValidationFB" property="patientType" value="2">
			     				<logic:equal name="InvResultValidationFB" property="generationType" value="L">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="toLabNo"/>&nbsp;
								</logic:equal>
								</logic:notEqual>
								
								<logic:equal name="InvResultValidationFB" property="patientType" value="2">
			     				<logic:equal name="InvResultValidationFB" property="generationType" value="L">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="toAccessionNo"/>&nbsp;
								</logic:equal>
								</logic:equal>
								
								 <logic:equal name="InvResultValidationFB" property="generationType" value="S">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="toSampleNo"/>&nbsp;
								</logic:equal>
								</font>
			      </div>
			      </td>
			      
			      <td width="25%" class="tdfont">
			       <logic:present name="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>">
			      <div align="left" id="labnowise2" style="display: none;">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="InvResultValidationFB" property="toLabNo"    tabindex="1"   >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
				  
				  
				   <logic:present name="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>">
			      <div align="left" id="samplenowise2" style="display: none;">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="InvResultValidationFB" property="toSampleNo"    tabindex="1"  >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
			     </td>
			     </tr>
			     </table>
			    
			     </logic:equal>
			     </logic:present>
			     </logic:equal>
			     <%-- </his:ContentTag> --%>
			     <!-- </div> -->
     <%boolean flag=false; %>
  	 <%
				//Pagination Logic
					/* PaginationFB fbPage= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage);
					fbPage.setCurrentPage(((InvResultValidationFB)request.getAttribute("InvResultValidationFB")).getCurrentPage());
					fbPage.setObjArrName(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
					fbPage.setAppendInTitle("List ");
					int maxRecord=15;
					fbPage.setMaxRecords(maxRecord); */
				 
				 %>
				
	<bean:define name="InvResultValidationFB" property="startDisplay" id="startDisplay" type="java.lang.Integer" />
 	<bean:define name="InvResultValidationFB" property="hideDisplay" id="hideDisplay" type="java.lang.Integer" />
  
	<logic:equal name="InvResultValidationFB" property="showStatus" value="0">
		<%-- <his:PaginationTag name="fbPagination"></his:PaginationTag> --%>
		<logic:present name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">
			<% flag=true; %>
			<logic:empty name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">
				<center>
				<font color="red" size="4">
			<bean:message key="noRecord"/></font></center>
			</logic:empty>
			
			<logic:notEmpty name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">
			<script>
			function format(value) {
				//alert(value);
			      return value;
			  }
			</script>
			<style>
				td.details-control {
    				background: url('media/images/details_open.png') no-repeat center center;
   					cursor: pointer;
				}
				tr.shown td.details-control {
   				 	background: url('media/images/details_close.png') no-repeat center center;
				}
			</style>
			<div class="panel-group">
					<div class="panel panel-primary">
						<div class="panel-body">
			<table id="table2" class="display cell-border responsive">
			<thead style="background-color: #1E90FF">
				<tr>
					<th width="3%" align="left"  >	
						<b> 
							<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<input type="checkbox" id="selectAllCheckbox" onclick="allSelected()" />
							</font>
	                  	</b>
					</th>
					<th width="12%" align="left"   >
						<b>
							<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						 		<bean:message key="crNO"/> 
						 	</font>
						</b>
					</th>
					<th width="10%" align="left"  >
						<b>
							<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="patientName"/>
							</font>
						</b>
					</th>
					<th width="8%" align="left"  >
						<b> 
							<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="age/gender"/>
							</font>
						</b>
					</th>
					<th width="10%" align="left"  >
						<b>
							<font color="ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="departmentunit"/>
							</font>
						</b>
					</th>
					<th width="10%" align="left">
						<b> 
							<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="TestName"/>
							</font>
						</b>
					</th>
					<th width="10%" align="left"  >
						<b>
							<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="labName"/>
							</font>
						</b>
					</th>
					
					
					<!-- updated by krishnan nema on 28/09/2018 -->
					<th width="10%" align="left"  >	
						<logic:notEqual name="InvResultValidationFB" property="patientType" value="2">                                                                        
							<b> 
								<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="sampLabNo"/>
								</font>
							</b>
						</logic:notEqual>
						<logic:equal name="InvResultValidationFB" property="patientType" value="2">                                                                        
							<b> 
								<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="accessionNowise"/>
								</font>
							</b>
						</logic:equal>
					</th>
					<th width="10%" align="left"  >
						<b> 
							<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="patStatus"/>
							</font>
						</b>
					</th>
					<th align="left">
										<b> 
											<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
												<logic:equal name="InvResultValidationFB" property="searchBy" value="1">  
													Requisition Date
												</logic:equal>
												<logic:equal name="InvResultValidationFB" property="searchBy" value="0">  
													Acceptance Date
												</logic:equal>
											</font>
										</b>
									</th>
					<th width="10%" align="left">
						<b> 
							<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">Machine</font>
						</b>
					</th>
					<th width="3%" style="display: none;">
						<% List<ResultEntryVO> lstPatVO=(List<ResultEntryVO>)session.getAttribute(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
					 		int i,size=0,total;
			 				if(lstPatVO!=null && lstPatVO.size()>0 )
			 					size=lstPatVO.size();
						%>
			 				<img class="button" title="Show All Test Details"  src='<his:path src="/hisglobal/images/plusinv.png"/>' id="showAllTest"      tabindex="1" onclick ="displayAllTest('<%=size%>')" >
  							<img class="button" title="Hide All Test Details"  src='<his:path src="/hisglobal/images/Minus.png"/>' id="hideAllTest"    style="display: none;"  tabindex="1" onclick ="hideAllTests('<%=size %>')" >
  					</th>
					<th width="5%" align="left">
								<b> 
									<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										Preview
									</font>
								</b>
					</th>
					<th>
						<img class="button" title="Show Test Details" 
							src="media/images/details_open.png" id="btn-show-all-children">
						<img class="button" title="Hide Test Details" 
							src="media/images/details_close.png" id="btn-hide-all-children" style="display: none;">
					</th>
				</tr>
			</thead>
			<tbody>
					<%
					
						//int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
						//int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
						Map<String, List<String>> dnoMap = new HashMap();
						startDisplay=1;
						hideDisplay=1;
						String grpCode="";
						Map<String, String> colorMap = new HashMap();
						String[] colorsDiv = {	"#ffe6e6", "#e6ffe6", "#ffe6cc", "#ffffcc", "#f2e6d9", 
								"#ffeee6", "#ccffeb", "#e6ffe6", "#c5edd7", "#ffebe6", 
								"#e6ffff", "#ffe6ff", "#ffeee6", "#ecf9f2"};
						int colorCount = 0;
						for(int j=0;j<lstPatVO.size();j++)
						{int l=j;
						boolean firstTimeTravesall=true;
					
					
						//int i=j-startIndex;
						//if(j<size)
						//{
							ResultEntryVO voPat=lstPatVO.get(j);
							if(colorMap.containsKey(voPat.getPatCRNo())){
								//colorMap.put(voPat.getPatCRNo(),"#9999ff");
							}else{
								//colorMap.put(voPat.getPatCRNo(),"#ddccff");
									if(colorCount > colorsDiv.length){
										colorCount = 0;
										colorMap.put(voPat.getPatCRNo(), colorsDiv[colorCount]);
										colorCount++;
									}else{
										colorMap.put(voPat.getPatCRNo(), colorsDiv[colorCount]);
										colorCount++;
									}
								}
							String chkVal=voPat.getPatCRNo()+"#"+voPat.getRequisitionNo()+"#"+voPat.getRequisitionDNo()+"#"+voPat.getGroupCode();  
						
							List<String> mapList = new ArrayList(); 
						 	String value=voPat.getRequisitionNo()+voPat.getGroupCode();
						
						 	String color="blue";
						 	int reportChangeValue=0;
						 	if(voPat.getReportChangeFlag()!=null)
						  		reportChangeValue=Integer.parseInt(voPat.getReportChangeFlag());
						 
						 	switch(reportChangeValue)
			 				{
				 				case 1: color="red";  // report change addendum
				 				        break;
				 				case 2: color="red";  //  report change addendum
							        	break;
				 				case 3: color="red";  // report change addendum
							        	break;
							    default: color="blue";
							    		break;
			 				}
						 
						if(voPat.getGroupCode()!=null)
						{   
							grpCode+='&';
							String[] SplitGrpCode=grpCode.split("&");
							int length=SplitGrpCode.length;
							if(SplitGrpCode.length>1)
							for(int x=0;x<SplitGrpCode.length;x++)
							{
	                        if(SplitGrpCode[x].equals(voPat.getRequisitionNo()+voPat.getGroupCode()))
	                        {
							 firstTimeTravesall=false;
	                        }
							 
							 else
		                        {
		                        	 firstTimeTravesall=true;	
		                        }
							} 
								 
								 grpCode+=voPat.getRequisitionNo()+voPat.getGroupCode();
						}
					 
						if(firstTimeTravesall)
			 			{	
							String childRowTable = "";
							childRowTable 	+=  	"<table width='90%'>" 
									+		"<tr style='background-color : #b3e0ff'>"
									+			"<th width='20%'>"
									+				"<font color='#1E90FF' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
									+					"Test Parameter Name"
									+				"</font>"
									+			"</th>"
									+			"<th width='20%'>"
									+				"<font color='#1E90FF' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
									+					"Test Parameter Value"
									+				"</font>"
									+			"</th>"
									+			"<th width='20%'>"
									+				"<font color='#1E90FF' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
									+					"Reference Range"
									+				"</font>"
									+			"</th>"
									+		"</tr>";
								
							
							
					%>   
					<%
					String[] parameters=voPat.getTestParameterName().split("`");
					int paraSize=parameters.length;
					
					System.out.println("reqdno"+voPat.getRequisitionDNo()+"testcode"+voPat.getTestCode());
						 
					for(int iterate=0;iterate<paraSize;iterate++)
					{
						String[] paraValues=parameters[iterate].split("#@");
						String paracode=paraValues[0];
						String paraName=paraValues[1];
						String refRange="";
						if(paraValues.length>2)
						 refRange=paraValues[2];
						String displayRef="";
						String rangeTypeFinal = "";
						String[] refreValueFinal = null;
				        
						//updated by krishnan nema on 17/10/2018
						String paraEntry="";
						if(paraValues.length>3)
						{
							paraEntry=paraValues[3];
						if(paraValues[3].equals("--")  )
						{
							 paraEntry="";
						}
						else
						{
							 
						}
						}
						
						if(!paraEntry.contains("<"))
						paraEntry=paraEntry.replace("\r\n","<br>");
						if(paraEntry.contains("$"))
							paraEntry=paraEntry.replace("$","<br>");
						
						boolean flagg=false;
						 if(refRange!=null||!refRange.equals(""))
						{
						refRange=refRange.replace("$", "@");
						String[] refValues=refRange.split("@");
						refreValueFinal = refValues;
						 if(refValues.length>1)
						 {
							 String checkRangetyp=refValues[0];
							 rangeTypeFinal = checkRangetyp;
							 if(checkRangetyp.equals("1"))
							 {
								 String highValue=refValues[1];
									
									String lowValue=refValues[2];
									
									if((highValue.matches("\\d*\\.?\\d+") ) && (lowValue.matches("\\d*\\.?\\d+") ))
									{
										
										flagg=true;
									}
									String highValueUom=refValues[3];
									String lowValueUom=refValues[4];
									 displayRef=lowValue+" "+lowValueUom+" - "+highValue+" "+highValueUom;
							 }
							 else if(checkRangetyp.equals("2"))
							 {
								 
								 String rangetyp=">";
									
									String tovalue=refValues[2];
									String tovalueunit=refValues[1];
									
									if( (tovalue.matches("\\d*\\.?\\d+") ))
									{
										
										flagg=true;
									}
									
									 displayRef=rangetyp+" "+tovalue+"  "+tovalueunit;
									
							 } 
								 
							 else if(checkRangetyp.equals("3"))
							 {
								 String rangetyp=">=";
									
									String tovalue="";
											if(refValues.length>2)
											tovalue=refValues[2];
									String tovalueunit="";
									if(refValues.length>1)
											tovalueunit=	refValues[1];
									
									if( (tovalue.matches("\\d*\\.?\\d+") ))
									{
										
										flagg=true;
									}
									
									 displayRef=rangetyp+" "+tovalue+"  "+tovalueunit;
									 
							 }
							 else if(checkRangetyp.equals("4"))
							 {
								 String rangetyp="<";
									
								 String tovalue="";
									if(refValues.length>2)
									tovalue=refValues[2];
							String tovalueunit="";
							if(refValues.length>1)
									tovalueunit=	refValues[1];
									if( (tovalue.matches("\\d*\\.?\\d+") ))
									{
										
										flagg=true;
									}
									 displayRef=rangetyp+" "+tovalue+"  "+tovalueunit;
									 
							 }
							 else if(checkRangetyp.equals("5"))
							 {
								 String rangetyp="<=";
									
								 String tovalue="";
									if(refValues.length>2)
									tovalue=refValues[2];
							String tovalueunit="";
							if(refValues.length>1)
									tovalueunit=	refValues[1];
									if( (tovalue.matches("\\d*\\.?\\d+") ))
									{
										
										flagg=true;
									}
									 displayRef=rangetyp+" "+tovalue+"  "+tovalueunit;
									 
							 }
						
						 }
						}
						else  
							displayRef="";
						
						/*  String paraEntry=paraValues[3];
						if(paraValues[3].equals("--")  )
						{
							 paraEntry="";
						}
						else
						{
							 
						}
						
						
						if(!paraEntry.contains("<"))
						paraEntry=paraEntry.replace("\r\n","<br>");
						if(paraEntry.contains("$"))
							paraEntry=paraEntry.replace("$","<br>"); */
					
							
							//updated by krishnan nema on 17/10/2018
							
							boolean numeric = false;
							try {
            					//Double num = Double.parseDouble(paraEntry);
            					if (paraEntry.matches("\\d*\\.?\\d+") ) 
            			        {
            						numeric = true;
            			        }
        					} catch (NumberFormatException e) {
            				numeric = false;
        					}
							
						if(numeric && flagg)
						{
							
							if(refreValueFinal!=null)
							{
								if(rangeTypeFinal.equals("1")){
									
									 String highValue="";
											 if(refreValueFinal.length>1)
									 highValue= refreValueFinal[1];
									 String lowValue="";
									 if(refreValueFinal.length>2)
									 lowValue= refreValueFinal[2];
									 
									 
									 if((Float.valueOf(paraEntry) > Float.valueOf(highValue)) || (Float.valueOf(paraEntry) < Float.valueOf(lowValue))){
										 String valu = voPat.getRequisitionDNo() + "#" + "null" + "#template#" + voPat.getTestCode() +paracode;
											mapList.add(valu);
											
											
											childRowTable	+= 	"<tr style='background-color: " + colorMap.get(voPat.getPatCRNo()) + "'>"
													+	"	<th width='20%'>"
													+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>" + paraName + "</font>"
													+	"	</th>"
													+	"	<th id='paraEntryColor' width='20%'>"
													+ 	"		<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>" + paraEntry + "</font>"
													+	"	</th>"
													+	"	<th width='20%'>"+ displayRef + "</th>"
													+	"</tr>";
									 }else{
										 childRowTable	+=	"<tr style='background-color: " + colorMap.get(voPat.getPatCRNo()) + "'>"
													+	"	<th width='20%'>"
													+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>" + paraName + "</font>"
													+	"	</th>"
													+	"	<th id='paraEntryColor' width='20%'>"
													+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+ paraEntry + "</font>"
													+	"	</th>"
													+	"	<th width='20%'>"+ displayRef +"</th>"
													+	"</tr>";
									 }
								}else if(rangeTypeFinal.equals("2")){
									
									String tovalue=refreValueFinal[2];
									if((Float.valueOf(paraEntry) < Float.valueOf(tovalue))){
										 String valu = voPat.getRequisitionDNo() + "#" + "null" + "#template#" + voPat.getTestCode() +paracode;
											mapList.add(valu);
											
											childRowTable	+=	"<tr style='background-color: " + colorMap.get(voPat.getPatCRNo()) + "'>"
													+	"	<th width='20%'>"
													+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>" + paraName + "</font>"
													+	"	</th>"
													+	"	<th id='paraEntryColor' width='20%'>"
													+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+ paraEntry + "</font>"
													+	"	</th>"
													+	"	<th width='20%'>"+ displayRef +"</th>"
													+	"</tr>";
									}else{
										childRowTable	+=	"<tr style='background-color: " + colorMap.get(voPat.getPatCRNo()) + "'>"
												+	"	<th width='20%'>"
												+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>" + paraName + "</font>"
												+	"	</th>"
												+	"	<th id='paraEntryColor' width='20%'>"
												+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+ paraEntry + "</font>"
												+	"	</th>"
												+	"	<th width='20%'>"+ displayRef +"</th>"
												+	"</tr>";
									}
							}else if(rangeTypeFinal.equals("3")){
									
									String tovalue=refreValueFinal[2];
									if((Float.valueOf(paraEntry) <= Float.valueOf(tovalue))){
										 String valu = voPat.getRequisitionDNo() + "#" + "null" + "#template#" + voPat.getTestCode() +paracode;
											mapList.add(valu);
											
											childRowTable	+=	"<tr style='background-color: " + colorMap.get(voPat.getPatCRNo()) + "'>"
													+	"	<th width='20%'>"
													+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>" + paraName + "</font>"
													+	"	</th>"
													+	"	<th id='paraEntryColor' width='20%'>"
													+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+ paraEntry + "</font>"
													+	"	</th>"
													+	"	<th width='20%'>"+ displayRef +"</th>"
													+	"</tr>";
									}else{
										childRowTable	+=	"<tr style='background-color: " + colorMap.get(voPat.getPatCRNo()) + "'>"
												+	"	<th width='20%'>"
												+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>" + paraName + "</font>"
												+	"	</th>"
												+	"	<th id='paraEntryColor' width='20%'>"
												+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+ paraEntry + "</font>"
												+	"	</th>"
												+	"	<th width='20%'>"+ displayRef +"</th>"
												+	"</tr>";
									}
							}else if(rangeTypeFinal.equals("4")){
								
								
								String tovalue=refreValueFinal[2];
								if((Float.valueOf(paraEntry) > Float.valueOf(tovalue))){
									 String valu = voPat.getRequisitionDNo() + "#" + "null" + "#template#" + voPat.getTestCode() +paracode;
										mapList.add(valu);
										
										childRowTable	+=	"<tr style='background-color: " + colorMap.get(voPat.getPatCRNo()) + "'>"
												+	"	<th width='20%'>"
												+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>" + paraName + "</font>"
												+	"	</th>"
												+	"	<th id='paraEntryColor' width='20%'>"
												+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+ paraEntry + "</font>"
												+	"	</th>"
												+	"	<th width='20%'>"+ displayRef +"</th>"
												+	"</tr>";
								}else{
									childRowTable	+=	"<tr style='background-color: " + colorMap.get(voPat.getPatCRNo()) + "'>"
											+	"	<th width='20%' >"
											+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>" + paraName + "</font>"
											+	"	</th>"
											+	"	<th id='paraEntryColor' width='20%'>"
											+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+ paraEntry + "</font>"
											+	"	</th>"
											+	"	<th width='20%'>"+ displayRef +"</th>"
											+	"</tr>";
								}
							}else if(rangeTypeFinal.equals("5")){
								
								String tovalue=refreValueFinal[2];
								if((Float.valueOf(paraEntry) >= Float.valueOf(tovalue))){
									 String valu = voPat.getRequisitionDNo() + "#" + "null" + "#template#" + voPat.getTestCode() +paracode;
										mapList.add(valu);
										
										childRowTable	+=	"<tr style='background-color: " + colorMap.get(voPat.getPatCRNo()) + "'>"
												+	"	<th width='20%'>"
												+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>" + paraName + "</font>"
												+	"	</th>"
												+	"	<th id='paraEntryColor' width='20%'>"
												+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+ paraEntry + "</font>"
												+	"	</th>"
												+	"	<th width='20%'>"+ displayRef +"</th>"
												+	"</tr>";
								}else{
									childRowTable	+=	"<tr style='background-color: " + colorMap.get(voPat.getPatCRNo()) + "'>"
											+	"	<th width='20%'>"
											+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>" + paraName + "</font>"
											+	"	</th>"
											+	"	<th id='paraEntryColor' width='20%'>"
											+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+ paraEntry + "</font>"
											+	"	</th>"
											+	"	<th width='20%'>"+ displayRef +"</th>"
											+	"</tr>";
								}
							}
							else{
								childRowTable	+=	"<tr style='background-color: " + colorMap.get(voPat.getPatCRNo()) + "'>"
										+	"	<th width='20%'>"
										+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>" + paraName + "</font>"
										+	"	</th>"
										+	"	<th id='paraEntryColor' width='20%'>"
										+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+ paraEntry + "</font>"
										+	"	</th>"
										+	"	<th width='20%'>"+ displayRef +"</th>"
										+	"</tr>";	
							}
						}else{
							childRowTable	+=	"<tr style='background-color: " + colorMap.get(voPat.getPatCRNo()) + "'>"
									+	"	<th width='20%'>"
									+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>" + paraName + "</font>"
									+	"	</th>"
									+	"	<th id='paraEntryColor' width='20%'>"
									+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+ paraEntry + "</font>"
									+	"	</th>"
									+	"	<th width='20%'>"+ displayRef +"</th>"
									+	"</tr>";	
						}
						}else{
							childRowTable	+= 	"<tr style='background-color: " + colorMap.get(voPat.getPatCRNo()) + "'>"
											+	"	<th width='20%'>"
											+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>" + paraName + "</font>"
											+	"	</th>"
											+	"	<th id='paraEntryColor' width='20%'>"
											+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>" + paraEntry ;
												
												if(paraEntry.equals("File Uploaded")){ 
									 				String valu1 = voPat.getRequisitionDNo() + "#" + "null" + "#template#" + voPat.getTestCode() +paracode;
													childRowTable += "<a id='view"+ valu1 +"' class='view@@" + valu1 + "' href='#' onclick='myuploadFunction(this)'>View File</a>";
												} 	
							childRowTable 	+=	"		</font>"
											+	"</th>"
											+	"<th width='20%'>" + displayRef + "</th>"
											+	"</tr>";
						}
					}
					dnoMap.put(voPat.getRequisitionDNo(),mapList);
				
					 if(voPat.getFinalRemarkReqd()!=null && voPat.getFinalRemarkReqd().equals("1")){ 
							childRowTable 	+= 	"	<tr style='background-color: " + colorMap.get(voPat.getPatCRNo()) + "'>"
											+	"	<th width='20%'>"
											+	"		<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> Comments </font>"
											+	"	</th>"
											+	"	<th id='paraEntryColor' width='20%'>"
											+	"		<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>" + ((voPat.getFinalRemarkString() == null )?"":voPat.getFinalRemarkString()) + "</font>"
											+	"	</th>"
											+	"<th width='20%'></th>"
											+	"</tr>";
					 }
					 
					 childRowTable += "</table>";
							
					%>
						<tr data-child-value="<%=childRowTable%>" style="background-color: <%=colorMap.get(voPat.getPatCRNo())%>">
							<td width="3%" align="left"  >
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<input type="checkbox" class="jpCheckbox" name="chkSamplePatient" value='<%=chkVal%>' onclick="ValidateSameCrNo(this)" >
								</font>
							</td>
							<td width="12%" align="left" >
						 		<div>
						  		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=voPat.getPatCRNo() %></font> 
						 		</div>
				  			</td>
				  			<td width="10%" align="left">
				  				<div>
				  					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatName() %></font>
						 	</div>
				  		
				  		</td>
				  		<td width="8%" align="left">
				  		 
				  		
				  		<div  >
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatAge()+"/"+voPat.getPatGender()%></font>
						 </div>
				  		</td>
				  		 <td  width="10%" align="left">
				  		<div  >
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatUnitName() %></font>
						 </div>
				  		</td>
				  		<td width="10%" align="left">
				  		 
				  		<div  >
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getGroupName().equals("NA")?voPat.getTestName():voPat.getGroupName() %></font>
						 </div>
				  		</td>
				  		<td width="10%" align="left">
				  		<div  >
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatLabName() %></font>
						 </div>
				  		</td>
				  		  		<td width="10%" align="left">
				  		<div >
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getTempSampleNo()==null?voPat.getLabNo():voPat.getTempSampleNo()%></font>
						 </div>
				  		</td>
				  		<td width="10%" align="left">
				  		<div >
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatStatus() %></font>
						 </div>
				  		</td>
				  		<td align="left">
														<div>
															<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getRequisitionDate() %></font>
														</div>
													</td>
				  		
				  		 <td width="10%" align="left">
									<div >
										<font color="#000000" size="2"
										<%
										if(voPat.getMachineCode()!=null && voPat.getMachineCode().equals("-1"))
										{
											voPat.setMachineCode(null);
										}
										%>
											face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getMachineCode()==null?"-":voPat.getMachineCode() %></font>
									</div>
								</td>
								
				  		<td width="3%" style="display: none;">
				  			<% 
				  		 		String showTest="showTestDetails("+j+",'"+value+"')";
						 		String hideTest="hideTestDetails("+j+",'"+value+"')";
			 				%>
  			 					<img class="button" title="Show Test Details"  src='<his:path src="/hisglobal/images/plusinv.png"/>' id="showTest<%=j%>"     tabindex="1" onclick ="<%=showTest%>" >
  								<img class="button" title="Hide Test Details"  src='<his:path src="/hisglobal/images/Minus.png"/>' id="hideTest<%=j%>"   style="display: none;"    tabindex="1" onclick ="<%=hideTest %>" >
  						</td>
				  		<td width="5%" align="left"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									<a href="#" onclick="displaySamplePatDetailsPopUp('<%=chkVal%>')"><span style="font-size: 15px;" class="glyphicon glyphicon-list-alt"></span></a>
									<%-- <input
										type="checkbox" class="jpCheckbox" name="chkSamplePatient1"
										value='<%=chkVal%>' > --%>
								</font>
						</td>
						
						<td class="details-control">
												
						</td>
						
							<%
				  				if(voPat.getDeptReport()!=null)
				  				{
				  			%>
				  					<td width="1%" align="left">
										<img  title='Show Department Report' src='/HISInvestigationG5/hisglobal/images/add_remarks_sml.jpg' onclick="printReport('<%=voPat.getDeptReport()%>')">
									</td>
				  			<%
				  				} 
				  			%>
					</tr>
										<!--end to display test and its value  -->
							<%
								}}
									session.setAttribute("mapForRefRangeValidation", dnoMap);
							%>
							<input type="hidden" name="chkValue" value="<%=grpCode%>" />
							</tbody>
						</table>
						</div>
								</div>
								</div>
			</logic:notEmpty>
			</logic:present>
			 </logic:equal>
			 
			 <logic:present name="<%=InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO %>">
			 
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
			Map<String,List<ResultEntryVO>> _mpp=(Map<String,List<ResultEntryVO>>)session.getAttribute(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO);
			Map<String,String> pidmap=(Map<String,String>)session.getAttribute(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO_PID);

			
			boolean sameSampleNO=false;
				 
			 

			
			Map<String,List<String>> mpAutoList = (Map<String,List<String>>)session.getAttribute(InvestigationConfig.AUTOCOMPLETE_LIST_VALUES);
			List<String> finalRemarkCodeList=new ArrayList<String>();
			
			  if(mpAutoList!=null)
			  {
				  Iterator itrAuto=mpAutoList.keySet().iterator();
			   while(itrAuto.hasNext())
			  { String autoCode=(String)itrAuto.next();
			  String autoList=(String)mpAutoList.get(autoCode).get(0);
			 
			  String hiddenCode="#hiddenid"+autoCode;
			  String hid="hiddenid"+autoCode;
			  autoCode="#"+autoCode;

			  %>
<script>

var objList = JSON.parse('<%=autoList%>');
//alert(objList);
var autoId='<%=autoCode%>';
var hiddenId='<%=hiddenCode%>';
//alert("autoid"+autoId);

		generateAuto(objList,autoId,hiddenId);
			
			</script>
			 <input type="hidden"  id='<%=hid %>' name="userCannedCode" /> 
			<%
			
		}}
		
				Iterator itrr=_mpp.keySet().iterator();
			int  size=0;
					int i=0;
				 
				while(itrr.hasNext())
				{
			boolean firstTimeTravesa=true;
			boolean firstTimeRemark=true;

					 String pidnoo="";
				String sampleNo=(String)itrr.next();
				 
				
				if(pidmap!=null && pidmap.size()>0)
				{
					if(pidmap.containsKey(sampleNo))
					{
						
						 pidnoo=(String)pidmap.get(sampleNo);
					}
			   
				}
				
				
				List<ResultEntryVO> lstResultEntryVO=_mpp.get(sampleNo);
			 		if(lstResultEntryVO!=null && lstResultEntryVO.size()>0 )
			 			size=lstResultEntryVO.size();
			 		if(size>1)
			 			sameSampleNO=true;
			 		 ResultEntryVO invresultentryvo=new  ResultEntryVO();
			 		HashMap<String, String> reqNoGroupCodeChkTestValueMap = new HashMap<String, String>();
			 		for(int k=0;k<size;k++)
			 		{
			 			invresultentryvo=new  ResultEntryVO();
			 			  invresultentryvo=lstResultEntryVO.get(k);
			 			 ResultEntryVO actualRvo = invresultentryvo.getResultEntryVOListValidatedBy().get(0);
			 			 String chkTestValue = "";
			 			 
			 			 if(actualRvo.getDynamnicTemplateResultEntryGroup().equals("1")
			 					 && actualRvo.isDoCreateTemplate() == true && actualRvo.getGroupCode() != null)
			 			 {
				 			 for(int m = 0; m < size; m++)
				 			 {
				 				ResultEntryVO tempInvRVO=lstResultEntryVO.get(m);
				 				String key = tempInvRVO.getRequisitionNo() + tempInvRVO.getGroupCode();
				 				 if(reqNoGroupCodeChkTestValueMap.containsKey(key))
				 				 {
				 					 String tempChkTestValue = tempInvRVO.getPatCRNo()+"#"+tempInvRVO.getRequisitionNo()+"#"+tempInvRVO.getRequisitionDNo()+"#"+tempInvRVO.getTestCode()+"#"+tempInvRVO.getSampleNo()+"#"+tempInvRVO.getLabCode()+"#"+tempInvRVO.getPatAge()+"#"+tempInvRVO.getPatGender()+"#"+tempInvRVO.getReportAvailableAfter()+"#"+tempInvRVO.getPatVisitDat()+"#"+tempInvRVO.getPatVisitNo()+"#"+tempInvRVO.getLabNo()+"#"+tempInvRVO.getEpisodeCode()+"#"+tempInvRVO.getDepartmentcode()+"#"+tempInvRVO.getPatdeptunitcode()+"#"+tempInvRVO.getRequisitionTypeCode()+"#"+tempInvRVO.getUomCode()+"#"+tempInvRVO.getTestName()+"#"+tempInvRVO.getPatLabName()+"#"+tempInvRVO.getSampleName()+"#"+tempInvRVO.getTempSampleNo()+"#"+tempInvRVO.getGroupCode()+"#"+tempInvRVO.getDynamicTemplatePrintedGroup() ;
				 					 String oldChkTestValue = reqNoGroupCodeChkTestValueMap.get(key);
				 					 oldChkTestValue += "!" + tempChkTestValue;
				 					reqNoGroupCodeChkTestValueMap.put(key, oldChkTestValue);
				 				 }
				 				 else
				 				 {
				 					String tempChkTestValue = tempInvRVO.getPatCRNo()+"#"+tempInvRVO.getRequisitionNo()+"#"+tempInvRVO.getRequisitionDNo()+"#"+tempInvRVO.getTestCode()+"#"+tempInvRVO.getSampleNo()+"#"+tempInvRVO.getLabCode()+"#"+tempInvRVO.getPatAge()+"#"+tempInvRVO.getPatGender()+"#"+tempInvRVO.getReportAvailableAfter()+"#"+tempInvRVO.getPatVisitDat()+"#"+tempInvRVO.getPatVisitNo()+"#"+tempInvRVO.getLabNo()+"#"+tempInvRVO.getEpisodeCode()+"#"+tempInvRVO.getDepartmentcode()+"#"+tempInvRVO.getPatdeptunitcode()+"#"+tempInvRVO.getRequisitionTypeCode()+"#"+tempInvRVO.getUomCode()+"#"+tempInvRVO.getTestName()+"#"+tempInvRVO.getPatLabName()+"#"+tempInvRVO.getSampleName()+"#"+tempInvRVO.getTempSampleNo() + "#"+tempInvRVO.getGroupCode()+"#"+tempInvRVO.getDynamicTemplatePrintedGroup();
				 					reqNoGroupCodeChkTestValueMap.put(key, tempChkTestValue);
				 				 }
				 			 }
				 			 chkTestValue = reqNoGroupCodeChkTestValueMap.get(invresultentryvo.getRequisitionNo() +invresultentryvo.getGroupCode() );
			 			 }
			 			 else {
			 				chkTestValue=invresultentryvo.getPatCRNo()+"#"+invresultentryvo.getRequisitionNo()+"#"+invresultentryvo.getRequisitionDNo()+"#"+invresultentryvo.getTestCode()+"#"+invresultentryvo.getSampleNo()+"#"+invresultentryvo.getLabCode()+"#"+invresultentryvo.getPatAge()+"#"+invresultentryvo.getPatGender()+"#"+invresultentryvo.getReportAvailableAfter()+"#"+invresultentryvo.getPatVisitDat()+"#"+invresultentryvo.getPatVisitNo()+"#"+invresultentryvo.getLabNo()+"#"+invresultentryvo.getEpisodeCode()+"#"+invresultentryvo.getDepartmentcode()+"#"+invresultentryvo.getPatdeptunitcode()+"#"+invresultentryvo.getRequisitionTypeCode()+"#"+invresultentryvo.getUomCode()+"#"+invresultentryvo.getTestName()+"#"+invresultentryvo.getPatLabName()+"#"+invresultentryvo.getSampleName()+"#"+invresultentryvo.getTempSampleNo()+"#"+invresultentryvo.getGroupCode()+"#"+invresultentryvo.getDynamicTemplatePrintedGroup()+"#"+invresultentryvo.getPatName()+"#"+invresultentryvo.getRefRange() +"#"+invresultentryvo.getDetpUnitCode()+"#"+invresultentryvo.getPatUnitName()+"#"+invresultentryvo.getParantParaCode()+"#"+invresultentryvo.getIsrequisitionformneeded();
			 			 }
			 			 
			 			// String chkTestValue=invresultentryvo.getPatCRNo()+"#"+invresultentryvo.getRequisitionNo()+"#"+invresultentryvo.getRequisitionDNo()+"#"+invresultentryvo.getTestCode()+"#"+invresultentryvo.getSampleNo()+"#"+invresultentryvo.getLabCode()+"#"+invresultentryvo.getPatAge()+"#"+invresultentryvo.getPatGender()+"#"+invresultentryvo.getReportAvailableAfter()+"#"+invresultentryvo.getPatVisitDat()+"#"+invresultentryvo.getPatVisitNo()+"#"+invresultentryvo.getLabNo()+"#"+invresultentryvo.getEpisodeCode()+"#"+invresultentryvo.getDepartmentcode()+"#"+invresultentryvo.getPatdeptunitcode()+"#"+invresultentryvo.getRequisitionTypeCode()+"#"+invresultentryvo.getUomCode()+"#"+invresultentryvo.getTestName()+"#"+invresultentryvo.getPatLabName()+"#"+invresultentryvo.getSampleName()+"#"+invresultentryvo.getTempSampleNo();                                                                                    
			 			String labCode=invresultentryvo.getLabCode();
			 			String reqDno=invresultentryvo.getRequisitionDNo();
			 			//String chkTestValue=invresultentryvo.getPatCRNo()+"#"+invresultentryvo.getRequisitionNo()+"#"+invresultentryvo.getRequisitionDNo()+"#"+invresultentryvo.getTestCode()+"#"+invresultentryvo.getSampleNo()+"#"+invresultentryvo.getLabCode()+"#"+invresultentryvo.getPatAge()+"#"+invresultentryvo.getPatGender()+"#"+invresultentryvo.getReportAvailableAfter()+"#"+invresultentryvo.getPatVisitDat()+"#"+invresultentryvo.getPatVisitNo()+"#"+invresultentryvo.getLabNo()+"#"+invresultentryvo.getEpisodeCode()+"#"+invresultentryvo.getDepartmentcode()+"#"+invresultentryvo.getPatdeptunitcode()+"#"+invresultentryvo.getRequisitionTypeCode()+"#"+invresultentryvo.getUomCode();                                                                                      
						
			 			 if(firstTimeTravesa)
			 			{
					%>
			<% 
String header="";
                   if(!pidnoo.equals("")) 
                   {
                	    header="Patient Details <font style='color:yellow;'>(PID Number: "+pidnoo+")</font>";
                    %>
                 <%-- <font  style="color: white;text-align">PID Number:<%=pidnoo %> </font> --%>
                    <%} else{
                    	header="Patient Details";
                    }%>
                    
                    
			<his:SubTitleTag name="<%=header%>">  
			 <% 
			 String showDetail="showPatDetails("+i+")";
			 String hideDetail="hidePatDetails("+i+")";
			 %>
			  <%	if(invresultentryvo.getDeptReport()!=null)
		  		{ %>
			  <a onclick="printReport('<%=invresultentryvo.getDeptReport()%>')" ><font color="white">View Departmental Report</font></a>
			  <%} %>

			 
  			 <img class="button" title="Show Patinet Details"  src='<his:path src="/hisglobal/images/plusinv.png"/>' id="show<%=i%>"      tabindex="1" onclick ="<%=showDetail%>" >
  			<img class="button" title="Hide Patient Details"  src='<his:path src="/hisglobal/images/Minus.png"/>' id="hide<%=i%>" style="display: none;"     tabindex="1" onclick ="<%=hideDetail %>" >
  			</his:SubTitleTag>
			<table width="100%">
			<tr>
		 			<td class="tdfont" width="15%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<bean:message key="crNO"/>&nbsp;
		 				</font>
		 				</div>
		 				
		 			</td>
		 			<td class="tdfonthead" width="16%"> 
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=invresultentryvo.getPatCRNo() %>
		 					</font>
		 				</div>
		 			</td>
		 			 <td class="tdfont" width="15%">
		 				<div align="right">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				 	<bean:message key="patientName"/>&nbsp;
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="16%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=invresultentryvo.getPatName() %>
		 				</font>
		 				</div>
		 			</td> 
		 			
		 			 <td class="tdfont" width="15%">
		 				<div align="right">
                      <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				 <% if(invresultentryvo.getLabtype()!=null && invresultentryvo.getLabtype().equals("2")){ %>
									 Accession No.
									 
									 <%}else{ %>
									 <bean:message 	key="sampleNo" /> 
									 <%} %>
		 				</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="16%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=invresultentryvo.getTempSampleNo()==null?"NA":invresultentryvo.getTempSampleNo() %>
		 					</font>
		 				</div>
		 			</td> 
		 			
		 			
		 		
			</tr>
		</table>
		<div id="showhide<%=i%>" style="display: none;">
		<table  width="100%">
		<tr>
		<td class="tdfont" width="15%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="age/gender"/>&nbsp;
		 				</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="16%"> 
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=invresultentryvo.getPatAge()+"/"+invresultentryvo.getPatGender()%>
		 					</font>
		 				</div>
		 			</td>
		 			 <td class="tdfont" width="15%">
		 				<div align="right">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				 	<% if(invresultentryvo.getLabtype()!=null && invresultentryvo.getLabtype().equals("2")){ %>
								 <bean:message
										key="visitDate" />&nbsp;	 
									 <%}else{ %>
								
 <bean:message 	key="SampleName" />&nbsp;
 
									 <%} %>
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="16%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<% if(invresultentryvo.getLabtype()!=null && invresultentryvo.getLabtype().equals("2")){ %>
									 <%=invresultentryvo.getPatVisitDat()%>
									 <%}else{ %>
										<%=  invresultentryvo.getSampleName()%>
									<% }	 %>
		 				</font>
		 				</div>
		 			</td> 
		 				 <td class="tdfont" width="15%">
		 				<div align="right">
                      <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				 <bean:message key="labName"/>&nbsp;
		 				</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="16%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=invresultentryvo.getPatLabName() %>
		 					</font>
		 				</div>
		 			</td> 
		</tr>
		<tr>
		<td class="tdfont" width="15%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<bean:message key="departmentunit"/>&nbsp;
		 				</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="16%"> 
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=invresultentryvo.getPatUnitName() %>
		 					</font>
		 				</div>
		 			</td>
		 			 <td class="tdfont" width="15%">
		 				<div align="right">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 			 <% if(invresultentryvo.getLabtype()!=null && !invresultentryvo.getLabtype().equals("2")){ %>
									<bean:message
										key="visitDate" />&nbsp;
										<%} %>
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="16%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<% if(invresultentryvo.getLabtype()!=null && !invresultentryvo.getLabtype().equals("2")){ %>
									<%=invresultentryvo.getPatVisitDat()%>
									<%} %>
		 				</font>
		 				</div>
		 			</td>
		 			 <td class="tdfont" width="15%">
		 				<div align="right">
                      <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="TestName"/>
		 				</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="16%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<%=invresultentryvo.getGroupName().equals("NA")?invresultentryvo.getTestName():invresultentryvo.getGroupName() %>
		 					</font>
		 				</div>
		 			</td> 
		</tr>
		</table>
		</div>
		<his:SubTitleTag name="Result Re Validation Form"></
  			</his:SubTitleTag>
<input type="hidden" value="<%=invresultentryvo.getPatLabName()%>" name="chkLabName" />


	<%
  			
  		} if(sameSampleNO)firstTimeTravesa=false;
					 			
					 			
					 				 
						 %>
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
			<tr >
			     <%if(lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString()!=null
					  && (lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).isDoCreateTemplate() == true)
					   ) 
			  
			  {
			  %>
			  <logic:equal name="InvResultValidationFB" property="ispreview"
					                   	value="1">
			   <td width="2%">
			   <input type="checkbox" id="<%=i%><%=k%><%=i%>chkBOx" name="chkResultValidationPatient" value='<%=chkTestValue%>'  >
			  
			   <html:hidden name="InvResultValidationFB" styleId="<%=reqDno%>" value="<%=labCode%>" property="cannedLabCode" />
		 	
		 	<html:hidden name="InvResultValidationFB"   property="cannedOrMacroCheck" />
			   </td>
			   
			     <%--    <td class="tdfont" width="15%">
		 				<div align="right">
                      <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="TestName"/>&nbsp;
		 				</font>
		 				</div>
		 			</td> --%>
		 			<td class="tdfonthead" width="10%">
		 				<div align="left">
		 				<font color="blue" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					  &nbsp;     <%=invresultentryvo.getTestName()%>
		 					</font>
		 					
		 		<!-- 			ddddd -->
		 					
		 						<% 
											 String inst=invresultentryvo.getInstructionPat()==null?"NA":invresultentryvo.getInstructionPat();
		 				                		inst=inst.replace("\r\n","<br>");
											/* String inst="d"; */
											%>
												<%if(!inst.equals("NA")){ %>
											 <img  class="button" title="Show Instructions"  src='<his:path src="/hisglobal/images/add_remarks_sml.jpg"/>'    tabindex="1" onclick ="showInstructions5('<%=inst%>');">
							               <%}else{} %>
							               
							                  <% if(!invresultentryvo.getIsrequisitionformneeded().equals("0")) {%>	
													 				<%-- 	&nbsp&nbsp<img  height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick="ShowRequistionForm('<%=invresultentryvo.getTestCode()%>','<%=invresultentryvo.getTestName()%>','<%=invresultentryvo.getLabCode()%>','<%=invresultentryvo.getLabName()%>','<%=invresultentryvo.getRequisitionDNo()%>')"> --%>
													 					<%} %>	
																									
							
							<div id="blanket" style="display: none;"></div>
 		<div id="popUpDiv5"  style="display:none;" align="center">  
		 
		<his:TitleTag name="Instructions For Technician">
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
	
	
 	
 	<!-- END -- TO DISPLAY INSTRUCTIONS --> 
							
		 					
		 					
		 					
		 				</div>
		 			</td> 
			   </logic:equal>
			     <td width='99.99%' id="<%=i%><%=k%><%=i%>templateValue" >
			  
			  <%=
					  lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString() %>
			   <%}else if(lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString()!=null
			   && lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).isDoCreateTemplate() == false)
			   {
				  // do nothing 
			   }
			   else   {%>
			   <left>
                   <font color="red" size="4">
				     <bean:message key="resultEntryNot"/>&nbsp; <%=invresultentryvo.getTestName()%>
				   </font>
			  </left>
			   
			    </td>
			    <%} %>
			 </tr>
														
			</table>
			
		
		<%}
			 		
			 		
			 		
					
		  			if(!finalRemarkCodeList.contains(invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo()+'#'+invresultentryvo.getTempSampleNo()))
		  			{
		  				finalRemarkCodeList.add(invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo()+'#'+invresultentryvo.getTempSampleNo());
		  				
		  			if(invresultentryvo.getFinalRemarkReqd().equals("1")){ %>
		  			
		  				<table width="100%" cellpadding="0" cellspacing="0" border="0">
		  				<tr>
		  				<td width="2%" ></td>
		  				<logic:equal name="InvResultValidationFB" property="ispreview"
						value="1">
		  				<td width="20%" class="tdfonthead">
		  					<div align="left">
		  				&nbsp;&nbsp;&nbsp;&nbsp;Comments  
		  				</div>				
		  				</td>
		  				
		  				<td width="78%" class="tdfonthead">
		  				<div align="left">
		  				<% String sp="/"; %>
		  				<html:textarea style="width: 900px;" value="<%=invresultentryvo.getFinalRemarkString()%>" styleId="<%=invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo()+'#'+invresultentryvo.getTempSampleNo().split(sp)[0]%>" name="InvResultValidationFB" property="finalRemarksValue"
		  				 >
		  				</html:textarea>
		  				<html:hidden name="InvResultValidationFB" property="crNoReqNoString" value="<%=invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo()+'#'+invresultentryvo.getTempSampleNo().split(sp)[0]%>"/>
														</div>
		  				</td>
		  				</logic:equal>
		  				
		  				</tr>
		  				</table>
				<%}}
		  			
		  			
		  			
		  			
		  			
		  			
					 		i++;
		}  %>
		
		
		
		
			<div id="cannedField"  style="display:none;position: fixed;overflow-y:auto;">  <div draggable="true"    >
		 
		 	<his:TitleTag name="Canned List">
			<%-- <his:insertDateTag/> --%>
		
    	
     
    	 <img src='/HISInvestigationG5/hisglobal/css/close.png'  onClick="autocompleteBox_close();">
 
  			</his:TitleTag>
 		                             <table width="100%" id="autoCannedCombo">
 		                             
 		                               
	  <html:hidden name="InvResultValidationFB" property="currentElement"/>
	  <html:hidden name="InvResultValidationFB" property="currentElementName"/>
	    <html:hidden name="InvResultValidationFB" property="editorName"/>
	  
 		                             <tr>
														<td class="tdfonthead" width="20%">
															<div align="right"><b><bean:message key="CanedCode"/></b></div>
														</td>
														<td class="tdfont" width="20%">
															<div align="left" class="ui-widget">
																<input type="text"  id="automplete-4" name="userCanned"
																	size="30" style="width: 100px;"	
																	onkeypress="if(event.keyCode==13) addCannedDetail('CANNED');return validateAlphaNumericOnly(event,this);">
																	 												 
																
																	
															</div>
														</td>
													<%-- 	
														 <td class="tdfonthead" width="20%">
														 
														 
				 
				    	 <img class="button" align="left"  src='<his:path src="/hisglobal/images/btn-ok.png"  />' onclick="addCannedDetail('CANNED')"    >
				    
				 
														 
														 
														 
														 </td> --%>
														</tr>
 		                             </table>
 		                             
 		                             <table id="addMoreValue"  width="100%"></table>
						<div align="center">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-ok.png"  />'
								onclick="setCannedDetail('CANNED');autocompleteBox_close();">
						</div>

					</div></div>
	
	
	
	
	
	
	
	
	
			<div id="macroField" style="display:none;position: fixed;overflow-y:auto;"> <div draggable="true"    >
			 <his:TitleTag name="Macro List">
			<%-- <his:insertDateTag/> --%>
		
    	
     
    	 <img src='/HISInvestigationG5/hisglobal/css/close.png'  onClick="autocompleteBox_close_macro();">
 
  			</his:TitleTag>
 		                             <table width="100%" id="autoMacroCombo">
 		                             
 		                               
	  <html:hidden name="InvResultValidationFB" property="currentElement"/>
	  <html:hidden name="InvResultValidationFB" property="currentElementName"/>
	    <html:hidden name="InvResultValidationFB" property="editorName"/>
	  
	  
	  
 		                             <tr>
														<td class="tdfonthead" width="20%">
															<div align="right"><b><bean:message key="macroCode"/></b></div>
														</td>
														<td class="tdfont" width="20%">
															<div align="left" class="ui-widget">
																 <input type="text" id="automplete-m" name="userMacro"
																	size="30" style="width: 100px;"	
																	onkeypress="if(event.keyCode==13) addCannedDetail('MACRO');return validateAlphaNumericOnly(event,this);">
																
																	
															</div>
														</td>
														
														<%--  <td class="tdfonthead" width="20%">
														 
														 
				
				    
				    	 <img class="button"  align="left" src='<his:path src="/hisglobal/images/btn-ok.png"  />' onclick="addCannedDetail('MACRO')"    >
				  
														 
														 
														 
														 </td> --%>
														 
														 
														</tr>
 		                             </table>
 		                             
 		                             
 		                             <table id="addMoreValuemacro"  width="100%"></table>
 		                             
 		                             <div align="center"> <img class="button"   src='<his:path src="/hisglobal/images/btn-ok.png"  />' onclick="setCannedDetail('MACRO');autocompleteBox_close_macro();"    ></div>
	
 		                             </div></div>
<!--  		                             </fieldset>
 -->		
		
		
		
		
		<div id="canned"><div draggable="true"    >
	<his:TitleTag name="Canned List">
			<%-- <his:insertDateTag/> --%>
		
    	
     
    	 <img src='/HISInvestigationG5/hisglobal/css/close.png'  onClick="lightbox_close();">
 
  			</his:TitleTag>
	<table id="addMoreValue"  width="100%">
 	 
	 
    
	  <html:hidden name="InvResultValidationFB" property="currentElement"/>
	  <html:hidden name="InvResultValidationFB" property="currentElementName"/>
	  
	</table>
	
	 
	               <his:ButtonToolBarTag>
				    
				    	 <img class="button"   src='<his:path src="/hisglobal/images/btn-ok.png"  />' onclick="setCannedDetail('CANNED')"    >
				    
				    </his:ButtonToolBarTag>
    	 
 
	</div>	 </div>
	
	
	<div id="macro"><div draggable="true"    >
	<his:TitleTag name="Macro List">
			<%-- <his:insertDateTag/> --%>
		
    	
     
    	 <img src='/HISInvestigationG5/hisglobal/css/close.png'  onClick="lightbox_close_macro();">
 
  			</his:TitleTag>
	<table id="addMoreValuemacro"  width="100%">
 	 
	 
    
	  <html:hidden name="InvResultValidationFB" property="currentElement"/>
	  <html:hidden name="InvResultValidationFB" property="currentElementName"/>
	  
	</table>
	
	 
	               <his:ButtonToolBarTag>
				    
				    	 <img class="button"   src='<his:path src="/hisglobal/images/btn-ok.png"  />' onclick="setCannedDetail('MACRO')"    >
				    
				    </his:ButtonToolBarTag>
    	 
 
	</div>	 </div>
		
		</logic:present>
		 
		
<!-- report Container -->
<div id="container">
				<div id="blanket" style="display: none;"></div>
				<div draggable="true" id="popUpDiv" style="display: none;">
					<his:SubTitleTag name="Patient Report">
						<div class="vertpan pic">
							<img src='/HISInvestigationG5/hisglobal/images/delete_on.gif'
								onclick="popupClose('popUpDiv')"'>
						</div>
					</his:SubTitleTag>
					<table id="reportTable" width="100%">
						<tr id="deleteRow">
						</tr>
					</table>
					
					<left></left>
				</div>
				<!-- end #mainContent -->
			</div>
		
		
			 <his:ButtonToolBarTag>
				      <logic:equal name="InvResultValidationFB" property="newEntry" value="1">
				     <%-- <img class="button"   src='<his:path src="/hisglobal/images/btn-save-validate.png"/>' id="nextDiv1"  style="cursor:pointer;display:none"   tabindex="1" onclick ="revalidateSamplePatDetails();" > --%>
				     <a id="nextDiv1" href="#" class="btn btn-info btn-sm" style="display: none;" onclick="revalidateSamplePatDetails();">
          					<span class="glyphicon glyphicon-floppy-saved"></span> Save & Validate
        				</a>
				     
				     
				     </logic:equal>
				     
				     
				    	<%--  <img class="button"   src='<his:path src="/hisglobal/images/btn-mo.png"/>' id="nextDiv"  style="cursor:pointer;display:none"   tabindex="1" onclick ="displaySamplePatDetails();" > --%>
				    	<a id="nextDiv" href="#" class="btn btn-warning btn-sm"  style="display: none;" onclick="displaySamplePatDetails();">
          				<span class="glyphicon glyphicon-edit"></span> Modify
       					</a>
				    	
				         <logic:present name="<%=InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO %>">
				        
				        
				        <logic:equal name="InvResultValidationFB" property="newEntry" value="1">
				        <img class="button" src='<his:path src="/hisglobal/images/btn-save-validate-all.png"/>' id="saveDivAll"    onkeypress="if(event.keyCode==13) onSave();"  tabindex="1" onclick ="selectAll();" >
				        
				        
				          <img class="button" src='<his:path src="/hisglobal/images/btn-save-validate.png"/>' id="saveDiv"    onkeypress="if(event.keyCode==13) onSave();"  tabindex="1" onclick ="onSave();" >
				         	</logic:equal>			    
				         	
				         	
				         	<logic:equal name="InvResultValidationFB" property="newEntry" value="2">
				      <img class="button" src='<his:path src="/hisglobal/images/btn-mo-all.png"/>' id="saveDivAll"    onkeypress="if(event.keyCode==13) onSave();"  tabindex="1" onclick ="modifyAll();" >
				          <img class="button" src='<his:path src="/hisglobal/images/btn-mo.png"/>' id="saveDiv"    onkeypress="if(event.keyCode==13) onSave();"  tabindex="1" onclick ="onModify();" >
				       </logic:equal>
				        
				        <logic:equal name="InvResultValidationFB" property="ispreview"
						value="1">
				        
				         				    <img class="button" src='<his:path src="/hisglobal/images/cannedFile.png"/>'   onclick ="popupCallCanned();"    tabindex="1"   >
				          
				          <img class="button" src='<his:path src="/hisglobal/images/macro.png"/>'   onclick ="popupCallMacro();"    tabindex="1"   >
				         	  
				       </logic:equal>  				    
				         				    </logic:present>
				    <logic:present name="<%=InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">
				    <logic:equal name="InvResultValidationFB" property="ispreview"
						value="1">	
						<logic:equal name="InvResultValidationFB" property="ispreview"
						value="1">
				    	<logic:present
					name="<%=InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO %>"> <!-- to hide ccl button on list page -->
				    <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'id="cancel" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitFor();" tabindex="1" onclick ="submitFor();">
				    </logic:present>
				    </logic:equal>
				    </logic:equal>
				    </logic:present>
				    
				    </his:ButtonToolBarTag>
				    
		<%-- <logic:equal name="InvResultValidationFB" property="showStatus" value="1"> --%>	
		<logic:equal name="InvResultValidationFB" property="ispreview"
						value="1">	        
	<logic:present name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO%>">
				    <his:SubTitleTag>
		<his:name>
		
		
		
			<bean:message key="legends"/>
		</his:name>
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td width="70%"> </td>
			<td width="30%">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font><img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' tabindex="1" onclick="showLegends();" onkeypress="if(event.keyCode==13) showLegends();">		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font><img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' tabindex="1" onclick="showLegendsNone();" onkeypress="if(event.keyCode==13) showLegendsNone();">
			</div>
			</td>			
		</tr>
		</table>
	</his:SubTitleTag>
<%-- </logic:equal>	 --%>
</logic:present>
</logic:equal>
    <div id="divLegends" style="display:none">
	<his:ContentTag>
		<table width="100%" colspacing="1" colpadding="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
			<tr>
				<td width="20%">
					<font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <div align="left">
					    Ctrl + F9
					  </div>
					</font>
				</td>
				<td width="80%">
					<font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					  	Open Canned File
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td width="20%">
					<font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <div align="left">
					 Ctrl + F12
					  </div>
					</font>
				</td>
				<td width="80%">
					<font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					Close Canned File
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td width="20%">
					<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <div align="left">
					  Shift + F9
					  </div>
					</font>
				</td>
				<td width="80%">
					<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					 Open Macro File
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td width="20%">
					<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <div align="left">
					  Shift + F12
					  </div>
					</font>
				</td>
				<td width="80%">
					<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					 Close Macro File
					</div>
					</font>
				</td>				
			</tr>
			
		</table>
	</his:ContentTag>
	</div>
	
	
	<html:hidden name="InvResultValidationFB" property="sampleAreaName" />
               <html:hidden name="InvResultValidationFB" property="isSampleAreaSelected" />
       <html:hidden name="InvResultValidationFB" property="sampleAreaCode" />
	
	
	 <html:hidden name="InvResultValidationFB" property="resultValidationTemplateValue" />	 
	  <html:hidden name="InvResultValidationFB" property="parameterCode" />	     
	   <html:hidden name="InvResultValidationFB" property="parantParameterCode" />
	    <html:hidden name="InvResultValidationFB" property="requisitionDNo" />
	     <html:hidden name="InvResultValidationFB" property="resultValidationTemplateValueWithHash" />	
	     	     <html:hidden name="InvResultValidationFB" property="startDisplay" />	
	     	     	     	     <html:hidden name="InvResultValidationFB" property="hideDisplay" />
	     	     	     	     <html:hidden name="InvResultValidationFB"
			property="fileName" />
	     	     	     	     <input type="hidden"  name="cannedDataCount"  value="0" /> 
	     <input type="hidden"  name="cannedDetails"  value="0" />
	          <input type="hidden"  id="hiddenid4" name="userCannedCode" /> 
	       <input type="hidden"  id="hiddenidm" name="userMacroCode" /> 
	     <input type="hidden"  name="macroDataCount"  value="0" /> 
	     <input type="hidden"  name="macroDetails"  value="0" />	
	   <html:hidden name="InvResultValidationFB" property="ispreview" />  	    
	   <html:hidden name="InvResultValidationFB" property="patientType"/> 
	     
	 <his:status/>		    
</html:form>
</body>
</html>