function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory*
    
	 
	////////alert(document.forms[0].sampleName);
	//////////alert(document.forms[0].sampleName.value);
 
	//alert(document.getElementsByName("elementType")[0].value);
	
	
	 
	if(document.forms[0].testCode &&document.getElementsByName("testCode")[0].value=="-1")
	{
	//	alert("inside");
		alert("Select Test Name   ");
		//document.forms[0].sampleName.focus();
		document.getElementsByName("testCode")[0].focus();
		return false;
	}
	if(document.forms[0].parameterCode && document.getElementsByName("parameterCode")[0].value=="-1"&& document.getElementsByName("parameterCode")[0].value=="")
	{
		alert("Select Parameter   ");
		document.getElementsByName("parameterCode")[0].focus();
		return false;                          
	}
	 
	if(document.getElementsByName("criteriaDesk")[0].value=="-1")
	{
		alert("Select Criteria");
		document.getElementsByName("criteriaDesk")[0].focus();
		return false;                          
	}
	 
	if(document.getElementsByName("loincScale")[0].value=="-1")
	{
		alert("Enter Loinc Scale");
		document.getElementsByName("loincScale")[0].focus();
		return false;                          
	}
	
	if(document.forms[0].elementType && document.getElementsByName("elementType")[0].value=="-1")
	{
		alert("Select Element Type   ");
		document.getElementsByName("elementType")[0].focus();
		return false;                          
	}
	  if(document.getElementsByName("showDefaultValue") != null && document.getElementsByName("showDefaultValue")[0].value == "1") {
		  var editor = CKEDITOR.instances.editor1;  	
	      var content = editor.getData();
	  
	      if(content.length > 12000)
	     {
	    	  
	    	  alert("Please limit the value in Text Area under 12000 characters including spaces.");
	    	  return false;
	     }
   
     }
	 if(document.getElementsByName("elementType")[0].value=="B")
		 {
	var  elementTyp=document.getElementById("boldLabel").value+'#'+document.getElementById("underlineLabel").value;
	//alert("inside element b label"+elementTyp);
	document.getElementsByName("elementProperty")[0].value=elementTyp;
	//alert(document.getElementsByName("elementProperty")[0].value);
		 }
	 if(document.getElementsByName("elementType")[0].value=="A")
	 {
var  elementTyp=document.getElementById("labelAlignmentTextBox1").value+'#'+document.getElementById("boldTextBox1").value+'#'+document.getElementById("underlineTextBox").value;
//alert("inside element b label"+elementTyp);
   //alert(elementTyp);
document.getElementsByName("elementProperty")[0].value=elementTyp;
document.getElementsByName("showParameterNameasLabel")[0].value=document.getElementById("TextBoxShowParameterNameasLabel1").value;

//alert(document.getElementsByName("elementProperty")[0].value);
	 }
	 
	 if(document.getElementsByName("elementType")[0].value=="C")
	 {
var  elementTyp=document.getElementById("labelAlignmentTextBox2").value+'#'+document.getElementById("boldTextBox2").value+'#'+document.getElementById("underlineTextBox2").value;
//alert("inside element b label"+elementTyp);
   //alert(elementTyp);
document.getElementsByName("elementProperty")[0].value=elementTyp;
document.getElementsByName("showParameterNameasLabel")[0].value=document.getElementById("TextBoxShowParameterNameasLabel2").value;

//alert(document.getElementsByName("elementProperty")[0].value);
	 }
	 
  
	 if(document.getElementsByName("elementType")[0].value=="E")
	 {
         
		 	var  elementTyp=document.getElementsByName("size")[0].value+'#'+document.getElementsByName("maxlength")[0].value+"#"+document.getElementById("boldTextBox").value+'#'+document.getElementById("underlineTextBox").value;
        //  alert("underline text box"+document.getElementById("underlineTextBox").value);
        //  alert("inside element e");
        //  alert(elementTyp);
		 	var functionality="-1";
		 	if(document.getElementsByName("functionality")[0].checked==true)
		 		functionality="0";
		 	else if(document.getElementsByName("functionality")[1].checked==true)
		 		functionality="1";
		 	else
		 		functionality="-1";
           document.getElementsByName("elementProperty")[0].value=elementTyp+"#"+functionality;
          //  alert(document.getElementsByName("elementProperty")[0].value);
           //alert(document.getElementById("defaultTextBoxValue").value);
       
     //  alert( document.getElementsByName("elementProperty")[0].value);
           document.getElementsByName("elementAlign")[0].value= document.getElementById("elementAlignTextBox").value;
           document.getElementsByName("labelAlignment")[0].value= document.getElementById("labelAlignmentTextBox").value;
           document.getElementsByName("textEditorValue")[0].value= document.getElementById("defaultTextBoxValue").value;
           document.getElementsByName("eventFunction")[0].value= document.getElementById("eventFunctionTextBox").value;
         
           document.getElementsByName("event")[0].value= document.getElementById("eventTextBox").value;
    
           document.getElementsByName("showParameterNameasLabel")[0].value=document.getElementById("TextBoxShowParameterNameasLabel").value;
           
	 }
	 
	 
	 if(document.getElementsByName("elementType")[0].value=="D")
	 {
            var  elementTyp=document.getElementById("boldCombo").value+'#'+document.getElementById("underlineCombo").value;
            // alert(elementTyp);
           document.getElementsByName("elementProperty")[0].value=elementTyp;
           document.getElementsByName("labelAlignment")[0].value= document.getElementById("labelAlignmentCombo").value;
           document.getElementsByName("elementAlign")[0].value= document.getElementById("elementAlignCombo").value;
           document.getElementsByName("eventFunction")[0].value= document.getElementById("eventFunctionCombo").value;
           document.getElementsByName("event")[0].value= document.getElementById("eventCombo").value;
           document.getElementsByName("showParameterNameasLabel")[0].value=document.getElementById("ComboShowParameterNameasLabel").value;
          // alert(document.getElementsByName("elementProperty")[0].value);
	 }
	 
	 if(document.getElementsByName("elementType")[0].value=="J")
	 {
            var  elementTyp=document.getElementById("boldButton").value+'#'+document.getElementById("underlineButton").value;
           //  alert(elementTyp);
           document.getElementsByName("elementProperty")[0].value=elementTyp;
           document.getElementsByName("labelAlignment")[0].value= document.getElementById("labelAlignmentButton").value;
           document.getElementsByName("elementAlign")[0].value= document.getElementById("elementAlignButton").value;
           document.getElementsByName("showParameterNameasLabel")[0].value=document.getElementById("ButtonShowParameterNameasLabel").value;
          // alert(document.getElementsByName("elementProperty")[0].value);
	 }
	 if(document.getElementsByName("elementType")[0].value=="K")
	 {
            var  elementTyp=document.getElementById("boldQueryCombo").value+'#'+document.getElementById("underlineQueryCombo").value;
           //  alert(elementTyp);
           document.getElementsByName("elementProperty")[0].value=elementTyp;
           document.getElementsByName("labelAlignment")[0].value= document.getElementById("labelAlignmentQueryCombo").value;
           document.getElementsByName("elementAlign")[0].value= document.getElementById("elementAlignQueryCombo").value;
           document.getElementsByName("showParameterNameasLabel")[0].value=document.getElementById("QueryComboShowParameterNameasLabel").value;
          // alert(document.getElementsByName("elementProperty")[0].value);
	 }
	 
	 if(document.getElementsByName("elementType")[0].value=="H")
	 {
		 //alert("inside here");
		// alert(document.getElementsByName("showDefaultValue")[0].value);
		 document.getElementsByName("labelAlignment")[0].value= document.getElementById("labelAlignmentTextArea").value;
		   document.getElementsByName("event")[0].value= document.getElementById("eventTextArea").value;
		  document.getElementsByName("elementAlign")[0].value= document.getElementById("elementAlignTextArea").value;
		  document.getElementsByName("eventFunction")[0].value= document.getElementById("eventFunctionTextArea").value;
		 if(document.getElementsByName("showDefaultValue")[0].value !="1")
			 {
			 document.getElementsByName("showDefaultValue")[0].value="0";
			 }
           var  elementTyp=document.getElementsByName("showDefaultValue")[0].value+'#'+document.getElementsByName("rows")[0].value+'#'+document.getElementsByName("columns")[0].value+"#"+document.getElementById("boldTextArea").value+'#'+document.getElementById("underlineTextArea").value;             //alert(elementTyp);
          document.getElementsByName("elementProperty")[0].value=elementTyp;
         // alert(document.getElementsByName("elementProperty")[0].value);
          
       
          if(document.getElementsByName("showDefaultValue")[0].value == "1") {
          var editor = CKEDITOR.instances.editor1;

      	// Get editor contents textEditorValue
      	// http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-getData
          //alert( editor.getData() );
        
        	  
          document.getElementsByName("textEditorValue")[0].value=editor.getData();
          
          }
          else
          {
        	 // alert(document.getElementById("defaultTextAreaValue").value);
        	  document.getElementsByName("textEditorValue")[0].value= document.getElementById("defaultTextAreaValue").value;
          }
          //alert(document.getElementsByName("textEditorValue")[0].value);
         // alert(document.getElementsByName("elementProperty")[0].value);
          
          document.getElementsByName("showParameterNameasLabel")[0].value=document.getElementById("TextAreaShowParameterNameasLabel").value;
	 }
	 
	   if(document.getElementsByName("elementType")[0].value=="Z")
    	 {
                var  elementTyp=document.getElementById("boldListBox").value+'#'+document.getElementById("underlineListBox").value;
                 //alert(elementTyp);
               document.getElementsByName("elementProperty")[0].value=elementTyp;
               document.getElementsByName("labelAlignment")[0].value= document.getElementById("labelAlignmentListBox").value;
               document.getElementsByName("elementAlign")[0].value= document.getElementById("elementAlignListBox").value;
               document.getElementsByName("eventFunction")[0].value= document.getElementById("eventFunctionListBox").value;
               document.getElementsByName("event")[0].value= document.getElementById("eventListBox").value;
               document.getElementsByName("showParameterNameasLabel")[0].value=document.getElementById("ListBoxShowParameterNameasLabel").value;
              // alert(document.getElementsByName("elementProperty")[0].value);
    	 }
	   
	   if(document.getElementsByName("elementType")[0].value=="P")
		 {
	            var  elementTyp=document.getElementById("boldAuto").value+'#'+document.getElementById("underlineAuto").value;
	            // alert(elementTyp);
	           document.getElementsByName("elementProperty")[0].value=elementTyp;
	           document.getElementsByName("labelAlignment")[0].value= document.getElementById("labelAlignmentAuto").value;
	           document.getElementsByName("elementAlign")[0].value= document.getElementById("elementAlignAuto").value;
	           document.getElementsByName("eventFunction")[0].value= document.getElementById("eventFunctionAuto").value;
	           document.getElementsByName("event")[0].value= document.getElementById("eventAuto").value;
	           document.getElementsByName("showParameterNameasLabel")[0].value=document.getElementById("AutoShowParameterNameasLabel").value;
	          // alert(document.getElementsByName("elementProperty")[0].value);
		 }
	   
	   if(document.getElementsByName("elementType")[0].value=="S")
		 {
	         
			 	var  elementTyp=document.getElementsByName("size")[0].value+'#'+document.getElementsByName("maxlength")[0].value+"#"+document.getElementById("boldTextBox").value+'#'+document.getElementById("underlineTextBox").value;
	        //  alert("underline text box"+document.getElementById("underlineTextBox").value);
	        //  alert("inside element e");
	        //  alert(elementTyp);
//			 	var functionality="-1";
//			 	if(document.getElementsByName("functionality")[0].checked==true)
//			 		functionality="0";
//			 	else if(document.getElementsByName("functionality")[1].checked==true)
//			 		functionality="1";
//			 	else
//			 		functionality="-1";
//	           document.getElementsByName("elementProperty")[0].value=elementTyp+"#"+functionality;
			 	document.getElementsByName("elementProperty")[0].value=elementTyp;
	          //  alert(document.getElementsByName("elementProperty")[0].value);
	           //alert(document.getElementById("defaultTextBoxValue").value);
	       
	     //  alert( document.getElementsByName("elementProperty")[0].value);
	           document.getElementsByName("elementAlign")[0].value= document.getElementById("elementAlignTextBox").value;
	           document.getElementsByName("labelAlignment")[0].value= document.getElementById("labelAlignmentTextBox").value;
	           document.getElementsByName("textEditorValue")[0].value= document.getElementById("defaultTextBoxValue").value;
	           document.getElementsByName("eventFunction")[0].value= document.getElementById("eventFunctionTextBox").value;
	         
	           document.getElementsByName("event")[0].value= document.getElementById("eventTextBox").value;
	    
	           document.getElementsByName("showParameterNameasLabel")[0].value=document.getElementById("TextBoxShowParameterNameasLabel").value;
	           
		 }
		 
		 
		 
   return true;
 } 	
	
function finalSubmit(mode)
{
	 var cchecked="";
	   for(var i=0;i<document.getElementsByName("paraType").length;i++)
		   {
	                  if(document.getElementsByName("paraType")[i].checked==true)
	                      {
	                	  cchecked=(document.getElementsByName("paraType")[i].value);
	                      }
		   }
	    
	  // alert("finalsubmit"+cchecked);
	   if(cchecked=="2")
		   {
		   
		   if(document.getElementsByName("reqMasterFormType")[0]==undefined)
			   {
			   submitPage(mode);
			   
			   }
			 
			 
	var val=document.getElementsByName("reqMasterFormType")[0].value;
	  //alert("2"+val);
	if(val==2)
		{
		  if(document.getElementsByName("mastertestCode")[0].value=="-1")
			  {
			  alert("Please select Master type");
			    return null;
			  }
			  if(document.getElementsByName("testCodee")[0].value=="-1")  
				{
				  alert("Please select Test Name");
			         return null;
				}    
			  submitPage(mode);
			  }
	else
		{
	if (!validateFinalSubmit()) return;
		}
	submitPage(mode);
		   }
	   else
		   {
		   
		   if (!validateFinalSubmit()) return;
             		submitPage(mode);

		   }
}

function clearAddForm()
 {
  
   document.getElementsByName('testCode')[0].value="-1";

   document.getElementsByName('parameterCode')[0].value="-1";
   document.getElementsByName('parentParameter')[0].value="-1";
   
   document.getElementsByName('criteriaDesk')[0].value="-1";
   document.getElementsByName('elementType')[0].value="-1";
   
   document.getElementsByName('remarks')[0].value="";
    
   document.getElementsByName('functionality')[0].checked=false;
   
   document.getElementsByName('eventFunction')[0].value="";
   document.getElementsByName('event')[0].value="-z	1";
   
  
   
  
 }
  
  
 
 
  
 