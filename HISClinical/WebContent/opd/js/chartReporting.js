function printChart(e)
{
	var url="/HISClinical/hisglobal/utility/generictemplate/printingTile.cnt?errorMode=TRY";
	openPopup(createFHashAjaxQuery(url),e,400,600);
}

function setFilterCiteria()
{
	var radFilterChoices = document.getElementsByName("filterCriteria");
	var divDates = document.getElementById("divBetweenDates");
	if(radFilterChoices[1].checked)
	{
		divDates.style.display = "block";
	}
	else
	{
		divDates.style.display = "none";
	}	
}

function viewChart()
{
	var chkCustom = document.getElementsByName("customize")[0];
	if(chkCustom.checked)
		submitFormOnValidate(validate(),'CUSTOM');
	else
		submitFormOnValidate(validate(),'GETREPORT');
}

function submitFormOnValidate(flag,mode)
{
 // alert("flag 123456 "+flag+" mode 123456 "+mode);
 // alert("cr no  "+document.getElementsByName("patCrNo")[0]);
  //alert("submitFormOnValidate")
 	if(flag)
	{
	 //alert("inside if");
		submitForm21(mode);
	}
	else{
// 	alert("elesee")
		return false;
	}
	
}

function validate()
{
	var flag = false;
	var radCharts = document.getElementsByName("chartId");	
	/*for(var i=0;i<radCharts.length;i++)
		if(radCharts[i].checked)
		{
			flag = true;
			break;
		}
	if(!flag)*/
	if(radCharts[0].value == "-1")
	{
		alert("Please Select a Chart");
		radCharts[0].focus();
		return false;
	}
	
	var chartParas = document.getElementsByName("chartPara");
	flag = false;
	if(chartParas[0])
	{
		for(var i=0;i<chartParas.length;i++)
			if(chartParas[i].checked)
			{
				flag = true;
				break;
			}
		if(!flag)
		{
			alert("Please Select at least One Parameter ");
			chartParas[0].focus();
			return false;
		}
	}
	
	
	var radFilterChoices = document.getElementsByName("filterCriteria");
	flag = false;
	if(radFilterChoices[0])
	{
		for(var i=0;i<radFilterChoices.length;i++)
			if(radFilterChoices[i].checked)
			{
				flag = true;
				break;
			}
		if(!flag)
		{
			alert("Please Select either Episode-Wise or Date-Wise");
			radFilterChoices[0].focus();
			return false;
		}
		if(radFilterChoices[1].checked)
		{
			if(!validateDates())
				return false;
		}
	}
	if(!validateDates())
		return false;
	return true;
}

function validateDates()
{
	var fDate = document.getElementsByName("fromDate")[0];
	var tDate = document.getElementsByName("toDate")[0];
	var epiAdmStartDate = document.getElementsByName("epiAdmStartDate")[0];
	var entryDate = document.getElementsByName("entryDate")[0];
	if(fDate.value=="")
	{
		alert("Please Enter From Date");
		fDate.focus();
		return false;
	}
	if(tDate.value=="")
	{
		alert("Please Enter To Date");
		tDate.focus();
		return false;
	}
	if(!DateValidator.validateDatesOnMode(fDate.value,'dd-Mon-yyyy',epiAdmStartDate.value,'dd-Mon-yyyy',DateValidator.COMPARE_MODES["GreaterNEqual"]))
	{
		alert("From Date should be greater than or equal to the Date '"+epiAdmStartDate.value+"'");
		fDate.focus();
		return false;
	}
	if(!DateValidator.validateDatesOnMode(fDate.value,'dd-Mon-yyyy',entryDate.value,'dd-Mon-yyyy',DateValidator.COMPARE_MODES["LessNEqual"]))
	{
		alert("From Date should be less than or equal to the Current Date");
		fDate.focus();
		return false;
	}
	if(!DateValidator.validateDatesOnMode(tDate.value,'dd-Mon-yyyy',entryDate.value,'dd-Mon-yyyy',DateValidator.COMPARE_MODES["LessNEqual"]))
	{
		alert("To Date should be less than or equal to the Current Date");
		fDate.focus();
		return false;
	}
	if(!DateValidator.validateDatesOnMode(fDate.value,'dd-Mon-yyyy',tDate.value,'dd-Mon-yyyy',DateValidator.COMPARE_MODES["LessNEqual"]))
	{
		alert("From Date should be less than or equal to To Date");
		fDate.focus();
		return false;
	}
	return true;
}

function getOrderBy(order, chartId)
{
	document.getElementsByName("sortType")[0].value = order;
	document.getElementsByName("sortChartId")[0].value = chartId;
	submitForm('ORDER');
}

function onClickImage(imgObj)
{
	var divObj=document.getElementById("div"+imgObj.id.substr(3));
	if(divObj.style.display=="none")
	{
		divObj.style.display="block";
		imgObj.src = "/HIS/hisglobal/images/avai/arrow-up.png";
	}
	else if(divObj.style.display=="block")
	{
		divObj.style.display="none";
		imgObj.src = "/HIS/hisglobal/images/avai/arrow-down.png";
	}
}

function doPagination(e,p)
{	
	document.getElementsByName('currentPage')[0].value = p;
	document.getElementsByName('hmode')[0].value = 'PAGINATION';
	document.forms[0].submit();
}

function showGraph(event,chartId,chartName)
{
	//document.getElementsByName("graphChartId")[0].value = chartId;
	var url="/HISClinical/opd/genericChartReporting.cnt?hmode=SHOWGRAPH&graphChartId="+chartId+"&graphChartName="+chartName;
	openPopup(createFHashAjaxQuery(url),event,400,600);
}

function setCutomizeOption(cbo, val)
{
	if(cbo.value == "-1")
	{
		setCutomize(true);
		return;
	}
	var cboGenType = document.getElementsByName("generationType")[0];
	var flag = true;
	for(var i=0; i<cboGenType.options.length; i++)
		if(cbo.value==cboGenType.options[i].value)
		{
			if(cboGenType.options[i].text==val)	flag = false;
			break;
		}
	setCutomize(flag);
}

function setCutomize(flag)
{
	var chkCustom = document.getElementsByName("customize")[0];
	if(flag)
	{
		chkCustom.disabled = false;
	}
	else
	{
		chkCustom.checked = false;
		chkCustom.disabled = true;
	}
}