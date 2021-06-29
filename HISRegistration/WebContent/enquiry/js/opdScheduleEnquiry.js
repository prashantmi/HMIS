function submitPage(mode)
{

 document.getElementsByName('hmode')[0].value=mode
 document.forms[0].submit();
}
function getUnitDetail(mode,departmentCode,department)
{
 //alert(department)
 document.getElementsByName('departmentCode')[0].value=departmentCode
 document.getElementsByName('department')[0].value=department
 document.getElementsByName('hmode')[0].value=mode
 document.forms[0].submit();
}
function getSpecialClinicDetail(mode,departmentUnitCode,department)
{
 //alert(department)
 document.getElementsByName('departmentUnitCode')[0].value=departmentUnitCode
 document.getElementsByName('department')[0].value=department
 document.getElementsByName('hmode')[0].value=mode
 document.forms[0].submit();
}
function getConsultant(event,departmentUnitCode,departmentUnit)
{
 //alert(departmentUnit)
 document.getElementsByName('departmentUnitCode')[0].value=departmentUnitCode
 document.getElementsByName('departmentUnit')[0].value=departmentUnit
 var department=document.getElementsByName('department')[0].value
 var url="/AHIMS/enquiry/opdScheduleEnquiry.cnt?hmode=GETCONSULTANT&departmentUnit=" + departmentUnit + "&department=" + department + "&departmentUnitCode=" + departmentUnitCode;
 openPopup(url,event,250,500);
}
function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}
function showLegends(){
  document.getElementById("divLegends").style.display=""; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}
function searchInListBox(eve,obj)
{
      flag=0;
      var lobj;
      var i = 0;
      var retValue = false;
      //var value=  document.getElementsByName('searchText')[0].value
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
	if(document.getElementsByName("departmentCode")[0].value=="" && document.getElementsByName("searchText")[0].value!=""){
		submitPage('GETDEPTBYNAME');
	}
	else{
		if(document.getElementsByName("searchText")[0].value!="" ){
			if(document.getElementsByName("departmentCode")[0].value.length==3){
				getUnitDetail('GETUNITDTL',document.getElementsByName("departmentCode")[0].value,document.getElementsByName("department")[0].value);
			}
			else{
				getSpecialClinicDetail('GETSPECIALCLINICDTL',document.getElementsByName("departmentCode")[0].value, document.getElementsByName("department")[0].value);
			}	
		}
		else{
			alert("Please Enter a Valid Value");
			return false;
		}
	}		
}
function setValue(obj){
	//alert(obj)
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


