/**
 * 
 */
/* $(function() {
	   $('#datetimepicker1').datetimepicker();
	 }); */

	$(document).ready( function () {
		var groupColumn = 9;
		 var table = $('#table2').DataTable({
			 "pageLength": 25,
			 "orderClasses": false,
			 "order": [[ 7, "asc"]],							//[ groupColumn, 'asc' ]
			 "columnDefs": [{
			    'targets': [0,10,11,12,13], /* column index */
			    'orderable': false, /* true or false */
			},{ type: 'date-dd-mmm-yyyy', targets: 9 }],		//,  { "visible": false, "targets": groupColumn }],
			 responsive: true,
			 /*  rowGroup: {
			        dataSrc: 9
			    }  */
			 });

		/*  jQuery.extend( jQuery.fn.dataTableExt.oSort, {
				"date-uk-pre": function ( a ) {
				    var ukDatea = a.split('-');
				    return (ukDatea[2] + ukDatea[1] + ukDatea[0]) * 1;
				},

				"date-uk-asc": function ( a, b ) {
				    return ((a < b) ? -1 : ((a > b) ? 1 : 0));
				},

				"date-uk-desc": function ( a, b ) {
				    return ((a < b) ? 1 : ((a > b) ? -1 : 0));
				}
				} ); */
		
		 $('#table2 tbody').on('click', 'td.details-control', function () {
	          var tr = $(this).closest('tr');
	          var row = table.row(tr);

	          if (row.child.isShown()) {
	              // This row is already open - close it
	              row.child.hide();
	              tr.removeClass('shown');
	          } else {
	              // Open this row
	              row.child(format(tr.data('child-value'))).show();
	              tr.addClass('shown');
	          }
	      });


		 $('#btn-show-all-children').on('click', function(){
			 document.getElementById("btn-show-all-children").style.display="none";	
			 document.getElementById("btn-hide-all-children").style.display="block";	
		        // Enumerate all rows
		        table.rows().every(function(){
		            // If row has details collapsed
		             var tr = $(this.node());
		            if(!this.child.isShown()){
		                // Open this row
		                this.child(format(tr.data('child-value'))).show();
		                tr.addClass('shown');
		            }
		        });
		    });

		    // Handle click on "Collapse All" button
		    $('#btn-hide-all-children').on('click', function(){
		    	document.getElementById("btn-show-all-children").style.display="block";	
				 document.getElementById("btn-hide-all-children").style.display="none";
		        // Enumerate all rows
		        table.rows().every(function(){
		            // If row has details expanded
		            if(this.child.isShown()){
		                // Collapse row details
		                this.child.hide();
		                $(this.node()).removeClass('shown');
		            }
		        });
		    });

   
	} );

	/* $(document).ready( function () {
		$('#table1').DataTable();
	} ); */
	
	
	
	
	function myuploadremoveFunction(obj)
	{
		var idd=obj.id;
		idd=idd.split("@@")[2];
		document.getElementById(idd).value="";
		document.getElementById(obj.id).style.display = "none";
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

			},
	        error: function(error)
	        {
	        	
	        }};
		var objDojoAjax = dojo.xhrPost(objXHR) ;
		//alert("new"+remarks);
		return remarks; 
		
	}
	
	
	function checkIsparameterDependent(dependentelementcodevalue,selectedText,reqdno)
	{
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
			},
	        error: function(error)
	        {
	        	
	        }};
		var objDojoAjax = dojo.xhrPost(objXHR) ;
		//alert("new"+remarks);
		return remarks; 
		
	}

    
	function f2()
	{
		document.getElementById('reqformss').style.display="none";
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
	
	