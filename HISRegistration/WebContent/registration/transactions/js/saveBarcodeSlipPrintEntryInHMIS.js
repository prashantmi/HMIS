/**
 * Created for CDAC Noida for use in HMIS Reg Module for Barcode Slip On Sep'18*/
function saveEntryForBarcodePrinting_notInCurrentUse() {//dated Sep'18

	var action = "/HISRegistration/registration/transactions/SAVEFROMOUTSIDEBarcodeGeneration.action";
	// alert(action);
	$
			.ajax({
				url : action,
				type : "POST",
				async : true,
				dataType : "xml",
				success : function(action) {
					// alert("success!!\n"+action);
					// do nothing
					/*
					 * var
					 * url="/HISRegistration/registration/transactions/PRINTBarcodeGeneration.action";
					 * 
					 * var child =
					 * window.open(url,'popupWindow','status=yes,scrollbars=yes,height=180,width=500,left=10,top=10');
					 * child.moveTo(250,250); child.focus(); if(!child.opener)
					 * child.opener = self;
					 */

					// alert("insidde showDuplicatePatientPopup");
					var windowWidth = 400;// $(window).width() - 50;
					var windowHeight = 325;

					var page = "/HISRegistration/registration/transactions/PRINTBarcodeGeneration.action";

					var $dialog = $('<div></div>')
							.html(
									'<iframe id="barcodeDialog_iframe" style="border: 0px; " src="'
											+ page
											+ '" width="100%" height="100%"></iframe>')
							.dialog(
									{
										autoOpen : false,
										modal : true,
										height : windowHeight,
										width : windowWidth,
										title : "Patient Barcode Slip",
										show : {
											effect : "clip"
										},
										resizable : true,
										position : [ 'top', 100 ],
										dialogClass : 'no-close custbtncolor',
										buttons : {
											//"Print" : function() {
												/*var barcodeSlipPrintDivContent = $(
														'[name="barcodeDialog_parent"]')
														.val();
												$("#divBarcodeSlipPrintId")
														.html(
																barcodeSlipPrintDivContent);
												var crNo = $('#divBarcodeSlipControl')[0].innerHTML;
												$("#divBarcodeSlipControl").barcode(
														crNo, // Value
														"code128" // type(string)
														);*/
												// Added For Opd Card and Bill
												// Print on the Same Time
												
												/*var oldPage2 =
												  $('body').html();
												  $('body').html( "<html><head><title></title></head><body><p>" +
												  get_object("divBarcodeSlipPrintId").innerHTML + "</p></body>");
												  window.print();
												  $('body').html(oldPage2);*/
												 //alert($(this)[0].innerHTML);

												//$('#divBarcodeSlipPrintId').html($(this)[0].innerHTML).printArea();
												// $(this).dialog("close");
												 //$("#divBarcodeSlipPrintId").html(barcodeSlipPrintDivContent).printArea();
												//alert($('#barcodeDialog').innerHTML);
												//window.print();
											//},
											"Close" : function() {
												$(this).dialog("close");
											}
										},
										open : function() {
											$('.ui-widget-overlay').addClass(
													'custoverlay');
											$('.ui-dialog-buttonpane')
													.find(
															'button:contains("Close")')
													.addClass('no-close custbtncolor');
											$('.ui-dialog-buttonpane')
													.find(
															'button:contains("Print")')
													.addClass('no-close custbtncolor');
										},
										close : function() {
											$(".ui-widget-overlay")
													.removeClass('custoverlay');
										}
									});
					$dialog.dialog('open');
					//$dialog.dialog('close');


				},
				error : function(errorMsg, textstatus, errorthrown) {
					alert('saveEntryForBarcodePrinting ' + errorMsg
							+ " textstatus::" + textstatus + " errorthrown::"
							+ errorthrown);

				}
			});
}

function saveEntryForBarcodePrinting_notneeded() {
	
				var url="/HISRegistration/registration/transactions/PRINTBarcodeGeneration.action";
			    
				var child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height=180,width=500,left=10,top=10');  
				  	child.moveTo(250,250);
				 	child.focus(); 
					if(!child.opener)
				   		child.opener = self;
}

function saveEntryForBarcodePrinting_revertedBackToOriginal() {//dated Sep'18

	//var action = "/HISRegistration/registration/transactions/SAVEFROMOUTSIDEBarcodeGeneration.action";
	// alert(action);
	//$
	//		.ajax({
	//			url : action,
	//			type : "POST",
	//			async : true,
	//			dataType : "xml",
	//			success : function(action) {
					// alert("success!!\n"+action);
					// do nothing
					/*
					 * var
					 * url="/HISRegistration/registration/transactions/PRINTBarcodeGeneration.action";
					 * 
					 * var child =
					 * window.open(url,'popupWindow','status=yes,scrollbars=yes,height=180,width=500,left=10,top=10');
					 * child.moveTo(250,250); child.focus(); if(!child.opener)
					 * child.opener = self;
					 */

					// alert("insidde showDuplicatePatientPopup");
					var windowWidth = 400;// $(window).width() - 50;
					var windowHeight = 250;

					var page = "/HISRegistration/registration/transactions/PRINTBarcodeGeneration.action";
					//var $dialog = $('<div></div>').dialog({autoOpen : false});
					//$dialog.find('script').remove();
					var $dialog = $('<div></div>')
							.html('<iframe id="barcodeDialog_iframe" style="border: 0px; " src="" width="100%" height="100%"></iframe>')
							.dialog(
									{
										autoOpen : false,
										modal : true,
										height : windowHeight,
										width : windowWidth,
										title : "Patient Barcode Slip",
										/*show : {
											effect : "clip"
										},*/
										resizable : true,
										position : [ 'top', 50 ],
										dialogClass : 'no-close custbtncolor',
										buttons : {
											//"Print" : function() {
												/*var barcodeSlipPrintDivContent = $(
														'[name="barcodeDialog_parent"]')
														.val();
												$("#divBarcodeSlipPrintId")
														.html(
																barcodeSlipPrintDivContent);
												var crNo = $('#divBarcodeSlipControl')[0].innerHTML;
												$("#divBarcodeSlipControl").barcode(
														crNo, // Value
														"code128" // type(string)
														);*/
												// Added For Opd Card and Bill
												// Print on the Same Time
												
												/*var oldPage2 =
												  $('body').html();
												  $('body').html( "<html><head><title></title></head><body><p>" +
												  get_object("divBarcodeSlipPrintId").innerHTML + "</p></body>");
												  window.print();
												  $('body').html(oldPage2);*/
												 //alert($(this)[0].innerHTML);

												//$('#divBarcodeSlipPrintId').html($(this)[0].innerHTML).printArea();
												// $(this).dialog("close");
												 //$("#divBarcodeSlipPrintId").html(barcodeSlipPrintDivContent).printArea();
												//alert($('#barcodeDialog').innerHTML);
												//window.print();
											//},
											/*"Print" : function() {
												//$('#bc_cdac').html("");
												//$('#bc_cdac').dialog("destroy").remove();
												 $("#dialog").printArea(); 
												$("#barcodeDialog_iframe").attr('src', "");
												$(this).dialog("close");
												$(this).remove();//.remove();
											},*/
											"Close" : function() {
												//$('#bc_cdac').html("");
												//$('#bc_cdac').dialog("destroy").remove();
												$("#barcodeDialog_iframe").attr('src', "");
												$(this).dialog("close");
												$(this).remove();//.remove();
											}
										},
										open : function() {
											$('.ui-widget-overlay').addClass(
													'custoverlay');
											$('.ui-dialog-buttonpane')
													.find(
															'button:contains("Close")')
													.addClass('custbtncolor');
											$('.ui-dialog-buttonpane')
													.find(
															'button:contains("Print")')
													.addClass('custbtncolor');
											
										},
										close : function() {
											$(".ui-widget-overlay")
													.removeClass('custoverlay');
											$("#barcodeDialog_iframe").attr('src', "");
											$(this).dialog("close");
											$(this).remove();
										}
										
									});
					$("#barcodeDialog_iframe").attr('src', page);
					$dialog.dialog('open');
					//$dialog.find('script').remove();


				//},
				//error : function(errorMsg, textstatus, errorthrown) {
				//	alert('saveEntryForBarcodePrinting ' + errorMsg
					//		+ " textstatus::" + textstatus + " errorthrown::"
				//			+ errorthrown);

				//}
			//});
}




// the following function is the original function which was called before the barcode slip dialog is added as an enhancment feature.
//dated Oct 2018

function saveEntryForBarcodePrinting() {
	
	/*var action = "/HISRegistration/registration/transactions/SAVEFROMOUTSIDEBarcodeGeneration.action";
	//alert(action);
	$.ajax({
				url : action,
				type : "POST",
				async : true,
				dataType : "xml",
				success : function(action) {*/
						//alert("success!!\n"+action);
						//do nothing
				var url="/HISRegistration/registration/transactions/PRINTBarcodeGeneration.action";
			    
				var child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height=180,width=500,left=10,top=10');  
				//directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=400,height=350
				  	child.moveTo(250,250);
				 	child.focus(); 
					if(!child.opener)
				   		child.opener = self;
				/*},
				error : function(errorMsg, textstatus, errorthrown) {
					alert('saveEntryForBarcodePrinting ' + errorMsg
							+ " textstatus::" + textstatus
							+ " errorthrown::" + errorthrown);

				}
			});*/
}

function saveEntryForBarcodePrinting_PaidProcess() { //this process is tailored for duplicate barcode generation paid process on Jan 11 '19
	
	var action = "/HISRegistration/registration/transactions/SAVEFROMOUTSIDEBarcodeGeneration.action";
	//alert(action);
	$.ajax({
				url : action,
				type : "POST",
				async : true,
				dataType : "xml",
				success : function(action) {
						//alert("success!!\n"+action);
						//do nothing
				var url="/HISRegistration/registration/transactions/PRINTBarcodeGeneration.action";
			    
				var child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height=180,width=500,left=10,top=10');  
				//directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=400,height=350
				  	child.moveTo(250,250);
				 	child.focus(); 
					if(!child.opener)
				   		child.opener = self;
				},
				error : function(errorMsg, textstatus, errorthrown) {
					alert('saveEntryForBarcodePrinting ' + errorMsg
							+ " textstatus::" + textstatus
							+ " errorthrown::" + errorthrown);

				}
			});
}