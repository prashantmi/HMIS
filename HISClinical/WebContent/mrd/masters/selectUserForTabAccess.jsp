<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="mrd.MrdConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<style type="text/css">
@import url(../css/calendar-blue2.css);
</style>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
function moveRightSingle(listNo)
{
	var source;
	var target;

	// 1 -> Billing category
	
	
	if(listNo=="1")
	{
		source  = document.forms[0].userId;
		target = document.forms[0].selectedUserId;	
	}

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}

function moveRightAll(listNo)
{
	var source;
	var target;
	
	if(listNo=="1")
	{
		source  = document.forms[0].userId;
		target = document.forms[0].selectedUserId;	
	}
	
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;
	
	for(var i=0;i<totalElement;i++)
	{
		val = source.options[i].value;
		txt = source.options[i].text;			
		
		targetlen = target.length;							
		target.length = (targetlen+1);				
			
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;													
	}
	deleteThis(target,source);
}

function deleteThis(source,target)
{	
	var tarlen = target.length;
	var srclen = source.length;

	var a1 = new Array(tarlen);
	var a2 = new Array(tarlen);
	var a3 = new Array(srclen);

	for(var i=0;i<tarlen;i++)
	{		
		a1[i]= target.options[i].value;
		a2[i]= target.options[i].text;	
	}
	for( i=0;i<srclen;i++)		
		a3[i]= source.options[i].value;

	target.length = 0;
	var counter = 0;
	var k = 0;
	var flag = 0;

	for(i=0;i<tarlen;i++)
	{		
		flag = 0;
		for(k=0;k<srclen;k++)
			if(a1[i]==a3[k])
				flag = 1;					
		if(flag==0)
		{
			target.length = (counter+1);
			target.options[counter].value = a1[i];
			target.options[counter].text  = a2[i]; 
			counter++;			
		}		
	}
}

function moveLeftSingle(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		target  = document.forms[0].userId;
		source = document.forms[0].selectedUserId;	
	}
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}

function moveLeftAll(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		target  = document.forms[0].userId;
		source = document.forms[0].selectedUserId;	
	}
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;
	
	for(var i=0;i<totalElement;i++)
	{
		val = source.options[i].value;
		txt = source.options[i].text;			
	
		targetlen = target.length;							
		target.length = (targetlen+1);				
		
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;													
	}

	deleteThis(target,source);
}

function MoveToSelected()
{
	// Select All Shift in Selected
	if(document.forms[0].selectedUserId)
		for(var i=0;i<document.forms[0].selectedUserId.length;i++)
			document.forms[0].selectedUserId.options[i].selected=true;
	
	// Unselect Remaining 
	if(document.forms[0].userId)
	{	
		for(var i=0;i<document.forms[0].userId.options.length;i++)
			document.forms[0].userId.options[i].selected=false;
	}
}


function submitPage(mode)
{
	MoveToSelected();
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(mode)
{
	if(comboValidation(document.getElementsByName('processType')[0],"Process Type"))
	{
		return true;
	}
	else{
		return false;
	}
}

function finalSubmit(mode)
{
	if (!validateFinalSubmit(mode)) 
	return;
	submitPage(mode);
	
}
function clearForm(){

	document.getElementsByName('selectedUserId')[0].length=0;
	document.getElementsByName('userId')[0].length=0;
}
 
function getFetchedList(){

	var fetchedList=document.forms[0].selectedUserId;
	var selectedData=document.forms[0].fetchedList.value;
	for(var i=0;i<fetchedList.length;i++){
		selectedData=selectedData + fetchedList.options[i].value +"%";
	}
	document.forms[0].fetchedList.value=selectedData;
}

function saveUser(){

	var selectedUserId=document.forms[0].selectedUserId
	var selectedData="";
	for(var i=0;i<selectedUserId.length;i++){
		if(i==0){
			selectedData=selectedData + selectedUserId.options[i].value;
		}
		else{
			selectedData=selectedData +"#"+ selectedUserId.options[i].value;
		}	
	}
	document.getElementsByName("selectedUsers")[0].value=selectedData
	var index=document.getElementsByName("selectedIndex")[0].value
	opener.document.getElementsByName("isEmpSelected")[index].value="1"
	document.getElementsByName("hmode")[0].value="SAVEUSERS"
	document.forms[0].submit()
	self.close();
}

</script>
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:css src="/css/calendar-blue2.css" />

 <body>
<his:TransactionContainer>
  <html:form action="/master/eprTabAccessMater" > 
    <html:hidden name="eprTabAccessMasterFB" property="hmode"/>
<%-- 	<html:hidden name="eprTabAccessMasterFB" property="serialNo"/> --%>
	<html:hidden name="eprTabAccessMasterFB" property="fetchedList"/>
	<html:hidden name="eprTabAccessMasterFB" property="selectedtabId"/>
	<html:hidden name="eprTabAccessMasterFB" property="selectedUsers"/>
	<html:hidden name="eprTabAccessMasterFB" property="selectedIndex"/>
	
	<his:TitleTag name="Select User">	</his:TitleTag>
  	   
	 <his:ContentTag>
		 <table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
						<td width="40%"  class="tdfonthead">
						</td>
						<td width="20%"  class="tdfonthead">
							<div align="center"><b>Select User </b></div>
						</td>
						<td width="40%"  class="tdfonthead">
						</td>
					</tr>
					<tr>
						<td width="40%"  class="tdfont">
							<div align="right">
								<html:select name="eprTabAccessMasterFB" property="userId" tabindex="1" multiple="true" size="6">
									<logic:notEqual name="eprTabAccessMasterFB" property="hmode"  value="NEW">
										<logic:present name="<%=MrdConfig.EPR_ACCESS_NOT_ADDED_USERID_LIST%>">
										<html:options  collection="<%=MrdConfig.EPR_ACCESS_NOT_ADDED_USERID_LIST%>" property="value" labelProperty="label" />
										</logic:present>
									</logic:notEqual>
								</html:select>		
							</div>
						</td>
						<td width="20%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("1");' /> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
							</div>
						</td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<html:select name="eprTabAccessMasterFB" property="selectedUserId" tabindex="1" multiple="true" size="6">
									<logic:notEqual name="eprTabAccessMasterFB" property="hmode"  value="NEW">
										<logic:present name="<%=MrdConfig.EPR_ACCESS_ADDED_USERID_LIST%>">
										<html:options  collection="<%=MrdConfig.EPR_ACCESS_ADDED_USERID_LIST%>" property="value" labelProperty="label" />
										</logic:present>
									</logic:notEqual>
								</html:select>
								
							</div>
						</td>
					</tr>
			</table>
				
      </his:ContentTag>
      
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			   
			     <img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) saveUser()" onclick="saveUser()">
			     <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="self.close()" onkeypress="if(event.keyCode==13) self.close()">
<!--				 <img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>' tabindex="1" style="cursor: pointer" onclick="finalSubmit('VIEW')" onkeypress="if(event.keyCode==13) finalSubmit('VIEW')">-->
				 
			</span>
		</his:ButtonToolBarTag>
		<his:status/>
       </html:form>
       </his:TransactionContainer>
  </body>
</html>
		     
		   
		  