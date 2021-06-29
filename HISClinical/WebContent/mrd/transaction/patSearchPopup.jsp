
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<%@page import="mrd.MrdConfig"%>

<script type="text/javascript">

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function closeForm()
{
	self.close();
}

function validateSearch(mode)
{
	if(document.getElementsByName("searchType")[0].checked==false && document.getElementsByName("searchType")[1].checked==false && document.getElementsByName("searchType")[2].checked==false && document.getElementsByName("searchType")[3].checked==false && document.getElementsByName("searchType")[4].checked==false)
	{
		alert("Please select search type");
		document.getElementsByName("searchType")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("searchType")[3].checked)
	{
		if(document.getElementsByName("str_mlc_no")[0].value=="")
		{
			alert("Please enter MLC No");
			document.getElementsByName("str_mlc_no")[0].focus();
			return false;
		}
	}
	if(document.getElementsByName("searchType")[4].checked)
	{
		if(document.getElementsByName("str_postmortem_no")[0].value=="")
		{
			alert("Please enter postMortem No");
			document.getElementsByName("str_postmortem_no")[0].focus();
			return false;
		}
	}
	
	if(document.getElementsByName("searchType")[2].checked)
	{
		if(document.getElementsByName("str_admission_no")[0].value=="")
		{
			alert("Please enter Admision No");
			document.getElementsByName("str_admission_no")[0].focus();
			return false;
		}
	}
	
	if(document.getElementsByName("searchType")[1].checked)
	{
		if(document.getElementsByName("str_pat_crno")[0].value=="")
		{
			alert("Please enter patient CR No");
			document.getElementsByName("str_pat_crno")[0].focus();
			return false;
		}
	}
	
	if(document.getElementsByName("searchType")[0].checked)
	{
		if(document.getElementsByName("str_first_name")[0].value=="" && document.getElementsByName("str_middle_name")[0].value=="" && document.getElementsByName("str_last_name")[0].value=="")
		{
			alert("Please enter patient name");
			document.getElementsByName("str_first_name")[0].focus();
			return false;
		}
	}
	
	submitPage(mode);
	
}

function clearForm()
{
	document.getElementsByName("str_first_name")[0].value="";
	document.getElementsByName("str_middle_name")[0].value="";
	document.getElementsByName("str_last_name")[0].value="";
	document.getElementsByName("str_pat_crno")[0].value="";
	document.getElementsByName("str_postmortem_no")[0].value="";
	document.getElementsByName("str_mlc_no")[0].value="";
	document.getElementsByName("str_admission_no")[0].value="";
}


function getPatientDtl()
{
	
	//alert("search type "+document.getElementsByName("searchType")[0].value);
	
	if(document.getElementsByName("searchType")[3].checked)
	{
		var len=document.getElementsByName("mlcNoArray").length;
		
		for(var i=0;i<len;i++)
		{
			if(document.getElementsByName("mlcNoArray")[i].checked)
			{
				opener.document.getElementsByName("patName")[0].value=document.getElementsByName("patNameArray")[i].value;
				opener.document.getElementsByName("fatherName")[0].value=document.getElementsByName("fatherNameArray")[i].value;
				opener.document.getElementsByName("spouseName")[0].value=document.getElementsByName("spouseNameArray")[i].value;
				opener.document.getElementsByName("motherName")[0].value=document.getElementsByName("motherNameArray")[i].value;
				opener.document.getElementsByName("patGender")[0].value=document.getElementsByName("patGenderArray")[i].value;
				opener.document.getElementsByName("age")[0].value=document.getElementsByName("patAgeArray")[i].value;
				opener.document.getElementsByName("patAddress")[0].value=document.getElementsByName("patAddressArray")[i].value;
				opener.document.getElementsByName("patCrNo")[0].value=document.getElementsByName("crNoArray")[i].value;
				opener.document.getElementsByName("postmortemId")[0].value=document.getElementsByName("postMortemNoArray")[i].value;
				opener.document.getElementsByName("MLCNo")[0].value=document.getElementsByName("mlcNoArray")[i].value;
				opener.document.getElementsByName("patGenderCode")[0].value=document.getElementsByName("patGenderCodeArray")[i].value;
				opener.document.getElementsByName("patDOB")[0].value=document.getElementsByName("patDOBArray")[i].value;
				opener.document.getElementsByName("episodeCode")[0].value=document.getElementsByName("episodeCodeArray")[i].value;
				opener.document.getElementsByName("episodeVisitNo")[0].value=document.getElementsByName("episodeVisitNoArray")[i].value;
				opener.document.getElementsByName("patAdmNo")[0].value=document.getElementsByName("patAdmissionNoArray")[i].value;
			}
		}
	}
	
	if(document.getElementsByName("searchType")[4].checked)
	{
		var len=document.getElementsByName("postMortemNoArray").length;
		
		for(var i=0;i<len;i++)
		{
			if(document.getElementsByName("postMortemNoArray")[i].checked)
			{
				opener.document.getElementsByName("patName")[0].value=document.getElementsByName("patNameArray")[i].value;
				opener.document.getElementsByName("fatherName")[0].value=document.getElementsByName("fatherNameArray")[i].value;
				opener.document.getElementsByName("spouseName")[0].value=document.getElementsByName("spouseNameArray")[i].value;
				opener.document.getElementsByName("motherName")[0].value=document.getElementsByName("motherNameArray")[i].value;
				opener.document.getElementsByName("patGender")[0].value=document.getElementsByName("patGenderArray")[i].value;
				opener.document.getElementsByName("age")[0].value=document.getElementsByName("patAgeArray")[i].value;
				opener.document.getElementsByName("patAddress")[0].value=document.getElementsByName("patAddressArray")[i].value;
				opener.document.getElementsByName("patCrNo")[0].value=document.getElementsByName("crNoArray")[i].value;
				opener.document.getElementsByName("postmortemId")[0].value=document.getElementsByName("postMortemNoArray")[i].value;
				opener.document.getElementsByName("MLCNo")[0].value=document.getElementsByName("mlcNoArray")[i].value;
				opener.document.getElementsByName("patGenderCode")[0].value=document.getElementsByName("patGenderCodeArray")[i].value;
				opener.document.getElementsByName("patDOB")[0].value=document.getElementsByName("patDOBArray")[i].value;
				opener.document.getElementsByName("episodeCode")[0].value=document.getElementsByName("episodeCodeArray")[i].value;
				opener.document.getElementsByName("episodeVisitNo")[0].value=document.getElementsByName("episodeVisitNoArray")[i].value;
				opener.document.getElementsByName("patAdmNo")[0].value=document.getElementsByName("patAdmissionNoArray")[i].value;
			}
		}
	}
	
	if(document.getElementsByName("searchType")[1].checked)
	{
			var len=document.getElementsByName("mlc_flagArray").length;
			
			for(var i=0;i<len;i++)
			{
				if(document.getElementsByName("mlc_flagArray")[i].checked)
				{
					
					opener.document.getElementsByName("patName")[0].value=document.getElementsByName("mlc_patNameArray")[i].value;
					opener.document.getElementsByName("fatherName")[0].value=document.getElementsByName("mlc_fatherNameArray")[i].value;
					opener.document.getElementsByName("spouseName")[0].value=document.getElementsByName("mlc_spouseNameArray")[i].value;
					opener.document.getElementsByName("motherName")[0].value=document.getElementsByName("mlc_motherNameArray")[i].value;
					opener.document.getElementsByName("patGender")[0].value=document.getElementsByName("mlc_patGenderArray")[i].value;
					opener.document.getElementsByName("age")[0].value=document.getElementsByName("mlc_patAgeArray")[i].value;
					opener.document.getElementsByName("patAddress")[0].value=document.getElementsByName("mlc_patAddressArray")[i].value;
					opener.document.getElementsByName("patCrNo")[0].value=document.getElementsByName("mlc_crNoArray")[i].value;
					opener.document.getElementsByName("postmortemId")[0].value=document.getElementsByName("mlc_postMortemNoArray")[i].value;
					opener.document.getElementsByName("MLCNo")[0].value=document.getElementsByName("mlc_mlcNoArray")[i].value;
					opener.document.getElementsByName("patGenderCode")[0].value=document.getElementsByName("mlc_patGenderCodeArray")[i].value;
					opener.document.getElementsByName("patDOB")[0].value=document.getElementsByName("mlc_patDOBArray")[i].value;
					opener.document.getElementsByName("episodeCode")[0].value=document.getElementsByName("mlc_episodeCodeArray")[i].value;
					opener.document.getElementsByName("episodeVisitNo")[0].value=document.getElementsByName("mlc_episodeVisitNoArray")[i].value;
					opener.document.getElementsByName("patAdmNo")[0].value=document.getElementsByName("mlc_admissionNoArray")[i].value;
				}
			}
			var len1=document.getElementsByName("postMortem_flagArray").length;
			
			for(var i=0;i<len1;i++)
			{
				if(document.getElementsByName("postMortem_flagArray")[i].checked)
				{
					
					opener.document.getElementsByName("patName")[0].value=document.getElementsByName("patNameArray")[i].value;
					opener.document.getElementsByName("fatherName")[0].value=document.getElementsByName("fatherNameArray")[i].value;
					opener.document.getElementsByName("spouseName")[0].value=document.getElementsByName("spouseNameArray")[i].value;
					opener.document.getElementsByName("motherName")[0].value=document.getElementsByName("motherNameArray")[i].value;
					opener.document.getElementsByName("patGender")[0].value=document.getElementsByName("patGenderArray")[i].value;
					opener.document.getElementsByName("age")[0].value=document.getElementsByName("patAgeArray")[i].value;
					opener.document.getElementsByName("patAddress")[0].value=document.getElementsByName("patAddressArray")[i].value;
					opener.document.getElementsByName("patCrNo")[0].value=document.getElementsByName("crNoArray")[i].value;
					opener.document.getElementsByName("postmortemId")[0].value=document.getElementsByName("postMortemNoArray")[i].value;
					opener.document.getElementsByName("MLCNo")[0].value=document.getElementsByName("mlcNoArray")[i].value;
					opener.document.getElementsByName("patGenderCode")[0].value=document.getElementsByName("patGenderCodeArray")[i].value;
					opener.document.getElementsByName("patDOB")[0].value=document.getElementsByName("patDOBArray")[i].value;
					opener.document.getElementsByName("episodeCode")[0].value=document.getElementsByName("episodeCodeArray")[i].value;
					opener.document.getElementsByName("episodeVisitNo")[0].value=document.getElementsByName("episodeVisitNoArray")[i].value;
					opener.document.getElementsByName("patAdmNo")[0].value=document.getElementsByName("patAdmissionNoArray")[i].value;
				}
			}
			
			var len2=document.getElementsByName("admission_flagArray").length;
			for(var i=0;i<len2;i++)
			{
				if(document.getElementsByName("admission_flagArray")[i].checked)
				{
					opener.document.getElementsByName("patName")[0].value=document.getElementsByName("adm_patNameArray")[i].value;
					opener.document.getElementsByName("fatherName")[0].value=document.getElementsByName("adm_fatherNameArray")[i].value;
					opener.document.getElementsByName("spouseName")[0].value=document.getElementsByName("adm_spouseNameArray")[i].value;
					opener.document.getElementsByName("motherName")[0].value=document.getElementsByName("adm_motherNameArray")[i].value;
					opener.document.getElementsByName("patGender")[0].value=document.getElementsByName("adm_patGenderArray")[i].value;
					opener.document.getElementsByName("age")[0].value=document.getElementsByName("adm_patAgeArray")[i].value;
					opener.document.getElementsByName("patAddress")[0].value=document.getElementsByName("adm_patAddressArray")[i].value;
					opener.document.getElementsByName("patCrNo")[0].value=document.getElementsByName("adm_crNoArray")[i].value;
					opener.document.getElementsByName("postmortemId")[0].value=document.getElementsByName("adm_postMortemNoArray")[i].value;
					opener.document.getElementsByName("MLCNo")[0].value=document.getElementsByName("adm_mlcNoArray")[i].value;
					opener.document.getElementsByName("patAdmNo")[0].value=document.getElementsByName("adm_patAdmNoArray")[i].value;
				}
			}
	}
	
	if(document.getElementsByName("searchType")[0].checked)
	{
			var len=document.getElementsByName("mlc_flagArray").length;
			for(var i=0;i<len;i++)
			{
				if(document.getElementsByName("mlc_flagArray")[i].checked)
				{
					opener.document.getElementsByName("patName")[0].value=document.getElementsByName("mlc_patNameArray")[i].value;
					opener.document.getElementsByName("fatherName")[0].value=document.getElementsByName("mlc_fatherNameArray")[i].value;
					opener.document.getElementsByName("spouseName")[0].value=document.getElementsByName("mlc_spouseNameArray")[i].value;
					opener.document.getElementsByName("motherName")[0].value=document.getElementsByName("mlc_motherNameArray")[i].value;
					opener.document.getElementsByName("patGender")[0].value=document.getElementsByName("mlc_patGenderArray")[i].value;
					opener.document.getElementsByName("age")[0].value=document.getElementsByName("mlc_patAgeArray")[i].value;
					opener.document.getElementsByName("patAddress")[0].value=document.getElementsByName("mlc_patAddressArray")[i].value;
					opener.document.getElementsByName("patCrNo")[0].value=document.getElementsByName("mlc_crNoArray")[i].value;
					opener.document.getElementsByName("postmortemId")[0].value=document.getElementsByName("mlc_postMortemNoArray")[i].value;
					opener.document.getElementsByName("MLCNo")[0].value=document.getElementsByName("mlc_mlcNoArray")[i].value;
					opener.document.getElementsByName("patGenderCode")[0].value=document.getElementsByName("mlc_patGenderCodeArray")[i].value;
					opener.document.getElementsByName("patDOB")[0].value=document.getElementsByName("mlc_patDOBArray")[i].value;
					opener.document.getElementsByName("episodeCode")[0].value=document.getElementsByName("mlc_episodeCodeArray")[i].value;
					opener.document.getElementsByName("episodeVisitNo")[0].value=document.getElementsByName("mlc_episodeVisitNoArray")[i].value;
					opener.document.getElementsByName("patAdmNo")[0].value=document.getElementsByName("mlc_admissionNoArray")[i].value;
				}
			}
			
			var len1=document.getElementsByName("postMortem_flagArray").length;
			for(var i=0;i<len1;i++)
			{
				if(document.getElementsByName("postMortem_flagArray")[i].checked)
				{
					opener.document.getElementsByName("patName")[0].value=document.getElementsByName("patNameArray")[i].value;
					opener.document.getElementsByName("fatherName")[0].value=document.getElementsByName("fatherNameArray")[i].value;
					opener.document.getElementsByName("spouseName")[0].value=document.getElementsByName("spouseNameArray")[i].value;
					opener.document.getElementsByName("motherName")[0].value=document.getElementsByName("motherNameArray")[i].value;
					opener.document.getElementsByName("patGender")[0].value=document.getElementsByName("patGenderArray")[i].value;
					opener.document.getElementsByName("age")[0].value=document.getElementsByName("patAgeArray")[i].value;
					opener.document.getElementsByName("patAddress")[0].value=document.getElementsByName("patAddressArray")[i].value;
					opener.document.getElementsByName("patCrNo")[0].value=document.getElementsByName("crNoArray")[i].value;
					opener.document.getElementsByName("postmortemId")[0].value=document.getElementsByName("postMortemNoArray")[i].value;
					opener.document.getElementsByName("MLCNo")[0].value=document.getElementsByName("mlcNoArray")[i].value;
					opener.document.getElementsByName("patGenderCode")[0].value=document.getElementsByName("patGenderCodeArray")[i].value;
					opener.document.getElementsByName("patDOB")[0].value=document.getElementsByName("patDOBArray")[i].value;
					opener.document.getElementsByName("episodeCode")[0].value=document.getElementsByName("episodeCodeArray")[i].value;
					opener.document.getElementsByName("episodeVisitNo")[0].value=document.getElementsByName("episodeVisitNoArray")[i].value;
					opener.document.getElementsByName("patAdmNo")[0].value=document.getElementsByName("patAdmissionNoArray")[i].value;
				}
			}
			
			var len2=document.getElementsByName("admission_flagArray").length;
			for(var i=0;i<len2;i++)
			{
				if(document.getElementsByName("admission_flagArray")[i].checked)
				{
					opener.document.getElementsByName("patName")[0].value=document.getElementsByName("adm_patNameArray")[i].value;
					opener.document.getElementsByName("fatherName")[0].value=document.getElementsByName("adm_fatherNameArray")[i].value;
					opener.document.getElementsByName("spouseName")[0].value=document.getElementsByName("adm_spouseNameArray")[i].value;
					opener.document.getElementsByName("motherName")[0].value=document.getElementsByName("adm_motherNameArray")[i].value;
					opener.document.getElementsByName("patGender")[0].value=document.getElementsByName("adm_patGenderArray")[i].value;
					opener.document.getElementsByName("age")[0].value=document.getElementsByName("adm_patAgeArray")[i].value;
					opener.document.getElementsByName("patAddress")[0].value=document.getElementsByName("adm_patAddressArray")[i].value;
					opener.document.getElementsByName("patCrNo")[0].value=document.getElementsByName("adm_crNoArray")[i].value;
					opener.document.getElementsByName("postmortemId")[0].value=document.getElementsByName("adm_postMortemNoArray")[i].value;
					opener.document.getElementsByName("MLCNo")[0].value=document.getElementsByName("adm_mlcNoArray")[i].value;
					opener.document.getElementsByName("patAdmNo")[0].value=document.getElementsByName("adm_patAdmNoArray")[i].value;
				}
			}
	}
	
	if(document.getElementsByName("searchType")[2].checked)
	{
		var len2=document.getElementsByName("admission_flagArray").length;
			for(var i=0;i<len2;i++)
			{
				if(document.getElementsByName("admission_flagArray")[i].checked)
				{
					opener.document.getElementsByName("patName")[0].value=document.getElementsByName("adm_patNameArray")[i].value;
					opener.document.getElementsByName("fatherName")[0].value=document.getElementsByName("adm_fatherNameArray")[i].value;
					opener.document.getElementsByName("spouseName")[0].value=document.getElementsByName("adm_spouseNameArray")[i].value;
					opener.document.getElementsByName("motherName")[0].value=document.getElementsByName("adm_motherNameArray")[i].value;
					opener.document.getElementsByName("patGender")[0].value=document.getElementsByName("adm_patGenderArray")[i].value;
					opener.document.getElementsByName("age")[0].value=document.getElementsByName("adm_patAgeArray")[i].value;
					opener.document.getElementsByName("patAddress")[0].value=document.getElementsByName("adm_patAddressArray")[i].value;
					opener.document.getElementsByName("patCrNo")[0].value=document.getElementsByName("adm_crNoArray")[i].value;
					opener.document.getElementsByName("postmortemId")[0].value=document.getElementsByName("adm_postMortemNoArray")[i].value;
					opener.document.getElementsByName("MLCNo")[0].value=document.getElementsByName("adm_mlcNoArray")[i].value;
					opener.document.getElementsByName("patAdmNo")[0].value=document.getElementsByName("adm_patAdmNoArray")[i].value;
				}
			}
			
			/*
			var len1=document.getElementsByName("postMortem_flagArray").length;
			for(var i=0;i<len1;i++)
			{
				if(document.getElementsByName("postMortem_flagArray")[i].checked)
				{
					opener.document.getElementsByName("patName")[0].value=document.getElementsByName("patNameArray")[i].value;
					opener.document.getElementsByName("fatherName")[0].value=document.getElementsByName("fatherNameArray")[i].value;
					opener.document.getElementsByName("spouseName")[0].value=document.getElementsByName("spouseNameArray")[i].value;
					opener.document.getElementsByName("motherName")[0].value=document.getElementsByName("motherNameArray")[i].value;
					opener.document.getElementsByName("patGender")[0].value=document.getElementsByName("patGenderArray")[i].value;
					opener.document.getElementsByName("age")[0].value=document.getElementsByName("patAgeArray")[i].value;
					opener.document.getElementsByName("patAddress")[0].value=document.getElementsByName("patAddressArray")[i].value;
					opener.document.getElementsByName("patCrNo")[0].value=document.getElementsByName("crNoArray")[i].value;
					opener.document.getElementsByName("postmortemId")[0].value=document.getElementsByName("postMortemNoArray")[i].value;
					opener.document.getElementsByName("MLCNo")[0].value=document.getElementsByName("mlcNoArray")[i].value;
					opener.document.getElementsByName("patGenderCode")[0].value=document.getElementsByName("patGenderCodeArray")[i].value;
					opener.document.getElementsByName("patDOB")[0].value=document.getElementsByName("patDOBArray")[i].value;
					opener.document.getElementsByName("episodeCode")[0].value=document.getElementsByName("episodeCodeArray")[i].value;
					opener.document.getElementsByName("episodeVisitNo")[0].value=document.getElementsByName("episodeVisitNoArray")[i].value;
					opener.document.getElementsByName("patAdmNo")[0].value=document.getElementsByName("patAdmissionNoArray")[i].value;
				}
			}
			
			var len=document.getElementsByName("mlc_flagArray").length;
			for(var i=0;i<len;i++)
			{
				if(document.getElementsByName("mlc_flagArray")[i].checked)
				{
					opener.document.getElementsByName("patName")[0].value=document.getElementsByName("mlc_patNameArray")[i].value;
					opener.document.getElementsByName("fatherName")[0].value=document.getElementsByName("mlc_fatherNameArray")[i].value;
					opener.document.getElementsByName("spouseName")[0].value=document.getElementsByName("mlc_spouseNameArray")[i].value;
					opener.document.getElementsByName("motherName")[0].value=document.getElementsByName("mlc_motherNameArray")[i].value;
					opener.document.getElementsByName("patGender")[0].value=document.getElementsByName("mlc_patGenderArray")[i].value;
					opener.document.getElementsByName("age")[0].value=document.getElementsByName("mlc_patAgeArray")[i].value;
					opener.document.getElementsByName("patAddress")[0].value=document.getElementsByName("mlc_patAddressArray")[i].value;
					opener.document.getElementsByName("patCrNo")[0].value=document.getElementsByName("mlc_crNoArray")[i].value;
					opener.document.getElementsByName("postmortemId")[0].value=document.getElementsByName("mlc_postMortemNoArray")[i].value;
					opener.document.getElementsByName("MLCNo")[0].value=document.getElementsByName("mlc_mlcNoArray")[i].value;
					opener.document.getElementsByName("patGenderCode")[0].value=document.getElementsByName("mlc_patGenderCodeArray")[i].value;
					opener.document.getElementsByName("patDOB")[0].value=document.getElementsByName("mlc_patDOBArray")[i].value;
					opener.document.getElementsByName("episodeCode")[0].value=document.getElementsByName("mlc_episodeCodeArray")[i].value;
					opener.document.getElementsByName("episodeVisitNo")[0].value=document.getElementsByName("mlc_episodeVisitNoArray")[i].value;
					opener.document.getElementsByName("patAdmNo")[0].value=document.getElementsByName("mlc_admissionNoArray")[i].value;
				}
			}
			*/
	}
		
	closeForm();
}

function refereshFieldValues()
{
	document.getElementsByName("patNameArray")[0].value=null;
	document.getElementsByName("fatherNameArray")[0].value=null;
	document.getElementsByName("spouseNameArray")[0].value=null;
	document.getElementsByName("motherNameArray")[0].value=null;
	document.getElementsByName("patGenderArray")[0].value=null;
	document.getElementsByName("patAddressArray")[0].value=null;
	document.getElementsByName("crNoArray")[0].value=null;
	document.getElementsByName("postMortemNoArray")[0].value=null;
	document.getElementsByName("mlcNoArray")[0].value=null;
	document.getElementsByName("patGenderCodeArray")[0].value=null;
	document.getElementsByName("patDOBArray")[0].value=null;
	document.getElementsByName("episodeCodeArray")[0].value=null;
	document.getElementsByName("episodeVisitNoArray")[0].value=null;
	document.getElementsByName("patAdmissionNoArray")[0].value=null;
}


function showDetail()
{
	//refereshFieldValues();
	
	if(document.getElementsByName("searchType")[3].checked)
	{
		//submitPage('SEARCHPAT');
		document.getElementsByName("str_mlc_no")[0].disabled=false;
		document.getElementsByName("str_mlc_no")[0].focus();
		
		document.getElementsByName("str_first_name")[0].value="";
		document.getElementsByName("str_middle_name")[0].value="";
		document.getElementsByName("str_last_name")[0].value="";
		document.getElementsByName("str_pat_crno")[0].value="";
		document.getElementsByName("str_postmortem_no")[0].value="";
		document.getElementsByName("str_admission_no")[0].value="";
		
		document.getElementsByName("str_first_name")[0].disabled=true;
		document.getElementsByName("str_middle_name")[0].disabled=true;
		document.getElementsByName("str_last_name")[0].disabled=true;
		document.getElementsByName("str_pat_crno")[0].disabled=true;
		document.getElementsByName("str_postmortem_no")[0].disabled=true;
		document.getElementsByName("str_admission_no")[0].disabled=true;
		
	}
	
	if(document.getElementsByName("searchType")[4].checked)
	{
		//submitPage('SEARCHPAT');
		document.getElementsByName("str_postmortem_no")[0].disabled=false;
		document.getElementsByName("str_postmortem_no")[0].focus();
		
		document.getElementsByName("str_first_name")[0].value="";
		document.getElementsByName("str_middle_name")[0].value="";
		document.getElementsByName("str_last_name")[0].value="";
		document.getElementsByName("str_pat_crno")[0].value="";
		document.getElementsByName("str_mlc_no")[0].value="";
		document.getElementsByName("str_admission_no")[0].value="";
		
		document.getElementsByName("str_first_name")[0].disabled=true;
		document.getElementsByName("str_middle_name")[0].disabled=true;
		document.getElementsByName("str_last_name")[0].disabled=true;
		document.getElementsByName("str_pat_crno")[0].disabled=true;
		document.getElementsByName("str_mlc_no")[0].disabled=true;
		document.getElementsByName("str_admission_no")[0].disabled=true;
		
	}
	
	if(document.getElementsByName("searchType")[1].checked)
	{
		//submitPage('SEARCHPAT');
		document.getElementsByName("str_pat_crno")[0].disabled=false;
		document.getElementsByName("str_pat_crno")[0].focus();
		
		document.getElementsByName("str_first_name")[0].value="";
		document.getElementsByName("str_middle_name")[0].value="";
		document.getElementsByName("str_last_name")[0].value="";
		document.getElementsByName("str_postmortem_no")[0].value="";
		document.getElementsByName("str_mlc_no")[0].value="";
		document.getElementsByName("str_admission_no")[0].value="";
		
		document.getElementsByName("str_first_name")[0].disabled=true;
		document.getElementsByName("str_middle_name")[0].disabled=true;
		document.getElementsByName("str_last_name")[0].disabled=true;
		document.getElementsByName("str_postmortem_no")[0].disabled=true;
		document.getElementsByName("str_mlc_no")[0].disabled=true;
		document.getElementsByName("str_admission_no")[0].disabled=true;
		
	}
	
	if(document.getElementsByName("searchType")[0].checked)
	{
		//submitPage('SEARCHPAT');
		document.getElementsByName("str_first_name")[0].disabled=false;
		document.getElementsByName("str_middle_name")[0].disabled=false;
		document.getElementsByName("str_last_name")[0].disabled=false;
		document.getElementsByName("str_first_name")[0].focus();
		
		document.getElementsByName("str_pat_crno")[0].value="";
		document.getElementsByName("str_postmortem_no")[0].value="";
		document.getElementsByName("str_mlc_no")[0].value="";
		document.getElementsByName("str_admission_no")[0].value="";
		
		document.getElementsByName("str_pat_crno")[0].disabled=true;
		document.getElementsByName("str_postmortem_no")[0].disabled=true;
		document.getElementsByName("str_mlc_no")[0].disabled=true;
		document.getElementsByName("str_admission_no")[0].disabled=true;
		
	}
	
	if(document.getElementsByName("searchType")[2].checked)
	{
		//submitPage('SEARCHPAT');
		document.getElementsByName("str_admission_no")[0].disabled=false;
		document.getElementsByName("str_admission_no")[0].focus();		
				
		document.getElementsByName("str_first_name")[0].value="";
		document.getElementsByName("str_middle_name")[0].value="";
		document.getElementsByName("str_last_name")[0].value="";
		document.getElementsByName("str_pat_crno")[0].value="";
		document.getElementsByName("str_postmortem_no")[0].value="";
		document.getElementsByName("str_mlc_no")[0].value="";
		
		document.getElementsByName("str_first_name")[0].disabled=true;
		document.getElementsByName("str_middle_name")[0].disabled=true;
		document.getElementsByName("str_last_name")[0].disabled=true;
		document.getElementsByName("str_pat_crno")[0].disabled=true;
		document.getElementsByName("str_postmortem_no")[0].disabled=true;
		document.getElementsByName("str_mlc_no")[0].disabled=true;
		
	}
}
 
</script>
<body  onload="showDetail()">
		

<his:TransactionContainer>
		<html:form action="/computerizedSummonDetail">
		
		<his:TitleTag name="Patient Search">
		</his:TitleTag>
		<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="1">
		<tr>
			<td width="25%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="selectseacrhType"/></b></font></div>
	  		</td>
	  		<td width="75%" class="tdfont">
	  			<div align="left">
	  				Name<html:radio property="searchType" value="<%=MrdConfig.PAT_SEARCH_BY_NAME %>" onclick="showDetail()"></html:radio>
					CR No<html:radio property="searchType" value="<%=MrdConfig.PAT_SERACH_BY_CRNO %>" onclick="showDetail()"></html:radio>
					Admission No<html:radio property="searchType" value="<%=MrdConfig.PAT_SERACH_BY_ADMISSION_NO %>" onclick="showDetail()"></html:radio>
					MLC No<html:radio property="searchType" value="<%=MrdConfig.PAT_SEARCH_BY_MLCNO %>" onclick="showDetail()"></html:radio>
					Post Mortem No<html:radio property="searchType" value="<%=MrdConfig.PAT_SEARCH_BY_POSTMARTEM_NO %>" onclick="showDetail()"></html:radio>
				</div>
	  		</td>		
		</tr>
		</table>	
		<table  width="100%" border="0" cellspacing="1" cellpadding="1">
		<tr>
			<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="fname"/></b></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
				<html:text name="SummonDtlFB"  maxlength="32" size="20" property="str_first_name"  tabindex="1" onkeypress="return if(validateAlphabetsOnly(event,this)) " disabled="true"/>
	  			</div>
	  		</td>
	  		<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="mname"/></b></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
				<html:text name="SummonDtlFB"  maxlength="32" size="20" property="str_middle_name"  tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" disabled="true"/>
	  			</div>
	  		</td>
	  		<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="lname"/></b></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
				<html:text name="SummonDtlFB"  maxlength="32" size="20" property="str_last_name"  tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" disabled="true"/>
	  			</div>
	  		</td>
	  		
	  	</tr>
		<tr>
			<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="crNo"/></b></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
				<html:text name="SummonDtlFB"  maxlength="32" size="20" property="str_pat_crno"  tabindex="1" onkeypress="return validateNumeric(event)" disabled="true" />
	  			</div>
	  		</td>
	  		<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="adimissionNo"/></b></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
				<html:text name="SummonDtlFB"  maxlength="32" size="20" property="str_admission_no"  tabindex="1" onkeypress="return validateNumeric(event)" disabled="true"/>
	  			</div>
	  		</td>
			<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="mlcNo"/></b></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
				<html:text name="SummonDtlFB"  maxlength="32" size="20" property="str_mlc_no"  tabindex="1" onkeypress="return validateAlphaNumOnly(this,event)" disabled="true"/>
	  			</div>
	  		</td>
	  	</tr>
		
	  	<tr>
	  		<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="postMortemNo"/></b></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
				<html:text name="SummonDtlFB"  maxlength="32" size="20" property="str_postmortem_no"  tabindex="1" onkeypress="return validateNumeric(event)" disabled="true"/>
	  			</div>
	  		</td>
	  		<td width="17%"  class="tdfonthead">
	  		</td>
	  		<td width="17%" class="tdfont">
	  		</td>
	  		<td width="17%"  class="tdfonthead">
	  		</td>
	  		<td width="17%" class="tdfont">
	  		</td>
	  	</tr>
	  	</table>
		</his:ContentTag>
		
		<his:ButtonToolBarTag>
	 		<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" tabindex="1" onclick="validateSearch('SEARCH');" onkeypress="if(event.keyCode==13) validateSearch('SEARCH');">
	 		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) closeForm();" tabindex="1" onclick ="closeForm();">
	 		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer onclick ="clearForm()" tabindex="1" onkeypress="if(event.keyCode==13) clearForm();">
 		</his:ButtonToolBarTag>
		
		
		<logic:equal value="<%=MrdConfig.PAT_SEARCH_BY_MLCNO %>" name="SummonDtlFB" property="searchType">
		<logic:notEmpty name="<%=MrdConfig.PAT_DETAIL_lIST_BY_MLCNO %>">
		<his:SubTitleTag name="Patient MLC Detail">
		</his:SubTitleTag>
			<his:ContentTag>
			<table  width="100%" border="0" cellspacing="1" cellpadding="1">
				<tr>
					<td class="tdfonthead" width="5%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="select"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="10%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="mlcNo"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="15%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="mlcDate"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="12%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="crNo"/>
							</font>	
						</div>	
					</td>
					
					<td class="tdfonthead" width="20%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="name"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="10%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="gender/age"/>
							</font>	
						</div>	
					</td>
					
				</tr>
			<logic:iterate id="summonDtlVO" indexId="i" name="<%=MrdConfig.PAT_DETAIL_lIST_BY_MLCNO %>" type="hisglobal.vo.SummonDetailVO">
				<tr>
					<td class="tdfont" width="5%" >
					 	<div align="center">
					 		<html:radio property="mlcNoArray" value="<%=summonDtlVO.getMLCNo() %>" onclick="getPatientDtl()"></html:radio>
					 		
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="MLCNo"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="mlcDate"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="12%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patCrNo"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patName"/>
					 		<html:hidden name="SummonDtlFB" property="patNameArray" value="<%=summonDtlVO.getPatName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="fatherNameArray" value="<%=summonDtlVO.getFatherName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="spouseNameArray" value="<%=summonDtlVO.getSpouseName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="motherNameArray" value="<%=summonDtlVO.getMotherName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patGenderArray" value="<%=summonDtlVO.getPatGender() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patAgeArray" value="<%=summonDtlVO.getPatAge() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patAddressArray" value="<%=summonDtlVO.getPatAddress() %>"/>
					 		<html:hidden name="SummonDtlFB" property="crNoArray" value="<%=summonDtlVO.getPatCrNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="postMortemNoArray" value="<%=summonDtlVO.getPostmortemId() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patGenderCodeArray" value="<%=summonDtlVO.getPatGenderCode() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patDOBArray" value="<%=summonDtlVO.getPatDOB() %>"/>
					 		<html:hidden name="SummonDtlFB" property="episodeCodeArray" value="<%=summonDtlVO.getEpisodeCode() %>"/>
					 		<html:hidden name="SummonDtlFB" property="episodeVisitNoArray" value="<%=summonDtlVO.getEpisodeVisitNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patAdmissionNoArray" value="<%=summonDtlVO.getPatAdmNo() %>"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 	<%String genderAndAge=summonDtlVO.getPatGender()+"/"+summonDtlVO.getPatAge(); %>
					 		<%=genderAndAge %>
					 		
					 	</div>
					</td>
							
				</tr>
			</logic:iterate>	
			</table>
			</his:ContentTag>
		</logic:notEmpty>	
		</logic:equal>
		
		<logic:equal value="<%=MrdConfig.PAT_SEARCH_BY_POSTMARTEM_NO %>" name="SummonDtlFB" property="searchType">
		<logic:notEmpty name="<%=MrdConfig.PAT_DETAIL_LIST_BY_POSTMORTEMID %>">
		<his:SubTitleTag name="Patient PostMortem Detail">
		</his:SubTitleTag>
		<his:ContentTag>
			<table  width="100%" border="0" cellspacing="1" cellpadding="1">
				<tr>
					<td class="tdfonthead" width="5%">
						<div align="center" >
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="select"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="10%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="postMortemNo"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="15%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="postMortemReqDate"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="12%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="crNo"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="20%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="name"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="10%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="gender/age"/>
							</font>	
						</div>	
					</td>
					
				</tr>
			<logic:iterate id="summonDtlVO" indexId="i" name="<%=MrdConfig.PAT_DETAIL_LIST_BY_POSTMORTEMID %>" type="hisglobal.vo.SummonDetailVO">
				<tr>
					<td class="tdfont" width="5%" >
					 	<div align="center">
					 		<html:radio property="postMortemNoArray" value="<%=summonDtlVO.getPostmortemId() %>" onclick="getPatientDtl()"></html:radio>
					 		
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="postmortemId"/>
					 	</div>
					</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="postMotemReqDate"/>
					 	</div>
					</td>
					<td class="tdfont" width="12%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patCrNo"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patName"/>
					 		<html:hidden name="SummonDtlFB" property="patNameArray" value="<%=summonDtlVO.getPatName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="fatherNameArray" value="<%=summonDtlVO.getFatherName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="spouseNameArray" value="<%=summonDtlVO.getSpouseName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="motherNameArray" value="<%=summonDtlVO.getMotherName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patGenderArray" value="<%=summonDtlVO.getPatGender() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patAgeArray" value="<%=summonDtlVO.getPatAge() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patAddressArray" value="<%=summonDtlVO.getPatAddress() %>"/>
					 		<html:hidden name="SummonDtlFB" property="crNoArray" value="<%=summonDtlVO.getPatCrNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlcNoArray" value="<%=summonDtlVO.getMLCNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patGenderCodeArray" value="<%=summonDtlVO.getPatGenderCode() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patDOBArray" value="<%=summonDtlVO.getPatDOB() %>"/>
					 		<html:hidden name="SummonDtlFB" property="episodeCodeArray" value="<%=summonDtlVO.getEpisodeCode() %>"/>
					 		<html:hidden name="SummonDtlFB" property="episodeVisitNoArray" value="<%=summonDtlVO.getEpisodeVisitNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patAdmissionNoArray" value="<%=summonDtlVO.getPatAdmNo() %>"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 	<%String genderAndAge=summonDtlVO.getPatGender()+"/"+summonDtlVO.getPatAge(); %>
					 		<%=genderAndAge %>
					 		
					 	</div>
					</td>
					
							
				</tr>
			</logic:iterate>	
			</table>
			</his:ContentTag>
		</logic:notEmpty>	
		</logic:equal>
		
		<logic:equal value="<%=MrdConfig.PAT_SERACH_BY_CRNO %>" name="SummonDtlFB" property="searchType">
		
		<logic:notEmpty name="<%=MrdConfig.PAT_DETAIL_lIST_BY_MLCNO %>">
		<his:SubTitleTag name="Patient MLC Detail">
		</his:SubTitleTag>
		<his:ContentTag>
			<table  width="100%" border="0" cellspacing="1" cellpadding="1">
				<tr>
					<td class="tdfonthead" width="5%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="select"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="10%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="mlcNo"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="12%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="mlcDate"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="15%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="crNo"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="20%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="name"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="10%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="gender/age"/>
							</font>	
						</div>	
					</td>
					
				</tr>
			<logic:iterate id="summonDtlVO" indexId="i" name="<%=MrdConfig.PAT_DETAIL_lIST_BY_MLCNO %>" type="hisglobal.vo.SummonDetailVO">
				<tr>
					<td class="tdfont" width="5%" >
					 	<div align="center">
					 		<html:radio property="mlc_flagArray" value="<%=summonDtlVO.getMLCNo() %>" onclick="getPatientDtl()"></html:radio>
					 		
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="MLCNo"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="mlcDate"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="12%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patCrNo"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patName"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_patNameArray" value="<%=summonDtlVO.getPatName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_fatherNameArray" value="<%=summonDtlVO.getFatherName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_spouseNameArray" value="<%=summonDtlVO.getSpouseName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_motherNameArray" value="<%=summonDtlVO.getMotherName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_patGenderArray" value="<%=summonDtlVO.getPatGender() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_patAgeArray" value="<%=summonDtlVO.getPatAge() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_patAddressArray" value="<%=summonDtlVO.getPatAddress() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_crNoArray" value="<%=summonDtlVO.getPatCrNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_postMortemNoArray" value="<%=summonDtlVO.getPostmortemId() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_patGenderCodeArray" value="<%=summonDtlVO.getPatGenderCode() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_patDOBArray" value="<%=summonDtlVO.getPatDOB() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_episodeCodeArray" value="<%=summonDtlVO.getEpisodeCode() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_episodeVisitNoArray" value="<%=summonDtlVO.getEpisodeVisitNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_mlcNoArray" value="<%=summonDtlVO.getMLCNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_admissionNoArray" value="<%=summonDtlVO.getPatAdmNo() %>"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 	<%String genderAndAge=summonDtlVO.getPatGender()+"/"+summonDtlVO.getPatAge(); %>
					 		<%=genderAndAge %>
					 		
					 	</div>
					</td>
						
				</tr>
			</logic:iterate>	
			</table>
			</his:ContentTag>
		</logic:notEmpty>
		
		
		<logic:notEmpty name="<%=MrdConfig.PAT_DETAIL_LIST_BY_POSTMORTEMID %>">
		<his:SubTitleTag name="Patient PostMortem Detail">
		</his:SubTitleTag>
		<his:ContentTag>
			<table  width="100%" border="0" cellspacing="1" cellpadding="1">
				<tr>
					<td class="tdfonthead" width="5%">
						<div align="center" >
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="select"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="10%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="postMortemNo"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="15%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="postMortemReqDate"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="12%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="crNo"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="20%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="name"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="10%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="gender/age"/>
							</font>	
						</div>	
					</td>
					
				</tr>
			<logic:iterate id="summonDtlVO" indexId="i" name="<%=MrdConfig.PAT_DETAIL_LIST_BY_POSTMORTEMID %>" type="hisglobal.vo.SummonDetailVO">
				<tr>
					<td class="tdfont" width="5%" >
					 	<div align="center">
					 		<html:radio property="postMortem_flagArray" value="<%=summonDtlVO.getPostmortemId() %>" onclick="getPatientDtl()"></html:radio>
					 		
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="postmortemId"/>
					 	</div>
					</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="postMotemReqDate"/>
					 	</div>
					</td>
					<td class="tdfont" width="12%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patCrNo"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patName"/>
					 		<html:hidden name="SummonDtlFB" property="patNameArray" value="<%=summonDtlVO.getPatName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="fatherNameArray" value="<%=summonDtlVO.getFatherName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="spouseNameArray" value="<%=summonDtlVO.getSpouseName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="motherNameArray" value="<%=summonDtlVO.getMotherName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patGenderArray" value="<%=summonDtlVO.getPatGender() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patAgeArray" value="<%=summonDtlVO.getPatAge() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patAddressArray" value="<%=summonDtlVO.getPatAddress() %>"/>
					 		<html:hidden name="SummonDtlFB" property="crNoArray" value="<%=summonDtlVO.getPatCrNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlcNoArray" value="<%=summonDtlVO.getMLCNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patGenderCodeArray" value="<%=summonDtlVO.getPatGenderCode() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patDOBArray" value="<%=summonDtlVO.getPatDOB() %>"/>
					 		<html:hidden name="SummonDtlFB" property="episodeCodeArray" value="<%=summonDtlVO.getEpisodeCode() %>"/>
					 		<html:hidden name="SummonDtlFB" property="episodeVisitNoArray" value="<%=summonDtlVO.getEpisodeVisitNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="postMortemNoArray" value="<%=summonDtlVO.getPostmortemId() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patAdmissionNoArray" value="<%=summonDtlVO.getPatAdmNo() %>"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 	<%String genderAndAge=summonDtlVO.getPatGender()+"/"+summonDtlVO.getPatAge(); %>
					 		<%=genderAndAge %>
					 		
					 	</div>
					</td>
					
							
				</tr>
			</logic:iterate>	
			</table>
			</his:ContentTag>
		</logic:notEmpty>	
		
		
		<logic:notEmpty name="<%=MrdConfig.PAT_DETAIL_LIST_BY_ADMISSION_NO %>">
		<his:SubTitleTag name="Patient Admission Detail">
		</his:SubTitleTag>
		<his:ContentTag>
			<table  width="100%" border="0" cellspacing="1" cellpadding="1">
				<tr>
					<td class="tdfonthead" width="5%">
						<div align="center" >
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="select"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="10%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="adimissionNo"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="15%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="admissionDate"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="12%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="crNo"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="20%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="name"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="10%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="gender/age"/>
							</font>	
						</div>	
					</td>
					
				</tr>
			<logic:iterate id="summonDtlVO" indexId="i" name="<%=MrdConfig.PAT_DETAIL_LIST_BY_ADMISSION_NO %>" type="hisglobal.vo.SummonDetailVO">
				<tr>
					<td class="tdfont" width="5%" >
					 	<div align="center">
					 		<html:radio property="admission_flagArray" value="<%=summonDtlVO.getPatAdmNo() %>" onclick="getPatientDtl()"></html:radio>
					 		
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patAdmNo"/>
					 	</div>
					</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patAdmissionDate"/>
					 	</div>
					</td>
					<td class="tdfont" width="12%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patCrNo"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patName"/>
					 		<html:hidden name="SummonDtlFB" property="adm_patNameArray" value="<%=summonDtlVO.getPatName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_fatherNameArray" value="<%=summonDtlVO.getFatherName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_spouseNameArray" value="<%=summonDtlVO.getSpouseName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_motherNameArray" value="<%=summonDtlVO.getMotherName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_patGenderArray" value="<%=summonDtlVO.getPatGender() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_patAgeArray" value="<%=summonDtlVO.getPatAge() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_patAddressArray" value="<%=summonDtlVO.getPatAddress() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_crNoArray" value="<%=summonDtlVO.getPatCrNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_mlcNoArray" value="<%=summonDtlVO.getMLCNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_postMortemNoArray" value="<%=summonDtlVO.getPostmortemId() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_patAdmNoArray" value="<%=summonDtlVO.getPatAdmNo() %>"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 	<%String genderAndAge=summonDtlVO.getPatGender()+"/"+summonDtlVO.getPatAge(); %>
					 		<%=genderAndAge %>
					 		
					 	</div>
					</td>
					
							
				</tr>
			</logic:iterate>	
			</table>
			</his:ContentTag>
		</logic:notEmpty>
		
		</logic:equal>
		
		<logic:equal value="<%=MrdConfig.PAT_SEARCH_BY_NAME %>" name="SummonDtlFB" property="searchType">
		
		<logic:notEmpty name="<%=MrdConfig.PAT_DETAIL_lIST_BY_MLCNO %>">
		<his:SubTitleTag name="Patient MLC Detail">
		</his:SubTitleTag>
		<his:ContentTag>
			<table  width="100%" border="0" cellspacing="1" cellpadding="1">
				<tr>
					<td class="tdfonthead" width="5%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="select"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="10%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="mlcNo"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="15%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="mlcDate"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="12%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="crNo"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="20%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="name"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="10%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="gender/age"/>
							</font>	
						</div>	
					</td>
					
				</tr>
			<logic:iterate id="summonDtlVO" indexId="i" name="<%=MrdConfig.PAT_DETAIL_lIST_BY_MLCNO %>" type="hisglobal.vo.SummonDetailVO">
				<tr>
					<td class="tdfont" width="5%" >
					 	<div align="center">
					 		<html:radio property="mlc_flagArray" value="<%=summonDtlVO.getMLCNo() %>" onclick="getPatientDtl()"></html:radio>
					 		
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="MLCNo"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="mlcDate"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="12%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patCrNo"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patName"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_patNameArray" value="<%=summonDtlVO.getPatName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_fatherNameArray" value="<%=summonDtlVO.getFatherName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_spouseNameArray" value="<%=summonDtlVO.getSpouseName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_motherNameArray" value="<%=summonDtlVO.getMotherName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_patGenderArray" value="<%=summonDtlVO.getPatGender() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_patAgeArray" value="<%=summonDtlVO.getPatAge() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_patAddressArray" value="<%=summonDtlVO.getPatAddress() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_crNoArray" value="<%=summonDtlVO.getPatCrNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_postMortemNoArray" value="<%=summonDtlVO.getPostmortemId() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_patGenderCodeArray" value="<%=summonDtlVO.getPatGenderCode() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_patDOBArray" value="<%=summonDtlVO.getPatDOB() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_episodeCodeArray" value="<%=summonDtlVO.getEpisodeCode() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_episodeVisitNoArray" value="<%=summonDtlVO.getEpisodeVisitNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_mlcNoArray" value="<%=summonDtlVO.getMLCNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlc_admissionNoArray" value="<%=summonDtlVO.getPatAdmNo() %>"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 	<%String genderAndAge=summonDtlVO.getPatGender()+"/"+summonDtlVO.getPatAge(); %>
					 		<%=genderAndAge %>
					 		
					 	</div>
					</td>
						
				</tr>
			</logic:iterate>	
			</table>
			</his:ContentTag>
		</logic:notEmpty>
		
		
		<logic:notEmpty name="<%=MrdConfig.PAT_DETAIL_LIST_BY_POSTMORTEMID %>">
		<his:SubTitleTag name="Patient PostMortem Detail">
		</his:SubTitleTag>
		<his:ContentTag>
			<table  width="100%" border="0" cellspacing="1" cellpadding="1">
				<tr>
					<td class="tdfonthead" width="5%">
						<div align="center" >
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="select"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="10%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="postMortemNo"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="15%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="postMortemReqDate"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="12%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="crNo"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="20%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="name"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="10%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="gender/age"/>
							</font>	
						</div>	
					</td>
					
				</tr>
			<logic:iterate id="summonDtlVO" indexId="i" name="<%=MrdConfig.PAT_DETAIL_LIST_BY_POSTMORTEMID %>" type="hisglobal.vo.SummonDetailVO">
				<tr>
					<td class="tdfont" width="5%" >
					 	<div align="center">
					 		<html:radio property="postMortem_flagArray" value="<%=summonDtlVO.getPostmortemId() %>" onclick="getPatientDtl()"></html:radio>
					 		
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="postmortemId"/>
					 	</div>
					</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="postMotemReqDate"/>
					 	</div>
					</td>
					<td class="tdfont" width="12%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patCrNo"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patName"/>
					 		<html:hidden name="SummonDtlFB" property="patNameArray" value="<%=summonDtlVO.getPatName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="fatherNameArray" value="<%=summonDtlVO.getFatherName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="spouseNameArray" value="<%=summonDtlVO.getSpouseName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="motherNameArray" value="<%=summonDtlVO.getMotherName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patGenderArray" value="<%=summonDtlVO.getPatGender() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patAgeArray" value="<%=summonDtlVO.getPatAge() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patAddressArray" value="<%=summonDtlVO.getPatAddress() %>"/>
					 		<html:hidden name="SummonDtlFB" property="crNoArray" value="<%=summonDtlVO.getPatCrNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="mlcNoArray" value="<%=summonDtlVO.getMLCNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patGenderCodeArray" value="<%=summonDtlVO.getPatGenderCode() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patDOBArray" value="<%=summonDtlVO.getPatDOB() %>"/>
					 		<html:hidden name="SummonDtlFB" property="episodeCodeArray" value="<%=summonDtlVO.getEpisodeCode() %>"/>
					 		<html:hidden name="SummonDtlFB" property="episodeVisitNoArray" value="<%=summonDtlVO.getEpisodeVisitNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="postMortemNoArray" value="<%=summonDtlVO.getPostmortemId() %>"/>
					 		<html:hidden name="SummonDtlFB" property="patAdmissionNoArray" value="<%=summonDtlVO.getPatAdmNo() %>"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 	<%String genderAndAge=summonDtlVO.getPatGender()+"/"+summonDtlVO.getPatAge(); %>
					 		<%=genderAndAge %>
					 		
					 	</div>
					</td>
					
				</tr>
			</logic:iterate>	
			</table>
			</his:ContentTag>
			</logic:notEmpty>
		
		<logic:notEmpty name="<%=MrdConfig.PAT_DETAIL_LIST_BY_ADMISSION_NO %>">
		<his:SubTitleTag name="Patient Admission Detail">
		</his:SubTitleTag>
		<his:ContentTag>
			<table  width="100%" border="0" cellspacing="1" cellpadding="1">
				<tr>
					<td class="tdfonthead" width="5%">
						<div align="center" >
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="select"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="10%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="adimissionNo"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="15%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="admissionDate"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="12%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="crNo"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="20%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="name"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="10%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="gender/age"/>
							</font>	
						</div>	
					</td>
					
				</tr>
			<logic:iterate id="summonDtlVO" indexId="i" name="<%=MrdConfig.PAT_DETAIL_LIST_BY_ADMISSION_NO %>" type="hisglobal.vo.SummonDetailVO">
				<tr>
					<td class="tdfont" width="5%" >
					 	<div align="center">
					 		<html:radio property="admission_flagArray" value="<%=summonDtlVO.getPatAdmNo() %>" onclick="getPatientDtl()"></html:radio>
					 		
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patAdmNo"/>
					 	</div>
					</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patAdmissionDate"/>
					 	</div>
					</td>
					<td class="tdfont" width="12%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patCrNo"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patName"/>
					 		<html:hidden name="SummonDtlFB" property="adm_patNameArray" value="<%=summonDtlVO.getPatName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_fatherNameArray" value="<%=summonDtlVO.getFatherName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_spouseNameArray" value="<%=summonDtlVO.getSpouseName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_motherNameArray" value="<%=summonDtlVO.getMotherName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_patGenderArray" value="<%=summonDtlVO.getPatGender() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_patAgeArray" value="<%=summonDtlVO.getPatAge() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_patAddressArray" value="<%=summonDtlVO.getPatAddress() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_crNoArray" value="<%=summonDtlVO.getPatCrNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_mlcNoArray" value="<%=summonDtlVO.getMLCNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_postMortemNoArray" value="<%=summonDtlVO.getPostmortemId() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_patAdmNoArray" value="<%=summonDtlVO.getPatAdmNo() %>"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 	<%String genderAndAge=summonDtlVO.getPatGender()+"/"+summonDtlVO.getPatAge(); %>
					 		<%=genderAndAge %>
					 		
					 	</div>
					</td>
					
							
				</tr>
			</logic:iterate>	
			</table>
			</his:ContentTag>
		</logic:notEmpty>	
		</logic:equal>
		
		<logic:equal value="<%=MrdConfig.PAT_SERACH_BY_ADMISSION_NO %>" name="SummonDtlFB" property="searchType">
		
		<logic:notEmpty name="<%=MrdConfig.PAT_DETAIL_LIST_BY_ADMISSION_NO %>">
		<his:SubTitleTag name="Patient Admission Detail">
		</his:SubTitleTag>
		<his:ContentTag>
			<table  width="100%" border="0" cellspacing="1" cellpadding="1">
				<tr>
					<td class="tdfonthead" width="5%">
						<div align="center" >
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="select"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="10%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="adimissionNo"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="15%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="admissionDate"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="12%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="crNo"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="20%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="name"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="10%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="gender/age"/>
							</font>	
						</div>	
					</td>
					
				</tr>
			<logic:iterate id="summonDtlVO" indexId="i" name="<%=MrdConfig.PAT_DETAIL_LIST_BY_ADMISSION_NO %>" type="hisglobal.vo.SummonDetailVO">
				<tr>
					<td class="tdfont" width="5%" >
					 	<div align="center">
					 		<html:radio property="admission_flagArray" value="<%=summonDtlVO.getPatAdmNo() %>" onclick="getPatientDtl()"></html:radio>
					 		
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patAdmNo"/>
					 	</div>
					</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patAdmissionDate"/>
					 	</div>
					</td>
					<td class="tdfont" width="12%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patCrNo"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="summonDtlVO" property="patName"/>
					 		<html:hidden name="SummonDtlFB" property="adm_patNameArray" value="<%=summonDtlVO.getPatName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_fatherNameArray" value="<%=summonDtlVO.getFatherName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_spouseNameArray" value="<%=summonDtlVO.getSpouseName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_motherNameArray" value="<%=summonDtlVO.getMotherName() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_patGenderArray" value="<%=summonDtlVO.getPatGender() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_patAgeArray" value="<%=summonDtlVO.getPatAge() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_patAddressArray" value="<%=summonDtlVO.getPatAddress() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_crNoArray" value="<%=summonDtlVO.getPatCrNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_mlcNoArray" value="<%=summonDtlVO.getMLCNo() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_postMortemNoArray" value="<%=summonDtlVO.getPostmortemId() %>"/>
					 		<html:hidden name="SummonDtlFB" property="adm_patAdmNoArray" value="<%=summonDtlVO.getPatAdmNo() %>"/>
					 	</div>
				 	</td>
				 	<td class="tdfont" width="20%" >
					 	<div align="center">
					 	<%String genderAndAge=summonDtlVO.getPatGender()+"/"+summonDtlVO.getPatAge(); %>
					 		<%=genderAndAge %>
					 		
					 	</div>
					</td>
				</tr>
			</logic:iterate>	
			</table>
			</his:ContentTag>
		</logic:notEmpty>
		</logic:equal>
		
		<his:status/>
		
	<html:hidden name="SummonDtlFB" property="hmode" />
	
</html:form>
</his:TransactionContainer>
</body>
</html>