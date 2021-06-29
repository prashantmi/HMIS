<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>
<%@page import="hisglobal.vo.HospitalMstVO"%>
<%@page import="ehr.EHRConfig"%>
<%@page import="ehr.followup.vo.EHRSection_FollowupVO"%>
<%@page import="emr.vo.PatientClinicalDocDetailVO"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page import="emr.vo.PatientClinicalDocDetailVO"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Discharge Summary</title>      

<link href="/HIS/hisglobal/bbpublic/assets/bootstrap/css/bootstrap.css" rel="stylesheet"> 



<script src="/HIS/hisglobal/bbpublic/assets/js/jquery-1.11.1.min.js"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.debug.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js"></script> -->
<his:javascript src="/registration/js/registration.js" /> 
<script>
/* $('#previewBtn').click(function() {
	  var options = {
	  };
	  var pdf = new jsPDF('p', 'pt', 'a4');
	  pdf.addHTML($("#PDFBODY"), 15, 15, options, function() {
	    pdf.save('pageContent.pdf');
	  });
	}); */
	
function generateComposition()
{
	/* var options = {
	  };
	  var pdf = new jsPDF('p', 'pt', 'a2');
	  pdf.addHTML($("#PDFBODY"), 15, 15, options, function() {
	    pdf.save('pageContent.pdf');
	  }); */
	  /* window.print(); */
	   $('img').each(function() {
	      $("img").attr("src", function() {
          return $(this).attr("class");
    });
	   });
	var str = document.getElementById('htmlEHRComposition').innerHTML;
	//alert("hi");
	//alert(document.getElementById('htmlEHRComposition').innerHTML);
	//alert(btoa(unescape(encodeURIComponent(str))));
	document.getElementsByName("htmlPreview")[0].value= window.btoa(unescape(encodeURIComponent(str)));
	
	submitForm21('SAVEDOCPDF');
}
window.onload = function()
{
	setPatDocSelectSections(this);
	/* var tds = document.getElementById('table1').rows[0].cells.length;
	alert(tds) */
}



function toggleContentSelection(obj)
{
	//alert($(obj).parent().closest("tr").attr('id'));
	
	if (obj.checked)
	{
		$(obj).parent().closest("tr").find("*").css("text-decoration","none");
		$(obj).parent().closest("tr").attr('id','');
		$(obj).parent().next().find("input").css("display","block");
		$(obj).parent().nextAll("td").find("input").css("display","block");
		//$(obj).parent().next().find("input").attr("readOnly","false");
		//$(obj).attr("disabled","false");
		//$('#gg').css("text-decoration","none");
		//$(this).parent().parent().css("text-decoration", "line-through");
	}
	else
	{
		//alert($(obj).children().next().find("input"));
		$(obj).parent().closest("tr").find("*").css("text-decoration", "line-through");
		$(obj).parent().closest("tr").attr('id','noPrintElement');
		$(obj).parent().next().find("input").css("display","none");
		$(obj).parent().nextAll("td").find("input").css("display","none");
		
	}
	
			
}

function toggleColumnSection(obj)
{
	var ele5 = document.getElementsByName('column_selected_5'); 

	var eletd5 = document.getElementsByName('column_selected_td_5'); 

	for(i = 0; i < ele5.length; i++) { 
        if(ele5[0].checked) {


        	for(i = 0; i < eletd5.length; i++) { 
        		$(eletd5[i]).css("text-decoration","none");
        		$(eletd5[i]).attr('id','');
        		$(eletd5[i]).prev("td").find("input").css("display","block");
            	}
          
		 }
        else
            {
        	for(i = 0; i < eletd5.length; i++) { 
        		$(eletd5[i]).css("text-decoration","line-through");
        		$(eletd5[i]).attr('id','noPrintElement');
        		$(eletd5[i]).prev("td").find("input").css("display","none");
            	}
            }
    
    }

	var ele6 = document.getElementsByName('column_selected_6'); 

	var eletd6 = document.getElementsByName('column_selected_td_6'); 

	for(i = 0; i < ele6.length; i++) { 
        if(ele6[0].checked) {


        	for(i = 0; i < eletd6.length; i++) { 
        		$(eletd6[i]).css("text-decoration","none");
        		$(eletd6[i]).attr('id','');
        		$(eletd6[i]).prev("td").find("input").css("display","block");
            	}
          
		 }
        else
            {
        	for(i = 0; i < eletd6.length; i++) { 
        		$(eletd6[i]).css("text-decoration","line-through");
        		$(eletd6[i]).attr('id','noPrintElement');
        		$(eletd6[i]).prev("td").find("input").css("display","none");
            	}
            }
    
    } 
	var ele7 = document.getElementsByName('column_selected_7'); 

	var eletd7 = document.getElementsByName('column_selected_td_7'); 

	for(i = 0; i < ele7.length; i++) { 
        if(ele7[0].checked) {


        	for(i = 0; i < eletd7.length; i++) { 
        		$(eletd7[i]).css("text-decoration","none");
        		$(eletd7[i]).attr('id','');
        		$(eletd7[i]).prev("td").find("input").css("display","block");
            	}
          
		 }
        else
            {
        	for(i = 0; i < eletd7.length; i++) { 
        		$(eletd7[i]).css("text-decoration","line-through");
        		$(eletd7[i]).attr('id','noPrintElement');
        		$(eletd7[i]).prev("td").find("input").css("display","none");
            	}
            }
    
    }

	var ele8 = document.getElementsByName('column_selected_8'); 

	var eletd8 = document.getElementsByName('column_selected_td_8'); 

	for(i = 0; i < ele8.length; i++) { 
        if(ele8[0].checked) {


        	for(i = 0; i < eletd8.length; i++) { 
        		$(eletd8[i]).css("text-decoration","none");
        		$(eletd8[i]).attr('id','');
        		$(eletd8[i]).prev("td").find("input").css("display","block");
            	}
          
		 }
        else
            {
        	for(i = 0; i < eletd8.length; i++) { 
        		$(eletd8[i]).css("text-decoration","line-through");
        		$(eletd8[i]).attr('id','noPrintElement');
        		$(eletd8[i]).prev("td").find("input").css("display","none");
            	}
            }
    
    } 
	
}



function toggleColumn(obj)
{
	var ele = document.getElementsByName('column_selected_1'); 

	var eletd = document.getElementsByName('column_selected_td_1'); 
	//alert(ele.length);
	
	for(i = 0; i < ele.length; i++) { 
        if(ele[0].checked) {


        	for(i = 0; i < eletd.length; i++) { 
        		$(eletd[i]).css("text-decoration","none");
        		$(eletd[i]).attr('id','');
            	}
          
		 }
        else
            {
        	for(i = 0; i < eletd.length; i++) { 
        		$(eletd[i]).css("text-decoration","line-through");
        		$(eletd[i]).attr('id','noPrintElement');
            	}
            }
    
    } 
    
	var ele1 = document.getElementsByName('column_selected_2'); 

	var eletd1 = document.getElementsByName('column_selected_td_2'); 

	for(i = 0; i < ele1.length; i++) { 
        if(ele1[0].checked) {


        	for(i = 0; i < eletd1.length; i++) { 
        		$(eletd1[i]).css("text-decoration","none");
        		$(eletd1[i]).attr('id','');
        		$(eletd1[i]).prev("td").find("input").css("display","block");
            	}
          
		 }
        else
            {
        	for(i = 0; i < eletd1.length; i++) { 
        		$(eletd1[i]).css("text-decoration","line-through");
        		$(eletd1[i]).attr('id','noPrintElement');
        		$(eletd1[i]).prev("td").find("input").css("display","none");
            	}
            }
    
    } 
	
	
	var ele2 = document.getElementsByName('column_selected_3'); 

	var eletd2 = document.getElementsByName('column_selected_td_3'); 

	for(i = 0; i < ele2.length; i++) { 
        if(ele2[0].checked) {


        	for(i = 0; i < eletd2.length; i++) { 
        		$(eletd2[i]).css("text-decoration","none");
        		$(eletd2[i]).attr('id','');
        		$(eletd2[i]).prev("td").find("input").css("display","block");
            	}
          
		 }
        else
            {
        	for(i = 0; i < eletd2.length; i++) { 
        		$(eletd2[i]).css("text-decoration","line-through");
        		$(eletd2[i]).attr('id','noPrintElement');
        		$(eletd2[i]).prev("td").find("input").css("display","none");
            	}
            }
    
    } 
	

	var ele3 = document.getElementsByName('column_selected_4'); 

	var eletd3 = document.getElementsByName('column_selected_td_4'); 

	for(i = 0; i < ele3.length; i++) { 
        if(ele3[0].checked) {


        	for(i = 0; i < eletd3.length; i++) { 
        		$(eletd3[i]).css("text-decoration","none");
        		$(eletd3[i]).attr('id','');
        		$(eletd3[i]).prev("td").find("input").css("display","block");
            	}
          
		 }
        else
            {
        	for(i = 0; i < eletd3.length; i++) { 
        		$(eletd3[i]).css("text-decoration","line-through");
        		$(eletd3[i]).attr('id','noPrintElement');
        		$(eletd3[i]).prev("td").find("input").css("display","none");
            	}
            }
    
    } 
	
	


     
}

function toggleContent(obj)
{
	//alert($(obj).closest("td"));
if(obj.checked)
	{
		$(obj).closest("td").next("td").css("text-decoration","none");
		$(obj).closest("td").next("td").attr('id','');
		$(obj).find("input").css("display","block");
	}

 else
	 {
	 $(obj).closest("td").next("td").css("text-decoration","line-through");
		$(obj).closest("td").next("td").attr('id','noPrintElement');
		$(obj).find("input").css("display","none");
	 }
}





	function setPatDocSelectSections(obj)
	{   
		//alert($(obj).siblings());
		//toggleContentSelection(obj);

		
	  var sections="";
	  for(var i=0;i<document.getElementsByName("ehrcomp_chk_select").length;i++)
		{
			var chk = document.getElementsByName("ehrcomp_chk_select")[i];
			if(chk.checked == true)
			{
				//alert("chk:"+chk);	
				sections = sections + chk.value + "#";
		 		//alert( chk.value);
		 		document.getElementsByName("chkSelectedSections")[0].value = sections;
			}else if(chk.checked == false ){
				
				}
		 }    
         //alert(sections);
	}
	

</script>
   
 </head>
<style>
.table.table-borderless td, .table.table-borderless th {
    border: 0 !important;
}

.table.table-borderless {
    margin-bottom: 0px;
}
fieldset.scheduler-border {
    border: 1px groove black !important;
    padding: 0 1.4em 1.4em 1.4em !important;
    margin: 0 0 1.5em 0 !important;
    -webkit-box-shadow:  0px 0px 0px 0px #000;
            box-shadow:  0px 0px 0px 0px #000;
}

legend.scheduler-border {
        font-size: 1.2em !important;
        font-weight: bold !important;
        text-align: left !important;
        width:auto;
        padding:0px 5px 10px;
        border-bottom:none;
        color:
    }
    @media print {
  #printPageButton {
    display: none;
  }
  #PatDetails
  {
  font-family:ARIAL;
  }
}
</style>
<body style="margin:0%" id="PDFBODY">
<div class="col-*-*" align="right">
	<img  class="button" src="/HISClinical/hisglobal/images/btn-generate.png"  style="cursor:pointer" id="previewBtn" tabindex="1" onclick = "generateComposition();" >
	<!-- <img  class="button " src="/HIS/hisglobal/images/buttons/btn-pnt.png"  style="cursor:pointer" tabindex="1"  onclick="submitForm21('getViewDoc');"> -->
</div>


<html:form  action="/uniPagePrescription.cnt" method="POST"> 
<%-- <html:form action="/uniPagePrescription"> --%>

	<%
	 	String hosCode = (String)session.getAttribute("HOSPITAL_CODE");
		String logo_path = "/HISClinical/hisglobal/images/aiims_header_"+hosCode+".png";
	%>
		<table width="100%" class="table" style="margin:1%">
				<tr>
					
					<td width="100%" align="center">
						<img src="<%=logo_path%>" height="110px">
					</td> 
				</tr>
			</table>
	<div id="htmlEHRComposition" class="table-responsive container-fluid">
		<table width="100%" class="table" style="margin:1%">
			<logic:iterate id="PatientClinicalDocDetailVO" name="<%=EHRConfig.CLINICAL_SECTION_COMP_SECTIONS_PRINT_LIST%>" indexId="id" type="emr.vo.PatientClinicalDocDetailVO" >
				<%
					if(PatientClinicalDocDetailVO.getHtmlString()!=null && !PatientClinicalDocDetailVO.getHtmlString().trim().equals(""))
					{
				%>
				<tr>
					<logic:equal name="PatientClinicalDocDetailVO" property="isToggable" value="1">		 
					<td width="2%" valign="top" id="noPrintElement" align="left">
                       	<input type="checkbox" id="noPrintElement" name="ehrcomp_chk_select" checked="checked" value="<%=PatientClinicalDocDetailVO.getClinicalSectionCode()+"^"+PatientClinicalDocDetailVO.getClinicalSecCompMappingVnd()%>" onClick="toggleContentSelection(this);setPatDocSelectSections('<%=PatientClinicalDocDetailVO.getClinicalSectionCode()+"#"+PatientClinicalDocDetailVO.getClinicalSecCompMappingVnd()%>')"  />
					</td> 
					</logic:equal> 
					 <logic:equal name="PatientClinicalDocDetailVO" property="isToggable" value="0">	
					<td width="2%" id="noPrintElement"  valign="top"  align="left">
					</td> 
					</logic:equal>  
					<td width="100%">
						<bean:write name="PatientClinicalDocDetailVO" property="htmlString" filter="false" format="false"/>	
						<html:hidden name="UniPagePrescriptionFB" property="isToggable" value='<%=PatientClinicalDocDetailVO.getIsToggable()%>'/>	
					</td> 
				</tr>
				<%} %>
			</logic:iterate>
		</table>
	</div>
	<html:hidden name="UniPagePrescriptionFB" property="chkSelectedSections"/>
	<html:hidden name="UniPagePrescriptionFB" property="hmode"/>
	<html:hidden name="UniPagePrescriptionFB" property="patCrNo"/>
	<html:hidden name="UniPagePrescriptionFB" property="htmlPreview"/>
	<html:hidden name="UniPagePrescriptionFB" property="documentType"/>
</html:form>
</body>
</html>