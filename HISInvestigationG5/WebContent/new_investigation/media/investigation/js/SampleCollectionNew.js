

$(document).ready(function(){
	


		var tippy_saveValBtn=tippy('.saveValidateBtnDiv', {
			delay: 100,
			arrow: true,
			arrowType: 'round',
			size: 'large',
			duration: 500,
			animation: 'shift-away-extreme',
			placement: 'bottom',
			allowHTML: true,
			content: 'Please select atleast one Record to Save Records',
		});

		tippyLoadingUtlInstance.saveValBtnDiv=tippy_saveValBtn[0];
		
	var tippy_fullScrBtn=tippy('.getDataBtnDiv', {
	    delay: 100,
	    arrow: true,
	    arrowType: 'round',
	    size: 'large',
	    duration: 350,
	    animation: 'shift-away-extreme', /*perspective-extreme*/
	    placement: 'bottom',
	    allowHTML: true,
	    content: '<strong><span style="color: aqua;">CRNO </span> Search</strong>',
	});
});
	$(document).ready(function(){
		var tippy_fullScrBtn=tippy('.getDataForDupBarCode', {
		    delay: 100,
		    arrow: true,
		    arrowType: 'round',
		    size: 'large',
		    duration: 350,
		    animation: 'shift-away-extreme', /*perspective-extreme*/
		    placement: 'bottom',
		    allowHTML: true,
		    content: '<strong><span style="color: aqua;">CRNO </span> Search</strong>',
		});

	});
	
	$(document).ready(function(){
			var tippy_fullScrBtn=tippy('#urgentImage', {
		    delay: 100,
		    arrow: true,
		    arrowType: 'round',
		    size: 'large',
		    duration: 350,
		    animation: 'shift-away-extreme', /*perspective-extreme*/
		    placement: 'bottom',
		    allowHTML: true,
		    content: '<strong><span style="color: aqua;">Urgent </span> Test</strong>',
		});

	});

function showLoding(showBoolean) {

	  if (showBoolean == true) {
	    $('#getData').addClass("d-none");
	    $('#getingData').removeClass("d-none");
	    $('#container2Row1').addClass("gradient-border");
	  } else if (showBoolean == false) {
	    $('#getingData').addClass("d-none");
	    $('#getData').removeClass("d-none");
	    $('#container2Row1').removeClass("gradient-border");
	  } else {
	    $('#getingData').addClass("d-none");
	    $('#getData').removeClass("d-none");
	    $('#container2Row1').removeClass("gradient-border");
	  }
	}
	

//---------------------------------------get patient data -------------------------------------------------------

//refreshBtn
//closeBtn
$(document).ready(function() {

  $('.refreshBtn').click(function() {
		document.getElementsByName('hmode')[0].value='NEW';
		document.forms[0].submit();
  
  });
  });
$(document).ready(function() {
$('.closeBtn').click(function() {
		window.parent.closeTab();
});
});


$(document).ready(function() {

  $('#getData').click(function() {
	//alert("search function called......");
	var sampleAreaCode = document.getElementsByName("sampleAreaCode")[0].value;
 	
	//alert("ss"+sampleAreaCode);
	
 	if (sampleAreaCode=="-1")
		{
		alert("Please Select Collection Area");
	document.getElementsByName("sampleAreaCode")[0].focus();
	             return null;
		}
 	else
 		{
	
 		
 		}
	
     var crno = $('#crNoInput').val();
    // console.log("fthfyt");
        if (crno.length == 15) {
            showLoding(true);

        	$('#patDetails').removeClass("d-none");
        	var globalSearchParam={ crNo:crno, billNo:"",searchType:"1"};
        		AjaxGetPatDetails(globalSearchParam);
        		$.when(globalAjaxGetPatDtlObj ).then(function( data, textStatus, jqXHR ){
        			//console.log("fffffff");
        		 //alert("Loading...");
    				var patDtl = globalPatientDetails[0].patStatusCode;
            		//alert(patDtl);
            		billedUnbilledfun(patDtl);
            		$('#tabsId').removeClass("d-none");
            		
            		var patCategoryCode =globalPatientDetails[0].patCategoryCode
            		setPatCategoryCode(patCategoryCode);
       	    	 });
				}
        	else{
        		alert("Cr. No. Should Be Of 15 Digits");
				$('#crNoInput').focus();
			       
				return null;
        	}
             showLoding(false);
      
    
   
  });
});


function setPatCategoryCode(patCategoryCode){
	if(document.getElementById("patCategoryCode_samplecoll01")!=undefined && document.getElementById("patCategoryCode_samplecoll01")!=null)
	{
	document.getElementById("patCategoryCode_samplecoll01").value=patCategoryCode;
	}
}

$(document).ready(function(){
	
	$('ul.tabs li').click(function(){
		
		var tab_id = $(this).attr('data-tab');
		//alert(tab_id);
		$('ul.tabs li').removeClass('current');
		$('.tab-content').removeClass('current');
		$('#allValReqnList_'+tab_id).removeClass('show');
		$(this).addClass('current');
		$("#"+tab_id).addClass('current');
		$('#allValReqnList_'+tab_id).addClass('show');
	});
});

 //---------------------------------------------------------get billed unbilled data -----------------------------------------------------------
	var globalBilleUnBilledDetails = null;
 var str = null;
 function billedUnbilledfun(patStatus){
	// alert("hello "+patStatus)
	var crNo = $('#crNoInput').val();
	var patStatus = patStatus;
	var _mode = "AjaxGetDetails";
	var sampleAreaCode=	document.getElementsByName("sampleAreaCode")[0].value;
 		
	 var url = "/HISInvestigationG5/new_investigation/NewsampleCollection.cnt?hmode=" + _mode + "&crNo=" + crNo +"&patStatusCode=" +patStatus+"&sampleAreaCode=" +sampleAreaCode;
	//	alert(url);
		var ajaxObjectArr1 ={};
		 ajaxObjectArr1.AjaxGetPatDetails=$.getJSON(url, function(data) {
		 	if(!$.isEmptyObject(data)) {
					//alert("hi");
					if(data) {
						//alert(data);
							$('#billedSpan').empty();
							$('#UnbilledSpan').empty();
							var bill= data.BilledDtl.length;
						var unbill = data.UnbilledDtl.length;
						$('#billedSpan').append(bill);
						$('#UnbilledSpan').append(unbill);
						callUnBilledSection(data.UnbilledDtl);
						callbilledSection(data.BilledDtl,data.UOMCombo,data.machineCombo,data.ContainerCombo);
						//alert(data.UnbilledDtl.length);
						//alert(data.BilledDtl.length);
					
							}
					else
						{
						var bill= data.BilledDtl.length;
						var unbill = data.UnbilledDtl.length;
						$('#billedSpan').empty();
						$('#UnbilledSpan').empty();
						$('#billedSpan').append(bill);
						$('#UnbilledSpan').append(unbill);
						
						}
							}
						
		 })
		 .done(function(data) {
			  console.log("%cAjaxGetPatDetails success | ResponseData Is Below", "color:green;");
			  console.log(data);
			})
			.fail(function( jqxhr, textStatus, error ) {
			  console.log("%cAjaxGetPatDetails Failed | ResponseError Is Below", "color:red;");
			  var err = textStatus + ", " + error;
			  console.log( "Request Failed: " + err );
			});
		  	
 }
 
 
 
 function billedUnbilledfun_ipd(patStatus,wardcode){
		// alert("hello "+patStatus)
		var crNo = $('#crNoInput').val();
		var patStatus = patStatus;
		var _mode = "AjaxGetDetails";
		document.getElementsByName("sampleAreaCode")[0].value=wardcode;
		var sampleAreaCode=	wardcode;
	 		
		 var url = "/HISInvestigationG5/new_investigation/NewsampleCollection.cnt?hmode=" + _mode + "&crNo=" + crNo +"&patStatusCode=" +patStatus+"&sampleAreaCode=" +sampleAreaCode;
		//	alert(url);
			var ajaxObjectArr1 ={};
			 ajaxObjectArr1.AjaxGetPatDetails=$.getJSON(url, function(data) {
			 	if(!$.isEmptyObject(data)) {
						//alert("hi");
						if(data) {
							//alert(data);
								$('#billedSpan').empty();
								$('#UnbilledSpan').empty();
								var bill= data.BilledDtl.length;
							var unbill = data.UnbilledDtl.length;
							$('#billedSpan').append(bill);
							$('#UnbilledSpan').append(unbill);
							callUnBilledSection(data.UnbilledDtl);
							callbilledSection(data.BilledDtl,data.UOMCombo,data.machineCombo,data.ContainerCombo);
							//alert(data.UnbilledDtl.length);
							//alert(data.BilledDtl.length);
						
								}
						else
							{
							var bill= data.BilledDtl.length;
							var unbill = data.UnbilledDtl.length;
							$('#billedSpan').empty();
							$('#UnbilledSpan').empty();
							$('#billedSpan').append(bill);
							$('#UnbilledSpan').append(unbill);
							
							}
								}
							
			 })
			 .done(function(data) {
				  console.log("%cAjaxGetPatDetails success | ResponseData Is Below", "color:green;");
				  console.log(data);
				})
				.fail(function( jqxhr, textStatus, error ) {
				  console.log("%cAjaxGetPatDetails Failed | ResponseError Is Below", "color:red;");
				  var err = textStatus + ", " + error;
				  console.log( "Request Failed: " + err );
				});
			  	
	 }
 
 
 function callUnBilledSection(UnbilledDtl){
	 $('#example1').DataTable().clear().destroy();
	 
	 
	 var str = '';
	 var div_data1='';
	 var div_data2='';
	 var div_data3='';
	 var mpreqq=new Map();
		
	// alert(Object.keys(UOMCombo).length);
	// alert(Object.keys(machineCombo).length);
	// alert(Object.keys(ContainerCombo).length);
	 	
		
	 var chk=0;
	 var strform="";
	 var name = "checkboxName_"+chk;
	 var Id = "checkboxId_"+chk;
	// $("#example").find("tr:not(:first)").remove();
		if(Object.keys(UnbilledDtl).length > 0){
			for(var i=0;i<Object.keys(UnbilledDtl).length;i++){
				var len = UnbilledDtl.length;

				//alert(UnbilledDtl[i].requisitionNo);
				str+='<tr class="'+UnbilledDtl[i].requisitionNo+'roww1">'

			    	
				+'<td id="reqno_'+chk+'" id="reqnoo1'+chk+'" >'+UnbilledDtl[i].labName+'</td>'
				+'<td id="reqno_'+chk+'" id="reqnoo1'+chk+'" >'+UnbilledDtl[i].testName+'</td>'
				+'<td id="reqno_'+chk+'" id="reqnoo1'+chk+'" >'+UnbilledDtl[i].requisitionDate+'</td>'
				+'<td id="reqno_'+chk+'" id="reqnoo1'+chk+'" >'+UnbilledDtl[i].priorityAll+'</td>'
				+'<td id="reqno_'+chk+'" id="reqnoo1'+chk+'" >'+UnbilledDtl[i].requisitionNo+'</td>'
				+'<input type="hidden" id="'+UnbilledDtl[i].requisitionNo+'dateunb" value="'+UnbilledDtl[i].requisitionDate+'" >'
				
				+'</tr>'; 
			chk=chk+1;
		}
			
		}
		// 
		$('#example1').append(str);
		var groupColumn = 4;
		  
	
	 
	 var tablew = $('#example1').DataTable({

			rowReorder: true,
			responsive: true,
			
	        select: {
	            style:    'multi',
	            selector: 'td:first-child'
	        },
	        "language": { "emptyTable": "No Data Is Available " },
	        
	        "columnDefs":
				[   { type: 'date',"targets": 4, "visible": false }
	],	
	"order": [[ 4, "desc" ]],
	
	         "drawCallback": function ( settings ) {
        	



       	 var api = this.api();
       var rows = api.rows( {page:'current'} ).nodes();
       var last=null;
       api.column(groupColumn, {page:'current'} ).data().each( function ( group, i ) {
       
           if ( last !== group ) {
           	
	
              	var rowChkDatanew="";
            	
                var reqdateid="#"+group+"dateunb";
        		var reqdate=$(reqdateid).val();
        	
            		
            		
                  
                                     		rowChkDatanew+='<label>Requisition Date: '+reqdate+'</label>&nbsp;<label><i class="fas fa-sort-amount-up reqnDtGrupSortUpnew1"></i><i class="fas fa-sort-amount-down-alt reqnDtGrupSortDownnew1 d-none"></i></label><a href="#" ><img class="fullygene1" height="25" width="25"   src="media/images/star-pending.svg" >';
                                       
                   
                                       
           	
           	$(rows).eq( i ).before(
               		

           		
                   '<tr class="group" style="background-color:#0064ff5c"><td colspan="3">'+rowChkDatanew+'</td><td ></td></tr>'
               );

               last = group;
              
               
               

               // Order by the grouping
    		    $('.reqnDtGrupSortDownnew1').on( 'click', function () {
    	//	    	alert("hel");
    		/*        var currentOrder = "";
    		        currentOrder=table.order()[6];
    		        alert(currentOrder);
    		        if(currentOrder==undefined)
    		        	currentOrder=table.order()[0];
    		   	 
    		*/        /*"order": [[ 12, "desc" ],
    		   		 [ 23, "desc" ],[ 15, "desc" ]
    		   	 ,[ 16, "desc" ],[ 17, "desc" ],[ 18, "desc" ],
    		   	 [ 19, "desc" ],[ 20, "desc" ],[ 10, "desc" ],],
    				*/
    		        var currentOrder = "";
    		        currentOrder=tablew.order()[0];
    		      //  alert(currentOrder);
    		      var flg="1" ;
    		        if (currentOrder[1] === 'desc' && flg=='1') {
//       		 alert();
    		        	flg='0' ;
    		        	tablew.order([ 2, "asc" ]).draw();
    		       
    		    $(".reqnDtGrupSortDownnew1").removeClass("d-none");
    			$(".reqnDtGrupSortUpnew1").addClass("d-none");
    		
    		//	alert("asc");
    		        }
    		        else if(currentOrder[1] === 'asc' && flg=='1')
    		        	{
    		        	flg='0' ;
    		        	tablew.order([ 2, "desc" ]).draw();
    		        
    		        	$(".reqnDtGrupSortUpnew1").removeClass("d-none");
    					$(".reqnDtGrupSortDownnew1").addClass("d-none");
    						       
    			//		alert("desc");
          		     
    		        	}
//    		        if ( currentOrder[0] === groupColumn && currentOrder[1] === 'asc' ) {
//    		            table.order( [ groupColumn, 'desc' ] ).draw();
//    		        }
//    		        else {
//    		       
//    		        	table.order( [ groupColumn, 'asc' ] ).draw();
//    		        
//    		        }
    		        
    		    } );
    		    
               // Order by the grouping
    		    $('.reqnDtGrupSortUpnew1').on( 'click', function () {
    	   	//alert("hel");
    		/*        var currentOrder = "";
    		        currentOrder=table.order()[6];
    		        alert(currentOrder);
    		        if(currentOrder==undefined)
    		        	currentOrder=table.order()[0];
    		   	 
    		*/        /*"order": [[ 12, "desc" ],
    		   		 [ 23, "desc" ],[ 15, "desc" ]
    		   	 ,[ 16, "desc" ],[ 17, "desc" ],[ 18, "desc" ],
    		   	 [ 19, "desc" ],[ 20, "desc" ],[ 10, "desc" ],],
    				*/
    		        var currentOrder = "";
    		        currentOrder=tablew.order()[0];
    		      //  alert(currentOrder);
    		      var flg="1" ;
    		        if (currentOrder[1] === 'desc' && flg=='1') {
//       		 
    		        	flg='0' ;
    		 	tablew.order([ 2, "asc" ]).draw();
    		       
    		    $(".reqnDtGrupSortDownnew1").removeClass("d-none");
    			$(".reqnDtGrupSortUpnew1").addClass("d-none");
    		
    		//	alert("asc");
    		        }
    		        else if(currentOrder[1] === 'asc' && flg=='1')
    		        	{
    		        	flg='0' ;
    		        	tablew.order([ 2, "desc" ]).draw();
    		        
    		        	$(".reqnDtGrupSortUpnew1").removeClass("d-none");
    					$(".reqnDtGrupSortDownnew1").addClass("d-none");
    						       
    			//		alert("desc");
          		     
    		        	}
//    		        if ( currentOrder[0] === groupColumn && currentOrder[1] === 'asc' ) {
//    		            table.order( [ groupColumn, 'desc' ] ).draw();
//    		        }
//    		        else {
//    		       
//    		        	table.order( [ groupColumn, 'asc' ] ).draw();
//    		        
//    		        }
    		        
    		    } );
    		  

               

    		    
               
               
   			var fullygene=tippy('.fullygene1', {
				delay: 100,
				arrow: true,
				arrowType: 'round',
				size: 'large',
				duration: 500,
				animation: 'shift-away-extreme',
				placement: 'bottom',
				allowHTML: true,
				content: "Requisition all Tests/Groups are Unbilled",
			});


			tippyLoadingUtlInstance.fullygene=fullygene[0];

           }
           
           
           
           



    		    

		    
           
       } );
   
        },
	
	    responsive: {
	    	details: {
	    		renderer: function(api, rowIdx, columns) {
	    			var data = $.map(columns, function(col, i) {
	    				return col.hidden ? customeRowType1(col) : '';
	    				}).join('');
	    			return data ? customeRowDataAppend(data) : false;
	    		}
	    	}
	    },
	    "initComplete": function(settings, json) {    }
	  });
	  tablew.select.info( false);
}
 
 function callbilledSection(billedDtl,UOMCombo,machineCombo,ContainerCombo)
 {
	// alert("billedDtl"+billedDtl);
	 $('#example').DataTable().clear().destroy();
	 var str = '';
	 var div_data1='';
	 var div_data2='';
	 var div_data3='';
	 var mpreqq=new Map();
		
	// alert(Object.keys(UOMCombo).length);
	// alert(Object.keys(machineCombo).length);
	// alert(Object.keys(ContainerCombo).length);
	 if(Object.keys(UOMCombo).length > 0)
	 {
		for(var j=0;j<Object.keys(UOMCombo).length;j++) 
		{
			    
			div_data1+="<option value="+UOMCombo[j].value+">"+UOMCombo[j].label+"</option>";
		}
	 }	
	 if(Object.keys(machineCombo).length > 0)
	 {
		for(var k=0;k<Object.keys(machineCombo).length;k++) 
		{
			    div_data2+="<option value="+machineCombo[k].value+">"+machineCombo[k].label+"</option>";
		}
	 }	
	 if(Object.keys(ContainerCombo).length > 0)
	 {
		for(var s=0;s<Object.keys(ContainerCombo).length;s++) 
		{
			    div_data3+="<option value="+ContainerCombo[s].value+">"+ContainerCombo[s].label+"</option>";
		}
	 }	
	 var chk=0;
	 var strform="";
	 var name = "checkboxName_"+chk;
	 var Id = "checkboxId_"+chk;
	// $("#example").find("tr:not(:first)").remove();
		if(Object.keys(billedDtl).length > 0){
			for(var i=0;i<Object.keys(billedDtl).length;i++){
				var len = billedDtl.length;
				//alert(len);
				
				if (billedDtl[i].groupName == "NA" || billedDtl[i].prioritgroupName == "") {
//					strStatus = '<td  id="testName_'+chk+'">'+billedDtl[i].testName+'<input type="hidden" id="testCodeId_'+chk+'" value="'+billedDtl[i].testCode+'"><img  src="media/images/icon1.png" width="45px;" height="30px;" ><input type="hidden" id="isConfidentialId_'+chk+'" value="'+billedDtl[i].isConfidential+'"></td>';
					strStatus = '<td  class="text-wrap" id="testName_'+chk+'">'+billedDtl[i].testName+'<input type="hidden" id="testCodeId_'+chk+'" value="'+billedDtl[i].testCode+'"><input type="hidden" id="isConfidentialId_'+chk+'" value="'+billedDtl[i].isConfidential+'"></td>';

				} else {
					strStatus = '<td   class="text-wrap" id="testName_'+chk+'">'+billedDtl[i].testName+ ' |<font color="#ff006c">'+billedDtl[i].groupName+'</font ><input type="hidden" id="testCodeId_'+chk+'" value="'+billedDtl[i].testCode+'"><input type="hidden" id="isConfidentialId_'+chk+'" value="'+billedDtl[i].isConfidential+'"></td>';

				}
				
				if (billedDtl[i].isrequisitionformneeded == "0") {
					strform ='<td ><input value="'+billedDtl[i].requisitionDNo+'" class="'+billedDtl[i].requisitionNo+' rowCheckBoxes" type="checkbox" name="checkboxName_'+chk+'" id="checkboxId_'+chk+'" "><input type="hidden" id="totalId1" value="'+len+'"><input type="hidden" id="priorityAllCodeId_'+chk+'" value="'+billedDtl[i].priorityAllCode+'"><input type="hidden" id="patInstructId_'+chk+'" value="'+billedDtl[i].patInstruct+'"> </td>';
					 
						
				} else {
				//	strform = '<td ><input class="'+billedDtl[i].requisitionNo+' rowCheckBoxes"  type="checkbox" name="checkboxName_'+chk+'" id="checkboxId_'+chk+'"  "><input type="hidden" id="totalId1" value="'+len+'"><input type="hidden" id="priorityAllCodeId_'+chk+'" value="'+billedDtl[i].priorityAllCode+'"><input type="hidden" id="patInstructId_'+chk+'" value="'+billedDtl[i].patInstruct+'"> </td>';
					strform ='<td ><input value="'+billedDtl[i].requisitionDNo+'" class="'+billedDtl[i].requisitionNo+' rowCheckBoxes" type="checkbox" name="checkboxName_'+chk+'" id="checkboxId_'+chk+'" "><input type="hidden" id="totalId1" value="'+len+'"><input type="hidden" id="priorityAllCodeId_'+chk+'" value="'+billedDtl[i].priorityAllCode+'"><input type="hidden" id="patInstructId_'+chk+'" value="'+billedDtl[i].patInstruct+'"> </td>';
					  
				}
				
				
				var colorr="black";
				
				if(billedDtl[i].priorityAll=="Urgent")
					colorr="red";
				//<img src="/HISInvestigationG5/hisglobal/images/button_rf.png" onclick="ShowRequistionForm()">
	
				str+='<tr class="'+billedDtl[i].requisitionNo+'roww">'
				+'<td></td>'
				+strform
				+'<td id="labName_'+chk+'">'+billedDtl[i].labName+' <input type="hidden" id="requisitionNoId_'+chk+'" value="'+billedDtl[i].requisitionNo+'"><input type="hidden" id="patNameId_'+chk+'" value="'+billedDtl[i].patName+'"><input type="hidden" id="labCodeId_'+chk+'" value="'+billedDtl[i].labCode+'"> </td>'
				+strStatus
				+'<td  id="requisitionDate_'+chk+'">'+billedDtl[i].requisitionDate+'</td>'
				+'<td  id="sampleName_'+chk+'">'+billedDtl[i].sampleName+'<input type="hidden" id="sampleCodeId_'+chk+'" value="'+billedDtl[i].sampleCode+'"><input type="hidden" id="defaultContainerCodeId_'+chk+'" value="'+billedDtl[i].defaultContainerCode+'"></td>'
				+'<td  id="sampleNo_'+chk+'">'+billedDtl[i].sampleNo+'"></td>'
				+'<td><input class="form-control"  id="sampleQnty_'+chk+'" type="text" value="'+billedDtl[i].sampleQnty+'"></td>'
				+'<td id=""><select class="form-control"   id="UOM_'+chk+'" >'+div_data1+'<input type="hidden" id="collInstructId_'+chk+'" value="'+billedDtl[i].collInstruct+'"><input type="hidden" id="isrequisitionformneededId_'+chk+'" value="'+billedDtl[i].isrequisitionformneeded+'"></td>'
				+'<td id=""><select class="form-control"  id="container_'+chk+'" >'+div_data3+'<input type="hidden" id="miscDateId_'+chk+'" value="'+billedDtl[i].miscDate+'"><input type="hidden" id="billDetailId_'+chk+'" value="'+billedDtl[i].billDetail+'"></td>'
				+'<td  id="priorityAll_'+chk+'" <font style="color:'+colorr+'">'+billedDtl[i].priorityAll+'</font><input type="hidden" id="billNoId_'+chk+'" value="'+billedDtl[i].billNo+'"><input type="hidden" id="patTypeId_'+chk+'" value="'+billedDtl[i].patType+'"></td>'
				+'<td id=""><select class="form-control"  id="machineCode_'+chk+'" ><option value="-1">Select</option>'+div_data2+'</td>'
				+'<td id="reqno_'+chk+'" id="reqnoo'+chk+'" >'+billedDtl[i].requisitionNo+'</td>'
				+'<td id="reqno_'+chk+'" id="reqnoo'+chk+'" >'+billedDtl[i].priorityCode+'</td>'

				
		/*		if(is_ins=="1" || is_ins=="2")
				{
				var msg="";
				if(is_ins=="1")
				 msg="Fasting Required:Yes("+is_ins_fastingtime+" "+is_ins_fastingtype+")";
				else
					 msg="Full Bladder Required:Yes("+is_ins_bladder+")";
		
				
				
				*/
				
				
				+'<input type="hidden" id="requisitionDNoId_'+chk+'" value="'+billedDtl[i].requisitionDNo+'"><input type="hidden" id="reqDtlStatusId_'+chk+'" value="'+billedDtl[i].reqDtlStatus+'">'
				+'<input type="hidden" id="'+billedDtl[i].requisitionNo+'date" value="'+billedDtl[i].requisitionDate+'" >'
				+'<input type="hidden" id="'+billedDtl[i].requisitionNo+'crno" value="'+billedDtl[i].patCRNo+'" >'
				+'<input type="hidden" id="'+billedDtl[i].requisitionNo+'pending" value="'+billedDtl[i].pendingPayments+'" >'
				+'<input type="hidden" id="sampleNoConfigurationId_'+chk+'" value="'+billedDtl[i].sampleNoConfiguration+'">'
				+'<input type="hidden" id="defaultUOMCodeId_'+chk+'" value="'+billedDtl[i].defaultUOMCode+'">'	

				+'<input type="hidden" id="'+billedDtl[i].requisitionDNo+'is_inst" value="'+billedDtl[i].isanyInstruction+'" >'
				+'<input type="hidden" id="'+billedDtl[i].requisitionDNo+'fastingtime" value="'+billedDtl[i].fastingTime+'" >'
				+'<input type="hidden" id="'+billedDtl[i].requisitionDNo+'fastingtype" value="'+billedDtl[i].fastingType+'" >'
				+'<input type="hidden" id="'+billedDtl[i].requisitionDNo+'bladder" value="'+billedDtl[i].bladderintr+'" >'
				+'<input type="hidden" id="'+billedDtl[i].requisitionDNo+'testt" value="'+billedDtl[i].testName+'" >'


				+'</tr>'; 
			chk=chk+1;
		}
			
		}
		// 
		$('#example').append(str);
		var groupColumn = 12;
		  
		$('<thead style="font-size:14px;font-weight:700;"></thead>').prependTo('#example').append($('#example tr:first'));
		  var table = $('#example').DataTable({
			 
			  "dom": '<"row"<"col-6 col-sm-5 col-lg-2 "l><"col-6 col-sm-2 col-lg-2"><"col-6 col-sm-5 col-lg-3 "f><"col-6 col-sm-12 col-lg-5"p>>rt<"row"<"col-12"i>><"clear">',
				
				rowReorder: true,
				responsive: true,
		        select: {
		            style:    'multi',
		           
		        },
"pageLength": -1,
				
				"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
			
		        "language": { "emptyTable": "No Data Is Available " },
		        
		        "columnDefs":
					[   { "targets": 12, "visible": false }, { "targets": 6, "visible": false },{ type: 'date',"targets": 4, "visible": false },{ "targets": 13, "visible": false }
						
		  ],
		  "order": [[ 4, "desc" ]],
			
		        "drawCallback": function ( settings ) {
		        	



		        	 var api = this.api();
	            var rows = api.rows( {page:'current'} ).nodes();
	            var last=null;
	            api.column(groupColumn, {page:'current'} ).data().each( function ( group, i ) {
	            
	                if ( last !== group ) {
	                	
       	var rowChkDatanew="";
	                	
        var reqdateid="#"+group+"date";
		var reqdate=$(reqdateid).val();
		reqdateid="#"+group+"crno";
		var crno=$(reqdateid).val();
	
		reqdateid="#"+group+"pending";
		var pendingtest=$(reqdateid).val();
	
    	var	partilagenee11="Requisitions Tests/Groups are Partially Billed.<br/>Pending Test:-<span>"+pendingtest+"</span>";
    	
    		var idf="partiallygene"+i;
    		
    		mpreqq.set(idf,partilagenee11);
    		
    		
                		rowChkDatanew+=		'<div class="custom-control custom-checkbox">' ;
                		rowChkDatanew+='<input type="checkbox" value="'+group+'" class="custom-control-input rowCheckBoxesall  " name="chkSamplePatient22" id="chkSamplePatient22'+i+'">';
          
                               if(pendingtest!="undefined" && pendingtest!="")
                		rowChkDatanew+='<label class="custom-control-label" for="chkSamplePatient22'+i+'">Requisition Date: '+reqdate+'</label>&nbsp;<label><i class="fas fa-sort-amount-up reqnDtGrupSortUpnew"></i><i class="fas fa-sort-amount-down-alt reqnDtGrupSortDownnew d-none"></i></label><a href="#" ><img class="partiallygene" height="25" width="25"   id="'+idf+'"  src="media/images/half-star.svg" ></a><small><a ></a></small>&nbsp;&nbsp;<small><a href="#" onclick=openInvTrackingByCrNoIFrame1('+'\''+(crno)+'\''+','+'\''+(group)+'\''+') >Pending Request Details</a></small>';
                               else
                             		rowChkDatanew+='<label class="custom-control-label" for="chkSamplePatient22'+i+'">Requisition Date: '+reqdate+'</label>&nbsp;<label><i class="fas fa-sort-amount-up reqnDtGrupSortUpnew"></i><i class="fas fa-sort-amount-down-alt reqnDtGrupSortDownnew d-none"></i></label><a href="#" ><img class="fullygene" height="25" width="25"   src="media/images/star.svg" >';
                               
              			 
	                	
	                	$(rows).eq( i ).before(
	                    		

	                		
	                        '<tr class="group" style="background-color:#0064ff5c"><td colspan="12">'+rowChkDatanew+'</td><td ></td></tr>'
	                    );
	 
	                    last = group;
	                   
	                }
	            } );
	        
	

                

                for (const [key, value] of mpreqq.entries()) {

                	var iss="#"+key;
                	var keyy=tippy(iss, {
        				delay: 100,
        				arrow: true,
        				arrowType: 'round',
        				size: 'large',
        				duration: 500,
        				animation: 'shift-away-extreme',
        				placement: 'bottom',
        				allowHTML: true,
        				content: value,
        			});

        			
        			tippyLoadingUtlInstance.keyy=keyy[0];
        			

         		      
             	 }
                
            
                
	            
	            var penmsgg="Requisuition Tests/Groups are Billed.";
                
    			var fullygene=tippy('.fullygene', {
    				delay: 100,
    				arrow: true,
    				arrowType: 'round',
    				size: 'large',
    				duration: 500,
    				animation: 'shift-away-extreme',
    				placement: 'bottom',
    				allowHTML: true,
    				content: penmsgg,
    			});


    			tippyLoadingUtlInstance.fullygene=fullygene[0];

    			

	            $("#selectAllDtCheck1").off().on( "change", function(e) {
	            	if ($(this).is( ":checked" )) {
	            		//alert("aa");
	            		sellectAllDtChecknew(true, table);
	            	} else {
	            		sellectAllDtChecknew(false, table);
	            	}

	            	if($(".rowCheckBoxes:checked").length){
	            		saveBtnsAnimations(true, false);
	            	} else {
	            		saveBtnsAnimations(false, false);
	            	}
	            });


	        	 
        		if($('.rowCheckBoxes').length){
    				$('.rowCheckBoxes').off("click").on("click", function (e){
    					
    					var isflg="1";
    					if(this.checked==true)
    						{
    					var reporturll="";
    					
    					var ins_reqno=this.value;
    					
    					//alert("ins_reqno"+ins_reqno);
    					
    					var is_ins_id="#"+ins_reqno+"is_inst";
        					
    						var is_ins=$(is_ins_id).val();
    						var is_ins_fastingtime="";
    						var is_ins_fastingtype="";
    						var is_ins_bladder="";
    						var ins_testt="";

							is_ins_id="#"+ins_reqno+"testt";
							ins_testt=$(is_ins_id).val();
   					
    						if(is_ins=="1")
    							{
    							is_ins_id="#"+ins_reqno+"fastingtime";
        						 is_ins_fastingtime=$(is_ins_id).val();
        						
        						is_ins_id="#"+ins_reqno+"fastingtype";
        						 is_ins_fastingtype=$(is_ins_id).val();
        					
        							is_ins_id="#"+ins_reqno+"testt";
        							ins_testt=$(is_ins_id).val();
           					
    							}
    						else if(is_ins=="2") 
    							{
    						is_ins_id="#"+ins_reqno+"bladder";
       						 is_ins_bladder=$(is_ins_id).val();
       					
    							}
    		
    						
    						if(is_ins=="1" || is_ins=="2")
    							{
    							var msf="Do you want to Collect Sample. ?"
    							var msg="";
    							if(is_ins=="1")
    							 msg="Fasting Required:Yes("+is_ins_fastingtime+" "+is_ins_fastingtype+")<br/>"+msf;
    							else
    								 msg="Full Bladder Required:Yes("+is_ins_bladder+")<br/>"+msf;
    								
    							var select = this; // save select element to variable						 
    						     return swal({
    								  title: "<h5> Test "+ins_testt+" has Instructions:</h5>",
    								  text: msg,
                                       html:true,
    								 // type: "warning",
    								  showCancelButton: true,
    								  confirmButtonClass: "btn-danger",
    								
    								  confirmButtonText: "Ok",
    								  cancelButtonText: "Cancel",
    								  closeOnConfirm: false,
    								  closeOnCancel: false
    								},
    								function(isConfirm) {
    								  if (isConfirm) {
    							            e.preventDefault();
    				    	//				alert("isflg"+isflg);
    				    					
    				    					
    				    					if(this.checked==true && isflg=="1")
    				    						{
    				    						
    				    						var clss=document.getElementsByClassName("reportss");
    				    						var rpts="";
    				    						for(var d=0;d<clss.length;d++)
    				    							{
    				    							
    				    							if(document.getElementsByClassName("reportss")[d].checked==true)
    				    								{
    				    								rpts=rpts+document.getElementsByClassName("reportss")[d].value+"@@";
    				    								
    				    								
    				    								for(var d1=0;d1<clss.length;d1++)
    				    								{
    				    									var rpt=document.getElementsByClassName("reportss")[d1].value;
    				    									
    				    									if(rpts.includes(rpt))
    				    										{
    				    										document.getElementsByClassName("reportss")[d1].checked=true;
    				    										rpts=rpts+rpt+"@@";
    				    										
    				    										}
    				    									
    				    								}
    				    								
    				    								}

    				    							
    				    							}
    				    						
    				    						
    				    						}
    				    					else
    				    						{

    				    								
    				    						var clss=document.getElementsByClassName("reportss");
    				    						var rpts="";
    				    						var rep=this.value;
    				    						
    				    								for(var d1=0;d1<clss.length;d1++)
    				    								{
    				    									var rpt=document.getElementsByClassName("reportss")[d1].value;
    				    									
    				    									if(rep.includes(rpt))
    				    										{
    				    										document.getElementsByClassName("reportss")[d1].checked=false;
    				    										rep=rep+rpt+"@@";
    				    										
    				    										}
    				    									
    				    								}
    				    								

    				    						
    				    							
    				    							
    				    						
    				    						}
    				    					
    				    					if($(".rowCheckBoxes:checked").length){
    				    						saveBtnsAnimations(true, false);
    				    						
    				    						
    				    						
    				    					} else {
    				    						saveBtnsAnimations(false, false);
    				    					}
    				    				  swal.close()	;
    				    			//		return true;
    		    							
    								  } else {
    									  
		    								
				    								

				    						
				    								
    							    //	alert("cc");
    									  isflg="0";	  
    									  select.checked=false;
    									  $(select).parent('td').parent('tr').removeClass("selected");
    									 swal.close()
        									return false;
        								   
    								  }
    								});
    							 
    						     
    						//	 alert("hello");
    						/*	  var txt;
    							  var r = confirm("This Test has some Instructions which needs to be followed in a mandatory basis.\nFasting Required:Yes("+is_ins_fastingtime+" "+is_ins_fastingtype+")");
    							  if (r == true) {
    							    txt = "You pressed OK!";
    							  } else {
    								  isflg="0";	  
    								  this.checked=false;
    								  txt = "You pressed Cancel!";
    							  }*/
    							}
    						
    					     if($(".rowCheckBoxes:checked").length){
		    						saveBtnsAnimations(true, false);
		    						
		    						
		    						
		    					} else {
		    						saveBtnsAnimations(false, false);
		    					}
					
    						
    						}
    					
    					if($(".rowCheckBoxes:checked").length){
    						saveBtnsAnimations(true, false);
    						
    						
    						
    					} else {
    						saveBtnsAnimations(false, false);
    					}
    				});
    				
    			}


        		

	        	if($('.rowCheckBoxesall').length){
	        		$('.rowCheckBoxesall').off().on("change", function (e){
	        			
			        	//alert("cvv");
	        			
	        			var reporturll="";
	        			if(this.checked==true)
	        				{
	        			   var vall=this.value ;
	        			 //  alert("vall"+vall);
	        				var clss=document.getElementsByClassName(vall);
	        				var rpts="";
	        				for(var d1=0;d1<clss.length;d1++)
	        					{
	        				           //alert("all");	
	        					
	        						rpts=rpts+document.getElementsByClassName(vall)[d1].value+"@@";
	        						
	        						
	        							var rpt=document.getElementsByClassName(vall)[d1].value;
	        							
	        								document.getElementsByClassName(vall)[d1].checked=true;
	        								
	        							var ide_td=document.getElementsByClassName(vall)[d1].getAttribute("value");
	        								
	        							
	        							var ins_reqno=ide_td;
	        	    					
	        	    					//alert("ins_reqno"+ins_reqno);
	        	    					
	        	    					var is_ins_id="#"+ins_reqno+"is_inst";
	        	        					
	        	    						var is_ins=$(is_ins_id).val();
	        	    						var is_ins_fastingtime="";
	        	    						var is_ins_fastingtype="";
	        	    						var is_ins_bladder="";
	        	    						
	        	    						if(is_ins=="1")
	        	    							{
	        	    							is_ins_id="#"+ins_reqno+"fastingtime";
	        	        						 is_ins_fastingtime=$(is_ins_id).val();
	        	        						
	        	        						is_ins_id="#"+ins_reqno+"fastingtype";
	        	        						 is_ins_fastingtype=$(is_ins_id).val();
	        	        					
	        	        					
	        	    							}
	        	    						else if(is_ins=="2") 
	        	    							{
	        	    						is_ins_id="#"+ins_reqno+"bladder";
	        	       						 is_ins_bladder=$(is_ins_id).val();
	        	       					
	        	    							}
	        	    		
	        	    						var ins_testt="";

	        								is_ins_id="#"+ins_reqno+"testt";
	        								ins_testt=$(is_ins_id).val();
	        								var msf="Do you want to Collect Sample ?"
	        	        						
	        	    						
	        	    						if(is_ins=="1" || is_ins=="2")
	            							{
	        	    								
	            							var msg="";
	            							if(is_ins=="1")
	            							 msg="Fasting Required:Yes("+is_ins_fastingtime+" "+is_ins_fastingtype+")";
	            							else
	            								 msg="Full Bladder Required:Yes("+is_ins_bladder+")";
	            					
	        	    						  var txt;
	          							  var r = confirm(" Test "+ins_testt+" has Instructions:\n"+msg+"\n"+msf);
	          							  if (r == true) {
	          							
	          								//alert("ide_td"+ide_td);
	  	        							
		        								
	          							  
	          							  } else {
	          								  
	          								document.getElementsByClassName(vall)[d1].checked=false;
	          								}
	          						
	          							  
	          							var elmid=vall+"roww";
        								var elem = document.getElementsByClassName(elmid);     
                                         /*
        								for(var d=0;d<elem.length;d++)
        									{
        						             if(document.getElementsByClassName(vall)[d1].checked==true)
        									elem[d].classList.add("selected");
        						             else
        						            	 elem[d].classList.remove("selected");
         						            	 
        									}*/
        								rpts=rpts+rpt+"@@";
        						
	            							}
	        	    						
	        	    						
	        							
	        							
	        						
	        					

    								
	        					
	        					}
	        				
	        				
	        				if(this.checked==true)
	        					{
	        					var flg=false;
	        					for(var d1=0;d1<clss.length;d1++)
	        					{
	        						if(document.getElementsByClassName(vall)[d1].checked==true)
	        							{
	        							flg=true;
	        							break;
	        							}
	        					}
	        				
	        					if(flg==false)
	        						{
	        						this.checked=false;
	        						}
	        					
	        					}
	        				
	        				
	        				
	        				}
	        			else
	        				{
	        				
	        				var vall=this.value ;
	        				 //  alert("vall"+vall);
	        					var clss=document.getElementsByClassName(vall);
	        					var rpts="";
	        				
	        				
	        				
	        				for(var d1=0;d1<clss.length;d1++)
	        				{
	        					document.getElementsByClassName(vall)[d1].checked=false;
	        						

								var elmid=vall+"roww";
								var elem = document.getElementsByClassName(elmid);     
                                 
								
	        					
	        				}
	        					
	        				
	        				}
	        			
	        			if($(".rowCheckBoxes:checked").length){
	        				saveBtnsAnimations(true, false);
	        				
	        				
	        				
	        			} else {
	        				saveBtnsAnimations(false, false);
	        			}
	        		});
	        		
	        	}




                // Order by the grouping
    		    $('.reqnDtGrupSortUpnew').on( 'click', function () {
    		        var currentOrder = "";
    		        currentOrder=table.order()[0];
    		      //  alert(currentOrder[1]);
    		        
    		      var flg="1" ;
    		        if (currentOrder[1] === 'desc' && flg=='1') {
        		// alert("true");
    		        	flg='0' ;
    		 	table.order([ 4, "asc" ]).draw();
    		       
    		    $(".reqnDtGrupSortDownnew").removeClass("d-none");
				$(".reqnDtGrupSortUpnew").addClass("d-none");
			
			//	alert("asc");
    		        }
    		      
    		    } );
    		  
    		    $('.reqnDtGrupSortDownnew').on( 'click', function () {
    		        var currentOrder = "";
    		        currentOrder=table.order()[0];
    		       // alert(currentOrder[1]);
    		        
    		      var flg="1" ;
    		        if (currentOrder[1] === 'asc' && flg=='1') {
        		// alert("true");
    		        	flg='0' ;
    		 	table.order([ 4, "desc" ]).draw();
    		       
    		    $(".reqnDtGrupSortUpnew").removeClass("d-none");
				$(".reqnDtGrupSortDownnew").addClass("d-none");
			
			//	alert("asc");
    		        }
    		        
    		    } );
    		  
   
	        				
	        			}

	        		
		        

		
	  });
		
		  table.select.info( false);
 }

 
 function ShowRequistionForm()
 {

		var totallen =$("#example").find("#totalId1").val(); 
	for(var i=0;i<totallen ; i++){
	 var labName = "#labName_"+i;
	labName= $("#example").find(labName).text();

	var testName = "#testName_"+i;
	testName= $ ("#example").find(testName).text();
	
	var requisitionNo = "#requisitionNoId_"+i;
	var reqNoo= $("#example").find(requisitionNo).val();
	
	var labCode = "#labCodeId_"+i;
	labCode= $("#example").find(labCode).val();
	
	var testCode = "#testCodeId_"+i;
	testCode= $("#example").find(testCode).val();

	var requisitionDNo = "#requisitionDNoId_"+i;
	requisitionDNo= $("#example").find(requisitionDNo).val();

 	 var status=1;
 	var hmode="EXISTINGREQUISITIONFORMDATA";
 	//alert(testCode);
 	var url1="/HISInvestigationG5/new_investigation/requisitionformprocess.cnt";
 	mywindow=window.open (url1+"?testCode="+testCode+"&testName="+testName+"&labCode="+labCode+"&labName="+labName+"&hmode="+hmode+"&status="+status+"&requisitionDNo="+requisitionDNo+"&reqformtestnames="+reqformtestnames+"&reqformtestcodes="+reqformtestcodes,"_blank","scrollbars=1,directories=no, status=no,width=1000, height=500,top=200,left=500");
 }
 }
 
 
 
 
function EnableSave(chk){
		var i = "checkboxId_"+chk;
		if($("#"+i).prop('checked') == true)
     		{
    		// alert("checked!!!!!!!!"+"#"+i)
    				$("#saveValidateBtn").removeAttr("disabled");
    	
			}
		else{
			
			// alert("Unchecked!!!!!!!!"+"#"+id)
		}
	 
}

function Save(){

//	$('.selectAllDtCheck1').trigger("change");

//alert(pat_ward);

	var totallen =$("#example").find("#totalId1").val(); 
//	alert(totallen);
	var list="";
	var testNameVal = "";
	var sampleNameVal = "" ;
	var sampleNoVal = "";
	for(var i=0;i<totallen ; i++){
		var chk = "checkboxId_"+i;
			
		if($("#"+chk).prop('checked') == true)
     	{
			
			//alert("chk"+chk);
	//		alert("1 method called ............");
			var labName = "#labName_"+i;
			var labNameVal= $("#example").find(labName).text();
			//alert(labNameVal);
			var testName = "#testName_"+i;
			testNameVal= $ ("#example").find(testName).text();
			//alert(testNameVal);
			var requisitionDate = "#requisitionDate_"+i;
			var requisitionDateVal= $("#example").find(requisitionDate).text();
			//alert(requisitionDateVal);
			var sampleName = "#sampleName_"+i;
			 sampleNameVal= $("#example").find(sampleName).text();
			//alert(sampleNameVal);
			var sampleNo = "#sampleNo_"+i;
			 sampleNoVal= $("#example").find(sampleNo).text();
			//alert(sampleNoVal);
			var sampleQnty = "#sampleQnty_"+i;
			var sampleQntyVal= $("#example").find(sampleQnty).val();
			//alert(sampleQntyVal);
			var UOM = "#UOM_"+i;
			var UOMVal= $("#example").find(UOM).val();
			//alert(UOMVal);
			
			var container = "#container_"+i;
			var containerVal= $("#example").find(container).val();
			//alert(containerVal);
			
			var priorityAll = "#priorityAll_"+i;
			var priorityAllVal= $("#example").find(priorityAll).text();
			//alert(priorityAllVal);
			
			var machineCode = "#machineCode_"+i;
			var machineCodeVal= $("#example").find(machineCode).val();
			//alert(machineCodeVal);
			
			var priorityAllCode = "#priorityAllCodeId_"+i;
			var priorityAllCodeVal= $("#example").find(priorityAllCode).val();
			//alert(priorityAllCodeVal);
			
			var patInstruct = "#patInstructId_"+i;
			var patInstructVal= $("#example").find(patInstruct).val();
			//alert(patInstructVal);
			
			var requisitionNo = "#requisitionNoId_"+i;
			var requisitionNoVal= $("#example").find(requisitionNo).val();
			//alert(requisitionNoVal);
			
			var labCode = "#labCodeId_"+i;
			var labCodeVal= $("#example").find(labCode).val();
			//alert(labCodeVal);
			
			var testCode = "#testCodeId_"+i;
			var testCodeVal= $("#example").find(testCode).val();
			//alert(testCodeVal);
			
			var isConfidential = "#isConfidentialId_"+i;
			var isConfidentialVal= $("#example").find(isConfidential).val();
			//alert(isConfidentialVal);
			
			var requisitionDNo = "#requisitionDNoId_"+i;
			var requisitionDNoVal= $("#example").find(requisitionDNo).val();
			//alert(requisitionDNoVal);
			
			var reqDtlStatus = "#reqDtlStatusId_"+i;
			var reqDtlStatusVal= $("#example").find(reqDtlStatus).val();
			//alert(reqDtlStatusVal);
			
			var sampleCode = "#sampleCodeId_"+i;
			var sampleCodeVal= $("#example").find(sampleCode).val();
			//alert(sampleCodeVal);
			
			var defaultContainerCode = "#defaultContainerCodeId_"+i;
			var defaultContainerCodeVal= $("#example").find(defaultContainerCode).val();
			//alert(defaultContainerCodeVal);
			
			var defaultUOMCode = "#defaultUOMCodeId_"+i;
			var defaultUOMCodeVal= $("#example").find(defaultUOMCode).val();
			//alert(defaultUOMCodeVal);
			
			var sampleNoConfiguration = "#sampleNoConfigurationId_"+i;
			var sampleNoConfigurationVal= $("#example").find(sampleNoConfiguration).val();
			//alert(sampleNoConfigurationVal);
			
			var collInstruct = "#collInstructId_"+i;
			var collInstructVal= $("#example").find(collInstruct).val();
			//alert(collInstructVal);
			
			var isrequisitionformneeded = "#isrequisitionformneededId_"+i;
			var isrequisitionformneededVal= $("#example").find(isrequisitionformneeded).val();
			//alert(isrequisitionformneededVal);
			
			var miscDate = "#miscDateId_"+i;
			var miscDateVal= $("#example").find(miscDate).val();
			//alert(miscDateVal);
			
			var billDetail = "#billDetailId_"+i;
			var billDetailVal= $("#example").find(billDetail).val();
			//alert(billDetailVal);
			
			var billNo= "#billNoId_"+i;
			var billNoVal= $("#example").find(billNo).val();
			//alert(billNoVal);
			
			var patType = "#patTypeId_"+i;
			var patTypeVal= $("#example").find(patType).val();
			//alert(patTypeVal);
			
			var patName = "#patNameId_"+i;
			var patNameVal= $("#example").find(patName).val();
				
		    var patCategoryCode=document.getElementById("patCategoryCode_samplecoll01").value;
		//    alert(patCategoryCode);
			var crNo = $('#crNoInput').val();
	 		var sampleAreaCode = document.getElementsByName("sampleAreaCode")[0].value;
	 	//	alert(sampleAreaCode);
	 			var Json={
	 					"crno"  :crNo,
	 					"patName" : patNameVal,
					"labName" : labNameVal,
				 	"testName" :testNameVal,
					"requisitionDate" :requisitionDateVal,
					"sampleName" : sampleNameVal,
				 	"sampleNo" :sampleNoVal,
					"sampleQnty" :sampleQntyVal,
					"UOM" : UOMVal,
				 	"container" :containerVal,
					"priorityAll" :priorityAllVal,
					"machineCode" : machineCodeVal,
					"priorityAllCode" : priorityAllCodeVal,
					"patInstruct" : patInstructVal,
					"requisitionNo" : requisitionNoVal,
					"labCode" : labCodeVal,
					"testCode" : testCodeVal,
					"isConfidential" : isConfidentialVal,
					"requisitionDNo" : requisitionDNoVal,
					"reqDtlStatus" : reqDtlStatusVal,
					"sampleCode" : sampleCodeVal,
					"defaultContainerCode" : defaultContainerCodeVal,
					"defaultUOMCode" : defaultUOMCodeVal,
					"sampleNoConfiguration" : sampleNoConfigurationVal,
					"collInstruct" : collInstructVal,
					"isrequisitionformneeded" : isrequisitionformneededVal,
					"miscDate" : miscDateVal,
					"billDetail" : billDetailVal,
					"billNo" : billNoVal,
					"patType" : patTypeVal,
					"sampleAreaCode" : sampleAreaCode,
					"patCategoryCode":patCategoryCode,
					"wardCode":pat_ward,
				};	
	 	var patientDtl = JSON.stringify(Json);
	//	alert(patientDtl)
 list =CheckAutoSampleNoFormate(Json,labCodeVal,testCodeVal,patTypeVal,sampleNoConfigurationVal,sampleAreaCode);
	  
     	}
	}
	var listFinal =list.replace(/\\/g, "");
	//alert(listFinal);
	$("#selectAllDtCheck1").prop('checked', false);

	savemethodHit(listFinal,crNo,sampleNoVal,sampleNameVal,testNameVal)

	
	
}

function savemethodHit(listFinal,crNo,sampleNoVal,sampleNameVal,testNameVal){
	//----------------------------------------------------FINAL SAVE-------------------------------------------------------------
	//alert(listFinal);
	var res = "" ;
	var flag =0;
	var err1 =crNo;
	var err2 =sampleNoVal;
	var err3 = sampleNameVal;
	var err4 = testNameVal;
	$ .ajax({
			type : "POST",
			url :"/HISInvestigationG5/new_investigation/NewsampleCollection.cnt?hmode=AjaxSaveDetails",
			 async :false,
			 contentType: "application/json",
			  data:listFinal,
			 
			success : function(response) {
				//alert(response);
				 res =response;
					},
			error : function(data) {

			}
		});
	if(res=="There was Some Problem.Please Try Again")
	  	{
	  	flag=0;
	  	}
	  
	else{
	flag=flag+1;
		}
	
	if(flag==0)
	{
	
	$('#save_id').append('There is some problem in Saving Data !!!!!');
	JObjectArray=[];
	jsonObject={};	
	}
	else{
		var html = $.parseHTML( "<htm><body>"+res+"</body></html>");
		//alert(res);
		//alert(html);
	$('#save_nor_id').empty();
	$('#save_nor_id').append(html);
	var url="/HISInvestigationG5/new_investigation/sampleCollection.cnt?hmode=PRINT";
	openPopuper(url,180,500);
	JObjectArray=[];
	jsonObject={};
//	resetScreenFun();
	if(pat_ward!="" &&  pat_crno!="")
		{
		callonloadaftersave();
		}
	else
		{

		resetScreenFun();
		}
	
		}
}

function resetScreenFun()
{

	var totallen =$("#example").find("#totalId1").val(); 
	//alert(totallen);
	for(var i=0;i<totallen ; i++){
		var chk = "checkboxId_"+i;
	if($("#"+chk).prop('checked') == true)
		{
		//alert("hi...");
		//$("#"+chk).closest("tr").remove();
		//$("#"+chk).closest("tr").addClass('row-disabled');
		//var value = $("#billedSpan").val();
		//alert(value);
		//value=value-1;
		//$("#billedSpan").val()=value;
		$('#billedSpan').empty();
		$('#UnbilledSpan').empty();
		
		$("#saveValidateBtn").attr("disabled", true);
			    var crno = $('#crNoInput').val();
			    if (crno.length == 15) {
		        	$('#patDetails').removeClass("d-none");
		        	var globalSearchParam={ crNo:crno, billNo:"",searchType:"1"};
		        		AjaxGetPatDetails(globalSearchParam);
		        		$.when(globalAjaxGetPatDtlObj ).then(function( data, textStatus, jqXHR ){
		        		// alert("Loading...");
		    				var patDtl = globalPatientDetails[0].patStatusCode;
		            		//alert(patDtl);
		            		billedUnbilledfun(patDtl);
		            		$('#tabsId').removeClass("d-none");
		       	    	 });
						}
		        	else{
		        		alert("Cr. No. Should Be Of 15 Digits");
						$('#crNoInput').focus();
		        	}
			    
			   
		}
	}
	
}


//----------------------------------------------------------barcode popuper---------------------------------------------------------------------------

function openPopuper(url,height, width)
{
   	var child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
  	child.moveTo(250,250);
 	child.focus(); 
	if(!child.opener)
   		child.opener = self;
  	return child;
}






var JObjectArray=[];
var jsonObject={};


function CheckAutoSampleNoFormate(patientDtl,LabCode,TestCode,patType,tempSampleNo,sampleAreaCode)
{ var list123 = "";
	var autoGenFormate = "";
	var _mode = "AJAX_CHECK_AUTO_SAMPLENO_GEN";
	var autoGen = "";
	var url = "/HISInvestigationG5/new_investigation/sampleCollection.cnt?hmode="+_mode+"&labCode="+LabCode+"&testCode="+TestCode+"&patType="+patType+"&tempSampleNo="+tempSampleNo+"&sampleAreaCode="+sampleAreaCode;
//alert(url);
		$ .ajax({
		type : "POST",
		url :url,
		dataType : "JSON",
		 async :false,
		success : function(data) {
			if(!$.isEmptyObject(data)) {
				//alert("hi");
				if(data) {
					//alert(data);
					auto1 = data.split("#")[0];
					auto2 = data.split("#")[1];
					auto3 = data.split("#")[2];
					//alert(auto3);
					auto4 = data.split("#")[3];
					auto5 = data.split("#")[4];
					auto6 = data.split("#")[5];
					auto7 = data.split("#")[6];
					auto8 = data.split("#")[7];
					auto9 = data.split("#")[8];
					auto10 = data.split("#")[9];
					auto11 = data.split("#")[10];
					auto12 = data.split("#")[11];
					auto13 = data.split("#")[12];
					autoGenFormate ={
							"sampleNoFormat" :auto1,
							"initDate" :auto2,
							"noOfSeqDigit" : auto3,
							"fromSeries" :auto4,
							"toSeries" : auto5,
							"initType":auto6,
							"runningSampleNo" : auto7,
							"patType" : auto8,
							"configLab" : auto9,
							"configType" : auto10,
							"configSeq" : auto11,
							"configTest" : auto12,  
							"configArea" :auto13,
							}; 
					var autoGen = JSON.stringify(autoGenFormate);
			
					 list123 =saveProces(autoGenFormate,patientDtl);
						}
				
				
			 			}

		

		},
		error : function(data) {

		}
	});
		//alert(list123);
return list123;
	
}

function saveProces(autoGen,patientDtl){
	//alert("last one called !!!!!!!!!!!!");
	var obj = $.extend({},patientDtl,autoGen);
	var fullDetail =  JSON.stringify(obj);
	//alert(fullDetail)
	var list =  finalSave(obj);
//	alert(list)
	return (list);
}

var JObjectArray=[];
var jsonObject={};

function finalSave(obj)
	{
			JObjectArray.push(obj);
			
			jsonObject["selectedList"]=JObjectArray;
			var listJSON = JSON.stringify(jsonObject);
			//console.log("listing is:-"+listJSON);
			//alert(listJSON);
			return (listJSON);
}

//----------------------------------------------------------------------duplicate barcode generation--------------------------------------------
function showduplicatebarcode()
{
	 $('#container2').addClass("d-none");
	 $('#patDetails').addClass("d-none");
	 $('#tabsId').addClass("d-none");
	 $('#container21').removeClass("d-none");
	 document.getElementsByName("sampleAreaCode")[0].value="-1";
	 document.getElementById("crNoInput").value="";
	 
	 document.getElementsByName("duplicateBarcodeGen")[0].checked = true;
	 document.getElementsByName("duplicateBarcodeGeneration")[0].checked = false;
}


function showsamplecol()
{
	 $('#container21').addClass("d-none");
	 $('#barcodePatDivId').addClass("d-none");
	 $('#container2').removeClass("d-none");
	 document.getElementById("crNoInput1").value="";
	 document.getElementsByName("samplesection")[0].checked = false;
	
}

function EnableSave1(chk){
	var i = "checkboxId_"+chk;
	if($("#"+i).prop('checked') == true)
 		{
		// alert("checked!!!!!!!!"+"#"+i)
				$("#saveGENBtn").removeAttr("disabled");
	
		}
	else{
		
		// alert("Unchecked!!!!!!!!"+"#"+id)
	}
 
}

$(document).ready(function() {

	  $('#getDataForDupBarCode').click(function() {
		  document.getElementsByName("isSampleAreaSelected")[0].value="1";
			document.getElementsByName("duplicateBarcodeGeneration")[0].value="0";
			var duplicateBarcodeGeneration = document.getElementsByName("duplicateBarcodeGeneration")[0].value;
			var isSampleAreaSelected =  document.getElementsByName("isSampleAreaSelected")[0].value;
			var showStatus = "3";
			var patCRNo = $('#crNoInput1').val();
			var modebarcode = "0";
			var sampleAreaCode = "-1";
			
	        if (patCRNo.length == 15) {
	        	 		var _mode = "AjaxNEWDUP";
	        			 var url = "/HISInvestigationG5/new_investigation/sampleCollection.cnt?hmode=" + _mode + "&duplicateBarcodeGeneration=" + duplicateBarcodeGeneration +"&isSampleAreaSelected=" + isSampleAreaSelected +"&showStatus=" + showStatus +"&patCRNo=" + patCRNo +"&modebarcode=" + modebarcode +"&sampleAreaCode=" +sampleAreaCode;
	        				//alert(url);
	        				var ajaxObjectArr1 ={};
	        				 ajaxObjectArr1.AjaxGetPatDetails=$.getJSON(url, function(data) {
	        				 	if(!$.isEmptyObject(data)) {
	        							//alert("hi");
	        							if(data) {
	        								//alert(data);
	        									$('#barcodeSpan').empty();
	        									var dupBar = data.DuplicateBarCodePatientData.length;
												$('#barcodeSpan').append(dupBar);
	        								dataTableDuplicateBarCodePatDetails(data.DuplicateBarCodePatientData)
	        								
	        									}
	        								
	        				 }
	        				 })
	        				 .done(function(data) {
	        					  console.log("%cAjaxGetPatDetails success | ResponseData Is Below", "color:green;");
	        					  console.log(data);
	        					})
	        					.fail(function( jqxhr, textStatus, error ) {
	        					  console.log("%cAjaxGetPatDetails Failed | ResponseError Is Below", "color:red;");
	        					  var err = textStatus + ", " + error;
	        					  console.log( "Request Failed: " + err );
	        					});
	        				  	
		        showLoding(false);
	        }
	        else{
	        	alert("Invalid Cr No!!");
	        	
	        }
	   
	  });
	});

//-----------------------------------------------------getting data for duplicate barcode printing-------------------------------------------------
 function dataTableDuplicateBarCodePatDetails(DuplicateBarCodePatientData){
	 $('#exampleBarCode').DataTable().clear().destroy();
	 $('#barcodePatDivId').removeClass("d-none");
		$('#exampleBarCode').removeClass("d-none");
		//$('#billedSpan').empty();
		//$('#UnbilledSpan').empty();
	 var chk=0;
	 var str='';
	 var strSugar='';
	 if(Object.keys(DuplicateBarCodePatientData).length > 0){
			for(var i=0;i<Object.keys(DuplicateBarCodePatientData).length;i++){ 
				if (DuplicateBarCodePatientData[i].sugarTestCode) {
					strSugar = '<td  id="tempSampleNo_'+chk+'">'+DuplicateBarCodePatientData[i].tempSampleNo+DuplicateBarCodePatientData[i].sugarTestCode+'<input type="hidden" id="sampleCodeId_'+chk+'" value="'+DuplicateBarCodePatientData[i].sampleCode+'"></td>';
				} else {

					strSugar ='<td  id="tempSampleNo_'+chk+'">'+DuplicateBarCodePatientData[i].tempSampleNo+'<input type="hidden" id="sampleCodeId_'+chk+'" value="'+DuplicateBarCodePatientData[i].sampleCode+'"></td>';

				}
				
				
				
				var len = DuplicateBarCodePatientData.length; 
				str+='<tr>'
				+'<td></td>'
				+'<td ><input type="checkbox" name="checkboxName_'+chk+'" id="checkboxId_'+chk+'" onClick="EnableSave1('+chk+'); "><input type="hidden" id="totalId" value="'+len+'"><input type="hidden" id="sampleNoId_'+chk+'" value="'+DuplicateBarCodePatientData[i].sampleNo+'"><input type="hidden" id="patVisitNoId_'+chk+'" value="'+DuplicateBarCodePatientData[i].patVisitNo+'"> </td>'
				+'<td id="patCRNo_'+chk+'">'+DuplicateBarCodePatientData[i].patCRNo+' <input type="hidden" id="requisitionNoId_'+chk+'" value="'+DuplicateBarCodePatientData[i].requisitionNo+'"><input type="hidden" id="labCodeId_'+chk+'" value="'+DuplicateBarCodePatientData[i].labCode+'"> </td>'
				+'<td  id="patName_'+chk+'">'+DuplicateBarCodePatientData[i].patName+'<input type="hidden" id="testCodeId_'+chk+'" value="'+DuplicateBarCodePatientData[i].testCode+'"></td>'	
				+'<td  id="patAge_'+chk+'">'+DuplicateBarCodePatientData[i].patAge+'<input type="hidden" id="patGenderCodeId_'+chk+'" value="'+DuplicateBarCodePatientData[i].patGenderCode+'"><input type="hidden" id="patDOBId_'+chk+'" value="'+DuplicateBarCodePatientData[i].patDOB+'"></td>'
				+'<td  id="labName_'+chk+'">'+DuplicateBarCodePatientData[i].labName+'<input type="hidden" id="reqHeaderStatusId_'+chk+'" value="'+DuplicateBarCodePatientData[i].reqHeaderStatus+'"><input type="hidden" id="sampleNameId_'+chk+'" value="'+DuplicateBarCodePatientData[i].sampleName+'"></td>'
				+'<td id="testName_'+chk+'">'+DuplicateBarCodePatientData[i].testName+'</td>'
				+strSugar
				+'<td id="requisitionDate_'+chk+'">'+DuplicateBarCodePatientData[i].requisitionDate+'<input type="hidden" id="patEmailId_'+chk+'" value="'+DuplicateBarCodePatientData[i].patEmail+'"><input type="hidden" id="patCategoryId_'+chk+'" value="'+DuplicateBarCodePatientData[i].patCategory+'"></td>'
				+'<td id="patStatus_'+chk+'">'+DuplicateBarCodePatientData[i].patStatus+'</td>'
				+'<td id="patDeptUnitName_'+chk+'">'+DuplicateBarCodePatientData[i].patDeptName+'-'+DuplicateBarCodePatientData[i].patUnitName+'<input type="hidden" id="patDeptUnitCodeId_'+chk+'" value="'+DuplicateBarCodePatientData[i].patDeptUnitCode+'"><input type="hidden" id="sampleCollectionDateId_'+chk+'" value="'+DuplicateBarCodePatientData[i].sampleCollectionDate+'"></td>'
				+'<td id="patVisitDate_'+chk+'">'+DuplicateBarCodePatientData[i].patVisitDate+'<input type="hidden" id="miscDateId_'+chk+'" value="'+DuplicateBarCodePatientData[i].miscDate+'"><input type="hidden" id="sugarTestCodeId_'+chk+'" value="'+DuplicateBarCodePatientData[i].sugarTestCode+'"></td>'
				+'</tr>'; 
			chk=chk+1;
		}
			
		}
		// 
		$('#exampleBarCode').append(str);
		 $('<thead></thead>').prependTo('#exampleBarCode').append($('#exampleBarCode tr:first'));
		
		  var table = $('#exampleBarCode').DataTable({
				rowReorder: true,
				responsive: true,
		        select: {
		            style:    'multi',
		           
		        },
		        order: [[ 1, 'asc' ]],
		        "language": { "emptyTable": "No Data Is Available " }
		
	  });
		  table.column( 7 ).data().unique();
		  
}


function GenerateBarcode(){
	var totallen =$("#exampleBarCode").find("#totalId").val(); 
	var list = '';
	for(var i=0;i<totallen ; i++){
		var chk = "checkboxId_"+i;
	if($("#"+chk).prop('checked') == true)
 	{
		//alert("hi");
		var patCRNo = "#patCRNo_"+i;
		var patCRNoVal= $("#exampleBarCode").find(patCRNo).text();
		
		var patName = "#patName_"+i;
		patNameVal= $ ("#exampleBarCode").find(patName).text();
		
		var labName = "#labName_"+i;
		var labNameVal= $("#exampleBarCode").find(labName).text();
		
		var SampleNo = "#tempSampleNo_"+i;
		SampleNoVal= $("#exampleBarCode").find(SampleNo).text();
	
		var requisitionDate = "#requisitionDate_"+i;
		requisitionDateVal= $("#exampleBarCode").find(requisitionDate).text();

		var patAge = "#patAge_"+i;
		var patAgeVal= $("#exampleBarCode").find(patAge).text();
		
		var patStatus = "#patStatus_"+i;
		var patStatusVal= $("#exampleBarCode").find(patStatus).text();
	
		
		var patDeptUnitName = "#patDeptUnitName_"+i;
		var patDeptUnitNameVal= $("#exampleBarCode").find(patDeptUnitName).text();
		//alert(containerVal);
		
		var patVisitDate = "#patVisitDate_"+i;
		var patVisitDateVal= $("#exampleBarCode").find(patVisitDate).text();
		
		
		var miscDateId = "#miscDateId_"+i;
		var miscDateIdVal= $("#exampleBarCode").find(miscDateId).text();
		//alert(machineCodeVal);
		
		var sugarTestCodeId = "#sugarTestCodeId_"+i;
		var sugarTestCodeIdVal= $("#exampleBarCode").find(sugarTestCodeId).val();
		//alert(priorityAllCodeVal);
		
		var sampleCollectionDateId = "#sampleCollectionDateId_"+i;
		var sampleCollectionDateIdVal= $("#exampleBarCode").find(sampleCollectionDateId).val();
		//alert(patInstructVal);
		
		var requisitionNo = "#requisitionNoId_"+i;
		var requisitionNoVal= $("#exampleBarCode").find(requisitionNo).val();
		//alert(requisitionNoVal);
		
		var labCode = "#labCodeId_"+i;
		var labCodeVal= $("#exampleBarCode").find(labCode).val();
		//alert(labCodeVal);
		
		var testCode = "#testCodeId_"+i;
		var testCodeVal= $("#exampleBarCode").find(testCode).text();
		//alert(testCodeVal);
		
		var patDeptUnitCodeId = "#patDeptUnitCodeId_"+i;
		var patDeptUnitCodeIdVal= $("#exampleBarCode").find(patDeptUnitCodeId).text();
		//alert(isConfidentialVal);
		
		
		var sampleCode = "#sampleCodeId_"+i;
		var sampleCodeVal= $("#exampleBarCode").find(sampleCode).val();
		//alert(sampleCodeVal);
		
		var sampleNameId = "#sampleNameId_"+i;
		var sampleNameIdVal= $("#exampleBarCode").find(sampleNameId).val();

 			var Json={
 					"crno"  :patCRNoVal,
				"patName" : patNameVal,
			 	"labName" :labNameVal,
				"SampleNo" :SampleNoVal,
				"requisitionDate" :requisitionDateVal,
				"patAge" : patAgeVal,
			 	"patStatus" :patStatusVal,
				"patDeptUnitName" :patDeptUnitNameVal,
				"patVisitDate" : patVisitDateVal,
				"miscDate" : miscDateIdVal,
				"sugarTestCode" : sugarTestCodeIdVal,
				"sampleCollectionDate" : sampleCollectionDateIdVal,
				"requisitionNo" : requisitionNoVal,
				"labCode" : labCodeVal,
				"sampleCode" : sampleCodeVal,
				"sampleName" : sampleNameIdVal,
			
			
			};	
 	var patientDtl = JSON.stringify(Json);
 	//alert(patientDtl)
 	list = finalJson(Json)
	}	
	}
	$("#dupselectallchk").prop('checked', false);

	//alert("first method alert"+list);
	saveBarcodemethodHit(list)
}


var JObjectArray1=[];
var jsonObject1={};

function finalJson(Json)
	{
			JObjectArray1.push(Json);
			jsonObject1["selectedListForDuplicateBarcode"]=JObjectArray1;
			var listJSON1 = JSON.stringify(jsonObject1);
			//alert(listJSON1)
			return (listJSON1);
}

function saveBarcodemethodHit(listFinal){
	//----------------------------------------------------FINAL SAVE-------------------------------------------------------------
	//alert(listFinal);
	var res = "" ;
	var flag =0;
		$ .ajax({
			type : "POST",
			url :"/HISInvestigationG5/new_investigation/sampleCollection.cnt?hmode=AJAXDUPLICATEBARCODE",
			 async :false,
			 contentType: "application/json",
			  data:listFinal,
			 
			success : function(response) {
				//alert(response);
				 res =response;
					},
			error : function(data) {

			}
		});
	if(res=="Barcode generated succefully")
	  	{
	  	flag=flag+1;
	  	}
	  
	else{
	flag=0;
		}
	
	if(flag==0)
	{
	$('#save_id').text('There is some problem in Saving Data !!!!!');
	
	
	}
	else{
		var totallen =$("#exampleBarCode").find("#totalId").val(); 
		for(var i=0;i<totallen ; i++){
			var chk = "checkboxId_"+i;
		if($("#"+chk).prop('checked') == true)
			{
			//alert("hi...");
			$("#"+chk).prop('checked', false);
			
			$("#saveGENBtn").attr("disabled", true);
			}
		}
	var url="/HISInvestigationG5/new_investigation/sampleCollection.cnt?hmode=PRINT";
	openPopuper(url,180,500);
	}	
	JObjectArray1=[];
	 jsonObject1={};
}

//CancelBtn
$(document).ready(function() {

  $('#CancelBtn1').click(function() {
		document.getElementsByName('hmode')[0].value='NEW';
		document.forms[0].submit();
  
  });
  });
$(document).ready(function() {

	  $('#CancelBtn2').click(function() {
			document.getElementsByName('hmode')[0].value='NEW';
			document.forms[0].submit();
	  
	  });
	  });
	  
$(document).ready(function() {

	  $('#CancelBtn3').click(function() {
			document.getElementsByName('hmode')[0].value='NEW';
			document.forms[0].submit();
	  
	  });
	  });

function selectAll()
{
	var totallen =$("#example").find("#totalId1").val(); 
	if($("#selectallchk").prop('checked') == true)
	{
		
		//alert(totallen);
		for(var i=0;i<totallen ; i++){
			var chk = "checkboxId_"+i;
			$("#"+chk).prop('checked', true);
		}
		EnableSaveAll();
	}
	else
	{
	for(var i=0;i<totallen ; i++){
		var chk = "checkboxId_"+i;
		$("#"+chk).prop('checked', false);
	}
	}

	
}


function EnableSaveAll(){
	$("#saveValidateBtn").removeAttr("disabled");
    	
		
}



function dupselectAll()
{
	var totallen =$("#exampleBarCode").find("#totalId").val(); 
	if($("#dupselectallchk").prop('checked') == true)
	{
		
		//alert(totallen);
		for(var i=0;i<totallen ; i++){
			var chk = "checkboxId_"+i;
			$("#"+chk).prop('checked', true);
		}
		DupEnableSaveAll();
	}
	else
	{
	for(var i=0;i<totallen ; i++){
		var chk = "checkboxId_"+i;
		$("#"+chk).prop('checked', false);
	}
	}

	
}


function DupEnableSaveAll(){
	$("#saveGENBtn").removeAttr("disabled");
    	
		
}



function saveBtnsAnimations(enableFlag, animateFlag){
	if(enableFlag==true && animateFlag==false){
		$('#saveValidateBtn').attr({"disabled":false});
		$('#modifyBtn').attr({"disabled":false});
		
		$('.saveValidateBtnSpan').removeClass("d-none");
		$('.validatingBtnSpan').addClass("d-none");
		
		tippyLoadingUtlInstance.saveValBtnDiv.setContent('Click to Save Records');
	//	tippyLoadingUtlInstance.modifyValBtnDiv.setContent('Click to Modify Selected Requisition');
	//	alert("sxsxs");
		enableRowCheckBoxes(true);

	}else if (enableFlag==false && animateFlag==false){
		$('#saveValidateBtn').attr({"disabled":true});
		$('#modifyBtn').attr({"disabled":true});
		
		$('.saveValidateBtnSpan').removeClass("d-none");
		$('.validatingBtnSpan').addClass("d-none");
		
		tippyLoadingUtlInstance.saveValBtnDiv.setContent('Please select atleast one Record to View Reports');
		//tippyLoadingUtlInstance.modifyValBtnDiv.setContent('Please select atleast one Requisition to Modify');
		//
		enableRowCheckBoxes(true);
		
	}else if (enableFlag==false && animateFlag==true){
		$('#saveValidateBtn').attr({"disabled":true});
		$('#modifyBtn').attr({"disabled":true});
		
		$('.saveValidateBtnSpan').addClass("d-none");
		$('.validatingBtnSpan').removeClass("d-none");
		
		tippyLoadingUtlInstance.saveValBtnDiv.setContent('Please wait while current Requisition is Save');
	//	tippyLoadingUtlInstance.modifyValBtnDiv.setContent('Please wait while current Requisition is Validated');
		
		enableRowCheckBoxes(false);
	}
}



function sellectAllDtChecknew(booleanCheck, table){
if (booleanCheck == true) {
table.rows().select();
$('.rowCheckBoxesall').prop("checked", true);
$('.rowCheckBoxesall').trigger("change");
//table.rows().$(tr td:nth-child(2) input).prop("checked", true);
}
else {
table.rows().deselect();
$('.rowCheckBoxesall').prop("checked", false);
$('.rowCheckBoxesall').trigger("change");

}
}


function enableRowCheckBoxes(enableRowChkBxFlag){
	if(enableRowChkBxFlag==true){
		//alert("as");

		
		if($('.rowCheckBoxes').length){
			
			$('.rowCheckBoxes').attr({"disabled":false});
		//	alert("checked");
		
		
		}
	//	if($('.selectAllDtCheck1').length){$('.rowCheckBoxes').attr({"disabled":false});}
		//if($('.selectAllDtCheck2').length){$('.rowCheckBoxes').attr({"disabled":false});}
	} else {
		if($('.rowCheckBoxes').length){
			
			$('.rowCheckBoxes').attr({"disabled":true});
			
		//alert("false");
		}
	//	if($('.selectAllDtCheck1').length){$('.rowCheckBoxes').attr({"disabled":true});}
		//if($('.selectAllDtCheck2').length){$('.rowCheckBoxes').attr({"disabled":true});}
	}
}




function openInvTrackingByCrNoIFrame1(crno1,reqno1){

	//alert("reqno");
	reqno1=reqno1+"@@partially";
	var _mode = "UrlExternalCall";
	var url="/HISInvestigationG5/new_investigation/InvestigationTrackingReport.cnt?hmode="+_mode+"&crNo="+crno1+"&searchType=1"+"&requisitionNo="+reqno1;
	$.fancybox.destroy();
	$(".fancyBoxIFrame").attr({"href":url});
	$(".fancyBoxIFrame").click();

}


$(document).ready(function() {
	$(".fancyBoxIFrame").fancybox({
		//closeExisting: false,
		type: 'iframe',
		toolbar  : false,
		smallBtn : true,
		transitionEffect: "zoom-in-out",
		transitionDuration: 366,
		iframe : {
			// Iframe template
			//tpl: '<iframe id="fancybox-frame{rnd}" name="fancybox-frame{rnd}" class="fancybox-iframe" allowfullscreen allow="autoplay; fullscreen" src=""></iframe>',
			preload : true,
			css : {
//				'width':$fancyboxWidth,
//				'height':$fancyboxHeight,
				'width'  : '100%',
				'height' : '100%',
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
		},
		afterClose: function( instance, current ) {
			//actionOnFancyClose();
		}
	});
});



function callonloadaftersave()
{

	

//	alert("12");	

//alert(pat_ward);

document.getElementsByName("sampleAreaCode")[0].value=pat_ward;

$('#crNoInput').val=pat_crno;


//document.getElementsByName("patCrNo")[0].value="961012000000732";
document.getElementById("crNoInput").value=pat_crno;
//document.getElementById("patStatusCode")[0].value="";
document.getElementsByName("sampleAreaCode")[0].value=pat_ward;
document.getElementsByName("patStatusCode")[0].value=pat_reqtype;

var crno = $('#crNoInput').val();
$('#patDetails').removeClass("d-none");

var globalSearchParam={ crNo:crno, billNo:"",searchType:"1"};
AjaxGetPatDetails(globalSearchParam);

$.when(globalAjaxGetPatDtlObj ).then(function( data, textStatus, jqXHR ){
	//console.log("fffffff");
 //alert("Loading...");
	var patDtl = globalPatientDetails[0].patStatusCode;
	//alert(patDtl);
	billedUnbilledfun_ipd("2",pat_ward);
	$('#tabsId').removeClass("d-none");
	 });
	 
showLoding(false);
$('#container2ExpandBtn').click();
$("#container2ExpandBtn").off("click");
$('#container2ExpandBtn').addClass("d-none");
$('#CancelBtn1').addClass("d-none");


}