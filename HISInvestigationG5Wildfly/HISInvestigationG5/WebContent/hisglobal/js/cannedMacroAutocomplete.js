


function openAutocompletePopup(val)
 
{
	
	if (typeof document.getElementsByName("cannedLabCode")[0]!='undefined') {
	   
	
	   var cannedVal=document.getElementsByName("cannedLabCode")[0].value;
	   var cannedMacroCheck=val;
	
		 if(val=="CANNED")
		 {
			 document.getElementById('cannedField').style.display='block';
		 }
		  
		 if(val=="MACRO")
		 {
			 document.getElementById('macroField').style.display='block';
	     } 

}
else
	{	
	alert("No Template Details Found For "+document.getElementsByName("chkLabName")[0].value);
	}
}
	
	
	
function autocompleteBox_close(){
    document.getElementById('cannedField').style.display='none';
    
}

function autocompleteBox_close_macro(){
    document.getElementById('macroField').style.display='none';
    
}
 
function addCannedDetail(canMacVal)
{

	  
	if(canMacVal=="CANNED")
		{
	
	if((document.getElementsByName('currentElement')[0].value=='TEXTAREA'||document.getElementsByName('currentElement')[0].value=='textarea')) 
	{
	var cbs=document.getElementsByName("userCanned")[0].value.toUpperCase();
	
	

//alert(	document.getElementById(cbs).value);
		
		//alert(cbs[i].value);
		
		selectedCannedValue=document.getElementById(cbs).value;
		selectedCannedValue+='\n';
    	
	
    	
	
	//document.getElementsByName('currentElementName')[0].value=selectedCannedValue;
	//alert(document.getElementsByName('currentElementName')[0].value);
	document.getElementsByName(document.getElementsByName('currentElementName')[0].value)[0].value+=selectedCannedValue;
//alert(	document.getElementsByName(document.getElementsByName('currentElementName')[0].value)[0].value);

	document.getElementById('automplete-4').value="";
	document.getElementById('automplete-4').focus();
	
	}
	else if(document.getElementsByName('currentElement')[0].value=='ckeditor')
	{
		
		var cbs=document.getElementsByName("userCanned")[0].value.toUpperCase();
		selectedCannedValue=document.getElementById(cbs).value;
		selectedCannedValue+='\n';
		//alert("addCannedDetail");
		
		//CKEDITOR.instances[document.getElementsByName("editorName")[0].value].setData(CKEDITOR.instances[document.getElementsByName("editorName")[0].value].getData()+selectedCannedValue);
		CKEDITOR.instances[document.getElementsByName("editorName")[0].value].insertHtml(selectedCannedValue);
		
		document.getElementById('automplete-4').value="";
		document.getElementById('automplete-4').focus();
		
	}else if((document.getElementsByName('currentElement')[0].value=='TA'))
	{
		var cbs=document.getElementsByName("userCanned")[0].value.toUpperCase();
		selectedCannedValue=document.getElementById(cbs).value;
		selectedCannedValue+='\n';
		
		document.getElementsByName('finalRemarksValue')[0].value+=removeTags(selectedCannedValue);
		
		document.getElementById('automplete-4').value="";
		document.getElementById('automplete-4').focus();
	
	}else
		{
		
		alert("Selected Element is Not TextArea ");
		}
		}
	
	if(canMacVal=="MACRO")
	{
//alert(document.getElementsByName('currentElement')[0].value);
if((document.getElementsByName('currentElement')[0].value=='TEXTAREA'||document.getElementsByName('currentElement')[0].value=='textarea')||(document.getElementsByName('currentElement')[0].value=='INPUT'||document.getElementsByName('currentElement')[0].value=='input')) 
{
	
	//alert("macro check");
var cbs=document.getElementsByName("userMacroCode");


	
	//alert(cbs[0].value);
	
	selectedCannedValue=cbs[0].value;
	selectedCannedValue+=" ";
	

	

//document.getElementsByName('currentElementName')[0].value=selectedCannedValue;
//alert(document.getElementsByName('currentElementName')[0].value);
document.getElementsByName(document.getElementsByName('currentElementName')[0].value)[0].value+=selectedCannedValue;
//alert(	document.getElementsByName(document.getElementsByName('currentElementName')[0].value)[0].value);


}
else
	{
	
	alert("Selected Element Is Not TextArea/TextBox ");
	}
	}
}   
	 
function callOnFocus(obj)
{
	var value=obj.name;

	

	var tagName=obj.tagName;
	//alert("tagname  "+tagName);
	document.getElementsByName("currentElement")[0].value=obj.tagName;
	document.getElementsByName("currentElementName")[0].value=obj.name;
	
	
	}

function removeTags(obj)
{

	var re = /(<([^>]+)>)/gi;
	//alert(obj);
		//return  obj.Replace(re,"");
	var obj=obj+"";
    
    var refAmp=/&[a-zA-z0-9]*;/g;
	obj=obj.replace(re,"");
	return obj.replace(refAmp,"");

}

function getTheLabCannedList(cannedMacroCheck)
{
	

	var flg = false;
	var details = false;
	//var cannedVal=document.getElementsByName("cannedLabCode")[0].value;
	//var cannedVal=document.getElementsByName("currentElementName")[0].value.split("#")[0].substring(5,10) ;
	//alert(document.getElementsByName("currentElementName")[0].value);
	
	if(document.getElementsByName('currentElement')[0].value=='TA')
	{
		

		// document.getElementsByName('currentElement')[0].value='TA';
		var cannedVal=document.getElementsByName("currentElementName")[0].value.split("#")[0].substring(5,10) ;
	}
	else
		{
	var cannedVal=document.getElementById(document.getElementsByName("currentElementName")[0].value.split("#")[0]).value;
     }

	//alert(cannedVal);
	var _mode = "SHOWCANNEDDETAILS";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/invResultEntryTemplateTile.cnt?hmode="+_mode+"&cannedLabCode="+cannedVal+"&cannedOrMacroCheck="+cannedMacroCheck, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			details = data;
			flg = true;
		},
        error: function(error)
        {
             
            details = false;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	//alert(details);
	return details;
}


//for dept template. puneet
function appendTable(obj)
{
	var addRow="";
	//to provide negative button too as enhancement and the check if user wants to modify the rows.
if(parseInt(obj.value))
	{
	
	var nRow=0;
	var tableObj=document.getElementById(obj.getAttribute("changeTableId"));
	var rowPresent=tableObj.rows.length;
var rowIdValueForIteration=rowPresent;
	
	if(rowPresent<=obj.value)//add difference
		{
	for(var i=0;i<obj.value-(rowPresent-1);i++)
		{

	//alert(rowIdValueForIteration);
	
	var tr=document.createElement("TR");
	tr.innerHTML=tableObj.rows[1].innerHTML;
	var newRow=tr;
	var scriptInputField="";
	var buttonId="";
	var buttonParent="-1";
	//alert(newRow.innerHTML);
	$(newRow).find('font').each (function() {
		
		
	$(this).find('script').remove();
			
	
	
		$(this).find('img').each (function() {
			
		
			this.id=rowIdValueForIteration+this.id;
			buttonId=this.id;
			buttonParent=this.id.split("#")[3].substring(0,this.id.split("#")[3].length-1);
		//	alert("button parent set is :"+buttonParent);
				}); 
		
		
		
		$(this).find('input').each (function() {
		
			
	this.value="";
	this.id=rowIdValueForIteration+this.id;
	//alert(this.id);
	//alert(this.id.indexOf(buttonParent)!=-1);
	if(this.id.indexOf(buttonParent)!=-1)
		{
//		alert("button parent is : "+buttonParent);
	scriptInputField=this.id;
	//alert("scriptfiewl :"+scriptInputField);
		}
	
		}); 
		
		}); 
	
	
	
	tr=newRow;
	tableObj.appendChild(tr);
	//alert("input field : "+scriptInputField + " buton : "+buttonId);
	if(!buttonId=="")
	 Calendar.setup({'inputField':scriptInputField ,'ifFormat':'%d-%b-%Y','button':buttonId,'singleClick':true});
	
	
	
	
	
	var numRows=tableObj.rows.length;
	nRow=numRows;
	//var tabRow=tableObj.insertRow(numRows);
	//tabRow.id=parseInt(nRow);
	rowIdValueForIteration++;
	buttonId="";
	buttonParent="-1";
	scriptInputField="";
		}
		}
	else//remove difference
		{
		for(var i=(rowPresent-1);i>rowPresent-(rowPresent-obj.value);i--)
			{
			
			document.getElementById(obj.getAttribute("changeTableId")).deleteRow(i);
			}
		}
	}

else
	{alert("Enter numeric value");
	obj.value="";
	obj.focus;
	}
	
//alert(tableObj.rows.length);
}