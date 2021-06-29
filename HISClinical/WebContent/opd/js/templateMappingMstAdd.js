window.onload=function (){

	if(!document.forms[0].deptRadio[1].checked){
		document.getElementById("unitDiv").style.display="none";
	}	
	else{
		document.getElementById("unitDiv").style.display="block";
	}
	if(!document.forms[0].deptRadio[2].checked){
		document.getElementById("wardDiv").style.display="none";
		
	}
	else{	
		document.getElementById("unitDiv").style.display="block";
		document.getElementById("wardDiv").style.display="block";
	}
}

function showDepartment(){
	document.getElementById("departmentDiv").style.display="block";
	document.getElementById("unitDiv").style.display="none";
	document.getElementById("wardDiv").style.display="none";
	document.getElementsByName("deptCode")[0].value="-1";
	submitPage("ADD");
}

function showUnit(){
	
	document.getElementById("unitDiv").style.display="block";
	document.getElementById("wardDiv").style.display="none";
	document.getElementsByName("deptCode")[0].value="-1";
	document.getElementsByName("deptUnitCode")[0].value="-1";
	//document.getElementById("wardCode").value="";
	submitPage("GETALLDEPT");
}

function showWard(){
	
	document.getElementById("unitDiv").style.display="block";
	document.getElementsByName("deptCode")[0].value="-1";
	document.getElementsByName("deptUnitCode")[0].value="-1";
	document.getElementById("wardDiv").style.display="block";
	submitPage("GETALLDEPT");
}

function getDeptUnit(){

	if(document.forms[0].deptRadio[1].checked){
	   submitPage('GETUNITNOTADDED');
	}	
	
	if(document.forms[0].deptRadio[2].checked){
		document.getElementsByName("wardCode")[0].value="-1";
		submitPage('GETALLUNIT');
	}	
}
function getWard(){
	if(document.forms[0].deptRadio[2].checked){
	document.getElementsByName("wardCode")[0].value="-1";
		submitPage('GETWARD');
	}
}

function submitPage(mode){
	
	/* var source=document.forms[0].selectedTemplateId
	 for(var i=0;i<source.length;i++){
	 	alert(source.options[i].text)
	 }*/
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
	
}

function validateFinalSubmit(mode){
	
	if(document.forms[0].hmode.value!="MODIFY"){
		if(document.forms[0].deptCode.value=="-1"){
			alert("Select Department ...");
			document.getElementsByName("deptCode")[0].focus();
			return false;
		}
		if(document.forms[0].deptRadio[1].checked || document.forms[0].deptRadio[2].checked){
			if(document.forms[0].deptUnitCode.value=="-1"){
				alert("Select Unit ...");
				document.getElementsByName("deptUnitCode")[0].focus();
				return false;
			}
		}
		if(document.forms[0].deptRadio[2].checked){
			if(document.forms[0].wardCode.value=="-1"){
				alert("Select Ward ...");
				document.getElementsByName("wardCode")[0].focus();
				return false;
			}
		}
	}
	if(mode!="GO"){
		if(document.forms[0].selectedTemplateId.length==0){
			alert("Select at least one Template Name...");
			document.getElementsByName("templateId")[0].focus();
			return false;
		}
	}	
	return true;
}
function finalSubmit(mode){
	MoveToSelected();
	if(!validateFinalSubmit(mode)) return;
	else{
		submitPage(mode);
	}	
}

function clearForm(){
	moveLeftAll(1);
	document.forms[0].selectedTemplateId.length=0;
}

function moveRightSingle(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		source  = document.forms[0].templateId;
		target = document.forms[0].selectedTemplateId;	
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
		source  = document.forms[0].templateId;
		target = document.forms[0].selectedTemplateId;	
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
		target  = document.forms[0].templateId;
		source = document.forms[0].selectedTemplateId;	
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
		target  = document.forms[0].templateId;
		source = document.forms[0].selectedTemplateId;	
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
	// Select All in Selected
	if(document.forms[0].selectedTemplateId)
		for(var i=0;i<document.forms[0].selectedTemplateId.length;i++)
			document.forms[0].selectedTemplateId.options[i].selected=true;
	
	// Unselect Remaining 
	if(document.forms[0].templateId)
	{	
		for(var i=0;i<document.forms[0].templateId.options.length;i++)
			document.forms[0].templateId.options[i].selected=false;
	}
}

function cancel(){
	
	submitPage('ADD');
}