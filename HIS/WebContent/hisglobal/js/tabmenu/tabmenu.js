$(function() {
		$(document).ready(function() {
			
			var ht = Math.round($(window).height() - $(".header").outerHeight()-$(".marque").outerHeight()-$(".footer").outerHeight()) + "px";
			var wt = Math.round($(window).width()) + "px";
			$('.body').height(ht);
			var ht2 = Math.round($(window).height() - $(".header").outerHeight()-$(".marque").outerHeight()-$(".footer").outerHeight()-$("#menuStrip").outerHeight()) + "px";
			$('.bodyContent').height(ht2);
			
			/* var ht = Math.round($(window).height() - $(".header").outerHeight()-$(".footer").outerHeight()-$(".marque").outerHeight()) + "px";
			var wt = Math.round($(window).width()) + "px";
			$('.body').height(ht);
			var ht2 = Math.round($(window).height() - $(".header").outerHeight()-$(".footer").outerHeight()-$(".marque").outerHeight()-$("#menuStrip").outerHeight()) + "px";
			$('.bodyContent').height(ht2); */
						
			$('.bodyContent').css({'margin-top':$("#menuStrip").outerHeight()});
			adjustMenuAlignment();
			/*$('.wrapper').width(wt).height(ht);
			alert('login height: '+ ht/2);
			$('.login').height(ht/2);
			 */
			$("#toggle").click(function() {
				alert('toggle called');
				$("#menuContainer").slideToggle();
			});
			 /* $(".userDetail ul li").children('div').addClass("align_right"); */
			$('#arrow_left').mouseout(adjustMenuAlignment);
			$('#arrow_right').mouseout(adjustMenuAlignment);
			$('#menu a').click(function() {
				if($(this).attr('class')=='plusParentNode'){
					$(this).removeClass('plusParentNode');
					$(this).addClass('minusParentNode');
				}else if($(this).attr('class')=='minusParentNode'){
					$(this).removeClass('minusParentNode');
					$(this).addClass('plusParentNode');
				}
			    $(this).next('ul').slideToggle();
			});
			//checkIfSlideBarRequired();
			//initSlideShow();
			$("#smoothmenu a").click(function(){
				$(this).trigger("mouseout");
			});
			$("#lastSeenMenusIcon").mouseover(function(){
				var lastSeenMenusHTML="";
				var idx;
				
				if(lastVisitedMenusStartIdx==-1){
					
				}
				else if(lastVisitedMenusEndIdx >lastVisitedMenusStartIdx){
					for(idx=lastVisitedMenusEndIdx-1; idx >=lastVisitedMenusStartIdx; idx--){
						lastSeenMenusHTML+="<li ><a  onclick=\"callMenu('"+lastVisitedURLs[idx] +"','"+lastVisitedMenus[idx]+"')\">"+lastVisitedMenus[idx] +"</a> </li>";
					}
				}else{
					for(idx=lastVisitedMenusEndIdx-1; idx >=0; idx--){
						lastSeenMenusHTML+="<li ><a  onclick=\"callMenu('"+lastVisitedURLs[idx] +"','"+lastVisitedMenus[idx]+"')\">"+lastVisitedMenus[idx] +"</a> </li>";
					}
					for(idx=maxLastVisitedMenuCount-1; idx >=lastVisitedMenusStartIdx; idx--){
						lastSeenMenusHTML+="<li ><a  onclick=\"callMenu('"+lastVisitedURLs[idx] +"','"+lastVisitedMenus[idx]+"')\">"+lastVisitedMenus[idx] +"</a> </li>";
					}
					
				}
				$("#lastSeenMenusID").html(lastSeenMenusHTML);
				//alert('last visited menus: '+ lastVisitedMenus+" lastVisitedMenusStartIdx: "+lastVisitedMenusStartIdx+" lastVisitedMenusEndIdx"+lastVisitedMenusEndIdx);
			});

			if($('#alertCount').val()>0)
				window.setInterval("$('#alertCountId').toggle();",999999999);

			//For Each 10 Seconds the Cash in the Desk will be refreshed, Added by Singaravelan on 04-Jun-2015
			window.setInterval(reloadCashDtl, 100000);
			window.setInterval(reloadAlertDtl, 100000);
		
		
							
		});
		
});


ddsmoothmenu.init({
		mainmenuid: "menuStrip", //menu DIV id
		//orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
		//classname: 'ddsmoothmenu', //class added to menu's outer DIV
		//customtheme: ["#1c5a80", "#18374a"],
		//contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
}) 
ddsmoothmenu.init({
		mainmenuid: "smoothmenu", //menu DIV id
		//orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
		//classname: 'ddsmoothmenu', //class added to menu's outer DIV
		//customtheme: ["#1c5a80", "#18374a"],
		//contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
}) 
	
function reloadCashDtl(){

	//var action="/AHIMSG5/hislogin/reloadCashUserDesk.action"
	var action="/AHIMSG5/hislogin/reloadCashUserDesk"
	$.ajax
	(
			{
				url : action, 
				type : "POST",
				async : true,
				dataType : "text",
				success : function(returnHTML) 
				{	
					if($('[name="varIsAutoRefresh"]').val()!=""&&$('[name="varIsAutoRefresh"]').val()!="0")
						reloadAlertDtl();
					document.getElementById("cashCollectedDiv").innerHTML=returnHTML;
					$('#cashCollectedDiv').qtip('destroy');
					$('#cashCollectedDiv').qtip({
				        content:
				        {
				        	text: 'Loading...',
				        	ajax: {
				        		//url: '/AHIMSG5/hislogin/cashCollectionDtlUserDesk.action', 
				        		url: '/AHIMSG5/hislogin/cashCollectionDtlUserDesk', 
				                type: 'POST', 
				                once:false,
				                data:{},
				                success: function(data, status) {					                      
				                    this.set('content.text', data);
				                }
				            }		            
				        },				          
				        style: {  	
				        	classes: 'tipCustomStyle qtip-rounded',	            	
				        },
				        position: {		       
				            my: 'top center', 
				            at: 'bottom center', 
				        }
				    });
				},
				error : function(errorMsg, textstatus, errorthrown) 
				{
						var erTxt="<font size='4 px' color='red'>Cash in Hand : Error</font>"
						document.getElementById("cashCollectedDiv").innerHTML=erTxt;
				}
			}
	);	
}

//To Reload the Alert Dtls in the Desk, Added by Singaravelan on 29-Oct-2015
function reloadAlertDtl(){
	
	//alert("Refresh Alert");
	//var action="/AHIMSG5/hislogin/getAlertDtlUserDesk.action"
	var action="/AHIMSG5/hislogin/getAlertDtlUserDesk"
	$.ajax
	(
			{
				url : action, 
				type : "POST",
				async : true,
				dataType : "text",
				success : function(returnHTML) 
				{	
					var alertCount="0";var mrgMsg="";
					if(returnHTML.indexOf("$")>0){
						alertCount= returnHTML.split("$")[0];
						mrqMsg=returnHTML.split("$")[1];
					}
					document.getElementById("alertCountId").innerHTML=alertCount;
					document.getElementById("footmarquee").innerHTML=mrqMsg;
					
				},
				error : function(errorMsg, textstatus, errorthrown) 
				{						
				}
			}
	);	
}
 
function addTab2(menu,targetURL){
		
		//alert("Inside tab Addition :: Menu :: "+menu+" Url ::"+targetURL);
		if ($('#tabframe').tabs('exists', menu) && checkTabHasURL(targetURL)) {
			$('#tabframe').tabs('select', menu);
			
		} else {
			ajaxStartTab();
			var content = '<iframe id="'+menu+'_iframe" scrolling="auto" frameborder="0" src="'
					+ targetURL + '" style="width:100%;height:95%;" onLoad="ajaxCompleteTab(this);"></iframe>';
			tabCount=$('#tabframe ul li').length;
			$('#tabframe').tabs(
					'add', {
					title : menu,content : content,	closable : true,heightStyle: "content",	id:targetURL				
					/* tools:[{ iconCls:'icon-mini-refresh', handler:function(){tabMenuRefresh(menu,targetURL);	}}] */
				});	
			
			if(tabCount<=MAX_TAB_COUNT){
				//alert("Inside tabCount<=MAX_TAB_COUNT :: tabCount"+tabCount+" MAX_TAB_COUNT "+MAX_TAB_COUNT);		
			}
			else{
				 //alert("Inside tabCount > MAX_TAB_COUNT :: tabCount"+tabCount+" MAX_TAB_COUNT "+MAX_TAB_COUNT+" Exsists ::"+$('#tabframe').tabs('exists', 1));				 
				$('#tabframe').tabs("close", 1);			
			}
		}
}
/**/

function addTab(menu,targetURL){

	//alert("Inside tab Addition :: Menu :: "+menu+" Url ::"+targetURL);
	if ($('#tabframe').tabs('exists', menu) && checkTabHasURL(targetURL)) {
		$('#tabframe').tabs('select', menu);
		if(targetURL.indexOf("/HBIMS/billing/transactions/CashCollectionOnlineTransCNT.cnt?hmode=GO&CRNO") !== -1)
			tabMenuRefresh(menu,targetURL);
	} else {
		ajaxStartTab();
		var content = '<iframe id="'+menu+'_iframe" scrolling="auto" frameborder="0" src="'
				+ targetURL + '" style="width:100%;height:95%;" onLoad="ajaxCompleteTab(this);"></iframe>';
		tabCount=$('#tabframe ul li').length;
		if(tabCount<=MAX_TAB_COUNT){	
			$('#tabframe').tabs(
					'add', {
		 			title : menu,content : content,	closable : true,heightStyle: "content",	id:targetURL				
					/* tools:[{ iconCls:'icon-mini-refresh', handler:function(){tabMenuRefresh(menu,targetURL);	}}] */
				});	
		} 
		else 
		{
			var truFls = window.confirm("Alert! Five Tabs are already open.\nPress 'Ok' to close the first tab.\nPress 'Cancel' to close the last tab.");
			if (truFls)
				{
					$('#tabframe').tabs("close",1);
					$('#tabframe').tabs(
							'add', {
							title : menu,content : content,	closable : true,heightStyle: "content",	id:targetURL				
							/* tools:[{ iconCls:'icon-mini-refresh', handler:function(){tabMenuRefresh(menu,targetURL);	}}] */
						});	
				}
			ajaxCompleteTab();
		}

	}
}

/**/

function tabMenuRefresh(menu,targetURL) {
	document.getElementById(menu+'_iframe').contentDocument.location=targetURL;
}

function tabRefresh(){
	ajaxStart();
	var tab = $('#tabframe').tabs('getSelected');
	var targetURL=tab.panel('options').id;
	var menu=tab.panel('options').title;
	// Function Modified By Raj Kumar for Removing Error Please Wait While Refereshing Report in PDF format
	if(targetURL.indexOf("/")>=0)
		{
		 var objFrm = document.getElementById(menu+'_iframe');
		 var objFrmCntentD = (objFrm.contentWindow || objFrm.contentDocument);
		 objFrmCntentD.location=targetURL; 
		}
	ajaxComplete();
}
 
function checkTabHasURL(targetURL){
	
	var urlFound=false;
	var tabs = $('#tabframe').tabs('tabs');
	for(var i=0; i<tabs.length; i++){
	    if(tabs[i].panel('options').id==targetURL)
	    	urlFound=true;
	}
	return urlFound;
	
}


function menuSelected(menuName,isSelectHomeScreen)
{
	//var action="/AHIMSG5/hislogin/refreshHomeUserDesk.action";
	var action="/AHIMSG5/hislogin/refreshHomeUserDesk";
	var data = {
			varUserChoiceMenu:menuName
	 };
	 $.ajax
	 ({
				url : action, 
				type : "POST",
				async : true,
				dataType : "text",
				data :data,
				success : function(returnHTML) 
				{							
				  if($('#frmMainMenu').length && typeof menu != 'undefined')
						$('#frmMainMenu').attr('src','/AHIMSG5/hislogin/transactions/jsp/st_desk_homeMenuTab_page.jsp'); 					  
				  
				  if(isSelectHomeScreen)
				 	 $('#tabframe').tabs('select', 0); 
				  else{
					  //$('#tabframe').tabs('select', 1); 
					  //$('#tabframe').tabs('select', 0); 
				  }
				  
				  $("ul#menuList li").removeClass('selectedMenu');	
				  menuName=menuName.toString().replace(/ /g, '_');
				  $("#"+menuName).addClass('selectedMenu');	
				  
				},
				error : function(errorMsg, textstatus, errorthrown) 
				{
						alert("Sorry !! Not Able to Refresh the Home Tab");
				}
	 });	
}

function showHomeTab(){
	$('#tabframe').tabs('select', 0); 
}

function closeTab(url)
{
	var targetURL = url;	
	var elemFrame = document.getElementById("frmMain");
	
	if(elemFrame!=null){
		elemFrame.src=targetURL;
		elemFrame.refresh();
	}
	else{
		if(typeof $('#tabframe')!='undefined'){
			var tab = $('#tabframe').tabs('getSelected');
			var index = $('#tabframe').tabs('getTabIndex',tab);
			$('#tabframe').tabs('select',index-1);	
			$('#tabframe').tabs('close',index);	

		}
	}
}

function maintainLastSeenMenuArray(url,menu){
	//alert('maintain url: '+ url + 'menu: '+ menu);
	 if(menu === undefined){
	 }else{	 
		if(lastVisitedMenus.indexOf(menu)!= -1){
			deleteMenuFromCircularQueue(url,menu);
		}
		addMenuToCircularQueue(url,menu);
	} 
	//alert('lastVisitedMenus: '+ lastVisitedMenus);
}

 function addMenuToCircularQueue(url,menu){
	if(lastVisitedMenusStartIdx == -1 && lastVisitedMenusEndIdx == -1){// empty array 
		lastVisitedMenus[++lastVisitedMenusEndIdx]=menu;
		lastVisitedURLs[lastVisitedMenusEndIdx]=url;
		lastVisitedMenusStartIdx=0;
		lastVisitedMenusEndIdx++;
	}else{
		lastVisitedMenus[lastVisitedMenusEndIdx]=menu;
		lastVisitedURLs[lastVisitedMenusEndIdx]=url;
		if(lastVisitedMenusEndIdx== lastVisitedMenusStartIdx)
			lastVisitedMenusStartIdx=(lastVisitedMenusStartIdx+1)%maxLastVisitedMenuCount;
		lastVisitedMenusEndIdx=(lastVisitedMenusEndIdx+1)%maxLastVisitedMenuCount;
	}
}
function deleteMenuFromCircularQueue(url,menu){
	try {
		var menuIdx= lastVisitedMenus.indexOf(menu);
		var idx;
		var noOfShuflings=lastVisitedMenusEndIdx-menuIdx-1;
		if(noOfShuflings <0){
			noOfShuflings=noOfShuflings+ maxLastVisitedMenuCount;
		}
		if(noOfShuflings > 0){
			for(idx=1; idx <= noOfShuflings; idx++ ){
				lastVisitedMenus[menuIdx]=lastVisitedMenus[(menuIdx+1)%maxLastVisitedMenuCount];
				lastVisitedURLs[menuIdx]=lastVisitedURLs[(menuIdx+1)%maxLastVisitedMenuCount];
				menuIdx=(menuIdx+1)%maxLastVisitedMenuCount;
			}
			lastVisitedMenus[menuIdx]='-1';
			lastVisitedURLs[menuIdx]='-1';
			lastVisitedMenusEndIdx=lastVisitedMenusEndIdx-1;
			if(lastVisitedMenusEndIdx<0)
				lastVisitedMenusEndIdx=lastVisitedMenusEndIdx+maxLastVisitedMenuCount;
		}else if(noOfShuflings == 0){ // only one element is there in array make array empty
			lastVisitedMenus[menuIdx]='-1';
			lastVisitedURLs[menuIdx]='-1';
			lastVisitedMenusStartIdx=-1;
			lastVisitedMenusEndIdx=-1;
		}
	}catch(err){
		alert(err);
	}	
}  

function deleteCookies()
{
	var allcookies = document.cookie.split(";");
	for (var i = 0; i < allcookies.length; i++)
	{
		var cookie = allcookies[i];
		var eqPos = cookie.indexOf("=");
		var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
		document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
		//document.cookie = 'UP-759283=; expires=Thu, 01-Jan-70 00:00:01 GMT;'; 
		//document.cookie = name+'="";-1; path=/';
	}
}

function callLogOut(e)
{
	deleteCookies();
	submitForm("logoutLogin");
// 	document.form1.hmode.value="LOG_OUT";
// 	//document.form1.action="../startup/loginAction";
// 	document.form1.action="loginAction";
// 	document.form1.submit();
}

function searchMenu()
{
	openURLInPopup('/AHIMSG5/hislogin/transactions/jsp/st_menu_search.jsp','500','200');
	
}

function showDiv()
{
	document.getElementById("envato-widget1").style.display= "block";
}
function hideDiv()
{
	document.getElementById("envato-widget1").style.display= "none";	
}

var secs;
var timerID = null;
var timerRunning = false;
var delay = 1000;
var currentDate=null;

function setTime()
{
	if('<s:property value="varIsFirstTimeLogin" />'=='1')
		//openURLInPopupWithoutClose("/AHIMSG5/hislogin/initFirstLoginLgnFtr.action","600","400");				
		openURLInPopupWithoutClose("/AHIMSG5/hislogin/initFirstLoginLgnFtr","600","400");				
	// Setting Time initially on Load
	var dateAsString = document.getElementsByName("varCurrentDate")[0].value;
	currentDate = convertStrToDate(dateAsString,'dd-MM-yyyy hh:mm'); // for format dd-MM-yyyy HH:mm  of 'dateAsString2'
	var tdDate=document.getElementById("dateTdId");
 	var newDateFormat = convertDateToStr(currentDate, "Week, dd-Mon-yyyy hh:mm");
	tdDate.innerHTML=newDateFormat;
	
	//alert("set time")
	InitializeTimer();
}

function InitializeTimer()
{
    // Set the length of the timer, in seconds
    secs = 60;
    StopTheClock();
    StartTheTimer();
}

function StopTheClock()
{	
	//alert("stop")
    if(timerRunning)
        clearTimeout(timerID);
    timerRunning = false;
}

function StartTheTimer()
{
	//alert("start")
	if (secs==0)
	{
		StopTheClock();
		var tdDate=document.getElementById("dateTdId");
		var objnewDate = addToDate(currentDate,1,"MI");
     	var newDateFormat = convertDateToStr(objnewDate, "Week, dd-Mon-yyyy hh:mm");
     	var newDate = convertDateToStr(objnewDate, "dd-MM-yyyy hh:mm");
     	currentDate = objnewDate;
     	
	   // alert("newDate---------"+newDate);
		tdDate.innerHTML=newDateFormat;
		document.getElementsByName("varCurrentDate")[0].value=newDate;

 	
      	InitializeTimer();
    }
    else
    {
        self.status = secs;
        secs = secs - 1;
      	timerRunning = true;
        timerID = self.setTimeout("StartTheTimer()", delay);
    }
}
function getNextMonth(monthId)
{
var monthArray=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];	
return monthArray[monthId];
}

function getNextDay(dayId)
{
var daysArray=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];	
return daysArray[dayId];
}
	
	

function checkIfSlideBarRequired(){
	var menuContainerWidth = $("#menuContainer").width();
	var menuWidth=0;
	$("#menuStrip #smoothmenu ul#menuList")
	.children("li")
	.each(
			function(index) {
				currentMenuWidth = $(this).outerWidth(true);
				menuWidth += currentMenuWidth;

			});
	if(menuContainerWidth > menuWidth){
		$('#arrow_left').css({'display':'none'});
		$('#arrow_right').css({'display':'none'});
		$('#menuStrip').css({'left':'0'});
	}
	
}
function adjustMenuAlignment() {
	var listColumnWidth = 0;
	var menuContainerWidth = $("#menuContainer").width();
	var leftOffset = Math.abs($("#menuStrip").offset().left);
	/*Find leftmost menu item */
	var cummulativeLeftMenuWidth = 0;
	var currentMenuWidth = 0;
	var currentWindowMenuWidth = 0;
	$("#menuStrip ul#menu")
			.children("li")
			.each(
					function(index) {
						currentMenuWidth = $(this).outerWidth(true);
						cummulativeLeftMenuWidth += currentMenuWidth;
						if (cummulativeLeftMenuWidth > leftOffset
								&& currentWindowMenuWidth < menuContainerWidth) {
							currentWindowMenuWidth += currentMenuWidth;
							listColumnWidth = $($(this).children('div'))
									.outerWidth(true);
							if (currentWindowMenuWidth + listColumnWidth > menuContainerWidth) {
								//alert('class added to '+ $(this).children("a").text());
								$(this).children('div').addClass(
										"align_right");
							} else {
								//alert('class removed from '+ $(this).children("a").text());
								$(this).children('div').removeClass(
										"align_right");
							}

						}

					});

}
	
function ajaxStart(){
    $("#loadingmessage").css("display","block");
}

function ajaxComplete(){
	$("#loadingmessage").css("display","none");
};
function ajaxStartMenu(){
    $("#loadingMenu").css("display","block");
}

function ajaxCompleteMenu(){
	$("#loadingMenu").css("display","none");
};
function ajaxStartTab(){
    $("#loadingTab").css("display","block");
}

function ajaxCompleteTab(obj)
{	
	//alert('4');
	$("#loadingTab").css("display","none");
	/*obj.contentDocument.onkeydown=firstKeyDown;   
	obj.contentDocument.onkeyup=shortcutKeysEventHandler;  
	window.focus();
	globalIframeId=obj.id;*/
	
	//var JS= obj.contentDocument.createElement('script');
	//JS.setAttribute('type', 'text/javascript');
	//JS.appendChild(document.createTextNode('alert(5)'));
	//document.getElementsByTagName('head').item(0).appendChild(JS);
	
	//JS.text= " var moduleCommonDIV='fade';var first_key_Down=false;var _helpOpenFlag=false;var keyCodeArray;var enableShortCutKey=true;var masterHotKeyCode=18; // Key :: ALT var cntrlKey=false;var exceptionKeysCombo=false; // ALT[17] + CTRL[18] + R[82]/S[83] In case of Billing var _helpKeyCode=112; // Key :: F1 function firstKeyDown(e) { alert('Hellos');  if(e.keyCode==masterHotKeyCode)  { first_key_Down=true;} if(first_key_Down && cntrlKey==false) { if(e.keyCode==18) { cntrlKey=true;}} if(first_key_Down && cntrlKey){if(e.keyCode==82 || e.keyCode==83){exceptionKeysCombo=true;	} }} ";
	//document.body.appendChild(JS);
};




/*
File Name 		: validation.js
Version	  		: 2.0
*/

/*
List of functions in this file

 1>	function validateData(e,index)
 2> HISValidator(frmname)
	2.1>addValidation(itemname,descriptor,errstr)
	2.2>clearAllValidations()
	2.3>validate()		
 3> trim(value)
*/


/* <p>Developer : Deepak Tiwari
 * <p>
 * <p>Fuction shortcutKeysEventHandler handles eventListeners
 * <p>attached to Save,Clear & Cancel images in whole module.
 * @param event
 * <Note> :: Check For <div id="normalMsg"> tag in your JSP. Help wold not work in case of absence
 */	
 
var moduleCommonDIV="SHORTCUTDIV"; 
var first_key_Down=false;
var _helpOpenFlag=false;
var imgArray;
var cssButtonArray;
var keyCodeArray;
var enableShortCutKey=true;
var masterHotKeyCode=18; // Key :: ALT 
var masterHotKeyCodeEsc=27; // Key :: ALT
var cntrlKey=false;
var exceptionKeysCombo=false; // ALT[17] + CTRL[18] + R[82]/S[83] In case of Billing
var _helpKeyCode=112; // Key :: F1
imgArray  = new Array();
cssButtonArray  = new Array();
var globalIframeId;

// imgArray stores names of Images used for various events.
// There can be multiple images for a single event.
// Corresponding to single event there can be only single event key code.
// Event Images and Key code used for that event should be at same index within their repective arrays.
// keyCodeArray defined in sequence with imgArray :: Event wise key code defined
// Keys || Insert : 45 :: Delete : 46 :: End : 35

var ie = document.all;
var nn6 = document.getElementById &&! document.all;
if(ie)
{   //alert('1');
    document.attachEvent('onkeydown',firstKeyDown);    
    document.attachEvent('onkeyup',shortcutKeysEventHandler);
}
else
{	//alert('2');
	document.onkeydown=firstKeyDown;    
    document.onkeyup=shortcutKeysEventHandler;  
    window.focus();
}

function callDefault(obj)
{
	//alert('3');
	document.onkeyup=parent.frmMainMenu.shortcutKeysEventHandler;
    var _win =obj.contentWindow;
    var _doc = _win.document;
    _doc.designMode = "on";
    if(_doc.addEventListener) 
    {// if support addEventListener
        _doc.addEventListener("keyup", parent.frmMainMenu.shortcutKeysEventHandler, true)
    }
    else 
    { //For IE
        _doc.attachEvent("onkeyup", shortcutKeysEventHandler);
    }
}
// Function <firstKeyDown> is where Hot Key is tracked and 
// any Module specific ShortCut Key Exceptions 
// could be defined.
function firstKeyDown(e)
{
	//alert("Hellod");
	if(e.keyCode==masterHotKeyCode )
	{
	    first_key_Down=true;
	} 
	if(e.keyCode==masterHotKeyCodeEsc)
	{
	    if(document.getElementById("SHORTCUTDIV").style.display=="block")
	    	closeShortCutPopup();
	}  
	if(first_key_Down && cntrlKey==false)
	{
		if(e.keyCode==18)
		{
			cntrlKey=true;
		}
	}
	if(first_key_Down && cntrlKey)
	{
		if(e.keyCode==82 || e.keyCode==83)
		{
		   exceptionKeysCombo=true;
		}   
	}		
}   


function shortcutKeysEventHandler(e)
{
	 //alert("short");
	 //keyCodeArray = new Array("48","49","50","51","52","53" , "54" , "55","56","57");	
	 if(e.keyCode==masterHotKeyCode || exceptionKeysCombo)
	 {
	    first_key_Down  = false;	    
	    exceptionKeysCombo = false;
	 }
	 else
	 {
		 /*if(first_key_Down==true)
         {
     	    if(e.keyCode==_helpKeyCode)
     	    {
     	    	if(_helpOpenFlag==false)
     	    	{
     	    	    _helpOpenFlag=true;     	    	   
     	    	    shortCutKeysHELP();
     	    	}
     	    	else
     	    	{
     	    	    _helpOpenFlag=false;
     	    	}   
     	    }
     	    else
     	    {
     	        if(bugReported==false && enableShortCutKey)
     	        {
     	           alert("Sorry, No ShortCut Event Attached.For Help Press ::  Alt+F1");
     	        }   
    	    }  
    	    first_key_Down=false;
         } */
		 if(first_key_Down == true && enableShortCutKey)
	 	 {
	 	 	 //for(var i=0;i<keyCodeArray.length;i++)
	 	 	//{
	 	 		//if(parseInt(e.keyCode)==parseInt(keyCodeArray[i]))
	 	 		//{
	 	 			if(parseInt(e.keyCode)=="48")//0
	 	 			{
	 	 				//window.parent.callMenu('/HISRegistration/registration/transactions/NewRegistration.action','Patient_Registration');
	 	 				window.parent.callMenu('/HISRegistration/registration/transactions/NewRegistration','Patient_Registration');
		 	 			return;	 	 			
	 	 			}
	 	 			else if(parseInt(e.keyCode)=="49")//1
 	 				{
	 	 				//window.parent.callMenu('/HISRegistration/registration/transactions/PatientVisit.action','Patient_Revisit');
	 	 				window.parent.callMenu('/HISRegistration/registration/transactions/PatientVisit','Patient_Revisit');
		 	 			return;
 	 				}
	 	 			else if(parseInt(e.keyCode)=="50")//2
	 	 			{
	 	 				//window.parent.callMenu('/HISRegistration/registration/transactions/EmgRegistration.action','Emergency_Registration');
	 	 				window.parent.callMenu('/HISRegistration/registration/transactions/EmgRegistration','Emergency_Registration');
		 	 			return;
 	 				}
	 	 			else if(parseInt(e.keyCode)=="51")//3
	 	 			{
	 	 				window.parent.callMenu('/HISRegistration/registration/commonaction.cnt?mode=SPLCLINICNEWREGWITHAPPOINTMENT','Special_Registration_With_Appointment');
		 	 			return;
 	 				}
	 	 			else if(parseInt(e.keyCode)=="52")//4
	 	 			{
	 	 				window.parent.callMenu('/HISRegistration/registration/commonaction.cnt?mode=NEWSPECIALCLINICVISITWITHAPPPOINTMENT','Special_Clinic_Visit_With_Appointment')
		 	 			return;
 	 				}
	 	 			else if(parseInt(e.keyCode)=="53")//5
	 	 			{
	 	 				//window.parent.callMenu('/HISRegistration/appointment/transactions/NewAppointment.action','New_Appointment');
	 	 				window.parent.callMenu('/HISRegistration/appointment/transactions/NewAppointment','New_Appointment');
		 	 			return;
 	 				}
	 	 			else if(parseInt(e.keyCode)=="54")//6
	 	 			{
	 	 				window.parent.callMenu('/HBIMS/billing/transactions/CashCollectionOnlineTransCNT.cnt','Cash_Collection_Online');
		 	 			return;
 	 				}
	 	 			else if(parseInt(e.keyCode)=="55")//7
	 	 			{
	 	 				window.parent.callMenu('/HBIMS/mms/transactions/LPIssueDeskTransCNT.cnt','Issue_Desk');
		 	 			return;
 	 				}
	 	 			else if(parseInt(e.keyCode)=="56")//8
	 	 			{
	 	 				window.parent.callMenu('/HBIMS/billing/transactions/DayEndTransCNT.cnt?billModuleId=3&userMode=1','Combined_ScrollXXYDay_End_Process');
		 	 			return;
 	 				}
	 	 			else if(parseInt(e.keyCode)=="57")//9
	 	 			{
	 	 				window.parent.callMenu('/HBIMS/ipd/transactions/PatientAdmissionTransCNT.cnt','Patient_Admission');
		 	 			return;
 	 				}
	 	 			else if(parseInt(e.keyCode)=="90")//Z
	 	 			{
	 	 				window.parent.callMenu('/HISInvestigationG5/new_investigation/investigationReqRaising.cnt','Online_Requisition_Raising');
		 	 			return;
 	 				}
	 	 			else if(parseInt(e.keyCode)=="65")//A
	 	 			{
	 	 				window.parent.callMenu('/HBIMS/mms/transactions/AcknowledgeTransCNT.cnt','Acknowledgement_Desk');
		 	 			return;
 	 				}
	 	 			else if(parseInt(e.keyCode)=="73")//I
	 	 			{
	 	 				window.parent.callMenu('/HBIMS/billing/transactions/IpdBillManagementTransNewCNT.cnt','Ipd Bill Management New');
	 	 				clickfocus();
	 	 				return;
 	 				}
	 	 			else if(parseInt(e.keyCode)=="78")//N
	 	 			{
	 	 				window.parent.callMenu('/HBIMS/mms/transactions/IndentTransCNT.cnt','Indent_Desk');
		 	 			return;
 	 				}
	 	 			else if(parseInt(e.keyCode)=="79")//O
	 	 			{
	 	 				window.parent.callMenu('/HISClinical/hisglobal/utility/dynamicdesk/enterNew.cnt?deskType=1','Doctor_Desk');
		 	 			return;
 	 				}
	 	 			else if(parseInt(e.keyCode)=="113")//F2
	 	 			{
	 	 				tabRefresh();
 	 				}
	 	 			else if(parseInt(e.keyCode)=="87")//W
	 	 			{
	 	 				window.parent.callMenu('/HISClinical/hisglobal/utility/dynamicdesk/enterNew.cnt?deskType=3','I_P_D_Nursing_Desk_New');
		 	 			return;
 	 				}
	 	 			else if(parseInt(e.keyCode)=="100")//NUMPAD 4
	 	 			{
	 	 				globalIframeId=document.activeElement.id;
	 	 				document.getElementById(globalIframeId).contentWindow.onClickTab('VIEWBILL');
		 	 			return;
 	 				}
	 	 			else if(parseInt(e.keyCode)=="99")//NUMPAD 3
	 	 			{
	 	 				globalIframeId=document.activeElement.id;
	 	 				document.getElementById(globalIframeId).contentWindow.onClickTab('BILLAPPROVAL');
		 	 			return;
 	 				}
	 	 			else if(parseInt(e.keyCode)=="97")//NUMPAD 1
	 	 			{
	 	 				globalIframeId=document.activeElement.id;
	 	 				document.getElementById(globalIframeId).contentWindow.onClickTab('ADDSERVICE');
		 	 			return;
 	 				}
	 	 			else if(parseInt(e.keyCode)=="98")//NUMPAD 2
	 	 			{
	 	 				globalIframeId=document.activeElement.id;
	 	 				document.getElementById(globalIframeId).contentWindow.onClickTab('UPDATEACCOUNTSTATUS');
		 	 			return;
 	 				}
	 	 			else if(parseInt(e.keyCode)=="107")//+Focus Trf Code
	 	 			{
	 	 				globalIframeId=document.activeElement.id;
	 	 				if(document.getElementById(globalIframeId).contentDocument.forms[0].strTariffCode!=undefined)
	 	 				document.getElementById(globalIframeId).contentDocument.forms[0].strTariffCode.focus();
		 	 			return;
 	 				}
	 	 			else
	 	 			{
	 	 				//alert("1");
	 	 				//if(document.getElementById("strSearchItemButtonDivId") != null)
	 	 				//{
	 	 				  	imgArray  = [
	 	 					               ['btn-sv.png' ],   //Array of Images Used for Save Event               
	 	 					               ['btn-clr.png' ],   //Array of Images Used for Clear Event               
	 	 					               ['btn-ccl.png'],  //Array of Images Used for Cancel Event               
	 	 					               ['plus.gif'],//Array of Images Used for plus Event               
	 	 					               ['ItemFinder.png'], // Array of Images used for search Event               
	 	 					               ['btn-ok.png'], // Array of Images used for search Event               
	 	 					               ['close_tab.png'], // Array of Images used for search Event
	 	 					               ['plus.gif'], // Array of Images used for open help Event
	 	 					               ['minus1.gif'], // Array of Images used for close help Event
	 	 					               ['print.gif'], // Array of Images used for close help Event
	 	 					            ];
	 	 				  	
	 	 				  	cssButtonArray  = [
	 	 						                 ['btn-sv' ],   //Array of Images Used for Save Event			                 
	 	 						                 ['btn-clr' ],   //Array of Images Used for Clear Event			                 
	 	 						                 ['btn-ccl'],  //Array of Images Used for Cancel Event			                 
	 	 						                 ['plus'],//Array of Images Used for plus Event			                 
	 	 						                 ['ItemFinder'], // Array of Images used for search Event			                 
	 	 						                 ['btn-ok'], // Array of Images used for search Event			                 
	 	 						                 ['close_tab'], // Array of Images used for search Event
	 	 						                 ['open_help'], // Array of Images used for open help Event
	 	 						                 ['close_help'], // Array of Images used for close help Event
	 	 						                 ['print_btn'], // Array of Images used for close help Event
	 	 						              ];

	 	 					keyCodeArray = new Array("114","46","35","107","73" , "79" , "67", "88", "77", "80");	
	 	 				 // }	
	 	 				//alert("2");
	 	 				 var retEval          =  false;	 
	 	 				 var imgName          =  false;	 
	 	 				 var bugReported      =  false;	 
	 	 				 var listenerIndx     =  false;	 
	 	 				 var selObjIndx       =  "";
	 	 				var  selObjType       =  "a";//a/img
	 	 				 var imageVisible     =  true;
	 	 				 var imageFound       =  false;
	 	 				 
	 	 				 var imgArrForKeyCode = new Array();
	 	 				 var imgArrForKeyCodeCss = new Array();
	 	 				// alert(exceptionKeysCombo);
	 	 				 for(var i=0;i<keyCodeArray.length;i++)
	 	 				 {
	 	 					//alert("3"+parseInt(keyCodeArray[i]));
	 	 							if(parseInt(e.keyCode)==parseInt(keyCodeArray[i]))
	 	 				 	 		{
	 	 				 	 			if(keyCodeArray.length==imgArray.length || keyCodeArray.length<imgArray.length)
	 	 				 	 			{
	 	 				 	 			   
	 	 				 	 			//alert("3.0"+parseInt(keyCodeArray[i]));
	 	 				 	 			imgArrForKeyCode=imgArray[i];
	 	 				 	 			   imgArrForKeyCodeCss=cssButtonArray[i];	 	 			  
	 	 				 	 			   imgName=true;
	 	 				 	 			}
	 	 				 	 			else
	 	 				 	 			{
	 	 				 	 			   alert("BUG::Image Sets Not defined for every Key Codes");	 	 			   
	 	 				 	 			   imgName=false;	 	 			   
	 	 				 	 			   bugReported=true;
	 	 				 	 			}
	 	 				 	 		}
	 	 				 }
	 	 				 if(imgName != false)
	 	 				 {		 	 				  
		 	 				  globalIframeId=document.activeElement.id;
		 	 				  var iframe = document.getElementById(globalIframeId);
		 	 				  var innerDoc = iframe.contentDocument || iframe.contentWindow.document;
		 	 				  var objCss = innerDoc.getElementsByTagName("a");
		 	 				  var obj = innerDoc.getElementsByTagName("img");
	 	 		
		 	 				  //alert("6>"+objCss.length+"-"+globalIframeId);
	 	 				    for(var i = obj.length-1 ; (i >= 0) && (listenerIndx==false) ; i--)
	 	 				    {
	 	 				   	   var strArr = new Array();	   	   	   	   
	 	 				   	   strArr     = obj[i].src.split("/");	 	 				   	   
	 	 				   	
	 	 				   		for(var x=0;x<imgArrForKeyCode.length;x++)
	 	 				   		{
	 	 				          if(strArr[strArr.length-1]==imgArrForKeyCode[x])
	 	 				          {
	 	 				        	listenerIndx = true;
	 	 				        	selObjIndx=i;
	 	 				   	        var selObj=obj[i];
	 	 				   	        imageFound=true;
	 	 				   	        selObjType="img";
	 	 				   	         
	 	 				   	         // While Loop::Checking whether the Button is visible on Screen or not.
	 	 				   	         while(selObj.parentNode.tagName!="FORM")
	 	 				   	         {
	 	 				   	             if(selObj.parentNode.tagName=="DIV")
	 	 				   	             {
	 	 				   	            	if(selObj.parentNode.style.display=="none")
	 	 				   	            	{
	 	 				   	            		imageVisible=false;
	 	 				   	            	}
	 	 				   	             }
	 	 				   	             selObj=selObj.parentNode;
	 	 				   	         }
	 	 				          } 
	 	 				       }  
	 	 				    }
	 	 				    if(imageFound==false || (imageFound==true && imageVisible==false))
	 	 				    {
	 	 				    	//alert("6>"+objCss.length);
	 	 				    	imageVisible=true;
	 	 				    	for(var i = objCss.length-1 ; (i >= 0) ; i--)
	 	 					    {
	 	 					   	   var objCssId;
	 	 					   	   objCssId     = objCss[i].id;
	 	 					   	//alert("6.0"+objCss[i].href);
	 	 					   	   if(objCss[i].id!="")
	 	 					   	   {
	 	 					   		   //alert("6.1");
	 	 					   		   for(var x=0;x<imgArrForKeyCodeCss.length;x++)
	 	 						       {
	 	 						          if(objCssId==imgArrForKeyCodeCss[x])
	 	 						          {
	 	 						        	//alert("6.2");
	 	 						        	listenerIndx = true;
	 		 	 				        	selObjIndx=i;
	 	 						   	         var selObj=objCss[i];
	 	 						   	         selObjType="a";
	 	 						   	         //alert(selObj.id);
	 	 						   	         // While Loop::Checking whether the Button is visible on Screen or not.
	 	 						   	         while(selObj.parentNode.tagName!="FORM")
	 	 						   	         {
	 	 						   	             if(selObj.parentNode.tagName=="DIV")
	 	 						   	             {
	 	 						   	            	if(selObj.parentNode.style.display=="none")
	 	 						   	            	{
	 	 						   	            		imageVisible=false;
	 	 						   	            	}
	 	 						   	             }
	 	 						   	             selObj=selObj.parentNode;
	 	 						   	         }
	 	 						          } 
	 	 						       } 
	 	 					   	   }
	 	 					    }
	 	 				    }
	 	 				    //alert("7"+selObjIndx);
	 	 				    if(selObjIndx >=0)
	 	 				    {
	 	 				    	///alert("8");
	 	 				    	if(typeof(obj[selObjIndx].attributes['onclick'])!="undefined" || typeof(objCss[selObjIndx].attributes['onclick'])!="undefined")
	 	 				       {
	 	 				    		//alert('88');
	 	 				    		//alert("9"+obj[listenerIndx].attributes['onclick']);
	 	 				    	  if(typeof(obj[selObjIndx].attributes['onclick'])!="undefined" && selObjType=="img")
	 	 				    	  {
	 	 				    		//  alert("7"+selObjIndx+"-"+obj[selObjIndx].id);
	 	 				    		 // alert("7"+obj[selObjIndx].name);
	 	 				    		  var invokeFuncName=obj[selObjIndx].attributes['onclick'].value;
	 	 				    	  }
	 	 				    	  else
	 	 				    	  {
	 	 				    		 // alert("8");
	 	 				           	  var invokeFuncName=objCss[selObjIndx].attributes['onclick'].value;
	 	 				    	  }
	 	 				          
	 	 				    	  //alert("10"+invokeFuncName);
	 	 				          if(invokeFuncName!="" && invokeFuncName.length>2 && invokeFuncName.indexOf('(')>-1 && invokeFuncName.indexOf(')')>-1)
	 	 				          {  
	 	 				             if(invokeFuncName.indexOf("return" ) > -1)
	 	 				             {
	 	 				                invokeFuncName=invokeFuncName.split("return ")[1];
	 	 				             }
	 	 				             if(_helpOpenFlag)
	 	 				             {
	 	 				             	document.getElementById(moduleCommonDIV).innerHTML="";
	 	 			     	    	    
	 	 			     	    	    _helpOpenFlag=false;
	 	 				             }
	 	 				             ///alert("imageVisible->"+imageVisible);
	 	 				             if(imageVisible)
	 	 				             {
	 	 				            	
	 	 				            	invokeFuncName=invokeFuncName.split("(")[0].split(")")[0];	 	 				            	
	 	 				            	//alert("11"+invokeFuncName);
	 	 				            	document.getElementById(globalIframeId).contentWindow[invokeFuncName]();
	 	 				            	//eval(document.getElementById("Cash Collection Desk_iframe").contentWindow[invokeFuncName]());
	 	 				            	 //var retEval=eval(innerDoc.invokeFuncName);
	 	 				             }
	 	 				             else
	 	 				             {
	 	 				             	alert("Associated Image Not Visible.");
	 	 				             }	 	 				             
	 	 				          }
	 	 				          else
	 	 				          {
	 	 				             alert("No Event Handler Attached To :: onClick ::  Found.");
	 	 				          }    
	 	 				       }
	 	 				       else
	 	 				       {
	 	 				       	   alert("No Event Listener :: onClick ::  Found.");
	 	 				       }   
	 	 				    }
	 	 				    else
	 	 				    {
	 	 				    	alert("Shortcut Associated Image Not Found.");
	 	 				    }  
	 	 			     }	 	 			    
	 	 			}
	 	 			
	 	 		//}
	 	 	//}
	 	 	if(e.keyCode==_helpKeyCode)
     	    {
     	    	if(_helpOpenFlag==false)
     	    	{
     	    	    _helpOpenFlag=true;     	    	   
     	    	    shortCutKeysHELP();
     	    	}
     	    	else
     	    	{
     	    	    _helpOpenFlag=false;
     	    	}   
     	    }
	 	 	first_key_Down=false;
	 	 }
	 }
}

//This Function opens ShortCut Key Help 

function shortCutKeysHELP() 
 {
   var qh=370;var qw=300;var dh=0;var dw=0;   
   if(window.innerHeight)
   {
      dh=window.innerHeight; 
      //alert("dh"+dh);
      dw=window.innerWidth;
   }
   else 
   {
      dh=document.documentElement.clientHeight;      
      dw=document.documentElement.clientWidth;
   }
   var tpos=parseInt((dh-qh)/2)+35; 
   //alert("tpos"+tpos)
   var lpos=parseInt((dw-qw)/2);
   
   var buttonStr;
   
   var wt = '<div id="qmvi_loading_div" class="centered" style="width:500px;font-family:Arial;text-decoration:none;font-weight:normal;font-size:13px;color:#00224A;background-color:#ffffff;border-width:1px;border-color:#828EA2;border-style:solid;">';
       wt+= '<table width="100%" cellspacing="1px" cellpadding="1px" border="0">';
       wt+= '<tr class="HEADER"><td colspan="4"align="center" style="font-weight: bold;">Short Cut Key Help Menu</td></tr>';
       wt+= '<tr><td class="multiLabel" width="20%" align="left" style="font-weight: bold;">Short Cut Keys</td><td class="multiLabel" width="30%" align="left">Event</td> <td class="multiLabel" width="20%" align="left"  style="font-weight: bold;">Short Cut Keys</td><td class="multiLabel" width="30%" align="left">Event</td></tr>';
       wt+= '<tr><td class="multiControl" width="20%"  style="font-weight: bold;">ALT + F3</td><td class="multiControl" width="30%" >SAVE</td><td class="multiControl" width="20%"   style="font-weight: bold;">ALT + Delete</td><td class="multiControl" width="30%" >CLEAR</td></tr>';
       wt+= '<tr><td class="multiControl" width="20%"  style="font-weight: bold;">ALT + End</td><td class="multiControl" width="30%" >CANCEL</td><td class="multiControl" width="20%"   style="font-weight: bold;">ALT + 0</td><td class="multiControl" width="30%" >New Registration</td></tr>';
       wt+= '<tr><td class="multiControl" width="20%"  style="font-weight: bold;">ALT + 1</td><td class="multiControl" width="30%" >Patient Revisit</td><td class="multiControl" width="20%"  style="font-weight: bold;">ALT + 2</td><td class="multiControl" width="30%" >Emergency Registration</td></tr>';
       wt+= '<tr><td class="multiControl" width="20%"  style="font-weight: bold;">ALT + 3</td><td class="multiControl" width="30%" >Spl Clinic Registration</td><td class="multiControl" width="20%"  style="font-weight: bold;">ALT + 4</td><td class="multiControl" width="30%" >Spl Clinic Revisit</td></tr>';
       wt+= '<tr><td class="multiControl" width="20%"  style="font-weight: bold;">ALT + 5</td><td class="multiControl" width="30%" >New Appointment</td><td class="multiControl" width="20%"  style="font-weight: bold;">ALT + 6</td><td class="multiControl" width="30%" >Cash Collection Desk</td></tr>';
       wt+= '<tr><td class="multiControl" width="20%"  style="font-weight: bold;">ALT + 7</td><td class="multiControl" width="30%" >Issue Desk</td><td class="multiControl" width="20%"  style="font-weight: bold;">ALT + 8</td><td class="multiControl" width="30%" >Scroll Generation</td></tr>';
       wt+= '<tr><td class="multiControl" width="20%"  style="font-weight: bold;">ALT + 9</td><td class="multiControl" width="30%" >Patient Admission</td><td class="multiControl" width="20%"  style="font-weight: bold;">ALT + Z</td><td class="multiControl" width="30%" >Investigation Raising</td></tr>';
       wt+= '<tr><td class="multiControl" width="20%"  style="font-weight: bold;">ALT + A</td><td class="multiControl" width="30%" >Acknowledge Desk</td><td class="multiControl" width="20%"  style="font-weight: bold;">ALT + I</td><td class="multiControl" width="30%" >IPD Bill Management</td></tr>';
       wt+= '<tr><td class="multiControl" width="20%"  style="font-weight: bold;">ALT + N</td><td class="multiControl" width="30%" >Indent Desk</td><td class="multiControl" width="20%"  style="font-weight: bold;">ALT + O</td><td class="multiControl" width="30%" >OPD Doctor Desk</td></tr>';
       wt+= '<tr><td class="multiControl" width="20%"  style="font-weight: bold;">ALT + W</td><td class="multiControl" width="30%" >IPD Nursing Desk</td><td class="multiControl" width="20%"  style="font-weight: bold;">ALT + F2</td><td class="multiControl" width="30%" >Tab Refresh</td></tr>';
       wt+= '<tr><td class="multiControl" width="20%"  style="font-weight: bold;">ALT + F1</td><td class="multiControl" width="30%" >HELP</td>  <td class="multiControl" width="20%"  style="font-weight: bold;">ALT + ESC</td><td class="multiControl" width="30%" >Hide HELP</td></tr>';





 
       if(document.getElementById("strSearchItemButtonDivId") != null)
       {       		
       		 wt+= '<tr><td class="multiControl" width="50%">ALT + P</td><td class="multiControl" width="50%">Search Items Popup</td></tr>';
       		 wt+= '<tr><td class="multiControl" width="50%">ALT + O</td><td class="multiControl" width="50%">Ok</td></tr>';
       		 wt+= '<tr><td class="multiControl" width="50%">ALT + C</td><td class="multiControl" width="50%">Cancel</td></tr>';       		
       }      
       if(enableShortCutKey)
       {
         buttonStr="Disable";
       } 
       else
       {
         buttonStr="Enable"; 
       }    
       wt+= '<tr><td class="multiControl" width="100%" colspan="4"><input type="button" class="shortCutBtn" name="shortCutHelpED_Button" value="'+buttonStr+'" onClick="enableDisableShortCutKeys();"/>';
       wt +=' <input type="button" value="Close" onClick="closeShortCutPopup();" > </td></tr>';
       //wt+= '<tr class="FOOTER"><td colspan="2"></td></tr>';
       wt+= '</table>';
       wt+='</div>';
       
   document.getElementById(moduleCommonDIV).innerHTML="";   
   document.getElementById(moduleCommonDIV).style.display="block";   
   document.getElementById(moduleCommonDIV).innerHTML=wt;
 }


function closeShortCutPopup()
{
	 document.getElementById(moduleCommonDIV).innerHTML="";
	 _helpOpenFlag=false;
}

// This Function Enables or Disables ShortCutKey Event Handler
 
function enableDisableShortCutKeys()
{
	if(enableShortCutKey)
	{
	    enableShortCutKey=false;
	    document.forms[0].shortCutHelpED_Button.value="Enable Short Cut Keys";
	    document.getElementById(moduleCommonDIV).innerHTML="";
	    _helpOpenFlag=false;
	}
	else
	{
	    enableShortCutKey=true;
	    document.forms[0].shortCutHelpED_Button.value="Disable Short Cut Keys";
	    document.getElementById(moduleCommonDIV).innerHTML="";
	    _helpOpenFlag=false;
	}    
}

function form_submit_handler()
{
	for(var itr=0;itr < this.formobj.elements.length;itr++)
	{
		if(this.formobj.elements[itr].validationset &&
	   !this.formobj.elements[itr].validationset.validate())
		{
			return false;
		}
	}
	
	if(!this.validatorRetValue) 
	{
		alert(this.validatorError);
		return false;
	}
		
	return true;
}

/*this function submits the page*/
function submitForm(mode)
{
	
	//document.forms[0].action = mode+ ".action";
	document.forms[0].action = mode;
	document.forms[0].submit();
	
	//document.form.hmode.value=mode;
	//document.form.submit();
}//end of validateFun


/*=========================================end of file===============================================*/

