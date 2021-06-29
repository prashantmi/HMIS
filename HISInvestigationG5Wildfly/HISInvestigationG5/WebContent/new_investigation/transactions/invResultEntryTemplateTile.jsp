<!-- 
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: SIDDHARTH SRIVASTAVA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : RESULT ENTRY ACTION
 ## Purpose						        : 
 ## Date of Creation					: 13/04/2015
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
-->

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="new_investigation.vo.Inv_RequisitionRaisingPatientVO"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page import="new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB"%>

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
<%@page import="new_investigation.transactions.controller.fb.InvResultEntryFB"%>
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

<%-- <%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%> --%>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">


<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />

<script src="scripts/js/jquery-3.3.1.js" type="text/javascript"></script>

<his:css src="/hisglobal/css/jquery/jquery.ui.menu.css" />
<%-- <his:css src="/hisglobal/css/jquery-ui.css" /> --%>
<his:css src="/hisglobal/css/style.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.autocomplete.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/invPopup.css" />
<%-- <his:css src="/hisglobal/css/icon.css" /> --%>
<his:css src="/hisglobal/css/email.css" />
<his:css src="/hisglobal/css/textboxcss.css" />
<his:css src="/hisglobal/css/drop.css" />
<his:css src="/hisglobal/css/Cannedstyle.css" />
<his:css src="/hisglobal/css/invPopupReport.css" />

<!-- <link rel="stylesheet" href="/new_investigation/css/Date/site-demos.css"> -->
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<%-- <his:javascript src="/hisglobal/js/commonUtility.js" /> --%>
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/cannedMacroValidation.js" />
<his:javascript src="/hisglobal/js/cannedMacroAutocomplete.js" />
<%-- <his:javascript src="/bloodbank/js/bloodRequisition.js" /> --%>
<his:javascript src="/new_investigation/js/reportsValidation.js" />
<his:javascript src="/new_investigation/js/onlinePatientAcceptance.js" />

<%-- <his:javascript src="/new_investigation/js/jquery-1.11.1.min.js" /> --%>

<his:javascript src="/new_investigation/js/jquery.validate.email.js" />
<his:javascript src="/new_investigation/js/additional-methods.min.js" />
<his:javascript src="/new_investigation/js/saveToDraftJS.js" />
<%-- <his:javascript src="/new_investigation/js/ckeditor/ckeditor.js"/> --%>
<script type="text/javascript" src="/HISInvestigationG5/new_investigation/js/ckeditor/ckeditor.js" charset="utf-8"></script>

<his:javascript src="/new_investigation/js/wysiwyg.js" />
<his:javascript src="/new_investigation/js/wysiwyg-settings.js" />
<his:javascript src="/hisglobal/js/css-pop-inv.js" />


<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<!-- <script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script> -->


<%-- <his:javascript src="/hisglobal/js/jquery-1.7.2.js" /> --%>

<%@include file="/hisglobal/invsnomed.html"%>

 
<link rel="stylesheet" href="/HISInvestigationG5/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<link rel="stylesheet" href="/HISInvestigationG5/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<!-- <link href="/HISInvestigationG5/hisglobal/snomedct/css/searchtool.css" rel="stylesheet"> -->

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.searchabledropdown-1.0.8.min.js"></script>
<!-- <script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script> -->
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
	
<!-- added by krishnan nema on 12/02/2019  -->
<his:javascript src="/new_investigation/js/specialCharacterRemover.js" />


<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="new_investigation.vo.template.ResultEntryVO"%>
<%@page import="new_investigation.vo.template.ResultEntryVOGroupByValidatedBy"%>



<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="new_investigation.transactions.controller.fb.InvResultEntryFB"%>





<%-- <his:javascript src="/hisglobal/js/jquery-ui.js" /> --%>
<his:javascript src="/hisglobal/js/sweet-alert.min.js" />
<!-- <script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script> -->
<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

<%@page import="new_investigation.InvestigationConfig"%>

<head>
<meta charset="utf-8">

<!-- Added by Prashant -->
<script src="scripts/js/jquery-3.3.1.js" type="text/javascript"></script>
<script src="media/dataTables/DataTables-1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>
<link href="media/dataTables/DataTables-1.10.18/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />

<script src="media/dataTables/Responsive-2.2.2/js/dataTables.responsive.min.js" type="text/javascript"></script>
<script src="media/dataTables/Buttons-1.5.6/js/dataTables.buttons.min.js" type="text/javascript"></script>
<script src="media/dataTables/Buttons-1.5.6/js/buttons.colVis.min.js" type="text/javascript"></script>

<link href="media/dataTables/Responsive-2.2.2/css/responsive.dataTables.min.css" rel="stylesheet" type="text/css" />
<link href="media/dataTables/Buttons-1.5.6/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />

<script src="media/jquery-ui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<link href="media/jquery-ui/1.12.1/jquery-ui.min.css" rel="stylesheet" type="text/css" />
 
 
<script type="text/javascript" src="media/misc/moment.min.js"></script>
<script type="text/javascript" src="media/misc/datetime-moment.js"></script>
<script src="media/common/js/commonDateOperations.js" type="text/javascript"></script>
<!-- Added by Prashant end-->


</head>

<html>



<style>
#blanket {
	background-color: #111;
	opacity: 0.65;
	*background: none;
	position: absolute;
	z-index: 9001;
	top: 2px;
	left: 0px;
	width: 100%;
}

#popUpDiv5 {
	position: absolute;
	background: #CCE6FF;
	width: 400px;
	height: 250px;
	border: 5px solid #000;
	z-index: 9002;
}

#popUpDiv5 a {
	position: relative;
	top: 1px;
	left: 20px
}

#blanketcanned {
	background-color: #111;
	opacity: 0.65;
	*background: none;
	position: absolute;
	z-index: 9001;
	top: 2px;
	left: 0px;
	width: 100%;
}

#popUpDiv5canned {
	position: absolute;
	background: #CCE6FF;
	width: 400px;
	height: 200px;
	border: 5px solid #000;
	z-index: 9002;
}

#popUpDiv5canned a {
	position: relative;
	top: 1px;
	left: 20px
}
</style>

<script type="text/javascript">


function getechodata(reqdno)
{
	
	var remarks = "";
	var flg = false;
	var labTestCodeArray = "";
	var _mode = "GETECHODATA";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/invResultValidationTemplateTile.cnt?hmode="+_mode+"&ECHODNO="+reqdno, sync:true, postData: "", handleAs: "text",
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

function myuploadremoveFunction(obj)
{

var idd=obj.id;
idd=idd.split("@@")[2];
document.getElementById(idd).value="";
document.getElementById(obj.id).style.display = "none";
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


function getuploadfiledata(reqdno,testparaname)
{
	//alert("setInSession"+dependentelementcodevalue);

	  //vall = vall.replace(/#/g, '^');
	//alert("new"+selectedText+"#"+reqdno);
	
	//alert(tmpSampleCode+"  "+tmpLabCode+"     "+tmpTestCode+"    "+tmpLabTestCodeArray);
	var remarks = "";
	var flg = false;
	var labTestCodeArray = "";
	var _mode = "GETFILEUPLOADDATATESTWISE";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/invResultValidationTemplateTile.cnt?hmode="+_mode+"&requisitionDNo="+reqdno+"&testParaMeterCode="+testparaname, sync:true, postData: "", handleAs: "text",
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
		 var file = "";
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
		  } 
		  else
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
		  // alert("flagisvalid"+flagisvalid+"pp"+file.name);
			
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
	    	 document.getElementsByName('fileuploaddatabase64')[0].value=document.getElementsByName('fileuploaddatabase64')[0].value+"#@#@"+reqdno+"@@"+filename+"@@"+reader.result+"@@"+testpatacode;;
		     
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

function TestFileType( fileName, fileTypes ,x) {

	
	 var ext = fileName.split('.').pop();
	   
	 if(ext=="pdf"){
	   //  alert("Please only upload Pdf files.");
	   } 
	 else
	   {
		   alert("Please only upload Pdf files.");
		   x.files[0]=null;
		   return false;
		   }
		   
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
	     //   alert(fileByteArray[i]);
	    }
	  }
	}
}



 var reqformtestnames="";
 var reqformtestcodes="";
 var mywindoww="";




 function templatecombocall(obj)
 {

 	var reqno=obj.id;
 	reqno=reqno.split("#")[0];
 	reqno=reqno.substr(0, 22);
 	var reqdno=obj.id;
 	reqdno=reqdno.split("#")[0];
 //	alert(reqdno);
 /* alert("id"+obj.id);
 alert(obj.value);
  */
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

     var val=checkIsparameterDependent(testcode,reqno,selectedText,reqdno);
   //  alert(val);
   //  alert(val.includes("Hyper Link"));
      if(val.includes("Hyper Link"))
        {

         //alert("insidee");
     	 var idd=obj.name;

     	 var idd1=idd.split("#")[0];
     	 var idd2=idd.split("#")[1];
     	 var idd3=idd.split("#")[2];
     	 var idd4=idd.split("#")[3];


     	 var textareaparacode=val.split("@@@")[0];
     	// alert("sss"+textareaparacode);
     	 textareaparacode=textareaparacode.split("###")[1];
     	 var hyperparacode=val.split("@@@")[0];
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
        //  document.getElementById(textareafinalhide).value = "";
          

         }
           else
               {
            // alert("except posit"+hyperfinalhide);
             var frt=hyperfinalhide.split("#");
             var idfordelete1="idd"+frt[1]+"#"+frt[2]+"#"+frt[3]+"#"+frt[4];
         //    alert("idfordelete1"+idfordelete1);
                if(document.getElementById(idfordelete1)!=null)
             document.getElementById(idfordelete1).innerHTML  = "";

             
           //  alert(frt);
               document.getElementById(hyperfinalhide).style.display = "none";
               document.getElementById(hyperfinalhidee).style.value = "";

               var idfordelete="divId_"+frt[1]+"#"+frt[2]+"#"+frt[3]+"#"+frt[4];
               
           //  alert("except posit"+idfordelete);
               document.getElementById(idfordelete).innerHTML  = "";
               
               document.getElementsByName('hyperLinkTableSession')[0].value=""
             	  document.getElementsByName('selectValuemapping')[0].value="";
                // document.getElementById(textareafinalhide).disabled = false;
                 document.getElementById(idfordelete1).innerHTML  = "";
                 
                 
               }
      
  	   }

     
 }


  function checkIsparameterDependent(dependentelementcodevalue,reqno,selectedindex,reqdno)
  {
  	
  	var remarks = "";
  	var flg = false;
  	var labTestCodeArray = "";
  	var _mode = "CHECKCISPARAMETERDEPENDENT";
  	var objXHR = {url: "/HISInvestigationG5/new_investigation/invResultEntryTemplateTile.cnt?hmode="+_mode+"&requisitionNo="+reqno+"&dependentelementcodevalue="+dependentelementcodevalue+"&selectedindex="+selectedindex+"&requisitionDNo="+reqdno, sync:true, postData: "", handleAs: "text",
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
  
 
function newtestraised(val)
{

	
   var	concatenateChkBoxVal=val;
   
	//document.getElementsByName('selectedCheckbox')[0].value=concatenateChkBoxVal;
	//document.getElementsByName('hmode')[0].value='GETPATDTL';
	var mode="GETPATDTL";
	var url1="/HISInvestigationG5/new_investigation/invResultEntryTemplateTile.cnt";
	concatenateChkBoxVal = concatenateChkBoxVal.replace(/#/g, "$");
//	+"&showStatus="+status
	mywindow=window.open (url1+"?hmode="+mode+"&selectedCheckbox="+concatenateChkBoxVal,"_blank","scrollbars=1,directories=no, status=no,width=1100, height=600,top=100,left=250");
 	//document.forms[0].submit();



}

function ff()
{
	//alert("hello1");
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
      for(var a=0;a<document.getElementsByName("chkResultEntryPatient").length;a++)
  {
     	// alert(a);
     	 //alert(document.getElementsByName("chkResultEntryPatient")[a].value);
     	 //alert((document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[6]);
     	 overalltest+=(document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[6]+";";
     	 
   }


      for(var a=0;a<document.getElementsByName("chkResultEntryPatient").length;a++)
      { 
                 //alert(a+"len"+document.getElementsByName("chkResultEntryPatient").length);
     	 var valuess=(document.getElementsByName("chkResultEntryPatient")[a].value).split("#");

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


            if((document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[27]=="1")
                {
                 var cell1 = row.insertCell(0);
            		var cell2 = row.insertCell(1);
            		cell1.innerHTML = (document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[17];

            		if((document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[27]=="1")
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


            if((document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[27]=="1")
                {
            var cell1 = row.insertCell(0);
            		var cell2 = row.insertCell(1);
            		cell1.innerHTML = (document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[17];

            		if((document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[27]=="1")
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
                  

                                   		           if((document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[27]=="1")
                                   		               {
                                   		           var cell1 = row.insertCell(0);
                                   		           		var cell2 = row.insertCell(1);
                                   		           		cell1.innerHTML = (document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[17];

                                   		           		if((document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[27]=="1")
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
                                         				
                                         				if((document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[27]=="1")
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


                     		           if((document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[27]=="1")
                     		               {
                     		           var cell1 = row.insertCell(0);
                     		           		var cell2 = row.insertCell(1);
                     		           		cell1.innerHTML = (document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[17];

                     		           		if((document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[27]=="1")
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
                                        	   
                           		           row.id=testCode+"#"+reqNo;
                          		           row.name=testName;
                          		           row.value=labName;
                                				row.testt=testCode;
                          		           reqformtestnames+=testName;
                          		           reqformtestcodes+=testCode;


                          		           if((document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[27]=="1")
                          		               {
                          		           var cell1 = row.insertCell(0);
                          		           		var cell2 = row.insertCell(1);
                          		           		cell1.innerHTML = (document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[17];

                          		           		if((document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[27]=="1")
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
                                         				
                                         				if((document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[27]=="1")
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
 

 function checkreqformTestType(TestCode)
 {
 	
 	//alert("setAptNoUsingAjax"+appoitmentNo+"labcode"+tmpLabCode+"testCode"+tmpTestCode);
 	
 	var flg = false;
 	var remarks = "";
 	var _mode = "AJX_CHECK_REQFORM_TESTTYPE";
 	var objXHR = {url: "/HISInvestigationG5/new_investigation/invResultEntryTemplateTile.cnt?hmode="+_mode+"&testCodee="+TestCode, sync:true, postData: "", handleAs: "text",
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
		//var b = document.getElementsByName("patientType")[0].value; 		
 		//alert(b);	
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

 function onComment()
 {
	// alert(document.getElementsByName('currentElement')[0].value);
 	//alert(selectedSNOMEDTerm);
 	//alert(selectedSNOMEDTerm.split(","));
	 	document.getElementsByName('currentElement')[0].value='TA';
 }
 
 
 function checkBillingAddendumTest1(valuee)
 {
 	var autoGenFormate = "";
 	var resultEntryTemplateValu="";
    var values="";
     // alert("ajax");
 	
 	 values=valuee;
     alert(values);
      for(var j=0;j<values.length;j++)
          {
     values=values.replace("#","@");
          }
   
 	var flg = false;
 	
 	var _mode = "AJX_CHECK_BILLING";
 	var objXHR = {url: "/HISInvestigationG5/new_investigation/invResultEntryTemplateTile.cnt?hmode="+_mode+"&selectedCheckbox="+values, sync:true, postData: "", handleAs: "text",
 		load: function(data) 
 		{
 		//	alert("aj"+data);
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
 
 
 function checkBillingAddendumTest(obj)
 {
 	var autoGenFormate = "";
 	var resultEntryTemplateValu="";
    var values="";
     // alert("ajax");
 	
 	 values=obj.value;
   //   alert(values);
      for(var j=0;j<values.length;j++)
          {
     values=values.replace("#","@");
          }
   
 	var flg = false;
 	
 	var _mode = "AJX_CHECK_BILLING";
 	var objXHR = {url: "/HISInvestigationG5/new_investigation/invResultEntryTemplateTile.cnt?hmode="+_mode+"&selectedCheckbox="+values, sync:true, postData: "", handleAs: "text",
 		load: function(data) 
 		{
 		//	alert("aj"+data);
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
    var menuu="Requisition Form";
    var url=url1+"?testCode="+testCode+"&testName="+testName+"&labCode="+labCode+"&labName="+labName+"&hmode="+hmode+"&status="+status+"&requisitionDNo="+requisitionDNo+"&reqformtestnames="+reqformtestnames+"&reqformtestcodes="+reqformtestcodes;
 	callMenu(url,menuu);

 	//mywindow=window.open (url1+"?testCode="+testCode+"&testName="+testName+"&labCode="+labCode+"&labName="+labName+"&hmode="+hmode+"&status="+status+"&requisitionDNo="+requisitionDNo,"_blank","scrollbars=1,directories=no, status=no,width=1000, height=500,top=200,left=500");
 }     

	function callMenu(url,menu)
	{
		//alert("call");
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
	var status="2";
	//10811906070910000101#0#template#11051130
	//var values= obj.name.split('#');
	var matchid=thiss.id;
	var values= matchid.split('#');
	//alert(a);

     var echodata=  getechodata(values[0]);

     
//	var elementCode=thisobj.name.split('#')[3];
	mywindow=window.open (url1+"?requisitionDNo="+values[0]+"&showStatus="+status+"&testParaCode="+values[3]+"&echodata="+echodata,"_blank","scrollbars=1,directories=no, status=no,width=1000, height=550,top=150");
	/*mywindow.moveTo(300,300); */
		
}

function openPopup(url, height, width) {


	
		child = window.open(url, 'popupWindow',
				'status=yes,scrollbars=yes,height=' + height + ',width='
						+ width + ',left=10,top=10');
		child.moveTo(250, 250);
		child.focus();

		if (!child.opener)
			child.opener = self;
	
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
	var resultEntryTemplateValu="";

    // alert("ajax");
	var cbs =document.getElementsByName('chkResultEntryPatient');
	for(var i=0; i < cbs.length; i++)
		{
		
	var checkBoxId=cbs[i].id;
	var values=document.getElementsByName('chkResultEntryPatient')[i].value;

    var labcode=values.split("#")[5];
     
	//alert(labcode);
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
	resultEntryTemplateValue.toString();
	//alert(resultEntryTemplateValue);
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



var sizeee=0;
//var counter=1;
var crnoo="";
var counterMap = new Map(); 

function showBrowse(crno,reqno,sampleno,sampleno1)
{
	var finalsampleno=sampleno+""+sampleno1;
	var shwbrowse="shwbrowse"+finalsampleno;
	alert(sampleno);
	alert(sampleno1);
	alert( finalsampleno);

	var uploadshw="uploadshw"+finalsampleno;
	var addtabl="addtabl"+finalsampleno;
	alert(crno);
	document.getElementsByName('sizeOfFile').value=0;
	document.getElementById(shwbrowse).style.display='none';
	document.getElementById(uploadshw).style.display='';
	document.getElementById(addtabl).style.display='';
	}

 function addfile(crno,reqno,reqno1,sampleno,sampleno1)
{
	 var filecounter = 0;
	 if(counterMap.get(crno+"#"+reqno+reqno1+"#"+sampleno1) == null){
		filecounter = filecounter+1;
		 counterMap.set(crno+"#"+reqno+reqno1+"#"+sampleno1,filecounter);
	}else{
			filecounter = counterMap.get(crno+"#"+reqno+reqno1+"#"+sampleno1);
			filecounter = filecounter+1;
			counterMap.set(crno+"#"+reqno+reqno1+"#"+sampleno1,filecounter);
			
		}
	

	
		
	 var finalsampleno=sampleno+""+sampleno1;
	// alert("finalsampleno = "+finalsampleno);
	 //alert(crno);
	// alert(reqno);
	 document.getElementById("divfile"+finalsampleno).style.display='';
	var table = document.getElementById("filetable"+finalsampleno);
	var table1 = document.getElementById("filerow"+finalsampleno);
	//alert(table);
/* if(document.getElementsByName("sizeOfFile").value==undefined)
	document.getElementsByName("sizeOfFile").value=0;
	
 */
 var sizee=document.getElementsByName("sizeOfFile").value;
 
//alert(sizee+counter);
var row = table1.insertCell(filecounter);
var f=1;
var finalreqno=reqno+""+reqno1;
var crreqnoFile1=crno+"#"+finalreqno;

row.innerHTML = "<font color='#FF0000' size='4'>"+"File "+filecounter+":</font><input type='file' name='file["+sizeee+"]'><input type='hidden' name='crreqnoFile' value='"+crreqnoFile1+"'>";
//alert(filecounter);
///counter++;

document.getElementById('hidetbl'+finalsampleno).style.display='';
document.getElementsByName("sizeOfFile").value++;
sizeee++;
}

 
 function hidefile(crno,reqno,reqno1,sampleno,sampleno1)
 {
	 var filecounter = counterMap.get(crno+"#"+reqno+reqno1+"#"+sampleno1);
	//alert("hh");
	var finalsampleno=sampleno+""+sampleno1;
 if(filecounter>1)
	{
	 //filecounter--;
	 //alert(document.getElementById("filerow"+finalsampleno).value);
	 document.getElementById("filerow"+finalsampleno).deleteCell(filecounter);
	 //alert(document.getElementById("filerow"+finalsampleno).value);
	 sizeee--;
	}
if(filecounter==1)
	{
	document.getElementById("divfile"+finalsampleno).style.display='none';
	document.getElementsByName("hideminus")[0].style.display='none';
	} 

	//document.getElementById("filerow"+finalsampleno).deleteCell(filecounter);
	/* if(filecounter==1){
		document.getElementById("divfile"+finalsampleno).style.display='none';
		document.getElementsByName("hideminus")[0].style.display='none';
		} */
	 filecounter--;
	 counterMap.set(crno+"#"+reqno+reqno1+"#"+sampleno1,filecounter);
 }	 
 
CKEDITOR.config.keystrokes = [];
setInterval(function() {
	///alert(document.getElementsByName("chkSamplePatient")[0].value); 
    
 }, 5000);
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


function hidePatDetails(k)
{
document.getElementById("showhide"+k).style.display="none";
document.getElementById("hide"+k).style.display="none";
document.getElementById("show"+k).style.display="";	
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
 	
 	//alert(document.getElementsByName("labCode")[0].value);
 	
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
    	alert("Select Name  ");
	    document.getElementsByName("tempPatCRNo")[0].focus();
	    return false;
     }  
 
	}
if(genTypeValue=="T")
	{
	 if(document.getElementsByName("testWiseCode")[0].value=="-1")
     {
    	alert("Select Test Name ");
	    document.getElementsByName("testWiseCode")[0].focus();
	    return false;
     }
 
	}
if(genTypeValue=="L")
	{
	 if(document.getElementsByName("fromLabNo")[0].value=="-1")
     {
    	alert("Select From Lab No ");
	    document.getElementsByName("fromLabNo")[0].focus();
	    return false;
     }
 
	 if(document.getElementsByName("toLabNo")[0].value=="-1")
     {
    	alert("Select To Lab No ");
	    document.getElementsByName("toLabNo")[0].focus();
	    return false;
     }
	}
if(genTypeValue=="S")
	{
	  
	 if(document.getElementsByName("fromSampleNo")[0].value=="-1")
     {
    	alert("Select From Sample No  ");
	    document.getElementsByName("fromSampleNo")[0].focus();
	    return false;
     }

	 if(document.getElementsByName("toSampleNo")[0].value=="-1")
     {
    	alert("Select To Sample No ");
	    document.getElementsByName("toSampleNo")[0].focus();
	    return false;
     }
	 
	}  


if(genTypeValue=="TG")
{
	 if(document.getElementsByName("testGroupCodeWise")[0].value=="-1")
     {
    	alert("Select Test Group  Name ");
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
					       		alert("Select Lab Name ");
					       		 
					       		document.getElementsByName("labCode")[0].focus();
					       		return false;
					      	}
					       	 
					           
					           var genTypeValue=document.getElementsByName('generationType')[0].value;
					       	//alert("onload"+genTypeValue); showOnLoad
					        
					        
					        if(genTypeValue=="P")
					       	{
					       	 if(document.getElementsByName("tempPatCRNo")[0].value=="-1")
					            {
					           	alert("Select Name ");
					       	    document.getElementsByName("tempPatCRNo")[0].focus();
					       	    return false;
					            }  
					        
					       	}
					       if(genTypeValue=="T")
					       	{
					       	 if(document.getElementsByName("testWiseCode")[0].value=="-1")
					            {
					           	alert("Select Test  Name ");
					       	    document.getElementsByName("testWiseCode")[0].focus();
					       	    return false;
					            }
					        
					       	}
					       if(genTypeValue=="L")
					       	{
					       	 if(document.getElementsByName("fromLabNo")[0].value=="-1")
					            {
					           	alert("Select From Lab No");
					       	    document.getElementsByName("fromLabNo")[0].focus();
					       	    return false;
					            }
					        
					       	 if(document.getElementsByName("toLabNo")[0].value=="-1")
					            {
					           	alert("Select To Lab No ");
					       	    document.getElementsByName("toLabNo")[0].focus();
					       	    return false;
					            }
					       	}
				    	       if(genTypeValue=="S")
					       	{
					       	  
					       	 if(document.getElementsByName("fromSampleNo")[0].value=="-1")
					            {
					           	alert("Select From Sample No");
					       	    document.getElementsByName("fromSampleNo")[0].focus();
					       	    return false;
					            }

					       	 if(document.getElementsByName("toSampleNo")[0].value=="-1")
					            {
					           	alert("Select To Sample No");
					       	    document.getElementsByName("toSampleNo")[0].focus();
					       	    return false;
					            }
					       	 
					       	}  


					       if(genTypeValue=="TG")
					       {
					       	 if(document.getElementsByName("testGroupCodeWise")[0].value=="-1")
					            {
					           	alert("Select Test Group  Name ");
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


function editRemarks(crno,reqno)

{
	
	alert(crno);
	alert(reqno);
	alert(document.getElementById(crno+"#"+reqno).value);
	
	
}



//call this function to generate auto complete lists in runtime by passing the value of object list, id of the input tag and hidden tag. puneet.
function generateAuto(objList,autoId,hiddenId)
{//alert("generate list");
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

function displaySamplePatDetails()
{	
	var count=0;
	document.getElementsByName('isPatDetailPage')[0].value="1";
	
	var concatenateChkBoxVal="";
	//var cbs = document.getElementsByTagName('input');
	var cbs =document.getElementsByName('chkSamplePatient');
	//alert(cbs);
	for(var i=0; i < cbs.length; i++) {
	 // if(cbs[i].type == 'checkbox') 
    //{
    	
    	if(cbs[i].checked)
    	{
    		//alert(cbs[i].value);
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
	//alert(concatenateChkBoxVal);
	document.getElementsByName('selectedCheckbox')[0].value=concatenateChkBoxVal;
	document.getElementsByName('hmode')[0].value='SHOWPATDETAILS';
	
	/*  var cannedVal=document.getElementsByName("cannedLabCode")[0].value;
	 var details=  getcannedDetail(cannedVal);
	 document.getElementsByName("cannedDetails")[0].value=details;
	 alert(details);
	 alert(document.getElementsByName("cannedDetails")[0].value); */
 	document.forms[0].submit();
 	
	}
function ValidateSameCrNo(obj)
{

	var isbilled="";
    var value=obj.value.split("#");
     var name=obj.name;
//	alert(value[4]);
	if(obj.checked)
	{
		
     if(value[4]!=null)   //to check test addendum or not
         {
              if(value[4]=="1")
            isbilled= checkBillingAddendumTest(obj);     
         }

           if(isbilled=="2")     //status 2 for unbilled and 1 for billed

               {

               alert("Required Billing ");
            obj.checked=false;
            //document.getElementById('nextDiv').style.display="none";
                    return false;                 
               }
           else
               {
        	   document.getElementById('nextDiv').style.display="";
               }
	}

	
	if(obj.checked)
	{
		if(value[4]=='null')   //to check test addendum or not
        {
		document.getElementById('nextDiv').style.display="";
        }       
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
			    		 
			    	//alert("please select same cr no");
			    	//document.getElementById('nextDiv').style.display="none";
			    //	obj.checked=false;
			    //	return false;
			    	} 
				}
		}
	}
		
}
function showPatDetails(k)
{
	//alert(k);
document.getElementById("showhide"+k).style.display="";
document.getElementById("hide"+k).style.display="";
document.getElementById("show"+k).style.display="none";	
	
}

function hidePatDetails(k)
{
	//alert(k);
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
	
	
function cancelFunc()
{
	window.parent.closeTab();
}


function selectAll()
{	var cbs=document.getElementsByName('chkResultEntryPatient');

	
for(var i=0; i < cbs.length; i++)
cbs[i].checked=true;
	
	onSave();
	}
	
function onSave()
{
	
	var count=0;
	var concatenateChkBoxVal="";
	var cbs =document.getElementsByName('chkResultEntryPatient');
	
	 var name;
	  var splitTemplateValue;
	  var reqNO=[];
	  var parameterValue=[];
	  var parameterCode=[];
	  var parantParameter=[];
	  var resultEntryTemplateValue=[];
	  var empCode;

		 //alert("cbs.length"+cbs.length);
		 
	for(var i=0; i < cbs.length; i++)
	{
	if(cbs[i].checked)
    	{

		/* if(document.getElementsByName('empNameWise')[i] != null)
			empCode = document.getElementsByName('empNameWise')[i].value;
		else
			empCode = "0"; */

			empCode = "0"
		//alert(empCode);
		
		document.getElementsByName('chkResultEntryPatient')[i].value = document.getElementsByName('chkResultEntryPatient')[i].value + "#" +empCode 
       
		
        var commentsboxdata=document.getElementsByName('chkResultEntryPatient')[i].value;
		
        if(commentsboxdata.split("#")[19]!="NA")
            {

        	var sampleid=commentsboxdata.split("#")[20];
    		sampleid=sampleid.split("/")[0];

    	//	alert("sampleid"+sampleid);
    		
        commentsboxdata=commentsboxdata.split("#")[0]+"#"+commentsboxdata.split("#")[1]+"#"+sampleid;
            }else
        	commentsboxdata=commentsboxdata.split("#")[0]+"#"+commentsboxdata.split("#")[1]+"#";    
       // alert("id"+commentsboxdata);
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
        	
        	document.getElementsByName('chkResultEntryPatient')[i].value = document.getElementsByName('chkResultEntryPatient')[i].value + "#" +empCode+"#"+document.getElementById(commentsboxdata).value;        	

            }
        else
            {

        	document.getElementsByName('chkResultEntryPatient')[i].value = document.getElementsByName('chkResultEntryPatient')[i].value + "#" +empCode+"#"+" ";

            }
        
         /* Added By prashant For Indication */
        var indicationInputId="indicationInput#"+document.getElementsByName('chkResultEntryPatient')[i].value.split("#")[1];
      // alert(indicationInputId); 
       var indicationInputElement = document.getElementById(indicationInputId);
      // alert(indicationInputElement.value);
      // alert(document.getElementsByName('chkResultEntryPatient')[i].value);
       
        if(indicationInputElement && indicationInputElement.value!=null && indicationInputElement.value!=""){
        	var checkspes = checkReservedCahracters(indicationInputElement.value);
      	  if(checkspes==true){
  			alert("Indication contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
  			return;
          	}else{} 
      	  indicationInputElement.value = removeSpecialCharacter(indicationInputElement.value);
      	  
        	document.getElementsByName('chkResultEntryPatient')[i].value=document.getElementsByName('chkResultEntryPatient')[i].value+"#"+indicationInputElement.value;
        }else{
        	
        	document.getElementsByName('chkResultEntryPatient')[i].value=document.getElementsByName('chkResultEntryPatient')[i].value+"#"+" "
        }
        //alert(document.getElementsByName('chkResultEntryPatient')[i].value);
        
        
        //   alert("commentbox data"+document.getElementById(commentsboxdata).value);
		 
		
		count++;	
	        var k=0;
           //alert(document.getElementById(i+'templateValue').getElementsByTagName("input"));
             
//          
//         alert("get_tags length"+get_tags.length);
//            for (var i=0; i<get_tags.length; i++) {
//         	    // assigns style properties
//         	     var name=get_tags[i].name;
//         	     alert("i"+name);
//         	  }
        	 var checkBoxId=cbs[i].id;


        	
        	// var splitTheCheckBoxId=checkBoxId.substr(0,2);
        	 
        	 var splitTheCheckBoxId=checkBoxId.replace('chkBOx', '');
			 
        	// alert(splitTheCheckBoxId);
        	 
         //   alert("document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName('input').length"+document.getElementById(splitTheCheckBoxId+'templateValue'));
       // return false;
             for(k;k<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input").length;k++)
        	    {
            //         alert("input");
             	
                 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
              //   alert(values);
                 get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input");
                   name=get_tags[k].name;
                  // alert(name);
                   id=get_tags[k].id;
                 //  alert(id);
                   //alert("id for "+k+"    "+id);
                   hiddenid="hiddenid"+id;
                   defaultid="default"+hiddenid;
              	  typ=get_tags[k].type;
              	//  alert(typ);
              	//  alert(get_tags[k].onclick=='callOnClick(this)');
              	 // return false;
              	  hidddentext="hidden";
              	  checkboxcheck="checkbox";              		
              		
              		
                // alert("type is " + typ);
                   if(typ!=hidddentext)
                   {
                  	 
                 // 	 alert("not hidden");
                   splitTemplateValue=name.split("#");
                   
                   reqNO.push(splitTemplateValue[0]);
                    parameterValue=splitTemplateValue[3];
                  //  alert("parameter value    "+parameterValue);
              //   alert(parameterValue);
                    parameterCode.push(parameterValue.substring(0, 5));
                //    alert( parameterCode.push(parameterValue.substring(0, 5)));
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
                           //    alert("filevalue"+resultValidationTemplateValue);
                               
                               var x = document.getElementById(name);
              				 var file = x.files[0];

              				 /* alert("filefile"+file);
              		       if( file!=undefined)
                  		       {
                  		       alert("yes");
                  		       }
              		       else
                  		       {
                  		       alert("bno");
                  		       }
              		       return null; */
              		       
                            if( file!=undefined)
                        	   resultValidationTemplateValue="File Uploaded";

                               }
                           else    
                      resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value;
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
               	   
                    //  alert(resultValidationTemplateValue);
                         var orderingValue= document.getElementsByName(name+"#order")[0].value;
                          name+='#'+resultValidationTemplateValue+'#'+values+"#"+"-"+"#"+orderingValue;
                    
                   //  alert("add in "+name);    
                       concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                       concatenateChkBoxVal+='@';
                   //    console.log("aa"+concatenateChkBoxVal);
                     //  alert(concatenateChkBoxVal);
                        }
                   }
                          
                          
		          
		          
	             }
        // alert(document.getElementById(i+'templateValue').getElementsByTagName("select").length);
       // alert("select value"+document.getElementById(i+'templateValue').getElementsByTagName("select")[0].value);
        var j=0;
         for(j;j<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select").length;j++)
    	       {
        	 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
     		//alert("The value is"+values);
        	  get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select");
       	   name=get_tags[j].name;
        	 
        	// alert("inside here");
        	//alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length);
            	 /* if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value=="-1" || document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value=="")
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
	        	  {  //alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value)
	        	   	  multiValue+=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value;
	        	      multiValue+="$";   	  
	        	  
	        	  }
	        	 resultEntryTemplateValue=multiValue
	        	  }
	          
	        else
	       	resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value;
		   
	        
	    //    alert(resultEntryTemplateValue);
	      var orderingValue= document.getElementsByName(name+"#order")[0].value;
	        name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-"+"#"+orderingValue;

     		  	concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
    			concatenateChkBoxVal+='@';

    	       
    	       } 
         
         
        
         
    
         //alert("text Area value"+document.getElementById(i+'templateValue').getElementsByTagName("textarea")[0].value);
         var n=0;
          for(n;n<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea").length;n++)
     	       {
    	       
 	             
        	  var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
        	  
        	  
        	  
      		//alert("The value is"+values);
        	  //alert("inside here");
 	             get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea");
 	        	   name=get_tags[n].name;
 	         	// alert("name1"+name);
 	              /* if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value=="")
 	         	   {
 	 	         	   
 				      alert("Enter the field Focussed");
 				      document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].focus();
 			          return false;
 		           } */
 	           //  document.getElementsByName('resultEntryTemplateValue')[0].value=document.getElementById(i+'templateValue').getElementsByTagName("input")[j].value;	 
                
 	         //  alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].id);
 	         var id1 = document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].id;
 	           var editor = CKEDITOR.instances[id1];
 	       
 	         
 	           if(editor!=null){
 	        	 
 	        	  
            //  alert( editor.getData() );
              
              var resultEntryTemplateValue=editor.getData();
            // alert(resultEntryTemplateValue);
              
              //added by krishnan nema on 16/10/2018
              /* var res  = resultEntryTemplateValue.replace("$", " ", true);
  	         var res2  = res.replace("#", " ", true);
  	         var res3 = res2.replace("~", " ", true);
  	         var res4 = res3.replace("`", " ", true);
  	       resultEntryTemplateValue = res4.replace("@", " ", true); */
		//alert("ewrwe");
  	     resultEntryTemplateValue = resultEntryTemplateValue.replace("&#39;",
   	    	     "<img id='base64image'src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAYAAAAMCAIAAADONVt5AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAlSURBVBhXY/iPAUgSur8gKmrBfQgbr1DdQQiTNOORAZlC//8DADcC0LbmFlSzAAAAAElFTkSuQmCC' />",true);
         
  	  	 var check23 = checkReservedCahracters(resultEntryTemplateValue);
  	  	 //alert("cehck23 "+check23);
    	  if(check23==true){
        	//alert("editor");
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
              var orderingValue= document.getElementsByName(name+"#order")[0].value;
              
	          name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-"+"#"+orderingValue;
                
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
     	      
		          var orderingValue= document.getElementsByName(name+"#order")[0].value;
		          
		          name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-"+"#"+orderingValue;
		          
 	        	  }
     		  	concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
    			concatenateChkBoxVal+='@';
             //alert(concatenateChkBoxVal);
     	       
     	       } 


	       /* added by chandan */
	       k = 0;
	       for(k;k<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a").length;k++)
   	    {

        	//alert("hyperlink");
        	
            var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
            //alert(values);
            get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a");
              name=get_tags[k].name;
           //   alert(name);
              id=get_tags[k].id;
           // alert(id);
           if(id.includes("view@@")==false) // not to add file upload view hyperlink option by chandan 
                 {
             if(id.indexOf("template")!=-1)
                 {
            	 
            //     alert("insie");
              splitTemplateValue=id.split("#");
              reqNO.push(splitTemplateValue[0])
              parameterValue=splitTemplateValue[3];
         
               var orderingValue= document.getElementsByName(id+"#order")[0].value;
                 var resultEntryTemplateValue=document.getElementsByName('hyperLinkTableSession')[0].value;
                        // alert("id"+id);
                          //alert("qq"+document.getElementsByName('hyperLinkTableSession')[0].value);
                 if(document.getElementsByName('hyperLinkTableSession')[0].value=="")
               {
         	 // alert("true");
         	  var ide=id;
         	  ide="divId_"+id;
         	  var ider="idd"+id;
//alert("ider"+document.getElementById(ider).innerHTML)
         	  if(document.getElementById(ider)!=null && document.getElementById(ider).innerHTML!='')
         	  {

         	//	alert( document.getElementById(id).onclick);

             	  
         	  var idee1=id.split("#")[0]+"$$";
         	   idee1+=id.split("#")[3]+"$$";
         	//   alert("idee1"+idee1);
     		  resultEntryTemplateValue="hyperchanks" ;
     		  
              // alert("true1"+resultEntryTemplateValue);
         	  }
     	    
         	  else if(document.getElementById(ide)!=null && document.getElementById(ide).innerHTML!='')
             	  {
           	//	alert( document.getElementById(id).onclick);
           	  
             	  var idee=id.split("#")[0]+"$$";
             	   idee+=id.split("#")[3]+"$$";
         		  resultEntryTemplateValue="hyperchanks" ;
         		  
                  // alert("true1"+resultEntryTemplateValue);
             	  }
         	    
               }
            		var tocheckfnctn=document.getElementById(id).onclick;
            		var valuoffuncntn=tocheckfnctn;
            		valuoffuncntn=valuoffuncntn.toString();
            		//valuoffuncntn=valuoffuncntn.split("hyper");
                      //    alert(""+valuoffuncntn);
                     if(valuoffuncntn.includes("echo"))
                      {
                          // alert("match found");

                           name+=id+"#"+resultEntryTemplateValue+"#"+values+"#-"+"#"+orderingValue;
                           


                           
                        }
                    else
                        {
                        name+=id+"#"+resultEntryTemplateValue+"#"+values+"#hyperlink"+"#"+orderingValue;

                        }
                    
               //     return null;
              // 	divId_961011000118122710002001#null#template#100171002
                //alert(name);
               // return null;
               // alert(name);    
                  concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                  concatenateChkBoxVal+='@';
               //alert(concatenateChkBoxVal);
                /* return false; */
               /*     }
              }
                */      
                 }
                 }
           
	          
            }
        
       
         
         }
   
    
     }
	

	//alert("Finally concatenateChkBoxValues For Save "+concatenateChkBoxVal);
	//return false;
	
	document.getElementsByName('resultEntryTemplateValueWithHash')[0].value=concatenateChkBoxVal;
	//alert(concatenateChkBoxVal);
	
		if(document.getElementsByName('resultEntryTemplateValueWithHash')[0].value=="")
			{
			alert("Invalid form. Modify form from Test Parameter Master.");
			return false;
			
			}
	if(count==0)
   	{
   	  alert("please select a Atleast One record");
   	  return false;
   	}
	 
	
	document.getElementsByName('showStatus')[0].value='0';


	document.getElementsByName('hmode')[0].value="SAVE";
	
	document.forms[0].submit();
	 
   return true;
	 
  }


//added by krishnan nema on 28/01/2018 for save To Draft changes

  
function modifyAll()
{	var cbs=document.getElementsByName('chkResultEntryPatient');

	
for(var i=0; i < cbs.length; i++)
cbs[i].checked=true;
	
	onModify();
	}
function onModify()
{
	
	var count=0;
	var concatenateChkBoxVal="";
	var cbs =document.getElementsByName('chkResultEntryPatient');
	
	 var name;
	  var splitTemplateValue;
	  var reqNO=[];
	  var parameterValue=[];
	  var parameterCode=[];
	  var parantParameter=[];
	  var resultEntryTemplateValue=[];
	//alert(cbs.length);
	for(var i=0; i < cbs.length; i++)
	{
	if(cbs[i].checked)
    	{


	//	alert("dataaaa"+cbs[i].value);

        var commentsboxdata=cbs[i].value;

        if(commentsboxdata.split("#")[19]!="NA")
            {
        	var sampleid=commentsboxdata.split("#")[20];
    		sampleid=sampleid.split("/")[0];
        commentsboxdata=commentsboxdata.split("#")[0]+"#"+commentsboxdata.split("#")[1]+"#"+sampleid;
            }else
        	commentsboxdata=commentsboxdata.split("#")[0]+"#"+commentsboxdata.split("#")[1]+"#";    

        if(document.getElementById(commentsboxdata)!=null )
        {
       // alert("dd");
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
        
        /* Added By prashant For Indication */
        var indicationInputId="indicationInput#"+document.getElementsByName('chkResultEntryPatient')[i].value.split("#")[1];
      // alert(indicationInputId); 
       var indicationInputElement = document.getElementById(indicationInputId);
      // alert(indicationInputElement.value);
      // alert(document.getElementsByName('chkResultEntryPatient')[i].value);
       
        if(indicationInputElement  && indicationInputElement.value!=null && indicationInputElement.value!=""){
        	var checkspes = checkReservedCahracters(indicationInputElement.value);
      	  if(checkspes==true){
  			alert("Indication contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
  			return;
          	}else{} 
      	  indicationInputElement.value = removeSpecialCharacter(indicationInputElement.value);
        	document.getElementsByName('chkResultEntryPatient')[i].value=document.getElementsByName('chkResultEntryPatient')[i].value+"#"+indicationInputElement.value;
        }else{
        	
        	document.getElementsByName('chkResultEntryPatient')[i].value=document.getElementsByName('chkResultEntryPatient')[i].value+"#"+" "
        }
        //alert(document.getElementsByName('chkResultEntryPatient')[i].value);
        
        
        
        // alert("id"+commentsboxdata);
       // alert("commentbox data"+document.getElementById(commentsboxdata).value);
      //  cbs[i].value = cbs[i].value +"#"+document.getElementById(commentsboxdata).value; 


		
		//var values=document.getElementById(i+"chkBOx").value;
		//alert("The value is"+values);
		count++;	
	        var k=0;
           //alert(document.getElementById(i+'templateValue').getElementsByTagName("input"));
             
//          
//         alert("get_tags length"+get_tags.length);
//            for (var i=0; i<get_tags.length; i++) {
//         	    // assigns style properties
//         	     var name=get_tags[i].name;
//         	     alert("i"+name);
//         	  }
        	 var checkBoxId=cbs[i].id;
        	//alert(checkBoxId);
        	// var splitTheCheckBoxId=checkBoxId.substr(0,2);
        	 
        	 var splitTheCheckBoxId=checkBoxId.replace('chkBOx', '');

         	
        	// alert(splitTheCheckBoxId);
        	 
         //   alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a").length);
       // return false;
             for(k;k<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input").length;k++)
        	    {
                    // alert("input");
             	
                 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
              //   alert(values);
                 get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input");
                   name=get_tags[k].name;
                  // alert(name);
                   id=get_tags[k].id;
                 //  alert(id);
                   //alert("id for "+k+"    "+id);
                   hiddenid="hiddenid"+id;
                   defaultid="default"+hiddenid;
              	  typ=get_tags[k].type;
              	 // alert(typ);
              	//  alert(get_tags[k].onclick=='callOnClick(this)');
              	 // return false;
              	  hidddentext="hidden";
              	  checkboxcheck="checkbox";              		
              		
              		
                // alert("type is " + typ);
                   if(typ!=hidddentext)
                   {
                  	 
                 // 	 alert("not hidden");
                   splitTemplateValue=name.split("#");
                   
                   reqNO.push(splitTemplateValue[0]);
                    parameterValue=splitTemplateValue[3];
                  //  alert("parameter value    "+parameterValue);
              //    alert(parameterValue);
                    parameterCode.push(parameterValue.substring(0, 5));
                //    alert( parameterCode.push(parameterValue.substring(0, 5)));
                    parantParameter.push(parameterValue.substring(9,18));
                  
                      
               var resultValidationTemplateValue="";

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
                      resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value;
                    //  alert(resultValidationTemplateValue);
                      
                         /*   var res  = resultValidationTemplateValue.replace("$", " ", true);
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
                    // alert(name);    
                       concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                       concatenateChkBoxVal+='@';
                     //  alert(concatenateChkBoxVal);
                        }
                   }
                          
                          
		          
		          
	             }
        // alert(document.getElementById(i+'templateValue').getElementsByTagName("select").length);
       // alert("select value"+document.getElementById(i+'templateValue').getElementsByTagName("select")[0].value);
        var j=0;
         for(j;j<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select").length;j++)
    	       {
        	 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
     		//alert("The value is"+values);
        	  get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select");
       	   name=get_tags[j].name;
        	 
        	// alert("inside here");
        	//alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length);
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
	        	  {  //alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value)
	        	   	  multiValue+=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value;
	        	      multiValue+="$";   	  
	        	  
	        	  }
	        	 resultEntryTemplateValue=multiValue
	        	  }
	          
	        else
	       	resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value;
		   
	        
	    //    alert(resultEntryTemplateValue);
	        name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-";
	          
     		  	concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
    			concatenateChkBoxVal+='@';

    	       
    	       } 
         
         
        
         
    
        // alert("text Area value"+document.getElementById(i+'templateValue').getElementsByTagName("textarea")[0].value);
         var n=0;
          for(n;n<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea").length;n++)
     	       {
 	             
        	  var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
        	  
      	//	alert("The value is"+values);
        	  //alert("inside here");
 	             get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea");
 	        	   name=get_tags[n].name;
 	         	// alert("name1"+name);
 	              
 	             /*  if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value=="")
 	         	   {
 				      alert("Enter the field Focussed");
 				      document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].focus();
 			          return false;
 		           } */
 	           //  document.getElementsByName('resultEntryTemplateValue')[0].value=document.getElementById(i+'templateValue').getElementsByTagName("input")[j].value;	 
                
 	         //  alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].id);
 	         var id1 = document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].id;
 	           var editor = CKEDITOR.instances[id1];
 	       
 	         
 	           if(editor!=null){
 	        	 
 	        	  
            //  alert( editor.getData() );
              
              var resultEntryTemplateValue=editor.getData();
				//alert(resultEntryTemplateValue);
  	       /*   var res  = resultEntryTemplateValue.replace("$", " ", true);
  	         var res2  = res.replace("#", " ", true);
  	         var res3  = res2.replace("~", " ", true);
  	       var res4  = res3.replace("`", " ", true);
  	       resultEntryTemplateValue = res4.replace("@", " ", true);  */
  	     resultEntryTemplateValue = resultEntryTemplateValue.replace("&#39;",
   	    	     "<img id='base64image'src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAYAAAAMCAIAAADONVt5AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAlSURBVBhXY/iPAUgSur8gKmrBfQgbr1DdQQiTNOORAZlC//8DADcC0LbmFlSzAAAAAElFTkSuQmCC' />",true);
              var check23 = checkReservedCahracters(resultEntryTemplateValue);
        	  if(check23==true){
    			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
    			return;
            	}else{
    			
                } 
  	     resultEntryTemplateValue = removeSpecialCharacterEditor(resultEntryTemplateValue);
  	                 
    	     
              //alert(resultEntryTemplateValue);
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

     	        /* var res  = resultEntryTemplateValue.replace("$", " ", true);
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
     		  	concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
    			concatenateChkBoxVal+='@';
            //  alert(concatenateChkBoxVal);
     	       
     	       } 


	       /* added by chandan */
	       k = 0;
	       for(k;k<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a").length;k++)
   	    {

        	//alert("hyperlink");
        	
            var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
            //alert(values);
            get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a");
              name=get_tags[k].name;
              id=get_tags[k].id;
          //  alert(id);
          if(id.includes("view@@")==false) // not to add file upload view hyperlink option by chandan 
                 {
             if(id.indexOf("template")!=-1)
                 {
            //     alert("insie");
              splitTemplateValue=id.split("#");
              reqNO.push(splitTemplateValue[0])
              parameterValue=splitTemplateValue[3];
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

         	//	alert( document.getElementById(id).onclick);

             	  
         	  var idee1=id.split("#")[0]+"$$";
         	   idee1+=id.split("#")[3]+"$$";
         	//   alert("idee1"+idee1);
     		  resultEntryTemplateValue=document.getElementById(ider).innerHTML ;
     		  
              // alert("true1"+resultEntryTemplateValue);
         	  }
     	    
         	  else if(document.getElementById(ide)!=null && document.getElementById(ide).innerHTML!='')
             	  {
             	  var idee=id.split("#")[0]+"$$";
             	   idee+=id.split("#")[3]+"$$";
         		  resultEntryTemplateValue=document.getElementById(ide).innerHTML ;
         		  
                  // alert("true1"+resultEntryTemplateValue);
             	  }
         	 else if(document.getElementById(ide)!=null && document.getElementById(ide).innerHTML!='')
        	  {
      		//alert( document.getElementById(id).onclick);
      	  
        	  var idee=id.split("#")[0]+"$$";
        	   idee+=id.split("#")[3]+"$$";
    		  resultEntryTemplateValue="hyperchanks" ;
    		  
             // alert("true1"+resultEntryTemplateValue);
        	  }
         	    
               }


                
 			  if(id.includes("view@@")==false) // not to add file upload view hyperlink option by chandan 
              {

 					var tocheckfnctn=document.getElementById(id).onclick;
            		var valuoffuncntn=tocheckfnctn;
            		valuoffuncntn=valuoffuncntn.toString();
            		//valuoffuncntn=valuoffuncntn.split("hyper");
                    //      alert(""+valuoffuncntn);
                     if(valuoffuncntn.includes("echo"))
                      {
                      //     alert("match found");

                           name+=id+"#"+resultEntryTemplateValue+"#"+values+"#-";
                           


                           
                        }
                    else
                        {
                        name+=id+"#"+resultEntryTemplateValue+"#"+values+"#hyperlink";

                        }
                   
               // alert(name);    
                  concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                  concatenateChkBoxVal+='@';
              }
              
           //    alert(concatenateChkBoxVal);
                /* return false; */
               /*     }
              }
                */      
                 }
                 }
           
	          
            }
        
       
         
         }
   
    
     }
	

	//alert("Finally concatenateChkBoxValues For Save "+concatenateChkBoxVal);
	
	
	document.getElementsByName('resultEntryTemplateValueWithHash')[0].value=concatenateChkBoxVal;
	//alert(concatenateChkBoxVal);
	
	
	if(count==0)
   	{
   	  alert("please select a Atleast One record");
   	  return false;
   	}
	 
	
	document.getElementsByName('showStatus')[0].value='0';


	document.getElementsByName('hmode')[0].value="MODIFY";
	
	document.forms[0].submit();
	 
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

  
  
  
  
function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
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
 document.getElementsByName('hmode')[0].value='GETTYPEWISEDETAIL';
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

function checkEntryType()
{
	//changed by ashu
	
	 
	 /* if(document.getElementsByName("chkResultEntryPatient")!='undefined')
	  	{

		  	var val=document.getElementsByName("chkResultEntryPatient");
		  	for(var i=0;i<val.lenght;i++)
			  	{
		  		document.getElementsByName("chkResultEntryPatient")[i].checked=true;			  	

			  	}
		  	
		 
	  	} */
	
	var sbelement =  document.getElementsByName("searchBy")[0];
	if (typeof(sbelement) != 'undefined' && sbelement != null) {

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
	  }
	
	var genTypeValue=document.getElementsByName('generationType')[0].value;
	//alert("onload"+genTypeValue); showOnLoad
	 if(genTypeValue=='')
		 {
		 
		 if ($('#showOnLoad').length > 0)
		 document.getElementById("showOnLoad").style.display="";	
		 
		 if ($('#patient').length > 0)
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

	}  //allPatient
	
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

function onloadHideShow(){
	var patType=document.getElementsByName('patientType')[0].value;
	if(patType == 2){
				
	}else{
		
	}
}


 function popupCallCanned()
 {
	 
	 
	 openAutocompletePopup('CANNED');
		var data=getTheLabCannedList('CANNED');
	//	alert("ajax data     "+ data);
		callCannedList(data);
		document.getElementById('automplete-4').value="";
		  document.getElementById("automplete-4").focus();
 }

function popupCallMacro()
{
	
	
	openAutocompletePopup('MACRO');
	var data=getTheLabCannedList('MACRO');
	callMacroList(data);
	 document.getElementById("automplete-m").value="";
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
        {//alert("s");
        	//alert(document.getElementById('selectAllCheckbox').checked);
        	
        	var cbs =document.getElementsByName('chkSamplePatient');
        //    alert(cbs);
                if(document.getElementById('selectAllCheckbox').checked==true)
                    {
        	for(var i1=0; i1 < cbs.length; i1++)
        		{
           //       alert("s");
        		document.getElementsByName('chkSamplePatient')[i1].checked=true;
        		var value=document.getElementsByName('chkSamplePatient')[i1].value;
        	//	alert(value);
        		checkbilling(i1,'chkSamplePatient',value);        

        		}
                    }
                else{
                	for(var i1=0; i1 < cbs.length; i1++)
            		 {
                		document.getElementsByName('chkSamplePatient')[i1].checked=false;

                     }
                    }
                    

        }


        var count=0;
        var testname="";
        function checkbilling(i,namee,valuee)
        {

        var isbilled="";
            var value=valuee.split("#");
             var name=namee;
//        	alert(value[4]);
        	
        		
             if(value[4]!=null)   //to check test addendum or not
                 {
                      if(value[4]=="1")
                    isbilled= checkBillingAddendumTest1(valuee);     
                 }

                   if(isbilled=="2")     //status 2 for unbilled and 1 for billed

                       {

                       alert("Required Billing ");
                       document.getElementsByName('chkSamplePatient')[i].checked=false;
                       count++;
                       var testName=valuee.split("#")[7];
                       testname+=testName+",";
                      // testname=testname.split(",").length-1;
                  //     alert(count+testname);
                    //document.getElementById('nextDiv').style.display="none";
                            return false;                 
                       }
                   else
                       {
                	   document.getElementById('nextDiv').style.display="";
                       }
        	

        }


        /*  function allSelected() 
        {
        	document.getElementById("nextDiv").style.display=""; 
        	// this line is for toggle the check
            isChecked = !isChecked;
            //below line refers to 'jpCheckbox' class
            $('input:checkbox.jpCheckbox').attr('checked',isChecked);
            //OR,
            //$('input:checkbox.jpCheckbox').attr('checked','checked');
        } */
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
            //	alert(cannedDetails.length);
            	
            	cannedDetails="["+cannedDetails.toString()+"]";
            	//alert(cannedDetails.length);
            	cannedDetails=cannedDetails.toString();
          }
   	 else
   		 {
   		
   			 alert("No Canned Data Is Mapped For  "+document.getElementsByName("chkLabName")[0].value+"   Lab");
   		
   		 
   		 }
            
            
            
            
            
            
            
            
          
            
            
            
            
           
  //       alert("inside jquery canned details label valiu      "+cannedDetails);
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


function call()
{
        //   alert("call");
	var cbs =document.getElementsByName('chkResultEntryPatient');

    for(var i=0; i < cbs.length; i++)
   {
           
              //  alert("ins");
                var dd=document.getElementsByTagName("select");
                for(var j=0;j<dd.length;j++)
                    {
              	  var name=dd[j].name;
                //    alert("name"+dd[j].onchange);
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
   
 function autovalue()
 {
      call();
	// alert("hello");
	 if(document.getElementsByName("chkResultEntryPatient")!='undefined')
	  	{
		 //alert("hello1");
		  	var val=document.getElementsByName("chkResultEntryPatient");
		  	for(var i=0;i<val.length;i++)
			  	{

		  		//alert(i);
			  	
		  		document.getElementsByName("chkResultEntryPatient")[i].checked=true;			  	

			  	}
		  	
		 
	  	}

	  	
var aa1=0;
    
<%-- 	 Map<String,List<String>> mpAutoList = <%=(Map<String,List<String>>)session.getAttribute(InvestigationConfig.AUTOCOMPLETE_LIST_VALUES)%>; --%>
	
<%-- 	Map<String,List<String>> mpAutoList = <%=(Map<String,List<String>>)session.getAttribute(InvestigationConfig.AUTOCOMPLETE_LIST_VALUES)%>;
	
	
	  if(mpAutoList!=null)
	  {
		  Iterator itrAuto=mpAutoList.keySet().iterator();
	   while(itrAuto.hasNext())
	  { String autoCode=(String)itrAuto.next();
	  String autoList=mpAutoList.get(autoCode).get(0);
	  
	  var objList = JSON.parse(autoList);
	  

	setInterval(function() {
    $( autoCode).autocomplete({
       
        select: function(event, ui) {
        	source:objList,
             $('#hiddenid'+autoCode).val(ui.item.value); 
            event.preventDefault();  
            $(autoCode).val(ui.item.value); 
        },

    focus: function(event, ui) { 
           event.preventDefault(); 
           $(autoCode).val(ui.item.value);}
     });
    }, 1000);
    
	
}} --%>
 }  
//macro
function callMacroList(data){
 $(function() {
 	      
 	           var macroCodes=data;
 	           //alert(macroCodes);
 	           
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
        		 deleteTableMacro();
        		 document.getElementsByName("macroDataCount")[0].value=splitCannedDetails.length-1;
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
        	 
        	 
        	//alert(cannedDetails);
        	 
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
/* 
var parsethis="[{\"label\": \"adfwefwe\",\"value\": \"1\"  }, {\"label\": \"bwefwefwe\",\"value\": \"2\" }, {\"label\": \"cfwefweff\",\"value\": \"3\" },  {\"label\": \"dfwefwfwef\",\"value\": \"4\" } ]";
parsethis=parsethis.toString();
var objlisting=JSON.parse(parsethis);
//alert(parsethis);alert(objlisting);


 setInterval(function() {
$( "#auto100221028" ).autocomplete({
  source: objlisting,
  select: function(event, ui) { 
      $('#hiddenidauto100221028').val(ui.item.value); 
      event.preventDefault(); 
      $("#auto100221028").val(ui.item.label); 
  },

focus: function(event, ui) { 
     event.preventDefault(); 
     $("#auto100221028").val(ui.item.label);}
});
}, 1000); */






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
/* 
CKEDITOR.instances[inst].setKeystroke([
                                       [ CKEDITOR.CTRL + 120,  false],
                                       [ CKEDITOR.SHIFT + 120, false]
                                       
                                       ]);

 */
// CKEDITOR.on('currentInstance', function(){alert(inst)});
 

CKEDITOR.instances[inst].on("focus",function()
	{
	//alert(this.name); always use this name and not inst name as it keeps getting incremented and thus takes up the value of the last editor
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
	
	//document.getElementById("records").style.display="none";
	//document.getElementById("mapUnmap").style.display="none";
	//document.getElementById("dateDisplay").style.display="none";
	//document.getElementById("fromToDate").style.display="";
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
	//$("tr#trReqDate").parent().replaceWith("tr#trCollDate");
	
	document.getElementById("divfromDateControl").style.display="none";
	document.getElementById("divfromDate").style.display="none";
	document.getElementById("divToDate").style.display="none";
	document.getElementById("divToDateControl").style.display="none";
	
	document.getElementById("divfromCollDateControl").style.display="";
	document.getElementById("divfromCollDate").style.display="";
	document.getElementById("divToCollDateControl").style.display="";
	document.getElementById("divToCollDate").style.display=""; 

	//alert("showCollDate called");
	//document.getElementsByName('hmode')[0].value='GETDETAILS';
	//document.forms[0].submit();
}

 function callGetDetails()
 {
	 document.getElementsByName('hmode')[0].value='GETDETAILS';
	 document.forms[0].submit();
 }

 
</script>

 <script>
 /* added by prashant */
function setIndication(){
	 return;
/* 	 var indication = document.getElementById("indicationInput").value;
	 document.getElementsByName("visitReason")[0].value=indication;
	 //alert(document.getElementsByName("visitReason")[0].value); */
		 }

/* added by prashant */
 function indicationInputChange(obj){
// return;
 	var inputval=obj.value;
 	//alert(inputval);
	var indicationClassName = obj.className;
	//alert(indicationClassName);
		var indicationInputC = document.getElementsByClassName(indicationClassName);
		for (var i=0; i<indicationInputC.length; i++)
		{	//alert("found"+i);
			indicationInputC[i].value=inputval;
		} 
}
		 </script>

<style>
.NewTextBox {
	border: 2px solid #456879;
	border-radius: 10px;
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




<!-- <body onload="autovalue();checkEntryType();onloadHideShow();openLastPage();"> --> 
 <body onload="autovalue();checkEntryType();onloadHideShow();"> 

	<html:form action="/invResultEntryTemplateTile"
		enctype="multipart/form-data">
		<html:hidden name="InvResultEntryFB" property="hmode" />
				<html:hidden name="InvResultEntryFB" property="echovar" />
		
				<html:hidden name="InvResultEntryFB" property="fileuploaddata" />
						<html:hidden name="InvResultEntryFB" property="fileuploaddatabase64" />
		
		<html:hidden name="InvResultEntryFB" property="isPatDetailPage" />
		<html:hidden name="InvResultEntryFB" property="selectedCheckbox" />
		<html:hidden name="InvResultEntryFB" property="showStatus" />
		<html:hidden name="InvResultEntryFB" property="currentPage" />
		<html:hidden name="InvResultEntryFB" property="patCRNo" />
		<html:hidden name="InvResultEntryFB" property="sysDate" />
		<html:hidden name="InvResultEntryFB" property="getSearchType" />
		<html:hidden name="InvResultEntryFB" property="generationType" />
		<html:hidden name="InvResultEntryFB" property="onLoadValue" />
		<html:hidden name="InvResultEntryFB" property="selectValuemapping"
			value="" />
		<html:hidden name="InvResultEntryFB" property="Counter" value="0" />
		<!--  <input type="hidden" name="selectValuemapping" value="null"/> -->
		<input type="hidden" name="hyperLinkTableSession" value="" />
		<html:hidden name="InvResultEntryFB" property="sampleAreaName" />
		<html:hidden name="InvResultEntryFB" property="isSampleAreaSelected" />
		<html:hidden name="InvResultEntryFB" property="sizeOfFile" value="0" />
		<html:hidden name="InvResultEntryFB" property="fileName" />

		<!-- canned popup  -->

		<div id="blanketcanned" style="display: none;"></div>
		<div id="popUpDiv5canned" style="display: none;" align="center">

			<his:TitleTag name="Canned Details">
				<img src='/HISInvestigationG5/hisglobal/css/close.png'
					onClick="closecanned();">
			</his:TitleTag>


			<table width="100%" id="allInstructionscanned">

				<tr>
					<td width="25%" class="tdfont">
						<div align="right">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> Canned Code
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <html:text
									property="cannedCode" name="InvResultEntryFB"></html:text>
							</font>
						</div>
					</td>

				</tr>
				<tr>
					<td width="25%" class="tdfont">
						<div align="right">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> Canned Name
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <html:text
									property="cannedName" name="InvResultEntryFB"></html:text> <input
								type="hidden" name="cannedvall">
							</font>
						</div>
					</td>

				</tr>

			</table>

			<img src='/HISInvestigationG5/hisglobal/images/btn-sv.png'
				onClick="closeInstructionscanned();">


		</div>


		<!--  //end -->


		<%!boolean readOnly;%>
		<%
			this.readOnly=false;
		%>
		<logic:equal name="InvResultEntryFB" property="hmode" value="VIEW">
			<%
				this.readOnly=true;
			%>
		</logic:equal>
		<his:TitleTag name="Result Entry Process">
			<%-- <his:insertDateTag/> --%>
		</his:TitleTag>



		<his:ContentTag>
			<%
				String fromDateLabel="" ;
			              String toDateLabel="" ;
			              String fromDateControl="" ;
			              String toDateControl="" ;
			%>
			<bean:define name="InvResultEntryFB" property="fromDate" id="frDate"
				type="java.lang.String" />
			<bean:define name="InvResultEntryFB" property="toDate" id="tDate"
				type="java.lang.String" />
			<bean:define name="InvResultEntryFB" property="fromCollDate"
				id="frCollDate" type="java.lang.String" />
			<bean:define name="InvResultEntryFB" property="toCollDate"
				id="tCollDate" type="java.lang.String" />

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
			<logic:equal name="InvResultEntryFB" property="showStatus" value="0">
				<his:SubTitleTag name="Acceptance Details"></
  			</his:SubTitleTag>
				<!-- Added by krishnan -->


				<!-- 	added by chandan -->


				<!-- added by chandan -->
				<logic:present
					name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>">
					<table width="100%">

						<tr>
							<td class="tdfont" width="25%">
								<div align="right">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="sampleColl" />&nbsp;
									</font>

								</div>
							</td>
							<td class="tdfont" width="25%"><logic:notEqual
									name="InvResultEntryFB" property="isSampleAreaSelected"
									value="1">
									<span class="custom-dropdown small"> <html:select
											name="InvResultEntryFB" property="sampleAreaCode"
											tabindex="1" onchange="showSearchDiv(this)">
											<bean:define id="patSampleCollection" type="java.util.List"
												name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>"></bean:define>
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
											
											<%-- <html:option value="-2">All</html:option> --%>
											<html:options
												collection="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>"
												property="value" labelProperty="label" />
										</html:select>
									</span>
								</logic:notEqual> <logic:equal name="InvResultEntryFB"
									property="isSampleAreaSelected" value="1">
									<div align="left">
										<bean:write name="InvResultEntryFB" property="sampleAreaName" />
										<html:hidden name="InvResultEntryFB" property="sampleAreaCode" />
									</div>
								</logic:equal></td>
							<td class="tdfont" width="25%"></td>
							<td class="tdfont" width="25%"></td>
						</tr>
					</table>
					<!-- end chandan -->
					<logic:equal name="InvResultEntryFB"
						property="isSampleAreaSelected" value="1">

						<table width="100%" border="0" cellspacing="1" cellpadding="0">



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
								<td width="25%" class="tdfont"><logic:present
										name="<%=InvestigationConfig.LAB_COMBO_FOR_RESULT_ENTRY%>">
										<div align="left">
											&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
													name="InvResultEntryFB" property="labCode" tabindex="1"
													onchange="labBased()">

													<html:option value="%">All</html:option>
													<html:options
														collection="<%=InvestigationConfig.LAB_COMBO_FOR_RESULT_ENTRY%>"
														property="value" labelProperty="label" />
												</html:select>
											</span>
										</div>
									</logic:present></td>
								<td width="25%" class="tdfont">

									<div align="right">
										<font color="RED" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
											color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
												key="ResultEntry" />&nbsp;
										</font>
									</div>


								</td>
								<td width="25%" class="tdfont"><html:radio
										name="InvResultEntryFB" tabindex="1" property="newEntry"
										value="1"></html:radio> <bean:message key="newEntry" />&nbsp;

									<html:radio name="InvResultEntryFB" tabindex="1"
										property="newEntry" value="2"></html:radio> <bean:message
										key="entered" />&nbsp;
										
									<html:radio name="InvResultEntryFB" tabindex="1" property="newEntry"
										value="3"></html:radio> Drafts &nbsp;
								</td>
							</tr>

							<!-- changed by ashu -->
							<tr>

								<td width="25%" class="tdfont">

									<div align="right">
										<font color="RED" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
											color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
												key="searchBy" />&nbsp;&nbsp;&nbsp;
										</font>
									</div>


								</td>
								<td width="25%" class="tdfont"><bean:message
										key="reqNoDate" />&nbsp; <!-- <input type="radio" name="resetData" id="resetYes"  onclick="hideRecords()" value="1" /> -->
									<html:radio name="InvResultEntryFB" property="searchBy"
										onclick="showReqDate();callGetDetails();" value="1"></html:radio>
									<%-- <html:radio name="InvResultValidationFB" property="searchBy" onclick="hideRecords() value="1"></html:radio> --%>

									<%-- <bean:message key="sampleCollDate"/>&nbsp; --%> <!-- updated by krishnan nema -->
									<bean:message key="sampleCollDateTest" />&nbsp; <!-- <input type="radio" name="resetData" id="resetNo"  onclick="showRecords()" checked="checked" value="0" /> -->
									<html:radio name="InvResultEntryFB" property="searchBy"
										onclick="showCollDate();callGetDetails();" value="0"></html:radio>
									<%-- <html:radio name="InvResultValidationFB" property="searchBy" onclick="showCollDate() value="0"></html:radio> --%>


								</td>

							</tr>
							<!-- changed by ashu -->


							<tr id="trReqDate">
								<td class="tdfont" width="25%">
									<div id='divfromDate' style='<%=fromDateLabel%>' align="right">
										<font color="#FF0000" size="1"
											face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font
											color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
												key="fromDate" />&nbsp;
										</font>
									</div>
								</td>
								<td class="tdfont" width="25%">
									<div id='divfromDateControl' style='<%=fromDateControl%>'
										align="left">
										&nbsp;&nbsp;&nbsp;
										<his:date name='fromDate' dateFormate="%d-%b-%Y"
											value="<%=frDate%>" />
									</div> <input type="hidden" value='<%=frDate%>'
									name="fromDateHidden" />
								</td>
								<td class="tdfont" width="25%">
									<div id='divToDate' style='<%=toDateLabel%>' align="right">
										<font color="#FF0000" size="1"
											face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font
											color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
												key="toDate" />&nbsp;
										</font>
									</div>
								</td>
								<td class="tdfont" width="25%">
									<div id='divToDateControl' style='<%=toDateControl%>'
										align="left">
										&nbsp;&nbsp;&nbsp;
										<his:date name='toDate' dateFormate="%d-%b-%Y"
											value="<%=tDate%>" />
									</div> <input type="hidden" value='<%=tDate%>' name="toDateHidden" />
								</td>
							</tr>

							<!-- changed by ashu -->

							<tr id="trCollDate">
								<td class="tdfont" width="25%">
									<div id='divfromCollDate' style='<%=fromDateLabel%>'
										align="right">
										<font color="#FF0000" size="1"
											face="Verdana, Arial, Helvetica, sans-serif\"> </font><font
											color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> 
											<!-- updated by krishnan nema -->
											<%-- <bean:message
												key="fromSampleCollDate" />&nbsp; --%>
												<bean:message
												key="fromSampleCollDateAcceptance" />&nbsp;
												
										</font>
									</div>
								</td>
								<td class="tdfont" width="25%">
									<div id='divfromCollDateControl' style='<%=fromDateControl%>'
										align="left">
										&nbsp;&nbsp;&nbsp;
										<his:date name='fromCollDate' dateFormate="%d-%b-%Y"
											value="<%=frCollDate%>" />
									</div>
								</td>
								<td class="tdfont" width="25%">
									<div id='divToCollDate' style='<%=toDateLabel%>' align="right">
										<font color="#FF0000" size="1"
											face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font
											color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif">
											<!-- updated by krishnan nema on 01/10/2018 -->
											<%--  <bean:message
												key="toSampleCollDate" />&nbsp; --%>
												<bean:message
												key="toSampleCollDateAcceptance" />&nbsp;
										</font>
									</div>
								</td>
								<td class="tdfont" width="25%">
									<div id='divToCollDateControl' style='<%=toDateControl%>'
										align="left">
										&nbsp;&nbsp;&nbsp;
										<his:date name='toCollDate' dateFormate="%d-%b-%Y"
											value="<%=tCollDate%>" />
									</div>
								</td>
							</tr>

							<!-- changed by ashu -->

							<tr>
								<td width="25%" class="tdfont">
									<div align="right">
										<font color="RED" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
											color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
												key="ResultEntryType" />&nbsp;
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" colspan="3">
									<div align="left">
										&nbsp;&nbsp;&nbsp;
										<logic:equal name="InvResultEntryFB" property="generationType"
											value="P">

											<input type="radio" name="patientWise"
												onclick="getDetails(this)" checked="checked" value="P" />
										</logic:equal>
										<logic:notEqual name="InvResultEntryFB"
											property="generationType" value="P">
											<input type="radio" name="patientWise" id="patient"
												onclick="getDetails(this)" value="P" />
										</logic:notEqual>
										<bean:message key="PatientWise" />

										&nbsp;&nbsp;&nbsp;
										<logic:equal name="InvResultEntryFB" property="generationType"
											value="T">
											<input type="radio" name="testWise"
												onclick="getDetails(this)" checked="checked" value="T" />
										</logic:equal>
										<logic:notEqual name="InvResultEntryFB"
											property="generationType" value="T">
											<input type="radio" name="testWise"
												onclick="getDetails(this)" value="T" />
										</logic:notEqual>

										<bean:message key="testWise" />

										<!-- updated by krishnan nema on 28/09/2018 -->
										<logic:notEqual name="InvResultEntryFB" property="patientType"
											value="2">                                                                        
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvResultEntryFB" property="generationType"
												value="S">
												<input type="radio" name="sampleNoWise"
													onclick="getDetails(this)" checked="checked" value="S" />
											</logic:equal>
											<logic:notEqual name="InvResultEntryFB"
												property="generationType" value="S">
												<input type="radio" name="sampleNoWise"
													onclick="getDetails(this)" value="S" />
											</logic:notEqual>

											<bean:message key="sampleNoWise" />

										</logic:notEqual>



										<logic:notEqual name="InvResultEntryFB" property="patientType"
											value="2">
										&nbsp;&nbsp;&nbsp;
										<logic:equal name="InvResultEntryFB" property="generationType"
											value="L">
											<input type="radio" name="labNoWise"
												onclick="getDetails(this)" checked="checked" value="L" />
										</logic:equal>
										<logic:notEqual name="InvResultEntryFB"
											property="generationType" value="L">
											<input type="radio" name="labNoWise"
												onclick="getDetails(this)" value="L" />
										</logic:notEqual>


										<bean:message key="labNOWise" />
										</logic:notEqual>
										
										<logic:equal name="InvResultEntryFB" property="patientType"
											value="2">
																					&nbsp;&nbsp;&nbsp;
										<logic:equal name="InvResultEntryFB" property="generationType"
											value="L">
											<input type="radio" name="labNoWise"
												onclick="getDetails(this)" checked="checked" value="L" />
										</logic:equal>
										<logic:notEqual name="InvResultEntryFB"
											property="generationType" value="L">
											<input type="radio" name="labNoWise"
												onclick="getDetails(this)" value="L" />
										</logic:notEqual>


										<bean:message key="labNOWiseAcc" />
										</logic:equal>

										&nbsp;&nbsp;&nbsp;
										<logic:equal name="InvResultEntryFB" property="generationType"
											value="TG">
											<input type="radio" name="testGroupWise"
												onclick="getDetails(this)" checked="checked" value="TG" />
										</logic:equal>
										<logic:notEqual name="InvResultEntryFB"
											property="generationType" value="TG">
											<input type="radio" name="testGroupWise"
												onclick="getDetails(this)" value="TG" />
										</logic:notEqual>


										<bean:message key="testGrpWise" />


										&nbsp;&nbsp;&nbsp;
										<logic:equal name="InvResultEntryFB" property="generationType"
											value="AP">
											<input type="radio" name="allPatient"
												onclick="getDetails(this)" checked="checked" value="AP" />
										</logic:equal>
										<logic:notEqual name="InvResultEntryFB"
											property="generationType" value="AP">
											<input type="radio" name="allPatient"
												onclick="getDetails(this)" value="AP" />
										</logic:notEqual>


										<bean:message key="allPatient" />
									</div>

								</td>

							</tr>
							<tr>
								<td width="25%" class="tdfont">
									<div align="right" id="showOnLoad" style="display: none">
										<bean:message key="crNO" />
										&nbsp;
									</div>
									<div align="right">
										<font color="RED" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
											color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <logic:equal
												name="InvResultEntryFB" property="generationType" value="P">
												<bean:message key="crNO" />&nbsp;
								</logic:equal> <logic:equal name="InvResultEntryFB" property="generationType"
												value="T">
												<font color="RED" size="2"
													face="Verdana, Arial, Helvetica, sans-serif"> * </font>
												<bean:message key="testName" />&nbsp;
								</logic:equal> 
								
								<!-- updated by krishnan nema on 01/10/2018 -->
								<logic:notEqual name="InvResultEntryFB" property="patientType"
											value="2">
								
								<logic:equal name="InvResultEntryFB" property="generationType"
												value="L">
												<font color="RED" size="2"
													face="Verdana, Arial, Helvetica, sans-serif"> * </font>
												<bean:message key="fromLabNo" />&nbsp;
								
								
								</logic:equal>
								
								</logic:notEqual>
								
								<logic:equal name="InvResultEntryFB" property="patientType"
											value="2">
								<logic:equal name="InvResultEntryFB" property="generationType"
												value="L">
												<font color="RED" size="2"
													face="Verdana, Arial, Helvetica, sans-serif"> * </font>
												<bean:message key="fromAccessionNo" />&nbsp;
								
								
								</logic:equal>
								</logic:equal>
								
								<%-- <logic:equal name="InvResultEntryFB" property="generationType"
												value="L">
												<font color="RED" size="2"
													face="Verdana, Arial, Helvetica, sans-serif"> * </font>
												<bean:message key="fromLabNo" />&nbsp;
								
								
								</logic:equal> --%>
								
								 <logic:equal name="InvResultEntryFB" property="generationType"
												value="S">
												<font color="RED" size="2"
													face="Verdana, Arial, Helvetica, sans-serif"> * </font>
												<bean:message key="fromSampleNo" />&nbsp;
								</logic:equal> <logic:equal name="InvResultEntryFB" property="generationType"
												value="TG">
												<font color="RED" size="2"
													face="Verdana, Arial, Helvetica, sans-serif"> * </font>
												<bean:message key="tstgrpName" />&nbsp;
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

									<div align="left" id="patientwise">
										&nbsp;&nbsp;&nbsp; <input type="text" id="textBoxCss"
											name="tempPatCRNo" value="<%=val%>" maxlength="15" size="20"
											onkeypress="return validateNumeric(event,this)" tabindex="1">
									</div> <logic:present
										name="<%=InvestigationConfig.TEST_WISE_COMBO_FOR_RESULT_ENTRY%>">
										<div align="left" id="testwise" style="display: none;">
											&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
													name="InvResultEntryFB" property="testWiseCode"
													tabindex="1">
													<html:option value="-1">Select Value</html:option>
													<html:options
														collection="<%=InvestigationConfig.TEST_WISE_COMBO_FOR_RESULT_ENTRY%>"
														property="value" labelProperty="label" />
												</html:select>
											</span>
										</div>
									</logic:present> <logic:present
										name="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>">
										<div align="left" id="samplenowise" style="display: none;">
											&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
													name="InvResultEntryFB" property="fromSampleNo"
													tabindex="1">
													<html:option value="-1">Select Value</html:option>
													<html:options
														collection="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>"
														property="value" labelProperty="label" />
												</html:select>
											</span>
										</div>
									</logic:present> <logic:present
										name="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>">
										<div align="left" id="labnowise" style="display: none;">
											&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
													name="InvResultEntryFB" property="fromLabNo" tabindex="1">
													<html:option value="-1">Select Value</html:option>
													<html:options
														collection="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>"
														property="value" labelProperty="label" />
												</html:select>
											</span>
										</div>
									</logic:present> <logic:present
										name="<%=InvestigationConfig.TEST_GROUP_COMBO_FOR_RESULT_ENTRY%>">
										<div align="left" id="testGrpWise" style="display: none;">
											&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
													name="InvResultEntryFB" property="testGroupCodeWise"
													tabindex="1">
													<html:option value="-1">Select Value</html:option>
													<html:options
														collection="<%=InvestigationConfig.TEST_GROUP_COMBO_FOR_RESULT_ENTRY%>"
														property="value" labelProperty="label" />
												</html:select>
											</span>
										</div>
									</logic:present>


								</td>
								<td width="25%" class="tdfont">

									<div align="left" id="patientwisename">
										Search Name&nbsp;&nbsp;&nbsp;<input type="text"
											id="textBoxCss" name="tempPatName" maxlength="20" size="20"
											onkeypress="return validateAlphabetsOnly(event,this)"
											tabindex="1">
									</div>


									<div align="right" id="toLabSampleNo" style="display: none;">
										<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> 
											
											<!-- updated by krishnan nema on 01/10/2018 -->
											<logic:notEqual name="InvResultEntryFB" property="patientType"
											value="2">
											<logic:equal
												name="InvResultEntryFB" property="generationType" value="L">
												<font color="RED" size="2"
													face="Verdana, Arial, Helvetica, sans-serif"> * </font>
												<bean:message key="toLabNo" />&nbsp;
											</logic:equal>
											
											</logic:notEqual>
											
											<logic:equal name="InvResultEntryFB" property="patientType"
											value="2">
											<logic:equal
												name="InvResultEntryFB" property="generationType" value="L">
												<font color="RED" size="2"
													face="Verdana, Arial, Helvetica, sans-serif"> * </font>
												<bean:message key="toAccessionNo" />&nbsp;
											</logic:equal>
											
											</logic:equal>
											
											<%-- <logic:equal
												name="InvResultEntryFB" property="generationType" value="L">
												<font color="RED" size="2"
													face="Verdana, Arial, Helvetica, sans-serif"> * </font>
												<bean:message key="toLabNo" />&nbsp;
											</logic:equal>  --%>
								
								<logic:equal name="InvResultEntryFB" property="generationType"
												value="S">
												<font color="RED" size="2"
													face="Verdana, Arial, Helvetica, sans-serif"> * </font>
												<bean:message key="toSampleNo" />&nbsp;
								</logic:equal>
										</font>
									</div>
								</td>

								<td width="25%" class="tdfont"><logic:present
										name="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>">
										<div align="left" id="labnowise2" style="display: none;">
											&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
													name="InvResultEntryFB" property="toLabNo" tabindex="1">
													<html:option value="-1">Select Value</html:option>
													<html:options
														collection="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>"
														property="value" labelProperty="label" />
												</html:select>
											</span>
										</div>
									</logic:present> <logic:present
										name="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>">
										<div align="left" id="samplenowise2" style="display: none;">
											&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
													name="InvResultEntryFB" property="toSampleNo" tabindex="1">
													<html:option value="-1">Select Value</html:option>
													<html:options
														collection="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>"
														property="value" labelProperty="label" />
												</html:select>
											</span>
										</div>
									</logic:present></td>
							</tr>
							<tr>
								<td class="tdfont" align="left" colspan="4" width="25%">
									<div align="center">
										<img class="button"
											src='<his:path src="/hisglobal/images/GO.png"/>' id="gob"
											style="cursor: pointer"
											onkeypress="if(event.keyCode==13) onClickGO('<%=hospitalCode%>')"
											onclick="onClickGO('<%=hospitalCode%>')" tabindex="1">
										<img class="button"
											src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
											id="gob" style="cursor: pointer"
											onkeypress="if(event.keyCode==13) cancelFunc() "
											onclick="cancelFunc()" tabindex="1">

									</div>
								</td>
							</tr>
						</table>
					</logic:equal>
				</logic:present>
			</logic:equal>
		</his:ContentTag>
		<%
			boolean flag=false;
		%>
		
		<%-- <%
			//Pagination Logic
					PaginationFB fbPage= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage);
					fbPage.setCurrentPage(((InvResultEntryFB)request.getAttribute("InvResultEntryFB")).getCurrentPage());
					fbPage.setObjArrName(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
					fbPage.setAppendInTitle("List ");
					int maxRecord=15;
					fbPage.setMaxRecords(maxRecord);
		%> --%>

		<logic:equal name="InvResultEntryFB" property="showStatus" value="0">
			<%-- <his:PaginationTag name="fbPagination"></his:PaginationTag> --%>
			<logic:present name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO%>">
				<%
					flag=true;
				%>
				<!-- <table width="100%" bgcolor="#EBEBEB"> -->
				<table id="DataTable1" class="table display" style="width:100%">
				 <thead>
					<tr>
						<th width="3%" align="left"><b> <font color="#000000"
								size="2" face="Verdana, Arial, Helvetica, sans-serif"> <input
									type="checkbox" id="selectAllCheckbox" onclick="allSelected()" />
							</font>
						</b></th>
						<th width="12%" align="left"><b><font color="#000000"
								size="2" face="Verdana, Arial, Helvetica, sans-serif"> CR
									NO </font></b></th>
						<th width="10%" align="left"><b><font color="#000000"
								size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="patientName" /></font></b></th>
						<th width="8%" align="left"><b> <font color="#000000"
								size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="age/gender" /></font></b></th>
						<th width="10%" align="left"><b><font color="#000000"
								size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="departmentunit" /></font></b></th>
						<th width="10%" align="left"><b> <font color="#000000"
								size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="TestName" /></font></b></th>
						<th width="10%" align="left"><b><font color="#000000"
								size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="labName" /></font></b></th>

						<!-- updated by krishnan nema on 01/10/2018 -->

						<logic:notEqual name="InvResultEntryFB" property="patientType"
							value="2">
							<th width="8%" align="left"><b> <font color="#000000"
									size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="sampLabNo" /></font></b></th>
						</logic:notEqual>

						<logic:equal name="InvResultEntryFB" property="patientType"
							value="2">
							<th width="8%" align="left"><b> <font color="#000000"
									size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="accessionNowise" /></font></b></th>
						</logic:equal>

						<%-- <th width="8%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="sampLabNo"/></font></b>
					</th> --%>


						<th width="7%" align="left"><b> <font color="#000000"
								size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="patStatus" /></font></b></th>

						<th width="10%" align="left"><b> <font color="#000000"
								size="2" face="Verdana, Arial, Helvetica, sans-serif">Machine</font></b></th>
						
						
						<th width="5%" align="left"><b> <font color="#000000"
								size="2" face="Verdana, Arial, Helvetica, sans-serif">
									Add/Modify</font></b></th>
					</tr>
					</thead>
					<tbody>
				<!-- </table> -->

				<%-- <logic:empty name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO%>">
					
					<center> <font color="red" size="4"> <bean:message key="noRecord" /></font> </center>
					
				</logic:empty> --%>
				
				<logic:notEmpty name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO%>">
					
					<!-- <table width="100%" bgcolor="#EBEBEB" cellspacing="0" style="border-spacing: 0;"> -->
					
						<% List<ResultEntryVO> lstPatVO=(List<ResultEntryVO>)session.getAttribute(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
									 int i,size=0;
							 		if(lstPatVO!=null && lstPatVO.size()>0 )
							 			size=lstPatVO.size();
									
							 		/* int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
									int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
										String grpCode="";
										for(int j=startIndex;j<=endIndex;j++)
									{
										//int i=j-startIndex;
										boolean firstTimeTravesall=true;
									if(j<size)
													{ 
										ResultEntryVO voPat=lstPatVO.get(j);
										*/
										
										String grpCode="";
										int j=0;
										boolean firstTimeTravesall=true;
										
										for (ResultEntryVO voPat: lstPatVO){
										String  chkVal=voPat.getPatCRNo()+"#"+voPat.getRequisitionNo()+"#"+voPat.getRequisitionDNo()+"#"+voPat.getGroupCode()+"#"+voPat.getIsAddendum()+"#"+voPat.getRequisitionTypeCode()+"#"+voPat.getSampleCode()+"#"+voPat.getTestName()+"#";
										String chkValNewTestRaised=voPat.getPatCRNo()+"#"+voPat.getRequisitionNo()+"#"+voPat.getRequisitionDNo()+"#"+voPat.getGroupCode()+"#"+voPat.getTestCode()+"#"+voPat.getLabCode()+"#";  

										//String labCode=voPat.getLabCode();
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
						%>


						<tr>
							<td width="3%" align="left"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <input
									type="checkbox" class="jpCheckbox" name="chkSamplePatient"
									value='<%=chkVal%>' onclick="ValidateSameCrNo(this)">
							</font></td>
							<td width="12%" align="left"><font color="#000000" size="2"
								Rsear face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatCRNo()%></font>

							</td>

							<td width="10%" align="left"><font color="#000000"
								size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatName()%></font>


							</td>
							<td width="8%" align="left"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatAge()+"/"+voPat.getPatGender()%></font>

							</td>
							<td width="10%" align="left"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatUnitName()%></font>

							</td>
							<td width="10%" align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=voPat.getGroupName().equals("NA")?voPat.getTestName():voPat.getGroupName()%>
							</font>
							
							<!--Added by PrashantMi for Repeat R -->
						     <font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						      <%= voPat.getIsRepeat() == null || voPat.getIsRepeat().equals("0")||voPat.getIsRepeat().equals("1")?"":" [R]"/* +voPat.getIsRepeat() */%>
						     </font>
							
							</td>
							<td width="10%" align="left"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatLabName()%></font>
								

							</td>


							<td width="8%" align="left"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getTempSampleNo()==null?voPat.getLabNo():voPat.getTempSampleNo()%></font>

							</td>


							<td width="7%" align="left"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatStatus()%></font>

							</td>

 							<td width="10%" align="left">
									<div >
										<font color="##000000" size="2"
										<%
										if(voPat.getMachineCode()!=null && voPat.getMachineCode().equals("-1"))
										{
											voPat.setMachineCode(null);
										}
										%>
											face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getMachineCode()==null?"-":voPat.getMachineCode() %></font>
									</div>
								</td>
								
							<%-- <td width="12%" align="left">
				  		 
				  		 
				  		 <input type="button" onclick="newtestraised('<%=chkValNewTestRaised%>');" >New Test Raised
						  
				  		</td> --%>
							<%
								if(voPat.getIslabtestmodify()!=null && voPat.getIslabtestmodify().equals("1") ){
							%>

							<td width="5%" align="left">
								<div >
									<font color="blue" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <a
										onclick="newtestraised('<%=chkValNewTestRaised%>')">Add/Modify</a></font>
								</div>
							</td>
							<%
								}else{
							%>
							<td width="5%" align="left">
								<div >
									<font color="blue" size="5"
										face="Verdana, Arial, Helvetica, sans-serif"> -</font>
								</div>
							</td>

							<%
								}
							%>
							<%
								if(voPat.getDeptReport()!=null)
									  		{
							%>

							<td width="1%" align="left"><img
								title='Show Department Report'
								src='/HISInvestigationG5/hisglobal/images/add_remarks_sml.jpg'
								onclick="printReport('<%=voPat.getDeptReport()%>')"></td>

							<%
								}
							%>
						</tr>

						<%-- <% }}  } %> --%>
						
						<%  } }%>
						
						
						</logic:notEmpty>

				</tbody>
			</table>
				
				<logic:equal name="InvResultEntryFB" property="israisingsave" value="1">
						<div id="raisingentr" style="font-size: 18px;">
							<font color="red" size="3"
								face="Verdana, Arial, Helvetica, sans-serif">Requisition
								Raised Successfully</font>
						</div>
					</logic:equal>
					
			</logic:present>
		</logic:equal>


		<logic:present name="<%=InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO%>">


			<div class="subDivStyle">
				<his:SubTitleTag name="Requisition Forms">
					<img class="button" title="Show Patinet Details"
						src='<his:path src="/hisglobal/images/plusinv.png"/>'
						id="reqformshow" tabindex="1" onclick="f1()">
					<img class="button" title="Hide Patient Details"
						src='<his:path src="/hisglobal/images/Minus.png"/>'
						id="reqformhide" tabindex="1" onclick="f2()">
				</his:SubTitleTag>
			</div>
			<div class="subDivStyle" id="reqformss" style="display: none">
				<table width="100%" border="1" id="tbll">
					<tr>
						<td style="font-weight: bold; color: brown;">TEST NAME</td>
						<td style="font-weight: bold; color: brown;">REQUISITION
							FORMS</td>
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

var autoId='<%=autoCode%>';
//alert(autoId);
var hiddenId='<%=hiddenCode%>';

generateAuto(objList,autoId,hiddenId);

				
					
					
					</script>
			<input type="hidden" id='<%=hid%>' name="userCannedCode" />
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
						 		 ResultEntryVO invresultentryvo=new ResultEntryVO();
						 		HashMap<String, String> reqNoGroupCodeChkTestValueMap = new HashMap<String, String>();
						 		for(int k=0;k<size;k++)
						 		{
						 			invresultentryvo=new ResultEntryVO();
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
						 				 chkTestValue=invresultentryvo.getPatCRNo()+"#"+invresultentryvo.getRequisitionNo()+"#"+invresultentryvo.getRequisitionDNo()+"#"+invresultentryvo.getTestCode()+"#"+invresultentryvo.getSampleNo()+"#"+invresultentryvo.getLabCode()+"#"+invresultentryvo.getPatAge()+"#"+invresultentryvo.getPatGender()+"#"+invresultentryvo.getReportAvailableAfter()+"#"+invresultentryvo.getPatVisitDat()+"#"+invresultentryvo.getPatVisitNo()+"#"+invresultentryvo.getLabNo()+"#"+invresultentryvo.getEpisodeCode()+"#"+invresultentryvo.getDepartmentcode()+"#"+invresultentryvo.getPatdeptunitcode()+"#"+invresultentryvo.getRequisitionTypeCode()+"#"+invresultentryvo.getUomCode()+"#"+invresultentryvo.getTestName()+"#"+invresultentryvo.getPatLabName()+"#"+invresultentryvo.getSampleName()+"#"+invresultentryvo.getTempSampleNo()+"#"+invresultentryvo.getGroupCode()+"#"+invresultentryvo.getDynamicTemplatePrintedGroup()+"#"+invresultentryvo.getPatName()+"#"+invresultentryvo.getRefRange() +"#"+invresultentryvo.getDetpUnitCode()+"#"+invresultentryvo.getPatUnitName()+"#"+invresultentryvo.getIsrequisitionformneeded();
						 			
						 			 }
						 			String labCode=invresultentryvo.getLabCode();
						 			String reqDno=invresultentryvo.getRequisitionDNo();
						 			//String chkTestValue=invresultentryvo.getPatCRNo()+"#"+invresultentryvo.getRequisitionNo()+"#"+invresultentryvo.getRequisitionDNo()+"#"+invresultentryvo.getTestCode()+"#"+invresultentryvo.getSampleNo()+"#"+invresultentryvo.getLabCode()+"#"+invresultentryvo.getPatAge()+"#"+invresultentryvo.getPatGender()+"#"+invresultentryvo.getReportAvailableAfter()+"#"+invresultentryvo.getPatVisitDat()+"#"+invresultentryvo.getPatVisitNo()+"#"+invresultentryvo.getLabNo()+"#"+invresultentryvo.getEpisodeCode()+"#"+invresultentryvo.getDepartmentcode()+"#"+invresultentryvo.getPatdeptunitcode()+"#"+invresultentryvo.getRequisitionTypeCode()+"#"+invresultentryvo.getUomCode();                                                                                      
									System.out.println("chkTestValue : "+ chkTestValue);
									//System.out.println("invresultentryvo.getEmpNameWise : "+ invresultentryvo.getEmpNameWise());
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

                   <%
					if(invresultentryvo.getDeptReport()!=null)
						  		{
				%>
				<a onclick="printReport('<%=invresultentryvo.getDeptReport()%>')"><font
					color="white">View Departmental Report</font></a>
				<%
					}
				%>
      <%-- <% 
                   if(!pidnoo.equals("")) 
                   {
                    %>
                 <font  style="color: white;text-align">PID Number:<%=pidnoo %> </font>
                    <%} %> --%>
				<img class="button" title="upload files"
					src='<his:path src="/hisglobal/images/plusinv.png"/>'
					id="show<%=i%>" tabindex="1" onclick="<%=showDetail%>">
				<img class="button" title="delete upload files"
					src='<his:path src="/hisglobal/images/Minus.png"/>' id="hide<%=i%>"
					style="display: none;" tabindex="1" onclick="<%=hideDetail%>">
			</his:SubTitleTag>
			<table width="100%">
				<tr>
					<td class="tdfont" width="15%">
						<div align="right">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
									key="crNO" />&nbsp;
							</font>
						</div>

					</td>
					<td class="tdfonthead" width="16%">
						<div align="left">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <%=invresultentryvo.getPatCRNo()%>
							</font>
						</div>
					</td>
					<td class="tdfont" width="15%">
						<div align="right">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
									key="patientName" />&nbsp;
							</font>
						</div>
					</td>
					<td class="tdfonthead" width="16%">
						<div align="left">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <%=invresultentryvo.getPatName()%>
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
								 <bean:message 		key="sampleNo" />&nbsp;

 
									 <%} %>
									 
							</font>
						</div>
					</td>
					<td class="tdfonthead" width="16%">
						<div align="left">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <%=invresultentryvo.getTempSampleNo()==null?"NA":invresultentryvo.getTempSampleNo()%>
							</font>
						</div>
					</td>



				</tr>
			</table>
			<div id="showhide<%=i%>" style="display: none;">
				<table width="100%">
					<tr>
						<td class="tdfont" width="15%">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="age/gender" />&nbsp;
								</font>
							</div>
						</td>
						<td class="tdfonthead" width="16%">
							<div align="left">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=invresultentryvo.getPatAge()+"/"+invresultentryvo.getPatGender()%>
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
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="labName" />&nbsp;
								</font>
							</div>
						</td>
						<td class="tdfonthead" width="16%">
							<div align="left">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=invresultentryvo.getPatLabName()%>
								</font>
							</div>
						</td>
					</tr>
					<tr>
						<td class="tdfont" width="15%">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="departmentunit" />&nbsp;
								</font>
							</div>
						</td>
						<td class="tdfonthead" width="16%">
							<div align="left">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=invresultentryvo.getPatUnitName()%>
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
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="TestName" />
								</font>
							</div>
						</td>

						<td class="tdfonthead" width="16%">
							<div align="left">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=invresultentryvo.getGroupName().equals("NA")?invresultentryvo.getTestName():invresultentryvo.getGroupName()%>
								</font>
							</div>
						</td>
					</tr>
						<!-- added by krishnan nema on 30/01/2019 for chief complaints(visit reaon display) -->
						
				
				</table>
				
			</div>
			<table width="100%">
					<tr>
						<td class="tdfont" width="15%">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									Indications
								</font>
							</div>
						</td>

						<td class="tdfonthead" width="79%">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
 								<%-- <%=invresultentryvo.getVisitReason()==null?"":invresultentryvo.getVisitReason()%>	 --%>
 								<!-- Added by prashant -->	
 												
								 <input type="text" id="indicationInput#<%=invresultentryvo.getRequisitionNo()%>" class="indicationInputC#<%=invresultentryvo.getRequisitionNo()%>" onchange="indicationInputChange(this);" value="<%=invresultentryvo.getVisitReason()==null?"":invresultentryvo.getVisitReason()%>">							
									</font>
 								<html:hidden name="InvResultEntryFB" property="visitReason"/> 
							</div>
						</td>
					</tr>
				</table>
				
			<%-- 		<his:SubTitleTag name="Upload File">
 <img class="button" title="Add File"  src='<his:path src="/hisglobal/images/plusinv.png"/>' id="shwbrowse<%=invresultentryvo.getSampleNo()%>"      tabindex="1" onclick ="showBrowse(<%=invresultentryvo.getPatCRNo() %>,<%=invresultentryvo.getRequisitionNo() %>,<%=invresultentryvo.getSampleNo().substring(0,10) %>,<%=invresultentryvo.getSampleNo().substring(10,invresultentryvo.getSampleNo().length()) %>)" >  					
  	 <img class="button" title="Add File"  src='<his:path src="/hisglobal/images/plusinv.png"/>' id="addtabl<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo()+invresultentryvo.getLabNo():invresultentryvo.getSampleNo()%>"   tabindex="1" onclick ="addfile(<%=invresultentryvo.getPatCRNo() %>,<%=invresultentryvo.getRequisitionNo().substring(0,10) %>,<%=invresultentryvo.getRequisitionNo().substring(10,invresultentryvo.getRequisitionNo().length()) %>,<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo():invresultentryvo.getSampleNo().substring(0,10) %>,<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo():invresultentryvo.getSampleNo().substring(10,invresultentryvo.getSampleNo().length()) %>)" >				
  	<img class="button" title="Hide Patient Details"  src='<his:path src="/hisglobal/images/Minus.png"/>' id="hidetbl<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo()+invresultentryvo.getLabNo():invresultentryvo.getSampleNo() %>" style="display: none;"     tabindex="1" onclick ="hidefile(<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo():invresultentryvo.getSampleNo().substring(0,10) %>,<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo():invresultentryvo.getSampleNo().substring(10,invresultentryvo.getSampleNo().length())%>)" >
  			</his:SubTitleTag>
  			 --%>

			<%--  <div class="subDivStyle">
                                <his:SubTitleTag name="Req Form"> 
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
				  --%>


			<his:SubTitleTag name="Result Entry Form">
			
			 
                    
		<!-- UPLOAD FILES: -->
<html:hidden name="InvResultEntryFB" property="sampleAreaCode" />
				<%--  <img class="button" title="Add File"  src='<his:path src="/hisglobal/images/plusinv.png"/>' id="shwbrowse<%=invresultentryvo.getSampleNo()%>"      tabindex="1" onclick ="showBrowse(<%=invresultentryvo.getPatCRNo() %>,<%=invresultentryvo.getRequisitionNo() %>,<%=invresultentryvo.getSampleNo().substring(0,10) %>,<%=invresultentryvo.getSampleNo().substring(10,invresultentryvo.getSampleNo().length()) %>)" >  					
 --%>
				<%-- <img class="button" title="Add File"
					src='<his:path src="/hisglobal/images/plusinv.png"/>'
					id="addtabl<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo()+invresultentryvo.getLabNo():invresultentryvo.getSampleNo()%>"
					tabindex="1"
					onclick="addfile(<%=invresultentryvo.getPatCRNo()%>,<%=invresultentryvo.getRequisitionNo().substring(0,10)%>,<%=invresultentryvo.getRequisitionNo().substring(10,invresultentryvo.getRequisitionNo().length())%>,<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo():invresultentryvo.getSampleNo().substring(0,10)%>,<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo():invresultentryvo.getSampleNo().substring(10,invresultentryvo.getSampleNo().length())%>)">
				<img class="button" title="Hide Patient Details"
					src='<his:path src="/hisglobal/images/Minus.png"/>'
					id="hidetbl<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo()+invresultentryvo.getLabNo():invresultentryvo.getSampleNo()%>"
					style="display: none;" tabindex="1"
					onclick="hidefile(<%=invresultentryvo.getPatCRNo()%>,<%=invresultentryvo.getRequisitionNo().substring(0,10)%>,<%=invresultentryvo.getRequisitionNo().substring(10,invresultentryvo.getRequisitionNo().length())%>,<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo():invresultentryvo.getSampleNo().substring(0,10)%>,<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo():invresultentryvo.getSampleNo().substring(10,invresultentryvo.getSampleNo().length())%>)"
					name="hideminus"> --%>


			</his:SubTitleTag>
			<input type="hidden" value="<%=invresultentryvo.getPatLabName()%>"
				name="chkLabName" />

			<%
				} if(sameSampleNO)firstTimeTravesa=false;
			%>



			<div align="center"
				id="divfile<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo()+invresultentryvo.getLabNo():invresultentryvo.getSampleNo()%>"
				style="display: none; overflow: scroll;">
				<table
					id="filetable<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo()+invresultentryvo.getLabNo():invresultentryvo.getSampleNo()%>">
					<tr
						id="filerow<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo()+invresultentryvo.getLabNo():invresultentryvo.getSampleNo()%>">
						<td>
							<div
								id="uploadshw<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo()+invresultentryvo.getLabNo():invresultentryvo.getSampleNo()%>"
								style="display: none">
								<!-- <input type="file" name="file" multiple="multiple" /> -->
								<%--  <input id="uploadfile" type='file' name='file[0]' >
  		     <input type='hidden' value='<%=invresultentryvo.getPatCRNo()+"#"+invresultentryvo.getRequisitionNo() %>'>
  		 --%>
							</div> <%-- 	 <html:file property="fileUpload" name="InvResultEntryFB" ></html:file> --%>
						</td>
					</tr>
				</table>
			</div>


			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<%
						if(lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString()!=null
								  && (lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).isDoCreateTemplate() == true)
								   ) 
						  
						  {
					%>
					<td width="2%" class="tdfonthead">
						<%--   <input type="hidden" name="<%=i%>LoincCode"  value="<%=lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).getParaLoinc()%>"/>		 --%>
						<input type="checkbox" id="<%=i%><%=k%><%=i%>chkBOx"
						name="chkResultEntryPatient" value='<%=chkTestValue%>'>
						<html:hidden name="InvResultEntryFB" styleId="<%=reqDno%>"
							value="<%=labCode%>" property="cannedLabCode" /> <html:hidden
							name="InvResultEntryFB" property="cannedOrMacroCheck" />


					</td>

					<td class="tdfonthead" width="10%">
						<div align="left">
							<font color="blue" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <%=lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).getDynamnicTemplateResultEntryGroup().equals("1")?invresultentryvo.getGroupName():invresultentryvo.getTestName()%>
							</font>
							
						
						<!--Added and commented by prashant for Repeat R untill further requirment -->
						<%-- <font color="blue" size="2"
						  face="Verdana, Arial, Helvetica, sans-serif"> <%=invresultentryvo.getIsRepeat().equals("0")||invresultentryvo.getIsRepeat().equals("1")?" [N]":" [R]-"+invresultentryvo.getIsRepeat()%>
						</font> --%>
						
							
							<!-- dddddddd -->
							<%
								String inst=invresultentryvo.getInstructionPat()==null?"NA":invresultentryvo.getInstructionPat();
									 					inst=inst.replace("\r\n","<br>");
																/* String inst="d"; */
							%>
							<%
								if(!inst.equals("NA")){
							%>
							<img class="button" title="Show Instructions"
								src='<his:path src="/hisglobal/images/add_remarks_sml.jpg"/>'
								tabindex="1" onclick="showInstructions5('<%=inst%>');">
							<%
								}else{}
							%>



							<%
								if(!invresultentryvo.getIsrequisitionformneeded().equals("0")) {
							%>
							<%-- &nbsp&nbsp<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick="ShowRequistionForm('<%=invresultentryvo.getTestCode()%>','<%=invresultentryvo.getTestName()%>','<%=invresultentryvo.getLabCode()%>','<%=invresultentryvo.getLabName()%>','<%=invresultentryvo.getRequisitionDNo()%>')"> --%>
							<%
								}
							%>



							<div id="blanket" style="display: none;"></div>
							<div id="popUpDiv5" style="display: none;" align="center">

								<his:TitleTag name="Instructions For Technician">
									<img src='/HISInvestigationG5/hisglobal/css/close.png'
										onClick="closeInstructions();">
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

								<img src='/HISInvestigationG5/hisglobal/images/ok.gif'
									onClick="closeInstructions();">


							</div>



							<!-- END -- TO DISPLAY INSTRUCTIONS -->


						</div>

					</td>
					<td width='99.99%' id="<%=i%><%=k%><%=i%>templateValue"
						class="tdfonthead"><%=lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString()%>
						<%
							}else if(lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString()!=null
							   && lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).isDoCreateTemplate() == false)
							   {
								  // do nothing 
							   }
							   else   {
						%> <left> <font color="red" size="4"> <bean:message
								key="resultEntryNot" />&nbsp;<%=invresultentryvo.getTestName()%>
						</font> </left></td>
					<%
						}
					%>
				</tr>

			</table>








			<%
				}
						 		
						 	
								
							
								if(!finalRemarkCodeList.contains(invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo()+"#"+invresultentryvo.getTempSampleNo()))
					  			{
					  				finalRemarkCodeList.add(invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo()+"#"+invresultentryvo.getTempSampleNo());
					  				
					  			if(invresultentryvo.getFinalRemarkReqd() != null && invresultentryvo.getFinalRemarkReqd().equals("1")){
			%>

			<table width="100%" cellpadding="0" cellspacing="0" border="0">

				<!-- added by ashu  -->

				<%-- <html:hidden name="InvResultEntryFB" property="currentElement"/>
	  							<html:hidden name="InvResultEntryFB" property="currentElementName"/>
	   							<html:hidden name="InvResultEntryFB" property="editorName"/> --%>


				<tr>
					<td width="2%" class="tdfonthead"></td>
					<td width="20%" class="tdfonthead">
						<div align="left">&nbsp;&nbsp;&nbsp;&nbsp;Comments</div>
					</td>

					<td width="78%" class="tdfonthead">
						<div align="left">
                             <% String sp="/"; %>
							<html:textarea style="width: 900px;"
								value="<%=invresultentryvo.getFinalRemarkString()%>"
								styleId="<%=invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo()+'#'+invresultentryvo.getTempSampleNo().split(sp)[0]%>"
								name="InvResultEntryFB" property="finalRemarksValue"
								onclick="onComment();">
							</html:textarea>
							<html:hidden name="InvResultEntryFB" property="crNoReqNoString"
								value="<%=invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo()+'#'+invresultentryvo.getTempSampleNo().split(sp)[0]%>" />
							<html:hidden name="InvResultEntryFB"
								property="currentElementName"
								value="<%=invresultentryvo.getRequisitionNo()+'#'+invresultentryvo.getPatCRNo()%>" />
							<html:hidden name="InvResultEntryFB" property="currentElement"
								value="TA  " />
						</div>
					</td>
				</tr>

				<tr>
				</tr>

				<tr>

					<td width="2%" class="tdfonthead"></td>
					<td width="20%" class="tdfonthead">
						<div align="left">&nbsp;&nbsp;&nbsp;&nbsp;Validator</div>
					</td>

					<td width="78%" class="tdfonthead">
						<div align="left">

							<logic:present
								name="<%=InvestigationConfig.EMP_NAME_COMBO_FOR_RESULT_ENTRY%>">

								<html:select name="InvResultEntryFB" property="empNameWise"
									tabindex="1">
									<html:option value="0">All</html:option>
									<html:options
										collection="<%=InvestigationConfig.EMP_NAME_COMBO_FOR_RESULT_ENTRY%>"
										property="value" labelProperty="label" />
								</html:select>

							</logic:present>

						</div>
					</td>

				</tr>
			</table>


			<%
				}}
						 		
								
								
						 		
						 		i++;
					}
			%>

			<%-- <table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
				  				
			      				<td width="2%" class="tdfonthead"></td>
				  				<td width="20%" class="tdfonthead">
				  					<div align="left">
				  				&nbsp;&nbsp;&nbsp;&nbsp;Validator
				  				</div>				
				  				</td>
			      
			      				<td width="78%" class="tdfonthead">
				  				<div align="left">
				  				
				      				<logic:present name="<%=InvestigationConfig.EMP_NAME_COMBO_FOR_RESULT_ENTRY%>">
				      				
				      				<html:select name="InvResultEntryFB" property="empNameWise"    tabindex="1"   >
				       					<html:option value="0">All</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.EMP_NAME_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				 
				 					</logic:present>
				  				
				  				</div>
				  				</td>
			    
				  				</tr>
				  				</table> --%>


			<!-- 		 <fieldset	id="cannedField" style="border: solid 3px blue; background-color: CCE6FF;display:none">
 -->
			<div id="cannedField"
				style="display: none; position: fixed; overflow-y: auto;">
				<div draggable="true">

					<his:TitleTag name="Canned List">
						<%-- <his:insertDateTag/> --%>



						<img src='/HISInvestigationG5/hisglobal/css/close.png'
							onClick="autocompleteBox_close();">

					</his:TitleTag>
					<table width="100%" id="autoCannedCombo">


						<html:hidden name="InvResultEntryFB" property="currentElement" />
						<html:hidden name="InvResultEntryFB" property="currentElementName" />
						<html:hidden name="InvResultEntryFB" property="editorName" />

						<tr>
							<td class="tdfonthead" width="20%">
								<div align="right">
									<b><bean:message key="CanedCode" /></b>
								</div>
							</td>
							<td class="tdfont" width="20%">
								<div align="left" class="ui-widget">
									<input type="text" id="automplete-4" name="userCanned"
										size="30" style="width: 100px;"
										onkeypress="if(event.keyCode==13) addCannedDetail('CANNED');return validateAlphaNumericOnly(event,this);">



								</div>
							</td>

							<td class="tdfont" width="20%">
								<div align="left">
									<img class="button"
										src='<his:path src="/hisglobal/images/btn-ok.png"  />'
										onclick="setCannedDetail('CANNED');autocompleteBox_close();">
								</div>
							</td>
							<%--  <td class="tdfonthead" width="20%">
														 
														 
				 
				    	 <img class="button" align="left"  src='<his:path src="/hisglobal/images/btn-ok.png"  />' onclick="addCannedDetail('CANNED')"    >
				    
				 
														 
														 
														 
														 </td> --%>
						</tr>
					</table>

					<table id="addMoreValue" width="100%"></table>
					<div align="center">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-ok.png"  />'
							onclick="setCannedDetail('CANNED');autocompleteBox_close();">
					</div>

				</div>
			</div>









			<div id="macroField"
				style="display: none; position: fixed; overflow-y: auto;">
				<div draggable="true">
					<his:TitleTag name="Macro List">
						<%-- <his:insertDateTag/> --%>



						<img src='/HISInvestigationG5/hisglobal/css/close.png'
							onClick="autocompleteBox_close_macro();">

					</his:TitleTag>
					<table width="100%" id="autoMacroCombo">


						<html:hidden name="InvResultEntryFB" property="currentElement" />
						<html:hidden name="InvResultEntryFB" property="currentElementName" />
						<html:hidden name="InvResultEntryFB" property="editorName" />


						<tr>
							<td class="tdfonthead" width="20%">
								<div align="right">
									<b><bean:message key="macroCode" /></b>
								</div>
							</td>
							<td class="tdfont" width="20%">
								<div align="left" class="ui-widget">
									<input type="text" id="automplete-m" name="userMacro" size="30"
										style="width: 100px;"
										onkeypress="if(event.keyCode==13) addCannedDetail('MACRO');return validateAlphaNumericOnly(event,this);">


								</div>
							</td>

							<%--  <td class="tdfonthead" width="20%">
														 
														 
				
				    
				    	 <img class="button"  align="left" src='<his:path src="/hisglobal/images/btn-ok.png"  />' onclick="addCannedDetail('MACRO')"    >
				  
														 
														 
														 
														 </td>
														  --%>

						</tr>
					</table>


					<table id="addMoreValuemacro" width="100%"></table>


					<div align="center">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-ok.png"  />'
							onclick="setCannedDetail('MACRO');autocompleteBox_close_macro();">
					</div>

				</div>
			</div>
			<!--  		                             </fieldset>
 -->



			<div id="canned">
				<div draggable="true">

					<his:TitleTag name="Canned List">
						<%-- <his:insertDateTag/> --%>



						<img src='/HISInvestigationG5/hisglobal/css/close.png'
							onClick="lightbox_close();">

					</his:TitleTag>
					<table id="addMoreValue" width="100%">




						<html:hidden name="InvResultEntryFB" property="currentElement" />
						<html:hidden name="InvResultEntryFB" property="currentElementName" />

					</table>


					<his:ButtonToolBarTag>

						<img class="button"
							src='<his:path src="/hisglobal/images/btn-ok.png"  />'
							onclick="setCannedDetail('CANNED')">

					</his:ButtonToolBarTag>


				</div>
			</div>


			<div id="macro">
				<div draggable="true">
					<his:TitleTag name="Macro List">
						<%-- <his:insertDateTag/> --%>



						<img src='/HISInvestigationG5/hisglobal/css/close.png'
							onClick="lightbox_close_macro();">

					</his:TitleTag>
					<table id="addMoreValuemacro" width="100%">



						<html:hidden name="InvResultEntryFB" property="currentElement" />
						<html:hidden name="InvResultEntryFB" property="currentElementName" />

					</table>


					<his:ButtonToolBarTag>

						<img class="button"
							src='<his:path src="/hisglobal/images/btn-ok.png"  />'
							onclick="setCannedDetail('MACRO')">

					</his:ButtonToolBarTag>


				</div>
			</div>
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

			<logic:equal name="InvResultEntryFB" property="newEntry" value="1">
				<img class="button"
					src='<his:path src="/hisglobal/images/EnterResults.png"/>'
					id="nextDiv" style="cursor: pointer; display: none" tabindex="1"
					onclick="displaySamplePatDetails();"
					onkeypress="if(event.keyCode==13)  displaySamplePatDetails();">
			</logic:equal>

			<logic:equal name="InvResultEntryFB" property="newEntry" value="2">
				<img class="button"
					src='<his:path src="/hisglobal/images/btn-mo.png"/>' id="nextDiv"
					style="cursor: pointer; display: none" tabindex="1"
					onclick="displaySamplePatDetails();"
					onkeypress="if(event.keyCode==13)  displaySamplePatDetails();">
			</logic:equal>
			<!-- updated by krishnan nema on 29/01/2018 for save to drafts changes -->
			<logic:equal name="InvResultEntryFB" property="newEntry" value="3">
				<img class="button"
					src='<his:path src="/hisglobal/images/EnterResults.png"/>'
					id="nextDiv" style="cursor: pointer; display: none" tabindex="1"
					onclick="displaySamplePatDetails();"
					onkeypress="if(event.keyCode==13)  displaySamplePatDetails();">
			</logic:equal>
			
			<logic:present
				name="<%=InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO%>">

				<logic:equal name="InvResultEntryFB" property="newEntry" value="1">
					<img class="button"
						src='<his:path src="/hisglobal/images/SaveAll.png"/>'
						id="saveDivAll" onkeypress="if(event.keyCode==13)setIndication(); if(event.keyCode==13)onSave();"
						tabindex="1" onclick="setIndication(); selectAll();">
					<img class="button"
						src='<his:path src="/hisglobal/images/btn-sv.png"/>' id="saveDiv"
						onkeypress="if(event.keyCode==13)setIndication(); if(event.keyCode==13)onoSave(); " tabindex="1"
						onclick="setIndication(); onSave();">
						
					<!-- added by krishnan nema on 28/01/2018 for save to draft change -->
					<img class="button"
						src='<his:path src="/hisglobal/images/saveToDraft.png"/>' id="saveDivDraft"
						onkeypress="if(event.keyCode==13)setIndication(); if(event.keyCode==13)onSaveToDraft();" tabindex="1"
						onclick="setIndication(); onSaveToDraft();">
						
				</logic:equal>

				<logic:equal name="InvResultEntryFB" property="newEntry" value="2">
					<img class="button"
						src='<his:path src="/hisglobal/images/btn-mo-all.png"/>'
						id="saveDivAll" onkeypress="if(event.keyCode==13)setIndication(); if(event.keyCode==13)onSave();"
						tabindex="1" onclick="setIndication(); modifyAll();">
					<img class="button"
						src='<his:path src="/hisglobal/images/btn-mo.png"/>' id="saveDiv"
						onkeypress="if(event.keyCode==13)setIndication(); if(event.keyCode==13)onSave();" tabindex="1"
						onclick="setIndication(); onModify();">
				</logic:equal>
				<!-- updated by krishnan nema on 29/01/2019 for save  to drafts -->
				<logic:equal name="InvResultEntryFB" property="newEntry" value="3">
					<img class="button"
						src='<his:path src="/hisglobal/images/finalSave.png"/>'
						id="saveDivAll" onkeypress="if(event.keyCode==13)setIndication(); if(event.keyCode==13)onSave();"
						tabindex="1" onclick="setIndication(); modifyAll();">
						
					<img class="button"
						src='<his:path src="/hisglobal/images/saveToDraft.png"/>'
						id="saveDivDraft" onkeypress="if(event.keyCode==13)setIndication(); if(event.keyCode==13)onModifyAndDraft();"
						tabindex="1" onclick="setIndication(); onModifyAndDraft();">
						
				</logic:equal>


				<img class="button"
					src='<his:path src="/hisglobal/images/cannedFile.png"/>'
					onclick="popupCallCanned();" tabindex="1">

				<img class="button"
					src='<his:path src="/hisglobal/images/macro.png"/>'
					onclick="popupCallMacro();" tabindex="1">


			</logic:present>
			<logic:present
				name="<%=InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO%>">
				<logic:present
					name="<%=InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO%>">
					<!-- this logic for cancel button hide in list page -->
					<img class="button"
						src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="cancel"
						tabindex="1" style="cursor: pointer"
						onkeypress="if(event.keyCode==13) submitFor();" tabindex="1"
						onclick="submitFor();">
				</logic:present>
			</logic:present>
		</his:ButtonToolBarTag>
		<logic:equal name="InvResultEntryFB" property="showStatus" value="1">
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
		</logic:equal>
		<div id="divLegends" style="display: none">
			<his:ContentTag>
				<table width="100%" colspacing="1" colpadding="0"
					style="clear: both; border-left: 1px solid #003366; border-top: 1px solid #003366">
					<tr>
						<td width="20%"><font color="blue" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Ctrl + F9</div>
						</font></td>
						<td width="80%"><font color="blue" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Open Canned File</div>
						</font></td>
					</tr>
					<tr>
						<td width="20%"><font color="blue" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Ctrl + F12</div>
						</font></td>
						<td width="80%"><font color="blue" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Close Canned File</div>
						</font></td>
					</tr>
					<tr>
						<td width="20%"><font color="red" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Shift + F9</div>
						</font></td>
						<td width="80%"><font color="red" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Open Macro File</div>
						</font></td>
					</tr>
					<tr>
						<td width="20%"><font color="red" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Shift + F12</div>
						</font></td>
						<td width="80%"><font color="red" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Close Macro File</div>
						</font></td>
					</tr>

				</table>


			</his:ContentTag>
		</div>

		<html:hidden name="InvResultEntryFB"
			property="resultEntryTemplateValue" />
			
		<html:hidden name="InvResultEntryFB" property="isSaveToDraft" />
		<html:hidden name="InvResultEntryFB" property="parameterCode" />
		<html:hidden name="InvResultEntryFB" property="parantParameterCode" />
		<html:hidden name="InvResultEntryFB" property="requisitionDNo" />
		<html:hidden name="InvResultEntryFB"
			property="resultEntryTemplateValueWithHash" />
		<html:hidden name="InvResultEntryFB" property="israisingsave" />
		<html:hidden name="InvResultEntryFB" property="patientType" />
	

		<input type="hidden" name="cannedDataCount" value="0" />
		<input type="hidden" name="cannedDetails" value="0" />
		<input type="hidden" id="hiddenid4" name="userCannedCode" />
		<input type="hidden" id="hiddenidauto100221007" name="userCannedCode" />

		<input type="hidden" id="hiddenidauto100221028" name="userCannedCode" />
		<input type="hidden" id="hiddenidm" name="userMacroCode" />
		<input type="hidden" name="macroDataCount" value="0" />
		<input type="hidden" name="macroDetails" value="0" />
		
		 

		<his:status />

		
	</html:form>
	
</body>

<script>


$(document).ready(
	    function setDataTable1(){
	    	$.fn.dataTable.moment( 'DD-MMM-YYYY' );
	    	if ($('#DataTable1').length)
	    	var table = $('#DataTable1').DataTable( {
	        	/* "dom": '<<ip><lf>rt>', */
				pageLength : 25,
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
	             ]        
	     });
	});


</script>
</html>
