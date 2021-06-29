<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HISInvestigationTools.tld" prefix="templateTag"%>

<%@page import="new_investigation.InvestigationConfig"%>
<html>
<style>
    a{ cursor:pointer }
</style>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/new_investigation/js/testParameterMstAddMod.js" />
<his:javascript src="/new_investigation/js/inv_popup.js" />
<his:javascript src="/new_investigation/js/labRequisition.js" />
<his:javascript src="/new_investigation/js/ckeditor/ckeditor.js"/>
<his:javascript src="/new_investigation/js/wysiwyg.js"/>
<his:javascript src="/new_investigation/js/wysiwyg-settings.js" />

<body onload="onSelectLoad(document.getElementsByName('elementType')[0],document.getElementsByName('textEditorValue')[0].value,document.getElementsByName('showDefaultValue')[0]);">

<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

	<script type="text/javascript">

	function setTestComboUsingAjax2(labCode)
	{
		var flg = false;
		var tstValArr = "";
		var _mode = "AJX_TEST_COMBO";
		var objXHR = {url: "/HISInvestigationG5/new_investigation/masters/TestParameterMstACT.cnt?hmode="+_mode, sync:true, postData: "", handleAs: "text",
			load: function(data) 
			{
				//alert(data);
				tstValArr = data;
				flg = true;
			},
	        error: function(error)
	        {
	            flg = false;
	        }};

		var objDojoAjax = dojo.xhrPost(objXHR);
		//document.getElementsByName('tstValArr')[0].value=tstValArr;
		//alert(document.getElementsByName('tstValArr')[0].value);
		 
		return tstValArr;
	}
	

	
function reqformElementsSet()
{

	//alert("reqformElementsSet");
//	var val=document.getElementsByName("reqMasterFormType")[0].value;
  //  alert(val);
   var cchecked="";
   for(var i=0;i<document.getElementsByName("paraType").length;i++)
	   {
                  if(document.getElementsByName("paraType")[i].checked==true)
                      {
                	  cchecked=(document.getElementsByName("paraType")[i].value);
                      }
	   }
   
          if(cchecked=="2")
        	document.getElementById("masterone").style.display="";
          else
              {
        	  document.getElementById("masterone").style.display="none";
        	  document.getElementsByName("reqMasterFormType")[0].value="-1";
        	  derivedcombo();
              
              }
}

function derivedcombo()
{

	//alert("reqformElementsSet");
	var val=document.getElementsByName("reqMasterFormType")[0].value;
   // alert(val);

      
    if(val==2)
        {
    	document.getElementById("derivedcomboshw").style.display="";
    	document.getElementById("req112").style.display="";
    	document.getElementById("req1").style.display="none";
    	
        	
        }
    else
        {
    	document.getElementById("derivedcomboshw").style.display="none";
    	document.getElementById("req112").style.display="none";
    	document.getElementById("req1").style.display="";
    	
        }
	//document.getElementById("masterone").style.display="";
	//setTestComboUsingAjax2();
    setreqmastervalue();

}


function setreqmastervalue()
{
     //alert("setreqmastervalue");
     var val=document.getElementsByName("reqMasterFormType")[0].value;
   //  alert(val);
     var reqMasterFormType=document.getElementsByName("reqMasterFormType")[0].value;
     if( (reqMasterFormType!=2))
         {
     document.getElementsByName("masterreqformcheck")[0].value="0";
     document.getElementsByName("masterreqformcheck")[0].value="1";  
	// alert(document.getElementsByName("masterreqformcheck")[0].value);
	// document.getElementById("req1").style.display="";
	 document.getElementById("req2").style.display="";
/* 	 document.getElementById("req3").style.display="none";
	 document.getElementById("req4").style.display="none";
	 document.getElementById("req5").style.display="none";
	 document.getElementById("req6").style.display="none";
	 document.getElementById("req7").style.display="none";
	 document.getElementById("req8").style.display="none";
	 document.getElementById("req9").style.display="none";
	 document.getElementById("req10").style.display="none";
	 */
	  document.getElementById("req11").style.display="";
	 document.getElementById("req12").style.display="";
	 document.getElementById("req13").style.display="";
	 document.getElementById("req14").style.display="";
	 document.getElementById("req15").style.display="";
	 document.getElementById("req16").style.display="";
	 document.getElementById("req17").style.display="";
	/*  document.getElementById("req18").style.display="none";
	 document.getElementById("req19").style.display="none"; */
	 document.getElementById("req20").style.display="";
     }
     else
         {
    	 document.getElementsByName("masterreqformcheck")[0].value="1";  
    	// alert(document.getElementsByName("masterreqformcheck")[0].value);
    	 //document.getElementById("req1").style.display="none";
    	 document.getElementById("req2").style.display="none";
    /* 	 document.getElementById("req3").style.display="none";
    	 document.getElementById("req4").style.display="none";
    	 document.getElementById("req5").style.display="none";
    	 document.getElementById("req6").style.display="none";
    	 document.getElementById("req7").style.display="none";
    	 document.getElementById("req8").style.display="none";
    	 document.getElementById("req9").style.display="none";
    	 document.getElementById("req10").style.display="none";
    	 */
    	  document.getElementById("req11").style.display="none";
    	 document.getElementById("req12").style.display="none";
    	 document.getElementById("req13").style.display="none";
    	 document.getElementById("req14").style.display="none";
    	 document.getElementById("req15").style.display="none";
    	 document.getElementById("req16").style.display="none";
    	 document.getElementById("req17").style.display="none";
    	/*  document.getElementById("req18").style.display="none";
    	 document.getElementById("req19").style.display="none"; */
    	 document.getElementById("req20").style.display="none";
    	 
         }
         }

function showMandatoryForTest(nm)
{
Popup.showModal(nm);
return false;
}
  function validateOnSave()
{
   valid=false;
   
    if( isEmpty(document.forms[0].testCode,"testCode") )
     {
         valid=true ;
     }
  return valid;
}
  
  
  function ShowValue(obj)
  {
   
	  var parameter=document.getElementsByName("parameterCode")[0].value;

	  
	 // alert(parameter);
	  document.getElementsByName("parentParameter")[0].value=parameter;

	 // alert(document.getElementsByName("parameterName")[0].value);
  }

  
  
  function onSelectLoad(obj,editor1,showDefaultValue)
  {  	

	  
	  if(showDefaultValue != null && editor1 != null)
		  ShowDefaultEditorModify(showDefaultValue, editor1);
		  		  
		 // CKEDIOR.instances.editor1.setData("dgfg");
	  if(document.getElementsByName("showParameterNameasLabel")[0].value=='1') 
	  {
	  
	  document.getElementById("subtext1").style.display="";
	  document.getElementById("subtext2").style.display="";
	  document.getElementById("subtext3").style.display="";
   
	  }
	   var i=0;
	   

 
//alert(document.getElementsByName("elementType")[0].value);
if(obj.value=='C') // for checkbox 
{
	document.getElementById("checkbox1").style.display="";
	  document.getElementById("Label1").style.display="none";
	  document.getElementById("textBox1").style.display="none";
	  document.getElementById("textArea1").style.display="none";
	  document.getElementById("combo1").style.display="none"; 
	  document.getElementById("button1").style.display="none"; 
	  document.getElementById("querycombo1").style.display="none"; 
	  document.getElementById("listBox1").style.display="none";
	  document.getElementById("Auto1").style.display="none"; 
	  document.getElementById("urltbl1").style.display="none";
	  document.getElementById("urltbl").style.display="none";
	  document.getElementById("hyperlink1").style.display="none";
	  document.getElementById("snomedct").style.display="none";
	
	  if(document.getElementsByName("showParameterNameasLabel")[0].value=='1') 
	   {
	   
	   document.getElementById("checkboxx1").style.display="";
	   document.getElementById("checkboxx2").style.display="";
	   document.getElementById("checkboxx3").style.display="";

	   }
	   if(document.getElementsByName("showParameterNameasLabel")[0].value=='0')
	   {
	   
	   document.getElementById("checkboxx1").style.display="none";
	   document.getElementById("checkboxx2").style.display="none";
	   document.getElementById("checkboxx3").style.display="none";

	   }

}
else if(obj.value=='A')//HYPERLINK
	{
   //alert("chandan");
  document.getElementById("urltbl1").style.display="";
   document.getElementById("urltbl").style.display="";
   document.getElementById("hyperlink1").style.display="";

   document.getElementById("Label1").style.display="none";
   document.getElementById("textBox1").style.display="none";
   document.getElementById("textArea1").style.display="none";
   document.getElementById("combo1").style.display="none"; 
   document.getElementById("button1").style.display="none"; 
   document.getElementById("querycombo1").style.display="none"; 
   document.getElementById("listBox1").style.display="none";
   document.getElementById("Auto1").style.display="none"; 
   document.getElementById("urltbl1").style.display="none";
   document.getElementById("urltbl").style.display="none";
   document.getElementById("checkbox1").style.display="none";
   document.getElementById("snomedct").style.display="none";
   if(document.getElementsByName("showParameterNameasLabel")[0].value=='1') 
   {
	   document.getElementById("urltbl1").style.display="";
	   document.getElementById("urltbl").style.display="";
   document.getElementById("hyper1").style.display="";
   document.getElementById("hyper2").style.display="";
   document.getElementById("hyper3").style.display="";

   }
   if(document.getElementsByName("showParameterNameasLabel")[0].value=='0')
   {
	   document.getElementById("urltbl1").style.display="none";
	   document.getElementById("urltbl").style.display="none";
   document.getElementById("hyper1").style.display="none";
   document.getElementById("hyper2").style.display="none";
   document.getElementById("hyper3").style.display="none";

   }
   
	}
else if(obj.value=='B')//label
  {
   
  document.getElementById("Label1").style.display="";
  document.getElementById("textBox1").style.display="none";
  document.getElementById("textArea1").style.display="none";
  document.getElementById("combo1").style.display="none"; 
  document.getElementById("button1").style.display="none"; 
  document.getElementById("querycombo1").style.display="none"; 
  document.getElementById("listBox1").style.display="none";
  document.getElementById("Auto1").style.display="none"; 
  document.getElementById("snomedct").style.display="none";
  }
   
  else if(obj.value=='E')//label
  {
	///  alert(document.getElementsByName("elementType")[0].value);
   
  document.getElementById("textBox1").style.display="";
  document.getElementById("Label1").style.display="none";
  document.getElementById("textArea1").style.display="none";
  document.getElementById("combo1").style.display="none"; 
  document.getElementById("button1").style.display="none"; 
  document.getElementById("querycombo1").style.display="none"; 
  document.getElementById("listBox1").style.display="none";
  document.getElementById("Auto1").style.display="none"; 
  document.getElementById("urltbl1").style.display="none";
  document.getElementById("urltbl").style.display="none";
  document.getElementById("hyperlink1").style.display="none";
  document.getElementById("checkbox1").style.display="none";
  document.getElementById("snomedct").style.display="none";
  
  if(document.getElementsByName("showParameterNameasLabel")[0].value=='1') 
  {
  
  document.getElementById("subtext1").style.display="";
  document.getElementById("subtext2").style.display="";
  document.getElementById("subtext3").style.display="";

  }
   
  }
  
  else if(obj.value=='H')//label
  {
	//  alert(document.getElementsByName("elementType")[0].value);
   
  document.getElementById("textBox1").style.display="none";
  document.getElementById("Label1").style.display="none";
  document.getElementById("textArea1").style.display="";
  document.getElementById("combo1").style.display="none"; 
  document.getElementById("button1").style.display="none"; 
  document.getElementById("querycombo1").style.display="none"; 
  document.getElementById("listBox1").style.display="none";
  document.getElementById("Auto1").style.display="none"; 
  document.getElementById("urltbl1").style.display="none";
  document.getElementById("urltbl").style.display="none";
  document.getElementById("hyperlink1").style.display="none";
  document.getElementById("checkbox1").style.display="none";
  document.getElementById("snomedct").style.display="none";
  
  if(document.getElementsByName("showParameterNameasLabel")[0].value=='1') 
  {
  
  document.getElementById("subtextarea1").style.display="";
  document.getElementById("subtextarea2").style.display="";
  document.getElementById("subtextarea3").style.display="";

  }
  }
  
  else if(obj.value=='D')//label
  {
	//  alert(document.getElementsByName("elementType")[0].value);
   
  document.getElementById("textBox1").style.display="none";
  document.getElementById("Label1").style.display="none";
  document.getElementById("textArea1").style.display="none";
  document.getElementById("combo1").style.display=""; 
  document.getElementById("button1").style.display="none"; 
  document.getElementById("querycombo1").style.display="none"; 
  document.getElementById("listBox1").style.display="none";
  document.getElementById("Auto1").style.display="none"; 
  document.getElementById("urltbl1").style.display="none";
  document.getElementById("urltbl").style.display="none";
  document.getElementById("hyperlink1").style.display="none";
  document.getElementById("checkbox1").style.display="none";
  document.getElementById("snomedct").style.display="none";
  }
  
  else if(obj.value=='J')//label
  {
	//  alert(document.getElementsByName("elementType")[0].value);
   
  document.getElementById("textBox1").style.display="none";
  document.getElementById("Label1").style.display="none";
  document.getElementById("textArea1").style.display="none";
  document.getElementById("combo1").style.display="none"; 
  document.getElementById("button1").style.display=""; 
  document.getElementById("querycombo1").style.display="none"; 
  document.getElementById("listBox1").style.display="none";
  document.getElementById("Auto1").style.display="none"; 
  document.getElementById("urltbl1").style.display="none";
  document.getElementById("urltbl").style.display="none";
  document.getElementById("hyperlink1").style.display="none";
  document.getElementById("checkbox1").style.display="none";
  document.getElementById("snomedct").style.display="none";
  }
  else if(obj.value=='K')//label
  {
	//  alert(document.getElementsByName("elementType")[0].value);
   
  document.getElementById("textBox1").style.display="none";
  document.getElementById("Label1").style.display="none";
  document.getElementById("textArea1").style.display="none";
  document.getElementById("combo1").style.display="none"; 
  document.getElementById("button1").style.display="none"; 
  document.getElementById("querycombo1").style.display=""; 
  document.getElementById("listBox1").style.display="none";
  document.getElementById("Auto1").style.display="none"; 
  document.getElementById("urltbl1").style.display="none";
  document.getElementById("urltbl").style.display="none";
  document.getElementById("hyperlink1").style.display="none";
  document.getElementById("checkbox1").style.display="none";
  document.getElementById("snomedct").style.display="none";
  }
  else if(obj.value=='Z')//label
  {
	//  alert(document.getElementsByName("elementType")[0].value);
   
  document.getElementById("textBox1").style.display="none";
  document.getElementById("Label1").style.display="none";
  document.getElementById("textArea1").style.display="none";
  document.getElementById("combo1").style.display="none"; 
  document.getElementById("button1").style.display="none"; 
  document.getElementById("querycombo1").style.display="none";
  document.getElementById("listBox1").style.display="";
  document.getElementById("Auto1").style.display="none"; 
  document.getElementById("urltbl1").style.display="none";
  document.getElementById("urltbl").style.display="none";
  document.getElementById("hyperlink1").style.display="none";
  document.getElementById("checkbox1").style.display="none";
  document.getElementById("snomedct").style.display="none";
  
  if(document.getElementsByName("showParameterNameasLabel")[0].value=='1') 
  {
  
  document.getElementById("sublist1").style.display="";
  document.getElementById("sublist2").style.display="";
  document.getElementById("sublist3").style.display="";

  }
  }  else if(obj.value=='P')//label
  {
		//  alert(document.getElementsByName("elementType")[0].value);
	 
	  document.getElementById("textBox1").style.display="none";
	  document.getElementById("Label1").style.display="none";
	  document.getElementById("textArea1").style.display="none";
	  document.getElementById("combo1").style.display="none"; 
	  document.getElementById("button1").style.display="none"; 
	  document.getElementById("querycombo1").style.display="none"; 
	  document.getElementById("listBox1").style.display="none";
	  document.getElementById("Auto1").style.display=""; 
	  document.getElementById("urltbl1").style.display="none";
	  document.getElementById("urltbl").style.display="none";
	  document.getElementById("hyperlink1").style.display="none";
	  document.getElementById("checkbox1").style.display="none";
	  document.getElementById("snomedct").style.display="none";
	  }
  else if(obj.value=='S') //Snomed CT
  {
	 // alert("fdsf");
	  document.getElementById("snomedct").style.display="";
	  document.getElementById("textBox1").style.display="none";
	  document.getElementById("Label1").style.display="none";
	  document.getElementById("textArea1").style.display="none";
	  document.getElementById("combo1").style.display="none"; 
	  document.getElementById("button1").style.display="none"; 
	  document.getElementById("querycombo1").style.display="none"; 
	  document.getElementById("listBox1").style.display="none";
	  document.getElementById("Auto1").style.display="none"; 
	  document.getElementById("urltbl1").style.display="none";
	  document.getElementById("urltbl").style.display="none";
	  document.getElementById("hyperlink1").style.display="none";
	  document.getElementById("checkbox1").style.display="none";
	  
	  if(document.getElementsByName("showParameterNameasLabel")[0].value=='1') 
	  {
	  
	  document.getElementById("subtext1").style.display="";
	  document.getElementById("subtext2").style.display="";
	  document.getElementById("subtext3").style.display="";

	  }
  }
     else //combo
      {
	  document.getElementById("Label1").style.display="none";
	  
	  document.getElementById("textBox1").style.display="none";
	  document.getElementById("textArea1").style.display="none";
	  document.getElementById("combo1").style.display="none";
	  document.getElementById("button1").style.display="none"; 
	  document.getElementById("querycombo1").style.display="none";
	  document.getElementById("listBox1").style.display="none";
	  document.getElementById("urltbl1").style.display="none";
	  document.getElementById("urltbl").style.display="none";
	  document.getElementById("hyperlink1").style.display="none";
	  document.getElementById("checkbox1").style.display="none";
      }

 
 
  }
  
  
  function onSelect(obj,editor1,showDefaultValue)
  {  	
	  if(showDefaultValue != null && editor1 != null)
		  ShowDefaultEditorModify(showDefaultValue, editor1);
		  		  
		 // CKEDIOR.instances.editor1.setData("dgfg");
	  if(document.getElementsByName("showParameterNameasLabel")[0].value=='1') 
	  {
	  
	  document.getElementById("subtext1").style.display="";
	  document.getElementById("subtext2").style.display="";
	  document.getElementById("subtext3").style.display="";
   
	  }
	   var i=0;
	   
for(i=0;i<document.getElementsByName("labelAlignment").length;i++)
	document.getElementsByName("labelAlignment")[i].value="0";
	
for(i=0;i<document.getElementsByName("bold").length;i++)
	document.getElementsByName("bold")[i].value="0";
	
for(i=0;i<document.getElementsByName("underline").length;i++)
	document.getElementsByName("underline")[i].value="0";
	
for(i=0;i<document.getElementsByName("elementAlign").length;i++)
	document.getElementsByName("elementAlign")[i].value="0";
	

for(i=0;i<document.getElementsByName("elementAlign").length;i++)
	document.getElementsByName("elementAlign")[i].value="0";
	
for(i=0;i<document.getElementsByName("event").length;i++)
	document.getElementsByName("event")[i].value="0";



for(i=0;i<document.getElementsByName("showParameterNameasLabel").length;i++)
	document.getElementsByName("showParameterNameasLabel")[i].value="1";
	
/* for(i=0;i<document.getElementById("TextBoxShowParameterNameasLabel1").length;i++)
	document.getElementsByName("showParameterNameasLabel")[i].value="0";
	 */
	
 
//alert(document.getElementsByName("elementType")[0].value);
  if(obj.value=='C')
	{

	  document.getElementById("checkbox1").style.display="";

	  document.getElementById("Label1").style.display="none";
	  document.getElementById("textBox1").style.display="none";
	  document.getElementById("textArea1").style.display="none";
	  document.getElementById("combo1").style.display="none"; 
	  document.getElementById("button1").style.display="none"; 
	  document.getElementById("querycombo1").style.display="none"; 
	  document.getElementById("listBox1").style.display="none";
	  document.getElementById("Auto1").style.display="none"; 
	  document.getElementById("urltbl1").style.display="none";
	  document.getElementById("urltbl").style.display="none";
	  document.getElementById("hyperlink1").style.display="none";
	  document.getElementById("snomedct").style.display="none";
	  if(document.getElementsByName("showParameterNameasLabel")[0].value=='1') 
	   {
	   
	   document.getElementById("checkboxx1").style.display="";
	   document.getElementById("checkboxx2").style.display="";
	   document.getElementById("checkboxx3").style.display="";

	   }
	   if(document.getElementsByName("showParameterNameasLabel")[0].value=='0')
	   {
	   
	   document.getElementById("checkboxx1").style.display="none";
	   document.getElementById("checkboxx2").style.display="none";
	   document.getElementById("checkboxx3").style.display="none";

	   }

	}
	 else if(obj.value=='A')//HYPERLINK
	{
  // alert("chandan");
  document.getElementById("urltbl1").style.display="";
   document.getElementById("urltbl").style.display="";
   document.getElementById("hyperlink1").style.display="";

   document.getElementById("Label1").style.display="none";
   document.getElementById("textBox1").style.display="none";
   document.getElementById("textArea1").style.display="none";
   document.getElementById("combo1").style.display="none"; 
   document.getElementById("button1").style.display="none"; 
   document.getElementById("querycombo1").style.display="none"; 
   document.getElementById("listBox1").style.display="none";
   document.getElementById("Auto1").style.display="none"; 
   document.getElementById("urltbl1").style.display="none";
   document.getElementById("urltbl").style.display="none";
   document.getElementById("checkbox1").style.display="none";
   document.getElementById("snomedct").style.display="none";
   
   if(document.getElementsByName("showParameterNameasLabel")[0].value=='1') 
   {
	   document.getElementById("urltbl1").style.display="";
	   document.getElementById("urltbl").style.display="";
   document.getElementById("hyper1").style.display="";
   document.getElementById("hyper2").style.display="";
   document.getElementById("hyper3").style.display="";

   }
   if(document.getElementsByName("showParameterNameasLabel")[0].value=='0')
   {
	   document.getElementById("urltbl1").style.display="none";
	   document.getElementById("urltbl").style.display="none";
   document.getElementById("hyper1").style.display="none";
   document.getElementById("hyper2").style.display="none";
   document.getElementById("hyper3").style.display="none";

   }
   
	}
else if(obj.value=='B')//label
  {
   
  document.getElementById("Label1").style.display="";
  document.getElementById("textBox1").style.display="none";
  document.getElementById("textArea1").style.display="none";
  document.getElementById("combo1").style.display="none"; 
  document.getElementById("button1").style.display="none"; 
  document.getElementById("querycombo1").style.display="none"; 
  document.getElementById("listBox1").style.display="none";
  document.getElementById("Auto1").style.display="none"; 
  document.getElementById("urltbl1").style.display="none";
  document.getElementById("urltbl").style.display="none";
  document.getElementById("hyperlink1").style.display="none";
  document.getElementById("checkbox1").style.display="none";
  document.getElementById("snomedct").style.display="none";
  }
   
  else if(obj.value=='E')//label
  {
	///  alert(document.getElementsByName("elementType")[0].value);
   
  document.getElementById("textBox1").style.display="";
  document.getElementById("Label1").style.display="none";
  document.getElementById("textArea1").style.display="none";
  document.getElementById("combo1").style.display="none"; 
  document.getElementById("button1").style.display="none"; 
  document.getElementById("querycombo1").style.display="none"; 
  document.getElementById("listBox1").style.display="none";
  document.getElementById("Auto1").style.display="none"; 
  document.getElementById("urltbl1").style.display="none";
  document.getElementById("urltbl").style.display="none";
  document.getElementById("hyperlink1").style.display="none";
  document.getElementById("checkbox1").style.display="none";
  document.getElementById("snomedct").style.display="none";
  
  if(document.getElementsByName("showParameterNameasLabel")[0].value=='1') 
  {
  
  document.getElementById("subtext1").style.display="";
  document.getElementById("subtext2").style.display="";
  document.getElementById("subtext3").style.display="";

  }
   
  }
  
  else if(obj.value=='H')//label
  {
	//  alert(document.getElementsByName("elementType")[0].value);
   
  document.getElementById("textBox1").style.display="none";
  document.getElementById("Label1").style.display="none";
  document.getElementById("textArea1").style.display="";
  document.getElementById("combo1").style.display="none"; 
  document.getElementById("button1").style.display="none"; 
  document.getElementById("querycombo1").style.display="none"; 
  document.getElementById("listBox1").style.display="none";
  document.getElementById("Auto1").style.display="none"; 
  document.getElementById("urltbl1").style.display="none";
  document.getElementById("urltbl").style.display="none";
  document.getElementById("hyperlink1").style.display="none";
  document.getElementById("checkbox1").style.display="none";
  document.getElementById("snomedct").style.display="none";
  if(document.getElementsByName("showParameterNameasLabel")[0].value=='1') 
  {
  
  document.getElementById("subtextarea1").style.display="";
  document.getElementById("subtextarea2").style.display="";
  document.getElementById("subtextarea3").style.display="";

  }
  }
  
  else if(obj.value=='D')//label
  {
	//  alert(document.getElementsByName("elementType")[0].value);
   
  document.getElementById("textBox1").style.display="none";
  document.getElementById("Label1").style.display="none";
  document.getElementById("textArea1").style.display="none";
  document.getElementById("combo1").style.display=""; 
  document.getElementById("button1").style.display="none"; 
  document.getElementById("querycombo1").style.display="none"; 
  document.getElementById("listBox1").style.display="none";
  document.getElementById("Auto1").style.display="none"; 
  document.getElementById("urltbl1").style.display="none";
  document.getElementById("urltbl").style.display="none";
  document.getElementById("hyperlink1").style.display="none";
  document.getElementById("checkbox1").style.display="none";
  document.getElementById("snomedct").style.display="none";
  }
  
  else if(obj.value=='J')//label
  {
	//  alert(document.getElementsByName("elementType")[0].value);
   
  document.getElementById("textBox1").style.display="none";
  document.getElementById("Label1").style.display="none";
  document.getElementById("textArea1").style.display="none";
  document.getElementById("combo1").style.display="none"; 
  document.getElementById("button1").style.display=""; 
  document.getElementById("querycombo1").style.display="none"; 
  document.getElementById("listBox1").style.display="none";
  document.getElementById("Auto1").style.display="none"; 
  document.getElementById("urltbl1").style.display="none";
  document.getElementById("urltbl").style.display="none";
  document.getElementById("hyperlink1").style.display="none";
  document.getElementById("checkbox1").style.display="none";
  document.getElementById("snomedct").style.display="none";
  }
  else if(obj.value=='K')//label
  {
	//  alert(document.getElementsByName("elementType")[0].value);
   
  document.getElementById("textBox1").style.display="none";
  document.getElementById("Label1").style.display="none";
  document.getElementById("textArea1").style.display="none";
  document.getElementById("combo1").style.display="none"; 
  document.getElementById("button1").style.display="none"; 
  document.getElementById("querycombo1").style.display=""; 
  document.getElementById("listBox1").style.display="none";
  document.getElementById("Auto1").style.display="none";
  document.getElementById("urltbl1").style.display="none";
  document.getElementById("urltbl").style.display="none";
  document.getElementById("hyperlink1").style.display="none";
  document.getElementById("checkbox1").style.display="none";
  document.getElementById("snomedct").style.display="none";
  }
  else if(obj.value=='Z')//label
  {
	//  alert(document.getElementsByName("elementType")[0].value);
   
  document.getElementById("textBox1").style.display="none";
  document.getElementById("Label1").style.display="none";
  document.getElementById("textArea1").style.display="none";
  document.getElementById("combo1").style.display="none"; 
  document.getElementById("button1").style.display="none"; 
  document.getElementById("querycombo1").style.display="none";
  document.getElementById("listBox1").style.display="";
  document.getElementById("Auto1").style.display="none"; 
  document.getElementById("urltbl1").style.display="none";
  document.getElementById("urltbl").style.display="none";
  document.getElementById("hyperlink1").style.display="none";
  document.getElementById("checkbox1").style.display="none";
  document.getElementById("snomedct").style.display="none";
  if(document.getElementsByName("showParameterNameasLabel")[0].value=='1') 
  {
  
  document.getElementById("sublist1").style.display="";
  document.getElementById("sublist2").style.display="";
  document.getElementById("sublist3").style.display="";

  }
  }  else if(obj.value=='P')//label
  {
		//  alert(document.getElementsByName("elementType")[0].value);
	 
	  document.getElementById("textBox1").style.display="none";
	  document.getElementById("Label1").style.display="none";
	  document.getElementById("textArea1").style.display="none";
	  document.getElementById("combo1").style.display="none"; 
	  document.getElementById("button1").style.display="none"; 
	  document.getElementById("querycombo1").style.display="none"; 
	  document.getElementById("listBox1").style.display="none";
	  document.getElementById("Auto1").style.display=""; 
	  document.getElementById("urltbl1").style.display="none";
	  document.getElementById("urltbl").style.display="none";
	  document.getElementById("hyperlink1").style.display="none";
	  document.getElementById("checkbox1").style.display="none";
	  document.getElementById("snomedct").style.display="none";
	  }
  else if(obj.value=='S') //Snomed CT
  {
	  //alert("fdsf");
	  document.getElementById("snomedct").style.display="";
	  document.getElementById("textBox1").style.display="none";
	  document.getElementById("Label1").style.display="none";
	  document.getElementById("textArea1").style.display="none";
	  document.getElementById("combo1").style.display="none"; 
	  document.getElementById("button1").style.display="none"; 
	  document.getElementById("querycombo1").style.display="none"; 
	  document.getElementById("listBox1").style.display="none";
	  document.getElementById("Auto1").style.display="none"; 
	  document.getElementById("urltbl1").style.display="none";
	  document.getElementById("urltbl").style.display="none";
	  document.getElementById("hyperlink1").style.display="none";
	  document.getElementById("checkbox1").style.display="none";
	  
	  if(document.getElementsByName("showParameterNameasLabel")[0].value=='1') 
	  {
	  
	  document.getElementById("subtext1").style.display="";
	  document.getElementById("subtext2").style.display="";
	  document.getElementById("subtext3").style.display="";

	  }
  }
     else //combo
      {
	  document.getElementById("Label1").style.display="none";
	  
	  document.getElementById("textBox1").style.display="none";
	  document.getElementById("textArea1").style.display="none";
	  document.getElementById("combo1").style.display="none";
	  document.getElementById("button1").style.display="none"; 
	  document.getElementById("querycombo1").style.display="none";
	  document.getElementById("listBox1").style.display="none";
	  document.getElementById("urltbl1").style.display="none";
	  document.getElementById("urltbl").style.display="none";
	  document.getElementById("hyperlink1").style.display="none";
	  document.getElementById("checkbox1").style.display="none";
      }

  
 
  }
  
  function Showpara(obj)
  {
 
 //alert(obj.value);
   if(obj.value=='1') 
  {
  
  document.getElementById("subtext1").style.display="";
  document.getElementById("subtext2").style.display="";
  document.getElementById("subtext3").style.display="";

  }
   
  else  
  {
	  document.getElementById("subtext1").style.display="none";
	  document.getElementById("subtext2").style.display="none";
	  document.getElementById("subtext3").style.display="none";

  }
 
  } 

  function Showpara1(obj)
  {
 
    //alert(obj.value);
   if(obj.value=='1') 
  {
  
  document.getElementById("hyper1").style.display="";
  document.getElementById("hyper2").style.display="";
  document.getElementById("hyper3").style.display="";

  }
   
  else  
  {
	  document.getElementById("hyper1").style.display="none";
	  document.getElementById("hyper2").style.display="none";
	  document.getElementById("hyper3").style.display="none";

  }
 
  } 



  function Showpara2(obj)
  {
 
    //alert(obj.value);
   if(obj.value=='1') 
  {
  
  document.getElementById("checkboxx1").style.display="";
  document.getElementById("checkboxx2").style.display="";
  document.getElementById("checkboxx3").style.display="";

  }
   
  else  
  {
	  document.getElementById("checkboxx1").style.display="none";
	  document.getElementById("checkboxx2").style.display="none";
	  document.getElementById("checkboxx3").style.display="none";

  }
 
  } 
  
  function  ShowDefaultEditorModify(obj, editorValue)
  {
	  var f = obj.checked;
		 
	  if(f == true)//showDefaultValue
	  {
		  document.getElementById("subtextareaeditor1").style.display="";
		  document.getElementsByName("showDefaultValue")[0].value="1";
		 
		  var editor = CKEDITOR.instances.editor1;
		  if(editor != null)
			  editor.setData(editorValue);
	  }
	  else
	  {
		 document.getElementById("subtextareaeditor1").style.display="none";
		 document.getElementsByName("showDefaultValue")[0].value="0";
		 document.getElementById("defaultTextAreaValue").value = editorValue;
	  }
  }
  
  function ShowDefaultEditor(obj)
  {	  
	  var f = obj.checked;
	 
	  if(f == true)//showDefaultValue
	  {
		  document.getElementById("subtextareaeditor1").style.display="";
		  document.getElementsByName("showDefaultValue")[0].value="1";
		  document.getElementById("defaultTextAreaValue").value = "";
		 /* var editor = CKEDITOR.instances.editor1;
		  if(editor != null)
			  editor.setData(editorValue);*/
	  }
	  else
	  {
		 document.getElementById("subtextareaeditor1").style.display="none";
		 document.getElementsByName("showDefaultValue")[0].value="0";
		 var editor = CKEDITOR.instances.editor1;
		  if(editor != null)
			  editor.setData("");
	  }
  }
  
  function Showtextarea(obj)
  {
  
   
 
//alert(document.getElementsByName("showParameterNameasLabel")[0].value);
  if(obj.value=='1') 
  {
  //alert("11");
  document.getElementById("subtextarea1").style.display="";
  document.getElementById("subtextarea2").style.display="";
  document.getElementById("subtextarea3").style.display="";

  
   
  }
  
  else  
  {
	  document.getElementById("subtextarea1").style.display="none";
	  document.getElementById("subtextarea2").style.display="none";
	  document.getElementById("subtextarea3").style.display="none";

  }

 
  
 
  } 
  
   function Showlistvalue(obj)
  {
	  if(obj.value=="1")
		  {
		  document.getElementById("sublist1").style.display="";
		  document.getElementById("sublist2").style.display="";
		  document.getElementById("sublist3").style.display="";
		  }
	  else{
		  document.getElementById("sublist1").style.display="none";
		  document.getElementById("sublist2").style.display="none";
		  document.getElementById("sublist3").style.display="none";
		  
		  
	  }
  } 
  
  function Showcombovalue(obj)
  {
  
   
 
//alert(document.getElementsByName("showParameterNameasLabel")[0].value);
  if(obj.value=='1') 
  {
  ////alert("11");
  document.getElementById("subcombo1").style.display="";
  document.getElementById("subcombo2").style.display="";
  
  
   
  }
   
 
  else  
  {
	  document.getElementById("subcombo1").style.display="none";
	  document.getElementById("subcombo2").style.display="none";
	  
  }
  }
  function Showbuttonvalue(obj)
  {
  
   
 
///alert(document.getElementsByName("showParameterNameasLabel")[0].value);
  if(obj.value=='1') 
  {
 // alert("11");
  document.getElementById("subbutton1").style.display="";
  document.getElementById("subbutton2").style.display="";
  
  
   
  }
   
 
  else  
  {
	  document.getElementById("subbutton1").style.display="none";
	  document.getElementById("subbutton2").style.display="none";
	  
  }
  }
  function Showquerycombovalue(obj)
  {
  
   
 
//alert(document.getElementsByName("showParameterNameasLabel")[0].value);
  if(obj.value=='1') 
  {
  //alert("11");
  document.getElementById("subquerycombo1").style.display="";
  document.getElementById("subquerycombo2").style.display="";
  
  
   
  }
   
 
  else  
  {
	  document.getElementById("subquerycombo1").style.display="none";
	  document.getElementById("subquerycombo2").style.display="none";
	  
  }
  }
  
  function setParameter(obj)
  {

	  //alert(document.getElementsByName("testCodee")[0].value);
	  /* var val=obj.value;
	  //alert(val);
	  var hmode="SETPARAMETER"; // create function in action file, UTIL,...... with query and populate the list in session and rediredt to NEW in ACTION
	  document.getElementsByName("showDefaultValue")[0].checked = false;
	  var editor = CKEDITOR.instances.editor1;
	  if(editor != null)
	  {
		  editor.setData("");
	  }
	  document.forms[0].hmode.value=hmode;
	  
		document.forms[0].submit();  */
	 
  
  }
  
  function clearForm()
  {
   
     submitPage('CLEAR');
  
  }
  
  
  function ShowAutovalue(obj)
  {
  
   
 
//alert(document.getElementsByName("showParameterNameasLabel")[0].value);
  if(obj.value=='1') 
  {
  ////alert("11");
  document.getElementById("subAuto1").style.display="";
  document.getElementById("subAuto2").style.display="";
  
  
   
  }
   
 
  else  
  {
	  document.getElementById("subAuto1").style.display="none";
	  document.getElementById("subAuto2").style.display="none";
	  
  }
  }
  
	function setFunctionality(obj)
	{
	if(document.getElementsByName("functionality")[0].checked==true)
	{
	
	document.getElementById("eventTextBox").value="3";
	document.getElementById("eventFunctionTextBox").value="appendTable(this)";
	}
	
	else
	{
	
	document.getElementById("eventTextBox").value="0";
	document.getElementById("eventFunctionTextBox").value="";
	}
	
	}	
	
</script>


 

	<html:form action="/masters/TestParameterMstACT">


		<html:hidden name="TestParameterMstFB" property="hmode" />
		
		<html:hidden name="TestParameterMstFB" property="masterreqformcheck" />
		
		<html:hidden name="TestParameterMstFB" property="elementProperty" />
		<html:hidden name="TestParameterMstFB" property="selectedChk" />
		<html:hidden name="TestParameterMstFB" property="textEditorValue" />
			<html:hidden name="TestParameterMstFB" property="parentId" />
		
	 





		<%!
		boolean readOnly;
	%>
		<% this.readOnly=false;%>

		<logic:equal name="TestParameterMstFB" property="hmode" value="VIEW">
			<% this.readOnly=true; %>
		</logic:equal>


		<his:TransactionContainer>
			<his:TitleTag name="Test Parameter Master">
			<%-- 	<his:insertDateTag /> --%>
			</his:TitleTag>
			<his:ContentTag>


				<table width="100%" border="0" cellspacing="1" cellpadding="0">

					<logic:equal name="TestParameterMstFB" property="hmode" value="ADD">

						<tr>
						
						    <td width="30%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							*	 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="TestType"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="70%" class="tdfont">
			      <div align="left">
					  
				       
						<html:radio name="TestParameterMstFB"  tabindex="1" property="paraType" value="0"  onclick="reqformElementsSet()"></html:radio>
						<bean:message key="testPara"/>
						<html:radio name="TestParameterMstFB" tabindex="1" property="paraType" value="1" onclick="reqformElementsSet()"></html:radio>
						<bean:message key="deptPara"/>
					    <html:radio name="TestParameterMstFB" tabindex="1" property="paraType" value="2" onclick="reqformElementsSet()"></html:radio>
						<bean:message key="resourceForm"/> 
					    
				  </div>
			     </td>
			     </tr>
			           
			       <tr id="masterone" style="display: none;">
			     
			     
							<td width="30%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif">Master Form&nbsp;
									</font>
								</div>
							</td>
							<td width="70%" class="tdfont" >
									<div align="left">

										<html:select name="TestParameterMstFB" property="reqMasterFormType"
											onchange="derivedcombo()" tabindex="1">
											<html:option value="-1" >Select Value</html:option>
											<html:option value="0" >Normal Form</html:option>
											<html:option value="1">Master Form</html:option>
											<html:option value="2">Derived Form</html:option>
										</html:select>
									</div>
								</td>
						</tr>
	
	
	  <tr id="derivedcomboshw" style="display: none;">
			     
			     
							<td width="30%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> Master Test&nbsp;
									</font>
								</div>
							</td>
							<td width="70%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.MASTERTYPE_TEST_COMBO %>">
									<div align="left">

										<html:select name="TestParameterMstFB" property="mastertestCode"
											 tabindex="1">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.MASTERTYPE_TEST_COMBO %>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>
						</tr>
		
	            			<!-- chanksss -->		
			     <tr id="req1">
			     
			     
							<td width="30%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="testName" />&nbsp;
									</font>
								</div>
							</td>
							<td width="70%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.TEST_COMBO %>">
									<div align="left">

										<html:select name="TestParameterMstFB" property="testCode"
											onchange="setParameter(this)" tabindex="1">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.TEST_COMBO %>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>
						</tr>

                         
                          <tr id="req112" style="display: none">
			     
			     
							<td width="30%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="testName" />&nbsp;
									</font>
								</div>
							</td>
							<td width="70%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.TEST_COMBO_REQQ %>">
									<div align="left">

										<html:select name="TestParameterMstFB" property="testCodee"
											onchange="setParameter(this)" tabindex="1">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.TEST_COMBO_REQQ %>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>
						</tr>
                         
                         
                         

						<tr id="req2">
							<td width="30%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="testParaName" />&nbsp;
									</font>
								</div>
							</td>
							<td width="70%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.PARAMETER_COMBO %>">
									<div align="left">

										<html:select name="TestParameterMstFB"
											property="parameterCode" onchange="ShowValue(this)"
											tabindex="1">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.PARAMETER_COMBO %>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>
						</tr>
					</logic:equal>
					<logic:equal name="TestParameterMstFB" property="hmode"
						value="SAVE">

	<tr id="req3">
						
						    <td width="30%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							*	 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="TestType"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="70%" class="tdfont">
			      <div align="left">
					  
				       
						<html:radio name="TestParameterMstFB"  tabindex="1" property="paraType" value="0"  onclick="reqformElementsSet()"></html:radio>
						<bean:message key="testPara"/>
						<html:radio name="TestParameterMstFB" tabindex="1" property="paraType" value="1" onclick="reqformElementsSet()"></html:radio>
						<bean:message key="deptPara"/>
					    <html:radio name="TestParameterMstFB" tabindex="1" property="paraType" value="2" onclick="reqformElementsSet()"></html:radio>
						<bean:message key="resourceForm"/> 
					    
				  </div>
			     </td>
			     </tr>
			      <tr id="masterone" style="display: none;">
			     
			     
							<td width="30%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif">Master Form&nbsp;
									</font>
								</div>
							</td>
							<td width="70%" class="tdfont" >
									<div align="left">

										<html:select name="TestParameterMstFB" property="reqMasterFormType"
											onchange="derivedcombo()" tabindex="1">
											<html:option value="-1" >Select Value</html:option>
											<html:option value="0" >Normal Form</html:option>
											<html:option value="1">Master Form</html:option>
											<html:option value="2">Derived Form</html:option>
										</html:select>
									</div>
								</td>
						</tr>
						  <tr id="derivedcomboshw" style="display: none;">
			     
			     
							<td width="30%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> Master Test&nbsp;
									</font>
								</div>
							</td>
							<td width="70%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.MASTERTYPE_TEST_COMBO %>">
									<div align="left">

										<html:select name="TestParameterMstFB" property="mastertestCode"
											 tabindex="1">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.MASTERTYPE_TEST_COMBO %>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>
						</tr>
						
						
						<tr id="req1">
							<td width="30%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="testName" />&nbsp;
									</font>
								</div>
							</td>
							<td width="70%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.TEST_COMBO %>">
									<div align="left">

										<html:select name="TestParameterMstFB" property="testCode"
											onchange="setParameter(this)" tabindex="1">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.TEST_COMBO %>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>
						</tr>

<tr id="req112" style="display: none">
			     
			     
							<td width="30%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="testName" />&nbsp;
									</font>
								</div>
							</td>
							<td width="70%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.TEST_COMBO_REQQ %>">
									<div align="left">

										<html:select name="TestParameterMstFB" property="testCodee"
											onchange="setParameter(this)" tabindex="1">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.TEST_COMBO_REQQ %>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>
						</tr>

						<tr id="req2">
							<td width="30%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="testParaName" />&nbsp;
									</font>
								</div>
							</td>
							<td width="70%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.PARAMETER_COMBO %>">
									<div align="left">

										<html:select name="TestParameterMstFB"
											property="parameterCode" onchange="ShowValue(this)"
											tabindex="1">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.PARAMETER_COMBO %>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>
						</tr>
					</logic:equal>

					<logic:equal name="TestParameterMstFB" property="hmode"
						value="MODIFYSAVE">

						<tr id="req6">
							<td width="30%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="testName" />&nbsp;
									</font>
								</div>
							</td>
							<td width="70%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.TEST_COMBO %>">
									<div align="left">

										<html:select name="TestParameterMstFB" property="testCode"
											tabindex="1">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.TEST_COMBO %>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>
						</tr>



						<tr id="req7">
							<td width="30%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="testParaName" />&nbsp;
									</font>
								</div>
							</td>
							<td width="70%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.PARAMETER_COMBO %>">
									<div align="left">

										<html:select name="TestParameterMstFB"
											property="parameterCode" onchange="ShowValue()" tabindex="1">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.PARAMETER_COMBO %>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>
						</tr>
					</logic:equal>

					<logic:equal name="TestParameterMstFB" property="hmode"
						value="MODIFY">

						<tr id="req8"> 
						
						
						
						    <td width="30%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							*	 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="TestType"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="70%" class="tdfont">
			      <div align="left">
					  
				       
						<html:radio name="TestParameterMstFB"  tabindex="1" property="paraType" value="0" disabled="true" ></html:radio>
						<bean:message key="testPara"/>
						<html:radio name="TestParameterMstFB" tabindex="1" property="paraType" value="1" disabled="true" ></html:radio>
						<bean:message key="deptPara"/>
					    <html:radio name="TestParameterMstFB" tabindex="1" property="paraType" value="2" disabled="true" ></html:radio>
						<bean:message key="resourceForm"/> 
					    
					    <html:hidden name="TestParameterMstFB" property="paraType" />
				  </div>
			     </td>
			     
			     </tr>
			     <tr id="req9">
			     
			     
			     
							<td width="30%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="testName" />&nbsp;
									</font>
								</div>
							</td>
							<td width="70%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.TEST_COMBO %>">
									<div align="left">

										<html:select name="TestParameterMstFB" property="testCode"
											disabled="true" tabindex="1">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.TEST_COMBO %>"
												property="value" labelProperty="label" />
										</html:select>
										<html:hidden name="TestParameterMstFB" property="testCode" />
									</div>
								</logic:present></td>
						</tr>



						<tr id="req10">
							<td width="30%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="testParaName" />&nbsp;
									</font>
								</div>
							</td>
							<td width="70%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.PARAMETER_COMBO %>">
									<div align="left">

										<html:select name="TestParameterMstFB"
											property="parameterCode" disabled="true"
											onchange="ShowValue()" tabindex="1">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.PARAMETER_COMBO %>"
												property="value" labelProperty="label" />
										</html:select>
										<html:hidden name="TestParameterMstFB"
											property="parameterCode" />

									</div>
								</logic:present></td>
						</tr>
					</logic:equal>
					<tr id="req11">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="ParentParameter" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.PARAMETER_COMBO %>">
								<div align="left">

									<html:select name="TestParameterMstFB"
										property="parentParameter" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.PARAMETER_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>

								</div>
							</logic:present></td>
					</tr>

					<tr id="req12">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">* </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="criteria" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.CRITERIA_COMBO %>">
								<div align="left">

									<html:select name="TestParameterMstFB" property="criteriaDesk"
										tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.CRITERIA_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present></td>
					</tr>

					<tr id="req13">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="ArrangedAs" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select name="TestParameterMstFB" property="arrangeAs"
									disabled="true" style="width:160;" tabindex="1">
									<html:option value="1">Simple</html:option>
									<html:option value="2">Horizontal Matrix</html:option>
									<html:option value="3">Vertical matrix</html:option>
								</html:select>
								<html:hidden name="TestParameterMstFB" value="1"
									property="arrangeAs" />
							</div>
						</td>
					</tr>

					<tr id="req14">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="IsPrintRequired" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="TestParameterMstFB" tabindex="1"
									property="isPrintRequired" value="0"></html:radio>

								<bean:message key="yes" />
								<html:radio name="TestParameterMstFB" tabindex="1"
									property="isPrintRequired" value="1"></html:radio>

								<bean:message key="no" />


							</div>
						</td>
					</tr>
					<tr id="req15">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="ElementPosition" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text name="TestParameterMstFB" property="elementPosition"
									maxlength="3" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateNumeric(event,this)" tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
					
					<tr id="req16">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">* </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="loincScale" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LOINC_SCALE_COMBO %>">
								<div align="left">

									<html:select name="TestParameterMstFB" property="loincScale"
										tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LOINC_SCALE_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present></td>
					</tr>

					

					<tr id="req17">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="remarks" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:textarea name="TestParameterMstFB" property="remarks"
									tabindex="1" readonly="<%=this.readOnly %>"
									onkeypress="return CheckMaxLength(event,this,50,3)">
								</html:textarea>
							</div>
						</td>
					</tr>

                <logic:notEqual name="TestParameterMstFB" property="hmode" value="MODIFY">
					<tr id="req18">
						<td class="HEADER" width='100%' colspan="6"
							style="font-size: 14px; font-weight: bold;"><b><bean:message
									key="ObjectProperties" /></b>&nbsp;
									<!-- ddddd -->
									</td>
					</tr>
             </logic:notEqual>

            <logic:equal name="TestParameterMstFB" property="hmode" value="MODIFY">
					<tr id="req19">
						<td class="HEADER" width='100%' colspan="6"
							style="font-size: 14px; font-weight: bold;"><b><bean:message
									key="ObjectProperties" /></b>&nbsp;
									</td>
					</tr>
            </logic:equal>
					<tr id="req20">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="ElementType" />&nbsp;
								</font>
							</div>
						</td>
						<td width="30%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.ELEMENT_TYPE_COMBO %>">
								<div align="left">

									<html:select name="TestParameterMstFB" property="elementType"
										onchange="onSelect(this)" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.ELEMENT_TYPE_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present>
							
						
							
							</td>
							
								
					</tr>
					
				 <!-- chanksss -->
                  <tr>
                  
                 <!--  <td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
								</font>
							</div>
						</td> -->
                  
                  
                  </tr>


					<!-- Label description -->




				</table>
	<table id="urltbl" style="display: none">
							<tr>
							<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">
									URL
								</font>
							</div>
						</td>
						<td>
						<td width="70%" class="tdfont">
							<div align="left"><logic:present
								name="<%=InvestigationConfig.URL_MAP_COMBO %>">
								<html:select name="TestParameterMstFB" property="urlCode"
										tabindex="1">
										<html:option value="-1">Select Value</html:option>
									 <html:options
											collection="<%=InvestigationConfig.URL_MAP_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
									</logic:present>
							</div>
						</td>
						</tr>
						
						
							
							</table>
							
							<table id="urltbl1" style="display: none">
							<tr>
							<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">
									HyperLink Name
								</font>
							</div>
						</td>
						<td>
						<td width="70%" class="tdfont">
							<div align="left">
								
							<html:text  name="TestParameterMstFB" property="hyperName"></html:text>
									
							</div>
						</td>
						</tr>
						</table>
						
				<table width="100%" id="Label1" style="display: none;">

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabelAlignment" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="labelAlignmentLabel" name="TestParameterMstFB" property="labelAlignment"
									style="width:160;" tabindex="1">
									<html:option value="0">Left</html:option>
									<html:option value="1">Center</html:option>
									<html:option value="2">Right</html:option>
								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Bold" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="boldLabel" name="TestParameterMstFB" property="bold"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Underline" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="underlineLabel" name="TestParameterMstFB" property="underline"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>

				</table>

				<!-- End of Label Description -->



				<!-- text Box description -->




				<table width="100%" id="textBox1" style="display: none;">

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="showParameterNameasLabel" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="TextBoxShowParameterNameasLabel" name="TestParameterMstFB"
									property="showParameterNameasLabel" onchange="Showpara(this)"
									style="width:160;" tabindex="1">
								
									<html:option value="0">No</html:option>
										<html:option value="1">Yes</html:option>
									

								</html:select>
							</div>
						</td>
					</tr>



					<tr id="subtext3" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabelAlignment" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="labelAlignmentTextBox" name="TestParameterMstFB" property="labelAlignment"
									style="width:160;" tabindex="1">
									<html:option value="0">Left</html:option>
									<html:option value="1">Center</html:option>
									<html:option value="2">Right</html:option>
								</html:select>
							</div>
						</td>
					</tr>



					<tr id="subtext1" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Bold" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="boldTextBox" name="TestParameterMstFB" property="bold"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option  value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>

					<tr id="subtext2" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Underline" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="underlineTextBox" name="TestParameterMstFB" property="underline"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="ElementAlign" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="elementAlignTextBox" name="TestParameterMstFB" property="elementAlign"
									style="width:160;" tabindex="1">
									<html:option value="0">Left</html:option>
									<html:option value="1">Center</html:option>
									<html:option value="2">Right</html:option>
								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Size" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text name="TestParameterMstFB" property="size"
									maxlength="5" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateNumeric(event,this)" tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Maxlength" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text name="TestParameterMstFB" property="maxlength"
									maxlength="5" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateNumeric(event,this)" tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Event" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select  styleId="eventTextBox"  name="TestParameterMstFB" property="event"
									style="width:160;" tabindex="1">
									<html:option value="0">Key Press</html:option>
									<html:option value="1">Click</html:option>
									<html:option value="2">Blur</html:option>
									<html:option value="3">onChange</html:option>
								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="EventFunction" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text  styleId = "eventFunctionTextBox" name="TestParameterMstFB" property="eventFunction"
									maxlength="50" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphabetsOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
					<!--  Default Value -->
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Default &nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text styleId = "defaultTextBoxValue" name="TestParameterMstFB" property="defaultTextBoxValue"
									maxlength="100" size="30"							
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
					
					
					<!-- is S.No Functionality / Date Functionality -->
					
						  <tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
						<font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Functionality &nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
							       <html:radio name="TestParameterMstFB" onclick="setFunctionality(this)"  tabindex="1" property="functionality" value="0"  ></html:radio>
									 &nbsp;Number
									 &nbsp; &nbsp;
								   <html:radio name="TestParameterMstFB" onclick="setFunctionality(this)" tabindex="1" property="functionality" value="1"  ></html:radio>
								&nbsp;Date
									
							
							</div>
						</td>
					</tr>
					
				</table>



				<!-- End of text Box Description -->


       <!-- HYPER LINK DESCRIPTION -->
       
       
				<table width="100%" id="hyperlink1" style="display: none;">

					<tr>
					<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="showParameterNameasLabel" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="TextBoxShowParameterNameasLabel1" name="TestParameterMstFB"
									property="showParameterNameasLabel" onchange="Showpara1(this)"

									style="width:160;" tabindex="1" >
									style="width:160;" tabindex="1" >
									

									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>
									
									

								</html:select>
							</div>
						</td>
					</tr>
					
					<tr id="hyper3" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabelAlignment" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="labelAlignmentTextBox1" name="TestParameterMstFB" property="labelAlignment"
									style="width:160;" tabindex="1">
									<html:option value="0">Left</html:option>
									<html:option value="1">Center</html:option>
									<html:option value="2">Right</html:option>
								</html:select>
							</div>
						</td>
					</tr>



					<tr id="hyper1" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Bold" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="boldTextBox1" name="TestParameterMstFB" property="bold"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option  value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>

					<tr id="hyper2" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Underline" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="underlineTextBox" name="TestParameterMstFB" property="underline"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>
					
					</table>

 
           <!-- CheckBox description -->

<table width="100%" id="checkbox1" style="display: none;">

					<tr>
					<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="showParameterNameasLabel" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="TextBoxShowParameterNameasLabel2" name="TestParameterMstFB"
									property="showParameterNameasLabel" onchange="Showpara2(this)"

									style="width:160;" tabindex="1" >
									style="width:160;" tabindex="1" >
									

									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>
									
									

								</html:select>
							</div>
						</td>
					</tr>
					
					<tr id="checkboxx3" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabelAlignment" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="labelAlignmentTextBox2" name="TestParameterMstFB" property="labelAlignment"
									style="width:160;" tabindex="1">
									<html:option value="0">Left</html:option>
									<html:option value="1">Center</html:option>
									<html:option value="2">Right</html:option>
								</html:select>
							</div>
						</td>
					</tr>



					<tr id="checkboxx1" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Bold" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="boldTextBox2" name="TestParameterMstFB" property="bold"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option  value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>

					<tr id="checkboxx2" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Underline" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="underlineTextBox2" name="TestParameterMstFB" property="underline"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>
					
					</table>


          <!-- end checkbox description -->

				<!-- text AREA description -->



				<table width="100%" id="textArea1" style="display: none;">

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="showParameterNameasLabel" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="TextAreaShowParameterNameasLabel" name="TestParameterMstFB"
									property="showParameterNameasLabel"
									onchange="Showtextarea(this)" style="width:160;" tabindex="1">									
									<html:option value="1">Yes</html:option>
									<html:option value="0">No</html:option>

								</html:select>
							</div>
						</td>
					</tr>
						
					
						
						
					<tr id="subtextarea3" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabelAlignment" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="labelAlignmentTextArea" name="TestParameterMstFB" property="labelAlignment"
									style="width:160;" tabindex="1">
									<html:option value="0">Left</html:option>
									<html:option value="1">Center</html:option>
									<html:option value="2">Right</html:option>
								</html:select>
							</div>
						</td>
					</tr>



					<tr id="subtextarea1" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Bold" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="boldTextArea" name="TestParameterMstFB" property="bold"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>

					<tr id="subtextarea2" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Underline" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="underlineTextArea" name="TestParameterMstFB" property="underline"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>


					


					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="ElementAlign" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="elementAlignTextArea" name="TestParameterMstFB" property="elementAlign"
									style="width:160;" tabindex="1">
									<html:option value="0">Left</html:option>
									<html:option value="1">Center</html:option>
									<html:option value="2">Right</html:option>
								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Rows" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text name="TestParameterMstFB" property="rows"
									maxlength="5" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateNumeric(event,this)" tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Columns" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text name="TestParameterMstFB" property="columns"
									maxlength="5" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateNumeric(event,this)" tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Event" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="eventTextArea" name="TestParameterMstFB" property="event"
									style="width:160;" tabindex="1">
									<html:option value="0">Key Press</html:option>
									<html:option value="1">Click</html:option>
									<html:option value="2">Blur</html:option>
									<html:option value="3">onChange</html:option>
								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="EventFunction" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text styleId = "eventFunctionTextArea" name="TestParameterMstFB" property="eventFunction"
									maxlength="50" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphabetsOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
					
					<!-- For Default Value of Text Area -->
					
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Default &nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<%-- <html:text styleId = "defaultTextAreaValue" name="TestParameterMstFB" property="defaultTextValue"
									maxlength="100" size="30"							
									tabindex="1">
								</html:text> --%>
								
								<html:textarea  styleId = "defaultTextAreaValue" name="TestParameterMstFB" property="defaultTextValue"
									tabindex="1" readonly="<%=this.readOnly %>"
									onkeypress="return CheckMaxLength(event,this,100,1)">
								
								</html:textarea>
							</div>
						</td>
					</tr>
					
					
					<!-- 
					
					For Editor
					 -->	
					  <tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
						<font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Editor &nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:checkbox name="TestParameterMstFB"
									property="showDefaultValue"
									onclick="ShowDefaultEditor(this)" value="0" style="width:160;" tabindex="1">
									</html:checkbox>
								
							</div>
						</td>
					</tr>
					
					<tr id ="subtextareaeditor1" style="display: none;">
						<td colspan = "2" width = "100%">
							<textarea class="ckeditor" name="editor1"></textarea>
						</td>
					</tr>
					
				</table>



				<!-- End of text Area Description -->


				<!-- COMBO Box description -->



				<table width="100%" id="combo1" style="display: none;">
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabelAlignment" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="labelAlignmentCombo" name="TestParameterMstFB" property="labelAlignment"
									style="width:160;" tabindex="1">
									<html:option value="0">Left</html:option>
									<html:option value="1">Center</html:option>
									<html:option value="2">Right</html:option>
								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="showParameterNameasLabel" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="ComboShowParameterNameasLabel" name="TestParameterMstFB"
									property="showParameterNameasLabel"
									onchange="Showcombovalue(this)" style="width:160;" tabindex="1">
									<html:option value="1">Yes</html:option>
									<html:option value="0">No</html:option>									
								</html:select>
							</div>
						</td>
					</tr>

					<tr id="subcombo1" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Bold" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="boldCombo" name="TestParameterMstFB" property="bold"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>

					<tr id="subcombo2" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Underline" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="underlineCombo" name="TestParameterMstFB" property="underline"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="ElementAlign" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="elementAlignCombo" name="TestParameterMstFB" property="elementAlign"
									style="width:160;" tabindex="1">
									<html:option value="0">Left</html:option>
									<html:option value="1">Center</html:option>
									<html:option value="2">Right</html:option>
								</html:select>
							</div>
						</td>
					</tr>





					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Event" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="eventCombo" name="TestParameterMstFB" property="event"
									style="width:160;" tabindex="1">
									<html:option value="0">Key Press</html:option>
									<html:option value="1">Click</html:option>
									<html:option value="2">Blur</html:option>
									<html:option value="3">onChange</html:option>
								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="EventFunction" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text styleId = "eventFunctionCombo" name="TestParameterMstFB" property="eventFunction"
									maxlength="50" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphabetsOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
				</table>



				<!-- End of COMBO Box Description -->



				<!-- Button Box description -->



				<table width="100%" id="button1" style="display: none;">
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabelAlignment" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="labelAlignmentButton" name="TestParameterMstFB" property="labelAlignment"
									style="width:160;" tabindex="1">
									<html:option value="0">Left</html:option>
									<html:option value="1">Center</html:option>
									<html:option value="2">Right</html:option>
								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="showParameterNameasLabel" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="ButtonShowParameterNameasLabel" name="TestParameterMstFB"
									property="showParameterNameasLabel"
									onchange="Showbuttonvalue(this)" style="width:160;"
									tabindex="1">
									<html:option value="1">Yes</html:option>
									<html:option value="0">No</html:option>
									

								</html:select>
							</div>
						</td>
					</tr>

					<tr id="subbutton1" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Bold" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="boldButton" name="TestParameterMstFB" property="bold"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>

					<tr id="subbutton2" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Underline" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="underlineButton" name="TestParameterMstFB" property="underline"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="ElementAlign" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="elementAlignButton" name="TestParameterMstFB" property="elementAlign"
									style="width:160;" tabindex="1">
									<html:option value="0">Left</html:option>
									<html:option value="1">Center</html:option>
									<html:option value="2">Right</html:option>
								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="CallingURL" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select name="TestParameterMstFB" property="callingURL"
									style="width:160;" tabindex="1">
									<html:option value="0">Add Site diagnosis</html:option>
									<html:option value="1">Add Test Result</html:option>
									<html:option value="2">AntiMicrobial Susceptibility Test</html:option>

								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Buttonname" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text name="TestParameterMstFB" property="buttonName"
									maxlength="50" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphabetsOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
				</table>
				<!-- End of BUTTON Box Description -->

				<!-- query Combo description -->



				<table width="100%" id="querycombo1" style="display: none;">
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabelAlignment" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="labelAlignmentQueryCombo" name="TestParameterMstFB" property="labelAlignment"
									style="width:160;" tabindex="1">
									<html:option value="0">Left</html:option>
									<html:option value="1">Center</html:option>
									<html:option value="2">Right</html:option>
								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="showParameterNameasLabel" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="QueryComboShowParameterNameasLabel" name="TestParameterMstFB"
									property="showParameterNameasLabel"
									onchange="Showquerycombovalue(this)" style="width:160;"
									tabindex="1">
									<html:option value="1">Yes</html:option>
									<html:option value="0">No</html:option>
									

								</html:select>
							</div>
						</td>
					</tr>

					<tr id="subquerycombo1" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Bold" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="boldQueryCombo" name="TestParameterMstFB" property="bold"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>

					<tr id="subquerycombo2" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Underline" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="underlineQueryCombo" name="TestParameterMstFB" property="underline"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="ElementAlign" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="elementAlignQueryCombo" name="TestParameterMstFB" property="elementAlign"
									style="width:160;" tabindex="1">
									<html:option value="0">Left</html:option>
									<html:option value="1">Center</html:option>
									<html:option value="2">Right</html:option>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Query" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:textarea name="TestParameterMstFB" property="query"
									tabindex="1" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphaNumericOnly(event,this)">
								</html:textarea>
							</div>
						</td>
					</tr>
				</table>



				<!-- End of query COMbo Box Description -->

	<!-- List Box Description -->
	
	<table width="100%" id="listBox1" style="display: none;">
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabelAlignment" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="labelAlignmentListBox" name="TestParameterMstFB" property="labelAlignment"
									style="width:160;" tabindex="1">
									<html:option value="0">Left</html:option>
									<html:option value="1">Center</html:option>
									<html:option value="2">Right</html:option>
								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="showParameterNameasLabel" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="ListBoxShowParameterNameasLabel" name="TestParameterMstFB"
									property="showParameterNameasLabel"
									onchange="Showlistvalue(this)" style="width:160;" tabindex="1">
									<html:option value="1">Yes</html:option>
									<html:option value="0">No</html:option>									
								</html:select>
							</div>
						</td>
					</tr>

					<tr id="sublist1" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Bold" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="boldListBox" name="TestParameterMstFB" property="bold"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>

					<tr id="sublist2" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Underline" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="underlineListBox" name="TestParameterMstFB" property="underline"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="ElementAlign" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="elementAlignListBox" name="TestParameterMstFB" property="elementAlign"
									style="width:160;" tabindex="1">
									<html:option value="0">Left</html:option>
									<html:option value="1">Center</html:option>
									<html:option value="2">Right</html:option>
								</html:select>
							</div>
						</td>
					</tr>





					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Event" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="eventListBox" name="TestParameterMstFB" property="event"
									style="width:160;" tabindex="1">
									<html:option value="0">Key Press</html:option>
									<html:option value="1">Click</html:option>
									<html:option value="2">Blur</html:option>
									<html:option value="3">onChange</html:option>
								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="EventFunction" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text styleId = "eventFunctionListBox" name="TestParameterMstFB" property="eventFunction"
									maxlength="50" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphabetsOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
				</table>
				
				
				<!-- End List Box Description -->
				
				
				

				<!-- AutoComplete description -->



				<table width="100%" id="Auto1" style="display: none;">
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabelAlignment" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="labelAlignmentAuto" name="TestParameterMstFB" property="labelAlignment"
									style="width:160;" tabindex="1">
									<html:option value="0">Left</html:option>
									<html:option value="1">Center</html:option>
									<html:option value="2">Right</html:option>
								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="showParameterNameasLabel" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="AutoShowParameterNameasLabel" name="TestParameterMstFB"
									property="showParameterNameasLabel"
									onchange="ShowAutovalue(this)" style="width:160;" tabindex="1">
									<html:option value="1">Yes</html:option>
									<html:option value="0">No</html:option>									
								</html:select>
							</div>
						</td>
					</tr>

					<tr id="subAuto1" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Bold" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="boldAuto" name="TestParameterMstFB" property="bold"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>

					<tr id="subAuto2" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Underline" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="underlineAuto" name="TestParameterMstFB" property="underline"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="ElementAlign" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="elementAlignAuto" name="TestParameterMstFB" property="elementAlign"
									style="width:160;" tabindex="1">
									<html:option value="0">Left</html:option>
									<html:option value="1">Center</html:option>
									<html:option value="2">Right</html:option>
								</html:select>
							</div>
						</td>
					</tr>





					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Event" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="eventAuto" name="TestParameterMstFB" property="event"
									style="width:160;" tabindex="1">
									<html:option value="0">Key Press</html:option>
									<html:option value="1">Click</html:option>
									<html:option value="2">Blur</html:option>
									<html:option value="3">onChange</html:option>
								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="EventFunction" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text styleId = "eventFunctionAuto" name="TestParameterMstFB" property="eventFunction"
									maxlength="50" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphabetsOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
				</table>


			<!-- Snomed CT Control -->
				<table width="100%" id="snomedct" style="display: none;">

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="showParameterNameasLabel" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="TextBoxShowParameterNameasLabel" name="TestParameterMstFB"
									property="showParameterNameasLabel" onchange="Showpara(this)"
									style="width:160;" tabindex="1">
								
									<html:option value="0">No</html:option>
										<html:option value="1">Yes</html:option>
									

								</html:select>
							</div>
						</td>
					</tr>



					<tr id="subtext3" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabelAlignment" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="labelAlignmentTextBox" name="TestParameterMstFB" property="labelAlignment"
									style="width:160;" tabindex="1">
									<html:option value="0">Left</html:option>
									<html:option value="1">Center</html:option>
									<html:option value="2">Right</html:option>
								</html:select>
							</div>
						</td>
					</tr>



					<tr id="subtext1" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Bold" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="boldTextBox" name="TestParameterMstFB" property="bold"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option  value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>

					<tr id="subtext2" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Underline" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="underlineTextBox" name="TestParameterMstFB" property="underline"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="ElementAlign" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="elementAlignTextBox" name="TestParameterMstFB" property="elementAlign"
									style="width:160;" tabindex="1">
									<html:option value="0">Left</html:option>
									<html:option value="1">Center</html:option>
									<html:option value="2">Right</html:option>
								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Size" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text name="TestParameterMstFB" property="size"
									maxlength="5" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateNumeric(event,this)" tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Maxlength" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text name="TestParameterMstFB" property="maxlength"
									maxlength="5" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateNumeric(event,this)" tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Event" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select  styleId="eventTextBox"  name="TestParameterMstFB" property="event"
									style="width:160;" tabindex="1">
									<html:option value="0">Key Press</html:option>
									<html:option value="1">Click</html:option>
									<html:option value="2">Blur</html:option>
									<html:option value="3">onChange</html:option>
								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="EventFunction" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text  styleId = "eventFunctionTextBox" name="TestParameterMstFB" property="eventFunction"
									maxlength="50" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphabetsOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
					<!--  Default Value -->
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Default &nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text styleId = "defaultTextBoxValue" name="TestParameterMstFB" property="defaultTextBoxValue"
									maxlength="100" size="30"							
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
					
					
					
					
				</table>
				<!-- End of Snomed CT Control description -->

				<!-- End of AutoComplete Description -->
	
	<%-- <table width="100%" id="listBox1" style="display: none;">

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="showParameterNameasLabel" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="ListBoxShowParameterNameasLabel" name="TestParameterMstFB"
									property="showParameterNameasLabel" onchange="Showlistvalue(this)"
									style="width:160;" tabindex="1">
									<html:option value="1">Yes</html:option>
									<html:option value="0">No</html:option>
									

								</html:select>
							</div>
						</td>
					</tr>



					<tr id="sublist3" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabelAlignment" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="labelAlignmentListBox" name="TestParameterMstFB" property="labelAlignment"
									style="width:160;" tabindex="1">
									<html:option value="0">Left</html:option>
									<html:option value="1">Center</html:option>
									<html:option value="2">Right</html:option>
								</html:select>
							</div>
						</td>
					</tr>



					<tr id="sublist1" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Bold" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="boldListBox" name="TestParameterMstFB" property="bold"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option  value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>

					<tr id="sublist2" style="display: none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Underline" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="underlineListBox" name="TestParameterMstFB" property="underline"
									style="width:160;" tabindex="1">
									<html:option value="0">No</html:option>
									<html:option value="1">Yes</html:option>

								</html:select>
							</div>
						</td>
					</tr>
	
<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Event" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								&nbsp;
								<html:select styleId="eventListBox" name="TestParameterMstFB" property="event"
									style="width:160;" tabindex="1">
									<html:option value="0">Key Press</html:option>
									<html:option value="1">Click</html:option>
									<html:option value="2">Blur</html:option>
									<html:option value="3">onChange</html:option>
								</html:select>
							</div>
						</td>
					</tr>

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="EventFunction" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text styleId = "eventFunctionListBox" name="TestParameterMstFB" property="eventFunction"
									maxlength="50" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphabetsOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
					
					<!-- For Default Value of Text Area -->
					
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Default &nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								
								<html:textarea  styleId = "defaultTextAreaValue" name="TestParameterMstFB" property="defaultTextValue"
									tabindex="1" readonly="<%=this.readOnly %>"
									onkeypress="return CheckMaxLength(event,this,100,1)">
								
								</html:textarea>
							</div>
						</td>
					</tr>
					</table> --%>
			</his:ContentTag>

			<his:ButtonToolBarTag>
				<span id="saveDiv"> <logic:notEqual name="TestParameterMstFB"
						property="hmode" value="MODIFY">
						<logic:notEqual name="TestParameterMstFB" property="hmode"
							value="VIEW">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-sv.png"/>'
								style="cursor: pointer"
								onkeypress="if(event.keyCode==13) finalSubmit('SAVE')"
								onclick="finalSubmit('SAVE')" tabindex="1">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-clr.png"/>'
								style="cursor: pointer" onclick="clearAddForm()"
								onkeypress="if(event.keyCode==13) clearAddForm()" tabindex="1">
						</logic:notEqual>
					</logic:notEqual> <logic:equal name="TestParameterMstFB" property="hmode"
						value="MODIFY">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-mo.png"/>'
							style="cursor: pointer"
							onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')"
							onclick="finalSubmit('MODIFYSAVE')" tabindex="1">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-clr.png"/>'
							style="cursor: pointer" onclick="clearForm()"
							onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
					</logic:equal> <img class="button"
					src='<his:path src="/hisglobal/images/viewTemplate.png" />'
					onclick="showMandatoryForTest('template')"> <!-- <img class="button" src='<his:path src="/hisglobal/images/btn-v.png"/>' style="cursor: pointer" onclick="clearAddForm()" onkeypress="if(event.keyCode==13) clearAddForm()" tabindex="1">-->
					<img class="button"
					src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
					style="cursor: pointer" onclick="submitForm('CANCEL')"
					onkeypress="if(event.keyCode==13) submitForm('CANCEL')"
					tabindex="1">
				</span>
			</his:ButtonToolBarTag>
			<his:status />
		</his:TransactionContainer>
	</html:form>

	<div id='template'
		style="display: none; border: 3px solid black; background-color: white; padding: 25px; font-size: 150%; text-align: center; display: none;">
		<logic:present name="RESULTENTRYVO">
			<table width="100%">
				<tr>
					<td class="HEADER"></td>
				</tr>
			</table>
			<templateTag:template name="RESULTENTRYVO" />
			<table width="100%">
				<tr>
					<td class="HEADER"></td>
				</tr>
			</table>
		</logic:present>
		<logic:notPresent name="RESULTENTRYVO">
			<table width="100%">
				<tr>
					<td class="HEADER"></td>
				</tr>
				<tr>
					<td width="100%"><div align="center">
							<font color="red"><b>Template To Display For</b> </font>
						</div></td>
				</tr>
				<tr>
					<td class="HEADER"></td>
				</tr>
			</table>
		</logic:notPresent>
		<table width="100%">
			<tr>
				<td width="100%"><div align="center">
						<img onClick="Popup.hide('template')"
							src="/HISInvestigationG5/hisglobal/images/ok.gif">
					</div></td>
			</tr>
		</table>
	</div>
</body>
</html>