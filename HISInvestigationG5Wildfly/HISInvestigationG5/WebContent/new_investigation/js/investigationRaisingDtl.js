$.getScript("media/misc/ajaxReplaceURLCaretForWildFly.js", function() {
	  // alert("Script loaded but not necessarily executed.");
	});

function setprevreq(obj)
{

//alert(obj.value);
	    $("#wait").css("display", "block");
	
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
		  //alert(obj.value);
                  if(obj.value=="0")
                  {	setPrevTestDtlUsingAjax(CrNo);
                  }
                  else
                      {
                	  setPrevTestDtlUsingAjaxALL(CrNo,obj.value);
                      }
                  
		  return false;
		  }
	  else
		  {

		  document.getElementById("setPrvTestDtl").deleteRow(1);
		  }

  }
		 }

	 var availableTestGroups="";
	  if(obj.value=="0")
      {	availableTestGroups=setPrevTestDtlUsingAjax(CrNo);
      }
      else
          {
    	  availableTestGroups= setPrevTestDtlUsingAjaxALL(CrNo,obj.value);
          }
      

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


function setpiddatainmodal()
{
	var dd='<%=pidoldreqset%>';
}

function setappslotdtls(divid,labCode)
{//alert("setappslotdtls");
	newarrayappdatalababsed.push(labCode+"$$"+"aptTagRow_"+labCode+"_0");
	
}
function getappdetails(divid,labCode)
{
	
//alert("cc"+divid);
var makeid=divid;
var newmakeid="freeSlotLabel"+"_"+makeid;
//alert("newmakeid"+newmakeid);
var htmll=document.getElementById(newmakeid).innerHTML;

}


function showtestswithteriff(a,labcode,id,btn)
{
    var fl=isappointmentmandatory(labcode);
  //  alert(fl);
    if(fl.split("#")[0]=="1")
	{
    	if(fl.split("#")[1]=="1")
    	{
        	alert("Appointment is mandatory for this lab.Test can't be raised.");
        	return null;
        }

    }
    else
        {
         var flag="";
    	if(fl.split("#")[1]=="1")
    	{

        }


        if(flag=="1")
        return null;

        }

	document.getElementsByName('searchLabName')[0].value=labcode;
   document.getElementsByName('labwisetestteriff')[0].value="1";
    document.getElementsByName("testLabTestCodeWise")[0].value="myhisswitchTestLab";
    document.getElementsByName('selectlabid')[0].value=id;
       searchLabWiseTest();

         document.getElementsByName('labwisetestteriff')[0].value="1";

}


/*chandna add*/



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
   

function callinggggg(labcode1,testcode1,ispidshow)
   {
	//   alert("call");
	   var data="";
	   //document.getElementById('myButton').style.display="";
	   if(document.getElementsByName('piddata')[0]!=undefined)
	   { data=document.getElementsByName('piddata')[0].value;
	   }
	   else
		   {
		   
		   data="";
		   }
		  //alert("data"+data);
		if(data!=undefined && data!="")
			{
			// alert("data undefined");
			if(ispidshow=="1")
			{
			data+=labcode1+"@@"+testcode1+"@@"+ispidshow+"##";
		//	alert("datadata"+data);
			document.getElementsByName('piddata')[0].value=data;
			document.getElementById('myButton').style.display="";

			}
			
		      	
			}
		else
			{
			
			if(ispidshow=="1")
			{
			data=labcode1+"@@"+testcode1+"@@"+ispidshow+"##";
			//alert("datadata"+data);

			document.getElementsByName('piddata')[0].value=data;
			document.getElementById('myButton').style.display="";

			}
			
			
			}
		
   }

function reqformhideone()
{
	document.getElementById("reqformss").style.display="none";
}

function showReqForms()
{
	document.getElementById("reqformss").style.display="";
	reqformshowone();
}
var reqformtestcodes="";
var reqformtestnames="";




function deleteRow(Str)
	{	
	
//	alert("inside delete");
	  var numRows=parseInt(document.getElementsByName('numberOfRow')[0].value);
      var tableObj=document.getElementById('tableSelectedLabTestIdHide');
  //   alert(tableObj);
      var temp=Str;
      //sendData(temp.rowIndex);  // Need To Check the Logic
      
       
      tableObj.deleteRow(temp.rowIndex);
      document.forms[0].numberOfRow.value=(numRows-1);
      
     // makeMandatory();
     
     
      return true;
	}

function deleteRow(index,tmpLabCode,tmpTestCode,grpcode)
{	
  
	//alert("index"+index+"labCode"+tmpLabCode+"testcode"+tmpTestCode);
	var tableObj=document.getElementById('tableSelectedLabTestIdHide');
   //alert("tableObj"+tableObj);
   //alert("parseInt"+(parseInt(index)+1));
	//tableObj.deleteRow((parseInt(index)));
	//alert("Tabkle no of rows"+tableObj.rows.length+"---indx-"+index);
	//tableObj.deleteRow(((index)));
  document.getElementsByName('numberOfRow')[0].value=tableObj.rows.length-1;
  
  //submitting page with hmode as "DELETEROW"
  	document.getElementsByName('hmode')[0].value="DELETEROW";
 	document.getElementsByName('tmpLabCode')[0].value=tmpLabCode;
  	document.getElementsByName('tmpTestCode')[0].value=tmpTestCode;
	document.getElementsByName('tmpGroupCode')[0].value=grpcode;
	  
  	document.forms[0].submit();
  
  //Calling AJAX
  //var tmpLabTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
  
 //var labTestCodeArray=deleteLabTestUsingAjax(tmpLabCode,tmpTestCode,tmpLabTestCodeArray);
  
 //document.getElementsByName('labTestCodeArray')[0].value=labTestCodeArray;
  
  
  
  
  
  //Code Commented As AJAX function is used for implementing below logic
  /*
  
  //Sample Code Setting
  var selectedSampleCode=document.getElementsByName('sampleInfo');
  var reqSampleCodeArray=[];
  
  for(var i=0,k=0;i<selectedSampleCode.length;i++)
	  {
	  	if(i!=index)
	  		reqSampleCodeArray[k++]=selectedSampleCode[i].value;
	  }
  
  document.getElementsByName('sampleInfo').length=0;
  
  document.getElementsByName('sampleInfo').length=reqSampleCodeArray.length;	  
  for(var k=0;k<reqSampleCodeArray.length;k++)
	  {
	  	document.getElementsByName('sampleInfo')[i].value=reqSampleCodeArray[i];
	  }
  
  
 var tmpLabTestCodeArray="";
 
 var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
 
 //Logic to update labTest*BOCodeArray value
 if(labTestCodeArray!=null&&labTestCodeArray!="")
	 {
		 var selectedLabTests=labTestCodeArray.split("@");
		 for(var i=0;i<selectedLabTests.length;i++)
			 {
			 	//Logic to Split selectedLabTest for getting lab Values
			 	var labTestArray=selectedLabTests.split("#");
			 	 var labCode=labTestArray[0];
			 	 var testCode=labTestArray[2];
			 	 if(tmpLabCode==labCode)
			 		 {
			 		 	if(tmpTestCode==testCode)
			 		 		{
			 		 		}
			 		 	else
			 		 		{
			 		 		 if(tmpLabTestCodeArray=="")
						 			tmpLabTestCodeArray=selectedLabTests[0];
						 	 else
						 			tmpLabTestCodeArray=tmpLabTestCodeArray+"@"+selectedLabTests[0];
			 		 		}
			 		 }
			 	 else
			 		 {
				 		 if(tmpLabTestCodeArray=="")
				 			tmpLabTestCodeArray=selectedLabTests[0];
				 		 else
				 			tmpLabTestCodeArray=tmpLabTestCodeArray+"@"+selectedLabTests[0];
			 		 }
			 	
			 }
		 document.getElementsByName('labTestCodeArray')[0].value=tmpLabTestCodeArray;
	 }
 
 */
 
  return true;
}

function deleteGroupRow(index,tmpLabCode,tmpTestCode)
{	
  
	//alert("index"+index+"labCode"+tmpLabCode+"testcode"+tmpTestCode);
	var tableObj=document.getElementById('tableSelectedLabTestIdHide');
   //alert("tableObj"+tableObj);
   //alert("parseInt"+(parseInt(index)+1));
	//tableObj.deleteRow((parseInt(index)));
	//alert("Tabkle no of rows"+tableObj.rows.length+"---indx-"+index);
	//tableObj.deleteRow(((index)));
  document.getElementsByName('numberOfRow')[0].value=tableObj.rows.length-1;
  
  //submitting page with hmode as "DELETEROW"
  	document.getElementsByName('hmode')[0].value="DELETEGROUPROW";
 	document.getElementsByName('tmpLabCode')[0].value=tmpLabCode;
  	document.getElementsByName('tmpTestCode')[0].value=tmpTestCode;
  
  	document.forms[0].submit();
  
  
 
  return true;
}



function validateRequisition()
{
	var labCodeSaved=document.getElementsByName("labCode");
	if(typeof labCodeSaved=='undefined' || labCodeSaved==null)
		{
			alert("Please Select atleast one Lab Test for Raising Requisition");
			return false;
		}
}

function submitFormForBookMark(bookMarkCode,hmode)
{
	//alert(bookMarkCode);
	
	document.getElementsByName('aptTestCode')[0].value="";
	document.getElementsByName('aptLabCode')[0].value="";

	document.getElementsByName('bookMarkCode')[0].value=bookMarkCode;
	document.getElementsByName('hmode')[0].value=hmode;
	document.forms[0].submit();
}



function setSampleCodeUsingAjax(objSampleCode,labCode,TestCode)
{
	
	
	
	var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;

	var newlabtestcodearray=document.getElementsByName('newlabtestcodearray')[0].value;
	//alert("newlabtestcodearray"+newlabtestcodearray);
	var values="";
	if(objSampleCode!=null)
     values=objSampleCode.value;

    var sampleshortname=values.split("$")[1];
	//alert("sampleshortname"+sampleshortname);
	var sampleCode=values.split("$")[0];
//	alert("samplecode"+sampleCode);
	var sampleName="";
	if(objSampleCode!=null)
	var sampleName=objSampleCode.options[objSampleCode.selectedIndex].innerHTML;
	labTestCodeArray=labTestCodeArray.replace(/#/g,";"); // As hash separator doesnot work in URL
	newlabtestcodearray=newlabtestcodearray.replace(/#/g,";");
	
	if(labTestCodeArray.indexOf("&")!=-1)
		labTestCodeArray=labTestCodeArray.replace(/&/g,"*");

	if(newlabtestcodearray.indexOf("&")!=-1)
		newlabtestcodearray=newlabtestcodearray.replace(/&/g,"*");

	var finalLabTestCodeArray = modifyLabTestCodeArray(sampleCode,labCode,TestCode,labTestCodeArray,sampleshortname,newlabtestcodearray);
	document.getElementsByName('labTestCodeArray')[0].value=finalLabTestCodeArray;
	//alert(document.getElementsByName('labTestCodeArray')[0].value);
}





function modifyLabTestCodeArray(tmpSampleCode,tmpLabCode,tmpTestCode,tmpLabTestCodeArray,sampleshortname,labarray1)
{
	//alert("old"+tmpLabTestCodeArray);
	//alert("new"+labarray1);
	
	//alert(tmpSampleCode+"  "+tmpLabCode+"     "+tmpTestCode+"    "+tmpLabTestCodeArray);
	
	var flg = false;
	var labTestCodeArray = "";
	var _mode = "AJX_MODIFY_LABTESTCODEARRAY";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&tmpLabCode="+tmpLabCode+"&tmpTestCode="+tmpTestCode+"&tmpSampleCode="+tmpSampleCode+"&labTestCodeArray="+labarray1+"&reqSampleShortName="+sampleshortname, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
	///alert(data);
			labTestCodeArray = data;
			tmpLabTestCodeArray=tmpLabTestCodeArray.replace(/;/g,"#");
			document.getElementsByName('newlabtestcodearray')[0].value=labTestCodeArray;
			//document.getElementsByName('labTestCodeArray')[0].value=tmpLabTestCodeArray;
			flg = true;
		},
        error: function(error)
        {
        	labTestCodeArray=tmpLabTestCodeArray.replace(/;/g,"#"); // As hash separator doesnot work in URL

             //  labTestCodeArray = tmpLabTestCodeArray;
            flg = false;
        }};
	//alert("new"+labTestCodeArray);
	var objDojoAjax = dojo.xhrPost(objXHR);
	return tmpLabTestCodeArray;
}


function callAjax(objPriority)
{
	//alert("hi");
	var id=objPriority.id;
	var idd=id.split("#");
	var iddd=idd[0];
	var nrow=idd[1];
	var checker=nrow-1;
//	alert(checker);
	var length=document.getElementsByName('sampleInfo').length;
//	alert(length);
	for(var i=0;i<length;i++)
		{
		//alert("inside i"+i);
		if(i!=checker)
			{
		//	alert("check");
		document.getElementsByName('priority')[i].onchange();
	//	alert("done");	
			}
		}

}
	
	

function setPriorityUsingAjax(objPriority,labCode,TestCode)
{
	//alert(objPriority.id);
	var priorityvalue=objPriority.value;
	var id=objPriority.id;
	var idd=id.split("#");
	var iddd=idd[0];
	var nrow=idd[1];
	//alert(nrow);
	//alert(iddd);
	
	if(iddd=="prioritytbl")
	  {


/*
		if(document.getElementsByName('labTestCodeArray')[0].value.split('@')[nrow-1].split('#')[5]=='P')
			{;}


		else
			{*/
		var sampleidcode="sampleaddtbl#"+nrow;
		//var samplecode=document.getElementById(sampleidcode).value;
		var labidcode="labtbl#"+nrow;
		var labcode=document.getElementById(labidcode).value;
		
		//alert(samplecode);
		//var length=document.getElementsByName('sampleInfo').length;
		
//	alert(length);	
	
/*	for(var i=0;i<length;i++)
		{
		
		if(samplecode==(document.getElementsByName('sampleInfo')[i].value) && labcode==(document.getElementsByName('labCode')[i].value))
		{
			//alert("ok");
		//	if(priorityvalue=="2")
		document.getElementsByName('priority')[i].value=priorityvalue;
		//alert(i);
	   
	//	modifyPriorityLabTestCodeArray(priorityvalue,labCode,TestCode,labTestCodeArray);
		}
		
		}*/

			/*}*/
	
	  }
	else if(iddd=="priorityaddtbl")
		{

		/*if(document.getElementsByName('labTestCodeArray')[0].value.split('@')[nrow-1].split('#')[5]=='P')
		{;}

		else
			{*/
		var sampleidcode="sampletbl#"+nrow;
		//var samplecode=document.getElementById(sampleidcode).value;
		//alert(samplecode);
	//	var length=document.getElementsByName('sampleInfo').length;
		var labidcode="labaddtbl#"+nrow;
		var labcode=document.getElementById(labidcode).value;
		
//	alert(length);	
	
	/*for(var i=0;i<length;i++)
		{
		
		if(samplecode==(document.getElementsByName('sampleInfo')[i].value) && labcode==(document.getElementsByName('labCode')[i].value))
		{
			//alert("ok");
		//	if(priorityvalue=="2")
		document.getElementsByName('priority')[i].value=priorityvalue;
		
		
		//document.getElementsByName('priority')[i].onchange();
		}
		}*/
		/*}	*/
		
		}
	
	var labTestCodeArray=document.getElementsByName('newlabtestcodearray')[0].value;
	//alert(labTestCodeArray);
	var priority=objPriority.value;
	labTestCodeArray=labTestCodeArray.replace(/#/g,";"); // As hash separator doesnot work in URL

	if(labTestCodeArray.indexOf("&")!=-1)
		labTestCodeArray=labTestCodeArray.replace(/&/g,"*");

	//alert("data1"+priority+"@!"+labCode+"@!"+TestCode+"@!");
	var finalLabTestCodeArray = modifyPriorityLabTestCodeArray(priority,labCode,TestCode,labTestCodeArray);
	document.getElementsByName('newlabtestcodearray')[0].value=finalLabTestCodeArray;
	
	//alert("data"+document.getElementsByName('newlabtestcodearray')[0].value);

}
	

	

function modifyPriorityLabTestCodeArray(tmpPriority,tmpLabCode,tmpTestCode,tmpLabTestCodeArray)
{
	var flg = false;
	var labTestCodeArray = "";
	var _mode = "AJX_MODIFY_PRIORITY";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&tmpLabCode="+tmpLabCode+"&tmpTestCode="+tmpTestCode+"&tmpPriority="+tmpPriority+"&newlabtestcodearray="+tmpLabTestCodeArray, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert("data2"+data);
			labTestCodeArray = data;
			flg = true;
		},
        error: function(error)
        {
            labTestCodeArray = tmpLabTestCodeArray;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	//alert("data3"+labTestCodeArray);
	return labTestCodeArray;
}



// getting test based on labCode
function setTestComboUsingAjax(labCode)
{
	var flg = false;
	var tstValArr = "";
	var _mode = "AJX_TEST_COMBO";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&tmpLabCode="+labCode, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			tstValArr = data;
			flg = true;
		},
        error: function(error)
        {
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	document.getElementsByName('tstValArr')[0].value=tstValArr;
	//alert(document.getElementsByName('tstValArr')[0].value);
	 
	return tstValArr;
}


function reqformshowone()
{

var globaltest="";
	
//alert("f1");
//document.getElementById("reqformss").style.display="";


var values=document.getElementsByName('newlabtestcodearray')[0].value;
//alert(values);

var val=values.split("@");
//alert(val.length);
var mappedData="";
for(var a=0;a<val.length;a++)
{
	var data=val[a];
	   data=data.split("#");
	   mappedData+=data[3]+"#"+data[2]+"#"+data[1]+"#"+data[0]+"#"+data[24]+"@";   //testname,testcode,labname,labcode,reqformneeded
}

//alert("mappedData"+mappedData);

var table = document.getElementById("tbll");

var masterdata=mappedData.split("@");
//alert("masterdata.length"+masterdata.length);
$("#tbll").find("tr:not(:first)").remove();

/* 
added by chandan */
 var testcodecombo="";
for(var i1=1;i1<(masterdata.length);i1++)
{
	var valuess1=masterdata[i1-1].split("#");
	 testcodecombo+=valuess1[1]+"#";
	// checkreqformTestType(valuess1[1]);     
} 

//alert("testcodecomboo"+testcodecombo);
/* end */
//alert("masterdata.length"+ (masterdata.length-1));
for(var i=0;i<(masterdata.length-1);i++)
{
	
	var valuess1=masterdata[i].split("#");
	 testcodecombo+=valuess1[1]+"#";

	// alert("chec"+i);
		//alert("test"+valuess1[1]);
		// 
	 var getData =  checkreqformTestType(valuess1[1]);
	// alert("getData"+getData);
	 var mappedTest=getData.split("#")[2];
	// alert("mappedTest"+mappedTest);
		var rowCount = table.rows.length;
		//alert("rowCount"+rowCount);
	var row = table.insertRow(rowCount);

	/* testCode,testName,labCode,labName */
	var valuess=masterdata[i].split("#");
	var testCode=valuess[1];
	var testName=valuess[0];
	var labCode=valuess[3];
	var labName=valuess[2];
	var reqformneeded=valuess[4];
	if(reqformneeded=="1")
		{
	 if(mappedTest=="0")
		 {
		// alert("maptest 0");
		 var rwnamee="";
			
				row.id = testCode;
		       //alert("rowid"+row.id);
				row.name = testCode;
				row.value = labName;
				row.testt=testCode+"^"+labCode;
				
				rwnamee=row.name;
				if(reqformtestcodes=="")
				reqformtestcodes+=row.name+"#";
				else
					reqformtestcodes+="@"+row.name+"#";	
				reqformtestnames+=testName+"#";
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				cell1.innerHTML = masterdata[i].split("#")[0];
			//	alert("alltests"+globaltest);
			     mappedTest=mappedTest.replace(/@/g , "#");
		    
				globaltest+=testCode+"#"+mappedTest+"#";
				//alert("globaltestnull"+globaltest);
		cell2.innerHTML = "<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+")'>";
	 }

	 else
		 {
              // alert("mapped test not 0");
              //alert( globaltest.includes(mappedTest));

               if(globaltest=='')
      		 {
            	   if(mappedTest.includes("@"))
             		row.id = testCode;
            	   else
            		   row.id = mappedTest;  
           //  alert("rowid"+row.id);
          
      		row.name = testCode;
      		row.value = labName;
      		row.testt=testCode+"^"+labCode;
      	//	alert("row.value"+row.value);
      		rwnamee=row.name;
      		reqformtestcodes+=row.name+"#";
      		reqformtestnames+=testName+"#";
      		var cell1 = row.insertCell(0);
      		var cell2 = row.insertCell(1);
      		cell1.innerHTML = masterdata[i].split("#")[0];
      	//	alert("alltests"+globaltest);
      	     mappedTest=mappedTest.replace(/@/g , "#");
          
      		globaltest+=testCode+"#"+mappedTest+"#";
      		//alert("globaltestnull"+globaltest);
      cell2.innerHTML = "<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+")'>";
      			 }
               else 
                   {
                  // alert("globaltest"+globaltest);
                 //  alert("mappedTest"+mappedTest);
                   
                     //     alert("match @@@"+mappedTest.includes("@")+globaltest.includes(mappedTest))
                       if(!mappedTest.includes("@"))
                           {
            	   if(globaltest.includes(mappedTest))
                	   {
            		   var tr ="";
                      //    alert("match"+globaltest);
                        if(document.getElementById(mappedTest)==null)
                        tr = document.getElementById(testCode);
                        else
                        tr = document.getElementById(mappedTest);    
        				var rwname=tr.name+"#";
        				rwname+=testCode;
        				rwnamee=rwname;
        				tr.name=rwname;
        				row.value = tr.value+";"+labName;
        				row.testt = tr.testt+";"+testCode+"^"+labCode;
        				tr.testt=row.testt;
        				//alert("row.value1"+row.value);
        				//alert("trridname"+tr.name+"@"+rwnamee);
        				var trr=document.getElementsByName(mappedTest).value;
        				
        				var td = tr.getElementsByTagName("td");
        				for(var l=1;l<td.length;l++) {
        					var namee=td[0].innerHTML+",";
        					var nameee=td[0].innerHTML+"#";
                           //  alert(td[1].innerHTML);
        					namee+=testName;
        					nameee+=testName;
        					reqformtestnames=nameee;
        					reqformtestcodes=rwnamee;
        	                   //alert("td[i].innerHTML"+td[i].innerHTML);
                           td[0].innerHTML=namee;
        				    //console.log(td[i].innerHTML);
        				}
                	   }
            	   else
                	   {
            		   reqformtestnames+="@"+testName;
       				reqformtestcodes+="@"+testCode;
       				if(mappedTest.includes("@"))
                 		row.id = testCode;
                	   else
                		   row.id = mappedTest;
         		   
       				row.name = testCode;
       				row.value=labName;
       				row.testt=testCode+"^"+labCode;
       				//alert("row.value2"+row.value);
       				var cell1 = row.insertCell(0);
       				var cell2 = row.insertCell(1);
       				cell1.innerHTML = masterdata[i].split("#")[0];
       			//	alert("globaltestelse"+globaltest);
       		cell2.innerHTML = "<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+")'>";
       		globaltest+=testCode+"#"+mappedTest+"#";
                	   }
                    }
                       else
                           {
                           
                    	   var tr ="";
                       //    alert("@ find"+globaltest.includes(testCode))
                    	  if(document.getElementById(testCode)==null)
                          tr=document.getElementById(mappedTest);
                    	  else
                    		  tr=document.getElementById(testCode);	  
            				var rwname=tr.name+"#";
            				rwname+=testCode;
            				rwnamee=rwname;
            				tr.name=rwname;
            				row.value = tr.value+","+labName;
            				tr.value=row.value;
            				row.testt = tr.testt+";"+testCode+"^"+labCode;
            				tr.testt=row.testt;
            			//	alert("row.value3"+row.value);
            				//alert("trridname"+tr.name+"@"+rwnamee);
            				var trr=document.getElementsByName(mappedTest).value;
            				
            				var td = tr.getElementsByTagName("td");
            				for(var l=1;l<td.length;l++) {
            					var namee=td[0].innerHTML+",";
            					var nameee=td[0].innerHTML+"#";
                               //  alert(td[1].innerHTML);
            					namee+=testName;
            					nameee+=testName;
            					reqformtestnames=nameee;
            					reqformtestcodes=rwnamee;
            	                   //alert("td[i].innerHTML"+td[i].innerHTML);
                               td[0].innerHTML=namee;
            				    //console.log(td[i].innerHTML);
            				}
                          }
                       
                   }
 }
	 
		}	

}
//var tbl='<table width="100%" border="1"><tr><td colspan="2" rowspan="1">' + " wdw" + '</td></tr><tr><td width="118">' + "sxs" + '</td><td width="186">' + "xs" + '</td></tr></table>';

//$('#reqformss').append(tbl);

}
function showRequisition(selectedEpisode)
{
	//alert(selectedEpisode);
	document.getElementsByName('selectedEpisode')[0].value=selectedEpisode;
}


function showLegends(){
	  document.getElementById("divLegends").style.display="block"; 
}
function showLegendsNone(){
document.getElementById("divLegends").style.display="none";
}
  
function cancelFunc()
{
	window.parent.closeTab();
}
  
function getAppointment(){
	var count=0;
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
	
	document.getElementsByName('hmode')[0].value='GETAPPOINTMENT';
	document.forms[0].submit();
	
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



 function saveUpdatedAppointment(){
	 
	 
	 
	 var chkBoxSample=document.getElementsByName("allotAppointment");
		var j=0;				
	 
	for(var i=0;i<chkBoxSample.length;i++)
		if(!document.getElementsByName("allotAppointment")[i].checked)
	 j++;
	 
	 
	
	if(j==chkBoxSample.length)
	{ 
		alert("Please select atleast one Record");
		return false;
	}
	
	
	
	
	for(i=0;i<chkBoxSample.length;i++)
	{
		
	//	alert("inside loop");
	var flag=false;
	if(document.getElementsByName("allotAppointment")[i].checked)
		{
		
		SplitValue=document.getElementsByName("allotAppointment")[i].value.split("#");
		
	var isAppointment=SplitValue[9];	
	var labCode=SplitValue[3];
	var testCode=SplitValue[4];
	
	if(isAppointment=="1")
	{
		var divId="aptTagRow_"+labCode+"_"+testCode;
	//alert("app based test condition");
	if(typeof document.getElementsByName('aptForDate_'+divId+'')[0] != 'undefined')
		{
	
	var aptSatus=createAppointment(divId);
	//alert(document.getElementsByName('appointmentRefNo')[0].value);
	
	var objaptno=document.getElementsByName('appointmentRefNo')[0].value;
	//alert("ref no is " +objaptno);
	//setAptNoUsingAjax(objaptno,labCode,testCode)
	
	//alert("apt "+aptSatus);appointmentRefNo
 	if(!aptSatus)
		 {
		 
		return false;
		}
		}
	
	}
	if(isAppointment==1 && typeof document.getElementsByName('aptForDate_'+divId+'')[0] == 'undefined')
	{
	
	alert("Can't raise Test as no slots are available");
	// alert("returing false 2");
	return false;
	
	}
	
		}
	}

	
	 var today = new Date();
	 var date=convertDateToStr(today,"dd-Mon-yyyy");
		var Time=convertDateToStr(today,"hh:mm"); 
		    //alert(date);
		    //alert(Time);

	    
	//alert(document.getElementsByName("freeSlotLabel")[0].value);
	//var freeSlotLabel=document.getElementsByName("freeSlotLabel")[0].value;
	//var arrAppointmentDate=freeSlotLabel.split(" ");
		if (typeof document.getElementsByName('aptForDate_'+divId+'')[0] != 'undefined')
			{
			//alert("set app date and time now");	
			///alert(document.getElementsByName('slotST_'+divId+'')[0].value);
	           document.getElementsByName('appointmentTime')[0].value=document.getElementsByName('slotST_'+divId+'')[0].value;

				document.getElementsByName('appointmentDate')[0].value=document.getElementsByName('aptForDate_'+divId+'')[0].value;
				
				//createAppointment();
		//if (typeof $('[name="slotST"]').val() === "undefined")
		     
			}
			else
			{
				//alert("offline Apt Details"+document.getElementsByName('offlineAppoitmentDtl')[0].value);
				 if(document.getElementsByName('offlineAppoitmentDtl')[0].value=='')
					 {
					 
					 
				    //alert(document.getElementsByName('appointmentDate')[0].value);
			        ///alert(document.getElementsByName('appointmentTime')[0].value);
			        
			        
			        
					 }
				 else
					 {
					// alert("false");
					 var dateAndTime=document.getElementsByName('offlineAppoitmentDtl')[0].value;
					 var SplitWithSpace=dateAndTime.split(' ');
					 
					 var offlineAptDate=SplitWithSpace[0];
					 var offLineAptTime=SplitWithSpace[1];
				//	 alert(offlineAptDate);
					// alert(offLineAptTime);
					 
					 document.getElementsByName('appointmentDate')[0].value=offlineAptDate;
				        document.getElementsByName('appointmentTime')[0].value=offLineAptTime;
					 
				        document.getElementsByName('appointmentRefNo')[0].value=document.getElementsByName('offlineAptDtl')[0].value;
				        
				       // alert(document.getElementsByName('appointmentRefNo')[0].value);
				        
					 }
			}
		 
	
		if(chkBoxSample==null || chkBoxSample.length<1)
			{ 
				alert("Please select atleast one Record");
				return false;
			}
	 

		document.getElementsByName('hmode')[0].value='SAVEAPPOINTMENT';
		document.forms[0].submit();
	 
 }
//Function for displaying selected Lab List
//Function For Check Displaying Test Group Details
//Function for displaying selected Lab List

$("#datepicker").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date(new Date().getTime() - (1000 * 60 * 60 * 24)));

function showSelectedLabTestList(objSelectedLabTest,objSelectedLabTest1)
{
	//alert("aa"+objSelectedLabTest);
	var chkVal=objSelectedLabTest.value;
	//var chkVal="10001#Biochemistry-General Medicine#10086#agetest (45699)#sample not carry#S#0#0#1005$ABDOMEN#1#0#0#0#null#null#null#null#null##null#null#1#1#Blood#0#null#";
	//alert(chkVal);
	// chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential      //Please Ensure the corresponding Changes before changing this order
	var chkArr=chkVal.split("#");
	//alert(((chkArr[3].split("("))[1].split(")"))[0]);
	var testcode=chkArr[2];
	//alert("testcode"+testcode);
	var remarks = null;
  	
  /*	var issufficientamount = issufficientamountt(testcode,"0");

  	if(issufficientamount=="1")
  	{
  		
  		alert("Patient Account Balance Going TO Be Insufficient. Please Deposit Part Payment!!");
  		return null;
  	}
  	*/
  	remarks = checkTestAvailabilityAjax((((chkArr[3].split("("))[1].split(")"))[0]).toUpperCase());

  	if(remarks != "1"){

  		if(remarks != "0")
  			alert('Test not Available ! Remarks : '+remarks);
  		else
  			alert('Test not Available !');
	
  		objSelectedLabTest.checked=false;
		return false;
  	}
	
	
	
	//Adding Row with the given values
	if(objSelectedLabTest.checked)
		{
		
		
		
		var labCode=chkArr[0];
		// Lab Name
		var labName=chkArr[1];
		// Test Code
		var testCode=chkArr[2];
		// Test Name
		var testName=chkArr[3];
		
		//alert("comboo"+chkArr[4]);

var labCodeSaved=document.getElementsByName("labCode");
var testCodeSaved=document.getElementsByName("testCode");
var size=0;
if(typeof labCodeSaved!='undefined' && labCodeSaved!=null)
	size=labCodeSaved.length;
if(size>0)
{
for(var i=0;i<size;i++)
		{
			if(labCodeSaved[i].value==labCode)
				{
					if(testCodeSaved[i].value==testCode)
					{
						/*var nRow21=0;
						var tableObj21=document.getElementById('tableSelectedLabTestIdHide');
						
						var numRows21=tableObj21.rows.length;
						nRow21=numRows21;
						
						var idd21="sampleaddtbl#"+nRow21;
						var val21=document.getElementById(idd21);
						setSampleCodeUsingAjax(val21,labCode,testCode);
*/
						alert("Laboratory :'"+labName+"' with corresponding Test :'"+testName+"' Already added. Please Select another Lab/Test.");
						var cbs =document.getElementsByName('chkLabTest');
						 //alert(cbs);
						for(var i=0; i < cbs.length; i++) {
							if(cbs[i].checked)
					    	{
							var id=cbs[i].id;
							//alert(id);
							if(id==labCode+testCode)
								{
							      document.getElementById(id).checked = false;
					    	     }
						     }
					      }
						return false;
					
					}
				}
		}
		}

		
		if(chkArr[22]=='1')//is time bound
		{
			if(chkArr[21]=='1') //is in time
			{
				
				document.getElementById("td1"+chkVal).style.backgroundColor="#ff9999";
				document.getElementById("td2"+chkVal).style.backgroundColor="#ff9999";
	/*			document.getElementById("td3"+chkVal).style.backgroundColor="#ff9999";
	*/
				 
				//Setting labCodeArray Value
				var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
				var newlabtestcodearray=document.getElementsByName('newlabtestcodearray')[0].value;
				if(labTestCodeArray!=null&&labTestCodeArray!="")
					{
					labTestCodeArray=labTestCodeArray+"@"+chkVal;
					newlabtestcodearray=newlabtestcodearray+"@"+objSelectedLabTest1;
					}
					else
						{
					labTestCodeArray=chkVal;
					newlabtestcodearray=	objSelectedLabTest1;
						}
					document.getElementsByName('labTestCodeArray')[0].value=labTestCodeArray;
					document.getElementsByName('newlabtestcodearray')[0].value=newlabtestcodearray;
					var map = new Object(); // or var map = {};

				AddRowToTable(chkArr,objSelectedLabTest1,chkVal);
			}
			else{
				objSelectedLabTest.checked=false;
				alert("Time Slot is not open for this Lab");
			}
		}
		else{
			document.getElementById("td1"+chkVal).style.backgroundColor="#ff9999";
			document.getElementById("td2"+chkVal).style.backgroundColor="#ff9999";
/*			document.getElementById("td3"+chkVal).style.backgroundColor="#ff9999";
*/
			 
			//Setting labCodeArray Value
			var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
			var newlabtestcodearray=document.getElementsByName('newlabtestcodearray')[0].value;
			if(labTestCodeArray!=null&&labTestCodeArray!="")
				{
				labTestCodeArray=labTestCodeArray+"@"+chkVal;
				newlabtestcodearray=newlabtestcodearray+"@"+objSelectedLabTest1;
				}
				else
					{
				labTestCodeArray=chkVal;
				newlabtestcodearray=objSelectedLabTest1;
					}
				document.getElementsByName('labTestCodeArray')[0].value=labTestCodeArray;
				document.getElementsByName('newlabtestcodearray')[0].value=newlabtestcodearray;
			//alert(newlabtestcodearray);
			AddRowToTable(chkArr,objSelectedLabTest1,chkVal);

		}
		}
	else
		{
		document.getElementById("td1"+chkVal).style.backgroundColor="";
		document.getElementById("td2"+chkVal).style.backgroundColor="";
		//document.getElementById("td3"+chkVal).style.backgroundColor="";
		
	//to delete the test on de selecting the corresponding check box	
	//	alert(chkArr[0]);
	//	alert(chkArr[2]);
		
		var newlabtestcodearrayy=document.getElementsByName('newlabtestcodearray')[0].value;

		   var isgrpcode="0";
	  
		   if(newlabtestcodearrayy!=null && newlabtestcodearrayy!="")
		{
	    	  
	    	  var data=newlabtestcodearrayy.split("@");
	    	  
	    	  for(var f=0;f<data.length;f++)
	    		  {
	    		  var newdata=data[f].split("#");
	    		  var newstrr=newdata[0]+"#"+newdata[2] //labtestcode
	    		  
	    		  if((chkArr[0]+"#"+chkArr[2])==(newstrr))
	    			  {
	    			  isgrpcode=newdata[10];
	    			  break;
	    			  }
	    		  
	    		  }
	    	  
		}
		
		deleteRow(1,chkArr[0],chkArr[2],isgrpcode);
		
		
		}
	
	showamount();
}


function showSelectedLabTestList1(objSelectedLabTest,objSelectedLabTest1)
{
	//alert("bb"+showSelectedLabTestList1);
	var chkVal=objSelectedLabTest;
	//var chkVal="10001#Biochemistry-General Medicine#10086#agetest (45699)#sample not carry#S#0#0#1005$ABDOMEN#1#0#0#0#null#null#null#null#null##null#null#1#1#Blood#0#null#";
	//alert(chkVal);
	// chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential      //Please Ensure the corresponding Changes before changing this order
	var chkArr=chkVal.split("#");
	//alert(chkArr[22]+ " " +chkArr[21]);
	//Adding Row with the given values
	//if(objSelectedLabTest.checked)
		//{
		if(chkArr[22]=='1')//is time bound
		{
			if(chkArr[21]=='1') //is in time
			{
				
				/*document.getElementById("td1"+chkVal).style.backgroundColor="#ff9999";
				document.getElementById("td2"+chkVal).style.backgroundColor="#ff9999";
				document.getElementById("td3"+chkVal).style.backgroundColor="#ff9999";*/
	
				 
				//Setting labCodeArray Value
				var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
				var newlabtestcodearray=document.getElementsByName('newlabtestcodearray')[0].value;
				//alert("labTestCodeArray : "+labTestCodeArray+" : newlabtestcodearray :"+newlabtestcodearray);
				if(labTestCodeArray!=null&&labTestCodeArray!="")
					{
					labTestCodeArray=labTestCodeArray+"@"+chkVal;
					newlabtestcodearray=newlabtestcodearray+"@"+objSelectedLabTest1;
					}
					else
						{
					labTestCodeArray=chkVal;
					newlabtestcodearray=	objSelectedLabTest1;
						}
					document.getElementsByName('labTestCodeArray')[0].value=labTestCodeArray;
					document.getElementsByName('newlabtestcodearray')[0].value=newlabtestcodearray;
				//	alert("newlabtestcodearray"+newlabtestcodearray);
				AddRowToTable(chkArr,objSelectedLabTest1,chkVal);
			}
			else{
				objSelectedLabTest.checked=false;
				alert("Time Slot is not open for this Lab");
			}
		}
		else{
			/*document.getElementById("td1"+chkVal).style.backgroundColor="#ff9999";
			document.getElementById("td2"+chkVal).style.backgroundColor="#ff9999";
			document.getElementById("td3"+chkVal).style.backgroundColor="#ff9999";*/

			 
			//Setting labCodeArray Value
			
			var labCode=chkArr[0];
			// Lab Name
			var labName=chkArr[1];
			// Test Code
			var testCode=chkArr[2];
			// Test Name
			var testName=chkArr[3];

			
			var labCodeSaved=document.getElementsByName("labCode");
			var testCodeSaved=document.getElementsByName("testCode");
		var size=0;
			if(typeof labCodeSaved!='undefined' && labCodeSaved!=null)
				size=labCodeSaved.length;
			if(size>0)
			{
		for(var i=0;i<size;i++)
					{
						if(labCodeSaved[i].value==labCode)
							{
								if(testCodeSaved[i].value==testCode)
								{
									/*var nRow21=0;
									var tableObj21=document.getElementById('tableSelectedLabTestIdHide');
									
									var numRows21=tableObj21.rows.length;
									nRow21=numRows21;
									
									var idd21="sampleaddtbl#"+nRow21;
									var val21=document.getElementById(idd21);
									setSampleCodeUsingAjax(val21,labCode,testCode);
		*/
									alert("Laboratory :'"+labName+"' with corresponding Test :'"+testName+"' Already added. Please Select another Lab/Test.");
									var cbs =document.getElementsByName('chkLabTest');
									 //alert(cbs);
									for(var i=0; i < cbs.length; i++) {
										if(cbs[i].checked)
								    	{
										var id=cbs[i].id;
										//alert(id);
										if(id==labCode+testCode)
											{
										      document.getElementById(id).checked = false;
								    	     }
									     }
								      }
									return false;
								
								}
							}
					}
					}
			
			
			
			var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
			var newlabtestcodearray=document.getElementsByName('newlabtestcodearray')[0].value;
			if(labTestCodeArray!=null&&labTestCodeArray!="")
				{
				labTestCodeArray=labTestCodeArray+"@"+chkVal;
				newlabtestcodearray=newlabtestcodearray+"@"+objSelectedLabTest1;
				}
				else
					{
				labTestCodeArray=chkVal;
				newlabtestcodearray=objSelectedLabTest1;
					}
				document.getElementsByName('labTestCodeArray')[0].value=labTestCodeArray;
				document.getElementsByName('newlabtestcodearray')[0].value=newlabtestcodearray;
			//alert(newlabtestcodearray);
			AddRowToTable(chkArr,objSelectedLabTest1,chkVal);

		}
		//}
	/*else
		{
		document.getElementById("td1"+chkVal).style.backgroundColor="";
		document.getElementById("td2"+chkVal).style.backgroundColor="";
		document.getElementById("td3"+chkVal).style.backgroundColor="";
		
	//to delete the test on de selecting the corresponding check box	
	//	alert(chkArr[0]);
	//	alert(chkArr[2]);
		deleteRow(1,chkArr[0],chkArr[2]);
		
		
		}*/
}

function showSelectedLabTestGroupList(objSelectedLabTest,objSelectedLabTest1)
{
	var flag="0";
	var chkVal=objSelectedLabTest.value;
	var chkArr=chkVal.split("#");
	// chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential      //Please Ensure the corresponding Changes before changing this order
	//alert("inside");
	
	if(objSelectedLabTest.checked)
		{
		
		var isduplicatetestraisedtoday = isduplicatetestrasied(chkArr);
               
		if(isduplicatetestraisedtoday=="1" )
		{
	if (confirm('This Group has already been rasied Today.Do you want to Continue')) {
		flag="0";
	    // Save it!
	} else {
		var mainn=chkArr[0]+chkArr[10];
		document.getElementById(mainn).checked=false;
		//document.getElementById("td1"+chkVal).style.backgroundColor="";
		//document.getElementById("td2"+chkVal).style.backgroundColor="";
		//document.getElementById("td3"+chkVal).style.backgroundColor="";
		
	//to delete the test on de selecting the corresponding check box	
	//	alert(chkArr[0]);
	//	alert(chkArr[2]);
		 deleteGroupRow(1,chkArr.split("#")[0],chkArr.split("#")[10]);

		flag="1";
	    // Do nothing!
	}
		}
		
		
		if(flag=="1")
	  	{
	  		return null;
	  	}
		
		         
			if(chkArr[22]=='1')
			{
				if(chkArr[21]=='1')
			{	
				document.getElementById("grptd1"+chkVal.split("#")[0]+chkVal.split("#")[10]).style.backgroundColor="#ff9999";
				document.getElementById("grptd2"+chkVal.split("#")[0]+chkVal.split("#")[10]).style.backgroundColor="#ff9999";
				document.getElementById("grptd3"+chkVal.split("#")[0]+chkVal.split("#")[10]).style.backgroundColor="#ff9999";
			
		//labtestGroupValues
		
		var concatenateChkBoxVal="";
			 
			var cbs =document.getElementsByName('labtestGroupValues');
			
			var newcbs =document.getElementsByName('newlabtestGroupValues');
			//alert(chkVal);
			//for(var i=0; i < cbs.length; i++) {
		    	//alert(cbs[cbs.length-1].value);
		     // }
		
		    	
		    	
			var newspiltValue=newcbs[newcbs.length-1].value.split("@");
			
			var spiltValue=cbs[cbs.length-1].value.split("@");

			var map = new Object(); // or var map = {};

			for(var i=0; i < spiltValue.length; i++)
				{
				var finalGroupValue=spiltValue[i].split("#");
		        // alert(finalGroupValue[10]);
		         //alert(spiltValue[i]);
		         if(finalGroupValue[0]+finalGroupValue[10]==chkArr[0]+chkArr[10])
		        	 
		        	 {
		        	 //alert(i);
		        	// AddRowToTable(finalGroupValue,objSelectedLabTest1);
		     		//Setting labCodeArray Value
		     		var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
		     		var newlabtestcodearray=document.getElementsByName('newlabtestcodearray')[0].value;
		     		
		     		
		     		 
		     		if(labTestCodeArray!=null&&labTestCodeArray!="")
		     			{
		     			labTestCodeArray=labTestCodeArray+"@"+spiltValue[i];
		     			newlabtestcodearray=newlabtestcodearray+"@"+newspiltValue[i];
		     			}
		     			else
		     			{
		     				labTestCodeArray+=spiltValue[i];
		     				newlabtestcodearray+=newspiltValue[i];
		     			}
		     		document.getElementsByName('labTestCodeArray')[0].value=labTestCodeArray;
		     		document.getElementsByName('newlabtestcodearray')[0].value=newlabtestcodearray;
		     		
		     	//	alert("labTestCodeArray1"+labTestCodeArray);
		     		//alert("newlabtestcodearray1"+newlabtestcodearray);
		     		 AddRowToTablerates(finalGroupValue,objSelectedLabTest1,spiltValue[i],map);	
		     		
		     		//alert(document.getElementsByName('labTestCodeArray')[0].value);
		        	 }
				
				
				}
			}
				else{
					objSelectedLabTest.checked=false;
					alert("Time Slot is not open for this Lab");
				}
			}
			else{
				if(document.getElementById("grptd1"+chkVal.split("#")[0]+chkVal.split("#")[10])!=null)
				document.getElementById("grptd1"+chkVal.split("#")[0]+chkVal.split("#")[10]).style.backgroundColor="#ff9999";
				if(document.getElementById("grptd2"+chkVal.split("#")[0]+chkVal.split("#")[10])!=null)
				document.getElementById("grptd2"+chkVal.split("#")[0]+chkVal.split("#")[10]).style.backgroundColor="#ff9999";
				if(document.getElementById("grptd3"+chkVal.split("#")[0]+chkVal.split("#")[10])!=null)
				document.getElementById("grptd3"+chkVal.split("#")[0]+chkVal.split("#")[10]).style.backgroundColor="#ff9999";
				var map = new Object(); // or var map = {};

		//labtestGroupValues
		
		var concatenateChkBoxVal="";
			 
			var cbs =document.getElementsByName('labtestGroupValues');
			var newcbs =document.getElementsByName('newlabtestGroupValues');
			//alert(chkVal);
			//for(var i=0; i < cbs.length; i++) {
		    	//alert(cbs[cbs.length-1].value);
		     // }
		
		    	
		    	
			var spiltValue=cbs[cbs.length-1].value.split("@");
			var newspiltValue=newcbs[newcbs.length-1].value.split("@");
			for(var i=0; i < spiltValue.length; i++)
				{
				var finalGroupValue=spiltValue[i].split("#");
		        // alert(finalGroupValue[10]);
		        // alert(chkArr[10]);
		         if(finalGroupValue[0]+finalGroupValue[10]==chkArr[0]+chkArr[10])
		        	 
		        	 {
		        	 //alert(i);
		        	// AddRowToTable(finalGroupValue,objSelectedLabTest1);
		     		//Setting labCodeArray Value
		     		var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
		     		var newlabtestcodearray=document.getElementsByName('newlabtestcodearray')[0].value;
		     		 
		     		
		     		if(labTestCodeArray!=null&&labTestCodeArray!="")
	     			{
	     			labTestCodeArray=labTestCodeArray+"@"+spiltValue[i];
	     			newlabtestcodearray=newlabtestcodearray+"@"+newspiltValue[i];
	     			}
	     			else
	     			{
	     				labTestCodeArray+=spiltValue[i];
	     				newlabtestcodearray+=newspiltValue[i];
	     			}
		     		
		     		
		     		document.getElementsByName('labTestCodeArray')[0].value=labTestCodeArray;
		     		document.getElementsByName('newlabtestcodearray')[0].value=newlabtestcodearray;
		     	//	alert("labTestCodeArray2"+labTestCodeArray);
		     	//	alert("newlabtestcodearray2"+newlabtestcodearray);
		     		
		     		AddRowToTablerates(finalGroupValue,objSelectedLabTest1,spiltValue[i],map);
		     		
		     		//alert(document.getElementsByName('labTestCodeArray')[0].value);
		
			}
				
		}
			}}
			
		
	else
		{
		
		document.getElementById("grptd1"+chkVal.split("#")[0]+chkVal.split("#")[10]).style.backgroundColor="";
		document.getElementById("grptd2"+chkVal.split("#")[0]+chkVal.split("#")[10]).style.backgroundColor="";
		document.getElementById("grptd3"+chkVal.split("#")[0]+chkVal.split("#")[10]).style.backgroundColor="";
		
		
		 var cbs =document.getElementsByName('labtestGroupValues');
		//alert(cbs.length);
		var spiltValue=cbs[cbs.length-1].value.split("@");
		//alert(spiltValue);
		for(var i=0; i < spiltValue.length; i++)
			{
			
			var finalGroupValue=spiltValue[i].split("#");
			//alert(finalGroupValue[0]+finalGroupValue[10]+","+chkVal.split("#")[0]+chkVal.split("#")[10]);
			 if((finalGroupValue[0]+finalGroupValue[10])==(chkVal.split("#")[0]+chkVal.split("#")[10])) 
				 deleteGroupRow(1,chkVal.split("#")[0],chkVal.split("#")[10]);
			
			
			
			} 
		
		
		
		}
	
//alert(document.getElementsByName('labtestGroupValues')[0].value);
	

 
	//Adding Row with the given values
	/* if(objSelectedLabTest.checked)
		{
			AddRowToTable(chkArr);
			//Setting labCodeArray Value
			var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
			if(labTestCodeArray!=null&&labTestCodeArray!="")
				labTestCodeArray=labTestCodeArray+"@"+chkVal;
			else
				labTestCodeArray=chkVal;
			document.getElementsByName('labTestCodeArray')[0].value=labTestCodeArray;
		} */
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
	
function showInstructions6(inst)
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
	//inst.replace("/\n/gm","<br>");
	//alert("d");
	//alert(inst);
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
 //inst=inst.replace("@#","<br>");
// alert(inst);
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


function showCashCollection()
{

	
	
	//alert("ajax fire");
	 	 var status=1;
	 	var mode="GO";
	 	//alert(testCode);
	 //	alert(document.getElementsByName('cashCrNo')[0].value);
      var crno=document.getElementsByName('cashCrNo')[0].value;
	 	
	 var url1='/HBIMS/billing/transactions/CashCollectionOnlineTransCNT.cnt?hmode='+mode+'&CRNO='+crno+'';
      //var url1='/HBIMS/billing/transactions/CashCollectionOnlineTransCNT.cnt?hmode='+mode+'';
      //clickMenu(url1,'Cash Collection Desk');	
      clickMenu(url1,'Cash_Collection_Desk');
      
      // mywindow=window.open (url1);
}


/*function callMenu(url,menu)
{
		//alert('menu: '+menu+' called with url: '+ url);
		var targetURL = url;
		
		if(checkURL(url))
		{
			//alert (url.indexOf("?"));
			if(url.indexOf("?")==-1)
				targetURL+= "?varSSOTicketGrantingTicket=" + document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
			else
				targetURL+= "&varSSOTicketGrantingTicket=" + document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
			maintainLastSeenMenuArray(url,menu);	
		}
		else
		{
			targetURL = "/AHIMSG5/hissso/jsp/error/sso_error_login_appnotavail.jsp";
		}
		
		//Commented to Show the URLs in the UI Tabs,Done by Singaravelan on 24-Jul-2015
		var elemFrame = document.getElementById("frmMain"); 
		elemFrame.src=targetURL;
		
		//elemFrame.refresh();
		
		//To Show the Menus in UI Tabs,Added by Singaravelan on 24-Jul-2015		
		addTab(menu,targetURL);			
	
}*/


var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9\+\/\=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/\r\n/g,"\n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}}
function clickMenu(url,menu){
	
	
		callMenu(url,menu);
	
	
}

function callMenu(url,menu)
{
	var selMenu=menu;
	parent.ajaxStartMenu();
	menu=menu.toString().replace(/_/g,' ').replace(/XXY/g,'/').replace(/XXX/g,'&');
	//url = Base64.decode(url).toString();
	parent.callMenu(url,menu);
	$("#"+selMenu).css("color","#6D00D5");
	parent.ajaxCompleteMenu();
	
	document.getElementById("billingid").style.display="none";
}


function ShowRequistionForm(testCode,testName,labCode,labName)
{
	var tr="";
	 var getData =  checkreqformTestType(testCode);
	 var newtest=getData.split("#")[2];
	 
	var idd=testCode;
	    if(document.getElementById(idd)==null && newtest!="0")
	    	 tr = document.getElementById(newtest);
	    else
	    	 tr = document.getElementById(idd);	
//	alert(tr.testt);
	reqformtestcodes=tr.testt;
//alert(reqformtestcodes);
	    labName=tr.value;
   var td = tr.getElementsByTagName("td");
   var nameee=td[0].innerHTML;
//alert(nameee);
   testName=nameee;
   //alert(testName);

	//alert("ShowRequistionFormreqformtestnames"+reqformtestnames);
	//alert("ShowRequistionFormreqformtestcodes"+reqformtestcodes);
	
	reqformtestnames=reqformtestnames.replace(/#/g,";");
	reqformtestnames=reqformtestnames.replace(/@/g,"!");
	
	//reqformtestcodes=reqformtestcodes.replace(/#/g,";");
	//reqformtestcodes=reqformtestcodes.replace(/@/g,"!");
	
	var hmode="EXISTINGREQUISITIONFORM";
	//alert(testCode);
	
	var url1="/HISInvestigationG5/new_investigation/requisitionformprocess.cnt";
	
	
	mywindow=window.open (url1+"?testCode="+testCode+"&testName="+testName+"&labCode="+labCode+"&labName="+labName+"&hmode="+hmode+"&reqformtestnames="+reqformtestnames+"&reqformtestcodes="+reqformtestcodes,"_blank","scrollbars=1,directories=no, status=no,width=700, height=500,top=200,left=500");
}

$(function() {
	$( "#datepicker" ).datepicker({
	dateFormat: 'dd-M-yy',
	showOn: "button",
	buttonImage: "/HISInvestigationG5/hisglobal/images/cal.png",
	buttonImageOnly: true,
	buttonText: "Select  "
	}).datepicker("setDate", new Date());
	});


function AddRowToTable(chkArr,labarray1,chkVal)
{
	
	
	//alert("ccaddrow"+chkArr);
	//alert("labarray1"+labarray1);
	var testcode="1";
	//var testnameee=chkarray[3];
	var testcode1=chkArr[2];
	var labcode1=chkArr[0];
	var grpcode=chkArr[10];

	
	  var testco="";
	  var testna="";
	  var  testr = "";
	  var  grptyp =grpcode;
	  var colorcodee="";
	if( grptyp!=null && grptyp!="null" && grptyp!="0")
	  {
		testco=grpcode;
	   testr = grptestratess[grpcode];
	
	  }
     else
	  {
	  
	//   alert("not grp"+testco);
    		testco=testcode1;
    		
    		if(testratess[testcode1]!=undefined)
    			{
	   testr = testratess[testcode1];
    			}
    		else
    			{
    			 testr ="0@#"+testcode1+"@#"+chkArr[3];
    			}
    		
	  }
	
     // alert("testr"+testr);
	  var testrnew="";
	  testrnew=testr;
	  
	  if(testr.includes("^"))
	  {
	//	  alert("true");
		  testr=testr.split("^")[0];
		  colorcodee=testrnew.split("@#")[2];
	  }
	  else{
		  testr=testr.split("@#")[0];
		  colorcodee=testrnew.split("@#")[2];
		//  alert("false");
	  }
	  
	  var map = new Object(); // or var map = {};
	  
	  
	  if (map[testco]==undefined)
		 {
		 
		  if(testrnew.includes("@#"))
		  {
			  //testname+=testrnew.split("@#")[1]+"#@";
		  }
		  else
			  {
			  
			  }
		  
	 var testrate=testr+"#@";
	  //testname+=testna+"#@";
	  map[testco] = testr;
	  
		 }
	 else
		 {
		 
		 }
	  
	
	
	var ispidshow=chkArr[27];
	var islababsedapp=chkArr[28];
	
	//alert("islababsedapp"+islababsedapp);
	var data=document.getElementsByName('piddata')[0].value;
	//  alert("data"+data);
	if(data!=undefined && data!="")
		{
		
		if(ispidshow=="1")
		{
		data+=labcode1+"@@"+testcode1+"@@"+ispidshow+"##";
		document.getElementsByName('piddata')[0].value=data;
		document.getElementById('myButton').style.display="";

		}
		
	      	
		}
	else
		{
		
		if(ispidshow=="1")
		{
		data=labcode1+"@@"+testcode1+"@@"+ispidshow+"##";
		document.getElementsByName('piddata')[0].value=data;
		document.getElementById('myButton').style.display="";

		}
		
		
		}
       
	 //alert("document.getElementsByName('piddata')[0].value"+document.getElementsByName('piddata')[0].value);
	
	
	
	 
	var mainn=labcode1+testcode1;
	var issufficientamount = issufficientamountt(testcode,"0");
	var isduplicatetestraisedtoday = isduplicatetestrasied(chkArr);
    var flag="0";
   // alert("isduplicatetestraisedtoday"+isduplicatetestraisedtoday);
	if(grpcode=="0" && isduplicatetestraisedtoday=="1")
		{
	if (confirm('This Test has already been rasied Today.Do you want to Continue')) {
		flag="0";
	    // Save it!
	} else {
		document.getElementById(mainn).checked=false;
		//document.getElementById("td1"+chkVal).style.backgroundColor="";
		//document.getElementById("td2"+chkVal).style.backgroundColor="";
		//document.getElementById("td3"+chkVal).style.backgroundColor="";
		
	//to delete the test on de selecting the corresponding check box	
	////	alert(chkArr[0]);
	//	alert(chkArr[2]);
	//	deleteRow(1,chkArr[0],chkArr[2]);
		flag="1";
		
		if(document.getElementById("td1"+chkVal)!=null)
		document.getElementById("td1"+chkVal).style.backgroundColor="";
		if(document.getElementById("td2"+chkVal)!=null)
		document.getElementById("td2"+chkVal).style.backgroundColor="";
	    // Do nothing!
	}
		}
	
  	if(issufficientamount=="1")
  	{
  		
  		alert("Patient Account Balance Going TO Be Insufficient. Please Deposit Part Payment!!");
  		return null;
  	}
  	
  	if(flag=="1")
  	{
  		return null;
  	}
  	
	//alert(document.getElementById('tableSelectedLabTestIdHide'))
	if(document.getElementById('tableSelectedLabTestId')!=null)
		{
		
	document.getElementById('tableSelectedLabTestId').style.display="";
		}
	// Logic to regain the Lab Test Values
	
	//alert(chkArr);
  	 
	// Lab Code
	//alert(chkArr);
	var labCode=chkArr[0];
	// Lab Name
	var labName=chkArr[1];
	// Test Code
	var testCode=chkArr[2];
	// Test Name
	var testName=chkArr[3];
	// Sample Combo String
	var sampleComboStr=chkArr[4];
   //alert(sampleComboStr);
	// Test Type
	var testType=chkArr[5];
	// is Appointment
	var isAppointment=chkArr[6];
	 
	// is Confidential
	var isConfidential=chkArr[7];
	// Sample Code
	var sampleCode=chkArr[8];
	// Priority
	var priority=chkArr[9];
	// Test GroupCode
	var testGroupCode=chkArr[10];
	// Test Group Type
	var testGroupType=chkArr[11];
	
	//Is Mandatory Req 
	var isMandatoryReq=chkArr[12];
	
var mandTextBoxCombo=chkArr[15];
var mandTextBoxComboName=chkArr[16];
		var finalMadCode=chkArr[17];

		var testGrpCode=chkArr[18];
		var offlineAptNo=chkArr[19];
		var instructionss=chkArr[20];
		var isreqformneeded=chkArr[24];
		
		var opdipdraise=chkArr[26];
		var callingdesk=document.getElementsByName('patAdmNo')[0].value;
		//alert("callingdesk"+callingdesk);
		if(callingdesk=="-1")
			{
			//alert("callingdesk"+":normal");

			
			}
		
		if(callingdesk=="")
			{
			//alert("callingdesk"+":opd");

			var opdteststatus=opdipdraise.split("$")[0];
		//alert(opdteststatus);
			
			if(opdteststatus=="0")
				{
			isMandatoryReq="0";
			isAppointment="0";
				}
			
				}
		
		 if(callingdesk!="" && callingdesk!="-1")
		{
				//alert("callingdesk"+":ipd");

				
		var ipdteststatus=opdipdraise.split("$")[2];
	//	alert(ipdteststatus);

		if(ipdteststatus=="0")
			{
		isMandatoryReq="0";
		isAppointment="0";
			}
		
			}
		
		//alert(document.getElementsByName('patAdmNo')[0].value);
		
	//	alert("opdteststatus"+opdteststatus+"ipdteststatus"+ipdteststatus);
        /*if(instructionss=='undefined')
        	instructionss = null;
        else*/
        	instructionss=instructionss.split('\n').join("<br>");
		//alert(instructionss);
		document.getElementsByName('mandComboTextBoxComboNamesOnPage')[0].value=mandTextBoxComboName;
		document.getElementsByName('finalMandCode')[0].value=finalMadCode;
	
	var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
	var patCrNo=document.getElementsByName('patCrNo')[0].value;
	var labCodeSaved=document.getElementsByName("labCode");
	var testCodeSaved=document.getElementsByName("testCode");
	var divAptTagRow="aptTagRow_"+labCode+"_"+testCode;  //+labCode+testCode
	//alert(divAptTagRow);
	var size=0;
	if(typeof labCodeSaved!='undefined' && labCodeSaved!=null)
		size=labCodeSaved.length;
	if(size>0)
	{
		for(var i=0;i<size;i++)
			{
				if(labCodeSaved[i].value==labCode)
					{
						if(testCodeSaved[i].value==testCode)
						{
							/*var nRow21=0;
							var tableObj21=document.getElementById('tableSelectedLabTestIdHide');
							
							var numRows21=tableObj21.rows.length;
							nRow21=numRows21;
							
							var idd21="sampleaddtbl#"+nRow21;
							var val21=document.getElementById(idd21);
							setSampleCodeUsingAjax(val21,labCode,testCode);
*/
							alert("Laboratory :'"+labName+"' with corresponding Test :'"+testName+"' Already added. Please Select another Lab/Test.");
							var cbs =document.getElementsByName('chkLabTest');
							 //alert(cbs);
							for(var i=0; i < cbs.length; i++) {
								if(cbs[i].checked)
						    	{
								var id=cbs[i].id;
								//alert(id);
								if(id==labCode+testCode)
									{
								      document.getElementById(id).checked = false;
						    	     }
							     }
						      }
							return false;
						
						}
					}
			}
		
	}
	var nRow=0;
	var tableObj=document.getElementById('tableSelectedLabTestIdHide');
	
	var numRows=tableObj.rows.length;
	nRow=numRows;
	var tabRow=tableObj.insertRow(numRows);
	tabRow.id=parseInt(nRow);

	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
	var td3=document.createElement("TD");
	var td5=document.createElement("TD");
	var td6=document.createElement("TD");
	var td7=document.createElement("TD");
	var td8=document.createElement("TD");
	//alert(isreqformneeded);
	if(isreqformneeded!=0)
		td1.innerHTML="<div align='left' id='checkOnSave'>"+testName+"<input id='labtbl#"+nRow+"' type='hidden' name='labCode' value='"+labCode+"'/></div>";
		//td1.innerHTML="<div align='left' id='checkOnSave'>"+testName+"<input id='labtbl#"+nRow+"' type='hidden' name='labCode' value='"+labCode+"'/>&nbsp&nbsp<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+")'></div>";   
	else
		td1.innerHTML="<div align='left' id='checkOnSave'>"+testName+"<input id='labtbl#"+nRow+"' type='hidden' name='labCode' value='"+labCode+"'/></div>";	
document.getElementsByName('isrequisitionFormNeeded')[0].value=isreqformneeded;
		td1.className='tdfonthead';
	td1.colspan="1";
	
		
	td2.innerHTML="<div align='left'>"+labName+"<input type='hidden' name='testCode' value='"+testCode+"'/></div>";
	td2.className='tdfonthead';
	td2.colspan="1";
	
	//alert(sampleComboStr);
	//alert("testType="+testType);
	if(testType=="P") // Sample or Slide Based
		td3.innerHTML="<div align='left'> <select style='width:55;'  id='site#"+nRow+"' name='site' onchange='setsiteUsingAjax(this,"+(labCode)+","+(testCode)+")'><option value='1'>NA</option><option value='2'>Both</option><option value='3'>Left</option><option value='4'>Right</option></select></div>";
	else
		td3.innerHTML="<div align='left'><select id='sampleaddtbl#"+nRow+"'  name='sampleInfo' tabindex='1' onchange='setSampleCodeUsingAjax(this,"+(labCode)+","+(testCode)+")'  >"+sampleComboStr+"</select></div>";
		
	td3.className='tdfonthead';																													
	td3.colspan="1";


		
	if(isMandatoryReq=="1")
	{ 
	
	var splitComboTextBoxValue=mandTextBoxCombo.split('&');
	var comboval=0;
	var popup="popUpDiv";
	/*for(comboval;comboval<splitComboTextBoxValue.length;comboval++)
		{
		if(comboval>0){ 
			
			AddRowToTableAddMoreValue(splitComboTextBoxValue[comboval],mandTextBoxComboName,finalMadCode);
		}
		else
			{ 
		//alert(splitComboTextBoxValue[comboval]);
td5.innerHTML=td5.innerHTML+"<div align='left'>"+splitComboTextBoxValue[comboval]+"</div>";
		}
		}*/
	/*if(comboval>0)
	td5.innerHTML=td5.innerHTML+" <img src='/HISInvestigationG5/hisglobal/images/addMore.jpg' id='minusButton' onclick='popup(\""+popup+"\")''> ";
	*/
	if(testr!="0")
	{td5.innerHTML="<div align='left' style='height:18 ;border-left : 4px solid "+colorcodee+" '>&nbsp;&#8377;&nbsp;"+testr+"</div>";
	}
	else
		{
		td5.innerHTML="<div align='left' style='height:18 ;border-left : 4px solid "+colorcodee+" '>&nbsp;&nbsp;Free</div>";
		}
	}
else
	{ 
/*	td5.innerHTML="<div align='left'>NA</div>";*/
	if(testr!="0")
	{td5.innerHTML="<div align='left' style='height:18 ;border-left : 4px solid "+colorcodee+" '>&nbsp;&#8377;&nbsp;"+testr+"</div>";
	}
	else
		{
		td5.innerHTML="<div align='left' style='height:18 ;border-left : 4px solid "+colorcodee+" '>&nbsp;&nbsp;Free</div>";
		}
	}
	td5.className='tdfonthead';
td5.colspan="1";

          var labbasedapp=document.getElementsByName('labbasedapppointment')[0].value;
           var labbasedaptdatetime="";
           var labbasedaptdatetimelab="";
           //alert(labbasedapp);
          if(labbasedapp!="")
        	  {
        	  labbasedaptdatetime=labbasedapp.split("#")[2];
        	  labbasedaptdatetimelab=labbasedapp.split("#")[4];
        //	  labbasedaptdatetimelab="10066";

        	  }
          
         // alert("chanks appointment call first time");
	var paraId=labCode+"^"+testCode+"^0^0^0^0^0";
	if(labbasedaptdatetime!="" && labbasedaptdatetimelab==labCode)
		{
		td6.innerHTML="<div align='left'>"+labbasedaptdatetime+"</div>";
		
		}
	else
		if(islababsedapp=="1")
		{ 
		//	alert("call from labbaed app"+document.getElementsByName('islabcallapp')[0].value);
		var islabalreadycallapp=document.getElementsByName('islabcallapp')[0].value;
                 
		if(islabalreadycallapp.includes(labCode))
			{
			//alert("labmatch");
			var ide=islabalreadycallapp.split("@");
			
			for(var a=0;a<ide.length;a++)
				{
				//alert("labmatchloop");
				var ff=ide[a];
				
				if(ff.includes(labCode))
					{
					//alert("match again");
					var makeid=ff.split("#")[1];
					//alert("makeid"+makeid);
					var newmakeid="freeSlotLabel"+"_"+makeid;
				//	alert("newmakeid"+newmakeid);
					var htmll=document.getElementById(newmakeid).innerHTML;
					document.getElementById(newmakeid).innerHTML=htmll;
			       //  alert("htmll"+htmll);
			       //  alert("makeid"+makeid);
			         var dateid="aptForDate_"+makeid ;
			      //  alert("zz"+document.getElementsByName(dateid)[0].value);
					var dateidvalue=	document.getElementsByName(dateid)[0].value;

			         var newdateid="aptForDate_"+divAptTagRow ;

						td6.innerHTML="<div name='"+makeid+"' id='"+divAptTagRow+"' align='left'>"+htmll+"</div><input type='hidden' name='"+newdateid+"' value='"+dateidvalue+"'/> ";

					}
				
				}
			
			
			
			}
		else
			{
			
			if(islabalreadycallapp=="")
			{
				islabalreadycallapp=labCode+"#"+divAptTagRow+"@";
			}
			else
				{
				
				islabalreadycallapp=islabalreadycallapp+labCode+"#"+divAptTagRow+"@";
				
				}
			
			paraId=labCode+"^"+'0'+"^0^0^0^0^0";

			document.getElementsByName('islabcallapp')[0].value=islabalreadycallapp;
		td6.innerHTML="<div id='"+divAptTagRow+"'></div> <div  align='left'><input type='hidden' name='dateTag' onchange='setDateInApoitment("+patCrNo+",\""+(paraId)+"\",this,\""+divAptTagRow+"\")' id='datepicker'></div>";
		$( "#datepicker" ).datepicker({
			dateFormat: 'dd-M-yy',
			showOn: "button",
			buttonImage: "/HISInvestigationG5/hisglobal/images/cal.png",
			buttonImageOnly: true,
			buttonText: "Select  "
			}).datepicker("setDate", new Date());
					//alert(td6.innerHTML);
		var paraId="";
	//	getAptSlotDetails('"+patCrNo+"','"+paraId+"','',divAptTagRow);
		
		paraId=labCode+"^"+'0'+"^0^0^0^0^0";
        //alert("lab bsed call paraIdparaId"+paraId);
			getAptSlotDetails("\'"+patCrNo+"\'",paraId,'',divAptTagRow,4);
			
			newarrayappdatalababsed.push(labCode+"$$"+divAptTagRow);
			}
		}
		else if(isAppointment=="1")
		{ 
		
		td6.innerHTML="<div id='"+divAptTagRow+"'></div> <div  align='left'><input type='hidden' name='dateTag' onchange='setDateInApoitment("+patCrNo+",\""+(paraId)+"\",this,\""+divAptTagRow+"\")' id='datepicker'></div>";
		$( "#datepicker" ).datepicker({
			dateFormat: 'dd-M-yy',
			showOn: "button",
			buttonImage: "/HISInvestigationG5/hisglobal/images/cal.png",
			buttonImageOnly: true,
			buttonText: "Select  "
			}).datepicker("setDate", new Date());
					//alert(td6.innerHTML);
		var paraId="";
	//	getAptSlotDetails('"+patCrNo+"','"+paraId+"','',divAptTagRow);
		
		
			 paraId=labCode+"^"+testCode+"^0^0^0^0^0";

			getAptSlotDetails("\'"+patCrNo+"\'",paraId,'',divAptTagRow,2);

			
				
		}
		else
		{
		td6.innerHTML="<div align='left'>NA</div>";	
		td6.className='tdfonthead';
		td6.colspan="1";
		}
	
		var checkflag=document.getElementById('flag').value;
		
		
		//td7.innerHTML="<div  align='left' ><select  id='prioritytbl#"+nRow+"' name='priority'    tabindex='1' onchange='setPriorityUsingAjax(this,"+(labCode)+","+(testCode)+")'  ><option value='<%=InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_NORMAL%>'>Normal</option><option value='<%=InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_URGENT%>'>Urgent</option></select></div>";
		td7.innerHTML="<div  align='left' ><select  id='prioritytbl#"+nRow+"' name='priority'    tabindex='1' onchange='setPriorityUsingAjax(this,"+(labCode)+","+(testCode)+")'  ><option value='1'>Normal</option><option value='2'>Urgent</option></select></div>";
	td7.className='tdfonthead';
	td7.colspan="1";
	
	//var ind=document.getElementById((parseInt(nRow)+1));
	//alert("ind="+ind)
   
   // td8.className='tdfonthead';
   // td8.colspan="1";
   // td8.innerHTML="<div align='left'><img src='/BLDHISInvestigationG5/hisglobal/images/imgDatepicker.png' onclick ='showIndication(event,parseInt(\""+ind.rowIndex+"\"))'></div>";
	//alert(instructionss);
	if(instructionss=='null')
	{
		td8.className='tdfonthead';
		td8.colspan="2";
		
		td8.innerHTML="<div align='left'><img src='/HISInvestigationG5/hisglobal/images/minus.gif' id='minusButton' onClick='deleteRow("+(parseInt(nRow)-1)+","+(labCode)+","+(testCode)+","+(testGroupCode)+")'></div>";


			
	
	}
	else
		{
	td8.className='tdfonthead';
	td8.colspan="2";

	//td8.innerHTML="<div align='left'><img src='/HISInvestigationG5/hisglobal/images/minus.gif' id='minusButton' onClick='deleteRow("+(parseInt(nRow)-1)+","+(labCode)+","+(testCode)+")'><img  title='Show Instructions' src='/HISInvestigationG5/hisglobal/images/add_remarks_sml.jpg' onClick='showInstructions6("+"\""+(instructionss)+"\""+")'></div>";
	
	td8.innerHTML="<div align='left'><img src='/HISInvestigationG5/hisglobal/images/minus.gif' id='minusButton' onClick='deleteRow("+(parseInt(nRow)-1)+","+(labCode)+","+(testCode)+","+(testGroupCode)+")'><img  title='Show Instructions' src='/HISInvestigationG5/hisglobal/images/add_remarks_sml.jpg' onClick='showInstructions6("+"\""+(instructionss)+"\""+")'></div>";
	
		
		}
	
    
	
   
	tabRow.appendChild(td1);  
	tabRow.appendChild(td2);
	tabRow.appendChild(td3);
	tabRow.appendChild(td5);
	tabRow.appendChild(td6);
	tabRow.appendChild(td7);
	tabRow.appendChild(td8);
	showamount();
	

	if(checkflag!="false")
			{
			//alert(checkflag);
			var valuetable="prioritytbl#"+nRow;
			//alert(valuetable);
			document.getElementById(valuetable).value=checkflag;
			}
	
	
	 document.forms[0].numberOfRow.value=numRows;
	 $( "#datepicker" ).datepicker({
		 dateFormat: 'dd-M-yy',
		 showOn: "button",
		 buttonImage: "/HISInvestigationG5/hisglobal/images/cal.png",
		 buttonImageOnly: true,
		 buttonText: "Select  "
		 }).datepicker("setDate", new Date());
		 	 //Calendar.setup({'inputField':'requirmentDate'+(parseInt(indexVolSpecific)+1) ,'ifFormat':'%d-%b-%Y','button':(parseInt(indexVolSpecific)+1)+'requirmentDate','singleClick':true});
var idd="sampleaddtbl#"+nRow;
var val=document.getElementById(idd);
//reqformshowone();
reqformhideone();
setSampleCodeUsingAjax(val,labCode,testCode);
 	
callopenmodal(ispidshow);

}



function callPrint()
{
window.print();
}
function submitFormForTestGroup(hmode)
{
	document.getElementsByName('hmode')[0].value=hmode;
	document.forms[0].submit();
}

function showBookMarkDiv(obj)
{
	//var bookMarkedDivClicked=document.getElementsByName('radioBookMark')[0].checked;
	//var bookMarkedDivClicked=document.getElementsByName('radioBookMark')[1].checked;
	
	if(obj.value=="B")
		{
		document.getElementById('bookMarkDiv').style.display="";
		document.getElementById('searchLabTestDiv').style.display="none";
		}
	else
		{
		document.getElementById('bookMarkDiv').style.display="none";
		document.getElementById('searchLabTestDiv').style.display="";
		}
	
	document.getElementsByName('isBookMark')[0].value=obj.value;
	document.getElementsByName('hmode')[0].value="CLEARLABTESTLIST";
	document.forms[0].submit();
	
	
}
function validateCRNoCHE(hospitalCode)
{
	 var crno =document.getElementsByName("patCrNo")[0].value;
     var textLength = crno.length;
     var hosCodeLen=hospitalCode.length;
     document.getElementsByName('casualitydesk')[0].value="1";
     
     if(hosCodeLen==3)
		{ 
        	  if(textLength==13)
	           {
         	document.getElementsByName('hmode')[0].value='GETPATDTL';
         	document.forms[0].submit();
	            }
        	   
             

	          else
	          {     
	            alert("InValid CR No");
	            if(document.getElementsByName("patCrNo")[0])
	            {
	            document.getElementsByName("patCrNo")[0].focus()
	             }    
	           
	          }
       }
      if(hosCodeLen==5)
    	{ 
			   
			    	
			    	if(textLength==15)
						 {
						   
						document.getElementsByName('hmode')[0].value='GETPATDTL';
						document.forms[0].submit();
					     }
					
			     else
			     {     
				   		    alert("InValid CR No");
				     		if(document.getElementsByName("patCrNo")[0]){
				            document.getElementsByName("patCrNo")[0].focus()
				                      }    
			          
			     }
          }
      
  
 
   return true;
}



//AJAX FUNCTIONS START

//AJax Functions
function deleteLabTestUsingAjax(labCode,TestCode,labTestCodeArray)
{
	//alert("labTestCodeArray="+labTestCodeArray);
	labTestCodeArray=labTestCodeArray.replace(/#/g,";"); // As hash separator doesnot work in URL

	if(labTestCodeArray.contains("&"))
		labTestCodeArray=labTestCodeArray.replace(/&/g,"*"); // As & separator doesnot work in URL

	var finalLabTestCodeArray = deleteLabTestCodeArray(labCode,TestCode,labTestCodeArray);
	return finalLabTestCodeArray;
}

function deleteLabTestCodeArray(tmpLabCode,tmpTestCode,tmpLabTestCodeArray)
{
	var flg = false;
	var labTestCodeArray = "";
	var _mode = "AJX_DELETE_LABTESTCODEARRAY";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&tmpLabCode="+tmpLabCode+"&tmpTestCode="+tmpTestCode+"&labTestCodeArray="+tmpLabTestCodeArray, sync:true, postData: "", handleAs: "text",
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
}



function deleteRowPrvReqDtlresultentry(labCode,testCode,reqNo,index,testgrpcode)

{
	// alert(reqNo);
	  var retVal = "";
	  
	  if(testgrpcode=="0")
		  {
		  retVal= confirm("Are You Sure to Delete it?");
		  }
	  else
		  {
		  retVal= confirm("Are You Sure to Delete Group ?");
		  }
	  
    if( retVal == true )
        {

   var data= updateReqTableresultentry(labCode,testCode,reqNo);
  // alert(data);
   document.getElementsByName("deletedtestdataviaresultentry")[0].value=data; 
    var fl=index-1;
    	var iddd="#"+index;
    //document.getElementById("resultentrydetails").deleteRow(fl);
    $(iddd).remove();
   // alert(document.getElementsByName("deletedtestdataviaresultentry")[0].value);
    	 var x = document.getElementById("resultentrydetails").rows.length;
    	 
    	 if(x=='0')
    		 {
    		 document.getElementById("showentryy1").style.display="none";
    		 document.getElementById("showentryy2").style.display="none";
    		 }
    	 
 //     	  showPrvDetail(); 
        /* 
  	  else
      	  alert("The Requisition can be cancelled only if billing has been done."); */
      	
}
    else{
   	  //alert("User does not want to continue!....");
       return false;
    }
	  
}


function updateReqTableresultentry(labCode,testCode,reqNo)
{
	//  alert("inside del");
	//alert(reqNo);
	/* document.getElementsByName("requisitionNo")[0].value=reqNo; */
	//document.getElementsByName('requisitionNo')[0].value=reqNo;
	
	var data=document.getElementsByName("deletedtestdataviaresultentry")[0].value
	//alert(data);
	var flg = false;
	var isRequisitonRaisingPresent = false;
	var _mode = "DELETEREQDTLRESULTENTRY";  
	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&delLabCode="+labCode+"&delTestCode="+testCode+"&requisitionNo="+reqNo+"&deletedtestdataviaresultentry="+data, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			isRequisitonRaisingPresent = data;
			flg = true;
		},
        error: function(error)
        {
            //if(typeof objKitchenList == 'undefined' || objKitchenList==null || objKitchenList=="" || objKitchenList.length==0)
            	//alert("No Kitchen Found");
            //alert(error+"Error while populating Meal Time Information");
            isRequisitonRaisingPresent = false;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return isRequisitonRaisingPresent;
}


function showentry()
{
	 document.getElementById("showentryy1").style.display="";
	 document.getElementById("showentryy2").style.display="";

}

function hidentry()
{
	 document.getElementById("showentryy1").style.display="none";
	 document.getElementById("showentryy2").style.display="none";
	
//	document.getElementById("showentryy1").style.display="none";
	//document.getElementById("showentryy2").style.display="none";
}



function reqformhideone()
{
	document.getElementById("reqformss").style.display="none";
}

function showReqForms()
{
	document.getElementById("reqformss").style.display="";
	reqformshowone();
}
var reqformtestcodes="";
var reqformtestnames="";




function deleteRow(Str)
	{	
	
//	alert("inside delete");
	  var numRows=parseInt(document.getElementsByName('numberOfRow')[0].value);
      var tableObj=document.getElementById('tableSelectedLabTestIdHide');
  //   alert(tableObj);
      var temp=Str;
      //sendData(temp.rowIndex);  // Need To Check the Logic
      
       
      tableObj.deleteRow(temp.rowIndex);
      document.forms[0].numberOfRow.value=(numRows-1);
      
     // makeMandatory();
     
     
      return true;
	}


function deleteGroupRow(index,tmpLabCode,tmpTestCode)
{	
  
	//alert("index"+index+"labCode"+tmpLabCode+"testcode"+tmpTestCode);
	var tableObj=document.getElementById('tableSelectedLabTestIdHide');
   //alert("tableObj"+tableObj);
   //alert("parseInt"+(parseInt(index)+1));
	//tableObj.deleteRow((parseInt(index)));
	//alert("Tabkle no of rows"+tableObj.rows.length+"---indx-"+index);
	//tableObj.deleteRow(((index)));
  document.getElementsByName('numberOfRow')[0].value=tableObj.rows.length-1;
  
  //submitting page with hmode as "DELETEROW"
  	document.getElementsByName('hmode')[0].value="DELETEGROUPROW";
 	document.getElementsByName('tmpLabCode')[0].value=tmpLabCode;
  	document.getElementsByName('tmpTestCode')[0].value=tmpTestCode;
  
  	document.forms[0].submit();
  
  
 
  return true;
}


function checkreqformTestType(TestCode)
{
	
	//alert("setAptNoUsingAjax"+appoitmentNo+"labcode"+tmpLabCode+"testCode"+tmpTestCode);
	
	var flg = false;
	var remarks = "";
	var _mode = "AJX_CHECK_REQFORM_TESTTYPE";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&testCodee="+TestCode, sync:true, postData: "", handleAs: "text",
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
	return remarks;
}



function checkTestAvailabilityAjax(userTestCode)
{
	
	//alert("setAptNoUsingAjax"+appoitmentNo+"labcode"+tmpLabCode+"testCode"+tmpTestCode);
	
	var flg = false;
	var remarks = "";
	var _mode = "AJX_CHECK_AVAILABILITY_TEST_CODE";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&userTestCode="+userTestCode, sync:true, postData: "", handleAs: "text",
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
	return remarks;
}



function searchLabWiseTest23()
{
  // alert("inside");   
   // alert(document.getElementsByName('searchTestGroupName')[0].value);
   // if(document.getElementsByName('searchTestGroupName')[0].value=='')
    //	{
    	//alert("SEARCHLABWISETEST");
    document.getElementsByName('hmode')[0].value="SEARCHTESTKEYWORDNEWLABWISE";
    //	}
   // else
    //	{
    	//alert("SEARCHGROUP");
    	//document.getElementsByName('hmode')[0].value="SEARCHGROUP";
    //	}
    // document.getElementById("showhideOnPagination").style.display="";
    // document.getElementById("hideOnPagination").style.display="";
   //  document.getElementById("showOnPagination").style.display="none";	
 
	
	document.forms[0].submit();
} 



function issufficientamountt(testCode,checkfromwhichcall)
{
	
	//alert("setAptNoUsingAjax"+appoitmentNo+"labcode"+tmpLabCode+"testCode"+tmpTestCode);
	var val=document.getElementsByName('newlabtestcodearray')[0].value;
	val=val.replace(/#/g,"^");
	//alert("issufficientamountt"+val);
	//return null;
	var flg = false;
	var remarks = "";
	var _mode = "ISSUFFICIENTBALANCE";
	var userTestCode="1";
	
	 var patcrno=document.getElementsByName('patCrNo')[0].value
	 var objXHR="";
	 if(checkfromwhichcall=="0")
		 
		 
		 {	
		 
		 objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&patCrNo="+patcrno+"&testCodee="+testCode+"&isamountsufficientflag="+val, sync:true, postData: "", handleAs: "text",
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

		 }

	 if(checkfromwhichcall=="1")

		 {
		 objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&patCrNo="+patcrno+"&userTestCode="+testCode+"&isamountsufficientflag="+checkfromwhichcall, sync:true, postData: "", handleAs: "text",
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
		 }
	var objDojoAjax = dojo.xhrPost(objXHR);
	return remarks;
}




function isduplicatetestrasied(chkarray)
{
	var testcode=chkarray[2];
		var labcode=chkarray[0];
		var grpcode=chkarray[10];
	//alert("isduplicatetestrasied"+"labcode"+labcode+"testCode"+testcode+"grpcode"+grpcode);
	 var patcrno=document.getElementsByName('patCrNo')[0].value

	var _mode = "ISDUPLICATETESTRAISEDTODAY";
	
		 
		 objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&patCrNo="+patcrno+"&testCodee="+testcode+"&labCodee="+labcode+"&groupraisedalready="+grpcode, sync:true, postData: "", handleAs: "text",
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
	return remarks;
}



function isduplicatetestrasied(chkarray)
{
	var testcode=chkarray[2];
		var labcode=chkarray[0];
		var grpcode=chkarray[10];
	//alert("isduplicatetestrasied"+"labcode"+labcode+"testCode"+testcode+"grpcode"+grpcode);
	 var patcrno=document.getElementsByName('patCrNo')[0].value

	var _mode = "ISDUPLICATETESTRAISEDTODAY";
	
		 
		 objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&patCrNo="+patcrno+"&testCodee="+testcode+"&labCodee="+labcode+"&groupraisedalready="+grpcode, sync:true, postData: "", handleAs: "text",
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
	return remarks;
}




function isduplicatetestrasiedgroupeise (usergrpcodee)
{
	var testcode="1";
		var labcode="2";
		var grpcode="3";
		var usergrpcode=usergrpcodee;
	//alert("isduplicatetestrasiedgroupwise"+"labcode"+labcode+"testCode"+testcode+"grpcode"+grpcode);
	 var patcrno=document.getElementsByName('patCrNo')[0].value

	var _mode = "ISDUPLICATETESTRAISEDTODAY";
	
		 
		 objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&patCrNo="+patcrno+"&testCodee="+testcode+"&labCodee="+labcode+"&groupraisedalready="+grpcode+"&usergrpcode="+usergrpcodee, sync:true, postData: "", handleAs: "text",
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
	return remarks;
}



function savepidd()
{   

   //alert("savepi");
   var pid=document.getElementById("pidenter").value; 
  
   
   var clientinit=document.getElementById("yesno0").checked; 
   var count="";
   var providerinit=document.getElementById("yesno1").checked; 
   var iswomenpreg="0";
   var followupvar="0";
   if(document.getElementById("followupdiv")){
	   //alert("followup div exist");
	   if(document.getElementById("followupchecky").checked){
		   followupvar="1"; document.getElementsByName('followup')[0].value="1";
	   }
	   else if (document.getElementById("followupcheckn").checked){
		   followupvar="0"; document.getElementsByName('followup')[0].value="0";
	   }
//alert(document.getElementsByName('followup')[0].value+"hidden followup"); 
   }
   
   if(document.getElementById("iswomenpregnantpid")!=null)
    iswomenpreg=document.getElementById("iswomenpregnantpid").value;
   
   //alert(providerinit);
if(pid!='')
	{

	if(clientinit==false &&  providerinit==false)
		{
            alert("Please Choose Initiated type");
            return null;
		}
	else
		{
		      if(clientinit==true &&  providerinit==false)
                    {
                       count="0";
                    }
                   else if(providerinit==true &&  clientinit==false)
                       {
                	   count="1";
                       }
                  
                  


              if(pid!="" && count!="")
                  {
            	  var lenn=document.getElementById("pidenter").value
            	  lenn=lenn.length;
            	  if(lenn>27)
            		  {
            	  document.getElementsByName('piddmodalopen')[0].value="1"
            	  document.getElementsByName('pidd')[0].value=pid+"@@@"+count+"@@@"+iswomenpreg;
            	     //  alert(document.getElementsByName('pidd')[0].value);
            		  }
            	  else
            		  {
            		  alert("Please Enter Valid PID.");
            		  return null;
            		  }
            	    //alert("finalvalue"+document.getElementsByName('pidd')[0].value);
                  }
                   
		}
	       // alert("counte1r"+count);
	//document.getElementById("pidenter").value="";
	        $('#dialog').dialog('close');
	        
	}
else
	{
	   alert("Please Enter PID");
	   return null;
		

	}
   
}

$(function() {

	  $("#dialog").dialog({
	     autoOpen: false,
	     modal: true,
	     close: function(){
	    	if (  document.getElementsByName('piddmodalopen')[0]!=null &&  !document.getElementsByName('piddmodalopen')[0].value=="1")
	           if (confirm('Do you want to close Form without save ?')) {
				
				    // Save it!
				} else {
					  $("#dialog").dialog("open");	
				}
	    	
	     },
	     width: 990,
	        height: 250,
	   });

	  $("#myButtonip").on("click", function(e) {
	      e.preventDefault();
	      setpiddatainmodal();
	    //  $("#dialog").dialog("open");
	      
	  });

	});



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



 
  function AddRowToTablePrv(chkVal)
  {

	 // alert(chkVal);
  	// Logic to regain the Lab Test Values
  	// Lab Code
  	//alert("inside here"+chkArr);
  	var chkArr=chkVal.split("#");
  	var status=chkArr[0];
  	// Lab Name
  	var labName=chkArr[1];
  	// Test Code
  	var sampleName=chkArr[2];
  	// Test Name
  	var testName=chkArr[3];
  	// Sample Combo String
  	var sampleComboStr=chkArr[4];
  	// Test Type
  	var testType=chkArr[5];
  	// is Appointment
  	var isAppointment=chkArr[6];
  	 
  	// is Confidential
  	var isConfidential=chkArr[7];
  	// Sample Code
  	var sampleCode=chkArr[8];
  	// Priority
  	var priority=chkArr[9];
  	// Test GroupCode
  	var testGroupCode=chkArr[10];
  	// Test Group Type
  	var testGroupType=chkArr[11];
  	
  	//Is Mandatory Req 
  	var isMandatoryReq=chkArr[12];
  	
  	
  	var reqDate=chkArr[15];
  	 
  	var labCode=chkArr[16];
  	var testCode=chkArr[17];
  	var prvReqStatus=chkArr[18];
  	var reqNo=chkArr[19];
  	var grpname=chkArr[20];
  	//alert(reqNo);
  	var nRow=0;
  	var tableObj=document.getElementById('setPrvTestDtl');
  	var numRows=tableObj.rows.length;
  	//alert("total length"+numRows);
  		nRow=numRows;
   
  	var tabRow=tableObj.insertRow(numRows);
  	tabRow.id=parseInt(nRow);
  	 
  	var td1=document.createElement("TD");
  	var td2=document.createElement("TD");
  	var td3=document.createElement("TD");
  	var td5=document.createElement("TD");
  	var td6=document.createElement("TD");
  	var td7=document.createElement("TD");
  	var td8=document.createElement("TD");
	var td9=document.createElement("TD");
  	 
  	
	/*  
		  2,'Requistion Raised', skyblue
		  5,'Requisition Raised',
		  3,'Sample Collected', silver
		  4,'Packing List Generated', white
		  6,'Sample/Patient Accepted', maroon
		  7,'Result Entered',   aqua
		  8,'Result Validated', purple
		  9,'Patient Rejected', fuchsia
		  10,'Test Resceduled', green
		  11,'Test Rescheduled', lime
		  12,'Sample Cancelled',  olive
		  13,'Ready For Report Printing', yellow
		  14,'Result Printed',    gold
		  15,'Test Cancelled', teal
		  16,'Test Deleted', red
		  26,'Report Generated' brown
		  
		  */
		  
		  
		  //alert(prvReqStatus);
	
		  
		  
		 var color='';
		 if(prvReqStatus=='2'||prvReqStatus=='5')
		 color='skyblue';
		 if(prvReqStatus=='3')
			 color='silver';
		 if(prvReqStatus=='4')
			 color='#CC99FF';
		 if(prvReqStatus=='6')
			 color='#ffe6e6';
		 if(prvReqStatus=='7')
			 color='aqua';
		 if(prvReqStatus=='8')
			 color='purple';
		 if(prvReqStatus=='9')
			 color='fuchsia';
		 if(prvReqStatus=='10'||prvReqStatus=='11')
			 color='blue';
		 if(prvReqStatus=='12')
			 color='olive';
		 if(prvReqStatus=='13')
			 color='lime';
		 if(prvReqStatus=='14')
			 color='gold';
		 if(prvReqStatus=='15')
			 color='teal';
		 if(prvReqStatus=='16')
			 color='#EB7273';
		 if(prvReqStatus=='26')
			 color='brown';
		 if(prvReqStatus=='17')
			 color='#FFA500';
		 if(prvReqStatus=='18')
			 color='#FFA599';
		 if(prvReqStatus=='55')
			 color='#9999FF';
		 
   if(testGroupCode=="0")
  	td1.innerHTML="<div align='left'   >"+testName+" </div>";  
  	else
  		td1.innerHTML="<div align='left'   >"+testName+"(Group:"+grpname+") </div>";
  	td1.className='tdfonthead';
  	td1.colspan="1";
  	td1.style.width='15%';
	td1.style.backgroundColor=color;
  		
  	td2.innerHTML="<div align='left'   >"+labName+"</div>";
  	td2.className='tdfonthead';
  	td2.colspan="1";
  	td2.style.width='15%';
  	td2.style.backgroundColor=color;
   
  		td3.innerHTML="<div align='left' > "+sampleCode+" </div>";
  		
  	td3.className='tdfonthead';																													
  	td3.colspan="1";
  	td3.style.width='15%';
  	td3.style.backgroundColor=color;
  	td5.innerHTML="<div align='left'  >"+sampleName+"</div>";
   
  	td5.className='tdfonthead';
  	td5.colspan="1";
  	td5.style.width='15%';
  	td5.style.backgroundColor=color;
  		td6.innerHTML="<div align='left'  >"+status+"</div>";	
  		td6.className='tdfonthead';
  		td6.colspan="1";
  		td6.style.width='16%';
  		td6.style.backgroundColor=color;
  		td7.innerHTML="<div align='left'  >"+priority+"</div>";
  		  
  		td7.className='tdfonthead';
  	td7.colspan="1";
  	td7.style.width='9%';
  	td7.style.backgroundColor=color;
  	td8.innerHTML="<div align='left' >"+reqDate+"</div>";
		 
		 
		 
		td8.className='tdfonthead';
	td8.colspan="1";
	td8.style.backgroundColor=color;
	td8.style.width='11%';
	
	
	td9.className='tdfonthead';
	td9.colspan="2";
	td9.style.width='2%';
	td9.style.backgroundColor=color;
	if(prvReqStatus=='2'||prvReqStatus=='5')
	{
	
	/*td9.innerHTML="<div align='center' ><img src='/HISInvestigationG5/hisglobal/images/minus.gif' id='minusButton' onClick='" +
			"("+(parseInt(nRow)-1)+","+(labCode)+","+(testCode)+","+"\""+(reqNo)+"\""+","+"\""+(testGroupCode)+"\""+")'></div>";
	*/
		td9.innerHTML="<div align='center' ><img src='/HISInvestigationG5/hisglobal/images/minus.gif' id='minusButton' onClick='deleteRowPrvReqDtl("+(parseInt(nRow)-1)+","+(labCode)+","+(testCode)+","+"\""+(reqNo)+"\""+","+"\""+(testGroupCode)+"\""+")'></div>";
	
	}
	 
	   
  	
  	tabRow.appendChild(td1);  
  	tabRow.appendChild(td2);
  	tabRow.appendChild(td3);
  	tabRow.appendChild(td5);
  	tabRow.appendChild(td6);
  	tabRow.appendChild(td7);
  	tabRow.appendChild(td8);
  	tabRow.appendChild(td9);
  
  	document.forms[0].numberOfRow.value=numRows;
  	 
  }
  
  
  
  
  
  function deleteRowPrvReqDtl(nrows,labCode,testCode,reqNo,testGroupCode1)

  {
	// alert("testGroupCode1"+testGroupCode1);
	 var retVal = "";
	 if(testGroupCode1=="0")
		 {
		  retVal = confirm("Are You Sure to Delete it?");
		 }
	 else
		 {
		  retVal = confirm("Are You Sure to Delete Group?");
		 }
		 
	 // var retVal = confirm("Are You Sure to Delete it?");
      if( retVal == true ){

    	  document.getElementsByName('delTestCode')[0].value=testCode;
    	  document.getElementsByName('delLabCode')[0].value=labCode;
    	  if(checkBillingDetail(labCode,testCode,reqNo) == 1){

    		  updateReqTable(labCode,testCode,reqNo,"1",testGroupCode1);
        	  
    		  var x = document.getElementById("setprevreqnew").selectedIndex;

    		  if(x=="0")
    		  {
    		  setprevreq(document.getElementById("setprevreqnew")[0])
    		  }
    		  else
    		  {
    		  setprevreq(document.getElementById("setprevreqnew")[1])
    		  }
          
    	  }
    	  else
        	  {
        	 // alert("The Requisition can be cancelled only if billing has been done.");
    		  updateReqTable(labCode,testCode,reqNo,"2",testGroupCode1);
        	  
    		  var x = document.getElementById("setprevreqnew").selectedIndex;

    		  if(x=="0")
    		  {
    		  setprevreq(document.getElementById("setprevreqnew")[0])
    		  }
    		  else
    		  {
    		  setprevreq(document.getElementById("setprevreqnew")[1])
    		  }

        	  }

        	
      }
      else{
     	  //alert("User does not want to continue!....");
         return false;
      }
	 
	   
	  
  }


  function checkBillingDetail(labCode,testCode,reqNo)
  {
	
  	var flg = false;
  	var isBillingDone = false;
  	var _mode = "CHECKBILLDTL";  
  	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&delLabCode="+labCode+"&delTestCode="+testCode+"&requisitionNo="+reqNo, sync:true, postData: "", handleAs: "text",
  		load: function(data) 
  		{
  			//alert(data);
  			isBillingDone = data;
  			flg = true;
  		},
          error: function(error)
          {
              //if(typeof objKitchenList == 'undefined' || objKitchenList==null || objKitchenList=="" || objKitchenList.length==0)
              	//alert("No Kitchen Found");
              //alert(error+"Error while populating Meal Time Information");
              isBillingDone = false;
              flg = false;
          }};

  	var objDojoAjax = dojo.xhrPost(objXHR);
  	return isBillingDone;
  }

  function updateReqTable(labCode,testCode,reqNo,isbilledornot,groupraisedalready)
  {
	//  alert("inside del");
	//alert(reqNo);
	/* document.getElementsByName("requisitionNo")[0].value=reqNo; */
	//document.getElementsByName('requisitionNo')[0].value=reqNo;
  	var flg = false;
  	var isRequisitonRaisingPresent = false;
  	var _mode = "DELETEREQDTL";  
  	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&delLabCode="+labCode+"&delTestCode="+testCode+"&requisitionNo="+reqNo+"&isbilledornot="+isbilledornot+"&groupraisedalready="+groupraisedalready, sync:true, postData: "", handleAs: "text",
  		load: function(data) 
  		{
  			//alert(data);
  			isRequisitonRaisingPresent = data;
  			flg = true;
  		},
          error: function(error)
          {
              //if(typeof objKitchenList == 'undefined' || objKitchenList==null || objKitchenList=="" || objKitchenList.length==0)
              	//alert("No Kitchen Found");
              //alert(error+"Error while populating Meal Time Information");
              isRequisitonRaisingPresent = false;
              flg = false;
          }};

  	var objDojoAjax = dojo.xhrPost(objXHR);
  	return isRequisitonRaisingPresent;
  }

  function showinstruction()
  {
	  
	  var chkVal="dddd";
		var nRow=0;
	  	var tableObj=document.getElementById('addinstruction');
	  	var numRows=tableObj.rows.length;
	  	//alert("total length"+numRows);
	  		nRow=numRows;
	   
	  	var tabRow=tableObj.insertRow(numRows);
	  	tabRow.id=parseInt(nRow);
	  	 
	  	var td1=document.createElement("TD");
	   
	  	td1.innerHTML="<div align='left'>"+chkVal+" </div>";   
	  	td1.className='tdfonthead';
	  	td1.colspan="1";
	  		
	     
	  	tabRow.appendChild(td1);  
	  	 
	  	 popup('popUpDiv1');

	  }
  
  function AddRowToTableAddMoreValue(chkVal,mandTextBoxComboName,finalMadCode)
  {
	//Logic to regain the Lab Test Values
  	// Lab Code
  //	alert("inside here AddRowToTableAddMoreValue"+mandTextBoxComboName);
  	
  //	document.getElementsByName('mandComboTextBoxComboNames')[0].value=mandTextBoxComboName;
  	document.getElementsByName('mandComboTextBoxComboNamesOnPage')[0].value=mandTextBoxComboName;
  	
  	
  	document.getElementsByName('finalMandCode')[0].value=finalMadCode;
  	//var chkArr=chkVal.split("#");
  	//var labCode=chkArr[0];
  	// Lab Name
  //	var labName=chkArr[1];
   
  	 
  	 
  	var nRow=0;
  	var tableObj=document.getElementById('addMoreValue');
  	var numRows=tableObj.rows.length;
  	//alert("total length"+numRows);
  		nRow=numRows;
   
  	var tabRow=tableObj.insertRow(numRows);
  	tabRow.id=parseInt(nRow);
  	 
  	var td1=document.createElement("TD");
   
  	td1.innerHTML="<div align='left'>"+chkVal+" </div>";   
  	td1.className='tdfonthead';
  	td1.colspan="1";
  		
     
  	tabRow.appendChild(td1);  
  	 
  	 

  	document.forms[0].numberOfRow.value=numRows;
  	  }
  
  function callopenmodal(ispidshow)
  {
	 // alert("pidshow"+ispidshow); 
	  
	  if(ispidshow=="1" && (  document.getElementsByName('piddmodalopen')[0]!=null &&  !document.getElementsByName('piddmodalopen')[0].value=="1"))
		{
		  
		  
		  if (confirm('PID should be  present for the selected test.Do you want to open.')) {
			  $("#dialog").dialog("open");
			    // Save it!
			} else {
				
			}
		  
		  
			}
		  
		  
		}
	
	  function ipdesxistonfocus(obj)
	  {
		 // alert(obj.value);
	        
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
	  
	  
	  
	  function fff()
	  {
		//  alert("dd");
		  $("#dialog").dialog("open");  
	  }
	  
	  
	  function isappointmentmandatory(labcode)
	  {
		  var flg = false;
			var labTestCodeArray = "";
			var _mode = "AJX_IS_LAB_MANDTORY";
			var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&tmpLabCode="+labcode, sync:true, postData: "", handleAs: "text",
				load: function(data) 
				{
			///alert(data);
					labTestCodeArray = data;
					
					
					
				},
		        error: function(error)
		        {

		             //  labTestCodeArray = tmpLabTestCodeArray;
		            flg = false;
		        }};
			//alert("new"+labTestCodeArray);
			var objDojoAjax = dojo.xhrPost(objXHR);
			return labTestCodeArray;
			
		  
	  }
	  
	  

	  function validateSave()
	  {
		  //alert("newarrayappdatalababsed"+newarrayappdatalababsed[0]);
		  
	  		var labTestCodeArrayValue=document.getElementsByName('labTestCodeArray')[0].value;
	  	//var patCrNo=document.getElementsByName("patCrNo")[0].value;

	  		var patCrNo=document.getElementsByName('patCrNo')[0].value;
	  	
	  	var ValueLabTest=labTestCodeArrayValue.split("@");
	  		
	  		if(document.getElementsByName('advisedByName')[0].value=="")
	  			document.getElementsByName('advisedBy')[0].value="";
	  		
	  	     document.getElementsByName('advisedByDoctorName')[0].value=document.getElementsByName('advisedByName')[0].value; 
	  	      
	  	      
	  if(document.getElementById('checkOnSave')==null)
	  { 
	  		alert("Please Select Atleast One Lab For Raising!");
	  			return false; 
	  } 
	  	 
	//  alert("document.getElementsByName('visitReason')[0].value"+document.getElementsByName('visitReason')[0].value);
	  
	 
	  // needs to uncomment for bhubneswer
	  /*if(document.getElementsByName('visitReason')[0]==null || document.getElementsByName('visitReason')[0].value=="")
	  { 
	  		alert("Indications can't empty.Please Enter Valid Indications.");
	  		
	  		document.getElementsByName("visitReason")[0].focus();
	  			return false; 
	  } 
	  */
	  
	  
	  	var sampleInfo= document.getElementsByName('sampleInfo');
	  	
	  	
	  	for(var i=0;i<sampleInfo.length;i++)
	  		  {
	  		  	if(sampleInfo[i].value=="-1")
	  		  		{
	  		  			alert("Please Select Sample Code!");
	  		  			sampleInfo[i].focus();
	  		  			return false;
	  		  		}
	  		 
	  		  }
	  	
	  var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
	  //alert(labTestCodeArray);
	  	var Value=labTestCodeArray.split("@");
	  	
	  		var today = new Date();
	  		var date=convertDateToStr(today,"dd-Mon-yyyy");
	  		var Time=convertDateToStr(today,"hh:mm"); 
	  		k=0;//variable for appointment array of date,time and refno
	  		var aptRefNo=[];
	  		var aptDate=[];
	  		var aptTime=[];
	  		
	  		for(i=0;i<Value.length;i++)
	  		{
	  		var flag=false;
	  		Value[i] = Value[i].replace(/;/g, '#');

	  		var SplitValue=Value[i].split("#");
	  		var labName=SplitValue[1];
	  		var testName=SplitValue[3];
	  		var labCode=SplitValue[0];
	  		var testCode=SplitValue[2];
	  		var aptFlag=SplitValue[6];
	  		var offAptNo=SplitValue[19];
	  		var isTimeBound=SplitValue[22];
	  		var isLabAvailable=SplitValue[21];
	  		var islababsedapp=SplitValue[28];
          // alert("createapp"+islababsedapp);
	   		var opdipdraise=SplitValue[26];
	              //  alert("opdipdraise"+opdipdraise);
	  		var callingdesk=document.getElementsByName('patAdmNo')[0].value;
	  	        // alert("callingdesk"+callingdesk);
	  		var opdteststatus=opdipdraise.split("$")[0];
	  		var ipdteststatus=opdipdraise.split("$")[2];

	  		//alert("callingdesk"+callingdesk);
	  		if(isTimeBound=='1')
	  			{
	  				if(isLabAvailable=='1')
	  					{
	  						
	  					}
	  				else{
	  					alert('Time Slot is not open for this Lab ! Test For '+labName+' Can Not Be Raised' );
	  					return false;
	  				}
	  			}

	               //   alert("callingdesk"+callingdesk);
	               
	           if(document.getElementsByName('labbasedapppointment')[0].value=="")    
	  		  if(callingdesk=="" || callingdesk!="-1")
	  		{ 
	          	if(opdteststatus!="0" || ipdteststatus!="0" )
	  		 { 
	  				  // alert("inside");
	  		var divId="aptTagRow_"+labCode+"_"+testCode;
	  		
	  		
	  		if (islababsedapp=='1' && newarrayappdatalababsed.length>0)
				{
				
				for(var g=0;g<newarrayappdatalababsed.length;g++)
					{
					
					var data=newarrayappdatalababsed[g];
					var datalen=data.split("$$");
				    var newdivid1=data.split("$$")[1];

					    var newdivid=data.split("$$")[1];
					  newdivid="aptForDate_"+newdivid;
					  if(typeof document.getElementsByName(newdivid)[0] != 'undefined' )
					{
					if(data.includes(labCode))
						{
						
						if(datalen.length==2)
							{
		  				var aptSatus=createAppointment(newdivid1);
		  				var objaptno=document.getElementsByName('appointmentRefNo')[0].value;
		  				aptRefNo.push(labCode+testCode+"#"+document.getElementsByName('appointmentRefNo')[0].value);
		  			newarrayappdatalababsed[g]=data+"$$"+labCode+testCode+"#"+document.getElementsByName('appointmentRefNo')[0].value;
		  			if(!aptSatus)
					 {
					// alert("returing false 1");
					return false;
					}
							}
						
						else if(datalen.length==3)
							{
							
							var datanew=datalen[2];
							aptRefNo.push(datanew);
							
							}
						
						
						}
					}
					
					 if(typeof document.getElementsByName(newdivid)[0] == 'undefined' && offAptNo=='null')
	  					{

						alert("Can't raise Test: "+testName+" for Laboratory: "+labName+", as no slots are available");
						// alert("returing false 2");
						return false;
						 
	  					}
					 
					if (typeof document.getElementsByName(newdivid+'')[0] != 'undefined' )
					{
						document.getElementsByName('appointmentTime')[0].value=document.getElementsByName('slotST_'+newdivid1+'')[0].value;
						document.getElementsByName('appointmentDate')[0].value=document.getElementsByName('aptForDate_'+newdivid1+'')[0].value;
						
						aptTime.push(labCode+testCode+"#"+document.getElementsByName('slotST_'+newdivid1+'')[0].value);
						aptDate.push(labCode+testCode+"#"+document.getElementsByName('aptForDate_'+newdivid1+'')[0].value);

					}
					
					
					if( typeof document.getElementsByName(newdivid+'')[0] == 'undefined' && offAptNo=='null' )
					{
					
					alert("Can't raise Test: "+testName+" for Laboratory: "+labName+", as no slots are available");
					// alert("returing false 2");
					return false;
					
					}
					
					
					  
					}
				
				}
	  		
	  		//alert(divId + aptFlag);
	  		if(aptFlag==1&&typeof document.getElementsByName('aptForDate_'+divId+'')[0] != 'undefined' && islababsedapp=='0')
	  			{
	  			
	  			
	  		var aptSatus=createAppointment(divId);
	  		var objaptno=document.getElementsByName('appointmentRefNo')[0].value;
	  		aptRefNo.push(labCode+testCode+"#"+document.getElementsByName('appointmentRefNo')[0].value);
	   	if(!aptSatus)
	  			 {
	  			// alert("returing false 1");
	   		
	  			return false;
	  			}
	  			}
	  		
	  		
	  		if(aptFlag==1 && typeof document.getElementsByName('aptForDate_'+divId+'')[0] == 'undefined' && offAptNo=='null' && islababsedapp=='0')
	  			{
	  			
	  			alert("Can't raise Test: "+testName+" for Laboratory: "+labName+", as no slots are available");
	  			// alert("returing false 2");
	  			return false;
	  			
	  			}
	  		
	  		
	  		if (typeof document.getElementsByName('aptForDate_'+divId+'')[0] != 'undefined' && islababsedapp=='0')
	  			{
	  				document.getElementsByName('appointmentTime')[0].value=document.getElementsByName('slotST_'+divId+'')[0].value;
	  				document.getElementsByName('appointmentDate')[0].value=document.getElementsByName('aptForDate_'+divId+'')[0].value;
	  				
	  				aptTime.push(labCode+testCode+"#"+document.getElementsByName('slotST_'+divId+'')[0].value);
	  				aptDate.push(labCode+testCode+"#"+document.getElementsByName('aptForDate_'+divId+'')[0].value);

	  			}
	  		else
	  			{
	  				//alert("offline Apt Details"+document.getElementsByName('offlineAppoitmentDtl')[0].value);
	  				 if(document.getElementsByName('offlineAppoitmentDtl')[0].value=='')
	  					 {
	  					 
	  					
	  					 }
	  				 else
	  					 {
	  					// alert("false");
	  					 var dateAndTime=document.getElementsByName('offlineAppoitmentDtl')[0].value;
	  					 var SplitWithSpace=dateAndTime.split(' ');
	  					 
	  					 var offlineAptDate=SplitWithSpace[0];
	  					 var offLineAptTime=SplitWithSpace[1];
	  				
	  				
	  						 document.getElementsByName('appointmentDate')[0].value=offlineAptDate;
	  				        document.getElementsByName('appointmentTime')[0].value=offLineAptTime;
	  					 
	  				        document.getElementsByName('appointmentRefNo')[0].value=document.getElementsByName('offlineAptDtl')[0].value
	  				        
	  				        aptTime.push(labCode+testCode+"#"+offLineAptTime);
	  						aptDate.push(labCode+testCode+"#"+offlineAptDate);
	  						aptRefNo.push(labCode+testCode+"#"+document.getElementsByName('appointmentRefNo')[0].value);
	  						
	  				       
	  					 }
	  			}
	  		 }
	  		 }

	  		  else
	  			  {
	  				var divId="aptTagRow_"+labCode+"_"+testCode;
	  				
	  				if (islababsedapp=='1' && newarrayappdatalababsed.length>0)
	  					{
	  					
	  					for(var g=0;g<newarrayappdatalababsed.length;g++)
	  						{
	  						
	  						var data=newarrayappdatalababsed[g];
	  						var datalen=data.split("$$");
  						    var newdivid1=data.split("$$")[1];

	  						    var newdivid=data.split("$$")[1];
	  						  newdivid="aptForDate_"+newdivid;
	  						  if(typeof document.getElementsByName(newdivid)[0] != 'undefined' )
	  	  					{
	  						if(data.includes(labCode))
	  							{
	  							
	  							if(datalen.length==2)
	  								{
	  			  				var aptSatus=createAppointment(newdivid1);
	  			  				var objaptno=document.getElementsByName('appointmentRefNo')[0].value;
	  			  				aptRefNo.push(labCode+testCode+"#"+document.getElementsByName('appointmentRefNo')[0].value);
	  			  			newarrayappdatalababsed[g]=data+"$$"+labCode+testCode+"#"+document.getElementsByName('appointmentRefNo')[0].value;
	  			  			if(!aptSatus)
		  					 {
		  					// alert("returing false 1");
		  					return false;
		  					}
	  								}
	  							
	  							else if(datalen.length==3)
	  								{
	  								
	  								var datanew=datalen[2];
	  								aptRefNo.push(datanew);
	  								
	  								}
	  							
	  							
	  							}
	  	  					}
	  						
	  						 if(typeof document.getElementsByName(newdivid)[0] == 'undefined' && offAptNo=='null')
		  	  					{

	  							alert("Can't raise Test: "+testName+" for Laboratory: "+labName+", as no slots are available");
	  							// alert("returing false 2");
	  							return false;
	  							 
		  	  					}
	  						 
	  						if (typeof document.getElementsByName(newdivid+'')[0] != 'undefined' )
	  						{
	  							document.getElementsByName('appointmentTime')[0].value=document.getElementsByName('slotST_'+newdivid1+'')[0].value;
	  							document.getElementsByName('appointmentDate')[0].value=document.getElementsByName('aptForDate_'+newdivid1+'')[0].value;
	  							
	  							aptTime.push(labCode+testCode+"#"+document.getElementsByName('slotST_'+newdivid1+'')[0].value);
	  							aptDate.push(labCode+testCode+"#"+document.getElementsByName('aptForDate_'+newdivid1+'')[0].value);

	  						}
	  						
	  						
	  						if(typeof document.getElementsByName(newdivid+'')[0] == 'undefined' && offAptNo=='null' )
		  					{
		  					
		  					alert("Can't raise Test: "+testName+" for Laboratory: "+labName+", as no slots are available");
		  					// alert("returing false 2");
		  					return false;
		  					
		  					}
	  						
	  						
	  						  
	  						}
	  					
	  					}
	  				
	  				//alert(divId + aptFlag);
	  				if(aptFlag==1&&typeof document.getElementsByName('aptForDate_'+divId+'')[0] != 'undefined' && islababsedapp=='0')
	  					{
	  					
	  					
	  				var aptSatus=createAppointment(divId);
	  				var objaptno=document.getElementsByName('appointmentRefNo')[0].value;
	  				//alert("objaptno"+objaptno);
	  				aptRefNo.push(labCode+testCode+"#"+document.getElementsByName('appointmentRefNo')[0].value);
	  		 	if(!aptSatus)
	  					 {
	  					// alert("returing false 1");
	  					return false;
	  					}
	  					}
	  		 
	  		 	
	  					
	  				
	  				if(aptFlag==1 && typeof document.getElementsByName('aptForDate_'+divId+'')[0] == 'undefined' && offAptNo=='null' && islababsedapp=='0')
	  					{
	  					
	  					alert("Can't raise Test: "+testName+" for Laboratory: "+labName+", as no slots are available");
	  					// alert("returing false 2");
	  					return false;
	  					
	  					}
	  				
	  				if (typeof document.getElementsByName('aptForDate_'+divId+'')[0] != 'undefined' && islababsedapp=='0')
	  					{
	  						document.getElementsByName('appointmentTime')[0].value=document.getElementsByName('slotST_'+divId+'')[0].value;
	  						document.getElementsByName('appointmentDate')[0].value=document.getElementsByName('aptForDate_'+divId+'')[0].value;
	  						
	  						aptTime.push(labCode+testCode+"#"+document.getElementsByName('slotST_'+divId+'')[0].value);
	  						aptDate.push(labCode+testCode+"#"+document.getElementsByName('aptForDate_'+divId+'')[0].value);

	  					}
	  					else
	  					{
	  						//alert("offline Apt Details"+document.getElementsByName('offlineAppoitmentDtl')[0].value);
	  						 if(document.getElementsByName('offlineAppoitmentDtl')[0].value=='')
	  							 {
	  							 
	  							
	  							 }
	  						 else
	  							 {
	  							// alert("false");
	  							 var dateAndTime=document.getElementsByName('offlineAppoitmentDtl')[0].value;
	  							 var SplitWithSpace=dateAndTime.split(' ');
	  							 
	  							 var offlineAptDate=SplitWithSpace[0];
	  							 var offLineAptTime=SplitWithSpace[1];
	  						
	  						
	  								 document.getElementsByName('appointmentDate')[0].value=offlineAptDate;
	  						        document.getElementsByName('appointmentTime')[0].value=offLineAptTime;
	  							 
	  						        document.getElementsByName('appointmentRefNo')[0].value=document.getElementsByName('offlineAptDtl')[0].value
	  						        
	  						        aptTime.push(labCode+testCode+"#"+offLineAptTime);
	  								aptDate.push(labCode+testCode+"#"+offlineAptDate);
	  								aptRefNo.push(labCode+testCode+"#"+document.getElementsByName('appointmentRefNo')[0].value);
	  								
	  						       
	  							 }
	  					}
	  				 
	  				 
	  			  }
	  		k++;
	  }
	  		
	  		 document.getElementsByName('labTestAptTime')[0].value=aptTime;
	  		document.getElementsByName('labTestAptDate')[0].value=aptDate;
	  		//alert("aptRefNo"+aptRefNo);
	  	 document.getElementsByName('labTestAptRefNo')[0].value=aptRefNo;

	     
	  	return true;
	  }
	  
	  
	  
	  function setsiteUsingAjax(siteobj,labCode,TestCode)
	  {
	  	//alert(objPriority.id);
	  	var priorityvalue=siteobj.value;
	  	var id=siteobj.id;
	  	var idd=id.split("#");
	  	var iddd=idd[0];
	  	var nrow=idd[1];
	  	//alert(nrow);
	  	//alert(iddd);
	  	
	  	if(iddd=="site")
	  	  {


	  /*
	  		if(document.getElementsByName('labTestCodeArray')[0].value.split('@')[nrow-1].split('#')[5]=='P')
	  			{;}


	  		else
	  			{*/
	  		var sampleidcode="sampleaddtbl#"+nrow;
	  		//var samplecode=document.getElementById(sampleidcode).value;
	  		var labidcode="labtbl#"+nrow;
	  		var labcode=document.getElementById(labidcode).value;
	  		
	  		//alert(samplecode);
	  		//var length=document.getElementsByName('sampleInfo').length;
	  		
//	  	alert(length);	
	  	
	  /*	for(var i=0;i<length;i++)
	  		{
	  		
	  		if(samplecode==(document.getElementsByName('sampleInfo')[i].value) && labcode==(document.getElementsByName('labCode')[i].value))
	  		{
	  			//alert("ok");
	  		//	if(priorityvalue=="2")
	  		document.getElementsByName('priority')[i].value=priorityvalue;
	  		//alert(i);
	  	   
	  	//	modifyPriorityLabTestCodeArray(priorityvalue,labCode,TestCode,labTestCodeArray);
	  		}
	  		
	  		}*/

	  			/*}*/
	  	
	  	  }
	  	else if(iddd=="siteaddtbl")
	  		{

	  		/*if(document.getElementsByName('labTestCodeArray')[0].value.split('@')[nrow-1].split('#')[5]=='P')
	  		{;}

	  		else
	  			{*/
	  		var sampleidcode="sampletbl#"+nrow;
	  		//var samplecode=document.getElementById(sampleidcode).value;
	  		//alert(samplecode);
	  	//	var length=document.getElementsByName('sampleInfo').length;
	  		var labidcode="labaddtbl#"+nrow;
	  		var labcode=document.getElementById(labidcode).value;
	  		
//	  	alert(length);	
	  	
	  	/*for(var i=0;i<length;i++)
	  		{
	  		
	  		if(samplecode==(document.getElementsByName('sampleInfo')[i].value) && labcode==(document.getElementsByName('labCode')[i].value))
	  		{
	  			//alert("ok");
	  		//	if(priorityvalue=="2")
	  		document.getElementsByName('priority')[i].value=priorityvalue;
	  		
	  		
	  		//document.getElementsByName('priority')[i].onchange();
	  		}
	  		}*/
	  		/*}	*/
	  		
	  		}
	  	
	  	var labTestCodeArray=document.getElementsByName('newlabtestcodearray')[0].value;
	  //	alert(labTestCodeArray);
	  	var priority=siteobj.value;
	  	labTestCodeArray=labTestCodeArray.replace(/#/g,";"); // As hash separator doesnot work in URL

	  	if(labTestCodeArray.indexOf("&")!=-1)
	  		labTestCodeArray=labTestCodeArray.replace(/&/g,"*");

	  //	alert("data1"+priority+"@!"+labCode+"@!"+TestCode+"@!");
	  	var finalLabTestCodeArray = modifysiteLabTestCodeArray(priority,labCode,TestCode,labTestCodeArray);
	  	document.getElementsByName('newlabtestcodearray')[0].value=finalLabTestCodeArray;
	  	
	  //	alert("data"+document.getElementsByName('newlabtestcodearray')[0].value);

	  }
	  
	  
	  
	  function modifysiteLabTestCodeArray(tmpPriority,tmpLabCode,tmpTestCode,tmpLabTestCodeArray)
	  {
	  	var flg = false;
	  	var labTestCodeArray = "";
	  	var _mode = "AJX_MODIFY_SITE";
	  	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&tmpLabCode="+tmpLabCode+"&tmpTestCode="+tmpTestCode+"&tmpsite="+tmpPriority+"&newlabtestcodearray="+tmpLabTestCodeArray, sync:true, postData: "", handleAs: "text",
	  		load: function(data) 
	  		{
	  			//alert("data2"+data);
	  			labTestCodeArray = data;
	  			flg = true;
	  		},
	          error: function(error)
	          {
	              labTestCodeArray = tmpLabTestCodeArray;
	              flg = false;
	          }};

	  	var objDojoAjax = dojo.xhrPost(objXHR);
	 // 	alert("data3"+labTestCodeArray);
	  	return labTestCodeArray;
	  }
	  
	  

	  function checkreqformTestType(TestCode)
	  {	var flg = false;
	  	var remarks = "";
	  	var _mode = "AJX_CHECK_REQFORM_TESTTYPE";
	  	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&testCodee="+TestCode, sync:true, postData: "", handleAs: "text",
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
	  	return remarks;
	  }

	  function checkTestAvailabilityAjax(userTestCode)
	  {
	  	//alert("setAptNoUsingAjax"+appoitmentNo+"labcode"+tmpLabCode+"testCode"+tmpTestCode);
	  	var flg = false;
	  	var remarks = "";
	  	var _mode = "AJX_CHECK_AVAILABILITY_TEST_CODE";
	  	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&userTestCode="+userTestCode, sync:true, postData: "", handleAs: "text",
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
	  	return remarks;
	  }


	  function setTestORTestGroup(obj)
	  {

	  	if(obj.value=="1")  // Test Group
	  	{
	  		document.getElementById('divSearchTestGroup').style.display="";
	  		document.getElementById('divSearchTest').style.display="none";
	  		document.getElementsByName('tstOrTestGroupValue')[0].value=obj.value;
	  		// alert(document.getElementsByName('tstOrTestGroupValue')[0].value);
	  		document.getElementsByName('hmode')[0].value="SEARCHLABWISETESTGROUPONCLICK";
	  		 document.forms[0].submit();


	  	}
	  	else  // Test
	  	{
	  		document.getElementById('divSearchTest').style.display="";
	  		document.getElementById('divSearchTestGroup').style.display="none";
	  		 document.getElementsByName('tstOrTestGroupValue')[0].value=obj.value;

	  		 document.getElementsByName('hmode')[0].value="SEARCHLABWISETEST";
	  		 document.forms[0].submit();

	  	}


	  }

	  function clearVal()
	  {

	  	document.getElementsByName('searchLabName')[0].value="";
	  }

	  function onLoad()
	  {
	  	document.getElementsByName('tstGrpArr')[0].value="";
	  	document.getElementsByName('tstValArr')[0].value="";
	  }


	  function showAddorGOButton(obj)
	  {


	  	 var labCode=document.getElementsByName('searchLabName')[0].value;
	  	var testCode=document.getElementsByName('searchTestName')[0].value;
	  	var testGroupCode=document.getElementsByName('searchTestGroupName')[0].value;
	  	var isTestGroup=document.getElementsByName('isTestGroup')[1].checked;
	  	//alert(typeof isTestGroup+" "+isTestGroup);
	  		if(isTestGroup)
	  		{
	  			if(labCode!=null && labCode!='' && testGroupCode!=null && testGroupCode!='')
	  			{
	  				document.getElementById('addGroupButton').style.display="";
	  				document.getElementById('goButton').style.display="none";
	  			}
	  			else
	  			{
	  				document.getElementById('addGroupButton').style.display="none";
	  				document.getElementById('goButton').style.display="";
	  			}
	  			document.getElementById('addTestButton').style.display="none";
	  		}
	  		else
	  		{
	  			if(labCode!=null && labCode!='' && testCode!=null && testCode!='')
	  			{
	  				document.getElementById('addTestButton').style.display="";
	  				document.getElementById('goButton').style.display="none";
	  			}
	  			else
	  			{
	  	        document.getElementById('addTestButton').style.display="none";
	  				document.getElementById('goButton').style.display="";
	  			}
	  			document.getElementById('addGroupButton').style.display="none";
	  		}
	  }

	  function showAddorGOButtonForAuto(obj)
	  {
	  	var value=obj.value;
	  	if(value=='')
	  		{
	          availableTests= setTestComboUsingAjax(value);
	         var list1=document.getElementsByName('tstValArr')[0].value;
	        var obj = JSON.parse(list1);
	         availableTests=obj;

	           var availableGroups= setTestGroupUsingAjax(value);
	                 var list2=document.getElementsByName('tstGrpArr')[0].value;
	                var obj2= JSON.parse(list2);
	                availableTestGroups=obj2;

	  		}

	  	 var labCode=document.getElementsByName('searchLabName')[0].value;
	  	var testCode=document.getElementsByName('searchTestName')[0].value;
	  	var testGroupCode=document.getElementsByName('searchTestGroupName')[0].value;
	  	var isTestGroup=document.getElementsByName('isTestGroup')[1].checked;
	  	//alert(typeof isTestGroup+" "+isTestGroup);
	  		if(isTestGroup)
	  		{
	  			if(labCode!=null && labCode!='' && testGroupCode!=null && testGroupCode!='')
	  			{
	  				document.getElementById('addGroupButton').style.display="";
	  				document.getElementById('goButton').style.display="none";
	  			}
	  			else
	  			{
	  				document.getElementById('addGroupButton').style.display="none";
	  				document.getElementById('goButton').style.display="";
	  			}
	  			document.getElementById('addTestButton').style.display="none";
	  		}
	  		else
	  		{
	  			if(labCode!=null && labCode!='' && testCode!=null && testCode!='')
	  			{
	  				document.getElementById('addTestButton').style.display="";
	  				document.getElementById('goButton').style.display="none";
	  			}
	  			else
	  			{
	  	        document.getElementById('addTestButton').style.display="none";
	  				document.getElementById('goButton').style.display="";
	  			}
	  			document.getElementById('addGroupButton').style.display="none";
	  		}
	  }

	  function clearTestAndGroup()
	  {
	     document.getElementsByName('tstValArr')[0].value="";
	      document.getElementsByName('tstGrpArr')[0].value="";
	      document.getElementsByName('searchTestName')[0].value="";
	  		document.getElementsByName('searchTestGroupName')[0].value="";
	  		document.getElementsByName('searchTest')[0].value="";
	  		document.getElementsByName('searchTestGroup')[0].value="";
	  }
	  

	  function checkreqformTestType(TestCode)
	  {	var flg = false;
	  	var remarks = "";
	  	var _mode = "AJX_CHECK_REQFORM_TESTTYPE";
	  	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&testCodee="+TestCode, sync:true, postData: "", handleAs: "text",
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
	  	return remarks;
	  }

	  function checkTestAvailabilityAjax(userTestCode)
	  {
	  	//alert("setAptNoUsingAjax"+appoitmentNo+"labcode"+tmpLabCode+"testCode"+tmpTestCode);
	  	var flg = false;
	  	var remarks = "";
	  	var _mode = "AJX_CHECK_AVAILABILITY_TEST_CODE";
	  	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&userTestCode="+userTestCode, sync:true, postData: "", handleAs: "text",
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
	  	return remarks;
	  }


	  function setTestORTestGroup(obj)
	  {

	  	if(obj.value=="1")  // Test Group
	  	{
	  		document.getElementById('divSearchTestGroup').style.display="";
	  		document.getElementById('divSearchTest').style.display="none";
	  		document.getElementsByName('tstOrTestGroupValue')[0].value=obj.value;
	  		// alert(document.getElementsByName('tstOrTestGroupValue')[0].value);
	  		document.getElementsByName('hmode')[0].value="SEARCHLABWISETESTGROUPONCLICK";
	  		 document.forms[0].submit();


	  	}
	  	else  // Test
	  	{
	  		document.getElementById('divSearchTest').style.display="";
	  		document.getElementById('divSearchTestGroup').style.display="none";
	  		 document.getElementsByName('tstOrTestGroupValue')[0].value=obj.value;

	  		 document.getElementsByName('hmode')[0].value="SEARCHLABWISETEST";
	  		 document.forms[0].submit();

	  	}


	  }

	  function clearVal()
	  {

	  	document.getElementsByName('searchLabName')[0].value="";
	  }

	  function onLoad()
	  {
	  	document.getElementsByName('tstGrpArr')[0].value="";
	  	document.getElementsByName('tstValArr')[0].value="";
	  }


	  function showAddorGOButton(obj)
	  {


	  	 var labCode=document.getElementsByName('searchLabName')[0].value;
	  	var testCode=document.getElementsByName('searchTestName')[0].value;
	  	var testGroupCode=document.getElementsByName('searchTestGroupName')[0].value;
	  	var isTestGroup=document.getElementsByName('isTestGroup')[1].checked;
	  	//alert(typeof isTestGroup+" "+isTestGroup);
	  		if(isTestGroup)
	  		{
	  			if(labCode!=null && labCode!='' && testGroupCode!=null && testGroupCode!='')
	  			{
	  				document.getElementById('addGroupButton').style.display="";
	  				document.getElementById('goButton').style.display="none";
	  			}
	  			else
	  			{
	  				document.getElementById('addGroupButton').style.display="none";
	  				document.getElementById('goButton').style.display="";
	  			}
	  			document.getElementById('addTestButton').style.display="none";
	  		}
	  		else
	  		{
	  			if(labCode!=null && labCode!='' && testCode!=null && testCode!='')
	  			{
	  				document.getElementById('addTestButton').style.display="";
	  				document.getElementById('goButton').style.display="none";
	  			}
	  			else
	  			{
	  	        document.getElementById('addTestButton').style.display="none";
	  				document.getElementById('goButton').style.display="";
	  			}
	  			document.getElementById('addGroupButton').style.display="none";
	  		}
	  }

	  function showAddorGOButtonForAuto(obj)
	  {
	  	var value=obj.value;
	  	if(value=='')
	  		{
	          availableTests= setTestComboUsingAjax(value);
	         var list1=document.getElementsByName('tstValArr')[0].value;
	        var obj = JSON.parse(list1);
	         availableTests=obj;

	           var availableGroups= setTestGroupUsingAjax(value);
	                 var list2=document.getElementsByName('tstGrpArr')[0].value;
	                var obj2= JSON.parse(list2);
	                availableTestGroups=obj2;

	  		}

	  	 var labCode=document.getElementsByName('searchLabName')[0].value;
	  	var testCode=document.getElementsByName('searchTestName')[0].value;
	  	var testGroupCode=document.getElementsByName('searchTestGroupName')[0].value;
	  	var isTestGroup=document.getElementsByName('isTestGroup')[1].checked;
	  	//alert(typeof isTestGroup+" "+isTestGroup);
	  		if(isTestGroup)
	  		{
	  			if(labCode!=null && labCode!='' && testGroupCode!=null && testGroupCode!='')
	  			{
	  				document.getElementById('addGroupButton').style.display="";
	  				document.getElementById('goButton').style.display="none";
	  			}
	  			else
	  			{
	  				document.getElementById('addGroupButton').style.display="none";
	  				document.getElementById('goButton').style.display="";
	  			}
	  			document.getElementById('addTestButton').style.display="none";
	  		}
	  		else
	  		{
	  			if(labCode!=null && labCode!='' && testCode!=null && testCode!='')
	  			{
	  				document.getElementById('addTestButton').style.display="";
	  				document.getElementById('goButton').style.display="none";
	  			}
	  			else
	  			{
	  	        document.getElementById('addTestButton').style.display="none";
	  				document.getElementById('goButton').style.display="";
	  			}
	  			document.getElementById('addGroupButton').style.display="none";
	  		}
	  }

	  function clearTestAndGroup()
	  {
	     document.getElementsByName('tstValArr')[0].value="";
	      document.getElementsByName('tstGrpArr')[0].value="";
	      document.getElementsByName('searchTestName')[0].value="";
	  		document.getElementsByName('searchTestGroupName')[0].value="";
	  		document.getElementsByName('searchTest')[0].value="";
	  		document.getElementsByName('searchTestGroup')[0].value="";
	  }

	  
	  
	  function setPrevTestDtlUsingAjaxALL(CrNo,fromwhichcall)
	  {
		  
		  
	  	var flg = false;
	  	var tstValArr = "";
	  	var _mode = "AJX_PRV_TEST_DTL_ALL";
	  	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&tmpCrNo="+CrNo+"&prev_req_fromwhichcall="+fromwhichcall, sync:true, postData: "", handleAs: "text",
	  		load: function(data)
	  		{
	  			//alert("LIST_PRVTESTDTL_AJAX"+data);
	  			tstValArr = data;
	  			flg = true;
	  		},
	          error: function(error)
	          {
	              flg = false;
	          }};

	  	var objDojoAjax = dojo.xhrPost(objXHR);
	  	document.getElementsByName('prvTestDtl')[0].value=tstValArr;

	  	 document.getElementById('saveDiv').style.display="none";
		  $("#wait").css("display", "none");

	 var values=tstValArr.split('@');
	
	 //alert("length"+values.length);
	 for(i=0;i<(values.length-1);i++)
	 {
		//alert(i);
		 AddRowToTablePrv(values[i]);

	 }
	
		 return tstValArr;
	  }
	  
	  
	  
	  function showamount()
	  {
		
		if  (document.getElementsByName('newlabtestcodearray')!=null )
		  {
			  
			  if(document.getElementsByName('newlabtestcodearray')[0].value!="")
			  {
				  var val=document.getElementsByName('newlabtestcodearray')[0].value;
				  var len=val.split("@");
				  var testname="";
				  var colorcodee="";
					  var testrate="";
					  var map = new Object(); // or var map = {};
				  for(var k=0;k<len.length;k++)
					  {
					  
					  var data=len[k];
					 // alert(data);
					  document.getElementById("estimaterate").innerHTML="200.00";				

					  var testco="";
					  var testna="";
					  var  testr = "";
					  var  grptyp = data.split("#")[10];
					 
					//  alert("grptyp"+grptyp);
					  
					  if( grptyp!=null && grptyp!="null" && grptyp!="0")
						  {
						  testco=data.split("#")[10];
						  testna=data.split("#")[18];
						   testr = grptestratess[testco];
						
						  }
					  else
						  {
						   testco=data.split("#")[2];
						   testna=data.split("#")[3];
						  
						//   alert("not grp"+testco);
							  
						 //  testr = testratess[testco];
						
						   if(testratess[testco]!=undefined)
			    			{
				   testr = testratess[testco];
			    			}
			    		else
			    			{
			    			 testr ="0@#"+testco+"@#"+testna;
			    			}
						   
						   
						  }
					  
					//  alert("testr"+testr);
					  var testrnew="";
					  testrnew=testr;
				
					  if(testr.includes("^"))
					  {
					//	  alert("true");
						  testr=testr.split("^")[0];
						  
					  }
					  else{
						  testr=testr.split("@#")[0];
						  
						//  alert("false");
					  }
					  
					
					  
					 // alert("testr"+testr);
					 if (map[testco]==undefined)
						 {
						 
						  if(testrnew.includes("@#"))
						  {
							  testname+=testrnew.split("@#")[1]+"#@";
							  colorcodee+=testrnew.split("@#")[2]+"#@";
						  }
						  else
							  {
							
							  }
						  
					  testrate+=testr+"#@";
					  //testname+=testna+"#@";
					  map[testco] = testr;
					  
						 }
					 else
						 {
						 
						 }
					 
					  
					  }
				  //alert("colorcodee ff"+testname+"rate"+colorcodee);
				  setpopoversinchargebtn(testname,testrate,colorcodee);
				  
				  
			  }
			  
			  
		  }
		  
		  
	
	  }
	  
	  
	  
	  
	  
		function setpopoversinchargebtn(testname,testrate,colorcodee)
		{

		  
			
			
			
		//	alert("colorcodee fianl"+colorcodee);
		      var stringg="<table class='table table-condensed table-responsive' >";


		            var  viewss="2";
		             var  chargee=100.0;
		             var  newdataa="test1";
		             
		               stringg+="<tr><td width='50'>"+newdataa+"<br/>("+ viewss +") </td><td>"+ Math.floor(chargee)+"&nbsp;&#8377;&nbsp;"+"</td></tr>";


		    
		    stringg+="</table>";
		  //  alert("stringg"+stringg);
		 	// $('#estimatebtn').attr('data-content', stringg);
			
		 	var datat="<table class='table table-condensed table-responsive' style='color:black;background-color:white'>";
		 	   var totalratee=0.00;
		 	 for(var k=0;k<testname.split("#@").length-1;k++)
		 		 {
		 		 
		 		 if(testname.split("#@")!="")
		 			 {
		 			totalratee=(parseFloat(totalratee))+(parseFloat(testrate.split("#@")[k]));
		 			
		 		datat+="<tr>";
		 		 if(testrate.split("#@")[k]=="0")
		 			 {
		 	      	datat+="<td  nowrap style=' white-space: nowrap;align:right;border-left : 4px solid "+colorcodee.split("#@")[k]+"  '>"+"&nbsp;"+testname.split("#@")[k]+""+"</td>"+"<td nowrap style=' white-space: nowrap;align:right'>:&nbsp;&nbsp;Free</td>";
		 			 }
		 		 else
		 			 {
		 			datat+="<td  nowrap style=' white-space: nowrap;align:right;border-left : 4px solid "+colorcodee.split("#@")[k]+"  '>"+"&nbsp;"+testname.split("#@")[k]+""+"</td>"+"<td nowrap style=' white-space: nowrap;align:right'>"+": &#8377;&nbsp;"+testrate.split("#@")[k]+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";
		 			 
		 			 }
		 		 datat+="</tr>";
		 			 }
		 		 
		 		 }
		 	datat+="</table>";
		 	 		
		 	
		 //	alert("datat"+datat);
		 	 $('#setam').html(datat); 
			  document.getElementById("estimaterate").innerHTML=": &#8377;&nbsp;"+totalratee+"&nbsp;&nbsp;";				



			}
		
		
		
		//add by chandan
		

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

		function searchLabWiseTest()
		{
		    	document.getElementsByName('hmode')[0].value="SEARCHLABWISETEST";

			document.forms[0].submit();
			//alert ("fg");
		}


		function searchLabWiseTest2()
		{

		    document.getElementsByName('hmode')[0].value="SEARCHTESTKEYWORDNEW";

			document.forms[0].submit();
		}

		function searchGroup()
		{
			document.getElementsByName('hmode')[0].value="SEARCHGROUP";


			document.forms[0].submit();
		}

		function searchTestGroup()
		{

			document.getElementsByName('bookMarkCode')[0].value="";
			document.getElementsByName('aptTestCode')[0].value="";
			document.getElementsByName('aptLabCode')[0].value="";

			document.getElementsByName('hmode')[0].value="SEARCHTEST";


			document.forms[0].submit();
		}


		function pagesubmit()
		{
			document.getElementById("showhideOnPagination").style.display="";
			document.getElementById("hideOnPagination").style.display="";
			document.getElementById("showOnPagination").style.display="none";

			}

		  function setDateInApoitment(patCrNo,paraId,obj,divAptTagRow)
		  {
			   var date=document.getElementsByName('dateTag')[0].value;

			   var tocheckfromwherecall=paraId;

		           if(tocheckfromwherecall.includes("^"))
		               {
		        	   tocheckfromwherecall=tocheckfromwherecall.split("^");
		        	  
		               }
				  
			  if(tocheckfromwherecall[1]=="0")
			  {
				  getAptSlotDetails(patCrNo,paraId,date,divAptTagRow,4);
			  }else
		       {
				  getAptSlotDetails(patCrNo,paraId,date,divAptTagRow,2);
			      }
		      
			  }

		  setInterval(function() {
			 	fleXenv.fleXcrollMain("mycustomscroll2");
			 	fleXenv.initByClass("flexcroll");

			 }, 500);


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


		  function setPrevTestDtlUsingAjax(CrNo)
		  {
		  	var flg = false;
		  	var tstValArr = "";
		  	var _mode = "AJX_PRV_TEST_DTL";
		  	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&tmpCrNo="+CrNo, sync:true, postData: "", handleAs: "text",
		  		load: function(data)
		  		{
		  			//alert("LIST_PRVTESTDTL_AJAX"+data);
		  			tstValArr = data;
		  			flg = true;
		  		},
		          error: function(error)
		          {
		              flg = false;
		          }};

		  	var objDojoAjax = dojo.xhrPost(objXHR);
		  	document.getElementsByName('prvTestDtl')[0].value=tstValArr;

		  	 document.getElementById('saveDiv').style.display="none";
		  	 $("#wait").css("display", "none");
		 var values=tstValArr.split('@');

		 //alert("length"+values.length);
		 for(i=0;i<(values.length-1);i++)
		 {
			//alert(i);
			 AddRowToTablePrv(values[i]);

		 }
			 return tstValArr;
		  }


		 
		  
		  
		function showPatDetailsPatient()
		{
		document.getElementById("showhidePatient").style.display="";
		document.getElementById("hidePatient").style.display="";
		document.getElementById("showPatient").style.display="none";

		}

		function hidePatDetailsPatient()
		{
		document.getElementById("showhidePatient").style.display="none";
		document.getElementById("hidePatient").style.display="none";
		document.getElementById("showPatient").style.display="";
		}
		
		//end
		
		
		
		

		function AddRowToTablerates(chkArr,labarray1,chkVal,map)
		{
			
			
			//alert("addrow"+chkArr);
			//alert("labarray1"+labarray1);
			var testcode="1";
			//var testnameee=chkarray[3];
			var testcode1=chkArr[2];
			var labcode1=chkArr[0];
			var grpcode=chkArr[10];

			
			  var testco="";
			  var testna="";
			  var  testr = "";
			  var  grptyp =grpcode;
			  var colorcodee="";
			if( grptyp!=null && grptyp!="null" && grptyp!="0")
			  {
				testco=grpcode;
			   testr = grptestratess[grpcode];
			
			  }
		     else
			  {
			  
			//   alert("not grp"+testco);
		    		testco=testcode1;
			 //  testr = testratess[testcode1];
			
			   if(testratess[testcode1]!=undefined)
   			{
	   testr = testratess[testcode1];
   			}
   		else
   			{
   			 testr ="0@#"+testcode1+"@#"+chkArr[3];
   			}
			   
			   
			  }
			
		      //alert("testr"+testr);
			  var testrnew="";
			  testrnew=testr;
			  
			  if(testr.includes("^"))
			  {
			//	  alert("true");
				  testr=testr.split("^")[0];
				  colorcodee=testrnew.split("@#")[2];
			  }
			  else{
				  testr=testr.split("@#")[0];
				  colorcodee=testrnew.split("@#")[2];
				//  alert("false");
			  }

			 /* if (map[testco]==undefined)
				 {
				 
				  if(testrnew.includes("@#"))
				  {
					  //testname+=testrnew.split("@#")[1]+"#@";
				  }
				  else
					  {
					  
					  }
				  
			 var testrate=testr+"#@";
			  //testname+=testna+"#@";
			  map[testco] = testr;
			  
				 }
			 else
				 {
				 
				 }*/
			  
			
			
			var ispidshow=chkArr[27];
			var islababsedapp=chkArr[28];
			
			//alert("islababsedapp"+islababsedapp);
			var data=document.getElementsByName('piddata')[0].value;
			//  alert("data"+data);
			if(data!=undefined && data!="")
				{
				
				if(ispidshow=="1")
				{
				data+=labcode1+"@@"+testcode1+"@@"+ispidshow+"##";
				document.getElementsByName('piddata')[0].value=data;
				document.getElementById('myButton').style.display="";

				}
				
			      	
				}
			else
				{
				
				if(ispidshow=="1")
				{
				data=labcode1+"@@"+testcode1+"@@"+ispidshow+"##";
				document.getElementsByName('piddata')[0].value=data;
				document.getElementById('myButton').style.display="";

				}
				
				
				}
		       
			 //alert("document.getElementsByName('piddata')[0].value"+document.getElementsByName('piddata')[0].value);
			
			
			
			 
			var mainn=labcode1+testcode1;
			var issufficientamount = issufficientamountt(testcode,"0");
			var isduplicatetestraisedtoday = isduplicatetestrasied(chkArr);
		    var flag="0";
		   // alert("isduplicatetestraisedtoday"+isduplicatetestraisedtoday);
			if(grpcode=="0" && isduplicatetestraisedtoday=="1")
				{
			if (confirm('This Test has already been rasied Today.Do you want to Continue')) {
				flag="0";
			    // Save it!
			} else {
				document.getElementById(mainn).checked=false;
				//document.getElementById("td1"+chkVal).style.backgroundColor="";
				//document.getElementById("td2"+chkVal).style.backgroundColor="";
				//document.getElementById("td3"+chkVal).style.backgroundColor="";
				
			//to delete the test on de selecting the corresponding check box	
			////	alert(chkArr[0]);
			//	alert(chkArr[2]);
			//	deleteRow(1,chkArr[0],chkArr[2]);
				flag="1";
				
				if(document.getElementById("td1"+chkVal)!=null)
				document.getElementById("td1"+chkVal).style.backgroundColor="";
				if(document.getElementById("td2"+chkVal)!=null)
				document.getElementById("td2"+chkVal).style.backgroundColor="";
			    // Do nothing!
			}
				}
			
		  	if(issufficientamount=="1")
		  	{
		  		
		  		alert("Patient Account Balance Going TO Be Insufficient. Please Deposit Part Payment!!");
		  		return null;
		  	}
		  	
		  	if(flag=="1")
		  	{
		  		return null;
		  	}
		  	
			//alert(document.getElementById('tableSelectedLabTestIdHide'))
			if(document.getElementById('tableSelectedLabTestId')!=null)
				{
				
			document.getElementById('tableSelectedLabTestId').style.display="";
				}
			// Logic to regain the Lab Test Values
			
			//alert(chkArr);
		  	 
			// Lab Code
			//alert(chkArr);
			var labCode=chkArr[0];
			// Lab Name
			var labName=chkArr[1];
			// Test Code
			var testCode=chkArr[2];
			// Test Name
			var testName=chkArr[3];
			// Sample Combo String
			var sampleComboStr=chkArr[4];
			//alert(sampleComboStr);
			// Test Type
			var testType=chkArr[5];
			// is Appointment
			var isAppointment=chkArr[6];
			 
			// is Confidential
			var isConfidential=chkArr[7];
			// Sample Code
			var sampleCode=chkArr[8];
			// Priority
			var priority=chkArr[9];
			// Test GroupCode
			var testGroupCode=chkArr[10];
			// Test Group Type
			var testGroupType=chkArr[11];
			
			//Is Mandatory Req 
			var isMandatoryReq=chkArr[12];
			
		var mandTextBoxCombo=chkArr[15];
		var mandTextBoxComboName=chkArr[16];
				var finalMadCode=chkArr[17];

				var testGrpCode=chkArr[18];
				var offlineAptNo=chkArr[19];
				var instructionss=chkArr[20];
				var isreqformneeded=chkArr[24];
				
				var opdipdraise=chkArr[26];
				var callingdesk=document.getElementsByName('patAdmNo')[0].value;
				//alert("callingdesk"+callingdesk);
				if(callingdesk=="-1")
					{
					//alert("callingdesk"+":normal");

					
					}
				
				if(callingdesk=="")
					{
					//alert("callingdesk"+":opd");

					var opdteststatus=opdipdraise.split("$")[0];
				//alert(opdteststatus);
					
					if(opdteststatus=="0")
						{
					isMandatoryReq="0";
					isAppointment="0";
						}
					
						}
				
				 if(callingdesk!="" && callingdesk!="-1")
				{
						//alert("callingdesk"+":ipd");

						
				var ipdteststatus=opdipdraise.split("$")[2];
			//	alert(ipdteststatus);

				if(ipdteststatus=="0")
					{
				isMandatoryReq="0";
				isAppointment="0";
					}
				
					}
				
				//alert(document.getElementsByName('patAdmNo')[0].value);
				
			//	alert("opdteststatus"+opdteststatus+"ipdteststatus"+ipdteststatus);
		        /*if(instructionss=='undefined')
		        	instructionss = null;
		        else*/
		        	instructionss=instructionss.split('\n').join("<br>");
				//alert(instructionss);
				document.getElementsByName('mandComboTextBoxComboNamesOnPage')[0].value=mandTextBoxComboName;
				document.getElementsByName('finalMandCode')[0].value=finalMadCode;
			
			var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
			var patCrNo=document.getElementsByName('patCrNo')[0].value;
			var labCodeSaved=document.getElementsByName("labCode");
			var testCodeSaved=document.getElementsByName("testCode");
			var divAptTagRow="aptTagRow_"+labCode+"_"+testCode;  //+labCode+testCode
			//alert(divAptTagRow);
			var size=0;
			if(typeof labCodeSaved!='undefined' && labCodeSaved!=null)
				size=labCodeSaved.length;
			if(size>0)
			{
				for(var i=0;i<size;i++)
					{
						if(labCodeSaved[i].value==labCode)
							{
								if(testCodeSaved[i].value==testCode)
								{
									/*var nRow21=0;
									var tableObj21=document.getElementById('tableSelectedLabTestIdHide');
									
									var numRows21=tableObj21.rows.length;
									nRow21=numRows21;
									
									var idd21="sampleaddtbl#"+nRow21;
									var val21=document.getElementById(idd21);
									setSampleCodeUsingAjax(val21,labCode,testCode);
		*/
									alert("Laboratory :'"+labName+"' with corresponding Test :'"+testName+"' Already added. Please Select another Lab/Test.");
									var cbs =document.getElementsByName('chkLabTest');
									 //alert(cbs);
									for(var i=0; i < cbs.length; i++) {
										if(cbs[i].checked)
								    	{
										var id=cbs[i].id;
										//alert(id);
										if(id==labCode+testCode)
											{
										      document.getElementById(id).checked = false;
								    	     }
									     }
								      }
									return false;
								
								}
							}
					}
				
			}
			var nRow=0;
			var tableObj=document.getElementById('tableSelectedLabTestIdHide');
			
			var numRows=tableObj.rows.length;
			nRow=numRows;
			var tabRow=tableObj.insertRow(numRows);
			tabRow.id=parseInt(nRow);

			var td1=document.createElement("TD");
			var td2=document.createElement("TD");
			var td3=document.createElement("TD");
			var td5=document.createElement("TD");
			var td6=document.createElement("TD");
			var td7=document.createElement("TD");
			var td8=document.createElement("TD");
			//alert(isreqformneeded);
			if(isreqformneeded!=0)
				td1.innerHTML="<div align='left' id='checkOnSave'>"+testName+"<input id='labtbl#"+nRow+"' type='hidden' name='labCode' value='"+labCode+"'/></div>";
				//td1.innerHTML="<div align='left' id='checkOnSave'>"+testName+"<input id='labtbl#"+nRow+"' type='hidden' name='labCode' value='"+labCode+"'/>&nbsp&nbsp<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+")'></div>";   
			else
				td1.innerHTML="<div align='left' id='checkOnSave'>"+testName+"<input id='labtbl#"+nRow+"' type='hidden' name='labCode' value='"+labCode+"'/></div>";	
		document.getElementsByName('isrequisitionFormNeeded')[0].value=isreqformneeded;
				td1.className='tdfonthead';
			td1.colspan="1";
			
				
			td2.innerHTML="<div align='left'>"+labName+"<input type='hidden' name='testCode' value='"+testCode+"'/></div>";
			td2.className='tdfonthead';
			td2.colspan="1";
			
			//alert(sampleComboStr);
			//alert("testType="+testType);
			if(testType=="P") // Sample or Slide Based
				td3.innerHTML="<div align='left'> <select style='width:55;'  id='site#"+nRow+"' name='site' onchange='setsiteUsingAjax(this,"+(labCode)+","+(testCode)+")'><option value='1'>NA</option><option value='2'>Both</option><option value='3'>Left</option><option value='4'>Right</option></select></div>";
			else
				td3.innerHTML="<div align='left'><select id='sampleaddtbl#"+nRow+"'  name='sampleInfo' tabindex='1' onchange='setSampleCodeUsingAjax(this,"+(labCode)+","+(testCode)+")'  >"+sampleComboStr+"</select></div>";
				
			td3.className='tdfonthead';																													
			td3.colspan="1";


				
			if(isMandatoryReq=="1")
			{ 
			
			var splitComboTextBoxValue=mandTextBoxCombo.split('&');
			var comboval=0;
			var popup="popUpDiv";
		/*	for(comboval;comboval<splitComboTextBoxValue.length;comboval++)
				{
				if(comboval>0){ 
					
					AddRowToTableAddMoreValue(splitComboTextBoxValue[comboval],mandTextBoxComboName,finalMadCode);
				}
				else
					{ 
				//alert(splitComboTextBoxValue[comboval]);
		td5.innerHTML=td5.innerHTML+"<div align='left'>"+splitComboTextBoxValue[comboval]+"</div>";
				}
				}
			if(comboval>0)
			td5.innerHTML=td5.innerHTML+" <img src='/HISInvestigationG5/hisglobal/images/addMore.jpg' id='minusButton' onclick='popup(\""+popup+"\")''> ";
			*/
			
			if (map[testco]==undefined)
			 {
				//alert("map1[testco1]"+map[testco]);
				td5.innerHTML="<div align='left' style='height:18;border-left : 4px solid "+colorcodee+"  '>&nbsp;&#8377;&nbsp;"+testr+"</div>";
			 }
			else
				{
				//alert("not map1[testco1]"+map[testco]);
				//var zeroo="-";
				td5.innerHTML="<div align='left' style='height:18; border-left : 4px solid "+colorcodee+" '> </div>";
				}
			
			map[testco] = testr;
			
			
			}
		else
			{ 
		/*	td5.innerHTML="<div align='left'>NA</div>";*/
			
			if (map[testco]==undefined)
			 {
				//alert("map1[testco1]"+map[testco]);
				td5.innerHTML="<div align='left' style='height:18;border-left : 4px solid "+colorcodee+"  '>&nbsp;&#8377;&nbsp;"+testr+"</div>";
			 }
			else
				{
				//alert("not map1[testco1]"+map[testco]);
				//var zeroo="-";
				td5.innerHTML="<div align='left' style='height:18; border-left : 4px solid "+colorcodee+" '> </div>";
				}
			
			map[testco] = testr;
			
			}
			td5.className='tdfonthead';
		td5.colspan="1";

		          var labbasedapp=document.getElementsByName('labbasedapppointment')[0].value;
		           var labbasedaptdatetime="";
		           var labbasedaptdatetimelab="";
		           //alert(labbasedapp);
		          if(labbasedapp!="")
		        	  {
		        	  labbasedaptdatetime=labbasedapp.split("#")[2];
		        	  labbasedaptdatetimelab=labbasedapp.split("#")[4];
		        //	  labbasedaptdatetimelab="10066";

		        	  }
		          
		         // alert("chanks appointment call first time");
			var paraId=labCode+"^"+testCode+"^0^0^0^0^0";
			if(labbasedaptdatetime!="" && labbasedaptdatetimelab==labCode)
				{
				td6.innerHTML="<div align='left'>"+labbasedaptdatetime+"</div>";
				
				}
			else
				if(islababsedapp=="1")
				{ 
				//	alert("call from labbaed app"+document.getElementsByName('islabcallapp')[0].value);
				var islabalreadycallapp=document.getElementsByName('islabcallapp')[0].value;
		                 
				if(islabalreadycallapp.includes(labCode))
					{
					//alert("labmatch");
					var ide=islabalreadycallapp.split("@");
					
					for(var a=0;a<ide.length;a++)
						{
						//alert("labmatchloop");
						var ff=ide[a];
						
						if(ff.includes(labCode))
							{
							//alert("match again");
							var makeid=ff.split("#")[1];
							//alert("makeid"+makeid);
							var newmakeid="freeSlotLabel"+"_"+makeid;
						//	alert("newmakeid"+newmakeid);
							var htmll=document.getElementById(newmakeid).innerHTML;
							document.getElementById(newmakeid).innerHTML=htmll;
					       //  alert("htmll"+htmll);
					       //  alert("makeid"+makeid);
					         var dateid="aptForDate_"+makeid ;
					      //  alert("zz"+document.getElementsByName(dateid)[0].value);
							var dateidvalue=	document.getElementsByName(dateid)[0].value;

					         var newdateid="aptForDate_"+divAptTagRow ;

								td6.innerHTML="<div name='"+makeid+"' id='"+divAptTagRow+"' align='left'>"+htmll+"</div><input type='hidden' name='"+newdateid+"' value='"+dateidvalue+"'/> ";

							}
						
						}
					
					
					
					}
				else
					{
					
					if(islabalreadycallapp=="")
					{
						islabalreadycallapp=labCode+"#"+divAptTagRow+"@";
					}
					else
						{
						
						islabalreadycallapp=islabalreadycallapp+labCode+"#"+divAptTagRow+"@";
						
						}
					
					paraId=labCode+"^"+'0'+"^0^0^0^0^0";

					document.getElementsByName('islabcallapp')[0].value=islabalreadycallapp;
				td6.innerHTML="<div id='"+divAptTagRow+"'></div> <div  align='left'><input type='hidden' name='dateTag' onchange='setDateInApoitment("+patCrNo+",\""+(paraId)+"\",this,\""+divAptTagRow+"\")' id='datepicker'></div>";
				$( "#datepicker" ).datepicker({
					dateFormat: 'dd-M-yy',
					showOn: "button",
					buttonImage: "/HISInvestigationG5/hisglobal/images/cal.png",
					buttonImageOnly: true,
					buttonText: "Select  "
					}).datepicker("setDate", new Date());
							//alert(td6.innerHTML);
				var paraId="";
			//	getAptSlotDetails('"+patCrNo+"','"+paraId+"','',divAptTagRow);
				
				paraId=labCode+"^"+'0'+"^0^0^0^0^0";
		        //alert("lab bsed call paraIdparaId"+paraId);
					getAptSlotDetails("\'"+patCrNo+"\'",paraId,'',divAptTagRow,4);
					
					newarrayappdatalababsed.push(labCode+"$$"+divAptTagRow);
					}
				}
				else if(isAppointment=="1")
				{ 
				
				td6.innerHTML="<div id='"+divAptTagRow+"'></div> <div  align='left'><input type='hidden' name='dateTag' onchange='setDateInApoitment("+patCrNo+",\""+(paraId)+"\",this,\""+divAptTagRow+"\")' id='datepicker'></div>";
				$( "#datepicker" ).datepicker({
					dateFormat: 'dd-M-yy',
					showOn: "button",
					buttonImage: "/HISInvestigationG5/hisglobal/images/cal.png",
					buttonImageOnly: true,
					buttonText: "Select  "
					}).datepicker("setDate", new Date());
							//alert(td6.innerHTML);
				var paraId="";
			//	getAptSlotDetails('"+patCrNo+"','"+paraId+"','',divAptTagRow);
				
				
					 paraId=labCode+"^"+testCode+"^0^0^0^0^0";

					getAptSlotDetails("\'"+patCrNo+"\'",paraId,'',divAptTagRow,2);

					
						
				}
				else
				{
				td6.innerHTML="<div align='left'>NA</div>";	
				td6.className='tdfonthead';
				td6.colspan="1";
				}
			
				var checkflag=document.getElementById('flag').value;
				
				
				//td7.innerHTML="<div  align='left' ><select  id='prioritytbl#"+nRow+"' name='priority'    tabindex='1' onchange='setPriorityUsingAjax(this,"+(labCode)+","+(testCode)+")'  ><option value='<%=InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_NORMAL%>'>Normal</option><option value='<%=InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_URGENT%>'>Urgent</option></select></div>";
				td7.innerHTML="<div  align='left' ><select  id='prioritytbl#"+nRow+"' name='priority'    tabindex='1' onchange='setPriorityUsingAjax(this,"+(labCode)+","+(testCode)+")'  ><option value='1'>Normal</option><option value='2'>Urgent</option></select></div>";
			td7.className='tdfonthead';
			td7.colspan="1";
			
			//var ind=document.getElementById((parseInt(nRow)+1));
			//alert("ind="+ind)
		   
		   // td8.className='tdfonthead';
		   // td8.colspan="1";
		   // td8.innerHTML="<div align='left'><img src='/BLDHISInvestigationG5/hisglobal/images/imgDatepicker.png' onclick ='showIndication(event,parseInt(\""+ind.rowIndex+"\"))'></div>";
			//alert(instructionss);
			if(instructionss=='null')
			{
				td8.className='tdfonthead';
				td8.colspan="2";
				
				td8.innerHTML="<div align='left'><img src='/HISInvestigationG5/hisglobal/images/minus.gif' id='minusButton' onClick='deleteRow("+(parseInt(nRow)-1)+","+(labCode)+","+(testCode)+","+(testGroupCode)+")'></div>";


					
			
			}
			else
				{
			td8.className='tdfonthead';
			td8.colspan="2";

			//td8.innerHTML="<div align='left'><img src='/HISInvestigationG5/hisglobal/images/minus.gif' id='minusButton' onClick='deleteRow("+(parseInt(nRow)-1)+","+(labCode)+","+(testCode)+")'><img  title='Show Instructions' src='/HISInvestigationG5/hisglobal/images/add_remarks_sml.jpg' onClick='showInstructions6("+"\""+(instructionss)+"\""+")'></div>";
			
			td8.innerHTML="<div align='left'><img src='/HISInvestigationG5/hisglobal/images/minus.gif' id='minusButton' onClick='deleteRow("+(parseInt(nRow)-1)+","+(labCode)+","+(testCode)+","+(testGroupCode)+")'><img  title='Show Instructions' src='/HISInvestigationG5/hisglobal/images/add_remarks_sml.jpg' onClick='showInstructions6("+"\""+(instructionss)+"\""+")'></div>";
			
				
				}
			
		    
			
		   
			tabRow.appendChild(td1);  
			tabRow.appendChild(td2);
			tabRow.appendChild(td3);
			tabRow.appendChild(td5);
			tabRow.appendChild(td6);
			tabRow.appendChild(td7);
			tabRow.appendChild(td8);
			showamount();
			

			if(checkflag!="false")
					{
					//alert(checkflag);
					var valuetable="prioritytbl#"+nRow;
					//alert(valuetable);
					document.getElementById(valuetable).value=checkflag;
					}
			
			
			 document.forms[0].numberOfRow.value=numRows;
			 $( "#datepicker" ).datepicker({
				 dateFormat: 'dd-M-yy',
				 showOn: "button",
				 buttonImage: "/HISInvestigationG5/hisglobal/images/cal.png",
				 buttonImageOnly: true,
				 buttonText: "Select  "
				 }).datepicker("setDate", new Date());
				 	 //Calendar.setup({'inputField':'requirmentDate'+(parseInt(indexVolSpecific)+1) ,'ifFormat':'%d-%b-%Y','button':(parseInt(indexVolSpecific)+1)+'requirmentDate','singleClick':true});
		var idd="sampleaddtbl#"+nRow;
		var val=document.getElementById(idd);
		//reqformshowone();
		reqformhideone();
		setSampleCodeUsingAjax(val,labCode,testCode);
		 	
		callopenmodal(ispidshow);

		}

		