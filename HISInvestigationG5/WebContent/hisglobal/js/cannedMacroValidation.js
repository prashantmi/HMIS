/*
 
Developer  : Pathan Basha 
Created On 9-07-2015
for Canned/Macro Validation
 
*/

 


function openCannedPopup(val)
 
{
	
	if (typeof document.getElementsByName("cannedLabCode")[0]!='undefined') {
	   
	//alert(document.getElementsByName("chkLabName")[0].value);
	   var cannedVal=document.getElementsByName("cannedLabCode")[0].value;
	   var cannedMacroCheck=val;
	 var details=getcannedDetail(cannedVal,cannedMacroCheck); 
	// alert(details);
	 var splitCannedDetails=details.split("@");
	// alert(splitCannedDetails.length-1);
	 if(splitCannedDetails.length-1!=0)
		 { 
	 for(i=0;i<splitCannedDetails.length-1;i++)
		 {
		 if(val=="CANNED")
		 {
		 document.getElementsByName("cannedDataCount")[0].value=splitCannedDetails.length-1;
		 AddRowToTableAddMoreValue(splitCannedDetails[i]);
		 window.scrollTo(0,0);
		    document.getElementById('canned').style.display='block';
		    document.getElementById('canned').onmousedown = function () {
		 		  _drag_init(this);
		 		  return false;

		 		  };
		 }
		    if(val=="MACRO")
			{
		    	document.getElementsByName("macroDataCount")[0].value=splitCannedDetails.length-1;
		    	AddRowToTableAddMoreValueMacro(splitCannedDetails[i]);
		    	 window.scrollTo(0,0);
		 	    document.getElementById('macro').style.display='block';
		 	   document.getElementById('macro').onmousedown = function () {
		 		  _drag_init(this);
		 		  return false;

		 		  };
			}
		 }
		 }
	 else
		 {
		 if(val=="CANNED")
			 alert("No Canned Data Is Mapped For  "+document.getElementsByName("chkLabName")[0].value+"   Lab");
		 if(val=="MACRO")
			 alert("No Macro Data Is Mapped For  "+document.getElementsByName("chkLabName")[0].value+"   Lab");
		 
		 }
	 
}
else
	{
	
	alert("No Template Details Found For "+document.getElementsByName("chkLabName")[0].value);
	}
	}
	
	
function getcannedDetail(cannedVal,cannedMacroCheck)
{
	
	 
	var flg = false;
	var details = false;
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
	return details;
}
	
function lightbox_close(){
    document.getElementById('canned').style.display='none';
    
}

function lightbox_close_macro(){
    document.getElementById('macro').style.display='none';
    
}
 
function AddRowToTableAddMoreValue(splitCannedDetails)
{
	//alert("add row to canned");
	var SplitCanned=splitCannedDetails.split("#");
	var cannedName=SplitCanned[0];
	var cannedContent=SplitCanned[1];
	 
	 var count=parseInt(document.getElementsByName("cannedDetails")[0].value);
	var size=document.getElementsByName("cannedDataCount")[0].value*2;
	var size1=size+count;
	 
	/* if(document.getElementById("addMoreValue").rows.length==size1){
	 
			for(var i = document.getElementById("addMoreValue").rows.length - 1; i > 0; i--)
			{
				document.getElementById("addMoreValue").deleteRow(i);
				
			}
			 document.getElementsByName("cannedDetails")[0].value="1";
	} */
	
	var nRow=0;
	var tableObj=document.getElementById('addMoreValue');
	
	var tr=document.createElement("TR");
	 
	tr.setAttribute("id","setPdf");
	tableObj.appendChild(tr);
	var numRows=tableObj.rows.length;
		nRow=numRows;
 
	var tabRow=tableObj.insertRow(numRows);
	tabRow.id=parseInt(nRow);
	 
	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
 
	td1.innerHTML="<div  align='left' ><input id='"+cannedName+"' type='checkbox' name='chkBoxCannedValues' value='"+cannedContent+"' /></div>";   
	td1.className='tdfont';
	td1.width="1";
	
	td2.innerHTML="<div align='left' >"+cannedName+"</div>";   
	td2.className='tdfont';
	td2.colspan="1";
   
	tabRow.appendChild(td1); 
	tabRow.appendChild(td2);  
	//document.forms[0].numberOfRow.value=numRows;
	}
	
	
	
function AddRowToTableAddMoreValueMacro(splitCannedDetails)
{
	 
	
	//alert(splitCannedDetails);
//	alert("add row to macro");
	var SplitCanned=splitCannedDetails.split("#");
	var cannedName=SplitCanned[0];
	var cannedContent=SplitCanned[1];
	 
	 var count=parseInt(document.getElementsByName("macroDetails")[0].value);
	// alert("count"+count);
	var size=document.getElementsByName("macroDataCount")[0].value*2;
	
//	alert("size"+size);
	var size1=size+count;
//	alert("sizw 1"+ size1);
	 
	/* if(document.getElementById("addMoreValuemacro").rows.length==size1){
	 
			for(var i = document.getElementById("addMoreValuemacro").rows.length - 1; i > 0; i--)
			{
				document.getElementById("addMoreValuemacro").deleteRow(i);
			//	alert("deleting macro");
			}
			 document.getElementsByName("macroDetails")[0].value="1";
			
	} 
	*/
	
	var nRow=0;
	var tableObj=document.getElementById('addMoreValuemacro');
	
	var tr=document.createElement("TR");
	 
	tr.setAttribute("id","setPdf");
	tableObj.appendChild(tr);
	var numRows=tableObj.rows.length;
	//alert("total length"+numRows);
		nRow=numRows;
 
	var tabRow=tableObj.insertRow(numRows);
	tabRow.id=parseInt(nRow);
	 
	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
 
	td1.innerHTML="<div  align='left' ><input type='checkbox' name='chkBoxCannedValues' value='"+cannedContent+"' /></div>";   
	td1.className='tdfont';
	td1.width="1";
	 
	
	td2.innerHTML="<div align='left' >"+cannedName+"</div>";   
	td2.className='tdfont';
	td2.colspan="1";
		
   
	tabRow.appendChild(td1); 
	tabRow.appendChild(td2);  
	 
	 

	//document.forms[0].numberOfRow.value=numRows;
		
	
	
	}	


 function setCannedDetail(canMacVal)
{
	 
	  
	if(canMacVal=="CANNED")
		{
		
	
	if((document.getElementsByName('currentElement')[0].value=='TEXTAREA'||document.getElementsByName('currentElement')[0].value=='textarea')||document.getElementsByName('currentElement')[0].value=='ckeditor'||document.getElementsByName('currentElement')[0].value=='TA') 
	{
		
	var cbs=document.getElementsByName("chkBoxCannedValues");
	var selectedCannedValue="";
	for(var i=0; i < cbs.length; i++)
	{
	if(cbs[i].checked)
    	{
		
		//alert(cbs[i].value);
		if	(document.getElementsByName('currentElement')[0].value=='ckeditor')
		{
		
		selectedCannedValue+="<p>";
		selectedCannedValue+=cbs[i].value;
		selectedCannedValue+="</p>";
		
		}else
			{
		selectedCannedValue+=cbs[i].value;
		selectedCannedValue+='\n';
		
			}
		
		cbs[i].checked=false;
    	}
	
    	}
	
	//document.getElementsByName('currentElementName')[0].value=selectedCannedValue;
	//alert(document.getElementsByName('currentElementName')[0].value);
	//alert("selectedCannedValue : "+selectedCannedValue);
	
	if(!(selectedCannedValue==""))
		{
		//alert(document.getElementsByName('currentElementName')[0].value);
		//alert(document.getElementsByName('currentElement')[0].value);
		
		if((document.getElementsByName('currentElement')[0].value=='TA'))
		{
		
		document.getElementsByName('finalRemarksValue')[0].value+=removeTags(selectedCannedValue);
		}
		
		else
			{//alert(document.getElementsByName(document.getElementsByName('currentElementName')[0].value)[0].value);	
	document.getElementsByName(document.getElementsByName('currentElementName')[0].value)[0].value+=selectedCannedValue ; 
		
	// changed by ashu
	
   /* if	(document.getElementsByName('currentElement')[0].value=='ckeditor')
	CKEDITOR.instances[document.getElementsByName("editorName")[0].value].setData(CKEDITOR.instances[document.getElementsByName("editorName")[0].value].getData()+selectedCannedValue);*/
		
	if(document.getElementsByName('currentElement')[0].value=='ckeditor'){
		CKEDITOR.instances[document.getElementsByName("editorName")[0].value].insertHtml(selectedCannedValue);
	//	document.getElementsByName('currentElement')[0].value='';
	}
			}
	}
//alert(	document.getElementsByName(document.getElementsByName('currentElementName')[0].value)[0].value);
	//myinstances[CKEDITOR.instances[i].name] = CKEDITOR.instances[i].getData(); 
//	 document.getElementsByName('currentElement')[0].value='';
	lightbox_close();
	
	}
	else
		{
		
		alert("Selected Element is Not TextArea ");
		}
		}
	
	if(canMacVal=="MACRO")
	{
//alert(document.getElementsByName('currentElement')[0].value);
if((document.getElementsByName('currentElement')[0].value=='TEXTAREA'||document.getElementsByName('currentElement')[0].value=='textarea')||(document.getElementsByName('currentElement')[0].value=='INPUT'||document.getElementsByName('currentElement')[0].value=='input')) 
{
var cbs=document.getElementsByName("chkBoxCannedValues");
var selectedCannedValue="";
for(var i=0; i < cbs.length; i++)
{
if(cbs[i].checked)
	{
	
	//alert(cbs[i].value);
	
	selectedCannedValue+=cbs[i].value;
	selectedCannedValue+=" ";
	cbs[i].checked=false;
	}

	}

//document.getElementsByName('currentElementName')[0].value=selectedCannedValue;
//alert(document.getElementsByName('currentElementName')[0].value);
if(!(selectedCannedValue=="")){
	//alert(	document.getElementsByName(document.getElementsByName('currentElementName')[0].value)[0].value);
	document.getElementsByName(document.getElementsByName('currentElementName')[0].value)[0].value+=selectedCannedValue;
}



lightbox_close_macro();
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
	//alert(value);
	var tagName=obj.tagName;
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

function deleteTableCanned()
{
	
	for(var i = document.getElementById("addMoreValue").rows.length-1; i >= 0; i--)
	{
		document.getElementById("addMoreValue").deleteRow(i);
		
	}
	
}


function deleteTableMacro()
{
	
for(var i = document.getElementById("addMoreValuemacro").rows.length-1; i >= 0; i--)
{
	document.getElementById("addMoreValuemacro").deleteRow(i);

}


}




var selected = null,  
x_pos = 0, y_pos = 0,  
x_elem = 0, y_elem = 0; 

 
function _drag_init(elem) {
 
selected = elem;
x_elem = x_pos - selected.offsetLeft;
y_elem = y_pos - selected.offsetTop;
}

function _move_elem(e) {
x_pos = document.all ? window.event.clientX : e.pageX;
y_pos = document.all ? window.event.clientY : e.pageY;
if (selected !== null) {
    selected.style.left = (x_pos - x_elem) + 'px';
    selected.style.top = (y_pos - y_elem) + 'px';
}
}

function _destroy() {
selected = null;
}



 
document.onmousemove = _move_elem;
document.onmouseup = _destroy;

/*=========================================end of file===============================================*/
