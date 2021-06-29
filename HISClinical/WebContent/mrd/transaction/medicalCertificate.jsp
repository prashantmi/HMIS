
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="hisglobal.vo.EpisodeRestAdviceVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.vo.PatMedicalDtlVO"%>

<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="mrd.transaction.controller.fb.MedicalCertificateFB"%>
<%@page import="hisglobal.vo.EpisodeVO"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function showDiagnosis()
{
	var diagnosis=document.getElementsByName("diagnosis")[0].value;
	if(diagnosis=="-1")
		document.getElementsByName("sufferingFrom")[0].value=document.getElementsByName("sufferingFrom")[0].value+"";
	else	
		document.getElementsByName("sufferingFrom")[0].value=document.getElementsByName("sufferingFrom")[0].value+document.getElementsByName("diagnosis")[0].value;
}
function showForNewDiagnosis()
{
	var diagnosis=document.getElementsByName("newDiagnosis")[0].value;
	if(diagnosis=="-1")
		document.getElementsByName("newSufferingFrom")[0].value=document.getElementsByName("newSufferingFrom")[0].value+"";
	else	
		document.getElementsByName("newSufferingFrom")[0].value=document.getElementsByName("newSufferingFrom")[0].value+document.getElementsByName("newDiagnosis")[0].value;
}
function clearRestAdvice()
{
	if(document.getElementsByName("generationMode")[0].value=="<%= Config.MEDICAL_CERTIFICATE_GENERATION_MANUAL%>")
	{
		document.getElementsByName("medicalCertificateId")[0].value="";
	}
	document.getElementsByName("diagnosis")[0].value="-1";
	document.getElementsByName("sufferingFrom")[0].value="";
}

function validateSaveByRestAdvice()
{
	if(document.getElementsByName("sufferingFrom")[0].value=="")
	{
		alert("Please Enter the Suffering From");
		document.getElementsByName("sufferingFrom")[0].focus();
		return false;
	}
	submitPage('SAVE');
}

function showNewAdvice()
{
	submitPage('NEWADVICE');
}

function clearNewAdvice()
{
	if(document.getElementsByName("generationMode")[0].value=="<%= Config.MEDICAL_CERTIFICATE_GENERATION_MANUAL%>")
	{
		document.getElementsByName("newMedicalCertificateId")[0].value="";
	}
	if(document.getElementsByName("backDatedFlagMC")[0].value=="<%=Config.GENERATE_MEDICAL_CERTIFICATE_BACK_DATED_YES%>")
	{
		document.getElementsByName("newFromDate")[0].value="-1";
		document.getElementsByName("newAdviceDays")[0].value="";
	}
	else
	{
		document.getElementsByName("newAdviceDays")[0].value="1";
	}
	document.getElementsByName("newDiagnosis")[0].value="-1";
	document.getElementsByName("newSufferingFrom")[0].value="";
	
	if(document.getElementsByName("episodeCloseDate")[0].value=="")
		document.getElementsByName("newToDate")[0].value=document.getElementsByName("sysDate")[0].value;
	else
		document.getElementsByName("newToDate")[0].value=document.getElementsByName("episodeCloseDate")[0].value;
		
	if(document.getElementsByName("empMappingFlag")[0].value=="<%=Config.MEDICAL_CERTIFICATE_EMPLOYEE_MAPPING_OFFLINE%>")
		document.getElementsByName("newEmpNo")[0].value="-1";
	
}

function showAdviceDays()
{
	var fromDate=document.getElementsByName("newFromDate")[0].value;
	var toDate=document.getElementsByName("newToDate")[0].value;
	if(fromDate=="-1")
		document.getElementsByName("newAdviceDays")[0].value="-";
	if(fromDate!="-1" && toDate!="")
	{
		datecheck(fromDate,toDate);
	}
}

function datecheck(a,b)

{
      var days=0;
      var aArray=a.split("-");
      
      var aday=aArray[0];
      var amonth=aArray[1];
      var ayear=aArray[2];
      var adate=new Date(amonth +" "+ aday+" "+ayear);
      
      var bArray=b.split("-");
     
      var bday=bArray[0];
      var bmonth=bArray[1];
      var byear=bArray[2];
      var bdate=new Date(bmonth +" "+ bday+" "+byear);
      
    
      days=((bdate-adate)/86400000)+1;
      if(days>0)
      {
      	document.getElementsByName("newAdviceDays")[0].value=days;
      	document.getElementsByName("newAdvDays")[0].value=days;
      	
      }
      else
      {
      	  document.getElementsByName("newToDate")[0].focus();
	      document.getElementsByName("newAdviceDays")[0].value=days;
	      document.getElementsByName("newAdvDays")[0].value=days;
	    
	  }
}

function validateSaveByNewAdvice()
{
	var valid=true;
	if(document.getElementsByName("generationMode")[0].value=="<%= Config.MEDICAL_CERTIFICATE_GENERATION_MANUAL%>")
	{
		if(document.getElementsByName("newMedicalCertificateId")[0].value=="")
		{
			alert("Please Enter The Medical Certificate No");
			document.getElementsByName("newMedicalCertificateId")[0].focus();
			valid=false;
		}
		else if(document.getElementsByName("newSufferingFrom")[0].value=="")
		{
			alert("Please Enter The Suffering From");
			document.getElementsByName("newSufferingFrom")[0].focus();
			valid=false;
		}
		else if(document.getElementsByName("newFromDate")[0].value=="-1")
		{
			alert("Please Select The From Date");
			document.getElementsByName("newFromDate")[0].focus();
			valid=false;
		}
		else if(document.getElementsByName("newToDate")[0].value=="")
		{
			alert("Please Select The To Date");
			document.getElementsByName("newToDate")[0].focus();
			valid=false;
		}
		else if(document.getElementsByName("episodeCloseDate")[0].value!="" && !checkCloseDate(document.getElementsByName("newToDate")[0].value,document.getElementsByName("episodeCloseDate")[0].value))
		{
			alert("To Date Cannot be Greater Than "+document.getElementsByName("episodeCloseDate")[0].value);
			document.getElementsByName("newToDate")[0].focus();
			valid=false;
		}
		else if(document.getElementsByName("empMappingFlag")[0].value=="<%=Config.MEDICAL_CERTIFICATE_EMPLOYEE_MAPPING_OFFLINE %>" && document.getElementsByName("newEmpNo")[0].value=="-1")
		{
			alert("Please Select The Consultant");
			document.getElementsByName("newEmpNo")[0].focus();
			valid=false;
		}
		else if(document.getElementsByName("newAdviceDays")[0].value < 1)
		{
			alert("To Date Cannot Be Less Than From Date");
			document.getElementsByName("newToDate")[0].focus();
			valid=false;
		}
		else if(!checkDocMaxDaysValidation())
		{
			alert("You Cannot Generate Medical Certificate More Than "+ document.getElementsByName("maxDaysMC")[0].value +" Days");
			valid=false;
			document.getElementsByName("newToDate")[0].focus();
		}
	}
	else 
	{
		if(document.getElementsByName("newSufferingFrom")[0].value=="")
		{
			alert("Please Enter The Suffering From");
			document.getElementsByName("newSufferingFrom")[0].focus();
			valid=false;
		}
		else if(document.getElementsByName("newFromDate")[0].value=="-1")
		{
			alert("Please Select The From Date");
			document.getElementsByName("newFromDate")[0].focus();
			valid=false;
		}
		else if(document.getElementsByName("newToDate")[0].value=="")
		{
			alert("Please Select The To Date");
			document.getElementsByName("newToDate")[0].focus();
			valid=false;
		}
		else if(document.getElementsByName("episodeCloseDate")[0].value!="" && !checkCloseDate(document.getElementsByName("newToDate")[0].value,document.getElementsByName("episodeCloseDate")[0].value))
		{
			alert("To Date Cannot be Greater Than "+document.getElementsByName("episodeCloseDate")[0].value);
			document.getElementsByName("newToDate")[0].focus();
			valid=false;
		}
		else if(document.getElementsByName("empMappingFlag")[0].value=="<%=Config.MEDICAL_CERTIFICATE_EMPLOYEE_MAPPING_OFFLINE %>" && document.getElementsByName("newEmpNo")[0].value=="-1")
		{
			alert("Please Select The Consultant");
			document.getElementsByName("newEmpNo")[0].focus();
			valid=false;
		}
		else if(document.getElementsByName("newAdviceDays")[0].value < 1)
		{
			alert("To Date Cannot Be Less Than From Date");
			document.getElementsByName("newToDate")[0].focus();
			valid=false;
		}
		else if(!checkDocMaxDaysValidation())
		{
			alert("You Cannot Generate Medical Certificate More Than "+ document.getElementsByName("maxDaysMC")[0].value +" Days");
			valid=false;
			document.getElementsByName("newToDate")[0].focus();
		}
	}	
	return valid;	
}

function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	if(document.getElementsByName("hmode")[0].value=="GETPATDTL")
		document.getElementsByName('hmode')[0].value='PAGINATIONEPI';
	if(document.getElementsByName("hmode")[0].value=="GETADVICEDTL")
		document.getElementsByName('hmode')[0].value='PAGINATIONMC';
	document.forms[0].submit();
}

function giveFitnessDate(obj)
{
	var index=obj;
	document.getElementsByName("index")[0].value=index;
	submitPage('FITDATE');
}

function changeDuration(obj)
{
	var index=obj;
	document.getElementsByName("index")[0].value=index;
	submitPage('EXTEND');
}

/*function showForModifyDiagnosis()
{
	var diagnosis=document.getElementsByName("modDiagnosis")[0].value;
	if(diagnosis=="-1")
		document.getElementsByName("sufferingFrom")[0].value=document.getElementsByName("sufferingFrom")[0].value+"";
	else	
		document.getElementsByName("sufferingFrom")[0].value=document.getElementsByName("sufferingFrom")[0].value+document.getElementsByName("modDiagnosis")[0].value;
	
}*/

function showModAdviceDays()
{
	var fromDate=document.getElementsByName("modFromDate")[0].value;
	var toDate=document.getElementsByName("modToDate")[0].value;
	
	modDatecheck(fromDate,toDate);
}

function modDatecheck(a,b)

{
      var days=0;
      var aArray=a.split("-");
      
      var aday=aArray[0];
      var amonth=aArray[1];
      var ayear=aArray[2];
      var adate=new Date(amonth +" "+ aday+" "+ayear);
      
      var bArray=b.split("-");
     
      var bday=bArray[0];
      var bmonth=bArray[1];
      var byear=bArray[2];
      var bdate=new Date(bmonth +" "+ bday+" "+byear);
      
    
      days=((bdate-adate)/86400000)+1;
      if(days>0)
      {
      	document.getElementsByName("modAdviceDays")[0].value=days;
      }
      else
      {
      	  document.getElementsByName("modToDate")[0].focus();
	      document.getElementsByName("modAdviceDays")[0].value=days;
	  }
}

function validateExtend()
{
	var valid=true;
	if(document.getElementsByName("modSufferingFrom")[0].value=="")
	{
		alert("Please Enter the Suffering From");
		document.getElementsByName("modSufferingFrom")[0].focus();
		valid=false;
	}
	else if(document.getElementsByName("modAdviceDays")[0].value < 1 )
	{
		alert("From Date Cannot be Less Than To Date");
		document.getElementsByName("modToDate")[0].focus();
		valid=false;
	}
	
	else if(!checkDocMaxDaysValidation())
	{
		alert("You cannot Extend the Medical Certificate More Than "+ document.getElementsByName("maxDaysMC")[0].value +" Days");
		document.getElementsByName("modToDate")[0].focus();
		valid=false;
	}
	else if(document.getElementsByName("remarks")[0].value =="" )
	{
		alert("Please Enter the Remarks");
		document.getElementsByName("remarks")[0].focus();
		valid=false;
	}
	return valid;
}

function showNewAdviceDetail()
{
	document.getElementById("newAdviceMC").style.display="block";
	document.getElementById("restAdviceMC").style.display="none";
	document.getElementById("modifyMC").style.display="none";
	
	document.getElementsByName("flagForMCSave")[0].value='<%=MrdConfig.MC_SAVE_FLAG_NEW%>';
	clearRestAdvice();
	clearNewAdvice();
	
	document.getElementsByName("newSufferingFrom")[0].value="";
	document.getElementsByName("sufferingFrom")[0].value="";
	document.getElementsByName("newDiagnosis")[0].value="-1";
	document.getElementsByName("diagnosis")[0].value="-1";
	
}

function getDetailsOfRestAdvice(obj)
{
	document.getElementById("newAdviceMC").style.display="none";
	document.getElementById("restAdviceMC").style.display="block";
	document.getElementById("modifyMC").style.display="none";
	//clearNewAdvice();
		
	document.getElementsByName("fromDate")[0].value=document.getElementsByName("hiddenRestStartDate")[obj.value].value;
	document.getElementsByName("toDate")[0].value=document.getElementsByName("hiddenRestEndDate")[obj.value].value;
	document.getElementsByName("consultantName")[0].value=document.getElementsByName("hiddenRestConsultantName")[obj.value].value;
	document.getElementsByName("adviceDays")[0].value=document.getElementsByName("hiddenRestAdviceDay")[obj.value].value;
	
	
	document.getElementsByName("flagForMCSave")[0].value='<%=MrdConfig.MC_SAVE_FLAG_REST%>';
	document.getElementsByName("newSufferingFrom")[0].value="";
	document.getElementsByName("sufferingFrom")[0].value="";
	document.getElementsByName("newDiagnosis")[0].value="-1";
	document.getElementsByName("diagnosis")[0].value="-1";
	document.getElementsByName("medicalCertificateId")[0].value="";

}

function validateSaveForMC()
{
	var valid=true;
	//alert(document.getElementsByName("flagForMCSave")[0].value);
	/*if(document.getElementsByName("flagForMCSave")[0].value == "")
	{
		alert("Please Select a Record to Generate Medical Certificate on basis of Rest Advice or Click on '+' Button to Generate Medical Certificate on New Advice");
		valid=false;
	}
	else
	{*/
		if(document.getElementsByName("flagForMCSave")[0].value == "<%=MrdConfig.MC_SAVE_FLAG_MODIFY%>")
		{
			valid=validateExtend();
		}
		else if(document.getElementsByName("flagForMCSave")[0].value == "<%=MrdConfig.MC_SAVE_FLAG_REST%>")
		{
			if(document.getElementsByName("generationMode")[0].value=="<%= Config.MEDICAL_CERTIFICATE_GENERATION_MANUAL%>")
			{
				if(document.getElementsByName("medicalCertificateId")[0].value=="")
				{
					alert("Please Enter The Medical Certificate No");
					document.getElementsByName("medicalCertificateId")[0].focus();
					valid=false;
				}
				else if(document.getElementsByName("sufferingFrom")[0].value=="")
				{
					alert("Please Enter the Suffering From");
					document.getElementsByName("sufferingFrom")[0].focus();
					valid=false;
				}
			}
			else
			{
				if(document.getElementsByName("sufferingFrom")[0].value=="")
				{
					alert("Please Enter the Suffering From");
					document.getElementsByName("sufferingFrom")[0].focus();
					valid=false;
				}
			}	
		}
		else
		{
			valid=validateSaveByNewAdvice();	
		}
//	}
	
	return valid;
}

function validateSaveForMCNew()
{
	var valid=true;
	//alert(document.getElementsByName("flagForMCSave")[0].value);
	if(document.getElementsByName("flagForMCSave")[0].value == "<%=MrdConfig.MC_SAVE_FLAG_MODIFY%>")
		valid=validateExtend();
	if(document.getElementsByName("flagForMCSave")[0].value == "<%=MrdConfig.MC_SAVE_FLAG_NEW%>")
		valid=validateSaveByNewAdvice();
	return valid;
}



function getDetailForModify(obj)
{
	document.getElementById("newAdviceMC").style.display="none";
	document.getElementById("restAdviceMC").style.display="none";
	document.getElementById("modifyMC").style.display="block";
	document.getElementsByName("flagForMCSave")[0].value='<%=MrdConfig.MC_SAVE_FLAG_MODIFY%>';
	document.getElementsByName("modifyIndex")[0].value=obj.value;
	
	
	document.getElementsByName("modSufferingFrom")[0].value=document.getElementsByName("hiddenSufferingFrom")[obj.value].value;
	document.getElementsByName("modFromDate")[0].value=document.getElementsByName("hiddenStartDate")[obj.value].value;
	document.getElementsByName("modToDate")[0].value=document.getElementsByName("hiddenEndDate")[obj.value].value;
	document.getElementsByName("modAdviceDays")[0].value=document.getElementsByName("hiddenAdviceDay")[obj.value].value;
	document.getElementsByName("modMaxDays")[0].value=document.getElementsByName("hiddenMaxDays")[obj.value].value;
	document.getElementsByName("medicalCertificateDesc")[0].value=document.getElementsByName("hiddenMCName")[obj.value].value;
	document.getElementsByName("modConsultantName")[0].value=document.getElementsByName("hiddenConsultantName")[obj.value].value;
	document.getElementsByName("medicalCertificateId")[0].value=document.getElementsByName("hiddenMCNo")[obj.value].value;
		
}

function showForModifyDiagnosis()
{
	var diagnosis=document.getElementsByName("modDiagnosis")[0].value;
	if(diagnosis=="-1")
		document.getElementsByName("modSufferingFrom")[0].value=document.getElementsByName("modSufferingFrom")[0].value+"";
	else	
		document.getElementsByName("modSufferingFrom")[0].value=document.getElementsByName("modSufferingFrom")[0].value+document.getElementsByName("modDiagnosis")[0].value;
}

window.onload = function() 
{
	if(document.getElementsByName("certificateType")[0].checked)
	{
		if(document.getElementsByName("flagForMCSave")[0].value=="<%=MrdConfig.MC_SAVE_FLAG_NEW%>")
		{
			document.getElementById("newAdviceMC").style.display="block";
			document.getElementById("restAdviceMC").style.display="none";
			document.getElementById("modifyMC").style.display="none";
		}
		else if(document.getElementsByName("flagForMCSave")[0].value=="<%=MrdConfig.MC_SAVE_FLAG_REST%>")
		{
			document.getElementById("newAdviceMC").style.display="none";
			document.getElementById("restAdviceMC").style.display="block";
			document.getElementById("modifyMC").style.display="none";
		}
		else if(document.getElementsByName("flagForMCSave")[0].value=="<%=MrdConfig.MC_SAVE_FLAG_MODIFY%>")
		{
			document.getElementById("newAdviceMC").style.display="none";
			document.getElementById("restAdviceMC").style.display="none";
			document.getElementById("modifyMC").style.display="block";
		}
		else 
		{
			document.getElementsByName("flagForMCSave")[0].value="<%=MrdConfig.MC_SAVE_FLAG_NEW%>";
			document.getElementById("newAdviceMC").style.display="block";
			document.getElementById("restAdviceMC").style.display="none";
			document.getElementById("modifyMC").style.display="none";
		}
	}	
	if(document.getElementsByName("certificateType")[1].checked)
	{
		var len =document.getElementsByName("selectedRadioFitness").length;
		var count=0;
		for (var i=0;i<len;i++)
		{
			if(document.getElementsByName("selectedRadioFitness")[i].checked==true)
			{
				count++;
			}
		}
		
		if(count<1)
			document.getElementById("generateFC").style.display="none";
		else
			document.getElementById("generateFC").style.display="block";	
	}
}

function showFitnessDetail(obj)
{
	document.getElementById("generateFC").style.display="block";
	
	document.getElementsByName("sufferingFrom")[0].value=document.getElementsByName("hiddenSufferingFrom")[obj.value].value;
	document.getElementsByName("fromDate")[0].value=document.getElementsByName("hiddenStartDate")[obj.value].value;
	document.getElementsByName("toDate")[0].value=document.getElementsByName("hiddenEndDate")[obj.value].value;
	document.getElementsByName("adviceDays")[0].value=document.getElementsByName("hiddenAdviceDay")[obj.value].value;
	document.getElementsByName("consultantName")[0].value=document.getElementsByName("hiddenConsultantName")[obj.value].value;
	//document.getElementsByName("medicalCertificateId")[0].value=document.getElementsByName("hiddenMCNo")[obj.value].value;
	
	document.getElementsByName("fitnessDate")[0].value=document.getElementsByName("hiddenFitnessDate")[obj.value].value;
}

function validateSaveForFC()
{
	var count=0;
	for (var i=0;i<document.getElementsByName("selectedRadioFitness").length;i++)
	{
		if(document.getElementsByName("selectedRadioFitness")[i].checked==true)
		{
			count++;
		}
	}
	
	if(count<1)
	{
		alert("Please Select a Certificate To Generate Fitness Certificate")
		return false;
	}
	else
	{
		if(document.getElementsByName("backDatedFlagFC")[0].value=="<%=Config.GENERATE_FITNESS_CERTIFICATE_BACK_DATED_YES%>")
		{
			if(document.getElementsByName("generationMode")[0].value=="<%= Config.MEDICAL_CERTIFICATE_GENERATION_MANUAL%>")
			{
				if(document.getElementsByName("fitnessCertificateId")[0].value=="")
				{
					alert("Please Enter The Fitness Certificate No");
					document.getElementsByName("fitnessCertificateId")[0].focus();
					return false;
				}
			}
			return true;
		}
		else
		{
			var endDate=document.getElementsByName("toDate")[0].value;
			var fitDate=document.getElementsByName("fitnessDt")[0].value;
			var startDate=document.getElementsByName("fromDate")[0].value;
			if(document.getElementsByName("generationMode")[0].value=="<%= Config.MEDICAL_CERTIFICATE_GENERATION_MANUAL%>")
			{
				if(document.getElementsByName("fitnessCertificateId")[0].value=="")
				{
					alert("Please Enter The Fitness Certificate No");
					document.getElementsByName("fitnessCertificateId")[0].focus();
					return false;
				}
				else if(varifyEndDate(endDate,fitDate)>1)
				{
					if(confirm("Do You Want To Extend The Medical Certificate Duration"))
					{
						if(!validateMCExtendInFC(startDate,fitDate))
						{
							alert("You Cannot Generate Medical Certificate More Than "+ document.getElementsByName("maxDaysMC")[0].value +" Days");
							return false;
						}
						else
						{
							document.getElementsByName("extendFlag")[0].value="<%=MrdConfig.FITNESS_MC_EXTEND_YES%>";
							return true;
						}
					}	
					else
					{
						document.getElementsByName("extendFlag")[0].value="<%=MrdConfig.FITNESS_MC_EXTEND_NO%>";
						return true;
					}	
				}
				else if(varifyEndDate(endDate,fitDate)<1)
				{
					if(confirm("Do You Want To Reduce The Medical Certificate Duration"))
					{
						document.getElementsByName("extendFlag")[0].value="<%=MrdConfig.FITNESS_MC_EXTEND_YES%>";	
						return true;
					}	
					else
						return false;
				}
				else
				{
					document.getElementsByName("extendFlag")[0].value="<%=MrdConfig.FITNESS_MC_EXTEND_NO%>";
					return true;
				}
				
			}
			else
			{
				//AUTOMATIC ID
				
				if(varifyEndDate(endDate,fitDate)>1)
				{
					if(confirm("Do You Want To Extend The Medical Certificate Duration"))
					{
						if(!validateMCExtendInFC(startDate,fitDate))
						{
							alert("You Cannot Generate Medical Certificate More Than "+ document.getElementsByName("maxDaysMC")[0].value +" Days");
							return false;
						}
						else
						{
							document.getElementsByName("extendFlag")[0].value="<%=MrdConfig.FITNESS_MC_EXTEND_YES%>";
							return true;
						}
					}	
					else
					{
						document.getElementsByName("extendFlag")[0].value="<%=MrdConfig.FITNESS_MC_EXTEND_NO%>";
						return true;
					}		
				}
				else if(varifyEndDate(endDate,fitDate)<1)
				{
					if(confirm("Do You Want To Reduce The Medical Certificate Duration"))
					{
						document.getElementsByName("extendFlag")[0].value="<%=MrdConfig.FITNESS_MC_EXTEND_YES%>";
						return true;
					}	
					else
						return false;
				}
				else
				{
					document.getElementsByName("extendFlag")[0].value="<%=MrdConfig.FITNESS_MC_EXTEND_NO%>";
					return true;
				}
			}
		}	
	}
}

function clearForm()
{
	if(document.getElementsByName("certificateType")[0].checked==true)
	{
		if(document.getElementsByName("flagForMCSave")[0].value=="<%=MrdConfig.MC_SAVE_FLAG_NEW%>")
		{
			clearNewAdvice();
		}
		else if(document.getElementsByName("flagForMCSave")[0].value=="<%=MrdConfig.MC_SAVE_FLAG_REST%>")
		{
			clearRestAdvice();
		}
		else if(document.getElementsByName("flagForMCSave")[0].value=="<%=MrdConfig.MC_SAVE_FLAG_MODIFY%>")
		{
			document.getElementsByName("remarks")[0].value="";
			document.getElementsByName("modDiagnosis")[0].value="-1";
			getDetailForModify(document.getElementsByName("modifyIndex")[0]);
		}
		else 
		{
			clearNewAdvice();
		}
	}
	else
	{
		clearFitnessCert();
	}	
}

function clearFitnessCert()
{
	if(document.getElementsByName("generationMode")[0].value=="<%= Config.MEDICAL_CERTIFICATE_GENERATION_MANUAL%>")
	{
		document.getElementsByName("fitnessCertificateId")[0].value="";
	}
}

function varifyEndDate(endDate,fitDate)
{
	var days=0;
    var endDateArray=endDate.split("-");
      
    var endDateday=endDateArray[0];
    var endDatemonth=endDateArray[1];
    var endDateyear=endDateArray[2];
    var endDatedate=new Date(endDatemonth +" "+ endDateday+" "+endDateyear);
      
    var fitDateArray=fitDate.split("-");
     
    var fitDateday=fitDateArray[0];
    var fitDatemonth=fitDateArray[1];
    var fitDateyear=fitDateArray[2];
    var fitDatedate=new Date(fitDatemonth +" "+ fitDateday+" "+fitDateyear);
      
    
    days=((fitDatedate-endDatedate)/86400000);
    /*if(days==1)
    {
    	return true;
    }
    else
    {
    	if(days>1)
      	{
      		return false;
      	}
      	else
      	{
      		return false;
      	}
	}*/
	return days;
}

function checkDocMaxDaysValidation()
{
	/*    // Removed as No Way to Enter the Limit is available in Designation Mappimg 2012.02.04
	//////OFFLINE
	if(document.getElementsByName("empMappingFlag")[0].value=="<%=Config.MEDICAL_CERTIFICATE_EMPLOYEE_MAPPING_OFFLINE%>")
	{
		var empNoWithDay=document.getElementsByName("newEmpNo")[0].value;
		
		var arr=empNoWithDay.split("@");;
		var maxDays=arr[3];
		var advDays=0;
		document.getElementsByName("maxDaysMC")[0].value=maxDays;
		if(document.getElementsByName("hmode")[0].value=="FITCERT")
			advDays=document.getElementsByName("extendedAdviceDays")[0].value;
		else
		{
			if(document.getElementsByName("flagForMCSave")[0].value == "<%=MrdConfig.MC_SAVE_FLAG_MODIFY%>")
			{
				advDays=document.getElementsByName("modAdviceDays")[0].value;
				maxDays=document.getElementsByName("modMaxDays")[0].value;
				document.getElementsByName("maxDaysMC")[0].value=maxDays;
			}	
			else	
				advDays=document.getElementsByName("newAdviceDays")[0].value;
		}
			
		if(parseInt(advDays) <= parseInt(maxDays))
			return true;
		else
			return false;
	}
	else
	{
		//ONLINE
		var maxDays=document.getElementsByName("onlineEmpMaxDays")[0].value;
		var advDays=0;
		document.getElementsByName("maxDaysMC")[0].value=maxDays;
		if(document.getElementsByName("hmode")[0].value=="FITCERT")
			var advDays=document.getElementsByName("extendedAdviceDays")[0].value;
		else
		{
			if(document.getElementsByName("flagForMCSave")[0].value == "<%=MrdConfig.MC_SAVE_FLAG_MODIFY%>")
			{
				advDays=document.getElementsByName("modAdviceDays")[0].value;
				maxDays=document.getElementsByName("modMaxDays")[0].value;
				document.getElementsByName("maxDaysMC")[0].value=maxDays;
			}	
			else	
				advDays=document.getElementsByName("newAdviceDays")[0].value;
		}
				
		if(parseInt(advDays) <= parseInt(maxDays))
			return true;
		else
			return false;
	}*/
	return true;
}

function checkCloseDate(toDate,closeDate)
{
	var days=0;
	var valid;
    var aArray=toDate.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
      
    var bArray=closeDate.split("-");
     
    var bday=bArray[0];
    var bmonth=bArray[1];
    var byear=bArray[2];
    var bdate=new Date(bmonth +" "+ bday+" "+byear);
      
    days=((bdate-adate)/86400000);
    
    if(days<0)
    	valid=false;
    else
    	valid=true;	
    
    return valid;
}

function validateMCExtendInFC(startDate,fitDate)
{
	var days=0;
	var valid;
    var aArray=startDate.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
      
    var bArray=fitDate.split("-");
     
    var bday=bArray[0];
    var bmonth=bArray[1];
    var byear=bArray[2];
    var bdate=new Date(bmonth +" "+ bday+" "+byear);
      
    days=((bdate-adate)/86400000);
    document.getElementsByName("extendedAdviceDays")[0].value=days;
    
   valid=checkDocMaxDaysValidation();
   return valid;
}
</script>

<body>
	<html:form action="/medicalCertificate">
	<%
		String newAdviceMC="none";
		String restAdviceMC="none";
		String modifyMC="none";
		String generateFC="none";
		
	%>
	<bean:define name="medicalCertificateFB" property="newToDate" id="toDate" type="java.lang.String"/>
	<bean:define name="medicalCertificateFB" property="episodeCloseDate" id="closeDate" type="java.lang.String"/>	
	<% if(toDate==null||toDate.equalsIgnoreCase(""))
					{
					toDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
					} 
					
				%>
		<his:TransactionContainer>
			<his:TitleTag name="Medical & Fitness Certificate">
			</his:TitleTag>
			
			<his:statusNew>
			<logic:empty name="medicalCertificateFB" property="patCrNo" >
				<his:InputCrNoTag name="medicalCertificateFB"></his:InputCrNoTag>
			</logic:empty>
			</his:statusNew>
			<his:statusUnsuccessfull>
				<jsp:include page="/registration/patientDetail.cnt" flush="true" />
			</his:statusUnsuccessfull>
			
			<his:statusList>
			
			<jsp:include page="/registration/patientDetail.cnt" flush="true" />
				
				<%
				PaginationFB fbPageEpi= new PaginationFB();
				pageContext.setAttribute("fbPagination",fbPageEpi);
				fbPageEpi.setCurrentPage(((MedicalCertificateFB)request.getAttribute("medicalCertificateFB")).getCurrentPage());
				fbPageEpi.setObjArrName(MrdConfig.MC_PATIENT_ALL_EPISODE_VO_ARR);
				fbPageEpi.setAppendInTitle("Visited Unit");
				fbPageEpi.setMaxRecords(5);
				%>
			<html:hidden name="medicalCertificateFB" property="currentPage"/>
			<his:PaginationTag name="fbPagination"></his:PaginationTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="select"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="22%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="deptName"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="22%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="unit"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="23%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="startday"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="23%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="endday"/>
										</b>	
									</font>
								</div>
							</td>
						</tr>
						
						<%
							EpisodeVO[] episodeVO=(EpisodeVO[])session.getAttribute(MrdConfig.MC_PATIENT_ALL_EPISODE_VO_ARR);
							int startIndexEpi = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
							int endIndexEpi = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
							
							for(int i=startIndexEpi; i<=endIndexEpi; i++)
							{
								EpisodeVO epiVO=episodeVO[i];	
							
						%>
							<tr>
								<td class="tdfont" width="10%" >
									<div align="center">
										<html:radio name="medicalCertificateFB" property="selectedEpiCode" value="<%=epiVO.getEpisodeCode() %>" onclick="submitPage('MEDCERT')"></html:radio>
									</div>
								</td>
								<td class="tdfont" width="22%" >
									<div align="center">
										<%=epiVO.getDepartment() %>
									</div>
								</td>
								<td class="tdfont" width="22%" >
									<div align="center">
										<%=epiVO.getDepartmentUnit() %>
									</div>
								</td>
								<td class="tdfont" width="23%" >
									<div align="center">
										<%=epiVO.getEpisodeDate() %>
									</div>
								</td>
								<td class="tdfont" width="23%" >
									<div align="center">
										<%=(epiVO.getEpisodeCloseDate()==null)?"-":epiVO.getEpisodeCloseDate() %>
									</div>
								</td>
							</tr>	
						<%} %>
					</table>	
				</his:ContentTag>
			</his:statusList>
			
			<his:statusTransactionInProcess>
			<jsp:include page="/registration/patientDetail.cnt" flush="true" />
				<his:SubTitleTag name="Certificate Type">
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="75%" >
							<div align="left">
								<font color="#005B88" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="generate"/>
										<bean:message key="medicalCertificate"/>
									</b>	
								</font>
								<html:radio name="medicalCertificateFB" property="certificateType" value="<%=MrdConfig.CERTIFICATE_TYPE_MEDICAL_CERTIFICATE%>" onclick="submitForm('CHANGEMODE')" ></html:radio>
								<font color="#005B88" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="generate"/>
										<bean:message key="fitnessCertificate"/>
									</b>
								</font>	
								<html:radio name="medicalCertificateFB" property="certificateType" value="<%=MrdConfig.CERTIFICATE_TYPE_FITNESS_CERTIFICATE%>" onclick="submitForm('CHANGEMODE')" ></html:radio>
							</div>
						</td>
						<td width="25%" >
							<div align="right">
								<logic:equal name="medicalCertificateFB" property="certificateType" value="<%=MrdConfig.CERTIFICATE_TYPE_MEDICAL_CERTIFICATE%>">
									<img class="button" id="addButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/plus.png"/>' tabindex='1' title="Medical Certificate On New Advice" onclick="showNewAdviceDetail()">
								</logic:equal>
							</div>
						</td>
					</tr>
				</table>
				</his:SubTitleTag>
				
				<logic:equal name="medicalCertificateFB" property="certificateType" value="<%=MrdConfig.CERTIFICATE_TYPE_MEDICAL_CERTIFICATE%>">
				
				<%PatMedicalDtlVO[] issuePatMCVO=(PatMedicalDtlVO[])session.getAttribute(MrdConfig.MC_ISSUED_MEDICAL_CERTIFICATE_ON_BASIS_EPISODE );
					if(issuePatMCVO.length>0){
					 %>
						
						<%
						PaginationFB fbPageMC= new PaginationFB();
						pageContext.setAttribute("fbPagination",fbPageMC);
						fbPageMC.setCurrentPage(((MedicalCertificateFB)request.getAttribute("medicalCertificateFB")).getCurrentPage());
						fbPageMC.setObjArrName(MrdConfig.MC_ISSUED_MEDICAL_CERTIFICATE_ON_BASIS_EPISODE);
						fbPageMC.setAppendInTitle("Generated Medical Certificate");
						fbPageMC.setMaxRecords(5);
						%>
						<html:hidden name="medicalCertificateFB" property="currentPage"/>
						<his:PaginationTag name="fbPagination"></his:PaginationTag>
						<his:ContentTag>
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr>
									<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="select"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="18%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="medCertificateNo"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="37%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="sufferingFrom"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="advice"/>
													<bean:message key="day"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="startday"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="endday"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="fitnessDate"/>
												</b>	
											</font>
										</div>
									</td>
								</tr>
								
									<%
									PatMedicalDtlVO[] patMedicalVO=(PatMedicalDtlVO[])session.getAttribute(MrdConfig.MC_ISSUED_MEDICAL_CERTIFICATE_ON_BASIS_EPISODE);
									int startIndexMC = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
									int endIndexMC = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX);
									
									for(int i=startIndexMC; i<=endIndexMC; i++)
									{
										PatMedicalDtlVO patMedVO=patMedicalVO[i];	
										
									%>
									<tr>
										<td class="tdfont" width="5%" >
											<div align="center">
											<%String a=""+Integer.toString(i)+""; %>
											<%if(patMedVO.getFitnessCertificateId()== null){ %>
												<html:radio name="medicalCertificateFB" property="selectedRadioModify" value="<%=a%>" onclick="getDetailForModify(this)"></html:radio>
												<%} %>
											</div>
										</td>
										<td class="tdfont" width="18%" >
											<div align="center">
												<%=patMedVO.getMedicalCertificateName() %>
												<html:hidden name="medicalCertificateFB" property="hiddenMCName" value="<%=patMedVO.getMedicalCertificateName() %>"/>
											</div>
										</td>
										<td class="tdfont" width="37%" >
											<div align="center">
												<%=patMedVO.getSufferingFrom() %>
												<html:hidden name="medicalCertificateFB" property="hiddenSufferingFrom" value="<%=patMedVO.getSufferingFrom() %>"/>
											</div>
										</td>
										<td class="tdfont" width="10%" >
											<div align="center">
												<%=patMedVO.getAdviceDays() %>
												<html:hidden name="medicalCertificateFB" property="hiddenAdviceDay" value="<%=patMedVO.getAdviceDays() %>"/>
											</div>
										</td>
										<td class="tdfont" width="10%" >
											<div align="center">
												<%=patMedVO.getFromDate() %>
												<html:hidden name="medicalCertificateFB" property="hiddenStartDate" value="<%=patMedVO.getFromDate() %>"/>
											</div>
										</td>
										<td class="tdfont" width="10%" >
											<div align="center">
												<%=patMedVO.getToDate() %>
												<html:hidden name="medicalCertificateFB" property="hiddenEndDate" value="<%=patMedVO.getToDate() %>"/>
											</div>
										</td>
										<td class="tdfont" width="10%" >
											<div align="center">
												<%if(patMedVO.getFitnessCertificateId()!= null){ %>
													<%= patMedVO.getFitnessDate()%>  
												<%} else{%>
													-
												<%} %>
											</div>
										</td>
									</tr>
									<html:hidden name="medicalCertificateFB" property="hiddenConsultantName" value="<%=patMedVO.getEmpName() %>"/>
									<html:hidden name="medicalCertificateFB" property="hiddenMCNo" value="<%=patMedVO.getMedicalCertificateId() %>"/>
									<html:hidden name="medicalCertificateFB" property="hiddenMaxDays" value="<%=patMedVO.getMaxDays() %>"/>
								<%} %>
							</table>
						</his:ContentTag>	
					<%} %>
				
				
				<%EpisodeRestAdviceVO[] arrEpiRestAdvice=(EpisodeRestAdviceVO[])session.getAttribute(MrdConfig.MC_PATIENT_EPI_REST_ADVICE_VO_BY_EPISODE_ARR);
					if(arrEpiRestAdvice.length>0){
				 %>
					 <his:SubTitleTag name=" Rest Advice detail ">
					 	
					 </his:SubTitleTag>
					 
						<his:ContentTag>
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr>
									<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="select"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="reason"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="startday"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="endday"/>
												</b>	
											</font>
										</div>
									</td>
								</tr>
								<logic:iterate name="<%=MrdConfig.MC_PATIENT_EPI_REST_ADVICE_VO_BY_EPISODE_ARR %>" id="arrRestAdviceVO" type="hisglobal.vo.EpisodeRestAdviceVO" indexId="idx">
									<tr>
										<td class="tdfont" width="10%" >
											<div align="center">
												<html:radio name="medicalCertificateFB" property="selectedRest" value="<%=idx.toString() %>" onclick="getDetailsOfRestAdvice(this)"></html:radio>
											</div>
										</td>
										<td class="tdfont" width="30%" >
											<div align="center">
												<bean:write name="arrRestAdviceVO" property="restReason"/>
											</div>
										</td>
										<td class="tdfont" width="30%" >
											<div align="center">
												<bean:write name="arrRestAdviceVO" property="startDate"/>
												<html:hidden name="medicalCertificateFB" property="hiddenRestStartDate" value="<%=arrRestAdviceVO.getStartDate() %>"/>
											</div>
										</td>
										<td class="tdfont" width="30%" >
											<div align="center">
												<bean:write name="arrRestAdviceVO" property="endDate"/>
												<html:hidden name="medicalCertificateFB" property="hiddenRestEndDate" value="<%=arrRestAdviceVO.getEndDate() %>"/>
											</div>
										</td>
									</tr>	
									<html:hidden name="medicalCertificateFB" property="hiddenRestAdviceDay" value="<%=arrRestAdviceVO.getAdviceDays() %>"/>
									<html:hidden name="medicalCertificateFB" property="hiddenRestConsultantName" value="<%=arrRestAdviceVO.getConsultantName() %>"/>
								</logic:iterate>
							</table>
						</his:ContentTag>
				<%}else { %>
				
					<%  newAdviceMC="";%>
				
				<%} %>
				
				
				<div id="newAdviceMC" style="display: <%=newAdviceMC  %>">
						
						
					<his:SubTitleTag name="New Medical Certificate">
						<td width="10%" class="tdfonthead">
							<div align="center">
								<img class="button" style="cursor: pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onclick="openPopup('/HISClinical/mrd/medicalCertificate.cnt?hmode=POPUP&unitDiagnosisCodeType='+document.forms[0].unitDiagnosisCodeType.value,event,300,600);">
							</div>
						</td>
					</his:SubTitleTag>
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<logic:equal name="medicalCertificateFB" property="generationMode" value="<%=Config.MEDICAL_CERTIFICATE_GENERATION_MANUAL %>">
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="medCertificateNo"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont" colspan="3">
										<div align="left">
											<html:text name="medicalCertificateFB" property="newMedicalCertificateId" maxlength="14" size="25"></html:text>
										</div>
									</td>
								</tr>
							</logic:equal>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											
											<font color="#FF0000">*</font>
											<bean:message key="sufferingFrom"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:textarea property="newSufferingFrom" name="medicalCertificateFB"  rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event))"></html:textarea>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="diagonosis"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:select name="medicalCertificateFB" property="newDiagnosis" onchange="showForNewDiagnosis()">
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MrdConfig.MC_PATIENT_DIAGNOSIS_LIST%>">
											<html:options collection="<%=MrdConfig.MC_PATIENT_DIAGNOSIS_LIST %>" property="value" labelProperty="label" />
											</logic:present>
										</html:select>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="fromDate"/>
										</font>
									</div>
								</td>
								
								<td width="25%" class="tdfont" >
									<div align="left">
										<logic:equal name="medicalCertificateFB" property="backDatedFlagMC" value="<%=Config.GENERATE_MEDICAL_CERTIFICATE_BACK_DATED_YES %>">
											<html:select name="medicalCertificateFB" property="newFromDate" onchange="showAdviceDays()">
												<html:option value="-1">Select Value</html:option>
												<logic:present name="<%=MrdConfig.MC_PATIENT_ALL_VISIT_EPISODE_VO_ARR%>">
												<html:options collection="<%=MrdConfig.MC_PATIENT_ALL_VISIT_EPISODE_VO_ARR %>" property="episodeDate" labelProperty="episodeDate" />
												</logic:present>
											</html:select>
										</logic:equal>	
										<logic:equal name="medicalCertificateFB" property="backDatedFlagMC" value="<%=Config.GENERATE_MEDICAL_CERTIFICATE_BACK_DATED_NO %>">
											<html:text name="medicalCertificateFB" property="newFromDate" value="<%=toDate %>" readonly="true"></html:text>
										</logic:equal>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="toDate"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" colspan="3">
									<div align="left">
										<logic:empty name="medicalCertificateFB" property="episodeCloseDate">
											<his:date name="newToDate" dateFormate="%d-%b-%Y" value="<%=toDate%>" onchange="showAdviceDays()"/>
										</logic:empty>
										<logic:notEmpty name="medicalCertificateFB" property="episodeCloseDate">
											<his:date name="newToDate" dateFormate="%d-%b-%Y" value="<%=closeDate %>" onchange="showAdviceDays()"></his:date>
										</logic:notEmpty>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="con"/>
											<bean:message key="name"/>
										</font>
									</div>
								</td>
								
									<td width="25%" class="tdfont" >
										<div align="left">
											<logic:equal name="medicalCertificateFB" property="empMappingFlag" value="<%=Config.MEDICAL_CERTIFICATE_EMPLOYEE_MAPPING_OFFLINE %>">
												<html:select name="medicalCertificateFB" property="newEmpNo">
													<html:option value="-1">Select Value</html:option>
													<logic:present name="<%=MrdConfig.MC_LIST_ALL_CONSULTANT%>">
													<html:options collection="<%=MrdConfig.MC_LIST_ALL_CONSULTANT%>" property="value" labelProperty="label" />
													</logic:present>
												</html:select>
											</logic:equal>
											<logic:equal name="medicalCertificateFB" property="empMappingFlag" value="<%=Config.MEDICAL_CERTIFICATE_EMPLOYEE_MAPPING_ONLINE %>">
												<bean:write name="medicalCertificateFB" property="empConsultantName"/>
											</logic:equal>
										</div>
									</td>
								
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="advice"/>
											<bean:message key="day"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<logic:equal name="medicalCertificateFB" property="backDatedFlagMC" value="<%=Config.GENERATE_MEDICAL_CERTIFICATE_BACK_DATED_YES %>">
											<html:text property="newAdviceDays" name="medicalCertificateFB"  maxlength="3" size="5" readonly="true"></html:text>
										</logic:equal>
										<logic:equal name="medicalCertificateFB" property="backDatedFlagMC" value="<%=Config.GENERATE_MEDICAL_CERTIFICATE_BACK_DATED_NO%>">
											<html:text property="newAdviceDays" name="medicalCertificateFB"  maxlength="3" size="5" readonly="true" value="1"></html:text>
										</logic:equal>
									</div>
								</td>
							</tr>
						</table>		
					</his:ContentTag>
				</div>
				
				<div id="restAdviceMC" style="display: <%=restAdviceMC%>">
					<his:SubTitleTag name="Medical Certificate On Rest Advice">
						<td width="10%" class="tdfonthead">
							<div align="center">
								<img class="button" style="cursor: pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onclick="openPopup('/HISClinical/mrd/medicalCertificate.cnt?hmode=POPUP&unitDiagnosisCodeType='+document.forms[0].unitDiagnosisCodeType.value,event,300,600);">
							</div>
						</td>
					</his:SubTitleTag>
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<logic:equal name="medicalCertificateFB" property="generationMode" value="<%=Config.MEDICAL_CERTIFICATE_GENERATION_MANUAL %>">
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="medCertificateNo"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont" colspan="3">
										<div align="left">
											<html:text name="medicalCertificateFB" property="medicalCertificateId" maxlength="14" size="25"></html:text>
										</div>
									</td>
								</tr>
							</logic:equal>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											
											<font color="#FF0000">*</font>
											<bean:message key="sufferingFrom"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:textarea property="sufferingFrom" name="medicalCertificateFB"  rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event))"></html:textarea>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="diagonosis"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:select name="medicalCertificateFB" property="diagnosis" onchange="showDiagnosis()">
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MrdConfig.MC_PATIENT_DIAGNOSIS_LIST%>">
											<html:options collection="<%=MrdConfig.MC_PATIENT_DIAGNOSIS_LIST %>" property="value" labelProperty="label" />
											</logic:present>
										</html:select>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="fromDate"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:text property="fromDate" name="medicalCertificateFB" size="15" readonly="true"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="toDate"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" colspan="3">
									<div align="left">
										<html:text property="toDate" name="medicalCertificateFB"  size="15" readonly="true"></html:text>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="con"/>
											<bean:message key="name"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:text name="medicalCertificateFB" property="consultantName" readonly="true"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="advice"/>
											<bean:message key="day"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:text property="adviceDays" name="medicalCertificateFB"  maxlength="3" size="5" readonly="true"></html:text>
									</div>
								</td>
							</tr>
						</table>	
					
					</his:ContentTag>
				</div>
				
				<div id="modifyMC" style="display: <%=modifyMC%>">
						<bean:define name="medicalCertificateFB" property="modToDate" id="modifyToDate" type="java.lang.String" />
				
						<his:SubTitleTag name="Modify Medical Certificate">
							<td width="10%" class="tdfonthead">
								<div align="center">
									<img class="button" style="cursor: pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onclick="openPopup('/HISClinical/mrd/medicalCertificate.cnt?hmode=POPUP&unitDiagnosisCodeType='+document.forms[0].unitDiagnosisCodeType.value,event,300,600);">
								</div>
							</td>
						</his:SubTitleTag>
						<his:ContentTag>
						 	<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						 		<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												
												<font color="#FF0000">*</font>
												<bean:message key="sufferingFrom"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont" >
										<div align="left">
											<html:textarea property="modSufferingFrom" name="medicalCertificateFB"  rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event))"></html:textarea>
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="diagonosis"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont" >
										<div align="left">
											<html:select name="medicalCertificateFB" property="modDiagnosis" onchange="showForModifyDiagnosis()" tabindex="1">
												<html:option value="-1">Select Value</html:option>
												<logic:present name="<%=MrdConfig.MC_PATIENT_DIAGNOSIS_LIST%>">
												<html:options collection="<%=MrdConfig.MC_PATIENT_DIAGNOSIS_LIST %>" property="value" labelProperty="label" />
												</logic:present>
											</html:select>
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="fromDate"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont" >
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:text name="medicalCertificateFB" property="modFromDate" readonly="true"></html:text>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="toDate"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont" colspan="3">
										<div align="left">
											<his:date name="modToDate" dateFormate="%d-%b-%Y" value="<%=modifyToDate %>" onchange="showModAdviceDays()" />
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="advice"/>
												<bean:message key="day"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont" >
										<div align="left">
											<html:text property="modAdviceDays" name="medicalCertificateFB"  maxlength="3" size="5" readonly="true"></html:text>
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="medCertificateNo"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont" >
										<div align="left">
											<html:text name="medicalCertificateFB" property="medicalCertificateDesc" readonly="true"></html:text>
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="con"/>
												<bean:message key="name"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont" >
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:text name="medicalCertificateFB" property="modConsultantName" readonly="true"></html:text>
											</font>	
										</div>
									</td>
					
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="remarks"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont" >
										<div align="left">
											<html:text property="remarks" name="medicalCertificateFB"  maxlength="100" size="30" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"></html:text>
										</div>
									</td>
								</tr>
						 	</table>
						 </his:ContentTag>
					</div>	
				
				</logic:equal>
				
				<logic:equal name="medicalCertificateFB" property="certificateType" value="<%=MrdConfig.CERTIFICATE_TYPE_FITNESS_CERTIFICATE%>">
					<%PatMedicalDtlVO[] issuePatMCVOFit=(PatMedicalDtlVO[])session.getAttribute(MrdConfig.MC_ISSUED_MEDICAL_CERTIFICATE_ON_BASIS_EPISODE );
					if(issuePatMCVOFit.length>0){
					 %>
						
						<%
						PaginationFB fbPageMC= new PaginationFB();
						pageContext.setAttribute("fbPagination",fbPageMC);
						fbPageMC.setCurrentPage(((MedicalCertificateFB)request.getAttribute("medicalCertificateFB")).getCurrentPage());
						fbPageMC.setObjArrName(MrdConfig.MC_ISSUED_MEDICAL_CERTIFICATE_ON_BASIS_EPISODE);
						fbPageMC.setAppendInTitle("Generated Medical Certificate");
						fbPageMC.setMaxRecords(5);
						%>
						<html:hidden name="medicalCertificateFB" property="currentPage"/>
						<his:PaginationTag name="fbPagination"></his:PaginationTag>
						<his:ContentTag>
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr>
									<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="select"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="18%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="medCertificateNo"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="37%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="sufferingFrom"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="advice"/>
													<bean:message key="day"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="startday"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="endday"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="fitnessDate"/>
												</b>	
											</font>
										</div>
									</td>
								</tr>
								
									<%
									PatMedicalDtlVO[] patMedicalVO=(PatMedicalDtlVO[])session.getAttribute(MrdConfig.MC_ISSUED_MEDICAL_CERTIFICATE_ON_BASIS_EPISODE);
									int startIndexMC = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
									int endIndexMC = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX);
									
									for(int i=startIndexMC; i<=endIndexMC; i++)
									{
										PatMedicalDtlVO patMedVO=patMedicalVO[i];	
										
									%>
									<tr>
										<td class="tdfont" width="5%" >
											<div align="center">
											<%String a=""+Integer.toString(i)+""; %>
											<%if(patMedVO.getFitnessCertificateId()== null){ %>
												<html:radio name="medicalCertificateFB" property="selectedRadioFitness" value="<%=a  %>" onclick="showFitnessDetail(this)"></html:radio>
												<%} %>
											</div>
										</td>
										<td class="tdfont" width="18%" >
											<div align="center">
												<%=patMedVO.getMedicalCertificateName() %>
												<html:hidden name="medicalCertificateFB" property="hiddenMCName" value="<%=patMedVO.getMedicalCertificateName() %>"/>
											</div>
										</td>
										<td class="tdfont" width="37%" >
											<div align="center">
												<%=patMedVO.getSufferingFrom() %>
												<html:hidden name="medicalCertificateFB" property="hiddenSufferingFrom" value="<%=patMedVO.getSufferingFrom() %>"/>
											</div>
										</td>
										<td class="tdfont" width="10%" >
											<div align="center">
												<%=patMedVO.getAdviceDays() %>
												<html:hidden name="medicalCertificateFB" property="hiddenAdviceDay" value="<%=patMedVO.getAdviceDays() %>"/>
											</div>
										</td>
										<td class="tdfont" width="10%" >
											<div align="center">
												<%=patMedVO.getFromDate() %>
												<html:hidden name="medicalCertificateFB" property="hiddenStartDate" value="<%=patMedVO.getFromDate() %>"/>
											</div>
										</td>
										<td class="tdfont" width="10%" >
											<div align="center">
												<%=patMedVO.getToDate() %>
												<html:hidden name="medicalCertificateFB" property="hiddenEndDate" value="<%=patMedVO.getToDate() %>"/>
											</div>
										</td>
										<td class="tdfont" width="10%" >
											<div align="center">
												<%if(patMedVO.getFitnessCertificateId()!= null){ %>
													<%= patMedVO.getFitnessDate()%>  
												<%} else{%>
													-
												<%} %>
											</div>
										</td>
									</tr>
									<html:hidden name="medicalCertificateFB" property="hiddenConsultantName" value="<%=patMedVO.getEmpName() %>"/>
									<html:hidden name="medicalCertificateFB" property="hiddenMCNo" value="<%=patMedVO.getMedicalCertificateId() %>"/>
									<html:hidden name="medicalCertificateFB" property="hiddenFitnessDate" value="<%=patMedVO.getFitnessDate() %>"/>
								<%} %>
							</table>
						</his:ContentTag>	
					<%} %>
					
					<div id="generateFC" style="display: <%=generateFC%>">
						<his:SubTitleTag name="Generate Fitness Certificate">
						</his:SubTitleTag>
						
						<his:ContentTag>
					 	<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					 		<logic:equal name="medicalCertificateFB" property="generationMode" value="<%=Config.MEDICAL_CERTIFICATE_GENERATION_MANUAL %>">
					 			<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="fitnessCertificateNo"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont" colspan="3">
										<div align="left">
											<html:text name="medicalCertificateFB" property="fitnessCertificateId" maxlength="14" size="25"></html:text>
										</div>
									</td>
								</tr>
					 		</logic:equal>
					 			<tr>
					 				<td width="25%" class="tdfonthead">
					 					<div align="right">
					 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 							<bean:message key="sufferingFrom"/>
					 						</font>	
					 					</div>
					 				</td>
					 				<td width="25%" class="tdfont">
					 					<div align="left">
					 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 							<html:textarea name="medicalCertificateFB" property="sufferingFrom" readonly="true" rows="2" cols="35"></html:textarea>
					 						</font>	
					 					</div>
					 				</td>
					 				<td width="25%" class="tdfonthead">
					 					<div align="right">
					 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 							<bean:message key="advice"/>
												<bean:message key="day"/>
					 						</font>	
					 					</div>
					 				</td>
					 				<td width="25%" class="tdfont">
					 					<div align="left">
					 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 							<html:text name="medicalCertificateFB" property="adviceDays" readonly="true" ></html:text>
					 						</font>	
					 					</div>
					 				</td>
					 			</tr>
					 			<tr>
					 				<td width="25%" class="tdfonthead">
					 					<div align="right">
					 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 							<bean:message key="startday"/>
					 						</font>	
					 					</div>
					 				</td>
					 				<td width="25%" class="tdfont">
					 					<div align="left">
					 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 							<html:text name="medicalCertificateFB" property="fromDate" readonly="true" ></html:text>
					 						</font>	
					 					</div>
					 				</td>
					 				<td width="25%" class="tdfonthead">
					 					<div align="right">
					 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 							<bean:message key="endday"/>
					 						</font>	
					 					</div>
					 				</td>
					 				<td width="25%" class="tdfont">
					 					<div align="left">
					 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 							<html:text name="medicalCertificateFB" property="toDate" readonly="true"></html:text>
					 						</font>	
					 					</div>
					 				</td>
					 			</tr>
					 			<tr>
					 				<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="con"/>
												<bean:message key="name"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont" >
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<html:text name="medicalCertificateFB" property="consultantName" readonly="true"></html:text>
										</font>	
									</div>
									</td>
					 				<td width="25%" class="tdfonthead">
					 					<div align="right">
					 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 							<bean:message key="fitnessDate"/>
					 						</font>	
					 					</div>
					 				</td>
					 				<td width="25%" class="tdfont">
					 					<div align="left">
					 						<logic:equal name="medicalCertificateFB" property="backDatedFlagFC" value="<%=Config.GENERATE_FITNESS_CERTIFICATE_BACK_DATED_YES %>">
					 							<html:text name="medicalCertificateFB" property="fitnessDate" readonly="true"></html:text>
					 						</logic:equal>
					 						<logic:equal name="medicalCertificateFB" property="backDatedFlagFC" value="<%=Config.GENERATE_FITNESS_CERTIFICATE_BACK_DATED_NO %>">
					 							<html:text name="medicalCertificateFB" property="fitnessDt" readonly="true" value="<%=toDate %>"></html:text>
					 						</logic:equal>
					 					</div>
					 				</td>
					 			</tr>
					 	</table>
					 </his:ContentTag>
					</div>	
					
				</logic:equal>
				
			</his:statusTransactionInProcess>
			
			<his:ButtonToolBarTag>
				
				<his:statusTransactionInProcess>
					<logic:equal name="medicalCertificateFB" property="certificateType" value="<%=MrdConfig.CERTIFICATE_TYPE_MEDICAL_CERTIFICATE%>">
				
					<%EpisodeRestAdviceVO[] arrEpiRestAdvice1=(EpisodeRestAdviceVO[])session.getAttribute(MrdConfig.MC_PATIENT_EPI_REST_ADVICE_VO_BY_EPISODE_ARR);
					if(arrEpiRestAdvice1.length>0){
				 	%>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if( validateSaveForMC()) submitPage('SAVEMC') " onkeypress="if(event.keyCode==13)if(validateSaveForMC())submitPage('SAVEMC')">
					<%}else{ %>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if( validateSaveForMCNew()) submitPage('SAVEMCNEW') " onkeypress="if(event.keyCode==13)if(validateSaveForMCNew())submitPage('SAVEMCNEW')">
					<%} %>
					</logic:equal>
					<logic:equal name="medicalCertificateFB" property="certificateType" value="<%=MrdConfig.CERTIFICATE_TYPE_FITNESS_CERTIFICATE%>">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if( validateSaveForFC()) submitPage('SAVEFC') " onkeypress="if(event.keyCode==13)if(validateSaveForFC())submitPage('SAVEFC')">
					</logic:equal>
					
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
				</his:statusTransactionInProcess>		
				<his:statusList>		
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
						
				</his:statusList>
				<his:statusNew>		
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
				</his:statusNew>
				<his:statusUnsuccessfull>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
				</his:statusUnsuccessfull>
				<his:statusDone>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
				</his:statusDone>
			</his:ButtonToolBarTag>
		</his:TransactionContainer>

	<html:hidden name="medicalCertificateFB" property="hmode" />
	<html:hidden name="medicalCertificateFB" property="patCrNo"/>
	<html:hidden name="medicalCertificateFB" property="selectedEpiCode"/>
	<html:hidden name="medicalCertificateFB" property="empNo"/>
	<html:hidden name="medicalCertificateFB" property="selectedRest"/>
	<html:hidden name="medicalCertificateFB" property="newAdvDays"/>
	<html:hidden name="medicalCertificateFB" property="tempMode"/>
	<html:hidden name="medicalCertificateFB" property="generationMode"/>
	<html:hidden name="medicalCertificateFB" property="deptUnitCode"/>
	<html:hidden name="medicalCertificateFB" property="index"/>
	<html:hidden name="medicalCertificateFB" property="modFromDate"/>
	<html:hidden name="medicalCertificateFB" property="unitDiagnosisCodeType"/>
	<html:hidden name="medicalCertificateFB" property="numberOfRow"/>
	<html:hidden name="medicalCertificateFB" property="certificateType"/>
	<html:hidden name="medicalCertificateFB" property="flagForMCSave"/>
	<html:hidden name="medicalCertificateFB" property="sysDate" value="<%=toDate%>"/>
	<html:hidden name="medicalCertificateFB" property="modifyIndex"/>
	<html:hidden name="medicalCertificateFB" property="backDatedFlagMC"/>
	<html:hidden name="medicalCertificateFB" property="backDatedFlagFC"/>
	<html:hidden name="medicalCertificateFB" property="extendFlag"/>
	<html:hidden name="medicalCertificateFB" property="empMappingFlag"/>
	<html:hidden name="medicalCertificateFB" property="empConsultantName"/>
	<html:hidden name="medicalCertificateFB" property="maxDaysMC"/>
	<html:hidden name="medicalCertificateFB" property="onlineEmpMaxDays"/>
	<html:hidden name="medicalCertificateFB" property="episodeCloseDate"/>
	<html:hidden name="medicalCertificateFB" property="extendedAdviceDays"/>
	<html:hidden name="medicalCertificateFB" property="modMaxDays"/>
	
	</html:form>
	<his:status/>

</body>


</html>