
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>

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
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css">


<link href="/HIS/hisglobal/css/bars.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href="/HIS/hisglobal/css/login/bootstrap.min.css"
	type="text/css" media="all" />
<link rel="stylesheet" href="/HIS/hisglobal/css/login/style.css"
	type="text/css" media="all" />
<link rel="stylesheet" href="/HIS/hisglobal/css/login/font-awesome.css" />



<link href="/HIS/hisglobal/css/basic.css" rel="stylesheet"
	type="text/css" media="screen" />


<!-- 
<link rel='stylesheet' type='text/css' href='/HIS/hisglobal/css/default1.css'  media='screen' />
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/css/js-image-slider.css"  >
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/css/jquery.smartmarquee.css" > -->


<script type="text/javascript"
	src="/HIS/hisglobal/js/login/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="/HIS/hisglobal/js/login/bootstrap.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/hashFunctions.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/util.js"></script>
<script type="text/javascript" src="/AHIMSG5/hissso/js/ssoLogin.js"></script>
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery.simplemodal.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/crawler.js"></script>
<script
	src="//rawgithub.com/ashleydw/lightbox/master/dist/ekko-lightbox.js"></script>
 <script src="https://www.google.com/recaptcha/api.js" async defer></script>


<style>
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
</style>




<!--<script language="JavaScript" src="../../hisglobal/js/jquery-1.10.1.min.js"></script>
<script src="/HIS/hisglobal/js/js-image-slider.js"></script>
<script src="/HIS/hisglobal/js/jquery.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery.simplemodal.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/hashFunctions.js"></script>
<script type="text/javascript" src="/IMCS/hissso/js/ssoLogin.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/util.js"></script>-->

<script>
var IS_CAPTCHA_REQ = "<%=HISConfig.CAPTCHA_IMPLEMENTATION%>";
var sessionToken = "<%=session.getAttribute(HISSSOServerConfig.LOGIN_SESSION_SALT)%>";
	$(function() {
		// 		  $(document).ready(function() { 
		$("#idForgotPassword")
				.click(
						function() {
							openURLInPopup(
									"/AHIMSG5/hislogin/initForgetPasswordLgnFtr.action",
									"600", "300");
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
				openURLInPopup("/AHIMSG5/hislogin/initForgetPasswordLgnFtr.action","600","300");				
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
	var url="/AHIMSG5/hissso/fileDownloadLogin.action?filename="+temp.id;
	 var mode = "fileDownload"; 
		$.ajax({
			  url: "/AHIMSG5/hissso/fileDownloadLogin.action?filename="+temp.id,
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



        </script>
<!-- fonts -->
<link
	href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i&amp;subset=cyrillic,cyrillic-ext,greek,greek-ext,latin-ext,vietnamese"
	rel="stylesheet">
<link
	href="//fonts.googleapis.com/css?family=Ropa+Sans:400,400i&amp;subset=latin-ext"
	rel="stylesheet">
<!-- //fonts -->
<!-- scrolling script -->
</head>
<body onload="document.getElementsByName('varUserName')[0].focus();">
<s:form action="Login"  method="post">
	<div class="top-header" id="home">
		<div class="container-fluid">
			<div class="col-md-8 col-sm-8 col-xs-12 top-left">
				<h1>
					<i class="fa fa-phone" aria-hidden="true"></i>HelpLine:
					<blink>
						<strong> +91-40-23489000</strong>
					</blink>
				</h1>
			</div>
			<!-- <!-- <div class="col-md-4 col-sm-4 col-xs-4 top-right">
					<a href="#" data-toggle="modal" data-target="#myModalo">Login</a>
			
				</div> -->
			
			<div class="clearfix"></div>
		</div>
	</div>
	<div class="top-main1 row" id="top-main">
		<div class="col-md-5 col-lg-5 col-xs-12 head1">
			<div class="wrap-lo">
				<div class="number">
					<a href="#"><img class="img-responsive"
						src="/HIS/hisglobal/images/login/logo.png" alt="" /></a>
				</div>
				<div class="text-logo">
					<h1>Directorate Of Health Services</h1>
					<h2>(Department Of Health And Family Welfare, Government Odisha)</h2>
				</div>
			</div>
		</div>
		<div class="col-md-5 col-lg-5 col-xs-12 head2">
			<nav class="navbar navbar-default">
			<div class="headrg">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#myNavbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav navbar-left">
						<li class="active"><a href="#top-main" class="scroll">home</a></li>
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
		</div>
		<div class="col-md-2 col-lg-2 col-xs-12">
			<div class="social-icons">
				<ul class="top-icons">

					<!-- <li><a href="#"><div class="img-box-content"><img src="/HIS/hisglobal/images/login/t1.jpg" alt=" " height="90" width="90"/></div></a></li> -->
					<li><a href="#"><div class="img-box-content">
								<img class="img-responsive"
									src="/HIS/hisglobal/images/login/t1.png" alt=" " height="80px"
									width="90" />
							</div></a></li>
					<!-- <li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
			<li><a href="#"><i class="fa fa-google-plus" aria-hidden="true"></i></a></li>
			<li><a href="#"><i class="fa fa-pinterest-p" aria-hidden="true"></i></a></li>
			<li><a href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a></li> -->
				</ul>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>




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
	<!-- code by chandni -->
	<div class="cover-banner">
		<div class="loginform">
			
				<h3 align="center">LOGIN</h3>

				<input type="text" tabindex="1" class="loginTextBox"
					style="border-radius: 7px;" placeholder="Enter User Name" value=""
					name="varUserName" maxlength="20"
					onkeypress="return validateAlphaNumWithUnderscoreOnly(this,event);"
					autocomplete="off"><br> <input type="password"
					tabindex="1" class="loginTextBox" style="border-radius: 7px;"
					placeholder="Enter Password" value="" name="varPassword"
					maxlength="10" autocomplete="off"><br>


				<%
					if (HISConfig.CAPTCHA_IMPLEMENTATION.equals("ON")) {
				%>
				<!-- <input type="text" tabindex="1" class="captcha-Text"
					style="width: 34%; border-radius: 7px;" maxlength="6"
					name="captchaResponse" placeholder="Enter Captcha"
					autocomplete="off" /> <img id="captchaImg"
					src="/AHIMSG5/hissso/captchaLogin.action" style="width: 30%"
					alt="Captcha Image" height="30"> <img
					src="/HIS/hisglobal/images/buttons/refresh.png" alt="Reload"
					onclick="document.forms[0].captchaImg.src='/AHIMSG5/hissso/captchaLogin.action'+'?id='+Math.random();"
					style="cursor: pointer" /> -->
					<div class="g-recaptcha" data-sitekey="6LeIAD0UAAAAAEdZKpUCG-orYLHC18g1ZKAMfXn8"> </div>

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
					style="color: #4c03f3" title="Forgot Password?"> Forgot Password?</a>
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
						src="/HIS/hisglobal/images/login/bannerhome5.jpg">
					<div class="carousel-caption banner-right">

						<h1 style="color: white" align="">
							STATISTICS <a id="" title="Refresh Data" href="#"
								onclick="submitForm('loginLogin')"><i
								class="fa fa-refresh digits pull-right"
								style="font-size: 20px; margin-right: 4px;" aria-hidden="true"></i></a>
						</h1>

						<div class="col-md-12 zero" id="bhamashah_enroll">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 16px;"
									align="center">OPD</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("OPD_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("OPD_Count_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 16px;"
									align="center">IPD</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("IPD_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("IPD_COUNT_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 16px;"
									align="center">Lab Test</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("IPD_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("INV_COUNT_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 16px;"
									align="center">Online Registration</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("MOBILE_REG_DATA_TOTAL").toString() %></span>App Based Registration:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class=" digits pull-right"> <%=Integer.parseInt(request.getSession().getAttribute("ONLINE_REGISTRATION_TOTAL").toString()) %></span>Online Provisional
									Registration:
								</h5>
							</div>
						</div>

					</div>
				</div>
				<div class="item">
					<img class="img-responsive"
						src="/HIS/hisglobal/images/login/bb.jpg">
					<div class="carousel-caption banner-right">

						<h1 style="color: white" align="">
							STATISTICS <a id="" title="Refresh Data" href="#"
								onclick="submitForm('loginLogin')"><i
								class="fa fa-refresh digits pull-right"
								style="font-size: 20px; margin-right: 4px;" aria-hidden="true"></i></a>
						</h1>

						<div class="col-md-12 zero" id="bhamashah_enroll">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 16px;"
									align="center">OPD</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("OPD_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("OPD_Count_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 16px;"
									align="center">IPD</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("IPD_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("IPD_COUNT_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 16px;"
									align="center">Lab Test</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("IPD_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("INV_COUNT_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 16px;"
									align="center">Online Registration</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("MOBILE_REG_DATA_TOTAL").toString() %></span>App Based Registration:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=Integer.parseInt(request.getSession().getAttribute("ONLINE_REGISTRATION_TOTAL").toString()) %></span>Online Provisional
									Registration:
								</h5>
							</div>
						</div>

					</div>
				</div>
				<div class="item">
					<img class="img-responsive" src="/HIS/hisglobal/images/login/b.jpg">
					<div class="carousel-caption banner-right">

						<h1 style="color: white" align="">
							STATISTICS <a id="" title="Refresh Data" href="#"
								onclick="submitForm('loginLogin')"><i
								class="fa fa-refresh digits pull-right"
								style="font-size: 20px; margin-right: 4px;" aria-hidden="true"></i></a>
						</h1>

						<div class="col-md-12 zero" id="bhamashah_enroll">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 16px;"
									align="center">OPD</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("OPD_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("OPD_Count_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 16px;"
									align="center">IPD</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("IPD_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("IPD_COUNT_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 16px;"
									align="center">Lab Test</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("IPD_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("INV_COUNT_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 16px;"
									align="center">Online Registration</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("MOBILE_REG_DATA_TOTAL").toString() %></span>App Based Registration:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=Integer.parseInt(request.getSession().getAttribute("ONLINE_REGISTRATION_TOTAL").toString()) %></span>Online Provisional
									Registration:
								</h5>
							</div>
						</div>

					</div>
				</div>
				<div class="item">
					<img class="img-responsive"
						src="/HIS/hisglobal/images/login/nims2.jpg">
					<div class="carousel-caption banner-right">

						<h1 style="color: white" align="">
							STATISTICS <a id="" title="Refresh Data" href="#"
								onclick="submitForm('loginLogin')"><i
								class="fa fa-refresh digits pull-right"
								style="font-size: 20px; margin-right: 4px;" aria-hidden="true"></i></a>
						</h1>

						<div class="col-md-12 zero" id="bhamashah_enroll">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 16px;"
									align="center">OPD</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("OPD_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("OPD_Count_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 16px;"
									align="center">IPD</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("IPD_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("IPD_COUNT_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 16px;"
									align="center">Lab Test</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("IPD_COUNT") %></span>Today:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=request.getSession().getAttribute("INV_COUNT_TOTAL1") %></span>Total:
								</h5>
							</div>
						</div>

						<div class="col-md-12 zero" id="bhamashah_enroll">
							<div class="">
								<h5 class="fontclass cen"
									style="color: #f5a11a; font-weight: bold; font-size: 16px;"
									align="center">Online Registration</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class="digits pull-right"><%=request.getSession().getAttribute("MOBILE_REG_DATA_TOTAL").toString() %></span>App Based Registration:
								</h5>
								<h5 class="fontclass" style="font-size: 16px;" align="left">
									<i class="fa fa-hand-o-right" aria-hidden="true"></i><span
										class=" digits pull-right"><%=Integer.parseInt(request.getSession().getAttribute("ONLINE_REGISTRATION_TOTAL").toString()) %></span>Online Provisional
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
				<p style="color: #111010;">e-Sushrut C-DAC’s Hospital Management
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
					<br> <i class="fa fa-h-square colo1" style="font-size: 120px;"></i>
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
					<br> <i class="fa fa-inr color6" style="font-size: 120px;"></i>
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
					<br> <i class="fa fa-barcode color3" style="font-size: 120px;"></i>
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
					<br> <i class="fa fa-line-chart color2"
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
					<br> <i class="fa fa-android color4" style="font-size: 120px;"></i>
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

	<!-- clients -->
	<%-- <section class="skills">
	<div class="container" style='text-align: center;'>
		<img src="/HIS/hisglobal/images/login/e-Aushadhi.png" alt=" " />
	</div>
</section>  --%>





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
			<div class="col-md-3 payment-grid">
				<i class="fa fa-android" aria-hidden="true"
					style="font-size: 7em; color: #ffffff;"></i>
				<h5>
					<a
						href="https://play.google.com/store/apps/details?id=com.cdac.nimsmobile"
						target="_blank">Mobile app</a>
				</h5>
			</div>
			<div class="col-md-3 payment-grid">
				<i class="fa credit fa-film" aria-hidden="true"></i>
				<h5>
					<a href="https://nims.edu.in" target="_blank">Web Portal</a>
				</h5>
				<h5>
					<a href="https://nims.edu.in" target="_blank">(nims.edu.in)</a>
				</h5>
			</div>

			<div class="col-md-3 payment-grid">
				<i class="fa fa-file-image-o" aria-hidden="true"
					style="font-size: 7em; color: #ffffff;"></i>
				<h5>
					<a href="https://nims.edu.in/list_labreports.php" target="_blank">Online
						Lab Report</a>
				</h5>
				<h5>
					<a href="https://nims.edu.in/provisionalregistration.php"
						target="_blank">Provisional Registration</a>
				</h5>

				<div class="clearfix"></div>
			</div>

			<div class="col-md-3 payment-grid">
				<i class="fa fa-search-plus" aria-hidden="true"></i>
				<h5>
					<a href="http://220.156.189.222/NIMSUSERMANUALS/usermanuals.html"
						target="_blank">User Manuals</a>
				</h5>
			</div>

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
							<a class="left fa fa-chevron-left btn btn-success"
								href="#carousel-example" data-slide="prev"></a><a
								class="right fa fa-chevron-right btn btn-success"
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
											<img src="/HIS/hisglobal/images/login/12.jpg"
												class="img-responsive" alt="a" />
										</div>
										<div class="info">
											<div class="row">
												<div class="price col-md-12">
													<h5>Event 1</h5>

												</div>

											</div>

											<div class="clearfix"></div>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="col-item">
										<div class="photo">
											<img src="/HIS/hisglobal/images/login/13.jpg"
												class="img-responsive" alt="a" />
										</div>
										<div class="info">
											<div class="row">
												<div class="price col-md-12">
													<h5>Event 2</h5>

												</div>

											</div>

											<div class="clearfix"></div>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="col-item">
										<div class="photo">
											<img src="/HIS/hisglobal/images/login/12.jpg"
												class="img-responsive" alt="a" />
										</div>
										<div class="info">
											<div class="row">
												<div class="price col-md-6">
													<h5>Event 3</h5>

												</div>

											</div>

											<div class="clearfix"></div>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="col-item">
										<div class="photo">
											<img src="/HIS/hisglobal/images/login/12.jpg"
												class="img-responsive" alt="a" />
										</div>
										<div class="info">
											<div class="row">
												<div class="price col-md-12">
													<h5>Event 4</h5>

												</div>

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
											<img src="/HIS/hisglobal/images/login/13.jpg"
												class="img-responsive" alt="a" />
										</div>
										<div class="info">
											<div class="row">
												<div class="price col-md-12">
													<h5>Event 5</h5>
												</div>

											</div>

											<div class="clearfix"></div>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="col-item">
										<div class="photo">
											<img src="/HIS/hisglobal/images/login/13.jpg"
												class="img-responsive" alt="a" />
										</div>
										<div class="info">
											<div class="row">
												<div class="price col-md-12">
													<h5>Event 6</h5>
												</div>

											</div>

											<div class="clearfix"></div>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="col-item">
										<div class="photo">
											<img src="/HIS/hisglobal/images/login/12.jpg"
												class="img-responsive" alt="a" />
										</div>
										<div class="info">
											<div class="row">
												<div class="price col-md-12">
													<h5>Event 7</h5>
												</div>

											</div>

											<div class="clearfix"></div>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="col-item">
										<div class="photo">
											<img src="/HIS/hisglobal/images/login/12.jpg"
												class="img-responsive" alt="a" />
										</div>
										<div class="info">
											<div class="row">
												<div class="price col-md-6">
													<h5>Event 8</h5>

												</div>

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
	<!-- //our blog -->
	<%-- 
<section class="skills" id="skills">
	<div class="container">
		<div class="skills-heading">
			<h3>Our growth</h3>
		</div>
		<div class="col-md-2 career-growth">
			<h4>CMO(100) :</h4>
			<h4>CHC(200) :</h4>
			<h4>PHC(100) :</h4>
			<h4>DH(50) :</h4>
			
		</div>
		<section class='col-md-10 bar'>
			  <div class='bar_group'>
				<div class='bar_group__bar thin' value='100'></div>
				<div class='bar_group__bar thin' value='200'></div>
				<div class='bar_group__bar thin' value='100'></div>
				<div class='bar_group__bar thin' value='50'></div>
				
			  </div>
			<div class='clearfix'></div>
		</section>
			<div class='clearfix'></div>
	</div>
</section> --%>
	<!-- //clients -->

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
							Patient Wallet and Ledger Folio billing system.</p>
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

	<!-- contact -->
	<!--<section class="contact" id="contact">
	<div class="container">
		<div class="contact-heading">
			<h3>Contact us</h3>
		</div>
		<div class="contact-grids">
			<div class=" col-md-6 contact-form">
				<form action="#" method="post">
						<input type="text" placeholder="Subject" required=""/>
						<input type="text" placeholder="Your name" required=""/>
						<input type="email" placeholder="Your mail" required=""/>
						<textarea placeholder="Message" required=""></textarea>
						<div class="submit1">
							<input type="submit" value="submit">
						</div>
				</form>
			</div>
			<div class=" col-md-6 map">
				<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d167998.10803373056!2d2.2074740643680624!3d48.85877410312378!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47e66e1f06e2b70f%3A0x40b82c3688c9460!2sParis%2C+France!5e0!3m2!1sen!2sin!4v1488168816174"></iframe>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</section>-->
	<!-- //contact -->

	<!-- footer -->
<section id="contact">
	<footer class="footer">
	<div class="container">
		<div class="row padding-f">
			<div class="col-sm-4 paddingtop-bottom footerleft ">
				<h6 class="heading7">Navigation</h6>
				<ul class="footer-ul">
					<li><a href="#"> Home </a></li>
					<li><a href="#"> About Us</a></li>
					<li><a href="#"> Contact US</a></li>
					<li><a href="#"> Gallery</a></li>

					<!-- <li><a href="sitemap.php"> Site Map</a></li> -->
					<!--<li><a href="#"> Privacy Policy</a></li>-->


				</ul>

			</div>

			<div class="col-sm-4 paddingtop-bottom">
				<h6 class="heading7">Useful Links</h6>
				<ul class="footer-ul">
					<li><a
						href="https://play.google.com/store/apps/details?id=com.cdac.nimsmobile"
						target="_blank">Mobile app</a></li>
					<li><a href="https://nims.edu.in" target="_blank">Web
							Portal</a></li>
					<li><a
						href="http://220.156.189.222/NIMSUSERMANUALS/usermanuals.html"
						target="_blank">User Manuals</a></li>
					<li><a href="https://nims.edu.in/list_labreports.php"
						target="_blank">Online Lab Report</a></li>
					<li><a href="https://nims.edu.in/provisionalregistration.php"
						target="_blank">Provisional Registration</a></li>



				</ul>
			</div>




			<div class="col-md-4 col-sm-6 paddingtop-bottom cont-ftr">
				<h6 class="heading7">Touch With us</h6>
				<p>
					<i class="fa fa-map-marker"></i>Health & Family Welfare <br>
				</p>
				<p>
					<i class="fa fa-map-marker"></i>Address- 2nd Floor, Health Directorate, HOD Building, Bhubaneswar, Odisha, PIN- 751001 <br>
				</p>
				<p>
					<i class="fa fa-envelope-o fa-fw"></i>E-mail I.D- dhsodisha@gmail.com<br>
				</p>
				<p>
					<i class="fa fa-phone"></i> <a href="tel:0120 - 2405968">0674- 2391536</a>
				</p>



			</div>
		</div>
	</div>
</section>
	<!--footer start from here--> </footer>

	<!-- //footer -->

	<!-- copyright -->
	<section class="copyright">
	<div class="agileits_copyright text-center">
		<p>
			©2017 C-DAC.Designed and Developed by C-DAC Noida.<a
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
	</script>

	<!-- //here ends scrolling icon -->
	<script src="/HIS/hisglobal/js/login/bars.js"></script>
	<s:token></s:token>
	</s:form>
</body>
<!-- //Body -->
</html>
<!-- //html -->