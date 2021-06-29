	<jsp:useBean id="configSetup" class="usermgmt.masters.ConfigSetup_Bean" scope="request"><jsp:setProperty name="configSetup" property="*" /></jsp:useBean>
	<%@page import="java.util.*"%>
	<%@page import="login.CSRFTokenUtil"%>
	<%@taglib uri="/WEB-INF/anti_csrf.tld" prefix="anticsrf"%>

<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script type='text/javascript'>
	function isNumberKey(evt)
	{
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (charCode != 46 && charCode > 31 
		&& (charCode < 48 || charCode > 57))
		return false;
		return true;
	}  
	function submitPage()
	{

		//alert("Inside Submit");
		document.forms[0].hmode.value="SAVE";
		document.forms[0].submit();
		
	}
  </script>
</head>
<body>
<form action="ConfigSetup_cnt.jsp" method="post" name="form1">
<div class="container">
  <h2>Global Application Level Configuration Master</h2>
  

  <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#sms">SMS Configuration</a></li>
    <li><a data-toggle="tab" href="#mail">Mail Configuration</a></li>
    <li><a data-toggle="tab" href="#gen">General Configuration</a></li>
    
  </ul>

  <div class="tab-content">
    <div id="sms" class="tab-pane fade in active">
      <h3>Enter SMS Details</h3>
		<div class="row">
			<div class="col-md-3 col-sm-6"><label>SMS Service URL</label></div>
			<div class="col-md-3 col-sm-6"><input class="form-control" id="url" placeholder="url" type="text" maxlength="100" name="url"></div>
			<div class="col-md-3 col-sm-6"><label>SMS Sender ID</label></div>
			<div class="col-md-3 col-sm-6"><input class="form-control" id="senderid" placeholder="senderid" type="text" maxlength="10" name="senderid"></div>
		</div>
		<br>
		<div class="row">
			<div class="col-md-3 col-sm-6"><label>SMS Sender User Name</label></div>
			<div class="col-md-3 col-sm-6"><input class="form-control" id="username" placeholder="username" type="text" maxlength="20" name="username"></div>
			<div class="col-md-3 col-sm-6"><label>SMS Sender Password</label></div>
			<div class="col-md-3 col-sm-6"><input class="form-control" id="password" placeholder="password" type="password" maxlength="15" name="password"></div>
		</div>
		<br>
		<div class="row">
			<div class="col-md-3 col-sm-6"><label>SMS Test Message</label></div>
			<div class="col-md-3 col-sm-6"><input class="form-control" id="msg" placeholder="message" type="text" maxlength="200" name="message"></div>
			<div class="col-md-3 col-sm-6"><label>SMS Test Mobile No</label></div>
			<div class="col-md-3 col-sm-6"><input class="form-control" id="mobileno" onkeypress="return isNumberKey(event)" placeholder="mobileno 10 characters only" type="text" maxlength="10" name="mobileno"></div>
		</div> 
		<br>
		<hr noshade>
		
		 <div class="row">
			<div class="col-md-3 col-sm-6"></div>
			<div class="col-md-3 col-sm-6"><button type="submit" class="btn btn-primary mb-2 center-block" onclick='submitPage()'>Submit Details</button></div>
			<div class="col-md-3 col-sm-6"><button type="submit" class="btn btn-primary mb-2 center-block">Test SMS Connection</button></div>
			<div class="col-md-3 col-sm-6"></div>
		</div>		      
	</div>
    <div id="mail" class="tab-pane fade">
      <h3>Menu 1</h3>
      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
    </div>
    <div id="gen" class="tab-pane fade">
      <h3>Menu 2</h3>
      <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
    </div>
  </div>
</div>

	<input type="hidden" name="hmode">
	<anticsrf:csrftoken/>
</form>
</body>
</html>
