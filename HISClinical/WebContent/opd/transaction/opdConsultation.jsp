<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:javascript src="/opd/opdJs/opd.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
function validateIt()
{// confirm("Do You Want To Close The Episode ?");
	
	var flag=false;
	
	var subject=document.getElementsByName("subject")[0].value
	var i=0;
	//alert("subject.length  "+subject.length);
	var subLen=subject.length;
	while(i<subLen){
	subject=subject.replace(" ","")
	i=i+1;
	}
	//alert("subject.length  "+subject.length);
	var contentText=document.getElementsByName("content")[0].value;
	var textLength=contentText.length;
	if((document.getElementsByName("toDoctorSeatId")[0].value=="-1")&&(document.getElementsByName("toDeptId")[0].value=="-1"))
		
	{
		alert("Please Select Doctor Name OR  Department Unit ");
	}
	else
	{
		if(textLength>1000){
	alert("Only 1000 Character can be send and your data is of "+contentText.length+" Character");
	}
	else
	{
		if(subject=="" && contentText.length==0){
			 flag=confirm("Do You Want To Send Mail Without Subject And Message ?");
		}
		else{
			if(subject==""){
				flag=confirm("Do You Want To Send Mail Without Subject ?");
			}
			else{
			if(contentText.length==0){
				flag=confirm("Do You Want To Send Mail Without Message ?");
			}
			else{
				flag =true;
			}
			}
		}
		// flag=true;
	}
		
}
	

	return flag;
}

function getPatientProfile(e){
	
	var path='/HISClinical/opd/opdCosultation.cnt?hmode=GETPROFILE';
	openPopup(createFHashAjaxQuery(path),e,600,700);
}
window.onload = function()
{
	if(callThisOnload)
		callThisOnload();
}

function toggleSelect(id)
{	//alert("hi");
    if (id == 'in')
    {		        
          document.getElementsByName("toDepartment")[0].checked=false;
    	  document.getElementById('toDoctor').style['display'] = 'block';
          document.getElementById('Doctor').style['display'] = 'block';
          document.getElementById('toDept').style['display'] = 'none';
          
    }
    if (id == 'off')
    {
        //alert("off");
         document.getElementsByName("toDoc")[0].checked=false;
         document.getElementById('Doctor').style['display'] = 'block';
         document.getElementById('toDoctor').style['display'] = 'none';
         document.getElementById('toDept').style['display'] = 'block';
    }
}
/*Added by Vasu on 29.Dec.17*/
function getDocIdNameValue(e)
{
   //var e = document.getElementsByName("toDoctorSeatId")[0];
   var eval = e.value;
   var strUser = e.options[e.selectedIndex].text;
   var docSeatId = eval+"#"+strUser;

   //alert(docSeatId);
   document.getElementsByName("toDocId")[0].value = docSeatId;
  // alert("e.value: "+document.getElementsByName("toDocId")[0].value);
}
</script>
<his:TitleTag>
		<his:name>
			<bean:message key="opdCosultation" />
		</his:name>
	</his:TitleTag>
	
<his:statusInProcess>


<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
	<his:SubTitleTag name="New Mail">
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td>
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<a style="cursor:pointer" onclick="getPatientProfile(event)" title="click to attach patient profile">	
								<bean:message key="attachProfile"/>
							</a>	
						</b>
					</font>
				</div>
			</td>
			
		</tr>
		</table>
		</his:SubTitleTag>
		

 <his:ContentTag>
 
 <table width="100%" cellspacing="1" cellpadding="0">
 <tr>
 					 <td width="10%"  class="tdfonthead">
					<div align="left">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><font color="#FF0000"></font>
					<bean:message key="userType"/></b>
					</font>
					</div>
					</td>  
					
				   <td  class="tdfonthead">
					<div align="left">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<input type='radio' name='toDoc' value='in' onclick="toggleSelect('in');" checked /><label for='toDoctor'>Doctor</label>
					<input type='radio' name='toDepartment' value='off' onclick="toggleSelect('off');"  /><label for='toDepartment'>Unit</label>
					
					</font>
					</div>
					</td> 
					</tr> 
					</table>
 <table width="100%" cellspacing="1" cellpadding="0">
 <tr>
 					<td width="10%"  class="tdfonthead">
					<div align="left" id="Doctor">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><font color="#FF0000"></font>
					<bean:message key="to"/></b>
					</font>
					</div>
					</td> 
    
	 				
 					<td width="90%"  class="tdfont">
					<div align="left" id="toDoctor"  >	           
					<html:select name="OpdConsultationFB" property="toDoctorSeatId" tabindex="1" onchange="getDocIdNameValue(this)">
					<html:option value="-1">Select Value</html:option>
					<logic:present name="<%=OpdConfig.OPD_ECONSULTANT_DETAIL_LIST%>" >
					<html:options collection ="<%=OpdConfig.OPD_ECONSULTANT_DETAIL_LIST%>" property = "value" labelProperty = "label"/>
					</logic:present>
					</html:select>
					</div>
 					<div align="left" id="toDept" style='display: none;'>	           
					<html:select name="OpdConsultationFB" property="toDeptId" tabindex="1" >
					<html:option value="-1">Select Value</html:option>
					<logic:present name="<%=OpdConfig.OPD_DEPT_LIST%>" >
					<html:options collection ="<%=OpdConfig.OPD_DEPT_LIST%>" property = "value" labelProperty = "label"/>
					</logic:present>
					</html:select>
					</div>
	  				</td>   
</tr>
</table>


<table width="100%" cellspacing="1" cellpadding="0">
<tr>
					      
	  				<td width="10%" class="tdfonthead">
					<div align="left">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<bean:message key="subject"/></b>
					</font>
					</div>
	  				</td>      
	  			<td   class="tdfont">
					<div >	      
					<html:text name="OpdConsultationFB" property="subject" tabindex="1"  size="50" 
					maxlength="50" onkeypress="if(event.keyCode!=13){return notSpecChar(this,event);}else{return false;}" ></html:text>     
					</div>
				</td>
</tr>
</table>
<table width="100%" cellspacing="1" cellpadding="0">
<tr>
				<td width="10%" class="tdfonthead">
				</td>
				<td width="90%"  class="tdfont">
					<div >	      
					<html:textarea name="OpdConsultationFB" property="content" tabindex="1" cols="120" rows="15" 
					onkeypress="return (validateTextArea(event,this,'1000') && notSpecChar(this,event))" ></html:textarea>  
					</div>
				</td>
</tr>
</table>
</his:ContentTag>
</his:statusInProcess>

<his:ButtonToolBarTag>
		  <img class='button' src='<his:path src="/hisglobal/images/GoNew.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "submitFormOnValidate(validateIt(),'SEND');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateIt(),'SEND');">
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
</his:ButtonToolBarTag>
<html:hidden name="OpdConsultationFB" property="hmode"/>
<html:hidden name="OpdConsultationFB" property="patCrNo"/>
<html:hidden name="OpdConsultationFB" property="selectedProfile"/>
<html:hidden name="OpdConsultationFB" property="toDeptId"/>		
<html:hidden name="OpdConsultationFB" property="toDoctorSeatId"/>
<html:hidden name = "OpdConsultationFB" property = "toDocId"/>