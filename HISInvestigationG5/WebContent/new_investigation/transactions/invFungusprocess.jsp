<!-- 
 
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: CHANDAN GUPTA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : ANtibiotic Organism Mapping Diagnosis PROCESS
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
	import="new_investigation.transactions.controller.fb.invFungusProcessFB"%>
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
<%@page import="new_investigation.vo.antibioticprocessVO"%>
<%@page import="new_investigation.vo.RequisitionListVO"%>
<%@page import="new_investigation.vo.invFungusProcessVO"%>
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
<script src="media/misc/datepicker1.js" type="text/javascript"></script>
<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

<%@page import="new_investigation.InvestigationConfig"%>

<html>

<style>

.odd{background-color: #ffffff;} 
  .even{background-color: #ccf5ff;} 
  
   .disable:hover{
  	background-color: yellow;
  }
  
</style>

<script language="javascript">
var counterr=0;

function preferred(obj,objj)
{
   //alert(obj);

if(obj!="")
	{
//obj="101#105";
var data=obj.split("#");	
//alert("data.length"+data.length);

        
    for(var j=0;j<data.length;j++)
        {
    	var code=data[j];
        
        for(var a=0;a<document.getElementsByName("chkSamplePatient").length;a++)
            {
               
            var dd=document.getElementsByName("chkSamplePatient")[a].value;     
               //    alert(code+dd);
               if(dd.split("#")[0]==code)
                   {
                   if(objj.checked==true)
            	   document.getElementsByName('chkSamplePatient')[a].checked=true;
                   else
                       {

                 	  var classnamee=document.getElementsByName('chkSamplePatient')[a].className;
                	  // chanks
                	  if(classnamee.includes('chanks'))
                    	  {}
                	  else
                    	  {
                	   document.getElementsByName('chkSamplePatient')[a].checked=false;    
                       }
                      }
                   }
            
            }

        }



   /*  if(this.checked==true)
	 { */

document.getElementById('pagbtn').style.display="";
	 
/* 	 }
else
{

document.getElementById('pagbtn').style.display="none";
document.getElementById('pagbtn1').style.display="none";
}	 */

	}
}
  
function adddetails(tbl)
{

	// alert(tbl);

	 	var organismCode=document.getElementsByName('organismCode')[0].value;
	 	var growthCode=document.getElementsByName('growthCode')[0].value;

                if(organismCode=="-1")
                    {
                     alert("Please Select organism");
                     return null;
                    }

                if(growthCode=="-1")
                {
                 alert("Please Select Growth");
                 return null;
                }
	 	var chkSamplePatient="";
	 	var flag="0";
	 	var flag1="0";
	 	for(var i=0;i<document.getElementsByName('chkSamplePatient').length;i++)
		 	{
	 		//alert("flagg2"+flag);
	 	 

	 		if(document.getElementsByName('chkSamplePatient')[i].checked==true)
			 	{
	 			//alert("flagg1"+flag);
	 		 	flag="2";
			 	   if(document.getElementsByName('chkSamplePatient')[i].value.indexOf("#"))
				 	{

					
					 	//alert("##");
					 	var str=document.getElementsByName('chkSamplePatient')[i].value;
			 		  str = str.replace(/#/g, '@');	
			 		// alert("str"+str);
                         var counter=str.split("@")[2];
                        // alert("counter"+counter);
						 var remark="-";
						 remark= document.getElementsByName('remarks')[i].value;

	                        if(remark=="")
		                        {
	                        	remark="-";
		                        }

                            var resultvalue="";

                           /*  for(var f=0;f<document.getElementsByName('chkInfo').length;f++)
                                {
                            	   alert("aa"+document.getElementsByName('chkInfo').length);
                               
                                         if(document.getElementsByName('chkInfo')[f].checked==true)
                                             {
                                        	 flag1="2";
                                        	 resultvalue=document.getElementsByName('chkInfo')[f].value;
                                             }
                                } */


                                var r="r#"+counter;
                                		var s="s#"+counter;
                                		var is="ir#"+counter;

                                	//	alert("r"+r);
                               if( document.getElementById(r)!=null && document.getElementById(r).checked==true)
                                   {
                            		 flag1="2";
                            		 resultvalue= document.getElementById(r).value;
                                   }

                               if(document.getElementById(s)!=null && document.getElementById(s).checked==true)
                               {
                            		 flag1="2";
                            		 resultvalue= document.getElementById(s).value;
                               }

                               if(document.getElementById(is)!=null &&  document.getElementById(is).checked==true)
                               {
                            		 flag1="2";
                            		 resultvalue= document.getElementById(is).value;
                               }
                           
                                     if(flag1=="0")
							       {
                                        alert("Please select  Antibiotic Result");
                                        return null;
        							}
                    chkSamplePatient+=str+"@@@"+resultvalue+"@@@"+remark+"@@@"+"$$$";
				 	}
				 	        
			 	}
	 	//	alert("flagg"+flag);
	 		

		 	}
	 	
	 	if(flag=="0")
	       {
          alert("Please select  Antibiotic ");
          return null;
			}
	 	var growthname =	document.forms[0].growthCode[document.forms[0].growthCode.selectedIndex].text ;
	 	var organismnamee=document.getElementsByName('organismnamee')[0].value;
	 	var reqno=document.getElementsByName('requisitionDNo')[0].value;
	 	reqno = reqno.slice(0, -2);
	 	var reqdno=document.getElementsByName('requisitionDNo')[0].value;
	 	var testparacode=document.getElementsByName('testParaCode')[0].value;
	 	
	 	//alert(counterr);
		var flg = false;
		var labTestCodeArray = "";
		var _mode = "ADDDATAINLIST";
		var objXHR = {url: "/HISInvestigationG5/new_investigation/fungusprocesss.cnt?hmode="+_mode+"&organismCode="+organismCode+"&growthCode="+growthCode+"&data="+chkSamplePatient+"&organismnamee="+organismnamee+"&organismName="+organismnamee+"&growthname="+growthname+"&requisitionNo="+reqno+"&requisitionDNo="+reqdno+"&testParaCode="+testparacode, sync:true, postData: "", handleAs: "text",
			load: function(data) 
			{
				
				//alert("true");
				labTestCodeArray = data;
				flg = true;
				var organismName1=	document.forms[0].organismCode[document.forms[0].organismCode.selectedIndex].text ;
				document.forms[0].growthCode.value = document.getElementsByName('growthCode')[0].value;
				document.forms[0].growthname.value =	document.forms[0].growthCode[document.forms[0].growthCode.selectedIndex].text ;
				/* alert(document.forms[0].growthCode[document.forms[0].growthCode.selectedIndex].text); */
				
				
				
				  //alert(organismName1);
					document.forms[0].organismName.value = organismName1;
					 /* document.getElementsByName('organismName')[0].value=organismName;
					  */
				     document.getElementsByName('hmode')[0].value="GETANTIBIOTICDETAILS";
				     document.forms[0].submit();
			},
	        error: function(error)
	        {
	        	
	            labTestCodeArray = tmpLabTestCodeArray;
	            flg = false;
	        }};

		var objDojoAjax = dojo.xhrPost(objXHR);
		
		//return labTestCodeArray;
		
    
}

function onmodifycall()
{

	//alert("ss");

	 	var organismCode=document.getElementsByName('organismCode')[0].value;
	var flg = false;
	var labTestCodeArray = "";
	var _mode = "AJX_GET_LIST_ANTIBIOITC";
// 	var objXHR = {url: "/HISInvestigationG5/new_investigation/fungusprocesss.cnt?hmode="+_mode+"&organismCode="+organismCode, sync:true, postData: "", handleAs: "text",async:false,
// 		load: function(data) 
// 		{
// 			//alert(data);
// 			labTestCodeArray = data;
// 			flg = true;
			
// 		},
		
//         error: function(error)
//         {
//             labTestCodeArray = tmpLabTestCodeArray;
//             flg = false;
//         },async:false};


/* 
	$.ajax({url: "/HISInvestigationG5/new_investigation/fungusprocesss.cnt?hmode="+_mode+"&organismCode="+organismCode,
		async: true,
		 success: function(result){
			 alert("success");
			 alert(data);
		labTestCodeArray = data;
		flg = true;
    },

    
    error: function (xhr, ajaxOptions, thrownError) {
    	 labTestCodeArray = tmpLabTestCodeArray;
         flg = false;
    }
    });
	
	//var objDojoAjax = dojo.xhrPost(objXHR);
	return labTestCodeArray; 
 */
}


/* function modifyPriorityLabTestCodeArray(tmpPriority,tmpLabCode,tmpTestCode,tmpLabTestCodeArray)
{
	var flg = false;
	var labTestCodeArray = "";
	var _mode = "AJX_MODIFY_PRIORITY";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&tmpLabCode="+tmpLabCode+"&tmpTestCode="+tmpTestCode+"&tmpPriority="+tmpPriority+"&labTestCodeArray="+tmpLabTestCodeArray, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			labTestCodeArray = data;
			flg = true;
		},
        error: function(error)
        {
            labTestCodeArray = tmpLabTestCodeArray;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return labTestCodeArray;
} */

function allSelectedd()
{
 if(document.getElementById('selectAllCheckbox').checked==true)
		 {
	 for(var i=0;i<document.getElementsByName('chkSamplePatient').length;i++)
		{
		 document.getElementsByName('chkSamplePatient')[i].checked=true;
		}
	 document.getElementById('pagbtn').style.display="";
		
		 }
 else
	 {
	 for(var i=0;i<document.getElementsByName('chkSamplePatient').length;i++)
		{
		 document.getElementsByName('chkSamplePatient')[i].checked=false;
		 document.getElementsByName('chkInfo')[i].checked=false;
		}
	 document.getElementById('pagbtn').style.display="none";
	 document.getElementById('pagbtn1').style.display="none";
		
	 }
}



function allSelectedd1()
{
 if(document.getElementById('selectAllCheckbox').checked==true)
		 {
	 for(var i=0;i<document.getElementsByName('chkSamplePatient').length;i++)
		{
		 document.getElementsByName('chkSamplePatient')[i].checked=true;
		}
//document.getElementById('pagbtn').style.display="";
		
		 }
 else
	 {
	 for(var i=0;i<document.getElementsByName('chkSamplePatient').length;i++)
		{
		 document.getElementsByName('chkSamplePatient')[i].checked=false;
		 document.getElementsByName('chkInfo')[i].checked=false;
		}
	 document.getElementById('pagbtn').style.display="none";
	// document.getElementById('pagbtn1').style.display="none";
		
	 }
}


function allSelected()
{
 if(document.getElementById('selectAllCheckbox1').checked==true)
		 {
	 for(var i=0;i<document.getElementsByName('chkSamplePatient').length;i++)
		{
		 document.getElementsByName('chkSamplePatient')[i].checked=true;
		}
		
		 }
 else
	 {
	 for(var i=0;i<document.getElementsByName('chkSamplePatient').length;i++)
		{
		 document.getElementsByName('chkSamplePatient')[i].checked=false;
		}
	
	 document.getElementById('pagbtn1').style.display="none";
		
	 }
}





function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}

function matchCase(reqdno,paracode)
{
 
    var reqdno1=document.getElementsByName('requisitionDNo')[0].value;
    var paracode1=document.getElementsByName('testParaCode')[0].value;
    document.getElementsByName('flag')[0].value=false;
    //alert(reqdno+"    "+reqdno1);
   // alert(paracode+"  "+paracode1);
	
if(reqdno==reqdno1 && paracode==paracode1)
{

	document.getElementsByName('flag')[0].value=true;
}

}


function call()
{

	   //alert(document.getElementsByName('organismCode1')[0].value);
	
	
//alert(document.getElementsByName('checkselectedvalue').length);
	for(var k=0;k<document.getElementsByName('checkselectedvalue').length;k++)
		{
		//alert(k);
	  var val=document.getElementsByName('checkselectedvalue')[k].value;

		 var val1=val.split("#")[0];
	  var idd=val.split("#")[1];
	//  alert(idd);
	  var re="r#"+idd;
		var ir="ir#"+idd;
		var s="s#"+idd;
//alert(val1);
	    if(val1!=='-1')
  {
		
	  if(val1==1)
		  {
		  
		  document.getElementById(re).checked=true;
		  document.getElementsByName('chkSamplePatient')[idd].checked=true;
		  }
	  if(val1==2)
	  {
	  
	  document.getElementById(s).checked=true;
	  document.getElementsByName('chkSamplePatient')[idd].checked=true;
	  }
	  if(val1==3)
	  {
	  
	  document.getElementById(ir).checked=true;
	  document.getElementsByName('chkSamplePatient')[idd].checked=true;
	  }    
	  

  }
		}

//alert("hello");


	
   if('SAVE'== document.getElementsByName('hmode')[0].value)
{
	 //  alert("save");
  var innerHtmlVar=document.getElementById('tempDivForInnerHTML').innerHTML;
      
     var reqDno=document.getElementsByName('requisitionDNo')[0].value;
     var testParacode=document.getElementsByName('testParaCode')[0].value;

    // var counter=opener.document.getElementsByName('Counter')[0].value;
        // alert(counter);
     	var tdId="td#"+reqDno+"#null#template#"+testParacode; 
	var microbialDivId="divId_"+reqDno+"#null#template#"+testParacode;
	var idddd="idd"+reqDno+"#null#template#"+testParacode;
	//alert(tdId);
	//var DivId="idd331011000216081210001301#null#template#100091007";
	var tdElement=opener.document.getElementById(tdId);
	var valuee=document.getElementsByName('mappinglist2')[0].value;
	var hyperlinktable=document.getElementsByName('finaltable')[0].value;
	var hyperlinktblsesInSession="hyperLinkTableSession";
if(opener.document.getElementById(microbialDivId)==null)
	{
	  /*  alert("as"); */
		var divElement =opener.document.createElement("div");
		var ide="selectValuemapping";
		
		divElement.id=microbialDivId;
		divElement.innerHTML=innerHtmlVar;
		if(opener.document.getElementById(idddd)!=null)
		opener.document.getElementById(idddd).innerHTML="";
		
		tdElement.appendChild(divElement);
		//alert("onload"+valuee);
		
opener.document.getElementsByName(ide)[0].value+=valuee+"@"

		 opener.document.getElementsByName(hyperlinktblsesInSession)[0].value+=reqDno+"$$"+testParacode+"$$"+hyperlinktable+"$$$"; 
		/* counter++;
		alert(counter);
		opener.document.getElementsByName('Counter')[0].value=counter;
		alert("ch"); */
		//opener.document.getElementById(DivId).style.display='none';
			//opener.document.getElementById(DivId).style.display='none';
		// window.close();
		
	}
	else
	{ 
		 //alert("innerHtmlVar"+innerHtmlVar);
		opener.document.getElementById(microbialDivId).innerHTML=innerHtmlVar;
		//adddetails(innerHtmlVar);
		//chandannn
//opener.document.getElementsByName(ide)[0].value+=valuee+"@";
		/* opener.document.getElementsByName(hyperlinktblsesInSession)[counter].value=hyperlinktable; */
 //opener.document.getElementsByName(hyperlinktblsesInSession)[0].value+=reqDno+"$$"+testParacode+"$$"+hyperlinktable+"$$$";
//counter++;
		//alert(counter)
//opener.document.getElementsByName('Counter')[0].value=counter;
		//opener.document.getElementById(DivId).innerHTML='';
		//opener.document.getElementById(DivId).style.display='none';
		//window.close();
		
	}    
 	//alert("ji");
window.close();

}




   if('MODIFYSAVE'== document.getElementsByName('hmode')[0].value)
   {

	     var reqDno1=document.getElementsByName('requisitionDNo')[0].value;
	     var testParacode1=document.getElementsByName('testParaCode')[0].value;

		   
     var innerHtmlVar=document.getElementById('tempDivForInnerHTML').innerHTML;

      var DivId="idd"+reqDno1+"#null#template#"+testParacode1;

       var hyperlinktable=document.getElementsByName('finaltable')[0].value;
  	var hyperlinktblsesInSession="hyperLinkTableSession";
  	
	var tdId="td#"+reqDno1+"#null#template#"+testParacode1; 
	var microbialDivId="divId_"+reqDno1+"#null#template#"+testParacode1;
	
   	var tdElement=opener.document.getElementById(tdId);
   	var valuee=document.getElementsByName('mappinglist2')[0].value;
   	var hyperlinktblsesInSession="hyperLinkTableSession";

   	if(opener.document.getElementById(microbialDivId)==null)
   	{
   		var divElement =opener.document.createElement("div");
   		var ide="selectValuemapping";
   		divElement.id=microbialDivId;
   		divElement.innerHTML=innerHtmlVar;
   		
   		tdElement.appendChild(divElement);
   		opener.document.getElementsByName(ide)[0].value+=valuee+"@";
   		opener.document.getElementById(DivId).style.display='none';
   	 opener.document.getElementsByName(hyperlinktblsesInSession)[0].value+=reqDno1+"$$"+testParacode1+"$$"+hyperlinktable+"$$$";
   		window.close();
   		
   	}
   	else
   	{ 
   		
   		opener.document.getElementById(microbialDivId).innerHTML=innerHtmlVar;
   		opener.document.getElementsByName(ide)[0].value+=valuee+"@";
   		opener.document.getElementById(DivId).style.display='none';
   	 opener.document.getElementsByName(hyperlinktblsesInSession)[0].value+=reqDno1+"$$"+testParacode1+"$$"+hyperlinktable+"$$$";
   	window.close();
   		
   	}    
    	//alert("ji");
   	/* self.close(); */

   }
}

</script>


</script>

<script type="text/javascript">



function cancelFunc()
{
	window.close();
	}

function ValidateSameCrNo()
{
	document.getElementById('pagbtn').style.display="";
	
	for(var a=0;a<document.getElementsByName('chkSamplePatient').length;a++)
	{
		var r="r#"+a;
		var s="s#"+a;
		var is="ir#"+a;
		
	if(document.getElementsByName('chkSamplePatient')[a].checked==false)
		{
		
		if(document.getElementById(r).checked==true || document.getElementById(s).checked==true || document.getElementById(is).checked==true)
        { 
			document.getElementById(r).checked=false;
			document.getElementById(s).checked=false;
			document.getElementById(is).checked=false;
			}
		}
	else
		{
		document.getElementsByName('counterrr')[0].value="";
		}
	}

	}


function ValidateSameCrNo12(thiss,antibioticcode,result,remarks)
{
	//alert("antibioticcode"+antibioticcode);
	document.getElementById('pagbtn').style.display="";
	
	 for(var a=0;a<document.getElementsByName('chkSamplePatient').length;a++)
	{
		var r="r#"+a;
		var s="s#"+a;
		var is="ir#"+a;
		
	if(document.getElementsByName('chkSamplePatient')[a].checked==false)
		{
		
		if(document.getElementById(r).checked==true || document.getElementById(s).checked==true || document.getElementById(is).checked==true)
        { 
			document.getElementById(r).checked=false;
			document.getElementById(s).checked=false;
			document.getElementById(is).checked=false;
			}
		}
	} 

	}
	
function ValidateSameCrNo1()
{
	//document.getElementById('pagbtn').style.display="";
	
	for(var a=0;a<document.getElementsByName('chkSamplePatient').length;a++)
	{
		var r="r#"+a;
		var s="s#"+a;
		var is="ir#"+a;
		
	if(document.getElementsByName('chkSamplePatient')[a].checked==false)
		{
		
		if(document.getElementById(r).checked==true || document.getElementById(s).checked==true || document.getElementById(is).checked==true)
        { 
			document.getElementById(r).checked=false;
			document.getElementById(s).checked=false;
			document.getElementById(is).checked=false;
			}
		}
	}

	}

function ValidateSameCrNo1()
{
	document.getElementById('pagbtn1').style.display="";
	
	for(var a=0;a<document.getElementsByName('chkSamplePatient').length;a++)
	{
		var r="r#"+a;
		var s="s#"+a;
		var is="ir#"+a;
		
	if(document.getElementsByName('chkSamplePatient')[a].checked==false)
		{
		
		if(document.getElementById(r).checked==true || document.getElementById(s).checked==true || document.getElementById(is).checked==true)
        { 
			document.getElementById(r).checked=false;
			document.getElementById(s).checked=false;
			document.getElementById(is).checked=false;
			}
		}
	}

	}

function ValidateSave()
{

//	alert(counterr);
    //  alert("cc"+document.getElementsByName('counterrr')[0].value)
     if(document.getElementsByName('counterrr')[0].value==null || document.getElementsByName('counterrr')[0].value=="")
         {
           alert("Please Add Data Before Save");
           counterr=0;
           return null;
         }
     
	var flag="false";
	
   var testname="";
   var count=0;
   var check=0;
	for(var a=0;a<document.getElementsByName('chkSamplePatient').length;a++)
		{
	   
		if(document.getElementsByName('chkSamplePatient')[a].checked==true)
			{

			var str=document.getElementsByName('chkSamplePatient')[a].value;
	 		  str = str.replace(/#/g, '@');	
	 		 //alert("str"+str);
               var counter=str.split("@")[2];
               //alert("counter"+counter);
               
             check=1;
			var r="r#"+counter;
			var s="s#"+counter;
			var is="ir#"+counter;
		
                  if(document.getElementById(r).checked==true || document.getElementById(s).checked==true || document.getElementById(is).checked==true)
                      { 
                      flag="true";
                	
                	
                       }
                  else
                      {
                      flag="false";
                       testname+=" "+document.getElementsByName('bioname')[a].value; 
                      
                      }
			}
		
			  


		}
	//alert(flag);
	if(flag=="true")
		{
		  submitForm("SAVE");
		}
	else
		{
		   count=1;
           
		}
		
		if(count==1)
			alert("Please select  value for Selected Antibiotic " +testname);
		if(check==0)
		alert("Please select atleast one checkbox");
	    


		
}

function ValidateSave1()
{
	var flag="false";
	var check=0;
	
   var testname="";
   var count=0;
	for(var a=0;a<document.getElementsByName('chkSamplePatient').length;a++)
		{
		
		if(document.getElementsByName('chkSamplePatient')[a].checked==true)
			{
			check=1;
			var r="r#"+a;
			var s="s#"+a;
			var is="ir#"+a;
			
                  if(document.getElementById(r).checked==true || document.getElementById(s).checked==true || document.getElementById(is).checked==true)
                      { 
                	  flag="true";
                	  
                	
                       }
                  else
                      {
                	  flag="false";
                     
                      testname+=" "+document.getElementsByName('bioname')[a].value;
                      
                      }
			}


		}
		
	if(flag=="true")
	{
		submitForm("MODIFYSAVE");
	}
else
	{
	 count=1; 
	}
	
	if(count==1)
		alert("Please select  value for Selected Antibiotic " +testname);
	if(check==0)
	alert("Please select atleast one checkbox");
	


		
}

function getantibiotic(obj)
{


	if(document.getElementsByName!="-1")
	{
	var organismName1=	document.forms[0].organismCode[document.forms[0].organismCode.selectedIndex].text ;
 // alert(organismName1);
  
  document.forms[0].organismnamee.value = organismName1;
	document.forms[0].organismName.value = organismName1;
	document.forms[0].counterrr.value = 0;
	
	 /* document.getElementsByName('organismName')[0].value=organismName;
	  */
     document.getElementsByName('hmode')[0].value="GETANTIBIOTICDETAILS";
     document.forms[0].submit();
	
	}
	
}


 function chkselect(obj)
{

 var idd=obj.id;
 idd=idd.split("#")[1];
// alert(idd);
  //alert(idd);

	if(obj.checked==true)
		{
	//	alert(obj.id);
		var check=obj.id;
		var re="r#"+idd;
		var ir="ir#"+idd;
		var s="s#"+idd;
	//	alert(s);
		if(check==re)
			{
			
    document.getElementById(ir).checked=false;
    document.getElementById(s).checked=false;
			}
		if(check==s)
		{
		
document.getElementById(ir).checked=false;
document.getElementById(re).checked=false;
		}
		if(check==ir)
		{
		
document.getElementById(re).checked=false;
document.getElementById(s).checked=false;
		}
	} 
		
	

}
 /*
function chkselect1(obj)
{

 var idd=obj.id;
 idd=idd.split("#")[1];
	if(obj.checked==true)
		{
    document.getElementsByName("chkInfo")[idd].checked=false;
    document.getElementsByName("chkInfo2")[idd].checked=false;
		}
	

}
function chkselect2(obj)
{

 var idd=obj.id;
 idd=idd.split("#")[1];
	if(obj.checked==true)
		{
	//	alert(idd);
    document.getElementsByName("chkInfo")[idd].checked=false;
    document.getElementsByName("chkInfo1")[idd].checked=false;
		}
	

} */


</script>

<body onload="call()">
<html:form action="/fungusprocesss">
	<html:hidden name="invFungusProcessFB" property="hmode" /> 
		<html:hidden name="invFungusProcessFB" property="counterrr" /> 
	
	<html:hidden name="invFungusProcessFB" property="growthname" /> 
	
	
	<html:hidden name="invFungusProcessFB" property="organismnamee" />  
	     <html:hidden name="invFungusProcessFB" property="currentPage" />
	  <html:hidden name="invFungusProcessFB" property="showStatus" />
	  <html:hidden name="invFungusProcessFB" property="requisitionDNo" />
	  <html:hidden name="invFungusProcessFB" property="testParaCode" />   
	  <html:hidden name="invFungusProcessFB" property="flag" />
	 <!--  <input type="hidden" name="organismCode1"> -->
	   <div id="firstdiv" >
	    <his:TitleTag name="Investigation Antibiotic Process">
			<%-- <his:insertDateTag/> --%>
		</his:TitleTag>
	
	
	
	
	
	<%
			
			List<ResultEntryVO> lstPatVO11321=(List<ResultEntryVO>)session.getAttribute(InvestigationConfig.LIST_RESULT_ENTRY );
			 
			List<ResultEntryVO> lstPatVO12211=null;
			List<invFungusProcessVO> lstPatVO12221=null;
			/* List<invFungusProcessVO> getantiwholelist=null; */
			List getantiwholelist1=new ArrayList();
			 String reqDno21121=request.getParameter("requisitionDNo");
				String testParaCod21121=request.getParameter("testParaCode");
			 for(int i=0;i<lstPatVO11321.size();i++)
			 {
				ResultEntryVO vo=lstPatVO11321.get(i);
				 List<ResultEntryVO> lstPatVO12212=vo.getResultEntryVOListValidatedBy();
				 if(lstPatVO12212!=null)
				 {
					 for(int k2=0;k2<lstPatVO12212.size();k2++)
					 {
					 ResultEntryVO vo11=lstPatVO12212.get(k2);
					 if(vo11.getParameterCombo().size()>0)
					 {
						 for(int k1=0;k1<vo11.getParameterCombo().size();k1++)
						 {
					ResultEntryVO vo1=vo11.getParameterCombo().get(k1);
					
					 if((reqDno21121.equals(vo1.getRequisitionDNo())) && (testParaCod21121.equals((vo1.getTestParaMeterCode()))))
							 {
						 lstPatVO12211=vo11.getParameterCombo();
						 
						 
							 }
						 }
					 }
					 
					 if(vo11.getGetfunguslistdynamic().size()>0)
						{
						 for(int k1=0;k1<vo11.getGetfunguslistdynamic().size();k1++)
						 {
						 invFungusProcessVO vo122=vo11.getGetfunguslistdynamic().get(k1);
					
						 if((reqDno21121.equals(vo122.getReqDno())) && (testParaCod21121.equals((vo122.getTestParaCode()))))
						 {
							 lstPatVO12221=vo11.getGetfunguslistdynamic();
							 
							 for(int i1=0;i1<lstPatVO12221.size();i1++)
							 {
								 invFungusProcessVO getantilist=lstPatVO12221.get(i1);
								 if((reqDno21121.equals(getantilist.getReqDno())) && (testParaCod21121.equals((getantilist.getTestParaCode()))))
								 {
									 getantiwholelist1.add(getantilist);
								 }
							 }
							 
							// session.setAttribute(InvestigationConfig.ANTIBIOTIC_COMBO,getantiwholelist1);
						 }
						 }
						 
					 
					
					 }
					 
				 }
				 }
			 }
			 
		
			
			// System.out.println("lstsize"+lstPatVO11.size());
			 
//			System.out.println("hyperlink size:"+lstPatVO12.size());    
			//String organismnamee="";
			lstPatVO12221=null;
			if(lstPatVO12221==null)
			{
			
			 %>
	
	
	
	
		     	<%-- <logic:empty name="<%=InvestigationConfig.HYPER_LINK_DATA_LIST %>">  --%>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			    
			    
			     <tr>
			    <td width="25%" class="tdfont">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								Fungus Name
						 </font>
						<html:hidden name="invFungusProcessFB" property="organismName" />
				     </div>
			      </td>
			      <td width="25%" class="tdfont">
			      <logic:present name="<%=InvestigationConfig.ORGANISM_COMBO%>">
			      <div align="left">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="invFungusProcessFB" property="organismCode"    tabindex="1"  onchange="getantibiotic(this)">
				       					<html:option value="-1">Select value</html:option>	
				 	   					<html:options collection="<%=InvestigationConfig.ORGANISM_COMBO%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				
				      				
				      				<%-- <html:hidden name="invFungusProcessFB" property="organismCode"/> --%>
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
								Growth
						 </font>
						<html:hidden name="invFungusProcessFB" property="organismName" />
				     </div>
			      </td>
			      <td width="25%" class="tdfont">
			      <logic:present name="<%=InvestigationConfig.ORGANISM_COMBO%>">
			      <div align="left">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="invFungusProcessFB" property="growthCode"    tabindex="1"  onchange="">
				       					<html:option value="-1">Select value</html:option>
				       					<html:option value="1">Doubtful significance</html:option>
				       					<html:option value="2">Heavy</html:option>
				       					<html:option value="3">Moderate</html:option>
				       					<html:option value="4">Non-Significant</html:option>
				       					<html:option value="5">Predominant Significance</html:option>
				       					<html:option value="6">Significant</html:option>	
				       					<html:option value="7">Scanty</html:option>
				 	   					<%-- <html:options collection="<%=InvestigationConfig.ORGANISM_COMBO%>" property="value" labelProperty="label"/> --%>
				      				</html:select>
				      				
				      				
				      				<%-- <html:hidden name="invFungusProcessFB" property="organismCode"/> --%>
				      				</span>
				  </div>
				 
				  </logic:present>
			     </td>
			     
			     </table>
			     
<%-- 			   <his:ButtonToolBarTag>
     			  <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelFunc() " onclick="cancelFunc()" tabindex="1">
			   
	             </his:ButtonToolBarTag>
			  --%>
			    
			<%-- </logic:empty> --%>
			<%} %>
			 
			    <%
			    boolean flag=false; %>
			   	  <logic:equal name="invFungusProcessFB" property="showStatus" value="0"> 
  	 <%
				//Pagination Logic
					PaginationFB fbPage= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage);
					fbPage.setCurrentPage(((invFungusProcessFB)request.getAttribute("invFungusProcessFB")).getCurrentPage());
					fbPage.setObjArrName(InvestigationConfig.ANTIBIOTIC_COMBO);
					fbPage.setAppendInTitle("List ");
					int maxRecord=100;
					fbPage.setMaxRecords(maxRecord);
				 
				 %>
				 
			
				 		<his:PaginationTag name="fbPagination"></his:PaginationTag>
			
			<% flag=true; %>
			<table   width="100%" bgcolor="#EBEBEB"   >
			<tr>
					<td width="10%" align="left"  >	
						<b> <font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif">
							
							<%
							 List<invFungusProcessVO> preffered=(List<invFungusProcessVO>)session.getAttribute(InvestigationConfig.ANTIBIOTIC_COMBO_PREFFREDED);
                              String prefferedd="";
							 if(preffered!=null && preffered.size()>0)
							 {
								 for(int i=0;i<preffered.size();i++)
								 {
								 invFungusProcessVO vo=preffered.get(i);
								 prefferedd+=vo.getAntibioticCode()+"#";
								 }	 
							 
							 }
							 
							
							%>
							
					<input type="checkbox" id="selectAllCheckbox" onclick="preferred('<%=prefferedd%>',this)" /> </font>
					
					Select Preferred
					
					<!-- <input type="checkbox"  onclick="preferred('1',this)" /> </font> -->
	                  </b>
					</td>
					<td width="20%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						Antibiotic Name </font></b>
					</td>
					<td width="10%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					Sensitive	</font></b>
					</td>
					<td width="10%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					Moderate Sensitive	</font></b>
					</td>
					<td width="10%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					Resistant	</font></b>
					</td>
					
					<td width="20%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					Remarks	</font></b>
					</td>
					
				</tr>
			</table>
			 <input type="hidden"  name="checkselectedvalue" value=<%=-1+"#"+0 %>>
			<logic:notEmpty name="<%=InvestigationConfig.ANTIBIOTIC_COMBO %>">
			<table   width="100%" bgcolor="#EBEBEB" cellspacing="0" style="border-spacing: 0;">
					<%
					String anticode="";
					 List<invFungusProcessVO> lstPatVO=(List<invFungusProcessVO>)session.getAttribute(InvestigationConfig.ANTIBIOTIC_COMBO);
					 int i,size=0;
			 		if(lstPatVO!=null && lstPatVO.size()>0 )
			 			size=lstPatVO.size();
					int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
					int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
						String grpCode="";
						
						for(int j=startIndex;j<=endIndex;j++)
					{
						
							
							String remarks="-";
			 				String checkedd="false";
			 				String checked1="false";
			 				String checked2="false";
			 				String checked3="false";
						//int i=j-startIndex;
						boolean firstTimeTravesall=true;
					if(j<size)
									{
						invFungusProcessVO voPat=lstPatVO.get(j);
						String	chkname=voPat.getAntibioticName();
						String  chkVal=voPat.getAntibioticCode()+"#"+chkname+"#"+j;
					    String org="";
						//String labCode=voPat.getLabCode();

					 if(firstTimeTravesall)
			 			{
						 
			 		Map<String,List<antibioticprocessVO>> mpBilled= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_fungus_in_sessionhyperlinkdata);
			 		String reqnoo=(String)session.getAttribute("reqn1o");
	 				
							                    if(mpBilled!=null)
							                    {
							          Iterator itr=mpBilled.keySet().iterator();
							          Iterator itr1=mpBilled.keySet().iterator();
							          while(itr1.hasNext())
								 		{
								 			String organismm="";
								 			String organisgm1=(String)itr1.next();
								 			organismm=organisgm1;
								 			organisgm1=organisgm1.split("#")[1];
								 			String orgnaismcode1=(String)request.getAttribute("organismcodee");
								 			session.setAttribute("organismcodee",orgnaismcode1);
								 			if(organisgm1.equalsIgnoreCase(orgnaismcode1))
								 			{
								 				org=organisgm1+"#"+"true";
								 				break;
								 			}
								 			else
								 			{
								 				org=orgnaismcode1+"#"+"truee";
								 			}
								 		}
							          
							 			String orgnaismcode12=(String)request.getAttribute("organismcodee");

							 			
							                    if(org.equals(""))
							 				org=orgnaismcode12+"#"+"false";

							                String arr[]=org.split("#");    
							          int sizee = mpBilled.keySet().size();
								 		int index=0;
								 		boolean sameReqNo=false;
								 		int j1=0;
								 		int counter=0;
								 		
								 			
								 			String organisgm=orgnaismcode12;
							            
								 			String orgnaismcode=(String)request.getAttribute("organismcodee");
                                                boolean flagg=false;
								 			if(arr[0].equalsIgnoreCase(orgnaismcode) && arr[1].equals("true"))
								 			{
								 			flagg=true;
								 			int count=1;
								 			List<antibioticprocessVO> lstVOSample=mpBilled.get(reqnoo+"#"+organisgm);
								 			//int rowSpanSize=lstVOSample.size();
								 			
								 			if(lstVOSample!=null && lstVOSample.size()>=0)
								 			for(int k=0;k<lstVOSample.size();k++)
								 			{
								 				
								 				System.out.println("1hello======");
								 				antibioticprocessVO voo=lstVOSample.get(k);
								 				
								 				if(voPat.getAntibioticCode().equalsIgnoreCase(voo.getAntibioticcode()) && voo.getRequisitionDNo().equals(reqnoo))
								 				{
								 					
								 					System.out.println("hello111======");
								 					
								 					checkedd="true";
								 					
								 					
								 					remarks= voo.getRemark();
									 				
								 					
								 					if( voo.getResult().equalsIgnoreCase("1"))
									 				{
								 						
									 					checked1="true";
									 				}
									 				

									 				
									 				if(voo.getResult().equalsIgnoreCase("2"))
									 				{
									 					checked2="true";
									 				}
									 				
									 				
									 				

									 				
									 				if( voo.getResult().equalsIgnoreCase("3"))
									 				{
									 					checked3="true";
									 				}
									 				
								 				}
								 				else
								 				{
								 					
								 				}
								 				
								 			}
								 				
								 			}	
								 				
								 				%>
								 		        
								 		        <% 
								 		       
								 		       if(arr[0].equalsIgnoreCase(orgnaismcode) )
									 			{
								 		        	counter++;
								 		        	
								 		        	String classname="";
								 		        	if(j % 2 == 0)
								 		        		classname="even disable";
								 		        	else
								 		        		classname="odd disable";
								 		        	%>
								 		      
								 		        	
								 		        	
								 				<tr class='<%=classname%>' >
						<td width="10%" align="left"  >
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
								<% 
								anticode+=voPat.getAntibioticCode()+"#";
								
								if(checkedd.equalsIgnoreCase("true")){ %>
							<input type="checkbox" class="jpCheckbox chanks" name="chkSamplePatient" id="chckbox1" value='<%=chkVal%>' checked="<%=checkedd%>" onclick="ValidateSameCrNo(this)" >
						   <%}else{ %>
						   <input type="checkbox" class="jpCheckbox" name="chkSamplePatient" id="chckbox1" value='<%=chkVal%>' onclick="ValidateSameCrNo(this)" >
						   <%} %>
							<input type="hidden"  name="chkSamplePatientName"  value='<%=chkname%>'  >
							</font>
						</td>
						<%checkedd="false"; %>
						<td width="20%"  align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getAntibioticName() %></font> 
						 	<input type="hidden" name="bioname" value="<%=voPat.getAntibioticName() %>">
						 
				  		</td>
				  		
				  		<td width="10%" align="left"  >
				  		 
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
							<% if(checked2.equalsIgnoreCase("true")){ %>
						<input type="checkbox" checked="checked" id="s#<%=j %>" name="chkInfo" value="2" onclick="chkselect(this)"/></font>
						 	<%}else{ %>
						 	<input type="checkbox"  id="s#<%=j %>" name="chkInfo" value="2" onclick="chkselect(this)"/></font>
						 	<%} %>
						 	<%-- <html:checkbox  id="<%=j %>" property="chkInfo" name="invFungusProcessFB" value="1" onclick="chkselect(this)"></html:checkbox> --%>
						 	
						  
				  		
				  		</td>
				  		<td  width="10%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<!-- 	<input type="radio"  name="patientWise"  onclick="getDetails(this)" checked="checked" value="P" /> -->
						<% if(checked3.equalsIgnoreCase("true")){ %>
						<input type="checkbox" checked="checked" id="ir#<%=j %>"  name="chkInfo" value="3" onclick="chkselect(this)"/></font>
						    <%}else{ %>
						    <input type="checkbox"  id="ir#<%=j %>"  name="chkInfo" value="3" onclick="chkselect(this)"/></font>
						    <%} %>
				  		</td>
				  		 <td  width="10%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<% if(checked1.equalsIgnoreCase("true")){ %>
						  	<input type="checkbox" checked="checked" id="r#<%=j %>"  name="chkInfo" value="1" onclick="chkselect(this)"/></font>
						 
	   <%}else{ %>
	   				  	<input type="checkbox"  id="r#<%=j %>"  name="chkInfo" value="1" onclick="chkselect(this)"/></font>
	   
	   <%} %>
				  		</td>
				  		 <td   width="20%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						  	<input type="textbox" id="remarks#<%=j %>" value="<%=remarks %>"  name="remarks" /></font>
						 
				  		</td>
				  		
				  		
					</tr>
					
					<%} %>
								 				
								 				
								 				<%
								 			
										  		checkedd="false";
										  		checked1="false";
										  		checked2="false";
										  		checked3="false";
										  		remarks="";
										  	
								 		%>
								 			
								 			
								 		
					
								 			
								 			<%
								 			
								 		
							                    }
							                    else
							                    {
							                    	
								 			%>
						 
					
				  <% 
								 		        	String classname="";
								 		        	if(j % 2 == 0)
								 		        		classname="even disable";
								 		        	else
								 		        		classname="odd disable";
								 		        	%>
					 
					
					<tr class="<%=classname%>">
						<td width="10%" align="left"  >
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<input type="checkbox" class="jpCheckbox" name="chkSamplePatient" id="chckbox1" value='<%=chkVal%>' onclick="ValidateSameCrNo12(this,'0','0','0')" >
							<input type="hidden"  name="chkSamplePatientName"  value='<%=chkname%>'  >
							</font>
						</td>
						<td width="20%"  align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getAntibioticName() %></font> 
						 	<input type="hidden" name="bioname" value="<%=voPat.getAntibioticName() %>">
						 
				  		</td>
				  		
				  		<td width="10%" align="left"  >
				  		 
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<input type="checkbox" id="s#<%=j %>" name="chkInfo" value="2" onclick="chkselect(this)"/></font>
						 	
						 	<%-- <html:checkbox  id="<%=j %>" property="chkInfo" name="invFungusProcessFB" value="1" onclick="chkselect(this)"></html:checkbox> --%>
						 	
						  
				  		
				  		</td>
				  		<td  width="10%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<!-- 	<input type="radio"  name="patientWise"  onclick="getDetails(this)" checked="checked" value="P" /> -->
						<input type="checkbox" id="ir#<%=j %>"  name="chkInfo" value="3" onclick="chkselect(this)"/></font>
						   
				  		</td>
				  		 <td  width="10%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						  	<input type="checkbox" id="r#<%=j %>"  name="chkInfo" value="1" onclick="chkselect(this)"/></font>
						 
				  		</td>
				  		 <td   width="20%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						  	<input type="textbox" id="remarks#<%=j %>"  name="remarks" /></font>
						 
				  		</td>
				  		
					</tr>
				
		
					<%}}}  }%>
		<logic:notEmpty name="<%=InvestigationConfig.list_fungus_in_sessionhyperlinkdata%>">
			
			   		<his:ButtonToolBarTag>
	<img class="button" src='<his:path src="/hisglobal/images/Add.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) adddetails('1')" onclick="adddetails('1')" tabindex="1">
     			<img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) ValidateSave()" onclick="ValidateSave()" tabindex="1">
			    <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelFunc() " onclick="cancelFunc()" tabindex="1">
			   
	             </his:ButtonToolBarTag>
	             </logic:notEmpty>
			</table>
		    <div id="pagbtn" style="display: none">		
		    	<logic:empty name="<%=InvestigationConfig.list_fungus_in_sessionhyperlinkdata%>">
		
	<his:ButtonToolBarTag>
	<img class="button" src='<his:path src="/hisglobal/images/Add.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) adddetails('1')" onclick="adddetails('1')" tabindex="1">
     			<img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) ValidateSave()" onclick="ValidateSave()" tabindex="1">
			    <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelFunc() " onclick="cancelFunc()" tabindex="1">
			   
	             </his:ButtonToolBarTag>
	             
	             </logic:empty>
			</div>    
		
		
			</logic:notEmpty>
			<logic:empty name="<%=InvestigationConfig.ANTIBIOTIC_COMBO%>">
				<center>
				<font color="red" size="4">
				No Record Found</font></center>
			</logic:empty>
			
			 </logic:equal>
			 
			 <!-- chanduuuuu -->
			 
			<%--  <his:TitleTag name="Selected Data List">
			<his:insertDateTag/>
		</his:TitleTag>
		
			  <div class="subDivStyle" id="reqformss">
                               <!-- <table width="100%" border="1" id="tbll"> -->
                               <table  id="tbll"  width="100%" bgcolor="#EBEBEB" cellspacing="0" style="border-spacing: 0;">
                               <tr>
                     
                     <td  align="right"  >
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<b>Organsim Name:</b>
							
							</font>
						</td>
						<td align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	Organsim1</font> 
						 
						 
				  		</td>
				  		
				  		</tr>
				  		<tr>
				  		 <td  align="right"  >
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<b>Antibiotic Name</b>
							
							</font>
						</td>
						<td align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<b>Result</b></font> 
						 
						 
				  		</td>
				  		
				  		</tr>
				  		
				  		<tr>
				  		
				  		 <td  align="right"  >
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							Antibiotic1 
							
							</font>
						</td>
						<td align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	S</font> 
						 
						 
				  		</td>
				  		
                      </tr>
                    
                               </table>                 
                 </div> --%>
			 
			 <!-- chanduuuuu -->
			 
			 
			 <!-- //checkdata --> 
			 
			<%
			
			List<ResultEntryVO> lstPatVO1132=(List<ResultEntryVO>)session.getAttribute(InvestigationConfig.LIST_RESULT_ENTRY );
			 
			List<ResultEntryVO> lstPatVO122=null;
			List<invFungusProcessVO> lstPatVO1222=null;
			/* List<invFungusProcessVO> getantiwholelist=null; */
			List getantiwholelist=new ArrayList();
			 String reqDno2112=request.getParameter("requisitionDNo");
				String testParaCod2112=request.getParameter("testParaCode");
			 for(int i=0;i<lstPatVO1132.size();i++)
			 {
				ResultEntryVO vo=lstPatVO1132.get(i);
				 List<ResultEntryVO> lstPatVO12212=vo.getResultEntryVOListValidatedBy();
				 if(lstPatVO12212!=null)
				 {
					 for(int i11=0;i11<lstPatVO12212.size();i11++)
					 {
					 ResultEntryVO vo11=lstPatVO12212.get(i11);
					 if(vo11.getParameterCombo().size()>0)
					 {
						 
						 for(int k3=0;k3<vo11.getParameterCombo().size();k3++)
						 {
					ResultEntryVO vo1=vo11.getParameterCombo().get(k3);
					
					
					 if((reqDno2112.equals(vo1.getRequisitionDNo())) && (testParaCod2112.equals((vo1.getTestParaMeterCode()))))
							 {
						 lstPatVO122=vo11.getParameterCombo();
						 
						 
							 }
						 }
					 }
					 
					 
					 if(vo11.getGetfunguslistdynamic().size()>0)
						{
						 for(int k33=0;k33<vo11.getGetfunguslistdynamic().size();k33++)
						 {
							 
						 
						 invFungusProcessVO vo122=vo11.getGetfunguslistdynamic().get(k33);
					
						 if((reqDno2112.equals(vo122.getReqDno())) && (testParaCod2112.equals((vo122.getTestParaCode()))))
						 {
							 lstPatVO1222=vo11.getGetfunguslistdynamic();
							 
							 for(int i1=0;i1<lstPatVO1222.size();i1++)
							 {
								 invFungusProcessVO getantilist=lstPatVO1222.get(i1);
								 if((reqDno2112.equals(getantilist.getReqDno())) && (testParaCod2112.equals((getantilist.getTestParaCode()))))
								 {
									 getantiwholelist.add(getantilist);
								 }
							 }
							 
							 session.setAttribute(InvestigationConfig.ANTIBIOTIC_COMBO,getantiwholelist);
						 }
						 }
					 
						}
					 
					 
				 }
				 
			 }
			 }
			 
		
			
			// System.out.println("lstsize"+lstPatVO11.size());
			 
//			System.out.println("hyperlink size:"+lstPatVO12.size());    
			//String organismnamee="";
			lstPatVO1222=null;
			if(lstPatVO1222!=null)
			{
			
			boolean flag1=false; %>
			   	<logic:present name="<%=InvestigationConfig.HYPER_LINK_DATA_LIST %>">
			   		<logic:notEmpty name="<%=InvestigationConfig.HYPER_LINK_DATA_LIST %>">
			 <%
				//Pagination Logic
					PaginationFB fbPage1= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage1);
					fbPage1.setCurrentPage(((invFungusProcessFB)request.getAttribute("invFungusProcessFB")).getCurrentPage());
					fbPage1.setObjArrName(InvestigationConfig.ANTIBIOTIC_COMBO);
					fbPage1.setAppendInTitle("List ");
					int maxRecord1=50;
					fbPage1.setMaxRecords(maxRecord1);
				 
				 %>
				 
			
				 			<his:PaginationTag name="fbPagination"></his:PaginationTag>
			
			<% flag=true; %>
			<% /* List<ResultEntryVO> lstPatVO12=(List<ResultEntryVO>)session.getAttribute(InvestigationConfig.HYPER_LINK_DATA_LIST ); */
			
			
			List<ResultEntryVO> lstPatVO113=(List<ResultEntryVO>)session.getAttribute(InvestigationConfig.LIST_RESULT_ENTRY );
			 
			List<ResultEntryVO> lstPatVO12=null;
			 
			 String reqDno211=request.getParameter("requisitionDNo");
				String testParaCod211=request.getParameter("testParaCode");
			 for(int i=0;i<lstPatVO113.size();i++)
			 {
				ResultEntryVO vo=lstPatVO113.get(i);
				 List<ResultEntryVO> lstPatVO1221=vo.getResultEntryVOListValidatedBy();
				 if(lstPatVO1221!=null)
				 {
					 for(int k4=0;k4<lstPatVO1221.size();k4++)
					 {
					 ResultEntryVO vo11=lstPatVO1221.get(k4);
					 
					 
					
					for(int k8=0;k8<vo11.getParameterCombo().size();k8++)
					{
						ResultEntryVO vo1=vo11.getParameterCombo().get(k8);
					 if((reqDno211.equals(vo1.getRequisitionDNo())) && (testParaCod211.equals((vo1.getTestParaMeterCode()))))
							 {
						 lstPatVO12=vo11.getParameterCombo();
							 }
					}
					
					 
					 
				 }
				 }
			 }
			 
		
			
			// System.out.println("lstsize"+lstPatVO11.size());
			 
//			System.out.println("hyperlink size:"+lstPatVO12.size());    
			String organismnamee="";
			if(lstPatVO12!=null)
			{
			for(int i=0;i<lstPatVO12.size();i++)
			{	
			
			ResultEntryVO voPat1=lstPatVO12.get(i);
			
			
			String reqDno1=request.getParameter("requisitionDNo");
			String testParaCod1=request.getParameter("testParaCode");
			//System.out.println("reqd values"+testParaCod1);
			
			if(reqDno1.equals(voPat1.getRequisitionDNo()) && testParaCod1.equals(voPat1.getTestParaMeterCode()))
			{
				 
			%>
			<table   width="100%" bgcolor="#EBEBEB"   >
			<tr>
					<!-- <td  class="tdfonthead" width="50%" align="right"  >	
					
						<b> <font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif">
					Fungus Name </font>
	                    </b>
					</td> -->
					<% 
					   if(voPat1.getOrganismname()!=null)
					   {
					if(organismnamee.indexOf(voPat1.getOrganismname())!=-1) {%>
							<%}else{ %>
					<td class="tdfonthead" width="16%">
		 				<div align="center">
		 				<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				 Fungus Name
		 					</font></b>
		 				</div>
		 			</td>  
		 			<td  class="tdfonthead" width="16%">
		 				<div align="center">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
							
							
		 				 <%=voPat1.getOrganismname()  %><%
		 				organismnamee+="#"+voPat1.getOrganismname();
							} %>
		 			<input type="hidden" name="organismCode" value="<%=voPat1.getOrganismcode() %>">
		 			
		 			<input type="hidden" name="organismName" value="<%=voPat1.getOrganismname() %>">
		 					</font>
		 				</div>
		 			</td> 
		 		<% }break;}}}%>
					<%-- <td class="tdfonthead" width="50%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat1.getOrganismname()  %></font> 
						 
				  		</td>
					<td width="50%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	</font> 
						 	<input type="hidden" name="organismcode" value="<%=voPat1.getOrganismcode() %>">
						 
				  		</td> --%>
			</tr>
			</table>
			
			
			<% flag=true; %>
			<table   width="100%" bgcolor="#EBEBEB"   >
			<tr>
					<td width="3%" align="left"  >	
						<b> <font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif">
					<input type="checkbox" id="selectAllCheckbox" onclick="allSelectedd1()" /> </font>
	                  </b>
					</td>
					<td width="20%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						Antibiotic Name </font></b>
					</td>
					<td width="15%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					Sensitive	</font></b>
					</td>
					<td width="10%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					Moderate Sensitive	</font></b>
					</td>
					<td width="15%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					Resistant	</font></b>
					</td>
					
				</tr>
			</table>
			 <input type="hidden"  name="checkselectedvalue" value=<%=-1+"#"+0 %>>
			
			  <div id="pagbtn1" style="">			
	<his:ButtonToolBarTag>
     			<img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) ValidateSave1()" onclick="ValidateSave1()" tabindex="1">
			    <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelFunc() " onclick="cancelFunc()" tabindex="1">
			   
	             </his:ButtonToolBarTag>
			</div>    
			<logic:empty name="<%=InvestigationConfig.ANTIBIOTIC_COMBO%>">
				<center>
				<font color="red" size="4">
				<bean:message key="noRecord"/></font></center>
			</logic:empty>
			
			 </logic:notEmpty>
			 </logic:present>
			 <%} %>
			 
		 </div>
			 			<logic:notEmpty name="<%=InvestigationConfig.list_fungus_in_sessionhyperlinkdata%>">
			 			</
			<br/>
	 		  <his:TitleTag name="Selected Data:">
			<%-- <his:insertDateTag/> --%>
		</his:TitleTag>
	 			<br/>
			 
<%


Map<String,List<antibioticprocessVO>> mpBilled= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_fungus_in_sessionhyperlinkdata);
		
                    
          Iterator itr=mpBilled.keySet().iterator();
	 		int index=0;
	 		boolean sameReqNo=false;
	 		int j=0;
	 		String reqnoo=(String)session.getAttribute("reqn1o");
				
	 		while(itr.hasNext())
	 		{
	 			
	 			String organisgm=(String)itr.next();
                     int count=1;
	 			List<antibioticprocessVO> lstVOSample=mpBilled.get(organisgm);
	 			organisgm=organisgm.split("#")[1];

	 			int rowSpanSize=lstVOSample.size();

	 			
	 			for(int k=0;k<lstVOSample.size();k++)
	 			{
	 				antibioticprocessVO voo=lstVOSample.get(k);
	 				
	 				
	 				if(reqnoo.equals(voo.getRequisitionDNo()) )
	 				{
	 					String result="";
	 				  
	 			%>
	 			      
	 			
	 			          <% if(count==1) {%>
	 			          
	 			          <script>
	 			         counterr=1;
	 			          </script>
	 			           <div style="border:thin dotted red;">
	 		
	 			
	 			<table   width="100%" bgcolor="#EBEBEB"   >
			<tr>
					<td width="30%" align="left"  >	
						<b> <font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif">
					Fungus Name </font>
	                  </b>
					</td>
					
					
					<td width="30%" align="left"  >
						<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					<%= voo.getOrganismname() %>	</font>
					</td>
					<td width="40%" align="left"  >
					
					</td>
					</tr>
					
					<tr>
					<td width="30%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					Growth	</font></b>
					</td>
					<td width="30%" align="left"  >
					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					<%= voo.getGrowthname() %>	</font>
					</td>
					<td width="40%" align="left"  >
					</td>
					
				</tr>
				
				
					<tr>
				<%-- 	<td width="30%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					Preffered	</font></b>
					</td>
					<td width="30%" align="left"  >
					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					<%= voo.getGrowthname() %>	</font>
					</td> --%>
					<td width="40%" align="left"  >
					</td>
					
				</tr>
				
				
			</table>
			<table   width="100%" bgcolor="#EBEBEB"   >
			<tr>
					<td width="30%" align="left"  >	
						<b> <font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif">
					Antibiotic Name </font>
	                  </b>
					</td>
					
					
					<td width="30%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					Result	</font></b>
					</td>
					
					<td width="40%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					Remarks	</font></b>
					</td>
					
				</tr>
			</table>
	 		
	 			<%
	 			count=0;
	 			
	 			
	 			          } 
	 			          
	 			          
	 			           result=voo.getResult();
	 			
	 			if(result.equals("1"))
	 			{
	 				result="R";
	 			}
	 			
	 			if(result.equals("2"))
	 			{
	 				result="S";
	 			}
	 			
	 			if(result.equals("3"))
	 			{
	 				result="MS";
	 			}
	 			%>
	 			
			
	 			<table   width="100%" bgcolor="#EBEBEB"   >
			<tr>
					
					<td width="30%" align="left"   >
						<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<%=voo.getAntibioticname() %> </font>
					</td>
					
					
					<td width="30%" align="left"  >
						<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					<%=result %>	</font>
					</td>
					
						<td width="40%" align="left"  >
						<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					<%=voo.getRemark() %>	</font>	
					</td>
				</tr>
			</table>
			
	 			
	 			
	 				
	 		<%     }
	 			}
	 			
	 			%>
	 			
	 			</div>
	 			<br/>
	 			<%
	 		}	
			
			
			%>
		</logic:notEmpty>
	 		
			 <!-- end checkdata -->
			 
			
		<%-- 	 <!-- chandan  Modify -->
			     <%boolean flag1=false; %>
			   	<logic:present name="<%=InvestigationConfig.HYPER_LINK_DATA_LIST %>">
			   		<logic:notEmpty name="<%=InvestigationConfig.HYPER_LINK_DATA_LIST %>">
     	 <%
				//Pagination Logic
					PaginationFB fbPage= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage);
					fbPage.setCurrentPage(((invFungusProcessFB)request.getAttribute("invFungusProcessFB")).getCurrentPage());
					fbPage.setObjArrName(InvestigationConfig.HYPER_LINK_DATA_LIST );
					fbPage.setAppendInTitle("List ");
					int maxRecord=10;
					fbPage.setMaxRecords(maxRecord);
				 
				 %>
				 
			
				 		<his:PaginationTag name="fbPagination"></his:PaginationTag>
			
			<% flag=true; %>
			<% List<ResultEntryVO> lstPatVO1=(List<ResultEntryVO>)session.getAttribute(InvestigationConfig.HYPER_LINK_DATA_LIST );
			
			 
			// System.out.println("lstsize"+lstPatVO11.size());
			 
			System.out.println("hyperlink size:"+lstPatVO1.size());    
			String organismnamee="";
			for(int i=0;i<lstPatVO1.size();i++)
			{	
			
			ResultEntryVO voPat1=lstPatVO1.get(i);
			
			
			String reqDno1=request.getParameter("requisitionDNo");
			String testParaCod1=request.getParameter("testParaCode");
			//System.out.println("reqd values"+testParaCod1);
			
			if(reqDno1.equals(voPat1.getRequisitionDNo()) && testParaCod1.equals(voPat1.getTestParaMeterCode()))
			{
				 
			%>
			<table   width="100%" bgcolor="#EBEBEB"   >
			<tr>
					<!-- <td  class="tdfonthead" width="50%" align="right"  >	
					
						<b> <font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif">
					Fungus Name </font>
	                    </b>
					</td> -->
					<% if(organismnamee.indexOf(voPat1.getOrganismname())!=-1) {%>
							<%}else{ %>
					<td class="tdfonthead" width="16%">
		 				<div align="center">
		 				<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				 Fungus Name
		 					</font></b>
		 				</div>
		 			</td>  
		 			<td  class="tdfonthead" width="16%">
		 				<div align="center">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
							
							
		 				 <%=voPat1.getOrganismname()  %><%
		 				organismnamee+="#"+voPat1.getOrganismname();
							} %>
		 			<input type="hidden" name="organismCode" value="<%=voPat1.getOrganismcode() %>">
		 			
		 			<input type="hidden" name="organismName" value="<%=voPat1.getOrganismname() %>">
		 					</font>
		 				</div>
		 			</td> 
		 		<% }}%>
					<td class="tdfonthead" width="50%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat1.getOrganismname()  %></font> 
						 
				  		</td>
					<td width="50%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	</font> 
						 	<input type="hidden" name="organismcode" value="<%=voPat1.getOrganismcode() %>">
						 
				  		</td>
			</tr>
			</table>
			
			<table   width="100%" bgcolor="#EBEBEB"   >
			<tr>
					<td width="3%" align="left"  >	
						<b> <font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif">
					<input type="checkbox" id="selectAllCheckbox1" onclick="allSelected()" /> </font>
	                  </b>
					</td>
					<td width="20%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						Antibiotic Name </font></b>
					</td>
					<td width="15%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					Sensitive	</font></b>
					</td>
					<td width="10%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					Intermediate Sensitive	</font></b>
					</td>
					<td width="15%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						Resistant</font></b>
					</td>
					
				</tr>
			</table>
			
			
			<table   width="100%" bgcolor="#EBEBEB" cellspacing="0" style="border-spacing: 0;">
					<%
					
					 List<ResultEntryVO> lstPatVO=(List<ResultEntryVO>)session.getAttribute(InvestigationConfig.HYPER_LINK_DATA_LIST );
					
					 List<invFungusProcessVO> lstPatVO11=(List<invFungusProcessVO>)session.getAttribute(InvestigationConfig.ANTIBIOTIC_COMBO);
					
					 int i,size=0;
			 		if(lstPatVO!=null && lstPatVO.size()>0 )
			 			size=lstPatVO.size();
					int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
					int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
						String grpCode="";
						int count =0;
						for(int j=startIndex;j<=endIndex;j++)
					{
						//int i=j-startIndex;
						boolean firstTimeTravesall=true;
						boolean matching=true;
					if(j<size)
									{
						ResultEntryVO voPat=lstPatVO.get(j);
						String	chkname=voPat.getAntibioticname();
						String  chkVal=voPat.getAntibioticcode()+"#"+chkname;
					
					String reqDno=request.getParameter("requisitionDNo");
					String testParaCod=request.getParameter("testParaCode");
					System.out.println("reqd values"+testParaCod);
					if(reqDno.equals(voPat.getRequisitionDNo()) && testParaCod.equals(voPat.getTestParaMeterCode()))
					{
						
						%>

					

	
					
					
					<tr>
						<td width="3%" align="left"  >
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<input type="checkbox" class="jpCheckbox" name="chkSamplePatient" id="chckbox1" value='<%=chkVal%>' onclick="ValidateSameCrNo1(this)" >
							<input type="hidden"  name="chkSamplePatientName"  value='<%=chkname%>'  >
							</font>
						</td>
						<td width="20%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getAntibioticname() %></font> 
						 		<input type="hidden" name="bioname" value="<%=voPat.getAntibioticname() %>">
						
				  		</td>
				  		
				  		<td width="15%" align="left"  >
				  		 
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
							
							<%
							String  val="-1";
							System.out.println("chandan:"+voPat.getDiagnosiscode());
							%>
							<%if (voPat.getDiagnosiscode().equals("1"))
							{
								val="1";
								
							}	
							else
							{;}
							%>
							<%if (voPat.getDiagnosiscode().equals("2"))
							{
								val="2";
							}	
							else
							{;}
							%>
							<%if (voPat.getDiagnosiscode().equals("3"))
							{
								val="3";
							}	
							else
							{;}
							%>
							
							<input type="hidden"  name="checkselectedvalue" value="<%=val+"#"+count %>">
				         <input type="checkbox" id="s#<%=count %>" name="chkInfo" value="2" onclick="chkselect(this)"/>
						
						 	<html:checkbox  id="<%=j %>" property="chkInfo" name="invFungusProcessFB" value="1" onclick="chkselect(this)"></html:checkbox>
						 	</font>
						  
				  		
				  		</td>
				  		<td width="10%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<!-- 	<input type="radio"  name="patientWise"  onclick="getDetails(this)" checked="checked" value="P" /> -->
						 <input type="checkbox" id="ir#<%=count %>"  name="chkInfo" value="3" onclick="chkselect(this)"/></font>
						  
				  		</td>
				  		 <td  width="15%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<input type="checkbox" id="r#<%=count %>"  name="chkInfo" value="1" onclick="chkselect(this)"/>
						</font>  
				  		</td>
				  		
				  		
					</tr>
			
		
					<%  count++; }
					
									
									}}  %>
		
			   		
			</table>
		    <div id="pagbtn1" style="">			
	<his:ButtonToolBarTag>
     			<img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) ValidateSave1()" onclick="ValidateSave1()" tabindex="1">
			    <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelFunc() " onclick="cancelFunc()" tabindex="1">
			   
	             </his:ButtonToolBarTag>
			</div>    
		 <logic:empty name="<%=InvestigationConfig.HYPER_LINK_DATA_LIST%>">
				<center>
				<font color="red" size="4">
				<bean:message key="noRecord"/></font></center>
			</logic:empty>
			
		
			</logic:notEmpty>
			
			</logic:present> --%>
			 
			
			 

		 <input type="hidden" name="mappinglist2" value=<%=session.getAttribute("mappingList5") %> />
		 <% String tbl=(String)session.getAttribute("tableString"); %>
		 <input type="hidden" name="finaltable" value="<%=tbl %>"	/> 
		<%System.out.println("cun:"+tbl); %>
			 <div id="tempDivForInnerHTML" style="display: none;"><%=session.getAttribute("tableString") %><% String a=(String)session.getAttribute("tableString");
			 String b=(String)session.getAttribute("mappingList5");
			//System.out.println(b); 
			 //System.out.println(a); %>

</div>
			 
		
			    
</html:form>
</body>
</html>  