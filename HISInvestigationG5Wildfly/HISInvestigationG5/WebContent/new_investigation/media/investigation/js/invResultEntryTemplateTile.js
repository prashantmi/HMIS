/*1st script tag*/
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
		},
        error: function(error)
        {
        	
        }};
	var objDojoAjax = dojo.xhrPost(objXHR) ;
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
	var classnamee=obj.className;
	var reqdno=classnamee.split("@@")[1];
	var testparaname=reqdno.split("#")[3];
     reqdno=reqdno.split("#")[0];
     var uploaddata=getuploadfiledata(reqdno,testparaname);

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
         }
}


function getuploadfiledata(reqdno,testparaname)
{

	var remarks = "";
	var flg = false;
	var labTestCodeArray = "";
	var _mode = "GETFILEUPLOADDATATESTWISE";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/invResultValidationTemplateTile.cnt?hmode="+_mode+"&requisitionDNo="+reqdno+"&testParaMeterCode="+testparaname, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			remarks = data;
		},
        error: function(error)
        {
        	
        }};
	var objDojoAjax = dojo.xhrPost(objXHR) ;
	return remarks; 
	
}

var fileByteArray = [];
function callfileupload(obj)
{
	var dd=obj.id;
   var rdd="view@@remove@@"+dd;
	
	
	var viewid="view@@"+dd;
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
		  } 
		  else
			  {
			 document.getElementById(dd).value="";
			 document.getElementById(rdd).style.display = "none";
			  }

		}
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
            }
        }
    }
    return flag;
}

function TestFileType( fileName, fileTypes ,x) {

	
	 var ext = fileName.split('.').pop();
	   
	 if(ext=="pdf"){
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
	  document.getElementsByName('fileuploaddatabase64')[0].value=reader;
	}
	
function processFile(theFile){
	  return function(e) { 
		  if (e.target.readyState == FileReader.DONE) {
	    var theBytes = e.target.result; //.split('base64,')[1]; // use with uploadFile2
	    fileByteArray.push(theBytes);
	    for (var i=0; i<fileByteArray.length; i++) {
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
 	
 var testcode=obj.name
 testcode=testcode.split("#")[3];

  testcode = testcode.substring(0, 5);


 var skillsSelect = document.getElementById(obj.id);
 var selectedText = skillsSelect.options[skillsSelect.selectedIndex].text;

     var val=checkIsparameterDependent(testcode,reqno,selectedText,reqdno);


      if(val.includes("Hyper Link"))
        {

     	 var idd=obj.name;

     	 var idd1=idd.split("#")[0];
     	 var idd2=idd.split("#")[1];
     	 var idd3=idd.split("#")[2];
     	 var idd4=idd.split("#")[3];


     	 var textareaparacode=val.split("@@@")[0];

     	 textareaparacode=textareaparacode.split("###")[1];
     	 var hyperparacode=val.split("@@@")[0];
     	 hyperparacode=hyperparacode.split("###")[1];
     	 
          var hyperfinalhide="td"+"#"+idd1+"#"+idd2+"#"+idd3+"#"+testcode+hyperparacode;
          var hyperfinalhidee=idd1+"#"+idd2+"#"+idd3+"#"+testcode+hyperparacode;

          var textareafinalhide=idd1+"#"+idd2+"#"+idd3+"#"+testcode+textareaparacode;

			if(selectedText=="Positive")
         {   

               document.getElementById(hyperfinalhide).style.display = "";


         }
           else
               {

             var frt=hyperfinalhide.split("#");
             var idfordelete1="idd"+frt[1]+"#"+frt[2]+"#"+frt[3]+"#"+frt[4];

                if(document.getElementById(idfordelete1)!=null)
             document.getElementById(idfordelete1).innerHTML  = "";

               document.getElementById(hyperfinalhide).style.display = "none";
               document.getElementById(hyperfinalhidee).style.value = "";

               var idfordelete="divId_"+frt[1]+"#"+frt[2]+"#"+frt[3]+"#"+frt[4];
               
               document.getElementById(idfordelete).innerHTML  = "";
               
               document.getElementsByName('hyperLinkTableSession')[0].value=""
               document.getElementsByName('selectValuemapping')[0].value="";
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
  		},
          error: function(error)
          {
          	
          }};
  	var objDojoAjax = dojo.xhrPost(objXHR) ;

  	return remarks; 
  	
  }
  
 
function newtestraised(val)
{
   var	concatenateChkBoxVal=val;

	var mode="GETPATDTL";
	var url1="/HISInvestigationG5/new_investigation/invResultEntryTemplateTile.cnt";
	concatenateChkBoxVal = concatenateChkBoxVal.replace(/#/g, "$");

	mywindow=window.open (url1+"?hmode="+mode+"&selectedCheckbox="+concatenateChkBoxVal,"_blank","scrollbars=1,directories=no, status=no,width=1100, height=600,top=100,left=250");


}

function ff()
{
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

     	 overalltest+=(document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[6]+";";
     	 
   }


      for(var a=0;a<document.getElementsByName("chkResultEntryPatient").length;a++)
      { 

     	 var valuess=(document.getElementsByName("chkResultEntryPatient")[a].value).split("#");

     	 var reqNo=valuess[1];
     	 var testCode=valuess[3];
     	 var testName=valuess[17];
     	 var labCode=valuess[5];
     	 var labName=valuess[18];
     	 var dNo=valuess[2]; 	 	   	 		
     	 
      var table = document.getElementById("tbll");

                   	  var mappedcheckedtest=checkreqformTestType(testCode);
                       var mappedTest=mappedcheckedtest.split("#")[2];
                       var formtype=mappedcheckedtest.split("#")[1];


                       if(mappedTest=="0")
              		 {
                             // not match
                             var rowCount = table.rows.length;

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
                       		    var rowCount = table.rows.length;

            var row = table.insertRow(rowCount);
            
      		
            if(mappedTest.includes("@"))
         		row.id = testCode+"#"+reqNo;
        	   else
        		   row.id = mappedTest+"#"+reqNo;

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
                                       
                     			  if(checktest.includes(mappedTest)) //find dervied test
                              	   {
 //
                                	   //  alert("match test");

                     				  var flag="2";
                                     //    alert("match"+matchtest);
                                        reqformtestnames+=";"+testName;
                                         reqformtestcodes+=";"+testCode;
                                         var tr ="";

                                              if(document.getElementById(mappedTest+"#"+reqNo)==null && document.getElementById(testCode+"#"+reqNo)==null)
                                                  {
                                                      // alert("req no. change ");

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
                                         				
                                         				var td = tr.getElementsByTagName("td");
                                         				
                                         				if((document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[27]=="1")
                                                        {	
                                         				for(var i=1;i<td.length;i++) {
                                         					var namee=td[0].innerHTML+",";
                                         					var nameee=td[0].innerHTML+";";

                                         					namee+=testName;
                                         					nameee+=testName;
                                         					reqformtestnames=nameee;

                                                            td[0].innerHTML=namee;

                                         				}
                                                        }

                                         				checktest+=testCode+"#"+mappedTest+"#";
                                                  }	
                             	   }
                     			  
                     			  else // not find
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

                     		           		checktest+=testCode+"#"+mappedTest+"#";
                     		               }

                         			  }
                                   }
                     			  else // find @ master form
                         			  {


                     				 var flag="2";

                                         matchtest=testCode;
                                        reqformtestnames+=";"+testName;
                                         reqformtestcodes+=";"+testCode;
                                         if(document.getElementById(matchtest+"#"+reqNo)==null)
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
                                         				
                                         				var td = tr.getElementsByTagName("td");
                                         				
                                         				if((document.getElementsByName("chkResultEntryPatient")[a].value).split("#")[27]=="1")
                                                        {	
                                         				for(var i=1;i<td.length;i++) {
                                         					var namee=td[0].innerHTML+",";
                                         					var nameee=td[0].innerHTML+";";

                                         					namee+=testName;
                                         					nameee+=testName;
                                         					reqformtestnames=nameee;

                                                            td[0].innerHTML=namee;
                                         				}
                                                        }
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
 	var objXHR = {url: "/HISInvestigationG5/new_investigation/invResultEntryTemplateTile.cnt?hmode="+_mode+"&testCodee="+TestCode, sync:true, postData: "", handleAs: "text",
 		load: function(data) 
 		{
 			//alert("checkreqformTestType"+data);
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
 		var patientType = document.getElementsByName("sampleAreaCode")[0].value.split("#")[1];
 		document.getElementsByName("patientType")[0].value = patientType;

 		document.getElementsByName("sampleAreaName")[0].value=document.getElementsByName("sampleAreaCode")[0].options[document.getElementsByName("sampleAreaCode")[0].selectedIndex].text;
 		document.getElementsByName("isSampleAreaSelected")[0].value="1";
 		
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

 	selectSNOMEDCTauto('ACTIVE',semantictag,'SYNONYMS','10','null',elmtId,callbck_index);
 	$("#conceptdiv_1").hide();
 	$("#norecorddiv_1").hide();
 	}

 function setValue(selectedSNOMEDTerm)
 {

 	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
 		{

 		var str=selectedSNOMEDTerm.term;
 		var str1=selectedSNOMEDTerm.conceptId;

 	 var snomedname="txt-snomed-ct-search_"+snomedidd;
 	 	 var snomedcode="txt-snomed-ct-search_code"+snomedidd;
 	 document.getElementById(snomedname).value=str;
 	    document.getElementById(snomedcode).value=str1;

 		}
 	else
 		{
 		document.getElementById(snomedname).value="-";
 		document.getElementById(snomedcode).value="";

 		}
 }

 function onComment()
 {

	 	document.getElementsByName('currentElement')[0].value='TA';
 }
 
 
 function checkBillingAddendumTest1(valuee)
 {
 	var autoGenFormate = "";
 	var resultEntryTemplateValu="";
    var values="";
 	
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
 
 
 function checkBillingAddendumTest(obj)
 {
 	var autoGenFormate = "";
 	var resultEntryTemplateValu="";
    var values="";
 	
 	 values=obj.value;
      for(var j=0;j<values.length;j++)
          {
     values=values.replace("#","@");
          }

 	var flg = false;
 	
 	var _mode = "AJX_CHECK_BILLING";
 	var objXHR = {url: "/HISInvestigationG5/new_investigation/invResultEntryTemplateTile.cnt?hmode="+_mode+"&selectedCheckbox="+values, sync:true, postData: "", handleAs: "text",
 		load: function(data) 
 		{
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

 	var url1="/HISInvestigationG5/new_investigation/requisitionformprocess.cnt";
    var menuu="Requisition Form";
    var url=url1+"?testCode="+testCode+"&testName="+testName+"&labCode="+labCode+"&labName="+labName+"&hmode="+hmode+"&status="+status+"&requisitionDNo="+requisitionDNo+"&reqformtestnames="+reqformtestnames+"&reqformtestcodes="+reqformtestcodes;
 	callMenu(url,menuu);
 }     

	function callMenu(url,menu)
	{

		var selMenu=menu;
		parent.ajaxStartMenu();
		menu=menu.toString().replace(/_/g,' ').replace(/XXY/g,'/').replace(/XXX/g,'&');

		parent.callMenu(url,menu);
		$("#"+selMenu).css("color","#6D00D5");
		parent.ajaxCompleteMenu();
		
	}

	            	
function hyper(thiss,url1)
{

	var status="2";

	var matchid=thiss.id;
	var values= matchid.split('#');

     var echodata=  getechodata(values[0]);

     
	mywindow=window.open (url1+"?requisitionDNo="+values[0]+"&showStatus="+status+"&testParaCode="+values[3]+"&echodata="+echodata,"_blank","scrollbars=1,directories=no, status=no,width=1000, height=550,top=150");
		
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

	var cbs =document.getElementsByName('chkResultEntryPatient');
	for(var i=0; i < cbs.length; i++)
		{
		
	var checkBoxId=cbs[i].id;
	var values=document.getElementsByName('chkResultEntryPatient')[i].value;

    var labcode=values.split("#")[5];
     

	var splitTheCheckBoxId=checkBoxId.replace('chkBOx', '');

	var n=0;
	          for(n;n<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea").length;n++)
	     	       {
				   get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea");
	            var name1=get_tags[n].name;

				if(val==name1)
					{
	  var id1 = document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].id;
	 	           var editor = CKEDITOR.instances[id1];
					 
	 	         
	 	           if(editor!=null){
	 	        	 
	 	        	  	              
	               resultEntryTemplateValue=editor.getData();
	               
	               if(resultEntryTemplateValue.length>12000)
	            	  {
	            	  alert("Editor Data limit is 12000, including spaces. Please don't exceed the limit");
	            	  return false;
	            	  
	            	  
	            	  }
				  }
					}
				  }
			 }

	
     val=val.split("#")[3];
	var flg = false;
	resultEntryTemplateValue = resultEntryTemplateValue.replace(/&/g,"$");
	resultEntryTemplateValue.toString();

	var _mode = "AJX_INSERT_MODIFY_CANNEDDETAILS";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/invResultEntryTemplateTile.cnt?hmode="+_mode+"&cannedCode="+code+"&cannedName="+name+"&cannedValue="+val+"&cannedContent="+resultEntryTemplateValue+"&modifycannedLabCode="+labcode, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
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

function showInstructions5(inst)
{
	
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
	
	var nRow=0;
	var tableObj=document.getElementById('allInstructions');
	
	var tr=document.createElement("TR");
	 
	tableObj.appendChild(tr);
	var numRows=tableObj.rows.length;
		nRow=numRows;
 
	var tabRow=tableObj.insertRow(numRows);
	tabRow.id=parseInt(nRow);
	 
	var td1=document.createElement("TD");
 
	td1.innerHTML="<div  align='left' >"+inst+"</div>";   
	td1.className='tdfont';
	td1.width="1";
	
	
   
	tabRow.appendChild(td1); 
	}
	
	

function deleteTableInst()
{
	
	for(var i = document.getElementById("allInstructions").rows.length-1; i > 0; i--)
	{
		document.getElementById("allInstructions").deleteRow(i);
		
	}
	
}



var sizeee=0;
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

	 document.getElementById("divfile"+finalsampleno).style.display='';
	var table = document.getElementById("filetable"+finalsampleno);
	var table1 = document.getElementById("filerow"+finalsampleno);

 var sizee=document.getElementsByName("sizeOfFile").value;


var row = table1.insertCell(filecounter);
var f=1;
var finalreqno=reqno+""+reqno1;
var crreqnoFile1=crno+"#"+finalreqno;

row.innerHTML = "<font color='#FF0000' size='4'>"+"File "+filecounter+":</font><input type='file' name='file["+sizeee+"]'><input type='hidden' name='crreqnoFile' value='"+crreqnoFile1+"'>";


document.getElementById('hidetbl'+finalsampleno).style.display='';
document.getElementsByName("sizeOfFile").value++;
sizeee++;
}

 
 function hidefile(crno,reqno,reqno1,sampleno,sampleno1)
 {
	 var filecounter = counterMap.get(crno+"#"+reqno+reqno1+"#"+sampleno1);
	var finalsampleno=sampleno+""+sampleno1;
 if(filecounter>1)
	{
	 //filecounter--;
	 document.getElementById("filerow"+finalsampleno).deleteCell(filecounter);
	 sizeee--;
	}
if(filecounter==1)
	{
	document.getElementById("divfile"+finalsampleno).style.display='none';
	document.getElementsByName("hideminus")[0].style.display='none';
	} 

	 filecounter--;
	 counterMap.set(crno+"#"+reqno+reqno1+"#"+sampleno1,filecounter);
 }	 
 
CKEDITOR.config.keystrokes = [];
setInterval(function() {
    
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
 
 var genTypeValue=document.getElementsByName('generationType')[0].value;
 
 
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

function displaySamplePatDetails()
{	
	var count=0;
	document.getElementsByName('isPatDetailPage')[0].value="1";
	
	var concatenateChkBoxVal="";
	var cbs =document.getElementsByName('chkSamplePatient');
	//alert(cbs);
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
	//alert(concatenateChkBoxVal);
	document.getElementsByName('selectedCheckbox')[0].value=concatenateChkBoxVal;
	document.getElementsByName('hmode')[0].value='SHOWPATDETAILS';
	
 	document.forms[0].submit();
 	
	}
function ValidateSameCrNo(obj)
{

	var isbilled="";
    var value=obj.value.split("#");
     var name=obj.name;

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
            //document.getElem
            entById('nextDiv').style.display="none";
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
			    		 

			    	} 
				}
		}
	}
		
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
		 
	for(var i=0; i < cbs.length; i++)
	{
	if(cbs[i].checked)
    	{

			empCode = "0"
		
		document.getElementsByName('chkResultEntryPatient')[i].value = document.getElementsByName('chkResultEntryPatient')[i].value + "#" +empCode 

        var commentsboxdata=document.getElementsByName('chkResultEntryPatient')[i].value;
		
        if(commentsboxdata.split("#")[19]!="NA")
            {

        	var sampleid=commentsboxdata.split("#")[20];
    		sampleid=sampleid.split("/")[0];
    		
        commentsboxdata=commentsboxdata.split("#")[0]+"#"+commentsboxdata.split("#")[1]+"#"+sampleid;
            }else
        	commentsboxdata=commentsboxdata.split("#")[0]+"#"+commentsboxdata.split("#")[1]+"#";    
        if(document.getElementById(commentsboxdata)!=null  )
            {
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
		
		count++;	
	        var k=0;

        	 var checkBoxId=cbs[i].id;
        	 
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
              	  checkboxcheck="checkbox";              		
              		
              		if(typ!=hidddentext)
                   {
                  	 splitTemplateValue=name.split("#");
                   
                   reqNO.push(splitTemplateValue[0]);
                    parameterValue=splitTemplateValue[3];

                    parameterCode.push(parameterValue.substring(0, 5));

                    parantParameter.push(parameterValue.substring(9,18));
                  
                      
                    var resultValidationTemplateValue="";

                    if(id.indexOf('chkbox')== -1 )
                        {

                           if(typ=='checkbox')
                            {
                                if(document.getElementById(id).checked==true)
                                    {
                                	resultValidationTemplateValue="1";}
                                if(document.getElementById(id).checked==false)
                                    {
                                	resultValidationTemplateValue="0";}
                            }
                           else if(typ=='file')
                               {
                               
                               var x = document.getElementById(name);
              				 var file = x.files[0];

              		       
                            if( file!=undefined)
                        	   resultValidationTemplateValue="File Uploaded";

                               }
                           else    
                      resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value;
                           //added by krishnan nema on 16/10/2018
                       
                           var check23 = checkReservedCahracters(resultValidationTemplateValue);
                       	  if(check23==true){
                   			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
                   			return;
                           	}else{
                   			
                               } 
               	     resultValidationTemplateValue = removeSpecialCharacter(resultValidationTemplateValue);
               	   
                         var orderingValue= document.getElementsByName(name+"#order")[0].value;
                          name+='#'+resultValidationTemplateValue+'#'+values+"#"+"-"+"#"+orderingValue;
                      concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                       concatenateChkBoxVal+='@';
                     //  alert(concatenateChkBoxVal);
                        }
                   }
                          
                          
		          
		          
	             }

        var j=0;
         for(j;j<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select").length;j++)
    	       {
        	 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;

        	  get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select");
       	   name=get_tags[j].name;
        	 
        
              var multiValue="";
              var resultEntryTemplateValue;
	        if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[0].value!="-1")
              for (var kk=0;kk<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length ;kk++)
	        	  {
	        	 if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].selected==true)
	        	  { 
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
         
         var n=0;
          for(n;n<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea").length;n++)
     	       {
    	       
 	             
        	  var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
        	  
 	             get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea");
 	        	   name=get_tags[n].name;
 	         
 	         var id1 = document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].id;
 	           var editor = CKEDITOR.instances[id1];
 	       
 	         
 	           if(editor!=null){
 	        	               
              var resultEntryTemplateValue=editor.getData();
          
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
              var orderingValue= document.getElementsByName(name+"#order")[0].value;
              
	          name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-"+"#"+orderingValue;
                
 	          }

 	          else
 	        	  {
     	         var resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value;
     	         //added by krishnan nema on 16/10/2018
     	     
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
                       
                 if(document.getElementsByName('hyperLinkTableSession')[0].value=="")
               {
         	 // alert("true");
         	  var ide=id;
         	  ide="divId_"+id;
         	  var ider="idd"+id;

         	  if(document.getElementById(ider)!=null && document.getElementById(ider).innerHTML!='')
         	  {
             	  
         	  var idee1=id.split("#")[0]+"$$";
         	   idee1+=id.split("#")[3]+"$$";

     		  resultEntryTemplateValue="hyperchanks" ;

         	  }
     	    
         	  else if(document.getElementById(ide)!=null && document.getElementById(ide).innerHTML!='')
             	  {           	  
             	  var idee=id.split("#")[0]+"$$";
             	   idee+=id.split("#")[3]+"$$";
         		  resultEntryTemplateValue="hyperchanks" ;
             	  }
         	    
               }
            		var tocheckfnctn=document.getElementById(id).onclick;
            		var valuoffuncntn=tocheckfnctn;
            		valuoffuncntn=valuoffuncntn.toString();
            		
            		if(valuoffuncntn.includes("echo"))
                      {
                           name+=id+"#"+resultEntryTemplateValue+"#"+values+"#-"+"#"+orderingValue;
                        }
                    else
                        {
                        name+=id+"#"+resultEntryTemplateValue+"#"+values+"#hyperlink"+"#"+orderingValue;

                        }
               
                  concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                  concatenateChkBoxVal+='@';
                 }
                 }
           
	          
            }
        
       
         
         }
   
    
     }
	

	//alert("Finally concatenateChkBoxValues For Save "+concatenateChkBoxVal);
	
	document.getElementsByName('resultEntryTemplateValueWithHash')[0].value=concatenateChkBoxVal;

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

	for(var i=0; i < cbs.length; i++)
	{
	if(cbs[i].checked)
    	{

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
  
		count++;	
	        var k=0;

        	 var checkBoxId=cbs[i].id;
        	 
        	 var splitTheCheckBoxId=checkBoxId.replace('chkBOx', '');
         	
        	   for(k;k<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input").length;k++)
        	    {
             	
                 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
              //   alert(values);
                 get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input");
                   name=get_tags[k].name;
                  // alert(name);
                   id=get_tags[k].id;
                 //  alert(id);

                   hiddenid="hiddenid"+id;
                   defaultid="default"+hiddenid;
              	  typ=get_tags[k].type;
              	 // alert(typ);

              	  hidddentext="hidden";
              	  checkboxcheck="checkbox";              		
              		
                   if(typ!=hidddentext)
                   {
                  	 
                 // 	 alert("not hidden");
                   splitTemplateValue=name.split("#");
                   
                   reqNO.push(splitTemplateValue[0]);
                    parameterValue=splitTemplateValue[3];
              //    alert(parameterValue);
                    parameterCode.push(parameterValue.substring(0, 5));
                //    alert( parameterCode.push(parameterValue.substring(0, 5)));
                    parantParameter.push(parameterValue.substring(9,18));
                  
             var resultValidationTemplateValue="";

                    //chandan comment auto    
     
                    if(id.indexOf('chkbox')== -1 )
                        {

                           if(typ=='checkbox')
                            {
                                if(document.getElementById(id).checked==true)
                                    {
                                	resultValidationTemplateValue="1";}
                                if(document.getElementById(id).checked==false)
                                    {
                                	resultValidationTemplateValue="0";}
                            }
                           else if(typ=='file')
                           {

                          	  var x = document.getElementById(name);
                 				 var file = x.files[0];

                 		       var uploadid="view@@"+name;
                    		                 		       
                               if( file!=undefined)
                         resultValidationTemplateValue="File Uploaded";
                               else if( document.getElementById(uploadid)!=null && document.getElementById(uploadid).className !=null)
                                   resultValidationTemplateValue="File Uploaded";
                 
                                  }
                           else    
                      resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value;

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

        var j=0;
         for(j;j<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select").length;j++)
    	       {
        	 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;

        	  get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select");
       	   name=get_tags[j].name;
        	  
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
        	  
 	             get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea");
 	        	   name=get_tags[n].name;
 	         	
 	         var id1 = document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].id;
 	           var editor = CKEDITOR.instances[id1];
 	       
 	         
 	           if(editor!=null){

              
              var resultEntryTemplateValue=editor.getData();
		
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

         	  if(document.getElementById(ider)!=null && document.getElementById(ider).innerHTML!='')
         	  {
             	  
         	  var idee1=id.split("#")[0]+"$$";
         	   idee1+=id.split("#")[3]+"$$";

     		  resultEntryTemplateValue=document.getElementById(ider).innerHTML ;
     		  
         	  }
     	    
         	  else if(document.getElementById(ide)!=null && document.getElementById(ide).innerHTML!='')
             	  {
             	  var idee=id.split("#")[0]+"$$";
             	   idee+=id.split("#")[3]+"$$";
         		  resultEntryTemplateValue=document.getElementById(ide).innerHTML ;
         		  

             	  }
         	 else if(document.getElementById(ide)!=null && document.getElementById(ide).innerHTML!='')
        	  {
      	  
        	  var idee=id.split("#")[0]+"$$";
        	   idee+=id.split("#")[3]+"$$";
    		  resultEntryTemplateValue="hyperchanks" ;
    		 
        	  }
         	    
               }


                
 			  if(id.includes("view@@")==false) // not to add file upload view hyperlink option by chandan 
              {

 					var tocheckfnctn=document.getElementById(id).onclick;
            		var valuoffuncntn=tocheckfnctn;
            		valuoffuncntn=valuoffuncntn.toString();
            		
                     if(valuoffuncntn.includes("echo"))
                      {

                           name+=id+"#"+resultEntryTemplateValue+"#"+values+"#-";
                           


                           
                        }
                    else
                        {
                        name+=id+"#"+resultEntryTemplateValue+"#"+values+"#hyperlink";

                        }
                   
                  concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                  concatenateChkBoxVal+='@';
              }
   
                 }
                 }
           
	          
            }
        
       
         
         }
   
    
     }
	

	document.getElementsByName('resultEntryTemplateValueWithHash')[0].value=concatenateChkBoxVal;
	
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


/*Script was removed from this place and added to jsp*/
  
  
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
 document.getElementsByName('hmode')[0].value='GETTYPEWISEDETAIL';
	document.forms[0].submit();

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
	
	if(document.getElementsByName("searchBy")[0].checked==true) 
	{
	 	document.getElementsByName("searchBy")[0].value=="1";
	 	document.getElementsByName("searchBy")[1].checked=false;
		
		showReqDate();
	}else{
		
		document.getElementsByName("searchBy")[0].value=="0";
	 	document.getElementsByName("searchBy")[0].checked=false;
	  	showCollDate();

	} 
	
	var genTypeValue=document.getElementsByName('generationType')[0].value;
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
	
	var mode='PRINTREPORT';
var reportName = name;


var url = '/HISInvestigationG5/new_investigation/invResultEntryTemplateTile.cnt?hmode='+mode+"&fileName="+name;

AddRowToTableAddReportValues(url);
popup('popUpDiv');



}




function AddRowToTableAddReportValues(fileurl) {

var nRow = 0;
var tableObj = document.getElementById('reportTable');
var numRows = tableObj.rows.length;

if (document.getElementById("setPdf") != null) {

document.getElementById("setPdf").remove();

}


nRow = numRows;

var tabRow = tableObj.insertRow(numRows);
tabRow.id = parseInt(nRow);

var td1 = document.createElement("TD");

td1.innerHTML = "<iframe id='setPdf' src='"+ fileurl + "' width='100%' height='450px' type='application/pdf'    ></iframe> ";
td1.className = 'tdfont';
td1.colspan = "1";

tabRow.appendChild(td1);


}


/*2nd script tag*/

var isChecked = false;




function allSelected()
{
	var cbs =document.getElementsByName('chkSamplePatient');

        if(document.getElementById('selectAllCheckbox').checked==true)
            {
	for(var i1=0; i1 < cbs.length; i1++)
		{

		document.getElementsByName('chkSamplePatient')[i1].checked=true;
		var value=document.getElementsByName('chkSamplePatient')[i1].value;

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
            
                    return false;                 
               }
           else
               {
        	   document.getElementById('nextDiv').style.display="";
               }
	

}



/*3rd script tag*/

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
    

 }  
//macro
function callMacroList(data){
 $(function() {
 	      
 	           var macroCodes=data;
 	          
                      
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


function showLegends(){
	  document.getElementById("divLegends").style.display="block"; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}


$(document).ready(function()
		{
for(var inst in CKEDITOR.instances) {


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

	document.getElementById("divfromDateControl").style.display="none";
	document.getElementById("divfromDate").style.display="none";
	document.getElementById("divToDate").style.display="none";
	document.getElementById("divToDateControl").style.display="none";
	
	document.getElementById("divfromCollDateControl").style.display="";
	document.getElementById("divfromCollDate").style.display="";
	document.getElementById("divToCollDateControl").style.display="";
	document.getElementById("divToCollDate").style.display=""; 


}

 function callGetDetails()
 {
	 document.getElementsByName('hmode')[0].value='GETDETAILS';
	 document.forms[0].submit();
 }

 
 /*4th script tag*/
 
 /* added by prashant */
 function setIndication(){
 	 return;

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

 