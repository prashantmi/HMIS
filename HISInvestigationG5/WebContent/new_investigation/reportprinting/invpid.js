
window.onscroll = function() {scrollFunction()};



$(document).ready(function() {
		   $('#example1').addClass( 'nowrap' );
	     var table1 = $('#example1').DataTable( {

	        	             "lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
	        	             "iDisplayLength": 25
	       
	        
	        	
	     } );
	  //   table1.page.len( 20 ).draw();
	     
	 } ); 



function scrollFunction() {
 
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
  document.body.scrollTop = 0;
  document.documentElement.scrollTop = 0;
}


function showall(){

	
	document.getElementsByName('hmode')[0].value="NEW";
	document.forms[0].submit();


	 
	 }  


/*function showall()
{


//document.getElementsByName('hmode')[0].value="NEW";
//document.forms[0].submit();

var myVar="NEW";

var patcrno= $('[name="patCrNo"]').val();

alert(patcrno);

var options = { "hmode" : myVar, "patCrNo" : patcrno};

var data="";
$.ajax({
        url: '/HISInvestigationG5/new_investigation/invResultReportPrintingNew.cnt',
        data: options,
        async: false,
        success: function (resp) {
            data = resp;
           
        },
        error: function () {}
    });

alert(data);

var newUrl = '/HISInvestigationG5/new_investigation/invResultReportPrintingNew.cnt?hmode=NEW&patCrNo='+patcrno;


var table;
if ( $.fn.dataTable.isDataTable( '#example' ) ) 
{
    table = $('#example').dataTable();
    table.fnReloadAjax(newUrl);
    
}


}*/



/*
$(document).ready(function() {
    
var table = $('#example').DataTable( {
    rowReorder: {
        selector: 'td:nth-child(2)'
    },
    responsive: true,  "bPaginate": false,
    "bFilter": false,
    "bInfo": false,
	    "ordering": false
} );
} );







$(document).ready(function() {
var table1 = $('#example1').DataTable( {
    
    responsive: true,
	 "destroy": true,
	 "columnDefs": [ {

'targets': [1],  column index 

'orderable': false,  true or false 

},	{ "width": "1%", "targets": 0 }]

} );
} );*/


function setpop()
{
	
	  	
}



function showreport()
{
	
	var obj=document.getElementsByName("reportt");
   var pdfname="";
	for(var a=0;a<obj.length;a++)
	{
		
	     if(obj[a].checked==true)
	    	 {
	    	 
	    	 pdfname+=obj[a].value+"@@";
	    	 
	    	 }
	     
	}
alert(pdfname);

var _mode = "SHOWREPORT";

var url1="/HISInvestigationG5/new_investigation/invResultReportPrintingNew.cnt?hmode=SHOWREPORT&reporturl="+pdfname;
//alert(url1);
document.getElementById('pdfFrame').src =url1;

/*$.ajax({
    type: "GET",
	url: "/HISInvestigationG5/new_investigation/invResultReportPrintingNew.cnt?hmode="+_mode+"&reporturl="+pdfname, sync:false,async: true, postData: "", handleAs: "text",
    async:true,
    success: function (data) {
        alert(data);
    }
});*/





}




function showreport1(){ 
	
	
	var obj=document.getElementsByName("reportt");
	   var pdfname="";
	/*	for(var a=0;a<obj.length;a++)
		{
			
		     if(obj[a].checked==true)
		    	 {
		    	 
		    	 pdfname+=obj[a].value+"@@";
		    	 
		    	 }
		     
		}*/
		
	   pdfname="961011900000944_10001_10032019124453.557PM.pdf@@";
	   
	   
		alert(pdfname);
		var _mode = "SHOWREPORT";

		var url1="/HISInvestigationG5/new_investigation/invResultReportPrintingNew.cnt?hmode=SHOWREPORT&selectedCheckbox="+pdfname;
		//alert(url1);
		
		if($('#printPrescFrameOnMainDeskId'))
		  	$('#printPrescFrameOnMainDeskId').remove();
		
		
	$('#printPrescriptionMainDeskModal .modal-body').prepend('<iframe id="printPrescFrameOnMainDeskId" style="width:100%;height:75vh;" src="'+ url1+'"></iframe>');
	$('#printPrescriptionMainDeskModal').modal('show'); 
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
            	//  document.getElementsByName('piddmodalopen')[0].value="1"
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
	
	       // alert("ccc"+document.getElementsByName('pidd')[0].value);
	        
	    	document.getElementsByName('hmode')[0].value="SAVEDATA";
			document.forms[0].submit();
			
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

