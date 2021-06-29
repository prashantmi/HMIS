
function callMe(form1)
{
	
	var cmbVal = "";
	with(form1)
	{
		if(combo[0].value == "0")
		{
			alert("Please Select Ward Name");
			return;
		}
		else
		{
			cmbVal = combo[0].options[combo[0].selectedIndex].text;
			comboValue.value = cmbVal;
			add("ADD");
		}	
	}
	
}
function deleteGlobalRecords(_param,objName){
	var fFlag = true;
	var Flag = true;
	var temp;
	var checkBox = document.getElementsByName("chk");
	for(var nTmpI=0; nTmpI < checkBox.length; nTmpI++)
	{		
		if(checkBox[nTmpI].checked && parseInt(checkBox[nTmpI].value.split("@")[2].split("|")[1].split("$")[0])!=0)
		{		
			Flag=false; //2@100@1|1$2
		}		
	}	
	if(fFlag)
	{
		if(Flag)
			deleteRecords(_param);
		else
			alert("The Selected "+objName+" having Child "+objName+" can not be Deleted.");
		
	}	
}
// function for Department Document Master View Page
function  componentView()
{
document.getElementById('message').style.display='none';

	if(checkForView()==false) return false;

	var chk_temp	=	document.forms[0].chk.length;
	var temp_cnt	=	document.forms[0].cnt_page.value;
	var temp1		=	temp_cnt.split(".");
	var cnt_page	=	temp1[0];
	var chk			=	"";

	var mode = "VIEW";	
	if(!isNaN(chk_temp))
	{
		for(var i=0;i<chk_temp;i++)
		{
			if(document.forms[0].chk[i].checked==true) 
			chk =document.forms[0].chk[i].value;
		}
	}
	else{
		chk =document.forms[0].chk.value;
	}
	
	
		//var  widthvalue=document.forms[0].view_row_length.value * 60;
	var heg_width="width=700,height=250,left=200,top=200";
	var myPopup = window.open(createFHashAjaxQuery("CNTDeptDocumentMst.cnt?hmode="+mode+"&chk="+chk,'popupWindow',heg_width))//+"&cnt_page="+cnt_page+"&masterName="+document.forms[0].masterName.value,'popupWindow',heg_width, 'scrollbars=yes,ALIGN=CENTER,left=100,top=100');

	
	
		
}

function  Wardview()
{
	document.getElementById('message').style.display='none';
	if(checkForView()==false) return false;
	var chk_temp	=	document.forms[0].chk.length;
	var temp_cnt	=	document.forms[0].cnt_page.value;
	var temp1		=	temp_cnt.split(".");
	var cnt_page	=	temp1[0];
	var chk			=	"";

	var mode = "VIEW";	
	
	if(!isNaN(chk_temp))
	{
		for(var i=0;i<chk_temp;i++)
		{
			if(document.forms[0].chk[i].checked==true) chk =document.forms[0].chk[i].value;
		}
	}
	else
		chk =document.forms[0].chk.value;
	
	//var  widthvalue=document.forms[0].view_row_length.value * 60;
	//var heg_width="width=700,height="+widthvalue;
	//var heg_width="width=700,height=350,left=200,top=200";
	//var myPopup = window.open("CNTWardMst.cnt?hmode="+mode+"&chk="+chk,'popupWindow',heg_width);
	
	
	var heg_width="width=500,height=500,left=200,top=200";

var myPopup = window.open(createFHashAjaxQuery("CNTWardMst.cnt?hmode="+mode+"&chk="+chk,'popupWindow',heg_width));//+"&cnt_page="+cnt_page+"&masterName="+document.forms[0].masterName.value,'popupWindow',heg_width, 'scrollbars=yes,ALIGN=CENETER,left=100,top=100');


	
}



// function for DUWR Bed Master View Page
function  Bedview()
{
document.getElementById('message').style.display='none';

	if(checkForView()==false) return false;

	var chk_temp	=	document.forms[0].chk.length;
	var temp_cnt	=	document.forms[0].cnt_page.value;
	var temp1		=	temp_cnt.split(".");
	var cnt_page	=	temp1[0];
	var chk			=	"";
	var mode = "VIEW";	
	if(!isNaN(chk_temp))
	{
		for(var i=0;i<chk_temp;i++)
		{
			if(document.forms[0].chk[i].checked==true) chk =document.forms[0].chk[i].value;
		}
	}
	else
		chk =document.forms[0].chk.value;
	
	//var  widthvalue=document.forms[0].view_row_length.value * 60;
	var heg_width="width=500,height=500,left=200,top=200";
	var myPopup = window.open(createFHashAjaxQuery("CNTDUWRBedMst.cnt?hmode="+mode+"&chk="+chk,'popupWindow',heg_width));//+"&cnt_page="+cnt_page+"&masterName="+document.forms[0].masterName.value,'popupWindow',heg_width, 'scrollbars=yes,ALIGN=CENETER,left=100,top=100');

	
	//var heg_width="width=700,height=350,left=200,top=200";
	//var myPopup = window.open("CNTDUWRBedMst.cnt?hmode="+mode+"&chk="+chk,'popupWindow',heg_width);//+"&cnt_page="+cnt_page+"&masterName="+document.forms[0].masterName.value,'popupWindow',heg_width, 'scrollbars=yes,ALIGN=CENETER,left=100,top=100');
}
// function for ward room bed master view page
function wardRoomBedView()
{

	//alert("inside wardRoomBedView");

	document.getElementById('message').style.display='none';

	if(checkForView()==false) return false;

	var chk_temp	=	document.forms[0].chk.length;
	var temp_cnt	=	document.forms[0].cnt_page.value;
	var temp1		=	temp_cnt.split(".");
	var cnt_page	=	temp1[0];
	var chk			=	"";

	var mode = "VIEW";	
	
	if(!isNaN(chk_temp))
	{
		for(var i=0;i<chk_temp;i++)
		{
			if(document.forms[0].chk[i].checked==true) chk =document.forms[0].chk[i].value;
		}
	}
	else{
		chk =document.forms[0].chk.value;
	}
	
	
	
	//var  widthvalue=document.forms[0].view_row_length.value * 60;
	var heg_width="width=700,height=500";
	
	var myPopup = window.open(createFHashAjaxQuery("CNTWardRoomBedMst.cnt?hmode="+mode+"&chk="+chk));//+"&cnt_page="+cnt_page+"&masterName="+document.forms[0].masterName.value,'popupWindow',heg_width, 'scrollbars=yes,ALIGN=CENETER,left=100,top=100');

}


/*function  hServiceview()
{
document.getElementById('message').style.display='none';

	if(checkForView()==false) return false;

	var chk_temp	=	document.forms[0].chk.length;
	var temp_cnt	=	document.forms[0].cnt_page.value;
	var temp1		=	temp_cnt.split(".");
	var cnt_page	=	temp1[0];
	var chk			=	"";

	var mode = "VIEW";	
	
	if(!isNaN(chk_temp))
	{
		for(var i=0;i<chk_temp;i++)
		{
			if(document.forms[0].chk[i].checked==true) chk =document.forms[0].chk[i].value;
		}
	}
	else
		chk =document.forms[0].chk.value;
	
	var  widthvalue=document.forms[0].view_row_length.value * 60;
	var heg_width="width=850,height="+widthvalue;
	var myPopup = window.open("../../ipd/masters/view_hserviceMst_ipd.jsp?mode="+mode+"&chk="+chk+"&cnt_page="+cnt_page+"&masterName="+document.forms[0].masterName.value,'popupWindow',heg_width, 'scrollbars=yes,ALIGN=CENETER,left=100,top=100');

}*/
function  Valueview()
{
document.getElementById('message').style.display='none';

	if(checkForView()==false) return false;

	var chk_temp	=	document.forms[0].chk.length;
	var temp_cnt	=	document.forms[0].cnt_page.value;
	var temp1		=	temp_cnt.split(".");
	var cnt_page	=	temp1[0];
	var chk			=	"";

	var mode = "VIEW";	
	
	if(!isNaN(chk_temp))
	{
		for(var i=0;i<chk_temp;i++)
		{
			if(document.forms[0].chk[i].checked==true) chk =document.forms[0].chk[i].value;
		}
	}
	else
		chk =document.forms[0].chk.value;
	
	var  widthvalue=document.forms[0].view_row_length.value * 60;
	var heg_width="width=700,height="+widthvalue;
	
	var myPopup = window.open(createFHashAjaxQuery("CNTCompExamValueMst.cnt?hmode="+mode+"&chk="+chk));//+"&cnt_page="+cnt_page+"&masterName="+document.forms[0].masterName.value,'popupWindow',heg_width, 'scrollbars=yes,ALIGN=CENETER,left=100,top=100');
	
}
function ageValidation(obj,index)
{
//alert("inside agevalidation");
//alert("index"+index);
val = obj.value;
//alert("val"+val);

var fage = document.getElementById("strFromAge" + index);
var tage = document.getElementById("strToAge" + index);



frageval = fage.value;
toageval = tage.value

if(val=="1")
{
if(!(frageval>=1&&frageval<=30)||!(toageval>=1&&toageval<=30))
{
alert("days should be between 1 and 30");
obj.selectedIndex=0;
}
}
 if(val=="2")
{
if(!(frageval>=1&&frageval<=12)||!(toageval>=1&&toageval<=12))
{
alert("month should be between 1 and 12");
obj.selectedIndex=0;
}
}
 if(val=="3")
 {
if(!(frageval>=1&&frageval<=150)||!(toageval>=1&&toageval<=150))
{
alert("year should be between 1 and 150");
obj.selectedIndex=0;
}
}

if(parseInt(frageval,10)>parseInt(toageval,10))
{

alert("from age should be less than To Age");
obj.selectedIndex=0;
}
}
function ageValidationModification(obj,index)
{

//alert("index"+index);
var age =  obj.value;
 //alert("age=="+age); 
 
var indexval =   document.getElementById("strFunit"+index).value; 
// alert("indexval=="+indexval);
if(indexval=="1")
{

if(!(age>=1&&age<=30))
{
alert("days should be between 1 and 30");
//obj.selectedIndex=0;
}
}
 if(indexval=="2")
{

if(!(age>=1&&age<=12))
{
alert("month should be between 1 and 12");
//obj.selectedIndex=0;
}
}
 if(indexval=="3")
 {
if(!(age>=1&&age<=150))
{
alert("year should be between 1 and 150");
//obj.selectedIndex=0;
}
}

}
function reportCrite(){
	document.forms[0].hmode.value="SHOWRPT";
	document.forms[0].submit();
}