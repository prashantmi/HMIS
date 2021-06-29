/*1st js*/

$(function() {
$( "#datepicker" ).datepicker({
dateFormat: 'dd-M-yy',
showOn: "button",
buttonImage: "/HISInvestigationG5/hisglobal/images/cal.png",
buttonImageOnly: true,
buttonText: "Select  "
}).datepicker("setDate", new Date());
});


/*2nd js*/

$(function() {
	var tabs = $( "#tabs" ).tabs();
	tabs.find( ".ui-tabs-nav" ).sortable({
	axis: "x",
	stop: function() {
	tabs.tabs( "refresh" );
	}
	});
	});


/*3rd js*/


setInterval(function() {

	var choices = [];
	var els = document.getElementsByName('isTestGroup');
	for (var i=0;i<els.length;i++){
	  if ( els[i].checked ) {
	    //choices.push(els[i].value);
	   // alert(els[i].value);
	    if(els[i].value=="1")  // Test Group isTestGroup
		{
			document.getElementById('divSearchTestGroup').style.display="";
			document.getElementById('divSearchTest').style.display="none";
		}
		else  // Test
		{
			document.getElementById('divSearchTest').style.display="";
			document.getElementById('divSearchTestGroup').style.display="none";
		}


	  }
	}
    }, 500);


function showPatDetails()
{
document.getElementById("showhide").style.display="";
document.getElementById("hide").style.display="";
document.getElementById("show").style.display="none";

}

function hidePatDetails()
{
document.getElementById("showhide").style.display="none";
document.getElementById("hide").style.display="none";
document.getElementById("show").style.display="";
}


/*4th js*/

function showPatDetailsOnPagination()
{
document.getElementById("showhideOnPagination").style.display="";
document.getElementById("hideOnPagination").style.display="";
document.getElementById("showOnPagination").style.display="none";

}

function hidePatDetailsOnPagination()
{
document.getElementById("showhideOnPagination").style.display="none";
document.getElementById("hideOnPagination").style.display="none";
document.getElementById("showOnPagination").style.display="";
}





function showCurrDetail()
{

document.getElementById("lengendStatus").style.display="none";
	  document.getElementById('saveDiv').style.display="";
document.getElementById("currentReqDtl").style.width ="90%";


document.getElementById("prvReqDtl").style.color = "";
document.getElementById("prvReqDtl").style.fontFamily = "";
document.getElementById("prvReqDtl").style.fontSize = "";
document.getElementById("prvReqDtl").style.backgroundColor="";
document.getElementById("prvReqDtl").style.borderRadius ="";
document.getElementById("prvReqDtl").style.width ="";


}
function showPatDetails()
{
document.getElementById("showhide").style.display="";
document.getElementById("hide").style.display="";
document.getElementById("show").style.display="none";

}

function hidePatDetails()
{
document.getElementById("showhide").style.display="none";
document.getElementById("hide").style.display="none";
document.getElementById("show").style.display="";
}

function showPrvDetail()
{

document.getElementById("lengendStatus").style.display="";


	  document.getElementById("currentReqDtl").style.color = "";
	  document.getElementById("currentReqDtl").style.fontFamily = "";
	  document.getElementById("currentReqDtl").style.fontSize = "";
	  document.getElementById("currentReqDtl").style.backgroundColor="";
	  document.getElementById("currentReqDtl").style.borderRadius ="";
	  document.getElementById("currentReqDtl").style.width ="";

var CrNo=document.getElementsByName("patCrNo")[0].value;

	  var tableObj=document.getElementById('setPrvTestDtl');
	  	var numRows=tableObj.rows.length;
	 if(numRows>1)
		 {
	  	for(i=1;i<=numRows;i++)
		  {
	  //	alert("total length"+numRows);
		  //alert("value of "+i);
	  if(i==numRows)
		  {
		//  alert(i);

		  	setPrevTestDtlUsingAjax(CrNo);
		  return false;
		  }
	  else
		  {

		  document.getElementById("setPrvTestDtl").deleteRow(1);
		  }

  }


		 }

	  var availableTestGroups= setPrevTestDtlUsingAjax(CrNo);

	  if(availableTestGroups.length==4)
		  {



		  var tableObj=document.getElementById('setPrvTestDtl');

			var numRows=tableObj.rows.length;
		  	//alert("total length"+numRows);
		  		nRow=numRows;

		  	var tabRow=tableObj.insertRow(numRows);
		  	tabRow.id=parseInt(nRow);

		  	var td1=document.createElement("TD");

		  	td1.innerHTML="<div align='center'   >No Requisition Raised Till Now</div>";
		  	td1.className='tdfonthead';
		  	td1.colspan="1";
		  	td1.style.width='90%';

			tabRow.appendChild(td1);


		  	document.forms[0].numberOfRow.value=numRows;

		  }

}




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

  $(function(){
  	$("#automplete-1").keypress(function(event) {
      if (event.which == 13) {
      	searchLabWiseTest();
         }
  });
  });
  
  
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