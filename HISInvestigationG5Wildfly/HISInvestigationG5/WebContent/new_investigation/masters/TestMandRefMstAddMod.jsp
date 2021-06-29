<!-- 
 /******************************************Start of program*************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	TEST MANDATORY REFERENCE MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:
 ## Purpose								:	
 ## Date of Creation					: 	20-Apr-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/***********************************************************************************************************/

-->



<%@page import="hisglobal.utility.Entry"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="new_investigation.vo.TestMandRefMasterVO"%>
<%@page import="java.util.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="new_investigation.InvestigationConfig"%>
<html>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/new_investigation/js/TestMandRefMstAddMod.js" />
<his:javascript src="/new_investigation/js/inv_popup.js"/>

<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>


<body>
	<script type="text/javascript">

	
/* functions to use specical character */

var currentElement=null;
var allowableElementArray=new Array('lowValueUom','highValueUom','symbol');

function getvalues()
{	
	var testCode=document.getElementsByName("testCode")[0].value;

	var rangeTyp=document.getElementsByName("rangeTyp")[0].value;

	
	if(rangeTyp==-1)
		{
		document.getElementsByName("parameterCode")[0].value=-1;
    alert("Please select Range");
    return null;
		}
	
	if(testCode!=-1)
	{
		submitPage('GETVALUES');
	}
	else
	{
		submitPage('ADD');
	} 
}

function onchangerange()
{
	document.getElementsByName("parameterCode")[0].value="-1";
	
}



function assignCurrentElement(obj)
{
	//HISAlert('info','','assignCurrentElement');
	if(checkAllowableElemnt(obj.name)){		
		currentElement=obj;
	}	
}

function checkAllowableElemnt(name)
{
	for(var m=0;m<allowableElementArray.length;m++)
	{
		if(name==allowableElementArray[m])
		{
			return true;
		}
	}

	return false;
}

function populate(value)
{	
	currentElement.value+=String.fromCharCode(parseInt(value));		
}

function showSpecialCharacters(name)
{
	//HISAlert('info','',currentElement);
	if(currentElement!=null)
	{
		Popup.showModal(name);		
	}
	else
	{		
		alert('Only Allowed For UOM and Symbol');
	}
	
return false;
}

function hideDivPopup(){
 Popup.hide('tableSpecialCharacters');
 currentElement=null;	
}

/* functions using special characters end */

function validateOnSave()
{
   valid=false;
   
    if( isEmpty(document.forms[0].testparaValue,"testparaValue"))
     {
         valid=true ;
     }
  return valid;
}

function getparameter() {
	//calll new mode in action GETPARAMETER

	var testCode = document.getElementsByName("testCode")[0].value;

	if (testCode != -1) {
		submitPage('GETPARAMETER');
	} else {
		submitPage('ADD');
	}
}

function getcombos()
{	
	var testCode=document.getElementsByName("testCode")[0].value;
	
	if(testCode!=-1)
	{
		submitPage('GETCOMBO');
	}
	else
	{
		submitPage('ADD');
	} 
}




/* ***********************ajax cal starts****************************** */

  function getLabSample(ojb)
{
	
	
		  --ojb; 
	  
	//alert(ojb);
	//alert(document.getElementsByName("labCode")[ojb].value);
	//alert(document.getElementsByName("testCode")[0].value);
	//alert("samplediv"+ojb);
	
	
	
	var flg = false;
	var objLabSampleList = null;
	var _mode = "AJX_G_LABSAMPLE";
	var labCode =document.getElementsByName("labCode")[ojb].value;
	var testCode=document.getElementsByName("testCode")[0].value;
	var urlNew= "/HISInvestigationG5/new_investigation/masters/TestMandRefMstACTION.cnt?hmode="+_mode+'&labCode='+labCode+'&testCode='+testCode;	
	var objXHR = {url: urlNew, sync:true, postData: "", handleAs: "json",
			load: function(data) 
			{
				objLabSampleList = data;
				flg = true;
			},
	        error: function(error)
	        {
	           alert(error+"Error while populating Information");
	            objLabSampleList = null;
	            flg = false;
	        }};
	
	
	var objDojoAjax = dojo.xhrPost(objXHR);	
	setLabSample(objLabSampleList,ojb);
	
	
	/* var divKitchenLabel = document.getElementById("samplediv"+ojb);
	divKitchenLabel.style.display = "none"; */
	
	/* function getTest()
	{
			var flg = false;
			var objtestList = null;
			var _mode = "AJX_G_TEST";
			var labCode =document.getElementsByName("labCode")[0].value;
			var bookmarkCode =document.getElementsByName("bookmarkCode")[0].value;
			var urlNew= "/HISInvestigationG5/new_investigation/masters/UserBookMarkMstACT.cnt?hmode="+_mode+'&labCode='+labCode+'&bookMarkCode='+bookmarkCode;;	
			alert(urlNew);	
			var objXHR = {url: urlNew, sync:true, postData: "", handleAs: "json",
				load: function(data) 
				{
					objtestList = data;
					flg = true;
				},
		        error: function(error)
		        {
		            //if(typeof objMenuMealTimeList == 'undefined' || objMenuMealTimeList==null || objMenuMealTimeList=="" || objMenuMealTimeList.length==0)
		            	//alert("No  Type");
		            alert(error+"Error while populating Information");
		            objtestList = null;
		            flg = false;
		        }};
		
			var objDojoAjax = dojo.xhrPost(objXHR);	
			//alert("objtestList::"+objtestList[0].value);	
			return objtestList;
	} */
	
	/*  var labCode=document.getElementsByName("labCode")[0].value;
	
	if(labCode!=-1)
	{
			
		submitPage('GETCOMBO');
	}
	else
	{
		submitPage('ADD');
	}  */
	
 
}
  
 
  function setLabSample(objLabSampleList,ojb)
  {
  	//alert("room No :"+obj.roomId+"  room Name :"+obj.roomName);
  	//var objtestList = obj.value;
  	if(objLabSampleList !=null)
  	{
  		var labSampleList = document.getElementById("samplediv"+ojb);
  		 labSampleList.innerHTML="";

  		/* labSampleList.innerHTML="<html:select name='TestMandRefMstFB' property='sampleCode'>"; */
  		
  		
  		
  		/* var myDiv = document.getElementById("myDiv");

//Create array of options to be added
var array = ["Volvo","Saab","Mercades","Audi"];

//Create and append select list
var selectList = document.createElement("select");
selectList.id = "mySelect";
myDiv.appendChild(selectList);

//Create and append the options
for (var i = 0; i < array.length; i++) {
    var option = document.createElement("option");
    option.value = array[i];
    option.text = array[i];
    selectList.appendChild(option);
} */



 var selectList= document.createElement("select");
 selectList.name="sampleCode";
 selectList.style='width:200px;';
 
labSampleList.appendChild(selectList);
  		
  		/* labSampleList.innerHTML=""; */
  		
		for(var i=0;i<objLabSampleList.length;i++)
  			{
  				//alert(objLabSampleList.length);
  				opt = document.createElement("option");
  				 				 				
  				opt.value = objLabSampleList[i].sampleCode;
  				/* opt.innerHTML = objLabSampleList[i].sampleName; */
  				opt.text = objLabSampleList[i].sampleName;
  				/* labSampleList.appendChild(opt); */
  				selectList.appendChild(opt);
  			}
		/* labSampleList.innerHTML+="</html:select>"; */
  	}
  }

 /* ********************** ajax call ends********************** */
 
 
 
function clearaddForm()
{
	document.getElementsByName('testCode')[0].value="-1";
	document.getElementsByName('parameterCode')[0].value="-1";

	
	submitPage('ADD');

}


function AddRowToTable()
{
	var rangeTyp=document.getElementsByName('rangeTyp')[0].value;
//	alert("rangeTyp:"+rangeTyp);
	var nRow=0;
	var rowValue0;
	var divValue=0;
	
	var tableObj=document.getElementById('refValueTable');
	var numRows=tableObj.rows.length;
	//alert("numRows"+numRows);
	
//	if(numRows>2)
	//{
	//	nRow=tableObj.rows[numRows-1].id;
	//	alert("Inside if numrows greater 1"+nRow);
	//	rowValue=--numRows;
	//	divValue=--rowValue;
	//}
	//else
//	{
		nRow=numRows;
		rowValue=nRow;
		divValue=nRow-1;
	//}
	
//alert("nRow"+nRow);
	
	var tabRow=tableObj.insertRow(numRows);
	//alert("tabrows"+tabRow);
	
	var htmlAddString="";
	tabRow.id=parseInt(nRow)+1;
	//alert("row id"+tabRow.id);
	//alert("html before"+tabRow.innerHTML);
	/* added by chandan for new range */
	if(rangeTyp==1)
		var htmlCommongString="<td class='tdfont'><div align='center'><input type='text'  name ='lowValue' maxlength='50'  size='6'></div></td><td class='tdfont'><div align='center'><input type='text'  name ='lowValueUom' size='16'  maxlength='20' onfocus='assignCurrentElement(this)'></div></td><td class='tdfont'><div align='center'><input type='text'  name ='highValue' maxlength='50'   size='6'></div></td><td class='tdfont'><div align='center'><input type='text'  name ='highValueUom' size='16' maxlength='20' onfocus='assignCurrentElement(this)'  ></div></td><td class='tdfont'><div align='center'><input type='text'  name ='symbol' size='6' maxlength='50' onfocus='assignCurrentElement(this)' ></div></td><td class='tdfont'><div align='center'><input type='text'  name ='remarks' size='6' maxlength='200' onkeypress='return CheckMaxLength(event,this,50,1)'  ></div></td><td class='tdfont'><img src='/HISInvestigationG5/hisglobal/images/minus.gif' onclick=\"deleteRow(document.getElementById(\'"+(parseInt(nRow)+1)+"\'))\"></td>";
	else
	var htmlCommongString="<td class='tdfont'><div align='center'><input type='text'  name ='range' maxlength='50' onkeypress='return validateAlphaNumericOnly(event,this)' size='6'></div></td><td class='tdfont'><div align='center'><input type='text'  name ='rangeUom' size='16'  maxlength='20' onfocus='assignCurrentElement(this)'></div></td><td class='tdfont'><div align='center'><input type='text'  name ='symbol' size='6' maxlength='50' onfocus='assignCurrentElement(this)' ></div></td><td class='tdfont'><div align='center'><input type='text'  name ='remarks' size='6' maxlength='200' onkeypress='return CheckMaxLength(event,this,50,1)'  ></div></td><td class='tdfont'><img src='/HISInvestigationG5/hisglobal/images/minus.gif' onclick=\"deleteRow(document.getElementById(\'"+(parseInt(nRow)+1)+"\'))\"></td>";
	
	
	
		if(document.getElementsByName('criteriaCode')[0].value==10)
		  {
			 if(tabRow.id>1)
		    {
		     document.getElementById("addImg").style.display="none";
		      
		    }
		  }
		if(document.getElementsByName('criteriaCode')[0].value==12)
		  {
			htmlAddString ="<td class='tdfont'><div align='center'><select name='gender'  ><option value = '-1'>Select Value</option><option value = '0'>Male</option><option value = '1'>Female</option><option value = '2'>other</option></select></div></td>";
		  }
		 else if(document.getElementsByName('criteriaCode')[0].value==11) 
		 {
		      htmlAddString ="<td class='tdfont'><div align='center'><input type='text'  name ='lowAge' maxlength='3' onkeypress='return validateNumeric(event)' size='6'></div></td><td class='tdfont'><div align='center'><select name ='lowAgeUom'  ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></td><td class='tdfont'><div align='center'><input type='text'  name ='highAge' size='6' maxlength='3' onkeypress='return validateNumeric(event)'   ></div></td><td class='tdfont'><div align='center'><select name ='highAgeUom'  ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></div></td> ";
		 }
		  else if(document.getElementsByName('criteriaCode')[0].value==13)
		 {
		      htmlAddString ="<td class='tdfontl'><div align='center'><select name='gender'  ><option value = '-1'>Select Value</option><option value = '0'>Male</option><option value = '1'>Female</option><option value = '2'>other</option></select></div></td><td class='tdfont'><div align='center'><input type='text'  name ='lowAge' size='6' maxlength='6' onkeypress='return validateNumeric(event)'  ></div></td><td class='tdfont'><div align='center'><select name ='lowAgeUom'  ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></td><td class='tdfont'><div align='center'><input type='text'  name ='highAge' size='6' maxlength='6' onkeypress='return validateNumeric(event)'  ></div></td><td class='tdfont'><div align='center'><select name ='highAgeUom'  ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></div></td> ";
		 }
	 	else if(document.getElementsByName('criteriaCode')[0].value==14 || document.getElementsByName('criteriaCode')[0].value=="14" )
		 {
		    
		   	 		
		htmlAddString="<td class='tdfont'>"+ getMandatoryList()+"</td>";
	
		                    
		 }
		 else if(document.getElementsByName('criteriaCode')[0].value==15 )
		 {
		    
	   	 		
				htmlAddString="<td class='tdfont'>"+ getMandatoryList()+"</td>";
			
		    htmlAddString +="<td class='tdfont'><div align='center'><select name='gender'  ><option value = '-1'>Select Value</option><option value = '0'>Male</option><option value = '1'>Female</option><option value = '2'>other</option></select></div></td>";
		    //<td class='tdfont'><div align='center'><input type='text'  name ='lowAge' maxlength='6' onkeypress='return validateNumeric(event)' size='6'  ></div></td><td class='tdfont'><div align='center'><select name ='lowAgeUom'  ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></td><td class='tdfont'><div align='center'><input type='text'  name ='highAge' maxlength='6' onkeypress='return validateNumeric(event)' size='6'  ></div></td><td class='tdfont'><div align='center'><select name ='highAgeUom'  ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></div></td> ";
		 }
		  else if(document.getElementsByName('criteriaCode')[0].value==16 )
		 {
		    
	   	 		
				htmlAddString="<td class='tdfont'>"+ getMandatoryList()+"</td>";
			
		     htmlAddString +="<td class='tdfont'><div align='center'><input type='text'  name ='lowAge' size='6' maxlength='6' onkeypress='return validateNumeric(event)'  ></div></td><td class='tdfont'><div align='center'><select name ='lowAgeUom'  ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></td><td class='tdfont'><div align='center'><input type='text'  name ='highAge' maxlength='6' onkeypress='return validateNumeric(event)' size='6'  ></div></td><td class='tdfont'><div align='center'><select name ='highAgeUom' ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></div></td> ";                
		 }
		  else if(document.getElementsByName('criteriaCode')[0].value==17 )
		 {
		    
	   	 		
				htmlAddString="<td class='tdfont'>"+getMandatoryList()+"</td>";
			    htmlAddString +="<td class='tdfont'><div align='center'><select name='gender'  ><option value = '-1'>Select Value</option><option value = '0'>Male</option><option value = '1'>Female</option><option value = '2'>other</option></select></div></td><td class='tdfont'><div align='center'><input type='text'  name ='lowAge' maxlength='6' onkeypress='return validateNumeric(event)' size='6'    ></div></td><td class='tdfont'><div align='center'><select name ='lowAgeUom'  ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></td><td class='tdfont'><div align='center'><input type='text'  name ='highAge' size='6' maxlength='6' onkeypress='return validateNumeric(event)'  ></div></td><td class='tdfont'><div align='center'><select name ='highAgeUom'  ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></div></td> ";
		 	//   htmlAddString +="<td class='tdfont'><div align='center'><input type='text'  name ='lowAge' maxlength='6' onkeypress='return validateNumeric(event)' size='6'  ></div></td><td class='tdfont'><div align='center'><select name ='lowAgeUom'  ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></td><td class='tdfont'><div align='center'><input type='text'  name ='highAge' maxlength='6' onkeypress='return validateNumeric(event)' size='6'  ></div></td><td class='tdfont'><div align='center'><select name ='highAgeUom'  ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></div></td> ";                
		 }
	 	else if(document.getElementsByName('criteriaCode')[0].value==18 )
		 {
		    
		    htmlAddString ="<td class='tdfont'>"+ getSampleList()+"</td> "  ;                
		 }
		 else if(document.getElementsByName('criteriaCode')[0].value==19 )
		 {
		    
		    htmlAddString ="<td class='tdfont'>"+ getSampleList()+"</td> "  ; 
		    htmlAddString +="<td class='tdfont'><div align='center'><input type='text'  name ='lowAge' maxlength='6' onkeypress='return validateNumeric(event)' size='6'  ></div></td><td class='tdfont'><div align='center'><select name ='lowAgeUom'   ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></td><td class='tdfont'><div align='center'><input type='text'  name ='highAge' size='6' maxlength='6' onkeypress='return validateNumeric(event)'   ></div></td><td class='tdfont'><div align='center'><select name ='highAgeUom'    ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></div></td> ";               
		 }
		 else if(document.getElementsByName('criteriaCode')[0].value==20 )
		 {
		    
		    htmlAddString ="<td class='tdfont'>"+ getSampleList()+"</td> "  ; 
		    htmlAddString +="<td class='tdfont'><div align='center'><select name='gender'    ><option value = '-1'>Select Value</option><option value = '0'>Male</option><option value = '1'>Female</option><option value = '2'>other</option></select></div></td>";               
		 }
		  else if(document.getElementsByName('criteriaCode')[0].value==21 )
		 {
		    
		    htmlAddString ="<td class='tdfont'>"+ getSampleList()+"</td> "  ; 
		    htmlAddString +="<td class='tdfont'><div align='center'><select name='gender'    ><option value = '-1'>Select Value</option><option value = '0'>Male</option><option value = '1'>Female</option><option value = '2'>other</option></select></div></td><td class='tdfont'><div align='center'><input type='text'  name ='lowAge' size='6'  maxlength='6' onkeypress='return validateNumeric(event)'   ></div></td><td class='tdfont'><div align='center'><select name ='lowAgeUom'><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></td><td class='tdfont'><div align='center'><input type='text'  name ='highAge' maxlength='6' onkeypress='return validateNumeric(event)' size='6'   ></div></td><td class='tdfont'><div align='center'><select name ='highAgeUom'   ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></div></td> ";
		   // htmlAddString +="<td class='tdfont'><div align='center'><input type='text'  name ='lowAge' size='6'  maxlength='6' onkeypress='return validateNumeric(event)'   ></div></td><td class='tdfont'><div align='center'><select name ='lowAgeUom'    ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></td><td class='tdfont'><div align='center'><input type='text'  name ='highAge' size='6' maxlength='6' onkeypress='return validateNumeric(event)'   ></div></td><td class='tdfont'><div align='center'><select name ='highAgeUom'    ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></div></td> ";              
		 }
		  else if(document.getElementsByName('criteriaCode')[0].value==22 )
		 {
		    
		    htmlAddString ="<td class='tdfont'>"+ getTestLaboratoryListSimple()+"</td> "  ;                
		 }
		  else if(document.getElementsByName('criteriaCode')[0].value==23 )
			 {
			    
			    htmlAddString ="<td class='tdfont'>"+ getTestLaboratoryList(rowValue)+"</td><td class='tdfont'><div id='samplediv"+divValue+"'>"+ getSampleList()+"</div></td>"  ;                
			 }
		  else if(document.getElementsByName('criteriaCode')[0].value==24 )
			 {
			    
			    htmlAddString ="<td class='tdfont'>"+ getTestLaboratoryList(rowValue)+"</td> <td class='tdfont'><div id='samplediv"+divValue+"'>"+ getSampleList()+"</div></td>"  ;
			    htmlAddString +="<td class='tdfont'><div align='center'><input type='text'  name ='lowAge' size='6' maxlength='6' onkeypress='return validateNumeric(event)'  ></div></td><td class='tdfont'><div align='center'><select name ='lowAgeUom'   ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></td><td class='tdfont'><div align='center'><input type='text'  name ='highAge' size='6' maxlength='6' onkeypress='return validateNumeric(event)'   ></div></td><td class='tdfont'><div align='center'><select name ='highAgeUom'    ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></div></td> ";                    
			 }
		  else if(document.getElementsByName('criteriaCode')[0].value==25 )
			 {
			    
			    htmlAddString ="<td class='tdfont'>"+ getTestLaboratoryList(rowValue)+"</td><td class='tdfont'><div id='samplediv"+divValue+"'>"+ getSampleList()+"</div></td> "  ;
			    htmlAddString +="<td class='tdfont'><div align='center'><select name='gender'    ><option value = '-1'>Select Value</option><option value = '0'>Male</option><option value = '1'>Female</option><option value = '2'>other</option></select></div></td>";
			    //<td class='tdfont'><div align='center'><input type='text'  name ='lowAge' size='6'  maxlength='6' onkeypress='return validateNumeric(event)'   ></div></td><td class='tdfont'><div align='center'><select name ='lowAgeUom'><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></td><td class='tdfont'><div align='center'><input type='text'  name ='highAge' maxlength='6' onkeypress='return validateNumeric(event)' size='6'   ></div></td><td class='tdfont'><div align='center'><select name ='highAgeUom'   ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></div></td> ";                    
			 }
		  else if(document.getElementsByName('criteriaCode')[0].value==26 )
			 {
			    
			    htmlAddString ="<td class='tdfont'>"+ getTestLaboratoryList(rowValue)+"</td><td class='tdfont'><div id='samplediv"+divValue+"'>"+ getSampleList()+"</div></td> "  ;
	   	 		
				htmlAddString+="<td class='tdfont'>"+ getMandatoryList()+"</td>";
			/* <td class='tdfont'>"+ getMandatoryValueList()+"</td>  "  ; */                    
			 }
		  else if(document.getElementsByName('criteriaCode')[0].value==27 )
			 {
			    
			    htmlAddString ="<td class='tdfont'>"+ getTestLaboratoryList(rowValue)+"</td> <td class='tdfont'><div id='samplediv"+divValue+"'>"+ getSampleList()+"</div></td> "  ;
			    htmlAddString +="<td class='tdfont'><div align='center'><select name='gender'    ><option value = '-1'>Select Value</option><option value = '0'>Male</option><option value = '1'>Female</option><option value = '2'>other</option></select></div></td>";
			    htmlAddString +="<td class='tdfont'><div align='center'><input type='text'  name ='lowAge' size='6' maxlength='6' onkeypress='return validateNumeric(event)'  ></div></td><td class='tdfont'><div align='center'><select name ='lowAgeUom'   ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></td><td class='tdfont'><div align='center'><input type='text'  name ='highAge' maxlength='6' onkeypress='return validateNumeric(event)' size='6'    ></div></td><td class='tdfont'><div align='center'><select name ='highAgeUom'    ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></div></td> ";                    
			 }
		  else if(document.getElementsByName('criteriaCode')[0].value==28 )
			 {
			    
			    htmlAddString ="<td class='tdfont'>"+ getTestLaboratoryListSimple()+"</td> "  ;
			    htmlAddString +="<td class='tdfont'><div align='center'><select name='gender'    ><option value = '-1'>Select Value</option><option value = '0'>Male</option><option value = '1'>Female</option><option value = '2'>other</option></select></div></td> ";
			                        
			 }
		  else if(document.getElementsByName('criteriaCode')[0].value==29 )
			 {
			    
			    htmlAddString ="<td class='tdfont'>"+ getTestLaboratoryListSimple()+"</td> "  ;
			    htmlAddString +="<td class='tdfont'><div align='center'><select name='gender'    ><option value = '-1'>Select Value</option><option value = '0'>Male</option><option value = '1'>Female</option><option value = '2'>other</option></select></div></td>";
			    htmlAddString +="<td class='tdfont'><div align='center'><input type='text'  name ='lowAge' maxlength='6' onkeypress='return validateNumeric(event)' size='6'  ></div></td><td class='tdfont'><div align='center'><select name ='lowAgeUom'   ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></td><td class='tdfont'><div align='center'><input type='text'  name ='highAge' maxlength='6' onkeypress='return validateNumeric(event)' size='6'    ></div></td><td class='tdfont'><div align='center'><select name ='highAgeUom'    ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></div></td> ";                    
			 }
		  else if(document.getElementsByName('criteriaCode')[0].value==30 )
			 {
			    
			    htmlAddString ="<td class='tdfont'>"+ getTestLaboratoryListSimple()+"</td> "  ;
			    htmlAddString +="<td class='tdfont'><div align='center'><input type='text'  name ='lowAge' size='6' maxlength='6' onkeypress='return validateNumeric(event)'  ></div></td><td class='tdfont'><div align='center'><select name ='lowAgeUom'   ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></td><td class='tdfont'><div align='center'><input type='text'  name ='highAge' maxlength='6' onkeypress='return validateNumeric(event)' size='6'    ></div></td><td class='tdfont'><div align='center'><select name ='highAgeUom'    ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></div></td> ";                    
			 }
		  else if(document.getElementsByName('criteriaCode')[0].value==31)
			 {
			    
			    htmlAddString ="<td class='tdfont'>"+ getTestLaboratoryList(rowValue)+"</td> <td class='tdfont'><div id='samplediv"+divValue+"'>"+ getSampleList()+"</div></td> "  ;
	   	 		
				htmlAddString+="<td class='tdfont'>"+ getMandatoryList()+"</td>";
			
			    htmlAddString +="<td class='tdfont'><div align='center'><input type='text'  name ='lowAge' size='6' maxlength='6' onkeypress='return validateNumeric(event)'  ></div></td><td class='tdfont'><div align='center'><select name ='lowAgeUom'   ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></td><td class='tdfont'><div align='center'><input type='text'  name ='highAge' maxlength='6' onkeypress='return validateNumeric(event)' size='6'    ></div></td><td class='tdfont'><div align='center'><select name ='highAgeUom'    ><option value = '-1'>Select Value</option><option value = '1'>Year</option><option value = '2'>Month</option><option value = '0'>Days</option></select></div></td> ";                    
			 }
		 else
		 {
		 }
	
	//HISAlert('info','',"tabRow.id="+tabRow.id)
	
	tabRow.innerHTML+= htmlAddString + htmlCommongString ;
	//alert("html after"+tabRow.innerHTML);
	document.forms[0].numberOfRow.value=numRows;
}


function getMandatoryList()
{
	var mandatoryCombo = "<div align='center'><select name = 'mandCode'  style='width:200px;'><option value='-1'>Select Value</option>";
	<%java.util.List<Entry>  lstMand= (java.util.List<Entry>)session.getAttribute(InvestigationConfig.LIST_MAND_COMBO);

	if(lstMand!=null)
	{
	for(int i=0;i<lstMand.size();i++)
	{
		%>
		mandatoryCombo+="<option value = '<%=lstMand.get(i).getValue()%>'><%=lstMand.get(i).getLabel()%></option>"
		<%
	}
	}
	%>
      mandatoryCombo+="</select></div>";
      return mandatoryCombo;
}


function getTestLaboratoryList(ojb)
{
/* 	var testLaboratoryCombo = "<div align='center'><select name = 'labCode' style='width:200px;' onchange='getLabSample("+ojb+")'><option value='-1'>Select Value</option>";
 */	
	var testLaboratoryCombo = "<div align='center'><select name = 'labCode' style='width:200px;' ><option value='-1'>Select Value</option>";

	<%java.util.List<Entry>  lstLab= (java.util.List<Entry>)session.getAttribute(InvestigationConfig.LIST_LAB_COMBO);

	
	
	if(lstLab!=null)
	{
	for(int i=0;i<lstLab.size();i++)
	{
		%>
		testLaboratoryCombo+="<option value = '<%=lstLab.get(i).getValue()%>'><%=lstLab.get(i).getLabel()%></option>"
		<%
	}
	}
	%>
      testLaboratoryCombo+="</select></div>";
      return testLaboratoryCombo;
}

function getTestLaboratoryListSimple(ojb)
{
	var testLaboratoryCombo = "<div align='center'><select name = 'labCode' style='width:200px;' ><option value='-1'>Select Value</option>";
	<%java.util.List<Entry>  lstLabSimple= (java.util.List<Entry>)session.getAttribute(InvestigationConfig.LIST_LAB_COMBO);

	
	
	if(lstLabSimple!=null)
	{
	for(int i=0;i<lstLabSimple.size();i++)
	{
		%>
		testLaboratoryCombo+="<option value = '<%=lstLabSimple.get(i).getValue()%>'><%=lstLabSimple.get(i).getLabel()%></option>"
		<%
	}
	}
	%>
      testLaboratoryCombo+="</select></div>";
      return testLaboratoryCombo;
}

function getSampleList()
{
	var sampleCombo = "<div align='center'><select name = 'sampleCode' style='width:200px;'><option value='-1'>Select Value</option>";
	<%java.util.List<Entry>  lstSample= (java.util.List<Entry>)session.getAttribute(InvestigationConfig.LIST_SAMPLE_COMBO);
	if(lstSample!=null)
	{
	for(int i=0;i<lstSample.size();i++)
	{
		%>
		sampleCombo+="<option value = '<%=lstSample.get(i).getValue()%>'><%=lstSample.get(i).getLabel()%></option>"
		<%
	}
	}
	%>
	sampleCombo+="</select></div>";
      return sampleCombo;
}


  
</script>

	<html:form action="/masters/TestMandRefMstACTION">
		<html:hidden name="TestMandRefMstFB" property="hmode" />
		<html:hidden name="TestMandRefMstFB" property="selectedChk" />
		<html:hidden name="TestMandRefMstFB" property="criteriaCode" />
		




		<%!boolean readOnly;%>
		<%
			this.readOnly = false;
		%>



		<his:TransactionContainer>
			<his:TitleTag name="Test Mandatory Reference Master">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag>
			<his:ContentTag>

				<!-- add page logic -->

				<logic:equal name="TestMandRefMstFB" property="hmode" value="ADD">




					<table width="100%" border="0" cellspacing="1" cellpadding="0">

						<%-------------------- test name combo values -----------------------%>

						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="testCombo" />&nbsp;</b>
									</font>
								</div>
							</td>



							<td width="50%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.LIST_TEST_COMBO%>">
									<div align="left">

										<html:select name="TestMandRefMstFB" property="testCode"
											tabindex="1" onchange="getparameter()" style="width:150px;">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_TEST_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>


						</tr>
						
						<!-- /* added by chandan for new range */ -->
							<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b>
										 Range Type &nbsp;</b>
									</font>
								</div>
							</td>



									
							<td width="50%" class="tdfont">
									<div align="left">

										<html:select name="TestMandRefMstFB" property="rangeTyp"
											tabindex="1"  style="width:150px;" onchange="onchangerange()">
											<html:option value="-1">select value</html:option>
											<html:option value="1">From-To</html:option>
											<html:option value="2">></html:option>
											<html:option value="3">>=</html:option>
											<html:option value="4"><</html:option>
											<html:option value="5"><=</html:option>
										</html:select>
									</div>
								</td>
							


						</tr>

						<%-------------------- TestPara combo values -----------------------%>

						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="TestParaCombo" />&nbsp;</b>
									</font>
								</div>
							</td>



							<td width="50%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.LIST_TESTPARA_COMBO%>">
									<div align="left">

										<html:select name="TestMandRefMstFB" property="parameterCode"
											tabindex="1" onchange="getvalues()" style="width:150px;">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_TESTPARA_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>


						</tr>
						
						
						
						 <!-- //added by chandan
            -->

	



					</table>

				</logic:equal>
				
					<!-- MODIFY CASE -->

				<logic:equal name="TestMandRefMstFB" property="hmode" value="MODIFY">





					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="testCombo" />&nbsp;</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.LIST_TEST_COMBO%>">
									<div align="left">

										<html:select name="TestMandRefMstFB" property="testCode"
											tabindex="1" disabled="true" style="width:150px;">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_TEST_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
										<html:hidden name="TestMandRefMstFB" property="testCode" />
									</div>
								</logic:present></td>
						</tr>
						
						
						
						<!-- /* added by chandan for new range */ -->
							<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b>
										 Range Type &nbsp;</b>
									</font>
								</div>
							</td>



						<logic:equal name="TestMandRefMstFB" property="rangeTyp" value="1">			
									
							<td width="50%" class="tdfont">
									<div align="left">

										<html:select name="TestMandRefMstFB" property="rangeTyp"
											tabindex="1"  style="width:150px;" disabled="true" >
											<html:option value="-1">select value</html:option>
											<html:option value="1">From-To</html:option>
											<html:option value="2">></html:option>
											<html:option value="3">>=</html:option>
											<html:option value="4"><</html:option>
											<html:option value="5"><=</html:option>
										</html:select>
										<html:hidden name="TestMandRefMstFB" property="rangeTyp" />
									</div>
								</td>
							
                         </logic:equal>
                         
                         <logic:notEqual name="TestMandRefMstFB" property="rangeTyp" value="1">
                          <td width="50%" class="tdfont">
									<div align="left">

										<html:select name="TestMandRefMstFB" property="rangeTyp"
											tabindex="1"  style="width:150px;" >
											
											<html:option value="2">></html:option>
											<html:option value="3">>=</html:option>
											<html:option value="4"><</html:option>
											<html:option value="5"><=</html:option>
										</html:select>
										<html:hidden name="TestMandRefMstFB" property="rangeTyp" />
									</div>
								</td>
                          </logic:notEqual>
						</tr>

						<%-------------------- TestPara combo values -----------------------%>

						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="TestParaCombo" />&nbsp;</b>
									</font>
								</div>
							</td>



							<td width="50%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.LIST_TESTPARA_COMBO %>">
									<div align="left">

										<html:select name="TestMandRefMstFB" property="parameterCode"
											tabindex="1" disabled="true" style="width:150px;">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_TESTPARA_COMBO %>"
												property="value" labelProperty="label" />
										</html:select>
										<html:hidden name="TestMandRefMstFB" property="parameterCode" />
									</div>
								</logic:present></td>


						</tr>




					</table>



				</logic:equal>



				<%---- END MODIFY CASE--%>
				


				<!-- table for reference value created -->

				<logic:present name="TestMandRefMstFB" property="parameterCode">

					<table id="refValueTable" width="100%">


						<!-- row 1- values to be added according to criteria -->





						<tr>


							<!-- GENDER 12 -->

							<logic:equal name="TestMandRefMstFB" property="criteriaCode"
								value="12">
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Gender"/></b>
									</div>
							</logic:equal>


							<!-- AGE 11-->


							<logic:equal name="TestMandRefMstFB" property="criteriaCode"
								value="11">
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="lowerAge"/></b>
									</div>
								</td>

								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="highAge"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
							</logic:equal>



							<!-- GENDER AGE-->

							<logic:equal name="TestMandRefMstFB" property="criteriaCode"
								value="13">
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Gender"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="lowerAge"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="highAge"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
							</logic:equal>

							<!-- MANDATORY FOR 14 TO 17 -->


							<logic:greaterEqual name="TestMandRefMstFB"
								property="criteriaCode" value="14">


								<logic:lessEqual name="TestMandRefMstFB" property="criteriaCode"
									value="17">

									<td class='tdfonthead' width="10%">
										<div align="center">
											<b><bean:message key="Mandatory"/> </b>
										</div>
									</td>
									
								</logic:lessEqual>
								
								
											
								<logic:equal name="TestMandRefMstFB" property="criteriaCode"
									value="15">
									<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Gender"/></b>
									</div>
								</td>
									
									</logic:equal>
									
									
									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
									value="16">
									
									<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="lowerAge"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="highAge"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
									</logic:equal>
									
									
								<logic:equal name="TestMandRefMstFB" property="criteriaCode"
									value="17">
									<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Gender"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="lowerAge"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="highAge"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
									
									</logic:equal>	
									
									

								<!-- SAMPLE -->


								<logic:equal name="TestMandRefMstFB" property="criteriaCode"
									value="18">

									<td class='tdfonthead' width="10%">
										<div align="center">
											<font color="red" size="2">*</font><b><bean:message key="Sample"/></b>
										</div>
									</td>
								</logic:equal>


								<!-- SAMPLE AGE -->

								<logic:equal name="TestMandRefMstFB" property="criteriaCode"
									value="19">
									<td class='tdfonthead' width="10%">
										<div align="center">
											<font color="red" size="2">*</font><b><bean:message key="Sample"/></b>
										</div>
									</td>
									<td class='tdfonthead' width="10%">
										<div align="center">
											<font color="red" size="2">*</font><b><bean:message key="lowerAge"/></b>
										</div>
									</td>
									<td class='tdfonthead' width="10%">
										<div align="center">
											<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
										</div>
									</td>
									<td class='tdfonthead' width="10%">
										<div align="center">
											<font color="red" size="2">*</font><b><bean:message key="highAge"/></b>
										</div>
									</td>
									<td class='tdfonthead' width="10%">
										<div align="center">
											<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
										</div>
									</td>
								</logic:equal>


								<!-- SAMPLE GENDER-->


								<logic:equal name="TestMandRefMstFB" property="criteriaCode"
									value="20">
									<td class='tdfonthead' width="10%">
										<div align="center">
											<font color="red" size="2">*</font><b><bean:message key="Sample"/></b>
										</div>
									</td>
									<td class='tdfonthead' width="10%">
										<div align="center">
											<font color="red" size="2">*</font><b><bean:message key="Gender"/></b>
										</div>
									</td>
								</logic:equal>



								<!-- SAMPLE AGE GENDER -->

								<logic:equal name="TestMandRefMstFB" property="criteriaCode"
									value="21">
									<td class='tdfonthead' width="10%">
										<div align="center">
											<font color="red" size="2">*</font><b><bean:message key="Sample"/></b>
										</div>
									</td>
									<td class='tdfonthead' width="10%">
										<div align="center">
											<font color="red" size="2">*</font><b><bean:message key="Gender"/></b>
										</div>
									</td>
									<td class='tdfonthead' width="10%">
										<div align="center">
											<font color="red" size="2">*</font><b><bean:message key="lowerAge"/></b>
										</div>
									</td>
									<td class='tdfonthead' width="10%">
										<div align="center">
											<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
										</div>
									</td>
									<td class='tdfonthead' width="10%">
										<div align="center">
											<font color="red" size="2">*</font><b><bean:message key="highAge"/></b>
										</div>
									</td>
									<td class='tdfonthead' width="10%">
										<div align="center">
											<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
										</div>
									</td>
								</logic:equal>


							</logic:greaterEqual>


							<!--LABORATORY  -->

							<logic:equal name="TestMandRefMstFB" property="criteriaCode"
								value="22">
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Laboratory"/></b>
									</div>
								</td>

							</logic:equal>


							<!-- Laboratory Sample -->

							<logic:equal name="TestMandRefMstFB" property="criteriaCode"
								value="23">
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Laboratory"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Sample"/></b>
									</div>
								</td>
							</logic:equal>

							<!-- Laboratory Sample Age -->
							<logic:equal name="TestMandRefMstFB" property="criteriaCode"
								value="24">
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Laboratory"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Sample"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="lowerAge"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="highAge"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
							</logic:equal>
							<!-- Laboratory Sample Gender -->
							<logic:equal name="TestMandRefMstFB" property="criteriaCode"
								value="25">
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Laboratory"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Sample"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Gender"/></b>
									</div>
								</td>
							</logic:equal>
							<!-- Laboratory Sample Mandatory -->
							<logic:equal name="TestMandRefMstFB" property="criteriaCode"
								value="26">
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Laboratory"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Sample"/></b>
									</div>
								</td>



								<td class='tdfonthead' width="10%">
									<div align="center">
										<b>Mandatory</b>
									</div>
								</td>

								
							</logic:equal>


							<!-- Laboratory Sample Gender Age -->
							<logic:equal name="TestMandRefMstFB" property="criteriaCode"
								value="27">
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Laboratory"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Sample"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Gender"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="lowerAge"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="highAge"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
							</logic:equal>

							<!-- Laboratory Gender -->
							<logic:equal name="TestMandRefMstFB" property="criteriaCode"
								value="28">
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Laboratory"/></b>
									</div>
								</td>

								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Gender"/></b>
									</div>
								</td>
							</logic:equal>
							<!-- Laboratory Gender Age -->
							<logic:equal name="TestMandRefMstFB" property="criteriaCode"
								value="29">
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Laboratory"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Gender"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="lowerAge"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="highAge"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
							</logic:equal>

							<!-- Laboratory Age -->
							<logic:equal name="TestMandRefMstFB" property="criteriaCode"
								value="30">
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Laboratory"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="lowerAge"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="highAge"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
							</logic:equal>
							<!-- Laboratory Sample mandatory Age -->
							<logic:equal name="TestMandRefMstFB" property="criteriaCode"
								value="31">
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Laboratory"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="Sample"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<b>Mandatory</b>
									</div>
								</td>

								



								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="lowerAge"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="highAge"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="10%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
							</logic:equal>


							<logic:notEqual name="TestMandRefMstFB" property="criteriaCode"
								value="10">
								<!-- /* added by chandan for new range */ -->
								<logic:equal name="TestMandRefMstFB" property="rangeTyp"
								value="1">
								<td class='tdfonthead' width="8%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="rangeFrom"/> </b>
									</div>
								</td>
								<td class='tdfonthead' width="8%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="8%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="rangeTo"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="8%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
								<td class='tdfonthead' width="8%">
									<div align="center">
										<b>Symbol</b>
									</div>
								</td>
								<td class='tdfonthead' width="8%">
									<div align="center">
										<b>Remarks</b>
									</div>
								</td>
								
								<td class='tdfonthead' width="1%">
									<div align='right'>
										<img src="/HISInvestigationG5/hisglobal/images/plus.gif" id="addImg"
											onclick="AddRowToTable()">
									</div>
								</td>
								</logic:equal>
								
								<logic:notEqual name="TestMandRefMstFB" property="rangeTyp"
								value="1">
								
									<td class='tdfonthead' width="8%">
									<div align="center">
										<font color="red" size="2">*</font><b>Range </b>
									</div>
								</td>
								<td class='tdfonthead' width="8%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
							
								<td class='tdfonthead' width="8%">
									<div align="center">
										<b>Symbol</b>
									</div>
								</td>
								<td class='tdfonthead' width="8%">
									<div align="center">
										<b>Remarks</b>
									</div>
								</td>
								
								<td class='tdfonthead' width="1%">
									<div align='right'>
										<img src="/HISInvestigationG5/hisglobal/images/plus.gif" id="addImg"
											onclick="AddRowToTable()">
									</div>
								</td>
								</logic:notEqual>
							</logic:notEqual>








							<logic:equal name="TestMandRefMstFB" property="criteriaCode"
								value="10">
								<!-- /* added by chandan for new range */ -->
								<logic:equal name="TestMandRefMstFB" property="rangeTyp"
								value="1">
								
								<td class='tdfonthead' width="8%">
									<div align="center">
										<font color="red" size="2">*</font><b>Range From</b>
									</div>
								</td>
								<td class='tdfonthead' width="8%"><div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div></td>
								<td class='tdfonthead' width="8%"><div align="center">
										<font color="red" size="2">*</font><b><bean:message key="rangeTo"/></b>
									</div></td>
								<td class='tdfonthead' width="8%"><div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div></td>
								<td class='tdfonthead' width="8%"><div align="center">
										<b><bean:message key="symbol"/></b>
									</div></td>
								<td class='tdfonthead' width="8%"><div align="center">
										<b><bean:message key="remarks"/></b>
									</div></td>
								
								<td class='tdfonthead' width="1%" colspan="5"><div
										align='right' style="display: none;">
										<img src="/HISInvestigationG5/hisglobal/images/plus.gif" id="addImg"
											onclick="AddRowToTable()">
									</div></td>
									</logic:equal>
									
									<!-- /* added by chandan for new range */ -->
									<logic:notEqual name="TestMandRefMstFB" property="rangeTyp"
								value="1">
									
									<td class='tdfonthead' width="8%">
									<div align="center">
										<font color="red" size="2">*</font><b>Range </b>
									</div>
								</td>
								<td class='tdfonthead' width="8%">
									<div align="center">
										<font color="red" size="2">*</font><b><bean:message key="uom"/></b>
									</div>
								</td>
							
								<td class='tdfonthead' width="8%">
									<div align="center">
										<b>Symbol</b>
									</div>
								</td>
								<td class='tdfonthead' width="8%">
									<div align="center">
										<b>Remarks</b>
									</div>
								</td>
								
								<td class='tdfonthead' width="1%">
									<div align='right'>
										<img src="/HISInvestigationG5/hisglobal/images/plus.gif" id="addImg"
											onclick="AddRowToTable()">
									</div>
								</td>
									
									</logic:notEqual>
							</logic:equal>
						</tr>


						<!-- row 1 ends -->





				<logic:present name="<%=InvestigationConfig.LIST_TESTMANDREF_VO %>">

				<%
     
  	 List<TestMandRefMasterVO> testmandRefMasterVOLst=(List<TestMandRefMasterVO>)session.getAttribute(InvestigationConfig.LIST_TESTMANDREF_VO);
  	 
 	
 		int testmandRefLstSize=testmandRefMasterVOLst.size();
  int rowCount=0;
 		
 		
 		
 		for(int count=0; count<testmandRefLstSize;count++)
 		{
 			TestMandRefMasterVO testmandrefmastervo=testmandRefMasterVOLst.get(count);
 			rowCount=count+1;
 			String rowcount=String.valueOf(testmandRefLstSize);
					%>	<!--  -->
								<tr id="<%=count+1%>">
									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="12">
										<td class='tdfont' width="10%"><div align="center">
												<html:select name="TestMandRefMstFB" property="gender" value="<%=testmandrefmastervo.getGender()%>">
													<html:option value='-1'>Select Value</html:option>
													<html:option value='0'><bean:message key="male"/></html:option>
													<html:option value='1'><bean:message key="female"/></html:option>
													<html:option value='2'><bean:message key="others"/></html:option>
												</html:select>
											</div></td>
									</logic:equal>

									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="11">
										<td class='tdfont' width="8%">
											<div align="center">
													<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getLowAge() %>" property="lowAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
										
													
											</div>
										</td>
										<td class='tdfont' width="9%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="lowAgeUom" value="<%=testmandrefmastervo.getLowAgeUom() %>">
													<html:option value='-1'>Select Value</html:option>
													<html:option value='1'><bean:message key="yearFormat"/> </html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>
										<td class='tdfont' width="8%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getHighAge() %>" property="highAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="8%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="highAgeUom" value="<%=testmandrefmastervo.getHighAgeUom() %>"
													 >
													<html:option value='-1'>Select Value</html:option>
													<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>
									</logic:equal>


									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="13">
										<td class='tdfont' width="7%">
											<div align="center">
												<b><html:select name="TestMandRefMstFB" property="gender" value="<%=testmandrefmastervo.getGender() %>"
														 >
														<html:option value='-1'>Select Value</html:option>
														<html:option value='0'><bean:message key="male"/></html:option>
														<html:option value='1'><bean:message key="female"/></html:option>
														<html:option value='2'><bean:message key="others"/></html:option>

													</html:select></b>
											</div>
										</td>
										<td class='tdfont' width="6%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getLowAge() %>" property="lowAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="7%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="lowAgeUom" value="<%=testmandrefmastervo.getLowAgeUom() %>"
													 >
													<html:option value='-1'>Select Value</html:option>
													<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>
										<td class='tdfont' width="6%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getHighAge() %>" property="highAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="7%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="highAgeUom" value="<%=testmandrefmastervo.getHighAgeUom() %>"
													 >
													<html:option value='-1'>Select Value</html:option>
													<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>
									</logic:equal>



									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="14">
										

										<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_MAND_COMBO%>">
												<div align="left">

													<html:select name="TestMandRefMstFB" property="mandCode"
														tabindex="1" style="width:200px;" value="<%=testmandrefmastervo.getMandCode()%>">
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_MAND_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>


									</logic:equal>

									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="15">
									

										<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_MAND_COMBO%>">
												<div align="left">

													<html:select name="TestMandRefMstFB" property="mandCode" value="<%=testmandrefmastervo.getMandCode() %>"
														tabindex="1" style="width:200px;">
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_MAND_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>

<td class='tdfont' width="7%">
											<div align="center">
												<b><html:select name="TestMandRefMstFB" property="gender" value="<%=testmandrefmastervo.getGender() %>"
														 >
														<html:option value='-1'>Select Value</html:option>
														<html:option value='0'><bean:message key="male"/></html:option>
														<html:option value='1'><bean:message key="female"/></html:option>
														<html:option value='2'><bean:message key="others"/></html:option>

													</html:select></b>
											</div>
										</td>
									</logic:equal>

									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="16">
										

										<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_MAND_COMBO%>">
												<div align="left">

													<html:select name="TestMandRefMstFB" property="mandCode" value="<%=testmandrefmastervo.getMandCode()%>"
														tabindex="1" style="width:200px;">
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_MAND_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>
											
											<td class='tdfont' width="6%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getLowAge() %>" property="lowAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="7%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="lowAgeUom" value="<%=testmandrefmastervo.getLowAgeUom() %>"
													 >
													<html:option value='-1'>Select Value</html:option>
													<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>
										<td class='tdfont' width="6%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getHighAge() %>" property="highAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="7%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="highAgeUom" value="<%=testmandrefmastervo.getHighAgeUom() %>"
													 >
													<html:option value='-1'>Select Value</html:option>
													<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>
									</logic:equal>

									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="17">
									

										<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_MAND_COMBO%>">
												<div align="left">

													<html:select name="TestMandRefMstFB" property="mandCode" value="<%=testmandrefmastervo.getMandCode()%>"
														tabindex="1" style="width:200px;">
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_MAND_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>
											
											<td class='tdfont' width="7%">
											<div align="center">
												<b><html:select name="TestMandRefMstFB" property="gender" value="<%=testmandrefmastervo.getGender() %>"
														 >
														<html:option value='-1'>Select Value</html:option>
														<html:option value='0'><bean:message key="male"/></html:option>
														<html:option value='1'><bean:message key="female"/></html:option>
														<html:option value='2'><bean:message key="others"/></html:option>

													</html:select></b>
											</div>
										</td>
										<td class='tdfont' width="6%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getLowAge() %>" property="lowAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="7%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="lowAgeUom" value="<%=testmandrefmastervo.getLowAgeUom() %>"
													 >
													<html:option value='-1'>Select Value</html:option>
													<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>
										<td class='tdfont' width="6%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getHighAge() %>" property="highAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="7%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="highAgeUom" value="<%=testmandrefmastervo.getHighAgeUom() %>"
													 >
													<html:option value='-1'>Select Value</html:option>
													<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>

									</logic:equal>

									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="18">
										


										<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>">
												<div align="left">

													<html:select name="TestMandRefMstFB" property="sampleCode" value="<%=testmandrefmastervo.getSampleCode()%>"
														tabindex="1" style="width:200px;">
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>


									</logic:equal>



									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="19">
										<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>">
												<div align="left">

													<html:select name="TestMandRefMstFB" property="sampleCode" value="<%=testmandrefmastervo.getSampleCode()%>"
														tabindex="1" style="width:200px;">
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>


										<td class='tdfont' width="8%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getLowAge() %>" property="lowAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="9%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="lowAgeUom" value="<%=testmandrefmastervo.getLowAgeUom() %>"
													 >
													<html:option value='-1'>Select Value</html:option>
													<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>
										<td class='tdfont' width="8%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getHighAge() %>" property="highAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="8%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="highAgeUom" value="<%=testmandrefmastervo.getHighAgeUom()%>"
													 >
													<html:option value='-1'>Select Value</html:option>
													<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>

									</logic:equal>


									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="20">
										<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>">
												<div align="left">

													<html:select name="TestMandRefMstFB" property="sampleCode" value="<%=testmandrefmastervo.getSampleCode()%>"
														tabindex="1" style="width:200px;">
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>
										<td class='tdfont' width="7%">
											<div align="center">
												<b><html:select name="TestMandRefMstFB" property="gender" value="<%=testmandrefmastervo.getGender()%>"
														 >
														<html:option value='-1'>Select Value</html:option>
														<html:option value='0'><bean:message key="male"/></html:option>
														<html:option value='1'><bean:message key="female"/></html:option>
														<html:option value='2'><bean:message key="others"/></html:option>

													</html:select></b>
											</div>
										</td>

									</logic:equal>



									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="21">
										<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>">
												<div align="left">

													<html:select name="TestMandRefMstFB" property="sampleCode" value="<%=testmandrefmastervo.getSampleCode()%>"
														tabindex="1" style="width:200px;">
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>


										<td class='tdfont' width="7%">
											<div align="center">
												<b><html:select name="TestMandRefMstFB" property="gender" value="<%=testmandrefmastervo.getGender() %>"
														 >
														<html:option value='-1'>Select Value</html:option>
														<html:option value='0'><bean:message key="male"/></html:option>
														<html:option value='1'><bean:message key="female"/></html:option>
														<html:option value='2'><bean:message key="others"/></html:option>

													</html:select></b>
											</div>
										</td>
										<td class='tdfont' width="6%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getLowAge() %>" property="lowAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="7%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="lowAgeUom" value="<%=testmandrefmastervo.getLowAgeUom() %>"
													 >
													<html:option value='-1'>Select Value</html:option>
													<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>
										<td class='tdfont' width="6%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getHighAge() %>" property="highAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="7%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="highAgeUom" value="<%=testmandrefmastervo.getHighAgeUom()%>"
													 >
													<html:option value='-1'>Select Value</html:option>
												<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>
									</logic:equal>


									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="22">
										<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_LAB_COMBO%>">
												<div align="left">

													<html:select name="TestMandRefMstFB" property="labCode" tabindex="1" value="<%=testmandrefmastervo.getLabCode()%>"
														style="width:200px;">
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_LAB_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>
									</logic:equal>

									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="23">
										<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_LAB_COMBO%>">
												<div align="left">

<%-- 													<html:select name="TestMandRefMstFB" property="labCode" onchange='<%="getLabSample("+rowCount+")"%>' tabindex="1" value="<%=testmandrefmastervo.getLabCode() %>" style="width:200px;" > 
 --%>										<html:select name="TestMandRefMstFB" property="labCode" tabindex="1" value="<%=testmandrefmastervo.getLabCode() %>" style="width:200px;" > 
 
 														<html:option value="-1">Select Value</html:option>
	
														<html:options
															collection="<%=InvestigationConfig.LIST_LAB_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>
		<!-- sample div -->					<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>">
												<div align="left" id='<%="samplediv"+count%>' >

													<html:select name="TestMandRefMstFB" property="sampleCode" value="<%=testmandrefmastervo.getSampleCode() %>"
														tabindex="1" style="width:200px;" >
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>

									</logic:equal>

									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="24">
										<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_LAB_COMBO%>">
												<div align="left">

													<%-- <html:select name="TestMandRefMstFB" property="labCode" onchange='<%="getLabSample("+rowCount+")"%>' tabindex="1" value="<%=testmandrefmastervo.getLabCode() %>" style="width:200px;" > --%> 
														<html:select name="TestMandRefMstFB" property="labCode" tabindex="1" value="<%=testmandrefmastervo.getLabCode() %>" style="width:200px;" >
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_LAB_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>
		<!-- sample div -->					<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>">
												<div align="left" id='<%="samplediv"+count%>' >

													<html:select name="TestMandRefMstFB" property="sampleCode" value="<%=testmandrefmastervo.getSampleCode() %>"
														tabindex="1" style="width:200px;" >
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>
										<td class='tdfont' width="6%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getLowAge() %>" property="lowAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="7%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="lowAgeUom" value="<%=testmandrefmastervo.getLowAgeUom()%>"
													 >
													<html:option value='-1'>Select Value</html:option>
													<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>
										<td class='tdfont' width="6%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getHighAge() %>" property="highAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="7%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="highAgeUom" value="<%=testmandrefmastervo.getHighAgeUom() %>"
													 >
													<html:option value='-1'>Select Value</html:option>
													<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>
									</logic:equal>
									<!-- labCode sample gender -->
									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="25">
									<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_LAB_COMBO%>">
												<div align="left">

<%-- 													<html:select name="TestMandRefMstFB" property="labCode" onchange='<%="getLabSample("+rowCount+")"%>' tabindex="1" value="<%=testmandrefmastervo.getLabCode() %>" style="width:200px;" > 
 --%>													<html:select name="TestMandRefMstFB" property="labCode" tabindex="1" value="<%=testmandrefmastervo.getLabCode() %>" style="width:200px;" > 
 														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_LAB_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>
		<!-- sample div -->					<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>">
												<div align="left" id='<%="samplediv"+count%>' >

													<html:select name="TestMandRefMstFB" property="sampleCode" value="<%=testmandrefmastervo.getSampleCode() %>"
														tabindex="1" style="width:200px;" >
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>
										<td class='tdfont' width="7%">
											<div align="center">
												<b><html:select name="TestMandRefMstFB" property="gender" value="<%=testmandrefmastervo.getGender() %>"
														 >
														<html:option value='-1'>Select Value</html:option>
														<html:option value='0'><bean:message key="male"/></html:option>
														<html:option value='1'><bean:message key="female"/></html:option>
														<html:option value='2'><bean:message key="others"/></html:option>

													</html:select></b>
											</div>
										</td>

									</logic:equal>
									<!-- Laboratory Sample Mandatory -->
									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="26" >
										<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_LAB_COMBO%>">
												<div align="left">

<%-- 													<html:select name="TestMandRefMstFB" property="labCode" onchange='<%="getLabSample("+rowCount+")"%>' tabindex="1" value="<%=testmandrefmastervo.getLabCode() %>" style="width:200px;" > 
 --%>														<html:select name="TestMandRefMstFB" property="labCode" tabindex="1" value="<%=testmandrefmastervo.getLabCode() %>" style="width:200px;" > 
 													<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_LAB_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>
		<!-- sample div -->					<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>">
												<div align="left" id='<%="samplediv"+count%>' >

													<html:select name="TestMandRefMstFB" property="sampleCode" value="<%=testmandrefmastervo.getSampleCode() %>"
														tabindex="1" style="width:200px;" >
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>
									


										<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_MAND_COMBO%>">
												<div align="left">

													<html:select name="TestMandRefMstFB" property="mandCode" value="<%=testmandrefmastervo.getMandCode() %>"
														tabindex="1" style="width:200px;">
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_MAND_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>
									</logic:equal>
<!-- on change function -->
									<!-- Laboratory Sample gender age -->
									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="27" >
										<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_LAB_COMBO%>">
												<div align="left">

<%-- 													<html:select name="TestMandRefMstFB" property="labCode" onchange='<%="getLabSample("+rowCount+")"%>' tabindex="1" value="<%=testmandrefmastervo.getLabCode() %>" style="width:200px;" > 
 --%>													<html:select name="TestMandRefMstFB" property="labCode" tabindex="1" value="<%=testmandrefmastervo.getLabCode() %>" style="width:200px;" > 
 														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_LAB_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>
		<!-- sample div -->					<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>">
												<div align="left" id='<%="samplediv"+count%>' >

													<html:select name="TestMandRefMstFB" property="sampleCode" value="<%=testmandrefmastervo.getSampleCode() %>"
														tabindex="1" style="width:200px;" >
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>
										<td class='tdfont' width="7%">
											<div align="center">
												<b><html:select name="TestMandRefMstFB" property="gender" value="<%=testmandrefmastervo.getGender()%>"
														 >
														<html:option value='-1'>Select Value</html:option>
														<html:option value='0'><bean:message key="male"/></html:option>
														<html:option value='1'><bean:message key="female"/></html:option>
														<html:option value='2'><bean:message key="others"/></html:option>

													</html:select></b>
											</div>
										</td>

										<td class='tdfont' width="6%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getLowAge() %>" property="lowAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="7%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="lowAgeUom" value="<%=testmandrefmastervo.getLowAgeUom()%>"
													 >
													<html:option value='-1'>Select Value</html:option>
													<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>
										<td class='tdfont' width="6%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getHighAge() %>" property="highAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="7%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="highAgeUom" value="<%=testmandrefmastervo.getHighAgeUom() %>"
													 >
													<html:option value='-1'>Select Value</html:option>
													<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>

									</logic:equal>
									<!-- Laboratory gender -->
									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="28">
										<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_LAB_COMBO%>">
												<div align="left">

													<html:select name="TestMandRefMstFB" property="labCode" tabindex="1" value="<%=testmandrefmastervo.getLabCode() %>"
														style="width:200px;">
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_LAB_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>
										<td class='tdfont' width="7%">
											<div align="center">
												<b><html:select name="TestMandRefMstFB" property="gender" value="<%=testmandrefmastervo.getGender()%>"
														 >
														<html:option value='-1'>Select Value</html:option>
														<html:option value='0'><bean:message key="male"/></html:option>
														<html:option value='1'><bean:message key="female"/></html:option>
														<html:option value='2'><bean:message key="others"/></html:option>

													</html:select></b>
											</div>
										</td>


									</logic:equal>
									<!-- Laboratory Sample gender age -->
									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="29">
										<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_LAB_COMBO%>">
												<div align="left">

													<html:select name="TestMandRefMstFB" property="labCode" tabindex="1" value="<%=testmandrefmastervo.getLabCode() %>"
														style="width:200px;">
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_LAB_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>
										<td class='tdfont' width="7%">
											<div align="center">
												<b><html:select name="TestMandRefMstFB" property="gender" value="<%=testmandrefmastervo.getGender()%>"
														 >
														<html:option value='-1'>Select Value</html:option>
														<html:option value='0'><bean:message key="male"/></html:option>
														<html:option value='1'><bean:message key="female"/></html:option>
														<html:option value='2'><bean:message key="others"/></html:option>

													</html:select></b>
											</div>
										</td>

										<td class='tdfont' width="6%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getLowAge() %>" property="lowAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="7%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="lowAgeUom" value="<%=testmandrefmastervo.getLowAgeUom() %>"
													 >
													<html:option value='-1'>Select Value</html:option>
												<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>
										<td class='tdfont' width="6%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getHighAge() %>" property="highAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="7%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="highAgeUom" value="<%=testmandrefmastervo.getHighAgeUom() %>"
													 >
													<html:option value='-1'>Select Value</html:option>
													<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>

									</logic:equal>

									<!-- Laboratory age -->
									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="30">
										<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_LAB_COMBO%>">
												<div align="left">

													<html:select name="TestMandRefMstFB" property="labCode" tabindex="1" value="<%=testmandrefmastervo.getLabCode()%>"
														style="width:200px;">
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_LAB_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>
										<td class='tdfont' width="6%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getLowAge() %>" property="lowAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="7%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="lowAgeUom" value="<%=testmandrefmastervo.getLowAgeUom()%>"
													 >
													<html:option value='-1'>Select Value</html:option>
													<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>
										<td class='tdfont' width="6%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getHighAge() %>" property="highAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="7%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="highAgeUom" value="<%=testmandrefmastervo.getHighAgeUom()%>"
													 >
													<html:option value='-1'>Select Value</html:option>
													<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>

									</logic:equal>

									<!-- Laboratory Sample gender age -->
									<logic:equal name="TestMandRefMstFB" property="criteriaCode"
										value="31">
										<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_LAB_COMBO%>">
												<div align="left">

<%-- 													<html:select name="TestMandRefMstFB" property="labCode" onchange='<%="getLabSample("+rowCount+")"%>' tabindex="1" value="<%=testmandrefmastervo.getLabCode() %>" style="width:200px;" > 
 --%>													<html:select name="TestMandRefMstFB" property="labCode" tabindex="1" value="<%=testmandrefmastervo.getLabCode() %>" style="width:200px;" > 
 														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_LAB_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>
		<!-- sample div -->					<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>">
												<div align="left" id='<%="samplediv"+count%>' >

													<html:select name="TestMandRefMstFB" property="sampleCode" value="<%=testmandrefmastervo.getSampleCode() %>"
														tabindex="1" style="width:200px;" >
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>
									

										<td width="10%" class="tdfont"><logic:present
												name="<%=InvestigationConfig.LIST_MAND_COMBO%>">
												<div align="left">

													<html:select name="TestMandRefMstFB" property="mandCode" value="<%=testmandrefmastervo.getMandCode()%>"
														tabindex="1" style="width:200px;">
														<html:option value="-1">Select Value</html:option>
														<html:options
															collection="<%=InvestigationConfig.LIST_MAND_COMBO%>"
															property="value" labelProperty="label" />
													</html:select>
												</div>
											</logic:present></td>
										<td class='tdfont' width="6%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getLowAge() %>" property="lowAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="7%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="lowAgeUom" value="<%=testmandrefmastervo.getLowAgeUom() %>"
													 >
													<html:option value='-1'>Select Value</html:option>
													<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>
										<td class='tdfont' width="6%">
											<div align="center">
												<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getHighAge() %>" property="highAge" size="6"
													maxlength="6" onkeypress="return validateNumeric(event)"
													tabindex="1"></html:text>
											</div>
										</td>
										<td class='tdfont' width="7%">
											<div align="center">
												<html:select name="TestMandRefMstFB" property="highAgeUom" value="<%=testmandrefmastervo.getHighAgeUom()%>"
													 >
													<html:option value='-1'>Select Value</html:option>
													<html:option value='1'><bean:message key="yearFormat"/></html:option>
													<html:option value='2'><bean:message key="monthFormat"/></html:option>
													<html:option value='0'><bean:message key="days"/></html:option>
												</html:select>
											</div>
										</td>

									</logic:equal>

                            <!-- /* added by chandan for new range */ -->
                             <logic:equal name="TestMandRefMstFB" property="rangeTyp"
										value="1">
									<td class='tdfont' width="8%">
										<div align="center">
											<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getLowValue() %>" property="lowValue" size="6"
												maxlength="50" 
									tabindex="1"
												 ></html:text>
										</div>
									</td>
									<td class='tdfont' width="8%">
										<div align="center">
											<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getLowValueUom() %>" property="lowValueUom" size="16"
												maxlength="20"   onfocus='assignCurrentElement(this)'
												 ></html:text>
										</div>
									</td>
									<td class='tdfont' width="8%">
										<div align="center">
											<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getHighValue() %>" property="highValue" size="6"
												maxlength="50" 
									tabindex="1"
												 ></html:text>
										</div>
									</td>
									<td class='tdfont' width="8%">
										<div align="center">
											<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getHighValueUom() %>" property="highValueUom" size="16"
												maxlength="20"   onfocus='assignCurrentElement(this)'
												 ></html:text>
										</div>
									</td>
									</logic:equal>
									<!-- /* added by chandan for new range */ -->
									<logic:notEqual name="TestMandRefMstFB" property="rangeTyp"
										value="1">
										
										<td class='tdfont' width="8%">
										<div align="center">
											<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getRange() %>" property="range" size="6"
												maxlength="50" onkeypress="return validateAlphaNumericOnly(event,this)"
									tabindex="1"
												 ></html:text>
										</div>
									</td>
									<td class='tdfont' width="8%">
										<div align="center">
											<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getRangeUom() %>" property="rangeUom" size="16"
												maxlength="20"   onfocus='assignCurrentElement(this)'
												 ></html:text>
										</div>
									</td>
									
										</logic:notEqual>
									 <td class='tdfont' width="8%">
										<div align="center">
											<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getSymbol() %>" property="symbol" size="6"
												maxlength="50"  onfocus='assignCurrentElement(this)'></html:text>
										</div>
									</td>
									<td class='tdfont' width="8%">
										<div align="center">
											<html:text name="TestMandRefMstFB" value="<%=testmandrefmastervo.getRemarks() %>" property="remarks" size="6"
												maxlength="200" onkeypress="return CheckMaxLength(event,this,50,1)"></html:text>
										</div>
									</td>
									
									<td class='tdfont' width="1%"><%-- <html:hidden name="TestMandRefMstFB"
											property="seqNo" /> --%> <logic:notEqual name="TestMandRefMstFB"
											property="criteriaCode" value="10">
											<img src='/HISInvestigationG5/hisglobal/images/minus.gif'
												onclick="deleteRow(document.getElementById(<%=count+1%>))">
										</logic:notEqual></td>

								<html:hidden property="numberOfRowPresent" value="<%=rowcount%>"/>
								
								<html:hidden property="seqNo" value="<%=testmandrefmastervo.getSeqNo()%>"/>
								
								<html:hidden property="oldSeq" value="<%=testmandrefmastervo.getSeqNo()%>"/>
								
								</tr>
								<%} %>

						</logic:present>
						

<!-- SINGLE ROW 2 WHEN CRITERIA IS 10 -->
			
						<logic:equal name="TestMandRefMstFB" property="hmode" value="ADD">
						<logic:equal name="TestMandRefMstFB" property="criteriaCode"
							value="10">
							<!-- /* added by chandan for new range */ -->
							<logic:equal name="TestMandRefMstFB" property="rangeTyp"
							value="1">
							<td class='tdfont'><div align='center'>
									<input type='text' name='lowValue' size='6' maxlength="50" onkeypress="return validateAlphaNumericOnly(event,this)"
										 >
								</div></td>
							<td class='tdfont'><div align='center'>
									<input type='text' name='lowValueUom' size='6' maxlength="20"   onfocus='assignCurrentElement(this)'
										 >
								</div></td>
							<td class='tdfont'><div align='center'>
									<input type='text' name='highValue' size='6' maxlength="50" 
										 >
								</div></td>
							<td class='tdfont'><div align='center'>
									<input type='text' name='highValueUom' size='6'  maxlength="20"   onfocus='assignCurrentElement(this)'
										 />
								</div></td>
							<td class='tdfont'><div align='center'>
									<input type='text' name='symbol' size='6' maxlength="50"  onfocus='assignCurrentElement(this)'
										 />
								</div></td>
							<td class='tdfont'><div align='center'>
									<input type='text' name='remarks' size='6' maxlength="200" onkeypress="return CheckMaxLength(event,this,50,1)">
										 
								</div></td>
							
							
							
							
							</logic:equal>
							
							<logic:notEqual name="TestMandRefMstFB" property="rangeTyp"
							value="1">
							
							<td class='tdfont'><div align='center'>
									<input type='text' name='range' size='6' maxlength="50" onkeypress="return validateAlphaNumericOnly(event,this)"
										 >
								</div></td>
							<td class='tdfont'><div align='center'>
									<input type='text' name='rangeUom' size='6' maxlength="20"   onfocus='assignCurrentElement(this)'
										 >
								</div></td>
								<td class='tdfont'><div align='center'>
									<input type='text' name='symbol' size='6' maxlength="50"  onfocus='assignCurrentElement(this)'
										 />
								</div></td>
							<td class='tdfont'><div align='center'>
									<input type='text' name='remarks' size='6' maxlength="200" onkeypress="return CheckMaxLength(event,this,50,1)">
										 
								</div></td>
							
							</logic:notEqual>
						<!-- 	
							<td class='tdfont'> <input type='hidden' name='seqNo'></td>
							<td class='tdfont' style="display: none">
								<div align="center" style="display: none">
									<input type="text" name="lowAge" size="6" maxlength="6"
										onkeypress="return getValidateStr(5);"
										onblur="numericOnly(this);" />
								</div> <select name="lowAgeUom"  ><option
										value='-1'>Select Value</option>
									<option value='1'>Year</option>
									<option value='2'>Month</option>
									<option value='0'>Days</option></select> <input type="text"
								name="highAge" size="6" maxlength="6"
								onkeypress="return getValidateStr(5);"
								onblur="numericOnly(this);"   />
								<select name="highAgeUom"  ><option
										value='-1'>Select Value</option>
									<option value='1'>Year</option>
									<option value='2'>Month</option>
									<option value='0'>Days</option></select>
							
							</td> -->
						</logic:equal>
						</logic:equal>
						
						
	<!-- SINGLE ROW ENDS FOR CRITERIA = 10 -->
			
			
			
			
					</table>
				</logic:present>

				<!-- END ROW 2 -->


		




			</his:ContentTag>


			<his:ButtonToolBarTag>
				<span id="saveDiv"> <logic:notEqual name="TestMandRefMstFB"
						property="hmode" value="MODIFY">
						<logic:notEqual name="TestMandRefMstFB" property="hmode"
							value="VIEW">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-sv.png"/>'
								style="cursor: pointer"
								onkeypress="if(event.keyCode==13) finalSubmit('SAVE')"
								onclick="finalSubmit('SAVE')" tabindex="1">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-clr.png"/>'
								style="cursor: pointer" onclick="clearaddForm()" tabindex="1">
						</logic:notEqual>
					</logic:notEqual> <logic:equal name="TestMandRefMstFB" property="hmode"
						value="MODIFY">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-mo.png"/>'
							style="cursor: pointer"
							onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')"
							onclick="finalSubmit('MODIFYSAVE')" tabindex="1">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-clr.png"/>'
							style="cursor: pointer" onclick="clearForm()"
							onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
					</logic:equal> <img class="button"
					src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
					style="cursor: pointer" onclick="submitForm('CANCEL')"
					onkeypress="if(event.keyCode==13) submitForm('CANCEL')"
					tabindex="1">
					
					<img class="button" src='<his:path src="/hisglobal/images/SpecialCharacters.png"/>'
					 style="cursor: pointer"
					 onclick='showSpecialCharacters("tableSpecialCharacters");'
					 tabindex="1">
					
					
					

				</span>
			</his:ButtonToolBarTag>
			
			
			
<!-- table for special characters starts-->

<div id='tableSpecialCharacters'  style="border:3px solid black; background-color:white; padding:25px; font-size:150%; text-align:center; display:none;">
<table>
    <tr>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="!"  onClick="populate('33')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">!</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="&amp;quot;"  onClick="populate('34')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">"</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="#"  onClick="populate('35')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">#</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="$"  onClick="populate('36')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">$</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="%"  onClick="populate('37')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">%</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="&amp;amp;"  onClick="populate('38')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">&amp;</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="'"  onClick="populate('39')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">'</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="("  onClick="populate('40')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">(</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title=")"  onClick="populate('41')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">)</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="*"  onClick="populate('42')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">*</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="+"  onClick="populate('43')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">+</span></a></td>
	  <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="+"  onClick="populate('44')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">,</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="-"  onClick="populate('45')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">-</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="."  onClick="populate('46')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">.</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="/"  onClick="populate('47')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">/</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="0"  onClick="populate('48')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">0</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="1"  onClick="populate('49')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">1</span></a></td>
     
    </tr>
    <tr>
	  <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="2"  onClick="populate('50')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">2</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="3"  onClick="populate('51')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">3</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="4"  onClick="populate('52')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">4</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="5"  onClick="populate('53')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">5</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="6"  onClick="populate('54')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">6</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="7"  onClick="populate('55')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">7</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="8"  onClick="populate('56')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">8</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="9"  onClick="populate('57')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">9</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title=":"  onClick="populate('58')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">:</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title=";"  onClick="populate('59')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">;</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="&amp;lt;"  onClick="populate('60')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">&lt;</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="="  onClick="populate('61')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">=</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="&amp;gt;"  onClick="populate('62')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">&gt;</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="?"  onClick="populate('63')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">?</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="@"  onClick="populate('64')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">@</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="A"  onClick="populate('65')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">A</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="B"  onClick="populate('66')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">B</span></a></td>
      
    </tr>
    <tr>
	<td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="C"  onClick="populate('67')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">C</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="D"  onClick="populate('68')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">D</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="E"  onClick="populate('69')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">E</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="F"  onClick="populate('70')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">F</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="G"  onClick="populate('71')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">G</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="H"  onClick="populate('72')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">H</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="I"  onClick="populate('73')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">I</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="J"  onClick="populate('74')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">J</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="K"  onClick="populate('75')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">K</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="L"  onClick="populate('76')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">L</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="M"  onClick="populate('77')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">M</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="N"  onClick="populate('78')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">N</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="O"  onClick="populate('79')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">O</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="P"  onClick="populate('80')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">P</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="Q"  onClick="populate('81')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">Q</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="R"  onClick="populate('82')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">R</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="S"  onClick="populate('83')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">S</span></a></td>

    </tr>
    <tr>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="T"  onClick="populate('84')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">T</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="U"  onClick="populate('85')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">U</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="V"  onClick="populate('86')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">V</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="W"  onClick="populate('87')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">W</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="X"  onClick="populate('88')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">X</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="Y"  onClick="populate('89')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">Y</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="Z"  onClick="populate('90')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">Z</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="["  onClick="populate('91')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">[</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="]"  onClick="populate('93')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">]</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="^"  onClick="populate('94')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">^</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="_"  onClick="populate('95')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">_</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="`"  onClick="populate('96')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">`</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="a"  onClick="populate('97')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">a</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="b"  onClick="populate('98')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">b</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="c"  onClick="populate('99')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">c</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="d"  onClick="populate('100')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">d</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="e"  onClick="populate('101')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">e</span></a></td>

    </tr>
    <tr>
	  <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="f"  onClick="populate('102')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">f</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="g"  onClick="populate('103')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">g</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="h"  onClick="populate('104')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">h</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="i"  onClick="populate('105')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">i</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="j"  onClick="populate('106')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">j</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="k"  onClick="populate('107')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">k</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="l"  onClick="populate('108')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">l</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="m"  onClick="populate('109')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">m</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="n"  onClick="populate('110')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">n</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="o"  onClick="populate('111')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">o</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="p"  onClick="populate('112')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">p</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="q"  onClick="populate('113')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">q</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="r"  onClick="populate('114')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">r</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="s"  onClick="populate('115')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">s</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="t"  onClick="populate('116')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">t</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="u"  onClick="populate('117')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">u</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="v"  onClick="populate('118')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">v</span></a></td>
      
    </tr>
	
	
    <tr>
	  <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="w"  onClick="populate('119')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">w</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="x"  onClick="populate('120')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">x</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="y"  onClick="populate('121')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">y</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="z"  onClick="populate('122')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">z</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="{"  onClick="populate('123')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">{</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="|"  onClick="populate('124')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">|</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="}"  onClick="populate('125')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">}</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="~"  onClick="populate('126')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">~</span></a></td>
      <td class="tdfonthead" style="cursor: default;">&nbsp;</td>
      <td class="tdfonthead" style="cursor: default;">&nbsp;</td>
      <td class="tdfonthead" style="cursor: default;">&nbsp;</td>
      <td class="tdfonthead" style="cursor: default;">&nbsp;</td>
      <td class="tdfonthead" style="cursor: default;">&nbsp;</td>
      <td class="tdfonthead" style="cursor: default;">&nbsp;</td>
      <td class="tdfonthead" style="cursor: default;">&nbsp;</td>
      <td class="tdfonthead" style="cursor: default;">&nbsp;</td>
      <td class="tdfonthead" style="cursor: default;">&nbsp;</td>

    </tr>
	<tr>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="f"  onClick="populate('161')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">¡</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="g"  onClick="populate('162')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">¢</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="h"  onClick="populate('163')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">£</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="i"  onClick="populate('164')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">¤</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="j"  onClick="populate('165')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">¥</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="k"  onClick="populate('166')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">¦</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="l"  onClick="populate('167')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">§</span></a></td>
      
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="n"  onClick="populate('169')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">©</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="o"  onClick="populate('170')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">ª</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="p"  onClick="populate('171')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">«</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="q"  onClick="populate('172')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">¬</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="r"  onClick="populate('173')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">­</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="s"  onClick="populate('174')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">®</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="t"  onClick="populate('175')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">¯</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="u"  onClick="populate('176')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">°</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="v"  onClick="populate('177')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">±</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="v"  onClick="populate('178')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">²</span></a></td>
      
 </tr>
	<tr>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="f"  onClick="populate('179')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">³</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="g"  onClick="populate('180')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">´</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="h"  onClick="populate('181')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">µ</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="i"  onClick="populate('182')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">¶</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="j"  onClick="populate('183')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">·</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="k"  onClick="populate('184')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">¸</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="l"  onClick="populate('185')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">¹</span></a></td>
      
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="n"  onClick="populate('186')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">º</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="o"  onClick="populate('187')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">»</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="p"  onClick="populate('188')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">¼</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="q"  onClick="populate('189')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">½</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="r"  onClick="populate('190')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">¾</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="s"  onClick="populate('191')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">¿</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="t"  onClick="populate('192')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">À</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="u"  onClick="populate('193')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">Á</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="v"  onClick="populate('194')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">Â</span></a></td>
      <td class="tdfonthead" style="cursor: default;"><a href="javascript: void(0);" style="cursor: pointer; display: block; height: 1.25em; margin-top: 0.25em; text-align: center;" title="v"  onClick="populate('195')" tabindex="-1"><span style="margin: 0pt auto; cursor: pointer;">Ã</span></a></td>
      
 </tr>

</table>
<table width="100%">
<tr><td width="100%"><div align="center"><img onClick="hideDivPopup()" src="/HISInvestigationG5/hisglobal/images/ok.gif" ></div></td></tr>
</table
></div>

<!-- table for special characters starts-->
			<his:status />




			<html:hidden property="numberOfRow" />
		</his:TransactionContainer>
	</html:form>
</body>
</html>
