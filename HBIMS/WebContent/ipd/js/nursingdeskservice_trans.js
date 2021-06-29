

function IpdMSRecordStatus(obj)
{
 
 

  comValue = obj.value;
  com = obj.id;
 

// alert("Selected Combo Value -->"+comValue);

// alert("Combo Id-->"+obj.id);

  var len = com.length;
  var ch = com.charAt(len-1);
  // alert( "comValue ====="+ comValue );

  if(comValue==1)
  {
  
 // alert("inside 1");
  
   document.getElementById("acceptId").style.color = "blue";
   document.getElementById("transferId").style.color = "blue";
   document.getElementById("leaveId").style.color = "blue";
   document.getElementById("dischargeId").style.color = "blue";
   document.getElementById("deathNoteId").style.color = "blue";
 
   
  }
   if(comValue==2)
  {
  
 // alert("inside 2");
   document.getElementById("acceptId").style.color = "blue";
   document.getElementById("transferId").style.color = "blue";
   document.getElementById("leaveId").style.color = "blue";
   document.getElementById("dischargeId").style.color = "blue";
   document.getElementById("deathNoteId").style.color = "blue";
  }
    
  
}


function IpdMscheckColor(mode,display)
{

if( mode == "ACCEPT" && document.getElementById("acceptId").style.color == "blue")
{
add(mode);
}

if(mode == "TRANSFER" && document.getElementById("transferId").style.color == "blue")
{

 add(mode);
}
if( mode == "LEAVE" && document.getElementById("leaveId").style.color == "blue")
{

 add(mode);
} 

if( mode == "DISCHARGE" && document.getElementById("dischargeId").style.color == "blue")
{

 add(mode);
} 
if(mode == "DEATHNOTE" && document.getElementById("deathNoteId").style.color == "blue")
{ 
 add(mode);
}

}


