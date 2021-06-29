/**
 * Javascript functions
 */

var GovtColor = '#F0AAAD';// '#ffb366';

function setTable(url) {
	var table;
	if ($.fn.dataTable.isDataTable('#example-table')) {
		table = $('#example-table').dataTable();
		var newUrl = url;
		table.fnReloadAjax(newUrl);
	}

	else {
		table = $('#example-table').DataTable({
			
			"ajax" : url,
			"deferRender" : true,
			"fixedHeader" : true,
			"responsive" : true,
			"createdRow": function( row, data, dataIndex ) {
			    if ( data[5] == "Govt." ) {
			    	$(row).css('background-color', GovtColor)
			    }
			  }

		});
	}
	return table;
}

function updateMapLocation()
{	
	console.log("here");
	$.ajax({
	    url: "/BLDAHIMS/bloodbank/nearbyBB.cnt?hmode=FETCHLOCATION",
	    success: function(data) {
	    	$.locations = data; 
	    },
	    async:false
	  });
	$.locations = JSON.parse($.locations);
	console.log("dfdfdf"+$.locations);        
}

function loadDistrictList(stateCode) {

	$('#dCode').val("-1");
	$('#sCode').val(stateCode);
	setSearchMode(SearchEnum.DROPDOWN);
	$.getJSON("/BLDAHIMS/bloodbank/nearbyBB.cnt?hmode=GETDISTRICTLIST", {
		selectedStateCode : stateCode
	}, function(data) {
		$("#distList option").not(':first').remove(); // Remove all <option> child tags.
		console.log(data);
		$.each(data.records, function(index, item) { // Iterates through a collection
			$("#distList").append( // Append an object to the inside of the select box
			$("<option></option>").text(item.id).val(item.value));
		});
	});
}
function geolocate() {

}

function changeFont()
{
	$(".increaseFont,.decreaseFont").click(function(){
		  var type=  $(this).val();
		   var curFontSize = $('.data').css('font-size');
		   alert(curFontSize);
		   if(type=='increase'){
		    $('.data').css('font-size', parseInt(curFontSize)+1);
		    }
		   else{
		    $('.data').css('font-size', parseInt(curFontSize)-1);
		   }
		   alert($('.data').css('font-size'));

		     });	
}

// Contact Details
function fetchContacts()
{
	$.getJSON("/BLDAHIMS/bloodbank/nearbyBB.cnt?hmode=CONTACTLIST",		
	 function(data) {
		var count = data.count;
	
		if(count == 0) {
			$('#contacts').append("No contact details found");
		} else {
			for(i = 0; i < count; i++) {
				var title = "<h4>" + data.contact[i].title + "</h4>";
				var detail = "<p>" + data.contact[i].name + "</br>" + data.contact[i].addr + "</br>" + data.contact[i].phone + "<br/> " + data.contact[i].email + "</p>";
				var contacts = title + detail;
				$('#contacts').append(contacts);
			}
		}
	});
}
	

function fetchNotifications()
{	
	$.ajax({
	    url: "/BLDAHIMS/bloodbank/nearbyBB.cnt?hmode=FETCHNOTIFICATION&type=4",
	    success: function(data) {	    		    	
	    	 $('#notifications').append(data);
	    	 $(".various").fancybox({
	 			
	 			maxWidth	: 800,
	 			maxHeight	: 600,
	 			fitToView	: false,
	 			width		: '70%',
	 			height		: '70%',
	 			autoSize	: false,
	 			closeClick	: false,
	 			openEffect	: 'none',
	 			closeEffect	: 'none',
	 			helpers: {
	 			    overlay: {
	 			      locked: false
	 			    }
	 			  }
	 		});// fancybox
	    }
	  });	
}

function getState(stateListType)
{
	$.getJSON("/BLDAHIMS/bloodbank/nearbyBB.cnt?hmode=GETSTATELIST&statetype="+ stateListType,		
			 function(data) {
		var comboHtml = "";
		$.each(data, function(index, element) {
			$('#donorAddStateCode').append(new Option(element.label, element.value));
		});
	});
}

function setStockTable(url) {
	var table;
	if ($.fn.dataTable.isDataTable('#example-table')) {
		table = $('#example-table').dataTable();
		var newUrl = url;
		table.fnReloadAjax(newUrl);
	}

	else {
		table = $('#example-table').DataTable({
			
			"ajax" : url,
			"deferRender" : true,
			"fixedHeader" : true,
			"responsive" : true,
			"createdRow": function( row, data, dataIndex ) {			   
				if ( data[2] == "Govt." ) {
			    	$(row).css('background-color', GovtColor)
			    }			    
			  }
		});
	}
	return table;
}


	
