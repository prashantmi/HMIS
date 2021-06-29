
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    document.getElementById("myBtn").style.display = "block";
  } else {
    document.getElementById("myBtn").style.display = "none";
  }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
  document.body.scrollTop = 0;
  document.documentElement.scrollTop = 0;
}


function showall(){

	document.getElementById("pattile").style.display="";
	document.getElementById("crnodatattile").style.display="";
//alert("show");
	
	/* $(document).ready(function() {
		var _mode = "NEW";
		var patcrno= $('[name="patCrNo"]').val();
		
	     var table = $('#example').DataTable( {
	        // lengthChange: false,
	    	 "columnDefs": [
	    	                {
	    	                    // The `data` parameter refers to the data for the cell (defined by the
	    	                    // `data` option, which defaults to the column being worked with, in
	    	                    // this case `data: 0`.
	    	                    "render": function ( data, type, row ) {
	    	                        return data +' ('+ row[3]+')';
	    	                    },
	    	                    "targets": 0
	    	                },
	    	                { "visible": false,  "targets": [ 3 ] }
	    	            ],
	    	 
	        dom: 'Bfrtip',
	        rowReorder: {
	            selector: 'td:nth-child(2)'
	        },
	        responsive: true,  "bPaginate": false,
	        "bFilter": false,
	        "bInfo": false,
	    	    "ordering": false,
		
	        ajax: {
	        	url: "/HISInvestigationG5/new_investigation/invResultReportPrintingNew.cnt?hmode="+_mode+"&patCrNo="+patcrno, sync:false,async: true, postData: "", handleAs: "text",
	              
	        	dataSrc: "data"  	  
	        	
	        		      
	        	},
	        	  "initComplete":function( settings, json){
	                  
	        		  var x = json.data[0];
	        		  x=JSON.stringify(x);
	        		  var res = x.split(",");
	        		//  alert(res[6]);
	        		  
	        		  if(res[6]=='"-"')
	        			  {
	        			  alert("true");
	        			  var api = new $.fn.dataTable.Api(settings);
                          api.columns([6,7,8]).visible(false);  
	        			
	        			  }
	        		  
	        		  
	        		  
	                  // call your function here
	              },
	        "sPaginationType": "full_numbers",
	         "bJQueryUI": true,
	         //"ordering": false
	         
	        
	        	
	     } );
	      
	     
	 } ); */





			var _mode = "NEW";
			alert(_mode);
			var patcrno= $('[name="patCrNo"]').val();
			

		/*	$.ajax({url: "/HISInvestigationG5/new_investigation/invResultReportPrintingNew.cnt?hmode="+_mode+"&patCrNo="+patcrno, success: function(result){
			    alert("su");
			  }});*/

			
					var _mode = "NEW";
					var patcrno= $('[name="patCrNo"]').val();
					   $('#example1').addClass( 'nowrap' ); 

				     var table = $('#example1').DataTable( {
				        // lengthChange: false,
				    	/* "columnDefs": [
				    	                {
				    	                    // The `data` parameter refers to the data for the cell (defined by the
				    	                    // `data` option, which defaults to the column being worked with, in
				    	                    // this case `data: 0`.
				    	                    "render": function ( data, type, row ) {
				    	                        return data +' ('+ row[3]+')';
				    	                    },
				    	                    "targets": 0
				    	                },
				    	                { "visible": false,  "targets": [ 3 ] }
				    	            ],*/
				    	 
				        dom: 'Bfrtip',
				        rowReorder: {
				            selector: 'td:nth-child(2)'
				        },
				        responsive: true,  "bPaginate": false,
				        "bFilter": false,
				        "bInfo": false,
				    	    "ordering": false,
					
				        ajax: {
				        	url: "/HISInvestigationG5/new_investigation/invResultReportPrintingNew.cnt?hmode="+_mode+"&patCrNo="+patcrno, sync:false,async: true, postData: "", handleAs: "text",
				              
				        	dataSrc: "data",  	  
				        	
				        		
				        	},
				              "columnDefs": [
				                             
				        	                 {
				        	                     // The `data` parameter refers to the data for the cell (defined by the
				        	                     // `data` option, which defaults to the column being worked with, in
				        	                     // this case `data: 0`.
				        	                     "render": function ( data, type, row ) {
				        	                    	// alert(data);
				        	                    //	return "<span style='color:blue;font-weight:bold'>"+data+"</span>" ;
				        	                         //return "<button type='button' class='btn btn-outline-info opdVitalBtn' style='background-color:#7C9CDE !important; border:0px;' onclick='openModalForVital(this,event);'  ><span style='color:white'><b>CR NO: "+data+"</b></span></button>";
				        	                         
				        	                    	return "<img class='img-circle img-responsive patProfileImg' src='/HISDRDESK/new_opd/img/img_avatar3.png' style='width: 30px; min-width: 26px; display:inline-block;  border: 1px solid #fff;' />   <i class='isAttended attended'></i> <a style='cursor: default;font-weight:bold;color:white;background-color:#7C9CDE !important;font-size:1.0em' class='label'>CR NO: "+data+"</a>";
				        	                         
				        	                    	//  return "<img class='img-circle img-responsive patProfileImg' src='/HISDRDESK/new_opd/img/img_avatar.png' style='width: 40px; min-width: 26px; display:inline-block;  border: 1px solid #fff;'>   <i class='isAttended attended'></i> <a style='cursor: default;font-weight:bold;color:white;background-color:#7C9CDE !important;font-size:1.0em' class='label'>CR NO: "+data+"</a>";
				        	                         
				        	                     },
				        	                     
				        	                     
				        	                     "targets": [0]
				        	                 },	 
				        	                 {
				        	                     // The `data` parameter refers to the data for the cell (defined by the
				        	                     // `data` option, which defaults to the column being worked with, in
				        	                     // this case `data: 0`.
				        	                     "render": function ( data, type, row ) {
				        	                    	// alert(data);
				        	                    //	return "<span style='color:blue;font-weight:bold'>"+data+"</span>" ;
				        	                         return "<span style='font-weight:bold'>Patient Name: </span>"+data;
				        	                         		
				        	                     },
				        	                     "targets": [1]
				        	                 }, {
				        	                     // The `data` parameter refers to the data for the cell (defined by the
				        	                     // `data` option, which defaults to the column being worked with, in
				        	                     // this case `data: 0`.
				        	                     "render": function ( data, type, row ) {
				        	                    	// alert(data);
				        	                    //	return "<span style='color:blue;font-weight:bold'>"+data+"</span>" ;
				        	                         return "<span style='font-weight:bold'>Age/Gender: </span>"+data+"</b></span></button>";
				        	                         		
				        	                     },
				        	                     "targets": [2]
				        	                 },
 {
				        	                     // The `data` parameter refers to the data for the cell (defined by the
				        	                     // `data` option, which defaults to the column being worked with, in
				        	                     // this case `data: 0`.
				        	                     "render": function ( data, type, row ) {
				        	                    	// alert(data);
				        	                    //	return "<span style='color:blue;font-weight:bold'>"+data+"</span>" ;
				        	                         return "<span style='font-weight:bold'>Mobile No: </span>"+data;
				        	                         		
				        	                     },
				        	                     "targets": [3]
				        	                 }
,
 {
				        	                     // The `data` parameter refers to the data for the cell (defined by the
				        	                     // `data` option, which defaults to the column being worked with, in
				        	                     // this case `data: 0`.
				        	                     "render": function ( data, type, row ) {
				        	                    	// alert(data);
				        	                        // return data;
				        	                    	 //return "<button type='button' class='btn btn-outline-info opdVitalBtn' style='background-color:#7C9CDE !important; border:0px;' onclick='openModalForVital(this,event);'  ><span style='color:white'><b>Patient Status: "+data+"</b></span></button>";
				        	                    	  return "<a style='cursor: default;font-weight:bold;color:white;background-color:#7C9CDE !important;font-size:1.0em' class='label'>Patient Status: "+data+"</a>";
				        	                    	//return <button type='button' class='btn btn-outline-info opdVitalBtn' style='background-color:#7C9CDE !important; border:0px;' onclick='openModalForVital(this,event);'  ><span style='color:white'><b>Patient Status: "+data+"</b></span></button>		
				        	                     },
				        	                     "targets": [4]
				        	                 }
, {
    // The `data` parameter refers to the data for the cell (defined by the
    // `data` option, which defaults to the column being worked with, in
    // this case `data: 0`.
    "render": function ( data, type, row ) {
   	// alert(data);
   //	return "<span style='color:blue;font-weight:bold'>"+data+"</span>" ;
   	 
   	 return "<span style='font-weight:bold'>Patient Category: </span>"+data;
        		
    },
    "targets": [5]
},
 {
				        	                     // The `data` parameter refers to the data for the cell (defined by the
				        	                     // `data` option, which defaults to the column being worked with, in
				        	                     // this case `data: 0`.
				        	                     "render": function ( data, type, row ) {
				        	                    	// alert(data);
				        	                    //	return "<span style='color:blue;font-weight:bold'>"+data+"</span>" ;
				        	                    	 if(data!="-")
				        	                    	 return "<span style='font-weight:bold'>Ward /Bed No: </span>"+data;
				        	                    	 else
				        	                    		 return "";
				        	                     },
				        	                     "targets": [6]
				        	                 }
,
 {
				        	                     // The `data` parameter refers to the data for the cell (defined by the
				        	                     // `data` option, which defaults to the column being worked with, in
				        	                     // this case `data: 0`.
				        	                     "render": function ( data, type, row ) {
				        	                    	// alert(data);
				        	                    //	return "<span style='color:blue;font-weight:bold'>"+data+"</span>" ;
				        	                    	 if(data!="-")
				        	                    	 return "<span style='font-weight:bold'>Admission No: </span>"+data;
				        	                    	 else
				        	                    		 return "";
				        	                     },
				        	                     "targets": [7]
				        	                 }
,
 {
				        	                     // The `data` parameter refers to the data for the cell (defined by the
				        	                     // `data` option, which defaults to the column being worked with, in
				        	                     // this case `data: 0`.
				        	                     "render": function ( data, type, row ) {
				        	                    	// alert(data);
				        	                    //	return "<span style='color:blue;font-weight:bold'>"+data+"</span>" ;
				        	                        if(data!="-")
				        	                    	 return "<span style='font-weight:bold'>MLC No: </span>"+data+"</b></span></button>";
				        	                        else
				        	                    		 return "";
				        	                     },
				        	                     "targets": [8]
				        	                 },

                
				        	                 ],
				        "sPaginationType": "full_numbers",
				         "bJQueryUI": true,
				         //"ordering": false
				         
				        
				        	
				     } );
				      
				     
		
	 

	 $(document).ready(function() {
			var _mode = "GETDATACRNOWISE";
			var patcrno= $('[name="patCrNo"]').val();
			   $('#example').addClass( 'nowrap' );
		     var table1 = $('#example').DataTable( {
		        // lengthChange: false,
		    	 "columnDefs": [
		    	                {
		    	                    // The `data` parameter refers to the data for the cell (defined by the
		    	                    // `data` option, which defaults to the column being worked with, in
		    	                    // this case `data: 0`.
		    	                    "render": function ( data, type, row ) {
		    	                        return data +' ('+ row[3]+')';
		    	                    },
		    	                    "targets": 0
		    	                },
		    	                { "visible": false,  "targets": [ 3 ] }
		    	            ],
		    	 
		       
		        responsive: true,  
		      
		        ajax: {
		        	url: "/HISInvestigationG5/new_investigation/invResultReportPrintingNew.cnt?hmode="+_mode+"&patCrNo="+patcrno, sync:false,async: true, postData: "", handleAs: "text",
		              
		        	dataSrc: "aaData" 
		        	},
		        	  "columnDefs": [
		        	                 {
		        	                     // The `data` parameter refers to the data for the cell (defined by the
		        	                     // `data` option, which defaults to the column being worked with, in
		        	                     // this case `data: 0`.
		        	                     "render": function ( data, type, row ) {
		        	                         return '';
		        	                     },
		        	                     "targets": [0]
		        	                 },
		        	                 {
		        	                     // The `data` parameter refers to the data for the cell (defined by the
		        	                     // `data` option, which defaults to the column being worked with, in
		        	                     // this case `data: 0`.
		        	                     "render": function ( data, type, row ) {
		        	                    	 
		        	                    	 if(row[13]==1 || row[13]==2)
		        	                    		 {
		        	                    		// alert(row[15]);
		        	                    		 if(row[15]!=null && row[15]!='')
		        	                    			 return "<input type='checkbox' id='row["+ row[0]+"]' name='reportt' value='"+ row[15]+"'> "+data ;       		 
		        	                    		 else
		        	                    			 return "<input type='checkbox' id='row["+ row[0]+"]' name='reportt' value=''> "+data ;	 
		        	                    		 }
		        	                    	else
		        	                    		 return data ;	 
		        	                     },
		        	                     "targets": [1]
		        	                 },
		        	                 {
		        	                     // The `data` parameter refers to the data for the cell (defined by the
		        	                     // `data` option, which defaults to the column being worked with, in
		        	                     // this case `data: 0`.
		        	                     "render": function ( data, type, row ) {
		        	                    	// alert(row[14]);
		        	                    	 if(row[14]!=null && row[14]!="")
		        	                    		 {
		        	                    		 var datat=row[14];
		        	                    		 datat="<span style='color:white'>"+datat+"</span>";
		        	                    		 
		        	                    		 
		        	                    	 return '<a href="#" data-html="true" style="color:black"   data-toggle="popover1" data-trigger="hover" title="<b>Report Generated Group Test</b>" data-content="'+datat+'">'+data+'</a>' ;

		        	                    		 }else
		        	                    		 {return data;}
		        	                    			 
		        	                     },
		        	                     "targets": [2]
		        	                 },
		        	                 {
		        	                     // The `data` parameter refers to the data for the cell (defined by the
		        	                     // `data` option, which defaults to the column being worked with, in
		        	                     // this case `data: 0`.
		        	                     "render": function ( data, type, row ) {
		        	                    	// alert("3"+row[3]);
		        	                    	 $('[data-toggle="popover1"]').popover();
		        	                    	 if(row[3]!=null && row[3]!='NA' )
		        	                    		 {
		        	                    		 
		        	                    		 var datat=row[3];
		        	                    		 datat="<span style='color:white'>"+datat+"</span>";
		        	                    		 
		        	                    	 return '<a href="#" data-html="true" style="color:black"   data-toggle="popover1" data-trigger="hover" title="<b>Pending Test/Group</b>" data-content="'+ datat+'">Yes</a>' ;
		        	                    		 }
		        	                    	 else
		        	                    	 {	 return "No" ;	 
		        	                    	 }
		        	                    	 
		        	                    	 },
		        	                     "targets": [3]
		        	                 },  {
		        	                     // The `data` parameter refers to the data for the cell (defined by the
		        	                     // `data` option, which defaults to the column being worked with, in
		        	                     // this case `data: 0`.
		        	                     "render": function ( data, type, row ) {
		        	                    	// alert("3"+row[3]);
		        	                    	 $('[data-toggle="popover1"]').popover();
		        	                    	
		        	                    		 var datat=row[4];
		        	                    		 datat="<span style='color:white'>"+datat+"</span>";
		        	                    		 
		        	                    	if(row[13]=="1")
		        	                         return "<a style='cursor: default;font-weight:bold;color:white' class='label label-info'>"+datat+"</a>" ;
		        	                    	if(row[13]=="0")
		        	                    	 return "<a style='cursor: default;font-weight:bold;color:white' class='label label-danger'>"+datat+"</a>" ;
		        	                    	if(row[13]=="2") 	
		        	                    	 return "<a style='cursor: default;font-weight:bold;color:white' class='label label-warning'>"+datat+"</a>" ;
			        	                    	
		        	                    	 
		        	                    	 },
		        	                     "targets": [4]
		        	                 },
		        	                 
		        	                 
		        	                 
		        	             ],
		        	             "lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
		        	             "iDisplayLength": 25
		       
		        
		         //"ordering": false
		         
		        
		        	
		     } );
		  //   table1.page.len( 20 ).draw();
		     
		 } ); 
	 
	 /* $(document).ready(function() {
			var _mode = "GETDATA";
			
		     var table = $('#example1').DataTable( {
		        // lengthChange: false,
		        dom: 'Bfrtip',
		        
		        responsive: true,
		    	 "destroy": true,
		    	 "columnDefs": [ {

		    'targets': [1], 

		    'orderable': false, 

		    },	{ "width": "1%", "targets": 0 }]

			
		        ajax: {
		        	url: "/HISInvestigationG5/new_investigation/invResultReportPrintingNew.cnt?hmode="+_mode+"&patCrNo="+patcrno, sync:true, postData: "", handleAs: "text",
		               dataSrc: "data"  	  
		        	  
		        	},
		        "sPaginationType": "full_numbers",
		         "bJQueryUI": true,
		         //"ordering": false
		         
		        
		        	
		     } );
		      
		     
		 } ); 
	  */

	 
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

