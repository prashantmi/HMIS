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
<link rel="shortcut icon" href="facivon.ico">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />  
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="facivon.ico">
<link rel="stylesheet" type="text/css" href="scripts/inv_cb_style.css">
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- <script src="//mozilla.github.io/pdf.js/build/pdf.js"></script>
 -->
<script type="text/javascript"
	src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js"
	djConfig="parseOnLoad: true"> </script>
<style>  
           ul{  
                background-color:#EEF2E7;  
                cursor:pointer;
                margin:2px; 
           }  
           li{  
                padding:10px; 
                cursor:pointer;
                background-color:#EEF2E7;
                
           }  
           </style>
  </head>
  
<body>
<%-- <html:form action="/investigationChatBot"> --%>
 <input type="hidden" name="ans" id="ansId" class="form-control" placeholder="Type Here" /> 
 <div class="image">
<img src="media/images/bot.png" id="image_bd" height="80" width="80" onclick="openForm()" title="Click for Help">
</div>
<!-- <div class="chat-popup" id="myForm" style="resize:both;overflow:auto;"> -->
<div class="chat-popup" id="myForm" >

<div class="msg_head" onclick="closeForm()">AIIMS FAQ Bot<div class="cm" id="cmid" title="Close">×</div></div>
  <div class="msg_wrap">
		<div class="msg_body" id="msg_body_id">
			<div class="msg_c" id="first_msg_1">Hi! I'm AIIMS FAQ Bot.</div>
			<div class="msg_c" id="first_msg_2">Please click 'Ask General Query' button to ask search FAQ's.</div>
<!-- 			<div class="msg_c" id="first_msg_3">And if you want to know the report details of a patient, please click 'Enquire CRN' button. </div>	
 -->			<div class="container" style="width:100%; padding:8px"> 
				<button class="button" id="mybutton1" onclick="showbox_ques()" style="background-color:#ff8566;"><span>Ask General Query</span></button>
<!-- 				<button class="button" id="mybutton2" onclick="showbox_crn()" style="background-color:#ff8566;"><span>Enquire CRN</span></button>
 -->				
				<button class="button2" id="mybutton3" onclick="back()" style="background-color:#ff8566;"><span>Back</span></button>
				<button class="button2" id="mybutton5" onclick="clear_chat()" style="background-color:#ff8566;"><span>Clear</span></button>
				
                <input type="text" name="ques" id="quesId" class="form-control" placeholder='' style="display:none;" title="Start typing and select from list"/>
                <input type="text" name="crn" id="crnId" class="form-control" placeholder="Enter CRN Number" maxlength="15" style="display:none;" onkeypress="return isNumber(event)" title="Numeric Only"/>
				<button class="button" id="mybutton4" onclick="submit()" style="background-color:#ff8566;margin-top:10px;"><span>Submit</span></button>
				<div id="quesList"></div>
           </div>
			<div class="msg_push"></div>
		</div>
</div>
</div>
 <%-- </html:form> --%> 
</body>


<script>
window.onload=function openbox() {
  document.getElementById("myForm").style.display = "block";
  $('#mybutton3').hide();
  $('#mybutton4').hide();
  $('#mybutton5').hide();
  var timer = setTimeout(function(){document.getElementById("myForm").style.display = "none";}, 4000);
  $(document.getElementById("myForm")).on("click",function(){
  clearTimeout(timer);
});
$(document.getElementById("myForm")).on("mouseenter",function(){
  clearTimeout(timer);
});
/* $.ajax({  
    url:"/HISInvestigationG5/new_investigation/investigationChatBot.cnt?hmode=AJX_GET_QUES", sync:true, postData: "", handleAs: "text",  
    method:"POST",  
    //data:{query:query},               
    success:function(data)  
    {
   	 alert("in ques success"); 
   	 var botreply = JSON.parse(data);
   	 alert(botreply);
    },
    error:function(error){
   	 alert("ERROR: "+eval(error));
    }
}); */ 
/* $.ajax({  
    url:"/HISInvestigationG5/new_investigation/investigationChatBot.cnt?hmode=AJX_GET_ANS", sync:true, postData: "", handleAs: "text",  
    method:"POST",  
    //data:{query:query},               
    success:function(data)  
    {
   	 alert("in ans success"); 
   	 var botreply = JSON.parse(data);
   	 alert(botreply);
    },
    error:function(error){
   	 alert("ERROR: "+eval(error));
    }
}); */
};

function back(){
	$('.msg_c').show();
	$('#mybutton1').show();
	$('#mybutton2').show();
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
  //document.getElementById('image_bd').style.visibility = 'hidden';
  $('#first_msg_1').show();
  $('#first_msg_2').show();
  $('#first_msg_3').show();
  $('#mybutton3').hide();  
}

function closeForm() {
  document.getElementById("myForm").style.display = "none";
  $("#image_bd").show();
  //document.getElementById('image_bd').style.visibility = 'visible';
  $('.msg_a').hide();
  $('.msg_b').hide();
  $('.msg_c').hide();
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
    $('.msg_c').hide();
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
	$('#crnId').show();
	$('.msg_a').hide();
	$('.msg_c').hide();
	$('#mybutton3').show();
	$('#mybutton4').show();
	$('#mybutton5').show();
}

function submit()
{	
	var crNo = document.getElementById("crnId").value;
	$('#crnId').val('');
	len=crNo.toString().length;	
	if(len==15){
		var botHtml = null;
		var pdf = null;
		var flg = false;
		var remarks = "";
		var _mode = "AJX_GET_REQUISITIONNO";
		var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationChatBot.cnt?hmode="+_mode+"&crNo="+crNo, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert("In ajax call");
			if (data != ''){
				//alert("Data is not null");
				var botreply = JSON.parse(data);
				//alert(botreply);
				size_of_reply=botreply.length;
				var no_of_records=Math.min(5,size_of_reply);
				var i=0;
				//alert(no_of_records);
				$('<div class="msg_b">'+crNo+'</div>').insertBefore('.msg_push');
				var flag_null = true;
				while (i<size_of_reply){
					var pdf = botreply[i].report;
					//alert(pdf);					
					if (pdf != null){
					flag_null = false;
					var reply ="Test- "+botreply[i].testname+"<br>"+"Req. Date- "+botreply[i].reqdate+"<br><a id='view_report' href='javascript:view_report("+data+","+i+");'>View Report</a>";
					//alert(encodeURI(botreply[i].reporturl));
					botHtml = '<div class="msg_a">' +reply+ '</div>';
					//alert(botHtml);
					$('<div class="msg_a">'+botHtml+'</div>').insertBefore('.msg_push');
					 }
					i++;
				}
				//alert(flag_null);
				if (flag_null){
					botHtml = '<div class="msg_a">' +"No reports to display"+ '</div>';
					$('<div class="msg_a">'+botHtml+'</div>').insertBefore('.msg_push');
				}
				}
				else{
					//alert("Data is null");
					botHtml = '<div class="msg_a">' + "Invalid CRN number." + '</div>';
				}
			$('.msg_body').scrollTop($('.msg_body')[0].scrollHeight);
			remarks = data;
			flg = true;
		},
        error: function(error)
        {
        	//alert("In Error function");
        	//alert(error);
        	botHtml = '<div class="msg_a">' + error + '</div>';
            //remarks = tmpLabTestCodeArray;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	
	//alert(objDojoAjax);
	return null;
	}
	
	else{
		var botHtml = '<div class="msg_b">' + "CRN Number is of 15 digits" + '</div>';
		//$('<div class="msg_b">'+x+'</div>').insertBefore('.msg_push');					
		$('<div class="msg_b">'+botHtml+'</div>').insertBefore('.msg_push');
		$('.msg_body').scrollTop($('.msg_body')[0].scrollHeight);
	}
	
}

function view_report(data, i) {
	//alert("In view report ");
	//alert(i);
	var pdf= null;
	var botreply=null;
	try{
		botreply = JSON.parse(data);
	}catch (e){
		botreply=data;
	}
	//alert(botreply);
	//alert(no_of_records);
	//var size_of_reply=botreply.length;
	//var no_of_records=Math.min(5,size_of_reply);
		//var i=0;
		//while (i<no_of_records){
			pdf = botreply[i].report;
			//alert(pdf);
			if (pdf != null){
				var windo = window.open("", "","width=600,height=600");
				var objbuilder = '';
				 objbuilder += ('<embed width=\'100%\' height=\'100%\'  src="data:application/pdf;base64,');
				 objbuilder += (pdf);
				 objbuilder += ('" type="application/pdf" />');
				 windo.document.write(objbuilder);
			}
			
			//i++;
		//}		
	}

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
	else if (charCode == 13){
		submit();
	}
    return true;
}

 $(document).ready(function(){  
		$('#quesId').keyup(function(){  
           var query = $(this).val();
           //alert(query);
           //query = query.charAt(0).toUpperCase() + query.substr(1);
           if(query != '')  
           {
        	   //alert("in ajax");
                $.ajax({  
                     url:"/HISInvestigationG5/new_investigation/investigationChatBot.cnt?hmode=AJX_GET_QUES"+"&ques="+query, sync:true, postData: "", handleAs: "text",  
                     method:"POST",  
                     data:{query:query},               
                     success:function(data)  
                     {
                    	 //alert(data);
						  $('.msg_a').remove();
						  $('.msg_b').remove();
						  var botreply = JSON.parse(data);
						  var size_of_reply=botreply.length;
						  //alert(size_of_reply);
						  var i=0;
						  var arr = new Array(size_of_reply);
						  $("#quesList").empty()
						  $('#quesList').fadeIn();					
						  while (i<size_of_reply){								
								arr[i] = botreply[i].ques;
								//alert(arr[i]);
								//$('#quesList').html(arr[i]);
								$('#quesList').append('<li>'+arr[i]+'</li>');
								$('#quesList').append('<br>');
								i=i+1;
							}
						  //$('#quesList').html(arr);                          
                     },
                     error:function(error){
                    	 alert("ERROR: "+eval(error));
                     }
                });  
           }
			
      });  
      $(document).on('click', 'li', function(){  
           $('#quesId').val($(this).text());  
           $('#quesList').fadeOut();
		   $('#ansId').val( $('#quesId').val());
		   var asked = $('#ansId').val();
		   alert(asked);
		   $.ajax({
			   url:"/HISInvestigationG5/new_investigation/investigationChatBot.cnt?hmode=AJX_GET_ANS"+"&ans="+asked, sync:true, postData: "", handleAs: "text",
			   method:"POST",
			   data:{asked:asked},
			   success:function(data)
			   {
				   $('#quesId').val('');
				   //data=data.charAt(0).toUpperCase() + data.substr(1);
				   var botreply = JSON.parse(data);
				   //alert("in ans "+botreply);
				   var botHtml = '<div class="msg_a">' + botreply[0].ans + '</div>';
					$('<div class="msg_b">'+asked+'</div>').insertBefore('.msg_push');					
					$('<div class="msg_a">'+botHtml+'</div>').insertBefore('.msg_push');
					$('.msg_body').scrollTop($('.msg_body')[0].scrollHeight);
			   }
		   });
      });
 });  
 
$(document).ready(function() {

  var textbox = $('#quesId'),
    captionLength = 0,
    caption = '',
    id = setTimeout(TypingEffect, 600); //call once and set an Id so it can be cleared


  function TypingEffect() {
   // console.log('TypingEffect');
    var tag = Math.floor((Math.random() * 5) + 1);
	
    if (tag == 1) {
      caption = "how to get new password"
    }
    if (tag == 2) {
      caption = "how to raise investigation"
    }
    if (tag == 3) {
      caption = "can i reset the lost password"
    }
    if (tag == 4) {
      caption = "how to view reports of a patient"
    }
    if (tag == 5) {
      caption = "who is system admin"
    }
   
    

    clearTimeout(id); //clear first clearTimeout(TypingEffect, 600) call
    captionLength = 1; //start at 0
    id = setInterval(type, 50); //call type every 50ms
  }

  function type() {
 //   console.log('type', caption, caption.substring(0, captionLength++));
    textbox.attr("placeholder", caption.substring(0, captionLength++));

    //when finshed typing clear interval and start erasing
    if (captionLength === caption.length + 1) {
   //   console.log('end type');
      clearInterval(id); //clear clearInterval(type, 50) 
      id = setTimeout(ErasingEffect, 1000); //start ErasingEffect call once after delay
    }
  }

  function ErasingEffect() {
   // console.log('ErasingEffect');
    clearTimeout(id); //clear clearTimeout(ErasingEffect, 2000); call 
    captionLength = caption.length; //start at end
    id = setInterval(erase,0); //call erase every 50ms
  }

  function erase() {
   // console.log('erase');
    textbox.attr("placeholder", caption.substring(0, captionLength--));

    //when finshed erasing clear interval and call type
    if (captionLength < 0) {
   //   console.log('end erase');
      clearInterval(id); //clear clearInterval(erase, 50)
      id = setTimeout(TypingEffect, 1000); //start over
    }
  }

});

var li = $('li');
var liSelected;
$(window).keydown(function(e) {
    if(e.which === 40) {
        if(liSelected) {
            liSelected.removeClass('selected');
            next = liSelected.next();
            if(next.length > 0) {
                liSelected = next.addClass('selected');
            } else {
                liSelected = li.eq(0).addClass('selected');
            }
        } else {
            liSelected = li.eq(0).addClass('selected');
        }
    } else if(e.which === 38) {
        if(liSelected) {
            liSelected.removeClass('selected');
            next = liSelected.prev();
            if(next.length > 0) {
                liSelected = next.addClass('selected');
            } else {
                liSelected = li.last().addClass('selected');
            }
        } else {
            liSelected = li.last().addClass('selected');
        }
    }
});
  
</script> 