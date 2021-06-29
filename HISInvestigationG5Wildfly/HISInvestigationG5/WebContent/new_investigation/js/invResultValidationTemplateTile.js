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
	 
         }
}


function getuploadfiledata(reqdno,testParaMeterCode)
{
	
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



function call()
	{
               //alert("call");
               
               
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
	               
                        }
	                     
	                   }
           
          
       
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
     for(var a=0;a<document.getElementsByName("chkResultValidationPatient").length;a++)
 {
    	// alert(a);
    	 //alert(document.getElementsByName("chkResultValidationPatient")[a].value);
    	 //alert((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[6]);
    	 overalltest+=(document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[6]+";";
    	 
  }


     for(var a=0;a<document.getElementsByName("chkResultValidationPatient").length;a++)
     { 
                //alert(a+"len"+document.getElementsByName("chkResultValidationPatient").length);
    	 var valuess=(document.getElementsByName("chkResultValidationPatient")[a].value).split("#");

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


           if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[27]=="1")
               {
                var cell1 = row.insertCell(0);
           		var cell2 = row.insertCell(1);
           		cell1.innerHTML = (document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[17];

           		if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[27]=="1")
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


           if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[27]=="1")
               {
           var cell1 = row.insertCell(0);
           		var cell2 = row.insertCell(1);
           		cell1.innerHTML = (document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[17];

           		if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[27]=="1")
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
                 

                                  		           if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[27]=="1")
                                  		               {
                                  		           var cell1 = row.insertCell(0);
                                  		           		var cell2 = row.insertCell(1);
                                  		           		cell1.innerHTML = (document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[17];

                                  		           		if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[27]=="1")
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
                                        				
                                        				if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[27]=="1")
                                                       {	
                                        				for(var i=1;i<td.length;i++) {
                                        					var namee=td[0].innerHTML+",";
                                        					var nameee=td[0].innerHTML+";";
                                                            // alert(td[1].innerHTML);
                                        					namee+=testName;
                                        					nameee+=testName;
                                        					reqformtestnames=nameee;
                                        					
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


                    		           if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[27]=="1")
                    		               {
                    		           var cell1 = row.insertCell(0);
                    		           		var cell2 = row.insertCell(1);
                    		           		cell1.innerHTML = (document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[17];

                    		           		if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[27]=="1")
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
                                    	  
                         		           row.name=testName;
                         		           row.value=labName;
                               				row.testt=testCode;
                         		           reqformtestnames+=testName;
                         		           reqformtestcodes+=testCode;


                         		           if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[27]=="1")
                         		               {
                         		           var cell1 = row.insertCell(0);
                         		           		var cell2 = row.insertCell(1);
                         		           		cell1.innerHTML = (document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[17];

                         		           		if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[27]=="1")
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
                                        				
                                        				if((document.getElementsByName("chkResultValidationPatient")[a].value).split("#")[27]=="1")
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
	var objXHR = {url: "/HISInvestigationG5/new_investigation/invResultValidationTemplateTile.cnt?hmode="+_mode+"&testCodee="+TestCode, sync:true, postData: "", handleAs: "text",
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



function displaySamplePatDetailsPopUp(val)
{	
	//document.getElementsByName('chkSamplePatient1')[0]

	var res = val.replace(/#/g, "%23");
     res = res.replace(/@/g, "%40");
	//document.getElementsByName('hmode')[0].value='SHOWPATDETAILS';
	
 //	document.forms[0].submit();

 	var hmode="SHOWPATDETAILSPOPUP";
	//alert(testCode);
	
	var url1="/HISInvestigationG5/new_investigation/invResultValidationTemplateTile.cnt";
	//url1=url1+"?isPatDetailPage="+isPatDetailPage+"&selectedCheckbox="+res+"&hmode="+hmode;
	var isPatDetailPage="1";
	var ispreview="2";
	mywindow=window.open (url1+"?isPatDetailPage="+isPatDetailPage+"&selectedCheckbox="+res+"&ispreview="+ispreview+"&hmode="+hmode,"_blank","scrollbars=1,directories=no, status=no,width=700, height=500,top=200,left=500");
	//mywindow=window.open (url1+"?selectedCheckbox="+concatenateChkBoxVal+"&isPatDetailPage="+isPatDetailPage+"&hmode="+hmode,"_blank","scrollbars=1,directories=no, status=no,width=700, height=500,top=200,left=500");
     //var menuu="Result Validation";
	//callMenu(url1,menuu);
	
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
	var url=url1+"?testCode="+testCode+"&testName="+testName+"&labCode="+labCode+"&labName="+labName+"&hmode="+hmode+"&status="+status+"&requisitionDNo="+requisitionDNo+"&reqformtestnames="+reqformtestnames+"&reqformtestcodes="+reqformtestcodes;
	var menuu="Requisition Form";
	callMenu(url,menuu);
	//mywindow=window.open (url1+"?testCode="+testCode+"&testName="+testName+"&labCode="+labCode+"&labName="+labName+"&hmode="+hmode+"&status="+status+"&requisitionDNo="+requisitionDNo,"_blank","scrollbars=1,directories=no, status=no,width=1000, height=500,top=200,left=500");
}     


function setIndication(){
	 var indication = document.getElementById("indicationInput").value;
	 document.getElementsByName("visitReason")[0].value=indication;
	// alert(document.getElementsByName("visitReason")[0].value);
		 }


function openInvTrackingToCompare(crNo,testCode,groupCode,requisitionDNo,requisitionNo){
  
  var isGroup="0";
  if(groupCode!="" && groupCode!=null && groupCode!="null"){isGroup="1"}
  
  var _mode = "UrlExternalCall";
  var url="/HISInvestigationG5/new_investigation/InvestigationTrackingReport.cnt?hmode="+_mode+"&crNo="+crNo+"&searchType=1"+"&isGroup="+isGroup
  +"&testCode="+testCode+"&groupCode="+groupCode+"&requisitionDNo="+requisitionDNo+"&requisitionNo="+requisitionNo+"&showGraph=0";
  
  $(".iframeInvTracking").attr({"href":url});
  $(".iframeInvTracking").click();
  //var win = window.open(url,'_blank');
  //localStorage.setItem('isFullScreen','1'); 
  
  /*window.location.href,*/
  
}


var $fancyboxWidth = 1350;
var $fancyboxHeight = 560;

$(document).ready(function() {
    $(".iframeInvTracking").fancybox({
       /* 
        'autoSize' : false,
        "width":$fancyboxWidth,
        "height":$fancyboxHeight,
        'transitionIn'	:	'elastic',
		'transitionOut'	:	'elastic',
		'speedIn'		:	600, 
		'speedOut'		:	200, 
		'overlayShow'	:	false,
		'showCloseButton'	: true,
        helpers : { 
        overlay: {
           // opacity: 0.8, // or the opacity you want 
            css: {'background-color': 'rgba(0,0,0,0.5)'}// background-color: rgba(0,0,0,0.5); // or your preferred hex color value
           }
        }*/
    	type: 'iframe',
    	toolbar  : false,
    	smallBtn : true,
    	
    	transitionEffect: "zoom-in-out",
    	transitionDuration: 366,
    	iframe : {
    		preload : true,
    		css : {
//    			'width':$fancyboxWidth,
//    	        'height':$fancyboxHeight,
    	        'width'  : '96%',
    	        'height' : '108%',
            }
    	},
    	 btnTpl: {
    		    close:
    		      '<button data-fancybox-close type="button"  class="fancybox-button fancybox-button--close" title="{{CLOSE}}">' +
    		      '<i class="far fa-window-close fa-w-16 fa-2x" style=" color: #82C91E;"></i>' +
    		      "</button>",
    		      
    		      smallBtn:
    		          '<button data-fancybox-close type="button"  class="fancybox-button fancybox-close-small " title="{{CLOSE}}">' +
    		          '<i class="far fa-window-close fa-w-16 fa-2x" style=" color: #82C91E;"></i>' +
    		          "</button>"
    	 }
    });
});