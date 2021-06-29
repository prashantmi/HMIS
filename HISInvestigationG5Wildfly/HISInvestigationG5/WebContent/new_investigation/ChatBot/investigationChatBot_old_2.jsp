<!-- 
Code by- Abhijat Chaturvedi
Created Date- 16 July 2019
Last Edit Date- 
Last Edit By- 
 -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<his:javascript src="/hisglobal/js/pdf.js" />
<his:javascript src="/hisglobal/js/pdf.worker.js" />
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="facivon.ico">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<title>AIIMSBot</title>
    <link rel="stylesheet"	href="/HIS/hisglobal/css/inv_chatbot_conversational_style.css">

	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

	<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.css" rel="stylesheet" type="text/css" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>
	
	 </head>
  <body>

<div class="msg_box" style="right:290px">
	<div class="msg_head">AIIMSBot
	</div>
	<div class="msg_wrap">
		<div class="msg_body">
			<div class="msg_s" id="msg_s">Hi! I'm AIIMSBot.	</div>
			<div class="msg_s" id="msg_s1">You can book appointment in special clinic from here.	</div>
			<div class="msg_s" id="msg_s2">Type in 'book an appointment' to start. Use 'Tab' key for autocomplete.</div>
			<div class="msg_s" id="msg_s3">Type in 'view report' to see lab reports of a patient.</div>
			<div class="msg_s" id="msg_s4">Type in 'help' for help.</div>
			 <script>
				  $(document).ready(function () {
					  $("#msg_s").hide();
					  $("#msg_s").delay(500).fadeIn(100);  
					  $("#msg_s1").hide();
					  $("#msg_s1").delay(1500).fadeIn(100);  
					  $("#msg_s2").hide();
					  $("#msg_s2").delay(2500).fadeIn(100); 
					  $("#msg_s3").hide();
					  $("#msg_s3").delay(3500).fadeIn(100); 
 					  $("#msg_s4").hide();
					  $("#msg_s4").delay(4500).fadeIn(100); 
					  $("#msg_b").fadeIn(100);
					  $("#msg_a").fadeIn(100);
				  });    
			  </script>
			
			<div class="msg_push"></div>
		</div>
        <p class="guess_class" id="guess"></p>
		<button class="button3" id="restart_btn" onclick="restart()" style="background-color:#ff8566;">Clear</button>
	<div class="msg_footer">
	<textarea class="msg_input" autocomplete="off" id="input" type="text" rows="2" style = "resize:none" placeholder="Type here" onkeyup="textUpdated(this)" ></textarea>
</div>
</div>
</div>

<script>
const guessPara = document.getElementById('guess');
const inputTextbox = document.getElementsByClassName('msg_input');
var wordList = null;
var idiomList = null;
var acText = "";
var slots='';
var reports='';
var report_datetime='';
var test_name='';
var rep1='';
var pdf_string='';


$.ajax({
	url: "/HIS/hisglobal/chatbot_data/words.txt"
})
	.done(function(data) {
	    //alert("In words");
		wordList = data.split('\n');
		//alert(wordList);
	});

$.ajax({
	url: "/HIS/hisglobal/chatbot_data/phrases.txt"
})
	.done(function(data) {
	    //alert("In phrases");
		idiomList = data.split('\n');
		//alert(idiomList);
	});



function textUpdated(input) {
	text1 = input.value;

	if (text1 == "") {
		setAutoCompleteText("","");
		return;
	}
	let ending = text1.endsWith(' ') ? "" : getRecommendedEnding(text1);
	//alert(ending);
	setAutoCompleteText(text1, ending)
}

function detectSpecialKeys(e) {
	//alert("In detect spl keys");
	if (e.keyCode == 9) {
		e.preventDefault();
		input.value = acText;		
	} else if (e.keyCode == 27) {
		e.preventDefault();
		input.value = "";		
	}
}

function setAutoCompleteText(enteredText, autoCompleteEnding) {
	if (autoCompleteEnding == undefined) {
		autoCompleteEnding = "";
	}
	acText = enteredText + autoCompleteEnding;
    //alert(acText);
	guessPara.innerHTML = "<span class='entered'>"+enteredText+"</span><span class='autocomplete'>"+autoCompleteEnding+"</span>";

}

function getRecommendedEnding(text1) {
	let newText = text1.toLowerCase();
	let idiomPart = "";

	if (text1.includes(' ')) {
		let split = text1.split(' ');

		newText = split[split.length - 1];

		if (split.length > 1) {
			let idiomSearch = split[split.length - 2] + ' ' + split[split.length - 1];
			let srch = searchIdiomList(idiomSearch);

			if (srch.length > 0) {
				idiomPart = srch;
				text1 = idiomSearch
			}
		}
	}

	if (idiomPart.length > 0) {
		let ending = "";
		let split = idiomPart.split(text1);

		if (split.length >= 3) {
			for (part in split) {
				if (part > 0) {
					if (part != split.length - 1)
						ending += split[part] + text1;
					else
						ending += split[part];
				}
			}
		} else {
			ending = split[1];
		}
		return ending;
	}

	let wordEnding = searchWordList(newText);
	let ending = wordEnding.split(newText)[1];
    //alert(ending);
	return ending;
}

function searchWordList(search) {
	for (word in wordList) {
		if (word.startsWith(search)) {
			return word;
		}
	}
	return "";
}

function searchIdiomList(search) {
	for (idiom in idiomList) {
		if (idiom.startsWith(search)) {
			return idiom;
		}
	}
	return "";
}

function put_value(selectedValue){
	var v = selectedValue.value;
	<!-- alert(v); -->
	document.getElementById("input").value = v.toString();
  	document.getElementById('input').focus();
}


function con_yes_fn(){
	document.getElementById("input").value = 'yes';
	document.getElementById('input').focus();
	inp = "yes";
	diable_selections();
	response_fn(inp);
	document.getElementById("input").value = '';
}

function con_no_fn(){
	document.getElementById("input").value = 'no';
	document.getElementById('input').focus();
	inp = "no";
	diable_selections();
	response_fn(inp);
	document.getElementById("input").value = '';
}

function restart(){
	inp = "restart";
	response_fn(inp);
	document.getElementById("input").value = '';
	$('.msg_a').remove();
	$('.msg_b').remove();
	$('#quesList').hide();
	$("#quesList").empty()
	document.getElementById('input').focus();
}


function get_value(){
	var e = document.getElementById("input");
	var inp = e.value;	
	document.getElementById("input").value='';
	diable_selections();
	response_fn(inp);
}


function response_fn(selectedValue){
	var msg = selectedValue;
	if(msg!=''){
	$('<div class="msg_b">'+msg+'</div>').insertBefore('.msg_push');
	$('.msg_body').scrollTop($('.msg_body')[0].scrollHeight);
	var myvar=setTimeout(function myfun(){$('<div class="msg_c">'+'Bot is typing'+'</div>').insertBefore('.msg_push');},2500);
	
	var msg_formated=msg.replace(/\s+/g, '%20').toLowerCase();
	var myurl='/ml_war/service/conv/con_bot?msg='+msg_formated;
	/* var myurl='http://10.226.24.102:5000/post?msg='+msg_formated; */
	var show_op_msg= true;
	$.ajax({
			type: "post",
			url: myurl,
			dataType: "html",
			success: function(results){
				//alert(results);
				if (results.slice(0,1)=='#'){
					var code=results.slice(0,7);
					results_new=results.substr(7);
				}
				else{
					code == 'nan'
					results_new=results;
				}
				var botHtml = '<div class="msg_a">' + results_new;		
				clearTimeout(myvar);
				$('.msg_s').hide();
				
				if (code == '##dep##' || code == '##idt##'){
					var depHtml = '<div class="dept_selector" id="dept_selector"><select id="dept_selection" onchange="put_value(this)"><option value="">Select One</option><option value="Physiotherapy">Physiotherapy</option> <option value="Anaesthesiology" >Anaesthesiology</option></select> <button id="dept_btn" onclick="get_value()" >GO</button> </div>';
					botHtml = botHtml + depHtml + '</div>';
				}
				else if (code=='##dat##' || code=='##icd##' || code == '##nfs##') {		
					document.getElementById("dept_selection").disabled = true;
					document.getElementById("dept_btn").disabled = true;
					//var datepicker =  '<div id="datetimepicker4" class="input-append"><input data-format="dd-MM-yyyy" type="text"></input><span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar"> </i> </span> </div>';
					var dateHtml = '<div class="date_selector" id="date_selector" ><input type="date" data-date="14-June-2019" data-date-format="DD MMMM YYYY" id="myDate" onchange="put_value(this)"><button id="dat_btn" onclick="get_value()">GO</button> </div>';
					//var dateHtml = '<div id="datepicker" class="input-group date" data-date-format="dd-mm-yyyy"><input type="text" id="myDate"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span><button id="dat_btn" onclick="get_value()">GO</button></div>';
					botHtml = botHtml+ dateHtml + '</div>';
				}
				else if (code=='##tim##'){					
					document.getElementById("myDate").disabled = true;
					document.getElementById("dat_btn").disabled = true;
					botHtml = '<div class="msg_a">' + 'Select one of the time slots.';
					slots = results_new.split(',');
					var i=0;
					var timeHtml = '<div class="time_selector" id="time_selector" ><select id="time_select" onchange="put_value(this)"><option value="">Select One</option>';
					for (i=0;i<slots.length-1;i++){
						timeHtml += '<option value="'+slots[i]+'">'+slots[i]+'</option>';
					}
					timeHtml += '</select><button id="time_btn" onclick="get_value()">GO</button> </div>';
					botHtml = botHtml +timeHtml + '</div>';
				}
				else if(code=='##ict##'){
					document.getElementById("myDate").disabled = true;
					document.getElementById("dat_btn").disabled = true;
					var timeHtml = '<div class="time_selector" id="time_selector" ><select id="time_select" onchange="put_value(this)"><option value="">Select One</option>';
					var i=0;
					for (i=0;i<slots.length-1;i++){
						timeHtml += '<option value="'+slots[i]+'">'+slots[i]+'</option>';
					}
					timeHtml += '</select><button id="time_btn" onclick="get_value()">GO</button> </div>';
					botHtml = botHtml +timeHtml + '</div>';
				}
				else if (code == '##con##' ){		
					document.getElementById("time_select").disabled = true;
					document.getElementById("time_btn").disabled = true;
					var y = "yes";
					var n = "no";
					var depHtml = '<div class="con_selector" id="con_selector"><button onclick="con_yes_fn()" id="yes_btn">Yes</button><button id="no_btn" onclick="con_no_fn()">No</button></div>';
					botHtml = botHtml + depHtml + '</div>';
				}
				else if (code == '##res##'){
					$('.msg_s').show();
					$('.msg_a').remove();
					$('.msg_b').remove();
					botHtml =botHtml+ '</div>';
					show_op_msg= false;
				}	
				else if(code=='##abs##'){
					document.getElementById("yes_btn").disabled = true;
					document.getElementById("no_btn").disabled = true;
				}
				else if(code=='##rep##'){
					var sp = results_new.split("###");
					reports=sp[0];
					test_name=sp[1];
					report_datetime=sp[2];
					reports=reports.split(',');
					test_name=test_name.split(',');
					report_datetime=report_datetime.split(',');
					var i=0;
					var timeHtml = '<div class="time_selector" id="report_selector" ><select id="time_select" onchange="put_value(this)"><option value="">Test Name---Date</option>';
					for (i=0;i<reports.length-1;i++){
						timeHtml += '<option value="'+reports[i]+'">'+test_name[i]+'---'+report_datetime[i]+'</option>';
					}
					timeHtml += '</select><button id="report_btn" onclick="view_report()">GO</button> </div>';
					botHtml = timeHtml + '</div>';
				}
				else if(code=='##tem##'){
					var sp = results_new.split(",");
					rep1=[sp[0],sp[1],sp[2]];
					pdf_string=[sp[3],sp[7],sp[11]];
					var date_req=[sp[4],sp[8],sp[12]];
					test_name=[sp[5],sp[9],sp[13]];
					var dept_name=[sp[6],sp[10],sp[14]];
					var date_test=['3 May','3 May','12 Apr'];
					var timeHtml = '<div class="time_selector" id="report_selector" ><select id="time_select" onchange="put_value(this)"><option value="">Select Report</option>';
					for (i=0;i<rep1.length;i++){
						timeHtml += '<option value="'+rep1[i]+'">'+dept_name[i]+' | '+date_req[i]+' | '+test_name[i]+'</option>';
					}
					timeHtml += '</select><button id="report_btn" onclick="view_report()">GO</button> </div>';
					botHtml = timeHtml + '</div>';
				}
				if(show_op_msg){
				$('<div class="msg_a">'+botHtml+'</div>').insertBefore('.msg_push');
				$('.msg_body').scrollTop($('.msg_body')[0].scrollHeight);
				}	
			},
			error: function(results) {
				var botHtml = '<div class="msg_a">' ;
				var e = "There is an error. "+results.toString();
				botHtml = botHtml + e+ '</div>';
				//alert("There is an error. "+results.toString());
				$('<div class="msg_a">'+botHtml+'</div>').insertBefore('.msg_push');
				$('.msg_body').scrollTop($('.msg_body')[0].scrollHeight);
			},
		});
		}
		else{
			$('<div class="msg_a">'+"Provide an input"+'</div>').insertBefore('.msg_push');
				$('.msg_body').scrollTop($('.msg_body')[0].scrollHeight);
		}
}


function back(){
	$('.msg_s').show();
	$('#mybutton1').show();
	$('#mybutton2').show();
	$('#mybutton6').show();
	$('#mybutton3').hide();
	$('#quesId').hide();
	$('#crnId').hide();
	$('#mybutton4').hide();
	$('#crnId').val('');
	$('#quesId').val('');
	$('.msg_a').hide();
	$('.msg_b').hide();
	$('#mybutton5').hide();
	$('#quesList').hide();
}

function clear_chat(){
	$('.msg_a').remove();
	$('.msg_b').remove();
	$('#quesList').hide();
	$("#quesList").empty()
}

function openForm() {
  document.getElementById("myForm").style.display = "block";  
  $("#image_bd").hide();
  $('#first_msg_1').show();
  $('#first_msg_2').show();
  $('#first_msg_3').show();
  $('#mybutton3').hide();  
}

function closeForm() {
  document.getElementById("myForm").style.display = "none";
  $("#image_bd").show();
  $('.msg_a').hide();
  $('.msg_b').hide();
  $('.msg_s').hide();
  $('#mybutton1').show();
  $('#mybutton2').show();
  $('#mybutton3').show();
  $('#quesId').hide();
  $('#crnId').hide();
  $('#crnId').val('');
  $('#quesId').val('');
  $('#mybutton4').hide();  
  $('#mybutton5').hide();
}

function showbox_ques(){
	$('#mybutton1').hide();
	$('#mybutton2').hide();
	$('#mybutton6').hide();
    $('.msg_s').hide();
	$('#quesId').show();
	$('.msg_a').hide();
	$('.msg_b').hide();
	$('#mybutton3').show();
	$('#mybutton5').show();
}

function showbox_crn(){
	$('#mybutton1').hide();
	$('#mybutton2').hide();
	$('#mybutton3').hide();
	$('#mybutton6').hide();
	$('#crnId').show();
	$('.msg_a').hide();
	$('.msg_s').hide();
	$('#mybutton3').show();
	$('#mybutton4').show();
	$('#mybutton5').show();
}
function showbox_app(){
	$('#mybutton1').hide();
	$('#mybutton2').hide();
	$('#mybutton3').hide();
	$('#mybutton6').hide();
	$('.msg_a').hide();
	$('.msg_s').hide();
	$('#mybutton3').show();
	$('#mybutton5').show();
}

function view_report() {
	<!-- alert("in view_report"); -->
	var e = document.getElementById("input");
	var pdf = e.value;	
	document.getElementById("input").value='';
	<!-- alert(pdf); -->
	ind=rep1.indexOf(pdf);
	<!-- alert("ind = "+ind); -->
	pdf1=pdf_string[ind];
	if (pdf1 != null){
		var windo = window.open("", "","width=600,height=600");
		var objbuilder = '';
		objbuilder += ('<embed width=\'100%\' height=\'100%\'  src="data:application/pdf;base64,');
		objbuilder += (pdf1);
		objbuilder += ('" type="application/pdf" />');
		windo.document.write(objbuilder);
	}
}


function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode > 48 || charCode < 57)) {
        return false;
    }
	else if (charCode == 13){
		submit();
	}
    return true;
}

function diable_selections(){
	if (document.getElementById("dept_selection")){
	document.getElementById("dept_selection").disabled = true;
	}
	if(document.getElementById("dept_btn")){
	document.getElementById("dept_btn").disabled = true;
	}
	if(document.getElementById("myDate")){
	document.getElementById("myDate").disabled = true;
	}
	if(document.getElementById("dat_btn")){
	document.getElementById("dat_btn").disabled = true;
	}
	if(document.getElementById("time_select")){
	document.getElementById("time_select").disabled = true;
	}
	if(document.getElementById("time_btn")){
	document.getElementById("time_btn").disabled = true;
	}
	if(document.getElementById("yes_btn")){
	document.getElementById("yes_btn").disabled = true;
	}
	if(document.getElementById("no_btn")){
	document.getElementById("no_btn").disabled = true;
	}
}


$(document).ready(function(){

	$('.chat_head').click(function(){
		$('.chat_body').slideToggle('slow');
	});
	$('.msg_head').click(function(){
		$('.msg_wrap').slideToggle('slow');
	});
	
	$('.close').click(function(){
		$('.msg_box').hide();
	});
	
	$('.user').click(function(){

		$('.msg_wrap').show();
		$('.msg_box').show();
	});
	
	$('#mybutton3').hide();
	$('#mybutton4').hide();
	$('#mybutton5').hide();
	$('.msg_input').focus();
	
		
$('textarea').keydown(
    function(e){
		if (e.keyCode == 9) {
			e.preventDefault();
			//alert("Tab Pressed");            
			$(this).val(acText) ;			
		}
		else if (e.keyCode == 27) {
			e.preventDefault();
			//alert("Esc Pressed");
			$(this).val('');	
		}
        else if (e.keyCode == 13) {
			$('.msg_s').hide();
			$('#mybutton1').hide();
			$('#mybutton3').show();
			$('#mybutton5').show();
			//alert("Enter Pressed");
            e.preventDefault();
            var msg = $(this).val();
			$(this).val('');
			diable_selections();
			response_fn(msg);
	    }		
    });
	
});

</script>
</body>
