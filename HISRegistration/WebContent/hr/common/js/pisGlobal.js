/* Module Name: Global JS
*  Name of Process: Global JS 
*  Name of Developer: Sh. Ashwini Mishra
*  Date of Creation: 04-07-2014
*  Name of Last Modifier: Sh. Ashwini Mishra
*  Date of Modification: 28-08-2015
*/


/* Global Variables */
var organizationLogoURL = "/HISRegistration/hisglobal/images/nims-tr-logo.gif";
var gAjaxErrorMode = 2;
//var gAjaxErrorURL = "/HISRegistration/pis/common/transactions/errorPageEmployeeErrorPage.action";

/* Global Variables */


//======== Added by Ashwini Mishra on 24-07-2015======================//

//add new options with default values
$.ui.dialog.prototype.options.clickOut = true;
$.ui.dialog.prototype.options.responsive = true;
$.ui.dialog.prototype.options.scaleH = 0.95;
$.ui.dialog.prototype.options.scaleW = 0.95;
$.ui.dialog.prototype.options.showTitleBar = true;
$.ui.dialog.prototype.options.showCloseButton = true;


// extend _init
var _init = $.ui.dialog.prototype._init;
$.ui.dialog.prototype._init = function () {
    var self = this;

    // apply original arguments
    _init.apply(this, arguments);

    //patch
    $.ui.dialog.overlay.events = $.map('focus,keydown,keypress'.split(','), function (event) {
        return event + '.dialog-overlay';
    }).join(' ');

};
// end _init


// extend open function
var _open = $.ui.dialog.prototype.open;
$.ui.dialog.prototype.open = function () {
    var self = this;

    // apply original arguments
    _open.apply(this, arguments);

    // get dialog original size on open
    var oHeight = self.element.parent().outerHeight(),
        oWidth = self.element.parent().outerWidth(),
        isTouch = $("html").hasClass("touch");

    // responsive width & height
    var resize = function () {

        //check if responsive
        // dependent on modernizr for device detection / html.touch
        if (self.options.responsive === true || (self.options.responsive === "touch" && isTouch)) {
            var elem = self.element,
                wHeight = $(window).height(),
                wWidth = $(window).width(),
                dHeight = elem.parent().outerHeight(),
                dWidth = elem.parent().outerWidth(),
                setHeight = Math.min(wHeight * self.options.scaleH, oHeight),
                setWidth = Math.min(wWidth * self.options.scaleW, oWidth);

            if ((oHeight + 100) > wHeight || elem.hasClass("resizedH")) {
                elem.dialog("option", "height", setHeight).parent().css("max-height", setHeight);
                elem.addClass("resizedH");
            }
            if ((oWidth + 100) > wWidth || elem.hasClass("resizedW")) {
                elem.dialog("option", "width", setWidth).parent().css("max-width", setWidth);
                elem.addClass("resizedW");
            }

            // only recenter & add overflow if dialog has been resized
            if (elem.hasClass("resizedH") || elem.hasClass("resizedW")) {
                elem.dialog("option", "position", "center");
                elem.css("overflow", "auto");
            }
        }

        // add webkit scrolling to all dialogs for touch devices
        if (isTouch) {
            elem.css("-webkit-overflow-scrolling", "touch");
        }
    };

    // call resize()
    resize();

    // resize on window resize
    $(window).on("resize", function () {
        resize();
    });

    // resize on orientation change
    window.addEventListener("orientationchange", function () {
        resize();
    });

    // hide titlebar
    if (!self.options.showTitleBar) {
        self.uiDialogTitlebar.css({
            "height": 0,
                "padding": 0,
                "background": "none",
                "border": 0
        });
        self.uiDialogTitlebar.find(".ui-dialog-title").css("display", "none");
    }

    //hide close button
    if (!self.options.showCloseButton) {
        self.uiDialogTitlebar.find(".ui-dialog-titlebar-close").css("display", "none");
    }

    // close on clickOut
    if (self.options.clickOut && !self.options.modal) {
        // use transparent div - simplest approach (rework)
        $('<div id="dialog-overlay"></div>').insertBefore(self.element.parent());
        $('#dialog-overlay').css({
            "position": "fixed",
                "top": 0,
                "right": 0,
                "bottom": 0,
                "left": 0,
                "background-color": "transparent"
        });
        $('#dialog-overlay').click(function (e) {
            e.preventDefault();
            e.stopPropagation();
            self.close();
        });
        // else close on modal click
    } else if (self.options.clickOut && self.options.modal) {
        $('.ui-widget-overlay').click(function (e) {
            self.close();
        });
    }

    // add dialogClass to overlay
    if (self.options.dialogClass) {
        $('.ui-widget-overlay').addClass(self.options.dialogClass);
    }
};
//end open


// extend close function
var _close = $.ui.dialog.prototype.close;
$.ui.dialog.prototype.close = function () {
    var self = this;
    // apply original arguments
    _close.apply(this, arguments);

    // remove dialogClass to overlay
    if (self.options.dialogClass) {
        $('.ui-widget-overlay').removeClass(self.options.dialogClass);
    }
    //remove clickOut overlay
    if ($("#dialog-overlay").length) {
        $("#dialog-overlay").remove();
    }
};
//end close

//=============================

//Added by Ashwini Mishra on 15-02-2016
/* This function is used to increase the z-index of dialog box by 1 from maximum z-index */
$.widget("ui.dialog", $.ui.dialog,
{
    open: function ()
    {
        var $dialog = $(this.element[0]);
        var maxZ = 0;
        $('*').each(function ()
        {
            var thisZ = $(this).css('zIndex');
            thisZ = (thisZ === 'auto' ? (Number(maxZ) + 1) : thisZ);
            if (thisZ > maxZ) maxZ = thisZ;
        });

        //$(".ui-widget-overlay").css("zIndex", (maxZ + 1));
		$dialog.parent().css("zIndex", (maxZ + 2));
        return this._super();
    }
});


// Added by Ashwini Mishra on 24-04-2014
function populateForm(frm, data) {   
		//alert("inside populateForm");
		$.each(data, function(key, value){  
		    var $ctrl = $('[name=\"'+key+'\"]', frm); 
		    if($ctrl.is('select')){
		        $("option",$ctrl).each(function(){
		            if (this.value==value) { this.selected=true; }
		        });
		    }
		    else {
		        switch($ctrl.attr("type"))  
		        {  
		            case "text" :   case "hidden":  case "textarea":  
		                $ctrl.val(value);   
		                break;   
		            case "radio" : case "checkbox":   
		                $ctrl.each(function(){
		                   if($(this).attr('value') == value) {  $(this).prop("checked",value); } else { $(this).prop("checked",false); } });   
		                break;
		        } 
		    } 
		});  
	}


//Added by Ashwini Mishra on 25-07-2014
function lb_selectOrUnselectAll(listID, isSelect) 
{	
        var listbox = document.getElementById(listID);
        for(var count=0; count < listbox.options.length; count++) 
        {
    
            listbox.options[count].selected = isSelect;
        }
}

//Added by Ashwini Mishra on 25-07-2014
function moveRightSingle(firstMenuObj,secondMenuObj)
{
	var totalElement  = firstMenuObj.length;
	var val1 = "";
	var val2 = "";
	var t1 = 0;
	for(var i=0;i<totalElement;i++)
	{
		if(firstMenuObj.options[i].selected)
		{
			val1 = firstMenuObj.options[i].value;
			val2 = firstMenuObj.options[i].text;			
			t1 = secondMenuObj.length;							
			secondMenuObj.length = (t1+1);				
			secondMenuObj.options[t1].value = val1;
			secondMenuObj.options[t1].text  = val2;													
		}
	}
	deleteThis(secondMenuObj,firstMenuObj);
}

//Added by Ashwini Mishra on 25-07-2014
function moveRightAll(firstMenuObj,secondMenuObj)
{
	var totalElement  = firstMenuObj.length;
	var val1 = "";
	var val2 = "";
	var t1 = 0;
	for(var i=0;i<totalElement;i++)
	{
		val1 = firstMenuObj.options[i].value;
		val2 = firstMenuObj.options[i].text;			
		t1 = secondMenuObj.length;							
		secondMenuObj.length = (t1+1);				
		secondMenuObj.options[t1].value = val1;
		secondMenuObj.options[t1].text  = val2;													
	}
	deleteThis(secondMenuObj,firstMenuObj);
}

//Added by Ashwini Mishra on 25-07-2014
function deleteThis(srcMenuObj,targetMenuObj)
{	
	var t =0;
	var val1 = "";
	var val2 = "";	
	var len  = targetMenuObj.length;
	var len2 = srcMenuObj.length;
	var a1 = new Array(len);
	var a2 = new Array(len);
	var a3 = new Array(len2);
	for(var i=0;i<len;i++)
	{		
		a1[i]= targetMenuObj.options[i].value;
		a2[i]= targetMenuObj.options[i].text;	
	}
	for( i=0;i<len2;i++)
	{		
		a3[i]= srcMenuObj.options[i].value;
	}
	
	targetMenuObj.length = 0;
	var counter = 0;
	var k = 0;
	var flag = 0;
	for(i=0;i<len;i++)
	{		
		flag = 0;
		for(k=0;k<len2;k++)
		{		
			if(a1[i]==a3[k])
			{	
				flag = 1;					
			}					
		}		
		if(flag==0)
		{
			targetMenuObj.length = (counter+1);
			targetMenuObj.options[counter].value = a1[i];
			targetMenuObj.options[counter].text  = a2[i]; 
			counter++;			
		}		
	}
}

//Added by Ashwini Mishra on 25-07-2014
function moveLeftSingle(firstMenuObj,secondMenuObj)
{
	var totalElement  = secondMenuObj.length;
	var val1 = "";
	var val2 = "";
	var t1 = 0;
	for(var i=0;i<totalElement;i++)
	{
		if(secondMenuObj.options[i].selected)
		{
			val1 = secondMenuObj.options[i].value;
			val2 = secondMenuObj.options[i].text;
			t1 = firstMenuObj.length;							
			firstMenuObj.length = (t1+1);				
			firstMenuObj.options[t1].value = val1;
			firstMenuObj.options[t1].text  = val2;													
		}
	}
	deleteThis(firstMenuObj,secondMenuObj);
}

//Added by Ashwini Mishra on 25-07-2014
function moveLeftAll(firstMenuObj,secondMenuObj)
{
	var totalElement  = secondMenuObj.length;
	var val1 = "";
	var val2 = "";
	var t1 = 0;
	for(var i=0;i<totalElement;i++)
	{
		val1 = secondMenuObj.options[i].value;
		val2 = secondMenuObj.options[i].text;
		t1 = firstMenuObj.length;							
		firstMenuObj.length = (t1+1);				
		firstMenuObj.options[t1].value = val1;
		firstMenuObj.options[t1].text  = val2;													
	}
	deleteThis(firstMenuObj,secondMenuObj);
}

//Added by Ashwini Mishra on 25-09-2014
/*

function callMenu(url)
{
	var targetURL = url;
	var elemFrame = parent.document.getElementById("frmMain");
	elemFrame.src=targetURL;
	elemFrame.refresh();
}

function callMenu(url)
{
	var targetURL = url;
	var elemFrame;
	if(parent.document.getElementById("frmMain")!=null) elemFrame = parent.document.getElementById("frmMain"); 
	else if(parent.parent.document.getElementById("frmMain")) elemFrame = parent.parent.document.getElementById("frmMain"); 
	else elemFrame = parent.document.getElementById("frmMain"); 	
	elemFrame.src=targetURL;
	elemFrame.refresh();
}
*/

function callMenu(mode, url)
{
	var targetURL = url;     
    var elemFrame;
 	if(parent.document.getElementById("frmMain")!=null) elemFrame = parent.document.getElementById("frmMain"); 
 	else if(parent.parent.document.getElementById("frmMain")) elemFrame = parent.parent.document.getElementById("frmMain"); 
 	else elemFrame = parent.document.getElementById("frmMain"); 	
    if(elemFrame!=null){
         elemFrame.src=targetURL;
         elemFrame.refresh();
    }
    else{
    	if(mode==1)
    	{
    		if(typeof $('#tabframe')!='undefined'){
                var tab = window.parent.$('#tabframe').tabs('getSelected');
                var index = window.parent.$('#tabframe').tabs('getTabIndex',tab);
                window.parent.$('#tabframe').tabs('select',index-1);  
                window.parent.$('#tabframe').tabs('close',index);      

            }    	
    	}
    	else if(mode==2)
    	{
    		if(typeof $('#tabDivId')!='undefined'){
                var tab = window.parent.$('#tabDivId').tabs('getSelected');
                var index = window.parent.$('#tabDivId').tabs('getTabIndex',tab);
                window.parent.$('#tabDivId').tabs('select',index-1);  
                window.parent.$('#tabDivId').tabs('close',index);       

            }
    	}
    	else
    	{
    		//alert("No Mode Defined");
    		showAlert ("1", "No Mode Defined!");
    	}
    }
}



//Added by Ashwini Mishra on 01-10-2014
function funcTab1(defaultTab)
{
	$("#content").find("[id^='tab']").hide(); // Hide all content
	$("#tabs1 li:first").attr("id","current"); // Activate the first tab
	$("#content "+defaultTab).fadeIn(); // Show first tab's content
	
	 $('#tabs1 a').click(function(e) {
	        e.preventDefault();
	        if ($(this).closest("li").attr("id") == "current"){ //detection for current tab
	         return;       
     }
     else{             
          $("#content").find("[id^='tab']").hide(); // Hide all content
          $("#tabs1 li").attr("id",""); //Reset id's
          $(this).parent().attr("id","current"); // Activate this Tab
          $('#' + $(this).attr('name')).fadeIn(); // Show content for the current tab
        }
    });	

}

//Added by Ashwini Mishra on 18-12-2015
function funcTabGen1(tabsContainer, defaultTab)
{
	$("#"+tabsContainer+"_content").find("[id^='tab']").hide(); // Hide all content
	$("#"+tabsContainer+" li:first").attr("id","current"); // Activate the first tab
	$("#"+tabsContainer+"_content "+defaultTab).fadeIn(); // Show first tab's content
	
	 $("#"+tabsContainer+" a").click(function(e) {
	        e.preventDefault();
	        if ($(this).closest("li").attr("id") == "current"){ //detection for current tab
	         return;       
     }
     else{             
          $("#"+tabsContainer+"_content").find("[id^='tab']").hide(); // Hide all content
          $("#"+tabsContainer+" li").attr("id",""); //Reset id's
          $(this).parent().attr("id","current"); // Activate this Tab
          $('#' + $(this).attr('name')).fadeIn(); // Show content for the current tab
        }
    });	

}

//Added by Ashwini Mishra on 01-10-2014
function funcTab2(defaultTab)
{
	$("#content").find("[id^='tab']").hide(); // Hide all content
	$("#tabs2 li:first").attr("id","current"); // Activate the first tab
	$("#content "+defaultTab).fadeIn(); // Show first tab's content
	
	 $('#tabs1 a').click(function(e) {
	        e.preventDefault();
	        if ($(this).closest("li").attr("id") == "current"){ //detection for current tab
	         return;       
     }
     else{             
          $("#content").find("[id^='tab']").hide(); // Hide all content
          $("#tabs2 li").attr("id",""); //Reset id's
          $(this).parent().attr("id","current"); // Activate this Tab
          $('#' + $(this).attr('name')).fadeIn(); // Show content for the current tab
        }
    });	
}

//Added by Ashwini Mishra on 01-10-2014
function funcTab3(defaultTab)
{
	
	
}

//Added by Ashwini Mishra on 01-10-2014
function funcTab4(defaultTab)
{
	$("#content").find("[id^='tab']").hide(); // Hide all content
    $("#tabs4 li:first").attr("id","current"); // Activate the first tab
    $("#content "+defaultTab).fadeIn(); // Show first tab's content
    
    $('#tabs4 a').click(function(e) {
        e.preventDefault();
        if ($(this).closest("li").attr("id") == "current"){ //detection for current tab
         return;       
        }
        else{             
          $("#content").find("[id^='tab']").hide(); // Hide all content
          $("#tabs4 li").attr("id",""); //Reset id's
          $(this).parent().attr("id","current"); // Activate this Tab
          $('#' + $(this).attr('name')).fadeIn(); // Show content for the current tab
        }
    });	
}


function printReport(frmDiv, toDiv)
{      
	if(frmDiv==undefined) frmDiv='divPrintId1';
	if(toDiv==undefined); toDiv='divPrintId2';
	$('#'+toDiv).html($('#'+frmDiv).html());
	window.print();
	$('#'+toDiv).html("");   
}	

//eval(function(p,a,c,k,e,d){e=function(c){return c;};if(!''.replace(/^/,String)){while(c--){d[c]=k[c]||c;}k=[function(e){return d[e];}];e=function(){return'\\w+';};c=1;};while(c--){if(k[c]){p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);}}return p;}
//('3 2(){$(\'#1\').0($(\'#6\').0());4.5();$(\'#1\').0("")}',7,7,'html|divPrintId2|printReport|function|window|print|divPrintId1'.split('|'),0,{}));

//var _0x9113=["\x68\x74\x6D\x6C","\x23","\x70\x72\x69\x6E\x74",""];function printReport(_0x17d6x2,_0x17d6x3){$(_0x9113[1]+_0x17d6x3)[_0x9113[0]]($(_0x9113[1]+_0x17d6x2)[_0x9113[0]]());window[_0x9113[2]]();$(_0x9113[1]+_0x17d6x3)[_0x9113[0]](_0x9113[3]);}

function filterReqText(a,c,d){var b=a;"1"==c?b=a:"2"==c?b=a.parent():"3"==c?b=a.parent().parent():"4"==c?b=a.parent().parent().parent():"5"==c?b=a.parent().parent().parent().parent():"6"==c&&(b=a.parent().parent().parent().parent().parent());-1!=a.html().toUpperCase().indexOf($('[name="'+d+'"]')[0].value.toUpperCase())?b.show():b.hide();};

//Added by Ashwini Mishra on 20-02-2015
function covertToUpperCase(Obj) { $(Obj).val($(Obj).val().toUpperCase());}

//Added by Ashwini Mishra on 20-02-2015
/*
$(document).bind('contextmenu', function (e) {e.preventDefault(); alert('Right Click is not allowed.');});
*/

//Added by Ashwini Mishra on 11-08-2015
function confirmationBox(mode, divId, title, msg) { 
	var retVal=false; 	
	if(confirm(msg)) retVal=true;
	else retVal=false;
	return retVal;
		
}


function showAlert (mode, msg, title, callBackOK, argsOK) {
	if(title==null || title.length==0)title="Alert";
	$( '<div style="height: 100% !important"></div>' ).dialog({
		modal: true,
		title: title,
		show: {
			 effect: "puff",
			 duration: 200
		},
		open: function() {			
			$(this).html(msg);			
		},
		buttons: {
			Ok: function() {
				$( this ).dialog( "close" );
				if (typeof callBackOK == "function")
					callBackOK();		
			}
		}
	});
	
	
}

/*
function showAlert (mode, msg, title, callBackOK, argsOK) {
	$( '<div style="height: 100% !important"></div>' ).dialog({
		modal: true,
		title: "Message",
		show: {
			 effect: "puff",
			 duration: 200
		},
		open: function() {
			$(this).html(msg);
		},
		buttons: {
			Ok: function() {
				$( this ).dialog( "close" );
				if (id != null) {
					if (id instanceof HTMLElement)
						id.focus();
					else
						document.getElementById(id).focus();
				}
			}
		}
	});
}
*/

function showConfirmDialog(mode, msg, title, callBackOK, argsOK, callBackCancel, argsCancel) {
	if(title==null || title.length==0)title="Confirmation";
	$( '<div style="height: 100% !important"></div>' ).dialog({
		modal: true,
		title: title,
		show: {
			 effect: "puff",
			 duration: 200
		},
		open: function() {
			$(this).html(msg);
		},
		buttons: {
			Ok: function() {
				$( this ).dialog( "close" );
				if (typeof callBackOK == "function")
					callBackOK();				
			},
			Cancel: function() {
				$( this ).dialog( "close" );
				if (typeof callBackCancel == "function")
					callBackCancel();
			}
		}
	});
	/*
	var didConfirm = confirm(msg);
	if (didConfirm == true)
		{callBackOK();}
	else
		callBackCancel();
	*/
}

function goToDefaultPage(mode) {
	//callMenu('/AHIMSG5/hislogin/transactions/jsp/st_default_desk_page.jsp');
	//parent.callMenu('/AHIMSG5/hislogin/transactions/jsp/st_default_desk_page.jsp');
	if(mode==1) {callMenu('1', '/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');}
	else if(mode==2) {callMenu('2', '/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');}
	else {callMenu('1', '/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');}
}


function toggleDivisionWithButton(firstDiv, secondDiv, divName)
{   			
	  $("#"+divName).slideToggle("slow"); 
	  document.getElementById(firstDiv).style.display ="none";
	  document.getElementById(secondDiv).style.display = "inline";
}


//Added by Ashwini Mishra on 17-06-2015
function applyEmpNoCheck(fldName, len, type, otherVal)
{
	if(otherVal!=''){
	if(type=="1"){$('[name="'+fldName+'"]').validatebox({required: true,validType: ['numeric','minLength['+len+']','maxLength['+len+']', otherVal]});}
	else if(type=="2"){$('[name="'+fldName+'"]').validatebox({required: true,validType: ['alphaNumeric','minLength['+len+']','maxLength['+len+']']});}
	else if(type=="3"){$('[name="'+fldName+'"]').validatebox({required: true,validType: ['alpha','minLength['+len+']','maxLength['+len+']']});}
	else{$('[name="'+fldName+'"]').validatebox({required: true,validType: ['minLength['+len+']','maxLength['+len+']']});}
	}
	else
	{
		if(type=="1"){$('[name="'+fldName+'"]').validatebox({required: true,validType: ['numeric','minLength['+len+']','maxLength['+len+']']});}
		else if(type=="2"){$('[name="'+fldName+'"]').validatebox({required: true,validType: ['alphaNumeric','minLength['+len+']','maxLength['+len+']']});}
		else if(type=="3"){$('[name="'+fldName+'"]').validatebox({required: true,validType: ['alpha','minLength['+len+']','maxLength['+len+']']});}
		else{$('[name="'+fldName+'"]').validatebox({required: true,validType: ['minLength['+len+']','maxLength['+len+']']});}
		
	}
	/*
	$('[name="'+fldName+'"]').bind("cut copy paste",function(e) {
	    e.preventDefault();
	});
	*/
	$('#'+fldName).bind("cut copy paste",function(e) {
	    e.preventDefault();
	});
}


function getRptTableOpen(mode)
{
	var rptTableOpen =  " <center> "+
	" <div id='reportForm' style='display:table;width: 95%;background-color: white;font-size: 10px; text-align: center;' align='center'> ";

	return rptTableOpen;
}


function getRptTableClose(mode)
{
	var rptTableClose =  " </center> "+
	" </div> ";

	return rptTableClose;
}


function getRptPrintOption(mode)
{
	//divPrintImageId
	var rptPrintRow =  " <div class='div-table-row' id='rptPrintImageDivId'> "+
	" <div class='div-table-col label' style='width: 100%; right:12px;'> "+
		"<div id='divReportOptionsId' style='display:; position:absolute; right:120px; top:8px;'>"+
		"Header <input name='intFlagReportHeaderRequired' tabindex='1' id='intFlagReportHeaderRequired' checked='true' type='checkbox' onchange='isRptHeaderRequired(\"divPrintId1\");'>"+
		" Footer <input name='intFlagReportFooterRequired' tabindex='1' id='intFlagReportFooterRequired' checked='true' type='checkbox' onchange='isRptFooterRequired(\"divPrintId1\");'>"+
		" Logo <input name='intFlagLogoRequired' tabindex='1' id='intFlagLogoRequired' type='checkbox' checked='true' onchange='isRptLogoRequired(\"divPrintId1\");'>"+
		"</div>"+
		"<img id='toggleRptprintOptionsImgId' src='/HIS/hisglobal/images/nonclinical/nc_report_options.png' width='25' height='25' onclick='toggleRptOptions(\"divPrintId1\");' style='display:; vertical-align: middle; cursor:pointer;' title='Options' />" +
		"&nbsp;&nbsp;&nbsp;"+
		" <img id='printRptImgId' src='/HIS/hisglobal/images/nonclinical/nc_printer.png' width='25' height='25' onclick='printReport();' style='display:; vertical-align: middle;cursor:pointer;' title='Print' /> "+
	" </div> "+
	" </div> ";

	return rptPrintRow;
}


function getRptHeader(mode)
{
	var rptHeaderRow =  " <div class='div-table-row' align='center' id='rptHeaderDivId' style='width: 100%;'> "+	
	" <div class='div-table' style='text-align: center;' align='center' > " +
		"<div class='div-table-row' >" +
		"<div class='div-table-col label' style='width: 25%; text-align:rigt; vertical-align: middle;'>" +
		"<div id='organizationLogoImageDivId' style='margin-top:10px;'>"+
			"<img src='"+organizationLogoURL+"' align='middle' style='vertical-align: middle; cursor:pointer;' title='Logo' width='90' height='90' />" +
		"</div>"+
		"</div>"+
		"<div class='div-table-col label' style='width: 50%; vertical-align: middle;'>" +
			"<div class='div-table'>" +	
			" <div id='' class='div-table-row' align='center'> &nbsp; </div> "+
			" <div id='rptHospitalNameDivId' class='div-table-row' align='center'> "+$('#hospitalName').val()+" </div> "+
			" <div id='rptHospitalAdd1DivId' class='div-table-row' align='center'> "+$('#address1').val()+" </div> "+
			" <div id='rptHospitalAdd2DivId' class='div-table-row' align='center'> "+$('#address2').val()+" </div> "+
			" <div id='rptHospitalAdd3DivId' class='div-table-row' align='center'> "+$('#district').val()+", "+$('#state').val() +" </div> "+
			"</div>"+
		"</div>"+
		"<div class='div-table-col label' style='width: 25%;'>" +
		"</div>"+
		"</div>"+
	" </div> "+
	" </div> ";
	return rptHeaderRow;
}


function getRptDateAndTime(mode, _date, _time)
{
	var rptDateAndTimeRow =  " <div id='rptDateTimeDivId' class='div-table-row' align='right'> "+
	$('#strDateTimeLabel').val()+"&nbsp;:&nbsp;<div id='divRptDateId' style='font-style: normal;font-weight:normal;display: inline;'>"+_date+"</div>/<div id='divRptTimeId' style='font-style: normal;font-weight:normal;display: inline;'>"+_time+"</div>&nbsp;&nbsp;&nbsp;&nbsp; "+
	" </div> ";
	return rptDateAndTimeRow;
}

function getRptHeading(mode, rptHeading)
{
	var rptFinalHeading =  "";
	
	if(mode==1) rptFinalHeading =  rptHeading;
	else if(mode==2){
	rptFinalHeading = " <div id='rptHeadingDivId' class='div-table-row' align='center'> "+
	" <u>"+rptHeading+"</u> "+
	" </div> ";
	}
	else
	{
		//alert("Wrong Mode");
		showAlert ("1", "Wrong Mode!");
		return false;
	}
	
	return rptFinalHeading;
}

function getRptFooter(mode)
{
	var rptFooterRow =  " <div id='rptFooterDivId' class='div-table-row' align='center' style='width: 100%;'> "+
	" <div class='div-table' style='text-align: center;' align='center' > " +
	" <div id='rptEndOfReportDivId' class='div-table-row' align='center'> "+
	$('#strRptFooter').val()+
	" </div> "+
	" <div id='rptComputerGeneratedRptDivId' class='div-table-row' align='center'> "+
	$('#strRptFooterCGF').val()+
	" </div> "+
	" </div> "+
	" </div> ";

	return rptFooterRow;
}

function openReport(mode, divName, rptTitle, rptContent)
{
	var windowWidth=window.innerWidth-25;
	var windowHeight=window.innerHeight-25;
	
	var blankHeight, blankWidth;
	if(!windowWidth)
		windowWidth =( $(window).width() * 0.8);
	if(!windowHeight)
		windowHeight = ($(window).height() * 0.8);
	
	//blankWidth = ($(window).width()-windowWidth);
	//blankHeight =($(window).height() - windowHeight)  ;
	blankWidth = (window.innerWidth-windowWidth);
	blankHeight =(window.innerHeight - windowHeight)  ;
	var windowLeft = Math.round((blankWidth / 2)-18)+ "px";
	var windowTop = Math.round((blankHeight / 2)-18 ) + "px";
		
	//alert("Record Details for = "+recordId);
	$("#"+divName).html("");
	$("#"+divName).html(rptContent);	
		
	//$("#"+divName+" #divRptId").children().find('.label, .control').css('font-size','12px');
	$("#"+divName+" #reportForm").children().find('.label, .control').css('font-size','12px');
	$("#"+divName+" #rptHospitalNameDivId").css('font-size','14px');
	$("#"+divName+" #rptHospitalAdd1DivId").css('font-size','12px');
	$("#"+divName+" #rptHospitalAdd2DivId").css('font-size','12px');
	$("#"+divName+" #rptHospitalAdd3DivId").css('font-size','12px');	
	$("#"+divName+" #rptHeadingDivId").css('font-size','12px');
	$("#"+divName+" #reportForm").children().css('color','black');
	$("#"+divName+" #reportForm").children().children().css('color','black');
	$("#"+divName+" #reportForm").children().children().children().css('color','black');
	$("#"+divName+" #reportForm").children().children().children().children().css('color','black');
	$("#"+divName+" #reportForm").children().children().children().children().children().css('color','black');
	$("#"+divName+" #rptHeadingDivId").show();
	
	//$("#"+divName).modal();
	if(divName!="divPrintId1") { $("#"+divName+" #printRptImgId").attr("onclick","printReport('"+divName+"','divPrintId2')");  }
	if(divName!="divPrintId1") { $("#"+divName+" #intFlagReportHeaderRequired").attr("onchange","isRptHeaderRequired('"+divName+"')");  }
	if(divName!="divPrintId1") { $("#"+divName+" #intFlagReportFooterRequired").attr("onchange","isRptFooterRequired('"+divName+"')");  }
	if(divName!="divPrintId1") { $("#"+divName+" #intFlagLogoRequired").attr("onchange","isRptLogoRequired('"+divName+"')");  }
	if(divName!="divPrintId1") { $("#"+divName+" #toggleRptprintOptionsImgId").attr("onclick","toggleRptOptions('"+divName+"')");  }
	
	$("#"+divName).dialog({
	    height: windowHeight,
	    width: windowWidth,
	    id: "dialogBox",
	    title: rptTitle,
	    responsive: true,		   
	    modal: true,
	    open: function (event, ui) {
		   //$('.ui-dialog').css('z-index',1003);
		   //$('.ui-widget-overlay').css('z-index',1002);
		},
	    close: function(event, ui)
        {
	    	$("#"+divName).html("");
        }
	});
	
	windowHeight = windowHeight + "px";
	windowWidth = windowWidth + "px";
	
	
	//$('#simplemodal-container').width(windowWidth).height(windowHeight);
	$('#simplemodal-container').width("90%").height("85%");
	//$('#simplemodal-container').css({'left':windowLeft,'top':windowTop});
	$('#simplemodal-container').css({'left':"3%",'top':"4%"});
	$("#simplemodal-container").resizable();
    //$("#simplemodal-container").draggable();

}

/* This function is called when report header checkbox is changed
 * Parameter - divName(Report Opening Division Name)
 * */
function isRptHeaderRequired(divName)
{	
	if($("#"+divName+" #intFlagReportHeaderRequired").prop("checked"))
	{
		$("#"+divName+" #intFlagLogoRequired").prop("disabled", false);
		$("#"+divName+" #rptHeaderDivId").show();
	}
	else
	{
		$("#"+divName+" #intFlagLogoRequired").prop('checked', false);
		$("#"+divName+" #intFlagLogoRequired").prop("disabled", true);
		$("#"+divName+" #rptHeaderDivId").hide();
	}
	
	isRptLogoRequired(divName);
}

/* This function is called when report footer checkbox is changed
 * Parameter - divName(Report Opening Division Name)
 * */
function isRptFooterRequired(divName)
{
	if($("#"+divName+" #intFlagReportFooterRequired").prop("checked"))
	{
		$("#"+divName+" #rptComputerGeneratedRptDivId").show();
	}
	else
	{
		$("#"+divName+" #rptComputerGeneratedRptDivId").hide();
	}
}

/* This function is called when report logo checkbox is changed
 * Parameter - divName(Report Opening Division Name)
 * */
function isRptLogoRequired(divName)
{
	if($("#"+divName+" #intFlagLogoRequired").is(":checked"))
	{
		$("#"+divName+" #organizationLogoImageDivId").show();
	}
	else
	{
		$("#"+divName+" #organizationLogoImageDivId").hide();
	}
}

/* This function is called when report options button is pressed in dialog box report to toggle header footer logo checkboxes.
 * Parameter - divName(Report Opening Division Name)
 * */
function toggleRptOptions(divName)
{
	var options= {direction:'right'};
	$("#"+divName+" #divReportOptionsId").toggle('slide', options, '250');
}


//Added by Ashwini Mishra on 19-03-2015 for cloumn chooser (Make Size of Both Portion Equal)
$.extend(true, $.jgrid.col, {
    width: 500,
    msel_opts: {dividerLocation: 0.5}
});

//Added by Ashwini Mishra on 10-10-2014
$(document).bind('ajaxStart', function(){ $("#loading-image").show(); 
}).bind('ajaxComplete', function(){ $("#loading-image").hide();
});


//Added by Manoj Kumar Singh on 11-02-2016
function globalMsgFuctionForEmpNoGo(rptMsgFlag)
{
	
	if(rptMsgFlag=='2') // not a valid Employee but exist in Emp Reg.
	{		
		var msg="No Record found.";
		$("#divErrorMsgId").html("&nbsp;<img src='/HIS/hisglobal/images/nonclinical/nc_warning.png' width='25' height='25' style='vertical-align:sub;' title='Warning' />&nbsp;"+msg);
		return false;
	}
	else if(rptMsgFlag=='3')
	{
		var msg="This Emp. is not valid";
		$("#divErrorMsgId").html("&nbsp;<img src='/HIS/hisglobal/images/nonclinical/nc_warning.png' width='25' height='25' style='vertical-align:sub;' title='Warning' />&nbsp;"+msg);
		return false;
	}	
	else if(rptMsgFlag=='4')
	{
		var msg="This Emp. No. does not exist.";
		$("#divErrorMsgId").html("&nbsp;<img src='/HIS/hisglobal/images/nonclinical/nc_warning.png' width='25' height='25' style='vertical-align:sub;' title='Warning' />&nbsp;"+msg);
		return false;
	}

}

$.datepicker.setDefaults( 
    {	showOn: 'both',
    	defaultDate: new Date(),
    	buttonImageOnly: true, 
    	buttonText : "Select Date",
    	buttonImage: "/HIS/hisglobal/images/nonclinical/nc_calendar_icon.gif"
   	});
   	
//Added by Ashwini Mishra on 25-04-2016
function showAjaxError(_processName, _funcName, _errorMsg, _textstatus, _errorthrown)
{
	alert(_funcName +": "+_errorMsg+" textstatus::"+_textstatus+" errorthrown::"+_errorthrown);	
}

