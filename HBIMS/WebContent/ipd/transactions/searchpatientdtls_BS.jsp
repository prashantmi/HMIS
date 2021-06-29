<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%-- <%@ taglib uri="/WEB-INF/hisMessage.tld" prefix="msg"%> --%>


<html>
<head>
<meta charset=utf-8>
<title>Patient Listing</title>


<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
 -->
 
 <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

<!-- <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> -->
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>

<style type="text/css">

.tdFont td{

     font-size:14px;
     border-top: 1px solid lightblue;
   font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
 
}

.tdFont th {
  font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
   color: #401500;
  background-color: rgba(0,0,0,.03);
      font-size:14px;
 
}
</style>

<script language="Javascript">

function autoTabIndexing()
{ 
	var fFlagFocusFirst=true;
	var allImageObj = document.getElementsByTagName('img');
	var allAnchorObj = document.getElementsByTagName('A');
	var allSelectObj = document.getElementsByTagName('SELECT');
	var allInputObj = document.getElementsByTagName('INPUT');
	var allTxtObj = document.getElementsByTagName('TEXTAREA');
	
	/*for(var nTmpI=0; nTmpI<document.forms[0].elements.length; nTmpI++)
	{
		document.forms[0].elements[nTmpI].setAttribute('tabIndex','1');
		if(fFlagFocusFirst && document.forms[0].elements[nTmpI].type!='hidden' 
			&& (document.forms[0].elements[nTmpI].tagName=='INPUT' 
			||document.forms[0].elements[nTmpI].tagName=='SELECT' 
			|| document.forms[0].elements[nTmpI].tagName=='TEXTAREA'))
		{
			document.forms[0].elements[nTmpI].focus();
	    if(((document.forms[0].elements[nTmpI].tagName=='INPUT' 
	    	&& document.forms[0].elements[nTmpI].type=='text') 
	    	|| document.forms[0].elements[nTmpI].tagName=='TEXTAREA') 
	    	&& document.forms[0].elements[nTmpI].value.length >0)
	    {
			document.forms[0].elements[nTmpI].select();
			fFlagFocusFirst=false;
		}
	}
	}*/
	for(var nTmpI=0; nTmpI<allImageObj.length; nTmpI++)
	{
		allImageObj[nTmpI].setAttribute('tabIndex','1');
		if(allImageObj[nTmpI].src.split('/')[allImageObj[nTmpI].src.split('/').length-1]=='tp.gif')
		allImageObj[nTmpI].setAttribute('tabIndex','0');
	}
	for(var nTmpI=0; nTmpI<allAnchorObj.length; nTmpI++)
	{
		allAnchorObj[nTmpI].setAttribute('tabIndex','1');
	}
	for(var nTmpI=0; nTmpI<allSelectObj.length; nTmpI++)
	{
		allSelectObj[nTmpI].setAttribute('tabIndex','1');
	}
	for(var nTmpI=0; nTmpI<allInputObj.length; nTmpI++)
	{
		allInputObj[nTmpI].setAttribute('tabIndex','1');
	}
	for(var nTmpI=0; nTmpI<allTxtObj.length; nTmpI++)
	{
		allTxtObj[nTmpI].setAttribute('tabIndex','1');
	}
}
</script>
</head>
<body onload="configPatListType(),fetchPatientList('1','1'),resize_popUp_Search();">
<html:form action="/transactions/IpdCNT.cnt" method="post">
<div class="normalMsg" id="normalMsg"></div>
<table width="100%" cellspacing="1px">
<tr>
	<td>
	<!-- <table width='100%' align="center" cellspacing="1px">
		<tr class="HEADER"><td colspan="4">Patient Listing</td></tr>
		<tr></tr>
	</table> -->
	<div class="row justify-content-center" >
							<div class="col-md-11">
							<br>
							  <div class="card">
											<div class="card-header" style="padding: .1rem 1.25rem; background-color: white;">
												
												<h4 class="card-title" style="color: black;">Patient Listing </h4>
											</div>
											<div class="card-body" style="padding: 0rem;">
													<div id="fetchRecordDivId"></div>
									            </div> <!-- card-body end .// -->
									 <div id="accordion1" >
	                      <div class='card font' style="max-width: 1230px;">
	                       <!--  <div class='card-header' style="padding: .5rem 1.25rem;">
	                          <a class='card-link' data-toggle='collapse' href='#collapseOne1'><i class='fas fa-angle-down rotate-icon' align='Right'></i></a>
	                        </div> -->
	                         <div class='card-header' style="padding: .5rem 1.25rem; background-color: white; line-height: 0.5">
	                          <a class='card-link' data-toggle='collapse' href='#collapseOne1'><i class='fa fa-question-circle'></i></a>
	                          <label>Help</label>
	                        </div>
	                           <div id='collapseOne1' class='collapse' data-parent='#accordion1'> 
	                              <div class='card-body'>             
					                <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Use <b>%</b>
					to Find Any Data of Any Length (Including zero length)</p>
		
				
					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Use <b>_</b>
					to Find Data on a Single Character Exclusion</p>
				
				                  </div>
					          </div>
					     </div>
					 </div>
								</div> 
	                </div><!-- card.// -->
	           </div>
	     
	
	<table width='100%' align="center" cellspacing="0" style="display: none">
		<tr>
			<td class='LABEL' width="35%"></td>
			<td class='LABEL' width="65%" align="left"><div align="left"><b>Find By&nbsp;</b>
			<select name='strSearchType' class='comboNormal'>
				<option value='1'>CR No.</option>
				<option value='2'>Patient Name</option>
			</select> 
			<input type='text' name='strSearchString' class="txtFldMax"
					style="" onkeypress="return validateSearchText(event);">
			<img style="cursor: pointer;position: absolute;" src="../../hisglobal/images/btn-search.png"
					title="Search Record" name="Search" onClick="getSearchPatListBySearch();" 
					onKeyPress="getSearchPatListBySearch();" width="70"> &nbsp;</div></td>
		</tr>
	</table>
	<!-- <table width='100%' cellspacing="0px">
		<tr class="FOOTER">
			<td width='3%'>
			<div id="plusHelpId" align="center">
			<img src="../../hisglobal/images/plus.gif" name="plusHelp" style="cursor: pointer;"
						align="middle" onclick="showHelpDetails('HelpId');" /></div>
			<div id="minusHelpId" style="display: none" align="center">
			<img  src="../../hisglobal/images/minus.gif" name="minusHelp" style="cursor: pointer;"
					onclick="hideHelpDetails('HelpId');"></div>
			</td>
			<td>
			<div align="left">Help</div>
			</td>
		</tr>
	</table> -->
	<!-- <div id="HelpId" style="display: none">
	<table width='100%' align="center" cellspacing="1px">
				<tr>
					<td class="CONTROL">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Use <b>%</b>
					to Find Any Data of Any Length (Including zero length)</td>
				</tr>
				<tr>
					<td class="CONTROL">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Use <b>_</b>
					to Find Data on a Single Character Exclusion</td>
				</tr>
				<tr class=FOOTER>
					<td></td>
				</tr>
	</table>
	</div> -->
	<!-- <table border="0" width='100%' align="center">
		<tr>
			<td align="right">
			<img style="cursor: pointer;" src="../../hisglobal/images/btn-ok.png" 
			onClick="setCrNo();" title="Select the Patient"></td>
			<td align="left">
			<img style="cursor: pointer;" src="../../hisglobal/images/btn-ccl.png"
				onClick="closePopup();" title="Cancel and Close the Window"></td>
		</tr>
	</table> -->
	<div align="center">
		 <button  type="button" class="btn btn-success btn-sm"  onClick="setCrNo();" style="font-size: 1rem" name="paientAdmissionTransBean" title="Select the Patient">Ok<i class="fa fa-check"></i></button>					
		<button  type="button" class="btn btn-danger btn-sm"  onClick="closePopup();" style="font-size: 1rem" name="paientAdmissionTransBean" title="Cancel and Close the Window">Cancel<i class="fa fa-times-circle-o"></i></button>					
</div>
</td></tr></table>
		<input type="hidden" name="strWinResize" value="${ipdBean.strWinResize}">
		<input type="hidden" name="patListType" value="${ipdBean.usrArg }" />
		<input type="hidden" name="userJsFunctionName" value="${ipdBean.usrFuncName }" />
		<input type="hidden" name="gblCRValue" value="${ipdBean.gblCRValue }" />		
</html:form>
</body>
</html>