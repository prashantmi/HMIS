<!-- 
 
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: Puneet
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : DEPARTMENT RESULT ENTRY ACTION
 ## Purpose						        : 
 ## Date of Creation					: 20/08/2015
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 

  -->
 <%@page import="new_investigation.vo.Inv_RequisitionRaisingPatientVO"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page
	import="new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB"%>
	<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<%@page import="new_investigation.transactions.controller.fb.departmentSpecificResultEntryFB"%>
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
<his:css src="/hisglobal/css/invPopup.css" />
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
<his:javascript src="/hisglobal/js/css-pop-inv.js" />


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
<%@page import="new_investigation.transactions.controller.fb.departmentSpecificResultEntryFB"%>

 
 
<his:javascript src="/hisglobal/js/jquery-ui.js" />
<his:javascript src="/hisglobal/js/sweet-alert.min.js" />
<script src="media/misc/datepicker1.js" type="text/javascript"></script>
<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

<%@page import="new_investigation.InvestigationConfig"%>

<html>
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

<script type="text/javascript">

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
var counter=1;
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
	 
	 
	 //alert(sampleno);
	 //alert(sampleno1);
	// alert(reqno1)	
	 ///alert(aa1);
		
	 var finalsampleno=sampleno+""+sampleno1;
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
 
//alert(sizee);
var row = table1.insertCell(counter);
var f=1;
var finalreqno=reqno+""+reqno1;
var crreqnoFile1=crno+"#"+finalreqno;

row.innerHTML = "<font color='#FF0000' size='4'>"+"File "+counter+":</font><input type='file' name='file["+sizeee+"]'><input type='hidden' name='crreqnoFile' value='"+crreqnoFile1+"'>";
counter++;

document.getElementById('hidetbl'+finalsampleno).style.display='';
document.getElementsByName("sizeOfFile").value++;
sizeee++;
}

 
 function hidefile(sampleno,sampleno1)
 {
//alert("hh");
var finalsampleno=sampleno+""+sampleno1;
if(counter>1)
	{
counter--;
	 document.getElementById("filerow"+finalsampleno).deleteCell(counter);
	 sizeee--;
	}
if(counter==1)
	{
	document.getElementById("divfile"+finalsampleno).style.display='none';
	document.getElementsByName("hideminus")[0].style.display='none';
	}
	 
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
			    		 
			    	alert("please select same cr no");
			    	document.getElementById('nextDiv').style.display="none";
			    	obj.checked=false;
			    	return false;
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
//         	    // assigns style properties
//         	     var name=get_tags[i].name;
//         	     alert("i"+name);
//         	  }
        	 var checkBoxId=cbs[i].id;
        	//alert(checkBoxId);
        	// var splitTheCheckBoxId=checkBoxId.substr(0,2);
        	 
        	 var splitTheCheckBoxId=checkBoxId.replace('chkBOx', '');
        	 
        	 
        	// alert(splitTheCheckBoxId);
             for(k;k<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input").length;k++)
        	    {

             	
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

                    //chandan comment auto    
                /*     if(id.contains('auto'))    
                  	  {
                
                  	 if( document.getElementById(hiddenid).value!=null && document.getElementById(hiddenid).value!="" )
                  	  resultValidationTemplateValue=document.getElementById(hiddenid).value;
                  	 else
                  		 resultValidationTemplateValue=document.getElementById(defaultid).value;
                  	  } 
                    else */
                    if(typ!=checkboxcheck)
                        {
                      resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value;
                      //alert("1 " +name);
                          name+='#'+resultValidationTemplateValue+'#'+values;
                    // alert(name);    
                       concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                       concatenateChkBoxVal+='@';
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
	        name+='#'+resultEntryTemplateValue+'#'+values;
	          
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
              //alert(resultEntryTemplateValue);
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
     		  	concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
    			concatenateChkBoxVal+='@';

     	       
     	       } 
        
       
         
         }
   
    
     }
	

	//alert("Finally concatenateChkBoxValues For Save "+concatenateChkBoxVal);
	
	
	document.getElementsByName('resultEntryTemplateValueWithHash')[0].value=concatenateChkBoxVal;
	//alert(document.getElementsByName('resultEntryTemplateValueWithHash')[0].value);
	
	/* for(j;j<reqNO.length;j++)
		{ 
		
	
	document.getElementsByName('resultEntryTemplateValue').value=resultEntryTemplateValue[j];
    alert(document.getElementsByName('resultEntryTemplateValue').value);
	document.getElementsByName('parameterCode').value=parameterCode[j];
    document.getElementsByName('parantParameterCode').value=parantParameter[j];
    document.getElementsByName('requisitionDNo').value=reqNO[j];
		} */
		
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
        	
        	 //var splitTheCheckBoxId=checkBoxId.substr(0,2);
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
              
               //  alert("type is " + typ);
                   if(typ!=hidddentext)
                   {
                  	 
            //      	 alert("not hidden");
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
                    
                    if(id.indexOf("auto")!=-1)    
                  	  {
                  
                  	 if( document.getElementById(hiddenid).value!=null && document.getElementById(hiddenid).value!="" )
                  	  resultValidationTemplateValue=document.getElementById(hiddenid).value;
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
 	        	  {//  alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value)
 	        	   	  multiValue+=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value;
 	        	      multiValue+="$";   	  
 	        	  
 	        	  }
 	        	 resultEntryTemplateValue=multiValue
 	        	  }
 	          
 	        else
 	       	resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value;
 		   
 	        
 	        //alert(resultEntryTemplateValue);
 	        name+='#'+resultEntryTemplateValue+'#'+values;
 	          
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
     		  	concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
    			concatenateChkBoxVal+='@';

     	       
     	       } 
        
       
         
         }
   
    
     }
	

	//alert("Finally concatenateChkBoxValues For Save "+concatenateChkBoxVal);
	
	
	document.getElementsByName('resultEntryTemplateValueWithHash')[0].value=concatenateChkBoxVal;
	//alert(document.getElementsByName('resultEntryTemplateValueWithHash')[0].value);
	
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
 
 
</script>
<script type="text/javascript">
        var isChecked = false;
        function allSelected() 
        {
        	document.getElementById("nextDiv").style.display=""; 
        	// this line is for toggle the check
            isChecked = !isChecked;
            //below line refers to 'jpCheckbox' class
            $('input:checkbox.jpCheckbox').attr('checked',isChecked);
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
         
 function autovalue()
 {
	 
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

function selectSaveReVal(obj)
{
	
	
//	alert(document.getElementsByName("saveReVal").length);
 	if(obj.checked)
		{
		for(var i=0;i<document.getElementsByName("saveReVal").length;i++)
			if(obj.value==document.getElementsByName("saveReVal")[i].value)
				document.getElementsByName("saveReVal")[i].checked=true;
		
		}
	else
		{
		
		for(var i=0;i<document.getElementsByName("saveReVal").length;i++)
			if(obj.value==document.getElementsByName("saveReVal")[i].value)
				document.getElementsByName("saveReVal")[i].checked=false;
		
		
		
		}
	
	
	
	
	
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
</script>

   
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




<body  onload="autovalue();checkEntryType();" >
	
		
<html:form action="/departmentSpecificResultEntryTemplateTile"  enctype="multipart/form-data">
	<html:hidden name="departmentSpecificResultEntryFB" property="hmode" />
	<html:hidden name="departmentSpecificResultEntryFB" property="isPatDetailPage" />
	<html:hidden name="departmentSpecificResultEntryFB" property="selectedCheckbox" />
	 <html:hidden name="departmentSpecificResultEntryFB" property="showStatus" />
	 <html:hidden name="departmentSpecificResultEntryFB" property="currentPage" />
	  <html:hidden name="departmentSpecificResultEntryFB" property="patCRNo" />
	  	  <html:hidden name="departmentSpecificResultEntryFB" property="sysDate" />
	  	  	  <html:hidden name="departmentSpecificResultEntryFB" property="getSearchType" />
	  	  	   <html:hidden name="departmentSpecificResultEntryFB" property="generationType"    />
	  	  	     <html:hidden name="departmentSpecificResultEntryFB" property="onLoadValue"    />
	 <html:hidden name="departmentSpecificResultEntryFB" property="sizeOfFile" value="0" />
	
	     
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
							<html:text property="cannedCode" name="departmentSpecificResultEntryFB"></html:text>
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
							<html:text property="cannedName" name="departmentSpecificResultEntryFB"></html:text>
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
	<logic:equal name="departmentSpecificResultEntryFB" property="hmode" value="VIEW">
		<% this.readOnly=true; %>
	</logic:equal>
		<his:TitleTag name="Department Result Entry Process">
			<%-- <his:insertDateTag/> --%>
		</his:TitleTag>
		<his:ContentTag>
		  <%
			  String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
         %>
      <bean:define name="departmentSpecificResultEntryFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="departmentSpecificResultEntryFB" property="toDate" id="tDate" type="java.lang.String"/>          
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
    	 <logic:equal name="departmentSpecificResultEntryFB" property="showStatus" value="0">
    	<his:SubTitleTag name="Details"></
  			</his:SubTitleTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			    
			         
			    
			     <tr>
			    <td width="25%" class="tdfont">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="LabType"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="25%" class="tdfont">
			      <logic:present name="<%=InvestigationConfig.LAB_COMBO_FOR_RESULT_ENTRY%>">
			      <div align="left">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="departmentSpecificResultEntryFB" property="labCode"    tabindex="1"  onchange="labBased()">
				       					
				       				<html:option value="%">All</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.LAB_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
			     </td>
			     <td width="25%" class="tdfont">
			     
			      <div align="right">
			               <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						   </font> 
						    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<bean:message key="ResultEntry"/>&nbsp;
						   </font>
				     </div>
				     
			     
			      </td>
			      <td width="25%" class="tdfont">
			      
			       <html:radio name="departmentSpecificResultEntryFB"   tabindex="1" property="newEntry" value="1"  ></html:radio>
						
						<bean:message key="newEntry"/>&nbsp;
						
						<html:radio name="departmentSpecificResultEntryFB" tabindex="1" property="newEntry" value="2" ></html:radio>
						
						<bean:message key="entered"/>&nbsp;
			       
			       
			     </td>
			     </tr>
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
 		 <tr  >
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
						<logic:equal name="departmentSpecificResultEntryFB" property="generationType" value="P">
						 
						<input type="radio"  name="patientWise"  onclick="getDetails(this)" checked="checked" value="P" />
						</logic:equal>
						<logic:notEqual name="departmentSpecificResultEntryFB" property="generationType" value="P">
						<input type="radio" name="patientWise" id="patient" onclick="getDetails(this)"   value="P" />
						</logic:notEqual>
						<bean:message key="PatientWise"/>
						
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="departmentSpecificResultEntryFB" property="generationType" value="T">
						<input type="radio" name="testWise" onclick="getDetails(this)" checked="checked"  value="T" />
						</logic:equal>
						<logic:notEqual name="departmentSpecificResultEntryFB" property="generationType" value="T">
					<input type="radio" name="testWise" onclick="getDetails(this)"   value="T" />
						</logic:notEqual>
						
						<bean:message key="testWise"/>
	     		 
	     		                                                                                               
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="departmentSpecificResultEntryFB" property="generationType" value="S">
						<input type="radio" name="sampleNoWise"  onclick="getDetails(this)"  checked="checked" value="S" />
						</logic:equal>
						<logic:notEqual name="departmentSpecificResultEntryFB" property="generationType" value="S">
						<input type="radio" name="sampleNoWise"  onclick="getDetails(this)"  value="S" />
						</logic:notEqual>
						
						
						<bean:message key="sampleNoWise"/>
	     		  
	     		                                                                                                       
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="departmentSpecificResultEntryFB" property="generationType" value="L">
						<input type="radio" name="labNoWise"  onclick="getDetails(this)" checked="checked" value="L" />
						</logic:equal>
						<logic:notEqual name="departmentSpecificResultEntryFB" property="generationType" value="L">
						<input type="radio" name="labNoWise"  onclick="getDetails(this)"  value="L" />
						</logic:notEqual>
						
						
						<bean:message key="labNOWise"/>
						
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="departmentSpecificResultEntryFB" property="generationType" value="TG">
						<input type="radio" name="testGroupWise"  onclick="getDetails(this)" checked="checked" value="TG" />
						</logic:equal>
						<logic:notEqual name="departmentSpecificResultEntryFB" property="generationType" value="TG">
						<input type="radio" name="testGroupWise"  onclick="getDetails(this)"  value="TG" />
						</logic:notEqual>
						
						
						<bean:message key="testGrpWise"/>
						
						
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="departmentSpecificResultEntryFB" property="generationType" value="AP">
						<input type="radio" name="allPatient"  onclick="getDetails(this)" checked="checked" value="AP" />
						</logic:equal>
						<logic:notEqual name="departmentSpecificResultEntryFB" property="generationType" value="AP">
						<input type="radio" name="allPatient"  onclick="getDetails(this)"  value="AP" />
						</logic:notEqual>
						
						
						<bean:message key="allPatient"/>
	     		   </div>  
	     		    
			     </td>
			     
			     </tr>
 		     <tr>
			    <td width="25%" class="tdfont">
			    <div align="right" id="showOnLoad" style="display:none">
			    <bean:message key="crNO"/>&nbsp;
			    </div>
			        <div align="right" >
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 <logic:equal name="departmentSpecificResultEntryFB" property="generationType" value="P">
								<bean:message key="crNO"/>&nbsp;
								</logic:equal>
								 <logic:equal name="departmentSpecificResultEntryFB" property="generationType" value="T">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="testName"/>&nbsp;
								</logic:equal>
								 <logic:equal name="departmentSpecificResultEntryFB" property="generationType" value="L">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="fromLabNo"/>&nbsp;
								</logic:equal>
								 <logic:equal name="departmentSpecificResultEntryFB" property="generationType" value="S">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="fromSampleNo"/>&nbsp;
								</logic:equal>
								<logic:equal name="departmentSpecificResultEntryFB" property="generationType" value="TG">
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
				 <html:select name="departmentSpecificResultEntryFB" property="testWiseCode"    tabindex="1"   >
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
				 <html:select name="departmentSpecificResultEntryFB" property="fromSampleNo"    tabindex="1"  >
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
				 <html:select name="departmentSpecificResultEntryFB" property="fromLabNo"    tabindex="1"   >
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
				 <html:select name="departmentSpecificResultEntryFB" property="testGroupCodeWise"    tabindex="1"   >
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
			     <logic:equal name="departmentSpecificResultEntryFB" property="generationType" value="L">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="toLabNo"/>&nbsp;
								</logic:equal>
								 <logic:equal name="departmentSpecificResultEntryFB" property="generationType" value="S">
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
				 <html:select name="departmentSpecificResultEntryFB" property="toLabNo"    tabindex="1"   >
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
				 <html:select name="departmentSpecificResultEntryFB" property="toSampleNo"    tabindex="1"  >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
			     </td>
			     </tr>
			   <tr>
			   <td class="tdfont" align="left" colspan="4" width="25%">
			  <div align="center">
			  <img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) onClickGO('<%=hospitalCode%>')" onclick="onClickGO('<%=hospitalCode%>')" tabindex="1">
			 <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelFunc() " onclick="cancelFunc()" tabindex="1">
			     
			      </div>
			   </td>
			   </tr>  
			     </table>
			     </logic:equal>
			     </his:ContentTag>
     <%boolean flag=false; %>
  	 <%
				//Pagination Logic
					PaginationFB fbPage= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage);
					fbPage.setCurrentPage(((departmentSpecificResultEntryFB)request.getAttribute("departmentSpecificResultEntryFB")).getCurrentPage());
					fbPage.setObjArrName(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
					fbPage.setAppendInTitle("List ");
					int maxRecord=15;
					fbPage.setMaxRecords(maxRecord);
				 
				 %>
				 
				 <logic:equal name="departmentSpecificResultEntryFB" property="showStatus" value="0">
				 		<his:PaginationTag name="fbPagination"></his:PaginationTag>
			<logic:present name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">
			<% flag=true; %>
			<table   width="100%" bgcolor="#EBEBEB"   >
			<tr>
					<td width="3%" align="left"  >	
						<b> <font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif">
					<input type="checkbox" id="selectAllCheckbox" onclick="allSelected()" /> </font>
	                  </b>
					</td>
					<td width="15%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						CR NO </font></b>
					</td>
					<td width="15%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="patientName"/></font></b>
					</td>
					<td width="10%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="age/gender"/></font></b>
					</td>
					<td width="15%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="departmentunit"/></font></b>
					</td>
					<td width="12%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="TestName"/></font></b>
					</td>
					<td width="11%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="labName"/></font></b>
					</td>
					
					<td width="8%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="sampLabNo"/></font></b>
					</td>
					
					
					<td width="12%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="patStatus"/></font></b>
					</td>
				</tr>
			</table>
			<logic:empty name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">
				<center>
				<font color="red" size="4">
				<bean:message key="noRecord"/></font></center>
			</logic:empty>
			<logic:notEmpty name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">
			<table   width="100%" bgcolor="#EBEBEB" cellspacing="0" style="border-spacing: 0;">
					<%
					 List<ResultEntryVO> lstPatVO=(List<ResultEntryVO>)session.getAttribute(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
					 int i,size=0;
			 		if(lstPatVO!=null && lstPatVO.size()>0 )
			 			size=lstPatVO.size();
					int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
					int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
						String grpCode="";
						for(int j=startIndex;j<=endIndex;j++)
					{
						//int i=j-startIndex;
						boolean firstTimeTravesall=true;
					if(j<size)
									{
						ResultEntryVO voPat=lstPatVO.get(j);
						String  chkVal=voPat.getPatCRNo()+"#"+voPat.getRequisitionNo()+"#"+voPat.getRequisitionDNo()+"#"+voPat.getGroupCode();
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
						<td width="3%" align="left"  >
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<input type="checkbox" class="jpCheckbox" name="chkSamplePatient" value='<%=chkVal%>' onclick="ValidateSameCrNo(this)" >
							</font>
						</td>
						<td width="15%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatCRNo() %></font> 
						 
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
						 	<%=voPat.getTempSampleNo()==null?voPat.getLabNo():voPat.getTempSampleNo()%></font>
						  
				  		</td>
				  		
				  		
				  		<td width="12%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatStatus() %></font>
						  
				  		</td>
					</tr>
				
					<%}}  }%>
					
					
			</table>
			</logic:notEmpty>
			</logic:present>
			 </logic:equal>
			 
			 <logic:present name="<%=InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO %>">
			
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

var autoId='<%=autoCode%>';
//alert(autoId);
var hiddenId='<%=hiddenCode%>';

generateAuto(objList,autoId,hiddenId);

				
					
					
					</script>
					 <input type="hidden"  id='<%=hid %>'  name="userCannedCode" /> 
					<%
					
				}}
				
				
				
				
				
					 
						Iterator itrr=_mpp.keySet().iterator();
					int  size=0;
							int i=0;
						 
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
					 				 chkTestValue=invresultentryvo.getPatCRNo()+"#"+invresultentryvo.getRequisitionNo()+"#"+invresultentryvo.getRequisitionDNo()+"#"+invresultentryvo.getTestCode()+"#"+invresultentryvo.getSampleNo()+"#"+invresultentryvo.getLabCode()+"#"+invresultentryvo.getPatAge()+"#"+invresultentryvo.getPatGender()+"#"+invresultentryvo.getReportAvailableAfter()+"#"+invresultentryvo.getPatVisitDat()+"#"+invresultentryvo.getPatVisitNo()+"#"+invresultentryvo.getLabNo()+"#"+invresultentryvo.getEpisodeCode()+"#"+invresultentryvo.getDepartmentcode()+"#"+invresultentryvo.getPatdeptunitcode()+"#"+invresultentryvo.getRequisitionTypeCode()+"#"+invresultentryvo.getUomCode()+"#"+invresultentryvo.getTestName()+"#"+invresultentryvo.getPatLabName()+"#"+invresultentryvo.getSampleName()+"#"+invresultentryvo.getTempSampleNo()+"#"+invresultentryvo.getGroupCode()+"#"+invresultentryvo.getDynamicTemplatePrintedGroup()+"#"+invresultentryvo.getPatName()+"#"+invresultentryvo.getRefRange() +"#"+invresultentryvo.getDetpUnitCode()+"#"+invresultentryvo.getPatUnitName();
					 			 }
					 			String labCode=invresultentryvo.getLabCode();
					 			String reqDno=invresultentryvo.getRequisitionDNo();
					 			//String chkTestValue=invresultentryvo.getPatCRNo()+"#"+invresultentryvo.getRequisitionNo()+"#"+invresultentryvo.getRequisitionDNo()+"#"+invresultentryvo.getTestCode()+"#"+invresultentryvo.getSampleNo()+"#"+invresultentryvo.getLabCode()+"#"+invresultentryvo.getPatAge()+"#"+invresultentryvo.getPatGender()+"#"+invresultentryvo.getReportAvailableAfter()+"#"+invresultentryvo.getPatVisitDat()+"#"+invresultentryvo.getPatVisitNo()+"#"+invresultentryvo.getLabNo()+"#"+invresultentryvo.getEpisodeCode()+"#"+invresultentryvo.getDepartmentcode()+"#"+invresultentryvo.getPatdeptunitcode()+"#"+invresultentryvo.getRequisitionTypeCode()+"#"+invresultentryvo.getUomCode();                                                                                      
								
					 			 if(firstTimeTravesa)
					 			{
					 			 
					 	
			%>
			
			<his:SubTitleTag name="Patient Details">  
			 <% 
			 String showDetail="showPatDetails("+i+")";
			 String hideDetail="hidePatDetails("+i+")";
			 %>
  			 <img class="button" title="upload files"  src='<his:path src="/hisglobal/images/plusinv.png"/>' id="show<%=i%>"      tabindex="1" onclick ="<%=showDetail%>" >
  			<img class="button" title="delete upload files"  src='<his:path src="/hisglobal/images/Minus.png"/>' id="hide<%=i%>" style="display: none;"     tabindex="1" onclick ="<%=hideDetail %>" >
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
		 				<bean:message key="sampleNo"/>&nbsp;

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
		 				 	<bean:message key="SampleName"/>&nbsp;
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="16%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=invresultentryvo.getSampleName() %>
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
		 				 	<bean:message key="visitDate"/>&nbsp;
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="16%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=invresultentryvo.getPatVisitDat() %>
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
	
  			<%-- 		<his:SubTitleTag name="Upload File">
 <img class="button" title="Add File"  src='<his:path src="/hisglobal/images/plusinv.png"/>' id="shwbrowse<%=invresultentryvo.getSampleNo()%>"      tabindex="1" onclick ="showBrowse(<%=invresultentryvo.getPatCRNo() %>,<%=invresultentryvo.getRequisitionNo() %>,<%=invresultentryvo.getSampleNo().substring(0,10) %>,<%=invresultentryvo.getSampleNo().substring(10,invresultentryvo.getSampleNo().length()) %>)" >  					
  	 <img class="button" title="Add File"  src='<his:path src="/hisglobal/images/plusinv.png"/>' id="addtabl<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo()+invresultentryvo.getLabNo():invresultentryvo.getSampleNo()%>"   tabindex="1" onclick ="addfile(<%=invresultentryvo.getPatCRNo() %>,<%=invresultentryvo.getRequisitionNo().substring(0,10) %>,<%=invresultentryvo.getRequisitionNo().substring(10,invresultentryvo.getRequisitionNo().length()) %>,<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo():invresultentryvo.getSampleNo().substring(0,10) %>,<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo():invresultentryvo.getSampleNo().substring(10,invresultentryvo.getSampleNo().length()) %>)" >				
  	<img class="button" title="Hide Patient Details"  src='<his:path src="/hisglobal/images/Minus.png"/>' id="hidetbl<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo()+invresultentryvo.getLabNo():invresultentryvo.getSampleNo() %>" style="display: none;"     tabindex="1" onclick ="hidefile(<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo():invresultentryvo.getSampleNo().substring(0,10) %>,<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo():invresultentryvo.getSampleNo().substring(10,invresultentryvo.getSampleNo().length())%>)" >
  			</his:SubTitleTag>
  			 --%>
  			
		<his:SubTitleTag name="Result Entry Form">
		
		<html:checkbox name="departmentSpecificResultEntryFB" property="saveReVal" value="<%=invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo()%>" onclick="selectSaveReVal(this)"></html:checkbox>
				
				<u>Print Report</u>
				
				
		UPLOAD FILES:

<%--  <img class="button" title="Add File"  src='<his:path src="/hisglobal/images/plusinv.png"/>' id="shwbrowse<%=invresultentryvo.getSampleNo()%>"      tabindex="1" onclick ="showBrowse(<%=invresultentryvo.getPatCRNo() %>,<%=invresultentryvo.getRequisitionNo() %>,<%=invresultentryvo.getSampleNo().substring(0,10) %>,<%=invresultentryvo.getSampleNo().substring(10,invresultentryvo.getSampleNo().length()) %>)" >  					
 --%>  	 <img class="button" title="Add File"  src='<his:path src="/hisglobal/images/plusinv.png"/>' id="addtabl<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo()+invresultentryvo.getLabNo():invresultentryvo.getSampleNo()%>"   tabindex="1" onclick ="addfile(<%=invresultentryvo.getPatCRNo() %>,<%=invresultentryvo.getRequisitionNo().substring(0,10) %>,<%=invresultentryvo.getRequisitionNo().substring(10,invresultentryvo.getRequisitionNo().length()) %>,<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo():invresultentryvo.getSampleNo().substring(0,10) %>,<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo():invresultentryvo.getSampleNo().substring(10,invresultentryvo.getSampleNo().length()) %>)" >				
  	<img class="button" title="Hide Patient Details"  src='<his:path src="/hisglobal/images/Minus.png"/>' id="hidetbl<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo()+invresultentryvo.getLabNo():invresultentryvo.getSampleNo() %>" style="display: none;"     tabindex="1" onclick ="hidefile(<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo():invresultentryvo.getSampleNo().substring(0,10) %>,<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo():invresultentryvo.getSampleNo().substring(10,invresultentryvo.getSampleNo().length())%>)"   name="hideminus">
		

  			</his:SubTitleTag>
  			<input type="hidden" value="<%=invresultentryvo.getPatLabName()%>" name="chkLabName" />
  			
  			<%
  			
  			
  		
  			} if(sameSampleNO)firstTimeTravesa=false;
					 			
					 			
					 				 
						 %>
			
			 
			 
		<div align="center" id="divfile<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo()+invresultentryvo.getLabNo():invresultentryvo.getSampleNo() %>" style="display:none;overflow:scroll;">
  			<table id="filetable<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo()+invresultentryvo.getLabNo():invresultentryvo.getSampleNo() %>">
  			<tr id="filerow<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo()+invresultentryvo.getLabNo():invresultentryvo.getSampleNo() %>">
  			<td>
  			<div id="uploadshw<%=invresultentryvo.getSampleNo()==null?invresultentryvo.getLabNo()+invresultentryvo.getLabNo():invresultentryvo.getSampleNo() %>" style="display:none">
  			<!-- <input type="file" name="file" multiple="multiple" /> -->
  		    <%--  <input id="uploadfile" type='file' name='file[0]' >
  		     <input type='hidden' value='<%=invresultentryvo.getPatCRNo()+"#"+invresultentryvo.getRequisitionNo() %>'>
  		 --%>   </div>
  		     
  	<%-- 	 <html:file property="fileUpload" name="departmentSpecificResultEntryFB" ></html:file> --%> 
  			</td>
  			</tr>
  			</table>
  			</div>
  				
		
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
			<tr >
			   <%if(lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString()!=null
					  && (lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).isDoCreateTemplate() == true)
					   ) 
			  
			  {
			  %>
			   <td width="2%" class="tdfonthead">
         <%--   <input type="hidden" name="<%=i%>LoincCode"  value="<%=lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).getParaLoinc()%>"/>		 --%>	
			   <input type="checkbox" id="<%=i%><%=k%>chkBOx" name="chkResultEntryPatient" value='<%=chkTestValue%>'  >
		 	<html:hidden name="departmentSpecificResultEntryFB" styleId="<%=reqDno%>" value="<%=labCode%>" property="cannedLabCode" />
		 	
		 	<html:hidden name="departmentSpecificResultEntryFB"   property="cannedOrMacroCheck" />
		 	
		  
			   </td>
			
		 			<td class="tdfonthead" width="10%">
		 				<div align="left">
		 				<font color="blue" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
							
		 					       <%=lstResultEntryVO.get(k).getResultEntryVOListValidatedBy().get(0).getDynamnicTemplateResultEntryGroup().equals("1")?invresultentryvo.getTestName():"Generic Test"%>
		 					</font>
		 					
		 					<!-- dddddddd -->
		 					<% 
											 String inst=invresultentryvo.getInstructionPat()==null?"NA":invresultentryvo.getInstructionPat();
		 					inst=inst.replace("\r\n","<br>");
											/* String inst="d"; */
											%>
										<%if(!inst.equals("NA")){ %>
											 <img  class="button" title="Show Instructions"  src='<his:path src="/hisglobal/images/add_remarks_sml.jpg"/>'    tabindex="1" onclick ="showInstructions5('<%=inst%>');">
							           <%}else{} %>
							           
							          <%--   <% if(!invresultentryvo.getIsrequisitionformneeded().equals("0")) {%>	
													 					&nbsp&nbsp<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick="ShowRequistionForm('<%=invresultentryvo.getTestCode()%>','<%=invresultentryvo.getTestName()%>','<%=invresultentryvo.getLabCode()%>','<%=invresultentryvo.getLabName()%>','<%=invresultentryvo.getRequisitionDNo()%>')">
													 					<%} %> --%>
							           
							           
							           
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
			    <td width='99.99%' id="<%=i%><%=k%>templateValue" class="tdfonthead">
			  
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
				     <bean:message key="resultEntryNot"/>&nbsp;<%=invresultentryvo.getTestName()%>
				   </font>
			  </left>
			 
			    </td>
			    <%} %>
			 </tr>
														
			</table>
			
			
			
			
			
			
			
			
		<%}
					 		
					 	
							
						
							if(!finalRemarkCodeList.contains(invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo()))
				  			{
				  				finalRemarkCodeList.add(invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo());
				  				
				  			if(invresultentryvo.getFinalRemarkReqd().equals("1")){ %>
				  			
				  				<table width="100%" cellpadding="0" cellspacing="0" border="0">
				  				<tr>
				  				<td width="2%" class="tdfonthead"></td>
				  				<td width="20%" class="tdfonthead">
				  					<div align="left">
				  				&nbsp;&nbsp;&nbsp;&nbsp;Final Remark(s) 
				  				</div>				
				  				</td>
				  				
				  				<td width="78%" class="tdfonthead">
				  				<div align="left">
				  				
				  				<html:textarea style="width: 900px;" value="<%=invresultentryvo.getFinalRemarkString()%>" styleId="<%=invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo()%>" name="departmentSpecificResultEntryFB" property="finalRemarksValue"
				  				 >
				  				</html:textarea>
				  				<html:hidden name="departmentSpecificResultEntryFB" property="crNoReqNoString" value="<%=invresultentryvo.getPatCRNo()+'#'+invresultentryvo.getRequisitionNo()%>"/>
																</div>
				  				</td>
				  				
				  				
				  				</tr>
				  				</table>
						<%}}
					 		
					 		
					 		i++;
		}  %>
		
<!-- 		 <fieldset	id="cannedField" style="border: solid 3px blue; background-color: CCE6FF;display:none">
 -->		<div id="cannedField"  style="display:none;">  <div draggable="true"    >
		 
		 	<his:TitleTag name="Canned List">
			<%-- <his:insertDateTag/> --%>
		
    	
     
    	 <img src='/HISInvestigationG5/hisglobal/css/close.png'  onClick="autocompleteBox_close();">
 
  			</his:TitleTag>
 		                             <table width="100%" id="autoCannedCombo">
 		                             
 		                               
	  <html:hidden name="departmentSpecificResultEntryFB" property="currentElement"/>
	  <html:hidden name="departmentSpecificResultEntryFB" property="currentElementName"/>
	   <html:hidden name="departmentSpecificResultEntryFB" property="editorName"/>
	  
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
														
														<%--  <td class="tdfonthead" width="20%">
														 
														 
				 
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
	
	
	
	
	
	
	
	
	
			<div id="macroField" style="display:none;"> <div draggable="true"    >
			 <his:TitleTag name="Macro List">
			<%-- <his:insertDateTag/> --%>
		
    	
     
    	 <img src='/HISInvestigationG5/hisglobal/css/close.png'  onClick="autocompleteBox_close_macro();">
 
  			</his:TitleTag>
 		                             <table width="100%" id="autoMacroCombo">
 		                             
 		                               
	  <html:hidden name="departmentSpecificResultEntryFB" property="currentElement"/>
	  <html:hidden name="departmentSpecificResultEntryFB" property="currentElementName"/>
	   <html:hidden name="departmentSpecificResultEntryFB" property="editorName"/>
	  
	  
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
				  
														 
														 
														 
														 </td>
														  --%>
														 
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
 	 
	 
    
    
	  <html:hidden name="departmentSpecificResultEntryFB" property="currentElement"/>
	  <html:hidden name="departmentSpecificResultEntryFB" property="currentElementName"/>
	  
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
 	 
	 
    
	  <html:hidden name="departmentSpecificResultEntryFB" property="currentElement"/>
	  <html:hidden name="departmentSpecificResultEntryFB" property="currentElementName"/>
	  
	</table>
	
	 
	               <his:ButtonToolBarTag>
				    
				    	 <img class="button"   src='<his:path src="/hisglobal/images/btn-ok.png"  />' onclick="setCannedDetail('MACRO')"    >
				    
				    </his:ButtonToolBarTag>
    	 
 
	</div>	 </div>
		</logic:present>
		 
		
			 <his:ButtonToolBarTag>
				     
				        <logic:equal name="departmentSpecificResultEntryFB" property="newEntry" value="1">
				    	 <img class="button"   src='<his:path src="/hisglobal/images/EnterResults.png"/>' id="nextDiv"  style="cursor:pointer;display:none"   tabindex="1" onclick ="displaySamplePatDetails();" >
				        </logic:equal>
				      
				      	<logic:equal name="departmentSpecificResultEntryFB" property="newEntry" value="2">
				       	 <img class="button"   src='<his:path src="/hisglobal/images/btn-mo.png"/>' id="nextDiv"  style="cursor:pointer;display:none"   tabindex="1" onclick ="displaySamplePatDetails();" >
				        </logic:equal>
				         <logic:present name="<%=InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO %>">
				         
				          <logic:equal name="departmentSpecificResultEntryFB" property="newEntry" value="1">
				          <img class="button" src='<his:path src="/hisglobal/images/SaveAll.png"/>' id="saveDivAll"    onkeypress="if(event.keyCode==13) onSave();"  tabindex="1" onclick ="selectAll();" >
				          <img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' id="saveDiv"    onkeypress="if(event.keyCode==13) onoSave();"  tabindex="1" onclick ="onSave();" >
				          </logic:equal>

       				<logic:equal name="departmentSpecificResultEntryFB" property="newEntry" value="2">
				      <img class="button" src='<his:path src="/hisglobal/images/btn-mo-all.png"/>' id="saveDivAll"    onkeypress="if(event.keyCode==13) onSave();"  tabindex="1" onclick ="modifyAll();" >
				          <img class="button" src='<his:path src="/hisglobal/images/btn-mo.png"/>' id="saveDiv"    onkeypress="if(event.keyCode==13) onSave();"  tabindex="1" onclick ="onModify();" >
				       </logic:equal>


				     <%--      <img class="button" src='<his:path src="/hisglobal/images/cannedFile.png"/>'   onclick ="popupCallCanned();"    tabindex="1"   >
				          
				          <img class="button" src='<his:path src="/hisglobal/images/macro.png"/>'   onclick ="popupCallMacro();"    tabindex="1"   >
				      --%>     
				          
		      		         				    </logic:present>
				    <logic:present name="<%=InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">
				     <logic:present name="<%=InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO %>"> <!-- this logic for cancel button hide in list page -->
				    <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'id="cancel" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitFor();" tabindex="1" onclick ="submitFor();">
				    </logic:present>
				    </logic:present>
				    </his:ButtonToolBarTag>
		<logic:equal name="departmentSpecificResultEntryFB" property="showStatus" value="1">		    
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
       
		 <html:hidden name="departmentSpecificResultEntryFB" property="resultEntryTemplateValue" />	 
		  <html:hidden name="departmentSpecificResultEntryFB" property="parameterCode" />	     
		   <html:hidden name="departmentSpecificResultEntryFB" property="parantParameterCode" />
	  	  <html:hidden name="departmentSpecificResultEntryFB" property="requisitionDNo" />
	     <html:hidden name="departmentSpecificResultEntryFB" property="resultEntryTemplateValueWithHash" />
	   
	     	
	     <input type="hidden"  name="cannedDataCount"  value="0" /> 
	     <input type="hidden"  name="cannedDetails"  value="0" />
	      <input type="hidden"  id="hiddenid4" name="userCannedCode" /> 
	      <input type="hidden"  id="hiddenidauto100221007" name="userCannedCode" />
	      
	        <input type="hidden"  id="hiddenidauto100221028" name="userCannedCode" />
	       <input type="hidden"  id="hiddenidm" name="userMacroCode" /> 
	     <input type="hidden"  name="macroDataCount"  value="0" /> 
	     <input type="hidden"  name="macroDetails"  value="0" />
	     
	   
	 <his:status/>		    
</html:form>
</body>
</html>  