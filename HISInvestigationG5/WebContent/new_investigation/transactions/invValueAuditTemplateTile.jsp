<!-- 
 
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: CHANDAN GUPTA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : VALUE AUDIT PROCESS
 ## Purpose						        : 
 ## Date of Creation					: 13/05/2016
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
	import="new_investigation.transactions.controller.fb.InvValueAuditFB"%>
 <%@page import="new_investigation.vo.InvValueAuditVO"%>
	
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

 
 
<his:javascript src="/hisglobal/js/jquery-ui.js" />
<his:javascript src="/hisglobal/js/sweet-alert.min.js" />
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

<%@page import="new_investigation.InvestigationConfig"%>

<html>
<script>
CKEDITOR.config.keystrokes = [];
setInterval(function() {
	///alert(document.getElementsByName("chkSamplePatient")[0].value); 
    
 }, 5000);


function showdetails()
{
	
	var count=0;
	//document.getElementsByName('isPatDetailPage')[0].value="1";
	
	var concatenateChkBoxVal="";
	//var cbs = document.getElementsByTagName('input');
	var cbs =document.getElementsByName('chkSamplePatient');
	for(var i=0; i < cbs.length; i++) {
	 // if(cbs[i].type == 'checkbox') 
    //{
    	
    	if(cbs[i].checked)
    	{
    		
    	count++;	
    //	concatenateChkBoxVal =concatenateChkBoxVal.concat(cbs[i].value);
    	//concatenateChkBoxVal+='@';
    	 }
  	//}
	  
 
      }
	
	if(count==0)
   	{
   	alert("please select a record");
   	return false;
   	}
	
	
	document.getElementsByName('hmode')[0].value="SHOWPATDETAILS";
    document.forms[0].submit();
	
	}


function validateGo()
{
	
	
	
	if(document.getElementsByName('labCode')[0].value=="-1")
		{
		alert("Please Select Lab Name");
		 document.getElementsByName("labCode")[0].focus();
		return false;
		
		}
	if(document.getElementsByName('testCode')[0].value=="-1")
	{
		alert("Please Select Test Name");
		 document.getElementsByName("testCode")[0].focus();
		return false;
	}
    document.getElementsByName('hmode')[0].value="GETLISTAUDITPROCESS";
    document.forms[0].submit();
	
	}
 
function labBasedtestlist()
{
	
	if(document.getElementsByName!="-1")
	{
		
     document.getElementsByName('hmode')[0].value="GETTESTDETAILS";
     document.forms[0].submit();
	
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
	//document.getElementsByName('showStatus')[0].value='0';
	document.getElementsByName('hmode')[0].value='NEW';
	document.forms[0].submit();
	}
	
	
function cancelFunc()
{
	window.parent.closeTab();
}

function cancel()
{
	alert("hello");
	window.parent.close();
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
                 
                 get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input");
                   name=get_tags[k].name;
                   id=get_tags[k].id;
                   //alert("id for "+k+"    "+id);
                   hiddenid="hiddenid"+id;
                   defaultid="default"+hiddenid;
              	  typ=get_tags[k].type;
              	  hidddentext="hidden";
              
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
                 
                      if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value=="")
                             {
                                    alert("Enter the field Focussed");
                                    document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].focus();
                                 return false;
                              }
                 
                         // document.getElementsByName('resultEntryTemplateValue').value.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);       
                    //  resultEntryTemplateValue.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);
                    var resultValidationTemplateValue="";
                    
                    if(id.contains("auto"))    
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
    	       {
        	 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
     		//alert("The value is"+values);
        	  get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select");
       	   name=get_tags[j].name;
        	 
        	// alert("inside here");
        	//alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length);
            	 if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value=="-1" || document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value=="")
	         	   {
				      alert("Enter the field Focussed");
				      document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].focus();
			          return false;
		           }
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
 	         	 
 	              
 	              if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value=="")
 	         	   {
 				      alert("Enter the field Focussed");
 				      document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].focus();
 			          return false;
 		           }
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
                 
                      if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value=="")
                             {
                                    alert("Enter the field Focussed");
                                    document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].focus();
                                 return false;
                              }
                 
                         // document.getElementsByName('resultEntryTemplateValue').value.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);       
                    //  resultEntryTemplateValue.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);
                    var resultValidationTemplateValue="";
                    
                    if(id.contains("auto"))    
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
             	 if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value=="-1" || document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value=="")
 	         	   {
 				      alert("Enter the field Focussed");
 				      document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].focus();
 			          return false;
 		           }
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
 	         	 
 	              
 	              if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value=="")
 	         	   {
 				      alert("Enter the field Focussed");
 				      document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].focus();
 			          return false;
 		           }
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
	alert("onload"+genTypeValue); //showOnLoad
	 if(genTypeValue=='')
		 {
		// document.getElementById("showOnLoad").style.display="";	
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
	
	
	
function checkEntryTypee()
{
 

	var genTypeValue=document.getElementsByName('generationType')[0].value;
	alert("onload"+genTypeValue); //showOnLoad
	 if(genTypeValue=='')
		 {
		// document.getElementById("showOnLoad").style.display="";	
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
	 ;
	
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
CKEDITOR.instances[inst].on("focus",function()
	{
	document.getElementsByName("currentElement")[0].value="ckeditor";
	document.getElementsByName("currentElementName")[0].value=CKEDITOR.instances[inst].name;
	document.getElementsByName("editorName")[0].value=inst;
	
	
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




<body onload="">
<html:form action="/auditprocess">
	<html:hidden name="InvValueAuditFB" property="hmode" />  
	     <html:hidden name="InvValueAuditFB" property="currentPage" />
	  <html:hidden name="InvValueAuditFB" property="showStatus" />   
	    <his:TitleTag name="Investigation Audit Process">
			<%-- <his:insertDateTag/> --%>
		</his:TitleTag>
		<logic:notEqual name="InvValueAuditFB" property="hmode" value="SHOWPATDETAILS"> 
		
		<his:ContentTag>
		  <%
			  String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
         %>
      <bean:define name="InvValueAuditFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="InvValueAuditFB" property="toDate" id="tDate" type="java.lang.String"/>          
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
			      <logic:present name="<%=InvestigationConfig.LAB_COMBO_FOR_AUDIT_PROCESS%>">
			      <div align="left">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="InvValueAuditFB" property="labCode"    tabindex="1"  onchange="labBasedtestlist()">
				       					<html:option value="-1">Select value</html:option>	
				       				<html:option value="%">All</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.LAB_COMBO_FOR_AUDIT_PROCESS%>" property="value" labelProperty="label"/>
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
								<bean:message key="testName"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="25%" class="tdfont">
			      <logic:present name="<%=InvestigationConfig.TEST_COMBO_FOR_AUDIT_PROCESS%>">
			      <div align="left">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="InvValueAuditFB" property="testCode"    tabindex="1"  onchange="labBased()">
				       					
				       				<html:option value="-1">Select value</html:option>	
				       				<html:option value="%">All</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.TEST_COMBO_FOR_AUDIT_PROCESS%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present >
				  
				 <logic:notPresent name="<%=InvestigationConfig.TEST_COMBO_FOR_AUDIT_PROCESS%>">
				 <div align="left">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="InvValueAuditFB" property="testCode"    tabindex="1"  onchange="labBased()">
				       					
				       				<html:option value="-1">Select value</html:option>
				 	   					
				      				</html:select>
				      				</span>
				  </div>
				 </logic:notPresent>
				  
				  
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
 		<!--  
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
						<logic:equal name="InvValueAuditFB" property="generationType" value="P">
						 
						<input type="radio"  name="patientWise"  onclick="getDetails(this)" checked="checked" value="P" />
						</logic:equal>
						<logic:notEqual name="InvValueAuditFB" property="generationType" value="P">
						<input type="radio" name="patientWise" id="patient" onclick="getDetails(this)"   value="P" />
						</logic:notEqual>
						<bean:message key="PatientWise"/>
						
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvValueAuditFB" property="generationType" value="T">
						<input type="radio" name="testWise" onclick="getDetails(this)" checked="checked"  value="T" />
						</logic:equal>
						<logic:notEqual name="InvValueAuditFB" property="generationType" value="T">
					<input type="radio" name="testWise" onclick="getDetails(this)"   value="T" />
						</logic:notEqual>
						
						<bean:message key="testWise"/>
	     		 
	     		                                                                                               
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvValueAuditFB" property="generationType" value="S">
						<input type="radio" name="sampleNoWise"  onclick="getDetails(this)"  checked="checked" value="S" />
						</logic:equal>
						<logic:notEqual name="InvValueAuditFB" property="generationType" value="S">
						<input type="radio" name="sampleNoWise"  onclick="getDetails(this)"  value="S" />
						</logic:notEqual>
						
						
						<bean:message key="sampleNoWise"/>
	     		  
	     		                                                                                                       
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvValueAuditFB" property="generationType" value="L">
						<input type="radio" name="labNoWise"  onclick="getDetails(this)" checked="checked" value="L" />
						</logic:equal>
						<logic:notEqual name="InvValueAuditFB" property="generationType" value="L">
						<input type="radio" name="labNoWise"  onclick="getDetails(this)"  value="L" />
						</logic:notEqual>
						
						
						<bean:message key="labNOWise"/>
						
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvValueAuditFB" property="generationType" value="TG">
						<input type="radio" name="testGroupWise"  onclick="getDetails(this)" checked="checked" value="TG" />
						</logic:equal>
						<logic:notEqual name="InvValueAuditFB" property="generationType" value="TG">
						<input type="radio" name="testGroupWise"  onclick="getDetails(this)"  value="TG" />
						</logic:notEqual>
						
						
						<bean:message key="testGrpWise"/>
						
						
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvValueAuditFB" property="generationType" value="AP">
						<input type="radio" name="allPatient"  onclick="getDetails(this)" checked="checked" value="AP" />
						</logic:equal>
						<logic:notEqual name="InvValueAuditFB" property="generationType" value="AP">
						<input type="radio" name="allPatient"  onclick="getDetails(this)"  value="AP" />
						</logic:notEqual>
						
						
						<bean:message key="allPatient"/>
	     		   </div>  
	     		    
			     </td>
			     
			     </tr>
			     -->
			     
			     <!--
 		     <tr>
			    <td width="25%" class="tdfont">
			    <div align="right" >
			    <bean:message key="crNO"/>&nbsp;
			    </div>
			        <div align="right" >
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 <logic:equal name="InvValueAuditFB" property="generationType" value="P">
								<bean:message key="crNO"/>&nbsp;
								</logic:equal>
								 <logic:equal name="InvValueAuditFB" property="generationType" value="T">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="testName"/>&nbsp;
								</logic:equal>
								 <logic:equal name="InvValueAuditFB" property="generationType" value="L">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="fromLabNo"/>&nbsp;
								</logic:equal>
								 <logic:equal name="InvValueAuditFB" property="generationType" value="S">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="fromSampleNo"/>&nbsp;
								</logic:equal>
								<logic:equal name="InvValueAuditFB" property="generationType" value="TG">
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
				 <html:select name="InvValueAuditFB" property="testWiseCode"    tabindex="1"   >
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
				 <html:select name="InvValueAuditFB" property="fromSampleNo"    tabindex="1"  >
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
				 <html:select name="InvValueAuditFB" property="fromLabNo"    tabindex="1"   >
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
				 <html:select name="InvValueAuditFB" property="testGroupCodeWise"    tabindex="1"   >
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
			     <logic:equal name="InvValueAuditFB" property="generationType" value="L">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="toLabNo"/>&nbsp;
								</logic:equal>
								 <logic:equal name="InvValueAuditFB" property="generationType" value="S">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="toSampleNo"/>&nbsp;
								</logic:equal>
								</font>
			      </div>
			      </td>
			     <td width="25%" class="tdfont">
			     </td> 
			       </tr>
			         -->
			     </table>
			     
			     
     			 <his:ButtonToolBarTag>
     			<img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) validateGo()" onclick="validateGo()" tabindex="1">
			    <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelFunc() " onclick="cancelFunc()" tabindex="1">
			   
	             </his:ButtonToolBarTag>
			    </his:ContentTag>
			    
			 </logic:notEqual>
			   <%boolean flag=false; %>
			   	 <logic:equal name="InvValueAuditFB" property="showStatus" value="0">
  	 <%
				//Pagination Logic
					PaginationFB fbPage= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage);
					fbPage.setCurrentPage(((InvValueAuditFB)request.getAttribute("InvValueAuditFB")).getCurrentPage());
					fbPage.setObjArrName(InvestigationConfig.LIST_PATIENT_AUDIT_PROCESS_ESSENTIALS_VO);
					fbPage.setAppendInTitle("List ");
					int maxRecord=10;
					fbPage.setMaxRecords(maxRecord);
				 
				 %>
				 
			
				 		<his:PaginationTag name="fbPagination"></his:PaginationTag>
			
			<% flag=true; %>
			<table   width="100%" bgcolor="#EBEBEB"   >
			<tr>
					<td width="3%" align="left"  >	
						<b> <font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif">
					<input type="checkbox" id="selectAllCheckbox" onclick="allSelected()" /> </font>
	                  </b>
					</td>
					<td width="20%" align="left"   >
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
					<td width="15%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="TestName"/></font></b>
					</td>
					<td width="11%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="labName"/></font></b>
					</td>
					
				</tr>
			</table>
			
			<logic:notEmpty name="<%=InvestigationConfig.LIST_PATIENT_AUDIT_PROCESS_ESSENTIALS_VO%>">
			<table   width="100%" bgcolor="#EBEBEB" cellspacing="0" style="border-spacing: 0;">
					<%
					 List<InvValueAuditVO> lstPatVO=(List<InvValueAuditVO>)session.getAttribute(InvestigationConfig.LIST_PATIENT_AUDIT_PROCESS_ESSENTIALS_VO);
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
						InvValueAuditVO voPat=lstPatVO.get(j);
						String  chkVal=voPat.getRequisitionDNo()+"#"+voPat.getLabCode()+"#"+voPat.getTestCode();
						//String labCode=voPat.getLabCode();

					 if(firstTimeTravesall)
			 			{
						 
					
					%>
					 
					
					<tr>
						<td width="3%" align="left"  >
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<input type="checkbox" class="jpCheckbox" name="chkSamplePatient" id="chckbox1" value='<%=chkVal%>' onclick="ValidateSameCrNo(this)" >
							</font>
						</td>
						<td width="20%" align="left" >
						  
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
				  		<td width="15%" align="left">
				  		 
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getTestName() %></font>
						  
				  		</td>
				  		<td width="11%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatLabName() %></font>
						 
				  		</td>
				  		
					</tr>
				
					<%}}  }%>
					
			
			</table>
		       
			<his:ButtonToolBarTag>
	 
	 
	 	
     			<img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' id="nextDiv"  style="cursor:pointer;display:none"  onkeypress="if(event.keyCode==13) validateGo()" onclick="showdetails()" tabindex="1">
			   </his:ButtonToolBarTag>
		
			</logic:notEmpty>
			<logic:empty name="<%=InvestigationConfig.LIST_PATIENT_AUDIT_PROCESS_ESSENTIALS_VO%>">
				<center>
				<font color="red" size="4">
				<bean:message key="noRecord"/></font></center>
			</logic:empty>
			
			 </logic:equal>
			 
			 
		<logic:notEmpty name="<%=InvestigationConfig.LIST_PATIENT_AUDIT_MODIFY_PROCESS_ESSENTIALS_VO%>">


<%
					 List<InvValueAuditVO> lstPatVO=(List<InvValueAuditVO>)session.getAttribute(InvestigationConfig.LIST_PATIENT_AUDIT_MODIFY_PROCESS_ESSENTIALS_VO);
					 boolean firstTraverse=true;
					 boolean check=true;
					 boolean secondTraverse=true;
					 String reqDno="";
					 List reqDnoList=new ArrayList();
					 List processIdList=new ArrayList();
					 List secondPidList=new ArrayList();
					 for(int k=0;k<lstPatVO.size();k++)
					 {
					 InvValueAuditVO invresultentryvo=lstPatVO.get(k);
					 reqDno=invresultentryvo.getRequisitionDNo();
					 if(!reqDnoList.contains(reqDno))
					 {
						 reqDnoList.add(reqDno);
						 firstTraverse=true;
					 }
					 else 
						 firstTraverse=false;
					
					 
%>
<%   if(firstTraverse) 
	{processIdList=new ArrayList();
	secondPidList=new ArrayList();
	%>
<his:SubTitleTag name="Patient Details">  
			<% 
			
			 String showDetail="showPatDetails("+k+")";
			 String hideDetail="hidePatDetails("+k+")";
			 %> 
  			 <img class="button" title="Show Patinet Details"  src='<his:path src="/hisglobal/images/plusinv.png"/>' id="show<%=k%>"      tabindex="1" onclick ="<%=showDetail%>" >
  			<img class="button" title="Hide Patient Details"  src='<his:path src="/hisglobal/images/Minus.png"/>' id="hide<%=k%>" style="display: none;"     tabindex="1" onclick ="<%=hideDetail %>" >
  			</his:SubTitleTag>
  			
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
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
		</table>
		<%int i=0;
		
			
		%>
		<div id="showhide<%=k%>" style="display: none;">
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
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
		 				<bean:message key="sampleNo"/>&nbsp;

		 				</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="16%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=invresultentryvo.getTempsampleno()==null?"NA":invresultentryvo.getTempsampleno() %>
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
		 					<%=invresultentryvo.getModifyDate()  %>
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
		 				 <%=invresultentryvo.getTestName()%>
		 					</font>
		 				</div>
		 			</td> 
		</tr>
		</table>
		</div>
		
		<his:SubTitleTag name="Result Log">  </his:SubTitleTag>
		
		
		
		
	       <%} %>  
	         
	         <%
	         
	       
			 String processId="";
			
			 processId=invresultentryvo.getProcessId();
			 
			 if(!processIdList.contains(processId))
			 {
				 processIdList.add(processId);
				 check=true;
			 }
			 else 
				 check=false;
			
			 
%>
 	<table width="100%">
<%   if(check) 
{%>
	         
	         
	        
	         <tr>
	          <td class="tdfont"colspan="2">
		 			<div align="center">
		 			<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				 Modified At Process</font>
		 				 		 				</div>
		  </td>
		  <td class="tdfont" colspan="2">
		 				<div align="center">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 			<b>	 <%=invresultentryvo.getProcessModify()%></b>
		 					</font>
		 				</div>
		 			</td> 
		 			 
		 	</tr>
		 	
		 	<tr>
		 	
		 	<td class="tdfont"colspan="2" width="16%">
		 				<div align="center">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				Modified By&nbsp;</font>
		 				</div>
		  </td>
		  <td class="tdfont" colspan="2">
		 				<div align="center">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 			<b>	 <%=invresultentryvo.getModifiedBy()%></b>
		 					</font>
		 				</div>
		 			</td> 
		 	</tr>
		 	
	
	       
	        <%} %>
	
	       <%
	         
	       
			 String pid="";
			
	       pid=invresultentryvo.getProcessId();
			 
	       if(!secondPidList.contains(pid))
			 {
				 secondPidList.add(pid);
				 secondTraverse=true;
			 }
			 else 
				 secondTraverse=false;
			
			 
%>
 	
<%   if(secondTraverse) 
{%>
	      
	
		
		            <tr>
	        <td class="tdfont" width="25%">
		 				<div align="center">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="ParameterName"/>&nbsp;
		 				</font>
		 				</div>
		  </td>
	        <td class="tdfont" width="25%">
		 				<div align="center">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="newvalue"/>&nbsp;
		 				</font>
		 				</div>
		  </td>
		  <td class="tdfont" width="25%">
		 				<div align="center">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="prevalue"/>&nbsp;
		 				</font>
		 				</div>
		  </td>
		  
		  <td class="tdfont" width="25%">
		 				<div align="center">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="moddate"/>&nbsp;
		 				</font>
		 				</div>
		  </td>
		 			 <!-- <td class="tdfont"colspan="2" width="16%">
		 				<div align="center">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				Modified By&nbsp;</font>
		 				</div>
		  </td> -->
		  
		 </tr>
		<%} %>
		
		 <tr>
		 
		 			<td class="tdfonthead" width="25%">
		 				<div align="center">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				 <%=invresultentryvo.getParaName()%>
		 					</font>
		 				</div>
		 			</td>  
		 			<td class="tdfonthead" width="25%">
		 				<div align="center">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				 <%=invresultentryvo.getNewValue().equals("--")?"":invresultentryvo.getNewValue()%>
		 					</font>
		 				</div>
		 			</td> 
		 			<td class="tdfonthead" width="25%">
		 				<div align="center">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				 <%=invresultentryvo.getPreValue().equals("--")?"":invresultentryvo.getPreValue()%>
		 					</font>
		 				</div>
		 			</td> 
		 			
		 			<td class="tdfonthead" width="25%">
		 				<div align="center">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				 <%=invresultentryvo.getModifyDate()+"  "+ invresultentryvo.getModifyTime()  %>
		 					</font>
		 				</div>
		 			</td>
		 <%-- <td class="tdfonthead" colspan="2" width="16%">
		 				<div align="center">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<%= invresultentryvo.getModifiedBy() %>
		 					</font>
		 				</div>
		 			</td>  --%>
		 	
		 </tr>
	 
		<%} %>      
	         </table>
	
	 <his:ButtonToolBarTag>
	 
	
	             
	             
     			  <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) submitFor(); " onclick="submitFor();" tabindex="1">
			   
	             </his:ButtonToolBarTag>
			  
	
    </logic:notEmpty>
			    	    
			    
</html:form>
</body>
</html>  