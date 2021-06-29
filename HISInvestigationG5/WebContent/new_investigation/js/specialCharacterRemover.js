/**
 * Added by krishnan nema on 12/02/2019 for removing special characters from result entry, validation, revalidation
 * 
 */


function removeSpecialCharacter(result){

	var res  = result.replace("$", " ", true);
    var res2  = res.replace("#", " ", true);
    var res3 = res2.replace("~", " ", true);
    var res4 = res3.replace("`", " ", true);
    var res5 = res4.replace(/& /g, " and ", true);
    var res6 = res5.replace("!", " ", true);
    
    var flg=isHTML(res6);
    var finalResult = res6;
    if(flg==false)
    	{
    	   var res7=res6.replace("", "", true);
    	    var res8=res7.replace("", "", true);
    	 
    	var res9 = res8.replace(/</g, "&lt;");
    var res10 = res9.replace(/>/g, "&gt;");
 
     finalResult = res10.replace("@", " ", true);
    	}
    else
    	{
    	finalResult = res6.replace("@", " ", true);
    	}
    
//alert("11");
	//alert("ml"+finalResult);
    return  finalResult;
}

function removeSpecialCharacterEditor(result){

   // alert("result"+result);
	//var res  = result.replace("$", " ", true);
    var res2  = result.replace("#", " ", true);
    var res3 = res2.replace("~", " ", true);
    var res4 = res3.replace("`", " ", true);
    //var res5 = res4.replace("!", " ", true);
/*    var res6 = res5.replace("<", " less than ", true);
    var res7 = res6.replace(">", " greater than ", true);*/
    
    
    /*var flg=isHTML(res4);
    aert("flg"+flg);
    var finalResult = res4;
    if(flg==true)
    	{
    */
    var res5=res4.replace("", "", true);
    var res6=res5.replace("", "", true);
    var res7=res6.replace(/&amp; /g, " and ", true);

    var res8 = res7.replace("", "", true);
        var res9 = res8.replace("", "", true);
         var res10 = res9.replace("@", " ", true);
         var   finalResult=res10;
    /*	}
    else
    	{
    	var res5 = res4.replace("<", " less than ", true);
    	 var res6 = res5.replace(">", " greater than ", true);
         
    	finalResult = res6.replace("@", " ", true);
    	}
    
    */
 //   alert("finalResult"+finalResult);
    return  finalResult;
}

function checkReservedCahracters(value){
	//alert("fdejrh");
	//alert("Value "+value);
	var specialChars = "@!#$^|`";
	for(i = 0; i < specialChars.length;i++){
	//	alert("werwrwr");
        if(value.indexOf(specialChars[i]) > -1){
      ///  	alert("true");
            return true;
        }
    }
    return false;
}


function isHTML(str) {
	  var a = document.createElement('div');
	  a.innerHTML = str;

	  for (var c = a.childNodes, i = c.length; i--; ) {
	    if (c[i].nodeType == 1) return true; 
	  }

	  return false;
	}





function setdepe(parameterValue,resultValidationTemplateValue,mymapvalue,values,checkBoxId)
{
	
    //alert("setdata");
//    var strr="128143612,128143615,128153613,128153620";
  
	
	// dev
	// var strr="128143612,128143615,128153613,128153617";
    
//	var strr1="128153620,128153616";
    
	// testing
//var strr="136312385,136312387,136312388,136312389,136312397,136315185,136335194,136335195,136335196,136345194,136345195,136345196,";

    
	 //var strr1="136345202,136345203,136345204";
    
    var strr="";
     var strr1="";    
	 if(hospitalcode_new=="96101")
	 {
		 strr="128143612,128143615,128153613,128153617";
			 strr1="128153620,128153616";
	 }
	 
	 
	 if(hospitalcode_new=="37913")
	 {
		 strr="136312385,136312387,136312388,136312389,136312397,136315185,136335194,136335195,136335196,136345194,136345195,136345196,";
		 strr1="136345202,136345203,136345204";
		 
	 }
	 
	 
	if(strr.includes(parameterValue))
    {
          //	 alert("textvoxxx"+resultValidationTemplateValue);
         
          	 var vali=resultValidationTemplateValue;
          	 if(vali=="")
          	 {
          		 vali="0";
          	 }
          	 
          	 if(mymapvalue.has(values.split("#")[1]+"#"+parameterValue.substring(0, 5)+"#"+checkBoxId+"#13"))
          		 {
          	 var vali2=mymapvalue.get(values.split("#")[1]+"#"+parameterValue.substring(0, 5)+"#"+checkBoxId+"#13");
				 vali=parseFloat(vali)+parseFloat(vali2);
          		 }
          //	 alert("vali"+vali);
          	 mymapvalue.set(values.split("#")[1]+"#"+parameterValue.substring(0, 5)+"#"+checkBoxId+"#13", vali);
    }
              
              
              if(strr1.includes(parameterValue))
              {
                    //	 alert("textvoxxx"+resultValidationTemplateValue);
                   
                    	 var vali=resultValidationTemplateValue;
                    	 if(vali=="")
                    	 {
                    		 vali="0";
                    	 }
                    	 
                    	 if(mymapvalue.has(values.split("#")[1]+"#"+parameterValue.substring(0, 5)+"#"+checkBoxId+"#"+"23"))
                    		 {
                    	 var vali2=mymapvalue.get(values.split("#")[1]+"#"+parameterValue.substring(0, 5)+"#"+checkBoxId+"#"+"23");
          				 vali=parseFloat(vali)+parseFloat(vali2);
                    		 }
            //        	 alert("vali"+vali);
                    	 mymapvalue.set(values.split("#")[1]+"#"+parameterValue.substring(0, 5)+"#"+checkBoxId+"#"+"23", vali);
              } 
              
	
}


function ischeckk(mymapvalue)
{

 	              
	        var msgg="";
	        var flagg=false;
 	 for (const [key, value] of mymapvalue.entries()) {
 	  	  
 	 //     alert("key"+key+"value"+value);

 	         var checkcall=parseFloat(value);
			 
			 checkcall=checkcall.toFixed(2);
			 
 	       //  alert("checkcall"+checkcall);

 	         if(checkcall > parseFloat(100.00))
 	        	 {
 	      //  	 alert("keyy"+key);
 	        //	alert("keyy12"+key.split("#")[2]);
	        	 
 	        	 var idee1=key.split("#")[2];
 	       	 var matchval=key.split("#")[3];
  	        
 	        	 // alert("idee1"+idee1);
 	        	 var idval=document.getElementById(idee1).value;
 	        	// alert("idval"+idval) ;

 	        	var patcrnoo=idval.split("#")[0];
 	        	var testt =idval.split("#")[17];
 	        	var sample_lab=idval.split("#")[20];

 	        	var msg_para="";
	 	       	 
 	        	if(testt.includes("Complete") || testt.includes("CBC") || testt.includes("cbc") || testt.includes("Indirect123"))
 	        		{

 	        		if(matchval=="23")
 	 	 	       	 {
 	 	 	       		///msg_para="Semen Morpholosis";
 	 	 	       	 }
 	        		else
 	        			{
 	        			msg_para="Differential Leukocyte Count";
 	        			}
 	        		}
 	        	else if(testt.includes("Semen") || testt.includes("Indirect123"))
	        		{
 	        		
 	        		if(matchval=="23")
 	 	 	       	 {
 	 	 	       		msg_para="Semen Morpholosis";
 	 	 	       	 }
 	        		else
 	        			{
 	        			msg_para="Microscopic Examination(A+B+C)";
 	        			}
 	        		
	        		}
	 	       	 
 	        	
 	        	
 	        	msgg=msgg+"For Patient "+patcrnoo+" having Test "+testt+" and Sample/Lab no "+sample_lab+" "+msg_para+" values greater than 100\n";
 	        
 	        	flagg=true;
               
 	        	 }

 	         
 	         if(checkcall < parseFloat(100.00))
	        	 {
	        	 var idee11=key.split("#")[2];
	        	 var matchval=key.split("#")[3];
	   	        
	        	 // 	 alert("idee1"+idee1);
	        	 var idval1=document.getElementById(idee11).value;
	        	// alert("idval"+idval) ;

	        	var patcrnoo1=idval1.split("#")[0];
	        	var testt1 =idval1.split("#")[17];
	        	var sample_lab1=idval1.split("#")[20];

 	        	var msg_para="";
	 	       	 
 	        	if(testt1.includes("Complete") || testt1.includes("CBC") || testt1.includes("Indirect123"))
 	        		{

 	        		if(testt1=="23")
 	 	 	       	 {
 	 	 	       		///msg_para="Semen Morpholosis";
 	 	 	       	 }
 	        		else
 	        			{
 	        			msg_para="Differential Leukocyte Count";
 	        			}
 	        		}
 	        	else if(testt1.includes("Semen") || testt1.includes("Indirect123"))
	        		{
 	        		
 	        		if(matchval=="23")
 	 	 	       	 {
 	 	 	       		msg_para="Semen Morphology";
 	 	 	       	 }
 	        		else
 	        			{
 	        			msg_para="Microscopic Examination(A+B+C)";
 	        			}
 	        		
	        		}
	        	
	        	msgg=msgg+"For Patient "+patcrnoo1+" having Test "+testt1+" and Sample/Lab no "+sample_lab1+" "+msg_para+" values less than than 100\n";
	        
	        	flagg=true;

	        	 }
 	         
 			 }

 	 //alert("flagg"+flagg);
 if(flagg)
	 {
	 msgg=msgg+"\n Are you sure you want to save all selected Records ?"
  	if (confirm(msgg)) {
  	return false;
  	}
  	else
  		{
  		return true;
  		}
	 }
}

//depe

function setdependent_all(obj,event)
{
	var obj_val=obj.name;
	//alert("obj_val1"+obj_val);

	//var cd=event.target.value;
	//alert("cd"+cd);

	var reqdnoo=obj_val.split("#")[0];
	var blankk=obj_val.split("#")[1];
	var temolatee=obj_val.split("#")[2];
	var paracodee=obj_val.split("#")[3];
	
	var vall=document.getElementsByName(obj_val)[0].value;
	
	var nums=document.getElementsByName(obj_val) ;
	//alert("zx");
	nums[0].onkeyup = function(){alert("vv"); return summer_all(obj,event) };
	
	summer_all(obj,event);
/* 	var cd1=vall;
	alert("cdd"+cd1);
	//alert("paracodee"+paracodee);

	if(paracodee=="128153613")
	{
	var new_obj_val=reqdnoo+"#"+blankk+"#"+temolatee+"#"+"128153620";
	//alert("new_obj_val"+new_obj_val);
	document.getElementsByName(new_obj_val)[0].value=cd1;
	} */
	
}

function summer_all(obj,event)
{

	
	
	var obj_val=obj.name;
	//alert("obj_val"+obj_val);

	//var cd=event.target.value;
	//alert("cd"+cd);

	var reqdnoo=obj_val.split("#")[0];
	var blankk=obj_val.split("#")[1];
	var temolatee=obj_val.split("#")[2];
	var paracodee=obj_val.split("#")[3];
	
	
	
	
	var vall=document.getElementsByName(obj_val)[0].value;
	
	var nums=document.getElementsByName(obj_val) ;
	
	nums[0].onkeyup = function(){return summer_all(obj,event)};
	
 	var cd1=vall.trim() ;
 	
 	if(cd1=="")
 		{
 		cd1="a" ;
 		}
	//alert("cdd"+cd1);
	//alert("paracodee"+paracodee);

	
 	
 	setpara_all(reqdnoo,blankk,temolatee,paracodee,cd1); 
	
}

function setpara_all(reqdnoo,blankk,temolatee,paracodee,cd1)
{
	
	// dev
	//var tlc_code="128153617"; // dep para value like tlc
	
	//testing
	//var tlc_code="136311180"; // dep para value like tlc

	var depe_data1= new Map();
	//dev
//	depe_data1.set(136312385,136315186);
//	depe_data1.set("128153616","128153604");
	
//	testing
	var tlc_code="";

	
	 if(hospitalcode_new=="96101")
	 {
		 tlc_code="128153617";
		 depe_data1.set("136312385","136315186");
		depe_data1.set("128153616","128153604");
		
		 
	 }
 	
	 
	 
	 if(hospitalcode_new=="37913")
	 {
		 tlc_code="136311180"; 
			depe_data1.set("136312385","136315186");
			depe_data1.set("136312387","136315188");
			depe_data1.set("136312388","136315189");
			depe_data1.set("136312389","136315187");
			depe_data1.set("136312397","136315190");

			
	 }
	 
	 
	if(tlc_code==paracodee ) // for which parameter value change
	{
		
		
	 	 for (const [key, value] of depe_data1.entries()) {
	 	  	  
	 	// 	      alert("key"+key+"value"+value);
	 		var new_obj_val=reqdnoo+"#"+blankk+"#"+temolatee+"#"+key;

	 		var newwval="";
	 		if(document.getElementsByName(new_obj_val)[0]!=undefined)
	 		{
	 			newwval=document.getElementsByName(new_obj_val)[0].value;
	 		}
	 		
	 		 
	 		newwval=newwval.trim();

	 	 	if(newwval=="")
	 	 		{
	 	 		newwval="a" ;
	 	 		}

	         if(!isNaN(cd1) && !isNaN(newwval) )
	        	 {
	
	        		var forlulabased=((newwval)*(cd1))/100 ;
					
					var tt=forlulabased % 1 == 0;
					
					if(tt==true)
					{
						
					}
				else{
					
					
					forlulabased=forlulabased.toFixed(2) ;
				}
					
	        		//alert(forlulabased);
	        	var new_obj_val=reqdnoo+"#"+blankk+"#"+temolatee+"#"+value;
	        	//alert("new_obj_val"+new_obj_val);
	        	
	        	document.getElementsByName(new_obj_val)[0].value=forlulabased;
	        	
	        	
	        	 }
	         
	         else
        	 {
        	 var new_obj_val=reqdnoo+"#"+blankk+"#"+temolatee+"#"+value;
        		
        	 if(document.getElementsByName(new_obj_val)[0]!=undefined)
        		 {
        	 document.getElementsByName(new_obj_val)[0].value="";
        	 }      	 
        	 }

	 	 }
		
	
	}
	
}


//end

function setdependent(obj,event)
{
	var obj_val=obj.name;
	//alert("obj_val"+obj_val);

	//var cd=event.target.value;
	//alert("cd"+cd);

	var reqdnoo=obj_val.split("#")[0];
	var blankk=obj_val.split("#")[1];
	var temolatee=obj_val.split("#")[2];
	var paracodee=obj_val.split("#")[3];
	
	var vall=document.getElementsByName(obj_val)[0].value;
	
	var nums=document.getElementsByName(obj_val) ;
	
	nums[0].onkeyup = function(){return summer(obj,event)};
	
	summer(obj,event);
/* 	var cd1=vall;
	alert("cdd"+cd1);
	//alert("paracodee"+paracodee);

	if(paracodee=="128153613")
	{
	var new_obj_val=reqdnoo+"#"+blankk+"#"+temolatee+"#"+"128153620";
	//alert("new_obj_val"+new_obj_val);
	document.getElementsByName(new_obj_val)[0].value=cd1;
	} */
	
}

function summer(obj,event)
{

	
	
	var obj_val=obj.name;
	//alert("obj_val"+obj_val);

	//var cd=event.target.value;
	//alert("cd"+cd);

	var reqdnoo=obj_val.split("#")[0];
	var blankk=obj_val.split("#")[1];
	var temolatee=obj_val.split("#")[2];
	var paracodee=obj_val.split("#")[3];
	
	
	
	
	var vall=document.getElementsByName(obj_val)[0].value;
	
	var nums=document.getElementsByName(obj_val) ;
	
	nums[0].onkeyup = function(){return summer(obj,event)};
	
 	var cd1=vall.trim() ;
 	
 	if(cd1=="")
 		{
 		cd1="a" ;
 		}
	//alert("cdd"+cd1);
	//alert("paracodee"+paracodee);

	
 	
 	setpara(reqdnoo,blankk,temolatee,paracodee,cd1); 
	
}

function setpara(reqdnoo,blankk,temolatee,paracodee,cd1)
{
	
	//dev
	//var tlc_code="128153617"; // dep para value like tlc

	// testig
//	var tlc_code="136311180"; // dep para value like tlc

	
	var depe_data1= new Map();

	// dev
	//depe_data1.set("128153613","128153620");
	//depe_data1.set("128153616","128153604");
	
//	testing


	
	
	var tlc_code="";

	 if(hospitalcode_new=="96101")
	 {
		 tlc_code="128153617";
		 depe_data1.set(136312385,136315186);
		depe_data1.set("128153616","128153604");
		
		 
	 }
	
	 
	 
	 if(hospitalcode_new=="37913")
	 {
		 tlc_code="136311180"; 
			depe_data1.set("136312385","136315186");
			depe_data1.set("136312387","136315188");
			depe_data1.set("136312388","136315189");
			depe_data1.set("136312389","136315187");
			depe_data1.set("136312397","136315190");

			
	 }
	 
	 
	var fixed_tlc_testpara_code_name=reqdnoo+"#"+blankk+"#"+temolatee+"#"+tlc_code;  // dep para value like tlc
	var fixed_tlc_testpara_code=document.getElementsByName(fixed_tlc_testpara_code_name)[0].value;
	
 fixed_tlc_testpara_code=fixed_tlc_testpara_code.trim() ;
 	
 	if(fixed_tlc_testpara_code=="")
 		{
 		fixed_tlc_testpara_code="a" ;
 		}
 	
 	
	if(depe_data1.has(paracodee) ) // for which parameter value change
	{
		
	         if(!isNaN(fixed_tlc_testpara_code) && !isNaN(cd1) )
	        	 {
		var forlulabased=((fixed_tlc_testpara_code)*(cd1))/100 ;
		
		
					var tt=forlulabased % 1 == 0;
					
					if(tt==true)
					{
						
					}
				else{
					
					
					forlulabased=forlulabased.toFixed(2) ;
				}
				
		//alert(forlulabased);
	var new_obj_val=reqdnoo+"#"+blankk+"#"+temolatee+"#"+depe_data1.get(paracodee);
	//alert("new_obj_val"+new_obj_val);
	
	document.getElementsByName(new_obj_val)[0].value=forlulabased;
	        	 }
	         else
	        	 {
	        	 var new_obj_val=reqdnoo+"#"+blankk+"#"+temolatee+"#"+depe_data1.get(paracodee);
	        		
	        	 document.getElementsByName(new_obj_val)[0].value="";
	        		        	 
	        	 }
	}
	
}

