function submitPage(mode) {
	alert(mode);

	var hmode = "GETDETAILS"; // create function in action file, UTIL,...... with query and populate the list in session and rediredt to NEW in ACTION
	document.getElementsByName('hmode')[0].value = hmode;
	document.forms[0].submit();

}

function validateFinalSubmit() {

	// These All Fields are Mandatory

	////////alert(document.forms[0].sampleName);
	//////////alert(document.forms[0].sampleName.value);
	alert("aaa");
	// alert(document.getElementsByName("patCRNo")[0].value);
	if (document.getElementsByName("patCRNo")[0].value == "") {
		alert("Enter CR no Name ... ");
		//document.forms[0].sampleName.focus();
		document.getElementsByName("patCRNo")[0].focus();
		return false;
	}

	return true;
}

function finalSubmit(mode) {
	if (!validateFinalSubmit())
		return;
	submitPage(mode);

}

function clearForm() {

	document.getElementsByName('sampleName')[0].value = "";

}



//1st

$(document).ready( function () {

	if(document.getElementsByName("chkSamplePatientOnSave")!=undefined)
	   for(var a=0;a<document.getElementsByName("chkSamplePatientOnSave").length;a++)
	   {   	
	        document.getElementsByName("chkSamplePatientOnSave")[a].checked=true;
	      	onClickSavecheckedgrp(document.getElementsByName("chkSamplePatientOnSave")[a],a,"1"); 	            	      	 
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


//2nd 

//just for the demos, avoids form submit
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



//3rd


var reqformtestnames="";
var reqformtestcodes="";



function reasonextrashw(obj,index)
{
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
   
                                     if(checktest.includes(mappedTest)) //find dervied test
                             	   {

                    				  var flag="2";

                                       reqformtestnames+=";"+testName;
                                        reqformtestcodes+=";"+testCode;
                                        var tr ="";

                                             if(document.getElementById(mappedTest+"#"+reqNo)==null && document.getElementById(testCode+"#"+reqNo)==null)
                                                 {

                                                      var rowCount = table.rows.length;

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


function checkreqformTestType(TestCode)
{
		
	var flg = false;
	var remarks = "";
	var _mode = "AJX_CHECK_REQFORM_TESTTYPE";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/sampleCollection.cnt?hmode="+_mode+"&testCode="+TestCode, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			remarks = data;
			flg = true;
		},
        error: function(error)
        {
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return remarks;
}

function showSearchDiv(obj)
{

	if(obj.value==-1)
	{
		document.getElementById('goButton').style.display="none";
	}
	else
	{
		document.getElementsByName("sampleAreaName")[0].value=document.getElementsByName("sampleAreaCode")[0].options[document.getElementsByName("sampleAreaCode")[0].selectedIndex].text;
		document.getElementsByName("isSampleAreaSelected")[0].value="1";
		
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
	   tr = document.getElementById(newtest+"#"+reqNoo);
		 }
	 else
		 {
   	 tr = document.getElementById(idd);
		 }
	 var td = tr.getElementsByTagName("td");
var nameee=td[0].innerHTML;
testName=nameee;

   labName=tr.value;
	
	 var status=1;
	var hmode="EXISTINGREQUISITIONFORMDATA";
	//alert(testCode);
	var url1="/HISInvestigationG5/new_investigation/requisitionformprocess.cnt";
	mywindow=window.open (url1+"?testCode="+testCode+"&testName="+testName+"&labCode="+labCode+"&labName="+labName+"&hmode="+hmode+"&status="+status+"&requisitionDNo="+requisitionDNo,"_blank","scrollbars=1,directories=no, status=no,width=1000, height=500,top=200,left=500");
}

function showInstructions5(inst,collInst)
{
	
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
	
	var nRow=0;
	var tableObj=document.getElementById('allInstructions');
	
	var tr=document.createElement("TR");
	 
	tableObj.appendChild(tr);
	var numRows=tableObj.rows.length;
		nRow=numRows;
 
	var tabRow=tableObj.insertRow(numRows);
	tabRow.id=parseInt(nRow);
	 
	var td1=document.createElement("TD");
var td2=document.createElement("TD");
 
	td1.innerHTML="<div  align='left' >"+inst.split('#')[0]+"</div>";   
	td1.className='';
	td1.width="1";
	
	td2.innerHTML="<div  align='left' >"+inst.split('#')[1]+"</div>";   
	td2.className='';
	td2.colspan="1";
	   
   
	tabRow.appendChild(td1); 
	tabRow.appendChild(td2);  
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
	try{
		parent.jQuery.fancybox.getInstance().close();
	} catch(err){     
		if(err.name=="TypeError"){
			window.parent.closeTab();
		}
	}
	
}
	
function displaySamplePatDetails()
{	


	document.getElementsByName('showStatus')[0].value='1';
	var count=0;
	document.getElementsByName('isPatDetailPage')[0].value="1";
	var concatenateChkBoxVal="";
	var cbs =document.getElementsByName('chkSamplePatient');
	for(var i=0; i < cbs.length; i++) {
	
    	
    	if(cbs[i].checked)
    	{
    		
    	count++;	
    	concatenateChkBoxVal =concatenateChkBoxVal.concat(cbs[i].value);
    	concatenateChkBoxVal+='@';
    	 }	  
 
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

function handleResponse()
{
	if(request.readyState == 4)
	{
		if(request.status == 200)
		{
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
		
		
		document.getElementById("showthis").style.display="";
		document.getElementById("showthi_"+k).style.display="";
		document.getElementById("showth_"+k).style.display="";
		document.getElementById("showthi").style.display="";

	       if(classs!="0" )  
	       {

	      
		for(var p=0;p<document.getElementsByName("accepted").length;p++)
		 {

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
	
}




function CheckAutoLabNoFormate(LabCode,TestCode ,config,sampleareacode)
{

	//alert("inside Ajax"+LabCode+TestCode+config);
	//alert(document.getElementsByName("patType")[0].value);
	var flg = false;
	var autoGenFormate = "";
	var _mode = "AJX_CHECK_AUTO_SAMPLENO_GEN";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/invPatientAcceptanceResp.cnt?hmode="+_mode+"&labCode="+LabCode+"&tmpTestCode="+TestCode+"&config="+config+"&sampleAreaCode="+sampleareacode, sync:true, postData: "", handleAs: "text",
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
	var objXHR = {url: "/HISInvestigationG5/new_investigation/invPatientAcceptanceResp.cnt?hmode="+_mode+"&strDailyLabNo="+labNoConfiguration, sync:true, postData: "", handleAs: "text",
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


//End AjaxFunctions

function setHiddenValue(obj){
	//alert("hit"+obj);
	document.getElementsByName("selectedmachineCode")[0].value = obj;
}


//4th

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


//5th
