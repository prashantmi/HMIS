<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<%@page import="hissso.config.HISSSOServerConfig"%>
<%@page import="hislogin.config.HISLoginConfig"%>
<%@page import="hisglobal.config.HISConfig"%>
<%@page import="hissso.config.HISSSOConfig"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

<link href="/HIS/hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jqueryExtValidationToolTip.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/easyui.css" rel="stylesheet" type="text/css">

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/hashFunctions.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/commonFunctions.js"></script>
<script language="JavaScript" type="text/javascript" src="/AHIMSG5/hislogin/js/aes.js"></script>
<script language="JavaScript" type="text/javascript" src="/AHIMSG5/hislogin/js/mode-ecb.js"></script>

<script type="text/javascript" src="/HIS/hisglobal/masterutil/js/jquery/security.js"></script> 
<script>
var IS_CAPTCHA_REQ = "<%=HISConfig.CAPTCHA_IMPLEMENTATION%>";
var passworkSecureKey = "<%=(String)session.getAttribute(HISSSOConfig.LOGGEDIN_USER_SECRET_KEY)%>"

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

</head>
<body onload="setPasswordStrength()">
<center>


<div class="wrapper rounded">

<s:form action="LgnFtr" id="changePasswordForm">
  
	<div class="div-table" >
		<div class="div-table-row " >
			<div class="div-table-col title width100 " >
					Change Password Details
			</div>
		</div>
		<div class="div-table-row ">
			<div class="div-table-col width40 label" >
				<font color="red">*</font>Old Password 
			</div>
			<div class="div-table-col width60 control" >
				<input type="password" name="varOldPassword" tabindex="1" value="" maxlength="15" id="varOldPassword" autocomplete="off">
			</div>
		</div>
		<div class="div-table-row ">
			<div class="div-table-col width40 label" >
				<font color="red">*</font>New Password 
			</div>
			<div class="div-table-col width60 control" >
				<input type="password" name="varPassword" tabindex="1" value="" maxlength="15" id="varPassword" autocomplete="off">
			</div>
		</div>
		<div class="div-table-row ">
			<div class="div-table-col width40 label" >
				<font color="red">*</font>Confirm Password 
			</div>
			<div class="div-table-col width60 control" >
				<input type="password" name="varConfirmPassword" tabindex="1" value="" maxlength="15" id="varConfirmPassword" autocomplete="off">
			</div>
		</div>
		 <div class="div-table-row ">
		<div class="div-table-col width40 label" >
			<%if(HISConfig.CAPTCHA_IMPLEMENTATION.equals("ON")) {%>
	 <img id="captchaImg" src="/AHIMSG5/hislogin/captchaLgnFtr" style="width:20%" alt="Captcha Image" height="30" />
	
	</div>
	
	<div class="div-table-col width60 control" >
	<input type="text" tabindex="1" class="captcha-Text"   maxlength="3" name="captchaResponse" id="captchaTxtBox" placeholder="Enter Captcha" autocomplete="off"  onkeypress="return isNumber(event);"/>
	 <img src="/HIS/hisglobal/images/buttons/refresh.png" alt="Reload"   onclick="document.forms[0].captchaImg.src='/AHIMSG5/hislogin/captchaLgnFtr'+'?id='+Math.random();refreshTxtBox();" style="cursor:pointer"/>
	 </div>
	  <%}%>
	  </div>
		
		<div class="div-table-row ">
			<div class="div-table-col width50 label" >
				<font color='red'>
					<br>
		     		The Password is case sensitive. 
		     	</font>
		     </div>
		</div>
	</div>
	
	<div class="div-table-button">
		<div class="div-table-row footerBar">
			<div class="div-table-col"> </div>
		</div>
		<div class="div-table-row emptyBar">
			<div class="div-table-col"> </div>
		</div>
		<div class="div-table-row" align="center">
			<a href="#" class="button" tabindex="1" id="idSave"><span class="save">Save</span></a>
			<a href="#" class="button" tabindex="1" id="idClear"><span class="clear">Clear</span></a>
			<a href="#" class="button" tabindex="1" id="idCancel"><span class="cancel">Cancel</span></a>
		</div>
	</div>
	<s:hidden name="varUserName"></s:hidden>
	<s:hidden name="varSK"></s:hidden>
	<s:hidden name="newPassword"></s:hidden>

<div id="divElementErrorsId" class="alertMessage"><s:actionerror/></div>

<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
</s:form>

<script type="text/javascript">
var passwordStrength = "<%=HISLoginConfig.PASSWORD_STRENGTH%>";

function setPasswordStrength()
{
	document.getElementsByName('varOldPassword')[0].focus();

	$('[name="varPassword"]').validatebox({
		required: true,
		validType : [ 'alphaSpecialChar', 'minLength[8]', 'maxLength[15]', 'notEqualTo["#varOldPassword","Old Password","New Password"]', 'passwordStrength['+passwordStrength+']']
	});
	$('[name="varConfirmPassword"]').validatebox({
		required: true,
		validType : [ 'alphaSpecialChar', 'minLength[8]', 'maxLength[15]', 'equalTo["#varPassword","New Password","Confirm Password"]' ]
	});
}

function callMenu(url)
{
	//alert('menu called with url: '+ url);
	var targetURL = url;// + "?varSSOTicketGrantingTicket=" + document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
	
	
	var elemFrame = parent.document.getElementById("frmMain");
	if(elemFrame!=null){
		elemFrame.src=targetURL;
		elemFrame.refresh();
	}
	else{
		if(typeof $('#tabframe')!='undefined'){
			var tab = window.parent.$('#tabframe').tabs('getSelected');
			var index = window.parent.$('#tabframe').tabs('getTabIndex',tab);
			window.parent.$('#tabframe').tabs('select',index-1);	
			window.parent.$('#tabframe').tabs('close',index);	

		}
	}
}

var changePasswordDetail = {
		clearForm : function()
		{
			document.getElementsByName("varOldPassword")[0].value = "";
			document.getElementsByName("varPassword")[0].value = "";
			document.getElementsByName("varConfirmPassword")[0].value = "";
		},
		submitOnSave : function()
		{
			if(!changePasswordDetail.secureOldPassword() || !changePasswordDetail.securePassword())
			{
				document.getElementById("divElementErrorsId").innerHTML = "Faced Some Unknown Problem. Please try Again!";
				document.getElementsByName("varOldPassword")[0].value = "";
				document.getElementsByName("varPassword")[0].value = "";
				document.getElementsByName("varConfirmPassword")[0].value = "";
				return;
			}
			else
			{
				//For Submission
				if (IS_CAPTCHA_REQ == "ON" && document.getElementsByName("captchaResponse")[0].value == "")
				{
					document.getElementById("divElementErrorsId").innerHTML = "Captcha is empty!";
					return false;
				}
				else
				{
					document.getElementsByName("varUserName")[0].value="";
					var pass = document.getElementsByName("varPassword")[0].value;
					/*
					///alert("pass   " + pass)
					alert("key   " + passworkSecureKey )
				//	var key = CryptoJS.enc.Hex.parse('000102030405060708090a0b0c0d0e0f');
					//  var iv  = CryptoJS.enc.Hex.parse('101112131415161718191a1b1c1d1e1f');
   							// var encrypted = CryptoJS.DES.encrypt(pass, key, { iv: iv });
				//	var encrypted = CryptoJS.DES.encrypt(pass, passworkSecureKey);
					//alert(encrypted.toString())
					// var key = CryptoJS.enc.Hex.parse(encrypted.toString());
					// alert(encrypted)
   					// var e_msg = encrypted.toString();
				var SK = encrypt(pass, passworkSecureKey);
					alert("encrypt    " + SK)
				//	var dSK = decryptByDES(SK, passworkSecureKey);
				//	alert("decrypt   "+dSK)
					document.getElementsByName("varSK")[0].value=SK;*/
					sortandEncodebase64($("#changePasswordForm")); //Added by Vasu on 22.May.2018 for STH:http parameter pollution
			  		submitForm("saveChangePasswordLgnFtr");
				}
			}
		},
		secureOldPassword : function()
		{
			var hashValue = "";
			var objPassHash = new jsSHA(document.getElementsByName("varOldPassword")[0].value+document.getElementsByName("varUserName")[0].value, "ASCII");
			try 
			{
				hashValue = objPassHash.getHash("SHA-1", "HEX");
			} 
			catch(e)
			{
				return false;
			}

			document.getElementsByName("varOldPassword")[0].value = hashValue;
			//alert(document.getElementsByName("varOldPassword")[0].value)
			return true;
		},
		securePassword : function()
		{
			var hashValue = "";
			var pass=document.getElementsByName("varPassword")[0].value;
			var SK = encrypt(pass, passworkSecureKey);
		//	alert(SK);
			document.getElementsByName("varSK")[0].value=SK;
			
			var objPassHash = new jsSHA(document.getElementsByName("varPassword")[0].value+document.getElementsByName("varUserName")[0].value, "ASCII");
			try 
			{
				hashValue = objPassHash.getHash("SHA-1", "HEX");
			} 
			catch(e)
			{
				return false;
			}
			document.getElementsByName("varPassword")[0].value = hashValue;
			document.getElementsByName("varConfirmPassword")[0].value = hashValue;
			//document.getElementsByName("hashedNewPAss")[0].value = hashValue;
			//alert(document.getElementsByName("hashedNewPAss")[0].value)
			return true;
		}


	};

	$('[name="varOldPassword"]').validatebox({
		required: true,
		validType : [ 'alphaSpecialChar', 'minLength[8]', 'maxLength[15]' ]
	});
	$('[name="varPassword"]').validatebox({
		required: true,
		validType : [ 'alphaSpecialChar', 'minLength[8]', 'maxLength[15]', 'notEqualTo["#varOldPassword","Old Password","New Password"]', 'passwordStrength['+passwordStrength+']']
	});
	$('[name="varConfirmPassword"]').validatebox({
		required: true,
		validType : [ 'alphaSpecialChar', 'minLength[8]', 'maxLength[15]', 'equalTo["#varPassword","New Password","Confirm Password"]' ]
	});

	// On Click of Clear
	$('#idClear').click(function(e){
		changePasswordDetail.clearForm();
	});

	// On Click of Cancel
	$('#idCancel').click(function(e){
		callMenu("/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp");
		e.preventDefault();
	});

	// On Click of Save
	$('#idSave').click(function(e){
		//setPasswordStrength();
		if($('#changePasswordForm').form('validate')){
			var password = document.getElementsByName("varPassword")[0].value;
			document.getElementsByName("newPassword")[0].value = password;
			//document.getElementsByName("varUserName")[0].value="";
			changePasswordDetail.submitOnSave();
		}else{
			return false;
		}
		e.preventDefault();
	});

	function encrypt(message, key) {

	    var encrypted = CryptoJS.AES.encrypt(message, key);
		  console.log(encrypted.toString());

		//  alert(CryptoJS.AES.encrypt('123456', key));

		  
                      /*var iv = CryptoJS.enc.Hex.parse('0000000000000000');
                      var cipher = CryptoJS.AES.encrypt(message, key, {
		                            iv: iv,
		                            mode: CryptoJS.mode.ECB,
		                            keySize: 256 / 32,
		                            padding: CryptoJS.pad.Pkcs5
		                        });

                      alert(cipher);
		   var cipherBase64 =  cipher.ciphertext.toString().hex2a().base64Encode();
	var encrypted = cipherBase64;
		   

		/*var cipher = crypto.createCipher('aes-128-ecb', key);
		var encrypted = cipher.update(message,'utf8','base64')
		encrypted += cipher.final('base64');*/
		
		/*alert(message +"  "+key);

	    var encrypted = CryptoJS.AES.encrypt(message, key, {
	        mode: CryptoJS.mode.ECB,
	        padding: CryptoJS.pad.Pkcs5
	    });




		  
		  // will output something like:
		  // U2FsdGVkX1/l/LqNSCQixd0iPv4neKAGZvbQDbYUovZE4OcM7l3ULNDgkZQmrweN
		  //var decrypted = CryptoJS.AES.decrypt(encrypted, password);
		  //console.log(decrypted.toString(CryptoJS.enc.Utf8));


		  
	    // For the key, when you pass a string,
	    // it's treated as a passphrase and used to derive an actual key and IV.
	    // Or you can pass a WordArray that represents the actual key.
	    // If you pass the actual key, you must also pass the actual IV.
	    var keyHex = CryptoJS.enc.Utf8.parse(key);
	    // console.log(CryptoJS.enc.Utf8.stringify(keyHex), CryptoJS.enc.Hex.stringify(keyHex));
	    // console.log(CryptoJS.enc.Hex.parse(CryptoJS.enc.Utf8.parse(key).toString(CryptoJS.enc.Hex)));

	    // CryptoJS use CBC as the default mode, and Pkcs7 as the default padding scheme
	    var encrypted = CryptoJS.AES.encrypt(message, keyHex, {
	        mode: CryptoJS.mode.ECB,
	        padding: CryptoJS.pad.Pkcs5
	    });
	    // decrypt encrypt result
	    // var decrypted = CryptoJS.DES.decrypt(encrypted, keyHex, {
	    //     mode: CryptoJS.mode.ECB,
	    //     padding: CryptoJS.pad.Pkcs7
	    // });
	    // console.log(decrypted.toString(CryptoJS.enc.Utf8));

	    // when mode is CryptoJS.mode.CBC (default mode), you must set iv param
	    // var iv = 'inputvec';
	    // var ivHex = CryptoJS.enc.Hex.parse(CryptoJS.enc.Utf8.parse(iv).toString(CryptoJS.enc.Hex));
	    // var encrypted = CryptoJS.DES.encrypt(message, keyHex, { iv: ivHex, mode: CryptoJS.mode.CBC });
	    // var decrypted = CryptoJS.DES.decrypt(encrypted, keyHex, { iv: ivHex, mode: CryptoJS.mode.CBC });

	    // console.log('encrypted.toString()  -> base64(ciphertext)  :', encrypted.toString());
	    // console.log('base64(ciphertext)    <- encrypted.toString():', encrypted.ciphertext.toString(CryptoJS.enc.Base64));
	    // console.log('ciphertext.toString() -> ciphertext hex      :', encrypted.ciphertext.toString());*/
	    return encrypted.toString();
	}

	function decryptByDES(ciphertext, key) {
	    var keyHex = CryptoJS.enc.Utf8.parse(key);
	    // direct decrypt ciphertext
	    var decrypted = CryptoJS.AES.decrypt({
	        ciphertext: CryptoJS.enc.Base64.parse(ciphertext)
	    }, keyHex, {
	        mode: CryptoJS.mode.ECB,
	        padding: CryptoJS.pad.Pkcs7
	    });
	    return decrypted.toString(CryptoJS.enc.Utf8);
	}

</script>

</div>
</center>
</body>
</html>