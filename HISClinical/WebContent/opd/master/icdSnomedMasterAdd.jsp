
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="registration.RegistrationConfig"%>
<%@page import="java.util.List"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<link href="/HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet">
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">

<!-- <link rel="stylesheet" href="/HIS/hisglobal/css/jquery-ui.css"> -->
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolnewautocomplete.js"></script>
<!-- <script type="text/javascript" src="/HIS/hisglobal/snomedct/js/demo.js"></script> -->
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/utilityFunctions.js"></script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script type="text/javascript">

window.onload = function()
{
	
	//alert("Loading");
	var callbck_index =function(ret_OUT){setValue(ret_OUT);};

	var semantictag_IN;
	var acceptability_IN;
	var state_IN ;
	var returnlimit_IN;
	var retterm_value ={};
	
	selectSNOMEDCTauto('ACTIVE','DISORDER','SYNONYMS','10','null','1',callbck_index);
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	document.getElementsByName("mappedICDCode").value="";
	document.getElementsByName("mappedICDName").value="";
	document.getElementsByName("mappedSnomed").value="";
	document.getElementsByName("mappedSnomedName").value="";
	document.getElementsByName("selsnomedArray")[0].value="";
//snomd new

}

function setValue(selectedSNOMEDTerm)
{
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
	//var arr=selectedSNOMEDTerm.split(",");
	var str=selectedSNOMEDTerm.term;
	var str1=selectedSNOMEDTerm.conceptId;
	//alert(str); alert(str1);
	 
	document.getElementsByName("prefferedTerm")[0].value=str;
	document.getElementsByName("conceptId")[0].value=str1;
		}
	else
		{
		document.getElementsByName("prefferedTerm")[0].value="";
		document.getElementsByName("conceptId")[0].value="";
		}
}


function selectValue(value, callback) {
	
	var data = unescape(value);

	var term = data.split('###$$$');

	var returnterm_OUT = term[1] +","+ term[0].replace("'", "");

	if (typeof callback === "function")
		callback(returnterm_OUT);

	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	

}



//snomd new end


/* function snomedctIdDtl()
{
 var alertId= document.getElementsByName("patAlertId")[0].value;
 if(alertId == "-1")
 {
 	document.getElementById("somecetIddata").innerHTML =" ";
 }
 else
 { 
	 var id=alertId.split("#");
	 var somedId=id[1];
	  //alert(alertId);
	  //alert(somedId);
	 document.getElementsByName("snomedCtId")[0].value=somedId;
	 document.getElementById("somecetIddata").innerHTML =somedId;
}
 return true;

} */



function submitForm(mode)
{
	document.getElementsByName('hmode')[0].value=mode;
	//alert(document.getElementsByName('hmode')[0].value);
	if(document.getElementsByName('hmode')[0].value=="SAVE")
		{
			mapSelected();	
			if(validateData())
			{
				document.forms[0].submit();
			}
		}	
	else
			document.forms[0].submit();
}

function validateData()
{
	var count=0;
	//alert(document.getElementsByName("mappedICDCode").length);
	/* if(document.getElementsByName("mappedICDCode").length==0)
	{
					
				alert("Please Select ICD Code");
				document.getElementsByName("icdCode")[0].focus();
				return false;
	} */
		
		for(var i=0;i<document.getElementsByName("mappedSnomed").length;i++)	
		{
			if(document.getElementsByName("mappedSnomed")[i].value != "removed")
			{
				count++;
			}			
		}
		
		if(count<=0)
		{
			alert("Please Select Snomed Concept");
			document.getElementsByName("txt-snomed-ct-search_1")[0].focus();
			return false;
		}
		
		return true;
}

function mapSelected()
{
	var temp;
	
	
	if(document.getElementsByName("hmode")[0].value=="SAVE")
	{
		
		for(var i=0;i<document.getElementsByName("mappedSnomed").length;i++)	
		{
			//alert("mapped snomed value:"+document.getElementsByName("mappedSnomed")[i].value)
			if(document.getElementsByName("mappedSnomed")[i].value != "removed")
			{
				if(i==0)
					temp = document.getElementsByName("mappedSnomed")[i].value;
				else
				{
					if(temp != undefined)
						temp=temp+'#'+document.getElementsByName("mappedSnomed")[i].value;
					else
						temp=document.getElementsByName("mappedSnomed")[i].value;
				}
			}
			//alert("temp:"+temp);
		}
		document.getElementsByName("selsnomedArray")[0].value=temp;
		//alert("passing value:"+document.getElementsByName("selsnomedArray")[0].value);
	}
	
	//alert(document.getElementsByName("selsnomedArray")[0].value);	
	if(document.getElementsByName("hmode")[0].value=="SAVE")
	{
		temp="deleted";
		for(var i=0;i<document.getElementsByName("mappedSnomedName").length;i++)	
		{
			//alert(document.getElementsByName("mappedSnomedName")[i].value)
			if(document.getElementsByName("mappedSnomedName")[i].value != "removed")
			{				
				if(i==0)
					temp = document.getElementsByName("mappedSnomedName")[i].value;
				else
				{
					if(temp != "deleted")
						temp=temp+'#'+document.getElementsByName("mappedSnomedName")[i].value;
					else
						temp=document.getElementsByName("mappedSnomedName")[i].value;
			}				
		}
		//alert("temp:"+temp);
		document.getElementsByName("selSnomedNameArray")[0].value=temp;
		//alert(document.getElementsByName("selSnomedNameArray")[0].value);
	}
	}
	//alert(document.getElementsByName("selSnomedNameArray")[0].value);
		return true;
}
function validateAddICD()
{
	
	if(document.getElementsByName("icdCode")[0].value=="" || document.getElementsByName("icdCode")[0].value=="-1")
	{
		alert("Please Select The ICD Code");
		document.getElementsByName("icdCode")[0].focus();
		return false;		
	}	
	//document.getElementsByName("icdName")[0].value=document.getElementsByName("icdCode")[0].text;
	//alert(document.getElementsByName("icdCode")[0].text);
	return true;
}	
function mapICD()
{
	var elemDiv = document.getElementById("divMappedICD");
	var icdCode = document.getElementsByName("icdCode")[0].value;
	var icdName = document.getElementsByName("icdCode")[0].options[document.getElementsByName("icdCode")[0].selectedIndex].text;
	var res = icdName.split("(");
    var icd = res[1].split(")");
	var htmlCode = "<table id='tblmapICD#"+icdCode+"'cellpadding='0' cellspacing='1' align='left' width='100%'><tr><td width='100%'>"+icdName+"<input type='hidden' name='mappedICDCode' value='"+icdCode+"'/><input type='hidden' name='mappedICDName' value='"+icdName+"'/></td></tr></table>"
	elemDiv.innerHTML = htmlCode;
	//alert(icd[0]);
	document.getElementsByName("icdName")[0].value=icd[0];
}	

function validateAdd()
{
		
		if(document.getElementsByName("txt-snomed-ct-search_1")[0].value=="")
		{
			alert("Please Select The Snomed Concept");
			document.getElementsByName("txt-snomed-ct-search_1")[0].focus();
			return false;		
		}
		
		var elemMappedSnomeds = document.getElementsByName("mappedSnomed");
		for(var i=0; i<elemMappedSnomeds.length;i++)
		{
			if(elemMappedSnomeds[i].value==document.getElementsByName("conceptId")[0].value)
				{
				alert("Snomed Concept alreddy mapped..");
				document.getElementsByName("txt-snomed-ct-search_1")[0].focus();
				return false;
				}
		}
		
		if(document.getElementsByName("hmode")[0].value=="MODIFY")
		{
			var elemSelectedSnomeds = document.getElementsByName("mappedSnomedIDOLD");
			for(var i=0; i<elemSelectedSnomeds.length;i++)
			{
				if(elemSelectedSnomeds[i].value==document.getElementsByName("conceptId")[0].value)
					{
					alert("Snomed Concept alreddy mapped..");
					document.getElementsByName("txt-snomed-ct-search_1")[0].focus();
					return false;
					}
			}
		}
	return true;
}

function mapSnomed()
{
	var elemDiv = document.getElementById("divMappedSnomed");
	var preTerm = document.getElementsByName("prefferedTerm")[0].value;
	var conceptID = document.getElementsByName("conceptId")[0].value;
	//alert("preTerm "+preTerm+"& Concept ID "+conceptID);
	var htmlCode = "<table id='tblmapSnomed#"+conceptID+"' cellpadding='0' cellspacing='1' align='left'><tr><td>"+preTerm+"<input type='hidden' name='mappedSnomed' value='"+conceptID+"'/><input type='hidden' name='mappedSnomedName' value='"+preTerm+"'/></td><td><img src='/HIS/hisglobal/images/minus.gif' style='cursor:pointer;vertical-align: middle;' onclick =\"removeSnomed('"+conceptID+"');\" onkeypress=\"if(event.keyCode==13) removeSnomed('"+conceptID+"');\"/></td></tr></table>"
	elemDiv.innerHTML += htmlCode;
	snomedtoICDMapping(conceptID);
	
}	

function removeSnomed(tid)
{
	//alert(tid);
	for(var i=0;i<document.getElementsByName("mappedSnomed").length;i++)	
	{
		var term=document.getElementsByName("mappedSnomed")[i].value;
		if(tid == term)
			{
				document.getElementsByName("mappedSnomed")[i].value="removed";
				document.getElementsByName("mappedSnomedName")[i].value="removed";
				document.getElementById("tblmapSnomed#"+term+"").style.display="none";
			}
			
	
	}
	
}


function snomedtoICDMapping(conceptID)
{
	
	 $.ajax({
		 
			url : createFHashAjaxQuery("/AHIMSG5/snomedct/csnoserv/api/map/icdmap?id="+conceptID ) ,
			type : 'GET' ,
			
			datatype : "json" ,
			 async : false ,
			  success : function(data) {
				var obj =  jQuery.parseJSON(data); 
				
		     	  if(obj.mapStatus == 'MAP_FOUND' || obj.mapStatus== 'INDETERMINATE_MAP')
		     	  { 
		     	   for(var i=0;i<obj.mapGroup.length;i++)
					{
					  var icdCode=obj.mapGroup[i].mappedICDCode;
				      searchICD(icdCode);
				      
				    }
		     	  }
			   },
			   
		error : function(data)
		{
    	alert('request failed :');
		}  });
} 


function searchICD(icdCode)
{
	var elemDiv = document.getElementById("divSuggestedIcd");
	var opts =  document.getElementsByName("icdCode")[0];
	var icdName="";
	var comboval="";
	//alert(opts.length);
	//alert(icdCode);
	//var icdarr=icdCode.split('.');
	//var icd=icdarr[0];
	//alert(trimData(icdCode));
	
	for(var i = 1 ; i < opts.length; i++)
	{
		 comboval =opts.options[i].value;
 		 //alert(opts.options[i].value);
		if(trimData(icdCode)==trimData(comboval))
		{
	//	document.getElementsByName("icdCode")[0].selectedIndex=i;
		// alert("matched"+i);
		
		// icdName =  opts.options[document.getElementsByName("icdCode")[0].selectedIndex].text;
	   	 icdName =  opts.options[i].text; 
	   	// alert(icdName)
	   	 icdCode = icdName.split("(");
	   	 var name = icdCode[1].split(")");
	 //   alert(name[0])
	   	 var htmlCode = "<table id='tblmapICD#"+icdCode[0]+"' align='left' style='margin-top: 2px;'><tr><td>"+icdCode[0]+"</td><td></td></tr></table>"
		 
	   	// alert(document.getElementsByName("icdCode").value)
	   	 elemDiv.innerHTML = htmlCode;
	   	document.getElementsByName("icdCode")[0].value=icdCode[0];
	   	document.getElementsByName("icdName")[0].value=name[0];
		   //alert(icdName);
 	     break;
		} 
	}
	  	
}




function deleteRow(obj1,obj2)
{
	document.getElementsByName("hiddenAlertName")[0].value=obj1;
	document.getElementsByName("hiddenPatAlertId")[0].value=obj2;
	submitForm('DELETEROW');
}




</script>
<style>
body{
margin:0%;
}
</style>
<html:form action="/master/icdSnomedMappingMaster">
 


<his:ContentTag>
	<logic:equal name="icdSnomedMappingMasterFB" property="hmode" value="ADD">
	<his:TitleTag name="ICD Snomed Mapping Master>>ADD">
</his:TitleTag>
		<table width="100%" cellpadding="0" cellspacing="1" align="center">
			<tr>
				<td width="25%"  class="tdfonthead" >
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="icdCode"/>
							</b>	
						</font>
					</div>
				</td>
				<td width="50%" class="tdfont">
					<div id="divSuggestedIcd">
					</div>
				</td>
				<%-- <html:hidden name="icdSnomedMappingMasterFB" property="icdCode"></html:hidden> --%>
				<td >
				 
					<div align="left"  style="display:none;">
						<html:select name="icdSnomedMappingMasterFB" property="icdCode" style="width:50%;" onchange="mapICD()" >
						<html:option value="-1">Select Value</html:option>
					 	<html:options collection="<%=OpdConfig.EssentialBO_LIST_ICD_DISEASE%>"
							property="value" labelProperty="label" />
					 	
						</html:select>
						<html:hidden name="icdSnomedMappingMasterFB" property="icdName"></html:hidden>				
					</div>
				</td>			
				<!-- <td width="50%" class="tdfont" style="vertical-align: middle;">
					<div align="left" id="divMappedICD" width="100%">
					</div>
				</td> -->
				<html:hidden name="icdSnomedMappingMasterFB" property="icdCode"></html:hidden>
			</tr>
				
			
			
			<tr>
				<td width="25%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="snomedCtId"/>
							</b>	
						</font>
					</div>				
				</td>
				<td width="50%"  class="tdfont">
						
					<div id="divSnomed" style="display:block">	
					<html:hidden 	name="icdSnomedMappingMasterFB" property="prefferedTerm" ></html:hidden>
					<html:hidden name="icdSnomedMappingMasterFB" property="conceptId" ></html:hidden>
					<div align="left" >
				    <div id="dialog-form_1" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_1" class="searchText ui-autocomplete-input" name="txt-snomed-ct-search_1"  style="width:97%;color:#000000;" type="text">
					<img class="button" src='<his:path src="/hisglobal/images/plus.gif"/>' style="cursor:pointer;vertical-align: middle;" onclick ="if(validateAdd()) mapSnomed()" onkeypress="if(event.keyCode==13) if(validateAdd()) mapSnomed();">
					</div>
					 <div id="norecorddiv_1">
					 <label style="display: inline;" id="reccnt">No. of records : </label>
					<span style="display: inline;" id="reccount" ></span>
					<label style="display: none;" id="nosuggestion">No suggestions found</label>
					<label style="display: none;" id="norec">No results found</label>
                     <label style="display: none;" id="msg3chars">Please enter atleast 3 characters</label>
                 
					 </div>         
                     <div class="concept" id="conceptdiv_1">                 
                    </div>
                         </div>
					
					</div>
					
						
					</div>
				</td>			
				<!-- <td width="50%" class="tdfont">
					<div id="divSuggestedIcd">
					</div>
				</td> -->
			</tr>
			<tr>
				<td colspan="3">
					<div align="left" id="divMappedSnomed">
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="remark"/></b>	
						</font>
					</div>
				</td>
				<td width="50%" class="tdfont" style="vertical-align: middle;">	
					<div align="left">
						<html:textarea name="icdSnomedMappingMasterFB" property="remarks" tabindex="1" rows="1" cols="150" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumOnly(this,event))" style="vertical-align: middle;"></html:textarea>
					</div>
				</td>	
				<!-- <td>
				</td> -->				
			</tr>	
			<tr>
			</tr>
		</table>
		</logic:equal>			
		
		
<logic:equal name="icdSnomedMappingMasterFB" property="hmode" value="VIEW">
	<his:TitleTag name="ICD Snomed Mapping Master>>VIEW">
</his:TitleTag>
		<table width="100%" cellpadding="0" cellspacing="1" align="center">
			
			
			<tr>
				<td width="50%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="icdCode"/>
							</b>	
						</font>
					</div>
				</td>
				<td width="50%" class="tdfont" style="vertical-align: middle;" colspan="2">	
						<html:text name="icdSnomedMappingMasterFB" property="icdName" style="width:100%;" readonly="true"  >
						</html:text>
				</td>	
			</tr>			
			<tr>
				<td width="50%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="snomedCtConceptId"/>
							</b>	
						</font>
					</div>				
				</td>
				<td width="50%"  class="tdfont" colspan="2">
					<html:text name="icdSnomedMappingMasterFB" property="conceptName" style="width:100%;" readonly="true"  >
						</html:text>				
				</td>			
				
			</tr>
		</table>
		</logic:equal>	 		
		

<logic:equal name="icdSnomedMappingMasterFB" property="hmode" value="MODIFY">
	<his:TitleTag name="ICD Snomed Mapping Master>>MODIFY">
</his:TitleTag>
		<table width="100%" cellpadding="0" cellspacing="1" align="center">
			
					
			<tr>
				<td width="50%"  class="tdfonthead">
					<div align="center">
						<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							Modification is Not Allowed
							</b>	
						</font>
					</div>				
				</td>		
				
			</tr>
		</table>
		</logic:equal>	

		 
	</his:ContentTag>	
	
	                                                                   
	<his:ButtonToolBarTag>
		
		 
		  <logic:equal name="icdSnomedMappingMasterFB" property="hmode" value="ADD">
			      <img class='button' id="butSave" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer onclick ="submitForm('SAVE');" onkeypress="if(event.keyCode==13) submitForm('SAVE');" tabindex="1">
				</logic:equal>
				
				
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')" tabindex="1">
          <logic:equal name="icdSnomedMappingMasterFB" property="hmode" value="ADD">
          	<logic:equal name="icdSnomedMappingMasterFB" property="hmode" value="VIEW">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer onclick ="submitForm('ADD')" onkeypress="if(event.keyCode==13) submitForm('ADD');" tabindex="1">
		</logic:equal>
		</logic:equal>
		<his:statusUnsuccessfull>
	  		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL');" tabindex="1">
		</his:statusUnsuccessfull>
		
	</his:ButtonToolBarTag>
<his:status></his:status>
<%-- <html:hidden name="icdSnomedMappingMasterFB" property="icdCode"></html:hidden> --%>
<html:hidden name="icdSnomedMappingMasterFB" property="hmode" />
<html:hidden name="icdSnomedMappingMasterFB" property="index"/>
<html:hidden name="icdSnomedMappingMasterFB" property="snomedCtId"/>
<html:hidden name="icdSnomedMappingMasterFB" property="icdName" ></html:hidden>
<html:hidden name="icdSnomedMappingMasterFB" property="conceptName" ></html:hidden>
<html:hidden name="icdSnomedMappingMasterFB" property="selsnomedArray" ></html:hidden>
<html:hidden name="icdSnomedMappingMasterFB" property="selsnomed" ></html:hidden>
<html:hidden name="icdSnomedMappingMasterFB" property="selSnomedNameArray" ></html:hidden>
 <input type="hidden" name="count" />
 

</html:form>


