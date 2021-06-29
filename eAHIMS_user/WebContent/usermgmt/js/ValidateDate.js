
//New function of comparing two dates made by ankur
//on 19 th Jan 2009,bcoz.Since the old function was  not working in a proper way when 
//from date=04-Jan-2009 and toDate=09-Jan-2009
//Hence the new functions were made.

function CompareDates() 
{ 

  var str11 = document.getElementsByName("fromDate")[0].value;
  var str22 = document.getElementsByName("toDate")[0].value; 
 
var index1=str11.indexOf("-");
var index2=str11.indexOf("-");
var index3=str11.indexOf("/");
var index4=str11.indexOf("/");




 if(index1!=-1 && index2!=-1)
 {
 var str1=str11.split("-");
 var str2=str22.split("-");
 }
 else
 if(index3!=-1 && index4!=-1)
 {
 var str1=str11.split("/");
 var str2=str22.split("/");
 }

 
 var dt1 = str1[0];
 var mon1 =str1[1]; 
 var yr1 = str1[2];
 
 var dt2 = str2[0];
 var mon2 =str2[1]; 
 var yr2 = str2[2];
 
 
 
  
  //for FromDate
  var month1=monthvalue(mon1.toUpperCase());
  //for ToDate
  var month2=monthvalue(mon2.toUpperCase());
  
  
  
  var date1 = new Date(yr1, month1, dt1); 
  var date2 = new Date(yr2, month2, dt2); 
  
  if(date2 < date1)
  {
  	 alert("To date can not be less than  From Date");
     return false; 
  } 
  else
  {
 	  return true;
  }
} 

function CompareDates_Old() 
{ 
//  var str1 = document.getElementById("fromDate").value;
//  var str2 = document.getElementById("toDate").value;
  
  var str1 = document.getElementsByName("fromDate")[0].value;
  var str2 = document.getElementsByName("toDate")[0].value;
 alert(str1);
 alert(str2);
 
  var dt1 = parseInt(str1.substring(0,3)); 
  var mon1 = (str1.substring(3,6)).toUpperCase();
  var yr1 = parseInt(str1.substring(7,11)); 
  var dt2 = parseInt(str2.substring(0,3)); 
  
  
  var mon2 = (str2.substring(3,6)).toUpperCase();
  var yr2 = parseInt(str2.substring(7,11)); 
  //for FromDate
  var month1=monthvalue(mon1);
  //for ToDate
  var month2=monthvalue(mon2);
  
  alert(dt2);
  alert(mon2);
  alert(yr2);
  alert(month2);
  
  
  var date1 = new Date(yr1, month1, dt1); 
  var date2 = new Date(yr2, month2, dt2); 
  /*
  var diffDate=new Date();
  diffDate = (date2 - date1);
  diffDate = ((((diffDate / 1000) / 60) / 60) / 24);
  alert("Difference is: " + diffDate);
  alert(diffDate>30);
  if(diffDate<0)
  {
  alert("FromDate cannot be greater than ToDate");
  return false;
  }
  */
alert(date2);
alert(date1);
  if(date2 < date1)
  {
  	 alert("To date can not be less than  From Date");
     return false; 
  } 
  else
  {
 	  return true;
  }
} 
function monthvalue(mon11) 
{
switch(mon11)
   {
   case "JAN" :
   	return 00;
   	
   case "FEB" :
   	return 01;
   	
   case "MAR" :
   	return 02;
   	
   case "APR" :
   	return 03;
   	
   case "MAY" :
   	return 04;
   	
   case "JUN" :
   	return 05;
   
   case "JUL" :
   	return 06;
   	
   case "AUG" :
   	return 07;
   	
   case "SEP" :
   	return 08;
    
   case "OCT" :
   	return 09;
   
   case "NOV" :
   	return 10;
   
   case "DEC" :
   	return 11;

   default :
   			alert("You have entered an invalid month");
   
   }

}
