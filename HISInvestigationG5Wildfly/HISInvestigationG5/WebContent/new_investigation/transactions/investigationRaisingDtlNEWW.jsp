<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="new_investigation.vo.Inv_RequisitionRaisingPatientVO"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>

<%@page import="new_investigation.vo.Inv_ictc_VO"%>
<%@page import="new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB"%>
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

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>

<his:javascript src="/new_investigation/js/appointment.js" />
<his:javascript src="/new_investigation/js/investigationRaisingDtl.js" />
<%-- <his:css src="/hisglobal/css/tab.css" /> --%>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<%-- <his:css src="/hisglobal/css/easyui.css" /> --%>  <!--  calender problem -->
<his:css src="/hisglobal/css/icon.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.autocomplete.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.menu.css" />
<his:css src="/hisglobal/css/jquery-ui.css" />
<his:css src="/hisglobal/css/style.css" />
<his:css src="/hisglobal/css/jquery/bookMark.css" />
<his:css src="/hisglobal/css/jquery/search.css" />
<his:css src="/hisglobal/css/InvBookMark.css" />
<his:css src="/hisglobal/css/invPopup.css" />
<his:css src="/hisglobal/css/invPopup.css" />
<his:css src="/hisglobal/css/newHisRadioStyle.css" />
<his:css src="/hisglobal/css/tutorstyle.css" />
<his:css src="/hisglobal/css/flexcrollstyles.css" />
<his:css src="/hisglobal/css/defaultteriff.css" />
<his:css src="/hisglobal/css/resetteriff.min.css" />
<his:css src="/hisglobal/css/styleteriff.css" />
<!--styleTextBox.css  newHisRadioStyle.css
 -->

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/css-pop-inv.js" />
 <his:javascript src="/hisglobal/js/flexcrolle.js" />

<!-- <script type="text/javascript" src="/HIS/appointment/js/jquery/jquery-2.1.1.min.js"></script>
 -->
<script type="text/javascript"
	src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js"
	djConfig="parseOnLoad: true"> </script>
<script src="media/misc/datepicker1.js" type="text/javascript"></script>


<!-- Added by prashant dataTables dataTables-->
<script type="text/javascript" src="media/dataTables/datatables.min.js"></script>
<link rel="stylesheet" type="text/css" href="media/dataTables/datatables.min.css"/>
<!-- Added by prashant dataTables generic-->
<script type="text/javascript" src="media/dataTables/genericDatatables.js"></script>
<script type="text/javascript" src="media/bootstrap/bootstrap3_typeahead/js/bootstrap3-typeahead.js"></script>

<style>

.tooltipamount {
  position: relative;
  display: inline-block;
  border-bottom: 1px dotted black;
}

.tooltipamount .tooltiptext {
  visibility: hidden;
  width: 250px;
  background-color: black;
  color: #fff;
  text-align: center;
  border-radius: 6px;
  padding: 5px 0;

  /* Position the tooltip */
  position: absolute;
  z-index: 1;
  top: 25px;
  left: -10%;
}

.tooltipamount:hover .tooltiptext {
  visibility: visible;
}

.tooltip {
  position: relative;
  display: inline-block;
 
}

.tooltip .tooltiptext {
  visibility: hidden;
  width: 120px;
  background-color: #555;
  color: #fff;
  text-align: center;
  border-radius: 6px;
  padding: 5px 0;
  position: absolute;
  z-index: 1;
  bottom: 125%;
  left: 50%;
  margin-left: -60px;
  opacity: 0;
  transition: opacity 0.3s;
}

.tooltip .tooltiptext::after {
  content: "";
  position: absolute;
  top: 100%;
  left: 50%;
  margin-left: -5px;
  border-width: 5px;
  border-style: solid;
  border-color: #555 transparent transparent transparent;
}

.tooltip:hover .tooltiptext {
  visibility: visible;
  opacity: 1;
}

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
	width:450px;
	height:200px;
	border:5px solid #000;
	z-index: 9002;
	top:2px;
	margin-top:1px;
}

#popUpDiv5 a {position:relative; top:1px; left:20px;margin:0;}

</style>

<script>

</script>
<script type="text/javascript">

var newarrayappdatalababsed=[];

if(document.getElementsByName('newlabtestcodearray')!=undefined)
	{

	}
else
	{
	newarrayappdatalababsed=[];

	}
<%
String pidoldreqset="";
String gendertypforpid="";
%>
var isamountsuffforgroup="0";
<%
String data="0";
if(session.getAttribute("issuffientamountforgroup")!=null)
{
	data=	(String)session.getAttribute("issuffientamountforgroup");

}

if(data.equals("1"))
{
%>

isamountsuffforgroup=<%=data %>;

<%
session.removeAttribute("issuffientamountforgroup");
}%>
//ashu
var datapidonload="";
var testratess="";
var grptestratess="";


function deleteRow(index,tmpLabCode,tmpTestCode,testgroupcode)
{

	var tableObj=document.getElementById('tableSelectedLabTestIdHide');
  document.getElementsByName('numberOfRow')[0].value=tableObj.rows.length-1;
  	document.getElementsByName('hmode')[0].value="DELETEROW";
 	document.getElementsByName('tmpLabCode')[0].value=tmpLabCode;
  	document.getElementsByName('tmpTestCode')[0].value=tmpTestCode;
  	document.getElementsByName('tmpGroupCode')[0].value=testgroupcode;

  	document.forms[0].submit();


  return true;
}
//chandan comments js fnctn
//JQuery Start
var availableTests;
var availableTestslabwise="";

var availableTestGroups;
         $(function() {
          	 var availableLabs =<%=(String)session.getAttribute(InvestigationConfig.ARRAY_LABNAMES)%>;
        	 availableTests =<%=(String)session.getAttribute(InvestigationConfig.ARRAY_TESTNAMES)%>;

        	 <%if(session.getAttribute(InvestigationConfig.ARRAY_TEST_CODE_WISE_LABWISE)!=null){%>
        	 availableTestslabwise =<%=(String)session.getAttribute(InvestigationConfig.ARRAY_TEST_CODE_WISE_LABWISE)%>;
        	 <%}else{%>
        	 availableTestslabwise =null;
        	 <%}%>
        	 availableTestGroups =<%=(String)session.getAttribute(InvestigationConfig.ARRAY_TESTGROUPNAMES)%>;

         	$( "#automplete-1" ).autocomplete({
               source: availableLabs,
               select: function(event, ui) {
                   $('#hiddenid1').val(ui.item.value);
                   event.preventDefault();
                   $("#automplete-1").val(ui.item.label);

                   //Clearing fields to make sure AJAX is accurate in firing
                    document.getElementsByName('tstValArr')[0].value="";
                    document.getElementsByName('tstGrpArr')[0].value="";
                    document.getElementsByName('searchTestName')[0].value="";
               		document.getElementsByName('searchTestGroupName')[0].value="";
               		document.getElementsByName('searchTest')[0].value="";
               		document.getElementsByName('searchTestGroup')[0].value="";
               availableTests= setTestComboUsingAjax(ui.item.value);

              var list1=document.getElementsByName('tstValArr')[0].value;

             var obj = JSON.parse(list1);

	          availableTests=obj;
	        //  alert("the availableTests value"+availableTests);

	          availableTestGroups= setTestGroupUsingAjax(ui.item.value);
               var list2=document.getElementsByName('tstGrpArr')[0].value;

               var obj2= JSON.parse(list2);

               availableTestGroups=obj2;

               },

           focus: function(event, ui) {
       event.preventDefault();
                  $("#automplete-1").val(ui.item.label);}
            });

         });

         $(function() {

            var list1=document.getElementsByName('tstValArr')[0].value;

        	 setInterval(function() {

            $( "#automplete-2" ).autocomplete({
                source: availableTests,
                select: function(event, ui) {
                 //   alert(ui.item.value);
                    $('#hiddenid2').val(ui.item.value);
                    event.preventDefault();
                    $("#automplete-2").val(ui.item.label);

                    availableTests= setTestComboUsingAjax(ui.item.value);


                },
             focus: function(event, ui) {
                   event.preventDefault();
                   $("#automplete-2").val(ui.item.label);}
             });
         }, 1000);
         });
         $(function() {

                      var list1=document.getElementsByName('tstValArr')[0].value;


                     var newnew=<%=(String)session.getAttribute(InvestigationConfig.ARRAY_TEST_CODE_WISE_LABWISE_TESTWISESEARCH)%>;

                 	 setInterval(function() {

                      $( "#automplete-testsearchlabwise" ).autocomplete({
                          source: newnew,
                          select: function(event, ui) {
                              //alert(ui.item.value);
                              $('#hiddenid23').val(ui.item.value);
                              event.preventDefault();
                              $("#automplete-testsearchlabwise").val(ui.item.label);
                                            var valu=ui.item.value;
                                          //  alert("valu"+valu);
                                             var main=valu.split("@");
                                            $('#hiddenid23').val(main[0]); 
                                            event.preventDefault(); 
                                            $("#automplete-testsearchlabwise").val(ui.item.label); 
                                            

                                            
                             if(valu.includes("testwise"))
                                 {
                              searchLabWiseTest23();
                                 }else if (valu.includes("groupwise")){
                                	 newtestwisesetUserGroupWiseDetail();
                                 }else{
                            	 searchLabWiseTest23();   
                                 }

                               },


                      focus: function(event, ui) {
                             event.preventDefault();
                             $("#automplete-testsearchlabwise").val(ui.item.label);}
                       });
                   }, 1000);
                   });

         $(function() {

             var list1=document.getElementsByName('tstValArr')[0].value;

         	 setInterval(function() {

             $( "#automplete-testsearch" ).autocomplete({
                 source: availableTests,
                 select: function(event, ui) {
                   //  alert(ui.item.value);
                     $('#hiddenid2').val(ui.item.value);
                     event.preventDefault();
                     $("#automplete-testsearch").val(ui.item.label);

                     searchLabWiseTest2();
                   //  availableTests= setTestComboUsingAjax2(ui.item.value);
                 },

             focus: function(event, ui) {
                    event.preventDefault();
                    $("#automplete-testsearch").val(ui.item.label);}
              });
          }, 1000);
          });


         $(function() {
	           var list2=document.getElementsByName('tstGrpArr')[0].value;
            setInterval(function() {
            $( "#automplete-3" ).autocomplete({
                source: availableTestGroups,
                select: function(event, ui) {
                    $('#hiddenid3').val(ui.item.value);
                    event.preventDefault();
                    $("#automplete-3").val(ui.item.label);
                },

            focus: function(event, ui) {
                   event.preventDefault();
                   $("#automplete-3").val(ui.item.label);}
             });
            }, 1000);
         });

         $(function() {

 	           var advisedByList=<%=(String)session.getAttribute(InvestigationConfig.ARRAY_ADVISEDBY_NAMES)%>;
               setInterval(function() {
             $( "#automplete-4" ).autocomplete({
                 source: advisedByList,
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



         $(function() {
  	          var advisedByList=<%=(String)session.getAttribute(InvestigationConfig.ARRAY_TEST_CODE_WISE)%>;


  	           setInterval(function() {
              $( "#automplete-5" ).autocomplete({
                  source: advisedByList,
                  select: function(event, ui) {
                      $('#hiddenid5').val(ui.item.value);
                      event.preventDefault();
                      $("#automplete-5").val(ui.item.label);

                      setTestCodeWiseDetail();
                  },

              focus: function(event, ui) {
                     event.preventDefault();
                     $("#automplete-5").val(ui.item.label);}
               });
              }, 1000);
           });


         $(function() {

  	           var userGroupLst=<%=(String)session.getAttribute(InvestigationConfig.ARRAY_GROUP_CODE_WISE)%>;


  	           setInterval(function() {
              $( "#automplete-6" ).autocomplete({
                  source: userGroupLst,
                  select: function(event, ui) {
                      $('#id6').val(ui.item.value);
                      event.preventDefault();
                      $("#automplete-6").val(ui.item.label);
                      setUserGroupWiseDetail();
                  },

              focus: function(event, ui) {
                     event.preventDefault();
                     $("#automplete-6").val(ui.item.label);}
               });
              }, 1000);
           });

//JQuery End

$("#datepicker").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date(new Date().getTime() - (1000 * 60 * 60 * 24)));

</script>

<script>
$(function() {
$( "#datepicker" ).datepicker({
dateFormat: 'dd-M-yy',
showOn: "button",
buttonImage: "/HISInvestigationG5/hisglobal/images/cal.png",
buttonImageOnly: true,
buttonText: "Select  "
}).datepicker("setDate", new Date());
});
</script>

<script>

</script>
<script>
$(function() {
var tabs = $( "#tabs" ).tabs();
tabs.find( ".ui-tabs-nav" ).sortable({
axis: "x",
stop: function() {
tabs.tabs( "refresh" );
}
});
});
</script>

<script>

//chandannnnnnnnnnnnnnnn111111111
</script>

<script>

//remove by chandan 4-aug-19

</script>
<script>
function showAptBasedTest()
{

	document.getElementsByName('hmode')[0].value="APTBASEDTEST";

	document.forms[0].submit();
}

function showAptBaseByDesk()
{

	
	document.getElementsByName('hmode')[0].value="APTBYDESK";

	document.forms[0].submit();
}

var concatenateChkBoxVal="";
var concatAptLabCode="";
var concatenateChkBoxValOfAppoitmetnDate="";

var concataptNo="";
  //for todays appointment
function ValidateSameCrNo(obj)
{
var countsa=0;
	    for(var i=0;i<document.getElementsByName('chkSamplePatient').length;i++)
	{
	    	//var countsa=0;
	     if(document.getElementsByName('chkSamplePatient')[i].checked==true)
	         {
	    	 countsa=countsa+1;
	         }

	     if(countsa>1)
			{
		    alert("Please select only one row");
		    document.getElementsByName('chkSamplePatient')[i].checked=false;

			document.getElementById('nextDiv1').style.display="";
			 return null;
			}
	 }

	if(obj.checked)
	{

		document.getElementById('nextDiv').style.display="";


	}
  else
     	{

          }

	var objCurrentCheckBox=obj.value;
	//alert(obj.checked);
	if(obj.checked)
	{

		var Value=obj.value;
		var splitValue=Value.split("#");

		document.getElementsByName('patCrNo')[0].value=splitValue[0];
		//alert(document.getElementsByName('patCrNo')[0].value);
		//alert(splitValue[5]);
		if(splitValue[5]=="0")
		document.getElementsByName('labbasedapppointment')[0].value=Value;

		concatenateChkBoxVal =concatenateChkBoxVal.concat(splitValue[1]);
		concatenateChkBoxVal+=',';


		concatenateChkBoxValOfAppoitmetnDate =concatenateChkBoxValOfAppoitmetnDate.concat(splitValue[2]);
		concatenateChkBoxValOfAppoitmetnDate+='@';


		concataptNo=concataptNo.concat(splitValue[3]);
		concataptNo+='`';

		concatAptLabCode=concatAptLabCode.concat(splitValue[4]);
		concatAptLabCode+=',';


		 document.getElementsByName('aptTestCode')[0].value=concatenateChkBoxVal;
		 document.getElementsByName('aptLabCode')[0].value=concatAptLabCode;


		 document.getElementsByName('offlineAppoitMentDate')[0].value=concatenateChkBoxValOfAppoitmetnDate;


		 document.getElementsByName('hidAptNo')[0].value=concataptNo;

		var cbs = document.getElementsByTagName('input');
		for(var i=0; i < cbs.length; i++)
		{
			    if(cbs[i].type == 'checkbox')
			    {

			    	 if(cbs[i].checked && (cbs[i].value.split("#")[0]!=objCurrentCheckBox.split("#")[0]))
			    	{

			    	alert("Please Select Same CR Number");
			    	document.getElementById('nextDiv').style.display="none";
			    	obj.checked=false;
			    	return false;
			    	}

			    	 if(cbs[i].checked && (cbs[i].value.split("#")[0]+cbs[i].value.split("#")[1]+cbs[i].value.split("#")[3]!=objCurrentCheckBox.split("#")[0]+objCurrentCheckBox.split("#")[1]+objCurrentCheckBox.split("#")[3]))
				    	{

				    	alert("Please Select Different Lab And Test");
				    	document.getElementById('nextDiv').style.display="none";
				    	obj.checked=false;
				    	return false;
				    	}
				}
		}
	}

}


function ValidateSameCr(obj)
{

	if(obj.checked)
	{

		document.getElementById('nextDiv1').style.display="";


	}
  else
     	{
	      }

	var objCurrentCheckBox=obj.value;
	//alert(obj.checked);
	if(obj.checked)
	{

		var Value=obj.value;
		var splitValue=Value.split("#");

		document.getElementsByName('patCrNo')[0].value=splitValue[0];
		//alert(document.getElementsByName('patCrNo')[0].value);
		concatenateChkBoxVal =concatenateChkBoxVal.concat(splitValue[1]);
		concatenateChkBoxVal+=',';
		var cbs = document.getElementsByTagName('input');
		for(var i=0; i < cbs.length; i++)
		{
			    if(cbs[i].type == 'checkbox')
			    {

			    	 if(cbs[i].checked && (cbs[i].value.split("#")[0]!=objCurrentCheckBox.split("#")[0]))
			    	{

			    	alert("Please Select Same CR Number");
			    	document.getElementById('nextDiv1').style.display="none";
			    	obj.checked=false;
			    	return false;
			    	}


				}
		}
	}

}

  function displayAptDetails()
  {
   document.getElementsByName('aptStatus')[0].value="1";
	  document.getElementsByName('hmode')[0].value="APTDETAIL";
	   document.forms[0].submit();

  }

  $(document).ready(function(){
	// your code

		if(document.getElementById("selectlabid")!=null)

    	    	{
    	var idf=document.getElementById("selectlabid")[0].value;
    //	alert("idf"+idf);
    	document.getElementById(idf).style.color = "yellow";
    	    	}
    	    	else
        	    	{
    	        	//alert("idf11");
        	    	}
	});

    function pageload()
  {

    	   //  alert("pageloa");
    	       	showamount();

    	 <%
    	  if(session.getAttribute("pidsetforgrpcase")!=null)
            {


    		  String pidgrpshow=(String)session.getAttribute("pidsetforgrpcase");

    		  if(pidgrpshow.equals("1"))
    		  {
    		  %>
    		//  alert("hello");
    		  document.getElementById('myButton').style.display="";
    		  callopenmodal("1");
              <%
              }


              }
    	    %>


    	if(document.getElementsByName("pidd")!=null && document.getElementsByName("pidd")[0].value!="")
        	{

                var data=document.getElementsByName("pidd")[0].value;
                var pid= data.split("@@@")[0];
                var initiatedtype= data.split("@@@")[1];
    		  //  document.getElementById("pidenter").value;

    		  if(pid!="" && initiatedtype!="")
        		  {
    			  document.getElementById("pidenter").value=pid;
    			//  alert("initiatedtype "+initiatedtype+" pid "+pid);
    			//  alert("initiatedtype"+initiatedtype);
                  if(initiatedtype=="0")
                   {
                	  document.getElementById("yesno0").checked=true;
                    }
                  else if(initiatedtype=="1")
                    { 
                	  document.getElementById("yesno1").checked=true;

                     }

        		  }


        	}

   
       if(isamountsuffforgroup=="1")
           {

     		alert("Patient Account Balance Going TO Be Insufficient. Please Deposit Part Payment!!");
     		return null;

           }


  	  if(document.getElementsByName("issearchtestnamewise")!=null && document.getElementsByName("issearchtestnamewise")[0].value==1)
       {
    	   if (document.getElementsByName("chkLabTest")!=null)
        	  {

    		  document.getElementsByName("chkLabTest")[0].checked=true;
    		  var val=document.getElementsByName("chkLabTest")[0].value;
    		  //alert(val);
    		     // 	alert("##"+document.getElementsByName("testLabTestCodeWise")[0].value);
    		      	//document.getElementsByName("testLabTestCodeWise")[0].value=val;
    				  document.getElementById("testCodeWiseSearchDiv").style.display="none";
    				  document.getElementById("searchLabTestDiv").style.display="";
    				  document.getElementById("automplete-1").focus();
    				  document.getElementById("automplete-testsearch").focus();

    		  showSelectedLabTestList(document.getElementsByName("chkLabTest")[0],val);

   		      }
       }


  	if(document.getElementsByName("issearchtestnamewise")!=null && document.getElementsByName("issearchtestnamewise")[0].value==2)
    {
  		 //alert(document.getElementsByName("issearchtestnamewise")[0].value);

 	   if (document.getElementsByName("chkLabTest")!=null)
     	  {

 		  document.getElementsByName("chkLabTest")[0].checked=true;
 		  var val=document.getElementsByName("chkLabTest")[0].value;
 			 document.getElementById("automplete-testsearchlabwise").focus();
 		  showSelectedLabTestList(document.getElementsByName("chkLabTest")[0],val);

		      }
 	  else
	  	{
		document.getElementById("automplete-5").focus();
	  	}
    }
  	else
  	  	{
  	  	if(document.getElementById("automplete-5")!=null)
  		document.getElementById("automplete-5").focus();
  	  	}

    	var ss="";
    	if(document.getElementsByName("selectlabid")!=null)
        	{
         ss=document.getElementsByName("selectlabid")[0].value;
        	}else
    		{//alert("null");
    		}
		//alert("ss"+ss);
        if(document.getElementById(ss)!=null && ss!='')
        	{document.getElementById(ss).style.color = "yellow";
    	document.getElementById(ss).style.backgroundColor  = "green";
        	}//document.getElementById("selectlabid").style.border = "4px solid black";

    	if(opener==null)
        	{

    		if(document.getElementById("showentryy1")!=null)
    		hidentry();


    		}
    	 // alert("hello"+document.getElementsByName("isentry")[0].value);
            if(opener!=null)
                {
                if(document.getElementsByName("isentry")[0].value=="1")
                    {
                	opener.document.getElementsByName("israisingsave")[0].value='1';
            opener.document.getElementsByName("hmode")[0].value='GETDETAILSSESSION';
           	 opener.document.forms[0].submit();
           	 self.close();
                    }

                }
      	   var element=document.getElementsByName("patCrNo")[0];
      	   //alert(element);
         element.setAttribute("onfocus","this.selectionStart = this.selectionEnd = this.value.length;this.select()");

         element.focus();
       // document.getElementById("automplete-5").focus();
       // alert("inside"+document.getElementsByName("testLabTestCodeWise")[0].value);
  	   if(document.getElementsByName("testLabTestCodeWise")[0].value!='')
		  {

	  if(document.getElementsByName("testLabTestCodeWise")[0].value=='myhisswitchTestLab')
	  {
		  document.getElementById("searchLabTestDiv").style.display="none";
		  document.getElementById("testCodeWiseSearchDiv").style.display="";

		  document.getElementById("lengendStatus").style.display="none";
		  document.getElementById("prvReqDtl").style.color = "";
		  document.getElementById("prvReqDtl").style.fontFamily = "";
		  document.getElementById("prvReqDtl").style.fontSize = "";
		  document.getElementById("prvReqDtl").style.backgroundColor="";
		  document.getElementById("prvReqDtl").style.borderRadius ="";
		  document.getElementById("prvReqDtl").style.width ="";
	  }
  if(document.getElementsByName("testLabTestCodeWise")[0].value=='myhisswitchTest')
  {
	  document.getElementById("testCodeWiseSearchDiv").style.display="none";
	  document.getElementById("searchLabTestDiv").style.display="";


	  document.getElementById("lengendStatus").style.display="none";

	  document.getElementById("prvReqDtl").style.color = "";
	  document.getElementById("prvReqDtl").style.fontFamily = "";
	  document.getElementById("prvReqDtl").style.fontSize = "";
	  document.getElementById("prvReqDtl").style.backgroundColor="";
	  document.getElementById("prvReqDtl").style.borderRadius ="";
	  document.getElementById("prvReqDtl").style.width ="";
		  }

		  }
	   else
		   {
	
		   if(document.getElementById("lengendStatus")!=null)
		  document.getElementById("lengendStatus").style.display="none";

			  if(  document.getElementById("prvReqDtl")!=null)
				  {
		  document.getElementById("prvReqDtl").style.color = "";
		  document.getElementById("prvReqDtl").style.fontFamily = "";
		  document.getElementById("prvReqDtl").style.fontSize = "";
		  document.getElementById("prvReqDtl").style.backgroundColor="";
		  document.getElementById("prvReqDtl").style.borderRadius ="";
		  document.getElementById("prvReqDtl").style.width ="";
		  document.getElementsByName("priorityAll")[0].value="1";
		   }


		   }

  	  if(document.getElementsByClassName(ss)!=null && ss!='')
    	{
	      	if(document.getElementsByClassName(ss)[0]!="undefined" && document.getElementsByClassName(ss)[0]!=undefined)
	      		/* alPras */
		      	{ alert(document.getElementsByClassName(ss)[0]==undefined );
		      
           	document.getElementsByClassName(ss)[0].style.color = "green";

    	document.getElementsByClassName(ss)[0].style.backgroundColor  = "yellow";

    	 var cells = document.getElementsByClassName("one");
     for (var i = 0; i < cells.length; i++) {
         cells[i].disabled = true;
     }
		      	}
   //  document.getElementsByClassName(ss)[0].disabled = false;
    	}
  }


  function getTestWiseList(val)
  {

	 var vall= document.getElementsByName('labwisetestteriff')[0].value;
	 if(vall==1)
		 {
		  document.getElementsByName('searchLabName')[0].value="";
		  document.getElementsByName('labwisetestteriff')[0].value="";
		  document.getElementsByName("testLabTestCodeWise")[0].value="myhisswitchTest";

		      searchLabWiseTest();
	     }
	  document.getElementById("terifftest").style.display="none";
	//alert(val);
	  if(val=='myhisswitchTestLab')
		  {
		 // alert("a");
		  document.getElementsByName("testLabTestCodeWise")[0].value=val;
	  document.getElementById("searchLabTestDiv").style.display="none";
	  document.getElementById("testCodeWiseSearchDiv").style.display="";
	  document.getElementById("automplete-5").focus();



		  }
	  if(val=='myhisswitchTest')
	  {
		 // alert("b");
		  document.getElementsByName("testLabTestCodeWise")[0].value=val;
		  document.getElementById("testCodeWiseSearchDiv").style.display="none";
		  document.getElementById("searchLabTestDiv").style.display="";
		  document.getElementById("automplete-1").focus();

	  }


  }

  function setTestCodeWiseDetail()
  {
  	//alert("setTestCodeWiseDetail() called.");
  	var testCodeList=<%=(String)session.getAttribute(InvestigationConfig.ARRAY_TEST_CODE_WISE)%>;
  	var testCodeFound = false;

	 for(k=0;k<testCodeList.length;k++)
  		{
  		 if(testCodeList[k].value.toUpperCase()==document.getElementById('automplete-5').value.toUpperCase().trim())
  			 {
  				testCodeFound = true;
  			 	document.getElementsByName('testCodeWise')[0].value=document.getElementById('automplete-5').value.trim();
  			 }
  		 }

  	 if(document.getElementsByName('testCodeWise')[0].value=='' || (testCodeFound == false))
		{
			alert('Select Valid Test Code!');
			document.getElementById('automplete-5').value="";
			document.getElementById('automplete-5').focus();

			return false;
		}

  	//changed by ashu

  	var testCodeSearchBy = document.getElementsByName('testCodeWise')[0].value;

  	var addValueToRow = null;

  	var remarks = null;


  	remarks = checkTestAvailabilityAjax(testCodeSearchBy.toUpperCase());

  	if(remarks != "1"){

  		if(remarks != "0")
  			alert('Test not Available ! Remarks : '+remarks);
  		else
  			alert('Test not Available !');

		document.getElementById('automplete-5').value="";
		document.getElementById('automplete-5').focus();

		return false;
  	}


  	addValueToRow = getUserTestCodeAjax(testCodeSearchBy.toUpperCase());

	showSelectedLabTestList1(addValueToRow,addValueToRow);

	document.getElementById('automplete-5').value="";
	document.getElementById('automplete-5').focus();

  }


  function getUserTestCodeAjax(userTestCode)
  {

  	var flg = false;
  	var labTestCodeArray = "";
  	var _mode = "AJX_USER_TEST_CODE";
  	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&userTestCode="+userTestCode, sync:true, postData: "", handleAs: "text",
  		load: function(data)
  		{
  		  // alert("data"+data);
  			labTestCodeArray = data;
  			flg = true;
  		},
          error: function(error)
          {
              //labTestCodeArray = tmpLabTestCodeArray;
              flg = false;
          }};

  	var objDojoAjax = dojo.xhrPost(objXHR);
  	return labTestCodeArray;
  }



  function changeAllPriority(obj)
  {
  	var priorityAll=obj.value;
  	//alert(priorityAll);

  	var length=document.getElementsByName('priority').length;
  	var flg="true";
  	document.getElementById('flag').value=priorityAll;
  	for(var i=0;i<length;i++)
  		{

  		document.getElementsByName('priority')[i].value=priorityAll;
  		document.getElementsByName('priority')[i].onchange();
  		}

  	}

function setUserGroupWiseDetail()
{
	 var testCodeList=<%=(String)session.getAttribute(InvestigationConfig.ARRAY_GROUP_CODE_WISE)%>;

	 for(k=0;k<testCodeList.length;k++)
		{
			 if(testCodeList[k].value.toUpperCase()==document.getElementById('automplete-6').value.toUpperCase())
			 {
			 document.getElementsByName('userGroupCodeWise')[0].value=document.getElementById('automplete-6').value;
			 }
		 }

	 if(document.getElementsByName('userGroupCodeWise')[0].value=='')
		{
			alert('Select Valid Group Code!');
			document.getElementById('automplete-6').value="";
			document.getElementById('automplete-6').focus();
			return false;
		}

 var isduplicatetestraisedtoday = isduplicatetestrasiedgroupeise(document.getElementsByName('userGroupCodeWise')[0].value);
	    var flag="0";
	   // alert("isduplicatetestraisedtoday"+isduplicatetestraisedtoday);
		if(isduplicatetestraisedtoday=="1")
			{
		if (confirm('This Group has already been rasied.Do you want to Continue')) {
			flag="0";
		    // Save it!
		} else {

			flag="1";
		    // Do nothing!
		}
			}


        if(flag=="1")
        return null;


		document.getElementsByName('hmode')[0].value="SEARCHGROUPCODEWISE";
		 document.forms[0].submit();

}

function newtestwisesetUserGroupWiseDetail()
{
	 var testCodeList=<%=(String)session.getAttribute(InvestigationConfig.ARRAY_GROUP_CODE_WISE)%>;
	 
	 for(k=0;k<testCodeList.length;k++)
		{
			 if(testCodeList[k].value.toUpperCase()==document.getElementById('automplete-testsearchlabwise').value.toUpperCase())
			 {
			 document.getElementsByName('userGroupCodeWise')[0].value=document.getElementById('automplete-testsearchlabwise').value;
			 }
		 }
	  	 
	 if(document.getElementsByName('userGroupCodeWise')[0].value=='')
		{
			alert('Select Valid Group Code!');
			document.getElementById('automplete-testsearchlabwise').value="";
			document.getElementById('automplete-testsearchlabwise').focus();
			return false;
		} 
	 
 var isduplicatetestraisedtoday = isduplicatetestrasiedgroupeise(document.getElementsByName('userGroupCodeWise')[0].value);
	    var flag="0";
	   // alert("isduplicatetestraisedtoday"+isduplicatetestraisedtoday);
		if(isduplicatetestraisedtoday=="1")
			{
		if (confirm('This Group has already been rasied.Do you want to Continue')) {
			flag="0";
		    // Save it!
		} else {
	
			flag="1";
		    // Do nothing!
		}
			}
		

        if(flag=="1")
        return null;

        
		document.getElementsByName('hmode')[0].value="SEARCHGROUPCODEWISE";
		 document.forms[0].submit();
	
}

function newtestwisesetUserGroupWiseDetail()
{
	 var testCodeList=<%=(String)session.getAttribute(InvestigationConfig.ARRAY_GROUP_CODE_WISE)%>;
	 
	 for(k=0;k<testCodeList.length;k++)
		{
			 if(testCodeList[k].value.toUpperCase()==document.getElementById('automplete-testsearchlabwise').value.toUpperCase())
			 {
			 document.getElementsByName('userGroupCodeWise')[0].value=document.getElementById('automplete-testsearchlabwise').value;
			 }
		 }
	  	 
	 if(document.getElementsByName('userGroupCodeWise')[0].value=='')
		{
			alert('Select Valid Group Code!');
			document.getElementById('automplete-testsearchlabwise').value="";
			document.getElementById('automplete-testsearchlabwise').focus();
			return false;
		} 
	 
 var isduplicatetestraisedtoday = isduplicatetestrasiedgroupeise(document.getElementsByName('userGroupCodeWise')[0].value);
	    var flag="0";
	   // alert("isduplicatetestraisedtoday"+isduplicatetestraisedtoday);
		if(isduplicatetestraisedtoday=="1")
			{
		if (confirm('This Group has already been rasied.Do you want to Continue')) {
			flag="0";
		    // Save it!
		} else {
	
			flag="1";
		    // Do nothing!
		}
			}
		

        if(flag=="1")
        return null;

        
		document.getElementsByName('hmode')[0].value="SEARCHGROUPCODEWISE";
		 document.forms[0].submit();
	
}

</script>

<script>

$(function(){
	$("#automplete-1").keypress(function(event) {
    if (event.which == 13) {
    	searchLabWiseTest();
       }
});
});
</script>
<script>

$(function(){
	$("#automplete-2").keypress(function(event) {

    if (event.which == 13) {

       	searchLabWiseTest();

       }
});
});



</script>


<script>

$(function(){
	$("#automplete-5").keypress(function(event) {

    if (event.which == 13) {

    	setTestCodeWiseDetail();
    	
    	$(".ui-menu-item").hide();
       }
});
});




$(function(){
	$("#automplete-6").keypress(function(event) {

    if (event.which == 13) {

    	setUserGroupWiseDetail();
       }
});
});


</script>

<script type="text/javascript" language="javascript" class="init">

/* Datatable Added by PrashantMi */
$(document).ready(function() {
	 if($("#todaysApotTable").length){
	UseDataTables("todaysApotTable");
	 }
});
</script>

<his:css src="/hisglobal/css/onlinerequisition.css" />

<!-- cssssssssssssss
 -->
<%
	String strdivage="\"\"";
String strdivdob="\"\"";
String patCrNo="";  //style="width:90%;margin:auto;min-width:600px;max-width:2000px"
%>


 <%
			  String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;

         %>


<body  onload="pageload()"  >
<div class="divStyle">

	<html:form action="/investigationReqRaising" method="POST">
		<html:hidden name="InvestigationRaisingDtlFB" property="piddata" />
		<html:hidden name="InvestigationRaisingDtlFB" property="piddmodalopen" />
      <input type="hidden" name="islabcallapp" />
		
					<logic:present name="<%=InvestigationConfig.LIST_PID_PAT%>">
				<%
				Inv_ictc_VO ictcvo=	(Inv_ictc_VO)session.getAttribute(InvestigationConfig.LIST_PID_PAT);

                            if(ictcvo!=null)
                         pidoldreqset=ictcvo.getPatpuk()+"#"+ictcvo.getPatgender()+"#"+ictcvo.getIspregnant()+"#"+ictcvo.getPidno()+"#"+ictcvo.getIntiatedtype()+"#";
                        		 %>
                        		 <script>
					datapidonload=<%=pidoldreqset %>;
					</script>
	</logic:present>
	
	
	<logic:present name="<%=InvestigationConfig.ALL_TEST_RATE%>">
				<%
				String testratesdata=	(String)session.getAttribute(InvestigationConfig.ALL_TEST_RATE);
			
%>
 <script>
   testratess=<%=testratesdata %>;

					</script>
					
</logic:present>


<logic:present name="<%=InvestigationConfig.ALL_GROUP_RATE%>">
				<%
				String grptestratesdata=	(String)session.getAttribute(InvestigationConfig.ALL_GROUP_RATE);
			
%>
 <script>
   grptestratess=<%=grptestratesdata %>;

					</script>
					
</logic:present>


	<html:hidden name="InvestigationRaisingDtlFB" value="" property="advice" />

	<html:hidden name="InvestigationRaisingDtlFB" value="" property="requisitionNo" />
	<%-- <html:hidden name="InvestigationRaisingDtlFB" value="" property="newlabtestcodearray" /> --%>
	<html:hidden name="InvestigationRaisingDtlFB" value="" property="requisitionFormData" />
	<html:hidden name="InvestigationRaisingDtlFB" value="" property="isrequisitionFormNeeded" />
	<html:hidden name="InvestigationRaisingDtlFB" property="cashCrNo" />

	<div id="blanket" style="display: none"></div>
 		<div id="popUpDiv5"  style="display:none;margin-top: 0%" align="center">

		<his:TitleTag name="Instructions For Patients">
		<img src='/HISInvestigationG5/hisglobal/css/close.png'  onClick="closeInstructions();">
  		</his:TitleTag>

  		<table width="100%" id="allInstructions">
 		<tr>
 	        <td class="tdfonthead" width="25%">
		 				<div align="right">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="CRNo."/>
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">

		 				</font>
		 				</div>
		 			</td>
		 			<td class="tdfont" width="25%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<bean:message key="Name"/>
		 				</font>
		 				</div>

		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">


		 					</font>
		 				</div>
		 			</td>
		 	</tr>

 		<tr>

		</tr>


 		</table>

<img src='/HISInvestigationG5/hisglobal/images/ok.gif'  onClick="closeInstructions();">
		<img src='/HISInvestigationG5/hisglobal/images/print_tab.gif'  onClick="callPrint();">

		</div>



 	<!-- END -- TO DISPLAY INSTRUCTIONS -->
	  <input type="hidden" id="flag" name="flag" value="false">
		<his:statusNew>
			<his:TitleTag key="invRaising">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag>

										 <logic:notEqual name="InvestigationRaisingDtlFB" property="flagDesk" value="1"></


			<his:InputCrNoInvestigationTag name="InvestigationRaisingDtlFB">
			</his:InputCrNoInvestigationTag>

			<%-- <his:SubTitleTag name="Today's Appoitment Based Test">
					</his:SubTitleTag> --%>
			<table width="100%">
				<tr>
				 	<td class="tdfont" width="25%">
						<div align="right">
							<input type="radio" name="radioEpisode" value=""
								onclick="showAptBasedTest()" />
								
						</div>
					</td>
					<td class="tdfont" width="25%">
						<div align="left">

	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="AppointmentBasedTest"/>
					</font>
        		</div>

					</td>
					<td class="tdfont" width="25%">
						<div align="right">
							<input type="radio" name="radioEpisode" value=""
								onclick="showAptBaseByDesk()" />
						</div>
					</td>
					<td class="tdfont" width="25%">
						<div align="left">

						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="AptBaseByDesk"/>
						</font>
						</div>
					</td>

				</tr>

			</table>

   </logic:notEqual>

		</his:statusNew>
		<his:statusTransactionInProcess>
			<logic:equal name="InvestigationRaisingDtlFB" property="aptStatus"
				value="0">


				<logic:present name="<%=InvestigationConfig.LIST_APTBASED_TEST%>">
					<his:SubTitleTag name="Today's Appointment Based Test Listing">
					</his:SubTitleTag>

						 <%boolean flag=false; %>

					<table width="100%" id="todaysApotTable">
					<thead>
						<tr>
							<th class="tdfont" width="5%">

								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									 <bean:message key="Select"/>  </font>

								</div>


							</th>


							<th class="tdfont" width="10%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										Appointment Type </font>
								</div>
							</th>


							<th class="tdfont" width="10%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										Lab Name </font>
								</div>
							</th>

							<th class="tdfont" width="10%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="testname"/> </font>
								</div>
							</th>

							<th class="tdfont" width="15%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="CrNO"/></font>
								</div>
							</th>


							<th class="tdfont" width="20%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="Name"/></font>
								</div>
							</th>
							<th class="tdfont" width="10%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="Age"/> </font>
								</div>
							</th>
							<th class="tdfont" width="5%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="Gender"/> </font>
								</div>
							</th>
							<th class="tdfont" width="20%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="AppoitmentDate/Time"/> </font>
								</div>
							</th>

						</tr>
						</thead>
						

						<tbody>

						<%
						 List<LabTestVO> lstPatAppotmentVO=(List<LabTestVO>)session.getAttribute(InvestigationConfig.LIST_APTBASED_TEST);
						 int i,size=0;

				 		if(lstPatAppotmentVO!=null && lstPatAppotmentVO.size()>0 )
				 		{
				 		//	System.out.println(lstPatAppotmentVO.size());
				 			size=lstPatAppotmentVO.size();

				 			for(int k=0;k<size;k++)
						{
																							 	if(lstPatAppotmentVO!=null && lstPatAppotmentVO.size()>0 )
																							 	{
																							 		int sizePatAptVO=lstPatAppotmentVO.size();


																							 			LabTestVO voPatApt=lstPatAppotmentVO.get(k);
																							 	    String testname="";
																							 			if(voPatApt.getTestCode()==null)
																							 	    {
																							 				testname="-";
																							 	    }
																							 	    else
																							 	    {
																							 	    	testname=voPatApt.getTestCode();
																							 	    }
																							 			String chkVal=voPatApt.getPukNo()+"#"+voPatApt.getTestCode()+"#"+voPatApt.getAptDate()+" "+voPatApt.getSlotTime()+"#"+voPatApt.getAptNO()+"#"+voPatApt.getLabCode()+"#"+testname;
																							 																 		%>
						<tr>


 	<td class="tdfonthead" width="5%">
								<div align="left">
									<input type="checkbox" name="chkSamplePatient"
										value='<%=chkVal%>' onclick="ValidateSameCrNo(this)">
								</div>
							</td>


							<td class="tdfonthead" width="10%">
								<div align="left">
									<%=voPatApt.getAptTyp()%>
								</div>
							</td>

								<td class="tdfonthead" width="10%">
								<div align="left">
									<%=voPatApt.getLabName()%>
								</div>
							</td>

 <td class="tdfonthead" width="10%">
								<div align="left">
									<%=voPatApt.getTestName()==null?"-":voPatApt.getTestName()%>
								</div>
							</td>
				<td class="tdfonthead" width="15%">

								<div align="left">
									<%=voPatApt.getPukNo()==null?"NA":voPatApt.getPukNo() %>
								</div>
							</td>


							<td class="tdfonthead" width="20%">
								<div align="left">
									<%=voPatApt.getPatFirstName()+" "+voPatApt.getPatMiddleName()+" "+voPatApt.getPatLastNAme()%>
								</div>
							</td>


							<td class="tdfonthead" width="10%">
								<div align="left">
									<%=voPatApt.getPatAge()%>
								</div>
							</td>

							<td class="tdfonthead" width="5%">
								<div align="left">
									<%=voPatApt.getGender()%>
								</div>
							</td>


							<td class="tdfonthead" width="20%">
								<div align="left">
									<%=voPatApt.getAptDate()+"      "+voPatApt.getSlotTime()%>
								</div>
							</td>



						</tr>
						

						<%
							}
													 	}}
						%>
					</tbody>
					</table>

				</logic:present>
				<logic:empty name="<%=InvestigationConfig.LIST_APTBASED_TEST%>">
					<center>
						<font id="colorCycl" color="red" size="4"><bean:message key="NoOfflineAppoitmentListFound"/> </font>
				</center>
				</logic:empty>
			</logic:equal>

			<logic:equal name="InvestigationRaisingDtlFB" property="aptStatus"
				value="2">

				 <bean:define name="InvestigationRaisingDtlFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="InvestigationRaisingDtlFB" property="toDate" id="tDate" type="java.lang.String"/>


					<%if(frDate==null||frDate.equalsIgnoreCase(""))
	         {
	        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
	        	frDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
	         }

			  if(tDate==null||tDate.equalsIgnoreCase(""))
			  {
			  	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
			  	tDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			   } %>


					<table width="100%">

						<tr >
									 			<td class="tdfonthead" width="20%">
									        		<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
										        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
										        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<bean:message key="fromDate"/>
														</font>
									        		</div>

												</td>
												<td class="tdfont" width="20%">
										    		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left">
														<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
													</div>

												</td>

									 			<td class="tdfonthead" width="20%">
									        		<div id='divfromDate' style='<%=toDateLabel %>' align="right">
										        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
										        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<bean:message key="toDate"/>
														</font>
									        		</div>
												</td>

												<td class="tdfont" width="20%">
										    		<div id='divfromDateControl' style='<%=toDateControl %>'align="left">
														<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
													</div>

												</td>

												<td class="tdfonthead" width="20%">
									        		<div  align="left">
										        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
  			 <img class="button" title="Show Patinet Details"  src='<his:path src="/hisglobal/images/GoNew.png"/>'  tabindex="1" onclick ="showAptBaseByDesk()" >
														</font>
									        		</div>
												</td>


								 		</tr>



					</table>


				<logic:present name="<%=InvestigationConfig.LIST_APT_BY_DESK%>">
					<his:SubTitleTag name="Test awaiting appointment as advised by Doctors">
					</his:SubTitleTag>

					<table width="100%">
						<tr>

								<td class="tdfont" width="10%">

								<div align="center">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									 <bean:message key="Select"/>  </font>

								</div>


							</td>
							<td class="tdfont" width="15%">
								<div align="center">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="CrNO"/></font>
								</div>
							</td>


							<td class="tdfont" width="15%">
								<div align="center">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="Name"/></font>
								</div>
							</td>
							<td class="tdfont" width="15%">
								<div align="center">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="Age"/> </font>
								</div>
							</td>
							<td class="tdfont" width="15%">
								<div align="center">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="reqDate"/> </font>
								</div>
							</td>

							<td class="tdfont" width="15%">
								<div align="center">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="LabType"/> </font>
								</div>
							</td>
							<td class="tdfont" width="15%">
								<div align="center">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="testname"/>	</font>
								</div>
							</td>


						</tr>

						<%
							List<LabTestVO> lstPatAppotmentVO=(List<LabTestVO>)session.getAttribute(InvestigationConfig.LIST_APT_BY_DESK);

																						 	if(lstPatAppotmentVO!=null && lstPatAppotmentVO.size()>0 )
																						 	{
																						 		int sizePatAptVO=lstPatAppotmentVO.size();

																							 	  Map<String,String> labbsedmap=new HashMap<String,String>();

																						 		for(int k=0;k<sizePatAptVO;k++)
																						 		{
																						 			LabTestVO voPatApt=lstPatAppotmentVO.get(k);
																						 			String chkVal=voPatApt.getPatCrNo()+"#"+voPatApt.getReqNo()+"#"+voPatApt.getLabCode()+"#"+voPatApt.getSampleCode()+"#"+k;
																						 			//String chkVal=voPatApt.getPukNo()+"#"+voPatApt.getTestCode()+"#"+voPatApt.getAptDate()+" "+voPatApt.getSlotTime()+"#"+voPatApt.getAptNO()+"#"+voPatApt.getLabCode();
																						 		%>
						<tr>


							<td class="tdfonthead" width="10%">
								<div align="center">
									<input type="checkbox" name="chkSamplePatient"
										value='<%=chkVal%>' onclick="ValidateSameCr(this)">
								</div>
							</td>


								<td class="tdfonthead" width="15%">

								<div align="center">
									<%=voPatApt.getPatCrNo()==null?"NA":voPatApt.getPatCrNo() %>
								</div>
							</td>


							<td class="tdfonthead" width="15%">
								<div align="center">
									<%=voPatApt.getPatName()%>

								</div>
							</td>


							<td class="tdfonthead" width="15%">
								<div align="center">
									<%=voPatApt.getPatAge()%>
								</div>
							</td>

							<td class="tdfonthead" width="15%">
								<div align="center">
									<%=voPatApt.getReqDate()%>
								</div>
							</td>

							<td class="tdfonthead" width="15%">
								<div align="center">
									<%=voPatApt.getLabName()%>
								</div>
							</td>

							<td class="tdfonthead" width="15%">
								<div align="center">
								<%=voPatApt.getTestName()%>
								</div>
							</td>

						</tr>

						<%
							}
													 	}
						%>
					</table>

				</logic:present>
				<logic:empty name="<%=InvestigationConfig.LIST_APT_BY_DESK%>">
					<center>
						<font id="colorCycl" color="red" size="4"><bean:message key="NoTestAdvised"/> </font>
				</center>
				</logic:empty>
			</logic:equal>


			<!-- END OF APPOINTMENT RAISED BY DOCTORS FROM DESK -->

			<!-- Details of APPOINTMENT based tests RAISED BY DOCTORS FROM DESK -->

			<logic:equal name="InvestigationRaisingDtlFB" property="aptStatus"
				value="5">

				<logic:present name="<%=InvestigationConfig.LIST_APT_DETAILS_REQ_DTL_DESK%>">
					<his:SubTitleTag name="Select Appointment Dates">
					</his:SubTitleTag>
					<table width="100%">
						<tr>

						<td class="tdfont" width="10%">
								<div align="center">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										 <bean:message key="Select"/>  </font>
								</div>
							</td>


							<td class="tdfont" width="22.5%">
								<div align="center">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="LabType"/> </font>
								</div>
							</td>


							<td class="tdfont" width="22.5%">
								<div align="center">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="Test"/></font>
								</div>
							</td>
							<td class="tdfont" width="22.5%">
								<div align="center">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="sampleCombo"/></font>
								</div>
							</td>
							<td class="tdfont" width="22.5%">
								<div align="center">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="Appointment"/> </font>
								</div>
							</td>

		</tr>
						</table>

						<%
							List<LabTestVO> lstPatAppotmentVO=(List<LabTestVO>)session.getAttribute(InvestigationConfig.LIST_APT_DETAILS_REQ_DTL_DESK);

																						 	if(lstPatAppotmentVO!=null && lstPatAppotmentVO.size()>0 )
																						 	{
																						 		int sizePatAptVO=lstPatAppotmentVO.size();

																							 	  Map<String,String> labbsedmap=new HashMap<String,String>();

																						 		for(int k=0;k<sizePatAptVO;k++)
																						 		{
																						 			LabTestVO voPatApt=lstPatAppotmentVO.get(k);
																						 			String chkVal=voPatApt.getPatCrNo()+"#"+voPatApt.getReqNo()+"#"+voPatApt.getLabCode()+"#"+voPatApt.getSampleCode()+"#"+k;

																						 			String _paraId=voPatApt.getLabCode()+"^"+voPatApt.getTestCode()+"^0^0^0^0^0";
																						 			String paraNum=voPatApt.getLabCode()+"^"+voPatApt.getTestCode()+"^0^0^0^0^0";
																						 			String groupType="0";
																						 			if(voPatApt.getGroupType()==null || voPatApt.getGroupType().equals(""))
																						 				voPatApt.setGroupType(groupType);
																						 			if(voPatApt.getGroupCode()==null || voPatApt.getGroupCode().equals(""))
																						 				voPatApt.setGroupCode("0");
																									String makeid="";
																									 boolean fl=false;
																										int count=0;
																						 			String onLoadRow="";
																						 		   String onLoadRownew="";

															                                           String labsedfunctn="";

																						 			if(voPatApt.getIslabappointmentbased().equals("1"))
																						 			{
																										String divAptTagRow="aptTagRow_"+voPatApt.getLabCode()+"_"+"0";

																							 			  if(voPatApt.getIslabappointmentbased().equals("1"))
																									 	  	 {
																							 				  
																							 				  if(!labbsedmap.containsKey(voPatApt.getLabCode()))
																									 	  		{labbsedmap.put(voPatApt.getLabCode(), divAptTagRow);
																									 	  		fl=false;
																									 	  		}
																							 				  else
																							 				  {
																							 					 makeid=  (String)labbsedmap.get(voPatApt.getLabCode());
																							 					 count=2;
																							 					 fl=true;
																							 				  }
																							 				  
																							 				  
																									 	  	 }
																							 			  
																										_paraId=voPatApt.getLabCode()+"^"+"0"+"^0^0^0^0^0";
																										paraNum=voPatApt.getLabCode()+"^"+"0"+"^0^0^0^0^0";
																						 			 onLoadRow="getAptSlotDetails('"+voPatApt.getPatCrNo()+"','"+paraNum+"','','aptTagRow_"+voPatApt.getLabCode()+"_"+'0'+"','4')";
																						 		//	   System.out.println("----onLoadRow------"+onLoadRow);
																						 				onLoadRownew="setappslotdtls('"+makeid+"','"+voPatApt.getLabCode()+"')";

																						 			  labsedfunctn="getappdetails('"+makeid+"','"+voPatApt.getLabCode()+"')";
																						 			}
																						 			
																						 			else	if(voPatApt.getIsAppointment().equals("1"))
																								 	{	
																						 			 onLoadRow="getAptSlotDetails('"+voPatApt.getPatCrNo()+"','"+paraNum+"','','aptTagRow_"+voPatApt.getLabCode()+"_"+voPatApt.getTestCode()+"','2')";
																						 			}	
																						 			
																						 			
																						 			String allot=voPatApt.getReqNo()+"#"+voPatApt.getPatCrNo()+"#"+voPatApt.getRequisitionDNo()+"#"+voPatApt.getLabCode()+"#"+voPatApt.getTestCode()+"#"+voPatApt.getPriorityCode()+"#"+voPatApt.getPatType()+"#"+voPatApt.getGroupType()+"#"+voPatApt.getGroupCode()+"#"+voPatApt.getIsAppointment()+"#"+voPatApt.getSampleCode();
																						 			//String chkVal=voPatApt.getPukNo()+"#"+voPatApt.getTestCode()+"#"+voPatApt.getAptDate()+" "+voPatApt.getSlotTime()+"#"+voPatApt.getAptNO()+"#"+voPatApt.getLabCode();


																					 				   if(voPatApt.getIslabappointmentbased().equals("1")){
															                                        	   System.out.println("----appointment present------");
																										%>
																										<script>

																										
																										<% if(fl){%>
																										
																										
																										<%}else{%>
																										
																											<%=onLoadRow%>
																									<%	}%>
																										
																						 	   
																					        </script>




															<%} 	else  if(voPatApt.getIsAppointment().equals("1")){
													                                        	 //  System.out.println("----appointment present------");
																								%>
																								<script>

																				 	   <%=onLoadRow%>
																			        </script>
													<%} else{}%>
													

									<table width="100%">
						<tr>


 <td class="tdfonthead" width="10%">
								<div align="center">

									<input type="checkbox" name="allotAppointment"
										value='<%=allot%>'>
								</div>
							</td>


								<td class="tdfonthead" width="22.5%">

								<div align="center">
									<%=voPatApt.getLabName()%>
								</div>
							</td>


							<td class="tdfonthead" width="22.5%">
								<div align="center">
									<%=voPatApt.getTestName() %>
								</div>
							</td>


							<td class="tdfonthead" width="22.5%">
								<div align="center">
										<%=voPatApt.getSampleName()==null?"Patient Based":voPatApt.getSampleName()%>
								</div>
							</td>



							<td class="tdfonthead" width="22.5%">
							
							<%
															if (voPatApt.getIslabappointmentbased().equals("1")) {
																
															boolean flg=true;
														%>
                                                    <%     if(fl)
                                                         {%>
                                                         
                                                        
														<div align="left"
															name='<%=makeid%>' id="aptTagRow_<%=voPatApt.getLabCode()%>_<%='0'%>" ></div> <%--labCode+testCode--%>
                                                      <script>
                                                       <%=labsedfunctn%>
                                                       </script>
                                                      <%     }
                                                         else
                                                         {%>
                                                         
                                                         <div align="left"
															id="aptTagRow_<%=voPatApt.getLabCode()%>_<%='0'%>" ></div> <%--labCode+testCode--%>
                                                         <script>
                                                        													<%=onLoadRownew%>
                                                        													</script>
                                                         <%  }%>
                                    					<%
														String setDate ="";
														
															
															 setDate = "setDateInApoitment('"+patCrNo+"','"+paraNum+"','this','aptTagRow_"+voPatApt.getLabCode()+"_"+'0'+"')";

													
														%> <input type="hidden" name="dateTag" 
														onchange="<%=setDate%>" id="datepicker">
														</p> <%
                                                         	}
															else	if (voPatApt.getIsAppointment().equals("1")) {%>
							     
									<div align="center"
															id="aptTagRow_<%=voPatApt.getLabCode()%>_<%=voPatApt.getTestCode()%>" ></div> <%--labCode+testCode--%>
                                       
														
														<%String setDate="";
														
														
														 setDate = "setDateInApoitment('"+voPatApt.getPatCrNo()+"','"+paraNum+"','this','aptTagRow_"+voPatApt.getLabCode()+"_"+voPatApt.getTestCode()+"')";		
														
														%> <input type="hidden" name="dateTag"
														onchange="<%=setDate%>" id="datepicker">
							</td>
<%}else{ %>


								<td class="tdfonthead" width="22.5%">
									<div align="center">
										<bean:message key="NA" />
									</div>
								</td>

								<%} %>


						</tr>

						<%
							
																						 		}
						%>
					</table>
<%} %>
				</logic:present>
				<logic:empty name="<%=InvestigationConfig.LIST_APT_DETAILS_REQ_DTL_DESK%>">
					<center>
						<font id="colorCycl" color="red" size="4">No Test Advised </font>
				</center>
				</logic:empty>
			</logic:equal>

			<logic:present name="<%=InvestigationConfig.PATIENT_VO%>">

               <%
              Inv_RequisitionRaisingPatientVO  lstPatVO=(Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);


						%>

   <his:SubTitleTag name="Patient Details">
			 <%
			 String showDetail="showPatDetailsPatient()";
			 String hideDetail="hidePatDetailsPatient()";
			 %>
<!-- 			 <button id="myButton" style="display: none">Pi Form</button>
 -->
    <button id="myButtonip" style="display: none;box-shadow: 0 6px 10px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);background-color: #556B2F;border: none;color: white;padding: 2px 10px;text-align: center; text-decoration: none;font-size: 14px;margin: 4px 2px;cursor: pointer;-webkit-transition-duration: 0.4s;transition-duration: 0.4s;height: 30px;">
   Pid Form
   </button>

 <!--  <a id="myButton" style="display: none" class="button" title="Pid Form" href = ></a> -->
	<a id="myButton" style="display: none;box-shadow: 0 6px 10px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);background-color: #556B2F;border: none;color: white;text-align: center; text-decoration: none;font-size: 14px;margin: 4px 2px;cursor: pointer;-webkit-transition-duration: 0.4s;transition-duration: 0.4s;height: 30px;" href="javascript:fff()">PID Form</a>
  <img class="button" title="Show Patinet Details"  src='<his:path src="/hisglobal/images/plusinv.png"/>' id="showPatient"      tabindex="1" onclick ="<%=showDetail%>" >
  			<img class="button" title="Hide Patient Details"  src='<his:path src="/hisglobal/images/Minus.png"/>' id="hidePatient" style="display: none;"     tabindex="1" onclick ="<%=hideDetail %>" >


  			</his:SubTitleTag>
			<table width="100%">
			<tr>
		 			<td class="tdfont" width="25%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<bean:message key="Name"/>
		 				</font>
		 				</div>

		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatFirstName() %>
		 					<%=lstPatVO.getPatMiddleName() %>
		 					<%=lstPatVO.getPatLastName() %>
		 					<html:hidden name="InvestigationRaisingDtlFB" value="<%=lstPatVO.getPatFirstName()%>" property="patName" />
		 					</font>
		 				</div>
		 			</td>
		 			 <td class="tdfont" width="25%">
		 				<div align="right">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="CRNo."/>
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatCRNo() %>
		 				</font>
		 				</div>
		 			</td>


			</tr>
		</table>
		<div id="showhidePatient" style="display: none;">
		<table  width="100%">
		<tr>
		<td class="tdfont" width="25%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="Age/Gender"/>
		 				</font>
		 				</div>
		 			</td>
		 			<%

		 			if(lstPatVO.getPatGenderCode().equalsIgnoreCase("m"))
		 			gendertypforpid="m";

		 			if(lstPatVO.getPatGenderCode().equalsIgnoreCase("f"))
			 			gendertypforpid="f";


		 			%>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatAge()+"/"+lstPatVO.getPatGenderCode()%>
		 					</font>
		 				</div>
		 			</td>
		 			 <td class="tdfont" width="25%">
		 				<div align="right">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				 	<bean:message key="PrimaryCategory"/>
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatCategory() %>
		 				</font>
		 				</div>
		 			</td>
		 	<html:hidden name="InvestigationRaisingDtlFB" value="<%=lstPatVO.getPatCategoryCode()%>" property="patCatCode" />
		</tr>
		<tr>
		<td class="tdfont" width="25%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 						<bean:message key="FatherName"/>
		 				</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatGuardianName() %>
		 					</font>
		 				</div>
		 			</td>
		 			 <td class="tdfont" width="25%">
		 				<div align="right">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="MobileNo."/>
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatMobileNo() %>
		 											<html:hidden name="InvestigationRaisingDtlFB"
							property="mobileNo"  value="<%=lstPatVO.getPatMobileNo() %>"/>

		 				</font>
		 				</div>
		 			</td>

		</tr>
		</table>
		</div>

			</logic:present>

	<!-- IPD Logic LIST_APTBASED_TEST -->
			<logic:equal name="InvestigationRaisingDtlFB" property="patStatus"
				value="IPD">


				<logic:present name="<%=InvestigationConfig.LIST_ADMISSION_VO%>">
					<his:SubTitleTag name="Admission Details">
					 <div align="right" style="margin-right: 35px;">

                   Indications:<%-- <html:text name="InvestigationRaisingDtlFB" property="visitReason" <%-- value="<%=voPatEpisode.getVisitReason()%>" --%> <%-- maxlength="99" ></html:text> --%>
                  <!-- <input type="text" name="visitReason" maxlength="99" size="30"/> -->
                  <html:text name="InvestigationRaisingDtlFB" property="visitReason" maxlength="99" size="30"></html:text>
                  </div>
					</
  </his:SubTitleTag>
					<his:ContentTag>

						<%
							List<Inv_PatientAdmissionDtlVO> lstPatAdmissionVO=(List<Inv_PatientAdmissionDtlVO>)session.getAttribute(InvestigationConfig.LIST_ADMISSION_VO);

																						 	if(lstPatAdmissionVO!=null && lstPatAdmissionVO.size()>0 )
																						 	{
																						 		int sizePatAdmVO=lstPatAdmissionVO.size();


																						 		for(int k=0;k<sizePatAdmVO;k++)
																						 		{
																						 			Inv_PatientAdmissionDtlVO voPatAdm=lstPatAdmissionVO.get(k);
																						 			patCrNo=voPatAdm.getPatcrno();
																									%>
																									<table width="100%">
							<tr>
								<td class="tdfont" width="25%">
									<div align="right"><bean:message key="AdmittedDepartmentName"/> </div>
								</td>
								<td class="tdfonthead" width="25%">
									<div align="left">
										<%=voPatAdm.getAdmitteddepartmentname()%>
									</div>
								</td>
								<td class="tdfont" width="25%">
									<div align="right"><bean:message key="WardName"/></div>
								</td>
								<td class="tdfonthead" width="25%">
									<div align="left">
										<%=voPatAdm.getPatwardname()%>
									</div>
								</td>
							</tr>
							<tr>
								<td class="tdfont" width="25%">
									<div align="right"><bean:message key="RoomName"/></div>
								</td>
								<td class="tdfonthead" width="25%">
									<div align="left">
										<%=voPatAdm.getPatroomname()%>
									</div>
								</td>
								<td class="tdfont" width="25%">
									<div align="right"><bean:message key="BedName"/></div>
								</td>
								<td class="tdfonthead" width="25%">
									<div align="left">
										<%=voPatAdm.getBedname()%>
									</div>
								</td>
							</tr>
							<tr>
								<td class="tdfont" width="25%">
									<div align="right"><bean:message key="ConsultantName"/></div>
								</td>
								<td class="tdfonthead" width="25%">
									<div align="left">
										<%=voPatAdm.getConsultantName()%>
									</div>
								</td>
								<td class="tdfont" width="25%">
									<div align="right"><bean:message key="Diagnosis"/></div>
								</td>
								<td class="tdfonthead" width="25%">
									<div align="left">
										<%=voPatAdm.getDiagnosis()%>
									</div>
								</td>
							</tr>
						</table>
						<%
							}
																						 	}
						%>

					</his:ContentTag>

				</logic:present>
			</logic:equal>


			<!-- OPD/Emergency Logic -->
			<div id="container">



				<div id="blanket" style="display: none;"></div>
				<div id="popUpDiv" style="display: none;">
					<table id="addMoreValue" width="100%">
						<tr>
						</tr>

						<html:hidden name="InvestigationRaisingDtlFB"
							property="mandComboTextBoxComboNamesOnPage" />




						<html:hidden name="InvestigationRaisingDtlFB"
							property="finalMandCode" />
					</table>
				<center>
						<img src='/HISInvestigationG5/hisglobal/images/ok.gif'
							id='minusButton' onclick="popupIn('popUpDiv')"'>

				</center>
				</div>

				<div id="popUpDiv1" style="display: none;">
					<table id="addinstruction" width="100%">
						<tr>
						</tr>


					</table>
				<center>
						<img src='/HISInvestigationG5/hisglobal/images/ok.gif'
							id='minusButton1' onclick="popupIn('popUpDiv1')"'>

				</center>
				</div>
				<!-- end #mainContent -->
			</div>
			<logic:notEqual name="InvestigationRaisingDtlFB" property="patStatus"
				value="IPD">
				<logic:present name="<%=InvestigationConfig.LIST_EPISODE_VO%>">




					<his:SubTitleTag name="Episode Details">
						<%
							String showDetail="showPatDetails()";
																							 String hideDetail="hidePatDetails()";
						%>
						<img class="button" title="Show Patinet Details"
							src='<his:path src="/hisglobal/images/plusinv.png"/>' id="show"
							tabindex="1" onclick="<%=showDetail%>">
						<img class="button" title="Hide Patient Details"
							src='<his:path src="/hisglobal/images/Minus.png"/>' id="hide"
							style="display:none;" tabindex="1" onclick="<%=hideDetail%>">

                  <div align="right" style="margin-right: 35px;">

                   Indications:<%-- <html:text name="InvestigationRaisingDtlFB" property="visitReason" <%-- value="<%=voPatEpisode.getVisitReason()%>" --%> <%-- maxlength="99" ></html:text> --%>
                  <!-- <input type="text" name="visitReason" maxlength="99" size="30"/> -->
                  <html:text name="InvestigationRaisingDtlFB" property="visitReason" maxlength="99" size="30"></html:text>
                  </div>


					</his:SubTitleTag>

					<his:ContentTag>
						<table width="100%" id="episodeTable">
							<tr>
								<td class="tdfont" width="10%">
									<div>
										<b><bean:message key="Select"/></b>
									</div>
								</td>
								<td class="tdfont" width="25%"> <bean:message key="VisitDate"/> </td>
								<td class="tdfont" width="25%">    <bean:message key="Department/Unit"/> </td>
								<td class="tdfont" width="25%">  <bean:message key="Diagnosis"/> </td>
							</tr>
						</table>
						<%
							boolean isEpisodeChecked=true;
																						     if(InvestigationConfig.IS_EPISODE_BOUND.equals(InvestigationConfig.IS_EPISODE_BOUND_FALSE))
																						    	 isEpisodeChecked=false;

																						  	 List<Inv_EpisodeVO> lstPatEpisodeVO=(List<Inv_EpisodeVO>)session.getAttribute(InvestigationConfig.LIST_EPISODE_VO);

																						 	if(lstPatEpisodeVO!=null && lstPatEpisodeVO.size()>0 )
																						 	{
																						 		boolean firstTimeTravesa=true;
																						 		if(lstPatEpisodeVO.size()==1)
																						 		{
																						 			%>

																						 			<script type="text/javascript">

																						 			document.getElementById("show").style.display="none";


																						 			</script>

																						 			<%

																						 		}
																						 		int sizePatEpisodeVO=lstPatEpisodeVO.size();
																						 		for(int k=0;k<sizePatEpisodeVO;k++)
																						 		{
																						 			Inv_EpisodeVO voPatEpisode=lstPatEpisodeVO.get(k);
																						 			patCrNo=voPatEpisode.getPatCRNo();

																						 			if(firstTimeTravesa)
																						 			{
						%>
						<table width="100%">
							<tr>
								<td class="tdfont" width="10%">
									<div>
										<input type="radio" checked="checked" name="radioEpisode"
											value="<%=voPatEpisode.getPatepisodecode()%>"
											onclick="showRequisition('<%=voPatEpisode.getPatepisodecode() %>')" />
											<html:hidden name="InvestigationRaisingDtlFB" value="<%=voPatEpisode.getPatepisodecode()%>" property="selectedEpisode" />
								         	<html:hidden name="InvestigationRaisingDtlFB" value="<%=voPatEpisode.getPatepisodecode()+'#'+voPatEpisode.getPatvisittypecode()%>" property="selectedEpisodedetails" />
									</div>
								</td>

								<td class="tdfont" width="25%">
									<div>
										<%=voPatEpisode.getPatvisitdate()%>
									</div>
								</td>
								<td class="tdfont" width="25%">
									<div>
										<%=voPatEpisode.getDepartment()+" / "+voPatEpisode.getPatdeptunit()%>
									</div>
								</td>
								<td class="tdfont" width="25%">
									<div>
										<%=voPatEpisode.getDiagnosis()%>
									</div>
								</td>
							</tr>
							
						</table>
						<div id="showhide" style="display:none;">
						<%
							}

																								if(!firstTimeTravesa)
																								{
						%>


							<table width="100%">
								<tr>
									<td class="tdfonthead" width="5%" align="left">
										<div align="left">
											<input type="radio" name="radioEpisode"
												value="<%=voPatEpisode.getPatepisodecode()%>"
												onclick="showRequisition('<%=voPatEpisode.getPatepisodecode() %>')" <%if(isEpisodeChecked){%>
												checked="checked" <%}%> />
												<html:hidden name="InvestigationRaisingDtlFB" value="<%=voPatEpisode.getPatepisodecode()+'#'+voPatEpisode.getPatvisittypecode()%>" property="selectedEpisodedetails" />
												<%if(isEpisodeChecked){%>
												<html:hidden name="InvestigationRaisingDtlFB" value="<%=voPatEpisode.getPatepisodecode()%>" property="selectedEpisode" />
												
												<%}%>
												<%-- <input type="hidden" name="selectedEpisodeRadio" value="<%=voPatEpisode.getPatepisodecode()%>"> --%>
										</div>
									</td>

									<td class="tdfonthead" width="25%" align="left">
										<div align="left">
											<%=voPatEpisode.getPatvisitdate()%>
										</div>
									</td>
									<td class="tdfonthead" width="25%" align="left">
										<div align="left">
											<%=voPatEpisode.getDepartment()+" / "+voPatEpisode.getPatdeptunit()%>
										</div>
									</td>
									<td class="tdfonthead" width="25%" align="left">
										<div align="left">
											<%=voPatEpisode.getDiagnosis()%>
										</div>
									</td>
								</tr>
							

							</table>



						<%
							}	firstTimeTravesa=false;
																							isEpisodeChecked=false; // for only one selection
																						 		}  %>
																						 	</div>
																						 <% 	}

																						 	else
																						 	{

																						 		%>

																					 			<script type="text/javascript">

																					 			document.getElementById("episodeTable").style.display="none";
																					 			document.getElementById("show").style.display="none";


																					 			</script>

																					 			No Episode Details

																					 			<%

																						 	}
						%>

					</his:ContentTag>

				</logic:present>
			</logic:notEqual>
			<%
				String hide="display:none";
							  	String display="display:block";
							  	String bookMarkDisplay="";
							  	String searchLogicDisplay="";
							  	String checked="checked='checked'";
							  	String bookMarkChecked="";
							  	String searchLogicChecked="";
							     String isBookMark=((InvestigationRaisingDtlFB)request.getAttribute("InvestigationRaisingDtlFB")).getIsBookMark();
								  if(isBookMark!=null&&!isBookMark.equals(""))
								  {
									  if(isBookMark.equals("B"))
									  {
								  bookMarkDisplay=display;
								  searchLogicDisplay=hide;
								  bookMarkChecked=checked;
									  }
									  else
									  {
								  bookMarkDisplay=hide;
								  searchLogicDisplay=display;
								  searchLogicChecked=checked;
									  }
								  }
			%>

			<logic:notEqual name="InvestigationRaisingDtlFB" property="aptStatus"
					value="0">

			<logic:notEqual name="InvestigationRaisingDtlFB" property="aptStatus"
					value="2">

			<logic:notEqual name="InvestigationRaisingDtlFB" property="aptStatus"
					value="5">

				<div  style=" height: 40px;width:100%; overflow-y: scroll;float:left;background-color:#006A9F;" align="left"<%-- style=<%=bookMarkDisplay %> --%> >
                                     <div style="float:left;background-color:#006A9F;">
                                  <!--   <b style="font-size: 11px;float:left;margin-top:8px;"> BookMark Details:</b> -->

                                  <button disabled="true" title="Select Bookmark-^" onclick="" style="box-shadow: 0 6px 10px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);background-color: #006A9F;border: none;color: white;padding: 2px 10px;text-align: center; text-decoration: none;display: inline-block;font-size: 14px;margin: 4px 2px;cursor: pointer;-webkit-transition-duration: 0.4s;transition-duration: 0.4s;height: 30px;margin-left:-8px;">Select Bookmark:</button>

                                     </div >
											<div style="float:left;">
													<%
														Map<String,Map<String,List<String>>> mpBookMark=(Map<String,Map<String,List<String>>>)session.getAttribute(InvestigationConfig.MAP_BOOK_MARK);
													String bookMarkCode1="";
													String labTestArrayString1=(String)((InvestigationRaisingDtlFB)request.getAttribute("InvestigationRaisingDtlFB")).getLabTestCodeArray();
												 	   if(labTestArrayString1!=null&&!labTestArrayString1.equals(""))
												 	   {
												 		  String[] labTestArray1=labTestArrayString1.split("@");
												 		 for(String labTestSelected1:labTestArray1)
												 		 {
												 			labTestSelected1=labTestSelected1.replaceAll(";", "#");
												 			 String[] labTestSelecetedArray1=labTestSelected1.split("#");
												 			 
												 			 if(labTestSelecetedArray1[13]!=null)
												 			 {		 
												 			 if(!bookMarkCode1.contains(labTestSelecetedArray1[13]))
												 			 bookMarkCode1+=labTestSelecetedArray1[13]+"#";
												 			 }
												 		 
												 		 }
												 		//   System.out.println("daata"+bookMarkCode1);

												 	   }
																																																							  int countColour=1;
																																																							  //Adding Colors for BookMark
																																																							  List lstColors=new ArrayList();
																																																									  lstColors.add("red");
																																																									  lstColors.add("aqua");
																																																									  lstColors.add("teal");
																																																									  lstColors.add("silver");
																																																									  lstColors.add("silver");
																																																									  lstColors.add("white");
																																																									  lstColors.add("blue");
																																																									  lstColors.add("red");
																																																									  lstColors.add("green");
																																																									  lstColors.add("aqua");
																																																									  lstColors.add("teal");

																																																							  if(mpBookMark!=null)
																																																							  {

																																																								  Iterator itrBookMark=mpBookMark.keySet().iterator();
																																																								  while(itrBookMark.hasNext())
																																																								  {
																																																									  boolean flagg=false;
																																																									  String bkCodeHashName=(String)itrBookMark.next();
																																																									  String bookMarkCode=bkCodeHashName.split("#")[0];
																																																									  String bookMarkName=bkCodeHashName.split("#")[1];
																																																									  if(!bookMarkCode1.equals(""))
																																																									  {

																																																										  String[] bookmarks=bookMarkCode1.split("#");


																																																										for(int i1=0;i1<bookmarks.length;i1++)
																																																										{
																																																										    if(bookmarks[i1].equals(bookMarkCode))
																																																										    {
																																																										    	flagg=true;
																																																										    }
																																																										}
																																																									  }
													%>
                                                    <%if(flagg==false){ %>

														
														<div style="display: inline-block;background-color:#006A9F;">
														<button onclick="submitFormForBookMark('<%=bookMarkCode%>','SEARCHBOOKMARK')" style="box-shadow: 0 6px 10px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);background-color: #556B2F;border: none;color: white;padding: 2px 10px;text-align: center; text-decoration: none;display: inline-block;font-size: 14px;margin: 4px 2px;cursor: pointer;-webkit-transition-duration: 0.4s;transition-duration: 0.4s;height: 30px;"><%=bookMarkName%></button>
												</div>

														  <%}else{ %>


														<%-- <div align="left" border: 4px solid black;
															onclick="submitFormForBookMark('<%=bookMarkCode%>','SEARCHBOOKMARK')"
															style="border-color: red; width: 8pxheight:8px; cursor: pointer;">
															<%=bookMarkName%>
														</div> --%>
														<div style="display: inline-block;background-color:#006A9F;">
														<button onclick="submitFormForBookMark('<%=bookMarkCode%>','SEARCHBOOKMARK')"  style="box-shadow: 0 6px 10px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);background-color: #556B2F;border: none;color: white;padding: 2px 10px;text-align: center; text-decoration: none;display: inline-block;font-size: 14px;margin: 4px 2px;cursor: pointer;-webkit-transition-duration: 0.4s;transition-duration: 0.4s;height: 30px;background-color: #8FBC8F;color: yellow"><%=bookMarkName%></button>
													    </div>


                                                       <%} %>

													<%
														Map<String,List<String>> mpLab=mpBookMark.get(bkCodeHashName);
																																																									  Iterator itrLab=mpLab.keySet().iterator();
																																																									  while(itrLab.hasNext())
																																																									  {
																																																										  String labCode=(String)itrLab.next();
																																																										  List<String> lstTestCodes=mpLab.get(labCode);
																																																										  int sizeTestCodes=lstTestCodes.size();
																																																										  for(int i=0;i<sizeTestCodes;i++)
																																																										  {
																																																											  //Code To be added in future as per further requirement
																																																										  }
																																																									  }
																																																									  countColour++; // For Random Color generation as given in the list
																																																								      if(countColour==10)
																																																								    	  countColour=1;
																																																								  }
																																																							  }else{
													%>

													<bean:message key="NoBookMarkDataIsAvailable"/>

													<%
														}
													%>
												</div>
												
												
										</div>
										</logic:notEqual>
									</logic:notEqual>
			</logic:notEqual>
			<% if(session.getAttribute("raisingdetailsentry")!=null){ %>
				<div id="showentryy11" style="width: 100%;"  align="left">
									  <his:SubTitleTag name="Requisition/Test Details">
									  		<img class="button" title="Show Patinet Details"  src='<his:path src="/hisglobal/images/plusinv.png"/>' id="showentryy"      tabindex="1" onclick ="showentry()" >
										<img class="button" title="Hide Patient Details"  src='<his:path src="/hisglobal/images/Minus.png"/>' id="hideentryy"      tabindex="1" onclick ="hidentry()" >

 		                             </his:SubTitleTag>

 		                              <table width="100%" id="showentryy1" >
 		                         <tr>
													<td id="colorCycle" width="20%">
														<div align="center">
															<b><bean:message key="Test"/></b>
														</div>
													</td>

													<td id="colorCycle" width="20%">
														<div align="center">
															<b><bean:message key="Laboratory"/></b>
														</div>
													</td>

													<td id="colorCycle" width="20%">
														<div align="center">
															<b><bean:message key="Sample"/></b>
														</div>
													</td>
													<td id="colorCycle" width="20%">
														<div align="center">
															<b><bean:message key="Priority"/></b>
														</div>
													</td>
													<td id="colorCycle" width="20%">
														<div align="center">
															<b>Delete</b>
														</div>
													</td>


												</tr>

                                               </table>
									</div>


										<div id="showentryy2"  style="width: 100%; height: 50px;overflow-y: auto;"  align="left" >


 		                         <table width="100%" id="resultentrydetails">


                                       <%

 		                         //    String ResultEntryDetails=(String)((InvestigationRaisingDtlFB)request.getAttribute("InvestigationRaisingDtlFB")).getRequisitionDetailsforResultEntry();
                                       String ResultEntryDetails=(String) session.getAttribute("raisingdetailsentry");
                                       // String  lstSampleAccepted=(String)mp.get(InvestigationConfig.LIST_PRVTESTDTL_AJAX);

                                       if(ResultEntryDetails!=null && !ResultEntryDetails.equals(""))
                                       for(int k=0;k<ResultEntryDetails.split("@").length;k++)
 		                             {

 		                            	 String newval=ResultEntryDetails.split("@")[k];
 		                              	    String status=newval.split("#")[0];
 		                              	 String labName=newval.split("#")[1];
 		                              	String sampleName=newval.split("#")[2];
 		                              	String testName=newval.split("#")[3];
 		                              	String sampleComboStr=newval.split("#")[4];
 		                              	String testType=newval.split("#")[5];
 		                              	String isAppointment=newval.split("#")[6];
 		                              	String isConfidential=newval.split("#")[7];
 		                              	String sampleCode=newval.split("#")[8];
 		                              	String priority=newval.split("#")[9];
 		                              	String testGroupCode=newval.split("#")[10];
 		                              	String testGroupType=newval.split("#")[11];
 		                              	String isMandatoryReq=newval.split("#")[12];
 		                              	String reqDate=newval.split("#")[15];
 		                              	String labCode=newval.split("#")[16];
 		                              	String testCode=newval.split("#")[17];
 		                              	String prvReqStatus=newval.split("#")[18];
 		                              	String reqNo=newval.split("#")[19];
 		                              	String grpname=newval.split("#")[20];
 		                            	 int kk=k+1;

 		                             if(testGroupCode.equals("0"))
 		                             {

 		                             }
 		                             else
 		                             {
 		                            	testName=testName+"("+grpname+")";
 		                             }

 		                              %>
                                          <tr  id="<%=kk%>" >
													<td class="tdfonthead" id="" width="20%">
														<div align="center">
														<%=testName %>
														</div>
													</td>

													<td class="tdfonthead" id="" width="20%">
														<div align="center">
															<%=labName %>
														</div>
													</td>

													<td class="tdfonthead" id="" width="20%">
														<div align="center">
															<%=sampleName %>
														</div>
													</td>
													<td class="tdfonthead" id="" width="20%">
														<div align="center">
															<%=priority %>
														</div>
													<!-- <td id="colorCycle" width="3%">
														 <div align='left'>
														 <b>Instruction</b>
														</div>



													</td> -->
													<td class="tdfonthead" id="" width="20%">
														<div align='center'>
														<img class="button" title="Delete Details"  src='<his:path src="/hisglobal/images/Minus.png"/>'  onclick ="deleteRowPrvReqDtlresultentry('<%=labCode %>','<%=testCode %>','<%=reqNo %>','<%=kk %>',,'<%=testGroupCode%>')" >
														</div>
													</td>

												</tr>



									 <% kk++;
									 } %>


                                 </table>

									</div>
									<%} %>

			<his:ContentTag>



				<table width="100%">
					<tr>
						<td class="tdfont" width="100%"><logic:present
								name="<%=InvestigationConfig.LIST_EPISODE_VO%>">
								<div id="tabs" align="left"  >
									<ul   >
										<li  ><a href="#tabs-1"  id="currentReqDtl" onclick="showCurrDetail()" style="cursor: pointer">   Current Requisition Details  <%-- <bean:message key="CurrentRequisitionDetails"/> --%> </a></li>
										<li><a href="#tabs-2" id="prvReqDtl"  onclick="showPrvDetail()" style="cursor: pointer">   Previous Requisition Details<%-- <b><bean:message key="PreviousRequisitons"/></b> --%></a></li>

										 <div align="center" style="margin-left: 1000px;">
						<li>
 		                     <font color="red">  <b style="font-size: 11px;color: white;"><bean:message key="Advisedby"/></b></font>
							<input type="text"  id="automplete-4" name="advisedBy"
							size="30"
							onkeypress="if(event.keyCode==13) checkAdvise();return validateAlphaNumericOnly(event,this);clearTestAndGroup();"
							 onblur="checkAdvise();">



							 </li>
				</div>
									</ul>


										<div id="tabs-1">

										<div id="selLabTest" class="subDivStyle">
											<%
												// Logic ofor persisting the selected Lab Test details even after page Submit
																																												 	   String labTestArrayString=(String)((InvestigationRaisingDtlFB)request.getAttribute("InvestigationRaisingDtlFB")).getLabTestCodeArray();
										 	                                                                                                                                               String labTestArrayStringnew=(String)((InvestigationRaisingDtlFB)request.getAttribute("InvestigationRaisingDtlFB")).getNewlabtestcodearray();

											if(labTestArrayString!=null&&!labTestArrayString.equals(""))
																																												 	   {
											%>
											<his:SubTitleTag name="Selected Test/Lab Details">

						<div style="margin-right: 250;" >
												<button id="estimatebtn" class="tooltipamount" style="background-color:green;color:white;margin-top:0px" type="button" class="btn btn-sm" data-toggle="popover"
					title="" data-html="true"
					data-content="">
					<b> Total Cost:</b> <i class="fa fa-inr" aria-hidden="true"></i>	<b>  <span id='estimaterate'>0.00</span></b>
					
					<span class="tooltiptext" id="setam" style="width:200"></span>
				</button>
												
												</div>
												
												</div>
												
											<div align="right" class="custom-dropdown small">
											Priority &nbsp&nbsp&nbsp
											 <html:select name="InvestigationRaisingDtlFB" property="priorityAll" onchange="changeAllPriority(this)">
													   <html:option value='<%=InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_NORMAL%>'>Normal</html:option>
													     <html:option value='<%=InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_URGENT%>'>Urgent</html:option>

													  </html:select>
											</div>
 		                                            </his:SubTitleTag>
											<table width="100%" id="tableSelectedLabTestIdHide"
												cellspacing="1" cellpadding="0">
										
												<tr>
													<td id="colorCycle" width="18%">
														<div align="left">
															<b><bean:message key="Test"/></b>
														</div>

													</td>

													<td id="colorCycle" width="18%">
														<div align="left">
															<b><bean:message key="Laboratory"/></b>
														</div>
													</td>

													<td id="colorCycle" width="10%">
														<div align="left">
															<b>Sample/Site</b>
														</div>
													</td>
													<td id="colorCycle" width="14.5%">
														<div align="left">
															<b>Rates</b>
														</div>
													</td>
													<td id="colorCycle" width="14.5%">
														<div align="left">
															<b><bean:message key="Appointment"/></b>
														</div>
													</td>
													<td id="colorCycle" width="6%">
														<div align="left">
															<b><bean:message key="Priority"/></b>
														</div>
													
													<td id="colorCycle" width="3%">
														<div align='left'>

														</div>
													</td>

												</tr>

											</table>

											<%
												int j=0;

																																				 	   	//labTestArray=chkVal1@chkVal2..... and chkVal1=labCode#labName#testCode#testName#sampleComboString#testType#isAppointment#isConfidential#sampleCode#priority
																																				 	   String[] labTestArray=labTestArrayString.split("@");
																																				 	 	 String[] labTestArraynew=null;
																																				 	   	if(labTestArrayString!=null )
																																				 	   	{
																																				 	   	labTestArraynew=new String[labTestArrayStringnew.length()];
																																				 	   	  labTestArraynew=labTestArrayStringnew.split("@");
																																				 	   	}
																																				 	   	Set setLabTestCode=new HashSet();
																																				 	   	String[] islabalreadycallapp=new String[labTestArray.length];
																																				 	  Map<String,String> labbsedmap=new HashMap<String,String>();
																																				 	 %>
																																				 	 <script>
																																				 	  	var map1 = new Object(); // or var map = {};
																																						  
																																				 	  	 </script>
																																				 	  	<%
																																				 	  for(String labTestSelected:labTestArray)
																																				 	   {
																																				 	   	labTestSelected=labTestSelected.replaceAll(";", "#");
																																				 		   String[] labTestSelecetedArray=labTestSelected.split("#");
																																				 		   String labCode=labTestSelecetedArray[0];
																																				 		  	String labName=labTestSelecetedArray[1];
																																				 		 	String testCode=labTestSelecetedArray[2];
																																				 			String testName=labTestSelecetedArray[3];
																																				 			String sampleComboStr=labTestSelecetedArray[4];
																																				 			String testType=labTestSelecetedArray[5];
																																				 			String isAppointment=labTestSelecetedArray[6];
																																				 			String isConfidential=labTestSelecetedArray[7];
																																				 			String sampleCode=labTestSelecetedArray[8];
																																				 			String priority=labTestSelecetedArray[9];
																																				 			String testGrroupCode=labTestSelecetedArray[10];
																																				 			String testGrroupType=labTestSelecetedArray[11];
																																				 			String isMandatoryReq=labTestSelecetedArray[12];
																																				 			String bookMarkCode=labTestSelecetedArray[13];
																																				 		 	String offlineAppoitmentDate=labTestSelecetedArray[14];
																																				 		 	String flagAdvised="";
																																				 		 	String mandTextBoxCombo=labTestSelecetedArray[15];

																																				 		 	String mandTextBoxComboNames=labTestSelecetedArray[16];


																																				 		 	String finalMandCode=labTestSelecetedArray[17];
                                                                                                                                                              String testGroupCode=labTestSelecetedArray[10];


																																				 		 	String offlineAptNo=labTestSelecetedArray[19];

																																				 	  	 String instruction=labTestSelecetedArray[20]==null?"No Instructions Available":labTestSelecetedArray[20];

																																				 	  	 /* dddddc */
																																				 	  	 String isreqFormNeeded=labTestSelecetedArray[24];

																																				 	  	 String ispidshown=labTestSelecetedArray[27];
																																				 	  	String islababsedapp="";
																																			 		 		if(labTestSelecetedArray.length>28)
																																			 		 			islababsedapp=labTestSelecetedArray[28];

																																				 	  	
																																				 	  	 
																																				 	  	 
																																				 	  	
																																				 	  	  String siteshow="1";

																																				 	  	 for(String labTestSelectedneww:labTestArraynew)
																																					 	   {
																																					 		   String[] labTestSelecetedArraynew=labTestSelectedneww.split("#");

																																				 	  		 String labCodenew=labTestSelecetedArraynew[0];

																																					 		 	String testCodenew=labTestSelecetedArraynew[2];

																																					 		 	if((labCodenew.equals(labCode)) && (testCodenew.equals(testCode)))
																																					 		 	{
																																					 		 		if(labTestSelecetedArraynew.length>28)
																																							 	  	  siteshow=labTestSelecetedArraynew[28];

																																					 		 	}
																																					 	   }



																																				 	  	 instruction=instruction.replace("\r\n","<br>");
																																				 	 // 	 System.out.println("coup"+instruction);

                                                                                                                                                          if(sampleComboStr.contains("advised"))
                                                                                                                                                          {  sampleComboStr=sampleComboStr.split("&")[1];

                                                                                                                                                          	 flagAdvised="1";
                                                                                                                                                          }

																																				 			if(!setLabTestCode.contains(labCode+"#"+testCode))
																																				 			{


																																				 			String _paraId=labCode+"^"+testCode+"^0^0^0^0^0";
																																				 			String paraNum=labCode+"^"+testCode+"^0^0^0^0^0";
											%>
											<%
											String divAptTagRowold="aptTagRow_"+labCode+"_"+testCode;  //+labCode+testCode
                                                  boolean fl=false;
											int count=0;
											String makeid="";
                                           String onLoadRow="";
                                           String onLoadRownew="";

                                           String labsedfunctn="";

											if(islababsedapp.equals("1"))
								 			{
												String divAptTagRow="aptTagRow_"+labCode+"_"+"0";

									 			  if(islababsedapp.equals("1"))
											 	  	 {
									 				  
									 				  if(!labbsedmap.containsKey(labCode))
											 	  		{labbsedmap.put(labCode, divAptTagRow);
											 	  		fl=false;
											 	  		}
									 				  else
									 				  {
									 					 makeid=  (String)labbsedmap.get(labCode);
									 					 count=2;
									 					 fl=true;
									 				  }
									 				  
									 				  
											 	  	 }
									 			  
												_paraId=labCode+"^"+"0"+"^0^0^0^0^0";
												paraNum=labCode+"^"+"0"+"^0^0^0^0^0";
								 			 onLoadRow="getAptSlotDetails('"+patCrNo+"','"+paraNum+"','','aptTagRow_"+labCode+"_"+'0'+"','4')";
								 			 //  System.out.println("----onLoadRow------"+onLoadRow);
								 				onLoadRownew="setappslotdtls('"+makeid+"','"+labCode+"')";

								 			  labsedfunctn="getappdetails('"+makeid+"','"+labCode+"')";
								 			}
								 			else	
								 			{

								 				onLoadRow="getAptSlotDetails('"+patCrNo+"','"+paraNum+"','','aptTagRow_"+labCode+"_"+testCode+"','2')";		
								 			}	
								 				  String call="createtable();";
                                        
								 				   if(islababsedapp.equals("1")){
		                                    //    	   System.out.println("----appointment present------");
													%>
													<script>

													
													<% if(fl){%>
													
													
													<%}else{%>
													
														<%=onLoadRow%>
												<%	}%>
													
									 	   
								        </script>




		<%} 	else  if(isAppointment.equals("1")){
                                        //	   System.out.println("----appointment present------");
											%>
											<script>

							 	   <%=onLoadRow%>
						        </script>
<%} else{}%>

                 <%

						       // if(ispidshown!=null && ispidshown.equals("1")){
						        	%>
						        			<script >

						        			callinggggg('<%=labCode%>','<%=testCode%>','<%=ispidshown%>');

						        </script>

						        <%
						//        }
                 %>

				<table width="100%" cellspacing="1" cellpadding="0"
												  >

												<tr>
													<td width="18%" class="tdfonthead">
														<div align="left" id="checkOnSave">
															<%=testName%>
															<input type="hidden" name='testCode'
																value='<%=testCode%>'>
																<% if(!isreqFormNeeded.equals("0")){ %>
														<%-- &nbsp&nbsp<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick="ShowRequistionForm('<%=testCode%>','<%=testName%>','<%=labCode%>','<%=labName%>')"> --%>
																<%}else{;} %>
														</div>
													</td>

													<td width="18%" class="tdfonthead">
														<div align="left">
															<%=labName%>
															<input id='labaddtbl#<%=j%>' type="hidden" name='labCode' value='<%=labCode%>'>
														</div>
													</td>

													<td width="10%" class="tdfonthead">
														<div align="left">
															<%
																if (testType.equals("P")) {



																String onChangesite = "setsiteUsingAjax(this,'"
																									+ labCode
																									+ "','"
																									+ testCode
																									+ "')";

																								//	System.out.println("fnctn"+ onChangesite);
																									//String chandan="chandan";
															%>

															<%
															if(siteshow==null || siteshow.equals("null") || siteshow.equals(""))
															{
																siteshow="1";
															}

																%>
															  <select value="<%=siteshow %>" style='width:55;' id='siteaddtbl#<%=j%>' name='site' onChange="<%=onChangesite%>">
															 <%if(siteshow.equals("1")) {%>
															  <option value="1" selected>NA</option>
															  <%}else{ %>
															   <option value="1" >NA</option>
															   <%} %>

															   <%if(siteshow.equals("2")) {%>
															  <option value="2" selected>Both</option>
															  <%}else{ %>
															  <option value="2">Both</option>
															   <%} %>

															   <%if(siteshow.equals("3")) {%>
															  <option value="3" selected>Left</option>
															  <%}else{ %>
															   <option value="3">Left</option>
															   <%} %>

															   <%if(siteshow.equals("4")) {%>
															    <option value="4" selected>Right</option>
															  <%}else{ %>
															     <option value="4">Right</option>
															   <%} %>

															  </select>

																										<%
																} else {


																	if(flagAdvised.equals("1"))

																	{
																		%>
																		<%=sampleComboStr%>
																		<%
																	}

																	else
																	{
																								String onChangeSample = "setSampleCodeUsingAjax(this,'"
																										+ labCode
																										+ "','"
																										+ testCode + "')";
															%>
															<select id='sampletbl#<%=j%>' name='sampleInfo' tabindex='1'
																onChange="<%=onChangeSample%>">
																<%=sampleComboStr%>
															</select>
															<%
																}}
															%>
														</div>
													</td>
													<td  width="14.5%" class="tdfonthead">

														<%
															if (isMandatoryReq.equals("1")) {

																%>
																<html:hidden name="InvestigationRaisingDtlFB" value="<%=finalMandCode%>"  property="finalMandCodeValuesOnBookmark" />
													
														
														
														 var testco1="";
													  var testna1="";
													  var  testr1 = "";
													  var grptyp1 =<%=testGrroupCode%>;
													  var colorcodee="";
													  grptyp1=grptyp1;
													//  alert("grptyp1"+grptyp1);
													
													  if( grptyp1!=null && grptyp1!="undefined" && grptyp1!="null" && grptyp1!="0")
													  {
														testco1=<%=testGrroupCode%>
													   testr1 = grptestratess[grptyp1];
													
													  }
												     else
													  {
													  
													//   alert("not grp"+testco);
												    		testco1=<%=testCode%>
												    		
												    		
												    		if(testratess[testco1]!=undefined)
   		                                                 	{
	                                                         testr = testratess[testco1];
   		                                                       	}
   	                                             	else
   		                                                     	{
   		                                    	 testr ="0@#"+testco1+"@#"+"";
   		                                                         	}
   			
													 //  testr1 = testratess[testco1];
													
													  }
													
												     // alert("testr1"+testr1);
													  var testrnew1="";
													  testrnew1=testr1;
													  
													  if(testr1.includes("^"))
													  {
													//	  alert("true");
														  testr1=testr1.split("^")[0];
														  colorcodee=testrnew1.split("@#")[2];
													  }
													  else{
														  testr1=testr1.split("@#")[0];
														  colorcodee=testrnew1.split("@#")[2];
														//  alert("false");
													  }
													  
													  
													  /* if (map1[testco1]==undefined)
														 {
														 
														  if(testrnew1.includes("@#"))
														  {
															  //testname1+=testrnew1.split("@#")[1]+"#@";
														  }
														  else
															  {
															  
															  }
														  
													 var testrate1=testr1+"#@";
													  //testname+=testna1+"#@";
													  map1[testco1] = testr1;
													  
														 }
													 else
														 {
														 
														 } */
														 if(testr1=="0")
														 {
															  testr1="&nbsp;&nbsp;Free";
														 }
														 else
															 {
															 testr1="&nbsp;&#8377;&nbsp;"+testr1
															 }
													  //   alert("final rate"+testr1);
				                                        
											var tdtag="<div align='left' style='height:18 ;border-left : 4px solid "+colorcodee+" '>";		     
													</script>
                                            
    
                                                <script>
												document.writeln(tdtag)
												if (map1[testco1]==undefined)
												 {
												//	alert("map1[testco1]"+map1[testco1]);
												document.writeln(testr1)
												 }
												else
													{
												//	alert("not map1[testco1]"+map1[testco1]);
													var zeroo="";
													document.writeln(zeroo)
													}
												map1[testco1] = testr1;
												</script></div>
												
												
														
														
<% 	} else {
	
 %>
														<%-- <div align="left"><bean:message key="NA"/></div> --%>
												
													
														<!-- <div align="left">testrnew</div> -->
														
															<script>

													  var testco1="";
													  var testna1="";
													  var  testr1 = "";
													  var grptyp1 =<%=testGrroupCode%>;
													  var colorcodee="";
													  grptyp1=grptyp1;
													//  alert("grptyp1"+grptyp1);
													
													  if( grptyp1!=null && grptyp1!="undefined" && grptyp1!="null" && grptyp1!="0")
													  {
														testco1=<%=testGrroupCode%>
													   testr1 = grptestratess[grptyp1];
													
													  }
												     else
													  {
													  
													//   alert("not grp"+testco);
												    		testco1=<%=testCode%>
													  // testr1 = testratess[testco1];
													
													  if(testratess[testco1]!=undefined)
   		                                                 	{
														  testr1 = testratess[testco1];
   		                                                       	}
   	                                             	else
   		                                                     	{
   	                                             	testr1 ="0@#"+testco1+"@#"+"";
   		                                                         	}
													  
													  }
													
												     // alert("testr1"+testr1);
													  var testrnew1="";
													  testrnew1=testr1;
													  
													  if(testr1.includes("^"))
													  {
													//	  alert("true");
														  testr1=testr1.split("^")[0];
														  colorcodee=testrnew1.split("@#")[2];
													  }
													  else{
														  testr1=testr1.split("@#")[0];
														  colorcodee=testrnew1.split("@#")[2];
														//  alert("false");
													  }
													  
													 
														 if(testr1=="0" || testr1=="undefined")
														 {
															  testr1="&nbsp;&nbsp;Free";
														 }
														 else
															 {
															 testr1="&nbsp;&#8377;&nbsp;"+testr1
															 }
													  //   alert("final rate"+testr1);
				                                        
											var tdtag="<div align='left' style='height:18 ;border-left : 4px solid "+colorcodee+" '>";		     
													</script>
                                            
    
                                                <script>
												document.writeln(tdtag)
												if (map1[testco1]==undefined)
												 {
												//	alert("map1[testco1]"+map1[testco1]);
												document.writeln(testr1)
												 }
												else
													{
												//	alert("not map1[testco1]"+map1[testco1]);
													var zeroo="";
													document.writeln(zeroo)
													}
												map1[testco1] = testr1;
												</script></div>
													
														 <%
 	}
 %>
													</td>


                                          <%

                                          String labbasedapp=(String)session.getAttribute("labbasedapppointment");

                                          String labbasedaptdatetime="";
                                          String labbasedaptdatetimelab="";

                                         if(labbasedapp!=null && !labbasedapp.equals(""))
                                       	  {
                                        	 labbasedaptdatetime=labbasedapp.split("#")[2];
                                       	  labbasedaptdatetimelab=labbasedapp.split("#")[4];
                                       	  }

                                         if(!labbasedaptdatetime.equals("") && labbasedaptdatetimelab.equals(labCode))
                                 		{


                                          %>
                                          <td  width="14.5%" class="tdfonthead">

                                          <div align="left"><%=labbasedaptdatetime %></div>
                                          </td>
                                          <%}else{ %>
													<%
														if (!offlineAppoitmentDate.equals("")
																							&& !offlineAppoitmentDate
																									.equals("null"))

																					{
													%>
													<td  width="14.5%" class="tdfonthead">
														<div align="left"><%=offlineAppoitmentDate%>

															<html:hidden name="InvestigationRaisingDtlFB"
																value="<%=offlineAppoitmentDate%>"
																property="offlineAppoitmentDtl" />

																<html:hidden name="InvestigationRaisingDtlFB"
																value="<%=offlineAptNo%>"
																property="offlineAptDtl" />

														</div>
													</td>

													<%
														} else {
													%>
													<td width="14.5%" class="tdfonthead">
													
													<%
															if (islababsedapp.equals("1")) {
																
															boolean flg=true;
														%>
                                                    <%     if(fl)
                                                         {%>
                                                         
                                                        
														<div align="left"
															name='<%=makeid%>' id="aptTagRow_<%=labCode%>_<%='0'%>" ></div> <%--labCode+testCode--%>
                                                      <script>
                                                       <%=labsedfunctn%>
                                                       </script>
                                                      <%     }
                                                         else
                                                         {%>
                                                         
                                                         <div align="left"
															id="aptTagRow_<%=labCode%>_<%='0'%>" ></div> <%--labCode+testCode--%>
                                                         <script>
                                                        													<%=onLoadRownew%>
                                                        													</script>
                                                         <%  }%>
                                    					<%
														String setDate ="";
														boolean flgg=true;
													 	if(flgg)
														{
															
															 setDate = "setDateInApoitment('"+patCrNo+"','"+paraNum+"','this','aptTagRow_"+labCode+"_"+'0'+"')";

														}
														else
														{
														 setDate = "setDateInApoitment('"+patCrNo+"','"+paraNum+"','this','aptTagRow_"+labCode+"_"+testCode+"')";
														}
														%> <input type="hidden" name="dateTag" 
														onchange="<%=setDate%>" id="datepicker">
														</p> <%
                                                         	} 
															else	if (isAppointment.equals("1")) {
																
															boolean flg=true;
														%>
                                                  
                                                         <div align="left"
															id="aptTagRow_<%=labCode%>_<%=testCode%>" ></div>
                                                        
                                    					<%
                                    					String setDate ="";
														boolean flgg=false;
													 	
														 setDate = "setDateInApoitment('"+patCrNo+"','"+paraNum+"','this','aptTagRow_"+labCode+"_"+testCode+"')";
														
														%> <input type="hidden" name="dateTag"
														onchange="<%=setDate%>" id="datepicker">
														</p> <%
 	} else {
 %>
														<div align="left">NA</div> <%
 	}
 %>
													</td>

													<%}}
													%>
													<td width="6%" class="tdfonthead">
														<div align="left">
															<%
																String onChangePriority = "setPriorityUsingAjax(this,'"
																									+ labCode
																									+ "','"
																									+ testCode
																									+ "')";

																								//	System.out.println("fnctn"+ onChangePriority);
																									String chandan="chandan";
															%>

															<select id='priorityaddtbl#<%=j%>' name='priority' tabindex='1'
																onChange="<%=onChangePriority%>">
																<option
																	value="<%=InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_NORMAL%>"><bean:message key="Normal"/></option>
																<option
																	value="<%=InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_URGENT%>"><bean:message key="Urgent"/></option>


															</select>
														</div>
													</td>
													

													<td width="3%" class="tdfonthead">
														<div align='left'>
															<%
																String strdelFunction = "deleteRow('"
																									+ j + "','" + labCode + "','"
																									+ testCode + "','"
																											+ testGroupCode + "')";
															%>
														 	<img src='/HISInvestigationG5/hisglobal/images/minus.gif'
																id='minus' onClick="<%=strdelFunction%>">
																<%
															//	System.out.println("chinstruction"+instruction);
																if(!instruction.equals("null") && !instruction.equals("NA")   ){ %>
														 <img  class="button" title="Show Instructions"  src='<his:path src="/hisglobal/images/add_remarks_sml.jpg"/>' id="<%=j%>showInstruction"      tabindex="1" onclick ="showInstructions5('<%=instruction%>');">
															<%} else{;} %>

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
          <img src='/hisglobal/images/print_tab.gif'  onClick="callPrint();">

		</div>



 	<!-- END -- TO DISPLAY INSTRUCTIONS -->

													</td>




												<%
													j++;
																				setLabTestCode.add(labCode + "#"
																						+ testCode);
																			}
																		}
												%>

											</table>
											<%
												} else {
											%>
											<div id="tableSelectedLabTestId" style="display: none;">
											<his:SubTitleTag name="Selected Test/Lab Details">
											
											<div style="margin-right: 250;" >
												<button id="estimatebtn" class="tooltipamount" style="background-color:green;color:white;margin-top:0px" type="button" class="btn btn-sm" data-toggle="popover"
					title="" data-html="true"
					data-content="">
					<b> Total Cost:</b> <i class="fa fa-inr" aria-hidden="true"></i>	<b>  <span id='estimaterate'>0.00</span></b>
					<span class="tooltiptext" id="setam"></span>
				</button>
												
												</div> 
											
											<div align="right" >
											Priority &nbsp&nbsp&nbsp
											 <span class="custom-dropdown small">
											 <html:select name="InvestigationRaisingDtlFB" property="priorityAll" onchange="changeAllPriority(this)">
													   <html:option value='<%=InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_NORMAL%>'>Normal</html:option>
													     <html:option value='<%=InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_URGENT%>'>Urgent</html:option>

													  </html:select>
											</span>
											</div>
 		 </his:SubTitleTag>

											<table width="100%" id="tableSelectedLabTestIdHide"
												cellspacing="1" cellpadding="0">
											<!--  	<tr>
													<td width="18%" id="tdfonthead" class="tdfont">
														<div align="left">
															<b><bean:message key="Priority"/></b>
														</div>
													</td>
													<td width="18%" id="tdfonthead" class="tdfont">
													 <html:select name="InvestigationRaisingDtlFB" property="priorityAll" onchange="changeAllPriority(this)">
													   <html:option value='<%=InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_NORMAL%>'>Normal</html:option>
													     <html:option value='<%=InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_URGENT%>'>Urgent</html:option>

													  </html:select>
													</td>
													<td width="10%" id="tdfonthead" class="tdfont">
													</td>
													<td width="14.5%" id="tdfonthead" class="tdfont">
													</td>
													<td width="14.5%" id="tdfonthead" class="tdfont">
													</td>
													<td width="6%" id="tdfonthead" class="tdfont">
													</td>
													<td width="3%" id="tdfonthead" class="tdfont">
													</td>
												</tr>
											-->
												<tr>
													<td width="18%" id="colorCycle">
														<div align="left">
															<b><bean:message key="Test"/></b>
														</div>
													</td>
													<td width="18%" id="colorCycle">
														<div align="left">
															<b><bean:message key="Laboratory"/></b>
														</div>
													</td>

													<td width="10%" id="colorCycle">
														<div align="left">
															<b>Sample/Site</b>
														</div>
													</td>
													<td  width="14.5%" id="colorCycle">
														<div align="left">
															<b>Rates</b>
														</div>
													</td>
													<td width="14.5%" id="colorCycle">
														<div align="left">
															<b><bean:message key="Appointment"/></b>
														</div>
													</td>
													<td width="6%" id="colorCycle">
														<div align="left">
															<b><bean:message key="Priority"/></b>
														</div>
													</td>

													<td width="3%" id="colorCycle">
														<div align='left'>

														</div>
													</td>
												</tr>
											</table>



											</div>

											<%
												}
											%>

										</div>


                       <div class="subDivStyle">
                                <his:SubTitleTag name="Requisition Forms">
											<img class="button" title="Show Patinet Details"  src='<his:path src="/hisglobal/images/plusinv.png"/>' id="reqformshow"      tabindex="1" onclick ="showReqForms()" >
										<img class="button" title="Hide Patient Details"  src='<his:path src="/hisglobal/images/Minus.png"/>' id="reqformhide"      tabindex="1" onclick ="reqformhideone()" >
<!-- 								<button id="myButton" style="display: none">click me pid!</button>
 -->											 </his:SubTitleTag>
           </div>

                <div id="dialog" title="PID FORM" align="left" style="font-size: large;width:auto; margin-left:150px; height: 201px;">

                <%
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
 PID &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;
 <input type="text" name="pidenter" maxlength="32" id="pidenter" value="<%=piddddate%>" data-initial="<%=piddddate%>" onkeypress="return isdeleteall(this,'<%=gendertypforpid%>')" size="40" onblur="ipdesxistonfocus(this)" >
 
 <% } else { %>
 PID &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;
	 <input type="text" name="pidenter" maxlength="32" id="pidenter" value="<%=piddddateoldval%>" data-initial="<%=piddddate%>"  disabled onkeypress="return isdeleteall(this,'<%=gendertypforpid%>')" size="40" onblur="ipdesxistonfocus(this)" >
  <% } %>
 <br><br>

<% if(pidoldreqset.split("#")[3].equals("null")) { %>

    Initiated Type:&nbsp;&nbsp;&nbsp;
    <input type="radio" name="yes_no"  value="0" id="yesno0"> Client Initiated&nbsp;&nbsp;
    <input type="radio" name="yes_no" value="1" id="yesno1"> Provider Initiated

<% } else if(initiatestypeeee.equals("0")) { %>
  	
  	Initiated Type :&nbsp;&nbsp;&nbsp;
	<input type="radio" name="yes_no"  value="0" id="yesno0" checked disabled> Client Initiated&nbsp;&nbsp;
	<input type="radio" name="yes_no" value="1" id="yesno1" disabled> Provider Initiated
    <br><br>
	<div id="followupdiv" style="font-size: large;" class="tooltip" >FollowUp &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;
	<input type="radio"  name="followupcheck" id="followupchecky" onclick="followupfn(this)" value="y" checked> Yes&nbsp;&nbsp;
    <input type="radio"  name="followupcheck" id="followupcheckn" onclick="followupfn(this)" value="n" > No
    
    <span class="tooltiptext">To make changes in initiated type change FollowUp To "No" </span>
	</div>
	
<% } else if (initiatestypeeee.equals("1")) { %>
    
    Initiated Type :&nbsp;&nbsp;&nbsp;
	<input type="radio" name="yes_no"  value="0" id="yesno0" disabled> Client Initiated&nbsp;&nbsp;
	<input type="radio" name="yes_no" value="1" id="yesno1" checked disabled> Provider Initiated
    <br><br> 
    <div id="followupdiv" style="font-size: large;" class="tooltip" >FollowUp &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;
	<input type="radio"  name="followupcheck" id="followupchecky" onclick="followupfn(this)" value="y" checked> Yes&nbsp;&nbsp;
    <input type="radio"  name="followupcheck" id="followupcheckn" onclick="followupfn(this)" value="n"> No
    
    <span class="tooltiptext">To make changes in initiated type change FollowUp To "No" </span>
	</div>

<% } %>	
	


<br><br>
<img style="margin-left:300px;" class="button"
							src='<his:path src="/hisglobal/images/btn-sv.png"/>'
							id="" style="cursor: pointer;height:28" tabindex="1"
							onclick="savepidd()">

</div>


                           <div class="subDivStyle" id="reqformss" style="display: none">
                               <table width="100%" border="1" id="tbll">
                               <tr>
                       <td style="font-weight: bold;color: brown;">TEST NAME</td>
                      <td style="font-weight: bold;color: brown;">REQUISITION FORMS</td>
                      </tr>
                               </table>
                 </div>


                                 <div class="subDivStyle" >
                                <his:SubTitleTag name="Labs">

											 </his:SubTitleTag>
											 <div style="overflow-y: scroll;">

											<!--  <table width="100%" border="1">
												<tr align="left">

												 -->
												<%


												List lstLabTestVO1 = (List) session.getAttribute(InvestigationConfig.MAP_LAB_CODE_TERIIFF_CHANGE);

												if(lstLabTestVO1!=null && lstLabTestVO1.size()>0)
												{

												//	System.out.println("=11111="+lstLabTestVO1.get(0));

													for(int p=0;p<lstLabTestVO1.size();p++)
													{
													String val=lstLabTestVO1.get(p).toString().replaceAll("\\[","");
												    val=val.replaceAll("\\]","");
												    String arr[]=val.split(",");

												    String labname=arr[0];
												    String labcode=arr[1];
												    //labname=labname.replaceAll("\\s", "");



												%>



														<button class='one <%=labcode%>' onclick="showtestswithteriff('bookm','<%=labcode%>','fgh<%=p%>',this)" id="fgh<%=p%>" style="box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);background-color: #bf3651;border: none;color: white;padding: 2px 15px;text-align: center; text-decoration: none;display: inline-block;font-size: 16px;margin: 4px 2px;cursor: pointer;-webkit-transition-duration: 0.4s;transition-duration: 0.4s;height: 40px;"><%=labname%></button>


													<!-- </td>
													 -->

													<%
													}

													} %>

												<!-- 	</tr>

									</table>
									 -->
										</div>

                 </div>
                  <div id="terifftest">
                   <logic:equal name="InvestigationRaisingDtlFB" property="labwisetestteriff" value="1">

                    <logic:present
												name="<%=InvestigationConfig.LIST_LAB_WISE_TEST_DTLS%>">

											<%
												//Pagination Logic

																PaginationFB fbPage1 = new PaginationFB();
																pageContext.setAttribute("fbPagination", fbPage1);
																fbPage1.setCurrentPage(((InvestigationRaisingDtlFB) request
																		.getAttribute("InvestigationRaisingDtlFB"))
																		.getCurrentPage());
																fbPage1.setObjArrName(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS);
																fbPage1.setAppendInTitle("List Of Test And Laboratory &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
																+"<input type='textbox'  style='height:22px;weight:300px;margin-left:-850;width:200' name='testSearchKeywordlabwise' id='automplete-testsearchlabwise'  placeholder='Enter Test Name/Group to Search '>");
																int maxRecordd = 40;
																fbPage1.setMaxRecords(maxRecordd);

																%>
											<his:PaginationTag name="fbPagination"></his:PaginationTag>

												<his:ContentTag>
													<div id="showhideOnPagination">
														<table width="100%">
															
															<%
																List<LabTestVO> lstLabTestVO = (List<LabTestVO>) session
																								.getAttribute(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS);
															String tefiitestrate="";
															//to highlight the tests already raised even after page load
															String alreadyRaisedArray=(String)((InvestigationRaisingDtlFB)request.getAttribute("InvestigationRaisingDtlFB")).getLabTestCodeArray();
															boolean alreadyRaised=false;

																						int i, size = 0;
																						String chkVal = "";
																						String singleSample="-1";
																						String reqdSampleName="-";
																						String isTestGroup = ((InvestigationRaisingDtlFB) request
																								.getAttribute("InvestigationRaisingDtlFB"))
																								.getIsTestGroup();
																						if (lstLabTestVO != null
																								&& lstLabTestVO.size() > 0)
																							size = lstLabTestVO.size();
																						int startIndex = ((Integer) request
																								.getAttribute(PaginationTag.PAGINATION_START_INDEX))
																								.intValue();
																						int endIndex = ((Integer) request
																								.getAttribute(PaginationTag.PAGINATION_END_INDEX))
																								.intValue();
																						int count=0;
																						for (int j = startIndex; j <= endIndex; j++) {
																							//int i=j-startIndex;
																							if (j < size) {
																								LabTestVO voLabTest = lstLabTestVO
																										.get(j);
																								String testGroupCode = (isTestGroup == null || isTestGroup
																										.equals("0")) ? "0"
																										: voLabTest
																												.getTestGroupCode();
																								String testGroupType = (isTestGroup == null || isTestGroup
																										.equals("0")) ? "0"
																										: voLabTest
																												.getTestGroupType();
																								// Please Notice the Order of Hashing value is fixed, should get the value any where in future in the same order
																								// chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential#sampleCode#priority#testGroupCode#testGroupType      //Please Ensure the corresponding Changes(In UTIL file, java script) before changing this order
																								if(voLabTest.getSingleSample()!=null && voLabTest.getSingleSample().equals("")==false)
																									singleSample=voLabTest.getSingleSample();

																								if(voLabTest.getReqdSampleName()!=null && voLabTest.getReqdSampleName().equals("")==false)
																									reqdSampleName=voLabTest.getReqdSampleName();

																								String instruction1=voLabTest.getInstructionPat();



																								chkVal = voLabTest.getLabCode()
																										+ "#"
																										+ voLabTest.getLabName()
																										+ "#"
																										+ voLabTest.getTestCode()
																										+ "#"
																										+ voLabTest.getTestName()
																										+ "#"
																										+ voLabTest
																												.getSampleComboStr()
																										+ "#"
																										+ voLabTest.getTestType()
																										+ "#"
																										+ voLabTest
																												.getIsAppointment()
																										+ "#"
																										+ voLabTest
																												.getIsConfidential()
																										+ "#"
																										+ singleSample
																										+ "#"
																										+ "1"
																										+ "#"
																										+ testGroupCode
																										+ "#"
																										+ testGroupType
																										+ "#"
																										+ voLabTest
																												.getIsMandatoryReq()
																										+ "#"
																										+ voLabTest
																												.getBookMarkCode()
																										+ "#"
																										+ voLabTest
																												.getOfflineAppoitMentDate()

																										+ "#"+voLabTest.getSetMandTextBoxCombo()+"#"+voLabTest.getMandComboTextBoxComboNames()+"#"+voLabTest.getMandCode()+"#"+""+"#"+voLabTest.getHideAptNo()+"#"+instruction1+"#"+voLabTest.getIsLabAvailable()  /* islabavailable contains 2 strings separated by hash. care to be taken to add more values in lab test array numbering */
																										+"#"+reqdSampleName+"#"+voLabTest.getIsRequisitionFormNeeded()+"#"+voLabTest.getReqSampleShortName()+"#"+voLabTest.getDeskcallingid()+"#"+voLabTest.getIspidshow()+"#"+voLabTest.getIslabappointmentbased()+"#";

																								String combostr="sample not carry";


																								String 	chkVall = voLabTest.getLabCode()
																										+ "#"
																										+ voLabTest.getLabName()
																										+ "#"
																										+ voLabTest.getTestCode()
																										+ "#"
																										+ voLabTest.getTestName()
																										+ "#"
																										+ combostr
																										+ "#"
																										+ voLabTest.getTestType()
																										+ "#"
																										+ voLabTest
																												.getIsAppointment()
																										+ "#"
																										+ voLabTest
																												.getIsConfidential()
																										+ "#"
																										+ singleSample
																										+ "#"
																										+ "1"
																										+ "#"
																										+ testGroupCode
																										+ "#"
																										+ testGroupType
																										+ "#"
																										+ voLabTest
																												.getIsMandatoryReq()
																										+ "#"
																										+ voLabTest
																												.getBookMarkCode()
																										+ "#"
																										+ voLabTest
																												.getOfflineAppoitMentDate()

																										+ "#"+voLabTest.getSetMandTextBoxCombo()+"#"+voLabTest.getMandComboTextBoxComboNames()+"#"+voLabTest.getMandCode()+"#"+""+"#"+voLabTest.getHideAptNo()+"#"+instruction1+"#"+voLabTest.getIsLabAvailable()  /* islabavailable contains 2 strings separated by hash. care to be taken to add more values in lab test array numbering */
																										+"#"+reqdSampleName+"#"+voLabTest.getIsRequisitionFormNeeded()+"#"+voLabTest.getReqSampleShortName()+"#"+voLabTest.getDeskcallingid()+"#"+voLabTest.getIspidshow()+"#"+voLabTest.getIslabappointmentbased()+"#";

																								//System.out.println("voLabTest.getUserTestCode()"+voLabTest.getUserTestCode());
																								//to highlight the tests already raised even after page load
																								 if(alreadyRaisedArray!=null&&!alreadyRaisedArray.equals(""))
																								 {
																									String[] raisedTests=alreadyRaisedArray.split("@");

																									for (String raisedTest:raisedTests)
																									{
																										raisedTest=raisedTest.replaceAll(";", "#");

																										if((raisedTest.split("#")[0]+raisedTest.split("#")[2]).equals(voLabTest.getLabCode()+voLabTest.getTestCode()))
																										{
																											alreadyRaised=true;
																											break;
																										}
																										else
																											alreadyRaised=false;
																									}
																								 }
															%>
															<% if(count==4){ %>
															<tr >
															<%
															count=0;
															}
															 %>
															<%
															//checkbox already selected and color changed for already raised test on page load
															if(alreadyRaised)
																{
																%>
																<td class="tdfonthead" id="td1<%=chkVal %>" style="background-color:#ff9999">
																	<div align="left">
																		<input type="checkbox"
																			id="<%=voLabTest.getLabCode()
													+ voLabTest.getTestCode()%>" checked="checked"
																			name="chkLabTest" tabindex="1" value="<%=chkVal%>"
																			onclick="showSelectedLabTestList(this,'<%=chkVall%>')" />
																	</div>
																</td>


																<td class="tdfonthead" id="td2<%=chkVal %>" style="background-color:#ff9999">
																	<div align="left">

																	<%
																	if(voLabTest.getTariffTestRate()!=null)
																	{
																	if(voLabTest.getTariffTestRate().contains("^"))
																		tefiitestrate=voLabTest.getTariffTestRate().split("\\^")[0];
																	else
																		tefiitestrate=voLabTest.getTariffTestRate();
																	}
																	%>
																		<%=voLabTest.getTestName()+"(<font color='red'>INR "+tefiitestrate+" </font>)"%>
																	</div>
																</td>
																<% count++;%>
																<%-- <td class="tdfonthead" id="td3<%=chkVal %>" style="background-color:#ff9999">
																	<div align="left">
																		<%=voLabTest.getLabName()%>
																	</div>
																</td> --%>
																<%}
															//if not already raised normal display
															else
																{
																if(voLabTest.getTariffTestRate()!=null)
																{
																if(voLabTest.getTariffTestRate().contains("^"))
																	tefiitestrate=voLabTest.getTariffTestRate().split("\\^")[0];
																else
																	tefiitestrate=voLabTest.getTariffTestRate();
																}

																%>
																	<td class="tdfonthead" id="td1<%=chkVal %>">
																	<div align="left">
																		<input type="checkbox"
																			id="<%=voLabTest.getLabCode()
													+ voLabTest.getTestCode()%>"
																			name="chkLabTest" tabindex="1" value="<%=chkVal%>"
																			onclick="showSelectedLabTestList(this,'<%=chkVall%>')" />
																	</div>
																</td>



																<td class="tdfonthead" id="td2<%=chkVal %>">
																	<div align="left">
																		<%=voLabTest.getTestName()+"(<font color='red'>INR "+tefiitestrate+"</font> ) "%>
																	</div>
																</td>
																<%-- <td class="tdfonthead" id="td3<%=chkVal %>">
																	<div align="left">
																		<%=voLabTest.getLabName()%>
																	</div>
																</td> --%>
																<%count++; %>

																<%} %>

															<%
																}%>
																<% if(count==4){ %>
															</tr >
															<%} %>
																<%
																						}
															%>

														</table>
													</div>
												</his:ContentTag>
											</logic:present>
											 </logic:equal>

      </div>

										<div id="searchLabTestDiv" class="subDivStyle" style="display:none;" <%--style=<%=searchLogicDisplay %> --%> >
									<his:SubTitleTag name="">
											<div  align="left">
											Search Laboratory And Test -------------->
											 <span style="display:inline-block; width: 58%;"></span>
<!--         <input type="checkbox" name="hisswitch"  id="myhisswitch" value="myhisswitchTestLab" onclick="getTestWiseList('myhisswitchTestLab');" >
 -->
                 <a style="color: #ffffff;cursor: pointer;background-color:#000000" onclick="getTestWiseList('myhisswitchTestLab');" > <b> Search Code Wise </b></a>

    </div>

 		 </his:SubTitleTag>

											<fieldset
												style="border: solid 3px blue; background-color: CCE6FF;">
												<legend align="left"
													style="color: red; text-align: right; font-size: 1.5em; border: 1px solid blue;"></legend>
												<table width="100%">
													<tr>
													<td class="tdfonthead" width="20%">
															<div align="right"><b>Search Test By Name</b></div>
														</td>
														<td class="tdfont" width="20%">
															<div align="left" class="ui-widget">
																<%-- <html:text name="InvestigationRaisingDtlFB" property="testSearchKeyword" id="automplete-1"></html:text>
																 --%>

																<input type="text"  name="testSearchKeyword"
																	size="30" id="automplete-testsearch"

																	 >
															</div>
														</td>

														<td class="tdfonthead" width="20%">
															<div align="right"><b><bean:message key="Laboratory"/></b></div>
														</td>
														<td class="tdfont" width="20%">
															<div align="left" class="ui-widget">
																<input type="text"  id="automplete-1" name="searchLab"
																	size="30"
																	onselect="searchLabWiseTest()" 	onchange="searchLabWiseTest()"
																	 >
															</div>
														</td>
														<td class="tdfonthead" width="20%"><logic:equal
																name="InvestigationRaisingDtlFB" property="isTestGroup"
																value="<%=InvestigationConfig.IS_TEST_GROUP_YES%>">
																<input type="radio" name="isTestGroup"
																	onclick="setTestORTestGroup(this)"
																	value="<%=InvestigationConfig.IS_TEST_GROUP_NO%>"><b><bean:message key="Test"/></b>
					 					<input type="radio" name="isTestGroup"
																	onclick="setTestORTestGroup(this)" checked="checked"
																	value="<%=InvestigationConfig.IS_TEST_GROUP_YES%>"><b><bean:message key="TestGroup"/></b>
					 			</logic:equal> <logic:notEqual name="InvestigationRaisingDtlFB"
																property="isTestGroup"
																value="<%=InvestigationConfig.IS_TEST_GROUP_YES%>">
																<input type="radio" name="isTestGroup"
																	onclick="setTestORTestGroup(this)" checked="checked"
																	value="<%=InvestigationConfig.IS_TEST_GROUP_NO%>"><b><bean:message key="Test"/></b>
					 					<input type="radio" name="isTestGroup"
																	onclick="setTestORTestGroup(this)"
																	value="<%=InvestigationConfig.IS_TEST_GROUP_YES%>"><b><bean:message key="TestGroup"/></b>
					 			</logic:notEqual></td>
														<td class="tdfont" width="20%">
															<div align="left" id="divSearchTest" class="ui-widget">
																<input type="text"  id="automplete-2" name="searchTest"
																	size="30"

																>

															</div>
															<div id="divSearchTestGroup" class="ui-widget"
																style="display: none">
																<input type="text"  id="automplete-3"
																	name="searchTestGroup" size="30"
																	onkeypress="return validateAlphaNumericOnly(event,this)"

																	 >
															</div>
														</td>
														<td width="20%">
															<div id="goButtonDiv" align="left">
																<img class="button" id="addTestButton" tabindex="1"
																	src="/HISInvestigationG5/hisglobal/images/btn-add.png"
																	style="cursor: pointer; display: none"
																	onkeypress="searchTestGroup()"
																	onclick="searchTestGroup()"> <img class="button"
																	id="addGroupButton" tabindex="1"
																	src="/HISInvestigationG5/hisglobal/images/btn-add.png"
																	style="cursor: pointer; display: none"
																	onkeypress="searchGroup()" onclick="searchGroup()">
																<img class="button" id="goButton" tabindex="1"
																	src="/HISInvestigationG5/hisglobal/images/GoNew.png"
																	style="cursor: pointer;"
																	onkeypress="searchLabWiseTest()"
																	onclick="searchLabWiseTest()">
															</div>
														</td>
													</tr>

												</table>


											</fieldset>
	<logic:empty  	name="<%=InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS%>">


	<logic:present
												name="<%=InvestigationConfig.LIST_LAB_WISE_TEST_DTLS%>">

											<%
												//Pagination Logic

																PaginationFB fbPage = new PaginationFB();
																pageContext.setAttribute("fbPagination", fbPage);
																fbPage.setCurrentPage(((InvestigationRaisingDtlFB) request
																		.getAttribute("InvestigationRaisingDtlFB"))
																		.getCurrentPage());
																fbPage.setObjArrName(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS);
																fbPage.setAppendInTitle("List Of Test And Laboratory");
																int maxRecord = 30;
																fbPage.setMaxRecords(maxRecord);
											%>
											<his:PaginationTag name="fbPagination"></his:PaginationTag>
										

												<his:ContentTag>
													<div id="showhideOnPagination">
														<table width="100%">
															<tr>
																<td width ="5%" class="tdfont">
																	<div align="left">
																	<font   size="2" face="Verdana, Arial, Helvetica, sans-serif">
																		 <bean:message key="Select"/>
																		 </font>
																	</div>
																</td>
																<td class="tdfont" width="48.5%">
																	<div align="left">
																	<font   size="2" face="Verdana, Arial, Helvetica, sans-serif">
																		 <bean:message key="testAndGroup"/>
                                                                     </font>
																	</div>
																</td>
																<td class="tdfont" width="48.5%">
																	<div align="left">
																	<font   size="2" face="Verdana, Arial, Helvetica, sans-serif">
																		 <bean:message key="Laboratory"/>
																		 </font>
																	</div>
																</td>

															</tr>
															<%
																List<LabTestVO> lstLabTestVO = (List<LabTestVO>) session
																								.getAttribute(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS);

															//to highlight the tests already raised even after page load
															String alreadyRaisedArray=(String)((InvestigationRaisingDtlFB)request.getAttribute("InvestigationRaisingDtlFB")).getLabTestCodeArray();
															boolean alreadyRaised=false;

																						int i, size = 0;
																						String chkVal = "";
																						String singleSample="-1";
																						String reqdSampleName="-";
																						String isTestGroup = ((InvestigationRaisingDtlFB) request
																								.getAttribute("InvestigationRaisingDtlFB"))
																								.getIsTestGroup();
																						if (lstLabTestVO != null
																								&& lstLabTestVO.size() > 0)
																							size = lstLabTestVO.size();
																						int startIndex = ((Integer) request
																								.getAttribute(PaginationTag.PAGINATION_START_INDEX))
																								.intValue();
																						int endIndex = ((Integer) request
																								.getAttribute(PaginationTag.PAGINATION_END_INDEX))
																								.intValue();
																						for (int j = startIndex; j <= endIndex; j++) {
																							//int i=j-startIndex;
																							if (j < size) {
																								LabTestVO voLabTest = lstLabTestVO
																										.get(j);
																								String testGroupCode = (isTestGroup == null || isTestGroup
																										.equals("0")) ? "0"
																										: voLabTest
																												.getTestGroupCode();
																								String testGroupType = (isTestGroup == null || isTestGroup
																										.equals("0")) ? "0"
																										: voLabTest
																												.getTestGroupType();
																								// Please Notice the Order of Hashing value is fixed, should get the value any where in future in the same order
																								// chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential#sampleCode#priority#testGroupCode#testGroupType      //Please Ensure the corresponding Changes(In UTIL file, java script) before changing this order
																								if(voLabTest.getSingleSample()!=null && voLabTest.getSingleSample().equals("")==false)
																									singleSample=voLabTest.getSingleSample();

																								if(voLabTest.getReqdSampleName()!=null && voLabTest.getReqdSampleName().equals("")==false)
																									reqdSampleName=voLabTest.getReqdSampleName();

																								String instruction1=voLabTest.getInstructionPat();



																								chkVal = voLabTest.getLabCode()
																										+ "#"
																										+ voLabTest.getLabName()
																										+ "#"
																										+ voLabTest.getTestCode()
																										+ "#"
																										+ voLabTest.getTestName()
																										+ "#"
																										+ voLabTest
																												.getSampleComboStr()
																										+ "#"
																										+ voLabTest.getTestType()
																										+ "#"
																										+ voLabTest
																												.getIsAppointment()
																										+ "#"
																										+ voLabTest
																												.getIsConfidential()
																										+ "#"
																										+ singleSample
																										+ "#"
																										+ "1"
																										+ "#"
																										+ testGroupCode
																										+ "#"
																										+ testGroupType
																										+ "#"
																										+ voLabTest
																												.getIsMandatoryReq()
																										+ "#"
																										+ voLabTest
																												.getBookMarkCode()
																										+ "#"
																										+ voLabTest
																												.getOfflineAppoitMentDate()

																										+ "#"+voLabTest.getSetMandTextBoxCombo()+"#"+voLabTest.getMandComboTextBoxComboNames()+"#"+voLabTest.getMandCode()+"#"+""+"#"+voLabTest.getHideAptNo()+"#"+instruction1+"#"+voLabTest.getIsLabAvailable()  /* islabavailable contains 2 strings separated by hash. care to be taken to add more values in lab test array numbering */
																										+"#"+reqdSampleName+"#"+voLabTest.getIsRequisitionFormNeeded()+"#"+voLabTest.getReqSampleShortName()+"#"+voLabTest.getDeskcallingid()+"#"+voLabTest.getIspidshow()+"#"+voLabTest.getIslabappointmentbased()+"#";

																								String combostr="sample not carry";


																								String 	chkVall = voLabTest.getLabCode()
																										+ "#"
																										+ voLabTest.getLabName()
																										+ "#"
																										+ voLabTest.getTestCode()
																										+ "#"
																										+ voLabTest.getTestName()
																										+ "#"
																										+ combostr
																										+ "#"
																										+ voLabTest.getTestType()
																										+ "#"
																										+ voLabTest
																												.getIsAppointment()
																										+ "#"
																										+ voLabTest
																												.getIsConfidential()
																										+ "#"
																										+ singleSample
																										+ "#"
																										+ "1"
																										+ "#"
																										+ testGroupCode
																										+ "#"
																										+ testGroupType
																										+ "#"
																										+ voLabTest
																												.getIsMandatoryReq()
																										+ "#"
																										+ voLabTest
																												.getBookMarkCode()
																										+ "#"
																										+ voLabTest
																												.getOfflineAppoitMentDate()

																										+ "#"+voLabTest.getSetMandTextBoxCombo()+"#"+voLabTest.getMandComboTextBoxComboNames()+"#"+voLabTest.getMandCode()+"#"+""+"#"+voLabTest.getHideAptNo()+"#"+instruction1+"#"+voLabTest.getIsLabAvailable()  /* islabavailable contains 2 strings separated by hash. care to be taken to add more values in lab test array numbering */
																										+"#"+reqdSampleName+"#"+voLabTest.getIsRequisitionFormNeeded()+"#"+voLabTest.getReqSampleShortName()+"#"+voLabTest.getDeskcallingid()+"#"+voLabTest.getIspidshow()+"#"+voLabTest.getIslabappointmentbased()+"#";
		 if(alreadyRaisedArray!=null&&!alreadyRaisedArray.equals(""))
																								 {
																									String[] raisedTests=alreadyRaisedArray.split("@");

																									for (String raisedTest:raisedTests)
																									{
																										raisedTest=raisedTest.replaceAll(";", "#");

																										if((raisedTest.split("#")[0]+raisedTest.split("#")[2]).equals(voLabTest.getLabCode()+voLabTest.getTestCode()))
																										{
																											alreadyRaised=true;
																											break;
																										}
																										else
																											alreadyRaised=false;
																									}
																								 }
															%>
															<tr >

															<%
															//checkbox already selected and color changed for already raised test on page load
															if(alreadyRaised)
																{
																%>
																<td class="tdfonthead" id="td1<%=chkVal %>" style="background-color:#ff9999">
																	<div align="left">
																		<input type="checkbox"
																			id="<%=voLabTest.getLabCode()
													+ voLabTest.getTestCode()%>" checked="checked"
																			name="chkLabTest" tabindex="1" value="<%=chkVal%>"
																			onclick="showSelectedLabTestList(this,'<%=chkVall%>')" />
																	</div>
																</td>


																<td class="tdfonthead" id="td2<%=chkVal %>" style="background-color:#ff9999">
																	<div align="left">
																		<%=voLabTest.getTestName()%>
																	</div>
																</td>
																<td class="tdfonthead" id="td3<%=chkVal %>" style="background-color:#ff9999">
																	<div align="left">
																		<%=voLabTest.getLabName()%>
																	</div>
																</td>
																<%}
															//if not already raised normal display
															else
																{


																%>
																	<td class="tdfonthead" id="td1<%=chkVal %>">
																	<div align="left">
																		<input type="checkbox"
																			id="<%=voLabTest.getLabCode()
													+ voLabTest.getTestCode()%>"
																			name="chkLabTest" tabindex="1" value="<%=chkVal%>"
																			onclick="showSelectedLabTestList(this,'<%=chkVall%>')" />
																	</div>
																</td>


																<td class="tdfonthead" id="td2<%=chkVal %>">
																	<div align="left">
																		<%=voLabTest.getTestName()%>
																	</div>
																</td>
																<td class="tdfonthead" id="td3<%=chkVal %>">
																	<div align="left">
																		<%=voLabTest.getLabName()%>
																	</div>
																</td>


																<%} %>
															</tr>
															<%
																}
																						}
															%>
														</table>
													</div>
												</his:ContentTag>
											</logic:present>

									 </logic:empty>
											<logic:present
												name="<%=InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS%>">

											<%
												//Pagination Logic

																PaginationFB fbPage = new PaginationFB();
																pageContext.setAttribute("fbPagination", fbPage);
																fbPage.setCurrentPage(((InvestigationRaisingDtlFB) request
																		.getAttribute("InvestigationRaisingDtlFB"))
																		.getCurrentPage());
																fbPage.setObjArrName(InvestigationConfig.LIST_SINGLE_LAB_WISE_GROUP_DTLS);
																fbPage.setAppendInTitle("List Of TestGroup And Laboratory ");
																int maxRecord = 30;
																fbPage.setMaxRecords(maxRecord);
											%>
											<his:PaginationTag name="fbPagination"></his:PaginationTag>
									

												<his:ContentTag>
													<div id="showhideOnPagination">
														<table width="100%">
															<tr>
																<td class="tdfont" width="5%" >
																	<div align="left">
																	<font   size="2" face="Verdana, Arial, Helvetica, sans-serif">
																		 <bean:message key="Select"/>
																		 </font>
																	</div>
																</td>
																<td class="tdfont" width="48.5%">
																	<div align="left">
																	<font   size="2" face="Verdana, Arial, Helvetica, sans-serif">
																		 <bean:message key="testAndGroup"/>
																		</font>
																	</div>
																</td>
																<td class="tdfont"  width="48.5%">
																	<div align="left">
																	<font   size="2" face="Verdana, Arial, Helvetica, sans-serif">
																		 <bean:message key="Laboratory"/>
																		</font>
																	</div>
																</td>

															</tr>



															<%

																List<LabTestVO> lstLabTestVO = (List<LabTestVO>) session
																								.getAttribute(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS);
																						int i, size = 0;
																						String chkVal = "";
																						String singleSample="-1";
																						String reqdSampleName="-";
																						String isTestGroup = ((InvestigationRaisingDtlFB) request
																								.getAttribute("InvestigationRaisingDtlFB"))
																								.getIsTestGroup();
																						if (lstLabTestVO != null
																								&& lstLabTestVO.size() > 0)
																							size = lstLabTestVO.size();
																						int startIndex = ((Integer) request
																								.getAttribute(PaginationTag.PAGINATION_START_INDEX))
																								.intValue();
																						int endIndex = ((Integer) request
																								.getAttribute(PaginationTag.PAGINATION_END_INDEX))
																								.intValue();
																						String grpCode="";
																						String ChkTestGroupValue="";
																						String newChkTestGroupValue="";
																						int j=0;
																						for (int l = startIndex; l <= endIndex+1 && j<size; j++) {
																							//int i=j-startIndex;

																							boolean firstTimeTravesall=true;
																							if (j < size) {
																								LabTestVO voLabTest = lstLabTestVO
																										.get(j);
																								String testGroupCode = (isTestGroup == null || isTestGroup
																										.equals("0")) ? "0"
																										: voLabTest
																												.getTestGroupCode();
																								String testGroupType = (isTestGroup == null || isTestGroup
																										.equals("0")) ? "0"
																										: voLabTest
																												.getTestGroupType();
																								if(voLabTest.getSingleSample()!=null && voLabTest.getSingleSample().equals("")==false)
																									singleSample=voLabTest.getSingleSample();
																								if(voLabTest.getReqdSampleName()!=null && voLabTest.getReqdSampleName().equals("")==false)
																									reqdSampleName=voLabTest.getReqdSampleName();
																								// Please Notice the Order of Hashing value is fixed, should get the value any where in future in the same order
																								// chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential#sampleCode#priority#testGroupCode#testGroupType      //Please Ensure the corresponding Changes(In UTIL file, java script) before changing this order

																								String instruction1=voLabTest.getInstructionPat();


																								chkVal = voLabTest.getLabCode()
																										+ "#"
																										+ voLabTest.getLabName()
																										+ "#"
																										+ voLabTest.getTestCode()
																										+ "#"
																										+ voLabTest.getTestName()
																										+ "#"
																										+ voLabTest
																												.getSampleComboStr()
																										+ "#"
																										+ voLabTest.getTestType()
																										+ "#"
																										+ voLabTest
																												.getIsAppointment()
																										+ "#"
																										+ voLabTest
																												.getIsConfidential()
																										+ "#"
																										+ singleSample
																										+ "#"
																										+ "1"
																										+ "#"
																										+ testGroupCode
																										+ "#"
																										+ testGroupType
																										+ "#"
																										+ voLabTest
																												.getIsMandatoryReq()
																										+ "#"
																										+ voLabTest
																												.getBookMarkCode()
																										+ "#"
																										+ voLabTest
																												.getOfflineAppoitMentDate()
																										+ "#"+voLabTest.getSetMandTextBoxCombo()+"#"+voLabTest.getMandComboTextBoxComboNames()+"#"+voLabTest.getMandCode()+"#"+voLabTest.getTestGroupCode()+"#"+voLabTest.getHideAptNo()+"#"+instruction1+"#"+voLabTest.getIsLabAvailable()  /* islabavailable contains 2 strings separated by hash. care to be taken to add more values in lab test array numbering */
																										+"#"+reqdSampleName+"#"+voLabTest.getIsRequisitionFormNeeded()+"#"+voLabTest.getReqSampleShortName()+"#"+voLabTest.getDeskcallingid()+"#"+voLabTest.getIspidshow()+"#"+voLabTest.getIslabappointmentbased()+"#";
																									

																										String  combostr="sample not carry";


																												String 	chkVall = voLabTest.getLabCode()
																														+ "#"
																														+ voLabTest.getLabName()
																														+ "#"
																														+ voLabTest.getTestCode()
																														+ "#"
																														+ voLabTest.getTestName()
																														+ "#"
																														+ combostr
																														+ "#"
																														+ voLabTest.getTestType()
																														+ "#"
																														+ voLabTest
																																.getIsAppointment()
																														+ "#"
																														+ voLabTest
																																.getIsConfidential()
																														+ "#"
																														+ singleSample
																														+ "#"
																														+ "1"
																														+ "#"
																														+ testGroupCode
																														+ "#"
																														+ testGroupType
																														+ "#"
																														+ voLabTest
																																.getIsMandatoryReq()
																														+ "#"
																														+ voLabTest
																																.getBookMarkCode()
																														+ "#"
																														+ voLabTest
																																.getOfflineAppoitMentDate()

																														+ "#"+voLabTest.getSetMandTextBoxCombo()+"#"+voLabTest.getMandComboTextBoxComboNames()+"#"+voLabTest.getMandCode()+"#"+""+"#"+voLabTest.getHideAptNo()+"#"+instruction1+"#"+voLabTest.getIsLabAvailable()  /* islabavailable contains 2 strings separated by hash. care to be taken to add more values in lab test array numbering */
																														+"#"+reqdSampleName+"#"+voLabTest.getIsRequisitionFormNeeded()+"#"+voLabTest.getReqSampleShortName()+"#"+voLabTest.getDeskcallingid()+"#"+voLabTest.getIspidshow()+"#"+voLabTest.getIslabappointmentbased()+"#";


																								if(voLabTest.getTestGroupCode()!=null)
																								{


																									grpCode+='&';
																									String[] SplitGrpCode=grpCode.split("&");
																									int length=SplitGrpCode.length;
																									if(SplitGrpCode.length>1)
																									for(int x=0;x<SplitGrpCode.length;x++)
																									{
																			                        if(SplitGrpCode[x].equals(voLabTest.getLabCode()+voLabTest.getTestGroupCode()))
																			                        {
																									 firstTimeTravesall=false;
																			                        }
																			                        else
																			                        {
																			                        	 firstTimeTravesall=true;
																			                        }
																									}

																									 grpCode+=voLabTest.getLabCode()+voLabTest.getTestGroupCode();
																								}


																								 if(firstTimeTravesall)
																						 			{l++;

																								%>
															<tr>
																<td class="tdfonthead" id="grptd1<%=voLabTest.getLabCode()
													+ voLabTest.getTestGroupCode()%>">
																	<div align="left">
																		<input type="checkbox"
																			id="<%=voLabTest.getLabCode()
													+ voLabTest.getTestGroupCode()%>"
																			name="chkLabTestGroup" tabindex="1" value="<%=chkVal%>"
																			onclick="showSelectedLabTestGroupList(this,'<%=chkVall%>')" />
																	</div>
																</td>
																<td class="tdfonthead" id="grptd2<%=voLabTest.getLabCode()
													+ voLabTest.getTestGroupCode()%>">
																	<div align="left">
																		<%=voLabTest.getSearchTestGroup()%>
																	</div>
																</td>
																<td class="tdfonthead" id="grptd3<%=voLabTest.getLabCode()
													+ voLabTest.getTestGroupCode()%>">
																	<div align="left">
																		<%=voLabTest.getLabName()%>
																	</div>
																</td>

															</tr>
															<%
																}

												ChkTestGroupValue+=chkVal;
												ChkTestGroupValue+="@";
												newChkTestGroupValue+=chkVall;
												newChkTestGroupValue+="@";
															 }
																						}
															%>
																<html:hidden name="InvestigationRaisingDtlFB" value="<%=ChkTestGroupValue%>"  property="labtestGroupValues" />
																<html:hidden name="InvestigationRaisingDtlFB" value="<%=newChkTestGroupValue%>"  property="newlabtestGroupValues" />
														</table>
													</div>
												</his:ContentTag>
											</logic:present>
										</div>
										<div id="testCodeWiseSearchDiv"  class="subDivStyle" >
                                  <his:SubTitleTag	name="">
                                 <div align="left"> Search Code Wise -------------->
                                <span style="display:inline-block; width: 60%;"></span>
      <!--   <input type="checkbox" name="hisswitchTest"  id="myhisswitchTest" value="myhisswitchTest" onclick="getTestWiseList('myhisswitchTest');" > -->


        <a style="color: #ffffff;cursor: pointer;background-color:#000000" onclick="getTestWiseList('myhisswitchTest');" ><b>  Search Lab/Test Wise --> </b> </a>


    </div>

 		                                  </his:SubTitleTag>


												<legend align="left"
													style="color: red; text-align: right; font-size: 1.5em; border: 1px solid blue;"></legend>
												<table width="100%">
													<tr>
														<td class="tdfonthead" width="20%">
															<div align="right"> <b><bean:message key="testCodeWise"/></b></div>
														</td>
														<td class="tdfont" width="20%">
															<div align="left" class="ui-widget">
																<input type="text"  id="automplete-5"   name="searchLab"
																	size="30" >

															</div>
														</td>

								 <td class="tdfonthead" width="20%">
															<div align="right"> <b><bean:message key="userGroupCodeWise"/></b></div>
														</td>

														<td class="tdfont" width="20%">
															<div align="left" class="ui-widget">
																<input type="text"  id="automplete-6"   name="searchLab"
																	size="30"
																	onchange="setUserGroupWiseDetail();" onselect="setUserGroupWiseDetail();" >

															</div>
														</td>



														<td width="20%"></td>
													</tr>

												</table>
						</div>

									</div>


									<div id="tabs-2" >
										 <div class="subDivStyle">

											<his:SubTitleTag name="Test/Lab Details">
											<select name="setprevreqnew" id="setprevreqnew" style='margin-right:80' onchange='setprevreq(this)'><option value='0'>From Last 3 Months</option><option value='1'>All</option></select>
										
                                               
 		                             </his:SubTitleTag>


											<%
												String prvTestArrayString = (String) ((InvestigationRaisingDtlFB) request
																		.getAttribute("InvestigationRaisingDtlFB"))
																		.getPrvTestDtl();
																if (prvTestArrayString != null
																		&& !prvTestArrayString.equals(""))
																/* { */
																	
											%>
											<table width="99%" cellspacing="1" cellpadding="0" id="prvRecord" >
												<tr>
													<td id="colorCycle" width="15%">
														<div align="left">
															<b><bean:message key="Test"/></b>
														</div>
													</td>
													<td id="colorCycle" width="15%">
														<div align="left">
															<b><bean:message key="Laboratory"/></b>
														</div>
													</td>

													<td id="colorCycle" width="15%">
														<div align="left">
															<b><bean:message key="TestType"/></b>
														</div>
													</td>
													<td id="colorCycle" width="15%">
														<div align="left">
															<b><bean:message key="SampleName"/></b>
														</div>
													</td>
													<td id="colorCycle" width="16%">
														<div align="left">
															<b><bean:message key="status"/></b>
														</div>
													</td>
													<td id="colorCycle" width="9%">
														<div align="left">
															<b><bean:message key="Priority"/></b>
														</div>
													</td>
													<td id="colorCycle" width="11%">
														<div align="left">
															<b><bean:message key="reqDate"/></b>
														</div>
													</td>
													<td id="colorCycle" width="2%">
														<div align="left">
															<b> </b>
														</div>

													</td>
												</tr>
											</table>




											 <div id='mycustomscroll2' class='flexcroll'>
											<table width="99%" cellspacing="1" cellpadding="0" id="setPrvTestDtl">
												<tr>

												</tr>
											</table>
											</div>

											  </div>

											 </div>
											 <div id="wait" style="display:none;width:69px;height:89px;border:0px solid black;position:absolute;top:50%;left:50%;padding:2px;"><img src='/HISInvestigationG5/hisglobal/css/loader.gif' width="64" height="64" /><br>Loading..</div>
											 
											 </div>


							</logic:present>

						 </td>

					</tr>

				</table>


			</his:ContentTag>

		</his:statusTransactionInProcess>


<html:hidden name="InvestigationRaisingDtlFB" property="selectlabid" />
		<html:hidden name="InvestigationRaisingDtlFB" property="hmode" />
		<html:hidden name="InvestigationRaisingDtlFB" property="casualitydesk" />
		<html:hidden name="InvestigationRaisingDtlFB"
			property="newlabtestcodearray" />
				<html:hidden name="InvestigationRaisingDtlFB" property="labbasedapppointment" />

				<html:hidden name="InvestigationRaisingDtlFB" property="pidd" />

				<html:hidden name="InvestigationRaisingDtlFB" property="pidd" />

				<html:hidden name="InvestigationRaisingDtlFB" property="followup"/>

		<html:hidden name="InvestigationRaisingDtlFB" property="flagDesk" />
		<html:hidden name="InvestigationRaisingDtlFB" property="issearchtestnamewise" />
		<html:hidden name="InvestigationRaisingDtlFB" property="patAdmNo" />
		<html:hidden name="InvestigationRaisingDtlFB" property="departmentUnitCode" />


		<html:hidden name="InvestigationRaisingDtlFB" property="callingdesk" />
		<html:hidden name="InvestigationRaisingDtlFB" property="visitReason" />


		<html:hidden name="InvestigationRaisingDtlFB" property="labwisetestteriff" />
		<html:hidden name="InvestigationRaisingDtlFB" property="patCrNo" />
		<html:hidden name="InvestigationRaisingDtlFB" property="isentry" />
		<html:hidden name="InvestigationRaisingDtlFB" property="currentPage" />
		<html:hidden name="InvestigationRaisingDtlFB" property="numberOfRow" />
		 <html:hidden name="InvestigationRaisingDtlFB" property="sysDate" />
<html:hidden name="InvestigationRaisingDtlFB" property="selectedCheckbox" />


		<html:hidden name="InvestigationRaisingDtlFB" property="labCode" />
		<html:hidden name="InvestigationRaisingDtlFB" property="testCode" />
		<html:hidden name="InvestigationRaisingDtlFB" property="admissionDate" />

		<html:hidden name="InvestigationRaisingDtlFB"
			property="labTestCodeArray" />

		<html:hidden name="InvestigationRaisingDtlFB" property="bookMarkCode" />

		<html:hidden name="InvestigationRaisingDtlFB" property="patStatus" />

		<html:hidden name="InvestigationRaisingDtlFB" property="isBookMark" />

		<html:hidden name="InvestigationRaisingDtlFB" property="tmpLabCode" />

		<html:hidden name="InvestigationRaisingDtlFB" property="tmpTestCode" />

		<html:hidden name="InvestigationRaisingDtlFB" property="tmpGroupCode" />


		<html:hidden name="InvestigationRaisingDtlFB" property="tmpPriority" />

		<html:hidden name="InvestigationRaisingDtlFB" property="tstValArr" />

		<html:hidden name="InvestigationRaisingDtlFB" property="tstGrpArr" />
		<html:hidden name="InvestigationRaisingDtlFB" property="tmpCrNo" />

		<html:hidden name="InvestigationRaisingDtlFB" property="prvTestDtl" />
		<html:hidden name="InvestigationRaisingDtlFB" property="aptStatus" />
		<html:hidden name="InvestigationRaisingDtlFB" property="aptTestCode" />
		<html:hidden name="InvestigationRaisingDtlFB" property="aptLabCode" />

		<html:hidden name="InvestigationRaisingDtlFB" property="hideSaveButton" />
		<html:hidden name="InvestigationRaisingDtlFB"
			property="offlineAppoitMentDate" />
		<html:hidden name="InvestigationRaisingDtlFB"
			property="offlineAppoitmentDtl" />
<html:hidden name="InvestigationRaisingDtlFB" property="mandComboTextBoxComboNames" />
   <html:hidden name="InvestigationRaisingDtlFB" property="finalMandValues" />

   <html:hidden name="InvestigationRaisingDtlFB" property="tstOrTestGroupValue" />
    	<html:hidden name="InvestigationRaisingDtlFB"
			property="appointmentDate" />

		<html:hidden name="InvestigationRaisingDtlFB"
			property="appointmentTime" />

<html:hidden name="InvestigationRaisingDtlFB" property="appointmentRefNo" />
<html:hidden name="InvestigationRaisingDtlFB"
			property="advisedByDoctorName" />

		<input type="hidden" id="hiddenid1" name="searchLabName">
		<input type="hidden" id="hiddenid2" name="searchTestName">
		<input type="hidden" id="hiddenid3" name="searchTestGroupName">
		<input type="hidden" id="hiddenid4" name="advisedByName">
		<input type="hidden" id="hiddenid5" name="testCodeWise">
		<input type="hidden" id="hiddenid6" name="userGroupCodeWise">

		<input type="hidden" id="hiddenid23" name="searchTestNamelabwise">
<!-- changed by ashu -->

		<%-- <input type="hidden" name="testCodeListObj" id="testCodeListObj" value=<%=testCodeListVal%> > --%>
		<input type="hidden" name="testCodeSearchBy" id="testCodeSearchBy">


			 <html:hidden name="InvestigationRaisingDtlFB"
			property="testCodeLabValue" />


<html:hidden name="InvestigationRaisingDtlFB"
			property="delLabCode" />
			<html:hidden name="InvestigationRaisingDtlFB"
			property="delTestCode" />

<html:hidden name="InvestigationRaisingDtlFB" property="testLabTestCodeWise" />


<html:hidden name="InvestigationRaisingDtlFB" property="dupLabCode" />


<html:hidden name="InvestigationRaisingDtlFB" property="dupTestCode" />

<html:hidden name="InvestigationRaisingDtlFB" property="appoitmentNo" />
<html:hidden name="InvestigationRaisingDtlFB" property="hidAptNo" />

<html:hidden name="InvestigationRaisingDtlFB" property="labTestAptDate" />
<html:hidden name="InvestigationRaisingDtlFB" property="labTestAptTime" />
<html:hidden name="InvestigationRaisingDtlFB" property="labTestAptRefNo" />
<html:hidden name="InvestigationRaisingDtlFB" property="deletedtestdataviaresultentry" />


		<his:ButtonToolBarTag>

			<his:statusNew>
				 <logic:notEqual name="InvestigationRaisingDtlFB" property="flagDesk" value="1">

				<img class="button"
					src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"
					style="cursor: pointer"
					onkeypress="if(event.keyCode==13) cancelFunc();"
					tabindex="1" onclick="cancelFunc();">
			</logic:notEqual>
			</his:statusNew>

			<his:statusTransactionInProcess>
				<logic:equal name="InvestigationRaisingDtlFB" property="aptStatus"
					value="0">
					<logic:present name="<%=InvestigationConfig.LIST_APTBASED_TEST%>">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-next.png"/>'
							id="nextDiv" style="cursor: pointer; display: none" tabindex="1"
							onclick="displayAptDetails();">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
							tabindex="1" style="cursor: pointer"
							onkeypress="if(event.keyCode==13) submitForm('NEW');"
							tabindex="1" onclick="submitForm('NEW');">
					</logic:present>
				</logic:equal>
					<logic:equal name="InvestigationRaisingDtlFB" property="aptStatus"
					value="2">
					<logic:present name="<%=InvestigationConfig.LIST_APT_BY_DESK%>">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-next.png"/>'
							id="nextDiv1" style="cursor: pointer; display: none" tabindex="1"
							onclick="getAppointment();">

					</logic:present>
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
							tabindex="1" style="cursor: pointer"
							onkeypress="if(event.keyCode==13) submitForm('NEW');"
							tabindex="1" onclick="submitForm('NEW');">
				</logic:equal>

				<logic:equal name="InvestigationRaisingDtlFB" property="aptStatus"
					value="5">
					<logic:present name="<%=InvestigationConfig.LIST_APT_DETAILS_REQ_DTL_DESK%>">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-sv.png"/>'
							id="savediv12" style="cursor: pointer;" tabindex="1"
							onclick="saveUpdatedAppointment();">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
							tabindex="1" style="cursor: pointer"
							onkeypress="if(event.keyCode==13) submitForm('NEW');"
							tabindex="1" onclick="submitForm('NEW');">
					</logic:present>
				</logic:equal>


				<logic:present name="<%=InvestigationConfig.LIST_EPISODE_VO%>">
			<img class="button"  id="saveDiv"
						src='<his:path src="/hisglobal/images/btn-sv.png"/>'
						style="cursor: pointer"
						onkeypress="if(event.keyCode==13 && validateSave()) submitForm('SAVE');"
						tabindex="1" onclick="if(validateSave()) submitForm('SAVE');">
								 <logic:notEqual name="InvestigationRaisingDtlFB" property="flagDesk" value="1">

					<img class="button"
						src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"
						style="cursor: pointer"
						onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1"
						onclick="submitForm('NEW');">
						</logic:notEqual>

				</logic:present>

			</his:statusTransactionInProcess>

		</his:ButtonToolBarTag>


<his:statusTransactionInProcess>

				<div style="display: none" id="lengendStatus">
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
    <div id="divLegends" style="display:none">
	<his:ContentTag>
		<table width="100%" colspacing="1" colpadding="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
			<tr>

				<td width="1%" bgcolor="skyblue">

				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">

					  	 <bean:message key="RequistionRaised"/>

					</font>
				</td>
				<td width="1%" bgcolor="silver">

				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial,
 sans-serif">

					  	 <bean:message key="SampleCollected"/>

					</font>
				</td>
				<td width="1%" bgcolor="#CC99FF">

				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">

					  	 <bean:message key="PackingListGenerated"/>

					</font>
				</td>
				<td width="1%" bgcolor="#ffe6e6">

				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">

					  	 <bean:message key="Sample/PatientAccepted"/>

					</font>
				</td>
				<td width="1%" bgcolor="aqua">

				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">

					  	 <bean:message key="ResultEntered"/>

					</font>
				</td>

			</tr>
			<tr>


				<td width="1%" bgcolor="purple">

				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">

					  	 <bean:message key="ResultValidated"/>

					</font>
				</td>
				<td width="1%" bgcolor="fuchsia">

				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">

					  	 <bean:message key="PatientRejected"/>

					</font>
				</td>

				<td width="1%" bgcolor="blue">

				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">

					  	 <bean:message key="TestRescheduled"/>

					</font>
				</td>
				<td width="1%" bgcolor="olive">

				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">

					  	 <bean:message key="SampleCancelled"/>

					</font>
				</td>
				<td width="1%" bgcolor="lime">

				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">

					  	 <bean:message key="ReadyForReportPrinting"/>

					</font>
				</td>
			</tr>

			<tr>


				<td width="1%" bgcolor="gold">

				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">

					  	 <bean:message key="reportPrinted"/>

					</font>
				</td>
				<td width="1%" bgcolor="teal">

				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">

					  	 <bean:message key="TestCancelled"/>

					</font>
				</td>
				<td width="1%" bgcolor="#EB7273">

				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">

					  	 <bean:message key="TestDeleted"/>

					</font>
				</td>
				<td width="1%" bgcolor="brown">

				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">

					  	 <bean:message key="ReportGenerated"/>

					</font>
				</td>


				<td width="1%" bgcolor="#FFA500">

				</td>

				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">

					  	 <bean:message key="sentToMachine"/>

					</font>
				</td>

			</tr>
			<tr>

				<td width="1%" bgcolor="#9999FF">

				</td>

				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">

					  	 <bean:message key="testAdvised"/>

					</font>
				</td>

				</tr>


		</table>
	</his:ContentTag>
	</div>
	</div>


</his:statusTransactionInProcess>

	</html:form>
<div id='flexcroll-init'></div>
</div>
		<his:status />
<!-- 
</form> -->
</body>
