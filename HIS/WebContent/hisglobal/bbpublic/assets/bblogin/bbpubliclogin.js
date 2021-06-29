function loadDistrictList(stateCode) {

// 		$('#dCode').val("-1");
// 		$('#sCode').val(stateCode);
		
		$.getJSON("/BLDAHIMS/bloodbank/nearbyBB.cnt?hmode=GETDISTRICTLIST", {
			selectedStateCode : stateCode
		}, function(data) {
			$("#distList option").not(':first').remove(); // Remove all <option> child tags.
// 			console.log(data);
			$.each(data.records, function(index, item) { // Iterates through a collection
				$("#distList").append( // Append an object to the inside of the select box
				$("<option></option>").text(item.id).val(item.value));
			});
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