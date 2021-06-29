<!-- 
 

 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: PUNEET SINGH KHURANA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : RESULT REPORT ADDENDUM
 ## Purpose						        : For Addendum and Amendment of report and patient demographics
 ## Date of Creation					: 19/07/2015
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
<%@page
	import="new_investigation.transactions.controller.fb.invReportAddendumFB"%>
<%@include file="/hisglobal/invsnomed.html" %>	
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

<his:css src="/hisglobal/css/icon.css" />
<his:css src="/hisglobal/css/email.css" />
<his:css src="/hisglobal/css/textboxcss.css" />
<his:css src="/hisglobal/css/drop.css" />
<his:css src="/hisglobal/css/Cannedstyle.css" />
<link rel="stylesheet" href="/new_investigation/css/Date/site-demos.css">
<his:css src="/hisglobal/css/invPopupReport.css" />

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
<his:javascript src="/bloodbank/js/bloodRequisition.js" />
<his:javascript src="/new_investigation/js/reportsValidation.js" />
<his:javascript src="/new_investigation/js/onlinePatientAcceptance.js" />
<his:javascript src="/new_investigation/js/jquery-1.11.1.min.js" />
<his:javascript src="/new_investigation/js/jquery.validate.email.js" />
<his:javascript src="/new_investigation/js/additional-methods.min.js" />
<his:javascript src="/new_investigation/js/ckeditor/ckeditor.js" />
<his:javascript src="/new_investigation/js/wysiwyg.js" />
<his:javascript src="/new_investigation/js/wysiwyg-settings.js" />
<his:javascript src="/hisglobal/js/jquery-1.7.2.js" />
<his:javascript src="/hisglobal/js/css-pop-report-inv.js" />

<%@include file="/hisglobal/invsnomed.html" %>
<link href="/HISInvestigationG5/hisglobal/snomedct/css/jquery-ui.css" rel="stylesheet">
<link rel="stylesheet" href="/HISInvestigationG5/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<link rel="stylesheet" href="/HISInvestigationG5/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">


<%@page import="hisglobal.hisconfig.Config"%>

<%@page import="new_investigation.vo.template.ResultEntryVO"%>
<%@page
	import="new_investigation.vo.template.ResultEntryVOGroupByValidatedBy"%>



<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page
	import="new_investigation.transactions.controller.fb.invReportAddendumFB"%>



<his:javascript src="/hisglobal/js/jquery-ui.js" />
<his:javascript src="/hisglobal/js/sweet-alert.min.js" />
<script src="media/misc/datepicker1.js" type="text/javascript"></script>
<script type="text/javascript"
	src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js"
	djConfig="parseOnLoad: true"> </script>

<%@page import="new_investigation.InvestigationConfig"%>

<html>
<script>


function templatecombocall(obj)
{
	
//alert("id"+obj.id);
//alert(obj.name);

var testcode=obj.name
testcode=testcode.split("#")[3];
var reqdno=obj.id;
	reqdno=reqdno.split("#")[0];
//alert(testcode);
 testcode = testcode.substring(0, 5);
// alert(testcode);
/* var al=obj.name+"#order"; */
var skillsSelect = document.getElementById(obj.id);
var selectedText = skillsSelect.options[skillsSelect.selectedIndex].text;
//alert(selectedText);


//document.getElementById("961011000118092610000301#null#template#100101004").disabled = true;

    var val=checkIsparameterDependent(testcode,selectedText,reqdno);
  //  alert(val);
  //  alert(val.includes("Hyper Link"));
     if(val.includes("Hyper Link")  )
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
    	 var hyperparacode=val.split("@@@")[0];
    	 hyperparacode=hyperparacode.split("###")[1];
    	 
         var hyperfinalhide="td"+"#"+idd1+"#"+idd2+"#"+idd3+"#"+testcode+hyperparacode;
         var idde1="idd"+idd1+"#"+idd2+"#"+idd3+"#"+testcode+hyperparacode;
         
         var hyperfinalhidee=idd1+"#"+idd2+"#"+idd3+"#"+testcode+hyperparacode;

         var textareafinalhide=idd1+"#"+idd2+"#"+idd3+"#"+testcode+textareaparacode;

      //   alert("hyperfinalhide"+hyperfinalhide);
          if(selectedText=="Positive")
        {   
           //   alert("selectedText"+selectedText);
              document.getElementById(hyperfinalhide).style.display = "";
                 
        // document.getElementById(textareafinalhide).disabled = true;
        // document.getElementById(textareafinalhide).value = "";

        }
          else
              {

        	 // alert("idde1"+idde1);
        	  if(document.getElementById(idde1)!=null)
              document.getElementById(idde1).innerHTML = "";

              
              document.getElementById(hyperfinalhide).style.display = "none";
              document.getElementById(hyperfinalhidee).style.value = "";
              
              document.getElementsByName('hyperLinkTableSession')[0].value=""
            	  document.getElementsByName('selectValuemapping')[0].value="";
               // document.getElementById(textareafinalhide).disabled = false;
              }
     
 	   }

    
}


function checkIsparameterDependent(dependentelementcodevalue,selectedText,reqdno)
{
	//alert("setInSession"+dependentelementcodevalue);

	  //vall = vall.replace(/#/g, '^');
	//alert("new"+selectedText+"#"+reqdno);
	var dd= document.getElementsByName('iscallfromonloaddynamic')[0].value;
	//alert(tmpSampleCode+"  "+tmpLabCode+"     "+tmpTestCode+"    "+tmpLabTestCodeArray);
	var remarks = "";
	var flg = false;
	var labTestCodeArray = "";
	var _mode = "CHECKCISPARAMETERDEPENDENT";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/invResultEntryTemplateTile.cnt?hmode="+_mode+"&dependentelementcodevalue="+dependentelementcodevalue+"&selectedindex="+selectedText+"&requisitionDNo="+reqdno+"&iscallfromonloaddynamic="+dd, sync:true, postData: "", handleAs: "text",
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



function getechodata(reqdno)
{
	//alert("setInSession"+dependentelementcodevalue);

	  //vall = vall.replace(/#/g, '^');
	//alert("new"+selectedText+"#"+reqdno);
	
	//alert(tmpSampleCode+"  "+tmpLabCode+"     "+tmpTestCode+"    "+tmpLabTestCodeArray);
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

function hyper(thiss,url1)
{
	//url1="/HISInvestigationG5/new_investigation/antibioticprocesss.cnt";
	var matchid=thiss.id;
	var values= matchid.split('#');
	var echodata=  getechodata(values[0]);

    
//	var elementCode=thisobj.name.split('#')[3];
	mywindow=window.open (url1+"?requisitionDNo="+values[0]+"&showStatus="+status+"&testParaCode="+values[3]+"&echodata="+echodata,"_blank","scrollbars=1,directories=no, status=no,width=1000, height=550,top=150");
	/*mywindow.moveTo(300,300); */
				
}





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
	
function showLegends1(){
	  document.getElementById("divLegends1").style.display="block"; 
}
function showLegendsNone1(){
document.getElementById("divLegends1").style.display="none";
}

function labBased()
{
	//added by krishnan nema on 28/09/2018
	 var patTestType = document.getElementsByName("labCode")[0].value.split("#")[1];
	// alert("patTestType"+patTestType);
	 document.getElementsByName("patTestType")[0].value = patTestType;
	 
	 if(!validateTodayOrDate())
	 {return false;}
	if(document.getElementsByName!="-1")
	{
		document.getElementsByName('onLoadValue')[0].value="ONLOAD";
	document.getElementsByName('hmode')[0].value="GETDETAILS";
     document.forms[0].submit();
	
	}
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
     
     
     if(hosCodeLen==3)
		{ 
    	 
    	   
     
          if (textLength==5||textLength==13||textLength==0)
	        {
        	  document.getElementsByName("patCRNo")[0].value="";
        	  if(textLength==13)
	           {
	             document.getElementsByName("patCRNo")[0].value=crno;
	            }
        	  
        	  	if( document.getElementsByName("patCRNo")[0].value=="")
				 {
		    		alert("Enter Valid CR No.");
		       		 
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
	            if(document.getElementsByName("tempPatCRNo")[0])
	            {
	            document.getElementsByName("tempPatCRNo")[0].focus()
	             }    
	           
	          }}
       
      if(hosCodeLen==5)
    	{ 
			    if (textLength==7||textLength==15||textLength==0)
				 {
			    	
			    	
			    	  
			    	  
			    	 document.getElementsByName("patCRNo")[0].value="";
			    	if(textLength==15)
						 {
						 document.getElementsByName("patCRNo")[0].value=crno;
						 }

					         
			    	if( document.getElementsByName("patCRNo")[0].value=="")
					 {
			    		alert("Enter Valid CR No.");
			       		 
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
				     		if(document.getElementsByName("tempPatCRNo")[0])
				     		{
				            document.getElementsByName("tempPatCRNo")[0].focus()
				                      }    
			          
			     }

               
    	}
   return true;
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


function displaySamplePatDetails1()
{	
	
	var count=0;
	document.getElementsByName('isPatDetailPage')[0].value="1";
	
	var concatenateChkBoxVal="";
	//var cbs = document.getElementsByTagName('input');
	var cbs =document.getElementsByName('chkSamplePatient');

  var count1=0;
	for(var i1=0; i1 < cbs.length; i1++) 
             {
		if(cbs[i1].checked)
    	{
    		
    	count1++;
    	}
                
             }

             if(count1>1)
                 {
            	 alert("Please Choose Only one record");
                 return null ;
                 }
	
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
	document.getElementsByName('hmode')[0].value='GETPATDTL';
	
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
	
/* 	if(obj.checked)
	{
		document.getElementById('nextDiv').style.display="";
		document.getElementById('nextDiv1').style.display="";        
	}
  else
     	{
	  document.getElementById('gob').style.display="";
      	document.getElementById('cancel').style.display="";
      	
          } */
	 
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
	document.getElementsByName('diagnosisCheck')[0].value='0';
	document.getElementsByName('amendmentCheck')[0].value='0';
	document.getElementsByName('addendumCheck')[0].value='0';
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

function onSave()
{


	
	//only when demogrphics is selected
	if(document.getElementsByName("diagnosisCheck")[0].value=="1" && document.getElementsByName("addendumCheck")[0].value=="" && document.getElementsByName("amendmentCheck")[0].value=="")
		{
		
		
		  document.getElementsByName('showStatus')[0].value='0';
	       document.getElementsByName('hmode')[0].value="SAVE";
	       document.forms[0].submit();
	       
		
		}
	else if(document.getElementsByName("diagnosisCheck")[0].value=="" && document.getElementsByName("addendumCheck")[0].value=="1" && document.getElementsByName("amendmentCheck")[0].value=="")
		{
		
		 if(document.getElementsByName("reasonCode")[0].value=="-1")
		{
		alert("Please Select Reason");
		return false;
		}
		
		
		  document.getElementsByName('showStatus')[0].value='0';
	       document.getElementsByName('hmode')[0].value="SAVE";
	       document.forms[0].submit();
		
		}
	else{
	
		 if(document.getElementsByName("reasonCode")[0].value=="-1")
		{
		alert("Please Select Reason");
		return false;
		} 
       var count=0;
       var concatenateChkBoxVal="";
       var cbs =document.getElementsByName('chkResultValidationPatient');
     //  alert(cbs);
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
              
             //    alert("type is " + typ);
                   if(typ!=hidddentext)
                   {
                  	 
                  //	 alert("not hidden");
                   splitTemplateValue=name.split("#");
                   reqNO.push(splitTemplateValue[0]);
                    parameterValue=splitTemplateValue[3];
              //      alert("parameter value    "+parameterValue);
                    parameterCode.push(parameterValue.substring(0, 5));
                    parantParameter.push(parameterValue.substring(9,18));
                  
                      
                //  alert("parameterValue"+parameterValue+"reqno"+reqNO+"parameter"+parantParameter);
                 
                    /*   if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value=="")
                             {
                                    alert("Enter the field Focussed");
                                    document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].focus();
                                 return false;
                              } */
                 
                         // document.getElementsByName('resultEntryTemplateValue').value.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);       
                    //  resultEntryTemplateValue.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);
                    var resultValidationTemplateValue="";
                    
                    if(id.indexOf("auto")!=-1)    
                  	  {
                  
                  	 if( document.getElementById(hiddenid).value!=null && document.getElementById(hiddenid).value!="" )
                  	  resultValidationTemplateValue=document.getElementById(hiddenid).value;
                  	 else
                  		 resultValidationTemplateValue=document.getElementById(defaultid).value;
                  	  } 
                    else if  (id.indexOf('chkbox')== -1 )
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
                          name+='#'+resultValidationTemplateValue+'#'+values+"#-";
                          
                       concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                       concatenateChkBoxVal+='@';
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
      //  	alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length);
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
		   
	        
	   //     alert(resultEntryTemplateValue);
	        name+='#'+resultEntryTemplateValue+'#'+values+"#-";;
	          
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
              if(resultEntryTemplateValue.length>12000)
        	  {
        	  alert("Editor Data limit is 12000, including spaces. Please don't exceed the limit");
        	  return false;
        	  
        	  
        	  }
	          name+='#'+resultEntryTemplateValue+'#'+values+"#-";;
          
 	          }

 	          else
 	        	  {
     	         var resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value;
		          name+='#'+resultEntryTemplateValue+'#'+values+"#-";;
	          
 	        	  
 	        	  }
        /*        var resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value;
                        name+='#'+resultValidationTemplateValue+'#'+values;*/
                 
                     concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                     concatenateChkBoxVal+='@';

              
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
          	  }else if(document.getElementById(ide)!=null && document.getElementById(ide).innerHTML!='')
         	  {
             		//alert( document.getElementById(id).onclick);
             	  
               	  var idee=id.split("#")[0]+"$$";
               	   idee+=id.split("#")[3]+"$$";
           		  resultEntryTemplateValue="hyperchanks" ;
           		  
                    // alert("true1"+resultEntryTemplateValue);
               	  }
  				  
      	    
            }

          //  var uploadidcheck="view@@"+id;
            if(id.includes("view@@")==false) // not to add file upload view hyperlink option by chandan 
                {

            	var tocheckfnctn=document.getElementById(id).onclick;
        		var valuoffuncntn=tocheckfnctn;
        		valuoffuncntn=valuoffuncntn.toString();
        		//valuoffuncntn=valuoffuncntn.split("hyper");
              //        alert(""+valuoffuncntn);
                 if(valuoffuncntn.includes("echo"))
                  {
                    //   alert("match found");

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
        //      alert(concatenateChkBoxVal);
            /*     }
           }
             */      
                  
                }
                }
         }
	          
         
         }
   
    
     }
       

    //  alert("Finally concatenateChkBoxValues For Save "+concatenateChkBoxVal);
   //  return null;  
       
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
         alert("please select a Atleast One record");
         return false;
       }
        
       
       document.getElementsByName('showStatus')[0].value='0';
       document.getElementsByName('hmode')[0].value="SAVE";
       document.forms[0].submit();
        
   return false;
	}
        
  }
  
function modifyAll()
{	var cbs=document.getElementsByName('chkResultValidationPatient');

	
for(var i=0; i < cbs.length; i++)
cbs[i].checked=true;
	
	onModify();
	}

function onModify()
{//alert("modify");
       var count=0;
       var concatenateChkBoxVal="";
       var cbs =document.getElementsByName('chkResultValidationPatient');
     //  alert(cbs);
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
        	
        	 //var splitTheCheckBoxId=checkBoxId.substr(0,2);
       // 	 alert(splitTheCheckBoxId);
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
            
          //     alert("type is " + typ);
                 if(typ!=hidddentext)
                 {
                	 
            //    	 alert("not hidden");
                 splitTemplateValue=name.split("#");
                 reqNO.push(splitTemplateValue[0]);
                  parameterValue=splitTemplateValue[3];
              //    alert("parameter value    "+parameterValue);
                  parameterCode.push(parameterValue.substring(0, 5));
                  parantParameter.push(parameterValue.substring(9,18));
                
                    
              //  alert("parameterValue"+parameterValue+"reqno"+reqNO+"parameter"+parantParameter);
               
                   /*  if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value=="")
                           {
                                  alert("Enter the field Focussed");
                                  document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].focus();
                               return false;
                            } */
               
                       // document.getElementsByName('resultEntryTemplateValue').value.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);       
                  //  resultEntryTemplateValue.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);
                  var resultValidationTemplateValue="";
                  
                  if(id.indexOf("auto")!=-1)    
                	  {

                	
                      
                	 if( document.getElementById(hiddenid).value!=null && document.getElementById(hiddenid).value!="" )
                	  resultValidationTemplateValue=document.getElementById(hiddenid).value;
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
                		 resultValidationTemplateValue=document.getElementById(defaultid).value;
                	  }
            	   
                  else
                    resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value;
                        name+='#'+resultValidationTemplateValue+'#'+values;
                        
                     concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                     concatenateChkBoxVal+='@';
                     
                 }
                        
                        
                    }
        // alert(document.getElementById(i+'templateValue').getElementsByTagName("select").length);
       // alert("select value"+document.getElementById(i+'templateValue').getElementsByTagName("select")[0].value);
        var j=0;
         for(j;j<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select").length;j++)
              { 	 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
     		//alert("The value is"+values);
        	  get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select");
       	   name=get_tags[j].name;
        	 
        	// alert("inside here");
       // 	alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length);
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
	        	  { // alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value)
	        	   	  multiValue+=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value;
	        	      multiValue+="$";   	  
	        	  
	        	  }
	        	 resultEntryTemplateValue=multiValue
	        	  }
	          
	        else
	       	resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value;
		   
	        
	      //  alert(resultEntryTemplateValue);
	        name+='#'+resultEntryTemplateValue+'#'+values;
	          
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
              if(resultEntryTemplateValue.length>12000)
        	  {
        	  alert("Editor Data limit is 12000, including spaces. Please don't exceed the limit");
        	  return false;
        	  
        	  
        	  }
	          name+='#'+resultEntryTemplateValue+'#'+values;
          
 	          }

 	          else
 	        	  {
     	         var resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value;
		          name+='#'+resultEntryTemplateValue+'#'+values;
	          
 	        	  
 	        	  }
        /*        var resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value;
                        name+='#'+resultValidationTemplateValue+'#'+values;*/
                 
                     concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                     concatenateChkBoxVal+='@';

              
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
          	  }else if(document.getElementById(ide)!=null && document.getElementById(ide).innerHTML!='')
         	  {
             		//alert( document.getElementById(id).onclick);
             	  
               	  var idee=id.split("#")[0]+"$$";
               	   idee+=id.split("#")[3]+"$$";
           		  resultEntryTemplateValue="hyperchanks" ;
           		  
                    // alert("true1"+resultEntryTemplateValue);
               	  }
  				  
      	    
            }

          //  var uploadidcheck="view@@"+id;
            if(id.includes("view@@")==false) // not to add file upload view hyperlink option by chandan 
                {

            	var tocheckfnctn=document.getElementById(id).onclick;
        		var valuoffuncntn=tocheckfnctn;
        		valuoffuncntn=valuoffuncntn.toString();
        		//valuoffuncntn=valuoffuncntn.split("hyper");
              //        alert(""+valuoffuncntn);
                 if(valuoffuncntn.includes("echo"))
                  {
                    //   alert("match found");

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
         alert("please select a Atleast One record");
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
document.getElementById("patientwisename").style.display="none";	
/* document.getElementById("testwise").style.display="none";	
document.getElementById("labnowise").style.display="none";	
document.getElementById("labnowise2").style.display="none";*/	
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
		 // alert("s");
		document.getElementById("samplenowise").style.display="";	
		document.getElementById("patientwise").style.display="none";	
		document.getElementById("patientwisename").style.display="none";	
/* 	document.getElementById("testwise").style.display="none";	
	document.getElementById("labnowise").style.display="none";	
	document.getElementById("labnowise2").style.display="none"; */	
	
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
 
	
	var genTypeValue=document.getElementsByName('generationType')[0].value;
	//alert("onload"+genTypeValue); 
	 if(genTypeValue=='')
		 {
		 document.getElementById("showOnLoad").style.display="";	
		 document.getElementById("patient").checked = true;
		 
	//	 labBased();
		 }
	 
	 if(genTypeValue=="P")
		{
		 
document.getElementById("patientwise").style.display="";	
document.getElementById("patientwisename").style.display="none";	
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
	/* document.getElementById("testwise").style.display="none";	
	document.getElementById("labnowise").style.display="none";	
	document.getElementById("labnowise2").style.display="none";	
	 */
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
	//	alert("ajax data     "+ data);
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

function cancelFunc()
{
	window.parent.closeTab();
}


</script>
<script type="text/javascript">
        var isChecked = false;
        function allSelected(obj) 
        {
        	checkall(obj)
        	// this line is for toggle the check
            isChecked = !isChecked;
            //below line refers to 'jpCheckbox' class
            $('input:checkbox.jpCheckbox').prop('checked',isChecked);
			document.getElementById('selectAllCheckbox').checked=isChecked;            
            //OR,
            //$('input:checkbox.jpCheckbox').attr('checked','checked');
            if(isChecked)
            	{document.getElementsByName("diagnosisCheck")[0].value="1";}
            else
            {document.getElementsByName("diagnosisCheck")[0].value=null;}
            document.getElementById("nextDiv").style.display=""; 
        //	document.getElementById("nextDiv1").style.display=""; 
        }
        
        function allSelectedchkbox() 
        {
        	
        	// this line is for toggle the check
            isChecked = !isChecked;
            //below line refers to 'jpCheckbox' class
            $('input:checkbox.jpCheckbox').prop('checked',isChecked);
            //OR,
            //$('input:checkbox.jpCheckbox').attr('checked','checked');
    /*         if(isChecked)
            	{document.getElementsByName("diagnosisCheck")[0].value="1";}
            else
            {document.getElementsByName("diagnosisCheck")[0].value=null;}
            document.getElementById("nextDiv").style.display="";  */
        //	document.getElementById("nextDiv1").style.display=""; 
        }
        

 


        // added by chandan on 9 feb
       
       function checkall(obj)
       {
           //alert(obj.name);
    	 	if(obj.checked)
        	 	{

        	 	if(obj.name=="newtest")
            	 	{
              document.getElementsByName("demographic")[0].checked=false;
              document.getElementsByName("amendment")[0].checked=false;
              document.getElementsByName("addendum")[0].checked=false;
            	 	}

        	 	if(obj.name=="demographic")
        	 	{
          document.getElementsByName("newtest")[0].checked=false;
          document.getElementsByName("amendment")[0].checked=false;
          document.getElementsByName("addendum")[0].checked=false;
        	 	}

        	 	if(obj.name=="amendment")
        	 	{
          document.getElementsByName("demographic")[0].checked=false;
          document.getElementsByName("newtest")[0].checked=false;
          document.getElementsByName("addendum")[0].checked=false;
        	 	}


        	 	if(obj.name=="addendum")
        	 	{
          document.getElementsByName("demographic")[0].checked=false;
          document.getElementsByName("amendment")[0].checked=false;
          document.getElementsByName("newtest")[0].checked=false;
        	 	}
        	 	
          	 	}
        	
         }
       
        function setNewTest(obj)
        {
            checkall(obj);
        	if(obj.checked)
        		document.getElementsByName("newtest")[0].value="1";
        	else
        		document.getElementsByName("newtest")[0].value="";
        	
        	document.getElementById('nextDivfortest').style.display="";
        	document.getElementById('nextDiv').style.display="none";
        }
        
        function setAddendumCheck(obj)
        {
        	checkall(obj);
        	if(obj.checked)
        		document.getElementsByName("addendumCheck")[0].value="1";
        	else
        		document.getElementsByName("addendumCheck")[0].value="";
        	
        	document.getElementById('nextDiv').style.display="";
        	document.getElementById('nextDivfortest').style.display="none";
        }
        
        function setAmendmentCheck(obj)
        {
        	checkall(obj);
        	if(obj.checked)
        		document.getElementsByName("amendmentCheck")[0].value="1";
        	else
        		document.getElementsByName("amendmentCheck")[0].value="";
        	
        	document.getElementById('nextDiv').style.display="";
        	document.getElementById('nextDivfortest').style.display="none";
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
            //	alert(cannedDetails.length);
            	
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



function printReport(name)
{
	
	//alert(name);
	document.getElementsByName('fileName')[0].value=name;
//document.getElementsByName('hmode')[0].value='PRINTREPORT';
	//document.forms[0].action="/AHIMS/investigationDesk/viewInvestigation.cnt"; 

//	document.forms[0].submit();
	
	
	var mode='PRINTREPORT';
var reportName = name;


var url = '/HISInvestigationG5/new_investigation/invResultReportPrinting.cnt?hmode='+mode+"&fileName="+name;

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



function call()
	{
               //alert("call");
               
               
                var patTestType = document.getElementsByName("labCode")[0].value.split("#")[1];
//	 alert("patTestType"+patTestType);
	 document.getElementsByName("patTestType")[0].value = patTestType;

	 
		var cbs =document.getElementsByName('chkResultValidationPatient');

	    for(var i=0; i < cbs.length; i++)
       {
               
                   // alert("ins");
                    var dd=document.getElementsByTagName("select");
                    for(var j=0;j<dd.length;j++)
                        {
                        document.getElementsByName('iscallfromonloaddynamic')[0].value="1";
                       
	              	  var name=dd[j].name;
	                  //  alert("name");
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

.ui-autocomplete {
	max-height: 100px;
	overflow-y: auto;
	/* prevent horizontal scrollbar */
	overflow-x: hidden;
}
</style>
<body onload="call();">
	<html:form action="/invReportAddendum">
		<html:hidden name="invReportAddendumFB" property="hmode" />
		
				<html:hidden name="invReportAddendumFB" property="echovar" />
		
		  <html:hidden name="InvResultValidationFB" property="iscallfromonloaddynamic" value="2" />
		  		<input type="hidden" name="selectValuemapping" value=""/>
		 <input type="hidden" name="hyperLinkTableSession" value=""/>
 
 
<html:hidden name="invReportAddendumFB" property="fileuploaddata" />
						<html:hidden name="invReportAddendumFB" property="fileuploaddatabase64" />
		
		
		
		<html:hidden name="invReportAddendumFB" property="isPatDetailPage" />
		<html:hidden name="invReportAddendumFB" property="selectedCheckbox" />
		<html:hidden name="invReportAddendumFB" property="showStatus" />
		<html:hidden name="invReportAddendumFB" property="showStatusLegends" />
		<html:hidden name="invReportAddendumFB" property="currentPage" />
		<html:hidden name="invReportAddendumFB" property="patCRNo" />
		<html:hidden name="invReportAddendumFB" property="sysDate" />

		<html:hidden name="invReportAddendumFB" property="getSearchType" />
		<html:hidden name="invReportAddendumFB" property="generationType" />
		<html:hidden name="invReportAddendumFB" property="onLoadValue" />
		<html:hidden name="invReportAddendumFB" property="diagnosisCheck" />
		<html:hidden name="invReportAddendumFB" property="addendumCheck" />
		<html:hidden name="invReportAddendumFB" property="amendmentCheck" />

		<%!
		boolean readOnly;
	%>
		<% this.readOnly=false;%>
		<logic:equal name="invReportAddendumFB" property="hmode"
			value="VIEW">
			<% this.readOnly=true; %>
		</logic:equal>
		<his:TitleTag name="Result Report Addendum/Amendment Process">
			<%-- <his:insertDateTag/> --%>
		</his:TitleTag>
		<his:ContentTag>
			<%
			  String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
         %>
			<bean:define name="invReportAddendumFB" property="fromDate"
				id="frDate" type="java.lang.String" />
			<bean:define name="invReportAddendumFB" property="toDate"
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
			<logic:equal name="invReportAddendumFB" property="showStatus"
				value="0">
				<his:SubTitleTag name="Details"></
  			</his:SubTitleTag>
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
											name="invReportAddendumFB" property="labCode" tabindex="1"
											onchange="labBased()">
											
												<bean:define id="patSampleCollection" type="java.util.List"
												name="<%=InvestigationConfig.LAB_COMBO_FOR_RESULT_ENTRY%>"></bean:define>
												
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
											
											<%
												}
												if(patTypeCount == patSampleCollection.size()){
													System.out.println("all");	
											%>
												<html:option value="%#2">All</html:option>
											<% 
												}
												else{
											%>
											<html:option value="%">All</html:option>
											<%
												}
											%>
											
										
											<%-- <html:option value="%">All</html:option> --%>
											<html:options
												collection="<%=InvestigationConfig.LAB_COMBO_FOR_RESULT_ENTRY%>"
												property="value" labelProperty="label" />
										</html:select>
									</span>
								</div>
							</logic:present></td>
						
	<logic:notEmpty
						name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">

	<td colspan="2" class="tdfont">
	<div align="center">
	<input Type="checkbox" name="demographic" onclick="allSelected(this);"/>Demographic
	<input Type="checkbox" name="amendment" onclick="setAmendmentCheck(this);"/>Amendment
	<input Type="checkbox" name="addendum" onclick="setAddendumCheck(this);"/>Addendum
	<input Type="checkbox" name="newtest" onclick="setNewTest(this);"/>New Test Raised  <!-- added by chandan on 9-feb -->
    </div>
	</td>
	</logic:notEmpty>
					</tr>
					<tr>
						<td class="tdfont" width="25%">
							<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
								<font color="#FF0000" size="1"
									face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="fromDate" />&nbsp;
								</font>
							</div>
						</td>
						<td class="tdfont" width="25%">
							<div id='divfromDateControl' style='<%=fromDateControl %>'
								align="left">
								&nbsp;&nbsp;&nbsp;
								<his:date name='fromDate' dateFormate="%d-%b-%Y"
									value="<%=frDate%>" />
							</div>
						</td>
						<td class="tdfont" width="25%">
							<div id='divfromDate' style='<%=toDateLabel %>' align="right">
								<font color="#FF0000" size="1"
									face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="toDate" />&nbsp;
								</font>
							</div>
						</td>
						<td class="tdfont" width="25%">
							<div id='divfromDateControl' style='<%=toDateControl %>'
								align="left">
								&nbsp;&nbsp;&nbsp;
								<his:date name='toDate' dateFormate="%d-%b-%Y"
									value="<%=tDate%>" />
							</div>
						</td>
					</tr>
			
			<tr>
						<!-- <td width="25%" class="tdfont">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Addendum/Amendment Type &nbsp;
								</font>
							</div>
						</td> -->
						 <%-- <td width="25%" class="tdfont" colspan="3">
							<div align="left">
								&nbsp;&nbsp;&nbsp;
								<logic:equal name="invReportAddendumFB"
									property="generationType" value="P">

									<input type="radio" name="patientWise"
										onclick="getDetails(this)" checked="checked" value="P" />
								</logic:equal>
								<logic:notEqual name="invReportAddendumFB"
									property="generationType" value="P">
									<input type="radio" name="patientWise" id="patient"
										onclick="getDetails(this)" value="P"  />
								</logic:notEqual>
								<bean:message key="PatientWise" />

								&nbsp;&nbsp;&nbsp;
								<logic:equal name="invReportAddendumFB"
									property="generationType" value="T">
									<input type="radio" name="testWise" onclick="getDetails(this)"
										checked="checked" value="T" />
								</logic:equal>
								<logic:notEqual name="invReportAddendumFB"
									property="generationType" value="T">
									<input type="radio" name="testWise" onclick="getDetails(this)"
										value="T" />
								</logic:notEqual>

								<bean:message key="testWise" />


								&nbsp;&nbsp;&nbsp;
								<logic:equal name="invReportAddendumFB"
									property="generationType" value="S">
									<input type="radio" name="sampleNoWise"
										onclick="getDetails(this)" checked="checked" value="S" />
								</logic:equal>
								<logic:notEqual name="invReportAddendumFB"
									property="generationType" value="S">
									<input type="radio" name="sampleNoWise"
										onclick="getDetails(this)" value="S" />
								</logic:notEqual>


								<bean:message key="sampleNoWise" />


								&nbsp;&nbsp;&nbsp; --%>
								<%-- <logic:equal name="invReportAddendumFB"
									property="generationType" value="L">
									<input type="radio" name="labNoWise" onclick="getDetails(this)"
										checked="checked" value="L" />
								</logic:equal>
								<logic:notEqual name="invReportAddendumFB"
									property="generationType" value="L">
									<input type="radio" name="labNoWise" onclick="getDetails(this)"
										value="L" />
								</logic:notEqual>


								<bean:message key="labNOWise" />

								&nbsp;&nbsp;&nbsp;
								<logic:equal name="invReportAddendumFB"
									property="generationType" value="TG">
									<input type="radio" name="testGroupWise"
										onclick="getDetails(this)" checked="checked" value="TG" />
								</logic:equal>
								<logic:notEqual name="invReportAddendumFB"
									property="generationType" value="TG">
									<input type="radio" name="testGroupWise"
										onclick="getDetails(this)" value="TG" />
								</logic:notEqual>


								<bean:message key="testGrpWise" />

								&nbsp;&nbsp;&nbsp;
								<logic:equal name="invReportAddendumFB"
									property="generationType" value="AP">
									<input type="radio" name="allPatient"
										onclick="getDetails(this)" checked="checked" value="AP" />
								</logic:equal>
								<logic:notEqual name="invReportAddendumFB"
									property="generationType" value="AP">
									<input type="radio" name="allPatient"
										onclick="getDetails(this)" value="AP" />
								</logic:notEqual>


								<bean:message key="allPatient" /> --%>
							</div>

						</td>

					</tr>
					<tr>
						<td width="25%" class="tdfont">
							<div align="right" id="showOnLoad" style="display:">

								<bean:message key="crNO" />
								&nbsp;
							</div>
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <logic:equal
										name="invReportAddendumFB" property="generationType"
										value="P">

										<bean:message key="crNO" />&nbsp;
								</logic:equal> <logic:equal name="invReportAddendumFB"
										property="generationType" value="T">
										<font color="RED" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> * </font>
										<bean:message key="testName" />&nbsp;
								</logic:equal> 
								
								
								<logic:equal name="invReportAddendumFB"
										property="generationType" value="L">
										<font color="RED" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> * </font>
										<bean:message key="fromLabNo" />&nbsp;
								</logic:equal> 
								<logic:equal name="invReportAddendumFB"
										property="generationType" value="S">
										<font color="RED" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> * </font>
										<bean:message key="fromSampleNo" />&nbsp;
								</logic:equal> 
								
								<logic:equal name="invReportAddendumFB"
										property="generationType" value="TG">
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
								&nbsp;&nbsp;&nbsp; <html:text styleId="textBoxCss"
									name="invReportAddendumFB"  maxlength="15" size="20" property="tempPatCRNo"
									onkeypress="return validateNumeric(event,this)" tabindex="1"/>
							</div> <logic:present
								name="<%=InvestigationConfig.TEST_WISE_COMBO_FOR_RESULT_ENTRY%>">
								<div align="left" id="testwise" style="display: none;">
									&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
											name="invReportAddendumFB" property="testWiseCode"
											tabindex="1">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.TEST_WISE_COMBO_FOR_RESULT_ENTRY%>"
												property="value" labelProperty="label" />
										</html:select>
									</span>
								</div>
							</logic:present> 
							 <logic:present
								name="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>">
								<div align="left" id="labnowise" style="display: none;">
									&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
											name="invReportAddendumFB" property="fromLabNo"
											tabindex="1">
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
											name="invReportAddendumFB" property="testGroupCodeWise"
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
						
						
					<div align="left" id="patientwisename" style="display:none" >                                                                                                           
					 Search Name&nbsp;&nbsp;&nbsp;<input type="text" id="textBoxCss" name="tempPatName"  maxlength="20" size="20"  onkeypress="return validateAlphabetsOnly(event,this)" tabindex="1">
				   </div>  
				   
				   
							<div align="right" id="toLabSampleNo" style="display: none;">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <logic:equal
										name="invReportAddendumFB" property="generationType"
										value="L">
										<font color="RED" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> * </font>
										<bean:message key="toLabNo" />&nbsp;
								</logic:equal> <logic:equal name="invReportAddendumFB"
										property="generationType" value="S">
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
											name="invReportAddendumFB" property="toLabNo" tabindex="1">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>"
												property="value" labelProperty="label" />
										</html:select>
									</span>
								</div>
							</logic:present> </td>
					</tr>
					
					
					
			<tr>
			
			
			<tr>
					
					<logic:present
								name="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>">
								
			<tr>
			<!-- updated by krishnan nema on 03/10/2018 -->
		<%-- 	<td class="tdfont" width="25%">
							<div  style='<%=toDateLabel %>' align="right">
								<font color="#FF0000" size="1"
									face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">From Sample No&nbsp;
								</font>
							</div>
						</td> --%>
			 <logic:notEqual name="invReportAddendumFB" property="patTestType" value="2">
			<td class="tdfont" width="25%">
							<div  style='<%=toDateLabel %>' align="right">
								<font color="#FF0000" size="1"
									face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">From Sample No&nbsp;
								</font>
							</div>
						</td>
			</logic:notEqual>
			
			 <logic:equal name="invReportAddendumFB" property="patTestType" value="2">
			<td class="tdfont" width="25%">
							<div  style='<%=toDateLabel %>' align="right">
								<font color="#FF0000" size="1"
									face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">From Acc. No&nbsp;
								</font>
							</div>
						</td>
			</logic:equal>

		<td class="tdfont" width="25%">
		
		<div align="left" >
									&nbsp;&nbsp; <span class="custom-dropdown small">
									<html:select name="invReportAddendumFB" property="fromSampleNo"    tabindex="1"  >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
										
										</span>
										</div>
		</td>
			<!-- updated by krishnan nema on 01/10/2018 -->
			<%-- <td class="tdfont" width="25%">
							<div  style='<%=toDateLabel %>' align="right">
								<font color="#FF0000" size="1"
									face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">To Sample No&nbsp;
								</font>
							</div>
						</td> --%>
			 <logic:notEqual name="invReportAddendumFB" property="patTestType" value="2">
		<td class="tdfont" width="25%">
							<div  style='<%=toDateLabel %>' align="right">
								<font color="#FF0000" size="1"
									face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">To Sample No&nbsp;
								</font>
							</div>
						</td>
						</logic:notEqual>
						
						<logic:equal name="invReportAddendumFB" property="patTestType" value="2">
						<td class="tdfont" width="25%">
							<div  style='<%=toDateLabel %>' align="right">
								<font color="#FF0000" size="1"
									face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">To Acc. No&nbsp;
								</font>
							</div>
						</td>
						</logic:equal>
						
		<td class="tdfont" width="25%">
		
		<div align="left" >
									&nbsp;&nbsp; <span class="custom-dropdown small"> 
										 <html:select name="invReportAddendumFB" property="toSampleNo"    tabindex="1"  >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
										
										</span>
										</div>
		</td>
		
			
			</tr>
			
			</logic:present>
					
					<tr>
						<th class="tdfont" align="left" colspan="4">
							<div align="center">
								<img class="button"
									src='<his:path src="/hisglobal/images/GO.png"/>' id="gob"
									style="cursor: pointer"
									onkeypress="if(event.keyCode==13) onClickGO('<%=hospitalCode%>')"
									onclick="onClickGO('<%=hospitalCode%>')" tabindex="1"> <img
									class="button"
									src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"
									style="cursor: pointer"
									onkeypress="if(event.keyCode==13) cancelFunc()"
									onclick="cancelFunc()" tabindex="1">

							</div>
						</th>
					</tr>
					
				</table>
				
			
			
					
				
			</logic:equal>
			
		</his:ContentTag>
		
			
			<%boolean flag=false; %>
			<%
				//Pagination Logic
					PaginationFB fbPage= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage);
					fbPage.setCurrentPage(((invReportAddendumFB)request.getAttribute("invReportAddendumFB")).getCurrentPage());
					fbPage.setObjArrName(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
					fbPage.setAppendInTitle("List ");
					int maxRecord=15;
					fbPage.setMaxRecords(maxRecord);
				 
				 %>

			<logic:equal name="invReportAddendumFB" property="showStatus"
				value="0">
				<his:PaginationTag name="fbPagination"></his:PaginationTag>
				<logic:present
					name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">
					<% flag=true; %>
					<table width="100%" bgcolor="#EBEBEB">
						<tr>
							<td width="3%" align="left"><b> <font color="#000000"
									size="2" face="Verdana, Arial, Helvetica, sans-serif"> <input
										type="checkbox" id="selectAllCheckbox" onclick="allSelectedchkbox()" />
								</font>
							</b></td>
							<td width="10%" align="left"><b><font color="#000000"
									size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="crNO" />
								</font></b></td>
							<td width="12%" align="left"><b><font color="#000000"
									size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="patientName" /></font></b></td>
							<td width="8%" align="left"><b> <font color="#000000"
									size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="age/gender" /></font></b></td>
							<td width="15%" align="left"><b><font color="#000000"
									size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="departmentunit" /></font></b></td>
							<td width="10%" align="left"><b> <font color="#000000"
									size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="TestName" /></font></b></td>
							<td width="10%" align="left"><b><font color="#000000"
									size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="labName" /></font></b></td>
							
							<!-- updated by krishnan nema on  01/10/2018 -->
							
							<%-- <td width="9%" align="left"><b> <font color="#000000"
									size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="sampLabNo" /></font></b></td>
							 --%>
							 
							 <logic:notEqual name="invReportAddendumFB" property="patTestType" value="2">
							 <td width="9%" align="left"><b> <font color="#000000"
									size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="sampLabNo" /></font></b></td>
							 
							 </logic:notEqual>
							 
							 <logic:equal name="invReportAddendumFB" property="patTestType" value="2">
							 <td width="9%" align="left"><b> <font color="#000000"
									size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="accessionNowise" /></font></b></td>
							</logic:equal>
							
							<td width="9%" align="left"><b> <font color="#000000"
									size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="patStatus" /></font></b></td>
							<td width="7%" align="left"><b> <font color="#000000"
									size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="viewReport" /></font></b></td>
							<td width="2%">
								<% List<ResultEntryVO> lstPatVO=(List<ResultEntryVO>)session.getAttribute(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
					 int i,size=0,total;
			 		if(lstPatVO!=null && lstPatVO.size()>0 )
			 			size=lstPatVO.size();
					 %> <img class="button" title="Show All Test Details"
								src='<his:path src="/hisglobal/images/plusinv.png"/>'
								id="showAllTest" tabindex="1"
								onclick="displayAllTest('<%=size%>')"> <img class="button"
								title="Hide All Test Details"
								src='<his:path src="/hisglobal/images/Minus.png"/>'
								id="hideAllTest" style="display: none;" tabindex="1"
								onclick="hideAllTests('<%=size %>')">


							</td>
						</tr>
					</table>
					<logic:empty
						name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">
						<center>
							<font color="red" size="4"> <bean:message key="noRecord" /></font>
						</center>
					</logic:empty>


					<bean:define name="invReportAddendumFB" property="startDisplay"
						id="startDisplay" type="java.lang.Integer" />
					<bean:define name="invReportAddendumFB" property="hideDisplay"
						id="hideDisplay" type="java.lang.Integer" />

					<logic:notEmpty
						name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">
						<table width="100%">

							<%
					
					int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
					int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();

					startDisplay=1;
					hideDisplay=1;
					String grpCode="";
					
					for(int j=startIndex;j<=endIndex;j++)
					{int l=j;
					boolean firstTimeTravesall=true;
					
						
					if(j<size)
									{
						ResultEntryVO voPat=lstPatVO.get(j);
					String chkVal=voPat.getPatCRNo()+"#"+voPat.getRequisitionNo()+"#"+voPat.getRequisitionDNo()+"#"+voPat.getGroupCode()+"#"+voPat.getTestCode()+"#"+voPat.getLabCode()+"#";  
					
					 
					 String value=voPat.getRequisitionNo()+voPat.getGroupCode();
					
					 
					 
					
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
						
						 
					%>
							<tr>
								<td width="3%" align="left"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <input
										type="checkbox" class="jpCheckbox" name="chkSamplePatient"
										value='<%=chkVal%>' onclick="ValidateSameCrNo(this)">
								</font></td>
								<td width="12%" align="left">
									<div>
										<font color="<%=color%>" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatCRNo() %></font>
									</div>
								</td>

								<td width="13%" align="left">

									<div>
										<font color="<%=color%>" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatName() %></font>
									</div>

								</td>
								<td width="10%" align="left">


									<div>
										<font color="<%=color%>" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatAge()+"/"+voPat.getPatGender()%></font>
									</div>
								</td>
								<td width="15%" align="left">
									<div>
										<font color="<%=color%>" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatUnitName() %></font>
									</div>
								</td>
								<td width="12%" align="left">

									<div>
										<font color="<%=color%>" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getGroupName().equals("NA")?voPat.getTestName():voPat.getGroupName() %></font></font>
									</div>
								</td>
								<td width="10%" align="left">
									<div>
										<font color="<%=color%>" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatLabName() %></font>
									</div>
								</td>
										<td width="9%" align="left">
									<div s>
										<font color="<%=color%>" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getTempSampleNo()==null?voPat.getLabNo():voPat.getTempSampleNo() %></font>
									</div>
								</td>
								
								<td width="9%" align="left">
									<div s>
										<font color="<%=color%>" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatStatus() %></font>
									</div>
								</td>
								
								
									<td width="7%" align="left">
									<div s>
										<font color="<%=color%>" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> 	<a onclick="printReport('<%=voPat.getReportUrl()%>')" >View Report</a></font>
									</div>
								</td>

								<td width="2%">
									<%  String showTest="showTestDetails("+j+",'"+value+"')";
						 String hideTest="hideTestDetails("+j+",'"+value+"')";
			
			 %> <img class="button" title="Show Test Details"
									src='<his:path src="/hisglobal/images/plusinv.png"/>'
									id="showTest<%=j%>" tabindex="1" onclick="<%=showTest%>">
									<img class="button" title="Hide Test Details"
									src='<his:path src="/hisglobal/images/Minus.png"/>'
									id="hideTest<%=j%>" style="display: none;" tabindex="1"
									onclick="<%=hideTest %>">






								</td>



							</tr>
							<%} %>
							
							
							
							
							<tr>
								<th colspan="1"></th>
								<th colspan="7">
									<table bgcolor="#F8F8F8" style="display: none;" width="100%"
										id="headings<%=j%>" border="1">
										<tr>

											<th width="20%"><font color="red" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <u><bean:message
															key="TestParaCombo" /></u></font></th>
											<th width="20%"><font color="red" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"><u><bean:message
															key="TestParaValue" /></u></font></th>

											<th width="20%"><font color="red" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"><u><bean:message
															key="refRange" /></u></font></th>
											<%-- <th width="20%"><font color="red" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><u><bean:message key="uom"/></u></font></th> --%>

										</tr>




										<!-- to display test and its value -->

										<%
						 
					String[] parameters=voPat.getTestParameterName().split("`");
					int paraSize=parameters.length;
					
						 
					for(int iterate=0;iterate<paraSize;iterate++)
					{
						String[] paraValues=parameters[iterate].split("#@");
						String paraName=paraValues[1];
						String refRange=paraValues[2];
						String paracode=paraValues[0];

						String displayRef="";
						
						 if(refRange!=null||!refRange.equals(""))
						{
						refRange=refRange.replace("$", "@");
						String[] refValues=refRange.split("@");
						 if(refValues.length>1)
						 {
							 String checkRangetyp=refValues[0];
							 if(checkRangetyp.equals("1"))
							 {
								 String highValue=refValues[1];
									
									String lowValue=refValues[2];
									String highValueUom=refValues[3];
									String lowValueUom=refValues[4];
									 displayRef=lowValue+" "+lowValueUom+" - "+highValue+" "+highValueUom;
							 }
							 else if(checkRangetyp.equals("2"))
							 {
								 
								 String rangetyp=">";
									
									String tovalue=refValues[2];
									String tovalueunit=refValues[1];
									
									 displayRef=rangetyp+" "+tovalue+"  "+tovalueunit;
							 } 
								 
							 else if(checkRangetyp.equals("3"))
							 {
								 String rangetyp=">=";
									
									String tovalue=refValues[2];
									String tovalueunit=refValues[1];
									
									 displayRef=rangetyp+" "+tovalue+"  "+tovalueunit;
							 }
							 else if(checkRangetyp.equals("4"))
							 {
								 String rangetyp="<";
									
									String tovalue=refValues[2];
									String tovalueunit=refValues[1];
									
									 displayRef=rangetyp+" "+tovalue+"  "+tovalueunit;
							 }
							 else if(checkRangetyp.equals("5"))
							 {
								 String rangetyp="<=";
									
									String tovalue=refValues[2];
									String tovalueunit=refValues[1];
									
									 displayRef=rangetyp+" "+tovalue+"  "+tovalueunit;
							 }
						 }
						}
						else  
							displayRef="";
						
						
						

							String paraEntry=paraValues[3];
							
							if(paraValues[3].equals("--")  )
							{
								 paraEntry="";
							}
							else
							{
								 
							}
							
						if(!(paraEntry.indexOf("<")!=-1))
						paraEntry=paraEntry.replace("\r\n","<br>");
						if(paraEntry.indexOf("$")!=-1)
							paraEntry=paraEntry.replace("$","<br>");
					
						
					%>

										<tr>

											<th width="20%"><font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <%=paraName%></font>

											</th>

											<th width="20%"><font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <%=paraEntry%></font>

										<%if(paraEntry.equals("File Uploaded")){ 
											 String valu1 = voPat.getRequisitionDNo() + "#" + "null" + "#template#" + voPat.getTestCode() +paracode;

										%>
									<a 
		        
		                         id="view<%=valu1 %>" class="view@@<%=valu1 %>" href="#" onclick="myuploadFunction(this)">
		                               View File</a>
									<%} %>	
											</th>
											<th width="20%"><%=displayRef%></th>
											<!-- 	<th width="20%">ml</th> -->
										</tr>


										<%}  %>
									</table>
								</th>
							</tr>
							<!--end to display test and its value  -->


							<%}  }%>
							<input type="hidden" name="chkValue" value="<%=grpCode%>" />
						</table>
						
						
						
					</logic:notEmpty>
				</logic:present>
			</logic:equal>
			
		<logic:present
				name="<%=InvestigationConfig.NEW_ENTRIES_PATIENT_VO %>">
				
				<%
				ResultEntryVO newPatientDetails=(ResultEntryVO)session.getAttribute(InvestigationConfig.NEW_ENTRIES_PATIENT_VO);
				ResultEntryVO oldPatientDetails=(ResultEntryVO)session.getAttribute(InvestigationConfig.OLD_ENTRIES_PATIENT_VO);
				
				%>
			
				<his:SubTitleTag name="" ><div align="left">New Patient Details</div></his:SubTitleTag>
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
									face="Verdana, Arial, Helvetica, sans-serif"> <%=newPatientDetails.getPatCRNo() %>
									<%System.out.println("newpat:"+newPatientDetails.getPatCRNo()); %>
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
							<%  
							 String color="";
							if(((newPatientDetails.getPatFirstName())+(newPatientDetails.getPatMiddleName())+(newPatientDetails.getPatLastName())).equals(oldPatientDetails.getPatName()))
							{
								color="black";
							}
							else
								color="red";
							System.out.println("newpat:"+newPatientDetails.getPatCRNo()); 
								%>
								<font color="<%=color%>" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=(newPatientDetails.getPatFirstName()==null?"":newPatientDetails.getPatFirstName())+" "+(newPatientDetails.getPatMiddleName()==null?"":newPatientDetails.getPatMiddleName())+" "+(newPatientDetails.getPatLastName()==null?"":newPatientDetails.getPatLastName()) %>
								</font>
							</div>
						</td>
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
									face="Verdana, Arial, Helvetica, sans-serif"> <%=newPatientDetails.getPatAge()+"/"+newPatientDetails.getPatGenderCode() %>
								</font>
							</div>

					
					</tr>
					</table>
					<br>
				
				
				
				
				
				
		
				<his:SubTitleTag name="" ><div align="left">Old Patient Details</div></his:SubTitleTag>
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
									face="Verdana, Arial, Helvetica, sans-serif"> <%=oldPatientDetails.getPatCRNo() %>
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
									face="Verdana, Arial, Helvetica, sans-serif"> <%=oldPatientDetails.getPatName() %>
								</font>
							</div>
						</td>
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
									face="Verdana, Arial, Helvetica, sans-serif"> <%=oldPatientDetails.getPatAge()+"/"+oldPatientDetails.getPatGender() %>
								</font>
							</div>

					
					</tr>
					</table>
					<br>
				
				</logic:present>
				
				
		<logic:equal name="invReportAddendumFB" property="showStatus" value="1">
		<logic:equal name="invReportAddendumFB" property="diagnosisCheck" value="1">				
		<logic:notPresent
				name="<%=InvestigationConfig.NEW_ENTRIES_PATIENT_VO %>">
				<b>No Changes Found in Demographics<b>
				</logic:notPresent>
			</logic:equal>	
			</logic:equal>

			<logic:present
				name="<%=InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO %>">
				<%
			Map<String,List<ResultEntryVO>> _mpp=(Map<String,List<ResultEntryVO>>)session.getAttribute(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO);
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
				<input type="hidden" id='<%=hid %>' name="userCannedCode" />
				<%
			
		}}
		
		
			 
				Iterator itrr=_mpp.keySet().iterator();
			int  size=0;
					int i=0;
				 
				%>
				
				
			
				
				<%	
					
					
					
					
					
					
					
				while(itrr.hasNext())
				{
			boolean firstTimeTravesa=true;
			boolean firstTimeRemark=true;

					 
				String sampleNo=(String)itrr.next();
				 
				
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
				 					 String tempChkTestValue = tempInvRVO.getPatCRNo()+"#"+tempInvRVO.getRequisitionNo()+"#"+tempInvRVO.getRequisitionDNo()+"#"+tempInvRVO.getTestCode()+"#"+tempInvRVO.getSampleNo()+"#"+tempInvRVO.getLabCode()+"#"+tempInvRVO.getPatAge()+"#"+tempInvRVO.getPatGender()+"#"+tempInvRVO.getReportAvailableAfter()+"#"+tempInvRVO.getPatVisitDat()+"#"+tempInvRVO.getPatVisitNo()+"#"+tempInvRVO.getLabNo()+"#"+tempInvRVO.getEpisodeCode()+"#"+tempInvRVO.getDepartmentcode()+"#"+tempInvRVO.getPatdeptunitcode()+"#"+tempInvRVO.getRequisitionTypeCode()+"#"+tempInvRVO.getUomCode()+"#"+tempInvRVO.getTestName()+"#"+tempInvRVO.getPatLabName()+"#"+tempInvRVO.getSampleName()+"#"+tempInvRVO.getTempSampleNo()+"#"+tempInvRVO.getGroupCode()+"#"+tempInvRVO.getDynamicTemplatePrintedGroup();
				 					 String oldChkTestValue = reqNoGroupCodeChkTestValueMap.get(key);
				 					 oldChkTestValue += "!" + tempChkTestValue;
				 					reqNoGroupCodeChkTestValueMap.put(key, oldChkTestValue);
				 				 }
				 				 else
				 				 {
				 					String tempChkTestValue = tempInvRVO.getPatCRNo()+"#"+tempInvRVO.getRequisitionNo()+"#"+tempInvRVO.getRequisitionDNo()+"#"+tempInvRVO.getTestCode()+"#"+tempInvRVO.getSampleNo()+"#"+tempInvRVO.getLabCode()+"#"+tempInvRVO.getPatAge()+"#"+tempInvRVO.getPatGender()+"#"+tempInvRVO.getReportAvailableAfter()+"#"+tempInvRVO.getPatVisitDat()+"#"+tempInvRVO.getPatVisitNo()+"#"+tempInvRVO.getLabNo()+"#"+tempInvRVO.getEpisodeCode()+"#"+tempInvRVO.getDepartmentcode()+"#"+tempInvRVO.getPatdeptunitcode()+"#"+tempInvRVO.getRequisitionTypeCode()+"#"+tempInvRVO.getUomCode()+"#"+tempInvRVO.getTestName()+"#"+tempInvRVO.getPatLabName()+"#"+tempInvRVO.getSampleName()+"#"+tempInvRVO.getTempSampleNo()+"#"+tempInvRVO.getGroupCode()+"#"+tempInvRVO.getDynamicTemplatePrintedGroup();
				 					reqNoGroupCodeChkTestValueMap.put(key, tempChkTestValue);
				 				 }
				 			 }
				 			 chkTestValue = reqNoGroupCodeChkTestValueMap.get(invresultentryvo.getRequisitionNo() +invresultentryvo.getGroupCode() );
			 			 }
			 			 else {
			 				 chkTestValue=invresultentryvo.getPatCRNo()+"#"+invresultentryvo.getRequisitionNo()+"#"+invresultentryvo.getRequisitionDNo()+"#"+invresultentryvo.getTestCode()+"#"+invresultentryvo.getSampleNo()+"#"+invresultentryvo.getLabCode()+"#"+invresultentryvo.getPatAge()+"#"+invresultentryvo.getPatGender()+"#"+invresultentryvo.getReportAvailableAfter()+"#"+invresultentryvo.getPatVisitDat()+"#"+invresultentryvo.getPatVisitNo()+"#"+invresultentryvo.getLabNo()+"#"+invresultentryvo.getEpisodeCode()+"#"+invresultentryvo.getDepartmentcode()+"#"+invresultentryvo.getPatdeptunitcode()+"#"+invresultentryvo.getRequisitionTypeCode()+"#"+invresultentryvo.getUomCode()+"#"+invresultentryvo.getTestName()+"#"+invresultentryvo.getPatLabName()+"#"+invresultentryvo.getSampleName()+"#"+invresultentryvo.getTempSampleNo()+"#"+invresultentryvo.getGroupCode()+"#"+invresultentryvo.getDynamicTemplatePrintedGroup()+"#"+invresultentryvo.getPatName()+"#"+invresultentryvo.getRefRange() +"#"+invresultentryvo.getDetpUnitCode()+"#"+invresultentryvo.getPatUnitName()+"#"+invresultentryvo.getReportUrl();
				 			 }
			 			 
			 			 
			 			 //String chkTestValue=invresultentryvo.getPatCRNo()+"#"+invresultentryvo.getRequisitionNo()+"#"+invresultentryvo.getRequisitionDNo()+"#"+invresultentryvo.getTestCode()+"#"+invresultentryvo.getSampleNo()+"#"+invresultentryvo.getLabCode()+"#"+invresultentryvo.getPatAge()+"#"+invresultentryvo.getPatGender()+"#"+invresultentryvo.getReportAvailableAfter()+"#"+invresultentryvo.getPatVisitDat()+"#"+invresultentryvo.getPatVisitNo()+"#"+invresultentryvo.getLabNo()+"#"+invresultentryvo.getEpisodeCode()+"#"+invresultentryvo.getDepartmentcode()+"#"+invresultentryvo.getPatdeptunitcode()+"#"+invresultentryvo.getRequisitionTypeCode()+"#"+invresultentryvo.getUomCode()+"#"+invresultentryvo.getTestName()+"#"+invresultentryvo.getPatLabName()+"#"+invresultentryvo.getSampleName()+"#"+invresultentryvo.getTempSampleNo();                                                                                    
			 			String labCode=invresultentryvo.getLabCode();
			 			String reqDno=invresultentryvo.getRequisitionDNo();
			 			//String chkTestValue=invresultentryvo.getPatCRNo()+"#"+invresultentryvo.getRequisitionNo()+"#"+invresultentryvo.getRequisitionDNo()+"#"+invresultentryvo.getTestCode()+"#"+invresultentryvo.getSampleNo()+"#"+invresultentryvo.getLabCode()+"#"+invresultentryvo.getPatAge()+"#"+invresultentryvo.getPatGender()+"#"+invresultentryvo.getReportAvailableAfter()+"#"+invresultentryvo.getPatVisitDat()+"#"+invresultentryvo.getPatVisitNo()+"#"+invresultentryvo.getLabNo()+"#"+invresultentryvo.getEpisodeCode()+"#"+invresultentryvo.getDepartmentcode()+"#"+invresultentryvo.getPatdeptunitcode()+"#"+invresultentryvo.getRequisitionTypeCode()+"#"+invresultentryvo.getUomCode();                                                                                      
						
			 			 if(firstTimeTravesa)
			 			{
			 			 
					%>
				<his:SubTitleTag name="Report Details">
					<% 
			 String showDetail="showPatDetails("+i+")";
			 String hideDetail="hidePatDetails("+i+")";
			 %>
			 
			 <a onclick="printReport('<%=invresultentryvo.getReportUrl()%>')" ><font color="white">View Report</font></a>
					<img class="button" title="Show Patinet Details"
						src='<his:path src="/hisglobal/images/plusinv.png"/>'
						id="show<%=i%>" tabindex="1" onclick="<%=showDetail%>">
					<img class="button" title="Hide Patient Details"
						src='<his:path src="/hisglobal/images/Minus.png"/>'
						id="hide<%=i%>" style="display: none;" tabindex="1"
						onclick="<%=hideDetail %>">
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
									face="Verdana, Arial, Helvetica, sans-serif"> <%=invresultentryvo.getPatCRNo() %>
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
										face="Verdana, Arial, Helvetica, sans-serif"> <%=invresultentryvo.getTempSampleNo()==null?"NA":invresultentryvo.getTempSampleNo() %>
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
										key="patientName" />&nbsp;
								</font>
							</div>
						</td>
						<td class="tdfonthead" width="16%">
							<div align="left">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=invresultentryvo.getPatName() %>
								</font>
							</div>
						</td>
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
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="labName" />&nbsp;
								</font>
							</div>
						</td>
						<td class="tdfonthead" width="16%">
							<div align="left">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=invresultentryvo.getPatLabName() %>
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
										face="Verdana, Arial, Helvetica, sans-serif"> <%=invresultentryvo.getPatUnitName() %>
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
										face="Verdana, Arial, Helvetica, sans-serif"> <%=invresultentryvo.getGroupName().equals("NA")?invresultentryvo.getTestName():invresultentryvo.getGroupName() %>
									</font>
								</div>
							</td>
						</tr>
					</table>
				</div>
				<his:SubTitleTag name="Result Validation Form"></
  			</his:SubTitleTag>
				<input type="hidden" value="<%=invresultentryvo.getPatLabName()%>"
					name="chkLabName" />
					
					
				<%
  			
  			
  		
				} if(sameSampleNO)firstTimeTravesa=false;
					 			
					 			
					 				 
						 %>

		
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
			<tr >
			   <%if(lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString()!=null
					   && (lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).isDoCreateTemplate() == true)) 

			  
			  {
			  %>
						<td width="2%"><input type="checkbox" id="<%=i%><%=k%><%=i%>chkBOx"
							name="chkResultValidationPatient" value='<%=chkTestValue%>'>
							<html:hidden name="invReportAddendumFB" styleId="<%=reqDno%>" value="<%=labCode%>"
								property="cannedLabCode" /> <html:hidden
								name="invReportAddendumFB" property="cannedOrMacroCheck" /></td>

					<%-- 	<td class="tdfont" width="15%">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="TestName" />&nbsp;
								</font>
							</div>
						</td> --%>
						<td class="tdfonthead" width="10%">
							<div align="left">
								<font color="blue" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									<%=lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).getDynamnicTemplateResultEntryGroup().equals("1")?invresultentryvo.getGroupName():invresultentryvo.getTestName()%>
								</font>
							</div>
						</td>


	<logic:equal name="invReportAddendumFB" property="amendmentCheck" value="1">
						<td width='99.99%' id="<%=i%><%=k%><%=i%>templateValue"><%=
					  lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString() %>
</logic:equal>
<logic:notEqual name="invReportAddendumFB" property="amendmentCheck" value="1">
<td width='99.99%' id="<%=i%><%=k%><%=i%>templateValue">
</logic:notEqual>
			   <%}else if(lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString()!=null
			   && lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).isDoCreateTemplate() == false)
			   {
				  // do nothing 
			   }else
				   {%>
			   <left>
                   <font color="red" size="4">
				     <bean:message key="resultEntryNot"/>&nbsp;<%=invresultentryvo.getTestName()%>
				   </font>
			  </left>
			  
			    </td>
			    <%} %>
			 </tr>
														
			</table>
			
			
		
				
				
				
	<%}
			 		
			 		
			 		
					
					
					if(!(finalRemarkCodeList.indexOf(invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo())!=-1))
	  			{
	  				finalRemarkCodeList.add(invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo());
	  				
	  				
	  				%>
	  				
	  							<table width="100%" cellpadding="0" cellspacing="0" border="0">
	  			
	  				
	  				<tr>
	  				<td width="2%"></td>
	  				<td width="20%" class="tdfonthead">
	  					<div align="left">
	  				&nbsp;&nbsp;&nbsp;&nbsp;Reasons
	  				</div>				
	  				</td>
	  				
	  				
	  				<td width="78%" class="tdfonthead">
	  				<div align="left">
	  				
	  				<logic:present name="<%=InvestigationConfig.ADDENDUM_REASON_LIST%>">
			    
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="invReportAddendumFB" property="reasonCode"    tabindex="1"  >
				       					<html:option value="-1">Select value</html:option>	
				       				
				 	   					<html:options collection="<%=InvestigationConfig.ADDENDUM_REASON_LIST%>" property="value" labelProperty="label"/>
				      				</html:select>
				  <html:hidden name="invReportAddendumFB" property="crNoReqNoStringReason" value="<%=invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo()%>"/>
				      				</span>
				 
				  </logic:present>
	  				
	  				
	  				<%-- <html:textarea  style="width: 900px;" value="<%=invresultentryvo.getFinalRemarkString()%>" styleId="<%=invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo()%>" name="invReportAddendumFB" property="finalRemarksValue"
	  				 >
	  				</html:textarea> --%>
	  				<%-- <html:hidden name="invReportAddendumFB" property="crNoReqNoString" value="<%=invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo()%>"/> --%>
			</div>						
	  				</td>
	  				
	  				
	  				
	  				</tr>
	  				</table>
	  				
	  				
	  				<%
	  				
	  			if(invresultentryvo.getFinalRemarkReqd().equals("1")){ %>
	  			
	  			
	  			
	  	
	  				<table width="100%" cellpadding="0" cellspacing="0" border="0">	
	  				
	  				<tr>
	  				<td width="2%" ></td>
	  				<td width="20%" class="tdfonthead">
	  					<div align="left">
	  				&nbsp;&nbsp;&nbsp;&nbsp;Comments
	  				</div>				
	  				</td>
	  				
	  				<td width="78%" class="tdfonthead">
	  				<div align="left">
	  				
	  				<html:textarea  style="width: 900px;" value="<%=invresultentryvo.getFinalRemarkString()%>" styleId="<%=invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo()%>" name="invReportAddendumFB" property="finalRemarksValue"
	  				 >
	  				</html:textarea>
	  				<html:hidden name="invReportAddendumFB" property="crNoReqNoString" value="<%=invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo()%>"/>
													</div>
	  				</td>
	  				
	  				
	  				</tr>
	  				
	  				</table>
	  				
			<%}}
			
			%>
			
			<logic:equal name="invReportAddendumFB" property="addendumCheck" value="1">
			
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
	  				<tr>
	  				<td width="2%" ></td>
	  				<td width="20%" class="tdfonthead">
	  					<div align="left">
	  				&nbsp;&nbsp;&nbsp;&nbsp;Addendum Remark(s)  
	  				</div>				
	  				</td>
	  				
	  				<td width="78%" class="tdfonthead">
	  				<div align="left">
	  				
	  				<html:textarea  style="width: 900px;" styleId="<%=invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo()%>" name="invReportAddendumFB" property="addendumRemarks"
	  				 >
	  				</html:textarea>
	  				<html:hidden name="invReportAddendumFB" property="crNoReqNoStringAddendum" value="<%=invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo()%>"/>
													</div>
	  				</td>
	  				
	  				
	  				</tr>
	  				</table>
	  				
	  				
	  				</logic:equal>
			
			
			
			
			
			<%		

					 		i++;
		}  %>


				<div id="cannedField" style="display: none;">
					<div draggable="true">

						<his:TitleTag name="Canned List">
							<%-- <his:insertDateTag/> --%>



							<img src='/HISInvestigationG5/hisglobal/css/close.png'
								onClick="autocompleteBox_close();">

						</his:TitleTag>
						<table width="100%" id="autoCannedCombo">


							<html:hidden name="invReportAddendumFB"
								property="currentElement" />
							<html:hidden name="invReportAddendumFB"
								property="currentElementName" />
							<html:hidden name="invReportAddendumFB" property="editorName" />

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

				<div id="macroField" style="display: none;">
					<div draggable="true">
						<his:TitleTag name="Macro List">
							<%-- <his:insertDateTag/> --%>



							<img src='/HISInvestigationG5/hisglobal/css/close.png'
								onClick="autocompleteBox_close_macro();">

						</his:TitleTag>
						<table width="100%" id="autoMacroCombo">


							<html:hidden name="invReportAddendumFB"
								property="currentElement" />
							<html:hidden name="invReportAddendumFB"
								property="currentElementName" />
							<html:hidden name="invReportAddendumFB" property="editorName" />



							<tr>
								<td class="tdfonthead" width="20%">
									<div align="right">
										<b><bean:message key="macroCode" /></b>
									</div>
								</td>
								<td class="tdfont" width="20%">
									<div align="left" class="ui-widget">
										<input type="text" id="automplete-m" name="userMacro"
											size="30" style="width: 100px;"
											onkeypress="if(event.keyCode==13) addCannedDetail('MACRO');return validateAlphaNumericOnly(event,this);">


									</div>
								</td>



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




				<div id="canned">
					<div draggable="true">
						<his:TitleTag name="Canned List">
							<%-- <his:insertDateTag/> --%>



							<img src='/HISInvestigationG5/hisglobal/css/close.png'
								onClick="lightbox_close();">

						</his:TitleTag>
						<table id="addMoreValue" width="100%">



							<html:hidden name="invReportAddendumFB"
								property="currentElement" />
							<html:hidden name="invReportAddendumFB"
								property="currentElementName" />

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



							<html:hidden name="invReportAddendumFB"
								property="currentElement" />
							<html:hidden name="invReportAddendumFB"
								property="currentElementName" />

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
		
				<logic:equal name="invReportAddendumFB" property="newEntry"
					value="1">
					<img class="button"
						src='<his:path src="/hisglobal/images/btn-save-validate.png"/>'
						id="nextDiv1" style="cursor: pointer; display: none" tabindex="1"
						onclick="revalidateSamplePatDetails();">
				</logic:equal>

				<img class="button"
					src='<his:path src="/hisglobal/images/btn-mo.png"/>' id="nextDiv"
					style="cursor: pointer; display: none" tabindex="1"
					onclick="displaySamplePatDetails();">
					
					
					<img class="button"
					src='<his:path src="/hisglobal/images/btn-mo.png"/>' id="nextDivfortest"
					style="cursor: pointer; display: none" tabindex="1"
					onclick="displaySamplePatDetails1();">
					
					
				<logic:present
					name="<%=InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO %>">

			<%-- 		<logic:equal name="invReportAddendumFB" property="newEntry"
						value="1">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-save-validate-all.png"/>'
							id="saveDivAll" onkeypress="if(event.keyCode==13) onSave();"
							tabindex="1" onclick="selectAll();">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-save-validate.png"/>'
							id="saveDiv" onkeypress="if(event.keyCode==13) onSave();"
							tabindex="1" onclick="onSave();">
					</logic:equal> --%>


				<%-- 	<logic:equal name="invReportAddendumFB" property="newEntry"
						value="2">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-mo-all.png"/>'
							id="saveDivAll" onkeypress="if(event.keyCode==13) onSave();"
							tabindex="1" onclick="modifyAll();">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-mo.png"/>' id="saveDiv"
							onkeypress="if(event.keyCode==13) onSave();" tabindex="1"
							onclick="onModify();">
					</logic:equal>
 --%>


				<img class="button"
					src='<his:path src="/hisglobal/images/btn-gen-all.png"/>'
					id="goButton" style="cursor: pointer;"
					onkeypress="if(event.keyCode==13) generateAllPackingList();"
					onclick="selectAll();" tabindex="1">


				<img class="button"
					src='<his:path src="/hisglobal/images/btn-generate.png"/>'
					id="nextDiv" style="cursor: pointer;" tabindex="1"
					onkeypress="if(event.keyCode==13) onSave();"
					onclick="onSave();">


				<img class="button"
						src='<his:path src="/hisglobal/images/cannedFile.png"/>'
						onclick="popupCallCanned();" tabindex="1">

					<img class="button"
						src='<his:path src="/hisglobal/images/macro.png"/>'
						onclick="popupCallMacro();" tabindex="1">

				</logic:present>
				<logic:present
					name="<%=InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">
					<logic:present
					name="<%=InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO %>"> <!-- to hide ccl button on list page -->
					<img class="button"
						src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="cancel"
						tabindex="1" style="cursor: pointer"
						onkeypress="if(event.keyCode==13) submitFor();" tabindex="1"
						onclick="submitFor();">
				</logic:present>
			</logic:present>
			
			
			
			<logic:present
					name="<%=InvestigationConfig.NEW_ENTRIES_PATIENT_VO %>">
						<logic:notPresent
					name="<%=InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO %>"> 
										
				<img class="button"
					src='<his:path src="/hisglobal/images/btn-generate.png"/>'
					id="nextDiv" style="cursor: pointer;" tabindex="1"
					onkeypress="if(event.keyCode==13) onSave();"
					onclick="onSave();">
					
					
						<img class="button"
						src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="cancel"
						tabindex="1" style="cursor: pointer"
						onkeypress="if(event.keyCode==13) submitFor();" tabindex="1"
						onclick="submitFor();">
						</logic:notPresent>
			</logic:present>
			
			<logic:equal name="invReportAddendumFB" property="showStatus" value="1">
				<logic:equal name="invReportAddendumFB" property="diagnosisCheck" value="1">		
		<logic:notPresent
				name="<%=InvestigationConfig.NEW_ENTRIES_PATIENT_VO %>">
				
					<img class="button"
						src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="cancel"
						tabindex="1" style="cursor: pointer"
						onkeypress="if(event.keyCode==13) submitFor();" tabindex="1"
						onclick="submitFor();">
				
				</logic:notPresent>
				</logic:equal>
				</logic:equal>
				
		</his:ButtonToolBarTag>
			<logic:equal name="invReportAddendumFB" property="showStatusLegends" value="1">
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
								tabindex="1" onclick="showLegends1();"
								onkeypress="if(event.keyCode==13) showLegends1();"> <font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">Hide </font><img
								src='<his:path src="/hisglobal/images/arrow_up.gif"/>'
								tabindex="1" onclick="showLegendsNone1();"
								onkeypress="if(event.keyCode==13) showLegendsNone1();">
						</div>
					</td>
				</tr>
			</table>
		</his:SubTitleTag>
		</logic:equal>
		<div id="divLegends1" style="display: none">
			<his:ContentTag>
				<table width="100%" colspacing="1" colpadding="0"
					style="clear: both; border-left: 1px solid #003366; border-top: 1px solid #003366">
					<tr>
						<td width="20%"><font color="blue" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Blue Color Code</div>
						</font></td>
						<td width="80%"><font color="blue" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Amendment/Addendum Not Done.</div>
						</font></td>
					</tr>
					
					<tr>
						<td width="20%"><font color="red" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Red Color Code</div>
						</font></td>
						<td width="80%"><font color="red" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Amendment/Addendum Done.</div>
						</font></td>
					</tr>
					

				</table>
			</his:ContentTag>
		</div>

<logic:equal name="invReportAddendumFB" property="showStatus" value="1">
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


		<html:hidden name="invReportAddendumFB"
			property="resultValidationTemplateValue" />
		<html:hidden name="invReportAddendumFB" property="parameterCode" />
		<html:hidden name="invReportAddendumFB"
			property="parantParameterCode" />
		<html:hidden name="invReportAddendumFB" property="requisitionDNo" />
		<html:hidden name="invReportAddendumFB"
			property="resultValidationTemplateValueWithHash" />
		<html:hidden name="invReportAddendumFB" property="startDisplay" />
		<html:hidden name="invReportAddendumFB" property="hideDisplay" />
		<!-- added by krishnan nema on 01/10/2018 -->
		<html:hidden name="invReportAddendumFB" property="patTestType" />
		
		<input type="hidden" name="cannedDataCount" value="0" />
		<input type="hidden" name="cannedDetails" value="0" />
		<input type="hidden" id="hiddenid4" name="userCannedCode" />
		<input type="hidden" id="hiddenidm" name="userMacroCode" />
		<input type="hidden" name="macroDataCount" value="0" />
		<input type="hidden" name="macroDetails" value="0" />
	<html:hidden name="invReportAddendumFB"
			property="fileName" />

		<his:status />
		
		<% if(session.getAttribute("addendumStatusFromRaising")!=null){
		
			
			String msg=(String)session.getAttribute("addendumStatusFromRaising");
		%>
	<div style="font-weight: bold;">
		<%=msg %><%}else{} %>
		</div>
	</html:form>
</body>
</html>