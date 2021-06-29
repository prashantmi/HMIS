function submitPage(mode)
{
 document.getElementsByName('hmode')[0].value=mode
 document.forms[0].submit();
}

function submitForm(mode,deptCode,deptType)
{

 document.getElementsByName('hmode')[0].value=mode
 document.getElementsByName('departmentCode')[0].value=deptCode
 document.getElementsByName('departmentTypeCode')[0].value=deptType
 document.forms[0].submit();
}

function submitFormForUnit(mode,unitCode,unit)
{

 document.getElementsByName('hmode')[0].value=mode
 document.getElementsByName('departmentUnitCode')[0].value=unitCode
 document.getElementsByName('departmentUnit')[0].value=unit
 
 document.forms[0].submit();
}

function searchInListBox(eve,obj)
{
      //alert("keycode "+eve.keyCode)
      flag=0;
      var lobj;
      var i = 0;
      var retValue = false;
      //var value=  document.getElementsByName('searchText')[0].value
      //alert(obj.value)
      var value=  obj.value
      if( value != "")
      { 
            //getting object
            lobj = document.getElementsByName('list')[0];
            if(lobj.length > 0)           //list box exists
            {
                  for(i=0;i<lobj.length;i++)
                  {
                        listValue = (lobj.options[i].text).toUpperCase();
                        //alert("listValue="+listValue)
                        if (listValue.indexOf((value.toString()).toUpperCase()) == 0)      //matched
                        {
                              lobj.selectedIndex = i;
                              retValue = true;
                              document.getElementById('listDiv').style.display="block"
                              break;
                        }
                  }
            }
      }
      else{
      //alert("hide")
      	document.getElementById('listDiv').style.display="none"
      	document.getElementsByName('flag')[0].value="false"
      	document.getElementsByName('departmentCode')[0].value=""
      }
      
      if(eve.keyCode==40)//for down arrow key
      {
       //  alert("change control")
		document.getElementsByName('flag')[0].value="true";
		changeControl();
	  }
      
		
      return retValue;
} //end searchInListBox() function

function getDetail(){
	if(document.getElementsByName("departmentCode")[0].value=="" && document.getElementsByName("searchText")[0].value!=" "){
		submitPage('GETDEPTBYNAME');
	}
	else{
		submitPage('GETDEPTBYNAME');
	}		
}
function setValue(obj){
	//alert(obj.value)
	//alert(obj.options[obj.selectedIndex].text)
	document.getElementsByName("department")[0].value=obj.options[obj.selectedIndex].text;
	document.getElementsByName("departmentCode")[0].value=obj.value;
	document.getElementsByName("searchText")[0].value=obj.options[obj.selectedIndex].text;
	document.getElementsByName("searchText")[0].focus()
	document.getElementById('listDiv').style.display="none"
}
function changeControl(){
	
 	document.getElementById('listDiv').style.display="block"
 	document.getElementsByName('list')[0].focus()

}
function callOnBlur(){
	
	var obj=document.getElementsByName("list")[0]
	//alert("blur")
	//alert(obj.value)
	
	document.getElementsByName("searchText")[0].value=obj.options[obj.selectedIndex].text
	//alert(document.getElementsByName("searchText")[0].value)
	if(document.getElementsByName("flag")[0].value=="false"){
		document.getElementById('listDiv').style.display="none"	
	}
	else{
		document.getElementById('listDiv').style.display="block"
	}
		
}

function callOnBlurForCombo(){
	document.getElementById('listDiv').style.display="none"
	document.getElementsByName("list")[0].selected=null;
}

function gotFocus(){
	document.getElementById('listDiv').style.display="block"
	document.getElementsByName("list")[0].focus()
}


