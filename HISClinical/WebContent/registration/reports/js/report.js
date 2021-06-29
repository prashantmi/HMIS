var report={
		processGeneralNode:function(elementName,elementNode)
		  {
			var optionText="";
			if(elementNode.childNodes.length!="0"&&elementName=="strUnitCode")
				optionText="<option value='0'>All</option>";
			else
				optionText="<option value='-1'>Select Value</option>";
			if(elementNode!=null){
				for(var i=0;i<elementNode.childNodes.length;i++ )
				{
					var optionNode=elementNode.childNodes[i];
					optionText+="<option value='"+(optionNode.getAttribute("value"))+"'>"+(optionNode.getAttribute("label"))+"</option>";
					
				}
			}
			if(document.getElementsByName(elementName).length!=0)
					document.getElementsByName(elementName)[0].innerHTML=optionText;			
		  },
		  showTimeTile:function(){
			  if($('[name="strChoice"]')[0].checked){
			  	$("#dateRow").hide();
			  	$("#timeRow").show();
			  	$('[name="fromHour"]').validatebox({required: true,validType:['numeric','numberRangeZeroToTwentythree']});
			  	$('[name="fromMin"]').validatebox({required: true,validType:['numeric','numberRangeZeroToFiftynine']});
			  	$('[name="toHour"]').validatebox({required: true,validType:['numeric','numberRangeZeroToTwentythree']});
			  	$('[name="toMin"]').validatebox({required: true,validType:['numeric','numberRangeZeroToFiftynine']});	
				$("#patFromDateId").validatebox({required: false, validType:null});
				$("#patToDateId").validatebox({required: false, validType: null});

			  }
			  else{
				$("#dateRow").show();
				$("#timeRow").hide();  
				report.showDivDate();
				//report.showDivToDate();
				$('[name="fromHour"]').validatebox({required: false,validType:null});
			  	$('[name="fromMin"]').validatebox({required: false,validType:null});
			  	$('[name="toHour"]').validatebox({required: false,validType:null});
			  	$('[name="toMin"]').validatebox({required: false,validType:null});
				//$("#patFromDateId").validatebox({required: true, validType: ['date[\'fromDate\',\'dd-mmm-yyyy\']','dtltet[\'toDate\',\'FromDate should be less than or equal to ToDate\']']});
				//$("#patToDateId").validatebox({required: true, validType: ['date[\'toDate\',\'dd-mmm-yyyy\']','dtgtet[\'fromDate\',\'ToDate should be greater than or equal to FromDate\']']});

			  }
		  },
		  onLoadTile:function(){
			  $('[name="strChoice"]')[0].checked=true;
			  report.showTimeTile();
		  },
		  showDivDate:function(){
			  $("#divFromDate").show();
			  $("#divToDate").show();
			  $(function() {
					/*$("#patFromDateId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date(new Date().getTime() - (1000 * 60 * 60 * 24)));
					$("#patToDateId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date(new Date().getTime() - (1000 * 60 * 60 * 24)));*/
				  
				 var _date=new Date(new Date().getTime() - (1000 * 60 * 60 * 24)).toLocaleDateString("en-au", {year: "numeric", month: "short",day: "2-digit"}).replace(/ /g,"-").replace('.', ""); 
				 //var _date=new Date(new Date().getTime() - (1000 * 60 * 60 * 24)).toLocaleFormat('%d-%b-%Y');
				 //_date.toLocaleDateString("en-au", {year: "numeric", month: "short",day: "2-digit"}).replace(/ /g,"-").replace('.', "");
				 // var _date = new Date(date.getTime()+date.getTimezoneOffset()*60*1000);
				 $('#patFromDateId').DatePicker({	format: 'd-M-Y',default_position :'below', start_date:_date, onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}} }).val(_date);;
				 $('#patToDateId').DatePicker({ format: 'd-M-Y',default_position :'below', start_date:_date, onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}} }).val(_date);;			  
				  
			  });
		  },
		  checkFromDateValid:function(){
			  $("#patFromDateId").validatebox({required: true, validType: ['date[\'fromDate\',\'dd-mmm-yyyy\']','dtltetlastdt[\'From Date must be lesser than Today\']','dtltet[\'toDate\',\'FromDate should be less than or equal to ToDate\']', 'dtltetctdty[\'Date Should Be Less Than Current Date \']']});
			  
		  },
		  checkToDateValid:function(){
			  $("#patToDateId").validatebox({required: true, validType: ['date[\'toDate\',\'dd-mmm-yyyy\']','dtltetlastdt[\'To Date must be lesser than Today\']','dtgtet[\'fromDate\',\'ToDate should be greater than or equal to FromDate\']', 'dtltetctdty[\'Date Should Be Less Than Current Date \']']});

		  }
		  
};

/*$.datepicker.setDefaults( 
	    {showOn: 'both',
	    	defaultDate: new Date(),
	    	buttonImageOnly: true, 
	    	buttonText : "Select Date",
	    	buttonImage: "../../hisglobal/images/calendar-icon.gif",
	    	stepBigMonths: 12
});*/ 

$(document).ready(function () {
	$('#patFromDateId, #patToDateId').DatePicker({format: 'd-M-Y',onSelect: function(dateStr) {
	   $(this).blur();
	   onCLickDateFn($(this));
	}
	});
	});

function onCLickDateFn(obj){
	obj.focus();
}

function callMenu(url)
{
	//alert('menu called with url: '+ url);
	var targetURL = url;// + "?varSSOTicketGrantingTicket=" + document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
	
	
	var elemFrame = parent.document.getElementById("frmMain");
	if(elemFrame!=null){
		elemFrame.src=targetURL;
		elemFrame.refresh();
	}
	else{
		if(typeof $('#tabframe')!='undefined'){
			var tab = window.parent.$('#tabframe').tabs('getSelected');
			var index = window.parent.$('#tabframe').tabs('getTabIndex',tab);
			window.parent.$('#tabframe').tabs('select',index-1);	
			window.parent.$('#tabframe').tabs('close',index);				
		}
	}
}