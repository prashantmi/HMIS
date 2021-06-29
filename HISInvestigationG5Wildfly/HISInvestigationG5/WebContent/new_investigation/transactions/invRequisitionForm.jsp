<!-- 
 
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: CHANDAN GUPTA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : Requisition Form PROCESS
 ## Purpose						        : 
 ## Date of Creation					: 19/08/2016
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 

  -->
  <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <%@page import="new_investigation.vo.Inv_RequisitionRaisingPatientVO"%>
 <%@page import="new_investigation.vo.Inv_SampleCollectionVO"%>
 <%@page import="new_investigation.vo.OnlinePatientAcceptanceVO"%>
 
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page import="new_investigation.vo.InvValueAuditVO"%>
<%@page
	import="new_investigation.transactions.controller.fb.invRequisitionFormFB"%>
<%@page	import="new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB"%>
 <%@page import="new_investigation.vo.InvValueAuditVO"%>
	
<%@page import="new_investigation.vo.LabTestVO"%>
<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
<%@page import="java.awt.BorderLayout"%>
<%@page import="java.awt.TextArea"%>
<%@page import="java.awt.Frame"%>
<%@page import="java.awt.Color"%>
<%@page import="javax.swing.JTextArea"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page import="new_investigation.vo.RequisitionListVO"%>
<%@page import="new_investigation.vo.invAntibioticProcessVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page import="new_investigation.transactions.controller.fb.InvValueAuditFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
 
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
							
<his:css src="/hisglobal/css/jquery/jquery.ui.menu.css" />
<his:css src="/hisglobal/css/jquery-ui.css" />
<his:css src="/hisglobal/css/style.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.autocomplete.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
 
<his:css src="/hisglobal/css/icon.css" />
<his:css src="/hisglobal/css/email.css" />
<his:css src="/hisglobal/css/textboxcss.css" />
<his:css src="/hisglobal/css/drop.css" />
 <his:css src="/hisglobal/css/Cannedstyle.css" />
 <link rel="stylesheet" href="/new_investigation/css/Date/site-demos.css"> 
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/commonUtility.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/cannedMacroValidation.js" />
<his:javascript src="/hisglobal/js/cannedMacroAutocomplete.js" />
<his:javascript src="/bloodbank/js/bloodRequisition.js" />
<his:javascript src="/new_investigation/js/reportsValidation.js" />
<his:javascript src="/new_investigation/js/onlinePatientAcceptance.js" />
<his:javascript src="/new_investigation/js/jquery-1.11.1.min.js" />
<his:javascript src="/new_investigation/js/jquery.validate.email.js" />
<his:javascript src="/new_investigation/js/additional-methods.min.js" />
<his:javascript src="/new_investigation/js/ckeditor/ckeditor.js"/>
<his:javascript src="/new_investigation/js/wysiwyg.js"/>
<his:javascript src="/new_investigation/js/wysiwyg-settings.js" />


<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
	 
	 
<his:javascript src="/hisglobal/js/jquery-1.7.2.js" />

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="new_investigation.vo.template.ResultEntryVO"%>
<%@page import="new_investigation.vo.template.ResultEntryVOGroupByValidatedBy"%>



<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="new_investigation.transactions.controller.fb.InvValueAuditFB"%>

 
 
<his:javascript src="/hisglobal/js/jquery-ui.js" />
<his:javascript src="/hisglobal/js/sweet-alert.min.js" />
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

<%@page import="new_investigation.InvestigationConfig"%>

<html>



<script type="text/javascript">

function callPrint()
{
	
window.print();


}

function cancelFun()
{
	window.parent.closeTab();
}

function closeadvice()
{
	window.close();
}

function validateSave()
{
    var testCode=document.getElementsByName('testCode')[0].value;
	var labCode=document.getElementsByName('labCode')[0].value;

    var reqformtestnames=document.getElementsByName('reqformtestnames')[0].value;
	var reqformtestcodes=document.getElementsByName('reqformtestcodes')[0].value;

	
	onSave(testCode,labCode,reqformtestcodes,reqformtestnames);
	//alert("check");
	//var valuee=testCode+"#"+labCode+"#"+additionalcomment+"#";
//	alert(valuee);
	//opener.document.getElementsByName('advice')[0].value+=valuee+"@";
	//window.close(); 
}




function onSave(testCode,labCode,reqformtestcodes,reqformtestnames)
{
	var codee=testCode;
	//alert(reqformtestcodes);
	//alert(reqformtestnames);
     var reqtests="";
      var len=reqformtestcodes.split("!");
      var lendata="";
    for(var a=0;a<len.length;a++)
    {

    	//alert("reqformtestcodes.length"+len.length);
       //  alert("reqformtestcodes[a]"+reqformtestcodes.split("!")[a]);
        if(reqformtestcodes.split("!")[a].indexOf(testCode)==0)
    	{
        	var testt=reqformtestcodes.split("!")[a];
        	//alert("match test"+testt);
        	//var ret = testt.replace(testCode,'');
        	//ret=ret+";";
        	testt=testt.replace(/;/g,"#");
        //	alert("match testt"+ret);
        	
        	reqtests+=testt+"#";

        }
    }
	       //alert("final match test"+reqtests);
	//alert("onsave");
	var count=0;
	var concatenateChkBoxVal="";
	
	 var name;
	  var splitTemplateValue;
	  var reqNO=[];
	  var parameterValue=[];
	  var parameterCode=[];
	  var parantParameter=[];
	  var resultEntryTemplateValue=[];
	//alert(cbs.length);
	
		var values;
		//alert("The value is"+values);
		count++;	
	        var k=0;
           
          for(k;k<document.getElementsByTagName("input").length;k++)
        	    {
               //     alert("input");
             	//alert("len:"+document.getElementsByTagName("input").length);
                 
              //   alert(values);
                 get_tags=document.getElementsByTagName("input");
                   name=get_tags[k].name;
                   //alert(testCode+labCode);
                   if(name.indexOf(testCode+labCode)!=-1)
                {
                //  alert("success");
                   id=get_tags[k].id;
                  // alert(id);
                   //alert("id for "+k+"    "+id);
                   hiddenid="hiddenid"+id;
                   defaultid="default"+hiddenid;
              	  typ=get_tags[k].type;
              	//  alert(typ);
              	  hidddentext="hidden";
              	  checkboxcheck="checkbox";
              
                // alert("type is " + typ);
                   if(typ!=hidddentext)
                   {
                  	 
                 // 	 alert("not hidden");
                   splitTemplateValue=name.split("#");
                   
                   reqNO.push(splitTemplateValue[0]);
                    parameterValue=splitTemplateValue[3];
                  //  alert("parameter value    "+parameterValue);
                // alert("para"+parameterValue);
                    parameterCode.push(parameterValue.substring(0, 5));
                  // alert( parameterCode.push(parameterValue.substring(0, 5)));
                    parantParameter.push(parameterValue.substring(9,18));
                  
                      
                //  alert("parameterValue"+parameterValue+"reqno"+reqNO+"parameter"+parantParameter);
                 
                      if(document.getElementsByTagName("input")[k].value=="")
                             {
                                    alert("Enter the field Focussed");
                                    document.getElementsByTagName("input")[k].focus();
                                 return false;
                              }
                 
                         // document.getElementsByName('resultEntryTemplateValue').value.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);       
                    //  resultEntryTemplateValue.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);
                    var resultValidationTemplateValue="";

                    //chandan comment auto    
                /*     if(id.contains('auto'))    
                  	  {
                
                  	 if( document.getElementById(hiddenid).value!=null && document.getElementById(hiddenid).value!="" )
                  	  resultValidationTemplateValue=document.getElementById(hiddenid).value;
                  	 else
                  		 resultValidationTemplateValue=document.getElementById(defaultid).value;
                  	  } 
                    else */
                   /*  if(typ!=checkboxcheck)
                        {
                       // alert("not checkbox");
                      resultValidationTemplateValue=document.getElementsByTagName("input")[k].value;
                 //     alert("name:"+name);
                         //alert("1 " +name);
                          name+="#"+resultValidationTemplateValue+"#"+"-";
                       
                       concatenateChkBoxVal +=name;
                     //  alert("before:"+concatenateChkBoxVal);
                       concatenateChkBoxVal+='@';
                     //  alert(concatenateChkBoxVal);
                        } */

                        if(id.indexOf('chkbox')== -1 )
                        {

                           if(typ=='checkbox')
                            {
                             //  alert("checkbox");
                                if(document.getElementById(id).checked==true)
                                    {
                                    //alert("check");
                                	resultValidationTemplateValue="1";}
                                if(document.getElementById(id).checked==false)
                                    {
                                	resultValidationTemplateValue="0";}
                            }
                           else
                       resultValidationTemplateValue=document.getElementsByTagName("input")[k].value;          
                     // resultValidationTemplateValue=document.getElementsByTagName("input")[k].value;
                    //  alert(resultValidationTemplateValue);
                       //  var orderingValue= document.getElementsByName(name+"#order")[0].value;
                          name+='#'+resultValidationTemplateValue+'#'+values+"#"+"-";
                    // alert(name);    
                       concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                       concatenateChkBoxVal+='@';
                    //   alert(concatenateChkBoxVal);
                        }

                    
                   }
                          
                          
                }
		          
	             }
        // alert(document.getElementById(i+'templateValue').getElementsByTagName("select").length);
       // alert("select value"+document.getElementById(i+'templateValue').getElementsByTagName("select")[0].value);
        var j=0;
         for(j;j<document.getElementsByTagName("select").length;j++)
    	       {
        	 //var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
     		//alert("The value is"+values);
        	  get_tags=document.getElementsByTagName("select");
       	   name=get_tags[j].name;
      //     alert("select:"+name);
       	   
       	 if(name.indexOf(testCode+labCode)!=-1)
         {
        	 //alert("inside select box");
        	//alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length);
            	 if(document.getElementsByTagName("select")[j].value=="-1" || document.getElementsByTagName("select")[j].value=="")
	         	   {
				      alert("Enter the field Focussed");
				      document.getElementsByTagName("select")[j].focus();
			          return false;
		           }
	          //   document.getElementsByName('resultEntryTemplateValue')[0].value=document.getElementById(i+'templateValue').getElementsByTagName("input")[j].value;	 
              var multiValue="";
              var resultEntryTemplateValue;
	        if(document.getElementsByTagName("select")[j].options[0].value!="-1")
              for (var kk=0;kk<document.getElementsByTagName("select")[j].length ;kk++)
	        	  {
	        	  //&& document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[0].value!="-1"
	        	 if(document.getElementsByTagName("select")[j].options[kk].selected==true)
	        	  {  //alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value)
	        	   	  multiValue+=document.getElementsByTagName("select")[j].options[kk].value;
	        	      multiValue+="$";   	  
	        	  
	        	  }
	        	 resultEntryTemplateValue=multiValue
	        	  }
	          
	        else
	       	resultEntryTemplateValue=document.getElementsByTagName("select")[j].value;
		   
	        
	    //    alert(resultEntryTemplateValue);
	    
	        name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-";
	          
     		  	concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
    			concatenateChkBoxVal+='@';

    	       
    	       } 
         
         
    	       }
         
    
        // alert("text Area value"+document.getElementById(i+'templateValue').getElementsByTagName("textarea")[0].value);
         var n=0;
          for(n;n<document.getElementsByTagName("textarea").length;n++)
     	       {
 	             
        	//  var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
        	  
      	//	alert("The value is"+values);
        	  //alert("inside here");
 	             get_tags=document.getElementsByTagName("textarea");
 	        	   name=get_tags[n].name;
 	         	// alert("name1"+name);
 	              if(name.indexOf(testCode+labCode)!=-1)
                {
 	                  
 	              if(document.getElementsByTagName("textarea")[n].value=="")
 	         	   {
 				      alert("Enter the field Focussed");
 				      document.getElementsByTagName("textarea")[n].focus();
 			          return false;
 		           }
 	           //  document.getElementsByName('resultEntryTemplateValue')[0].value=document.getElementById(i+'templateValue').getElementsByTagName("input")[j].value;	 
                
 	         //  alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].id);
 	         var id1 = document.getElementsByTagName("textarea")[n].id;
 	           var editor = CKEDITOR.instances[id1];
 	       
 	         
 	           if(editor!=null){
 	        	 
 	        	  
            //  alert( editor.getData() );
              
              var resultEntryTemplateValue=editor.getData();
              //alert(resultEntryTemplateValue);
              if(resultEntryTemplateValue.length>12000)
            	  {
            	  alert("Editor Data limit is 12000, including spaces. Please don't exceed the limit");
            	  return false;
            	  
            	  
            	  }
	          name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-";
          
 	          }

 	          else
 	        	  {
     	         var resultEntryTemplateValue=document.getElementsByTagName("textarea")[n].value;
		          name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-";
	          
 	        	  
 	        	  }
     		  	concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
    			concatenateChkBoxVal+='@';
            //  alert(concatenateChkBoxVal);
     	       
     	       } 

     	       }
	       /* added by chandan */
	       k = 0;
	       for(k;k<document.getElementsByTagName("a").length;k++)
   	    {

        	//alert("hyperlink");
        	
           // var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
            //alert(values);
            get_tags=document.getElementsByTagName("a");
              name=get_tags[k].name;
              id=get_tags[k].id;
              if(id.indexOf(testCode+labCode)!=-1)
              {
          //  alert(id);
             if(id.indexOf("template")!=-1)
                 {
            //     alert("insie");
              splitTemplateValue=id.split("#");
              reqNO.push(splitTemplateValue[0])
              parameterValue=splitTemplateValue[3];
          /*   //alert(name);
              id=get_tags[k].id;
              alert(id);
              //alert("id for "+k+"    "+id);
              hiddenid="hiddenid"+id;
              defaultid="default"+hiddenid;
         	  typ=get_tags[k].type;
         	  alert(typ);
         	  hidddentext="hidden";
         	  checkboxcheck="checkbox";
         
           // alert("type is " + typ);
              if(typ!=hidddentext)
              {
             	 
            // 	 alert("not hidden");
              splitTemplateValue=id.split("#");
              reqNO.push(splitTemplateValue[0]);
               parameterValue=splitTemplateValue[3];
             //  alert("parameter value    "+parameterValue);
               parameterCode.push(parameterValue.substring(0, 5));
               parantParameter.push(parameterValue.substring(9,18));
             
                 
           //  alert("parameterValue"+parameterValue+"reqno"+reqNO+"parameter"+parantParameter);
            
                 if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value=="")
                        {
                               alert("Enter the field Focussed");
                               document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].focus();
                            return false;
                         }
            
                    // document.getElementsByName('resultEntryTemplateValue').value.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);       
               //  resultEntryTemplateValue.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);
               var resultValidationTemplateValue="";

               //chandan comment auto    
           /*     if(id.contains('auto'))    
             	  {
           
             	 if( document.getElementById(hiddenid).value!=null && document.getElementById(hiddenid).value!="" )
             	  resultValidationTemplateValue=document.getElementById(hiddenid).value;
             	 else
             		 resultValidationTemplateValue=document.getElementById(defaultid).value;
             	  } 
               else */
             /*   if(typ!=checkboxcheck)
                   {
                 resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value;
               */    //alert("1 " +name);
               var resultEntryTemplateValue=document.getElementsByName('hyperLinkTableSession')[0].value;
               name+="#"+resultValidationTemplateValue+"#"+"-";
                   /*   name+=id+"#"+resultEntryTemplateValue+"#"+values+"#hyperlink"; */
               // alert(name);    
                  concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                  concatenateChkBoxVal+='@';

                
                /* return false; */
               /*     }
              }
                */      
                 }
	          
              }
            }
        
       
         
        
   
             //   alert("reqtests"+reqtests);
            //    alert(reqtests.split("#")[0]);

                if(reqtests!='')
            {

      //      alert("sss");

            for(var a=0;a<reqtests.split("#").length;a++)
            {
            	 if(reqtests.split("#")[a]!='')
                 {
                        var testtt=reqtests.split("#")[a].split("^")[0];
                        var labcode=reqtests.split("#")[a].split("^")[1];  
                       // alert("matched testt1"+testtt+labcode);
                       var allvalues=concatenateChkBoxVal.replace(new RegExp(codee+labCode, "g"), testtt+labcode);
                         allvalues=allvalues.replace(new RegExp(codee, "g"), testtt);
                         
                          
                        concatenateChkBoxVal+=allvalues;
                      // alert("allvalues"+allvalues);
                       
                 }
            }
            }
	

	//alert("Finally concatenateChkBoxValues For Save "+concatenateChkBoxVal);
	
	//alert("concatenateChkBoxVal"+concatenateChkBoxVal);
	document.getElementsByName('resultEntryTemplateValueWithHash')[0].value=concatenateChkBoxVal;
	var valuee=document.getElementsByName('resultEntryTemplateValueWithHash')[0].value;
	/* alert("final:"+valuee);
	alert("opener value:"+opener.document.getElementsByName('requisitionFormData')[0].value);
	 */
	 //alert(valuee+"$");
	opener.document.getElementsByName('requisitionFormData')[0].value+=valuee+"$";
	

	
	//alert(document.getElementsByName('resultEntryTemplateValueWithHash')[0].value);
	
	/* for(j;j<reqNO.length;j++)
		{ 
		
	
	document.getElementsByName('resultEntryTemplateValue').value=resultEntryTemplateValue[j];
    alert(document.getElementsByName('resultEntryTemplateValue').value);
	document.getElementsByName('parameterCode').value=parameterCode[j];
    document.getElementsByName('parantParameterCode').value=parantParameter[j];
    document.getElementsByName('requisitionDNo').value=reqNO[j];
		} */
		
		if(document.getElementsByName('resultEntryTemplateValueWithHash')[0].value=="")
			{
			alert("Invalid form. Modify form from Test Parameter Master.");
			return false;
			
			}
	if(confirm("Do you want to print the requisition form"))
		{
		document.getElementById("btn").style.display="none";
		window.print();
		window.close();
		}
	else
		{
		 window.close();
		}
		 return true;
	 
  }


function cancelFunc()
{
	
window.close();
}


</script>



<body onload="">
<html:form action="/requisitionformprocess">


 <html:hidden name="invRequisitionFormFB" property="hmode" />
  <html:hidden name="invRequisitionFormFB" property="testCode" />
   <html:hidden name="invRequisitionFormFB" property="labCode" />
    <html:hidden name="invRequisitionFormFB" property="testName" />
    <html:hidden name="invRequisitionFormFB" property="labName" />
     <html:hidden name="invRequisitionFormFB" property="status" />
      <html:hidden name="invRequisitionFormFB" property="advice" />
	    <html:hidden name="invRequisitionFormFB" property="resultEntryTemplateValueWithHash" />
	  
	  <html:hidden name="invRequisitionFormFB" property="reqformtestnames" />
	    <html:hidden name="invRequisitionFormFB" property="reqformtestcodes" />
	  
	  
	    <his:TitleTag name="Requisition Form Process">
			<%-- <his:insertDateTag/> --%>
		</his:TitleTag>
	
	<logic:notEqual  name="invRequisitionFormFB" property="status" value="1">
	
	<%
              Inv_RequisitionRaisingPatientVO  lstPatVO=(Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
                        String	xslPrintRequisitionForm=(String)session.getAttribute("xslPrintRequisitionForm");																	  	 
								//System.out.println("requisitionformTemplate:"+xslPrintRequisitionForm);														 	 
																						 		 
																						 	 
						%>
	<his:SubTitleTag name=" Patient Details"> </his:SubTitleTag>
	<table width="100%">
	
			<tr>
		 			<td class="tdfont" width="25%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<bean:message key="Name"/>
		 				</font>
		 				</div>
		 				
		 			</td>
		 			<td class="tdfonthead" width="25%"> 
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatFirstName() %>
		 				
		 					</font>
		 				</div>
		 			</td>
		 			 <td class="tdfont" width="25%">
		 				<div align="right">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="CRNo."/> 
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatCRNo() %>
		 				</font>
		 				</div>
		 			</td> 
		 			
		 			 
			</tr>
		<tr>
		<td class="tdfont" width="25%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="Age/Gender"/> 
		 				</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%"> 
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatAge()+"/"+lstPatVO.getPatGenderCode()%>
		 					</font>
		 				</div>
		 			</td>
		 			 <td class="tdfont" width="25%">
		 				<div align="right">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				 	<bean:message key="PrimaryCategory"/> 
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatCategory() %>
		 				</font>
		 				</div>
		 			</td> 
		 			 
		</tr>
		<tr>
		<td class="tdfont" width="25%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 						<bean:message key="FatherName"/>  
		 				</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%"> 
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatGuardianName() %>
		 					</font>
		 				</div>
		 			</td>
		 			 <td class="tdfont" width="25%">
		 				<div align="right">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="MobileNo."/>  
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatMobileNo() %>
		 									
		 				</font>
		 				</div>
		 			</td> 
		 			  
		</tr>
		</table>
	<his:SubTitleTag name="selected Test Details"> </his:SubTitleTag>
	
	<% 
	
	              String testcode=request.getParameter("testCode");
	 String testName=request.getParameter("testName");
	 String labCode=request.getParameter("labCode");
	 String labName=request.getParameter("labName");
	
	 String tessttname=request.getParameter("reqformtestnames");
	 String testtcode=request.getParameter("reqformtestcodes");
	 
	
	%>			
                                 	<table width="100%" cellspacing="1" cellpadding="0">
												
												<tr>
												<td class="tdfont" width="25%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 						Test Name  
		 				</font>
		 				</div>
		 			</td>
													<td width="25%" class="tdfonthead">
														<div align="left" id="checkOnSave">
															<%=testName%>
															
														</div>
													</td>
													<%-- <td class="tdfont" width="25%"> //labname comment
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 						Lab Name  
		 				</font>
		 				</div>
		 			</td>
													<td width="25%" class="tdfonthead">
														<div align="left">
															<%=labName%>
															
														</div>
													</td> --%>
											</tr>
											</table>
					<his:TitleTag name="Comments">	</his:TitleTag>					
					 <table width="100%" cellspacing="1" cellpadding="0">
												
					<tr>
					<!-- <td class="tdfont" width="50%">
		 				<div align="center">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					Additional	Comments
		 				</font>
		 				</div>
		 			</td> -->
		 			
		 			<%  if(xslPrintRequisitionForm==null){  %>
		 			<td width="50%" class="tdfonthead">
		 			<div align="center">
		 			<%="No Template Found" %>
		 			</div>
		 			</td>
		 			<% }else{%>
		 			<td width="50%" class="tdfonthead"><font color="red" size="3">
		 			<%
		 			String errmsg="No Template Found";
		 			%>
		 			</font>
														<div align="center">
														<html:textarea name="invRequisitionFormFB" property="remarks" tabindex="1" >
									
								</html:textarea>
												 <% if(xslPrintRequisitionForm!=null || !xslPrintRequisitionForm.equals("") || !xslPrintRequisitionForm.equals("null")){%>	
										<%= xslPrintRequisitionForm%><%}else{ %><%= errmsg  %><%} %>				
														</div>
													</td>
												<%} %>
					</tr>
		 			</table>	 					
							<his:ButtonToolBarTag>
							<div id="btn">
     			<img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) validateSave()" onclick="validateSave()" tabindex="1">
			    <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelFunc() " onclick="cancelFunc()" tabindex="1">
			   </div>
	             </his:ButtonToolBarTag>				
													
			<%%>
			
			</logic:notEqual>
			
			<logic:equal  name="invRequisitionFormFB" property="status" value="1">
			<%
			Inv_SampleCollectionVO  lstPatVO=(Inv_SampleCollectionVO)session.getAttribute(InvestigationConfig.SELECTED_PAT_DETAILS);
			OnlinePatientAcceptanceVO onlinePatientvo= (OnlinePatientAcceptanceVO)session.getAttribute(InvestigationConfig.LIST_PATIENT_VO);
			boolean fl=false;       
			 ResultEntryVO invresultentryvo=new ResultEntryVO();
			 invresultentryvo=new ResultEntryVO();
			 
			if(lstPatVO==null && onlinePatientvo==null)
			         {
			        	
			        	 Map<String,List<ResultEntryVO>> _mpp=(Map<String,List<ResultEntryVO>>)session.getAttribute(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO);
			        	 Iterator itrr=_mpp.keySet().iterator();
			        	 int size=0;
			        	 while(itrr.hasNext())
			        	 						{
			        	 						String sampleNo=(String)itrr.next();
			        	 						List<ResultEntryVO> lstResultEntryVO=_mpp.get(sampleNo);
			        	 						
			        	 						if(lstResultEntryVO!=null && lstResultEntryVO.size()>0 )
			        	 					 			size=lstResultEntryVO.size();
			        	 			
			        	                         for(int k=0;k<size;k++)
			        	 					 		{
			        	                        	 
			        	                        	 String dno=request.getParameter("requisitionDNo");
			        	                                  
			        	 					 			 invresultentryvo=lstResultEntryVO.get(k);
			        	 					 			 if(invresultentryvo.getRequisitionDNo().equals(dno))
			        	 					 			 
			        	 					 				 {
			        	 					 				     fl=true;
			        	 					 				 System.out.println("reqnoo"+invresultentryvo.getPatName()+" sa "+invresultentryvo.getRequisitionDNo());
			        	 					 				invresultentryvo=lstResultEntryVO.get(k);
			        	 					 				 }
			        	 					 		}
			        	 							
			        	 						}
			        	 
			        	 
			        	 
			         }
                        String	xslPrintRequisitionForm=(String)session.getAttribute("xslPrintRequisitionForm");																	  	 
								System.out.println("requisitionformTemplate:"+xslPrintRequisitionForm);														 	 
								System.out.println("lstPatVO:"+lstPatVO);	
								if(lstPatVO!=null)
								{
																						 	 
						%>
	<his:SubTitleTag name=" Patient Details"> </his:SubTitleTag>
	<table width="100%">
	
			<tr>
		 			<td class="tdfont" width="25%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<bean:message key="Name"/>
		 				</font>
		 				</div>
		 				
		 			</td>
		 			<td class="tdfonthead" width="25%"> 
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getpatName() %>
		 				
		 					</font>
		 				</div>
		 			</td>
		 			 <td class="tdfont" width="25%">
		 				<div align="right">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="CRNo."/> 
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatCRNo() %>
		 				</font>
		 				</div>
		 			</td> 
		 			
		 			 
			</tr>
		<tr>
		<td class="tdfont" width="25%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="Age/Gender"/> 
		 				</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%"> 
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatAge()+"/"+lstPatVO.getPatGenderCode()%>
		 					</font>
		 				</div>
		 			</td>
		 			 <td class="tdfont" width="25%">
		 				<div align="right">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				 	<bean:message key="PrimaryCategory"/> 
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatCategory() %>
		 				</font>
		 				</div>
		 			</td> 
		 			 
		</tr>
		</table>
	<his:SubTitleTag name="selected Test Details"> </his:SubTitleTag>
	
	<% 
	
	              String testcode=request.getParameter("testCode");
	 String testName=request.getParameter("testName");
	 //String testName=request.getParameter("reqformtestnames");
	 String labCode=request.getParameter("labCode");
	 String labName=request.getParameter("labName");
	
	
	 String tessttname=request.getParameter("reqformtestnames");
	 String testtcode=request.getParameter("reqformtestcodes");
	 
	 
	%>			
                                 	<table width="100%" cellspacing="1" cellpadding="0">
												
												<tr>
												<td class="tdfont" width="25%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 						Test Name  
		 				</font>
		 				</div>
		 			</td>
													<td width="25%" class="tdfonthead">
														<div align="left" id="checkOnSave">
															<%=testName%>
															
														</div>
													</td>
													<%-- <td class="tdfont" width="25%"> //labname comment
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 						Lab Name  
		 				</font>
		 				</div>
		 			</td>
													<td width="25%" class="tdfonthead">
														<div align="left">
															<%=labName%>
															
														</div>
													</td> --%>
											</tr>
											</table>
											
											<%}
								else if(onlinePatientvo!=null)
								{
																						 	 
						%>
						
						
						
						<his:SubTitleTag name=" Patient Details"> </his:SubTitleTag>
	<table width="100%">
	
			<tr>
		 			<td class="tdfont" width="25%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<bean:message key="Name"/>
		 				</font>
		 				</div>
		 				
		 			</td>
		 			<td class="tdfonthead" width="25%"> 
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=onlinePatientvo.getPatName() %>
		 				
		 					</font>
		 				</div>
		 			</td>
		 			 <td class="tdfont" width="25%">
		 				<div align="right">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="CRNo."/> 
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=onlinePatientvo.getPatPuk() %>
		 				</font>
		 				</div>
		 			</td> 
		 			
		 			 
			</tr>
		<tr>
		<td class="tdfont" width="25%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="Age/Gender"/> 
		 				</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%"> 
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=onlinePatientvo.getPatAge()+"/"+onlinePatientvo.getPatGender()%>
		 					</font>
		 				</div>
		 			</td>
		 			 <td class="tdfont" width="25%">
		 				<div align="right">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				 	<bean:message key="PrimaryCategory"/> 
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=onlinePatientvo.getPatCategory() %>
		 				</font>
		 				</div>
		 			</td> 
		 			 
		</tr>
		</table>
	<his:SubTitleTag name="selected Test Details"> </his:SubTitleTag>
	
	<% 
	
	              String testcode1=request.getParameter("testCode");
	 String testName1=request.getParameter("testName");
	 //String testName=request.getParameter("reqformtestnames");
	 String labCode1=request.getParameter("labCode");
	 String labName1=request.getParameter("labName");
	
	
	 String tessttname1=request.getParameter("reqformtestnames");
	 String testtcode1=request.getParameter("reqformtestcodes");
	
	%>			
                                 	<table width="100%" cellspacing="1" cellpadding="0">
												
												<tr>
												<td class="tdfont" width="25%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 						Test Name  
		 				</font>
		 				</div>
		 			</td>
													<td width="25%" class="tdfonthead">
														<div align="left" id="checkOnSave">
															<%=testName1%>
															
														</div>
													</td>
													<%-- <td class="tdfont" width="25%"> //labname comment
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 						Lab Name  
		 				</font>
		 				</div>
		 			</td>
													<td width="25%" class="tdfonthead">
														<div align="left">
															<%=labName1%>
															
														</div>
													</td> --%>
											</tr>
											</table>
											
											
						
						<%}	else if(fl==true)
								{%>
									<!-- else -->
								
								<his:SubTitleTag name=" Patient Details"> </his:SubTitleTag>
	<table width="100%">
	
			<tr>
		 			<td class="tdfont" width="25%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<bean:message key="Name"/>
		 				</font>
		 				</div>
		 				
		 			</td>
		 			<td class="tdfonthead" width="25%"> 
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=invresultentryvo.getPatName() %>
		 				
		 					</font>
		 				</div>
		 			</td>
		 			 <td class="tdfont" width="25%">
		 				<div align="right">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="CRNo."/> 
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=invresultentryvo.getPatCRNo() %>
		 				</font>
		 				</div>
		 			</td> 
		 			
		 			 
			</tr>
		<tr>
		<td class="tdfont" width="25%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="Age/Gender"/> 
		 				</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%"> 
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=invresultentryvo.getPatAge()+"/"+invresultentryvo.getPatGender()%>
		 					</font>
		 				</div>
		 			</td>
		 			<%--  <td class="tdfont" width="25%">
		 				<div align="right">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				 	Sample Number 
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<%=invresultentryvo.getTempSampleNo()==null?"NA":invresultentryvo.getTempSampleNo() %>	
		 				</font>
		 				</div>
		 			</td>  --%>
		 			 
		</tr>
		</table>
	<his:SubTitleTag name="selected Test Details"> </his:SubTitleTag>
	
	<% 
	
	              String testcode=request.getParameter("testCode");
	 String testName=request.getParameter("testName");
	 //String testName=request.getParameter("reqformtestnames");
	 String labCode=request.getParameter("labCode");
	 String labName=request.getParameter("labName");
	
	
	 //String tessttname=request.getParameter("reqformtestnames");
	 //String testtcode=request.getParameter("reqformtestcodes");
	 
	/*  for(int i=0;i<testtcode.split("!").length-1;i++)
	 {
		 String test=testtcode.split("!")[i];
		 if(test.contains(testcode))
		 {
			 testName=tessttname.split("!")[0];
		 }
	 } */
	 
	 
	%>			
                                 	<table width="100%" cellspacing="1" cellpadding="0">
												
												<tr>
												<td class="tdfont" width="25%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 						Test Name  
		 				</font>
		 				</div>
		 			</td>
													<td width="25%" class="tdfonthead">
														<div align="left" id="checkOnSave">
															<%=testName%>
															
														</div>
													</td>
													<%-- <td class="tdfont" width="25%"> //labname comment
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 						Lab Name  
		 				</font>
		 				</div>
		 			</td>
													<td width="25%" class="tdfonthead">
														<div align="left">
															<%=labName%>
															
														</div>
													</td> --%>
											</tr>
											</table>
											
								
									
									
								<%
								
								}
								%>
			<his:SubTitleTag name=" Additional Advice"> </his:SubTitleTag>
	        
	        <table width="100%">
	
			<tr>
		 			<!-- <td class="tdfonthead" width="50%">
		 				<div align="center">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					Additional Comments
		 				</font>
		 				</div>
		 			</td> -->
		 			
		 			<%    String advice=request.getParameter("advice");
		 			
		 			     /* if(advice.equals("-") || advice.equals(""))
		 			     {
		 			    	 
		 			    	 advice="NA";
		 			     } */
		 			    
		 			    String msg="No Template Found";
		 			%>
		 			<td class="tdfonthead" width="50%">
		 				<div align="left">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%-- <%=advice %> --%>
		 					 <% if(xslPrintRequisitionForm!=null || !xslPrintRequisitionForm.equals("")){%>	
										<%= xslPrintRequisitionForm%><%}else{ %><%= msg  %><%} %>	
		 				</font>
		 				</div>
		 			</td>
	       </tr>
	       			<his:ButtonToolBarTag>
	       			<div id="btn1">
     			 
			<img class="button" src='<his:path src="/hisglobal/images/close_tab.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelFun() " onclick="cancelFun()" tabindex="1">
			<%   Inv_SampleCollectionVO  lstPatVOO=(Inv_SampleCollectionVO)session.getAttribute(InvestigationConfig.SELECTED_PAT_DETAILS);
                        													 	 
								if(true)
								{ %>
			    <img class="button" src='<his:path src="/hisglobal/images/print_tab.gif"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) callPrint() " onclick="callPrint()" tabindex="1">
	            <%} %>
	            </div>
	             </his:ButtonToolBarTag>	
	             
	        </table>
			</logic:equal>
			
			  
</html:form>
</body>
</html>  