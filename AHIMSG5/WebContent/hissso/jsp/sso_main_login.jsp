
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<%@page import="hissso.config.HISSSOServerConfig"%>
<%@page import="hissso.config.HISSSOConfig"%>
<%@page import="hissso.config.HISSSOServerConfig"%>
<%@page import="hissso.config.HISSSOConfig"%>
<%@page import="hisglobal.config.HISConfig"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>

<title>Login To HIS</title>
<meta charset="UTF-8">
<link rel="shortcut icon" href="/HIS/hisglobal/images/hisico.ico">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/HIS/hisglobal/login/w3.css">
<link rel="stylesheet"
	href="/HIS/hisglobal/login/w3-theme-black.css">
<link rel="stylesheet"
	href="/HIS/hisglobal/login/fontawesome/css/all.css">


<link href="/HIS/hisglobal/css/bars.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href="/HIS/hisglobal/login/bootstrap/css/bootstrap.min.css"
	type="text/css" media="all" />
<link rel="stylesheet" href="/HIS/hisglobal/css/login/style.css"
	type="text/css" media="all" />
<!-- <link rel="stylesheet" href="/HIS/hisglobal/css/login/font-awesome.css" /> -->
 

<link href="/HIS/hisglobal/css/basic.css" rel="stylesheet"
	type="text/css" media="screen" />


<!-- 
<link rel='stylesheet' type='text/css' href='/HIS/hisglobal/css/default1.css'  media='screen' />
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/css/js-image-slider.css"  >
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/css/jquery.smartmarquee.css" > -->


<script type="text/javascript"
	src="/HIS/hisglobal/js/login/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="/HIS/hisglobal/login/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/hashFunctions.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/util.js"></script>
<script type="text/javascript" src="/AHIMSG5/hissso/js/ssoLogin.js"></script>
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery.simplemodal.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/crawler.js"></script>
<script
	src="/HIS/hisglobal/login/lightbox/ekko-lightbox.js"></script>
<%--  <script src="https://www.google.com/recaptcha/api.js" async defer></script> --%>

<script type="text/javascript" src="/HIS/hisglobal/masterutil/js/jquery/security.js"></script><!--Added by Vasu on 22.May.18 for removing http parameter pollution vulnerability -->

<style>
.glyphicon-hand-right{
	margin-right:5px;
}
.footer .paddingtop-bottom li::before {

    content: "*";
    color: #e9910d; 
    margin-right: 5px;
    font-size: 19px;

}
.highlight-block>div:hover {
	background: rgba(0, 0, 0, .8)
}

#bhamashah_enroll,#bhamashah_trans {
	color: #fff;
	padding: 8px 16px; /*background:rgba(0,0,0,.7);*/
}

#bhamashah_enroll:hover,#bhamashah_trans:hover {
	background-color: rgba(0, 0, 0, .8)
}

#bhamashah_enroll .digits,#bhamashah_trans .digits {
	font-weight: bold;
	font-family: 'digital';
	color: #1ac1f5;
	letter-spacing: 2px;
	font-size: 22px !important
}

@font-face {
	font-family: 'digital';
	src: url('/HIS/hisglobal/css/assets/css/digital.eot');
	src: url('/HIS/hisglobal/css/assets/css/digital.eot?#iefix')
		format('embedded-opentype'),
		url('/HIS/hisglobal/css/assets/css/digital.woff2') format('woff2'),
		url('core/fonts/digital.woff') format('woff'),
		url('core/fonts/digital.svg#digital') format('svg');
	font-weight: normal;
	font-style: normal
}


<!-- Added by vibhuti -->

.modal-content{
border-radius: 25px;
}
.sticky {
  position: fixed;
  top: 0;
  width: 100%;
}
#main_menu {
//height:18%;
background-color:white;
//background-color:transparent;
}
.number{
    width: 20%;
    float:left;
}
.text-logo{
    width: 80%;
    float:left;
    position:relative;
   }
@media screen and (max-width: 1265px) {
/* .head1{
    width: 44% !important;
    left:22%;
    float:none;
    padding:0px;
  //  height:10%;
    -ms-transform: translate(-50%, 0);
    transform: translate(-50%, 0);

}
#prod_logo_id{
left:40%;
} */

}
@media screen and (max-width: 1175px) {
/* .head1{
    width: 54% !important;
    left:30%;
    float:none;
    padding:0px;
  //  height:10%;
    -ms-transform: translate(-50%, 0);
    transform: translate(-50%, 0);

} */

}
@media screen and (max-width: 980px) {
  #main_menu {
    /* position:absolute; */
  }
   }
@media screen and (max-width: 976px) {
#myModal_content{
overflow: auto;
max-height:500px;
}
  /* .head1{
    left:50%;
    -ms-transform: translate(-50%, 0);
    transform: translate(-50%, 0);
  }
  .text-logo{
    left:50%;
    float:none;
    -ms-transform: translate(-50%, 0);
    transform: translate(-50%, 0);
  }
  .head2{
    width: device-width;
    left:50%;
    float:none;
    padding:0px;
    height:10%;
    -ms-transform: translate(-50%, 0);
    transform: translate(-50%, 0);
  
  }
  #prod_logo_id {
    width: device-width;
    left:50%;
    float:left;
    text-align:left;
    -ms-transform: translate(-50%, 0);
    transform: translate(-50%, 0);
    padding-right:30%;
  } */
 } 

/* ------------  Added by Vibhuti on 21 Mar 2018 for Image Modal --------------   */
#img-responsive2 {
    border-radius: 5px;
    cursor: pointer;
    transition: 0.3s;
    width:25%;
    height: 20%;
}

#img-responsive2:hover {opacity: 0.7;}

/* The Modal (background) */
.modal_img {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 5%; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    //background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.9); /* Black w/ opacity */
}

/* Modal Content (image) */
.modal-content_img {
    margin: auto;
    display: block;
    width: 80%;
    max-width: 700px;
}

/* Caption of Modal Image */
#caption_img {
    margin: auto;
    display: block;
    width: 80%;
    max-width: 700px;
    text-align: center;
    color: #ccc;
    padding: 10px 0;
    height: 150px;
}

/* Added Animation */
.modal-content_img, #caption_img {    
    -webkit-animation-name: zoom;
    -webkit-animation-duration: 0.9s;
    animation-name: zoom;
    animation-duration: 0.9s;
}

@-webkit-keyframes zoom {
    from {-webkit-transform:scale(0)} 
    to {-webkit-transform:scale(1)}
}

@keyframes zoom {
    from {transform:scale(0)} 
    to {transform:scale(1)}
}

/* The Close Button */
.close_img {
    position: absolute;
    top: 15px;
    right: 35px;
    color: #f1f1f1;
    font-size: 40px;
    font-weight: bold;
    transition: 0.3s;
}

.close_img:hover,
.close_img:focus {
    color: #bbb;
    text-decoration: none;
    cursor: pointer;
}

/* 100% Image Width on Smaller Screens */
@media only screen and (max-width: 700px){
    .modal-content_img {
        width: 100%;
    }
}

span{
color: black !important;
}
  .affix {
      top: 0;
      width: 100%;
      z-index: 9999 !important;
  }

  .affix + .container-fluid {
      padding-top: 70px;
  }
  .navbar-header {

    background: #0861b700 !important;

}
.navbar-inverse .navbar-nav li:hover a{
	background-color: #FFB900;
}
.navbar-inverse .navbar-nav li a{
	color: black;
	font-size: 18px;
}

.navbar-inverse .navbar-nav li a:visited{
	color: black !important; 
}
</style>




<!--<script language="JavaScript" src="../../hisglobal/js/jquery-1.10.1.min.js"></script>
<script src="/HIS/hisglobal/js/js-image-slider.js"></script>
<script src="/HIS/hisglobal/js/jquery.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery.simplemodal.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/hashFunctions.js"></script>
<script type="text/javascript" src="/IMCS/hissso/js/ssoLogin.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/util.js"></script>-->

<script>
function refershMain()
{
	 //alert('a');
	//window.location = window.location.href;
	//window.location.reload(true);
	 //this.location.reload(); 
	window.location.href = window.location.pathname; 
}

var IS_CAPTCHA_REQ = "<%=HISConfig.CAPTCHA_IMPLEMENTATION%>";


var sessionToken = "<%=session.getAttribute(HISSSOServerConfig.LOGIN_SESSION_SALT)%>";
	$(function() {
		// 		  $(document).ready(function() { 
		$("#idForgotPassword")
				.click(
						function() {
							openURLInPopupWithoutClose(
									//"/AHIMSG5/hislogin/initForgetPasswordLgnFtr.action",
									"/AHIMSG5/hislogin/initForgetPasswordLgnFtr",
									"600", "300");
							//window.parent.location = window.parent.location.href;
						});
		// 		  });
		});
</script>
<script>
var IS_CAPTCHA_REQ = "<%=HISConfig.CAPTCHA_IMPLEMENTATION%>";
var sessionToken = "<%=session.getAttribute(HISSSOServerConfig.LOGIN_SESSION_SALT)%>";
 	  $(function() {
// 		  $(document).ready(function() { 
			$("#idForgotPassword").click(function(){
				//openURLInPopup("/AHIMSG5/hislogin/initForgetPasswordLgnFtr.action","600","300");				
				openURLInPopupWithoutClose("/AHIMSG5/hislogin/initForgetPasswordLgnFtr","600","300");				
					});
// 		  });
		});
</script>
 <script>
              marqueeInit({
                    uniqueid: 'mycrawler2',
                    style: {
                         
                    },
                    inc: 5, //speed - pixel increment for each iteration of this marquee's movement
                    mouse: 'cursor driven', //mouseover behavior ('pause' 'cursor driven' or false)
                    moveatleast: 2,
                    neutral: 150,
                    savedirection: true,
                    random: true
                });


function getfiledownload(temp){
	//alert(temp.id);
//	var url="/AHIMSG5/hissso/fileDownloadLogin.action?filename="+temp.id;
	var url="/AHIMSG5/hissso/fileDownloadLogin?filename="+temp.id;
	 var mode = "fileDownload"; 
		$.ajax({
			  //url: "/AHIMSG5/hissso/fileDownloadLogin.action?filename="+temp.id,
			  url: "/AHIMSG5/hissso/fileDownloadLogin?filename="+temp.id,
			  success: function(response, status, request) {
			        var disp = request.getResponseHeader('Content-Disposition');
			        if (disp && disp.search('attachment') != -1) {
			            var form = $('<form method="POST" action="' + url + '">');
			            $.each(function(k, v) {
			                form.append($('<input type="hidden" name="' + k +
			                        '" value="' + v + '">'));
			            });
			            $('body').append(form);
			          // $('#downloadinddiv').css({'display':'none'});
			         //  $(".overlay").remove();
			           form.submit();
			        }
			  }
			});
	}
 //Added by Vasu on 22.March.2018 for Number validation in captcha textbox
 function isNumber(evt) {
    evt = (evt) ? evt : window.event;
       var charCode = (evt.which) ? evt.which : evt.keyCode;
         if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
       }
    return true;
}
function refreshTxtBox(){
	document.getElementById("captchaTxtBox").value = "";
}
        </script>
<!-- fonts -->
<link
	href="/HIS/hisglobal/login/fonts/font1.css"
	rel="stylesheet">
	
<link
	href="/HIS/hisglobal/login/fonts/font2.css"
	rel="stylesheet">
<!-- //fonts -->
<!-- scrolling script -->
</head>
<body onload="document.getElementsByName('varUserName')[0].focus();">
<s:form action="Login"  method="post" id="loginForm">
	<div class="top-header" id="home">
		<div class="container-fluid">
			<div class="col-md-8 col-sm-8 col-xs-12 top-left">
				<%-- <h1>
					<i class="fa fa-phone" aria-hidden="true"></i>Office Of The Principle:
					<blink>
						<strong> +91-0671-2414355</strong>
					</blink>
				</h1> --%>
			</div>
			<!-- <div class="col-md-4 col-sm-4 col-xs-4 top-right">
					<a href="#" data-toggle="modal" data-target="#myModalo">Login</a>
			
				</div> -->
			
			<div class="clearfix"></div>
		</div>
	</div>
	
	
	
	<nav class="navbar navbar-inverse" style="background-color: #fff; border-color: #fff;"> <!-- data-spy="affix" data-offset-top="100" -->
	  <div class="navbar-container container">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar" style="background-color: #0e0e0e;">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>                        
	      </button>
	      <a class="navbar-brand" href="/AHIMSG5/hissso/Login" style="height: auto;padding: 5px 15px;"><img class="img-responsive" src="/HIS/hisglobal/images/aiimsp_logo.PNG"></a>
	    </div>
	    <div class="collapse navbar-collapse" id="myNavbar" style="margin-top:25px">
	      <ul class="nav navbar-nav navbar-right">
			<li><a href="#">HOME</a></li>
			<li><a href="#about" >ABOUT</a></li>
			<li><a href="#services" >FEATURES</a></li>
			<li><a href="#payment" >LINK</a></li>
			<li><a href="#blog" >GALLERY</a></li>
			<li><a href="#contact" >CONTACT</a></li>
	      </ul> 
	    </div>
	  </div>
	</nav>
	
	<script>
		 $(window).on("load, resize", function() {
		    var viewportWidth = $(window).width();
		    if (viewportWidth < 1200) {
		            $(".navbar-inverse .navbar-nav").removeClass("navbar-right");
		    }
		    else
			{
	            $(".navbar-inverse .navbar-nav").addClass("navbar-right");
			} 
			 if (viewportWidth < 2300) { 
		         $(".navbar-container").removeClass("container").addClass("container-fluid");
			   }
		    else
			  { 
		        $(".navbar-container").removeClass("container-fluid").addClass("container");
			  }
		});

		 $(document).ready(function() {
			    var viewportWidth = $(window).width();
			    if (viewportWidth < 1200) {
			            $(".navbar-inverse .navbar-nav").removeClass("navbar-right");
			    }
			    else
				{
		            $(".navbar-inverse .navbar-nav").addClass("navbar-right");
				} 
				 if (viewportWidth < 2300) { 
			         $(".navbar-container").removeClass("container").addClass("container-fluid");
				   }
			    else
				  { 
			        $(".navbar-container").removeClass("container-fluid").addClass("container");
				  }
		 });
			  
	</script>
	
<%-- 	<nav id="main_menu" class="navbar navbar-default"> <!-- Addded by Vibhuti on 20 March -->
   
	<div class="top-main1 row" id="top-main">
		<div class="col-xs-12 col-sm-5 head1" style="margin-right:0px;"> 
			<!-- <div class="wrap-lo">
				<div class="number">
					<a href="#"><img class="img-responsive" src="/HIS/hisglobal/images/AIIMS_Logo.jpg" alt=""></a>
				</div>
				<div class="text-logo">
					<h1>All India Institute of Medical Sciences Patna</h1>
					<h4>अखिल भारतीय आयुर्विज्ञान संस्थान पटना</h4>
				</div>
			</div> -->
		</div>
		<div class="col-sm-7 col-xs-12 head2">
			<div class="headrg">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#myNavbar">
						<span class="sr-only">Toggle navigation</span> 
						<span class="icon-bar"></span> 
						<span class="icon-bar"></span> 
						<span class="icon-bar"></span>
					</button>
				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav navbar-left">
						<li class="active"><a href="#" class="scroll">home</a></li>
						<li><a href="#about" class="scroll">about</a></li>
						<li><a href="#services" class="scroll">Features</a></li>
						<li><a href="#payment" class="scroll">link</a></li>
						<li><a href="#blog" class="scroll">Gallery</a></li>
						<li><a href="#contact" class="scroll">contact</a></li>
						</ul>
				</div>
			</div>
			
		</div>
		<!-- <div class="col-md-2 col-lg-2 col-xs-12 float-right" id="prod_logo_id">
			<div class="social-icons">
				<ul class="top-icons">

					<li><a href="#"><div class="img-box-content">
								<img class="img-responsive" src="/HIS/hisglobal/images/login/t1.png" alt=" " width="90" height="80px">
							</div></a></li>
				</ul>
			</div>
		</div> -->
		<div class="clearfix"></div>
	</div>
</nav> --%>


	<!-- Top-Bar -->
	<%-- <div class="top-bar">
			<nav class="navbar navbar-default">
				<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#myNavbar">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
					</div>
					<div class="collapse navbar-collapse" id="myNavbar">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="#top-main" class="scroll">home</a></li>
							<li><a href="#about" class="scroll">about</a></li>
							<li><a href="#services" class="scroll">Features</a></li>
							<!--<li><a href="#skills" class="scroll">skills</a></li>-->
						<!-- 	<li><a href="#team" class="scroll">team</a></li> -->
							<li><a href="#payment" class="scroll">link</a></li>
							<li><a href="#blog" class="scroll">Gallery</a></li>
							<li><a href="#contact" class="scroll">contact</a></li>
						</ul>
						
					</div>
				</div>
			</nav>
		</div> --%>
	<!--<div class="logo">
			<a href="index.html">-->
	<!--<i class="fa fa-inr" aria-hidden="true"></i>-->
	<!--Corporate <span>bank</span></a>
		</div>-->
		
<!-- code by vibhuti for login hide-show effect -->
<script>

	$(window).scroll(function(){
		if(window.innerWidth>=680)
		{
		   if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
			$('.loginform').hide('slow');
    	   } else {
    		$('.loginform').show('slow');
   		   }
	    }
});
</script>		
		
	<!-- code by chandni -->
	<div class="cover-banner">
		<div class="loginform" align="center">
			
				<h3 align="center">LOGIN</h3>

				<input type="text" tabindex="1" class="loginTextBox"
					style="border-radius: 7px;" placeholder="Enter User Name" value=""
					name="varUserName" maxlength="20"
					onkeypress="return validateAlphaNumWithUnderscoreOnly(this,event);"
					autocomplete="off"><br> <input type="password"
					tabindex="1" class="loginTextBox" style="border-radius: 7px;"
					placeholder="Enter Password" value="" name="varPassword"
					maxlength="15" autocomplete="off"><br>


				<%
					if (HISConfig.CAPTCHA_IMPLEMENTATION.equals("ON")) {
				%>
				<%System.out.println("Captcha Implementation is ON"); %>
				  <img id="captchaImg" style="margin:2% 0 4% 20%;"
					src="/AHIMSG5/hissso/captchaLogin" style="width: 30%"
					alt="Captcha Image" height="30"> 
					<input type="text" tabindex="1" class="captcha-Text"id="captchaTxtBox";
					style="width: 17%; border-radius: 5px;" maxlength="3"
					name="captchaResponse" placeholder="Enter"
					autocomplete="off" onkeypress="return isNumber(event)"/>
					<img
					src="/HIS/hisglobal/images/buttons/refresh.png" alt="Reload"
					onclick="document.forms[0].captchaImg.src='/AHIMSG5/hissso/captchaLogin'+'?id='+Math.random();refreshTxtBox();"
					style="cursor: pointer" /> 
					<!-- <div class="g-recaptcha" data-sitekey="6LeIAD0UAAAAAEdZKpUCG-orYLHC18g1ZKAMfXn8"> </div> -->

				<%
					}
				%>
				<%
					if (HISConfig.CAPTCHA_IMPLEMENTATION.equals("OFF")) {
				%>
				<br> <br>
				
				
				<%
					}
				%>

				<%-- <select name="varLanguage" style="display:none;"> <option value="english">English</option> </select> --%>
				<!--<input type="text" placeholder="Location" required=""/>-->
				
				
				<div class="submit">
					<input type="submit" tabindex="1" value="Login"
						class="login-button"
						onclick="submitFormOnValidate(validate(),'loginLogin')"> <br>
					<!-- <input type="submit" value="login" onclick="submitFormOnValidate(validate(),'loginLogin')"> -->
				</div>
				<a href="#" class="forgetPwd" tabindex="1" id="idForgotPassword"
					style="color: #ffff;" title="Forgot Password?"> Forgot Password?</a>
				<div id="divElementErrorsId" class="alertMessage">
					<s:actionerror />
				</div>
				<%-- 							<div id="divElementErrorsId" class="alertMessage" style="color:red;font-weight:bold;float: left;margin-left:10%;"><s:actionerror/></div>
							<div id="mahlogin" style="margin-top: 8px;margin-left: 10px;float:left;">
										<%
											String status = (String) request.getAttribute("message");
											if (status != null && !status.equals(" ")) {
												out.println("<font color='Red'><strong>" + status
														+ "</strong></font>");
											}
										%>
									</div> --%>
		
		</div> 
			<div class="carousel-container container-fluid">
				<div id="carousel" class="carousel slide carousel-fade"
			data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#carousel" data-slide-to="0" class="active"></li>
				<li data-target="#carousel" data-slide-to="1"></li>
				<li data-target="#carousel" data-slide-to="2"></li>
				<li data-target="#carousel" data-slide-to="3"></li>
			</ol>
			<!-- Carousel items -->
			<div class="carousel-inner carousel-zoom">
				<div class="active item">
					<img class="img-responsive"
						src="/HIS/hisglobal/images/login/bg1.png">
					<div class="carousel-caption banner-right">

						<h1 style="color: white; margin:10px 0 0 0 !important;" align="">
							STATISTICS <a id="" title="Refresh Data" href="#"
								onclick="submitForm('Login')"><i
								class="glyphicon glyphicon-refresh digits pull-right"
								style="font-size: 20px; margin-right: 4px;" aria-hidden="true"></i></a>
						</h1>

						<div class="col-md-12 zero" id="bhamashah_enroll" style="padding-top:0px; padding-bottom:5px;">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 14px;"
									align="center">OPD</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("OPD_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("OPD_Count_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll" style="padding-top:0px; padding-bottom:5px;">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 14px;"
									align="center">Emergency</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("EMG_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class=" digits pull-right"> <%=request.getSession().getAttribute("EMG_COUNT_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll" style="padding-top:0px; padding-bottom:5px;">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 14px;"
									align="center">IPD</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("IPD_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("IPD_COUNT_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>
						
						<div class="col-md-12 zero" id="bhamashah_enroll" style="padding-top:0px; padding-bottom:5px;">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 14px;"
									align="center">Lab Test</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("INV_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("INV_COUNT_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll" style="padding-top:0px; padding-bottom:5px;">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 14px;"
									align="center">Online Registration</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("MOBILE_REG_DATA_TOTAL").toString() %></span>App Based Registration:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class=" digits pull-right"> <%=Integer.parseInt(request.getSession().getAttribute("ONLINE_REGISTRATION_TOTAL").toString()) %></span>Online Provisional
									Registration:
								</h5>
							</div>
						</div>
						
						

					</div>
				</div>
				<div class="item">
					<img class="img-responsive"
						src="/HIS/hisglobal/images/login/bg2.png">
					<div class="carousel-caption banner-right">

							<h1 style="color: white; margin:10px 0 0 0 !important;" align="">
							STATISTICS <a id="" title="Refresh Data" href="#"
								onclick="submitForm('Login')"><i
								class="glyphicon glyphicon-refresh digits pull-right"
								style="font-size: 20px; margin-right: 4px;" aria-hidden="true"></i></a>
						</h1>

						<div class="col-md-12 zero" id="bhamashah_enroll" style="padding-top:0px; padding-bottom:5px;">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 14px;"
									align="center">OPD</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("OPD_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("OPD_Count_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll" style="padding-top:0px; padding-bottom:5px;">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 14px;"
									align="center">Emergency</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("EMG_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class=" digits pull-right"> <%=request.getSession().getAttribute("EMG_COUNT_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>
						
						<div class="col-md-12 zero" id="bhamashah_enroll" style="padding-top:0px; padding-bottom:5px;">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 14px;"
									align="center">IPD</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("IPD_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("IPD_COUNT_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll" style="padding-top:0px; padding-bottom:5px;">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 14px;"
									align="center">Lab Test</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("INV_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("INV_COUNT_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll" style="padding-top:0px; padding-bottom:5px;">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 14px;"
									align="center">Online Registration</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("MOBILE_REG_DATA_TOTAL").toString() %></span>App Based Registration:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class=" digits pull-right"> <%=Integer.parseInt(request.getSession().getAttribute("ONLINE_REGISTRATION_TOTAL").toString()) %></span>Online Provisional
									Registration:
								</h5>
							</div>
						</div>
						
						
					</div>
				</div>
				<div class="item">
					<img class="img-responsive" src="/HIS/hisglobal/images/login/bg3.png">
					<div class="carousel-caption banner-right">

							<h1 style="color: white; margin:10px 0 0 0 !important;" align="">
							STATISTICS <a id="" title="Refresh Data" href="#"
								onclick="submitForm('Login')"><i
								class="glyphicon glyphicon-refresh digits pull-right"
								style="font-size: 20px; margin-right: 4px;" aria-hidden="true"></i></a>
						</h1>

						<div class="col-md-12 zero" id="bhamashah_enroll" style="padding-top:0px; padding-bottom:5px;">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 14px;"
									align="center">OPD</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("OPD_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("OPD_Count_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll" style="padding-top:0px; padding-bottom:5px;">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 14px;"
									align="center">Emergency</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("EMG_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class=" digits pull-right"> <%=request.getSession().getAttribute("EMG_COUNT_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>
						
						<div class="col-md-12 zero" id="bhamashah_enroll" style="padding-top:0px; padding-bottom:5px;">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 14px;"
									align="center">IPD</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("IPD_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("IPD_COUNT_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll" style="padding-top:0px; padding-bottom:5px;">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 14px;"
									align="center">Lab Test</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("INV_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("INV_COUNT_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll" style="padding-top:0px; padding-bottom:5px;">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 14px;"
									align="center">Online Registration</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("MOBILE_REG_DATA_TOTAL").toString() %></span>App Based Registration:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class=" digits pull-right"> <%=Integer.parseInt(request.getSession().getAttribute("ONLINE_REGISTRATION_TOTAL").toString()) %></span>Online Provisional
									Registration:
								</h5>
							</div>
						</div>
												
					</div>
				</div>
				<div class="item">
					<img class="img-responsive"
						src="/HIS/hisglobal/images/login/bg4.png">
					<div class="carousel-caption banner-right">

							<h1 style="color: white; margin:10px 0 0 0 !important;" align="">
							STATISTICS <a id="" title="Refresh Data" href="#"
								onclick="submitForm('Login')"><i
								class="glyphicon glyphicon-refresh digits pull-right"
								style="font-size: 20px; margin-right: 4px;" aria-hidden="true"></i></a>
						</h1>

						<div class="col-md-12 zero" id="bhamashah_enroll" style="padding-top:0px; padding-bottom:5px;">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 14px;"
									align="center">OPD</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("OPD_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("OPD_Count_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll" style="padding-top:0px; padding-bottom:5px;">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 14px;"
									align="center">Emergency</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("EMG_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class=" digits pull-right"> <%=request.getSession().getAttribute("EMG_COUNT_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>
						
						<div class="col-md-12 zero" id="bhamashah_enroll" style="padding-top:0px; padding-bottom:5px;">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 14px;"
									align="center">IPD</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("IPD_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("IPD_COUNT_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll" style="padding-top:0px; padding-bottom:5px;">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 14px;"
									align="center">Lab Test</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("INV_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("INV_COUNT_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll" style="padding-top:0px; padding-bottom:5px;">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 14px;"
									align="center">Online Registration</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("MOBILE_REG_DATA_TOTAL").toString() %></span>App Based Registration:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i><span
										class=" digits pull-right"> <%=Integer.parseInt(request.getSession().getAttribute("ONLINE_REGISTRATION_TOTAL").toString()) %></span>Online Provisional
									Registration:
								</h5>
							</div>
						</div>
						
						
					</div>
				</div>
			</div>
			<!-- Carousel nav -->
			<a class="carousel-control left" href="#carousel" data-slide="prev">‹</a>
			<a class="carousel-control right" href="#carousel" data-slide="next">›</a>
		</div> 
		</div>
		

	</div>
	<script>
	 $(document).ready(function() {
		    var viewportWidth = $(window).width();
			 if (viewportWidth < 2300) { 
		         $(".cover-banner .carousel-container").removeClass("container").addClass("container-fluid");
		         $(".cover-banner .carousel-container").css('padding','0px');
		           $('.loginform').css({
						"top":"35%",
						"right":"0%"
			         }); 
			   }
		    else
			  { 
		        $(".cover-banner .carousel-container").removeClass("container-fluid").addClass("container");
		         $(".cover-banner .carousel-container").css('padding','');
		           $('.loginform').css({
						"top":"15%",
						"right":"38%"
			         });
			  }
	 });
	 $(window).on('resize',function() {
		    var viewportWidth = $(window).width();
			 if (viewportWidth < 2300) { 
		         $(".cover-banner .carousel-container").removeClass("container").addClass("container-fluid");
		         $(".cover-banner .carousel-container").css('padding','0px');
		           $('.loginform').css({
						"top":"35%",
						"right":"0%"
			         });  
			   }
		    else
			  { 
		        $(".cover-banner .carousel-container").removeClass("container-fluid").addClass("container");
		         $(".cover-banner .carousel-container").css('padding','');
		           $('.loginform').css({
						"top":"15%",
						"right":"38%"
			         });  
			  }
	 });
	</script>
	
	<!-- //Top-Bar -->


	<%-- <div class="banner-main jarallax">
			<div class="container">
				<div class="banner-inner">
					<div class="col-md-5 banner-left">
						<form action="#" method="post">
						<h3 align="center">LOGIN</h3>
							
							<input type="text" tabindex="1" class="loginTextBox" style="border-radius: 7px;" placeholder="Enter User Name"  value="" name="varUserName" maxlength="20" onkeypress="return validateAlphaNumWithUnderscoreOnly(this,event);" autocomplete="off"><br>
							<input type="password" tabindex="1" class="loginTextBox" style="border-radius: 7px;" placeholder="Enter Password"  value="" name="varPassword" maxlength="10"  autocomplete="off"><br>
							
							
							<%if(HISConfig.CAPTCHA_IMPLEMENTATION.equals("ON")) {%>
							<input type="text" tabindex="1" class="captcha-Text" style="width:34%;border-radius: 7px;"  maxlength="6" name="captchaResponse" placeholder="Enter Captcha" autocomplete="off"/>
							<img id="captchaImg" src="/AHIMSG5/hissso/captchaLogin.action" style="width:30%" alt="Captcha Image" height="30">
							<img src="/HIS/hisglobal/images/buttons/refresh.png" alt="Reload"  onclick="document.forms[0].captchaImg.src='/AHIMSG5/hissso/captchaLogin.action'+'?id='+Math.random();" style="cursor:pointer"/> 
							
							<%}%>
							<%if(HISConfig.CAPTCHA_IMPLEMENTATION.equals("OFF")) {%>
							<br>
							<br>
							<%}%>
							
							<select name="varLanguage" style="display:none;"> <option value="english">English</option> </select>
							<!--<input type="text" placeholder="Location" required=""/>-->
							<br>
							<br/>
							<div class="submit">
							<input type="submit" tabindex="1" value="Login" class="login-button" onclick="submitFormOnValidate(validate(),'loginLogin')"> <br>
								<!-- <input type="submit" value="login" onclick="submitFormOnValidate(validate(),'loginLogin')"> -->
							</div>
							<a href="#" class="forgetPwd" tabindex="1" id="idForgotPassword" style="color: black" title="Forgot Password?"> Forgot Password?</a>
							<div id="divElementErrorsId" class="alertMessage"><s:actionerror/></div>
							<div id="divElementErrorsId" class="alertMessage" style="color:red;font-weight:bold;float: left;margin-left:10%;"><s:actionerror/></div>
							<div id="mahlogin" style="margin-top: 8px;margin-left: 10px;float:left;">
										<%
											String status = (String) request.getAttribute("message");
											if (status != null && !status.equals(" ")) {
												out.println("<font color='Red'><strong>" + status
														+ "</strong></font>");
											}
										%>
									</div>
						</form>
					</div>
					<div class="col-md-7 banner-right" >
						<h1 align="" style="color: white">STATISTICS <a id="" title="Refresh Data" href="#" onclick="submitForm('loginLogin')"><i class="fa fa-refresh digits pull-right" style="font-size: 20px;margin-right: 4px;" aria-hidden="true" ></i></a></h1>
						
							 <div class="col-md-12 zero" id="bhamashah_enroll">
							<div class="">
							  <h5 class="fontclass" style="color: #f5a11a; font-weight: bold;font-size: 16px;" align="center">OPD</h5>
							  <h5 class="fontclass" style="font-size: 16px;"><i class="fa fa-hand-o-right" aria-hidden="true"></i><span class="digits pull-right"><%=request.getAttribute("OPD_COUNT").toString() %></span>Today:</h5>
							  <h5 class="fontclass" style="font-size: 16px;"><i class="fa fa-hand-o-right" aria-hidden="true"></i><span class=" digits pull-right"><%=request.getAttribute("OPD_COUNT_TOTAL").toString() %></span>Total:</h5>
							</div>
						  </div>
						  
						   <div class="col-md-12 zero" id="bhamashah_enroll">
							<div class="">
							  <h5 class="fontclass" style="color: #f5a11a; font-weight: bold;font-size: 16px;" align="center">IPD</h5>
							  <h5 class="fontclass" style="font-size: 16px;"><i class="fa fa-hand-o-right" aria-hidden="true"></i><span class="digits pull-right"><%=request.getAttribute("IPD_COUNT").toString() %></span>Today:</h5>
							  <h5 class="fontclass" style="font-size: 16px;"><i class="fa fa-hand-o-right" aria-hidden="true"></i><span class=" digits pull-right"><%=request.getAttribute("IPD_COUNT_TOTAL").toString() %></span>Total:</h5>
							</div>
						  </div>
						  
						   <div class="col-md-12 zero" id="bhamashah_enroll">
							<div class="">
							  <h5 class="fontclass" style="color: #f5a11a; font-weight: bold;font-size: 16px;" align="center">Lab Test</h5>
							  <h5 class="fontclass" style="font-size: 16px;"><i class="fa fa-hand-o-right" aria-hidden="true"></i><span class="digits pull-right"><%=request.getAttribute("INV_COUNT").toString() %></span>Today:</h5>
							  <h5 class="fontclass" style="font-size: 16px;"><i class="fa fa-hand-o-right" aria-hidden="true"></i><span class=" digits pull-right"><%=request.getAttribute("INV_COUNT_TOTAL").toString() %></span>Total:</h5>
							</div>
						  </div>
						  
						   <div class="col-md-12 zero" id="bhamashah_enroll">
							<div class="">
							  <h5 class="fontclass" style="color: #f5a11a; font-weight: bold;font-size: 16px;" align="center">Online Registration</h5>
							  <h5 class="fontclass" style="font-size: 16px;"><i class="fa fa-hand-o-right" aria-hidden="true"></i><span class="digits pull-right"><%=request.getAttribute("MOBILE_REG_DATA_TOTAL").toString() %></span>App Based Registration:</h5>
							  <h5 class="fontclass" style="font-size: 16px;"><i class="fa fa-hand-o-right" aria-hidden="true"></i><span class=" digits pull-right"><%=Integer.parseInt(request.getAttribute("ONLINE_REGISTRATION_TOTAL").toString()) %></span>Online Provisional Registration:</h5>
							</div>
						  </div>
						</div>
						
					
					
					
					
					<div class="clearfix"></div>
					 <div class="col-md-1 banner-right1" style="margin-top: 10px" >
					 <h2 align="center" style="color: white;font-family: 'Ropa Sans', sans-serif;">CIRCULAR</h2>
					<marquee direction="up" scrollamount="1"  onmouseover="this.setAttribute('scrollamount', 0, 0);" onmouseout="this.setAttribute('scrollamount', 3, 0);">
					<%
					ArrayList al= (ArrayList)request.getSession().getAttribute("circulardata");
					Iterator favIterator= al.iterator();
					String target="";
					while(favIterator.hasNext())
					{
						String	Menu1=(String) favIterator.next();
						if(Menu1.split("#")[5].replace("@", "#").equalsIgnoreCase("#"))
						{
							target="_self";
						}else
						{
							target="_blank";
						}
						%><i class="fa fa-hand-o-right" aria-hidden="true" style="margin-left: 20px;"></i><a href=<%=Menu1.split("#")[5].replace("@", "#")%> target=<%=target%> style="font-weight: bold;color: white;margin-left: 10px"><%=Menu1.split("#")[1]+"("+Menu1.split("#")[2]+")"%></a>
						<a href="#" onclick="getfiledownload(this)" id="<%=Menu1.split("#")[3]%>" style="font-weight: bold;color: #1ac1f5;margin-left: 10px">Click Here For Download</a>
						<br><%
					}
					
					%>
					<!-- <a href="#" style="font-weight: bold;color: red">just for check</a><br> -->
					
					
						  </marquee>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div> --%>
	<!-- -code for marqee by Ashutosh Pandey -->
	<%--<div class="banner-bottom">
		<div class="container">
			<div class="row">
				<div class="col-md-2 col-lg-2 ">
					<div class="wrap-marquee">
						<h3>CIRCULAR</h3>
					</div>
				</div>
				<div class="col-md-10 col-lg-10 ">
					<div class="wrap1-mar">
						<marquee direction="UP" scrollamount="1"
							onmouseover="this.setAttribute('scrollamount', 0, 0);"
							onmouseout="this.setAttribute('scrollamount', 3, 0);">
							<%
								ArrayList al = (ArrayList) request.getSession().getAttribute(
										"circulardata");
								Iterator favIterator = al.iterator();
								String target = "";
								while (favIterator.hasNext()) {
									String Menu1 = (String) favIterator.next();
									if (Menu1.split("#")[5].replace("@", "#").equalsIgnoreCase("#")) {
										target = "_self";
									} else {
										target = "_blank";
									}
							%><i class="fa fa-hand-o-right" aria-hidden="true"
								style="margin-left: 20px;"></i><a
								href=<%=Menu1.split("#")[5].replace("@", "#")%>
								target=<%=target%>
								style="font-weight: normal; color: white; margin-left: 10px"><%=Menu1.split("#")[1] + "(" + Menu1.split("#")[2] + ")"%></a>
							<a href="#" onclick="getfiledownload(this)"
								id="<%=Menu1.split("#")[3]%>"
								style="font-weight: bold; color: orange; margin-left: 10px">Click
								Here For Download</a> <br>
							<%
								}
							%>
							<!-- <a href="#" style="font-weight: bold;color: red">just for check</a><br> -->


						</marquee>
					</div>
				</div>
			</div>
		</div>
	</div>
 --%>
	<!--team-->
	<%-- <div class="team" id="team">
	<div class="container">
		<!-- <h4 class="title-w3">Team</h4> -->
		<div class="team-grids">
		<div class="col-md-3 team-gds wow fadeInRight animated four" data-wow-delay=".5s">
			<div class="gal-grid-rev vertical">
				<div class="img-box-content"></div>
					<div class="gal-text-box">
						<div class="info-gal-con">
							<div class="social-content">
								<ul>
									<li class="facebook"><a class="ico1 hvr-underline-from-center" href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
									<li class="twitter"><a class="ico2 hvr-underline-from-center" href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
									<li class="pinterest"><a class="ico3 hvr-underline-from-center" href="#"><i class="fa fa-pinterest-p" aria-hidden="true"></i></a></li>
									<li class="linkedin"><a class="ico4 hvr-underline-from-center" href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a></li>
								</ul>			
							</div>
						</div>
					</div>
			</div>
			<h4></h4>
			<p></p>
		</div>
		<div class="col-md-3 team-gds wow fadeInLeft animated" data-wow-delay=".5s">
			<div class="gal-grid-rev vertical">
				<div class="img-box-content"><img src="/HIS/hisglobal/images/login/t1.jpg" alt=" " /></div>
					<div class="gal-text-box">
						<div class="info-gal-con">
							<div class="social-content">
								<ul>
									<li class="facebook"><a class=" hvr-underline-from-center" href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
									<li class="twitter"><a class="ico2 hvr-underline-from-center" href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
									<li class="pinterest"><a class="ico3 hvr-underline-from-center" href="#"><i class="fa fa-pinterest-p" aria-hidden="true"></i></a></li>
									<li class="linkedin"><a class="ico4 hvr-underline-from-center" href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a></li>
								</ul>			
							</div>
						</div>
					</div>
			</div>
			<h4>Adityanath Yogi</h4>
			<p>Chief Minister of Uttar Pradesh</p>
		</div>
		<div class="col-md-3 team-gds wow fadeInUp animated" data-wow-delay=".5s">
			<div class="gal-grid-rev vertical">
				<div class="img-box-content"><img src="/HIS/hisglobal/images/login/t2.jpeg" alt=" " /></div>
					<div class="gal-text-box">
						<div class="info-gal-con">
							<div class="social-content">
								<ul>
									<li class="facebook"><a class="ico1 hvr-underline-from-center" href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
									<li class="twitter"><a class="ico2 hvr-underline-from-center" href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
									<li class="pinterest"><a class="ico3 hvr-underline-from-center" href="#"><i class="fa fa-pinterest-p" aria-hidden="true"></i></a></li>
									<li class="linkedin"><a class="ico4 hvr-underline-from-center" href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a></li>
								</ul>			
							</div>
						</div>
					</div>
			</div>
			<h4>Siddharth Nath Singh</h4>
			<p>Health Minister of Uttar Pradesh </p>
		</div>
		<div class="col-md-3 team-gds wow fadeInDown animated three" data-wow-delay=".5s">
			<div class="gal-grid-rev vertical">
				<div class="img-box-content"><img src="" alt=" " /></div>
					<div class="gal-text-box">
						<div class="info-gal-con">
							<div class="social-content">
								<ul>
									<li class="facebook"><a class="ico1 hvr-underline-from-center" href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
									<li class="twitter"><a class="ico2 hvr-underline-from-center" href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
									<li class="pinterest"><a class="ico3 hvr-underline-from-center" href="#"><i class="fa fa-pinterest-p" aria-hidden="true"></i></a></li>
									<li class="linkedin"><a class="ico4 hvr-underline-from-center" href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a></li>
								</ul>			
							</div>
						</div>
					</div>
			</div>
			<h4></h4>
			<p></p>
		</div>
		
		<div class="clearfix"></div>
	</div>
	</div>
</div>
<!--//team-->
<section class="skills">
	<div class="container" style='text-align: center;'>
		<img src="/HIS/hisglobal/images/login/e-Aushadhi.png" alt=" " />
	</div>
</section>	 --%>
	<!-- about -->
	<section class="about" id="about">
	<div class="container">
		<div class="spec ">
			<h3 class="section-title">About Us</h3>
			<div class="ser-t">
				<b></b> <span><i></i></span> <b class="line"></b>
			</div>
		</div>
		<div class="about-grids">
			<div class="col-md-6 about-left">
				<img src="/HIS/hisglobal/images/login/1.png" alt="" />
			</div>
			<div class="col-md-6 about-right">
				<!-- <h3>Supply of Quality Medicine Transparent Drug Procurement Procedure Upto Date Medicine Inventory.</h3> -->
				<p class="text-justify" style="color: #111010;">e-Sushrut C-DAC’s Hospital Management
					Information System is a major step towards adapting technology to
					improve healthcare. HMIS incorporates an integrated computerized
					clinical information system for improved hospital administration
					and patient health care. It also provides an accurate,
					electronically stored medical record of the patient. A data
					warehouse of such records can be utilized for statistical
					requirements and for research. The real time HMIS streamlines the
					treatment flow of patients and simultaneously empowering workforce
					to perform to their peak ability, in an optimized and efficient
					manner. It is modeled on the unique combination of a ‘patient
					centric and medical staff centric’ paradigm, thus providing
					benefits to both the recipients and the providers of healthcare. It
					ensures dramatic improvement in performance along with reducing the
					costs.</p>
				<div class="more">
					<a href="#" data-toggle="modal" data-target="#myModal">Read
						More</a>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	</section>
	<!-- //about -->
	<%--  <section class="skills"> --%>
	<!-- 	<div class="container" style='text-align: center;'> -->
	<!-- 		<img src="/HIS/hisglobal/images/login/logo1.png" alt=" " /> -->
	<!-- 	</div> -->
	<%-- </section>  --%>
	<!-- services -->
	<section class="services" id="services">
	<div class="container">
		<div class="spec ">
			<h3 class="section-title">What We Do</h3>
			<div class="ser-t">
				<b></b> <span><i></i></span> <b class="line"></b>
			</div>
		</div>


		<div class="w3-row-padding w3-center w3-margin-top">
			<div class="w3-third">
				<div class="w3-card-2 w3-container" style="min-height: 288px;">
					<h3>Electronic Health Record</h3>
					<br> <i class="glyphicon glyphicon-plus-sign colo1" style="font-size: 120px;"></i>
					<p
						style="text-align: justify; text-justify: inter-word; color: #111010;">An
						Electronic Health Record (EHR) is an electronic version of a
						patient’s longitudinal health record.</p>
					<div class=readmore style="margin: 1em 0 1em;" align="right">
						<a href="#" data-toggle="modal" data-target="#Ehr">...Read
							More</a>
					</div>
				</div>
			</div>

			<div class="w3-third">
				<div class="w3-card-2 w3-container" style="min-height: 288px;">
					<h3>Medical Billing</h3>
					<i class="glyphicon color6" style="font-size: 120px;">&#8377;</i>
					<p
						style="text-align: justify; text-justify: inter-word; color: #111010;">Integrated
						Medical Billing system for OPD,IPD and Ancillary services.Improved
						UI on cash collection..</p>
					<div class=readmore style="margin: 1em 0 1em;" align="right">
						<a href="#" data-toggle="modal" data-target="#bill">...Read
							More</a>
					</div>

				</div>
			</div>

			<div class="w3-third">
				<div class="w3-card-2 w3-container" style="min-height: 288px;">
					<h3>Barcode Integration</h3>
					<br> <i class="glyphicon glyphicon-barcode color3" style="font-size: 120px;"></i>
					<p style="text-align: justify; text-justify: inter-word;">Do
						not spend hours in patient registration and lab tests.Smart HMIS
						system generates a unique..</p>
					<div class=readmore style="margin: 1em 0 1em;" align="right">
						<a href="#" data-toggle="modal" data-target="#barcode">...Read
							More</a>
					</div>

				</div>
			</div>
		</div>



		<div class="w3-row-padding w3-center w3-margin-top">
			<div class="w3-third">
				<div class="w3-card-2 w3-container" style="min-height: 288px;">
					<h3>Dashboard and Dynamic Reports</h3>
					<br> <i class="fa fa-file"
						style="font-size: 120px;"></i>
					<p
						style="text-align: justify; text-justify: inter-word; color: #111010;">Integrated
						Dashboard Works in real time management information system, visual
						..</p>
					<div class=readmore style="margin: 1em 0 1em;" align="right">
						<a href="#" data-toggle="modal" data-target="#dashboard">...Read
							More</a>
					</div>
				</div>
			</div>



			<div class="w3-third">
				<div class="w3-card-2 w3-container" style="min-height: 288px;">
					<h3>Mobile Application and Web Portal</h3>
					<br> <i class="glyphicon glyphicon-phone color4" style="font-size: 120px;"></i>
					<p
						style="text-align: justify; text-justify: inter-word; color: #111010;">Provisional
						Registration services with Aadhaar QR Scan provision through
						Mobile App and ..</p>
					<div class=readmore style="margin: 1em 0 1em;" align="right">
						<a href="#" data-toggle="modal" data-target="#Mobileapps">...Read
							More</a>
					</div>
				</div>
			</div>


			<div class="w3-third">
				<div class="w3-card-2 w3-container" style="min-height: 288px;">
					<h3>Integration with Mera Aspataal</h3>
					<br>
					<!-- <i class="fa fa-hospital-o" style="font-size:120px; color: #1ebaed""></i> -->
					<img alt="alt" src="/HIS/hisglobal/images/login/mera.png"
						style="height: 112px; margin: 4px;">
					<p
						style="text-align: justify; text-justify: inter-word; color: #111010;">Integrated
						with MOH&FW ICT-based Patient Satisfaction System (PSS) Mera
						Aspataal / My...</p>
					<div class=readmore style="margin: 1em 0 1em;" align="right">
						<a href="#" data-toggle="modal" data-target="#meraspatal">...Read
							More</a>
					</div>
				</div>
			</div>

		</div>



	</div>
	</section>
	<!-- //services -->


	<!-- payment -->
	<section class="payment jarallax" id="payment">
	<div class="container">
		<div class="spec ">
			<h3 class="section-title">Usefull link</h3>
			<div class="ser-t">
				<b></b> <span><i></i></span> <b class="line"></b>
			</div>
		</div>
		<div class="payment-grids">
		    <div class="col-sm-3"></div>
			<div class="col-md-3 payment-grid">
				<i class="fa fa-desktop" aria-hidden="true"
					style="font-size: 7em; color: #ffffff;"></i>
				<h5>
					<a href="https://www.aiimspatna.org/" target="_blank">AIIMS Patna WebSite</a>
				</h5>
			</div>
			<div class="col-md-3 payment-grid">
			  <a href="https://www.aiimspatna.org/public/patienthome.php" target="_blank">
				<i class="fa fa-hotel" aria-hidden="true" style="font-size: 7em; color: #ffffff;"></i>
				<h5 style="color:white;">
					Patient Portal
				</h5>
				</a>
			</div>

			<!-- <div class="col-md-3 payment-grid">
				<i class="fa fa-search-plus" aria-hidden="true"></i>
				<h5>
					<a href="" target="_blank">User Manuals</a>
				</h5>
			</div> -->

		</div>
	</section>
	<!-- //payment -->

	<!-- our blog -->


	<section class="blog " id="blog" style="background: #f7f7f7">

	<div class="container">
		<div class="spec ">
			<h3 class="section-title">Gallery</h3>
			<div class="ser-t">
				<b></b> <span><i></i></span> <b class="line"></b>
			</div>
		</div>


		<div class="container">
			<div class="row">
				<div class="row">
					<div class="col-md-9"></div>
					<div class="col-md-3">
						<!-- Controls -->
						<div class="controls pull-right ">
							<a class="left glyphicon glyphicon-chevron-left btn btn-success"
								href="#carousel-example" data-slide="prev"></a><a
								class="right glyphicon glyphicon-chevron-right btn btn-success"
								href="#carousel-example" data-slide="next"></a>
						</div>
					</div>
				</div>
				<div id="carousel-example" class="carousel slide "
					data-ride="carousel">
					<!-- Wrapper for slides -->
					<div class="carousel-inner">
						<div class="item active">
							<div class="row">
								<div class="col-sm-3 ">
									<div class="col-item">
										<div class="photo">
											<img src="/HIS/hisglobal/images/login/gl1.jpg"
												class="img-responsive" alt="a" />
										</div>
										<div class="info">
											<div class="row">
												<!-- <div class="price col-md-12">
													<h5>Event 1</h5>

												</div> -->

											</div>

											<div class="clearfix"></div>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="col-item">
										<div class="photo">
											<img src="/HIS/hisglobal/images/login/gl2.png"
												class="img-responsive" alt="a" />
										</div>
										<div class="info">
											<div class="row">
												<!-- <div class="price col-md-12">
													<h5>Event 2</h5>

												</div> -->

											</div>

											<div class="clearfix"></div>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="col-item">
										<div class="photo">
											<img src="/HIS/hisglobal/images/login/gl4.jpg"
												class="img-responsive" alt="a" />
										</div>
										<div class="info">
											<div class="row">
												<!-- <div class="price col-md-6">
													<h5>Event 3</h5>

												</div> -->

											</div>

											<div class="clearfix"></div>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="col-item">
										<div class="photo">
											<img src="/HIS/hisglobal/images/login/gl5.jpg"
												class="img-responsive" alt="a" />
										</div>
										<div class="info">
											<div class="row">
												<!-- <div class="price col-md-12">
													<h5>Event 4</h5>

												</div> -->

											</div>

											<div class="clearfix"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="item">
							<div class="row">
								<div class="col-sm-3">
									<div class="col-item">
										<div class="photo">
											<img src="/HIS/hisglobal/images/login/gl6.jpg"
												class="img-responsive" alt="a" />
										</div>
										<div class="info">
											<div class="row">
												<!-- <div class="price col-md-12">
													<h5>Event 5</h5>
												</div> -->

											</div>

											<div class="clearfix"></div>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="col-item">
										<div class="photo">
											<img src="/HIS/hisglobal/images/login/gl7.jpg"
												class="img-responsive" alt="a" />
										</div>
										<div class="info">
											<div class="row">
												<!-- <div class="price col-md-12">
													<h5>Event 6</h5>
												</div> -->

											</div>

											<div class="clearfix"></div>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="col-item">
										<div class="photo">
											<img src="/HIS/hisglobal/images/login/gl5.jpg"
												class="img-responsive" alt="a" />
										</div>
										<div class="info">
											<div class="row">
												<!-- <div class="price col-md-12">
													<h5>Event 7</h5>
												</div> -->

											</div>

											<div class="clearfix"></div>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="col-item">
										<div class="photo">
											<img src="/HIS/hisglobal/images/login/gl11.jpg"
												class="img-responsive" alt="a" />
										</div>
										<div class="info">
											<div class="row">
												<!-- <div class="price col-md-12">
													<h5>Event 7</h5>
												</div> -->

											</div>

											<div class="clearfix"></div>
										</div>
									</div>
								</div>
					
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>




	</div>
	</section>

	<!-- modal -->
	<div class="modal about-modal fade" id="myModal" tabindex="-1"
		role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span class="span1" aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<span>e-Sushrut</span>
					</h4>
				</div>
				<div class="modal-body">
					<div class="agileits-w3layouts-info">
						<!-- 	<img src="/HIS/hisglobal/images/login/1.png" alt="" /> -->
						<p style="color: #111010; text-align: justify;">e-Sushrut
							C-DAC’s Hospital Management Information System is a major step
							towards adapting technology to improve healthcare. HMIS
							incorporates an integrated computerized clinical information
							system for improved hospital administration and patient health
							care. It also provides an accurate, electronically stored medical
							record of the patient. A data warehouse of such records can be
							utilized for statistical requirements and for research. The real
							time HMIS streamlines the treatment flow of patients and
							simultaneously empowering workforce to perform to their peak
							ability, in an optimized and efficient manner. It is modeled on
							the unique combination of a ‘patient centric and medical staff
							centric’ paradigm, thus providing benefits to both the recipients
							and the providers of healthcare. It ensures dramatic improvement
							in performance along with reducing the costs.</p>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="modal about-modal fade" id="Ehr" tabindex="-1"
		role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span class="span1" aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<span>Electronic Health Record</span>
					</h4>
				</div>
				<div class="modal-body">
					<div class="agileits-w3layouts-info">
						<!-- <img src="/HIS/hisglobal/images/login/1.png" alt="" /> -->
						<p style="text-align: justify; color: #111010;">An Electronic
							Health Record (EHR) is an electronic version of a patient’s
							longitudinal health record. EHR may contain medical history,
							diagnoses, medications, treatment plans, immunization dates,
							allergies and test results of a patient across time.</p>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="modal about-modal fade" id="bill" tabindex="-1"
		role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span class="span1" aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<span>Medical Billing</span>
					</h4>
				</div>
				<div class="modal-body">
					<div class="agileits-w3layouts-info">
						<!-- <img src="/HIS/hisglobal/images/login/1.png" alt="" /> -->
						<p style="text-align: justify; color: #111010;">Integrated
							Medical Billing system for OPD,IPD and Ancillary
							services.Improved UI on cash collection desks ,equipped with
							Patient Wallet.</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal about-modal fade" id="barcode" tabindex="-1"
		role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span class="span1" aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<span>Barcode Integration</span>
					</h4>
				</div>
				<div class="modal-body">
					<div class="agileits-w3layouts-info">
						<!-- <img src="/HIS/hisglobal/images/login/1.png" alt="" /> -->
						<p style="text-align: justify; color: #111010;">Do not spend
							hours in patient registration and lab tests.Smart HMIS system
							generates a unique barcode for every new patient registration and
							lab samples.Patients OPD/IPD files will have barcode sticker on
							it and only takes seconds to scan and offer hospital services
							like revisits,billing,pharmacy and IPD services etc.Better
							tracking due to less human error,save time and money.</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal about-modal fade" id="dashboard" tabindex="-1"
		role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span class="span1" aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<span>Dashboard and Dynamic Reports</span>
					</h4>
				</div>
				<div class="modal-body">
					<div class="agileits-w3layouts-info">
						<!-- <img src="/HIS/hisglobal/images/login/1.png" alt="" /> -->
						<p style="text-align: justify; color: #111010;">Integrated
							Dashboard Works in real time management information system,
							visual presentation for quick statistics to hospital
							authorities.Dynamic Report provide facility to generate report
							due to intermittent transactions, frequent update.</p>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="modal about-modal fade" id="meraspatal" tabindex="-1"
		role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span class="span1" aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<span>Integration with Mera Aspataal</span>
					</h4>
				</div>
				<div class="modal-body">
					<div class="agileits-w3layouts-info">
						<!-- <img src="/HIS/hisglobal/images/login/1.png" alt="" /> -->
						<p style="text-align: justify; color: #111010;">Integrated
							with MOH&FW ICT-based Patient Satisfaction System (PSS) Mera
							Aspataal / My Hospital for empowering the patient by seeking his
							/ her views/feedbacks on quality of experience in a public
							healthcare facility through SMS and IVRS.</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal about-modal fade" id="Mobileapps" tabindex="-1"
		role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span class="span1" aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<span>Mobile Apps and Web Portal</span>
					</h4>
				</div>
				<div class="modal-body">
					<div class="agileits-w3layouts-info">
						<!-- <img src="/HIS/hisglobal/images/login/1.png" alt="" /> -->
						<p style="text-align: justify; color: #111010;">Provisional
							Registration services with Aadhaar QR Scan provision through
							Mobile App and Web Portal services.Features such as Online
							investigation report view/download and comphrensive Doctor login
							desk provisions are also been provided in mobile apps.</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal about-modal fade" id="myModalo" tabindex="-1"
		role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<div class="modal-body">
						<div class="agileits-w3layouts-info">hhh</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal about-modal fade" id="myModal1" tabindex="-1"
		role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<div class="modal-body">
						<div class="agileits-w3layouts-info">
							<img src="/HIS/hisglobal/images/login/b2.jpg" alt="" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal about-modal fade" id="myModal2" tabindex="-1"
		role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<div class="modal-body">
						<div class="agileits-w3layouts-info">
							<img src="/HIS/hisglobal/images/login/tel.png" alt="" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal about-modal fade" id="myModal3" tabindex="-1"
		role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<div class="modal-body">
						<div class="agileits-w3layouts-info">
							<img src="/HIS/hisglobal/images/login/portal.png" alt="" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal about-modal fade" id="n2" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<div class="modal-body">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span class="span1" aria-hidden="true">&times;</span>
						</button>
						<div class="agileits-w3layouts-info">
							<img src="/HIS/hisglobal/images/login/n2.jpg" alt="" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal about-modal fade" id="n1" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<div class="modal-body">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span class="span1" aria-hidden="true">&times;</span>
						</button>
						<div class="modal-header"></div>
						<div class="agileits-w3layouts-info">
							<img src="/HIS/hisglobal/images/login/n1.jpg" alt="" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal about-modal fade" id="VideoId" tabindex="-1"
		role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span class="span1" aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">GPF View</h4>
				</div>
				<div class="modal-body">
					<div class="agileits-w3layouts-info" id='videoDiv'></div>
				</div>
			</div>
		</div>
	</div>
	<!-- //modal -->


	<!-- footer -->
<section id="contact">
	<footer class="footer">
	<div class="container">
		<div class="row">
			<div class="col-sm-4 paddingtop-bottom footerleft ">
				<h6 class="heading7">Navigation</h6>
				<ul class="footer-ul">
					<li><a href="#"> HOME </a></li>
					<li><a href="#about" >ABOUT</a></li>
					<li><a href="#services" >FEATURES</a></li>
					<li><a href="#payment" >LINK</a></li>
					<li><a href="#blog" >GALLERY</a></li>
					<li><a href="#contact" >CONTACT</a></li>

					<!-- <li><a href="#"> Site Map</a></li> -->
					<!--<li><a href="#"> Privacy Policy</a></li>-->


				</ul>

			</div>

			<div class="col-sm-4 paddingtop-bottom">
				<h6 class="heading7">Useful Links</h6>
				<ul class="footer-ul">
					<li><a href="https://www.aiimspatna.org/" target="_blank">AIIMS Patna WebSite</a></li>
					<li><a href="https://www.aiimspatna.org/public/patienthome.php" target="_blank">Patient Portal</a></li>
				 </ul>
			</div>




			<div class="col-sm-4 paddingtop-bottom cont-ftr">
				<h6 class="heading7">Touch With us</h6>
				<p>
					<a href="https://www.google.com/maps/place/Kashyap+Medico+AIIMS+Patna/@25.572168,85.0534865,19z/data=!4m5!3m4!1s0x39f2a9ea2ae04fd9:0xb2efd2a31008d750!8m2!3d25.572168!4d85.0540337"><i class="fa fa-map-marker"></i></a>All India Institute of Medical Sciences <br>
				</p>
				<p>
					<!-- <i class="fa fa-map-marker"></i> -->Address- Phulwari Sharif, Patna, Bihar, Pin- 801507 <br>
				</p>
				<p>
					<a href="mailto:admin[at]aiimspatna[dot]org"><i class="glyphicon glyphicon-envelope fa-fw"></i>E-mail I.D- admin[at]aiimspatna[dot]org</a><br>
				</p>
				<p>
					 <a href="tel:0612-2451070"><i class="glyphicon glyphicon-phone"></i>0612-2451070</a>
				</p>
				<!-- <div style="height:200px; width:100%" id="map">
	</div> -->
	<!--   start code by vibhuti */ -->
<%-- 	<script>
      function initMap() {
      var myLatLng ={lat: 25.560634, lng: 85.0421137};
        var map = new google.maps.Map(document.getElementById('map'), {
          center: myLatLng,
          zoom: 13
        });
	var marker = new google.maps.Marker({
          map: map,
          position: myLatLng,
	});
      }

    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCI91WSQw9AxKq8CQYoVwIM6BXaHfJ87D4&callback=initMap"
    async defer></script> --%>
				 
			</div>
		</div>
		<div class="clearfix visible-xs"></div>
		<div class="row" style="min-height:100px">
			<div class="col-sm-6 col-sm-offset-3">
				 <img class="img-responsive" src="/HIS/hisglobal/images/login/eSushrut_logo.png" style="margin-left: auto;margin-right: auto;width: 70%;">
			</div>
		</div>
		<div class="clearfix visible-xs" style="padding:4% 0;"></div>
	</div>
</section>
	<!--footer start from here--> </footer>

	<!-- //footer -->

	<!-- copyright -->
	<section class="copyright">
	<div class="agileits_copyright text-center">
		<p>
			©2018 C-DAC.Designed and Developed by C-DAC Noida.<a
				href="www.cdac.in" class="w3_agile"></a>
		</p>
	</div>
	</section>
	
	<!-- //copyright -->

	<script src="/HIS/hisglobal/js/login/jarallax.js"></script>
	<script src="/HIS/hisglobal/js/login/SmoothScroll.min.js"></script>
	<script type="text/javascript">
		/* init Jarallax */
		$('.jarallax').jarallax({
			speed : 0.5,
			imgWidth : 1366,
			imgHeight : 768
		})
	</script>
	<script>
		$(document).ready(
				function() {
					$('i.glyphicon-thumbs-up, i.glyphicon-thumbs-down').click(
							function() {
								var $this = $(this), c = $this.data('count');
								if (!c)
									c = 0;
								c++;
								$this.data('count', c);
								$('#' + this.id + '-bs3').html(c);
							});
					$(document).delegate('*[data-toggle="lightbox"]', 'click',
							function(event) {
								event.preventDefault();
								$(this).ekkoLightbox();
							});
				});
	</script>
	<script type="text/javascript"
		src="/HIS/hisglobal/js/login/move-top.js"></script>
	<script type="text/javascript" src="/HIS/hisglobal/js/login/easing.js"></script>

	<!-- here stars scrolling icon -->
	<script type="text/javascript">
		$(document).ready(function() {
			/*
				var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear' 
				};
			 */

			$().UItoTop({
				easingType : 'easeOutQuart'
			});

		});
       //Added by Vasu on 19.March.18 for Provisional Registration
		$("#idAppointment").click(function(){
			window.open('/HISCitizenServices/registration/transactions/onlineAppointmentNewRegistration.action	','_blank');		
		});
	</script>

	<!-- //here ends scrolling icon -->
	<script src="/HIS/hisglobal/js/login/bars.js"></script>
	<cmbPers:cmbPers></cmbPers:cmbPers>
	<s:token></s:token>
	</s:form>
</body>
<!-- //Body -->
</html>
<!-- //html -->