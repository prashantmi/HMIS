<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="mrd.MrdConfig"%>
<%@page import="mrd.transaction.controller.fb.BulletinGeneartionFB"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="hisglobal.presentation.Status"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css"
	rel="stylesheet">
<script src="/HIS/hisglobal/bbpublic/assets/js/jquery-1.11.1.min.js"></script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
var minYearValidation = 2018;
var minMonthValidation = 7;


$( document ).ready(function() {
	document.getElementsByName('bulletinType')[0].value="101";
	var currDate = document.getElementsByName("entryDate")[0].value;
	bulletinCombo=document.getElementsByName('bulletinType')[0].value;
	//bulletinCombo="101";
    
    var d = new Date();
	month = '' + (d.getMonth() + 1), day = '' + d.getDate(), year = ''
			+ d.getFullYear();
	//alert(year);
	var Year = year;
	document.getElementById('year').innerHTML = "";

	var optn = document.createElement("OPTION");
	optn.text = "Select";
	optn.value = "-1";
	document.getElementById('year').options.add(optn);

	for (var y = minYearValidation; y <= Year; y++) {
		var optn = document.createElement("OPTION");
		optn.text = y;
		optn.value = y;

		document.getElementById('year').options.add(optn);

	}
    if (bulletinCombo == 101) {
		document.getElementById("monthdiv").style.display = "block"; 
		document.getElementById("yeardiv").style.display = "none";
		document.getElementById('year1').value = "Select";
		for (var y = minYearValidation; y <= Year; y++) {
			var optn = document.createElement("OPTION");
			optn.text = y;
			optn.value = y;

			document.getElementById('year1').options.add(optn);
		}
    }
		else if (bulletinCombo == -1) {
			document.getElementById("yeardiv").style.display = "none";
			document.getElementById("monthdiv").style.display = "none";
		} else {
			document.getElementById("yeardiv").style.display = "block";
			document.getElementById("monthdiv").style.display = "none";
		}
});

	function anotherTile() {
		//alert(obj.value);
		var currDate = document.getElementsByName("entryDate")[0].value;
		//alert(currDate);
		var bulletinCombo = document.getElementsByName('bulletinType')[0].value;
		//alert(bulletinCombo);
		var d = new Date();
		month = '' + (d.getMonth() + 1), day = '' + d.getDate(), year = ''
				+ d.getFullYear();
		//alert(year);
		var Year = year;
		document.getElementById('year').innerHTML = "";

		var optn = document.createElement("OPTION");
		optn.text = "Select";
		optn.value = "-1";
		document.getElementById('year').options.add(optn);

		for (var y = minYearValidation; y <= Year; y++) {
			var optn = document.createElement("OPTION");
			optn.text = y;
			optn.value = y;

			document.getElementById('year').options.add(optn);

		}

		if (bulletinCombo == 101) {
			document.getElementById("monthdiv").style.display = "block";
			document.getElementById("yeardiv").style.display = "none";
			document.getElementById('year1').value = "Select";
			for (var y = minYearValidation; y <= Year; y++) {
				var optn = document.createElement("OPTION");
				optn.text = y;
				optn.value = y;

				document.getElementById('year1').options.add(optn);
			}

		} else if (bulletinCombo == -1) {
			document.getElementById("yeardiv").style.display = "none";
			document.getElementById("monthdiv").style.display = "none";
		} else {
			document.getElementById("yeardiv").style.display = "block";
			document.getElementById("monthdiv").style.display = "none";
		}

	}

	function generatebtnfun(event) {

		var bulletinCombo = document.getElementsByName('bulletinType')[0].value;
		//var currDate = document.getElementsByName("entryDate")[0].value;
		var d = new Date();
		month1 = '' + (d.getMonth() + 1), day1 = '' + d.getDate(), year1 = ''
				+ d.getFullYear();

		//if (month1.length < 2) month1 = '0' + month1;
		
			//alert(year1);
		
		var month = document.getElementById('monthvalue').value;
		//alert(month);
		var year = document.getElementById('year1').value;
		//alert(year1-year);
		const monthNames = ["January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"
                  ];
		var monthNumber = monthNames.indexOf(month) + 1;
        //alert(monthNumber);
        //alert(month1);
		//alert(monthNames.indexOf(month) + 1);
		var popupUrl = "/HISClinical/mrd/BulletinGeneration.cnt?hmode=BULLETIN_DETAILS&selectedYear="+ year+"&selectedMonth="+ month+"&selectedMonthNumber="+ monthNumber+"&bulletinType="+ bulletinCombo + " ";

		if (bulletinCombo == -1)
		{
			alert("Please Select Bulletin..");
			document.getElementById('bulletinType').focus();
		}
		else if (month == -1)
		{
			alert("Please Select Month..");
			document.getElementById('monthvalue').focus();
		}
		else if(year == -1)
		{
			alert("Please Select Year..");
			document.getElementById('year1').focus();
		}
		else if(year<minYearValidation || year>year1 )
		{
			alert("Please Select Correct Year..");
			document.getElementById('year1').focus();
		}
		else if(year==year1 && monthNumber>=month1)
		{
			alert("Month should be less than Current Month ...");
			document.getElementById('monthvalue').focus();
		}
		else if(year==minYearValidation && monthNumber<minMonthValidation)
		{
			alert("Month should be greater than "+monthNames[minMonthValidation-1]+" Month ...");
			document.getElementById('monthvalue').focus();
		} else
		{
			openDependentPopup(popupUrl, event, 700, 700, true);
		}
	/*
				$.ajax({
						url : "/HISClinical/mrd/BulletinGeneration.cnt?hmode=BULLETIN_DETAILS&selectedYear="+ year+ "&selectedMonth="+ month+"&selectedMonthNumber="+ monthNumber+ "&bulletinType=" + bulletinCombo + " ",
						type : 'POST',
						async : false,
						success : function(data) {
							//alert(data);
							//	alert('success');
							openDependentPopup(popupUrl, event, 700, 700, true);
						},
						error : function(data) {
							alert('request failed :');
						}

					});
		*/
	}

	function openDependentPopup(url, eventObj, height, width, resize) {
		//alert(url);
		if (eventObj.type == "click" || eventObj.keyCode == 13) {
			child = window.open(url, 'popupWindow',
					'status=yes,scrollbars=yes,height=' + height + ',width='
							+ width
							+ ',left=10,top=10,dependent=yes,resizable='
							+ resize + '');
			child.moveTo(250, 250);
			child.focus();

			if (!child.opener)
				child.opener = self;
		}
		return child
	}
</script>
</head>
<%
	String sysDate = WebUTIL.getCustomisedSysDate(
			(Date) session.getAttribute(Config.SYSDATEOBJECT),
			"dd-MMM-yyyy");
%>
<input name="entryDate" value="<%=sysDate%>" type="hidden" />
<body style="background-color: #f0f3f5">
	<div class="Container-fluid" style="padding-top: 5px;">

		<div class="row"
			style="background: linear-gradient(to bottom, #74a9d8, #74a9d8); border-radius: 5px; height: 25px;">
			<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12"
				style="font-size: 1em; font-weight: bold; color: white; padding-left: 10px;">
				Bulletin Generation</div>
		</div>

		<div class="row" style="background-color: white; padding-top: 8px;">
			<div class="col-sm-5 col-md-5 col-lg-5 col-xl-5"
				style="text-align: right; font-size: 0.9em; font-weight: bold; padding-top: 8px;">
				Bulletin Type:</div>
			<div class="col-sm-7 col-md-7 col-lg-7 col-xl-7"
				style="text-align: left;">

				<html:select name="BulletinGeneartionFB" property="bulletinType"
					tabindex="1" styleClass="form-control" onchange="anotherTile();"
					style="width:210px;">
					<html:option value="-1">Select Value</html:option>
					<logic:present name="<%=MrdConfig.BULLETIN_TYPE%>">
						<html:options collection="<%=MrdConfig.BULLETIN_TYPE%>"
							property="value" labelProperty="label" />
					</logic:present>
				</html:select>
			</div>
		</div>

		<div class="row" id="monthdiv"
			style="background-color: white; padding-top: 8px; display: none;">
			<div class="col-sm-3 col-md-3 col-lg-3 col-xl-3"
				style="text-align: right; padding-top: 8px;"></div>
			<div class="col-sm-2 col-md-2 col-lg-2 col-xl-2"
				style="text-align: right; font-size: 0.9em; font-weight: bold; padding-top: 12px;">
				Month</div>
			<div class="col-sm-2 col-md-2 col-lg-2 col-xl-2"
				style="text-align: right; padding-top: 8px;">
				<select class="form-control" id="monthvalue" style="">
					<option value="-1">Select Month</option>
					<option>January</option>
					<option>February</option>
					<option>March</option>
					<option>April</option>
					<option>May</option>
					<option>June</option>
					<option>July</option>
					<option>August</option>
					<option>September</option>
					<option>October</option>
					<option>November</option>
					<option>December</option>
				</select>
			</div>
			<div class="col-sm-1 col-md-1 col-lg-1 col-xl-1"
				style="text-align: right; font-size: 0.9em; font-weight: bold; padding-top: 12px;">Year</div>
			<div class="col-sm-1 col-md-1 col-lg-1 col-xl-1"
				style="text-align: right; padding-top: 8px;">
				<select class="form-control" id="year1" style="width:">
					<option value="-1">Select</option>
				</select>
			</div>
			<div class="col-sm-1 col-md-1 col-lg-1 col-xl-1"
				style="text-align: right; padding-top: 8px;">
				<button type="button" id="generatebtn"
					onclick="generatebtnfun(event);" class="btn btn-sm btn-success"
					style="margin-top: 5px;">Generate</button>
			</div>
			<div class="col-sm-2 col-md-2 col-lg-2 col-xl-2"
				style="text-align: right; padding-top: 8px;"></div>
		</div>


		<div class="row" id="yeardiv"
			style="background-color: white; padding-top: 8px; display: none;">
			<div class="col-sm-3 col-md-3 col-lg-3 col-xl-3"></div>
			<div class="col-sm-2 col-md-2 col-lg-2 col-xl-2"
				style="text-align: right; font-size: 0.9em; font-weight: bold; padding-top: 12px;">
				Year</div>
			<div class="col-sm-1 col-md-1 col-lg-1 col-xl-1"
				style="text-align: right; padding-top: 8px;">
				<select class="form-control" id="year" style="width:">
					<option>Select</option>
				</select>
			</div>

			<div class="col-sm-1 col-md-1 col-lg-1 col-xl-1"
				style="text-align: right; padding-top: 8px;">
				<button type="button" id="generatebtn" onclick="generatebtnfun();"
					class="btn btn-sm btn-success" style="margin-top: 5px;">Generate</button>
			</div>
			<div class="col-sm-3 col-md-3 col-lg-3 col-xl-3"></div>
		</div>

	</div>

	<html:hidden name="BulletinGeneartionFB" property="bulletinType" />
	<html:hidden name="BulletinGeneartionFB" property="hmode" />
</body>

