/**
 * Added by krishnan nema on 29/01/2019 for save to draft changes
 */

function openLastPage(){
	var val = document.getElementsByName('isSaveToDraft')[0].value;
	alert(val);
	if(val!=null && val=="3"){
		displaySamplePatDetailsDraft();
	}
}


function displaySamplePatDetailsDraft()
{	
	var count=0;
	document.getElementsByName('isPatDetailPage')[0].value="1";
	
	var concatenateChkBoxVal="";
	//var cbs = document.getElementsByTagName('input');
	var cbs =document.getElementsByName('chkSamplePatient');
	for(var i=0; i < cbs.length; i++) {
	 // if(cbs[i].type == 'checkbox') 
    //{
    	
    	
    	count++;	
    	concatenateChkBoxVal =concatenateChkBoxVal.concat("961011800000211#9610110001190129100010#961011000119012910001001#null#null#2#1001#Chikungunia#");
    	concatenateChkBoxVal+='@';
    	 
  	//}
	  
 
      }
	
	if(count==0)
   	{
   	alert("please select a record");
   	return false;
   	}
	//alert(concatenateChkBoxVal);
	document.getElementsByName('selectedCheckbox')[0].value=concatenateChkBoxVal;
	document.getElementsByName('hmode')[0].value='SHOWPATDETAILS';
	
	/*  var cannedVal=document.getElementsByName("cannedLabCode")[0].value;
	 var details=  getcannedDetail(cannedVal);
	 document.getElementsByName("cannedDetails")[0].value=details;
	 alert(details);
	 alert(document.getElementsByName("cannedDetails")[0].value); */
 	document.forms[0].submit();
 	
	}
/*
function onSaveToDraft(){
	var a = onSaveToDraft2();
	alert("qwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww" +
			"qwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww" +
			"qwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww" +
			"qwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww" +
			"qwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww" +
			"qwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww" +
			"");
	
}

*/
function onSaveToDraft()
{

	document.getElementsByName('isSaveToDraft')[0].value="1";
	
	var count=0;
	var concatenateChkBoxVal="";
	var cbs =document.getElementsByName('chkResultEntryPatient');
	
	 var name;
	  var splitTemplateValue;
	  var reqNO=[];
	  var parameterValue=[];
	  var parameterCode=[];
	  var parantParameter=[];
	  var resultEntryTemplateValue=[];
	  var empCode;
	 
	for(var i=0; i < cbs.length; i++)
	{
	if(cbs[i].checked)
    	{

		/* if(document.getElementsByName('empNameWise')[i] != null)
			empCode = document.getElementsByName('empNameWise')[i].value;
		else
			empCode = "0"; */

			empCode = "0"
		//alert(empCode);
		
		document.getElementsByName('chkResultEntryPatient')[i].value = document.getElementsByName('chkResultEntryPatient')[i].value + "#" +empCode 
       // alert("document.getElementsByName('chkResultEntryPatient')["+i+"].value : "+document.getElementsByName('chkResultEntryPatient')[i].value);
		//var values=document.getElementById(i+"chkBOx").value;
		//alert("The value is"+values);
		//alert("dataaaa"+document.getElementsByName('chkResultEntryPatient')[i].value);

		
		
        var commentsboxdata=document.getElementsByName('chkResultEntryPatient')[i].value;

        if(commentsboxdata.split("#")[19]!="NA")
            {

        	var sampleid=commentsboxdata.split("#")[20];
    		sampleid=sampleid.split("/")[0];

    	//	alert("sampleid"+sampleid);
    		
        commentsboxdata=commentsboxdata.split("#")[0]+"#"+commentsboxdata.split("#")[1]+"#"+sampleid;
            }else
        	commentsboxdata=commentsboxdata.split("#")[0]+"#"+commentsboxdata.split("#")[1]+"#";    
       // alert("id"+commentsboxdata);
        if(document.getElementById(commentsboxdata)!=null  )
            {
        //    alert("dd");
        	var commentBoxEditedValue = "";
        	if( document.getElementById(commentsboxdata).value==""){
        			document.getElementById(commentsboxdata).value=" ";	
        	}else{
        		commentBoxEditedValue = document.getElementById(commentsboxdata).value;
        		commentBoxEditedValue = document.getElementById(commentsboxdata).value;
        		var check23 = checkReservedCahracters(commentBoxEditedValue);
          	  if(check23==true){
      			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
      			return;
              	}else{
      			
                  } 
        		commentBoxEditedValue = removeSpecialCharacter(commentBoxEditedValue);
        		document.getElementById(commentsboxdata).value=commentBoxEditedValue;
            }
        	
        	//if( document.getElementById(commentsboxdata).value=="")
        		//document.getElementById(commentsboxdata).value=" ";	
        	
        	document.getElementsByName('chkResultEntryPatient')[i].value = document.getElementsByName('chkResultEntryPatient')[i].value + "#" +empCode+"#"+document.getElementById(commentsboxdata).value;        	

            }
        else
            {

        	document.getElementsByName('chkResultEntryPatient')[i].value = document.getElementsByName('chkResultEntryPatient')[i].value + "#" +empCode+"#"+" ";

            }
        
     //   alert("commentbox data"+document.getElementById(commentsboxdata).value);
		 
        /* Added By prashant For Indication */
        var indicationInputId="indicationInput#"+document.getElementsByName('chkResultEntryPatient')[i].value.split("#")[1];
      // alert(indicationInputId); 
       var indicationInputElement = document.getElementById(indicationInputId);
      // alert(indicationInputElement.value);
      // alert(document.getElementsByName('chkResultEntryPatient')[i].value);
       
        if(indicationInputElement && indicationInputElement.value!=null && indicationInputElement.value!=""){
        	var checkspes = checkReservedCahracters(indicationInputElement.value);
        	  if(checkspes==true){
    			alert("Indication contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
    			return;
            	}else{} 
        	  indicationInputElement.value = removeSpecialCharacter(indicationInputElement.value);
        	document.getElementsByName('chkResultEntryPatient')[i].value=document.getElementsByName('chkResultEntryPatient')[i].value+"#"+indicationInputElement.value;
        }else{
        
        	document.getElementsByName('chkResultEntryPatient')[i].value=document.getElementsByName('chkResultEntryPatient')[i].value+"#"+" "
        }
        //alert(document.getElementsByName('chkResultEntryPatient')[i].value);
        
        
		count++;	
	        var k=0;
           //alert(document.getElementById(i+'templateValue').getElementsByTagName("input"));
             
//          
//         alert("get_tags length"+get_tags.length);
//            for (var i=0; i<get_tags.length; i++) {
//         	    // assigns style properties
//         	     var name=get_tags[i].name;
//         	     alert("i"+name);
//         	  }
        	 var checkBoxId=cbs[i].id;
        	//alert("checkBoxId : "+checkBoxId);
        	// var splitTheCheckBoxId=checkBoxId.substr(0,2);
        	 
        	 var splitTheCheckBoxId=checkBoxId.replace('chkBOx', '');
			 
        	// alert(splitTheCheckBoxId);
        	 
         //   alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a").length);
       // return false;
             for(k;k<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input").length;k++)
        	    {
                    // alert("input");
             	
                 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
              //   alert(values);
                 get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input");
                   name=get_tags[k].name;
                  // alert(name);
                   id=get_tags[k].id;
                 //  alert(id);
                   //alert("id for "+k+"    "+id);
                   hiddenid="hiddenid"+id;
                   defaultid="default"+hiddenid;
              	  typ=get_tags[k].type;
              	 // alert(typ);
              	//  alert(get_tags[k].onclick=='callOnClick(this)');
              	 // return false;
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
              //   alert(parameterValue);
                    parameterCode.push(parameterValue.substring(0, 5));
                //    alert( parameterCode.push(parameterValue.substring(0, 5)));
                    parantParameter.push(parameterValue.substring(9,18));
                  
                      
                //  alert("parameterValue"+parameterValue+"reqno"+reqNO+"parameter"+parantParameter);
                 
                      /* if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value=="")
                             {
                                    alert("Enter the field Focussed");
                                    document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].focus();
                                 return false;
                              } */
                 
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
                           else if(typ=='file')
                           {

                          	  var x = document.getElementById(name);
                 				 var file = x.files[0];

                 				 /*  alert("filefile"+file);
                 		       if( file!=undefined)
                     		       {
                     		       alert("yes");
                     		       }
                 		       else
                     		       {
                     		       alert("bno");
                     		       } */

                 		       var uploadid="view@@"+name;
                    		   // alert(document.getElementById(uploadid).className);
                    		
                 		       //return null; 
                 		       
                               if( file!=undefined)
                         resultValidationTemplateValue="File Uploaded";
                               else if( document.getElementById(uploadid)!=null && document.getElementById(uploadid).className !=null)
                                   resultValidationTemplateValue="File Uploaded";
                 
                                  }
                           else    
                      resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value;
                           //added by krishnan nema on 16/10/2018
                          /* var res  = resultValidationTemplateValue.replace("$", " ", true);
               	         var res2  = res.replace("#", " ", true);
               	         var res3 = res2.replace("~", " ", true);
               	      var res4 = res3.replace("`", " ", true);
               	      resultValidationTemplateValue = res4.replace("@", " ", true);*/
                           var check23 = checkReservedCahracters(resultValidationTemplateValue);
                       	  if(check23==true){
                   			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
                   			return;
                           	}else{
                   			
                               } 
                       resultValidationTemplateValue = removeSpecialCharacter(resultValidationTemplateValue);
                       
                    //  alert(resultValidationTemplateValue);
                         var orderingValue= document.getElementsByName(name+"#order")[0].value;
                          name+='#'+resultValidationTemplateValue+'#'+values+"#"+"-"+"#"+orderingValue;
                    
                    // alert(name);    
                       concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                       concatenateChkBoxVal+='@';
                     //  alert(concatenateChkBoxVal);
                        }
                   }
                          
                          
		          
		          
	             }
        // alert(document.getElementById(i+'templateValue').getElementsByTagName("select").length);
       // alert("select value"+document.getElementById(i+'templateValue').getElementsByTagName("select")[0].value);
        var j=0;
         for(j;j<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select").length;j++)
    	       {
        	 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
     		//alert("The value is"+values);
        	  get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select");
       	   name=get_tags[j].name;
        	 
        	// alert("inside here");
        	//alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length);
            	 /* if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value=="-1" || document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value=="")
	         	   {
				      alert("Enter the field Focussed");
				      document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].focus();
			          return false;
		           } */
	          //   document.getElementsByName('resultEntryTemplateValue')[0].value=document.getElementById(i+'templateValue').getElementsByTagName("input")[j].value;	 
              var multiValue="";
              var resultEntryTemplateValue;
	        if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[0].value!="-1")
              for (var kk=0;kk<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length ;kk++)
	        	  {
	        	  //&& document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[0].value!="-1"
	        	 if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].selected==true)
	        	  {  //alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value)
	        	   	  multiValue+=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value;
	        	      multiValue+="$";   	  
	        	  
	        	  }
	        	 resultEntryTemplateValue=multiValue
	        	  }
	          
	        else
	       	resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value;
		   
	        
	    //    alert(resultEntryTemplateValue);
	      var orderingValue= document.getElementsByName(name+"#order")[0].value;
	        name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-"+"#"+orderingValue;

     		  	concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
    			concatenateChkBoxVal+='@';

    	       
    	       } 
         
         
        
         
    
         //alert("text Area value"+document.getElementById(i+'templateValue').getElementsByTagName("textarea")[0].value);
         var n=0;
          for(n;n<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea").length;n++)
     	       {
    	       
 	             
        	  var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
        	  
        	  
        	  
      		//alert("The value is"+values);
        	  //alert("inside here");
 	             get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea");
 	        	   name=get_tags[n].name;
 	         	// alert("name1"+name);
 	              /* if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value=="")
 	         	   {
 	 	         	   
 				      alert("Enter the field Focussed");
 				      document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].focus();
 			          return false;
 		           } */
 	           //  document.getElementsByName('resultEntryTemplateValue')[0].value=document.getElementById(i+'templateValue').getElementsByTagName("input")[j].value;	 
                
 	         //  alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].id);
 	         var id1 = document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].id;
 	           var editor = CKEDITOR.instances[id1];
 	       
 	         
 	           if(editor!=null){
 	        	 
 	        	  
            //  alert( editor.getData() );
              
              var resultEntryTemplateValue=editor.getData();
              
              //added by krishnan nema on 16/10/2018
              /*var res  = resultEntryTemplateValue.replace("$", " ", true);
  	         var res2  = res.replace("#", " ", true);
  	         var res3 = res2.replace("~", " ", true);
  	         var res4 = res3.replace("`", " ", true);
  	       resultEntryTemplateValue = res4.replace("@", " ", true);*/
              resultEntryTemplateValue = resultEntryTemplateValue.replace("&#39;",
        	    	     "<img id='base64image'src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAYAAAAMCAIAAADONVt5AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAlSURBVBhXY/iPAUgSur8gKmrBfQgbr1DdQQiTNOORAZlC//8DADcC0LbmFlSzAAAAAElFTkSuQmCC' />",true);
             
              var check23 = checkReservedCahracters(resultEntryTemplateValue);
        	  if(check23==true){
    			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
    			return;
            	}else{
    			
                } 
              resultEntryTemplateValue = removeSpecialCharacterEditor(resultEntryTemplateValue);
       	      
              if(resultEntryTemplateValue.length>12000)
            	  {
            	  alert("Editor Data limit is 12000, including spaces. Please don't exceed the limit");
            	  return false;
            	  }
              var orderingValue= document.getElementsByName(name+"#order")[0].value;
              
	          name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-"+"#"+orderingValue;
                
 	          }

 	          else
 	        	  {
     	         var resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value;
     	         //added by krishnan nema on 16/10/2018
     	         
     	        /* var res  = resultEntryTemplateValue.replace("$", " ", true);
     	         var res2  = res.replace("#", " ", true);
     	         var res3  = res2.replace("~", " ", true);
     	        var res4  = res3.replace("`", " ", true);
     	         resultEntryTemplateValue = res4.replace("@", " ", true);*/
     	        var check23 = checkReservedCahracters(resultEntryTemplateValue);
            	  if(check23==true){
        			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
        			return;
                	}else{
        			
                    } 
     	        resultEntryTemplateValue = removeSpecialCharacter(resultEntryTemplateValue);
     	      
		          var orderingValue= document.getElementsByName(name+"#order")[0].value;
		          
		          name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-"+"#"+orderingValue;
		          
 	        	  }
     		  	concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
    			concatenateChkBoxVal+='@';
             //alert(concatenateChkBoxVal);
     	       
     	       } 


	       /* added by chandan */
	       k = 0;
	       for(k;k<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a").length;k++)
   	    {

        	//alert("hyperlink");
        	
            var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
            //alert(values);
            get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a");
              name=get_tags[k].name;
           //   alert(name);
              id=get_tags[k].id;
           // alert(id);
              if(id.includes("view@@")==false) // not to add file upload view hyperlink option by chandan 
              {
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
               var orderingValue= document.getElementsByName(id+"#order")[0].value;
                 var resultEntryTemplateValue=document.getElementsByName('hyperLinkTableSession')[0].value;
                        // alert("id"+id);
                          //alert("qq"+document.getElementsByName('hyperLinkTableSession')[0].value);
                 if(document.getElementsByName('hyperLinkTableSession')[0].value=="")
               {
         	 // alert("true");
         	  var ide=id;
         	  ide="divId_"+id;
         	  var ider="idd"+id;
//alert("ider"+document.getElementById(ider).innerHTML)
         	  if(document.getElementById(ider)!=null && document.getElementById(ider).innerHTML!='')
         	  {
         	  var idee1=id.split("#")[0]+"$$";
         	   idee1+=id.split("#")[3]+"$$";
         	//   alert("idee1"+idee1);
     		  resultEntryTemplateValue="hyperchanks" ;
     		  
              // alert("true1"+resultEntryTemplateValue);
         	  }
     	    
         	  else if(document.getElementById(ide)!=null && document.getElementById(ide).innerHTML!='')
             	  {
             	  var idee=id.split("#")[0]+"$$";
             	   idee+=id.split("#")[3]+"$$";
         		  resultEntryTemplateValue="hyperchanks" ;
         		  
                  // alert("true1"+resultEntryTemplateValue);
             	  }
         	    
               }
                         
              // 	divId_961011000118122710002001#null#template#100171002
                 if(id.includes("view@@")==false) // not to add file upload view hyperlink option by chandan 
                 {
                name+=id+"#"+resultEntryTemplateValue+"#"+values+"#hyperlink"+"#"+orderingValue;
                //alert(name);
               // return null;
               // alert(name);    
                  concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                       concatenateChkBoxVal+='@';
                 }
                 //alert(concatenateChkBoxVal);
                /* return false; */
               /*     }
              }
                */      
                 }
	          
              }
            }
        
       
         
         }
   
    
     }
	

	//alert("Finally concatenateChkBoxValues For Save "+concatenateChkBoxVal);
	//return false;
	
	document.getElementsByName('resultEntryTemplateValueWithHash')[0].value=concatenateChkBoxVal;
	//alert(concatenateChkBoxVal);
	
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
	if(count==0)
   	{
   	  alert("please select a Atleast One record");
   	  return false;
   	}
	 
	
	document.getElementsByName('showStatus')[0].value='0';


	document.getElementsByName('hmode')[0].value="SAVETODRAFT";
	
	document.forms[0].submit();
	 
   return true;
	 
  }




function onModifyAndDraft()
{
	document.getElementsByName('isSaveToDraft')[0].value="1";
	var count=0;
	var concatenateChkBoxVal="";
	var cbs =document.getElementsByName('chkResultEntryPatient');
	
	 var name;
	  var splitTemplateValue;
	  var reqNO=[];
	  var parameterValue=[];
	  var parameterCode=[];
	  var parantParameter=[];
	  var resultEntryTemplateValue=[];
	//alert(cbs.length);
	for(var i=0; i < cbs.length; i++)
	{
	if(cbs[i].checked)
    	{


	//	alert("dataaaa"+cbs[i].value);

        var commentsboxdata=cbs[i].value;

        if(commentsboxdata.split("#")[19]!="NA")
            {
        	var sampleid=commentsboxdata.split("#")[20];
    		sampleid=sampleid.split("/")[0];
        commentsboxdata=commentsboxdata.split("#")[0]+"#"+commentsboxdata.split("#")[1]+"#"+sampleid;
            }else
        	commentsboxdata=commentsboxdata.split("#")[0]+"#"+commentsboxdata.split("#")[1]+"#";    

        if(document.getElementById(commentsboxdata)!=null )
        {
       // alert("dd");
        	var commentBoxEditedValue = "";
            if( document.getElementById(commentsboxdata).value==""){
        		document.getElementById(commentsboxdata).value=" ";	}
            else{
            	commentBoxEditedValue = document.getElementById(commentsboxdata).value;
        		var check23 = checkReservedCahracters(commentBoxEditedValue);
          	  if(check23==true){
      			alert("Document contains one of the reserved characters '@!#$^|\\/`' which is not allowed. Please remove them.");
      			return;
              	}else{
      			
                  } 
        		commentBoxEditedValue = removeSpecialCharacter(commentBoxEditedValue);
        		document.getElementById(commentsboxdata).value=commentBoxEditedValue;
        		}
        	
            cbs[i].value = cbs[i].value +"#"+document.getElementById(commentsboxdata).value; 

            
        }
        else
            {
            cbs[i].value = cbs[i].value +"#"+" "; 

            }
        
        /* Added By prashant For Indication */
        var indicationInputId="indicationInput#"+document.getElementsByName('chkResultEntryPatient')[i].value.split("#")[1];
      // alert(indicationInputId); 
       var indicationInputElement = document.getElementById(indicationInputId);
      // alert(indicationInputElement.value);
      // alert(document.getElementsByName('chkResultEntryPatient')[i].value);
       
        if(indicationInputElement && indicationInputElement.value!=null && indicationInputElement.value!=""){
        	var checkspes = checkReservedCahracters(indicationInputElement.value);
      	  if(checkspes==true){
  			alert("Indication contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
  			return;
          	}else{} 
      	  indicationInputElement.value = removeSpecialCharacter(indicationInputElement.value);
        	document.getElementsByName('chkResultEntryPatient')[i].value=document.getElementsByName('chkResultEntryPatient')[i].value+"#"+indicationInputElement.value;
        }else{
        	
        	document.getElementsByName('chkResultEntryPatient')[i].value=document.getElementsByName('chkResultEntryPatient')[i].value+"#"+" "
        }
        //alert(document.getElementsByName('chkResultEntryPatient')[i].value);
        
        
        // alert("id"+commentsboxdata);
       // alert("commentbox data"+document.getElementById(commentsboxdata).value);
      //  cbs[i].value = cbs[i].value +"#"+document.getElementById(commentsboxdata).value; 


		
		//var values=document.getElementById(i+"chkBOx").value;
		//alert("The value is"+values);
		count++;	
	        var k=0;
           //alert(document.getElementById(i+'templateValue').getElementsByTagName("input"));
             
//          
//         alert("get_tags length"+get_tags.length);
//            for (var i=0; i<get_tags.length; i++) {
//         	    // assigns style properties
//         	     var name=get_tags[i].name;
//         	     alert("i"+name);
//         	  }
        	 var checkBoxId=cbs[i].id;
        	//alert(checkBoxId);
        	// var splitTheCheckBoxId=checkBoxId.substr(0,2);
        	 
        	 var splitTheCheckBoxId=checkBoxId.replace('chkBOx', '');

         	
        	// alert(splitTheCheckBoxId);
        	 
         //   alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a").length);
       // return false;
             for(k;k<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input").length;k++)
        	    {
                    // alert("input");
             	
                 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
              //   alert(values);
                 get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input");
                   name=get_tags[k].name;
                  // alert(name);
                   id=get_tags[k].id;
                 //  alert(id);
                   //alert("id for "+k+"    "+id);
                   hiddenid="hiddenid"+id;
                   defaultid="default"+hiddenid;
              	  typ=get_tags[k].type;
              	 // alert(typ);
              	//  alert(get_tags[k].onclick=='callOnClick(this)');
              	 // return false;
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
              //    alert(parameterValue);
                    parameterCode.push(parameterValue.substring(0, 5));
                //    alert( parameterCode.push(parameterValue.substring(0, 5)));
                    parantParameter.push(parameterValue.substring(9,18));
                  
                      
                //  alert("parameterValue"+parameterValue+"reqno"+reqNO+"parameter"+parantParameter);
                 
                      /* if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value=="")
                             {
                                    alert("Enter the field Focussed");
                                    document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].focus();
                                 return false;
                              } */
                 
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
                           else if(typ=='file')
                           {

                          	  var x = document.getElementById(name);
                 				 var file = x.files[0];

                 				 /*  alert("filefile"+file);
                 		       if( file!=undefined)
                     		       {
                     		       alert("yes");
                     		       }
                 		       else
                     		       {
                     		       alert("bno");
                     		       } */

                 		       var uploadid="view@@"+name;
                    		   // alert(document.getElementById(uploadid).className);
                    		
                 		       //return null; 
                 		       
                               if( file!=undefined)
                         resultValidationTemplateValue="File Uploaded";
                               else if( document.getElementById(uploadid)!=null && document.getElementById(uploadid).className !=null)
                                   resultValidationTemplateValue="File Uploaded";
                 
                                  }
                           else    
                      resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value;
                    //  alert(resultValidationTemplateValue);
                      
                        /*   var res  = resultValidationTemplateValue.replace("$", " ", true);
               	         var res2  = res.replace("#", " ", true);
               	         var res3 = res2.replace("~", " ", true);
               	      var res4 = res3.replace("`", " ", true);
               	   	resultValidationTemplateValue = res4.replace("@", " ", true);*/
                           var check23 = checkReservedCahracters(resultValidationTemplateValue);
                	  if(check23==true){
                			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
                			return;
                        	}else{
                			
                            } 
                           resultValidationTemplateValue = removeSpecialCharacter(resultValidationTemplateValue);
               	      
                          name+='#'+resultValidationTemplateValue+'#'+values+"#"+"-";
                    // alert(name);    
                       concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                       concatenateChkBoxVal+='@';
                     //  alert(concatenateChkBoxVal);
                        }
                   }
                          
                          
		          
		          
	             }
        // alert(document.getElementById(i+'templateValue').getElementsByTagName("select").length);
       // alert("select value"+document.getElementById(i+'templateValue').getElementsByTagName("select")[0].value);
        var j=0;
         for(j;j<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select").length;j++)
    	       {
        	 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
     		//alert("The value is"+values);
        	  get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select");
       	   name=get_tags[j].name;
        	 
        	// alert("inside here");
        	//alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length);
            	/*  if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value=="-1" || document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value=="")
	         	   {
				      alert("Enter the field Focussed");
				      document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].focus();
			          return false;
		           } */
	          //   document.getElementsByName('resultEntryTemplateValue')[0].value=document.getElementById(i+'templateValue').getElementsByTagName("input")[j].value;	 
              var multiValue="";
              var resultEntryTemplateValue;
	        if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[0].value!="-1")
              for (var kk=0;kk<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length ;kk++)
	        	  {
	        	  //&& document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[0].value!="-1"
	        	 if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].selected==true)
	        	  {  //alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value)
	        	   	  multiValue+=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value;
	        	      multiValue+="$";   	  
	        	  
	        	  }
	        	 resultEntryTemplateValue=multiValue
	        	  }
	          
	        else
	       	resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value;
		   
	        
	    //    alert(resultEntryTemplateValue);
	        name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-";
	          
     		  	concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
    			concatenateChkBoxVal+='@';

    	       
    	       } 
         
         
        
         
    
        // alert("text Area value"+document.getElementById(i+'templateValue').getElementsByTagName("textarea")[0].value);
         var n=0;
          for(n;n<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea").length;n++)
     	       {
 	             
        	  var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
        	  
      	//	alert("The value is"+values);
        	  //alert("inside here");
 	             get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea");
 	        	   name=get_tags[n].name;
 	         	// alert("name1"+name);
 	              
 	             /*  if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value=="")
 	         	   {
 				      alert("Enter the field Focussed");
 				      document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].focus();
 			          return false;
 		           } */
 	           //  document.getElementsByName('resultEntryTemplateValue')[0].value=document.getElementById(i+'templateValue').getElementsByTagName("input")[j].value;	 
                
 	         //  alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].id);
 	         var id1 = document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].id;
 	           var editor = CKEDITOR.instances[id1];
 	       
 	         
 	           if(editor!=null){
 	        	 
 	        	  
            //  alert( editor.getData() );
              
              var resultEntryTemplateValue=editor.getData();

  	        /* var res  = resultEntryTemplateValue.replace("$", " ", true);
  	         var res2  = res.replace("#", " ", true);
  	         var res3  = res2.replace("~", " ", true);
  	       var res4  = res3.replace("`", " ", true);
  	       resultEntryTemplateValue = res4.replace("@", " ", true); */
              resultEntryTemplateValue = resultEntryTemplateValue.replace("&#39;",
	      	    	     "<img id='base64image'src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAYAAAAMCAIAAADONVt5AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAlSURBVBhXY/iPAUgSur8gKmrBfQgbr1DdQQiTNOORAZlC//8DADcC0LbmFlSzAAAAAElFTkSuQmCC' />",true);
   
              var check23 = checkReservedCahracters(resultEntryTemplateValue);
        	  if(check23==true){
    			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
    			return;
            	}else{
    			
                } 
              resultEntryTemplateValue = removeSpecialCharacterEditor(resultEntryTemplateValue);
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
     	         var resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value;

     	       /* var res  = resultEntryTemplateValue.replace("$", " ", true);
    	         var res2  = res.replace("#", " ", true);
    	         var res3  = res2.replace("~", " ", true);
    	        var res4  = res3.replace("`", " ", true);
    	        resultEntryTemplateValue = res4.replace("@", " ", true);*/
     	        var check23 = checkReservedCahracters(resultEntryTemplateValue);
            	  if(check23==true){
        			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
        			return;
                	}else{
        			
                    } 
     	        resultEntryTemplateValue = removeSpecialCharacter(resultEntryTemplateValue);
     	         
		          name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-";
	          
 	        	  
 	        	  }
     		  	concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
    			concatenateChkBoxVal+='@';
            //  alert(concatenateChkBoxVal);
     	       
     	       } 


	       /* added by chandan */
	       k = 0;
	       for(k;k<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a").length;k++)
   	    {

        	//alert("hyperlink");
        	
            var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
            //alert(values);
            get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a");
              name=get_tags[k].name;
              id=get_tags[k].id;
          //  alert(id);
              if(id.includes("view@@")==false) // not to add file upload view hyperlink option by chandan 
              {
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

               if(document.getElementsByName('hyperLinkTableSession')[0].value=="")
               {
         	 // alert("true");
         	  var ide=id;
         	  ide="divId_"+id;
         	  var ider="idd"+id;
//alert("ider"+document.getElementById(ider).innerHTML)
         	  if(document.getElementById(ider)!=null && document.getElementById(ider).innerHTML!='')
         	  {
         	  var idee1=id.split("#")[0]+"$$";
         	   idee1+=id.split("#")[3]+"$$";
         	//   alert("idee1"+idee1);
     		  resultEntryTemplateValue=document.getElementById(ider).innerHTML ;
     		  
              // alert("true1"+resultEntryTemplateValue);
         	  }
     	    
         	  else if(document.getElementById(ide)!=null && document.getElementById(ide).innerHTML!='')
             	  {
             	  var idee=id.split("#")[0]+"$$";
             	   idee+=id.split("#")[3]+"$$";
         		  resultEntryTemplateValue=document.getElementById(ide).innerHTML ;
         		  
                  // alert("true1"+resultEntryTemplateValue);
             	  }
         	    
               }
               
               if(id.includes("view@@")==false) // not to add file upload view hyperlink option by chandan 
               {
                     name+=id+"#"+resultEntryTemplateValue+"#"+values+"#hyperlink";
               // alert(name);    
                  concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
               }  concatenateChkBoxVal+='@';
           //    alert(concatenateChkBoxVal);
                /* return false; */
               /*     }
              }
                */      
                 }
              }
	          
            }
        
       
         
         }
   
    
     }
	

	//alert("Finally concatenateChkBoxValues For Save "+concatenateChkBoxVal);
	
	
	document.getElementsByName('resultEntryTemplateValueWithHash')[0].value=concatenateChkBoxVal;
	//alert(concatenateChkBoxVal);
	
	/* for(j;j<reqNO.length;j++)
		{ 
		
	
	document.getElementsByName('resultEntryTemplateValue').value=resultEntryTemplateValue[j];
    alert(document.getElementsByName('resultEntryTemplateValue').value);
	document.getElementsByName('parameterCode').value=parameterCode[j];
    document.getElementsByName('parantParameterCode').value=parantParameter[j];
    document.getElementsByName('requisitionDNo').value=reqNO[j];
		} */
		
		/* /* /* /* /* /* if(document.getElementsByName('resultEntryTemplateValueWithHash')[0].value=="")
			{
			alert("Invalid form. Modify form from Test Parameter Master.");
			return false;
			
			} */ 
	if(count==0)
   	{
   	  alert("please select a Atleast One record");
   	  return false;
   	}
	 
	
	document.getElementsByName('showStatus')[0].value='0';


	document.getElementsByName('hmode')[0].value="MODIFYDRAFT";
	
	document.forms[0].submit();
	 
   return true;
	 
  }



function onSaveValidationToDraft(){
	
	//alert("onsave");

	document.getElementsByName('isSaveToDraft')[0].value="1";
    var count=0;
    var concatenateChkBoxVal="";
    var cbs =document.getElementsByName('chkResultValidationPatient');
  //  alert(cbs);
   // alert(cbs.length);
     var name;
      var splitTemplateValue;
      var reqNO=[];
      var parameterValue=[];
      var parameterCode=[];
      var parantParameter=[];
      var resultValidationTemplateValue=[];
    //alert(cbs.length);
    for(var i=0; i < cbs.length; i++)
    {
    if(cbs[i].checked)
    {


 	   //alert("dataaaa"+cbs[i].value);

        var commentsboxdata=cbs[i].value;

        if(commentsboxdata.split("#")[19]!="NA")
            {
     	   var sampleid=commentsboxdata.split("#")[20];
    		sampleid=sampleid.split("/")[0];
        commentsboxdata=commentsboxdata.split("#")[0]+"#"+commentsboxdata.split("#")[1]+"#"+sampleid;
            }   else
        	commentsboxdata=commentsboxdata.split("#")[0]+"#"+commentsboxdata.split("#")[1]+"#";  


        if(document.getElementById(commentsboxdata)!=null  )
        {
    //    alert("dd");
        	var commentBoxEditedValue = "";
    	if( document.getElementById(commentsboxdata).value==""){
    		document.getElementById(commentsboxdata).value=" ";	}
    	else{
    		commentBoxEditedValue = document.getElementById(commentsboxdata).value;
    		var check23 = checkReservedCahracters(commentBoxEditedValue);
      	  if(check23==true){
  			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
  			return;
          	}else{
  			
              } 
    		commentBoxEditedValue = removeSpecialCharacter(commentBoxEditedValue);
    		document.getElementById(commentsboxdata).value=commentBoxEditedValue;
    	}
    	
       cbs[i].value = cbs[i].value +"#"+document.getElementById(commentsboxdata).value; 

        }
    else
        {

        cbs[i].value = cbs[i].value +"#"+" "; 

        }

        
        /* Added By prashant For Indication */
        var indicationInputId="indicationInput#"+document.getElementsByName('chkResultValidationPatient')[i].value.split("#")[1];
      // alert(indicationInputId); 
       var indicationInputElement = document.getElementById(indicationInputId);
      // alert(indicationInputElement.value);
      // alert(document.getElementsByName('chkResultEntryPatient')[i].value);
       
        if(indicationInputElement && indicationInputElement.value!=null && indicationInputElement.value!=""){
        	var checkspes = checkReservedCahracters(indicationInputElement.value);
      	  if(checkspes==true){
  			alert("Indication contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
  			return;
          	}else{} 
      	  indicationInputElement.value = removeSpecialCharacter(indicationInputElement.value);
        	document.getElementsByName('chkResultValidationPatient')[i].value=document.getElementsByName('chkResultValidationPatient')[i].value+"#"+indicationInputElement.value;
        }else{
        	
        	document.getElementsByName('chkResultValidationPatient')[i].value=document.getElementsByName('chkResultValidationPatient')[i].value+"#"+" "
        }
        //alert(document.getElementsByName('chkResultEntryPatient')[i].value);
        
        
        
           //var values=document.getElementById(i+"chkBOx").value;
           //alert("The value is"+values);
           count++;      
            var k=0;
        //alert(document.getElementById(i+'templateValue').getElementsByTagName("input"));
          
//       
//      alert("get_tags length"+get_tags.length);
//         for (var i=0; i<get_tags.length; i++) {
//             // assigns style properties
//              var name=get_tags[i].name;
//              alert("i"+name);
//           }
            
         var checkBoxId=cbs[i].id;
     	
     	// var splitTheCheckBoxId=checkBoxId.substr(0,2);
     	  var splitTheCheckBoxId=checkBoxId.replace('chkBOx', '');
     	
     	// alert(document.getElementById(splitTheCheckBoxId+"chkBOx").value);
          for(k;k<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input").length;k++)
               {
         	 

          	
              var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
              
              get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input");
                name=get_tags[k].name;
                id=get_tags[k].id;
                hiddenid="hiddenid"+id;
                defaultid="default"+hiddenid;
           	  typ=get_tags[k].type;
           	  hidddentext="hidden";
           
          //    alert("type is " + typ);
                if(typ!=hidddentext)
                {
               	 
               //	 alert("not hidden");
                splitTemplateValue=name.split("#");
                reqNO.push(splitTemplateValue[0]);
                 parameterValue=splitTemplateValue[3];
           //      alert("parameter value    "+parameterValue);
                 parameterCode.push(parameterValue.substring(0, 5));
                 parantParameter.push(parameterValue.substring(9,18));
               
                   
             //  alert("parameterValue"+parameterValue+"reqno"+reqNO+"parameter"+parantParameter);
              
                   /* if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value=="")
                          {
                                 alert("Enter the field Focussed");
                                 document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].focus();
                              return false;
                           } */
              
                      // document.getElementsByName('resultEntryTemplateValue').value.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);       
                 //  resultEntryTemplateValue.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);
                 var resultValidationTemplateValue="";
                 
                   if(id.indexOf('auto')!=-1)    
               	  {
               
               	 if( document.getElementById(hiddenid).value!=null && document.getElementById(hiddenid).value!="" )
               	  resultValidationTemplateValue=document.getElementById(hiddenid).value;
               	 else
               		 resultValidationTemplateValue=document.getElementById(defaultid).value;
               	  } 
                 else 
                     {
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
                          else if(typ=='file')
                          {

                         	  var x = document.getElementById(name);
                				 var file = x.files[0];

                				 /*  alert("filefile"+file);
                		       if( file!=undefined)
                    		       {
                    		       alert("yes");
                    		       }
                		       else
                    		       {
                    		       alert("bno");
                    		       } */

                		       var uploadid="view@@"+name;
                   		   // alert(document.getElementById(uploadid).className);
                   		
                		       //return null; 
                		       
                              if( file!=undefined)
                        resultValidationTemplateValue="File Uploaded";
                              else if( document.getElementById(uploadid)!=null && document.getElementById(uploadid).className !=null)
                                  resultValidationTemplateValue="File Uploaded";
                
                                 }
                          else    
                   resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value;
                          //added by krishnan nema on 16/10/2018
                        /* var res  = resultValidationTemplateValue.replace("$", " ", true);
              	         var res2  = res.replace("#", " ", true);
              	         var res3 = res2.replace("~", " ", true);
              	        var res4 = res3.replace("`", " ", true);
              	        resultValidationTemplateValue = res4.replace("@", " ", true);*/
                          var check23 = checkReservedCahracters(resultValidationTemplateValue);
                      	  if(check23==true){
                  			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
                  			return;
                          	}else{
                  			
                              } 

                          resultValidationTemplateValue = removeSpecialCharacter(resultValidationTemplateValue);
              	       
                       name+='#'+resultValidationTemplateValue+'#'+values+"#"+"-";;
                       
                    concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                    concatenateChkBoxVal+='@';
                     }
                     } 
                }
                       
                       
                
                  
                     
                     
                 }
     // alert(document.getElementById(i+'templateValue').getElementsByTagName("select").length);
    // alert("select value"+document.getElementById(i+'templateValue').getElementsByTagName("select")[0].value);
     var j=0;
      for(j;j<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select").length;j++)
           {   	 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
  		//alert("The value is"+values);
     	  get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select");
    	   name=get_tags[j].name;
     	 
     	// alert("inside here");
   //  	alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length);
         	/*  if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value=="-1" || document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value=="")
	         	   {
				      alert("Enter the field Focussed");
				      document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].focus();
			          return false;
		           } */
	          //   document.getElementsByName('resultEntryTemplateValue')[0].value=document.getElementById(i+'templateValue').getElementsByTagName("input")[j].value;	 
           var multiValue="";
           var resultEntryTemplateValue;
	        if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[0].value!="-1")
           for (var kk=0;kk<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length ;kk++)
	        	  {
	        	  //&& document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[0].value!="-1"
	        	 if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].selected==true)
	        	  { // alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value)
	        	   	  multiValue+=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value;
	        	      multiValue+="$";   	  
	        	  
	        	  }
	        	 resultEntryTemplateValue=multiValue
	        	  }
	          
	        else
	       	resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value;
		   
	        
	   //     alert(resultEntryTemplateValue);
	        name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-";
	          
  		  	concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
 			concatenateChkBoxVal+='@';

           } 
      
      
     // alert("text Area value"+document.getElementById(i+'templateValue').getElementsByTagName("textarea")[0].value);
      var n=0;
       for(n;n<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea").length;n++)
           {
                 
             var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
   //     alert("The value is"+values);
             //alert("inside here");
                 get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea");
                     name=get_tags[n].name;
                   
                  
                /*   if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value=="")
                     {
                              alert("Enter the field Focussed");
                              document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].focus();
                            return false;
                      } */
               //  document.getElementsByName('resultEntryTemplateValue')[0].value=document.getElementById(i+'templateValue').getElementsByTagName("input")[j].value;       
             
            var id1 = document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].id;
	           var editor = CKEDITOR.instances[id1];
	       
	         
	           if(editor!=null){
	        	 
	        	  
         //  alert( editor.getData() );
           
           var resultEntryTemplateValue=editor.getData();

         //added by krishnan nema on 16/10/2018
         
           /* var res  = resultEntryTemplateValue.replace("$", " ", true);
            var res2  = res.replace("#", " ", true);
            var res3  = res2.replace("~", " ", true);
            var res4  = res3.replace("`", " ", true);
            resultEntryTemplateValue = res4.replace("@", " ", true);*/
           resultEntryTemplateValue = resultEntryTemplateValue.replace("&#39;",
  	    	     "<img id='base64image'src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAYAAAAMCAIAAADONVt5AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAlSURBVBhXY/iPAUgSur8gKmrBfQgbr1DdQQiTNOORAZlC//8DADcC0LbmFlSzAAAAAElFTkSuQmCC' />",true);

           var check23 = checkReservedCahracters(resultEntryTemplateValue);
     	  if(check23==true){
 			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
 			return;
         	}else{
 			
             } 
           resultEntryTemplateValue = removeSpecialCharacterEditor(resultEntryTemplateValue);
          
            if(resultEntryTemplateValue.length>12000)
     	  {
     	  alert("Editor Data limit is 12000, including spaces. Please don't exceed the limit");
     	  return false;
     	  
     	  
     	  }
	          name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-";
       
	          }

	          else
	        	  {
  	         var resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value;
//added by krishnan nema on 16/10/2018
  	         
  	         /*var res  = resultEntryTemplateValue.replace("$", " ", true);
  	         var res2  = res.replace("#", " ", true);
  	         var res3  = res2.replace("~", " ", true);
  	        var res4  = res3.replace("`", " ", true);
  	        resultEntryTemplateValue = res4.replace("@", " ", true);*/
  	       var check23 = checkReservedCahracters(resultEntryTemplateValue);
       	  if(check23==true){
   			alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
   			return;
           	}else{
   			
               } 

  	       resultEntryTemplateValue = removeSpecialCharacter(resultEntryTemplateValue);
  	       
		          name+='#'+resultEntryTemplateValue+'#'+values+"#"+"-";
	          
	        	  
	        	  }
     /*        var resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value;
                     name+='#'+resultValidationTemplateValue+'#'+values;*/
              
                  concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                  concatenateChkBoxVal+='@';

           
           }


       /* added by chandan */
	       k = 0;
	       for(k;k<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a").length;k++)
	    {

   	//alert("hyperlink");
   	
       var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
    //   alert(values);
       get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("a");
         name=get_tags[k].name;
         id=get_tags[k].id;
       //  alert(id);
         if(id.includes("view@@")==false) // not to add file upload view hyperlink option by chandan 
         {
       if(id.indexOf("template")!=-1)
              {
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


          if(document.getElementsByName('hyperLinkTableSession')[0].value=="")
          {
    	 // alert("true");
    	  var ide=id;
    	  ide="divId_"+id;
    	  var ider="idd"+id;
//alert("ider"+document.getElementById(ider).innerHTML)
    	  if(document.getElementById(ider)!=null && document.getElementById(ider).innerHTML!='')
    	  {
    	  var idee1=id.split("#")[0]+"$$";
    	   idee1+=id.split("#")[3]+"$$";
    	//   alert("idee1"+idee1);
		  resultEntryTemplateValue="hyperchanks" ;
		  
         // alert("true1"+resultEntryTemplateValue);
    	  }
	    
    	  else if(document.getElementById(ide)!=null && document.getElementById(ide).innerHTML!='')
        	  {
        	  var idee=id.split("#")[0]+"$$";
        	   idee+=id.split("#")[3]+"$$";
    		  resultEntryTemplateValue="hyperchanks" ;
    		  
             // alert("true1"+resultEntryTemplateValue);
        	  }
    	  
    	  else if(document.getElementById(ide)!=null && document.getElementById(ide).innerHTML!='')
     	  {
   		//alert( document.getElementById(id).onclick);
   	  
     	  var idee=id.split("#")[0]+"$$";
     	   idee+=id.split("#")[3]+"$$";
 		  resultEntryTemplateValue="hyperchanks" ;
 		  
          // alert("true1"+resultEntryTemplateValue);
     	  }
    	    
          }
          
          if(id.includes("view@@")==false) // not to add file upload view hyperlink option by chandan 
          {
        	  
        	  
        	  var tocheckfnctn=document.getElementById(id).onclick;
      		var valuoffuncntn=tocheckfnctn;
      		valuoffuncntn=valuoffuncntn.toString();
      		//valuoffuncntn=valuoffuncntn.split("hyper");
                  //  alert(""+valuoffuncntn);
               if(valuoffuncntn.includes("echo"))
                {
                    // alert("match found");

                     name+=id+"#"+resultEntryTemplateValue+"#"+values+"#-";
                     


                     
                  }
               else{
                name+=id+"#"+resultEntryTemplateValue+"#"+values+"#hyperlink";
               }
               // alert(name);    
             concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
             concatenateChkBoxVal+='@';
          }
          //      alert(concatenateChkBoxVal);
          /*     }
         }
           */      
                
              }      
              }
       }  
     
    
      
      }

 
  }
    

  // alert("Finally concatenateChkBoxValues For Save "+concatenateChkBoxVal);
    
    
    document.getElementsByName('resultValidationTemplateValueWithHash')[0].value=concatenateChkBoxVal;
    
    
    /* for(j;j<reqNO.length;j++)
           { 
           
    
    document.getElementsByName('resultEntryTemplateValue').value=resultEntryTemplateValue[j];
 alert(document.getElementsByName('resultEntryTemplateValue').value);
    document.getElementsByName('parameterCode').value=parameterCode[j];
 document.getElementsByName('parantParameterCode').value=parantParameter[j];
 document.getElementsByName('requisitionDNo').value=reqNO[j];
           } */
    if(count==0)
    {
      alert("please select a Atleast One record");
      return false;
    }
     
    
    document.getElementsByName('showStatus')[0].value='0';
    document.getElementsByName('hmode')[0].value="SAVETODRAFT";
    document.forms[0].submit();
     
return false;
     
}