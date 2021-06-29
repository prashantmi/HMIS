/*Created by PrashantMi for replacing caret sign for AJAX URl as caret not supported in url in wildfly16*/
/*PRASHANT WILDFLY*/

function replaceCaret(argument){
	var datType = typeof argument;
	//alert(datType+" 1st");
	if(datType=="object"){
    //alert(datType+" 2nd");
		for (var j=0; j<Object.keys(argument).length; j++){
			
			var dataType2= typeof argument[j];
            //alert(dataType2+" 2 1st");
          
			if (dataType2=="string"){
				argument[j]=argument[j].replace(/\^/g, "%5E");
			 }
			else if (dataType2=="undefined" && Object.keys(argument).length>0){
				var keyNames = Object.keys(argument);
				argument[keyNames[j]]=argument[keyNames[j]].replace(/\^/g, "%5E");
			}
            else if(dataType2=="object"){
             argument[j]=replaceCaret(argument[j]);
            }
		}
	}
	
	else if(datType=="string"){
    //alert(datType+" 4th");
	argument=argument.replace(/\^/g, "%5E");
	}
	
	else if(datType=="undefined"){
		argument="undefined";
	}
	return argument;
}


function showtestswithteriff(a,labcode,id,btn)
{
    var fl=isappointmentmandatory(labcode);
  // alert(fl);
    if(fl.split("#")[0]=="1")
	{
    	if(fl.split("#")[1]=="1")
    	{
        	alert("Appointment is mandatory for this lab.Test can't be raised.");
        	return null;
        }

    }
    else
        {
         var flag="";
    	if(fl.split("#")[1]=="1")
    	{

        }

		
        if(flag=="1")
        return null;

        }
	//alert("showtestswithteriff called............");
	document.getElementsByName('searchLabName')[0].value=labcode;
   document.getElementsByName('labwisetestteriff')[0].value="1";
    document.getElementsByName("testLabTestCodeWise")[0].value="myhisswitchTestLab";
    document.getElementsByName('selectlabid')[0].value=id;
       searchLabWiseTest();
			//alert(document.getElementsByName('labwisetestteriff')[0].value);
         document.getElementsByName('labwisetestteriff')[0].value="1";

}

function getTestWiseList(val)
{

	 var vall= document.getElementsByName('labwisetestteriff')[0].value;
	 if(vall==1)
		 {
		// alert(val);
		  document.getElementsByName('searchLabName')[0].value="";
		  document.getElementsByName('labwisetestteriff')[0].value="";
		  document.getElementsByName("testLabTestCodeWise")[0].value="myhisswitchTest";

		      searchLabWiseTest();
	     }
	  document.getElementById("terifftest").style.display="none";
	//alert(val);
	  if(val=='myhisswitchTestLab')
		  {
		 // alert("a");
		  document.getElementsByName("testLabTestCodeWise")[0].value=val;
	  document.getElementById("searchLabTestDiv").style.display="none";
	  document.getElementById("testCodeWiseSearchDiv").style.display="";
	  document.getElementById("automplete-5").focus();



		  }
	  if(val=='myhisswitchTest')
	  {
		 // alert("b");
		  document.getElementsByName("testLabTestCodeWise")[0].value=val;
		  document.getElementById("testCodeWiseSearchDiv").style.display="none";
		  document.getElementById("searchLabTestDiv").style.display="";
		  document.getElementById("automplete-1").focus();

	  }


}


/*For including this js file in another js file*/
/*$.getScript("media/misc/ajaxReplaceURLCaretForWildFly.js", function() {
	   alert("Script loaded but not necessarily executed.");
	});*/
/*or*/
/*<script type="text/javascript" src="media/misc/ajaxReplaceURLCaretForWildFly.js"></script>*/