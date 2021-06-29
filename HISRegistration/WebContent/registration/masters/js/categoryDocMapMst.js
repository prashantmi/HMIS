function moveRightSingle(listNo)
{
	var source="";
	var target="";

	if(listNo=="1"){
		source = document.getElementsByName("patCatDocMapModel.strUnMappedDocCodes")[0];
		target = document.getElementsByName("patCatDocMapModel.strMappedDocCodes")[0];
	}

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++){
		if(source.options[i].selected){
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

function deleteThis(source,target)
{	
	var tarlen = target.length;
	var srclen = source.length;

	var a1 = new Array(tarlen);
	var a2 = new Array(tarlen);
	var a3 = new Array(srclen);

	for(var i=0;i<tarlen;i++){		
		a1[i]= target.options[i].value;
		a2[i]= target.options[i].text;	
	}
	for( i=0;i<srclen;i++)
		a3[i]= source.options[i].value;

	target.length = 0;
	var counter = 0;
	var k = 0;
	var flag = 0;

	for(i=0;i<tarlen;i++){		
		flag = 0;
		for(k=0;k<srclen;k++)
			if(a1[i]==a3[k])
				flag = 1;					
		if(flag==0){
			target.length = (counter+1);
			target.options[counter].value = a1[i];
			target.options[counter].text  = a2[i]; 
			counter++;			
		}		
	}
}

function moveRightAll(listNo)
{
	var source="";
	var target="";
	
	if(listNo=="1"){
		source = document.getElementsByName("patCatDocMapModel.strUnMappedDocCodes")[0];
		target = document.getElementsByName("patCatDocMapModel.strMappedDocCodes")[0];
	}	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;
	
	for(var i=0;i<totalElement;i++){
		val = source.options[i].value;
		txt = source.options[i].text;			
		
		targetlen = target.length;							
		target.length = (targetlen+1);				
			
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;			
		target.options[targetlen].selected="true";

	}
	deleteThis(target,source);
}

function moveLeftSingle(listNo)
{
	var source="";
	var target="";

	if(listNo=="1"){
		source = document.getElementsByName("patCatDocMapModel.strMappedDocCodes")[0];	
		target = document.getElementsByName("patCatDocMapModel.strUnMappedDocCodes")[0];
	}
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++){
		if(source.options[i].selected){
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
	var source="";
	var target="";

	if(listNo=="1"){
		source = document.getElementsByName("patCatDocMapModel.strMappedDocCodes")[0];
		target = document.getElementsByName("patCatDocMapModel.strUnMappedDocCodes")[0];	
	}
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;
	
	for(var i=0;i<totalElement;i++){
		val = source.options[i].value;
		txt = source.options[i].text;			
	
		targetlen = target.length;							
		target.length = (targetlen+1);				
		
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;		
		target.options[targetlen].selected="true";

	}

	deleteThis(target,source);
}

function clearPatCatDocMapMstAction(){	
	document.getElementsByName("patCatDocMapModel.strPatCategoryCode")[0].value="-1";
	document.getElementsByName("patCatDocMapModel.strMappedDocCodes")[0].length=0;
	document.getElementsByName("patCatDocMapModel.strUnMappedDocCodes")[0].length=0;
	$("#isMappedSelected").hide();
	document.forms[0].action="viewDataCatDocMapMst.action";
	document.forms[0].submit();
}

function savePatCatDocMapMstAction()
{
	if(validateIt()){
	document.forms[0].action="saveCatDocMapMst.action";
	document.forms[0].submit();
	}
}

function validateIt()
{
	var isValid=true;
	isValid=selectAll();
	return isValid;
}

function selectAll()
{
	var isValid=true;
	var mappedDocs = document.getElementsByName("patCatDocMapModel.strMappedDocCodes")[0];
	var totalMappedElement = mappedDocs.length;
	if(document.getElementsByName("patCatDocMapModel.strPatCategoryCode")[0].value=="-1"){
		//alert("Please Select Patient Primary Category..!");
		document.getElementsByName("patCatDocMapModel.strPatCategoryCode")[0].focus();
		isValid=false;		
	}
	else if(totalMappedElement<=0){
		$("#isMappedSelected").show();
		//alert("Please Select Some Verification Document..!");
		document.getElementsByName("patCatDocMapModel.strMappedDocCodes")[0].focus();
		document.getElementsByName("patCatDocMapModel.strUnMappedDocCodes")[0].focus();
		isValid=false;
		
	}
	else
	{
		for(var i=0;i<totalMappedElement;i++)
		{	
			mappedDocs.options[i].selected="true";
		}		
	}
	return isValid;
	
}

function cancelPatCatDocMapMstAction()
{
	//document.forms[0].action="cancelCatDocMapMst.action";
	document.forms[0].action="addCatDocMapMst.action";
	document.forms[0].submit();
}

function getData()
{
	//if(document.getElementsByName("patCatDocMapModel.strPatCategoryCode")[0].value!="-1")
	//{
	var unmappedDocs = document.getElementsByName("patCatDocMapModel.strUnMappedDocCodes")[0];
	var totalNonMappedElement = unmappedDocs.length;

	for(var i=0;i<totalNonMappedElement;i++)
	{	
		//alert("hi");
		unmappedDocs.options[i].selected="false";
		//alert("hello");
	}	
		document.forms[0].action="viewDataCatDocMapMst.action";
		document.forms[0].submit();
	//}
	//else
	//	clearPatCatDocMapMstAction();
}